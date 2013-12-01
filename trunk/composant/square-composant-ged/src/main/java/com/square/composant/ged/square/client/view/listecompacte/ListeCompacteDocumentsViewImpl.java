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
package com.square.composant.ged.square.client.view.listecompacte;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.ged.square.client.composant.panel.MessagePanel;
import com.square.composant.ged.square.client.composant.panel.MessagePanel.MessageType;
import com.square.composant.ged.square.client.presenter.ListeCompacteDocumentsPresenter.ListeCompacteDocumentsView;

/**
 * Implémentation de la vue.
 * @author jgoncalves - SCUB
 */
public class ListeCompacteDocumentsViewImpl extends Composite implements ListeCompacteDocumentsView {
    private ListeCompacteDocumentsViewImplConstants viewConstants;
    private FlowPanel documentsPanel;
    private Anchor lienAjouter;
    private MessagePanel messagePanel;

    /** Constructeur par défaut. */
    public ListeCompacteDocumentsViewImpl() {
        viewConstants = GWT.create(ListeCompacteDocumentsViewImplConstants.class);
        final HorizontalPanel mainPanel = new HorizontalPanel();
        mainPanel.setWidth("100%");

        documentsPanel = new FlowPanel();
        lienAjouter = new Anchor(viewConstants.ajouterDocument());
        lienAjouter.addStyleName("lienDocumentModeCompact");

        mainPanel.add(documentsPanel);
        mainPanel.add(lienAjouter);

        mainPanel.setCellWidth(lienAjouter, "200px");
        mainPanel.setCellHorizontalAlignment(lienAjouter, HasHorizontalAlignment.ALIGN_RIGHT);
        mainPanel.setCellVerticalAlignment(lienAjouter, HasVerticalAlignment.ALIGN_MIDDLE);

        messagePanel = new MessagePanel();

        final VerticalPanel vPanel = new VerticalPanel();
        vPanel.setWidth("100%");
        vPanel.add(mainPanel);
        vPanel.add(messagePanel);

        initWidget(vPanel);
    }

    @Override
    public HasClickHandlers ajouterLien(String nom, String url, String typeMime) {
        final HorizontalPanel panel = new HorizontalPanel();
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        // Pour le moment, on n'affiche que le pdf en icone
        if (typeMime != null && typeMime.indexOf("pdf") > 0) {
            final Image image = new Image(viewConstants.dossierImagesTypesMime() + typeMime + ".png");
            panel.add(image);
        }
        final Anchor lien = new Anchor(nom, url, "_blank");
        lien.addStyleName("lienDocumentModeCompact");
        panel.add(lien);
        panel.addStyleName("blocDocumentModeCompact");
        documentsPanel.add(panel);
        return lien;
    }

    @Override
    public ListeCompacteDocumentsViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void onRpcServiceFailure(Throwable exception) {
        messagePanel.showMessagePanel(viewConstants.messageErreurGeneriqueGed(), MessageType.ERROR);
    }

    @Override
    public void onRpcServiceSuccess() {
        messagePanel.hideMessagePanel();
        documentsPanel.setVisible(true);
        lienAjouter.setVisible(true);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasClickHandlers getLienAssocierDocument() {
        return lienAjouter;
    }

    @Override
    public void vider() {
        documentsPanel.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRpcServiceCall(String message) {
        documentsPanel.setVisible(false);
        lienAjouter.setVisible(false);
        messagePanel.showMessagePanel(message, MessageType.LOADING);
    }

}
