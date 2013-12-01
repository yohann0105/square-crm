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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.CaisseDao;
import com.square.core.model.Caisse;
import com.square.core.model.dto.DimensionCriteresRechercheCaisseDto;

/**
 * Implémentation du dao pour les caisses.
 * @author cblanchard - SCUB
 */
public class CaisseDaoImplementation extends HibernateDaoBaseImplementation implements CaisseDao {

    @Override
    public Caisse rechercheCaisseParId(Long identifiant) {
        return load(identifiant, Caisse.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Caisse> rechercherCaisseParCriteres(DimensionCriteresRechercheCaisseDto criteresRecherche) {
        final StringBuffer requete = new StringBuffer("SELECT c FROM Caisse c ");
        if (criteresRecherche.getIdDepartement() != null) {
            // On n'effectue la jointure que si le critère sur le département est spécifié
            // afin de ne pas retourner plusieurs fois la même caisse associée à plusieurs départements
            requete.append(" LEFT JOIN c.departements d ");
        }
        requete.append(" WHERE 1=1 ");

        // Critère sur l'identifiant
        if (criteresRecherche.getDimensionCriteres() != null) {
            if (criteresRecherche.getDimensionCriteres().getId() != null) {
                requete.append("AND c.id = :id ");
            }
            // Critère sur le libelle
            if (!StringUtils.isBlank(criteresRecherche.getDimensionCriteres().getLibelle())) {
                requete.append("AND (lower(c.nom) like lower(:libelle) ");
                requete.append("OR lower(c.code) like lower(:libelle) ");
                requete.append("OR lower(c.code||'.'||c.centre||'.'||c.nom) like lower(:libelle)) ");
            }
            // Critère sur la visibilité
            if (criteresRecherche.getDimensionCriteres().getVisible() != null) {
                requete.append("AND c.visible = :visible ");
            }
        }
        // Critere sur le régime
        if (criteresRecherche.getIdRegime() != null) {
            requete.append("AND c.regime.id = :idRegime ");
        }
        // Critere sur le departement
        if (criteresRecherche.getIdDepartement() != null) {
            requete.append("AND d.id = :idDepartement ");
        }
        if (criteresRecherche.getCode() != null) {
            requete.append("AND c.code = :code ");
        }
        if (criteresRecherche.getCentre() != null) {
            requete.append("AND c.centre = :centre ");
        }
        requete.append("ORDER BY c.ordre, c.code, c.centre, c.nom ");

        final Query query = createQuery(requete.toString());
        if (criteresRecherche.getDimensionCriteres() != null) {
            if (criteresRecherche.getDimensionCriteres().getId() != null) {
                query.setLong("id", criteresRecherche.getDimensionCriteres().getId());
            }
            // Critère sur le libelle
            if (criteresRecherche.getDimensionCriteres().getLibelle() != null && !criteresRecherche.getDimensionCriteres().getLibelle().equals("")) {
                query.setString("libelle", criteresRecherche.getDimensionCriteres().getLibelle() + "%");
            }
            // Critère sur la visibilité
            if (criteresRecherche.getDimensionCriteres().getVisible() != null) {
                query.setBoolean("visible", criteresRecherche.getDimensionCriteres().getVisible());
            }
            // Maxresults
            if (criteresRecherche.getDimensionCriteres().getMaxResults() != null) {
                query.setFirstResult(0);
                query.setMaxResults(criteresRecherche.getDimensionCriteres().getMaxResults());
            }
        }
        // Critere sur le régime
        if (criteresRecherche.getIdRegime() != null) {
            query.setLong("idRegime", criteresRecherche.getIdRegime());
        }
        // Critere sur le departement
        if (criteresRecherche.getIdDepartement() != null) {
            query.setLong("idDepartement", criteresRecherche.getIdDepartement());
        }
        if (criteresRecherche.getCode() != null) {
            query.setString("code", criteresRecherche.getCode());
        }
        if (criteresRecherche.getCentre() != null) {
            query.setString("centre", criteresRecherche.getCentre());
        }

        return query.list();
    }

}
