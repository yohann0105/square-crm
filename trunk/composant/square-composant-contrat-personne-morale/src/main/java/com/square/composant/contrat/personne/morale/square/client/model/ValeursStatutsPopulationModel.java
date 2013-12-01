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
 * Modèle encapsulant les différentes valeurs d'une population pour les différents statuts possibles.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ValeursStatutsPopulationModel implements IsSerializable {

    /** Libellé de la population. */
    private String libellePopulation;

    /** La liste des effectifs pour les statuts. */
    private List<EffectifStatutModel> listeEffectifsParStatut;

    /**
     * Récupère la valeur de libellePopulation.
     * @return la valeur de libellePopulation
     */
    public String getLibellePopulation() {
        return libellePopulation;
    }

    /**
     * Définit la valeur de libellePopulation.
     * @param libellePopulation la nouvelle valeur de libellePopulation
     */
    public void setLibellePopulation(String libellePopulation) {
        this.libellePopulation = libellePopulation;
    }

    /**
     * Récupère la valeur de listeEffectifsParStatut.
     * @return la valeur de listeEffectifsParStatut
     */
    public List<EffectifStatutModel> getListeEffectifsParStatut() {
        if (listeEffectifsParStatut == null) {
            listeEffectifsParStatut = new ArrayList<EffectifStatutModel>();
        }
        return listeEffectifsParStatut;
    }

    /**
     * Définit la valeur de listeEffectifsParStatut.
     * @param listeEffectifsParStatut la nouvelle valeur de listeEffectifsParStatut
     */
    public void setListeEffectifsParStatut(List<EffectifStatutModel> listeEffectifsParStatut) {
        this.listeEffectifsParStatut = listeEffectifsParStatut;
    }
}
