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

/**
 * DTO permettant de récupérer les informations d'une caisse.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class CaisseDto extends IdentifiantEidLibelleDto {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -4569900831810489942L;

    /** Centre de la caisse. */
    private String centre;

    /** Nom de la caisse. */
    private String nom;

    /** Code de la caisse. */
    private String code;

    /** Télétransmission. */
    private String teletrans;

    /** Regime associé. */
    private RegimeDto regime;

    /**
     * Récupère la valeur de code.
     * @return la valeur de code
     */
    public String getCode() {
        return code;
    }

    /**
     * Définit la valeur de code.
     * @param code la nouvelle valeur de code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Recuperer la valeur.
     * @return the regime
     */
    public RegimeDto getRegime() {
        return regime;
    }

    /**
     * Fixer la valeur.
     * @param regime the regime to set
     */
    public void setRegime(RegimeDto regime) {
        this.regime = regime;
    }

    /**
     * Récupère la valeur de centre.
     * @return la valeur de centre
     */
    public String getCentre() {
        return centre;
    }

    /**
     * Définit la valeur de centre.
     * @param centre la nouvelle valeur de centre
     */
    public void setCentre(String centre) {
        this.centre = centre;
    }

    /**
     * Récupère la valeur de nom.
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la valeur de teletrans.
     * @return la valeur de teletrans
     */
    public String getTeletrans() {
        return teletrans;
    }

    /**
     * Définit la valeur de teletrans.
     * @param teletrans la nouvelle valeur de teletrans
     */
    public void setTeletrans(String teletrans) {
        this.teletrans = teletrans;
    }
}
