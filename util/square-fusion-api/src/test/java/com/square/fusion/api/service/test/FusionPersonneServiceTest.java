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
package com.square.fusion.api.service.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
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
import com.square.fusion.api.dto.ProprieteFusionnableEmailDto;
import com.square.fusion.api.dto.ProprieteFusionnableTelephoneDto;
import com.square.fusion.api.dto.TelephoneFusionDto;
import com.square.fusion.api.service.interfaces.FusionPersonneService;
import com.square.fusion.api.service.test.mock.ActionServiceImpl;
import com.square.fusion.api.service.test.mock.OpportuniteServiceImpl;
import com.square.fusion.api.service.test.mock.PersonnePhysiqueServiceImpl;
import com.square.fusion.api.service.test.mock.PersonneServiceImpl;
import com.square.fusion.api.service.test.mock.TarificateurPersonneServiceImpl;
import com.square.fusion.api.util.MessageKeyUtil;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;

/**
 * Tests des services de fusion de personnes.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class FusionPersonneServiceTest extends BaseTestCase {

    private static final String EXCEPTION_PAS_BONNE = Messages.getString("FusionPersonneServiceTest.0");

    private static final String NATURE_MAIL_PAS_BONNE = Messages.getString("FusionPersonneServiceTest.1");

    private static final String ADRESSE_MAIL_PAS_BONNE = Messages.getString("FusionPersonneServiceTest.2");

    private static final String NATURE_TELEPHONE_PAS_BONNE = Messages.getString("FusionPersonneServiceTest.3");

    private static final String NUMERO_TELEPHONE_PAS_BON = Messages.getString("FusionPersonneServiceTest.4");

    private static final String IDENTIFIANT_TELEPHONE_PAS_BON = Messages.getString("FusionPersonneServiceTest.5");

    private static final String FLAG_DIFFERENT_PAS_BON = Messages.getString("FusionPersonneServiceTest.6");

    private static final String CHAMP_PAS_BON = Messages.getString("FusionPersonneServiceTest.7");

    private static final String PERSONNE_CIBLE_PAS_BONNE = Messages.getString("FusionPersonneServiceTest.8");

    private static final String PERSONNE_SOURCE_DOIT_PAS_POSSEDER_CONTRAT = Messages.getString("FusionPersonneServiceTest.9");

    private static final String PERSONNE_SOURCE_PAS_BONNE = Messages.getString("FusionPersonneServiceTest.10");

    /** Service de fusion. */
    private FusionPersonneService fusionPersonneService;

    /** Service de mapping Square. */
    private SquareMappingService squareMappingService;

    /** Service PersonnePhysique Square. */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Service Personne Square. */
    private PersonneService personneService;

    /** Service Opportunité Square. */
    private OpportuniteService opportuniteService;

    /** Service Actions Square. */
    private ActionService actionService;

    /** Service Personne (Tarificateur Square). */
    private TarificateurPersonneService tarificateurPersonneService;

    /** MessageFusionApiSourceUtil. */
    private MessageSourceUtil messageFusionApiSourceUtil;

    /** Méthode appelée avant les tests unitaires. */
    @Before
    public void setUp() {
        fusionPersonneService = (FusionPersonneService) getBeanSpring("fusionPersonneService");
        squareMappingService = (SquareMappingService) getBeanSpring("squareMappingService");
        messageFusionApiSourceUtil = (MessageSourceUtil) getBeanSpring("messageFusionApiSourceUtil");
        personnePhysiqueService = (PersonnePhysiqueService) getBeanSpring("personnePhysiqueService");
        personneService = (PersonneService) getBeanSpring("personneService");
        opportuniteService = (OpportuniteService) getBeanSpring("opportuniteService");
        actionService = (ActionService) getBeanSpring("actionService");
        tarificateurPersonneService = (TarificateurPersonneService) getBeanSpring("tarificateurPersonneService");
    }

    /** Test du service de préparation à la fusion. */
    @Test
    public void testPreparerFusion() {
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;
        final Long idPersonne3 = 3L;
        final Long idPersonne4 = 4L;
        final Long idPersonne5 = 5L;

        // Test avec une personne n'existant pas (4) : exception
        try {
            fusionPersonneService.preparerFusion(idPersonne1, idPersonne4);
            fail(Messages.getString("FusionPersonneServiceTest.19"));
        } catch (TechnicalException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_INEXISTANTE), e.getMessage());
        }

        // Test avec deux personnes possédant un contrat (1 et 3) : exception
        try {
            fusionPersonneService.preparerFusion(idPersonne1, idPersonne3);
            fail(Messages.getString("FusionPersonneServiceTest.20"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_IMPOSSIBLE_PERSONNES_RATTACHEES_CONTRATS), e
                    .getMessage());
        }

        // Test avec une personne avec un contrat (1) et une personne sans contrat (2) : source 2, cible 1
        final ParametresFusionDto parametresFusion1 = fusionPersonneService.preparerFusion(idPersonne1, idPersonne2);
        assertEquals(PERSONNE_SOURCE_PAS_BONNE, idPersonne2, parametresFusion1.getPersonneSource().getIdentifiant());
        assertFalse(PERSONNE_SOURCE_DOIT_PAS_POSSEDER_CONTRAT, parametresFusion1.getPersonneSource().getPossedeContrat());
        assertEquals(PERSONNE_CIBLE_PAS_BONNE, idPersonne1, parametresFusion1.getPersonneCible().getIdentifiant());
        assertTrue(Messages.getString("FusionPersonneServiceTest.21"), parametresFusion1.getPersonneCible().getPossedeContrat());
        // Vérification du remplissage des DTO des personnes
        controlerParametresFusionPersonnes1Et2(parametresFusion1.getPersonneSource(), parametresFusion1.getPersonneCible());

        // Test avec une personne sans contrat (2) et une personne avec un contrat (1) : source 2, cible 1
        final ParametresFusionDto parametresFusion2 = fusionPersonneService.preparerFusion(idPersonne2, idPersonne1);
        assertEquals(PERSONNE_SOURCE_PAS_BONNE, idPersonne2, parametresFusion2.getPersonneSource().getIdentifiant());
        assertFalse(PERSONNE_SOURCE_DOIT_PAS_POSSEDER_CONTRAT, parametresFusion2.getPersonneSource().getPossedeContrat());
        assertEquals(PERSONNE_CIBLE_PAS_BONNE, idPersonne1, parametresFusion2.getPersonneCible().getIdentifiant());
        assertTrue(Messages.getString("FusionPersonneServiceTest.22"), parametresFusion2.getPersonneCible().getPossedeContrat());

        // Test avec deux personnes sans contrat (2 et 5) : source 2 et cible 5
        final ParametresFusionDto parametresFusion3 = fusionPersonneService.preparerFusion(idPersonne2, idPersonne5);
        assertEquals(PERSONNE_SOURCE_PAS_BONNE, idPersonne2, parametresFusion3.getPersonneSource().getIdentifiant());
        assertFalse(PERSONNE_SOURCE_DOIT_PAS_POSSEDER_CONTRAT, parametresFusion3.getPersonneSource().getPossedeContrat());
        assertEquals(PERSONNE_CIBLE_PAS_BONNE, idPersonne5, parametresFusion3.getPersonneCible().getIdentifiant());
        assertFalse(Messages.getString("FusionPersonneServiceTest.23"), parametresFusion3.getPersonneSource().getPossedeContrat());

        // Test avec deux personnes sans contrat (5 et 2) : source 5 et cible 2
        final ParametresFusionDto parametresFusion4 = fusionPersonneService.preparerFusion(idPersonne5, idPersonne2);
        assertEquals(PERSONNE_SOURCE_PAS_BONNE, idPersonne5, parametresFusion4.getPersonneSource().getIdentifiant());
        assertFalse(PERSONNE_SOURCE_DOIT_PAS_POSSEDER_CONTRAT, parametresFusion4.getPersonneSource().getPossedeContrat());
        assertEquals(PERSONNE_CIBLE_PAS_BONNE, idPersonne2, parametresFusion4.getPersonneCible().getIdentifiant());
        assertFalse(Messages.getString("FusionPersonneServiceTest.24"), parametresFusion4.getPersonneSource().getPossedeContrat());
    }

    /** Test de la validation de la fusion avec exceptions. */
    @Test
    public void testValiderFusionAvecExceptions() {
        final Long idPersonneInexistante100 = 100L;
        final Long idPersonneInexistante101 = 101L;
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;
        final String utilisateur = "user";

        // Création du parametre de fusion
        final ParametresFusionDto parametresFusionDto = new ParametresFusionDto();
        final PersonneSourceFusionDto personneSource = new PersonneSourceFusionDto();
        parametresFusionDto.setPersonneSource(personneSource);
        final PersonneCibleFusionDto personneCible = new PersonneCibleFusionDto();
        parametresFusionDto.setPersonneCible(personneCible);

        // Personne source inexistante (exception)
        personneSource.setIdentifiant(idPersonneInexistante100);
        personneCible.setIdentifiant(idPersonne1);
        try {
            fusionPersonneService.validerFusion(parametresFusionDto, utilisateur);
            fail(Messages.getString("FusionPersonneServiceTest.26"));
        } catch (TechnicalException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_SOURCE_INEXISTANTE), e.getMessage());
        }
        // Personne source inexistante (null)
        personneSource.setIdentifiant(idPersonneInexistante101);
        personneCible.setIdentifiant(idPersonne1);
        try {
            fusionPersonneService.validerFusion(parametresFusionDto, utilisateur);
            fail(Messages.getString("FusionPersonneServiceTest.27"));
        } catch (TechnicalException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_SOURCE_INEXISTANTE), e.getMessage());
        }
        // Personne cible inexistante (exception)
        personneSource.setIdentifiant(idPersonne2);
        personneCible.setIdentifiant(idPersonneInexistante100);
        try {
            fusionPersonneService.validerFusion(parametresFusionDto, utilisateur);
            fail(Messages.getString("FusionPersonneServiceTest.28"));
        } catch (TechnicalException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_CIBLE_INEXISTANTE), e.getMessage());
        }
        // Personne cible inexistante (null)
        personneSource.setIdentifiant(idPersonne2);
        personneCible.setIdentifiant(idPersonneInexistante101);
        try {
            fusionPersonneService.validerFusion(parametresFusionDto, utilisateur);
            fail(Messages.getString("FusionPersonneServiceTest.29"));
        } catch (TechnicalException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageFusionApiSourceUtil.get(MessageKeyUtil.ERREUR_FUSION_PERSONNE_CIBLE_INEXISTANTE), e.getMessage());
        }
    }

    /** Test de validation de la fusion. */
    @Test
    public void testValiderFusion() {
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;
        // Récupération des paramètres de la fusion des personnes 1 et 2
        final ParametresFusionDto parametresFusion = fusionPersonneService.preparerFusion(idPersonne1, idPersonne2);

        // Sélection des champs à fusionner
        final PersonneSourceFusionDto personneSource = parametresFusion.getPersonneSource();
        personneSource.getPrenom().setAFusionner(Boolean.TRUE);
        personneSource.getDateDeces().setAFusionner(Boolean.TRUE);
        personneSource.getDecede().setAFusionner(Boolean.TRUE);
        personneSource.getCsp().setAFusionner(Boolean.TRUE);
        personneSource.getSitFam().setAFusionner(Boolean.TRUE);
        personneSource.getProfession().setAFusionner(Boolean.TRUE);
        personneSource.getCommercial().setAFusionner(Boolean.TRUE);
        personneSource.getAgence().setAFusionner(Boolean.TRUE);
        personneSource.getAdressePrincipale().setAFusionner(Boolean.TRUE);
        // Sélection du téléphone 1 (numéro différent)
        final Long idTelephone1 = 1L;
        for (ProprieteFusionnableTelephoneDto telephone : personneSource.getListeTelephones()) {
            if (idTelephone1.equals(telephone.getValeur().getIdentifiant())) {
                telephone.setAFusionner(Boolean.TRUE);
            }
        }
        // Sélection du mail 2 (nature 3 n'existe pas dans la cible)
        final Long idEmail2 = 2L;
        for (ProprieteFusionnableEmailDto email : personneSource.getListeEmails()) {
            if (idEmail2.equals(email.getValeur().getIdentifiant())) {
                email.setAFusionner(Boolean.TRUE);
            }
        }

        // Validation de la fusion
        fusionPersonneService.validerFusion(parametresFusion, "user");

        // Vérification des champs de la personne cible passée au service de modification
        verifierPersonneModifieeApresFusion();
        // Vérification des coordonnées
        verifierCoordonneesModifieesApresFusion();
        // Vérification du transfert des relations
        assertTrue(Messages.getString("FusionPersonneServiceTest.31"), ((PersonneServiceImpl) personneService).isTransfertRelationsEffectue());
        // Vérification du transfert des opportunités
        assertTrue(Messages.getString("FusionPersonneServiceTest.32"), ((OpportuniteServiceImpl) opportuniteService).isTransfertOpportunitesEffectue());
        // Vérification du transfert des actions
        assertTrue(Messages.getString("FusionPersonneServiceTest.33"), ((ActionServiceImpl) actionService).isTransfertActionsEffectue());
        // Vérification du transfert des EID de personnes dans le tarificateur Square
        assertTrue(Messages.getString("FusionPersonneServiceTest.34"), ((TarificateurPersonneServiceImpl) tarificateurPersonneService)
                .isTransfertRelationsEffectue());
        // Vérification de la suppression de la personne source
        assertTrue(Messages.getString("FusionPersonneServiceTest.35"), ((PersonneServiceImpl) personneService).isSuppressionPersonneEffectuee());
    }

    /**
     * Contrôle les paramètres de la fusion calculés pour les personnes 2 (source) et 1 (cible).
     * @param personneSource la personne source
     * @param personneCible la personne cible
     */
    private void controlerParametresFusionPersonnes1Et2(PersonneSourceFusionDto personneSource, PersonneCibleFusionDto personneCible) {
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;
        assertEquals(PERSONNE_SOURCE_PAS_BONNE, idPersonne2, personneSource.getIdentifiant());
        assertEquals(Messages.getString("FusionPersonneServiceTest.36"), "NumClient2", personneSource.getNumeroClient());
        assertFalse(PERSONNE_SOURCE_DOIT_PAS_POSSEDER_CONTRAT, personneSource.getPossedeContrat());
        assertEquals(PERSONNE_CIBLE_PAS_BONNE, idPersonne1, personneCible.getIdentifiant());
        assertEquals(Messages.getString("FusionPersonneServiceTest.38"), "NumClient1", personneCible.getNumeroClient());
        assertTrue(Messages.getString("FusionPersonneServiceTest.40"), personneCible.getPossedeContrat());

        // Vérification des données de la personne source
        assertEquals(CHAMP_PAS_BON, "181101634101712", personneSource.getNro().getValeur());
        assertEquals(FLAG_DIFFERENT_PAS_BON, false, personneSource.getNro().getDifferent());
        assertEquals(CHAMP_PAS_BON, 2L, personneSource.getCivilite().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Madame", personneSource.getCivilite().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, true, personneSource.getCivilite().getDifferent());
        assertEquals(CHAMP_PAS_BON, "Nicolas", personneSource.getPrenom().getValeur());
        assertEquals(FLAG_DIFFERENT_PAS_BON, true, personneSource.getPrenom().getDifferent());
        assertEquals(CHAMP_PAS_BON, "PELTIER", personneSource.getNom().getValeur());
        assertEquals(FLAG_DIFFERENT_PAS_BON, false, personneSource.getNom().getDifferent());
        assertEquals(CHAMP_PAS_BON, null, personneSource.getNomJeuneFille().getValeur());
        assertEquals(FLAG_DIFFERENT_PAS_BON, false, personneSource.getNomJeuneFille().getDifferent());
        assertEquals(CHAMP_PAS_BON, getCalendarByDate("03/10/1981"), personneSource.getDateNaissance().getValeur());
        assertEquals(FLAG_DIFFERENT_PAS_BON, false, personneSource.getDateNaissance().getDifferent());
        assertEquals(CHAMP_PAS_BON, null, personneSource.getDateDeces().getValeur());
        assertEquals(FLAG_DIFFERENT_PAS_BON, true, personneSource.getDateDeces().getDifferent());
        assertEquals(CHAMP_PAS_BON, 1L, personneSource.getNaturePersonne().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Prospect", personneSource.getNaturePersonne().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, true, personneSource.getNaturePersonne().getDifferent());
        assertEquals(CHAMP_PAS_BON, false, personneSource.getDecede().getValeur());
        assertEquals(FLAG_DIFFERENT_PAS_BON, true, personneSource.getDecede().getDifferent());
        assertEquals(CHAMP_PAS_BON, 1L, personneSource.getCaisse().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Caisse 1", personneSource.getCaisse().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, false, personneSource.getCaisse().getDifferent());
        assertEquals(CHAMP_PAS_BON, 1L, personneSource.getCaisseRegime().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Régime 1", personneSource.getCaisseRegime().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, false, personneSource.getCaisseRegime().getDifferent());
        assertEquals(CHAMP_PAS_BON, 1L, personneSource.getSegment().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Individuel", personneSource.getSegment().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, false, personneSource.getSegment().getDifferent());
        assertEquals(CHAMP_PAS_BON, 1L, personneSource.getReseauVente().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Interne", personneSource.getReseauVente().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, false, personneSource.getReseauVente().getDifferent());
        assertEquals(CHAMP_PAS_BON, null, personneSource.getCsp().getValeur());
        assertEquals(FLAG_DIFFERENT_PAS_BON, true, personneSource.getCsp().getDifferent());
        assertEquals(CHAMP_PAS_BON, 2L, personneSource.getSitFam().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Marié", personneSource.getSitFam().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, true, personneSource.getSitFam().getDifferent());
        assertEquals(CHAMP_PAS_BON, 1L, personneSource.getProfession().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "NC", personneSource.getProfession().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, true, personneSource.getProfession().getDifferent());
        assertEquals(CHAMP_PAS_BON, 1L, personneSource.getStatut().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Actif", personneSource.getStatut().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, false, personneSource.getStatut().getDifferent());
        assertEquals(CHAMP_PAS_BON, 2L, personneSource.getCommercial().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "PrenomCommercial2 NomCommercial2", personneSource.getCommercial().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, true, personneSource.getCommercial().getDifferent());
        assertEquals(CHAMP_PAS_BON, 2L, personneSource.getAgence().getValeur().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Agence2", personneSource.getAgence().getValeur().getLibelle());
        assertEquals(FLAG_DIFFERENT_PAS_BON, true, personneSource.getAgence().getDifferent());
        // Adresse principale de la source
        assertEquals(FLAG_DIFFERENT_PAS_BON, false, personneSource.getAdressePrincipale().getDifferent());
        controlerAdressePrincipalePersonne2(personneSource.getAdressePrincipale().getValeur());
        // Liste des téléphones de la source
        controlerListeTelephonesPersonne2(personneSource.getListeTelephones());
        // Liste des mails de la source
        controlerListeEmailsPersonne2(personneSource.getListeEmails());

        // Vérification des données de la personne cible
        assertEquals(CHAMP_PAS_BON, "181101634101712", personneCible.getNro());
        assertEquals(CHAMP_PAS_BON, 1L, personneCible.getCivilite().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Monsieur", personneCible.getCivilite().getLibelle());
        assertEquals(CHAMP_PAS_BON, "NIcôlas", personneCible.getPrenom());
        assertEquals(CHAMP_PAS_BON, "PELTIER", personneCible.getNom());
        assertEquals(CHAMP_PAS_BON, null, personneCible.getNomJeuneFille());
        assertEquals(CHAMP_PAS_BON, getCalendarByDate("03/10/1981"), personneCible.getDateNaissance());
        assertEquals(CHAMP_PAS_BON, getCalendarByDate("01/01/2005"), personneCible.getDateDeces());
        assertEquals(CHAMP_PAS_BON, 3L, personneCible.getNaturePersonne().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Adhérent", personneCible.getNaturePersonne().getLibelle());
        assertEquals(CHAMP_PAS_BON, true, personneCible.isDecede());
        assertEquals(CHAMP_PAS_BON, 1L, personneCible.getCaisse().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Caisse 1", personneCible.getCaisse().getLibelle());
        assertEquals(CHAMP_PAS_BON, 1L, personneCible.getCaisseRegime().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Régime 1", personneCible.getCaisseRegime().getLibelle());
        assertEquals(CHAMP_PAS_BON, 1L, personneCible.getSegment().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Individuel", personneCible.getSegment().getLibelle());
        assertEquals(CHAMP_PAS_BON, 1L, personneCible.getReseauVente().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Interne", personneCible.getReseauVente().getLibelle());
        assertEquals(CHAMP_PAS_BON, 9L, personneCible.getCsp().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "NC", personneCible.getCsp().getLibelle());
        assertEquals(CHAMP_PAS_BON, 3L, personneCible.getSitFam().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "NC", personneCible.getSitFam().getLibelle());
        assertEquals(CHAMP_PAS_BON, null, personneCible.getProfession());
        assertEquals(CHAMP_PAS_BON, 1L, personneCible.getStatut().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Actif", personneCible.getStatut().getLibelle());
        assertEquals(CHAMP_PAS_BON, 1L, personneCible.getCommercial().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "PrenomCommercial1 NomCommercial1", personneCible.getCommercial().getLibelle());
        assertEquals(CHAMP_PAS_BON, 1L, personneCible.getAgence().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Agence1", personneCible.getAgence().getLibelle());
        // Adresse principale de la cible
        controlerAdressePrincipalePersonne1(personneCible.getAdressePrincipale());
        // Liste des téléphones de la cible
        controlerListeTelephonesPersonne1(personneCible.getListeTelephones());
        // Liste des mails de la cible
        controlerListeEmailsPersonne1(personneCible.getListeEmails());
    }

    /**
     * Contrôle l'adresse principale de la personne 1.
     * @param adressePrincipalePersonne2 l'adresse principale de la personne 1
     */
    private void controlerAdressePrincipalePersonne1(AdresseFusionDto adressePrincipalePersonne1) {
        assertEquals(Messages.getString("FusionPersonneServiceTest.72"), 4L, adressePrincipalePersonne1.getIdentifiant());
        assertEquals(Messages.getString("FusionPersonneServiceTest.73"), squareMappingService.getIdNatureAdressePrincipale(),
        	adressePrincipalePersonne1.getNature().getIdentifiant());
        final String libelleAdressePrincipaleSource = "147 Rue de Limoges 16000 ANGOULEME FRANCE";
        assertEquals(Messages.getString("FusionPersonneServiceTest.75"), libelleAdressePrincipaleSource, adressePrincipalePersonne1.getLibelleAdresse());
    }

    /**
     * Contrôle l'adresse principale de la personne 2.
     * @param adressePrincipalePersonne2 l'adresse principale de la personne 2
     */
    private void controlerAdressePrincipalePersonne2(AdresseFusionDto adressePrincipalePersonne2) {
        assertEquals(Messages.getString("FusionPersonneServiceTest.76"), 2L, adressePrincipalePersonne2.getIdentifiant());
        assertEquals(Messages.getString("FusionPersonneServiceTest.77"), squareMappingService.getIdNatureAdressePrincipale(),
        		adressePrincipalePersonne2.getNature().getIdentifiant());
        final String libelleAdressePrincipaleSource = "147 Rue de Limoges 16000 ANGOULEME FRANCE";
        assertEquals(Messages.getString("FusionPersonneServiceTest.79"), libelleAdressePrincipaleSource, adressePrincipalePersonne2.getLibelleAdresse());
    }

    /**
     * Contrôle la liste des téléphones de la personne 1.
     * @param listeTelephones la liste des téléphones
     */
    private void controlerListeTelephonesPersonne1(List<TelephoneFusionDto> listeTelephones) {
        final int nbTelephones = 3;
        assertEquals(Messages.getString("FusionPersonneServiceTest.80"), nbTelephones, listeTelephones.size());
        // Vérification des téléphones en fonction de leur nature (on ne doit retrouver que les natures de téléphones de la source)
        final Long idNature1 = 1L;
        final Long idNature3 = 3L;
        final Long idNature4 = 4L;
        boolean possedeTelephoneNature1 = false;
        boolean possedeTelephoneNature3 = false;
        boolean possedeTelephoneNature4 = false;
        for (TelephoneFusionDto telephone : listeTelephones) {
            if (idNature1.equals(telephone.getNature().getIdentifiant())) {
                possedeTelephoneNature1 = true;
                assertEquals(IDENTIFIANT_TELEPHONE_PAS_BON, 4L, telephone.getIdentifiant());
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "01 01 01 01 01", telephone.getNumero());
                assertEquals(NATURE_TELEPHONE_PAS_BONNE, "Nature Téléphone 1", telephone.getNature().getLibelle());
            } else if (idNature3.equals(telephone.getNature().getIdentifiant())) {
                possedeTelephoneNature3 = true;
                assertEquals(IDENTIFIANT_TELEPHONE_PAS_BON, null, telephone.getIdentifiant());
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "", telephone.getNumero());
                assertEquals(NATURE_TELEPHONE_PAS_BONNE, "Nature Téléphone 3", telephone.getNature().getLibelle());
            } else if (idNature4.equals(telephone.getNature().getIdentifiant())) {
                possedeTelephoneNature4 = true;
                assertEquals(IDENTIFIANT_TELEPHONE_PAS_BON, 6L, telephone.getIdentifiant());
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "03 03 03 03 03", telephone.getNumero());
                assertEquals(NATURE_TELEPHONE_PAS_BONNE, "Nature Téléphone 4", telephone.getNature().getLibelle());
            }
        }
        assertTrue(Messages.getString("FusionPersonneServiceTest.87"), possedeTelephoneNature1 && possedeTelephoneNature3 && possedeTelephoneNature4);
    }

    /**
     * Contrôle la liste des téléphones (fusionnables) de la personne 2.
     * @param listeTelephones la liste des téléphones (fusionnables)
     */
    private void controlerListeTelephonesPersonne2(List<ProprieteFusionnableTelephoneDto> listeTelephones) {
        final int nbTelephones = 3;
        assertEquals(Messages.getString("FusionPersonneServiceTest.88"), nbTelephones, listeTelephones.size());
        // Vérification des téléphones en fonction de leur nature (on ne doit retrouver que les natures de téléphones de la source)
        final Long idNature1 = 1L;
        final Long idNature3 = 3L;
        final Long idNature4 = 4L;
        boolean possedeTelephoneNature1 = false;
        boolean possedeTelephoneNature3 = false;
        boolean possedeTelephoneNature4 = false;
        for (ProprieteFusionnableTelephoneDto telephoneFusionnable : listeTelephones) {
            final TelephoneFusionDto telephone = telephoneFusionnable.getValeur();
            if (idNature1.equals(telephone.getNature().getIdentifiant())) {
                possedeTelephoneNature1 = true;
                assertEquals(FLAG_DIFFERENT_PAS_BON, true, telephoneFusionnable.getDifferent());
                assertEquals(IDENTIFIANT_TELEPHONE_PAS_BON, 1L, telephone.getIdentifiant());
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "01 01 01 02 02", telephone.getNumero());
                assertEquals(NATURE_TELEPHONE_PAS_BONNE, Messages.getString("FusionPersonneServiceTest.90"), telephone.getNature().getLibelle());
            } else if (idNature3.equals(telephone.getNature().getIdentifiant())) {
                possedeTelephoneNature3 = true;
                assertEquals(FLAG_DIFFERENT_PAS_BON, true, telephoneFusionnable.getDifferent());
                assertEquals(IDENTIFIANT_TELEPHONE_PAS_BON, 2L, telephone.getIdentifiant());
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "01 02 03 04 05", telephone.getNumero());
                assertEquals(NATURE_TELEPHONE_PAS_BONNE, "Nature Téléphone 3", telephone.getNature().getLibelle());
            } else if (idNature4.equals(telephone.getNature().getIdentifiant())) {
                possedeTelephoneNature4 = true;
                assertEquals(FLAG_DIFFERENT_PAS_BON, false, telephoneFusionnable.getDifferent());
                assertEquals(IDENTIFIANT_TELEPHONE_PAS_BON, 3L, telephone.getIdentifiant());
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "03 03 03 03 03", telephone.getNumero());
                assertEquals(NATURE_TELEPHONE_PAS_BONNE, "Nature Téléphone 4", telephone.getNature().getLibelle());
            }
        }
        assertTrue(Messages.getString("FusionPersonneServiceTest.95"), possedeTelephoneNature1 && possedeTelephoneNature3 && possedeTelephoneNature4);
    }

    /**
     * Contrôle la liste des mails de la personne 1.
     * @param listeEmails la liste des téléphones
     */
    private void controlerListeEmailsPersonne1(List<EmailFusionDto> listeEmails) {
        final int nbEmails = 3;
        assertEquals(Messages.getString("FusionPersonneServiceTest.96"), nbEmails, listeEmails.size());
        // Vérification des mails en fonction de leur nature (on ne doit retrouver que les natures de mails de la source)
        final Long idNature1 = 1L;
        final Long idNature3 = 3L;
        final Long idNature4 = 4L;
        boolean possedeEmailNature1 = false;
        boolean possedeEmailNature3 = false;
        boolean possedeEmailNature4 = false;
        for (EmailFusionDto email : listeEmails) {
            if (idNature1.equals(email.getNature().getIdentifiant())) {
                possedeEmailNature1 = true;
                assertEquals(IDENTIFIANT_TELEPHONE_PAS_BON, 4L, email.getIdentifiant());
                assertEquals(ADRESSE_MAIL_PAS_BONNE, "email0101@test.net", email.getAdresse());
                assertEquals(NATURE_MAIL_PAS_BONNE, "Nature Mail 1", email.getNature().getLibelle());
            } else if (idNature3.equals(email.getNature().getIdentifiant())) {
                possedeEmailNature3 = true;
                assertEquals(IDENTIFIANT_TELEPHONE_PAS_BON, null, email.getIdentifiant());
                assertEquals(ADRESSE_MAIL_PAS_BONNE, "", email.getAdresse());
                assertEquals(NATURE_MAIL_PAS_BONNE, "Nature Mail 3", email.getNature().getLibelle());
            } else if (idNature4.equals(email.getNature().getIdentifiant())) {
                possedeEmailNature4 = true;
                assertEquals(IDENTIFIANT_TELEPHONE_PAS_BON, 6L, email.getIdentifiant());
                assertEquals(ADRESSE_MAIL_PAS_BONNE, "email0303@test.net", email.getAdresse());
                assertEquals(NATURE_MAIL_PAS_BONNE, "Nature Mail 4", email.getNature().getLibelle());
            }
        }
        assertTrue(Messages.getString("FusionPersonneServiceTest.103"), possedeEmailNature1 && possedeEmailNature3 && possedeEmailNature4);
    }

    /**
     * Contrôle la liste des mails (fusionnables) de la personne 2.
     * @param listeEmails la liste des mails (fusionnables)
     */
    private void controlerListeEmailsPersonne2(List<ProprieteFusionnableEmailDto> listeEmails) {
        final int nbEmails = 3;
        assertEquals(Messages.getString("FusionPersonneServiceTest.104"), nbEmails, listeEmails.size());
        // Vérification des mails en fonction de leur nature (on ne doit retrouver que les natures de mails de la source)
        final Long idNature1 = 1L;
        final Long idNature3 = 3L;
        final Long idNature4 = 4L;
        boolean possedeEmailNature1 = false;
        boolean possedeEmailNature3 = false;
        boolean possedeEmailNature4 = false;
        for (ProprieteFusionnableEmailDto emailFusionnable : listeEmails) {
            final EmailFusionDto email = emailFusionnable.getValeur();
            if (idNature1.equals(email.getNature().getIdentifiant())) {
                possedeEmailNature1 = true;
                assertEquals(FLAG_DIFFERENT_PAS_BON, true, emailFusionnable.getDifferent());
                assertEquals(Messages.getString("FusionPersonneServiceTest.105"), 1L, email.getIdentifiant());
                assertEquals(ADRESSE_MAIL_PAS_BONNE, "email0102@test.net", email.getAdresse());
                assertEquals(NATURE_MAIL_PAS_BONNE, "Nature Mail 1", email.getNature().getLibelle());
            } else if (idNature3.equals(email.getNature().getIdentifiant())) {
                possedeEmailNature3 = true;
                assertEquals(FLAG_DIFFERENT_PAS_BON, true, emailFusionnable.getDifferent());
                assertEquals(Messages.getString("FusionPersonneServiceTest.108"), 2L, email.getIdentifiant());
                assertEquals(ADRESSE_MAIL_PAS_BONNE, "email0105@test.net", email.getAdresse());
                assertEquals(NATURE_MAIL_PAS_BONNE, "Nature Mail 3", email.getNature().getLibelle());
            } else if (idNature4.equals(email.getNature().getIdentifiant())) {
                possedeEmailNature4 = true;
                assertEquals(FLAG_DIFFERENT_PAS_BON, false, emailFusionnable.getDifferent());
                assertEquals(Messages.getString("FusionPersonneServiceTest.111"), 3L, email.getIdentifiant());
                assertEquals(ADRESSE_MAIL_PAS_BONNE, "email0303@test.net", email.getAdresse());
                assertEquals(NATURE_MAIL_PAS_BONNE, "Nature Mail 4", email.getNature().getLibelle());
            }
        }
        assertTrue(Messages.getString("FusionPersonneServiceTest.114"), possedeEmailNature1 && possedeEmailNature3 && possedeEmailNature4);
    }

    /** Vérifie les champs de la personne envoyée au service de modification de personne lors de la validation de la fusion. */
    private void verifierPersonneModifieeApresFusion() {
        // Récupération de la personne
        final PersonneDto personneModifiee = ((PersonnePhysiqueServiceImpl) personnePhysiqueService).getPersonneModifiee();
        assertEquals(CHAMP_PAS_BON, "181101634101712", personneModifiee.getInfoSante().getNro());
        assertEquals(CHAMP_PAS_BON, 1L, personneModifiee.getCivilite().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Monsieur", personneModifiee.getCivilite().getLibelle());
        assertEquals(CHAMP_PAS_BON, "Nicolas", personneModifiee.getPrenom());
        assertEquals(CHAMP_PAS_BON, "PELTIER", personneModifiee.getNom());
        assertEquals(CHAMP_PAS_BON, null, personneModifiee.getNomJeuneFille());
        assertEquals(CHAMP_PAS_BON, getCalendarByDate("03/10/1981"), personneModifiee.getDateNaissance());
        assertEquals(CHAMP_PAS_BON, null, personneModifiee.getDateDeces());
        assertEquals(CHAMP_PAS_BON, 3L, personneModifiee.getNaturePersonne().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Adhérent", personneModifiee.getNaturePersonne().getLibelle());
        assertEquals(CHAMP_PAS_BON, false, personneModifiee.isDecede());
        assertEquals(CHAMP_PAS_BON, 1L, personneModifiee.getInfoSante().getCaisse().getId());
        assertEquals(CHAMP_PAS_BON, "Caisse 1", personneModifiee.getInfoSante().getCaisse().getNom());
        assertEquals(CHAMP_PAS_BON, 1L, personneModifiee.getInfoSante().getCaisseRegime().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Régime 1", personneModifiee.getInfoSante().getCaisseRegime().getLibelle());
        assertEquals(CHAMP_PAS_BON, 1L, personneModifiee.getSegment().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Individuel", personneModifiee.getSegment().getLibelle());
        assertEquals(CHAMP_PAS_BON, 1L, personneModifiee.getReseauVente().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Interne", personneModifiee.getReseauVente().getLibelle());
        assertEquals(CHAMP_PAS_BON, null, personneModifiee.getCsp());
        assertEquals(CHAMP_PAS_BON, 2L, personneModifiee.getSitFam().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Marié", personneModifiee.getSitFam().getLibelle());
        assertEquals(CHAMP_PAS_BON, 1L, personneModifiee.getProfession().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "NC", personneModifiee.getProfession().getLibelle());
        assertEquals(CHAMP_PAS_BON, 1L, personneModifiee.getStatut().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, "Actif", personneModifiee.getStatut().getLibelle());
        assertEquals(CHAMP_PAS_BON, 2L, personneModifiee.getCommercial().getIdentifiant());
        assertEquals(CHAMP_PAS_BON, 2L, personneModifiee.getAgence().getIdentifiant());
        // TODO Voir si nécessaire Vérification du transfert du commercial et de l'agence
        // assertTrue("Le commercial n'a pas été transféré", ((PersonneServiceImpl) personneService).isTransfertPersonneACommercialEffectue());
        // assertTrue("L'agence n'a pas été transférée", ((PersonneServiceImpl) personneService).isTransfertPersonneAAgenceEffectue());
    }

    /** Vérifie les coordonnées de la personne envoyée au service de modification de coordonnées lors de la validation de la fusion. */
    private void verifierCoordonneesModifieesApresFusion() {
        // Récupération des coordonnées modifiées
        final CoordonneesDto coordonneesModifiees = ((PersonneServiceImpl) personneService).getCoordonneesModifiees();

        // Vérification des adresses
        verifierAdressesApresFusion(coordonneesModifiees.getAdresses());
        // Vérification des téléphones
        verifierTelephonesApresFusion(coordonneesModifiees.getTelephones());
        // Vérification des mails
        verifierEmailsApresFusion(coordonneesModifiees.getEmails());
    }

    /**
     * Vérifie la liste des adresses de la personne fusionnée.
     * @param listeAdresses la liste des adresses
     */
    private void verifierAdressesApresFusion(List<AdresseDto> listeAdresses) {
        final int nbAdresses = 2;
        final Long idAdresse4 = 4L;
        final Long idAdresse5 = 5L;
        boolean possedeAdresse4 = false;
        boolean possedeAdresse5 = false;
        assertEquals(Messages.getString("FusionPersonneServiceTest.128"), nbAdresses, listeAdresses.size());
        // Parcours des adresses pour vérifier que l'adresse principale a été modifiée
        for (AdresseDto adresse : listeAdresses) {
            if (idAdresse4.equals(adresse.getIdentifiant())) {
                possedeAdresse4 = true;
                assertEquals(Messages.getString("FusionPersonneServiceTest.129"), squareMappingService.getIdNatureAdressePrincipale(), adresse.getNature()
                        .getIdentifiant());
                assertEquals(Messages.getString("FusionPersonneServiceTest.130"), getCalendarByDate("01/01/2000"), adresse.getDateDebut());
                assertEquals(Messages.getString("FusionPersonneServiceTest.132"), null, adresse.getDateFin());
                assertEquals(Messages.getString("FusionPersonneServiceTest.133"), "de Limoges", adresse.getNomVoie());
                assertEquals(Messages.getString("FusionPersonneServiceTest.135"), 1L, adresse.getCodePostalCommune().getIdentifiant());
            } else if (idAdresse5.equals(adresse.getIdentifiant())) {
                possedeAdresse5 = true;
                assertEquals(Messages.getString("FusionPersonneServiceTest.136"), squareMappingService.getIdNatureAdresseSecondaire(), adresse.getNature()
                        .getIdentifiant());
                assertEquals(Messages.getString("FusionPersonneServiceTest.137"), getCalendarByDate("01/01/2000"), adresse.getDateDebut());
                assertEquals(Messages.getString("FusionPersonneServiceTest.139"), null, adresse.getDateFin());
                assertEquals(Messages.getString("FusionPersonneServiceTest.140"), "de la Loge", adresse.getNomVoie());
                assertNotNull(Messages.getString("FusionPersonneServiceTest.142"), adresse.getCodePostalCommune());
                assertEquals(Messages.getString("FusionPersonneServiceTest.143"), 2L, adresse.getCodePostalCommune().getIdentifiant());
            }
        }
        assertTrue(Messages.getString("FusionPersonneServiceTest.144"), possedeAdresse4 && possedeAdresse5);
    }

    /**
     * Vérifie la liste des téléphones de la personne fusionnée.
     * @param listeTelephones la liste des téléphones
     */
    private void verifierTelephonesApresFusion(List<TelephoneDto> listeTelephones) {
        // 3 téléphones : le 4 a un nouveau numéro
        final int nbTelephones = 3;
        final Long idTelephone4 = 4L;
        final Long idTelephone5 = 5L;
        final Long idTelephone6 = 6L;
        boolean possedeTelephone4 = false;
        boolean possedeTelephone5 = false;
        boolean possedeTelephone6 = false;
        assertEquals(Messages.getString("FusionPersonneServiceTest.145"), nbTelephones, listeTelephones.size());
        for (TelephoneDto telephone : listeTelephones) {
            if (idTelephone4.equals(telephone.getId())) {
                possedeTelephone4 = true;
                assertEquals(Messages.getString("FusionPersonneServiceTest.146"), "01 01 01 02 02", telephone.getNumero());
                assertEquals(Messages.getString("FusionPersonneServiceTest.148"), 1L, telephone.getNature().getIdentifiant());
            } else if (idTelephone5.equals(telephone.getId())) {
                possedeTelephone5 = true;
                assertEquals(Messages.getString("FusionPersonneServiceTest.149"), "02 02 02 02 02", telephone.getNumero());
                assertEquals(Messages.getString("FusionPersonneServiceTest.151"), 2L, telephone.getNature().getIdentifiant());
            } else if (idTelephone6.equals(telephone.getId())) {
                possedeTelephone6 = true;
                assertEquals(Messages.getString("FusionPersonneServiceTest.152"), "03 03 03 03 03", telephone.getNumero());
                assertEquals(Messages.getString("FusionPersonneServiceTest.154"), 4L, telephone.getNature().getIdentifiant());
            }
        }
        assertTrue(Messages.getString("FusionPersonneServiceTest.155"), possedeTelephone4 && possedeTelephone5 && possedeTelephone6);
    }

    /**
     * Vérifie la liste des mails de la personne fusionnée.
     * @param listeEmails la liste des mails
     */
    private void verifierEmailsApresFusion(List<EmailDto> listeEmails) {
        // 4 mails : mail de nature 3 supplémentaire
        final int nbEmails = 4;
        final Long idEmail4 = 4L;
        final Long idEmail5 = 5L;
        final Long idEmail6 = 6L;
        final Long idNature3 = 3L;
        boolean possedeEmail4 = false;
        boolean possedeEmail5 = false;
        boolean possedeEmail6 = false;
        boolean possedeEmailNature3 = false;
        assertEquals(Messages.getString("FusionPersonneServiceTest.156"), nbEmails, listeEmails.size());
        for (EmailDto email : listeEmails) {
            if (idEmail4.equals(email.getIdentifiant())) {
                possedeEmail4 = true;
                assertEquals(Messages.getString("FusionPersonneServiceTest.157"), "email0101@test.net", email.getAdresse());
                assertEquals(NATURE_MAIL_PAS_BONNE, 1L, email.getNatureEmail().getIdentifiant());
            } else if (idEmail5.equals(email.getIdentifiant())) {
                possedeEmail5 = true;
                assertEquals(Messages.getString("FusionPersonneServiceTest.159"), "email0202@test.net", email.getAdresse());
                assertEquals(NATURE_MAIL_PAS_BONNE, 2L, email.getNatureEmail().getIdentifiant());
            } else if (idEmail6.equals(email.getIdentifiant())) {
                possedeEmail6 = true;
                assertEquals(Messages.getString("FusionPersonneServiceTest.161"), "email0303@test.net", email.getAdresse());
                assertEquals(NATURE_MAIL_PAS_BONNE, 4L, email.getNatureEmail().getIdentifiant());
            } else if (idNature3.equals(email.getNatureEmail().getIdentifiant())) {
                possedeEmailNature3 = true;
                assertNull(Messages.getString("FusionPersonneServiceTest.163"), email.getIdentifiant());
                assertEquals(NATURE_MAIL_PAS_BONNE, "email0105@test.net", email.getAdresse());
            }
        }
        assertTrue(Messages.getString("FusionPersonneServiceTest.165"), possedeEmail4 && possedeEmail5 && possedeEmail6 && possedeEmailNature3);
    }

    /**
     * Construit un calendar à partir d'une date de la forme "dd/MM/yyyy".
     * @param date la date
     * @return l'objet Calendar
     */
    private static Calendar getCalendarByDate(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(Messages.getString("FusionPersonneServiceTest.11"));
        try {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            return calendar;
        } catch (ParseException e) {
            throw new TechnicalException(Messages.getString("FusionPersonneServiceTest.167") + date);
        }
    }

}
