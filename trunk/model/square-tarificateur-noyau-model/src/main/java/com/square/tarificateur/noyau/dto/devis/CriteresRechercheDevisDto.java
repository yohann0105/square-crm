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
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;
import java.util.List;

/**
 * Criteres de recherche de devis.
 * @author Goumard Stephane (stephane.goumard@scub.net) - SCUB
 *
 */
public class CriteresRechercheDevisDto implements Serializable
{
    /**
     * SVUID.
     */
    private static final long serialVersionUID = 2371990128929762691L;

    /**
     * Identifiant origine.
     */
    private Boolean origineSiteWeb = Boolean.FALSE;

    /**
     * Identifiant finalite.
     */
    private Long idFinalite;

    /**
     * Liste d'identifiant finalite.
     */
    private List<Long> idsFinalite;

    /**
     * Identifiant du prospect.
     */
    private Long idPersonne;

    /**
     * Identifiant du prospect.
     */
    private Long eidPersonne;

    /**
     * Cloturer.
     */
    private Boolean cloturer;

    /**
     * Identifiant du devis.
     */
    private Long idDevis;

    /**
     * Doit on récupérer aussi le contenu des devis ?.
     */
    private boolean recupererContenuDevis = true;

    /**
     * Get the idFinalite value.
     * @return the idFinalite
     */
    public Long getIdFinalite() {
        return idFinalite;
    }

    /**
     * Set the idFinalite value.
     * @param idFinalite the idFinalite to set
     */
    public void setIdFinalite(Long idFinalite) {
        this.idFinalite = idFinalite;
    }

    /**
	 * Recuperer la valeur.
	 * @return the idPersonne
	 */
	public Long getIdPersonne() {
		return idPersonne;
	}

	/**
	 * Fixer la valeur.
	 * @param idPersonne the idPersonne to set
	 */
	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	/**
     * Get the cloturer value.
     * @return the cloturer
     */
    public Boolean getCloturer() {
        return cloturer;
    }

    /**
     * Set the cloturer value.
     * @param cloturer the cloturer to set
     */
    public void setCloturer(Boolean cloturer) {
        this.cloturer = cloturer;
    }

	/**
	 * Recuperer la valeur.
	 * @return the origineSiteWeb
	 */
	public Boolean getOrigineSiteWeb() {
		return origineSiteWeb;
	}

	/**
	 * Fixer la valeur.
	 * @param origineSiteWeb the origineSiteWeb to set
	 */
	public void setOrigineSiteWeb(Boolean origineSiteWeb) {
		this.origineSiteWeb = origineSiteWeb;
	}

	/**
	 * Recuperer la valeur.
	 * @return the eidPersonne
	 */
	public Long getEidPersonne() {
		return eidPersonne;
	}

	/**
	 * Fixer la valeur.
	 * @param eidPersonne the eidPersonne to set
	 */
	public void setEidPersonne(Long eidPersonne) {
		this.eidPersonne = eidPersonne;
	}

	/**
	 * Recuperer la valeur.
	 * @return the idDevis
	 */
	public Long getIdDevis() {
		return idDevis;
	}

	/**
	 * Fixer la valeur.
	 * @param idDevis the idDevis to set
	 */
	public void setIdDevis(Long idDevis) {
		this.idDevis = idDevis;
	}

    /**
     * Récupération de recupererContenuDevis.
     * @return the recupererContenuDevis
     */
    public boolean isRecupererContenuDevis() {
        return recupererContenuDevis;
    }

    /**
     * Définition de recupererContenuDevis.
     * @param recupererContenuDevis the recupererContenuDevis to set
     */
    public void setRecupererContenuDevis(boolean recupererContenuDevis) {
        this.recupererContenuDevis = recupererContenuDevis;
    }

    /**
     * Get the value of idsFinalite.
     * @return the idsFinalite
     */
    public List<Long> getIdsFinalite() {
        return idsFinalite;
    }

    /**
     * Set the value of idsFinalite.
     * @param idsFinalite the idsFinalite to set
     */
    public void setIdsFinalite(List<Long> idsFinalite) {
        this.idsFinalite = idsFinalite;
    }
}