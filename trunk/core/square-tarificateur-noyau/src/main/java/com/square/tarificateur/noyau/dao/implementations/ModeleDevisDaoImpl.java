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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.tarificateur.noyau.dao.interfaces.ModeleDevisDao;
import com.square.tarificateur.noyau.dto.devis.CritereModeleDevisDto;
import com.square.tarificateur.noyau.model.opportunite.ModeleDevis;

/**
 * Implémentation de la couche d'accès aux données pour les modèles de devis.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class ModeleDevisDaoImpl extends HibernateDaoBaseImplementation implements ModeleDevisDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public ModeleDevis getModeleDevisById(Long idModeleDevis) {
        return load(idModeleDevis, ModeleDevis.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ModeleDevis> getListeModelesDevisByCriteres(CritereModeleDevisDto criteres) {
        final Criteria crit = createCriteria(ModeleDevis.class);
        if (criteres != null) {
            if (criteres.getListeIdsModeles() != null && criteres.getListeIdsModeles().size() > 0) {
                crit.add(Restrictions.in("id", criteres.getListeIdsModeles()));
            }
        }
        crit.addOrder(Order.asc("ordre"));
        return (ArrayList<ModeleDevis>) crit.list();
    }

}
