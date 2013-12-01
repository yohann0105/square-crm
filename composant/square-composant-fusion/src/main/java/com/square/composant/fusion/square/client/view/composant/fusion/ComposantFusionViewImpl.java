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
package com.square.composant.fusion.square.client.view.composant.fusion;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenter;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenterConstants;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenter.ComposantFusionView;

/**
 * Implémentation de l'interface pour la vue de l'application.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ComposantFusionViewImpl extends Composite implements ComposantFusionView {

    /** Gestion des onglets. */
    private Map<String, ScrollPanel> gestionOnglets;

    private Map<String, Integer> gestionIndexsOnglets;

    /** TabPanel. */
    private DecoratedTabPanel tabPanel;

    /**
     * Constructeur.
     */
    public ComposantFusionViewImpl() {
        // Création des onglets
        gestionOnglets = new HashMap<String, ScrollPanel>();
        gestionIndexsOnglets = new HashMap<String, Integer>();
        tabPanel = new DecoratedTabPanel();
        tabPanel.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        int index = 0;
        final String[] onglets = new String[] {ComposantFusionPresenter.CONSTANTS.ongletSelectionDoublons(),
            ComposantFusionPresenter.CONSTANTS.ongletFusionner()};
        for (String onglet : onglets) {
            final ScrollPanel panel = new ScrollPanel();
            gestionOnglets.put(onglet, panel);
            gestionIndexsOnglets.put(onglet, index++);
            final String libelleOnglet = getLibelleOnglet(onglet);
            final Label lOnglet = new Label(libelleOnglet);
            lOnglet.setWordWrap(false);
            tabPanel.add(panel, lOnglet);
        }

        // Création du panel principal
        final VerticalPanel pPrincipal = new VerticalPanel();
        pPrincipal.addStyleName(ComposantFusionPresenter.RESOURCES.css().composantFusion());
        pPrincipal.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        pPrincipal.setSpacing(10);
        pPrincipal.add(tabPanel);
        pPrincipal.setWidth(ComposantFusionPresenterConstants.LARGEUR_CONTENEUR);
        initWidget(pPrincipal);
    }

    /**
     * Récupère le libellé d'un onglet.
     * @param appControllerConstants les constantes de l'application.
     * @param onglet l'onglet.
     * @return le libellé de l'onglet.
     */
    private String getLibelleOnglet(String onglet) {
        if (ComposantFusionPresenter.CONSTANTS.ongletSelectionDoublons().equals(onglet)) {
            return ComposantFusionPresenter.CONSTANTS.libelleOngletSelectionDoublons();
        }
        else if (ComposantFusionPresenter.CONSTANTS.ongletFusionner().equals(onglet)) {
            return ComposantFusionPresenter.CONSTANTS.libelleOngletFusionner();
        }
        else {
            return null;
        }
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        ErrorPopup.afficher(config);
    }

    @Override
    public HasWidgets getOngletContainer(String onglet) {
        final SimplePanel panel = gestionOnglets.get(onglet);
        final Integer index = gestionIndexsOnglets.get(onglet);
        tabPanel.selectTab(index);
        return panel;
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
    public void selectOnglet(String onglet) {
        for (Entry<String, Integer> entry : gestionIndexsOnglets.entrySet()) {
            if (entry.getKey().equals(onglet)) {
                tabPanel.selectTab(entry.getValue());
            }
        }
    }

}
