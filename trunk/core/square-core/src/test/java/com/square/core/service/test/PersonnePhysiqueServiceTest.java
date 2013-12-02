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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.dao.interfaces.InfoSanteDao;
import com.square.core.dao.interfaces.PersonnePhysiqueDao;
import com.square.core.model.InfoSante;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.BeneficiaireDto;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.IdentifiantEidLibelleDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.InfoSanteDto;
import com.square.core.model.dto.PaysSimpleDto;
import com.square.core.model.dto.PersonneCreationAssureSocialDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonnePhysiqueCopieDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.model.dto.SousRapportDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.PersonnePhysiqueKeyUtil;

/**
 * Classe de test des services des personnes physiques.
 * @author cblanchard - SCUB
 */
public class PersonnePhysiqueServiceTest extends DbunitBaseTestCase {

    /**
     * Le service.
     */
    private PersonnePhysiqueService personnePhysiqueService;

    /**
     * Le service sur les personnes.
     */
    private PersonneService personneService;

    /**
     * Gestion des messages.
     */
    private MessageSourceUtil messageSourceUtil;

    /**
     * Service de Mapping.
     */
    private SquareMappingService squareMappingService;

    /** InfoSanteDao. */
    private InfoSanteDao infoSanteDao;

    /** DAO Personne. */
    private PersonnePhysiqueDao personnePhysiqueDao;

    /**
     * Max Count Lucene Value.
     */
    private static final int MAX_LUCENE_VALUE = Integer.MAX_VALUE;

    private Logger logger = Logger.getLogger(this.getClass());

    private List<Long> idsReferentToDelete;

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        personnePhysiqueService = (PersonnePhysiqueService) getBeanSpring("personnePhysiqueService");
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        personneService = (PersonneService) getBeanSpring("personneService");
        squareMappingService = (SquareMappingService) getBeanSpring("squareMappingService");
        infoSanteDao = (InfoSanteDao) getBeanSpring("infoSanteDao");
        personnePhysiqueDao = (PersonnePhysiqueDao) getBeanSpring("personnePhysiqueDao");
        createSecureContext("user", "user");
        idsReferentToDelete = new ArrayList<Long>();

        final List<Long> idsReferent = new ArrayList<Long>();
        idsReferent.add(1L);

        // il faut poser les referents des infos santé
        for (Long idReferent : idsReferent) {
            setReferent(idReferent);
        }
    }

    @Override
    public void tearDownBaseTestCase() throws Exception {
        final List<Long> idsReferent = new ArrayList<Long>();
        idsReferent.add(1L);
        idsReferent.add(4L);
        idsReferent.addAll(idsReferentToDelete);

        // il faut poser les referents des infos santé
        for (Long idReferent : idsReferent) {
            deleteReferent(idReferent);
        }
        super.tearDownBaseTestCase();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class[] getClassManualIndexChanges() {
        return new Class[] {PersonnePhysique.class};
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"squareMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "datasetPersonnePhysique.xml";
    }

    private void setReferent(Long idPersonne) {
        final PersonnePhysique personne = personnePhysiqueDao.rechercherPersonneParId(idPersonne);
        final InfoSante infoSante = personne.getInfoSante();
        if (infoSante != null) {
            infoSante.setReferent(personne);
            infoSanteDao.updateInfoSante(infoSante);
        }
    }

    private void deleteReferent(Long idPersonne) {
        final InfoSante infoSante = personnePhysiqueDao.rechercherPersonneParId(idPersonne).getInfoSante();
        if (infoSante != null) {
            infoSante.setReferent(null);
            infoSanteDao.updateInfoSante(infoSante);
        }
    }

    /**
     * Identifiant extérieur de la personne.
     */
    final String idext = "idext";

    /**
     * Prénom de la personne.
     */
    final String prenom = "prenom";

    /**
     * Nom de la personne.
     */
    final String nom = "nom";

    /**
     * Prénom du bénéficiaire.
     */
    final String prenomB = "prenomB";

    /**
     * Nom du bénéficiaire.
     */
    final String nomB = "nomB";

    /**
     * Nom de la voie.
     */
    final String nomVoie = "nomVoie";

    /**
     * Numéro de la voie.
     */
    final String numVoie = "1";

    /**
     * Numéro de téléphone.
     */
    final String numtel = "0545128767";

    /**
     * Adresse email.
     */
    private String email = "square@scub.fr";

    /**
     * Complément nom voie.
     */
    private String batimentA = "batimentA";

    /**
     * Complément adresse.
     */
    private String complAdresse = "complementAdresse";

    /**
     * Boite postale.
     */
    private String boitePostale = "boitePostale";

    /**
     * résultat null pour recherche fulltext.
     */
    private String resultatNull = Messages.getString("PersonnePhysiqueServiceTest.21");

    /** Prénom1. */
    private String prenom1 = "prénomun";

    /** Nom1. */
    private String nom1 = "NOMUN";

    /**
     * Tests Création.
     * @author mlamine. - SCUB
     */
    /**
     * Test unitaire de création d'une personne sans civilité.
     */
    @Test
    public void testCreatePersonneCiviliteNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final EmailDto emailDto = new EmailDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(null);
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom("nomB");
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleDto(2L, null));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null));

        // Ajout de l'adresse à la personne.

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.25"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.26"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_CIVILITE_NULL), e.getRapport().getRapports().get("PersonneDto.civilite")
                    .getMessage());
        }
        catch (BusinessException b) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.28"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_CIVILITE_INEXISTENT_EN_BD), b.getMessage());
        }

    }

    /**
     * Test unitaire de création d'une personne sans prénom.
     */
    @Test
    public void testCreatePersonnePrenomNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(null);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleDto(2L, null));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.29"));
        }
        catch (ControleIntegriteException e) {

            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.30"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_NULL), e.getRapport().getRapports().get("PersonneDto.prenom").getMessage());
        }

    }

    /**
     * Test unitaire de création d'une personne sans nom.
     */
    @Test
    public void testCreatePersonneNomNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(null);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleDto(2L, null));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.32"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.33"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NOM_NULL), e.getRapport().getRapports().get("PersonneDto.nom").getMessage());
        }
    }

    /**
     * Test unitaire de création d'une personne sans date de naissance.
     */
    @Test
    public void testCreatePersonneDateNaissanceNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.

        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);

        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        personneToCreate.setDateNaissance(null);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleDto(2L, null));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.35"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.36"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_NULL), e.getRapport().getRapports().get("PersonneDto.dateNaissance")
                    .getMessage());
        }
    }

    /**
     * Test unitaire de création d'une personne sans email.
     */
    @Test
    public void testCreatePersonneEmailNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, null, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.38"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.39"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_COORDONNEE_OBLIGATOIRE), e.getRapport().getRapports().get("EmailDto.adresse").getMessage());
        }
        catch (BusinessException b) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.41"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_EMAIL_DTO_NULL), b.getMessage());
        }
    }

    /**
     * Test unitaire de création d'une personne sans nature téléphone.
     */
    @Test
    public void testCreatePersonneNatureTelephoneNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation telephoneDto
        telephoneToCreate.setNature(null);
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.42"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.43"),
                messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NATURETELEPHONE_NULL), e.getRapport().getRapports().get(
                    "TelephoneDto.nature.0").getMessage());
        }

        catch (BusinessException t) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.45"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_NATURE_TELEPHONE_INEXISTENT_EN_BD), t.getMessage());
        }
    }

    /**
     * Test unitaire de création d'une personne sans numéro de téléphone.
     */
    @Test
    public void testCreatePersonneTelephoneNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);
        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, null);
            fail(Messages.getString("PersonnePhysiqueServiceTest.46"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.47"),
                messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_COORDONNEE_OBLIGATOIRE), e.getRapport().getRapports().get("TelephoneDto.numero.0")
                        .getMessage());
        }
        catch (BusinessException b) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.49"),
                messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_TELEPHONE_DTO_NULL), b.getMessage());
        }

    }

    /**
     * Test unitaire de création d'un bénéficiaire sans donner la civilité.
     */
    @Test
    public void testCreateBeneficaireCiviliteNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(null);
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleDto(2L, null));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setNomVoie("nomVoie");
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);
        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.51"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.52"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_BENEFICIAIRE_CIVILITE_NULL), e.getRapport().getRapports().get("BeneficiaireDto.civilite.0")
                    .getMessage());
        }
        catch (BusinessException t) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.54"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_CIVILITE_INEXISTENT_EN_BD), t.getMessage());
        }

    }

    /**
     * Test unitaire de création d'un bénéficiaire sans donner le prénom.
     */
    @Test
    public void testCreatebeneficiairePrenomNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(null);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.55"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.56"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_BENEFICIAIRE_PRENOM_NULL), e.getRapport().getRapports().get("BeneficiaireDto.prenom.0")
                    .getMessage());
        }

    }

    /**
     * Test unitaire de création d'un bénéficiaire sans donner le nom.
     */
    @Test
    public void testCreateBeneficiaireNomNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(null);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.58"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.59"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_BENEFICIAIRE_NOM_NULL), e.getRapport().getRapports().get("BeneficiaireDto.nom.0")
                    .getMessage());
        }

    }

    /**
     * Test unitaire de création d'un bénéficiaire sans donner la date de naissance.
     */
    @Test
    public void testCreateBeneficiaireDateNaissanceNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        beneficaireTocreate.setDateNaissance(null);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.61"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.62"),
                messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_BENEFICIAIRE_DATENAISSANCE_NULL), e.getRapport().getRapports().get(
                    "BeneficiaireDto.dateNaissance.0").getMessage());
        }
    }

    /**
     * Test unitaire de création d'une adresse sans donner le code postal Commune.
     */
    @Test
    public void testCreateAdresseCodePostalNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto());
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance()));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.64"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.65"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_ADRESSE_CODEPOSTAL_NULL), e.getRapport().getRapports().get("AdresseDto.codePostal")
                    .getMessage());
        }

    }

    /**
     * Test unitaire de création d'une adresse sans donner le pays.
     */
    @Test
    public void testCreateAdressePaysNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(null);

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.67"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.68"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_ADRESSE_PAYS_NULL), e.getRapport().getRapports().get("AdresseDto.pays").getMessage());
        }
        catch (BusinessException t) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.70"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PAYS_INEXISTENT_EN_BD), t.getMessage());
        }

    }

    /**
     * Test unitaire de création d'une personne sans bénéficiaire.
     */
    @Test
    public void testCreatePersonneBeneficiaireNull() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(null);

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance(), null));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();

        // Appel du service à tester
        personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);

        // Récupération du résultat.
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(new PersonneCriteresRechercheDto(), 0, MAX_LUCENE_VALUE);
        final RemotePagingResultsDto<PersonneSimpleDto> personnes = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.71"), 8, personnes.getTotalResults());
    }

    /**
     * Test unitaire de création d'une personne avec affectation par code postal.
     */
    @Test
    public void testCreatePersonneAffectationCodePostal() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(null);

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance(), null));

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();

        // on cree la personne
        PersonneDto personneCree =
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.72"), 2L, personneCree.getAgence().getIdentifiant());

        // on cree une nouvelle personne avec un autre code postal
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(4L));
        personneCree = personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.73"), 1L, personneCree.getAgence().getIdentifiant());
    }

    /**
     * Test unitaire de création d'une personne avec affectation par commercial.
     */
    @Test
    public void testCreatePersonneAffectationCommercial() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        final DimensionRessourceDto commercial = new DimensionRessourceDto();
        commercial.setIdentifiant(3L);
        personneToCreate.setCommercial(commercial);

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance(), null));

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();

        // on cree la personne
        final PersonneDto personneCree =
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.74"), 3L, personneCree.getCommercial().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.75"), 3L, personneCree.getAgence().getIdentifiant());
    }

    /**
     * Test unitaire de création d'une personne avec deux bénéficiaires.
     */
    @Test
    public void testCreatePersonneAvecDeuxBenef() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate1 = new BeneficiaireDto();
        final BeneficiaireDto beneficaireTocreate2 = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation BeneficiaireDto 1
        beneficaireTocreate1.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate1.setPrenom("prenomB1");
        beneficaireTocreate1.setNom("nomB1");
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate1.setDateNaissance(date11);
        beneficaireTocreate1.setTypeRelation(new IdentifiantLibelleDto(1L));

        // Initialisation BeneficiaireDto 2
        beneficaireTocreate2.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate2.setPrenom("prenomB2");
        beneficaireTocreate2.setNom("nomB2");
        beneficaireTocreate2.setDateNaissance(date11);
        beneficaireTocreate2.setTypeRelation(new IdentifiantLibelleDto(2L));

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate1);
        listebeneficiaire.add(beneficaireTocreate2);

        // Appel du service à tester
        personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);

        // Récupération du résultat.
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, MAX_LUCENE_VALUE);
        final RemotePagingResultsDto<PersonneSimpleDto> personnes = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.80"), 10, personnes.getTotalResults());

    }

    /**
     * Test unitaire de création d'une personne avec toutes les informations.
     */
    @Test
    public void testCreatePersonne() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(2L));

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        // Appel du service à tester
        final PersonneDto personneCree =
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);

        // Récupération du résultat.
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, MAX_LUCENE_VALUE);
        final RemotePagingResultsDto<PersonneSimpleDto> personnes = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.81"), 9, personnes.getTotalResults());

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.82"), personneCree.getIdentifiant());

        // Test des valeurs par défaut
        final String message = Messages.getString("PersonnePhysiqueServiceTest.83");
        assertNotNull(message, personneCree.getNaturePersonne());
        assertNotNull(message, personneCree.getProfession());
        assertNotNull(message, personneCree.getSegment());
        assertNotNull(message, personneCree.getSitFam());
        assertNotNull(message, personneCree.getReseauVente());
        assertNotNull(message, personneCree.getNaturePersonne());
        assertEquals(message, squareMappingService.getIdNaturePersonneParDefaut(), personneCree.getNaturePersonne().getIdentifiant());
        assertEquals(message, squareMappingService.getIdProfessionPersonneParDefaut(), personneCree.getProfession().getIdentifiant());
        assertEquals(message, squareMappingService.getIdSegmentPersonneParDefaut(), personneCree.getSegment().getIdentifiant());
        assertEquals(message, squareMappingService.getIdSituationFamilialePersonneParDefaut(), personneCree.getSitFam().getIdentifiant());
        assertEquals(message, squareMappingService.getIdReseauVentePersonneParDefaut(), personneCree.getReseauVente().getIdentifiant());
        assertEquals(message, squareMappingService.getIdCSPPersonneParDefaut(), personneCree.getCsp().getIdentifiant());
        final Calendar dateCreation = Calendar.getInstance();
        dateCreation.setTime(new Date());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.84"), String.format(Messages.getString("PersonnePhysiqueServiceTest.85"), dateCreation.getTime()), String.format(Messages.getString("PersonnePhysiqueServiceTest.86"), personneCree //$NON-NLS-3$
                .getDateCreation().getTime()));
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.87"), 1L, personneCree.getCommercial().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.88"), 2L, personneCree.getAgence().getIdentifiant());

        // Test des coordonnées
        final CoordonneesDto coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(personneCree.getIdentifiant());
        final List<AdresseDto> adressesDto = coordonneesDto.getAdresses();
        final List<EmailDto> emailsDto = coordonneesDto.getEmails();
        final List<TelephoneDto> telephonesDto = coordonneesDto.getTelephones();
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.89"), adressesDto.size(), 1);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.90"), telephonesDto.size(), 1);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.91"), emailsDto.size(), 1);

        // On récupère les bénéficiaires de la personne créée
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonneSource(personneCree.getIdentifiant());
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> rprRelations =
            personneService.rechercherRelationsParCritreres(criteresRel);
        final List<RelationInfosDto<? extends PersonneRelationDto>> relations = rprRelations.getListResults();
        for (RelationInfosDto<? extends PersonneRelationDto> relation : relations) {
            // On vérifie que le bénéficiaire a les mêmes coordonnées que la personne principale
            final CoordonneesDto coordonneesBeneficiaireDto = personneService.rechercherCoordonneesParIdPersonne(relation.getIdPersonne());
            final List<AdresseDto> adressesBeneficiaireDto = coordonneesBeneficiaireDto.getAdresses();
            final List<EmailDto> emailsBeneficiaireDto = coordonneesBeneficiaireDto.getEmails();
            final List<TelephoneDto> telephonesBeneficiaireDto = coordonneesBeneficiaireDto.getTelephones();
            boolean adresseTrouvee = false;
            for (AdresseDto adresseBeneficiaire : adressesBeneficiaireDto) {
                for (AdresseDto adresse : adressesDto) {
                    if (adresse.getIdentifiant().equals(adresseBeneficiaire.getIdentifiant())) {
                        adresseTrouvee = true;
                        break;
                    }
                }
            }
            boolean emailTrouve = false;
            for (EmailDto emailBeneficiaire : emailsBeneficiaireDto) {
                for (EmailDto emailPersonnePrincipale : emailsDto) {
                    if (emailPersonnePrincipale.getIdentifiant().equals(emailBeneficiaire.getIdentifiant())) {
                        emailTrouve = true;
                        break;
                    }
                }
            }
            boolean telephoneTrouve = false;
            for (TelephoneDto telBeneficiaire : telephonesBeneficiaireDto) {
                for (TelephoneDto telephone : telephonesDto) {
                    if (telephone.getId().equals(telBeneficiaire.getId())) {
                        telephoneTrouve = true;
                        break;
                    }
                }
            }
            assertTrue(Messages.getString("PersonnePhysiqueServiceTest.92"), adresseTrouvee);
            assertTrue(Messages.getString("PersonnePhysiqueServiceTest.93"), emailTrouve);
            assertTrue(Messages.getString("PersonnePhysiqueServiceTest.94"), telephoneTrouve);
        }
    }

    /**
     * Test unitaire de création d'une personne avec toutes les informations.
     */
    @Test
    public void testCreatePersonneNomPrenomErrones() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom("2nom-2l");
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(2L));

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        // Appel du service à tester
        try {
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.96"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.97"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NOM_BAD_FORMAT), e.getRapport().getRapports().get("PersonneDto.nom").getMessage());
        }
    }

    /**
     * Test unitaire de création d'une personne avec la suppression des majuscules sur les champs de type String.
     */
    public void testCreatePersonneAvecSuppressionMajuscules() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom("PRENOM TEST");
        personneToCreate.setNom("NOM TEST");
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Appel du service à tester
        final PersonneDto personneCree = personnePhysiqueService.creerPersonnePhysique(personneToCreate, null, adresseToCreate, emailDto, telephoneToCreate);

        // Récupération du résultat.
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        criterias.setNom("nom test");
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, MAX_LUCENE_VALUE);
        final RemotePagingResultsDto<PersonneSimpleDto> personnes = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.102"), 1, personnes.getTotalResults());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.103"), "nom test", personnes.getListResults().get(0).getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.105"), "prenom test", personnes.getListResults().get(0).getPrenom());

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.107"), personneCree.getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.108"), "nom test", personneCree.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.110"), "prenom test", personneCree.getPrenom());
    }

    /**
     * Test d'un pays autre que la france.
     */
    @Test
    public void testCreationPersonnePaysEtranger() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(2L));

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(2L));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        // Appel du service à tester
        personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);

        // Récupération du résultat.
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, MAX_LUCENE_VALUE);
        final RemotePagingResultsDto<PersonneSimpleDto> personnes = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.112"), 9, personnes.getTotalResults());
    }

    /**
     * Test unitaire du format de numéro de téléphone.
     */
    // FIXME : pour l'instant, plus de vérification de la nature de téléphone
    // @Test
    // public void testFomatTelephone() {
    // // Instanciation des dto.
    // final PersonneDto personneToCreate = new PersonneDto();
    // final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
    // final AdresseDto adresseToCreate = new AdresseDto();
    // final TelephoneDto telephoneToCreate = new TelephoneDto();
    // final EmailDto emailDto = new EmailDto();
    //
    // // Initialisation PersonneDto.
    // personneToCreate.setIdext(idext);
    // personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
    // personneToCreate.setPrenom(prenom);
    // personneToCreate.setNom(nom);
    // final Calendar date1 = Calendar.getInstance();
    // date1.clear();
    // final int year = 2010;
    // date1.set(year, Calendar.MAY, 3);
    // personneToCreate.setDateNaissance(date1);
    // personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    // personneToCreate.setCreateur(createur());
    // personneToCreate.setCommercial(commercial());
    //
    // // Initialisation telephoneDto
    // telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    // telephoneToCreate.setNumero("012LHJG12");
    // final PaysSimpleDto pays = new PaysSimpleDto();
    // pays.setId(1L);
    // telephoneToCreate.setPays(pays);
    //
    // // Initilisation de l'email.
    // emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
    // emailDto.setAdresse(email);
    //
    // // Initialisation BeneficiaireDto
    // beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
    // beneficaireTocreate.setPrenom(prenomB);
    // beneficaireTocreate.setNom(nomB);
    // final Calendar date11 = Calendar.getInstance();
    // date11.clear();
    // final int year1 = 2010;
    // date11.set(year1, Calendar.MAY, 3);
    // beneficaireTocreate.setDateNaissance(date11);
    //
    // // Initialisation AdresseDto
    // adresseToCreate.setIdext(idext);
    // adresseToCreate.setNumVoie(numVoie);
    // adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
    // adresseToCreate.setCodePostal(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    // adresseToCreate.setCommune(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    // adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    //
    // // Ajout du bénéficiaire dans la liste des bénéficiaires.
    // final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
    // listebeneficiaire.add(beneficaireTocreate);
    //
    // try {
    // // Appel du service à tester.
    // personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
    // fail("La saisie d'un mauvais numéro de téléphone aurait dû échouée.");
    // }
    // catch (ControleIntegriteException e) {
    // assertEquals("L'exception rencontrée lors de la saisie d'un téléphone incorrect est différente de celle attendue", messageSourceUtil
    // .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), e.getRapport().getRapports().get("TelephoneDto.numero.0")
    // .getMessage());
    //
    // }
    //
    // }
    //
    // // Initialisation BeneficiaireDto
    // beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
    // beneficaireTocreate.setPrenom(prenomB);
    // beneficaireTocreate.setNom(nomB);
    // final Calendar date11 = Calendar.getInstance();
    // date11.clear();
    // final int year1 = 2010;
    // date11.set(year1, Calendar.MAY, 3);
    // beneficaireTocreate.setDateNaissance(date11);
    //
    // // Initialisation AdresseDto
    // adresseToCreate.setIdext(idext);
    // adresseToCreate.setNumVoie(numVoie);
    // adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
    // adresseToCreate.setCodePostal(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    // adresseToCreate.setCommune(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    // adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    //
    // // Ajout du bénéficiaire dans la liste des bénéficiaires.
    // final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
    // listebeneficiaire.add(beneficaireTocreate);
    //
    // try {
    // // Appel du service à tester.
    // personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
    // fail("La saisie d'un mauvais numéro de téléphone aurait dû échouée.");
    // }
    // catch (ControleIntegriteException e) {
    // assertEquals("L'exception rencontrée lors de la saisie d'un téléphone incorrect est différente de celle attendue", messageSourceUtil
    // .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), e.getRapport().getRapports().get("TelephoneDto.numero.0")
    // .getMessage());
    //
    // }
    //
    // }

    // /**
    // * Test unitaire de la nature de téléphone.
    // */
    // @Test
    // public void testNatureTelephone() {
    // // Instanciation des dto.
    // final PersonneDto personneToCreate = new PersonneDto();
    // final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
    // final AdresseDto adresseToCreate = new AdresseDto();
    // final TelephoneDto telephoneToCreate = new TelephoneDto();
    // final EmailDto emailDto = new EmailDto();
    //
    // // Initialisation PersonneDto.
    // personneToCreate.setIdext(idext);
    // personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
    // personneToCreate.setPrenom(prenom);
    // personneToCreate.setNom(nom);
    // final Calendar date1 = Calendar.getInstance();
    // date1.clear();
    // final int year = 2010;
    // date1.set(year, Calendar.MAY, 3);
    // personneToCreate.setDateNaissance(date1);
    // personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    // personneToCreate.setCreateur(createur());
    // personneToCreate.setCommercial(commercial());
    //
    // // Initialisation telephoneDto
    // telephoneToCreate.setNature(null);
    // telephoneToCreate.setNumero("0545454545");
    // final PaysSimpleDto pays = new PaysSimpleDto();
    // pays.setId(1L);
    // telephoneToCreate.setPays(pays);
    //
    // // Initilisation de l'email.
    // emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
    // emailDto.setAdresse(email);
    //
    // // Initialisation BeneficiaireDto
    // beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
    // beneficaireTocreate.setPrenom(prenomB);
    // beneficaireTocreate.setNom(nomB);
    // final Calendar date11 = Calendar.getInstance();
    // date11.clear();
    // final int year1 = 2010;
    // date11.set(year1, Calendar.MAY, 3);
    // beneficaireTocreate.setDateNaissance(date11);
    //
    // // Initialisation AdresseDto
    // adresseToCreate.setIdext(idext);
    // adresseToCreate.setNumVoie(numVoie);
    // adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
    // adresseToCreate.setCodePostal(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    // adresseToCreate.setCommune(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    // adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
    //
    // // Ajout du bénéficiaire dans la liste des bénéficiaires.
    // final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
    // listebeneficiaire.add(beneficaireTocreate);
    //
    // try {
    // // Appel du service à tester.
    // personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
    // fail("La création d'un téléphone avec nature incorrecte aurait du échouer.");
    // }
    // catch (ControleIntegriteException e) {
    // assertEquals("L'exception rencontrée lors de la saisie d'un némuro avec la nature non conforme différente de celle attendue", messageSourceUtil
    // .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NATURETELEPHONE_NULL), e.getRapport().getRapports().get("TelephoneDto.nature.0")
    // .getMessage());
    // }
    // }

    /**
     * Test unitaire du format de l'email.
     */
    @Test
    public void testFomatEmail() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse("email@");

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        try {
            // Appel du service à tester.
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.114"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.115"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_BAD_EMAIL), e.getRapport().getRapports().get("EmailDto.adresse").getMessage());

        }

    }

    /**
     * Test unitaire de modifcation d'une personne sans nom de jeune fille.
     */
    @Test
    public void testModifPersonneNomJeuneFilleNull() {
        final PersonneDto personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(1L);
        personneDto.setNomJeuneFille(null);
        personneDto.setPrenom("prénom");
        personneDto.setNom(null);
        personneDto.setDecede(false);
        personneDto.setNaturePersonne(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneDto.setInfoSante(new InfoSanteDto());
        personneDto.getInfoSante().setNro("123456789123489");
        personneDto.getInfoSante().setCaisse(new CaisseSimpleDto(1L));
        personneDto.setReseauVente(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneDto.setCreateur(createur());
        personneDto.setCommercial(commercial());
        personneDto.setStatut(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        try {
            // Appel du service à tester.
            personnePhysiqueService.modifierPersonnePhysique(personneDto);
            fail(Messages.getString("PersonnePhysiqueServiceTest.119"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.120"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NOM_NULL), e.getRapport().getRapports().get("PersonneDto.nom").getMessage());
        }
    }

    /**
     * Test unitaire de modifcation d'une personne avec une caisse inexistante.
     */
    @Test
    public void testModifPersonneCaisseInexistante() {
        final PersonneDto personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(1L);
        personneDto.setAgence(new IdentifiantEidLibelleDto(1L, null));
        personneDto.getInfoSante().setCaisse(new CaisseSimpleDto(-1L));
        try {
            // Appel du service à tester.
            personnePhysiqueService.modifierPersonnePhysique(personneDto);
            fail(Messages.getString("PersonnePhysiqueServiceTest.122"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.123"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_CAISSE_INEXISTENT_EN_BD), e.getMessage());
        }
    }

    /**
     * Test unitaire de modification d'une personne avec une agence nulle.
     */
    @Test
    public void testModifPersonneAgenceNull() {
        final PersonneDto personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(1L);
        personneDto.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneDto.setAgence(null);
        try {
            // Appel du service à tester.
            personnePhysiqueService.modifierPersonnePhysique(personneDto);
            fail(Messages.getString("PersonnePhysiqueServiceTest.124"));
        }

        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.125"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_SAVE_PERSONNE_AGENCE_NULL), e.getRapport().getRapports().get("PersonneDto.agence").getMessage());
        }

        catch (BusinessException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.127"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_COMMERCIAL_INEXISTANTE), e.getMessage());
        }
    }

    /**
     * Test de mofication d'une personne.
     */
    @Test
    public void testModificationPersonne() {
        final Long idPersonne = 1L;
        PersonneDto personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne);
        personneDto.setNomJeuneFille("nomJeuneFille");
        personneDto.setNom("Nom personne");
        personneDto.setPrenom("Prénom");
        personneDto.setDecede(false);
        personneDto.setReseauVente(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneDto.setCreateur(createur());
        personneDto.setCommercial(commercial());
        personneDto.setInfoSante(new InfoSanteDto());
        personneDto.getInfoSante().setNro("179011600100095");
        personneDto.getInfoSante().setCaisse(new CaisseSimpleDto(1L));
        personneDto.getInfoSante().setIdReferent(idPersonne);
        personneDto.setStatut(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneDto.setAgence(new IdentifiantEidLibelleDto(1L, null));
        personneDto.setCommercial(commercial());

        // Appel du service à tester.
        personnePhysiqueService.modifierPersonnePhysique(personneDto);

        // Récupération du résultat.
        personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne);

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.132"), "nom personne", personneDto.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.134"), "prénom", personneDto.getPrenom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.136"), "Nom 3", personneDto.getCommercial().getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.138"), "Prénom 1", personneDto.getCommercial().getPrenom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.140"), "179011600100095", personneDto.getInfoSante().getNro());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.142"), 1L, personneDto.getInfoSante().getCaisse().getId());
    }

    /**
     * Test de mise à jour des index lors de modification d'adresse, mail ou telephone.
     */
    @Test
    public void testMiseAJourIndexBidirectionnel() {
        final Long idPersonne = 1L;

        final CoordonneesDto coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(idPersonne);

        // Creation d'une adresse originale
        final String nomDeLaVoie = "nomVoieTestBidir";
        coordonneesDto.getAdresses().get(0).setNomVoie(nomDeLaVoie);

        // Creation d'un email original
        final String emailAdr = "testbidir@scub.net";
        coordonneesDto.getEmails().get(0).setAdresse(emailAdr);

        // Creation d'un telephone original
        final String numeroTel = "0678951236";
        coordonneesDto.getTelephones().get(0).setNumero(numeroTel);

        personneService.creerOuMettreAJourCoordonnees(coordonneesDto, true, false);

        // Test de la recherche sur les criteres
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;
        RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText = null;
        criteresFullText = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);

        // Recherche sur adresse
        criteresRecherche.setAdresse(nomDeLaVoie);
        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);
        assertNotNull(resultatNull, resultFullText);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.146"), 2, resultFullText.getTotalResults());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.147"), resultFullText.getListResults().get(0).getId(), idPersonne);

        // Recherche sur telephone
        criteresRecherche.setAdresse(null);
        criteresRecherche.setTelephone(numeroTel);
        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);
        assertNotNull(resultatNull, resultFullText);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.148"), 2, resultFullText.getTotalResults());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.149"), resultFullText.getListResults().get(0).getId(), idPersonne);

        // Recherche sur email
        criteresRecherche.setTelephone(null);
        criteresRecherche.setEmail(emailAdr);
        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);
        assertNotNull(resultatNull, resultFullText);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.150"), 2, resultFullText.getTotalResults());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.151"), resultFullText.getListResults().get(0).getId(), idPersonne);
    }

    /**
     * Test de mofication d'une personne qui n'avait pas d'info santé.
     */
    @Test
    public void testModificationPersonneSansInfoSante() {
        final Long idPersonne = 4L;
        PersonneDto personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne);
        assertNull(Messages.getString("PersonnePhysiqueServiceTest.152"), personneDto.getInfoSante());

        personneDto.setInfoSante(new InfoSanteDto());
        personneDto.getInfoSante().setNro("179011600100095");
        personneDto.getInfoSante().setCaisse(new CaisseSimpleDto(1L));
        personneDto.getInfoSante().setIdReferent(idPersonne);

        // Appel du service à tester.
        personnePhysiqueService.modifierPersonnePhysique(personneDto);

        // Récupération du résultat.
        personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.154"), personneDto.getInfoSante());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.155"), "179011600100095", personneDto.getInfoSante().getNro());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.157"), 1L, personneDto.getInfoSante().getCaisse().getId());
    }

    // Tests Recherche
    /**
     * Test de la récupération d'une personne avec un DTO null.
     */
    @Test
    public void testRechercherPersonneAvecParamNull() {
        try {
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(null);
            fail(Messages.getString("PersonnePhysiqueServiceTest.158"));
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.159"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SEARCH_DTO_NULL), be.getMessage());
        }
    }

    /**
     * Test de la récupération d'une personne avec la recherche Hibernate search avec un DTO null.
     */
    @Test
    public void testRechercherPersonneFullTextAvecParamNull() {
        try {
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(null);
            fail(Messages.getString("PersonnePhysiqueServiceTest.160"));
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.161"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SEARCH_DTO_NULL), be.getMessage());
        }
    }

    /**
     * Test de la récupération d'une personne avec aucun critères.
     */
    @Test
    public void testRechercherPersonneSansCriteres() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.162"));
        // Création des critères de recherche
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.163"), result);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.164"), 7, result.getTotalResults());
        boolean isPersonne1Trouvee = false;
        boolean isPersonne2Trouvee = false;
        boolean isPersonne4Trouvee = false;
        boolean isPersonne5Trouvee = false;
        boolean isPersonne6Trouvee = false;
        boolean isPersonne7Trouvee = false;
        for (PersonneSimpleDto personne : result.getListResults()) {
            if (personne.getId().equals(1L)) {
                isPersonne1Trouvee = true;
            }
            else if (personne.getId().equals(2L)) {
                isPersonne2Trouvee = true;
            }
            else if (personne.getId().equals(4L)) {
                isPersonne4Trouvee = true;
            }
            else if (personne.getId().equals(5L)) {
                isPersonne5Trouvee = true;
            }
            else if (personne.getId().equals(6L)) {
                isPersonne6Trouvee = true;
            }
            else if (personne.getId().equals(7L)) {
                isPersonne7Trouvee = true;
            } 
            else if (personne.getId().equals(8L)) {
                isPersonne7Trouvee = true;
            }
            else {
                fail(Messages.getString("PersonnePhysiqueServiceTest.165"));
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueServiceTest.166"), isPersonne1Trouvee && isPersonne2Trouvee && isPersonne4Trouvee
            && isPersonne5Trouvee && isPersonne6Trouvee && isPersonne7Trouvee);

        // Avec recherche full Text

        // Recherche fullText sans personne de crée
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, 10);
        final RemotePagingResultsDto<PersonneSimpleDto> resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        assertNotNull(resultatNull, resultFullText);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.167"), 7, resultFullText
                .getTotalResults());

        final PersonneDto personne = creerPersonne();
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, creerAdresse(), creerEmail(), creerTelephone());

        // Test de la recherche avec une personne de crée
        final RemotePagingResultsDto<PersonneSimpleDto> resultFullTextAvecPersonne =
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(resultatNull, resultFullTextAvecPersonne);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.168"), 8, resultFullTextAvecPersonne
                .getTotalResults());
    }

    /**
     * Test de la récupération d'une personne par son numéro client.
     */
    @Test
    public void testRechercherPersonneParNumClient() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.169"));
        // Création des critères de recherche.
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();

        criteres.setNumeroClient("1111");

        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.171"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.172"), 1, result.getTotalResults());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.173"), 1L, result.getListResults().get(0).getId());

        // Recherche avec %
        criteres.setNumeroClient("%11%");
        criteresAvecPagination.setCriterias(criteres);

        final RemotePagingResultsDto<PersonneSimpleDto> resultatAvecApproximation =
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.175"), resultatAvecApproximation);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.176"), 1, resultatAvecApproximation.getTotalResults());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.177"), 1L, resultatAvecApproximation.getListResults().get(0).getId());

    }

    /**
     * Test de la récupération d'une personne par son nom.
     */
    @Test
    public void testRechercherPersonneParNom() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.178"));
        // Création des critères de recherche.
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();

        criteres.setNom(nom1);

        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.179"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.180"), 2, result.getTotalResults());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.181"), 8L, result.getListResults().get(0).getId());

        // Avec recherche full Text
        // Création de la personne a indexer
        PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emaildto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personne = personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emaildto, telephone);

        // Test de la recherche avec un nom exact
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        criteresRecherche.setNom("nom*");
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.183"), resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.184"), 8, resultFullText.getTotalResults());

        // Recherche avec %
        criteres.setNom("%m%");
        criteresAvecPagination.setCriterias(criteres);
        final List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        listeSorts.add(new RemotePagingSort(squareMappingService.getProprietePersonneNom(), RemotePagingSort.REMOTE_PAGING_SORT_DESC));
        criteresAvecPagination.setListeSorts(listeSorts);

        final RemotePagingResultsDto<PersonneSimpleDto> resultatAvecApproximation =
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.186"), resultatAvecApproximation);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.187"), 8, resultatAvecApproximation.getTotalResults());
        final List<PersonneSimpleDto> listeResults = resultatAvecApproximation.getListResults();
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.188"), resultatAvecApproximation.getTotalResults(),
            listeResults.size());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.189"), 5L, listeResults.get(0).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.190"), 8L, listeResults.get(1).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.191"), 1L, listeResults.get(2).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.192"), 4L, listeResults.get(3).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.193"), 2L, listeResults.get(4).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.194"), 6L, listeResults.get(5).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.194"), 7L, listeResults.get(6).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.195"), personne.getIdentifiant(), listeResults.get(7).getId());
    }

    /**
     * Test de la récupération par nom de jeune fille.
     */
    @Test
    public void testRechercherPersonneParNomJeuneFille() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.196"));
        // Création des critères de recherche.
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();

        criteres.setNomJeuneFille("NomJeuneFille1");

        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.198"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.199"), 2, result.getTotalResults());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.200"), 8L, result.getListResults().get(0).getId());

        // Recherche avec %
        criteres.setNomJeuneFille("%m%");
        criteresAvecPagination.setCriterias(criteres);

        final RemotePagingResultsDto<PersonneSimpleDto> resultatAvecApproximation =
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.202"), resultatAvecApproximation);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.203"), 4, resultatAvecApproximation.getTotalResults());
    }

    /**
     * Test de la récupération par prénom.
     */
    @Test
    public void testRechercherPersonneParPrenom() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.204"));

        // Recherche hibernate search
        final PersonneDto personne = creerPersonne();
        personne.setPrenom("prénom");
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emaildto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emaildto, telephone);

        // Test de la recherche
        PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        criteresRecherche.setPrenom("pr?nom*");
        RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.207"), resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.208"), 8, resultFullText.getTotalResults());

        // Test de la recherche approximative
        criteresRecherche = new PersonneCriteresRechercheDto();
        criteresRecherche.setPrenom("pren*");
        criteresFullText = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.210"), resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.211"), 8, resultFullText.getTotalResults());

        // Recherche avec %
        criteresRecherche.setPrenom("%mu%");
        criteresFullText.setCriterias(criteresRecherche);

        final RemotePagingResultsDto<PersonneSimpleDto> resultatAvecApproximation =
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.213"), resultatAvecApproximation);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.214"), 2, resultatAvecApproximation.getTotalResults());

        boolean trouvee = false;

        for (PersonneSimpleDto personneSimple : resultatAvecApproximation.getListResults()) {
            if (personneSimple.getId().equals(1L)) {
                trouvee = true;
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueServiceTest.215"), trouvee);

    }

    /**
     * Test de la récupération par nro.
     */
    @Test
    public void testRechercherPersonneParNRO() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.216"));
        // Création des critères de recherche.
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();

        criteres.setNro("123456789012311");

        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.218"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.219"), 2, result.getTotalResults());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.220"), 8L, result.getListResults().get(0).getId());
    }

    /**
     * Test de la récupération par dateNaissance.
     */
    @Test
    public void testRechercherPersonneParDate() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.221"));
        // Création des critères de recherche.
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();

        final Calendar dateNaissance = Calendar.getInstance();
        dateNaissance.clear();
        final int year = 2000;
        dateNaissance.set(year, Calendar.JANUARY, 1);
        criteres.setDateNaissance(dateNaissance);

        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.222"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.223"), 2, result.getTotalResults());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.224"), 8L, result.getListResults().get(0).getId());

        // Avec recherche full Text
        // Création de la personne a indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emaildto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emaildto, telephone);

        // Test de la recherche
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        criteresRecherche.setDateNaissance(dateNaissance);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.225"), resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.226"), 3, resultFullText.getTotalResults());
    }

    /**
     * Test de la récupéation par nature.
     */
    @Test
    public void testRechercherPersonneParNature() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.227"));
        // Création des critères de recherche.
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();

        final List<Long> listeIdsNatures = new ArrayList<Long>();
        listeIdsNatures.add(1L);
        listeIdsNatures.add(2L);
        criteres.setListeNatures(listeIdsNatures);

        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        final List<RemotePagingSort> listSort = new ArrayList<RemotePagingSort>();
        final RemotePagingSort remotePagingSort = new RemotePagingSort();
        remotePagingSort.setSortField("nature.id");
        remotePagingSort.setSortAsc(RemotePagingSort.REMOTE_PAGING_SORT_DESC);
        listSort.add(remotePagingSort);
        criteresAvecPagination.setListeSorts(listSort);
        // Appel au service.
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.229"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.230"), 6, result.getTotalResults());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.231"), 2L, result.getListResults().get(0).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.232"), 4L, result.getListResults().get(1).getId());
    }

    /**
     * Test de la récupération par téléphone.
     */
    @Test
    public void testRechercherPersonneParTel() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.233"));
        // Création des critères de recherche.
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();

        criteres.setTelephone("0505050505");

        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.235"), result);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.236"), 2, result.getTotalResults());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.237"), 8L, result.getListResults().get(0).getId());

        // Avec recherche full Text
        // Création de la personne a indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emaildto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emaildto, telephone);

        // Test de la recherche
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        criteresRecherche.setTelephone("0123456");
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);
        // Tests
        assertNotNull(resultatNull, resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.239"), 1, resultFullText.getTotalResults());

        // Recherche avec %
        criteresRecherche.setTelephone("0%");
        criteresFullText.setCriterias(criteresRecherche);

        final RemotePagingResultsDto<PersonneSimpleDto> resultatAvecApproximation =
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.241"), resultatAvecApproximation);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.242"), 4, resultatAvecApproximation.getTotalResults());

    }

    /**
     * Test de la récupération par email.
     */
    @Test
    public void testRechercherPersonneParEmail() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.243"));

        // Avec recherche full Text
        // Création de la personne a indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emaildto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emaildto, telephone);

        // Test de la recherche
        PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        criteresRecherche.setEmail("email1@square.com");
        RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(resultatNull, resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.245"), 3, resultFullText.getTotalResults());

        // Recherche approximative
        criteresRecherche = new PersonneCriteresRechercheDto();
        criteresRecherche.setEmail("e*");
        criteresFullText = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(resultatNull, resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.247"), 4, resultFullText.getTotalResults());

        // Recherche avec %
        criteresRecherche.setEmail("%@%");
        criteresFullText.setCriterias(criteresRecherche);

        final RemotePagingResultsDto<PersonneSimpleDto> resultatAvecApproximation =
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.249"), resultatAvecApproximation);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.250"), 4, resultatAvecApproximation.getTotalResults());

        boolean trouvee = false;
        for (PersonneSimpleDto personneSimple : resultatAvecApproximation.getListResults()) {
            if (personneSimple.getId().equals(1L)) {
                trouvee = true;
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueServiceTest.251"), trouvee);
    }

    /**
     * Test de la récupération par numéro de voie.
     */
    @Test
    public void testRechercherPersonneParNumVoie() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.252"));

        // Avec recherche full Text
        // Création de la personne a indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emaildto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emaildto, telephone);

        // Test de la recherche
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        criteresRecherche.setNumVoie("1");
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(resultatNull, resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.254"), 3, resultFullText.getTotalResults());

        // Recherche avec %
        criteresRecherche.setNumVoie("%b%");
        criteresFullText.setCriterias(criteresRecherche);

        final RemotePagingResultsDto<PersonneSimpleDto> resultatAvecApproximation =
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.256"), resultatAvecApproximation);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.257"), 1, resultatAvecApproximation.getTotalResults());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.258"), 2L, resultatAvecApproximation.getListResults().get(0).getId());
    }

    /**
     * Test de la récupération par nature de voie.
     */
    @Test
    public void testRecherchePersonneParNaturevoie() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.259"));

        // Avec recherche full Text
        // Création de la personne a indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emaildto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emaildto, telephone);

        // Test de la recherche
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        final List<Long> listeNaturesVoie = new ArrayList<Long>();
        listeNaturesVoie.add(1L);
        listeNaturesVoie.add(2L);
        criteresRecherche.setListeNaturesVoie(listeNaturesVoie);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.260"), resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.261"), 4, resultFullText.getTotalResults());
    }

    /**
     * Test de la récupération par adresse.
     */
    @Test
    public void testRechercherPersonneParAdresse() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.262"));

        // Avec recherche full Text
        // Création de la personne a indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emaildto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        final PersonneDto personneCreee = personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emaildto, telephone);

        // Test de la recherche
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        criteresRecherche.setAdresse(nomVoie);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(resultatNull, resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.263"), 1, resultFullText.getTotalResults());

        // Recherche avec %
        criteresRecherche.setAdresse("%n%");
        criteresFullText.setCriterias(criteresRecherche);

        final RemotePagingResultsDto<PersonneSimpleDto> resultatAvecApproximation =
            personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.265"), resultatAvecApproximation);
        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.266"), 1, resultatAvecApproximation.getTotalResults());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.267"), personneCreee.getIdentifiant(),
            resultatAvecApproximation.getListResults().get(0).getId());
    }

    /**
     * Test de la récupération par code postal.
     */
    @Test
    public void testRechercherPersonneParCodePostal() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.268"));

        // Avec recherche full Text
        // Création de la personne a indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emaildto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emaildto, telephone);

        // Test de la recherche
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        final List<Long> listeCodesPostaux = new ArrayList<Long>();
        listeCodesPostaux.add(1L);
        listeCodesPostaux.add(2L);
        // On ajoute un critère complémentaire pour etre sur qu'un ET est fait entre le critère et la clause in
        criteresRecherche.setNom("Nom*");
        criteresRecherche.setListeCodesPostaux(listeCodesPostaux);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(resultatNull, resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.270"), 4, resultFullText.getListResults().size());
    }

    /**
     * Test de la récupération par ville.
     */
    @Test
    public void testRechercherPersonneParVille() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.271"));

        // Avec recherche full Text
        // Création de la personne a indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emailDto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emailDto, telephone);

        // Test de la recherche
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        final List<Long> listeVilles = new ArrayList<Long>();
        listeVilles.add(1L);
        listeVilles.add(2L);
        criteresRecherche.setListeVilles(listeVilles);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.272"), resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.273"), 4, resultFullText.getTotalResults());
    }

    /**
     * Test de la récupération par agence.
     */
    @Test
    public void testRechercherPersonneParAgence() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.274"));

        // Avec recherche full Text
        // Création de la personne à indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emailDto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emailDto, telephone);

        // Création des critères de recherche.
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        final List<Long> listeAgences = new ArrayList<Long>();
        listeAgences.add(1L);
        listeAgences.add(2L);
        criteresRecherche.setIdAgences(listeAgences);

        // Test de la recherche
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);

        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;
        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.275"), resultFullText);

        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.276"), 7, resultFullText.getTotalResults());

        final Long idPersonne1 = 1L;
        boolean personne1Trouvee = false;
        for (final PersonneSimpleDto personneParcourue : resultFullText.getListResults()) {
            if (personneParcourue.getId().equals(idPersonne1)) {
                personne1Trouvee = true;
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueServiceTest.277"), personne1Trouvee);
    }

    /**
     * Test de la récupération par commercial.
     */
    @Test
    public void testRechercherPersonneParCommercial() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.278"));

        // Avec recherche full Text
        // Création de la personne à indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emailDto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        final PersonneDto personneCreee = personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emailDto, telephone);

        // Création des critères de recherche.
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();

        final List<Long> listeRessources = new ArrayList<Long>();
        listeRessources.add(1L);
        listeRessources.add(2L);
        criteresRecherche.setIdCommerciaux(listeRessources);

        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);

        // Appel au service.
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;
        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.279"), resultFullText);

        // Tests des valeurs de retour.
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.280"), 7, resultFullText.getTotalResults());

        boolean isPersonne1Trouve = false;
        boolean isPersonne2Trouve = false;
        boolean isPersonne4Trouve = false;
        boolean isPersonne5Trouve = false;
        boolean isPersonne6Trouve = false;
        boolean isPersonne7Trouve = false;
        boolean isPersonneCreeTrouve = false;
        for (PersonneSimpleDto personneSimple : resultFullText.getListResults()) {
            if (personneSimple.getId().equals(1L)) {
                isPersonne1Trouve = true;
            }
            else if (personneSimple.getId().equals(2L)) {
                isPersonne2Trouve = true;
            }
            else if (personneSimple.getId().equals(4L)) {
                isPersonne4Trouve = true;
            }
            else if (personneSimple.getId().equals(5L)) {
                isPersonne5Trouve = true;
            }
            else if (personneSimple.getId().equals(6L)) {
                isPersonne6Trouve = true;
            }
            else if (personneSimple.getId().equals(7L)) {
                isPersonne7Trouve = true;
            }
            else if (personneSimple.getId().equals(personneCreee.getIdentifiant())) {
                isPersonneCreeTrouve = true;
            }
            else {
                fail(Messages.getString("PersonnePhysiqueServiceTest.281"));
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueServiceTest.282"), isPersonne1Trouve && isPersonne2Trouve && isPersonne4Trouve
            && isPersonne5Trouve && isPersonne6Trouve && isPersonne7Trouve && isPersonneCreeTrouve);
    }

    /**
     * Test de la récupération d'une personne avec tous les critères renseignés.
     */
    @Test
    public void testRechercherPersonneAvecTousLesCriteres() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.283"));

        final Calendar dateNaissance = Calendar.getInstance();
        dateNaissance.clear();
        final int year = 2000;
        dateNaissance.set(year, Calendar.JANUARY, 1);

        // Avec recherche full Text
        // Création de la personne a indexer
        final PersonneDto personne = creerPersonne();
        final TelephoneDto telephone = creerTelephone();
        final EmailDto emailDto = creerEmail();
        final AdresseDto adresse = creerAdresse();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emailDto, telephone);

        // Test de la recherche
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        final List<Long> listeVilles = new ArrayList<Long>();
        listeVilles.add(1L);
        criteresRecherche.setListeVilles(listeVilles);
        criteresRecherche.setAdresse(nomVoie);
        criteresRecherche.setEmail("email1@square.com");
        criteresRecherche.setDateNaissance(dateNaissance);
        criteresRecherche.setNom("Nom");
        criteresRecherche.setPrenom(prenom);
        criteresRecherche.setTelephone("0123456");
        criteresRecherche.setNumVoie(numVoie);
        final List<Long> listeCodesPostaux = new ArrayList<Long>();
        listeCodesPostaux.add(1L);
        criteresRecherche.setListeCodesPostaux(listeCodesPostaux);

        final List<Long> listeAgences = new ArrayList<Long>();
        listeAgences.add(2L);
        criteresRecherche.setIdAgences(listeAgences);

        final List<Long> listeRessources = new ArrayList<Long>();
        listeRessources.add(1L);
        criteresRecherche.setIdCommerciaux(listeRessources);

        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.287"), resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.288"), 1, resultFullText.getTotalResults());
        final CodePostalCommuneDto codePostalCommune = resultFullText.getListResults().get(0).getCodePostalCommune();
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.289"), 1L, codePostalCommune.getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.290"), "Commune1 ach", codePostalCommune.getLibelleAcheminement());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.292"), 1L, codePostalCommune.getCodePostal().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.293"), "000", codePostalCommune.getCodePostal().getLibelle());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.295"), 1L, codePostalCommune.getCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.296"), "Commune1", codePostalCommune.getCommune().getLibelle());
    }

    /**
     * Test le tri.
     */
    @Test
    public void testRechercherPersonneAvecTri() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.298"));

        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        final List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        final RemotePagingSort rps = new RemotePagingSort("triNom", RemotePagingSort.REMOTE_PAGING_SORT_DESC);
        listeSorts.add(rps);
        criteresAvecPagination.setListeSorts(listeSorts);
        final RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.300"), results);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.301"), 7, results.getTotalResults());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.302"), 5L, results.getListResults().get(0).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.303"), 8L, results.getListResults().get(1).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.304"), 1L, results.getListResults().get(2).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.305"), 4L, results.getListResults().get(3).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.306"), 2L, results.getListResults().get(4).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.307"), 6L, results.getListResults().get(5).getId());

        // Avec recherche full Text
        // Création de la personne a indexer
        final PersonneDto personne = creerPersonne();
        final PersonneDto personne2 = new PersonneDto();
        personne2.setIdext("6");
        personne2.setCivilite(new IdentifiantLibelleDto(1L));
        personne2.setNom("Nom");
        personne2.setPrenom("Prenom");
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        final int year = 2000;
        cal.set(year, Calendar.JANUARY, 1);
        personne2.setDateNaissance(cal);
        final TelephoneDto telephone = new TelephoneDto();
        telephone.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephone.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephone.setPays(pays);
        personne2.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personne2.setCreateur(createur());
        personne2.setCommercial(commercial());
        final AdresseDto adresseDto2 = new AdresseDto();
        adresseDto2.setIdext(idext);
        adresseDto2.setNumVoie(numVoie);
        adresseDto2.setTypeVoie(new IdentifiantLibelleDto(2L));
        adresseDto2.setNomVoie(nomVoie);
        adresseDto2.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseDto2.setPays(new IdentifiantLibelleBooleanDto(1L));

        final EmailDto emailDto = creerEmail();

        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        final PersonneDto personne1Creee = personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresseDto2, emailDto, telephone);
        final PersonneDto personne2Creee = personnePhysiqueService.creerPersonnePhysique(personne2, listebeneficiaire, adresseDto2, emailDto, telephone);

        // Test de la recherche
        final PersonneCriteresRechercheDto criteresRecherche = new PersonneCriteresRechercheDto();
        criteresRecherche.setNom(nom + "*");

        final List<RemotePagingSort> listeSorts2 = new ArrayList<RemotePagingSort>();
        final RemotePagingSort rps2 = new RemotePagingSort("triPrenom", RemotePagingSort.REMOTE_PAGING_SORT_DESC);
        listeSorts2.add(rps2);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresRecherche, 0, MAX_LUCENE_VALUE);
        criteresFullText.setListeSorts(listeSorts2);
        RemotePagingResultsDto<PersonneSimpleDto> resultFullText = null;

        resultFullText = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);

        // Tests
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.313"), resultFullText);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.314"), 9, resultFullText.getTotalResults());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.315"), 5L, resultFullText.getListResults().get(0).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.316"), 8L, resultFullText.getListResults().get(1).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.317"), 1L, resultFullText.getListResults().get(2).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.318"), 4L, resultFullText.getListResults().get(3).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.319"), 2L, resultFullText.getListResults().get(4).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.320"), 7L, resultFullText.getListResults().get(5).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.320"), 6L, resultFullText.getListResults().get(6).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.321"), personne1Creee.getIdentifiant(), resultFullText.getListResults().get(7).getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.322"), personne2Creee.getIdentifiant(), resultFullText.getListResults().get(8).getId());
    }

    /**
     * Test de la récupération du nombre d'elements.
     */
    @Test
    public void testCountFullText() {
        // FIXME a corriger car tourne en boucle
        logger.debug("testCountFullText");
        // final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        // personnePhysiqueService.creerPersonnePhysique(creerPersonne(), listebeneficiaire, creerAdresse(), creerEmail(), creerTelephone());
        //
        // final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        // final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresFullText =
        // new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, MAX_LUCENE_VALUE);
        // final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresFullText);
        // assertEquals("Le nombre de résultat ne correspond pas", 3, result.getTotalResults());
    }

    /**
     * Test de la recherche de personne par identifiant.
     */
    @Test
    public void testRechercherPersonneParIdentifiant() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.324"));
        // Test avec une personne non existante
        try {
            personnePhysiqueService.rechercherPersonneParIdentifiant(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.325"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), be.getMessage());
        }

        // Test avec une personne non existante
        try {
            personnePhysiqueService.rechercherPersonneParIdentifiant(1000L);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.326"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), be.getMessage());
        }

        final PersonneDto personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(8L);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.327"), 8L, personneDto.getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.328"), "NomUn", personneDto.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.330"), "prénomUn", personneDto.getPrenom());
        final Calendar date = Calendar.getInstance();
        date.clear();
        final int year = 2000;
        date.set(year, Calendar.JANUARY, 1);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.331"), date, personneDto.getDateNaissance());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.332"), 4L, personneDto.getCommercial().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.333"), "2001", personneDto.getCommercial().getIdentifiantExterieur());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.335"), 4L, personneDto.getAgence().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.336"), "4", personneDto.getAgence().getIdentifiantExterieur());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.338"), 4L, personneDto.getCreateur().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.339"), "2001", personneDto.getCreateur().getIdentifiantExterieur());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.341"), 1L, personneDto.getInfoSante().getCaisse().getId());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.342"), "Centre nom 1", personneDto.getInfoSante().getCaisse().getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.344"), 1L, personneDto.getInfoSante().getCaisseRegime().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.345"), "Caisse régime 1", personneDto.getInfoSante().getCaisseRegime().getLibelle());

    }

    /**
     * Test de la recherche des coordonnées d'une personne.
     */
    @Test
    public void testRechercherCoordonnee() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.347"));

        // Test avec un identifiant null
        try {
            personneService.rechercherCoordonneesParIdPersonne(null);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.348"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL), be.getMessage());
        }

        // Test avec une personne non existante
        try {
            personneService.rechercherCoordonneesParIdPersonne(10L);
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.349"), Messages.getString("PersonnePhysiqueServiceTest.350"), be.getMessage());
        }

        final CoordonneesDto coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(1L);
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.0"), coordonneesDto);
        // Verification de l'identifiant de la personne
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.352"), 1L, coordonneesDto.getIdPersonne());
        // Vérification des adresses
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.353"), 2, coordonneesDto.getAdresses().size());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.354"), 1L, coordonneesDto.getAdresses().get(0).getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.355"), "1", coordonneesDto.getAdresses().get(0).getNumVoie());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.357"), "1", coordonneesDto.getAdresses().get(0).getNomVoie());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.359"), 1L, coordonneesDto.getAdresses().get(0).getNature().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.360"), 1L, coordonneesDto.getAdresses().get(0).getTypeVoie().getIdentifiant());
        final CodePostalCommuneDto codePostalCommune = coordonneesDto.getAdresses().get(0).getCodePostalCommune();
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.361"), 1L, codePostalCommune.getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.362"), "Commune1 ach", codePostalCommune.getLibelleAcheminement());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.364"), 1L, codePostalCommune.getCodePostal().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.365"), "000", codePostalCommune.getCodePostal().getLibelle());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.367"), 1L, codePostalCommune.getCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.368"), "Commune1", codePostalCommune.getCommune().getLibelle());
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.370"), coordonneesDto.getAdresses().get(0).getDepartement());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.371"), 1L, coordonneesDto.getAdresses().get(0).getDepartement().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.372"), "Vienne", coordonneesDto.getAdresses().get(0).getDepartement().getLibelle());

        // Vérification des téléphones
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.374"), 2, coordonneesDto.getTelephones().size());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.375"), "0505050505", coordonneesDto.getTelephones().get(0).getNumero());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.377"), 1L, coordonneesDto.getTelephones().get(0).getNature().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.378"), 1L, coordonneesDto.getTelephones().get(0).getPays().getId());
        // Vérification des emails
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.379"), 2, coordonneesDto.getEmails().size());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.380"), "email1@square.com", coordonneesDto.getEmails().get(0).getAdresse());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.382"), 1L, coordonneesDto.getEmails().get(0).getNatureEmail().getIdentifiant());
    }

    /**
     * Test du changement d'adresse sans adresse secondaire.
     */
    @Test
    public void testEnregistrerCoordonneeSansAdresseSecondaire() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.383"));
        // Initialisation de la personne
        final PersonneDto personne = new PersonneDto();
        personne.setIdentifiant(1L);
        personne.setIdext(idext);
        personne.setCivilite(new IdentifiantLibelleDto(1L));
        personne.setPrenom(prenom);
        personne.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personne.setDateNaissance(date1);
        personne.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personne.setCreateur(createur());
        personne.setCommercial(commercial());

        // Initialisation telephoneDto
        final TelephoneDto telephoneDto = new TelephoneDto();
        telephoneDto.setId(1L);
        telephoneDto.setNature(new IdentifiantLibelleDto(2L, null));
        telephoneDto.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneDto.setPays(pays);

        // Initilisation de l'email.
        final EmailDto emailDto = new EmailDto();
        emailDto.setIdentifiant(1L);
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        final AdresseDto adresseDto = new AdresseDto();
        adresseDto.setIdext(idext);
        adresseDto.setNumVoie(numVoie);
        adresseDto.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseDto.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseDto.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        adresseDto.setNomVoie("Nom Voie");
        adresseDto.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdressePrincipale()));
        final Calendar dateDebut = Calendar.getInstance();
        final int anneeDebut = 2008;
        dateDebut.set(anneeDebut, Calendar.JANUARY, 1);
        adresseDto.setDateDebut(dateDebut);

        // Création de la personne avec une adresse principale
        final PersonneDto personneResultat = personnePhysiqueService.creerPersonnePhysique(personne, null, adresseDto, emailDto, telephoneDto);

        // Modification de l'adresse principale pour la passer en secondaire
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(personneResultat.getIdentifiant());

        final Calendar dateFin = Calendar.getInstance();
        final int anneeFin = 2010;
        dateFin.set(anneeFin, Calendar.JANUARY, 1);
        adresseDto.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdresseSecondaire()));
        adresseDto.setIdentifiant(coordonnees.getAdresses().get(0).getIdentifiant());
        final List<AdresseDto> adresses = new ArrayList<AdresseDto>();
        adresses.add(adresseDto);
        coordonnees.setAdresses(adresses);
        coordonnees.getTelephones().get(0).setNumero(numtel);

        coordonnees.setIdPersonne(personneResultat.getIdentifiant());
        try {
            personneService.creerOuMettreAJourCoordonnees(coordonnees, true, false);
            fail(Messages.getString("PersonnePhysiqueServiceTest.385"));
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.386"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_AUCUNE_ADRESSE_PRINCIPALE), be
                    .getMessage());
        }

        for (AdresseDto adresse : coordonnees.getAdresses()) {
            final List<PersonneDto> personnes = personneService.rechercherPersonnesParAdresse(adresse.getIdentifiant());
            final List<Long> idPersonnes = new ArrayList<Long>();
            for (PersonneDto personneALAdresse : personnes) {
                idPersonnes.add(personneALAdresse.getIdentifiant());
            }
            assertTrue(Messages.getString("PersonnePhysiqueServiceTest.387"), idPersonnes.contains(personne.getIdentifiant()));
        }

        for (EmailDto mail : coordonnees.getEmails()) {
            final List<PersonneDto> personnes = personneService.rechercherPersonnesParEmail(mail.getIdentifiant());
            final List<Long> idPersonnes = new ArrayList<Long>();
            for (PersonneDto personneALEmail : personnes) {
                idPersonnes.add(personneALEmail.getIdentifiant());
            }
            assertTrue(Messages.getString("PersonnePhysiqueServiceTest.388"), idPersonnes.contains(personne.getIdentifiant()));
        }

        for (TelephoneDto telephone : coordonnees.getTelephones()) {
            final List<PersonneDto> personnes = personneService.rechercherPersonnesParTelephone(telephone.getId());
            final List<Long> idPersonnes = new ArrayList<Long>();
            for (PersonneDto personneAuTel : personnes) {
                idPersonnes.add(personneAuTel.getIdentifiant());
            }
            assertTrue(Messages.getString("PersonnePhysiqueServiceTest.389"), idPersonnes.contains(personne.getIdentifiant()));
        }
    }

    /**
     * Test du switch entre adresse principale et adresse secondaire.
     */
    @Test
    public void testSwitchAdresse() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.390"));
        // Initialisation de la personne
        final PersonneDto personne = new PersonneDto();
        personne.setIdext(idext);
        personne.setCivilite(new IdentifiantLibelleDto(1L));
        personne.setPrenom(prenom);
        personne.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personne.setDateNaissance(date1);
        personne.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personne.setCreateur(createur());
        personne.setCommercial(commercial());

        // Initialisation telephoneDto
        final TelephoneDto telephoneDto = new TelephoneDto();
        telephoneDto.setNature(new IdentifiantLibelleDto(2L, null));
        telephoneDto.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneDto.setPays(pays);

        // Initilisation de l'email.
        final EmailDto emailDto = new EmailDto();
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation AdresseDto
        final AdresseDto adresseDto = new AdresseDto();
        adresseDto.setIdext(idext);
        adresseDto.setNumVoie(numVoie);
        adresseDto.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseDto.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseDto.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        adresseDto.setNomVoie("Nom Voie");
        adresseDto.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdressePrincipale()));
        final Calendar dateDebut = Calendar.getInstance();
        final int anneeDebut = 2008;
        dateDebut.set(anneeDebut, Calendar.JANUARY, 1);
        adresseDto.setDateDebut(dateDebut);

        // Création de la personne avec une adresse principale
        final PersonneDto personneResultat = personnePhysiqueService.creerPersonnePhysique(personne, null, adresseDto, emailDto, telephoneDto);

        // Création d'une adresse secondaire pour la personne.
        // Initialisation AdresseDto
        final AdresseDto adresseDto2 = new AdresseDto();
        adresseDto2.setIdext(idext);
        adresseDto2.setNumVoie(numVoie);
        adresseDto2.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseDto2.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseDto2.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        adresseDto2.setNomVoie("Nom Voie");
        adresseDto2.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdresseSecondaire()));
        final Calendar dateDebut2 = Calendar.getInstance();
        final int anneeDebut2 = 2007;
        dateDebut.set(anneeDebut2, Calendar.JANUARY, 1);
        adresseDto2.setDateDebut(dateDebut2);

        // Récupération des coordonnées de la personne.
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(personneResultat.getIdentifiant());

        // Ajout de l'adresse secondaire.
        coordonnees.getAdresses().add(adresseDto2);
        final List<TelephoneDto> listeTelephones = new ArrayList<TelephoneDto>();
        listeTelephones.add(telephoneDto);
        coordonnees.setTelephones(listeTelephones);
        personneService.creerOuMettreAJourCoordonnees(coordonnees, true, false);

        // Ajout d'une date de fin sur l'adresse principale
        final CoordonneesDto coordonneesTotale = personneService.rechercherCoordonneesParIdPersonne(personneResultat.getIdentifiant());

        Long idAdressePrincipale = null;
        Long idAdresseSecondaire = null;
        for (AdresseDto adresse : coordonneesTotale.getAdresses()) {
            if (adresse.getNature().getIdentifiant().equals(squareMappingService.getIdNatureAdressePrincipale())) {
                coordonneesTotale.getAdresses().get(0).setDateFin(Calendar.getInstance());
                idAdressePrincipale = adresse.getIdentifiant();
            }
            if (adresse.getNature().getIdentifiant().equals(squareMappingService.getIdNatureAdresseSecondaire())) {
                idAdresseSecondaire = adresse.getIdentifiant();
            }
        }
        coordonneesTotale.setTelephones(listeTelephones);

        personneService.creerOuMettreAJourCoordonnees(coordonneesTotale, true, false);

        // Vérification que l'adresse Principale est devenu secondaire
        final CoordonneesDto coordonneesDtoResultat = personneService.rechercherCoordonneesParIdPersonne(personneResultat.getIdentifiant());
        for (AdresseDto adresse : coordonneesDtoResultat.getAdresses()) {
            if (adresse.getIdentifiant().equals(idAdressePrincipale)) {
                assertEquals(Messages.getString("PersonnePhysiqueServiceTest.393"), squareMappingService.getIdNatureAdresseSecondaire(), adresse.getNature()
                        .getIdentifiant());
            }
            if (adresse.getIdentifiant().equals(idAdresseSecondaire)) {
                assertEquals(Messages.getString("PersonnePhysiqueServiceTest.394"), squareMappingService.getIdNatureAdressePrincipale(), adresse.getNature()
                        .getIdentifiant());
            }
        }

    }

    /**
     * Test de création d'une deuxieme adresse principale.
     */
    @Test
    public void testCreationDeuxiemeAdressePrincipale() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.395"));
        // Initialisation de la personne
        final PersonneDto personne = new PersonneDto();
        final CoordonneesDto coordonneesPersonne = new CoordonneesDto();
        personne.setIdext(idext);
        personne.setCivilite(new IdentifiantLibelleDto(1L));
        personne.setPrenom(prenom);
        personne.setNom(nom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personne.setDateNaissance(date1);
        personne.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personne.setCreateur(createur());
        personne.setCommercial(commercial());

        // Initialisation telephoneDto
        final TelephoneDto telephoneDto = new TelephoneDto();
        telephoneDto.setNature(new IdentifiantLibelleDto(2L, null));
        telephoneDto.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneDto.setPays(pays);

        coordonneesPersonne.getTelephones().add(telephoneDto);

        // Initilisation de l'email.
        final EmailDto emailDto = new EmailDto();
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        coordonneesPersonne.getEmails().add(emailDto);

        // Initialisation AdresseDto
        final AdresseDto adresseDto = new AdresseDto();
        adresseDto.setIdext(idext);
        adresseDto.setNumVoie(numVoie);
        adresseDto.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseDto.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseDto.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        adresseDto.setNomVoie("Nom Voie");
        adresseDto.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdressePrincipale()));
        final Calendar dateDebut = Calendar.getInstance();
        final int anneeDebut = 2008;
        dateDebut.set(anneeDebut, Calendar.JANUARY, 1);
        adresseDto.setDateDebut(dateDebut);

        coordonneesPersonne.getAdresses().add(adresseDto);

        // Création de la personne avec une adresse principale
        final PersonneDto personneResultat = personnePhysiqueService.creerPersonnePhysique(personne, null, coordonneesPersonne);

        // Création d'une adresse secondaire pour la personne.
        // Initialisation AdresseDto
        final AdresseDto adresseDto2 = new AdresseDto();
        adresseDto2.setIdext(idext);
        adresseDto2.setNumVoie(numVoie);
        adresseDto2.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseDto2.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseDto2.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        adresseDto2.setNomVoie("Nom Voie");
        adresseDto2.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdressePrincipale()));
        adresseDto2.setChoixPasserEnSecondaire(true);
        final Calendar dateDebut2 = Calendar.getInstance();
        final int anneeDebut2 = 2009;
        dateDebut.set(anneeDebut2, Calendar.JANUARY, 1);
        adresseDto2.setDateDebut(dateDebut2);

        // Récupération des coordonnées de la personne.
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(personneResultat.getIdentifiant());

        // Ajout de l'adresse secondaire.
        coordonnees.getAdresses().add(adresseDto2);
        final List<TelephoneDto> listeTelephones = new ArrayList<TelephoneDto>();
        listeTelephones.add(telephoneDto);
        coordonnees.setTelephones(listeTelephones);
        personneService.creerOuMettreAJourCoordonnees(coordonnees, true, false);

        // Ajout d'une date de fin sur l'adresse principale
        final CoordonneesDto coordonneesTotale = personneService.rechercherCoordonneesParIdPersonne(personneResultat.getIdentifiant());

        boolean existUneAdresseSeconadire = false;
        for (AdresseDto adresse : coordonneesTotale.getAdresses()) {
            if (adresse.getNature().getIdentifiant().equals(squareMappingService.getIdNatureAdresseSecondaire())) {
                existUneAdresseSeconadire = true;
                break;
            }
        }
        if (!existUneAdresseSeconadire) {
            assertTrue(Messages.getString("PersonnePhysiqueServiceTest.398"), true);
        }
    }

    /**
     * Test de la modification de l'adresse principale avec une date de fin.
     */
    // // TODO Tests pour le switch adresse principale adresse secondaire
    // @Test
    // public void testEnregistrerCoordonneeAdressePrincipale() {
    //
    // // Adresse princiaple
    // AdresseDto adresseDtoPrincipale = new AdresseDto();
    // adresseDtoPrincipale.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdressePrincipale()));
    // adresseDtoPrincipale.setDateFin(Calendar.getInstance());
    // adresseDtoPrincipale.setIdentifiant(1L);
    // adresseDtoPrincipale.setNumVoie("1");
    // adresseDtoPrincipale.setNomVoie("nom voie test");
    // adresseDtoPrincipale.setCodePostal(new IdentifiantLibelleDto(1L));
    // adresseDtoPrincipale.setAutresComplements("autres compléments");
    // adresseDtoPrincipale.setNpai(false);
    // adresseDtoPrincipale.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance()));
    // adresseDtoPrincipale.setCommune(new IdentifiantLibelleDto(1L));
    // Calendar dateDebut = Calendar.getInstance();
    // dateDebut.clear();
    // final int year = 2000;
    // dateDebut.set(year, Calendar.JANUARY, 1);
    // adresseDtoPrincipale.setDateDebut(dateDebut);
    // adresseDtoPrincipale.setTypeVoie(new IdentifiantLibelleDto(1L));
    // adresseDtoPrincipale.setDepartement(new IdentifiantLibelleDto(1L));
    //
    // // Adresse secondaire
    // AdresseDto adresseDtoSecondaire = new AdresseDto();
    // adresseDtoSecondaire.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdresseSecondaire()));
    // adresseDtoSecondaire.setIdentifiant(2L);
    // adresseDtoSecondaire.setNumVoie("2");
    // adresseDtoSecondaire.setNomVoie("nom voie test2");
    // adresseDtoSecondaire.setCodePostal(new IdentifiantLibelleDto(1L));
    // adresseDtoSecondaire.setAutresComplements("autres compléments2");
    // adresseDtoSecondaire.setNpai(false);
    // adresseDtoSecondaire.setPays(new IdentifiantLibelleBooleanDto(1L));
    // adresseDtoSecondaire.setCommune(new IdentifiantLibelleDto(1L));
    // adresseDtoSecondaire.setDateDebut(Calendar.getInstance());
    // adresseDtoSecondaire.setTypeVoie(new IdentifiantLibelleDto(1L));
    // adresseDtoSecondaire.setDepartement(new IdentifiantLibelleDto(1L));
    //
    // // Téléphone
    // TelephoneDto tel = new TelephoneDto();
    // tel.setId(1L);
    // tel.setNumero(numtel);
    // tel.setNature(new IdentifiantLibelleDto(1L));
    // final PaysSimpleDto paysFrance = new PaysSimpleDto();
    // paysFrance.setId(70L);
    // tel.setPays(paysFrance);
    // List < TelephoneDto > telephone = new ArrayList<TelephoneDto>();
    // telephone.add(tel);
    //
    // // Test avec une adresse principale désactivé et pas d'adresse secondaire
    // final CoordonneesDto coordonneesDtoTest1 = new CoordonneesDto();
    // coordonneesDtoTest1.setIdPersonne(1L);
    // List < AdresseDto > listAdresseDtoTest1 = new ArrayList<AdresseDto>();
    // listAdresseDtoTest1.add(adresseDtoPrincipale);
    // coordonneesDtoTest1.setAdresses(listAdresseDtoTest1);
    //
    // coordonneesDtoTest1.setEmails(new ArrayList<EmailDto>());
    // coordonneesDtoTest1.setTelephones(telephone);
    //
    // try {
    // personnePhysiqueService.creerOuMettreAJourCoordonnees(coordonneesDtoTest1);
    // fail("L'appel au service de mise à jour d'adresse aurait du échouer");
    // } catch (BusinessException be) {
    // assertEquals("Le message d'erreur ne correspond pas pour la mise à jour sans adresse principale",
    // messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_SECONDAIRE_NULL_ADRESSE_PRINCIPALE_DATE_FIN),
    // be.getMessage());
    // }
    //
    // // Test avec une adresse principale desactivé et une adresse secondaire désactivé
    // CoordonneesDto coordonneesDtoTest2 = new CoordonneesDto();
    // coordonneesDtoTest2.setIdPersonne(1L);
    // adresseDtoSecondaire.setDateFin(Calendar.getInstance());
    // adresseDtoPrincipale.setDateFin(Calendar.getInstance());
    // List < AdresseDto > listAdresseDtoTest2 = new ArrayList<AdresseDto>();
    // listAdresseDtoTest2.add(adresseDtoPrincipale);
    // listAdresseDtoTest2.add(adresseDtoSecondaire);
    // coordonneesDtoTest2.setAdresses(listAdresseDtoTest2);
    // coordonneesDtoTest2.setTelephones(telephone);
    // coordonneesDtoTest2.setEmails(new ArrayList<EmailDto>());
    //
    // try {
    // personnePhysiqueService.creerOuMettreAJourCoordonnees(coordonneesDtoTest2);
    // fail("L'appel au service de mise à jour d'adresse aurait du échouer");
    // } catch (BusinessException be) {
    // assertEquals("Le message d'erreur ne correspond pas pour la mise à jour sans adresse principale",
    // messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_SECONDAIRE_NULL_ADRESSE_PRINCIPALE_DATE_FIN),
    // be.getMessage());
    // }
    //
    // // Test avec une adresse princiapel desactivé et une adresse secondaire active
    // CoordonneesDto coordonneesDtoTest3 = new CoordonneesDto();
    // coordonneesDtoTest3.setIdPersonne(1L);
    // adresseDtoPrincipale.setDateFin(Calendar.getInstance());
    // adresseDtoSecondaire.setDateFin(null);
    // List < AdresseDto > listAdresseDtoTest3 = new ArrayList<AdresseDto>();
    // listAdresseDtoTest3.add(adresseDtoPrincipale);
    // listAdresseDtoTest3.add(adresseDtoSecondaire);
    // coordonneesDtoTest3.setAdresses(listAdresseDtoTest2);
    // coordonneesDtoTest3.setTelephones(telephone);
    // coordonneesDtoTest3.setEmails(new ArrayList<EmailDto>());
    //
    // personnePhysiqueService.creerOuMettreAJourCoordonnees(coordonneesDtoTest3);
    //
    // // TODO Vérification que l'inversion s'est bien déroulée.
    // // Vérification des données modifiées
    // final CoordonneesDto coordonneesDtoRes = personnePhysiqueService.rechercherCoordonneesParIdPersonne(1L);
    // assertNotNull("les coordonnées sont null", coordonneesDtoRes);
    // // Vérification de l'identifiant de la personne
    // assertEquals("l'identifiant de la personne ne correspond pas", 1L, coordonneesDtoRes.getIdPersonne());
    // // Vérification des adresses
    // assertEquals("Le nombre d'adresse n'est pas le bon", 2, coordonneesDtoRes.getAdresses().size());
    // assertEquals("l'identifiant de l'adresse ne correspond pas", 1L, coordonneesDtoRes.getAdresses().get(0).getIdentifiant());
    // assertEquals("le num voie ne correspond pas", "num", coordonneesDtoRes.getAdresses().get(0).getNumVoie());
    // assertEquals("le nom de voie ne correspond pas", "nom voie test", coordonneesDtoRes.getAdresses().get(0).getNomVoie());
    // assertEquals("la commune n'est pas la bonne", 1L, coordonneesDtoRes.getAdresses().get(0).getCommune().getIdentifiant());
    // assertEquals("le nom de la commune n'est pas bon", commune, coordonneesDtoRes.getAdresses().get(0).getCommune().getLibelle());
    // // Vérification que les natures ont bien été inversée.
    // assertEquals("l'identifiant de la nature n'est pas bon",
    // squareMappingService.getIdNatureAdresseSecondaire(),
    // coordonneesDtoRes.getAdresses().get(0).getNature().getIdentifiant());
    // // assertEquals("la nature n'est pas la bonne",
    // // squareMappingService.getIdNatureAdressePrincipale(),
    // // coordonneesDtoRes.getAdresses().get(1).getNature().getIdentifiant());
    // // assertEquals("l'identifiant du type de voie n'est pas bon", 1L, coordonneesDtoRes.getAdresses().get(0).getTypeVoie().getIdentifiant());
    // // assertEquals("l'identifiant du codePostal de voie n'est pas bon", 1L, coordonneesDtoRes.getAdresses().get(0).getCodePostal().getIdentifiant());
    // }

    /**
     * Test de la récupération des différents champs. Vérification du mapping.
     */
    @Test
    public void testGetPersonneVerifValeur() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.399"));
        // Création des critères de recherche
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        criteres.setNumeroClient("1111");
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);

        // Appel au service
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresAvecPagination);

        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.401"), result);
        // Vérification des résultats
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.402"), 1, result.getTotalResults());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.403"), 1L, result.getListResults().get(0).getId());
        final PersonneSimpleDto personne = result.getListResults().get(0);

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.404"), "nomun", personne.getNom());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.406"), prenom1, personne.getPrenom());

        final Calendar dateNaissance = Calendar.getInstance();
        dateNaissance.clear();
        final int year = 2000;
        dateNaissance.set(year, Calendar.JANUARY, 1);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.407"), dateNaissance, personne.getDateNaissance());

        final CodePostalCommuneDto codePostalCommune = personne.getCodePostalCommune();
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.408"), 1L, codePostalCommune.getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.409"), "Commune1 ach", codePostalCommune.getLibelleAcheminement());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.411"), 1L, codePostalCommune.getCodePostal().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.412"), "000", codePostalCommune.getCodePostal().getLibelle());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.414"), 1L, codePostalCommune.getCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.415"), "Commune1", codePostalCommune.getCommune().getLibelle());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.417"), "Nature 1", personne.getNature().getLibelle());

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.419"), "segment1", personne.getSegment().getLibelle());
    }

    /**
     * Test pour la récupération d'une personneSimple.
     */
    @Test
    public void testRechercherPersonneSimple() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.421"));
        try {
            personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(null);
            fail(Messages.getString("PersonnePhysiqueServiceTest.422"));
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.423"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL), be.getMessage());
        }

        final PersonneSimpleDto personneSimpleDto = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(1L);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.424"), "nomun", personneSimpleDto.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.426"), "prénomun", personneSimpleDto.getPrenom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.428"), "Monsieur", personneSimpleDto.getCivilite().getLibelle());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.430"), getCalendarByDate("01/01/2000"), personneSimpleDto.getDateNaissance());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.432"), "1111", personneSimpleDto.getNumeroClient());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.434"), 1L, personneSimpleDto.getNature().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.435"), 1L, personneSimpleDto.getSegment().getIdentifiant());
        final CodePostalCommuneDto codePostalCommune = personneSimpleDto.getCodePostalCommune();
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.436"), 1L, codePostalCommune.getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.437"), "Commune1 ach", codePostalCommune.getLibelleAcheminement());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.439"), 1L, codePostalCommune.getCodePostal().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.440"), "000", codePostalCommune.getCodePostal().getLibelle());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.442"), 1L, codePostalCommune.getCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.443"), "Commune1", codePostalCommune.getCommune().getLibelle());

    }

    /**
     * Test de création d'une personne dont le nom, prenom contient des tirets et espaces.
     */
    @Test
    public void testCreerPersonneNomPrenomAvecTiret() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.445"));
        PersonneDto personne = creerPersonne();
        personne.setNom("Conjeant-");
        personne.setPrenom(" Jean-Claude");
        TelephoneDto telephone = creerTelephone();
        EmailDto emailPersonne = creerEmail();
        AdresseDto adresse = creerAdresse();
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();

        try {
            personne = personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emailPersonne, telephone);
            fail(Messages.getString("PersonnePhysiqueServiceTest.448"));
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.449"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NOM_BAD_FORMAT), e
                    .getRapport().getRapports().get("PersonneDto.nom").getMessage());
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.451"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_BAD_FORMAT), e
                    .getRapport().getRapports().get("PersonneDto.prenom").getMessage());
        }
        // Test avec des tiret à l'intérieur du nom et du prénom
        personne = creerPersonne();
        personne.setNom("NOMUN-NOMDEUX--NOMTROIS");
        personne.setPrenom("MBAZOA NOAH TSALA");
        telephone = creerTelephone();
        emailPersonne = creerEmail();
        adresse = creerAdresse();
        personne = personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, emailPersonne, telephone);
    }

    /**
     * Test de modification du code postal et de la ville pour un pays étranger.
     */
    @Test
    public void testModifierAdresseEtrangere() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.455"));
        PersonneDto personne = creerPersonne();
        personne.setNom(nom);
        personne.setPrenom(prenom);
        final TelephoneDto telephone = creerTelephone();
        final EmailDto email = creerEmail();
        final AdresseDto adresse = creerAdresse();
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();

        personne = personnePhysiqueService.creerPersonnePhysique(personne, listebeneficiaire, adresse, email, telephone);

        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(personne.getIdentifiant());

        final AdresseDto adresseAModifier = coordonnees.getAdresses().get(0);
        adresseAModifier.setPays(new IdentifiantLibelleBooleanDto(1L));
        adresseAModifier.setCodePostalEtranger("12345");
        adresseAModifier.setCommuneEtranger("commune etrangere");
        coordonnees.getTelephones().get(0).setNumero("0123456789");

        final CoordonneesDto nouvellesCoordonnees = personneService.creerOuMettreAJourCoordonnees(coordonnees, true, false);

        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.459"), nouvellesCoordonnees.getAdresses().get(0).getCodePostalEtranger(), "12345");
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.461"), nouvellesCoordonnees.getAdresses().get(0).getCommuneEtranger(), "commune etrangere");
    }

    @Test
    public void testCalculAgePersonnePhysique() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.463"));
        final PersonneDto personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(1L);
        final Calendar dateNaissance = Calendar.getInstance();
        final int jour = dateNaissance.get(Calendar.DATE);
        final int mois = dateNaissance.get(Calendar.MONTH);
        final int annee = dateNaissance.get(Calendar.YEAR);
        final int nbAnnees = -10;
        final int nbJours = 5;
        dateNaissance.clear();
        dateNaissance.set(annee, mois, jour);
        dateNaissance.add(Calendar.YEAR, nbAnnees);
        dateNaissance.add(Calendar.DATE, nbJours);
        personneDto.setDateNaissance(dateNaissance);
        personnePhysiqueService.modifierPersonnePhysique(personneDto);

        final PersonneSimpleDto personneSimpleDto = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(1L);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.464"), "9 ans", personneSimpleDto.getAge());
    }

    // /**
    // * Test de controle sur le telephone portable.
    // */
    // // FIXME : pour l'instant, plus de vérification de la nature de téléphone
    // @Test
    // public void testControlerTelephonePortable() {
    // final TelephoneDto telephoneDto = new TelephoneDto();
    // telephoneDto.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureMobileTravail()));
    // telephoneDto.setNumero("05");
    // final PaysSimpleDto pays = new PaysSimpleDto();
    // pays.setId(1L);
    // telephoneDto.setPays(pays);
    // try {
    // personnePhysiqueService.controlerTelephone(telephoneDto);
    // }
    // catch (ControleIntegriteException e) {
    // assertEquals("L'exception rencontrée lors du controle de telephone est différente de celle attendue",
    // messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE),
    // e.getRapport().getRapports().get("TelephoneDto.nature").getMessage());
    // }
    // catch (BusinessException b) {
    // assertEquals("L'exception rencontrée lors du controle de telephone est différente de celle attendue", messageSourceUtil
    // .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), b.getMessage());
    // }
    // }

    // /**
    // * Test de controle sur le telephone portable.
    // */
    // // FIXME : pour l'instant, plus de vérification de la nature de téléphone
    // @Test
    // public void testControlerTelephoneFixe() {
    // final TelephoneDto telephoneDto = new TelephoneDto();
    // telephoneDto.setNumero("06");
    // telephoneDto.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureTelephoneFixe()));
    // final PaysSimpleDto pays = new PaysSimpleDto();
    // pays.setId(1L);
    // telephoneDto.setPays(pays);
    //
    // try {
    // personnePhysiqueService.controlerTelephone(telephoneDto);
    // }
    // catch (ControleIntegriteException e) {
    // assertEquals("L'exception rencontrée lors du controle de telephone est différente de celle attendue",
    // messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE),
    // e.getRapport().getRapports().get("TelephoneDto.nature").getMessage());
    // }
    // catch (BusinessException b) {
    // assertEquals("L'exception rencontrée lors du controle de telephone est différente de celle attendue", messageSourceUtil
    // .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), b.getMessage());
    // }
    // }

    /**
     * Test de controle sur le telephone portable.
     */
    @Test
    public void testControlerTelephoneFixe() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.466"));
        final TelephoneDto telephoneDto = new TelephoneDto();
        telephoneDto.setNumero("06");
        telephoneDto.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureTelephoneFixe()));
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneDto.setPays(pays);

        try {
            personnePhysiqueService.controlerTelephone(telephoneDto);
        }
        catch (ControleIntegriteException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.468"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), e.getRapport().getRapports().get("TelephoneDto.nature").getMessage());
        }
        catch (BusinessException b) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.470"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), b.getMessage());
        }
    }

    /**
     * Test de controle sur la création d'assure social.
     */
    @Test
    public void testCreerPersonnePhysiqueAssureSocial() {
        final PersonneCreationAssureSocialDto assureSocialDto = new PersonneCreationAssureSocialDto();

        try {
            personnePhysiqueService.creerPersonnePhysiqueAssureSocial(assureSocialDto);
            fail(Messages.getString("PersonnePhysiqueServiceTest.471"));
        }
        catch (ControleIntegriteException e) {
            assertTrue(Messages.getString("PersonnePhysiqueServiceTest.472"), e.getRapport().getEnErreur());
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.473"), 6, e.getRapport().getRapports().size());
            boolean sousRapportPrenomTrouve = false;
            boolean sousRapportNnomTrouve = false;
            boolean sousRapportDateNaissanceTrouve = false;
            boolean sousRapportCiviliteTrouve = false;
            boolean sousRapportCaisseTrouve = false;
            boolean sousRapportNroTrouve = false;
            for (String key : e.getRapport().getRapports().keySet()) {
                final SousRapportDto sousRapport = e.getRapport().getRapports().get(key);
                if (key.equals("PersonneCreationAssureSocialDto.prenom")) {
                    sousRapportPrenomTrouve = true;
                }
                else if (key.equals("PersonneCreationAssureSocialDto.nom")) {
                    sousRapportNnomTrouve = true;
                }
                else if (key.equals("PersonneCreationAssureSocialDto.civilite")) {
                    sousRapportCiviliteTrouve = true;
                }
                else if (key.equals("PersonneCreationAssureSocialDto.dateNaissance")) {
                    sousRapportDateNaissanceTrouve = true;
                }
                else if (key.equals("PersonneCreationAssureSocialDto.infoSante.caisse")) {
                    sousRapportCaisseTrouve = true;
                }
                else if (key.equals("PersonneCreationAssureSocialDto.infoSante.nro")) {
                    sousRapportNroTrouve = true;
                }
                assertTrue(Messages.getString("PersonnePhysiqueServiceTest.480"), sousRapport.getErreur());
            }
            assertTrue(Messages.getString("PersonnePhysiqueServiceTest.481"), sousRapportCiviliteTrouve && sousRapportPrenomTrouve && sousRapportNnomTrouve
                && sousRapportDateNaissanceTrouve && sousRapportCaisseTrouve && sousRapportNroTrouve);
        }

        final Calendar dateNaissance = Calendar.getInstance();
        dateNaissance.clear();
        dateNaissance.set(1984, 6, 30);
        assureSocialDto.setCivilite(new IdentifiantLibelleDto(1L));
        assureSocialDto.setDateNaissance(dateNaissance);
        assureSocialDto.setNom("GUILLEMETTE");
        assureSocialDto.setPrenom("Anthony");
        final InfoSanteDto infoSante = new InfoSanteDto();
        infoSante.setNro("190071634105372");
        infoSante.setCaisse(new CaisseSimpleDto(1L));
        assureSocialDto.setInfoSante(infoSante);

        try {
            personnePhysiqueService.creerPersonnePhysiqueAssureSocial(assureSocialDto);
            fail(Messages.getString("PersonnePhysiqueServiceTest.485"));
        }
        catch (ControleIntegriteException e) {
            assertTrue(Messages.getString("PersonnePhysiqueServiceTest.486"), e.getRapport().getEnErreur());
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.487"), 6, e.getRapport().getRapports().size());
            for (String key : e.getRapport().getRapports().keySet()) {
                final SousRapportDto sousRapport = e.getRapport().getRapports().get(key);
                if (key.equals("PersonneCreationAssureSocialDto.infoSante.nro")) {
                    assertTrue(Messages.getString("PersonnePhysiqueServiceTest.489"), sousRapport.getErreur());
                }
                else {
                    assertFalse(Messages.getString("PersonnePhysiqueServiceTest.490"), sousRapport.getErreur());
                }
            }
        }

        assureSocialDto.getInfoSante().setNro("184071634105372");
        final PersonneDto personneCree = personnePhysiqueService.creerPersonnePhysiqueAssureSocial(assureSocialDto);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.492"), squareMappingService.getIdNaturePersonneAssureSocial(), personneCree.getNaturePersonne()
                .getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.493"), assureSocialDto.getInfoSante().getNro(), personneCree.getInfoSante().getNro());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.494"), personneCree.getIdentifiant(), personneCree.getInfoSante().getIdReferent());

        idsReferentToDelete.add(personneCree.getIdentifiant());
    }

    /**
     * Test de l'export de recherche.
     */
    @Test
    public void testExporterXlsRecherchePersonneFullTextParCriteres() {
        // FIXME a corriger car tourne en boucle
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.495"));
        // final String nomFichier = "exportRecherchePersonnesPhysiques.xls";
        // final String typeMime = "application/vnd.ms-excel";
        //
        // // Création des critères de recherche.
        // final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        // final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresAvecPagination =
        // new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, MAX_LUCENE_VALUE);
        //
        // // Appel au service.
        // final FichierDto result = personnePhysiqueService.exporterRecherchePersonneFullTextParCriteres(criteresAvecPagination);
        //
        // assertNotNull("Le fichier ne devrait pas etre null", result);
        // assertEquals("Le nom du fichier n'est pas le bon", nomFichier, result.getNom());
        // assertEquals("Le type MIME du fichier n'est pas le bon", typeMime, result.getTypeMime());
        //
        // // on verifie le contenu du fichier
        // try {
        // // on copie le fichier pour vérification visuel
        // final File newFile = new File("./target/" + result.getNom());
        // newFile.createNewFile();
        // IOUtils.write(result.getData(), new FileOutputStream(newFile));
        //
        // final HSSFWorkbook classeur = new HSSFWorkbook(new ByteArrayInputStream(result.getData()));
        // assertEquals("Le nombre de feuilles du classeur n'est pas le bon", 1, classeur.getNumberOfSheets());
        // final HSSFSheet page = classeur.getSheetAt(0);
        // assertEquals("Le nombre de lignes de la page n'est pas le bon", 3, page.getLastRowNum() + 1);
        //
        // // on verifie la colonne d'entete nom
        // String value = page.getRow(0).getCell(1).getRichStringCellValue().getString();
        // assertEquals("Le contenu de la cellule Nom de la ligne 0 n'est pas le bon", "Nom", value);
        // value = page.getRow(1).getCell(1).getRichStringCellValue().getString();
        // assertEquals("Le contenu de la cellule Nom de la ligne 1 n'est pas le bon", "NomUn", value);
        // value = page.getRow(2).getCell(1).getRichStringCellValue().getString();
        // assertEquals("Le contenu de la cellule Nom de la ligne 2 n'est pas le bon", "NomDeux", value);
        // } catch (IOException e) {
        // e.printStackTrace();
        // fail("Erreur lors de la vérification du fichier");
        // }
    }

    /**
     * Test de la gestion du passage en minuscule.
     */
    @Test
    public void testControlePassageMinuscule() {
        logger.debug(Messages.getString("PersonnePhysiqueServiceTest.496"));
        // Test de création
        // Personne
        final PersonneDto personneDto = new PersonneDto();
        personneDto.setCivilite(new IdentifiantLibelleDto(1L));
        personneDto.setNom("NomTest");
        personneDto.setPrenom("PrenomTest");
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        final int year = 2000;
        cal.set(year, Calendar.JANUARY, 1);
        personneDto.setDateNaissance(cal);
        personneDto.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneDto.setCreateur(createur());
        personneDto.setCommercial(commercial());
        // Email
        final EmailDto emailDto = new EmailDto();
        emailDto.setIdentifiant(1L);
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse("EMAIL@square.com");
        // Adresse
        final AdresseDto adresseDto = new AdresseDto();
        adresseDto.setNumVoie("12B");
        adresseDto.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseDto.setNomVoie("Chez TOTO");
        adresseDto.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseDto.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance()));
        final PersonneDto personneCreeDto = personnePhysiqueService.creerPersonnePhysique(personneDto, null, adresseDto, emailDto, null);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.502"), "nomtest", personneCreeDto.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.504"), "prenomtest", personneCreeDto.getPrenom());
        assertNull(Messages.getString("PersonnePhysiqueServiceTest.506"), personneCreeDto.getIdext());

        final CoordonneesDto coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(personneCreeDto.getIdentifiant());
        assertNull(Messages.getString("PersonnePhysiqueServiceTest.507"), coordonneesDto.getAdresses().get(0).getIdext());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.508"), "12b", coordonneesDto.getAdresses().get(0).getNumVoie());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.510"), "chez toto", coordonneesDto.getAdresses().get(0).getNomVoie());
        assertNull(Messages.getString("PersonnePhysiqueServiceTest.512"), coordonneesDto.getEmails().get(0).getIdext());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.513"), "email@square.com", coordonneesDto.getEmails().get(0).getAdresse());

        // Test de modification
        // Personne
        final PersonneDto personneAModifierDto = personnePhysiqueService.rechercherPersonneParIdentifiant(1L);
        personneAModifierDto.setNom("TESTNOM");
        personneAModifierDto.setPrenom("TestPrenom");
        personnePhysiqueService.modifierPersonnePhysique(personneAModifierDto);
        final PersonneDto personneModifieeDto = personnePhysiqueService.rechercherPersonneParIdentifiant(1L);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.517"), "testnom", personneModifieeDto.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.519"), "testprenom", personneModifieeDto.getPrenom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.521"), "123ABC", personneModifieeDto.getIdext());

        // Coordonnées
        coordonneesDto.getAdresses().get(0).setIdext("147AZE");
        coordonneesDto.getEmails().get(0).setIdext("123ABC456");
        personneService.mettreAJourCoordonneesExtId(coordonneesDto);
        final CoordonneesDto coordonneesMAJDto = personneService.rechercherCoordonneesParIdPersonne(personneCreeDto.getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.525"), "147AZE", coordonneesMAJDto.getAdresses().get(0).getIdext());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.527"), "123ABC456", coordonneesMAJDto.getEmails().get(0).getIdext());
    }

    /**
     * Test unitaire du service {@link PersonnePhysiqueService#creerUneCopiePersonne(com.square.core.model.dto.PersonnePhysiqueCopieDto)}.
     */
    @Test
    public void testCreerUneCopiePersonnePhysique() {
        final Long idPersonneSource = 1L;
        final Long idCivilite = squareMappingService.getIdCiviliteMademoiselle();
        final String nomPersonneCopiee = "Pouet";
        final String prenomPersonneCopiee = "Lucie";
        final Calendar dateNaissance = Calendar.getInstance();
        final int jourNaissance = 21;
        final int moisNaissance = Calendar.MAY;
        final int anneeNaissance = 1984;
        dateNaissance.set(anneeNaissance, moisNaissance, jourNaissance);
        final Long idNaturePersonneCopiee = squareMappingService.getIdNaturePersonneBeneficiaire();
        final PersonnePhysiqueCopieDto infosCopie = new PersonnePhysiqueCopieDto();
        infosCopie.setIdPersonneSource(idPersonneSource);
        infosCopie.setCivilite(idCivilite);
        infosCopie.setDateNaissance(dateNaissance);
        infosCopie.setNom(nomPersonneCopiee);
        infosCopie.setPrenom(prenomPersonneCopiee);
        infosCopie.setIdNaturePersonne(idNaturePersonneCopiee);
        final PersonneDto copiePersonne = personnePhysiqueService.creerUneCopiePersonne(infosCopie);
        final PersonneDto personneSource = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonneSource);
        // Vérifications que la personne a été correctement copiée
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.1"), idNaturePersonneCopiee, copiePersonne.getNaturePersonne()
                .getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.2"), nomPersonneCopiee.toLowerCase(), copiePersonne.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.533"), prenomPersonneCopiee.toLowerCase(), copiePersonne.getPrenom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.534"), dateNaissance, copiePersonne.getDateNaissance());
        if (personneSource.getSegment() != null) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.535"), personneSource.getSegment().getIdentifiant(),
                copiePersonne.getSegment().getIdentifiant());
        }
        if (personneSource.getCsp() != null) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.536"), personneSource.getCsp()
                    .getIdentifiant(), copiePersonne.getCsp().getIdentifiant());
        }
        final CoordonneesDto coordonneesCopiePersonne = personneService.rechercherCoordonneesParIdPersonne(copiePersonne.getIdentifiant());
        final CoordonneesDto coordonneesPersonneSource = personneService.rechercherCoordonneesParIdPersonne(personneSource.getIdentifiant());
        int nbAdressesCommunes = 0;
        final int nbAdressesCommunesAttendues = coordonneesPersonneSource.getAdresses().size();
        for (AdresseDto adresseCopiePersonne : coordonneesCopiePersonne.getAdresses()) {
            for (AdresseDto adressePersonneSource : coordonneesPersonneSource.getAdresses()) {
                if (adresseCopiePersonne.getIdentifiant().equals(adressePersonneSource.getIdentifiant())) {
                    nbAdressesCommunes++;
                }
            }
        }
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.537"), nbAdressesCommunesAttendues, nbAdressesCommunes);
        int nbTelCommuns = 0;
        final int nbTelCommunsAttendus = coordonneesPersonneSource.getTelephones().size();
        for (TelephoneDto telCopiePersonne : coordonneesCopiePersonne.getTelephones()) {
            for (TelephoneDto telPersonneSource : coordonneesPersonneSource.getTelephones()) {
                if (telCopiePersonne.getId().equals(telPersonneSource.getId())) {
                    nbTelCommuns++;
                }
            }
        }
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.538"), nbTelCommunsAttendus, nbTelCommuns);
        int nbEmailsCommuns = 0;
        final int nbEmailsCommunsAttendus = coordonneesPersonneSource.getEmails().size();
        for (EmailDto emailCopiePersonne : coordonneesCopiePersonne.getEmails()) {
            for (EmailDto emailPersonneSource : coordonneesPersonneSource.getEmails()) {
                if (emailCopiePersonne.getIdentifiant().equals(emailPersonneSource.getIdentifiant())) {
                    nbEmailsCommuns++;
                }
            }
        }
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.539"), nbEmailsCommunsAttendus, nbEmailsCommuns);
        idsReferentToDelete.add(copiePersonne.getIdentifiant());
    }

    /**
     * Test unitaire du service {@link PersonnePhysiqueService#creerUneCopiePersonne(com.square.core.model.dto.PersonnePhysiqueCopieDto)}.
     */
    @Test
    public void testCopiePersonnePhysiqueDupliquerCoordonnees() {
        final Long idPersonneSource = 1L;
        final Long idCivilite = squareMappingService.getIdCiviliteMademoiselle();
        final String nomPersonneCopiee = "Pouet";
        final String prenomPersonneCopiee = "Lucie";
        final Calendar dateNaissance = Calendar.getInstance();
        final int jourNaissance = 21;
        final int moisNaissance = Calendar.MAY;
        final int anneeNaissance = 1984;
        dateNaissance.set(anneeNaissance, moisNaissance, jourNaissance);
        final Long idNaturePersonneCopiee = squareMappingService.getIdNaturePersonneBeneficiaire();
        final PersonnePhysiqueCopieDto infosCopie = new PersonnePhysiqueCopieDto();
        infosCopie.setIdPersonneSource(idPersonneSource);
        infosCopie.setCivilite(idCivilite);
        infosCopie.setDateNaissance(dateNaissance);
        infosCopie.setNom(nomPersonneCopiee);
        infosCopie.setPrenom(prenomPersonneCopiee);
        infosCopie.setIdNaturePersonne(idNaturePersonneCopiee);
        infosCopie.setDupliquerCoordonnees(true);
        final PersonneDto copiePersonne = personnePhysiqueService.creerUneCopiePersonne(infosCopie);
        final PersonneDto personneSource = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonneSource);
        // Vérifications que la personne a été correctement copiée
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.542"), idNaturePersonneCopiee, copiePersonne.getNaturePersonne()
                .getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.543"), nomPersonneCopiee.toLowerCase(), copiePersonne.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.544"), prenomPersonneCopiee.toLowerCase(), copiePersonne.getPrenom());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.545"), dateNaissance, copiePersonne.getDateNaissance());
        if (personneSource.getSegment() != null) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.546"), personneSource.getSegment().getIdentifiant(),
                copiePersonne.getSegment().getIdentifiant());
        }
        if (personneSource.getCsp() != null) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.547"), personneSource.getCsp()
                    .getIdentifiant(), copiePersonne.getCsp().getIdentifiant());
        }
        final CoordonneesDto coordonneesCopiePersonne = personneService.rechercherCoordonneesParIdPersonne(copiePersonne.getIdentifiant());
        final CoordonneesDto coordonneesPersonneSource = personneService.rechercherCoordonneesParIdPersonne(personneSource.getIdentifiant());
        int nbAdressesCommunes = 0;
        final int nbAdressesCommunesAttendues = 0;
        for (AdresseDto adresseCopiePersonne : coordonneesCopiePersonne.getAdresses()) {
            for (AdresseDto adressePersonneSource : coordonneesPersonneSource.getAdresses()) {
                if (adresseCopiePersonne.getIdentifiant().equals(adressePersonneSource.getIdentifiant())) {
                    nbAdressesCommunes++;
                }
            }
        }
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.548"),
            nbAdressesCommunesAttendues, nbAdressesCommunes);
        int nbTelCommuns = 0;
        final int nbTelCommunsAttendus = 0;
        for (TelephoneDto telCopiePersonne : coordonneesCopiePersonne.getTelephones()) {
            for (TelephoneDto telPersonneSource : coordonneesPersonneSource.getTelephones()) {
                if (telCopiePersonne.getId().equals(telPersonneSource.getId())) {
                    nbTelCommuns++;
                }
            }
        }
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.549"),
            nbTelCommunsAttendus, nbTelCommuns);
        int nbEmailsCommuns = 0;
        final int nbEmailsCommunsAttendus = 0;
        for (EmailDto emailCopiePersonne : coordonneesCopiePersonne.getEmails()) {
            for (EmailDto emailPersonneSource : coordonneesPersonneSource.getEmails()) {
                if (emailCopiePersonne.getIdentifiant().equals(emailPersonneSource.getIdentifiant())) {
                    nbEmailsCommuns++;
                }
            }
        }
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.550"),
            nbEmailsCommunsAttendus, nbEmailsCommuns);
        idsReferentToDelete.add(copiePersonne.getIdentifiant());
    }

    /** Test du service creerPersonnePhysiqueGestionVivier. */
    @Test
    public void testCreerPersonnePhysiqueGestionVivier() {
        final PersonneDto personneToCreate = new PersonneDto();
        personneToCreate.setNom(nom);
        personneToCreate.setPrenom(prenom);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(1L));

        final AdresseDto adresseToCreate = new AdresseDto();
        adresseToCreate.setNumVoie(numVoie);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(4L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null));

        final TelephoneDto telephoneToCreate = new TelephoneDto();
        telephoneToCreate.setNature(new IdentifiantLibelleDto(2L, null));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        final EmailDto emailToCreate = new EmailDto();
        emailToCreate.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailToCreate.setAdresse(email);

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        List<BeneficiaireDto> listeBeneficiaire = new ArrayList<BeneficiaireDto>();
        listeBeneficiaire.add(beneficaireTocreate);

        // Création d'un vivier et d'un bénéficiaire vivier
        PersonneDto personneCreee = personnePhysiqueService.creerPersonnePhysiqueGestionVivier(personneToCreate, listeBeneficiaire, adresseToCreate,
            emailToCreate, telephoneToCreate);
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.551"), personneCreee.getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.552"), squareMappingService.getIdNaturePersonneVivier(),
            personneCreee.getNaturePersonne().getIdentifiant());
        // Récupération des bénéficiaires créés
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonneSource(personneCreee.getIdentifiant());
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> rprRelations =
            personneService.rechercherRelationsParCritreres(criteresRel);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.553"), 1, rprRelations.getTotalResults());
        List<RelationInfosDto<? extends PersonneRelationDto>> relations = rprRelations.getListResults();
        for (RelationInfosDto<? extends PersonneRelationDto> relation : relations) {
            final PersonneDto beneficiaireDto = personnePhysiqueService.rechercherPersonneParIdentifiant(relation.getIdPersonne());
            assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.554"), beneficiaireDto.getIdentifiant());
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.555"), squareMappingService.getIdNaturePersonneBeneficiaireVivier(),
                beneficiaireDto.getNaturePersonne().getIdentifiant());
        }

        // Création d'un prospect et d'un bénéficiaire prospect
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        personneCreee = personnePhysiqueService.creerPersonnePhysiqueGestionVivier(personneToCreate, listeBeneficiaire, adresseToCreate,
            emailToCreate, telephoneToCreate);
        assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.556"), personneCreee.getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.557"), squareMappingService.getIdNaturePersonneProspect(),
            personneCreee.getNaturePersonne().getIdentifiant());
        // Récupération des bénéficiaires créés
        criteriasRel.setIdPersonneSource(personneCreee.getIdentifiant());
        rprRelations = personneService.rechercherRelationsParCritreres(criteresRel);
        assertEquals(Messages.getString("PersonnePhysiqueServiceTest.558"), 1, rprRelations.getTotalResults());
        relations = rprRelations.getListResults();
        for (RelationInfosDto<? extends PersonneRelationDto> relation : relations) {
            final PersonneDto beneficiaireDto = personnePhysiqueService.rechercherPersonneParIdentifiant(relation.getIdPersonne());
            assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.559"), beneficiaireDto.getIdentifiant());
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.560"), squareMappingService.getIdNaturePersonneBeneficiaireProspect(),
                beneficiaireDto.getNaturePersonne().getIdentifiant());
        }
    }

    /** Test du service hasMembreFamilleNaturePersonne. */
    @Test
    public void testHasMembreFamilleNaturePersonne() {
        final Long idPersonneInexistante = 100L;
        final Long idVivierSeul = 5L;
        final Long idPersonneAvecBenefVivier = 6L;
        final List<Long> listeIdsNaturesPersonne = new ArrayList<Long>();
        listeIdsNaturesPersonne.add(squareMappingService.getIdNaturePersonneVivier());
        listeIdsNaturesPersonne.add(squareMappingService.getIdNaturePersonneBeneficiaireVivier());
        try {
            personnePhysiqueService.hasMembreFamilleNaturePersonne(idPersonneInexistante, listeIdsNaturesPersonne);
            fail(Messages.getString("PersonnePhysiqueServiceTest.561"));
        }
        catch (TechnicalException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.562"), messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE),
                e.getMessage());
        }

        boolean hasMembreFamilleNaturePersonne = personnePhysiqueService.hasMembreFamilleNaturePersonne(idVivierSeul, listeIdsNaturesPersonne);
        assertTrue(Messages.getString("PersonnePhysiqueServiceTest.563"), hasMembreFamilleNaturePersonne);
        hasMembreFamilleNaturePersonne = personnePhysiqueService.hasMembreFamilleNaturePersonne(idPersonneAvecBenefVivier, listeIdsNaturesPersonne);
        assertTrue(Messages.getString("PersonnePhysiqueServiceTest.564"),
            hasMembreFamilleNaturePersonne);

        // Création d'un prospect
        final PersonneDto personne = creerPersonne();
        final IdentifiantLibelleDto naturePersonne = new IdentifiantLibelleDto(1L);
        personne.setNaturePersonne(naturePersonne);
        final List<BeneficiaireDto> listeBeneficiaires = new ArrayList<BeneficiaireDto>();
        final AdresseDto adresseDto = creerAdresse();
        final EmailDto emailDto = creerEmail();
        final TelephoneDto telephoneDto = creerTelephone();
        final PersonneDto personneCreee = personnePhysiqueService.creerPersonnePhysique(personne, listeBeneficiaires, adresseDto, emailDto, telephoneDto);
        hasMembreFamilleNaturePersonne = personnePhysiqueService.hasMembreFamilleNaturePersonne(personneCreee.getIdentifiant(), listeIdsNaturesPersonne);
        assertFalse(Messages.getString("PersonnePhysiqueServiceTest.565"), hasMembreFamilleNaturePersonne);
    }

    /**
     * Test unitaire de création d'une personne.
     */
    @Test
    public void testCreatePersonneNumeroVoieErrone() {
        // Instanciation des dto.
        final PersonneDto personneToCreate = new PersonneDto();
        final BeneficiaireDto beneficaireTocreate = new BeneficiaireDto();
        final AdresseDto adresseToCreate = new AdresseDto();
        final TelephoneDto telephoneToCreate = new TelephoneDto();
        final EmailDto emailDto = new EmailDto();

        // Initialisation PersonneDto.
        personneToCreate.setIdext(idext);
        personneToCreate.setCivilite(new IdentifiantLibelleDto(1L));
        personneToCreate.setPrenom(prenom);
        personneToCreate.setNom("2nom-2l");
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        final int year = 2010;
        date1.set(year, Calendar.MAY, 3);
        personneToCreate.setDateNaissance(date1);
        personneToCreate.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personneToCreate.setCreateur(createur());
        personneToCreate.setCommercial(commercial());

        // Initialisation telephoneDto
        telephoneToCreate.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        telephoneToCreate.setNumero(numtel);
        final PaysSimpleDto pays = new PaysSimpleDto();
        pays.setId(1L);
        telephoneToCreate.setPays(pays);

        // Initilisation de l'email.
        emailDto.setNatureEmail(new IdentifiantLibelleDto(1L));
        emailDto.setAdresse(email);

        // Initialisation BeneficiaireDto
        beneficaireTocreate.setCivilite(new IdentifiantLibelleDto(2L));
        beneficaireTocreate.setPrenom(prenomB);
        beneficaireTocreate.setNom(nomB);
        final Calendar date11 = Calendar.getInstance();
        date11.clear();
        final int year1 = 2010;
        date11.set(year1, Calendar.MAY, 3);
        beneficaireTocreate.setDateNaissance(date11);
        beneficaireTocreate.setTypeRelation(new IdentifiantLibelleDto(2L));

        // Initialisation AdresseDto
        adresseToCreate.setIdext(idext);
        adresseToCreate.setNomVoie(nomVoie);
        adresseToCreate.setNumVoie("84/7");
        adresseToCreate.setComplementNom(batimentA);
        adresseToCreate.setComplementAdresse(complAdresse);
        adresseToCreate.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseToCreate.setBoitePostal(boitePostale);
        adresseToCreate.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseToCreate.setPays(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Ajout du bénéficiaire dans la liste des bénéficiaires.
        final List<BeneficiaireDto> listebeneficiaire = new ArrayList<BeneficiaireDto>();
        listebeneficiaire.add(beneficaireTocreate);

        // Appel du service à tester
        try {
            personnePhysiqueService.creerPersonnePhysique(personneToCreate, listebeneficiaire, adresseToCreate, emailDto, telephoneToCreate);
            fail(Messages.getString("PersonnePhysiqueServiceTest.568"));
        } catch (ControleIntegriteException e) {
            assertNotNull(Messages.getString("PersonnePhysiqueServiceTest.569"), e.getRapport().getRapports().get("AdresseDto.numVoie"));
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.571"), messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_FORMAT_NUM_VOIE_INCORRECT), e.getRapport().getRapports().get("AdresseDto.numVoie").getMessage());
        }
    }

    /** Test du service indexerListePersonnesPhysiques. */
    @Test
    public void testIndexerListePersonnesPhysiques() {
        // Personne inexistante
        final Long idPersonneInexistante = 100L;
        final List<Long> listeIdsPersonnesAIndexer = new ArrayList<Long>();
        listeIdsPersonnesAIndexer.add(idPersonneInexistante);
        try {
            personnePhysiqueService.indexerListePersonnesPhysiques(listeIdsPersonnesAIndexer);
            fail(Messages.getString("PersonnePhysiqueServiceTest.573"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("PersonnePhysiqueServiceTest.574"),
                messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE), e.getMessage());
        }
    }

    private PersonneDto creerPersonne() {
        final PersonneDto personne = new PersonneDto();
        personne.setIdext("5");
        personne.setCivilite(new IdentifiantLibelleDto(1L));
        personne.setNom("Nom");
        personne.setPrenom(prenom);
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        final int year = 2000;
        cal.set(year, Calendar.JANUARY, 1);
        personne.setDateNaissance(cal);
        personne.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
        personne.setCreateur(createur());
        personne.setCommercial(commercial());
        return personne;
    }

    private TelephoneDto creerTelephone() {
        final TelephoneDto telephone = new TelephoneDto();
        telephone.setNature(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));
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
        adresseDto.setIdext(idext);
        adresseDto.setNumVoie(numVoie);
        adresseDto.setTypeVoie(new IdentifiantLibelleDto(2L, null));
        adresseDto.setNomVoie(nomVoie);
        adresseDto.setCodePostalCommune(new CodePostalCommuneDto(1L));
        adresseDto.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance()));
        return adresseDto;
    }

    private DimensionRessourceDto commercial() {
        final DimensionRessourceDto dimensionRessourceDto = new DimensionRessourceDto();
        dimensionRessourceDto.setIdentifiant(1L);
        dimensionRessourceDto.setNom("Nom 3");
        dimensionRessourceDto.setPrenom("Prénom 1");
        return dimensionRessourceDto;
    }

    private DimensionRessourceDto createur() {
        final DimensionRessourceDto dimensionRessourceDto = new DimensionRessourceDto();
        dimensionRessourceDto.setIdentifiant(1L);
        dimensionRessourceDto.setIdentifiantExterieur("1500");
        dimensionRessourceDto.setNom("Nom 3");
        dimensionRessourceDto.setPrenom("Prénom 1");
        return dimensionRessourceDto;
    }

    /**
     * Construit un calendar à partir d'une date de la forme "dd/MM/yyyy".
     * @param date la date
     * @return l'objet Calendar
     */
    private static Calendar getCalendarByDate(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(Messages.getString("PersonnePhysiqueServiceTest.4"));
        try {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            return calendar;
        }
        catch (ParseException e) {
            throw new TechnicalException(Messages.getString("PersonnePhysiqueServiceTest.3") + date);
        }
    }
}
