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
package com.square.adherent.noyau.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.OptionDao;
import com.square.adherent.noyau.model.data.espace.client.Option;

/**
 * Implémentation de l'accès aux données pour les options des adhérents.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class OptionDaoImpl extends HibernateDaoBaseImplementation implements OptionDao {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Option> getOptionsAdherent(Long uidPersonne) {
        final Criteria criteria = createCriteria(Option.class);
        criteria.add(Restrictions.eq("uidPersonne", uidPersonne));
        return (List<Option>) criteria.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option getOptionById(Long id) {
        return load(id, Option.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOption(Option option) {
        save(option);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPersonnePossedeOption(Long idPersonne, Long idTypeOption) {
        final Criteria crit = createCriteria(Option.class);
        crit.setProjection(Projections.rowCount());
        crit.add(Restrictions.eq("type.id", idTypeOption));
        crit.add(Restrictions.eq("uidPersonne", idPersonne));
        crit.add(Restrictions.eq("active", Boolean.TRUE));
        return Integer.valueOf(crit.uniqueResult().toString()) > 0;
    }

    @Override
    public Option getOptionByPersonneAndType(Long idPersonne, Long idTypeOption) {
        final Criteria crit = createCriteria(Option.class);
        crit.add(Restrictions.eq("type.id", idTypeOption));
        crit.add(Restrictions.eq("uidPersonne", idPersonne));
        return (Option) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Option> getListeOptionsSouscritesByPersonne(Long uidPersonne) {
        final Criteria crit = createCriteria(Option.class);
        crit.add(Restrictions.eq("uidPersonne", uidPersonne));
        crit.add(Restrictions.eq("active", Boolean.TRUE));
        return (ArrayList<Option>) crit.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> getListeAdherentsByIdOptionSouscrite(Long idOption) {
        final StringBuffer requete = new StringBuffer("SELECT o.uidPersonne ");
        requete.append("FROM Option o, EspaceClientInternet c ");
        requete.append("WHERE o.uidPersonne = c.uidPersonne ");
        requete.append("AND o.type.id = :idOption ");
        requete.append("AND o.active = true ");
        requete.append("AND c.active = 'O' ");
        requete.append("ORDER BY o.uidPersonne ");
        final Query query = createQuery(requete.toString());
        query.setLong("idOption", idOption);
        return (ArrayList<Long>) query.list();
    }

}
