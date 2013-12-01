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
package com.square.client.gwt.client.composant.code.postal.commune;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.ExtendedSuggestBox;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.square.client.gwt.client.event.code.postal.commune.OnSelectedItemCodePostalCommuneEvent;
import com.square.client.gwt.client.event.code.postal.commune.OnSelectedItemCodePostalCommuneEventHandler;
import com.square.client.gwt.client.event.code.postal.commune.SetRequestSuggestionsCodePostalCommuneEvent;
import com.square.client.gwt.client.event.code.postal.commune.SetRequestSuggestionsCodePostalCommuneEventHandler;
import com.square.client.gwt.client.event.code.postal.commune.SetSelectedItemCodePostalCommuneEvent;
import com.square.client.gwt.client.event.code.postal.commune.SetSelectedItemCodePostalCommuneEventHandler;
import com.square.client.gwt.client.model.IdentifiantLibelleCodePostalCommuneModel;

/**
 * Composant suggestListBox qui permet une recherche de valeur de liste.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class CodePostalCommuneSuggestBox extends Composite implements SelectionHandler<SuggestOracle.Suggestion>, BlurHandler, ValueChangeHandler<String>,
        HasValueChangeHandlers<String>, HasChangeHandlers, HasSelectionHandlers<SuggestOracle.Suggestion> {

    private ExtendedSuggestBox extendedSuggestBox;

    private IdentifiantLibelleCodePostalCommuneModel selectedItem;

    private HandlerManager handlerManager = new HandlerManager(extendedSuggestBox);

    /**
     * Constructeur.
     */
    public CodePostalCommuneSuggestBox() {
        extendedSuggestBox = new ExtendedSuggestBox(suggestOracle, true);
        extendedSuggestBox.getTextBox().addBlurHandler(this);
        extendedSuggestBox.addSelectionHandler(this);
        extendedSuggestBox.addValueChangeHandler(this);
        this.initWidget(extendedSuggestBox);
    }

    /** Oracle pour la récupération des infos. */
    private SuggestOracle suggestOracle = new SuggestOracle() {
        /** {@inheritDoc} */
        public void requestSuggestions(final Request request, final Callback callback) {
            // Récupération du libellé de l'utilisateur
            String libelleRecherche = request.getQuery().trim();
            if (ExtendedSuggestBox.ALL_SUGGESTS.equals(libelleRecherche)) {
                libelleRecherche = "";
            }

            // Création du callback pour la récupération des utilisateurs
            final AsyncCallback<List<IdentifiantLibelleCodePostalCommuneModel>> asyncCallback =
                new AsyncCallback<List<IdentifiantLibelleCodePostalCommuneModel>>() {
                    public void onFailure(Throwable caught) {
                        final ErrorPopupConfiguration config = new ErrorPopupConfiguration(caught);
                        ErrorPopup.afficher(config);
                    }

                    public void onSuccess(List<IdentifiantLibelleCodePostalCommuneModel> result) {
                        // Transformation en liste de FormulePrestaSuggestion
                        final List<IdentifiantLibelleCodePostalCommuneSuggestion> listeSuggestion = transformerIdentifiantLibelle(result);
                        // Création de la réponse avec la liste
                        final Response response = new Response(listeSuggestion);
                        // Affectation de la liste à la réponse
                        callback.onSuggestionsReady(request, response);
                    }
                };

            // Appel du service de récupération des utilisateurs
            setRequestSuggestions(libelleRecherche, asyncCallback);
        }
    };

    /**
     * Léve un evenement de type SetRequest pour demander la récupération des suggestions.
     * @param libelle le libelle.
     * @param asyncCallback le call back pour le retour.
     */
    private void setRequestSuggestions(String libelle, AsyncCallback<List<IdentifiantLibelleCodePostalCommuneModel>> asyncCallback) {
        handlerManager.fireEvent(new SetRequestSuggestionsCodePostalCommuneEvent(libelle, asyncCallback));
    }

    /**
     * Methode appelée lors de la sélection d'un item.
     * @param libelleVille le libelle de la ville
     */
    private void onSelectedItem(IdentifiantLibelleCodePostalCommuneModel item) {
        handlerManager.fireEvent(new OnSelectedItemCodePostalCommuneEvent(item));
    }

    /**
     * Léve un evenement de type SetSelectedItem pour demander la récupération de la valeur par son id.
     * @param id l'id.
     * @param asyncCallback le call back pour le retour.
     */
    private void setSelectedItem(Long id, AsyncCallback<List<IdentifiantLibelleCodePostalCommuneModel>> asyncCallback) {
        handlerManager.fireEvent(new SetSelectedItemCodePostalCommuneEvent(id, asyncCallback));
    }

    /**
     * Sélectionne l'item de la suggestListBox.
     * @param item l'item
     */
    public void setSelectedItem(IdentifiantLibelleCodePostalCommuneModel item) {
        setSelectedItem(item.getIdCommune(), new AsyncCallback<List<IdentifiantLibelleCodePostalCommuneModel>>() {
            @Override
            public void onFailure(Throwable caught) {
                clean();
            }

            @Override
            public void onSuccess(List<IdentifiantLibelleCodePostalCommuneModel> result) {
                if (result != null && result.size() == 1) {
                    selectedItem = result.get(0);
                    extendedSuggestBox.setText(result.get(0).getLibelleCodePostal());
                }
                else {
                    clean();
                }
            }
        });
    }

    /**
     * Récupère l'item selectionné de la suggestListBox.
     * @return l'item selectionné de la suggestListBox
     */
    public IdentifiantLibelleCodePostalCommuneModel getSelectedItem() {
        return selectedItem;
    }

    /**
     * Nettoie le composant.
     */
    public void clean() {
        extendedSuggestBox.setText("");
    }

    /**
     * Active/Désactive la suggestbox.
     * @param enabled true si on veut activer, false si on veut désactiver.
     */
    public void setEnabled(boolean enabled) {
        extendedSuggestBox.setEnabled(enabled);
    }

    /**
     * Méthode utilitaire permettant de transformer une liste de IdentifiantLibelleGwt en liste de IdentifiantLibelleCodePostalCommuneSuggestion.
     * @param listeResultats : la liste des resultats
     * @return la liste de IdentifiantLibelleCodePostalCommuneSuggestion
     */
    public static List<IdentifiantLibelleCodePostalCommuneSuggestion> transformerIdentifiantLibelle(
        List<IdentifiantLibelleCodePostalCommuneModel> listeResultats) {
        // Création de la liste de retour
        final List<IdentifiantLibelleCodePostalCommuneSuggestion> liste = new ArrayList<IdentifiantLibelleCodePostalCommuneSuggestion>();

        if (listeResultats != null) {
            // Parcours de la liste des résultats
            for (IdentifiantLibelleCodePostalCommuneModel identifiantLibelle : listeResultats) {
                // Création de la suggestion
                liste.add(new IdentifiantLibelleCodePostalCommuneSuggestion(identifiantLibelle));
            }
        }
        return liste;
    }

    @Override
    public void onBlur(BlurEvent event) {
        if (selectedItem == null) {
            clean();
        }
    }

    @Override
    public void onSelection(SelectionEvent<Suggestion> event) {
        selectedItem = ((IdentifiantLibelleCodePostalCommuneSuggestion) event.getSelectedItem()).getIdentifiantLibelle();
        onSelectedItem(selectedItem);
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        selectedItem = null;
        onSelectedItem(null);
    }

    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<Suggestion> handler) {
        return extendedSuggestBox.addSelectionHandler(handler);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return extendedSuggestBox.addValueChangeHandler(handler);
    }
    /**
     * Ajout d'un handler.
     * @param handler l'ahandler à ajouter
     */
    public void addRequestSuggestionsHandler(SetRequestSuggestionsCodePostalCommuneEventHandler handler) {
        handlerManager.addHandler(SetRequestSuggestionsCodePostalCommuneEvent.TYPE, handler);
    }

    /**
     * Ajout d'un handler.
     * @param handler l'ahandler à ajouter
     */
    public void addSelectedItemHandler(OnSelectedItemCodePostalCommuneEventHandler handler) {
        handlerManager.addHandler(OnSelectedItemCodePostalCommuneEvent.TYPE, handler);
    }

    /**
     * Ajout d'un handler.
     * @param handler l'ahandler à ajouter
     */
    public void addSetSelectedItemHandler(SetSelectedItemCodePostalCommuneEventHandler handler) {
        handlerManager.addHandler(SetSelectedItemCodePostalCommuneEvent.TYPE, handler);
    }

    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return extendedSuggestBox.addChangeHandler(handler);
    }

}
