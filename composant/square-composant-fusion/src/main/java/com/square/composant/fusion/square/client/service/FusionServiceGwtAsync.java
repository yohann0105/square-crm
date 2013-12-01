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
package com.square.composant.fusion.square.client.service;

import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.composant.fusion.square.client.model.ParametresFusionModel;
import com.square.composant.fusion.square.client.model.RechercheDoublonCritereModel;
import com.square.composant.fusion.square.client.model.RechercheDoublonResultatModel;

/**
 * Interface asynchrone pour les services de la fusion.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface FusionServiceGwtAsync {

    /**
     * Recherche les doublons de personnes pour sélection à partir de critères.
     * @param criteres les critères de recherche.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void rechercherDoublonsPourSelection(RemotePagingCriteriasGwt<RechercheDoublonCritereModel> criteres,
        AsyncCallback<RemotePagingResultsGwt<RechercheDoublonResultatModel>> asyncCallback);

    /**
     * Prépare la fusion entre 2 personnes. Crée un DTO contenant les différentes informations et paramètres nécessaires à la fusion (personne source, personne
     * cible, différence entre les propriétés et les bénéficiaires, ...). La personne cible sera, en priorité, celle qui possède un contrat. Si les deux
     * personnes ont un contrat, une exception est renvoyée.
     * @param idPersonne1 l'identifiant de la première personne à fusionner
     * @param idPersonne2 l'identifiant de la seconde personne à fusionner
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void preparerFusion(Long idPersonne1, Long idPersonne2, AsyncCallback<ParametresFusionModel> asyncCallback);

    /**
     * Valide une fusion entre deux personnes en fusionnant les propriétés demandées ainsi que les différents objets rattachés (opportunités, actions, ...).
     * @param parametresFusion les paramètres de la fusion
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void validerFusion(ParametresFusionModel parametresFusion, AsyncCallback<Object> asyncCallback);
}
