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
package com.square.composant.cotisations.client.presenter.moteur.recherche;

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
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.cotisations.client.content.i18n.ComposantCotisationsConstants;
import com.square.composant.cotisations.client.model.CodeAiaLibelleModel;
import com.square.composant.cotisations.client.model.CotisationModel;
import com.square.composant.cotisations.client.model.CotisationsCriteresRechercheModel;
import com.square.composant.cotisations.client.model.RetourCotisationModel;
import com.square.composant.cotisations.client.presenter.bloc.entete.BlocEnteteCotisationPresenter;
import com.square.composant.cotisations.client.presenter.bloc.entete.BlocEnteteCotisationPresenter.BlocEnteteCotisationView;
import com.square.composant.cotisations.client.service.CotisationsServiceGwtAsync;
import com.square.composant.cotisations.client.view.bloc.entete.BlocEnteteCotisationViewImpl;
import com.square.composant.cotisations.client.view.moteur.recherche.MoteurRechercheCotisationsViewImplConstants;

/**
 * Presenter pour le moteur de recherche de cotisations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class MoteurRechercheCotisationsPresenter extends Presenter {

    /** Vue associé au presenter. */
    private MoteurRechercheCotisationsView view;

    private Long uidPersonne;

    private int currentPage;

    /** Service asynchrone. */
    private CotisationsServiceGwtAsync cotisationsRpcService;

    private boolean currrentIsSimulation;

    /**
     * Presenter Cotisations.
     * @param eventBus eventBus
     * @param view view
     * @param uidPersonne uidPersonne
     * @param cotisationsRpcService cotisationsRpcService
     */
    public MoteurRechercheCotisationsPresenter(HandlerManager eventBus, MoteurRechercheCotisationsView view, Long uidPersonne,
        CotisationsServiceGwtAsync cotisationsRpcService) {
        super(eventBus);
        this.uidPersonne = uidPersonne;
        this.view = view;
        this.cotisationsRpcService = cotisationsRpcService;
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
                rechercherCotisations(1, false);
            }
        });
        view.getBtnSimuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                rechercherCotisations(1, true);
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
                    rechercherCotisations(1, false);
                }
            }
        });
        view.getSlbContratsHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                cotisationsRpcService.recupererContrats(uidPersonne, event.getSuggest(), event.getCallBack());
            }
        });
        view.getSlbModesPaiementHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                cotisationsRpcService.rechercherModesPaiementParCriteres(event.getSuggest(), event.getCallBack());
            }
        });
        view.getSlbSituationsHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<CodeAiaLibelleModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<CodeAiaLibelleModel> event) {
                cotisationsRpcService.rechercherSituationsParCriteres(event.getSuggest(), event.getCallBack());
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
    }

    /**
     * Recherche des cotisations.
     * @param le numero de la page à charger
     * @param isSimulation flag simulation ou pas
     */
    private void rechercherCotisations(int page, boolean isSimulation) {
        currentPage = page;
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().rechercherEnCours()));

        final RemotePagingCriteriasGwt<CotisationsCriteresRechercheModel> criteresPagines = new RemotePagingCriteriasGwt<CotisationsCriteresRechercheModel>();
        criteresPagines.setCriterias(recupererCriteres(isSimulation));
        criteresPagines.setFirstResult(MoteurRechercheCotisationsViewImplConstants.NB_ELEMENT_PAR_PAGE * (page - 1));
        criteresPagines.setMaxResult(MoteurRechercheCotisationsViewImplConstants.NB_ELEMENT_PAR_PAGE);

        cotisationsRpcService.recupererCotisations(criteresPagines, new AsyncCallback<RetourCotisationModel>() {
            @Override
            public void onSuccess(RetourCotisationModel result) {
                final RemotePagingResultsGwt<CotisationModel> listeCotisations = result.getResultatsCotisation();
                chargerPagination(listeCotisations.getTotalResults());
                afficherEntetes(listeCotisations.getListResults());
                if (result.getSolde() != null) {
                    view.getLValueSoldeGlobalActuel().setText(ComposantCotisationsConstants.NUMBERFORMAT.format(result.getSolde()));
                }
                view.onRpcServiceSuccess();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    private CotisationsCriteresRechercheModel recupererCriteres(boolean isSimulation) {
        currrentIsSimulation = isSimulation;

        final CotisationsCriteresRechercheModel criteres = new CotisationsCriteresRechercheModel();
        criteres.setUidPersonne(uidPersonne);
        criteres.setSimulation(isSimulation);
        final Date dateDebut = view.getCdbDateDebutPeriode().getValue();
        if (dateDebut != null) {
            criteres.setDateDebut(DateUtil.getString(dateDebut));
        }
        final Date dateFin = view.getCdbDateFinPeriode().getValue();
        if (dateFin != null) {
            criteres.setDateFin(DateUtil.getString(dateFin));
        }
        final IdentifiantLibelleGwt contrat = view.getSlbContrats().getValue();
        if (contrat != null) {
            criteres.setContrat(contrat.getLibelle());
        }
        final IdentifiantLibelleGwt modePaiement = view.getSlbModesPaiement().getValue();
        if (modePaiement != null) {
            criteres.setIdModePaiement(modePaiement.getIdentifiant());
        }
        final CodeAiaLibelleModel situation = view.getSlbSituations().getValue();
        if (situation != null) {
            criteres.setSituation(situation);
        }
        final String montantMax = view.getTbMontantMax().getValue();
        if (montantMax != null && !"".equals(montantMax)) {
            criteres.setMontantMax(Float.valueOf(montantMax));
        }
        final String montantMin = view.getTbMontantMin().getValue();
        if (montantMin != null && !"".equals(montantMin)) {
            criteres.setMontantMin(Float.valueOf(montantMin));
        }
        return criteres;
    }

    /**
     * Affiche les entetes.
     * @param entetes les entetes
     */
    private void afficherEntetes(List<CotisationModel> entetes) {
        view.viderConteneurEntetes();
        view.afficherModeSimulation(currrentIsSimulation);
        view.afficherConteneurEntete(entetes.size() > 0);
        // Nettoyage des présenters existants
        for (Presenter presenter : listePresenter) {
            presenter.detachPresenter();
        }
        listePresenter.clear();
        for (CotisationModel enteteCotisationModel : entetes) {
            final BlocEnteteCotisationView enteteView = new BlocEnteteCotisationViewImpl();
            addChildPresenter(new BlocEnteteCotisationPresenter(eventBus, enteteView, enteteCotisationModel))
                .showPresenter(view.ajouterConteneurEntete());
        }
    }

    /**
     * Charge la pagination.
     * @param totalResults le nb total de résultats
     */
    private void chargerPagination(int totalResults) {
        view.viderPagination();
        final int nbElementsPage = MoteurRechercheCotisationsViewImplConstants.NB_ELEMENT_PAR_PAGE;
        final double nbPages = Math.ceil((double) totalResults / nbElementsPage);
        for (int i = 1; i <= nbPages; i++) {
            final boolean selected = i == currentPage;
            final HasClickHandlers clickHandler = view.ajouterLienPage(i, selected);
            if (!selected) {
                final int numPage = i;
                clickHandler.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        rechercherCotisations(numPage, currrentIsSimulation);
                    }
                });
            }
        }
    }

    /**
     * Interface pour la vue MoteurRechercheCotisationsView.
     */
    public interface MoteurRechercheCotisationsView extends View {
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
         * Retourne le handler.
         * @return le handler
         */
        HasValue<IdentifiantLibelleGwt> getSlbContrats();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<IdentifiantLibelleGwt> getSlbModesPaiement();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<CodeAiaLibelleModel> getSlbSituations();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<Date> getCdbDateDebutPeriode();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<Date> getCdbDateFinPeriode();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<String> getTbMontantMin();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasValue<String> getTbMontantMax();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasText getLValueSoldeGlobalActuel();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbContratsHandler();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbModesPaiementHandler();

        /**
         * Retourne le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<CodeAiaLibelleModel> getSlbSituationsHandler();

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
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnSimuler();

        /**
         * Gerer la touche entré.
         * @return le handler sur la gestion de la touche entré.
         */
        HasKeyPressHandlers getGestionToucheEntreHandler();

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

        /**
         * Initialiser le moteur de recherche.
         */
        void effacerRecherche();

        /**
         * Affiche le mode simulation.
         * @param isSimulation flag simulation
         */
        void afficherModeSimulation(boolean isSimulation);

        /**
         * Affiche le conteneur de resultat.
         * @param afficher flag afficher
         */
        void afficherConteneurEntete(boolean afficher);

        /**
         * Recupere les constantes.
         * @return les constantes
         */
        MoteurRechercheCotisationsViewImplConstants getViewConstants();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
