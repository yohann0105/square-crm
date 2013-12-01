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
package com.square.logs.service.interfaces;

import java.util.Calendar;
import java.util.List;

import com.square.logs.core.model.dto.LogDto;

/**
 * interface de service de Log.
 * @author Ksouri MohamedAli - SCUB
 */
public interface LogService {
    /**
     * Ajoute un log dans la queue des logs à sauvegarder.
     * @param logDto log à ajouter
     */
    void ajouterLog(LogDto logDto);

    /**
     * Sauvegarde un log.
     * @param logDto log à ajouter
     * @return le log sauvegardé
     */
    LogDto persister(LogDto logDto);

    /**
     * Recupere les ids des logs avant une date.
     * @param date la date
     * @param pagination le nombre a récupérer
     * @return la liste des identifiants
     */
    List<Long> getIdsLogsBeforeDate(Calendar date, int pagination);

    /**
     * Recupere le nombre de logs avant une date.
     * @param date la date
     * @return le nombre de logs
     */
    int getNbLogsBeforeDate(Calendar date);

    /**
     * Supprime des logs par leurs ids.
     * @param idsASupprimer les ids à supprimer
     * @return le nombre de logs supprimés
     */
    int supprimerLogsByIds(List<Long> idsASupprimer);

    /**
     * Retourne les logs dans la queue des logs à sauvegarder.
     * @return les logs pas encore sauvegardés.
     */
    List<LogDto> getLogsEnAttente();
}
