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
package com.square.logs.core.dao.implementations;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.logs.core.dao.interfaces.LogDao;
import com.square.logs.core.model.Log;

/**
 * implémentation de dao de log.
 * @author Manuel Godbert (manuel.godbert@scub.net) - SCUB
 *
 */
public class LogDaoImplementation extends HibernateDaoBaseImplementation implements LogDao {

    @Override
    public void creer(Log log) {
        save(log);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Long> getIdsLogsBeforeDate(Calendar date, int pagination) {
        final String requete = "SELECT id FROM Log WHERE date <= :date ORDER BY date ASC";
        final Query query = createQuery(requete);
        query.setCalendar("date", date);
        query.setFirstResult(0);
        query.setMaxResults(pagination);
        return (List<Long>) query.list();
    }

    @Override
    public int getNbLogsBeforeDate(Calendar date) {
        final String requete = "SELECT count(id) FROM Log WHERE date <= :date";
        final Query query = createQuery(requete);
        query.setCalendar("date", date);
        final Object result = query.uniqueResult();
        if (result instanceof BigInteger) {
            return ((BigInteger) result).intValue();
        } else {
            return ((Long) result).intValue();
        }
    }

    @Override
    public int supprimerLogsByIds(List<Long> idsASupprimer) {
        // suppression des arguments
        String requete = "DELETE FROM data_argument where log_id in (:idsASupprimer)";
        Query query = createSqlQuery(requete);
        query.setParameterList("idsASupprimer", idsASupprimer);
        query.executeUpdate();
        // suppression des logs
        requete = "DELETE FROM data_log where id in (:idsASupprimer)";
        query = createSqlQuery(requete);
        query.setParameterList("idsASupprimer", idsASupprimer);
        return query.executeUpdate();
    }
}
