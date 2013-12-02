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
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.dao.interfaces.CampagneStatutDao;
import com.square.core.model.dto.CampagneCriteresRechercheDto;
import com.square.core.model.dto.CampagneCriteresRechercheLibelle;
import com.square.core.model.dto.CampagneDto;
import com.square.core.model.dto.CampagneRechercheDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.service.interfaces.CampagneService;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.util.CampagneKeyUtil;

/**
 * Classe de tests pour les campagnes.
 * @author cblanchard - SCUB
 */
public class CampagneServiceTest extends DbunitBaseTestCase {

    /** Le service des tables de dimensions. */
    private DimensionService dimensionService;

    /** Le service des campagnes. */
    private CampagneService campagneService;

    /** messageSourceUtil. */
    private MessageSourceUtil messageSourceUtil;

    /** Message d'erreur. */
    private static String messageErreur = Messages.getString("CampagneServiceTest.0");

    /** Mesage d'erreur lorsqu'une campagne retrouvée n'est pas bonne. */
    private static String messageErreurMauvaiseCampagne = Messages.getString("CampagneServiceTest.1");

    /** Message d'erreur lorsque le message attendu ne correspond pas. */
    private static String messageErreurMauvaisMessage = Messages.getString("CampagneServiceTest.2");

    /** Message statut actif. */
    private static String active = "Active";

    /** Libelle 1. */
    private static String libelle1 = "Libelle Campagne 1";

    /**
     * Méthode appelée avant chaque test.
     */
    @Before
    public void setUp() {
        dimensionService = (DimensionService) getBeanSpring("dimensionService");
        campagneService = (CampagneService) getBeanSpring("campagneService");
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        createSecureContext("user", "user");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"squareMappingContext.xml"};
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected String getXmlDateSet() {
        return "datasetCampagneService.xml";
    }

    /**
     * Test de recherche des types de campagne.
     */
    @Test
    public void testRechercherTypeCampagne() {
        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherTypesCampagnes(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("CampagneServiceTest.12"), messageErreur, be.getMessage());
        }

        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setVisible(null);
        result = dimensionService.rechercherTypesCampagnes(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.13"), 3, result.size());

        // Test de la recherche par identifiant.
        criteres.setId(1L);
        result = dimensionService.rechercherTypesCampagnes(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.14"), 1, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.15"), "TypeCampagne1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("TypeCampagne2");
        result = dimensionService.rechercherTypesCampagnes(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.18"), 1, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.19"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par par approximation.
        criteres.setId(null);
        criteres.setLibelle("Typ");
        result = dimensionService.rechercherTypesCampagnes(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.21"), 3, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.22"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherTypesCampagnes(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.23"), 2, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.24"), 2L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("CampagneServiceTest.25"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.26"), 1L, result.get(1).getIdentifiant());
    }

    /**
     * Test de recherche des types de campagne.
     */
    @Test
    public void testRechercherStatutCampagne() {
        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherStatutsCampagnes(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("CampagneServiceTest.27"), messageErreur, be.getMessage());
        }

        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setVisible(null);
        result = dimensionService.rechercherStatutsCampagnes(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.28"), 3, result.size());

        // Test de la recherche par identifiant.
        criteres.setId(1L);
        result = dimensionService.rechercherStatutsCampagnes(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.29"), 1, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.30"), active, result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Inactive");
        result = dimensionService.rechercherStatutsCampagnes(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.32"), 1, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.33"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par par approximation.
        criteres.setId(null);
        criteres.setLibelle("act");
        result = dimensionService.rechercherStatutsCampagnes(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.35"), 1, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.36"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherStatutsCampagnes(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.37"), 2, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.38"), 2L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("CampagneServiceTest.39"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.40"), 1L, result.get(1).getIdentifiant());
    }

    /**
     * Test de la recherche de campagnes par critères.
     */
    @Test
    public void testRechercherCampagneParCriteres() {

        // Test de la recherche avec null
        try {
            campagneService.rechercherCampagnesParCriteres(null);
            fail(Messages.getString("CampagneServiceTest.41"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERROR_CAMPAGNE_SEARCH_DTO_NULL), be.getMessage());
        }

        try {
            campagneService.rechercherCampagnesParCriteres(new RemotePagingCriteriasDto<CampagneCriteresRechercheDto>(null, 0, Integer.MAX_VALUE));
            fail(Messages.getString("CampagneServiceTest.42"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_DTO_RECHERCHE_NULL), be.getMessage());
        }

        RemotePagingResultsDto<CampagneRechercheDto> resultPagination = new RemotePagingResultsDto<CampagneRechercheDto>();
        // Test de la recherche avec aucun critères
        resultPagination =
            campagneService.rechercherCampagnesParCriteres(new RemotePagingCriteriasDto<CampagneCriteresRechercheDto>(new CampagneCriteresRechercheDto(), 0,
                Integer.MAX_VALUE));
        List<CampagneRechercheDto> result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.43"), 3, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.44"), 1L, result.get(0).getId());
        assertEquals(Messages.getString("CampagneServiceTest.45"), "1111", result.get(0).getCode());
        assertEquals(Messages.getString("CampagneServiceTest.47"), libelle1, result.get(0).getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.48"), 1L, result.get(0).getStatut().getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.49"), active, result.get(0).getStatut().getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.50"), 1, result.get(0).getDateDebut().get(Calendar.DAY_OF_MONTH));

        // Test de la recherche avec un code fournit
        RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteresPagination =
            new RemotePagingCriteriasDto<CampagneCriteresRechercheDto>(null, 0, Integer.MAX_VALUE);
        CampagneCriteresRechercheDto criteres = new CampagneCriteresRechercheDto();
        criteres.setCode("2222");
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.52"), 1, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.53"), 2L, result.get(0).getId());
        assertEquals(Messages.getString("CampagneServiceTest.54"), "2222", result.get(0).getCode());
        assertEquals(Messages.getString("CampagneServiceTest.56"), "Libelle Campagne 2", result.get(0).getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.58"), 1L, result.get(0).getStatut().getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.59"), active, result.get(0).getStatut().getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.60"), 2, result.get(0).getDateDebut().get(Calendar.DAY_OF_MONTH));

        // Test de la recherche avec un libelle de fournit
        criteresPagination = new RemotePagingCriteriasDto<CampagneCriteresRechercheDto>(null, 0, Integer.MAX_VALUE);
        criteres = new CampagneCriteresRechercheDto();
        criteres.setLibelle("Libelle Campagne 2");
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.62"), 1, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.63"), 2L, result.get(0).getId());
        assertEquals(Messages.getString("CampagneServiceTest.64"), "2222", result.get(0).getCode());
        assertEquals(Messages.getString("CampagneServiceTest.66"), "Libelle Campagne 2", result.get(0).getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.68"), 1L, result.get(0).getStatut().getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.69"), active, result.get(0).getStatut().getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.70"), 2, result.get(0).getDateDebut().get(Calendar.DAY_OF_MONTH));

        // Test de la recherche avec un type
        criteres.setLibelle(null);
        List<Long> listeTypes = new ArrayList<Long>();
        listeTypes.add(2L);
        criteres.setListeTypes(listeTypes);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.71"), 2, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.72"), 2L, result.get(0).getId());
        assertEquals(Messages.getString("CampagneServiceTest.73"), 3L, result.get(1).getId());

        // Test de la recherche avec plusieurs types
        listeTypes = new ArrayList<Long>();
        listeTypes.add(1L);
        listeTypes.add(2L);
        criteres.setListeTypes(listeTypes);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.74"), 3, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.75"), 1L, result.get(0).getId());
        assertEquals(Messages.getString("CampagneServiceTest.76"), 2L, result.get(1).getId());

        // Test de la recherche avec un statut
        criteres.setListeTypes(null);
        List<Long> listeStatuts = new ArrayList<Long>();
        listeStatuts.add(2L);
        criteres.setListeStatuts(listeStatuts);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.77"), 1, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.78"), 3L, result.get(0).getId());

        // Test de la recherche avec un créateur.
        // Test de la recherche avec plusieurs status
        listeStatuts = new ArrayList<Long>();
        listeStatuts.add(1L);
        listeStatuts.add(2L);
        criteres.setListeStatuts(listeTypes);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.79"), 3, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.80"), 1L, result.get(0).getId());

        // Test de la recherche avec une date de début pour la date de début.
        criteres.setListeStatuts(null);
        criteres.setIdCreateur(2L);
        criteresPagination.setCriterias(criteres);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.81"), 1, result.size());
        assertEquals(Messages.getString("CampagneServiceTest.82"), 3L, result.get(0).getId());

        // Test de la recherche avec une date de début pour la date de début.
        criteres.setIdCreateur(null);
        final Calendar dateDebutInf = Calendar.getInstance();
        dateDebutInf.clear();
        final int year = 2010;
        dateDebutInf.set(year, Calendar.FEBRUARY, 1);
        criteres.setDateDebutBorneInf(dateDebutInf);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.83"), 2, result.size());
        assertEquals(messageErreurMauvaiseCampagne, 2L, result.get(0).getId());

        // Test de la recherche avec une date de fin pour la date de début.
        criteres.setDateDebutBorneInf(null);
        final Calendar dateDebutSup = Calendar.getInstance();
        dateDebutSup.clear();
        dateDebutSup.set(year, Calendar.MARCH, 1);
        criteres.setDateDebutBorneSup(dateDebutSup);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.84"), 2, result.size());
        assertEquals(messageErreurMauvaiseCampagne, 1L, result.get(0).getId());

        // Test de la recherche avec une date de fin et une date de début pour la date de début.
        criteres.setDateDebutBorneInf(dateDebutInf);
        criteres.setDateDebutBorneSup(dateDebutSup);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.85"), 1, result.size());
        assertEquals(messageErreurMauvaiseCampagne, 2L, result.get(0).getId());

        // Test de la recherche avec une date de début pour la date de fin.
        criteres.setDateDebutBorneInf(null);
        criteres.setDateDebutBorneSup(null);
        final Calendar dateFinBorneInf = Calendar.getInstance();
        dateFinBorneInf.clear();
        final int yearFin = 2012;
        dateFinBorneInf.set(yearFin, Calendar.FEBRUARY, 1);
        criteres.setDateFinBorneInf(dateFinBorneInf);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.86"), 2, result.size());
        assertEquals(messageErreurMauvaiseCampagne, 2L, result.get(0).getId());

        // Test de la recherche avec une date de fin pour la date de fin.
        criteres.setDateFinBorneInf(null);
        final Calendar dateFinBorneSup = Calendar.getInstance();
        dateFinBorneSup.clear();
        dateFinBorneSup.set(yearFin, Calendar.APRIL, 1);
        criteres.setDateFinBorneSup(dateFinBorneSup);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.87"), 2, result.size());
        assertEquals(messageErreurMauvaiseCampagne, 1L, result.get(0).getId());

        // Test de la recherche avec une date de fin et une date de début pour la date de fin.
        criteres.setDateFinBorneInf(dateFinBorneInf);
        criteres.setDateFinBorneSup(dateFinBorneSup);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.88"), 1, result.size());
        assertEquals(messageErreurMauvaiseCampagne, 2L, result.get(0).getId());

        // Test de la recherche avec une date de début et une date de fin renseigné.
        criteres.setDateFinBorneInf(dateFinBorneInf);
        criteres.setDateFinBorneSup(dateFinBorneSup);
        criteres.setDateDebutBorneInf(dateDebutInf);
        criteres.setDateDebutBorneSup(dateDebutSup);
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.89"), 1, result.size());
        assertEquals(messageErreurMauvaiseCampagne, 2L, result.get(0).getId());

        // Test sur l'odre
        criteresPagination = new RemotePagingCriteriasDto<CampagneCriteresRechercheDto>(new CampagneCriteresRechercheDto(), 0, Integer.MAX_VALUE);
        criteresPagination.setCriterias(new CampagneCriteresRechercheDto());
        final RemotePagingSort remotePagingSort = new RemotePagingSort();
        remotePagingSort.setSortAsc(RemotePagingSort.REMOTE_PAGING_SORT_DESC);
        remotePagingSort.setSortField("id");
        final List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        listeSorts.add(remotePagingSort);
        criteresPagination.setListeSorts(listeSorts);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        assertEquals(Messages.getString("CampagneServiceTest.91"), 3, resultPagination.getTotalResults());
        assertEquals(messageErreurMauvaiseCampagne, 3L, resultPagination.getListResults().get(0).getId());
    }

    /**
     * Test sur la recherche de campagnes par libelle.
     */
    @Test
    public void testRechercherCampagneParLibelle() {
        List<IdentifiantLibelleDto> resultat = new ArrayList<IdentifiantLibelleDto>();

        // Test avec aucun libelle
        resultat = campagneService.rechercherCampagnesParLibelle(new CampagneCriteresRechercheLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.92"), 2, resultat.size());

        // Test avec un libelle incomplet
        final CampagneCriteresRechercheLibelle criteres = new CampagneCriteresRechercheLibelle();
        criteres.setMaxResult(Integer.MAX_VALUE);
        criteres.setLibelle("Lib");
        resultat = campagneService.rechercherCampagnesParLibelle(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.94"), 2, resultat.size());
        assertEquals(Messages.getString("CampagneServiceTest.95"), 1L, resultat.get(0).getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.96"), libelle1, resultat.get(0).getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.97"), 2L, resultat.get(1).getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.98"), "Libelle Campagne 2", resultat.get(1).getLibelle());

        // Test avec un libelle complet
        criteres.setLibelle(libelle1);
        resultat = campagneService.rechercherCampagnesParLibelle(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.100"), 1, resultat.size());
        assertEquals(Messages.getString("CampagneServiceTest.101"), 1L, resultat.get(0).getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.102"), libelle1, resultat.get(0).getLibelle());

        // Réaliser le test sur le maxResult
        criteres.setLibelle(null);
        criteres.setMaxResult(1);
        resultat = campagneService.rechercherCampagnesParLibelle(criteres);
        assertEquals(Messages.getString("CampagneServiceTest.103"), 1, resultat.size());
        assertEquals(Messages.getString("CampagneServiceTest.104"), 1L, resultat.get(0).getIdentifiant());
    }

    /**
     * Test des messages d'erreur pour la création d'une campagne.
     */
    @Test
    public void testErreurCreationCampagne() {

        // Test avec un dto null
        try {
            campagneService.creerCampagne(null);
            fail(Messages.getString("CampagneServiceTest.105"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERROR_CAMPAGNE_SEARCH_DTO_NULL), be.getMessage());
        }

        // Test avec aucun champ de renseigné
        try {
            campagneService.creerCampagne(new CampagneDto());
        }
        catch (ControleIntegriteException ce) {
            assertEquals(Messages.getString("CampagneServiceTest.106"), 4, ce.getRapport().getRapports().size());
        }

        // Créations des données pour le test
        final CampagneDto campagneDto = new CampagneDto();
        final Calendar dateDebut = Calendar.getInstance();
        dateDebut.add(Calendar.YEAR, 2);
        final Calendar dateFin = Calendar.getInstance();
        dateFin.add(Calendar.YEAR, 3);
        final IdentifiantLibelleDto statut = new IdentifiantLibelleDto(1L, active);
        final IdentifiantLibelleDto type = new IdentifiantLibelleDto(1L, "typeCampagne1");
        final String libelle = "Libelle Campagne 5";

        // Test avec aucun libelle
        creerDonneesCampagneDto(null, statut, type, dateDebut, dateFin, campagneDto);
        try {
            campagneService.creerCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.109"));
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_LIBELLE_CAMPAGNE_NULL), ce.getRapport()
                    .getRapports().get("CampagneDto.libelle").getMessage());
        }

        // Test avec un libelle déjà existant
        creerDonneesCampagneDto(libelle1, statut, type, dateDebut, dateFin, campagneDto);
        try {
            campagneService.creerCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.111"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_LIBELLE_CAMPAGNE_EXITANT), be.getMessage());
        }

        // Test avec aucun type
        creerDonneesCampagneDto(libelle, statut, null, dateDebut, dateFin, campagneDto);
        try {
            campagneService.creerCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.112"));
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_TYPE_CAMPAGNE_NULL), ce.getRapport().getRapports()
                    .get(Messages.getString("CampagneServiceTest.113")).getMessage());
        }

        // Test avec un type inexistant
        creerDonneesCampagneDto(libelle, statut, new IdentifiantLibelleDto(10L, "typeCampagne"), dateDebut, dateFin, campagneDto);
        try {
            campagneService.creerCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.115"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_TYPE_CAMPAGNE_INEXISTANT), be.getMessage());
        }

        // Test avec aucune date de début
        creerDonneesCampagneDto(libelle, statut, type, null, dateFin, campagneDto);
        try {
            campagneService.creerCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.116"));
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_DATE_DEBUT_CAMPAGNE_NULL), ce.getRapport()
                    .getRapports().get("CampagneDto.dateDebut").getMessage());
        }

        // Test avec aucune date de fin
        creerDonneesCampagneDto(libelle, statut, type, dateDebut, null, campagneDto);
        try {
            campagneService.creerCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.118"));
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_DATE_FIN_CAMPAGNE_NULL), ce.getRapport()
                    .getRapports().get("CampagneDto.dateFin").getMessage());
        }

        // Test avec une date de fin < date de début
        final Calendar mauvaiseDateFin = Calendar.getInstance();
        mauvaiseDateFin.add(Calendar.YEAR, 1);
        creerDonneesCampagneDto(libelle, statut, type, dateDebut, mauvaiseDateFin, campagneDto);
        try {
            campagneService.creerCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.120"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_DATE_FIN_INFERIEURE_DATE_DEBUT), be
                    .getMessage());
        }

        // Test avec une date de début < date du jour
        final Calendar mauvaiseDateDebut = Calendar.getInstance();
        mauvaiseDateDebut.clear();
        final int mauvaiseAnneeDebut = 2000;
        mauvaiseDateDebut.set(mauvaiseAnneeDebut, Calendar.JANUARY, 1);
        creerDonneesCampagneDto(libelle, statut, type, mauvaiseDateDebut, dateFin, campagneDto);
        try {
            campagneService.creerCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.121"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_DATE_DEBUT_INFERIEURE_DATE_JOUR), be
                    .getMessage());
        }

        // Test avec une date de fin < date du jour
        final Calendar mauvaiseDateFinDateJour = Calendar.getInstance();
        mauvaiseDateFinDateJour.clear();
        final int mauvaiseAnneeFin = 2000;
        mauvaiseDateFinDateJour.set(mauvaiseAnneeFin, Calendar.JANUARY, 1);
        creerDonneesCampagneDto(libelle, statut, type, dateDebut, mauvaiseDateFinDateJour, campagneDto);
        try {
            campagneService.creerCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.122"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_DATE_FIN_INFERIEURE_DATE_JOUR), be
                    .getMessage());
        }
    }

    /**
     * Test de la création.
     */
    @Test
    public void testCreationCampagne() {
        // Test de la création avec tous les champs replit
        // Créations des données pour le test
        final CampagneDto campagneDto = new CampagneDto();
        final Calendar dateDebut = Calendar.getInstance();
        dateDebut.add(Calendar.YEAR, 1);
        final Calendar dateFin = Calendar.getInstance();
        dateFin.add(Calendar.YEAR, 2);
        final IdentifiantLibelleDto type = new IdentifiantLibelleDto(1L, "typeCampagne1");
        final String libelle = "Libelle Campangne 5";

        creerDonneesCampagneDto(libelle, null, type, dateDebut, dateFin, campagneDto);

        final CampagneDto resultat = campagneService.creerCampagne(campagneDto);
        assertEquals(Messages.getString("CampagneServiceTest.3"), 4L, resultat.getId());
        assertEquals(Messages.getString("CampagneServiceTest.126"), 3, (campagneService.rechercherCampagnesParLibelle(new CampagneCriteresRechercheLibelle())).size());
    }

    /**
     * Test du service de récupération de campagne par identifiant.
     */
    @Test
    public void testRechercheCampagneParId() {
        // Test avec un identifiant null
        try {
            campagneService.rechercherCampagnesParId(null);
            fail(Messages.getString("CampagneServiceTest.127"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreur, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_ID_NULL), be.getMessage());
        }

        // Test avec un identifiant inexistant
        try {
            campagneService.rechercherCampagnesParId(10L);
        }
        catch (BusinessException be) {
            assertEquals(messageErreur, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_ID_INEXISTANT), be.getMessage());
        }

        // Vérification des valeurs reçues
        final CampagneDto resultat = campagneService.rechercherCampagnesParId(1L);
        assertEquals(Messages.getString("CampagneServiceTest.128"), 1L, resultat.getId());
        assertEquals(Messages.getString("CampagneServiceTest.129"), "1111", resultat.getCode());
        assertEquals(Messages.getString("CampagneServiceTest.131"), libelle1, resultat.getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.132"), 1L, resultat.getStatut().getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.133"), active, resultat.getStatut().getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.134"), 1L, resultat.getType().getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.135"), "TypeCampagne1", resultat.getType().getLibelle());
        final int yearDebut = 2010;
        assertEquals(Messages.getString("CampagneServiceTest.137"), yearDebut, resultat.getDateDebut().get(Calendar.YEAR));
        final int yearFin = 2012;
        assertEquals(Messages.getString("CampagneServiceTest.138"), yearFin, resultat.getDateFin().get(Calendar.YEAR));
        assertEquals(Messages.getString("CampagneServiceTest.139"), "stat", resultat.getDescriptif());
    }

    /**
     * Test de la modification d'une campagne.
     */
    @Test
    public void testErreursModification() {
        // Test avec un dto null
        try {
            campagneService.modifierCampagne(null);
            fail(Messages.getString("CampagneServiceTest.141"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERROR_CAMPAGNE_SEARCH_DTO_NULL), be.getMessage());
        }

        // Test sans id
        try {
            campagneService.modifierCampagne(new CampagneDto());
            fail(Messages.getString("CampagneServiceTest.142"));
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("CampagneServiceTest.143"), messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_ID_NULL), be.getMessage());
        }

        // Créations des données pour le test
        final CampagneDto campagneDto = new CampagneDto();
        final Calendar dateDebut = Calendar.getInstance();
        dateDebut.clear();
        final int year = 2010;
        dateDebut.set(year, Calendar.JANUARY, 1);
        final Calendar dateFin = Calendar.getInstance();
        dateFin.clear();
        final int yearFin = 2012;
        dateFin.set(yearFin, Calendar.JANUARY, 1);
        final IdentifiantLibelleDto statut = new IdentifiantLibelleDto(1L, active);
        final IdentifiantLibelleDto type = new IdentifiantLibelleDto(1L, "typeCampagne1");
        final String libelle = "Libelle Campagne 5";
        campagneDto.setCode("1010");
        // Test avec un identifiant inexistant
        campagneDto.setId(10L);
        creerDonneesCampagneDto(libelle, statut, type, dateDebut, dateFin, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.147"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_ID_INEXISTANT), be.getMessage());
        }

        // Test avec un identifiant mais aucune autre données
        campagneDto.setId(1L);
        creerDonneesCampagneDto(null, null, null, null, null, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.148"));
        }
        catch (ControleIntegriteException ce) {
            assertEquals(Messages.getString("CampagneServiceTest.149"), 5, ce.getRapport().getRapports().size());
        }

        // Test avec aucun libelle
        creerDonneesCampagneDto(null, statut, type, dateDebut, dateFin, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.150"));
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_LIBELLE_CAMPAGNE_NULL), ce.getRapport()
                    .getRapports().get("CampagneDto.libelle").getMessage());
        }

        // Test avec un libelle déjà existant
        creerDonneesCampagneDto("Libelle Campagne 2", statut, type, dateDebut, dateFin, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.153"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_LIBELLE_CAMPAGNE_EXITANT), be.getMessage());
        }

        // Test avec aucun statut
        creerDonneesCampagneDto(libelle, null, type, dateDebut, dateFin, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(messageErreurMauvaisMessage);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_STATUT_CAMPAGNE_NULL), ce.getRapport().getRapports()
                    .get("CampagneDto.statut").getMessage());
        }

        // Test avec un statut inexistant en base
        creerDonneesCampagneDto(libelle, new IdentifiantLibelleDto(10L, "Statut"), type, dateDebut, dateFin, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.156"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_STATUT_CAMPAGNE_INEXISTANT), be.getMessage());
        }

        // Test avec aucun type
        creerDonneesCampagneDto(libelle, statut, null, dateDebut, dateFin, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.157"));
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_TYPE_CAMPAGNE_NULL), ce.getRapport().getRapports()
                    .get("CampagneDto.type").getMessage());
        }

        // Test avec un type inexistant
        creerDonneesCampagneDto(libelle, statut, new IdentifiantLibelleDto(10L, "typeCampagne"), dateDebut, dateFin, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.160"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_TYPE_CAMPAGNE_INEXISTANT), be.getMessage());
        }

        // Test avec aucune date de début
        creerDonneesCampagneDto(libelle, statut, type, null, dateFin, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.161"));
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_DATE_DEBUT_CAMPAGNE_NULL), ce.getRapport()
                    .getRapports().get("CampagneDto.dateDebut").getMessage());
        }

        // Test avec aucune date de fin
        creerDonneesCampagneDto(libelle, statut, type, dateDebut, null, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.163"));
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_DATE_FIN_CAMPAGNE_NULL), ce.getRapport()
                    .getRapports().get("CampagneDto.dateFin").getMessage());
        }

        // Test avec une date de fin < date de début
        final Calendar mauvaiseDateFin = Calendar.getInstance();
        mauvaiseDateFin.clear();
        final int mauvaiseAnnee = 2009;
        mauvaiseDateFin.set(mauvaiseAnnee, Calendar.JANUARY, 1);
        creerDonneesCampagneDto(libelle, statut, type, dateDebut, mauvaiseDateFin, campagneDto);
        try {
            campagneService.modifierCampagne(campagneDto);
            fail(Messages.getString("CampagneServiceTest.165"));
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_DATE_FIN_INFERIEURE_DATE_DEBUT), be
                    .getMessage());
        }
    }

    /**
     * Test Modficiation.
     */
    @Test
    public void testModification() {
        // Création des données
        final CampagneDto campagneDto = new CampagneDto();
        campagneDto.setId(1L);
        campagneDto.setCode("101010");
        final Calendar dateDebut = Calendar.getInstance();
        dateDebut.clear();
        final int yearDebut = 2011;
        dateDebut.set(yearDebut, Calendar.JANUARY, 1);
        campagneDto.setDateDebut(dateDebut);
        final Calendar dateFin = Calendar.getInstance();
        dateFin.clear();
        final int yearFin = 2015;
        dateFin.set(yearFin, Calendar.JANUARY, 1);
        campagneDto.setDateFin(dateFin);
        campagneDto.setDescriptif("Descriptif");
        campagneDto.setLibelle("Nouvelle Campagne");
        campagneDto.setStatut(new IdentifiantLibelleDto(2L, "Statut 2"));
        campagneDto.setType(new IdentifiantLibelleDto(2L, "Type 2"));

        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setLibelle("s");
        final int sizeAvant = ((CampagneStatutDao) getBeanSpring("campagneStatutDao")).rechercherCampagneStatutParCriteres(criteres).size();

        // Appel au service
        final CampagneDto resultat = campagneService.modifierCampagne(campagneDto);

        // Vérification de l'enregistrement
        assertEquals(Messages.getString("CampagneServiceTest.173"), 1L, resultat.getId());
        assertEquals(Messages.getString("CampagneServiceTest.174"), "101010", resultat.getCode());
        assertEquals(Messages.getString("CampagneServiceTest.176"), "Nouvelle Campagne", resultat.getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.178"), 2L, resultat.getStatut().getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.179"), "Inactive", resultat.getStatut().getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.181"), 1L, resultat.getType().getIdentifiant());
        assertEquals(Messages.getString("CampagneServiceTest.182"), "TypeCampagne1", resultat.getType().getLibelle());
        assertEquals(Messages.getString("CampagneServiceTest.184"), yearDebut, resultat.getDateDebut().get(Calendar.YEAR));
        assertEquals(Messages.getString("CampagneServiceTest.185"), yearFin, resultat.getDateFin().get(Calendar.YEAR));
        assertEquals(Messages.getString("CampagneServiceTest.186"), "Descriptif", resultat.getDescriptif());

        final int sizeApres = ((CampagneStatutDao) getBeanSpring("campagneStatutDao")).rechercherCampagneStatutParCriteres(criteres).size();
        assertEquals(Messages.getString("CampagneServiceTest.189"), sizeAvant, sizeApres);
    }

    /**
     * Test la recherche d'une liste des campagnes.
     */
    @Test
    public void testRechercherCampagneParCritereLib() {
        // Test de la recherche avec un libelle de fournit
        RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteresPagination =
            new RemotePagingCriteriasDto<CampagneCriteresRechercheDto>(null, 0, Integer.MAX_VALUE);
        CampagneCriteresRechercheDto criteres = new CampagneCriteresRechercheDto();
        criteres.setLibelle("*Libelle Campagne");
        criteresPagination.setCriterias(criteres);
        RemotePagingResultsDto<CampagneRechercheDto> resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        List<CampagneRechercheDto> result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.191"), 3, result.size());

        // Test de la recherche avec un libelle de fournit
        criteresPagination = new RemotePagingCriteriasDto<CampagneCriteresRechercheDto>(null, 0, Integer.MAX_VALUE);
        criteres = new CampagneCriteresRechercheDto();
        criteres.setLibelle("*Libelle Campagne 1");
        criteresPagination.setCriterias(criteres);
        resultPagination = campagneService.rechercherCampagnesParCriteres(criteresPagination);
        result = resultPagination.getListResults();
        assertEquals(Messages.getString("CampagneServiceTest.193"), 1, result.size());
    }

    /** Méthode privée permettant de creer les données d'une campagne. */
    private void creerDonneesCampagneDto(String libelle, IdentifiantLibelleDto statut, IdentifiantLibelleDto type, Calendar dateDebut, Calendar dateFin,
        CampagneDto campagneDto) {
        campagneDto.setLibelle(libelle);
        campagneDto.setStatut(statut);
        campagneDto.setType(type);
        campagneDto.setDateDebut(dateDebut);
        campagneDto.setDateFin(dateFin);
    }
}
