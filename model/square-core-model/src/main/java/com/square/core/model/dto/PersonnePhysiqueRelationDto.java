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
 * Représentation de la personne physique. TODO version temporaire
 */
public class PersonnePhysiqueRelationDto extends PersonneRelationDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3594606209708216832L;

    /**
     * Numero de la personne.
     */
    private String num;

    /**
     * Civilité de la personne physique.
     */
    private IdentifiantLibelleDto civilite;

    /**
     * Nom de la personne physique.
     */
    private String nom;

    /**
     * Prénom de la personne physique.
     */
    private String prenom;

    /**
     * Date de naissance de la personne physique.
     */
    private Calendar dateNaissance;

    /**
     * Age de la personne.
     */
    private int age;

    /**
     * Date de décès de la personne.
     */
    private Calendar dateDeces;

    /**
     * Permet de savoir si la personne est décédée.
     */
    private boolean deces;

    /**
     * Numero de sécurité social.
     */
    private String numSS;

    /**
     * Clé du numéro de sécurité social.
     */
    private String cleNumSS;

    /**
     * Nature de la personne.
     */
    private IdentifiantLibelleDto naturePersonne;

    /** Régime de la personne. */
    private IdentifiantLibelleDto regime;

    /** Caisse de la personne. */
    private CaisseSimpleDto caisse;

    /**
     * Retourne la valeur de civilite.
     * @return the civilite
     */
    public IdentifiantLibelleDto getCivilite() {
        return civilite;
    }

    /**
     * Modifie la valeur de civilite.
     * @param civilite the civilite to set
     */
    public void setCivilite(IdentifiantLibelleDto civilite) {
        this.civilite = civilite;
    }

    /**
     * Renvoi la valeur de nom.
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoi la valeur de prenom.
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie prenom.
     * @param prenom la nouvelle valeur de prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Renvoi la valeur de dateNaissance.
     * @return dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Renvoi la valeur de age.
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Modifie age.
     * @param age la nouvelle valeur de age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Renvoi la valeur de numSS.
     * @return numSS
     */
    public String getNumSS() {
        return numSS;
    }

    /**
     * Modifie numSS.
     * @param numSS la nouvelle valeur de numSS
     */
    public void setNumSS(String numSS) {
        this.numSS = numSS;
    }

    /**
     * Renvoi la valeur de cleNumSS.
     * @return cleNumSS
     */
    public String getCleNumSS() {
        return cleNumSS;
    }

    /**
     * Modifie cleNumSS.
     * @param cleNumSS la nouvelle valeur de cleNumSS
     */
    public void setCleNumSS(String cleNumSS) {
        this.cleNumSS = cleNumSS;
    }

    /**
     * Get num.
     * @return the num
     */
    public String getNum() {
        return num;
    }

    /**
     * Set num.
     * @param num the num to set
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * Get naturePersonne.
     * @return the naturePersonne
     */
    public IdentifiantLibelleDto getNaturePersonne() {
        return naturePersonne;
    }

    /**
     * Set naturePersonne.
     * @param naturePersonne the naturePersonne to set
     */
    public void setNaturePersonne(IdentifiantLibelleDto naturePersonne) {
        this.naturePersonne = naturePersonne;
    }

    /**
     * Getter function.
     * @return the dateDeces
     */
    public Calendar getDateDeces() {
        return dateDeces;
    }

    /**
     * Getter function.
     * @return the deces
     */
    public boolean isDeces() {
        return deces;
    }

    /**
     * Setter function.
     * @param dateDeces the dateDeces to set
     */
    public void setDateDeces(Calendar dateDeces) {
        this.dateDeces = dateDeces;
    }

    /**
     * Setter function.
     * @param deces the deces to set
     */
    public void setDeces(boolean deces) {
        this.deces = deces;
    }

    /**
     * Récupère la valeur de regime.
     * @return la valeur de regime
     */
    public IdentifiantLibelleDto getRegime() {
        return regime;
    }

    /**
     * Définit la valeur de regime.
     * @param regime la nouvelle valeur de regime
     */
    public void setRegime(IdentifiantLibelleDto regime) {
        this.regime = regime;
    }

    /**
     * Récupère la valeur de caisse.
     * @return la valeur de caisse
     */
    public CaisseSimpleDto getCaisse() {
        return caisse;
    }

    /**
     * Définit la valeur de caisse.
     * @param caisse la nouvelle valeur de caisse
     */
    public void setCaisse(CaisseSimpleDto caisse) {
        this.caisse = caisse;
    }
}
