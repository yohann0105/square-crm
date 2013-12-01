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

import java.util.Iterator;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.emails.square.client.ComposantEmailsController;
import com.square.composant.emails.square.client.content.i18n.ComposantEmailsConstants;
import com.square.composant.emails.square.client.event.DisplayViewListeEmailsEvent;
import com.square.composant.emails.square.client.model.EmailModel;
import com.square.composant.emails.square.client.model.GroupeEmailModel;
import com.square.composant.emails.square.client.model.PersonneEmailModel;
import com.square.composant.emails.square.client.service.EmailServiceGWTAsync;
import com.square.composant.emails.square.client.view.emails.CorpsEmailViewImpl;
import com.square.composant.emails.square.client.view.emails.HeaderEmailViewImpl;

/**
 * Presenter capable de gérer la consultation d'un groupe d'emails.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class GroupeEmailsPresenter extends Presenter {

    private GroupeEmailsView view;

    private EmailServiceGWTAsync emailServiceGWTAsync;

    private GroupeEmailModel groupeEmail;

    /**
     * Constructeur.
     * @param eventBus bus de transport des évènements
     * @param view la vue associée au presenter
     * @param emailServiceGWTAsync service asynchrone pour les emails
     */
    public GroupeEmailsPresenter(HandlerManager eventBus, GroupeEmailsView view, EmailServiceGWTAsync emailServiceGWTAsync) {
        super(eventBus);
        this.view = view;
        this.emailServiceGWTAsync = emailServiceGWTAsync;
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
        view.getBtnRetour().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEventLocalBus(new DisplayViewListeEmailsEvent());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onShow(final HasWidgets container) {
        container.add(view.asWidget());
    }

    /**
     * Affichage d'un email.
     * @param idEmail l'email
     */
    public void afficherEmail(Long idEmail) {
        LoadingPopup.afficher(new LoadingPopupConfiguration(ComposantEmailsController.CONSTANTS.chargementGroupeEmailsEnCours()));
        // On charge le groupe d'emails.
        emailServiceGWTAsync.getGroupeEmailParEmail(idEmail, new AsyncCallback<GroupeEmailModel>() {
            @Override
            public void onFailure(Throwable caught) {
                onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(GroupeEmailModel result) {
                view .nettoyer();
                groupeEmail = result;
                final List<EmailModel> listeEmails = groupeEmail.getListeEmails();
                if (!listeEmails.isEmpty()) {
                    for (int i = 0; i < listeEmails.size(); i++) {
                        final EmailModel email = listeEmails.get(i);
                        final HeaderEmailView headerEmailView = new HeaderEmailViewImpl();
                        headerEmailView.getDateEnvoiEmail().setText(email.getDateEnvoi());
                        headerEmailView.getResumeEmail().setHTML(buildHeaderEmail(email));
                        final CorpsEmailView corpsEmailView = new CorpsEmailViewImpl();
                        corpsEmailView.getCorpsEmail().setHTML(email.getCorps());
                        view.addEmail(headerEmailView, corpsEmailView);

                        if (i == 0) {
                            // On récupère le sujet du premier email
                            view.getSujetGroupeEmails().setText(email.getSujet());
                        }
                    }
                    // On affiche le contenu du dernier email de la conversation
                    view.showLastEmail();
                }
                view.asWidget().setSize(ComposantEmailsConstants.POURCENT_100, ComposantEmailsConstants.POURCENT_100);
                LoadingPopup.stopAll();
            }
        });
    }

    /**
     * Construit le header à afficher pour chaque email.
     * @param email l'email
     * @return le header construit
     */
    public String buildHeaderEmail(EmailModel email) {
        final StringBuffer header = new StringBuffer(email.getExpediteur().getAdresseEmail());
        header.append(" &agrave; ");
        for (final Iterator<PersonneEmailModel> it = email.getListeDestinataires().iterator(); it.hasNext();) {
            final PersonneEmailModel destinataire = it.next();
            header.append(destinataire.getAdresseEmail());
            if (it.hasNext()) {
                header.append(", ");
            }
        }
        return header.toString();
    }

    /**
     * Affiche une popup d'erreur.
     * @param errorPopupConfiguration configuration de la popup d'erreur.
     */
    private void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    /**
     * Interface de la vue associée au presenter.
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     */
    public interface GroupeEmailsView extends View {
        /**
         * Accesseur sujet du groupe d'emails.
         * @return le sujet du premier email du groupe.
         */
        HasText getSujetGroupeEmails();

        /**
         * Ajoute un email à la pile.
         * @param headerEmail vue associée au header de l'email.
         * @param corpsEmailView vue associée au corps de l'email.
         */
        void addEmail(HeaderEmailView headerEmail, CorpsEmailView corpsEmailView);

        /**
         * Déplie / affiche le dernier email du groupe.
         */
        void showLastEmail();

        /**
         * Lien du bouton pour retourner à la liste d'emails.
         * @return handler.
         */
        HasClickHandlers getBtnRetour();

        /**
         * Nettoyage de la vue.
         */
        void nettoyer();
    }

    /**
     * Interface de la vue qui représente un header d'email.
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     */
    public interface HeaderEmailView extends View {
        /**
         * Accesseur résumé de l'email.
         * @return le résumé.
         */
        HasHTML getResumeEmail();

        /**
         * Accesseur date d'envoi de l'email.
         * @return la date d'envoi.
         */
        HasText getDateEnvoiEmail();
    }

    /**
     * Interface de la vue qui représente un corps d'email.
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     */
    public interface CorpsEmailView extends View {
        /**
         * Accesseur corps de l'email.
         * @return le corps de l'email.
         */
        HasHTML getCorpsEmail();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

}
