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
package com.square.price.core.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Dto represente un critere.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class CritereDto implements Serializable {

    private static final long serialVersionUID = -6611912392889022782L;

    /** Identifiant du critere. */
    private Integer identifiant;

    /** Libelle du critere. */
    private String libelle;

    /** Type HTML. */
    private String typeHtml;

    /** Valeur par defaut du critere. */
    private String valeurParDefautCod;

    /** Champ Bareme code type. */
    private String champBaremeCodeType;

    /** Liste des valeur par defaut. */
    private List<ValeurCritereDto> valeursPossibles;

    /** Ordre Code Ou Lib. */
    private Boolean ordreCodOuLib;

    /** Afficher code ou lib. */
    private Boolean afficheCodeOuLib;

    /** Forcage Code Critere. */
    private String forcageCodeCritere;

    /** Information par rapport à l'application appelante. */
    private CritereApplicationDto infosCritereApplication;

    /** Information mapping base du critére. */
    private String champBaremeCodeMapping;

    /** Information mapping libelle base du critére. */
    private String champBaremeLibMapping;

    /** Information mapping hibernate du critére. */
    private String champBaremeCodeMappingHibernate;

    /** Information mapping hibernate libelle du critere. */
    private String champBaremeLibMappingHibernate;

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
     * Get the valeursPossibles value.
     * @return the valeursPossibles
     */
    public List<ValeurCritereDto> getValeursPossibles() {
        return this.valeursPossibles;
    }

    /**
     * Set the valeursPossibles value.
     * @param valeursPossibles the valeursPossibles to set
     */
    public void setValeursPossibles(List<ValeurCritereDto> valeursPossibles) {
        this.valeursPossibles = valeursPossibles;
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
     * Get the infosCritereApplication value.
     * @return the infosCritereApplication
     */
    public CritereApplicationDto getInfosCritereApplication() {
        return infosCritereApplication;
    }

    /**
     * Set the infosCritereApplication value.
     * @param infosCritereApplication the infosCritereApplication to set
     */
    public void setInfosCritereApplication(CritereApplicationDto infosCritereApplication) {
        this.infosCritereApplication = infosCritereApplication;
    }

    /**
     * Get the champBaremeCodeMapping value.
     * @return the champBaremeCodeMapping
     */
    public String getChampBaremeCodeMapping() {
        return champBaremeCodeMapping;
    }

    /**
     * Set the champBaremeCodeMapping value.
     * @param champBaremeCodeMapping the champBaremeCodeMapping to set
     */
    public void setChampBaremeCodeMapping(String champBaremeCodeMapping) {
        this.champBaremeCodeMapping = champBaremeCodeMapping;
    }

    /**
     * Get the champBaremeLibMapping value.
     * @return the champBaremeLibMapping
     */
    public String getChampBaremeLibMapping() {
        return champBaremeLibMapping;
    }

    /**
     * Set the champBaremeLibMapping value.
     * @param champBaremeLibMapping the champBaremeLibMapping to set
     */
    public void setChampBaremeLibMapping(String champBaremeLibMapping) {
        this.champBaremeLibMapping = champBaremeLibMapping;
    }

    /**
     * Get the champBaremeCodeMappingHibernate value.
     * @return the champBaremeCodeMappingHibernate
     */
    public String getChampBaremeCodeMappingHibernate() {
        return champBaremeCodeMappingHibernate;
    }

    /**
     * Set the champBaremeCodeMappingHibernate value.
     * @param champBaremeCodeMappingHibernate the champBaremeCodeMappingHibernate to set
     */
    public void setChampBaremeCodeMappingHibernate(String champBaremeCodeMappingHibernate) {
        this.champBaremeCodeMappingHibernate = champBaremeCodeMappingHibernate;
    }

    /**
     * Get the champBaremeLibMappingHibernates value.
     * @return the champBaremeLibMappingHibernates
     */
    public String getChampBaremeLibMappingHibernate() {
        return champBaremeLibMappingHibernate;
    }

    /**
     * Set the champBaremeLibMappingHibernates value.
     * @param champBaremeLibMappingHibernates the champBaremeLibMappingHibernates to set
     */
    public void setChampBaremeLibMappingHibernate(String champBaremeLibMappingHibernates) {
        this.champBaremeLibMappingHibernate = champBaremeLibMappingHibernates;
    }
}
