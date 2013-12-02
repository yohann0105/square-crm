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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.model.dto.AgendasDisponiblesCriteresDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.RendezVousCriteresRechercheDto;
import com.square.core.model.dto.RendezVousDto;
import com.square.core.service.interfaces.AgendaService;
import com.square.core.util.AgendaKeyUtil;

/**
 * Test sur les services de l'agenda.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class AgendaServiceTest extends DbunitBaseTestCase {

    private static final String DATE_NE_CORRESPOND_PAS = Messages.getString("AgendaServiceTest.0");

    private static final String MESSAGE_ERREUR_MAUVAIS_MESSAGE = Messages.getString("AgendaServiceTest.1");

    private static final String MESSAGE_FAIL = Messages.getString("AgendaServiceTest.2");

    private AgendaService agendaService;

    private MessageSourceUtil messageSourceUtil;

    private SimpleDateFormat sdf = new SimpleDateFormat(Messages.getString("AgendaServiceTest.3"));

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        agendaService = (AgendaService) getBeanSpring("agendaService");
        createSecureContext("user", "user");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"squareMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "datasetAction.xml";
    }

    @Override
    protected String[] getIncludeTableOnDeleteOperation() {
        return new String[] {"DATA_DOCUMENT", "DATA_COMMENTAIRE", "DATA_ACTION"}; //$NON-NLS-3$
    }

    /**
     * Test de la récupération de rendez vous pour l'agenda.
     */
    @Test
    public void testRechercherRendezVousParCriteres() {
        // Test si le dto de critères de recherche est null
        try {
            agendaService.rechercherRendezVousParCriteres(null);
            fail(MESSAGE_FAIL);
        } catch (BusinessException be) {
            assertEquals(MESSAGE_ERREUR_MAUVAIS_MESSAGE, messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_CRITERES_NULL), be.getMessage());
        }

        // Test avec une ressource null
        final RendezVousCriteresRechercheDto criteres = new RendezVousCriteresRechercheDto();
        try {
            agendaService.rechercherRendezVousParCriteres(criteres);
            fail(MESSAGE_FAIL);
        } catch (BusinessException be) {
            assertEquals(MESSAGE_ERREUR_MAUVAIS_MESSAGE, messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_RESSOURCE_NULL), be.getMessage());
        }
        // Test avec une date min null
        criteres.setIdRessource(1L);
        criteres.setDateMinDateDebut(null);
        criteres.setDateMaxDateDebut(Calendar.getInstance());
        try {
            agendaService.rechercherRendezVousParCriteres(criteres);
            fail(MESSAGE_FAIL);
        } catch (BusinessException be) {
            assertEquals(MESSAGE_ERREUR_MAUVAIS_MESSAGE, messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_DATE_MIN_NULL), be.getMessage());
        }
        // Test avec une date max null
        criteres.setDateMaxDateDebut(null);
        criteres.setDateMinDateDebut(Calendar.getInstance());
        try {
            agendaService.rechercherRendezVousParCriteres(criteres);
            fail(MESSAGE_FAIL);
        } catch (BusinessException be) {
            assertEquals(MESSAGE_ERREUR_MAUVAIS_MESSAGE, messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_DATE_MAX_NULL), be.getMessage());
        }
        // Test avec une date min supérieure à la date max
        Calendar dateMin = Calendar.getInstance();
        dateMin.add(Calendar.YEAR, 1);
        criteres.setDateMinDateDebut(dateMin);
        criteres.setDateMaxDateDebut(Calendar.getInstance());
        try {
            agendaService.rechercherRendezVousParCriteres(criteres);
            fail(MESSAGE_FAIL);
        } catch (BusinessException be) {
            assertEquals(MESSAGE_ERREUR_MAUVAIS_MESSAGE, messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_DATE_MIN_SUP_DATE_MAX), be.getMessage());
        }
        // Test de récupération des rendez vous
        dateMin = Calendar.getInstance();
        dateMin.clear();
        final int year = 2010;
        dateMin.set(year, Calendar.JANUARY, 4);
        Calendar dateMax = Calendar.getInstance();
        dateMax.clear();
        dateMax.set(year, Calendar.JANUARY, 4, 23, 59, 59);
        criteres.setDateMinDateDebut(dateMin);
        criteres.setDateMaxDateDebut(dateMax);

        final List<RendezVousDto> resultat = agendaService.rechercherRendezVousParCriteres(criteres);
        // Vérification des informations retournées
        assertNotNull("AgendaServiceTest.86", resultat);
        assertEquals(Messages.getString("AgendaServiceTest.14"), 5, resultat.size());

        // on verifie l'ordre
        assertEquals(DATE_NE_CORRESPOND_PAS, "04/01/2010 12:00", sdf.format(resultat.get(0).getDate().getTime()));
        assertEquals(DATE_NE_CORRESPOND_PAS, "04/01/2010 14:00", sdf.format(resultat.get(1).getDate().getTime()));
        assertEquals(DATE_NE_CORRESPOND_PAS, "28/08/2011 10:00", sdf.format(resultat.get(2).getDate().getTime()));
        assertEquals(DATE_NE_CORRESPOND_PAS, "28/08/2011 11:00", sdf.format(resultat.get(3).getDate().getTime()));
        assertEquals(DATE_NE_CORRESPOND_PAS, "28/08/2011 15:00", sdf.format(resultat.get(4).getDate().getTime()));

        assertEquals(Messages.getString("AgendaServiceTest.20"), 13L, resultat.get(0).getIdAction());
        assertEquals(Messages.getString("AgendaServiceTest.21"), "NOMSEPT", resultat.get(0).getNomPersonne());
        assertEquals(Messages.getString("AgendaServiceTest.23"), "PrénomSept", resultat.get(0).getPrenomPersonne());
        assertNull(Messages.getString("AgendaServiceTest.25"), resultat.get(0).getRaisonSociale());
        assertEquals(Messages.getString("AgendaServiceTest.26"), 1L, resultat.get(0).getIdStatut());
        assertEquals(Messages.getString("AgendaServiceTest.27"), "Type 2", resultat.get(0).getType());
        assertEquals(Messages.getString("AgendaServiceTest.29"), "Objet 3", resultat.get(0).getObjet());
        assertEquals(Messages.getString("AgendaServiceTest.31"), "Sous objet 1", resultat.get(0).getSousObjet());
        assertEquals(Messages.getString("AgendaServiceTest.33"), "NOMSEPT PrénomSept", resultat.get(0).getTitre());
        assertEquals(Messages.getString("AgendaServiceTest.35"), 480, resultat.get(0).getNbMinutesDuree());

        assertEquals(Messages.getString("AgendaServiceTest.36"), "Rendez vous 1", resultat.get(2).getTitre());
        assertEquals(Messages.getString("AgendaServiceTest.38"), 60, resultat.get(2).getNbMinutesDuree());

        // Récupération de rendez vous avec une personne Morale
        dateMin = Calendar.getInstance();
        dateMin.clear();
        dateMin.set(year, Calendar.JANUARY, 27, 0, 0, 0);
        dateMax = Calendar.getInstance();
        dateMax.clear();
        dateMax.set(year, Calendar.JANUARY, 27, 23, 59, 59);
        criteres.setDateMinDateDebut(dateMin);
        criteres.setDateMaxDateDebut(dateMax);

        final List<RendezVousDto> resultatPersonneMorale = agendaService.rechercherRendezVousParCriteres(criteres);

        assertNotNull(Messages.getString("AgendaServiceTest.39"), resultatPersonneMorale);
        assertEquals(Messages.getString("AgendaServiceTest.40"), 4, resultatPersonneMorale.size());
        assertEquals(Messages.getString("AgendaServiceTest.41"), 27L, resultatPersonneMorale.get(0).getIdAction());
        assertEquals(DATE_NE_CORRESPOND_PAS, "27/01/2010 12:00", sdf.format(resultatPersonneMorale.get(0).getDate().getTime()));
        assertNull(Messages.getString("AgendaServiceTest.43"), resultatPersonneMorale.get(0).getNomPersonne());
        assertNull(Messages.getString("AgendaServiceTest.44"), resultatPersonneMorale.get(0).getPrenomPersonne());
        assertEquals(Messages.getString("AgendaServiceTest.45"), "RAISONSOCIALE", resultatPersonneMorale.get(0).getRaisonSociale());
        assertEquals(Messages.getString("AgendaServiceTest.47"), 1L, resultatPersonneMorale.get(0).getIdStatut());
        assertEquals(Messages.getString("AgendaServiceTest.48"), "Type 2", resultatPersonneMorale.get(0).getType());
        assertEquals(Messages.getString("AgendaServiceTest.50"), "Objet 3", resultatPersonneMorale.get(0).getObjet());
        assertEquals(Messages.getString("AgendaServiceTest.52"), "Sous objet 1", resultatPersonneMorale.get(0).getSousObjet());
        assertEquals(Messages.getString("AgendaServiceTest.54"), "1012 RAISONSOCIALE", resultatPersonneMorale.get(0).getTitre());
    }

    /**
     * Test de la récupération des agendas disponibles pour un admin.
     */
    @Test
    public void testRechercherAgendasDisponiblesAdmin() {
        try {
            agendaService.rechercherAgendasDisponibles(null);
            fail(MESSAGE_FAIL);
        } catch (BusinessException b) {
            assertEquals(MESSAGE_ERREUR_MAUVAIS_MESSAGE, messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_CRITERES_NULL), b.getMessage());
        }

        AgendasDisponiblesCriteresDto criteres = new AgendasDisponiblesCriteresDto();
        List<DimensionRessourceDto> listeAgendas = agendaService.rechercherAgendasDisponibles(criteres);
        assertEquals(Messages.getString("AgendaServiceTest.56"), 5, listeAgendas.size());
        assertEquals(Messages.getString("AgendaServiceTest.57"), 2L, listeAgendas.get(0).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.58"), 5L, listeAgendas.get(1).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.59"), 2L, listeAgendas.get(2).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.60"), 3L, listeAgendas.get(3).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.61"), 4L, listeAgendas.get(4).getIdentifiant());

        criteres = new AgendasDisponiblesCriteresDto();
        criteres.setLibelle("nom");
        listeAgendas = agendaService.rechercherAgendasDisponibles(criteres);
        assertEquals(Messages.getString("AgendaServiceTest.63"), 4, listeAgendas.size());
        assertEquals(Messages.getString("AgendaServiceTest.64"), 2L, listeAgendas.get(0).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.65"), 2L, listeAgendas.get(1).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.66"), 3L, listeAgendas.get(2).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.67"), 4L, listeAgendas.get(3).getIdentifiant());

        criteres = new AgendasDisponiblesCriteresDto();
        criteres.setLibelle("nom2");
        listeAgendas = agendaService.rechercherAgendasDisponibles(criteres);
        assertEquals(Messages.getString("AgendaServiceTest.69"), 2, listeAgendas.size());
        assertEquals(Messages.getString("AgendaServiceTest.70"), 2L, listeAgendas.get(0).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.71"), 2L, listeAgendas.get(1).getIdentifiant());

        criteres = new AgendasDisponiblesCriteresDto();
        criteres.setLibelle("POAZI");
        listeAgendas = agendaService.rechercherAgendasDisponibles(criteres);
        assertEquals(Messages.getString("AgendaServiceTest.73"), 1, listeAgendas.size());
        assertEquals(Messages.getString("AgendaServiceTest.74"), 2L, listeAgendas.get(0).getIdentifiant());
    }

    /**
     * Test de la récupération des agendas disponibles pour un animateur.
     */
    @Test
    public void testRechercherAgendasDisponiblesAnimateur() {
        AgendasDisponiblesCriteresDto criteres = new AgendasDisponiblesCriteresDto();
        criteres.setIdAnimateur(5L);
        List<DimensionRessourceDto> listeAgendas = agendaService.rechercherAgendasDisponibles(criteres);
        assertEquals(Messages.getString("AgendaServiceTest.75"), 3, listeAgendas.size());
        assertEquals(Messages.getString("AgendaServiceTest.76"), 2L, listeAgendas.get(0).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.77"), 2L, listeAgendas.get(1).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.78"), 4L, listeAgendas.get(2).getIdentifiant());

        criteres = new AgendasDisponiblesCriteresDto();
        criteres.setIdAnimateur(5L);
        criteres.setLibelle("nom2");
        listeAgendas = agendaService.rechercherAgendasDisponibles(criteres);
        assertEquals(Messages.getString("AgendaServiceTest.80"), 2, listeAgendas.size());
        assertEquals(Messages.getString("AgendaServiceTest.81"), 2L, listeAgendas.get(0).getIdentifiant());
        assertEquals(Messages.getString("AgendaServiceTest.82"), 2L, listeAgendas.get(1).getIdentifiant());

        criteres = new AgendasDisponiblesCriteresDto();
        criteres.setIdAnimateur(5L);
        criteres.setLibelle("POAZI");
        listeAgendas = agendaService.rechercherAgendasDisponibles(criteres);
        assertEquals(Messages.getString("AgendaServiceTest.84"), 1, listeAgendas.size());
        assertEquals(Messages.getString("AgendaServiceTest.85"), 2L, listeAgendas.get(0).getIdentifiant());

        criteres = new AgendasDisponiblesCriteresDto();
        criteres.setIdAnimateur(2L);
        try {
            agendaService.rechercherAgendasDisponibles(criteres);
            fail(MESSAGE_FAIL);
        } catch (BusinessException b) {
            assertEquals(MESSAGE_ERREUR_MAUVAIS_MESSAGE, messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_PAS_ANIMATEUR), b.getMessage());
        }
    }
}
