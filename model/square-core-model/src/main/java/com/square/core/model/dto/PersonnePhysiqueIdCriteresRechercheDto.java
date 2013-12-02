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
package com.square.core.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Objet contenant les critères de recherche de l'id de personnes physiques.
 * @author Raphaël MARNIER (raphael.marnier@scub.net)
 */
public class PersonnePhysiqueIdCriteresRechercheDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -6514764959861172718L;

    /** Liste des ids de personnes physiques. */
    List<Long> listeIdsPersonnes;

    /** Liste des ids de natures des personnes. */
    List<Long> listeIdsNaturesPersonnes;

    /**
     * Constructeur par défaut.
     */
    public PersonnePhysiqueIdCriteresRechercheDto() {
    }

    /**
     * Return the value of listeIdsPersonnes.
     * @return the listeIdsPersonnes
     */
    public List<Long> getListeIdsPersonnes() {
        return listeIdsPersonnes;
    }

    /**
     * Modify the value of listeIdsPersonnes.
     * @param listeIdsPersonnes the listeIdsPersonnes to set
     */
    public void setListeIdsPersonnes(List<Long> listeIdsPersonnes) {
        this.listeIdsPersonnes = listeIdsPersonnes;
    }

    /**
     * Return the value of listeIdsNaturesPersonnes.
     * @return the listeIdsNaturesPersonnes
     */
    public List<Long> getListeIdsNaturesPersonnes() {
        return listeIdsNaturesPersonnes;
    }

    /**
     * Modify the value of listeIdsNaturesPersonnes.
     * @param listeIdsNaturesPersonnes the listeIdsNaturesPersonnes to set
     */
    public void setListeIdsNaturesPersonnes(List<Long> listeIdsNaturesPersonnes) {
        this.listeIdsNaturesPersonnes = listeIdsNaturesPersonnes;
    }

}
