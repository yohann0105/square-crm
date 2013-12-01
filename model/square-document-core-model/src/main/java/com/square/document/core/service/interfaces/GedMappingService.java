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
package com.square.document.core.service.interfaces;

/**
 * Service mapping.
 * @author jgoncalves - SCUB - SCUB
 */
public interface GedMappingService {

    /**
     * Sens Entrant.
     * @return le sens
     */
    String getSensEntrant();

    /**
     * Sens Sortant.
     * @return le sens
     */
    String getSensSortant();

    /**
     * Référence du noeud pour la catégorie Documents / Bulletin d'adhésion.
     * @return la référence (identifiant)
     */
    String getNodeRefCategorieDocumentsBulletinAdhesion();

    /**
     * Référence du noeud pour la catégorie Documents / Avenant.
     * @return la référence (identifiant)
     */
    String getNodeRefCategorieDocumentsAvenant();

    /**
     * Référence du noeud pour la catégorie Documents / Lettre d'annulation.
     * @return la référence (identifiant)
     */
    String getNodeRefCategorieDocumentsLettreAnnulation();

    /**
     * Récupération du chemin pour le stockage des documents.
     * @return le chemin
     */
    String getCheminRepertoire();
}
