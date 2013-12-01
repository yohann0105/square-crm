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
package com.square.tarificateur.noyau.model.opportunite;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.SimpleBaseModel;

import com.square.tarificateur.noyau.model.personne.Personne;

/**
 * Modèle du devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_DEVIS")
@javax.persistence.SequenceGenerator(name = "SEQ_DEVIS", sequenceName = "sequence_devis", allocationSize = 1)
public class Devis extends SimpleBaseModel {

    /** SerialVersionUID. */
    private static final long serialVersionUID = -7555537503497529496L;

    /** Identifiant Clé primaire par defaut. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DEVIS")
    @Column(name = "ID")
    private Long id;

    /** Opportunité. */
    @ManyToOne
    @JoinColumn(name = "OPPORTUNITE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_DEVIS_DATA_OPPORTUNITE")
    private Opportunite opportunite;

    /** EID du créateur. */
    @Column(name = "CREATEUR_EID")
    private Long eidCreateur;

    /** Personne principale du devis. */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PERSONNE_PRINCIPALE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_DEVIS_DATA_PERSONNE")
    private Personne personnePrincipale;

    /** Liste des lignes de devis de ce devis. */
    @OneToMany(mappedBy = "devis", cascade = CascadeType.REMOVE)
    private Set<LigneDevis> listeLigneDevis = new HashSet<LigneDevis>();

    /** Date de création du devis. */
    @Column(name = "DATE_CREATION")
    private Calendar dateCreation;

    /** Date de clôture du devis. */
    @Column(name = "DATE_CLOTURE")
    private Calendar dateCloture;

    /** Finalité. */
    @ManyToOne
    @JoinColumn(name = "FINALITE_UID")
    @ForeignKey(name = "FK_DATA_DEVIS_DIM_FINALITE_UID")
    private Finalite finalite;

    /** TODO Mutuelle Complémentaire. */

    /** Motif. */
    @ManyToOne
    @JoinColumn(name = "MOTIF_UID")
    @ForeignKey(name = "FK_DATA_DEVIS_DIM_MOTIF_UID")
    private MotifDevis motif;

    /** Flag indiquant que la famille a été modifié. */
    @Column(name = "FLAG_FAMILLE_MODIFIEE")
    private Boolean familleModifiee = Boolean.FALSE;

    /** Flag indiquant que le devis est en lecture seule. */
    @Column(name = "LECTURE_SEULE")
    private Boolean lectureSeule = Boolean.FALSE;

    /** Flag indiquant que le devis proviens du site internet. */
    @Column(name = "ORIGINE_SITE_WEB")
    private Boolean origineSiteWeb = Boolean.FALSE;

    /** EId de la relation dans le cadre d'un parrainage. */
    @Column(name = "EID_RELATION_PARRAIN")
    private Long eidRelationParrain;

    /** Liste des assurés sociaux. */
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "DATA_DEVIS_ASSURE_SOCIAL", joinColumns = @JoinColumn(name = "DEVIS_UID"), inverseJoinColumns = @JoinColumn(name = "ASSURE_SOCIAL_UID"))
    private Set<Personne> listeAssuresSociaux = new HashSet<Personne>();

    /**
     * Récupère la valeur de id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la valeur de eidCreateur.
     * @return the eidCreateur
     */
    public Long getEidCreateur() {
        return eidCreateur;
    }

    /**
     * Définit la valeur de eidCreateur.
     * @param eidCreateur the eidCreateur to set
     */
    public void setEidCreateur(Long eidCreateur) {
        this.eidCreateur = eidCreateur;
    }

    /**
     * Getter function.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Récupère la valeur de dateCloture.
     * @return the dateCloture
     */
    public Calendar getDateCloture() {
        return dateCloture;
    }

    /**
     * Définit la valeur de dateCloture.
     * @param dateCloture the dateCloture to set
     */
    public void setDateCloture(Calendar dateCloture) {
        this.dateCloture = dateCloture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Devis)) {
            return false;
        }
        final Devis otherObj = (Devis) other;
        return this.getId() == null && otherObj.getId() != null ? false : this.getId() != null && otherObj.getId() == null ? false : this.getId() == null
            && otherObj.getId() == null ? this == other : this.getId().equals(otherObj.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Récupère la valeur de personnePrincipale.
     * @return the personnePrincipale
     */
    public Personne getPersonnePrincipale() {
        return personnePrincipale;
    }

    /**
     * Définit la valeur de personnePrincipale.
     * @param personnePrincipale the personnePrincipale to set
     */
    public void setPersonnePrincipale(Personne personnePrincipale) {
        this.personnePrincipale = personnePrincipale;
    }

    /**
     * Récupère la valeur de motif.
     * @return the motif
     */
    public MotifDevis getMotif() {
        return motif;
    }

    /**
     * Définit la valeur de motif.
     * @param motif the motif to set
     */
    public void setMotif(MotifDevis motif) {
        this.motif = motif;
    }

    /**
     * Récupère la valeur de opportunite.
     * @return the opportunite
     */
    public Opportunite getOpportunite() {
        return opportunite;
    }

    /**
     * Définit la valeur de opportunite.
     * @param opportunite the opportunite to set
     */
    public void setOpportunite(Opportunite opportunite) {
        this.opportunite = opportunite;
    }

    /**
     * Récupère la valeur de finalite.
     * @return the finalite
     */
    public Finalite getFinalite() {
        return finalite;
    }

    /**
     * Définit la valeur de finalite.
     * @param finalite the finalite to set
     */
    public void setFinalite(Finalite finalite) {
        this.finalite = finalite;
    }

    /**
     * Récupère la valeur de listeLigneDevis.
     * @return the listeLigneDevis
     */
    public Set<LigneDevis> getListeLigneDevis() {
        if (listeLigneDevis == null) {
            listeLigneDevis = new HashSet<LigneDevis>();
        }
        return listeLigneDevis;
    }

    /**
     * Définit la valeur de listeLigneDevis.
     * @param listeLigneDevis the listeLigneDevis to set
     */
    public void setListeLigneDevis(Set<LigneDevis> listeLigneDevis) {
        this.listeLigneDevis = listeLigneDevis;
    }

    /**
     * Récupère la valeur de lectureSeule.
     * @return the lectureSeule
     */
    public Boolean getLectureSeule() {
        return lectureSeule;
    }

    /**
     * Définit la valeur de lectureSeule.
     * @param lectureSeule the lectureSeule to set
     */
    public void setLectureSeule(Boolean lectureSeule) {
        this.lectureSeule = lectureSeule;
    }

    /**
     * Récupère la valeur de familleModifiee.
     * @return la valeur de familleModifiee
     */
    public Boolean getFamilleModifiee() {
        return familleModifiee;
    }

    /**
     * Définit la valeur de familleModifiee.
     * @param familleModifiee la nouvelle valeur de familleModifiee
     */
    public void setFamilleModifiee(Boolean familleModifiee) {
        this.familleModifiee = familleModifiee;
    }

    /**
     * Recuperer la valeur.
     * @return the origineSiteWeb
     */
    public Boolean getOrigineSiteWeb() {
        return origineSiteWeb;
    }

    /**
     * Fixer la valeur.
     * @param origineSiteWeb the origineSiteWeb to set
     */
    public void setOrigineSiteWeb(Boolean origineSiteWeb) {
        this.origineSiteWeb = origineSiteWeb;
    }

    /**
     * Get the eidRelationParrain value.
     * @return the eidRelationParrain
     */
    public Long getEidRelationParrain() {
        return eidRelationParrain;
    }

    /**
     * Set the eidRelationParrain value.
     * @param eidRelationParrain the eidRelationParrain to set
     */
    public void setEidRelationParrain(Long eidRelationParrain) {
        this.eidRelationParrain = eidRelationParrain;
    }

    /**
     * Get the listeAssuresSociaux value.
     * @return the listeAssuresSociaux
     */
    public Set<Personne> getListeAssuresSociaux() {
        return listeAssuresSociaux;
    }

    /**
     * Set the listeAssuresSociaux value.
     * @param listeAssuresSociaux the listeAssuresSociaux to set
     */
    public void setListeAssuresSociaux(Set<Personne> listeAssuresSociaux) {
        this.listeAssuresSociaux = listeAssuresSociaux;
    }
}
