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
package com.square.composant.tarificateur.square.client.model.transfo.aia;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO contenant les résultats de la transformation AIA.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ResultatTransformationAiaModel implements IsSerializable {

    /** Liste des erreurs bloquantes. */
    private List<String> listeErreursBloquantes;

    /** Liste des erreurs non bloquantes. */
    private List<String> listeErreursNonBloquantes;

    /**
     * Récupère la valeur de listeErreursBloquantes.
     * @return la valeur de listeErreursBloquantes
     */
    public List<String> getListeErreursBloquantes() {
        if (listeErreursBloquantes == null) {
            listeErreursBloquantes = new ArrayList<String>();
        }
        return listeErreursBloquantes;
    }

    /**
     * Définit la valeur de listeErreursBloquantes.
     * @param listeErreursBloquantes la nouvelle valeur de listeErreursBloquantes
     */
    public void setListeErreursBloquantes(List<String> listeErreursBloquantes) {
        this.listeErreursBloquantes = listeErreursBloquantes;
    }

    /**
     * Récupère la valeur de listeErreursNonBloquantes.
     * @return la valeur de listeErreursNonBloquantes
     */
    public List<String> getListeErreursNonBloquantes() {
        if (listeErreursNonBloquantes == null) {
            listeErreursNonBloquantes = new ArrayList<String>();
        }
        return listeErreursNonBloquantes;
    }

    /**
     * Définit la valeur de listeErreursNonBloquantes.
     * @param listeErreursNonBloquantes la nouvelle valeur de listeErreursNonBloquantes
     */
    public void setListeErreursNonBloquantes(List<String> listeErreursNonBloquantes) {
        this.listeErreursNonBloquantes = listeErreursNonBloquantes;
    }

}
