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
package com.square.core.service.implementations;

import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.model.dto.ActionNotificationInfosDto;
import com.square.core.model.dto.ConstantesDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.NumeroRoDto;
import com.square.core.model.dto.PaysSimpleDto;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.RessourceHabilitationUtil;
import com.square.core.util.SquareMappingKeyUtil;

/**
 * Implémentation du service de mapping.
 * @author cblanchard - SCUB
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class SquareMappingServiceImplementation implements SquareMappingService {

    /** Identifiant du pays par défaut. */
    private Long idPaysParDefaut;

    /** Identifiant de la France. */
    private Long idPaysFrance;

    /** Identifiant de la France métropolitaine. */
    private Long idPaysFranceMetropolitaine;

    /** Identifiant de la nature de l'adresse principale. */
    private Long idNatureAdressePrincipale;

    /** Identifiant de la nature de l'adresse secondaire. */
    private Long idNatureAdresseSecondaire;

    /** Identifiant de la nature de l'email personnel. */
    private Long idNatureEmailPersonnel;

    /** Identifiant du type de relation conjoint. */
    private Long idTypeRelationConjoint;

    /** Identifiant du type de relation enfant. */
    private Long idTypeRelationEnfant;

    /** Identifiant du type de relation "Correspondant". */
    private Long idTypeRelationCorrespondant;

    /** Identifiant des ressources. TODO Constante temporaire */
    private Long idRessource;

    /** Identifiant du groupement famille. */
    private Long idGroupementFamille;

    /** Identifiant de groupement tiers. */
    private Long idGroupementTiers;

    /** Identifiant du groupement professionnel. */
    private Long idGroupementProfessionnel;

    /** Identifiant de la nature par défaut d'une PP. */
    private Long idNaturePersonneParDefaut;

    /** Identifiant de la nature bénéficiaire d'une PP. */
    private Long idNaturePersonneBeneficiaire;

    /** Identifiant de la nature "décédé" pour les PP. */
    private Long idNaturePersonneDecede;

    /** Identifiant de la profession par défaut d'une PP. */
    private Long idProfessionPersonneParDefaut;

    /** Identifiant du segment par défaut d'une PP. */
    private Long idSegmentPersonneParDefaut;

    /** Identifiant du segment individuel d'une PP. */
    private Long idSegmentIndividuel;

    /** Identifiant de la sittuation familale par défaut d'une PP. */
    private Long idSituationFamilialePersonneParDefaut;

    /** Identifiant du réseau de vente par défaut d'une PP. */
    private Long idReseauVentePersonneParDefaut;

    /** Identifiant de la CSP par défaut d'une PP. */
    private Long idCSPPersonneParDefaut;

    /** Identifiant du téléphone fixe. */
    private Long idNatureTelephoneFixe;

    /** Identifiant de la priorité par defaut des actions. */
    private Long idActionPrioriteParDefaut;

    /** Identifiant de la priorité "haute" pour une action. */
    private Long idActionPrioriteHaute;

    /** Identifiant de la priorité "urgente" pour une action. */
    private Long idActionPrioriteUrgente;

    /** List de notifications. */
    private List<ActionNotificationInfosDto> notifications;

    /** Identifiant du statut d'une campagne active. */
    private Long idStatutCampagneActive;

    /** Identifiant du mobile privé. */
    private Long idNatureMobilePrive;

    /** Identifiant du mobile de travail. */
    private Long idNatureMobileTravail;

    /** Identifiant de la nature du téléphone fixe du travail. */
    private Long idNatureTelephoneFixeTravail;

    /** Identifiant de la nature du fax privé. */
    private Long idNatureFaxPrive;

    /** Identifiant nature téléphone. */
    private Long idNatureTelephoneErrone;

    /** List des Identifiants du téléphone mobile. */
    private List<Long> listeIdsNaturesTelephoneMobile;

    /** Message source util. */
    private MessageSourceUtil messageSourceUtil;

    /** Identifiant du statut des actions après création (A faire). */
    private Long idStatutActionParDefaut;

    /** Identifiant du statut "à faire" des actions. */
    private Long idStatutActionAFaire;

    /** Identifiant du statut "terminé" des actions. */
    private Long idStatutActionTermine;

    /** Identifiant du statut "annulé" des actions. */
    private Long idStatutActionAnnule;

    /** Identifiant de la civilité monsieur. */
    private Long idCiviliteMonsieur;

    /** Identifiant de la civilité madame. */
    private Long idCiviliteMadame;

    /** Identifiant de la civilité mademoiselle. */
    private Long idCiviliteMademoiselle;

    /** Identifiant de la civilité non reneigné. */
    private Long idCiviliteNonRenseigne;

    /** Identifiant du résultat opportunite. */
    private Long idResultatOpportunite;

    /** Identifiant du résultat relance. */
    private Long idResultatRelance;

    /** Identifiant du statut terminer. */
    private Long idStatutTerminer;

    /** Identifiant du statut à faire. */
    private Long idStatutAFaire;

    /** Identifiant du statut annuler. */
    private Long idStatutAnnuler;

    /** Identifiant du statut d'opportunité non renseigné. */
    private Long idStatutOpportuniteNonRenseigne;

    /** Identifiant du statut d'opportunité refusé. */
    private Long idStatutOpportuniteRefuse;

    /** Identifiant du statut d'opportunité accepté. */
    private Long idStatutOpportuniteAccepte;

    /** Identifiant du statut d'opportunité corbeille. */
    private Long idStatutOpportuniteCorbeille;

    /** Identifiant du statut d'opportunité transformé. */
    private Long idStatutOpportuniteTransforme;

    /** Identifiant du statut d'opportunité en attente. */
    private Long idStatutOpportuniteEnAttente;

    /** Identifiant de l'objet d'action. */
    private Long idObjetActionCourrier;

    /** Identifiant objet action "changement composition familiale". */
    private Long idObjetActionChgtCompoFamiliale;

    /** Identifiant de l'objet d'action. */
    private Long idObjetActionEditique;

    /** Identifiant de l'objet d'action. */
    private Long idObjetActionTransformationAia;

    /** Identifiant de l'objet d'action. */
    private Long idObjetActionNouvelleAdhesion;

    /** Identifiant de l'objet d'action client. */
    private Long idObjetActionClient;

    /** Identifiant de l'objet d'action client. */
    private Long idObjetActionJustificatifIdentite;

    /** Identifiant de l'objet d'action client. */
    private Long idObjetActionControle;

    /** Id du sous-objet d'action. */
    private Long idSousObjetActionEditionBa;

    /** Id du sous-objet d'action. */
    private Long idSousObjetActionEnvoiBaParMail;

    /** Identifiant d'une sous-objet "Collectif" d'une action. */
    private Long idSousObjetActionCollectif;

    /** Identifiant du type d'action opportunité. */
    private Long idTypeActionOpportunite;

    private Long idTypeActionDocument;

    /** Identifiant du type d'action relance. */
    private Long idTypeActionRelance;

    /** Identifiant du type d'action cotisation. */
    private Long idTypeActionCotisation;

    /** Identifiant du type d'action e-services. */
    private Long idTypeActionEServices;

    /** Identifiant du type d'action prestations sinistres. */
    private Long idTypeActionPrestationsSinistres;

    /** Identifiant du type d'action radiation. */
    private Long idTypeActionRadiation;

    /** Identifiant du type d'action recours gracieux. */
    private Long idTypeActionRecoursGracieux;

    /** Identifiant du type d'action lab. */
    private Long idTypeActionLab;

    /** Identifiant de la nature d'action. */
    private Long idNatureActionInternet;

    /** Identifiant de la nature d'action Téléphone sortant. */
    private Long idNatureActionTelephoneSortant;

    /** Identifiant de la nature d'action Visite sortante. */
    private Long idNatureActionVisiteSortante;

    /** Identifiant de la nature d'action Courrier entrant. */
    private Long idNatureActionCourrierEntrant;

    /** Identifiant de la nature d'action Courrier entrant. */
    private Long idNatureActionCourrierSortant;

    /** Identifiant de la nature d'une action interne. */
    private Long idNatureActionInterne;

    /** Identifiant de la nature d'action Email Entrant. */
    private Long idNatureActionEmailEntrant;

    /** Identifiant de la nature d'action Email Sortant. */
    private Long idNatureActionEmailSortant;

    /** Identifiant de la nature d'action Achat Fichiers. */
    private Long idNatureActionAchatFichiers;

    /** Libellé du type d'une action opportunité. */
    private String libelleTypeActionOpportunite;

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

    /** Propriété pour la date de création de la personne. */
    private String proprietePersonneDateCreation;

    /** Identifiant de la nature d'une personne de type vivier. */
    private Long idNaturePersonneVivier;

    /** Identifiant de la nature d'une personne de type prospect. */
    private Long idNaturePersonneProspect;

    /** Identifiant de la nature d'une personne de type adhérent. */
    private Long idNaturePersonneAdherent;

    /** Identifiant de la nature d'une personne de type assuré social. */
    private Long idNaturePersonneAssureSocial;

    /** Identifiant de la nature "Correspondant' d'une personne. */
    private Long idNaturePersonneCorrespondant;

    /** Identifiant de la nature "Prospect" pour une personne morale. */
    private Long idNaturePersonneMoraleProspect;

    /** Identifiant de la nature "Adherent" pour une personne morale. */
    private Long idNaturePersonneMoraleAdherent;

    /** Role square bacth. */
    private String roleSquareBatch;

    /** Identifiant de la nature de personne de type bénéficiaire prospect. */
    private Long idNaturePersonneBeneficiaireProspect;

    /** Identifiant de la nature de personne de type bénéficiaire vivier. */
    private Long idNaturePersonneBeneficiaireVivier;

    /** Identifiant du réseau de vente courtage. */
    private Long idReseauVenteCourtage;

    /** Rôle Square pour les campagnes. */
    private String roleSquareCampagne;

    /** Rôle Square pour les animateurs. */
    private String roleSquareAnimateur;

    /** Identifiant du type de relation filleul. */
    private Long idTypeRelationFilleul;

    /** Identifiant du type de relation filleul lors d'une demande de parrainage en cours. */
    private Long idTypeRelationFilleulEnCoursCreation;

    /** Identifiant du type de relation filleul si garantie incompatible. */
    private Long idTypeRelationFilleulGarantieIncompatible;

    /** Identifiant du type de relation filleul pour un filleul deja adherent. */
    private Long idTypeRelationFilleulDejaAdherent;

    /** Constante qui permet d'ordonner les relations par actif/non actif. */
    private String orderByRelationActif;

    /** Constante qui permet d'ordonner les relations par leur type. */
    private String orderByRelationTypeOrdre;

    /** Constante qui permet d'ordonner les relation par leur date de début. */
    private String orderByRelationDateDebut;

    /** Constante qui permet d'ordonner les relation par leur date de fin. */
    private String orderByRelationDateFin;

    /** Identifiant du groupement de type en cours parrainage. */
    private Long idGroupementRelationTypeEnCoursParrainage;

    /** Identifiant du groupement de type echec parrainage garantie incompatible. */
    private Long idGroupementRelationTypeEchecParrainageGarantie;

    /** Identifiant du groupement de type echec parrainage deja adherent. */
    private Long idGroupementRelationTypeEchecParrainageAdherent;

    /** Libelle non renseigné. */
    private String libelleNonRenseigne;

    /** Libelle a pour enfant. */
    private String libelleAPourEnfant;

    /** Valeur de la télétransmission pour Oui. */
    private String infosTeletransmissionCaisseOui;

    /** Valeur de la télétransmission pour Non. */
    private String infosTeletransmissionCaisseNon;

    /** Id du type action commercial. */
    private Long idTypeActionCommercial;

    /** Id du type action commercial. */
    private Long idObjetActionRelance;

    /** Identifiant objet action "Demande devis ou PEC". */
    private Long idObjetActionDemandeDevisOuPEC;

    /** Id du type action commercial. */
    private Long idSousObjetActionRelanceParrainage;

    /** Id du sous-objet action relance. */
    private Long idSousObjetActionRelance;

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

    /** Délais à rajouter pour l'affichage d'une action. */
    private int decalageDateAffichageAction;

    /** Décalage à rajouter pour la date de l'action de relance d'une opportunité. */
    private int decalageDateActionRelance;

    /** Liste des identifiants de statuts de personne de la loi madelin. */
    private List<Long> listeIdsStatutsLoiMadelin;

    /** . */
    private Long idObjetModificationAdressePostale;

    /** Id du sous objet d'action "Sans changement de département". */
    private Long idSousObjetActionSansChangementDepartement;

    /** Id du sous objet d'action "Sans changement de département". */
    private Long idSousObjetActionAvecChangementDepartement;

    /** Id du sous objet d'action "Adhésion en ligne". */
    private Long idSousObjetActionAdhesionEnLigne;

    /** . */
    private Long idTypeActionModification;

    /** Identifiant de l'état actif d'une ressource. */
    private Long idEtatActifRessource;

    /** Identifiant de l'état inactif d'une ressource. */
    private Long idEtatInactifRessource;

    /** Identifiant de la fonction CMP pour les ressources. */
    private Long idFonctionCMP;

    /** Identifiant de la fonction CCD pour les ressources. */
    private Long idFonctionCCD;

    /** Identifiant de la fonction CC pour les ressources. */
    private Long idFonctionCC;

    /** Identifiant de la fonction Animateur pour les ressources. */
    private Long idFonctionAnimateur;

    /** Role square bacth. */
    private String roleSquareAdmin;

    /** Identifiant du type de relation assure social. */
    private Long idTypeRelationAssureSociale;

    /** Identifiant du pôle fidélisation. */
    private Long idAgencePoleFidelisation;

    /** Identifiant de l'agence internet. */
    private Long idAgenceInternet;

    /** Identifiant du réseau individuel. */
    private Long idReseauIndividuel;

    /** Identifiant de la nature d'une personne bénéficiaire adhérent. */
    private Long idNaturePersonneBeneficiaireAdherent;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Service de dimension. */
    private DimensionService dimensionService;

    /** Classe utilitaire pour faire le pont entre les habilitations et les ressources Square. */
    private RessourceHabilitationUtil ressourceHabilitationUtil;

    @Override
    public ConstantesDto getConstantes() {
        final ConstantesDto constantes = new ConstantesDto();
        constantes.setIdPaysFrance(this.getIdPaysFrance());
        constantes.setIdPaysFranceMetropolitaine(this.getIdPaysFranceMetropolitaine());
        constantes.setIdPaysParDefaut(this.getIdPaysParDefaut());

        final DimensionCriteresRechercheDto criterePaysDto = new DimensionCriteresRechercheDto(this.getIdPaysFrance());
        final List<PaysSimpleDto> listePaysDto = dimensionService.rechercherSimplePaysParCriteres(criterePaysDto);
        if (listePaysDto == null || listePaysDto.size() != 1) {
            throw new BusinessException("La récupération du pays par défaut a échouée");
        }
        constantes.setPaysFrance(listePaysDto.get(0));

        // Récupération de l'identifiant-libellé du pays par défaut
        final DimensionCriteresRechercheDto criterePaysParDefaut = new DimensionCriteresRechercheDto();
        criterePaysParDefaut.setId(constantes.getIdPaysParDefaut());
        final List<IdentifiantLibelleBooleanDto> listePays = dimensionService.rechercherPaysBooleanParCriteres(criterePaysParDefaut);
        constantes.setIdLibellePaysParDefaut(listePays.get(0));

        constantes.setIdTypeRelationConjoint(this.getIdTypeRelationConjoint());
        constantes.setIdTypeRelationEnfant(this.getIdTypeRelationEnfant());
        constantes.setIdGroupementFamiliale(this.getIdGroupementFamille());
        constantes.setIdGroupementProfessionnel(this.getIdGroupementProfessionnel());
        constantes.setIdNatureAdressePrincipale(this.getIdNatureAdressePrincipale());
        constantes.setIdNatureAdresseSecondaire(this.getIdNatureAdresseSecondaire());
        constantes.setIdActionPrioriteParDefaut(this.getIdActionPrioriteParDefaut());
        constantes.setIdStatutTerminer(this.getIdStatutTerminer());
        constantes.setIdStatutAFaire(this.getIdStatutAFaire());
        constantes.setIdCiviliteMonsieur(this.getIdCiviliteMonsieur());
        constantes.setIdStatutAnnuler(this.getIdStatutAnnuler());
        constantes.setIdResultatOpportunite(this.getIdResultatOpportunite());
        constantes.setIdResultatRelance(this.getIdResultatRelance());
        constantes.setIdNatureTelephoneFixe(this.getIdNatureTelephoneFixe());
        constantes.setIdNatureMobilePrive(this.getIdNatureMobilePrive());
        constantes.setIdNatureMobileTravail(this.getIdNatureMobileTravail());
        constantes.setIdStatutOpportuniteNonRenseigne(this.getIdStatutOpportuniteNonRenseigne());
        constantes.setIdStatutOpportuniteRefuse(this.getIdStatutOpportuniteRefuse());
        constantes.setIdStatutOpportuniteAccepte(this.getIdStatutOpportuniteAccepte());
        constantes.setIdStatutOpportuniteCorbeille(this.getIdStatutOpportuniteCorbeille());
        constantes.setIdStatutOpportuniteTransforme(this.getIdStatutOpportuniteTransforme());
        constantes.setIdStatutOpportuniteEnAttente(this.getIdStatutOpportuniteEnAttente());
        constantes.setIdObjetActionEditique(this.getIdObjetActionEditique());
        constantes.setIdObjetActionTransformationAia(this.getIdObjetActionTransformationAia());
        constantes.setIdSousObjetActionEditionBa(this.getIdSousObjetActionEditionBa());
        constantes.setIdSousObjetActionEnvoiBaParMail(this.getIdSousObjetActionEnvoiBaParMail());
        constantes.setIdTypeActionOpportunite(this.getIdTypeActionOpportunite());
        constantes.setIdTypeActionRelance(this.getIdTypeActionRelance());
        constantes.setIdNatureActionInternet(this.getIdNatureActionInternet());
        constantes.setIdNatureActionTelephoneSortant(this.getIdNatureActionTelephoneSortant());
        constantes.setIdNatureActionVisiteSortante(this.getIdNatureActionVisiteSortante());
        constantes.setIdNatureActionEmailSortant(this.getIdNatureActionEmailSortant());
        constantes.setIdNatureActionCourrierSortant(this.getIdNatureActionCourrierSortant());
        constantes.setIdNatureActionInterne(this.getIdNatureActionInterne());
        constantes.setIdNaturePersonneVivier(this.getIdNaturePersonneVivier());
        constantes.setIdNaturePersonneProspect(this.getIdNaturePersonneProspect());
        constantes.setIdNaturePersonneAdherent(this.getIdNaturePersonneAdherent());
        constantes.setIdNaturePersonneBeneficiaireProspect(this.getIdNaturePersonneBeneficiaireProspect());
        constantes.setIdNaturePersonneBeneficiaireVivier(this.getIdNaturePersonneBeneficiaireVivier());
        constantes.setIdNaturePersonneMoraleAdherent(this.getIdNaturePersonneMoraleAdherent());
        final IdentifiantLibelleDto idLibelleTypeOpportunite = new IdentifiantLibelleDto();
        idLibelleTypeOpportunite.setIdentifiant(this.getIdTypeActionOpportunite());
        idLibelleTypeOpportunite.setLibelle(this.getLibelleTypeActionOpportunite());
        constantes.setIdLibelleTypeOpportunite(idLibelleTypeOpportunite);
        constantes.setProprietePersonneAgence(this.getProprietePersonneAgence());
        constantes.setProprietePersonneCodePostal(this.getProprietePersonneCodePostal());
        constantes.setProprietePersonneCommercial(this.getProprietePersonneCommercial());
        constantes.setProprietePersonneCommune(this.getProprietePersonneCommune());
        constantes.setProprietePersonneDateNaissance(this.getProprietePersonneDateNaissance());
        constantes.setProprietePersonneNature(this.getProprietePersonneNature());
        constantes.setProprietePersonneNom(this.getProprietePersonneNom());
        constantes.setProprietePersonneNumeroClient(this.getProprietePersonneNumeroClient());
        constantes.setProprietePersonnePrenom(this.getProprietePersonnePrenom());
        constantes.setProprietePersonneSegment(this.getProprietePersonneSegment());
        constantes.setProprietePrestationRefDecompte(this.getProprietePrestationRefDecompte());
        constantes.setProprietePrestationDateSoins(this.getProprietePrestationDateSoins());
        constantes.setProprietePrestationActe(this.getProprietePrestationActe());
        constantes.setProprietePrestationDepensesEngagees(this.getProprietePrestationDepensesEngagees());
        constantes.setProprietePrestationRemboursementRO(this.getProprietePrestationRemboursementRO());
        constantes.setProprietePrestationRemboursementSmatis(this.getProprietePrestationRemboursementSmatis());
        constantes.setProprietePrestationResteACharge(this.getProprietePrestationResteACharge());
        constantes.setProprietePrestationNomDestinataire(this.getProprietePrestationNomDestinataire());
        constantes.setStatutPaiementPaye(this.getStatutPaiementPaye());
        constantes.setIdNaturePersonneDecede(this.getIdNaturePersonneDecede());
        constantes.setIdEtatActifRessource(this.getIdEtatActifRessource());
        constantes.setIdEtatInactifRessource(this.getIdEtatInactifRessource());
        constantes.setIdAgenceFrance(this.getIdentifiantAgenceFrance());
        constantes.setIdFonctionCC(this.getIdFonctionCC());

        // On récupère les rôles de l'utilisateur connecté
        boolean hasRoleCampagne = false;
        boolean hasRoleAnimateur = false;
        boolean hasRoleAdmin = false;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            final GrantedAuthority[] authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(this.getRoleSquareCampagne())) {
                    hasRoleCampagne = true;
                }
                if (authority.getAuthority().equals(this.getRoleSquareAnimateur())) {
                    hasRoleAnimateur = true;
                }
                if (authority.getAuthority().equals(this.getRoleSquareAdmin())) {
                    hasRoleAdmin = true;
                }
            }
        }
        constantes.setHasRoleCampagne(hasRoleCampagne);
        constantes.setHasRoleAnimateur(hasRoleAnimateur);
        constantes.setHasRoleAdmin(hasRoleAdmin);
        // Récupération du statut "A faire"
        DimensionCriteresRechercheDto critereDto = new DimensionCriteresRechercheDto();
        critereDto.setId(this.getIdStatutAFaire());
        final List<IdentifiantLibelleDto> listeStatuts = dimensionService.rechercherStatutActionsParCriteres(critereDto);
        if (listeStatuts == null || listeStatuts.size() != 1) {
            throw new BusinessException("La récupération du statut \"A faire\" des actions est terminé");
        }
        final IdentifiantLibelleDto idLibelleStatutAFaire = mapperDozerBean.map(listeStatuts.get(0), IdentifiantLibelleDto.class);
        constantes.setIdLibelleStatutAFaire(idLibelleStatutAFaire);

        // Récupération de l'état d'une ressource "Actif"
        critereDto = new DimensionCriteresRechercheDto();
        critereDto.setId(this.getIdEtatActifRessource());
        final List<IdentifiantLibelleDto> listeEtatsRessource = dimensionService.rechercherRessourceEtatParCriteres(critereDto);
        if (listeEtatsRessource == null || listeEtatsRessource.size() != 1) {
            throw new BusinessException("Une erreur est survenue lors de la récupération de l'état \"Actif\" des ressources");
        }
        final IdentifiantLibelleDto idLibelleEtatActifRessource = mapperDozerBean.map(listeEtatsRessource.get(0), IdentifiantLibelleDto.class);
        constantes.setIdLibelleEtatActifRessource(idLibelleEtatActifRessource);

        // Récupération des constantes de tri pour les actions
        constantes.setOrderByActionId(this.getOrderByActionId());
        constantes.setOrderByActionDateAction(this.getOrderByActionDateAction());
        constantes.setOrderByActionDateCreation(this.getOrderByActionDateCreation());
        constantes.setOrderByActionDateTerminee(this.getOrderByActionDateTerminee());
        constantes.setOrderByActionAttributionAgence(this.getOrderByActionAttributionAgence());
        constantes.setOrderByActionAttributionRessource(this.getOrderByActionAttributionRessource());
        constantes.setOrderByActionObjet(this.getOrderByActionObjet());
        constantes.setOrderByActionPriorite(this.getOrderByActionPriorite());
        constantes.setOrderByActionSsObjet(this.getOrderByActionSsObjet());
        constantes.setOrderByActionStatut(this.getOrderByActionStatut());
        constantes.setOrderByActionType(this.getOrderByActionType());

        // Détermine si l'utilisateur connecté a le rôle de gestion des opportunités
        constantes.setHasRoleGestionnaireOpportunites(dimensionService.hasRessourceConnecteeRole(this.getIdentifiantRoleGestionnaireOpportunites()));

        return constantes;
    }

    /**
     * Convertir le numero de régime obligatoire en numero de sécurité social et clé.
     * @param nro le numero de régime obligatoire
     * @return le dto du numéro de regime.
     * @author mlamine - SCUB
     */
    public NumeroRoDto convertirNroVersNss(String nro) {
        if (nro == null || nro.equals("")) {
            return null;
        }
        final int tailleNss = 13;
        final int tailleNro = 15;
        final NumeroRoDto nroDto = new NumeroRoDto();
        try {
            nroDto.setNumeroSS(nro.substring(0, tailleNss));
            nroDto.setCleSS(nro.substring(tailleNss, tailleNro));
        } catch (IndexOutOfBoundsException e) {
            throw new TechnicalException(messageSourceUtil.get(SquareMappingKeyUtil.MESSAGE_ERREUR_DECOMPOSER_NUMERO_SECURITE_SOCIALE), e.getCause());
        }
        return nroDto;
    }

    /**
     * Conveti le numero de sécurité social et la clé en numero de régime obligatoire.
     * @param nss le numero de sécurité social
     * @param cless la clé.
     * @return le numéro de regime.
     * @author mlamine - SCUB
     */
    public String convertirNssVersNro(String nss, String cless) {
        if (nss == null) {
            return null;
        }
        final String numRo = nss.concat(cless);
        return numRo;
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
     * Renvoi la valeur de idNatureEmailPersonnel.
     * @return idNatureEmailPersonnel
     */
    public Long getIdNatureEmailPersonnel() {
        return idNatureEmailPersonnel;
    }

    /**
     * Modifie idNatureEmailPersonnel.
     * @param idNatureEmailPersonnel la nouvelle valeur de idNatureEmailPersonnel
     */
    public void setIdNatureEmailPersonnel(Long idNatureEmailPersonnel) {
        this.idNatureEmailPersonnel = idNatureEmailPersonnel;
    }

    /**
     * Renvoi la valeur de idTypeRelationConjoint.
     * @return idTypeRelationConjoint
     */
    public Long getIdTypeRelationConjoint() {
        return idTypeRelationConjoint;
    }

    /**
     * Modifie idTypeRelationConjoint.
     * @param idTypeRelationConjoint la nouvelle valeur de idTypeRelationConjoint
     */
    public void setIdTypeRelationConjoint(Long idTypeRelationConjoint) {
        this.idTypeRelationConjoint = idTypeRelationConjoint;
    }

    /**
     * Renvoi la valeur de idTypeRelationEnfant.
     * @return idTypeRelationEnfant
     */
    public Long getIdTypeRelationEnfant() {
        return idTypeRelationEnfant;
    }

    /**
     * Modifie idTypeRelationEnfant.
     * @param idTypeRelationEnfant la nouvelle valeur de idTypeRelationEnfant
     */
    public void setIdTypeRelationEnfant(Long idTypeRelationEnfant) {
        this.idTypeRelationEnfant = idTypeRelationEnfant;
    }

    /**
     * Renvoi la valeur de idRessource.
     * @return idRessource
     */
    public Long getIdRessource() {
        return idRessource;
    }

    /**
     * Modifie idRessource.
     * @param idRessource la nouvelle valeur de idRessource
     */
    public void setIdRessource(Long idRessource) {
        this.idRessource = idRessource;
    }

    /**
     * Renvoi la valeur de idNatureAdresseSecondaire.
     * @return idNatureAdresseSecondaire
     */
    public Long getIdNatureAdresseSecondaire() {
        return idNatureAdresseSecondaire;
    }

    /**
     * Modifie idNatureAdresseSecondaire.
     * @param idNatureAdresseSecondaire la nouvelle valeur de idNatureAdresseSecondaire
     */
    public void setIdNatureAdresseSecondaire(Long idNatureAdresseSecondaire) {
        this.idNatureAdresseSecondaire = idNatureAdresseSecondaire;
    }

    /**
     * Renvoi la valeur de idPaysParDefaut.
     * @return idPaysParDefaut
     */
    public Long getIdPaysParDefaut() {
        return idPaysParDefaut;
    }

    /**
     * Modifie idPaysParDefaut.
     * @param idPaysParDefaut la nouvelle valeur de idPaysParDefaut
     */
    public void setIdPaysParDefaut(Long idPaysParDefaut) {
        this.idPaysParDefaut = idPaysParDefaut;
    }

    /**
     * Renvoi la valeur de idPaysFrance.
     * @return idPaysFrance
     */
    public Long getIdPaysFrance() {
        return idPaysFrance;
    }

    /**
     * Modifie idPaysFrance.
     * @param idPaysFrance la nouvelle valeur de idPaysFrance
     */
    public void setIdPaysFrance(Long idPaysFrance) {
        this.idPaysFrance = idPaysFrance;
    }

    /**
     * Retourne la valeur de idGroupementFamille.
     * @return the idGroupementFamille
     */
    public Long getIdGroupementFamille() {
        return idGroupementFamille;
    }

    /**
     * Modifie la valeur de idGroupementFamille.
     * @param idGroupementFamille the idGroupementFamille to set
     */
    public void setIdGroupementFamille(Long idGroupementFamille) {
        this.idGroupementFamille = idGroupementFamille;
    }

    /**
     * Retourne la valeur de idGroupementTiers.
     * @return the idGroupementTiers
     */
    public Long getIdGroupementTiers() {
        return idGroupementTiers;
    }

    /**
     * Modifie la valeur de idGroupementTiers.
     * @param idGroupementTiers the idGroupementTiers to set
     */
    public void setIdGroupementTiers(Long idGroupementTiers) {
        this.idGroupementTiers = idGroupementTiers;
    }

    @Override
    public ActionNotificationInfosDto getActionNotificationParId(Long id) {
        for (ActionNotificationInfosDto notification : notifications) {
            if (notification.getId().equals(id)) {
                return notification;
            }
        }
        return null;
    }

    @Override
    public Long getIdNotification(Calendar dateAction, Calendar dateNotification) {
        if (dateAction == null) {
            throw new BusinessException(messageSourceUtil.get(SquareMappingKeyUtil.MESSAGE_ERREUR_DATE_NULL));
        }
        if (dateNotification == null) {
            throw new BusinessException(messageSourceUtil.get(SquareMappingKeyUtil.MESSAGE_ERREUR_DATE_NULL));
        }
        if (dateAction.compareTo(dateNotification) < 0) {
            throw new BusinessException(messageSourceUtil.get(SquareMappingKeyUtil.MESSAGE_ERREUR_DATE_ACTION_INF_DATE_NOTIFICATION));
        }

        final Long dateTime = dateAction.getTimeInMillis() - dateNotification.getTimeInMillis();
        final Calendar soustraction = Calendar.getInstance();
        soustraction.clear();
        soustraction.setTimeInMillis(dateTime);
        for (ActionNotificationInfosDto notification : notifications) {
            // Si on a trouvé une correspondance on la retourne.
            if (notification.getNotification().compareTo(soustraction) == 0) {
                return notification.getId();
            }
        }
        return null;
    }

    @Override
    public List<ActionNotificationInfosDto> getListeActionNotifications() {
        return notifications;
    }

    @Override
    public Long getIdNaturePersonneParDefaut() {
        return idNaturePersonneParDefaut;
    }

    /**
     * Modifie la nature de la nature de personne par defaut.
     * @param idNaturePersonneParDefaut the idNaturePersonneParDefaut to set
     */
    public void setIdNaturePersonneParDefaut(Long idNaturePersonneParDefaut) {
        this.idNaturePersonneParDefaut = idNaturePersonneParDefaut;
    }

    /**
     * Renvoie la nature bénéficiaire d'une personne.
     * @return l'id de la nature
     */
    @Override
    public Long getIdNaturePersonneBeneficiaire() {
        return idNaturePersonneBeneficiaire;
    }

    /**
     * Setter de idNaturePersonneBeneficiaire.
     * @param idNaturePersonneBeneficiaire dans idNaturePersonneBeneficiaire
     */
    public void setIdNaturePersonneBeneficiaire(Long idNaturePersonneBeneficiaire) {
        this.idNaturePersonneBeneficiaire = idNaturePersonneBeneficiaire;
    }

    @Override
    public Long getIdCSPPersonneParDefaut() {
        return idCSPPersonneParDefaut;
    }

    @Override
    public Long getIdProfessionPersonneParDefaut() {
        return idProfessionPersonneParDefaut;
    }

    @Override
    public Long getIdReseauVentePersonneParDefaut() {
        return idReseauVentePersonneParDefaut;
    }

    @Override
    public Long getIdSegmentPersonneParDefaut() {
        return idSegmentPersonneParDefaut;
    }

    @Override
    public Long getIdSituationFamilialePersonneParDefaut() {
        return idSituationFamilialePersonneParDefaut;
    }

    /**
     * Renvoi la valeur de idStatutActionParDefaut.
     * @return idStatutActionParDefaut
     */
    @Override
    public Long getIdStatutActionParDefaut() {
        return idStatutActionParDefaut;
    }

    /**
     * Modifie la profession par defaut.
     * @param idProfessionPersonneParDefaut the idProfessionPersonneParDefaut to set
     */
    public void setIdProfessionPersonneParDefaut(Long idProfessionPersonneParDefaut) {
        this.idProfessionPersonneParDefaut = idProfessionPersonneParDefaut;
    }

    /**
     * Modifie le segment par defaut.
     * @param idSegmentPersonneParDefaut the idSegmentPersonneParDefaut to set
     */
    public void setIdSegmentPersonneParDefaut(Long idSegmentPersonneParDefaut) {
        this.idSegmentPersonneParDefaut = idSegmentPersonneParDefaut;
    }

    /**
     * Modifie la situation familiale par defaut.
     * @param idSituationFamilialePersonneParDefaut the idSituationFamilialePersonneParDefaut to set
     */
    public void setIdSituationFamilialePersonneParDefaut(Long idSituationFamilialePersonneParDefaut) {
        this.idSituationFamilialePersonneParDefaut = idSituationFamilialePersonneParDefaut;
    }

    /**
     * Modifie le réseau de vente par defaut.
     * @param idReseauVentePersonneParDefaut the idReseauVentePersonneParDefaut to set
     */
    public void setIdReseauVentePersonneParDefaut(Long idReseauVentePersonneParDefaut) {
        this.idReseauVentePersonneParDefaut = idReseauVentePersonneParDefaut;
    }

    /**
     * Modifie le csp par defaut.
     * @param idCSPPersonneParDefaut the idCSPPersonneParDefaut to set
     */
    public void setIdCSPPersonneParDefaut(Long idCSPPersonneParDefaut) {
        this.idCSPPersonneParDefaut = idCSPPersonneParDefaut;
    }

    /**
     * Retourne la valeur de idNatureTelephoneFixe.
     * @return the idNatureTelephoneFixe
     */
    public Long getIdNatureTelephoneFixe() {
        return idNatureTelephoneFixe;
    }

    /**
     * Modifie la valeur de idNatureTelephoneFixe.
     * @param idNatureTelephoneFixe the idNatureTelephoneFixe to set
     */
    public void setIdNatureTelephoneFixe(Long idNatureTelephoneFixe) {
        this.idNatureTelephoneFixe = idNatureTelephoneFixe;
    }

    /**
     * Fixer les notifications.
     * @param notifications the notifications to set
     */
    public void setNotifications(List<ActionNotificationInfosDto> notifications) {
        this.notifications = notifications;
    }

    /**
     * Retourne la valeur de idNatureMobilePrive.
     * @return the idNatureMobilePrive
     */
    public Long getIdNatureMobilePrive() {
        return idNatureMobilePrive;
    }

    /**
     * Modifie la valeur de idNatureMobilePrive.
     * @param idNatureMobilePrive the idNatureMobilePrive to set
     */
    public void setIdNatureMobilePrive(Long idNatureMobilePrive) {
        this.idNatureMobilePrive = idNatureMobilePrive;
    }

    /**
     * Retourne la valeur de idNatureMobileTravail.
     * @return the idNatureMobileTravail
     */
    public Long getIdNatureMobileTravail() {
        return idNatureMobileTravail;
    }

    /**
     * Modifie la valeur de idNatureMobileTravail.
     * @param idNatureMobileTravail the idNatureMobileTravail to set
     */
    public void setIdNatureMobileTravail(Long idNatureMobileTravail) {
        this.idNatureMobileTravail = idNatureMobileTravail;
    }

    /**
     * Renvoi la valeur de idStatutCampagneActive.
     * @return idStatutCampagneActive
     */
    public Long getIdStatutCampagneActive() {
        return idStatutCampagneActive;
    }

    /**
     * Modifie idStatutCampagneActive.
     * @param idStatutCampagneActive la nouvelle valeur de idStatutCampagneActive
     */
    public void setIdStatutCampagneActive(Long idStatutCampagneActive) {
        this.idStatutCampagneActive = idStatutCampagneActive;
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
     * Récupération de la liste des natures de télphone mobile.
     * @return the listeIdsNaturesTelephoneMobile
     */
    public List<Long> getListeIdsNaturesTelephoneMobile() {
        return listeIdsNaturesTelephoneMobile;
    }

    /**
     * Définition de la liste des natures de téléphone mobile.
     * @param listeIdsNaturesTelephoneMobile the listeIdsNaturesTelephoneMobile to set
     */
    public void setListeIdsNaturesTelephoneMobile(List<Long> listeIdsNaturesTelephoneMobile) {
        this.listeIdsNaturesTelephoneMobile = listeIdsNaturesTelephoneMobile;
    }

    /**
     * Modifie messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Modifie idStatutActionParDefaut.
     * @param idStatutActionParDefaut la nouvelle valeur de idStatutActionParDefaut
     */
    public void setIdStatutActionParDefaut(Long idStatutActionParDefaut) {
        this.idStatutActionParDefaut = idStatutActionParDefaut;
    }

    /**
     * setter civilité monsieur.
     * @param idCiviliteMonsieur dans idCiviliteMonsieur
     */
    public void setIdCiviliteMonsieur(Long idCiviliteMonsieur) {
        this.idCiviliteMonsieur = idCiviliteMonsieur;
    }

    /**
     * Renvoi l'identifiant du résultat opportunité.
     * @return l'identifiant du résultat opportunité
     */
    @Override
    public Long getIdResultatOpportunite() {
        return idResultatOpportunite;
    }

    /**
     * Renvoi l'identifiant du résultat relance.
     * @return l'identifiant du résultat relance
     */
    @Override
    public Long getIdResultatRelance() {
        return idResultatRelance;
    }

    /**
     * Renvoi l'identifiant du statut terminer.
     * @return l'identifiant du statut terminer
     */
    @Override
    public Long getIdStatutTerminer() {
        return idStatutTerminer;
    }

    /**
     * Renvoi la valeur de civilité monsieur.
     * @return idCiviliteMonsieur
     */
    @Override
    public Long getIdCiviliteMonsieur() {
        return idCiviliteMonsieur;
    }

    /**
     * Modifie idResultatOpportunite.
     * @param idResultatOpportunite la nouvelle valeur de idResultatOpportunite
     */
    public void setIdResultatOpportunite(Long idResultatOpportunite) {
        this.idResultatOpportunite = idResultatOpportunite;
    }

    /**
     * Modifie idResultatRelance.
     * @param idResultatRelance la nouvelle valeur de idResultatRelance
     */
    public void setIdResultatRelance(Long idResultatRelance) {
        this.idResultatRelance = idResultatRelance;
    }

    /**
     * Modifie idStatutTerminer.
     * @param idStatutTerminer la nouvelle valeur de idStatutTerminer
     */
    public void setIdStatutTerminer(Long idStatutTerminer) {
        this.idStatutTerminer = idStatutTerminer;
    }

    /**
     * Renvoi l'identifiant du statut annuler.
     * @return l'identifiant du statut annuler
     */
    @Override
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
     * Récupère la valeur de idCiviliteMadame.
     * @return la valeur de idCiviliteMadame
     */
    public Long getIdCiviliteMadame() {
        return idCiviliteMadame;
    }

    /**
     * Définit la valeur de idCiviliteMadame.
     * @param idCiviliteMadame la nouvelle valeur de idCiviliteMadame
     */
    public void setIdCiviliteMadame(Long idCiviliteMadame) {
        this.idCiviliteMadame = idCiviliteMadame;
    }

    /**
     * Récupère la valeur de idCiviliteMademoiselle.
     * @return la valeur de idCiviliteMademoiselle
     */
    public Long getIdCiviliteMademoiselle() {
        return idCiviliteMademoiselle;
    }

    /**
     * Définit la valeur de idCiviliteMademoiselle.
     * @param idCiviliteMademoiselle la nouvelle valeur de idCiviliteMademoiselle
     */
    public void setIdCiviliteMademoiselle(Long idCiviliteMademoiselle) {
        this.idCiviliteMademoiselle = idCiviliteMademoiselle;
    }

    /**
     * Récupère la valeur de idCiviliteNonRenseigne.
     * @return the idCiviliteNonRenseigne
     */
    public Long getIdCiviliteNonRenseigne() {
        return idCiviliteNonRenseigne;
    }

    /**
     * Définit la valeur de idCiviliteNonRensigne.
     * @param idCiviliteNonRenseigne the idCiviliteNonRenseigne to set
     */
    public void setIdCiviliteNonRenseigne(Long idCiviliteNonRenseigne) {
        this.idCiviliteNonRenseigne = idCiviliteNonRenseigne;
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
     * {@inheritDoc}
     */
    @Override
    public Long getIdTypeActionCotisation() {
        return idTypeActionCotisation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdTypeActionEServices() {
        return idTypeActionEServices;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdTypeActionPrestationsSinistres() {
        return idTypeActionPrestationsSinistres;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdTypeActionRadiation() {
        return idTypeActionRadiation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdTypeActionRecoursGracieux() {
        return idTypeActionRecoursGracieux;
    }

    /**
     * Setter function.
     * @param idTypeActionCotisation the idTypeActionCotisation to set
     */
    public void setIdTypeActionCotisation(Long idTypeActionCotisation) {
        this.idTypeActionCotisation = idTypeActionCotisation;
    }

    /**
     * Setter function.
     * @param idTypeActionEServices the idTypeActionEServices to set
     */
    public void setIdTypeActionEServices(Long idTypeActionEServices) {
        this.idTypeActionEServices = idTypeActionEServices;
    }

    /**
     * Setter function.
     * @param idTypeActionPrestationsSinistres the idTypeActionPrestationsSinistres to set
     */
    public void setIdTypeActionPrestationsSinistres(Long idTypeActionPrestationsSinistres) {
        this.idTypeActionPrestationsSinistres = idTypeActionPrestationsSinistres;
    }

    /**
     * Setter function.
     * @param idTypeActionRadiation the idTypeActionRadiation to set
     */
    public void setIdTypeActionRadiation(Long idTypeActionRadiation) {
        this.idTypeActionRadiation = idTypeActionRadiation;
    }

    /**
     * Setter function.
     * @param idTypeActionRecoursGracieux the idTypeActionRecoursGracieux to set
     */
    public void setIdTypeActionRecoursGracieux(Long idTypeActionRecoursGracieux) {
        this.idTypeActionRecoursGracieux = idTypeActionRecoursGracieux;
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
     * Getter function.
     * @return the idTypeActionRelance
     */
    public Long getIdTypeActionRelance() {
        return idTypeActionRelance;
    }

    /**
     * Setter function.
     * @param idTypeActionRelance the idTypeActionRelance to set
     */
    public void setIdTypeActionRelance(Long idTypeActionRelance) {
        this.idTypeActionRelance = idTypeActionRelance;
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
     * Getter function.
     * @return the idStatutOpportuniteTransforme
     */
    public Long getIdStatutOpportuniteTransforme() {
        return idStatutOpportuniteTransforme;
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
     * Setter function.
     * @param idStatutOpportuniteTransforme the idStatutOpportuniteTransforme to set
     */
    public void setIdStatutOpportuniteTransforme(Long idStatutOpportuniteTransforme) {
        this.idStatutOpportuniteTransforme = idStatutOpportuniteTransforme;
    }

    /**
     * Récupère la valeur de libelleTypeActionOpportunite.
     * @return la valeur de libelleTypeActionOpportunite
     */
    public String getLibelleTypeActionOpportunite() {
        return libelleTypeActionOpportunite;
    }

    /**
     * Définit la valeur de libelleTypeActionOpportunite.
     * @param libelleTypeActionOpportunite la nouvelle valeur de libelleTypeActionOpportunite
     */
    public void setLibelleTypeActionOpportunite(String libelleTypeActionOpportunite) {
        this.libelleTypeActionOpportunite = libelleTypeActionOpportunite;
    }

    @Override
    public String getProprietePersonneAgence() {
        return proprietePersonneAgence;
    }

    @Override
    public String getProprietePersonneCodePostal() {
        return proprietePersonneCodePostal;
    }

    @Override
    public String getProprietePersonneCommercial() {
        return proprietePersonneCommercial;
    }

    @Override
    public String getProprietePersonneCommune() {
        return proprietePersonneCommune;
    }

    @Override
    public String getProprietePersonneDateNaissance() {
        return proprietePersonneDateNaissance;
    }

    @Override
    public String getProprietePersonneNature() {
        return proprietePersonneNature;
    }

    @Override
    public String getProprietePersonneNom() {
        return proprietePersonneNom;
    }

    @Override
    public String getProprietePersonneNumeroClient() {
        return proprietePersonneNumeroClient;
    }

    @Override
    public String getProprietePersonnePrenom() {
        return proprietePersonnePrenom;
    }

    @Override
    public String getProprietePersonneSegment() {
        return proprietePersonneSegment;
    }

    /**
     * Définit la valeur de proprietePersonneAgence.
     * @param proprietePersonneAgence la nouvelle valeur de proprietePersonneAgence
     */
    public void setProprietePersonneAgence(String proprietePersonneAgence) {
        this.proprietePersonneAgence = proprietePersonneAgence;
    }

    /**
     * Définit la valeur de proprietePersonneCodePostal.
     * @param proprietePersonneCodePostal la nouvelle valeur de proprietePersonneCodePostal
     */
    public void setProprietePersonneCodePostal(String proprietePersonneCodePostal) {
        this.proprietePersonneCodePostal = proprietePersonneCodePostal;
    }

    /**
     * Définit la valeur de proprietePersonneCommercial.
     * @param proprietePersonneCommercial la nouvelle valeur de proprietePersonneCommercial
     */
    public void setProprietePersonneCommercial(String proprietePersonneCommercial) {
        this.proprietePersonneCommercial = proprietePersonneCommercial;
    }

    /**
     * Définit la valeur de proprietePersonneCommune.
     * @param proprietePersonneCommune la nouvelle valeur de proprietePersonneCommune
     */
    public void setProprietePersonneCommune(String proprietePersonneCommune) {
        this.proprietePersonneCommune = proprietePersonneCommune;
    }

    /**
     * Définit la valeur de proprietePersonneDateNaissance.
     * @param proprietePersonneDateNaissance la nouvelle valeur de proprietePersonneDateNaissance
     */
    public void setProprietePersonneDateNaissance(String proprietePersonneDateNaissance) {
        this.proprietePersonneDateNaissance = proprietePersonneDateNaissance;
    }

    /**
     * Définit la valeur de proprietePersonneNature.
     * @param proprietePersonneNature la nouvelle valeur de proprietePersonneNature
     */
    public void setProprietePersonneNature(String proprietePersonneNature) {
        this.proprietePersonneNature = proprietePersonneNature;
    }

    /**
     * Définit la valeur de proprietePersonneNom.
     * @param proprietePersonneNom la nouvelle valeur de proprietePersonneNom
     */
    public void setProprietePersonneNom(String proprietePersonneNom) {
        this.proprietePersonneNom = proprietePersonneNom;
    }

    /**
     * Définit la valeur de proprietePersonneNumeroClient.
     * @param proprietePersonneNumeroClient la nouvelle valeur de proprietePersonneNumeroClient
     */
    public void setProprietePersonneNumeroClient(String proprietePersonneNumeroClient) {
        this.proprietePersonneNumeroClient = proprietePersonneNumeroClient;
    }

    /**
     * Définit la valeur de proprietePersonnePrenom.
     * @param proprietePersonnePrenom la nouvelle valeur de proprietePersonnePrenom
     */
    public void setProprietePersonnePrenom(String proprietePersonnePrenom) {
        this.proprietePersonnePrenom = proprietePersonnePrenom;
    }

    /**
     * Définit la valeur de proprietePersonneSegment.
     * @param proprietePersonneSegment la nouvelle valeur de proprietePersonneSegment
     */
    public void setProprietePersonneSegment(String proprietePersonneSegment) {
        this.proprietePersonneSegment = proprietePersonneSegment;
    }

    @Override
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

    @Override
    public Long getIdNaturePersonneAdherent() {
        return idNaturePersonneAdherent;
    }

    @Override
    public Long getIdNaturePersonneProspect() {
        return idNaturePersonneProspect;
    }

    @Override
    public Long getIdNaturePersonneVivier() {
        return idNaturePersonneVivier;
    }

    /**
     * Définit la valeur de idNaturePersonneVivier.
     * @param idNaturePersonneVivier la nouvelle valeur de idNaturePersonneVivier
     */
    public void setIdNaturePersonneVivier(Long idNaturePersonneVivier) {
        this.idNaturePersonneVivier = idNaturePersonneVivier;
    }

    /**
     * Définit la valeur de idNaturePersonneProspect.
     * @param idNaturePersonneProspect la nouvelle valeur de idNaturePersonneProspect
     */
    public void setIdNaturePersonneProspect(Long idNaturePersonneProspect) {
        this.idNaturePersonneProspect = idNaturePersonneProspect;
    }

    /**
     * Définit la valeur de idNaturePersonneAdherent.
     * @param idNaturePersonneAdherent la nouvelle valeur de idNaturePersonneAdherent
     */
    public void setIdNaturePersonneAdherent(Long idNaturePersonneAdherent) {
        this.idNaturePersonneAdherent = idNaturePersonneAdherent;
    }

    @Override
    public String getRoleSquareBatch() {
        return this.roleSquareBatch;
    }

    /**
     * Définit la valeur de roleSquareBatch.
     * @param roleSquareBatch la nouvelle valeur de roleSquareBatch
     */
    public void setRoleSquareBatch(String roleSquareBatch) {
        this.roleSquareBatch = roleSquareBatch;
    }

    @Override
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
     * Récupère la valeur de idReseauVenteCourtage.
     * @return la valeur de idReseauVenteCourtage
     */
    public Long getIdReseauVenteCourtage() {
        return idReseauVenteCourtage;
    }

    /**
     * Définit la valeur de idReseauVenteCourtage.
     * @param idReseauVenteCourtage la nouvelle valeur de idReseauVenteCourtage
     */
    public void setIdReseauVenteCourtage(Long idReseauVenteCourtage) {
        this.idReseauVenteCourtage = idReseauVenteCourtage;
    }

    /**
     * Getter function.
     * @return the roleSquareCampagne
     */
    public String getRoleSquareCampagne() {
        return roleSquareCampagne;
    }

    /**
     * Setter function.
     * @param roleSquareCampagne the roleSquareCampagne to set
     */
    public void setRoleSquareCampagne(String roleSquareCampagne) {
        this.roleSquareCampagne = roleSquareCampagne;
    }

    /**
     * Get the idSegmentIndividuel value.
     * @return the idSegmentIndividuel
     */
    public Long getIdSegmentIndividuel() {
        return idSegmentIndividuel;
    }

    /**
     * Set the idSegmentIndividuel value.
     * @param idSegmentIndividuel the idSegmentIndividuel to set
     */
    public void setIdSegmentIndividuel(Long idSegmentIndividuel) {
        this.idSegmentIndividuel = idSegmentIndividuel;
    }

    /**
     * Get the idTypeRelationFilleul value.
     * @return the idTypeRelationFilleul
     */
    public Long getIdTypeRelationFilleul() {
        return idTypeRelationFilleul;
    }

    /**
     * Set the idTypeRelationFilleul value.
     * @param idTypeRelationFilleul the idTypeRelationFilleul to set
     */
    public void setIdTypeRelationFilleul(Long idTypeRelationFilleul) {
        this.idTypeRelationFilleul = idTypeRelationFilleul;
    }

    /**
     * Get the idTypeRelationFilleulEnCoursCreation value.
     * @return the idTypeRelationFilleulEnCoursCreation
     */
    public Long getIdTypeRelationFilleulEnCoursCreation() {
        return idTypeRelationFilleulEnCoursCreation;
    }

    /**
     * Set the idTypeRelationFilleulEnCoursCreation value.
     * @param idTypeRelationFilleulEnCoursCreation the idTypeRelationFilleulEnCoursCreation to set
     */
    public void setIdTypeRelationFilleulEnCoursCreation(Long idTypeRelationFilleulEnCoursCreation) {
        this.idTypeRelationFilleulEnCoursCreation = idTypeRelationFilleulEnCoursCreation;
    }

    /**
     * Get the idTypeRelationFilleulGarantieIncompatible value.
     * @return the idTypeRelationFilleulGarantieIncompatible
     */
    public Long getIdTypeRelationFilleulGarantieIncompatible() {
        return idTypeRelationFilleulGarantieIncompatible;
    }

    /**
     * Set the idTypeRelationFilleulGarantieIncompatible value.
     * @param idTypeRelationFilleulGarantieIncompatible the idTypeRelationFilleulGarantieIncompatible to set
     */
    public void setIdTypeRelationFilleulGarantieIncompatible(Long idTypeRelationFilleulGarantieIncompatible) {
        this.idTypeRelationFilleulGarantieIncompatible = idTypeRelationFilleulGarantieIncompatible;
    }

    /**
     * Get the idTypeRelationFilleulDejaAdherent value.
     * @return the idTypeRelationFilleulDejaAdherent
     */
    public Long getIdTypeRelationFilleulDejaAdherent() {
        return idTypeRelationFilleulDejaAdherent;
    }

    /**
     * Set the idTypeRelationFilleulDejaAdherent value.
     * @param idTypeRelationFilleulDejaAdherent the idTypeRelationFilleulDejaAdherent to set
     */
    public void setIdTypeRelationFilleulDejaAdherent(Long idTypeRelationFilleulDejaAdherent) {
        this.idTypeRelationFilleulDejaAdherent = idTypeRelationFilleulDejaAdherent;
    }

    /**
     * Get the orderByRelationDateDebut value.
     * @return the orderByRelationDateDebut
     */
    public String getOrderByRelationDateDebut() {
        return orderByRelationDateDebut;
    }

    /**
     * Set the orderByRelationDateDebut value.
     * @param orderByRelationDateDebut the orderByRelationDateDebut to set
     */
    public void setOrderByRelationDateDebut(String orderByRelationDateDebut) {
        this.orderByRelationDateDebut = orderByRelationDateDebut;
    }

    /**
     * Get the idStatutActionTermine value.
     * @return the idStatutActionTermine
     */
    public Long getIdStatutActionTermine() {
        return idStatutActionTermine;
    }

    /**
     * Set the idStatutActionTermine value.
     * @param idStatutActionTermine the idStatutActionTermine to set
     */
    public void setIdStatutActionTermine(Long idStatutActionTermine) {
        this.idStatutActionTermine = idStatutActionTermine;
    }

    /**
     * Récupère la valeur de idStatutAFaire.
     * @return la valeur de idStatutAFaire
     */
    public Long getIdStatutAFaire() {
        return idStatutAFaire;
    }

    /**
     * Définit la valeur de idStatutAFaire.
     * @param idStatutAFaire la nouvelle valeur de idStatutAFaire
     */
    public void setIdStatutAFaire(Long idStatutAFaire) {
        this.idStatutAFaire = idStatutAFaire;
    }

    /**
     * Get the idGroupementRelationTypeEnCoursParrainage value.
     * @return the idGroupementRelationTypeEnCoursParrainage
     */
    public Long getIdGroupementRelationTypeEnCoursParrainage() {
        return idGroupementRelationTypeEnCoursParrainage;
    }

    /**
     * Set the idGroupementRelationTypeEnCoursParrainage value.
     * @param idGroupementRelationTypeEnCoursParrainage the idGroupementRelationTypeEnCoursParrainage to set
     */
    public void setIdGroupementRelationTypeEnCoursParrainage(Long idGroupementRelationTypeEnCoursParrainage) {
        this.idGroupementRelationTypeEnCoursParrainage = idGroupementRelationTypeEnCoursParrainage;
    }

    /**
     * Get the idGroupementRelationTypeEchecParrainageGarantie value.
     * @return the idGroupementRelationTypeEchecParrainageGarantie
     */
    public Long getIdGroupementRelationTypeEchecParrainageGarantie() {
        return idGroupementRelationTypeEchecParrainageGarantie;
    }

    /**
     * Set the idGroupementRelationTypeEchecParrainageGarantie value.
     * @param idGroupementRelationTypeEchecParrainageGarantie the idGroupementRelationTypeEchecParrainageGarantie to set
     */
    public void setIdGroupementRelationTypeEchecParrainageGarantie(Long idGroupementRelationTypeEchecParrainageGarantie) {
        this.idGroupementRelationTypeEchecParrainageGarantie = idGroupementRelationTypeEchecParrainageGarantie;
    }

    /**
     * Get the idGroupementRelationTypeEchecParrainageAdherent value.
     * @return the idGroupementRelationTypeEchecParrainageAdherent
     */
    public Long getIdGroupementRelationTypeEchecParrainageAdherent() {
        return idGroupementRelationTypeEchecParrainageAdherent;
    }

    /**
     * Set the idGroupementRelationTypeEchecParrainageAdherent value.
     * @param idGroupementRelationTypeEchecParrainageAdherent the idGroupementRelationTypeEchecParrainageAdherent to set
     */
    public void setIdGroupementRelationTypeEchecParrainageAdherent(Long idGroupementRelationTypeEchecParrainageAdherent) {
        this.idGroupementRelationTypeEchecParrainageAdherent = idGroupementRelationTypeEchecParrainageAdherent;
    }

    /**
     * Get the libelleNonRenseigne value.
     * @return the libelleNonRenseigne
     */
    public String getLibelleNonRenseigne() {
        return libelleNonRenseigne;
    }

    /**
     * Set the libelleNonRenseigne value.
     * @param libelleNonRenseigne the libelleNonRenseigne to set
     */
    public void setLibelleNonRenseigne(String libelleNonRenseigne) {
        this.libelleNonRenseigne = libelleNonRenseigne;
    }

    /**
     * Get the libelleAPourEnfant.
     * @return the libelleAPourEnfant
     */
    public String getLibelleAPourEnfant() {
        return libelleAPourEnfant;
    }

    /**
     * Set the libelleAPourEnfant value.
     * @param libelleAPourEnfant the libelleAPourEnfant to set
     */
    public void setLibelleAPourEnfant(String libelleAPourEnfant) {
        this.libelleAPourEnfant = libelleAPourEnfant;
    }

    /**
     * Récupère la valeur de infosTeletransmissionCaisseOui.
     * @return la valeur de infosTeletransmissionCaisseOui
     */
    public String getInfosTeletransmissionCaisseOui() {
        return infosTeletransmissionCaisseOui;
    }

    /**
     * Définit la valeur de infosTeletransmissionCaisseOui.
     * @param infosTeletransmissionCaisseOui la nouvelle valeur de infosTeletransmissionCaisseOui
     */
    public void setInfosTeletransmissionCaisseOui(String infosTeletransmissionCaisseOui) {
        this.infosTeletransmissionCaisseOui = infosTeletransmissionCaisseOui;
    }

    /**
     * Récupère la valeur de infosTeletransmissionCaisseNon.
     * @return la valeur de infosTeletransmissionCaisseNon
     */
    public String getInfosTeletransmissionCaisseNon() {
        return infosTeletransmissionCaisseNon;
    }

    /**
     * Définit la valeur de infosTeletransmissionCaisseNon.
     * @param infosTeletransmissionCaisseNon la nouvelle valeur de infosTeletransmissionCaisseNon
     */
    public void setInfosTeletransmissionCaisseNon(String infosTeletransmissionCaisseNon) {
        this.infosTeletransmissionCaisseNon = infosTeletransmissionCaisseNon;
    }

    /**
     * Get the idTypeActionCommercial value.
     * @return the idTypeActionCommercial
     */
    public Long getIdTypeActionCommercial() {
        return idTypeActionCommercial;
    }

    /**
     * Set the idTypeActionCommercial value.
     * @param idTypeActionCommercial the idTypeActionCommercial to set
     */
    public void setIdTypeActionCommercial(Long idTypeActionCommercial) {
        this.idTypeActionCommercial = idTypeActionCommercial;
    }

    /**
     * Get the idObjetActionRelance value.
     * @return the idObjetActionRelance
     */
    public Long getIdObjetActionRelance() {
        return idObjetActionRelance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdObjetActionDemandeDevisOuPEC() {
        return idObjetActionDemandeDevisOuPEC;
    }

    /**
     * Setter function.
     * @param idObjetActionDemandeDevisOuPEC the idObjetActionDemandeDevisOuPEC to set
     */
    public void setIdObjetActionDemandeDevisOuPEC(Long idObjetActionDemandeDevisOuPEC) {
        this.idObjetActionDemandeDevisOuPEC = idObjetActionDemandeDevisOuPEC;
    }

    /**
     * Set the idObjetActionRelance value.
     * @param idObjetActionRelance the idObjetActionRelance to set
     */
    public void setIdObjetActionRelance(Long idObjetActionRelance) {
        this.idObjetActionRelance = idObjetActionRelance;
    }

    /**
     * Get the idSousObjetActionRelanceParrainage value.
     * @return the idSousObjetActionRelanceParrainage
     */
    public Long getIdSousObjetActionRelanceParrainage() {
        return idSousObjetActionRelanceParrainage;
    }

    /**
     * Set the idSousObjetActionRelanceParrainage value.
     * @param idSousObjetActionRelanceParrainage the idSousObjetActionRelanceParrainage to set
     */
    public void setIdSousObjetActionRelanceParrainage(Long idSousObjetActionRelanceParrainage) {
        this.idSousObjetActionRelanceParrainage = idSousObjetActionRelanceParrainage;
    }

    /**
     * Get the orderByRelationTypeOrdre value.
     * @return the orderByRelationTypeOrdre
     */
    public String getOrderByRelationTypeOrdre() {
        return orderByRelationTypeOrdre;
    }

    /**
     * Set the orderByRelationTypeOrdre value.
     * @param orderByRelationTypeOrdre the orderByRelationTypeOrdre to set
     */
    public void setOrderByRelationTypeOrdre(String orderByRelationTypeOrdre) {
        this.orderByRelationTypeOrdre = orderByRelationTypeOrdre;
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
     * @return the orderByActionId
     */
    public String getOrderByActionId() {
        return orderByActionId;
    }

    /**
     * Setter function.
     * @param orderByActionId the orderByActionId to set
     */
    public void setOrderByActionId(String orderByActionId) {
        this.orderByActionId = orderByActionId;
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
     * @param orderByActionDateAction the orderByActionDateAction to set
     */
    public void setOrderByActionDateAction(String orderByActionDateAction) {
        this.orderByActionDateAction = orderByActionDateAction;
    }

    /**
     * Setter function.
     * @param orderByActionDateTerminee the orderByActionDateTerminee to set
     */
    public void setOrderByActionDateTerminee(String orderByActionDateTerminee) {
        this.orderByActionDateTerminee = orderByActionDateTerminee;
    }

    /**
     * Getter function.
     * @return the idNaturePersonneDecede
     */
    public Long getIdNaturePersonneDecede() {
        return idNaturePersonneDecede;
    }

    /**
     * Setter function.
     * @param idNaturePersonneDecede the idNaturePersonneDecede to set
     */
    public void setIdNaturePersonneDecede(Long idNaturePersonneDecede) {
        this.idNaturePersonneDecede = idNaturePersonneDecede;
    }

    @Override
    public Long getIdNatureActionCourrierEntrant() {
        return idNatureActionCourrierEntrant;
    }

    /**
     * Définition de idNatureActionCourrierEntrant.
     * @param idNatureActionCourrierEntrant the idNatureActionCourrierEntrant to set
     */
    public void setIdNatureActionCourrierEntrant(Long idNatureActionCourrierEntrant) {
        this.idNatureActionCourrierEntrant = idNatureActionCourrierEntrant;
    }

    @Override
    public Long getIdObjetActionCourrier() {
        return idObjetActionCourrier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdObjetActionChgtCompoFamiliale() {
        return idObjetActionChgtCompoFamiliale;
    }

    /**
     * Setter function.
     * @param idObjetActionChgtCompoFamiliale the idObjetActionChgtCompoFamiliale to set
     */
    public void setIdObjetActionChgtCompoFamiliale(Long idObjetActionChgtCompoFamiliale) {
        this.idObjetActionChgtCompoFamiliale = idObjetActionChgtCompoFamiliale;
    }

    @Override
    public Long getIdTypeActionDocument() {
        return idTypeActionDocument;
    }

    /**
     * Définition de idTypeActionDocument.
     * @param idTypeActionDocument the idTypeActionDocument to set
     */
    public void setIdTypeActionDocument(Long idTypeActionDocument) {
        this.idTypeActionDocument = idTypeActionDocument;
    }

    /**
     * Définition de idObjetActionCourrier.
     * @param idObjetActionCourrier the idObjetActionCourrier to set
     */
    public void setIdObjetActionCourrier(Long idObjetActionCourrier) {
        this.idObjetActionCourrier = idObjetActionCourrier;
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
     * Récupère la valeur de idObjetActionNouvelleAdhesion.
     * @return la valeur de idObjetActionNouvelleAdhesion
     */
    public Long getIdObjetActionNouvelleAdhesion() {
        return idObjetActionNouvelleAdhesion;
    }

    /**
     * Définit la valeur de idObjetActionNouvelleAdhesion.
     * @param idObjetActionNouvelleAdhesion la nouvelle valeur de idObjetActionNouvelleAdhesion
     */
    public void setIdObjetActionNouvelleAdhesion(Long idObjetActionNouvelleAdhesion) {
        this.idObjetActionNouvelleAdhesion = idObjetActionNouvelleAdhesion;
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
     * Récupère la valeur de idObjetActionClient.
     * @return la valeur de idObjetActionClient
     */
    public Long getIdObjetActionClient() {
        return idObjetActionClient;
    }

    /**
     * Définit la valeur de idObjetActionClient.
     * @param idObjetActionClient la nouvelle valeur de idObjetActionClient
     */
    public void setIdObjetActionClient(Long idObjetActionClient) {
        this.idObjetActionClient = idObjetActionClient;
    }

    /**
     * Récupère la valeur de idNatureTelephoneFixeTravail.
     * @return la valeur de idNatureTelephoneFixeTravail
     */
    public Long getIdNatureTelephoneFixeTravail() {
        return idNatureTelephoneFixeTravail;
    }

    /**
     * Définit la valeur de idNatureTelephoneFixeTravail.
     * @param idNatureTelephoneFixeTravail la nouvelle valeur de idNatureTelephoneFixeTravail
     */
    public void setIdNatureTelephoneFixeTravail(Long idNatureTelephoneFixeTravail) {
        this.idNatureTelephoneFixeTravail = idNatureTelephoneFixeTravail;
    }

    /**
     * Récupère la valeur de idNatureFaxPrive.
     * @return la valeur de idNatureFaxPrive
     */
    public Long getIdNatureFaxPrive() {
        return idNatureFaxPrive;
    }

    /**
     * Définit la valeur de idNatureFaxPrive.
     * @param idNatureFaxPrive la nouvelle valeur de idNatureFaxPrive
     */
    public void setIdNatureFaxPrive(Long idNatureFaxPrive) {
        this.idNatureFaxPrive = idNatureFaxPrive;
    }

    @Override
    public List<Long> getListeIdsStatutsLoiMadelin() {
        return listeIdsStatutsLoiMadelin;
    }

    /**
     * Définit la valeur de listeIdsStatutsLoiMadelin.
     * @param listeIdsStatutsLoiMadelin la nouvelle valeur de listeIdsStatutsLoiMadelin
     */
    public void setListeIdsStatutsLoiMadelin(List<Long> listeIdsStatutsLoiMadelin) {
        this.listeIdsStatutsLoiMadelin = listeIdsStatutsLoiMadelin;
    }

    /**
     * Get the decalageDateActionRelance value.
     * @return the decalageDateActionRelance
     */
    public int getDecalageDateActionRelance() {
        return decalageDateActionRelance;
    }

    /**
     * Set the decalageDateActionRelance value.
     * @param decalageDateActionRelance the decalageDateActionRelance to set
     */
    public void setDecalageDateActionRelance(int decalageDateActionRelance) {
        this.decalageDateActionRelance = decalageDateActionRelance;
    }

    /**
     * Get the decalageDateAffichageAction value.
     * @return the decalageDateAffichageAction
     */
    public int getDecalageDateAffichageAction() {
        return decalageDateAffichageAction;
    }

    /**
     * Set the decalageDateAffichageAction value.
     * @param decalageDateAffichageAction the decalageDateAffichageAction to set
     */
    public void setDecalageDateAffichageAction(int decalageDateAffichageAction) {
        this.decalageDateAffichageAction = decalageDateAffichageAction;
    }

    /**
     * Getter function.
     * @return the idActionPrioriteHaute
     */
    public Long getIdActionPrioriteHaute() {
        return idActionPrioriteHaute;
    }

    /**
     * Setter function.
     * @param idActionPrioriteHaute the idActionPrioriteHaute to set
     */
    public void setIdActionPrioriteHaute(Long idActionPrioriteHaute) {
        this.idActionPrioriteHaute = idActionPrioriteHaute;
    }

    /**
     * Getter function.
     * @return the idActionPrioriteUrgente
     */
    public Long getIdActionPrioriteUrgente() {
        return idActionPrioriteUrgente;
    }

    /**
     * Setter function.
     * @param idActionPrioriteUrgente the idActionPrioriteUrgente to set
     */
    public void setIdActionPrioriteUrgente(Long idActionPrioriteUrgente) {
        this.idActionPrioriteUrgente = idActionPrioriteUrgente;
    }

    @Override
    public Long getIdNatureTelephoneErrone() {
        return idNatureTelephoneErrone;
    }

    /**
     * Définition de idNatureTelephoneErrone.
     * @param idNatureTelephoneErrone the idNatureTelephoneErrone to set
     */
    public void setIdNatureTelephoneErrone(Long idNatureTelephoneErrone) {
        this.idNatureTelephoneErrone = idNatureTelephoneErrone;
    }

    @Override
    public Long getIdObjetModificationAdressePostale() {
        return idObjetModificationAdressePostale;
    }

    @Override
    public Long getIdTypeActionModification() {
        return idTypeActionModification;
    }

    /**
     * Définition de idObjetModificationAdressePostale.
     * @param idObjetModificationAdressePostale the idObjetModificationAdressePostale to set
     */
    public void setIdObjetModificationAdressePostale(Long idObjetModificationAdressePostale) {
        this.idObjetModificationAdressePostale = idObjetModificationAdressePostale;
    }

    /**
     * Définition de idTypeActionModification.
     * @param idTypeActionModification the idTypeActionModification to set
     */
    public void setIdTypeActionModification(Long idTypeActionModification) {
        this.idTypeActionModification = idTypeActionModification;
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
     * Récupère la valeur de idNatureActionEmailEntrant.
     * @return la valeur de idNatureActionEmailEntrant
     */
    public Long getIdNatureActionEmailEntrant() {
        return idNatureActionEmailEntrant;
    }

    /**
     * Définit la valeur de idNatureActionEmailEntrant.
     * @param idNatureActionEmailEntrant la nouvelle valeur de idNatureActionEmailEntrant
     */
    public void setIdNatureActionEmailEntrant(Long idNatureActionEmailEntrant) {
        this.idNatureActionEmailEntrant = idNatureActionEmailEntrant;
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
     * Récupère la valeur de idSousObjetActionSansChangementDepartement.
     * @return la valeur de idSousObjetActionSansChangementDepartement
     */
    public Long getIdSousObjetActionSansChangementDepartement() {
        return idSousObjetActionSansChangementDepartement;
    }

    /**
     * Définit la valeur de idSousObjetActionSansChangementDepartement.
     * @param idSousObjetActionSansChangementDepartement la nouvelle valeur de idSousObjetActionSansChangementDepartement
     */
    public void setIdSousObjetActionSansChangementDepartement(Long idSousObjetActionSansChangementDepartement) {
        this.idSousObjetActionSansChangementDepartement = idSousObjetActionSansChangementDepartement;
    }

    /**
     * Récupère la valeur de idSousObjetActionAvecChangementDepartement.
     * @return la valeur de idSousObjetActionAvecChangementDepartement
     */
    public Long getIdSousObjetActionAvecChangementDepartement() {
        return idSousObjetActionAvecChangementDepartement;
    }

    /**
     * Définit la valeur de idSousObjetActionAvecChangementDepartement.
     * @param idSousObjetActionAvecChangementDepartement la nouvelle valeur de idSousObjetActionAvecChangementDepartement
     */
    public void setIdSousObjetActionAvecChangementDepartement(Long idSousObjetActionAvecChangementDepartement) {
        this.idSousObjetActionAvecChangementDepartement = idSousObjetActionAvecChangementDepartement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public Long getIdSousObjetActionAdhesionEnLigne() {
        return idSousObjetActionAdhesionEnLigne;
    }

    /**
     * Définit la valeur de idSousObjetActionAdhesionEnLigne.
     * @param idSousObjetActionAdhesionEnLigne la nouvelle valeur de idSousObjetActionAdhesionEnLigne
     */
    public void setIdSousObjetActionAdhesionEnLigne(Long idSousObjetActionAdhesionEnLigne) {
        this.idSousObjetActionAdhesionEnLigne = idSousObjetActionAdhesionEnLigne;
    }

    /**
     * Récupère la valeur de proprietePersonneDateCreation.
     * @return la valeur de proprietePersonneDateCreation
     */
    public String getProprietePersonneDateCreation() {
        return proprietePersonneDateCreation;
    }

    /**
     * Définit la valeur de proprietePersonneDateCreation.
     * @param proprietePersonneDateCreation la nouvelle valeur de proprietePersonneDateCreation
     */
    public void setProprietePersonneDateCreation(String proprietePersonneDateCreation) {
        this.proprietePersonneDateCreation = proprietePersonneDateCreation;
    }

    /**
     * Récupère la valeur de idEtatInactifRessource.
     * @return la valeur de idEtatInactifRessource
     */
    public Long getIdEtatInactifRessource() {
        return idEtatInactifRessource;
    }

    /**
     * Définit la valeur de idEtatInactifRessource.
     * @param idEtatInactifRessource la nouvelle valeur de idEtatInactifRessource
     */
    public void setIdEtatInactifRessource(Long idEtatInactifRessource) {
        this.idEtatInactifRessource = idEtatInactifRessource;
    }

    /**
     * Get the idNatureActionAchatFichiers value.
     * @return the idNatureActionAchatFichiers
     */
    public Long getIdNatureActionAchatFichiers() {
        return idNatureActionAchatFichiers;
    }

    /**
     * Set the idNatureActionAchatFichiers value.
     * @param idNatureActionAchatFichiers the idNatureActionAchatFichiers to set
     */
    public void setIdNatureActionAchatFichiers(Long idNatureActionAchatFichiers) {
        this.idNatureActionAchatFichiers = idNatureActionAchatFichiers;
    }

    /**
     * Get the roleSquareAnimateur value.
     * @return the roleSquareAnimateur
     */
    public String getRoleSquareAnimateur() {
        return roleSquareAnimateur;
    }

    /**
     * Set the roleSquareAnimateur value.
     * @param roleSquareAnimateur the roleSquareAnimateur to set
     */
    public void setRoleSquareAnimateur(String roleSquareAnimateur) {
        this.roleSquareAnimateur = roleSquareAnimateur;
    }

    /**
     * Définit la valeur de ressourceHabilitationUtil.
     * @param ressourceHabilitationUtil la nouvelle valeur de ressourceHabilitationUtil
     */
    public void setRessourceHabilitationUtil(RessourceHabilitationUtil ressourceHabilitationUtil) {
        this.ressourceHabilitationUtil = ressourceHabilitationUtil;
    }

    @Override
    public Long getIdentifiantRoleGestionnaireOpportunites() {
        return ressourceHabilitationUtil.getIdentifiantRoleGestionnaireOpportunites();
    }

    @Override
    public Long getIdentifiantAgenceFrance() {
        return ressourceHabilitationUtil.getIdentifiantAgenceFrance();
    }

    /**
     * Récupère la valeur de idFonctionCMP.
     * @return la valeur de idFonctionCMP
     */
    public Long getIdFonctionCMP() {
        return idFonctionCMP;
    }

    /**
     * Définit la valeur de idFonctionCMP.
     * @param idFonctionCMP la nouvelle valeur de idFonctionCMP
     */
    public void setIdFonctionCMP(Long idFonctionCMP) {
        this.idFonctionCMP = idFonctionCMP;
    }

    /**
     * Récupère la valeur de idFonctionCCD.
     * @return la valeur de idFonctionCCD
     */
    public Long getIdFonctionCCD() {
        return idFonctionCCD;
    }

    /**
     * Définit la valeur de idFonctionCCD.
     * @param idFonctionCCD la nouvelle valeur de idFonctionCCD
     */
    public void setIdFonctionCCD(Long idFonctionCCD) {
        this.idFonctionCCD = idFonctionCCD;
    }

    /**
     * Getter function.
     * @return the idStatutActionAFaire
     */
    public Long getIdStatutActionAFaire() {
        return idStatutActionAFaire;
    }

    /**
     * Getter function.
     * @return the idStatutActionAnnule
     */
    public Long getIdStatutActionAnnule() {
        return idStatutActionAnnule;
    }

    /**
     * Setter function.
     * @param idStatutActionAFaire the idStatutActionAFaire to set
     */
    public void setIdStatutActionAFaire(Long idStatutActionAFaire) {
        this.idStatutActionAFaire = idStatutActionAFaire;
    }

    /**
     * Setter function.
     * @param idStatutActionAnnule the idStatutActionAnnule to set
     */
    public void setIdStatutActionAnnule(Long idStatutActionAnnule) {
        this.idStatutActionAnnule = idStatutActionAnnule;
    }

    @Override
    public String getRoleSquareAdmin() {
        return roleSquareAdmin;
    }

    /**
     * setter de roleSquareAdmin.
     * @param roleSquareAdmin the roleSquareAdmin to set
     */
    public void setRoleSquareAdmin(String roleSquareAdmin) {
        this.roleSquareAdmin = roleSquareAdmin;
    }

    /**
     * Get the idNaturePersonneAssureSocial value.
     * @return the idNaturePersonneAssureSocial
     */
    public Long getIdNaturePersonneAssureSocial() {
        return idNaturePersonneAssureSocial;
    }

    /**
     * Set the idNaturePersonneAssureSocial value.
     * @param idNaturePersonneAssureSocial the idNaturePersonneAssureSocial to set
     */
    public void setIdNaturePersonneAssureSocial(Long idNaturePersonneAssureSocial) {
        this.idNaturePersonneAssureSocial = idNaturePersonneAssureSocial;
    }

    /**
     * Renvoi la valeur de idTypeRelationAssureSociale.
     * @return idTypeRelationAssureSociale
     */
    public Long getIdTypeRelationAssureSociale() {
        return idTypeRelationAssureSociale;
    }

    /**
     * Modifie idTypeRelationAssureSociale.
     * @param idTypeRelationAssureSociale la nouvelle valeur de idTypeRelationAssureSociale
     */
    public void setIdTypeRelationAssureSociale(Long idTypeRelationAssureSociale) {
        this.idTypeRelationAssureSociale = idTypeRelationAssureSociale;
    }

    @Override
    public Long getIdNaturePersonneCorrespondant() {
        return idNaturePersonneCorrespondant;
    }

    @Override
    public Long getIdNaturePersonneMoraleProspect() {
        return idNaturePersonneMoraleProspect;
    }

    @Override
    public Long getIdSousObjetActionCollectif() {
        return idSousObjetActionCollectif;
    }

    @Override
    public Long getIdTypeRelationCorrespondant() {
        return idTypeRelationCorrespondant;
    }

    /**
     * Définit la valeur de idNaturePersonneCorrespondant.
     * @param idNaturePersonneCorrespondant la nouvelle valeur de idNaturePersonneCorrespondant
     */
    public void setIdNaturePersonneCorrespondant(Long idNaturePersonneCorrespondant) {
        this.idNaturePersonneCorrespondant = idNaturePersonneCorrespondant;
    }

    /**
     * Définit la valeur de idNaturePersonneMoraleProspect.
     * @param idNaturePersonneMoraleProspect la nouvelle valeur de idNaturePersonneMoraleProspect
     */
    public void setIdNaturePersonneMoraleProspect(Long idNaturePersonneMoraleProspect) {
        this.idNaturePersonneMoraleProspect = idNaturePersonneMoraleProspect;
    }

    /**
     * Définit la valeur de idSousObjetActionCollectif.
     * @param idSousObjetActionCollectif la nouvelle valeur de idSousObjetActionCollectif
     */
    public void setIdSousObjetActionCollectif(Long idSousObjetActionCollectif) {
        this.idSousObjetActionCollectif = idSousObjetActionCollectif;
    }

    /**
     * Définit la valeur de idTypeRelationCorrespondant.
     * @param idTypeRelationCorrespondant la nouvelle valeur de idTypeRelationCorrespondant
     */
    public void setIdTypeRelationCorrespondant(Long idTypeRelationCorrespondant) {
        this.idTypeRelationCorrespondant = idTypeRelationCorrespondant;
    }

    /**
     * Récupère la valeur de idAgencePoleFidelisation.
     * @return la valeur de idAgencePoleFidelisation
     */
    public Long getIdAgencePoleFidelisation() {
        return idAgencePoleFidelisation;
    }

    /**
     * Définit la valeur de idAgencePoleFidelisation.
     * @param idAgencePoleFidelisation la nouvelle valeur de idAgencePoleFidelisation
     */
    public void setIdAgencePoleFidelisation(Long idAgencePoleFidelisation) {
        this.idAgencePoleFidelisation = idAgencePoleFidelisation;
    }

    /**
     * Renvoie la valeur de idObjetActionJustificatifIdentite.
     * @return idObjetActionJustificatifIdentite
     */
    public Long getIdObjetActionJustificatifIdentite() {
        return idObjetActionJustificatifIdentite;
    }

    /**
     * Modifie idObjetActionJustificatifIdentite.
     * @param idObjetActionJustificatifIdentite la nouvelle valeur de idObjetActionJustificatifIdentite
     */
    public void setIdObjetActionJustificatifIdentite(Long idObjetActionJustificatifIdentite) {
        this.idObjetActionJustificatifIdentite = idObjetActionJustificatifIdentite;
    }

    /**
     * Renvoie la valeur de idTypeActionLab.
     * @return idTypeActionLab
     */
    public Long getIdTypeActionLab() {
        return idTypeActionLab;
    }

    /**
     * Modifie idTypeActionLab.
     * @param idTypeActionLab la nouvelle valeur de idTypeActionLab
     */
    public void setIdTypeActionLab(Long idTypeActionLab) {
        this.idTypeActionLab = idTypeActionLab;
    }

    /**
     * Renvoie la valeur de idSousObjetActionRelance.
     * @return idSousObjetActionRelance
     */
    public Long getIdSousObjetActionRelance() {
        return idSousObjetActionRelance;
    }

    /**
     * Modifie idSousObjetActionRelance.
     * @param idSousObjetActionRelance la nouvelle valeur de idSousObjetActionRelance
     */
    public void setIdSousObjetActionRelance(Long idSousObjetActionRelance) {
        this.idSousObjetActionRelance = idSousObjetActionRelance;
    }

    /**
     * Renvoie la valeur de idAgenceInternet.
     * @return idAgenceInternet
     */
    public Long getIdAgenceInternet() {
        return idAgenceInternet;
    }

    /**
     * Modifie idAgenceInternet.
     * @param idAgenceInternet la nouvelle valeur de idAgenceInternet
     */
    public void setIdAgenceInternet(Long idAgenceInternet) {
        this.idAgenceInternet = idAgenceInternet;
    }

    /**
     * Renvoie la valeur de idObjetActionControle.
     * @return idObjetActionControle
     */
    public Long getIdObjetActionControle() {
        return idObjetActionControle;
    }

    /**
     * Modifie idObjetActionControle.
     * @param idObjetActionControle la nouvelle valeur de idObjetActionControle
     */
    public void setIdObjetActionControle(Long idObjetActionControle) {
        this.idObjetActionControle = idObjetActionControle;
    }

    @Override
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
     * Modifie dimensionService.
     * @param dimensionService la nouvelle valeur de dimensionService
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Get the value of orderByRelationActif.
     * @return the orderByRelationActif
     */
    public String getOrderByRelationActif() {
        return orderByRelationActif;
    }

    /**
     * Set the value of orderByRelationActif.
     * @param orderByRelationActif the orderByRelationActif to set
     */
    public void setOrderByRelationActif(String orderByRelationActif) {
        this.orderByRelationActif = orderByRelationActif;
    }

    /**
     * Get the value of orderByRelationDateFin.
     * @return the orderByRelationDateFin
     */
    public String getOrderByRelationDateFin() {
        return orderByRelationDateFin;
    }

    /**
     * Set the value of orderByRelationDateFin.
     * @param orderByRelationDateFin the orderByRelationDateFin to set
     */
    public void setOrderByRelationDateFin(String orderByRelationDateFin) {
        this.orderByRelationDateFin = orderByRelationDateFin;
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

    /**
     * Get the value of idFonctionAnimateur.
     * @return the idFonctionAnimateur
     */
    public Long getIdFonctionAnimateur() {
        return idFonctionAnimateur;
    }

    /**
     * Set the value of idFonctionAnimateur.
     * @param idFonctionAnimateur the idFonctionAnimateur to set
     */
    public void setIdFonctionAnimateur(Long idFonctionAnimateur) {
        this.idFonctionAnimateur = idFonctionAnimateur;
    }

    /**
     * Get the value of idReseauIndividuel.
     * @return the idReseauIndividuel
     */
    public Long getIdReseauIndividuel() {
        return idReseauIndividuel;
    }

    /**
     * Set the value of idReseauIndividuel.
     * @param idReseauIndividuel the idReseauIndividuel to set
     */
    public void setIdReseauIndividuel(Long idReseauIndividuel) {
        this.idReseauIndividuel = idReseauIndividuel;
    }

    /**
     * Get the value of idNaturePersonneBeneficiaireAdherent.
     * @return the idNaturePersonneBeneficiaireAdherent
     */
    public Long getIdNaturePersonneBeneficiaireAdherent() {
        return idNaturePersonneBeneficiaireAdherent;
    }

    /**
     * Set the value of idNaturePersonneBeneficiaireAdherent.
     * @param idNaturePersonneBeneficiaireAdherent the idNaturePersonneBeneficiaireAdherent to set
     */
    public void setIdNaturePersonneBeneficiaireAdherent(Long idNaturePersonneBeneficiaireAdherent) {
        this.idNaturePersonneBeneficiaireAdherent = idNaturePersonneBeneficiaireAdherent;
    }
}
