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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.ModeleEmailDao;
import com.square.core.model.ModeleEmail;
import com.square.core.model.dto.DimensionCriteresRechercheDto;

/**
 * Implémentation du dao sur les modèles d'email.
 * @author Clément Lavaud - SCUB
 */
public class ModeleEmailDaoImplementation extends HibernateDaoBaseImplementation implements ModeleEmailDao {

	@SuppressWarnings("unchecked")
    @Override
    public List<IdentifiantLibelleDto> rechercherListeModelesEmails(DimensionCriteresRechercheDto criteres) {

        final Criteria criteria = createCriteria(ModeleEmail.class);
        criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("id")).add(Projections.groupProperty("libelle"))
        		.add(Projections.groupProperty("ordre")));

        // Critère sur l'identifiant
        if (criteres.getId() != null) {
            criteria.add(Restrictions.eq("id", criteres.getId()));
        }
        // Critère sur le libelle
        if (criteres.getLibelle() != null && !criteres.getLibelle().equals("")) {
            criteria.add(Restrictions.ilike("libelle", criteres.getLibelle().toLowerCase() + "%"));
        }
        // Critère sur la visibilité
        if (criteres.getVisible() != null) {
            criteria.add(Restrictions.eq("visible", criteres.getVisible()));
        }
        // Maxresults
        if (criteres.getMaxResults() != null) {
            criteria.setFirstResult(0);
            criteria.setMaxResults(criteres.getMaxResults());
        }
        // Ordonner les éléments
        criteria.addOrder(Order.asc("ordre"));
        criteria.addOrder(Order.asc("libelle"));

        final List<Object[]> resultat = criteria.list();
        final List<IdentifiantLibelleDto> listeModeles = new ArrayList<IdentifiantLibelleDto>();
        for (Object[] row : resultat) {
        	listeModeles.add(new IdentifiantLibelleDto((Long) row[0], (String) row[1]));
        }
        return listeModeles;
    }

	@SuppressWarnings("unchecked")
    @Override
    public List<ModeleEmail> rechercherModelesEmailParCriteres(DimensionCriteresRechercheDto criteres) {

        final Criteria criteria = createCriteria(ModeleEmail.class);

        // Critère sur l'identifiant
        if (criteres.getId() != null) {
            criteria.add(Restrictions.eq("id", criteres.getId()));
        }
        // Critère sur le libelle
        if (criteres.getLibelle() != null && !criteres.getLibelle().equals("")) {
            criteria.add(Restrictions.ilike("libelle", criteres.getLibelle().toLowerCase() + "%"));
        }
        // Critère sur la visibilité
        if (criteres.getVisible() != null) {
            criteria.add(Restrictions.eq("visible", criteres.getVisible()));
        }
        // Maxresults
        if (criteres.getMaxResults() != null) {
            criteria.setFirstResult(0);
            criteria.setMaxResults(criteres.getMaxResults());
        }
        // Ordonner les éléments
        criteria.addOrder(Order.asc("ordre"));
        criteria.addOrder(Order.asc("libelle"));

        return criteria.list();
    }
}
