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
package com.square.adherent.noyau.dao.implementations.prestation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.prestation.DecompteActeDao;
import com.square.adherent.noyau.dto.DimensionAdherentCriteresRechercheDto;
import com.square.adherent.noyau.model.dimension.prestation.DecompteActe;

/**
 * Implementation du dao DecompteActe.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class DecompteActeDaoImpl extends HibernateDaoBaseImplementation implements DecompteActeDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<DecompteActe> rechercherActesDecompteParCriteres(DimensionAdherentCriteresRechercheDto criteres) {
        final Criteria criteria = createCriteria(DecompteActe.class);
        if (criteres != null) {
            if (criteres.getId() != null) {
                criteria.add(Restrictions.eq("id", criteres.getId()));
            }
            if (criteres.getIdentifiantExterieur() != null) {
                criteria.add(Restrictions.eq("eid", criteres.getIdentifiantExterieur()));
            }
            if (criteres.getLibelle() != null) {
                criteria.add(Restrictions.ilike("libelle", criteres.getLibelle() + "%"));
            }
            if (criteres.getVisible() != null) {
                criteria.add(Restrictions.eq("visible", criteres.getVisible()));
            }
            if (criteres.getMaxResults() != null) {
                criteria.setMaxResults(criteres.getMaxResults());
            }
        }
        criteria.addOrder(Order.asc("ordre")).addOrder(Order.asc("libelle")).addOrder(Order.asc("eid"));
        return (List<DecompteActe>) criteria.list();
    }
}
