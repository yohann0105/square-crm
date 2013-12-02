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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.CoreRunTimeException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.dao.interfaces.RelationDao;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.Relation;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.AdresseSimpleDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.PaysSimpleDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonnePhysiqueRelationDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.model.dto.SousRapportDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.model.exception.ConfirmationDesactivationEserviceException;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.PersonneKeyUtil;
import com.square.core.util.PersonnePhysiqueKeyUtil;

/**
 * Test autour des services de personne.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneServiceTest extends DbunitBaseTestCase {

    private static final String ORDRE_PAS_BON = Messages.getString("PersonneServiceTest.0");

    private static final String EXCEPTION_PAS_BONNE = Messages.getString("PersonneServiceTest.1");

    private static final String A_POUR_CONJOINT = Messages.getString("PersonneServiceTest.2");

    private PersonneService personneService;

    private PersonnePhysiqueService personnePhysiqueService;

    private MessageSourceUtil messageSourceUtil;

    private SquareMappingService squareMappingService;

    private RelationDao relationDao;

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        personneService = (PersonneService) getBeanSpring("personneService");
        personnePhysiqueService = (PersonnePhysiqueService) getBeanSpring("personnePhysiqueService");
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        squareMappingService = (SquareMappingService) getBeanSpring("squareMappingService");
        relationDao = (RelationDao) getBeanSpring("relationDao");
        createSecureContext("user", "user");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"squareMappingContext.xml"};
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class[] getClassManualIndexChanges() {
        return new Class[] {PersonnePhysique.class};
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected String getXmlDateSet() {
        return "datasetPersonneService.xml";
    }

    /**
     * Test de la recherche de relation.
     */
    @Test
    public void testRechercherRelation() {
        // Test de la recherche sans identifiant
        RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();

//        // RECUPERER TOUTES LES RELATIONS DE LA PERSONNE
//        criteres = new RelationCriteresRechercheDto();
//        criteres.setIdPersonne(1L);
        RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
        RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> result = personneService.rechercherRelationsParCritreres(criterias);
//        assertNotNull("resultat sans criteres ne devrait pas être null", result);
//        assertEquals("le nombre sans criteres de résultat ne correspond pas", 4, result.getTotalResults());
//        assertEquals("le nombre sans criteres de résultat ne correspond pas", 4, result.getListResults().size());
//
//        // FILTRE SUR LE GROUPEMENT
//        criteres = new RelationCriteresRechercheDto();
//        criteres.setIdPersonne(1L);
//        List<Long> listeGroupements = new ArrayList<Long>();
//        listeGroupements.add(2L);
//        criteres.setGroupements(listeGroupements);
//        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
//        result = personneService.rechercherRelationsParCritreres(criterias);
//        assertNotNull("le résultat est null pour la rechercher sur le groupement", result);
//        assertEquals("le nombre de résultat ne correspond pas pour la recherche sur le groupement", 1, result.getListResults().size());
//
//        criteres = new RelationCriteresRechercheDto();
//        criteres.setIdPersonne(1L);
//        listeGroupements = new ArrayList<Long>();
//        listeGroupements.add(1L);
//        criteres.setGroupements(listeGroupements);
//        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
//        result = personneService.rechercherRelationsParCritreres(criterias);
//        assertNotNull("le résultat est null", result);
//        assertEquals("le nombre de résultat ne correspond pas", 3, result.getListResults().size());
//
//        // FILTRE UNE PERSONNE DANS LES RELATIONS
//        criteres = new RelationCriteresRechercheDto();
//        criteres.setIdPersonne(3L);
//        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
//        result = personneService.rechercherRelationsParCritreres(criterias);
//        assertNotNull("le résultat est null", result);
//        assertEquals("le nombre de résultat ne correspond pas", 3, result.getListResults().size());
//
//        // TEST DU MAPPING
//        criteres = new RelationCriteresRechercheDto();
//        criteres.setIdPersonne(3L);
//        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
//        result = personneService.rechercherRelationsParCritreres(criterias);
//        assertNotNull("le résultat est null", result);
//        assertEquals("le nombre de résultat ne correspond pas", 3, result.getListResults().size());
//        RelationInfosDto<? extends PersonneRelationDto> relation = result.getListResults().get(0);
//        assertNotNull("le type de la relation ne devrait pas être null", relation.getType());
//        assertEquals("identifaint de la relation du type incorrecte", relation.getType().getIdentifiant(), 3L);
//        assertEquals("libelle de la relation du type incorrecte", relation.getType().getLibelle(), "a pour employe");
//        assertNotNull("la date ne devrait pas être null", relation.getDateDebut());
//        assertEquals("identifaint du groupement incorrecte", relation.getGroupement().getIdentifiant(), 2L);
//        assertTrue("Type de la relation personne incorrecte", relation.getPersonne() instanceof PersonnePhysiqueRelationDto);
//        final PersonnePhysiqueRelationDto personne = (PersonnePhysiqueRelationDto) relation.getPersonne();
//        assertNotNull("Nom de la personne dans la relation devrait pas être null", personne.getNom());
//        assertNotNull("Prenom ne devrait pas être null", personne.getPrenom());
//        assertNotNull("Date de naissance  ne devrait pas être null", personne.getDateNaissance());
//        assertNotNull("Civlite ne devrait pas être null", personne.getCivilite());
//        assertNotNull("L'age ne devrait pas être null", personne.getAge());
//        assertNotNull("le numéro de sécurité social ne devrait pas être null", personne.getNumSS());
//        assertEquals("le numss ne correspond pas", "1234567890123", personne.getNumSS());
//        assertNotNull("la clé du numss ne devrait pas être nulle", personne.getCleNumSS());
//        assertEquals("la clé ne correspond pas", "11", personne.getCleNumSS());
//        assertEquals("L'identifiant de la caisse est incorrect", 2L, personne.getCaisse().getId());
//        assertEquals("Le libellé de la caisse est incorrect", "Centre nom 2", personne.getCaisse().getNom());
//        assertEquals("L'identifiant du régime est incorrect", 2L, personne.getRegime().getIdentifiant());
//        assertEquals("Le libellé du régime est incorrect", "Caisse régime 2", personne.getRegime().getLibelle());
//
//        criteres = new RelationCriteresRechercheDto();
//        criteres.setIdPersonne(1L);
//        listeGroupements = new ArrayList<Long>();
//        listeGroupements.add(2L);
//        criteres.setGroupements(listeGroupements);
//        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
//        result = personneService.rechercherRelationsParCritreres(criterias);
//        assertNotNull("le résultat est null", result);
//        assertEquals("le nombre de résultat ne correspond pas", 1, result.getListResults().size());
//        relation = result.getListResults().get(0);
//        assertNotNull("le type ne devrait pas être null", relation.getType());
//        assertEquals("identifaint du type incorrecte", relation.getType().getIdentifiant(), 3L);
//        assertEquals("libelle du type incorrecte", relation.getType().getLibelle(), "est employe de");
//        assertEquals("identifaint du groupement incorrecte", relation.getGroupement().getIdentifiant(), 2L);
//        assertTrue("Type de personne incorrecte", relation.getPersonne() instanceof PersonneMoraleRelationDto);
//        final PersonneMoraleRelationDto personneMorale = (PersonneMoraleRelationDto) relation.getPersonne();
//        assertNotNull("L'identifiant ne devrait pas être null", personneMorale.getId());
//        assertNotNull("La nature de la personne ne devrait pas être null", personneMorale.getNaturePersonne());
//        assertNotNull("L'identifiant de la nature de la personne ne devrait pas être null", personneMorale.getNaturePersonne().getIdentifiant());
//
//        // Test sur l'odre des relations
//        criteres = new RelationCriteresRechercheDto();
//        criteres.setIdPersonne(1L);
//        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
//        result = personneService.rechercherRelationsParCritreres(criterias);
//        assertNotNull("resultat sans criteres ne devrait pas être null", result);
//        assertEquals("le nombre sans criteres de résultat ne correspond pas", 4, result.getListResults().size());
//        assertEquals("la première relation n'est pas bonne", 1L, result.getListResults().get(0).getId());
//        assertEquals("la seconde relation n'est pas bonne", 3L, result.getListResults().get(1).getId());
//        assertEquals("la troisème relation n'est pas bonne", 4L, result.getListResults().get(2).getId());
//        assertEquals("la dernière relation n'est pas bonne", 2L, result.getListResults().get(3).getId());

        // on desactive les relations qui ne sont plus valides
        final List<Long> idsRelations = new ArrayList<Long>();
        idsRelations.add(6L);
        idsRelations.add(7L);
        idsRelations.add(8L);
        personneService.desactiverRelations(idsRelations);
        // Test sur l'odre des relations
        criteres = new RelationCriteresRechercheDto();
        criteres.setIdPersonne(10L);
        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
        criterias.setListeSorts(new ArrayList<RemotePagingSort>());
        // tri : d'abord relations en cours puis terminée
        final RemotePagingSort sortActif = new RemotePagingSort(squareMappingService.getOrderByRelationActif(), RemotePagingSort.REMOTE_PAGING_SORT_DESC);
        final RemotePagingSort sortType = new RemotePagingSort(squareMappingService.getOrderByRelationTypeOrdre(), RemotePagingSort.REMOTE_PAGING_SORT_ASC);
        criterias.getListeSorts().add(sortActif);
        criterias.getListeSorts().add(sortType);
        result = personneService.rechercherRelationsParCritreres(criterias);
        assertNotNull(Messages.getString("PersonneServiceTest.12"), result);
        assertEquals(Messages.getString("PersonneServiceTest.13"), 6, result.getListResults().size());
        assertEquals(Messages.getString("PersonneServiceTest.14"), 5L, result.getListResults().get(0).getId());
        assertEquals(Messages.getString("PersonneServiceTest.15"), 10L, result.getListResults().get(1).getId());
        assertEquals(Messages.getString("PersonneServiceTest.16"), 9L, result.getListResults().get(2).getId());
        assertEquals(Messages.getString("PersonneServiceTest.17"), 6L, result.getListResults().get(3).getId());
        assertEquals(Messages.getString("PersonneServiceTest.18"), 7L, result.getListResults().get(4).getId());
        assertEquals(Messages.getString("PersonneServiceTest.19"), 8L, result.getListResults().get(5).getId());
    }

    /**
     * Test de la creation d'une relation de relation.
     */
    @Test
    public void testCreerRelation() {
        final RelationDto relation = new RelationDto();
        relation.setIdPersonnePrincipale(6L);
        relation.setIdPersonne(7L);
        relation.setType(new IdentifiantLibelleDto());
        try {
            personneService.creerRelation(relation);
            fail(Messages.getString("PersonneServiceTest.20"));
        } catch (CoreRunTimeException ex) {
            assertTrue(Messages.getString("PersonneServiceTest.21"), ex instanceof ControleIntegriteException);

            // CREATION D'UNE RELATION ENTRE DEUX PERSONNE SOURCE => CIBLE
            final PersonneDto personne =
                personnePhysiqueService.creerPersonnePhysique(creerPersonnePhysique(), null, creerAdresse(), creerEmail(), creerTelephone());

            relation.setIdPersonnePrincipale(6L);
            relation.setIdPersonne(personne.getIdentifiant());
            relation.setType(new IdentifiantLibelleDto(1L, A_POUR_CONJOINT));
            relation.setDateDebut(Calendar.getInstance());

            final RelationDto relationRetour = personneService.creerRelation(relation);
            assertNotNull(Messages.getString("PersonneServiceTest.22"), relationRetour.getId());

            // JE PASSE PAR LA BASE CAR LE SERVICE DE CREATION RETOURNE LE DTO
            RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();
            final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
                new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
            criteres.setIdPersonne(personne.getIdentifiant());
            RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> relations = personneService.rechercherRelationsParCritreres(criterias);
            assertEquals(Messages.getString("PersonneServiceTest.23"), relations.getListResults().size(), 1);
            RelationInfosDto<? extends PersonneRelationDto> relationInfos = relations.getListResults().get(0);
            assertNotNull(Messages.getString("PersonneServiceTest.24"), relationInfos.getType());
            assertEquals(Messages.getString("PersonneServiceTest.25"), relationInfos.getType().getIdentifiant(), 1L);
            assertNotNull(Messages.getString("PersonneServiceTest.26"), relationInfos.getDateDebut());
            PersonnePhysiqueRelationDto personneControle = (PersonnePhysiqueRelationDto) relationInfos.getPersonne();
            assertNotNull(Messages.getString("PersonneServiceTest.27"), personneControle.getId().equals(6L));

            // TEST MODIFICATION DE LA RELATION
            relation.setIdPersonnePrincipale(personne.getIdentifiant());
            relation.setIdPersonne(6L);
            relation.setType(new IdentifiantLibelleDto(1L, A_POUR_CONJOINT));
            relation.setDateFin(Calendar.getInstance());
            relation.setId(relationRetour.getId());

            personneService.modifierRelation(relation);

            criteres = new RelationCriteresRechercheDto();
            criteres.setIdPersonne(personne.getIdentifiant());
            relations = personneService.rechercherRelationsParCritreres(criterias);
            assertEquals(Messages.getString("PersonneServiceTest.28"), relations.getListResults().size(), 1);
            relationInfos = relations.getListResults().get(0);
            assertNotNull(Messages.getString("PersonneServiceTest.29"), relationInfos.getType());
            assertEquals(Messages.getString("PersonneServiceTest.30"), relationInfos.getType().getIdentifiant(), 1L);
            assertEquals(Messages.getString("PersonneServiceTest.31"), relationInfos.getType().getLibelle(), A_POUR_CONJOINT);
            assertNotNull(Messages.getString("PersonneServiceTest.32"), relationInfos.getDateDebut());
            assertTrue(Messages.getString("PersonneServiceTest.33"), relationInfos.getPersonne() instanceof PersonnePhysiqueRelationDto);
            personneControle = (PersonnePhysiqueRelationDto) relationInfos.getPersonne();
            assertNotNull(Messages.getString("PersonneServiceTest.34"), personneControle.getId().equals(6L));
            assertNotNull(Messages.getString("PersonneServiceTest.35"), relationInfos.getDateFin());

            // TEST MODIFICATION DE LA RELATION
            relation.setIdPersonnePrincipale(personne.getIdentifiant());
            relation.setIdPersonne(6L);
            relation.setType(new IdentifiantLibelleDto(1L, "est conjoint de"));
            relation.setDateFin(Calendar.getInstance());
            relation.setId(relationRetour.getId());

            personneService.modifierRelation(relation);

            criteres = new RelationCriteresRechercheDto();
            criteres.setIdPersonne(personne.getIdentifiant());
            relations = personneService.rechercherRelationsParCritreres(criterias);
            assertEquals(Messages.getString("PersonneServiceTest.37"), relations.getListResults().size(), 1);
            relationInfos = relations.getListResults().get(0);
            assertNotNull(Messages.getString("PersonneServiceTest.38"), relationInfos.getType());
            assertEquals(Messages.getString("PersonneServiceTest.39"), relationInfos.getType().getIdentifiant(), 1L);
            assertEquals(Messages.getString("PersonneServiceTest.40"), relationInfos.getType().getLibelle(), "est conjoint de");
            assertNotNull(Messages.getString("PersonneServiceTest.42"), relationInfos.getDateDebut());
            assertTrue(Messages.getString("PersonneServiceTest.43"), relationInfos.getPersonne() instanceof PersonnePhysiqueRelationDto);
            personneControle = (PersonnePhysiqueRelationDto) relationInfos.getPersonne();
            assertNotNull(Messages.getString("PersonneServiceTest.44"), personneControle.getId().equals(6L));
            assertNotNull(Messages.getString("PersonneServiceTest.45"), relationInfos.getDateFin());
        }
    }

    /**
     * Test pour la récupération d'adresse simple.
     */
    @Test
    public void testRechercherAdresseSimple() {

        // Test avec un identifiant de personne null
        try {
            personneService.rechercherAdresseSimpleParIdPersonne(null);
            fail(Messages.getString("PersonneServiceTest.46"));
        } catch (BusinessException be) {
            assertEquals(Messages.getString("PersonneServiceTest.47"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL), be.getMessage());
        }
        // Test avec un identifiant de personne non existante
        try {
            personneService.rechercherAdresseSimpleParIdPersonne(20L);
            fail(Messages.getString("PersonneServiceTest.48"));
        } catch (BusinessException be) {
            assertEquals(Messages.getString("PersonneServiceTest.49"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), be.getMessage());
        }

        // Test de la vérification des informations récupérées sur une adresse
        List<AdresseSimpleDto> result = personneService.rechercherAdresseSimpleParIdPersonne(2L);
        assertNotNull(Messages.getString("PersonneServiceTest.50"), result);
        assertEquals(Messages.getString("PersonneServiceTest.51"), 2, result.size());
        for (AdresseSimpleDto adresseSimpleDto : result) {
            if (adresseSimpleDto.getTypeAdresse().getIdentifiant().equals(2L)) {
                assertEquals(Messages.getString("PersonneServiceTest.52"), "nature 2", adresseSimpleDto.getTypeAdresse().getLibelle());
                assertEquals(Messages.getString("PersonneServiceTest.54"), 1, adresseSimpleDto.getDateDebut().get(Calendar.DAY_OF_MONTH));
                assertNull(Messages.getString("PersonneServiceTest.55"), adresseSimpleDto.getDateFin());
            }
        }

        // Test de la vérification des informations récupérées sur plusieurs adresses
        result = personneService.rechercherAdresseSimpleParIdPersonne(1L);
        assertNotNull(Messages.getString("PersonneServiceTest.56"), result);
        assertEquals(Messages.getString("PersonneServiceTest.57"), 5, result.size());
        assertEquals(Messages.getString("PersonneServiceTest.58"), 1L, result.get(0).getTypeAdresse().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.59"), "nature 1", result.get(0).getTypeAdresse().getLibelle());
        assertEquals(Messages.getString("PersonneServiceTest.61"), 1, result.get(0).getDateDebut().get(Calendar.DAY_OF_MONTH));
        assertEquals(Messages.getString("PersonneServiceTest.62"), null, result.get(0).getDateFin());
        assertEquals(Messages.getString("PersonneServiceTest.63"), 2L, result.get(2).getTypeAdresse().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.64"), "nature 2", result.get(2).getTypeAdresse().getLibelle());
        assertEquals(Messages.getString("PersonneServiceTest.66"), 1, result.get(2).getDateDebut().get(Calendar.DAY_OF_MONTH));
        assertEquals(Messages.getString("PersonneServiceTest.67"), null, result.get(2).getDateFin());
        // Test de l'ordre : En premier adresse principale puis adresse secondaire.
        assertEquals(ORDRE_PAS_BON, 1L, result.get(0).getTypeAdresse().getIdentifiant());
        assertEquals(ORDRE_PAS_BON, 1L, result.get(1).getTypeAdresse().getIdentifiant());
        assertEquals(ORDRE_PAS_BON, 2L, result.get(2).getTypeAdresse().getIdentifiant());
        assertEquals(ORDRE_PAS_BON, 2L, result.get(3).getTypeAdresse().getIdentifiant());
        assertEquals(ORDRE_PAS_BON, 2L, result.get(4).getTypeAdresse().getIdentifiant());
    }

    /**
     * Test pour la récupération d'une personneSimple.
     */
    @Test
    public void testRechercherPersonneSimple() {
        try {
            personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(null);
            fail(Messages.getString("PersonneServiceTest.68"));
        } catch (BusinessException be) {
            assertEquals(Messages.getString("PersonneServiceTest.69"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL), be.getMessage());
        }

        final PersonneSimpleDto personneSimpleDto = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(1L);
        assertEquals(Messages.getString("PersonneServiceTest.70"), "En couple, 2 enfants", personneSimpleDto.getSituationFamiliale());

        final PersonneSimpleDto personneSimpleDto2 = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(6L);
        assertEquals(Messages.getString("PersonneServiceTest.72"), "Célibataire, 0 enfant", personneSimpleDto2.getSituationFamiliale());

    }

    /**
     * Test de la creation d'une relation de type conjoint.
     */
    @Test
    public void testCreerRelationDeTypeConjoint() {
        RelationDto relation = new RelationDto();
        relation.setIdPersonnePrincipale(1L);
        relation.setIdPersonne(4L);
        relation.setType(new IdentifiantLibelleDto(1L, A_POUR_CONJOINT));
        final Calendar dateDebut = Calendar.getInstance();
        dateDebut.setTime(new Date());
        relation.setDateDebut(dateDebut);
        try {
            personneService.creerRelation(relation);
            fail(Messages.getString("PersonneServiceTest.74"));
        } catch (BusinessException be) {
            assertEquals(Messages.getString("PersonneServiceTest.75"), messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_CONJOINT_DOUBLE), be.getMessage());
        }

        relation = new RelationDto();
        relation.setIdPersonnePrincipale(5L);
        relation.setIdPersonne(2L);
        relation.setType(new IdentifiantLibelleDto(1L, A_POUR_CONJOINT));
        dateDebut.setTime(new Date());
        relation.setDateDebut(dateDebut);
        try {
            personneService.creerRelation(relation);
            fail(Messages.getString("PersonneServiceTest.76"));
        } catch (BusinessException be) {
            assertEquals(Messages.getString("PersonneServiceTest.77"), messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_CONJOINT_DOUBLE), be.getMessage());
        }
    }

    /**
     * Test de la creation d'une relation de type conjoint.
     */
    @Test
    public void testModifierRelationDeTypeConjoint() {
        // Création d'une relation de type "a pour enfant", test pour la personne principale
        RelationDto relation = new RelationDto();
        relation.setIdPersonnePrincipale(1L);
        relation.setIdPersonne(4L);
        relation.setType(new IdentifiantLibelleDto(3L, "a pour employe"));
        Calendar dateDebut = Calendar.getInstance();
        relation.setDateDebut(dateDebut);
        relation = personneService.creerRelation(relation);

        // Modification d'une relation A_POUR_CONJOINT
        relation.setType(new IdentifiantLibelleDto(1L, A_POUR_CONJOINT));
        try {
            personneService.modifierRelation(relation);
            fail(Messages.getString("PersonneServiceTest.79"));
        } catch (BusinessException be) {
            assertEquals(Messages.getString("PersonneServiceTest.80"), messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_CONJOINT_DOUBLE), be.getMessage());
        }

        // Creation d'une relation de type "a pour enfant", pour la personne cible
        relation = new RelationDto();
        relation.setIdPersonnePrincipale(5L);
        relation.setIdPersonne(2L);
        relation.setType(new IdentifiantLibelleDto(2L, "a pour enfant"));
        dateDebut = Calendar.getInstance();
        relation.setDateDebut(dateDebut);
        relation = personneService.creerRelation(relation);

        // Modification d'une relation de type "a pour conjoint", pour la personne cible
        relation.setType(new IdentifiantLibelleDto(1L, A_POUR_CONJOINT));
        try {
            personneService.modifierRelation(relation);
            fail(Messages.getString("PersonneServiceTest.82"));
        } catch (BusinessException be) {
            assertEquals(Messages.getString("PersonneServiceTest.83"), messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_CONJOINT_DOUBLE), be.getMessage());
        }
    }

    /** Test de suppression d'une personne. */
    @Test
    public void testSupprimerPersonne() {
        // Suppression sans définir la personne
        try {
            personneService.supprimerPersonne(null);
            fail(Messages.getString("PersonneServiceTest.84"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_SUPPRESSION_PERSONNE_NULL), e.getMessage());
        }

        // Suppression d'une personne inexistante
        final Long idPersonneInexistante = 100L;
        try {
            personneService.supprimerPersonne(idPersonneInexistante);
            fail(Messages.getString("PersonneServiceTest.85"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_SUPPRESSION_PERSONNE_INEXISTANTE), e.getMessage());
        }

        // Suppression d'une personne existante
        final Long idPersonne2 = 2L;
        // Recherche de l'ensemble des personnes avant suppression
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> remote = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, 100);
        RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        final int nbPersonnesAvantSuppression = 10;
        assertEquals(Messages.getString("PersonneServiceTest.86"), nbPersonnesAvantSuppression, results.getTotalResults());
        // Appel du service de suppression
        personneService.supprimerPersonne(idPersonne2);
        // Recherche de l'ensemble des personnes après suppression
        results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        final int nbPersonnesApresSuppression = nbPersonnesAvantSuppression - 1;
        assertEquals(Messages.getString("PersonneServiceTest.87"), nbPersonnesApresSuppression, results.getTotalResults());
    }

    /** Test du transfert du commercial d'une personne. */
    @Test
    public void testTransfererPersonneACommercial() {
        final Long idPersonne2 = 2L;
        final Long idCommercial2 = 2L;
        final Long idPersonneInexistante = 100L;
        final Long idCommercialInexistant = 100L;

        // Test sans définir la personne
        try {
            personneService.transfererPersonneACommercial(null, idCommercial2);
            fail(Messages.getString("PersonneServiceTest.88"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_PERSONNE_NULL), e.getMessage());
        }
        // Test sans définir le commercial
        try {
            personneService.transfererPersonneACommercial(idPersonne2, null);
            fail(Messages.getString("PersonneServiceTest.89"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_COMMERCIAL_NULL), e
                    .getMessage());
        }
        // Test en définissant une personne inexistante
        try {
            personneService.transfererPersonneACommercial(idPersonneInexistante, idCommercial2);
            fail(Messages.getString("PersonneServiceTest.90"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_PERSONNE_INEXISTANTE), e
                    .getMessage());
        }
        // Test en définissant un commercial inexistant
        try {
            personneService.transfererPersonneACommercial(idPersonne2, idCommercialInexistant);
            fail(Messages.getString("PersonneServiceTest.91"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_COMMERCIAL_INEXISTANT), e
                    .getMessage());
        }

        // Transfert du commercial
        personneService.transfererPersonneACommercial(idPersonne2, idCommercial2);
        // Vérification des données
        final PersonneDto personne = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne2);
        assertEquals(Messages.getString("PersonneServiceTest.92"), idCommercial2, personne.getCommercial().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.93"), "nom2", personne.getCommercial().getNom());
        assertEquals(Messages.getString("PersonneServiceTest.95"), "prenom2", personne.getCommercial().getPrenom());
    }

    /** Test du transfert de l'agence d'une personne. */
    @Test
    public void testTransfererPersonneAAgence() {
        final Long idPersonne2 = 2L;
        final Long idAgence1 = 1L;
        final Long idPersonneInexistante = 100L;
        final Long idAgenceInexistante = 100L;

        // Test sans définir la personne
        try {
            personneService.transfererPersonneAAgence(null, idAgence1);
            fail(Messages.getString("PersonneServiceTest.97"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_PERSONNE_NULL), e.getMessage());
        }
        // Test sans définir l'agence
        try {
            personneService.transfererPersonneAAgence(idPersonne2, null);
            fail(Messages.getString("PersonneServiceTest.98"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_AGENCE_NULL), e.getMessage());
        }
        // Test en définissant une personne inexistante
        try {
            personneService.transfererPersonneAAgence(idPersonneInexistante, idAgence1);
            fail(Messages.getString("PersonneServiceTest.99"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_PERSONNE_INEXISTANTE), e
                    .getMessage());
        }
        // Test en définissant un commercial inexistant
        try {
            personneService.transfererPersonneAAgence(idPersonne2, idAgenceInexistante);
            fail(Messages.getString("PersonneServiceTest.100"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_AGENCE_INEXISTANTE), e
                    .getMessage());
        }

        // Transfert de l'agence
        personneService.transfererPersonneAAgence(idPersonne2, idAgence1);
        // Vérification des données
        final PersonneDto personne = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne2);
        assertEquals(Messages.getString("PersonneServiceTest.101"), idAgence1, personne.getAgence().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.102"), "libelle1", personne.getAgence().getLibelle());
    }

    /** Test du transfert de relations d'une source vers une cible. */
    @Test
    public void testTransfererRelations() {
        final Long idPersonne1 = 1L;
        final Long idPersonne7 = 7L;
        final Long idPersonneCibleInexistante = 100L;

        // Test avec la personne source non définie
        try {
            personneService.transfererRelations(null, idPersonne7);
            fail(Messages.getString("PersonneServiceTest.104"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_RELATIONS_PERSONNE_SOURCE_NULL), e.getMessage());
        }
        // Test avec la personne cible non définie
        try {
            personneService.transfererRelations(idPersonne1, null);
            fail(Messages.getString("PersonneServiceTest.105"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_RELATIONS_PERSONNE_CIBLE_NULL), e.getMessage());
        }
        // Test avec la personne cible inexistante
        try {
            personneService.transfererRelations(idPersonne1, idPersonneCibleInexistante);
            fail(Messages.getString("PersonneServiceTest.106"));
        } catch (BusinessException e) {
            assertEquals(EXCEPTION_PAS_BONNE, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_RELATIONS_PERSONNE_CIBLE_INEXISTANTE), e
                    .getMessage());
        }

        // Transfert de relations de 1 vers 7 : 4 relations
        // Vérification des relations de la personne source avant transfert
        final int nbRelationsPersonne1AvantTransfert = 4;
        final int nbRelationsPersonne1SourceAvantTransfert = 3;
        final Long idRelation1 = 1L;
        final Long idRelation2 = 2L;
        final Long idRelation3 = 3L;
        final Long idRelation4 = 4L;
        boolean possedeRelation1 = false;
        boolean possedeRelation2 = false;
        boolean possedeRelation3 = false;
        boolean possedeRelation4 = false;
        final RelationCriteresRechercheDto criteresRelationPersonne1 = new RelationCriteresRechercheDto();
        criteresRelationPersonne1.setIdPersonne(idPersonne1);
        RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelationPersonne1, 0, Integer.MAX_VALUE);
        RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> listeRelationsPersonne1AvantTransfert =
            personneService.rechercherRelationsParCritreres(criterias);
        assertEquals(Messages.getString("PersonneServiceTest.107"), nbRelationsPersonne1AvantTransfert,
            listeRelationsPersonne1AvantTransfert.getListResults().size());
        for (RelationInfosDto<? extends PersonneRelationDto> relation : listeRelationsPersonne1AvantTransfert.getListResults()) {
            if (idRelation1.equals(relation.getId())) {
                possedeRelation1 = true;
            } else if (idRelation2.equals(relation.getId())) {
                possedeRelation2 = true;
            } else if (idRelation3.equals(relation.getId())) {
                possedeRelation3 = true;
            } else if (idRelation4.equals(relation.getId())) {
                possedeRelation4 = true;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.108"), possedeRelation1 && possedeRelation2 && possedeRelation3 && possedeRelation4);
        criteresRelationPersonne1.setIdPersonne(null);
        criteresRelationPersonne1.setIdPersonneSource(idPersonne1);
        listeRelationsPersonne1AvantTransfert = personneService.rechercherRelationsParCritreres(criterias);
        assertEquals(Messages.getString("PersonneServiceTest.109"), nbRelationsPersonne1SourceAvantTransfert,
            listeRelationsPersonne1AvantTransfert.getListResults().size());
        possedeRelation1 = false;
        possedeRelation3 = false;
        possedeRelation4 = false;
        for (RelationInfosDto<? extends PersonneRelationDto> relation : listeRelationsPersonne1AvantTransfert.getListResults()) {
            if (idRelation1.equals(relation.getId())) {
                possedeRelation1 = true;
            } else if (idRelation3.equals(relation.getId())) {
                possedeRelation3 = true;
            } else if (idRelation4.equals(relation.getId())) {
                possedeRelation4 = true;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.110"), possedeRelation1 && possedeRelation3 && possedeRelation4);

        // Vérification des relations de la personne cible avant transfert
        final int nbRelationsPersonne7AvantTransfert = 0;
        final RelationCriteresRechercheDto criteresRelationPersonne7 = new RelationCriteresRechercheDto();
        criteresRelationPersonne7.setIdPersonne(idPersonne7);
        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelationPersonne7, 0, Integer.MAX_VALUE);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> listeRelationsPersonne7AvantTransfert =
            personneService.rechercherRelationsParCritreres(criterias);
        assertEquals(Messages.getString("PersonneServiceTest.111"), nbRelationsPersonne7AvantTransfert,
            listeRelationsPersonne7AvantTransfert.getListResults().size());

        // Transfert des relations
        personneService.transfererRelations(idPersonne1, idPersonne7);

        // Vérification des relations de la personne source après transfert
        final int nbRelationsPersonne1ApresTransfert = 0;
        criteresRelationPersonne1.setIdPersonne(idPersonne1);
        criteresRelationPersonne1.setIdPersonneSource(null);
        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelationPersonne1, 0, Integer.MAX_VALUE);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> listeRelationsPersonne1ApresTransfert =
            personneService.rechercherRelationsParCritreres(criterias);
        assertEquals(Messages.getString("PersonneServiceTest.112"), nbRelationsPersonne1ApresTransfert,
            listeRelationsPersonne1ApresTransfert.getListResults().size());

        // Vérification des relations de la personne cible après transfert
        final int nbRelationsPersonne7ApresTransfert = 4;
        final int nbRelationsPersonne7SourceApresTransfert = 3;
        possedeRelation1 = false;
        possedeRelation2 = false;
        possedeRelation3 = false;
        possedeRelation4 = false;
        criteresRelationPersonne7.setIdPersonne(idPersonne7);
        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelationPersonne7, 0, Integer.MAX_VALUE);
        RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> listeRelationsPersonne7ApresTransfert =
            personneService.rechercherRelationsParCritreres(criterias);
        assertEquals(Messages.getString("PersonneServiceTest.113"), nbRelationsPersonne7ApresTransfert,
            listeRelationsPersonne7ApresTransfert.getListResults().size());
        for (RelationInfosDto<? extends PersonneRelationDto> relation : listeRelationsPersonne7ApresTransfert.getListResults()) {
            if (idRelation1.equals(relation.getId())) {
                possedeRelation1 = true;
            } else if (idRelation2.equals(relation.getId())) {
                possedeRelation2 = true;
            } else if (idRelation3.equals(relation.getId())) {
                possedeRelation3 = true;
            } else if (idRelation4.equals(relation.getId())) {
                possedeRelation4 = true;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.114"), possedeRelation1 && possedeRelation2 && possedeRelation3 && possedeRelation4);
        criteresRelationPersonne7.setIdPersonne(null);
        criteresRelationPersonne7.setIdPersonneSource(idPersonne7);
        criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelationPersonne7, 0, Integer.MAX_VALUE);
        listeRelationsPersonne7ApresTransfert = personneService.rechercherRelationsParCritreres(criterias);
        assertEquals(Messages.getString("PersonneServiceTest.115"), nbRelationsPersonne7SourceApresTransfert,
            listeRelationsPersonne7ApresTransfert.getListResults().size());
        possedeRelation1 = false;
        possedeRelation3 = false;
        possedeRelation4 = false;
        for (RelationInfosDto<? extends PersonneRelationDto> relation : listeRelationsPersonne7ApresTransfert.getListResults()) {
            if (idRelation1.equals(relation.getId())) {
                possedeRelation1 = true;
            } else if (idRelation3.equals(relation.getId())) {
                possedeRelation3 = true;
            } else if (idRelation4.equals(relation.getId())) {
                possedeRelation4 = true;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.116"), possedeRelation1 && possedeRelation3 && possedeRelation4);
    }

    /**
     * Test d'ecrasment UID sur telephone et adresse.
     * @return
     */
    @Test
    public void testEcrasmentEid() {
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(1L);
        assertNotNull(Messages.getString("PersonneServiceTest.117"), coordonnees.getAdresses().get(0).getIdext());
        assertNotNull(Messages.getString("PersonneServiceTest.118"), coordonnees.getTelephones().get(0).getIdext());
        coordonnees.getAdresses().get(0).setNomVoie("modif voie");
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        coordonnees.getTelephones().get(0).setPays(pays);
        coordonnees.getTelephones().get(0).setNumero("0577603226");
        coordonnees.getTelephones().get(1).setPays(pays);
        coordonnees.getTelephones().get(1).setNumero("0606060606");
        coordonnees.getAdresses().get(0).setDateDebut(Calendar.getInstance());
        personneService.creerOuMettreAJourCoordonnees(coordonnees, true, false);
        final CoordonneesDto coordonneesRetour = personneService.rechercherCoordonneesParIdPersonne(1L);
        // FIXME l'ordre des adresse n'est pas bon sur les tests unitaires (les dates à null passent après les dates remplies)
        assertEquals(Messages.getString("PersonneServiceTest.122"), coordonneesRetour.getAdresses().get(0).getNomVoie(), "modif voie");
        assertEquals(Messages.getString("PersonneServiceTest.124"), coordonneesRetour.getTelephones().get(0).getNumero(), "0577603226");
        assertNotNull(Messages.getString("PersonneServiceTest.126"), coordonneesRetour.getAdresses().get(0).getIdext());
        assertNotNull(Messages.getString("PersonneServiceTest.127"), coordonneesRetour.getTelephones().get(0).getIdext());
    }

    /** Test de la suppression d'un téléphone. */
    @Test
    public void testSupprimerTelephone() {
        // Récupération des coordonnées d'une personne avec 2 téléphones
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(1L);
        assertEquals(Messages.getString("PersonneServiceTest.128"), 2, coordonnees.getTelephones().size());

        // Suppression d'un numéro de téléphone
        coordonnees.getTelephones().get(0).setNumero("0505050505");
        coordonnees.getTelephones().get(1).setNumero("");
        final CoordonneesDto coordonneesModifiees = personneService.creerOuMettreAJourCoordonnees(coordonnees, true, false);
        assertEquals(Messages.getString("PersonneServiceTest.131"), 1, coordonneesModifiees.getTelephones().size());
        assertEquals(Messages.getString("PersonneServiceTest.132"), 1L, coordonneesModifiees.getTelephones().get(0).getId());
    }

    /** Test de l'ajout d'une nouvelle adresse sur une personne. */
    @Test
    public void testVerifContrainteAjoutAdresse() {
        final Long idPersonne = 2L;

        final Calendar dateDebut = Calendar.getInstance();
        final int year = 2010;
        final int month = 10;
        final int date = 1;
        dateDebut.set(year, month, date);

        final AdresseDto adresseDto = new AdresseDto();
        adresseDto.setChoixPasserEnSecondaire(false);
        adresseDto.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseDto.setDateDebut(Calendar.getInstance());
        adresseDto.setDepartement(new IdentifiantLibelleDto(1L));
        adresseDto.setNature(new IdentifiantLibelleDto(1L));
        adresseDto.setNomVoie("des Halles");
        adresseDto.setNumVoie("84/7");
        final IdentifiantLibelleBooleanDto paysDto = new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance());
        adresseDto.setPays(paysDto);
        final IdentifiantLibelleDto typeVoieDto = new IdentifiantLibelleDto(1L);
        adresseDto.setTypeVoie(typeVoieDto);

        try {
            personneService.ajouterNouvelleAdresse(idPersonne, adresseDto, false);
            fail(Messages.getString("PersonneServiceTest.135"));
        } catch (ControleIntegriteException e) {
            int nbErreurs = 0;
            assertTrue(Messages.getString("PersonneServiceTest.136"), e.getRapport().getEnErreur());
            for (SousRapportDto sousRapport : e.getRapport().getRapports().values()) {
                if (sousRapport.getErreur()) {
                    nbErreurs++;
                    assertEquals(Messages.getString("PersonneServiceTest.137"),
                        messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_FORMAT_NUM_VOIE_INCORRECT), sousRapport.getMessage());
                }
            }
            assertEquals(Messages.getString("PersonneServiceTest.138"), 1, nbErreurs);
        }
    }

    /** Test. */
    @Test
    public void testVerifContrainteCreerOuMettreAJourCoordonnees() {
        final Long idPersonne = 2L;
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonne);
        assertEquals(Messages.getString("PersonneServiceTest.139"), 2, coordonnees.getAdresses().size());

        final AdresseDto adresse1 = coordonnees.getAdresses().get(0);
        final AdresseDto adresse2 = coordonnees.getAdresses().get(1);

        adresse1.setNumVoie("47/8");
        adresse2.setIdentifiant(null);
        adresse2.setNumVoie("47/8");

        try {
            personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);
            fail(Messages.getString("PersonneServiceTest.142"));
        } catch (ControleIntegriteException e) {
            int nbErreurs = 0;
            assertTrue(Messages.getString("PersonneServiceTest.143"), e.getRapport().getEnErreur());
            for (SousRapportDto sousRapport : e.getRapport().getRapports().values()) {
                if (sousRapport.getErreur()) {
                    nbErreurs++;
                    assertEquals(Messages.getString("PersonneServiceTest.144"),
                        messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_FORMAT_NUM_VOIE_INCORRECT), sousRapport.getMessage());
                }
            }
            assertEquals(Messages.getString("PersonneServiceTest.145"), 2, nbErreurs);
        }
    }

    /** Test de l'ajout d'une nouvelle adresse sur une personne. */
    @Test
    public void testAjouterNouvelleAdresse() {
        final Long idPersonneInexistante = 100L;
        AdresseDto adresseDto = null;
        try {
            personneService.ajouterNouvelleAdresse(idPersonneInexistante, adresseDto, false);
            fail(Messages.getString("PersonneServiceTest.146"));
        } catch (BusinessException e) {
            assertEquals(Messages.getString("PersonneServiceTest.147"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_DTO_NULL), e.getMessage());
        }

        adresseDto = new AdresseDto();
        try {
            personneService.ajouterNouvelleAdresse(idPersonneInexistante, adresseDto, false);
            fail(Messages.getString("PersonneServiceTest.148"));
        } catch (BusinessException e) {
            assertEquals(Messages.getString("PersonneServiceTest.149"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), e
                    .getMessage());
        }

        final Long idPersonne = 2L;

        CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonne);
        assertEquals(Messages.getString("PersonneServiceTest.150"), 2, coordonnees.getAdresses().size());

        adresseDto.setChoixPasserEnSecondaire(true);
        adresseDto.setCodePostalCommune(new CodePostalCommuneDto(1L));
        final Calendar dateDebut = Calendar.getInstance();
        final int year = 2010;
        final int month = 10;
        final int date = 1;
        dateDebut.set(year, month, date);
        adresseDto.setDateDebut(Calendar.getInstance());
        final IdentifiantLibelleDto departementDto = new IdentifiantLibelleDto(1L);
        adresseDto.setDepartement(departementDto);
        final IdentifiantLibelleDto natureDto = new IdentifiantLibelleDto(1L);
        adresseDto.setNature(natureDto);
        adresseDto.setNomVoie("des Halles");
        adresseDto.setNumVoie("17");
        final IdentifiantLibelleBooleanDto paysDto = new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance());
        adresseDto.setPays(paysDto);
        final IdentifiantLibelleDto typeVoieDto = new IdentifiantLibelleDto(1L);
        adresseDto.setTypeVoie(typeVoieDto);
        personneService.ajouterNouvelleAdresse(idPersonne, adresseDto, false);

        coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonne);
        assertEquals(Messages.getString("PersonneServiceTest.153"), 3, coordonnees.getAdresses().size());
        AdresseDto adressePrincipale = null;
        AdresseDto adresseSecondaire = null;
        if (coordonnees.getAdresses().get(0).getNature().getIdentifiant().equals(1L)) {
            adressePrincipale = coordonnees.getAdresses().get(0);
            adresseSecondaire = coordonnees.getAdresses().get(1);
        } else {
            adressePrincipale = coordonnees.getAdresses().get(1);
            adresseSecondaire = coordonnees.getAdresses().get(0);
        }
        assertNotNull(Messages.getString("PersonneServiceTest.154"), adressePrincipale.getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.155"), "des halles", adressePrincipale.getNomVoie());
        assertEquals(Messages.getString("PersonneServiceTest.157"), 1L, adressePrincipale.getCodePostalCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.158"), "Commune1 ach", adressePrincipale.getCodePostalCommune().getLibelleAcheminement());
        assertEquals(Messages.getString("PersonneServiceTest.160"), 1L, adressePrincipale.getCodePostalCommune().getCodePostal().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.161"), "000", adressePrincipale.getCodePostalCommune().getCodePostal().getLibelle());
        assertEquals(Messages.getString("PersonneServiceTest.163"), 1L, adressePrincipale.getCodePostalCommune().getCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.164"), "Commune1", adressePrincipale.getCodePostalCommune().getCommune().getLibelle());
        assertEquals(Messages.getString("PersonneServiceTest.166"), 1L, adressePrincipale.getDepartement().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.167"), "Vienne", adressePrincipale.getDepartement().getLibelle());
        assertEquals(Messages.getString("PersonneServiceTest.169"), 2L, adresseSecondaire.getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.170"), "nom voie 2", adresseSecondaire.getNomVoie());
        assertEquals(Messages.getString("PersonneServiceTest.172"), 2L, adresseSecondaire.getCodePostalCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.173"), "Commune2 ach", adresseSecondaire.getCodePostalCommune().getLibelleAcheminement());
        assertEquals(Messages.getString("PersonneServiceTest.175"), 2L, adresseSecondaire.getCodePostalCommune().getCodePostal().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.176"), "001", adresseSecondaire.getCodePostalCommune().getCodePostal().getLibelle());
        assertEquals(Messages.getString("PersonneServiceTest.178"), 2L, adresseSecondaire.getCodePostalCommune().getCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.179"), "Commune2", adresseSecondaire.getCodePostalCommune().getCommune().getLibelle());
        assertEquals(Messages.getString("PersonneServiceTest.181"), 2L, adresseSecondaire.getDepartement().getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.182"), "Charente", adresseSecondaire.getDepartement().getLibelle());
    }

    /** Test de l'ordre des adresses. */
    @Test
    public void testOrdreAdresses() {
        final List<AdresseDto> listeAdresses = personneService.rechercherCoordonneesParIdPersonne(1L).getAdresses();
        // FIXME l'ordre des adresses n'est pas bon sur les tests unitaires (les dates à null passent après les dates remplies)
        assertEquals(Messages.getString("PersonneServiceTest.184"), 5L, listeAdresses.get(0).getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.185"), 1L, listeAdresses.get(1).getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.186"), 3L, listeAdresses.get(2).getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.187"), 7L, listeAdresses.get(3).getIdentifiant());
    }

    /**
     * Test la maj d'un téléphone seulement pour la personne principale.
     */
    @Test
    public void testModifierTelephoneSansImpacterFamilleDepuisPorteur() {
        final Long idPersonnePrincipale = 1L;
        final String nouveauNumero = "0546137896";
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonnePrincipale);
        assertEquals(Messages.getString("PersonneServiceTest.189"), 2, coordonnees.getTelephones().size());

        // on change le numero de telephone
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        final Long idTelModifie = coordonnees.getTelephones().get(0).getId();
        final String idExtTelModifie = coordonnees.getTelephones().get(0).getIdext();
        coordonnees.getTelephones().get(0).setPays(pays);
        coordonnees.getTelephones().get(0).setNumero(nouveauNumero);
        final CoordonneesDto coordonneesMaj = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // on verifie que l'ancien numéro a été retiré et que le nouveau a bien été ajouté
        assertEquals(Messages.getString("PersonneServiceTest.190"), 2, coordonneesMaj.getTelephones().size());
        boolean nouveauNumeroTrouve = false;
        for (TelephoneDto telephone : coordonneesMaj.getTelephones()) {
            assertFalse(Messages.getString("PersonneServiceTest.191"), telephone.getId().equals(idTelModifie));
            if (telephone.getNumero().equals(nouveauNumero)) {
                nouveauNumeroTrouve = true;
                assertEquals(Messages.getString("PersonneServiceTest.192"), idExtTelModifie, telephone.getIdext());
                break;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.193"), nouveauNumeroTrouve);

        // on verifie que l'ancien numéro est toujours sur la famille et que l'id ext a été supprimé
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(idPersonnePrincipale);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            Long idPersonneLiee;
            if (relation.getPersonneSource().getId().equals(idPersonnePrincipale)) {
                idPersonneLiee = relation.getPersonneCible().getId();
            } else {
                idPersonneLiee = relation.getPersonneSource().getId();
            }
            final CoordonneesDto coordonneesPersonneLiee = personneService.rechercherCoordonneesParIdPersonne(idPersonneLiee);
            boolean ancienNumeroConserve = false;
            for (TelephoneDto telephone : coordonneesPersonneLiee.getTelephones()) {
                if (telephone.getId().equals(idTelModifie)) {
                    ancienNumeroConserve = true;
                    assertNull(Messages.getString("PersonneServiceTest.194"), telephone.getIdext());
                    break;
                }
            }
            assertTrue(Messages.getString("PersonneServiceTest.195"), ancienNumeroConserve);
        }
    }

    /**
     * Test la suppression d'un téléphone seulement pour la personne principale.
     */
    @Test
    public void testSupprimerTelephoneSansImpacterFamilleDepuisPorteur() {
        final Long idPersonnePrincipale = 1L;
        final String nouveauNumero = "";
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonnePrincipale);
        assertEquals(Messages.getString("PersonneServiceTest.197"), 2, coordonnees.getTelephones().size());

        // on change le numero de telephone
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        final Long idTelModifie = coordonnees.getTelephones().get(0).getId();
        coordonnees.getTelephones().get(0).setPays(pays);
        coordonnees.getTelephones().get(0).setNumero(nouveauNumero);
        final CoordonneesDto coordonneesMaj = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // on verifie que l'ancien numéro a été retiré
        assertEquals(Messages.getString("PersonneServiceTest.198"), 1, coordonneesMaj.getTelephones().size());
        for (TelephoneDto telephone : coordonneesMaj.getTelephones()) {
            assertFalse(Messages.getString("PersonneServiceTest.199"), telephone.getId().equals(idTelModifie));
        }

        // on verifie que l'ancien numéro est toujours sur la famille et que l'id ext a été supprimé
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(idPersonnePrincipale);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            Long idPersonneLiee;
            if (relation.getPersonneSource().getId().equals(idPersonnePrincipale)) {
                idPersonneLiee = relation.getPersonneCible().getId();
            } else {
                idPersonneLiee = relation.getPersonneSource().getId();
            }
            final CoordonneesDto coordonneesPersonneLiee = personneService.rechercherCoordonneesParIdPersonne(idPersonneLiee);
            boolean ancienNumeroConserve = false;
            for (TelephoneDto telephone : coordonneesPersonneLiee.getTelephones()) {
                if (telephone.getId().equals(idTelModifie)) {
                    ancienNumeroConserve = true;
                    assertNull(Messages.getString("PersonneServiceTest.200"), telephone.getIdext());
                    break;
                }
            }
            assertTrue(Messages.getString("PersonneServiceTest.201"), ancienNumeroConserve);
        }
    }

    /**
     * Test la maj d'un téléphone seulement pour la personne principale.
     */
    @Test
    public void testModifierTelephoneSansImpacterFamilleDepuisNonPorteur() {
        final Long idPersonnePrincipale = 2L;
        final String nouveauNumero = "0546137896";
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonnePrincipale);
        assertEquals(Messages.getString("PersonneServiceTest.203"), 2, coordonnees.getTelephones().size());

        Long idTelModifie = null;
        boolean numeroAModifierTrouve = false;
        for (TelephoneDto telephone : coordonnees.getTelephones()) {
            if (telephone.getId().equals(1L)) {
                numeroAModifierTrouve = true;
                // on change le numero de telephone
                final PaysSimpleDto pays = new PaysSimpleDto();
                pays.setId(1L);
                idTelModifie = telephone.getId();
                telephone.setPays(pays);
                telephone.setNumero(nouveauNumero);
                break;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.204"), numeroAModifierTrouve);
        final CoordonneesDto coordonneesMaj = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // on verifie que l'ancien numéro a été retiré et que le nouveau a bien été ajouté
        assertEquals(Messages.getString("PersonneServiceTest.205"), 2, coordonneesMaj.getTelephones().size());
        boolean nouveauNumeroTrouve = false;
        for (TelephoneDto telephone : coordonneesMaj.getTelephones()) {
            assertFalse(Messages.getString("PersonneServiceTest.206"), telephone.getId().equals(idTelModifie));
            if (telephone.getNumero().equals(nouveauNumero)) {
                nouveauNumeroTrouve = true;
                assertNull(Messages.getString("PersonneServiceTest.207"), telephone.getIdext());
                break;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.208"), nouveauNumeroTrouve);

        // on verifie que l'ancien numéro est toujours sur la famille et que l'id ext a été supprimé
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(idPersonnePrincipale);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            Long idPersonneLiee;
            if (relation.getPersonneSource().getId().equals(idPersonnePrincipale)) {
                idPersonneLiee = relation.getPersonneCible().getId();
            } else {
                idPersonneLiee = relation.getPersonneSource().getId();
            }
            final CoordonneesDto coordonneesPersonneLiee = personneService.rechercherCoordonneesParIdPersonne(idPersonneLiee);
            boolean ancienNumeroConserve = false;
            for (TelephoneDto telephone : coordonneesPersonneLiee.getTelephones()) {
                if (telephone.getId().equals(idTelModifie)) {
                    ancienNumeroConserve = true;
                    assertNotNull(Messages.getString("PersonneServiceTest.209"), telephone.getIdext());
                    break;
                }
            }
            assertTrue(Messages.getString("PersonneServiceTest.210"), ancienNumeroConserve);
        }
    }

    /**
     * Test la suppression d'un téléphone seulement pour la personne principale.
     */
    @Test
    public void testSupprimerTelephoneSansImpacterFamilleDepuisNonPorteur() {
        final Long idPersonnePrincipale = 2L;
        final String nouveauNumero = "";
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonnePrincipale);
        assertEquals(Messages.getString("PersonneServiceTest.212"), 2, coordonnees.getTelephones().size());

        // on change le numero de telephone
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        final Long idTelModifie = coordonnees.getTelephones().get(0).getId();
        coordonnees.getTelephones().get(0).setPays(pays);
        coordonnees.getTelephones().get(0).setNumero(nouveauNumero);
        final CoordonneesDto coordonneesMaj = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // on verifie que l'ancien numéro a été retiré
        assertEquals(Messages.getString("PersonneServiceTest.213"), 1, coordonneesMaj.getTelephones().size());
        for (TelephoneDto telephone : coordonneesMaj.getTelephones()) {
            assertFalse(Messages.getString("PersonneServiceTest.214"), telephone.getId().equals(idTelModifie));
        }

        // on verifie que l'ancien numéro est toujours sur la famille et que l'id ext n'a pas été supprimé
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(idPersonnePrincipale);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            Long idPersonneLiee;
            if (relation.getPersonneSource().getId().equals(idPersonnePrincipale)) {
                idPersonneLiee = relation.getPersonneCible().getId();
            } else {
                idPersonneLiee = relation.getPersonneSource().getId();
            }
            final CoordonneesDto coordonneesPersonneLiee = personneService.rechercherCoordonneesParIdPersonne(idPersonneLiee);
            boolean ancienNumeroConserve = false;
            for (TelephoneDto telephone : coordonneesPersonneLiee.getTelephones()) {
                if (telephone.getId().equals(idTelModifie)) {
                    ancienNumeroConserve = true;
                    assertNotNull(Messages.getString("PersonneServiceTest.215"), telephone.getIdext());
                    break;
                }
            }
            assertTrue(Messages.getString("PersonneServiceTest.216"), ancienNumeroConserve);
        }
    }

    /**
     * Test la maj d'un email seulement pour la personne principale.
     */
    @Test
    public void testModifierEmailSansImpacterFamilleDepuisPorteur() {
        final Long idPersonnePrincipale = 1L;
        final String nouvelEmail = "truc@test.fr";
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonnePrincipale);
        assertEquals(Messages.getString("PersonneServiceTest.218"), 2, coordonnees.getEmails().size());

        // on change l'email
        final Long idEmailModifie = coordonnees.getEmails().get(0).getIdentifiant();
        final String idExtTelModifie = coordonnees.getEmails().get(0).getIdext();
        coordonnees.getEmails().get(0).setAdresse(nouvelEmail);
        final CoordonneesDto coordonneesMaj = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // on verifie que l'ancien numéro a été retiré et que le nouveau a bien été ajouté
        assertEquals(Messages.getString("PersonneServiceTest.219"), 2, coordonneesMaj.getEmails().size());
        boolean nouveauEmailTrouve = false;
        for (EmailDto email : coordonneesMaj.getEmails()) {
            assertFalse(Messages.getString("PersonneServiceTest.220"), email.getIdentifiant().equals(idEmailModifie));
            if (email.getAdresse().equals(nouvelEmail)) {
                nouveauEmailTrouve = true;
                assertEquals(Messages.getString("PersonneServiceTest.221"), idExtTelModifie, email.getIdext());
                break;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.222"), nouveauEmailTrouve);

        // on verifie que l'ancien email est toujours sur la famille et que l'id ext a été supprimé
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(idPersonnePrincipale);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            Long idPersonneLiee;
            if (relation.getPersonneSource().getId().equals(idPersonnePrincipale)) {
                idPersonneLiee = relation.getPersonneCible().getId();
            } else {
                idPersonneLiee = relation.getPersonneSource().getId();
            }
            final CoordonneesDto coordonneesPersonneLiee = personneService.rechercherCoordonneesParIdPersonne(idPersonneLiee);
            boolean ancienEmailConserve = false;
            for (EmailDto email : coordonneesPersonneLiee.getEmails()) {
                if (email.getIdentifiant().equals(idEmailModifie)) {
                    ancienEmailConserve = true;
                    assertNull(Messages.getString("PersonneServiceTest.223"), email.getIdext());
                    break;
                }
            }
            assertTrue(Messages.getString("PersonneServiceTest.224"), ancienEmailConserve);
        }
    }

    /**
     * Test la suppression d'un email seulement pour la personne principale.
     */
    @Test
    public void testSupprimerEmailSansImpacterFamilleDepuisPorteur() {
        final Long idPersonnePrincipale = 1L;
        final String nouvelEmail = "";
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonnePrincipale);
        assertEquals(Messages.getString("PersonneServiceTest.226"), 2, coordonnees.getEmails().size());

        // on change l'email
        final Long idEmailModifie = coordonnees.getEmails().get(0).getIdentifiant();
        coordonnees.getEmails().get(0).setAdresse(nouvelEmail);
        final CoordonneesDto coordonneesMaj = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // on verifie que l'ancien numéro a été retiré
        assertEquals(Messages.getString("PersonneServiceTest.227"), 1, coordonneesMaj.getEmails().size());
        for (EmailDto email : coordonneesMaj.getEmails()) {
            assertFalse(Messages.getString("PersonneServiceTest.228"), email.getIdentifiant().equals(idEmailModifie));
        }

        // on verifie que l'ancien email est toujours sur la famille et que l'id ext a été supprimé
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(idPersonnePrincipale);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            Long idPersonneLiee;
            if (relation.getPersonneSource().getId().equals(idPersonnePrincipale)) {
                idPersonneLiee = relation.getPersonneCible().getId();
            } else {
                idPersonneLiee = relation.getPersonneSource().getId();
            }
            final CoordonneesDto coordonneesPersonneLiee = personneService.rechercherCoordonneesParIdPersonne(idPersonneLiee);
            boolean ancienEmailConserve = false;
            for (EmailDto email : coordonneesPersonneLiee.getEmails()) {
                if (email.getIdentifiant().equals(idEmailModifie)) {
                    ancienEmailConserve = true;
                    assertNull(Messages.getString("PersonneServiceTest.229"), email.getIdext());
                    break;
                }
            }
            assertTrue(Messages.getString("PersonneServiceTest.230"), ancienEmailConserve);
        }
    }

    /**
     * Test la maj d'un email seulement pour la personne principale.
     */
    @Test
    public void testModifierEmailSansImpacterFamilleDepuisNonPorteur() {
        final Long idPersonnePrincipale = 2L;
        final String nouvelEmail = "truc@test.fr";
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonnePrincipale);
        assertEquals(Messages.getString("PersonneServiceTest.232"), 2, coordonnees.getEmails().size());

        // on change l'email
        final Long idEmailModifie = coordonnees.getEmails().get(0).getIdentifiant();
        coordonnees.getEmails().get(0).setAdresse(nouvelEmail);
        final CoordonneesDto coordonneesMaj = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // on verifie que l'ancien numéro a été retiré et que le nouveau a bien été ajouté
        assertEquals(Messages.getString("PersonneServiceTest.233"), 2, coordonneesMaj.getEmails().size());
        boolean nouveauEmailTrouve = false;
        for (EmailDto email : coordonneesMaj.getEmails()) {
            assertFalse(Messages.getString("PersonneServiceTest.234"), email.getIdentifiant().equals(idEmailModifie));
            if (email.getAdresse().equals(nouvelEmail)) {
                nouveauEmailTrouve = true;
                assertNull(Messages.getString("PersonneServiceTest.235"), email.getIdext());
                break;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.236"), nouveauEmailTrouve);

        // on verifie que l'ancien email est toujours sur la famille et que l'id ext a été supprimé
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(idPersonnePrincipale);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            Long idPersonneLiee;
            if (relation.getPersonneSource().getId().equals(idPersonnePrincipale)) {
                idPersonneLiee = relation.getPersonneCible().getId();
            } else {
                idPersonneLiee = relation.getPersonneSource().getId();
            }
            final CoordonneesDto coordonneesPersonneLiee = personneService.rechercherCoordonneesParIdPersonne(idPersonneLiee);
            boolean ancienEmailConserve = false;
            for (EmailDto email : coordonneesPersonneLiee.getEmails()) {
                if (email.getIdentifiant().equals(idEmailModifie)) {
                    ancienEmailConserve = true;
                    assertNotNull(Messages.getString("PersonneServiceTest.237"), email.getIdext());
                    break;
                }
            }
            assertTrue(Messages.getString("PersonneServiceTest.238"), ancienEmailConserve);
        }
    }

    /**
     * Test la suppression d'un email seulement pour la personne principale.
     */
    @Test
    public void testSupprimerEmailSansImpacterFamilleDepuisNonPorteur() {
        final Long idPersonnePrincipale = 2L;
        final String nouvelEmail = "";
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonnePrincipale);
        assertEquals(Messages.getString("PersonneServiceTest.240"), 2, coordonnees.getEmails().size());

        // on change l'email
        final Long idEmailModifie = coordonnees.getEmails().get(0).getIdentifiant();
        coordonnees.getEmails().get(0).setAdresse(nouvelEmail);
        final CoordonneesDto coordonneesMaj = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // on verifie que l'ancien numéro a été retiré
        assertEquals(Messages.getString("PersonneServiceTest.241"), 1, coordonneesMaj.getEmails().size());
        for (EmailDto email : coordonneesMaj.getEmails()) {
            assertFalse(Messages.getString("PersonneServiceTest.242"), email.getIdentifiant().equals(idEmailModifie));
        }

        // on verifie que l'ancien email est toujours sur la famille et que l'id ext a été supprimé
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(idPersonnePrincipale);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            Long idPersonneLiee;
            if (relation.getPersonneSource().getId().equals(idPersonnePrincipale)) {
                idPersonneLiee = relation.getPersonneCible().getId();
            } else {
                idPersonneLiee = relation.getPersonneSource().getId();
            }
            final CoordonneesDto coordonneesPersonneLiee = personneService.rechercherCoordonneesParIdPersonne(idPersonneLiee);
            boolean ancienEmailConserve = false;
            for (EmailDto email : coordonneesPersonneLiee.getEmails()) {
                if (email.getIdentifiant().equals(idEmailModifie)) {
                    ancienEmailConserve = true;
                    assertNotNull(Messages.getString("PersonneServiceTest.243"), email.getIdext());
                    break;
                }
            }
            assertTrue(Messages.getString("PersonneServiceTest.244"), ancienEmailConserve);
        }
    }

    /**
     * Test la maj d'une adresse seulement pour la personne principale.
     */
    @Test
    public void testModifierAdresseSansImpacterFamilleDepuisPorteur() {
        final Long idPersonnePrincipale = 1L;
        final String nouveauNomVoie = "blablabla";
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonnePrincipale);
        assertEquals(Messages.getString("PersonneServiceTest.246"), 4, coordonnees.getAdresses().size());

        final Map<Long, String> mapIdExt = new HashMap<Long, String>();

        final Long idAdresseModifiee = 1L;
        String idExtAdresseModifie = null;
        for (AdresseDto adresse : coordonnees.getAdresses()) {
            mapIdExt.put(adresse.getIdentifiant(), adresse.getIdext());
            if (adresse.getIdentifiant().equals(idAdresseModifiee)) {
                // on change l'adresse
                idExtAdresseModifie = adresse.getIdext();
                adresse.setNomVoie(nouveauNomVoie);
                break;
            }
        }
        assertNotNull(Messages.getString("PersonneServiceTest.247"), idExtAdresseModifie);
        final CoordonneesDto coordonneesMaj = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // on verifie que l'ancienne adresse a été retirée et que le nouveau a bien été ajouté
        assertEquals(Messages.getString("PersonneServiceTest.248"), 4, coordonneesMaj.getAdresses().size());
        boolean nouvelleAdresseTrouvee = false;
        for (AdresseDto adresse : coordonneesMaj.getAdresses()) {
            assertFalse(Messages.getString("PersonneServiceTest.249"), adresse.getIdentifiant().equals(idAdresseModifiee));
            if (mapIdExt.get(adresse.getIdentifiant()) != null) {
                assertEquals(Messages.getString("PersonneServiceTest.250"), mapIdExt.get(adresse.getIdentifiant()), adresse.getIdext());
            }
            if (adresse.getNomVoie().equals(nouveauNomVoie)) {
                nouvelleAdresseTrouvee = true;
                assertEquals(Messages.getString("PersonneServiceTest.251"), idExtAdresseModifie, adresse.getIdext());
                break;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.252"), nouvelleAdresseTrouvee);

        // on verifie que l'ancienne adresse est toujours sur la famille et que l'id ext a été supprimé
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(idPersonnePrincipale);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        assertNotSame(Messages.getString("PersonneServiceTest.253"), 0, listRelation.size());
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            Long idPersonneLiee;
            if (relation.getPersonneSource().getId().equals(idPersonnePrincipale)) {
                idPersonneLiee = relation.getPersonneCible().getId();
            } else {
                idPersonneLiee = relation.getPersonneSource().getId();
            }
            final CoordonneesDto coordonneesPersonneLiee = personneService.rechercherCoordonneesParIdPersonne(idPersonneLiee);
            boolean ancienneAdresseConservee = false;
            for (AdresseDto adresse : coordonneesPersonneLiee.getAdresses()) {
                if (adresse.getIdentifiant().equals(idAdresseModifiee)) {
                    ancienneAdresseConservee = true;
                    assertNull(Messages.getString("PersonneServiceTest.254"), adresse.getIdext());
                    break;
                }
            }
            assertTrue(Messages.getString("PersonneServiceTest.255"), ancienneAdresseConservee);
        }
    }

    /**
     * Test la maj d'une adresse seulement pour la personne principale.
     */
    @Test
    public void testModifierAdresseSansImpacterFamilleDepuisNonPorteur() {
        final Long idPersonnePrincipale = 2L;
        final String nouveauNomVoie = "blablabla";
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonnePrincipale);
        assertEquals(Messages.getString("PersonneServiceTest.257"), 2, coordonnees.getAdresses().size());

        final Long idAdresseModifiee = 1L;
        String idExtAdresseModifie = null;
        for (AdresseDto adresse : coordonnees.getAdresses()) {
            if (adresse.getIdentifiant().equals(idAdresseModifiee)) {
                // on change l'adresse
                idExtAdresseModifie = adresse.getIdext();
                adresse.setNomVoie(nouveauNomVoie);
                break;
            }
        }
        final CoordonneesDto coordonneesMaj = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // on verifie que l'ancienne adresse a été retirée et que le nouveau a bien été ajouté
        assertEquals(Messages.getString("PersonneServiceTest.258"), 2, coordonneesMaj.getAdresses().size());
        boolean nouvelleAdresseTrouvee = false;
        for (AdresseDto adresse : coordonneesMaj.getAdresses()) {
            assertFalse(Messages.getString("PersonneServiceTest.259"), adresse.getIdentifiant().equals(idAdresseModifiee));
            if (adresse.getNomVoie().equals(nouveauNomVoie)) {
                nouvelleAdresseTrouvee = true;
                assertNull(Messages.getString("PersonneServiceTest.260"), adresse.getIdext());
                break;
            }
        }
        assertTrue(Messages.getString("PersonneServiceTest.261"), nouvelleAdresseTrouvee);

        // on verifie que l'ancienne adresse est toujours sur la famille et que l'id ext a été supprimé
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(idPersonnePrincipale);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            Long idPersonneLiee;
            if (relation.getPersonneSource().getId().equals(idPersonnePrincipale)) {
                idPersonneLiee = relation.getPersonneCible().getId();
            } else {
                idPersonneLiee = relation.getPersonneSource().getId();
            }
            final CoordonneesDto coordonneesPersonneLiee = personneService.rechercherCoordonneesParIdPersonne(idPersonneLiee);
            boolean ancienneAdresseConservee = false;
            for (AdresseDto adresse : coordonneesPersonneLiee.getAdresses()) {
                if (adresse.getIdentifiant().equals(idAdresseModifiee)) {
                    ancienneAdresseConservee = true;
                    assertNotNull(Messages.getString("PersonneServiceTest.262"), adresse.getIdext());
                    break;
                }
            }
            assertTrue(Messages.getString("PersonneServiceTest.263"), ancienneAdresseConservee);
        }
    }

    /** Test du service mettreAJourCoordonneesExtId. */
    @Test
    public void testMettreAJourCoordonneesExtId() {
        final Long idPersonne = 2L;
        final String idExtAdresse = "123abc";
        final String idExtTel1 = "az123er";
        final String idExtTel2 = "qs456df";
        final String idExtEmail = "wx789cv";
        final CoordonneesDto coordonnees = new CoordonneesDto();
        coordonnees.setIdPersonne(idPersonne);
        final List<AdresseDto> adresses = new ArrayList<AdresseDto>();
        final AdresseDto adresse = new AdresseDto();
        adresse.setIdentifiant(2L);
        adresse.setIdext(idExtAdresse);
        adresse.setPorteurUid(idPersonne);
        adresses.add(adresse);
        coordonnees.setAdresses(adresses);
        final List<TelephoneDto> telephones = new ArrayList<TelephoneDto>();
        final TelephoneDto telephone1 = new TelephoneDto();
        telephone1.setId(1L);
        telephone1.setIdext(idExtTel1);
        telephone1.setPorteurUid(idPersonne);
        telephones.add(telephone1);
        final TelephoneDto telephone2 = new TelephoneDto();
        telephone2.setId(3L);
        telephone2.setIdext(idExtTel2);
        telephone2.setPorteurUid(idPersonne);
        telephones.add(telephone2);
        coordonnees.setTelephones(telephones);
        final List<EmailDto> emails = new ArrayList<EmailDto>();
        final EmailDto email = new EmailDto();
        email.setIdentifiant(3L);
        email.setIdext(idExtEmail);
        email.setPorteurUid(idPersonne);
        emails.add(email);
        coordonnees.setEmails(emails);
        personneService.mettreAJourCoordonneesExtId(coordonnees);

        final CoordonneesDto coordonneesModifiees = personneService.rechercherCoordonneesParIdPersonne(idPersonne);
        assertEquals(Messages.getString("PersonneServiceTest.268"), 2, coordonneesModifiees.getAdresses().size());
        assertEquals(Messages.getString("PersonneServiceTest.269"), idExtAdresse, coordonnees.getAdresses().get(0).getIdext());
        assertEquals(Messages.getString("PersonneServiceTest.270"), idPersonne, coordonnees.getAdresses().get(0).getPorteurUid());
        assertEquals(Messages.getString("PersonneServiceTest.271"), 2, coordonneesModifiees.getTelephones().size());
        assertEquals(Messages.getString("PersonneServiceTest.272"), idExtTel1, coordonnees.getTelephones().get(0).getIdext());
        assertEquals(Messages.getString("PersonneServiceTest.273"), idPersonne, coordonnees.getTelephones().get(0).getPorteurUid());
        assertEquals(Messages.getString("PersonneServiceTest.274"), idExtTel2, coordonnees.getTelephones().get(1).getIdext());
        assertEquals(Messages.getString("PersonneServiceTest.275"), idPersonne, coordonnees.getTelephones().get(1).getPorteurUid());
        assertEquals(Messages.getString("PersonneServiceTest.276"), 2, coordonneesModifiees.getEmails().size());
        assertEquals(Messages.getString("PersonneServiceTest.277"), idExtEmail, coordonnees.getEmails().get(0).getIdext());
        assertEquals(Messages.getString("PersonneServiceTest.278"), idPersonne, coordonnees.getEmails().get(0).getPorteurUid());
    }

    /** Test de la modification d'un téléphone. */
    @Test
    public void testModifierTelephone() {
        final TelephoneDto telephone = new TelephoneDto();
        telephone.setId(1L);
        telephone.setIdext("1");
        telephone.setNature(new IdentifiantLibelleDto(1L));
        final String numero = "0545123456";
        telephone.setNumero(numero);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephone.setPays(pays);
        telephone.setPorteurUid(1L);

        // Personne inexistante.
        try {
            personneService.creerOuMettreAJourTelephone(20L, telephone, true, false);
            fail(Messages.getString("PersonneServiceTest.281"));
        } catch (BusinessException e) {
            assertEquals(Messages.getString("PersonneServiceTest.282"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), e
                    .getMessage());
        }

        // Personne existante
        final TelephoneDto telephoneModifie = personneService.creerOuMettreAJourTelephone(1L, telephone, true, false);
        assertEquals(Messages.getString("PersonneServiceTest.283"), 1L, telephoneModifie.getId());
        assertEquals(Messages.getString("PersonneServiceTest.284"), numero, telephoneModifie.getNumero());
    }

    /** Test de la modification d'un téléphone. */
    @Test
    public void testModifierTelephoneAvecDesactivationEservice() {
        // Personne adherente avec eservice d'envoi par sms
        final Long idPersonne = 4L;

        final TelephoneDto telephone = new TelephoneDto();
        telephone.setId(1L);
        telephone.setIdext("1");
        telephone.setNature(new IdentifiantLibelleDto(1L));
        final String numero = "";
        telephone.setNumero(numero);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephone.setPays(pays);
        telephone.setPorteurUid(1L);

        // exception car eservice
        try {
            personneService.creerOuMettreAJourTelephone(idPersonne, telephone, true, false);
            fail(Messages.getString("PersonneServiceTest.287"));
        } catch (ConfirmationDesactivationEserviceException e) {
            assertEquals(Messages.getString("PersonneServiceTest.288"), messageSourceUtil.get(PersonneKeyUtil.MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_TELEPHONE), e
                    .getMessage());
        }

        // forcage de la desactivation du eservice
        final TelephoneDto telephoneModifie = personneService.creerOuMettreAJourTelephone(idPersonne, telephone, true, true);
        assertNull(Messages.getString("PersonneServiceTest.289"), telephoneModifie.getId());
        assertNull(Messages.getString("PersonneServiceTest.290"), telephoneModifie.getNumero());
    }

    /** Test de la modification d'un téléphone. */
    @Test
    public void testModifierTelephoneSansDesactivationEservice() {
        // Personne adherente sans eservice d'envoi par sms
        final Long idPersonne = 5L;

        final TelephoneDto telephone = new TelephoneDto();
        telephone.setId(1L);
        telephone.setIdext("1");
        telephone.setNature(new IdentifiantLibelleDto(1L));
        final String numero = "";
        telephone.setNumero(numero);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephone.setPays(pays);
        telephone.setPorteurUid(1L);

        final TelephoneDto telephoneModifie = personneService.creerOuMettreAJourTelephone(idPersonne, telephone, true, false);
        assertNull(Messages.getString("PersonneServiceTest.293"), telephoneModifie.getId());
        assertNull(Messages.getString("PersonneServiceTest.294"), telephoneModifie.getNumero());
    }

    /** Test de la modification d'un email. */
    @Test
    public void testModifierEmail() {
        final EmailDto email = new EmailDto();
        email.setIdentifiant(1L);
        email.setIdext("1");
        email.setNatureEmail(new IdentifiantLibelleDto(1L));
        final String adresse = "test@scub.net";
        email.setAdresse(adresse);
        email.setPorteurUid(1L);

        // Personne inexistante.
        try {
            personneService.creerOuMettreAJourEmail(20L, email, true, false);
            fail(Messages.getString("PersonneServiceTest.297"));
        } catch (BusinessException e) {
            assertEquals(Messages.getString("PersonneServiceTest.298"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), e
                    .getMessage());
        }

        // Personne existante
        final EmailDto emailModifie = personneService.creerOuMettreAJourEmail(1L, email, true, false);
        assertEquals(Messages.getString("PersonneServiceTest.299"), 1L, emailModifie.getIdentifiant());
        assertEquals(Messages.getString("PersonneServiceTest.300"), adresse, emailModifie.getAdresse());
    }

    /** Test de la modification d'un email. */
    @Test
    public void testModifierEmailAvecDesactivationEservice() {
        // Personne adherente avec eservice d'envoi par email
        final Long idPersonne = 4L;

        final EmailDto email = new EmailDto();
        email.setIdentifiant(1L);
        email.setIdext("1");
        email.setNatureEmail(new IdentifiantLibelleDto(1L));
        final String adresse = "";
        email.setAdresse(adresse);
        email.setPorteurUid(1L);

        // exception car eservice
        try {
            personneService.creerOuMettreAJourEmail(idPersonne, email, true, false);
            fail(Messages.getString("PersonneServiceTest.303"));
        } catch (ConfirmationDesactivationEserviceException e) {
            assertEquals(Messages.getString("PersonneServiceTest.304"), messageSourceUtil.get(PersonneKeyUtil.MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_EMAIL), e
                    .getMessage());
        }

        // Personne existante
        final EmailDto emailModifie = personneService.creerOuMettreAJourEmail(idPersonne, email, true, true);
        assertNull(Messages.getString("PersonneServiceTest.305"), emailModifie.getIdentifiant());
        assertNull(Messages.getString("PersonneServiceTest.306"), emailModifie.getAdresse());
    }

    /** Test de la modification d'un email. */
    @Test
    public void testModifierEmailSansDesactivationEservice() {
        // Personne adherente sans eservice d'envoi par email
        final Long idPersonne = 5L;

        final EmailDto email = new EmailDto();
        email.setIdentifiant(1L);
        email.setIdext("1");
        email.setNatureEmail(new IdentifiantLibelleDto(1L));
        final String adresse = "";
        email.setAdresse(adresse);
        email.setPorteurUid(1L);

        // Personne existante
        final EmailDto emailModifie = personneService.creerOuMettreAJourEmail(idPersonne, email, true, false);
        assertNull(Messages.getString("PersonneServiceTest.309"), emailModifie.getIdentifiant());
        assertNull(Messages.getString("PersonneServiceTest.310"), emailModifie.getAdresse());
    }

    /** Test de la modification d'un email et d'un tel. */
    @Test
    public void testModifierEmailEtTelAvecDesactivationEservice() {
        // Personne adherente avec eservice d'envoi par email
        final Long idPersonne = 4L;

        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonne);
        assertEquals(Messages.getString("PersonneServiceTest.311"), 1, coordonnees.getEmails().size());
        assertEquals(Messages.getString("PersonneServiceTest.312"), 2, coordonnees.getTelephones().size());
        assertEquals(Messages.getString("PersonneServiceTest.313"), "0505050505", coordonnees.getTelephones().get(0).getNumero());
        assertEquals(Messages.getString("PersonneServiceTest.315"), squareMappingService.getIdNatureTelephoneFixe(), coordonnees.getTelephones().get(0)
                .getNature().getIdentifiant());

        coordonnees.getEmails().get(0).setAdresse("");
        coordonnees.getTelephones().get(1).setNumero("");

        // exception car eservice
        try {
            personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);
            fail(Messages.getString("PersonneServiceTest.318"));
        } catch (ConfirmationDesactivationEserviceException e) {
            assertEquals(Messages.getString("PersonneServiceTest.319"), messageSourceUtil
                    .get(PersonneKeyUtil.MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_TELEPHONE_ET_EMAIL), e.getMessage());
        }

        // Personne existante
        final CoordonneesDto coordonneesModifiees = personneService.creerOuMettreAJourCoordonnees(coordonnees, false, true);
        assertEquals(Messages.getString("PersonneServiceTest.320"), 0, coordonneesModifiees.getEmails().size());
        assertEquals(Messages.getString("PersonneServiceTest.321"), 1, coordonneesModifiees.getTelephones().size());
        assertEquals(Messages.getString("PersonneServiceTest.322"), "0505050505", coordonneesModifiees.getTelephones().get(0).getNumero());
    }

    /** Test de desactivation de relations. */
    @Test
    public void testDesactiverRelations() {
        final Calendar date = Calendar.getInstance();
        date.clear();
        date.set(2012, 0, 1);

        final int nbRelationsADesactiver = 3;

        // Récupération des relations à désactiver
        List<Long> idsRelations = personneService.rechercherIdsRelationsADesactiver(date, Integer.MAX_VALUE);
        assertEquals(Messages.getString("PersonneServiceTest.324"), nbRelationsADesactiver, idsRelations.size());

        final int nbDesactivees = personneService.desactiverRelations(idsRelations);
        assertEquals(Messages.getString("PersonneServiceTest.325"), nbRelationsADesactiver, nbDesactivees);

        idsRelations = personneService.rechercherIdsRelationsADesactiver(date, Integer.MAX_VALUE);
        assertEquals(Messages.getString("PersonneServiceTest.326"), 0, idsRelations.size());
    }

    private PersonneDto creerPersonnePhysique() {
        final PersonneDto personne = new PersonneDto();
        personne.setIdext("5");
        personne.setCivilite(new IdentifiantLibelleDto(1L));
        personne.setNom("Nom");
        personne.setPrenom("prenom");
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        final int year = 2000;
        cal.set(year, Calendar.JANUARY, 1);
        personne.setDateNaissance(cal);
        personne.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personne.setCommercial(commercial());
        return personne;
    }

    private TelephoneDto creerTelephone() {
        final TelephoneDto telephone = new TelephoneDto();
        telephone.setNature(new IdentifiantLibelleDto(1L, null));
        telephone.setNumero("0123456789");
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephone.setPays(pays);
        return telephone;
    }

    private EmailDto creerEmail() {
        final EmailDto emailDto = new EmailDto();
        emailDto.setIdentifiant(1L);
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse("email1@square.com");
        return emailDto;
    }

    private AdresseDto creerAdresse() {
        final AdresseDto adresseDto = new AdresseDto();
        adresseDto.setNumVoie("1");
        adresseDto.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseDto.setNomVoie("nomVoie");
        adresseDto.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseDto.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance()));
        return adresseDto;
    }

    private DimensionRessourceDto commercial() {
        final DimensionRessourceDto dimensionRessourceDto = new DimensionRessourceDto();
        dimensionRessourceDto.setIdentifiant(1L);
        dimensionRessourceDto.setNom("nom");
        dimensionRessourceDto.setPrenom("prenom");
        return dimensionRessourceDto;
    }

}
