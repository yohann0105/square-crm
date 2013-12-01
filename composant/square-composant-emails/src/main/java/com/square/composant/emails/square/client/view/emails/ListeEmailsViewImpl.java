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
package com.square.composant.emails.square.client.view.emails;

import org.gwtwidgets.client.ui.pagination.Column;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEvent;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingSortGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.remote.paging.RemotePagingTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.emails.square.client.content.i18n.ComposantEmailsConstants;
import com.square.composant.emails.square.client.model.RechercheEmailRequeteModel;
import com.square.composant.emails.square.client.model.RechercheEmailResultatModel;
import com.square.composant.emails.square.client.presenter.emails.ListeEmailsPresenter.ListeEmailsView;

/**
 * Implémentation de la vue permettant de consulter les emails échangés entre la Smatis et une personne.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class ListeEmailsViewImpl extends Composite implements ListeEmailsView {

    private static final ListeEmailsViewImplConstants VIEW_CONSTANTS = GWT.create(ListeEmailsViewImplConstants.class);

    private RemotePagingTable<RechercheEmailResultatModel, RechercheEmailRequeteModel> remotePagingTableEmails;

    /** Pour recuperer les evenements sur le tableau dans le Presenter. */
    private HandlerManager remotePagingHandlerManager;

    /**
     * Constructeur.
     */
    public ListeEmailsViewImpl() {
        super();
        remotePagingTableEmails =
            new RemotePagingTable<RechercheEmailResultatModel, RechercheEmailRequeteModel>(ListeEmailsViewImplConstants.NB_RESULTATS, true) {
                @Override
                public void setCellClicked(RechercheEmailResultatModel objet) {
                    remotePagingHandlerManager.fireEvent(new SetCellClickedEvent<RechercheEmailResultatModel>(objet));
                }

                @Override
                public void setDataProvider(RemotePagingCriteriasGwt<RechercheEmailRequeteModel> params,
                    AsyncCallback<RemotePagingResultsGwt<RechercheEmailResultatModel>> callback) {
                    remotePagingHandlerManager.fireEvent(new SetDataProviderEvent<RechercheEmailRequeteModel, RechercheEmailResultatModel>(params, callback));
                }

                @Override
                public int setDefaultSortAsc() {
                    return RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                }

                @Override
                public String setDefaultSortField() {
                    return VIEW_CONSTANTS.fieldDateEnvoi();
                }

                @Override
                public Column[] setHeader() {
                    return new Column[] {new Column(VIEW_CONSTANTS.headerContact(), VIEW_CONSTANTS.fieldContact()),
                        new Column(VIEW_CONSTANTS.headerSujet(), VIEW_CONSTANTS.fieldSujet()),
                        new Column(VIEW_CONSTANTS.headerDateEnvoi(), VIEW_CONSTANTS.fieldDateEnvoi())};
                }

                @Override
                public void setRow(int row, RechercheEmailResultatModel email) {
                    String contact = "";
                    if ((email.getNomExpediteur() == null || "".equals(email.getNomExpediteur()))
                            && (email.getPrenomExpediteur() == null || "".equals(email.getPrenomExpediteur()))) {
                        contact = email.getAdresseMailExpediteur();
                    }
                    else {
                        contact = email.getPrenomExpediteur() + " " + email.getNomExpediteur();
                    }
                    this.setWidget(row, 0, new Label(contact));
                    this.setWidget(row, 1, new Label(email.getSujetMail()));
                    this.setWidget(row, 2, new Label(email.getDateEnvoi()));
                }
            };
        remotePagingHandlerManager = new HandlerManager(remotePagingTableEmails);
        remotePagingTableEmails.setWidth(ComposantEmailsConstants.POURCENT_100);

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setSpacing(10);
        conteneur.setWidth(ComposantEmailsConstants.POURCENT_100);
        conteneur.add(remotePagingTableEmails);
        initWidget(conteneur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerManager getRemotePagingHandlerManager() {
        return remotePagingHandlerManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RemotePagingTable<RechercheEmailResultatModel, RechercheEmailRequeteModel> getRemotePagingTable() {
        return remotePagingTableEmails;
    }

}
