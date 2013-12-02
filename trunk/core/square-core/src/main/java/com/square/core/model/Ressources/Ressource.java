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
package com.square.core.model.Ressources;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.square.core.model.ModelData;

/**
 * Entité persistante pour les ressources.
 * @author cblanchard - SCUB
 */
@Entity
@Table(name = "DATA_RESSOURCE")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "RESSOURCE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "RESSOURCE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "RESSOURCE_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "RESSOURCE_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "RESSOURCE_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "RESSOURCE_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "RESSOURCE_SUPPRIME", nullable = false)) })
public class Ressource extends ModelData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -3143179088486938375L;

    /**
     * Nom de la ressource.
     */
    @Column(name = "RESSOURCE_NOM", nullable = false)
    @Fields({@Field(index = Index.TOKENIZED), @Field(name = "triNom", index = Index.UN_TOKENIZED, store = Store.YES) })
    private String nom;

    /**
     * Prénom de la ressource.
     */
    @Column(name = "RESSOURCE_PRENOM", nullable = false)
    @Fields({@Field(index = Index.TOKENIZED), @Field(name = "triPrenom", index = Index.UN_TOKENIZED, store = Store.YES) })
    private String prenom;

    /**
     * Service d'une ressource.
     */
    @ManyToOne()
    @JoinColumn(name = "RESSOURCE_SERVICE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_RESSOURCE_DIM_RESSOURCE_SERVICE_RESSOURCE_FONCTION_UID")
    @IndexedEmbedded
    private RessourceService service;

    /**
     * Agence de la ressource.
     */
    @ManyToOne()
    @JoinColumn(name = "AGENCE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_RESSOURCE_DATA_AGENCE_AGENCE_UID")
    @IndexedEmbedded
    private Agence agence;

    /**
     * Fonction de la ressource.
     */
    @ManyToOne()
    @JoinColumn(name = "RESSOURCE_FONCTION_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_RESSOURCE_DIM_RESSOURCE_FONCTION_RESSOURCE_FONCTION_UID")
    @IndexedEmbedded
    private RessourceFonction fonction;

    /**
     * Etat de la ressource.
     */
    @ManyToOne()
    @JoinColumn(name = "RESSOURCE_ETAT_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_RESSOURCE_DIM_RESSOURCE_ETAT_RESSOURCE_ETAT_UID")
    @IndexedEmbedded
    private RessourceEtat etat;

    /**
     * L'email de la ressource.
     */
    @Column(name = "RESSOURCE_EMAIL")
    private String email;

    /**
     * Renvoi la valeur de nom.
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoi la valeur de fonction.
     * @return fonction
     */
    public RessourceFonction getFonction() {
        return fonction;
    }

    /**
     * Modifie fonction.
     * @param fonction la nouvelle valeur de fonction
     */
    public void setFonction(RessourceFonction fonction) {
        this.fonction = fonction;
    }

    /**
     * Retourne la valeur de service.
     * @return the service
     */
    public RessourceService getService() {
        return service;
    }

    /**
     * Modifie la valeur de service.
     * @param service the service to set
     */
    public void setService(RessourceService service) {
        this.service = service;
    }

    /**
     * Retourne la valeur de etat.
     * @return the etat
     */
    public RessourceEtat getEtat() {
        return etat;
    }

    /**
     * Modifie la valeur de etat.
     * @param etat the etat to set
     */
    public void setEtat(RessourceEtat etat) {
        this.etat = etat;
    }

    /**
     * Renvoi la valeur de agence.
     * @return agence
     */
    public Agence getAgence() {
        return agence;
    }

    /**
     * Modifie agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Ressource)) {
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
     * Récupération de prenom.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définition de prenom.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Définition de nom.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la valeur de email.
     * @return la valeur de email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit la valeur de email.
     * @param email la nouvelle valeur de email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
