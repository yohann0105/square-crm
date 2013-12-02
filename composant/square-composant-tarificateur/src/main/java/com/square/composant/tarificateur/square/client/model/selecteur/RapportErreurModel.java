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
package com.square.composant.tarificateur.square.client.model.selecteur;

import java.util.List;

/**
 * Model permettant de transférer un rapport d'erreur.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class RapportErreurModel {

    /** Nom du produit. */
    private String nomProduit;

    /** Nom de la personne. */
    private String nomPersonne;

    /**
     * Liste des erreurs.
     */
    private List<String> listeErreurs;

    /**
     * Retourne la valeur de listeErreurs.
     * @return listeErreurs
     */
    public List<String> getListeErreurs() {
        return listeErreurs;
    }

    /**
     * Définit la valeur de listeErreurs.
     * @param listeErreurs la nouvelle valeur de listeErreurs
     */
    public void setListeErreurs(List<String> listeErreurs) {
        this.listeErreurs = listeErreurs;
    }

    /**
     * Retourne la valeur de nomPersonne.
     * @return nomPersonne
     */
    public String getNomPersonne() {
        return nomPersonne;
    }

    /**
     * Définit la valeur de nomPersonne.
     * @param nomPersonne la nouvelle valeur de nomPersonne
     */
    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    /**
     * Retourne la valeur de nomProduit.
     * @return nomProduit
     */
    public String getNomProduit() {
        return nomProduit;
    }

    /**
     * Définit la valeur de nomProduit.
     * @param nomProduit la nouvelle valeur de nomProduit
     */
    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    /**
     * Formatage du rapport en HTML.
     * @return le rapport en HTML
     */
    public String toHTML() {
        final StringBuffer rapport = new StringBuffer();
        rapport.append("<b>Contraintes de vente non respectées</b>");
        if (nomProduit != null) {
            rapport.append("<br>Produit : ").append(nomProduit);
        }
        if (nomPersonne != null) {
            rapport.append("<br>Assuré : ").append(nomPersonne);
        }
        for (String erreur : listeErreurs) {
            rapport.append("<br>- ").append(erreur);
        }
        return rapport.toString();
    }

}
