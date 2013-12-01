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

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.adherent.noyau.dto.adherent.connexion.IdentifiantsConnexionDto;
import com.square.adherent.noyau.dto.adherent.connexion.InformationConnexionSimpleDto;
import com.square.adherent.noyau.dto.espace.client.EspaceClientInternetDto;
import com.square.adherent.noyau.service.interfaces.EspaceClientInternetService;

/**
 * Tests unitaires des services liés à l'espace client internet.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class EspaceClientInternetServiceTest extends DbunitBaseTestCase {

    private static final String LOGIN_PERSONNE1 = "login01";

    private static final String PASSWORD_PERSONNE1 = "mdp01";

    private static final String ESPACE = " ";

    private static final String PASSWORD_PERSONNE5 = "mdp5";

    private static final String ENCRYPTED_PASSWORD_PERSONNE1 = "73wsaPIdxS0WL1JhiRS9iZbTGbfn7w2Ci2WpRr2fr5Q=";

    private static final String ESPACE_CLIENT_INTERNET_CIBLE_PAS_BONNE = Messages.getString("EspaceClientInternetServiceTest.5");

    private static final String ESPACE_CLIENT_INTERNET_POINTER_PERSONNE_CIBLE = Messages.getString("EspaceClientInternetServiceTest.6");

    private static final String ESPACE_CLIENT_INTERNET_CIBLE_DEVRAIT_EXISTER = Messages.getString("EspaceClientInternetServiceTest.7");

	private static final Integer CINQUANTE = null;

    private EspaceClientInternetService espaceClientInternetService;

    @SuppressWarnings("unused")
	private StandardPBEStringEncryptor passwordEncryptor;

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        espaceClientInternetService = (EspaceClientInternetService) getBeanSpring("espaceClientInternetService");
        passwordEncryptor = (StandardPBEStringEncryptor) getBeanSpring("passwordEncryptor");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"adherentMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "dataset-espace-client.xml";
    }

    /**
     * Test le service de création d'espace client.
     */
    @Test
    public void testCreerEspaceClient() {
        // On essaye de créer l'espace client d'une personne sans préciser d'identifiant
        Long uidPersonne = null;
        try {
            espaceClientInternetService.creerEspaceClient(new EspaceClientInternetDto(uidPersonne));
            fail(Messages.getString("EspaceClientInternetServiceTest.12"));
        } catch (BusinessException e) {
            assertEquals(Messages.getString("EspaceClientInternetServiceTest.13"), Messages.getString("EspaceClientInternetServiceTest.14"), e.getMessage());
        }
        // On essaye de créer l'espace client d'une personne qui possède déjà un espace client
        uidPersonne = 1L;
        try {
            espaceClientInternetService.creerEspaceClient(new EspaceClientInternetDto(uidPersonne));
            fail(Messages.getString("EspaceClientInternetServiceTest.15"));
        } catch (BusinessException e) {
            assertEquals(Messages.getString("EspaceClientInternetServiceTest.16"), Messages.getString("EspaceClientInternetServiceTest.17")
            		+ ESPACE + uidPersonne.toString() + Messages.getString("EspaceClientInternetServiceTest.18"), e.getMessage());
        }
        // On créé l'espace client de la personne
        uidPersonne = 2L;
        final String numPersonne = "0002";
        final EspaceClientInternetDto infosConnexionDto = espaceClientInternetService.creerEspaceClient(new EspaceClientInternetDto(uidPersonne));
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.20"), numPersonne, infosConnexionDto.getLogin());
        assertTrue(Messages.getString("EspaceClientInternetServiceTest.21"), StringUtils.isNotBlank(infosConnexionDto.getMotDePasse()));
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.22"), 6, infosConnexionDto.getMotDePasse().length());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.23"), uidPersonne, infosConnexionDto.getUidPersonne());
        assertTrue(Messages.getString("EspaceClientInternetServiceTest.24"), infosConnexionDto.getActive());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.25"), 0, infosConnexionDto.getNbVisites());
        assertTrue(Messages.getString("EspaceClientInternetServiceTest.26"), infosConnexionDto.getPremiereVisite());
    }

    /**
     * Test unitaire du service de création d'espace client à partir d'un DTO pré-rempli.
     */
    @Test
    public void testCreerEspaceClientDtoPreRempli() {
        // On créé l'espace client de la personne
        final Long uidPersonne = 2L;
        final EspaceClientInternetDto espaceClientDto = new EspaceClientInternetDto(uidPersonne);
        espaceClientDto.setEid("30");
        espaceClientDto.setActive(false);
        final Calendar dateCreation = Calendar.getInstance();
        dateCreation.add(Calendar.YEAR, -1);
        espaceClientDto.setDateCreation(dateCreation);
        final Calendar dateModification = Calendar.getInstance();
        dateModification.add(Calendar.MONTH, -1);
        espaceClientDto.setDateModification(dateModification);
        final Calendar dateDerniereDematerialisation = (Calendar) dateCreation.clone();
        dateDerniereDematerialisation.add(Calendar.MONTH, 2);
        espaceClientDto.setDateDerniereDematerialisation(dateDerniereDematerialisation);
        final Calendar dateDerniereVisite = Calendar.getInstance();
        dateDerniereVisite.add(Calendar.DAY_OF_MONTH, -1);
        espaceClientDto.setDateDerniereVisite(dateDerniereVisite);
        espaceClientDto.setDateDesactivation(dateModification);
        final Calendar datePremiereVisite = (Calendar) dateCreation.clone();
        datePremiereVisite.add(Calendar.DAY_OF_MONTH, 7);
        espaceClientDto.setDatePremiereVisite(datePremiereVisite);
        espaceClientDto.setDateReactivation(dateModification);
        espaceClientDto.setLogin("loginPersonne" + uidPersonne);
        espaceClientDto.setMotDePasse("blabla");
        espaceClientDto.setNature(new IdentifiantLibelleDto(1L));
        espaceClientDto.setNbVisites(CINQUANTE);
        espaceClientDto.setPremiereVisite(false);
        final EspaceClientInternetDto espaceClientCree = espaceClientInternetService.creerEspaceClient(espaceClientDto);
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.30"), espaceClientDto.getEid(), espaceClientCree.getEid());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.31"), espaceClientDto.getLogin(), espaceClientCree.getLogin());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.32"), espaceClientDto.getMotDePasse(), espaceClientCree.getMotDePasse());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.33"), uidPersonne, espaceClientCree.getUidPersonne());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.34"), espaceClientDto.getActive(), espaceClientCree.getActive());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.35"), espaceClientDto.getPremiereVisite(), espaceClientCree.getPremiereVisite());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.36"), 0, espaceClientCree.getNbVisites());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.37"), espaceClientDto.getNature().getIdentifiant(),
            espaceClientCree.getNature().getIdentifiant());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.38"), espaceClientDto.getDateCreation(),
            espaceClientCree.getDateCreation());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.39"), espaceClientDto.getDateModification(),
            espaceClientCree.getDateModification());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.40"), espaceClientDto.getDateDerniereDematerialisation(),
            espaceClientCree.getDateDerniereDematerialisation());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.41"), espaceClientDto.getDatePremiereVisite(),
            espaceClientCree.getDatePremiereVisite());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.42"), espaceClientDto.getDateDerniereVisite(),
            espaceClientCree.getDateDerniereVisite());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.43"), espaceClientDto.getDateDesactivation(),
            espaceClientCree.getDateDesactivation());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.44"), espaceClientDto.getDateReactivation(),
            espaceClientCree.getDateReactivation());
    }

    /**
     * Test unitaire du service de mise à jour de l'espace client.
     */
    @Test
    public void testMajEspaceClient() {
        final Long idPersonne = 1L;
        final String nouveauLogin = "nouveauLogin";
        final String nouveauMotDePasse = "nouveauMdp";
        final EspaceClientInternetDto infosConnexion = espaceClientInternetService.getEspaceClientInternet(idPersonne);
        infosConnexion.setLogin(nouveauLogin);
        infosConnexion.setMotDePasse(nouveauMotDePasse);
        infosConnexion.setPremiereVisite(false);
        final EspaceClientInternetDto infosConnexionMaj = espaceClientInternetService.majEspaceClient(infosConnexion);
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.47"), nouveauLogin , infosConnexionMaj.getLogin());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.48"), infosConnexion.getMotDePasse(), infosConnexionMaj.getMotDePasse());
        assertFalse(Messages.getString("EspaceClientInternetServiceTest.49"), infosConnexionMaj.getPremiereVisite());
    }

    /**
     * Test le service de vérification de disponibilité d'identifiant de connexion.
     */
    @Test
    public void testVerifierLoginDisponible() {
        // On vérifie qu'on détecte bien un identifiant déjà utilisé
        String login = "login01";
        assertFalse(Messages.getString("EspaceClientInternetServiceTest.51") + login + Messages.getString("EspaceClientInternetServiceTest.52"),
            espaceClientInternetService.verifierLoginDisponible(login, 2L));
        login = "1015006";
        assertFalse(Messages.getString("EspaceClientInternetServiceTest.54") + login + Messages.getString("EspaceClientInternetServiceTest.55"),
            espaceClientInternetService.verifierLoginDisponible(login, 2L));
        login = "7894561";
        assertFalse(Messages.getString("EspaceClientInternetServiceTest.57") + login + Messages.getString("EspaceClientInternetServiceTest.58"),
            espaceClientInternetService.verifierLoginDisponible(login, 2L));
        // On vérifie qu'on détecte bien un identifiant disponible
        login = Messages.getString("EspaceClientInternetServiceTest.59");
        assertTrue(Messages.getString("EspaceClientInternetServiceTest.60") + login + Messages.getString("EspaceClientInternetServiceTest.61"),
            espaceClientInternetService.verifierLoginDisponible(login, 1L));
        login = "1015036";
        assertTrue(Messages.getString("EspaceClientInternetServiceTest.63") + login + Messages.getString("EspaceClientInternetServiceTest.64"),
            espaceClientInternetService.verifierLoginDisponible(login, 1L));
    }

    /**
     * Test le service d'authentification à l'espace client.
     */
    @Test
    public void testAuthentificationEspaceClient() {
        // Test authentification manuelle
        final Long uidPersonne1 = 1L;
        final int nbVisites = 1;
        final String login = LOGIN_PERSONNE1;
        final String motDePasse = PASSWORD_PERSONNE1;
        final String motDePasseEncrypte = ENCRYPTED_PASSWORD_PERSONNE1;
        IdentifiantsConnexionDto identificationParamsDto = new IdentifiantsConnexionDto(login, motDePasse);
        EspaceClientInternetDto infosConnexion = espaceClientInternetService.identificationEspaceClient(identificationParamsDto);

        assertEquals(Messages.getString("EspaceClientInternetServiceTest.65"), uidPersonne1, infosConnexion.getUidPersonne());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.66"), login, infosConnexion.getLogin());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.67"), motDePasse, infosConnexion.getMotDePasse());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.68"), nbVisites, infosConnexion.getNbVisites());

        // Test authentification automatique
        identificationParamsDto = new IdentifiantsConnexionDto(login, motDePasseEncrypte);
        identificationParamsDto.setMotDePasseEncrypte(true);
        identificationParamsDto.setMettreAJourInfosConnexion(false);
        infosConnexion = espaceClientInternetService.identificationEspaceClient(identificationParamsDto);

        assertEquals(Messages.getString("EspaceClientInternetServiceTest.69"), uidPersonne1, infosConnexion.getUidPersonne());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.70"), login, infosConnexion.getLogin());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.71"), motDePasse, infosConnexion.getMotDePasse());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.72"), nbVisites, infosConnexion.getNbVisites());
    }

    /**
     * Test le service d'authentification à l'espace client suivant les regles du 0.
     */
    @Test
    public void testAuthentificationEspaceClientRegleZero() {
        final Long uidPersonne6 = 6L;
        String login = "0123456";
        String motDePasse = PASSWORD_PERSONNE5;
        IdentifiantsConnexionDto identificationParamsDto = new IdentifiantsConnexionDto(login, motDePasse);
        EspaceClientInternetDto infosConnexion = espaceClientInternetService.identificationEspaceClient(identificationParamsDto);

        assertEquals(Messages.getString("EspaceClientInternetServiceTest.74"), uidPersonne6, infosConnexion.getUidPersonne());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.75"), login, infosConnexion.getLogin());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.76"), motDePasse, infosConnexion.getMotDePasse());

        final Long uidPersonne7 = 7L;
        login = "147852";
        motDePasse = PASSWORD_PERSONNE5;
        identificationParamsDto = new IdentifiantsConnexionDto(login, motDePasse);
        infosConnexion = espaceClientInternetService.identificationEspaceClient(identificationParamsDto);

        assertEquals(Messages.getString("EspaceClientInternetServiceTest.78"), uidPersonne7, infosConnexion.getUidPersonne());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.79"), login, infosConnexion.getLogin());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.80"), motDePasse, infosConnexion.getMotDePasse());

        login = "0147852";
        motDePasse = PASSWORD_PERSONNE5;
        identificationParamsDto = new IdentifiantsConnexionDto(login, motDePasse);
        infosConnexion = espaceClientInternetService.identificationEspaceClient(identificationParamsDto);

        assertEquals(Messages.getString("EspaceClientInternetServiceTest.82"), uidPersonne7, infosConnexion.getUidPersonne());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.83"), "147852", infosConnexion.getLogin());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.85"), motDePasse, infosConnexion.getMotDePasse());
    }

    /**
     * Test de fusion d'espace client.
     */
    @Test
    public void testFusionnerEspaceClientAdherentProspect() {
        final Long idPersonneAdherent = 1L;
        final Long idPersonneProspect = 5L;

        // on fusion l'espace client d'un prospect et d'un adhérent
        espaceClientInternetService.fusionnerEspaceClient(idPersonneProspect, idPersonneAdherent);
        final EspaceClientInternetDto connexionSource = espaceClientInternetService.getEspaceClientInternet(idPersonneProspect);
        final EspaceClientInternetDto connexionCible = espaceClientInternetService.getEspaceClientInternet(idPersonneAdherent);
        assertNull(Messages.getString("EspaceClientInternetServiceTest.86"), connexionSource);
        assertNotNull(ESPACE_CLIENT_INTERNET_CIBLE_DEVRAIT_EXISTER, connexionCible);
        assertEquals(ESPACE_CLIENT_INTERNET_POINTER_PERSONNE_CIBLE, idPersonneAdherent, connexionCible.getUidPersonne());
        assertEquals(ESPACE_CLIENT_INTERNET_CIBLE_PAS_BONNE, "login01", connexionCible.getLogin());
    }

    /**
     * Test de fusion d'espace client.
     */
    @Test
    public void testFusionnerAdherentBeneficiaireProspect() {
        final Long idPersonneAdherent = 1L;
        final Long idPersonneBeneficiaireProspect = 3L;

        // on fusion l'espace client d'un beneficiaire prospect et d'un adhérent
        espaceClientInternetService.fusionnerEspaceClient(idPersonneBeneficiaireProspect, idPersonneAdherent);
        final EspaceClientInternetDto connexionSource = espaceClientInternetService.getEspaceClientInternet(idPersonneBeneficiaireProspect);
        final EspaceClientInternetDto connexionCible = espaceClientInternetService.getEspaceClientInternet(idPersonneAdherent);
        assertNull(Messages.getString("EspaceClientInternetServiceTest.88"), connexionSource);
        assertNotNull(ESPACE_CLIENT_INTERNET_CIBLE_DEVRAIT_EXISTER, connexionCible);
        assertEquals(ESPACE_CLIENT_INTERNET_POINTER_PERSONNE_CIBLE, idPersonneAdherent, connexionCible.getUidPersonne());
        assertEquals(ESPACE_CLIENT_INTERNET_CIBLE_PAS_BONNE, "login01", connexionCible.getLogin());
    }

    /**
     * Test de fusion d'espace client.
     */
    @Test
    public void testFusionnerEspaceClientProspectActifProspect() {
        final Long idPersonneProspectConnexionActive = 5L;
        final Long idPersonneProspectConnexionNonActive = 4L;

        // on fusion l'espace client de deux prospect
        espaceClientInternetService.fusionnerEspaceClient(idPersonneProspectConnexionActive, idPersonneProspectConnexionNonActive);
        final EspaceClientInternetDto connexionSource = espaceClientInternetService.getEspaceClientInternet(idPersonneProspectConnexionActive);
        final EspaceClientInternetDto connexionCible = espaceClientInternetService.getEspaceClientInternet(idPersonneProspectConnexionNonActive);
        assertNull(Messages.getString("EspaceClientInternetServiceTest.90"), connexionSource);
        assertNotNull(ESPACE_CLIENT_INTERNET_CIBLE_DEVRAIT_EXISTER, connexionCible);
        assertEquals(ESPACE_CLIENT_INTERNET_POINTER_PERSONNE_CIBLE, idPersonneProspectConnexionNonActive, connexionCible.getUidPersonne());
        assertEquals(ESPACE_CLIENT_INTERNET_CIBLE_PAS_BONNE, Messages.getString("EspaceClientInternetServiceTest.91"), connexionCible.getLogin());
    }

    /**
     * Test de fusion d'espace client.
     */
    @Test
    public void testFusionnerEspaceClientProspectProspectActif() {
        final Long idPersonneProspectConnexionActive = 5L;
        final Long idPersonneProspectConnexionNonActive = 4L;

        // on fusion l'espace client de deux prospect
        espaceClientInternetService.fusionnerEspaceClient(idPersonneProspectConnexionNonActive, idPersonneProspectConnexionActive);
        final EspaceClientInternetDto connexionSource = espaceClientInternetService.getEspaceClientInternet(idPersonneProspectConnexionNonActive);
        final EspaceClientInternetDto connexionCible = espaceClientInternetService.getEspaceClientInternet(idPersonneProspectConnexionActive);
        assertNull(Messages.getString("EspaceClientInternetServiceTest.92"), connexionSource);
        assertNotNull(ESPACE_CLIENT_INTERNET_CIBLE_DEVRAIT_EXISTER, connexionCible);
        assertEquals(ESPACE_CLIENT_INTERNET_POINTER_PERSONNE_CIBLE, idPersonneProspectConnexionActive, connexionCible.getUidPersonne());
        assertEquals(ESPACE_CLIENT_INTERNET_CIBLE_PAS_BONNE, Messages.getString("EspaceClientInternetServiceTest.93"), connexionCible.getLogin());
    }

    /**
     * Test de fusion d'espace client.
     */
    @Test
    public void testFusionnerProspectBeneficiaireProspect() {
        final Long idPersonneProspect = 5L;
        final Long idPersonneBeneficiaireProspect = 3L;

        // on fusion l'espace client d'un beneficiaire prospect et d'un adhérent
        espaceClientInternetService.fusionnerEspaceClient(idPersonneProspect, idPersonneBeneficiaireProspect);
        final EspaceClientInternetDto connexionSource = espaceClientInternetService.getEspaceClientInternet(idPersonneProspect);
        final EspaceClientInternetDto connexionCible = espaceClientInternetService.getEspaceClientInternet(idPersonneBeneficiaireProspect);
        assertNull(Messages.getString("EspaceClientInternetServiceTest.94"), connexionSource);
        assertNotNull(ESPACE_CLIENT_INTERNET_CIBLE_DEVRAIT_EXISTER, connexionCible);
        assertEquals(ESPACE_CLIENT_INTERNET_POINTER_PERSONNE_CIBLE, idPersonneBeneficiaireProspect, connexionCible.getUidPersonne());
        assertEquals(ESPACE_CLIENT_INTERNET_CIBLE_PAS_BONNE, Messages.getString("EspaceClientInternetServiceTest.95"), connexionCible.getLogin());
    }

    /**
     * Test de fusion d'espace client.
     */
    @Test
    public void testGetInfoConnexionSimpleByNumClient() {
        final String email = Messages.getString("EspaceClientInternetServiceTest.96");

        String numClient = Messages.getString("EspaceClientInternetServiceTest.97");
        String mdp = PASSWORD_PERSONNE5;

        InformationConnexionSimpleDto infos = espaceClientInternetService.getInfoConnexionSimpleByNumClient(numClient);
        assertNotNull(Messages.getString("EspaceClientInternetServiceTest.98"), infos);
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.99"), email, infos.getEmail());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.100"), mdp, infos.getMotDePasse());

        numClient = Messages.getString("EspaceClientInternetServiceTest.101");
        mdp = PASSWORD_PERSONNE5;

        infos = espaceClientInternetService.getInfoConnexionSimpleByNumClient(numClient);
        assertNotNull(Messages.getString("EspaceClientInternetServiceTest.102"), infos);
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.103"), email, infos.getEmail());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.104"), mdp, infos.getMotDePasse());

        numClient = Messages.getString("EspaceClientInternetServiceTest.105");
        mdp = PASSWORD_PERSONNE5;

        infos = espaceClientInternetService.getInfoConnexionSimpleByNumClient(numClient);
        assertNotNull(Messages.getString("EspaceClientInternetServiceTest.106"), infos);
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.107"), email, infos.getEmail());
        assertEquals(Messages.getString("EspaceClientInternetServiceTest.108"), mdp, infos.getMotDePasse());
    }

}
