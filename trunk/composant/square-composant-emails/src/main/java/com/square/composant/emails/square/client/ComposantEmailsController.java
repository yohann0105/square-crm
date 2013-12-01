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
package com.square.composant.emails.square.client;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.emails.square.client.bundle.ComposantEmailsResources;
import com.square.composant.emails.square.client.content.i18n.ComposantEmailsConstants;
import com.square.composant.emails.square.client.event.DisplayViewGroupeEmailsEvent;
import com.square.composant.emails.square.client.event.DisplayViewGroupeEmailsEventHandler;
import com.square.composant.emails.square.client.event.DisplayViewListeEmailsEvent;
import com.square.composant.emails.square.client.event.DisplayViewListeEmailsEventHandler;
import com.square.composant.emails.square.client.event.ListeEmailsChargeesEvent;
import com.square.composant.emails.square.client.event.ListeEmailsChargeesEventHandler;
import com.square.composant.emails.square.client.presenter.emails.GroupeEmailsPresenter;
import com.square.composant.emails.square.client.presenter.emails.ListeEmailsPresenter;
import com.square.composant.emails.square.client.service.EmailServiceGWT;
import com.square.composant.emails.square.client.service.EmailServiceGWTAsync;
import com.square.composant.emails.square.client.view.emails.GroupeEmailsViewImpl;
import com.square.composant.emails.square.client.view.emails.ListeEmailsViewImpl;

/**
 * Controller du composant.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class ComposantEmailsController extends Presenter {

    /** Constantes du composant pour les emails. */
    public static final ComposantEmailsConstants CONSTANTS = GWT.create(ComposantEmailsConstants.class);

    /** Instance des ressources. */
    public static final ComposantEmailsResources RESOURCES = GWT.create(ComposantEmailsResources.class);

    private ComposantEmailsView view;

    private String numeroAdherent;

    private EmailServiceGWTAsync emailServiceAsync;

    private GroupeEmailsPresenter groupeEmailsPresenter;
    private ListeEmailsPresenter listeEmailsPresenter;

    /**
     * Constructeur.
     * @param eventBus bus de transport des évènements
     * @param view vue associée au presenter
     * @param numeroAdherent numéro de l'adhérent
     */
    public ComposantEmailsController(HandlerManager eventBus, ComposantEmailsView view, String numeroAdherent) {
        super(eventBus);
        this.view = view;
        this.emailServiceAsync = GWT.create(EmailServiceGWT.class);
        this.numeroAdherent = numeroAdherent;

        // instancier les resources ici
        StyleInjector.inject(RESOURCES.css().getText());

        groupeEmailsPresenter = addChildPresenter(new GroupeEmailsPresenter(eventBus, new GroupeEmailsViewImpl(), emailServiceAsync));
        groupeEmailsPresenter.showPresenter(view.getSlotGroupeEmails());
        listeEmailsPresenter = addChildPresenter(new ListeEmailsPresenter(eventBus, new ListeEmailsViewImpl(), emailServiceAsync, numeroAdherent));
        listeEmailsPresenter.showPresenter(view.getSlotListeEmails());
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
        listeEmailsPresenter.addEventHandlerToLocalBus(DisplayViewGroupeEmailsEvent.TYPE, new DisplayViewGroupeEmailsEventHandler() {
            @Override
            public void displayView(DisplayViewGroupeEmailsEvent event) {
                groupeEmailsPresenter.afficherEmail(event.getIdEmail());
                view.switchVue();
            }
        });
        listeEmailsPresenter.addEventHandlerToLocalBus(ListeEmailsChargeesEvent.TYPE, new ListeEmailsChargeesEventHandler() {
            @Override
            public void updateInfosEmails(ListeEmailsChargeesEvent event) {
                // Propagation
                fireEventLocalBus(event);
            }
        });
        groupeEmailsPresenter.addEventHandlerToLocalBus(DisplayViewListeEmailsEvent.TYPE, new DisplayViewListeEmailsEventHandler() {
            @Override
            public void displayView(DisplayViewListeEmailsEvent event) {
                listeEmailsPresenter.afficherListe();
                view.switchVue();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
        listeEmailsPresenter.afficherListe();
    }

    /**
     * Interface de la vue du composant.
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     */
    public interface ComposantEmailsView extends View {
        /**
         * Récupération de l'emplacement des groupes de mails.
         * @return l'emplacement
         */
        HasWidgets getSlotGroupeEmails();

        /**
         * Récupération de l'emplacement des listes de mails.
         * @return l'emplacement
         */
        HasWidgets getSlotListeEmails();

        /**
         * Passage d'un slot a l'autre.
         */
        void switchVue();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

	/**Get numeroAdherent.
	 * @return the numeroAdherent
	 */
	public String getNumeroAdherent() {
		return numeroAdherent;
	}

}
