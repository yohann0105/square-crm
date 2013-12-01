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
package com.square.client.gwt.client.composant.bloc;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;

/**
 * Contenu d'onglet principal qui remplit la hauteur de la page.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ContenuOnglet extends Composite implements HasWidgets {

    private SimplePanel conteneur;

    /**
     * Constructeur.
     */
    public ContenuOnglet() {
        conteneur = new SimplePanel();
        conteneur.setSize(AppControllerConstants.POURCENT_100, AppControllerConstants.POURCENT_100);
        conteneur.addStyleName(SquareResources.INSTANCE.css().contenuOnglet());
        this.initWidget(conteneur);
    }

    /**
     * Constructeur.
     * @param contenu le contenu
     */
    public ContenuOnglet(Widget contenu) {
        this();
        conteneur.add(contenu);
    }

    @Override
    public void add(Widget contenu) {
        conteneur.add(contenu);
    }

    @Override
    public void clear() {
        conteneur.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return conteneur.iterator();
    }

    @Override
    public boolean remove(Widget widget) {
        return conteneur.remove(widget);
    }
}
