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
package com.square.synchro.app.noyau.dao.interfaces;

import com.square.synchro.app.noyau.modele.SynchroAppTableCrsp;

/**
 * Dao pour la gestin de table de correspondance.
 * @author sgoumard (Scub). - SCUB
 */
public interface SynchroAppTableCrspDao {
    /**
     * Permet de sauvegarder une nouvelle correspondance.
     * @param crsp la correspondance à sauvegarder.
     */
    void save(SynchroAppTableCrsp crsp);

    /**
     *Rechercher une correspondance permet de rechercher une correspondance . entre deux application avec un identiiant .
     * @param identifiantObjet identifaint de l'objet .
     * @param identifiantApp identifaint de l'application.
     * @param identifiantAppCrsp identifiant de l'application correspondante.
     * @param typeObjet identifaint du type d'objet.
     * @return l'identifiant d'objet correspondant null si aucune occurence.
     */
    String rechercherCrsp(String identifiantObjet, String identifiantApp, String identifiantAppCrsp, String typeObjet);
}
