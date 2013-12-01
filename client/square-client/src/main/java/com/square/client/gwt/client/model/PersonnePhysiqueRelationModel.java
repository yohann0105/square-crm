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

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model des relations pour les personnes physiques.
 * @author cblanchard TODO version temporaire - SCUB
 */
public class PersonnePhysiqueRelationModel extends PersonneRelationModel implements IsSerializable {

    /** Civilité de la personne physique. */
    private IdentifiantLibelleGwt civilite;

    /** Numero de la personne. */
    private String num;

    /** Nom de la personne physique. */
    private String nom;

    /** Prénom de la personne physique. */
    private String prenom;

    /** Date de naissance de la personne physique. */
    private String dateNaissance;

    /** Age de la personne. */
    private int age;

    /** Date de décès de la personne. */
    private String dateDeces;

    /** Permet de savoir si la personne est décédée. */
    private boolean deces;

    /** Numéro de sécurité social de la personne. */
    private String numSS;

    /** Clé du numéro de sécurité social. */
    private String cleNumSS;

    /** Nature de la personne. */
    private IdentifiantLibelleGwt naturePersonne;

    /** Régime de la personne. */
    private IdentifiantLibelleGwt regime;

    /** Caisse de la personne. */
    private CaisseSimpleModel caisse;

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
     * Renvoi la valeur de civilite.
     * @return civilite
     */
    public IdentifiantLibelleGwt getCivilite() {
        return civilite;
    }

    /**
     * Modifie civilite.
     * @param civilite la nouvelle valeur de civilite
     */
    public void setCivilite(IdentifiantLibelleGwt civilite) {
        this.civilite = civilite;
    }

    /**
     * Renvoi la valeur de dateNaissance.
     * @return dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(String dateNaissance) {
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
    public IdentifiantLibelleGwt getNaturePersonne() {
        return naturePersonne;
    }

    /**
     * Set naturePersonne.
     * @param naturePersonne the naturePersonne to set
     */
    public void setNaturePersonne(IdentifiantLibelleGwt naturePersonne) {
        this.naturePersonne = naturePersonne;
    }

    /**
     * Getter function.
     * @return the dateDeces
     */
    public String getDateDeces() {
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
    public void setDateDeces(String dateDeces) {
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
    public IdentifiantLibelleGwt getRegime() {
        return regime;
    }

    /**
     * Définit la valeur de regime.
     * @param regime la nouvelle valeur de regime
     */
    public void setRegime(IdentifiantLibelleGwt regime) {
        this.regime = regime;
    }

    /**
     * Récupère la valeur de caisse.
     * @return la valeur de caisse
     */
    public CaisseSimpleModel getCaisse() {
        return caisse;
    }

    /**
     * Définit la valeur de caisse.
     * @param caisse la nouvelle valeur de caisse
     */
    public void setCaisse(CaisseSimpleModel caisse) {
        this.caisse = caisse;
    }

}
