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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.square.core.util.lucene.DateNaissanceBridge;

/**
 * Entité persostante modélisant les personnes physiques.
 * @author cblanchard - SCUB
 */
@Entity
@Table(name = "DATA_PERSONNE_PHYSIQUE")
@PrimaryKeyJoinColumn(name = "PERSONNE_UID")
@Indexed
public class PersonnePhysique extends Personne {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -7517122941973157357L;

    /**
     * Nom du client.
     */
    @Column(name = "PERSONNE_NOM")
    @Fields({@Field(index = Index.TOKENIZED), @Field(name = "triNom", index = Index.TOKENIZED, store = Store.YES) })
    private String nom;

    /**
     * Nom de jeunne fille du client.
     */
    @Field(index = Index.TOKENIZED)
    @Column(name = "PERSONNE_NOM_JF")
    private String nomJeuneFille;

    /**
     * Prénom du client.
     */
    @Fields({@Field(index = Index.TOKENIZED), @Field(name = "triPrenom", index = Index.TOKENIZED, store = Store.YES) })
    @Column(name = "PERSONNE_PRENOM")
    private String prenom;

    /**
     * Date de naissance du client.
     */
    @Column(name = "PERSONNE_DATE_NAISSANCE")
    @Field(index = Index.UN_TOKENIZED)
    @FieldBridge(impl = DateNaissanceBridge.class)
    private Calendar dateNaissance;

    /**
     * Date de déces de la personne.
     */
    @Column(name = "PERSONNE_DATE_DECES")
    private Calendar dateDeces;

    /**
     * Permet de savoir si la personne est décédée.
     */
    @Column(name = "PERSONNE_DECES")
    private boolean deces;

    /**
     * Nombre d'enfants de la personne.
     */
    @Column(name = "PERSONNE_NB_ENFANTS")
    private int nbEnfants;

    /**
     * Segement de la personne.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_SEGMENT_UID")
    @ForeignKey(name = "FK_DATA_PERSONNE_PHYSIQUE_DIM_SEGMENT_SEGMENT_UID")
    @IndexedEmbedded
    private PersonneSegment segment;

    /**
     * CSP de la personne.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_CSP_UID")
    @ForeignKey(name = "FK_DATA_PERSONNE_PHYSIQUE_DIM_PERSONNE_CSP_PERSONNE_CSP_UID")
    private PersonneCSP csp;

    /**
     * Situation familiale de la personne.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_SITFAM_UID")
    @ForeignKey(name = "FK_DATA_PERSONNE_PHYSIQUE_DIM_PERSONNE_SITFAM_PERSONNE_SITFAM_UID")
    private PersonneSituationFamiliale situationFamiliale;

    /**
     * Civilité de la personne.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_CIVILITE_UID")
    @ForeignKey(name = "FK_DATA_PERSONNE_PHYSIQUE_DIM_PERSONNE_CIVILITE_UID_PERSONNE_CIVILITE_UID")
    private PersonneCivilite civilite;

    /**
     * Réseau de la personne.
     */
    @ManyToOne()
    @JoinColumn(name = "RESEAU_UID")
    @ForeignKey(name = "FK_DATA_PERSONNE_PHYSIQUE_DIM_RESEAU_RESEAU_UID")
    private PersonneReseau reseau;

    /**
     * Statut de la personne.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_STATUT_UID")
    @ForeignKey(name = "FK_DATA_PERSONNE_PHYSIQUE_DIM_PERSONNE_STATUT_PERSONNE_STATUT_UID")
    private PersonneStatut statut;

    /**
     * Situation professionnel de la personne.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_PROFESSION_UID")
    @ForeignKey(name = "FK_DATA_PERSONNE_PHYSIQUE_DIM_PERSONNE_PROFESSION_PERSONNE_PROFESSION_UID")
    private PersonneProfession profession;

    /**
     * Nature de la personne.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_P_NATURE_UID")
    @IndexedEmbedded
    @ForeignKey(name = "FK_DATA_PERSONNE_PHYSIQUE_DIM_PERSONNE_PHYSIQUE_NATURE_PERSONNE_P_NATURE_UID")
    private PersonnePhysiqueNature nature;

    /**
     * Flag pour savoir si la personne refuse d'etre contacté.
     */
    @Column(name = "PERSONNE_REFUSE_ETRE_CONTACTE")
    private Boolean refuseEtreContacte = Boolean.TRUE;

    /**
     * Numéro de sécurité unique.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSONNE_INFO_SANTE_UID")
    @IndexedEmbedded
    @ForeignKey(name = "FK_DATA_PERSONNE_DATA_INFO_SANTE_UNIQUE")
    private InfoSante infoSante;

    /**
     * Renvoi la valeur de nom.
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoi la valeur de nomJeuneFille.
     * @return nomJeuneFille
     */
    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    /**
     * Modifie nomJeuneFille.
     * @param nomJeuneFille la nouvelle valeur de nomJeuneFille
     */
    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    /**
     * Renvoi la valeur de prenom.
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie prenom.
     * @param prenom la nouvelle valeur de prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Renvoi la valeur de dateNaissance.
     * @return dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Renvoi la valeur de dateDeces.
     * @return dateDeces
     */
    public Calendar getDateDeces() {
        return dateDeces;
    }

    /**
     * Modifie dateDeces.
     * @param dateDeces la nouvelle valeur de dateDeces
     */
    public void setDateDeces(Calendar dateDeces) {
        this.dateDeces = dateDeces;
    }

    /**
     * Renvoi la valeur de deces.
     * @return deces
     */
    public boolean isDeces() {
        return deces;
    }

    /**
     * Modifie deces.
     * @param deces la nouvelle valeur de deces
     */
    public void setDeces(boolean deces) {
        this.deces = deces;
    }

    /**
     * Renvoi la valeur de segment.
     * @return segment
     */
    public PersonneSegment getSegment() {
        return segment;
    }

    /**
     * Modifie segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(PersonneSegment segment) {
        this.segment = segment;
    }

    /**
     * Renvoi la valeur de csp.
     * @return csp
     */
    public PersonneCSP getCsp() {
        return csp;
    }

    /**
     * Modifie csp.
     * @param csp la nouvelle valeur de csp
     */
    public void setCsp(PersonneCSP csp) {
        this.csp = csp;
    }

    /**
     * Renvoi la valeur de situationFamiliale.
     * @return situationFamiliale
     */
    public PersonneSituationFamiliale getSituationFamiliale() {
        return situationFamiliale;
    }

    /**
     * Modifie situationFamiliale.
     * @param situationFamiliale la nouvelle valeur de situationFamiliale
     */
    public void setSituationFamiliale(PersonneSituationFamiliale situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    /**
     * Renvoi la valeur de civilite.
     * @return civilite
     */
    public PersonneCivilite getCivilite() {
        return civilite;
    }

    /**
     * Modifie civilite.
     * @param civilite la nouvelle valeur de civilite
     */
    public void setCivilite(PersonneCivilite civilite) {
        this.civilite = civilite;
    }

    /**
     * Renvoi la valeur de reseau.
     * @return reseau
     */
    public PersonneReseau getReseau() {
        return reseau;
    }

    /**
     * Modifie reseau.
     * @param reseau la nouvelle valeur de reseau
     */
    public void setReseau(PersonneReseau reseau) {
        this.reseau = reseau;
    }

    /**
     * Renvoi la valeur de statut.
     * @return statut
     */
    public PersonneStatut getStatut() {
        return statut;
    }

    /**
     * Modifie statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(PersonneStatut statut) {
        this.statut = statut;
    }

    /**
     * Renvoi la valeur de nature.
     * @return nature
     */
    public PersonnePhysiqueNature getNature() {
        return nature;
    }

    /**
     * Modifie nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(PersonnePhysiqueNature nature) {
        this.nature = nature;
    }

    /**
     * Renvoi la valeur de nbEnfants.
     * @return nbEnfants
     */
    public int getNbEnfants() {
        return nbEnfants;
    }

    /**
     * Modifie nbEnfants.
     * @param nbEnfants la nouvelle valeur de nbEnfants
     */
    public void setNbEnfants(int nbEnfants) {
        this.nbEnfants = nbEnfants;
    }

    /**
     * Renvoi la valeur de profession.
     * @return profession
     */
    public PersonneProfession getProfession() {
        return profession;
    }

    /**
     * Modifie profession.
     * @param profession la nouvelle valeur de profession
     */
    public void setProfession(PersonneProfession profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PersonnePhysique)) {
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
     * Get the refuseEtreContacte value.
     * @return the refuseEtreContacte
     */
    public Boolean getRefuseEtreContacte() {
        return refuseEtreContacte;
    }

    /**
     * Set the refuseEtreContacte value.
     * @param refuseEtreContacte the refuseEtreContacte to set
     */
    public void setRefuseEtreContacte(Boolean refuseEtreContacte) {
        this.refuseEtreContacte = refuseEtreContacte;
    }

    /**
     * Get the infoSante value.
     * @return the infoSante
     */
    public InfoSante getInfoSante() {
        return infoSante;
    }

    /**
     * Set the infoSante value.
     * @param infoSante the infoSante to set
     */
    public void setInfoSante(InfoSante infoSante) {
        this.infoSante = infoSante;
    }

}
