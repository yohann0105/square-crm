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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.square.composant.tarificateur.square.client.model.selecteur.AssureSelecteurModel;

/**
 * Un Checkbox qui encapsule un assuré et change une de ses valeurs en fonction de ses évènements.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CheckBoxAssureSelecteur extends Composite implements HasClickHandlers, HasValue<Boolean>, ClickHandler {

    private AssureSelecteurModel objSelecteur;

    private CheckBox checkBox;

    /**
     * Constructeur.
     * @param objSelecteur objet du selecteur à mettre a jour
     */
    public CheckBoxAssureSelecteur(final AssureSelecteurModel objSelecteur) {
        this.objSelecteur = objSelecteur;
        this.checkBox = new CheckBox();

        // sélection de la valeur si elle existe
        if (objSelecteur != null && (Boolean) objSelecteur.getIsSelection() != null) {
            this.checkBox.setValue(objSelecteur.getIsSelection());
        }
        // sinon on met a jour la valeur
        else {
            setValeur();
        }

        this.checkBox.addClickHandler(this);

        initWidget(checkBox);
    }

    /**
     * {@inheritDoc}
     */
    public void addStyleName(String styleName) {
        checkBox.addStyleName(styleName);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEnabled() {
        return checkBox.isEnabled();
    }

    /**
     * {@inheritDoc}
     */
    public void setEnabled(boolean enabled) {
        checkBox.setEnabled(enabled);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isVisible() {
        return checkBox.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    public void setVisible(boolean visible) {
        checkBox.setVisible(visible);
    }

    /**
     * Définit la valeur.
     */
    private void setValeur() {
        objSelecteur.setIsSelection(checkBox.getValue());
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return checkBox.addClickHandler(handler);
    }

    @Override
    public Boolean getValue() {
        return checkBox.getValue();
    }

    @Override
    public void setValue(Boolean value) {
        checkBox.setValue(value);
        setValeur();
    }

    @Override
    public void setValue(Boolean value, boolean fireEvents) {
        checkBox.setValue(value, fireEvents);
        setValeur();
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
        return checkBox.addValueChangeHandler(handler);
    }

    @Override
    public void onClick(ClickEvent event) {
        setValeur();
    }

}
