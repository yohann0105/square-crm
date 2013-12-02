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
package com.square.document.core.dto;

import java.io.Serializable;

/**
 * DTO contenant les infos de retour d'ajout d'un document.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB - SCUB
 */
public class RetourAjoutDocumentDto implements Serializable {

	/** Identificateur de sérialisation. */
	private static final long serialVersionUID = -2485086896557464990L;

	/** Identifiant du document. */
	private String identifiantDocument;

	/**
	 * Récupère la valeur identifiantDocument.
	 * @return the identifiantDocument
	 */
	public String getIdentifiantDocument() {
		return identifiantDocument;
	}

	/**
	 * Définit la valeur de identifiantDocument.
	 * @param identifiantDocument the identifiantDocument to set
	 */
	public void setIdentifiantDocument(String identifiantDocument) {
		this.identifiantDocument = identifiantDocument;
	}
}
