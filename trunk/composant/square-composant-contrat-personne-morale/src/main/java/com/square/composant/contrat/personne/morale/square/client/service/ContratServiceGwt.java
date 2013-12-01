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
package com.square.composant.contrat.personne.morale.square.client.service;

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.contrat.personne.morale.square.client.model.ContratPersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.model.InfosContratsPersonneMoraleModel;

/**
 * Interface synchrone des services GWT pour le service des contrats.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/contratPersonneMoraleService")
public interface ContratServiceGwt extends RemoteService {

    /**
     * Class statique permettant de recuperer une instance du service asynchrone.
     */
    public static final class App {
        private static ContratServiceGwtAsync app = null;

        /**
         * Constructeur prive.
         */
        private App() { }

        /**
         * Recupere l'instance du service.
         * @return le service.
         */
        public static synchronized ContratServiceGwtAsync getInstance() {
            if (app == null) {
                app = (ContratServiceGwtAsync) GWT.create(ContratServiceGwt.class);
            }
            return app;
        }
    }

    /**
     * Récupère les informations (synthèse et liste des contrats) des contrats d'une personne morale.
     * @param uidPersonneMorale l'identifiant unique de la personne morale.
     * @return les contrats de la personne morale.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    InfosContratsPersonneMoraleModel getInfosContratPersonneMorale(Long uidPersonneMorale) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère les informations d'un contrat d'une personne morale.
     * @param uidContrat l'identifiant unique du contrat à récupérer.
     * @return le contrat.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    ContratPersonneMoraleModel getContratPersonneMorale(Long uidContrat) throws GwtRunTimeExceptionGwt;

}