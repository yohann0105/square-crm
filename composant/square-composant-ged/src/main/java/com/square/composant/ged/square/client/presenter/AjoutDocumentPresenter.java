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

import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnChangeUploaderHandler;
import gwtupload.client.IUploader.OnFinishUploaderHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.gwt.module.client.exception.BusinessExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.ged.square.client.composant.popup.LoadingPopupConfiguration;
import com.square.composant.ged.square.client.composant.tagcloud.SelecteurTagCloud;
import com.square.composant.ged.square.client.content.i18n.ComposantGedConstants;
import com.square.composant.ged.square.client.content.i18n.ComposantGedMessages;
import com.square.composant.ged.square.client.event.AddDocumentEvent;
import com.square.composant.ged.square.client.model.CodeLibelleModel;
import com.square.composant.ged.square.client.model.DocumentModel;
import com.square.composant.ged.square.client.model.TypeDocumentModel;
import com.square.composant.ged.square.client.service.DocumentsServiceGwtAsync;
import com.square.composant.ged.square.client.view.ajoutdocument.AjoutDocumentViewImplConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.composants.model.SousRapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Présenter pour l'ajout et la qualification d'un document.
 * @author jgoncalves - SCUB
 */
public class AjoutDocumentPresenter extends Presenter {

    private AjoutDocumentView view;

    private DocumentsServiceGwtAsync documentService;

    private DocumentModel documentModel;

    private Long idAction;

    private static final ComposantGedMessages CONSTANTES = GWT.create(ComposantGedMessages.class);

    /**
     * Constructeur.
     * @param eventBus le bus.
     * @param numClient le numéro de la personne
     * @param idAction l'identifiant de l'action
     * @param view la vue
     * @param documentService le service de documents
     */
    public AjoutDocumentPresenter(HandlerManager eventBus, String numClient, Long idAction, AjoutDocumentView view, DocumentsServiceGwtAsync documentService) {
        super(eventBus);
        this.view = view;
        this.documentService = documentService;
        documentModel = new DocumentModel();
        documentModel.setNumeroClient(numClient);
        this.idAction = idAction;
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        view.getBoutonAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.fermer();
            }
        });
        view.getUploader().addOnFinishUploadHandler(new OnFinishUploaderHandler() {
            @Override
            public void onFinish(IUploader uploader) {

                view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().sauvegardeEnCours()));
                documentService.ajouterDocumentEtAssocierAAction(documentModel, idAction, new AsyncCallback<Object>() {

                    @Override
                    public void onFailure(Throwable caught) {
                    	if (caught.getMessage().equals(ComposantGedConstants.ERROR_FILE_RECOVER)) {
                    		view.onRpcServiceFailure(new BusinessExceptionGwt(CONSTANTES.errorFileRecover()));
                    	} else {
                    		view.onRpcServiceFailure(caught);
                    	}
                    }

                    @Override
                    public void onSuccess(Object result) {
                        view.onRpcServiceSuccess();
                        view.fermer();
                        fireEventLocalBus(new AddDocumentEvent());
                    }
                });
            }
        });
        view.getBoutonAjouter().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (controlerChampsObligatoires()) {

                    // Enregistrement du document
                    view.afficherProgressPopup(new LoadingPopupConfiguration(view.getViewConstants().uploadEnCours()));
                    view.getUploader().submit();
                }
            }
        });
        view.getUploader().addOnChangeUploadHandler(new OnChangeUploaderHandler() {
            @Override
            public void onChange(IUploader uploader) {
                documentModel.setNom(IUploader.Utils.basename(uploader.getFileName()));
            }
        });
        view.getSelecteurTypesDocuments().addValueChangeHandler(new ValueChangeHandler<List<String>>() {
            @Override
            public void onValueChange(ValueChangeEvent<List<String>> event) {
                documentModel.setTypes(new ArrayList<CodeLibelleModel>());
                for (String type : event.getValue()) {
                    documentModel.getTypes().add(new CodeLibelleModel(type));
                }
            }
        });
        view.getDateReception().addValueChangeHandler(new ValueChangeHandler<Date>() {

            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                documentModel.setDateReception(event.getValue());
            }

        });
    }

    /**
     * Afficher pour une autre personne / action.
     * @param numClient le numéro du client
     * @param newIdAction l'id de l'action
     */
    public void afficher(String numClient, Long newIdAction) {
        documentModel = new DocumentModel();
        documentModel.setNumeroClient(numClient);
        this.idAction = newIdAction;
        view.ouvrir();
    }

    /** Chargement des types de documents disponibles. */
    private void chargerTypesDocuments() {
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().chargementTypesDocuments()));
        // Chargement des types de document, sélection des cases a cocher
        documentService.getListeTypesDocuments(new AsyncCallback<List<TypeDocumentModel>>() {

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(caught);
            }

            @Override
            public void onSuccess(List<TypeDocumentModel> result) {
                view.getSelecteurTypesDocuments().setListeTypesDocumentsDisponibles(result);
                view.onRpcServiceSuccess();
            }
        });
    }

    /**
     * Le controle des champs obligatoires se fait coté client pour éviter d'uploader inutilement le fichier.
     */
    @SuppressWarnings("deprecation")
	private boolean controlerChampsObligatoires() {
        final RapportModel rapport = new RapportModel();
        rapport.setRapports(new HashMap<String, SousRapportModel>());

        if (documentModel.getTypes() == null || documentModel.getTypes().isEmpty()) {
            ajouterSousRapport(rapport, view.getViewConstants().erreurChampType(), view.getViewConstants().messageErreurChampType());
        }
        // Problème sur la gestion des évènements du dateBox, il faut passer par la vue au moment de l'enregistrement plutot que par un handler dans le onbind
        if (view.getDateReception().getValue() == null) {
            ajouterSousRapport(rapport, view.getViewConstants().erreurChampDate(), view.getViewConstants().messageErreurChampDate());
        }
        if (documentModel.getNom() == null) {
            ajouterSousRapport(rapport, view.getViewConstants().erreurChampFichier(), view.getViewConstants().messageErreurChampFichier());
        }
        if (!rapport.getRapports().isEmpty()) {
            view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
            return false;
        } else {
            return true;
        }
    }

    /**
     * Construction d'un sous rapport.
     */
    private void ajouterSousRapport(RapportModel rapport, String type, String message) {
        final SousRapportModel ssRapport = new SousRapportModel();
        ssRapport.setAttribut(type);
        ssRapport.setMessage(message);
        ssRapport.setErreur(Boolean.TRUE);
        rapport.getRapports().put(type, ssRapport);
    }

    @Override
    public void onShow(HasWidgets container) {
        chargerTypesDocuments();
        view.ouvrir();
    }

    /**
     * Interface définissant la vue.
     * @author jgoncalves - SCUB
     */
    public interface AjoutDocumentView extends View {
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

        /**
         * Affiche une popup de chargement avec barre de progression.
         * @param loadingPopupConfiguration la configuration
         */
        void afficherProgressPopup(LoadingPopupConfiguration loadingPopupConfiguration);

        /** Fermer la popup de chargement. */
        void onRpcServiceSuccess();

        /**
         * Récupération des constantes de la vue.
         * @return les constantes de la vue.
         */
        AjoutDocumentViewImplConstants getViewConstants();

        /**
         * Récupération du handler sur le bouton de fermeture.
         * @return le handler
         */
        HasClickHandlers getBoutonAnnuler();

        /**
         * Fermeture.
         */
        void fermer();

        /**
         * Ouverture.
         */
        void ouvrir();

        /**
         * Récupération du handler sur le bouton d'ajout.
         * @return le handler
         */
        HasClickHandlers getBoutonAjouter();

        /**
         * Récupération du handler sur l'upload de document.
         * @return .
         */
        IUploader getUploader();

        /**
         * Renvoi du composant de sélection des types de doc.
         * @return .
         */
        SelecteurTagCloud getSelecteurTypesDocuments();

        /**
         * Renvoi la valeur de iconeErreurChampManager.
         * @return iconeErreurChampManager
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Récupération du handler sur la date.
         * @return le handler
         */
        DecoratedCalendrierDateBox getDateReception();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

}
