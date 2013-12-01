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
package com.square.composant.espace.client.square.client.model.espace.adherent;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO encapsulant les informations des options de l'espace adhérent.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfosOptionsAdherentModel implements IsSerializable {

    /**
     * Date de modification des informations de l'adhérent.
     */
    private String dateModificationOptions;

    /**
     * Options de l'adhérent.
     */
    private List<OptionAdherentModel> listeOptions;

    /**
     * Get the dateModificationOptions value.
     * @return the dateModificationOptions
     */
    public String getDateModificationOptions() {
        return dateModificationOptions;
    }

    /**
     * Set the dateModificationOptions value.
     * @param dateModificationOptions the dateModificationOptions to set
     */
    public void setDateModificationOptions(String dateModificationOptions) {
        this.dateModificationOptions = dateModificationOptions;
    }

    /**
     * Get the listeOptions value.
     * @return the listeOptions
     */
    public List<OptionAdherentModel> getListeOptions() {
        return listeOptions;
    }

    /**
     * Set the listeOptions value.
     * @param listeOptions the listeOptions to set
     */
    public void setListeOptions(List<OptionAdherentModel> listeOptions) {
        this.listeOptions = listeOptions;
    }
}
