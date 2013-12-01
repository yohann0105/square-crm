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

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.emails.square.client.ComposantEmailsController;
import com.square.composant.emails.square.client.content.i18n.ComposantEmailsConstants;
import com.square.composant.emails.square.client.presenter.emails.GroupeEmailsPresenter.CorpsEmailView;

/**
 * Implémentation de la vue affichant le corps d'un email.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class CorpsEmailViewImpl extends HTML implements CorpsEmailView {

    /**
     * Constructeur.
     */
    public CorpsEmailViewImpl() {
        super();
        this.setSize(ComposantEmailsConstants.POURCENT_100, ComposantEmailsConstants.POURCENT_100);
        this.addStyleName(ComposantEmailsController.RESOURCES.css().corpsEmail());
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
    public HasHTML getCorpsEmail() {
        return this;
    }

}
