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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.square.adherent.noyau.model.data.contrat.Garantie;
import com.square.adherent.noyau.model.dimension.TarifType;
import com.square.adherent.noyau.model.dimension.TypePayeur;

/**
 * Modèle d'un bareme de garantie.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_GARANTIE_BAREME")
public class GarantieBareme implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6101561263751464929L;

    /** Identifiant. */
    @Id
    @Column(name = "GARANTIE_BAREME_UID")
    private Long id;

    /** Identifiant extérieur. */
    @Column(name = "GARANTIE_BAREME_EID")
    private String identifiantExterieur;

    /** Garantie associé. */
    @ManyToOne
    @JoinColumn(name = "GARANTIE_BAREME_GARANTIE_UID")
    @ForeignKey(name = "fk_garantie_bareme_garantie")
    private Garantie garantie;

    /** Type payeur du bareme. */
    @ManyToOne
    @JoinColumn(name = "GARANTIE_BAREME_TYPE_PAYEUR_UID")
    @ForeignKey(name = "fk_garantie_bareme_type_payeur")
    private TypePayeur typePayeur;

    /** Tarif type du bareme. */
    @ManyToOne
    @JoinColumn(name = "GARANTIE_BAREME_TARIF_TYPE_UID")
    @ForeignKey(name = "fk_garantie_bareme_tarif_type")
    private TarifType tarifType;

    /** Formule. */
    @Column(name = "GARANTIE_BAREME_FORMULE")
    private String formule;

    /** Eid bareme 1. */
    @Column(name = "GARANTIE_BAREME_BAREME1_EID")
    private String bareme1Eid;

    /** Zone bareme 1. */
    @Column(name = "GARANTIE_BAREME_BAREME1_ZONE")
    private Integer bareme1Zone;

    /** Eid bareme 2. */
    @Column(name = "GARANTIE_BAREME_BAREME2_EID")
    private String bareme2Eid;

    /** Zone bareme 2. */
    @Column(name = "GARANTIE_BAREME_BAREME2_ZONE")
    private Integer bareme2Zone;

    /**
     * Get the value of id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of identifiantExterieur.
     * @return the identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Set the value of identifiantExterieur.
     * @param identifiantExterieur the identifiantExterieur to set
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }

    /**
     * Get the value of typePayeur.
     * @return the typePayeur
     */
    public TypePayeur getTypePayeur() {
        return typePayeur;
    }

    /**
     * Set the value of typePayeur.
     * @param typePayeur the typePayeur to set
     */
    public void setTypePayeur(TypePayeur typePayeur) {
        this.typePayeur = typePayeur;
    }

    /**
     * Get the value of tarifType.
     * @return the tarifType
     */
    public TarifType getTarifType() {
        return tarifType;
    }

    /**
     * Set the value of tarifType.
     * @param tarifType the tarifType to set
     */
    public void setTarifType(TarifType tarifType) {
        this.tarifType = tarifType;
    }

    /**
     * Get the value of formule.
     * @return the formule
     */
    public String getFormule() {
        return formule;
    }

    /**
     * Set the value of formule.
     * @param formule the formule to set
     */
    public void setFormule(String formule) {
        this.formule = formule;
    }

    /**
     * Get the value of bareme1Eid.
     * @return the bareme1Eid
     */
    public String getBareme1Eid() {
        return bareme1Eid;
    }

    /**
     * Set the value of bareme1Eid.
     * @param bareme1Eid the bareme1Eid to set
     */
    public void setBareme1Eid(String bareme1Eid) {
        this.bareme1Eid = bareme1Eid;
    }

    /**
     * Get the value of bareme1Zone.
     * @return the bareme1Zone
     */
    public Integer getBareme1Zone() {
        return bareme1Zone;
    }

    /**
     * Set the value of bareme1Zone.
     * @param bareme1Zone the bareme1Zone to set
     */
    public void setBareme1Zone(Integer bareme1Zone) {
        this.bareme1Zone = bareme1Zone;
    }

    /**
     * Get the value of bareme2Eid.
     * @return the bareme2Eid
     */
    public String getBareme2Eid() {
        return bareme2Eid;
    }

    /**
     * Set the value of bareme2Eid.
     * @param bareme2Eid the bareme2Eid to set
     */
    public void setBareme2Eid(String bareme2Eid) {
        this.bareme2Eid = bareme2Eid;
    }

    /**
     * Get the value of bareme2Zone.
     * @return the bareme2Zone
     */
    public Integer getBareme2Zone() {
        return bareme2Zone;
    }

    /**
     * Set the value of bareme2Zone.
     * @param bareme2Zone the bareme2Zone to set
     */
    public void setBareme2Zone(Integer bareme2Zone) {
        this.bareme2Zone = bareme2Zone;
    }

    /**
     * Get the value of garantie.
     * @return the garantie
     */
    public Garantie getGarantie() {
        return garantie;
    }

    /**
     * Set the value of garantie.
     * @param garantie the garantie to set
     */
    public void setGarantie(Garantie garantie) {
        this.garantie = garantie;
    }

}
