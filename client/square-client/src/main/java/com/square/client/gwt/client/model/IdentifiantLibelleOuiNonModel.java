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
/**
 * 
 */
package com.square.client.gwt.client.model;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

/**
 * Identifiant Libelle Dto specifique pour la gestion des listbox oui/non.
 * @author Benoit THOMAS (benoit.thomas@scub.net) - SCUB
 */
public class IdentifiantLibelleOuiNonModel extends IdentifiantLibelleGwt {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -3400235732055168917L;

    /**
     * Libelle Inverse.
     */
    private Boolean flag;

    /**
     * Constructeur par défaut.
     */
    public IdentifiantLibelleOuiNonModel() {
    }

    /**
     * Constructeur d'initialisation.
     * @param bool valeur d'initialisation
     */
    public IdentifiantLibelleOuiNonModel(Boolean bool) {
    	this.flag = bool;
		if (bool == null) {
			this.setIdentifiant(Long.valueOf(0));
			this.setLibelle("");
		} else if (bool.equals(Boolean.TRUE)) {
			this.setIdentifiant(Long.valueOf(1));
			this.setLibelle("Oui");
		} else if (bool.equals(Boolean.FALSE)) {
			this.setIdentifiant(Long.valueOf(2));
			this.setLibelle("Non");
		}
    }

	/**
	 * Renvoie la valeur de flag.
	 * @return flag
	 */
	public Boolean getFlag() {
		return flag;
	}

	/**
	 * Modifie flag.
	 * @param flag la nouvelle valeur de flag
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	/**
	 * Renvoie la liste des propositions pour suggest list Oui/Non.
	 * @return la liste
	 */
	public static List<IdentifiantLibelleOuiNonModel> getListSuggestions() {
		final List<IdentifiantLibelleOuiNonModel> lst = new ArrayList<IdentifiantLibelleOuiNonModel>();
		lst.add(new IdentifiantLibelleOuiNonModel(null));
		lst.add(new IdentifiantLibelleOuiNonModel(Boolean.TRUE));
		lst.add(new IdentifiantLibelleOuiNonModel(Boolean.FALSE));
		return lst;
	}
}
