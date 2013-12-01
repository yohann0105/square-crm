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
package com.square.client.gwt.client.view.action.annulation;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.presenter.action.ActionPopupAnnulerPresenter.ActionPopupAnnulerView;

public class ActionPopupAnnulerViewImpl extends Popup implements ActionPopupAnnulerView {

    /** View constants. */
    private static ActionPopupAnnulerViewImplConstants viewConstants =
        (ActionPopupAnnulerViewImplConstants) GWT.create(ActionPopupAnnulerViewImplConstants.class);

    private static ActionPopupAnnulerViewImplDebugIdConstants viewDebugIdConstants =
        (ActionPopupAnnulerViewImplDebugIdConstants) GWT.create(ActionPopupAnnulerViewImplDebugIdConstants.class);

    /** Conteneur principale. */
    private VerticalPanel conteneur;

    /** Bouton Ok. */
    private DecoratedButton btnOui;

    /** Bouton Refus. */
    private DecoratedButton btnNon;

    /** Constructeur. */
    public ActionPopupAnnulerViewImpl() {
        super(viewConstants.popupTitle(), false, false, true);
        conteneur = new VerticalPanel();
        final Label lAnnonce = new Label(viewConstants.libelleAnnonce());
        conteneur.add(lAnnonce);
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        btnOui = new DecoratedButton(viewConstants.oui());
        btnOui.ensureDebugId(viewDebugIdConstants.debugIdBtnOui());
        btnNon = new DecoratedButton(viewConstants.non());
        btnNon.ensureDebugId(viewDebugIdConstants.debugIdBtnOui());
        horizontalPanel.add(btnOui);
        horizontalPanel.add(btnNon);
        horizontalPanel.setSpacing(5);
        conteneur.add(horizontalPanel);
        conteneur.setCellHorizontalAlignment(horizontalPanel, HasAlignment.ALIGN_CENTER);
        this.setWidget(conteneur);
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public void showPopup() {
        this.show();
    }

    @Override
    public HasClickHandlers getBtnOui() {
        return btnOui;
    }

    @Override
    public HasClickHandlers getBtnNon() {
        return btnNon;
    }

    @Override
    public void masquerPopup() {
        this.hide();
    }

}
