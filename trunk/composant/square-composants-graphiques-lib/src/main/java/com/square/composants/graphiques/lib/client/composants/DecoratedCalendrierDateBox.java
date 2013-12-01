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

import java.util.Date;

import org.scub.foundation.framework.gwt.module.client.util.composants.calendrier.CalendrierDateBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.calendrier.DateInvalideEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.calendrier.HasDateInvalideHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedFocusWidget;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.square.composants.graphiques.lib.client.bundle.SquareLibCalendrierDateBoxImages;

/**
 * Calendar décoré.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class DecoratedCalendrierDateBox extends DecoratedFocusWidget implements HasAllKeyHandlers, HasValueChangeHandlers<Date>, HasDateInvalideHandler,
        HasChangeHandlers, HasValue<Date> {

    private CalendrierDateBox calendrier;

    private static final String STYLENAME = "decoratedCalendrierDateBox";

    /**
     * Constructeur du composant textbox décoré.
     */
    public DecoratedCalendrierDateBox() {
        this(false);
    }

    /**
     * Constructeur du composant textbox décoré.
     * @param mettreDateInvalideANull indique que si une date est invalide, la date est passé à null
     */
    public DecoratedCalendrierDateBox(boolean mettreDateInvalideANull) {
        super(STYLENAME);
        calendrier = new CalendrierDateBox(SquareLibCalendrierDateBoxImages.INSTANCE, mettreDateInvalideANull);
        setComposant(calendrier.getDateBox());
        flexTable.setWidget(1, 3, calendrier.getImage());
    }

    /**
     * Retourne la date.
     * @return la date
     * @deprecated utiliser getValue
     */
    @Deprecated
    public Date getDate() {
        return calendrier.getDate();
    }

    /**
     * Modifie la date.
     * @param date la nouvelle date
     * @deprecated utiliser setValue
     */
    @Deprecated
    public void setDate(Date date) {
        calendrier.setDate(date);
    }

    /**
     * Désactive le calendrier.
     * @param enabled le boulean pour la désactivation
     */
    public void setEnabled(boolean enabled) {
        this.calendrier.setEnabled(enabled);
    }

    /**
     * Active le focus.
     * @param focused le focus
     */
    public void setFocus(boolean focused) {
        this.calendrier.getDateBox().setFocus(focused);
    }

    /**
     * ReInitialisation du Calendar.
     */
    public void clean() {
        calendrier.clean();
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return calendrier.addKeyUpHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return calendrier.addKeyDownHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return calendrier.addKeyPressHandler(handler);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
        return this.calendrier.addValueChangeHandler(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEnsureDebugId(String baseID) {
        calendrier.getDateBox().ensureDebugId(baseID);
    }

    @Override
    public HandlerRegistration addDateInvalideEventHandler(DateInvalideEventHandler handler) {
        return calendrier.addDateInvalideEventHandler(handler);
    }

    /**
     * Teste si la date indiquée dans la textBox est valide.
     * @return true si la date est valide
     */
    public boolean isDateValide() {
        return calendrier.isDateValide();
    }

    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return calendrier.addChangeHandler(handler);
    }

    @Override
    public Date getValue() {
        return calendrier.getValue();
    }

    @Override
    public void setValue(Date value) {
        calendrier.setValue(value);
    }

    @Override
    public void setValue(Date value, boolean fireEvents) {
        calendrier.setValue(value, fireEvents);
    }

}
