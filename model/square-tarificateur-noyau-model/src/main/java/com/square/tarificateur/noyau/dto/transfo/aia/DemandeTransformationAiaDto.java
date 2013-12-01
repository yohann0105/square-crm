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
package com.square.tarificateur.noyau.dto.transfo.aia;

import java.io.Serializable;

/**
 * DTO pour la demande de transformation AIA d'un devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class DemandeTransformationAiaDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -2344720413705701617L;

    /** Identifiant du devis. */
    private Long idDevis;

    /** Login de l'utilisateur faisant la demande (utilisateur connecté). */
    private String loginUtilisateurConnecte;

    /** Indicateur de création d'action post traitement. */
    private Boolean createActionSuivi = false;

    /**
     * Active / Désactive le contrôle des informations avant de lancer la transformation AIA.
     * Contrôle activé par défaut
     */
    private boolean controleActif = true;

    /**
     * Getter function.
     * @return the controleActif
     */
    public boolean isControleActif() {
        return controleActif;
    }

    /**
     * Setter function.
     * @param controleActif the controleActif to set
     */
    public void setControleActif(boolean controleActif) {
        this.controleActif = controleActif;
    }

    /**
     * Récupère la valeur de idDevis.
     * @return la valeur de idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }

    /**
     * Définit la valeur de idDevis.
     * @param idDevis la nouvelle valeur de idDevis
     */
    public void setIdDevis(Long idDevis) {
        this.idDevis = idDevis;
    }

    /**
     * Get the loginUtilisateurConnecte value.
     * @return the loginUtilisateurConnecte
     */
    public String getLoginUtilisateurConnecte() {
        return loginUtilisateurConnecte;
    }

    /**
     * Set the loginUtilisateurConnecte value.
     * @param loginUtilisateurConnecte the loginUtilisateurConnecte to set
     */
    public void setLoginUtilisateurConnecte(String loginUtilisateurConnecte) {
        this.loginUtilisateurConnecte = loginUtilisateurConnecte;
    }

	/**
	 * Renvoie la valeur de createActionSuivi.
	 * @return createActionSuivi
	 */
	public Boolean getCreateActionSuivi() {
		return createActionSuivi;
	}

	/**
	 * Modifie createActionSuivi.
	 * @param createActionSuivi la nouvelle valeur de createActionSuivi
	 */
	public void setCreateActionSuivi(Boolean createActionSuivi) {
		this.createActionSuivi = createActionSuivi;
	}

}
