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
package com.square.composant.tarificateur.square.client.model.selecteur;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model representant un critere de produit pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class CritereSelecteurModel implements IsSerializable {

    /**
     * Identifiant.
     */
    private Integer identifiant;

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Type html du composant.
     */
    private String typeHtml;

    /**
     * Valeur par défaut du critere.
     */
    private String valeurParDefautCod;

    /**
     * Champ Bareme code type.
     */
    private String champBaremeCodeType;

    /**
     * Ordre Code Ou Lib.
     */
    private Boolean ordreCodOuLib;

    /**
     * Afficher code ou lib.
     */
    private Boolean afficheCodeOuLib;

    /**
     * Forcage Code Critere.
     */
    private String forcageCodeCritere;

    /**
     * Modifiable.
     */
    private Boolean modifiable;

    /**
     * Définit si le critere est modifiable pour l'adherent principal.
     */
    private Boolean modifiablePourAdherentPrincipal;

    /**
     * Visible.
     */
    private Boolean visible;

    /**
     * Obligatoire.
     */
    private Boolean obligatoire;

    /**
     * Liste des valeurs de criteres pour l'assuré.
     */
    private List<ValeurCritereSelecteurModel> listeValeursCriteres;

    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Get the libelle value.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the libelle value.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Get the typeHtml value.
     * @return the typeHtml
     */
    public String getTypeHtml() {
        return typeHtml;
    }

    /**
     * Set the typeHtml value.
     * @param typeHtml the typeHtml to set
     */
    public void setTypeHtml(String typeHtml) {
        this.typeHtml = typeHtml;
    }

    /**
     * Get the valeurParDefautCod value.
     * @return the valeurParDefautCod
     */
    public String getValeurParDefautCod() {
        return valeurParDefautCod;
    }

    /**
     * Set the valeurParDefautCod value.
     * @param valeurParDefautCod the valeurParDefautCod to set
     */
    public void setValeurParDefautCod(String valeurParDefautCod) {
        this.valeurParDefautCod = valeurParDefautCod;
    }

    /**
     * Get the champBaremeCodeType value.
     * @return the champBaremeCodeType
     */
    public String getChampBaremeCodeType() {
        return champBaremeCodeType;
    }

    /**
     * Set the champBaremeCodeType value.
     * @param champBaremeCodeType the champBaremeCodeType to set
     */
    public void setChampBaremeCodeType(String champBaremeCodeType) {
        this.champBaremeCodeType = champBaremeCodeType;
    }

    /**
     * Get the ordreCodOuLib value.
     * @return the ordreCodOuLib
     */
    public Boolean getOrdreCodOuLib() {
        return ordreCodOuLib;
    }

    /**
     * Set the ordreCodOuLib value.
     * @param ordreCodOuLib the ordreCodOuLib to set
     */
    public void setOrdreCodOuLib(Boolean ordreCodOuLib) {
        this.ordreCodOuLib = ordreCodOuLib;
    }

    /**
     * Get the afficheCodeOuLib value.
     * @return the afficheCodeOuLib
     */
    public Boolean getAfficheCodeOuLib() {
        return afficheCodeOuLib;
    }

    /**
     * Set the afficheCodeOuLib value.
     * @param afficheCodeOuLib the afficheCodeOuLib to set
     */
    public void setAfficheCodeOuLib(Boolean afficheCodeOuLib) {
        this.afficheCodeOuLib = afficheCodeOuLib;
    }

    /**
     * Get the forcageCodeCritere value.
     * @return the forcageCodeCritere
     */
    public String getForcageCodeCritere() {
        return forcageCodeCritere;
    }

    /**
     * Set the forcageCodeCritere value.
     * @param forcageCodeCritere the forcageCodeCritere to set
     */
    public void setForcageCodeCritere(String forcageCodeCritere) {
        this.forcageCodeCritere = forcageCodeCritere;
    }

    /**
     * Get the modifiable value.
     * @return the modifiable
     */
    public Boolean getModifiable() {
        return modifiable;
    }

    /**
     * Set the modifiable value.
     * @param modifiable the modifiable to set
     */
    public void setModifiable(Boolean modifiable) {
        this.modifiable = modifiable;
    }

    /**
     * Get the visible value.
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Set the visible value.
     * @param visible the visible to set
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * Get the obligatoire value.
     * @return the obligatoire
     */
    public Boolean getObligatoire() {
        return obligatoire;
    }

    /**
     * Set the obligatoire value.
     * @param obligatoire the obligatoire to set
     */
    public void setObligatoire(Boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    /**
     * Get the modifiablePourAdherentPrincipal value.
     * @return the modifiablePourAdherentPrincipal
     */
    public Boolean getModifiablePourAdherentPrincipal() {
        return modifiablePourAdherentPrincipal;
    }

    /**
     * Set the modifiablePourAdherentPrincipal value.
     * @param modifiablePourAdherentPrincipal the modifiablePourAdherentPrincipal to set
     */
    public void setModifiablePourAdherentPrincipal(Boolean modifiablePourAdherentPrincipal) {
        this.modifiablePourAdherentPrincipal = modifiablePourAdherentPrincipal;
    }

    /**
     * Get the listeValeursCriteres value.
     * @return the listeValeursCriteres
     */
    public List<ValeurCritereSelecteurModel> getListeValeursCriteres() {
        return listeValeursCriteres;
    }

    /**
     * Set the listeValeursCriteres value.
     * @param listeValeursCriteres the listeValeursCriteres to set
     */
    public void setListeValeursCriteres(List<ValeurCritereSelecteurModel> listeValeursCriteres) {
        this.listeValeursCriteres = listeValeursCriteres;
    }

}
