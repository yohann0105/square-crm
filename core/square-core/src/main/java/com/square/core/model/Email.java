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
 * Entité persistante représentant les emails.
 * @author cblanchard - SCUB
 */
@Entity
@Table(name = "DATA_EMAIL")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "EMAIL_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "EMAIL_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "EMAIL_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "EMAIL_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "EMAIl_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "EMAIL_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "EMAIL_SUPPRIME", nullable = false)) })
public class Email extends ModelData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 4071803149064883487L;

    /**
     * Email adresse.
     */
    @Field(index = Index.TOKENIZED)
    @Column(name = "EMAIL_ADRESSE")
    private String adresse;

    /**
     * Nature de l'email.
     */
    @ManyToOne()
    @JoinColumn(name = "EMAIL_NATURE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_EMAIL_DIM_EMAIL_NATURE_EMAIL_NATURE_UID")
    private EmailNature nature;

    /** La liste des personnes associés à cette adresse email. */
    @ManyToMany(cascade = {CascadeType.ALL }, mappedBy = "emails", targetEntity = Personne.class)
    @ForeignKey(name = "FK_PERSONNES_EMAILS_EMAIL")
    @ContainedIn
    private Set<Personne> personnes;

    /** Identifiant du porteur de l'email. */
    @Column(name = "PERSONNE_UID")
    private Long porteurUid;

    /**
     * Renvoi la valeur de adresse.
     * @return adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Modifie adresse.
     * @param adresse la nouvelle valeur de adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Renvoi la valeur de nature.
     * @return nature
     */
    public EmailNature getNature() {
        return nature;
    }

    /**
     * Modifie nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(EmailNature nature) {
        this.nature = nature;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Email)) {
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
