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

import java.io.Serializable;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto contenant les coordonnées d'une personne.
 */
public class ConstantesDto implements Serializable {

	private static final long serialVersionUID = -955238738897574837L;

    /** Id du pays france. */
    private Long idPaysFrance;

    /** Pays France. */
    private PaysSimpleDto paysFrance;

    /** Id du pays france métropolitaine. */
    private Long idPaysFranceMetropolitaine;

    /** Id du pays par defaut. */
    private Long idPaysParDefaut;

    /** Id - Libellé du pays par defaut. */
    private IdentifiantLibelleDto idLibellePaysParDefaut;

    /** Id du type de relation Conjoint. */
    private Long idTypeRelationConjoint;

    /** Id du type de relation Enfant. */
    private Long idTypeRelationEnfant;

    /** Id du groupement familiale. */
    private Long idGroupementFamiliale;

    /** Id du groupement profesionnel. */
    private Long idGroupementProfessionnel;

    /** Id de la nature de l'adresse principale. */
    private Long idNatureAdressePrincipale;

    /** Id de la nature de l'adresse secondaire. */
    private Long idNatureAdresseSecondaire;

    /** Id de la priorite par defaut. */
    private Long idActionPrioriteParDefaut;

    /** Id du statut terminer. */
    private Long idStatutTerminer;

    /** Id du statut à faire. */
    private Long idStatutAFaire;

    /** Id du statut annuler. */
    private Long idStatutAnnuler;

    /** Identifiant de la civilité monsieur. */
    private Long idCiviliteMonsieur;

    /** Identifiant du résultat opportunite. */
    private Long idResultatOpportunite;

    /** Identifiant du résultat relance. */
    private Long idResultatRelance;

    /** Identifiant du telephone fixe. */
    private Long idNatureTelephoneFixe;

    /** Identifiant du telephone mobile. */
    private Long idNatureMobileTravail;

    /** Identifiant nature de téléphone mobile privé. */
    private Long idNatureMobilePrive;

    /** Identifiant du statut d'opportunité non renseigné. */
    private Long idStatutOpportuniteNonRenseigne;

    /** Identifiant du statut d'opportunité transformé. */
    private Long idStatutOpportuniteTransforme;

    /** Identifiant du statut d'opportunité refusé. */
    private Long idStatutOpportuniteRefuse;

    /** Identifiant du statut d'opportunité accepté. */
    private Long idStatutOpportuniteAccepte;

    /** Identifiant du statut d'opportunité corbeille. */
    private Long idStatutOpportuniteCorbeille;

    /** Identifiant du statut d'opportunité en attente. */
    private Long idStatutOpportuniteEnAttente;

    /** Identifiant de l'objet d'action Editique. */
    private Long idObjetActionEditique;

    /** Identifiant de l'objet d'action. */
    private Long idObjetActionTransformationAia;

    /** Id du sous-objet d'action Edition de BA. */
    private Long idSousObjetActionEditionBa;

    /** Id du sous-objet d'action Envoi de BA par mail. */
    private Long idSousObjetActionEnvoiBaParMail;

    /** Identifiant du type d'action. */
    private Long idTypeActionOpportunite;

    /** Identifiant du type d'action "Relance". */
    private Long idTypeActionRelance;

    /** Identifiant de la nature d'action "Internet". */
    private Long idNatureActionInternet;

    /** Identifiant de la nature d'action "Téléphone sortant". */
    private Long idNatureActionTelephoneSortant;

    /** Identifiant de la nature d'action "Visite sortante". */
    private Long idNatureActionVisiteSortante;

    /** Identifiant de la nature d'action "E-mail sortant". */
    private Long idNatureActionEmailSortant;

    /** Identifiant de la nature d'action "Courrier sortant". */
    private Long idNatureActionCourrierSortant;

    /** Identifiant de la nature d'action "Interne". */
    private Long idNatureActionInterne;

    /** Identifiant de la nature de personne vivier. */
    private Long idNaturePersonneVivier;

    /** Identifiant de la nature de personne prospect. */
    private Long idNaturePersonneProspect;

    /** Identifiant de la nature de personne adherent. */
    private Long idNaturePersonneAdherent;

    /** Identifiant de la nature de personne bénéficiaire prospect. */
    private Long idNaturePersonneBeneficiaireProspect;

    /** Identifiant de la nature de personne bénéficiaire vivier. */
    private Long idNaturePersonneBeneficiaireVivier;

    /** Identifiant de la nature "Adherent" pour une personne morale. */
    private Long idNaturePersonneMoraleAdherent;

    /** Identifiant - Libellé du type pour une opportunité. */
    private IdentifiantLibelleDto idLibelleTypeOpportunite;

    /** Propriété pour l'agence d'une personne. */
    private String proprietePersonneAgence;

    /** Propriété pour le code postal d'une personne. */
    private String proprietePersonneCodePostal;

    /** Propriété pour le commercial d'une personne. */
    private String proprietePersonneCommercial;

    /** Propriété pour la commmune d'une personne. */
    private String proprietePersonneCommune;

    /** Propriété pour la date de naissance d'une personne. */
    private String proprietePersonneDateNaissance;

    /** Propriété pour la nature d'une personne. */
    private String proprietePersonneNature;

    /** Propriété pour le nom d'une personne. */
    private String proprietePersonneNom;

    /** Propriété pour le numéro de client d'une personne. */
    private String proprietePersonneNumeroClient;

    /** Propriété pour le prénom d'une personne. */
    private String proprietePersonnePrenom;

    /** Propriété pour le segment d'une personne. */
    private String proprietePersonneSegment;

    /** Flag qui indique si l'utilisateur connecté a le rôle "ROLE_SQUARE_CAMPAGNE". */
    private boolean hasRoleCampagne;

    /** Flag qui indique si l'utilisateur connecté a le rôle "ROLE_SQUARE-ANIMATEURS". */
    private boolean hasRoleAnimateur;

    /** Identifiant - Libellé du statut "A faire". */
    private IdentifiantLibelleDto idLibelleStatutAFaire;

    /** Propriété pour la référence du décompte d'une prestation. */
    private String proprietePrestationRefDecompte;

    /** Propriété pour la date de soins d'une prestation. */
    private String proprietePrestationDateSoins;

    /** Propriété pour l'acte d'une prestation. */
    private String proprietePrestationActe;

    /** Propriété pour les dépenses engagées d'une prestation. */
    private String proprietePrestationDepensesEngagees;

    /** Propriété pour le remboursement RO d'une prestation. */
    private String proprietePrestationRemboursementRO;

    /** Propriété pour le remboursement Smatis à l'adhérent d'une prestation. */
    private String proprietePrestationRemboursementSmatis;

    /** Propriété pour le reste à charge d'une prestation. */
    private String proprietePrestationResteACharge;

    /** Propriété pour le nom du destinataire d'une prestation. */
    private String proprietePrestationNomDestinataire;

    /** Statut de paiement payé. */
    private String statutPaiementPaye;

    /** Tri des actions par leur identifiant unique. */
    private String orderByActionId;

    /** Tri des actions par leur date de création. */
    private String orderByActionDateCreation;

    /** Tri des actions par leur date d'action. */
    private String orderByActionDateAction;

    /** Tri des actions par leur date de fin. */
    private String orderByActionDateTerminee;

    /** Tri des actions par type. */
    private String orderByActionType;

    /** Tri des actions par objet. */
    private String orderByActionObjet;

    /** Tri des actions par sous objet. */
    private String orderByActionSsObjet;

    /** Tri des actions par priorité. */
    private String orderByActionPriorite;

    /** Tri des actions par statut. */
    private String orderByActionStatut;

    /** Tri des actions par attribution d'agence. */
    private String orderByActionAttributionAgence;

    /** Tri des actions par attribution de ressource. */
    private String orderByActionAttributionRessource;

    /** Identifiant de la nature personne décédée. */
    private Long idNaturePersonneDecede;

    /** Url de la servlet d'export. */
    private String urlServletExporterRecherche;

    /** Param service de la servlet d'export. */
    private String paramService;

    /** Valeur du param service de la servlet d'export. */
    private String valueServiceRecherchePersonnePhysique;

    /** Valeur du param service de la servlet d'export. */
    private String valueServiceRechercheCampagne;

    /** Valeur du param service de la servlet d'export. */
    private String valueServiceRechercheAction;

    /** Valeur du param service de la servlet d'export. */
    private String valueServiceRechercheRessource;

    /** Valeur du param service de la servlet d'export. */
    private String valueServiceRecherchePersonneMorale;

    /** Identifiant de l'état actif pour les ressources. */
    private Long idEtatActifRessource;

    /** Identifiant de l'état inactif pour les ressources. */
    private Long idEtatInactifRessource;

    /** Identifiant - Libellé de l'état actif d'une ressource. */
    private IdentifiantLibelleDto idLibelleEtatActifRessource;

    /** Booléen indiquant si la ressource connectée a le rôle de gestion des oppportunités. */
    private boolean hasRoleGestionnaireOpportunites;

    /** Identifiant de l'agence France. */
    private Long idAgenceFrance;

    /** Flag qui indique si l'utilisateur connecté a le rôle "ROLE_SQUARE_ADMIN". */
    private boolean hasRoleAdmin;

    /** Identifiant de la fonction "Chargé de communication". */
    private Long idFonctionCC;

    /**
     * Retourner l'identifiant de la civilité monsieur.
     * @return idCiviliteMonsieur
     */
    public Long getIdCiviliteMonsieur() {
        return idCiviliteMonsieur;
    }

    /**
     * Setter de la propriété idCiviliteMonsieur.
     * @param idCiviliteMonsieur dans idCiviliteMonsieur
     */
    public void setIdCiviliteMonsieur(Long idCiviliteMonsieur) {
        this.idCiviliteMonsieur = idCiviliteMonsieur;
    }

    /**
     * Get the idPaysFrance value.
     * @return the idPaysFrance
     */
    public Long getIdPaysFrance() {
        return idPaysFrance;
    }

    /**
     * Set the idPaysFrance value.
     * @param idPaysFrance the idPaysFrance to set
     */
    public void setIdPaysFrance(Long idPaysFrance) {
        this.idPaysFrance = idPaysFrance;
    }

    /**
     * Get the idPaysParDefaut value.
     * @return the idPaysParDefaut
     */
    public Long getIdPaysParDefaut() {
        return idPaysParDefaut;
    }

    /**
     * Set the idPaysParDefaut value.
     * @param idPaysParDefaut the idPaysParDefaut to set
     */
    public void setIdPaysParDefaut(Long idPaysParDefaut) {
        this.idPaysParDefaut = idPaysParDefaut;
    }

    /**
     * Get the idTypeRelationConjoint value.
     * @return the idTypeRelationConjoint
     */
    public Long getIdTypeRelationConjoint() {
        return idTypeRelationConjoint;
    }

    /**
     * Set the idTypeRelationConjoint value.
     * @param idTypeRelationConjoint the idTypeRelationConjoint to set
     */
    public void setIdTypeRelationConjoint(Long idTypeRelationConjoint) {
        this.idTypeRelationConjoint = idTypeRelationConjoint;
    }

    /**
     * Get the idTypeRelationEnfant value.
     * @return the idTypeRelationEnfant
     */
    public Long getIdTypeRelationEnfant() {
        return idTypeRelationEnfant;
    }

    /**
     * Set the idTypeRelationEnfant value.
     * @param idTypeRelationEnfant the idTypeRelationEnfant to set
     */
    public void setIdTypeRelationEnfant(Long idTypeRelationEnfant) {
        this.idTypeRelationEnfant = idTypeRelationEnfant;
    }

    /**
     * Renvoi la valeur de idGroupementFamiliale.
     * @return idGroupementFamiliale
     */
    public Long getIdGroupementFamiliale() {
        return idGroupementFamiliale;
    }

    /**
     * Modifie idGroupementFamiliale.
     * @param idGroupementFamiliale la nouvelle valeur de idGroupementFamiliale
     */
    public void setIdGroupementFamiliale(Long idGroupementFamiliale) {
        this.idGroupementFamiliale = idGroupementFamiliale;
    }

    /**
     * Renvoi la valeur de idNatureAdressePrincipale.
     * @return idNatureAdressePrincipale
     */
    public Long getIdNatureAdressePrincipale() {
        return idNatureAdressePrincipale;
    }

    /**
     * Modifie idNatureAdressePrincipale.
     * @param idNatureAdressePrincipale la nouvelle valeur de idNatureAdressePrincipale
     */
    public void setIdNatureAdressePrincipale(Long idNatureAdressePrincipale) {
        this.idNatureAdressePrincipale = idNatureAdressePrincipale;
    }

    /**
     * Renvoi la valeur de idActionPrioriteParDefaut.
     * @return idActionPrioriteParDefaut
     */
    public Long getIdActionPrioriteParDefaut() {
        return idActionPrioriteParDefaut;
    }

    /**
     * Modifie idActionPrioriteParDefaut.
     * @param idActionPrioriteParDefaut la nouvelle valeur de idActionPrioriteParDefaut
     */
    public void setIdActionPrioriteParDefaut(Long idActionPrioriteParDefaut) {
        this.idActionPrioriteParDefaut = idActionPrioriteParDefaut;
    }

    /**
     * Renvoi la valeur de idStatutAnnuler.
     * @return idStatutAnnuler
     */
    public Long getIdStatutAnnuler() {
        return idStatutAnnuler;
    }

    /**
     * Modifie idStatutAnnuler.
     * @param idStatutAnnuler la nouvelle valeur de idStatutAnnuler
     */
    public void setIdStatutAnnuler(Long idStatutAnnuler) {
        this.idStatutAnnuler = idStatutAnnuler;
    }

    /**
     * Get the idStatutTerminer value.
     * @return the idStatutTerminer
     */
    public Long getIdStatutTerminer() {
        return idStatutTerminer;
    }

    /**
     * Set the idStatutTerminer value.
     * @param idStatutTerminer the idStatutTerminer to set
     */
    public void setIdStatutTerminer(Long idStatutTerminer) {
        this.idStatutTerminer = idStatutTerminer;
    }

    /**
     * Get the idResultatOpportunite value.
     * @return the idResultatOpportunite
     */
    public Long getIdResultatOpportunite() {
        return idResultatOpportunite;
    }

    /**
     * Set the idResultatOpportunite value.
     * @param idResultatOpportunite the idResultatOpportunite to set
     */
    public void setIdResultatOpportunite(Long idResultatOpportunite) {
        this.idResultatOpportunite = idResultatOpportunite;
    }

    /**
     * Get the idResultatRelance value.
     * @return the idResultatRelance
     */
    public Long getIdResultatRelance() {
        return idResultatRelance;
    }

    /**
     * Set the idResultatRelance value.
     * @param idResultatRelance the idResultatRelance to set
     */
    public void setIdResultatRelance(Long idResultatRelance) {
        this.idResultatRelance = idResultatRelance;
    }

    /**
     * Get the idObjetActionTransformationAia value.
     * @return the idObjetActionTransformationAia
     */
    public Long getIdObjetActionTransformationAia() {
        return idObjetActionTransformationAia;
    }

    /**
     * Set the idObjetActionTransformationAia value.
     * @param idObjetActionTransformationAia the idObjetActionTransformationAia to set
     */
    public void setIdObjetActionTransformationAia(Long idObjetActionTransformationAia) {
        this.idObjetActionTransformationAia = idObjetActionTransformationAia;
    }

    /**
     * Get the idTypeActionOpportunite value.
     * @return the idTypeActionOpportunite
     */
    public Long getIdTypeActionOpportunite() {
        return idTypeActionOpportunite;
    }

    /**
     * Set the idTypeActionOpportunite value.
     * @param idTypeActionOpportunite the idTypeActionOpportunite to set
     */
    public void setIdTypeActionOpportunite(Long idTypeActionOpportunite) {
        this.idTypeActionOpportunite = idTypeActionOpportunite;
    }

    /**
     * Get the idNatureActionInternet value.
     * @return the idNatureActionInternet
     */
    public Long getIdNatureActionInternet() {
        return idNatureActionInternet;
    }

    /**
     * Set the idNatureActionInternet value.
     * @param idNatureActionInternet the idNatureActionInternet to set
     */
    public void setIdNatureActionInternet(Long idNatureActionInternet) {
        this.idNatureActionInternet = idNatureActionInternet;
    }

    /**
     * Renvoie la nature du telephone fixe.
     * @return idNatureTelephoneFixe nature du telephone
     */
    public Long getIdNatureTelephoneFixe() {
        return idNatureTelephoneFixe;
    }

    /**
     * Modifie la nature du telephone fixe.
     * @param idNatureTelephoneFixe la nouvelle valeur
     */
    public void setIdNatureTelephoneFixe(Long idNatureTelephoneFixe) {
        this.idNatureTelephoneFixe = idNatureTelephoneFixe;
    }

    /**
     * Renvoie la nature du telephone mobile.
     * @return idNatureTelephoneFixe nature du telephone
     */
    public Long getIdNatureMobileTravail() {
        return idNatureMobileTravail;
    }

    /**
     * Modifie la nature du telephone mobile.
     * @param idNatureMobileTravail la nouvelle valeur
     */
    public void setIdNatureMobileTravail(Long idNatureMobileTravail) {
        this.idNatureMobileTravail = idNatureMobileTravail;
    }

    /**
     * Getter function.
     * @return the idStatutOpportuniteNonRenseigne
     */
    public Long getIdStatutOpportuniteNonRenseigne() {
        return idStatutOpportuniteNonRenseigne;
    }

    /**
     * Getter function.
     * @return the idStatutOpportuniteTransforme
     */
    public Long getIdStatutOpportuniteTransforme() {
        return idStatutOpportuniteTransforme;
    }

    /**
     * Getter function.
     * @return the idStatutOpportuniteRefuse
     */
    public Long getIdStatutOpportuniteRefuse() {
        return idStatutOpportuniteRefuse;
    }

    /**
     * Getter function.
     * @return the idStatutOpportuniteAccepte
     */
    public Long getIdStatutOpportuniteAccepte() {
        return idStatutOpportuniteAccepte;
    }

    /**
     * Getter function.
     * @return the idStatutOpportuniteCorbeille
     */
    public Long getIdStatutOpportuniteCorbeille() {
        return idStatutOpportuniteCorbeille;
    }

    /**
     * Setter function.
     * @param idStatutOpportuniteNonRenseigne the idStatutOpportuniteNonRenseigne to set
     */
    public void setIdStatutOpportuniteNonRenseigne(Long idStatutOpportuniteNonRenseigne) {
        this.idStatutOpportuniteNonRenseigne = idStatutOpportuniteNonRenseigne;
    }

    /**
     * Setter function.
     * @param idStatutOpportuniteTransforme the idStatutOpportuniteTransforme to set
     */
    public void setIdStatutOpportuniteTransforme(Long idStatutOpportuniteTransforme) {
        this.idStatutOpportuniteTransforme = idStatutOpportuniteTransforme;
    }

    /**
     * Setter function.
     * @param idStatutOpportuniteRefuse the idStatutOpportuniteRefuse to set
     */
    public void setIdStatutOpportuniteRefuse(Long idStatutOpportuniteRefuse) {
        this.idStatutOpportuniteRefuse = idStatutOpportuniteRefuse;
    }

    /**
     * Setter function.
     * @param idStatutOpportuniteAccepte the idStatutOpportuniteAccepte to set
     */
    public void setIdStatutOpportuniteAccepte(Long idStatutOpportuniteAccepte) {
        this.idStatutOpportuniteAccepte = idStatutOpportuniteAccepte;
    }

    /**
     * Setter function.
     * @param idStatutOpportuniteCorbeille the idStatutOpportuniteCorbeille to set
     */
    public void setIdStatutOpportuniteCorbeille(Long idStatutOpportuniteCorbeille) {
        this.idStatutOpportuniteCorbeille = idStatutOpportuniteCorbeille;
    }

    /**
     * Récupère la valeur de idLibelleTypeOpportunite.
     * @return la valeur de idLibelleTypeOpportunite
     */
    public IdentifiantLibelleDto getIdLibelleTypeOpportunite() {
        return idLibelleTypeOpportunite;
    }

    /**
     * Définit la valeur de idLibelleTypeOpportunite.
     * @param idLibelleTypeOpportunite la nouvelle valeur de idLibelleTypeOpportunite
     */
    public void setIdLibelleTypeOpportunite(IdentifiantLibelleDto idLibelleTypeOpportunite) {
        this.idLibelleTypeOpportunite = idLibelleTypeOpportunite;
    }

    /**
     * Récupère la valeur de idPaysFranceMetropolitaine.
     * @return la valeur de idPaysFranceMetropolitaine
     */
    public Long getIdPaysFranceMetropolitaine() {
        return idPaysFranceMetropolitaine;
    }

    /**
     * Définit la valeur de idPaysFranceMetropolitaine.
     * @param idPaysFranceMetropolitaine la nouvelle valeur de idPaysFranceMetropolitaine
     */
    public void setIdPaysFranceMetropolitaine(Long idPaysFranceMetropolitaine) {
        this.idPaysFranceMetropolitaine = idPaysFranceMetropolitaine;
    }

    /**
     * Récupère la valeur de proprietePersonneAgence.
     * @return la valeur de proprietePersonneAgence
     */
    public String getProprietePersonneAgence() {
        return proprietePersonneAgence;
    }

    /**
     * Définit la valeur de proprietePersonneAgence.
     * @param proprietePersonneAgence la nouvelle valeur de proprietePersonneAgence
     */
    public void setProprietePersonneAgence(String proprietePersonneAgence) {
        this.proprietePersonneAgence = proprietePersonneAgence;
    }

    /**
     * Récupère la valeur de proprietePersonneCodePostal.
     * @return la valeur de proprietePersonneCodePostal
     */
    public String getProprietePersonneCodePostal() {
        return proprietePersonneCodePostal;
    }

    /**
     * Définit la valeur de proprietePersonneCodePostal.
     * @param proprietePersonneCodePostal la nouvelle valeur de proprietePersonneCodePostal
     */
    public void setProprietePersonneCodePostal(String proprietePersonneCodePostal) {
        this.proprietePersonneCodePostal = proprietePersonneCodePostal;
    }

    /**
     * Récupère la valeur de proprietePersonneCommercial.
     * @return la valeur de proprietePersonneCommercial
     */
    public String getProprietePersonneCommercial() {
        return proprietePersonneCommercial;
    }

    /**
     * Définit la valeur de proprietePersonneCommercial.
     * @param proprietePersonneCommercial la nouvelle valeur de proprietePersonneCommercial
     */
    public void setProprietePersonneCommercial(String proprietePersonneCommercial) {
        this.proprietePersonneCommercial = proprietePersonneCommercial;
    }

    /**
     * Récupère la valeur de proprietePersonneCommune.
     * @return la valeur de proprietePersonneCommune
     */
    public String getProprietePersonneCommune() {
        return proprietePersonneCommune;
    }

    /**
     * Définit la valeur de proprietePersonneCommune.
     * @param proprietePersonneCommune la nouvelle valeur de proprietePersonneCommune
     */
    public void setProprietePersonneCommune(String proprietePersonneCommune) {
        this.proprietePersonneCommune = proprietePersonneCommune;
    }

    /**
     * Récupère la valeur de proprietePersonneDateNaissance.
     * @return la valeur de proprietePersonneDateNaissance
     */
    public String getProprietePersonneDateNaissance() {
        return proprietePersonneDateNaissance;
    }

    /**
     * Définit la valeur de proprietePersonneDateNaissance.
     * @param proprietePersonneDateNaissance la nouvelle valeur de proprietePersonneDateNaissance
     */
    public void setProprietePersonneDateNaissance(String proprietePersonneDateNaissance) {
        this.proprietePersonneDateNaissance = proprietePersonneDateNaissance;
    }

    /**
     * Récupère la valeur de proprietePersonneNature.
     * @return la valeur de proprietePersonneNature
     */
    public String getProprietePersonneNature() {
        return proprietePersonneNature;
    }

    /**
     * Définit la valeur de proprietePersonneNature.
     * @param proprietePersonneNature la nouvelle valeur de proprietePersonneNature
     */
    public void setProprietePersonneNature(String proprietePersonneNature) {
        this.proprietePersonneNature = proprietePersonneNature;
    }

    /**
     * Récupère la valeur de proprietePersonneNom.
     * @return la valeur de proprietePersonneNom
     */
    public String getProprietePersonneNom() {
        return proprietePersonneNom;
    }

    /**
     * Définit la valeur de proprietePersonneNom.
     * @param proprietePersonneNom la nouvelle valeur de proprietePersonneNom
     */
    public void setProprietePersonneNom(String proprietePersonneNom) {
        this.proprietePersonneNom = proprietePersonneNom;
    }

    /**
     * Récupère la valeur de proprietePersonneNumeroClient.
     * @return la valeur de proprietePersonneNumeroClient
     */
    public String getProprietePersonneNumeroClient() {
        return proprietePersonneNumeroClient;
    }

    /**
     * Définit la valeur de proprietePersonneNumeroClient.
     * @param proprietePersonneNumeroClient la nouvelle valeur de proprietePersonneNumeroClient
     */
    public void setProprietePersonneNumeroClient(String proprietePersonneNumeroClient) {
        this.proprietePersonneNumeroClient = proprietePersonneNumeroClient;
    }

    /**
     * Récupère la valeur de proprietePersonnePrenom.
     * @return la valeur de proprietePersonnePrenom
     */
    public String getProprietePersonnePrenom() {
        return proprietePersonnePrenom;
    }

    /**
     * Définit la valeur de proprietePersonnePrenom.
     * @param proprietePersonnePrenom la nouvelle valeur de proprietePersonnePrenom
     */
    public void setProprietePersonnePrenom(String proprietePersonnePrenom) {
        this.proprietePersonnePrenom = proprietePersonnePrenom;
    }

    /**
     * Récupère la valeur de proprietePersonneSegment.
     * @return la valeur de proprietePersonneSegment
     */
    public String getProprietePersonneSegment() {
        return proprietePersonneSegment;
    }

    /**
     * Définit la valeur de proprietePersonneSegment.
     * @param proprietePersonneSegment la nouvelle valeur de proprietePersonneSegment
     */
    public void setProprietePersonneSegment(String proprietePersonneSegment) {
        this.proprietePersonneSegment = proprietePersonneSegment;
    }

    /**
     * Récupère la valeur de idLibellePaysParDefaut.
     * @return la valeur de idLibellePaysParDefaut
     */
    public IdentifiantLibelleDto getIdLibellePaysParDefaut() {
        return idLibellePaysParDefaut;
    }

    /**
     * Définit la valeur de idLibellePaysParDefaut.
     * @param idLibellePaysParDefaut la nouvelle valeur de idLibellePaysParDefaut
     */
    public void setIdLibellePaysParDefaut(IdentifiantLibelleDto idLibellePaysParDefaut) {
        this.idLibellePaysParDefaut = idLibellePaysParDefaut;
    }

    /**
     * Récupère la valeur de idNaturePersonneProspect.
     * @return la valeur de idNaturePersonneProspect
     */
    public Long getIdNaturePersonneProspect() {
        return idNaturePersonneProspect;
    }

    /**
     * Définit la valeur de idNaturePersonneProspect.
     * @param idNaturePersonneProspect la nouvelle valeur de idNaturePersonneProspect
     */
    public void setIdNaturePersonneProspect(Long idNaturePersonneProspect) {
        this.idNaturePersonneProspect = idNaturePersonneProspect;
    }

    /**
     * Récupère la valeur de idNaturePersonneAdherent.
     * @return la valeur de idNaturePersonneAdherent
     */
    public Long getIdNaturePersonneAdherent() {
        return idNaturePersonneAdherent;
    }

    /**
     * Définit la valeur de idNaturePersonneAdherent.
     * @param idNaturePersonneAdherent la nouvelle valeur de idNaturePersonneAdherent
     */
    public void setIdNaturePersonneAdherent(Long idNaturePersonneAdherent) {
        this.idNaturePersonneAdherent = idNaturePersonneAdherent;
    }

    /**
     * Récupère la valeur de idNaturePersonneBeneficiaireProspect.
     * @return la valeur de idNaturePersonneBeneficiaireProspect
     */
    public Long getIdNaturePersonneBeneficiaireProspect() {
        return idNaturePersonneBeneficiaireProspect;
    }

    /**
     * Définit la valeur de idNaturePersonneBeneficiaireProspect.
     * @param idNaturePersonneBeneficiaireProspect la nouvelle valeur de idNaturePersonneBeneficiaireProspect
     */
    public void setIdNaturePersonneBeneficiaireProspect(Long idNaturePersonneBeneficiaireProspect) {
        this.idNaturePersonneBeneficiaireProspect = idNaturePersonneBeneficiaireProspect;
    }

    /**
     * Getter function.
     * @return the hasRoleCampagne
     */
    public boolean isHasRoleCampagne() {
        return hasRoleCampagne;
    }

    /**
     * Setter function.
     * @param hasRoleCampagne the hasRoleCampagne to set
     */
    public void setHasRoleCampagne(boolean hasRoleCampagne) {
        this.hasRoleCampagne = hasRoleCampagne;
    }

    /**
     * Définit la valeur de idStatutAFaire.
     * @param idStatutAFaire the idStatutAFaire to set
     */
    public void setIdStatutAFaire(Long idStatutAFaire) {
        this.idStatutAFaire = idStatutAFaire;
    }

    /**
     * Récupère la valeur de idStatutAFaire.
     * @return the idStatutAFaire
     */
    public Long getIdStatutAFaire() {
        return idStatutAFaire;
    }

    /**
     * Récupère la valeur de idNatureActionTelephoneSortant.
     * @return la valeur de idNatureActionTelephoneSortant
     */
    public Long getIdNatureActionTelephoneSortant() {
        return idNatureActionTelephoneSortant;
    }

    /**
     * Définit la valeur de idNatureActionTelephoneSortant.
     * @param idNatureActionTelephoneSortant la nouvelle valeur de idNatureActionTelephoneSortant
     */
    public void setIdNatureActionTelephoneSortant(Long idNatureActionTelephoneSortant) {
        this.idNatureActionTelephoneSortant = idNatureActionTelephoneSortant;
    }

    /**
     * Récupère la valeur de idNatureActionVisiteSortante.
     * @return la valeur de idNatureActionVisiteSortante
     */
    public Long getIdNatureActionVisiteSortante() {
        return idNatureActionVisiteSortante;
    }

    /**
     * Définit la valeur de idNatureActionVisiteSortante.
     * @param idNatureActionVisiteSortante la nouvelle valeur de idNatureActionVisiteSortante
     */
    public void setIdNatureActionVisiteSortante(Long idNatureActionVisiteSortante) {
        this.idNatureActionVisiteSortante = idNatureActionVisiteSortante;
    }

    /**
     * Récupère la valeur de idLibelleStatutAFaire.
     * @return la valeur de idLibelleStatutAFaire
     */
    public IdentifiantLibelleDto getIdLibelleStatutAFaire() {
        return idLibelleStatutAFaire;
    }

    /**
     * Définit la valeur de idLibelleStatutAFaire.
     * @param idLibelleStatutAFaire la nouvelle valeur de idLibelleStatutAFaire
     */
    public void setIdLibelleStatutAFaire(IdentifiantLibelleDto idLibelleStatutAFaire) {
        this.idLibelleStatutAFaire = idLibelleStatutAFaire;
    }

    /**
     * Récupère la valeur de idGroupementProfessionnel.
     * @return la valeur de idGroupementProfessionnel
     */
    public Long getIdGroupementProfessionnel() {
        return idGroupementProfessionnel;
    }

    /**
     * Définit la valeur de idGroupementProfessionnel.
     * @param idGroupementProfessionnel la nouvelle valeur de idGroupementProfessionnel
     */
    public void setIdGroupementProfessionnel(Long idGroupementProfessionnel) {
        this.idGroupementProfessionnel = idGroupementProfessionnel;
    }

    /**
     * Récupère la valeur de proprietePrestationRefDecompte.
     * @return la valeur de proprietePrestationRefDecompte
     */
    public String getProprietePrestationRefDecompte() {
        return proprietePrestationRefDecompte;
    }

    /**
     * Définit la valeur de proprietePrestationRefDecompte.
     * @param proprietePrestationRefDecompte la nouvelle valeur de proprietePrestationRefDecompte
     */
    public void setProprietePrestationRefDecompte(String proprietePrestationRefDecompte) {
        this.proprietePrestationRefDecompte = proprietePrestationRefDecompte;
    }

    /**
     * Récupère la valeur de proprietePrestationDateSoins.
     * @return la valeur de proprietePrestationDateSoins
     */
    public String getProprietePrestationDateSoins() {
        return proprietePrestationDateSoins;
    }

    /**
     * Définit la valeur de proprietePrestationDateSoins.
     * @param proprietePrestationDateSoins la nouvelle valeur de proprietePrestationDateSoins
     */
    public void setProprietePrestationDateSoins(String proprietePrestationDateSoins) {
        this.proprietePrestationDateSoins = proprietePrestationDateSoins;
    }

    /**
     * Récupère la valeur de proprietePrestationActe.
     * @return la valeur de proprietePrestationActe
     */
    public String getProprietePrestationActe() {
        return proprietePrestationActe;
    }

    /**
     * Définit la valeur de proprietePrestationActe.
     * @param proprietePrestationActe la nouvelle valeur de proprietePrestationActe
     */
    public void setProprietePrestationActe(String proprietePrestationActe) {
        this.proprietePrestationActe = proprietePrestationActe;
    }

    /**
     * Récupère la valeur de proprietePrestationDepensesEngagees.
     * @return la valeur de proprietePrestationDepensesEngagees
     */
    public String getProprietePrestationDepensesEngagees() {
        return proprietePrestationDepensesEngagees;
    }

    /**
     * Définit la valeur de proprietePrestationDepensesEngagees.
     * @param proprietePrestationDepensesEngagees la nouvelle valeur de proprietePrestationDepensesEngagees
     */
    public void setProprietePrestationDepensesEngagees(String proprietePrestationDepensesEngagees) {
        this.proprietePrestationDepensesEngagees = proprietePrestationDepensesEngagees;
    }

    /**
     * Récupère la valeur de proprietePrestationRemboursementRO.
     * @return la valeur de proprietePrestationRemboursementRO
     */
    public String getProprietePrestationRemboursementRO() {
        return proprietePrestationRemboursementRO;
    }

    /**
     * Définit la valeur de proprietePrestationRemboursementRO.
     * @param proprietePrestationRemboursementRO la nouvelle valeur de proprietePrestationRemboursementRO
     */
    public void setProprietePrestationRemboursementRO(String proprietePrestationRemboursementRO) {
        this.proprietePrestationRemboursementRO = proprietePrestationRemboursementRO;
    }

    /**
     * Récupère la valeur de proprietePrestationRemboursementSmatis.
     * @return la valeur de proprietePrestationRemboursementSmatis
     */
    public String getProprietePrestationRemboursementSmatis() {
        return proprietePrestationRemboursementSmatis;
    }

    /**
     * Définit la valeur de proprietePrestationRemboursementSmatis.
     * @param proprietePrestationRemboursementSmatis la nouvelle valeur de proprietePrestationRemboursementSmatis
     */
    public void setProprietePrestationRemboursementSmatis(String proprietePrestationRemboursementSmatis) {
        this.proprietePrestationRemboursementSmatis = proprietePrestationRemboursementSmatis;
    }

    /**
     * Récupère la valeur de proprietePrestationResteACharge.
     * @return la valeur de proprietePrestationResteACharge
     */
    public String getProprietePrestationResteACharge() {
        return proprietePrestationResteACharge;
    }

    /**
     * Définit la valeur de proprietePrestationResteACharge.
     * @param proprietePrestationResteACharge la nouvelle valeur de proprietePrestationResteACharge
     */
    public void setProprietePrestationResteACharge(String proprietePrestationResteACharge) {
        this.proprietePrestationResteACharge = proprietePrestationResteACharge;
    }

    /**
     * Récupère la valeur de proprietePrestationNomDestinataire.
     * @return la valeur de proprietePrestationNomDestinataire
     */
    public String getProprietePrestationNomDestinataire() {
        return proprietePrestationNomDestinataire;
    }

    /**
     * Définit la valeur de proprietePrestationNomDestinataire.
     * @param proprietePrestationNomDestinataire la nouvelle valeur de proprietePrestationNomDestinataire
     */
    public void setProprietePrestationNomDestinataire(String proprietePrestationNomDestinataire) {
        this.proprietePrestationNomDestinataire = proprietePrestationNomDestinataire;
    }

    /**
     * Récupère la valeur de statutPaiementPaye.
     * @return la valeur de statutPaiementPaye
     */
    public String getStatutPaiementPaye() {
        return statutPaiementPaye;
    }

    /**
     * Définit la valeur de statutPaiementPaye.
     * @param statutPaiementPaye la nouvelle valeur de statutPaiementPaye
     */
    public void setStatutPaiementPaye(String statutPaiementPaye) {
        this.statutPaiementPaye = statutPaiementPaye;
    }

    /**
     * Getter function.
     * @return the orderByActionId
     */
    public String getOrderByActionId() {
        return orderByActionId;
    }

    /**
     * Getter function.
     * @return the orderByActionType
     */
    public String getOrderByActionType() {
        return orderByActionType;
    }

    /**
     * Getter function.
     * @return the orderByActionObjet
     */
    public String getOrderByActionObjet() {
        return orderByActionObjet;
    }

    /**
     * Getter function.
     * @return the orderByActionSsObjet
     */
    public String getOrderByActionSsObjet() {
        return orderByActionSsObjet;
    }

    /**
     * Getter function.
     * @return the orderByActionPriorite
     */
    public String getOrderByActionPriorite() {
        return orderByActionPriorite;
    }

    /**
     * Getter function.
     * @return the orderByActionStatut
     */
    public String getOrderByActionStatut() {
        return orderByActionStatut;
    }

    /**
     * Getter function.
     * @return the orderByActionAttributionAgence
     */
    public String getOrderByActionAttributionAgence() {
        return orderByActionAttributionAgence;
    }

    /**
     * Getter function.
     * @return the orderByActionAttributionRessource
     */
    public String getOrderByActionAttributionRessource() {
        return orderByActionAttributionRessource;
    }

    /**
     * Setter function.
     * @param orderByActionId the orderByActionId to set
     */
    public void setOrderByActionId(String orderByActionId) {
        this.orderByActionId = orderByActionId;
    }

    /**
     * Setter function.
     * @param orderByActionType the orderByActionType to set
     */
    public void setOrderByActionType(String orderByActionType) {
        this.orderByActionType = orderByActionType;
    }

    /**
     * Setter function.
     * @param orderByActionObjet the orderByActionObjet to set
     */
    public void setOrderByActionObjet(String orderByActionObjet) {
        this.orderByActionObjet = orderByActionObjet;
    }

    /**
     * Setter function.
     * @param orderByActionSsObjet the orderByActionSsObjet to set
     */
    public void setOrderByActionSsObjet(String orderByActionSsObjet) {
        this.orderByActionSsObjet = orderByActionSsObjet;
    }

    /**
     * Setter function.
     * @param orderByActionPriorite the orderByActionPriorite to set
     */
    public void setOrderByActionPriorite(String orderByActionPriorite) {
        this.orderByActionPriorite = orderByActionPriorite;
    }

    /**
     * Setter function.
     * @param orderByActionStatut the orderByActionStatut to set
     */
    public void setOrderByActionStatut(String orderByActionStatut) {
        this.orderByActionStatut = orderByActionStatut;
    }

    /**
     * Setter function.
     * @param orderByActionAttributionAgence the orderByActionAttributionAgence to set
     */
    public void setOrderByActionAttributionAgence(String orderByActionAttributionAgence) {
        this.orderByActionAttributionAgence = orderByActionAttributionAgence;
    }

    /**
     * Setter function.
     * @param orderByActionAttributionRessource the orderByActionAttributionRessource to set
     */
    public void setOrderByActionAttributionRessource(String orderByActionAttributionRessource) {
        this.orderByActionAttributionRessource = orderByActionAttributionRessource;
    }

    /**
     * Getter function.
     * @return the orderByActionDateCreation
     */
    public String getOrderByActionDateCreation() {
        return orderByActionDateCreation;
    }

    /**
     * Getter function.
     * @return the orderByActionDateAction
     */
    public String getOrderByActionDateAction() {
        return orderByActionDateAction;
    }

    /**
     * Getter function.
     * @return the orderByActionDateTerminee
     */
    public String getOrderByActionDateTerminee() {
        return orderByActionDateTerminee;
    }

    /**
     * Setter function.
     * @param orderByActionDateCreation the orderByActionDateCreation to set
     */
    public void setOrderByActionDateCreation(String orderByActionDateCreation) {
        this.orderByActionDateCreation = orderByActionDateCreation;
    }

    /**
     * Setter function.
     * @param orderByActionDateTerminee the orderByActionDateTerminee to set
     */
    public void setOrderByActionDateTerminee(String orderByActionDateTerminee) {
        this.orderByActionDateTerminee = orderByActionDateTerminee;
    }

    /**
     * Setter function.
     * @param orderByActionDateAction the orderByActionDateAction to set
     */
    public void setOrderByActionDateAction(String orderByActionDateAction) {
        this.orderByActionDateAction = orderByActionDateAction;
    }

    /**
     * Getter function.
     * @return the idStatutOpportuniteEnAttente
     */
    public Long getIdStatutOpportuniteEnAttente() {
        return idStatutOpportuniteEnAttente;
    }

    /**
     * Setter function.
     * @param idStatutOpportuniteEnAttente the idStatutOpportuniteEnAttente to set
     */
    public void setIdStatutOpportuniteEnAttente(Long idStatutOpportuniteEnAttente) {
        this.idStatutOpportuniteEnAttente = idStatutOpportuniteEnAttente;
    }

    /**
     * Récupère la valeur de idNaturePersonneDecede.
     * @return la valeur de idNaturePersonneDecede
     */
    public Long getIdNaturePersonneDecede() {
        return idNaturePersonneDecede;
    }

    /**
     * Définit la valeur de idNaturePersonneDecede.
     * @param idNaturePersonneDecede la nouvelle valeur de idNaturePersonneDecede
     */
    public void setIdNaturePersonneDecede(Long idNaturePersonneDecede) {
        this.idNaturePersonneDecede = idNaturePersonneDecede;
    }

    /**
     * Récupère la valeur de paysFrance.
     * @return la valeur de paysFrance
     */
    public PaysSimpleDto getPaysFrance() {
        return paysFrance;
    }

    /**
     * Définit la valeur de paysFrance.
     * @param paysFrance la nouvelle valeur de paysFrance
     */
    public void setPaysFrance(PaysSimpleDto paysFrance) {
        this.paysFrance = paysFrance;
    }

    /**
     * Get the urlServletExporterRecherche value.
     * @return the urlServletExporterRecherche
     */
    public String getUrlServletExporterRecherche() {
        return urlServletExporterRecherche;
    }

    /**
     * Set the urlServletExporterRecherche value.
     * @param urlServletExporterRecherche the urlServletExporterRecherche to set
     */
    public void setUrlServletExporterRecherche(String urlServletExporterRecherche) {
        this.urlServletExporterRecherche = urlServletExporterRecherche;
    }

    /**
     * Get the paramService value.
     * @return the paramService
     */
    public String getParamService() {
        return paramService;
    }

    /**
     * Set the paramService value.
     * @param paramService the paramService to set
     */
    public void setParamService(String paramService) {
        this.paramService = paramService;
    }

    /**
     * Get the valueServiceRechercheCampagne value.
     * @return the valueServiceRechercheCampagne
     */
    public String getValueServiceRechercheCampagne() {
        return valueServiceRechercheCampagne;
    }

    /**
     * Set the valueServiceRechercheCampagne value.
     * @param valueServiceRechercheCampagne the valueServiceRechercheCampagne to set
     */
    public void setValueServiceRechercheCampagne(String valueServiceRechercheCampagne) {
        this.valueServiceRechercheCampagne = valueServiceRechercheCampagne;
    }

    /**
     * Get the valueServiceRecherchePersonnePhysique value.
     * @return the valueServiceRecherchePersonnePhysique
     */
    public String getValueServiceRecherchePersonnePhysique() {
        return valueServiceRecherchePersonnePhysique;
    }

    /**
     * Set the valueServiceRecherchePersonnePhysique value.
     * @param valueServiceRecherchePersonnePhysique the valueServiceRecherchePersonnePhysique to set
     */
    public void setValueServiceRecherchePersonnePhysique(String valueServiceRecherchePersonnePhysique) {
        this.valueServiceRecherchePersonnePhysique = valueServiceRecherchePersonnePhysique;
    }

    /**
     * Get the valueServiceRechercheAction value.
     * @return the valueServiceRechercheAction
     */
    public String getValueServiceRechercheAction() {
        return valueServiceRechercheAction;
    }

    /**
     * Set the valueServiceRechercheAction value.
     * @param valueServiceRechercheAction the valueServiceRechercheAction to set
     */
    public void setValueServiceRechercheAction(String valueServiceRechercheAction) {
        this.valueServiceRechercheAction = valueServiceRechercheAction;
    }

    /**
     * Get the valueServiceRechercheRessource value.
     * @return the valueServiceRechercheRessource
     */
    public String getValueServiceRechercheRessource() {
        return valueServiceRechercheRessource;
    }

    /**
     * Set the valueServiceRechercheRessource value.
     * @param valueServiceRechercheRessource the valueServiceRechercheRessource to set
     */
    public void setValueServiceRechercheRessource(String valueServiceRechercheRessource) {
        this.valueServiceRechercheRessource = valueServiceRechercheRessource;
    }

    /**
     * Get the valueServiceRecherchePersonneMorale value.
     * @return the valueServiceRecherchePersonneMorale
     */
    public String getValueServiceRecherchePersonneMorale() {
        return valueServiceRecherchePersonneMorale;
    }

    /**
     * Set the valueServiceRecherchePersonneMorale value.
     * @param valueServiceRecherchePersonneMorale the valueServiceRecherchePersonneMorale to set
     */
    public void setValueServiceRecherchePersonneMorale(String valueServiceRecherchePersonneMorale) {
        this.valueServiceRecherchePersonneMorale = valueServiceRecherchePersonneMorale;
    }

    /**
     * Getter function.
     * @return the idNatureMobilePrive
     */
    public Long getIdNatureMobilePrive() {
        return idNatureMobilePrive;
    }

    /**
     * Setter function.
     * @param idNatureMobilePrive the idNatureMobilePrive to set
     */
    public void setIdNatureMobilePrive(Long idNatureMobilePrive) {
        this.idNatureMobilePrive = idNatureMobilePrive;
    }

    /**
     * Récupère la valeur de idObjetActionEditique.
     * @return la valeur de idObjetActionEditique
     */
    public Long getIdObjetActionEditique() {
        return idObjetActionEditique;
    }

    /**
     * Définit la valeur de idObjetActionEditique.
     * @param idObjetActionEditique la nouvelle valeur de idObjetActionEditique
     */
    public void setIdObjetActionEditique(Long idObjetActionEditique) {
        this.idObjetActionEditique = idObjetActionEditique;
    }

    /**
     * Récupère la valeur de idSousObjetActionEditionBa.
     * @return la valeur de idSousObjetActionEditionBa
     */
    public Long getIdSousObjetActionEditionBa() {
        return idSousObjetActionEditionBa;
    }

    /**
     * Définit la valeur de idSousObjetActionEditionBa.
     * @param idSousObjetActionEditionBa la nouvelle valeur de idSousObjetActionEditionBa
     */
    public void setIdSousObjetActionEditionBa(Long idSousObjetActionEditionBa) {
        this.idSousObjetActionEditionBa = idSousObjetActionEditionBa;
    }

    /**
     * Récupère la valeur de idSousObjetActionEnvoiBaParMail.
     * @return la valeur de idSousObjetActionEnvoiBaParMail
     */
    public Long getIdSousObjetActionEnvoiBaParMail() {
        return idSousObjetActionEnvoiBaParMail;
    }

    /**
     * Définit la valeur de idSousObjetActionEnvoiBaParMail.
     * @param idSousObjetActionEnvoiBaParMail la nouvelle valeur de idSousObjetActionEnvoiBaParMail
     */
    public void setIdSousObjetActionEnvoiBaParMail(Long idSousObjetActionEnvoiBaParMail) {
        this.idSousObjetActionEnvoiBaParMail = idSousObjetActionEnvoiBaParMail;
    }

    /**
     * Récupère la valeur de idEtatActifRessource.
     * @return la valeur de idEtatActifRessource
     */
    public Long getIdEtatActifRessource() {
        return idEtatActifRessource;
    }

    /**
     * Définit la valeur de idEtatActifRessource.
     * @param idEtatActifRessource la nouvelle valeur de idEtatActifRessource
     */
    public void setIdEtatActifRessource(Long idEtatActifRessource) {
        this.idEtatActifRessource = idEtatActifRessource;
    }

    /**
     * Récupère la valeur de idTypeActionRelance.
     * @return la valeur de idTypeActionRelance
     */
    public Long getIdTypeActionRelance() {
        return idTypeActionRelance;
    }

    /**
     * Définit la valeur de idTypeActionRelance.
     * @param idTypeActionRelance la nouvelle valeur de idTypeActionRelance
     */
    public void setIdTypeActionRelance(Long idTypeActionRelance) {
        this.idTypeActionRelance = idTypeActionRelance;
    }

    /**
     * Récupère la valeur de idNatureActionEmailSortant.
     * @return la valeur de idNatureActionEmailSortant
     */
    public Long getIdNatureActionEmailSortant() {
        return idNatureActionEmailSortant;
    }

    /**
     * Définit la valeur de idNatureActionEmailSortant.
     * @param idNatureActionEmailSortant la nouvelle valeur de idNatureActionEmailSortant
     */
    public void setIdNatureActionEmailSortant(Long idNatureActionEmailSortant) {
        this.idNatureActionEmailSortant = idNatureActionEmailSortant;
    }

    /**
     * Récupère la valeur de idNatureActionInterne.
     * @return la valeur de idNatureActionInterne
     */
    public Long getIdNatureActionInterne() {
        return idNatureActionInterne;
    }

    /**
     * Définit la valeur de idNatureActionInterne.
     * @param idNatureActionInterne la nouvelle valeur de idNatureActionInterne
     */
    public void setIdNatureActionInterne(Long idNatureActionInterne) {
        this.idNatureActionInterne = idNatureActionInterne;
    }

    /**
     * Récupère la valeur de idLibelleEtatActifRessource.
     * @return la valeur de idLibelleEtatActifRessource
     */
    public IdentifiantLibelleDto getIdLibelleEtatActifRessource() {
        return idLibelleEtatActifRessource;
    }

    /**
     * Définit la valeur de idLibelleEtatActifRessource.
     * @param idLibelleEtatActifRessource la nouvelle valeur de idLibelleEtatActifRessource
     */
    public void setIdLibelleEtatActifRessource(IdentifiantLibelleDto idLibelleEtatActifRessource) {
        this.idLibelleEtatActifRessource = idLibelleEtatActifRessource;
    }

    /**
     * Accesseur pour l'attribut idNatureActionCourrierSortant.
     * @return l'attribut idNatureActionCourrierSortant
     */
    public Long getIdNatureActionCourrierSortant() {
        return idNatureActionCourrierSortant;
    }

    /**
     * Définit la valeur de idNatureActionCourrierSortant.
     * @param idNatureActionCourrierSortant la nouvelle valeur de idNatureActionCourrierSortant
     */
    public void setIdNatureActionCourrierSortant(Long idNatureActionCourrierSortant) {
        this.idNatureActionCourrierSortant = idNatureActionCourrierSortant;
    }

    /**
     * Get the hasRoleAnimateur value.
     * @return the hasRoleAnimateur
     */
    public boolean isHasRoleAnimateur() {
        return hasRoleAnimateur;
    }

    /**
     * Set the hasRoleAnimateur value.
     * @param hasRoleAnimateur the hasRoleAnimateur to set
     */
    public void setHasRoleAnimateur(boolean hasRoleAnimateur) {
        this.hasRoleAnimateur = hasRoleAnimateur;
    }

    /**
     * Récupère la valeur de hasRoleGestionnaireOpportunites.
     * @return la valeur de hasRoleGestionnaireOpportunites
     */
    public boolean isHasRoleGestionnaireOpportunites() {
        return hasRoleGestionnaireOpportunites;
    }

    /**
     * Définit la valeur de hasRoleGestionnaireOpportunites.
     * @param hasRoleGestionnaireOpportunites la nouvelle valeur de hasRoleGestionnaireOpportunites
     */
    public void setHasRoleGestionnaireOpportunites(boolean hasRoleGestionnaireOpportunites) {
        this.hasRoleGestionnaireOpportunites = hasRoleGestionnaireOpportunites;
    }

    /**
     * Getter.
     * @return the idAgenceFrance
     */
    public Long getIdAgenceFrance() {
        return idAgenceFrance;
    }

    /**
     * Setter.
     * @param idAgenceFrance the idAgenceFrance to set
     */
    public void setIdAgenceFrance(Long idAgenceFrance) {
        this.idAgenceFrance = idAgenceFrance;
    }

    /**
     * Getter function.
     * @return the idEtatInactifRessource
     */
    public Long getIdEtatInactifRessource() {
        return idEtatInactifRessource;
    }

    /**
     * Setter function.
     * @param idEtatInactifRessource the idEtatInactifRessource to set
     */
    public void setIdEtatInactifRessource(Long idEtatInactifRessource) {
        this.idEtatInactifRessource = idEtatInactifRessource;
    }

    /**
     * Setter function.
     * @param hasRoleAdmin the hasRoleAdmin to set
     */
    public void setHasRoleAdmin(boolean hasRoleAdmin) {
        this.hasRoleAdmin = hasRoleAdmin;
    }

    /**
     * Getter function.
     * @return the hasRoleAdmin
     */
    public boolean isHasRoleAdmin() {
        return hasRoleAdmin;
    }

    /**
     * Getter function.
     * @return the idNatureAdresseSecondaire
     */
    public Long getIdNatureAdresseSecondaire() {
        return idNatureAdresseSecondaire;
    }

    /**
     * Setter function.
     * @param idNatureAdresseSecondaire the idNatureAdresseSecondaire to set
     */
    public void setIdNatureAdresseSecondaire(Long idNatureAdresseSecondaire) {
        this.idNatureAdresseSecondaire = idNatureAdresseSecondaire;
    }

    /**
     * Get the value of idNaturePersonneVivier.
     * @return the idNaturePersonneVivier
     */
    public Long getIdNaturePersonneVivier() {
        return idNaturePersonneVivier;
    }

    /**
     * Set the value of idNaturePersonneVivier.
     * @param idNaturePersonneVivier the idNaturePersonneVivier to set
     */
    public void setIdNaturePersonneVivier(Long idNaturePersonneVivier) {
        this.idNaturePersonneVivier = idNaturePersonneVivier;
    }

    /**
     * Récupère la valeur de idNaturePersonneBeneficiaireVivier.
     * @return la valeur de idNaturePersonneBeneficiaireVivier
     */
    public Long getIdNaturePersonneBeneficiaireVivier() {
        return idNaturePersonneBeneficiaireVivier;
    }

    /**
     * Définit la valeur de idNaturePersonneBeneficiaireVivier.
     * @param idNaturePersonneBeneficiaireVivier la nouvelle valeur de idNaturePersonneBeneficiaireVivier
     */
    public void setIdNaturePersonneBeneficiaireVivier(Long idNaturePersonneBeneficiaireVivier) {
        this.idNaturePersonneBeneficiaireVivier = idNaturePersonneBeneficiaireVivier;
    }

    /**
     * Get the value of idNaturePersonneMoraleAdherent.
     * @return the idNaturePersonneMoraleAdherent
     */
    public Long getIdNaturePersonneMoraleAdherent() {
        return idNaturePersonneMoraleAdherent;
    }

    /**
     * Set the value of idNaturePersonneMoraleAdherent.
     * @param idNaturePersonneMoraleAdherent the idNaturePersonneMoraleAdherent to set
     */
    public void setIdNaturePersonneMoraleAdherent(Long idNaturePersonneMoraleAdherent) {
        this.idNaturePersonneMoraleAdherent = idNaturePersonneMoraleAdherent;
    }

    /**
     * Get the value of idFonctionCC.
     * @return the idFonctionCC
     */
    public Long getIdFonctionCC() {
        return idFonctionCC;
    }

    /**
     * Set the value of idFonctionCC.
     * @param idFonctionCC the idFonctionCC to set
     */
    public void setIdFonctionCC(Long idFonctionCC) {
        this.idFonctionCC = idFonctionCC;
    }

}
