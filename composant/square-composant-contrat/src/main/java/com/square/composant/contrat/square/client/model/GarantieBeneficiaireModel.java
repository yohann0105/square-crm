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
package com.square.composant.contrat.square.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant les informations du bénéficiaire d'une garantie.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class GarantieBeneficiaireModel implements IsSerializable {

    /**
     * Identifiant du bénéficiaire.
     */
    private Long idBenef;

    /** Identifiant du contrat. */
    private Long idContrat;

    /**
     * Le nom du bénéficiaire.
     */
    private String nom;

    /**
     * Le prénom du bénéficiaire.
     */
    private String prenom;

    /**
     * Le rôle du bénéficiaire.
     */
    private IdentifiantLibelleGwt role;

    /**
     * Getter function.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter function.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Getter function.
     * @return the role
     */
    public IdentifiantLibelleGwt getRole() {
        return role;
    }

    /**
     * Setter function.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter function.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Setter function.
     * @param role the role to set
     */
    public void setRole(IdentifiantLibelleGwt role) {
        this.role = role;
    }

    /**
     * Récupère la valeur de idBenef.
     * @return la valeur de idBenef
     */
    public Long getIdBenef() {
        return idBenef;
    }

    /**
     * Définit la valeur de idBenef.
     * @param idBenef la nouvelle valeur de idBenef
     */
    public void setIdBenef(Long idBenef) {
        this.idBenef = idBenef;
    }

    /**
     * Récupère la valeur de idContrat.
     * @return la valeur de idContrat
     */
    public Long getIdContrat() {
        return idContrat;
    }

    /**
     * Définit la valeur de idContrat.
     * @param idContrat la nouvelle valeur de idContrat
     */
    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
    }

}
