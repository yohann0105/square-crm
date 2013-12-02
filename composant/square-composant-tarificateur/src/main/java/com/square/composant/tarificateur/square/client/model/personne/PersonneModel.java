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
package com.square.composant.tarificateur.square.client.model.personne;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model Gwt modélisant une personne.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class PersonneModel implements IsSerializable {

    /** Identifiant de la personne. */
    private Long id;

    /** EID de la personne (Square). */
    private Long eidPersonne;

    /** Eid Civilité (Square). */
    private Long eidCivilite;

    /** Nom. */
    private String nom;

    /** Nom de jeune fille. */
    private String nomJeuneFille;

    /** Prénom. */
    private String prenom;

    /** Date de naissance. */
    private String dateNaissance;

    /** Info santé. */
    private InfoSanteCreationModel infoSante;

    /** Travailleur non salarié. */
    private boolean travailleurNonSalarie;

    /** Loi Madelin. */
    private boolean loiMadelin;

    /** Actuellement couvert. */
    private boolean actuellementCouvert;

    /** Couvert les 6 derniers mois. */
    private boolean couvertSixDerniersMois;

    /** Eid Catégorie socio-professionnelle (Square). */
    private Long eidCategorieSocioProf;

    /** Eid Situation familiale (Square). */
    private Long eidSituationFamiliale;

    /** Eid Statut (Square). */
    private Long eidStatut;

    /** Eid Nature (Square). */
    private Long eidNature;

    /** Adresse principale. */
    private AdresseModel adressePrincipale;

    /** Adresse secondaire. */
    private AdresseModel adresseSecondaire;

    /** Numéro de téléphone fixe. */
    private TelephoneModel telephoneFixe;

    /** Numéro de téléphone portable. */
    private TelephoneModel telephonePortable;

    /** Numéro de téléphone bureau. */
    private TelephoneModel telephoneBureau;

    /** Numéro de téléphone fax. */
    private TelephoneModel telephoneFax;

    /** Email. */
    private EmailModel email;

    /** Liste des bénéficiaires. */
    private List<BeneficiaireModel> listeBeneficiaires;

    /**
     * Getter function.
     * @return the eidPersonne
     */
    public Long getEidPersonne() {
        return eidPersonne;
    }

    /**
     * Getter function.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter function.
     * @return the nomJeuneFille
     */
    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    /**
     * Getter function.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Getter function.
     * @return the dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Getter function.
     * @return the travailleurNonSalarie
     */
    public boolean isTravailleurNonSalarie() {
        return travailleurNonSalarie;
    }

    /**
     * Getter function.
     * @return the loiMadelin
     */
    public boolean isLoiMadelin() {
        return loiMadelin;
    }

    /**
     * Getter function.
     * @return the actuellementCouvert
     */
    public boolean isActuellementCouvert() {
        return actuellementCouvert;
    }

    /**
     * Getter function.
     * @return the couvertSixDerniersMois
     */
    public boolean isCouvertSixDerniersMois() {
        return couvertSixDerniersMois;
    }

    /**
     * Getter function.
     * @return the adressePrincipale
     */
    public AdresseModel getAdressePrincipale() {
        return adressePrincipale;
    }

    /**
     * Getter function.
     * @return the adresseSecondaire
     */
    public AdresseModel getAdresseSecondaire() {
        return adresseSecondaire;
    }

    /**
     * Getter function.
     * @return the telephoneFixe
     */
    public TelephoneModel getTelephoneFixe() {
        return telephoneFixe;
    }

    /**
     * Getter function.
     * @return the telephonePortable
     */
    public TelephoneModel getTelephonePortable() {
        return telephonePortable;
    }

    /**
     * Getter function.
     * @return the telephoneBureau
     */
    public TelephoneModel getTelephoneBureau() {
        return telephoneBureau;
    }

    /**
     * Getter function.
     * @return the email
     */
    public EmailModel getEmail() {
        return email;
    }

    /**
     * Getter function.
     * @return the listeBeneficiaires
     */
    public List<BeneficiaireModel> getListeBeneficiaires() {
        if (listeBeneficiaires == null) {
            listeBeneficiaires = new ArrayList<BeneficiaireModel>();
        }
        return listeBeneficiaires;
    }

    /**
     * Setter function.
     * @param eidPersonne the eidPersonne to set
     */
    public void setEidPersonne(Long eidPersonne) {
        this.eidPersonne = eidPersonne;
    }

    /**
     * Setter function.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter function.
     * @param nomJeuneFille the nomJeuneFille to set
     */
    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    /**
     * Setter function.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Setter function.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Setter function.
     * @param travailleurNonSalarie the travailleurNonSalarie to set
     */
    public void setTravailleurNonSalarie(boolean travailleurNonSalarie) {
        this.travailleurNonSalarie = travailleurNonSalarie;
    }

    /**
     * Setter function.
     * @param loiMadelin the loiMadelin to set
     */
    public void setLoiMadelin(boolean loiMadelin) {
        this.loiMadelin = loiMadelin;
    }

    /**
     * Setter function.
     * @param actuellementCouvert the actuellementCouvert to set
     */
    public void setActuellementCouvert(boolean actuellementCouvert) {
        this.actuellementCouvert = actuellementCouvert;
    }

    /**
     * Setter function.
     * @param couvertSixDerniersMois the couvertSixDerniersMois to set
     */
    public void setCouvertSixDerniersMois(boolean couvertSixDerniersMois) {
        this.couvertSixDerniersMois = couvertSixDerniersMois;
    }

    /**
     * Setter function.
     * @param adressePrincipale the adressePrincipale to set
     */
    public void setAdressePrincipale(AdresseModel adressePrincipale) {
        this.adressePrincipale = adressePrincipale;
    }

    /**
     * Setter function.
     * @param adresseSecondaire the adresseSecondaire to set
     */
    public void setAdresseSecondaire(AdresseModel adresseSecondaire) {
        this.adresseSecondaire = adresseSecondaire;
    }

    /**
     * Setter function.
     * @param telephoneFixe the telephoneFixe to set
     */
    public void setTelephoneFixe(TelephoneModel telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }

    /**
     * Setter function.
     * @param telephonePortable the telephonePortable to set
     */
    public void setTelephonePortable(TelephoneModel telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    /**
     * Setter function.
     * @param telephoneBureau the telephoneBureau to set
     */
    public void setTelephoneBureau(TelephoneModel telephoneBureau) {
        this.telephoneBureau = telephoneBureau;
    }

    /**
     * Setter function.
     * @param email the email to set
     */
    public void setEmail(EmailModel email) {
        this.email = email;
    }

    /**
     * Setter function.
     * @param listeBeneficiaires the listeBeneficiaires to set
     */
    public void setListeBeneficiaires(List<BeneficiaireModel> listeBeneficiaires) {
        this.listeBeneficiaires = listeBeneficiaires;
    }

    /**
     * Getter function.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter function.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la valeur de telephoneFax.
     * @return la valeur de telephoneFax
     */
    public TelephoneModel getTelephoneFax() {
        return telephoneFax;
    }

    /**
     * Définit la valeur de telephoneFax.
     * @param telephoneFax la nouvelle valeur de telephoneFax
     */
    public void setTelephoneFax(TelephoneModel telephoneFax) {
        this.telephoneFax = telephoneFax;
    }

    /**
     * Get the eidCivilite value.
     * @return the eidCivilite
     */
    public Long getEidCivilite() {
        return eidCivilite;
    }

    /**
     * Set the eidCivilite value.
     * @param eidCivilite the eidCivilite to set
     */
    public void setEidCivilite(Long eidCivilite) {
        this.eidCivilite = eidCivilite;
    }

    /**
     * Get the eidCategorieSocioProf value.
     * @return the eidCategorieSocioProf
     */
    public Long getEidCategorieSocioProf() {
        return eidCategorieSocioProf;
    }

    /**
     * Set the eidCategorieSocioProf value.
     * @param eidCategorieSocioProf the eidCategorieSocioProf to set
     */
    public void setEidCategorieSocioProf(Long eidCategorieSocioProf) {
        this.eidCategorieSocioProf = eidCategorieSocioProf;
    }

    /**
     * Get the eidSituationFamiliale value.
     * @return the eidSituationFamiliale
     */
    public Long getEidSituationFamiliale() {
        return eidSituationFamiliale;
    }

    /**
     * Set the eidSituationFamiliale value.
     * @param eidSituationFamiliale the eidSituationFamiliale to set
     */
    public void setEidSituationFamiliale(Long eidSituationFamiliale) {
        this.eidSituationFamiliale = eidSituationFamiliale;
    }

    /**
     * Get the eidStatut value.
     * @return the eidStatut
     */
    public Long getEidStatut() {
        return eidStatut;
    }

    /**
     * Set the eidStatut value.
     * @param eidStatut the eidStatut to set
     */
    public void setEidStatut(Long eidStatut) {
        this.eidStatut = eidStatut;
    }

    /**
     * Getter function.
     * @return the eidNature
     */
    public Long getEidNature() {
        return eidNature;
    }

    /**
     * Setter function.
     * @param eidNature the eidNature to set
     */
    public void setEidNature(Long eidNature) {
        this.eidNature = eidNature;
    }

    /**
     * Get the infoSante value.
     * @return the infoSante
     */
    public InfoSanteCreationModel getInfoSante() {
        return infoSante;
    }

    /**
     * Set the infoSante value.
     * @param infoSante the infoSante to set
     */
    public void setInfoSante(InfoSanteCreationModel infoSante) {
        this.infoSante = infoSante;
    }
}
