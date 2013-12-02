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
package com.square.composant.contrat.square.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant les informations des différents contrats souscrits par un adhérent.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ListeContratsModel implements IsSerializable {

    /**
     * Synthèse de l'ensemble des contrats soucrit par l'adhérent.
     */
    private InfosContratsModel infosContrats;

    /**
     * Liste des contrats souscrits par l'adhérent.
     */
    private List<ContratSimpleModel> listeContrats;

    /**
     * Récupère la valeur de infosContrats.
     * @return la valeur de infosContrats
     */
    public InfosContratsModel getInfosContrats() {
        return infosContrats;
    }

    /**
     * Définit la valeur de infosContrats.
     * @param infosContrats la nouvelle valeur de infosContrats
     */
    public void setInfosContrats(InfosContratsModel infosContrats) {
        this.infosContrats = infosContrats;
    }

    /**
     * Récupère la valeur de listeContrats.
     * @return la valeur de listeContrats
     */
    public List<ContratSimpleModel> getListeContrats() {
        if (listeContrats == null) {
            listeContrats = new ArrayList<ContratSimpleModel>();
        }
        return listeContrats;
    }

    /**
     * Définit la valeur de listeContrats.
     * @param listeContrats la nouvelle valeur de listeContrats
     */
    public void setListeContrats(List<ContratSimpleModel> listeContrats) {
        this.listeContrats = listeContrats;
    }

}
