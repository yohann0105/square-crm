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
package com.square.adherent.noyau.model.data.contrat;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Modèle d'une franchise.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_FRANCHISE")
public class Franchise implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -8436260172973606542L;

    /** Identifiant. */
    @Id
    @Column(name = "FRANCHISE_UID")
    private Long id;

    /** Identifiant de la garantie. */
    @Column(name = "FRANCHISE_GARANTIE_UID")
    private Long uidGarantie;

    /** Identifiant de la personne. */
    @Column(name = "FRANCHISE_PERSONNE_UID")
    private Long uidPersonne;

    /** Cotisation annuelle. */
    @Column(name = "FRANCHISE_COTISATION_ANNUELLE")
    private double cotisationAnnuelle;

    /** Réserve versée. */
    @Column(name = "FRANCHISE_VERSEE_RESERVE")
    private double reserveVersee;

    /** Date de début de période. */
    @Column(name = "FRANCHISE_DEBUT_PERIODE")
    private Calendar dateDebutPeriode;

    /** Date de fin de période. */
    @Column(name = "FRANCHISE_FIN_PERIODE")
    private Calendar dateFinPeriode;

    /**
     * Récupère la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la valeur de uidGarantie.
     * @return la valeur de uidGarantie
     */
    public Long getUidGarantie() {
        return uidGarantie;
    }

    /**
     * Définit la valeur de uidGarantie.
     * @param uidGarantie la nouvelle valeur de uidGarantie
     */
    public void setUidGarantie(Long uidGarantie) {
        this.uidGarantie = uidGarantie;
    }

    /**
     * Récupère la valeur de uidPersonne.
     * @return la valeur de uidPersonne
     */
    public Long getUidPersonne() {
        return uidPersonne;
    }

    /**
     * Définit la valeur de uidPersonne.
     * @param uidPersonne la nouvelle valeur de uidPersonne
     */
    public void setUidPersonne(Long uidPersonne) {
        this.uidPersonne = uidPersonne;
    }

    /**
     * Récupère la valeur de cotisationAnnuelle.
     * @return la valeur de cotisationAnnuelle
     */
    public double getCotisationAnnuelle() {
        return cotisationAnnuelle;
    }

    /**
     * Définit la valeur de cotisationAnnuelle.
     * @param cotisationAnnuelle la nouvelle valeur de cotisationAnnuelle
     */
    public void setCotisationAnnuelle(double cotisationAnnuelle) {
        this.cotisationAnnuelle = cotisationAnnuelle;
    }

    /**
     * Récupère la valeur de reserveVersee.
     * @return la valeur de reserveVersee
     */
    public double getReserveVersee() {
        return reserveVersee;
    }

    /**
     * Définit la valeur de reserveVersee.
     * @param reserveVersee la nouvelle valeur de reserveVersee
     */
    public void setReserveVersee(double reserveVersee) {
        this.reserveVersee = reserveVersee;
    }

    /**
     * Récupère la valeur de dateDebutPeriode.
     * @return la valeur de dateDebutPeriode
     */
    public Calendar getDateDebutPeriode() {
        return dateDebutPeriode;
    }

    /**
     * Définit la valeur de dateDebutPeriode.
     * @param dateDebutPeriode la nouvelle valeur de dateDebutPeriode
     */
    public void setDateDebutPeriode(Calendar dateDebutPeriode) {
        this.dateDebutPeriode = dateDebutPeriode;
    }

    /**
     * Récupère la valeur de dateFinPeriode.
     * @return la valeur de dateFinPeriode
     */
    public Calendar getDateFinPeriode() {
        return dateFinPeriode;
    }

    /**
     * Définit la valeur de dateFinPeriode.
     * @param dateFinPeriode la nouvelle valeur de dateFinPeriode
     */
    public void setDateFinPeriode(Calendar dateFinPeriode) {
        this.dateFinPeriode = dateFinPeriode;
    }
}
