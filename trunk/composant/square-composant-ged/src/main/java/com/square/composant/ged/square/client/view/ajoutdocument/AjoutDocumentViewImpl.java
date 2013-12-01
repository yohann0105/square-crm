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
package com.square.composant.ged.square.client.view.ajoutdocument;

import gwtupload.client.IUploadStatus;
import gwtupload.client.IUploader;
import gwtupload.client.Uploader;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.CancelBehavior;

import java.util.HashSet;
import java.util.Set;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.ged.square.client.composant.popup.ErrorPopup;
import com.square.composant.ged.square.client.composant.popup.LoadingPopup;
import com.square.composant.ged.square.client.composant.popup.LoadingPopupConfiguration;
import com.square.composant.ged.square.client.composant.popup.Popup;
import com.square.composant.ged.square.client.composant.tagcloud.SelecteurTagCloud;
import com.square.composant.ged.square.client.presenter.AjoutDocumentPresenter.AjoutDocumentView;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Implémentation vue ajout de document.
 * @author jgoncalves - SCUB
 */
public class AjoutDocumentViewImpl extends Popup implements AjoutDocumentView {

    private static AjoutDocumentViewImplConstants viewConstants = GWT.create(AjoutDocumentViewImplConstants.class);
    private DecoratedButton boutonAnnuler;
    private DecoratedButton boutonAjouter;
    private DecoratedCalendrierDateBox dateReception;
    private Uploader uploader;
    private SelecteurTagCloud tagCloud;
    private IconeErreurChampManager iconeErreurChampManager;

    /**
     * Constructeur par défaut.
     */
    public AjoutDocumentViewImpl() {
        super(viewConstants.titrePopup(), false, true, true);
        iconeErreurChampManager = new IconeErreurChampManager();
        dateReception = new DecoratedCalendrierDateBox();
        boutonAnnuler = new DecoratedButton(viewConstants.boutonAnnuler());
        boutonAjouter = new DecoratedButton(viewConstants.boutonAjouter());

        uploader = new Uploader(FileInputType.LABEL, false);
        final CustomUploaderConstants uploaderConstants = GWT.create(CustomUploaderConstants.class);
        uploader.setI18Constants(uploaderConstants);

        final CaptionPanel panelInfosFichier = new CaptionPanel(viewConstants.informationsFichier());
        final FlexTable tableInfosGenerales = new FlexTable();
        tableInfosGenerales.setWidget(0, 0, new Label(viewConstants.labelFichier()));
        tableInfosGenerales.setWidget(0, 1, construireBlocIcone(uploader, "fichier"));
        tableInfosGenerales.setWidget(1, 0, new Label(viewConstants.labelDateReception()));
        tableInfosGenerales.setWidget(1, 1, construireBlocIcone(dateReception, "dateReception"));
        panelInfosFichier.add(tableInfosGenerales);

        tagCloud = new SelecteurTagCloud(iconeErreurChampManager);

        final FlexTable mainPanel = new FlexTable();
        mainPanel.setSize("750px", "450px");
        mainPanel.setWidget(0, 0, panelInfosFichier);
        mainPanel.setWidget(1, 0, tagCloud);

        boutonAjouter.addStyleName("boutonPopupSelectionDocument");

        final HorizontalPanel panelBoutons = new HorizontalPanel();
        panelBoutons.add(boutonAnnuler);
        panelBoutons.add(boutonAjouter);
        mainPanel.setWidget(2, 0, panelBoutons);
        this.setWidget(mainPanel);
        this.addStyleName("popupSelectionDocument");
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        panel.add(icone);
        panel.setCellVerticalAlignment(icone, HasAlignment.ALIGN_MIDDLE);
        return panel;
    }

    @Override
    public void fermer() {
        this.hide();
    }

    @Override
    public HasClickHandlers getBoutonAjouter() {
        return boutonAjouter;
    }

    @Override
    public HasClickHandlers getBoutonAnnuler() {
        return boutonAnnuler;
    }


    @Override
    public void ouvrir() {
       this.show();
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public void afficherProgressPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        // Préparation du widget pour la progression :
        final SimplePanel panel = new SimplePanel();
        panel.addStyleName("GWTUpld");
        panel.setWidget(uploader.getStatusWidget().getWidget());
        final Set<CancelBehavior> cancelConfigSet = new HashSet<CancelBehavior>();
        cancelConfigSet.add(IUploadStatus.CancelBehavior.DISABLED);
        uploader.getStatusWidget().setCancelConfiguration(cancelConfigSet);
        loadingPopupConfiguration.setExtraContent(panel);
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public void onRpcServiceFailure(Throwable exception) {
        LoadingPopup.stopAll();
        ErrorPopup.getInstance().afficher(exception);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stopAll();
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public AjoutDocumentViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public IUploader getUploader() {
        return uploader;
    }

    @Override
    public SelecteurTagCloud getSelecteurTypesDocuments() {
        return tagCloud;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public DecoratedCalendrierDateBox getDateReception() {
        return dateReception;
    }
}
