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
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.RessourceCriteresRechercheDto;
import com.square.core.model.dto.RessourceDto;
import com.square.core.model.dto.RessourceRechercheDto;
import com.square.core.service.interfaces.RessourceService;
import com.square.core.util.PersonnePhysiqueKeyUtil;

/**
 * Classe de test des services des ressources.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class RessourceServiceTest extends DbunitBaseTestCase {

    /**
     * RessourceService.
     */
    RessourceService ressourceService;

    /**
     * Max Count Lucene Value.
     */
    private static final int MAX_LUCENE_VALUE = 100;

    /**
     * Méthode appelée avant chaque test unitaire.
     */

    /**
     * Gestion des messages.
     */
    private MessageSourceUtil messageSourceUtil;

    @Before
    public void setUp() {
        ressourceService = (RessourceService) getBeanSpring("ressourceService");
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        createSecureContext("user", "user");
    }

    @Override
    protected String getXmlDateSet() {
        return "datasetRessource.xml";
    }

    @Override
    protected Class[] getClassManualIndexChanges() {
        return new Class[] {Ressource.class};
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"squareMappingContext.xml"};
    }

    /**
     * Test de la récupération d'une ressource par son nom.
     */
    @Test
    public void testRechercherRessourceParNom() {
        // Création des critères de recherche.
        final RessourceCriteresRechercheDto criteres = new RessourceCriteresRechercheDto();

        // criteres.setNom("Nom trois");

        final RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<RessourceCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<RessourceRechercheDto> result = ressourceService.rechercherRessourceFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("RessourceServiceTest.6"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("RessourceServiceTest.7"), 4, result.getTotalResults());


        // Recherche avec %
        criteres.setNom("%Nom%");
        final List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        final RemotePagingSort rps = new RemotePagingSort("triNom", RemotePagingSort.REMOTE_PAGING_SORT_DESC);
        listeSorts.add(rps);
        criteresAvecPagination.setListeSorts(listeSorts);
        criteresAvecPagination.setCriterias(criteres);

        final RemotePagingResultsDto<RessourceRechercheDto> resultatAvecApproximation =
            ressourceService.rechercherRessourceFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("RessourceServiceTest.10"), resultatAvecApproximation);
        // Tests des valeurs de retour.

        assertEquals(Messages.getString("RessourceServiceTest.11"), 3, resultatAvecApproximation.getTotalResults());
        // assertEquals("Le premier nom ne correspond pas", "Nom un", resultatAvecApproximation.getListResults().get(0).getNom());
        // assertEquals("Le second nom ne correspond pas", "Nom trois", resultatAvecApproximation.getListResults().get(1).getNom());
        // assertEquals("Le troisième nom ne correspond pas", "Nom deux", resultatAvecApproximation.getListResults().get(2).getNom());

        boolean trouvee = false;
        for (RessourceRechercheDto ressource : resultatAvecApproximation.getListResults()) {
            if (ressource.getId().equals(1L)) {
                trouvee = true;
            }
        }
        assertTrue(Messages.getString("RessourceServiceTest.12"), trouvee);
    }

    /**
     * Test de la récupération d'une ressource par son Prenom.
     */
    @Test
    public void testRechercherRessourceParPrenom() {
        // Création des critères de recherche.
        final RessourceCriteresRechercheDto criteres = new RessourceCriteresRechercheDto();

        criteres.setPrenom("Prenom");

        final RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<RessourceCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<RessourceRechercheDto> result = ressourceService.rechercherRessourceFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("RessourceServiceTest.14"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("RessourceServiceTest.15"), 3, result.getTotalResults());

        // Recherche avec %
        criteres.setPrenom("%Prenom%");
        final List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        final RemotePagingSort rps = new RemotePagingSort("triPrenom", RemotePagingSort.REMOTE_PAGING_SORT_ASC);
        listeSorts.add(rps);
        criteresAvecPagination.setListeSorts(listeSorts);
        criteresAvecPagination.setCriterias(criteres);

        final RemotePagingResultsDto<RessourceRechercheDto> resultatAvecApproximation =
            ressourceService.rechercherRessourceFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("RessourceServiceTest.18"), resultatAvecApproximation);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("RessourceServiceTest.19"), 3, resultatAvecApproximation.getTotalResults());
        // assertEquals("Le premier nom ne correspond pas", "Prénom deux", resultatAvecApproximation.getListResults().get(0).getPrenom());
        // assertEquals("Le second nom ne correspond pas", "Prénom trois", resultatAvecApproximation.getListResults().get(1).getPrenom());
        // assertEquals("Le troisième nom ne correspond pas", "Prénom un", resultatAvecApproximation.getListResults().get(2).getPrenom());

        boolean trouvee = false;
        for (RessourceRechercheDto ressource : resultatAvecApproximation.getListResults()) {
            if (ressource.getId().equals(1L)) {
                trouvee = true;
            }
        }
        assertTrue(Messages.getString("RessourceServiceTest.20"), trouvee);
    }

    /**
     * Test de la récupération d'une ressource par Agences.
     */
    @Test
    public void testRechercherRessourceParAgence() {
        // Création des critères de recherche.
        final RessourceCriteresRechercheDto criteres = new RessourceCriteresRechercheDto();

        List<Long> list = new ArrayList<Long>();
        list.add(1L);
        criteres.setIdAgences(list);

        final RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<RessourceCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<RessourceRechercheDto> result = ressourceService.rechercherRessourceFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("RessourceServiceTest.21"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("RessourceServiceTest.22"), 1, result.getTotalResults());

        assertEquals(Messages.getString("RessourceServiceTest.23"), 2L, result.getListResults().get(0).getId());

    }

    /**
     * Test de la récupération d'une ressource par Services.
     */
    @Test
    public void testRechercherRessourceParService() {
        // Création des critères de recherche.
        final RessourceCriteresRechercheDto criteres = new RessourceCriteresRechercheDto();

        List<Long> list = new ArrayList<Long>();
        list.add(1L);
        criteres.setIdServices(list);

        final RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<RessourceCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<RessourceRechercheDto> result = ressourceService.rechercherRessourceFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("RessourceServiceTest.24"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("RessourceServiceTest.25"), 1, result.getTotalResults());

        assertEquals(Messages.getString("RessourceServiceTest.26"), 1L, result.getListResults().get(0).getId());

    }

    /**
     * Test de la récupération d'une ressource par Etats.
     */
    @Test
    public void testRechercherRessourceParEtat() {
        // Création des critères de recherche.
        final RessourceCriteresRechercheDto criteres = new RessourceCriteresRechercheDto();

        List<Long> list = new ArrayList<Long>();
        list.add(1L);
        criteres.setIdEtats(list);

        final RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<RessourceCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<RessourceRechercheDto> result = ressourceService.rechercherRessourceFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("RessourceServiceTest.27"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("RessourceServiceTest.28"), 1, result.getTotalResults());

        assertEquals(Messages.getString("RessourceServiceTest.29"), 1L, result.getListResults().get(0).getId());
    }

    /**
     * Test de la récupération d'une ressource par Fonctions.
     */
    @Test
    public void testRechercherRessourceParFonction() {
        // Création des critères de recherche.
        final RessourceCriteresRechercheDto criteres = new RessourceCriteresRechercheDto();

        List<Long> list = new ArrayList<Long>();
        list.add(1L);
        criteres.setIdFonctions(list);

        final RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<RessourceCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<RessourceRechercheDto> result = ressourceService.rechercherRessourceFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("RessourceServiceTest.30"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("RessourceServiceTest.31"), 1, result.getTotalResults());

        assertEquals(Messages.getString("RessourceServiceTest.32"), 1L, result.getListResults().get(0).getId());
    }

    /**
     * Test de la recherche de resource par identifiant.
     */
    @Test
    public void testRechercherPersonneParIdentifiant() {
        // Test avec une personne non existante
        try {
            ressourceService.rechercherRessourceParIdentifiant(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("RessourceServiceTest.33"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), be.getMessage());
        }

        // Test avec une personne non existante
        try {
            ressourceService.rechercherRessourceParIdentifiant(1000L);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("RessourceServiceTest.34"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), be.getMessage());
        }

        final RessourceDto ressourceDto = ressourceService.rechercherRessourceParIdentifiant(1L);
        assertEquals(Messages.getString("RessourceServiceTest.35"), 1L, ressourceDto.getId());
        assertEquals(Messages.getString("RessourceServiceTest.36"), "Nom trois", ressourceDto.getNom());
        assertEquals(Messages.getString("RessourceServiceTest.38"), "Prénom un", ressourceDto.getPrenom());
    }

    /**
     * Test de la recherche de resource par external identifiant.
     */
    @Test
    public void testRechercherPersonneParEid() {
        // Test avec une personne non existante
        try {
            ressourceService.rechercherRessourceParEid(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("RessourceServiceTest.40"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), be.getMessage());
        }

        // Test avec une personne non existante
        try {
            ressourceService.rechercherRessourceParEid("1000");
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("RessourceServiceTest.42"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), be.getMessage());
        }

        final RessourceDto ressourceDto = ressourceService.rechercherRessourceParEid("1500");
        assertEquals(Messages.getString("RessourceServiceTest.44"), 1L, ressourceDto.getId());
        assertEquals(Messages.getString("RessourceServiceTest.45"), "Nom trois", ressourceDto.getNom());
        assertEquals(Messages.getString("RessourceServiceTest.47"), "Prénom un", ressourceDto.getPrenom());
    }
    
    /** Test du service rechercherIdsRessourcesFullTextParCriteres. */
    @Test
    public void testRechercherIdsRessourcesFullTextParCriteres() {
        // Création des critères de recherche.
        final RessourceCriteresRechercheDto criteres = new RessourceCriteresRechercheDto();

        // Sans critères
        List<Long> result = ressourceService.rechercherIdsRessourcesFullTextParCriteres(criteres);
        assertEquals(Messages.getString("RessourceServiceTest.49"), 4, result.size());


        // Recherche sur le nom
        criteres.setNom("%Nom%");
        result = ressourceService.rechercherIdsRessourcesFullTextParCriteres(criteres);
        assertEquals(Messages.getString("RessourceServiceTest.51"), 3, result.size());
        boolean ressource1Trouvee = false;
        boolean ressource2Trouvee = false;
        boolean ressource3Trouvee = false;
        for (Long idRessource : result) {
            if (idRessource.equals(1L)) {
                ressource1Trouvee = true;
            }
            else if (idRessource.equals(2L)) {
                ressource2Trouvee = true;
            }
            else if (idRessource.equals(3L)) {
                ressource3Trouvee = true;
            }
            else {
                fail(Messages.getString("RessourceServiceTest.52"));
            }
        }
        assertTrue(Messages.getString("RessourceServiceTest.53"), ressource1Trouvee && ressource2Trouvee && ressource3Trouvee);

        // Recherche sur le nom et la fonction
        final List<Long> listFonctions = new ArrayList<Long>();
        listFonctions.add(1L);
        criteres.setIdFonctions(listFonctions);
        result = ressourceService.rechercherIdsRessourcesFullTextParCriteres(criteres);
        assertEquals(Messages.getString("RessourceServiceTest.54"), 1, result.size());
        assertEquals(Messages.getString("RessourceServiceTest.55"), 1L, result.get(0));

        // Recherche sur le nom et le service
        final List<Long> listServices = new ArrayList<Long>();
        listServices.add(2L);
        criteres.setIdFonctions(null);
        criteres.setIdServices(listServices);
        result = ressourceService.rechercherIdsRessourcesFullTextParCriteres(criteres);
        assertEquals(Messages.getString("RessourceServiceTest.56"), 2, result.size());
        boolean ressource2TrouveeService = false;
        boolean ressource3TrouveeService = false;
        for (Long idRessource : result) {
            if (idRessource.equals(2L)) {
                ressource2TrouveeService = true;
            }
            else if (idRessource.equals(3L)) {
                ressource3TrouveeService = true;
            }
            else {
                fail(Messages.getString("RessourceServiceTest.57"));
            }
        }
        assertTrue(Messages.getString("RessourceServiceTest.58"), ressource2TrouveeService && ressource3TrouveeService);
    }
}
