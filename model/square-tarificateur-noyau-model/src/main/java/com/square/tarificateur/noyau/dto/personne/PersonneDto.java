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

/**
 * DTO modélisant une personne.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class PersonneDto implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 6787504704801632458L;

    /** Identifiant de la personne. */
    private Long id;

    /** EID de la personne (Square). */
    private Long eidPersonne;

    /** Eid Civilité (Square). */
    private Long eidCivilite;

    /** Numéro de client. */
    private String numClient;

    /** Nom. */
    private String nom;

    /** Nom de jeune fille. */
    private String nomJeuneFille;

    /** Prénom. */
    private String prenom;

    /** Date de naissance. */
    private Calendar dateNaissance;

    /** Info Santé. */
    private InfoSanteDto infoSante;

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
    private AdresseDto adressePrincipale;

    /** Adresse secondaire. */
    private AdresseDto adresseSecondaire;

    /** Numéro de téléphone fixe. */
    private TelephoneDto telephoneFixe;

    /** Numéro de téléphone portable. */
    private TelephoneDto telephonePortable;

    /** Numéro de téléphone bureau. */
    private TelephoneDto telephoneBureau;

    /** Numéro de téléphone fax. */
    private TelephoneDto telephoneFax;

    /** Email. */
    private EmailDto email;

    /** Liste des bénéficiaires. */
    private List<BeneficiaireDto> listeBeneficiaires;

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
    public Calendar getDateNaissance() {
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
    public AdresseDto getAdressePrincipale() {
        return adressePrincipale;
    }

    /**
     * Getter function.
     * @return the adresseSecondaire
     */
    public AdresseDto getAdresseSecondaire() {
        return adresseSecondaire;
    }

    /**
     * Getter function.
     * @return the telephoneFixe
     */
    public TelephoneDto getTelephoneFixe() {
        return telephoneFixe;
    }

    /**
     * Getter function.
     * @return the telephonePortable
     */
    public TelephoneDto getTelephonePortable() {
        return telephonePortable;
    }

    /**
     * Getter function.
     * @return the telephoneBureau
     */
    public TelephoneDto getTelephoneBureau() {
        return telephoneBureau;
    }

    /**
     * Getter function.
     * @return the email
     */
    public EmailDto getEmail() {
        return email;
    }

    /**
     * Getter function.
     * @return the listeBeneficiaires
     */
    public List<BeneficiaireDto> getListeBeneficiaires() {
        if (listeBeneficiaires == null) {
            listeBeneficiaires = new ArrayList<BeneficiaireDto>();
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
    public void setDateNaissance(Calendar dateNaissance) {
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
    public void setAdressePrincipale(AdresseDto adressePrincipale) {
        this.adressePrincipale = adressePrincipale;
    }

    /**
     * Setter function.
     * @param adresseSecondaire the adresseSecondaire to set
     */
    public void setAdresseSecondaire(AdresseDto adresseSecondaire) {
        this.adresseSecondaire = adresseSecondaire;
    }

    /**
     * Setter function.
     * @param telephoneFixe the telephoneFixe to set
     */
    public void setTelephoneFixe(TelephoneDto telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }

    /**
     * Setter function.
     * @param telephonePortable the telephonePortable to set
     */
    public void setTelephonePortable(TelephoneDto telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    /**
     * Setter function.
     * @param telephoneBureau the telephoneBureau to set
     */
    public void setTelephoneBureau(TelephoneDto telephoneBureau) {
        this.telephoneBureau = telephoneBureau;
    }

    /**
     * Setter function.
     * @param email the email to set
     */
    public void setEmail(EmailDto email) {
        this.email = email;
    }

    /**
     * Setter function.
     * @param listeBeneficiaires the listeBeneficiaires to set
     */
    public void setListeBeneficiaires(List<BeneficiaireDto> listeBeneficiaires) {
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
    public TelephoneDto getTelephoneFax() {
        return telephoneFax;
    }

    /**
     * Définit la valeur de telephoneFax.
     * @param telephoneFax la nouvelle valeur de telephoneFax
     */
    public void setTelephoneFax(TelephoneDto telephoneFax) {
        this.telephoneFax = telephoneFax;
    }

    /**
     * Récupère la valeur de eidCivilite.
     * @return la valeur de eidCivilite
     */
    public Long getEidCivilite() {
        return eidCivilite;
    }

    /**
     * Définit la valeur de eidCivilite.
     * @param eidCivilite la nouvelle valeur de eidCivilite
     */
    public void setEidCivilite(Long eidCivilite) {
        this.eidCivilite = eidCivilite;
    }

    /**
     * Récupère la valeur de eidCategorieSocioProf.
     * @return la valeur de eidCategorieSocioProf
     */
    public Long getEidCategorieSocioProf() {
        return eidCategorieSocioProf;
    }

    /**
     * Définit la valeur de eidCategorieSocioProf.
     * @param eidCategorieSocioProf la nouvelle valeur de eidCategorieSocioProf
     */
    public void setEidCategorieSocioProf(Long eidCategorieSocioProf) {
        this.eidCategorieSocioProf = eidCategorieSocioProf;
    }

    /**
     * Récupère la valeur de eidSituationFamiliale.
     * @return la valeur de eidSituationFamiliale
     */
    public Long getEidSituationFamiliale() {
        return eidSituationFamiliale;
    }

    /**
     * Définit la valeur de eidSituationFamiliale.
     * @param eidSituationFamiliale la nouvelle valeur de eidSituationFamiliale
     */
    public void setEidSituationFamiliale(Long eidSituationFamiliale) {
        this.eidSituationFamiliale = eidSituationFamiliale;
    }

    /**
     * Récupère la valeur de eidStatut.
     * @return la valeur de eidStatut
     */
    public Long getEidStatut() {
        return eidStatut;
    }

    /**
     * Définit la valeur de eidStatut.
     * @param eidStatut la nouvelle valeur de eidStatut
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
    public InfoSanteDto getInfoSante() {
        return infoSante;
    }

    /**
     * Set the infoSante value.
     * @param infoSante the infoSante to set
     */
    public void setInfoSante(InfoSanteDto infoSante) {
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

}
