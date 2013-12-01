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
package com.square.composant.cotisations.client.presenter.bloc.entete;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.square.composant.cotisations.client.content.i18n.ComposantCotisationsConstants;
import com.square.composant.cotisations.client.model.CotisationModel;
import com.square.composant.cotisations.client.model.DetailCotisationModel;
import com.square.composant.cotisations.client.model.DetailEncaissementModel;
import com.square.composant.cotisations.client.view.bloc.entete.BlocEnteteCotisationViewImplConstants;
import com.square.composant.cotisations.client.view.bloc.entete.BlocEnteteCotisationViewImplMessages;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;

/**
 * Presenter pour un bloc entete.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class BlocEnteteCotisationPresenter extends Presenter {

    /** Vue associé au presenter. */
    private BlocEnteteCotisationView view;

    private CotisationModel cotisation;

    /**
     * Presenter Cotisations.
     * @param eventBus eventBus
     * @param view view
     * @param enteteCotisations enteteCotisations
     */
    public BlocEnteteCotisationPresenter(HandlerManager eventBus, BlocEnteteCotisationView view, CotisationModel enteteCotisations) {
        super(eventBus);
        this.view = view;
        this.cotisation = enteteCotisations;
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        // on charge d'abord l'entete
        final List<ChampSynthese> listeChamps = new ArrayList<ChampSynthese>();
        listeChamps.add(new ChampSynthese(new Label(cotisation.getDateDebut() + " - " + cotisation.getDateFin()), view.getViewConstants().periode(), true));
        listeChamps.add(new ChampSynthese(new Label(cotisation.getModePaiement().getLibelle()), view.getViewConstants().modePaiement(), true));
        listeChamps.add(new ChampSynthese(new Label(ComposantCotisationsConstants.NUMBERFORMAT.format(cotisation.getMontant())), view.getViewConstants()
                .montant(), true));
        listeChamps.add(new ChampSynthese(new Label(ComposantCotisationsConstants.NUMBERFORMAT.format(cotisation.getMontantRegle())), view.getViewConstants()
                .montantRegle(), true));
        listeChamps.add(new ChampSynthese(new Label(cotisation.getSituation().getLibelle()), view.getViewConstants().situation(), true));
        view.chargerEntete(listeChamps);

        if (cotisation.getListeDetailsEncaissement() != null) {
            view.chargerDetailsEncaissement(cotisation.getListeDetailsEncaissement());
        }
        if (cotisation.getListeDetailsCotisation() != null) {
            view.chargerDetailsCotisation(cotisation.getListeDetailsCotisation());
        }
    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
    }

    /**
     * Interface pour la vue MoteurRechercheCotisationsView.
     */
    public interface BlocEnteteCotisationView extends View {
        /**
         * Charge l'entete du bloc entete.
         * @param listeChamps la liste des champs
         */
        void chargerEntete(List<ChampSynthese> listeChamps);

        /**
         * Recupere les constantes.
         * @return les constantes
         */
        BlocEnteteCotisationViewImplConstants getViewConstants();

        /**
         * Recupere les messages.
         * @return les messages
         */
        BlocEnteteCotisationViewImplMessages getViewMessages();

        /**
         * Methode appelé lorsque un service Rpc s'est deroulé correctement.
         */
        void onRpcServiceSuccess();

        /**
         * Methode appelé lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Affiche un message de chargement.
         * @param config la config
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Charge les détails de cotisations.
         * @param lignes les détails de cotisations
         */
        void chargerDetailsCotisation(List<DetailCotisationModel> lignes);

        /**
         * Charge les détails d'encaissement.
         * @param lignes les détails d'encaissement
         */
        void chargerDetailsEncaissement(List<DetailEncaissementModel> lignes);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
