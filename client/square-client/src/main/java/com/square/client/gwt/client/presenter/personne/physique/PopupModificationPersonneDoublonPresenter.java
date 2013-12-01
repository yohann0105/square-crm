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
package com.square.client.gwt.client.presenter.personne.physique;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.event.ConfirmerModificationPersonneEvent;

/**
 * Presenter pour la vue de confirmation de modification d'une personne suite à la détection de doublons.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class PopupModificationPersonneDoublonPresenter extends Presenter {

    private PopupModificationPersonneDoublonView view;

    private Long idPersonne;

    /**
     * Constructeur.
     * @param eventBus bus d'évènements
     * @param view vue gérée par le presenter
     * @param idPersonne identifiant de la personne concernée par la confirmation de modification
     */
    public PopupModificationPersonneDoublonPresenter(HandlerManager eventBus, PopupModificationPersonneDoublonView view, Long idPersonne) {
        super(eventBus);
        this.view = view;
        this.idPersonne = idPersonne;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View asView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBind() {
        view.getAllKeyHandlersFocusPanel().addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                final int keyCode = event.getNativeKeyCode();
                if (keyCode == KeyCodes.KEY_ESCAPE) {
                    annuler();
                } else if (keyCode == KeyCodes.KEY_ENTER) {
                    enregistrer();
                }
            }
        });

        view.getClickHandlerBtnEnregistrer().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                enregistrer();
            }
        });

        view.getClickHandlerBtnEnregistrerPuisFusionner().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                enregistrerPuisFusionner();
            }
        });

        view.getClickHandlerBtnAnnuler().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                annuler();
            }
        });
    }

    public void afficher(Long idPersonne) {
        this.idPersonne = idPersonne;
        this.view.afficherPopup();
    }

    private void enregistrer() {
        view.cacherPopup();
        // On envoie un évènement pour enregistrer les modifications effectuées sur la personne
        fireEventLocalBus(new ConfirmerModificationPersonneEvent(idPersonne, ConfirmerModificationPersonneEvent.Action.ENREGISTRER));
    }

    private void enregistrerPuisFusionner() {
        view.cacherPopup();
        // On envoie un évènement pour enregistrer les modifications effectuées sur la personne et lancer la procédure de fusion
        fireEventLocalBus(new ConfirmerModificationPersonneEvent(idPersonne, ConfirmerModificationPersonneEvent.Action.ENREGISTRER_PUIS_FUSIONNER));
    }

    private void annuler() {
        view.cacherPopup();
        // On envoie un évènement pour annuler les modifications effectuées sur la personne
        final ConfirmerModificationPersonneEvent enregistrerModifsEvent = new ConfirmerModificationPersonneEvent(idPersonne, ConfirmerModificationPersonneEvent.Action.ANNULER);
        fireEventLocalBus(enregistrerModifsEvent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onShow(HasWidgets container) {
        this.view.afficherPopup();
    }

    /**
     * Interface de la vue associée au presenter.
     * 
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     *
     */
    public interface PopupModificationPersonneDoublonView extends View {
        /** Affiche la popup. */
        void afficherPopup();

        /** Ferme la popup. */
        void cacherPopup();

        /**
         * Accesseur AllKeyHandlers du focus panel.
         * @return le handler
         */
        HasAllKeyHandlers getAllKeyHandlersFocusPanel();

        /**
         * Accesseur ClickHandlers du bouton enregistrer.
         * @return le handler
         */
        HasClickHandlers getClickHandlerBtnEnregistrer();

        /**
         * Accesseur ClickHandlers du bouton enregistrer puis fusionner.
         * @return le handler
         */
        HasClickHandlers getClickHandlerBtnEnregistrerPuisFusionner();

        /**
         * Accesseur ClickHandlers du bouton annuler.
         * @return le handler
         */
        HasClickHandlers getClickHandlerBtnAnnuler();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

}
