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
package com.square.client.gwt.client.view.actions;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.presenter.actions.ActionsPresenter.ActionsView;

/**
 * Vue associé à la gestion des actions.
 * @author Goumars Stephane (Scub) - SCUB
 */
public class ActionsViewImpl extends Composite implements ActionsView {

    /** Bouton ajouter personne physique. */
    private DecoratedButton boutonAjouterPersonne;

    /** Boutton ajouter campagne. */
    private DecoratedButton boutonAjouterCampagne;

    /** View constants. */
    private ActionsViewImplConstants viewConstants;

    /** View constants. */
    private ActionsViewImplDebugIdConstants viewDebugIdConstants;

    private VerticalPanel panel;

    /**
     * Constructeur.
     */
    public ActionsViewImpl() {
        this.viewConstants = (ActionsViewImplConstants) GWT.create(ActionsViewImplConstants.class);
        this.viewDebugIdConstants = (ActionsViewImplDebugIdConstants) GWT.create(ActionsViewImplDebugIdConstants.class);
        final Label titrePanel = new Label(viewConstants.titrePanel());
        titrePanel.addStyleName(SquareResources.INSTANCE.css().titrePanelDroite());
        this.boutonAjouterPersonne = new DecoratedButton(viewConstants.libelleBoutonAjouterPersonne());
        this.boutonAjouterPersonne.ensureDebugId(viewDebugIdConstants.debugIdBoutonAjouterPersonne());
        this.boutonAjouterCampagne = new DecoratedButton(viewConstants.libelleBouttonAjouterCampagne());
        this.boutonAjouterCampagne.ensureDebugId(viewDebugIdConstants.debugIdBoutonAjouterCampagne());

        panel = new VerticalPanel();
        panel.add(titrePanel);
        panel.add(boutonAjouterPersonne);
        panel.add(boutonAjouterCampagne);

        panel.addStyleName(SquareResources.INSTANCE.css().blocPanelDroite());
        panel.setWidth(AppControllerConstants.POURCENT_100);
        panel.setSpacing(10);
        panel.setCellHorizontalAlignment(boutonAjouterPersonne, HasAlignment.ALIGN_CENTER);
        panel.setCellHorizontalAlignment(boutonAjouterCampagne, HasAlignment.ALIGN_CENTER);

        initWidget(panel);
        addStyleName(SquareResources.INSTANCE.css().panelActions());
    }

    @Override
    public HasClickHandlers getAjouterPersonneHandler() {
        return boutonAjouterPersonne;
    }

    @Override
    public HasClickHandlers getAjouterCampagneHandler() {
        return boutonAjouterCampagne;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void setAjouterCampagneVisible(boolean visible) {
        boutonAjouterCampagne.setVisible(visible);
    }

    @Override
    public HasWidgets getNewContextMenuSlot() {
        final SimplePanel slot = new SimplePanel();
        panel.add(slot);
        return slot;
    }
}
