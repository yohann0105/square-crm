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

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.client.gwt.client.model.CampagneCriteresRechercheLibelleModel;
import com.square.client.gwt.client.model.CampagneCriteresRechercheModel;
import com.square.client.gwt.client.model.CampagneModel;
import com.square.client.gwt.client.model.CampagneRechercheModel;

/**
 * Interface synchrone des services GWT pour le service des campagnes.
 * @author cblanchard - SCUB
 */
@RemoteServiceRelativePath(value = "handler/campagneService")
public interface CampagneServiceGwt extends RemoteService {

    /**
     * Recherche les campagnes par critères.
     * @param criteres les critères de recherche
     * @return les campagnes correspondant
     */
    RemotePagingResultsGwt<CampagneRechercheModel> rechercherCampagnesParCriteres(RemotePagingCriteriasGwt<CampagneCriteresRechercheModel> criteres)
        throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les campagnes par critères.
     * @param criteres les critères de recherche
     * @return les campagnes correspondant
     * @throws GwtRunTimeExceptionGwt
     */
    List<IdentifiantLibelleGwt> rechercherIdLibelleCampagnesParCriteres(CampagneCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les campagnes par libelle.
     * @param criteres les critères de recherche
     * @return les informations de la campagne correspondant
     */
    List<IdentifiantLibelleGwt> rechercherCampagnesParLibelle(CampagneCriteresRechercheLibelleModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Creer une campagne.
     * @param campagneModel la campagne à créer
     * @return la campagne crée
     */
    CampagneModel creerCampagne(CampagneModel campagneModel) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche une campagne par identifiant.
     * @param id l'identifiant de la campagne à rechercher
     * @return la campagne correspondant
     */
    CampagneModel rechercherCampagnesParId(Long id) throws GwtRunTimeExceptionGwt;

    /**
     * Modification d'une campagne.
     * @param campagneModel la campagne contenant les nouvelles informations
     * @return la campagne modifiée
     */
    CampagneModel modifierCampagne(CampagneModel campagneModel) throws GwtRunTimeExceptionGwt;
}
