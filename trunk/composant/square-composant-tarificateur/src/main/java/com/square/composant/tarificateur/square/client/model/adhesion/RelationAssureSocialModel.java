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
package com.square.composant.tarificateur.square.client.model.adhesion;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle pour les relations avec les asusrés sociaux.
 * @author mgodbert - SCUB
 *
 */
public class RelationAssureSocialModel implements IsSerializable {

    /** Lien familial tarificateur. */
    private IdentifiantLibelleGwt lienFamilialTarificateur;

    /** Lien familial square. */
    private IdentifiantLibelleGwt lienFamilialSquare;

	/**
	 * Récupère le lienFamilialTarificateur.
	 * @return le lienFamilialTarificateur
	 */
	public IdentifiantLibelleGwt getLienFamilialTarificateur() {
		return lienFamilialTarificateur;
	}

	/**
	 * Définit le lienFamilialTarificateur.
	 * @param lienFamilialTarificateur le nouveau lienFamilialTarificateur
	 */
	public void setLienFamilialTarificateur(IdentifiantLibelleGwt lienFamilialTarificateur) {
		this.lienFamilialTarificateur = lienFamilialTarificateur;
	}

	/**
	 * Récupère le lienFamilialSquare.
	 * @return le lienFamilialSquare
	 */
	public IdentifiantLibelleGwt getLienFamilialSquare() {
		return lienFamilialSquare;
	}

	/**
	 * Définit le lienFamilialSquare.
	 * @param lienFamilialSquare le nouveau lienFamilialSquare
	 */
	public void setLienFamilialSquare(IdentifiantLibelleGwt lienFamilialSquare) {
		this.lienFamilialSquare = lienFamilialSquare;
	}
}
