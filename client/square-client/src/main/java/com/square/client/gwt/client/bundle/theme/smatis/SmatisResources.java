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
package com.square.client.gwt.client.bundle.theme.smatis;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.square.client.gwt.client.bundle.SquareCss;
import com.square.client.gwt.client.bundle.SquareResources;

/**
 * Interface des images utilisées dans Square pour le theme Smatis.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface SmatisResources extends SquareResources {

    /**
     * Css.
     * @return le css
     */
    @NotStrict
    @Source({"com/square/client/gwt/public/themes/default/styles/default.css", "com/square/client/gwt/public/themes/smatis/styles/smatis.css",
        "com/square/client/gwt/public/themes/smatis/styles/cal.css" })
    SquareCss css();

    /**
     * Icone de l'onglet tableau de bord.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/icones_onglets/dashboard.png")
    ImageResource iconeOngletTableauBord();

    /**
     * Icone de l'onglet personne physique.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/icones_onglets/users.png")
    ImageResource iconeOngletPersonnePhysique();

    /**
     * Icone de l'onglet personne morale.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/icones_onglets/societes.png")
    ImageResource iconeOngletPersonneMorale();

    /**
     * Icone de l'onglet campagnes marketing.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/icones_onglets/campagnes.png")
    ImageResource iconeOngletCampagnesMarketing();

    /**
     * Icone de l'onglet actions relances.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/icones_onglets/actions.png")
    ImageResource iconeOngletActionsRelances();

    /**
     * Icone de l'onglet agenda.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/icones_onglets/agenda.png")
    ImageResource iconeOngletAgenda();

    /**
     * Icone de l'onglet ressources.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/icones_onglets/resources.png")
    ImageResource iconeOngletRessources();

    /**
     * Icone Plus.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/icones/plus.png")
    ImageResource iconePlus();

    /**
     * Image gauche du bouton action vert.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/formulaire/bouton_action_vert_gauche.png")
    ImageResource boutonActionVertGauche();

    /**
     * Image fond du bouton action vert.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/formulaire/bouton_action_vert_fond.png")
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource boutonActionVertFond();

    /**
     * Image droite du bouton action vert.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/formulaire/bouton_action_vert_droite.png")
    ImageResource boutonActionVertDroite();

    /**
     * Image de l'arrondi bas droite du fond vert.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/bloc_vert_bas_droite.png")
    ImageResource blocVertBasDroite();

    /**
     * Image de l'arrondi bas droite du fond vert.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/bloc_vert_fond_haut.jpg")
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource blocVertFondHaut();

    /**
     * Image de fleche bleue.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/icones/fleche_bleue.jpg")
    ImageResource flecheBleue();

    /**
     * Icone de l'onglet pour le scroll vers la droite.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/onglets/scroll_droite.png")
    ImageResource iconeOngletScrollDroite();

    /**
     * Icone de l'onglet pour le scroll vers la gauche.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/onglets/scroll_gauche.png")
    ImageResource iconeOngletScrollGauche();

    /**
     * Icone de l'onglet pour le scroll vers la droite désactivé.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/onglets/scroll_droite_disable.png")
    ImageResource iconeOngletScrollDroiteDisable();

    /**
     * Icone de l'onglet pour le scroll vers la gauche désactivé.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/onglets/scroll_gauche_disable.png")
    ImageResource iconeOngletScrollGaucheDisable();

    /**
     * Image de l'arrondi bas droite du fond bleu.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/bloc_bleu_bas_droite.jpg")
    ImageResource blocBleuBasDroite();
}
