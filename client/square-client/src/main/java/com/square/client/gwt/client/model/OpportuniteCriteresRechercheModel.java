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
package com.square.client.gwt.client.model;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model contenant les critères de recherche pour la recherche d'opportunité.
 * @author cblanchard - SCUB
 */
public class OpportuniteCriteresRechercheModel implements IsSerializable {

    /** Identifiant de l'opportunité. */
    private List<Long> listeIds;

    /** Identifiant externe de l'opportunité. */
    private String identifiantExterieur;

    /** Identifiant de la personne physique. */
    private Long idPersonnePhysique;

    /**
     * Renvoi la valeur de idPersonnePhysique.
     * @return idPersonnePhysique
     */
    public Long getIdPersonnePhysique() {
        return idPersonnePhysique;
    }

    /**
     * Modifie idPersonnePhysique.
     * @param idPersonnePhysique la nouvelle valeur de idPersonnePhysique
     */
    public void setIdPersonnePhysique(Long idPersonnePhysique) {
        this.idPersonnePhysique = idPersonnePhysique;
    }

    /**
     * Getter function.
     * @return the identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Setter function.
     * @param identifiantExterieur the identifiantExterieur to set
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }

    /**
     * Récupération de listeIds.
     * @return the listeIds
     */
    public List<Long> getListeIds() {
        return listeIds;
    }

    /**
     * Définition de listeIds.
     * @param listeIds the listeIds to set
     */
    public void setListeIds(List<Long> listeIds) {
        this.listeIds = listeIds;
    }

}
