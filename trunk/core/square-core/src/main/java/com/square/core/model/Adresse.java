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
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * Entité persistante modélisant les adresses.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@Entity
@Table(name = "DATA_ADRESSE")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "ADRESSE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "ADRESSE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "ADRESSE_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "ADRESSE_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "ADRESSE_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "ADRESSE_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "ADRESSE_SUPPRIME", nullable = false)) })
public class Adresse extends ModelData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -8270384992998665144L;

    /**
     * Numéro de voie de l'adresse.
     */
    @Column(name = "ADRESSE_NUMERO_VOIE")
    @Field(index = Index.TOKENIZED)
    private String numeroVoie;

    /**
     * Complément de nom de l'adresse.
     */
    @Column(name = "ADRESSE_COMPLEMENT_NOM")
    @Field(index = Index.TOKENIZED)
    private String complementNom;

    /**
     * Nom de voie de l'adresse.
     */
    @Column(name = "ADRESSE_NOM_VOIE")
    @Field(index = Index.TOKENIZED)
    private String nomVoie;

    /**
     * Nom de résidence/Batiment de l'adresse.
     */
    @Column(name = "ADRESSE_RESIDENCE_BATIMENT")
    private String residenceBatiment;

    /**
     * Boite postale de l'adresse.
     */
    @Column(name = "ADRESSE_BP_LIEUDIT")
    private String boitePostal;

    /**
     * Indique si la personne habite à l'adresse indiquée.
     */
    @Column(name = "ADRESSE_TOP_NPAI", nullable = false)
    private boolean topNpai;

    /**
     * Date de début.
     */
    @Column(name = "ADRESSE_DATE_DEBUT", nullable = false)
    private Calendar dateDebut;

    /**
     * Date de fin.
     */
    @Column(name = "ADRESSE_DATE_FIN")
    private Calendar dateFin;

    /**
     * Clé étrangère vers la table CLASSEMENT.
     */
    @ManyToOne()
    @JoinColumn(name = "ADRESSE_NATURE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_ADRESSE_DIM_ADRESSE_NATURE_ADRESSE_NATURE_UID")
    private AdresseNature nature;

    /**
     * Clé étrangère vers la table PAYS.
     */
    @ManyToOne()
    @JoinColumn(name = "PAYS_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_ADRESSE_DIM_PAYS_PAYS_UID")
    private Pays pays;

    /**
     * Clé étrangère vers la table TYPEVOIE.
     */
    @ManyToOne()
    @JoinColumn(name = "ADRESSE_TYPE_VOIE_UID")
    @IndexedEmbedded
    @ForeignKey(name = "FK_DATA_ADRESSE_DIM_ADRESSE_TYPE_VOIE_ADRESSE_TYPE_VOIE_UID")
    private TypeVoie typeVoie;

    /**
     * Nom de ville si étranger.
     */
    @Column(name = "ADRESSE_COMMUNE_ETRANGER")
    private String communeEtranger;

    /**
     * Code postal si étranger.
     */
    @Column(name = "ADRESSE_CODE_POSTAL_ETRANGER")
    private String codePostalEtranger;

    /** Code postal et commune de l'adresse. */
    @ManyToOne
    @JoinColumn(name = "CODE_POSTAL_COMMUNE_UID")
    @IndexedEmbedded
    @ForeignKey(name = "FK_DATA_ADRESSE_DIM_CODE_POSTAL_COMMUNE_UID")
    private CodePostalCommune codePostalCommune;

    /** La liste des personnes résidant à cette adresse. */
    @ManyToMany(cascade = {CascadeType.ALL }, mappedBy = "adresses", targetEntity = Personne.class)
    @ForeignKey(name = "FK_PERSONNES_ADRESSES_ADRESSE")
    @ContainedIn
    private Set<Personne> personnes;

    /** Identifiant du porteur de l'adresse. */
    @Column(name = "PERSONNE_UID")
    private Long porteurUid;

    /**
     * Retourne la valeur de numeroVoie.
     * @return the numeroVoie
     */
    public String getNumeroVoie() {
        return numeroVoie;
    }

    /**
     * Modifie la valeur de numeroVoie.
     * @param numeroVoie the numeroVoie to set
     */
    public void setNumeroVoie(String numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    /**
     * Retourne la valeur de complementNom.
     * @return the complementNom
     */
    public String getComplementNom() {
        return complementNom;
    }

    /**
     * Modifie la valeur de complementNom.
     * @param complementNom the complementNom to set
     */
    public void setComplementNom(String complementNom) {
        this.complementNom = complementNom;
    }

    /**
     * Retourne la valeur de nomVoie.
     * @return the nomVoie
     */
    public String getNomVoie() {
        return nomVoie;
    }

    /**
     * Modifie la valeur de nomVoie.
     * @param nomVoie the nomVoie to set
     */
    public void setNomVoie(String nomVoie) {
        this.nomVoie = nomVoie;
    }

    /**
     * Retourne la valeur de residenceBatiment.
     * @return the residenceBatiment
     */
    public String getResidenceBatiment() {
        return residenceBatiment;
    }

    /**
     * Modifie la valeur de residenceBatiment.
     * @param residenceBatiment the residenceBatiment to set
     */
    public void setResidenceBatiment(String residenceBatiment) {
        this.residenceBatiment = residenceBatiment;
    }

    /**
     * Retourne la valeur de boitePostal.
     * @return the boitePostal
     */
    public String getBoitePostal() {
        return boitePostal;
    }

    /**
     * Modifie la valeur de boitePostal.
     * @param boitePostal the boitePostal to set
     */
    public void setBoitePostal(String boitePostal) {
        this.boitePostal = boitePostal;
    }

    /**
     * Retourne la valeur de topNpai.
     * @return the topNpai
     */
    public boolean getTopNpai() {
        return topNpai;
    }

    /**
     * Modifie la valeur de topNpai.
     * @param topNpai the topNpai to set
     */
    public void setTopNpai(boolean topNpai) {
        this.topNpai = topNpai;
    }

    /**
     * Retourne la valeur de dateDebut.
     * @return the dateDebut
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * Modifie la valeur de dateDebut.
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Retourne la valeur de dateFin.
     * @return the dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Modifie la valeur de dateFin.
     * @param dateFin the dateFin to set
     */
    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
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
     * Retourne la valeur de typeVoie.
     * @return the typeVoie
     */
    public TypeVoie getTypeVoie() {
        return typeVoie;
    }

    /**
     * Modifie la valeur de typeVoie.
     * @param typeVoie the typeVoie to set
     */
    public void setTypeVoie(TypeVoie typeVoie) {
        this.typeVoie = typeVoie;
    }

    /**
     * Renvoi la valeur de nature.
     * @return nature
     */
    public AdresseNature getNature() {
        return nature;
    }

    /**
     * Modifie nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(AdresseNature nature) {
        this.nature = nature;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Adresse)) {
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
     * Récupération du nom de la commune a l'étranger.
     * @return the communeEtranger
     */
    public String getCommuneEtranger() {
        return communeEtranger;
    }

    /**
     * Définition du nom de la commune a l'étranger.
     * @param communeEtranger the communeEtranger to set
     */
    public void setCommuneEtranger(String communeEtranger) {
        this.communeEtranger = communeEtranger;
    }

    /**
     * Récupération du code postal a l'étranger.
     * @return the codePostalEtranger
     */
    public String getCodePostalEtranger() {
        return codePostalEtranger;
    }

    /**
     * Définition du code postal a l'étranger.
     * @param codePostalEtranger the codePostalEtranger to set
     */
    public void setCodePostalEtranger(String codePostalEtranger) {
        this.codePostalEtranger = codePostalEtranger;
    }

    /**
     * Récupère la valeur codePostalCommune.
     * @return the codePostalCommune
     */
    public CodePostalCommune getCodePostalCommune() {
        return codePostalCommune;
    }

    /**
     * Définit la valeur de codePostalCommune.
     * @param codePostalCommune the codePostalCommune to set
     */
    public void setCodePostalCommune(CodePostalCommune codePostalCommune) {
        this.codePostalCommune = codePostalCommune;
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
