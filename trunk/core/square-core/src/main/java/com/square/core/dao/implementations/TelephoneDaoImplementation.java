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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.TelephoneDao;
import com.square.core.model.Telephone;
import com.square.core.model.util.CritereRechercheTelephone;

/**
 * Implémentation du dao sur les téléphones.
 * @author Juanito Goncalves (juanito.goncalves@scub.net - SCUB
 */
public class TelephoneDaoImplementation extends HibernateDaoBaseImplementation implements TelephoneDao {

    @Override
    public Telephone rechercherTelephoneParId(Long id) {
        return load(id, Telephone.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Telephone> rechercherTelephoneParCritere(CritereRechercheTelephone criteres) {
        final Query query = creerQuery("select telephone", criteres);
        return query.list();
    }

    /**
     * Former une requête pour la recherche de téléphone.
     * @param select la projection.
     * @param criteres les criteres de la recherche.
     * @return la requête.
     */
    private Query creerQuery(final String select, CritereRechercheTelephone criteres) {
        final StringBuffer requete = new StringBuffer(select);
        requete.append(" from Telephone as telephone");
        requete.append(" inner join telephone.personnes as personne");
        requete.append(" where 1 = 1");
        final Map<String, Object> parameters = new HashMap<String, Object>();

        if (criteres.getIdsPersonnes() != null && !criteres.getIdsPersonnes().isEmpty()) {
            final String param = "idsPersonnes";
            requete.append(" and personne.id in (:" + param + ")");
            parameters.put(param, criteres.getIdsPersonnes());
        }

        if (criteres.getIdNature() != null) {
            requete.append(" and telephone.natureTelephone.id=:idNature");
            parameters.put("idNature", criteres.getIdNature());
        }

        if (criteres.getNumeroTelephone() != null && !"".equals(criteres.getNumeroTelephone())) {
            requete.append(" and telephone.numTelephone=:numTel");
            parameters.put("numTel", criteres.getNumeroTelephone());
        }

        if (criteres.getIsSupprime() != null) {
            requete.append(" and telephone.supprime=:isSupprime");
            parameters.put("isSupprime", criteres.getIsSupprime());
        }
        if (!criteres.getIdsTelExclus().isEmpty()) {
            requete.append(" and telephone.id not in (:idsTelExclus)");
            parameters.put("idsTelExclus", criteres.getIdsTelExclus());
        }

        final Query query = createQuery(requete.toString());
        query.setProperties(parameters);
        return query;
    }

    @Override
    public void supprimerTelephone(Telephone telephone) {
        delete(telephone);
    }
}
