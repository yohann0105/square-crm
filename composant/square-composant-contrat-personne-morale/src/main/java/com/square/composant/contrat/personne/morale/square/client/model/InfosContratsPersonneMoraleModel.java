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
package com.square.composant.contrat.personne.morale.square.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant les informations des différents contrats souscrits par la personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class InfosContratsPersonneMoraleModel implements IsSerializable {

    /** La synthèse des contrats de la personne morale. */
    private SyntheseContratPersonneMoraleModel syntheseContrat;

    /** Liste des contrats souscrits par la personne morale. */
    private List<ContratSimplePersonneMoraleModel> listeContrats;

    /**
     * Récupère la valeur de syntheseContrat.
     * @return la valeur de syntheseContrat
     */
    public SyntheseContratPersonneMoraleModel getSyntheseContrat() {
        return syntheseContrat;
    }

    /**
     * Définit la valeur de syntheseContrat.
     * @param syntheseContrat la nouvelle valeur de syntheseContrat
     */
    public void setSyntheseContrat(SyntheseContratPersonneMoraleModel syntheseContrat) {
        this.syntheseContrat = syntheseContrat;
    }

    /**
     * Récupère la valeur de listeContrats.
     * @return la valeur de listeContrats
     */
    public List<ContratSimplePersonneMoraleModel> getListeContrats() {
        if (listeContrats == null) {
            listeContrats = new ArrayList<ContratSimplePersonneMoraleModel>();
        }
        return listeContrats;
    }

    /**
     * Définit la valeur de listeContrats.
     * @param listeContrats la nouvelle valeur de listeContrats
     */
    public void setListeContrats(List<ContratSimplePersonneMoraleModel> listeContrats) {
        this.listeContrats = listeContrats;
    }
}
