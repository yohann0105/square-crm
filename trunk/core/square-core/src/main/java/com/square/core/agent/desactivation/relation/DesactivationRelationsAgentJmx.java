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
package com.square.core.agent.desactivation.relation;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.SessionFactory;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.core.service.interfaces.PersonneService;

/**
 * Désactive les relations qui ont une date de fin à la date du jour.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class DesactivationRelationsAgentJmx implements DesactivationRelationsAgentJmxMBean {

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
    private Logger logger = RootLogger.getLogger(this.getClass());

    /** Service des personnes physiques. */
    private PersonneService personneService;

    private int pagination;

    private String date;

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
            new DesactivationRelationsAgentJmxThead(this).start();
        }
    }

    @Override
    public void stop() {
        if (!stopping) {
            setEtat("Agent DesactivationRelations fin du traitement.");
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

    /**
     * Get the param value.
     * @return the stopping
     */
    public boolean isStopping() {
        return stopping;
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
     * Get the value of personneService.
     * @return the personneService
     */
    public PersonneService getPersonneService() {
        return personneService;
    }

    /**
     * Set the value of personneService.
     * @param personneService the personneService to set
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    @Override
    public int getPagination() {
        return pagination;
    }

    @Override
    public void setPagination(int pagination) {
        this.pagination = pagination;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }
}
