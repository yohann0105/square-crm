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
package com.square.tarificateur.noyau.model.opportunite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle de la valeur de critère d'une ligne de devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_VALEUR_CRITERE_LIGNE_DEVIS")
public class ValeurCritereLigneDevis extends BaseModel implements Comparable<ValeurCritereLigneDevis> {

    /** SerialVersionUID. */
    private static final long serialVersionUID = -5240827261323984238L;

    /** Critère associé. */
    @Column(name = "CRITERE_EID")
    private Integer eidCritere;

    /** Valeur du critère. */
    @Column(name = "VALEUR", nullable = false)
    private String valeur;

    /** Affichage de la valeur. */
    @Column(name = "AFFICHAGE_VALEUR")
    private String affichageValeur;

    /**
     * Get the affichageValeur value.
     * @return the affichageValeur
     */
    public String getAffichageValeur() {
        return affichageValeur;
    }

    /**
     * Set the affichageValeur value.
     * @param affichageValeur the affichageValeur to set
     */
    public void setAffichageValeur(String affichageValeur) {
        this.affichageValeur = affichageValeur;
    }

    /**
     * Get the valeur value.
     * @return the valeur
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Set the valeur value.
     * @param valeur the valeur to set
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    /**
     * Comparaison de 2 valeurs de critères pour une ligne de devis.
     * @param valeurCritereLigneDevis l'objet a comparer
     * @return l'entier descriptif de la comparaison
     */
    public final int compareTo(final ValeurCritereLigneDevis valeurCritereLigneDevis) {

        final Integer orderO = valeurCritereLigneDevis.getEidCritere();
        final Integer orderT = this.getEidCritere();

        if (orderO.longValue() > orderT.longValue()) {
            return -1;
        } else if (orderO.longValue() == orderT.longValue()) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ValeurCritereLigneDevis)) { return false; }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Récupère la valeur de eidCritere.
     * @return the eidCritere
     */
    public Integer getEidCritere() {
        return eidCritere;
    }

    /**
     * Définit la valeur de eidCritere.
     * @param eidCritere the eidCritere to set
     */
    public void setEidCritere(Integer eidCritere) {
        this.eidCritere = eidCritere;
    }
}
