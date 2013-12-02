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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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

import com.square.core.model.dto.ActionCreationDto;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.ActionDto;
import com.square.core.model.dto.ActionModificationDto;
import com.square.core.model.dto.ActionRechercheDto;
import com.square.core.model.dto.ActionSyntheseDto;
import com.square.core.model.dto.CritereActionSyntheseDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.DocumentDto;
import com.square.core.model.dto.HistoriqueCommentaireDto;
import com.square.core.model.dto.MessagePublishDto;
import com.square.core.model.dto.OpportuniteCriteresRechercheDto;
import com.square.core.model.dto.OpportuniteSimpleDto;
import com.square.core.model.dto.RapportDto;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.model.plugin.NotificateurSquarePlugin;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.ActionKeyUtil;

/**
 * Test sur les services de action.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionServiceTest extends DbunitBaseTestCase {

    /**
     * EXCEPTION_LEVEE_DIFFERENTE_DE_CELLE_ATTENDUE.
     */
    private static final String EXCEPTION_LEVEE_DIFFERENTE_DE_CELLE_ATTENDUE = Messages.getString("ActionServiceTest.0");

    private static final String NOMBRE_ACTIONS_PAS_BON = Messages.getString("ActionServiceTest.1");

    private static final int NB_TOTAL_ACTIONS = 37;

    /** Libelle sousObjet1. */
    private static final String SOUS_OBJET_1 = "Sous objet 1";

	private static final String SOUS_OBJET_2 = "Sous objet 2";

    /** Libelle SOUS_OBJET_3. */
    private static final String SOUS_OBJET_3 = "Sous objet 3";

    /** Libelle Objet1. */
    private static final String OBJET_1 = "Objet 1";

    /** Libelle Objet2. */
    private static final String OBJET_2 = "Objet 2";

    /** Libelle Objet 3. */
    private static final String OBJET_3 = "Objet 3";

    /** Libelle statut 1. */
    private static final String STATUT_1 = "Statut 1";

    /** Libelle priorité 1. */
    private static final String PRIORITE_1 = "Priorité 1";

	private static final String PRIORITE_2 = "priorite 2";

    /** Libelle priorite 3. */
    private static final String PRIORITE_3 = "Priorité 3";

    /** Libelle Type2. */
    private static final String TYPE_2 = "Type 2";


    private ActionService actionService;

    private MessageSourceUtil messageSourceUtil;

    private SquareMappingService squareMappingService;

    /** Le service des opportunités. */
    private OpportuniteService opportuniteService;

    private NotificateurSquarePlugin notificateurPlugin;

    /** Message d'erreur lorsque le message attendu ne correspond pas. */
    private static String messageErreurMauvaisMessage = Messages.getString("ActionServiceTest.2");

    /** Message d'erreur pour les fail. */
    private static String messageFail = Messages.getString("ActionServiceTest.3");

    /** Message d'erreur lorsque la priorité ne correspond pas. */
    private static String prioriteNeCorrespondPas = Messages.getString("ActionServiceTest.4");

    /** Message d'erreur lorsqu'un objet ne correspond pas. */
    private static String objetNeCorrespondPas = Messages.getString("ActionServiceTest.5");

    /** Message d'erreur lorsqu'un sous objet ne correspond pas. */
    private static String sousObjetNeCorrespondPas = Messages.getString("ActionServiceTest.6");

    /** Message d'erreur lorsque le statut ne correspond pas. */
    private static String statutNeCorrespondPas = Messages.getString("ActionServiceTest.7");



    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        notificateurPlugin = (NotificateurSquarePlugin) getBeanSpring("notificateurSquarePlugin");
        actionService = (ActionService) getBeanSpring("actionService");
        squareMappingService = (SquareMappingService) getBeanSpring("squareMappingService");
        opportuniteService = (OpportuniteService) getBeanSpring("opportuniteService");
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
     * Test notification recherche.
     */
    @Test
    public void notificationRecherche() {
        final ActionCritereRechercheDto criteres = new ActionCritereRechercheDto();
        criteres.setDateNotification(Calendar.getInstance());
        final RemotePagingCriteriasDto<ActionCritereRechercheDto> criterias =
            new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteres, 0, Integer.MAX_VALUE);

        RemotePagingResultsDto<ActionRechercheDto> resultPagination = new RemotePagingResultsDto<ActionRechercheDto>();
        List<ActionRechercheDto> result = resultPagination.getListResults();
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        final MessagePublishDto message = new MessagePublishDto();
        message.setIdUtilisateur(3L);
        message.setTexte(Messages.getString("ActionServiceTest.29") + result.get(0).getNomPersonne() + " " + result.get(0).getPrenomPersonne());
        message.setTitre(Messages.getString("ActionServiceTest.31"));
        notificateurPlugin.publierMessage(message);
        assertNotNull(Messages.getString("ActionServiceTest.32"), result);
        assertEquals(Messages.getString("ActionServiceTest.33"), 1, resultPagination.getTotalResults());
    }

    /**
     * Test de la recherche d'une action par critère.
     */
    @Test
    public void rechercheActionTest() {

        // Test de la recherche avec dto de critère de recherche nul
        try {
            actionService.rechercherActionParCritere(null);
            fail(Messages.getString("ActionServiceTest.34"));
        } catch (BusinessException e) {
            assertEquals(Messages.getString("ActionServiceTest.35"), messageSourceUtil
                    .get(ActionKeyUtil.MESSAGE_ERREUR_RECHERCHE_ACTION_DTO_NULL), e.getMessage());
        }

        final ActionCritereRechercheDto criteres = new ActionCritereRechercheDto();
        final RemotePagingCriteriasDto<ActionCritereRechercheDto> criterias =
            new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteres, 0, Integer.MAX_VALUE);

        RemotePagingResultsDto<ActionRechercheDto> resultPagination = new RemotePagingResultsDto<ActionRechercheDto>();
        List<ActionRechercheDto> result = resultPagination.getListResults();

        // Test de la recherche sans aucun critère
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        final int nbObjets1 = 37;
        assertNotNull(Messages.getString("ActionServiceTest.36"), result);
        assertEquals(Messages.getString("ActionServiceTest.37"), nbObjets1, resultPagination.getTotalResults());
        assertEquals(Messages.getString("ActionServiceTest.38"), nbObjets1, result.size());
        assertEquals(Messages.getString("ActionServiceTest.39"), "Type 3", result.get(0).getType());
        assertEquals(objetNeCorrespondPas, OBJET_2, result.get(1).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_2, result.get(1).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(1).getStatut());
        assertEquals(prioriteNeCorrespondPas, "Priorité 2", result.get(1).getPriorite());

        // Test de la recherche par type
        final int nbObjets2 = 2;
        criteres.setIdType(1L);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.43"), result);
        assertEquals(Messages.getString("ActionServiceTest.44"), nbObjets2, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_2, result.get(0).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_2, result.get(0).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(0).getStatut());
        assertEquals(prioriteNeCorrespondPas, "Priorité 2", result.get(0).getPriorite());

        // Test de la recherche par nature
        final int nbObjets3 = 2;
        criteres.setIdType(null);
        List<Long> listeNatureActions = new ArrayList<Long>();
        listeNatureActions.add(1L);
        criteres.setListeNatureActions(listeNatureActions);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.47"), result);
        assertEquals(Messages.getString("ActionServiceTest.48"), nbObjets3, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_2, result.get(0).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_2, result.get(0).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(0).getStatut());
        assertEquals(prioriteNeCorrespondPas, "Priorité 2", result.get(0).getPriorite());

        // Test de la recherche sur plusieurs natures
        final int nbObjets4 = 33;
        listeNatureActions = new ArrayList<Long>();
        listeNatureActions.add(1L);
        listeNatureActions.add(2L);
        criteres.setListeNatureActions(listeNatureActions);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.51"), result);
        assertEquals(Messages.getString("ActionServiceTest.52"), nbObjets4, result.size());

        // Test de la recherche par nature des résultats
        final int nbObjets5 = 2;
        criteres.setListeNatureActions(null);
        List<Long> listeNatureResultats = new ArrayList<Long>();
        listeNatureResultats.add(3L);
        criteres.setListeNatureResultats(listeNatureResultats);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.53"), result);
        assertEquals(Messages.getString("ActionServiceTest.54"), nbObjets5, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_2, result.get(0).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_2, result.get(0).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(0).getStatut());
        assertEquals(prioriteNeCorrespondPas, "Priorité 2", result.get(0).getPriorite());

        // Test de la recherche par plusieurs natures résultats
        final int nbObjets6 = 3;
        criteres.setListeNatureActions(null);
        listeNatureResultats = new ArrayList<Long>();
        listeNatureResultats.add(1L);
        listeNatureResultats.add(3L);
        criteres.setListeNatureResultats(listeNatureResultats);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.57"), result);
        assertEquals(Messages.getString("ActionServiceTest.58"), nbObjets6, result.size());

        // Test de la recherche par priorité
        final int nbObjets7 = 30;
        criteres.setListeNatureResultats(null);
        List<Long> listePriorites = new ArrayList<Long>();
        listePriorites.add(1L);
        criteres.setListePriorites(listePriorites);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.59"), result);
        assertEquals(Messages.getString("ActionServiceTest.60"), nbObjets7, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_3, result.get(1).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_1, result.get(1).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(1).getStatut());
        assertEquals(prioriteNeCorrespondPas, PRIORITE_1, result.get(1).getPriorite());

        // Test de la recherche sur plusieurs priorités
        final int nbObjets8 = 32;
        criteres.setListeNatureResultats(null);
        listePriorites = new ArrayList<Long>();
        listePriorites.add(1L);
        listePriorites.add(2L);
        criteres.setListePriorites(listePriorites);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.61"), result);
        assertEquals(Messages.getString("ActionServiceTest.62"), nbObjets8, result.size());

        // Test de la recherche par résultat
        final int nbObjets9 = 20;
        criteres.setListePriorites(null);
        List<Long> listeResultats = new ArrayList<Long>();
        listeResultats.add(3L);
        criteres.setListeResultats(listeResultats);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.63"), result);
        assertEquals(Messages.getString("ActionServiceTest.64"), nbObjets9, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_3, result.get(1).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_1, result.get(1).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(1).getStatut());
        assertEquals(prioriteNeCorrespondPas, PRIORITE_3, result.get(1).getPriorite());

        // Test de la recherche sur plusieurs résultats
        final int nbObjets10 = 3;
        criteres.setListePriorites(null);
        listeResultats = new ArrayList<Long>();
        listeResultats.add(1L);
        listeResultats.add(2L);
        criteres.setListeResultats(listeResultats);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.65"), result);
        assertEquals(Messages.getString("ActionServiceTest.66"), nbObjets10, result.size());

        // Test de la recherche par statut
        final int nbObjets11 = 27;
        criteres.setListeNatureResultats(null);
        criteres.setListeResultats(null);
        List<Long> listeStatuts = new ArrayList<Long>();
        listeStatuts.add(1L);
        criteres.setListeStatuts(listeStatuts);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.67"), result);
        assertEquals(Messages.getString("ActionServiceTest.68"), nbObjets11, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_2, result.get(0).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_3, result.get(1).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(1).getStatut());
        assertEquals(prioriteNeCorrespondPas, PRIORITE_3, result.get(1).getPriorite());

        // Test de la recherche sur plusieurs statuts
        final int nbObjets12 = 10;
        criteres.setListeNatureResultats(null);
        criteres.setListeResultats(null);
        listeStatuts = new ArrayList<Long>();
        listeStatuts.add(2L);
        listeStatuts.add(3L);
        criteres.setListeStatuts(listeStatuts);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.69"), result);
        assertEquals(Messages.getString("ActionServiceTest.70"), nbObjets12, result.size());

        // Test de la recherche par campagne
        final int nbObjets13 = 25;
        criteres.setListeStatuts(null);
        List<Long> listeCampagnes = new ArrayList<Long>();
        listeCampagnes.add(1L);
        listeCampagnes.add(2L);
        criteres.setListeCampagnes(listeCampagnes);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.71"), result);
        assertEquals(Messages.getString("ActionServiceTest.72"), nbObjets13, result.size());

        // Test de la recherche par plusieurs campagnes
        criteres.setListeStatuts(null);
        listeCampagnes = new ArrayList<Long>();
        listeCampagnes.add(1L);
        criteres.setListeCampagnes(listeCampagnes);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.73"), result);

        final int nbObjets14 = 18;
        assertEquals(Messages.getString("ActionServiceTest.74"), nbObjets14, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_3, result.get(0).getObjet());
        assertEquals(sousObjetNeCorrespondPas, "Sous objet 1", result.get(0).getSousObjet());
        assertEquals(statutNeCorrespondPas, "Statut 1", result.get(0).getStatut());
        assertEquals(prioriteNeCorrespondPas, PRIORITE_3, result.get(0).getPriorite());

        // Test de la recherche par objet
        final int nbObjets15 = 1;
        criteres.setListeCampagnes(null);
        criteres.setIdObjet(1L);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.77"), result);
        assertEquals(Messages.getString("ActionServiceTest.78"), nbObjets15, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_1, result.get(0).getObjet());
        assertEquals(sousObjetNeCorrespondPas, "Sous objet 3", result.get(0).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(0).getStatut());
        assertEquals(prioriteNeCorrespondPas, "Priorité 3", result.get(0).getPriorite());

        // Test de la recherche par sous objet
        criteres.setIdObjet(null);
        criteres.setIdSousObjet(2L);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.81"), result);
        assertEquals(objetNeCorrespondPas, OBJET_2, result.get(0).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_2, result.get(0).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(0).getStatut());
        assertEquals(prioriteNeCorrespondPas, "Priorité 2", result.get(0).getPriorite());

        // Test de la recherche par créateur d'action.
        criteres.setIdSousObjet(null);
        final List<Long> listeCreateurs = new ArrayList<Long>();
        listeCreateurs.add(1L);
        criteres.setIdCreateurs(listeCreateurs);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.84"), result);
        final int nbrObjetCreateur = 30;
        assertEquals(Messages.getString("ActionServiceTest.85"), nbrObjetCreateur, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_3, result.get(1).getObjet());
        assertEquals(sousObjetNeCorrespondPas, "Sous objet 1", result.get(1).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(1).getStatut());
        assertEquals(prioriteNeCorrespondPas, "Priorité 3", result.get(1).getPriorite());

        // Test de la recherche par commercial responsable de l'action.
        criteres.setIdCreateurs(null);
        final List<Long> listeCommercial = new ArrayList<Long>();
        listeCommercial.add(2L);
        criteres.setIdCommerciaux(listeCommercial);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.88"), result);
        assertEquals(Messages.getString("ActionServiceTest.89"), 2, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_2, result.get(0).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_3, result.get(0).getSousObjet());
        assertEquals(statutNeCorrespondPas, "Statut 3", result.get(0).getStatut());
        assertEquals(prioriteNeCorrespondPas, PRIORITE_1, result.get(0).getPriorite());

        // Test de la recherche par agence responsable de l'action sans responsable.
        final int nbObjetsAgence = 33;
        criteres.setIdCommerciaux(null);
        criteres.setRechercheEtEntreAgencesEtCommerciaux(Boolean.FALSE);
        final List<Long> listeAgences = new ArrayList<Long>();
        listeAgences.add(1L);
        listeAgences.add(2L);
        criteres.setIdAgences(listeAgences);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.91"), result);
        assertEquals(Messages.getString("ActionServiceTest.92"), nbObjetsAgence, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_2, result.get(1).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_3, result.get(0).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(1).getStatut());
        assertEquals(prioriteNeCorrespondPas, PRIORITE_1, result.get(0).getPriorite());

        // Test de la recherche par date d'action.
        criteres.setIdAgences(null);
        criteres.setRechercheEtEntreAgencesEtCommerciaux(Boolean.TRUE);
        // Test de la recherche par date d'action
        criteres.setListeCampagnes(null);
        final int year = 2010;

        final Calendar dateDebut = Calendar.getInstance();
        dateDebut.clear();
        dateDebut.set(year, Calendar.JUNE, 3);

        final Calendar dateFin = Calendar.getInstance();
        dateFin.clear();
        final int jour = 28;
        dateFin.set(year, Calendar.JUNE, jour);

        criteres.setDateDebutAction(dateDebut);
        criteres.setDateFinAction(dateFin);

        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.93"), result);
        assertEquals(Messages.getString("ActionServiceTest.94"), 3, result.size());
        assertEquals(objetNeCorrespondPas, OBJET_2, result.get(0).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_3, result.get(0).getSousObjet());
        assertEquals(statutNeCorrespondPas, "Statut 3", result.get(0).getStatut());
        assertEquals(prioriteNeCorrespondPas, PRIORITE_1, result.get(0).getPriorite());

        // Test de la recherche par reclamation
        criteres.setReclamation(true);
        criteres.setDateDebutAction(null);
        criteres.setDateFinAction(null);
        resultPagination = actionService.rechercherActionParCritere(criterias);
        result = resultPagination.getListResults();
        assertNotNull(Messages.getString("ActionServiceTest.96"), result);
        assertEquals(objetNeCorrespondPas, OBJET_3, result.get(1).getObjet());
        assertEquals(sousObjetNeCorrespondPas, SOUS_OBJET_1, result.get(1).getSousObjet());
        assertEquals(statutNeCorrespondPas, STATUT_1, result.get(1).getStatut());
        assertEquals(prioriteNeCorrespondPas, "Priorité 3", result.get(1).getPriorite());

        // Test du tri.
        final int nbrObjet = 25;
        assertEquals(Messages.getString("ActionServiceTest.98"), nbrObjet, resultPagination.getTotalResults());
        assertEquals(Messages.getString("ActionServiceTest.99"), "Type 3", result.get(0).getType());
        assertEquals(Messages.getString("ActionServiceTest.101"), TYPE_2, result.get(1).getType());
        assertEquals(Messages.getString("ActionServiceTest.102"), TYPE_2, result.get(2).getType());

    }

    /**
     * Test recherche d'actions avec tri.
     */
    @Test
    public void rechercherActionsTri() {
        RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres = new RemotePagingCriteriasDto<ActionCritereRechercheDto>(null, 0, Integer.MAX_VALUE);
        List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        listeSorts.add(new RemotePagingSort(squareMappingService.getOrderByActionPriorite(), RemotePagingSort.REMOTE_PAGING_SORT_ASC));
        criteres.setListeSorts(listeSorts);
        RemotePagingResultsDto<ActionRechercheDto> searchResults = actionService.rechercherActionParCritere(criteres);

        List<ActionRechercheDto> actionsTrouvees = searchResults.getListResults();

        assertEquals(Messages.getString("ActionServiceTest.103"), NB_TOTAL_ACTIONS, searchResults.getTotalResults());
        assertEquals(Messages.getString("ActionServiceTest.104"), NB_TOTAL_ACTIONS, actionsTrouvees.size());

        final Long idAction2 = 2L;
        final Long idAction5 = 5L;
        final Long idAction6 = 6L;
        final Long idAction7 = 7L;
        final Long idAction10 = 10L;
        final Long idAction11 = 11L;
        assertEquals(Messages.getString("ActionServiceTest.105"), idAction2, actionsTrouvees.get(0).getId());
        assertEquals(Messages.getString("ActionServiceTest.106"), idAction10, actionsTrouvees.get(1).getId());
        assertEquals(Messages.getString("ActionServiceTest.107"), idAction11, actionsTrouvees.get(2).getId());

        // Test tri multiple sur colonnes "ordre"
        criteres = new RemotePagingCriteriasDto<ActionCritereRechercheDto>(null, 0, Integer.MAX_VALUE);
        listeSorts = new ArrayList<RemotePagingSort>();
        criteres.setListeSorts(listeSorts);
        listeSorts.add(new RemotePagingSort(squareMappingService.getOrderByActionType(), RemotePagingSort.REMOTE_PAGING_SORT_ASC));
        listeSorts.add(new RemotePagingSort(squareMappingService.getOrderByActionObjet(), RemotePagingSort.REMOTE_PAGING_SORT_ASC));
        listeSorts.add(new RemotePagingSort(squareMappingService.getOrderByActionSsObjet(), RemotePagingSort.REMOTE_PAGING_SORT_ASC));
        listeSorts.add(new RemotePagingSort(squareMappingService.getOrderByActionStatut(), RemotePagingSort.REMOTE_PAGING_SORT_ASC));

        searchResults = actionService.rechercherActionParCritere(criteres);

        actionsTrouvees = searchResults.getListResults();

        assertEquals(Messages.getString("ActionServiceTest.108"), NB_TOTAL_ACTIONS, searchResults.getTotalResults());
        assertEquals(Messages.getString("ActionServiceTest.109"), NB_TOTAL_ACTIONS, actionsTrouvees.size());

        assertEquals(Messages.getString("ActionServiceTest.110"), idAction5, actionsTrouvees.get(0).getId());
        assertEquals(Messages.getString("ActionServiceTest.111"), idAction6, actionsTrouvees.get(1).getId());
        assertEquals(Messages.getString("ActionServiceTest.112"), idAction7, actionsTrouvees.get(2).getId());
    }

    /**
     * Test pour les erreurs de création.
     */
    @Test
    public void testErreurCreation() {

        // Création avec un dto null
        try {
            actionService.creerAction(null);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DTO_NULL), be.getMessage());
        }

        // Création avec aucune valeur
        try {
            actionService.creerAction(new ActionCreationDto());
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(Messages.getString("ActionServiceTest.113"), 6, ce.getRapport().getRapports().size());
        }
        // Création des données de tests
        final ActionCreationDto action = new ActionCreationDto();
        final Calendar dateAction = Calendar.getInstance();
        dateAction.add(Calendar.YEAR, 1);
        action.setDateAction(dateAction);
        action.setIdNatureAction(1L);
        action.setIdNotificationList(1L);
        action.setIdObjet(1L);
        action.setIdPersonne(1L);
        action.setIdPriorite(1L);
        action.setIdSousObjet(1L);
        action.setIdType(1L);
        action.setMePrevenirParMail(true);
        action.setReclamation(true);
        action.setIdAgence(1L);
        action.setIdCommercial(2L);

        // Création sans date d'action
        action.setDateAction(null);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DATE_ACTION_NULL), ce.getRapport()
                    .getRapports().get("ActionCreationDto.dateAction").getMessage());
        }
        action.setDateAction(dateAction);
        // Création sans idPersonne
        action.setIdPersonne(null);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDPERSONNE_NULL), ce.getRapport().getRapports()
                    .get("ActionCreationDto.idPersonne").getMessage());
        }
        // Création avec un idPersonne inconnu
        final Long idPersonneInconnu = 100L;
        action.setIdPersonne(idPersonneInconnu);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDPERSONNE_INEXISTANT), be.getMessage());
        }
        // Création sans nature d'action
        action.setIdPersonne(1L);
        action.setIdNatureAction(null);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_ACTION_NULL), ce.getRapport()
                    .getRapports().get("ActionCreationDto.idNatureAction").getMessage());
        }
        // Création avec une nature d'action inconnue
        action.setIdNatureAction(10L);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_ACTION_INEXISTANT), be.getMessage());
        }
        // Création sans type d'action
        action.setIdNatureAction(1L);
        action.setIdType(null);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TYPE_ACTION_NULL), ce.getRapport()
                    .getRapports().get("ActionCreationDto.idType").getMessage());
        }
        // Création avec un type d'action inconnu
        action.setIdType(10L);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TYPE_ACTION_INEXISTANT), be.getMessage());
        }
        // Création sans objet
        action.setIdType(1L);
        action.setIdObjet(null);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_OBJET_NULL), ce.getRapport().getRapports().get(
                "ActionCreationDto.idObjet").getMessage());
        }
        // Création avec un objet inconnu
        action.setIdObjet(10L);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_OBJET_INEXISTANT), be.getMessage());
        }
        // Création avec un sous objet inconnu
        action.setIdObjet(1L);
        action.setIdSousObjet(10L);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_SOUS_OBJET_INEXISTANT), be.getMessage());
        }
        // Création avec une priorité inconnu
        action.setIdSousObjet(null);
        action.setIdPriorite(10L);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_PRIORITE_INEXISTANTE), be.getMessage());
        }
        // Création avec une campagne inconnue
        action.setIdPriorite(null);
        action.setIdCampagne(10L);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_CAMPAGNE_INEXISTANTE), be.getMessage());
        }

        // Création avec une demande de rappel mais pas de temps
        action.setIdCampagne(null);
        action.setRappel(true);
        action.setIdNotificationList(null);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_RAPPEL_SANS_TEMPS), be.getMessage());
        }

        // Création avec une demande de rappel et un mauvais temps
        final Long mauvaisId = 100L;
        action.setIdNotificationList(mauvaisId);
        try {
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_RAPPEL_SANS_TEMPS), be.getMessage());
        }
    }

    /**
     * Test de la création d'action.
     */
    @Test
    public void testCreationAction() {
        // Création des données
        final ActionCreationDto action = new ActionCreationDto();
        final Calendar dateAction = Calendar.getInstance();
        action.setDateAction(dateAction);
        action.setIdNatureAction(1L);
        action.setRappel(true);
        action.setIdNotificationList(1L);
        action.setIdObjet(1L);
        action.setIdPersonne(1L);
        action.setIdPriorite(1L);
        action.setIdSousObjet(1L);
        action.setIdType(1L);
        action.setMePrevenirParMail(true);
        action.setReclamation(true);
        action.setIdAgence(1L);
        action.setIdCommercial(2L);
        action.setIdDuree(1L);
        action.setVisibleAgenda(true);

        // Vérification de la création
        ActionCreationDto actionResultat = actionService.creerAction(action);
        assertEquals(Messages.getString("ActionServiceTest.119"), dateAction.get(Calendar.YEAR), actionResultat.getDateAction().get(Calendar.YEAR));
        assertEquals(Messages.getString("ActionServiceTest.120"), action.getIdNatureAction(), actionResultat.getIdNatureAction());
        assertEquals(Messages.getString("ActionServiceTest.121"), action.getIdNotificationList(), actionResultat.getIdNotificationList());
        assertEquals(Messages.getString("ActionServiceTest.122"), action.getIdObjet(), actionResultat.getIdObjet());
        assertEquals(Messages.getString("ActionServiceTest.123"), action.getIdPersonne(), actionResultat.getIdPersonne());
        assertEquals(Messages.getString("ActionServiceTest.124"), action.getIdPriorite(), actionResultat.getIdPriorite());
        assertEquals(Messages.getString("ActionServiceTest.125"), action.getIdSousObjet(), actionResultat.getIdSousObjet());
        assertEquals(Messages.getString("ActionServiceTest.126"), action.getIdType(), actionResultat.getIdType());
        assertEquals(Messages.getString("ActionServiceTest.127"), action.getMePrevenirParMail(), actionResultat.getMePrevenirParMail());
        assertEquals(Messages.getString("ActionServiceTest.128"), action.getReclamation(), actionResultat.getReclamation());
        assertEquals(Messages.getString("ActionServiceTest.129"), action.getIdDuree(), actionResultat.getIdDuree());
        assertTrue(Messages.getString("ActionServiceTest.130"), actionResultat.getVisibleAgenda());

        // Vérification de la création avec les valeurs par défaut.
        action.setIdSousObjet(null);
        action.setReclamation(null);
        action.setIdPriorite(null);
        action.setIdCampagne(null);
        action.setMePrevenirParMail(null);
        action.setRappel(false);
        action.setIdNotificationList(null);
        actionResultat = actionService.creerAction(action);
        assertEquals(Messages.getString("ActionServiceTest.131"), dateAction.get(Calendar.YEAR), actionResultat.getDateAction().get(Calendar.YEAR));
        assertEquals(Messages.getString("ActionServiceTest.132"), action.getIdNatureAction(), actionResultat.getIdNatureAction());
        assertEquals(Messages.getString("ActionServiceTest.133"), null, actionResultat.getIdNotificationList());
        assertEquals(Messages.getString("ActionServiceTest.134"), false, actionResultat.getRappel());
        assertEquals(Messages.getString("ActionServiceTest.135"), action.getIdObjet(), actionResultat.getIdObjet());
        assertEquals(Messages.getString("ActionServiceTest.136"), action.getIdPersonne(), actionResultat.getIdPersonne());
        assertEquals(Messages.getString("ActionServiceTest.137"), 1L, actionResultat.getIdPriorite());
        assertEquals(Messages.getString("ActionServiceTest.138"), null, actionResultat.getIdSousObjet());
        assertEquals(Messages.getString("ActionServiceTest.139"), action.getIdType(), actionResultat.getIdType());
        assertEquals(Messages.getString("ActionServiceTest.140"), false, actionResultat.getMePrevenirParMail());
        assertEquals(Messages.getString("ActionServiceTest.141"), false, actionResultat.getReclamation());
    }

    /**
     * Tests des erreurs pour la recherche d'actions.
     */
    @Test
    public void testErreurRechercheActions() {
        // Test avec un Dto null
        try {
            actionService.recupererActionsSynthese(null);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DTO_NULL), be.getMessage());
        }

        // Test avec un identifiant de personne null
        try {
            final CritereActionSyntheseDto criteres = new CritereActionSyntheseDto();
            criteres.setIdPersonne(null);
            actionService.recupererActionsSynthese(criteres);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDPERSONNE_NULL), be.getMessage());
        }

        // Test avec un mauvais identifiant de personne
        try {
            final CritereActionSyntheseDto criteres = new CritereActionSyntheseDto();
            final Long idPersonne = 100L;
            criteres.setIdPersonne(idPersonne);
            actionService.recupererActionsSynthese(criteres);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDPERSONNE_INEXISTANT), be.getMessage());
        }
    }

    /**
     * Test de la récupération d'actions.
     */
    @Test
    public void testRecuperationActions() {

        // Test récupération d'aucune actions
        CritereActionSyntheseDto criteres = new CritereActionSyntheseDto();
        criteres.setIdPersonne(8L);
        final List<Stack<ActionSyntheseDto>> resultat = actionService.recupererActionsSynthese(criteres);
        assertNotNull(Messages.getString("ActionServiceTest.142"), resultat);
        assertEquals(Messages.getString("ActionServiceTest.143"), 0, resultat.size());

        // Test de récupération de deux actions d'un seul niveau
        criteres = null;
        criteres = new CritereActionSyntheseDto();
        criteres.setIdPersonne(5L);
        final List<Stack<ActionSyntheseDto>> resultat2 = actionService.recupererActionsSynthese(criteres);
        assertNotNull(Messages.getString("ActionServiceTest.144"), resultat2);
        assertEquals(Messages.getString("ActionServiceTest.145"), 2, resultat2.size());
        // Vérification de la première pile
        assertNotNull(Messages.getString("ActionServiceTest.146"), resultat2.get(0));
        assertEquals(Messages.getString("ActionServiceTest.147"), 1, resultat2.get(0).size());
        assertEquals(Messages.getString("ActionServiceTest.148"), 7L, resultat2.get(0).peek().getId());
        assertEquals(Messages.getString("ActionServiceTest.149"), TYPE_2, resultat2.get(0).peek().getType().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.150"), "Objet 3", resultat2.get(0).peek().getObjet());
        assertEquals(Messages.getString("ActionServiceTest.152"), SOUS_OBJET_1, resultat2.get(0).peek().getSousObjet());
        assertEquals(Messages.getString("ActionServiceTest.153"), STATUT_1, resultat2.get(0).peek().getStatut().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.154"), 1L, resultat2.get(0).peek().getStatut().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.155"), 3L, resultat2.get(0).peek().getPriorite().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.156"), PRIORITE_3, resultat2.get(0).peek().getPriorite().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.157"), 0, resultat2.get(0).peek().getNiveau());
        assertEquals(Messages.getString("ActionServiceTest.158"), "nom1 prenom1, Agence 2", resultat2.get(0).peek().getAttribueA());
        assertEquals(Messages.getString("ActionServiceTest.160"), "Nature action 2", resultat2.get(0).peek().getNatureContact());
        assertEquals(Messages.getString("ActionServiceTest.162"), "Libelle Campagne 1", resultat2.get(0).peek().getCampagne());
        assertEquals(Messages.getString("ActionServiceTest.164"), 2L, resultat2.get(0).peek().getDuree().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.165"), "09:00", resultat2.get(0).peek().getDuree().getLibelle());
        // Vérification de la première pile
        assertNotNull(Messages.getString("ActionServiceTest.167"), resultat2.get(1));
        assertEquals(Messages.getString("ActionServiceTest.168"), 1, resultat2.get(1).size());
        assertEquals(Messages.getString("ActionServiceTest.169"), 6L, resultat2.get(1).peek().getId());
        assertEquals(Messages.getString("ActionServiceTest.170"), TYPE_2, resultat2.get(1).peek().getType().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.171"), OBJET_3, resultat2.get(1).peek().getObjet());
        assertEquals(Messages.getString("ActionServiceTest.172"), SOUS_OBJET_1, resultat2.get(1).peek().getSousObjet());
        assertEquals(Messages.getString("ActionServiceTest.173"), STATUT_1, resultat2.get(1).peek().getStatut().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.174"), 1L, resultat2.get(1).peek().getStatut().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.175"), 3L, resultat2.get(1).peek().getPriorite().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.176"), PRIORITE_3, resultat2.get(1).peek().getPriorite().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.177"), 0, resultat2.get(1).peek().getNiveau());
        assertEquals(Messages.getString("ActionServiceTest.178"), "nom1 prenom1, Agence 2", resultat2.get(1).peek().getAttribueA());
        assertEquals(Messages.getString("ActionServiceTest.180"), "Nature action 2", resultat2.get(1).peek().getNatureContact());
        assertEquals(Messages.getString("ActionServiceTest.182"), "Libelle Campagne 1", resultat2.get(1).peek().getCampagne());
        assertEquals(Messages.getString("ActionServiceTest.184"), 1L, resultat2.get(1).peek().getDuree().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.185"), "08:00", resultat2.get(1).peek().getDuree().getLibelle());

        // Test de la récupération d'une action à 3 niveaux
        criteres = null;
        criteres = new CritereActionSyntheseDto();
        criteres.setIdPersonne(6L);
        final List<Stack<ActionSyntheseDto>> resultat3 = actionService.recupererActionsSynthese(criteres);
        assertNotNull(Messages.getString("ActionServiceTest.187"), resultat3);
        assertEquals(Messages.getString("ActionServiceTest.188"), 1, resultat3.size());
        // Vérification du lien entre les 3 actions
        assertNotNull(Messages.getString("ActionServiceTest.189"), resultat3.get(0));
        assertEquals(Messages.getString("ActionServiceTest.190"), 5, resultat3.get(0).size());
        assertEquals(Messages.getString("ActionServiceTest.191"), 33L, resultat3.get(0).peek().getId());
        assertEquals(Messages.getString("ActionServiceTest.192"), 3, resultat3.get(0).pop().getNiveau());
        assertEquals(Messages.getString("ActionServiceTest.193"), 32L, resultat3.get(0).peek().getId());
        assertEquals(Messages.getString("ActionServiceTest.194"), 0, resultat3.get(0).pop().getNiveau());
        assertEquals(Messages.getString("ActionServiceTest.195"), 10L, resultat3.get(0).peek().getId());
        assertEquals(Messages.getString("ActionServiceTest.196"), "nom1 prenom1, Agence 2", resultat3.get(0).peek().getAttribueA());
        assertEquals(Messages.getString("ActionServiceTest.198"), 2, resultat3.get(0).pop().getNiveau());
        assertEquals(Messages.getString("ActionServiceTest.199"), 9L, resultat3.get(0).peek().getId());
        assertEquals(Messages.getString("ActionServiceTest.200"), 1, resultat3.get(0).pop().getNiveau());
        assertEquals(Messages.getString("ActionServiceTest.201"), 8L, resultat3.get(0).peek().getId());
        assertEquals(Messages.getString("ActionServiceTest.202"), 0, resultat3.get(0).pop().getNiveau());

        // Test de la récupération d'un action ayant 2 actions liée dont une avec une autre actions liée.
        criteres = null;
        criteres = new CritereActionSyntheseDto();
        criteres.setIdPersonne(7L);
        final List<Stack<ActionSyntheseDto>> resultat4 = actionService.recupererActionsSynthese(criteres);
        assertNotNull(Messages.getString("ActionServiceTest.203"), resultat4);
        assertEquals(Messages.getString("ActionServiceTest.204"), 1, resultat4.size());
        // Vérification de la première pile
        assertNotNull(Messages.getString("ActionServiceTest.205"), resultat4.get(0));
        assertEquals(Messages.getString("ActionServiceTest.206"), 4, resultat4.get(0).size());
        final Long id13 = 13L;
        final Long id14 = 14L;
        final Long id12 = 12L;
        final Long id11 = 11L;
        assertEquals(Messages.getString("ActionServiceTest.207"), id13, resultat4.get(0).pop().getId());
        assertEquals(Messages.getString("ActionServiceTest.208"), id14, resultat4.get(0).pop().getId());
        assertEquals(Messages.getString("ActionServiceTest.209"), id12, resultat4.get(0).pop().getId());
        assertEquals(Messages.getString("ActionServiceTest.210"), id11, resultat4.get(0).pop().getId());

        // Test deux piles complex
        criteres = null;
        criteres = new CritereActionSyntheseDto();
        criteres.setIdPersonne(9L);
        final List<Stack<ActionSyntheseDto>> resultat5 = actionService.recupererActionsSynthese(criteres);
        assertNotNull(Messages.getString("ActionServiceTest.211"), resultat5);
        assertEquals(Messages.getString("ActionServiceTest.212"), 2, resultat5.size());
        // Vérification de la première pile
        assertNotNull(Messages.getString("ActionServiceTest.213"), resultat5.get(0));
        assertEquals(Messages.getString("ActionServiceTest.214"), 4, resultat5.get(0).size());
        final Long id19 = 19L;
        final Long id17 = 17L;
        final Long id18 = 18L;
        final Long id16 = 16L;
        assertEquals(Messages.getString("ActionServiceTest.215"), id19, resultat5.get(0).pop().getId());
        assertEquals(Messages.getString("ActionServiceTest.216"), id17, resultat5.get(0).pop().getId());
        assertEquals(Messages.getString("ActionServiceTest.217"), id18, resultat5.get(0).pop().getId());
        assertEquals(Messages.getString("ActionServiceTest.218"), id16, resultat5.get(0).pop().getId());
        // Vérification de la seconde pile
        assertNotNull(Messages.getString("ActionServiceTest.219"), resultat5.get(1));
        final Long id20 = 20L;
        final Long id15 = 15L;
        assertEquals(Messages.getString("ActionServiceTest.220"), 2, resultat5.get(1).size());
        assertEquals(Messages.getString("ActionServiceTest.221"), id20, resultat5.get(1).pop().getId());
        assertEquals(Messages.getString("ActionServiceTest.222"), id15, resultat5.get(1).pop().getId());
    }

    /**
     * Test de la récupération d'une action.
     */
    @Test
    public void testRechercheActionParId() {
        // Test avec un identifiant null
        try {
            actionService.rechercherActionParIdentifiant(null);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_NULL), be.getMessage());
        }
        // Test avec un identifiant inexistant
        try {
            final Long mauvaisId = 99L;
            actionService.rechercherActionParIdentifiant(mauvaisId);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_INEXISTANT), be.getMessage());
        }

        // Test avec une action source
        ActionDto resultat = actionService.rechercherActionParIdentifiant(8L);
        assertNotNull(Messages.getString("ActionServiceTest.223"), resultat);
        assertEquals(Messages.getString("ActionServiceTest.224"), 8L, resultat.getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.225"), 6L, resultat.getIdPersonne());
        assertEquals(Messages.getString("ActionServiceTest.226"), TYPE_2, resultat.getType().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.227"), 2L, resultat.getType().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.228"), OBJET_3, resultat.getObjet().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.229"), 3L, resultat.getObjet().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.230"), SOUS_OBJET_1, resultat.getSousObjet().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.231"), 1L, resultat.getSousObjet().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.232"), "Terminé", resultat.getStatut().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.234"), 2L, resultat.getStatut().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.235"), 2, resultat.getDateAction().get(Calendar.DAY_OF_MONTH));
        assertEquals(Messages.getString("ActionServiceTest.236"), "nom1", resultat.getCreateur().getNom());
        assertEquals(Messages.getString("ActionServiceTest.238"), "prenom1", resultat.getCreateur().getPrenom());
        assertEquals(Messages.getString("ActionServiceTest.240"), "Agence 2", resultat.getAgence().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.242"), true, resultat.getReclamation());
        assertEquals(Messages.getString("ActionServiceTest.243"), PRIORITE_3, resultat.getPriorite().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.244"), 3L, resultat.getPriorite().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.245"), 8, resultat.getDateCreation().get(Calendar.DAY_OF_MONTH));
        assertEquals(Messages.getString("ActionServiceTest.246"), "Libelle Campagne 1", resultat.getCampagne().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.248"), true, resultat.getRappel());
        assertEquals(Messages.getString("ActionServiceTest.249"), 1L, resultat.getIdNotificationList());
        assertEquals(Messages.getString("ActionServiceTest.250"), true, resultat.getMePrevenirParMail());
        assertEquals(Messages.getString("ActionServiceTest.251"), "Nature action 2", resultat.getNatureAction().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.253"), "Nature résultat 2", resultat.getNatureResultat().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.255"), "Résultat 3", resultat.getResultat().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.257"), 3L, resultat.getResultat().getIdentifiant());
        assertTrue(Messages.getString("ActionServiceTest.258"), resultat.getCommentaires().size() != 0);
        assertEquals(Messages.getString("ActionServiceTest.259"), OBJET_3, resultat.getCommentaires().get(0).getObjet());
        assertEquals(Messages.getString("ActionServiceTest.260"), SOUS_OBJET_1, resultat.getCommentaires().get(0).getSousObjet());
        assertEquals(Messages.getString("ActionServiceTest.261"), 2, resultat.getCommentaires().get(0).getDateAction().get(Calendar.DAY_OF_MONTH));
        assertEquals(Messages.getString("ActionServiceTest.262"), "Commentaire 8", resultat.getCommentaires().get(0).getDescriptif());
        assertEquals(Messages.getString("ActionServiceTest.264"), 1L, resultat.getDuree().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.265"), "08:00", resultat.getDuree().getLibelle());
        assertTrue(Messages.getString("ActionServiceTest.267"), resultat.getVisibleAgenda());

        // Test avec une action liée
        resultat = new ActionDto();
        resultat = actionService.rechercherActionParIdentifiant(10L);
        assertNotNull(Messages.getString("ActionServiceTest.268"), resultat);
        assertEquals(Messages.getString("ActionServiceTest.269"), 10L, resultat.getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.270"), 6L, resultat.getIdPersonne());
        assertEquals(Messages.getString("ActionServiceTest.271"), "Action Relance", resultat.getType().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.273"), OBJET_3, resultat.getObjet().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.274"), SOUS_OBJET_1, resultat.getSousObjet().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.275"), STATUT_1, resultat.getStatut().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.276"), 1L, resultat.getStatut().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.277"), 2, resultat.getDateAction().get(Calendar.DAY_OF_MONTH));
        assertEquals(Messages.getString("ActionServiceTest.278"), "nom1", resultat.getRessource().getNom());
        assertEquals(Messages.getString("ActionServiceTest.280"), "prenom1", resultat.getRessource().getPrenom());
        assertEquals(Messages.getString("ActionServiceTest.282"), 1L, resultat.getRessource().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.283"), "nom1", resultat.getCreateur().getNom());
        assertEquals(Messages.getString("ActionServiceTest.285"), "prenom1", resultat.getCreateur().getPrenom());
        assertEquals(Messages.getString("ActionServiceTest.287"), "Agence 2", resultat.getAgence().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.289"), false, resultat.getReclamation());
        assertEquals(Messages.getString("ActionServiceTest.290"), "Priorité 1", resultat.getPriorite().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.292"), 10, resultat.getDateCreation().get(Calendar.DAY_OF_MONTH));
        assertEquals(Messages.getString("ActionServiceTest.293"), "Libelle Campagne 1", resultat.getCampagne().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.295"), true, resultat.getRappel());
        assertEquals(Messages.getString("ActionServiceTest.296"), 2L, resultat.getIdNotificationList());
        assertEquals(Messages.getString("ActionServiceTest.297"), true, resultat.getMePrevenirParMail());
        assertEquals(Messages.getString("ActionServiceTest.298"), "Nature action 2", resultat.getNatureAction().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.300"), "Nature résultat 2", resultat.getNatureResultat().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.302"), "Résultat 3", resultat.getResultat().getLibelle());
        assertEquals(Messages.getString("ActionServiceTest.304"), 3L, resultat.getResultat().getIdentifiant());
        assertTrue(Messages.getString("ActionServiceTest.305"), resultat.getCommentaires().size() != 0);
        assertEquals(Messages.getString("ActionServiceTest.306"), OBJET_3, resultat.getCommentaires().get(0).getObjet());
        assertEquals(Messages.getString("ActionServiceTest.307"), SOUS_OBJET_1, resultat.getCommentaires().get(0).getSousObjet());
        assertEquals(Messages.getString("ActionServiceTest.308"), 2, resultat.getCommentaires().get(0).getDateAction().get(Calendar.DAY_OF_MONTH));
        assertEquals(Messages.getString("ActionServiceTest.309"), "Commentaire 10", resultat.getCommentaires().get(0).getDescriptif());

        // Test de la récupération d'une action avec une opportunité
        resultat = new ActionDto();
        final Long idAction = 24L;
        resultat = actionService.rechercherActionParIdentifiant(idAction);
        assertNotNull(Messages.getString("ActionServiceTest.311"), resultat);
        assertEquals(Messages.getString("ActionServiceTest.312"), idAction, resultat.getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.313"), 1L, resultat.getIdOpportunite());
        assertEquals(Messages.getString("ActionServiceTest.314"), "idExt1", resultat.getEidOpportunite());
    }

    /**
     * Test de la récupération de l'historique d'une action.
     */
    @Test
    public void testRechercheHistorique() {
        // Test avec un identifiant null
        try {
            actionService.rechercherNotesActions(null);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_NULL), be.getMessage());
        }
        // Test avec un identifiant inexistant
        try {
            final Long mauvaisId = 99L;
            actionService.rechercherNotesActions(mauvaisId);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_INEXISTANT), be.getMessage());
        }

        // Test sans historique
        List<HistoriqueCommentaireDto> resultat = actionService.rechercherNotesActions(8L);
        assertNotNull(Messages.getString("ActionServiceTest.316"), resultat);
        assertEquals(Messages.getString("ActionServiceTest.317"), 0, resultat.size());

        // Test avec un historique
        resultat = actionService.rechercherNotesActions(10L);
        assertEquals(Messages.getString("ActionServiceTest.318"), 2, resultat.size());
        assertEquals(Messages.getString("ActionServiceTest.319"), "<br/>nom1 prenom1 : Commentaire 9", resultat.get(0).getDescriptif());
        assertEquals(Messages.getString("ActionServiceTest.321"), "nom1 prenom1", resultat.get(0).getAttribution());
        assertEquals(Messages.getString("ActionServiceTest.323"), "<br/>nom1 prenom1 : Commentaire 8", resultat.get(1).getDescriptif());

        // Test avec un autre historique
        final List<HistoriqueCommentaireDto> resultat2 = actionService.rechercherNotesActions(14L);
        assertEquals(Messages.getString("ActionServiceTest.325"), 2, resultat2.size());
        assertEquals(Messages.getString("ActionServiceTest.326"), "<br/>nom1 prenom1 : Commentaire 12", resultat2.get(0).getDescriptif());
        assertEquals(Messages.getString("ActionServiceTest.328"), "<br/>nom1 prenom1 : Commentaire 11", resultat2.get(1).getDescriptif());
    }

    /**
     * Test des erreurs de la modification.
     */
    @Test
    public void testErreurModification() {
        // Dto null
        final ActionModificationDto action = null;
        try {
            actionService.modifierAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DTO_NULL), be.getMessage());
        }
        // Identifiant de l'action null
        final ActionModificationDto actionModificationDto = new ActionModificationDto();
        final Calendar dateAction = Calendar.getInstance();
        dateAction.add(Calendar.DAY_OF_MONTH, 1);
        actionModificationDto.setDateAction(dateAction);
        actionModificationDto.setIdAction(null);
        try {
            actionService.modifierAction(actionModificationDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_NULL), be.getMessage());
        }

        // Statut null
        actionModificationDto.setIdAction(2L);
        actionModificationDto.setNatureAction(new IdentifiantLibelleDto(2L));
        actionModificationDto.setStatut(null);
        try {
            actionService.modifierAction(actionModificationDto);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(Messages.getString("ActionServiceTest.330"), 2, ce.getRapport().getRapports().size());
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_STATUT_NULL), ce.getRapport().getRapports()
                    .get(Messages.getString("ActionServiceTest.331")).getMessage());
        }

        // Résultat de la nature de l'action null
        actionModificationDto.setNatureResultat(null);
        actionModificationDto.setStatut(new IdentifiantLibelleDto(2L));
        try {
            actionService.modifierAction(actionModificationDto);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(Messages.getString("ActionServiceTest.332"), 3, ce.getRapport().getRapports().size());
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_RESULTAT_ACTION_NULL), ce.getRapport()
                    .getRapports().get("ActionModificationDto.natureResultat").getMessage());
        }

        // Identifiant de l'action inexistant
        final Long mauvaisId = 99L;
        actionModificationDto.setIdAction(mauvaisId);
        actionModificationDto.setStatut(new IdentifiantLibelleDto(mauvaisId, ""));
        actionModificationDto.setNatureResultat(new IdentifiantLibelleDto(2L));
        try {
            actionService.modifierAction(actionModificationDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_INEXISTANTE), be.getMessage());
        }

        // Statut inexistant
        actionModificationDto.setIdAction(2L);
        actionModificationDto.setStatut(new IdentifiantLibelleDto(mauvaisId, ""));
        try {
            actionService.modifierAction(actionModificationDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_STATUT_INEXISTANT), be.getMessage());
        }

        // Résultat inexistant
        actionModificationDto.setStatut(new IdentifiantLibelleDto(1L));
        actionModificationDto.setResultat(new IdentifiantLibelleDto(mauvaisId));
        try {
            actionService.modifierAction(actionModificationDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_RESULTAT_INEXISTANT), be.getMessage());
        }
        // Identifiant de notification inexistant
        actionModificationDto.setResultat(new IdentifiantLibelleDto(1L));
        actionModificationDto.setIdNotification(mauvaisId);
        try {
            actionService.modifierAction(actionModificationDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDNOTIFICATION_INEXISTANT), be.getMessage());
        }

        // Date d'action null
        actionModificationDto.setIdNotification(1L);
        actionModificationDto.setDateAction(null);
        try {
            actionService.modifierAction(actionModificationDto);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DATE_ACTION_NULL), ce.getRapport()
                    .getRapports().get("ActionModificationDto.dateAction").getMessage());
        }

        // Date d'action antérieure à la date courante
        actionModificationDto.setIdNotification(1L);
        final Calendar dateActionInvalide = Calendar.getInstance();
        dateActionInvalide.add(Calendar.DAY_OF_MONTH, -1);
        actionModificationDto.setDateAction(dateActionInvalide);
        try {
            actionService.modifierAction(actionModificationDto);
            fail(messageFail);
        }
        catch (ControleIntegriteException ce) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DATE_ACTION_INVALIDE), ce.getRapport()
                    .getRapports().get("ActionModificationDto.dateAction").getMessage());
        }

        // test erreur si nature d'action est visite sortante et nature de résultat non renseignée
        final  IdentifiantLibelleDto natureAction = new IdentifiantLibelleDto();
        natureAction.setIdentifiant(squareMappingService.getIdNatureActionVisiteSortante());
        final IdentifiantLibelleDto statutAction = new IdentifiantLibelleDto();
        statutAction.setIdentifiant(squareMappingService.getIdStatutActionTermine());
        actionModificationDto.setStatut(statutAction);
        actionModificationDto.setDateAction(dateAction);
        actionModificationDto.setNatureAction(natureAction);
        actionModificationDto.setNatureResultat(null);
        actionModificationDto.setResultat(null);
        actionModificationDto.setVerifierResultatNatureAction(true);
        try {
            actionService.modifierAction(actionModificationDto);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_RESULTAT_VISITE_SORTANTE),
                be.getMessage());
        }
    }

    /**
     * Test de la modification avec une nouvelle date d'action.
     */
    @Test
    public void testModificationActionAvecDateAction() {
        // Création des données de test avec un nouveau rappel
        final ActionModificationDto actionModificationDto = new ActionModificationDto();
        actionModificationDto.setIdAction(2L);
        actionModificationDto.setNatureAction(new IdentifiantLibelleDto(2L));
        actionModificationDto.setNatureResultat(new IdentifiantLibelleDto(2L));
        actionModificationDto.setStatut(new IdentifiantLibelleDto(1L, STATUT_1));
        actionModificationDto.setResultat(new IdentifiantLibelleDto(2L, "Resultat 2"));
        final HistoriqueCommentaireDto commentaireDto = new HistoriqueCommentaireDto();
        commentaireDto.setDescriptif(Messages.getString("ActionServiceTest.339"));
        actionModificationDto.setCommentaire(commentaireDto);
        actionModificationDto.setRappel(true);
        actionModificationDto.setIdNotification(2L);
        actionModificationDto.setRappelMail(false);
        final Calendar nouvelleDateAction = Calendar.getInstance();
        nouvelleDateAction.add(Calendar.DAY_OF_MONTH, 1);
        actionModificationDto.setDateAction(nouvelleDateAction);
        actionModificationDto.setVisibleAgenda(false);
        actionModificationDto.setIdDuree(null);
        // Appel au service
        actionService.modifierAction(actionModificationDto);
        final ActionDto resultat = actionService.rechercherActionParIdentifiant(2L);
        assertNotNull(Messages.getString("ActionServiceTest.340"), resultat);
        assertEquals(Messages.getString("ActionServiceTest.341"), nouvelleDateAction, resultat.getDateAction());
        assertNull(Messages.getString("ActionServiceTest.342"), resultat.getDuree());
        assertFalse(Messages.getString("ActionServiceTest.343"), resultat.getVisibleAgenda());
    }

    /**
     * Test création action liée.
     */
    @Test
    public void testCreationActionLiee() {
        // Création des données
        final ActionCreationDto action = new ActionCreationDto();
        final Calendar dateAction = Calendar.getInstance();
        dateAction.add(Calendar.HOUR_OF_DAY, 2);
        action.setDateAction(dateAction);
        action.setIdNatureAction(3L);
        action.setRappel(false);
        action.setIdNotificationList(null);
        action.setIdObjet(3L);
        action.setIdPersonne(3L);
        action.setIdPriorite(2L);
        action.setIdSousObjet(2L);
        action.setIdType(2L);
        action.setMePrevenirParMail(false);
        action.setReclamation(false);
        action.setIdAgence(2L);
        action.setIdCommercial(1L);
        action.setIdCampagne(1L);

        // Test avec une mauvaise action source.
        try {
            final Long mauvaisId = 99L;
            action.setIdActionSource(mauvaisId);
            actionService.creerAction(action);
            fail(messageFail);
        }
        catch (BusinessException be) {
            assertEquals(messageErreurMauvaisMessage, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDSOURCE_INEXISTANTE), be.getMessage());
        }

        // Test avec des changement sur tous les champs
        action.setIdActionSource(2L);
        final ActionCreationDto actionResultat = actionService.creerAction(action);
        assertEquals(Messages.getString("ActionServiceTest.344"), dateAction.get(Calendar.YEAR), actionResultat.getDateAction().get(Calendar.YEAR));
        assertEquals(Messages.getString("ActionServiceTest.345"), 3L, actionResultat.getIdNatureAction());
        assertEquals(Messages.getString("ActionServiceTest.346"), false, actionResultat.getRappel());
        assertEquals(Messages.getString("ActionServiceTest.347"), action.getIdNotificationList(), actionResultat.getIdNotificationList());
        assertEquals(Messages.getString("ActionServiceTest.348"), action.getIdObjet(), actionResultat.getIdObjet());
        assertEquals(Messages.getString("ActionServiceTest.349"), 2L, actionResultat.getIdPersonne());
        assertEquals(Messages.getString("ActionServiceTest.350"), action.getIdPriorite(), actionResultat.getIdPriorite());
        assertEquals(Messages.getString("ActionServiceTest.351"), action.getIdSousObjet(), actionResultat.getIdSousObjet());
        assertEquals(Messages.getString("ActionServiceTest.352"), action.getIdType(), actionResultat.getIdType());
        assertEquals(Messages.getString("ActionServiceTest.353"), action.getMePrevenirParMail(), actionResultat.getMePrevenirParMail());
        assertEquals(Messages.getString("ActionServiceTest.354"), action.getReclamation(), actionResultat.getReclamation());
        assertEquals(Messages.getString("ActionServiceTest.355"), 2L, actionResultat.getIdCampagne());
        assertEquals(Messages.getString("ActionServiceTest.356"), action.getIdAgence(), actionResultat.getIdAgence());
        assertEquals(Messages.getString("ActionServiceTest.357"), action.getIdCommercial(), actionResultat.getIdCommercial());
        assertEquals(Messages.getString("ActionServiceTest.358"), action.getIdActionSource(), actionResultat.getIdActionSource());

        // Test avec une action source sans campagne.
        final Long idActionSansCampagne = 21L;
        action.setIdActionSource(idActionSansCampagne);
        final ActionCreationDto actionResultatSansCampagne = actionService.creerAction(action);
        assertEquals(Messages.getString("ActionServiceTest.359"), null, actionResultatSansCampagne.getIdCampagne());
    }

    /**
     * Test de la récupération d'action avec une opportunité en critère.
     */
    @Test
    public void testRecuperationActionAvecOpportunite() {
        // Appel au service
        final CritereActionSyntheseDto critere = new CritereActionSyntheseDto();
        critere.setIdPersonne(10L);
        critere.setIdOpportunite(1L);
        final List<Stack<ActionSyntheseDto>> resultat = actionService.recupererActionsSynthese(critere);
        // Vérification du nombre d'actions récupéré.
        assertEquals(Messages.getString("ActionServiceTest.360"), 1, resultat.size());
        final Long idActionLiee = 24L;
        assertEquals(Messages.getString("ActionServiceTest.361"), idActionLiee, resultat.get(0).pop().getId());
        final Long idActionSource = 23L;
        assertEquals(Messages.getString("ActionServiceTest.362"), idActionSource, resultat.get(0).pop().getId());
    }

    /**
     * Test de la modification d'une action pour créer une opportunité.
     */
    @Test
    public void testModificationActionOpportunite() {
        // Test avec une action possédant déjà un résultat à opportunité
        final ActionModificationDto actionModificationDto = new ActionModificationDto();
        final Long idAction = 23L;
        actionModificationDto.setIdAction(idAction);
        actionModificationDto.setNatureAction(new IdentifiantLibelleDto(2L));
        actionModificationDto.setNatureResultat(new IdentifiantLibelleDto(2L));
        actionModificationDto.setResultat(new IdentifiantLibelleDto(1L));
        actionModificationDto.setStatut(new IdentifiantLibelleDto(2L));
        actionModificationDto.setRessource(new DimensionRessourceDto(1L));
        final Calendar dateAction = Calendar.getInstance();
        dateAction.add(Calendar.DAY_OF_MONTH, 1);
        actionModificationDto.setDateAction(dateAction);
        actionService.modifierAction(actionModificationDto);
        // Vérification qu'aucune opportunité, ni aucune action n'a été crée
        final CritereActionSyntheseDto critere = new CritereActionSyntheseDto();
        critere.setIdPersonne(10L);
        final List<Stack<ActionSyntheseDto>> resultat = actionService.recupererActionsSynthese(critere);
        assertEquals(Messages.getString("ActionServiceTest.363"), 2, resultat.size());
        assertEquals(Messages.getString("ActionServiceTest.364"), 1, resultat.get(0).size());
        final OpportuniteCriteresRechercheDto criteresOpportunite = new OpportuniteCriteresRechercheDto();
        criteresOpportunite.setIdPersonnePhysique(10L);
        final List<OpportuniteSimpleDto> resultatOpportunite = opportuniteService.rechercherOpportuniteParCriteres(criteresOpportunite);
        assertEquals(Messages.getString("ActionServiceTest.365"), 1, resultatOpportunite.size());

    }

    /**
     * Test de la récupération d'actions avec une action de relance d'une opportunité éditable.
     */
    @Test
    public void testRecuperationActionDateEditable() {
        final CritereActionSyntheseDto criteres = new CritereActionSyntheseDto();
        final Long idPersonne = 11L;
        criteres.setIdPersonne(idPersonne);
        final List<Stack<ActionSyntheseDto>> resultat = actionService.recupererActionsSynthese(criteres);
        assertNotNull(Messages.getString("ActionServiceTest.366"), resultat);
        assertEquals(Messages.getString("ActionServiceTest.367"), 1, resultat.size());
        assertEquals(Messages.getString("ActionServiceTest.368"), true, resultat.get(0).pop().getDateActionEditable());
    }

    /** Test du transfert des actions d'une personne. */
    @Test
    public void testTransfererActionsPersonne() {
        final Long idPersonneSource = 7L;
        final Long idPersonneCible = 9L;
        final Long idPersonneCibleInexistante = 100L;

        // Test sans la personne source
        try {
            actionService.transfererActionsPersonne(null, idPersonneCible);
            fail(Messages.getString("ActionServiceTest.369"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("ActionServiceTest.370"), messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TRANSFERT_PERSONNE_SOURCE_NULL), e
                    .getMessage());
        }

        // Test sans la personne cible
        try {
            actionService.transfererActionsPersonne(idPersonneSource, null);
            fail(Messages.getString("ActionServiceTest.371"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("ActionServiceTest.372"), messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TRANSFERT_PERSONNE_CIBLE_NULL), e
                    .getMessage());
        }

        // Test avec une personne cible inexistante
        try {
            actionService.transfererActionsPersonne(idPersonneSource, idPersonneCibleInexistante);
            fail(Messages.getString("ActionServiceTest.373"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("ActionServiceTest.374"), messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TRANSFERT_PERSONNE_CIBLE_INEXISTANTE), e
                    .getMessage());
        }

        // Test du transfert
        final CritereActionSyntheseDto criteresSource = new CritereActionSyntheseDto();
        criteresSource.setIdPersonne(idPersonneSource);
        final CritereActionSyntheseDto criteresCible = new CritereActionSyntheseDto();
        criteresCible.setIdPersonne(idPersonneCible);
        // Vérification avant transfert (source : 11, 12, 13, 14; cible : 15, 16, 17, 18, 19, 20)
        final Long idAction11 = 11L;
        final Long idAction12 = 12L;
        final Long idAction13 = 13L;
        final Long idAction14 = 14L;
        final Long idAction15 = 15L;
        final Long idAction16 = 16L;
        final Long idAction17 = 17L;
        final Long idAction18 = 18L;
        final Long idAction19 = 19L;
        final Long idAction20 = 20L;
        boolean possedeAction11 = false;
        boolean possedeAction12 = false;
        boolean possedeAction13 = false;
        boolean possedeAction14 = false;
        boolean possedeAction15 = false;
        boolean possedeAction16 = false;
        boolean possedeAction17 = false;
        boolean possedeAction18 = false;
        boolean possedeAction19 = false;
        boolean possedeAction20 = false;
        // Vérification des actions de la source
        List<Stack<ActionSyntheseDto>> listeActionsSource = actionService.recupererActionsSynthese(criteresSource);
        for (Stack<ActionSyntheseDto> stackActionSource : listeActionsSource) {
            for (ActionSyntheseDto actionSource : stackActionSource) {
                if (idAction11.equals(actionSource.getId())) {
                    possedeAction11 = true;
                }
                else if (idAction12.equals(actionSource.getId())) {
                    possedeAction12 = true;
                }
                else if (idAction13.equals(actionSource.getId())) {
                    possedeAction13 = true;
                }
                else if (idAction14.equals(actionSource.getId())) {
                    possedeAction14 = true;
                }
            }
        }
        assertTrue(Messages.getString("ActionServiceTest.375"), possedeAction11 && possedeAction12 && possedeAction13 && possedeAction14);
        // Vérification des actions de la cible
        List<Stack<ActionSyntheseDto>> listeActionsCible = actionService.recupererActionsSynthese(criteresCible);
        for (Stack<ActionSyntheseDto> stackActionCible : listeActionsCible) {
            for (ActionSyntheseDto actionCible : stackActionCible) {
                if (idAction15.equals(actionCible.getId())) {
                    possedeAction15 = true;
                }
                else if (idAction16.equals(actionCible.getId())) {
                    possedeAction16 = true;
                }
                else if (idAction17.equals(actionCible.getId())) {
                    possedeAction17 = true;
                }
                else if (idAction18.equals(actionCible.getId())) {
                    possedeAction18 = true;
                }
                else if (idAction19.equals(actionCible.getId())) {
                    possedeAction19 = true;
                }
                else if (idAction20.equals(actionCible.getId())) {
                    possedeAction20 = true;
                }
            }
        }
        assertTrue(Messages.getString("ActionServiceTest.376"), possedeAction15 && possedeAction16 && possedeAction17 && possedeAction18
            && possedeAction19 && possedeAction20);

        // Appel du service de transfert
        actionService.transfererActionsPersonne(idPersonneSource, idPersonneCible);

        // Vérification du transfert
        possedeAction11 = false;
        possedeAction12 = false;
        possedeAction13 = false;
        possedeAction14 = false;
        possedeAction15 = false;
        possedeAction16 = false;
        possedeAction17 = false;
        possedeAction18 = false;
        possedeAction19 = false;
        possedeAction20 = false;
        // Vérification des actions de la source
        listeActionsSource = actionService.recupererActionsSynthese(criteresSource);
        assertEquals(Messages.getString("ActionServiceTest.377"), 0, listeActionsSource.size());
        // Vérification des actions de la cible
        listeActionsCible = actionService.recupererActionsSynthese(criteresCible);
        for (Stack<ActionSyntheseDto> stackActionCible : listeActionsCible) {
            for (ActionSyntheseDto actionCible : stackActionCible) {
                if (idAction11.equals(actionCible.getId())) {
                    possedeAction11 = true;
                }
                else if (idAction12.equals(actionCible.getId())) {
                    possedeAction12 = true;
                }
                else if (idAction13.equals(actionCible.getId())) {
                    possedeAction13 = true;
                }
                else if (idAction14.equals(actionCible.getId())) {
                    possedeAction14 = true;
                }
                else if (idAction15.equals(actionCible.getId())) {
                    possedeAction15 = true;
                }
                else if (idAction16.equals(actionCible.getId())) {
                    possedeAction16 = true;
                }
                else if (idAction17.equals(actionCible.getId())) {
                    possedeAction17 = true;
                }
                else if (idAction18.equals(actionCible.getId())) {
                    possedeAction18 = true;
                }
                else if (idAction19.equals(actionCible.getId())) {
                    possedeAction19 = true;
                }
                else if (idAction20.equals(actionCible.getId())) {
                    possedeAction20 = true;
                }
            }
        }
        assertTrue(Messages.getString("ActionServiceTest.378"), possedeAction11 && possedeAction12 && possedeAction13 && possedeAction14
            && possedeAction15 && possedeAction16 && possedeAction17 && possedeAction18 && possedeAction19 && possedeAction20);
    }

    /** Test de la modification du résultat de la nature d'une action ayant pour nature "Téléphone sortant". */
    @Test
    public void testModificationActionNatureResultat() {
        // Création des données de test avec un nouveau rappel
        final ActionModificationDto actionModificationDto = new ActionModificationDto();
        actionModificationDto.setIdAction(2L);
        actionModificationDto.setNatureAction(new IdentifiantLibelleDto(2L));
        final Long idNatureResultat = 2L;
        actionModificationDto.setNatureResultat(new IdentifiantLibelleDto(idNatureResultat));
        actionModificationDto.setStatut(new IdentifiantLibelleDto(1L, STATUT_1));
        actionModificationDto.setResultat(new IdentifiantLibelleDto(2L, "Resultat 2"));
        final HistoriqueCommentaireDto commentaireDto = new HistoriqueCommentaireDto();
        commentaireDto.setDescriptif(Messages.getString("ActionServiceTest.380"));
        actionModificationDto.setCommentaire(commentaireDto);
        actionModificationDto.setRappel(true);
        actionModificationDto.setIdNotification(2L);
        actionModificationDto.setRappelMail(false);
        final Calendar nouvelleDateAction = Calendar.getInstance();
        nouvelleDateAction.add(Calendar.DAY_OF_MONTH, 1);
        actionModificationDto.setDateAction(nouvelleDateAction);
        // Appel au service
        actionService.modifierAction(actionModificationDto);
        final ActionDto resultat = actionService.rechercherActionParIdentifiant(2L);
        assertNotNull(Messages.getString("ActionServiceTest.381"), resultat);
        assertEquals(Messages.getString("ActionServiceTest.382"), nouvelleDateAction, resultat.getDateAction());
        assertEquals(Messages.getString("ActionServiceTest.383"), idNatureResultat, resultat.getNatureResultat().getIdentifiant());
        assertEquals(Messages.getString("ActionServiceTest.384"), "Nature résultat 2", resultat.getNatureResultat().getLibelle());
    }

    /** Teste les documents liés à une action. */
    @Test
    public void testDocumentsLiesAAction() {
        final String erreur = Messages.getString("ActionServiceTest.386");
        final ActionDto actionDto = actionService.rechercherActionParIdentifiant(2l);
        assertNotNull(erreur, actionDto.getDocuments());
        assertEquals(erreur, 2, actionDto.getDocuments().size());
        assertEquals(erreur, "workspace://05465s4d5s6q5s4d6q5s4d6q5s4d5ze5e5sd654-qsd2qdsf-qsdf", actionDto.getDocuments().get(0).getIdentifiantExterieur());
        assertEquals(erreur, "workspace://05465s4d5s6q5s4d6q5s4d6q5s4d5ze5e5sd654-qsd2qdsf-qsda", actionDto.getDocuments().get(1).getIdentifiantExterieur());
    }

    /** Teste l'ajout d'un document lié à une action. */
    @Test
    public void testAjoutDocumentLieAction() {
        final String erreur = Messages.getString("ActionServiceTest.389");
        final String erreurExceptionNonConforme = Messages.getString("ActionServiceTest.390");
        try {
            actionService.attacherDocuments(null, null);
            fail(erreurExceptionNonConforme);
        }
        catch (TechnicalException e) {
            assertEquals(erreurExceptionNonConforme, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_NULL), e.getMessage());
        }

        try {
            actionService.attacherDocuments(-15l, null);
            fail(erreurExceptionNonConforme);
        }
        catch (TechnicalException e) {
            assertEquals(erreurExceptionNonConforme, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_INEXISTANTE), e.getMessage());
        }
        // final List<DocumentDto> listeDocs = new ArrayList<DocumentDto>();
        // listeDocs.add(new DocumentDto());
        // try {
        // actionService.attacherDocuments(2l, listeDocs);
        // fail(erreurExceptionNonConforme);
        // } catch (TechnicalException e) {
        // assertEquals(erreurExceptionNonConforme, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DOCUMENT_EID_NULL), e.getMessage());
        // }

        final ActionDto ancienneActionDto = actionService.rechercherActionParIdentifiant(2l);
        final List<DocumentDto> listeDocuments = ancienneActionDto.getDocuments();
        final DocumentDto document = new DocumentDto("workspace://05465s4d5s6q5s4d6q5s4d6q5s4d5ze5e5sd654-qsd2qdsf-qsdb");
        listeDocuments.add(document);

        actionService.attacherDocuments(2l, listeDocuments);
        final ActionDto actionDto = actionService.rechercherActionParIdentifiant(2l);
        assertNotNull(erreur, actionDto.getDocuments());
        assertEquals(erreur, 3, actionDto.getDocuments().size());
    }

    /** Test de la modification d'une action avec vérification de la règle de gestion sur l'opportunité en cours. */
    @Test
    public void testModifierActionAvecRegleGestion() {
        // Passage à terminé d'une action "Normale" avec opportunité en cours
        final Long idAction3 = 3L;
        final ActionModificationDto actionModificationDto = new ActionModificationDto();
        actionModificationDto.setIdAction(idAction3);
        actionModificationDto.setStatut(new IdentifiantLibelleDto(squareMappingService.getIdStatutTerminer()));
        actionModificationDto.setVerifierRegleGestionOpportuniteEnCours(Boolean.TRUE);
        actionModificationDto.setNatureAction(new IdentifiantLibelleDto(3L));
        actionModificationDto.setRessource(new DimensionRessourceDto(1L));
        final Calendar dateAction = Calendar.getInstance();
        dateAction.add(Calendar.DAY_OF_MONTH, 1);
        actionModificationDto.setDateAction(dateAction);
        // Appel au service
        actionService.modifierAction(actionModificationDto);
        ActionDto actionModifiee = actionService.rechercherActionParIdentifiant(idAction3);
        assertEquals(Messages.getString("ActionServiceTest.392"), squareMappingService.getIdStatutTerminer(), actionModifiee.getStatut().getIdentifiant());

        // Passage à terminé d'une action de relance avec opportunité acceptée
        final Long idAction28 = 28L;
        actionModificationDto.setIdAction(idAction28);
        actionModificationDto.setStatut(new IdentifiantLibelleDto(squareMappingService.getIdStatutTerminer()));
        // Appel au service
        actionService.modifierAction(actionModificationDto);
        actionModifiee = actionService.rechercherActionParIdentifiant(idAction28);
        assertEquals(Messages.getString("ActionServiceTest.393"), squareMappingService.getIdStatutTerminer(), actionModifiee.getStatut().getIdentifiant());

        // Passage à "à faire" d'une action de relance avec opportunité en cours
        final Long idAction29 = 29L;
        actionModificationDto.setIdAction(idAction29);
        actionModificationDto.setStatut(new IdentifiantLibelleDto(squareMappingService.getIdStatutAFaire()));
        // Appel au service
        actionService.modifierAction(actionModificationDto);
        actionModifiee = actionService.rechercherActionParIdentifiant(idAction29);
        assertEquals(Messages.getString("ActionServiceTest.394"), squareMappingService.getIdStatutAFaire(), actionModifiee.getStatut().getIdentifiant());

        // Passage à "Terminé" d'une action de relance avec opportunité en cours ==> Exception
        final Long idAction30 = 30L;
        actionModificationDto.setIdAction(idAction30);
        actionModificationDto.setStatut(new IdentifiantLibelleDto(squareMappingService.getIdStatutTerminer()));
        // Appel au service
        try {
            actionService.modifierAction(actionModificationDto);
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("ActionServiceTest.395"), messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_RELANCE_TERMINEE_OPPORTUNITE_EN_COURS), e
                    .getMessage());
        }

        // Passage à "Terminé" d'une action de relance avec opportunité en cours sans vérification
        final Long idAction31 = 31L;
        actionModificationDto.setIdAction(idAction31);
        actionModificationDto.setStatut(new IdentifiantLibelleDto(squareMappingService.getIdStatutTerminer()));
        actionModificationDto.setVerifierRegleGestionOpportuniteEnCours(Boolean.FALSE);
        // Appel au service
        actionService.modifierAction(actionModificationDto);
        actionModifiee = actionService.rechercherActionParIdentifiant(idAction31);
        assertEquals(Messages.getString("ActionServiceTest.396"), squareMappingService.getIdStatutTerminer(), actionModifiee.getStatut().getIdentifiant());
    }

    /** Test de la recherche d'actions par agences et responsables. */
    @Test
    public void testRechercherActionsAgenceResponsable() {
        final ActionCritereRechercheDto criteres = new ActionCritereRechercheDto();
        final RemotePagingCriteriasDto<ActionCritereRechercheDto> criterias =
            new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteres, 0, Integer.MAX_VALUE);

        final Long idAgence1 = 1L;
        final Long idAgence2 = 2L;
        final Long idRessource2 = 2L;
        final Long idRessource3 = 3L;
        final Long idAction2 = 2L;
        final Long idAction4 = 4L;
        final Long idAction8 = 8L;
        final Long idAction34 = 34L;

        criteres.setIdAgences(new ArrayList<Long>());
        criteres.setIdCommerciaux(new ArrayList<Long>());

        // Recherche avec agence (1, 2) sans responsable, recherche de type ET
        criteres.getIdAgences().add(idAgence1);
        criteres.getIdAgences().add(idAgence2);
        criteres.setRechercheEtEntreAgencesEtCommerciaux(Boolean.TRUE);
        List<ActionRechercheDto> result = actionService.rechercherActionParCritere(criterias).getListResults();
        final int nbActions1 = 1;
        assertEquals(NOMBRE_ACTIONS_PAS_BON, nbActions1, result.size());
        assertEquals(Messages.getString("ActionServiceTest.397"), idAction8, result.get(0).getId());

        // Recherche avec agence (1, 2) sans responsable, recherche de type OU
        criteres.setRechercheEtEntreAgencesEtCommerciaux(Boolean.FALSE);
        result = actionService.rechercherActionParCritere(criterias).getListResults();
        final int nbActions2 = 33;
        assertEquals(NOMBRE_ACTIONS_PAS_BON, nbActions2, result.size());

        // Recherche avec responsable (2) sans agence, recherche de type ET
        criteres.getIdAgences().clear();
        criteres.getIdCommerciaux().add(idRessource2);
        criteres.setRechercheEtEntreAgencesEtCommerciaux(Boolean.TRUE);
        result = actionService.rechercherActionParCritere(criterias).getListResults();
        final int nbActions3 = 2;
        assertEquals(NOMBRE_ACTIONS_PAS_BON, nbActions3, result.size());
        boolean possedeAction2 = false;
        for (ActionRechercheDto action : result) {
            if (idAction2.equals(action.getId())) {
                possedeAction2 = true;
            }
        }
        assertTrue(Messages.getString("ActionServiceTest.398"), possedeAction2);

        // Recherche avec responsable (2) sans agence, recherche de type OU
        criteres.getIdAgences().clear();
        criteres.getIdCommerciaux().add(idRessource2);
        criteres.setRechercheEtEntreAgencesEtCommerciaux(Boolean.FALSE);
        result = actionService.rechercherActionParCritere(criterias).getListResults();
        final int nbActions4 = 2;
        assertEquals(NOMBRE_ACTIONS_PAS_BON, nbActions4, result.size());
        possedeAction2 = false;
        for (ActionRechercheDto action : result) {
            if (idAction2.equals(action.getId())) {
                possedeAction2 = true;
            }
        }
        assertTrue(Messages.getString("ActionServiceTest.399"), possedeAction2);

        // Recherche avec agence (1) et responsable (2,3), recherche de type ET
        criteres.getIdAgences().clear();
        criteres.getIdAgences().add(idAgence1);
        criteres.getIdCommerciaux().clear();
        criteres.getIdCommerciaux().add(idRessource2);
        criteres.getIdCommerciaux().add(idRessource3);
        criteres.setRechercheEtEntreAgencesEtCommerciaux(Boolean.TRUE);
        result = actionService.rechercherActionParCritere(criterias).getListResults();
        final int nbActions5 = 2;
        assertEquals(NOMBRE_ACTIONS_PAS_BON, nbActions5, result.size());
        possedeAction2 = false;
        for (ActionRechercheDto action : result) {
            if (idAction2.equals(action.getId())) {
                possedeAction2 = true;
            }
        }
        assertTrue(Messages.getString("ActionServiceTest.400"), possedeAction2);

        // Recherche avec agence (1) et responsable (2,3), recherche de type OU
        criteres.setRechercheEtEntreAgencesEtCommerciaux(Boolean.FALSE);
        result = actionService.rechercherActionParCritere(criterias).getListResults();
        final int nbActions6 = 3;
        assertEquals(NOMBRE_ACTIONS_PAS_BON, nbActions6, result.size());
        possedeAction2 = false;
        boolean possedeAction34 = false;
        for (ActionRechercheDto action : result) {
            if (idAction2.equals(action.getId())) {
                possedeAction2 = true;
            }
            else if (idAction34.equals(action.getId())) {
                possedeAction34 = true;
            }
        }
        assertTrue(Messages.getString("ActionServiceTest.401"), possedeAction2 && possedeAction34);
    }

    /** Test de la création d'action liée à une opportunité sans rensigner l'action source. */
    @Test
    public void testCreationActionOpportuniteSansActionSource() {
        // Création des données
        final Long idOpportunite = 5L;
        final Long idPersonne = 13L;
        final Long idAction36 = 36L;
        final Long idAction37 = 37L;
        final Long idAction38 = 38L;
        final ActionCreationDto action = new ActionCreationDto();
        final Calendar dateAction = Calendar.getInstance();
        dateAction.add(Calendar.HOUR_OF_DAY, 2);
        action.setDateAction(dateAction);
        action.setIdOpportunite(idOpportunite);
        action.setIdPersonne(idPersonne);
        action.setIdNatureAction(3L);
        action.setRappel(false);
        action.setIdNotificationList(null);
        action.setIdObjet(3L);
        action.setIdPriorite(2L);
        action.setIdSousObjet(2L);
        action.setIdType(2L);
        action.setMePrevenirParMail(false);
        action.setReclamation(false);
        action.setIdAgence(2L);
        action.setIdCommercial(1L);
        final ActionCreationDto actionCreee = actionService.creerAction(action);
        final CritereActionSyntheseDto critereActionSyntheseDto = new CritereActionSyntheseDto();
        critereActionSyntheseDto.setIdOpportunite(idOpportunite);
        critereActionSyntheseDto.setIdPersonne(idPersonne);
        final List<Stack<ActionSyntheseDto>> listeActionsOpportunite = actionService.recupererActionsSynthese(critereActionSyntheseDto);
        assertEquals(Messages.getString("ActionServiceTest.402"), actionCreee.getIdentifiant(), listeActionsOpportunite.get(0).pop().getId());
        assertEquals(Messages.getString("ActionServiceTest.403"), idAction38, listeActionsOpportunite.get(0).pop().getId());
        assertEquals(Messages.getString("ActionServiceTest.404"), idAction37, listeActionsOpportunite.get(0).pop().getId());
        assertEquals(Messages.getString("ActionServiceTest.405"), idAction36, listeActionsOpportunite.get(0).pop().getId());
    }

    /** Test de transfert d'actions. */
    @Test
    public void testTransfererActionsParCritere() {
        final Long idAgence = 1L;
        final Long idRessource = 1L;
        final String keyErreurAgence = ActionRechercheDto.class.getSimpleName() + ".agence.id";

        ActionCritereRechercheDto criteres = null;
        Long idAgenceCible = null;
        Long idRessourceCible = null;

        try {
            actionService.transfererActionsParCritere(criteres, idAgenceCible, idRessourceCible);
            fail(Messages.getString("ActionServiceTest.407") + messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_RECHERCHE_ACTION_DTO_NULL));
        }
        catch (TechnicalException e) {
            assertEquals(Messages.getString("ActionServiceTest.408"), messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_RECHERCHE_ACTION_DTO_NULL), e.getMessage());
        }

        criteres = new ActionCritereRechercheDto();
        try {
            actionService.transfererActionsParCritere(criteres, idAgenceCible, idRessourceCible);
            fail(Messages.getString("ActionServiceTest.409") + messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_TRANSFERT_ACTION_AGENCE_NULL));
        }
        catch (ControleIntegriteException e) {
            final RapportDto rapport = e.getRapport();
            assertTrue(Messages.getString("ActionServiceTest.410"), rapport.getEnErreur());
            assertEquals(Messages.getString("ActionServiceTest.411"), rapport.getRapports().size(), 1);
            assertNotNull(Messages.getString("ActionServiceTest.412"), rapport.getRapports().get(keyErreurAgence));
            assertEquals(Messages.getString("ActionServiceTest.413"), messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_TRANSFERT_ACTION_AGENCE_NULL), rapport
                    .getRapports().get(keyErreurAgence).getMessage());
        }

        final RemotePagingCriteriasDto<ActionCritereRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteres, 0, Integer.MAX_VALUE);

        // AFFECTATION A UNE AGENCE SEULE
        criteres = new ActionCritereRechercheDto();
        criteresDto.setCriterias(criteres);
        idAgenceCible = idAgence;
        actionService.transfererActionsParCritere(criteres, idAgenceCible, idRessourceCible);
        // on verifie toutes les actions
        RemotePagingResultsDto<ActionRechercheDto> results = actionService.rechercherActionParCritere(criteresDto);
        for (ActionRechercheDto action : results.getListResults()) {
            assertEquals(Messages.getString("ActionServiceTest.414"), idAgence, action.getAgence().getIdentifiant());
            assertNull(Messages.getString("ActionServiceTest.415"), action.getCommercial());
        }

        // AFFECTATION A UNE RESSOURCE D'AGENCE
        criteres = new ActionCritereRechercheDto();
        criteresDto.setCriterias(criteres);
        idAgenceCible = idAgence;
        idRessourceCible = idRessource;
        actionService.transfererActionsParCritere(criteres, idAgenceCible, idRessourceCible);
        // on verifie toutes les actions
        results = actionService.rechercherActionParCritere(criteresDto);
        for (ActionRechercheDto action : results.getListResults()) {
            assertEquals(Messages.getString("ActionServiceTest.416"), idAgence, action.getAgence().getIdentifiant());
            assertEquals(Messages.getString("ActionServiceTest.417"), idRessource, action.getCommercial().getIdentifiant());
        }
    }

    /**
     * Test du service {@link ActionService#supprimerAction(Long)}.
     */
    @Test
    public void testSupprimerAction() {
        try {
            actionService.supprimerAction(null);
            fail(Messages.getString("ActionServiceTest.418"));
        }
        catch (BusinessException e) {
            assertEquals(EXCEPTION_LEVEE_DIFFERENTE_DE_CELLE_ATTENDUE, messageSourceUtil
                    .get(ActionKeyUtil.MESSAGE_ERREUR_SUPPRIMER_ACTION_PARAM_ID_ACTION_OBLIGATOIRE), e.getMessage());
        }
        // On initialise l'identifiant de l'action à supprimer avec un identifiant d'une action qui n'existe pas
        Long idActionASupprimer = 1L;
        try {
            actionService.supprimerAction(idActionASupprimer);
            fail(Messages.getString("ActionServiceTest.419"));
        }
        catch (TechnicalException e) {
            assertEquals(EXCEPTION_LEVEE_DIFFERENTE_DE_CELLE_ATTENDUE, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_SUPPRIMER_ACTION_ACTION_INEXISTANTE,
                new String[] {idActionASupprimer.toString()}), e.getMessage());
        }
        // On spécifie un identifiant d'une action à supprimer qui existe
        idActionASupprimer = 16L;
        // Actions liées à l'action 16
        final Long idActionLiee17 = 17L;
        final Long idActionLiee18 = 18L;
        // Action liée à l'action 17
        final Long idActionLiee19 = 19L;

        // On supprime l'action
        actionService.supprimerAction(idActionASupprimer);
        // On vérifie que l'action n'existe plus dans la base de données
        try {
            actionService.rechercherActionParIdentifiant(idActionASupprimer);
            fail(Messages.getString("ActionServiceTest.420"));
        }
        catch (BusinessException e) {
            assertEquals(EXCEPTION_LEVEE_DIFFERENTE_DE_CELLE_ATTENDUE, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_INEXISTANT), e
                    .getMessage());
        }
        // On vérifie que les actions liées de l'action supprimée n'existent plus
        final int nbResultatsAttendu = 0;
        final ActionCritereRechercheDto criterias = new ActionCritereRechercheDto();
        final Set<Long> idsActions = new HashSet<Long>();
        idsActions.add(idActionLiee17);
        idsActions.add(idActionLiee18);
        idsActions.add(idActionLiee19);
        criterias.setIdsActions(idsActions);
        final RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres =
            new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criterias, 0, Integer.MAX_VALUE);
        final RemotePagingResultsDto<ActionRechercheDto> result = actionService.rechercherActionParCritere(criteres);
        assertEquals(Messages.getString("ActionServiceTest.421"), nbResultatsAttendu, result.getTotalResults());
        assertEquals(Messages.getString("ActionServiceTest.422"), nbResultatsAttendu, result.getListResults().size());
    }
}
