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
package com.square.composant.tarificateur.square.client.presenter.popup.doublon;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.tarificateur.square.client.event.CreerAssureSocialEvent;
import com.square.composant.tarificateur.square.client.model.doublons.PersonneDoublonModel;
import com.square.composant.tarificateur.square.client.model.personne.AssureSocialModel;

/**
 * Presenter de la popup indiquant la possibilité de doublon lors de la création d'une personne.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupCreationAssureSocialDoublonPresenter extends Presenter {

    /** Vue associée au presenter. */
    private PopupCreationAssureSocialDoublonView view;

    /** La personne en cours. */
    private AssureSocialModel assureSocialEnCours;

    private List<PersonneDoublonModel> listeDoublons;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènement.
     * @param view la vue associée au presenter.
     * @param personne la personne à créer.
     * @param listeDoublons la liste des doublons.
     */
    public PopupCreationAssureSocialDoublonPresenter(HandlerManager eventBus, PopupCreationAssureSocialDoublonView view, AssureSocialModel personne,
        List<PersonneDoublonModel> listeDoublons) {
        super(eventBus);
        this.view = view;
        this.assureSocialEnCours = personne;
        this.listeDoublons = listeDoublons;
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        view.getBtnCreer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEventLocalBus(new CreerAssureSocialEvent(assureSocialEnCours));
                view.cacherPopup();
            }
        });
        view.getBtnRattacher().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                for (PersonneDoublonModel doublon : listeDoublons) {
                    if (doublon.getId().equals(view.getIdPersonneSelectionnee())) {
                        assureSocialEnCours.setEidPersonne(doublon.getId());
                        assureSocialEnCours.setCivilite(doublon.getCivilite());
                        assureSocialEnCours.setInfoSante(doublon.getInfoSante());
                        fireEventLocalBus(new CreerAssureSocialEvent(assureSocialEnCours));
                        break;
                    }
                }
                view.cacherPopup();
            }
        });
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.cacherPopup();
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        view.afficherPopup();
    }
    /**
     * Afficher.
     * @param personne personne
     * @param listeDoublons listeDoublons
     */
    public void afficher(AssureSocialModel personne, List<PersonneDoublonModel> listeDoublons) {
        this.assureSocialEnCours = personne;
        this.listeDoublons = listeDoublons;
        view.rafraichir(personne.getNom(), personne.getPrenom(), listeDoublons);
        view.afficherPopup();
    }

    /**
     * Interface de la vue du presenter.
     * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
     */
    public interface PopupCreationAssureSocialDoublonView extends View {

        /**
         * Accesseur sur le click handler du bouton de création.
         * @return le handler.
         */
        HasClickHandlers getBtnCreer();

        /**
         * Accesseur sur le click handler du bouton de rattachement.
         * @return le handler.
         */
        HasClickHandlers getBtnRattacher();

        /**
         * Accesseur sur le click handler du bouton de rattachement.
         * @return le handler.
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Retourne le gestionnaire d'évènement du tableau.
         * @return le gestionnaire d'évènement.
         */
        HandlerManager getRemotePagingHandlerManager();

        /**
         * Récupère l'identifiant de la personne sélectionnée pour le rattachement.
         * @return l'identifiant de la personne sélectionnée.
         */
        Long getIdPersonneSelectionnee();

        /** Affiche la popup. */
        void afficherPopup();

        /** Ferme la popup. */
        void cacherPopup();

        /**
         * Rafraichir.
         * @param nom nom
         * @param prenom prenom
         * @param listeDoublons listedoublons
         */
        void rafraichir(String nom, String prenom, List<PersonneDoublonModel> listeDoublons);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
