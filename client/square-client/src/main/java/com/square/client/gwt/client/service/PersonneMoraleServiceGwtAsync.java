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

import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.client.gwt.client.model.PersonneMoralCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneMoraleModel;
import com.square.client.gwt.client.model.PersonneMoraleRechercheModel;
import com.square.client.gwt.client.model.PersonneMoraleSimpleModel;

/**
 * Interface asynchrone des services GWT pour le service des personnes morales.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface PersonneMoraleServiceGwtAsync extends HasCompteurServiceGwtAsync {
    /**
     * Recherche full text des personnes morales.
     * @param remotePagingCriteriasGwt les critères de recherches
     * @param asyncCallback callback
     */
    void rechercherPersonneMoraleParCriteres(RemotePagingCriteriasGwt<PersonneMoralCriteresRechercheModel> remotePagingCriteriasGwt,
        AsyncCallback<RemotePagingResultsGwt<PersonneMoraleRechercheModel>> asyncCallback);

    /**
     * Recherche d'une personne morale par son identifiant.
     * @param identifiant l'identifiant de la personne
     * @param asyncCallback callback
     */
    void recherchePersonneMoraleParId(Long identifiant, AsyncCallback<PersonneMoraleModel> asyncCallback);

    /**
     * Recherche d'une personne morale simple par son identifiant.
     * @param identifiant l'identifiant de la personne
     * @param asyncCallback callback
     */
    void recherchepersonneMoraleSimpleParId(Long identifiant, AsyncCallback<PersonneMoraleSimpleModel> asyncCallback);
}
