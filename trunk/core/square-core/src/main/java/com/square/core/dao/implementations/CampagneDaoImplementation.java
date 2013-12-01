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

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.CampagneDao;
import com.square.core.model.Campagne;
import com.square.core.model.dto.CampagneCriteresRechercheDto;
import com.square.core.model.dto.CampagneCriteresRechercheLibelle;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Implementation du dao sur les campagnes.
 * @author cblanchard - SCUB
 */
public class CampagneDaoImplementation extends HibernateDaoBaseImplementation implements CampagneDao {

    /** Le service de mapping. */
    private SquareMappingService squareMappingService;

    @Override
    public int getTotalResultat(RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteres) {
        Criteria criteria = createCriteria(Campagne.class);
        criteria = requeteRecherche(criteres, criteria);
        criteria.setProjection(Projections.rowCount());
        return Integer.valueOf(criteria.uniqueResult().toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Campagne> rechercherCampagnesParCriteres(RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteres) {
        Criteria criteria = createCriteria(Campagne.class, criteres);
        criteria.createAlias("ressource", "ressource");
        criteria = requeteRecherche(criteres, criteria);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Campagne> rechercheCampagnesParLibelle(CampagneCriteresRechercheLibelle criteres) {
        final Criteria criteria = createCriteria(Campagne.class);

        // Critère sur le libelle
        if (criteres.getLibelle() != null && !("".equals(criteres.getLibelle()))) {
            criteria.add(Restrictions.ilike("libelle", criteres.getLibelle().toLowerCase() + "%"));
        }

        // Critères sur le maxResult
        if (criteres.getMaxResult() != null) {
            criteria.setFirstResult(0);
            criteria.setMaxResults(criteres.getMaxResult());
        }

        criteria.add(Restrictions.eq("supprime", false));
        // Critère sur le statut : une Campagne doit être active
        criteria.add(Restrictions.eq("statut.id", squareMappingService.getIdStatutCampagneActive()));
        criteria.addOrder(Order.asc("libelle"));

        return criteria.list();
    }

    @Override
    public void creerCampagne(Campagne campagne) {
        save(campagne);
    }

    /**
     * Construit la requete de recherche.
     * @param criteres les critères de recherche
     * @param criteria le criteria
     * @return la requete formée
     */
    private Criteria requeteRecherche(RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteres, Criteria criteria) {

        final CampagneCriteresRechercheDto campagneCriteresRechercheDto = criteres.getCriterias();

        final Long idCampagne = campagneCriteresRechercheDto.getId();
        if (idCampagne != null) {
            criteria.add(Restrictions.eq("id", idCampagne));
        }

        // Critère sur le code
        if (campagneCriteresRechercheDto.getCode() != null && !("".equals(campagneCriteresRechercheDto.getCode()))) {
             criteria.add(Restrictions.ilike("code", campagneCriteresRechercheDto.getCode().toLowerCase() + "%"));       
        }

        // Critère sur le libelle
        if (campagneCriteresRechercheDto.getLibelle() != null && !("".equals(campagneCriteresRechercheDto.getLibelle()))) {
            String critereLibelleCampagne = campagneCriteresRechercheDto.getLibelle();
            if (critereLibelleCampagne.indexOf("*") > -1) {
                critereLibelleCampagne = critereLibelleCampagne.replaceAll("\\*", "%");
            }
            criteria.add(Restrictions.ilike("libelle", critereLibelleCampagne.toLowerCase() + "%"));
        }

        // Critère sur le statut
        if (campagneCriteresRechercheDto.getListeStatuts() != null && !campagneCriteresRechercheDto.getListeStatuts().isEmpty()) {
            criteria.add(Restrictions.in("statut.id", campagneCriteresRechercheDto.getListeStatuts()));
        }

        // Critère sur le type
        if (campagneCriteresRechercheDto.getListeTypes() != null && !campagneCriteresRechercheDto.getListeTypes().isEmpty()) {
            criteria.add(Restrictions.in("type.id", campagneCriteresRechercheDto.getListeTypes()));
        }

        // Critères sur la borne inférieur de la date de début
        if (campagneCriteresRechercheDto.getDateDebutBorneInf() != null) {
            criteria.add(Restrictions.ge("dateDebut", campagneCriteresRechercheDto.getDateDebutBorneInf()));
        }
        // Critère sur la borne supérieur de la date de début
        if (campagneCriteresRechercheDto.getDateDebutBorneSup() != null) {
            criteria.add(Restrictions.le("dateDebut", campagneCriteresRechercheDto.getDateDebutBorneSup()));
        }
        // Criteres sur la borne inférieur de la date de fin
        if (campagneCriteresRechercheDto.getDateFinBorneInf() != null) {
            criteria.add(Restrictions.ge("dateFin", campagneCriteresRechercheDto.getDateFinBorneInf()));
        }
        // Critères sur la borne supérieur de la date de fin
        if (campagneCriteresRechercheDto.getDateFinBorneSup() != null) {
            criteria.add(Restrictions.le("dateFin", campagneCriteresRechercheDto.getDateFinBorneSup()));
        }
        // Critère sur le créateur de la campagne
        if (campagneCriteresRechercheDto.getIdCreateur() != null) {
            criteria.add(Restrictions.eq("ressource.id", campagneCriteresRechercheDto.getIdCreateur()));
        }

        criteria.add(Restrictions.eq("supprime", false));
        return criteria;
    }

    /**
     * Modifie squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    @Override
    public Campagne rechercherCampagneParId(Long id) {
        return load(id, Campagne.class);
    }

    @Override
    public Long rechercherSequence() {
        Long sequence = 0L;
        Object obj;
        final SQLQuery sq = createSqlQuery("SELECT nextval('campagne_sequence')");
        obj = sq.uniqueResult();
        sequence = (obj == null) ? null : Long.parseLong(obj.toString());

        return sequence;
    }
}
