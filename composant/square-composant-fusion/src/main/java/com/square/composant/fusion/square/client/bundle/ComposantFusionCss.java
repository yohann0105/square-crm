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
package com.square.composant.fusion.square.client.bundle;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;

/**
 * Interface du css utilisé dans le composant de fusion.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
@Shared
public interface ComposantFusionCss extends CssResource {

    /**
     * Style CSS pour le composant de fusion.
     * @return le style.
     */
    String composantFusion();

    /**
     * Style CSS pour le contenu des pages.
     * @return le style.
     */
    String contenuPageFusion();

    /**
     * Style CSS pour la page de sélection de doublon.
     * @return le style.
     */
    String selectionDoublons();

    /**
     * Style CSS pour le bloc de la source.
     * @return le style.
     */
    String blocSource();

    /**
     * Style CSS pour le bloc de la cible.
     * @return le style.
     */
    String blocCible();

    /**
     * Style CSS pour le contenu du bloc.
     * @return le style.
     */
    String contenuBloc();

    /**
     * Style CSS pour le numéro de client.
     * @return le style.
     */
    String numeroClient();

    /**
     * Style CSS pour la table des propriétés à fusionner.
     * @return le style.
     */
    String tableau();

    /**
     * Style CSS pour le titre des colonnes des propriétés.
     * @return le style.
     */
    String titreColonne();

    /**
     * Style CSS pour les cellules claires.
     * @return le style.
     */
    String cellClair();

    /**
     * Style CSS pour les cellules sombres.
     * @return le style.
     */
    String cellSombre();

    /**
     * Style CSS pour le bouton inactif.
     * @return le style.
     */
    String btnInactif();

    /**
     * Style CSS pour la date de création.
     * @return le style.
     */
    String dateCreation();
}
