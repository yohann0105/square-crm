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
package com.square.notificateur.core.model;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Entité persistante modélisant les message du gestionnaire de messages.
 * 
 * @author KsouriMohamed Ali - SCUB
 */
@Entity
@Table(name = "MESSAGES")
public class Message extends BaseModel {

	/**
	 * id de la sérialisation.
	 */
	private static final long serialVersionUID = 1946857396573772177L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((dateLastPublication == null) ? 0 : dateLastPublication
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Message)) {
			return false;
		}
		return true;
	}

	/**
	 * Id de l'utilisateur.
	 */
	@Column(name = "IDUSER", nullable = false)
	private Long idUtilisateur;

	/**
	 * Date de publication.
	 */
	@Column(name = "DATEPUBLICATION")
	private Calendar datePublication;

	/**
	 * Calendar de derniére livraison.
	 */
	@Column(name = "DATELASTPUBLICATION")
	private Calendar dateLastPublication;

	/**
	 * Nombre de livraison de message.
	 */
	@Column(name = "NOMBRELIVRAISON")
	private Integer nombreLivraison = 0;

	/**
	 * Calendar de l'accusé de réception.
	 */
	@Column(name = "DATERECEPTION")
	private Calendar dateReception;

	/**
	 *Texte du message.
	 */
	@Column(name = "TEXTE")
	@Type(type = "text")
	private String texte;

	/**
	 *Titre du message.
	 */
	@Column(name = "TITRE")
	@Type(type = "text")
	private String titre;

	/**Get idUtilisateur.
	 * @return the idUtilisateur
	 */
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	/**Set idUtilisateur.
	 * @param idUtilisateur the idUtilisateur to set
	 */
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	/**Get datePublication.
	 * @return the datePublication
	 */
	public Calendar getDatePublication() {
		return datePublication;
	}

	/**Set datePublication.
	 * @param datePublication the datePublication to set
	 */
	public void setDatePublication(Calendar datePublication) {
		this.datePublication = datePublication;
	}

	/**Get dateLastPublication.
	 * @return the dateLastPublication
	 */
	public Calendar getDateLastPublication() {
		return dateLastPublication;
	}

	/**Set dateLastPublication.
	 * @param dateLastPublication the dateLastPublication to set
	 */
	public void setDateLastPublication(Calendar dateLastPublication) {
		this.dateLastPublication = dateLastPublication;
	}

	/**Get nombreLivraison.
	 * @return the nombreLivraison
	 */
	public Integer getNombreLivraison() {
		return nombreLivraison;
	}

	/**Set nombreLivraison.
	 * @param nombreLivraison the nombreLivraison to set
	 */
	public void setNombreLivraison(Integer nombreLivraison) {
		this.nombreLivraison = nombreLivraison;
	}

	/**Get dateReception.
	 * @return the dateReception
	 */
	public Calendar getDateReception() {
		return dateReception;
	}

	/**Set dateReception.
	 * @param dateReception the dateReception to set
	 */
	public void setDateReception(Calendar dateReception) {
		this.dateReception = dateReception;
	}

	/**Get texte.
	 * @return the texte
	 */
	public String getTexte() {
		return texte;
	}

	/**Set texte.
	 * @param texte the texte to set
	 */
	public void setTexte(String texte) {
		this.texte = texte;
	}

	/**Get titre.
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**Set titre.
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
}
