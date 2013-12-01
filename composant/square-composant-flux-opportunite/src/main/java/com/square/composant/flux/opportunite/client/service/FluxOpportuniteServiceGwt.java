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
package com.square.composant.flux.opportunite.client.service;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.flux.opportunite.client.model.AccesPotCommunModel;
import com.square.composant.flux.opportunite.client.model.QuotaModel;

/**
 * Service pour les examples.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath("handler/fluxOpportuniteService")
public interface FluxOpportuniteServiceGwt extends RemoteService {

    /**
     * Class statique permettant de recuperer une instance du service asynchrone.
     */
    public static final class App {
        private static FluxOpportuniteServiceGwtAsync app = null;

        /**
         * Constructeur prive.
         */
        private App() { }

        /**
         * Recupere l'instance du service.
         * @return le service.
         */
        public static synchronized FluxOpportuniteServiceGwtAsync getInstance() {
            if (app == null) {
                app = (FluxOpportuniteServiceGwtAsync) GWT.create(FluxOpportuniteServiceGwt.class);
            }
            return app;
        }
    }

    /**
     * Récupère la liste des quotas d'une ressource.
     * @param idRessource l'identifiant de la ressource.
     * @return la liste des quotas de la ressource.
     */
    List<QuotaModel> getListeQuotasRessource(Long idRessource) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère la liste des jours des quotas.
     * @return la liste des jours des quotas.
     */
    List<IdentifiantLibelleGwt> getListeJours() throws GwtRunTimeExceptionGwt;

    /**
     * Modifie les quotas de certaines ressources.
     * @param listeIdRessources la liste des resources à modifier.
     * @param listeQuotas les nouvelles valeurs des quotas.
     */
    void modifierQuotas(List<Long> listeIdRessources, List<QuotaModel> listeQuotas) throws GwtRunTimeExceptionGwt;

    /**
     * Détermine si une ressource a accés au pot commun.
     * @param idRessource l'identifiant de la ressource.
     * @return les informations d'accès au pot commun.
     */
    AccesPotCommunModel hasAccesPotCommun(Long idRessource) throws GwtRunTimeExceptionGwt;
}