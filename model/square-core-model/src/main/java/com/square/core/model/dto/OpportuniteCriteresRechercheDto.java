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
package com.square.core.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto contenant les critères de recherche pour la recherche d'opportunité.
 * @author cblanchard - SCUB
 */
public class OpportuniteCriteresRechercheDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -11348177170922345L;

    /** Identifiant de la personne physique. */
    private Long idPersonnePhysique;

    /** EID. */
    private String identifiantExterieur;

    /** Liste des identifiants. */
    private List<Long> listeIds;

    /** Identifiant du responsable. */
    private Long idResponsable;

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
     * Récupération de identifiantExterieur.
     * @return the identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Définition de identifiantExterieur.
     * @param identifiantExterieur the identifiantExterieur to set
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }

    /**
     * Récupère la valeur de listeIds.
     * @return la valeur de listeIds
     */
    public List<Long> getListeIds() {
        if (listeIds == null) {
            listeIds = new ArrayList<Long>();
        }
        return listeIds;
    }

    /**
     * Définit la valeur de listeIds.
     * @param listeIds la nouvelle valeur de listeIds
     */
    public void setListeIds(List<Long> listeIds) {
        this.listeIds = listeIds;
    }

    /**
     * Récupère la valeur de idResponsable.
     * @return la valeur de idResponsable
     */
    public Long getIdResponsable() {
        return idResponsable;
    }

    /**
     * Définit la valeur de idResponsable.
     * @param idResponsable la nouvelle valeur de idResponsable
     */
    public void setIdResponsable(Long idResponsable) {
        this.idResponsable = idResponsable;
    }

}
