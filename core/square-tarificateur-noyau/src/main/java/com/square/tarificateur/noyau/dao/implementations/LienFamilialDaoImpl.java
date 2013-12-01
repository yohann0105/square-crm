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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.tarificateur.noyau.dao.interfaces.LienFamilialDao;
import com.square.tarificateur.noyau.dto.personne.DimensionCriteresLienFamilialRechercheDto;
import com.square.tarificateur.noyau.model.personne.LienFamilial;

/**
 * Implémentation de LienFamilialDao.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class LienFamilialDaoImpl extends HibernateDaoBaseImplementation implements LienFamilialDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public LienFamilial getLienFamilial(Long idLienFamilial) {
        return load(idLienFamilial, LienFamilial.class);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<LienFamilial> rechercherLiensFamiliauxParCriteres(DimensionCriteresLienFamilialRechercheDto criteres) {

        final Criteria criteria = createCriteria(LienFamilial.class);

        // Critère sur le libelle
        if (criteres.getLibelle() != null && !criteres.getLibelle().equals("")) {
            criteria.add(Restrictions.ilike("libelle", criteres.getLibelle().toLowerCase() + "%"));
        }
        // les types autorisés
        if (criteres.getListeIds() != null && criteres.getListeIds().size() > 0) {
            criteria.add(Restrictions.in("id", criteres.getListeIds()));
        }
        // Ordonner les éléments
        criteria.addOrder(Order.asc("libelle"));

        return criteria.list();
    }
}
