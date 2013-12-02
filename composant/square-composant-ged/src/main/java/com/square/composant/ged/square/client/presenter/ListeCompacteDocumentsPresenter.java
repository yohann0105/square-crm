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
package com.square.composant.ged.square.client.presenter;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.ged.square.client.bundle.ComposantGedBundle;
import com.square.composant.ged.square.client.constants.ApplicationConstants;
import com.square.composant.ged.square.client.event.AssociationActionDocumentEvent;
import com.square.composant.ged.square.client.event.AssociationActionDocumentEventHandler;
import com.square.composant.ged.square.client.model.DocumentModel;
import com.square.composant.ged.square.client.service.DocumentsServiceGwtAsync;
import com.square.composant.ged.square.client.view.associationdocumentsaction.AssociationDocumentsActionViewImpl;
import com.square.composant.ged.square.client.view.listecompacte.ListeCompacteDocumentsViewImplConstants;

/**
 * Présenter liste de documents sous forme compacte.
 * @author jgoncalves - SCUB
 */
public class ListeCompacteDocumentsPresenter extends Presenter {

    private List<String> listeIdsDocuments;

    private ListeCompacteDocumentsView view;

    private DocumentsServiceGwtAsync documentService;

    private Long idAction;

    private String numeroClient;

    /** LISTE DES PRESENTER. */
    private AssociationActionsDocumentsPresenter associationActionsDocumentsPresenter;

    /**
     * Constructeur.
     * @param eventBus le bus.
     * @param idAction l'identifiant de l'action
     * @param numeroClient numeroClient
     * @param view la vue
     * @param documentService le service de documents
     */
    public ListeCompacteDocumentsPresenter(HandlerManager eventBus, Long idAction, String numeroClient, ListeCompacteDocumentsView view,
        DocumentsServiceGwtAsync documentService) {
        super(eventBus);
        ComposantGedBundle.INSTANCE.css().ensureInjected();

        this.idAction = idAction;
        this.numeroClient = numeroClient;
        this.view = view;
        this.documentService = documentService;
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        view.getLienAssocierDocument().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                if (associationActionsDocumentsPresenter == null) {
                    associationActionsDocumentsPresenter =
                        addChildPresenter(new AssociationActionsDocumentsPresenter(eventBus, listeIdsDocuments, idAction, numeroClient,
                            new AssociationDocumentsActionViewImpl(), documentService));
                    associationActionsDocumentsPresenter.showPresenter(null);

                    associationActionsDocumentsPresenter.addEventHandlerToLocalBus(AssociationActionDocumentEvent.TYPE,
                        new AssociationActionDocumentEventHandler() {
                            @Override
                            public void onAssociationDocument(AssociationActionDocumentEvent event) {
                                chargerDocuments();
                            }
                        });
                } else {
                    associationActionsDocumentsPresenter.rechercher(true);
                }
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        chargerDocuments();
        container.add(view.asWidget());
    }

    /**
     * Permet de recharger la liste des documents pour une action.
     */
    public void chargerDocuments() {
        view.vider();
        documentService.getListeDocumentsAssociesAAction(idAction, new AsyncCallback<List<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(caught);
            }

            @Override
            public void onSuccess(List<String> result) {
                listeIdsDocuments = result;
                documentService.getListeDocumentsByListeIds(listeIdsDocuments, new AsyncCallback<List<DocumentModel>>() {

                    @Override
                    public void onSuccess(List<DocumentModel> result) {
                        view.onRpcServiceSuccess();
                        if (listeIdsDocuments.isEmpty()) {
                            result.clear();
                        }
                        for (final DocumentModel doc : result) {
                            HasClickHandlers lien =
                                view.ajouterLien(doc.getNom(), doc.getUrl(), doc.getTypeMime() == null ? null : doc.getTypeMime().replaceAll("/", "_"));
                            lien.addClickHandler(new ClickHandler() {

                                @Override
                                public void onClick(ClickEvent event) {
                                    if (doc.getUrl() != null)
                                        Window.open(doc.getUrl(), "_blank", "resizable=yes,menubar=no,location=no");
                                    else{
                                        //TODO : Supprimer le log
                                        String url = ApplicationConstants.URL_SERVLET_DOWNLOAD + "?" + doc.getIdentifiant().trim();
                                        Window.open(url, "_blank", "resizable=yes,menubar=no,location=no");
                                    }
                                        
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(caught);
                    }
                });
            }
        });
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                view.onRpcServiceCall(view.getViewConstants().chargementDesDocumentsEnCours());
            }
        });
    }

    /**
     * Interface définissant la vue.
     * @author jgoncalves - SCUB
     */
    public interface ListeCompacteDocumentsView extends View {

        /**
         * Affiche un message d'attente avant le RPC.
         * @param message le message d'attente
         */
        void onRpcServiceCall(String message);

        /**
         * Masque le message d'attente et affiche les erreurs remontées par le RPC.
         * @param exception l'exception levée
         */
        void onRpcServiceFailure(Throwable exception);

        /**
         * Masque le message d'attente après le RPC.
         */
        void onRpcServiceSuccess();

        /**
         * Ajoute un lien vers un document.
         * @param nom le nom
         * @param url l'url
         * @param image le nom de l'image
         * @return clickHandler sur le lien
         */
        HasClickHandlers ajouterLien(String nom, String url, String image);

        /**
         * Vide la vue.
         */
        void vider();

        /**
         * Récupération des constantes de la vue.
         * @return les constantes de la vue.
         */
        ListeCompacteDocumentsViewImplConstants getViewConstants();

        /**
         * Récupération du lien d'association.
         * @return le handler sur le lien
         */
        HasClickHandlers getLienAssocierDocument();

    }

    @Override
    public void onDetach() {
        GWT.log("onDetach ListeCompacteDocumentPresenter");
    }

}
