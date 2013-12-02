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
package com.square.composant.tarificateur.square.client.presenter.devis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.event.AffichagePopupChoixProduitEvent;
import com.square.composant.tarificateur.square.client.event.AffichagePopupEnvoiDevisParMailEvent;
import com.square.composant.tarificateur.square.client.event.AffichagePopupImpressionDevisEvent;
import com.square.composant.tarificateur.square.client.event.AffichagePopupSelectionProduitEvent;
import com.square.composant.tarificateur.square.client.event.AffichagePopupSelectionProduitEventHandler;
import com.square.composant.tarificateur.square.client.event.RechargementOpportuniteEvent;
import com.square.composant.tarificateur.square.client.event.SuccesMajInfosAdhesionEvent;
import com.square.composant.tarificateur.square.client.event.SuccesMajInfosAdhesionEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesTransfertEvent;
import com.square.composant.tarificateur.square.client.exception.ControleIntegriteExceptionGwt;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.opportunite.InfosOpportuniteModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModificationModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.EnregistrementFinaliteDevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.EnregistrementFinaliteLigneDevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.TransfertDevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.ligne.LigneDevisModel;
import com.square.composant.tarificateur.square.client.presenter.adhesion.InfosAdhesionPresenter;
import com.square.composant.tarificateur.square.client.presenter.adhesion.InfosAdhesionPresenter.InfosAdhesionView;
import com.square.composant.tarificateur.square.client.presenter.ligne.devis.LigneDevisPresenter;
import com.square.composant.tarificateur.square.client.presenter.ligne.devis.LigneDevisPresenter.LigneDevisView;
import com.square.composant.tarificateur.square.client.service.DimensionServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurPersonneServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurTransformationAiaServiceGwtAsync;
import com.square.composant.tarificateur.square.client.view.adhesion.InfosAdhesionViewImpl;
import com.square.composant.tarificateur.square.client.view.popup.PopupTitle;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Presenter des devis.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class DevisPresenter extends Presenter {

    /** La vue. */
    private DevisView view;

    private DevisModel devis;

    private ConstantesModel constantesApp;

    private int compteurLignesSelPourTransfert;

    private int compteurLignePrincSelectionne;

    private TarificateurServiceGwtAsync tarificateurService;

    private TarificateurTransformationAiaServiceGwtAsync tarificateurTransformationAiaRpcService;

    private TarificateurPersonneServiceGwtAsync tarificateurPersonneRpcService;

    private DimensionServiceGwtAsync dimensionRpcService;

    private DeskBar deskBar;

    private Map<Long, LigneDevisView> mapLigneDevisView;

    private InfosOpportuniteModel infosOpportunite;

    private DevisPresenterConstants presenterConstants;

    private PopupTitle popupWarningFamilleDifferente;

    private Map<Long, LigneDevisPresenter> mapLignesDevisPresenters;

    private InfosAdhesionPresenter infosAdhesionPresenter;

    /**
     * Constructeur du présenter.
     * @param eventBus le bus
     * @param constantesApp les constantes d'application
     * @param devis le devis a charger
     * @param devisView la vue
     * @param tarificateurService tarificateurService
     * @param tarificateurTransformationAiaRpcService tarificateurTransformationAiaRpcService
     * @param infosOpportunite infosOpportunite
     * @param tarificateurPersonneRpcService tarificateurPersonneRpcService
     * @param dimensionRpcService dimensionRpcService
     * @param deskBar deskBar
     */
    public DevisPresenter(HandlerManager eventBus, ConstantesModel constantesApp, DevisModel devis, DevisView devisView,
        TarificateurServiceGwtAsync tarificateurService, InfosOpportuniteModel infosOpportunite,
        TarificateurTransformationAiaServiceGwtAsync tarificateurTransformationAiaRpcService,
        TarificateurPersonneServiceGwtAsync tarificateurPersonneRpcService, DimensionServiceGwtAsync dimensionRpcService, DeskBar deskBar) {
        super(eventBus);
        this.view = devisView;
        this.devis = devis;
        this.constantesApp = constantesApp;
        this.tarificateurService = tarificateurService;
        this.infosOpportunite = infosOpportunite;
        this.mapLignesDevisPresenters = new HashMap<Long, LigneDevisPresenter>();
        this.tarificateurTransformationAiaRpcService = tarificateurTransformationAiaRpcService;
        this.tarificateurPersonneRpcService = tarificateurPersonneRpcService;
        this.dimensionRpcService = dimensionRpcService;
        this.deskBar = deskBar;

        presenterConstants = GWT.create(DevisPresenterConstants.class);
        popupWarningFamilleDifferente = new PopupTitle(presenterConstants.titleIconeWarningFamilleDifferente());
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        view.getBtnEnregistrerDevis().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                enregistrerFinaliteDevis();
            }
        });
        view.getBtnAjouterProduit().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // poste un evenement pour afficher la popup de choix produit
                fireEventLocalBus(new AffichagePopupChoixProduitEvent(devis.getId(), devis.getType()));
            }
        });
        view.getBtnEnvoyerDevisMail().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // Récupération des lignes de devis sélectionnées
                final List<Long> listeIdsLignesSelectionnees = getLigneSelectionnees();

                // Poste un évènement pour afficher la popup d'envoi de devis par mail
                fireEventLocalBus(new AffichagePopupEnvoiDevisParMailEvent(devis, infosOpportunite.getEidOpportunite(), listeIdsLignesSelectionnees));
            }
        });
        view.getBtnImprimerDevis().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Récupération des lignes de devis sélectionnées
                final List<Long> listeIdsLignesSelectionnees = getLigneSelectionnees();

                // Poste un évènement pour afficher la popup d'impression de devis
                final Long idMotif = view.getMotifDevis().getValue() != null ? view.getMotifDevis().getValue().getIdentifiant() : null;
                final DevisModificationModel devisModificationModel = new DevisModificationModel();
                devisModificationModel.setIdDevis(devis.getId());
                devisModificationModel.setIdMotifDevis(idMotif);
                fireEventLocalBus(new AffichagePopupImpressionDevisEvent(devis, devisModificationModel, infosOpportunite.getEidOpportunite(),
                    listeIdsLignesSelectionnees));
            }
        });
        view.getBtnTransfererNouveauDevis().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                transfererVersNouveauDevis();
            }
        });
        view.getBtnInformationsAdhesion().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                final boolean isInfoAdhesionEditable = constantesApp.getIdFinaliteNonRenseignee().equals(devis.getFinalite().getIdentifiant()) || constantesApp.getIdFinaliteEnCours().equals(devis.getFinalite().getIdentifiant());
                // final AffichagePopupAdhesionEvent affichagePopupAdhesionEvent =
                // new AffichagePopupAdhesionEvent(devis.getId(), devis.getPersonnePrincipale().getAdressePrincipale().getEidDepartement(), false,
                // !isDevisTransforme);
                // fireEventLocalBus(affichagePopupAdhesionEvent);
                // TODO PERFS JUAN
                afficherInformationsAdhesions(false, isInfoAdhesionEditable, null, true);

            }
        });
        view.getMotifDevisSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                Long idMotifDevis = null;
                if (devis != null && devis.getMotif() != null) {
                    idMotifDevis = devis.getMotif().getIdentifiant();
                }
                tarificateurService.getMotifsDevisByCriteres(event.getSuggest(), idMotifDevis, event.getCallBack());
            }
        });
        view.getIconeWarningFamilleDifferente().addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                popupWarningFamilleDifferente.show(view.getIconeWarningFamilleDifferente().getAbsoluteLeft() + 35, view.getIconeWarningFamilleDifferente()
                        .getAbsoluteTop() - 30);
            }
        });
        view.getIconeWarningFamilleDifferente().addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                popupWarningFamilleDifferente.hide();
            }
        });
    }

    /**
     * Affiche les infos d'adhesion.
     * @param lancerTransformationAiaEnsuite lancerTransformationAiaEnsuite
     * @param isInfoAdhesionEditable isInfoAdhesionEditable
     * @param rapportErreurs rapportErreurs
     * @param rechargerInfosAdhesion rechargerInfosAdhesion
     */
    public void afficherInformationsAdhesions(boolean lancerTransformationAiaEnsuite, boolean isInfoAdhesionEditable, RapportModel rapportErreurs,
        boolean rechargerInfosAdhesion) {
        if (infosAdhesionPresenter == null) {
            final InfosAdhesionView viewInfosAdhesion = new InfosAdhesionViewImpl(false, false, true);
            infosAdhesionPresenter =
                addChildPresenter(new InfosAdhesionPresenter(eventBus, viewInfosAdhesion, tarificateurService, tarificateurTransformationAiaRpcService,
                    constantesApp, dimensionRpcService, tarificateurPersonneRpcService, deskBar, devis.getId(), isInfoAdhesionEditable, devis
                            .getPersonnePrincipale().getAdressePrincipale().getEidDepartement(), lancerTransformationAiaEnsuite, rapportErreurs));
            infosAdhesionPresenter.addEventHandlerToLocalBus(SuccesMajInfosAdhesionEvent.TYPE, new SuccesMajInfosAdhesionEventHandler() {
                @Override
                public void onSuccess(SuccesMajInfosAdhesionEvent event) {
                    // On propage quoi qu'il arrive
                    fireEventLocalBus(event);
                }
            });
            infosAdhesionPresenter.showPresenter(null);
        } else {
            infosAdhesionPresenter.afficherAdhesion(lancerTransformationAiaEnsuite, isInfoAdhesionEditable, devis.getPersonnePrincipale()
                    .getAdressePrincipale().getEidDepartement(), rapportErreurs, rechargerInfosAdhesion);
        }
    }

    /** Ferme les infos d'adhesion. */
    public void fermerInformationsAdhesion() {
        if (infosAdhesionPresenter != null) {
            infosAdhesionPresenter.fermerAdhesion();
        }
    }

    @Override
    public void onShow(HasWidgets container) {
        construireDevis();
        container.add(view.asWidget());
    }

    /** Construit un devis. */
    public void construireDevis() {
        compteurLignesSelPourTransfert = 0;
        compteurLignePrincSelectionne = 0;
        view.activerBtnImprimerDevis(false);
        view.activerBtnEnvoiEmailDevis(false);

        final boolean isDevisTransforme = constantesApp.getIdFinaliteTransformee().equals(devis.getFinalite().getIdentifiant());
        // On active les boutons seulement si le devis n'a pas été transformé
        view.activerBtnEnregistrerDevis(!isDevisTransforme);
        // On active ou désactive le bouton de transformation et d'ajout de produit
        view.activerBtnTransformerDevisAia(devis.getFinalite().getIdentifiant().equals(constantesApp.getIdFinaliteAcceptee()));
        view.activerBtnAjouterProduit(devis.getListeLigneDevis() == null || devis.getListeLigneDevis().size() == 0);

        // on charge le motif de devis
        if (devis.getMotif() != null) {
            view.getMotifDevis().setValue(devis.getMotif());
        } else {
            view.getMotifDevis().setValue(constantesApp.getMotifDevisDefaut());
        }

        // on bloque la liste si lecture seule ou devis transformé
        view.activerLbMotifDevis(!devis.isLectureSeule() && !isDevisTransforme);

        view.afficherIconeAlerteFamilleDifferente(devis.isFamilleModifiee());

        // on affiche la finalité
        mettraAJourFinalite();

        mapLigneDevisView = new HashMap<Long, LigneDevisView>();

        nettoyerPresentersLignes();

        // on charge les lignes de devis principale
        for (LigneDevisModel ligneDevisPrincipale : devis.getListeLigneDevis()) {
            if (!mapLignesDevisPresenters.containsKey(ligneDevisPrincipale)) {
                final LigneDevisView ligneDevisView = view.creerLigneDevisView(constantesApp);
                mapLigneDevisView.put(ligneDevisPrincipale.getIdentifiant(), ligneDevisView);
                final LigneDevisPresenter ligneDevisPresenter =
                    new LigneDevisPresenter(eventBus, constantesApp, ligneDevisPrincipale, devis.isLectureSeule(), devis.getType(), devis
                            .getPersonnePrincipale().getListeBeneficiaires(), ligneDevisView);
                mapLignesDevisPresenters.put(ligneDevisPrincipale.getIdentifiant(), ligneDevisPresenter);
                ligneDevisPresenter.addEventHandlerToLocalBus(AffichagePopupSelectionProduitEvent.TYPE, new AffichagePopupSelectionProduitEventHandler() {
                    @Override
                    public void afficherPopup(AffichagePopupSelectionProduitEvent event) {
                        // Propagation
                        fireEventLocalBus(event);
                    }
                });
                ligneDevisPresenter.showPresenter(view.getConteneurLigneDevis());

                // on ajoute un handler sur la checkbox de selection pour transfert
                ligneDevisView.activerCbTransfertLigne(devis.getFinalite().getIdentifiant().equals(constantesApp.getIdFinaliteEnCours()));
                ligneDevisView.getCbTransfertLigne().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Boolean> event) {
                        if (event.getValue().booleanValue()) {
                            compteurLignesSelPourTransfert++;
                        } else {
                            compteurLignesSelPourTransfert--;
                        }
                        // Mise à jour de l'affichage du bouton de transfert
                        view.activerBtnTransfererNouveauDevis(compteurLignesSelPourTransfert > 0);
                    }
                });
                // on ajoute un handler sur les checkboxes de selection pour impression
                ajouterEvenementsLigne(ligneDevisView, ligneDevisPrincipale, null, false);
                // on incremente le compteur de ligne principale sélectionnée
                if (ligneDevisPrincipale.getSelectionnePourImpression() != null && ligneDevisPrincipale.getSelectionnePourImpression().booleanValue()) {
                    compteurLignePrincSelectionne++;
                    view.activerBtnImprimerDevis(true);
                    view.activerBtnEnvoiEmailDevis(true);
                }
                for (LigneDevisModel ligneLiee : ligneDevisPrincipale.getListeLignesDevisLiees()) {
                    ajouterEvenementsLigne(ligneDevisView, ligneLiee, ligneDevisPrincipale.getIdentifiant(), true);
                }
            }
        }
    }

    /**
     * Rafraichit un devis.
     * @param devisARafraichir devisARafraichir
     */
    public void rafraichir(DevisModel devisARafraichir) {
        this.devis = devisARafraichir;
        this.view.nettoyer();
        construireDevis();
    }

    private void nettoyerPresentersLignes() {
        for (LigneDevisPresenter ligneDevisP : mapLignesDevisPresenters.values()) {
            ligneDevisP.detachPresenter();
        }
        mapLignesDevisPresenters.clear();
    }

    /**
     * Affiche les evenements sur une ligne de devis.
     * @param ligneDevisView ligneDevisView
     * @param ligne ligne
     * @param idLignePrincipale idLignePrincipale
     * @param ligneLiee ligneLiee
     */
    private void ajouterEvenementsLigne(final LigneDevisView ligneDevisView, final LigneDevisModel ligne, final Long idLignePrincipale,
        final boolean ligneLiee) {
        // on récupère le critère optionnel ou pas du produit
        boolean isProduitOptionnel = true;
        if (ligneLiee && ligne.getProduitOptionnel() != null) {
            isProduitOptionnel = ligne.getProduitOptionnel().booleanValue();
        }

        final HasValue<Boolean> cbLigne = ligneDevisView.getMapCbImprimerLigne().get(ligne.getIdentifiant());
        if (ligneLiee && isProduitOptionnel) {
            cbLigne.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(ValueChangeEvent<Boolean> event) {
                    final HasValue<Boolean> cbPrincipale = ligneDevisView.getMapCbImprimerLigne().get(idLignePrincipale);
                    if (event.getValue() && !cbPrincipale.getValue() && compteurLignePrincSelectionne == 2) {
                        cbLigne.setValue(false);
                        ligneDevisView.afficherPopupErreurSelectionImpression();
                        view.activerBtnImprimerDevis(false);
                        view.activerBtnEnvoiEmailDevis(false);
                    } else if (event.getValue() && !cbPrincipale.getValue()) {
                        compteurLignePrincSelectionne++;
                        cbPrincipale.setValue(true);
                        view.activerBtnImprimerDevis(true);
                        view.activerBtnEnvoiEmailDevis(true);
                    }
                    repercuteModifsLignesLiees(ligneDevisView, ligne);
                }
            });
        } else if (!ligneLiee) {
            cbLigne.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(ValueChangeEvent<Boolean> event) {
                    if (event.getValue() && compteurLignePrincSelectionne == 2) {
                        cbLigne.setValue(false);
                        ligneDevisView.afficherPopupErreurSelectionImpression();
                    } else if (event.getValue()) {
                        compteurLignePrincSelectionne++;
                        view.activerBtnImprimerDevis(true);
                        view.activerBtnEnvoiEmailDevis(true);
                    } else if (!event.getValue()) {
                        compteurLignePrincSelectionne--;
                        if (compteurLignePrincSelectionne == 0) {
                            view.activerBtnImprimerDevis(false);
                            view.activerBtnEnvoiEmailDevis(false);
                        }
                    }
                    repercuteModifsLignesLiees(ligneDevisView, ligne);
                }
            });
        }
    }

    /**
     * Selectionne la finalité des lignes liees obligatoires.
     */
    private void repercuteModifsLignesLiees(final LigneDevisView ligneDevisView, final LigneDevisModel ligne) {
        final boolean cbImprimerLigne = ligneDevisView.getMapCbImprimerLigne().get(ligne.getIdentifiant()).getValue();
        for (LigneDevisModel ligneLiee : ligne.getListeLignesDevisLiees()) {
            ligneDevisView.getMapCbImprimerLigne().get(ligneLiee.getIdentifiant()).setValue(cbImprimerLigne);
        }
    }

    /**
     * Met à jour l'affichage des boutons.
     */
    private void mettraAJourFinalite() {
        // on affiche la finalite
        view.getLibelleFinaliteDevis().setText(devis.getFinalite().getLibelle());
        String styleFinalite = "";
        // on applique le style suivant le statut
        final Long idFinalite = devis.getFinalite().getIdentifiant() != null ? devis.getFinalite().getIdentifiant() : constantesApp.getIdFinaliteEnCours();
        if (idFinalite.equals(constantesApp.getIdFinaliteAcceptee())) {
            styleFinalite = ComposantTarificateur.RESOURCES.css().finaliteAcceptee();
        } else if (idFinalite.equals(constantesApp.getIdFinaliteRefusee())) {
            styleFinalite = ComposantTarificateur.RESOURCES.css().finaliteRefusee();
        } else if (idFinalite.equals(constantesApp.getIdFinaliteCorbeille())) {
            styleFinalite = ComposantTarificateur.RESOURCES.css().finaliteCorbeille();
        } else {
            styleFinalite = ComposantTarificateur.RESOURCES.css().finaliteNonRenseignee();
        }
        view.setStylePrimaryNameFinaliteDevis(styleFinalite);
    }

    /**
     * Sauvegarde les lignes de devis.
     */
    public void enregistrerFinaliteDevis() {
        view.afficherPopupLoadingEnregistrementDevis();
        // Appel du service
        tarificateurService.enregistrerFinaliteDevis(getEnregistrementFinaliteDevisModel(), new AsyncCallback<Object>() {
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    view.onRpcServiceFailure(null);
                } else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }

            public void onSuccess(Object object) {
                // poste un evenement pour recharger l'opp
                tarificateurService.getDevis(devis.getId(), new AsyncCallback<DevisModel>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }

                    @Override
                    public void onSuccess(DevisModel result) {
                        fireEventLocalBus(new RechargementOpportuniteEvent());
                        rafraichir(result);
                    }
                });
            }
        });
    }

    private EnregistrementFinaliteDevisModel getEnregistrementFinaliteDevisModel() {
        final Long idMotif = view.getMotifDevis().getValue() != null ? view.getMotifDevis().getValue().getIdentifiant() : null;
        final EnregistrementFinaliteDevisModel enregistrementFinalite = new EnregistrementFinaliteDevisModel(devis.getId(), idMotif);
        final List<EnregistrementFinaliteLigneDevisModel> listeEnregistrementsFinaliteLignes = new ArrayList<EnregistrementFinaliteLigneDevisModel>();
        for (LigneDevisModel lignePrincipale : devis.getListeLigneDevis()) {
            listeEnregistrementsFinaliteLignes.add(getEnregistrementFinaliteLigneDevis(lignePrincipale.getIdentifiant(), lignePrincipale.getIdentifiant()));
            for (LigneDevisModel ligneLiee : lignePrincipale.getListeLignesDevisLiees()) {
                listeEnregistrementsFinaliteLignes.add(getEnregistrementFinaliteLigneDevis(lignePrincipale.getIdentifiant(), ligneLiee.getIdentifiant()));
            }
        }
        enregistrementFinalite.setListeEnregistrementsFinaliteLignes(listeEnregistrementsFinaliteLignes);
        return enregistrementFinalite;
    }

    private EnregistrementFinaliteLigneDevisModel getEnregistrementFinaliteLigneDevis(Long idLignePrincipale, Long idLigne) {
        Long idFinalite = null;
        final HasValue<Boolean> rbAdhesion = mapLigneDevisView.get(idLignePrincipale).getMapRbLigneAdhesion().get(idLigne);
        if (rbAdhesion.getValue()) {
            idFinalite = constantesApp.getIdFinaliteAcceptee();
        } else {
            final HasValue<Boolean> rbRefus = mapLigneDevisView.get(idLignePrincipale).getMapRbLigneRefus().get(idLigne);
            if (rbRefus.getValue()) {
                idFinalite = constantesApp.getIdFinaliteRefusee();
            } else {
                final HasValue<Boolean> rbCorbeille = mapLigneDevisView.get(idLignePrincipale).getMapRbLigneCorbeille().get(idLigne);
                if (rbCorbeille.getValue()) {
                    idFinalite = constantesApp.getIdFinaliteCorbeille();
                } else {
                    final HasValue<Boolean> rbEnCours = mapLigneDevisView.get(idLignePrincipale).getMapRbLigneEnCours().get(idLigne);
                    if (rbEnCours.getValue()) {
                        idFinalite = constantesApp.getIdFinaliteEnCours();
                    }
                }
            }
        }
        return new EnregistrementFinaliteLigneDevisModel(idLigne, idFinalite);
    }

    /** Transfère les propositions sélectionnées vers un nouveau devis. */
    private void transfererVersNouveauDevis() {
        final List<Long> listeLignesTransfert = new ArrayList<Long>();

        // on recupere les lignes sélectionnés
        for (LigneDevisModel lignePrincipale : devis.getListeLigneDevis()) {
            final HasValue<Boolean> cbTransfert = mapLigneDevisView.get(lignePrincipale.getIdentifiant()).getCbTransfertLigne();
            if (cbTransfert != null && cbTransfert.getValue()) {
                listeLignesTransfert.add(lignePrincipale.getIdentifiant());
            }
        }

        // Il doit y avoir des lignes sélectionnées pour le transfert
        if (listeLignesTransfert.size() > 0) {
            view.afficherPopupLoadingTransfertDevis();
            // Création des infos de transfert
            final TransfertDevisModel transfertDevis = new TransfertDevisModel();
            transfertDevis.setIdDevis(devis.getId());
            transfertDevis.setListeIdsLignesDevisATransferer(listeLignesTransfert);

            // Création du callback
            final AsyncCallback<Object> callback = new AsyncCallback<Object>() {
                public void onFailure(Throwable caught) {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }

                public void onSuccess(Object result) {
                    // Décochage des propositions
                    for (Long idLigneSelectionne : listeLignesTransfert) {
                        mapLigneDevisView.get(idLigneSelectionne).getCbTransfertLigne().setValue(false);
                    }
                    // on désactive le bouton de transfert
                    view.activerBtnTransfererNouveauDevis(false);

                    view.stopperPopupChargement();
                    view.afficherConfirmationTransfert();
                    fireEventLocalBus(new SuccesTransfertEvent());
                }

            };
            // Appel du service
            tarificateurService.transfererDevis(transfertDevis, infosOpportunite, callback);
        }
    }

    /**
     * Récupère les lignes sélectionnées.
     * @return les identifiants des lignes sélectionnées.
     */
    private List<Long> getLigneSelectionnees() {
        final List<Long> listeIdsLignesSelectionnees = new ArrayList<Long>();
        for (Long idLigneDevis : mapLigneDevisView.keySet()) {
            final LigneDevisView ligneDevisPrincipaleView = mapLigneDevisView.get(idLigneDevis);
            final List<Long> idLignesSelectionnes = ligneDevisPrincipaleView.getLignesDevisSelectionnees();
            listeIdsLignesSelectionnees.addAll(idLignesSelectionnes);
        }
        return listeIdsLignesSelectionnees;
    }

    /**
     * Interface de la vue.
     */
    public interface DevisView extends View {
        /**
         * Affiche la popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Recupere le handler du bouton.
         * @return le handler du bouton
         */
        HasClickHandlers getBtnAjouterProduit();

        /**
         * Recupere le handler du bouton.
         * @return le handler du bouton
         */
        HasClickHandlers getBtnImprimerDevis();

        /**
         * Recupere le handler du bouton.
         * @return le handler du bouton
         */
        HasClickHandlers getBtnEnregistrerDevis();

        /**
         * Recupere le handler du bouton.
         * @return le handler du bouton
         */
        HasClickHandlers getBtnEnvoyerDevisMail();

        /**
         * Recupere le handler du bouton.
         * @return le handler du bouton
         */
        HasClickHandlers getBtnTransformerDevisAia();

        /**
         * Recupere le handler du bouton.
         * @return le handler du bouton
         */
        HasClickHandlers getBtnTransfererNouveauDevis();

        /**
         * Recupere le handler du bouton.
         * @return le handler du bouton
         */
        HasClickHandlers getBtnInformationsAdhesion();

        /**
         * Active ou désactive le bouton.
         * @param actif flag
         */
        void activerBtnTransformerDevisAia(boolean actif);

        /**
         * Active ou désactive le bouton.
         * @param actif flag
         */
        void activerBtnTransfererNouveauDevis(boolean actif);

        /**
         * Active ou désactive le bouton.
         * @param actif flag
         */
        void activerBtnAjouterProduit(boolean actif);

        /**
         * Recupere le libelle de la finalite de devis.
         * @return le libelle
         */
        HasText getLibelleFinaliteDevis();

        /**
         * Définit le style primaire de la finalité de devis.
         * @param styleName le style
         */
        void setStylePrimaryNameFinaliteDevis(String styleName);

        /**
         * Recupere le conteneur d'une ligne de devis.
         * @return le conteneur
         */
        HasWidgets getConteneurLigneDevis();

        /**
         * Recupere la valeur.
         * @return la valeur
         */
        HasValue<IdentifiantLibelleGwt> getMotifDevis();

        /**
         * Active la liste déroulante.
         * @param actif flag actif
         */
        void activerLbMotifDevis(boolean actif);

        /**
         * Cree une vue de ligne de devis.
         * @param constantesApp les constantes
         * @return la vue
         */
        LigneDevisView creerLigneDevisView(ConstantesModel constantesApp);

        /**
         * Affiche la popup de chargement.
         */
        void afficherPopupLoadingEnregistrementDevis();

        /**
         * Affiche la popup de chargement.
         */
        void afficherPopupLoadingTransfertDevis();

        /**
         * Affiche la popup de confirmation.
         */
        void afficherConfirmationTransfert();

        /**
         * Stoppe le message de chargement.
         */
        void stopperPopupChargement();

        /**
         * Affiche la popup de confirmation de succes de la transfo.
         * @param visible le flag visible
         */
        void afficherIconeAlerteFamilleDifferente(boolean visible);

        /**
         * Active ou désactive le bouton d'impression de devis.
         * @param actif flag.
         */
        void activerBtnImprimerDevis(boolean actif);

        /**
         * Active ou désactive le bouton d'envoi par email de devis.
         * @param actif flag.
         */
        void activerBtnEnvoiEmailDevis(boolean actif);

        /**
         * Active ou désactive le bouton permettant d'enregistrer le devis.
         * @param actif true pour activer le bouton, false pour le désactiver.
         */
        void activerBtnEnregistrerDevis(boolean actif);

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getMotifDevisSuggestHandler();

        /**
         * Récupère l'image de warning.
         * @return l'image.
         */
        Image getIconeWarningFamilleDifferente();

        /**
         * Handler pour l'affichage d'icone associés aux champs de la vue.
         * @return le handler.
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Nettoyage.
         */
        void nettoyer();
    }

    @Override
    public void onDetach() {
        nettoyerPresentersLignes();
    }

    /**
     * Récupération de devis.
     * @return the devis
     */
    public DevisModel getDevis() {
        return devis;
    }
}
