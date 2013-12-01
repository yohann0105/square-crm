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
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.square.core.model.Ressources.Ressource;

/**
 * Entité persistante modélisant les personnes.
 * @author cblanchard - SCUB
 */
@Entity
@Table(name = "DATA_PERSONNE")
@Inheritance(strategy = InheritanceType.JOINED)
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "PERSONNE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "PERSONNE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "PERSONNE_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "PERSONNE_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "PERSONNE_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "PERSONNE_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "PERSONNE_SUPPRIME", nullable = false)) })
public class Personne extends ModelData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -2497228187504844281L;

    /**
     * Numéro de la personne.
     */
    @Column(name = "PERSONNE_NUM")
    @Field(name = "num", index = Index.TOKENIZED)
    private String num;

    /**
     * Adresses de la personne.
     */
    @ManyToMany(targetEntity = Adresse.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DATA_PERSONNES_ADRESSES", joinColumns = @JoinColumn(name = "PERSONNE_UID"), inverseJoinColumns = @JoinColumn(name = "ADRESSE_UID"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"PERSONNE_UID", "ADRESSE_UID" }))
    @ForeignKey(name = "FK_PERSONNES_ADRESSES_PERSONNE")
    @IndexedEmbedded
    private Set<Adresse> adresses;

    /**
     * Téléphones de la personne.
     */
    @ManyToMany(targetEntity = Telephone.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DATA_PERSONNES_TELEPHONES", joinColumns = @JoinColumn(name = "PERSONNE_UID"), inverseJoinColumns = @JoinColumn(name = "TELEPHONE_UID"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"PERSONNE_UID", "TELEPHONE_UID" }))
    @ForeignKey(name = "FK_PERSONNES_TELEPHONES_PERSONNE")
    @IndexedEmbedded
    private Set<Telephone> telephones;

    /**
     * Email de la personne.
     */
    @ManyToMany(targetEntity = Email.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DATA_PERSONNES_EMAILS", joinColumns = @JoinColumn(name = "PERSONNE_UID"), inverseJoinColumns = @JoinColumn(name = "EMAIL_UID"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"PERSONNE_UID", "EMAIL_UID" }))
    @ForeignKey(name = "FK_PERSONNES_EMAILS_PERSONNE")
    @IndexedEmbedded
    private Set<Email> emails;

    /**
     * Resource à laquelle la personne est affectée (créateur).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESSOURCE_UID", nullable = false)
    @IndexedEmbedded
    @ForeignKey(name = "FK_DATA_PERSONNE_DATA_RESSOURCE_RESSOURCE_UID")
    private Ressource ressource;

    /**
     * L'attribution de la personne.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSONNE_ATTRIBUTION_UID", nullable = false)
    @IndexedEmbedded
    @ForeignKey(name = "FK_DATA_PERSONNE_DATA_PERSONNE_ATTRIBUTION_PERSONNE_ATTRIBUTION_UID")
    private PersonneAttribution attribution;

    /**
     * Renvoi la valeur de num.
     * @return num
     */
    public String getNum() {
        return num;
    }

    /**
     * Modifie num.
     * @param num la nouvelle valeur de num
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * Renvoi la valeur de adresses.
     * @return adresses
     */
    public Set<Adresse> getAdresses() {
        return adresses;
    }

    /**
     * Renvoi la valeur de telephones.
     * @return telephones
     */
    public Set<Telephone> getTelephones() {
        return telephones;
    }

    /**
     * Renvoi la valeur de emails.
     * @return emails
     */
    public Set<Email> getEmails() {
        return emails;
    }

    /**
     * Modifie la valeur de ressource.
     * @param ressource the ressource to set
     */
    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    /**
     * Retourne la valeur de ressource.
     * @return the ressource
     */
    public Ressource getRessource() {
        return ressource;
    }

    /**
     * Retourne la valeur de attribution.
     * @return the attribution
     */
    public PersonneAttribution getAttribution() {
        return attribution;
    }

    /**
     * Modifie la valeur de attribution.
     * @param attribution the attribution to set
     */
    public void setAttribution(PersonneAttribution attribution) {
        this.attribution = attribution;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Personne)) {
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
     * Ajoute une adresse a la liste existante.
     * @param adresse la nouvelle adresse à ajouter
     */
    public void addAdresse(Adresse adresse) {
        if (this.adresses == null) {
            this.adresses = new LinkedHashSet<Adresse>();
        }
        adresse.getPersonnes().add(this);
        this.adresses.add(adresse);
    }

    /**
     * Enlève l'adresse de la liste des adresses de la personne.
     * @param adresse l'adresse à retirer de la liste
     */
    public void removeAdresse(Adresse adresse) {
        if (this.adresses != null && adresse != null) {
            adresse.getPersonnes().remove(this);
            this.adresses.remove(adresse);
        }
    }

    /**
     * Ajoute une adresse email a la liste existante.
     * @param email la nouvelle adresse email à ajouter
     */
    public void addEMail(Email email) {
        if (this.emails == null) {
            this.emails = new LinkedHashSet<Email>();
        }
        email.getPersonnes().add(this);
        this.emails.add(email);
    }

    /**
     * Enlève l'email de la liste des emails de la personne.
     * @param email l'email à retirer de la liste
     */
    public void removeEmail(Email email) {
        if (this.emails != null && email != null) {
            email.getPersonnes().remove(this);
            this.emails.remove(email);
        }
    }

    /**
     * Ajoute un numéro de téléphone a la liste existante.
     * @param telephone le nouveau numéro de téléphone à ajouter
     */
    public void addTelephone(Telephone telephone) {
        if (this.telephones == null) {
            this.telephones = new LinkedHashSet<Telephone>();
            telephone.getPersonnes().add(this);
            this.telephones.add(telephone);
        }
        else {
        	final Set<Telephone> nouvelleListeTelephones = new LinkedHashSet<Telephone>();
            for (Telephone tel : this.telephones) {
            	nouvelleListeTelephones.add(tel);
            }
            nouvelleListeTelephones.add(telephone);
            this.telephones = nouvelleListeTelephones;
            telephone.getPersonnes().add(this);
        }
    }

    /**
     * Enlève le téléphone de la liste des téléphones de la personne.
     * @param telephone le téléphone à retirer de la liste
     */
    public void removeTelephone(Telephone telephone) {
        if (this.telephones != null && telephone != null) {
            telephone.getPersonnes().remove(this);
            this.telephones.remove(telephone);
        }
    }
}
