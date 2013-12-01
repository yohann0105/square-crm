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
package com.square.composant.contrat.square.client.ui.composant;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

/**
 * Panel récapitulatif des garanties.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class RecapitulatifGarantiesPanel extends FlexTable {

    /** Constantes. */
    private RecapitulatifGarantiesPanelConstants constants;

    /** Constructeur. */
    public RecapitulatifGarantiesPanel() {
        super();
        constants = (RecapitulatifGarantiesPanelConstants) GWT.create(RecapitulatifGarantiesPanelConstants.class);
    }

    /**
     * Remplit le tableau récapitulatif.
     * @param listeBeneficiairesRoles la liste des bénéficiaires et des rôles (nom, role)
     * @param listeProduitsIdentifiants la liste des garanties et leur identifiant (produit, identifiant)
     * @param valeurs la liste des valeurs (ordonnée par rapport à la liste des bénéficiaires et des produits)
     */
    public void remplirRecapitulatif(String[][] listeBeneficiairesRoles, String[][] listeProduitsIdentifiants, String[][] valeurs) {
        // Remplissage de l'entête
        if (valeurs.length > 0) {
            this.setWidget(0, 0, new Label(constants.enteteColonnePersonne()));
            this.setWidget(0, 1, new Label(constants.enteteColonneRole()));
            // Affichage des libellés des garanties
            for (int idxGarantie = 0; idxGarantie < listeProduitsIdentifiants.length; idxGarantie++) {
                if (listeProduitsIdentifiants[idxGarantie][0] != null) {
                    this.setWidget(0, idxGarantie + 2, new Label(listeProduitsIdentifiants[idxGarantie][0]));
                }
            }
            // Affichage des bénéficiaires (nom et rôle)
            for (int idxBeneficiaire = 0; idxBeneficiaire < listeBeneficiairesRoles.length; idxBeneficiaire++) {
                if (listeBeneficiairesRoles[idxBeneficiaire][0] != null) {
                    this.setWidget(idxBeneficiaire + 1, 0, new Label(listeBeneficiairesRoles[idxBeneficiaire][0]));
                }
                if (listeBeneficiairesRoles[idxBeneficiaire][1] != null) {
                    this.setWidget(idxBeneficiaire + 1, 1, new Label(listeBeneficiairesRoles[idxBeneficiaire][1]));
                }
            }
            // Affichage des valeurs
            for (int i = 0; i < valeurs.length; i++) {
                for (int j = 0; j < valeurs[i].length; j++) {
                    if (valeurs[i][j] != null) {
                        this.setWidget(i + 1, j + 2, new Label(valeurs[i][j]));
                    }
                }
            }
        }
    }
}
