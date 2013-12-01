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
package com.square.composant.aide.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * interface des ressources de composants.
 * @author mohamed - SCUB
 */
public interface AideComposantRessources extends ClientBundle {
    /**
     * instance de la classe de ressources.
     */
    AideComposantRessources INSTANCE = (AideComposantRessources) GWT.create(AideComposantRessources.class);

    /**
     * image icone aide administrateur.
     * @return l'image
     */
    @Source("com/square/composant/aide/gwt/public/images/help.png")
    ImageResource iconeAide();

    /**
     * image icone aide utilisateur..
     * @return l'image
     */
    @Source("com/square/composant/aide/gwt/public/images/nohelp.png")
    ImageResource iconeSansAide();
}
