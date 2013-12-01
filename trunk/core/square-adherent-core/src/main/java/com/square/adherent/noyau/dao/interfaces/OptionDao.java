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

import com.square.adherent.noyau.model.data.espace.client.Option;

/**
 * Interface d'accès aux données des options des adhérents Smatis.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public interface OptionDao {

    /**
     * Récupère les options de l'adhérent.
     * @param uidPersonne identifiant unique de l'adhérent.
     * @return les options de l'adhérent.
     */
    List<Option> getOptionsAdherent(Long uidPersonne);

    /**
     * Récupère l'option correspondant à l'identifiant passé en paramètre.
     * @param id identifiant de l'option.
     * @return l'option trouvée. null si aucune option trouvée.
     */
    Option getOptionById(Long id);

    /**
     * Sauvegarde l'option dans la base de données.
     * @param option l'option à sauvegarder.
     */
    void saveOption(Option option);

    /**
     * Indique si une personne possède une option spécifiée.
     * @param idPersonne l'identifiant de la personne.
     * @param idTypeOption l'identifiant du type de l'option
     * @return true si le compte possède l'option, sinon false.
     */
    boolean isPersonnePossedeOption(Long idPersonne, Long idTypeOption);

    /**
     * Récupère une option pour une personne en fonction de son type.
     * @param idPersonne l'identifiant de la personne.
     * @param idTypeOption l'identifiant du type de l'option
     * @return l'option.
     */
    Option getOptionByPersonneAndType(Long idPersonne, Long idTypeOption);

    /**
     * Récupère la liste des options souscrites par une personne.
     * @param uidPersonne identifiant unique de l'adhérent.
     * @return la liste des options
     */
    List<Option> getListeOptionsSouscritesByPersonne(Long uidPersonne);

    /**
     * Récupère la liste des identifiants des adhérents ayant souscrit à l'option spécifiée.
     * @param idOption l'identifiant de l'option.
     * @return la liste des adhérentsOnum.
     */
    List<Long> getListeAdherentsByIdOptionSouscrite(Long idOption);

}
