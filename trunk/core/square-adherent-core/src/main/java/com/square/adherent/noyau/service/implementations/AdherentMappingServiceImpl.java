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
package com.square.adherent.noyau.service.implementations;

import java.util.List;
import java.util.Map;

import com.square.adherent.noyau.service.interfaces.AdherentMappingService;

/**
 * Implémentation de AdherentMappingService.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class AdherentMappingServiceImpl implements AdherentMappingService {

    /** Identifiant du statut "En cours" d'un contrat. */
    private Long idStatutContratEnCours;

    /** Identifiant du statut "Résilié" d'un contrat. */
    private Long idStatutContratResilie;

    /** Identifiant du statut "Futur" d'un contrat. */
    private Long idStatutContratFutur;

    /** Identifiant du segment "Collectif". */
    private Long idSegmentCollectif;

    /** Identifiant du segment "Collectif Individualisé". */
    private Long idSegmentCollectifIndividualise;

    /** Identifiant du type payeur "Souscripteur". */
    private Long idTypePayeurSouscripteur;

    /** Identifiant du type payeur "Multipart". */
    private Long idTypePayeurMultipart;

    /** Identifiant du type payeur "Assuré". */
    private Long idTypePayeurAssure;

    /** Identifiant du type d'option. */
    private Long idTypeOptionEnvoiRelevesPrestationEmail;

    /** Identifiant du type d'option. */
    private Long idTypeOptionEnvoiMutuellementEmail;

    /** Identifiant du type d'option. */
    private Long idTypeOptionEnvoiSms;

    /** Identifiant de la nature de contrat "Santé". */
    private Long idNatureContratSante;

    /** Identifiant de la nature de contrat "Prévoyance". */
    private Long idNatureContratPrevoyance;

    /** Identifiant de la nature de contrat "Autre". */
    private Long idNatureContratAutre;

    /** Identifiant du statut de garantie "En cours". */
    private Long idStatutGarantieEnCours;

    /** Identifiant du statut de garantie "Résiliée". */
    private Long idStatutGarantieResiliee;

    /** Identifiant du statut de garantie "Futur". */
    private Long idStatutGarantieFutur;

    /** Identifiant du statut de garantie "Sans Effet". */
    private Long idStatutGarantieSansEffet;

    /** Identifiant de la famille de garantie "Santé". */
    private Long idFamilleGarantieSante;

    /** Identifiant de la famille de garantie "Bonus". */
    private Long idFamilleGarantieBonus;

    /** Identifiant de la famille de garantie "Assistance". */
    private Long idFamilleGarantieAssistance;

    /** Identifiant de la famille de garantie "Assistance". */
    private Long idFamilleGarantieExoneration;

    /** Identifiant du rôle "Assuré" d'une garantie. */
    private Long idRoleGarantieAssure;

    /** Identifiant du rôle "Conjoint" d'une garantie. */
    private Long idRoleGarantieConjoint;

    /** Identifiant du rôle "Concubin" d'une garantie. */
    private Long idRoleGarantieConcubin;

    /** Identifiant de la nature "Espace client" d'une connexion. */
    private Long idNatureConnexionEspaceClient;

    /** Identifiant de l'origine de décompte "Almerys". */
    private Long idOrigineDecompteAlmerys;

    /** Identifiant de l'origine de décompte "Indu". */
    private Long idOrigineDecompteIndu;

    /** Identifiant du motif de résiliation "Sans effet". */
    private Long idMotifResiliationSansEffet;

    /** Identifiant du motif de résiliation "Erreur de saisie". */
    private Long idMotifResiliationErreurDeSaisie;

    /** Identifiant du moyen de paiement par chèque. */
    private Long idMoyenPaiementCheque;

    /** Identifiant du moyen de paiement par virement. */
    private Long idMoyenPaiementVirement;

    /** Identifiant du moyen de paiement par virement encaissement. */
    private Long idMoyenPaiementVirementEncaissement;

    /** Identifiant du moyen de paiement par espèce. */
    private Long idMoyenPaiementEspece;

    /** Identifiant du moyen de paiement par mandat. */
    private Long idMoyenPaiementMandat;

    /** Identifiant de l'acte non remboursable. */
    private Long idActeNonRemboursable;
    
    /** Identifiant du moyen de paiement par prélèvement. */
    private Long idMoyenPaiementPrelevement;

    /** Jour de paiement par défaut. **/
    private int jourPaiementPrelevementCinq;

    /** Fréquence de paiement par défaut. **/
	private Long idFrequencePrelevementMensuelle;

	/** Récupérer le contrat de santé. **/
	private Long idContratSante; 
	
    /** Tri des décompte par la date de règlement. */
    private String orderDecompteByDateReglement;

    /** Tri des décompte par l'id. */
    private String orderDecompteById;

    /** Tri des décompte par le numero. */
    private String orderDecompteByNumero;

    /** Tri des décompte par le numero de couplage. */
    private String orderDecompteByNumeroCouplage;

    /** Tri des décompte par la date de soin. */
    private String orderDecompteByDateSoin;

    /** Tri des décompte par la date de fin soin. */
    private String orderDecompteByDateFinSoin;

    /** Tri des groupes de décomptes par la date de soin. */
    private String orderDecomptesByDateDebutSoin;

    /** Tri des groupes de décompte par la date de fin soin. */
    private String orderDecomptesByDateFinSoin;

    /** Tri des groupes de décompte par le montant remboursé par la Smatis. */
    private String orderDecomptesByRbtSmatis;

    /** Tri des décompte par le nom du bénéficiaire des soins. */
    private String orderDecomptesByNomBeneficiaireSoins;

    /** Tri des décompte par le nom du destinataire. */
    private String orderDecompteByNomDestinataireReglement;

    /** Tri des décompte par le bénéficiaire. */
    private String orderDecompteByBeneficiaire;

    /** Tri des décompte par l'acte. */
    private String orderDecompteByActe;

    /** Tri des décompte par la dépense. */
    private String orderDecompteByDepense;

    /** Tri des décompte par la base. */
    private String orderDecompteByBase;

    /** Tri des décompte par le taux. */
    private String orderDecompteByTaux;

    /** Tri des décompte par le remboursement de la sécu. */
    private String orderDecompteByRbtRO;

    /** Tri des décompte par le remboursement de la Smatis. */
    private String orderDecompteByRbtSmatis;

    /** Tri des décompte par le remboursement professionnel. */
    private String orderDecompteByRbtProf;

    /** Tri des décompte par le reste à charge. */
    private String orderDecompteByRaC;

    /** Le différé (en jours) entre la date actuelle et la date d'impression des relevés pour l'envoi par mail. */
    private int nombreJoursDifferesEnvoiMail;

    /** . */
    private List<Long> listeOriginesDecomptesExcluesEnvoiSms;

    /** HashMap pour l'ordre des populations. */
    private Map<String, Integer> mapOrdrePopulation;

    /** Tri des ccotisations par la date de debut. */
    private String orderCotisationDateDebut;

    /** Tri des ccotisations par le montant. */
    private String orderCotisationMontant;

    /** Tri des ccotisations par le montant reglé. */
    private String orderCotisationMontantRegle;

    /** Tri des ccotisations par la situation. */
    private String orderCotisationSituation;

    /** Identifiant du type de relation conjoint. */
    private Long idTypeRelationConjoint;

    /** Identifiant du type de relation enfant. */
    private Long idTypeRelationEnfant;

    /**
     * Map qui classe les statuts de garantie par niveau d'importance.
     * clé : identifiant du statut de garantie
     * valeur : niveau d'importance du statut
     */
    private Map<Long, Integer> mapImportanceStatutsGaranties;

    /** Valeur du statut de paiement payé d'un décompte. */
    private String idStatutPaiementDecomptePaye;

    /** Valeur du statut de paiement non payé d'un décompte. */
    private String idStatutPaiementDecompteNonPaye;

    /** Identifiant de la nature de règlement "Tiers Santé". */
    private String idNatureReglementTiersSante;

    /**
     * Récupère la valeur de idStatutContratEnCours.
     * @return la valeur de idStatutContratEnCours
     */
    public Long getIdStatutContratEnCours() {
        return idStatutContratEnCours;
    }

    /**
     * Définit la valeur de idStatutContratEnCours.
     * @param idStatutContratEnCours la nouvelle valeur de idStatutContratEnCours
     */
    public void setIdStatutContratEnCours(Long idStatutContratEnCours) {
        this.idStatutContratEnCours = idStatutContratEnCours;
    }

    /**
     * Récupère la valeur de idStatutContratResilie.
     * @return la valeur de idStatutContratResilie
     */
    public Long getIdStatutContratResilie() {
        return idStatutContratResilie;
    }

    /**
     * Définit la valeur de idStatutContratResilie.
     * @param idStatutContratResilie la nouvelle valeur de idStatutContratResilie
     */
    public void setIdStatutContratResilie(Long idStatutContratResilie) {
        this.idStatutContratResilie = idStatutContratResilie;
    }

    /**
     * Récupère la valeur de idSegmentCollectif.
     * @return la valeur de idSegmentCollectif
     */
    public Long getIdSegmentCollectif() {
        return idSegmentCollectif;
    }

    /**
     * Définit la valeur de idSegmentCollectif.
     * @param idSegmentCollectif la nouvelle valeur de idSegmentCollectif
     */
    public void setIdSegmentCollectif(Long idSegmentCollectif) {
        this.idSegmentCollectif = idSegmentCollectif;
    }

    /**
     * Récupère la valeur de idTypePayeurSouscripteur.
     * @return la valeur de idTypePayeurSouscripteur
     */
    public Long getIdTypePayeurSouscripteur() {
        return idTypePayeurSouscripteur;
    }

    /**
     * Définit la valeur de idTypePayeurSouscripteur.
     * @param idTypePayeurSouscripteur la nouvelle valeur de idTypePayeurSouscripteur
     */
    public void setIdTypePayeurSouscripteur(Long idTypePayeurSouscripteur) {
        this.idTypePayeurSouscripteur = idTypePayeurSouscripteur;
    }

    /**
     * Getter function.
     * @return the idTypeOptionEnvoiRelevesPrestationEmail
     */
    public Long getIdTypeOptionEnvoiRelevesPrestationEmail() {
        return idTypeOptionEnvoiRelevesPrestationEmail;
    }

    /**
     * Getter function.
     * @return the idTypeOptionEnvoiMutuellementEmail
     */
    public Long getIdTypeOptionEnvoiMutuellementEmail() {
        return idTypeOptionEnvoiMutuellementEmail;
    }

    /**
     * Getter function.
     * @return the idTypeOptionEnvoiSms
     */
    public Long getIdTypeOptionEnvoiSms() {
        return idTypeOptionEnvoiSms;
    }

    /**
     * Setter function.
     * @param idTypeOptionEnvoiRelevesPrestationEmail the idTypeOptionEnvoiRelevesPrestationEmail to set
     */
    public void setIdTypeOptionEnvoiRelevesPrestationEmail(Long idTypeOptionEnvoiRelevesPrestationEmail) {
        this.idTypeOptionEnvoiRelevesPrestationEmail = idTypeOptionEnvoiRelevesPrestationEmail;
    }

    /**
     * Setter function.
     * @param idTypeOptionEnvoiMutuellementEmail the idTypeOptionEnvoiMutuellementEmail to set
     */
    public void setIdTypeOptionEnvoiMutuellementEmail(Long idTypeOptionEnvoiMutuellementEmail) {
        this.idTypeOptionEnvoiMutuellementEmail = idTypeOptionEnvoiMutuellementEmail;
    }

    /**
     * Setter function.
     * @param idTypeOptionEnvoiSms the idTypeOptionEnvoiSms to set
     */
    public void setIdTypeOptionEnvoiSms(Long idTypeOptionEnvoiSms) {
        this.idTypeOptionEnvoiSms = idTypeOptionEnvoiSms;
    }

    /**
     * Récupère la valeur de idNatureContratSante.
     * @return la valeur de idNatureContratSante
     */
    public Long getIdNatureContratSante() {
        return idNatureContratSante;
    }

    /**
     * Définit la valeur de idNatureContratSante.
     * @param idNatureContratSante la nouvelle valeur de idNatureContratSante
     */
    public void setIdNatureContratSante(Long idNatureContratSante) {
        this.idNatureContratSante = idNatureContratSante;
    }

    /**
     * Récupère la valeur de idStatutGarantieEnCours.
     * @return la valeur de idStatutGarantieEnCours
     */
    public Long getIdStatutGarantieEnCours() {
        return idStatutGarantieEnCours;
    }

    /**
     * Définit la valeur de idStatutGarantieEnCours.
     * @param idStatutGarantieEnCours la nouvelle valeur de idStatutGarantieEnCours
     */
    public void setIdStatutGarantieEnCours(Long idStatutGarantieEnCours) {
        this.idStatutGarantieEnCours = idStatutGarantieEnCours;
    }

    /**
     * Récupère la valeur de idFamilleGarantieBonus.
     * @return la valeur de idFamilleGarantieBonus
     */
    public Long getIdFamilleGarantieBonus() {
        return idFamilleGarantieBonus;
    }

    /**
     * Définit la valeur de idFamilleGarantieBonus.
     * @param idFamilleGarantieBonus la nouvelle valeur de idFamilleGarantieBonus
     */
    public void setIdFamilleGarantieBonus(Long idFamilleGarantieBonus) {
        this.idFamilleGarantieBonus = idFamilleGarantieBonus;
    }

    /**
     * Récupère la valeur de idRoleGarantieAssure.
     * @return la valeur de idRoleGarantieAssure
     */
    public Long getIdRoleGarantieAssure() {
        return idRoleGarantieAssure;
    }

    /**
     * Définit la valeur de idRoleGarantieAssure.
     * @param idRoleGarantieAssure la nouvelle valeur de idRoleGarantieAssure
     */
    public void setIdRoleGarantieAssure(Long idRoleGarantieAssure) {
        this.idRoleGarantieAssure = idRoleGarantieAssure;
    }

    /**
     * Récupère la valeur de idNatureContratPrevoyance.
     * @return la valeur de idNatureContratPrevoyance
     */
    public Long getIdNatureContratPrevoyance() {
        return idNatureContratPrevoyance;
    }

    /**
     * Définit la valeur de idNatureContratPrevoyance.
     * @param idNatureContratPrevoyance la nouvelle valeur de idNatureContratPrevoyance
     */
    public void setIdNatureContratPrevoyance(Long idNatureContratPrevoyance) {
        this.idNatureContratPrevoyance = idNatureContratPrevoyance;
    }

    /**
     * Récupère la valeur de idNatureContratAutre.
     * @return la valeur de idNatureContratAutre
     */
    public Long getIdNatureContratAutre() {
        return idNatureContratAutre;
    }

    /**
     * Définit la valeur de idNatureContratAutre.
     * @param idNatureContratAutre la nouvelle valeur de idNatureContratAutre
     */
    public void setIdNatureContratAutre(Long idNatureContratAutre) {
        this.idNatureContratAutre = idNatureContratAutre;
    }

    /**
     * Récupère la valeur de idOrigineDecompteAlmerys.
     * @return la valeur de idOrigineDecompteAlmerys
     */
    public Long getIdOrigineDecompteAlmerys() {
        return idOrigineDecompteAlmerys;
    }

    /**
     * Définit la valeur de idOrigineDecompteAlmerys.
     * @param idOrigineDecompteAlmerys la nouvelle valeur de idOrigineDecompteAlmerys
     */
    public void setIdOrigineDecompteAlmerys(Long idOrigineDecompteAlmerys) {
        this.idOrigineDecompteAlmerys = idOrigineDecompteAlmerys;
    }

    /**
     * Récupère la valeur de idRoleGarantieConjoint.
     * @return la valeur de idRoleGarantieConjoint
     */
    public Long getIdRoleGarantieConjoint() {
        return idRoleGarantieConjoint;
    }

    /**
     * Définit la valeur de idRoleGarantieConjoint.
     * @param idRoleGarantieConjoint la nouvelle valeur de idRoleGarantieConjoint
     */
    public void setIdRoleGarantieConjoint(Long idRoleGarantieConjoint) {
        this.idRoleGarantieConjoint = idRoleGarantieConjoint;
    }

    /**
     * Récupère la valeur de idRoleGarantieConcubin.
     * @return la valeur de idRoleGarantieConcubin
     */
    public Long getIdRoleGarantieConcubin() {
        return idRoleGarantieConcubin;
    }

    /**
     * Définit la valeur de idRoleGarantieConcubin.
     * @param idRoleGarantieConcubin la nouvelle valeur de idRoleGarantieConcubin
     */
    public void setIdRoleGarantieConcubin(Long idRoleGarantieConcubin) {
        this.idRoleGarantieConcubin = idRoleGarantieConcubin;
    }

    /**
     * Récupère la valeur de idFamilleGarantieAssistance.
     * @return la valeur de idFamilleGarantieAssistance
     */
    public Long getIdFamilleGarantieAssistance() {
        return idFamilleGarantieAssistance;
    }

    /**
     * Définit la valeur de idFamilleGarantieAssistance.
     * @param idFamilleGarantieAssistance la nouvelle valeur de idFamilleGarantieAssistance
     */
    public void setIdFamilleGarantieAssistance(Long idFamilleGarantieAssistance) {
        this.idFamilleGarantieAssistance = idFamilleGarantieAssistance;
    }

    /**
     * Récupère la valeur de idFamilleGarantieExoneration.
     * @return la valeur de idFamilleGarantieExoneration
     */
    public Long getIdFamilleGarantieExoneration() {
        return idFamilleGarantieExoneration;
    }

    /**
     * Définit la valeur de idFamilleGarantieExoneration.
     * @param idFamilleGarantieExoneration la nouvelle valeur de idFamilleGarantieExoneration
     */
    public void setIdFamilleGarantieExoneration(Long idFamilleGarantieExoneration) {
        this.idFamilleGarantieExoneration = idFamilleGarantieExoneration;
    }

    /**
     * Récupère la valeur de idFamilleGarantieSante.
     * @return la valeur de idFamilleGarantieSante
     */
    public Long getIdFamilleGarantieSante() {
        return idFamilleGarantieSante;
    }

    /**
     * Définit la valeur de idFamilleGarantieSante.
     * @param idFamilleGarantieSante la nouvelle valeur de idFamilleGarantieSante
     */
    public void setIdFamilleGarantieSante(Long idFamilleGarantieSante) {
        this.idFamilleGarantieSante = idFamilleGarantieSante;
    }

    @Override
    public Long getIdSegmentCollectifIndividualise() {
        return idSegmentCollectifIndividualise;
    }

    /**
     * Définit la valeur de idSegmentCollectifIndividualise.
     * @param idSegmentCollectifIndividualise la nouvelle valeur de idSegmentCollectifIndividualise
     */
    public void setIdSegmentCollectifIndividualise(Long idSegmentCollectifIndividualise) {
        this.idSegmentCollectifIndividualise = idSegmentCollectifIndividualise;
    }

    /**
     * Récupère la valeur de idStatutGarantieResiliee.
     * @return la valeur de idStatutGarantieResiliee
     */
    public Long getIdStatutGarantieResiliee() {
        return idStatutGarantieResiliee;
    }

    /**
     * Définit la valeur de idStatutGarantieResiliee.
     * @param idStatutGarantieResiliee la nouvelle valeur de idStatutGarantieResiliee
     */
    public void setIdStatutGarantieResiliee(Long idStatutGarantieResiliee) {
        this.idStatutGarantieResiliee = idStatutGarantieResiliee;
    }

    /**
     * Récupère la valeur de idStatutGarantieFutur.
     * @return la valeur de idStatutGarantieFutur
     */
    public Long getIdStatutGarantieFutur() {
        return idStatutGarantieFutur;
    }

    /**
     * Définit la valeur de idStatutGarantieFutur.
     * @param idStatutGarantieFutur la nouvelle valeur de idStatutGarantieFutur
     */
    public void setIdStatutGarantieFutur(Long idStatutGarantieFutur) {
        this.idStatutGarantieFutur = idStatutGarantieFutur;
    }

    /**
     * Récupère la valeur de idMotifResiliationSansEffet.
     * @return la valeur de idMotifResiliationSansEffet
     */
    public Long getIdMotifResiliationSansEffet() {
        return idMotifResiliationSansEffet;
    }

    /**
     * Définit la valeur de idMotifResiliationSansEffet.
     * @param idMotifResiliationSansEffet la nouvelle valeur de idMotifResiliationSansEffet
     */
    public void setIdMotifResiliationSansEffet(Long idMotifResiliationSansEffet) {
        this.idMotifResiliationSansEffet = idMotifResiliationSansEffet;
    }

    /**
     * Récupère la valeur de idMotifResiliationErreurDeSaisie.
     * @return la valeur de idMotifResiliationErreurDeSaisie
     */
    public Long getIdMotifResiliationErreurDeSaisie() {
        return idMotifResiliationErreurDeSaisie;
    }

    /**
     * Définit la valeur de idMotifResiliationErreurDeSaisie.
     * @param idMotifResiliationErreurDeSaisie la nouvelle valeur de idMotifResiliationErreurDeSaisie
     */
    public void setIdMotifResiliationErreurDeSaisie(Long idMotifResiliationErreurDeSaisie) {
        this.idMotifResiliationErreurDeSaisie = idMotifResiliationErreurDeSaisie;
    }

    /**
     * Récupère la valeur de idMoyenPaiementCheque.
     * @return la valeur de idMoyenPaiementCheque
     */
    public Long getIdMoyenPaiementCheque() {
        return idMoyenPaiementCheque;
    }

    /**
     * Définit la valeur de idMoyenPaiementCheque.
     * @param idMoyenPaiementCheque la nouvelle valeur de idMoyenPaiementCheque
     */
    public void setIdMoyenPaiementCheque(Long idMoyenPaiementCheque) {
        this.idMoyenPaiementCheque = idMoyenPaiementCheque;
    }

    /**
     * Récupère la valeur de idMoyenPaiementVirement.
     * @return la valeur de idMoyenPaiementVirement
     */
    public Long getIdMoyenPaiementVirement() {
        return idMoyenPaiementVirement;
    }

    /**
     * Définit la valeur de idMoyenPaiementVirement.
     * @param idMoyenPaiementVirement la nouvelle valeur de idMoyenPaiementVirement
     */
    public void setIdMoyenPaiementVirement(Long idMoyenPaiementVirement) {
        this.idMoyenPaiementVirement = idMoyenPaiementVirement;
    }

    /**
     * Récupère la valeur de idMoyenPaiementVirementEncaissement.
     * @return la valeur de idMoyenPaiementVirementEncaissement
     */
    public Long getIdMoyenPaiementVirementEncaissement() {
        return idMoyenPaiementVirementEncaissement;
    }

    /**
     * Définit la valeur de idMoyenPaiementVirementEncaissement.
     * @param idMoyenPaiementVirementEncaissement la nouvelle valeur de idMoyenPaiementVirementEncaissement
     */
    public void setIdMoyenPaiementVirementEncaissement(Long idMoyenPaiementVirementEncaissement) {
        this.idMoyenPaiementVirementEncaissement = idMoyenPaiementVirementEncaissement;
    }

    /**
     * Récupère la valeur de idMoyenPaiementEspece.
     * @return la valeur de idMoyenPaiementEspece
     */
    public Long getIdMoyenPaiementEspece() {
        return idMoyenPaiementEspece;
    }

    /**
     * Définit la valeur de idMoyenPaiementEspece.
     * @param idMoyenPaiementEspece la nouvelle valeur de idMoyenPaiementEspece
     */
    public void setIdMoyenPaiementEspece(Long idMoyenPaiementEspece) {
        this.idMoyenPaiementEspece = idMoyenPaiementEspece;
    }

    /**
     * Récupère la valeur de idMoyenPaiementMandat.
     * @return la valeur de idMoyenPaiementMandat
     */
    public Long getIdMoyenPaiementMandat() {
        return idMoyenPaiementMandat;
    }

    /**
     * Définit la valeur de idMoyenPaiementMandat.
     * @param idMoyenPaiementMandat la nouvelle valeur de idMoyenPaiementMandat
     */
    public void setIdMoyenPaiementMandat(Long idMoyenPaiementMandat) {
        this.idMoyenPaiementMandat = idMoyenPaiementMandat;
    }

    /**
     * Getter function.
     * @return the idActeNonRemboursable
     */
    public Long getIdActeNonRemboursable() {
        return idActeNonRemboursable;
    }

    /**
     * Setter function.
     * @param idActeNonRemboursable the idActeNonRemboursable to set
     */
    public void setIdActeNonRemboursable(Long idActeNonRemboursable) {
        this.idActeNonRemboursable = idActeNonRemboursable;
    }

    /**
	 * Récupère le jour de paiement par défaut.
	 * @return the jourPaiementPrelevementDefault
	 */
	public int getJourPaiementPrelevementCinq() {
		return jourPaiementPrelevementCinq;
	}

	/**
	 * Définit le jour de paiement par défaut.
	 * @param jourPaiementPrelevementCinq the jourPaiementPrelevementDefault to set
	 */
	public void setJourPaiementPrelevementCinq(int jourPaiementPrelevementCinq) {
		this.jourPaiementPrelevementCinq = jourPaiementPrelevementCinq;
	}

	/**
	 * Recupère l'id de la fréquence de paiement par défaut.
	 * @return the idFrequencePrelevementDefault
	 */
	public Long getIdFrequencePrelevementMensuelle() {
		return idFrequencePrelevementMensuelle;
	}

	/**
	 * Définit l'id de la fréquence de paiement par défaut.
	 * @param idFrequencePrelevementMensuelle the idFrequencePrelevementDefault to set
	 */
	public void setIdFrequencePrelevementMensuelle(Long idFrequencePrelevementMensuelle) {
		this.idFrequencePrelevementMensuelle = idFrequencePrelevementMensuelle;
	}

	/**
	 * Définit le moyen de paiement prélèvement.
	 * @param idMoyenPaiementPrelevement the idMoyenPaiementPrelevementDefault to set
	 */
	public void setIdMoyenPaiementPrelevement(Long idMoyenPaiementPrelevement) {
		this.idMoyenPaiementPrelevement = idMoyenPaiementPrelevement;
	}

	/**
	 * Récupère le moyen de paiement prélèvement.
	 * @return idMoyenPaiementPrelevement
	 */
	public Long getIdMoyenPaiementPrelevement() {
		return idMoyenPaiementPrelevement;
	}

    /**
     * Getter function.
     * @return the orderDecompteByDateReglement
     */
    public String getOrderDecompteByDateReglement() {
        return orderDecompteByDateReglement;
    }

    /**
     * Setter function.
     * @param orderDecompteByDateReglement the orderDecompteByDateReglement to set
     */
    public void setOrderDecompteByDateReglement(String orderDecompteByDateReglement) {
        this.orderDecompteByDateReglement = orderDecompteByDateReglement;
    }

    /**
     * Get the nombreJoursDifferesEnvoiMail value.
     * @return the nombreJoursDifferesEnvoiMail
     */
    public int getNombreJoursDifferesEnvoiMail() {
        return nombreJoursDifferesEnvoiMail;
    }

    /**
     * Set the nombreJoursDifferesEnvoiMail value.
     * @param nombreJoursDifferesEnvoiMail the nombreJoursDifferesEnvoiMail to set
     */
    public void setNombreJoursDifferesEnvoiMail(int nombreJoursDifferesEnvoiMail) {
        this.nombreJoursDifferesEnvoiMail = nombreJoursDifferesEnvoiMail;
    }

    @Override
    public List<Long> getListeOriginesDecomptesExcluesEnvoiSms() {
        return listeOriginesDecomptesExcluesEnvoiSms;
    }

    /**
     * Définition de listeOriginesDecomptesExcluesEnvoiSms.
     * @param listeOriginesDecomptesExcluesEnvoiSms the listeOriginesDecomptesExcluesEnvoiSms to set
     */
    public void setListeOriginesDecomptesExcluesEnvoiSms(List<Long> listeOriginesDecomptesExcluesEnvoiSms) {
        this.listeOriginesDecomptesExcluesEnvoiSms = listeOriginesDecomptesExcluesEnvoiSms;
    }

    /**
     * Récupère la valeur de idTypePayeurMultipart.
     * @return la valeur de idTypePayeurMultipart
     */
    public Long getIdTypePayeurMultipart() {
        return idTypePayeurMultipart;
    }

    /**
     * Définit la valeur de idTypePayeurMultipart.
     * @param idTypePayeurMultipart la nouvelle valeur de idTypePayeurMultipart
     */
    public void setIdTypePayeurMultipart(Long idTypePayeurMultipart) {
        this.idTypePayeurMultipart = idTypePayeurMultipart;
    }

    /**
     * Récupère la valeur de idTypePayeurAssure.
     * @return la valeur de idTypePayeurAssure
     */
    public Long getIdTypePayeurAssure() {
        return idTypePayeurAssure;
    }

    /**
     * Définit la valeur de idTypePayeurAssure.
     * @param idTypePayeurAssure la nouvelle valeur de idTypePayeurAssure
     */
    public void setIdTypePayeurAssure(Long idTypePayeurAssure) {
        this.idTypePayeurAssure = idTypePayeurAssure;
    }

    @Override
    public Integer getOrdrePopulation(String libellePopulation) {
        if (mapOrdrePopulation.containsKey(libellePopulation)) {
            return mapOrdrePopulation.get(libellePopulation);
        }
        else {
            return null;
        }
    }

    /**
     * Définit la valeur de mapOrdrePopulation.
     * @param mapOrdrePopulation la nouvelle valeur de mapOrdrePopulation
     */
    public void setMapOrdrePopulation(Map<String, Integer> mapOrdrePopulation) {
        this.mapOrdrePopulation = mapOrdrePopulation;
    }

    /**
     * Récupère la valeur de idStatutContratFutur.
     * @return la valeur de idStatutContratFutur
     */
    public Long getIdStatutContratFutur() {
        return idStatutContratFutur;
    }

    /**
     * Définit la valeur de idStatutContratFutur.
     * @param idStatutContratFutur la nouvelle valeur de idStatutContratFutur
     */
    public void setIdStatutContratFutur(Long idStatutContratFutur) {
        this.idStatutContratFutur = idStatutContratFutur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdStatutGarantieSansEffet() {
        return idStatutGarantieSansEffet;
    }

    /**
     * Setter function.
     * @param idStatutGarantieSansEffet the idStatutGarantieSansEffet to set
     */
    public void setIdStatutGarantieSansEffet(Long idStatutGarantieSansEffet) {
        this.idStatutGarantieSansEffet = idStatutGarantieSansEffet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNiveauImportanceStatutGarantie(Long idStatutGarantie) {
        return mapImportanceStatutsGaranties.get(idStatutGarantie);
    }

    /**
     * Setter function.
     * @param mapImportanceStatutsGaranties the mapImportanceStatutsGaranties to set
     */
    public void setMapImportanceStatutsGaranties(Map<Long, Integer> mapImportanceStatutsGaranties) {
        this.mapImportanceStatutsGaranties = mapImportanceStatutsGaranties;
    }

    /**
     * Get the orderDecompteById value.
     * @return the orderDecompteById
     */
    public String getOrderDecompteById() {
        return orderDecompteById;
    }

    /**
     * Set the orderDecompteById value.
     * @param orderDecompteById the orderDecompteById to set
     */
    public void setOrderDecompteById(String orderDecompteById) {
        this.orderDecompteById = orderDecompteById;
    }

    /**
     * Get the orderDecompteByNumero value.
     * @return the orderDecompteByNumero
     */
    public String getOrderDecompteByNumero() {
        return orderDecompteByNumero;
    }

    /**
     * Set the orderDecompteByNumero value.
     * @param orderDecompteByNumero the orderDecompteByNumero to set
     */
    public void setOrderDecompteByNumero(String orderDecompteByNumero) {
        this.orderDecompteByNumero = orderDecompteByNumero;
    }

    /**
     * Récupère la valeur de orderDecompteByDateSoin.
     * @return la valeur de orderDecompteByDateSoin
     */
    public String getOrderDecompteByDateSoin() {
        return orderDecompteByDateSoin;
    }

    /**
     * Définit la valeur de orderDecompteByDateSoin.
     * @param orderDecompteByDateSoin la nouvelle valeur de orderDecompteByDateSoin
     */
    public void setOrderDecompteByDateSoin(String orderDecompteByDateSoin) {
        this.orderDecompteByDateSoin = orderDecompteByDateSoin;
    }

    /**
     * Récupère la valeur de orderDecompteByBeneficiaire.
     * @return la valeur de orderDecompteByBeneficiaire
     */
    public String getOrderDecompteByBeneficiaire() {
        return orderDecompteByBeneficiaire;
    }

    /**
     * Définit la valeur de orderDecompteByBeneficiaire.
     * @param orderDecompteByBeneficiaire la nouvelle valeur de orderDecompteByBeneficiaire
     */
    public void setOrderDecompteByBeneficiaire(String orderDecompteByBeneficiaire) {
        this.orderDecompteByBeneficiaire = orderDecompteByBeneficiaire;
    }

    /**
     * Récupère la valeur de orderDecompteByActe.
     * @return la valeur de orderDecompteByActe
     */
    public String getOrderDecompteByActe() {
        return orderDecompteByActe;
    }

    /**
     * Définit la valeur de orderDecompteByActe.
     * @param orderDecompteByActe la nouvelle valeur de orderDecompteByActe
     */
    public void setOrderDecompteByActe(String orderDecompteByActe) {
        this.orderDecompteByActe = orderDecompteByActe;
    }

    /**
     * Récupère la valeur de orderDecompteByDepense.
     * @return la valeur de orderDecompteByDepense
     */
    public String getOrderDecompteByDepense() {
        return orderDecompteByDepense;
    }

    /**
     * Définit la valeur de orderDecompteByDepense.
     * @param orderDecompteByDepense la nouvelle valeur de orderDecompteByDepense
     */
    public void setOrderDecompteByDepense(String orderDecompteByDepense) {
        this.orderDecompteByDepense = orderDecompteByDepense;
    }

    /**
     * Récupère la valeur de orderDecompteByBase.
     * @return la valeur de orderDecompteByBase
     */
    public String getOrderDecompteByBase() {
        return orderDecompteByBase;
    }

    /**
     * Définit la valeur de orderDecompteByBase.
     * @param orderDecompteByBase la nouvelle valeur de orderDecompteByBase
     */
    public void setOrderDecompteByBase(String orderDecompteByBase) {
        this.orderDecompteByBase = orderDecompteByBase;
    }

    /**
     * Récupère la valeur de orderDecompteByTaux.
     * @return la valeur de orderDecompteByTaux
     */
    public String getOrderDecompteByTaux() {
        return orderDecompteByTaux;
    }

    /**
     * Définit la valeur de orderDecompteByTaux.
     * @param orderDecompteByTaux la nouvelle valeur de orderDecompteByTaux
     */
    public void setOrderDecompteByTaux(String orderDecompteByTaux) {
        this.orderDecompteByTaux = orderDecompteByTaux;
    }

    /**
     * Récupère la valeur de orderDecompteByRbTRO.
     * @return la valeur de orderDecompteByRbTRO
     */
    public String getOrderDecompteByRbtRO() {
        return orderDecompteByRbtRO;
    }

    /**
     * Définit la valeur de orderDecompteByRbtRO.
     * @param orderDecompteByRbtRO la nouvelle valeur de orderDecompteByRbtRO
     */
    public void setOrderDecompteByRbtRO(String orderDecompteByRbtRO) {
        this.orderDecompteByRbtRO = orderDecompteByRbtRO;
    }

    /**
     * Récupère la valeur de orderDecompteByRbtSmatis.
     * @return la valeur de orderDecompteByRbtSmatis
     */
    public String getOrderDecompteByRbtSmatis() {
        return orderDecompteByRbtSmatis;
    }

    /**
     * Définit la valeur de orderDecompteByRbtSmatis.
     * @param orderDecompteByRbtSmatis la nouvelle valeur de orderDecompteByRbtSmatis
     */
    public void setOrderDecompteByRbtSmatis(String orderDecompteByRbtSmatis) {
        this.orderDecompteByRbtSmatis = orderDecompteByRbtSmatis;
    }

    /**
     * Récupère la valeur de orderDecompteByRbtProf.
     * @return la valeur de orderDecompteByRbtProf
     */
    public String getOrderDecompteByRbtProf() {
        return orderDecompteByRbtProf;
    }

    /**
     * Définit la valeur de orderDecompteByRbtProf.
     * @param orderDecompteByRbtProf la nouvelle valeur de orderDecompteByRbtProf
     */
    public void setOrderDecompteByRbtProf(String orderDecompteByRbtProf) {
        this.orderDecompteByRbtProf = orderDecompteByRbtProf;
    }

    /**
     * Récupère la valeur de orderDecompteByRaC.
     * @return la valeur de orderDecompteByRaC
     */
    public String getOrderDecompteByRaC() {
        return orderDecompteByRaC;
    }

    /**
     * Définit la valeur de orderDecompteByRaC.
     * @param orderDecompteByRaC la nouvelle valeur de orderDecompteByRaC
     */
    public void setOrderDecompteByRaC(String orderDecompteByRaC) {
        this.orderDecompteByRaC = orderDecompteByRaC;
    }

    /**
     * Get the idOrigineDecompteIndu value.
     * @return the idOrigineDecompteIndu
     */
    public Long getIdOrigineDecompteIndu() {
        return idOrigineDecompteIndu;
    }

    /**
     * Set the idOrigineDecompteIndu value.
     * @param idOrigineDecompteIndu the idOrigineDecompteIndu to set
     */
    public void setIdOrigineDecompteIndu(Long idOrigineDecompteIndu) {
        this.idOrigineDecompteIndu = idOrigineDecompteIndu;
    }

    /**
     * Get the orderDecompteByNumeroCouplage value.
     * @return the orderDecompteByNumeroCouplage
     */
    public String getOrderDecompteByNumeroCouplage() {
        return orderDecompteByNumeroCouplage;
    }

    /**
     * Set the orderDecompteByNumeroCouplage value.
     * @param orderDecompteByNumeroCouplage the orderDecompteByNumeroCouplage to set
     */
    public void setOrderDecompteByNumeroCouplage(String orderDecompteByNumeroCouplage) {
        this.orderDecompteByNumeroCouplage = orderDecompteByNumeroCouplage;
    }

    /**
     * Get the idStatutPaiementDecomptePaye value.
     * @return the idStatutPaiementDecomptePaye
     */
    public String getIdStatutPaiementDecomptePaye() {
        return idStatutPaiementDecomptePaye;
    }

    /**
     * Set the idStatutPaiementDecomptePaye value.
     * @param idStatutPaiementDecomptePaye the idStatutPaiementDecomptePaye to set
     */
    public void setIdStatutPaiementDecomptePaye(String idStatutPaiementDecomptePaye) {
        this.idStatutPaiementDecomptePaye = idStatutPaiementDecomptePaye;
    }

    /**
     * Get the idStatutPaiementDecompteNonPaye value.
     * @return the idStatutPaiementDecompteNonPaye
     */
    public String getIdStatutPaiementDecompteNonPaye() {
        return idStatutPaiementDecompteNonPaye;
    }

    /**
     * Set the idStatutPaiementDecompteNonPaye value.
     * @param idStatutPaiementDecompteNonPaye the idStatutPaiementDecompteNonPaye to set
     */
    public void setIdStatutPaiementDecompteNonPaye(String idStatutPaiementDecompteNonPaye) {
        this.idStatutPaiementDecompteNonPaye = idStatutPaiementDecompteNonPaye;
    }

    /**
     * Get the idNatureConnexionEspaceClient value.
     * @return the idNatureConnexionEspaceClient
     */
    public Long getIdNatureConnexionEspaceClient() {
        return idNatureConnexionEspaceClient;
    }

    /**
     * Set the idNatureConnexionEspaceClient value.
     * @param idNatureConnexionEspaceClient the idNatureConnexionEspaceClient to set
     */
    public void setIdNatureConnexionEspaceClient(Long idNatureConnexionEspaceClient) {
        this.idNatureConnexionEspaceClient = idNatureConnexionEspaceClient;
    }

    /**
     * Get the orderCotisationDateDebut value.
     * @return the orderCotisationDateDebut
     */
    public String getOrderCotisationDateDebut() {
        return orderCotisationDateDebut;
    }

    /**
     * Set the orderCotisationDateDebut value.
     * @param orderCotisationDateDebut the orderCotisationDateDebut to set
     */
    public void setOrderCotisationDateDebut(String orderCotisationDateDebut) {
        this.orderCotisationDateDebut = orderCotisationDateDebut;
    }

    /**
     * Get the orderCotisationMontant value.
     * @return the orderCotisationMontant
     */
    public String getOrderCotisationMontant() {
        return orderCotisationMontant;
    }

    /**
     * Set the orderCotisationMontant value.
     * @param orderCotisationMontant the orderCotisationMontant to set
     */
    public void setOrderCotisationMontant(String orderCotisationMontant) {
        this.orderCotisationMontant = orderCotisationMontant;
    }

    /**
     * Get the orderCotisationMontantRegle value.
     * @return the orderCotisationMontantRegle
     */
    public String getOrderCotisationMontantRegle() {
        return orderCotisationMontantRegle;
    }

    /**
     * Set the orderCotisationMontantRegle value.
     * @param orderCotisationMontantRegle the orderCotisationMontantRegle to set
     */
    public void setOrderCotisationMontantRegle(String orderCotisationMontantRegle) {
        this.orderCotisationMontantRegle = orderCotisationMontantRegle;
    }

    /**
     * Get the orderCotisationSituation value.
     * @return the orderCotisationSituation
     */
    public String getOrderCotisationSituation() {
        return orderCotisationSituation;
    }

    /**
     * Set the orderCotisationSituation value.
     * @param orderCotisationSituation the orderCotisationSituation to set
     */
    public void setOrderCotisationSituation(String orderCotisationSituation) {
        this.orderCotisationSituation = orderCotisationSituation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrderDecompteByDateFinSoin() {
        return orderDecompteByDateFinSoin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrderDecomptesByNomBeneficiaireSoins() {
        return orderDecomptesByNomBeneficiaireSoins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrderDecompteByNomDestinataireReglement() {
        return orderDecompteByNomDestinataireReglement;
    }

    /**
     * Setter function.
     * @param orderDecompteByDateFinSoin the orderDecompteByDateFinSoin to set
     */
    public void setOrderDecompteByDateFinSoin(String orderDecompteByDateFinSoin) {
        this.orderDecompteByDateFinSoin = orderDecompteByDateFinSoin;
    }

    /**
     * Setter function.
     * @param ordreDecomptesByNomBeneficiaireSoins the orderDecomptesByNomBeneficiaireSoins to set
     */
    public void setOrderDecompteByNomBeneficiaireSoins(String ordreDecomptesByNomBeneficiaireSoins) {
        this.orderDecomptesByNomBeneficiaireSoins = ordreDecomptesByNomBeneficiaireSoins;
    }

    /**
     * Setter function.
     * @param orderDecompteByNomDestinataireReglement the orderDecompteByNomDestinataireReglement to set
     */
    public void setOrderDecompteByNomDestinataireReglement(String orderDecompteByNomDestinataireReglement) {
        this.orderDecompteByNomDestinataireReglement = orderDecompteByNomDestinataireReglement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrderDecomptesByDateDebutSoin() {
        return orderDecomptesByDateDebutSoin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrderDecomptesByDateFinSoin() {
        return orderDecomptesByDateFinSoin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrderDecomptesByRbtSmatis() {
        return orderDecomptesByRbtSmatis;
    }

    /**
     * Setter function.
     * @param orderDecomptesByDateDebutSoin the orderDecomptesByDateDebutSoin to set
     */
    public void setOrderDecomptesByDateDebutSoin(String orderDecomptesByDateDebutSoin) {
        this.orderDecomptesByDateDebutSoin = orderDecomptesByDateDebutSoin;
    }

    /**
     * Setter function.
     * @param orderDecomptesByDateFinSoin the orderDecomptesByDateFinSoin to set
     */
    public void setOrderDecomptesByDateFinSoin(String orderDecomptesByDateFinSoin) {
        this.orderDecomptesByDateFinSoin = orderDecomptesByDateFinSoin;
    }

    /**
     * Setter function.
     * @param orderDecomptesByRbtSmatis the orderDecomptesByRbtSmatis to set
     */
    public void setOrderDecomptesByRbtSmatis(String orderDecomptesByRbtSmatis) {
        this.orderDecomptesByRbtSmatis = orderDecomptesByRbtSmatis;
    }

    /**
     * Setter function.
     * @param orderDecomptesByNomBeneficiaireSoins the orderDecomptesByNomBeneficiaireSoins to set
     */
    public void setOrderDecomptesByNomBeneficiaireSoins(String orderDecomptesByNomBeneficiaireSoins) {
        this.orderDecomptesByNomBeneficiaireSoins = orderDecomptesByNomBeneficiaireSoins;
    }

    @Override
    public Long getIdTypeRelationConjoint() {
        return idTypeRelationConjoint;
    }

    @Override
    public Long getIdTypeRelationEnfant() {
        return idTypeRelationEnfant;
    }

    /**
     * Récupère la valeur de idNatureReglementTiersSante.
     * @return la valeur de idNatureReglementTiersSante
     */
    public String getIdNatureReglementTiersSante() {
        return idNatureReglementTiersSante;
    }

    /**
     * Définit la valeur de idNatureReglementTiersSante.
     * @param idNatureReglementTiersSante la nouvelle valeur de idNatureReglementTiersSante
     */
    public void setIdNatureReglementTiersSante(String idNatureReglementTiersSante) {
        this.idNatureReglementTiersSante = idNatureReglementTiersSante;
    }

    /**
     * Set the value of idTypeRelationConjoint.
     * @param idTypeRelationConjoint the idTypeRelationConjoint to set
     */
    public void setIdTypeRelationConjoint(Long idTypeRelationConjoint) {
        this.idTypeRelationConjoint = idTypeRelationConjoint;
    }

    /**
     * Set the value of idTypeRelationEnfant.
     * @param idTypeRelationEnfant the idTypeRelationEnfant to set
     */
    public void setIdTypeRelationEnfant(Long idTypeRelationEnfant) {
        this.idTypeRelationEnfant = idTypeRelationEnfant;
    }

    /**
     * Récupérer l'identifiant du contrat de santé.
     * @return idContratSante
     */
	public Long getIdContratSante() {
		return idContratSante;
	}

	/**
	 * Définit l'identifiant du contrat de santé.
	 * @param idContratSante l'id du contrat de santé
	 */
	public void setIdContratSante(Long idContratSante) {
		this.idContratSante = idContratSante;
	}

}
