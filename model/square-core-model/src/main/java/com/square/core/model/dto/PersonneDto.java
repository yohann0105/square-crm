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
package com.square.core.model.dto;

import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Un objet contenant une personne simple.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneDto extends PersonneBaseDto {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 5803482267020285847L;

    /**
     * Identifiant de la personne.
     */
    private Long identifiant;

    /**
     * Numéro de client.
     */
    private String numClient;

    /**
     * Info santé.
     */
    private InfoSanteDto infoSante;

    /**
     * Identifiant extérieur de la personne.
     */
    private String idext;

    /**
     * Civilité de la personne.
     */
    private IdentifiantLibelleDto civilite;

    /**
     * Prénom de la personne.
     */
    private String prenom;

    /**
     * Nom de la personne.
     */
    private String nom;

    /**
     * Nom de jeune fille.
     */
    private String nomJeuneFille;

    /**
     * Date de naissance de la personne.
     */
    private Calendar dateNaissance;

    /**
     * Date de décès de la personne.
     */
    private Calendar dateDeces;

    /**
     * Date de création de la personne.
     */
    private Calendar dateCreation;

    /**
     * L'âge de la personne.
     */
    private int age;

    /**
     * Nature de la personne.
     */
    private IdentifiantLibelleDto naturePersonne;

    /**
     * Indique si la personne est décédé.
     */
    private boolean decede;

    /**
     * Le segment de la personne.
     */
    private IdentifiantLibelleDto segment;

    /**
     * Le réseau de vente.
     */
    private IdentifiantLibelleDto reseauVente;

    /**
     * Csp de la personne.
     */
    private IdentifiantLibelleDto csp;

    /**
     * Nombre d'enfant de la personne.
     */
    private int nbEnfants;

    /**
     * Situation familiale.
     */
    private IdentifiantLibelleDto sitFam;

    /**
     * Profession de la personne.
     */
    private IdentifiantLibelleBooleanDto profession;

    /**
     * Statut de la personne.
     */
    private IdentifiantLibelleDto statut;

    /**
     * La ressource: créateur de la personne.
     */
    private DimensionRessourceDto createur;

    /**
     * La ressource: responsable de la personne.
     */
    private DimensionRessourceDto commercial;

    /**
     * L'agence.
     */
    private IdentifiantEidLibelleDto agence;

    /**
     * Flag pour savoir si la personne refuse d'etre contacté.
     */
    private Boolean refuseEtreContacte = Boolean.TRUE;

    /**
     * Flag pour savoir si la personne est supprimée.
     */
    private Boolean supprime;

    /** Flag indiquant si la nature de la personne a changé. */
    private Boolean hasNaturePersonneChanged;

    /** Ancienne nature de la personne. */
    private String ancienneNaturePersonne;

    /** Nouvelle nature de la personne. */
    private String nouvelleNaturePersonne;

    /**
     * Retourne la valeur de identifiant.
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Modifie la valeur de identifiant.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
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
     * Retourne la valeur de idext.
     * @return the idext
     */
    public String getIdext() {
        return idext;
    }

    /**
     * Modifie la valeur de idext.
     * @param idext the idext to set
     */
    public void setIdext(String idext) {
        this.idext = idext;
    }

    /**
     * Retourne la valeur de prenom.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie la valeur de prenom.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne la valeur de nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie la valeur de nom.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la valeur de nomJeuneFille.
     * @return the nomJeuneFille
     */
    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    /**
     * Modifie la valeur de nomJeuneFille.
     * @param nomJeuneFille the nomJeuneFille to set
     */
    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    /**
     * Retourne la valeur de dateNaissance.
     * @return the dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie la valeur de dateNaissance.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Retourne la valeur de decede.
     * @return the decede
     */
    public boolean isDecede() {
        return decede;
    }

    /**
     * Modifie la valeur de decede.
     * @param decede the decede to set
     */
    public void setDecede(boolean decede) {
        this.decede = decede;
    }

    /**
     * recuperer la valeur.
     * @return the nbEnfants
     */
    public int getNbEnfants() {
        return nbEnfants;
    }

    /**
     * Fixer la valeur.
     * @param nbEnfants the nbEnfants to set
     */
    public void setNbEnfants(int nbEnfants) {
        this.nbEnfants = nbEnfants;
    }

    /**
     * Renvoi la valeur de naturePersonne.
     * @return naturePersonne
     */
    public IdentifiantLibelleDto getNaturePersonne() {
        return naturePersonne;
    }

    /**
     * Modifie naturePersonne.
     * @param naturePersonne la nouvelle valeur de naturePersonne
     */
    public void setNaturePersonne(IdentifiantLibelleDto naturePersonne) {
        this.naturePersonne = naturePersonne;
    }

    /**
     * Renvoi la valeur de segment.
     * @return segment
     */
    public IdentifiantLibelleDto getSegment() {
        return segment;
    }

    /**
     * Modifie segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(IdentifiantLibelleDto segment) {
        this.segment = segment;
    }

    /**
     * Renvoi la valeur de reseauVente.
     * @return reseauVente
     */
    public IdentifiantLibelleDto getReseauVente() {
        return reseauVente;
    }

    /**
     * Modifie reseauVente.
     * @param reseauVente la nouvelle valeur de reseauVente
     */
    public void setReseauVente(IdentifiantLibelleDto reseauVente) {
        this.reseauVente = reseauVente;
    }

    /**
     * Renvoi la valeur de csp.
     * @return csp
     */
    public IdentifiantLibelleDto getCsp() {
        return csp;
    }

    /**
     * Modifie csp.
     * @param csp la nouvelle valeur de csp
     */
    public void setCsp(IdentifiantLibelleDto csp) {
        this.csp = csp;
    }

    /**
     * Renvoi la valeur de sitFam.
     * @return sitFam
     */
    public IdentifiantLibelleDto getSitFam() {
        return sitFam;
    }

    /**
     * Modifie sitFam.
     * @param sitFam la nouvelle valeur de sitFam
     */
    public void setSitFam(IdentifiantLibelleDto sitFam) {
        this.sitFam = sitFam;
    }

    /**
     * Renvoi la valeur de profession.
     * @return profession
     */
    public IdentifiantLibelleBooleanDto getProfession() {
        return profession;
    }

    /**
     * Modifie profession.
     * @param profession la nouvelle valeur de profession
     */
    public void setProfession(IdentifiantLibelleBooleanDto profession) {
        this.profession = profession;
    }

    /**
     * Renvoi la valeur de statut.
     * @return statut
     */
    public IdentifiantLibelleDto getStatut() {
        return statut;
    }

    /**
     * Modifie statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleDto statut) {
        this.statut = statut;
    }

    /**
     * Renvoi la valeur de civilite.
     * @return civilite
     */
    public IdentifiantLibelleDto getCivilite() {
        return civilite;
    }

    /**
     * Modifie civilite.
     * @param civilite la nouvelle valeur de civilite
     */
    public void setCivilite(IdentifiantLibelleDto civilite) {
        this.civilite = civilite;
    }

    /**
     * Retourne la valeur de createur.
     * @return the createur
     */
    public DimensionRessourceDto getCreateur() {
        return createur;
    }

    /**
     * Modifie la valeur de createur.
     * @param createur the createur to set
     */
    public void setCreateur(DimensionRessourceDto createur) {
        this.createur = createur;
    }

    /**
     * Retourne la valeur de commercial.
     * @return the commerciale
     */
    public DimensionRessourceDto getCommercial() {
        return commercial;
    }

    /**
     * Modifie la valeur de commercial.
     * @param commercial the commercial to set
     */
    public void setCommercial(DimensionRessourceDto commercial) {
        this.commercial = commercial;
    }

    /**
     * Retourne la valeur de agence.
     * @return the agence
     */
    public IdentifiantEidLibelleDto getAgence() {
        return agence;
    }

    /**
     * Modifie la valeur de agence.
     * @param agence the agence to set
     */
    public void setAgence(IdentifiantEidLibelleDto agence) {
        this.agence = agence;
    }

    /**
     * Date de création de la personne.
     * @return dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Affecter la date de création de la personne.
     * @param dateCreation dans dateCreation
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Récupère la valeur de dateDeces.
     * @return la valeur de dateDeces
     */
    public Calendar getDateDeces() {
        return dateDeces;
    }

    /**
     * Définit la valeur de dateDeces.
     * @param dateDeces la nouvelle valeur de dateDeces
     */
    public void setDateDeces(Calendar dateDeces) {
        this.dateDeces = dateDeces;
    }

    /**
     * Getter function.
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter function.
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
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
     * Get the supprime value.
     * @return the supprime
     */
    public Boolean isSupprime() {
        return supprime;
    }

    /**
     * Renvoie la valeur de supprime.
     * @return supprime
     */
    public Boolean getSupprime() {
        return supprime;
    }

    /**
     * Set the supprime value.
     * @param supprime the supprime to set
     */
    public void setSupprime(Boolean supprime) {
        this.supprime = supprime;
    }

    /**
     * Récupère la valeur de hasNaturePersonneChanged.
     * @return la valeur de hasNaturePersonneChanged
     */
    public Boolean getHasNaturePersonneChanged() {
        return hasNaturePersonneChanged;
    }

    /**
     * Définit la valeur de hasNaturePersonneChanged.
     * @param hasNaturePersonneChanged la nouvelle valeur de hasNaturePersonneChanged
     */
    public void setHasNaturePersonneChanged(Boolean hasNaturePersonneChanged) {
        this.hasNaturePersonneChanged = hasNaturePersonneChanged;
    }

    /**
     * Récupère la valeur de ancienneNaturePersonne.
     * @return la valeur de ancienneNaturePersonne
     */
    public String getAncienneNaturePersonne() {
        return ancienneNaturePersonne;
    }

    /**
     * Définit la valeur de ancienneNaturePersonne.
     * @param ancienneNaturePersonne la nouvelle valeur de ancienneNaturePersonne
     */
    public void setAncienneNaturePersonne(String ancienneNaturePersonne) {
        this.ancienneNaturePersonne = ancienneNaturePersonne;
    }

    /**
     * Récupère la valeur de nouvelleNaturePersonne.
     * @return la valeur de nouvelleNaturePersonne
     */
    public String getNouvelleNaturePersonne() {
        return nouvelleNaturePersonne;
    }

    /**
     * Définit la valeur de nouvelleNaturePersonne.
     * @param nouvelleNaturePersonne la nouvelle valeur de nouvelleNaturePersonne
     */
    public void setNouvelleNaturePersonne(String nouvelleNaturePersonne) {
        this.nouvelleNaturePersonne = nouvelleNaturePersonne;
    }
}
