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
package com.square.adherent.noyau.dao.interfaces;

import java.util.List;

import com.square.adherent.noyau.dto.adherent.connexion.IdentifiantsConnexionDto;
import com.square.adherent.noyau.model.data.espace.client.EspaceClientInternet;

/**
 * Interface d'accès aux données de l'espace client.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public interface EspaceClientInternetDao {

    /**
     * Supprime l'espace client de la base de données.
     * @param espaceClientInternet l'espace client à supprimer
     */
    void deleteEspaceClientInternet(EspaceClientInternet espaceClientInternet);

    /**
     * Sauvegarde l'espace client dans la base de données.
     * @param espaceClientInternet l'espace client à sauvegarder
     */
    void saveEspaceClientInternet(EspaceClientInternet espaceClientInternet);

    /**
     * Récupère les informations de l'espace d'un client à partir de son identifiant.
     * @param uidPersonne identifiant unique Square du client.
     * @return informations de l'espace client
     */
    EspaceClientInternet getEspaceClientInternetClient(Long uidPersonne);

    /**
     * Récupère l'espace client d'une personne en fonction de la nature de l'espace client.
     * @param idPersonne l'identifiant de la personne
     * @param idNature l'identifiant de la nature
     * @return l'espace client
     */
    EspaceClientInternet getEspaceClientInternetByPersonneAndNature(Long idPersonne, Long idNature);

    /**
     * Récupère la liste des espaces client en fonction des identifiants et de la nature.
     * @param identifiants les identifiants des espaces client (login, mot de passe)
     * @param idNature l'identifiant de la nature de l'espace client
     * @param statutEspaceClientInternet null pour ne pas filtrer sur le statut de l'espace client, true pour filtrer les connexions actives false pour filtrer
     *            les connexions inactives
     * @return la liste des connexions demandées
     */
    List<EspaceClientInternet> getListeEspaceClientInternetsByIdentifiantsAndNature(IdentifiantsConnexionDto identifiants, Long idNature,
        Boolean statutEspaceClientInternet);
}
