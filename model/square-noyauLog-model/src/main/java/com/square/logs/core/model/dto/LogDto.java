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
package com.square.logs.core.model.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * dto de Log.
 * 
 * @author MohamedAli - SCUB
 */
public class LogDto implements Serializable {
	/**
	 * serial id.
	 */
	private static final long serialVersionUID = -2214969601361107259L;

	/**
	 * id de dto.
	 */
	private Long id;

	/**
	 * le nom de l'application.
	 */
	private String application;

	/**
	 * la profondeur d'introspection.
	 */
	private int profondeur;

	/**
	 * la signature de la méthode .
	 */
	private String signature;

	/**
	 * la date de l'appel.
	 */
	private Calendar date;

	/**
	 * trace de l'exception.
	 */
	private String traceException;

	/**
	 * la liste des arguments de l'appel de la méthode.
	 */
	private List<Object> parametres;

	/**
	 * le résultat de la méthode.
	 */
	private Object resultat;

	/**
	 * modifier id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * récupérer l'id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * modifier le nom de l'application.
	 * 
	 * @param application
	 *            the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * récupérer le nom de l'application.
	 * 
	 * @return the application
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * modifier la date.
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * récupérer la date.
	 * 
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * modifier la signature.
	 * 
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * récupérer la signature.
	 * 
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * récupére la trace d'exception.
	 * 
	 * @return the traceException
	 */
	public String getTraceException() {
		return traceException;
	}

	/**
	 * modifie la trace d'exception.
	 * 
	 * @param traceException
	 *            the traceException to set
	 */
	public void setTraceException(String traceException) {
		this.traceException = traceException;
	}

	/**
	 * modifie les paramètres.
	 * 
	 * @param parametres
	 *            the parametres to set
	 */
	public void setParametres(List<Object> parametres) {
		this.parametres = parametres;
	}

	/**
	 * renvoie les paramètres d'entrée.
	 * 
	 * @return the parametres
	 */
	public List<Object> getParametres() {
		return parametres;
	}

	/**
	 * modifie le résultat.
	 * 
	 * @param resultat
	 *            the resultat to set
	 */
	public void setResultat(Object resultat) {
		this.resultat = resultat;
	}

	/**
	 * récupère le resultat.
	 * 
	 * @return the resultat
	 */
	public Object getResultat() {
		return resultat;
	}

	/**
	 * Récupère la profondeur d'introspection.
	 * 
	 * @return la profondeur d'introspection
	 */
	public int getProfondeur() {
		return profondeur;
	}

	/**
	 * Définit la profondeur d'introspection.
	 * 
	 * @param profondeur
	 *            la profondeur d'introspection
	 */
	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}
}
