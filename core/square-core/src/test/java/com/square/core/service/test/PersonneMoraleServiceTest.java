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

import com.square.core.dao.interfaces.AgenceDao;
import com.square.core.dao.interfaces.FormeJuridiqueDao;
import com.square.core.dao.interfaces.PersonneMoraleDao;
import com.square.core.dao.interfaces.PersonneMoraleNatureDao;
import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.model.FormeJuridique;
import com.square.core.model.PersonneAttribution;
import com.square.core.model.PersonneMorale;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.PersonneMoralCriteresRechercheDto;
import com.square.core.model.dto.PersonneMoraleDto;
import com.square.core.model.dto.PersonneMoraleRechercheDto;
import com.square.core.model.dto.PersonneMoraleSimpleDto;
import com.square.core.model.dto.SousRapportDto;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.service.interfaces.PersonneMoraleService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.PersonneMoraleKeyUtil;
import com.square.core.util.PersonnePhysiqueKeyUtil;
import com.square.core.util.RessourceHabilitationUtil;

/**
 * Classe de test des services des personnes morales.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneMoraleServiceTest extends DbunitBaseTestCase {

    /**
     * Identifiant de la PM déjà présent dans le dataset.
     */
    private static final long ID_PM_DATASET = 3L;

    /**
     * Gestion des messages.
     */
    private MessageSourceUtil messageSourceUtil;

    /** Service des personnes morales. */
    PersonneMoraleService personneMoraleService;

    /** Service des personne. */
    PersonneService personneService;

    /** Service de Mapping. */
    private SquareMappingService squareMappingService;

    /**
     * Max Count Lucene Value.
     */
    private static final int MAX_LUCENE_VALUE = 100;

    /** Message d'erreur lorsque le nombre de sociétés n'est pas 1. */
    private static String nbTotalDifferentDeUn = Messages.getString("PersonneMoraleServiceTest.0");

    /** Message d'erreur lorsque l'identifiant est différent. */
    private static String idDifferent = Messages.getString("PersonneMoraleServiceTest.1");

    /**
     * Méthode appelée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        personneMoraleService = (PersonneMoraleService) getBeanSpring("personneMoraleService");
        personneService = (PersonneService) getBeanSpring("personneService");
        squareMappingService = (SquareMappingService) getBeanSpring("squareMappingService");
        createSecureContext("user", "user");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"squareMappingContext.xml"};
    }
    @Override
    protected Class[] getClassManualIndexChanges() {
        return new Class[] {PersonneMorale.class};
    }
    /**
     * Test unitaire de la recherche d'une personne morale par son id.
     */
    @Test
    public void testRecherchePersonneMoraleParId() {
        // On récupère la personne
        final PersonneMoraleDto personneMorale = personneMoraleService.recherchePersonneMoraleParId(ID_PM_DATASET);
        assertNotNull(Messages.getString("PersonneMoraleServiceTest.2"), personneMorale);
        assertEquals(Messages.getString("PersonneMoraleServiceTest.10"), "raisonSociale", personneMorale.getRaisonSociale());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.12"), "3333", personneMorale.getNumEntreprise());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.14"), "enseigne", personneMorale.getEnseigneCommercial());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.16"), "Adhérent", personneMorale.getNature().getLibelle());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.18"), "Nom 3", personneMorale.getCommercial().getNom());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.20"), "Agence 2", personneMorale.getAgence().getLibelle());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.22"), "Nom 1", personneMorale.getCreateur().getNom());
    }

    /**
     * Test unitaire de la recherche d'une personne morale simple par son id.
     */
    @Test
    public void testRecherchePersonneSimpleParId() {
        // On teste pour la personne simmple nulle
        try {
            personneMoraleService.recherchepersonneMoraleSimpleParId(null);
            fail(Messages.getString("PersonneMoraleServiceTest.24"));
        }
        catch (BusinessException b) {
            assertEquals(Messages.getString("PersonneMoraleServiceTest.25"), messageSourceUtil
                    .get(PersonneMoraleKeyUtil.MESSAGE_ERREUR_PERSONNE_MORALE_NULL), b.getMessage());
        }
        // On récupère la personne simple.
        final PersonneMoraleSimpleDto personneSimple = personneMoraleService.recherchepersonneMoraleSimpleParId(ID_PM_DATASET);

        assertEquals(Messages.getString("PersonneMoraleServiceTest.26"), "3333", personneSimple.getNum());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.28"), "raisonSociale", personneSimple.getRaisonSociale());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.30"), "3333", personneSimple.getNumeroEntreprise());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.32"), "Adhérent", personneSimple.getNature().getLibelle());

    }

    /**
     * Test du recherche full text filtre.
     */
    @Test
    public void testRechercherPersonneMoraleFullText() {
        final PersonneMoraleDao personneMoraleDao = (PersonneMoraleDao) getBeanSpring("personneMoraleDao");
        final FormeJuridiqueDao formeJuridiqueDao = (FormeJuridiqueDao) getBeanSpring("formeJuridiqueDao");
        final RessourceHabilitationUtil ressourceHabilitationUtil = (RessourceHabilitationUtil) getBeanSpring("ressourceHabilitationUtil");
        final RessourceDao ressourceDao = (RessourceDao) getBeanSpring("ressourceDao");
        final AgenceDao agenceDao = (AgenceDao) getBeanSpring("agenceDao");
        final PersonneMoraleNatureDao personneMoraleNatureDao = (PersonneMoraleNatureDao) getBeanSpring("personneMoraleNatureDao");
        final Long idNaturePersonneMoraleAdherent = 1L;
        final Long idNaturePersonneMoraleProspect = 2L;

        // Récupération de la 1ere PM du dataset
        final PersonneMorale personne1 = personneMoraleDao.rechercherPersonneMoraleParId(ID_PM_DATASET);

        // Ajout d'une 2eme PM
        final PersonneMorale personne2 = new PersonneMorale();
        personne2.setRaisonSociale("POUSSIN BLEU");
        personne2.setNumSiret("0012345678910");
        personne2.setDateCreation(Calendar.getInstance());
        final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
        criteres.setId(1L);
        FormeJuridique formeJuridique = formeJuridiqueDao.rechercherFormeJuridiqueParCriteres(criteres).get(0);
        personne2.setFormeJuridique(formeJuridique);
        personne2.setNaturePersonneMorale(personneMoraleNatureDao.rechercherNaturePersonneMoraleParId(idNaturePersonneMoraleProspect));

        // La ressource connectée.
        final Ressource ressourceConnecte = ressourceHabilitationUtil.getUtilisateurConnecte();
        personne2.setRessource(ressourceConnecte);

        // LES ATTRIBUTIONS DE LA PERSONNE
        PersonneAttribution attribution = new PersonneAttribution();
        attribution.setAgence(agenceDao.rechercheAgenceParId(1L));
        attribution.setRessource(ressourceDao.rechercherRessourceParId(1L));
        personne2.setAttribution(attribution);

        personneMoraleDao.creerPersonneMorale(personne2);

        // Création d'une nouvelle adresse, création d'un nouveau téléphone, modification de l'email
        final CoordonneesDto coordonneesDto = new CoordonneesDto();
        coordonneesDto.setIdPersonne(personne2.getId());
        final List<AdresseDto> listAdresseDto = new ArrayList<AdresseDto>();
        final AdresseDto adr1 = new AdresseDto();
        adr1.setNumVoie("1");
        adr1.setNomVoie("nom1 voie test");
        adr1.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adr1.setAutresComplements("autres compléments");
        adr1.setComplementNom("Complement nom");
        adr1.setNpai(false);
        adr1.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance()));
        adr1.setNature(new IdentifiantLibelleDto(1L));
        adr1.setDateDebut(Calendar.getInstance());
        adr1.setTypeVoie(new IdentifiantLibelleDto(1L));
        adr1.setDepartement(new IdentifiantLibelleDto(1L));
        listAdresseDto.add(adr1);
        coordonneesDto.setAdresses(listAdresseDto);
        final EmailDto emailDto = new EmailDto();
        emailDto.setAdresse("email@test1.fr");
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        final List<EmailDto> emailDtos = new ArrayList<EmailDto>();
        emailDtos.add(emailDto);
        coordonneesDto.setEmails(emailDtos);

        personneService.creerOuMettreAJourCoordonnees(coordonneesDto, true, false);

        // Ajout d'une 3eme PM
        final PersonneMorale personne3 = new PersonneMorale();
        personne3.setRaisonSociale("supér warrior");
        personne3.setEnseigneCommercial("enseigne commerciale");
        personne3.setNumSiret("8812345678910");
        personne3.setDateCreation(Calendar.getInstance());
        criteres.setId(2L);
        formeJuridique = formeJuridiqueDao.rechercherFormeJuridiqueParCriteres(criteres).get(0);
        personne3.setFormeJuridique(formeJuridique);
        personne3.setNaturePersonneMorale(personneMoraleNatureDao.rechercherNaturePersonneMoraleParId(idNaturePersonneMoraleAdherent));

        personne3.setRessource(ressourceConnecte);

        // LES ATTRIBUTIONS DE LA PERSONNE
        attribution = new PersonneAttribution();
        attribution.setRessource(ressourceDao.rechercherRessourceParId(1L));
        attribution.setAgence(agenceDao.rechercheAgenceParId(1L));
        personne3.setAttribution(attribution);

        personneMoraleDao.creerPersonneMorale(personne3);
        adr1.setDepartement(new IdentifiantLibelleDto(2L));
        adr1.setCodePostalCommune(new CodePostalCommuneDto(2L));
        adr1.setComplementNom("FAUBERT");
        coordonneesDto.setIdPersonne(personne3.getId());

        personneService.creerOuMettreAJourCoordonnees(coordonneesDto, true, false);

        // RECHERCHE PAR NUMERO SIRET
        PersonneMoralCriteresRechercheDto criteresFullText = new PersonneMoralCriteresRechercheDto();
        criteresFullText.setNumeroEntreprise("0012345678*");
        RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto> pagination =
            new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneMoraleRechercheDto> result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, result.getTotalResults(), 1);
        assertEquals(nbTotalDifferentDeUn, result.getListResults().size(), 1);
        PersonneMoraleRechercheDto personneDto = result.getListResults().get(0);
        assertEquals(idDifferent, personne2.getId(), personneDto.getId());
        // TEST DU MAPPING ET LE RETOUR DE L'INDEX
        assertEquals(Messages.getString("PersonneMoraleServiceTest.52"), personne2.getRaisonSociale(), personneDto.getRaisonSociale());
        // assertEquals("Numero Siret devrait être le même", personne.getNumSiret(), personneDto.getNumeroEntreprise());
        final CodePostalCommuneDto codePostalCommunePers2 = personneDto.getCodePostalCommune();
        assertEquals(Messages.getString("PersonneMoraleServiceTest.53"), 1L, codePostalCommunePers2.getIdentifiant());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.54"), "Commune1 ach", codePostalCommunePers2.getLibelleAcheminement());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.56"), 1L, codePostalCommunePers2.getCodePostal().getIdentifiant());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.57"), "000", codePostalCommunePers2.getCodePostal().getLibelle());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.59"), 1L, codePostalCommunePers2.getCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.60"), "Commune1", codePostalCommunePers2.getCommune().getLibelle());

        // RECHERCHE PAR RAISON SOCIALE
        criteresFullText = new PersonneMoralCriteresRechercheDto();
        criteresFullText.setRaisonSociale("sup?r*");
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, result.getTotalResults(), 1);
        assertEquals(nbTotalDifferentDeUn, result.getListResults().size(), 1);
        personneDto = result.getListResults().get(0);
        assertEquals(idDifferent, personne3.getId(), personneDto.getId());

        // RECHERCHE PAR ENSEIGNE COMMERCIALE
        criteresFullText = new PersonneMoralCriteresRechercheDto();
        criteresFullText.setEnseigneCommerciale("enseigne commerciale");
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, 1, result.getTotalResults());
        assertEquals(nbTotalDifferentDeUn, 1, result.getListResults().size());
        personneDto = result.getListResults().get(0);
        assertEquals(idDifferent, personne3.getId(), personneDto.getId());

        // RECHERCHE PAR COMPLEMENT NOM
        criteresFullText = new PersonneMoralCriteresRechercheDto();
        criteresFullText.setComplementNom("FAUBERT");
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, 1, result.getTotalResults());
        assertEquals(nbTotalDifferentDeUn, 1, result.getListResults().size());
        personneDto = result.getListResults().get(0);
        assertEquals(idDifferent, personne3.getId(), personneDto.getId());

        // RECHERCHE PAR FORME JURIDIQUE
        criteresFullText = new PersonneMoralCriteresRechercheDto();
        final List<Long> listeFormesJuridiques = new ArrayList<Long>();
        listeFormesJuridiques.add(2L);
        criteresFullText.setListeFormesJuridiques(listeFormesJuridiques);
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, result.getTotalResults(), 1);
        assertEquals(nbTotalDifferentDeUn, result.getListResults().size(), 1);
        personneDto = result.getListResults().get(0);
        assertEquals(idDifferent, personne3.getId(), personneDto.getId());

        // RECHERCHE PAR VILLE
        criteresFullText = new PersonneMoralCriteresRechercheDto();
        final List<Long> listeVilles = new ArrayList<Long>();
        listeVilles.add(1L);
        criteresFullText.setListeVilles(listeVilles);
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, 1, result.getTotalResults());
        assertEquals(nbTotalDifferentDeUn, 1, result.getListResults().size());
        personneDto = result.getListResults().get(0);
        assertEquals(idDifferent, personne2.getId(), personneDto.getId());

        // RECHERCHE PAR DEPARTEMENT
        criteresFullText = new PersonneMoralCriteresRechercheDto();
        final List<Long> listeDepartements = new ArrayList<Long>();
        listeDepartements.add(2L);
        criteresFullText.setListeDepartements(listeDepartements);
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, result.getTotalResults(), 1);
        assertEquals(nbTotalDifferentDeUn, result.getListResults().size(), 1);
        personneDto = result.getListResults().get(0);
        assertEquals(idDifferent, personne3.getId(), personneDto.getId());

        // RECHERCHE PAR COMMERCIAL
        criteresFullText = new PersonneMoralCriteresRechercheDto();
        final List<Long> listeCommerciaux = new ArrayList<Long>();
        listeCommerciaux.add(1L);
        criteresFullText.setListeCommerciaux(listeCommerciaux);

        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(Messages.getString("PersonneMoraleServiceTest.65"), result.getTotalResults(), 3);
        assertEquals(Messages.getString("PersonneMoraleServiceTest.66"), result.getListResults().size(), 3);

        // RECHERCHE PAR L'AGENCE
        criteresFullText = new PersonneMoralCriteresRechercheDto();
        final List<Long> listeAgences = new ArrayList<Long>();
        listeAgences.add(1L);
        listeAgences.add(2L);
        criteresFullText.setListeAgences(listeAgences);

        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(Messages.getString("PersonneMoraleServiceTest.67"), result.getTotalResults(), 3);
        assertEquals(Messages.getString("PersonneMoraleServiceTest.68"), result.getListResults().size(), 3);

        // TEST DE LA PAGINATION
        // Ajout d'une 4eme PM
        final PersonneMorale personne4 = new PersonneMorale();
        personne4.setRaisonSociale("supér warrior");
        personne4.setNumSiret("9912345678910");
        personne4.setDateCreation(Calendar.getInstance());
        criteres.setId(2L);
        formeJuridique = formeJuridiqueDao.rechercherFormeJuridiqueParCriteres(criteres).get(0);
        personne4.setFormeJuridique(formeJuridique);
        personne4.setNaturePersonneMorale(personneMoraleNatureDao.rechercherNaturePersonneMoraleParId(idNaturePersonneMoraleProspect));

        // Créateur de la personne
        personne4.setRessource(ressourceConnecte);

        // LES ATTRIBUTIONS DE LA PERSONNE
        attribution = new PersonneAttribution();
        attribution.setRessource(ressourceDao.rechercherRessourceParId(1L));
        attribution.setAgence(agenceDao.rechercheAgenceParId(1L));
        personne4.setAttribution(attribution);

        personneMoraleDao.creerPersonneMorale(personne4);

        criteresFullText = new PersonneMoralCriteresRechercheDto();
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, result.getTotalResults(), 4);
        assertEquals(nbTotalDifferentDeUn, result.getListResults().size(), 4);
        criteresFullText = new PersonneMoralCriteresRechercheDto();
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, 1);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, result.getTotalResults(), 4);
        assertEquals(nbTotalDifferentDeUn, result.getListResults().size(), 1);

        // TEST DU TRI SUR NUMERO SIRET
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        RemotePagingSort sort = new RemotePagingSort("triNumSiret", RemotePagingSort.REMOTE_PAGING_SORT_DESC);
        List<RemotePagingSort> sorts = new ArrayList<RemotePagingSort>();
        sorts.add(sort);
        pagination.setListeSorts(sorts);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, result.getTotalResults(), 4);
        assertEquals(nbTotalDifferentDeUn, result.getListResults().size(), 4);

        // TEST DU TRI SUR NATURE
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        sorts = new ArrayList<RemotePagingSort>();
        sort = new RemotePagingSort("naturePersonneMorale.libelle", RemotePagingSort.REMOTE_PAGING_SORT_DESC);
        sorts.add(sort);
        sort = new RemotePagingSort("id", RemotePagingSort.REMOTE_PAGING_SORT_ASC);
        sorts.add(sort);
        pagination.setListeSorts(sorts);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, result.getTotalResults(), 4);
        assertEquals(nbTotalDifferentDeUn, result.getListResults().size(), 4);

        assertEquals(Messages.getString("PersonneMoraleServiceTest.74"),
            personne4.getId(), result.getListResults().get(0).getId());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.75"),
            personne2.getId(), result.getListResults().get(1).getId());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.76"),
            personne1.getId(), result.getListResults().get(2).getId());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.77"),
            personne3.getId(), result.getListResults().get(3).getId());

        // TEST RECHRERCHE MULTI-CRITERE
        // Ajout d'une 5eme PM
        final PersonneMorale personne5 = new PersonneMorale();
        personne5.setRaisonSociale("supér warrior");
        personne5.setNumSiret("666123456123456789");
        personne5.setNum("666987654321");
        personne5.setDateCreation(Calendar.getInstance());
        criteres.setId(2L);
        formeJuridique = formeJuridiqueDao.rechercherFormeJuridiqueParCriteres(criteres).get(0);
        personne5.setFormeJuridique(formeJuridique);

        personne5.setRessource(ressourceConnecte);

        // LES ATTRIBUTIONS DE LA PERSONNE
        attribution = new PersonneAttribution();
        attribution.setRessource(ressourceDao.rechercherRessourceParId(1L));
        attribution.setAgence(agenceDao.rechercheAgenceParId(1L));
        personne5.setAttribution(attribution);

        personneMoraleDao.creerPersonneMorale(personne5);

        criteresFullText = new PersonneMoralCriteresRechercheDto();
        criteresFullText.setNumeroEntreprise("66612345*");
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, result.getTotalResults(), 1);
        assertEquals(nbTotalDifferentDeUn, result.getListResults().size(), 1);
        personneDto = result.getListResults().get(0);
        assertEquals(idDifferent, personne5.getId(), personneDto.getId());

        criteresFullText = new PersonneMoralCriteresRechercheDto();
        criteresFullText.setNumeroEntreprise("6669876*");
        pagination = new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criteresFullText, 0, MAX_LUCENE_VALUE);
        result = personneMoraleService.rechercherPersonneMoraleParCriteres(pagination);

        assertEquals(nbTotalDifferentDeUn, result.getTotalResults(), 1);
        assertEquals(nbTotalDifferentDeUn, result.getListResults().size(), 1);
        personneDto = result.getListResults().get(0);
        assertEquals(idDifferent, personne5.getId(), personneDto.getId());
    }

    /** Test du service creerPersonneMorale. */
    @Test
    public void testCreerPersonneMorale() {
        PersonneMoraleDto personneDto = null;
        AdresseDto adresseDto = null;
        try {
            personneMoraleService.creerPersonneMorale(personneDto, adresseDto);
            fail(Messages.getString("PersonneMoraleServiceTest.83"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("PersonneMoraleServiceTest.84"), messageSourceUtil.get(PersonneMoraleKeyUtil.MESSAGE_ERREUR_PERSONNE_MORALE_NULL), e.getMessage());
        }

        personneDto = new PersonneMoraleDto();
        personneDto.setRaisonSociale("");
        final IdentifiantLibelleDto naturePersonneMoraleProspect = new IdentifiantLibelleDto(squareMappingService.getIdNaturePersonneMoraleProspect());
        personneDto.setNature(naturePersonneMoraleProspect);
        final DimensionRessourceDto createur = new DimensionRessourceDto();
        createur.setIdentifiant(1L);
        personneDto.setCreateur(createur);
        try {
            personneMoraleService.creerPersonneMorale(personneDto, adresseDto);
            fail(Messages.getString("PersonneMoraleServiceTest.86"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("PersonneMoraleServiceTest.87"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_DTO_NULL), e.getMessage());
        }

        adresseDto = new AdresseDto();
        adresseDto.setNumVoie("test");
        adresseDto.setNomVoie(Messages.getString("PersonneMoraleServiceTest.89"));
        final CodePostalCommuneDto codePostalCommune = new CodePostalCommuneDto(1L);
        final IdentifiantLibelleDto codePostal = new IdentifiantLibelleDto(1L);
        codePostalCommune.setCodePostal(codePostal);
        adresseDto.setCodePostalCommune(codePostalCommune);
        final IdentifiantLibelleBooleanDto pays = new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance());
        adresseDto.setPays(pays);
        adresseDto.setAutresComplements("Autre complément");
        adresseDto.setComplementNom("Complément nom");
        try {
            personneMoraleService.creerPersonneMorale(personneDto, adresseDto);
            fail(Messages.getString("PersonneMoraleServiceTest.92"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonneMoraleServiceTest.93"), 5, e.getRapport().getRapports().size());
            boolean erreurRaisonSociale = false;
            boolean erreurNumVoie = false;
            boolean erreurNomVoie = false;
            for (String key : e.getRapport().getRapports().keySet()) {
                final SousRapportDto sousRapport = e.getRapport().getRapports().get(key);
                if (sousRapport.getAttribut().equals("PersonneMoraleDto.raisonSociale") && sousRapport.getErreur()) {
                    erreurRaisonSociale = true;
                }
                else if (sousRapport.getAttribut().equals("AdresseDto.numVoie") && sousRapport.getErreur()) {
                    erreurNumVoie = true;
                }
                else if (sousRapport.getAttribut().equals("AdresseDto.nomVoie") && sousRapport.getErreur()) {
                    erreurNomVoie = true;
                }
                else if (sousRapport.getErreur()) {
                    fail("Erreur imprévue");
                }
            }
            assertTrue(Messages.getString("PersonneMoraleServiceTest.98"), erreurRaisonSociale && erreurNumVoie && erreurNomVoie);
        }

        personneDto.setRaisonSociale("Scub");
        final String numVoie = "147";
        adresseDto.setNumVoie(numVoie);
        final IdentifiantLibelleDto natureVoie = new IdentifiantLibelleDto(1L);
        adresseDto.setNature(natureVoie);
        adresseDto.setNomVoie("de Limoges");
        final PersonneMoraleDto personneCreee = personneMoraleService.creerPersonneMorale(personneDto, adresseDto);
        // Vérification de la personne morale créée
        assertNotNull(Messages.getString("PersonneMoraleServiceTest.3"), personneCreee.getId());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.103"), "scub", personneCreee.getRaisonSociale());
        assertNotNull(Messages.getString("PersonneMoraleServiceTest.105"), personneCreee.getDateCreation());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.106"), naturePersonneMoraleProspect.getIdentifiant(),
            personneCreee.getNature().getIdentifiant());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.107"), createur.getIdentifiant(),
            personneCreee.getCreateur().getIdentifiant());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.108"), 2L, personneCreee.getAgence().getIdentifiant());

        // Vérification de l'adresse de la personne morale créée
        final CoordonneesDto coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(personneCreee.getId());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.109"), 1, coordonneesDto.getAdresses().size());
        final AdresseDto adresseCreee = coordonneesDto.getAdresses().get(0);
        assertEquals(Messages.getString("PersonneMoraleServiceTest.110"), numVoie, adresseCreee.getNumVoie());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.111"), natureVoie.getIdentifiant(), adresseCreee.getNature().getIdentifiant());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.112"), "de limoges", adresseCreee.getNomVoie());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.114"), 1L, adresseCreee.getCodePostalCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.115"), "Commune1 ach",
            adresseCreee.getCodePostalCommune().getLibelleAcheminement());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.117"), pays.getIdentifiant(), adresseCreee.getPays().getIdentifiant());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.118"), "autre complément", adresseCreee.getAutresComplements());
        assertEquals(Messages.getString("PersonneMoraleServiceTest.120"), "complément nom", adresseCreee.getComplementNom()); //$NON-NLS-2$
    }

}
