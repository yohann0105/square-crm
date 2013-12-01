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
package com.square.tarificateur.noyau.model.personne;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle d'une personne.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_PERSONNE")
public class Personne extends BaseModel {

    /** serialVersionUID. */
    private static final long serialVersionUID = 628434820286990657L;

    /** EID de la personne (Square). */
    @Column(name = "PERSONNE_EID", nullable = true)
    private Long eidPersonne;

    /** Date de naissance. */
    @Column(name = "DATE_NAISSANCE", nullable = false)
    private Calendar dateNaissance;

    /** Numéro de sécurité unique. */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSONNE_INFO_SANTE_UID")
    @ForeignKey(name = "FK_DATA_PERSONNE_DATA_INFO_SANTE")
    private InfoSante infoSante;

    /** Travailleur non salarié. */
    @Column(name = "TRAVAILLEUR_NON_SALARIE")
    private boolean travailleurNonSalarie;

    /** Loi Madelin. */
    @Column(name = "LOI_MADELIN")
    private boolean loiMadelin;

    /** Actuellement couvert. */
    @Column(name = "ACTUELLEMENT_COUVERT")
    private boolean actuellementCouvert;

    /** Couvert les 6 derniers mois. */
    @Column(name = "COUVERT_SIX_DERNIERS_MOIS")
    private boolean couvertSixDerniersMois;

    /** Adresse principale. */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADRESSE_PRINCIPALE_UID")
    @ForeignKey(name = "FK_DATA_PERSONNE_DATA_ADRESSE_PRINCIPALE")
    private Adresse adressePrincipale;

    /** Liste des bénéficiaires. */
    @OneToMany(mappedBy = "personneSource", cascade = CascadeType.ALL)
    private Set<Beneficiaire> listeBeneficiaires = new HashSet<Beneficiaire>();

    /** Liste des assurés sociaux. */
    @OneToOne(mappedBy = "personne", cascade = CascadeType.ALL)
    private RelationAssureSocial relationAssureSocial;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Personne)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Récupère la valeur de eidPersonne.
     * @return the eidPersonne
     */
    public Long getEidPersonne() {
        return eidPersonne;
    }

    /**
     * Définit la valeur de eidPersonne.
     * @param eidPersonne the eidPersonne to set
     */
    public void setEidPersonne(Long eidPersonne) {
        this.eidPersonne = eidPersonne;
    }

    /**
     * Récupère la valeur de dateNaissance.
     * @return the dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la valeur de dateNaissance.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Récupère la valeur de travailleurNonSalarie.
     * @return the travailleurNonSalarie
     */
    public boolean getTravailleurNonSalarie() {
        return travailleurNonSalarie;
    }

    /**
     * Définit la valeur de travailleurNonSalarie.
     * @param travailleurNonSalarie the travailleurNonSalarie to set
     */
    public void setTravailleurNonSalarie(boolean travailleurNonSalarie) {
        this.travailleurNonSalarie = travailleurNonSalarie;
    }

    /**
     * Récupère la valeur de loiMadelin.
     * @return the loiMadelin
     */
    public boolean getLoiMadelin() {
        return loiMadelin;
    }

    /**
     * Définit la valeur de loiMadelin.
     * @param loiMadelin the loiMadelin to set
     */
    public void setLoiMadelin(boolean loiMadelin) {
        this.loiMadelin = loiMadelin;
    }

    /**
     * Récupère la valeur de actuellementCouvert.
     * @return the actuellementCouvert
     */
    public boolean getActuellementCouvert() {
        return actuellementCouvert;
    }

    /**
     * Définit la valeur de actuellementCouvert.
     * @param actuellementCouvert the actuellementCouvert to set
     */
    public void setActuellementCouvert(boolean actuellementCouvert) {
        this.actuellementCouvert = actuellementCouvert;
    }

    /**
     * Récupère la valeur de couvertSixDerniersMois.
     * @return the couvertSixDerniersMois
     */
    public boolean getCouvertSixDerniersMois() {
        return couvertSixDerniersMois;
    }

    /**
     * Définit la valeur de couvertSixDerniersMois.
     * @param couvertSixDerniersMois the couvertSixDerniersMois to set
     */
    public void setCouvertSixDerniersMois(boolean couvertSixDerniersMois) {
        this.couvertSixDerniersMois = couvertSixDerniersMois;
    }

    /**
     * Récupère la valeur de adressePrincipale.
     * @return the adressePrincipale
     */
    public Adresse getAdressePrincipale() {
        return adressePrincipale;
    }

    /**
     * Définit la valeur de adressePrincipale.
     * @param adressePrincipale the adressePrincipale to set
     */
    public void setAdressePrincipale(Adresse adressePrincipale) {
        this.adressePrincipale = adressePrincipale;
    }

    /**
     * Récupère la valeur de listeBeneficiaires.
     * @return the listeBeneficiaires
     */
    public Set<Beneficiaire> getListeBeneficiaires() {
        return listeBeneficiaires;
    }

    /**
     * Définit la valeur de listeBeneficiaires.
     * @param listeBeneficiaires the listeBeneficiaires to set
     */
    public void setListeBeneficiaires(Set<Beneficiaire> listeBeneficiaires) {
        this.listeBeneficiaires = listeBeneficiaires;
    }

	/**
	 * Récupère le relationAssureSocial.
	 * @return le relationAssureSocial
	 */
	public RelationAssureSocial getRelationAssureSocial() {
		return relationAssureSocial;
	}

	/**
	 * Définit le relationAssureSocial.
	 * @param relationAssureSocial le nouveau relationAssureSocial
	 */
	public void setRelationAssureSocial(RelationAssureSocial relationAssureSocial) {
		this.relationAssureSocial = relationAssureSocial;
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
