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

import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.ExtendedSuggestBox.ExtendedSuggestBoxImages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;

/**
 * Interface des images utilisées dans Square pour le theme Smatis.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface SmatisExtendedSuggestBoxImages extends ExtendedSuggestBoxImages {

	/**
	 * Instance of SmatisExtendedSuggestBoxImages.
	 */
    ExtendedSuggestBoxImages INSTANCE = (ExtendedSuggestBoxImages) GWT.create(SmatisExtendedSuggestBoxImages.class);

    /**
     * Fleche de la suggestbox.
     * @return l'image
     */
    @Source("com/square/client/gwt/public/themes/smatis/images/formulaire/suggestListBox_vert_droite.png")
    ImageResource fleche();
}
