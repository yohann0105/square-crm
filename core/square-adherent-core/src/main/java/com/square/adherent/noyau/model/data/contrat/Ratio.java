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
 * Modèle d'un ratio (prestation/cotisation).
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_RATIO")
public class Ratio  implements Serializable {

    /** Identifiant de serialisation. */
    private static final long serialVersionUID = 2817805003008961972L;

    /** Identifiant. */
    @Id
    @Column(name = "RATIO_UID")
    private Long id;

    /** UID de la personne associé. */
    @Column(name = "RATIO_PERSONNE_UID")
    private Long uidPersonne;

    /** Date de création. */
    @Column(name = "RATIO_DATE_CREATION")
    private Calendar dateCreation;

    /** Date de modification. */
    @Column(name = "RATIO_DATE_MODIFICATION")
    private Calendar dateModification;

    /** Année du ratio. */
    @Column(name = "RATIO_ANNEE")
    private int annee;

    /** Ratio des prestations sur les cotisations. */
    @Column(name = "RATIO_PRESTA_SUR_COTIS")
    private double ratioPrestaSurCotis;

    /** Montant des prestations. */
    @Column(name = "RATIO_MT_PRESTATION")
    private double montantPresta;

    /** Montant des cotisations. */
    @Column(name = "RATIO_MT_COTISATION")
    private double montantCotis;

    /** Qualification du ratio. */
    @Column(name = "RATIO_QUALIFICATION")
    private String qualification;

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
     * Récupère la valeur de dateCreation.
     * @return la valeur de dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Définit la valeur de dateCreation.
     * @param dateCreation la nouvelle valeur de dateCreation
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Récupère la valeur de dateModification.
     * @return la valeur de dateModification
     */
    public Calendar getDateModification() {
        return dateModification;
    }

    /**
     * Définit la valeur de dateModification.
     * @param dateModification la nouvelle valeur de dateModification
     */
    public void setDateModification(Calendar dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Récupère la valeur de annee.
     * @return la valeur de annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Définit la valeur de annee.
     * @param annee la nouvelle valeur de annee
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Récupère la valeur de ratioPrestaSurCotis.
     * @return la valeur de ratioPrestaSurCotis
     */
    public double getRatioPrestaSurCotis() {
        return ratioPrestaSurCotis;
    }

    /**
     * Définit la valeur de ratioPrestaSurCotis.
     * @param ratioPrestaSurCotis la nouvelle valeur de ratioPrestaSurCotis
     */
    public void setRatioPrestaSurCotis(double ratioPrestaSurCotis) {
        this.ratioPrestaSurCotis = ratioPrestaSurCotis;
    }

    /**
     * Récupère la valeur de montantPresta.
     * @return la valeur de montantPresta
     */
    public double getMontantPresta() {
        return montantPresta;
    }

    /**
     * Définit la valeur de montantPresta.
     * @param montantPresta la nouvelle valeur de montantPresta
     */
    public void setMontantPresta(double montantPresta) {
        this.montantPresta = montantPresta;
    }

    /**
     * Récupère la valeur de montantCotis.
     * @return la valeur de montantCotis
     */
    public double getMontantCotis() {
        return montantCotis;
    }

    /**
     * Définit la valeur de montantCotis.
     * @param montantCotis la nouvelle valeur de montantCotis
     */
    public void setMontantCotis(double montantCotis) {
        this.montantCotis = montantCotis;
    }

    /**
     * Récupère la valeur de qualification.
     * @return la valeur de qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * Définit la valeur de qualification.
     * @param qualification la nouvelle valeur de qualification
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
