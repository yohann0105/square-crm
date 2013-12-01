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
package com.square.composant.fusion.square.client.presenter.selection.doublon;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.remote.paging.RemotePagingTable;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.fusion.square.client.event.AfficherPreparationFusionEvent;
import com.square.composant.fusion.square.client.model.RechercheDoublonCritereModel;
import com.square.composant.fusion.square.client.model.RechercheDoublonResultatModel;
import com.square.composant.fusion.square.client.service.FusionServiceGwtAsync;

/**
 * Presenter pour la vue de sélection de doublons.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class SelectionDoublonPresenter extends Presenter {

    /** Vue associée au presenter. */
    private SelectionDoublonView view;

    /** Service de fusion. */
    private FusionServiceGwtAsync fusionRpcService;

    /** Constantes de la vue. */
    private SelectionDoublonPresenterConstants presenterConstants;

    /** Les critères de recherche. */
    private RechercheDoublonCritereModel criteres;

    private Long idPersonneSelectionnee;

    /**
     * Constructeur par défaut.
     * @param eventBus le bus des évènements.
     * @param view la vue associée au presenter.
     * @param criteresRecherche les critères de recherche.
     * @param fusionRpcService le service de fusion.
     * @param idPersonneSelectionnee l'id de la personne séléctionnée
     */
    public SelectionDoublonPresenter(HandlerManager eventBus, SelectionDoublonView view, RechercheDoublonCritereModel criteresRecherche,
        FusionServiceGwtAsync fusionRpcService, Long idPersonneSelectionnee) {
        super(eventBus);
        this.view = view;
        this.fusionRpcService = fusionRpcService;
        this.criteres = criteresRecherche;
        this.idPersonneSelectionnee = idPersonneSelectionnee;

        // Création des constantes du presenter
        presenterConstants = (SelectionDoublonPresenterConstants) GWT.create(SelectionDoublonPresenterConstants.class);
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        view.getBtnValider().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                validerSelection();
            }
        });
        view.getBtnViderSelection().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.viderSelection();
            }
        });
        view.getRemotePagingHandlerManager().addHandler(SetDataProviderEvent.TYPE,
            new SetDataProviderEventHandler<RechercheDoublonCritereModel, RechercheDoublonResultatModel>() {
                @Override
                public void setDataProvider(SetDataProviderEvent<RechercheDoublonCritereModel, RechercheDoublonResultatModel> event) {
                    if (criteres != null) {
                        event.getParams().setCriterias(criteres);
                        fusionRpcService.rechercherDoublonsPourSelection(event.getParams(), event.getCallback());
                    }
                }
            });
    }

    /** Valide la sélection des deux doublons à fusionner. */
    private void validerSelection() {
        if (view.getListeIdsPersonnesSelectionnees().size() != 2) {
            ErrorPopup.afficher(new ErrorPopupConfiguration(presenterConstants.erreurSelectionIncorrect()));
        }
        else {
            // Envoie d'un évènement de demande d'ouverture dans le bus
            fireEventLocalBus(new AfficherPreparationFusionEvent(
                view.getListeIdsPersonnesSelectionnees().get(0), view.getListeIdsPersonnesSelectionnees().get(1)));
        }
    }

    @Override
    public void onShow(HasWidgets container) {
        if (criteres != null) {
            this.view.setIdPersonneSelectionnee(idPersonneSelectionnee);
            // Lance la recherche pour l'affichage des résultats
            this.view.getRemotePagingTableDoublons().rechercher();
        }
        container.add(view.asWidget());
    }

    /**
     * Relance une recherche sur une personne.
     * @param idPersonneSelectionne idPersonneSelectionnee
     * @param critere critere
     */
    public void rechercher(Long idPersonneSelectionne, RechercheDoublonCritereModel critere) {
        view.viderSelection();
        view.nettoyer();
        this.idPersonneSelectionnee = idPersonneSelectionne;
        this.criteres = critere;
        this.view.setIdPersonneSelectionnee(idPersonneSelectionnee);
        this.view.getRemotePagingTableDoublons().rechercher();
    }

    /**
     * Interface pour la vue SelectionDoublonView.
     */
    public interface SelectionDoublonView extends View {
        /**
         * Récupère le bouton de validation.
         * @return le bouton.
         */
        HasClickHandlers getBtnValider();

        /**
         * Récupère le bouton pour vider la sélection.
         * @return le bouton.
         */
        HasClickHandlers getBtnViderSelection();

        /**
         * Récupère le tableau d'affichage des résultats de la recherche des doublons.
         * @return le tableau d'affichage des résultats de la recherche des doublons
         */
        RemotePagingTable<RechercheDoublonResultatModel, RechercheDoublonCritereModel> getRemotePagingTableDoublons();

        /**
         * Récupère le gestionnaire d'évènement du tableau affichant les doublons.
         * @return le gestionnaire d'évènement du tableau affichant les doublons.
         */
        HandlerManager getRemotePagingHandlerManager();

        /**
         * Récupère la liste des identifiants des personnes sélectionnées pour la fusion.
         * @return la liste des identifiants des personnes sélectionnées pour la fusion.
         */
        List<Long> getListeIdsPersonnesSelectionnees();

        /**
         * Vide la sélection.
         */
        void viderSelection();

        /**
         * Nettoie la vue.
         */
        void nettoyer();

        /**
         * Change la personne sélectionnée.
         * @param idPersonneSelectionnee idPersonneSelectionnee
         */
        void setIdPersonneSelectionnee(Long idPersonneSelectionnee);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
