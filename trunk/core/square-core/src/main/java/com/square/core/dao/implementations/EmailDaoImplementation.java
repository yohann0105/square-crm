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

import com.square.core.dao.interfaces.EmailDao;
import com.square.core.model.Email;
import com.square.core.model.util.CritereRechercheEmail;

/**
 * Implémentation du dao sur les emails.
 * @author Juanito Goncalves (juanito.goncalves@scub.net - SCUB
 */
public class EmailDaoImplementation extends HibernateDaoBaseImplementation implements EmailDao {

    @Override
    public Email rechercherEmailParId(Long id) {
        return load(id, Email.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Email> rechercherEmailParCritere(CritereRechercheEmail criteres) {
        final Query query = creerQuery("select email", criteres);
        return query.list();

    }

    /**
     * Former une requête pour la recherche d'email.
     * @param criteres les critères de recherche.
     * @return la requête.
     */
    private Query creerQuery(final String select, CritereRechercheEmail criteres) {
        final StringBuffer requete = new StringBuffer(select);
        requete.append(" from Email as email");
        requete.append(" inner join email.personnes as personne");
        requete.append(" where 1 = 1");
        final Map<String, Object> parameters = new HashMap<String, Object>();

        if (criteres.getIdsPersonnes() != null && !criteres.getIdsPersonnes().isEmpty()) {
            final String param = "idsPersonnes";
            requete.append(" and personne.id in (:" + param + ")");
            parameters.put(param, criteres.getIdsPersonnes());
        }
        if (criteres.getIdNature() != null) {
            requete.append(" and email.nature.id=:idNature");
            parameters.put("idNature", criteres.getIdNature());
        }
        if (criteres.getEmail() != null && !"".equals(criteres.getEmail())) {
            requete.append(" and email.adresse=:adresseMail");
            parameters.put("adresseMail", criteres.getEmail());
        }
        if (criteres.getIsSupprime() != null) {
            requete.append(" and email.supprime=:isSupprime");
            parameters.put("isSupprime", criteres.getIsSupprime());
        }
        if (!criteres.getIdsEmailsExclus().isEmpty()) {
            requete.append(" and email.id not in ( :idsEmailsExclus )");
            parameters.put("idsEmailsExclus", criteres.getIdsEmailsExclus());
        }

        final Query query = createQuery(requete.toString());
        query.setProperties(parameters);
        return query;
    }

    @Override
    public void supprimerEmail(Email email) {
        delete(email);
    }

}
