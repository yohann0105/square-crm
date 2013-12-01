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
package com.square.client.gwt.client.composant.popup;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.popup.PopupFusionPresenter.PopupFusionView;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;
import com.square.composants.graphiques.lib.client.popups.minimizable.PopupMinimizable;

/**
 * Implémentation vue popup fusion.
 * @author jgoncalves - SCUB
 */
public class PopupFusionViewImpl extends Popup implements PopupFusionView {

    /** Constantes de la popup. */
    private static PopupFusionConstants popupConstants = GWT.create(PopupFusionConstants.class);
    private DecoratedButton btnReduire;
    private PopupMinimizable minimizablePopup;
    private DecoratedButton btnFermer;
    private VerticalPanel pConteneurComposantFusion;

    public PopupFusionViewImpl() {
        super(popupConstants.titrePopup(), false, false, true);
        this.addStyleName(SquareResources.INSTANCE.css().popupFusion());

        final VerticalPanel pConteneur = new VerticalPanel();
        pConteneur.setWidth(PopupFusionConstants.LARGEUR_POPUP);
        pConteneur.setSpacing(5);

        pConteneurComposantFusion = new VerticalPanel();
        pConteneurComposantFusion.setWidth(AppControllerConstants.POURCENT_100);

        btnFermer = new DecoratedButton(popupConstants.btnFermer());
        btnReduire = new DecoratedButton(popupConstants.reduire());

        final HorizontalPanel conteneurBoutons = new HorizontalPanel();
        conteneurBoutons.add(btnReduire);
        conteneurBoutons.add(btnFermer);
        conteneurBoutons.setSpacing(5);

        pConteneur.add(pConteneurComposantFusion);
        pConteneur.add(conteneurBoutons);
        pConteneur.setCellHorizontalAlignment(conteneurBoutons, HasAlignment.ALIGN_CENTER);

        this.setWidget(pConteneur, 0);

        // on en fait une popup minimisable
        minimizablePopup = new PopupMinimizable(this, popupConstants.titrePopup(), btnReduire);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public DecoratedButton getBtnFermer() {
        return btnFermer;
    }

    @Override
    public DecoratedButton getBtnReduire() {
        return btnReduire;
    }

    @Override
    public IsMinimizable getMinimizablePopup() {
        return minimizablePopup;
    }

    @Override
    public void hidePopup() {
        hide();
    }

    @Override
    public void showPopup() {
        show();
    }

    @Override
    public HasWidgets getConteneurComposantFusion() {
        return pConteneurComposantFusion;
    }

}
