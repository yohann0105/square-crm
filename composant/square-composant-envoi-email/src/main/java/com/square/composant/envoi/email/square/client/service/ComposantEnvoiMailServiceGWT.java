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
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.envoi.email.square.client.model.DimensionCriteresRechercheModel;
import com.square.composant.envoi.email.square.client.model.EmailModel;
import com.square.composant.envoi.email.square.client.model.ModeleEmailModel;

/**
 * Service d'envoi d'email du composant.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/emailComposantEnvoiService")
public interface ComposantEnvoiMailServiceGWT extends RemoteService {

    /**
     * Envoie un email.
     * @param email l'email a envoyé.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    void envoyerEmail(EmailModel email) throws GwtRunTimeExceptionGwt;

    /**
     * Supprime une liste de pièces jointes du fichier de stockage des données.
     * @param listePath la liste des paths des fichiers à supprimer.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    void supprimerListePiecesJointesRepertoire(List<String> listePath) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des modèles d'emails par critères.
     * @param criteres les critères de recherche
     * @return la liste de modèle
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    List<IdentifiantLibelleGwt> rechercherListeModelesEmails(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des modèles d'emails par critères.
     * @param criteres les critères de recherche
     * @return la liste de modèle
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    List<ModeleEmailModel> rechercherModelesEmails(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;
}
