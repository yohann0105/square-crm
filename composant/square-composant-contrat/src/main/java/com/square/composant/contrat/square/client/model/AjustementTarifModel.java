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
package com.square.composant.contrat.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle qui encapsule les informations d'un ajustement.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class AjustementTarifModel implements IsSerializable {

    /**
     * Reference de l'ajustement.
     */
    private String reference;

    /**
     * Nature de l'ajustement.
     */
    private String nature;

    /**
     * Montant de l'ajustement.
     */
    private double montant;

    /**
     * Date de début de l'ajustement.
     */
    private String dateDebut;

    /**
     * Date de fin de l'ajustement.
     */
    private String dateFin;

    /**
     * Getter function.
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Getter function.
     * @return the nature
     */
    public String getNature() {
        return nature;
    }

    /**
     * Getter function.
     * @return the dateDebut
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Getter function.
     * @return the dateFin
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Setter function.
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Setter function.
     * @param nature the nature to set
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * Setter function.
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Setter function.
     * @param dateFin the dateFin to set
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Récupère la valeur de montant.
     * @return la valeur de montant
     */
    public double getMontant() {
        return montant;
    }

    /**
     * Définit la valeur de montant.
     * @param montant la nouvelle valeur de montant
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }
}
