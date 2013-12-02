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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.OrgChart;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.SimpleValueChangeEvent;
import com.square.client.gwt.client.event.SimpleValueChangeEventHandler;
import com.square.client.gwt.client.event.UpdateTabNameEvent;
import com.square.client.gwt.client.event.UpdateTabNameEventHandler;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneMoraleServiceGwtAsync;
import com.square.client.gwt.client.service.PersonnePhysiqueServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.personne.relations.PersonneRelationPopupViewImpl;
import com.square.client.gwt.client.view.personne.relations.PersonneRelationsViewImpl;
import com.square.client.gwt.client.view.personne.relations.PersonneRelationsVisualizationViewImpl;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Ce presenter permet de jonglet enter les deux modes de representation des relations Graphique ou Editable.
 * @author Goumard Stephane (stephane.goumard@scub.net) - SCUB
 */
public class PersonneRelationsModePresenter extends Presenter {
    /** Vue du mode de relation. */
    private PersonneRelationsModeView view;

    /** Le service sur les personnes. */
    private PersonneServiceGwtAsync personneRpcService;

    /** Service pour les personnes physiques. */
    private PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService;

    /** Service pour les personnes morales. */
    private PersonneMoraleServiceGwtAsync personneMoraleRpcService;

    /** Identifiant de la personne principale. */
    private Long idPersonne;

    /** Identifiant de la nature de la personne principale. */
    private Long idNaturePersonne;

    /** identifiant limitation pour les groupement. */
    private List<Long> filtreGroupements;

    /** identifiant limitation pour les groupement. */
    private List<Long> filtrePasDansGroupements;

    /** Le service sur les dimensions. */
    private DimensionServiceGwtAsync dimensionRpcService;

    /** Constantes. */
    private ConstantesModel constantes;

    /** Gestion des modes d'edition. **/
    private int modeCourant;

    private String nomPersonne;

    /** Type de la personne source. */
    private Long typePersonneSource;

    /** Service de gestion des aides . */
    private AideServiceGwtAsync aideService;

    private DeskBar deskBar;

    /** LISTE DES PRESENTER. **/
    private PersonneRelationsPopupPresenter personneRelationsPopupPresenter;

    private PersonneRelationsPopupPresenter personneRelationsFamillePopupPresenter;

    private PersonneRelationsVisualizationPresenter personneRelationsVisualizationPresenter;

    private PersonneRelationsPresenter personneRelationsPresenter;
    
    /** Constantes du presenter. */
    private PersonneRelationsModePresenterConstants presenterConstants;

    /**
     * Constructeur par defaut.
     * @param eventBus .
     * @param view .
     * @param personneRpcService .
     * @param personnePhysiqueRpcService .
     * @param personneMoraleRpcService .
     * @param dimensionRpcService .
     * @param constantes .
     * @param filtreGroupements .
     * @param nomPersonne nom de la personne.
     * @param filtrePasDansGroupements .
     * @param typePersonneSource le type de la personne source
     * @param initModeEdition mode d'dition à afficher par defaut.
     * @param aideService service d'aide.
     * @param idPersonne identifiant de la personne.
     * @param nomPersonne le nom de la personne.
     * @param idNaturePersonne l'identifiant de la nature de la personne.
     * @param deskBar deskBar.
     */
    public PersonneRelationsModePresenter(HandlerManager eventBus, PersonneRelationsModeView view, PersonneServiceGwtAsync personneRpcService,
        PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService, PersonneMoraleServiceGwtAsync personneMoraleRpcService,
        DimensionServiceGwtAsync dimensionRpcService, ConstantesModel constantes, List<Long> filtreGroupements, Long idPersonne, String nomPersonne,
        Long idNaturePersonne, List<Long> filtrePasDansGroupements, Long typePersonneSource, int initModeEdition, AideServiceGwtAsync aideService,
        DeskBar deskBar) {
        super(eventBus);
        this.personneRpcService = personneRpcService;
        this.personnePhysiqueRpcService = personnePhysiqueRpcService;
        this.personneMoraleRpcService = personneMoraleRpcService;
        this.dimensionRpcService = dimensionRpcService;
        this.constantes = constantes;
        this.view = view;
        this.idPersonne = idPersonne;
        this.nomPersonne = nomPersonne;
        this.idNaturePersonne = idNaturePersonne;
        this.filtreGroupements = filtreGroupements;
        this.filtrePasDansGroupements = filtrePasDansGroupements;
        this.modeCourant = initModeEdition;
        this.typePersonneSource = typePersonneSource;
        this.aideService = aideService;
        this.deskBar = deskBar;
        this.presenterConstants = GWT.create(PersonneRelationsModePresenterConstants.class);
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        view.getBtAjouterRelationGenerale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (idNaturePersonne != null
                    && (constantes.getIdNaturePersonneVivier().equals(idNaturePersonne) || constantes.getIdNaturePersonneBeneficiaireVivier().equals(
                        idNaturePersonne))) {
                    view.afficherPopupErreur(new ErrorPopupConfiguration(presenterConstants.ajoutRelationVivierImpossible()));
                }
                else {
                    if (filtreGroupements != null) {
                        if (personneRelationsFamillePopupPresenter == null) {
                            personneRelationsFamillePopupPresenter =
                                addChildPresenter(new PersonneRelationsPopupPresenter(eventBus, personneRpcService, personnePhysiqueRpcService,
                                    personneMoraleRpcService, dimensionRpcService, new PersonneRelationPopupViewImpl(constantes.isHasRoleAdmin()), constantes,
                                    idPersonne, nomPersonne, filtreGroupements, filtrePasDansGroupements, typePersonneSource, deskBar, aideService));

                            personneRelationsFamillePopupPresenter.addEventHandlerToLocalBus(SimpleValueChangeEvent.TYPE,
                                new SimpleValueChangeEventHandler<String>() {
                                    @Override
                                    public void onValueChange(SimpleValueChangeEvent<String> event) {
                                        switchModeEdition(modeCourant);
                                    }
                                });
                            personneRelationsFamillePopupPresenter.showPresenter(null);
                        }
                        else {
                            personneRelationsFamillePopupPresenter.afficherPopupAjoutRelation();
                        }
                    }
                    if (filtrePasDansGroupements != null) {
                        if (personneRelationsPopupPresenter == null) {
                            personneRelationsPopupPresenter =
                                addChildPresenter(new PersonneRelationsPopupPresenter(eventBus, personneRpcService, personnePhysiqueRpcService,
                                    personneMoraleRpcService, dimensionRpcService, new PersonneRelationPopupViewImpl(constantes.isHasRoleAdmin()), constantes,
                                    idPersonne, nomPersonne, filtreGroupements, filtrePasDansGroupements, typePersonneSource, deskBar, aideService));
                            personneRelationsPopupPresenter.addEventHandlerToLocalBus(SimpleValueChangeEvent.TYPE, new SimpleValueChangeEventHandler<String>() {
                                @Override
                                public void onValueChange(SimpleValueChangeEvent<String> event) {
                                    switchModeEdition(modeCourant);
                                }
                            });
                            personneRelationsPopupPresenter.showPresenter(null);
                        }
                        else {
                            personneRelationsPopupPresenter.afficherPopupAjoutRelation();
                        }
                    }
                }
            }
        });
        view.getBtEnregistrerRelationGenerale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (personneRelationsPresenter.isDatesValides()) {
                    personneRelationsPresenter.modifierRelations();
                }
            }
        });
        view.btChangementDeMode().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (modeCourant == AppControllerConstants.MODE_RELATION_EDITION) {
                    switchModeEdition(AppControllerConstants.MODE_RELATION_GRAPHIQUE);
                }
                else {
                    switchModeEdition(AppControllerConstants.MODE_RELATION_EDITION);
                }
            }
        });
        // Tentative de connexion a internet pour déterminer si la visualisation est disponible.
        view.afficherChangementMode(false);
        final RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "http://www.google.fr");
        rb.setHeader("Access-Control-Allow-Origin", "http://www.google.fr");
        final int timeout = 15000;
        rb.setTimeoutMillis(timeout);
        rb.setCallback(new RequestCallback() {

            @Override
            public void onResponseReceived(Request request, Response response) {
                if (response != null) {
                    final Runnable onLoadCallback = new Runnable() {
                        public void run() {
                            view.afficherChangementMode(true);
                        }
                    };
                    VisualizationUtils.loadVisualizationApi(onLoadCallback, OrgChart.PACKAGE);
                }
            }

            @Override
            public void onError(Request request, Throwable exception) {
            }
        });
        try {
            rb.send();
        }
        catch (RequestException e) {
            GWT.log("", e);
        }
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

    /**
     * Recuperer la valeur.
     * @return the modeCourant
     */
    public int getModeCourant() {
        return modeCourant;
    }

    @Override
    public void onShow(HasWidgets container) {
        switchModeEdition(modeCourant);
        container.add(view.asWidget());
    }

    /**
     * Passer d'un mode à un autre.
     * @param mode le mode voulus.
     */
    public void switchModeEdition(int mode) {
        modeCourant = mode;
        if (modeCourant == AppControllerConstants.MODE_RELATION_GRAPHIQUE) {
            view.passerEnModeGraphique();
            if (personneRelationsVisualizationPresenter == null) {
                personneRelationsVisualizationPresenter =
                    addChildPresenter(new PersonneRelationsVisualizationPresenter(eventBus, new PersonneRelationsVisualizationViewImpl(), personneRpcService,
                        filtreGroupements, idPersonne, filtrePasDansGroupements));
                personneRelationsVisualizationPresenter.showPresenter(view.getConteneurModeRelation());
            }
            else {
                // UN CONTENEUR POUR DEUX VUES ON SWITCH
                view.getConteneurModeRelation().clear();
                personneRelationsVisualizationPresenter.onShow(view.getConteneurModeRelation());
            }
        }
        else {
            view.passerEnModeEdition();
            if (personneRelationsPresenter == null) {
                personneRelationsPresenter =
                    addChildPresenter(new PersonneRelationsPresenter(eventBus, personneRpcService, dimensionRpcService, new PersonneRelationsViewImpl(),
                        constantes, filtreGroupements, idPersonne, filtrePasDansGroupements, aideService));
                personneRelationsPresenter.showPresenter(view.getConteneurModeRelation());
                personneRelationsPresenter.addEventHandlerToLocalBus(UpdateTabNameEvent.TYPE, new UpdateTabNameEventHandler() {
                    @Override
                    public void updateTabName(UpdateTabNameEvent event) {
                        // Propagation
                        fireEventLocalBus(event);
                    }
                });
            }
            else {
                // UN CONTENEUR POUR DEUX VUES ON SWITCH
                view.getConteneurModeRelation().clear();
                personneRelationsPresenter.onShow(view.getConteneurModeRelation());
            }
        }
    }

    /**
     * La vue associé aux gestionnaires de modes.
     * @author Goumard Stephane (scub) - SCUB
     */
    public interface PersonneRelationsModeView extends View {
        /**
         * Renvoi la valeur de enregistrerRelation.
         * @return enregistrerRelation
         */
        HasClickHandlers getBtEnregistrerRelationGenerale();

        /**
         * Renvoi la valeur de ajouterRelation.
         * @return ajouterRelation
         */
        HasClickHandlers getBtAjouterRelationGenerale();

        /**
         * Le Bouton de changement de mode.
         * @return le handler sur le click du bouton.
         */
        HasClickHandlers btChangementDeMode();

        /**
         * Conteneur des modes de l'application.
         * @return le widget sur le click du bouton.
         */
        HasWidgets getConteneurModeRelation();

        /**
         * Passage en mode graphique.
         */
        void passerEnModeGraphique();

        /**
         * Passage en mode graphique.
         */
        void passerEnModeEdition();

        /**
         * Affiche/Masque le changement de mode.
         * @param afficher flag
         */
        void afficherChangementMode(boolean afficher);

        /**
         * Affiche une popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void afficherPopupErreur(ErrorPopupConfiguration errorPopupConfiguration);
    }
}
