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
package com.square.adherent.noyau.service.interfaces;

import com.square.adherent.noyau.dto.adherent.connexion.IdentifiantsConnexionDto;
import com.square.adherent.noyau.dto.adherent.connexion.InformationConnexionSimpleDto;
import com.square.adherent.noyau.dto.espace.client.EspaceClientInternetDto;

/**
 * Interface des services liés à la connexion.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface EspaceClientInternetService {

    /**
     * Permet de s'authentifier pour l'accès à l'espace client du site Web.
     * @param identificationParamsDto le DTO contenant les identifiants de connexion
     * @return l'espace client de la personne authentifiée
     */
    EspaceClientInternetDto identificationEspaceClient(IdentifiantsConnexionDto identificationParamsDto);

    /**
     * Récupère l'espace client d'une personne.
     * @param uidPersonne identifiant de la personne
     * @return l'espace client de la personne.
     */
    EspaceClientInternetDto getEspaceClientInternet(Long uidPersonne);

    /**
     * Récupère les informations de connexion simples (email et mot de passe) d'un client.
     * @param numClient le numéro de client.
     * @return les informations de connexion.
     */
    InformationConnexionSimpleDto getInfoConnexionSimpleByNumClient(String numClient);

    /**
     * Créer l'espace client pour la personne dont l'identifiant est passé en paramètre.
     * @param espaceClientDto l'espace client à créer
     * @return l'espace client créé
     */
    EspaceClientInternetDto creerEspaceClient(EspaceClientInternetDto espaceClientDto);

    /**
     * Met à jour l'espace client d'une personne.
     * @param espaceClientMaj l'espace client à mettre à jour
     * @return l'espace client mis à jour
     */
    EspaceClientInternetDto majEspaceClient(EspaceClientInternetDto espaceClientMaj);

    /**
     * Vérifie que le login de connexion passé en paramètre est disponible.
     * @param login le login à vérifier.
     * @param idPersonne l'identifiant de la personne en cours.
     * @return true si le login est disponible, false sinon.
     */
    boolean verifierLoginDisponible(String login, Long idPersonne);

    /**
     * Fusionne les espaces client des personnes.
     * @param idPersonneSource l'identifiant de la personne source
     * @param idPersonneCible l'identifiant de la personne cible
     */
    void fusionnerEspaceClient(Long idPersonneSource, Long idPersonneCible);

    /**
     * Permet d'envoyer à une personne son mot de passe permettant l'accès à l'espace client.
     * @param login l'identifiant de connexion de la personne qui souhaite retrouver son mot de passe
     * @param envoyerParMail spécifie si la personne souhaite recevoir le mot de passe par email
     * @param envoyerParSms spécifie si la personne souhaite recevoir le mot de passe par sms
     */
    void envoyerMotDePassePerdu(String login, boolean envoyerParMail, boolean envoyerParSms);

    /**
     * Permet de désactiver l'espace client d'une personne.
     * @param uidPersonne l'id de la personne
     */
    void desactiverEspaceClient(Long uidPersonne);
}
