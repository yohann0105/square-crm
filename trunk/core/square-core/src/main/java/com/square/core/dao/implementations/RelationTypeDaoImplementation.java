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

import com.square.core.dao.interfaces.RelationTypeDao;
import com.square.core.model.RelationType;
import com.square.core.model.dto.DimensionCritereRechercheTypeRelationDto;

/**
 * Implémentation du dao ddu type de relation.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class RelationTypeDaoImplementation extends HibernateDaoBaseImplementation implements RelationTypeDao {

    @Override
    public RelationType rechercherTypeRelationParId(Long identifiant) {
        return load(identifiant, RelationType.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RelationType> rechercherRelationTypeParCriteres(DimensionCritereRechercheTypeRelationDto criteres) {

        final Criteria criteria = createCriteria(RelationType.class);

        // Critère sur l'identifiant
        if (criteres.getId() != null) {
            criteria.add(Restrictions.eq("id", criteres.getId()));
        }
        // Critère sur la liste d'identifiants
        if (criteres.getListeIds() != null && criteres.getListeIds().size() > 0) {
            criteria.add(Restrictions.in("id", criteres.getListeIds()));
        }
        // Critère sur la visibilité
        if (criteres.getVisible() != null) {
            criteria.add(Restrictions.eq("visible", criteres.getVisible()));
        }
        // Critère sur le groupement
        if (criteres.getGroupements() != null && criteres.getGroupements().length > 0) {
            criteria.add(Restrictions.in("relationGroupement.id", criteres.getGroupements()));
        }
        // Critère sur le groupement
        if (criteres.getPasDansGroupements() != null && criteres.getPasDansGroupements().length > 0) {
            criteria.add(Restrictions.not(Restrictions.in("relationGroupement.id", criteres.getPasDansGroupements())));
        }
        // Critère sur le libelle ou libelle inverse
        if (criteres.getLibelle() != null && !("".equals(criteres.getLibelle()))) {
            criteria.add(Restrictions.or(Restrictions.ilike("libelle", criteres.getLibelle().toLowerCase() + "%"), Restrictions.ilike("inverse", criteres
                    .getLibelle().toLowerCase()
                + "%")));
        }
        // Ordonner les éléments
        criteria.addOrder(Order.asc("ordre"));
        criteria.addOrder(Order.asc("libelle"));
        return criteria.list();
    }

}
