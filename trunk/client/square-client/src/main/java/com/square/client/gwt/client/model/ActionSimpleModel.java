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

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Objet contenant une action simple.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionSimpleModel implements IsSerializable {

    /**
     * Le type de l'action.
     */
    private IdentifiantLibelleGwt type;

    /**
     * Le statut de l'action.
     */
    private IdentifiantLibelleGwt statut;

    /**
     * L'objet de l'action.
     */
    private IdentifiantLibelleGwt objet;

    /**
     * Le sous objet de l'action.
     */
    private IdentifiantLibelleGwt sousObjet;

    /**
     * Le libellé de la reclamation.
     */
    private String reclamation;

    /**
     * La date de l'action.
     */
    private String dateAction;

    /**
     * Retourne la valeur de type.
     * @return the type
     */
    public IdentifiantLibelleGwt getType() {
        return type;
    }

    /**
     * Modifie la valeur de type.
     * @param type the type to set
     */
    public void setType(IdentifiantLibelleGwt type) {
        this.type = type;
    }

    /**
     * Retourne la valeur de statut.
     * @return the statut
     */
    public IdentifiantLibelleGwt getStatut() {
        return statut;
    }

    /**
     * Modifie la valeur de statut.
     * @param statut the statut to set
     */
    public void setStatut(IdentifiantLibelleGwt statut) {
        this.statut = statut;
    }

    /**
     * Retourne la valeur de objet.
     * @return the objet
     */
    public IdentifiantLibelleGwt getObjet() {
        return objet;
    }

    /**
     * Modifie la valeur de objet.
     * @param objet the objet to set
     */
    public void setObjet(IdentifiantLibelleGwt objet) {
        this.objet = objet;
    }

    /**
     * Retourne la valeur de sousObjet.
     * @return the sousObjet
     */
    public IdentifiantLibelleGwt getSousObjet() {
        return sousObjet;
    }

    /**
     * Modifie la valeur de sousObjet.
     * @param sousObjet the sousObjet to set
     */
    public void setSousObjet(IdentifiantLibelleGwt sousObjet) {
        this.sousObjet = sousObjet;
    }

    /**
     * Retourne la valeur de reclamation.
     * @return the reclamation
     */
    public String getReclamation() {
        return reclamation;
    }

    /**
     * Modifie la valeur de reclamation.
     * @param reclamation the reclamation to set
     */
    public void setReclamation(String reclamation) {
        this.reclamation = reclamation;
    }

    /**
     * Retourne la valeur de dateAction.
     * @return the dateAction
     */
    public String getDateAction() {
        return dateAction;
    }

    /**
     * Modifie la valeur de dateAction.
     * @param dateAction the dateAction to set
     */
    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }

}
