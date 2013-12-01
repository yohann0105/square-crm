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
package com.square.tarificateur.noyau.service.interfaces;

import java.util.List;

/**
 * Service de mapping.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface TarificateurSquareMappingService {

    /**
     * Getter function.
     * @return the idFinaliteNonRenseignee
     */
    Long getIdFinaliteNonRenseignee();

    /**
     * Getter function.
     * @return the idFinaliteAcceptee
     */
    Long getIdFinaliteAcceptee();

    /**
     * Getter function.
     * @return the idFinaliteRefusee
     */
    Long getIdFinaliteRefusee();

    /**
     * Getter function.
     * @return the idFinaliteCorbeille
     */
    Long getIdFinaliteCorbeille();

    /**
     * Getter function.
     * @return the idFinaliteTransformee
     */
    Long getIdFinaliteTransformee();

    /**
     * Récupère l'identifiant de la finalité en cours.
     * @return l'identifiant de la finalité en cours.
     */
    Long getIdFinaliteEnCours();

    /**
     * Getter function.
     * @return the idMoyenPaiementPrelevement
     */
    Long getIdMoyenPaiementPrelevement();

    /**
     * Getter function.
     * @return the idMoyenPaiementCarteBancaire
     */
    Long getIdMoyenPaiementCarteBancaire();

    /**
     * Getter function.
     * @return the idMoyenPaiementCheque
     */
    Long getIdMoyenPaiementCheque();

    /**
     * Getter function.
     * @return the idMoyenPaiementEspeces
     */
    Long getIdMoyenPaiementEspeces();

    /**
     * Getter function.
     * @return the idMoyenPaiementMandat
     */
    Long getIdMoyenPaiementMandat();

    /**
     * Getter function.
     * @return the idMoyenPaiementVirement
     */
    Long getIdMoyenPaiementVirement();

    /**
     * Getter function.
     * @return the idMoyenPaiementVirementEncaissement
     */
    Long getIdMoyenPaiementVirementEncaissement();

    /**
     * Getter function.
     * @return the typeSantePrevoyance
     */
    String getTypeSantePrevoyance();

    /**
     * Getter function.
     * @return the typeNeutre
     */
    String getTypeNeutre();

    /**
     * Getter function.
     * @return the nbAnneesBonus1
     */
    Integer getNbAnneesBonus1();

    /**
     * Getter function.
     * @return the nbAnneesBonus2
     */
    Integer getNbAnneesBonus2();

    /**
     * Recuperer un code compo famille par composition famille gestcom.
     * @param idLienFamilial identifiant lien familial gestcom.
     * @return le code compo null si aucune occurence.
     */
    String getCodeCompoParCompoFam(final Long idLienFamilial);

    /**
     * Permet de recuperer un code role en fonction d'un code composition famille Gestcom.
     * @param compoFamille la composition famille.
     * @return le code role ou null si aucun role pour la composition famille.
     */
    String getCodeRoleParCompoFam(final String compoFamille);

    /**
     * Retourne l'id du lien familial d'un assure principal.
     * @return l'id du lien familial
     */
    Long getIdLienFamilialAssurePrincipal();

    /**
     * Retourne l'id du lien familial d'un conjoint.
     * @return l'id du lien familial
     */
    Long getIdLienFamilialConjoint();

    /**
     * Retourne l'id du lien familial d'un enfant.
     * @return l'id du lien familial
     */
    Long getIdLienFamilialEnfant();

    /**
     * Retourne la liste des propriétés d'info santé à comparer.
     * @return la liste des propriétés d'info santé à comparer
     */
    List<String> getListeProprietesInfoSanteAComparer();

    /**
     * Retourne la liste des propriétés de personnes à comparer.
     * @return la liste des propriétés de personnes à comparer
     */
    List<String> getListeProprietesPersonneAComparer();

    /**
     * Retourne la liste des propriétés d'adresses à comparer.
     * @return la liste des propriétés d'adresses à comparer
     */
    List<String> getListeProprietesAdresseAComparer();

    /**
     * Getter function.
     * @return the idPeriodicitePaiementMensuelle
     */
    Long getIdPeriodicitePaiementMensuelle();

    /**
     * Getter function.
     * @return the idPeriodicitePaiementTrimestrielle
     */
    Long getIdPeriodicitePaiementTrimestrielle();

    /**
     * Getter function.
     * @return the idPeriodicitePaiementSemestrielle
     */
    Long getIdPeriodicitePaiementSemestrielle();

    /**
     * Getter function.
     * @return the idPeriodicitePaiementAnnuelle
     */
    Long getIdPeriodicitePaiementAnnuelle();

    /**
     * Getter function.
     * @return the idJourPaiement5DuMois
     */
    Long getIdJourPaiement5DuMois();

    /**
     * Getter function.
     * @return the idJourPaiement10DuMois
     */
    Long getIdJourPaiement10DuMois();

    /**
     * Getter function.
     * @return the idJourPaiement15DuMois
     */
    Long getIdJourPaiement15DuMois();

    /**
     * Récupère l'identifiant du modèle de devis BA.
     * @return l'identifiant.
     */
    Long getIdModeleDevisBulletinAdhesion();

    /**
     * Récupère l'identifiant du modèle de devis de fiche de transfert.
     * @return l'identifiant.
     */
    Long getIdModeleDevisFicheTransfert();

    /**
     * Récupère l'identifiant du modèle de devis Lettre d'annulation.
     * @return l'identifiant.
     */
    Long getIdModeleLettreAnnulation();

    /**
     * Récupère l'identifiant du modèle de devis Lettre de radiation.
     * @return l'identifiant.
     */
    Long getIdModeleLettreRadiation();

    /**
     * Récupère l'identifiant du modèle de devis Lettre de radiation par loi Chatel.
     * @return l'identifiant.
     */
    Long getIdModeleLettreRadiationLoiChatel();

    /**
     * Récupère l'identifiant de la source de ligne de devis "Consultation de tarifs".
     * @return l'identifiant de la source de ligne de devis "Consultation de tarifs"
     */
    Long getIdSourceLigneDevisConsultationTarifs();

    /**
     * Récupère l'identifiant de la source de ligne de devis "Demande de dossier".
     * @return l'identifiant de la source de ligne de devis "Demande de dossier"
     */
    Long getIdSourceLigneDevisDemandeDossier();

    /**
     * Récupère l'identifiant de la source de ligne de devis "Adhésion".
     * @return l'identifiant de la source de ligne de devis "Adhésion"
     */
    Long getIdSourceLigneDevisAdhesion();

    /**
     * Récupère l'identifiant de la source de ligne de devis "Non renseignée".
     * @return l'identifiant de la source de ligne de devis "Non renseignée"
     */
    Long getIdSourceLigneDevisNonRenseignee();

    /**
     * Recuperer la valeur.
     * @return the constanteIdMotifNonRenseigne
     */
    Long getConstanteIdMotifNonRenseigne();

    /**
     * Recuperer la valeur.
     * @return the constanteIdMotifSpontanee
     */
    Long getConstanteIdMotifSpontanee();

    /**
     * Recuperer la valeur.
     * @return the constanteIdMotifVide
     */
    Long getConstanteIdMotifVide();

    /**
     * Recuperer la valeur.
     * @return the constanteIdMotifAutre
     */
    Long getConstanteIdMotifAutre();

    /**
     * Recuperer la valeur.
     * @return the idMotifDevisStandard
     */
    Long getIdMotifDevisStandard();

    /**
     * Identifiant du motif de devis "parrainage".
     * @return identifiant
     */
    Long getIdMotifDevisParrainage();

    /**
     * Identifiant du motif de devis "parrainage bénéficiaire".
     * @return identifiant
     */
    Long getIdMotifDevisParrainageBeneficiaire();

    /**
     * Identifiant du motif de devis "parrainage entreprise".
     * @return identifiant
     */
    Long getIdMotifDevisParrainageEntreprise();

    /**
     * Accesseur pour l'attribut constanteAdhesionAgeMiniDelaiStage.
     * @return l'attribut constanteAdhesionAgeMiniDelaiStage
     */
    Integer getConstanteAdhesionAgeMiniDelaiStage();
}
