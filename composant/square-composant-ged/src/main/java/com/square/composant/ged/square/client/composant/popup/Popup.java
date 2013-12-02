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
package com.square.composant.ged.square.client.composant.popup;

import org.scub.foundation.framework.gwt.module.client.util.popup.PopupStateChangeEvent;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.client.GlassPanel;

/**
 * Popup.
 * @author jgoncalves - SCUB
 */
public class Popup extends Composite {
    /** 100%. */
    public static final String POURCENT_100 = "100%";

    /** POPUP_WIDTH_DEFAULT. */
    public static final int POPUP_WIDTH_DEFAULT = 350;

    /** POPUP_WIDTH_SMALL. */
    public static final int POPUP_WIDTH_SMALL = 270;

    /** POPUP_WIDTH_LARGE. */
    public static final int POPUP_WIDTH_LARGE = 450;

    private Widget widget;

    private GlassPanel glassPanel;

    private ConstrainedDialogBox dialogBox;

    private PopupCallback callback;

    /**
     * Le callback de la popup.
     */
    public interface PopupCallback {
        /**
         * Méthode appelée sur le retour de la popup.
         * @param result une constante représentant le résultat (CF chaque type de popup pour la signification de chaque résultat
         */
        void onResult(int result);
    }

    /**
     * Constructeur.
     * @param title title
     * @param autoHide autoHide
     * @param modal modal
     * @param hasGlassPanel glasspanel
     */
    public Popup(String title, boolean autoHide, boolean modal, boolean hasGlassPanel) {
        dialogBox = new ConstrainedDialogBox(autoHide, modal);
        dialogBox.setText(title);
        dialogBox.setAnimationEnabled(true);

        if (hasGlassPanel) {
            // on ajoute un glass panel
            glassPanel = new GlassPanel(false);
        }

        final VerticalPanel panel = new VerticalPanel();
        dialogBox.setWidget(panel);
        panel.setSpacing(10);
    }

    /**
     * Modifie la position de la popup.
     * @param left .
     * @param top .
     */
    public void setPosition(int left, int top) {
        dialogBox.setPopupPosition(left, top);
    }

    /**
     * Affiche la popup à une position donnée.
     * @param left left
     * @param top top
     */
    public void showOnPosition(final int left, final int top) {
        if (glassPanel != null) {
            // Attach (display) the glass panel
            RootPanel.get().add(glassPanel, 0, 0);
        }
        dialogBox.setPopupPosition(left, top);
        dialogBox.show();
        fireEvent(new PopupStateChangeEvent(true));
    }

    /**
     * Affiche la popup.
     */
    public void show() {
        if (glassPanel != null) {
            // Attach (display) the glass panel
            RootPanel.get().add(glassPanel, 0, 0);
        }
        dialogBox.show();
        dialogBox.center();
    }

    /**
     * A la fermeture de la popup.
     */
    public void hide() {
        if (glassPanel != null) {
            glassPanel.removeFromParent();
        }
        dialogBox.hide();
    }

    /** {@inheritDoc} */
    public void setWidget(Widget w) {
        setWidget(w, null);
    }

    /** {@inheritDoc} */
    public void setWidget(Widget w, final Integer spacing) {
        final VerticalPanel layoutPanel = (VerticalPanel) dialogBox.getWidget();
        layoutPanel.setWidth(POURCENT_100);
        if (spacing != null) {
            layoutPanel.setSpacing(spacing);
        }
        if (widget != w) {
            if (widget != null) {
                layoutPanel.remove(widget);
            }
            widget = w;
            layoutPanel.add(widget);
        }
    }

    /** {@inheritDoc} */
    public void addStyleName(String style) {
        dialogBox.addStyleName(style);
    }

    /** {@inheritDoc} */
    public void setWidth(String width) {
        dialogBox.setWidth(width);
    }

    /**
     * Ajoute un style au glass panel.
     * @param style le style
     */
    public void setGlassPanelStyleName(String style) {
        if (glassPanel != null) {
            glassPanel.addStyleName(style);
        }
    }

    /**
     * Sets the text inside the caption. Use {@link #setWidget(Widget)} to set the contents inside the {@link DialogBox}.
     * @param text the object's new text
     */
    public void setText(String text) {
        dialogBox.setText(text);
    }

    /**
     * Définition d'un panel comme contrainte pour le positionnement / déplacement.
     * @param panelContrainte le panel
     */
    public void setPanelContrainte(Widget panelContrainte) {
        if (panelContrainte != null) {
            dialogBox.setPanelContrainte(panelContrainte.getElement());
        }
    }

    /**
     * Récupération de callback.
     * @return the callback
     */
    public PopupCallback getCallback() {
        return callback;
    }

    /**
     * Définition de callback.
     * @param callback the callback to set
     */
    public void setCallback(PopupCallback callback) {
        this.callback = callback;
    }
}
