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
package com.square.core.service.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.ActionDto;
import com.square.core.model.dto.ActionRechercheDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.OpportuniteCriteresRechercheDto;
import com.square.core.model.dto.OpportuniteDto;
import com.square.core.model.dto.OpportuniteMaJStatutDto;
import com.square.core.model.dto.OpportuniteModificationDto;
import com.square.core.model.dto.OpportuniteSimpleDto;
import com.square.core.model.exception.ConfirmationCreationOpportuniteException;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.OpportuniteKeyUtil;

/**
 * Test sur les opportunités.
 * @author cblanchard - SCUB
 */
public class OpportuniteServiceTest extends DbunitBaseTestCase {

    /** Le service des opportunités. */
    private OpportuniteService opportuniteService;

    /** Le service des actions. */
    private ActionService actionService;

    /** Le service de mapping Square. */
    private SquareMappingService squareMappingService;

    /** MessageSourceUtil. */
    private MessageSourceUtil messageSourceUtil;

    /** Message d'erreur lorsque le message attendu ne correspond pas. */
    private static String messageErreurMauvaisMessage = Messages.getString("OpportuniteServiceTest.0");

    /** Message d'erreur pour les fail. */
    private static String messageFail = Messages.getString("OpportuniteServiceTest.1");

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"squareMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "datasetOpportunite.xml";
    }

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        opportuniteService = (OpportuniteService) getBeanSpring("opportuniteService");
        actionService = (ActionService) getBeanSpring("actionService");
        squareMappingService = (SquareMappingService) getBeanSpring("squareMappingService");
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        createSecureContext("user", "user");
    }

    @Override
    protected String[] getIncludeTableOnDeleteOperation() {
        return new String[] {"DATA_ACTION"};
    }

    /**
     * Tests sur les erreurs de l'opportunité.
     */
    @Test
    public void testErreurCreationOpportunite() {
        // Test avec un dto null
        try {
            opportuniteService.creerOpportunite(null);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, be.getMessage(), messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_DTO_CREATION_NULL));
        }
        // Test avec aucune champ de renseigné
        try {
            opportuniteService.creerOpportunite(new OpportuniteDto());
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, 5, ce.getRapport().getRapports().size());
        }

        // Création des données de tests
        final OpportuniteDto opportuniteDto = new OpportuniteDto();
        opportuniteDto.setIdActionSource(null);
        opportuniteDto.setIdAgence(1L);
        opportuniteDto.setIdCampagne(null);
        opportuniteDto.setIdNature(1L);
        opportuniteDto.setIdObjet(1L);
        opportuniteDto.setIdOpportunite(null);
        opportuniteDto.setIdPersonnePhysique(1L);
        opportuniteDto.setIdRessource(1L);
        opportuniteDto.setIdSousObjet(1L);
        opportuniteDto.setIdType(1L);
        opportuniteDto.setCreationForcee(null);
        final Long mauvaisId = 99L;
        final Long idPersonneNatureDecedee = 14L;
        final Long idPersonneFlagDecedee = 15L;
        // Test sans nature de renseigné
        opportuniteDto.setIdNature(null);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_NATURE_NULL), ce.getRapport().getRapports()
                    .get("OpportuniteDto.idNature").getMessage());
        }
        // Test avec une mauvaise nature
        opportuniteDto.setIdNature(mauvaisId);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, be.getMessage(), messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_NATURE_INEXISTANT));
        }
        // Test avec une mauvaise campagne
        opportuniteDto.setIdNature(1L);
        opportuniteDto.setIdCampagne(mauvaisId);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, be.getMessage(), messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_CAMPAGNE_INEXISTANTE));
        }
        // Test avec une mauvaise agence
        opportuniteDto.setIdCampagne(null);
        opportuniteDto.setIdAgence(mauvaisId);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_AGENCE_INEXISTANTE, new String[] {mauvaisId
                    .toString()}), be.getMessage());
        }
        // Test avec une mauvaise ressource
        opportuniteDto.setIdAgence(1L);
        opportuniteDto.setIdRessource(mauvaisId);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_RESSOURCE_INEXISTANTE), be.getMessage());
        }
        // Test sans personne
        opportuniteDto.setIdRessource(null);
        opportuniteDto.setIdPersonnePhysique(null);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_PERSONNE_NULL), be.getMessage());
        }
        // Test avec une personne inexistante
        opportuniteDto.setIdPersonnePhysique(mauvaisId);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_PERSONNE_INEXISTANTE), be.getMessage());
        }
        // Test avec une personne décédée (sur nature)
        opportuniteDto.setIdPersonnePhysique(idPersonneNatureDecedee);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_PERSONNE_DECEDEE), be.getMessage());
        }
        // Test avec une personne décédée (sur flag décédé)
        opportuniteDto.setIdPersonnePhysique(idPersonneFlagDecedee);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_PERSONNE_DECEDEE), be.getMessage());
        }
        // Test avec une famille non éligible
        opportuniteDto.setIdPersonnePhysique(9L);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, be.getMessage(), messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_FAMILLE_NON_ELIGIBLE));
            opportuniteDto.setIdPersonnePhysique(1L);
        }
        // Test sans type
        opportuniteDto.setIdPersonnePhysique(1L);
        opportuniteDto.setIdType(null);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_TYPE_NULL), ce.getRapport().getRapports()
                    .get("OpportuniteDto.idType").getMessage());
        }
        // Test avec un type inexistant
        opportuniteDto.setIdType(mauvaisId);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_TYPE_INEXISTANT), be.getMessage());
        }
        // Test sans objet
        opportuniteDto.setIdType(1L);
        opportuniteDto.setIdObjet(null);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_OBJET_NULL), ce.getRapport().getRapports()
                    .get("OpportuniteDto.idObjet").getMessage());
        }
        // Test avec un objet inexistant
        opportuniteDto.setIdObjet(mauvaisId);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_OBJET_INEXISTANT), be.getMessage());
        }
        // Test avec un sous objet inexistant
        opportuniteDto.setIdObjet(1L);
        opportuniteDto.setIdSousObjet(mauvaisId);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_SOUS_OBJET_INEXISTANT), be.getMessage());
        }
        // Test avec une action source inexistante
        opportuniteDto.setIdSousObjet(null);
        opportuniteDto.setIdActionSource(mauvaisId);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_ACTION_SOURCE_INEXISTANTE), be.getMessage());
        }

        // Test avec une personne ayant déjà opportunité active
        opportuniteDto.setIdActionSource(null);
        opportuniteDto.setIdPersonnePhysique(2L);
        opportuniteDto.setCreationForcee(null);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (ConfirmationCreationOpportuniteException ccoe) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_CONFIRMATION_CREATION_OPPORTUNITE), ccoe.getMessage());
        }
        // Test avec une opportunité sur l'action source
        opportuniteDto.setIdActionSource(1L);
        try {
            opportuniteService.creerOpportunite(opportuniteDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_DEJA_ACTIVE), be.getMessage());
        }
    }

    /**
     * Test de la récupération d'opportunité.
     */
    @Test
    public void testRechercheOpportunite() {
        // Test avec un dto de critère null
        try {
            opportuniteService.rechercherOpportuniteParCriteres(null);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_DTO_CRITERE_RECHERCHE_NULL), be.getMessage());
        }

        // Récupération des opportunités
        OpportuniteCriteresRechercheDto criteres = new OpportuniteCriteresRechercheDto();
        criteres.setIdPersonnePhysique(4L);
        List<OpportuniteSimpleDto> resultat = opportuniteService.rechercherOpportuniteParCriteres(criteres);
        // Vérification de l'odre
        assertNotNull(Messages.getString("OpportuniteServiceTest.14"), resultat);
        assertEquals(Messages.getString("OpportuniteServiceTest.15"), 3, resultat.size());
        assertEquals(Messages.getString("OpportuniteServiceTest.16"), 4L, resultat.get(0).getIdOpportunite());
        assertEquals(Messages.getString("OpportuniteServiceTest.17"), 2L, resultat.get(1).getIdOpportunite());
        assertEquals(Messages.getString("OpportuniteServiceTest.18"), 3L, resultat.get(2).getIdOpportunite());

        // Vérification des données récupérées
        assertEquals(Messages.getString("OpportuniteServiceTest.19"), 3, resultat.get(0).getDateCreation().get(Calendar.DAY_OF_MONTH));
        assertEquals(Messages.getString("OpportuniteServiceTest.20"), "nom1 prenom1", resultat.get(0).getCreateur().getLibelle());
        assertEquals(Messages.getString("OpportuniteServiceTest.22"), "Active", resultat.get(0).getStatut().getLibelle());
        assertNotNull(Messages.getString("OpportuniteServiceTest.24"), resultat.get(0).getRessource());
        assertEquals(Messages.getString("OpportuniteServiceTest.25"), 1L, resultat.get(0).getRessource().getIdentifiant());
        assertNotNull(Messages.getString("OpportuniteServiceTest.26"), resultat.get(0).getAgence());
        assertEquals(Messages.getString("OpportuniteServiceTest.27"), 1L, resultat.get(0).getAgence().getIdentifiant());
        assertEquals(Messages.getString("OpportuniteServiceTest.28"), "1000", resultat.get(0).getAgence().getIdentifiantExterieur());
        assertEquals(Messages.getString("OpportuniteServiceTest.30"), "nom1", resultat.get(0).getRessource().getNom());
        assertEquals(Messages.getString("OpportuniteServiceTest.32"), "prenom1", resultat.get(0).getRessource().getPrenom());
        assertEquals(Messages.getString("OpportuniteServiceTest.34"), "1500", resultat.get(0).getRessource().getIdentifiantExterieur());
        assertEquals(Messages.getString("OpportuniteServiceTest.36"), "1500", resultat.get(0).getCreateur().getIdentifiantExterieur());
        assertEquals(Messages.getString("OpportuniteServiceTest.38"), "Type 2", resultat.get(0).getType());
        assertEquals(Messages.getString("OpportuniteServiceTest.40"), "Objet 2", resultat.get(0).getObjet());
        assertEquals(Messages.getString("OpportuniteServiceTest.42"), "Sous objet 2", resultat.get(0).getSousObjet());

        // Recherche sur l'identifiant de l'opportunité et de son responsable
        criteres = new OpportuniteCriteresRechercheDto();
        final List<Long> listeIdsOpps = new ArrayList<Long>();
        listeIdsOpps.add(1L);
        listeIdsOpps.add(6L);
        criteres.setListeIds(listeIdsOpps);
        criteres.setIdResponsable(2L);
        resultat = opportuniteService.rechercherOpportuniteParCriteres(criteres);
        assertEquals(Messages.getString("OpportuniteServiceTest.44"), 1, resultat.size());
        assertEquals(Messages.getString("OpportuniteServiceTest.45"), 6L, resultat.get(0).getIdOpportunite());
    }

    /**
     * Test sur la mise a jour du statut d'une opportunité.
     */
    @Test
    public void testMaJStatutOpportunite() {
        final Long idOpportunite = 1L;
        final Long idStatut = 1L;

        final OpportuniteMaJStatutDto opportuniteMaJStatutDto = new OpportuniteMaJStatutDto();
        try {
            opportuniteMaJStatutDto.setIdOpportunite(null);
            opportuniteService.mettreAJourStatutOpportunite(opportuniteMaJStatutDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_ID_OPP_NULL), be.getMessage());
        }
        // Test avec un statut null
        try {
            opportuniteMaJStatutDto.setIdOpportunite(idOpportunite);
            opportuniteMaJStatutDto.setStatut(null);
            opportuniteService.mettreAJourStatutOpportunite(opportuniteMaJStatutDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_STATUT_NULL), be.getMessage());
        }
        // Test avec un statut inexistant
        try {
            final Long mauvaisId = 99L;
            opportuniteMaJStatutDto.setStatut(new IdentifiantLibelleDto(mauvaisId));
            opportuniteService.mettreAJourStatutOpportunite(opportuniteMaJStatutDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPPORTUNITE_STATUT_INEXISTANT), be.getMessage());
        }
        opportuniteMaJStatutDto.setStatut(new IdentifiantLibelleDto(idStatut));
        opportuniteService.mettreAJourStatutOpportunite(opportuniteMaJStatutDto);

        // Vérification des modifications
        final OpportuniteCriteresRechercheDto criteres = new OpportuniteCriteresRechercheDto();
        final List<Long> listeIdsOpportunites = new ArrayList<Long>();
        listeIdsOpportunites.add(idOpportunite);
        criteres.setListeIds(listeIdsOpportunites);
        final List<OpportuniteSimpleDto> resultat = opportuniteService.rechercherOpportuniteParCriteres(criteres);
        assertNotNull(Messages.getString("OpportuniteServiceTest.46"), resultat);
        assertEquals(Messages.getString("OpportuniteServiceTest.47"), 1, resultat.size());
        assertEquals(Messages.getString("OpportuniteServiceTest.48"), idStatut, resultat.get(0).getStatut().getIdentifiant());
    }

    /**
     * Test sur la modification d'une opportunité.
     */
    @Test
    public void testModificationOpportunite() {
        // Test avec un dto null
        try {
            opportuniteService.modifierOpportunite(null);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_MODIFICATION_DTO_NULL), be.getMessage());
        }

        // Test avec un identifiant d'opportunité null
        OpportuniteModificationDto opportuniteModificationDto = new OpportuniteModificationDto();
        opportuniteModificationDto.setIdOpportunite(1L);
        DimensionRessourceDto ressource = new DimensionRessourceDto();
        ressource.setIdentifiant(1L);
        opportuniteModificationDto.setRessource(ressource);
        final Long mauvaisId = 99L;
        try {
            opportuniteModificationDto.setIdOpportunite(null);
            opportuniteService.modifierOpportunite(opportuniteModificationDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_ID_OPP_NULL), be.getMessage());
        }
        // Test avec un identifiant d'opportunité inexistant
        try {
            opportuniteModificationDto.setIdOpportunite(mauvaisId);
            opportuniteService.modifierOpportunite(opportuniteModificationDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_ID_OPP_INEXISTANT), be.getMessage());
        }
        // Test avec sans agence et sans ressource
        try {
            opportuniteModificationDto.setAgence(null);
            opportuniteModificationDto.setRessource(null);
            opportuniteService.modifierOpportunite(opportuniteModificationDto);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            // Vérification que les deux exceptions ont été levées
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_RESSOURCE_NULL), ce.getRapport()
                    .getRapports().get(Messages.getString("OpportuniteServiceTest.49")).getMessage());
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_AGENGE_RESSOURCE_NULL), ce.getRapport()
                    .getRapports().get(Messages.getString("OpportuniteServiceTest.50")).getMessage());
        }
        // Test avec une agence inexistante
        try {
            opportuniteModificationDto.setAgence(new IdentifiantLibelleDto(mauvaisId));
            opportuniteModificationDto.setRessource(ressource);
        }
        catch (TechnicalException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_AGENCE_INEXISTANTE, new String[] {String
                    .valueOf(mauvaisId)}), be.getMessage());
        }
        // Test avec une ressource inexistante
        try {
            opportuniteModificationDto.setIdOpportunite(1L);
            opportuniteModificationDto.setAgence(new IdentifiantLibelleDto(1L));
            ressource.setIdentifiant(mauvaisId);
            opportuniteModificationDto.setRessource(ressource);
            opportuniteService.modifierOpportunite(opportuniteModificationDto);
            fail(messageFail);
        }
        catch (TechnicalException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_RESSOURCE_INEXISTANTE, new String[] {String
                    .valueOf(mauvaisId)}), be.getMessage());
        }
        // Modification
        opportuniteModificationDto = new OpportuniteModificationDto();
        opportuniteModificationDto.setIdOpportunite(1L);
        ressource = new DimensionRessourceDto();
        ressource.setIdentifiant(2L);
        opportuniteModificationDto.setRessource(ressource);
        opportuniteModificationDto.setAgence(new IdentifiantLibelleDto(1L));
        opportuniteService.modifierOpportunite(opportuniteModificationDto);
        // Vérification des modifications
        final OpportuniteCriteresRechercheDto criteres = new OpportuniteCriteresRechercheDto();
        criteres.setIdPersonnePhysique(2L);
        List<OpportuniteSimpleDto> resultat = opportuniteService.rechercherOpportuniteParCriteres(criteres);
        assertNotNull(Messages.getString("OpportuniteServiceTest.51"), resultat);
        assertEquals(Messages.getString("OpportuniteServiceTest.52"), 1, resultat.size());
        assertEquals(Messages.getString("OpportuniteServiceTest.53"), 2L, resultat.get(0).getRessource().getIdentifiant());

        // Test avec un agence mais pas de ressource
        opportuniteModificationDto.setAgence(new IdentifiantLibelleDto(2L));
        opportuniteModificationDto.setRessource(null);
        opportuniteService.modifierOpportunite(opportuniteModificationDto);
        resultat = opportuniteService.rechercherOpportuniteParCriteres(criteres);
        assertEquals(Messages.getString("OpportuniteServiceTest.54"), 2l, resultat.get(0).getAgence().getIdentifiant());
        assertNull(Messages.getString("OpportuniteServiceTest.55"), resultat.get(0).getRessource());

        // Test avec une ressource mais pas d'agence
        opportuniteModificationDto.setAgence(null);
        ressource.setIdentifiant(2L);
        opportuniteModificationDto.setRessource(ressource);
        opportuniteService.modifierOpportunite(opportuniteModificationDto);
        resultat = opportuniteService.rechercherOpportuniteParCriteres(criteres);
        assertEquals(Messages.getString("OpportuniteServiceTest.56"), 2L, resultat.get(0).getAgence().getIdentifiant());
        assertEquals(Messages.getString("OpportuniteServiceTest.57"), 2L, resultat.get(0).getRessource().getIdentifiant());
    }

    /**
     * Test sur la détection de la possession d'opportunité d'une personne.
     */
    @Test
    public void testHasPersonneOpportunite() {
        final Long idPersonneSansOpportunite = 1L;
        final Long idPersonneAvecOpportunite = 2L;
        assertFalse(Messages.getString("OpportuniteServiceTest.58"), opportuniteService.hasPersonneOpportunite(idPersonneSansOpportunite));
        assertTrue(Messages.getString("OpportuniteServiceTest.59"), opportuniteService.hasPersonneOpportunite(idPersonneAvecOpportunite));
    }

    /** Test du service de transfert d'opportunité. */
    @Test
    public void testTransfererOpportunites() {
        final Long idPersonneSource = 4L;
        final Long idPersonneCible = 2L;
        final Long idPersonneCibleInexistante = 100L;

        // Test sans la personne source
        try {
            opportuniteService.transfererOpportunites(null, idPersonneCible);
            fail(Messages.getString("OpportuniteServiceTest.60"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("OpportuniteServiceTest.61"), messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_TRANSFERT_PERSONNE_SOURCE_NULL), e
                    .getMessage());
        }

        // Test sans la personne cible
        try {
            opportuniteService.transfererOpportunites(idPersonneSource, null);
            fail(Messages.getString("OpportuniteServiceTest.62"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("OpportuniteServiceTest.63"), messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_TRANSFERT_PERSONNE_CIBLE_NULL), e
                    .getMessage());
        }

        // Test avec une personne cible inexistante
        try {
            opportuniteService.transfererOpportunites(idPersonneSource, idPersonneCibleInexistante);
            fail(Messages.getString("OpportuniteServiceTest.64"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("OpportuniteServiceTest.65"), messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_TRANSFERT_PERSONNE_CIBLE_INEXISTANTE), e
                    .getMessage());
        }

        // Test du transfert
        final int nbOpportunitesSourceAvantTransfert = 3;
        final int nbOpportunitesCibleAvantTransfert = 1;
        final OpportuniteCriteresRechercheDto criteresSource = new OpportuniteCriteresRechercheDto();
        criteresSource.setIdPersonnePhysique(idPersonneSource);
        final OpportuniteCriteresRechercheDto criteresCible = new OpportuniteCriteresRechercheDto();
        criteresCible.setIdPersonnePhysique(idPersonneCible);
        assertEquals(Messages.getString("OpportuniteServiceTest.66"), nbOpportunitesSourceAvantTransfert, opportuniteService
                .rechercherOpportuniteParCriteres(criteresSource).size());
        assertEquals(Messages.getString("OpportuniteServiceTest.67"), nbOpportunitesCibleAvantTransfert, opportuniteService
                .rechercherOpportuniteParCriteres(criteresCible).size());
        // Appel du service de transfert
        opportuniteService.transfererOpportunites(idPersonneSource, idPersonneCible);
        // Vérification du transfert
        final int nbOpportunitesSourceApresTransfert = 0;
        final int nbOpportunitesCibleApresTransfert = 4;
        assertEquals(Messages.getString("OpportuniteServiceTest.68"), nbOpportunitesSourceApresTransfert, opportuniteService
                .rechercherOpportuniteParCriteres(criteresSource).size());
        assertEquals(Messages.getString("OpportuniteServiceTest.69"), nbOpportunitesCibleApresTransfert, opportuniteService
                .rechercherOpportuniteParCriteres(criteresCible).size());
    }

    /** Test de l'égibilité d'une famille pour la création d'une opportunité. */
    @Test
    public void testIsFamilleEligiblePourOpportunite() {
        final Long idPersonne5 = 5L;
        final Long idPersonne7 = 7L;
        final Long idPersonne9 = 9L;
        final Long idPersonne12 = 12L;
        assertTrue(Messages.getString("OpportuniteServiceTest.70"), opportuniteService.isFamilleEligiblePourOpportunite(idPersonne5));
        assertTrue(Messages.getString("OpportuniteServiceTest.71"), opportuniteService.isFamilleEligiblePourOpportunite(idPersonne7));
        assertFalse(Messages.getString("OpportuniteServiceTest.72"), opportuniteService.isFamilleEligiblePourOpportunite(idPersonne9));
        assertTrue(Messages.getString("OpportuniteServiceTest.73"), opportuniteService.isFamilleEligiblePourOpportunite(idPersonne12));
    }

    /** Test de la modification du statut de l'opportunité avec modification des actions de relance. */
    @Test
    public void testMaJStatutOpportuniteActionRelance() {
        final Long idOpportunite5 = 5L;
        // Récupération des actions de type relance "A faire" de l'opportunité
        final ActionCritereRechercheDto criteresActions = new ActionCritereRechercheDto();
        criteresActions.setIdOpportunite(idOpportunite5);
        criteresActions.setIdType(squareMappingService.getIdTypeActionRelance());
        final List<Long> listeStatutsAction = new ArrayList<Long>();
        listeStatutsAction.add(squareMappingService.getIdStatutAFaire());
        criteresActions.setListeStatuts(listeStatutsAction);
        final RemotePagingCriteriasDto<ActionCritereRechercheDto> remoteCriteriaAction =
            new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteresActions, 0, Integer.MAX_VALUE);
        List<ActionRechercheDto> listeActions = actionService.rechercherActionParCritere(remoteCriteriaAction).getListResults();
        assertEquals(Messages.getString("OpportuniteServiceTest.74"), 1, listeActions.size());
        // Appel du service de modification de l'opportunité
        final OpportuniteMaJStatutDto opportuniteMaJStatutDto = new OpportuniteMaJStatutDto();
        opportuniteMaJStatutDto.setIdOpportunite(idOpportunite5);
        opportuniteMaJStatutDto.setStatut(new IdentifiantLibelleDto(squareMappingService.getIdStatutOpportuniteTransforme()));
        opportuniteService.mettreAJourStatutOpportunite(opportuniteMaJStatutDto);
        // Récupération des actions de type relance "A faire" de l'opportunité
        listeActions = actionService.rechercherActionParCritere(remoteCriteriaAction).getListResults();
        assertEquals(Messages.getString("OpportuniteServiceTest.75"), 0, listeActions.size());
        // Récupération des actions de type relance "Terminé" de l'opportunité
        criteresActions.getListeStatuts().clear();
        criteresActions.getListeStatuts().add(squareMappingService.getIdStatutTerminer());
        listeActions = actionService.rechercherActionParCritere(remoteCriteriaAction).getListResults();
        assertEquals(Messages.getString("OpportuniteServiceTest.76"), 1, listeActions.size());
    }

    /**
     * Teste la suppression d'une opportunite.
     */
    @Test
    public void testSuppressionOpportunite() {
        final Long idOpportunite5 = 5L;
        opportuniteService.supprimerOpportunite(idOpportunite5);
        final OpportuniteCriteresRechercheDto critere = new OpportuniteCriteresRechercheDto();
        critere.setIdentifiantExterieur("idExt5");
        final List<OpportuniteSimpleDto> resultat = opportuniteService.rechercherOpportuniteParCriteres(critere);
        assertEquals(Messages.getString("OpportuniteServiceTest.78"), resultat.size(), 0);
        final ActionDto action = actionService.rechercherActionParIdentifiantExterieur("123");
        assertEquals(Messages.getString("OpportuniteServiceTest.80"), action, null);
    }

    /** Test du service hasPersonneOpportuniteByStatuts. */
    @Test
    public void tesHasPersonneOpportuniteByStatuts() {
        // Personne inexistante
        final Long idPersonneInexistante = 10L;
        List<Long> listeIdsStatuts = new ArrayList<Long>();
        listeIdsStatuts.add(2L);
        boolean result = opportuniteService.hasPersonneOpportuniteByStatuts(idPersonneInexistante, listeIdsStatuts);
        assertFalse(Messages.getString("OpportuniteServiceTest.81"), result);

        // Personne avec une opportunité existante
        final Long idPersonne = 4L;
        result = opportuniteService.hasPersonneOpportuniteByStatuts(idPersonne, listeIdsStatuts);
        assertTrue(Messages.getString("OpportuniteServiceTest.82"), result);

        // Personne avec une opportunité supprimée
        listeIdsStatuts = new ArrayList<Long>();
        listeIdsStatuts.add(3L);
        result = opportuniteService.hasPersonneOpportuniteByStatuts(idPersonne, listeIdsStatuts);
        assertFalse(Messages.getString("OpportuniteServiceTest.83"), result);
    }
}
