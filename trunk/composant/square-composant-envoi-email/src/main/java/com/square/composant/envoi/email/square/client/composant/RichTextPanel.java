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
package com.square.composant.envoi.email.square.client.composant;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.envoi.email.square.client.presenter.ComposantEnvoiEmailPresenter;

/**
 * Panel complet contenant le RichTextToolbar et le RichTextArea.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class RichTextPanel extends VerticalPanel {

    private RichTextToolbar richTextToolbar;
    private RichTextArea richTextArea;

    /**
     * The constants used in this Page.
     */
    public interface PageConstants extends Constants {

        /** Hauteur par défaut de l'area. */
        String DEFAULT_AREA_HEIGHT = "150px";

        /** Largeur par défaut de la toolbar. */
        String DEFAULT_TOOLBAR_WIDTH = "495px";

        /** Cent pourcent. */
        String CENT_POURCENT = "100%";
    }

    /**
     * Constructeur d'un panel à partir d'une toolbar et d'une area.
     * @param richTextToolbar la toolbar
     * @param richTextArea l'area
     */
    public RichTextPanel(RichTextToolbar richTextToolbar, RichTextArea richTextArea) {
        this(richTextToolbar, richTextArea, PageConstants.DEFAULT_AREA_HEIGHT);
    }

    /**
     * Constructeur d'un panel à partir d'une toolbar.
     * @param richTextToolbar la toolbar
     * @param richTextArea l'area
     * @param height la hauteur de l'area
     */
    public RichTextPanel(RichTextToolbar richTextToolbar, RichTextArea richTextArea, String height) {
        this(richTextToolbar, richTextArea, height, PageConstants.DEFAULT_TOOLBAR_WIDTH);
    }

    /**
     * Constructeur d'un panel à partir d'une toolbar.
     * @param richTextToolbar la toolbar
     * @param richTextArea l'area
     * @param height la hauteur de l'area
     * @param width la largeur de la toolbar
     */
    public RichTextPanel(RichTextToolbar richTextToolbar, RichTextArea richTextArea, String height, String width) {
        this.richTextArea = richTextArea;
        this.richTextToolbar = richTextToolbar;
        this.addStyleName(ComposantEnvoiEmailPresenter.RESSOURCES.css().richTextPanel());
        // Create the text area
        richTextArea.setSize(PageConstants.CENT_POURCENT, height);
        richTextArea.setHeight(height);
        richTextToolbar.setWidth(width);
        this.add(richTextToolbar);
        this.add(richTextArea);
    }

    /**
     * Permet d'activer ou désactiver la toolbar.
     * @param enabled flag true ou false
     */
    public void setEnabled(boolean enabled) {
        // on desactive la toolbar
        richTextToolbar.setEnabled(enabled);
        // on desactive la textarea
        richTextArea.setEnabled(enabled);
    }

}