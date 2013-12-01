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
package com.square.print.core.dto.adhesion;

import java.io.Serializable;

/**
 * DTO représentant les critères d'un produit d'un devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class CritereProduitBADto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 7716887028697142181L;

    /** Identifiant du critère. */
    private Integer idCritere;

    /** Libellé. */
    private String libelle;

    /** Valeur. */
    private String valeur;

    /** Valeur à afficher. */
    private String affichageValeur;

    /** Affichage du critere dans le devis. */
    private Boolean afficherCritere = Boolean.TRUE;

    /** Constructeur. */
    public CritereProduitBADto() { }

    /**
     * Constructeur.
     * @param idCritere l'identifiant du critère
     * @param libelle le libellé
     * @param valeur la valeur
     * @param affichageValeur la valeur à afficher
     * @param afficherCritere flag indiquant si le critère doit être affiché
     */
    public CritereProduitBADto(Integer idCritere, String libelle, String valeur, String affichageValeur, Boolean afficherCritere) {
        this.idCritere = idCritere;
        this.libelle = libelle;
        this.valeur = valeur;
        this.affichageValeur = affichageValeur;
        this.afficherCritere = afficherCritere;
    }

    /**
     * Définit la valeur de libelle.
     * @return la valeur de libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit la valeur de libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Définit la valeur de valeur.
     * @return la valeur de valeur
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Définit la valeur de valeur.
     * @param valeur la nouvelle valeur de valeur
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    /**
     * Définit la valeur de idCritere.
     * @return la valeur de idCritere
     */
    public Integer getIdCritere() {
        return idCritere;
    }

    /**
     * Définit la valeur de idCritere.
     * @param idCritere la nouvelle valeur de idCritere
     */
    public void setIdCritere(Integer idCritere) {
        this.idCritere = idCritere;
    }

    /**
     * Définit la valeur de afficherCritere.
     * @return la valeur de afficherCritere
     */
    public Boolean getAfficherCritere() {
        return afficherCritere;
    }

    /**
     * Définit la valeur de afficherCritere.
     * @param afficherCritere la nouvelle valeur de afficherCritere
     */
    public void setAfficherCritere(Boolean afficherCritere) {
        this.afficherCritere = afficherCritere;
    }

    /**
     * Récupère la valeur de affichageValeur.
     * @return la valeur de affichageValeur
     */
    public String getAffichageValeur() {
        return affichageValeur;
    }

    /**
     * Définit la valeur de affichageValeur.
     * @param affichageValeur la nouvelle valeur de affichageValeur
     */
    public void setAffichageValeur(String affichageValeur) {
        this.affichageValeur = affichageValeur;
    }
}
