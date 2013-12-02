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
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;

/**
 * DTO pour les valeurs des règles d'une ligne de devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ValeurRegleLigneDevisDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 3407422352780030483L;

    /** Identifiant de la règle. */
    private Integer identifiantRegle;

    /** Libellé de la règle. */
    private String libelleRegle;

    /** Valeur de la règle. */
    private String valeurRegle;

    /** Type de la valeur de règle. */
    private boolean valeurRegleIsTypeString = false;

    /** Type de la valeur de règle. */
    private boolean valeurRegleIsTypeInteger = false;

    /** Type de la valeur de règle. */
    private boolean valeurRegleIsTypeLong = false;

    /** Impacte le tarif. */
    private boolean impacteTarif = false;

    /**
     * Définit la valeur de identifiantRegle.
     * @return la valeur de identifiantRegle
     */
    public Integer getIdentifiantRegle() {
        return identifiantRegle;
    }

    /**
     * Définit la valeur de identifiantRegle.
     * @param identifiantRegle la nouvelle valeur de identifiantRegle
     */
    public void setIdentifiantRegle(Integer identifiantRegle) {
        this.identifiantRegle = identifiantRegle;
    }

    /**
     * Définit la valeur de libelleRegle.
     * @return la valeur de libelleRegle
     */
    public String getLibelleRegle() {
        return libelleRegle;
    }

    /**
     * Définit la valeur de libelleRegle.
     * @param libelleRegle la nouvelle valeur de libelleRegle
     */
    public void setLibelleRegle(String libelleRegle) {
        this.libelleRegle = libelleRegle;
    }

    /**
     * Définit la valeur de valeurRegle.
     * @return la valeur de valeurRegle
     */
    public String getValeurRegle() {
        return valeurRegle;
    }

    /**
     * Définit la valeur de valeurRegle.
     * @param valeurRegle la nouvelle valeur de valeurRegle
     */
    public void setValeurRegle(String valeurRegle) {
        this.valeurRegle = valeurRegle;
    }

    /**
     * Get the valeurRegleIsTypeString value.
     * @return the valeurRegleIsTypeString
     */
    public boolean isValeurRegleIsTypeString() {
        return valeurRegleIsTypeString;
    }

    /**
     * Set the valeurRegleIsTypeString value.
     * @param valeurRegleIsTypeString the valeurRegleIsTypeString to set
     */
    public void setValeurRegleIsTypeString(boolean valeurRegleIsTypeString) {
        this.valeurRegleIsTypeString = valeurRegleIsTypeString;
    }

    /**
     * Get the valeurRegleIsTypeInteger value.
     * @return the valeurRegleIsTypeInteger
     */
    public boolean isValeurRegleIsTypeInteger() {
        return valeurRegleIsTypeInteger;
    }

    /**
     * Set the valeurRegleIsTypeInteger value.
     * @param valeurRegleIsTypeInteger the valeurRegleIsTypeInteger to set
     */
    public void setValeurRegleIsTypeInteger(boolean valeurRegleIsTypeInteger) {
        this.valeurRegleIsTypeInteger = valeurRegleIsTypeInteger;
    }

    /**
     * Get the valeurRegleIsTypeLong value.
     * @return the valeurRegleIsTypeLong
     */
    public boolean isValeurRegleIsTypeLong() {
        return valeurRegleIsTypeLong;
    }

    /**
     * Set the valeurRegleIsTypeLong value.
     * @param valeurRegleIsTypeLong the valeurRegleIsTypeLong to set
     */
    public void setValeurRegleIsTypeLong(boolean valeurRegleIsTypeLong) {
        this.valeurRegleIsTypeLong = valeurRegleIsTypeLong;
    }

    /**
     * Get the impacteTarif value.
     * @return the impacteTarif
     */
    public boolean isImpacteTarif() {
        return impacteTarif;
    }

    /**
     * Set the impacteTarif value.
     * @param impacteTarif the impacteTarif to set
     */
    public void setImpacteTarif(boolean impacteTarif) {
        this.impacteTarif = impacteTarif;
    }

}
