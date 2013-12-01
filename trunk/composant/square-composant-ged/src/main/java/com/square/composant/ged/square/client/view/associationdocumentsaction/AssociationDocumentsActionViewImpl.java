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
package com.square.composant.ged.square.client.view.associationdocumentsaction;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.ged.square.client.composant.popup.ErrorPopup;
import com.square.composant.ged.square.client.composant.popup.LoadingPopup;
import com.square.composant.ged.square.client.composant.popup.LoadingPopupConfiguration;
import com.square.composant.ged.square.client.composant.popup.Popup;
import com.square.composant.ged.square.client.presenter.AssociationActionsDocumentsPresenter.AssociationDocumentsActionView;

/**
 * Vue permettant l'association de documents à une action.
 * @author jgoncalves - SCUB
 */
public class AssociationDocumentsActionViewImpl extends Popup implements AssociationDocumentsActionView {

    private static AssociationDocumentsActionViewImplConstants viewConstants = GWT.create(AssociationDocumentsActionViewImplConstants.class);
    private DecoratedButton boutonAnnuler;
    private DecoratedButton boutonAjouter;
    private DecoratedButton boutonEnlever;
    private DecoratedButton boutonAjouterDocument;
    private DecoratedButton boutonEnregistrer;
    private Tree documentsClientsTree;
    private Tree documentsActionTree;

    /** Constructeur par défaut. */
    public AssociationDocumentsActionViewImpl() {
        super(viewConstants.titrePopup(), false, true, true);

        documentsClientsTree = new Tree();
        documentsActionTree = new Tree();
        final FlexTable mainPanel = new FlexTable();

        final ScrollPanel scrollClient = new ScrollPanel();
        scrollClient.add(documentsClientsTree);
        scrollClient.setSize("290px", POURCENT_100);
        final ScrollPanel scrollActions = new ScrollPanel();
        scrollActions.setSize("290px", POURCENT_100);
        scrollActions.add(documentsActionTree);

        final CaptionPanel panelDocumentsClients = new CaptionPanel(viewConstants.titreDocumentsClient());
        panelDocumentsClients.setSize("300px", "300px");
        panelDocumentsClients.add(scrollClient);
        final CaptionPanel panelDocumentsAction = new CaptionPanel(viewConstants.titreDocumentsAction());
        panelDocumentsAction.setSize("300px", "300px");
        panelDocumentsAction.add(scrollActions);
        mainPanel.setWidget(0, 0, panelDocumentsClients);
        mainPanel.setWidget(0, 2, panelDocumentsAction);

        boutonAnnuler = new DecoratedButton(viewConstants.boutonAnnuler());
        boutonAjouter = new DecoratedButton(viewConstants.boutonAjouter());
        boutonEnlever = new DecoratedButton(viewConstants.boutonEnlever());
        boutonAjouterDocument = new DecoratedButton(viewConstants.boutonAjouterDocument());
        boutonEnregistrer = new DecoratedButton(viewConstants.boutonEnregistrer());

        boutonAjouter.addStyleName("boutonPopupActionSelectionDocument");
        boutonEnlever.addStyleName("boutonPopupActionSelectionDocument");
        boutonAjouterDocument.addStyleName("boutonPopupSelectionDocument");
        boutonEnregistrer.addStyleName("boutonPopupSelectionDocument");

        final VerticalPanel panelBoutonsAction = new VerticalPanel();
        panelBoutonsAction.add(boutonAjouter);
        panelBoutonsAction.add(boutonEnlever);
        mainPanel.setWidget(0, 1, panelBoutonsAction);

        final HorizontalPanel panelBoutons = new HorizontalPanel();
        panelBoutons.add(boutonAnnuler);
        panelBoutons.add(boutonEnregistrer);
        panelBoutons.add(boutonAjouterDocument);
        mainPanel.setWidget(1, 0, panelBoutons);
        mainPanel.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
        mainPanel.getFlexCellFormatter().setColSpan(1, 0, 3);

        this.setWidget(mainPanel);
        this.addStyleName("popupActionSelectionDocument");
    }

    @Override
    public AssociationDocumentsActionViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public void onRpcServiceFailure(Throwable exception) {
        LoadingPopup.stopAll();
        ErrorPopup.getInstance().afficher(exception);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasClickHandlers getBoutonAnnuler() {
        return boutonAnnuler;
    }

    @Override
    public void fermer() {
        hide();
    }

    @Override
    public void ouvrir() {
        show();
    }

    @Override
    public Tree getArbreDocumentsClient() {
        return documentsClientsTree;
    }

    @Override
    public Tree getArbreDocumentsAction() {
        return documentsActionTree;
    }

    @Override
    public HasClickHandlers getBoutonAjouter() {
        return boutonAjouter;
    }

    @Override
    public HasClickHandlers getBoutonEnlever() {
        return boutonEnlever;
    }

    @Override
    public HasClickHandlers getBoutonAjouterDocument() {
        return boutonAjouterDocument;
    }

    @Override
    public HasClickHandlers getBoutonEnregistrer() {
        return boutonEnregistrer;
    }

}
