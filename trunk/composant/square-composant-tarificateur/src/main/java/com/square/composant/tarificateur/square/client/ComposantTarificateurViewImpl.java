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
package com.square.composant.tarificateur.square.client;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.ComposantTarificateur.ComposantTarificateurView;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Vue du composant tarificateur.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ComposantTarificateurViewImpl extends Composite implements ComposantTarificateurView {

    private static final String HAUTEUR_CAPTION_PANEL = "95px";

    private VerticalPanel conteneurDevis;

    private DecoratedButton btnAjouterDevis;

    private DecoratedButton btnVoirActions;

    private DecoratedButton btnSupprimerOpportunite;

    private ComposantTarificateurViewImplConstants viewConstants;

    private Label lDateCloture;

    private Label lDateSignature;

    private Label lDateEditionBA;

    private Label lNature;

    /**
     * Constructeur.
     * @param hasRoleAdmin vrai si l'utilisateur connecté a le rôle admin square
     */
    public ComposantTarificateurViewImpl(boolean hasRoleAdmin, boolean hasRoleAnimateur) {
        this.viewConstants = (ComposantTarificateurViewImplConstants) GWT.create(ComposantTarificateurViewImplConstants.class);

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth(ComposantTarificateurConstants.POURCENT_100);
        conteneur.addStyleName(ComposantTarificateur.RESOURCES.css().composantTarificateur());

        btnAjouterDevis = new DecoratedButton(viewConstants.ajouterDevis());
        btnVoirActions = new DecoratedButton(viewConstants.voirActions());
        btnSupprimerOpportunite = new DecoratedButton(viewConstants.supprimerOpportunite());
        btnSupprimerOpportunite.setVisible(hasRoleAdmin);

        final HorizontalPanel conteneurBoutons = new HorizontalPanel();
        conteneurBoutons.setSpacing(5);
        conteneurBoutons.add(btnAjouterDevis);
        conteneurBoutons.add(btnVoirActions);
        conteneurBoutons.add(btnSupprimerOpportunite);

        final Label lDateClotureIntitule = new Label(viewConstants.labelDateCloture());
        final Label lDateSignatureIntitule = new Label(viewConstants.labelDateSignature());
        final Label lDateEditionBAIntitule = new Label(viewConstants.labelDateEditionBA());
        lDateCloture = new Label();
        lDateSignature = new Label();
        lDateEditionBA = new Label();

        final FlexTable ftDates = new FlexTable();
        ftDates.setCellSpacing(3);
        ftDates.setWidget(0, 0, lDateClotureIntitule);
        ftDates.setWidget(0, 1, lDateCloture);
        ftDates.setWidget(1, 0, lDateSignatureIntitule);
        ftDates.setWidget(1, 1, lDateSignature);
        ftDates.setWidget(2, 0, lDateEditionBAIntitule);
        ftDates.setWidget(2, 1, lDateEditionBA);

        final CaptionPanel cpDates = new CaptionPanel(viewConstants.captionDates());
        cpDates.add(ftDates);
        cpDates.setHeight(HAUTEUR_CAPTION_PANEL);

        final Label lNatureIntitule = new Label(viewConstants.labelNature());
        lNature = new Label();

        final FlexTable ftInformations = new FlexTable();
        ftInformations.setCellSpacing(3);
        ftInformations.setWidget(0, 0, lNatureIntitule);
        ftInformations.setWidget(0, 1, lNature);

        final CaptionPanel cpInformations = new CaptionPanel(viewConstants.captionInformations());
        cpInformations.add(ftInformations);
        cpInformations.setHeight(HAUTEUR_CAPTION_PANEL);

        final Grid conteneurSyntheseInformations = new Grid(1, 2);
        conteneurSyntheseInformations.setCellSpacing(5);
        conteneurSyntheseInformations.setWidth(ComposantTarificateurConstants.POURCENT_100);
        conteneurSyntheseInformations.setWidget(0, 0, cpDates);
        conteneurSyntheseInformations.setWidget(0, 1, cpInformations);
        conteneurSyntheseInformations.getColumnFormatter().setWidth(0, ComposantTarificateurConstants.POURCENT_50);
        conteneurSyntheseInformations.getColumnFormatter().setWidth(1, ComposantTarificateurConstants.POURCENT_50);
        conteneurSyntheseInformations.getRowFormatter().setVerticalAlign(0, HasVerticalAlignment.ALIGN_TOP);

        conteneurDevis = new VerticalPanel();
        conteneurDevis.setWidth(ComposantTarificateurConstants.POURCENT_100);
        conteneurDevis.setSpacing(5);

        conteneur.add(conteneurBoutons);
        conteneur.add(conteneurSyntheseInformations);
        conteneur.add(conteneurDevis);
        this.initWidget(conteneur);
        this.setWidth(ComposantTarificateurConstants.POURCENT_100);
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(config);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasClickHandlers getBtnAjouterDevis() {
        return btnAjouterDevis;
    }

    @Override
    public HasClickHandlers getBtnVoirActions() {
        return btnVoirActions;
    }

    @Override
    public HasClickHandlers getBtnSupprimerOpportunite() {
    	return btnSupprimerOpportunite;
    }

    @Override
    public void afficherPopupLoadingTransformationDevisAia() {
        final LoadingPopupConfiguration config = new LoadingPopupConfiguration(viewConstants.transformationDevisAIA());
        LoadingPopup.afficher(config);
    }

    @Override
    public void afficherErreursBloquantesTransformationDevisAia(String erreurs) {
        final StringBuffer buffer = new StringBuffer(viewConstants.titreListeErreursBloquantes() + " : <br />");
        buffer.append(erreurs);
        final ErrorPopupConfiguration config = new ErrorPopupConfiguration(buffer.toString());
        config.setWidth(ComposantTarificateurConstants.LARGEUR_LARGE_ERROR_POPUP);
        ErrorPopup.afficher(config);
    }

    @Override
    public void afficherErreursNonBloquantesTransformationDevisAia(String erreurs) {
        final StringBuffer buffer = new StringBuffer(viewConstants.titreListeErreursNonBloquantes() + " : <br />");
        buffer.append(erreurs);
        final ErrorPopupConfiguration config = new ErrorPopupConfiguration(buffer.toString());
        config.setWidth(ComposantTarificateurConstants.LARGEUR_LARGE_ERROR_POPUP);
        ErrorPopup.afficher(config);
    }

    @Override
    public void afficherPopupSuccesTransformationDevisAia(PopupCallback callback) {
        final PopupInfoConfiguration config = new PopupInfoConfiguration(viewConstants.messagePopupSuccesTransformation(),
            ComposantTarificateurConstants.NOTIFICATION_TIME_OUT);
        config.setCallback(callback);
        new DecoratedInfoPopup(config).show();
    }

    @Override
    public HasWidgets getConteneurDevis() {
        final SimplePanel conteneurUnDevis = new SimplePanel();
        conteneurUnDevis.setWidth(ComposantTarificateurConstants.POURCENT_100);
        conteneurDevis.add(conteneurUnDevis);
        return conteneurUnDevis;
    }

    @Override
    public void initConteneurDevis() {
        conteneurDevis.clear();
    }

    @Override
    public void afficherPopupChargementOpportunite() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(viewConstants.chargementOpportunite()));
    }

    @Override
    public void stopperPopupChargement() {
        LoadingPopup.stopAll();
    }

    @Override
    public void afficherAucunDevis() {
        final Label lAucunDevis = new Label(viewConstants.aucunDevis());
        lAucunDevis.addStyleName(ComposantTarificateur.RESOURCES.css().aucunDevis());
        conteneurDevis.add(lAucunDevis);
        conteneurDevis.setCellHorizontalAlignment(lAucunDevis, HasHorizontalAlignment.ALIGN_CENTER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getDateCloture() {
        return lDateCloture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getDateEditionBA() {
        return lDateEditionBA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getDateSignature() {
        return lDateSignature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getNature() {
        return lNature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableAjoutDevis(boolean enable) {
        btnAjouterDevis.setEnabled(enable);
    }
}
