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
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.emails.square.client.model.DestinataireRechercheResultatsModel;
import com.square.composant.emails.square.client.model.EmailModel;
import com.square.composant.emails.square.client.model.GroupeEmailModel;
import com.square.composant.emails.square.client.model.RechercheEmailRequeteModel;
import com.square.composant.emails.square.client.model.RechercheEmailResultatModel;
import com.square.composant.emails.square.client.model.UtilisateurRechercheQueryModel;

/**
 * Interfaces des services pour les emails.
 * @author nnadeau - SCUB
 */
@RemoteServiceRelativePath(value = "handler/emailsService")
public interface EmailServiceGWT extends RemoteService {

    /**
     * Récupère les mails correspondants aux critères de recherche.
     * @param criterias les critères de recherche
     * @return résultats de la recherche
     */
    RemotePagingResultsGwt<RechercheEmailResultatModel> rechercherEmailsParCriteres(RechercheEmailRequeteModel criterias) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère la liste des services accessibles pour l'utilisateur connecté.
     * @return la liste des services
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    List<IdentifiantLibelleGwt> getFiltresServices() throws GwtRunTimeExceptionGwt;

    /**
     * Récupère la liste des utilisateurs accessible pour l'utilisateur connecté et le service demandé.
     * @param idService l'identifiant du service
     * @return la liste des utilisateurs
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    List<IdentifiantLibelleGwt> getFiltresUtilisateurs(Long idService) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère le groupe d'emails d'un email.
     * @param idEmail l'identifiant de l'email.
     * @return le groupe d'email.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    GroupeEmailModel getGroupeEmailParEmail(Long idEmail) throws GwtRunTimeExceptionGwt;

    /**
     * Change l'état d'un email.
     * @param idEmail id de l'email
     * @param idEtat l'id de l'état
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    void changerEtatEmail(Long idEmail, Long idEtat) throws GwtRunTimeExceptionGwt;

    /**
     * Change l'état de tous les emails d'un groupe.
     * @param idGroupeEmail id de l'email
     * @param idEtat l'id de l'état
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    void changerEtatEmailsByGroupeEmail(Long idGroupeEmail, Long idEtat) throws GwtRunTimeExceptionGwt;

    /**
     * Verrouille ou déverrouille tous les emails d'un groupe.
     * @param idGroupeEmail id de l'email
     * @param aVerrouiller boolean pour verrouiller ou deverrouiller le mail
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    void verrouillerEmailsByGroupeEmail(Long idGroupeEmail, Boolean aVerrouiller) throws GwtRunTimeExceptionGwt;

    /**
     * Envoie un email.
     * @param email l'email
     * @return l'email
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    EmailModel envoyerEmail(EmailModel email) throws GwtRunTimeExceptionGwt;

    /**
     * Envoie un email et archive l'email.
     * @param email l'email
     * @param idGroupeEmail id du groupe de l'email
     * @return l'email
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    EmailModel envoyerEmailEtArchiver(EmailModel email, Long idGroupeEmail) throws GwtRunTimeExceptionGwt;

    /**
     * Transfert une liste de groupe d'email à un utilisateur.
     * @param listeGroupes la liste des groupes à transférer.
     * @param idUtilisateurDestination le destinataire.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    void transfererGroupe(List<Long> listeGroupes, Long idUtilisateurDestination) throws GwtRunTimeExceptionGwt;

    /**
     * Retourne la liste des utilisateurs correspondant aux critères demandés.
     * @param query les critères de recherche
     * @throws GwtRunTimeExceptionGwt erreur authentifiée
     * @return l'objet contenant la liste des résultats
     */
    DestinataireRechercheResultatsModel rechercherEmailsByNomOrEmail(UtilisateurRechercheQueryModel query) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère le nombre total de mails en base.
     * @return le nombre total de mails en base
     * @throws GwtRunTimeExceptionGwt erreur authentifiée
     */
    Integer getNombreTotalEmails() throws GwtRunTimeExceptionGwt;

    /**
     * Compte le nombre d'emails échangés entre la Smatis et un adhérent spécifié en paramètre.
     * @param numeroAdherent le numéro de l'adhérent
     * @return le nombre d'emails.
     * @throws GwtRunTimeExceptionGwt exception levée par GWT à l'exécution.
     */
    Integer getNombreEmailsAdherent(String numeroAdherent) throws GwtRunTimeExceptionGwt;
}
