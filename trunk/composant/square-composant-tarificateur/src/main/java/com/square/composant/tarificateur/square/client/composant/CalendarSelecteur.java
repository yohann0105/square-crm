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

import org.scub.foundation.framework.gwt.module.client.util.DateUtil;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;

/**
 * Un Calendar qui encapsule un assuré et change une de ses valeurs en fonction de ses évènements.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CalendarSelecteur extends Composite implements ChangeHandler, KeyUpHandler, HasChangeHandlers, HasAllKeyHandlers, CritereWidgetAvailable {

    private SelecteurProduitEventMapper<String> objSelecteur;

    private DecoratedCalendrierDateBox calendar;

    /**
     * Constructeur.
     * @param objSelecteur objet du selecteur à mettre a jour
     */
    public CalendarSelecteur(SelecteurProduitEventMapper<String> objSelecteur) {
        this.objSelecteur = objSelecteur;
        this.calendar = new DecoratedCalendrierDateBox();

        // sélection de la valeur si elle existe
        if (objSelecteur != null && objSelecteur.getValeurMapper() != null) {
            calendar.setDate(DateUtil.getDate(objSelecteur.getValeurMapper()));
        }
        // sinon on met a jour la valeur
        else {
            setValeur();
        }

        calendar.addChangeHandler(this);
        calendar.addKeyUpHandler(this);

        initWidget(calendar);
    }

    /**
     * {@inheritDoc}
     */
    public void setEnabled(boolean enabled) {
        calendar.setEnabled(enabled);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isVisible() {
        return calendar.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    public void setVisible(boolean visible) {
        calendar.setVisible(visible);
    }

    /**
     * {@inheritDoc}
     */
    public String getDate() {
        return DateUtil.getString(calendar.getDate());
    }

    /**
     * Définit la valeur.
     */
    private void setValeur() {
        objSelecteur.setValeurMapper(DateUtil.getString(calendar.getDate()));
    }

    /**
     * {@inheritDoc}
     */
    public void setDate(String date) {
        calendar.setDate(DateUtil.getDate(date));
        setValeur();
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
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return calendar.addChangeHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return calendar.addKeyUpHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return calendar.addKeyDownHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return calendar.addKeyPressHandler(handler);
    }

    @Override
    public Widget getCritereWidget() {
        return this;
    }

    @Override
    public String getValeurCritereWidget() {
        return DateUtil.getString(calendar.getDate());
    }

    @Override
    public boolean isEnabled() {
        return calendar.isEnabled();
    }

    @Override
    public void setValeurCritereWidget(String valeur) {
        calendar.setDate(DateUtil.getDate(valeur));
    }

}
