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

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

/**
 * Entité persistante modélisant les téléphones.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@Entity
@Table(name = "DATA_TELEPHONE")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "TELEPHONE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "TELEPHONE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "TELEPHONE_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "TELEPHONE_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "TELEPHONE_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "TELEPHONE_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "TELEPHONE_SUPPRIME", nullable = false)) })
public class Telephone extends ModelData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 6015615599180477872L;

    /**
     * Numéro de téléphone.
     */
    @Column(name = "TELEPHONE_NUM", nullable = false)
    @Field(index = Index.TOKENIZED)
    private String numTelephone;

    /**
     * Clé étrangère vers la table PAYS.
     */
    @ManyToOne
    @JoinColumn(name = "PAYS_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_TELEPHONE_DIM_PAYS_PAYS_UID")
    private Pays pays;

    /**
     * Clé étrangère vers la table TELEPHONE_NATURE.
     */
    @ManyToOne
    @JoinColumn(name = "TELEPHONE_NATURE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_TELEPHONE_DIM_TELEPHONE_NATURE_TELEPHONE_NATURE_UID")
    private NatureTelephone natureTelephone;

    /** La liste des personnes pouvant être contactées à cette numéro de téléphone. */
    @ManyToMany(cascade = {CascadeType.ALL }, mappedBy = "telephones", targetEntity = Personne.class)
    @ForeignKey(name = "FK_PERSONNES_TELEPHONES_TELEPHONE")
    @ContainedIn
    private Set<Personne> personnes;

    /** Identifiant du porteur du téléphone. */
    @Column(name = "PERSONNE_UID")
    private Long porteurUid;

    /**
     * Retourne la valeur de numTelephone.
     * @return the numTelephone
     */
    public String getNumTelephone() {
        return numTelephone;
    }

    /**
     * Modifie la valeur de numTelephone.
     * @param numTelephone the numTelephone to set
     */
    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    /**
     * Retourne la valeur de pays.
     * @return the pays
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Modifie la valeur de pays.
     * @param pays the pays to set
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * Retourne la valeur de natureTelephone.
     * @return the natureTelephone
     */
    public NatureTelephone getNatureTelephone() {
        return natureTelephone;
    }

    /**
     * Modifie la valeur de natureTelephone.
     * @param natureTelephone the natureTelephone to set
     */
    public void setNatureTelephone(NatureTelephone natureTelephone) {
        this.natureTelephone = natureTelephone;
    }

    /**
     * Getter function.
     * @return the personnes
     */
    public Set<Personne> getPersonnes() {
        if (personnes == null) {
            personnes = new LinkedHashSet<Personne>();
        }
        return personnes;
    }

    /**
     * Setter function.
     * @param personnes the personnes to set
     */
    public void setPersonnes(Set<Personne> personnes) {
        this.personnes = personnes;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Telephone)) {
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

    /**
     * Récupère la valeur de porteurUid.
     * @return la valeur de porteurUid
     */
    public Long getPorteurUid() {
        return porteurUid;
    }

    /**
     * Définit la valeur de porteurUid.
     * @param porteurUid la nouvelle valeur de porteurUid
     */
    public void setPorteurUid(Long porteurUid) {
        this.porteurUid = porteurUid;
    }

}
