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
package com.square.composant.fusion.square.client.presenter.fusion;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.confirm.ConfirmPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.PopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.fusion.square.client.event.FinFusionPersonnesEvent;
import com.square.composant.fusion.square.client.event.MajSelectionDoublonsEvent;
import com.square.composant.fusion.square.client.exception.ControleIntegriteFusionExceptionGwt;
import com.square.composant.fusion.square.client.model.ParametresFusionModel;
import com.square.composant.fusion.square.client.model.PersonneCibleFusionModel;
import com.square.composant.fusion.square.client.model.PersonneSourceFusionModel;
import com.square.composant.fusion.square.client.service.FusionServiceGwtAsync;

/**
 * Presenter pour la vue de fusion.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class FusionPresenter extends Presenter {

    /** Vue associée au presenter. */
    private FusionView view;

    /** Service de fusion. */
    private FusionServiceGwtAsync fusionRpcService;

    /** Constantes du presenter. */
    private FusionPresenterConstants presenterConstants;

    /** Identifiant de la première personne pour la fusion. */
    private Long idPersonne1;

    /** Identifiant de la seconde personne pour la fusion. */
    private Long idPersonne2;

    /** Personne source en cours. */
    private PersonneSourceFusionModel personneSourceEncours;

    /** Personne cible en cours. */
    private PersonneCibleFusionModel personneCibleEnCours;

    /**
     * Constructeur.
     * @param eventBus le bus des évènements.
     * @param fusionRpcService le service de fusion.
     * @param view la vue associée au presenter.
     */
    public FusionPresenter(HandlerManager eventBus, FusionServiceGwtAsync fusionRpcService, FusionView view) {
        super(eventBus);
        this.view = view;
        this.fusionRpcService = fusionRpcService;

        // Création des constantes du presenter
        presenterConstants = (FusionPresenterConstants) GWT.create(FusionPresenterConstants.class);
    }

    /**
     * Charge les identifiants des personnes à fusionner.
     * @param id1 l'identifiant de la première personne (source).
     * @param id2 l'identifiant nrid de la seconde personne (cible).
     */
    public void chargerIdsPersonnes(Long id1, Long id2) {
        this.idPersonne1 = id1;
        this.idPersonne2 = id2;
        if (idPersonne1 != null && idPersonne2 != null) {
            chargerPersonnesFusion();
        }
    }

    public void nettoyer() {
        view.nettoyer();
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        view.getBtnInverser().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final Long idAux = idPersonne1;
                idPersonne1 = idPersonne2;
                idPersonne2 = idAux;
                chargerPersonnesFusion();
            }
        });
        view.getBtnSuivant().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Chargement du résumé
                view.chargerResume(personneSourceEncours);
            }
        });
        view.getBtnPrecedent().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.afficherSelectionChampsFusion();
            }
        });
        view.getBtnValiderFusion().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final PopupCallback callback = new PopupCallback() {
                    public void onResult(boolean result) {
                        if (result) {
                            validerFusion();
                        }
                    }
                };
                final PopupConfiguration config = new PopupConfiguration(presenterConstants.confirmationFusion());
                config.setTitle(presenterConstants.titrePopupConfirmationFusion());
                config.setCallback(callback);
                ConfirmPopup.afficher(config);
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
    }

    /** Charge les personnes pour la fusion. */
    private void chargerPersonnesFusion() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(presenterConstants.chargementPersonnesFusionEnCours()));
        final AsyncCallback<ParametresFusionModel> asyncCallback = new AsyncCallback<ParametresFusionModel>() {
            @Override
            public void onFailure(Throwable caught) {
                LoadingPopup.stopAll();
                ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(ParametresFusionModel result) {
                LoadingPopup.stop();
                personneSourceEncours = result.getPersonneSource();
                personneCibleEnCours = result.getPersonneCible();
                view.chargerPersonnes(personneSourceEncours, personneCibleEnCours);
            }
        };
        fusionRpcService.preparerFusion(idPersonne1, idPersonne2, asyncCallback);
    }

    /**
     * Vailde la fusion.
     */
    private void validerFusion() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(presenterConstants.validationFusionEnCours()));
        // Récupération de la personne source et de la personne cible
        final ParametresFusionModel parametresFusion = new ParametresFusionModel();
        parametresFusion.setPersonneSource(personneSourceEncours);
        parametresFusion.setPersonneCible(personneCibleEnCours);

        // Appel asynchrone
        final AsyncCallback<Object> asyncCallback = new AsyncCallback<Object>() {

            @Override
            public void onFailure(Throwable caught) {
                LoadingPopup.stopAll();
                if (caught instanceof ControleIntegriteFusionExceptionGwt) {
                    afficherMessageErreurControleIntegrite((ControleIntegriteFusionExceptionGwt) caught);
                }
                else {
                    ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
                }
            }

            @Override
            public void onSuccess(Object result) {
                LoadingPopup.stop();
                // Envoi d'un évènement dans le bus pour mettre à jour la sélection de doublon
                fireEventLocalBus(new MajSelectionDoublonsEvent());
                // Envoi d'un évènement pour indiquer la fin de la fusion des deux personnes
                fireEventLocalBus(new FinFusionPersonnesEvent(personneSourceEncours.getIdentifiant(), personneCibleEnCours.getIdentifiant()));

                // Initialisation de la vue de la fusion
                view.initVue();
            }
        };
        fusionRpcService.validerFusion(parametresFusion, asyncCallback);
    }

    /**
     * Affiche le message d'erreur du contrôle d'intégrité.
     * @param caught l'exception du contrôle d'intégrité
     */
    private void afficherMessageErreurControleIntegrite(ControleIntegriteFusionExceptionGwt caught) {
        final StringBuffer message = new StringBuffer(presenterConstants.erreurControleIntegriteCible() + "<br/>");
        if (caught != null && caught.getListeErreurs() != null && !caught.getListeErreurs().isEmpty()) {
            for (String erreur : caught.getListeErreurs()) {
                if (message.length() != 0) {
                    message.append("<br/>");
                }
                message.append(erreur);
            }
        }
        ErrorPopup.afficher(new ErrorPopupConfiguration(message.toString()));
    }

    /**
     * Interface pour la vue FusionView.
     */
    public interface FusionView extends View {
        /**
         * Charge les propriétés des personnes dans la vue.
         * @param personneSourceEncours les propriétés de la personne source.
         * @param personneCibleEnCours les propriétés de la personne cible.
         */
        void chargerPersonnes(PersonneSourceFusionModel personneSourceEncours, PersonneCibleFusionModel personneCibleEnCours);

        /**
         * Récupère le bouton d'inversion.
         * @return la bouton.
         */
        HasClickHandlers getBtnInverser();

        /**
         * Récupère le bouton pour pousuivre la fusion.
         * @return la bouton.
         */
        HasClickHandlers getBtnSuivant();

        /**
         * Récupère le bouton pour retourner à l'étape précédente (sélection des champs à fusionner).
         * @return la bouton.
         */
        HasClickHandlers getBtnPrecedent();

        /**
         * Récupère le bouton de validation de la fusion.
         * @return la bouton.
         */
        HasClickHandlers getBtnValiderFusion();

        /** Affiche la sélection des champs pour la fusion. */
        void afficherSelectionChampsFusion();

        /**
         * Charge le résumé de la fusion.
         * @param personneSourceEncours les informations de la personne source à fusionner.
         */
        void chargerResume(PersonneSourceFusionModel personneSourceEncours);

        /** Initialise la vue. */
        void initVue();

        /** nettoie la vue. */
        void nettoyer();
    }

    @Override
    public void onDetach() {
       GWT.log("onDetach " + this);
    }
}
