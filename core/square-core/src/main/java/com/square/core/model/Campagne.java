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

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

import com.square.core.model.Ressources.Ressource;

/**
 * Entité persistante modélisant les campagnes.
 * @author straumat - SCUB
 */
@Entity
@Table(name = "DATA_CAMPAGNE")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "CAMPAGNE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "CAMPAGNE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "CAMPAGNE_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "CAMPAGNE_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "CAMPAGNE_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "CAMPAGNE_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "CAMPAGNE_SUPPRIME", nullable = false)) })
public class Campagne extends ModelData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 2484985382449560207L;

    /**
     * Libelle de la campagne.
     */
    @Column(name = "CAMPAGNE_LIB", unique = true, nullable = false)
    private String libelle;

    /**
     * Date de début de la campagne.
     */
    @Column(name = "CAMPAGNE_DATE_DEBUT", nullable = false)
    private Calendar dateDebut;

    /**
     * Date de fin de la campagne.
     */
    @Column(name = "CAMPAGNE_DATE_FIN", nullable = false)
    private Calendar dateFin;

    /**
     * Code de la campagne.
     */
    @Column(name = "CAMPAGNE_CODE", nullable = false)
    private String code;

    /**
     * Description de la campagne.
     */
    @Column(name = "CAMPAGNE_DESCRIPTIF")
    @Type(type = "text")
    private String descriptif;

    /**
     * Statut de la campagne.
     */
    @ManyToOne
    @JoinColumn(name = "CAMPAGNE_STATUT_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_CAMPAGNE_DIM_CAMPAGNE_STATUT_CAMPAGNE_STATUT_UID")
    private CampagneStatut statut;

    /**
     * Type de la campagne.
     */
    @ManyToOne
    @JoinColumn(name = "CAMPAGNE_TYPE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_CAMPAGNE_DIM_CAMPAGNE_TYPE_CAMPAGNE_TYPE_UID")
    private CampagneType type;

    /**
     * Ressource ayant créé la campagne.
     */
    @ManyToOne()
    @JoinColumn(name = "RESSOURCE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_CAMPAGNE_DATA_RESSOURCE_RESSOURCE_UID")
    private Ressource ressource;

    /**
     * Getter/setter de la prioriété.
     * @return the dateDebut
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * Getter/setter de la prioriété.
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter/setter de la prioriété.
     * @return the dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Getter/setter de la prioriété.
     * @param dateFin the dateFin to set
     */
    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Getter/setter de la prioriété.
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter/setter de la prioriété.
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter/setter de la prioriété.
     * @return the statut
     */
    public CampagneStatut getStatut() {
        return statut;
    }

    /**
     * Getter/setter de la prioriété.
     * @param statut the statut to set
     */
    public void setStatut(CampagneStatut statut) {
        this.statut = statut;
    }

    /**
     * Getter/setter de la prioriété.
     * @return the type
     */
    public CampagneType getType() {
        return type;
    }

    /**
     * Getter/setter de la prioriété.
     * @param type the type to set
     */
    public void setType(CampagneType type) {
        this.type = type;
    }

    /**
     * Getter/setter de la prioriété.
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Renvoi la valeur de libelle.
     * @return libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Modifie libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Renvoi la valeur de descriptif.
     * @return descriptif
     */
    public String getDescriptif() {
        return descriptif;
    }

    /**
     * Modifie descriptif.
     * @param descriptif la nouvelle valeur de descriptif
     */
    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    /**
     * Retourne la valeur de ressource.
     * @return the ressource
     */
    public Ressource getRessource() {
        return ressource;
    }

    /**
     * Modifie la valeur de ressource.
     * @param ressource the ressource to set
     */
    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Campagne)) {
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
