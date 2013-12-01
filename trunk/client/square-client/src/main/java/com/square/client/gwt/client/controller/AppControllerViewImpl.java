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
package com.square.client.gwt.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.bloc.ContenuOnglet;
import com.square.client.gwt.client.composant.onglet.scroll.DecoratedDoubleTabPanelScroll;
import com.square.client.gwt.client.controller.AppController.AppControllerView;
import com.square.client.gwt.client.event.CloseTabEvent;
import com.square.client.gwt.client.model.OngletModel;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Implémentation de la vue par défaut.
 * @author jgoncalves - SCUB
 */
public class AppControllerViewImpl extends Composite implements AppControllerView {

    /** Gestion des onglets. */
    private Map<String, ContainerTabPanel> gestionOnglets;

    /** Gestions des onglets et onglets parents. */
    private Map<String, String> ongletsParent;

    /** TabLayoutPanel. */
    private DecoratedDoubleTabPanelScroll tabPanel;

    /** Liste des labels des onglets (pour la gestion des la mise a jour. */
    private Map<String, HTML> listeLabelsOnglets;

    /** Simple panel ACP. */
    private SimplePanel containerAcp;

    /** Simple panel Actions. */
    private SimplePanel containerActions;

    /** Simple panel ActionsContext. **/
    private SimplePanel rootContextMenu;

    /** Handler Manager du Presenter. */
    private HandlerManager appControllerHandlerManager;

    /** Nombre des onglets par defaut. */
    private int nbreDefaultTabs;

    /** Nombre des onglets par defaut. */
    private int nbreDynamicsTabsOpened = 0;

    private DeskBar deskBar;

    /**
     * Constructeur.
     * @param onglets la liste des onglets.
     * @param appCoHandlerManager handler manager.
     * @param constants les constantes
     * @param eventBus eventBus
     */
    public AppControllerViewImpl(List<OngletModel> onglets, HandlerManager appCoHandlerManager, AppControllerConstants constants,
        HandlerManager eventBus) {
        this.appControllerHandlerManager = appCoHandlerManager;
        listeLabelsOnglets = new HashMap<String, HTML>();

        // CONTAINER ACTIONS CONTEXT
        rootContextMenu = new SimplePanel();
        nbreDefaultTabs = onglets.size();

        // TITRE
        final HTML titreAppli =
            new HTML("<span class=\"partie1\">" + constants.titrePartie1() + "</span> " + "<span class=\"partie2\">" + constants.titrePartie2() + "</span>");
        final SimplePanel conteneurTitre = new SimplePanel();
        conteneurTitre.setStylePrimaryName(SquareResources.INSTANCE.css().conteneurTitre());
        conteneurTitre.add(titreAppli);

        // ONGLET MENU
        gestionOnglets = new HashMap<String, ContainerTabPanel>();
        ongletsParent = new HashMap<String, String>();
        final int largeurScrollPanel = AppControllerConstants.LARGEUR_TABPANEL - onglets.size() * AppControllerConstants.LARGEUR_ONGLET;
        tabPanel = new DecoratedDoubleTabPanelScroll(onglets.size(), largeurScrollPanel);
        tabPanel.setSize(AppControllerConstants.LARGEUR_TABPANEL_STRING, AppControllerConstants.POURCENT_100);
        tabPanel.addStyleName(SquareResources.INSTANCE.css().tabPanel());

        for (OngletModel onglet : onglets) {
            final ContainerTabPanel panel = new ContainerTabPanel(this.rootContextMenu);
            gestionOnglets.put(onglet.getId(), panel);
            if (onglet.getImage() != null) {
                final Image imageOnglet = new Image(onglet.getImage());
                imageOnglet.setTitle(onglet.getLibelle());
                tabPanel.add(panel, imageOnglet);
            } else {
                final HTML libelleOnglet = new HTML(onglet.getLibelle());
                tabPanel.add(panel, libelleOnglet);
            }
        }

        // PANEL DROITE
        final VerticalPanel conteneurGlobalPanelDroite = new VerticalPanel();
        conteneurGlobalPanelDroite.addStyleName(SquareResources.INSTANCE.css().conteneurGlobalPanelDroite());
        conteneurGlobalPanelDroite.setSize("260px", AppControllerConstants.POURCENT_100);
        final VerticalPanel conteneurPanelDroite = new VerticalPanel();
        conteneurPanelDroite.addStyleName(SquareResources.INSTANCE.css().conteneurPanelDroite());
        conteneurPanelDroite.setSize("260px", AppControllerConstants.POURCENT_100);
        final VerticalPanel panelDroite = new VerticalPanel();
        panelDroite.addStyleName(SquareResources.INSTANCE.css().panelDroite());
        panelDroite.setWidth(AppControllerConstants.POURCENT_100);
        panelDroite.setSpacing(10);

        this.containerAcp = new SimplePanel();
        panelDroite.add(containerAcp);

        // CONTAINER ACTIONS
        this.containerActions = new SimplePanel();
        panelDroite.add(containerActions);

        panelDroite.add(rootContextMenu);

        deskBar = new DeskBar(AppControllerConstants.NB_MINIMIZE_POPUP_MAX, eventBus);
        panelDroite.add(deskBar);

        conteneurPanelDroite.add(panelDroite);
        conteneurGlobalPanelDroite.add(conteneurPanelDroite);

        // PANEL PRINCIPAL
        final DockPanel dockPanel = new DockPanel();

        dockPanel.add(conteneurTitre, DockPanel.NORTH);
        dockPanel.setCellHeight(conteneurTitre, "40px");
        dockPanel.add(tabPanel, DockPanel.CENTER);
        dockPanel.setCellVerticalAlignment(tabPanel, HasVerticalAlignment.ALIGN_TOP);
        dockPanel.add(conteneurGlobalPanelDroite, DockPanel.EAST);

        dockPanel.addStyleName(SquareResources.INSTANCE.css().panelPrincipal());
        dockPanel.setSize("1190px", AppControllerConstants.POURCENT_100);

        initWidget(dockPanel);
    }

    @Override
    public void fermerOnglet(String onglet) {
        final ContainerTabPanel panel = gestionOnglets.get(onglet);

        if (panel != null) {

            nbreDynamicsTabsOpened--;

            final int selectedTab = tabPanel.getSelectedTab();
            final int indexPanel = tabPanel.getWidgetIndex(panel);
            final int indexParent = tabPanel.getWidgetIndex(gestionOnglets.get(ongletsParent.get(onglet)));
            int newIndex = (indexPanel == gestionOnglets.size() - 1) ? indexPanel - 1 : indexPanel;
            newIndex = (newIndex == nbreDefaultTabs - 1) ? indexParent : newIndex;

            //SUPPRIME TOUS CONTENU DU CONTAINER
            panel.clear();
            //SUPPRIME TOUS CONTEXTE DU CONTAINER
            panel.destroyContext();
            //SUPPRIME LA REFERENCE VERS LE CONTAINER
            gestionOnglets.remove(onglet);

            //SUPPRIMER REFERENCE DU CONTAINER et DU LIBELLE DANS LE TAB-PANEL
            tabPanel.remove(panel);

            //REFERENCE LIBELLE
            ongletsParent.remove(onglet);
            listeLabelsOnglets.remove(onglet);

            // on se repositionne sur le nouvel onglet
            tabPanel.selectTab(newIndex);
        }
    }
    /**
     * {@inheritDoc}
     */
    public void selectionOnglet(String ongletId)
    {
        if (gestionOnglets.containsKey(ongletId)) {
            final SimplePanel panel = gestionOnglets.get(ongletId);
            final int index = tabPanel.getWidgetIndex(panel);
            tabPanel.selectTab(index);
        }
    }

    @Override
    public HasWidgets getOngletContainer(final OngletModel onglet) {
        return getOngletContainer(onglet, "");
    }

    @Override
    public HasWidgets getOngletContainer(final OngletModel onglet, final String idOngletParent) {
        // ONGLET PERMANENT OU EXISTANT
        if (gestionOnglets.containsKey(onglet.getId())) {
            final SimplePanel panel = gestionOnglets.get(onglet.getId());
            final int index = tabPanel.getWidgetIndex(panel);
//            tabPanel.selectTab(index);
            return panel;
        }
        // ONGLET DYNAMIQUE
        else {
            // on augmente le compteur d'onglet dynamique
            nbreDynamicsTabsOpened++;

            final ContainerTabPanel panel = new ContainerTabPanel(rootContextMenu);
            final HorizontalPanel hpOnglet = new HorizontalPanel();
            final HTML libelleOnglet = new HTML(onglet.getLibelle(), false);
            hpOnglet.add(libelleOnglet);
            final Button boutonClose = new Button("&nbsp;");
            boutonClose.setStyleName("gwt-ButtonTab");
            hpOnglet.add(boutonClose);

            // Stockage de la référence du label (pour le renommage des onglets)
            listeLabelsOnglets.put(onglet.getId(), libelleOnglet);
            tabPanel.add(panel, hpOnglet);
            ongletsParent.put(onglet.getId(), idOngletParent);
            gestionOnglets.put(onglet.getId(), panel);
            final int index = tabPanel.getWidgetIndex(panel);
            tabPanel.selectTab(index);
            boutonClose.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    fermerOnglet(onglet.getId());
                    appControllerHandlerManager.fireEvent(new CloseTabEvent(onglet.getId()));
                }
            });
            return panel;
        }
    }

    @Override
    public void updateOnglet(OngletModel onglet) {
        if (gestionOnglets.containsKey(onglet.getId())) {
            listeLabelsOnglets.get(onglet.getId()).setHTML(onglet.getLibelle());
        }
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasWidgets getActionsContainer() {
        return containerActions;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        ErrorPopup.afficher(config);
    }

    @Override
    public String getOngletContainerName(Integer index) {
        final Widget wid = tabPanel.getWidget(index);
        for (String name : gestionOnglets.keySet()) {
            if (gestionOnglets.get(name).equals(wid)) {
                return name;
            }
        }
        return null;
    }

    @Override
    public HasSelectionHandlers<Integer> getOngletSelectionHandler() {
        return tabPanel;
    }

    @Override
    public HasWidgets getActionsContextContainer() {
        return rootContextMenu;
    }

    /**
     * Permet le raz d contexte d'un onglet.
     * @param onglet .
     */
    public void initContextMenu(final Integer onglet) {
        final ContainerTabPanel panel = gestionOnglets.get(getOngletContainerName(onglet));
        this.rootContextMenu.clear();
        if (panel.getContext() != null) {
            this.rootContextMenu.add(panel.getContext());
        }
    }

    /**
     * Classe Permettant de fixer un menu au container principal.
     * @author Goumard Stephane (scub). - SCUB
     */
    public class ContainerTabPanel extends SimplePanel {
        private SimplePanel rootContexMenu;

        private Widget context;

        /**
         * Constructeur.
         * @param rootContexMenu .
         */
        public ContainerTabPanel(SimplePanel rootContexMenu) {
            this.rootContexMenu = rootContexMenu;
            this.context = null;
        }

        /**
         * Permet d'ajotuer un menu au context.
         * @param contextMenu le contextMenu
         */
        public void addContextMenu(Widget contextMenu) {
            rootContexMenu.clear();
            rootContexMenu.add(contextMenu);
            this.context = contextMenu;
        }

        /**
         * Recuperer le context du tab panel.
         * @return .
         */
        Widget getContext() {
            return context;
        }

        /**
         * Supprime tous contexte du container.
         */
        public void destroyContext() {
            this.rootContexMenu.clear();
            this.rootContexMenu = null;
            this.context = null;
        }
    }

    @Override
    public HasWidgets getTelephonieContainer() {
        return containerAcp;
    }

    @Override
    public int getNbreDynamicsTabsOpened() {
        return nbreDynamicsTabsOpened;
    }

    @Override
    public boolean isOngletContainerExistant(OngletModel onglet) {
        return gestionOnglets.containsKey(onglet.getId());
    }

    @Override
    public HasWidgets getOngletContenuOnglet(OngletModel onglet) {
        final HasWidgets conteneur = getOngletContainer(onglet);
        final ContenuOnglet contenuOnglet = new ContenuOnglet();
        conteneur.add(contenuOnglet);
        return contenuOnglet;
    }

    @Override
    public DeskBar getDeskBar() {
        return deskBar;
    }
    @Override
    public HandlerManager getBusLocal() {
        return appControllerHandlerManager;
    }
}
