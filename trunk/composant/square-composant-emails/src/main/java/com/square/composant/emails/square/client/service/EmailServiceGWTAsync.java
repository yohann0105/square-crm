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
package com.square.composant.emails.square.client.service;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.composant.emails.square.client.model.DestinataireRechercheResultatsModel;
import com.square.composant.emails.square.client.model.EmailModel;
import com.square.composant.emails.square.client.model.GroupeEmailModel;
import com.square.composant.emails.square.client.model.RechercheEmailRequeteModel;
import com.square.composant.emails.square.client.model.RechercheEmailResultatModel;
import com.square.composant.emails.square.client.model.UtilisateurRechercheQueryModel;

/**
 * Interface pour les appels asynchrones des services pour les emails.
 * @author nnadeau - SCUB
 */
public interface EmailServiceGWTAsync {

    /**
     * Récupère les mails correspondants aux critères de recherche.
     * @param criterias les critères de recherche
     * @param callback callback
     */
    void rechercherEmailsParCriteres(RechercheEmailRequeteModel criterias, AsyncCallback<RemotePagingResultsGwt<RechercheEmailResultatModel>> callback);

    /**
     * Récupère la liste des services accessibles pour l'utilisateur connecté.
     * @param asyncCallback callback
     */
    void getFiltresServices(AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Récupère la liste des utilisateurs accessible pour l'utilisateur connecté et le service demandé.
     * @param idService l'identifiant du service
     * @param asyncCallback callback
     */
    void getFiltresUtilisateurs(Long idService, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Récupère le groupe d'emails d'un email.
     * @param idEmail l'identifiant de l'email.
     * @param asyncCallback callback
     */
    void getGroupeEmailParEmail(Long idEmail, AsyncCallback<GroupeEmailModel> asyncCallback);

    /**
     * Change l'état d'un email.
     * @param idEmail id de l'email
     * @param idEtat l'id de l'état
     * @param asyncCallback callback
     */
    void changerEtatEmail(Long idEmail, Long idEtat, AsyncCallback<Object> asyncCallback);

    /**
     * Change l'état de tous les emails d'un groupe.
     * @param idGroupeEmail id de l'email
     * @param idEtat l'id de l'état
     * @param asyncCallback callback
     */
    void changerEtatEmailsByGroupeEmail(Long idGroupeEmail, Long idEtat, AsyncCallback<GroupeEmailModel> asyncCallback);

    /**
     * Verrouille ou déverrouille tous les emails d'un groupe.
     * @param idGroupeEmail id de l'email
     * @param aVerrouiller boolean pour verrouiller ou deverrouiller le mail
     * @param asyncCallback callback
     */
    void verrouillerEmailsByGroupeEmail(Long idGroupeEmail, Boolean aVerrouiller, AsyncCallback<GroupeEmailModel> asyncCallback);

    /**
     * Envoie un email.
     * @param email l'email
     * @param asyncCallback callback
     */
    void envoyerEmail(EmailModel email, AsyncCallback<EmailModel> asyncCallback);

    /**
     * Envoie un email et archive l'email.
     * @param email l'email
     * @param idGroupeEmail id du groupe de l'email
     * @param asyncCallback callback
     */
    void envoyerEmailEtArchiver(EmailModel email, Long idGroupeEmail, AsyncCallback<EmailModel> asyncCallback);

    /**
     * Transfert une liste de groupe d'email à un utilisateur.
     * @param listeGroupes la liste des groupes à transférer.
     * @param idUtilisateurDestination le destinataire.
     * @param asyncCallback callback.
     */
    void transfererGroupe(List<Long> listeGroupes, Long idUtilisateurDestination, AsyncCallback<Object> asyncCallback);

    /**
     * Retourne la liste des utilisateurs correspondant aux critères demandés.
     * @param query les critères de recherche
     * @param asyncCallback l'objet de retour asynchrone
     */
    void rechercherEmailsByNomOrEmail(UtilisateurRechercheQueryModel query, AsyncCallback<DestinataireRechercheResultatsModel> asyncCallback);

    /**
     * Récupère le nombre total de mails en base.
     * @param asyncCallback le nombre total de mails en base
     */
    void getNombreTotalEmails(AsyncCallback<Integer> asyncCallback);

    /**
     * Compte le nombre d'emails échangés entre la Smatis et un adhérent spécifié en paramètre.
     * @param numeroAdherent le numéro de l'adhérent
     * @param asyncCallback callback qui encapsule le nombre d'emails.
     */
    void getNombreEmailsAdherent(String numeroAdherent, AsyncCallback<Integer> asyncCallback);
}
