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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * Modèle d'un ajustement de tarif.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table (name = "DATA_AJUSTEMENT_TARIF")
public class AjustementTarif implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6502490951529036551L;

    /** Identifiant. */
    @Id
    @Column(name = "AJUSTEMENT_TARIF_UID")
    private Long id;

    /** Contrat associé. */
    @ManyToOne
    @JoinColumn(name = "AJUSTEMENT_TARIF_CONTRAT_UID")
    @ForeignKey(name = "fk_ajustement_contrat")
    private Contrat contrat;

    /** UID de l'assuré associé. */
    @Column(name = "AJUSTEMENT_TARIF_ASSURE_UID")
    private Long uidAssure;

    /** Nature de l'ajustement. */
    @Column(name = "AJUSTEMENT_TARIF_NATURE")
    private String nature;

    /** Montant de l'ajustement. */
    @Column(name = "AJUSTEMENT_TARIF_MONTANT")
    private double montant;

    /** Date de début de l'ajustement. */
    @Column(name = "AJUSTEMENT_TARIF_DATE_DEBUT")
    private Calendar dateDebut;

    /** Date de fin de l'ajustement. */
    @Column(name = "AJUSTEMENT_TARIF_DATE_FIN")
    private Calendar dateFin;

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
     * Récupère la valeur de contrat.
     * @return la valeur de contrat
     */
    public Contrat getContrat() {
        return contrat;
    }

    /**
     * Définit la valeur de contrat.
     * @param contrat la nouvelle valeur de contrat
     */
    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    /**
     * Récupère la valeur de uidAssure.
     * @return la valeur de uidAssure
     */
    public Long getUidAssure() {
        return uidAssure;
    }

    /**
     * Définit la valeur de uidAssure.
     * @param uidAssure la nouvelle valeur de uidAssure
     */
    public void setUidAssure(Long uidAssure) {
        this.uidAssure = uidAssure;
    }

    /**
     * Récupère la valeur de nature.
     * @return la valeur de nature
     */
    public String getNature() {
        return nature;
    }

    /**
     * Définit la valeur de nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(String nature) {
        this.nature = nature;
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

    /**
     * Récupère la valeur de dateDebut.
     * @return la valeur de dateDebut
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * Définit la valeur de dateDebut.
     * @param dateDebut la nouvelle valeur de dateDebut
     */
    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Récupère la valeur de dateFin.
     * @return la valeur de dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Définit la valeur de dateFin.
     * @param dateFin la nouvelle valeur de dateFin
     */
    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }
}
