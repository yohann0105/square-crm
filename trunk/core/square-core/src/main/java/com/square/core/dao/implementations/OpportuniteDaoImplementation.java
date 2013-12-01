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
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.OpportuniteDao;
import com.square.core.model.Opportunite;
import com.square.core.model.dto.OpportuniteCriteresRechercheDto;

/**
 * Inteface du DAO sur les opportunités.
 * @author cblanchard - SCUB
 */
public class OpportuniteDaoImplementation extends HibernateDaoBaseImplementation implements OpportuniteDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Opportunite> rechercherOpportunitesParCriteres(OpportuniteCriteresRechercheDto criteres) {
        final Criteria criteria = creerCriteria(criteres);
        criteria.createAlias("statut", "statutAlias");
        criteria.addOrder(Order.asc("statutAlias.ordre"));
        criteria.addOrder(Order.desc("dateCreation"));
        return criteria.list();
    }

    private Criteria creerCriteria(OpportuniteCriteresRechercheDto criteres) {
        final Criteria criteria = createCriteria(Opportunite.class);
        if (criteres.getIdPersonnePhysique() != null) {
            criteria.add(Restrictions.eq("personnePhysique.id", criteres.getIdPersonnePhysique()));
        }
        if (criteres.getIdentifiantExterieur() != null) {
            criteria.add(Restrictions.eq("identifiantExterieur", criteres.getIdentifiantExterieur()));
        }
        if (criteres.getListeIds() != null && criteres.getListeIds().size() > 0) {
            criteria.add(Restrictions.in("id", criteres.getListeIds()));
        }
        if (criteres.getIdResponsable() != null) {
            criteria.createAlias("opportuniteAttribution", "attribution");
            criteria.createAlias("attribution.ressource", "responsable");
            criteria.add(Restrictions.eq("responsable.id", criteres.getIdResponsable()));
        }
        //on ne recupere que les opportunites non supprimees
        criteria.add(Restrictions.eq("supprime", false));
        return criteria;
    }

    @Override
    public void creerOpportunite(Opportunite opportunite) {
        save(opportunite);
    }

    @Override
    public Opportunite rechercherOpportuniteParId(Long idOpportunite) {
        return load(idOpportunite, Opportunite.class);
    }

    @Override
    public Long rechercherSequence() {
        Long sequence = 0L;
        Object obj;
        final SQLQuery sq = createSqlQuery("SELECT nextval('opportunite_sequence')");
        obj = sq.uniqueResult();
        sequence = (obj == null) ? null : Long.parseLong(obj.toString());

        return sequence;
    }

    @Override
    public void supprimerOpportunite(Long idOpportunite) {
    	final Opportunite opportunite = load(idOpportunite, Opportunite.class);
    	opportunite.setDateSuppression(Calendar.getInstance());
    	opportunite.setSupprime(true);
    	update(opportunite);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean hasPersonneOpportuniteByStatuts(Long idPersonne, List<Long> listeIdsStatuts) {
        final Criteria criteria = createCriteria(Opportunite.class);
        if (idPersonne != null) {
            criteria.add(Restrictions.eq("personnePhysique.id", idPersonne));
        }
        if (listeIdsStatuts != null && listeIdsStatuts.size() > 0) {
            criteria.add(Restrictions.in("statut.id", listeIdsStatuts));
        }
        criteria.add(Restrictions.eq("supprime", false));
        final List<Opportunite> listeOpps = criteria.list();
        return listeOpps.size() > 0;
    }

    @Override
    public Integer countOpportunitesParCriteres(OpportuniteCriteresRechercheDto criteres) {
        final Criteria criteria = creerCriteria(criteres);
        criteria.setProjection(Projections.countDistinct("id"));
        return Integer.valueOf(criteria.uniqueResult().toString());
    }

}
