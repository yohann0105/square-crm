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
package com.square.client.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle contenant les informations pour un commentaire d'action.
 * @author cblanchard - SCUB
 */
public class HistoriqueCommentaireModel implements IsSerializable {

	/** Identifiant de la ressource ayant posté le commentaire. */
	private DimensionRessourceModel ressource;

    /** Objet de l'action. */
    private String objet;

    /** Sous objet de l'action. */
    private String sousObjet;

    /** Attribution de l'action. */
    private String attribution;

    /** Date d'action. */
    private String dateAction;

    /** Description de l'action. */
    private String descriptif;

	/**
	 * Renvoi la valeur de ressource.
	 * @return ressource
	 */
	public DimensionRessourceModel getRessource() {
		return ressource;
	}

	/**
	 * Modifie ressource.
	 * @param ressource la nouvelle valeur de ressource
	 */
	public void setRessource(DimensionRessourceModel ressource) {
		this.ressource = ressource;
	}

    /**
     * Renvoi la valeur de objet.
     * @return objet
     */
    public String getObjet() {
        return objet;
    }

    /**
     * Modifie objet.
     * @param objet la nouvelle valeur de objet
     */
    public void setObjet(String objet) {
        this.objet = objet;
    }

    /**
     * Renvoi la valeur de sousObjet.
     * @return sousObjet
     */
    public String getSousObjet() {
        return sousObjet;
    }

    /**
     * Modifie sousObjet.
     * @param sousObjet la nouvelle valeur de sousObjet
     */
    public void setSousObjet(String sousObjet) {
        this.sousObjet = sousObjet;
    }

    /**
     * Renvoi la valeur de attribution.
     * @return attribution
     */
    public String getAttribution() {
        return attribution;
    }

    /**
     * Modifie attribution.
     * @param attribution la nouvelle valeur de attribution
     */
    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    /**
     * Renvoi la valeur de dateAction.
     * @return dateAction
     */
    public String getDateAction() {
        return dateAction;
    }

    /**
     * Modifie dateAction.
     * @param dateAction la nouvelle valeur de dateAction
     */
    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }

    /**
     * Renvoi la valeur de descriptif.
     * @return descriptif
     */
    public String getDescriptif() {
        return descriptif;
    }

    /**
     * Modifie descriptif.
     * @param descriptif la nouvelle valeur de descriptif
     */
    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

}
