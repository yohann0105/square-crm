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

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto du modèle d'e-mail.
 * @author Clément Lavaud - SCUB
 */
public class ModeleEmailDto extends IdentifiantLibelleDto {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -3592040196261038470L;

	/**
	 * L'objet du mail.
	 */
	private String objet;

	/**
	 * Le corps du mail.
	 */
	private String corps;

    /**
     * Renvoi la valeur de objet.
     * @return objet
     */
    public String getObjet() {
        return objet;
    }

    /**
     * Modifie objet.
     * @param objet la nouvelle valeur de objet
     */
    public void setObjet(String objet) {
        this.objet = objet;
    }

    /**
     * Renvoi la valeur de corps.
     * @return corps
     */
    public String getCorps() {
        return corps;
    }

    /**
     * Modifie corps.
     * @param corps la nouvelle valeur de corps
     */
    public void setCorps(String corps) {
        this.corps = corps;
    }
}
