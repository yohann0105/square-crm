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
package com.square.client.gwt.client.model;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model contenant les informations sur la cotisation.
 * @author Farh ZARROUK (farh.zarrouk@gmail.com) - SCUB
 */
public class CotisationRechercheModel implements IsSerializable {

    /**
     * Année de cotisation.
     */
    private Integer annee;

    /**
     * Cotisation par mois.
     */
    private List<MoisCotisationSimpleModel> listMoisCotisation;

    /**
     * Mode de réglement.
     */
    private String modeReglement;

    /**
     * Renvoie l'année de la recherche.
     * @return année
     */
    public Integer getAnnee() {
        return annee;
    }

    /**
     * Modifie l'année de la recherche.
     * @param annee dans annee
     */
    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    /**
     * Renvoie la liste des mois de cotisation.
     * @return listMoisCotisation
     */
    public List<MoisCotisationSimpleModel> getListMoisCotisation() {
        return listMoisCotisation;
    }

    /**
     * Modifie la liste des mois de cotisation.
     * @param listMoisCotisation dans listMoisCotisation
     */
    public void setListMoisCotisation(List<MoisCotisationSimpleModel> listMoisCotisation) {
        this.listMoisCotisation = listMoisCotisation;
    }

    /**
     * Renvoie le mode de réglement.
     * @return modeReglement
     */
    public String getModeReglement() {
        return modeReglement;
    }

    /**
     * Modifie le mode de réglement.
     * @param modeReglement
     */
    public void setModeReglement(String modeReglement) {
        this.modeReglement = modeReglement;
    }

}
