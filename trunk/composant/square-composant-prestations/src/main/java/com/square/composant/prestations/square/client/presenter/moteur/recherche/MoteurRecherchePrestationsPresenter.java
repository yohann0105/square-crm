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
package com.square.composant.prestations.square.client.presenter.moteur.recherche;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.prestations.square.client.model.ConstantesPrestationModel;
import com.square.composant.prestations.square.client.model.EntetePrestationModel;
import com.square.composant.prestations.square.client.model.IdentifiantEidLibelleGwt;
import com.square.composant.prestations.square.client.model.PrestationCriteresRechercheModel;
import com.square.composant.prestations.square.client.presenter.bloc.entete.BlocEntetePrestationPresenter;
import com.square.composant.prestations.square.client.presenter.bloc.entete.BlocEntetePrestationPresenter.BlocEntetePrestationView;
import com.square.composant.prestations.square.client.service.PrestationServiceGwtAsync;
import com.square.composant.prestations.square.client.view.bloc.entete.BlocEntetePrestationViewImpl;
import com.square.composant.prestations.square.client.view.moteur.recherche.MoteurRecherchePrestationsViewImplConstants;

/**
 * Presenter pour le moteur de recherche de prestations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class MoteurRecherchePrestationsPresenter extends Presenter {

    /** Vue associé au presenter. */
    private MoteurRecherchePrestationsView view;

    private Long uidPersonne;

    /** Service asynchrone. */
    private PrestationServiceGwtAsync prestationRpcService;

    private int currentPage;

    /** Constantes. */
    private ConstantesPrestationModel constantes;

    /**
     * Presenter Prestations.
     * @param eventBus eventBus
     * @param view view
     * @param uidPersonne uidPersonne
     * @param prestationRpcService prestationRpcService
     * @param constantes les constantes
     */
    public MoteurRecherchePrestationsPresenter(HandlerManager eventBus, MoteurRecherchePrestationsView view, Long uidPersonne,
        PrestationServiceGwtAsync prestationRpcService, ConstantesPrestationModel constantes) {
        super(eventBus);
        this.uidPersonne = uidPersonne;
        this.view = view;
        this.prestationRpcService = prestationRpcService;
        this.constantes = constantes;
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        view.getBtnRechercher().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                rechercherPrestations(1);
            }
        });
        view.getBtnInitRecherche().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.effacerRecherche();
            }
        });
        view.getGestionToucheEntreHandler().addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    rechercherPrestations(1);
                }
            }
        });
        view.getSlbBeneficairesHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                prestationRpcService.recupererBenefPrestations(uidPersonne, event.getSuggest(), event.getCallBack());
            }
        });
        view.getSlbOriginesHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                prestationRpcService.rechercherOriginesDecompteParCriteres(event.getSuggest(), event.getCallBack());
            }
        });
        view.getSlbActesHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantEidLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantEidLibelleGwt> event) {
                prestationRpcService.rechercherActesDecompteParCriteres(event.getSuggest(), event.getCallBack());
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
    }

    /**
     * Recherche une ressource à partir des criteres.
     * @param le numero de la page à charger
     */
    private void rechercherPrestations(int page) {
        currentPage = page;
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().rechercherEnCours()));

        final RemotePagingCriteriasGwt<PrestationCriteresRechercheModel> criteresPagines = new RemotePagingCriteriasGwt<PrestationCriteresRechercheModel>();
        criteresPagines.setCriterias(recupererCriteres());
        criteresPagines.setFirstResult(MoteurRecherchePrestationsViewImplConstants.NB_ELEMENT_PAR_PAGE * (page - 1));
        criteresPagines.setMaxResult(MoteurRecherchePrestationsViewImplConstants.NB_ELEMENT_PAR_PAGE);

        prestationRpcService.rechercherEntetesPrestationParCritreres(criteresPagines, new AsyncCallback<RemotePagingResultsGwt<EntetePrestationModel>>() {
            @Override
            public void onSuccess(RemotePagingResultsGwt<EntetePrestationModel> result) {
                chargerPagination(result.getTotalResults());
                afficherEntetes(result.getListResults());
                view.onRpcServiceSuccess();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    private PrestationCriteresRechercheModel recupererCriteres() {
        final PrestationCriteresRechercheModel criteres = new PrestationCriteresRechercheModel();
        criteres.setIdAssure(uidPersonne);
        final Date dateDebutSoins = view.getCdbDateDebutSoins().getValue();
        if (dateDebutSoins != null) {
            criteres.setDateDebutSoins(DateUtil.getString(dateDebutSoins));
        }
        final Date dateFinSoins = view.getCdbDateFinSoins().getValue();
        if (dateFinSoins != null) {
            criteres.setDateFinSoins(DateUtil.getString(dateFinSoins));
        }
        final IdentifiantLibelleGwt benef = view.getSlbBeneficaires().getValue();
        if (benef != null) {
            criteres.setIdBeneficiaire(benef.getIdentifiant());
        }
        final IdentifiantLibelleGwt origine = view.getSlbOrigines().getValue();
        if (origine != null) {
            criteres.setIdOrigine(origine.getIdentifiant());
        }
        final List<IdentifiantEidLibelleGwt> actes = view.getSlbActes().getValue();
        if (actes != null) {
            final List<Long> idsActes = new ArrayList<Long>();
            for (IdentifiantLibelleGwt acte : actes) {
                idsActes.add(acte.getIdentifiant());
            }
            criteres.setIdActes(idsActes);
        }
        return criteres;
    }

    /**
     * Affiche les entetes.
     * @param entetes les entetes
     */
    private void afficherEntetes(List<EntetePrestationModel> entetes) {
        view.viderConteneurEntetes();
        for (EntetePrestationModel entetePrestationModel : entetes) {
            final BlocEntetePrestationView enteteView = new BlocEntetePrestationViewImpl(constantes);
            new BlocEntetePrestationPresenter(eventBus, enteteView, uidPersonne, entetePrestationModel, prestationRpcService, constantes).showPresenter(view
                    .ajouterConteneurEntete());
        }
    }

    /**
     * Charge la pagination.
     * @param totalResults le nb total de résultats
     */
    private void chargerPagination(int totalResults) {
        view.viderPagination();
        final int nbElementsPage = MoteurRecherchePrestationsViewImplConstants.NB_ELEMENT_PAR_PAGE;
        final double nbPages = Math.ceil((double) totalResults / nbElementsPage);
        for (int i = 1; i <= nbPages; i++) {
            final boolean selected = i == currentPage;
            final HasClickHandlers clickHandler = view.ajouterLienPage(i, selected);
            if (!selected) {
                final int numPage = i;
                clickHandler.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        rechercherPrestations(numPage);
                    }
                });
            }
        }
    }

    /**
     * Interface pour la vue MoteurRecherchePrestationsView.
     */
    public interface MoteurRecherchePrestationsView extends View {
        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<IdentifiantLibelleGwt> getSlbBeneficaires();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<Date> getCdbDateDebutSoins();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<Date> getCdbDateFinSoins();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<IdentifiantLibelleGwt> getSlbOrigines();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<List<IdentifiantEidLibelleGwt>> getSlbActes();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbBeneficairesHandler();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbOriginesHandler();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantEidLibelleGwt> getSlbActesHandler();

        /**
         * Methode appelé lorsque un service Rpc s'est deroulé correctement.
         */
        void onRpcServiceSuccess();

        /**
         * Methode appelé lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Affiche un message de chargement.
         * @param config la config
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnRechercher();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnInitRecherche();

        /**
         * Initialiser le moteur de recherche.
         */
        void effacerRecherche();

        /**
         * Gerer la touche entré.
         * @return le handler sur la gestion de la touche entré.
         */
        HasKeyPressHandlers getGestionToucheEntreHandler();

        /**
         * Recupere les constantes.
         * @return les constantes
         */
        MoteurRecherchePrestationsViewImplConstants getViewConstants();

        /**
         * Retourne le conteneur d'entetes.
         * @return le conteneur
         */
        HasWidgets ajouterConteneurEntete();

        /**
         * Vide le conteneur d'entete.
         */
        void viderConteneurEntetes();

        /**
         * Vide le conteneur de pagination.
         */
        void viderPagination();

        /**
         * Ajoute un lien vers une page.
         * @param numPage le numero de la page
         * @param selected page courante
         * @return le click handler du lien
         */
        HasClickHandlers ajouterLienPage(int numPage, boolean selected);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
