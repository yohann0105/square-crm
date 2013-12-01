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
package com.square.fusion.api.service.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.adherent.noyau.service.interfaces.AdherentService;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.InfoSanteDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.fusion.api.dto.AdresseFusionDto;
import com.square.fusion.api.dto.EmailFusionDto;
import com.square.fusion.api.dto.ParametresFusionDto;
import com.square.fusion.api.dto.PersonneCibleFusionDto;
import com.square.fusion.api.dto.PersonneSourceFusionDto;
import com.square.fusion.api.dto.ProprieteFusionnableAdresseDto;
import com.square.fusion.api.dto.ProprieteFusionnableBooleanDto;
import com.square.fusion.api.dto.ProprieteFusionnableCalendarDto;
import com.square.fusion.api.dto.ProprieteFusionnableEmailDto;
import com.square.fusion.api.dto.ProprieteFusionnableIdLibelleDto;
import com.square.fusion.api.dto.ProprieteFusionnableStringDto;
import com.square.fusion.api.dto.ProprieteFusionnableTelephoneDto;
import com.square.fusion.api.dto.TelephoneFusionDto;
import com.square.fusion.api.service.interfaces.FusionPersonneService;
import com.square.fusion.api.util.MessageKeyUtil;
import com.square.synchro.app.noyau.dto.DefaultMessageSynchroAppDto;
import com.square.synchro.app.noyau.dto.SquareGedFusionDocumentSynchroDto;
import com.square.synchro.app.noyau.services.interfaces.SynchroAppJmsSender;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurService;

/**
 * Implémentation de FusionPersonneService.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class FusionPersonneServiceImpl implements FusionPersonneService {

    private static final String ESPACE = " ";

    /** Service PersonnePhysique Square. */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Service Personne Square. */
    private PersonneService personneService;

    /** Service Opportunité Square. */
    private OpportuniteService opportuniteService;

    /** Service Actions Square. */
    private ActionService actionService;

    /** Service de mapping Square. */
    private SquareMappingService squareMappingService;

    /** Service Adhérent (ECM Square). */
    private AdherentService adherentService;

    /** Service Personne (Tarificateur Square). */
    private TarificateurPersonneService tarificateurPersonneService;

    /** Service Tarificateur Square. */
    private TarificateurService tarificateurService;

    /** MessageSourceUtil. */
    private MessageSourceUtil messageFusionApiSourceUtil;

    /** Service pour poster dans la file JMS. */
    private SynchroAppJmsSender synchroAppJmsSender;

    /** Logger. */
    private Logger logger = Logger.getLogger(FusionPersonneServiceImpl.class);

    @Override
    public ParametresFusionDto preparerFusion(Long idPersonne1, Long idPersonne2) {
        // Récupération des personnes dans Square
        PersonneDto personne1 = null;
        try {
            personne1 = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne1);
        }
        catch (BusinessException e) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
           		 new String[] {String.valueOf(idPersonne1)}));
            throw new TechnicalException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_INEXISTANTE));
        }
        if (personne1 == null) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
              		 new String[] {String.valueOf(idPersonne1)}));
            throw new TechnicalException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_INEXISTANTE));
        }
        PersonneDto personne2 = null;
        try {
            personne2 = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne2);
        }
        catch (BusinessException e) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
              		 new String[] {String.valueOf(idPersonne2)}));
            throw new TechnicalException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_INEXISTANTE));
        }
        if (personne2 == null) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
              		 new String[] {String.valueOf(idPersonne2)}));
            throw new TechnicalException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_INEXISTANTE));
        }

        // Vérification de l'association à un contrat pour chaque personne
        final boolean personne1AssocieeAContrat = adherentService.isPersonneAssocieeAContrat(idPersonne1);
        final boolean personne2AssocieeAContrat = adherentService.isPersonneAssocieeAContrat(idPersonne2);
        final List<Long> listeIdsStatuts = new ArrayList<Long>();
        listeIdsStatuts.add(squareMappingService.getIdStatutOpportuniteTransforme());
        final boolean personne1PossedeOppTransformee = opportuniteService.hasPersonneOpportuniteByStatuts(idPersonne1, listeIdsStatuts);
        final boolean personne2PossedeOppTransformee = opportuniteService.hasPersonneOpportuniteByStatuts(idPersonne2, listeIdsStatuts);

        // Vérification si les personnes sont bénéféciaires sur des opp transformées
        final boolean personne1IsBeneficiaireSurOppTransformee = tarificateurService.personneIsBeneficiaireSurOppTransforme(idPersonne1);
        final boolean personne2IsBeneficiaireSurOppTransformee = tarificateurService.personneIsBeneficiaireSurOppTransforme(idPersonne2);

        // Exception si les 2 personnes possèdent des contrats
        if (personne1AssocieeAContrat && personne2AssocieeAContrat) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_RATTACHEES_CONTRATS));
            throw new BusinessException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_RATTACHEES_CONTRATS));
        }
        // Exception si les 2 personnes possèdent des opportunités transformées
        if (personne1PossedeOppTransformee && personne2PossedeOppTransformee) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_OPPS_TRANSFORMEES));
            throw new BusinessException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_OPPS_TRANSFORMEES));
        }
        // Exception si les 2 personnes sont bénéficiaires sur des opportunités transformées
        if (personne1IsBeneficiaireSurOppTransformee && personne2IsBeneficiaireSurOppTransformee) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_BENEFICIAIRES_SUR_OPPS_TRANSFORMEES));
            throw new BusinessException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_BENEFICIAIRES_SUR_OPPS_TRANSFORMEES));
        }
        // Exception si une des personnes possède des contrats et que l'autre possède au moins une opportunité transformée
        if ((personne1AssocieeAContrat && personne2PossedeOppTransformee) || (personne2AssocieeAContrat && personne1PossedeOppTransformee)) {
            logger.error(messageFusionApiSourceUtil.get(
            		MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_RATTACHEES_CONTRATS_OPPS_TRANSFORMEES));
            throw new BusinessException(messageFusionApiSourceUtil.get(
            		MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_RATTACHEES_CONTRATS_OPPS_TRANSFORMEES));
        }
        // Exception si une des personnes possède des contrats et que l'autre est bénéficiaire sur une opportunité transformée
        if ((personne1AssocieeAContrat && personne2IsBeneficiaireSurOppTransformee)
        		|| (personne2AssocieeAContrat && personne1IsBeneficiaireSurOppTransformee)) {
            logger.error(messageFusionApiSourceUtil
                    .get(MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_RATTACHEES_CONTRATS_BENEFICIAIRES_SUR_OPPS_TRANSFORMEES));
            throw new BusinessException(messageFusionApiSourceUtil
                    .get(MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_RATTACHEES_CONTRATS_BENEFICIAIRES_SUR_OPPS_TRANSFORMEES));
        }
        // Exception si une des personnes possède des contrats et que l'autre est bénéficiaire sur une opportunité transformée
        if ((personne1PossedeOppTransformee && personne2IsBeneficiaireSurOppTransformee)
            || (personne2PossedeOppTransformee && personne1IsBeneficiaireSurOppTransformee)) {
            logger.error(messageFusionApiSourceUtil.get(
                MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_OPP_TRANSFORMEE_BENEFICIAIRES_SUR_OPPS_TRANSFORMEES));
            throw new BusinessException(messageFusionApiSourceUtil.get(
                MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_OPP_TRANSFORMEE_BENEFICIAIRES_SUR_OPPS_TRANSFORMEES));
        }

        // Choix de la personne source et de la personne cible : la personne source est celle qui n'est pas associée à un contrat
        PersonneDto personneSource = null;
        PersonneDto personneCible = null;
        boolean personneSourceAssocieeAContrat = false;
        boolean personneCibleAssocieeContrat = false;
        boolean personneSourcePossedeOppTransformee = false;
        boolean personneCiblePossedeOppTransformee = false;
        if (personne1AssocieeAContrat || personne1PossedeOppTransformee) {
            personneSource = personne2;
            personneCible = personne1;
            personneSourceAssocieeAContrat = personne2AssocieeAContrat;
            personneCibleAssocieeContrat = personne1AssocieeAContrat;
            personneSourcePossedeOppTransformee = personne2PossedeOppTransformee;
            personneCiblePossedeOppTransformee = personne1PossedeOppTransformee;
        }
        else {
            personneSource = personne1;
            personneCible = personne2;
            personneSourceAssocieeAContrat = personne1AssocieeAContrat;
            personneCibleAssocieeContrat = personne2AssocieeAContrat;
            personneSourcePossedeOppTransformee = personne1PossedeOppTransformee;
            personneCiblePossedeOppTransformee = personne2PossedeOppTransformee;
        }
        logger.debug(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_INFOS_PERSONNE_CIBLE_SOURCE,
          		 new String[] {String.valueOf(personneSource.getIdentifiant()), String.valueOf(personneCible.getIdentifiant())}));

        // Création des DTO PersonneFusion
        final PersonneSourceFusionDto personneSourceFusion = new PersonneSourceFusionDto();
        personneSourceFusion.setIdentifiant(personneSource.getIdentifiant());
        personneSourceFusion.setNumeroClient(personneSource.getNumClient());
        personneSourceFusion.setDateCreation(personneSource.getDateCreation());
        personneSourceFusion.setPossedeContrat(personneSourceAssocieeAContrat);
        personneSourceFusion.setPossedeOppTransformee(personneSourcePossedeOppTransformee);
        final PersonneCibleFusionDto personneCibleFusion = new PersonneCibleFusionDto();
        personneCibleFusion.setIdentifiant(personneCible.getIdentifiant());
        personneCibleFusion.setNumeroClient(personneCible.getNumClient());
        personneCibleFusion.setDateCreation(personneCible.getDateCreation());
        personneCibleFusion.setPossedeContrat(personneCibleAssocieeContrat);
        personneCibleFusion.setPossedeOppTransformee(personneCiblePossedeOppTransformee);

        // Récupération des propriétés et calcul des différences
        recupererEtComparerProprietesPersonnes(personneSource, personneSourceFusion, personneCible, personneCibleFusion);

        // Création du DTO de retour
        final ParametresFusionDto parametresFusionDto = new ParametresFusionDto();
        parametresFusionDto.setPersonneSource(personneSourceFusion);
        parametresFusionDto.setPersonneCible(personneCibleFusion);
        return parametresFusionDto;
    }

    @Override
    public void validerFusion(ParametresFusionDto parametresFusion, String utilisateur) {
        final Long idPersonneSource = parametresFusion.getPersonneSource().getIdentifiant();
        final Long idPersonneCible = parametresFusion.getPersonneCible().getIdentifiant();

        logger.debug(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_VALIDATION_FUSION,
         		 new String[] {String.valueOf(idPersonneSource), String.valueOf(idPersonneCible)}));

        // Récupération de la personne source à fusionner
        final PersonneSourceFusionDto personneAFusionner = parametresFusion.getPersonneSource();

        // Récupération des personnes dans Square
        PersonneDto personneSource = null;
        try {
            personneSource = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonneSource);
        }
        catch (BusinessException e) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
              		 new String[] {String.valueOf(idPersonneSource)}));
            throw new TechnicalException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_SOURCE_INEXISTANTE));
        }
        if (personneSource == null) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
              		 new String[] {String.valueOf(idPersonneSource)}));
            throw new TechnicalException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_SOURCE_INEXISTANTE));
        }
        PersonneDto personneCible = null;
        try {
            personneCible = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonneCible);
        }
        catch (BusinessException e) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
              		 new String[] {String.valueOf(idPersonneCible)}));
            throw new TechnicalException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_CIBLE_INEXISTANTE));
        }
        if (personneCible == null) {
            logger.error(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
              		 new String[] {String.valueOf(idPersonneCible)}));
            throw new TechnicalException(messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_CIBLE_INEXISTANTE));
        }

        // Fusion des champs de la personne source vers la personne cible
        fusionnerPersonneSourceDansPersonneCible(personneAFusionner, personneSource, personneCible);

        // Transfert des relations
        personneService.transfererRelations(idPersonneSource, idPersonneCible);

        // Transfert des opportunités
        opportuniteService.transfererOpportunites(idPersonneSource, idPersonneCible);

        // Transfert des actions
        actionService.transfererActionsPersonne(idPersonneSource, idPersonneCible);

        // Transfert des documents (envoi dans la file d'attente)
        final SquareGedFusionDocumentSynchroDto message = new SquareGedFusionDocumentSynchroDto();
        message.setNumClientPersonneSource(parametresFusion.getPersonneSource().getNumeroClient());
        message.setNumClientPersonneCible(parametresFusion.getPersonneCible().getNumeroClient());
        message.setLoginUtilisateur(utilisateur);
        message.setMsgSynchroOrigineHeader(DefaultMessageSynchroAppDto.MSOH_SQUARE_GED_FUSION_DOCUMENT);
        synchroAppJmsSender.envoyerMessageSynchro(message);
        logger.info(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_INFOS_TRANSFERT_DOCUMENTS,
        		 new String[] {String.valueOf(idPersonneSource), String.valueOf(idPersonneCible)}));

        // Transfert des EID de personne dans le Tarificateur Square
        tarificateurPersonneService.transfererEidPersonne(idPersonneSource, idPersonneCible);

        // Suppression (logique) de la personne source
        personneService.supprimerPersonne(idPersonneSource);
    }

    /**
     * Récupère et compare les propriétés des personnes source et cible en remplissant les infos de différence.
     * @param personneSource la personne source à comparer
     * @param personneSourceFusion l'objet pour remplir les infos des propriétés de la personne source
     * @param personneCible la personne cible à comparer
     * @param personneCibleFusion l'objet pour remplir les infos des propriétés de la personne cible
     */
    private void recupererEtComparerProprietesPersonnes(PersonneDto personneSource, PersonneSourceFusionDto personneSourceFusion, PersonneDto personneCible,
        PersonneCibleFusionDto personneCibleFusion) {
        if (personneSource.getInfoSante() == null) {
            personneSource.setInfoSante(new InfoSanteDto());
        }
        if (personneCible.getInfoSante() == null) {
            personneCible.setInfoSante(new InfoSanteDto());
        }
        // NRO
        final boolean isNroDifferent = isValeursDifferentes(personneSource.getInfoSante().getNro(), personneCible.getInfoSante().getNro());
        personneSourceFusion.setNro(new ProprieteFusionnableStringDto(personneSource.getInfoSante().getNro(), isNroDifferent));
        personneCibleFusion.setNro(personneCible.getInfoSante().getNro());
        // Civilité
        final boolean isCiviliteDifferent = isValeursDifferentes(personneSource.getCivilite(), personneCible.getCivilite());
        personneSourceFusion.setCivilite(new ProprieteFusionnableIdLibelleDto(personneSource.getCivilite(), isCiviliteDifferent));
        personneCibleFusion.setCivilite(personneCible.getCivilite());
        // Prénom
        final boolean isPrenomDifferent = isValeursDifferentes(personneSource.getPrenom(), personneCible.getPrenom());
        personneSourceFusion.setPrenom(new ProprieteFusionnableStringDto(personneSource.getPrenom(), isPrenomDifferent));
        personneCibleFusion.setPrenom(personneCible.getPrenom());
        // Nom
        final boolean isNomDifferent = isValeursDifferentes(personneSource.getNom(), personneCible.getNom());
        personneSourceFusion.setNom(new ProprieteFusionnableStringDto(personneSource.getNom(), isNomDifferent));
        personneCibleFusion.setNom(personneCible.getNom());
        // Nom de jeune fille
        final boolean isNomJeuneFilleDifferent = isValeursDifferentes(personneSource.getNomJeuneFille(), personneCible.getNomJeuneFille());
        personneSourceFusion.setNomJeuneFille(new ProprieteFusionnableStringDto(personneSource.getNomJeuneFille(), isNomJeuneFilleDifferent));
        personneCibleFusion.setNomJeuneFille(personneCible.getNomJeuneFille());
        // Date de naissance
        final boolean isDateNaissanceDifferent = isValeursDifferentes(personneSource.getDateNaissance(), personneCible.getDateNaissance());
        personneSourceFusion.setDateNaissance(new ProprieteFusionnableCalendarDto(personneSource.getDateNaissance(), isDateNaissanceDifferent));
        personneCibleFusion.setDateNaissance(personneCible.getDateNaissance());
        // Date de décès
        final boolean isDateDecesDifferent = isValeursDifferentes(personneSource.getDateDeces(), personneCible.getDateDeces());
        personneSourceFusion.setDateDeces(new ProprieteFusionnableCalendarDto(personneSource.getDateDeces(), isDateDecesDifferent));
        personneCibleFusion.setDateDeces(personneCible.getDateDeces());
        // Nature
        final boolean isNatureDifferent = isValeursDifferentes(personneSource.getNaturePersonne(), personneCible.getNaturePersonne());
        final ProprieteFusionnableIdLibelleDto propNature = new ProprieteFusionnableIdLibelleDto(personneSource.getNaturePersonne(), isNatureDifferent);
        final Long idNatureAdherent = squareMappingService.getIdNaturePersonneAdherent();
        if ((personneSource != null && personneSource.getNaturePersonne() != null && idNatureAdherent.equals(personneSource.getNaturePersonne()
                .getIdentifiant()))
            || (personneCible != null && personneCible.getNaturePersonne() != null && idNatureAdherent.equals(personneCible.getNaturePersonne()
                    .getIdentifiant()))) {
            propNature.setIsSelectionnable(Boolean.FALSE);
        }
        personneSourceFusion.setNaturePersonne(propNature);
        personneCibleFusion.setNaturePersonne(personneCible.getNaturePersonne());
        // Flag décédé
        final boolean isDecedeDifferent = isValeursDifferentes(personneSource.isDecede(), personneCible.isDecede());
        personneSourceFusion.setDecede(new ProprieteFusionnableBooleanDto(personneSource.isDecede(), isDecedeDifferent));
        personneCibleFusion.setDecede(personneCible.isDecede());
        // Caisse
        IdentifiantLibelleDto caissePersonneSource = null;
        IdentifiantLibelleDto caissePersonneCible = null;
        if (personneSource.getInfoSante().getCaisse() != null) {
            caissePersonneSource =
                new IdentifiantLibelleDto(personneSource.getInfoSante().getCaisse().getId(), personneSource.getInfoSante().getCaisse().getNom());
        }
        if (personneCible.getInfoSante().getCaisse() != null) {
            caissePersonneCible =
                new IdentifiantLibelleDto(personneCible.getInfoSante().getCaisse().getId(), personneCible.getInfoSante().getCaisse().getNom());
        }
        final boolean isCaisseDifferent = isValeursDifferentes(caissePersonneSource, caissePersonneCible);
        personneSourceFusion.setCaisse(new ProprieteFusionnableIdLibelleDto(caissePersonneSource, isCaisseDifferent));
        personneCibleFusion.setCaisse(caissePersonneCible);
        // Régime
        final boolean isCaisseRegimeDifferent =
            isValeursDifferentes(personneSource.getInfoSante().getCaisseRegime(), personneCible.getInfoSante().getCaisseRegime());
        personneSourceFusion.setCaisseRegime(new ProprieteFusionnableIdLibelleDto(personneSource.getInfoSante().getCaisseRegime(), isCaisseRegimeDifferent));
        personneCibleFusion.setCaisseRegime(personneCible.getInfoSante().getCaisseRegime());
        // Segment
        final boolean isSegmentDifferent = isValeursDifferentes(personneSource.getSegment(), personneCible.getSegment());
        personneSourceFusion.setSegment(new ProprieteFusionnableIdLibelleDto(personneSource.getSegment(), isSegmentDifferent));
        personneCibleFusion.setSegment(personneCible.getSegment());
        // Réseau de vente
        final boolean isReseauVenteDifferent = isValeursDifferentes(personneSource.getReseauVente(), personneCible.getReseauVente());
        personneSourceFusion.setReseauVente(new ProprieteFusionnableIdLibelleDto(personneSource.getReseauVente(), isReseauVenteDifferent));
        personneCibleFusion.setReseauVente(personneCible.getReseauVente());
        // CSP
        final boolean isCspDifferent = isValeursDifferentes(personneSource.getCsp(), personneCible.getCsp());
        personneSourceFusion.setCsp(new ProprieteFusionnableIdLibelleDto(personneSource.getCsp(), isCspDifferent));
        personneCibleFusion.setCsp(personneCible.getCsp());
        // Situation familiale
        final boolean isSitFamDifferent = isValeursDifferentes(personneSource.getSitFam(), personneCible.getSitFam());
        personneSourceFusion.setSitFam(new ProprieteFusionnableIdLibelleDto(personneSource.getSitFam(), isSitFamDifferent));
        personneCibleFusion.setSitFam(personneCible.getSitFam());
        // Profession
        final boolean isProfessionDifferent = isValeursDifferentes(personneSource.getProfession(), personneCible.getProfession());
        personneSourceFusion.setProfession(new ProprieteFusionnableIdLibelleDto(personneSource.getProfession(), isProfessionDifferent));
        personneCibleFusion.setProfession(personneCible.getProfession());
        // Statut
        final boolean isStatutDifferent = isValeursDifferentes(personneSource.getStatut(), personneCible.getStatut());
        personneSourceFusion.setStatut(new ProprieteFusionnableIdLibelleDto(personneSource.getStatut(), isStatutDifferent));
        personneCibleFusion.setStatut(personneCible.getStatut());
        // Commercial
        final boolean isCommercialDifferent = isValeursDifferentes(personneSource.getCommercial(), personneCible.getCommercial());
        final IdentifiantLibelleDto commercialPersonneSource = getIdentifiantLibelleByDimensionResource(personneSource.getCommercial());
        final IdentifiantLibelleDto commercialPersonneCible = getIdentifiantLibelleByDimensionResource(personneCible.getCommercial());
        personneSourceFusion.setCommercial(new ProprieteFusionnableIdLibelleDto(commercialPersonneSource, isCommercialDifferent));
        personneCibleFusion.setCommercial(commercialPersonneCible);
        // Agence
        final boolean isAgenceDifferent = isValeursDifferentes(personneSource.getAgence(), personneCible.getAgence());
        personneSourceFusion.setAgence(new ProprieteFusionnableIdLibelleDto(personneSource.getAgence(), isAgenceDifferent));
        personneCibleFusion.setAgence(personneCible.getAgence());

        // Récupération des coordonnées de la personne source
        final CoordonneesDto coordonneesPersonneSource = personneService.rechercherCoordonneesParIdPersonne(personneSource.getIdentifiant());
        // Récupération des coordonnées de la personne cible
        final CoordonneesDto coordonneesPersonneCible = personneService.rechercherCoordonneesParIdPersonne(personneCible.getIdentifiant());

        // Adresse principale
        final Long idNatureAdressePrincipale = squareMappingService.getIdNatureAdressePrincipale();
        final AdresseDto adressePrincPersonneSource = rechercherAdressePrincipaleEnCours(coordonneesPersonneSource.getAdresses(), idNatureAdressePrincipale);
        final AdresseDto adressePrincPersonneCible = rechercherAdressePrincipaleEnCours(coordonneesPersonneCible.getAdresses(), idNatureAdressePrincipale);
        final boolean isAdressePrincipaleDifferent = isAdressesDifferentes(adressePrincPersonneSource, adressePrincPersonneCible);
        final AdresseFusionDto adressePrincPersonneSourceFusion = getAdresseFusionByAdresse(adressePrincPersonneSource);
        final AdresseFusionDto adressePrincPersonneCibleFusion = getAdresseFusionByAdresse(adressePrincPersonneCible);
        personneSourceFusion.setAdressePrincipale(new ProprieteFusionnableAdresseDto(adressePrincPersonneSourceFusion, isAdressePrincipaleDifferent));
        personneCibleFusion.setAdressePrincipale(adressePrincPersonneCibleFusion);

        // Liste des téléphones
        recupererEtComparerTelephonesPersonnes(coordonneesPersonneSource.getTelephones(), personneSourceFusion, coordonneesPersonneCible.getTelephones(),
            personneCibleFusion);

        // Liste des mails
        recupererEtComparerEmailsPersonnes(coordonneesPersonneSource.getEmails(), personneSourceFusion, coordonneesPersonneCible.getEmails(),
            personneCibleFusion);
    }

    /**
     * Teste la différence entre des valeurs de type String.
     * @param valeur1 la valeur 1
     * @param valeur2 la valeur 2
     * @return true si les valeurs sont différentes, false sinon
     */
    private boolean isValeursDifferentes(String valeur1, String valeur2) {
        if (valeur1 == null && valeur2 == null) {
            return false;
        }
        else if ((valeur1 == null && valeur2 != null) || (valeur1 != null && valeur2 == null)) {
            return true;
        }
        else {
            return !valeur1.trim().equals(valeur2.trim());
        }
    }

    /**
     * Teste la différence entre des valeurs de type Calendar.
     * @param valeur1 la valeur 1
     * @param valeur2 la valeur 2
     * @return true si les valeurs sont différentes, false sinon
     */
    private boolean isValeursDifferentes(Calendar valeur1, Calendar valeur2) {
        if (valeur1 == null && valeur2 == null) {
            return false;
        }
        else if ((valeur1 == null && valeur2 != null) || (valeur1 != null && valeur2 == null)) {
            return true;
        }
        else {
            return !valeur1.equals(valeur2);
        }
    }

    /**
     * Teste la différence entre des valeurs de type Boolean.
     * @param valeur1 la valeur 1
     * @param valeur2 la valeur 2
     * @return true si les valeurs sont différentes, false sinon
     */
    @SuppressWarnings("unused")
	private boolean isValeursDifferentes(Boolean valeur1, Boolean valeur2) {
        if (valeur1 == null && valeur2 == null) {
            return false;
        }
        else if ((valeur1 == null && valeur2 != null) || (valeur1 != null && valeur2 == null)) {
            return true;
        }
        else {
            return !valeur1.equals(valeur2);
        }
    }

    /**
     * Teste la différence entre des valeurs de type IdentifiantLibelleDto.
     * @param valeur1 la valeur 1
     * @param valeur2 la valeur 2
     * @return true si les valeurs sont différentes, false sinon
     */
    private boolean isValeursDifferentes(IdentifiantLibelleDto valeur1, IdentifiantLibelleDto valeur2) {
        if (valeur1 == null && valeur2 == null) {
            return false;
        }
        else if (valeur1 == null && valeur2 != null) {
            return valeur2.getIdentifiant() != null;
        }
        else if (valeur1 != null && valeur2 == null) {
            return valeur1.getIdentifiant() != null;
        }
        else if (valeur1.getIdentifiant() == null && valeur2.getIdentifiant() == null) {
            return false;
        }
        else if (valeur1.getIdentifiant() != null && valeur2.getIdentifiant() != null) {
            return !valeur1.getIdentifiant().equals(valeur2.getIdentifiant());
        }
        else {
            return true;
        }
    }

    /**
     * Teste la différence entre des valeurs de type boolean.
     * @param valeur1 la valeur 1
     * @param valeur2 la valeur 2
     * @return true si les valeurs sont différentes, false sinon
     */
    private boolean isValeursDifferentes(boolean valeur1, boolean valeur2) {
        return valeur1 != valeur2;
    }

    /**
     * Teste la différence entre des valeurs de type Long.
     * @param valeur1 la valeur 1
     * @param valeur2 la valeur 2
     * @return true si les valeurs sont différentes, false sinon
     */
    private boolean isValeursDifferentes(DimensionRessourceDto valeur1, DimensionRessourceDto valeur2) {
        if (valeur1 == null && valeur2 == null) {
            return false;
        }
        else if (valeur1 == null && valeur2 != null) {
            return valeur2.getIdentifiant() != null;
        }
        else if (valeur1 != null && valeur2 == null) {
            return valeur1.getIdentifiant() != null;
        }
        else if (valeur1.getIdentifiant() == null && valeur2.getIdentifiant() == null) {
            return false;
        }
        else if (valeur1.getIdentifiant() != null && valeur2.getIdentifiant() != null) {
            return !valeur1.getIdentifiant().equals(valeur2.getIdentifiant());
        }
        else {
            return true;
        }
    }

    /**
     * Teste la différence entre des valeurs de type CodePostalCommuneDto.
     * @param valeur1 la valeur 1
     * @param valeur2 la valeur 2
     * @return true si les valeurs sont différentes, false sinon
     */
    private boolean isValeursDifferentes(CodePostalCommuneDto valeur1, CodePostalCommuneDto valeur2) {
        if (valeur1 == null && valeur2 == null) {
            return false;
        }
        else if (valeur1 == null && valeur2 != null) {
            return valeur2.getIdentifiant() != null;
        }
        else if (valeur1 != null && valeur2 == null) {
            return valeur1.getIdentifiant() != null;
        }
        else if (valeur1.getIdentifiant() == null && valeur2.getIdentifiant() == null) {
            return false;
        }
        else if (valeur1.getIdentifiant() != null && valeur2.getIdentifiant() != null) {
            return !valeur1.getIdentifiant().equals(valeur2.getIdentifiant());
        }
        else {
            return true;
        }
    }

    /**
     * Construit un objet IdentifiantLibelleDto à partir d'un objet DimensionRessourceDto.
     * @param dimensionRessourceDto l'objet DimensionRessourceDto
     * @return l'objet IdentifiantLibelleDto
     */
    private IdentifiantLibelleDto getIdentifiantLibelleByDimensionResource(DimensionRessourceDto dimensionRessourceDto) {
        IdentifiantLibelleDto identifiantLibelleDto = null;
        if (dimensionRessourceDto != null) {
            final StringBuffer libelle = new StringBuffer("");
            if (dimensionRessourceDto.getPrenom() != null) {
                libelle.append(dimensionRessourceDto.getPrenom());
            }
            if (dimensionRessourceDto.getNom() != null) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(dimensionRessourceDto.getNom());
            }
            identifiantLibelleDto = new IdentifiantLibelleDto(dimensionRessourceDto.getIdentifiant(), libelle.toString());
        }
        return identifiantLibelleDto;
    }

    /**
     * Recherche l'adresse principale en cours parmi une liste d'adresses.
     * @param listeAdresses la liste d'adresses
     * @param idNatureAdressePrincipale l'identifiant de la nature d'une adresse principale
     * @return l'adresse, null si pas trouvée
     */
    private AdresseDto rechercherAdressePrincipaleEnCours(List<AdresseDto> listeAdresses, Long idNatureAdressePrincipale) {
        final Calendar aujourdhui = Calendar.getInstance();
        // Parcours de la liste d'adresses
        if (listeAdresses != null) {
            for (AdresseDto adresse : listeAdresses) {
                // Si c'est une adresse principale
                if (adresse.getNature() != null && idNatureAdressePrincipale.equals(adresse.getNature().getIdentifiant())) {
                    // Test si l'adresse est active
                    if (adresse.getDateDebut().before(aujourdhui) && (adresse.getDateFin() == null || adresse.getDateFin().after(aujourdhui))) {
                        return adresse;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Teste la différence entre deux adresses de même nature.
     * @param adresse1 l'adresse 1
     * @param adresse2 l'adresse 2
     * @return true si les adresses sont différentes, false sinon
     */
    private boolean isAdressesDifferentes(AdresseDto adresse1, AdresseDto adresse2) {
        if (adresse1 == null && adresse2 == null) {
            return false;
        }
        else if ((adresse1 == null && adresse2 != null) || (adresse1 != null && adresse2 == null)) {
            return true;
        }
        else {
            // Comparaison des champs de l'adresse
            if (isValeursDifferentes(adresse1.getNumVoie(), adresse2.getNumVoie())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.getNomVoie(), adresse2.getNomVoie())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.getTypeVoie(), adresse2.getTypeVoie())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.getComplementNom(), adresse2.getComplementNom())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.getComplementAdresse(), adresse2.getComplementAdresse())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.isNpai(), adresse2.isNpai())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.getBoitePostal(), adresse2.getBoitePostal())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.getAutresComplements(), adresse2.getAutresComplements())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.getCodePostalCommune(), adresse2.getCodePostalCommune())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.getDepartement(), adresse2.getDepartement())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.getPays(), adresse2.getPays())) {
                return true;
            }
            else if (isValeursDifferentes(adresse1.getCommuneEtranger(), adresse2.getCommuneEtranger())) {
                return true;
            }
            else {
                return isValeursDifferentes(adresse1.getCodePostalEtranger(), adresse2.getCodePostalEtranger());
            }
        }
    }

    /**
     * Construit un objet AdresseFusionDto à partir d'un objet AdresseDto.
     * @param adresseDto l'objet AdresseDto
     * @return l'objet AdresseFusionDto
     */
    private AdresseFusionDto getAdresseFusionByAdresse(AdresseDto adresseDto) {
        if (adresseDto == null) {
            return null;
        }
        else {
            final AdresseFusionDto adresseFusionDto = new AdresseFusionDto();
            adresseFusionDto.setIdentifiant(adresseDto.getIdentifiant());
            adresseFusionDto.setNature(adresseDto.getNature());
            // Construction du libellé de l'adresse
            final StringBuffer libelle = new StringBuffer("");
            if (adresseDto.isNpai()) {
                libelle.append(messageFusionApiSourceUtil.get(MessageKeyUtil.MESSAGE_NPAI));
            }
            if (adresseDto.getComplementNom() != null && !"".equals(adresseDto.getComplementNom().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getComplementNom().trim());
            }
            if (adresseDto.getNumVoie() != null && !"".equals(adresseDto.getNumVoie().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getNumVoie().trim());
            }
            if (adresseDto.getTypeVoie() != null && adresseDto.getTypeVoie().getLibelle() != null && !"".equals(adresseDto.getTypeVoie().getLibelle().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getTypeVoie().getLibelle().trim());
            }
            if (adresseDto.getNomVoie() != null && !"".equals(adresseDto.getNomVoie().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getNomVoie().trim());
            }
            if (adresseDto.getComplementAdresse() != null && !"".equals(adresseDto.getComplementAdresse().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getComplementAdresse().trim());
            }
            if (adresseDto.getAutresComplements() != null && !"".equals(adresseDto.getAutresComplements().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getAutresComplements().trim());
            }
            if (adresseDto.getCodePostalCommune() != null && adresseDto.getCodePostalCommune().getCodePostal() != null
                && adresseDto.getCodePostalCommune().getCodePostal().getLibelle() != null
                && !"".equals(adresseDto.getCodePostalCommune().getCodePostal().getLibelle().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getCodePostalCommune().getCodePostal().getLibelle().trim());
            }
            // Libellé d'acheminement
            if (adresseDto.getCodePostalCommune() != null && adresseDto.getCodePostalCommune().getLibelleAcheminement() != null
                && !"".equals(adresseDto.getCodePostalCommune().getLibelleAcheminement().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getCodePostalCommune().getLibelleAcheminement().trim());
            }
            if (adresseDto.getDepartement() != null && adresseDto.getDepartement().getLibelle() != null
                && !"".equals(adresseDto.getDepartement().getLibelle().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getDepartement().getLibelle().trim());
            }
            if (adresseDto.getPays() != null && adresseDto.getPays().getLibelle() != null && !"".equals(adresseDto.getPays().getLibelle().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getPays().getLibelle().trim());
            }
            if (adresseDto.getCodePostalEtranger() != null && !"".equals(adresseDto.getCodePostalEtranger().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getCodePostalEtranger().trim());
            }
            if (adresseDto.getCommuneEtranger() != null && !"".equals(adresseDto.getCommuneEtranger().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getCommuneEtranger().trim());
            }
            adresseFusionDto.setLibelleAdresse(libelle.toString());
            return adresseFusionDto;
        }
    }

    /**
     * Récupère et compare les téléphones des personnes source et cible.
     * @param listeTelephonesSource la liste des téléphones de la personne source
     * @param personneSourceFusion l'objet pour remplir les infos des propriétés de la personne source
     * @param listeTelephonesCible la liste des téléphones de la personne cible
     * @param personneCibleFusion l'objet pour remplir les infos des propriétés de la personne cible
     */
    private void recupererEtComparerTelephonesPersonnes(List<TelephoneDto> listeTelephonesSource, PersonneSourceFusionDto personneSourceFusion,
        List<TelephoneDto> listeTelephonesCible, PersonneCibleFusionDto personneCibleFusion) {
        final List<ProprieteFusionnableTelephoneDto> listeTelephonesSourceFusion = new ArrayList<ProprieteFusionnableTelephoneDto>();
        final List<TelephoneFusionDto> listeTelephonesCibleFusion = new ArrayList<TelephoneFusionDto>();
        // Parcours de la liste des téléphones de la source
        if (listeTelephonesSource != null) {
            for (TelephoneDto telephoneSource : listeTelephonesSource) {
                // Recherche du téléphone de la cible ayant la même nature que le téléphone de la source
                if (telephoneSource.getNature() != null && telephoneSource.getNature().getIdentifiant() != null) {
                    final TelephoneDto telephoneCible = rechercherTelephoneParNature(listeTelephonesCible, telephoneSource.getNature().getIdentifiant());
                    final boolean isTelephoneDifferent = isTelephonesDifferents(telephoneSource, telephoneCible);
                    // Création des téléphones fusion
                    final TelephoneFusionDto telephoneFusionSource = getTelephoneFusionByTelephone(telephoneSource);
                    final TelephoneFusionDto telephoneFusionCible = getTelephoneFusionByTelephone(telephoneCible);
                    // Si le téléhone cible n'a pas été trouvé, on remplit avec la nature de la source et un numéro vide
                    if (telephoneCible == null) {
                        telephoneFusionCible.setNature(telephoneSource.getNature());
                        telephoneFusionCible.setNumero("");
                    }
                    // Ajout des Téléphones Fusion
                    listeTelephonesSourceFusion.add(new ProprieteFusionnableTelephoneDto(telephoneFusionSource, isTelephoneDifferent));
                    listeTelephonesCibleFusion.add(telephoneFusionCible);
                }
            }
        }
        // Ajout des listes des téléphones aux personnes
        personneSourceFusion.setListeTelephones(listeTelephonesSourceFusion);
        personneCibleFusion.setListeTelephones(listeTelephonesCibleFusion);
    }

    /**
     * Recherche un téléphone de la nature demandée dans une liste de téléphones.
     * @param listeTelephones la liste de téléphones
     * @param idNature l'identifiant de la nature du téléphone
     * @return le téléphone recherché, null si pas trouvé
     */
    private TelephoneDto rechercherTelephoneParNature(List<TelephoneDto> listeTelephones, Long idNature) {
        if (listeTelephones != null) {
            // Parcours de la liste des téléphones
            for (TelephoneDto telephone : listeTelephones) {
                if (telephone.getNature() != null && idNature.equals(telephone.getNature().getIdentifiant())) {
                    return telephone;
                }
            }
        }
        return null;
    }

    /**
     * Teste la différence entre deux téléphones de même nature.
     * @param telephone1 le téléphone 1
     * @param telephone2 le téléphone 2
     * @return true si les téléphones sont différents, false sinon
     */
    private boolean isTelephonesDifferents(TelephoneDto telephone1, TelephoneDto telephone2) {
        if (telephone1 == null && telephone2 == null) {
            return false;
        }
        else if ((telephone1 == null && telephone2 != null) || (telephone1 != null && telephone2 == null)) {
            return true;
        }
        else {
            return isValeursDifferentes(telephone1.getNumero(), telephone2.getNumero());
        }
    }

    /**
     * Construit un objet TelephoneFusionDto à partir d'un objet TelephoneDto.
     * @param telephoneDto l'objet TelephoneDto (si null, création d'un objet TelephoneFusionDto avec un numéro vide)
     * @return l'objet TelephoneFusionDto
     */
    private TelephoneFusionDto getTelephoneFusionByTelephone(TelephoneDto telephoneDto) {
        final TelephoneFusionDto telephoneFusionDto = new TelephoneFusionDto();
        if (telephoneDto != null) {
            telephoneFusionDto.setIdentifiant(telephoneDto.getId());
            telephoneFusionDto.setNature(telephoneDto.getNature());
            telephoneFusionDto.setNumero(telephoneDto.getNumero());
        }
        return telephoneFusionDto;
    }

    /**
     * Récupère et compare les mails des personnes source et cible.
     * @param listeEmailsSource la liste des mails de la personne source
     * @param personneSourceFusion l'objet pour remplir les infos des propriétés de la personne source
     * @param listeEmailsCible la liste des mails de la personne cible
     * @param personneCibleFusion l'objet pour remplir les infos des propriétés de la personne cible
     */
    private void recupererEtComparerEmailsPersonnes(List<EmailDto> listeEmailsSource, PersonneSourceFusionDto personneSourceFusion,
        List<EmailDto> listeEmailsCible, PersonneCibleFusionDto personneCibleFusion) {
        final List<ProprieteFusionnableEmailDto> listeEmailsSourceFusion = new ArrayList<ProprieteFusionnableEmailDto>();
        final List<EmailFusionDto> listeEmailsCibleFusion = new ArrayList<EmailFusionDto>();
        // Parcours de la liste des mails de la source
        if (listeEmailsSource != null) {
            for (EmailDto emailSource : listeEmailsSource) {
                // Recherche du mail de la cible ayant la même nature que le mail de la source
                if (emailSource.getNatureEmail() != null && emailSource.getNatureEmail().getIdentifiant() != null) {
                    final EmailDto emailCible = rechercherEmailParNature(listeEmailsCible, emailSource.getNatureEmail().getIdentifiant());
                    final boolean isEmailDifferent = isEmailsDifferents(emailSource, emailCible);
                    // Création des mails fusion
                    final EmailFusionDto emailFusionSource = getEmailFusionByTelephone(emailSource);
                    final EmailFusionDto emailFusionCible = getEmailFusionByTelephone(emailCible);
                    // Si le mail cible n'a pas été trouvé, on remplit avec la nature de la source et un numéro vide
                    if (emailCible == null) {
                        emailFusionCible.setNature(emailSource.getNatureEmail());
                        emailFusionCible.setAdresse("");
                    }
                    // Ajout des Emails Fusion
                    listeEmailsSourceFusion.add(new ProprieteFusionnableEmailDto(emailFusionSource, isEmailDifferent));
                    listeEmailsCibleFusion.add(emailFusionCible);
                }
            }
        }
        // Ajout des listes des mails aux personnes
        personneSourceFusion.setListeEmails(listeEmailsSourceFusion);
        personneCibleFusion.setListeEmails(listeEmailsCibleFusion);
    }

    /**
     * Recherche un mail de la nature demandée dans une liste de mails.
     * @param listeEmails la liste de mails
     * @param idNature l'identifiant de la nature du mail
     * @return le mail recherché, null si pas trouvé
     */
    private EmailDto rechercherEmailParNature(List<EmailDto> listeEmails, Long idNature) {
        if (listeEmails != null) {
            // Parcours de la liste des mails
            for (EmailDto email : listeEmails) {
                if (email.getNatureEmail() != null && idNature.equals(email.getNatureEmail().getIdentifiant())) {
                    return email;
                }
            }
        }
        return null;
    }

    /**
     * Teste la différence entre deux mails de même nature.
     * @param email1 le mail 1
     * @param email2 le mail 2
     * @return true si les mails sont différents, false sinon
     */
    private boolean isEmailsDifferents(EmailDto email1, EmailDto email2) {
        if (email1 == null && email2 == null) {
            return false;
        }
        else if ((email1 == null && email2 != null) || (email1 != null && email2 == null)) {
            return true;
        }
        else {
            return isValeursDifferentes(email1.getAdresse(), email2.getAdresse());
        }
    }

    /**
     * Construit un objet EmailFusionDto à partir d'un objet EmailDto.
     * @param emailDto l'objet EmailDto (si null, création d'un objet EmailFusionDto avec un numéro vide)
     * @return l'objet EmailFusionDto
     */
    private EmailFusionDto getEmailFusionByTelephone(EmailDto emailDto) {
        final EmailFusionDto emailFusionDto = new EmailFusionDto();
        if (emailDto != null) {
            emailFusionDto.setIdentifiant(emailDto.getIdentifiant());
            emailFusionDto.setNature(emailDto.getNatureEmail());
            emailFusionDto.setAdresse(emailDto.getAdresse());
        }
        return emailFusionDto;
    }

    /**
     * Fusionne les champs de la personne source vers ceux de la personne cible.
     * @param personneAFusionner l'objet indiquant les champs à fusionner
     * @param personneSource la personne source Square
     * @param personneCible la personne cible Square
     */
    private void fusionnerPersonneSourceDansPersonneCible(PersonneSourceFusionDto personneAFusionner, PersonneDto personneSource, PersonneDto personneCible) {
        // Mapping des valeurs à fusionner
        if (personneCible.getInfoSante() == null) {
            personneCible.setInfoSante(new InfoSanteDto());
        }
        // NRO
        if (personneAFusionner.getNro() != null && Boolean.TRUE.equals(personneAFusionner.getNro().getAFusionner())) {
            personneCible.getInfoSante().setNro(personneSource.getInfoSante().getNro());
        }
        // Civilité
        if (personneAFusionner.getCivilite() != null && Boolean.TRUE.equals(personneAFusionner.getCivilite().getAFusionner())) {
            personneCible.setCivilite(personneSource.getCivilite());
        }
        // Prénom
        if (personneAFusionner.getPrenom() != null && Boolean.TRUE.equals(personneAFusionner.getPrenom().getAFusionner())) {
            personneCible.setPrenom(personneSource.getPrenom());
        }
        // Nom
        if (personneAFusionner.getNom() != null && Boolean.TRUE.equals(personneAFusionner.getNom().getAFusionner())) {
            personneCible.setNom(personneSource.getNom());
        }
        // Nom de jeune fille
        if (personneAFusionner.getNomJeuneFille() != null && Boolean.TRUE.equals(personneAFusionner.getNomJeuneFille().getAFusionner())) {
            personneCible.setNomJeuneFille(personneSource.getNomJeuneFille());
        }
        // Date de naissance
        if (personneAFusionner.getDateNaissance() != null && Boolean.TRUE.equals(personneAFusionner.getDateNaissance().getAFusionner())) {
            personneCible.setDateNaissance(personneSource.getDateNaissance());
        }
        // Date de décès
        if (personneAFusionner.getDateDeces() != null && Boolean.TRUE.equals(personneAFusionner.getDateDeces().getAFusionner())) {
            personneCible.setDateDeces(personneSource.getDateDeces());
        }
        // Nature
        if (personneAFusionner.getNaturePersonne() != null && Boolean.TRUE.equals(personneAFusionner.getNaturePersonne().getAFusionner())) {
            personneCible.setNaturePersonne(personneSource.getNaturePersonne());
        }
        // Flag décédé
        if (personneAFusionner.getDecede() != null && Boolean.TRUE.equals(personneAFusionner.getDecede().getAFusionner())) {
            personneCible.setDecede(personneSource.isDecede());
        }
        // Caisse
        if (personneAFusionner.getCaisse() != null && Boolean.TRUE.equals(personneAFusionner.getCaisse().getAFusionner())) {
            personneCible.getInfoSante().setCaisse(personneSource.getInfoSante().getCaisse());
        }
        // Régime
        if (personneAFusionner.getCaisseRegime() != null && Boolean.TRUE.equals(personneAFusionner.getCaisseRegime().getAFusionner())) {
            personneCible.getInfoSante().setCaisseRegime(personneSource.getInfoSante().getCaisseRegime());
        }
        // Segment
        if (personneAFusionner.getSegment() != null && Boolean.TRUE.equals(personneAFusionner.getSegment().getAFusionner())) {
            personneCible.setSegment(personneSource.getSegment());
        }
        // Réseau de vente
        if (personneAFusionner.getReseauVente() != null && Boolean.TRUE.equals(personneAFusionner.getReseauVente().getAFusionner())) {
            personneCible.setReseauVente(personneSource.getReseauVente());
        }
        // CSP
        if (personneAFusionner.getCsp() != null && Boolean.TRUE.equals(personneAFusionner.getCsp().getAFusionner())) {
            personneCible.setCsp(personneSource.getCsp());
        }
        // Situation familiale
        if (personneAFusionner.getSitFam() != null && Boolean.TRUE.equals(personneAFusionner.getSitFam().getAFusionner())) {
            personneCible.setSitFam(personneSource.getSitFam());
        }
        // Profession
        if (personneAFusionner.getProfession() != null && Boolean.TRUE.equals(personneAFusionner.getProfession().getAFusionner())) {
            personneCible.setProfession(personneSource.getProfession());
        }
        // Statut
        if (personneAFusionner.getStatut() != null && Boolean.TRUE.equals(personneAFusionner.getStatut().getAFusionner())) {
            personneCible.setStatut(personneSource.getStatut());
        }

        // Transfert du Commercial
        if (personneAFusionner.getCommercial() != null && Boolean.TRUE.equals(personneAFusionner.getCommercial().getAFusionner())) {
            if (personneSource.getCommercial() != null && personneSource.getCommercial().getIdentifiant() != null) {
                DimensionRessourceDto commercialCible = personneCible.getCommercial();
                if (commercialCible == null) {
                    commercialCible = new DimensionRessourceDto();
                }
                // Affectation du nouveau commercial
                commercialCible.setIdentifiant(personneSource.getCommercial().getIdentifiant());
                personneCible.setCommercial(commercialCible);
                // TODO voir si nécessaire de passer par le transfert
                // personneService.transfererPersonneACommercial(personneCible.getIdentifiant(), personneSource.getCommercial().getIdentifiant());
            }
        }

        // Transfert de l'Agence
        if (personneAFusionner.getAgence() != null && Boolean.TRUE.equals(personneAFusionner.getAgence().getAFusionner())) {
            personneCible.setAgence(personneSource.getAgence());
            // TODO voir si nécessaire de passer par le transfert
            // if (personneSource.getAgence() != null && personneSource.getAgence().getIdentifiant() != null) {
            // personneService.transfererPersonneAAgence(personneCible.getIdentifiant(), personneSource.getAgence().getIdentifiant());
            // }
        }

        // Appel du service de modification de personne
        personnePhysiqueService.modifierPersonnePhysique(personneCible);

        // Récupération des coordonnées de la personne source
        final CoordonneesDto coordonneesPersonneSource = personneService.rechercherCoordonneesParIdPersonne(personneSource.getIdentifiant());
        // Récupération des coordonnées de la personne cible
        final CoordonneesDto coordonneesPersonneCible = personneService.rechercherCoordonneesParIdPersonne(personneCible.getIdentifiant());

        // Booléen indiquant que les coordonnées de la cible ont été modifiées
        boolean coordonneesCibleModifiees = false;

        // Adresse principale
        if (personneAFusionner.getAdressePrincipale() != null && Boolean.TRUE.equals(personneAFusionner.getAdressePrincipale().getAFusionner())) {
            final Long idNatureAdressePrincipale = squareMappingService.getIdNatureAdressePrincipale();
            final AdresseDto adressePrincPersonneSource =
                rechercherAdressePrincipaleEnCours(coordonneesPersonneSource.getAdresses(), idNatureAdressePrincipale);
            AdresseDto adressePrincPersonneCible = rechercherAdressePrincipaleEnCours(coordonneesPersonneCible.getAdresses(), idNatureAdressePrincipale);
            // Si l'adresse principale n'existe pas, ajout d'une adresse aux coordonnées
            if (adressePrincPersonneCible == null) {
                logger.debug(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CREATION_ADRESSE_PRINCIPALE_CIBLE));
                adressePrincPersonneCible = new AdresseDto();
                ajouterAdressePrincipaleACoordonnees(adressePrincPersonneCible, coordonneesPersonneCible);
            }
            fusionnerAdresses(adressePrincPersonneSource, adressePrincPersonneCible);
            coordonneesCibleModifiees = true;
        }

        // Liste des téléphones
        // Parcours de la liste des téléphones sources
        if (personneAFusionner.getListeTelephones() != null && !personneAFusionner.getListeTelephones().isEmpty()) {
            final List<TelephoneDto> telephonesToAdd = new ArrayList<TelephoneDto>();
            for (ProprieteFusionnableTelephoneDto telephoneSourceAFusionner : personneAFusionner.getListeTelephones()) {
                // Si le téléphone doit être fusionné
                if (Boolean.TRUE.equals(telephoneSourceAFusionner.getAFusionner())) {
                    // Recherche du téléphone source par son identifiant
                    final TelephoneDto telephoneSource =
                        rechercherTelephoneParId(coordonneesPersonneSource.getTelephones(), telephoneSourceAFusionner.getValeur().getIdentifiant());
                    // Recherche du téléphone cible de même nature que la source
                    TelephoneDto telephoneCible = null;
                    if (telephoneSource != null) {
                        // si le téléphone n'est pas vide
                        if (telephoneSourceAFusionner.getValeur().getNumero() != null && telephoneSourceAFusionner.getValeur().getNumero() != "") {
                            telephonesToAdd.add(telephoneSource);
                        }
                        else {
                            if (telephoneSourceAFusionner.getValeur().getNature() != null
                                && telephoneSourceAFusionner.getValeur().getNature().getIdentifiant() != null) {
                                telephoneCible =
                                    rechercherTelephoneParNature(coordonneesPersonneCible.getTelephones(), telephoneSourceAFusionner.getValeur().getNature()
                                            .getIdentifiant());
                            }
                            // Si le téléphone cible a été trouvé, on le supprime
                            if (telephoneCible != null) {
                                telephoneCible.setSupprime(true);
                            }
                        }
                        coordonneesCibleModifiees = true;
                    }
                }
            }
            for (TelephoneDto tel : telephonesToAdd) {
                ajouterTelephoneAuxCoordonnees(tel, coordonneesPersonneCible);
            }
        }

        // Liste des mails
        // Parcours de la liste des mails sources
        if (personneAFusionner.getListeEmails() != null && !personneAFusionner.getListeEmails().isEmpty()) {
            for (ProprieteFusionnableEmailDto emailSourceAFusionner : personneAFusionner.getListeEmails()) {
                // Si le mail doit être fusionné
                if (Boolean.TRUE.equals(emailSourceAFusionner.getAFusionner())) {
                    // Recherche du mail source par son identifiant
                    final EmailDto emailSource =
                        rechercherEmailParId(coordonneesPersonneSource.getEmails(), emailSourceAFusionner.getValeur().getIdentifiant());
                    // Recherche du mail cible de même nature que la source
                    EmailDto emailCible = null;
                    if (emailSource != null) {
                        if (emailSource.getNatureEmail() != null && emailSource.getNatureEmail().getIdentifiant() != null) {
                            emailCible = rechercherEmailParNature(coordonneesPersonneCible.getEmails(), emailSource.getNatureEmail().getIdentifiant());
                        }
                        // Si le mail cible n'a pas été trouvé, création d'un nouveau mail à partir de la source
                        if (emailCible == null) {
                            logger.debug(messageFusionApiSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CREATION_MAIL_CIBLE_NATURE,
                           		 new String[] {String.valueOf(emailSource.getNatureEmail().getIdentifiant())}));
                            emailCible = new EmailDto();
                            // Ajout du mail cible à la liste des mails de la cible
                            ajouterEmailAuxCoordonnees(emailCible, coordonneesPersonneCible);
                        }
                        // Fusion des champs du mail
                        fusionnerEmails(emailSource, emailCible);
                        coordonneesCibleModifiees = true;
                    }
                }
            }
        }

        // Modification des coordonnées si nécessaire
        if (coordonneesCibleModifiees) {
            personneService.creerOuMettreAJourCoordonnees(coordonneesPersonneCible, true, true);
        }
    }

    /**
     * Fusionne les champs des adresses.
     * @param adresseSource l'adresse source
     * @param adresseCible l'adresse cible
     */
    private void fusionnerAdresses(AdresseDto adresseSource, AdresseDto adresseCible) {
        adresseCible.setNumVoie(adresseSource.getNumVoie());
        adresseCible.setNomVoie(adresseSource.getNomVoie());
        adresseCible.setTypeVoie(adresseSource.getTypeVoie());
        adresseCible.setComplementNom(adresseSource.getComplementNom());
        adresseCible.setComplementAdresse(adresseSource.getComplementAdresse());
        adresseCible.setNpai(adresseSource.isNpai());
        adresseCible.setBoitePostal(adresseSource.getBoitePostal());
        adresseCible.setAutresComplements(adresseSource.getAutresComplements());
        adresseCible.setCodePostalCommune(adresseSource.getCodePostalCommune());
        adresseCible.setDepartement(adresseSource.getDepartement());
        adresseCible.setPays(adresseSource.getPays());
        adresseCible.setCommuneEtranger(adresseSource.getCommuneEtranger());
        adresseCible.setCodePostalEtranger(adresseSource.getCodePostalEtranger());
    }

    /**
     * Fusionne les champs des mails.
     * @param emailSource le mail source
     * @param emailCible le mail cible
     */
    private void fusionnerEmails(EmailDto emailSource, EmailDto emailCible) {
        emailCible.setIdext(emailSource.getIdext());
        emailCible.setAdresse(emailSource.getAdresse());
        emailCible.setNatureEmail(emailSource.getNatureEmail());
    }

    /**
     * Recherche un téléphone par son identifiant dans une liste de téléphones.
     * @param listeTelephones la liste des téléphones
     * @param idTelephone l'identifiant du téléphone
     * @return le téléphone recherché, null si non trouvé
     */
    private TelephoneDto rechercherTelephoneParId(List<TelephoneDto> listeTelephones, Long idTelephone) {
        if (listeTelephones != null && !listeTelephones.isEmpty()) {
            for (TelephoneDto telephone : listeTelephones) {
                if (idTelephone != null && idTelephone.equals(telephone.getId())) {
                    return telephone;
                }
            }
        }
        return null;
    }

    /**
     * Ajoute un téléphone aux coordonnées.
     * @param telephone le téléphone
     * @param coordonnees les coordonnées
     */
    private void ajouterTelephoneAuxCoordonnees(TelephoneDto telephone, CoordonneesDto coordonnees) {
        // Création de la liste des téléphones si elle n'existe pas
        if (coordonnees.getTelephones() == null) {
            coordonnees.setTelephones(new ArrayList<TelephoneDto>());
        }
        // Ajout du téléphone à la liste
        telephone.setId(null);
        coordonnees.getTelephones().add(telephone);
    }

    /**
     * Recherche un mail par son identifiant dans une liste de mails.
     * @param listeEmails la liste des mails
     * @param idEmail l'identifiant du mail
     * @return le mail recherché, null si non trouvé
     */
    private EmailDto rechercherEmailParId(List<EmailDto> listeEmails, Long idEmail) {
        if (listeEmails != null && !listeEmails.isEmpty()) {
            for (EmailDto email : listeEmails) {
                if (idEmail.equals(email.getIdentifiant())) {
                    return email;
                }
            }
        }
        return null;
    }

    /**
     * Ajoute un mail aux coordonnées.
     * @param email le mail
     * @param coordonnees les coordonnées
     */
    private void ajouterEmailAuxCoordonnees(EmailDto email, CoordonneesDto coordonnees) {
        // Création de la liste des mails si elle n'existe pas
        if (coordonnees.getEmails() == null) {
            coordonnees.setEmails(new ArrayList<EmailDto>());
        }
        // Ajout du mail à la liste
        coordonnees.getEmails().add(email);
    }

    /**
     * Ajoute une adresse principale aux coordonnées.
     * @param coordonnees les coordonnées
     * @return l'adresse ajoutée
     */
    private void ajouterAdressePrincipaleACoordonnees(AdresseDto adresse, CoordonneesDto coordonnees) {
        if (coordonnees.getAdresses() == null) {
            coordonnees.setAdresses(new ArrayList<AdresseDto>());
        }
        final Long idNatureAdressePrincipale = squareMappingService.getIdNatureAdressePrincipale();
        adresse.setTypeVoie(new IdentifiantLibelleDto());
        adresse.setCodePostalCommune(new CodePostalCommuneDto());
        adresse.setDepartement(new IdentifiantLibelleDto());
        adresse.setPays(new IdentifiantLibelleBooleanDto());
        adresse.setNature(new IdentifiantLibelleDto(idNatureAdressePrincipale));
        adresse.setDateDebut(Calendar.getInstance());
        coordonnees.getAdresses().add(adresse);
    }

    /**
     * Définit la valeur de messageFusionApiSourceUtil.
     * @param messageFusionApiSourceUtil la nouvelle valeur de messageFusionApiSourceUtil
     */
    public void setMessageFusionApiSourceUtil(MessageSourceUtil messageFusionApiSourceUtil) {
        this.messageFusionApiSourceUtil = messageFusionApiSourceUtil;
    }

    /**
     * Définit la valeur de personnePhysiqueService.
     * @param personnePhysiqueService la nouvelle valeur de personnePhysiqueService
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Définit la valeur de adherentService.
     * @param adherentService la nouvelle valeur de adherentService
     */
    public void setAdherentService(AdherentService adherentService) {
        this.adherentService = adherentService;
    }

    /**
     * Définit la valeur de personneService.
     * @param personneService la nouvelle valeur de personneService
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définit la valeur de opportuniteService.
     * @param opportuniteService la nouvelle valeur de opportuniteService
     */
    public void setOpportuniteService(OpportuniteService opportuniteService) {
        this.opportuniteService = opportuniteService;
    }

    /**
     * Définit la valeur de actionService.
     * @param actionService la nouvelle valeur de actionService
     */
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    /**
     * Définit la valeur de tarificateurPersonneService.
     * @param tarificateurPersonneService la nouvelle valeur de tarificateurPersonneService
     */
    public void setTarificateurPersonneService(TarificateurPersonneService tarificateurPersonneService) {
        this.tarificateurPersonneService = tarificateurPersonneService;
    }

    /**
     * Définit la valeur de tarificateurService.
     * @param tarificateurService la nouvelle valeur de tarificateurService
     */
    public void setTarificateurService(TarificateurService tarificateurService) {
        this.tarificateurService = tarificateurService;
    }

    /**
     * Définit la valeur de synchroAppJmsSender.
     * @param synchroAppJmsSender the synchroAppJmsSender to set
     */
    public void setSynchroAppJmsSender(SynchroAppJmsSender synchroAppJmsSender) {
        this.synchroAppJmsSender = synchroAppJmsSender;
    }
}
