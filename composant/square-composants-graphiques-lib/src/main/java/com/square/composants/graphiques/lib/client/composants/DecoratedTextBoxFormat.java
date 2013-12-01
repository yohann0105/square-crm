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

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedFocusWidget;
import org.scub.foundation.framework.gwt.module.client.util.composants.textboxformat.TextBoxFormat;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Un TexBox formaté décoré.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class DecoratedTextBoxFormat extends DecoratedFocusWidget implements HasValue<String>, HasAllKeyHandlers, HasAllFocusHandlers, HasChangeHandlers {

    private TextBoxFormat textBoxFormat;

    private static final String STYLENAME = "decoratedTextBox";

    /**
     * Constructeur de .
     * @param format le format du text.
     */
    public DecoratedTextBoxFormat(String format) {
        super(STYLENAME);
        textBoxFormat = new TextBoxFormat(format);
        setComposant(textBoxFormat);
    }

    @Override
    public String getValue() {
        return textBoxFormat.getValue();
    }

    @Override
    public void setValue(String value) {
        textBoxFormat.setValue(value);
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        textBoxFormat.setValue(value, fireEvents);
    }

    /** {@inheritDoc} */
    public int getSelectionLength() {
        return textBoxFormat.getSelectionLength();
    }

    /** {@inheritDoc} */
    public void cancelKey() {
        textBoxFormat.cancelKey();
    }

    /** {@inheritDoc} */
    public int getTabIndex() {
        return textBoxFormat.getTabIndex();
    }

    /** {@inheritDoc} */
    public void setAccessKey(char key) {
        textBoxFormat.setAccessKey(key);
    }

    /** {@inheritDoc} */
    public void setFocus(boolean focused) {
        textBoxFormat.setFocus(focused);
    }

    /** {@inheritDoc} */
    public void setTabIndex(int index) {
        textBoxFormat.setTabIndex(index);
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return textBoxFormat.addKeyUpHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return textBoxFormat.addKeyDownHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return textBoxFormat.addKeyPressHandler(handler);
    }

    @Override
    public HandlerRegistration addFocusHandler(FocusHandler handler) {
        return textBoxFormat.addFocusHandler(handler);
    }

    @Override
    public HandlerRegistration addBlurHandler(BlurHandler handler) {
        return textBoxFormat.addBlurHandler(handler);
    }

    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return textBoxFormat.addChangeHandler(handler);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return textBoxFormat.addValueChangeHandler(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEnsureDebugId(String baseID) {
        textBoxFormat.ensureDebugId(baseID);
    }

    /**
     * Modifie le format de TextBox.
     * @param format le nouveau format.
     */
    public void setFormat(String format) {
        textBoxFormat.setFormat(format);
    }
}
