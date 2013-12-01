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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.composant.flux.opportunite.client.model.AccesPotCommunModel;
import com.square.composant.flux.opportunite.client.model.QuotaModel;

/**
 * Service asynchrone sur les examples.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface FluxOpportuniteServiceGwtAsync {

    /**
     * Récupère la liste des quotas d'une ressource.
     * @param idRessource l'identifiant de la ressource.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void getListeQuotasRessource(Long idRessource, AsyncCallback<List<QuotaModel>> asyncCallback);

    /**
     * Récupère la liste des jours des quotas.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void getListeJours(AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Modifie les quotas de certaines ressources.
     * @param listeIdRessources la liste des resources à modifier.
     * @param listeQuotas les nouvelles valeurs des quotas.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void modifierQuotas(List<Long> listeIdRessources, List<QuotaModel> listeQuotas, AsyncCallback<Void> asyncCallback);

    /**
     * Détermine si une ressource a accés au pot commun.
     * @param idRessource l'identifiant de la ressource.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void hasAccesPotCommun(Long idRessource, AsyncCallback<AccesPotCommunModel> asyncCallback);
}