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
package com.square.composant.envoi.email.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle pour une pièce jointe.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PieceJointeModel implements IsSerializable {

    /** Nom du fichier. */
    private String nom;

    /**  Type mime du fichier. */
    private String typeMime;

    /** Chemin d'accès du contenu du fichier. */
    private String path;

    /**
     * Récupère la valeur de nom.
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la valeur de typeMime.
     * @return la valeur de typeMime
     */
    public String getTypeMime() {
        return typeMime;
    }

    /**
     * Définit la valeur de typeMime.
     * @param typeMime la nouvelle valeur de typeMime
     */
    public void setTypeMime(String typeMime) {
        this.typeMime = typeMime;
    }

    /**
     * Récupère la valeur de path.
     * @return la valeur de path
     */
    public String getPath() {
        return path;
    }

    /**
     * Définit la valeur de path.
     * @param path la nouvelle valeur de path
     */
    public void setPath(String path) {
        this.path = path;
    }
}
