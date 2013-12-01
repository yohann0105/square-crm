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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.DepartementDao;
import com.square.core.model.Commune;
import com.square.core.model.Departement;
import com.square.core.model.dto.DimensionCritereRechercheDepartementDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;

/**
 * Implémentation du dao sur les départements.
 * @author cblanchard - SCUB
 */
public class DepartementDaoImplementation extends HibernateDaoBaseImplementation implements DepartementDao {

    @Override
    public Departement rechercherDepartementParId(Long identifiant) {
        return load(identifiant, Departement.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Departement> rechercherDepartementParCriteres(DimensionCritereRechercheDepartementDto criteres) {

        if (criteres.getDimensionCriteres() == null) {
            criteres.setDimensionCriteres(new DimensionCriteresRechercheDto());
        }
        final Criteria criteria = createCriteria(Departement.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

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
        Criteria commune = null;
        // Critère sur la commune du departement.
        if (criteres.getIdCommune() != null) {
        	commune = criteria.createAlias("communes", "commune");
        	commune.add(Restrictions.eq("commune.id", criteres.getIdCommune()));
        }
        if (criteres.getIdCodePostal() != null)
        {
        	if (commune == null)
        	{
        		commune = criteria.createAlias("communes", "commune");
        	}
        	criteria.createAlias("commune.codesPostaux", "codes").add(Restrictions.eq("codes.codePostal.id",
        			criteres.getIdCodePostal()));
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
    public Departement rechercherDepartementParIdCommune(Long idCommune) {
        final Criteria criteria = createCriteria(Commune.class);
        criteria.add(Restrictions.eq("id", idCommune));
        criteria.setProjection(Projections.property("departement"));
        return (Departement) criteria.uniqueResult();
    }

}
