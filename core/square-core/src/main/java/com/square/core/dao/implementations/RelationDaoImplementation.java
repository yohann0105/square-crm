/*
 * This file is a part of Square, Customer Relationship Management Software for insurance's companies
 * Copyright (C) 2010-2012  SCUB <square@scub.net> - Mutuelle SMATIS FRANCE  <square@smatis.fr >
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.square.core.dao.implementations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.RelationDao;
import com.square.core.model.Relation;
import com.square.core.model.dto.RelationCriteresRechercheDto;

/**
 * Implémentation du Dao de la relation entre une personne et son bénéficiaire.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class RelationDaoImplementation extends HibernateDaoBaseImplementation implements RelationDao {

    @Override
    public void creerRelation(Relation relation) {
        save(relation);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Relation> rechercherRelationsParCriteres(RemotePagingCriteriasDto<RelationCriteresRechercheDto> remotePagingCriterias) {
        final RelationCriteresRechercheDto criteres = remotePagingCriterias.getCriterias();

        final Map<String, Object> parameters = new HashMap<String, Object>();
        final StringBuffer requete = new StringBuffer("from Relation");
        requete.append(" where 1=1");
        completerQuery(requete, criteres, parameters);
        if (remotePagingCriterias.getListeSorts() == null || remotePagingCriterias.getListeSorts().size() == 0) {
            requete.append(" order by type.relationGroupement.ordre, type.ordre");
        } else {
            requete.append(" order by");
            int countOrder = 0;
            for (RemotePagingSort order : remotePagingCriterias.getListeSorts()) {
                requete.append(countOrder > 0 ? ", " : " ").append(order.getSortField());
                requete.append(order.getSortAsc() == RemotePagingSort.REMOTE_PAGING_SORT_ASC ? " asc" : " desc");
                countOrder++;
            }
        }
        // on rajoute toujours un tri sur les ids de personne cible et source, pour assurer un tri identique à chaque fois
        requete.append(", personneCible.id, personneSource.id");

        final Query query = createQuery(requete.toString());
        query.setProperties(parameters);
        query.setFirstResult(remotePagingCriterias.getFirstResult());
        query.setMaxResults(remotePagingCriterias.getMaxResult());
        final List<Relation> resultat = query.list();

        return resultat;
    }

    @Override
    public int countRelationsParCriteres(RelationCriteresRechercheDto criteres) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        final StringBuffer requete = new StringBuffer("select count(id) from Relation rel");
        requete.append(" where 1=1");
        completerQuery(requete, criteres, parameters);
        final Query query = createQuery(requete.toString());
        query.setProperties(parameters);
        final Object count = query.uniqueResult();
        return count instanceof Integer ? ((Integer) count).intValue() : ((Long) count).intValue();
    }

    private void completerQuery(StringBuffer requete, RelationCriteresRechercheDto criteres, Map<String, Object> parameters) {
        if (criteres.getActif() != null) {
            requete.append(" and topActif = :topActif");
            parameters.put("topActif", criteres.getActif());
        }
        if (criteres.getGroupements() != null && criteres.getGroupements().size() > 0) {
            requete.append(" and type.relationGroupement.id in (:groupements)");
            parameters.put("groupements", criteres.getGroupements());
        }
        if (criteres.getPasDansGroupements() != null && criteres.getPasDansGroupements().size() > 0) {
            requete.append(" and type.relationGroupement.id not in (:pasGroupements)");
            parameters.put("pasGroupements", criteres.getPasDansGroupements());
        }
        if (criteres.getIdsRelations() != null && criteres.getIdsRelations().size() > 0) {
            requete.append(" and id in (:idsRelations)");
            parameters.put("idsRelations", criteres.getIdsRelations());
        }
        if (criteres.getTypes() != null && criteres.getTypes().size() > 0) {
            requete.append(" and type.id in (:types)");
            parameters.put("types", criteres.getTypes());
        }
        if (criteres.getDateFinMax() != null) {
            requete.append(" and dateFin is not null and dateFin <= :dateFinMax");
            parameters.put("dateFinMax", criteres.getDateFinMax());
        }
        if (criteres.getPasDansRelations() != null && criteres.getPasDansRelations().size() > 0) {
            requete.append(" and id not in (:pasRelations)");
            parameters.put("pasRelations", criteres.getPasDansRelations());
        }
        if (criteres.getIdPersonne() != null) {
            requete.append(" and (personneCible.id = :idPersonne or personneSource.id = :idPersonne)");
            parameters.put("idPersonne", criteres.getIdPersonne());
        } else {
            if (criteres.getIdPersonneCible() != null) {
                requete.append(" and personneCible.id = :idPersonneCible");
                parameters.put("idPersonneCible", criteres.getIdPersonneCible());
            }
            if (criteres.getIdPersonneSource() != null) {
                requete.append(" and personneSource.id = :idPersonneSource");
                parameters.put("idPersonneSource", criteres.getIdPersonneSource());
            }
        }
        if (criteres.getSupprime() != null) {
            requete.append(" and supprime = :supprime and personneCible.supprime = :supprime and personneSource.supprime = :supprime");
            parameters.put("supprime", criteres.getSupprime().booleanValue());
        }
    }

    @Override
    public Relation rechercherRelationParId(Long id) {
        return load(id, Relation.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Long> rechercherIdsRelationsADesactiver(Calendar date, int pagination) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        final StringBuffer requete = new StringBuffer("select id from Relation");
        requete.append(" where topActif = :topActif");
        parameters.put("topActif", true);
        requete.append(" and dateFin is not null and dateFin <= :dateFinMax");
        parameters.put("dateFinMax", date);
        requete.append(" order by id");

        final Query query = createQuery(requete.toString());
        query.setProperties(parameters);
        query.setFirstResult(0);
        query.setMaxResults(pagination);
        return query.list();
    }

    @Override
    public int desactiverRelations(List<Long> idsRelationsADesactiver) {
        final StringBuffer requete = new StringBuffer("update Relation set topActif = false where id in (:idsRelationsADesactiver)");
        final Query query = createQuery(requete.toString());
        query.setParameterList("idsRelationsADesactiver", idsRelationsADesactiver);
        return query.executeUpdate();
    }
}
