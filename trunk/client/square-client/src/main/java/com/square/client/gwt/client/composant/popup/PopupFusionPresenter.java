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

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.event.RafraichirRecherchePersonneEvent;
import com.square.composant.fusion.square.client.event.FinFusionPersonnesEvent;
import com.square.composant.fusion.square.client.event.FinFusionPersonnesEventHandler;
import com.square.composant.fusion.square.client.model.RechercheDoublonCritereModel;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenter;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEventHandler;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;

/**
 * Popup pour fusion des personnes.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupFusionPresenter extends Presenter {

    private DeskBar deskBar;

    private ComposantFusionPresenter composantFusionPresenter;

    private HandlerRegistration deskBarRegistration;

    private PopupFusionView view;

    private RechercheDoublonCritereModel criteres;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènement.
     * @param nom le nom du doublon.
     * @param prenom le prénom du doublon.
     * @param dateNaissance la date de naissance du doublon.
     * @param deskBar deskBar
     */
    public PopupFusionPresenter(final HandlerManager eventBus, String nom, String prenom, String dateNaissance, DeskBar deskBar) {
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
    public PopupFusionPresenter(final HandlerManager eventBus, String nom, String prenom, String dateNaissance, Long idPersonneSelectionnee, DeskBar deskBar) {
        super(eventBus);
        view = new PopupFusionViewImpl();
        this.deskBar = deskBar;
        criteres = new RechercheDoublonCritereModel();
        criteres.setNom(nom);
        criteres.setPrenom(prenom);
        criteres.setDateNaissance(dateNaissance);

        composantFusionPresenter = addChildPresenter(new ComposantFusionPresenter(eventBus, criteres, idPersonneSelectionnee));
        composantFusionPresenter.showPresenter(view.getConteneurComposantFusion());
    }

    /**
     * Rafraichir la fusion.
     * @param nom .
     * @param prenom .
     * @param dateDeNaissance .
     * @param idPersonneSelectionnee .
     */
    public void rafraichir(String nom, String prenom, String dateDeNaissance, Long idPersonneSelectionnee) {
        criteres.setDateNaissance(dateDeNaissance);
        criteres.setNom(nom);
        criteres.setPrenom(prenom);
        composantFusionPresenter.demanderFusion(criteres, idPersonneSelectionnee);
        view.showPopup();
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        composantFusionPresenter.addEventHandlerToLocalBus(FinFusionPersonnesEvent.TYPE, new FinFusionPersonnesEventHandler() {
            @Override
            public void onFinFusionPersonnes(FinFusionPersonnesEvent event) {
                // Propagation
                fireEventLocalBus(event);
            }
        });
        view.getBtnFermer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
                fireEventLocalBus(new RafraichirRecherchePersonneEvent());
            }
        });

        deskBarRegistration = deskBar.getEventBus().addHandler(EnableMinimizeWidgetEvent.TYPE, new EnableMinimizeWidgetEventHandler() {
            @Override
            public void enableMinimizeWidget(EnableMinimizeWidgetEvent event) {
                view.getBtnReduire().setEnabled(event.isEnabled());
            }
        });

    }

    @Override
    public void onShow(HasWidgets container) {
        view.showPopup();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                deskBar.addPopup(view.getMinimizablePopup());
            }
        });
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
        deskBarRegistration.removeHandler();
    }

    /**
     * Interface de la vue.
     */
    public interface PopupFusionView extends View {
        /**
         * Accès au bouton de réduction.
         * @return le bouton
         */
        DecoratedButton getBtnReduire();

        /**
         * Accès au bouton de fermeture.
         * @return
         */
        DecoratedButton getBtnFermer();

        /**
         * Masquer la popup.
         */
        void hidePopup();

        /**
         * Afficher la popup.
         */
        void showPopup();

        /**
         * Récupère la popup minimisable.
         * @return la popup minimisable
         */
        IsMinimizable getMinimizablePopup();

        /**
         * Conteneur du composant fusion.
         */
        HasWidgets getConteneurComposantFusion();
    }
}
