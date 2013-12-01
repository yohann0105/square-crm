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
package com.square.adherent.noyau.service.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.adherent.noyau.dto.adherent.contrat.ContratCollectifCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratPersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratSimpleDto;
import com.square.adherent.noyau.dto.adherent.contrat.CoordonneesBancairesDto;
import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieSimpleDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosContratsBeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosContratsDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosContratsPersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.ListeContratsDto;
import com.square.adherent.noyau.dto.adherent.contrat.PopulationCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.ProduitCollectifAdherentCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.ProduitCollectifAdherentDto;
import com.square.adherent.noyau.dto.adherent.contrat.RatioPrestationCotisationDto;
import com.square.adherent.noyau.dto.adherent.contrat.TypePayeurCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.TypePayeurDto;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.ContratService;

/**
 * Tests unitaires sur les contrats.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ContratServiceTest extends DbunitBaseTestCase {

    private static final String TYPE_PAYEUR_PAS_BON = Messages.getString("ContratServiceTest.0");

    private static final String PRODUIT_PAS_BON = Messages.getString("ContratServiceTest.1");

    private static final String POPULATION_PAS_BONNE = Messages.getString("ContratServiceTest.2");

    private static final String NB_RESULTATS_PAS_BON = Messages.getString("ContratServiceTest.3");

    private static final String ACTIFS = Messages.getString("ContratServiceTest.4");

	private static final String NUMERO_CONTRAT = "01/SR/01";

	private static final String NUMERO_CONTRAT_2 = "10/SR/10";

    private static final String SMATIS_EXCELLENCE_C = Messages.getString("ContratServiceTest.5");

	private static final int TREIZE = 13;

	private static final String BACKSLASH = "/";

	private static final int YEAR_2011 = 2011;

	private static final int YEAR_2007 = 2007;

    /** Service pour les contrats. */
    private ContratService contratService;

    private AdherentMappingService adherentMappingService;

    /** Formateur de date. */
    private SimpleDateFormat sdf;

    /** Méthode appellée avant chaque test unitaire. */
    @Before
    public void setUp() {
        contratService = (ContratService) getBeanSpring("contratService");
        adherentMappingService = (AdherentMappingService) getBeanSpring("adherentMappingService");
        sdf = new SimpleDateFormat(Messages.getString("ContratServiceTest.9"));
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"adherentMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "dataset-contrat.xml";
    }

    /** Test du service getContrat. */
    @Test
    public void testGetContrat() {
        // Récupération d'un contrat inexistant
        final Long uidContratInexistant = 100L;
        try {
            contratService.getContrat(uidContratInexistant);
            fail(Messages.getString("ContratServiceTest.12"));
        } catch (BusinessException e) {
            assertEquals(Messages.getString("ContratServiceTest.13"), Messages.getString("ContratServiceTest.394"), e.getMessage());
        }

        final Long idRoleGarantieAssure = adherentMappingService.getIdRoleGarantieAssure();
        final Long idRoleGarantieConjoint = adherentMappingService.getIdRoleGarantieConjoint();

        // Récupération d'un contrat existant
        final Long uidContratExistant = 1L;
        final ContratDto contrat = contratService.getContrat(uidContratExistant);
        assertEquals(Messages.getString("ContratServiceTest.14"), "Carole Martellucci", contrat.getApporteur());
        assertEquals(Messages.getString("ContratServiceTest.16"), "L32", contrat.getCodeApporteur());
        final String dateEffet = "01/01/2010";
        assertEquals(Messages.getString("ContratServiceTest.19"), dateEffet, sdf.format(contrat.getDateEffet().getTime()));
        assertNull(Messages.getString("ContratServiceTest.20"), contrat.getDateResiliation());
        assertEquals(Messages.getString("ContratServiceTest.21"), "Mensuelle", contrat.getInfosPaiementCotisation()
                .getFrequencePaiement().getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.23"), 1, contrat.getInfosPaiementCotisation().getJourPaiement());
        assertEquals(Messages.getString("ContratServiceTest.24"), "Virement", contrat.getInfosPaiementPrestation()
                .getMoyenPaiement().getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.26"), 2, contrat.getListeGaranties().size());
        boolean isGarantie1teste = false;
        boolean isGarantie2teste = false;
        final Integer idProduitExcellence = 1860220;
        final Integer idProduitAHC = 1669188;
        for (GarantieDto garantie : contrat.getListeGaranties()) {
            if (garantie.getId().equals(3L)) {
                assertEquals(Messages.getString("ContratServiceTest.27"), "456 789", garantie.getCodeGeneration());
                assertEquals(Messages.getString("ContratServiceTest.29"), "123 456", garantie.getCodeTarif());
                assertEquals(Messages.getString("ContratServiceTest.31"), idProduitExcellence, garantie.getIdProduit());
                assertEquals(Messages.getString("ContratServiceTest.32"), SMATIS_EXCELLENCE_C, garantie.getLibelle());
                assertEquals(Messages.getString("ContratServiceTest.33"), "SMATIS EXCELLENCE C_CFTA41 O1", garantie
                        .getLibelleGarantieGestion());
                assertEquals(Messages.getString("ContratServiceTest.35"), "SMC SANTE", garantie.getLibelleProduitGestion());
                assertNull(Messages.getString("ContratServiceTest.37"), garantie.getMontantSouscrit());
                assertNotNull(Messages.getString("ContratServiceTest.38"), garantie.getLoiMadelin());
                assertTrue(Messages.getString("ContratServiceTest.39"), garantie.getLoiMadelin());
                isGarantie1teste = true;
            } else if (garantie.getId().equals(2L)) {
                assertEquals(Messages.getString("ContratServiceTest.40"), "456 789", garantie.getCodeGeneration());
                assertEquals(Messages.getString("ContratServiceTest.42"), "123 456", garantie.getCodeTarif());
                assertEquals(Messages.getString("ContratServiceTest.44"), idProduitAHC, garantie.getIdProduit());
                assertEquals(Messages.getString("ContratServiceTest.45"), "ALLOCATION HOSPITALIERE COMPLEMENTAIRE", garantie.getLibelle());
                assertEquals(Messages.getString("ContratServiceTest.47"), "ALLOCATION HOSPITALIERE COMPLEMENTAIRE", garantie
                        .getLibelleGarantieGestion());
                assertEquals(Messages.getString("ContratServiceTest.49"), "SMC SANTE", garantie.getLibelleProduitGestion());
                final Double montantSouscrit = 500.00;
                assertEquals(Messages.getString("ContratServiceTest.51"), montantSouscrit, garantie.getMontantSouscrit());
                assertNotNull(Messages.getString("ContratServiceTest.52"), garantie.getLoiMadelin());
                assertFalse(Messages.getString("ContratServiceTest.53"), garantie.getLoiMadelin());
                isGarantie2teste = true;
            } else {
                fail(Messages.getString("ContratServiceTest.54"));
            }
        }
        assertTrue(Messages.getString("ContratServiceTest.55"), isGarantie1teste && isGarantie2teste);
        assertEquals(Messages.getString("ContratServiceTest.56"), 1, contrat.getListeAjustements().size());
        assertEquals(Messages.getString("ContratServiceTest.57"), "SR/01", contrat.getListeAjustements().get(0).getReference());
        assertEquals(Messages.getString("ContratServiceTest.59"), "Réduction tarifaire 20%", contrat.getListeAjustements().get(0).getNature());
        assertEquals(Messages.getString("ContratServiceTest.61"), 0.0, contrat.getListeAjustements().get(0).getMontant());
        assertEquals(Messages.getString("ContratServiceTest.62"), NUMERO_CONTRAT, contrat.getNumeroContrat());
        assertEquals(Messages.getString("ContratServiceTest.64"), 3, contrat.getRecapitulatifGarantiesContrat().getListeBeneficiaires().size());
        final GarantieBeneficiaireDto assureDto = contrat.getRecapitulatifGarantiesContrat().getListeBeneficiaires().get(0);
        assertEquals(Messages.getString("ContratServiceTest.65"), "MARTIN", assureDto.getNom());
        assertEquals(Messages.getString("ContratServiceTest.67"), "Pierre", assureDto.getPrenom());
        assertEquals(Messages.getString("ContratServiceTest.69"), idRoleGarantieAssure, assureDto.getRole().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.70"), "Assuré", assureDto.getRole().getLibelle());
        final GarantieBeneficiaireDto conjointDto = contrat.getRecapitulatifGarantiesContrat().getListeBeneficiaires().get(1);
        assertEquals(Messages.getString("ContratServiceTest.72"), "MARTIN", conjointDto.getNom());
        assertEquals(Messages.getString("ContratServiceTest.74"), "Claire", conjointDto.getPrenom());
        assertEquals(Messages.getString("ContratServiceTest.76"), idRoleGarantieConjoint, conjointDto.getRole().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.77"), "Conjoint", conjointDto.getRole().getLibelle());
        final GarantieBeneficiaireDto enfantDto = contrat.getRecapitulatifGarantiesContrat().getListeBeneficiaires().get(2);
        assertEquals(Messages.getString("ContratServiceTest.79"), "MARTIN", enfantDto.getNom());
        assertEquals(Messages.getString("ContratServiceTest.81"), "Pascal", enfantDto.getPrenom());
        assertEquals(Messages.getString("ContratServiceTest.83"), "Enfant", enfantDto.getRole().getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.85"), 2, contrat.getRecapitulatifGarantiesContrat().getListeGaranties()
                .size());
        final Long idGammeExcellence = 16L;
        final String dateAdhesion = "01/01/2010";
        final GarantieSimpleDto garantie1Dto = contrat.getRecapitulatifGarantiesContrat().getListeGaranties().get(0);
        assertEquals(Messages.getString("ContratServiceTest.87"), idGammeExcellence, garantie1Dto.getIdGamme());
        assertEquals(Messages.getString("ContratServiceTest.88"), idProduitExcellence, garantie1Dto.getIdProduit());
        assertEquals(Messages.getString("ContratServiceTest.89"), garantie1Dto.getLibelle(), SMATIS_EXCELLENCE_C);
        assertEquals(Messages.getString("ContratServiceTest.90"), garantie1Dto.getSegment().getIdentifiant(), 1L);
        assertEquals(Messages.getString("ContratServiceTest.91"), 3, garantie1Dto.getListeInfosGarantiesBeneficiaires().size());
        assertEquals(Messages.getString("ContratServiceTest.92"), 2L, garantie1Dto.getListeInfosGarantiesBeneficiaires().get(0)
                .getIdBeneficiaire());
        assertEquals(Messages.getString("ContratServiceTest.93"), dateAdhesion, sdf.format(garantie1Dto
                .getListeInfosGarantiesBeneficiaires().get(0).getDateAdhesion().getTime()));
        assertEquals(Messages.getString("ContratServiceTest.94"), 3L, garantie1Dto.getListeInfosGarantiesBeneficiaires().get(1)
                .getIdBeneficiaire());
        assertEquals(Messages.getString("ContratServiceTest.95"), dateAdhesion, sdf.format(garantie1Dto
                .getListeInfosGarantiesBeneficiaires().get(1).getDateAdhesion().getTime()));
        assertEquals(Messages.getString("ContratServiceTest.96"), 1L, garantie1Dto.getListeInfosGarantiesBeneficiaires().get(2)
                .getIdBeneficiaire());
        assertEquals(Messages.getString("ContratServiceTest.97"), dateAdhesion, sdf.format(garantie1Dto
                .getListeInfosGarantiesBeneficiaires().get(2).getDateAdhesion().getTime()));
        final GarantieSimpleDto garantie2Dto = contrat.getRecapitulatifGarantiesContrat().getListeGaranties().get(1);
        assertEquals(Messages.getString("ContratServiceTest.98"), 10L, garantie2Dto.getIdGamme());
        assertEquals(Messages.getString("ContratServiceTest.99"), idProduitAHC, garantie2Dto.getIdProduit());
        assertEquals(Messages.getString("ContratServiceTest.100"), "ALLOCATION HOSPITALIERE COMPLEMENTAIRE", garantie2Dto.getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.102"), garantie2Dto.getSegment().getIdentifiant(), 2L);
        assertEquals(Messages.getString("ContratServiceTest.103"), 1, garantie2Dto.getListeInfosGarantiesBeneficiaires().size());
        assertEquals(Messages.getString("ContratServiceTest.104"), 1L, garantie2Dto.getListeInfosGarantiesBeneficiaires().get(0)
                .getIdBeneficiaire());
        assertEquals(Messages.getString("ContratServiceTest.105"), dateAdhesion, sdf.format(garantie2Dto
                .getListeInfosGarantiesBeneficiaires().get(0).getDateAdhesion().getTime()));
        assertEquals(Messages.getString("ContratServiceTest.106"), 1L, contrat.getSegment().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.107"), "Individuel", contrat.getSegment().getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.109"), 1L, contrat.getSouscripteur().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.110"), "Pierre Martin", contrat.getSouscripteur().getLibelle());
        final Long idStatutContratEnCours = 4714269L;
        assertEquals(Messages.getString("ContratServiceTest.112"), idStatutContratEnCours, contrat.getStatut().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.113"), "En cours", contrat.getStatut().getLibelle());
        final Long idTypePayeurSouscripteur = 4714128L;
        assertEquals(Messages.getString("ContratServiceTest.115"), idTypePayeurSouscripteur, contrat.getTypePayeur().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.116"), "Souscripteur", contrat.getTypePayeur().getLibelle());
    }

    /** Test du service getListeContrats. */
    @Test
    public void testGetListeContrats() {
        // Récupération des contrats d'une personne sans contrat
        final Long uidPersonneSansContrat = 100L;
        ListeContratsDto listeContratsDto = contratService.getListeContrats(uidPersonneSansContrat);
        assertNull(Messages.getString("ContratServiceTest.118"), listeContratsDto.getInfosContrats());
        assertEquals(Messages.getString("ContratServiceTest.119"), 0, listeContratsDto.getListeContrats().size());

        // Récupération des contrats d'une personne avec des personnes
        final Long uidPersonne = 1L;
        listeContratsDto = contratService.getListeContrats(uidPersonne);
        final InfosContratsDto infosContrat = listeContratsDto.getInfosContrats();
        final String dateMutualisation = "01/01/2010";
        final String dateRadiation = "31/12/2009";
        final Long idStatutContratEnCours = 4714269L;
        final Long idStatutContratSuspendu = 4714264L;
        assertEquals(Messages.getString("ContratServiceTest.122"), dateMutualisation, sdf.format(infosContrat.getDatePremiereMutualisation().getTime()));
        assertEquals(Messages.getString("ContratServiceTest.123"), dateRadiation, sdf.format(infosContrat.getDateDerniereRadiation().getTime()));
        assertEquals(Messages.getString("ContratServiceTest.124"), "Souscripteur", infosContrat.getGestionDuContrat());
        assertEquals(Messages.getString("ContratServiceTest.126"), "Gestionnaire1", infosContrat.getGestionnaire());
        assertEquals(Messages.getString("ContratServiceTest.128"), "Demande annulée", infosContrat.getMotifDerniereRadiation());
        assertEquals(Messages.getString("ContratServiceTest.130"), 3, infosContrat.getNbAnneesFidelite());
        assertEquals(Messages.getString("ContratServiceTest.131"), 9, infosContrat.getNbMoisFidelite());
        assertEquals(Messages.getString("ContratServiceTest.132"), ACTIFS, infosContrat.getPopulation());
        assertEquals(Messages.getString("ContratServiceTest.133"), 1L, infosContrat.getSegment().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.134"), "Individuel", infosContrat.getSegment().getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.136"), idStatutContratEnCours, infosContrat.getStatut().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.137"), "En cours", infosContrat.getStatut().getLibelle());
        assertTrue(Messages.getString("ContratServiceTest.139"), infosContrat.getTeletransmission());
        final double reserveAnnuelle = 200.5;
        final double reserveConsommee = 55.5;
        assertEquals(Messages.getString("ContratServiceTest.140"), 1, infosContrat.getListeReservesBanco().size());
        assertEquals(Messages.getString("ContratServiceTest.141"), reserveAnnuelle, infosContrat.getListeReservesBanco().get(0).getReserveAnnuelle());
        assertEquals(Messages.getString("ContratServiceTest.142"), reserveConsommee, infosContrat.getListeReservesBanco().get(0).getReserveConsommee());
        assertEquals(Messages.getString("ContratServiceTest.143"), 9, infosContrat.getListeRatiosPrestationCotisation().size());
        boolean ratioPersonne1Annee2011 = false;
        boolean ratioPersonne1Annee2010 = false;
        boolean ratioPersonne1Annee2009 = false;
        boolean ratioPersonne2Annee2011 = false;
        boolean ratioPersonne2Annee2010 = false;
        boolean ratioPersonne2Annee2009 = false;
        boolean ratioPersonne3Annee2011 = false;
        boolean ratioPersonne3Annee2010 = false;
        boolean ratioPersonne3Annee2009 = false;
        final int annee2011 = 2011;
        final int annee2010 = 2010;
        final int annee2009	= 2009;
        final double ratioPrestaCotisPersonne1Annee2011 = 12;
        final double ratioPrestaCotisPersonne1Annee2010 = 126;
        final double ratioPrestaCotisPersonne1Annee2009 = 42;
        final double ratioPrestaCotisPersonne2Annee2011 = 190;
        final double ratioPrestaCotisPersonne2Annee2010 = 78;
        final double ratioPrestaCotisPersonne2Annee2009 = 135;
        final double ratioPrestaCotisPersonne3Annee2011 = 83;
        final double ratioPrestaCotisPersonne3Annee2010 = 96;
        final double ratioPrestaCotisPersonne3Annee2009 = 150;

        for (RatioPrestationCotisationDto ratio : infosContrat.getListeRatiosPrestationCotisation()) {
        	if (ratio.getPersonne().getIdentifiant().equals(1L)) {
            	if (ratio.getAnnee() == annee2011) {
                    assertEquals(Messages.getString("ContratServiceTest.144"), ratioPrestaCotisPersonne1Annee2011, ratio.getRatioPrestationCotisation());
                    ratioPersonne1Annee2011 = true;
                } else if (ratio.getAnnee() == annee2010) {
                    assertEquals(Messages.getString("ContratServiceTest.145"), ratioPrestaCotisPersonne1Annee2010, ratio.getRatioPrestationCotisation());
                    ratioPersonne1Annee2010 = true;
                } else if (ratio.getAnnee() == annee2009) {
                	assertEquals(Messages.getString("ContratServiceTest.395"), ratioPrestaCotisPersonne1Annee2009, ratio.getRatioPrestationCotisation());
                	ratioPersonne1Annee2009 = true;
            	} else {
                    fail(Messages.getString("ContratServiceTest.146"));
                }
            } else if (ratio.getPersonne().getIdentifiant().equals(2L)) {
                if (ratio.getAnnee() == annee2011) {
                    assertEquals(Messages.getString("ContratServiceTest.147"), ratioPrestaCotisPersonne2Annee2011, ratio.getRatioPrestationCotisation());
                    ratioPersonne2Annee2011 = true;
                } else if (ratio.getAnnee() == annee2010) {
                    assertEquals(Messages.getString("ContratServiceTest.148"), ratioPrestaCotisPersonne2Annee2010, ratio.getRatioPrestationCotisation());
                    ratioPersonne2Annee2010 = true;
                } else if (ratio.getAnnee() == annee2009) {
                	assertEquals(Messages.getString("ContratServiceTest.396"), ratioPrestaCotisPersonne2Annee2009, ratio.getRatioPrestationCotisation());
                	ratioPersonne2Annee2009 = true;
            	} else {
                    fail(Messages.getString("ContratServiceTest.149"));
                }
            } else if (ratio.getPersonne().getIdentifiant().equals(3L)) {
                if (ratio.getAnnee() == annee2011) {
                    assertEquals(Messages.getString("ContratServiceTest.150"), ratioPrestaCotisPersonne3Annee2011, ratio.getRatioPrestationCotisation());
                    ratioPersonne3Annee2011 = true;
                } else if (ratio.getAnnee() == annee2010) {
                    assertEquals(Messages.getString("ContratServiceTest.151"), ratioPrestaCotisPersonne3Annee2010, ratio.getRatioPrestationCotisation());
                    ratioPersonne3Annee2010 = true;
                } else if (ratio.getAnnee() == annee2009) {
                	assertEquals(Messages.getString("ContratServiceTest.397"), ratioPrestaCotisPersonne3Annee2009, ratio.getRatioPrestationCotisation());
                	ratioPersonne3Annee2009 = true;
            	} else {
                    fail(Messages.getString("ContratServiceTest.152"));
                }
            } else {
                fail(Messages.getString("ContratServiceTest.153"));
            }
        }
        assertTrue(Messages.getString("ContratServiceTest.154"), ratioPersonne1Annee2011 && ratioPersonne1Annee2010 && ratioPersonne2Annee2011
            && ratioPersonne2Annee2010 && ratioPersonne3Annee2011 && ratioPersonne3Annee2010);
        assertEquals(Messages.getString("ContratServiceTest.155"), listeContratsDto.getListeContrats().size(), 2);
        boolean isContrat1 = false;
        boolean isContrat2 = false;
        for (ContratSimpleDto contrat : listeContratsDto.getListeContrats()) {
            if (contrat.getIdentifiant().equals(1L)) {
                assertEquals(Messages.getString("ContratServiceTest.156"), NUMERO_CONTRAT, contrat.getNumeroContrat());
                assertEquals(Messages.getString("ContratServiceTest.158"), "L32", contrat.getCodeApporteur());
                assertEquals(Messages.getString("ContratServiceTest.160"), idStatutContratEnCours, contrat.getStatut().getIdentifiant());
                assertEquals(Messages.getString("ContratServiceTest.161"), "En cours", contrat.getStatut().getLibelle());
                isContrat1 = true;
            } else if (contrat.getIdentifiant().equals(2L)) {
                assertEquals(Messages.getString("ContratServiceTest.163"), "02/SR/01", contrat.getNumeroContrat());
                assertEquals(Messages.getString("ContratServiceTest.165"), "A31", contrat.getCodeApporteur());
                assertEquals(Messages.getString("ContratServiceTest.167"), idStatutContratSuspendu, contrat.getStatut().getIdentifiant());
                assertEquals(Messages.getString("ContratServiceTest.168"), "Suspendu", contrat.getStatut().getLibelle());
                isContrat2 = true;
            } else {
                fail(Messages.getString("ContratServiceTest.170"));
            }
        }
        assertTrue(Messages.getString("ContratServiceTest.171"), isContrat1 && isContrat2);
    }

    /** Test du service hasContratAssureBeneficiaire. */
    @Test
    public void testHasContratAssureBeneficiaire() {
        final Long idAssure = 1L;
        final Long idBeneficiaireAvecContrat = 2L;
        final Long idBeneficiaireSansContrat = 15L;
        final Long idAssureContratTermine = 4L;
        final Long idBeneficiaireContratTermine = 5L;

        InfosContratsBeneficiaireDto infosContratsBenef = contratService.hasContratAssureBeneficiaire(idAssure, idBeneficiaireAvecContrat);
        assertTrue(Messages.getString("ContratServiceTest.172"), infosContratsBenef.isHasContrats());
        assertTrue(Messages.getString("ContratServiceTest.173"), infosContratsBenef.isHasContratsActifs());

        infosContratsBenef = contratService.hasContratAssureBeneficiaire(idAssure, idBeneficiaireSansContrat);
        assertFalse(Messages.getString("ContratServiceTest.174"), infosContratsBenef.isHasContrats());
        assertFalse(Messages.getString("ContratServiceTest.175"), infosContratsBenef.isHasContratsActifs());

        infosContratsBenef = contratService.hasContratAssureBeneficiaire(idAssureContratTermine, idBeneficiaireContratTermine);
        assertTrue(Messages.getString("ContratServiceTest.176"), infosContratsBenef.isHasContrats());
        assertFalse(Messages.getString("ContratServiceTest.177"), infosContratsBenef.isHasContratsActifs());
    }

    /** Test du service getContratPersonneMorale. */
    @Test
    public void testGetContratPersonneMorale() {
        final Long uidContrat = 12L;
        final Long idStatutContratEnCours = 4714269L;
        final Long idStatutContratResilie = 4714252L;
        final Long idTypePayeurAssure = 4714130L;
        final Integer idProduitSante = 1862074;
        final Integer idProduitDeces65 = 1861998;
        final ContratPersonneMoraleDto contrat = contratService.getContratPersonneMorale(uidContrat);
        assertEquals(Messages.getString("ContratServiceTest.178"), uidContrat, contrat.getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.179"), "12/SR/10", contrat.getNumeroContrat());
        assertEquals(Messages.getString("ContratServiceTest.181"), idStatutContratEnCours, contrat.getStatut().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.182"), 3L, contrat.getSegment().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.183"), "Sylvie Blancheton", contrat.getApporteur());
        assertEquals(Messages.getString("ContratServiceTest.185"), "A12", contrat.getCodeApporteur());
        assertEquals(Messages.getString("ContratServiceTest.187"), "SMC SANTE, SMI ABSOLUE, SMI ACTIVE, SMI PREV", contrat.getProduitGestion());
        assertEquals(Messages.getString("ContratServiceTest.189"), idTypePayeurAssure, contrat.getTypeGestion().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.190"), "Facultative", contrat.getTypeSouscription());
        assertEquals(Messages.getString("ContratServiceTest.192"), 1, contrat.getNbAdherents());
        assertEquals(Messages.getString("ContratServiceTest.193"), 1, contrat.getNbBeneficiaires());
        assertEquals(Messages.getString("ContratServiceTest.194"), 2, contrat.getRecapitulatifPopulation().getListeStatuts().size());
        assertEquals(Messages.getString("ContratServiceTest.195"), idStatutContratEnCours, contrat.getRecapitulatifPopulation()
                .getListeStatuts().get(0).getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.196"), idStatutContratResilie, contrat.getRecapitulatifPopulation()
                .getListeStatuts().get(1).getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.197"), 4, contrat.getRecapitulatifPopulation().getListeValeursPopulation()
                .size());
        assertEquals(Messages.getString("ContratServiceTest.198"), ACTIFS, contrat.getRecapitulatifPopulation().getListeValeursPopulation().get(0)
                .getLibellePopulation());
        assertEquals(Messages.getString("ContratServiceTest.199"), 2, contrat.getRecapitulatifPopulation()
                .getListeValeursPopulation().get(0).getListeEffectifsParStatut().get(0).getEffectif());
        assertEquals(Messages.getString("ContratServiceTest.200"), 1, contrat.getRecapitulatifPopulation()
                .getListeValeursPopulation().get(0).getListeEffectifsParStatut().get(1).getEffectif());
        assertEquals(Messages.getString("ContratServiceTest.201"), "RETRAITES", contrat.getRecapitulatifPopulation().getListeValeursPopulation().get(1)
                .getLibellePopulation());
        assertEquals(Messages.getString("ContratServiceTest.203"), 1, contrat.getRecapitulatifPopulation()
                .getListeValeursPopulation().get(1).getListeEffectifsParStatut().get(0).getEffectif());
        assertNull(Messages.getString("ContratServiceTest.204"), contrat.getRecapitulatifPopulation().getListeValeursPopulation()
                .get(1).getListeEffectifsParStatut().get(1).getEffectif());
        assertEquals(Messages.getString("ContratServiceTest.205"), "Toute Population", contrat.getRecapitulatifPopulation().getListeValeursPopulation()
                .get(2).getLibellePopulation());
        assertNull(Messages.getString("ContratServiceTest.207"), contrat.getRecapitulatifPopulation()
                .getListeValeursPopulation().get(2).getListeEffectifsParStatut().get(0).getEffectif());
        assertEquals(Messages.getString("ContratServiceTest.208"), 1, contrat.getRecapitulatifPopulation()
                .getListeValeursPopulation().get(2).getListeEffectifsParStatut().get(1).getEffectif());
        assertEquals(Messages.getString("ContratServiceTest.209"), "Autre population", contrat.getRecapitulatifPopulation()
                .getListeValeursPopulation().get(3).getLibellePopulation());
        assertEquals(Messages.getString("ContratServiceTest.211"), 1, contrat.getRecapitulatifPopulation()
                .getListeValeursPopulation().get(3).getListeEffectifsParStatut().get(0).getEffectif());
        assertNull(Messages.getString("ContratServiceTest.212"), contrat.getRecapitulatifPopulation()
                .getListeValeursPopulation().get(3).getListeEffectifsParStatut().get(1).getEffectif());
        assertEquals(Messages.getString("ContratServiceTest.213"), 4, contrat.getListeGaranties().size());

        assertEquals(Messages.getString("ContratServiceTest.214"), idProduitSante, contrat.getListeGaranties().get(0).getIdProduit());
        assertEquals(Messages.getString("ContratServiceTest.215"), "SANTE 150I", contrat.getListeGaranties().get(0).getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.217"), 2, contrat.getListeGaranties().get(0).getListeInfosGarantie().size());
        assertEquals(Messages.getString("ContratServiceTest.218"), "456 789", contrat.getListeGaranties().get(0)
                .getListeInfosGarantie().get(0).getCodeTarif());
        assertEquals(Messages.getString("ContratServiceTest.220"), "SANTE 150I_CFTA67", contrat.getListeGaranties().get(0)
                .getListeInfosGarantie().get(0).getLibelleGarantieGestion());
        assertEquals(Messages.getString("ContratServiceTest.222"), ACTIFS, contrat.getListeGaranties().get(0)
                .getListeInfosGarantie().get(0).getLibellePopulation());
        assertEquals(Messages.getString("ContratServiceTest.223"), null, contrat.getListeGaranties().get(0)
                .getListeInfosGarantie().get(0).getMontantSouscrit());
        assertEquals(Messages.getString("ContratServiceTest.224"), "164 789", contrat.getListeGaranties().get(0)
                .getListeInfosGarantie().get(1).getCodeTarif());
        assertEquals(Messages.getString("ContratServiceTest.226"), "SANTE 150I_CFTA67", contrat.getListeGaranties().get(0)
                .getListeInfosGarantie().get(1).getLibelleGarantieGestion());
        assertEquals(Messages.getString("ContratServiceTest.228"), ACTIFS, contrat.getListeGaranties().get(0)
                .getListeInfosGarantie().get(1).getLibellePopulation());
        assertEquals(Messages.getString("ContratServiceTest.229"), null, contrat.getListeGaranties().get(0)
                .getListeInfosGarantie().get(1).getMontantSouscrit());

        assertEquals(Messages.getString("ContratServiceTest.230"), idProduitDeces65, contrat.getListeGaranties().get(1).getIdProduit());
        assertEquals(Messages.getString("ContratServiceTest.231"), "DECES 65", contrat.getListeGaranties().get(1).getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.233"), 1, contrat.getListeGaranties().get(1).getListeInfosGarantie().size());
        assertEquals(Messages.getString("ContratServiceTest.234"), "456 182", contrat.getListeGaranties().get(1)
                .getListeInfosGarantie().get(0).getCodeTarif());
        assertEquals(Messages.getString("ContratServiceTest.236"), "DECES 65", contrat.getListeGaranties().get(1)
                .getListeInfosGarantie().get(0).getLibelleGarantieGestion());
        assertEquals(Messages.getString("ContratServiceTest.238"), "Toute Population", contrat.getListeGaranties()
                .get(1).getListeInfosGarantie().get(0).getLibellePopulation());
        final Double montantSouscrit1 = 15.5;
        assertEquals(Messages.getString("ContratServiceTest.240"), montantSouscrit1, contrat.getListeGaranties().get(1)
                .getListeInfosGarantie().get(0).getMontantSouscrit());

        final Long uidContratAvecInfosPaiement = 13L;
        final ContratPersonneMoraleDto contratAvecInfosPaiement = contratService.getContratPersonneMorale(uidContratAvecInfosPaiement);
        assertNotNull(Messages.getString("ContratServiceTest.241"), contratAvecInfosPaiement.getInfosPaiement());
        assertEquals(Messages.getString("ContratServiceTest.242"), "19", contratAvecInfosPaiement.getInfosPaiement().getCle());
        assertEquals(Messages.getString("ContratServiceTest.244"), "12345", contratAvecInfosPaiement.getInfosPaiement()
                .getCodeBanque());
        assertEquals(Messages.getString("ContratServiceTest.246"), "12345678910", contratAvecInfosPaiement.getInfosPaiement()
                .getCodeCompte());
        assertEquals(Messages.getString("ContratServiceTest.248"), "00123", contratAvecInfosPaiement.getInfosPaiement().getCodeGuichet());
        assertEquals(Messages.getString("ContratServiceTest.250"), 2L, contratAvecInfosPaiement.getInfosPaiement()
                .getMoyenPaiement().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.251"), 2L, contratAvecInfosPaiement
                .getInfosPaiement().getFrequencePaiement().getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.252"), 5, contratAvecInfosPaiement.getInfosPaiement().getJourPaiement());
        assertEquals(Messages.getString("ContratServiceTest.253"), "contrat", contratAvecInfosPaiement.getInfosPaiement()
                .getTypeEcheance());
    }

    /** Test du service getInfosContratPersonneMorale. */
    @Test
    public void testGetInfosContratPersonneMorale() {
        final Long uidPersonneMorale = 10L;
        final Long idStatutContratEnCours = 4714269L;
        final Long idStatutContratResilie = 4714252L;
        final Long idContrat11 = 11L;
        final InfosContratsPersonneMoraleDto infosContrat = contratService.getInfosContratPersonneMorale(uidPersonneMorale);
        // Vérification des informations du bloc synthèse
        assertEquals(Messages.getString("ContratServiceTest.255"), "Souscripteur", infosContrat.getSyntheseContrat().getGestionDuContrat());
        assertEquals(Messages.getString("ContratServiceTest.257"), "Gestionnaire10", infosContrat.getSyntheseContrat().getGestionnaire());
        assertEquals(Messages.getString("ContratServiceTest.259"), 4, infosContrat.getSyntheseContrat().getNbAnneesFidelite());
        assertEquals(Messages.getString("ContratServiceTest.260"), 7, infosContrat.getSyntheseContrat().getNbMoisFidelite());
        assertEquals(Messages.getString("ContratServiceTest.261"), idStatutContratEnCours, infosContrat.getSyntheseContrat().getStatut().getIdentifiant());

        // Vérification de la population
        assertEquals(Messages.getString("ContratServiceTest.262"), 3, infosContrat.getSyntheseContrat().getPopulation().size());
        assertEquals(Messages.getString("ContratServiceTest.263"), ACTIFS, infosContrat.getSyntheseContrat().getPopulation().get(0)
                .getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.264"), 3, infosContrat.getSyntheseContrat().getPopulation().get(0).getEffectif());
        assertEquals(Messages.getString("ContratServiceTest.265"), "RETRAITES", infosContrat.getSyntheseContrat().getPopulation().get(1)
                .getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.267"), 1, infosContrat.getSyntheseContrat().getPopulation().get(1).getEffectif());
        assertEquals(Messages.getString("ContratServiceTest.268"), "Autre population", infosContrat.getSyntheseContrat().getPopulation()
                .get(2).getLibelle());
        assertEquals(Messages.getString("ContratServiceTest.270"), 1, infosContrat.getSyntheseContrat().getPopulation().get(2)
                .getEffectif());

        // Vérification de la liste de contrats
        assertEquals(Messages.getString("ContratServiceTest.271"), 2, infosContrat.getListeContrats().size());
        assertEquals(Messages.getString("ContratServiceTest.272"), 10L, infosContrat.getListeContrats().get(0).getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.273"), NUMERO_CONTRAT_2, infosContrat.getListeContrats().get(0).getNumeroContrat());
        assertEquals(Messages.getString("ContratServiceTest.275"), idStatutContratEnCours, infosContrat.getListeContrats().get(0).getStatut()
                .getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.276"), "A10", infosContrat.getListeContrats().get(0).getCodeApporteur());
        assertEquals(Messages.getString("ContratServiceTest.278"), idContrat11, infosContrat.getListeContrats().get(1).getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.279"), "11/SR/11", infosContrat.getListeContrats().get(1).getNumeroContrat());
        assertEquals(Messages.getString("ContratServiceTest.281"), idStatutContratResilie, infosContrat.getListeContrats().get(1).getStatut()
                .getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.282"), "A11", infosContrat.getListeContrats().get(1).getCodeApporteur());
    }

    /** Test du service hasPersonneContrats. */
    @Test
    public void testHasPersonneContrats() {
        final Long idPersonneSansContrat = 100L;
        final Long idPersonneAvecContrat = 1L;
        assertFalse(Messages.getString("ContratServiceTest.284"), contratService.hasPersonneContrats(idPersonneSansContrat));
        assertTrue(Messages.getString("ContratServiceTest.285"), contratService.hasPersonneContrats(idPersonneAvecContrat));
    }

    /** Test du service getContratsSimpleByCriteres. */
    @Test
    public void testGetContratsSimpleByCriteres() {
        CritereRechercheContratDto criteres = null;

        List<ContratSimpleDto> listeContrats = contratService.getContratsSimpleByCriteres(criteres);
        assertEquals(Messages.getString("ContratServiceTest.286"), TREIZE, listeContrats.size());

        criteres = new CritereRechercheContratDto();
        criteres.setIdAssure(1L);
        listeContrats = contratService.getContratsSimpleByCriteres(criteres);
        assertEquals(Messages.getString("ContratServiceTest.287"), 2, listeContrats.size());
        assertEquals(Messages.getString("ContratServiceTest.288"), 1L, listeContrats.get(0).getIdentifiant());
        assertEquals(Messages.getString("ContratServiceTest.289"), 2L, listeContrats.get(1).getIdentifiant());

        criteres = new CritereRechercheContratDto();
        criteres.setIdAssure(1L);
        criteres.setNumeroContrat("1");
        listeContrats = contratService.getContratsSimpleByCriteres(criteres);
        assertEquals(Messages.getString("ContratServiceTest.291"), 0, listeContrats.size());
    }

    /** Test du service getListeCoordonneesBancaires. */
    @Test
    public void testGetListeCoordonneesBancaires() {
        final Long idPersonneSansContrat = 100L;
        final Long idPersonneAvecContrat = 1L;

        List<CoordonneesBancairesDto> listeCoordonnees = contratService.getListeCoordonneesBancaires(idPersonneSansContrat);
        assertEquals(Messages.getString("ContratServiceTest.292"), 0, listeCoordonnees.size());

        listeCoordonnees = contratService.getListeCoordonneesBancaires(idPersonneAvecContrat);
        assertEquals(Messages.getString("ContratServiceTest.293"), 1, listeCoordonnees.size());
        final CoordonneesBancairesDto coordonnees1 = listeCoordonnees.get(0);
        assertEquals(Messages.getString("ContratServiceTest.294"), NUMERO_CONTRAT, coordonnees1.getNumeroContrat());
        assertNotNull(Messages.getString("ContratServiceTest.296"), coordonnees1.getInfosBanque());
        assertEquals(Messages.getString("ContratServiceTest.297"), "ANGOULEME", coordonnees1.getInfosBanque().getDomiciliation());
        assertNotNull(Messages.getString("ContratServiceTest.299"), coordonnees1.getInfosPaiement());
        assertEquals(Messages.getString("ContratServiceTest.300"), "Virement", coordonnees1.getInfosPaiement().getMoyenPaiement().getLibelle());
    }

    /** Test du service getListeContratsCollectifsByCriteres. */
    @Test
    public void testGetListeContratsCollectifsByCriteres() {
        ContratCollectifCriteresDto criteres = new ContratCollectifCriteresDto();
        List<String> liste = contratService.getListeContratsCollectifsByCriteres(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 5, liste.size());
        assertEquals(Messages.getString("ContratServiceTest.302"), NUMERO_CONTRAT, liste.get(0));
        assertEquals(Messages.getString("ContratServiceTest.304"), "02/SR/01", liste.get(1));
        assertEquals(Messages.getString("ContratServiceTest.306"), "03/SR/01", liste.get(2));
        assertEquals(Messages.getString("ContratServiceTest.308"), NUMERO_CONTRAT_2, liste.get(3));
        assertEquals(Messages.getString("ContratServiceTest.310"), "11/SR/11", liste.get(4));

        criteres = new ContratCollectifCriteresDto();
        criteres.setUidEntreprise(1L);
        liste = contratService.getListeContratsCollectifsByCriteres(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 3, liste.size());
        assertEquals(Messages.getString("ContratServiceTest.312"), NUMERO_CONTRAT, liste.get(0));
        assertEquals(Messages.getString("ContratServiceTest.314"), "02/SR/01", liste.get(1));
        assertEquals(Messages.getString("ContratServiceTest.316"), "03/SR/01", liste.get(2));

        criteres = new ContratCollectifCriteresDto();
        criteres.setUidEntreprise(10L);
        liste = contratService.getListeContratsCollectifsByCriteres(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 2, liste.size());
        assertEquals(Messages.getString("ContratServiceTest.318"), NUMERO_CONTRAT_2, liste.get(0));
        assertEquals(Messages.getString("ContratServiceTest.320"), "11/SR/11", liste.get(1));
    }

    /** Test du service getListePopulationsByCriteres. */
    @Test
    public void testGetListePopulationsByCriteres() {
        PopulationCriteresDto criteres = new PopulationCriteresDto();
        List<String> liste = contratService.getListePopulationsByCriteres(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 3, liste.size());
        assertEquals(POPULATION_PAS_BONNE, ACTIFS, liste.get(0));
        assertEquals(POPULATION_PAS_BONNE, "ANI", liste.get(1));
        assertEquals(POPULATION_PAS_BONNE, "Toute Population", liste.get(2));

        criteres = new PopulationCriteresDto();
        criteres.setContrat("03/SR/01");
        liste = contratService.getListePopulationsByCriteres(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 1, liste.size());
        assertEquals(POPULATION_PAS_BONNE, ACTIFS, liste.get(0));
    }

    /** Test du service getListeProduitsCollectifsAdherent. */
    @Test
    public void testGetListeProduitsCollectifsAdherent() {
        ProduitCollectifAdherentCriteresDto criteres = new ProduitCollectifAdherentCriteresDto();
        List<ProduitCollectifAdherentDto> liste = contratService.getListeProduitsCollectifsAdherent(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 8, liste.size());
        assertEquals(PRODUIT_PAS_BON, "SMATIS EXCELLENCE C_CFTA41 O1/SMC SANTE/123 456/null/1", getProduit(liste.get(0)));
        assertEquals(PRODUIT_PAS_BON, "ALLOCATION HOSPITALIERE COMPLEMENTAIRE/SMC SANTE/123 456/500.0/1", getProduit(liste.get(1)));
        assertEquals(PRODUIT_PAS_BON, "SANTE 150I_CFTA67/SMI ABSOLUE/456 789/null/7", getProduit(liste.get(2)));
        assertEquals(PRODUIT_PAS_BON, "DECES 65/SMI ACTIVE/456 182/15.5/0", getProduit(liste.get(3)));
        assertEquals(PRODUIT_PAS_BON, "SANTE 150I_CFTA67/SMI ABSOLUE/164 789/null/7", getProduit(liste.get(4)));
        assertEquals(PRODUIT_PAS_BON, "DECES TEMPORAIRE DC69/SMI PREV/456 485/null/0", getProduit(liste.get(5)));
        assertEquals(PRODUIT_PAS_BON, "SMATIS EXCELLENCE C_CFTA41 O1/SMC SANTE/164 789/null/0", getProduit(liste.get(6)));
        assertEquals(PRODUIT_PAS_BON, "ALLOCATION HOSPITALIERE COMPLEMENTAIRE/SMC SANTE/456 485/null/0", getProduit(liste.get(7)));

        criteres = new ProduitCollectifAdherentCriteresDto();
        criteres.setContrat(NUMERO_CONTRAT_2);
        liste = contratService.getListeProduitsCollectifsAdherent(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 4, liste.size());
        assertEquals(PRODUIT_PAS_BON, "SANTE 150I_CFTA67/SMI ABSOLUE/456 789/null/1", getProduit(liste.get(0)));
        assertEquals(PRODUIT_PAS_BON, "DECES 65/SMI ACTIVE/456 182/15.5/0", getProduit(liste.get(1)));
        assertEquals(PRODUIT_PAS_BON, "SANTE 150I_CFTA67/SMI ABSOLUE/164 789/null/1", getProduit(liste.get(2)));
        assertEquals(PRODUIT_PAS_BON, "DECES TEMPORAIRE DC69/SMI PREV/456 485/null/0", getProduit(liste.get(3)));

        criteres = new ProduitCollectifAdherentCriteresDto();
        criteres.setContrat(NUMERO_CONTRAT_2);
        criteres.setPopulation(ACTIFS);
        liste = contratService.getListeProduitsCollectifsAdherent(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 2, liste.size());
        assertEquals(PRODUIT_PAS_BON, "SANTE 150I_CFTA67/SMI ABSOLUE/456 789/null/1", getProduit(liste.get(0)));
        assertEquals(PRODUIT_PAS_BON, "SANTE 150I_CFTA67/SMI ABSOLUE/164 789/null/1", getProduit(liste.get(1)));

        criteres = new ProduitCollectifAdherentCriteresDto();
        criteres.setContrat(NUMERO_CONTRAT_2);
        criteres.setPopulation("RETRAITES");
        liste = contratService.getListeProduitsCollectifsAdherent(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 0, liste.size());

        criteres = new ProduitCollectifAdherentCriteresDto();
        criteres.setContrat(NUMERO_CONTRAT_2);
        criteres.setPopulation(ACTIFS);
        criteres.setUidEntreprise(10L);
        liste = contratService.getListeProduitsCollectifsAdherent(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 2, liste.size());
        assertEquals(PRODUIT_PAS_BON, "SANTE 150I_CFTA67/SMI ABSOLUE/456 789/null/1", getProduit(liste.get(0)));
        assertEquals(PRODUIT_PAS_BON, "SANTE 150I_CFTA67/SMI ABSOLUE/164 789/null/1", getProduit(liste.get(1)));

        criteres = new ProduitCollectifAdherentCriteresDto();
        criteres.setContrat(NUMERO_CONTRAT_2);
        criteres.setPopulation(ACTIFS);
        criteres.setUidEntreprise(1L);
        liste = contratService.getListeProduitsCollectifsAdherent(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 0, liste.size());

        Calendar dateEffet = Calendar.getInstance();
        dateEffet.clear();
        dateEffet.set(YEAR_2011, 1, 1);
        criteres = new ProduitCollectifAdherentCriteresDto();
        criteres.setContrat(NUMERO_CONTRAT_2);
        criteres.setPopulation(ACTIFS);
        criteres.setUidEntreprise(10L);
        criteres.setDateEffet(dateEffet);
        liste = contratService.getListeProduitsCollectifsAdherent(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 2, liste.size());
        assertEquals(PRODUIT_PAS_BON, "SANTE 150I_CFTA67/SMI ABSOLUE/456 789/null/1", getProduit(liste.get(0)));
        assertEquals(PRODUIT_PAS_BON, "SANTE 150I_CFTA67/SMI ABSOLUE/164 789/null/1", getProduit(liste.get(1)));

        dateEffet = Calendar.getInstance();
        dateEffet.clear();
        dateEffet.set(YEAR_2007, 1, 1);
        criteres = new ProduitCollectifAdherentCriteresDto();
        criteres.setContrat(NUMERO_CONTRAT_2);
        criteres.setPopulation(ACTIFS);
        criteres.setUidEntreprise(10L);
        criteres.setDateEffet(dateEffet);
        liste = contratService.getListeProduitsCollectifsAdherent(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 0, liste.size());
    }

    private String getProduit(ProduitCollectifAdherentDto produit) {
        return produit.getGarantieGestion() + BACKSLASH + produit.getProduitGestion() + BACKSLASH
        + produit.getCodeTarif() + BACKSLASH + produit.getMontantSouscrit() + BACKSLASH
        + produit.getNbAdherents();
    }

    /** Test du service getListeTypesPayeurs. */
    @Test
    public void testGetListeTypesPayeurs() {
        TypePayeurCriteresDto criteres = new TypePayeurCriteresDto();
        List<TypePayeurDto> liste = contratService.getListeTypesPayeurs(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 4, liste.size());
        assertEquals(TYPE_PAYEUR_PAS_BON, "Souscripteur/bareme1/null/null/null", getTypePayeur(liste.get(0)));
        assertEquals(TYPE_PAYEUR_PAS_BON, "Assuré/bareme2/null/null/null", getTypePayeur(liste.get(1)));
        assertEquals(TYPE_PAYEUR_PAS_BON, "Assuré/bareme3/null/null/null", getTypePayeur(liste.get(2)));
        assertEquals(TYPE_PAYEUR_PAS_BON, "Souscripteur/bareme4/null/bareme1/null", getTypePayeur(liste.get(3)));

        criteres = new TypePayeurCriteresDto();
        criteres.setContrat("11/SR/11");
        liste = contratService.getListeTypesPayeurs(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 0, liste.size());

        criteres = new TypePayeurCriteresDto();
        criteres.setContrat(NUMERO_CONTRAT_2);
        liste = contratService.getListeTypesPayeurs(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 4, liste.size());
        assertEquals(TYPE_PAYEUR_PAS_BON, "Souscripteur/bareme1/null/null/null", getTypePayeur(liste.get(0)));
        assertEquals(TYPE_PAYEUR_PAS_BON, "Assuré/bareme2/null/null/null", getTypePayeur(liste.get(1)));
        assertEquals(TYPE_PAYEUR_PAS_BON, "Assuré/bareme3/null/null/null", getTypePayeur(liste.get(2)));
        assertEquals(TYPE_PAYEUR_PAS_BON, "Souscripteur/bareme4/null/bareme1/null", getTypePayeur(liste.get(3)));

        criteres = new TypePayeurCriteresDto();
        criteres.setContrat(NUMERO_CONTRAT_2);
        criteres.setGarantieGestion("SANTE 150I_CFTA67");
        criteres.setProduitGestion("SMI ABSOLUE");
        liste = contratService.getListeTypesPayeurs(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 2, liste.size());
        assertEquals(TYPE_PAYEUR_PAS_BON, "Souscripteur/bareme1/null/null/null", getTypePayeur(liste.get(0)));
        assertEquals(TYPE_PAYEUR_PAS_BON, "Souscripteur/bareme4/null/bareme1/null", getTypePayeur(liste.get(1)));

        criteres = new TypePayeurCriteresDto();
        criteres.setContrat(NUMERO_CONTRAT_2);
        criteres.setGarantieGestion("SANTE 150I_CFTA67");
        criteres.setProduitGestion("SMI ABSOLUE");
        criteres.setMontantSouscrit(null);
        criteres.setCodeTarif("164 789");
        liste = contratService.getListeTypesPayeurs(criteres);
        assertEquals(NB_RESULTATS_PAS_BON, 1, liste.size());
        assertEquals(TYPE_PAYEUR_PAS_BON, "Souscripteur/bareme4/null/bareme1/null", getTypePayeur(liste.get(0)));
    }

    private String getTypePayeur(TypePayeurDto typePayeur) {
        return typePayeur.getLibelle() + BACKSLASH + typePayeur.getBareme1Eid() + BACKSLASH + typePayeur.getBareme1Zone()
        + BACKSLASH + typePayeur.getBareme2Eid() + BACKSLASH + typePayeur.getBareme2Zone();
    }

    /** Test du service getListeProduitsCollectifsAdherent. */
    @Test
    public void testGetContratFutur() {
        // recuperer le contrat avec l'id = 20
        final ContratDto contratDto = contratService.getContrat(20L);
        // verifier le statut futur du contrat
        assertEquals(Messages.getString("ContratServiceTest.6"), adherentMappingService.getIdStatutContratFutur(), contratDto.getStatut().getIdentifiant());
        // verifier le nombre de garanties
        assertEquals(Messages.getString("ContratServiceTest.7"), 2, contratDto.getListeGaranties().size());
        // parcourir les garanties récupérées
        final Long idGarantie20 = 20L;
        final Long idGarantie21 = 21L;
        boolean possedeGarantie20 = false;
        boolean possedeGarantie21 = false;
        for (GarantieDto garantieDto : contratDto.getListeGaranties()) {
            if (garantieDto.getId().equals(idGarantie20)) {
                possedeGarantie20 = true;
            }
            if (garantieDto.getId().equals(idGarantie21)) {
                possedeGarantie21 = true;
            }
            // verifier le statut futur de chaque garantie
            assertEquals(Messages.getString("ContratServiceTest.8"), adherentMappingService.getIdStatutGarantieFutur(),
            garantieDto.getStatut().getIdentifiant());
        }
        assertTrue(Messages.getString("ContratServiceTest.10"), possedeGarantie20 && possedeGarantie21);
    }

    /** Test du service getContrat pour un contrat en cours. */
    @Test
    public void testGetContratEnCours() {
        // recuperer le contrat avec l'id = 12
        final ContratDto contratDto = contratService.getContrat(12L);
        // verifier le statut en cours du contrat
        assertEquals(Messages.getString("ContratServiceTest.11"), adherentMappingService.getIdStatutContratEnCours(), contratDto.getStatut().getIdentifiant());
        // Verifions le nombre de garanties
        assertEquals(Messages.getString("ContratServiceTest.391"), 1, contratDto.getListeGaranties().size());
        // parcourir les garanties récupérées
        final Long idGarantie10 = 10L;
        boolean possedeGarantie10 = false;
        for (GarantieDto garantieDto : contratDto.getListeGaranties()) {
            if (garantieDto.getId().equals(idGarantie10)) {
                possedeGarantie10 = true;
            }
            // verifier le statut en cours de chaque garantie
            assertEquals(Messages.getString("ContratServiceTest.392"), adherentMappingService.getIdStatutGarantieEnCours(),
            garantieDto.getStatut().getIdentifiant());
        }
        assertTrue(Messages.getString("ContratServiceTest.393"), possedeGarantie10);
    }
}
