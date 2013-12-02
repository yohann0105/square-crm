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
package com.square.composant.tarificateur.square.client;

import java.util.HashMap;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.tarificateur.square.client.bundle.ComposantTarificateurResources;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.event.AffichagePopupChoixProduitEvent;
import com.square.composant.tarificateur.square.client.event.AffichagePopupChoixProduitEventHandler;
import com.square.composant.tarificateur.square.client.event.AffichagePopupEnvoiDevisParMailEvent;
import com.square.composant.tarificateur.square.client.event.AffichagePopupEnvoiDevisParMailEventHandler;
import com.square.composant.tarificateur.square.client.event.AffichagePopupImpressionDevisEvent;
import com.square.composant.tarificateur.square.client.event.AffichagePopupImpressionDevisEventHandler;
import com.square.composant.tarificateur.square.client.event.AffichagePopupSelectionProduitAdherentEvent;
import com.square.composant.tarificateur.square.client.event.AffichagePopupSelectionProduitAdherentEventHandler;
import com.square.composant.tarificateur.square.client.event.AffichagePopupSelectionProduitEvent;
import com.square.composant.tarificateur.square.client.event.AffichagePopupSelectionProduitEventHandler;
import com.square.composant.tarificateur.square.client.event.DemandeTransformationAiaEvent;
import com.square.composant.tarificateur.square.client.event.DemandeTransformationAiaEventHandler;
import com.square.composant.tarificateur.square.client.event.MiseAJourOpportuniteEvent;
import com.square.composant.tarificateur.square.client.event.RechargementOpportuniteEvent;
import com.square.composant.tarificateur.square.client.event.RechargementOpportuniteEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesMajInfosAdhesionEvent;
import com.square.composant.tarificateur.square.client.event.SuccesMajInfosAdhesionEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesTransfertEvent;
import com.square.composant.tarificateur.square.client.event.SuccesTransfertEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesTransformationAiaEvent;
import com.square.composant.tarificateur.square.client.event.SuccesTransformationAiaEventHandler;
import com.square.composant.tarificateur.square.client.event.SuppressionOpportuniteEvent;
import com.square.composant.tarificateur.square.client.exception.ControleIntegriteExceptionGwt;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.opportunite.InfosOpportuniteModel;
import com.square.composant.tarificateur.square.client.model.opportunite.OpportuniteModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModel;
import com.square.composant.tarificateur.square.client.model.transfo.aia.DemandeTransformationAiaModel;
import com.square.composant.tarificateur.square.client.model.transfo.aia.ResultatTransformationAiaModel;
import com.square.composant.tarificateur.square.client.presenter.devis.DevisPresenter;
import com.square.composant.tarificateur.square.client.presenter.popup.PopupEnvoiDevisParMailPresenter;
import com.square.composant.tarificateur.square.client.presenter.popup.PopupImpressionDevisPresenter;
import com.square.composant.tarificateur.square.client.service.ConstantesServiceGwt;
import com.square.composant.tarificateur.square.client.service.ConstantesServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.DimensionServiceGwt;
import com.square.composant.tarificateur.square.client.service.DimensionServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.ProduitServiceGwt;
import com.square.composant.tarificateur.square.client.service.ProduitServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.SelecteurProduitServiceGwt;
import com.square.composant.tarificateur.square.client.service.SelecteurProduitServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurEditiqueServiceGwt;
import com.square.composant.tarificateur.square.client.service.TarificateurEditiqueServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurPersonneServiceGwt;
import com.square.composant.tarificateur.square.client.service.TarificateurPersonneServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwt;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurTransformationAiaServiceGwt;
import com.square.composant.tarificateur.square.client.service.TarificateurTransformationAiaServiceGwtAsync;
import com.square.composant.tarificateur.square.client.ui.popup.choix.PopupChoixProduit;
import com.square.composant.tarificateur.square.client.ui.popup.selection.PopupSelectionProduits;
import com.square.composant.tarificateur.square.client.view.devis.DevisViewImpl;
import com.square.composant.tarificateur.square.client.view.popup.PopupChoixModelesViewImpl;
import com.square.composant.tarificateur.square.client.view.popup.PopupChoixModelesViewImpl.TypePopup;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Composant Tarificateur.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ComposantTarificateur extends Presenter {

    /** Instance des ressources. */
    public static final ComposantTarificateurResources RESOURCES = GWT.create(ComposantTarificateurResources.class);

    /** Constantes du composant. */
    public static final ComposantTarificateurConstants CONSTANTS = GWT.create(ComposantTarificateurConstants.class);

    private ConstantesModel constantesApp;

    private ComposantTarificateurView composantTarificateurView;

    private ConstantesServiceGwtAsync constantesRpcService;

    private ProduitServiceGwtAsync produitRpcService;

    private TarificateurServiceGwtAsync tarificateurRpcService;

    private DimensionServiceGwtAsync dimensionRpcService;

    private TarificateurPersonneServiceGwtAsync tarificateurPersonneRpcService;

    private TarificateurTransformationAiaServiceGwtAsync tarificateurTransformationAiaRpcService;

    private SelecteurProduitServiceGwtAsync selecteurProduitRpcService;

    private TarificateurEditiqueServiceGwtAsync tarificateurEditiqueRpcService;

    private InfosOpportuniteModel infosOpportunite;

    private DeskBar deskBar;

    private PopupSelectionProduits popupSelectionProduits;

    private PopupChoixProduit popupChoixProduit;

    private AffichagePopupChoixProduitEventHandler affichagePopupChoixProduitEventHandler;

    private AffichagePopupEnvoiDevisParMailEventHandler affichagePopupEnvoiDevisParMailEventHandler;

    private AffichagePopupImpressionDevisEventHandler affichagePopupImpressionDevisEventHandler;

    private SuccesTransformationAiaEventHandler succesTransformationAiaEventHandler;

    private SuccesTransfertEventHandler succesTransfertEventHandler;

    private PopupEnvoiDevisParMailPresenter popupEnvoiDevisParMailPresenter;

    private PopupImpressionDevisPresenter popupImpression;

    private Map<Long, DevisPresenter> listeDevisPresenter;

    private HandlerRegistration popupHandlerRegistration;

    private HandlerRegistration popupChoixProduitHandlerRegistration;

    private HandlerRegistration popupChoixProduitAdherentHandlerRegistration;

    /**
     * Constructeur.
     * @param eventBus le bus d'evenement
     * @param deskBar la deskBar
     * @param infosOpportunite les infos sur l'opportunite
     * @param hasRoleAdmin si l'utilisateur a le role admin
     * @param hasRoleAnimateur si l'utilisateur a le role animateur
     */
    public ComposantTarificateur(final HandlerManager eventBus, final DeskBar deskBar, final InfosOpportuniteModel infosOpportunite, boolean hasRoleAdmin,
        boolean hasRoleAnimateur) {
        super(eventBus);
        // instancier les resources ici
        StyleInjector.inject(RESOURCES.css().getText());

        // Création des services
        this.constantesRpcService = GWT.create(ConstantesServiceGwt.class);
        this.produitRpcService = GWT.create(ProduitServiceGwt.class);
        this.tarificateurRpcService = GWT.create(TarificateurServiceGwt.class);
        this.selecteurProduitRpcService = GWT.create(SelecteurProduitServiceGwt.class);
        this.tarificateurTransformationAiaRpcService = GWT.create(TarificateurTransformationAiaServiceGwt.class);
        this.tarificateurEditiqueRpcService = GWT.create(TarificateurEditiqueServiceGwt.class);
        this.dimensionRpcService = GWT.create(DimensionServiceGwt.class);
        this.tarificateurPersonneRpcService = GWT.create(TarificateurPersonneServiceGwt.class);

        this.deskBar = deskBar;
        this.infosOpportunite = infosOpportunite;
        this.composantTarificateurView = new ComposantTarificateurViewImpl(hasRoleAdmin, hasRoleAnimateur);
        this.listeDevisPresenter = new HashMap<Long, DevisPresenter>();

        initCommonHandlers();

        constantesRpcService.getConstantes(new AsyncCallback<ConstantesModel>() {
            @Override
            public void onFailure(Throwable caught) {
                composantTarificateurView.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(ConstantesModel constantesGwt) {
                constantesApp = constantesGwt;
                popupSelectionProduits = new PopupSelectionProduits(getLocalBus(), constantesApp, selecteurProduitRpcService, infosOpportunite, deskBar);
                popupHandlerRegistration =
                    popupSelectionProduits.getEventBusLocal().addHandler(RechargementOpportuniteEvent.TYPE, new RechargementOpportuniteEventHandler() {
                        @Override
                        public void rechargerOpportunite(RechargementOpportuniteEvent event) {
                            chargerOpportunite(true, false);
                        }
                    });
                popupChoixProduit = new PopupChoixProduit(getLocalBus(), constantesApp, tarificateurRpcService, produitRpcService);
                popupChoixProduitAdherentHandlerRegistration =
                    popupChoixProduit.getEventBusLocal().addHandler(AffichagePopupSelectionProduitAdherentEvent.TYPE,
                        new AffichagePopupSelectionProduitAdherentEventHandler() {
                            @Override
                            public void afficherPopup(AffichagePopupSelectionProduitAdherentEvent event) {
                                popupSelectionProduits.chargerProduitAdherentPourDevis(event.getIdDevis(), event.getProduitAia(), event.getGarantieAia());
                            }
                        });
                popupChoixProduitHandlerRegistration =
                    popupChoixProduit.getEventBusLocal().addHandler(AffichagePopupSelectionProduitEvent.TYPE, new AffichagePopupSelectionProduitEventHandler() {
                        @Override
                        public void afficherPopup(AffichagePopupSelectionProduitEvent event) {
                            popupSelectionProduits.chargerProduitPourDevis(event.getIdProduit(), event.getIdDevis(), event.getIdLigneDevis(), event
                                    .getTypeDevis());
                        }
                    });
                chargerOpportunite(false, false);
            }
        });
    }

    /**
     * Initialisation des handlers communs a tous les présenters.
     */
    private void initCommonHandlers() {
        affichagePopupChoixProduitEventHandler = new AffichagePopupChoixProduitEventHandler() {
            @Override
            public void afficherPopup(AffichagePopupChoixProduitEvent event) {
                popupChoixProduit.afficherChoixProduitPourDevis(event.getIdDevis(), event.getTypeDevis(), infosOpportunite.getPersonne().getEidPersonne());
            }
        };

        affichagePopupEnvoiDevisParMailEventHandler = new AffichagePopupEnvoiDevisParMailEventHandler() {
            @Override
            public void afficherPopup(AffichagePopupEnvoiDevisParMailEvent event) {
                String emailDestinataire = "";
                if (infosOpportunite != null && infosOpportunite.getPersonne() != null && infosOpportunite.getPersonne().getEmail() != null) {
                    emailDestinataire = infosOpportunite.getPersonne().getEmail().getAdresse();
                }
                if (popupEnvoiDevisParMailPresenter == null) {
                    popupEnvoiDevisParMailPresenter =
                        addChildPresenter(new PopupEnvoiDevisParMailPresenter(eventBus, new PopupChoixModelesViewImpl(TypePopup.EMAIL), constantesApp,
                            tarificateurRpcService, tarificateurEditiqueRpcService, dimensionRpcService, event.getDevis(), event.getReferenceOpportunite(),
                            event.getListeIdsLignesSelectionnees(), emailDestinataire, infosOpportunite.getEidResponsable(), deskBar));
                    popupEnvoiDevisParMailPresenter.showPresenter(null);
                } else {
                    popupEnvoiDevisParMailPresenter.afficherPourEnvoi(event.getDevis(), event.getReferenceOpportunite(),
                        event.getListeIdsLignesSelectionnees(), emailDestinataire);
                }
            }
        };

        affichagePopupImpressionDevisEventHandler = new AffichagePopupImpressionDevisEventHandler() {
            @Override
            public void afficherPopup(AffichagePopupImpressionDevisEvent event) {
                if (popupImpression == null) {
                    popupImpression =
                        addChildPresenter(new PopupImpressionDevisPresenter(eventBus, new PopupChoixModelesViewImpl(TypePopup.IMPRESSION), constantesApp,
                            tarificateurRpcService, event.getDevis(), event.getDevisModificationModel(), event.getReferenceOpportunite(), event
                                    .getListeIdsLignesSelectionnees()));
                    popupImpression.showPresenter(null);
                } else {
                    popupImpression.demanderImpression(event.getDevis(), event.getDevisModificationModel(), event.getReferenceOpportunite(), event
                            .getListeIdsLignesSelectionnees());
                }
            }
        };

        succesTransformationAiaEventHandler = new SuccesTransformationAiaEventHandler() {
            @Override
            public void onSuccess(SuccesTransformationAiaEvent event) {
                // Propagation
                fireEventLocalBus(event);
            }
        };

        succesTransfertEventHandler = new SuccesTransfertEventHandler() {
            @Override
            public void onSuccess(SuccesTransfertEvent event) {
                // Propagation
                fireEventLocalBus(event);
            }
        };
    }

    /**
     * Charge une opportunite via les infos fournies.
     * @param envoiEvenementMajStatutOpp flag pour savoir si on envoit un evenement pour mettre a jour le statut de l'opp à la fin du chargement
     * @param rechargerSeulementEntete flag recharger uniquement l'en-tête
     */
    public void chargerOpportunite(final boolean envoiEvenementMajStatutOpp, final boolean rechargerSeulementEntete) {
        composantTarificateurView.afficherPopupChargementOpportunite();
        tarificateurRpcService.getOrCreateOpportunite(infosOpportunite, new AsyncCallback<OpportuniteModel>() {
            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    composantTarificateurView.stopperPopupChargement();
                    ErrorPopup.afficher(new ErrorPopupConfiguration(rapport.getMessageGenerale()));
                    composantTarificateurView.enableAjoutDevis(false);
                } else {
                    composantTarificateurView.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }

            @Override
            public void onSuccess(OpportuniteModel opportunite) {
                updateViewOpportunite(opportunite, rechargerSeulementEntete);
                if (envoiEvenementMajStatutOpp) {
                    fireEventLocalBus(new MiseAJourOpportuniteEvent(opportunite.getFinalite().getEidFinalite()));
                } else {
                    composantTarificateurView.stopperPopupChargement();
                }
            }
        });
    }

    /**
     * Met à jour les informations de l'opportunité dans la vue.
     * @param opportunite opportunité chargée.
     * @param seulementEntete flag mise a jour en tête
     */
    protected void updateViewOpportunite(OpportuniteModel opportunite, boolean seulementEntete) {
        composantTarificateurView.getDateCloture().setText(opportunite.getDateCloture());
        composantTarificateurView.getDateSignature().setText(opportunite.getDateSignature());
        composantTarificateurView.getDateEditionBA().setText(opportunite.getDateEditionBA());
        composantTarificateurView.getNature().setText(opportunite.getNaturePersonnePrincipale());

        // On peut ne pas avoir a demander tout le contenu (par exemple lorsque la demande de mise a jour proviens du devis lui meme
        boolean aDevisVide = false;
        if (!seulementEntete) {
            nettoyerPresentersDevis();
            composantTarificateurView.initConteneurDevis();

            if (opportunite.getListeDevis().size() > 0) {
                // charge les devis
                for (DevisModel devis : opportunite.getListeDevis()) {
                    if (devis.getListeLigneDevis().isEmpty()) {
                        aDevisVide = true;
                    }
                    construirePresenterDevis(devis);
                }
            } else {
                composantTarificateurView.afficherAucunDevis();
            }
        } else {
            aDevisVide = listeDevisPresenter.isEmpty();
        }

        // On active le bouton pour ajouter un devis seulement
        // Si le statut de l'opportunité est en cours et si il n'y a aucun devis vide déjà existant
        final Long idFinaliteOpp = opportunite.getFinalite().getIdentifiant();
        composantTarificateurView.enableAjoutDevis(constantesApp.getIdFinaliteEnCours().equals(idFinaliteOpp) && !aDevisVide);
        // On peut re-ouvrir permettrait les opp ayant un statut "transformé" ou "accepté" ou "refusé" ou "corbeille"
        final boolean enableReouverture =
            constantesApp.getIdFinaliteAcceptee().equals(idFinaliteOpp) || constantesApp.getIdFinaliteTransformee().equals(idFinaliteOpp)
                || constantesApp.getIdFinaliteRefusee().equals(idFinaliteOpp) || constantesApp.getIdFinaliteCorbeille().equals(idFinaliteOpp);
        composantTarificateurView.stopperPopupChargement();
    }

    /**
     * Construction & binding d'un présenter pour un devis.
     * @param devis le devis
     */
    private void construirePresenterDevis(final DevisModel devis) {
        if (!listeDevisPresenter.containsKey(devis.getId())) {
            final DevisPresenter devisPresenter =
                new DevisPresenter(eventBus, constantesApp, devis, new DevisViewImpl(), tarificateurRpcService, infosOpportunite,
                    tarificateurTransformationAiaRpcService, tarificateurPersonneRpcService, dimensionRpcService, deskBar);
            listeDevisPresenter.put(devis.getId(), devisPresenter);
            devisPresenter.addEventHandlerToLocalBus(AffichagePopupChoixProduitEvent.TYPE, affichagePopupChoixProduitEventHandler);
            devisPresenter.addEventHandlerToLocalBus(AffichagePopupEnvoiDevisParMailEvent.TYPE, affichagePopupEnvoiDevisParMailEventHandler);
            devisPresenter.addEventHandlerToLocalBus(AffichagePopupImpressionDevisEvent.TYPE, affichagePopupImpressionDevisEventHandler);
            devisPresenter.addEventHandlerToLocalBus(RechargementOpportuniteEvent.TYPE, new RechargementOpportuniteEventHandler() {
                @Override
                public void rechargerOpportunite(RechargementOpportuniteEvent event) {
                    // Dans le cas présent, le devis est déjà a jour et demande que l'entete soit mis a jour:
                    chargerOpportunite(true, true);
                }
            });
            devisPresenter.addEventHandlerToLocalBus(SuccesTransformationAiaEvent.TYPE, succesTransformationAiaEventHandler);
            devisPresenter.addEventHandlerToLocalBus(SuccesTransfertEvent.TYPE, succesTransfertEventHandler);
            devisPresenter.addEventHandlerToLocalBus(DemandeTransformationAiaEvent.TYPE, new DemandeTransformationAiaEventHandler() {
                @Override
                public void demanderTransformation(DemandeTransformationAiaEvent event) {
                    transformerDevisAia(event.getIdDevis(), event.isCreerActionSuivi(), event.isRechargerInfosAdhesion());
                }
            });
            devisPresenter.addEventHandlerToLocalBus(SuccesMajInfosAdhesionEvent.TYPE, new SuccesMajInfosAdhesionEventHandler() {
                @Override
                public void onSuccess(SuccesMajInfosAdhesionEvent event) {
                    if (event.getInfosPaiement() != null) {
                        // On met à jour la date de signature
                        composantTarificateurView.getDateSignature().setText(event.getInfosPaiement().getDateSignature());
                    }
                    // Propagation
                    fireEventLocalBus(event);
                }
            });
            devisPresenter.addEventHandlerToLocalBus(AffichagePopupSelectionProduitEvent.TYPE, new AffichagePopupSelectionProduitEventHandler() {
                @Override
                public void afficherPopup(AffichagePopupSelectionProduitEvent event) {
                    popupSelectionProduits.chargerProduitPourDevis(event.getIdProduit(), event.getIdDevis(), event.getIdLigneDevis(), event.getTypeDevis());
                }
            });

            devisPresenter.showPresenter(composantTarificateurView.getConteneurDevis());
        } else {
            listeDevisPresenter.get(devis.getId()).rafraichir(devis);
        }
    }

    /**
     * Transforme le devis en devis AIA.
     * @param idDevis l'id du devis
     * @param creerActionSuivi flag actionSuivi
     * @param rechargerInfosAdhesion flag recharger info adhesion
     */
    public void transformerDevisAia(final Long idDevis, final boolean creerActionSuivi, final boolean rechargerInfosAdhesion) {
        composantTarificateurView.afficherPopupLoadingTransformationDevisAia();

        // Création du callback
        final AsyncCallback<ResultatTransformationAiaModel> callback = new AsyncCallback<ResultatTransformationAiaModel>() {
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    if (listeDevisPresenter.containsKey(idDevis)) {
                        // on affiche la popup de renseignement des infos de paiement avec le rapport d'erreur
                        final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                        if (!rechargerInfosAdhesion) {
                            composantTarificateurView.stopperPopupChargement();
                        }
                        listeDevisPresenter.get(idDevis).afficherInformationsAdhesions(true, true, rapport, rechargerInfosAdhesion);
                    }
                } else {
                    composantTarificateurView.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }

            public void onSuccess(final ResultatTransformationAiaModel resultat) {
                composantTarificateurView.stopperPopupChargement();
                listeDevisPresenter.get(idDevis).fermerInformationsAdhesion();

                // si erreurs bloquantes
                if (resultat.getListeErreursBloquantes() != null && resultat.getListeErreursBloquantes().size() > 0) {
                    final StringBuffer buffer = new StringBuffer();
                    for (String erreur : resultat.getListeErreursBloquantes()) {
                        buffer.append("- ").append(erreur).append("<br />");
                    }
                    composantTarificateurView.afficherErreursBloquantesTransformationDevisAia(buffer.toString());
                }
                // si erreurs non bloquantes
                else if (resultat.getListeErreursNonBloquantes() != null && resultat.getListeErreursNonBloquantes().size() > 0) {
                    final StringBuffer buffer = new StringBuffer();
                    for (String erreur : resultat.getListeErreursNonBloquantes()) {
                        buffer.append("- ").append(erreur).append("<br />");
                    }
                    composantTarificateurView.afficherErreursNonBloquantesTransformationDevisAia(buffer.toString());
                } else {
                    final PopupCallback callback = new PopupCallback() {
                        @Override
                        public void onResult(boolean result) {
                            // on notifie du succes de la tranformation
                            fireEventLocalBus(new SuccesTransformationAiaEvent());
                        }
                    };
                    composantTarificateurView.afficherPopupSuccesTransformationDevisAia(callback);
                }
            }

        };

        final DemandeTransformationAiaModel demande = new DemandeTransformationAiaModel();
        demande.setIdDevis(idDevis);
        demande.setLoginUtilisateurConnecte(infosOpportunite.getLoginUtilisateurConnecte());
        demande.setCreateActionSuivi(creerActionSuivi);
        // Appel du service
        tarificateurTransformationAiaRpcService.transformerDevisAia(demande, callback);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBind() {

        this.composantTarificateurView.getBtnSupprimerOpportunite().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEventLocalBus(new SuppressionOpportuniteEvent(ComposantTarificateur.this.infosOpportunite.getEidOpportunite()));
            }
        });

    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(composantTarificateurView.asWidget());
    }

    @Override
    public View asView() {
        return composantTarificateurView;
    }

    /**
     * Retourne le handler du bouton ajouter devis.
     * @return le handler
     */
    public HasClickHandlers getBtnAjouterDevisClickHandler() {
        return composantTarificateurView.getBtnAjouterDevis();
    }

    /**
     * Retourne le handler du bouton ajouter devis.
     * @return le handler
     */
    public HasClickHandlers getBtnVoirActionsClickHandler() {
        return composantTarificateurView.getBtnVoirActions();
    }

    /**
     * Ouvre la popup de choix de produit pour créer un nouveau devis.
     * @param nouvellesInfosOpportunite les infos de l'opportunité avec la derniere personne à jour
     */
    public void creerNouveauDevis(InfosOpportuniteModel nouvellesInfosOpportunite) {
        this.infosOpportunite = nouvellesInfosOpportunite;
        popupSelectionProduits.chargerNouvellesInfosOpportunite(nouvellesInfosOpportunite);
        popupChoixProduit.afficherChoixProduitPourDevis(null, constantesApp.getTypeNeutre(), infosOpportunite.getPersonne().getEidPersonne());
    }

    /**
     * Interface pour la vue ComposantTarificateurView.
     */
    public interface ComposantTarificateurView extends View {
        /**
         * Methode appelé lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Recupere le click handler du bouton d'ajout de devis.
         * @return le click handler
         */
        HasClickHandlers getBtnAjouterDevis();

        /**
         * Recupere le click handler du bouton voir les actions.
         * @return le click handler
         */
        HasClickHandlers getBtnVoirActions();

        /**
         * Recupere le click handler du bouton supprimer l'opportunité.
         * @return le click handler.
         */
        HasClickHandlers getBtnSupprimerOpportunite();

        /**
         * Recupere le conteneur d'un devis.
         * @return le conteneur
         */
        HasWidgets getConteneurDevis();

        /**
         * Initialise le conteneur des devis.
         */
        void initConteneurDevis();

        /**
         * Stoppe le message de chargement.
         */
        void stopperPopupChargement();

        /**
         * Affiche le message de chargement.
         */
        void afficherPopupChargementOpportunite();

        /**
         * Affiche aucun devis.
         */
        void afficherAucunDevis();

        /**
         * Récupère le texte de la date de cloture.
         * @return le texte.
         */
        HasText getDateCloture();

        /**
         * Récupère le texte de la date de signature.
         * @return le texte.
         */
        HasText getDateSignature();

        /**
         * Récupère le texte de la date d'édition du BA.
         * @return le texte.
         */
        HasText getDateEditionBA();

        /**
         * Récupère le texte de la nature.
         * @return le texte.
         */
        HasText getNature();

        /**
         * Active / désactive l'ajout de devis.
         * @param enable true pour activer, false pour désactiver
         */
        void enableAjoutDevis(boolean enable);

        /**
         * Affiche la popup de chargement.
         */
        void afficherPopupLoadingTransformationDevisAia();

        /**
         * Affiche la popup des erreurs de la transfo.
         * @param erreurs les erreurs
         */
        void afficherErreursBloquantesTransformationDevisAia(String erreurs);

        /**
         * Affiche la popup des erreurs de la transfo.
         * @param erreurs les erreurs
         */
        void afficherErreursNonBloquantesTransformationDevisAia(String erreurs);

        /**
         * Affiche la popup de confirmation de succes de la transfo.
         * @param callback le callback
         */
        void afficherPopupSuccesTransformationDevisAia(PopupCallback callback);
    }

    private void nettoyerPresentersDevis() {
        for (DevisPresenter presenter : listeDevisPresenter.values()) {
            GWT.log("onDetach " + presenter);
            presenter.detachPresenter();
        }
        listeDevisPresenter.clear();
    }

    @Override
    public void onDetach() {
        nettoyerPresentersDevis();
        popupHandlerRegistration.removeHandler();
        popupChoixProduitHandlerRegistration.removeHandler();
        popupChoixProduitAdherentHandlerRegistration.removeHandler();
        GWT.log("onDetach " + this);
    }
}
