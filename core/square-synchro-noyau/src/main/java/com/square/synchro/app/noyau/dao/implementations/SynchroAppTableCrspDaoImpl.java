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
package com.square.synchro.app.noyau.dao.implementations;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.synchro.app.noyau.dao.interfaces.SynchroAppTableCrspDao;
import com.square.synchro.app.noyau.modele.SynchroAppTableCrsp;

/**
 * Implemantation dao Gestion de la table de correspondance.
 * @author sgoumard (Scub). - SCUB
 */
public class SynchroAppTableCrspDaoImpl extends HibernateDaoBaseImplementation implements SynchroAppTableCrspDao {

    @Override
    public String rechercherCrsp(String identifiantObjet, String identifiantApp, String identifiantAppCrsp, String typeObjet) {

        final Criteria criteria = createCriteria(SynchroAppTableCrsp.class);
        if (identifiantObjet != null) {
            final Disjunction conjunction = Restrictions.disjunction();
            conjunction.add(Restrictions.eq("identifiantObjet", identifiantObjet));
            conjunction.add(Restrictions.eq("identifiantObjetCrsp", identifiantObjet));
            criteria.add(conjunction);
        }
        if (identifiantApp != null) {
            final Disjunction conjunction = Restrictions.disjunction();
            conjunction.add(Restrictions.eq("identifiantApp", identifiantApp));
            conjunction.add(Restrictions.eq("identifiantAppCrsp", identifiantApp));
            criteria.add(conjunction);
        }
        if (identifiantAppCrsp != null) {
            final Disjunction conjunction = Restrictions.disjunction();
            conjunction.add(Restrictions.eq("identifiantApp", identifiantAppCrsp));
            conjunction.add(Restrictions.eq("identifiantAppCrsp", identifiantAppCrsp));
            criteria.add(conjunction);
        }
        if (typeObjet != null) {
            criteria.add(Restrictions.eq("typeObjet", typeObjet));
        }
        final SynchroAppTableCrsp result = (SynchroAppTableCrsp) criteria.uniqueResult();
        if (result != null) {
            return result.getIdentifiantObjet().equals(identifiantObjet) ? result.getIdentifiantObjetCrsp() : result.getIdentifiantObjet();
        }
        return null;
    }

    @Override
    public void save(SynchroAppTableCrsp crsp) {
        super.save(crsp);
    }
}
