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
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.dao.interfaces.InfoSanteDao;
import com.square.core.dao.interfaces.PersonnePhysiqueDao;
import com.square.core.model.InfoSante;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.BeneficiaireDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.PaysSimpleDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Tests unitaires des services liés à la gestion des doublons.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class PersonnePhysiqueDoublonServiceTest extends DbunitBaseTestCase {

    private static final String TYPE_RELATION_PAS_BON = Messages.getString("PersonnePhysiqueDoublonServiceTest.0");
    private static final String NATURE_RELATION_PAS_BONNE = Messages.getString("PersonnePhysiqueDoublonServiceTest.1");
    private static final String PAYS_TELEPHONE_PAS_BON = Messages.getString("PersonnePhysiqueDoublonServiceTest.2");
    private static final String NUMERO_TELEPHONE_PAS_BON = Messages.getString("PersonnePhysiqueDoublonServiceTest.3");
    private static final String NATURE_TELEPHONE_PAS_BONNE = Messages.getString("PersonnePhysiqueDoublonServiceTest.4");
    private static final String ADRESSE_MAIL_PAS_BONNE = Messages.getString("PersonnePhysiqueDoublonServiceTest.5");
    private static final String NATURE_MAIL_PAS_BON = Messages.getString("PersonnePhysiqueDoublonServiceTest.6");
    private static final String COMMUNE_ADRESSE_PAS_BONNE = Messages.getString("PersonnePhysiqueDoublonServiceTest.7");
    private static final String LIBELLE_ADRESSE_PAS_BON = Messages.getString("PersonnePhysiqueDoublonServiceTest.8");
    private static final String NUMERO_ADRESSE_PAS_BON = Messages.getString("PersonnePhysiqueDoublonServiceTest.9");
    private static final String NATURE_ADRESSE_PAS_BONNE = Messages.getString("PersonnePhysiqueDoublonServiceTest.10");
    private static final String NOMBRE_MAILS_PAS_BON = Messages.getString("PersonnePhysiqueDoublonServiceTest.11");
    private static final String NOMBRE_TELEPHONES_PAS_BON = Messages.getString("PersonnePhysiqueDoublonServiceTest.12");
    private static final String NOMBRE_ADRESSES_PAS_BON = Messages.getString("PersonnePhysiqueDoublonServiceTest.13");
    private static final String FLAG_DOUBLON_PAS_BON = Messages.getString("PersonnePhysiqueDoublonServiceTest.14");

    /**
     * Le service des personnes physiques.
     */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Service Personne. */
    private PersonneService personneService;

    /** Service de mapping. */
    private SquareMappingService squareMappingService;

    /** InfoSanteDao. */
    private InfoSanteDao infoSanteDao;

    /** DAO Personne. */
    private PersonnePhysiqueDao personnePhysiqueDao;

    private List<Long> idsReferentToDelete;

    /**
     * Méthode appelée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        personnePhysiqueService = (PersonnePhysiqueService) getBeanSpring("personnePhysiqueService");
        personneService = (PersonneService) getBeanSpring("personneService");
        squareMappingService = (SquareMappingService) getBeanSpring("squareMappingService");
        infoSanteDao = (InfoSanteDao) getBeanSpring("infoSanteDao");
        personnePhysiqueDao = (PersonnePhysiqueDao) getBeanSpring("personnePhysiqueDao");
        createSecureContext("user", "user");
        idsReferentToDelete = new ArrayList<Long>();
    }

    @Override
    public void tearDownBaseTestCase() throws Exception {
        final List<Long> idsReferent = new ArrayList<Long>();

        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criterias = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, 1000);
        final RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criterias);
        for (PersonneSimpleDto personne : results.getListResults()) {
            idsReferent.add(personne.getId());
        }
        idsReferent.addAll(idsReferentToDelete);

        // il faut poser les referents des infos santé
        for (Long idReferent : idsReferent) {
            deleteReferent(idReferent);
        }
        super.tearDownBaseTestCase();
    }

    private void deleteReferent(Long idPersonne) {
        final InfoSante infoSante = personnePhysiqueDao.rechercherPersonneParId(idPersonne).getInfoSante();
        if (infoSante != null) {
            infoSante.setReferent(null);
            infoSanteDao.updateInfoSante(infoSante);
        }
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
        return "datasetPersonnePhysiqueDoublon.xml";
    }

    /** Test de recherche de personne avec calcul du flag doublon. */
    @Test
    public void testRechercherPersonneCalculFlagDoublon() {
        // Recherche de l'ensemble des personnes (doublons : (3,4), (5,6,7); la personne 9 a un doublon (8) supprimé)
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> remote = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, 100);
        final RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);

        // Vérification des données
        final int nbResultats = 12;
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.24"), nbResultats, results.getTotalResults());
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;
        final Long idPersonne3 = 3L;
        final Long idPersonne4 = 4L;
        final Long idPersonne5 = 5L;
        final Long idPersonne6 = 6L;
        final Long idPersonne7 = 7L;
        final Long idPersonne9 = 9L;
        boolean possedePersonne1 = false;
        boolean possedePersonne2 = false;
        boolean possedePersonne3 = false;
        boolean possedePersonne4 = false;
        boolean possedePersonne5 = false;
        boolean possedePersonne6 = false;
        boolean possedePersonne7 = false;
        boolean possedePersonne9 = false;
        // Parcours de la liste des personnes pour vérification du flag
        for (PersonneSimpleDto personne : results.getListResults()) {
            if (idPersonne1.equals(personne.getId())) {
                possedePersonne1 = true;
                assertEquals(FLAG_DOUBLON_PAS_BON, false, personne.isDoublon());
            } else if (idPersonne2.equals(personne.getId())) {
                possedePersonne2 = true;
                assertEquals(FLAG_DOUBLON_PAS_BON, false, personne.isDoublon());
            } else if (idPersonne3.equals(personne.getId())) {
                possedePersonne3 = true;
                assertEquals(FLAG_DOUBLON_PAS_BON, true, personne.isDoublon());
            } else if (idPersonne4.equals(personne.getId())) {
                possedePersonne4 = true;
                assertEquals(FLAG_DOUBLON_PAS_BON, true, personne.isDoublon());
            } else if (idPersonne5.equals(personne.getId())) {
                possedePersonne5 = true;
                assertEquals(FLAG_DOUBLON_PAS_BON, true, personne.isDoublon());
            } else if (idPersonne6.equals(personne.getId())) {
                possedePersonne6 = true;
                assertEquals(FLAG_DOUBLON_PAS_BON, true, personne.isDoublon());
            } else if (idPersonne7.equals(personne.getId())) {
                possedePersonne7 = true;
                assertEquals(FLAG_DOUBLON_PAS_BON, true, personne.isDoublon());
            } else if (idPersonne9.equals(personne.getId())) {
                possedePersonne9 = true;
                assertEquals(FLAG_DOUBLON_PAS_BON, false, personne.isDoublon());
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueDoublonServiceTest.25"), possedePersonne1 && possedePersonne2
        		&& possedePersonne3 && possedePersonne4 && possedePersonne5
            && possedePersonne6 && possedePersonne7 && possedePersonne9);
    }

    /** Test de la création de personne par sélection de l'assuré et création d'un bénéficiaire. */
    @Test
    public void testCreerPersonneSelectionAssureCreationBenef() {
        final Long idAssureSelectionne = 10L;
        final Long idConjoint = 11L;
        final Long idEnfant = 12L;
        Long idNouveauBeneficiaire = null;

        // Création du DTO de l'assuré
        final PersonneDto assureSelectionne = new PersonneDto();
        assureSelectionne.setIdentifiant(idAssureSelectionne);
        // Ajout d'un bénéficiaire
        final List<BeneficiaireDto> listeBeneficiaires = new ArrayList<BeneficiaireDto>();
        final BeneficiaireDto beneficaire = new BeneficiaireDto();
        final Long idCiviliteBeneficiaire = squareMappingService.getIdCiviliteMademoiselle();
        final String prenomBeneficiaire = "prenomBen1";
        final String nomBeneficiaire = "NomBen1";
        final Calendar dateNaissanceBeneficiaire = getCalendarByDate("05/06/2010");
        beneficaire.setCivilite(new IdentifiantLibelleDto(idCiviliteBeneficiaire));
        beneficaire.setPrenom(prenomBeneficiaire);
        beneficaire.setNom(nomBeneficiaire);
        beneficaire.setDateNaissance(dateNaissanceBeneficiaire);
        beneficaire.setTypeRelation(new IdentifiantLibelleDto(squareMappingService.getIdTypeRelationEnfant()));
        listeBeneficiaires.add(beneficaire);

        // Appel du service de création de personnes
        final PersonneDto personneCree = personnePhysiqueService.creerPersonnePhysique(assureSelectionne, listeBeneficiaires, null, null, null);
        idsReferentToDelete.add(personneCree.getIdentifiant());

        // Vérification du nombre de personnes
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> remote = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, 100);
        final RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        final int nbPersonnes = 13;
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.29"), nbPersonnes, results.getTotalResults());

        // Vérification des coordonnées de l'assuré
        final CoordonneesDto coordonneesAssure = personneService.rechercherCoordonneesParIdPersonne(idAssureSelectionne);
        assertEquals(NOMBRE_ADRESSES_PAS_BON, 1, coordonneesAssure.getAdresses().size());
        assertEquals(NOMBRE_TELEPHONES_PAS_BON, 2, coordonneesAssure.getTelephones().size());
        assertEquals(NOMBRE_MAILS_PAS_BON, 1, coordonneesAssure.getEmails().size());
        // Vérification de l'adresse
        final AdresseDto adresseAssure = coordonneesAssure.getAdresses().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.30"), 1L, adresseAssure.getIdentifiant());
        assertEquals(NATURE_ADRESSE_PAS_BONNE, squareMappingService.getIdNatureAdressePrincipale(), adresseAssure.getNature()
            .getIdentifiant());
        assertEquals(NUMERO_ADRESSE_PAS_BON, "19", adresseAssure.getNumVoie());
        assertEquals(LIBELLE_ADRESSE_PAS_BON, "de la loge", adresseAssure.getNomVoie());
        assertEquals(COMMUNE_ADRESSE_PAS_BONNE, 1L, adresseAssure.getCodePostalCommune().getCommune().getIdentifiant());
        // Vérification du mail
        final EmailDto emailAssure = coordonneesAssure.getEmails().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.33"), 1L, emailAssure.getIdentifiant());
        assertEquals(NATURE_MAIL_PAS_BON, 1L, emailAssure.getNatureEmail().getIdentifiant());
        assertEquals(ADRESSE_MAIL_PAS_BONNE, "email1@square.com", emailAssure.getAdresse());
        // Vérification des téléphones
        assertEquals(NOMBRE_TELEPHONES_PAS_BON, 2, coordonneesAssure.getTelephones().size());
        final Long idTelephone1 = 1L;
        final Long idTelephone2 = 2L;
        boolean possedeTelephone1 = false;
        boolean possedeTelephone2 = false;
        for (TelephoneDto telephoneAssure : coordonneesAssure.getTelephones()) {
            if (idTelephone1.equals(telephoneAssure.getId())) {
                possedeTelephone1 = true;
                assertEquals(NATURE_TELEPHONE_PAS_BONNE, squareMappingService.getIdNatureTelephoneFixe(), telephoneAssure.getNature().getIdentifiant());
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "0505050505", telephoneAssure.getNumero());
                assertEquals(PAYS_TELEPHONE_PAS_BON, squareMappingService.getIdPaysFrance(), telephoneAssure.getPays().getId());
            } else if (idTelephone2.equals(telephoneAssure.getId())) {
                possedeTelephone2 = true;
                assertEquals(NATURE_TELEPHONE_PAS_BONNE, squareMappingService.getIdNatureMobilePrive(), telephoneAssure.getNature().getIdentifiant());
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "0606060606", telephoneAssure.getNumero());
                assertEquals(PAYS_TELEPHONE_PAS_BON, squareMappingService.getIdPaysFrance(), telephoneAssure.getPays().getId());
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueDoublonServiceTest.37"), possedeTelephone1 && possedeTelephone2);

        // Vérification des relations
        final RelationCriteresRechercheDto criteresRelations = new RelationCriteresRechercheDto();
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelations, 0, Integer.MAX_VALUE);
        criteresRelations.setIdPersonneSource(idAssureSelectionne);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> resultsRelations =
            personneService.rechercherRelationsParCritreres(criterias);
        final int nbRelations = 3;
        boolean possedeRelationAvec11 = false;
        boolean possedeRelationAvec12 = false;
        boolean possedeNouvelleRelationEnfant = false;
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.38"), nbRelations, resultsRelations.getListResults().size());
        for (RelationInfosDto<? extends PersonneRelationDto> relation : resultsRelations.getListResults()) {
            if (idConjoint.equals(relation.getIdPersonne())) {
                possedeRelationAvec11 = true;
                assertEquals(NATURE_RELATION_PAS_BONNE, squareMappingService.getIdTypeRelationConjoint(), relation.getType()
                    .getIdentifiant());
            } else if (idEnfant.equals(relation.getIdPersonne())) {
                possedeRelationAvec12 = true;
                assertEquals(NATURE_RELATION_PAS_BONNE, squareMappingService.getIdTypeRelationEnfant(), relation.getType().getIdentifiant());
            } else {
                possedeNouvelleRelationEnfant = true;
                assertEquals(NATURE_RELATION_PAS_BONNE, squareMappingService.getIdTypeRelationEnfant(), relation.getType().getIdentifiant());
                idNouveauBeneficiaire = relation.getIdPersonne();
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueDoublonServiceTest.39"), possedeRelationAvec11
        		&& possedeRelationAvec12 && possedeNouvelleRelationEnfant);

        // Vérification des champs du bénéficiaire crée
        final PersonneDto nouveauBeneficiaire = personnePhysiqueService.rechercherPersonneParIdentifiant(idNouveauBeneficiaire);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.40"), idCiviliteBeneficiaire, nouveauBeneficiaire.getCivilite().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.41"), "nomben1", nouveauBeneficiaire.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.43"), "prenomben1", nouveauBeneficiaire.getPrenom());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.45"), dateNaissanceBeneficiaire, nouveauBeneficiaire.getDateNaissance());

        // Vérification des coordonnées du bénéficiaire
        final CoordonneesDto coordonneesBeneficiaire = personneService.rechercherCoordonneesParIdPersonne(idNouveauBeneficiaire);
        assertEquals(NOMBRE_ADRESSES_PAS_BON, 1, coordonneesBeneficiaire.getAdresses().size());
        assertEquals(NOMBRE_TELEPHONES_PAS_BON, 2, coordonneesBeneficiaire.getTelephones().size());
        assertEquals(NOMBRE_MAILS_PAS_BON, 1, coordonneesBeneficiaire.getEmails().size());
        // Vérification de l'adresse
        final AdresseDto adresseBeneficiaire = coordonneesBeneficiaire.getAdresses().get(0);
        assertEquals(NATURE_ADRESSE_PAS_BONNE, squareMappingService.getIdNatureAdressePrincipale(), adresseBeneficiaire.getNature()
            .getIdentifiant());
        assertEquals(NUMERO_ADRESSE_PAS_BON, "19", adresseBeneficiaire.getNumVoie());
        assertEquals(LIBELLE_ADRESSE_PAS_BON, "de la loge", adresseBeneficiaire.getNomVoie());
        assertEquals(COMMUNE_ADRESSE_PAS_BONNE, 1L, adresseBeneficiaire.getCodePostalCommune().getCommune().getIdentifiant());
        // Vérification du mail
        final EmailDto emailBeneficiaire = coordonneesBeneficiaire.getEmails().get(0);
        assertEquals(NATURE_MAIL_PAS_BON, 1L, emailBeneficiaire.getNatureEmail().getIdentifiant());
        assertEquals(ADRESSE_MAIL_PAS_BONNE, "email1@square.com", emailBeneficiaire.getAdresse());
        // Vérification des téléphones
        assertEquals(NOMBRE_TELEPHONES_PAS_BON, 2, coordonneesBeneficiaire.getTelephones().size());
        boolean possedeTelephoneFixe = false;
        boolean possedeTelephonePortable = false;
        for (TelephoneDto telephoneBeneficiaire : coordonneesBeneficiaire.getTelephones()) {
            if (squareMappingService.getIdNatureTelephoneFixe().equals(telephoneBeneficiaire.getNature().getIdentifiant())) {
                possedeTelephoneFixe = true;
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "0505050505", telephoneBeneficiaire.getNumero());
                assertEquals(PAYS_TELEPHONE_PAS_BON, squareMappingService.getIdPaysFrance(), telephoneBeneficiaire.getPays().getId());
            } else if (squareMappingService.getIdNatureMobilePrive().equals(telephoneBeneficiaire.getNature().getIdentifiant())) {
                possedeTelephonePortable = true;
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "0606060606", telephoneBeneficiaire.getNumero());
                assertEquals(PAYS_TELEPHONE_PAS_BON, squareMappingService.getIdPaysFrance(), telephoneBeneficiaire.getPays().getId());
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueDoublonServiceTest.51"), possedeTelephoneFixe && possedeTelephonePortable);
    }

    /** Test de la création de personne par création de l'assuré et sélection d'un bénéficiaire. */
    @Test
    public void testCreerPersonneCreationAssureSelectionBenef() {
        final Long idBeneficiaireSelectionne = 13L;

        // Initialisation PersonneDto.
        final Long idCiviliteAssure = squareMappingService.getIdCiviliteMonsieur();
        final String prenomAssure = "Nicolas";
        final String nomAssure = "Peltier";
        final Calendar dateNaissanceAssure = getCalendarByDate("01/02/1985");
        final PersonneDto assureACreer = new PersonneDto();
        assureACreer.setCivilite(new IdentifiantLibelleDto(idCiviliteAssure));
        assureACreer.setPrenom(prenomAssure);
        assureACreer.setNom(nomAssure);
        assureACreer.setDateNaissance(dateNaissanceAssure);
        assureACreer.setProfession(new IdentifiantLibelleBooleanDto(1L, null, Boolean.FALSE));

        // Création des coordonnées
        CoordonneesDto coordonneesAssure = new CoordonneesDto();

        // Initialisation de l'adresse assuré
        final List<AdresseDto> listeAdresses = new ArrayList<AdresseDto>();
        final String numVoie = "200";
        final String libelleVoie = "de Bordeaux";
        final Long idCodePostalCommune = 2L;
        final Long idPays = squareMappingService.getIdPaysFrance();
        AdresseDto adresseAssure = new AdresseDto();
        adresseAssure.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdressePrincipale()));
        adresseAssure.setNumVoie(numVoie);
        adresseAssure.setNomVoie(libelleVoie);
        adresseAssure.setCodePostalCommune(new CodePostalCommuneDto(idCodePostalCommune));
        adresseAssure.setPays(new IdentifiantLibelleBooleanDto(idPays));
        listeAdresses.add(adresseAssure);
        coordonneesAssure.setAdresses(listeAdresses);

        // Initialisation téléphone assuré
        final List<TelephoneDto> listeTelephones = new ArrayList<TelephoneDto>();
        final String numeroTelephoneAssure = "0545000000";
        TelephoneDto telephoneFixeAssure = new TelephoneDto();
        telephoneFixeAssure.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureTelephoneFixe()));
        telephoneFixeAssure.setNumero(numeroTelephoneAssure);
        final PaysSimpleDto paysFrance = new PaysSimpleDto();
        final Long idPaysFrance = squareMappingService.getIdPaysFrance();
        paysFrance.setId(idPaysFrance);
        telephoneFixeAssure.setPays(paysFrance);
        listeTelephones.add(telephoneFixeAssure);
        coordonneesAssure.setTelephones(listeTelephones);

        // Initilisation mail assuré
        final List<EmailDto> listeEmails = new ArrayList<EmailDto>();
        final String adresseEmailAssure = "emailassure@test.net";
        EmailDto emailAssure = new EmailDto();
        emailAssure.setNatureEmail(new IdentifiantLibelleDto(squareMappingService.getIdNatureEmailPersonnel()));
        emailAssure.setAdresse(adresseEmailAssure);
        listeEmails.add(emailAssure);
        coordonneesAssure.setEmails(listeEmails);

        // Ajout du bénéficiaire sélectionné
        final List<BeneficiaireDto> listeBeneficiaires = new ArrayList<BeneficiaireDto>();
        final BeneficiaireDto beneficiaireSelectionne = new BeneficiaireDto();
        beneficiaireSelectionne.setIdentifiant(idBeneficiaireSelectionne);
        beneficiaireSelectionne.setTypeRelation(new IdentifiantLibelleDto(squareMappingService.getIdTypeRelationEnfant()));
        listeBeneficiaires.add(beneficiaireSelectionne);

        // Appel du service de création
        final Long idAssureCree = personnePhysiqueService.creerPersonnePhysique(assureACreer, listeBeneficiaires, coordonneesAssure).getIdentifiant();
        idsReferentToDelete.add(idAssureCree);

        // Vérification du nombre de personnes
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> remote = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, 100);
        final RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        final int nbPersonnes = 13;
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.59"), nbPersonnes, results.getTotalResults());

        // Vérification des champs de l'assuré
        final PersonneDto assureCree = personnePhysiqueService.rechercherPersonneParIdentifiant(idAssureCree);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.60"), idCiviliteAssure, assureCree.getCivilite().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.61"), nomAssure.toLowerCase(), assureCree.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.62"), prenomAssure.toLowerCase(), assureCree.getPrenom());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.63"), dateNaissanceAssure, assureCree.getDateNaissance());

        // Vérification des coordonnées de l'assuré
        coordonneesAssure = personneService.rechercherCoordonneesParIdPersonne(idAssureCree);
        assertEquals(NOMBRE_ADRESSES_PAS_BON, 1, coordonneesAssure.getAdresses().size());
        assertEquals(NOMBRE_TELEPHONES_PAS_BON, 1, coordonneesAssure.getTelephones().size());
        assertEquals(NOMBRE_MAILS_PAS_BON, 1, coordonneesAssure.getEmails().size());
        // Vérification de l'adresse
        adresseAssure = coordonneesAssure.getAdresses().get(0);
        assertEquals(NATURE_ADRESSE_PAS_BONNE, squareMappingService.getIdNatureAdressePrincipale(), adresseAssure.getNature()
            .getIdentifiant());
        assertEquals(NUMERO_ADRESSE_PAS_BON, numVoie, adresseAssure.getNumVoie());
        assertEquals(LIBELLE_ADRESSE_PAS_BON, libelleVoie.toLowerCase(), adresseAssure.getNomVoie());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.64"), idCodePostalCommune, adresseAssure.getCodePostalCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.65"), "Bordeaux Cedex",
        		adresseAssure.getCodePostalCommune().getLibelleAcheminement());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.67"), 2L, adresseAssure.getCodePostalCommune().getCodePostal().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.68"), "33000", adresseAssure.getCodePostalCommune().getCodePostal().getLibelle());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.70"), 2L, adresseAssure.getCodePostalCommune().getCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.71"), "Bordeaux", adresseAssure.getCodePostalCommune().getCommune().getLibelle());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.73"), 1L, adresseAssure.getDepartement().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.74"), "Charente", adresseAssure.getDepartement().getLibelle());
        // Vérification du mail
        emailAssure = coordonneesAssure.getEmails().get(0);
        assertEquals(NATURE_MAIL_PAS_BON, squareMappingService.getIdNatureEmailPersonnel(), emailAssure.getNatureEmail().getIdentifiant());
        assertEquals(ADRESSE_MAIL_PAS_BONNE, adresseEmailAssure, emailAssure.getAdresse());
        // Vérification des téléphones
        telephoneFixeAssure = coordonneesAssure.getTelephones().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.76"),
        		squareMappingService.getIdNatureTelephoneFixe(), emailAssure.getNatureEmail().getIdentifiant());
        assertEquals(NUMERO_TELEPHONE_PAS_BON, "0545000000", telephoneFixeAssure.getNumero());
        assertEquals(PAYS_TELEPHONE_PAS_BON, squareMappingService.getIdPaysFrance(), telephoneFixeAssure.getPays().getId());

        // Vérification des relations de l'assuré
        final RelationCriteresRechercheDto criteresRelations = new RelationCriteresRechercheDto();
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelations, 0, Integer.MAX_VALUE);
        criteresRelations.setIdPersonneSource(idAssureCree);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> resultsRelationsAssure =
            personneService.rechercherRelationsParCritreres(criterias);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.78"), 1, resultsRelationsAssure.getListResults().size());
        final RelationInfosDto<? extends PersonneRelationDto> relationAssureEnfant = resultsRelationsAssure.getListResults().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.79"), idBeneficiaireSelectionne, relationAssureEnfant.getIdPersonne());
        assertEquals(TYPE_RELATION_PAS_BON, squareMappingService.getIdTypeRelationEnfant(), relationAssureEnfant.getType().getIdentifiant());

        // Vérification des champs du bénéficiaire
        final PersonneDto beneficiaire = personnePhysiqueService.rechercherPersonneParIdentifiant(idBeneficiaireSelectionne);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.80"), 1L, beneficiaire.getCivilite().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.81"),
        		Messages.getString("PersonnePhysiqueDoublonServiceTest.82"), beneficiaire.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.83"),
        		Messages.getString("PersonnePhysiqueDoublonServiceTest.84"), beneficiaire.getPrenom());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.85"), getCalendarByDate("10/03/2006"), beneficiaire.getDateNaissance());

        // Vérification des coordonnées de l'assuré
        final CoordonneesDto coordonneesBeneficiaire = personneService.rechercherCoordonneesParIdPersonne(idBeneficiaireSelectionne);
        assertEquals(NOMBRE_ADRESSES_PAS_BON, 1, coordonneesBeneficiaire.getAdresses().size());
        assertEquals(NOMBRE_TELEPHONES_PAS_BON, 1, coordonneesBeneficiaire.getTelephones().size());
        assertEquals(NOMBRE_MAILS_PAS_BON, 1, coordonneesBeneficiaire.getEmails().size());
        // Vérification de l'adresse
        final AdresseDto adresseBeneficiaire = coordonneesBeneficiaire.getAdresses().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.87"), 4L, adresseBeneficiaire.getIdentifiant());
        assertEquals(NATURE_ADRESSE_PAS_BONNE, 1L, adresseBeneficiaire.getNature()
            .getIdentifiant());
        assertEquals(NUMERO_ADRESSE_PAS_BON, "147", adresseBeneficiaire.getNumVoie());
        assertEquals(LIBELLE_ADRESSE_PAS_BON, "de limoges", adresseBeneficiaire.getNomVoie());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.90"), 1L, adresseBeneficiaire.getCodePostalCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.91"),
        		"Angoulême Cedex", adresseBeneficiaire.getCodePostalCommune().getLibelleAcheminement());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.93"),
        		1L, adresseBeneficiaire.getCodePostalCommune().getCodePostal().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.94"),
        		"16000", adresseBeneficiaire.getCodePostalCommune().getCodePostal().getLibelle());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.96"), 1L, adresseBeneficiaire.getCodePostalCommune().getCommune().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.97"),
        		"Angoulême", adresseBeneficiaire.getCodePostalCommune().getCommune().getLibelle());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.99"), 1L, adresseBeneficiaire.getDepartement().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.100"), "Charente", adresseBeneficiaire.getDepartement().getLibelle());
        // Vérification du mail
        final EmailDto emailBeneficiaire = coordonneesBeneficiaire.getEmails().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.102"), 4L, emailBeneficiaire.getIdentifiant());
        assertEquals(NATURE_MAIL_PAS_BON, 1L, emailBeneficiaire.getNatureEmail().getIdentifiant());
        assertEquals(ADRESSE_MAIL_PAS_BONNE, Messages.getString("PersonnePhysiqueDoublonServiceTest.103"), emailBeneficiaire.getAdresse());
        // Vérification des téléphones
        final TelephoneDto telephoneBeneficiaire = coordonneesBeneficiaire.getTelephones().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.104"), 5L, telephoneBeneficiaire.getId());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.105"), 1L, telephoneBeneficiaire.getNature().getIdentifiant());
        assertEquals(NUMERO_TELEPHONE_PAS_BON, "0500000000", telephoneBeneficiaire.getNumero());
        assertEquals(PAYS_TELEPHONE_PAS_BON, squareMappingService.getIdPaysFrance(), telephoneBeneficiaire.getPays().getId());
    }

    /** Test de la création de personne par sélection de l'assuré et sélection d'un bénéficiaire. */
    @Test
    public void testCreerPersonneSelectionAssureSelectionBenef() {
        final Long idAssureSelectionne = 10L;
        final Long idConjoint = 11L;
        final Long idEnfant = 12L;
        final Long idBeneficiaireSelectionne = 13L;

        // Création du DTO de l'assuré
        final PersonneDto assureSelectionne = new PersonneDto();
        assureSelectionne.setIdentifiant(idAssureSelectionne);
        // Ajout du bénéficiaire sélectionné
        final List<BeneficiaireDto> listeBeneficiaires = new ArrayList<BeneficiaireDto>();
        final BeneficiaireDto beneficiaireSelectionne = new BeneficiaireDto();
        beneficiaireSelectionne.setIdentifiant(idBeneficiaireSelectionne);
        beneficiaireSelectionne.setTypeRelation(new IdentifiantLibelleDto(squareMappingService.getIdTypeRelationEnfant()));
        listeBeneficiaires.add(beneficiaireSelectionne);

        // Appel du service de création de personnes
        final PersonneDto personneCree = personnePhysiqueService.creerPersonnePhysique(assureSelectionne, listeBeneficiaires, null, null, null);
        idsReferentToDelete.add(personneCree.getIdentifiant());

        // Vérification du nombre de personnes
        final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> remote = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, 100);
        final RemotePagingResultsDto<PersonneSimpleDto> results = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remote);
        final int nbPersonnes = 12;
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.107"), nbPersonnes, results.getTotalResults());

        // Vérification des coordonnées de l'assuré
        final CoordonneesDto coordonneesAssure = personneService.rechercherCoordonneesParIdPersonne(idAssureSelectionne);
        assertEquals(NOMBRE_ADRESSES_PAS_BON, 1, coordonneesAssure.getAdresses().size());
        assertEquals(NOMBRE_TELEPHONES_PAS_BON, 2, coordonneesAssure.getTelephones().size());
        assertEquals(NOMBRE_MAILS_PAS_BON, 1, coordonneesAssure.getEmails().size());
        // Vérification de l'adresse
        final AdresseDto adresseAssure = coordonneesAssure.getAdresses().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.108"), 1L, adresseAssure.getIdentifiant());
        assertEquals(NATURE_ADRESSE_PAS_BONNE, squareMappingService.getIdNatureAdressePrincipale(), adresseAssure.getNature()
            .getIdentifiant());
        assertEquals(NUMERO_ADRESSE_PAS_BON, "19", adresseAssure.getNumVoie());
        assertEquals(LIBELLE_ADRESSE_PAS_BON, "de la loge", adresseAssure.getNomVoie());
        assertEquals(COMMUNE_ADRESSE_PAS_BONNE, 1L, adresseAssure.getCodePostalCommune().getCommune().getIdentifiant());
        // Vérification du mail
        final EmailDto emailAssure = coordonneesAssure.getEmails().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.111"), 1L, emailAssure.getIdentifiant());
        assertEquals(NATURE_MAIL_PAS_BON, 1L, emailAssure.getNatureEmail().getIdentifiant());
        assertEquals(ADRESSE_MAIL_PAS_BONNE, "email1@square.com", emailAssure.getAdresse());
        // Vérification des téléphones
        assertEquals(NOMBRE_TELEPHONES_PAS_BON, 2, coordonneesAssure.getTelephones().size());
        final Long idTelephone1 = 1L;
        final Long idTelephone2 = 2L;
        boolean possedeTelephone1 = false;
        boolean possedeTelephone2 = false;
        for (TelephoneDto telephoneAssure : coordonneesAssure.getTelephones()) {
            if (idTelephone1.equals(telephoneAssure.getId())) {
                possedeTelephone1 = true;
                assertEquals(NATURE_TELEPHONE_PAS_BONNE, squareMappingService.getIdNatureTelephoneFixe(), telephoneAssure.getNature().getIdentifiant());
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "0505050505", telephoneAssure.getNumero());
                assertEquals(PAYS_TELEPHONE_PAS_BON, squareMappingService.getIdPaysFrance(), telephoneAssure.getPays().getId());
            } else if (idTelephone2.equals(telephoneAssure.getId())) {
                possedeTelephone2 = true;
                assertEquals(NATURE_TELEPHONE_PAS_BONNE, squareMappingService.getIdNatureMobilePrive(), telephoneAssure.getNature().getIdentifiant());
                assertEquals(NUMERO_TELEPHONE_PAS_BON, "0606060606", telephoneAssure.getNumero());
                assertEquals(PAYS_TELEPHONE_PAS_BON, squareMappingService.getIdPaysFrance(), telephoneAssure.getPays().getId());
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueDoublonServiceTest.115"), possedeTelephone1 && possedeTelephone2);

        // Vérification des relations
        final RelationCriteresRechercheDto criteresRelations = new RelationCriteresRechercheDto();
        criteresRelations.setIdPersonneSource(idAssureSelectionne);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelations, 0, Integer.MAX_VALUE);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> resultsRelationsAssure =
            personneService.rechercherRelationsParCritreres(criterias);
        final int nbRelations = 3;
        boolean possedeRelationAvec11 = false;
        boolean possedeRelationAvec12 = false;
        boolean possedeRelationAvec13 = false;
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.116"), nbRelations, resultsRelationsAssure.getListResults().size());
        for (RelationInfosDto<? extends PersonneRelationDto> relation : resultsRelationsAssure.getListResults()) {
            if (idConjoint.equals(relation.getIdPersonne())) {
                possedeRelationAvec11 = true;
                assertEquals(NATURE_RELATION_PAS_BONNE, squareMappingService.getIdTypeRelationConjoint(), relation.getType()
                    .getIdentifiant());
            } else if (idEnfant.equals(relation.getIdPersonne())) {
                possedeRelationAvec12 = true;
                assertEquals(NATURE_RELATION_PAS_BONNE, squareMappingService.getIdTypeRelationEnfant(), relation.getType().getIdentifiant());
            } else if (idBeneficiaireSelectionne.equals(relation.getIdPersonne())) {
                possedeRelationAvec13 = true;
                assertEquals(NATURE_RELATION_PAS_BONNE, squareMappingService.getIdTypeRelationEnfant(), relation.getType().getIdentifiant());
            }
        }
        assertTrue(Messages.getString("PersonnePhysiqueDoublonServiceTest.117"), possedeRelationAvec11 && possedeRelationAvec12 && possedeRelationAvec13);

        // Vérification des champs du bénéficiaire
        final PersonneDto beneficiaire = personnePhysiqueService.rechercherPersonneParIdentifiant(idBeneficiaireSelectionne);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.118"), 1L, beneficiaire.getCivilite().getIdentifiant());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.119"), "PersonneDoublon", beneficiaire.getNom());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.121"), "PrenomPersonneDoublon", beneficiaire.getPrenom());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.123"), getCalendarByDate("10/03/2006"), beneficiaire.getDateNaissance());

        // Vérification des coordonnées de l'assuré
        final CoordonneesDto coordonneesBeneficiaire = personneService.rechercherCoordonneesParIdPersonne(idBeneficiaireSelectionne);
        assertEquals(NOMBRE_ADRESSES_PAS_BON, 1, coordonneesBeneficiaire.getAdresses().size());
        assertEquals(NOMBRE_TELEPHONES_PAS_BON, 1, coordonneesBeneficiaire.getTelephones().size());
        assertEquals(NOMBRE_MAILS_PAS_BON, 1, coordonneesBeneficiaire.getEmails().size());
        // Vérification de l'adresse
        final AdresseDto adresseBeneficiaire = coordonneesBeneficiaire.getAdresses().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.125"), 4L, adresseBeneficiaire.getIdentifiant());
        assertEquals(NATURE_ADRESSE_PAS_BONNE, 1L, adresseBeneficiaire.getNature()
            .getIdentifiant());
        assertEquals(NUMERO_ADRESSE_PAS_BON, "147", adresseBeneficiaire.getNumVoie());
        assertEquals(LIBELLE_ADRESSE_PAS_BON, "de limoges", adresseBeneficiaire.getNomVoie());
        assertEquals(COMMUNE_ADRESSE_PAS_BONNE, 1L, adresseBeneficiaire.getCodePostalCommune().getCommune().getIdentifiant());
        // Vérification du mail
        final EmailDto emailBeneficiaire = coordonneesBeneficiaire.getEmails().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.128"), 4L, emailBeneficiaire.getIdentifiant());
        assertEquals(NATURE_MAIL_PAS_BON, 1L, emailBeneficiaire.getNatureEmail().getIdentifiant());
        assertEquals(ADRESSE_MAIL_PAS_BONNE, "emailpersonnedoublon@square.com", emailBeneficiaire.getAdresse());
        // Vérification des téléphones
        final TelephoneDto telephoneBeneficiaire = coordonneesBeneficiaire.getTelephones().get(0);
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.130"), 5L, telephoneBeneficiaire.getId());
        assertEquals(Messages.getString("PersonnePhysiqueDoublonServiceTest.131"), 1L, telephoneBeneficiaire.getNature().getIdentifiant());
        assertEquals(NUMERO_TELEPHONE_PAS_BON, "0500000000", telephoneBeneficiaire.getNumero());
        assertEquals(PAYS_TELEPHONE_PAS_BON, squareMappingService.getIdPaysFrance(), telephoneBeneficiaire.getPays().getId());
    }

    /**
     * Construit un calendar à partir d'une date de la forme "dd/MM/yyyy".
     * @param date la date
     * @return l'objet Calendar
     */
    private static Calendar getCalendarByDate(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(Messages.getString("PersonnePhysiqueDoublonServiceTest.133"));
        try {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            return calendar;
        } catch (ParseException e) {
            throw new TechnicalException(Messages.getString("PersonnePhysiqueDoublonServiceTest.134") + date);
        }
    }
}
