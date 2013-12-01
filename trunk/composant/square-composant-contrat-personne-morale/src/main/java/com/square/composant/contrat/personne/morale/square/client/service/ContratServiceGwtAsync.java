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


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.composant.contrat.personne.morale.square.client.model.ContratPersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.model.InfosContratsPersonneMoraleModel;

/**
 * Interface asynchrone des services GWT pour le service des contrats.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface ContratServiceGwtAsync {

    /**
     * Récupère les informations (synthèse et liste des contrats) des contrats d'une personne morale.
     * @param uidPersonne l'identifiant unique de la personne morale.
     * @param asyncCallback le callback
     */
    void getInfosContratPersonneMorale(Long uidPersonne, AsyncCallback<InfosContratsPersonneMoraleModel> asyncCallback);

    /**
     * Récupère les informations d'un contrat d'une personne morale.
     * @param uidContrat l'identifiant unique du contrat à récupérer.
     * @param asyncCallback le callback
     */
    void getContratPersonneMorale(Long uidContrat, AsyncCallback<ContratPersonneMoraleModel> asyncCallback);

}