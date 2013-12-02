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
package com.square.client.gwt.client.presenter.personne;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasListGridCellClickHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.ListGridCellClickEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.ListGridCellClickEventHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.event.ModifierPaysTelephoneEvent;
import com.square.client.gwt.client.event.SuggestPaysEvent;
import com.square.client.gwt.client.event.SuggestPaysEventHandler;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.PaysSimpleModel;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.view.personne.coordonnees.PopupSelectionPaysViewImplConstants;

/**
 * Presenter de la popup de sélection d'un pays.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupSelectionPaysPresenter extends Presenter {

    /** Service des dimensions. */
    private DimensionServiceGwtAsync dimensionServiceRpcService;

    /** Vue associée au presenter. */
    private PopupSelectionPaysView view;

    /** Indique s'il s'aguit du téléphone principal ou non. */
    private Boolean isTelephonePrincipal;

    /** Constantes de la popup. */
    private PopupSelectionPaysPresenterConstants popupConstants;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènement.
     * @param dimensionServiceRpcService le service des dimensions.
     * @param view la vue associée au presenter.
     * @param isTelephonePrincipal indique s'il s'agit du téléphone principal ou non.
     */
    public PopupSelectionPaysPresenter(HandlerManager eventBus, DimensionServiceGwtAsync dimensionServiceRpcService, PopupSelectionPaysView view,
        Boolean isTelephonePrincipal) {
        super(eventBus);
        this.dimensionServiceRpcService = dimensionServiceRpcService;
        this.view = view;
        this.isTelephonePrincipal = isTelephonePrincipal;
        this.popupConstants = GWT.create(PopupSelectionPaysPresenterConstants.class);
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        view.addSuggestHandler(new SuggestPaysEventHandler() {
            @Override
            public void suggest(SuggestPaysEvent event) {
                if (event.getSuggest().length() >= 3) {
                    final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                    criteres.setLibelle(event.getSuggest());
                    dimensionServiceRpcService.rechercherSimplePaysParCriteres(criteres, event.getCallBack());
                }
            }
        });
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
            }
        });
        view.getResult().addCellClicktHandler(new ListGridCellClickEventHandler() {
            @Override
            public void onCellClick(ListGridCellClickEvent event) {
                // Envoi d'un évènement pour signaler la modification du pays
                final PaysSimpleModel paysSelectionne = view.getPaysSelectionne(event.getRow());
                final String url = PopupSelectionPaysViewImplConstants.DEBUT_URL_IMAGE_PAYS + paysSelectionne.getId()
                    + PopupSelectionPaysViewImplConstants.FIN_URL_IMAGE_PAYS;
                final String title = popupConstants.pays() + " " + paysSelectionne.getLibelle() + " - "
                + popupConstants.indicatifTel() + " " + paysSelectionne.getIndicatifTelephone() + " - "
                + popupConstants.formatNumTel() + " " + paysSelectionne.getFormatTelephone();
                fireEventLocalBus(new ModifierPaysTelephoneEvent(url, title, paysSelectionne, isTelephonePrincipal));
                view.hidePopup();
            }
        });
        view.getTbSaisie().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                view.fireSuggestEvent();
            }
        });
    }

    /**
     * Retourne l'identifiant du pays sélectionné.
     * @return l'identifiant du pays sélectionné.
     */
    public Long getIdPaysSelectionne() {
        return view.getIdPaysSelectionne();
    }

    @Override
    public void onShow(HasWidgets container) {
        view.showPopup();
    }

    /**
     * Permet d'afficher la popup.
     */
    public void showPopup() {
        view.showPopup();
    }

    /**
     * Interface de la vue associée au presenter.
     * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
     */
    public interface PopupSelectionPaysView extends View {

        /** Affiche la popup. */
        void showPopup();

        /** Cache la popup. */
        void hidePopup();

        /**
         * Ajout d'un handler de suggestion.
         * @param handler le handler.
         * @return .
         */
        HandlerRegistration addSuggestHandler(SuggestPaysEventHandler handler);

        /**
         * Accesseur sur le ClickHandler du bouton d'annulation.
         * @return le handler.
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Récupère l'identifiant du pays sélectionné.
         * @return l'identifiant du pays sélectionné.
         */
        Long getIdPaysSelectionne();

        /**
         * Accesseur sur le handler des résultats.
         * @return le handler.
         */
        HasListGridCellClickHandler getResult();

        /**
         * Récupère le pays sélectionné.
         * @param row la ligne du pays sélectionné.
         * @return le pays sélectionné.
         */
        PaysSimpleModel getPaysSelectionne(int row);

        /**
         * Accesseur sur le handler de la TextBox de saisie.
         * @return le handler.
         */
        HasKeyUpHandlers getTbSaisie();

        /** Lance l'évènement de suggestion. */
        void fireSuggestEvent();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
