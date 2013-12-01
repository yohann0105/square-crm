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
package com.square.price.core.service.interfaces;

import com.square.price.core.dto.CritereDto;
import com.square.price.core.dto.TarifCriteresDto;
import com.square.price.core.dto.TarifReponsesDto;

/**
 * Interface Service Tarif.
 * @author Goumard Stephane.
 */
public interface TarifService {
    /**
     * Récuperer un tarif en fonction des critéres.
     * @param criteres pour la recherche de tarif.
     * @return ReponseDto le tarif qui valide les criteres.
     */
    TarifReponsesDto getTarifParCriteres(final TarifCriteresDto criteres);
//
//    /**
//     * Récuperer une liste de tarifs en fonction des critéres pour un affichage specifique en grille.
//     * @param criteres pour la recherche de tarif.
//     * @return la liste des tarifs qui valide les criteres.
//     */
//    List<TarifReponsesDto> getListeTarifsSpecifiquesParCriteres(final TarifCriteresDto criteres);
//
    /**
     * Recuperer un critere par son identifiant.
     * @param identifiantCritere l'identifiant du critere.
     * @param idApplication identifiant de l'application.
     * @return le critere null si aucune occurence.
     */
    CritereDto getCritereParIdentifiant(final Integer identifiantCritere, final Integer idApplication);
//
//    /**
//     * Récupère la date d'effet minimale de tarification pour une application.
//     * @param idApplication l'identifiant de l'application
//     * @return la date d'effet minimale de tarification pour l'application
//     */
//    Calendar getDateEffetMinimaleByApplication(final Integer idApplication);
//
//    /**
//     * Récupère les barêmes de produits en fonction de critères de recherche.
//     * @param criteres les critères de recherche
//     * @return la liste des barêmes de produit
//     */
//    List<ProduitBaremeDto> getListeProduitsBaremes(ProduitBaremeCriteresDto criteres);
}
