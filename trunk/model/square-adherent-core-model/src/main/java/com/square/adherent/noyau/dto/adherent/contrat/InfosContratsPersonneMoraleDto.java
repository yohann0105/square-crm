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
 * Dto encapsulant les informations des différents contrats souscrits par la personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class InfosContratsPersonneMoraleDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -6201311913278517549L;

    /** La synthèse des contrats de la personne morale. */
    private SyntheseContratPersonneMoraleDto syntheseContrat;

    /** Liste des contrats souscrits par la personne morale. */
    private List<ContratSimpleDto> listeContrats;

    /**
     * Récupère la valeur de syntheseContrat.
     * @return la valeur de syntheseContrat
     */
    public SyntheseContratPersonneMoraleDto getSyntheseContrat() {
        return syntheseContrat;
    }

    /**
     * Définit la valeur de syntheseContrat.
     * @param syntheseContrat la nouvelle valeur de syntheseContrat
     */
    public void setSyntheseContrat(SyntheseContratPersonneMoraleDto syntheseContrat) {
        this.syntheseContrat = syntheseContrat;
    }

    /**
     * Récupère la valeur de listeContrats.
     * @return la valeur de listeContrats
     */
    public List<ContratSimpleDto> getListeContrats() {
        if (listeContrats == null) {
            listeContrats = new ArrayList<ContratSimpleDto>();
        }
        return listeContrats;
    }

    /**
     * Définit la valeur de listeContrats.
     * @param listeContrats la nouvelle valeur de listeContrats
     */
    public void setListeContrats(List<ContratSimpleDto> listeContrats) {
        this.listeContrats = listeContrats;
    }
}
