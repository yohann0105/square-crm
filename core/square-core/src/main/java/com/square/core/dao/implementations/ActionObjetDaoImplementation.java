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

import com.square.core.dao.interfaces.ActionObjetDao;
import com.square.core.model.ActionObjet;
import com.square.core.model.dto.DimensionCritereRechercheObjetDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;

/**
 * Implémentation du dao des objets des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionObjetDaoImplementation extends HibernateDaoBaseImplementation implements ActionObjetDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<ActionObjet> rechercherObjetActionParCritere(DimensionCritereRechercheObjetDto criteres) {
        if (criteres.getDimensionCriteres() == null) {
            criteres.setDimensionCriteres(new DimensionCriteresRechercheDto());
        }
        final Criteria criteria = createCriteria(ActionObjet.class);

        // Critère sur l'identifiant
        if (criteres.getDimensionCriteres().getId() != null) {
            criteria.add(Restrictions.eq("id", criteres.getDimensionCriteres().getId()));
        }
        // Critère sur le libelle
        if (criteres.getDimensionCriteres().getLibelle() != null && !criteres.getDimensionCriteres().getLibelle().equals("")) {
            criteria.add(Restrictions.ilike("libelle", criteres.getDimensionCriteres().getLibelle().toLowerCase() + "%"));
        }
        // Critère sur la visibilité
        if (criteres.getDimensionCriteres().getVisible() != null) {
            criteria.add(Restrictions.eq("visible", criteres.getDimensionCriteres().getVisible()));
        }
        // Critère sur le type de l'objet.
        if (criteres.getIdType() != null) {
            criteria.createAlias("actionType", "actionType").add(Restrictions.eq("actionType.id", criteres.getIdType()));
        }
        // Maxresults
        if (criteres.getDimensionCriteres().getMaxResults() != null) {
            criteria.setFirstResult(0);
            criteria.setMaxResults(criteres.getDimensionCriteres().getMaxResults());
        }
        // Ordonner les éléments
        criteria.addOrder(Order.asc("ordre"));
        criteria.addOrder(Order.asc("libelle"));
        
        return criteria.list();
    }

    @Override
    public ActionObjet rechercherObjetActionParId(Long idObjet) {
        return load(idObjet, ActionObjet.class);
    }

}
