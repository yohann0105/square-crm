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
package com.square.composant.contrat.square.client.bundle;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.CssResource.NotStrict;

/**
 * Interface des resource utilisées dans le composant contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface ComposantContratResources extends ClientBundle {

    /**
     * Css.
     * @return le css
     */
    @NotStrict
    @Source("com/square/composant/contrat/square/public/themes/default/styles/default.css")
    ComposantContratCss css();

    /**
     * Image Icône PDF.
     * @return la ressource de l'image
     */
    @Source("com/square/composant/contrat/square/public/themes/default/images/iconePdf2.png")
    ImageResource imageIconePdf();


    /**
     * Image Icône contrat actif.
     * @return la ressource de l'image
     */
    @Source("com/square/composant/contrat/square/public/themes/default/images/iconeContratActif.png")
    ImageResource imageIconeContratActif();

    /**
     * Image Icône contrat résilié.
     * @return la ressource de l'image
     */
    @Source("com/square/composant/contrat/square/public/themes/default/images/iconeContratResilie.png")
    ImageResource imageIconeContratResilie();
}
