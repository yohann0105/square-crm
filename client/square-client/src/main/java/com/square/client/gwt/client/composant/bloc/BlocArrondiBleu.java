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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;

/**
 * Bloc arrondi bleu.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class BlocArrondiBleu extends Composite implements HasWidgets {

    private static final String HAUTEUR_ARRONDI = "37px";

    private FlexTable flexTable;

    private SimplePanel conteneurWidget;

    /**
     * Constructeur.
     */
    public BlocArrondiBleu() {
        flexTable = new FlexTable();
        flexTable.setCellPadding(0);
        flexTable.setCellSpacing(0);
        flexTable.setBorderWidth(0);
        flexTable.setSize(AppControllerConstants.POURCENT_100, AppControllerConstants.POURCENT_100);

        conteneurWidget = new SimplePanel();
        conteneurWidget.setSize(AppControllerConstants.POURCENT_100, AppControllerConstants.POURCENT_100);

        final FlexTable footer = new FlexTable();
        footer.setBorderWidth(0);
        footer.setCellPadding(0);
        footer.setCellSpacing(0);
        footer.setSize(AppControllerConstants.POURCENT_100, HAUTEUR_ARRONDI);
        footer.setWidget(0, 0, new Label());
        footer.setWidget(0, 1, new Label());
        footer.getCellFormatter().setStylePrimaryName(0, 0, SquareResources.INSTANCE.css().blocArrondiBleuFooterLeft());
        footer.getCellFormatter().setStylePrimaryName(0, 1, SquareResources.INSTANCE.css().blocArrondiBleuFooterRight());
        footer.getCellFormatter().setWidth(0, 1, HAUTEUR_ARRONDI);

        flexTable.setWidget(0, 0, conteneurWidget);
        flexTable.setWidget(1, 0, footer);
        flexTable.getCellFormatter().setStylePrimaryName(0, 0, SquareResources.INSTANCE.css().blocArrondiBleuContenu());
        flexTable.getCellFormatter().setHeight(0, 0, AppControllerConstants.POURCENT_100);
        flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);

        this.initWidget(flexTable);
    }

    /**
     * Constructeur.
     * @param contenu le contenu
     */
    public BlocArrondiBleu(Widget contenu) {
        this();
        this.add(contenu);
    }

    @Override
    public void add(Widget contenu) {
        conteneurWidget.add(contenu);
        contenu.setWidth(AppControllerConstants.POURCENT_100);
    }

    @Override
    public void clear() {
        conteneurWidget.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return conteneurWidget.iterator();
    }

    @Override
    public boolean remove(Widget widget) {
        return conteneurWidget.remove(widget);
    }
}
