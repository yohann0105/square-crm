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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.ActionAffectationDao;
import com.square.core.model.ActionAffectation;

/**
 * Implémentation du dao sur les affectations.
 * @author cblanchard - SCUB
 */
public class ActionAffectationDaoImplementation extends HibernateDaoBaseImplementation implements ActionAffectationDao {

    @Override
    public void creerActionAffectation(ActionAffectation actionAffectation) {
        save(actionAffectation);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ActionAffectation> rechercherActionAffectationPersonne(Long idPersonne) {
        final Criteria crit = createCriteria(ActionAffectation.class);
        crit.add(Restrictions.eq("personne.id", idPersonne));
        return (ArrayList<ActionAffectation>) crit.list();
    }

}
