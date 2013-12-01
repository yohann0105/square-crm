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

import com.square.core.dao.interfaces.AdresseDao;
import com.square.core.model.Adresse;
import com.square.core.model.dto.AdresseCriteresRechercheDto;

/**
 * Implémentation du dao sur les adresses.
 * @author Juanito Goncalves (juanito.goncalves@scub.net - SCUB
 */
public class AdresseDaoImplementation extends HibernateDaoBaseImplementation implements AdresseDao {

    @Override
    public Adresse rechercherAdresseParId(Long id) {
        return load(id, Adresse.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> rechercherIdAdressesParCriteres(AdresseCriteresRechercheDto criteres) {
        final Query query = creerQuery("select adresse.id", criteres);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Adresse> rechercherAdresseParCritere(AdresseCriteresRechercheDto criteres) {
        final Query query = creerQuery("select adresse", criteres);
        return query.list();
    }

    /**
     * Former une requête pour la recherche d'adresse.
     * @param select la projection.
     * @param criteres les criteres de la recherche.
     * @return la requête.
     */
    private Query creerQuery(final String select, final AdresseCriteresRechercheDto criteres) {

        final StringBuffer requete = new StringBuffer(select);
        requete.append(" from Personne personne inner join personne.adresses adresse where 1 = 1");
        final Map<String, Object> parameters = new HashMap<String, Object>();
        if (criteres.getDateDebut() != null) {
            requete.append(" and adresse.dateDebut = :dateDebut");
            parameters.put("dateDebut", criteres.getDateDebut());
        }

        if (criteres.getIdPersonne() != null) {
            requete.append(" and personne.id=:idPersonne");
            parameters.put("idPersonne", criteres.getIdPersonne());
        }

        if (criteres.getIdNature() != null) {
            requete.append(" and adresse.nature.id=:idNature");
            parameters.put("idNature", criteres.getIdNature());
        }

        if (criteres.getActive() != null) {
            requete.append(" and adresse.dateFin ");
            if (criteres.getActive()) {
                requete.append("is null");
            } else {
                requete.append("is not null");
            }
        }

        requete.append(" and adresse.supprime = false");

        // Tri sur la nature de l'adresse puis sur la date (adresse en cours en premier : date de fin à null)
        requete.append(" order by adresse.nature.ordre, adresse.dateFin desc, adresse.dateDebut desc");

        final Query query = createQuery(requete.toString());
        query.setProperties(parameters);
        return query;
    }

    @Override
    public void ajouterAdresse(Adresse adresse) {
        save(adresse);
    }

}
