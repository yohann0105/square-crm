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

/**
 * Interface expose les methodes via JMX.
 * @author Goumard Stephane (Scub) - SCUB
 */
public interface RebuildingIndexAgentJmxMBean extends IAgentJmxBaseMBean {

    /**
     * Batch Size.
     * @return .
     */
    Integer getBatchSizeToLoad();

    /**
     * Nombre de thread.
     * @return .
     */
    Integer getThreadsToLoad();

    /**
     * Nombre de thread pour les jointures.
     * @return .
     */
    Integer getThreadsForSubsequentFetching();

    /**
     * Batch Size.
     * @param size .
     */
    void setBatchSizeToLoad(Integer size);

    /**
     * Nombre de thread.
     * @param number .
     */
    void setThreadsToLoad(Integer number);

    /**
     * Nombre de thread pour les jointures.
     * @param number .
     */
    void setThreadsForSubsequentFetching(Integer number);

    /**
     * Récupération de limitIndexedObjectsTo.
     * @return the limitIndexedObjectsTo
     */
    Integer getLimitIndexedObjectsTo();

    /**
     * Définition de limitIndexedObjectsTo.
     * @param limitIndexedObjectsTo the limitIndexedObjectsTo to set
     */
    void setLimitIndexedObjectsTo(Integer limitIndexedObjectsTo);

    /**
     * Récupération de requete.
     * @return the requete
     */
    String getRequete();

    /**
     * Définition de requete.
     * @param requete the requete to set
     */
    void setRequete(String requete);

    /**
     * Permet de jouer sur le filtre sur la date de modification des PP prise en charge
     * dans l'indexation. deduit à la date du jour ce paramétre tel que le fitre sa appliqué.
     * pp.dateModification > now() - day.
     * @param day valeur accépté N ou -N.
     */
    void setProcPpNowSubIntervaleDay(Integer day);

    /**
     * Permet de jouer sur le filtre sur la date de modification des PP prise en charge
     * dans l'indexation. deduit à la date du jour ce paramétre tel que le fitre sa appliqué.
     * pp.dateModification > now() - day.
     * @return la valeur.
     */
    Integer getProcPpNowSubIntervaleDay();

    /**
     * Permet de jouer sur le filtre sur la date de modification des PP prise en charge
     * dans l'indexation. rajoute a la date du jour l'heure passé en paramétre.
     * pp.dateModification > now() - getProcPpNowSubIntervaleDay + procPpNowAddIntervalHour.
     * @return la valeur.
     */
    Integer getProcPpNowAddIntervalHour();

    /**
     * Permet de jouer sur le filtre sur la date de modification des PP prise en charge
     * dans l'indexation. rajoute a la date du jour l'heure passé en paramétre.
     * pp.dateModification > now() - getProcPpNowSubIntervaleDay() + procPpNowAddIntervalHour.
     * @param procPpNowAddIntervalHour valeur accépté N ou -N.
     */
    void setProcPpNowAddIntervalHour(Integer procPpNowAddIntervalHour);

}
