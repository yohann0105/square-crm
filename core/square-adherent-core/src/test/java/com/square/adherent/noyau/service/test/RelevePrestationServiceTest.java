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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.adherent.noyau.dto.fichier.FichierDto;
import com.square.adherent.noyau.dto.prestation.CritereSelectionRelevePrestationDto;
import com.square.adherent.noyau.dto.prestation.ParamRelevePrestationMailDto;
import com.square.adherent.noyau.dto.prestation.RelevePrestationDto;
import com.square.adherent.noyau.service.interfaces.RelevePrestationService;

/**
 * Classe contenant les tests unitaires des services de RelevePrestationService.
 * @author nnadeau - SCUB
 */
public class RelevePrestationServiceTest extends DbunitBaseTestCase {

    /** Service pour les RelevePrestation. */
    private RelevePrestationService relevePrestationService;

    private static final Long ID_PERSONNE_1 = 1L;

    private static final Long ID_PERSONNE_7 = 7L;

    private static final SimpleDateFormat SDF = new SimpleDateFormat(Messages.getString("RelevePrestationServiceTest.0"));

	private static final Long ONZE = 11L;

	private static final Long SEIZE = 16L;

    private boolean chargerBean = true;
    /**
     * Method called before each unit testing.
     */
    @Before
    public void setUp() {
        relevePrestationService = (RelevePrestationService) getBeanSpring("relevePrestationService");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getFichierContextSpringSup() {
        return new String[] {"adherentMappingContext.xml"};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getXmlDateSet() {
        return "dataset-releve-prestation.xml";
    }


    /** {@inheritDoc} */
    @Override
    public String getFichierRequetesSql() {
    	if (chargerBean) {
    		chargerBean = false;
    		return "square-object.sql";
    	}
    	return "square-object-vide.sql";
    }

    /** Test le moteur de recherche de relevés de prestations. */
    @Test
    public void testGetListeRelevesParReleveId() {
        final CritereSelectionRelevePrestationDto criteres = new CritereSelectionRelevePrestationDto();
        criteres.setRelevePrestationId(new Long(1));
        final List<RelevePrestationDto> list = relevePrestationService.getListeReleveParCriteres(criteres);
        assertEquals(Messages.getString("RelevePrestationServiceTest.4"), 1, list.size());
    }

    /** Test le moteur de recherche de relevés de prestations. */
    @Test
    public void testGetListeRelevesParAdherent() {
        final CritereSelectionRelevePrestationDto criteres = new CritereSelectionRelevePrestationDto();
        criteres.setIdPersonne(ID_PERSONNE_1);
        final List<RelevePrestationDto> list = relevePrestationService.getListeReleveParCriteres(criteres);
        assertEquals(Messages.getString("RelevePrestationServiceTest.5"), 2, list.size());
    }

    /** Test le moteur de recherche de relevés de prestations. */
    @Test
    public void testGetListeRelevesMultiplesCriteres() {
        final CritereSelectionRelevePrestationDto criteres = new CritereSelectionRelevePrestationDto();
        criteres.setRelevePrestationId(new Long(1));
        criteres.setEnvoyeMail(false);
        final List<RelevePrestationDto> list = relevePrestationService.getListeReleveParCriteres(criteres);
        assertEquals(Messages.getString("RelevePrestationServiceTest.6"), 1, list.size());
    }

    /** Test le moteur de recherche de relevés de prestations. */
    @Test
    public void testGetListeRelevesNonEnvoyes() {
        final CritereSelectionRelevePrestationDto criteres = new CritereSelectionRelevePrestationDto();
        criteres.setEnvoyeMail(false);
        final List<RelevePrestationDto> list = relevePrestationService.getListeReleveParCriteres(criteres);
        assertEquals(Messages.getString("RelevePrestationServiceTest.7"), 10, list.size());
    }

    /** Test le moteur de recherche de relevés de prestations. */
    @Test
    public void testGetListeRelevesParAdherentEnvoyes() {
        final CritereSelectionRelevePrestationDto criteres = new CritereSelectionRelevePrestationDto();
        criteres.setEnvoyeMail(true);
        criteres.setIdPersonne(ID_PERSONNE_7);
        final List<RelevePrestationDto> list = relevePrestationService.getListeReleveParCriteres(criteres);
        assertEquals(Messages.getString("RelevePrestationServiceTest.8"), 3, list.size());
    }

    /** Test le moteur de recherche de relevés de prestations. */
    @Test
    public void testGetListeRelevesParDateMax() {
        final CritereSelectionRelevePrestationDto criteres = new CritereSelectionRelevePrestationDto();
        final Calendar dateMax = Calendar.getInstance();
        dateMax.clear();
        // Lors de la récupération des dates d'impression des relevés dans le dataset,
        // Releve.dateImpression.mois = dataset.mois - 1;
        // Donc Janvier = 0, Février = 1, ... Juillet = 6, etc...
        final int annee = 2008;
        dateMax.set(annee, 6, 6);
        criteres.setDateMaxImpression(dateMax);
        final List<RelevePrestationDto> list = relevePrestationService.getListeReleveParCriteres(criteres);
        final int resultAttendu = 13;
        assertEquals(Messages.getString("RelevePrestationServiceTest.9"), resultAttendu, list.size());
    }

    /** Test le compte du nombre de candidats. */
    @Test
    public void testGetNombreCandidatsEnvoiMail() {
    	// FIXME le test ne peut pas passer car le dao pointe sur une table data_personnes_emails non reconnu par le model.
        // FIXME il y a un probleme de conception car le noyau adherent ne devrait pas connaitre le model du noyau square

    	// Validation temporaire, utilisation de requete sql au chargement ( cf test/ressource/square-object.sql)
        final Integer count = relevePrestationService.getNombreCandidatsEnvoiRelevesPrestationEmail();
        assertEquals(Messages.getString("RelevePrestationServiceTest.10"), 5, count.intValue());
    }

    /** Test la récupération paginée des candidats. */
    @Test
    public void testGetCandidatsEnvoiMail() {
    	// FIXME le test ne peut pas passer car le dao pointe sur une table data_personnes_emails non reconnu par le model.
        // FIXME il y a un probleme de conception car le noyau adherent ne devrait pas connaitre le model du noyau square

    	// Validation temporaire, utilisation de requete sql au chargement ( cf test/ressource/square-object.sql)
    	final int maxResult = 3;
        final List<Long> listeIdsExclus = new ArrayList<Long>();
        final int nbCandidats = relevePrestationService.getNombreCandidatsEnvoiRelevesPrestationEmail();
        List<Long> candidats = relevePrestationService.getCandidatsEnvoiRelevesPrestationEmail(0, maxResult, listeIdsExclus);
        assertEquals(Messages.getString("RelevePrestationServiceTest.11"), 5, nbCandidats);
        assertEquals(Messages.getString("RelevePrestationServiceTest.12"), maxResult, candidats.size());
        assertEquals(Messages.getString("RelevePrestationServiceTest.13"), 3L, candidats.get(0));
        assertEquals(Messages.getString("RelevePrestationServiceTest.14"), 4L, candidats.get(1));
        assertEquals(Messages.getString("RelevePrestationServiceTest.15"), 10L, candidats.get(2));
        listeIdsExclus.add(ONZE);
        candidats = relevePrestationService.getCandidatsEnvoiRelevesPrestationEmail(3, maxResult, listeIdsExclus);
        assertEquals(Messages.getString("RelevePrestationServiceTest.16"), 1, candidats.size());
        assertEquals(Messages.getString("RelevePrestationServiceTest.17"), SEIZE, candidats.get(0));
    }

    /** Test l'ajout d'un releve de prestation. */
    @Test
    public void testAjouterRelevePrestaNotMax() {
        // nomFichier = N° adhérent ++ "_" ++ Date au format AAAAMMJJ ++ "_" ++ Mutuelle ++ "_" ++ Mode de paiement.pdf
        final String nomFichier = "01_20080710_Smatis_virement.pdf";
        relevePrestationService.ajouterRelevePrestation(nomFichier);
        final CritereSelectionRelevePrestationDto critere = new CritereSelectionRelevePrestationDto();
        critere.setIdPersonne(1L);
        final List<RelevePrestationDto> result = relevePrestationService.getListeReleveParCriteres(critere);
        assertEquals(Messages.getString("RelevePrestationServiceTest.19"), 3, result.size());
        assertEquals(Messages.getString("RelevePrestationServiceTest.20"), new Long(1), result.get(0).getId());
        assertEquals(Messages.getString("RelevePrestationServiceTest.21"), new Long(2), result.get(1).getId());
        assertNotNull(Messages.getString("RelevePrestationServiceTest.22"), result.get(2).getId());
        final Calendar date = Calendar.getInstance();
        date.clear();
        final int annee = 2008;
        date.set(annee, 6, 10);
        assertEquals(Messages.getString("RelevePrestationServiceTest.23"), SDF.format(date.getTime()), SDF.format(result.get(2).getDateImpression()
                .getTime()));
    }

    /** Test l'ajout d'un releve de prestation. */
    @Test
    public void testAjouterRelevePrestaMax() {
        // nomFichier = N° adhérent ++ "_" ++ Date au format AAAAMMJJ ++ "_" ++ Mutuelle ++ "_" ++ Mode de paiement.pdf
        final String nomFichier = "07_20080710_Smatis_virement.pdf";
        relevePrestationService.ajouterRelevePrestation(nomFichier);
        final CritereSelectionRelevePrestationDto critere = new CritereSelectionRelevePrestationDto();
        critere.setIdPersonne(7L);
        final List<RelevePrestationDto> result = relevePrestationService.getListeReleveParCriteres(critere);
        assertEquals(Messages.getString("RelevePrestationServiceTest.25"), 3, result.size());
        assertEquals(Messages.getString("RelevePrestationServiceTest.26"), new Long(6), result.get(0).getId());
        assertEquals(Messages.getString("RelevePrestationServiceTest.27"), new Long(7), result.get(1).getId());
        assertNotNull(Messages.getString("RelevePrestationServiceTest.28"), result.get(2).getId());
        final Calendar date = Calendar.getInstance();
        date.clear();
        final int annee = 2008;
        date.set(annee, 6, 10);
        assertEquals(Messages.getString("RelevePrestationServiceTest.29"), SDF.format(date.getTime()), SDF.format(result.get(2).getDateImpression()
                .getTime()));
    }

    /** Test la conversion d'un relevé. */
    @Test
    public void testGetByteArray() {
        final FichierDto fichier = relevePrestationService.getRelevePrestationByteArray(new Long(1), true);
        assertEquals(Messages.getString("RelevePrestationServiceTest.30"), "releve_prestations_20080310.pdf", fichier.getNomFichier());
        assertNotNull(Messages.getString("RelevePrestationServiceTest.32"), fichier.getContenu());
        assertNotNull(Messages.getString("RelevePrestationServiceTest.33"), fichier.getTypeMime());

        // Création des fichiers pour le test
        final File fichierImage1 = new File("/tmp/duplicata.pdf");

        try {
            fichierImage1.createNewFile();
            final FileOutputStream output = new FileOutputStream(fichierImage1);
            output.write(fichier.getContenu());
            output.close();
        } catch (FileNotFoundException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /** Test l'envoi de relevé de prestations par mail. */
    @Test
    public void testEnvoiRelevePrestationsParMail() {
        final ParamRelevePrestationMailDto params = new ParamRelevePrestationMailDto();
        params.setIdPersonne(1L);
        params.setForceEnvoyeMail(true);
        relevePrestationService.envoyerParMailRelevesPrestations(params);

        final CritereSelectionRelevePrestationDto critere = new CritereSelectionRelevePrestationDto();
        critere.setIdPersonne(1L);
        critere.setEnvoyeMail(true);
        final List<RelevePrestationDto> result = relevePrestationService.getListeReleveParCriteres(critere);
        assertEquals(Messages.getString("RelevePrestationServiceTest.35"), 1, result.size());
    }
}
