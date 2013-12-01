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
package com.square.composant.tarificateur.square.client.bundle;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;

/**
 * Interface du css utilisé dans le composant Tarificateur.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@Shared
public interface ComposantTarificateurCss extends CssResource {

    /**
     * Style CSS.
     * @return le style
     */
    String blocEnteteDevis();

    /**
     * Style CSS.
     * @return le style
     */
    String ficheDevis();

    /**
     * Style CSS.
     * @return le style
     */
    String finaliteAcceptee();

    /**
     * Style CSS.
     * @return le style
     */
    String finaliteRefusee();

    /**
     * Style CSS.
     * @return le style
     */
    String finaliteCorbeille();

    /**
     * Style CSS.
     * @return le style
     */
    String finaliteNonRenseignee();

    /**
     * Style CSS.
     * @return le style
     */
    String tableauLignesDevis();

    /**
     * Style CSS.
     * @return le style
     */
    String tableauLignesDevisEntete();

    /**
     * Style CSS.
     * @return le style
     */
    String important();

    /**
     * Style CSS.
     * @return le style
     */
    String produitSansTarif();

    /**
     * Style CSS.
     * @return le style
     */
    String aucunDevis();

    /**
     * Style CSS.
     * @return le style
     */
    String composantTarificateur();

    /**
     * Style CSS.
     * @return le style
     */
    String ligneDevis();

    /**
     * Style CSS.
     * @return le style
     */
    String total();

    /**
     * Style CSS.
     * @return le style
     */
    String totalApresRemise();

    /**
     * Style CSS.
     * @return le style
     */
    String enteteLignePrincipale();

    /**
     * Style CSS.
     * @return le style
     */
    String sansBordure();

    /**
     * Style CSS.
     * @return le style
     */
    String panelProduits();

    /**
     * Style CSS.
     * @return le style
     */
    String legendeNonVendu();

    /**
     * Style CSS.
     * @return le style
     */
    String caseCouleurNonVendu();

    /**
     * Style CSS.
     * @return le style
     */
    String selectionProduits();

    /**
     * Style CSS.
     * @return le style
     */
    String panelBoutons();

    /**
     * Style CSS.
     * @return le style
     */
    String choixProduitBloc();

    /**
     * Style CSS.
     * @return le style
     */
    String choixProduit();

    /**
     * Style CSS.
     * @return le style
     */
    String panelFamillePrincipale();

    /**
     * Style CSS.
     * @return le style
     */
    String conteneurFamille();

    /**
     * Style CSS.
     * @return le style
     */
    String panelListeProduits();

    /**
     * Style CSS.
     * @return le style
     */
    String panelInfosBeneficiaires();

    /**
     * Style CSS.
     * @return le style
     */
    String panelFamilleLiee();

    /**
     * Style CSS.
     * @return le style
     */
    String panelCriteresBeneficiaires();

    /**
     * Style CSS.
     * @return le style
     */
    String panelInfosPersonne();

    /**
     * Style CSS.
     * @return le style
     */
    String panelErreursContraintes();

    /**
     * Style CSS.
     * @return le style
     */
    String panelCriteres();

    /**
     * Style CSS.
     * @return le style
     */
    String libelleGras();

    /**
     * Style CSS.
     * @return le style
     */
    String panelTitreFamille();

    /**
     * Style CSS.
     * @return le style
     */
    String produitInactif();

    /**
     * Style CSS.
     * @return le style
     */
    String popupChoixModele();

    /**
     * Style CSS.
     * @return le style
     */
    String popupAjoutAssure();

    /**
     * Style CSS.
     * @return le style
     */
    String popupInfosAdhesion();

    /**
     * Style CSS.
     * @return le style
     */
    String popupTitle();

    /**
     * Style CSS.
     * @return le style
     */
    String messagePopupTitle();

    /**
     * Style CSS.
     * @return le style
     */
    String popupEnvoiEmail();

    /**
     * Style CSS.
     * @return le style
     */
    String popupRechercheOppTransaction();

    /**
     * Style css.
     * @return le style
     */
    String labelReclamation();

    /**
     * Style css.
     * @return le style
     */
    String popupCreationPersonneDoublon();
}
