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
import java.util.Calendar;

/**
 * Classe de Dto contenant des informations sur le message.
 * @author mohamed - SCUB
 *
 */
public class MessageCriteresDto  implements java.io.Serializable {

	/**
	 * Serial Verion UID.
	 */
	private static final long serialVersionUID = 803401705885195876L;
	/**
	 * le User Id de message.
	 */
	private Long idUtilisateur;
	/**
	 * la date de publication de message.
	 */
	private Calendar datePublication;
	/**
	 * inclure message acquitté ou non(inclure=true,ne pas inclure=false).
	 */
	private Boolean inclureAquites;

	/**setter de idUtilisateur.
	 * @param idUtilisateur the idUtilisateur to set.
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
	 * @param datePublication the datePublication to set
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
	/**setter de inclureAquites.
	 * @param inclureAquites the inclureAquites to set
	 */
	public void setInclureAquites(Boolean inclureAquites) {
		this.inclureAquites = inclureAquites;
	}
	/**getter de inclureAquites.
	 * @return the inclureAquites
	 */
	public Boolean getInclureAquites() {
		return inclureAquites;
	}

}
