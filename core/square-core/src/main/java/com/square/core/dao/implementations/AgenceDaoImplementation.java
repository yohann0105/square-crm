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
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.StringValueTransformer;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.AgenceDao;
import com.square.core.model.Ressources.Agence;
import com.square.core.model.dto.DimensionCriteresRechercheDto;

/**
 * Implémentation du dao de l'agence.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class AgenceDaoImplementation extends HibernateDaoBaseImplementation implements AgenceDao {

    @Override
    public Agence rechercheAgenceParId(Long identifiant) {
        return load(identifiant, Agence.class);
    }

    @Override
    public void creerAgence(Agence agence) {
        save(agence);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Agence> rechercherAgenceParCriteres(DimensionCriteresRechercheDto criteres) {

        final Criteria criteria = createCriteria(Agence.class);

        final Long idAgence = criteres.getId();
        final Set<Long> eidsAgences = criteres.getEids();
        if (idAgence != null) {
            criteria.add(Restrictions.eq("id", idAgence));
        }
        if (eidsAgences != null && !eidsAgences.isEmpty()) {
            criteria.add(Restrictions.in("identifiantExterieur", (List<String>) CollectionUtils.collect(eidsAgences, StringValueTransformer.getInstance())));
        }
        // Critère sur le libellé de l'agence.
        final String libelleAgence = criteres.getLibelle();
        if (!StringUtils.isBlank(libelleAgence)) {
        	criteria.add(Restrictions.or(Restrictions.ilike("libelle", libelleAgence + "%"),
        		Restrictions.or(Restrictions.ilike("libelle", "% " + libelleAgence + "%"), Restrictions.ilike("libelle", "%'" + libelleAgence + "%"))));
        }
        if (criteres.getIdentifiantExterieur() != null) {
            criteria.add(Restrictions.eq("identifiantExterieur", criteres.getIdentifiantExterieur()));
        }
        if (criteres.getVisible() != null) {
            criteria.add(Restrictions.eq("supprime", !criteres.getVisible()));
        }

        criteria.addOrder(Order.asc("libelle"));

        return criteria.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Agence rechercheAgenceParEid(String eid) {
        final Criteria crit = createCriteria(Agence.class);
        crit.add(Restrictions.eq("identifiantExterieur", eid));
        return (Agence) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Agence> rechercheAgencesParIds(List<Long> listeIds) {
        final String requete = "select a from Agence a where a.id in (:listeIds)";
        final Query query = createQuery(requete);
        query.setParameterList("listeIds", listeIds);
        return query.list();
    }

}
