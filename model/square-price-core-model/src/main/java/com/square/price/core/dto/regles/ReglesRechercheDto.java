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
package com.square.price.core.dto.regles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * DTO contenant les critères de recherche de règles.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public final class ReglesRechercheDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -3348827664817525984L;

    private Integer codeProduit;

    /** Date d'effet. */
    private Calendar dateEffet;

    private List < ListeObjetsParDiscriminantDto < CritereRegleDto > > listeReglesRechercheCritere;

    /** Constructeur par défaut. */
    public ReglesRechercheDto() { }

    /**
     * Constructeur paramétré.
     * @param codeProduit le code produit
     */
    public ReglesRechercheDto(Integer codeProduit) {
        this.codeProduit = codeProduit;
    }


    /**
     * Get the codeProduit value.
     * @return the codeProduit
     */
    public Integer getCodeProduit() {
        return codeProduit;
    }

    /**
     * Set the codeProduit value.
     * @param codeProduit the codeProduit to set
     */
    public void setCodeProduit(Integer codeProduit) {
        this.codeProduit = codeProduit;
    }

    /**
     * Ajoute un critère de recherche à la liste.
     * @param reglesRechercheCritereDto le critère à ajouter
     */
    public void ajouterReglesRechercheCritere(ListeObjetsParDiscriminantDto < CritereRegleDto > reglesRechercheCritereDto) {
        if (this.listeReglesRechercheCritere == null) {
            this.listeReglesRechercheCritere = new ArrayList < ListeObjetsParDiscriminantDto < CritereRegleDto > > ();
        }
        this.listeReglesRechercheCritere.add(reglesRechercheCritereDto);
    }

    /**
     * Get the listeReglesRechercheCritere value.
     * @return the listeReglesRechercheCritere
     */
    public List < ListeObjetsParDiscriminantDto < CritereRegleDto > > getListeReglesRechercheCritere() {
        return listeReglesRechercheCritere;
    }

    /**
     * Set the listeReglesRechercheCritere value.
     * @param listeReglesRechercheCritere the listeReglesRechercheCritere to set
     */
    public void setListeReglesRechercheCritere(List < ListeObjetsParDiscriminantDto < CritereRegleDto > > listeReglesRechercheCritere) {
        this.listeReglesRechercheCritere = listeReglesRechercheCritere;
    }

    /**
     * Get the dateEffet value.
     * @return the dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Set the dateEffet value.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
    }
}
