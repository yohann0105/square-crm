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
package com.square.client.gwt.client.view.ressource.gestion;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.bloc.ContenuOnglet;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.presenter.ressource.GestionRessourcePresenter;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue pour la gestion d'une ressource.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class GestionRessourceViewImpl extends Composite implements GestionRessourcePresenter.GestionRessourceView {

    /** View constants. */
    private static GestionRessourceViewImplConstants viewConstants = (GestionRessourceViewImplConstants) GWT.create(GestionRessourceViewImplConstants.class);

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Conteneur principal. */
    private VerticalPanel conteneur;

    /** Label du code. */
    private Label lnom;

    /** Label du type. */
    private Label lPrenom;

    /** Label de l'etat. */
    private Label lEtat;

    /** Label de la fonction. */
    private Label lFonction;

    /** Label du service. */
    private Label lService;

    /** Label de l'agence. */
    private Label lAgence;

    /** Bouton Enregistrer. */
    private DecoratedButton btnEnregistrer;

    /** Bouton Annuler. */
    private DecoratedButton btnAnnuler;

    /** Conteneur des quotas. */
    private VerticalPanel conteneurQuotas;

    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    private boolean isAdmin;

    /** Constructeur. */
    public GestionRessourceViewImpl(boolean isAdmin) {
        iconeErreurChampManager = new IconeErreurChampManager();
        this.isAdmin = isAdmin;
        conteneur = new VerticalPanel();
        construirePage();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        this.initWidget(new ContenuOnglet(conteneur));
        this.setWidth(AppControllerConstants.POURCENT_100);
        this.addStyleName(SquareResources.INSTANCE.css().campagneGestion());
    }

    private void construirePage() {
        lnom = new Label();
        lPrenom = new Label();
        lFonction = new Label();
        lAgence = new Label();
        lService = new Label();
        lEtat = new Label();

        btnAnnuler = new DecoratedButton(viewConstants.annuler());
        btnEnregistrer = new DecoratedButton(viewConstants.enregistrer());

        final FlexTable ressourceInfoFTable = new FlexTable();
        ressourceInfoFTable.setWidth(AppControllerConstants.POURCENT_100);
        ressourceInfoFTable.setCellSpacing(10);
        ressourceInfoFTable.setWidget(0, 0, new Label(viewConstants.nom(), false));
        ressourceInfoFTable.setWidget(0, 1, lnom);
        ressourceInfoFTable.setWidget(0, 2, new Label(viewConstants.prenom(), false));
        ressourceInfoFTable.setWidget(0, 3, lPrenom);
        ressourceInfoFTable.setWidget(0, 4, new Label(viewConstants.etat(), false));
        ressourceInfoFTable.setWidget(0, 5, lEtat);
        ressourceInfoFTable.setWidget(1, 0, new Label(viewConstants.fonction(), false));
        ressourceInfoFTable.setWidget(1, 1, lFonction);
        ressourceInfoFTable.setWidget(1, 2, new Label(viewConstants.service(), false));
        ressourceInfoFTable.setWidget(1, 3, lService);
        ressourceInfoFTable.setWidget(1, 4, new Label(viewConstants.agence(), false));
        ressourceInfoFTable.setWidget(1, 5, lAgence);
        ressourceInfoFTable.getColumnFormatter().setWidth(0, "10%");
        ressourceInfoFTable.getColumnFormatter().setWidth(1, "23%");
        ressourceInfoFTable.getColumnFormatter().setWidth(2, "10%");
        ressourceInfoFTable.getColumnFormatter().setWidth(3, "23%");
        ressourceInfoFTable.getColumnFormatter().setWidth(4, "10%");
        ressourceInfoFTable.getColumnFormatter().setWidth(5, "24%");

        final CaptionPanel captionPanelInformation = new CaptionPanel(viewConstants.informations());
        captionPanelInformation.add(ressourceInfoFTable);
        // Construction de la bare de bouton
        final HorizontalPanel horizontalPanelBoutons = new HorizontalPanel();
        // Désactivation temporaire des boutons
        // horizontalPanelBoutons.add(btnEnregistrer);
        // horizontalPanelBoutons.add(btnAnnuler);
        horizontalPanelBoutons.setSpacing(5);

        // Panel présentant les quotas de la ressource
        conteneurQuotas = new VerticalPanel();
        conteneurQuotas.setWidth("100%");

        final VerticalPanel panelGlobal = new VerticalPanel();
        AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_RESSOURCES_GESTION_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        conteneur.add(aideView);
        conteneur.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);
        panelGlobal.add(captionPanelInformation);
        panelGlobal.add(horizontalPanelBoutons);
        panelGlobal.add(conteneurQuotas);
        panelGlobal.setCellHorizontalAlignment(horizontalPanelBoutons, HasAlignment.ALIGN_RIGHT);
        panelGlobal.setSpacing(10);
        panelGlobal.setWidth(AppControllerConstants.POURCENT_100);

        conteneur.add(panelGlobal);
    }
    /** ajoute les composants d'aide. */
    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(config);
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    /**
     * Renvoi la valeur de lnom.
     * @return lnom
     */
    @Override
    public Label getlNom() {
        return lnom;
    }

    /**
     * Renvoi la valeur de lPrenom.
     * @return lPrenom
     */
    @Override
    public Label getlPrenom() {
        return lPrenom;
    }

    /**
     * Renvoi la valeur de lEtat.
     * @return lEtat
     */
    @Override
    public Label getlEtat() {
        return lEtat;
    }

    /**
     * Renvoi la valeur de lFonction.
     * @return lFonction
     */
    @Override
    public Label getlFonction() {
        return lFonction;
    }

    /**
     * Renvoi la valeur de lService.
     * @return lService
     */
    @Override
    public Label getlService() {
        return lService;
    }

    /**
     * Renvoi la valeur de lAgence.
     * @return lAgence
     */

    @Override
    public Label getlAgence() {
        return lAgence;
    }

    /**
     * Renvoi la valeur de btnEnregistrer.
     * @return btnEnregistrer
     */
    @Override
    public DecoratedButton getBtnEnregistrer() {
        return btnEnregistrer;
    }

    /**
     * Renvoi la valeur de btnAnnuler.
     * @return btnAnnuler
     */
    @Override
    public DecoratedButton getBtnAnnuler() {
        return btnAnnuler;
    }

    /**
     * Renvoi les constantes de la vue.
     * @return viewConstants
     */
    @Override
    public GestionRessourceViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public HasWidgets getConteneurQuotas() {
        return conteneurQuotas;
    }

    @Override
    public List<AideComposant> getListAideCompsant() {
    	return listComposantAide;
    }

    @Override
    public List<HasDemandeAideEventHandler> getListeDemandeEventHandler() {
    	return listDemandeAideEventHandler;
    }

    @Override
    public List<HasUpdateAideEventHandler> getListeUpdateEventHandler() {
    	return listUpdateAideEventHandler;
    }
}
