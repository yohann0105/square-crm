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
package com.square.core.model.plugin;

/**
 * Interface qui définit les habilitations.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface HabilitationSquarePlugin {
    /**
     * Méthode qui retourne l'identifiant d'un utilisateur.
     * @param login le login de l'utilisateur connecté.
     * @return l'identifiant de l'utilisateur
     */
    String getEidByLogin(String login);

    /**
     * Récupère aléatoirement un responsable en fonction du code postal.
     * @param codePostal le code postal
     * @return le responsable sélectionné
     */
    String getEidResponsableByCodePostal(String codePostal);

    /**
     * Détermine si un utilisateur a un certains rôle.
     * @param login le login de l'utilisateur.
     * @param idRole l'identifiant du rôle.
     * @return true si l'utilisateur a le rôle, false si non.
     */
    boolean hasUtilisateurRole(String login, Long idRole);

    /**
     * Récupère l'identifiant du rôle administrateur de square.
     * @return l'identifiant du rôle.
     */
    Long getIdentifiantRoleSquareAdmin();

    /**
     * Récupère l'identifiant du rôle de gestionnaire des opportunités.
     * @return l'identifiant du rôle de gestionnaire des opportunités.
     */
    Long getIdentifiantRoleGestionnaireOpportunites();

    /**
     * Récupère l'identifiant du rôle square.
     * @return l'identifiant du rôle square.
     */
    Long getIdentifiantRoleSquare();

    /**
     * Récupère l'identifiant de l'agence France.
     * @return l'identifiant de l'agence France.
     */
    Long getIdentifiantAgenceFrance();

    /**
     * Détermine si un utilisateur a un certains rôle.
     * @param idUtilisateur l'identifiant de l'utilisateur.
     * @param idRole l'identifiant du rôle.
     * @return true si l'utilisateur a le rôle, false si non.
     */
    boolean hasUtilisateurByIdRole(Long idUtilisateur, Long idRole);

    /**
     * Récupère une agence en fonction du code postal.
     * @param codePostal le code postal.
     * @return l'agence correspondante.
     */
    String getEidAgenceByCodePostal(String codePostal);
}
