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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.ActionNatureResultatDao;
import com.square.core.model.ActionNatureResultat;
import com.square.core.model.dto.ActionNatureResultatCriteresRechercheDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;

/**
 * Implémentation du dao des natures des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionNatureResultatDaoImplementation extends HibernateDaoBaseImplementation implements ActionNatureResultatDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<ActionNatureResultat> rechercherActionNatureResultatParCritere(ActionNatureResultatCriteresRechercheDto criteres) {
        final Criteria criteria = createCriteria(ActionNatureResultat.class);

        // Critère sur l'identifiant
        if (criteres.getId() != null) {
            criteria.add(Restrictions.eq("id", criteres.getId()));
        }
        // Critère sur le libellé
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
        // Critère sur l'identifiant de la nature d'action
        if (criteres.getIdActionNature() != null) {
            criteria.add(Restrictions.eq("nature.id", criteres.getIdActionNature()));
        }
        criteria.addOrder(Order.asc("ordre"));
        criteria.addOrder(Order.asc("libelle"));

        return criteria.list();
    }

    @Override
    public ActionNatureResultat rechercherNatureResultatActionById(Long idNatureResultatAction) {
        return load(idNatureResultatAction, ActionNatureResultat.class);
    }

}
