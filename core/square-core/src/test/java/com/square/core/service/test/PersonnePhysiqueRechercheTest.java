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
/**
 * 
 */
package com.square.core.service.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.model.PersonnePhysique;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Test Temporaire sur les problemes de recherche du moteur full text.
 * @author Goumard Stephane (stephane.goumard@scub.net) - SCUB
 */
public class PersonnePhysiqueRechercheTest extends DbunitBaseTestCase {

    private static final String NOMBRE_PERSONNES_PAS_BON = Messages.getString("PersonnePhysiqueRechercheTest.0");

    /**
     * Le service des personnes physiques.
     */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Service Personne. */
    private PersonneService personneService;

    /** Service SquareMapping. */
    private SquareMappingService squareMappingService;

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {

        personnePhysiqueService = (PersonnePhysiqueService) getBeanSpring("personnePhysiqueService");
        personneService = (PersonneService) getBeanSpring("personneService");
        squareMappingService = (SquareMappingService) getBeanSpring("squareMappingService");
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

    @Override
    protected String getXmlDateSet() {
        return "datasetPersonneRechercheFullText.xml";
    }

    /**
     * Probléme de recherche de prenom avec accent.
     */
    @Test
    public void problemeRechercheSurPrenom() {
        String prenom = "stephan";

        PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        criteres.setPrenom(prenom);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> remote = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, 100);

        RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.9"), 2, results.getTotalResults());

        prenom = "stephane";
        criteres.setPrenom(prenom);
        results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.11"), 1, results.getTotalResults());

        prenom = "stéphane";
        criteres.setPrenom(prenom);
        results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.13"), 1, results.getTotalResults());

        prenom = "stép";
        criteres.setPrenom(prenom);
        results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.15"), 2, results.getTotalResults());

        prenom = "";
        criteres.setPrenom(prenom);
        criteres.setNom("OS");
        results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.18"), 0, results.getTotalResults());

        prenom = "";
        criteres.setPrenom(prenom);
        criteres.setNom("BEN OSMANE");
        results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.21"), 1, results.getTotalResults());

        prenom = "David";
        criteres.setPrenom(prenom);
        criteres.setNom("Zago");
        results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.24"), 1, results.getTotalResults());
        criteres.setPrenom("");
        criteres.setNom("");
        criteres.setNumeroClient("0470417");
        results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.28"), 1, results.getTotalResults());

        criteres = new PersonneCriteresRechercheDto();
        criteres.setRechercheStricte(true);
        criteres.setNom("BEN OSMANE");
        criteres.setPrenom("Patrick");
        final Calendar dateNaissance = Calendar.getInstance();
        dateNaissance.clear();
        dateNaissance.set(2000, Calendar.JANUARY, 1);
        criteres.setDateNaissance(dateNaissance);
        results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.31"), 1, results.getTotalResults());
    }

    /**
     * Test de la recherche full text stricte de personne.
     */
    @Test
    public void testRechercherPersonneStricte() {
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        criteres.setRechercheStricte(true);
        criteres.setNom("ABDOURAMANE DORO");
        criteres.setPrenom("ramatou");
        final Calendar dateNaissance = Calendar.getInstance();
        dateNaissance.clear();
        dateNaissance.set(1978, Calendar.FEBRUARY, 7);
        criteres.setDateNaissance(dateNaissance);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> remote = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, 100);
        final RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.34"), 1, results.getTotalResults());
    }

    /**
     * Test de la recherche full text de personne avec nom composé accentué.
     */
    @Test
    public void testRechercherPersonneNomComposeAccentue() {
        final Long idPersonneAttendue = 5L;
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        criteres.setNom("précigout corré");
        criteres.setPrenom("stéphanie");
        final Calendar dateNaissance = Calendar.getInstance();
        dateNaissance.clear();
        dateNaissance.set(1978, Calendar.FEBRUARY, 7);
        criteres.setDateNaissance(dateNaissance);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> remote = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, 100);
        final RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.37"), 1, results.getTotalResults());
        assertFalse(Messages.getString("PersonnePhysiqueRechercheTest.38"), results.getListResults().isEmpty());
        final PersonneSimpleDto personneTrouvee = results.getListResults().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.39"), idPersonneAttendue, personneTrouvee.getId());
    }

    /**
     * Test de la recherche full text de personne avec prénom "jean michèle" composé accentué indexé avec le trait d'union "jean-michèle".
     */
    @Test
    public void testRechercherPersonnePrennomComposeAccentue() {
        final Long idPersonneAttendue = 6L;
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        criteres.setNom("moré");
        criteres.setPrenom("jean-michèle");
        final Calendar dateNaissance = Calendar.getInstance();
        dateNaissance.clear();
        dateNaissance.set(1978, Calendar.FEBRUARY, 7);
        criteres.setDateNaissance(dateNaissance);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> remote = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, 100);
        final RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.42"), 1, results.getTotalResults());
        assertFalse(Messages.getString("PersonnePhysiqueRechercheTest.43"), results.getListResults().isEmpty());
        final PersonneSimpleDto personneTrouvee = results.getListResults().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.44"), idPersonneAttendue, personneTrouvee.getId());
    }

    /**
     * Test de la recherche full text d'un groupe de personnes par leurs identifiants.
     */
    @Test
    public void testRecherchePersonnesParIds() {
        // Identifiants des personnes recherchées
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;

        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        final List<Long> idPersonnes = new ArrayList<Long>();
        idPersonnes.add(idPersonne1);
        idPersonnes.add(idPersonne2);
        criterias.setIdPersonnes(idPersonnes);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);
        final RemotePagingResultsDto<PersonneSimpleDto> remotePagingResult = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);

        final int nbResultatsAttendus = 2;
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.45"), nbResultatsAttendus, remotePagingResult.getTotalResults());
        final List<PersonneSimpleDto> results = remotePagingResult.getListResults();
        assertEquals(Messages.getString("PersonnePhysiqueRechercheTest.46"), nbResultatsAttendus, results.size());

        boolean personne1Trouvee = false;
        boolean personne2Trouvee = false;
        for (PersonneSimpleDto personne : results) {
            final Long idPersonne = personne.getId();
            if (idPersonne.equals(idPersonne1)) {
                personne1Trouvee = true;
            } else if (idPersonne.equals(idPersonne2)) {
                personne2Trouvee = true;
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueRechercheTest.47"), personne1Trouvee && personne2Trouvee);
    }

    /**
     * Test de la recherche fulltext par tri sur le code postal avec des personnes ayant plusieurs adresses.
     * TODO : NP ce test ne devrait pas passer
     */
    @Test
    public void testRechercheTriCodePostalPlusieursAdresses() {
        // Ajout d'une adresse secondaire
        personneService.ajouterNouvelleAdresse(1L, getAdresseSecondaire(), false);
        // Recherche en triant sur le code postal
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);
        final List<RemotePagingSort> listeTris = new ArrayList<RemotePagingSort>();
        listeTris.add(new RemotePagingSort(squareMappingService.getProprietePersonneCodePostal(), RemotePagingSort.REMOTE_PAGING_SORT_ASC));
        criteres.setListeSorts(listeTris);
        personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
    }

    /**
     * Test de la recherche par code postal après avoir modifié le code postal - commune de l'adresse.
     */
    @Test
    public void testRechercheParCodePostalApresModifCodePostal() {
        final Long idPersonne1 = 1L;
        final Long idCodePostal1 = 1L;
        final Long idCodePostal2 = 2L;
        final Long idCodePostalCommune2 = 2L;

        final List<Long> listeIdsCodesPostaux = new ArrayList<Long>();
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);

        // Recherche sur des personnes avec code postal 1
        listeIdsCodesPostaux.add(idCodePostal1);
        criterias.setListeCodesPostaux(listeIdsCodesPostaux);
        RemotePagingResultsDto<PersonneSimpleDto> resultats = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
        assertEquals(NOMBRE_PERSONNES_PAS_BON, 1, resultats.getTotalResults());

        // Recherche sur des personnes avec code postal 2
        listeIdsCodesPostaux.clear();
        listeIdsCodesPostaux.add(idCodePostal2);
        criterias.setListeCodesPostaux(listeIdsCodesPostaux);
        resultats = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
        assertEquals(NOMBRE_PERSONNES_PAS_BON, 0, resultats.getTotalResults());

        // Modification du code postal de l'adresse
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonne1);
        for (AdresseDto adresse : coordonnees.getAdresses()) {
            if (squareMappingService.getIdNatureAdressePrincipale().equals(adresse.getNature().getIdentifiant())) {
                adresse.setCodePostalCommune(new CodePostalCommuneDto(idCodePostalCommune2));
            }
        }
        personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // Recherche sur des personnes avec code postal 1
        listeIdsCodesPostaux.clear();
        listeIdsCodesPostaux.add(idCodePostal1);
        criterias.setListeCodesPostaux(listeIdsCodesPostaux);
        resultats = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
        assertEquals(NOMBRE_PERSONNES_PAS_BON, 0, resultats.getTotalResults());

        // Recherche sur des personnes avec code postal 2
        listeIdsCodesPostaux.clear();
        listeIdsCodesPostaux.add(idCodePostal2);
        criterias.setListeCodesPostaux(listeIdsCodesPostaux);
        resultats = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
        assertEquals(NOMBRE_PERSONNES_PAS_BON, 1, resultats.getTotalResults());
    }

    /**
     * Test de la recherche par commune après avoir modifié le code postal - commune de l'adresse.
     */
    @Test
    public void testRechercheParCommuneApresModifCodePostal() {
        final Long idPersonne1 = 1L;
        final Long idCommune1 = 1L;
        final Long idCommune2 = 2L;
        final Long idCodePostalCommune2 = 2L;

        final List<Long> listeIdsCommunes = new ArrayList<Long>();
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);

        // Recherche sur des personnes avec commune 1
        listeIdsCommunes.add(idCommune1);
        criterias.setListeVilles(listeIdsCommunes);
        RemotePagingResultsDto<PersonneSimpleDto> resultats = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
        assertEquals(NOMBRE_PERSONNES_PAS_BON, 1, resultats.getTotalResults());

        // Recherche sur des personnes avec commune 2
        listeIdsCommunes.clear();
        listeIdsCommunes.add(idCommune2);
        criterias.setListeVilles(listeIdsCommunes);
        resultats = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
        assertEquals(NOMBRE_PERSONNES_PAS_BON, 0, resultats.getTotalResults());

        // Modification du code postal de l'adresse
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(idPersonne1);
        for (AdresseDto adresse : coordonnees.getAdresses()) {
            if (squareMappingService.getIdNatureAdressePrincipale().equals(adresse.getNature().getIdentifiant())) {
                adresse.setCodePostalCommune(new CodePostalCommuneDto(idCodePostalCommune2));
            }
        }
        personneService.creerOuMettreAJourCoordonnees(coordonnees, false, false);

        // Recherche sur des personnes avec commune 1
        listeIdsCommunes.clear();
        listeIdsCommunes.add(idCommune1);
        criterias.setListeVilles(listeIdsCommunes);
        resultats = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
        assertEquals(NOMBRE_PERSONNES_PAS_BON, 0, resultats.getTotalResults());

        // Recherche sur des personnes avec commune 2
        listeIdsCommunes.clear();
        listeIdsCommunes.add(idCommune2);
        criterias.setListeVilles(listeIdsCommunes);
        resultats = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
        assertEquals(NOMBRE_PERSONNES_PAS_BON, 1, resultats.getTotalResults());
    }

    /**
     * Construit une adresse secondaire.
     * @return l'adresse secondaire
     */
    private AdresseDto getAdresseSecondaire() {
        final AdresseDto adresseDto = new AdresseDto();
        final Calendar dateDebut = Calendar.getInstance();
        final int year = 2010;
        final int month = 10;
        final int date = 1;
        dateDebut.set(year, month, date);
        adresseDto.setDateDebut(Calendar.getInstance());
        adresseDto.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdresseSecondaire()));
        adresseDto.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseDto.setDepartement(new IdentifiantLibelleDto(1L));
        adresseDto.setNumVoie("17");
        adresseDto.setTypeVoie(new IdentifiantLibelleDto(1L));
        adresseDto.setNomVoie("des Halles");
        adresseDto.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance()));
        return adresseDto;
    }
}
