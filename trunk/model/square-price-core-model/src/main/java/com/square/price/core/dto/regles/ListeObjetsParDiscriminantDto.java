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
import java.util.List;

/**
 * Permet le transport d'une liste d'objets associés à un discriminant.
 * @param <T> le type de l'objet a associer au discriminant.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public class ListeObjetsParDiscriminantDto < T > implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 1586663089522153579L;

    private String discriminant;

    private List < T > listeObjets;

    /** Constructeur par défaut. */
    public ListeObjetsParDiscriminantDto() { }

    /**
     * Constructeur paramétré.
     * @param discriminant le discriminant
     */
    public ListeObjetsParDiscriminantDto(String discriminant) {
        this.discriminant = discriminant;
    }

    /**
     * Get the discriminant value.
     * @return the discriminant
     */
    public String getDiscriminant() {
        return discriminant;
    }

    /**
     * Set the discriminant value.
     * @param discriminant the discriminant to set
     */
    public void setDiscriminant(String discriminant) {
        this.discriminant = discriminant;
    }

    /**
     * Get the listeObjets value.
     * @return the listeObjets
     */
    public List < T > getListeObjets() {
        return listeObjets;
    }

    /**
     * Set the listeObjets value.
     * @param listeObjets the listeObjets to set
     */
    public void setListeObjets(List < T > listeObjets) {
        this.listeObjets = listeObjets;
    }

    /**
     * Ajout d'un objet à la liste.
     * @param objet l'objet à ajouter
     */
    public void ajouterObjetAListe(T objet) {
        if (listeObjets == null) {
            listeObjets = new ArrayList < T > ();
        }
        listeObjets.add(objet);
     }

}
