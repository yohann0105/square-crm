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
package com.square.adherent.noyau.dto.adherent;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * DTO encapsulant les informations des options d'un adhérent.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfosOptionsAdherentDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -2474366815496129447L;

    /**
     * Date de modification des informations de l'adhérent.
     */
    private Calendar dateModificationOptions;

    /**
     * Options de l'adhérent.
     */
    private List<OptionAdherentDto> listeOptions;

    /**
     * Get the dateModificationOptions value.
     * @return the dateModificationOptions
     */
    public Calendar getDateModificationOptions() {
        return dateModificationOptions;
    }

    /**
     * Set the dateModificationOptions value.
     * @param dateModificationOptions the dateModificationOptions to set
     */
    public void setDateModificationOptions(Calendar dateModificationOptions) {
        this.dateModificationOptions = dateModificationOptions;
    }

    /**
     * Get the listeOptions value.
     * @return the listeOptions
     */
    public List<OptionAdherentDto> getListeOptions() {
        return listeOptions;
    }

    /**
     * Set the listeOptions value.
     * @param listeOptions the listeOptions to set
     */
    public void setListeOptions(List<OptionAdherentDto> listeOptions) {
        this.listeOptions = listeOptions;
    }

}
