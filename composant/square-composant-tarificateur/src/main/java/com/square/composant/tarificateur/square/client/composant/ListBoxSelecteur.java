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

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;

/**
 * Un ListBox qui encapsule un objGwt du sélecteur et change sa valeur en fonction de ses évènements.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public abstract class ListBoxSelecteur extends Composite implements ValueChangeHandler<ListBoxItem>, HasValueChangeHandlers<ListBoxItem>,
    HasValue<ListBoxItem>, CritereWidgetAvailable {

    private SelecteurProduitEventMapper<String> objSelecteur;

    private DecoratedSuggestListBoxSingle<ListBoxItem> listBox;

    /**
     * Constructeur.
     * @param objSelecteur objet du selecteur à mettre a jour
     * @param listBox liste box contenant les valeurs
     */
    public ListBoxSelecteur(final SelecteurProduitEventMapper<String> objSelecteur, DecoratedSuggestListBoxSingle<ListBoxItem> listBox) {
        this.objSelecteur = objSelecteur;
        this.listBox = listBox;

        // sélection de la valeur si elle existe
        listBox.setValue(getItemValue(objSelecteur.getValeurMapper()));
        setValeur();

        listBox.addValueChangeHandler(this);

        initWidget(listBox);
    }

    /**
     * Recupere un item à partir d'un string valeur.
     * @param valeur la valeur string
     * @return l'item
     */
    public abstract ListBoxItem getItemValue(String valeur);

    /**
     * {@inheritDoc}
     */
    public void addStyleName(String styleName) {
        listBox.addStyleName(styleName);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEnabled() {
        return listBox.isEnabled();
    }

    /**
     * {@inheritDoc}
     */
    public void setEnabled(boolean enabled) {
        listBox.setEnabled(enabled);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isVisible() {
        return listBox.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    public void setVisible(boolean visible) {
        listBox.setVisible(visible);
    }

    /**
     * Définit la valeur.
     */
    private void setValeur() {
        objSelecteur.setValeurMapper(listBox.getValue() != null ? listBox.getValue().getIdentifiant() : null);
    }

    @Override
    public ListBoxItem getValue() {
        return listBox.getValue();
    }

    @Override
    public void onValueChange(ValueChangeEvent<ListBoxItem> event) {
        setValeur();
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<ListBoxItem> handler) {
        return listBox.addValueChangeHandler(handler);
    }

    /**
     * Definit un item à partir d'un string.
     * @param valeur la valeur
     */
    public void setValue(String valeur) {
        listBox.setValue(getItemValue(valeur));
        setValeur();
    }

    @Override
    public void setValue(ListBoxItem value) {
        listBox.setValue(value);
        setValeur();
    }

    @Override
    public void setValue(ListBoxItem value, boolean fireEvents) {
        listBox.setValue(value, fireEvents);
        setValeur();
    }

    @Override
    public Widget getCritereWidget() {
        return this;
    }

    @Override
    public String getValeurCritereWidget() {
        return listBox.getValue().getIdentifiant();
    }

    @Override
    public void setValeurCritereWidget(String valeur) {
        listBox.setValue(getItemValue(valeur));
    }
}
