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

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.emails.square.client.content.i18n.ComposantEmailsConstants;
import com.square.composant.emails.square.client.presenter.emails.GroupeEmailsPresenter.HeaderEmailView;

/**
 * Implémentation de la vue affichant le header d'un email.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class HeaderEmailViewImpl extends Composite implements HeaderEmailView {

    private HTML resumeEmail;

    private Label dateEnvoiEmail;

    /**
     * Constructeur.
     */
    public HeaderEmailViewImpl() {
        super();
        final Grid container = new Grid(1, 2);
        container.setWidth(ComposantEmailsConstants.POURCENT_100);
        resumeEmail = new HTML();
        dateEnvoiEmail = new Label();
        container.setWidget(0, 0, resumeEmail);
        container.setWidget(0, 1, dateEnvoiEmail);
        container.getColumnFormatter().setWidth(1, "105px");
        container.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
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
    public HasText getDateEnvoiEmail() {
        return dateEnvoiEmail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasHTML getResumeEmail() {
        return resumeEmail;
    }

}
