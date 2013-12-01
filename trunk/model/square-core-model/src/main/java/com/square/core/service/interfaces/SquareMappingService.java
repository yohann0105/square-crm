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
package com.square.core.service.interfaces;

import java.util.Calendar;
import java.util.List;

import com.square.core.model.dto.ActionNotificationInfosDto;
import com.square.core.model.dto.ConstantesDto;
import com.square.core.model.dto.NumeroRoDto;

/**
 * Interface du service pour le mapping des constantes de l'application.
 * @author cblanchard - SCUB
 */
public interface SquareMappingService {

    /**
     * Renvoi les constantes.
     * @return les constantes
     */
    ConstantesDto getConstantes();

    /**
     * Renvoi la valeur de idPaysFrance.
     * @return idPaysFrance
     */
    Long getIdPaysFrance();

    /**
     * Renvoi la valeur de idPaysFranceMetropolitaine.
     * @return idPaysFranceMetropolitaine
     */
    Long getIdPaysFranceMetropolitaine();

    /**
     * Renvoi la valeur de idPaysParDefaut.
     * @return idPaysParDefaut
     */
    Long getIdPaysParDefaut();

    /**
     * Renvoi la valeur de idNatureAdressePrincipale.
     * @return idNatureAdressePrincipale
     */
    Long getIdNatureAdressePrincipale();

    /**
     * Renvoi la valeur de idNatureAdresseSecondaire.
     * @return idNatureAdresseSecondaire
     */
    Long getIdNatureAdresseSecondaire();

    /**
     * Renvoi la valeur de idNatureEmailPersonnel.
     * @return idNatureEmailPersonnel
     */
    Long getIdNatureEmailPersonnel();

    /**
     * Renvoi la valeur de idTypeRelationConjoint.
     * @return idTypeRelationConjoint
     */
    Long getIdTypeRelationConjoint();

    /**
     * Renvoi la valeur de idTypeRelationEnfant.
     * @return idTypeRelationEnfant
     */
    Long getIdTypeRelationEnfant();

    /**
     * Renvoi la valeur de idRessource.
     * @return idRessource TODO constante temporaire
     */
    Long getIdRessource();

    /**
     * Convertir le numero de régime obligatoire en numero de sécurité social et clé.
     * @param nro le numero de régime obligatoire
     * @return le dto du numéro de regime.
     * @author mlamine - SCUB
     */
    NumeroRoDto convertirNroVersNss(String nro);

    /**
     * Converti le numero de sécurité social et la clé en numero de régime obligatoire.
     * @param nss le numero de sécurité social
     * @param cless la clé.
     * @return le numéro de regime.
     * @author mlamine - SCUB
     */
    String convertirNssVersNro(String nss, String cless);

    /**
     * Renvoie la valeur de idGroupementFamille.
     * @return idGroupementFamille
     */
    Long getIdGroupementFamille();

    /**
     * Renvoie la valeur de idGroupementTiers.
     * @return idGroupementTiers
     */
    Long getIdGroupementTiers();

    /**
     * Renvoie la valeur de l'identifiant du groupement professionnel.
     * @return l'identifiant du groupement professionnel
     */
    Long getIdGroupementProfessionnel();

    /**
     * Renvoie la nature par défaut d'une personne.
     * @return l'id de la nature
     */
    Long getIdNaturePersonneParDefaut();

    /**
     * Renvoie la nature par défaut d'une personne.
     * @return l'id de la nature
     */
    // FIXME : cette constante n'est pas assez précise (benef vivier, prospect ou adherent) et pointe vers la mauvaise valeur (adherent)
    Long getIdNaturePersonneBeneficiaire();

    /**
     * Renvoie la profession par défaut d'une personne.
     * @return l'id de la nature
     */
    Long getIdProfessionPersonneParDefaut();

    /**
     * Renvoie le segment par défaut d'une personne.
     * @return l'id de la nature
     */
    Long getIdSegmentPersonneParDefaut();

    /**
     * Renvoie le segment individuel d'une personne.
     * @return l'id de la nature
     */
    Long getIdSegmentIndividuel();

    /**
     * Renvoie la situation familiale par défaut d'une personne.
     * @return l'id de la nature
     */
    Long getIdSituationFamilialePersonneParDefaut();

    /**
     * Renvoie le réseau de vente par défaut d'une personne.
     * @return l'id de la nature
     */
    Long getIdReseauVentePersonneParDefaut();

    /**
     * Renvoie la CSP par défaut d'une personne.
     * @return l'id de la nature
     */
    Long getIdCSPPersonneParDefaut();

    /**
     * Renvoie la valeur du téléphone fixe.
     * @return idNatureTelephoneFixe
     */
    Long getIdNatureTelephoneFixe();

    /**
     * Renvoie la valeur du téléphone erroné.
     * @return idNatureTelephoneFixe
     */
    Long getIdNatureTelephoneErrone();

    /**
     * Renvoie la valeur du mobile privé.
     * @return idNatureMobilePrive
     */
    Long getIdNatureMobilePrive();

    /**
     * Renvoie la valeur du mobile travail.
     * @return idNatureMobileTravail
     */
    Long getIdNatureMobileTravail();

    /**
     * Renvoie la valeur du téléphone fixe du travail.
     * @return la valeur du téléphone fixe du travail
     */
    Long getIdNatureTelephoneFixeTravail();

    /**
     * Renvoie la valeur du fax privé.
     * @return la valeur du fax privé
     */
    Long getIdNatureFaxPrive();

    /**
     * Renvoi la valeur de idStatutCampagneActive.
     * @return idStatutCampagneActive
     */
    Long getIdStatutCampagneActive();

    /**
     * Recuperer la liste des notifications.
     * @return la liste des notifications
     */
    List<ActionNotificationInfosDto> getListeActionNotifications();

    /**
     * Recuperer la liste des notifications.
     * @param id l'identifiant de la nortification
     * @return la notification
     */
    ActionNotificationInfosDto getActionNotificationParId(Long id);

    /**
     * Récupère l'identifiant correspondant à la différence entre deux dates.
     * @param dateAction la date d'action
     * @param dateNotification la date de notification
     * @return l'identifiant correspondant à la différence entre les deux dates
     */
    Long getIdNotification(Calendar dateAction, Calendar dateNotification);

    /**
     * Renvoie la priorité par défaut d'une action.
     * @return la priorité par defaut
     */
    Long getIdActionPrioriteParDefaut();

    /**
     * Renvoie l'identifiant de la priorité "haute" d'une action.
     * @return la priorité haute
     */
    Long getIdActionPrioriteHaute();

    /**
     * Renvoie l'identifiant de la priorité "urgente" d'une action.
     * @return l'identifiant de la priorité "urgente"
     */
    Long getIdActionPrioriteUrgente();

    /**
     * Renvoie la liste des natures nature du téléphone mobile.
     * @return idNatureTelephoneMobile
     */
    List<Long> getListeIdsNaturesTelephoneMobile();

    /**
     * Renvoi la valeur de idStatutActionParDefaut.
     * @return idStatutActionParDefaut
     */
    Long getIdStatutActionParDefaut();

    /**
     * Renvoi la valeur de idStatutActionTermine.
     * @return idStatutActionParDefaut
     */
    Long getIdStatutActionTermine();

    /**
     * Getter function.
     * @return the idStatutActionAFaire
     */
    Long getIdStatutActionAFaire();

    /**
     * Getter function.
     * @return the idStatutActionAnnule
     */
    Long getIdStatutActionAnnule();

    /**
     * Renvoi la valeur de idCiviliteMonsieur.
     * @return idCiviliteMonsieur
     */
    Long getIdCiviliteMonsieur();

    /**
     * Renvoi la valeur de idCiviliteMadame.
     * @return idCiviliteMadame
     */
    Long getIdCiviliteMadame();

    /**
     * Renvoi la valeur de idCiviliteMademoiselle.
     * @return idCiviliteMademoiselle
     */
    Long getIdCiviliteMademoiselle();

    /**
     * Renvoi la valeur de idCiviliteNonRenseigne.
     * @return the idCiviliteNonRenseigne
     */
    Long getIdCiviliteNonRenseigne();

    /**
     * Renvoi l'identifiant du résultat opportunité.
     * @return l'identifiant du résultat opportunité
     */
    Long getIdResultatOpportunite();

    /**
     * Renvoi l'identifiant du résultat relance.
     * @return l'identifiant du résultat relance
     */
    Long getIdResultatRelance();

    /**
     * Renvoi l'identifiant du statut terminer.
     * @return l'identifiant du statut terminer
     */
    Long getIdStatutTerminer();

    /**
     * Renvoi l'identifiant du à faire.
     * @return l'identifiant du statut à faire
     */
    Long getIdStatutAFaire();

    /**
     * Renvoi l'identifiant du statut annuler.
     * @return l'identifiant du statut annuler
     */
    Long getIdStatutAnnuler();

    /**
     * Renvoi l'identifiant de l'objet d'action pour l'éditique.
     * @return l'identifiant de l'objet d'action
     */
    Long getIdObjetActionEditique();

    /**
     * Renvoi l'identifiant de l'objet d'action pour la transformation Aia.
     * @return l'identifiant de l'objet d'action
     */
    Long getIdObjetActionTransformationAia();

    /**
     * Renvoi l'identifiant de l'objet d'action pour une nouvelle adhésion.
     * @return l'identifiant de l'objet d'action
     */
    Long getIdObjetActionNouvelleAdhesion();

    /**
     * Renvoi l'identifiant de l'objet d'action pour un client.
     * @return l'identifiant de l'objet d'action
     */
    Long getIdObjetActionClient();

    /**
     * Renvoi l'identifiant de le type d'action Opportunité.
     * @return l'identifiant de le type d'action
     */
    Long getIdTypeActionOpportunite();

    /**
     * Type action modification.
     * @return .
     */
    Long getIdTypeActionModification();

    /**
     * Renvoi l'identifiant de le type d'action Document.
     * @return l'identifiant de le type d'action
     */
    Long getIdTypeActionDocument();

    /**
     * Renvoi l'identifiant du type d'action "Cotisation".
     * @return l'identifiant.
     */
    Long getIdTypeActionCotisation();

    /**
     * Renvoi l'identifiant du type d'action "E-Services".
     * @return l'identifiant.
     */
    Long getIdTypeActionEServices();

    /**
     * Renvoi l'identifiant du type d'action "Prestations Sinistres".
     * @return l'identifiant.
     */
    Long getIdTypeActionPrestationsSinistres();

    /**
     * Renvoi l'identifiant du type d'action "Radiation".
     * @return l'identifiant.
     */
    Long getIdTypeActionRadiation();

    /**
     * Renvoi l'identifiant du type d'action "Radiation".
     * @return l'identifiant.
     */
    Long getIdTypeActionRecoursGracieux();

    /**
     * Renvoi l'identifiant du type d'action "LAB".
     * @return l'identifiant.
     */
    Long getIdTypeActionLab();

    /**
     * Renvoie l'identifiant de la nature d'action Internet.
     * @return l'identifiant de la nature d'action
     */
    Long getIdNatureActionInternet();

    /**
     * Renvoi l'identifiant de la nature d'action Téléphone Sortant.
     * @return l'identifiant de la nature d'action
     */
    Long getIdNatureActionTelephoneSortant();

    /**
     * Renvoi l'identifiant de la nature d'action Téléphone Sortant.
     * @return l'identifiant de la nature d'action
     */
    Long getIdNatureActionVisiteSortante();

    /**
     * Renvoi l'identifiant de la nature d'action Courrier Entrant.
     * @return l'identifiant de la nature d'action
     */
    Long getIdNatureActionCourrierEntrant();

    /**
     * Renvoi l'identifiant de la nature d'action Courrier Sortant.
     * @return l'identifiant de la nature d'action
     */
    Long getIdNatureActionCourrierSortant();

    /**
     * Récupère l'identifiant de la nature d'une action interne.
     * @return l'identifiant.
     */
    Long getIdNatureActionInterne();

    /**
     * Renvoi l'identifiant de la nature d'action Email Entrant.
     * @return l'identifiant de la nature d'action
     */
    Long getIdNatureActionEmailEntrant();

    /**
     * Renvoi l'identifiant de la nature d'action Email Sortant.
     * @return l'identifiant de la nature d'action
     */
    Long getIdNatureActionEmailSortant();

    /**
     * Renvoi l'identifiant de la nature d'action Achat Fichiers.
     * @return l'identifiant de la nature d'action
     */
    Long getIdNatureActionAchatFichiers();

    /**
     * Getter function.
     * @return the idTypeActionRelance
     */
    Long getIdTypeActionRelance();

    /**
     * Getter function.
     * @return the idStatutOpportuniteNonRenseigne
     */
    Long getIdStatutOpportuniteNonRenseigne();

    /**
     * Getter function.
     * @return the idStatutOpportuniteRefuse
     */
    Long getIdStatutOpportuniteRefuse();

    /**
     * Getter function.
     * @return the idStatutOpportuniteAccepte
     */
    Long getIdStatutOpportuniteAccepte();

    /**
     * Getter function.
     * @return the idStatutOpportuniteCorbeille
     */
    Long getIdStatutOpportuniteCorbeille();

    /**
     * Getter function.
     * @return the idStatutOpportuniteTransforme
     */
    Long getIdStatutOpportuniteTransforme();

    /**
     * Renvoi le libellé du type d'une apportunité.
     * @return le libellé du type d'une apportunité.
     */
    String getLibelleTypeActionOpportunite();

    /**
     * Accesseur sur la propriété du numéro de client de la personne.
     * @return la propriété.
     */
    String getProprietePersonneNumeroClient();

    /**
     * Accesseur sur la propriété du nom de la personne.
     * @return la propriété.
     */
    String getProprietePersonneNom();

    /**
     * Accesseur sur la propriété du prénom de la personne.
     * @return la propriété.
     */
    String getProprietePersonnePrenom();

    /**
     * Accesseur sur la propriété de la date de la personne.
     * @return la propriété.
     */
    String getProprietePersonneDateNaissance();

    /**
     * Accesseur sur la propriété du segment de la personne.
     * @return la propriété.
     */
    String getProprietePersonneSegment();

    /**
     * Accesseur sur la propriété de la nature de la personne.
     * @return la propriété.
     */
    String getProprietePersonneNature();

    /**
     * Accesseur sur la propriété du code postal de la personne.
     * @return la propriété.
     */
    String getProprietePersonneCodePostal();

    /**
     * Accesseur sur la propriété de la commune de la personne.
     * @return la propriété.
     */
    String getProprietePersonneCommune();

    /**
     * Accesseur sur la propriété du commercial de la personne.
     * @return la propriété.
     */
    String getProprietePersonneCommercial();

    /**
     * Accesseur sur la propriété du commercial de la personne.
     * @return la propriété.
     */
    String getProprietePersonneAgence();

    /**
     * Accesseur sur la propriété de la date de création de la personne.
     * @return la propriété.
     */
    String getProprietePersonneDateCreation();

    /**
     * Accesseur sur l'identifiant de la nature d'une personne de type vivier.
     * @return l'identifiant.
     */
    Long getIdNaturePersonneVivier();

    /**
     * Accesseur sur l'identifiant de la nature d'une personne de type prospect.
     * @return l'identifiant.
     */
    Long getIdNaturePersonneProspect();

    /**
     * Accesseur sur l'identifiant de la nature d'une personne de type adhérent.
     * @return l'identifiant.
     */
    Long getIdNaturePersonneAdherent();

    /**
     * Accesseur sur l'identifiant de la nature d'une personne de type assure social.
     * @return l'identifiant.
     */
    Long getIdNaturePersonneAssureSocial();

    /**
     * Accesseur sur le role square batch.
     * @return le role.
     */
    String getRoleSquareBatch();

    /**
     * Accesseur sur l'identifiant de la nature de personne de type bénéficiaire prospect.
     * @return le role.
     */
    Long getIdNaturePersonneBeneficiaireProspect();

    /**
     * Accesseur sur l'identifiant de la nature de personne de type bénéficiaire vivier.
     * @return le role.
     */
    Long getIdNaturePersonneBeneficiaireVivier();

    /**
     * Identifiant du réseau de vente courtage.
     * @return l'identifiant
     */
    Long getIdReseauVenteCourtage();

    /**
     * Accesseur rôle Square Campagne.
     * @return le rôle.
     */
    String getRoleSquareCampagne();

    /**
     * Accesseur rôle Square Animateur.
     * @return le rôle.
     */
    String getRoleSquareAnimateur();

    /**
     * Recupere l'identifiant du type filleul en cours de création.
     * @return l'identifiant
     */
    Long getIdTypeRelationFilleulEnCoursCreation();

    /**
     * Recupere l'identifiant du type filleul en cours de création.
     * @return l'identifiant
     */
    Long getIdTypeRelationFilleulGarantieIncompatible();

    /**
     * Recupere l'identifiant du type filleul en cours de création.
     * @return l'identifiant
     */
    Long getIdTypeRelationFilleulDejaAdherent();

    /**
     * Récupère l'identifiant du type de relation filleul (FP).
     * @return l'identifiant du type.
     */
    Long getIdTypeRelationFilleul();

    /**
     * Getter function.
     * @return the orderByRelationType
     */
    String getOrderByRelationActif();

    /**
     * Getter function.
     * @return the orderByRelationType
     */
    String getOrderByRelationTypeOrdre();

    /**
     * Getter function.
     * @return the orderByRelationDateDebut
     */
    String getOrderByRelationDateDebut();

    /**
     * Getter function.
     * @return the orderByRelationDateFin
     */
    String getOrderByRelationDateFin();

    /**
     * Récupère l'identifiant du groupement de type en cours parrainage (Z).
     * @return l'identifiant du groupement.
     */
    Long getIdGroupementRelationTypeEnCoursParrainage();

    /**
     * Récupère l'identifiant du groupement de type echec parrainage garantie incompatible (X).
     * @return l'identifiant du groupement.
     */
    Long getIdGroupementRelationTypeEchecParrainageGarantie();

    /**
     * Récupère l'identifiant du groupement de type echec parrainage deja adherent (Y).
     * @return l'identifiant du groupement.
     */
    Long getIdGroupementRelationTypeEchecParrainageAdherent();

    /**
     * Recupère le libelle non renseigné.
     * @return le libelle
     */
    String getLibelleNonRenseigne();

    /**
     * Recupère le libelle a pour enfant.
     * @return le libelle
     */
    String getLibelleAPourEnfant();

    /**
     * Valeur de la télétransmission pour Oui.
     * @return la valeur
     */
    String getInfosTeletransmissionCaisseOui();

    /**
     * Valeur de la télétransmission pour Non.
     * @return la valeur
     */
    String getInfosTeletransmissionCaisseNon();

    /**
     * Id du type d'action commercial.
     * @return la valeur
     */
    Long getIdTypeActionCommercial();

    /**
     * Id de l'objet d'action relance.
     * @return la valeur
     */
    Long getIdObjetActionRelance();

    /**
     * Id de l'objet d'action relance.
     * @return la valeur
     */
    Long getIdObjetModificationAdressePostale();

    /**
     * Id de l'objet d'action courrier.
     * @return la valeur
     */
    Long getIdObjetActionCourrier();

    /**
     * Id de l'objet d'action "Justificatif d'identité".
     * @return la valeur
     */
    Long getIdObjetActionJustificatifIdentite();

    /**
     * Id de l'objet d'action "Contrôle".
     * @return la valeur
     */
    Long getIdObjetActionControle();

    /**
     * Identifiant objet action "changement composition familiale".
     * @return l'identifiant
     */
    Long getIdObjetActionChgtCompoFamiliale();

    /**
     * Accesseur identifiant objet action "Demande devis ou PEC".
     * @return l'identifiant
     */
    Long getIdObjetActionDemandeDevisOuPEC();

    /**
     * Id du sous objet d'action relance parrainage.
     * @return la valeur
     */
    Long getIdSousObjetActionRelanceParrainage();

    /**
     * Id du sous objet d'action relance.
     * @return la valeur
     */
    Long getIdSousObjetActionRelance();

    /**
     * Id du sous objet d'action édition de BA.
     * @return la valeur
     */
    Long getIdSousObjetActionEditionBa();

    /**
     * Id du sous objet d'action envoi de BA par mail.
     * @return la valeur
     */
    Long getIdSousObjetActionEnvoiBaParMail();

    /**
     * Id du sous objet d'action "Sans changement de département".
     * @return la valeur
     */
    Long getIdSousObjetActionSansChangementDepartement();

    /**
     * Id du sous objet d'action "Sans changement de département".
     * @return la valeur
     */
    Long getIdSousObjetActionAvecChangementDepartement();

    /**
     * Identifiant du sous objet d'action "adhésion en ligne".
     * @return la valeur
     */
    Long getIdSousObjetActionAdhesionEnLigne();

    /**
     * Accesseur sur la propriété de la référence de décompte de la prestation.
     * @return la propriété.
     */
    String getProprietePrestationRefDecompte();

    /**
     * Accesseur sur la propriété de la date de soin de la prestation.
     * @return la propriété.
     */
    String getProprietePrestationDateSoins();

    /**
     * Accesseur sur la propriété de l'acte de la prestation.
     * @return la propriété.
     */
    String getProprietePrestationActe();

    /**
     * Accesseur sur la propriété des dépenses engagées de la prestation.
     * @return la propriété.
     */
    String getProprietePrestationDepensesEngagees();

    /**
     * Accesseur sur la propriété du remboursement RO de la prestation.
     * @return la propriété.
     */
    String getProprietePrestationRemboursementRO();

    /**
     * Accesseur sur la propriété du remboursement Smatis de la prestation.
     * @return la propriété.
     */
    String getProprietePrestationRemboursementSmatis();

    /**
     * Accesseur sur la propriété du reste à charge de la prestation.
     * @return la propriété.
     */
    String getProprietePrestationResteACharge();

    /**
     * Accesseur sur la propriété du nom du destinataire de la prestation.
     * @return la propriété.
     */
    String getProprietePrestationNomDestinataire();

    /**
     * Accesseur sur le statut de paiement payé.
     * @return le statut.
     */
    String getStatutPaiementPaye();

    /**
     * Getter function.
     * @return the orderByActionType
     */
    String getOrderByActionType();

    /**
     * Getter function.
     * @return the orderByActionObjet
     */
    String getOrderByActionObjet();

    /**
     * Getter function.
     * @return the orderByActionSsObjet
     */
    String getOrderByActionSsObjet();

    /**
     * Getter function.
     * @return the orderByActionPriorite
     */
    String getOrderByActionPriorite();

    /**
     * Getter function.
     * @return the orderByActionStatut
     */
    String getOrderByActionStatut();

    /**
     * Getter function.
     * @return the orderByActionAttributionAgence
     */
    String getOrderByActionAttributionAgence();

    /**
     * Getter function.
     * @return the orderByActionAttributionRessource
     */
    String getOrderByActionAttributionRessource();

    /**
     * Getter function.
     * @return the orderByActionId
     */
    String getOrderByActionId();

    /**
     * Getter function.
     * @return the orderByActionDateCreation
     */
    String getOrderByActionDateCreation();

    /**
     * Getter function.
     * @return the orderByActionDateAction
     */
    String getOrderByActionDateAction();

    /**
     * Getter function.
     * @return the orderByActionDateTerminee
     */
    String getOrderByActionDateTerminee();

    /**
     * Getter function.
     * @return the idNaturePersonneDecede
     */
    Long getIdNaturePersonneDecede();

    /**
     * Getter function.
     * @return the idStatutOpportuniteEnAttente
     */
    Long getIdStatutOpportuniteEnAttente();

    /**
     * Récupère le délais à rajouter pour l'affichage d'une action.
     * @return le delais.
     */
    int getDecalageDateAffichageAction();

    /**
     * Récupère le décalage à rajouter pour la date de l'action de relance d'une opportunité.
     * @return le delais.
     */
    int getDecalageDateActionRelance();

    /**
     * Récupère la liste des identifiants pour la loi madelin.
     * @return la liste.
     */
    List<Long> getListeIdsStatutsLoiMadelin();

    /**
     * Récupère l'identifiant de l'état actif d'une ressource.
     * @return la liste.
     */
    Long getIdEtatActifRessource();

    /**
     * Récupère l'identifiant de l'état inactif d'une ressource.
     * @return la liste.
     */
    Long getIdEtatInactifRessource();

    /**
     * Récupère l'identifiant du rôle de gestionnaire des opportunités.
     * @return l'identifiant du rôle de gestionnaire des opportunités.
     */
    Long getIdentifiantRoleGestionnaireOpportunites();

    /**
     * Récupère l'identifiant de l'agence France.
     * @return l'identifiant de l'agence France.
     */
    Long getIdentifiantAgenceFrance();

    /**
     * Récupère l'identifiant de la fonction CMP pour les ressources.
     * @return lidentifiant de la fonction CMP.
     */
    Long getIdFonctionCMP();

    /**
     * Récupère l'identifiant de la fonction CCD pour les ressources.
     * @return lidentifiant de la fonction CCD.
     */
    Long getIdFonctionCCD();

    /**
     * Récupère l'identifiant de la fonction CC pour les ressources.
     * @return lidentifiant de la fonction CC.
     */
    Long getIdFonctionCC();

    /**
     * Récupère l'identifiant de la fonction Aimateur pour les ressources.
     * @return lidentifiant de la fonction Aimateur.
     */
    Long getIdFonctionAnimateur();

    /**
     * Accesseur rôle Square Administrateur.
     * @return le rôle.
     */
    String getRoleSquareAdmin();

    /**
     * Renvoi la valeur de idTypeRelationAssureSociale.
     * @return idTypeRelationAssureSociale
     */
    Long getIdTypeRelationAssureSociale();

    /**
     * Récupère l'identifiant de la nature "Correspondant" pour une personne physique.
     * @return l'identifiant.
     */
    Long getIdNaturePersonneCorrespondant();

    /**
     * Récupère l'identifiant du type de relation "Correspondant".
     * @return l'identifiant.
     */
    Long getIdTypeRelationCorrespondant();

    /**
     * Récupère l'identifiant du sous-objet "Collectif" pour une action.
     * @return l'identifiant.
     */
    Long getIdSousObjetActionCollectif();

    /**
     * Récupère l'identifiant de la nature "Prospect" pour une personne morale.
     * @return l'identifiant.
     */
    Long getIdNaturePersonneMoraleProspect();

    /**
     * Accesseur sur l'identifiant de la nature d'une personne morale de type adhérent.
     * @return l'identifiant.
     */
    Long getIdNaturePersonneMoraleAdherent();

    /**
     * Récupère l'identifiant du "Pôle fidélisation".
     * @return l'identifiant.
     */
    Long getIdAgencePoleFidelisation();

    /**
     * Récupère l'identifiant de "Agence internet".
     * @return l'identifiant.
     */
    Long getIdAgenceInternet();

    /**
     * Récupère l'identifiant du réseau individuel.
     * @return l'identifiant.
     */
    Long getIdReseauIndividuel();

    /**
     * Récupère l'identifiant de la nature d'une personne bénéficiaire adhérent.
     * @return l'identifiant.
     */
    Long getIdNaturePersonneBeneficiaireAdherent();
}
