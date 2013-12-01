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
package com.square.core.model;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * Entité persistant représentant les caisses.
 * @author cblanchard - SCUB
 */
@Entity
@Table(name = "DIM_CAISSE")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "CAISSE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "CAISSE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "CAISSE_EID", unique = true)),
    @AttributeOverride(name = "ordre", column = @Column(name = "CAISSE_ORDRE", nullable = false)),
    @AttributeOverride(name = "visible", column = @Column(name = "CAISSE_VISIBLE", nullable = false)) })
public class Caisse extends ModelDim {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 3308565329060775788L;

    /**
     * Centre de la caisse.
     */
    @Column(name = "CAISSE_CENTRE")
    private String centre;

    /**
     * Nom de la caisse.
     */
    @Column(name = "CAISSE_NOM")
    private String nom;

    /**
     * Code de la caisse.
     */
    @Column(name = "CAISSE_CODE", nullable = false)
    private String code;

    /**
     * Télétransmission.
     */
    @Column(name = "CAISSE_TELETRANS", nullable = false)
    private String teletrans;

    /**
     * Many to many Vers les département.
     */
    @ManyToMany()
    @JoinTable(name = "DATA_CAISSE_DEPARTEMENT", joinColumns = @JoinColumn(name = "CAISSE_UID"), inverseJoinColumns = @JoinColumn(name = "DEPARTEMENT_UID"))
    @ForeignKey(name = "FK_DIM_CAISSE_DATA_CAISSE_DEPARTEMENT_CAISSE_DEPARTEMENT_UID")
    private Set<Departement> departements;

    /**
     * Régime.
     */
    @ManyToOne()
    @JoinColumn(name = "CAISSE_REGIME_UID", nullable = false)
    @ForeignKey(name = "FK_DIM_CAISSE_DIM_CAISSE_REGIME_CAISSE_REGIME_UID")
    private CaisseRegime regime;

    /**
     * Renvoi la valeur de centre.
     * @return centre
     */
    public String getCentre() {
        return centre;
    }

    /**
     * Modifie centre.
     * @param centre la nouvelle valeur de centre
     */
    public void setCentre(String centre) {
        this.centre = centre;
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
     * Renvoi la valeur de code.
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Modifie code.
     * @param code la nouvelle valeur de code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Renvoi la valeur de teletrans.
     * @return teletrans
     */
    public String getTeletrans() {
        return teletrans;
    }

    /**
     * Modifie teletrans.
     * @param teletrans la nouvelle valeur de teletrans
     */
    public void setTeletrans(String teletrans) {
        this.teletrans = teletrans;
    }

    /**
     * Renvoi la valeur de regime.
     * @return regime
     */
    public CaisseRegime getRegime() {
        return regime;
    }

    /**
     * Modifie regime.
     * @param regime la nouvelle valeur de regime
     */
    public void setRegime(CaisseRegime regime) {
        this.regime = regime;
    }

    /**
     * Retourne la liste des departements associés.
     * @return the departements .
     */
    public Set<Departement> getDepartements() {
        return departements;
    }

    /**
     * Fixe la liste des departements associé.
     * @param departements the departements to set
     */
    public void setDepartements(Set<Departement> departements) {
        this.departements = departements;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Caisse)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
