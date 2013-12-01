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
package com.square.tarificateur.noyau.dto.personne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.square.tarificateur.noyau.dto.InfoSanteCreationDto;

/**
 * DTO modélisant une personne du tarificateur.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class PersonneTarificateurDto implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -2555101001815979680L;

    /** Identifiant de la personne. */
    private Long id;

    /** EID de la personne (Square). */
    private Long eidPersonne;

    /** Date de naissance. */
    private Calendar dateNaissance;

    /** Info santé. */
    private InfoSanteCreationDto infoSante;

    /** Travailleur non salarié. */
    private boolean travailleurNonSalarie;

    /** Loi Madelin. */
    private boolean loiMadelin;

    /** Actuellement couvert. */
    private boolean actuellementCouvert;

    /** Couvert les 6 derniers mois. */
    private boolean couvertSixDerniersMois;

    /** Adresse principale. */
    private AdresseTarificateurDto adressePrincipale;

    /** Liste des bénéficiaires. */
    private List<BeneficiaireTarificateurDto> listeBeneficiaires;

    /** EID Nature (Square). */
    private Long eidNature;

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
     * Récupère la valeur de dateNaissance.
     * @return la valeur de dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la valeur de dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
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
     * Récupère la valeur de adressePrincipale.
     * @return la valeur de adressePrincipale
     */
    public AdresseTarificateurDto getAdressePrincipale() {
        return adressePrincipale;
    }

    /**
     * Définit la valeur de adressePrincipale.
     * @param adressePrincipale la nouvelle valeur de adressePrincipale
     */
    public void setAdressePrincipale(AdresseTarificateurDto adressePrincipale) {
        this.adressePrincipale = adressePrincipale;
    }

    /**
     * Récupère la valeur de listeBeneficiaires.
     * @return la valeur de listeBeneficiaires
     */
    public List<BeneficiaireTarificateurDto> getListeBeneficiaires() {
        if (listeBeneficiaires == null) {
            listeBeneficiaires = new ArrayList<BeneficiaireTarificateurDto>();
        }
        return listeBeneficiaires;
    }

    /**
     * Définit la valeur de listeBeneficiaires.
     * @param listeBeneficiaires la nouvelle valeur de listeBeneficiaires
     */
    public void setListeBeneficiaires(List<BeneficiaireTarificateurDto> listeBeneficiaires) {
        this.listeBeneficiaires = listeBeneficiaires;
    }

	/**
	 * Récupère la valeur eidNature.
	 * @return the eidNature
	 */
	public Long getEidNature() {
		return eidNature;
	}

	/**
	 * Définit la valeur de eidNature.
	 * @param eidNature the eidNature to set
	 */
	public void setEidNature(Long eidNature) {
		this.eidNature = eidNature;
	}

    /**
     * Get the infoSante value.
     * @return the infoSante
     */
    public InfoSanteCreationDto getInfoSante() {
        return infoSante;
    }

    /**
     * Set the infoSante value.
     * @param infoSante the infoSante to set
     */
    public void setInfoSante(InfoSanteCreationDto infoSante) {
        this.infoSante = infoSante;
    }
}
