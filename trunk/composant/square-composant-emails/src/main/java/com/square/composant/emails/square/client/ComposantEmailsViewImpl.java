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
package com.square.composant.emails.square.client;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.emails.square.client.ComposantEmailsController.ComposantEmailsView;

/**
 * Implémentation de la vue générale du composant.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class ComposantEmailsViewImpl extends VerticalPanel implements ComposantEmailsView {

    private SimplePanel slotListeVue;
    private SimplePanel slotGroupeVue;

    /**
     * Constructeur.
     */
    public ComposantEmailsViewImpl() {
        super();
        slotListeVue = new SimplePanel();
        slotGroupeVue = new SimplePanel();
        slotGroupeVue.setVisible(false);
        this.add(slotListeVue);
        this.add(slotGroupeVue);
        this.setStyleName(ComposantEmailsController.RESOURCES.css().composantEmails());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasWidgets getSlotGroupeEmails() {
        return slotGroupeVue;
    }

    @Override
    public HasWidgets getSlotListeEmails() {
        return slotListeVue;
    }

    @Override
    public void switchVue() {
        slotListeVue.setVisible(!slotListeVue.isVisible());
        slotGroupeVue.setVisible(!slotGroupeVue.isVisible());
    }

}
