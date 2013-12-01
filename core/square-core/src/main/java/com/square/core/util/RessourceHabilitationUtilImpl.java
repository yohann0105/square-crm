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

import org.apache.log4j.Logger;
import org.springframework.security.context.SecurityContextHolder;

import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.plugin.HabilitationSquarePlugin;

/**
 * Implémentation de RessourceHabilitationUtil.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class RessourceHabilitationUtilImpl implements RessourceHabilitationUtil {

    /** Logger. */
    private final Logger logger = Logger.getLogger(this.getClass());

    /** Dao sur les ressources. */
    private RessourceDao ressourceDao;

    /** Plugin des habilitations. */
    private HabilitationSquarePlugin pluginHabilitation;

    @Override
    public Ressource getUtilisateurConnecte() {
        String login = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            login = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            // TODO patch temporaire à retirer voir SG
            login = "sgoum";
        }
        logger.debug("login utilisateur connecté = " + login);
        final String eid = pluginHabilitation.getEidByLogin(login);
        logger.debug("eid utilisateur connecté = " + eid);
        final Ressource ressource = ressourceDao.rechercherRessourceParEid(eid);
        return ressource;
    }

    @Override
    public Ressource getResponsableByCodePostal(String codePostal) {
        final String eidResponsable = pluginHabilitation.getEidResponsableByCodePostal(codePostal);
        logger.debug("eid responsable = " + eidResponsable);
        return ressourceDao.rechercherRessourceParEid(eidResponsable);
    }

    @Override
    public boolean hasRessourceConnecteeRole(Long idRole) {
        String login = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            login = SecurityContextHolder.getContext().getAuthentication().getName();
        }
        if (login != null) {
            final boolean isAdminSquare = pluginHabilitation.hasUtilisateurRole(login, pluginHabilitation.getIdentifiantRoleSquareAdmin());
            if (isAdminSquare) {
                return true;
            }
            else {
                return pluginHabilitation.hasUtilisateurRole(login, idRole);
            }
        }
        else {
            return false;
        }
    }

    @Override
    public boolean hasRessourceRole(Long idRessource, Long idRole) {
        final Ressource ressource = ressourceDao.rechercherRessourceParId(idRessource);
        if (ressource != null) {
            final Long idUtilisateur = Long.valueOf(ressource.getIdentifiantExterieur());
            final boolean isAdminSquare = pluginHabilitation.hasUtilisateurByIdRole(idUtilisateur, pluginHabilitation.getIdentifiantRoleSquareAdmin());
            if (isAdminSquare) {
                return true;
            }
            else {
                return pluginHabilitation.hasUtilisateurByIdRole(idUtilisateur, idRole);
            }
        }
        return false;
    }

    @Override
    public Long getIdentifiantRoleGestionnaireOpportunites() {
        return pluginHabilitation.getIdentifiantRoleGestionnaireOpportunites();
    }

    @Override
    public Long getIdentifiantAgenceFrance() {
        return pluginHabilitation.getIdentifiantAgenceFrance();
    }

    /**
     * Modifie la valeur de ressourceDao.
     * @param ressourceDao the ressourceDao to set
     */
    public void setRessourceDao(RessourceDao ressourceDao) {
        this.ressourceDao = ressourceDao;
    }

    /**
     * Modifie la valeur de pluginHabilitation.
     * @param pluginHabilitation the pluginHabilitation to set
     */
    public void setPluginHabilitation(HabilitationSquarePlugin pluginHabilitation) {
        this.pluginHabilitation = pluginHabilitation;
    }

    @Override
    public Long getIdentifiantRoleSquare() {
        return pluginHabilitation.getIdentifiantRoleSquare();
    }
}
