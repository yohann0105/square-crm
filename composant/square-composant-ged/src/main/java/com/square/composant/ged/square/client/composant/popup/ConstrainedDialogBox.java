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

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.ui.DialogBox;

/**
 * Boite de dialogue contrainte sur un panel précis (ne peut se déplacer en dehors, se centre par rapport, ...
 * @author jgoncalves - SCUB
 */
public class ConstrainedDialogBox extends DialogBox {
    private Element panelContrainte;
    private boolean dragging;
    private int dragStartX, dragStartY;

    /**
     * Constructeur.
     * @param autoHide .
     * @param modal .
     */
    public ConstrainedDialogBox(boolean autoHide, boolean modal) {
        super(autoHide, modal);
    }

    @Override
    protected void continueDragging(MouseMoveEvent event) {
        if (panelContrainte != null) {
            if (dragging) {
                final int absX = event.getX() + getAbsoluteLeft();
                final int absY = event.getY() + getAbsoluteTop();
                final int posX = absX - dragStartX < panelContrainte.getAbsoluteLeft() ? panelContrainte.getAbsoluteLeft()
                        : (absX - dragStartX + getOffsetWidth() > panelContrainte.getAbsoluteRight()
                                ? panelContrainte.getAbsoluteRight() - getOffsetWidth() : absX - dragStartX);
                final int posY = absY - dragStartY + getOffsetHeight() > panelContrainte.getAbsoluteBottom()
                            ? panelContrainte.getAbsoluteBottom() - getOffsetHeight()
                                : (absY - dragStartY < panelContrainte.getAbsoluteTop() ? panelContrainte.getAbsoluteTop() : absY - dragStartY);
                setPopupPosition(posX, posY);
            }
        } else {
            super.continueDragging(event);
        }
    }

    @Override
    protected void beginDragging(MouseDownEvent event) {
        this.dragging = true;
        dragStartX = event.getX();
        dragStartY = event.getY();
        super.beginDragging(event);
    }

    @Override
    protected void endDragging(MouseUpEvent event) {
        this.dragging = false;
        super.endDragging(event);
    }

    @Override
    public void center() {
        if (panelContrainte != null && (panelContrainte.getAbsoluteLeft() > 0 || panelContrainte.getAbsoluteTop() > 0)) {
            setPopupPosition((panelContrainte.getAbsoluteLeft() + panelContrainte.getClientWidth() / 2) - getOffsetWidth() / 2,
                panelContrainte.getAbsoluteTop() + panelContrainte.getClientHeight() / 2 - getOffsetHeight() / 2);
        } else {
            super.center();
        }
    }

    /**
     * Définition de panelContrainte.
     * @param panelContrainte the panelContrainte to set
     */
    public void setPanelContrainte(Element panelContrainte) {
        this.panelContrainte = panelContrainte;
    }

    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.DialogBox#show()
     */
    @Override
    public void show() {
        super.show();
    }
}
