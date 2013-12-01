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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.RafraichirRecherchePersonneEvent;
import com.square.composant.fusion.square.client.model.RechercheDoublonCritereModel;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenter;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEventHandler;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;
import com.square.composants.graphiques.lib.client.popups.minimizable.PopupMinimizable;

/**
 * Popup pour fusion des personnes.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupFusion extends Popup {

    /** Constantes de la popup. */
    private static PopupFusionConstants popupConstants = GWT.create(PopupFusionConstants.class);

    private DeskBar deskBar;

    private DecoratedButton btnReduire;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènement.
     * @param nom le nom du doublon.
     * @param prenom le prénom du doublon.
     * @param dateNaissance la date de naissance du doublon.
     * @param deskBar deskBar
     */
    public PopupFusion(final HandlerManager eventBus, String nom, String prenom, String dateNaissance, DeskBar deskBar) {
        this(eventBus, nom, prenom, dateNaissance, null, deskBar);
    }

    /**
     * Constructeur.
     * @param eventBus le bus d'évènement.
     * @param nom le nom du doublon.
     * @param prenom le prénom du doublon.
     * @param dateNaissance la date de naissance du doublon.
     * @param idPersonneSelectionnee identifiant de la personne pré-selectionnée dans la popup de fusion
     * @param deskBar deskBar
     */
    public PopupFusion(final HandlerManager eventBus, String nom, String prenom, String dateNaissance, Long idPersonneSelectionnee, DeskBar deskBar) {
        super(popupConstants.titrePopup(), false, false, true);
        this.addStyleName(SquareResources.INSTANCE.css().popupFusion());
        this.deskBar = deskBar;

        final VerticalPanel pConteneur = new VerticalPanel();
        pConteneur.setWidth(PopupFusionConstants.LARGEUR_POPUP);
        pConteneur.setSpacing(5);

        final VerticalPanel pConteneurComposantFusion = new VerticalPanel();
        pConteneurComposantFusion.setWidth(AppControllerConstants.POURCENT_100);
        final RechercheDoublonCritereModel criteres = new RechercheDoublonCritereModel();
        criteres.setNom(nom);
        criteres.setPrenom(prenom);
        criteres.setDateNaissance(dateNaissance);
        final ComposantFusionPresenter composantFusionPresenter = new ComposantFusionPresenter(eventBus, criteres, idPersonneSelectionnee);
        composantFusionPresenter.showPresenter(pConteneurComposantFusion);

        final DecoratedButton btnFermer = new DecoratedButton(popupConstants.btnFermer());
        btnFermer.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();

                // On met à jour la recherche
                eventBus.fireEvent(new RafraichirRecherchePersonneEvent());
            }
        });

        btnReduire = new DecoratedButton(popupConstants.reduire());

        eventBus.addHandler(EnableMinimizeWidgetEvent.TYPE, new EnableMinimizeWidgetEventHandler() {
            @Override
            public void enableMinimizeWidget(EnableMinimizeWidgetEvent event) {
                btnReduire.setEnabled(event.isEnabled());
            }
        });

        final HorizontalPanel conteneurBoutons = new HorizontalPanel();
        conteneurBoutons.add(btnReduire);
        conteneurBoutons.add(btnFermer);
        conteneurBoutons.setSpacing(5);

        pConteneur.add(pConteneurComposantFusion);
        pConteneur.add(conteneurBoutons);
        pConteneur.setCellHorizontalAlignment(conteneurBoutons, HasAlignment.ALIGN_CENTER);

        this.setWidget(pConteneur, 0);
    }

    @Override
    public void show() {
        super.show();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                addPopup();
            }
        });
    }

    private void addPopup() {
        // on en fait une popup minimisable
        deskBar.addPopup(new PopupMinimizable(this, popupConstants.titrePopup(), btnReduire));
    }
}
