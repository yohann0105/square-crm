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
package com.square.client.gwt.client.presenter.actions;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.actions.AjoutCampagneEvent;
import com.square.client.gwt.client.event.actions.AjoutPersonneEvent;
import com.square.client.gwt.client.model.ConstantesModel;

/**
 * Presenter pour les actions.
 */
public class ActionsPresenter extends Presenter {

    /**
     * Vue Associé aux actions.
     */
    final ActionsView view;

    private final ConstantesModel constantes;

    /**
     * Les constantes d'application.
     */
    final AppControllerConstants appControllerConstants;

    /**
     * Constructeur.
     * @param eventBus le bus d'evenement
     * @param view la vue
     * @param appControllerConstants les constantes
     * @param constantes les constantes chargées
     */
    public ActionsPresenter(HandlerManager eventBus, ActionsView view, AppControllerConstants appControllerConstants, ConstantesModel constantes) {
        super(eventBus);
        this.view = view;
        this.appControllerConstants = appControllerConstants;
        this.constantes = constantes;
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        view.getAjouterPersonneHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEventLocalBus(new AjoutPersonneEvent());
            }
        });

        // Si l'utilisateur connecté a le rôle pour gérer les campagnes
        if (constantes.isHasRoleCampagne()) {
            // On affiche le bouton permettant l'ajout de campagnes
            view.setAjouterCampagneVisible(true);
            view.getAjouterCampagneHandler().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    fireEventLocalBus(new AjoutCampagneEvent());
                }
            });
        } else {
            view.setAjouterCampagneVisible(false);
        }
    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
    }

    /**
     * Interface pour la vue PersonnePhysiqueMoteurRechercheView.
     */
    public interface ActionsView extends View {

        /**
         * Retourne un nouvel emplacement pour le menu.
         * @return le widget qui sert de conteneur pour encapsuler l
         */
        HasWidgets getNewContextMenuSlot();

        /**
         * Bouton pour ajouter une personne.
         * @return le composant
         */
        HasClickHandlers getAjouterPersonneHandler();

        /**
         * Permet de spécifier si le bouton d'ajout de campagne est visible ou non.
         * @param visible true pour afficher, false pour cacher.
         */
        void setAjouterCampagneVisible(boolean visible);

        /**
         * Boutton pour ajouter une campagne.
         * @return le composant
         */
        HasClickHandlers getAjouterCampagneHandler();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
