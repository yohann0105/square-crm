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
package com.square.composant.tarificateur.square.client.model.adhesion;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.tarificateur.square.client.model.personne.InfoSanteModel;

/**
 * DTO modélisant les informations supplémentaires d'une personne nécessaires à une adhésion.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class InfosPersonneModel implements IsSerializable {

    /** Identifiant de la personne. */
    private Long id;

    /** EID de la personne. */
    private Long eidPersonne;

    /** Nom de la personne. */
    private String nom;

    /** Prénom de la personne. */
    private String prenom;

    /** Numéro de client. */
    private String numClient;

    /** Type de relation avec un assuré social. */
    private IdentifiantLibelleGwt typeRelationAssureSocial;

    /** Lien familial (null si personne principale). */
    private IdentifiantLibelleGwt lienFamilial;

    /** Info santé. */
    private InfoSanteModel infoSante;

    /** Travailleur non salarié. */
    private boolean travailleurNonSalarie;

    /** Loi Madelin. */
    private boolean loiMadelin;

    /** Actuellement couvert. */
    private boolean actuellementCouvert;

    /** Couvert les 6 derniers mois. */
    private boolean couvertSixDerniersMois;

    /**
     * Récupère la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la valeur de eidPersonne.
     * @return la valeur de eidPersonne
     */
    public Long getEidPersonne() {
        return eidPersonne;
    }

    /**
     * Définit la valeur de eidPersonne.
     * @param eidPersonne la nouvelle valeur de eidPersonne
     */
    public void setEidPersonne(Long eidPersonne) {
        this.eidPersonne = eidPersonne;
    }

    /**
     * Récupère la valeur de nom.
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la valeur de prenom.
     * @return la valeur de prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit la valeur de prenom.
     * @param prenom la nouvelle valeur de prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère la valeur de lienFamilial.
     * @return la valeur de lienFamilial
     */
    public IdentifiantLibelleGwt getLienFamilial() {
        return lienFamilial;
    }

    /**
     * Définit la valeur de lienFamilial.
     * @param lienFamilial la nouvelle valeur de lienFamilial
     */
    public void setLienFamilial(IdentifiantLibelleGwt lienFamilial) {
        this.lienFamilial = lienFamilial;
    }

    /**
     * Récupère la valeur de travailleurNonSalarie.
     * @return la valeur de travailleurNonSalarie
     */
    public boolean isTravailleurNonSalarie() {
        return travailleurNonSalarie;
    }

    /**
     * Définit la valeur de travailleurNonSalarie.
     * @param travailleurNonSalarie la nouvelle valeur de travailleurNonSalarie
     */
    public void setTravailleurNonSalarie(boolean travailleurNonSalarie) {
        this.travailleurNonSalarie = travailleurNonSalarie;
    }

    /**
     * Récupère la valeur de loiMadelin.
     * @return la valeur de loiMadelin
     */
    public boolean isLoiMadelin() {
        return loiMadelin;
    }

    /**
     * Définit la valeur de loiMadelin.
     * @param loiMadelin la nouvelle valeur de loiMadelin
     */
    public void setLoiMadelin(boolean loiMadelin) {
        this.loiMadelin = loiMadelin;
    }

    /**
     * Récupère la valeur de actuellementCouvert.
     * @return la valeur de actuellementCouvert
     */
    public boolean isActuellementCouvert() {
        return actuellementCouvert;
    }

    /**
     * Définit la valeur de actuellementCouvert.
     * @param actuellementCouvert la nouvelle valeur de actuellementCouvert
     */
    public void setActuellementCouvert(boolean actuellementCouvert) {
        this.actuellementCouvert = actuellementCouvert;
    }

    /**
     * Récupère la valeur de couvertSixDerniersMois.
     * @return la valeur de couvertSixDerniersMois
     */
    public boolean isCouvertSixDerniersMois() {
        return couvertSixDerniersMois;
    }

    /**
     * Définit la valeur de couvertSixDerniersMois.
     * @param couvertSixDerniersMois la nouvelle valeur de couvertSixDerniersMois
     */
    public void setCouvertSixDerniersMois(boolean couvertSixDerniersMois) {
        this.couvertSixDerniersMois = couvertSixDerniersMois;
    }

    /**
     * Get the infoSante value.
     * @return the infoSante
     */
    public InfoSanteModel getInfoSante() {
        return infoSante;
    }

    /**
     * Set the infoSante value.
     * @param infoSante the infoSante to set
     */
    public void setInfoSante(InfoSanteModel infoSante) {
        this.infoSante = infoSante;
    }

    /**
     * Retourne la valeur de numClient.
     * @return the numClient
     */
    public String getNumClient() {
        return numClient;
    }

    /**
     * Modifie la valeur de numClient.
     * @param numClient the numClient to set
     */
    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

	/**
	 * Récupère le typeRelationAssureSocial.
	 * @return le typeRelationAssureSocial
	 */
	public IdentifiantLibelleGwt getTypeRelationAssureSocial() {
		return typeRelationAssureSocial;
	}

	/**
	 * Définit le typeRelationAssureSocial.
	 * @param typeRelationAssureSocial le nouveau typeRelationAssureSocial
	 */
	public void setTypeRelationAssureSocial(IdentifiantLibelleGwt typeRelationAssureSocial) {
		this.typeRelationAssureSocial = typeRelationAssureSocial;
	}
}
