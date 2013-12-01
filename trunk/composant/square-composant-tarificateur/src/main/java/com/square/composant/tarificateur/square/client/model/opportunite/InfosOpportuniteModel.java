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
package com.square.composant.tarificateur.square.client.model.opportunite;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.tarificateur.square.client.model.personne.PersonneModel;

/**
 * Model Gwt encapsulant les informations nécessaires à la création/récupération d'une opportunité.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class InfosOpportuniteModel implements IsSerializable {

    /** Login de l'utilisateur connecté. */
    private String loginUtilisateurConnecte;

    /** Identifiant externe de l'opportunité (Square). */
    private Long eidOpportunite;

    /** Agence responsable de l'opportunité. */
    private Long eidAgence;

    /** Ressource responsable de l'opportunité. */
    private Long eidResponsable;

    /** Identifiant externe du créateur de l'opportunité. */
    private Long eidCreateur;

    /** Personne ciblée par l'opportunité. */
    private PersonneModel personne;

    /**
     * Getter function.
     * @return the eidOpportunite
     */
    public Long getEidOpportunite() {
        return eidOpportunite;
    }

    /**
     * Setter function.
     * @param eidOpportunite the eidOpportunite to set
     */
    public void setEidOpportunite(Long eidOpportunite) {
        this.eidOpportunite = eidOpportunite;
    }

    /**
     * Getter function.
     * @return the eidAgence
     */
    public Long getEidAgence() {
        return eidAgence;
    }

    /**
     * Getter function.
     * @return the eidResponsable
     */
    public Long getEidResponsable() {
        return eidResponsable;
    }

    /**
     * Getter function.
     * @return the eidCreateur
     */
    public Long getEidCreateur() {
        return eidCreateur;
    }

    /**
     * Getter function.
     * @return the personne
     */
    public PersonneModel getPersonne() {
        return personne;
    }

    /**
     * Setter function.
     * @param eidAgence the eidAgence to set
     */
    public void setEidAgence(Long eidAgence) {
        this.eidAgence = eidAgence;
    }

    /**
     * Setter function.
     * @param eidResponsable the eidResponsable to set
     */
    public void setEidResponsable(Long eidResponsable) {
        this.eidResponsable = eidResponsable;
    }

    /**
     * Setter function.
     * @param eidCreateur the eidCreateur to set
     */
    public void setEidCreateur(Long eidCreateur) {
        this.eidCreateur = eidCreateur;
    }

    /**
     * Setter function.
     * @param personne the personne to set
     */
    public void setPersonne(PersonneModel personne) {
        this.personne = personne;
    }

    /**
     * Get the loginUtilisateurConnecte value.
     * @return the loginUtilisateurConnecte
     */
    public String getLoginUtilisateurConnecte() {
        return loginUtilisateurConnecte;
    }

    /**
     * Set the loginUtilisateurConnecte value.
     * @param loginUtilisateurConnecte the loginUtilisateurConnecte to set
     */
    public void setLoginUtilisateurConnecte(String loginUtilisateurConnecte) {
        this.loginUtilisateurConnecte = loginUtilisateurConnecte;
    }

}
