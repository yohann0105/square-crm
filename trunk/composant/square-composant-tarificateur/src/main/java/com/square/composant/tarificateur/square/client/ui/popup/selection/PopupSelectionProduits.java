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
package com.square.composant.tarificateur.square.client.ui.popup.selection;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.exception.BusinessExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.exception.TechnicalExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.evenement.Evenement;
import org.scub.foundation.framework.gwt.module.client.util.evenement.ObservableGenerique;
import org.scub.foundation.framework.gwt.module.client.util.evenement.Observateur;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.event.RechargementOpportuniteEvent;
import com.square.composant.tarificateur.square.client.exception.ControleIntegriteExceptionGwt;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.opportunite.InfosOpportuniteModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.ligne.LigneDevisModel;
import com.square.composant.tarificateur.square.client.model.personne.BeneficiaireModel;
import com.square.composant.tarificateur.square.client.model.selecteur.SelecteurProduitModel;
import com.square.composant.tarificateur.square.client.service.SelecteurProduitServiceGwtAsync;
import com.square.composant.tarificateur.square.client.ui.panel.produits.PanelProduits;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEventHandler;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;
import com.square.composants.graphiques.lib.client.popups.minimizable.PopupMinimizable;

/**
 * Popup de sélection des produits.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class PopupSelectionProduits extends Popup implements Observateur {

    /**
     * The constants used in this page.
     */
    public interface PageConstants extends Constants {

        /** LARGEUR_POPUP. */
        String LARGEUR_POPUP = "900px";

        /** Marge Popup. */
        int MARGE_POPUP = 110;

        /**
         * Le texte.
         * @return le texte
         */
        String titreBoutonAjouter();

        /**
         * Le texte.
         * @return le texte
         */
        String titreBoutonModifier();

        /**
         * Le texte.
         * @return le texte
         */
        String titreBoutonUniformiserDate();

        /**
         * Le texte.
         * @return le texte
         */
        String titreBoutonAnnuler();

        /**
         * Label.
         * @return le label
         */
        String reduire();
    }

    /**
     * The messages used in this page.
     */
    public interface PageMessages extends Messages {

        /**
         * Retourne le message.
         * @return le message
         */
        String chargementProduitEnCours();

        /**
         * Retourne le message.
         * @return le message
         */
        String chargementLigneDevisEnCours();

        /**
         * Retourne le message.
         * @return le message
         */
        String ajoutPropositionEnCours();

        /**
         * Retourne le message.
         * @return le message
         */
        String modificationPropositionEnCours();

        /**
         * Message indiquant que la date d'effet du devis est incorrect pour le courtier connecté.
         * @return le message.
         */
        String erreurDateEffetCourtier();

        /**
         * Message indiquant qu'une erreur c'est produite lors de la récupération de l'utilisateur connécté.
         * @return le message
         */
		String errorUserAbsent();
    }

    /** Page constants. */
    private PageConstants pageConstants;

    /** Page messages. */
    private PageMessages pageMessages;

    /** Panel des produits. */
    private PanelProduits panelProduits;

    private SelecteurProduitServiceGwtAsync selecteurProduitService;

    private ConstantesModel constantesMapping;

    private ObservableGenerique obsGenerique = new ObservableGenerique();

    private InfosOpportuniteModel infosOpportunite;

    private boolean isDevisProduitAdherent = false;

    private List<Long> identifiantsBeneficiairesInactifs;

    private HandlerManager eventBus;
    private HandlerManager eventBusLocal;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    private DeskBar deskBar;

    private DecoratedButton btnReduire;

    private ComposantTarificateurConstants constantComposantTarificateur;

    /**
     * Constructeur.
     * @param eventBus le bus d'evenement
     * @param constantesMapping constantes de mapping nécessaires au composant.
     * @param selecteurProduitService selecteurProduitService
     * @param infosOpportunite infosOpportunite
     * @param deskBar deskBar
     */
    public PopupSelectionProduits(final HandlerManager eventBus, ConstantesModel constantesMapping,
        SelecteurProduitServiceGwtAsync selecteurProduitService, InfosOpportuniteModel infosOpportunite, DeskBar deskBar) {
        super(ComposantTarificateur.CONSTANTS.titrePopupSelectionProduit(), false, true, true);
        iconeErreurChampManager = new IconeErreurChampManager();

        // Create the page constants
        pageConstants = (PageConstants) GWT.create(PageConstants.class);
        // Create the page messages
        pageMessages = (PageMessages) GWT.create(PageMessages.class);

        this.constantComposantTarificateur = GWT.create(ComposantTarificateurConstants.class);
        this.addStyleName(ComposantTarificateur.RESOURCES.css().selectionProduits());
        this.constantesMapping = constantesMapping;
        this.infosOpportunite = infosOpportunite;
        this.selecteurProduitService = selecteurProduitService;
        this.eventBus = eventBus;
        eventBusLocal = new HandlerManager(this);
        this.deskBar = deskBar;
    }

    /** Initialisation des composants. */
    private void initComposants(final boolean afficherBoutonAjouter, final boolean afficherBoutonModifier) {
        // Panneau des boutons
        final HorizontalPanel panelBoutons = new HorizontalPanel();
        panelBoutons.setSpacing(5);
        panelBoutons.addStyleName(ComposantTarificateur.RESOURCES.css().panelBoutons());

        if (afficherBoutonAjouter) {
            final DecoratedButton btnAjouter = new DecoratedButton(pageConstants.titreBoutonAjouter());
            btnAjouter.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    saveLigneDevisParSelecteurProduit(true);
                }
            });
            panelBoutons.add(btnAjouter);
        }
        if (afficherBoutonModifier) {
            final DecoratedButton btnModifier = new DecoratedButton(pageConstants.titreBoutonModifier());
            btnModifier.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    saveLigneDevisParSelecteurProduit(false);
                }
            });
            panelBoutons.add(btnModifier);
        }
        final DecoratedButton btnUniformiserDate = new DecoratedButton(pageConstants.titreBoutonUniformiserDate());
        btnUniformiserDate.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                panelProduits.uniformiserDateEffet();
            }
        });
        panelBoutons.add(btnUniformiserDate);
        btnReduire = new DecoratedButton(pageConstants.reduire());
        panelBoutons.add(btnReduire);
        final DecoratedButton btnAnnuler = new DecoratedButton(pageConstants.titreBoutonAnnuler());
        btnAnnuler.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();
            }
        });
        panelBoutons.add(btnAnnuler);

        deskBar.getEventBus().addHandler(EnableMinimizeWidgetEvent.TYPE, new EnableMinimizeWidgetEventHandler() {
            @Override
            public void enableMinimizeWidget(EnableMinimizeWidgetEvent event) {
                btnReduire.setEnabled(event.isEnabled());
            }
        });

        // Création du panel des produits dans un scrollPanel
        panelProduits = new PanelProduits(constantesMapping, selecteurProduitService, this);
        final ScrollPanel scrollPanel = new ScrollPanel(panelProduits);
        // final int hauteur = Window.getClientHeight() - PageConstants.MARGE_POPUP;
        final int hauteur = ComposantTarificateurConstants.HAUTEUR_POPUP_SELECTION;
        scrollPanel.setHeight(hauteur + "px");

        final FocusPanel focusPanel = new FocusPanel(scrollPanel);
        focusPanel.setWidth(ComposantTarificateurConstants.POURCENT_100);
        focusPanel.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    if (afficherBoutonAjouter) {
                        saveLigneDevisParSelecteurProduit(true);
                    } else if (afficherBoutonModifier) {
                        saveLigneDevisParSelecteurProduit(false);
                    }
                } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide();
                }
            }
        });

        final VerticalPanel panelConteneur = new VerticalPanel();
        panelConteneur.setWidth(ComposantTarificateurConstants.POURCENT_100);
        panelConteneur.add(panelBoutons);
        panelConteneur.add(focusPanel);
        panelConteneur.setWidth(PageConstants.LARGEUR_POPUP);

        this.setWidget(panelConteneur);
    }

    /**
     * Chargement d'un produit.
     * @param idProduitPrincipal l'identifiant du produit (peut être null si modification d'une proposition)
     * @param idDevis l'identifiant du devis associé (pour ajout ou modification d'une ligne de devis)
     * @param idLigneDevis l'identifiant de la ligne de devis à modifier (peut être null si ajout)
     * @param typeDevis le type du devis (Santé ou CNP)
     */
    public void chargerProduitPourDevis(Integer idProduitPrincipal, Long idDevis, Long idLigneDevis, String typeDevis) {
        this.isDevisProduitAdherent = false;

        // Bouton de modification visible si modification (idLigneDevis != null)
        initComposants(idLigneDevis == null, idLigneDevis != null);

        // Creation du callback
        final AsyncCallback<SelecteurProduitModel> callback = new AsyncCallback<SelecteurProduitModel>() {
            public void onFailure(Throwable caught) {
                LoadingPopup.stopAll();
                if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_DATE_PARSING)) {
            		ErrorPopup.afficher(new ErrorPopupConfiguration(new TechnicalExceptionGwt(constantComposantTarificateur.errorDateParsing())));
            	} else if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_DATE_INVALID)) {
            		ErrorPopup.afficher(new ErrorPopupConfiguration(new BusinessExceptionGwt(constantComposantTarificateur.errorDateInvalid())));
            	} else if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_DATE_ABSENT)) {
            		ErrorPopup.afficher(new ErrorPopupConfiguration(new BusinessExceptionGwt(constantComposantTarificateur.errorDateAbsent())));
            	} else {
            		ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
            	}
            }

            public void onSuccess(SelecteurProduitModel selecteurProduitGwt) {
                LoadingPopup.stop();

                // Initialisation du panel produit pour modification
                panelProduits.chargerSelecteurProduit(selecteurProduitGwt, iconeErreurChampManager);
            }
        };

        // Si la ligne de devis est null : ajout => initialisation du panel du produit
        if (idLigneDevis == null) {
            final LoadingPopupConfiguration configPopup = new LoadingPopupConfiguration(pageMessages.chargementProduitEnCours());
            LoadingPopup.afficher(configPopup);
            // Creation : récupération d'un selecteur initial
            // on n'utilisera la personne de l'opportunité que si nouveau devis, sinon on prendra la personne du devis
            selecteurProduitService.getSelecteurParProduit(idProduitPrincipal, idDevis, infosOpportunite.getPersonne(), callback);
        }
        else {
            final LoadingPopupConfiguration configPopup = new LoadingPopupConfiguration(pageMessages.chargementLigneDevisEnCours());
            LoadingPopup.afficher(configPopup);
            // Modification : récupération du selecteur rempli pour la ligne de devis concernée
            selecteurProduitService.getSelecteurProduitParLigneDevis(idLigneDevis, idDevis, callback);
        }
    }

    /**
     * Chargement d'un produit.
     * @param idDevis l'identifiant du devis associé (pour ajout ou modification d'une ligne de devis)
     * @param produitAia le produitAia
     * @param garantieAia la garantieAia
     */
    public void chargerProduitAdherentPourDevis(Long idDevis, String produitAia, String garantieAia) {
    	this.isDevisProduitAdherent = true;
        initComposants(true, false);

        // Creation du callback
        final AsyncCallback<SelecteurProduitModel> callback = new AsyncCallback<SelecteurProduitModel>() {
            public void onFailure(Throwable caught) {
                LoadingPopup.stopAll();
                if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_DATE_PARSING)) {
            		ErrorPopup.afficher(new ErrorPopupConfiguration(new TechnicalExceptionGwt(constantComposantTarificateur.errorDateParsing())));
            	} else if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_DATE_INVALID)) {
            		ErrorPopup.afficher(new ErrorPopupConfiguration(new BusinessExceptionGwt(constantComposantTarificateur.errorDateInvalid())));
            	} else if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_DATE_ABSENT)) {
            		ErrorPopup.afficher(new ErrorPopupConfiguration(new BusinessExceptionGwt(constantComposantTarificateur.errorDateAbsent())));
            	} else {
            		ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
            	}
            }

            public void onSuccess(SelecteurProduitModel selecteurProduitGwt) {
                LoadingPopup.stop();

                // Initialisation du panel produit pour modification
                panelProduits.chargerSelecteurProduit(selecteurProduitGwt, iconeErreurChampManager);
            }
        };

        final LoadingPopupConfiguration configPopup = new LoadingPopupConfiguration(pageMessages.chargementLigneDevisEnCours());
        LoadingPopup.afficher(configPopup);
        // Modification : récupération du selecteur rempli pour le produit de l'adherent
        selecteurProduitService.getSelecteurProduitParProduitsAdherent(idDevis, produitAia, garantieAia, infosOpportunite.getPersonne(), callback);
    }

    /** Ajoute la proposition au devis. */
    private void saveLigneDevisParSelecteurProduit(boolean isCreate) {
        // Contrôle des contraintes de vente
        if (panelProduits.hasErreurs()) {
            return;
        }
        else {
            if (isCreate) {
                final LoadingPopupConfiguration configPopup = new LoadingPopupConfiguration(pageMessages.ajoutPropositionEnCours());
                LoadingPopup.afficher(configPopup);
            }
            else {
                final LoadingPopupConfiguration configPopup = new LoadingPopupConfiguration(pageMessages.modificationPropositionEnCours());
                LoadingPopup.afficher(configPopup);
            }

            // Récupération du selecteurProduit
            final SelecteurProduitModel selecteurProduit = panelProduits.getSelecteurProduit();

            // Création du callback
            final AsyncCallback<LigneDevisModel> callback = new AsyncCallback<LigneDevisModel>() {
                public void onFailure(Throwable caught) {
                    LoadingPopup.stopAll();
                    if (caught instanceof ControleIntegriteExceptionGwt) {
                        final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                        // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                        iconeErreurChampManager.fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    }
                    else {
                    	if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_USER_ABSENT)) {
                    		ErrorPopup.afficher(new ErrorPopupConfiguration(new BusinessExceptionGwt(pageMessages.errorUserAbsent())));
                    	}
                        ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
                    }
                }

                public void onSuccess(LigneDevisModel ligne) {
                    hide();
                    LoadingPopup.stop();
                    eventBusLocal.fireEvent(new RechargementOpportuniteEvent());
                }
            };

            // Récupération du login de l'utilisateur connecté
            selecteurProduit.setLoginUtilisateurConnecte(infosOpportunite.getLoginUtilisateurConnecte());
            // Appel du service
            if (isCreate && selecteurProduit.getIdentifiantDevis() != null) {
                selecteurProduitService.addLigneDevisParSelecteurProduit(selecteurProduit, callback);
            }
            else if (isCreate && selecteurProduit.getIdentifiantDevis() == null) {
                selecteurProduitService.addLigneDevisParSelecteurProduit(selecteurProduit, infosOpportunite, callback);
            }
            else {
                selecteurProduitService.updateLigneDevisParSelecteurProduit(selecteurProduit, callback);
            }
        }
    }

    /**
     * Ajoute un observateur.
     * @param observateur l'observateur à ajouter
     */
    public void ajouterObservateur(Observateur observateur) {
        obsGenerique.ajouterObservateur(observateur);
    }

    /**
     * {@inheritDoc}
     */
    public void nouvelEvenement(Evenement evenement) {
        // demande de chargement de produit dans selecteur
        if (evenement.getNom().equals(ComposantTarificateurConstants.EVENT_CHARGER_PRODUIT_POUR_DEVIS)) {
            // on recupere les objets nécessaires
            final Long idDevis = (Long) evenement.getObjets().get(ComposantTarificateurConstants.EVENT_OBJECT_ID_DEVIS);
            final String typeDevis = (String) evenement.getObjets().get(ComposantTarificateurConstants.EVENT_OBJECT_TYPE_DEVIS);
            final Long idLigneDevis = (Long) evenement.getObjets().get(ComposantTarificateurConstants.EVENT_OBJECT_ID_LIGNE);
            final Long idProduitLong = (Long) evenement.getObjets().get(ComposantTarificateurConstants.EVENT_OBJECT_ID_PRODUIT);
            final Integer idProduit = idProduitLong != null ? Integer.valueOf(idProduitLong.toString()) : null;
            chargerProduitPourDevis(idProduit, idDevis, idLigneDevis, typeDevis);
        }
        // demande de chargement de produit dans selecteur pour un ahderent
        else if (evenement.getNom().equals(ComposantTarificateurConstants.EVENT_CHARGER_PRODUIT_ADHERENT_POUR_DEVIS)) {
            // on recupere les objets nécessaires
            final Long idDevis = (Long) evenement.getObjets().get(ComposantTarificateurConstants.EVENT_OBJECT_ID_DEVIS);
            final String produitAia = (String) evenement.getObjets().get(ComposantTarificateurConstants.EVENT_OBJECT_PRODUIT_AIA);
            final String garantieAia = (String) evenement.getObjets().get(ComposantTarificateurConstants.EVENT_OBJECT_GARANTIE_AIA);
            chargerProduitAdherentPourDevis(idDevis, produitAia, garantieAia);
        }
    }

    /**
     * Charge des nouvelles infos d'opp.
     * @param nouvellesInfosOpportunite infos d'opp
     */
    public void chargerNouvellesInfosOpportunite(InfosOpportuniteModel nouvellesInfosOpportunite) {
        this.infosOpportunite = nouvellesInfosOpportunite;
        identifiantsBeneficiairesInactifs = null;
    }

    /**
     * Récupère la liste des bénéficiaires inactifs, non sélectionnables.
     * @return les bénéficiaires inactifs
     */
    public List<Long> getIdentifiantsBeneficiairesInactifs() {
    	if (identifiantsBeneficiairesInactifs == null) {
    		identifiantsBeneficiairesInactifs = new ArrayList<Long>();
    		for (BeneficiaireModel benef : infosOpportunite.getPersonne().getListeBeneficiaires()) {
    			if (benef.getActif() == null || !benef.getActif()) {
    				identifiantsBeneficiairesInactifs.add(benef.getEidPersonne());
    			}
    		}
    	}
		return identifiantsBeneficiairesInactifs;
	}

    /**
     * Retourne si on est dans le cadre d'une action "générer la proposition".
     * @return true si on est dans le cadre d'une action "générer la proposition".
     */
    public boolean isDevisProduitAdherent() {
    	return isDevisProduitAdherent;
    }

    @Override
    public void show() {
        super.show();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                addPopup();
            }
        });
    }

    private void addPopup() {
        // on en fait une popup minimisable
        deskBar.addPopup(new PopupMinimizable(this, ComposantTarificateur.CONSTANTS.titrePopupSelectionProduit(), btnReduire));
    }

    /**
     * Récupération de eventBusLocal.
     * @return the eventBusLocal
     */
    public HandlerManager getEventBusLocal() {
        return eventBusLocal;
    }
}
