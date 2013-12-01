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
package com.square.composant.envoi.email.square.client.service;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.composant.envoi.email.square.client.model.DimensionCriteresRechercheModel;
import com.square.composant.envoi.email.square.client.model.EmailModel;
import com.square.composant.envoi.email.square.client.model.ModeleEmailModel;

/**
 * Interface asynchrone des services GWT pour l'envoi d'email du composant.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface ComposantEnvoiMailServiceGWTAsync {

    /**
     * Envoie un email.
     * @param email l'email a envoyé.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void envoyerEmail(EmailModel email, AsyncCallback<Void> asyncCallback);

    /**
     * Supprime une liste de pièces jointes du fichier de stockage des données.
     * @param listePath la liste des paths des fichiers à supprimer.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void supprimerListePiecesJointesRepertoire(List<String> listePath, AsyncCallback<Void> asyncCallback);

    /**
     * Recherche des modèles d'email par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherListeModelesEmails(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des modèles d'emails par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherModelesEmails(DimensionCriteresRechercheModel criteres, AsyncCallback<List<ModeleEmailModel>> asyncCallback);
}
