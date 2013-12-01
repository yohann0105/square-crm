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
package com.square.composant.emails.square.client.presenter.emails;

import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.remote.paging.RemotePagingTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.emails.square.client.event.DisplayViewGroupeEmailsEvent;
import com.square.composant.emails.square.client.event.ListeEmailsChargeesEvent;
import com.square.composant.emails.square.client.model.PagingModel;
import com.square.composant.emails.square.client.model.RechercheEmailRequeteModel;
import com.square.composant.emails.square.client.model.RechercheEmailResultatModel;
import com.square.composant.emails.square.client.service.EmailServiceGWTAsync;

/**
 * Presenter capable de gérer l'affichage des emails échangés entre un adhérent et la Smatis.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class ListeEmailsPresenter extends Presenter {

    private ListeEmailsView view;

    private String numeroAdherent;

    private EmailServiceGWTAsync emailServiceAsync;

    /**
     * Constructeur.
     * @param eventBus bus de transport des évènements
     * @param view l'instance de la vue à associer au presenter.
     * @param emailServiceGWTAsync emailServiceGWTAsync
     * @param numeroAdherent numéro de l'adhérent ciblé.
     */
    public ListeEmailsPresenter(HandlerManager eventBus, ListeEmailsView view, EmailServiceGWTAsync emailServiceGWTAsync, String numeroAdherent) {
        super(eventBus);
        this.view = view;
        this.emailServiceAsync = emailServiceGWTAsync;
        this.numeroAdherent = numeroAdherent;
        // Récupération du nombre total d'emails
        getNbEmails();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View asView() {
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBind() {
        view.getRemotePagingHandlerManager().addHandler(SetDataProviderEvent.TYPE,
            new SetDataProviderEventHandler<RechercheEmailRequeteModel, RechercheEmailResultatModel>() {
                @Override
                public void setDataProvider(SetDataProviderEvent<RechercheEmailRequeteModel, RechercheEmailResultatModel> event) {
                    final RechercheEmailRequeteModel criterias = new RechercheEmailRequeteModel();
                    criterias.setNumeroAdherent(numeroAdherent);
                    final PagingModel pagination = new PagingModel();
                    pagination.setFirstResult(event.getParams().getFirstResult());
                    pagination.setMaxResult(event.getParams().getMaxResult());
                    criterias.setPagination(pagination);
                    event.getParams().setCriterias(criterias);
                    emailServiceAsync.rechercherEmailsParCriteres(event.getParams().getCriterias(), event.getCallback());
                }
            });
        view.getRemotePagingHandlerManager().addHandler(SetCellClickedEvent.TYPE, new SetCellClickedEventHandler<RechercheEmailResultatModel>() {
            @Override
            public void setCellClicked(SetCellClickedEvent<RechercheEmailResultatModel> event) {
                fireEventLocalBus(new DisplayViewGroupeEmailsEvent(event.getModele().getIdentifiantMail()));
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
    }

    /**
     * Afficher la liste.
     */
    public void afficherListe() {
        view.getRemotePagingTable().rechercher();
    }

    /**
     * Récupère le nombre total d'emails.
     */
    private void getNbEmails() {
        emailServiceAsync.getNombreEmailsAdherent(numeroAdherent, new AsyncCallback<Integer>() {
            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess(Integer result) {
                fireEventLocalBus(new ListeEmailsChargeesEvent(numeroAdherent, result));
            }
        });
    }

    /**
     * Interface de la vue associée au presenter.
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     */
    public interface ListeEmailsView extends View {
        /**
         * Retourne le gestionnaire d'evenement du tableau.
         * @return le gestionnaire d'evenement.
         */
        HandlerManager getRemotePagingHandlerManager();

        /**
         * Retourne le tableau paginé.
         * @return tableau paginé.
         */
        RemotePagingTable<RechercheEmailResultatModel, RechercheEmailRequeteModel> getRemotePagingTable();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

}
