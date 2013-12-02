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
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Un objet contenant le bénéficiaire.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class BeneficiaireDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -2935847099996096187L;

    /**
     * L'identifiant du bénéficiaire.
     */
    private Long identifiant;

    /**
     * Civilité du bénéficiaire.
     */
    private IdentifiantLibelleDto civilite;

    /**
     * Prénom du bénéficiaire.
     */
    private String prenom;

    /**
     * Nom du bénéficiaire.
     */
    private String nom;

    /**
     * Date de naissance du bénéficiaire.
     */
    private Calendar dateNaissance;

    /**
     * Type de relation.
     */
    private IdentifiantLibelleDto typeRelation;

    /**
     * Index du bénéficiaire.
     */
    private int index;

    /**
     * Profession de la personne.
     */
    private IdentifiantLibelleDto profession;

    /**
     * Le regime de la personne.
     */
    private IdentifiantLibelleDto caisseRegime;

    /**
     * Indique si l'enfant doit être rattaché aux 2 parents.
     */
    private Boolean rattacherAuxParents;

    /**
     * Récupération de l'identifiant.
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Définition de l'identifiant.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Récupération de la civilité.
     * @return the civilite
     */
    public IdentifiantLibelleDto getCivilite() {
        return civilite;
    }

    /**
     * Définition de la civilité.
     * @param civilite the civilite to set
     */
    public void setCivilite(IdentifiantLibelleDto civilite) {
        this.civilite = civilite;
    }

    /**
     * Récupéaration du prénom.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définition du prénom.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupération du nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définition du nom.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupération de la date de naissance.
     * @return the dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définition de la date de naissance.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Récupération du type de relation.
     * @return the typeRelation
     */
    public IdentifiantLibelleDto getTypeRelation() {
        return typeRelation;
    }

    /**
     * Définition du type de relation.
     * @param typeRelation the typeRelation to set
     */
    public void setTypeRelation(IdentifiantLibelleDto typeRelation) {
        this.typeRelation = typeRelation;
    }

    /**
     * Getter function.
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Setter function.
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Get the profession value.
     * @return the profession
     */
    public IdentifiantLibelleDto getProfession() {
        return profession;
    }

    /**
     * Set the profession value.
     * @param profession the profession to set
     */
    public void setProfession(IdentifiantLibelleDto profession) {
        this.profession = profession;
    }

    /**
     * Get the caisseRegime value.
     * @return the caisseRegime
     */
    public IdentifiantLibelleDto getCaisseRegime() {
        return caisseRegime;
    }

    /**
     * Set the caisseRegime value.
     * @param caisseRegime the caisseRegime to set
     */
    public void setCaisseRegime(IdentifiantLibelleDto caisseRegime) {
        this.caisseRegime = caisseRegime;
    }

    /**
     * Get the rattacherAuxParents value.
     * @return the rattacherAuxParents
     */
    public Boolean getRattacherAuxParents() {
        return rattacherAuxParents;
    }

    /**
     * Set the rattacherAuxParents value.
     * @param rattacherAuxParents the rattacherAuxParents to set
     */
    public void setRattacherAuxParents(Boolean rattacherAuxParents) {
        this.rattacherAuxParents = rattacherAuxParents;
    }
}
