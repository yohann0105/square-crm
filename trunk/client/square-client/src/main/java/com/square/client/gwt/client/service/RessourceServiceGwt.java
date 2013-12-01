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
package com.square.client.gwt.client.service;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.RessourceCriteresRechercheModel;
import com.square.client.gwt.client.model.RessourceModel;

/**
 * Interface synchrone des services GWT pour le service des ressources.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/ressourceService")
public interface RessourceServiceGwt extends RemoteService {

    /**
     * Récupère les informations de la ressource actuellement connectée.
     * @return les informations de la ressource connectée.
     * @throws GwtRunTimeExceptionGwt Exception levée par GWT à l'exécution.
     */
    DimensionRessourceModel getRessourceConnectee() throws GwtRunTimeExceptionGwt;

    /**
     * Recherche une ressource par identifiant.
     * @param id l'identifiant de la ressource à rechercher
     * @return la ressource correspondant
     */
    RessourceModel rechercherRessourceParId(Long id) throws GwtRunTimeExceptionGwt;

    /**
     * Récupére la liste des ressources selon des critères.
     * @param criteres les critères de recherche avec les informations de pagination et de tri
     * @return les ressources correspondantes
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    RemotePagingResultsGwt<RessourceModel> rechercherRessourceFullTextParCriteres(RemotePagingCriteriasGwt<RessourceCriteresRechercheModel> criteres)
        throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les identifiants des ressources correspondant aux critères de recherche.
     * @param criteres les critères de recherche.
     * @return la liste des identifiants des ressources correspondant aux critères.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    List<Long> rechercherIdsRessourcesFullTextParCriteres(RessourceCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;
}
