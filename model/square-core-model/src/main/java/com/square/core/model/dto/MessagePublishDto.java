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

import java.util.Calendar;

/**
 * DTO contenant les informations d'un message.
 * @author Ksouri Mohamed Ali - SCUB
 */


public class MessagePublishDto implements java.io.Serializable {

	/**
	 * serial id.
	 */
	private static final long serialVersionUID = -4858553059133037090L;

	/**
	 * Id du message.
	 */
	private Long id;

	/**
	 * Id de l'utilisateur.
	 */
	private Long idUtilisateur;

	/**
	 * Date de publication.
	 */
	private Calendar datePublication;

	/**
	 * Date de derniére livraison.
	 */
	private Calendar dateLastPublication;

	/**
	 * Nombre de livraison de message.
	 */
	private int nombreLivraison;

	/**
	 * Date de l'accusé de réception.
	 */
	private Calendar dateReception;

	/**
	 *Texte du message.
	 */
	private String texte;

	/**
	 *Titre du message.
	 */
	private String titre;

	/**setter de id.
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**getter de id.
	 * @return the idMessage
	 */
	public Long getId() {
		return id;
	}

	/**setter de idUtilisateur.
	 * @param idUtilisateur
	 *            the idUtilisateur to set
	 */
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	/** getter de idUtilisateur.
	 * @return the idUtilisateur
	 */
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	/**setter de datePublication.
	 * @param datePublication
	 *            the datePublication to set
	 */
	public void setDatePublication(Calendar datePublication) {
		this.datePublication = datePublication;
	}

	/**getter de datePublication.
	 * @return the datePublication
	 */
	public Calendar getDatePublication() {
		return datePublication;
	}

	/**setter de dateLastPublication.
	 * @param dateLastPublication
	 *            the dateLastPublication to set
	 */
	public void setDateLastPublication(Calendar dateLastPublication) {
		this.dateLastPublication = dateLastPublication;
	}

	/**getter de dateLastPublication.
	 * @return the dateLastPublication
	 */
	public Calendar getDateLastPublication() {
		return dateLastPublication;
	}

	/**setter de nombreLivraison.
	 * @param nombreLivraison
	 *            the nombreLivraison to set
	 */
	public void setNombreLivraison(int nombreLivraison) {
		this.nombreLivraison = nombreLivraison;
	}

	/**getter de nombreLivraison.
	 * @return the nombreLivraison
	 */
	public int getNombreLivraison() {
		return nombreLivraison;
	}

	/**setter de dateReception.
	 * @param dateReception
	 *            the dateReception to set
	 */
	public void setDateReception(Calendar dateReception) {
		this.dateReception = dateReception;
	}

	/**getter de datePublication.
	 * @return the dateReception
	 */
	public Calendar getDateReception() {
		return dateReception;
	}

	/**setter de texte.
	 * @param texte
	 *            the texte to set
	 */
	public void setTexte(String texte) {
		this.texte = texte;
	}

	/**getter de texte.
	 * @return the texte
	 */
	public String getTexte() {
		return texte;
	}

	/**Set the titre.
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**Get the titre.
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

}
