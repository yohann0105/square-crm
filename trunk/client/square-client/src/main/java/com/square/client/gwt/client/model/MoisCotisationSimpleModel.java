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

public class MoisCotisationSimpleModel implements IsSerializable {

    /**
     * Mois de cotisation.
     */
    private Integer mois;

    /**
     * List de cotisations pour le mois.
     */
    private List<CotisationSimpleModel> listCotisation;

    /**
     * Renvoie le mois.
     * @return mois
     */
    public Integer getMois() {
        return mois;
    }

    /**
     * Modifie le mois.
     * @param mois dans mois
     */
    public void setMois(Integer mois) {
        this.mois = mois;
    }

    /**
     * Renvoie la liste des cotisations par mois.
     * @return liste de cotisation par mois
     */
    public List<CotisationSimpleModel> getListCotisation() {
        return listCotisation;
    }

    /**
     * Modifie la liste de cotisations par mois.
     * @param listCotisation dans listCotisation
     */
    public void setListCotisation(List<CotisationSimpleModel> listCotisation) {
        this.listCotisation = listCotisation;
    }

}
