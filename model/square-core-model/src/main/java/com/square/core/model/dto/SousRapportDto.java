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

/**
 * Un objet contenant les éléments du rapport d'erreur.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class SousRapportDto implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 304662744946805373L;

	/**
	 * La propriété à vérifier.
	 */
	private String attribut;

	/**
	 * Le message à afficher.
	 */
	private String message;

	/**
	 * Le booléen qui indique l'erreur.
	 * true: erreur, alors exception levée.
	 * false: pas d'erreur.
	 */
	private Boolean erreur;

	/**
	 * Constructeur sousrapport.
	 * @param attribut la propriété à vérifier
	 * @param message le message à afficher
	 * @param erreur la nature de l'erreur.
	 */
	public SousRapportDto(String attribut, String message, Boolean erreur) {
		this.attribut = attribut;
		this.message = message;
		this.erreur = erreur;
	}
	/**
	 * Retourne la valeur de attribut.
	 * @return the attribut
	 */
	public String getAttribut() {
		return attribut;
	}

	/**
	 * Retourne la valeur de message.
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Retourne la valeur de erreur.
	 * @return the erreur
	 */
	public Boolean getErreur() {
		return erreur;
	}

}
