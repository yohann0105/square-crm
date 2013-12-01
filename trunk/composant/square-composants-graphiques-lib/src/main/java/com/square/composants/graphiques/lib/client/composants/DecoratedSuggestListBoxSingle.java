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

import org.scub.foundation.framework.gwt.module.client.util.HasStringValue;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedFocusWidget;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasStringValueChangeHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.StringValueChangeEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleResource;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.square.composants.graphiques.lib.client.bundle.SquareLibSuggestListBoxSingleResource;

/**
 * SuggestListBox décorée.
 * @param <TypeResult> le type de l'objet manipulé
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class DecoratedSuggestListBoxSingle<TypeResult> extends DecoratedFocusWidget implements HasSuggestListBoxHandler<TypeResult>, HasValue<TypeResult>,
        HasStringValue, HasStringValueChangeHandler {

    private SuggestListBoxSingle<TypeResult> suggestListBox;

    private static final String STYLENAME = "decoratedSuggestListBoxSingle";

    /**
     * Constructeur du composant suggestListBox décoré.
     * @param properties les properties
     */
    public DecoratedSuggestListBoxSingle(SuggestListBoxSingleProperties<TypeResult> properties) {
        this(properties, SquareLibSuggestListBoxSingleResource.INSTANCE);
    }

    /**
     * Constructeur du composant suggestListBox décoré.
     * @param properties les properties
     * @param resource les resources
     */
    public DecoratedSuggestListBoxSingle(SuggestListBoxSingleProperties<TypeResult> properties, SuggestListBoxSingleResource resource) {
        super(STYLENAME);
        suggestListBox = new SuggestListBoxSingle<TypeResult>(properties, resource);
        setComposant(suggestListBox);
    }

    /** {@inheritDoc} */
    public TypeResult getValue() {
        return suggestListBox.getValue();
    }

    @Override
    public HandlerRegistration addSuggestHandler(SuggestListBoxSuggestEventHandler<TypeResult> handler) {
        return suggestListBox.addSuggestHandler(handler);
    }

    @Override
    public void setValue(TypeResult value) {
        suggestListBox.setValue(value);
    }

    @Override
    public void setValue(TypeResult value, boolean fireEvents) {
        suggestListBox.setValue(value, fireEvents);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<TypeResult> handler) {
        return suggestListBox.addValueChangeHandler(handler);
    }

    @Override
    public String getStringValue() {
        return suggestListBox.getStringValue();
    }

    /**
     * Effacer le contenu du composant.
     */
    public void clean() {
        suggestListBox.setText("");
        suggestListBox.setValue(null);
    }

    /**
     * Permet de mettre le focus.
     * @param focus flag pour savoir si on met le focus
     */
    public void setFocus(boolean focus) {
        suggestListBox.setFocus(focus);
    }

    @Override
    protected void onEnsureDebugId(String baseID) {
        suggestListBox.ensureDebugId(baseID);
    }

    @Override
    public HandlerRegistration addStringValueChangeEventHandler(StringValueChangeEventHandler handler) {
        return suggestListBox.addStringValueChangeEventHandler(handler);
    }

    /**
     * Définit le nombre de caractères maximum pour la textbox.
     * @param maxLenght le nombre de caractères maximum
     */
    public void setMaxLenght(int maxLenght) {
        suggestListBox.setMaxLenght(maxLenght);
    }
}
