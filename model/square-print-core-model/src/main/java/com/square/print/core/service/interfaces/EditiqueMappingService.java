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
package com.square.print.core.service.interfaces;

import java.util.List;
import java.util.Map;

import com.square.print.core.dto.ModeleDevisDto;


/**
 * Service pour l'accés au constantes de l'éditique.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public interface EditiqueMappingService {

    /**
     * Récupère l'identifiant du modèle de devis "Bulletin d'adhésion".
     * @return l'identifiant
     */
    Long getConstanteIdModeleDevisBulletinAdhesion();

    /**
     * Récupère l'identifiant du modèle de devis "Fiche de transfert".
     * @return l'identifiant
     */
    Long getConstanteIdModeleDevisFicheTransfert();

    /**
     * Récupère l'identifiant du modèle de "la lettre d'annulation".
     * @return l'identifiant.
     */
    Long getConstanteIdModeleLettreAnnulation();

    /**
     * Récupère l'identifiant du modèle de "la lettre de radiation".
     * @return l'identifiant.
     */
    Long getConstanteIdModeleLettreRadiation();

    /**
     * Récupère l'identifiant du modèle de "la lettre de radiation par loi Chatel".
     * @return l'identifiant.
     */
    Long getConstanteIdModeleLettreRadiationLoiChatel();

	/**
	 * Map de la liste des fichiers CNP en téléchargement sur un devis type CNP.
	 * @return la liste des fichiers sous forme de Map < Libelle fichier, Nom du fichier >.
	 */
	Map<String, String> getMapFichiersStatiques();

    /**
     * Récupère la liste des modèles de devis.
     * @return la liste des modèles de devis.
     */
    List<ModeleDevisDto> getListeModelesDevis();

    /**
     * Récupère un modèle de devis par son identifiant.
     * @param idModeleDevis l'identifiant du modèle de devis.
     * @return le modèle de devis.
     */
    ModeleDevisDto getModelDevisById(Long idModeleDevis);
}
