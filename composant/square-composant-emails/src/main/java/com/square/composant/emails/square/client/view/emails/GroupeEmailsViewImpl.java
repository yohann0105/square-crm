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
package com.square.composant.emails.square.client.view.emails;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.emails.square.client.ComposantEmailsController;
import com.square.composant.emails.square.client.content.i18n.ComposantEmailsConstants;
import com.square.composant.emails.square.client.presenter.emails.GroupeEmailsPresenter.CorpsEmailView;
import com.square.composant.emails.square.client.presenter.emails.GroupeEmailsPresenter.GroupeEmailsView;
import com.square.composant.emails.square.client.presenter.emails.GroupeEmailsPresenter.HeaderEmailView;

/**
 * Implémentation de la vue permettant de consulter un groupe d'emails.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class GroupeEmailsViewImpl extends Composite implements GroupeEmailsView {

    private static final int HAUTEUR_HEADER_STACK_EMAIL = 35;

    private static final GroupeEmailsViewImplConstants VIEW_CONSTANTS = GWT.create(GroupeEmailsViewImplConstants.class);

    private DecoratedButton btnRetour;

    private Label lSujetGroupeEmails;

    private StackLayoutPanel stackEmails;
    private VerticalPanel container;

    /**
     * Constructeur.
     */
    public GroupeEmailsViewImpl() {
        super();
        container = new VerticalPanel();
        container.setWidth(ComposantEmailsConstants.POURCENT_100);
        container.setSpacing(10);

        btnRetour = new DecoratedButton(VIEW_CONSTANTS.hlBackward());

        lSujetGroupeEmails = new Label();
        lSujetGroupeEmails.addStyleName(ComposantEmailsController.RESOURCES.css().sujetEmail());

        stackEmails = new StackLayoutPanel(Unit.PX);
        stackEmails.setSize(ComposantEmailsConstants.POURCENT_100, "600px");

        container.add(btnRetour);
        container.add(lSujetGroupeEmails);
        container.add(stackEmails);
        initWidget(container);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEmail(HeaderEmailView headerEmail, CorpsEmailView corpsEmail) {
        stackEmails.add(corpsEmail.asWidget(), headerEmail.asWidget(), HAUTEUR_HEADER_STACK_EMAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLastEmail() {
        stackEmails.showWidget(stackEmails.getWidgetCount() - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getSujetGroupeEmails() {
        return lSujetGroupeEmails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasClickHandlers getBtnRetour() {
        return btnRetour;
    }

    @Override
    public void nettoyer() {
        stackEmails.clear();
        lSujetGroupeEmails.setText("");
    }

}
