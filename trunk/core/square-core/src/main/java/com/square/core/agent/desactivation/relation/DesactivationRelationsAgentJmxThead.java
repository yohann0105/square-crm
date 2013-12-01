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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.service.interfaces.PersonneService;

/**
 * Classe Thread desactivation des relations à la date du jour dans Square.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class DesactivationRelationsAgentJmxThead extends Thread {

    private DesactivationRelationsAgentJmx agent;

    private PersonneService personneService;

    /** Logger. **/
    private Logger logger = Logger.getLogger(DesactivationRelationsAgentJmxThead.class);

    /**
     * Constructeur.
     * @param agent l'agent JMX qui supervise le thread.
     */
    public DesactivationRelationsAgentJmxThead(DesactivationRelationsAgentJmx agent) {
        this.agent = agent;
        this.personneService = agent.getPersonneService();
    }

    @Override
    public void run() {
        agent.setEtat("Agent DesactivationRelations debut de traitement");
        try {
            final Calendar date = Calendar.getInstance();
            if (StringUtils.isNotBlank(agent.getDate())) {
                final SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                date.setTime(formatDate.parse(agent.getDate()));
            }
            date.clear(Calendar.HOUR);
            date.clear(Calendar.MINUTE);
            date.clear(Calendar.SECOND);
            date.clear(Calendar.MILLISECOND);

            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            agent.setEtat("Lancement desactivation relations à la date du " + format.format(date.getTime()));
            int counter = 0;

            // Création des criteres des relations à désactiver
            final RelationCriteresRechercheDto criterias = new RelationCriteresRechercheDto();
            criterias.setDateFinMax(date);
            criterias.setActif(true);
            criterias.setSupprime(null);

            // Calcul du nombre total
            final int nbTotal = personneService.countRelationsParCriteres(criterias);
            agent.setEtat("Traitement des relations : " + nbTotal + " éléments");
            // Recuparation des ids de relations
            List<Long> listeRelationsADesactiver = personneService.rechercherIdsRelationsADesactiver(date, agent.getPagination());


            while (listeRelationsADesactiver.size() > 0 && !agent.isStopping()) {
                counter += listeRelationsADesactiver.size();
                logger.debug("Désactivation des relations en cours " + counter + " / " + nbTotal);
                personneService.desactiverRelations(listeRelationsADesactiver);

                // Récupération des relations à désactiver
                listeRelationsADesactiver = personneService.rechercherIdsRelationsADesactiver(date, agent.getPagination());
            }
        } catch (Exception e) {
            agent.setEtat(e.getMessage());
            e.printStackTrace();
        } finally {
            agent.getSessionFactory().openSession().close();
            agent.stop();
        }
    }
}
