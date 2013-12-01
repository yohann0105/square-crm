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
package com.square.tarificateur.noyau.dao.interfaces;

import java.util.List;

import com.square.tarificateur.noyau.dto.devis.CritereModeleDevisDto;
import com.square.tarificateur.noyau.model.opportunite.ModeleDevis;

/**
 * Interface d'accès aux données pour les modèles de devis.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public interface ModeleDevisDao {

    /**
     * Récupère le modèle de devis correspondant à l'identifiant passé en paramètre.
     * @param idModeleDevis identifiant du modèle de devis à récupérer.
     * @return le modèle de devis.
     */
    ModeleDevis getModeleDevisById(Long idModeleDevis);

    /**
     * Récupère les modèles de devis correspondant aux critères de recherche.
     * @param criteres les critères de recherche.
     * @return la liste des modèles de devis.
     */
    List<ModeleDevis> getListeModelesDevisByCriteres(CritereModeleDevisDto criteres);
}
