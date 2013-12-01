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
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.tarificateur.noyau.dao.interfaces.JourPaiementDao;
import com.square.tarificateur.noyau.model.paiement.JourPaiement;

/**
 * Implémentation de JourPaiementDao.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class JourPaiementDaoImpl extends HibernateDaoBaseImplementation implements JourPaiementDao {

    @Override
    public JourPaiement getJourPaiement(Long idJourPaiement) {
        return load(idJourPaiement, JourPaiement.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<JourPaiement> getListeJoursPaiement() {
        final Criteria crit = createCriteria(JourPaiement.class);
        crit.addOrder(Order.asc("ordre"));
        return (List<JourPaiement>) crit.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<JourPaiement> rechercherJoursPaiementParCriteres(IdentifiantLibelleDto criteres) {
        final Criteria crit = createCriteria(JourPaiement.class);
        if (criteres != null) {
            if (criteres.getIdentifiant() != null) {
                crit.add(Restrictions.eq("id", criteres.getIdentifiant()));
            }
            if (criteres.getLibelle() != null) {
                crit.add(Restrictions.ilike("libelle", criteres.getLibelle() + "%"));
            }
        }
        crit.addOrder(Order.asc("ordre"));
        return (List<JourPaiement>) crit.list();
    }

}
