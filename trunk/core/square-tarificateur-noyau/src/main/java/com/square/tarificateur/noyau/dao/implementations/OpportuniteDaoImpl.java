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
package com.square.tarificateur.noyau.dao.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.tarificateur.noyau.dao.interfaces.OpportuniteDao;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheOpportuniteDto;
import com.square.tarificateur.noyau.model.opportunite.Adhesion;
import com.square.tarificateur.noyau.model.opportunite.Devis;
import com.square.tarificateur.noyau.model.opportunite.Opportunite;

/**
 * Implémentation de la couche d'accès aux données pour les opportunités.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class OpportuniteDaoImpl extends HibernateDaoBaseImplementation implements OpportuniteDao {

    @Override
    public Opportunite getOpportuniteByEid(Long eidOpportunite) {
        final Criteria crit = createCriteria(Opportunite.class);
        crit.add(Restrictions.eq("eidOpportunite", eidOpportunite));
        return (Opportunite) crit.uniqueResult();
    }

    @Override
    public void saveOpportunite(Opportunite opportunite) {
        save(opportunite);
    }

    @Override
    public Opportunite getOpportuniteById(Long idOpportunite) {
        return load(idOpportunite, Opportunite.class);
    }

    @Override
    public Adhesion getAdhesionByIdFichierCNS(String idFichier) {
        final Criteria crit = createCriteria(Adhesion.class);
        crit.add(Restrictions.eq("idFichierCNS", idFichier));
        return (Adhesion) crit.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<String> getIdsBaAdhesionNonTelecharges() {
        final Criteria crit = createCriteria(Adhesion.class);
        crit.setProjection(Projections.property("idFichierCNS"));
        crit.add(Restrictions.isNull("dateTelechargementBA"));
        crit.add(Restrictions.isNotNull("idFichierCNS"));
        return (List<String>) crit.list();
    }

    @Override
    public Long getIdDevisLieAdhesion(Long idAdhesion, Long idFinaliteAccepte) {
        final Criteria crit = createCriteria(Devis.class);
        crit.setProjection(Projections.property("id"));
        crit.createAlias("opportunite", "opp");
        crit.createAlias("opp.adhesion", "adh");
        crit.add(Restrictions.eq("adh.id", idAdhesion));
        crit.add(Restrictions.eq("finalite.id", idFinaliteAccepte));
        return (Long) crit.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Opportunite getOpportuniteByNumTransaction(String numeroTransaction) {
        final Criteria crit = createCriteria(Opportunite.class);
        crit.createAlias("adhesion", "adh");
        crit.add(Restrictions.eq("adh.numeroTransaction", numeroTransaction));
        return (Opportunite) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Opportunite> rechercherOpportunitesByCritere(CriteresRechercheOpportuniteDto criteres) {
        final Criteria crit = createCriteria(Opportunite.class);
        if (criteres.getListeEids() != null && criteres.getListeEids().size() > 0) {
            crit.add(Restrictions.in("eidOpportunite", criteres.getListeEids()));
        }
        if (criteres.getIdFinalite() != null) {
            crit.add(Restrictions.eq("finalite.id", criteres.getIdFinalite()));
        }
        if (criteres.getDebutDateCloture() != null) {
            crit.add(Restrictions.ge("dateCloture", criteres.getDebutDateCloture()));
        }
        if (criteres.getFinDateCloture() != null) {
            crit.add(Restrictions.lt("dateCloture", criteres.getFinDateCloture()));
        }
        if (criteres.getListeEidsNaturePersonneExclure() != null && criteres.getListeEidsNaturePersonneExclure().size() > 0) {
            crit.add(Restrictions.or(Restrictions.isNull("eidNaturePersonne"),
                Restrictions.not(Restrictions.in("eidNaturePersonne", criteres.getListeEidsNaturePersonneExclure()))));
        }
        return (List<Opportunite>) crit.list();
    }

}
