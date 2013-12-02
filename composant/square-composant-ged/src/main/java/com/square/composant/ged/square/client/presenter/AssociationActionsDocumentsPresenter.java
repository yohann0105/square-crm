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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.square.composant.ged.square.client.composant.popup.LoadingPopupConfiguration;
import com.square.composant.ged.square.client.event.AddDocumentEvent;
import com.square.composant.ged.square.client.event.AddDocumentEventHandler;
import com.square.composant.ged.square.client.event.AssociationActionDocumentEvent;
import com.square.composant.ged.square.client.model.CriteresRechercheDocumentModel;
import com.square.composant.ged.square.client.model.DocumentModel;
import com.square.composant.ged.square.client.model.DossierDocumentModel;
import com.square.composant.ged.square.client.service.DocumentsServiceGwtAsync;
import com.square.composant.ged.square.client.view.ajoutdocument.AjoutDocumentViewImpl;
import com.square.composant.ged.square.client.view.associationdocumentsaction.AssociationDocumentsActionViewImplConstants;

/**
 * Présenter liste de documents dispo & ceux affectés à une actions.
 * @author jgoncalves - SCUB
 */
public class AssociationActionsDocumentsPresenter extends Presenter {

    private List<String> listeIdsDocumentsAction;

    private String numClient;

    private Long idAction;

    private AssociationDocumentsActionView view;

    private DocumentsServiceGwtAsync documentService;

    private Map<TreeItem, DocumentModel> mapItemsDisponibles;

    private Map<TreeItem, DocumentModel> mapItemsAssocies;

    private Map<TreeItem, DossierDocumentModel> mapItemsDossiersDisponibles;

    private Map<TreeItem, DossierDocumentModel> mapItemsDossiersAssocies;

    private Map<String, TreeItem> mapIdItemsDisponibles;

    private Map<String, TreeItem> mapIdItemsAssocies;

    private Map<String, TreeItem> mapIdItemsDossierDisponibles;

    private Map<String, TreeItem> mapIdItemsDossierAssocies;

    private AjoutDocumentPresenter ajoutDocumentPresenter;

    /**
     * Constructeur.
     * @param eventBus le bus.
     * @param listeIdsDocuments la liste des ids de document.
     * @param idAction l'id action
     * @param numClient le numéro de client.
     * @param view la vue
     * @param documentService le service de documents
     */
    public AssociationActionsDocumentsPresenter(HandlerManager eventBus, List<String> listeIdsDocuments, Long idAction, String numClient,
        AssociationDocumentsActionView view, DocumentsServiceGwtAsync documentService) {
        super(eventBus);
        this.listeIdsDocumentsAction = listeIdsDocuments;
        this.numClient = numClient;
        this.idAction = idAction;
        this.view = view;
        this.documentService = documentService;
        mapItemsDisponibles = new HashMap<TreeItem, DocumentModel>();
        mapItemsAssocies = new HashMap<TreeItem, DocumentModel>();
        mapItemsDossiersDisponibles = new HashMap<TreeItem, DossierDocumentModel>();
        mapItemsDossiersAssocies = new HashMap<TreeItem, DossierDocumentModel>();
        mapIdItemsDisponibles = new HashMap<String, TreeItem>();
        mapIdItemsAssocies = new HashMap<String, TreeItem>();
        mapIdItemsDossierDisponibles = new HashMap<String, TreeItem>();
        mapIdItemsDossierAssocies = new HashMap<String, TreeItem>();
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {

        // Construction de la liste arborescente des documents associés à l'utilisateur
        view.getBoutonAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.fermer();
            }
        });
        view.getBoutonAjouter().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                // Ajout de l'entrée sélectionnée à la liste des documents associés.
                if (mapItemsDisponibles.containsKey(view.getArbreDocumentsClient().getSelectedItem())) {
                    // Déjà affecté ?
                    final DocumentModel docSelectionne = mapItemsDisponibles.get(view.getArbreDocumentsClient().getSelectedItem());
                    if (!mapIdItemsAssocies.containsKey(docSelectionne.getParent().getIdentifiant() + docSelectionne.getIdentifiant())) {
                        // Ajout à la liste & affichage
                        if (!listeIdsDocumentsAction.contains(docSelectionne.getIdentifiant())) {
                            listeIdsDocumentsAction.add(docSelectionne.getIdentifiant());
                        }

                        // Ajout de l'élément
                        final TreeItem parent = selectionneParentDyna(docSelectionne.getParent());
                        final TreeItem treeItem = new TreeItem(docSelectionne.getNom());
                        treeItem.addStyleName("elementArborescence");
                        parent.addItem(treeItem);
                        mapIdItemsAssocies.put(docSelectionne.getParent().getIdentifiant() + docSelectionne.getIdentifiant(), treeItem);
                        mapItemsAssocies.put(treeItem, docSelectionne);
                        ouvrirParents(treeItem);
                    }
                }
            }
        });
        view.getBoutonEnlever().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Suppression de l'entitée sélectionnée de la liste des documents associés.
                final TreeItem selectedItem = view.getArbreDocumentsAction().getSelectedItem();
                if (mapItemsAssocies.containsKey(selectedItem)) {
                    final DocumentModel document = mapItemsAssocies.get(view.getArbreDocumentsAction().getSelectedItem());
                    listeIdsDocumentsAction.remove(document.getIdentifiant());
                    final TreeItem parent = selectedItem.getParentItem();
                    selectedItem.remove();
                    mapItemsAssocies.remove(selectedItem);
                    mapIdItemsAssocies.remove(document.getParent().getIdentifiant() + document.getIdentifiant());
                    nettoyerParents(parent);
                }
            }
        });
        view.getBoutonAjouterDocument().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (ajoutDocumentPresenter == null) {
                    ajoutDocumentPresenter = new AjoutDocumentPresenter(eventBus, numClient, idAction, new AjoutDocumentViewImpl(), documentService);
                    ajoutDocumentPresenter.showPresenter(null);
                    ajoutDocumentPresenter.addEventHandlerToLocalBus(AddDocumentEvent.TYPE, new AddDocumentEventHandler() {
                        @Override
                        public void onAddDocument(AddDocumentEvent event) {
                            // Si un document a été ajouté à une action, on ferme la popup
                            view.fermer();
                            fireEventLocalBus(new AssociationActionDocumentEvent());
                        }
                    });
                } else {
                    ajoutDocumentPresenter.afficher(numClient, idAction);
                }
            }
        });
        view.getBoutonEnregistrer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().chargementDesDocumentsEnCours()));
                documentService.associerDocumentsAAction(idAction, listeIdsDocumentsAction, new AsyncCallback<Object>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(caught);
                    }

                    @Override
                    public void onSuccess(Object result) {
                        view.onRpcServiceSuccess();
                        view.fermer();
                        fireEventLocalBus(new AssociationActionDocumentEvent());
                    }
                });
            }
        });
    }

    /**
     * Nettoyage des noeuds parents.
     */
    private void nettoyerParents(TreeItem noeud) {
        if (noeud != null) {
            final TreeItem parent = noeud.getParentItem();
            if (noeud.getChildCount() == 0) {
                if (mapItemsDossiersAssocies.containsKey(noeud)) {
                    final DossierDocumentModel dossierModel = mapItemsDossiersAssocies.get(noeud);
                    mapIdItemsDossierAssocies.remove(dossierModel.getIdentifiant());
                    mapItemsDossiersAssocies.remove(noeud);
                }
                noeud.remove();
            }
            if (parent != null) {
                nettoyerParents(parent);
            }
        }
    }

    /**
     * Nettoyage des noeuds parents.
     */
    private void ouvrirParents(TreeItem noeud) {
        if (noeud.getParentItem() != null) {
            noeud.getParentItem().setState(true);
            ouvrirParents(noeud.getParentItem());
        }
    }

    /**
     * Sélectionne le parent (créée l'arborescence si besoin).
     * @param dossier le dossier parent a sélectionner
     */
    private TreeItem selectionneParentDyna(DossierDocumentModel dossier) {
        if (mapIdItemsDossierAssocies.containsKey(dossier.getIdentifiant())) {
            return mapIdItemsDossierAssocies.get(dossier.getIdentifiant());
        } else {
            final TreeItem dossierItem = new TreeItem(dossier.getNom());
            dossierItem.addStyleName("elementArborescence");
            mapIdItemsDossierAssocies.put(dossier.getIdentifiant(), dossierItem);
            mapItemsDossiersAssocies.put(dossierItem, dossier);

            if (dossier.getParent() != null) {
                final TreeItem parent = selectionneParentDyna(dossier.getParent());
                parent.addItem(dossierItem);
            } else {
                view.getArbreDocumentsAction().addItem(dossierItem);
            }
            return dossierItem;
        }
    }

    @Override
    public void onShow(HasWidgets container) {
        rechercher(true);
    }

    /**
     * lancer une recherche.
     * @param chargerExistant charger l'existant.
     */
    public void rechercher(final boolean chargerExistant) {
        mapItemsDisponibles = new HashMap<TreeItem, DocumentModel>();
        mapItemsDossiersDisponibles = new HashMap<TreeItem, DossierDocumentModel>();
        mapIdItemsDisponibles = new HashMap<String, TreeItem>();
        mapIdItemsDossierDisponibles = new HashMap<String, TreeItem>();
        view.getArbreDocumentsClient().clear();

        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().chargementDesDocumentsEnCours()));
            }
        });

        final CriteresRechercheDocumentModel criteres = new CriteresRechercheDocumentModel();
        criteres.setNumeroClient(numClient);
        documentService.getListeArborescenteDocumentsByCriteres(criteres, new AsyncCallback<List<DossierDocumentModel>>() {

            @Override
            public void onSuccess(List<DossierDocumentModel> result) {
                for (DossierDocumentModel dossier : result) {
                    final TreeItem enfantItem =
                        remplirArborescence(dossier, mapItemsDisponibles, mapIdItemsDisponibles, mapItemsDossiersDisponibles, mapIdItemsDossierDisponibles);
                    if (enfantItem != null) {
                        view.getArbreDocumentsClient().addItem(enfantItem);
                    }
                }
                if (chargerExistant) {
                    chargerAssociationsExistantes();
                } else {
                    view.onRpcServiceSuccess();
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(caught);
            }
        });
    }

    private void chargerAssociationsExistantes() {
        final CriteresRechercheDocumentModel criteres = new CriteresRechercheDocumentModel();
        criteres.setNumeroClient(numClient);
        criteres.setIds(listeIdsDocumentsAction);
        view.getArbreDocumentsAction().clear();
        documentService.getListeArborescenteDocumentsByCriteres(criteres, new AsyncCallback<List<DossierDocumentModel>>() {

            @Override
            public void onSuccess(List<DossierDocumentModel> result) {
                for (DossierDocumentModel dossier : result) {
                    final TreeItem enfantItem =
                        remplirArborescence(dossier, mapItemsAssocies, mapIdItemsAssocies, mapItemsDossiersAssocies, mapIdItemsDossierAssocies);
                    if (enfantItem != null) {
                        view.getArbreDocumentsAction().addItem(enfantItem);
                    }
                }
                view.ouvrir();
                view.onRpcServiceSuccess();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(caught);
            }
        });
    }

    /** Remplissage d'un arbre. */
    private TreeItem remplirArborescence(DossierDocumentModel dossier, Map<TreeItem, DocumentModel> mapCorrespondance,
        Map<String, TreeItem> mapCorrespondanceId, Map<TreeItem, DossierDocumentModel> mapCorrespondanceDossiers,
        Map<String, TreeItem> mapCorrespondanceDossierId) {
        final TreeItem treeItem = new TreeItem(dossier.getNom());
        treeItem.addStyleName("elementArborescence");
        for (DossierDocumentModel dossierEnfant : dossier.getDossiers()) {
            final TreeItem enfantItem =
                remplirArborescence(dossierEnfant, mapCorrespondance, mapCorrespondanceId, mapCorrespondanceDossiers, mapCorrespondanceDossierId);
            if (enfantItem != null) {
                treeItem.addItem(enfantItem);
            }
        }

        for (DocumentModel document : dossier.getDocuments()) {
            final TreeItem itemDoc = new TreeItem(document.getNom());
            itemDoc.addStyleName("elementArborescence");
            mapCorrespondance.put(itemDoc, document);
            // La clé est le parent + le doc car un meme doc peut apparaitre dans plusieurs rubriques
            mapCorrespondanceId.put(document.getParent().getIdentifiant() + document.getIdentifiant(), itemDoc);
            treeItem.addItem(itemDoc);
        }

        // On ne renvoie que les documents ou les dossiers non vides
        if (treeItem.getChildCount() > 0) {
            mapCorrespondanceDossiers.put(treeItem, dossier);
            mapCorrespondanceDossierId.put(dossier.getIdentifiant(), treeItem);
            return treeItem;
        } else {
            return null;
        }
    }

    /**
     * Interface définissant la vue.
     * @author jgoncalves - SCUB
     */
    public interface AssociationDocumentsActionView extends View {
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
        AssociationDocumentsActionViewImplConstants getViewConstants();

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
         * Récupération de l'arbre des documents du client.
         * @return l'arbre
         */
        Tree getArbreDocumentsClient();

        /**
         * Récupération de l'arbre des documents de l'action.
         * @return l'arbre
         */
        Tree getArbreDocumentsAction();

        /**
         * Récupération du handler sur le bouton d'ajout.
         * @return le handler
         */
        HasClickHandlers getBoutonAjouter();

        /**
         * Récupération du handler sur le bouton d'enlèvement.
         * @return le handler
         */
        HasClickHandlers getBoutonEnlever();

        /**
         * Récupération du handler sur l'ajout d'un document.
         * @return le handler
         */
        HasClickHandlers getBoutonAjouterDocument();

        /**
         * Récupération du handler sur l'enregistrement des associations.
         * @return le handler
         */
        HasClickHandlers getBoutonEnregistrer();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach AssociationActionsDocumentsPresenter");
    }

}
