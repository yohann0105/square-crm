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
/**
 * 
 */
package com.square.client.gwt.client.presenter.personne;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.OrgChart;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;

/**
 * Classe representation des Relations sous forme graphique.
 * @author Goumard Stephane (scub) - SCUB
 */
public class PersonneRelationsVisualizationPresenter extends Presenter {

    /**
     * Vue associé.
     */
    private PersonneRelationsVisualizationView view;

    /** Le service sur les personnes. */
    private PersonneServiceGwtAsync personneRpcService;

    /** Identifiant de la personne principale. */
    private Long idPersonne;

    /** identifiant limitation pour les groupements. */
    private List<Long> filtreGroupements;

    /** identifiant limitation pour les groupements. */
    private List<Long> filtrePasDansGroupements;

    /**
     * Presenter affichage graphique des relations.
     * @param eventBus .
     * @param view .
     * @param personneRpcService .
     * @param filtreGroupements .
     * @param idPersonne .
     * @param filtrePasDansGroupements .
     */
    public PersonneRelationsVisualizationPresenter(HandlerManager eventBus, PersonneRelationsVisualizationView view,
        PersonneServiceGwtAsync personneRpcService, List<Long> filtreGroupements, Long idPersonne, List<Long> filtrePasDansGroupements) {
        super(eventBus);
        this.view = view;
        this.idPersonne = idPersonne;
        this.filtreGroupements = filtreGroupements;
        this.filtrePasDansGroupements = filtrePasDansGroupements;
        this.personneRpcService = personneRpcService;
    }

    /** Récupère les relations. */
    public void initListeRelations() {
        view.afficherLoadingPopup();
        personneRpcService.relationOrgChartDataStore(idPersonne, filtreGroupements, filtrePasDansGroupements, new AsyncCallback<String[][]>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught.getMessage()));
            }

            @Override
            public void onSuccess(String[][] relationsDatas) {
                view.afficherGraphique(relationsDatas);
                view.onRpcServiceSuccess();
            }
        });
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        view.getChart().addSelectHandler(createSelectHandler(view.getChart(), view.getData()));
    }

    @Override
    public void onShow(HasWidgets container) {
        initListeRelations();
        container.add(view.asWidget());
    }

    /**
     * Evenement sur le clic.
     * @param chart
     * @param datas
     * @return SelectHandler
     */
    private SelectHandler createSelectHandler(final OrgChart chart, final DataTable data) {
        return new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                // May be multiple selections.
                final JsArray<Selection> selections = chart.getSelections();
                final Selection celluleSelectionnee = selections.get(0);
                final int rowCell = celluleSelectionnee.getRow();
                if (rowCell != 0) {
                    final Long idPersCible = Long.parseLong(data.getValueString(rowCell, 0));
                    personneRpcService.rechercherPersonneParId(idPersCible, new AsyncCallback<PersonneBaseModel>() {
                        @Override
                        public void onSuccess(PersonneBaseModel result) {
                            final PersonneModel personneCible = (PersonneModel) result;
                            fireEventGlobalBus(new OpenPersonEvent(idPersCible, personneCible.getNumClient(),
                                personneCible.getNom() != null ? personneCible.getNom() : "" ,
                                    personneCible.getPrenom() != null ? personneCible.getPrenom() : "",
                                (personneCible.getNaturePersonne() != null) ? personneCible.getNaturePersonne().getIdentifiant() : null));
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                        }
                    });
                }
            }
        };
    }

    /**
     * Vue associé au presenter.
     * @author Goumard Stephane (Scub) - SCUB
     */
    public interface PersonneRelationsVisualizationView extends View {

        /**
         * Retourner la chart.
         * @return la charte
         */
        OrgChart getChart();

        /**
         * Retourner la table de données.
         * @return la table de données
         */
        DataTable getData();

        /**
         * Afficher le graphique.
         * @param relationsDatas les relations sous formes de matrice.
         */
        void afficherGraphique(final String[][] relationsDatas);

        /**
         * Afficher la popup de chargement.
         */
        void afficherLoadingPopup();

        /**
         * Notification a la vue reussite d'un appel RPc.
         */
        void onRpcServiceSuccess();

        /**
         * Affiche une popUp d'erreur.
         * @param errorPopupConfiguration la configuration de la popup d'erreur
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
