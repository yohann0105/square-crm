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
package com.square.composant.espace.client.square.client.bundle;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;

/**
 * Interface du css utilisé dans le composant Espace client.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@Shared
public interface ComposantEspaceClientCss extends CssResource {

    /**
     * Retourne le style css.
     * @return le style
     */
    String composantEspaceClient();

    /**
     * Retourne le style css.
     * @return le style
     */
    String blocSyntheseDepliant();

    /**
     * Retourne le style css.
     * @return le style
     */
    String tableauOptions();

    /**
     * Retourne le style css.
     * @return le style
     */
    String celluleSansBordure();

    /**
     * Retourne le style css.
     * @return le style
     */
    String celluleEnteteColonne();

    /**
     * Retourne le style css.
     * @return le style
     */
    String celluleEnteteLigne();

}
