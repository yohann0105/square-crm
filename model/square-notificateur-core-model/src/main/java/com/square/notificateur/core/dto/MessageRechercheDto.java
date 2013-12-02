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
package com.square.notificateur.core.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * classe de dtos de recherches MessageRechercheDto.
 * @author mohamed - SCUB
 *
 */
public class MessageRechercheDto implements Serializable {

	/**
	 * serial version UId.
	 */
	private static final long serialVersionUID = -4574838192757252513L;
	/**
	 * Id du message.
	 */
	private Long idMessage;

	/**
	 * Id de l'utilisateur.
	 */
	private Long idUser;

	/**
	 * Date de publication.
	 */
	private Date datePublication;

	/**
	 * Date de derniére livraison.
	 */
	private Date dateLastPublication;

	/**
	 * Nombre de livraison de message.
	 */
	private Integer nombreLivraison;

	/**
	 * Date de l'accusé de réception.
	 */
	private Date dateReception;

	/**
	 *Texte du message.
	 */
	private String texte;

	/**setter de l'id.
	 * @param id the id to set
	 */
	public void setIdMessage(Long id) {
		this.idMessage = id;
	}

	/**getter de IdMessage.
	 * @return the idMessage
	 */
	public Long getIdMessage() {
		return idMessage;
	}

	/**setter de idUser.
	 * @param idUser
	 *            the idUser to set
	 */
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	/**getter de idUser.
	 * @return the idUser
	 */
	public Long getIdUser() {
		return idUser;
	}

	/**setter de datePublication.
	 * @param datePublication
	 *            the datePublication to set
	 */
	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	/**getter de datePublication.
	 * @return the datePublication
	 */
	public Date getDatePublication() {
		return datePublication;
	}

	/**setter de dateLastPublication.
	 * @param dateLastPublication
	 *            the dateLastPublication to set
	 */
	public void setDateLastPublication(Date dateLastPublication) {
		this.dateLastPublication = dateLastPublication;
	}

	/**getter de dateLastPublication.
	 * @return the dateLastPublication
	 */
	public Date getDateLastPublication() {
		return dateLastPublication;
	}

	/**setter de nombreLivraison.
	 * @param nombreLivraison
	 *            the nombreLivraison to set
	 */
	public void setNombreLivraison(Integer nombreLivraison) {
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
	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
	}

	/**getter de dateReception.
	 * @return the dateReception
	 */
	public Date getDateReception() {
		return dateReception;
	}

	/**setter de texte.
	 * @param texte the texte to set
	 */
	public void setTexte(String texte) {
		this.texte = texte;
	}

	/** getter de texte.
	 * @return the texte
	 */
	public String getTexte() {
		return texte;
	}

}
