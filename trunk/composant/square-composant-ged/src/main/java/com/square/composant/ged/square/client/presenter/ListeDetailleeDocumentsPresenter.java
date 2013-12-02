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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TreeItem;
import com.square.composant.ged.square.client.composant.popup.LoadingPopupConfiguration;
import com.square.composant.ged.square.client.content.i18n.ComposantGedConstants;
import com.square.composant.ged.square.client.model.CodeLibelleModel;
import com.square.composant.ged.square.client.model.CriteresRechercheDocumentModel;
import com.square.composant.ged.square.client.model.DocumentModel;
import com.square.composant.ged.square.client.service.DocumentsServiceGwtAsync;
import com.square.composant.ged.square.client.view.listedetaillee.BlocListeDocumentsDto;
import com.square.composant.ged.square.client.view.listedetaillee.LigneDocumentDto;
import com.square.composant.ged.square.client.view.listedetaillee.ListeDetailleeDocumentsViewImplConstants;

/**
 * Présenter liste de documents sous forme détaillée.
 * @author jgoncalves - SCUB
 */
public class ListeDetailleeDocumentsPresenter extends Presenter {

    private ListeDetailleeDocumentsView view;

    private DocumentsServiceGwtAsync documentService;

    private String numeroClient;

    private Map<TreeItem, DocumentModel> mapItemsDisponibles;

    /**
     * Constructeur.
     * @param eventBus le bus.
     * @param view la vue
     * @param numeroClient numeroClient
     * @param documentService le service de documents
     */
    public ListeDetailleeDocumentsPresenter(HandlerManager eventBus, String numeroClient, ListeDetailleeDocumentsView view,
        DocumentsServiceGwtAsync documentService) {
        super(eventBus);
        this.numeroClient = numeroClient;
        this.view = view;
        this.documentService = documentService;
        mapItemsDisponibles = new HashMap<TreeItem, DocumentModel>();
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        // view.getArbreDocumentsClient().addSelectionHandler(new SelectionHandler<TreeItem>() {
        // @Override
        // public void onSelection(SelectionEvent<TreeItem> event) {
        // // Chargement du document.
        // if (mapItemsDisponibles.containsKey(event.getSelectedItem())) {
        // final DocumentModel document = mapItemsDisponibles.get(event.getSelectedItem());
        // view.afficherDetailDocument(document.getNom(), document.getUrl(), document.getDateReception(), document.getTypes());
        // } else {
        // view.masquerDetail();
        // }
        // }
        // });
    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
        rafraichir();
    }

    /**
     * Rafraichir.
     */
    public void rafraichir() {
        view.nettoyer();
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().chargementDesDocumentsEnCours()));
        final CriteresRechercheDocumentModel criteres = new CriteresRechercheDocumentModel();
        criteres.setNumeroClient(numeroClient);
        // documentService.getListeArborescenteDocumentsByCriteres(criteres, new AsyncCallback<List<DossierDocumentModel>>() {
        //
        // @Override
        // public void onSuccess(List<DossierDocumentModel> result) {
        // for (DossierDocumentModel dossier : result) {
        // final TreeItem enfantItem = remplirArborescence(dossier, mapItemsDisponibles);
        // if (enfantItem != null) {
        // view.getArbreDocumentsClient().addItem(enfantItem);
        // }
        // }
        // view.onRpcServiceSuccess();
        // }
        //
        // @Override
        // public void onFailure(Throwable caught) {
        // view.onRpcServiceFailure(caught);
        // }
        // });
        documentService.getListeDocumentsByCriteres(criteres, new AsyncCallback<List<DocumentModel>>() {

            @Override
            public void onSuccess(List<DocumentModel> result) {
                final Date moinsUnMois = new Date();
                final Date moinsTroisMois = new Date();
                moinsUnMois.setMonth(moinsUnMois.getMonth() - 1);
                moinsTroisMois.setMonth(moinsTroisMois.getMonth() - 3);

                final BlocListeDocumentsDto blocMoinsUnMois =
                    new BlocListeDocumentsDto(view.getViewConstants().titreBlocMoinsUnMois(), new ArrayList<LigneDocumentDto>());
                final BlocListeDocumentsDto blocMoinsTroisMois =
                    new BlocListeDocumentsDto(view.getViewConstants().titreBlocMoinsTroisMois(), new ArrayList<LigneDocumentDto>());
                final BlocListeDocumentsDto blocPlusTroisMois =
                    new BlocListeDocumentsDto(view.getViewConstants().titreBlocPlusTroisMois(), new ArrayList<LigneDocumentDto>());

                for (DocumentModel document : result) {
                    final LigneDocumentDto ligne = new LigneDocumentDto(document.getNom(), document.getUrl(), document.getDateReception(), document.getSens());
                    if (document.getTypes() != null && document.getTypes().size() > 0) {
                        final List<String> listeTags = new ArrayList<String>();
                        for (CodeLibelleModel type : document.getTypes()) {
                            listeTags.add(type.getLibelle());
                        }
                        ligne.setTags(listeTags);
                    }
                    else if (document.getTags() != null && document.getTags().size() > 0) {
                        ligne.setTags(document.getTags());
                    }
                    if (document.getDateReception() != null && document.getDateReception().after(moinsUnMois)) {
                        blocMoinsUnMois.getListeLignes().add(ligne);
                    } else if (document.getDateReception() != null && document.getDateReception().after(moinsTroisMois)) {
                        blocMoinsTroisMois.getListeLignes().add(ligne);
                    } else {
                        blocPlusTroisMois.getListeLignes().add(ligne);
                    }
                }
                if (blocMoinsUnMois != null) {
                    Collections.sort(blocMoinsUnMois.getListeLignes());
                }
                if (blocMoinsTroisMois != null) {
                    Collections.sort(blocMoinsTroisMois.getListeLignes());
                }
                if (blocPlusTroisMois != null) {
                    Collections.sort(blocPlusTroisMois.getListeLignes());
                }

                if (!blocMoinsUnMois.getListeLignes().isEmpty()) {
                    view.ajouterBlocListeDocuments(blocMoinsUnMois);
                }
                if (!blocMoinsTroisMois.getListeLignes().isEmpty()) {
                    view.ajouterBlocListeDocuments(blocMoinsTroisMois);
                }
                if (!blocPlusTroisMois.getListeLignes().isEmpty()) {
                    view.ajouterBlocListeDocuments(blocPlusTroisMois);
                }
                if (blocMoinsUnMois.getListeLignes().isEmpty() && blocMoinsTroisMois.getListeLignes().isEmpty()
                        && blocPlusTroisMois.getListeLignes().isEmpty()) {
                    view.afficherAucunDocument();
                }
                view.onRpcServiceSuccess();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(caught);
            }
        });
    }

    /**
     * Interface définissant la vue.
     * @author jgoncalves - SCUB
     */
    public interface ListeDetailleeDocumentsView extends View {
        /**
         * Popup d'erreur.
         * @param exception l'exception
         */
        void onRpcServiceFailure(Throwable exception);

        /**
         * Affiche une popup de chargement.
         * @param loadingPopupConfiguration la configuration
         */
        void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration);

        /** Fermer la popup de chargement. */
        void onRpcServiceSuccess();

        /**
         * Récupération des constantes de la vue.
         * @return les constantes de la vue.
         */
        ListeDetailleeDocumentsViewImplConstants getViewConstants();

        /**
         * Ajout d'un bloc listant les documents.
         * @param bloc le bloc
         */
        void ajouterBlocListeDocuments(BlocListeDocumentsDto bloc);

        /**
         * Ajout d'un bloc listant les documents.
         */
        void afficherAucunDocument();

        /**
         * Nettoyage.
         */
        void nettoyer();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach ListeDetailleeDocumentsPresenter");
    }

}
