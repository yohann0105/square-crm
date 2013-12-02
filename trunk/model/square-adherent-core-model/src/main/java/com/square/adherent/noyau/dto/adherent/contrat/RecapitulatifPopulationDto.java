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

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto contenant le récapitulatif des populations.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class RecapitulatifPopulationDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 8644810839246003724L;

    /** La liste des statuts. */
    private List<IdentifiantLibelleDto> listeStatuts;

    /** Liste des valeurs d'une population pour les différents statuts. */
    private List<ValeursStatutsPopulationDto> listeValeursPopulation;

    /**
     * Récupère la valeur de listeStatuts.
     * @return la valeur de listeStatuts
     */
    public List<IdentifiantLibelleDto> getListeStatuts() {
        if (listeStatuts == null) {
            listeStatuts = new ArrayList<IdentifiantLibelleDto>();
        }
        return listeStatuts;
    }

    /**
     * Définit la valeur de listeStatuts.
     * @param listeStatuts la nouvelle valeur de listeStatuts
     */
    public void setListeStatuts(List<IdentifiantLibelleDto> listeStatuts) {
        this.listeStatuts = listeStatuts;
    }

    /**
     * Récupère la valeur de listeValeursPopulation.
     * @return la valeur de listeValeursPopulation
     */
    public List<ValeursStatutsPopulationDto> getListeValeursPopulation() {
        if (listeValeursPopulation == null) {
            listeValeursPopulation = new ArrayList<ValeursStatutsPopulationDto>();
        }
        return listeValeursPopulation;
    }

    /**
     * Définit la valeur de listeValeursPopulation.
     * @param listeValeursPopulation la nouvelle valeur de listeValeursPopulation
     */
    public void setListeValeursPopulation(List<ValeursStatutsPopulationDto> listeValeursPopulation) {
        this.listeValeursPopulation = listeValeursPopulation;
    }
}
