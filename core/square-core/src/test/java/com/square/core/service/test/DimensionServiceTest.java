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
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.model.dto.ActionNatureResultatCriteresRechercheDto;
import com.square.core.model.dto.CaisseDto;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CommuneCriteresRechercherDto;
import com.square.core.model.dto.DimensionCritereRechercheCodePostalCommuneDto;
import com.square.core.model.dto.DimensionCritereRechercheDepartementDto;
import com.square.core.model.dto.DimensionCritereRechercheObjetDto;
import com.square.core.model.dto.DimensionCritereRechercheRessourceFonctionDto;
import com.square.core.model.dto.DimensionCritereRechercheResultatActionDto;
import com.square.core.model.dto.DimensionCritereRechercheSousObjetDto;
import com.square.core.model.dto.DimensionCritereRechercheTypeRelationDto;
import com.square.core.model.dto.DimensionCriteresRechercheCaisseDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.DimensionCriteresRechercheRegimeDto;
import com.square.core.model.dto.DimensionCriteresRechercheRessourceDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.IdentifiantEidLibelleDto;
import com.square.core.model.dto.IdentifiantLibelleCodePostalCommuneDto;
import com.square.core.model.dto.IdentifiantLibelleDepartementCodeDto;
import com.square.core.model.dto.IdentifiantLibelleTypeRelationDto;
import com.square.core.model.dto.ModeleEmailDto;
import com.square.core.model.dto.PaysSimpleDto;
import com.square.core.service.interfaces.DimensionService;

/**
 * Classe de test des services sur les tables de dimensions.
 * @author cblanchard - SCUB
 */
public class DimensionServiceTest extends DbunitBaseTestCase {

    private static final String NOMBRE_RESULTATS_PAS_BON = Messages.getString("DimensionServiceTest.0");

    private static final String LIBELLE_COMMUNE_PAS_BON = Messages.getString("DimensionServiceTest.1");

    private static final String IDENTIFIANT_COMMUNE_PAS_BON = Messages.getString("DimensionServiceTest.2");

    private static final String LIBELLE_CODE_POSTAL_PAS_BON = Messages.getString("DimensionServiceTest.3");

    private static final String IDENTIFIANT_CODE_POSTAL_PAS_BON = Messages.getString("DimensionServiceTest.4");

    private static final String LIBELLE_ACHEMINEMENT_PAS_BON = Messages.getString("DimensionServiceTest.5");

    private static final String IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON = Messages.getString("DimensionServiceTest.6");

    private static final String COMMUNE1 = "Commune1";

    private static final String CODE_POSTAL_001 = "001";

    private static final String LIBELLE_PAS_BON = Messages.getString("DimensionServiceTest.9");

    private static final String EID_PAS_BON = Messages.getString("DimensionServiceTest.10");

    private static final String IDENTIFIANT_PAS_BON = Messages.getString("DimensionServiceTest.11");

    private static final String EID_1 = "1";

    private static final String IDENTIFIANT_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS =
        Messages.getString("DimensionServiceTest.13");

    private static final String LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_COMMUNE =
        Messages.getString("DimensionServiceTest.14");

    private static final String LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_CODE =
        Messages.getString("DimensionServiceTest.15");

    private static final String LE_CODE_NE_CORRESPOND_PAS = Messages.getString("DimensionServiceTest.16");

    private static final long CHIFFRE_70 = 70L;

    private static final String COMMUNE2 = "Commune2";

    private static final String TRIPLE_ZERO = "000";

    private static final String IDENTIFIANT_RECHERCHE_NATURE_NE_CORRESPOND_PAS = Messages.getString("DimensionServiceTest.19");

    /**
     * Le service des tables de dimensions.
     */
    private DimensionService dimensionService;

    /**
     * Message d'erreur.
     */
    private static String messageErreur = Messages.getString("DimensionServiceTest.20");

    /**
     * Méthode appelée avant chaque test.
     */
    @Before
    public void setUp() {
        dimensionService = (DimensionService) getBeanSpring("dimensionService");
        createSecureContext("user", "user");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"squareMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return super.getXmlDateSet();
    }

    /**
     * Test sur la recherche des natures de personne physique.
     */
    @Test
    public void testRechercheNaturePersonnePhysique() {
        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherNaturePersonnePhysiqueParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.7"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherNaturePersonnePhysiqueParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.26"), 6, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherNaturePersonnePhysiqueParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.27"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.28"), "Nature 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Nature 2");
        result = dimensionService.rechercherNaturePersonnePhysiqueParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.31"), 1, result.size());
        assertEquals(IDENTIFIANT_RECHERCHE_NATURE_NE_CORRESPOND_PAS, 2L, result.get(0).getIdentifiant());

        // Test de la recherche approximative.
        criteres.setId(null);
        criteres.setLibelle("Nature");
        result = dimensionService.rechercherNaturePersonnePhysiqueParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.33"), 4, result.size());
        assertEquals(IDENTIFIANT_RECHERCHE_NATURE_NE_CORRESPOND_PAS, 1L, result.get(2).getIdentifiant());

        // Test sur le maxresults
        criteres.setLibelle(null);
        criteres.setMaxResults(1);
        result = dimensionService.rechercherNaturePersonnePhysiqueParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.34"), 1, result.size());
        assertEquals(IDENTIFIANT_RECHERCHE_NATURE_NE_CORRESPOND_PAS, 2L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setMaxResults(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherNaturePersonnePhysiqueParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.35"), 6, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.36"), 1L, result.get(2).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(IDENTIFIANT_RECHERCHE_NATURE_NE_CORRESPOND_PAS, 1L, result.get(2).getIdentifiant());
        assertEquals(IDENTIFIANT_RECHERCHE_NATURE_NE_CORRESPOND_PAS, 3L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des natures de voies.
     */
    @Test
    public void testRechercheNatureVoie() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherNatureVoieParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.37"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherNatureVoieParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.38"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherNatureVoieParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.39"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.40"), "type1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("type2");
        result = dimensionService.rechercherNatureVoieParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.43"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.44"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("%typ%");
        result = dimensionService.rechercherNatureVoieParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.46"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.47"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité.
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherNatureVoieParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.48"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.49"), 1L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.50"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.51"), 3L, result.get(2).getIdentifiant());
    }

    /**
     * Test sur la recherche des codes postaux.
     */
    @Test
    public void testRechercheCodePostaux() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherCodePostauxParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.52"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherCodePostauxParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.53"), 4, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherCodePostauxParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.54"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.55"), TRIPLE_ZERO, result.get(0).getLibelle());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle(CODE_POSTAL_001);
        result = dimensionService.rechercherCodePostauxParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.56"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.57"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("0");
        result = dimensionService.rechercherCodePostauxParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.59"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.60"), 1L, result.get(1).getIdentifiant());

        // Test de la recherche par visibilité.
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherCodePostauxParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.61"), 4, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.62"), 1L, result.get(1).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.63"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.64"), 1L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des villes.
     */
    @Test
    public void testRechercheVille() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherCommuneParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.65"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherCommuneParCriteres(new CommuneCriteresRechercherDto());
        assertEquals(Messages.getString("DimensionServiceTest.66"), 8, result.size());

        // Test de la recherche par identifiant.
        final CommuneCriteresRechercherDto criteres = new CommuneCriteresRechercherDto();
        final DimensionCriteresRechercheDto criteresDimensions = new DimensionCriteresRechercheDto();
        criteresDimensions.setId(1L);
        criteres.setDimensionCriteres(criteresDimensions);
        result = dimensionService.rechercherCommuneParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.67"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.68"), COMMUNE1, result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteresDimensions.setId(null);
        criteresDimensions.setLibelle(COMMUNE2);
        criteres.setDimensionCriteres(criteresDimensions);
        result = dimensionService.rechercherCommuneParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.69"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.70"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteresDimensions.setId(null);
        criteresDimensions.setLibelle("Com");
        criteres.setDimensionCriteres(criteresDimensions);
        result = dimensionService.rechercherCommuneParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.72"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.73"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteresDimensions.setLibelle(null);
        criteresDimensions.setVisible(true);
        criteres.setDimensionCriteres(criteresDimensions);
        result = dimensionService.rechercherCommuneParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.74"), 8, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.75"), 1L, result.get(0).getIdentifiant());

        // Test du code postal
        criteres.setDimensionCriteres(null);
        criteres.setIdCodePostal(1L);
        result = dimensionService.rechercherCommuneParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.76"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.77"), 1L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.78"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.79"), 3L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des civilités.
     */
    @Test
    public void testRechercheCivilite() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherCiviliteParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.80"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherCiviliteParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.81"), 2, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherCiviliteParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.82"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.83"), "Monsieur", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Madame");
        result = dimensionService.rechercherCiviliteParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.86"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.87"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("M");
        result = dimensionService.rechercherCiviliteParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.89"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.90"), 1L, result.get(1).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherCiviliteParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.91"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.92"), 1L, result.get(1).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.93"), 1L, result.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.94"), 2L, result.get(0).getIdentifiant());
    }

    /**
     * Test sur la recherche de la nature des téléphones.
     */
    @Test
    public void testRechercheNatureTelephone() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherNatureTelephoneParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.95"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherNatureTelephoneParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.96"), 2, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherNatureTelephoneParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.97"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.98"), "Nature téléphone 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Nature téléphone 2");
        result = dimensionService.rechercherNatureTelephoneParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.101"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.102"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("Nature télép");
        result = dimensionService.rechercherNatureTelephoneParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.104"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.105"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherNatureTelephoneParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.106"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.107"), 1L, result.get(0)
                .getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.108"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.109"), 2L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des professions.
     */
    @Test
    public void testRechercheProfessions() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherProfessionParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.110"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherProfessionParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.111"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherProfessionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.112"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.113"), "Situation pro 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Situation pro 2");
        result = dimensionService.rechercherProfessionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.116"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.117"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("Situation");
        result = dimensionService.rechercherProfessionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.119"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.120"), 1L, result.get(2).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherProfessionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.121"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.122"), 1L, result.get(2).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.123"), 3L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.124"), 2L, result.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.125"), 1L, result.get(2).getIdentifiant());
    }

    /**
     * Test sur la recherche des pays.
     */
    @Test
    public void testRecherchePays() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherPaysParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.126"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherPaysParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.127"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherPaysParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.128"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.129"), "France", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Allemagne");
        result = dimensionService.rechercherPaysParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.132"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.133"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("All");
        result = dimensionService.rechercherPaysParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.135"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.136"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherPaysParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.137"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.138"), CHIFFRE_70, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.139"), CHIFFRE_70, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.140"), 1L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des pays.
     */
    @Test
    public void testRechercheSimplePays() {

        List<PaysSimpleDto> result = null;

        try {
            result = dimensionService.rechercherSimplePaysParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.141"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherSimplePaysParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.142"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherSimplePaysParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.143"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.144"), "France", result.get(0).getLibelle());

        // Test indicatif de telephone
        assertEquals(Messages.getString("DimensionServiceTest.146"), 1, result.get(0).getIndicatifTelephone());

        // Test code pays
        assertEquals(Messages.getString("DimensionServiceTest.147"), "1", result.get(0).getCodeISO());
        // Test Format telephone
        assertEquals(Messages.getString("DimensionServiceTest.149"), "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",
            result.get(0).getFormatTelephone());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Allemagne");
        result = dimensionService.rechercherSimplePaysParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.152"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.153"), 2L, result.get(0).getId());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("All");
        result = dimensionService.rechercherSimplePaysParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.155"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.156"), 2L, result.get(0).getId());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherSimplePaysParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.157"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.158"), 70L, result.get(0).getId());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.159"), 70L, result.get(0).getId());
        assertEquals(Messages.getString("DimensionServiceTest.160"), 1L, result.get(1).getId());
    }

    /**
     * Test sur la recherche des caisses.
     */
    /**
     * Test sur la recherche des caisses.
     */
    @Test
    public void testRechercheCaisses() {
        List<CaisseSimpleDto> result = null;

        try {
            result = dimensionService.rechercherCaisseParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.161"), messageErreur, be.getMessage());
        }

        DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        DimensionCriteresRechercheCaisseDto caisseCritere = new DimensionCriteresRechercheCaisseDto(criteres);

        // Test de la recherche de caisse sans criteres
        result = dimensionService.rechercherCaisseParCriteres(caisseCritere);
        assertEquals(Messages.getString("DimensionServiceTest.162"), 13, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.163"), 1L, result.get(0).getId());
        assertEquals(Messages.getString("DimensionServiceTest.164"), 2L, result.get(1).getId());
        assertEquals(Messages.getString("DimensionServiceTest.165"), 3L, result.get(2).getId());
        assertEquals(Messages.getString("DimensionServiceTest.166"), 4L, result.get(3).getId());
        assertEquals(Messages.getString("DimensionServiceTest.167"), 5L, result.get(4).getId());
        assertEquals(Messages.getString("DimensionServiceTest.168"), 6L, result.get(5).getId());
        assertEquals(Messages.getString("DimensionServiceTest.169"), 7L, result.get(6).getId());
        assertEquals(Messages.getString("DimensionServiceTest.170"), 8L, result.get(7).getId());
        assertEquals(Messages.getString("DimensionServiceTest.171"), 9L, result.get(8).getId());

        // Test de la recherche par identifiant.
        criteres = new DimensionCriteresRechercheDto();
        caisseCritere = new DimensionCriteresRechercheCaisseDto(criteres);
        criteres.setId(1L);
        result = dimensionService.rechercherCaisseParCriteres(caisseCritere);
        assertEquals(Messages.getString("DimensionServiceTest.172"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.173"), "Centre nom 1", result.get(0).getNom());

        // Test de la recherche par libelle.
        criteres = new DimensionCriteresRechercheDto();
        caisseCritere = new DimensionCriteresRechercheCaisseDto(criteres);
        criteres.setId(null);
        criteres.setLibelle("Centre nom 2");
        result = dimensionService.rechercherCaisseParCriteres(caisseCritere);
        assertEquals(Messages.getString("DimensionServiceTest.176"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.177"), 2L, result.get(0).getId());

        // Test de la recherche par libelle.
        criteres = new DimensionCriteresRechercheDto();
        caisseCritere = new DimensionCriteresRechercheCaisseDto(criteres);
        criteres.setId(null);
        criteres.setLibelle("ANG");
        result = dimensionService.rechercherCaisseParCriteres(caisseCritere);
        assertEquals(Messages.getString("DimensionServiceTest.179"), 9, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.180"), 3L, result.get(0).getId());
        assertEquals(Messages.getString("DimensionServiceTest.181"), 4L, result.get(1).getId());
        assertEquals(Messages.getString("DimensionServiceTest.182"), 5L, result.get(2).getId());
        assertEquals(Messages.getString("DimensionServiceTest.183"), 6L, result.get(3).getId());

        // Test de la recherche par libelle et regime.
        criteres = new DimensionCriteresRechercheDto();
        caisseCritere = new DimensionCriteresRechercheCaisseDto(criteres);
        caisseCritere.setIdRegime(4L);
        criteres.setLibelle("ANG");
        result = dimensionService.rechercherCaisseParCriteres(caisseCritere);
        assertEquals(Messages.getString("DimensionServiceTest.185"), 0, result.size());

        // Test de la recherche par approximation.
        criteres = new DimensionCriteresRechercheDto();
        caisseCritere = new DimensionCriteresRechercheCaisseDto(criteres);
        criteres.setLibelle("Centre");
        result = dimensionService.rechercherCaisseParCriteres(caisseCritere);
        assertEquals(Messages.getString("DimensionServiceTest.187"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.188"), 1L, result.get(0).getId());

        // Test de la recherche de caisse par régime
        criteres = new DimensionCriteresRechercheDto();
        caisseCritere = new DimensionCriteresRechercheCaisseDto(criteres);
        caisseCritere.setIdRegime(1L);
        result = dimensionService.rechercherCaisseParCriteres(caisseCritere);
        assertEquals(Messages.getString("DimensionServiceTest.189"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.190"), 1L, result.get(0).getId());

        // Test de la recherche de caisse par régime
        criteres = new DimensionCriteresRechercheDto();
        caisseCritere = new DimensionCriteresRechercheCaisseDto(criteres);
        caisseCritere.setIdRegime(3L);
        result = dimensionService.rechercherCaisseParCriteres(caisseCritere);
        assertEquals(Messages.getString("DimensionServiceTest.191"), 9, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.192"), 3L, result.get(0).getId());
        assertEquals(Messages.getString("DimensionServiceTest.193"), 4L, result.get(1).getId());
        assertEquals(Messages.getString("DimensionServiceTest.194"), 5L, result.get(2).getId());
        assertEquals(Messages.getString("DimensionServiceTest.195"), 6L, result.get(3).getId());

        // Test de la recherche par visibilité
        criteres = new DimensionCriteresRechercheDto();
        caisseCritere = new DimensionCriteresRechercheCaisseDto(criteres);
        criteres.setVisible(true);
        result = dimensionService.rechercherCaisseParCriteres(caisseCritere);
        assertEquals(Messages.getString("DimensionServiceTest.196"), 13, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.197"), 1L, result.get(0).getId());

        // Test de la recherche sur le departement
        criteres = new DimensionCriteresRechercheDto();
        caisseCritere = new DimensionCriteresRechercheCaisseDto(criteres);
        criteres.setVisible(true);
        caisseCritere.setIdDepartement(1L);
        result = dimensionService.rechercherCaisseParCriteres(caisseCritere);
        assertEquals(Messages.getString("DimensionServiceTest.198"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.199"), 1L, result.get(0).getId());
    }

    /**
     * Test sur la recherche des regimes.
     */
    @Test
    public void testRechercheRegime() {
        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherRegimeParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.200"), messageErreur, be.getMessage());
        }

        final DimensionCriteresRechercheRegimeDto criteres = new DimensionCriteresRechercheRegimeDto();
        final DimensionCriteresRechercheDto dimensionCriteres = new DimensionCriteresRechercheDto();
        criteres.setDimensionCriteres(dimensionCriteres);

        result = dimensionService.rechercherRegimeParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.201"), 5, result.size());

        // Test de la recherche par identifiant.
        dimensionCriteres.setId(1L);
        result = dimensionService.rechercherRegimeParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.202"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.203"), "Caisse régime 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        dimensionCriteres.setId(null);
        dimensionCriteres.setLibelle("Caisse régime 2");
        result = dimensionService.rechercherRegimeParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.206"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.207"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        dimensionCriteres.setId(null);
        dimensionCriteres.setLibelle("Caisse");
        result = dimensionService.rechercherRegimeParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.209"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.210"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        dimensionCriteres.setLibelle(null);
        dimensionCriteres.setVisible(true);
        result = dimensionService.rechercherRegimeParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.211"), 5, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.212"), 1L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.213"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.214"), 2L, result.get(1).getIdentifiant());

        // Test de la recherche par visibilité site web
        dimensionCriteres.setLibelle(null);
        dimensionCriteres.setVisible(null);
        criteres.setVisibleApplicatif(true);
        result = dimensionService.rechercherRegimeParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.215"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.216"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité site web
        dimensionCriteres.setLibelle(null);
        dimensionCriteres.setVisible(null);
        criteres.setVisibleApplicatif(false);
        result = dimensionService.rechercherRegimeParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.217"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.218"), 2L, result.get(0).getIdentifiant());
    }

    /**
     * Test sur la recherche des csp.
     */
    @Test
    public void testRechercheCSP() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherCSPParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.219"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherCSPParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.220"), 4, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherCSPParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.221"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.222"), "Profession 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Profession 2");
        result = dimensionService.rechercherCSPParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.225"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.226"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("Prof");
        result = dimensionService.rechercherCSPParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.228"), 4, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.229"), 9L, result.get(2).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherCSPParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.230"), 4, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.231"), 9L, result.get(2).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.232"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.233"), 3L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des segments.
     */
    @Test
    public void testRechercheSegment() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherSegmentParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.234"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherSegmentParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.235"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherSegmentParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.236"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.237"), "segment1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("segment2");
        result = dimensionService.rechercherSegmentParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.240"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.241"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par par approximation.
        criteres.setId(null);
        criteres.setLibelle("seg");
        result = dimensionService.rechercherSegmentParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.243"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.244"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherSegmentParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.245"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.246"), 1L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.247"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.248"), 3L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des situations familiales.
     */
    @Test
    public void testRechercheSituationFamiliale() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherSitFamParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.249"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherSitFamParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.250"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherSitFamParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.251"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.252"), "En couple", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Célibataire");
        result = dimensionService.rechercherSitFamParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.255"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.256"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("Céli");
        result = dimensionService.rechercherSitFamParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.258"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.259"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherSitFamParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.260"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.261"), 3L, result.get(0)
                .getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.262"), 3L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.263"), 2L, result.get(2).getIdentifiant());
    }

    /**
     * Test sur la recherche des types adresse.
     */
    @Test
    public void testRechercheTypeAdresse() {

        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherTypeAdresseParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.264"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherTypeAdresseParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.265"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherTypeAdresseParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.266"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.267"), "nature 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("nature 2");
        result = dimensionService.rechercherTypeAdresseParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.270"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.271"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("natu");
        result = dimensionService.rechercherTypeAdresseParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.273"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.274"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherTypeAdresseParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.275"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.276"), 1L, result.get(0)
                .getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.277"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.278"), 2L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des départements.
     */
    @Test
    public void testRechercheDepartement() {

        List<IdentifiantLibelleDepartementCodeDto> result = null;

        try {
            result = dimensionService.rechercherDepartementParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.279"), messageErreur, be.getMessage());
        }

        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        final DimensionCritereRechercheDepartementDto departementCriteres = new DimensionCritereRechercheDepartementDto(criteres);

        result = dimensionService.rechercherDepartementParCriteres(departementCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.280"), 4, result.size());

        // Test de la recherche par identifiant.
        criteres.setId(1L);
        result = dimensionService.rechercherDepartementParCriteres(departementCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.281"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.282"), "Vienne", result.get(0).getLibelle());
        assertEquals(LE_CODE_NE_CORRESPOND_PAS, "86", result.get(0).getCodeDepartement());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Vienne");
        result = dimensionService.rechercherDepartementParCriteres(departementCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.286"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.287"), 1L, result.get(0).getId());
        assertEquals(LE_CODE_NE_CORRESPOND_PAS, "86", result.get(0).getCodeDepartement());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("Char");
        result = dimensionService.rechercherDepartementParCriteres(departementCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.290"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.291"), 2L, result.get(0).getId());
        assertEquals(LE_CODE_NE_CORRESPOND_PAS, "16", result.get(0).getCodeDepartement());

        // Test de la recherche par commune.
        criteres.setVisible(null);
        criteres.setId(null);
        criteres.setLibelle(null);
        departementCriteres.setIdCommune(1L);
        result = dimensionService.rechercherDepartementParCriteres(departementCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.293"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.294"), 1L, result.get(0).getId());
        assertEquals(LE_CODE_NE_CORRESPOND_PAS, "86", result.get(0).getCodeDepartement());

        // Test de la recherche par visibilité.
        criteres.setVisible(true);
        criteres.setId(null);
        criteres.setLibelle(null);
        departementCriteres.setIdCommune(null);
        result = dimensionService.rechercherDepartementParCriteres(departementCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.296"), 4, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.297"), 1L, result.get(0).getId());
        assertEquals(LE_CODE_NE_CORRESPOND_PAS, "86", result.get(0).getCodeDepartement());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.299"), 1L, result.get(0).getId());
        assertEquals(Messages.getString("DimensionServiceTest.300"), 2L, result.get(1).getId());
        assertEquals(Messages.getString("DimensionServiceTest.301"), 3L, result.get(2).getId());

        //Test de recherhe par code postale
        criteres.setVisible(null);
        criteres.setId(null);
        criteres.setLibelle(null);
        departementCriteres.setIdCommune(null);
        departementCriteres.setIdCodePostal(15238L);

        result = dimensionService.rechercherDepartementParCriteres(departementCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.302"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.303"), 4L, result.get(0).getId());



    }

    /**
     * Test sur la recherche des réseaux de ventes.
     */
    @Test
    public void testRechercheReseauVente() {
        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherReseauVenteParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.304"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherReseauVenteParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.305"), 2, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherReseauVenteParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.306"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.307"), "réseau 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("réseau 1");
        result = dimensionService.rechercherReseauVenteParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.310"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.311"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("rése");
        result = dimensionService.rechercherReseauVenteParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.313"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.314"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherReseauVenteParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.315"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.316"), 1L, result.get(0)
                .getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.317"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.318"), 2L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des statuts de personne.
     */
    @Test
    public void testRecherchePersonnoStatut() {
        List<IdentifiantLibelleDto> result = dimensionService.rechercherPersonneStatutParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.319"), 2, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherPersonneStatutParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.320"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.321"), "Statut 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Statut 2");
        result = dimensionService.rechercherPersonneStatutParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.324"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.325"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("stat");
        result = dimensionService.rechercherPersonneStatutParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.327"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.328"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherPersonneStatutParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.329"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.330"), 1L, result.get(0)
                .getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.331"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.332"), 2L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des formes juridiques.
     */
    @Test
    public void testRechercheFormeJuridique() {
        List<IdentifiantLibelleDto> result = dimensionService.rechercherFormesJuridiques(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.333"), 2, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherFormesJuridiques(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.334"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.335"), "SA", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("SARL");
        result = dimensionService.rechercherFormesJuridiques(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.338"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.339"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par approximation.
        criteres.setId(null);
        criteres.setLibelle("SA");
        result = dimensionService.rechercherFormesJuridiques(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.341"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.342"), 1L, result.get(1).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherFormesJuridiques(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.343"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.344"), 1L, result.get(1)
                .getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.345"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.346"), 1L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des types de relations.
     */
    @Test
    public void testRechercheTypesRelation() {
        List<IdentifiantLibelleTypeRelationDto> result = dimensionService.rechercherTypesRelations(new DimensionCritereRechercheTypeRelationDto());
        assertEquals(Messages.getString("DimensionServiceTest.347"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCritereRechercheTypeRelationDto criteres = new DimensionCritereRechercheTypeRelationDto();
        criteres.setId(1L);
        result = dimensionService.rechercherTypesRelations(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.348"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.349"), "a pour conjoint", result.get(0).getLibelle());
        assertEquals(Messages.getString("DimensionServiceTest.351"), "est conjoint de", result.get(0).getLibelleInverse());
        assertEquals(Messages.getString("DimensionServiceTest.353"), 1L, result.get(0)
                .getIdentifiant());
        // Test de la recherche par visibilité
        criteres.setId(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherTypesRelations(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.354"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.355"), 1L, result.get(0)
                .getIdentifiant());

        // Rechercher par groupement.
        criteres.setVisible(null);
        criteres.setGroupements(new Long[] {1L});
        result = dimensionService.rechercherTypesRelations(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.356"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.357"), 1L, result.get(0)
                .getIdentifiant());

        result = dimensionService.rechercherTypesRelations(new DimensionCritereRechercheTypeRelationDto());
        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.358"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.359"), 3L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des codesPostaux - communes.
     */
    @Test
    public void testCodesPostauxCommunes() {
        List<IdentifiantLibelleCodePostalCommuneDto> result = dimensionService.rechercherCodesPostauxCommunes(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.360"), 9, result.size());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_CODE, CODE_POSTAL_001, result.get(0).getLibelleCodePostal());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_COMMUNE, COMMUNE1, result.get(0).getLibelleCommune());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_CODE, TRIPLE_ZERO, result.get(1).getLibelleCodePostal());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_COMMUNE, COMMUNE1, result.get(1).getLibelleCommune());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_CODE, CODE_POSTAL_001, result.get(2).getLibelleCodePostal());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_COMMUNE, COMMUNE2, result.get(2).getLibelleCommune());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_CODE, TRIPLE_ZERO, result.get(3).getLibelleCodePostal());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_COMMUNE, COMMUNE2, result.get(3).getLibelleCommune());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherCodesPostauxCommunes(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.361"), 2, result.size());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle(TRIPLE_ZERO);
        result = dimensionService.rechercherCodesPostauxCommunes(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.362"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.363"), 1L, result.get(0).getIdCodePostal());
        assertEquals(Messages.getString("DimensionServiceTest.364"), 1L, result.get(0).getIdCommune());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherCodesPostauxCommunes(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.365"), 9, result.size());
        // Test sur l'ordre.
        assertEquals(IDENTIFIANT_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS, 2L, result.get(0).getIdCodePostal());
        assertEquals(IDENTIFIANT_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS, 1L, result.get(0).getIdCommune());
        assertEquals(IDENTIFIANT_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS, 1L, result.get(1).getIdCodePostal());
        assertEquals(IDENTIFIANT_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS, 1L, result.get(1).getIdCommune());
    }

    /**
     * Test sur la recherche des codesPostaux - communes.
     */
    @Test
    public void testCommunesCodesPostaux() {
        List<IdentifiantLibelleCodePostalCommuneDto> result = dimensionService.rechercherCommunesCodesPostaux(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.366"), 9, result.size());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_CODE, CODE_POSTAL_001, result.get(0).getLibelleCodePostal());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_COMMUNE, COMMUNE1, result.get(0).getLibelleCommune());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_CODE, TRIPLE_ZERO, result.get(1).getLibelleCodePostal());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_COMMUNE, COMMUNE1, result.get(1).getLibelleCommune());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_CODE, CODE_POSTAL_001, result.get(2).getLibelleCodePostal());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_COMMUNE, COMMUNE2, result.get(2).getLibelleCommune());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_CODE, TRIPLE_ZERO, result.get(3).getLibelleCodePostal());
        assertEquals(LIBELLE_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS_AFFICHAGE_COMMUNE, COMMUNE2, result.get(3).getLibelleCommune());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherCommunesCodesPostaux(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.367"), 2, result.size());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle(COMMUNE2);
        result = dimensionService.rechercherCommunesCodesPostaux(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.368"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.369"), 2L, result.get(0).getIdCodePostal());
        assertEquals(Messages.getString("DimensionServiceTest.370"), 2L, result.get(0).getIdCommune());

        // Test de la recherche par visibilité
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherCommunesCodesPostaux(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.371"), 9, result.size());
        // Test sur l'ordre.
        assertEquals(IDENTIFIANT_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS, 2L, result.get(0).getIdCodePostal());
        assertEquals(IDENTIFIANT_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS, 1L, result.get(0).getIdCommune());
        assertEquals(IDENTIFIANT_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS, 1L, result.get(1).getIdCodePostal());
        assertEquals(IDENTIFIANT_RECHERCHE_CODES_POSTAUX_COMMUNES_NE_CORRESPOND_PAS, 1L, result.get(1).getIdCommune());
    }

    /**
     * Test sur la recherche des natures des actions.
     */
    @Test
    public void testRechercherNatureAction() {
        List<IdentifiantLibelleDto> result = dimensionService.rechercherNatureActionsParCriteres(new DimensionCriteresRechercheDto());
        assertNotNull(Messages.getString("DimensionServiceTest.372"), result);
        assertEquals(Messages.getString("DimensionServiceTest.373"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherNatureActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.374"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.375"), "Nature action 1", result.get(0).getLibelle());

        // Test de la recherche par libellé.
        criteres.setId(null);
        criteres.setLibelle("Nature action 1");
        result = dimensionService.rechercherNatureActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.378"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.379"), 1L, result.get(0)
                .getIdentifiant());

        // Test de la recherche par visibilité.
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherNatureActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.380"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.381"), 1L, result.get(2)
                .getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.382"), 1L, result.get(2).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.383"), 3L, result.get(0).getIdentifiant());
    }

    /**
     * Test sur la recherche des types des actions.
     */
    @Test
    public void testRechercherTypeAction() {
        List<IdentifiantLibelleDto> result = dimensionService.rechercherTypesActionsParCriteres(new DimensionCriteresRechercheDto());
        assertNotNull(Messages.getString("DimensionServiceTest.384"), result);
        assertEquals(Messages.getString("DimensionServiceTest.385"), 4, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherTypesActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.386"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.387"), "Type 1", result.get(0).getLibelle());

        // Test de la recherche par libellé.
        criteres.setId(null);
        criteres.setLibelle("Type 1");
        result = dimensionService.rechercherTypesActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.390"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.391"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité.
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherTypesActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.392"), 4, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.393"), 1L, result.get(1).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.394"), 1L, result.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.395"), 3L, result.get(2).getIdentifiant());
    }

    /**
     * Test sur la recherche des natures des résultats des actions.
     */
    @Test
    public void testRechercherNatureResultatAction() {
        List<IdentifiantLibelleDto> result = dimensionService.rechercherNatureResultatActionsParCriteres(new ActionNatureResultatCriteresRechercheDto());
        assertNotNull(Messages.getString("DimensionServiceTest.396"), result);
        assertEquals(Messages.getString("DimensionServiceTest.397"), 2, result.size());

        // Test de la recherche par identifiant.
        final ActionNatureResultatCriteresRechercheDto criteres = new ActionNatureResultatCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherNatureResultatActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.398"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.399"), "Nature résultat 1", result.get(0).getLibelle());

        // Test de la recherche par libellé.
        criteres.setId(null);
        criteres.setLibelle("Nature résultat 2");
        result = dimensionService.rechercherNatureResultatActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.402"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.403"), 2L, result.get(0)
                .getIdentifiant());

        // Test de la recherche par visibilité.
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherNatureResultatActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.404"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.405"), 2L, result.get(0)
                .getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.406"), 1L, result.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.407"), 2L, result.get(0).getIdentifiant());
    }

    /**
     * Test sur la recherche des objets des actions.
     */
    @Test
    public void testRechercherObjetAction() {

        List<IdentifiantLibelleDto> result = null;
        try {
            result = dimensionService.rechercherObetsActionsParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.408"), messageErreur, be.getMessage());
        }

        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        final DimensionCritereRechercheObjetDto objetCriteres = new DimensionCritereRechercheObjetDto(criteres);

        result = dimensionService.rechercherObetsActionsParCriteres(objetCriteres);
        assertNotNull(Messages.getString("DimensionServiceTest.409"), result);
        assertEquals(Messages.getString("DimensionServiceTest.410"), 3, result.size());

        // Test de la recherche par identifiant.
        criteres.setId(1L);
        result = dimensionService.rechercherObetsActionsParCriteres(objetCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.411"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.412"), "Objet 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Objet 3");
        result = dimensionService.rechercherObetsActionsParCriteres(objetCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.415"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.416"), 3L, result.get(0).getIdentifiant());

        // Test de la recherche par type.
        criteres.setVisible(null);
        criteres.setId(null);
        criteres.setLibelle(null);
        objetCriteres.setIdType(1L);
        result = dimensionService.rechercherObetsActionsParCriteres(objetCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.417"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.418"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité.
        criteres.setVisible(true);
        criteres.setId(null);
        criteres.setLibelle(null);
        objetCriteres.setIdType(null);
        result = dimensionService.rechercherObetsActionsParCriteres(objetCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.419"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.420"), 1L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.421"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.422"), 3L, result.get(1).getIdentifiant());

    }

    /**
     * Test sur la recherche des sous objets des actions.
     */
    @Test
    public void testRechercherSousObjetAction() {
        List<IdentifiantLibelleDto> result = null;
        try {
            result = dimensionService.rechercherObetsActionsParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.423"), messageErreur, be.getMessage());
        }

        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        final DimensionCritereRechercheSousObjetDto sousObjetCriteres = new DimensionCritereRechercheSousObjetDto(criteres);

        result = dimensionService.rechercherSousObetsActionsParCriteres(sousObjetCriteres);
        assertNotNull(Messages.getString("DimensionServiceTest.424"), result);
        assertEquals(Messages.getString("DimensionServiceTest.425"), 3, result.size());

        // Test de la recherche par identifiant.
        criteres.setId(1L);
        result = dimensionService.rechercherSousObetsActionsParCriteres(sousObjetCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.426"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.427"), "Sous objet 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Sous objet 3");
        result = dimensionService.rechercherSousObetsActionsParCriteres(sousObjetCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.430"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.431"), 3L, result.get(0).getIdentifiant());

        // Test de la recherche par objet.
        criteres.setVisible(null);
        criteres.setId(null);
        criteres.setLibelle(null);
        sousObjetCriteres.setIdObjet(2L);
        result = dimensionService.rechercherSousObetsActionsParCriteres(sousObjetCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.432"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.433"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité.
        criteres.setVisible(true);
        criteres.setId(null);
        criteres.setLibelle(null);
        sousObjetCriteres.setIdObjet(null);
        result = dimensionService.rechercherSousObetsActionsParCriteres(sousObjetCriteres);
        assertEquals(Messages.getString("DimensionServiceTest.434"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.435"), 3L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.436"), 3L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.437"), 2L, result.get(1).getIdentifiant());

    }

    /**
     * Test sur la recherche des priorités des actions.
     */
    @Test
    public void testRechercherPrioriteAction() {
        List<IdentifiantLibelleDto> result = dimensionService.rechercherPrioriteActionsParCriteres(new DimensionCriteresRechercheDto());
        assertNotNull(Messages.getString("DimensionServiceTest.438"), result);
        assertEquals(Messages.getString("DimensionServiceTest.439"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherPrioriteActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.440"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.441"), "Priorité 1", result.get(0).getLibelle());

        // Test de la recherche par libellé.
        criteres.setId(null);
        criteres.setLibelle("Priorité 2");
        result = dimensionService.rechercherPrioriteActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.444"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.445"), 2L, result.get(0)
                .getIdentifiant());

        // Test de la recherche par visibilité.
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherPrioriteActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.446"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.447"), 2L, result.get(0)
                .getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.448"), 1L, result.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.449"), 2L, result.get(0).getIdentifiant());
    }

    /**
     * Test sur la recherche des status des actions.
     */
    @Test
    public void testRechercherStatutAction() {
        List<IdentifiantLibelleDto> result = dimensionService.rechercherStatutActionsParCriteres(new DimensionCriteresRechercheDto());
        assertNotNull(Messages.getString("DimensionServiceTest.450"), result);
        assertEquals(Messages.getString("DimensionServiceTest.451"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherStatutActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.452"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.453"), "Statut 1", result.get(0).getLibelle());

        // Test de la recherche par libellé.
        criteres.setId(null);
        criteres.setLibelle("Statut 2");
        result = dimensionService.rechercherStatutActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.456"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.457"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité.
        criteres.setLibelle(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherStatutActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.458"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.459"), 1L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.460"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.461"), 2L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherhce des résultats des actions.
     */
    @Test
    public void testRechercherResultatAction() {
        final DimensionCritereRechercheResultatActionDto criteres = new DimensionCritereRechercheResultatActionDto(new DimensionCriteresRechercheDto());
        List<IdentifiantLibelleDto> result = dimensionService.rechercherResultatActionsParCriteres(criteres);
        assertNotNull(Messages.getString("DimensionServiceTest.462"), result);
        assertEquals(Messages.getString("DimensionServiceTest.463"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteresGenerique = new DimensionCriteresRechercheDto();
        criteresGenerique.setId(1L);
        criteres.setDimensionCriteres(criteresGenerique);
        result = dimensionService.rechercherResultatActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.464"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.465"), "Résultat 1", result.get(0).getLibelle());

        // Test de la recherche par libellé.
        criteresGenerique.setId(null);
        criteresGenerique.setLibelle("Résultat 2");
        criteres.setDimensionCriteres(criteresGenerique);
        result = dimensionService.rechercherResultatActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.468"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.469"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité.
        criteresGenerique.setLibelle(null);
        criteresGenerique.setVisible(true);
        criteres.setDimensionCriteres(criteresGenerique);
        result = dimensionService.rechercherResultatActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.470"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.471"), 3L, result.get(0)
                .getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.472"), 3L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.473"), 1L, result.get(2).getIdentifiant());

        // Test de la recherche sans remonter l'opportunite
        criteres.setRecuperationOpportunite(false);
        result = dimensionService.rechercherResultatActionsParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.474"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.475"), 3L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.476"), 2L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche de la ressource par critères.
     */
    @Test
    public void testRechercheRessource() {

        // Test de la recherche avec dto de critère de recherche nul.
        try {
            dimensionService.rechercherRessourceParCriteres(null);
            fail(Messages.getString("DimensionServiceTest.477"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("DimensionServiceTest.478"), messageErreur, e.getMessage());
        }

        final DimensionCriteresRechercheRessourceDto criteres = new DimensionCriteresRechercheRessourceDto();

        List<DimensionRessourceDto> result = null;

        // Test de la recherche sans aucun critère.
        result = dimensionService.rechercherRessourceParCriteres(criteres);
        assertNotNull(Messages.getString("DimensionServiceTest.479"), result);
        assertEquals(Messages.getString("DimensionServiceTest.480"), 4, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.481"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.482"), "Prénom 3", result.get(0).getPrenom());
        assertEquals(Messages.getString("DimensionServiceTest.484"), "Nom 3", result.get(2).getNom());

        // Test de la recherche par nom.
        criteres.setNom("Nom 2");
        result = dimensionService.rechercherRessourceParCriteres(criteres);
        assertNotNull(Messages.getString("DimensionServiceTest.487"), result);
        assertEquals(Messages.getString("DimensionServiceTest.488"), 5, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.489"), 3L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.490"), "Prénom 2", result.get(0).getPrenom());

        // Test de la recherche par identifiant de l'agence.
        criteres.setNom(null);
        criteres.setIdAgence(2L);
        result = dimensionService.rechercherRessourceParCriteres(criteres);
        assertNotNull(Messages.getString("DimensionServiceTest.492"), result);
        assertEquals(Messages.getString("DimensionServiceTest.493"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.494"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.495"), "Prénom 1", result.get(0).getPrenom());
        assertEquals(Messages.getString("DimensionServiceTest.497"), "Nom 3", result.get(0).getNom());

        // Test de la recherche par nom et prénom
        criteres.setIdAgence(null);
        criteres.setNomPrenom("noM 1 prénom 3");
        result = dimensionService.rechercherRessourceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.500"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.501"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par prénom et nom
        criteres.setNomPrenom(null);
        criteres.setPrenomNom("pRénom 1 nOm 3");
        result = dimensionService.rechercherRessourceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.503"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.504"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par liste d'agences
        criteres.setPrenomNom(null);
        final List<Long> listeIdsAgences = new ArrayList<Long>();
        listeIdsAgences.add(1L);
        listeIdsAgences.add(2L);
        criteres.setIdAgences(listeIdsAgences);
        result = dimensionService.rechercherRessourceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.505"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.506"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.507"), 1L, result.get(1).getIdentifiant());

        // Test de la recherche par liste d'états
        criteres.setIdAgences(null);
        final List<Long> listeIdsEtats = new ArrayList<Long>();
        listeIdsEtats.add(2L);
        criteres.setIdEtats(listeIdsEtats);
        result = dimensionService.rechercherRessourceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.508"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.509"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.510"), 3L, result.get(1).getIdentifiant());
        listeIdsEtats.add(1L);
        criteres.setIdEtats(listeIdsEtats);
        result = dimensionService.rechercherRessourceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.511"), 4, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.512"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.513"), 3L, result.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.514"), 1L, result.get(2).getIdentifiant());

        // Test sur le tri
        criteres.setIdEtats(null);
        result = dimensionService.rechercherRessourceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.515"), 1L, result.get(2).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.516"), 3L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche de l'agence par critères.
     */
    @Test
    public void testRechercheAgence() {

        // Test de la recherche avec dto de critères de recherche nul.
        try {
            dimensionService.rechercherAgenceParCriteres(null);
            fail(Messages.getString("DimensionServiceTest.517"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("DimensionServiceTest.518"), messageErreur, e.getMessage());
        }

        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        List<IdentifiantLibelleDto> result = null;
        result = dimensionService.rechercherAgenceParCriteres(criteres);

        // Test de la recherche sans aucun critère.
        assertNotNull(Messages.getString("DimensionServiceTest.519"), result);
        assertEquals(Messages.getString("DimensionServiceTest.520"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.521"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.522"), "Agence 1", result.get(0).getLibelle());

        // Test de la recherche avec le critère libellé de l'agence.
        criteres.setLibelle("Agence 2");
        result = dimensionService.rechercherAgenceParCriteres(criteres);
        assertNotNull(Messages.getString("DimensionServiceTest.525"), result);
        assertEquals(Messages.getString("DimensionServiceTest.526"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.527"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.528"), "Agence 2", result.get(0).getLibelle());

        // Test sur le tri
        criteres.setLibelle(null);
        result = dimensionService.rechercherAgenceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.530"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.531"), 2L, result.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.532"), 3L, result.get(2).getIdentifiant());
    }

    /**
     * Test de la recherche d'agences par identifiant externes.
     */
    @Test
    public void testRechercherAgencesParEids() {
        // Identifiants / identifiants externes des agences recherchées
        final Long idAgence1 = 1L;
        final Long eidAgence1 = 1L;
        final Long idAgence2 = 2L;
        final Long eidAgence2 = 2L;
        final Long idAgence3 = 3L;
        final Long eidAgence3 = 3L;

        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.getEids().add(eidAgence1);
        criteres.getEids().add(eidAgence2);
        criteres.getEids().add(eidAgence3);
        final List<IdentifiantLibelleDto> agencesTrouvees = dimensionService.rechercherAgenceParCriteres(criteres);

        final int nbAgencesAttendues = 3;
        boolean agence1Trouvee = false;
        boolean agence2Trouvee = false;
        boolean agence3Trouvee = false;

        assertEquals(Messages.getString("DimensionServiceTest.533"), nbAgencesAttendues, agencesTrouvees.size());
        for (IdentifiantLibelleDto agence : agencesTrouvees) {
            if (idAgence1.equals(agence.getIdentifiant())) {
                agence1Trouvee = true;
            } else if (idAgence2.equals(agence.getIdentifiant())) {
                agence2Trouvee = true;
            } else if (idAgence3.equals(agence.getIdentifiant())) {
                agence3Trouvee = true;
            }
        }
        assertTrue(Messages.getString("DimensionServiceTest.534"), agence1Trouvee && agence2Trouvee && agence3Trouvee);
    }

    /**
     * Test sur la recherche de l'agence par critères.
     */
    @Test
    public void testRechercherDepartementParIdCommune() {
        final Long idCommune = new Long(3);
        final Long idDepartement = new Long(2);
        final IdentifiantLibelleDepartementCodeDto departement = dimensionService.rechercherDepartementParIdCommune(idCommune);
        assertEquals(Messages.getString("DimensionServiceTest.535"), idDepartement, departement.getId());
    }

    /** Test de la recherche d'une CSP. */
    public void testRechercherCSPParId() {
        final Long id = 1L;
        final String eid = EID_1;
        final String libelle = "Profession 1";
        final IdentifiantEidLibelleDto csp = dimensionService.rechercherCSPParId(id);
        assertEquals(IDENTIFIANT_PAS_BON, id, csp.getIdentifiant());
        assertEquals(EID_PAS_BON, eid, csp.getIdentifiantExterieur());
        assertEquals(LIBELLE_PAS_BON, libelle, csp.getLibelle());
    }

    /** Test de la recherche d'une caisse. */
    public void testRechercherCaisseParId() {
        final Long id = 1L;
        final String eid = EID_1;
        final String libelle = "Profession 1";
        final String code = EID_1;
        final CaisseDto caisse = dimensionService.rechercherCaisseParId(id);
        assertEquals(IDENTIFIANT_PAS_BON, id, caisse.getIdentifiant());
        assertEquals(EID_PAS_BON, eid, caisse.getIdentifiantExterieur());
        assertEquals(LIBELLE_PAS_BON, libelle, caisse.getLibelle());
        assertEquals(Messages.getString("DimensionServiceTest.538"), code, caisse.getCode());
    }

    /** Test de la recherche d'une civilité. */
    public void testRechercherCiviliteParId() {
        final Long id = 1L;
        final String eid = EID_1;
        final String libelle = "Monsieur";
        final IdentifiantEidLibelleDto civilite = dimensionService.rechercherCiviliteParId(id);
        assertEquals(IDENTIFIANT_PAS_BON, id, civilite.getIdentifiant());
        assertEquals(EID_PAS_BON, eid, civilite.getIdentifiantExterieur());
        assertEquals(LIBELLE_PAS_BON, libelle, civilite.getLibelle());
    }

    /** Test de la recherche d'un régime. */
    public void testRechercherRegimeParId() {
        final Long id = 1L;
        final String eid = EID_1;
        final String libelle = "Caisse régime 1";
        final IdentifiantEidLibelleDto regime = dimensionService.rechercherRegimeParId(id);
        assertEquals(IDENTIFIANT_PAS_BON, id, regime.getIdentifiant());
        assertEquals(EID_PAS_BON, eid, regime.getIdentifiantExterieur());
        assertEquals(LIBELLE_PAS_BON, libelle, regime.getLibelle());
    }

    /** Test de la recherche d'une situation familiale. */
    public void testRechercherSitFamParId() {
        final Long id = 1L;
        final String eid = EID_1;
        final String libelle = "En couple";
        final IdentifiantEidLibelleDto sitFam = dimensionService.rechercherSitFamParId(id);
        assertEquals(IDENTIFIANT_PAS_BON, id, sitFam.getIdentifiant());
        assertEquals(EID_PAS_BON, eid, sitFam.getIdentifiantExterieur());
        assertEquals(LIBELLE_PAS_BON, libelle, sitFam.getLibelle());
    }

    /** Test de la recherche d'un type de voie. */
    public void testRechercherTypeVoieParId() {
        final Long id = 1L;
        final String eid = EID_1;
        final String libelle = "type1";
        final IdentifiantEidLibelleDto typeVoie = dimensionService.rechercherTypeVoieParId(id);
        assertEquals(IDENTIFIANT_PAS_BON, id, typeVoie.getIdentifiant());
        assertEquals(EID_PAS_BON, eid, typeVoie.getIdentifiantExterieur());
        assertEquals(LIBELLE_PAS_BON, libelle, typeVoie.getLibelle());
    }

    /** Test de la recherche d'un statut. */
    public void testRechercherStatutParId() {
        final Long id = 1L;
        final String eid = EID_1;
        final String libelle = "Statut 1";
        final IdentifiantEidLibelleDto statut = dimensionService.rechercherStatutParId(id);
        assertEquals(IDENTIFIANT_PAS_BON, id, statut.getIdentifiant());
        assertEquals(EID_PAS_BON, eid, statut.getIdentifiantExterieur());
        assertEquals(LIBELLE_PAS_BON, libelle, statut.getLibelle());
    }

    /** Test de la recherche d'un pays. */
    public void testRechercherPaysParId() {
        final Long id = 1L;
        final String eid = EID_1;
        final String libelle = "France";
        final IdentifiantEidLibelleDto pays = dimensionService.rechercherPaysParId(id);
        assertEquals(IDENTIFIANT_PAS_BON, id, pays.getIdentifiant());
        assertEquals(EID_PAS_BON, eid, pays.getIdentifiantExterieur());
        assertEquals(LIBELLE_PAS_BON, libelle, pays.getLibelle());
    }

    /**
     * Test sur la recherche des fonctions de la ressource.
     */
    @Test
    public void testRechercheRessourceFonction() {
        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherRessourceFonctionParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.545"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherRessourceFonctionParCriteres(new DimensionCritereRechercheRessourceFonctionDto());
        assertEquals(Messages.getString("DimensionServiceTest.546"), 2, result.size());

        // Test de la recherche par identifiant.
        final DimensionCritereRechercheRessourceFonctionDto criteres = new DimensionCritereRechercheRessourceFonctionDto();
        criteres.setId(1L);
        result = dimensionService.rechercherRessourceFonctionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.547"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.548"), "Fonction 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Fonction 2");
        result = dimensionService.rechercherRessourceFonctionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.551"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.552"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche approximative.
        criteres.setId(null);
        criteres.setLibelle("Fonction");
        result = dimensionService.rechercherRessourceFonctionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.554"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.555"), 1L, result.get(0).getIdentifiant());

        // Test sur le maxresults
        criteres.setLibelle(null);
        criteres.setMaxResults(1);
        result = dimensionService.rechercherRessourceFonctionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.556"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.557"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setMaxResults(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherRessourceFonctionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.558"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.559"), 1L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.560"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.561"), 2L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des services de la ressource.
     */
    @Test
    public void testRechercheRessourceService() {
        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherRessourceServiceParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.562"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherRessourceServiceParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.563"), 2, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherRessourceServiceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.564"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.565"), "Service 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle("Service 2");
        result = dimensionService.rechercherRessourceServiceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.568"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.569"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche approximative.
        criteres.setId(null);
        criteres.setLibelle("Service");
        result = dimensionService.rechercherRessourceServiceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.571"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.572"), 1L, result.get(0).getIdentifiant());

        // Test sur le maxresults
        criteres.setLibelle(null);
        criteres.setMaxResults(1);
        result = dimensionService.rechercherRessourceServiceParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.573"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.574"), 1L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        final DimensionCritereRechercheRessourceFonctionDto criteresFonctions = new DimensionCritereRechercheRessourceFonctionDto();
        criteresFonctions.setMaxResults(null);
        criteresFonctions.setVisible(true);
        result = dimensionService.rechercherRessourceFonctionParCriteres(criteresFonctions);
        assertEquals(Messages.getString("DimensionServiceTest.575"), 2, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.576"), 1L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.577"), 1L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.578"), 2L, result.get(1).getIdentifiant());
    }

    /**
     * Test sur la recherche des etats de la ressource.
     */
    @Test
    public void testRechercheRessourceEtat() {
        List<IdentifiantLibelleDto> result = null;

        try {
            result = dimensionService.rechercherRessourceEtatParCriteres(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("DimensionServiceTest.579"), messageErreur, be.getMessage());
        }

        result = dimensionService.rechercherRessourceEtatParCriteres(new DimensionCriteresRechercheDto());
        assertEquals(Messages.getString("DimensionServiceTest.580"), 3, result.size());

        // Test de la recherche par identifiant.
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        result = dimensionService.rechercherRessourceEtatParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.581"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.582"), "libelle 1", result.get(0).getLibelle());

        // Test de la recherche par libelle.
        criteres.setId(null);
        criteres.setLibelle(Messages.getString("DimensionServiceTest.584"));
        result = dimensionService.rechercherRessourceEtatParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.585"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.586"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche approximative.
        criteres.setId(null);
        criteres.setLibelle("libelle");
        result = dimensionService.rechercherRessourceEtatParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.588"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.589"), 2L, result.get(0).getIdentifiant());

        // Test sur le maxresults
        criteres.setLibelle(null);
        criteres.setMaxResults(1);
        result = dimensionService.rechercherRessourceEtatParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.590"), 1, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.591"), 2L, result.get(0).getIdentifiant());

        // Test de la recherche par visibilité
        criteres.setMaxResults(null);
        criteres.setVisible(true);
        result = dimensionService.rechercherRessourceEtatParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.592"), 3, result.size());
        assertEquals(Messages.getString("DimensionServiceTest.593"), 2L, result.get(0).getIdentifiant());

        // Test sur l'ordre.
        assertEquals(Messages.getString("DimensionServiceTest.594"), 1L, result.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.595"), 2L, result.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.596"), 3L, result.get(2).getIdentifiant());
    }

    /**
     * Test sur la recherche d'une agence par identifiant de la ressource.
     */
    @Test
    public void testRechercherAgenceParIdRessource() {
        final Long idRessource = 2L;

        final IdentifiantLibelleDto agenceDto = dimensionService.rechercherAgenceParIdRessource(idRessource);
        assertNotNull(Messages.getString("DimensionServiceTest.597"), agenceDto);
        assertEquals(Messages.getString("DimensionServiceTest.598"), 1L, agenceDto.getIdentifiant());
    }

    /** Test de la recherche d'une commune. */
    @Test
    public void testRechercherCommuneParId() {
        final Long id = 1L;
        final String eid = EID_1;
        final String libelle = COMMUNE1;
        final IdentifiantEidLibelleDto commune = dimensionService.rechercherCommuneParId(id);
        assertEquals(IDENTIFIANT_PAS_BON, id, commune.getIdentifiant());
        assertEquals(EID_PAS_BON, eid, commune.getIdentifiantExterieur());
        assertEquals(LIBELLE_PAS_BON, libelle, commune.getLibelle());
    }

    /** Test de la recherche d'un code postal. */
    @Test
    public void testRechercherCodePostalParId() {
        final Long id = 1L;
        final String eid = EID_1;
        final String libelle = "000";
        final IdentifiantEidLibelleDto codePostal = dimensionService.rechercherCodePostalParId(id);
        assertEquals(IDENTIFIANT_PAS_BON, id, codePostal.getIdentifiant());
        assertEquals(EID_PAS_BON, eid, codePostal.getIdentifiantExterieur());
        assertEquals(LIBELLE_PAS_BON, libelle, codePostal.getLibelle());
    }

    /** Test de la recherche d'un code postal - commune. */
    @Test
    public void testRechercherCodePostalCommuneParId() {
    	final Long idCodePostalCommune = 3L;
    	final String libelleAcheminement = "Commune2 ach 1";
    	final Long idCodePostal = 1L;
    	final String libelleCodePostal = "000";
    	final Long idCommune = 3L;
    	final String libelleCommune = "Commune2";
    	final CodePostalCommuneDto codePostalCommune = dimensionService.rechercherCodePostalCommuneParId(idCodePostalCommune);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune, codePostalCommune.getCommune().getLibelle());
    }

    /** Test de la recherche de codes postaux - communes par critères. */
    @Test
    public void testRechercherCodesPostauxCommunesParCriteres() {
    	final Long idCodePostalCommune1 = 1L;
    	final Long idCodePostalCommune2 = 2L;
    	final Long idCodePostalCommune3 = 3L;
    	final Long idCodePostalCommune4 = 4L;
    	final Long idCodePostalCommune5 = 5L;
    	final Long idCodePostalCommune6 = 6L;
    	final Long idCodePostalCommune7 = 7L;
    	final String libelleAcheminement1 = "Commune1 ach";
    	final String libelleAcheminement2 = "Commune2 ach";
    	final String libelleAcheminement3 = "Commune2 ach 1";
    	final String libelleAcheminement4 = "Commune2 ach";
    	final String libelleAcheminement5 = "LA ROCHELLE";
    	final String libelleAcheminement6 = "LA ROCHELLE";
    	final String libelleAcheminement7 = "LA ROCHELLE";
    	final Long idCodePostal1 = 1L;
    	final Long idCodePostal2 = 2L;
    	final Long idCodePostal3 = 3L;
    	final String libelleCodePostal1 = TRIPLE_ZERO;
    	final String libelleCodePostal2 = CODE_POSTAL_001;
    	final String libelleCodePostal3 = "17000";
    	final Long idCommune1 = 1L;
    	final Long idCommune2 = 2L;
    	final Long idCommune3 = 3L;
    	final Long idCommune4 = 4L;
    	final Long idCommune5 = 5L;
    	final Long idCommune6 = 6L;
    	final String libelleCommune1 = COMMUNE1;
    	final String libelleCommune2 = COMMUNE2;
    	final String libelleCommune3 = COMMUNE2;
    	final String libelleCommune4 = "LA ROCHELLE";
    	final String libelleCommune5 = "LA PALLICE";
    	final String libelleCommune6 = "VILLENEUVE LES SALINES";

    	// Recherche par identifiant
    	final DimensionCritereRechercheCodePostalCommuneDto criteres = new DimensionCritereRechercheCodePostalCommuneDto();
    	criteres.setIdCodePostalCommune(idCodePostalCommune3);
    	List<CodePostalCommuneDto> resultats = dimensionService.rechercherCodesPostauxCommunesParCriteres(criteres);
    	assertEquals(NOMBRE_RESULTATS_PAS_BON, 1, resultats.size());
    	CodePostalCommuneDto codePostalCommune = resultats.get(0);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune3, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement3, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal1, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal1, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune3, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune3, codePostalCommune.getCommune().getLibelle());
    	criteres.setIdCodePostalCommune(null);

    	// Recherche par code postal
    	criteres.setLibelleCodePostal("17");
    	resultats = dimensionService.rechercherCodesPostauxCommunesParCriteres(criteres);
    	assertEquals(NOMBRE_RESULTATS_PAS_BON, 3, resultats.size());
    	codePostalCommune = resultats.get(0);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune6, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement6, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal3, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal3, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune5, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune5, codePostalCommune.getCommune().getLibelle());
    	codePostalCommune = resultats.get(1);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune5, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement5, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal3, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal3, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune4, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune4, codePostalCommune.getCommune().getLibelle());
    	codePostalCommune = resultats.get(2);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune7, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement7, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal3, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal3, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune6, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune6, codePostalCommune.getCommune().getLibelle());
    	criteres.setLibelleCodePostal(null);

    	// Recherche par libellé de commune
    	criteres.setLibelleCommune("La");
    	resultats = dimensionService.rechercherCodesPostauxCommunesParCriteres(criteres);
    	assertEquals(NOMBRE_RESULTATS_PAS_BON, 2, resultats.size());
    	codePostalCommune = resultats.get(0);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune6, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement6, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal3, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal3, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune5, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune5, codePostalCommune.getCommune().getLibelle());
    	codePostalCommune = resultats.get(1);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune5, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement5, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal3, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal3, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune4, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune4, codePostalCommune.getCommune().getLibelle());
    	criteres.setLibelleCommune(null);

    	// Recherche sur le libellé d'acheminement
    	criteres.setLibelleAcheminement("Commune2 ach");
    	resultats = dimensionService.rechercherCodesPostauxCommunesParCriteres(criteres);
    	assertEquals(NOMBRE_RESULTATS_PAS_BON, 3, resultats.size());
    	codePostalCommune = resultats.get(0);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune4, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement4, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal2, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal2, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune1, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune1, codePostalCommune.getCommune().getLibelle());
    	codePostalCommune = resultats.get(1);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune2, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement2, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal2, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal2, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune2, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune2, codePostalCommune.getCommune().getLibelle());
    	codePostalCommune = resultats.get(2);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune3, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement3, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal1, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal1, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune3, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune3, codePostalCommune.getCommune().getLibelle());
    	criteres.setLibelleAcheminement(null);

    	// Recherche sur l'identifiant du code postal
    	criteres.setIdCodePostal(idCodePostal3);
    	resultats = dimensionService.rechercherCodesPostauxCommunesParCriteres(criteres);
    	assertEquals(NOMBRE_RESULTATS_PAS_BON, 3, resultats.size());
    	codePostalCommune = resultats.get(0);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune6, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement6, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal3, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal3, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune5, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune5, codePostalCommune.getCommune().getLibelle());
    	codePostalCommune = resultats.get(1);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune5, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement5, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal3, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal3, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune4, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune4, codePostalCommune.getCommune().getLibelle());
    	codePostalCommune = resultats.get(2);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune7, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement7, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal3, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal3, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune6, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune6, codePostalCommune.getCommune().getLibelle());
    	criteres.setIdCodePostal(null);

    	// Recherche sur l'identifiant de la commune
    	criteres.setIdCommune(idCommune1);
    	resultats = dimensionService.rechercherCodesPostauxCommunesParCriteres(criteres);
    	assertEquals(NOMBRE_RESULTATS_PAS_BON, 2, resultats.size());
    	codePostalCommune = resultats.get(0);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune4, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement4, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal2, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal2, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune1, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune1, codePostalCommune.getCommune().getLibelle());
    	codePostalCommune = resultats.get(1);
    	assertEquals(IDENTIFIANT_CODE_POSTAL_COMMUNE_PAS_BON, idCodePostalCommune1, codePostalCommune.getIdentifiant());
    	assertEquals(LIBELLE_ACHEMINEMENT_PAS_BON, libelleAcheminement1, codePostalCommune.getLibelleAcheminement());
    	assertEquals(IDENTIFIANT_CODE_POSTAL_PAS_BON, idCodePostal1, codePostalCommune.getCodePostal().getIdentifiant());
    	assertEquals(LIBELLE_CODE_POSTAL_PAS_BON, libelleCodePostal1, codePostalCommune.getCodePostal().getLibelle());
    	assertEquals(IDENTIFIANT_COMMUNE_PAS_BON, idCommune1, codePostalCommune.getCommune().getIdentifiant());
    	assertEquals(LIBELLE_COMMUNE_PAS_BON, libelleCommune1, codePostalCommune.getCommune().getLibelle());
    }

    /** Test de la recherche d'un modele d'email. */
    @Test
    public void testRechercherListeModelesEmails() {
    	final Long idModele3 = 3L;

    	// Recherche par identifiant
    	final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
    	List<IdentifiantLibelleDto> resultats = dimensionService.rechercherListeModelesEmails(criteres);
    	assertEquals(NOMBRE_RESULTATS_PAS_BON, 3, resultats.size());
    	assertEquals(Messages.getString("DimensionServiceTest.617"), 3L, resultats.get(1).getIdentifiant());
    	criteres.setId(idModele3);
    	resultats = dimensionService.rechercherListeModelesEmails(criteres);
    	assertEquals(NOMBRE_RESULTATS_PAS_BON, 1, resultats.size());
    	assertEquals(Messages.getString("DimensionServiceTest.618"), "Modele 3", resultats.get(0).getLibelle());
    }

    /** Test de la recherche d'un modele d'email. */
    @Test
    public void testRechercherModelesEmails() {
    	final Long idModele3 = 3L;

    	// Recherche par identifiant
    	final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
    	List<ModeleEmailDto> resultats = dimensionService.rechercherModelesEmails(criteres);
    	assertEquals(NOMBRE_RESULTATS_PAS_BON, 3, resultats.size());
    	assertEquals(Messages.getString("DimensionServiceTest.620"), 3L, resultats.get(1).getIdentifiant());
    	criteres.setId(idModele3);
    	resultats = dimensionService.rechercherModelesEmails(criteres);
    	assertEquals(NOMBRE_RESULTATS_PAS_BON, 1, resultats.size());
    	assertEquals(Messages.getString("DimensionServiceTest.621"), "Objet3", resultats.get(0).getObjet());
    }

    /** Test du service rechercherDureeActionParCriteres. */
    @Test
    public void testRechercherDureeActionParCriteres() {
        // Aucun critère de recherche
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setVisible(null);
        List<IdentifiantLibelleDto> listeDurees = dimensionService.rechercherDureeActionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.623"), 5, listeDurees.size());
        assertEquals(Messages.getString("DimensionServiceTest.624"), 1L, listeDurees.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.625"), "08:00", listeDurees.get(0).getLibelle());
        assertEquals(Messages.getString("DimensionServiceTest.627"), 2L, listeDurees.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.628"), "09:00", listeDurees.get(1).getLibelle());
        assertEquals(Messages.getString("DimensionServiceTest.630"), 3L, listeDurees.get(2).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.631"), "10:00", listeDurees.get(2).getLibelle());
        assertEquals(Messages.getString("DimensionServiceTest.633"), 4L, listeDurees.get(3).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.634"), "11:00", listeDurees.get(3).getLibelle());
        assertEquals(Messages.getString("DimensionServiceTest.636"), 5L, listeDurees.get(4).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.637"), "12:00", listeDurees.get(4).getLibelle());

        // Critère sur les durées visibles
        criteres.setVisible(true);
        listeDurees = dimensionService.rechercherDureeActionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.639"), 4, listeDurees.size());
        assertEquals(Messages.getString("DimensionServiceTest.640"), 1L, listeDurees.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.641"), "08:00", listeDurees.get(0).getLibelle());
        assertEquals(Messages.getString("DimensionServiceTest.643"), 2L, listeDurees.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.644"), "09:00", listeDurees.get(1).getLibelle());
        assertEquals(Messages.getString("DimensionServiceTest.646"), 3L, listeDurees.get(2).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.647"), "10:00", listeDurees.get(2).getLibelle());
        assertEquals(Messages.getString("DimensionServiceTest.649"), 4L, listeDurees.get(3).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.650"), "11:00", listeDurees.get(3).getLibelle());

        // Critère sur le libellé
        criteres.setLibelle("0");
        listeDurees = dimensionService.rechercherDureeActionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.653"), 2, listeDurees.size());
        assertEquals(Messages.getString("DimensionServiceTest.654"), 1L, listeDurees.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.655"), "08:00", listeDurees.get(0).getLibelle());
        assertEquals(Messages.getString("DimensionServiceTest.657"), 2L, listeDurees.get(1).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.658"), "09:00", listeDurees.get(1).getLibelle());

        // Critère sur l'identifiant
        criteres.setId(2L);
        listeDurees = dimensionService.rechercherDureeActionParCriteres(criteres);
        assertEquals(Messages.getString("DimensionServiceTest.660"), 1, listeDurees.size());
        assertEquals(Messages.getString("DimensionServiceTest.661"), 2L, listeDurees.get(0).getIdentifiant());
        assertEquals(Messages.getString("DimensionServiceTest.662"), "09:00", listeDurees.get(0).getLibelle()); //$NON-NLS-2$
    }
}
