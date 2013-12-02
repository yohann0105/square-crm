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
package com.square.composant.prestations.square.client.presenter.bloc.entete;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingSortGwt;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.square.composant.prestations.square.client.model.ConstantesPrestationModel;
import com.square.composant.prestations.square.client.model.EntetePrestationModel;
import com.square.composant.prestations.square.client.model.LigneDecompteModel;
import com.square.composant.prestations.square.client.service.PrestationServiceGwtAsync;
import com.square.composant.prestations.square.client.view.bloc.entete.BlocEntetePrestationViewImplConstants;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;
import com.square.composants.graphiques.lib.client.event.HasAllBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEventHandler;

/**
 * Presenter pour un bloc entete.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class BlocEntetePrestationPresenter extends Presenter {

    /** Vue associé au presenter. */
    private BlocEntetePrestationView view;

    /** Service asynchrone. */
    private PrestationServiceGwtAsync prestationRpcService;

    /** Constantes. */
    private ConstantesPrestationModel constantes;

    private EntetePrestationModel entetePrestation;

    private Long uidPersonne;

    private NumberFormat numberFormat = NumberFormat.getFormat("#,##0.00");

    private boolean alreadyLoaded = false;

    private int triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;

    private String colonneTri;

    /** Constantes du presenter. */
    private BlocEntetePrestationPresenterConstants presenterConstants;

    /**
     * Presenter Prestations.
     * @param eventBus eventBus
     * @param view view
     * @param uidPersonne l'uid de la personne
     * @param entetePrestation entetePrestation
     * @param prestationRpcService prestationRpcService
     * @param constantes les constantes
     */
    public BlocEntetePrestationPresenter(HandlerManager eventBus, BlocEntetePrestationView view, Long uidPersonne, EntetePrestationModel entetePrestation,
        PrestationServiceGwtAsync prestationRpcService, ConstantesPrestationModel constantes) {
        super(eventBus);
        this.presenterConstants = GWT.create(BlocEntetePrestationPresenterConstants.class);
        this.view = view;
        this.prestationRpcService = prestationRpcService;
        this.entetePrestation = entetePrestation;
        this.uidPersonne = uidPersonne;
        this.constantes = constantes;
        colonneTri = constantes.getOrderDecompteByDateSoin();
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        // on charge d'abord l'entete
        final List<ChampSynthese> listeChamps = new ArrayList<ChampSynthese>();
        listeChamps.add(new ChampSynthese(new Label(entetePrestation.getNumero()), view.getViewConstants().reference(), true));
        listeChamps.add(new ChampSynthese(new Label(entetePrestation.getDateReglement()), view.getViewConstants().datePaiement(), true));
        listeChamps.add(new ChampSynthese(new Label(entetePrestation.getOrigine().getLibelle()), view.getViewConstants().origine(), true));
        final String libellePaye =
            constantes.getIdOrigineDecompteIndu().equals(entetePrestation.getOrigine().getIdentifiant()) ? view.getViewConstants().montantRembourse() : view
                .getViewConstants().montantPaye();
        listeChamps.add(new ChampSynthese(new Label(numberFormat.format(entetePrestation.getRemboursementSmatis())), libellePaye, true));
        final String libelleStatutReglement = constantes.getIdStatutPaiementDecomptePaye().equals(
        		entetePrestation.getStatutPaiement()) ? presenterConstants.statutSolde() : presenterConstants.statutEnCour();
        listeChamps.add(new ChampSynthese(new Label(libelleStatutReglement), view.getViewConstants().statutPaiement(), true));
        view.chargerEntete(listeChamps);

        view.getBlocSyntheseHandler().addOpenEventHandler(new OpenBlocSyntheseEventHandler() {
            @Override
            public void onOpen(OpenBlocSyntheseEvent event) {
                if (!alreadyLoaded) {
                    prestationRpcService.recupererLignesPrestationsByNumeroDecompte(uidPersonne, entetePrestation.getNumero(), constantes
                            .getOrderDecompteByDateSoin(), RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC, new AsyncCallback<List<LigneDecompteModel>>() {
                        @Override
                        public void onSuccess(List<LigneDecompteModel> result) {
                            chargerInfos(result);
                            view.onRpcServiceSuccess();
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                        }
                    });
                    alreadyLoaded = true;
                }
            }
        });
        view.getLDateSoin().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (constantes.getOrderDecompteByDateSoin().equals(colonneTri)) {
                    if (triDefaut == RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC) {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                    } else {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    }
                } else {
                    triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    colonneTri = constantes.getOrderDecompteByDateSoin();
                }
                view.setIsTriColonneRbtProf(false);
                actualiserLignesPresta();
            }
        });
        view.getLBeneficiaire().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (constantes.getOrderDecompteByBeneficiaire().equals(colonneTri)) {
                    if (triDefaut == RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC) {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                    } else {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    }
                } else {
                    triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    colonneTri = constantes.getOrderDecompteByBeneficiaire();
                }
                view.setIsTriColonneRbtProf(false);
                actualiserLignesPresta();
            }
        });
        view.getLActe().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (constantes.getOrderDecompteByActe().equals(colonneTri)) {
                    if (triDefaut == RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC) {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                    } else {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    }
                } else {
                    triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    colonneTri = constantes.getOrderDecompteByActe();
                }
                view.setIsTriColonneRbtProf(false);
                actualiserLignesPresta();
            }
        });
        view.getLDepenseEngagee().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (constantes.getOrderDecompteByDepense().equals(colonneTri)) {
                    if (triDefaut == RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC) {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                    } else {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    }
                } else {
                    triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    colonneTri = constantes.getOrderDecompteByDepense();
                }
                view.setIsTriColonneRbtProf(false);
                actualiserLignesPresta();
            }
        });
        view.getLBase().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (constantes.getOrderDecompteByBase().equals(colonneTri)) {
                    if (triDefaut == RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC) {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                    } else {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    }
                } else {
                    triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    colonneTri = constantes.getOrderDecompteByBase();
                }
                view.setIsTriColonneRbtProf(false);
                actualiserLignesPresta();
            }
        });
        view.getLTaux().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (constantes.getOrderDecompteByTaux().equals(colonneTri)) {
                    if (triDefaut == RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC) {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                    } else {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    }
                } else {
                    triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    colonneTri = constantes.getOrderDecompteByTaux();
                }
                view.setIsTriColonneRbtProf(false);
                actualiserLignesPresta();
            }
        });
        view.getLRbtRo().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (constantes.getOrderDecompteByRbtRO().equals(colonneTri)) {
                    if (triDefaut == RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC) {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                    } else {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    }
                } else {
                    triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    colonneTri = constantes.getOrderDecompteByRbtRO();
                }
                view.setIsTriColonneRbtProf(false);
                actualiserLignesPresta();
            }
        });
        view.getLRbtSmatisAdherent().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (constantes.getOrderDecompteByRbtSmatis().equals(colonneTri)) {
                    if (triDefaut == RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC) {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                    } else {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    }
                } else {
                    triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    colonneTri = constantes.getOrderDecompteByRbtSmatis();
                }
                view.setIsTriColonneRbtProf(false);
                actualiserLignesPresta();
            }
        });
        view.getLRbtSmatisProf().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (constantes.getOrderDecompteByRbtProf().equals(colonneTri)) {
                    if (triDefaut == RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC) {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                    } else {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    }
                } else {
                    triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    colonneTri = constantes.getOrderDecompteByRbtProf();
                }
                view.setIsTriColonneRbtProf(true);
                actualiserLignesPresta();
            }
        });
        view.getLRAC().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (constantes.getOrderDecompteByRaC().equals(colonneTri)) {
                    if (triDefaut == RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC) {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                    } else {
                        triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    }
                } else {
                    triDefaut = RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                    colonneTri = constantes.getOrderDecompteByRaC();
                }
                view.setIsTriColonneRbtProf(false);
                actualiserLignesPresta();
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
    }

    private void chargerInfos(List<LigneDecompteModel> lignes) {
        view.getDateDebutSoins().setText(entetePrestation.getDateDebutSoins());
        view.getDateFinSoins().setText(entetePrestation.getDateFinSoins());
        view.getDestinataireReglement().setText(entetePrestation.getNomDestinataireReglement());
        if (entetePrestation.getCompte() != null && !entetePrestation.getCompte().equals("")) {
            view.afficherCompte();
            view.getCompte().setText(entetePrestation.getDomiciliation() + " " + entetePrestation.getCompte());
        }
        if (entetePrestation.getNumeroCheque() != null && !entetePrestation.getNumeroCheque().equals("")) {
            view.afficherNumeroCheque();
            view.getNumeroCheque().setText(entetePrestation.getNumeroCheque());
        }
        view.chargerLignes(lignes);
    }

    /** Actualise les lignes de presta. */
    private void actualiserLignesPresta() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(presenterConstants.rechercheEnCours()));
        prestationRpcService.recupererLignesPrestationsByNumeroDecompte(uidPersonne, entetePrestation.getNumero(), colonneTri, triDefaut,
            new AsyncCallback<List<LigneDecompteModel>>() {
                @Override
                public void onSuccess(List<LigneDecompteModel> result) {
                    view.actualiserLignes(result, colonneTri, triDefaut);
                    view.onRpcServiceSuccess();
                }

                @Override
                public void onFailure(Throwable caught) {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            });
    }

    /**
     * Interface pour la vue MoteurRecherchePrestationsView.
     */
    public interface BlocEntetePrestationView extends View {
        /**
         * Charge l'entete du bloc entete.
         * @param listeChamps la liste des champs
         */
        void chargerEntete(List<ChampSynthese> listeChamps);

        /**
         * Recupere les handlers du bloc synthese.
         * @return les handlers
         */
        HasAllBlocSyntheseEventHandlers getBlocSyntheseHandler();

        /**
         * Recupere les constantes.
         * @return les constantes
         */
        BlocEntetePrestationViewImplConstants getViewConstants();

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
         * Le widget.
         * @return le widget
         */
        HasText getCompte();

        /**
         * Le widget.
         * @return le widget
         */
        HasText getNumeroCheque();

        /**
         * Le widget.
         * @return le widget
         */
        HasText getDateDebutSoins();

        /**
         * Le widget.
         * @return le widget
         */
        HasText getDateFinSoins();

        /**
         * Le widget.
         * @return le widget
         */
        HasText getDestinataireReglement();

        /**
         * Affiche le numero de compte.
         */
        void afficherCompte();

        /**
         * Affiche le numero de cheque.
         */
        void afficherNumeroCheque();

        /**
         * Charge les lignes.
         * @param lignes les lignes
         */
        void chargerLignes(List<LigneDecompteModel> lignes);

        /**
         * Le widget.
         * @return le widget
         */
        HasClickHandlers getLDateSoin();

        /**
         * Le widget.
         * @return le widget
         */
        HasClickHandlers getLBeneficiaire();

        /**
         * Le widget.
         * @return le widget
         */
        HasClickHandlers getLActe();

        /**
         * Le widget.
         * @return le widget
         */
        HasClickHandlers getLDepenseEngagee();

        /**
         * Le widget.
         * @return le widget
         */
        HasClickHandlers getLBase();

        /**
         * Le widget.
         * @return le widget
         */
        HasClickHandlers getLTaux();

        /**
         * Le widget.
         * @return le widget
         */
        HasClickHandlers getLRbtRo();

        /**
         * Le widget.
         * @return le widget
         */
        HasClickHandlers getLRbtSmatisAdherent();

        /**
         * Le widget.
         * @return le widget
         */
        HasClickHandlers getLRbtSmatisProf();

        /**
         * Le widget.
         * @return le widget
         */
        HasClickHandlers getLRAC();

        /**
         * Actualise les lignes.
         * @param listeLignes la liste des lignes.
         * @param colonneTri la colonne de tri.
         * @param triDefaut le tri.
         */
        void actualiserLignes(List<LigneDecompteModel> listeLignes, String colonneTri, int triDefaut);

        /**
         * Indique si le tri porte sur la colonne "Rbt Prof".
         * @param value la valeur.
         */
        void setIsTriColonneRbtProf(boolean value);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
