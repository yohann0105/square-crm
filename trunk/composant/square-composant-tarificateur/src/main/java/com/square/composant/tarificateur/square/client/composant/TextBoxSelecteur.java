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
package com.square.composant.tarificateur.square.client.composant;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * Un Textbox qui encapsule un objGwt du sélecteur et change sa valeur en fonction de ses évènements.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class TextBoxSelecteur extends Composite implements ChangeHandler, KeyUpHandler, HasAllKeyHandlers, HasValue<String>, CritereWidgetAvailable {

    private SelecteurProduitEventMapper<String> objSelecteur;

    private DecoratedTextBox textBox;

    /**
     * Constructeur.
     * @param objSelecteur objet du selecteur à mettre a jour
     */
    public TextBoxSelecteur(SelecteurProduitEventMapper<String> objSelecteur) {
        this.objSelecteur = objSelecteur;
        this.textBox = new DecoratedTextBox();

        // sélection de la valeur si elle existe
        if (objSelecteur != null && objSelecteur.getValeurMapper() != null) {
            textBox.setValue(objSelecteur.getValeurMapper());
        }
        // sinon on met a jour la valeur
        else {
            setValeur();
        }

        textBox.addChangeHandler(this);
        textBox.addKeyUpHandler(this);

        initWidget(textBox);
    }

    /**
     * {@inheritDoc}
     */
    public void addStyleName(String styleName) {
        textBox.addStyleName(styleName);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEnabled() {
        return textBox.isEnabled();
    }

    /**
     * {@inheritDoc}
     */
    public void setEnabled(boolean enabled) {
        textBox.setEnabled(enabled);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isVisible() {
        return textBox.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    public void setVisible(boolean visible) {
        textBox.setVisible(visible);
    }

    /**
     * Définit la valeur.
     */
    private void setValeur() {
        objSelecteur.setValeurMapper(textBox.getValue());
    }

    @Override
    public void onChange(ChangeEvent event) {
        setValeur();
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        setValeur();
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return textBox.addKeyUpHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return textBox.addKeyDownHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return textBox.addKeyPressHandler(handler);
    }

    @Override
    public String getValue() {
        return textBox.getValue();
    }

    @Override
    public void setValue(String value) {
        textBox.setValue(value);
        setValeur();
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        textBox.setValue(value, fireEvents);
        setValeur();
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return textBox.addValueChangeHandler(handler);
    }

    @Override
    public Widget getCritereWidget() {
        return this;
    }

    @Override
    public String getValeurCritereWidget() {
        return textBox.getValue();
    }

    @Override
    public void setValeurCritereWidget(String valeur) {
        textBox.setValue(valeur);
    }
}
