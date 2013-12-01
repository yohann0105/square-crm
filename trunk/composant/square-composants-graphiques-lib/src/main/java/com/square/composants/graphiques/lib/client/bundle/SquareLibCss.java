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
package com.square.composants.graphiques.lib.client.bundle;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;

/**
 * Interface du css utilisé dans Square.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@Shared
public interface SquareLibCss extends CssResource {

    /**
     * Css.
     * @return css correspondant.
     */
    String blocSyntheseDepliant();

    /**
     * Css.
     * @return css correspondant.
     */
    String decoratedInfoPopup();

    /**
     * Css.
     * @return css correspondant.
     */
    String messageInfoPopup();

    /**
     * Css.
     * @return css correspondant.
     */
    String decoratedWarningPopup();

    /**
     * Css.
     * @return css correspondant.
     */
    String messageWarningPopup();

    /**
     * Css.
     * @return css correspondant.
     */
    String deskBar();

    /**
     * Css.
     * @return css correspondant.
     */
    String deskItem();
}
