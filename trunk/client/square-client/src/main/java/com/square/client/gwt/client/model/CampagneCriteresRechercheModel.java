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
 * Model contenant les critères de recherche d'une campagne.
 * @author cblanchard - SCUB
 */
public class CampagneCriteresRechercheModel implements IsSerializable {

    /** Identifiant de la campagne. */
    private Long id;

    /** Code de la campagne. */
    private String code;

    /** Libelle de la campagne. */
    private String libelle;

    /** Type de la campagne. */
    private List<Long> listeTypes;

    /** Statut de la campagne. */
    private List<Long> listeStatuts;

    /** Date de début de plage pour la date de début. */
    private String dateDebutBorneInf;

    /** Date de fin de plage pour la date de début. */
    private String dateDebutBorneSup;

    /** Date de début de plage pour la date de fin. */
    private String dateFinBorneInf;

    /** Date de fin de plage pour la date de fin. */
    private String dateFinBorneSup;

    /** Créateur de la campagne. */
    private Long idCreateur;

    /**
     * Renvoi la valuer de code.
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Affecte la valeur de code.
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Renvoi la valeur de libelle.
     * @return libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Modifie libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Renvoi la valeur de dateDebutBorneInf.
     * @return dateDebutBorneInf
     */
    public String getDateDebutBorneInf() {
        return dateDebutBorneInf;
    }

    /**
     * Modifie dateDebutBorneInf.
     * @param dateDebutBorneInf la nouvelle valeur de dateDebutBorneInf
     */
    public void setDateDebutBorneInf(String dateDebutBorneInf) {
        this.dateDebutBorneInf = dateDebutBorneInf;
    }

    /**
     * Renvoi la valeur de dateDebutBorneSup.
     * @return dateDebutBorneSup
     */
    public String getDateDebutBorneSup() {
        return dateDebutBorneSup;
    }

    /**
     * Modifie dateDebutBorneSup.
     * @param dateDebutBorneSup la nouvelle valeur de dateDebutBorneSup
     */
    public void setDateDebutBorneSup(String dateDebutBorneSup) {
        this.dateDebutBorneSup = dateDebutBorneSup;
    }

    /**
     * Renvoi la valeur de dateFinBorneInf.
     * @return dateFinBorneInf
     */
    public String getDateFinBorneInf() {
        return dateFinBorneInf;
    }

    /**
     * Modifie dateFinBorneInf.
     * @param dateFinBorneInf la nouvelle valeur de dateFinBorneInf
     */
    public void setDateFinBorneInf(String dateFinBorneInf) {
        this.dateFinBorneInf = dateFinBorneInf;
    }

    /**
     * Renvoi la valeur de dateFinBorneSup.
     * @return dateFinBorneSup
     */
    public String getDateFinBorneSup() {
        return dateFinBorneSup;
    }

    /**
     * Modifie dateFinBorneSup.
     * @param dateFinBorneSup la nouvelle valeur de dateFinBorneSup
     */
    public void setDateFinBorneSup(String dateFinBorneSup) {
        this.dateFinBorneSup = dateFinBorneSup;
    }

    /**
     * Renvoi la valeur de listeTypes.
     * @return listeTypes
     */
    public List<Long> getListeTypes() {
        return listeTypes;
    }

    /**
     * Modifie listeTypes.
     * @param listeTypes la nouvelle valeur de listeTypes
     */
    public void setListeTypes(List<Long> listeTypes) {
        this.listeTypes = listeTypes;
    }

    /**
     * Renvoi la valeur de listeStatuts.
     * @return listeStatuts
     */
    public List<Long> getListeStatuts() {
        return listeStatuts;
    }

    /**
     * Modifie listeStatuts.
     * @param listeStatuts la nouvelle valeur de listeStatuts
     */
    public void setListeStatuts(List<Long> listeStatuts) {
        this.listeStatuts = listeStatuts;
    }

    /**
     * Retourne la valeur de idCreateur.
     * @return the idCreateur
     */
    public Long getIdCreateur() {
        return idCreateur;
    }

    /**
     * Modifie la valeur de idCreateur.
     * @param idCreateur the idCreateur to set
     */
    public void setIdCreateur(Long idCreateur) {
        this.idCreateur = idCreateur;
    }

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

}
