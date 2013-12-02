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
package com.square.client.gwt.client.view.personne.relations;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.presenter.personne.PersonneRelationAjouterEnfantConjointPresenter.PersonneRelationAjouterEnfantConjointView;

/**
 * Vue pour la popup de demande d'ajout d'enfant au conjoint.
 * @author Raphaël Marnier - SCUB
 */
public class PersonneRelationAjouterEnfantConjointViewImpl extends Popup implements PersonneRelationAjouterEnfantConjointView {

    /** View constants. */
    private static PersonneRelationAjouterEnfantConjointViewImplConstants viewConstants =
        (PersonneRelationAjouterEnfantConjointViewImplConstants) GWT.create(PersonneRelationAjouterEnfantConjointViewImplConstants.class);

	/** Conteneur principal. */
    private VerticalPanel conteneur;

    /** Bouton Ok. */
    private DecoratedButton btnOui;

    /** Bouton Refus. */
    private DecoratedButton btnNon;

	/** Constructeur. */
	public PersonneRelationAjouterEnfantConjointViewImpl() {
		super(viewConstants.popupTitle(), false, false, true);
		conteneur = new VerticalPanel();
		final Label lAnnonce = new Label(viewConstants.libelleAnnonce());
        conteneur.add(lAnnonce);
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		btnOui = new DecoratedButton(viewConstants.oui());
        btnNon = new DecoratedButton(viewConstants.non());
        horizontalPanel.add(btnOui);
        horizontalPanel.add(btnNon);
        horizontalPanel.setSpacing(5);
        conteneur.add(horizontalPanel);
        conteneur.setCellHorizontalAlignment(horizontalPanel, HasAlignment.ALIGN_CENTER);
        this.setWidget(conteneur);
	}

	@Override
	public HasClickHandlers getBtnNon() {
		return btnNon;
	}

	@Override
	public HasClickHandlers getBtnOui() {
		return btnOui;
	}

	@Override
	public void masquerPopup() {
		this.hide();
	}

	@Override
	public void showPopup() {
		this.show();
	}

	@Override
	public Widget asWidget() {
		return null;
	}

}
