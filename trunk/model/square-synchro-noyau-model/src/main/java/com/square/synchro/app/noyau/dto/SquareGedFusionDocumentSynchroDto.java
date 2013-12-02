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
package com.square.synchro.app.noyau.dto;

/**
 * DTO permettant la fusion de documents.
 * @author Clément Lavaud - SCUB
 */
public class SquareGedFusionDocumentSynchroDto extends DefaultMessageSynchroAppDto {

    /** Identificateur de sérialisation. */
	private static final long serialVersionUID = -2652385263665985185L;

	/** Numéro client de la personne source de la fusion. */
    private String numClientPersonneSource;

    /** Numéro client de la personne cible de la fusion. */
    private String numClientPersonneCible;

    /** Login de l'utilisateur. */
    private String loginUtilisateur;

    /**
     * Get the numClientPersonneSource value.
     * @return the numClientPersonneSource
     */
    public String getNumClientPersonneSource() {
		return numClientPersonneSource;
	}

    /**
     * Set the numClientPersonneSource value.
     * @param numClientPersonneSource the numClientPersonneSource to set
     */
    public void setNumClientPersonneSource(String numClientPersonneSource) {
		this.numClientPersonneSource = numClientPersonneSource;
	}

    /**
     * Get the numClientPersonneCible value.
     * @return the numClientPersonneCible
     */
    public String getNumClientPersonneCible() {
		return numClientPersonneCible;
	}

    /**
     * Set the numClientPersonneCible value.
     * @param numClientPersonneCible the numClientPersonneCible to set
     */
    public void setNumClientPersonneCible(String numClientPersonneCible) {
		this.numClientPersonneCible = numClientPersonneCible;
	}

    /**
     * Get the loginUtilisateur value.
     * @return the loginUtilisateur
     */
    public String getLoginUtilisateur() {
		return loginUtilisateur;
	}

    /**
     * Set the loginUtilisateur value.
     * @param loginUtilisateur the loginUtilisateur to set
     */
    public void setLoginUtilisateur(String loginUtilisateur) {
		this.loginUtilisateur = loginUtilisateur;
	}
}
