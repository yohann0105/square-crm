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

import com.square.core.dao.interfaces.ActionResultatDao;
import com.square.core.model.ActionResultat;
import com.square.core.model.dto.DimensionCritereRechercheResultatActionDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Implémentation du dao des résultats des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionResultatDaoImplementation extends HibernateDaoBaseImplementation implements ActionResultatDao {

    private SquareMappingService squareMappingService;

    @SuppressWarnings("unchecked")
    @Override
    public List<ActionResultat> rechercherResultatActionParCritere(DimensionCritereRechercheResultatActionDto criteres) {

        if (criteres.getDimensionCriteres() == null) {
            criteres.setDimensionCriteres(new DimensionCriteresRechercheDto());
        }

        final Criteria criteria = createCriteria(ActionResultat.class);

        // Critère sur l'identifiant
        if (criteres.getDimensionCriteres().getId() != null) {
            criteria.add(Restrictions.eq("id", criteres.getDimensionCriteres().getId()));
        }

        // Critère sur le libellé
        if (criteres.getDimensionCriteres().getLibelle() != null && !criteres.getDimensionCriteres().getLibelle().equals("")) {
            criteria.add(Restrictions.ilike("libelle", criteres.getDimensionCriteres().getLibelle().toLowerCase() + "%"));
        }

        // Critère sur la visibilité
        if (criteres.getDimensionCriteres().getVisible() != null) {
            criteria.add(Restrictions.eq("visible", criteres.getDimensionCriteres().getVisible()));
        }
        // Maxresults
        if (criteres.getDimensionCriteres().getMaxResults() != null) {
            criteria.setFirstResult(0);
            criteria.setMaxResults(criteres.getDimensionCriteres().getMaxResults());
        }
        // Critère sur l'opportunite
        if (criteres.getRecuperationOpportunite() != null && !criteres.getRecuperationOpportunite()) {
            criteria.add(Restrictions.not(Restrictions.eq("id", squareMappingService.getIdResultatOpportunite())));
        }

        criteria.addOrder(Order.asc("ordre"));
        criteria.addOrder(Order.asc("libelle"));
        
        return criteria.list();
    }

    @Override
    public ActionResultat rechercherActionResultatParId(Long idActionResultat) {
        return load(idActionResultat, ActionResultat.class);
    }

    /**
     * Modifie squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

}
