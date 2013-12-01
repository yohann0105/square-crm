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
package com.square.composant.tarificateur.square.client.service;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.composant.tarificateur.square.client.model.CaisseSimpleModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresLienFamilialRechercheModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheCaisseModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheRegimeModel;
import com.square.composant.tarificateur.square.client.model.InfosExpediteurEnvoiEmailModel;

/**
 * Interface asynchrone des services GWT pour le service des dimensions.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface DimensionServiceGwtAsync {

    /**
     * Recherche des caisses par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherCaisseParCriteres(DimensionCriteresRechercheCaisseModel criteres, AsyncCallback<List<CaisseSimpleModel>> asyncCallback);

    /**
     * Recherche des regimes par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherRegimeParCriteres(DimensionCriteresRechercheRegimeModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recupère les informations de l'expéditeur pour l'envoi d'un email.
     * @param eidResponsable eidResponsable de l'opp
     * @param asyncCallback l'objet du retour asynchrone.
     */
    void getInfosExpediteurEmail(Long eidResponsable, AsyncCallback<InfosExpediteurEnvoiEmailModel> asyncCallback);

    /**
     * Recherche des civilités par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherCiviliteParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des relations par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherLiensFamiliauxParCriteres(DimensionCriteresLienFamilialRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);
}
