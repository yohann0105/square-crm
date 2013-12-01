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
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.adherent.noyau.dto.adherent.InfosOptionsAdherentDto;
import com.square.adherent.noyau.dto.adherent.contrat.CoupleBaremeDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBaremeCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBaremeDto;
import com.square.adherent.noyau.dto.prestation.CriteresPersonnesNotificationSmsDto;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.AdherentService;

/**
 * Tests unitaires des services liés aux adhérents.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class AdherentServiceTest extends DbunitBaseTestCase {

    private static final String SEP = "-";

    private static final String BAREME_PAS_BON = Messages.getString("AdherentServiceTest.1");

    private static final String NOMBRE_BAREMES_PAS_BON = Messages.getString("AdherentServiceTest.2");

	private static final Long ONZE = 11L;

	private static final Long QUATORZE = 14L;

	private static final Long SEIZE = 16L;

	private static final Long ROLE_IDENTIFIANT = 4714236L;

	private static final Integer VINGT_SIX = 26;

	private static final Double CODE_NVIGAR = 12.56;

    private AdherentService adherentService;

    private AdherentMappingService adherentMappingService;

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        adherentService = (AdherentService) getBeanSpring("adherentService");
        adherentMappingService = (AdherentMappingService) getBeanSpring("adherentMappingService");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"adherentMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "dataset-adherent.xml";
    }

    /** Test du service getListePersonnesByCriteres. */
    @Test
    public void testGetInfosOptionsAdherent() {
        final Long uidPersonne = 1L;
        final SimpleDateFormat sdf = new SimpleDateFormat(Messages.getString("AdherentServiceTest.8"));

        final InfosOptionsAdherentDto infosOptionsAdherentDto = adherentService.getInfosOptionsAdherent(uidPersonne);
        assertEquals(Messages.getString("AdherentServiceTest.9"), "01/11/2008", sdf.format(infosOptionsAdherentDto.getDateModificationOptions().getTime()));
        assertEquals(Messages.getString("AdherentServiceTest.11"), 2, infosOptionsAdherentDto.getListeOptions().size());
        // option 1
        assertEquals(Messages.getString("AdherentServiceTest.12"), 1L, infosOptionsAdherentDto.getListeOptions().get(0).getId());
        assertEquals(Messages.getString("AdherentServiceTest.13"), Messages.getString("AdherentServiceTest.14"), infosOptionsAdherentDto.getListeOptions().get(
            0).getType().getLibelle());
        assertTrue(Messages.getString("AdherentServiceTest.15"), infosOptionsAdherentDto.getListeOptions().get(0).isActive());
        // option 2
        assertEquals(Messages.getString("AdherentServiceTest.16"), 2L, infosOptionsAdherentDto.getListeOptions().get(1).getId());
        assertEquals(Messages.getString("AdherentServiceTest.17"), Messages.getString("AdherentServiceTest.18"),
        		infosOptionsAdherentDto.getListeOptions().get(1).getType()
                .getLibelle());
        assertTrue(Messages.getString("AdherentServiceTest.19"), infosOptionsAdherentDto.getListeOptions().get(1).isActive());
    }

    /** Test du service déterminant si une personne est associée à un contrat. */
    @Test
    public void testIsPersonneAssocieeAContrat() {
        // Test sur une personne possédant une garantie
        final Long idPersonneAvecGarantie = 3L;
        assertTrue(Messages.getString("AdherentServiceTest.20"), adherentService.isPersonneAssocieeAContrat(idPersonneAvecGarantie));
        // Test sur une personne ne possédant pas de garantie.
        final Long idPersonneSansGarantie = 2L;
        assertFalse(Messages.getString("AdherentServiceTest.21"), adherentService.isPersonneAssocieeAContrat(idPersonneSansGarantie));
    }

    /** Test du service recuperant les ids des adherents ayant l'option spécifiée. */
    @Test
    public void testGetListeAdherentsByIdOptionSouscrite() {
        // option qui n'existe pas
        Long idOption = 0L;
        List<Long> listeAdherents = adherentService.getListeAdherentsByIdOptionSouscrite(idOption);
        assertEquals(Messages.getString("AdherentServiceTest.22"), 0, listeAdherents.size());

        // option qui existe
        idOption = 1L;
        listeAdherents = adherentService.getListeAdherentsByIdOptionSouscrite(idOption);
        assertEquals(Messages.getString("AdherentServiceTest.23"), 5, listeAdherents.size());
        assertEquals(Messages.getString("AdherentServiceTest.24"), 3L, listeAdherents.get(0));
        assertEquals(Messages.getString("AdherentServiceTest.25"), 4L, listeAdherents.get(1));
        assertEquals(Messages.getString("AdherentServiceTest.26"), 10L, listeAdherents.get(2));
        assertEquals(Messages.getString("AdherentServiceTest.27"), ONZE, listeAdherents.get(3));
        assertEquals(Messages.getString("AdherentServiceTest.28"), SEIZE, listeAdherents.get(4));
    }

    /** Test du service recuperant les ids des adherents ayant reçu le mutuellement. */
    @Test
    public void testGetListeAdherentsByIdMagazineEnvoye() {
        // magazine qui n'existe pas
        Long idMagazine = 0L;
        List<Long> listeAdherents = adherentService.getListeAdherentsByIdMagazineEnvoye(idMagazine);
        assertEquals(Messages.getString("AdherentServiceTest.29"), 0, listeAdherents.size());

        // magazine qui existe
        idMagazine = 1L;
        listeAdherents = adherentService.getListeAdherentsByIdMagazineEnvoye(idMagazine);
        assertEquals(Messages.getString("AdherentServiceTest.30"), 3, listeAdherents.size());
        assertEquals(Messages.getString("AdherentServiceTest.31"), 1L, listeAdherents.get(0));
        assertEquals(Messages.getString("AdherentServiceTest.32"), 2L, listeAdherents.get(1));
        assertEquals(Messages.getString("AdherentServiceTest.33"), 3L, listeAdherents.get(2));
    }

    /** Test du service getListePersonnesByCriteres. */
    @Test
    public void tetGetListePersonnesNotificationSmsByCriteres() {
        // recherche des paiements pour une date et une valeur min
        final CriteresPersonnesNotificationSmsDto criteres = new CriteresPersonnesNotificationSmsDto();
        final Calendar date = Calendar.getInstance();
        date.clear();
        final int annee = 2009;
        final int mois = 5;
        final int jour = 15;
        date.set(annee, mois, jour);
        criteres.setDateReglement(date);
        final float montantMinimalCorrect = 10.00f;
        criteres.setMontantMinimal(montantMinimalCorrect);
        List<Long> listePersonnes = adherentService.getListePersonnesNotificationSmsByCriteres(criteres);
        assertEquals(Messages.getString("AdherentServiceTest.34"), 1, listePersonnes.size());
        assertEquals(Messages.getString("AdherentServiceTest.35"), 1L, listePersonnes.get(0));

        // montant minimal trop eleve
        final float montantMinimalTropEleve = 160.00f;
        criteres.setMontantMinimal(montantMinimalTropEleve);
        listePersonnes = adherentService.getListePersonnesNotificationSmsByCriteres(criteres);
        assertEquals(Messages.getString("AdherentServiceTest.36"), 0, listePersonnes.size());
    }

    /** Test du service getListeGarantiesBaremesAdherent. */
    @Test
    public void testGetListeGarantiesBaremesAdherent() {
        try {
            adherentService.getListeGarantiesBaremesAdherent(null);
            fail(Messages.getString("AdherentServiceTest.37"));
        }
        catch (TechnicalException e) {
            assertEquals(Messages.getString("AdherentServiceTest.38"), Messages.getString("AdherentServiceTest.95"),
            		e.getMessage());
        }

        GarantieBaremeCriteresDto criteres = new GarantieBaremeCriteresDto();
        List<GarantieBaremeDto> liste = adherentService.getListeGarantiesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 5, liste.size());
        assertEquals(BAREME_PAS_BON, 1L, liste.get(0).getId());
        assertEquals(BAREME_PAS_BON, 2L, liste.get(1).getId());
        assertEquals(BAREME_PAS_BON, 3L, liste.get(2).getId());
        assertEquals(BAREME_PAS_BON, ONZE, liste.get(3).getId());
        assertEquals(BAREME_PAS_BON, QUATORZE, liste.get(4).getId());

        criteres = new GarantieBaremeCriteresDto();
        criteres.setGarantie("2");
        liste = adherentService.getListeGarantiesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 2, liste.size());
        assertEquals(BAREME_PAS_BON, 2L, liste.get(0).getId());
        assertEquals(BAREME_PAS_BON, 3L, liste.get(1).getId());

        criteres = new GarantieBaremeCriteresDto();
        criteres.setGarantieGestion("garantie2");
        liste = adherentService.getListeGarantiesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 2, liste.size());
        assertEquals(BAREME_PAS_BON, 2L, liste.get(0).getId());
        assertEquals(BAREME_PAS_BON, 3L, liste.get(1).getId());

        criteres = new GarantieBaremeCriteresDto();
        criteres.setGarantieGestion("garantie2");
        criteres.setIdRoleGarantie(adherentMappingService.getIdRoleGarantieAssure());
        liste = adherentService.getListeGarantiesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 2, liste.size());
        assertEquals(BAREME_PAS_BON, 2L, liste.get(0).getId());
        assertEquals(BAREME_PAS_BON, 3L, liste.get(1).getId());

        criteres = new GarantieBaremeCriteresDto();
        criteres.setGarantieGestion("garantie2");
        criteres.setIdRoleGarantie(adherentMappingService.getIdRoleGarantieConjoint());
        liste = adherentService.getListeGarantiesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 0, liste.size());

        criteres = new GarantieBaremeCriteresDto();
        criteres.setGarantieGestion("garantie2");
        criteres.setProduitGestion("produit2");
        liste = adherentService.getListeGarantiesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 0, liste.size());

        criteres = new GarantieBaremeCriteresDto();
        criteres.setContrat("001");
        liste = adherentService.getListeGarantiesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 3, liste.size());
        assertEquals(BAREME_PAS_BON, 2L, liste.get(1).getId());
        assertEquals(BAREME_PAS_BON, 3L, liste.get(2).getId());

        criteres = new GarantieBaremeCriteresDto();
        criteres.setContrat("001");
        criteres.setEidTarifType("1");
        criteres.setEidTypePayeur("1");
        liste = adherentService.getListeGarantiesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 1, liste.size());
        final GarantieBaremeDto garantieBareme = liste.get(0);
        assertEquals(BAREME_PAS_BON, 1L, garantieBareme.getId());
        assertEquals(Messages.getString("AdherentServiceTest.49"), 1L, garantieBareme.getTarifType().getIdentifiant());
        assertEquals(Messages.getString("AdherentServiceTest.50"), "TarifType1", garantieBareme.getTarifType().getLibelle());
        assertEquals(Messages.getString("AdherentServiceTest.52"), 1L, garantieBareme.getTypePayeur().getIdentifiant());
        assertEquals(Messages.getString("AdherentServiceTest.53"), "TypePayeur1", garantieBareme.getTypePayeur().getLibelle());
        assertNotNull(Messages.getString("AdherentServiceTest.55"), garantieBareme.getFamille());
        assertEquals(Messages.getString("AdherentServiceTest.56"), 1L, garantieBareme.getFamille().getIdentifiant());
        assertEquals(Messages.getString("AdherentServiceTest.57"), "Santé", garantieBareme.getFamille().getLibelle());
        assertNotNull(Messages.getString("AdherentServiceTest.59"), garantieBareme.getRole());
        assertEquals(Messages.getString("AdherentServiceTest.60"), ROLE_IDENTIFIANT, garantieBareme.getRole().getIdentifiant());
        assertEquals(Messages.getString("AdherentServiceTest.61"), "Assuré", garantieBareme.getRole().getLibelle());
        assertEquals(Messages.getString("AdherentServiceTest.63"), "garantie1", garantieBareme.getGarantieGestion());
        assertEquals(Messages.getString("AdherentServiceTest.65"), "produit1", garantieBareme.getProduitGestion());
        assertEquals(Messages.getString("AdherentServiceTest.67"), "1A", garantieBareme.getCodeCompositionFamille());
        assertEquals(Messages.getString("AdherentServiceTest.69"), "G614", garantieBareme.getCodeApporteur());
        assertEquals(Messages.getString("AdherentServiceTest.71"), "1", garantieBareme.getCodeEntreprise());
        assertEquals(Messages.getString("AdherentServiceTest.73"), "177", garantieBareme.getCodeGarantie());
        assertEquals(Messages.getString("AdherentServiceTest.75"), "2011", garantieBareme.getCodeGeneration());
        assertEquals(Messages.getString("AdherentServiceTest.77"), "361 NT7", garantieBareme.getCodeGestion());
        assertEquals(Messages.getString("AdherentServiceTest.79"), "01", garantieBareme.getCodeMois());
        assertEquals(Messages.getString("AdherentServiceTest.81"), CODE_NVIGAR, garantieBareme.getCodeNivgar());
        assertEquals(Messages.getString("AdherentServiceTest.82"), "id", garantieBareme.getCodeRole());
        assertEquals(Messages.getString("AdherentServiceTest.84"), VINGT_SIX, garantieBareme.getCodeAgeMin());
        assertEquals(Messages.getString("AdherentServiceTest.85"), VINGT_SIX, garantieBareme.getCodeAgeMinMillesime());

        // on pointe sur un contrat collectif sans le preciser => aucun resultat
        criteres = new GarantieBaremeCriteresDto();
        criteres.setContrat("11/SR/11");
        liste = adherentService.getListeGarantiesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 0, liste.size());

        criteres = new GarantieBaremeCriteresDto();
        criteres.setContrat("11/SR/11");
        criteres.setContratCollectif(true);
        liste = adherentService.getListeGarantiesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 2, liste.size());
        assertEquals(BAREME_PAS_BON, ONZE, liste.get(0).getId());
        assertEquals(BAREME_PAS_BON, QUATORZE, liste.get(1).getId());
    }

    /** Test du service getListeCouplesBaremesAdherent. */
    @Test
    public void testGetListeCouplesBaremesAdherent() {
        try {
            adherentService.getListeCouplesBaremesAdherent(null);
            fail(Messages.getString("AdherentServiceTest.88"));
        }
        catch (TechnicalException e) {
            assertEquals(Messages.getString("AdherentServiceTest.89"), Messages.getString("AdherentServiceTest.147"),
            		e.getMessage());
        }

        GarantieBaremeCriteresDto criteres = new GarantieBaremeCriteresDto();
        List<CoupleBaremeDto> liste = adherentService.getListeCouplesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 4, liste.size());
        assertEquals(BAREME_PAS_BON, "bareme1-null", liste.get(0).getBareme1Eid() + SEP + liste.get(0).getBareme2Eid());
        assertEquals(BAREME_PAS_BON, "bareme2-null", liste.get(1).getBareme1Eid() + SEP + liste.get(1).getBareme2Eid());
        assertEquals(BAREME_PAS_BON, "bareme3-null", liste.get(2).getBareme1Eid() + SEP + liste.get(2).getBareme2Eid());
        assertEquals(BAREME_PAS_BON, "bareme4-bareme1", liste.get(3).getBareme1Eid() + SEP + liste.get(3).getBareme2Eid());

        criteres = new GarantieBaremeCriteresDto();
        criteres.setContrat("001");
        liste = adherentService.getListeCouplesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 3, liste.size());
        assertEquals(BAREME_PAS_BON, "bareme1-null", liste.get(0).getBareme1Eid() + SEP + liste.get(0).getBareme2Eid());
        assertEquals(BAREME_PAS_BON, "bareme2-null", liste.get(1).getBareme1Eid() + SEP + liste.get(1).getBareme2Eid());
        assertEquals(BAREME_PAS_BON, "bareme3-null", liste.get(2).getBareme1Eid() + SEP + liste.get(2).getBareme2Eid());

        criteres = new GarantieBaremeCriteresDto();
        criteres.setContrat("11/SR/11");
        criteres.setContratCollectif(true);
        liste = adherentService.getListeCouplesBaremesAdherent(criteres);
        assertEquals(NOMBRE_BAREMES_PAS_BON, 2, liste.size());
        assertEquals(BAREME_PAS_BON, "bareme1-null", liste.get(0).getBareme1Eid() + SEP + liste.get(0).getBareme2Eid());
        assertEquals(BAREME_PAS_BON, "bareme4-bareme1", liste.get(1).getBareme1Eid() + SEP + liste.get(1).getBareme2Eid());
    }
}
