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
package com.square.composants.graphiques.lib.client.composants;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetRequestSuggestionsEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetSelectedItemEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedFocusWidget;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBox;

import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.square.composants.graphiques.lib.client.bundle.SquareLibExtendedSuggestBoxImages;

/**
 * SuggestListBox décorée.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
@SuppressWarnings("deprecation")
public class DecoratedSuggestListBox extends DecoratedFocusWidget implements HasAllKeyHandlers {

    private SuggestListBox suggestListBox = new SuggestListBox(SquareLibExtendedSuggestBoxImages.INSTANCE);

    private static final String STYLENAME = "decoratedSuggestListBox";

    /**
     * Constructeur du composant suggestListBox décoré.
     */
    public DecoratedSuggestListBox() {
        super(STYLENAME);
        setComposant(suggestListBox);
    }

    /**
     * Récupère l'item selectionné de la suggestListBox.
     * @return l'item selectionné de la suggestListBox
     */
    public IdentifiantLibelleGwt getSelectedItem() {
        return suggestListBox.getSelectedItem();
    }

    /**
     * {@inheritDoc}
     */
    public void addRequestSuggestionsHandler(SetRequestSuggestionsEventHandler handler) {
        suggestListBox.addRequestSuggestionsHandler(handler);
    }

    /**
     * {@inheritDoc}
     */
    public void addSetSelectedItemHandler(SetSelectedItemEventHandler handler) {
        suggestListBox.addSetSelectedItemHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return suggestListBox.addKeyUpHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return suggestListBox.addKeyDownHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return suggestListBox.addKeyPressHandler(handler);
    }

    @Override
    protected void onEnsureDebugId(String baseID) {
        suggestListBox.ensureDebugId(baseID);
    }
}
