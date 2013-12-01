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
package com.square.client.gwt.client.bundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.square.client.gwt.client.bundle.theme.smatis.SmatisResources;

/**
 * Interface des images utilisées dans Square.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface SquareResources extends ClientBundle {

    // TODO trouver une methode pour spécifier l'interface à instancier ailleurs
	/**
	 * Instance of SmatisResources.
	 */
    SquareResources INSTANCE = (SquareResources) GWT.create(SmatisResources.class);

    /**
     * Css de Square.
     * @return le css
     */
    @NotStrict
    @Source("com/square/client/gwt/public/themes/default/styles/default.css")
    SquareCss css();

    /**
     * Flag Pays France.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/flags/flag-fr.png")
    ImageResource flagFr();

    /**
     * Image d'erreur pour un champ.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/icones/erreurChamp.png")
    ImageResource erreurChamp();

    /**
     * Icone de l'onglet tableau de bord.
     * @return l'image
     */
    ImageResource iconeOngletTableauBord();

    /**
     * Icone de l'onglet personne physique.
     * @return l'image
     */
    ImageResource iconeOngletPersonnePhysique();

    /**
     * Icone de l'onglet personne morale.
     * @return l'image
     */
    ImageResource iconeOngletPersonneMorale();

    /**
     * Icone de l'onglet campagnes marketing.
     * @return l'image
     */
    ImageResource iconeOngletCampagnesMarketing();

    /**
     * Icone de l'onglet actions relances.
     * @return l'image
     */
    ImageResource iconeOngletActionsRelances();

    /**
     * Icone de l'onglet agenda.
     * @return l'image
     */
    ImageResource iconeOngletAgenda();

    /**
     * Icone de l'onglet ressources.
     * @return l'image
     */
    ImageResource iconeOngletRessources();

    /**
     * Icone Plus.
     * @return l'image
     */
    ImageResource iconePlus();

    /**
     * Image gauche du bouton action vert.
     * @return l'image
     */
    ImageResource boutonActionVertGauche();

    /**
     * Image fond du bouton action vert.
     * @return l'image
     */
    ImageResource boutonActionVertFond();

    /**
     * Image droite du bouton action vert.
     * @return l'image
     */
    ImageResource boutonActionVertDroite();

    /**
     * Image de selection multiple.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/selection_multiple.png")
    ImageResource selectionMultiple();

    /**
     * Image de l'arrondi bas droite du fond vert.
     * @return l'image
     */
    ImageResource blocVertBasDroite();

    /**
     * Image de l'arrondi bas droite du fond vert.
     * @return l'image
     */
    ImageResource blocVertFondHaut();

    /**
     * Image de fleche bleue.
     * @return l'image
     */
    ImageResource flecheBleue();

    /**
     * Icone de l'onglet pour le scroll vers la droite.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/onglets/scroll_droite.png")
    ImageResource iconeOngletScrollDroite();

    /**
     * Icone de l'onglet pour le scroll vers la gauche.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/onglets/scroll_gauche.png")
    ImageResource iconeOngletScrollGauche();

    /**
     * Icone de l'onglet pour le scroll vers la droite désactivé.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/onglets/scroll_droite_disable.png")
    ImageResource iconeOngletScrollDroiteDisable();

    /**
     * Icone de l'onglet pour le scroll vers la gauche désactivé.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/onglets/scroll_gauche_disable.png")
    ImageResource iconeOngletScrollGaucheDisable();

    /**
     * Icone du téléphone actif.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/icones/phoneActif.png")
    ImageResource iconePhoneActif();

    /**
     * Icone du téléphone inactif.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/icones/phoneInactif.png")
    ImageResource iconePhoneInactif();

    /**
     * Icone pour un warning.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/icones/warning.png")
    ImageResource imgWarning();

    /**
     * Icone pour excel.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/icones/excel.png")
    ImageResource iconeExcel();

    /**
     * Icone.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/icones/accept.gif")
    ImageResource iconeAccept();

    /**
     * Icone.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/default/images/icones/cancel.png")
    ImageResource iconeCancel();
}
