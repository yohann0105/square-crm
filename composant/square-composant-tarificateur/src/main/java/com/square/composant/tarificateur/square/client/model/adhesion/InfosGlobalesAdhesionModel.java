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

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.tarificateur.square.client.model.personne.AssureSocialModel;

/**
 * DTO modélisant les infos globales pour la popup d'adhesion.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfosGlobalesAdhesionModel implements IsSerializable {

    private InfosAdhesionModel infosAdhesionModel;

    private List<AssureSocialModel> listeAssuresSociaux;

    /**
     * Get the infosAdhesionModel value.
     * @return the infosAdhesionModel
     */
    public InfosAdhesionModel getInfosAdhesionModel() {
        return infosAdhesionModel;
    }

    /**
     * Set the infosAdhesionModel value.
     * @param infosAdhesionModel the infosAdhesionModel to set
     */
    public void setInfosAdhesionModel(InfosAdhesionModel infosAdhesionModel) {
        this.infosAdhesionModel = infosAdhesionModel;
    }

    /**
     * Get the listeAssuresSociaux value.
     * @return the listeAssuresSociaux
     */
    public List<AssureSocialModel> getListeAssuresSociaux() {
        return listeAssuresSociaux;
    }

    /**
     * Set the listeAssuresSociaux value.
     * @param listeAssuresSociaux the listeAssuresSociaux to set
     */
    public void setListeAssuresSociaux(List<AssureSocialModel> listeAssuresSociaux) {
        this.listeAssuresSociaux = listeAssuresSociaux;
    }

}
