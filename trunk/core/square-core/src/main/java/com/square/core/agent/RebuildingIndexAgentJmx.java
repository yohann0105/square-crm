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
package com.square.core.agent;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.SessionFactory;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.core.model.ModelData;
import com.square.core.service.interfaces.PersonneMoraleService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.util.AgentJmxKeyUtil;

/**
 * Rebuild Automatique de l'indexe Hibernate Search.
 * @author Goumard Stephane (stephane.goumard@scub.net) - SCUB
 */
public class RebuildingIndexAgentJmx implements RebuildingIndexAgentJmxMBean {

    /**
     * La session factory hibernate.
     */
    private SessionFactory sessionFactory;

    /** Etat. */
    private String etat;

    /** Date de la derniere execution du traitement. */
    private Calendar dateFinExecution;

    /** Date de debut execution. */
    private Calendar dateDebutExecution;

    /** Message Source Util. */
    private MessageSourceUtil messageSourceUtil;

	/** Variable booléenne repère pour l'arrêt du traitement. */
    private boolean stopping = true;

    /** Requete pour la réindexation manuelle. */
    private String requete;

    /** Logger. **/
    private Logger logger = RootLogger.getLogger(RebuildingIndexAgentJmx.class);

    /** Configuration. **/
    private Integer threadsForSubsequentFetching;

    /** Batch Size. **/
    private Integer batchSizeToLoad;

    /** Nombre de thread. **/
    private Integer threadsToLoad;

    /** Nombre d'entité max a charger (pour le dev). */
    private Integer limitIndexedObjectsTo;

    /** Process Smatis Filtre sur Date Modification PP. **/
    private Integer procPpNowAddIntervalHour;

    /** Process Smatis Filtre sur Date Modification PP. **/
    private Integer procPpNowSubIntervaleDay;

    /** Service des personnes physiques. */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Service des personnes morales. */
    private PersonneMoraleService personneMoraleService;

    /**
     * Liste des classes pour refaire l'indexe.
     */
    private List<Class<? extends ModelData>> procMasIndexerCandidate;

    @Override
    public String getDateDebutExecution() {

        return dateDebutExecution == null ? "" : messageSourceUtil.getDateFormat(dateDebutExecution);
    }

    @Override
    public String getDateFinExecution() {
        return dateFinExecution == null ? "" : messageSourceUtil.getDateFormat(dateFinExecution);
    }

    @Override
    public String getEtat() {
        return etat == null ? "" : etat;
    }

    /**
     * Set the param value.
     * @param etat the etat to set
     */
    public void setEtat(String etat) {
        logger.debug(etat);
        this.etat = etat;
    }

    @Override
    public void start() {
        if (stopping) {
            stopping = false;
            dateDebutExecution = Calendar.getInstance();
            dateFinExecution = null;
            new RebuildingIndexAgentJmxThead(this).start();
        }
    }

    @Override
    public void stop() {
        if (!stopping) {
            setEtat(messageSourceUtil.get(AgentJmxKeyUtil.MESSAGE_AGENT_REBUILDINDEX_FIN_TRAITEMENT));
            dateFinExecution = Calendar.getInstance();
            stopping = true;
        }
    }

    /**
     * Fixe le parametre.
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get the param value.
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Fixe le parametre.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    @Override
    public Integer getBatchSizeToLoad() {
        return batchSizeToLoad;
    }

    @Override
    public Integer getThreadsForSubsequentFetching() {
        return threadsForSubsequentFetching;
    }

    @Override
    public Integer getThreadsToLoad() {
        return threadsToLoad;
    }

    @Override
    public void setBatchSizeToLoad(Integer size) {
        batchSizeToLoad = size;

    }

    @Override
    public void setThreadsForSubsequentFetching(Integer number) {
        threadsForSubsequentFetching = number;

    }

    @Override
    public void setThreadsToLoad(Integer number) {
        threadsToLoad = number;
    }

    /**
     * Get the param value.
     * @return the stopping
     */
    public boolean isStopping() {
        return stopping;
    }

    /**
     * Récupération de limitIndexedObjectsTo.
     * @return the limitIndexedObjectsTo
     */
    public Integer getLimitIndexedObjectsTo() {
        return limitIndexedObjectsTo;
    }

    /**
     * Définition de limitIndexedObjectsTo.
     * @param limitIndexedObjectsTo the limitIndexedObjectsTo to set
     */
    public void setLimitIndexedObjectsTo(Integer limitIndexedObjectsTo) {
        this.limitIndexedObjectsTo = limitIndexedObjectsTo;
    }

    /**
     * Récupération de requete.
     * @return the requete
     */
    public String getRequete() {
        return requete;
    }

    /**
     * Définition de requete.
     * @param requete the requete to set
     */
    public void setRequete(String requete) {
        this.requete = requete;
    }

    /**
     * Recuperer la valeur.
     * @return the procPpNowAddIntervalHour
     */
    public Integer getProcPpNowAddIntervalHour() {
        return procPpNowAddIntervalHour;
    }

    /**
     * Fixer la valeur.
     * @param procPpNowAddIntervalHour the procPpNowAddIntervalHour to set
     */
    public void setProcPpNowAddIntervalHour(Integer procPpNowAddIntervalHour) {
        this.procPpNowAddIntervalHour = procPpNowAddIntervalHour;
    }

    /**
     * Recuperer la valeur.
     * @return the proPpNowSubIntervaleDay
     */
    public Integer getProcPpNowSubIntervaleDay() {
        return procPpNowSubIntervaleDay;
    }

    /**
     * Fixer la valeur.
     * @param proPpNowSubIntervaleDay the proPpNowSubIntervaleDay to set
     */
    public void setProcPpNowSubIntervaleDay(Integer proPpNowSubIntervaleDay) {
        this.procPpNowSubIntervaleDay = proPpNowSubIntervaleDay;
    }

    /**
     * Fixer la valeur.
     * @param procMasIndexerCandidate the procMasIndexerCandidate to set
     */
    public void setProcMasIndexerCandidate(List<Class<? extends ModelData>> procMasIndexerCandidate) {
        this.procMasIndexerCandidate = procMasIndexerCandidate;
    }

    /**
     * Recuperer la valeur.
     * @return the procMasIndexerCandidate
     */
    public List<Class<? extends ModelData>> getProcMasIndexerCandidate() {
        return procMasIndexerCandidate;
    }

    /**
     * Setter de personnePhysiqueService.
     * @param personnePhysiqueService personnePhysiqueService
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Getter de personnePhysiqueService.
     * @return le service des personnes physiques
     */
    public PersonnePhysiqueService getPersonnePhysiqueService() {
        return personnePhysiqueService;
    }

    /**
     * Getter de personneMoraleService.
     * @return the personneMoraleService
     */
    public PersonneMoraleService getPersonneMoraleService() {
        return personneMoraleService;
    }

    /**
     * Setter de personneMoraleService.
     * @param personneMoraleService the personneMoraleService to set
     */
    public void setPersonneMoraleService(PersonneMoraleService personneMoraleService) {
        this.personneMoraleService = personneMoraleService;
    }

    /**
     * .
     * @return le messageSourceUtil
     */
    public MessageSourceUtil getMessageSourceUtil() {
		return messageSourceUtil;
	}

}
