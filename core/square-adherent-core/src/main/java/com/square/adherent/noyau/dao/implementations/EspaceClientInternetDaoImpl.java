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

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.EspaceClientInternetDao;
import com.square.adherent.noyau.dto.adherent.connexion.IdentifiantsConnexionDto;
import com.square.adherent.noyau.model.data.espace.client.EspaceClientInternet;

/**
 * Implémentation de l'accès aux données à l'espace client.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class EspaceClientInternetDaoImpl extends HibernateDaoBaseImplementation implements EspaceClientInternetDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveEspaceClientInternet(EspaceClientInternet espaceClientInternet) {
        save(espaceClientInternet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EspaceClientInternet getEspaceClientInternetClient(Long uidPersonne) {
        final Criteria criteria = createCriteria(EspaceClientInternet.class);
        criteria.add(Restrictions.eq("uidPersonne", uidPersonne));
        return (EspaceClientInternet) criteria.uniqueResult();
    }

    @Override
    public EspaceClientInternet getEspaceClientInternetByPersonneAndNature(Long idPersonne, Long idNature) {
        final Criteria criteria = createCriteria(EspaceClientInternet.class);
        criteria.add(Restrictions.eq("uidPersonne", idPersonne));
        criteria.add(Restrictions.eq("nature.id", idNature));
        return (EspaceClientInternet) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EspaceClientInternet> getListeEspaceClientInternetsByIdentifiantsAndNature(IdentifiantsConnexionDto identifiants, Long idNature,
        Boolean statutEspaceClientInternet) {
        final Criteria crit = createCriteria(EspaceClientInternet.class);
        // Identifiants
        if (identifiants != null && StringUtils.isNotBlank(identifiants.getLogin())) {
            crit.add(Restrictions.eq("login", identifiants.getLogin().trim()));
        }
        // Nature
        if (idNature != null) {
            crit.add(Restrictions.eq("nature.id", idNature));
        }
        if (statutEspaceClientInternet != null) {
            // On filtre les connexions activées / désactivées
            crit.add(Restrictions.eq("active", statutEspaceClientInternet));
        }
        return (ArrayList<EspaceClientInternet>) crit.list();
    }

    @Override
    public void deleteEspaceClientInternet(EspaceClientInternet espaceClientInternet) {
        delete(espaceClientInternet);
    }

}
