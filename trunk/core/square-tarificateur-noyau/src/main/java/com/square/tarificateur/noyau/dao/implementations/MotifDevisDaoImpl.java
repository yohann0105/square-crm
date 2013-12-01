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

import com.square.tarificateur.noyau.dao.interfaces.MotifDevisDao;
import com.square.tarificateur.noyau.model.opportunite.MotifDevis;

/**
 * Implémentation de MotifDevisDao.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class MotifDevisDaoImpl extends HibernateDaoBaseImplementation implements MotifDevisDao {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<MotifDevis> getListeMotifsDevis() {
        final Criteria crit = createCriteria(MotifDevis.class);
        crit.addOrder(Order.asc("ordre"));
        return (List<MotifDevis>) crit.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MotifDevis getMotifDevisById(Long idMotifDevis) {
        return load(idMotifDevis, MotifDevis.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MotifDevis> getMotifsDevisByCriteres(String libelle) {
        final Criteria crit = createCriteria(MotifDevis.class);
        crit.add(Restrictions.ilike("libelle", libelle + "%"));
        crit.addOrder(Order.asc("ordre"));
        return (List<MotifDevis>) crit.list();
    }

}
