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
package com.square.core.agent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.hibernate.search.SearchException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.model.BaseModel;

import com.square.core.model.ModelData;
import com.square.core.model.PersonneMorale;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneMoraleDto;
import com.square.core.service.interfaces.PersonneMoraleService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.util.AgentJmxKeyUtil;

/**
 * Classe Threas execution de l'indexation dans Square.
 * @author Goumard Stephane (scub) - Juanito Goncalves (scub) - SCUB
 */
public class RebuildingIndexAgentJmxThead extends Thread {

    /**
     * Liste des classes à indexer.
     */
    private RebuildingIndexAgentJmx agent;

    /**
     * Service des personnes physiques.
     */
    private PersonnePhysiqueService personnePhysiqueService;

    /**
     * Service des personnes morales.
     */
    private PersonneMoraleService personneMoraleService;

    /** Logger. **/
    private Logger logger = Logger.getLogger(RebuildingIndexAgentJmxThead.class);

    /**
     * Constructeur.
     * @param agent l'agent JMX qui supervise le thread.
     */
    public RebuildingIndexAgentJmxThead(RebuildingIndexAgentJmx agent) {
        this.agent = agent;
        this.personnePhysiqueService = agent.getPersonnePhysiqueService();
        this.personneMoraleService = agent.getPersonneMoraleService();
    }

    @Override
    public void run() {
        agent.setEtat(agent.getMessageSourceUtil().get(AgentJmxKeyUtil.MESSAGE_AGENT_REBUILD_INDEX_DEBUT_TRAITEMENT));
        final Session session = agent.getSessionFactory().openSession();
        try {
            if (agent.getRequete() != null && !"".equals(agent.getRequete().trim())) {
                runManualIndexer(session);
            } else if (agent.getProcPpNowAddIntervalHour().intValue() == -1 && agent.getProcPpNowSubIntervaleDay().intValue() == -1) {
                runMassIndexerReprise(session);
            } else {
                runProcessSmatisManuelIndexer();
            }
        } catch (Exception e) {
            agent.setEtat(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
            agent.stop();
        }
    }

    /**
     * Lancement indexation manuelle sur requete.
     */
    private void runManualIndexer(Session session) {
        final FullTextSession fullTextSession = Search.getFullTextSession(session);
        try {
            fullTextSession.setFlushMode(FlushMode.MANUAL);
            fullTextSession.setCacheMode(CacheMode.IGNORE);
            final Transaction transaction = fullTextSession.beginTransaction();
            // Scrollable results will avoid loading too many objects in memory
            final ScrollableResults results =
                fullTextSession.createQuery(agent.getRequete()).setFetchSize(agent.getBatchSizeToLoad()).scroll(ScrollMode.FORWARD_ONLY);
            int index = 0;
            while (results.next()) {
                index++;
                logger.debug(agent.getMessageSourceUtil().get(AgentJmxKeyUtil.MESSAGE_INDEXATION_DE) + " " + results.get(0) + " (id = "
                    + ((BaseModel) results.get(0)).getId() + ")");
                fullTextSession.index(results.get(0)); // index each element
                if (index % agent.getBatchSizeToLoad() == 0) {
                    fullTextSession.flushToIndexes(); // apply changes to indexes
                    fullTextSession.clear(); // free memory since the queue is processed
                }
            }
            transaction.commit();
        } catch (SearchException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lancement indexation manuelle sur requete.
     */
    private void runProcessSmatisManuelIndexer() {
        final Calendar now = Calendar.getInstance();
        now.clear(Calendar.HOUR);
        now.clear(Calendar.MINUTE);
        now.clear(Calendar.SECOND);
        now.clear(Calendar.MILLISECOND);
        now
                .add(Calendar.DAY_OF_MONTH, agent.getProcPpNowSubIntervaleDay() < 0 ? agent.getProcPpNowSubIntervaleDay() : agent.getProcPpNowSubIntervaleDay()
                    * -1);
        now.set(Calendar.HOUR_OF_DAY, agent.getProcPpNowAddIntervalHour());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        agent.setEtat(agent.getMessageSourceUtil().get(AgentJmxKeyUtil.MESSAGE_LANCEMENT_INDEXATION_PROCESS_SMATIS_PP) + " " + format.format(now.getTime()));

        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String requetePP =
            "from PersonnePhysique pp where (pp.dateModification > '" + format.format(now.getTime()) + "'"
                + " OR pp.dateModification is null) and pp.identifiantExterieur is not null";

        // Récupération des personnes à indexer
        final List<PersonneDto> personnesPhysiques = personnePhysiqueService.rechercherPersonneParRequete(requetePP);

        // Indexation de ces personnes
        final List<Long> listeIdsPPAIndexer = new ArrayList<Long>();
        for (PersonneDto personne : personnesPhysiques) {
            listeIdsPPAIndexer.add(personne.getIdentifiant());
        }
        personnePhysiqueService.indexerListePersonnesPhysiques(listeIdsPPAIndexer);

        agent.setEtat(agent.getMessageSourceUtil().get(AgentJmxKeyUtil.MESSAGE_LANCEMENT_INDEXATION_PROCESS_SMATIS_PM) + " " + format.format(now.getTime()));
        final String requetePM =
            "from PersonneMorale pm where (pm.dateModification > '" + format.format(now.getTime()) + "'"
                + " OR pm.dateModification is null) and pm.identifiantExterieur is not null";

        // Récupération des personnes morales à indexer
        final List<PersonneMoraleDto> personnesMorales = personneMoraleService.rechercherPersonnesMoralesParRequete(requetePM);

        // Indexation de ces personnes morales
        final List<Long> listeIdsPMAIndexer = new ArrayList<Long>();
        for (PersonneMoraleDto personne : personnesMorales) {
            listeIdsPMAIndexer.add(personne.getId());
        }
        personneMoraleService.indexerListePersonnesMorales(listeIdsPMAIndexer);
    }

    /**
     * Lancement indexation de masse.
     */
    private void runMassIndexer(Session session, List<Class<? extends ModelData>> entities) {
        FullTextSession fullTextSession = null;
        try {
            fullTextSession = Search.getFullTextSession(session);
            for (int indexEntite = 0; indexEntite < entities.size() && !agent.isStopping(); indexEntite++) {
                String etat = "Execute rebuild index to " + entities.get(indexEntite).getSimpleName();
                final MassIndexer indexer =
                    fullTextSession.createIndexer(entities.get(indexEntite)).batchSizeToLoadObjects(agent.getBatchSizeToLoad()).threadsToLoadObjects(
                        agent.getThreadsToLoad()).threadsForSubsequentFetching(agent.getThreadsForSubsequentFetching()).cacheMode(CacheMode.IGNORE);
                if (agent.getLimitIndexedObjectsTo() != null && agent.getLimitIndexedObjectsTo().intValue() > 0) {
                    indexer.limitIndexedObjectsTo(agent.getLimitIndexedObjectsTo());
                    etat += " (Limite : " + agent.getLimitIndexedObjectsTo() + " )";
                }
                agent.setEtat(etat);
                indexer.startAndWait();
            }
        } catch (InterruptedException e) {
            logger.error(e);
        } catch (SearchException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lancement de l'indexation de masse pour la reprise des données.
     */
    private void runMassIndexerReprise(Session session) {
        agent.setEtat(agent.getMessageSourceUtil().get(AgentJmxKeyUtil.MESSAGE_LANCEMENT_INDEXATION_MODE_REPRISE_DONNEES));
        final List<Class<? extends ModelData>> entites = new ArrayList<Class<? extends ModelData>>();
        entites.add(PersonnePhysique.class);
        entites.add(PersonneMorale.class);
        entites.add(Ressource.class);
        runMassIndexer(session, entites);
    }

}
