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
package com.square.client.gwt.client.composant.code.postal.commune;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.square.client.gwt.client.model.IdentifiantLibelleCodePostalCommuneModel;

/**
 * Composant de suggestion pour les types Identifiant-Libelle pour les CodePostal/Communes.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class IdentifiantLibelleCodePostalCommuneSuggestion implements Suggestion {

    private static final String SEPARATEUR_CP_VILLE = " - ";

    /** L'objet de la suggestion. */
    private IdentifiantLibelleCodePostalCommuneModel identifiantLibelle;

    /**
     * Constructeur avec paramètre.
     * @param identifiantLibelle l'objet lié à la suggestion
     */
    public IdentifiantLibelleCodePostalCommuneSuggestion(IdentifiantLibelleCodePostalCommuneModel identifiantLibelle) {
        this.identifiantLibelle = identifiantLibelle;
    }

    /** {@inheritDoc} */
    public String getDisplayString() {
        return identifiantLibelle.getLibelleCodePostal() + SEPARATEUR_CP_VILLE + identifiantLibelle.getLibelleCommune();
    }

    /**
     * {@inheritDoc}
     */
    public String getReplacementString() {
        return identifiantLibelle.getLibelleCodePostal();
    }

    /** {@inheritDoc} */
    public Long getValue() {
        return identifiantLibelle.getIdCommune();
    }

    /**
     * Retourne la valeur de identifiantLibelle.
     * @return identifiantLibelle
     */
    public IdentifiantLibelleCodePostalCommuneModel getIdentifiantLibelle() {
        return identifiantLibelle;
    }

    /**
     * Méthode utilitaire permettant de transformer une liste de IdentifiantLibelleCodePostalCommuneGwt en liste de VilleSuggestion.
     * @param listeResultats : la liste des resultats
     * @return la liste de VilleSuggestion
     */
    public static List<IdentifiantLibelleCodePostalCommuneSuggestion> transformerIdentifiantLibelle(
        List<IdentifiantLibelleCodePostalCommuneModel> listeResultats) {
        // Création de la liste de retour
        final List<IdentifiantLibelleCodePostalCommuneSuggestion> liste = new ArrayList<IdentifiantLibelleCodePostalCommuneSuggestion>();

        if (listeResultats != null) {
            // Parcours de la liste des résultats
            for (IdentifiantLibelleCodePostalCommuneModel identifiantLibelle : listeResultats) {
                // Création de la suggestion
                liste.add(new IdentifiantLibelleCodePostalCommuneSuggestion(identifiantLibelle));
            }
        }
        return liste;
    }

}
