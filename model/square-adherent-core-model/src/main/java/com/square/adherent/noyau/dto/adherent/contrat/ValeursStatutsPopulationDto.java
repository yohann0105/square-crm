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
package com.square.adherent.noyau.dto.adherent.contrat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto encapsulant les différentes valeurs d'une population pour les différents statuts possibles.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ValeursStatutsPopulationDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -7430949730232537956L;

    /** Libellé de la population. */
    private String libellePopulation;

    /** Ordre d'affichage de la population. */
    private Integer ordrePopulation;

    /** La liste des effectifs pour les statuts. */
    private List<EffectifStatutDto> listeEffectifsParStatut;

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
    public List<EffectifStatutDto> getListeEffectifsParStatut() {
        if (listeEffectifsParStatut == null) {
            listeEffectifsParStatut = new ArrayList<EffectifStatutDto>();
        }
        return listeEffectifsParStatut;
    }

    /**
     * Définit la valeur de listeEffectifsParStatut.
     * @param listeEffectifsParStatut la nouvelle valeur de listeEffectifsParStatut
     */
    public void setListeEffectifsParStatut(List<EffectifStatutDto> listeEffectifsParStatut) {
        this.listeEffectifsParStatut = listeEffectifsParStatut;
    }

    /**
     * Récupère la valeur de ordrePopulation.
     * @return la valeur de ordrePopulation
     */
    public Integer getOrdrePopulation() {
        return ordrePopulation;
    }

    /**
     * Définit la valeur de ordrePopulation.
     * @param ordrePopulation la nouvelle valeur de ordrePopulation
     */
    public void setOrdrePopulation(Integer ordrePopulation) {
        this.ordrePopulation = ordrePopulation;
    }
}
