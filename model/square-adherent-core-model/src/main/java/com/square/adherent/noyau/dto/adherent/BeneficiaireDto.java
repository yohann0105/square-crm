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
package com.square.adherent.noyau.dto.adherent;

import java.io.Serializable;
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO contenant les informations d'un bénéficiaire d'adhérent.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class BeneficiaireDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -8912057170100840613L;

    /**
     * L'identifiant du bénéficiaire.
     */
    private Long id;

    /**
     * Civilité du bénéficiaire.
     */
    private IdentifiantLibelleDto civilite;

    /**
     * Nom du bénéficiaire.
     */
    private String nom;

    /**
     * Prénom du bénéficiaire.
     */
    private String prenom;

    /**
     * Date de naissance du bénéficiaire.
     */
    private Calendar dateNaissance;

    /**
     * Le type de relation entre l'adhérent et le bénéficiaire.
     */
    private IdentifiantLibelleDto typeRelation;

    /**
     * Identifiant de la ressource Square qui créé le bénéficiaire.
     */
    private Long idCreateur;

    /**
     * Getter function.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter function.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter function.
     * @return the civilite
     */
    public IdentifiantLibelleDto getCivilite() {
        return civilite;
    }

    /**
     * Setter function.
     * @param civilite the civilite to set
     */
    public void setCivilite(IdentifiantLibelleDto civilite) {
        this.civilite = civilite;
    }

    /**
     * Getter function.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter function.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter function.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter function.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter function.
     * @return the dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Setter function.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Getter function.
     * @return the typeRelation
     */
    public IdentifiantLibelleDto getTypeRelation() {
        return typeRelation;
    }

    /**
     * Setter function.
     * @param typeRelation the typeRelation to set
     */
    public void setTypeRelation(IdentifiantLibelleDto typeRelation) {
        this.typeRelation = typeRelation;
    }

    /**
     * Getter function.
     * @return the idCreateur
     */
    public Long getIdCreateur() {
        return idCreateur;
    }

    /**
     * Setter function.
     * @param idCreateur the idCreateur to set
     */
    public void setIdCreateur(Long idCreateur) {
        this.idCreateur = idCreateur;
    }

}
