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
package com.square.core.util;

import com.square.core.model.Ressources.Ressource;

/**
 * Interface permettant de récuperer un utilisateur connecté.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface RessourceHabilitationUtil {

    /**
     * Méthode qui renvoie l'utilisateur connecté.
     * @return l'utilisateur connecté.
     */
    Ressource getUtilisateurConnecte();

    /**
     * Récupère aléatoirement un responsable en fonction du code postal.
     * @param codePostal le code postal
     * @return le responsable sélectionné
     */
    Ressource getResponsableByCodePostal(String codePostal);

    /**
     * Détermine si la ressource connectée a un certains rôle.
     * @param idRole l'identifiant du rôle.
     * @return true si elle le rôle, false si non.
     */
    boolean hasRessourceConnecteeRole(Long idRole);

    /**
     * Détermine si une ressource a un certains rôle.
     * @param idRessource l'identifiant de la ressource.
     * @param idRole l'identifiant du rôle.
     * @return true si elle le rôle, false si non.
     */
    boolean hasRessourceRole(Long idRessource, Long idRole);

    /**
     * Récupère l'identifiant du rôle de gestionnaire des opportunités.
     * @return l'identifiant du rôle de gestionnaire des opportunités.
     */
    Long getIdentifiantRoleGestionnaireOpportunites();

    /**
     * Récupère l'identifiant du rôle comparateur.
     * @return l'identifiant du rôle comparateur.
     */
    Long getIdentifiantRoleSquare();

    /**
     * Récupère l'identifiant de l'agence France.
     * @return l'identifiant de l'agence France.
     */
    Long getIdentifiantAgenceFrance();
}
