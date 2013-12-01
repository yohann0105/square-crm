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

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.CaisseRegimeDao;
import com.square.core.model.CaisseRegime;
import com.square.core.model.dto.DimensionCriteresRechercheRegimeDto;

/**
 * Implémentation du dao des régimes.
 * @author cblanchard - SCUB
 */
public class CaisseRegimeDaoImplementation extends HibernateDaoBaseImplementation implements CaisseRegimeDao {

    @Override
    public CaisseRegime rechercheRegimeParId(Long identifiant) {
        return load(identifiant, CaisseRegime.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CaisseRegime> rechercherRegimeParCriteres(DimensionCriteresRechercheRegimeDto criteres) {

        final Criteria criteria = createCriteria(CaisseRegime.class);

        // Critère sur l'identifiant
        if (criteres.getDimensionCriteres() != null && criteres.getDimensionCriteres().getId() != null) {
            criteria.add(Restrictions.eq("id", criteres.getDimensionCriteres().getId()));
        }
        // Critère sur le libelle
        if (criteres.getDimensionCriteres() != null && StringUtils.isNotBlank(criteres.getDimensionCriteres().getLibelle())) {
            criteria.add(Restrictions.ilike("libelle", criteres.getDimensionCriteres().getLibelle().toLowerCase() + "%"));
        }
        // Critère sur la visibilité
        if (criteres.getDimensionCriteres() != null && criteres.getDimensionCriteres().getVisible() != null) {
            criteria.add(Restrictions.eq("visible", criteres.getDimensionCriteres().getVisible()));
        }
        // Critère sur la visibilité Site Web
        if (criteres.getVisibleApplicatif() != null) {
            criteria.add(Restrictions.eq("visibleApplicatif", criteres.getVisibleApplicatif()));
        }
        // Maxresults
        if (criteres.getDimensionCriteres() != null && criteres.getDimensionCriteres().getMaxResults() != null) {
            criteria.setFirstResult(0);
            criteria.setMaxResults(criteres.getDimensionCriteres().getMaxResults());
        }

        criteria.addOrder(Order.asc("ordre"));
        criteria.addOrder(Order.asc("libelle"));
        return criteria.list();
    }
}
