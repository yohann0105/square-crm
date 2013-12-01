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
package com.square.adherent.noyau.service.interfaces;

import java.util.List;

/**
 * Interface des services de mapping des adhérents.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface AdherentMappingService {

    /**
     * Récupère l'identifiant du statut "En cours" d'un contrat.
     * @return l'identifiant
     */
    Long getIdStatutContratEnCours();

    /**
     * Récupère l'identifiant du statut "Résilié" d'un contrat.
     * @return l'identifiant
     */
    Long getIdStatutContratResilie();

    /**
     * Récupère l'identifiant du statut "Futur" d'un contrat.
     * @return l'identifiant
     */
    Long getIdStatutContratFutur();

    /**
     * Récupère l'identifiant du segment "Collectif".
     * @return l'identifiant
     */
    Long getIdSegmentCollectif();

    /**
     * Récupère l'identifiant du segment "Collectif Individualisé".
     * @return l'identifiant
     */
    Long getIdSegmentCollectifIndividualise();

    /**
     * Récupère l'identifiant du type payeur "Souscripteur".
     * @return l'identifiant
     */
    Long getIdTypePayeurSouscripteur();

    /**
     * Récupère l'identifiant du type payeur "Multipart".
     * @return l'identifiant
     */
    Long getIdTypePayeurMultipart();

    /**
     * Récupère l'identifiant du type payeur "Assure".
     * @return l'identifiant
     */
    Long getIdTypePayeurAssure();

    /**
     * Getter function.
     * @return the idTypeOptionEnvoiRelevesPrestationEmail
     */
    Long getIdTypeOptionEnvoiRelevesPrestationEmail();

    /**
     * Getter function.
     * @return the idTypeOptionEnvoiMutuellementEmail
     */
    Long getIdTypeOptionEnvoiMutuellementEmail();

    /**
     * Getter function.
     * @return the idTypeOptionEnvoiSms
     */
    Long getIdTypeOptionEnvoiSms();

    /**
     * Récupère l'identifiant de la nature de contrat "Santé".
     * @return l'identifiant
     */
    Long getIdNatureContratSante();

    /**
     * Récupère l'identifiant de la nature de contrat "Prévoyance".
     * @return l'identifiant
     */
    Long getIdNatureContratPrevoyance();

    /**
     * Récupère l'identifiant de la nature de contrat "Autre".
     * @return l'identifiant
     */
    Long getIdNatureContratAutre();

    /**
     * Récupère l'identifiant du statut de garantie "En cours".
     * @return l'identifiant
     */
    Long getIdStatutGarantieEnCours();

    /**
     * Récupère l'identifiant du statut de garantie "Résiliée".
     * @return l'identifiant
     */
    Long getIdStatutGarantieResiliee();

    /**
     * Récupère l'identifiant du statut de garantie "Futur".
     * @return l'identifiant
     */
    Long getIdStatutGarantieFutur();

    /**
     * Récupère l'identifiant du statut de garantie "Sans Effet".
     * @return l'identifiant
     */
    Long getIdStatutGarantieSansEffet();

    /**
     * Récupère l'identifiant de la famille de garantie "Santé".
     * @return l'identifiant
     */
    Long getIdFamilleGarantieSante();

    /**
     * Récupère l'identifiant de la famille de garantie "Bonus".
     * @return l'identifiant
     */
    Long getIdFamilleGarantieBonus();

    /**
     * Récupère l'identifiant de la famille de garantie "Assistance".
     * @return l'identifiant
     */
    Long getIdFamilleGarantieAssistance();

    /**
     * Récupère l'identifiant de la famille de garantie "Assistance".
     * @return l'identifiant
     */
    Long getIdFamilleGarantieExoneration();

    /**
     * Récupère l'identifiant du rôle "Assuré" d'une garantie.
     * @return l'identifiant
     */
    Long getIdRoleGarantieAssure();

    /**
     * Récupère l'identifiant du rôle "Conjoint" d'une garantie.
     * @return l'identifiant
     */
    Long getIdRoleGarantieConjoint();

    /**
     * Récupère l'identifiant du rôle "Concubin" d'une garantie.
     * @return l'identifiant
     */
    Long getIdRoleGarantieConcubin();

    /**
     * Récupère l'identifiant de la nature "Espace client" d'une connexion.
     * @return l'identifiant
     */
    Long getIdNatureConnexionEspaceClient();

    /**
     * Récupère l'identifiant de l'origine de décompte "Almerys".
     * @return l'identifiant
     */
    Long getIdOrigineDecompteAlmerys();

    /**
     * Récupère l'identifiant de l'origine de décompte "Indu".
     * @return l'identifiant
     */
    Long getIdOrigineDecompteIndu();

    /**
     * Récupère l'identifiant du motif de résiliation sans effet.
     * @return l'identifiant
     */
    Long getIdMotifResiliationSansEffet();

    /**
     * Récupère l'identifiant du motif de résiliation erreur de saisie.
     * @return l'identifiant
     */
    Long getIdMotifResiliationErreurDeSaisie();

    /**
     * Récupère l'identifiant du moyen de paiement par chèque.
     * @return l'identifiant
     */
    Long getIdMoyenPaiementCheque();

    /**
     * Récupère l'identifiant du moyen de paiement par virement.
     * @return l'identifiant
     */
    Long getIdMoyenPaiementVirement();

    /**
     * Récupère l'identifiant du moyen de paiement par virement encaissement.
     * @return l'identifiant
     */
    Long getIdMoyenPaiementVirementEncaissement();

    /**
     * Récupère l'identifiant du moyen de paiement par espèce.
     * @return l'identifiant
     */
    Long getIdMoyenPaiementEspece();

    /**
     * Récupère l'identifiant du moyen de paiement par mandat.
     * @return l'identifiant
     */
    Long getIdMoyenPaiementMandat();

    /**
     * Récupère l'identifiant de l'acte non remboursable.
     * @return l'identifiant
     */
    Long getIdActeNonRemboursable();

    /**
     * Récupère l'identifiant du contrat de santé.
     * @return l'identifiant
     */
    Long getIdContratSante();

    /**
     * Récupère la constante pour trier les décompte par l'id.
     * @return the orderDecompteById
     */
    String getOrderDecompteById();

    /**
     * Récupère la constante pour trier les décompte par le numéro.
     * @return the orderDecompteByNumero
     */
    String getOrderDecompteByNumero();

    /**
     * Récupère la constante pour trier les décompte par le numéro de couplage.
     * @return the orderDecompteByNumero
     */
    String getOrderDecompteByNumeroCouplage();

    /**
     * Récupère la constante pour trier les décompte par la date de règlement.
     * @return the orderDecompteByDateReglement
     */
    String getOrderDecompteByDateReglement();

    /**
     * Récupère la constante pour trier les décomptes par le nom du destinataire de règlement.
     * @return la colonne utilisée pour le tri
     */
    String getOrderDecompteByNomDestinataireReglement();

    /**
     * Définit le différé (en jours) entre la date actuelle et la date d'impression des relevés pour l'envoi par mail.
     * @return le nombre de jours.
     */
    int getNombreJoursDifferesEnvoiMail();

    /**
     * Récupère la liste des origines de remboursement exclues de l'envoi par SMS.
     * @return .
     */
    List<Long> getListeOriginesDecomptesExcluesEnvoiSms();

    /**
     * Récupère l'ordre d'affichage d'une population pour l'affichage.
     * @param libellePopulation le libellé de la population.
     * @return l'ordre d'apparition de la population.
     */
    Integer getOrdrePopulation(String libellePopulation);

    /**
     * Récupère le niveau d'importance du statut de garantie passé en paramètre.
     * @param idStatutGarantie identifiant du statut de garantie
     * @return le niveau d'importance du statut de garantie
     */
    Integer getNiveauImportanceStatutGarantie(Long idStatutGarantie);

    /**
     * Récupère la propriété de tri sur la date de soin.
     * @return la propriété.
     */
    String getOrderDecompteByDateSoin();

    /**
     * Récupère la propriété de tri sur la date de fin de soin.
     * @return la propriété.
     */
    String getOrderDecompteByDateFinSoin();

    /**
     * Récupère la propriété de tri par la date de début de soin des décomptes regroupés.
     * @return la propriété.
     */
    String getOrderDecomptesByDateDebutSoin();

    /**
     * Récupère la propriété de tri par la date de fin de soin des décomptes regroupés.
     * @return la propriété.
     */
    String getOrderDecomptesByDateFinSoin();

    /**
     * Récupère la propriété de tri par le nom du bénéficiaire des soins des décomptes regroupés.
     * @return la propriété.
     */
    String getOrderDecomptesByNomBeneficiaireSoins();

    /**
     * Récupère la propriété de tri par le montant total remboursé des décomptes regroupés.
     * @return la propriété.
     */
    String getOrderDecomptesByRbtSmatis();

    /**
     * Récupère la propriété de tri sur le bénéficiaire.
     * @return la propriété.
     */
    String getOrderDecompteByBeneficiaire();

    /**
     * Récupère la propriété de tri sur l'acte.
     * @return la propriété.
     */
    String getOrderDecompteByActe();

    /**
     * Récupère la propriété de tri sur la dépense.
     * @return la propriété.
     */
    String getOrderDecompteByDepense();

    /**
     * Récupère la propriété de tri sur la base.
     * @return la propriété.
     */
    String getOrderDecompteByBase();

    /**
     * Récupère la propriété de tri sur le taux.
     * @return la propriété.
     */
    String getOrderDecompteByTaux();

    /**
     * Récupère la propriété de tri sur le remboursement de sécu.
     * @return la propriété.
     */
    String getOrderDecompteByRbtRO();

    /**
     * Récupère la propriété de tri sur le remboursement de la Smatis.
     * @return la propriété.
     */
    String getOrderDecompteByRbtSmatis();

    /**
     * Récupère la propriété de tri sur le remboursement professionnel.
     * @return la propriété.
     */
    String getOrderDecompteByRbtProf();

    /**
     * Récupère la propriété de tri sur le reste à charge.
     * @return la propriété.
     */
    String getOrderDecompteByRaC();

    /**
     * Recupere le statut de paiement de decompte payé.
     * @return la propriété.
     */
    String getIdStatutPaiementDecomptePaye();

    /**
     * Recupere le statut de paiement de decompte non payé.
     * @return la propriété.
     */
    String getIdStatutPaiementDecompteNonPaye();

    /**
     * Recupere l'ordre de tri de cotisation sur date debut.
     * @return la propriété.
     */
    String getOrderCotisationDateDebut();

    /**
     * Recupere l'ordre de tri de cotisation sur montant.
     * @return la propriété.
     */
    String getOrderCotisationMontant();

    /**
     * Recupere l'ordre de tri de cotisation sur montant regle.
     * @return la propriété.
     */
    String getOrderCotisationMontantRegle();

    /**
     * Recupere l'ordre de tri de cotisation sur situation.
     * @return la propriété.
     */
    String getOrderCotisationSituation();

    /**
     * Récupère l'identifiant de la nature de règlement "Tiers Payant".
     * @return l'identifiant.
     */
    String getIdNatureReglementTiersSante();

    /**
     * Récupère l'identifiant de la relation conjoint.
     * @return l'identifiant de la relation conjoint.
     */
    Long getIdTypeRelationConjoint();

    /**
     * Récupère l'identifiant de la relation enfant.
     * @return l'identifiant de la relation enfant.
     */
    Long getIdTypeRelationEnfant();

    /**
	 * Récupère le jour de paiement par défaut.
	 * @return the jourPaiementPrelevementCinq
	 */
	int getJourPaiementPrelevementCinq();

	/**
	 * Recupère l'id de la fréquence de paiement par défaut.
	 * @return the idFrequencePrelevementMensuelle
	 */
	Long getIdFrequencePrelevementMensuelle();

	/**
     * Récupère l'identifiant du moyen de paiement par virement.
     * @return l'identifiant
     */
    Long getIdMoyenPaiementPrelevement();
}
