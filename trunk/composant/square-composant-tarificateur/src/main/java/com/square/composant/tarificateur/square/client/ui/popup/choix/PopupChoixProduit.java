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
package com.square.composant.tarificateur.square.client.ui.popup.choix;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.event.AffichagePopupSelectionProduitAdherentEvent;
import com.square.composant.tarificateur.square.client.event.AffichagePopupSelectionProduitEvent;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.GammesProduitsCriteresModel;
import com.square.composant.tarificateur.square.client.model.InfosProduitAdherentModel;
import com.square.composant.tarificateur.square.client.model.IntegerLibelleModel;
import com.square.composant.tarificateur.square.client.model.ListeProduitsAdherentModel;
import com.square.composant.tarificateur.square.client.model.ProduitCriteresModel;
import com.square.composant.tarificateur.square.client.service.ProduitServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;

/**
 * Popup permettant le choix d'un produit.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class PopupChoixProduit extends Popup {

    /**
     * The constants used in this page.
     */
    public interface PageConstants extends Constants {

        /** LARGEUR_POPUP. */
        String LARGEUR_POPUP = "780px";

        /** VIRGULE. */
        String VIRGULE = ", ";

        /** Largeur. */
        String LARGEUR_SLB_GAMME = "110px";

        /** Message.*/
		String DU = "du";

		/** Message.*/
		String AU = "au";

        /**
         * Le texte.
         * @return le texte
         */
        String labelTitreReseaux();

        /**
         * Le texte.
         * @return le texte
         */
        String labelTypeGamme();

        /**
         * Le texte.
         * @return le texte
         */
        String labelCategorie();

        /**
         * Le texte.
         * @return le texte
         */
        String labelGamme();

        /**
         * Le texte.
         * @return le texte
         */
        String labelGarantie();

        /**
         * Le texte.
         * @return le texte
         */
        String labelBoutonSelectionner();

        /**
         * Le texte.
         * @return le texte
         */
        String labelBoutonAnnuler();

        /**
         * Le texte.
         * @return le texte
         */
        String labelBoutonAjoutProduitAdherent();

        /**
         * Le texte.
         * @return le texte
         */
        String titreListeProduitsAdherent();

        /**
         * Le texte.
         * @return le texte
         */
        String titreBlocProduit();
    }

    /**
     * The messages used in this page.
     */
    public interface PageMessages extends Messages {

        /**
         * Retourne le message.
         * @return le message
         */
        String erreurSelectionnerGarantie();

        /**
         * Retourne le message.
         * @return le message
         */
        String erreurSelectionnerGamme();

        /**
         * Retourne le message.
         * @return le message
         */
        String erreurSelectionnerEntite();

        /**
         * Retourne le message.
         * @return le message
         */
        String erreurSelectionnerType();

        /**
         * Retourne le message.
         * @return le message
         */
        String erreurSelectionnerCategorie();

        /**
         * Retourne le message.
         * @return le message
         */
        String ajoutPropositionEnCours();

        /**
         * Retourne le message.
         * @return le message
         */
        String erreurSelectionGammeDevisSantePrevoyance();

        /**
         * Retourne le message.
         * @return le message
         */
        String erreurSelectionGammeDevisCNP();

        /**
         * Retourne le message.
         * @return le message
         */
        String erreurDevisMalInitialise();

        /**
         * Retourne le message.
         * @return le message
         */
        String erreurAjoutNonMultiProposition();
    }

    private PageConstants pageConstants;

    private PageMessages pageMessages;

    /** ListBox des réseaux de gammes. */
    private DecoratedSuggestListBoxSingle<IntegerLibelleModel> slbReseauxGammes;

    /** ListBox des types de gammess. */
    private DecoratedSuggestListBoxSingle<IntegerLibelleModel> slbTypesGammes;

    /** ListBox des catégories de gammes. */
    private DecoratedSuggestListBoxSingle<IntegerLibelleModel> slbCategoriesGammes;

    /** Libellé de la gamme CNP. */
    private Label libelleGammeCNP;

    /** SuggestBox des gammes. */
    private DecoratedSuggestListBoxSingle<IntegerLibelleModel> slbGammes;

    /** SuggestBox des produits. */
    private DecoratedSuggestListBoxSingle<IntegerLibelleModel> slbProduits;

    /** Bouton de sélection. */
    private DecoratedButton btnSelectionner;

    /** Bouton d'annulation. */
    private DecoratedButton btnAnnuler;

    /** Identifiant du devis associé. */
    private Long idDevis;

    /** Type du devis (CNP, Santé/Prévoyance, neutre). */
    private String typeDevis;

    /** Le idPersonne du prospect. */
    private Long idPersonne;

    /** Panel de la liste des produits d'un adhérent. */
    private CaptionPanel captionPanelListeProduitsAdherent;

    /** Service Produit. */
    private ProduitServiceGwtAsync produitService;

    /** Service Tarificateur. */
    private TarificateurServiceGwtAsync tarificateurService;

    private ConstantesModel constantesMapping;

    private HandlerManager eventBus;
    private HandlerManager eventBusLocal;

    /**
     * Constructeur.
     * @param eventBus eventBus
     * @param constantesMapping constantes de mapping nécessaires au composant.
     * @param tarificateurService tarificateurService
     * @param produitService produitService
     */
    public PopupChoixProduit(final HandlerManager eventBus, ConstantesModel constantesMapping, TarificateurServiceGwtAsync tarificateurService,
        ProduitServiceGwtAsync produitService) {
        super(ComposantTarificateur.CONSTANTS.titrePopupChoixProduit(), false, true, true);
        this.addStyleName(ComposantTarificateur.RESOURCES.css().choixProduit());

        // Create the page constants
        pageConstants = (PageConstants) GWT.create(PageConstants.class);
        // Create the page messages
        pageMessages = (PageMessages) GWT.create(PageMessages.class);

        this.constantesMapping = constantesMapping;
        this.produitService = produitService;
        this.tarificateurService = tarificateurService;
        this.eventBus = eventBus;
        this.eventBusLocal  = new HandlerManager(this);

        // Initialisation des composants
        creerSuggestListBox();
        initComposants();
    }

    /** Initialisation des composants. */
    private void initComposants() {
        final Label lbTitreReseaux = new Label(pageConstants.labelTitreReseaux(), false);
        final Label lbType = new Label(pageConstants.labelTypeGamme(), false);
        final Label lbCategorie = new Label(pageConstants.labelCategorie(), false);
        final Label lbTitreGamme = new Label(pageConstants.labelGamme(), false);
        final Label lbTitreProduit = new Label(pageConstants.labelGarantie(), false);

        final FlexTable panneauCriteresGamme = new FlexTable();
        panneauCriteresGamme.setWidth(ComposantTarificateurConstants.POURCENT_100);
        panneauCriteresGamme.addStyleName(ComposantTarificateur.RESOURCES.css().choixProduitBloc());
        panneauCriteresGamme.setCellSpacing(3);
        panneauCriteresGamme.setWidget(0, 0, lbTitreReseaux);
        panneauCriteresGamme.setWidget(0, 1, slbReseauxGammes);
        panneauCriteresGamme.setWidget(0, 3, lbType);
        panneauCriteresGamme.setWidget(0, 4, slbTypesGammes);
        panneauCriteresGamme.setWidget(0, 6, lbCategorie);
        panneauCriteresGamme.setWidget(0, 7, slbCategoriesGammes);

        final FlexTable panneauGammeProduit = new FlexTable();
        panneauGammeProduit.setWidth(ComposantTarificateurConstants.POURCENT_100);
        panneauGammeProduit.addStyleName(ComposantTarificateur.RESOURCES.css().choixProduitBloc());
        panneauGammeProduit.setCellSpacing(3);
        panneauGammeProduit.setWidget(0, 0, lbTitreGamme);
        panneauGammeProduit.setWidget(0, 1, slbGammes);
        panneauGammeProduit.setWidget(0, 2, libelleGammeCNP);
        panneauGammeProduit.setWidget(0, 4, lbTitreProduit);
        panneauGammeProduit.setWidget(0, 5, slbProduits);

        // Panneau des boutons
        btnSelectionner = new DecoratedButton(pageConstants.labelBoutonSelectionner());
        btnSelectionner.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Ouverture de la popup de sélection des produits pour le produit choisi
                afficherPopupSelectionProduits();
            }
        });
        btnAnnuler = new DecoratedButton(pageConstants.labelBoutonAnnuler());
        btnAnnuler.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                init();
                hide();
            }
        });
        final HorizontalPanel panelBoutons = new HorizontalPanel();
        panelBoutons.setSpacing(5);
        panelBoutons.add(btnSelectionner);
        panelBoutons.add(btnAnnuler);

        final VerticalPanel conteneurPanelProduit = new VerticalPanel();
        conteneurPanelProduit.setWidth(ComposantTarificateurConstants.POURCENT_100);
        conteneurPanelProduit.add(panneauCriteresGamme);
        conteneurPanelProduit.add(panneauGammeProduit);
        final CaptionPanel captionPanelProduit = new CaptionPanel(pageConstants.titreBlocProduit());
        captionPanelProduit.add(conteneurPanelProduit);

        // Liste des produits d'un adhérent
        captionPanelListeProduitsAdherent = new CaptionPanel(pageConstants.titreListeProduitsAdherent());
        captionPanelListeProduitsAdherent.setVisible(false);

        final FocusPanel focusPanel = new FocusPanel(captionPanelProduit);
        focusPanel.setWidth(ComposantTarificateurConstants.POURCENT_100);
        focusPanel.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    afficherPopupSelectionProduits();
                } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    init();
                    hide();
                }
            }
        });

        final VerticalPanel panelConteneur = new VerticalPanel();
        panelConteneur.setWidth(ComposantTarificateurConstants.POURCENT_100);
        panelConteneur.setSpacing(10);
        panelConteneur.add(focusPanel);
        panelConteneur.add(panelBoutons);
        panelConteneur.setCellHorizontalAlignment(panelBoutons, HasHorizontalAlignment.ALIGN_CENTER);
        panelConteneur.add(captionPanelListeProduitsAdherent);
        panelConteneur.setWidth(PageConstants.LARGEUR_POPUP);
        this.setWidget(panelConteneur, 0);
    }

    private void creerSuggestListBox() {
        final SuggestListBoxSingleProperties<IntegerLibelleModel> slbIntegerLibelleProperties = new SuggestListBoxSingleProperties<IntegerLibelleModel>() {
            @Override
            public String getSelectSuggestRenderer(IntegerLibelleModel row) {
                return row == null ? "" : row.getLibelle();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(IntegerLibelleModel row) {
                return new String[] {row == null ? "" : row.getLibelle()};
            }
        };

        slbReseauxGammes = new DecoratedSuggestListBoxSingle<IntegerLibelleModel>(slbIntegerLibelleProperties);
        slbTypesGammes = new DecoratedSuggestListBoxSingle<IntegerLibelleModel>(slbIntegerLibelleProperties);
        slbCategoriesGammes = new DecoratedSuggestListBoxSingle<IntegerLibelleModel>(slbIntegerLibelleProperties);
        libelleGammeCNP = new Label();
        libelleGammeCNP.setVisible(false);
        slbGammes = new DecoratedSuggestListBoxSingle<IntegerLibelleModel>(slbIntegerLibelleProperties);
        slbProduits = new DecoratedSuggestListBoxSingle<IntegerLibelleModel>(slbIntegerLibelleProperties);

        slbReseauxGammes.setWidth(PageConstants.LARGEUR_SLB_GAMME);
        slbTypesGammes.setWidth(PageConstants.LARGEUR_SLB_GAMME);
        slbCategoriesGammes.setWidth(PageConstants.LARGEUR_SLB_GAMME);

        slbReseauxGammes.addSuggestHandler(new SuggestListBoxSuggestEventHandler<IntegerLibelleModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IntegerLibelleModel> event) {
                produitService.getListeReseauxGamme(event.getSuggest(), event.getCallBack());
            }
        });
        slbTypesGammes.addSuggestHandler(new SuggestListBoxSuggestEventHandler<IntegerLibelleModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IntegerLibelleModel> event) {
                produitService.getListeVetustesGamme(event.getSuggest(), event.getCallBack());
            }
        });
        slbCategoriesGammes.addSuggestHandler(new SuggestListBoxSuggestEventHandler<IntegerLibelleModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IntegerLibelleModel> event) {
                produitService.getListeCategoriesGamme(event.getSuggest(), event.getCallBack());
            }
        });
        slbGammes.addSuggestHandler(new SuggestListBoxSuggestEventHandler<IntegerLibelleModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IntegerLibelleModel> event) {
                final GammesProduitsCriteresModel criteres = new GammesProduitsCriteresModel();
                if (slbReseauxGammes.getValue() != null) {
                    criteres.setIdReseauGamme(Integer.valueOf(slbReseauxGammes.getValue().getIdentifiant().intValue()));
                    if (slbTypesGammes.getValue() != null) {
                        criteres.setIdVetusteGamme(Integer.valueOf(slbTypesGammes.getValue().getIdentifiant().intValue()));
                        if (slbCategoriesGammes.getValue() != null) {
                            criteres.setIdCategorieGamme(Integer.valueOf(slbCategoriesGammes.getValue().getIdentifiant().intValue()));
                            criteres.setTypeDevis(typeDevis);
                            criteres.setLibelleGamme(event.getSuggest());
                            produitService.getListeGammesProduits(criteres, event.getCallBack());
                        } else {
                            ErrorPopup.afficher(new ErrorPopupConfiguration(pageMessages.erreurSelectionnerCategorie()));
                            slbProduits.setValue(null);
                        }
                    } else {
                        ErrorPopup.afficher(new ErrorPopupConfiguration(pageMessages.erreurSelectionnerType()));
                        slbProduits.setValue(null);
                    }
                } else {
                    ErrorPopup.afficher(new ErrorPopupConfiguration(pageMessages.erreurSelectionnerEntite()));
                    slbProduits.setValue(null);
                }
            }
        });
        slbProduits.addSuggestHandler(new SuggestListBoxSuggestEventHandler<IntegerLibelleModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IntegerLibelleModel> event) {
                final ProduitCriteresModel criteres = new ProduitCriteresModel();
                if (slbGammes.getValue() != null) {
                    criteres.setIdentifiantGammeProduit(slbGammes.getValue().getIdentifiant());
                    criteres.setIdDevis(idDevis);
                    criteres.setLibelleProduit(event.getSuggest());
                    criteres.setActif(true);
                    produitService.getListeProduits(criteres, event.getCallBack());
                } else {
                    ErrorPopup.afficher(new ErrorPopupConfiguration(pageMessages.erreurSelectionnerGamme()));
                    slbProduits.setValue(null);
                }
            }
        });

        final ValueChangeHandler<IntegerLibelleModel> gammeValueChangeHandler = new ValueChangeHandler<IntegerLibelleModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<IntegerLibelleModel> event) {
                slbGammes.setValue(null);
                slbProduits.setValue(null);
            }
        };
        slbReseauxGammes.addValueChangeHandler(gammeValueChangeHandler);
        slbTypesGammes.addValueChangeHandler(gammeValueChangeHandler);
        slbCategoriesGammes.addValueChangeHandler(gammeValueChangeHandler);

        slbGammes.addValueChangeHandler(new ValueChangeHandler<IntegerLibelleModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<IntegerLibelleModel> event) {
                // Aucun produit sélectionné
                slbProduits.setValue(null);
            }
        });
    }

    /**
     * Affichage de la popup du choix de produit pour un devis.
     * @param idDevisAssocie l'identifiant du devis
     * @param typeDevisAssocie type du devis
     * @param personneId personneId
     */
    public void afficherChoixProduitPourDevis(Long idDevisAssocie, String typeDevisAssocie, Long personneId) {
        // On masque la liste des produits des adhérents
        captionPanelListeProduitsAdherent.setVisible(false);

        // Récupération de l'identifiant du devis
        this.idDevis = idDevisAssocie;
        this.typeDevis = typeDevisAssocie;
        this.idPersonne = personneId;

        // Réinitialisation de la popup
        init();

        // Affichage de la liste des produits de l'adhérent si nécessaire
        afficherListeProduitsAdherent();

        // Affichage de la popup
        this.show();

        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                slbGammes.setFocus(true);
            }
        });
    }

    /** Affiche la popup de sélection des produits. */
    private void afficherPopupSelectionProduits() {
        // Si aucun produit choisi
        if (slbProduits.getValue() == null) {
            ErrorPopup.afficher(new ErrorPopupConfiguration(pageMessages.erreurSelectionnerGarantie()));
        } else {
            hide();
            // envoie de l'evenement de demande de chargement de produit pour devis
            eventBusLocal.fireEvent(new AffichagePopupSelectionProduitEvent(slbProduits.getValue().getIdentifiant(), idDevis, typeDevis, null));
        }
    }

    /**
     * Génère une proposition de devis pour un adhérent.
     * @param produitAia produit AIA.
     * @param garantieAia garantie AIA.
     */
    private void afficherPopupSelectionProduitsAdherent(String produitAia, String garantieAia) {
        hide();
        // envoie de l'evenement de demande de chargement de produit pour devis
        eventBusLocal.fireEvent(new AffichagePopupSelectionProduitAdherentEvent(produitAia, garantieAia, idDevis));
    }

    /** Réinitialisation de la popup (remise à zéro). */
    private void init() {
        // Entite sur Smatis France
        slbReseauxGammes.setValue(constantesMapping.getReseauGammeDefaut());
        // Type gamme sur Ouvert à la vente
        slbTypesGammes.setValue(constantesMapping.getTypeGammeDefaut());
        // Catégorie sur Santé
        slbCategoriesGammes.setValue(constantesMapping.getCategorieGammeDefaut());
        slbGammes.setValue(null);
        slbProduits.setValue(null);
    }

    /** Chargement de la liste des produits de l'adhérent. */
    private void afficherListeProduitsAdherent() {
        // La liste n'est affichée que si le prospect est un adhérent et que ce n'est pas un devis CNP

        if (idPersonne != null) {
            // Chargement de la liste des produits de l'adhérent
            final AsyncCallback<List<ListeProduitsAdherentModel>> callback = new AsyncCallback<List<ListeProduitsAdherentModel>>() {
                public void onFailure(Throwable caught) {
                    captionPanelListeProduitsAdherent.setVisible(false);
                    ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
                }

                public void onSuccess(List<ListeProduitsAdherentModel> listeProduitsContratsAdherentGwt) {
                    if (listeProduitsContratsAdherentGwt == null || listeProduitsContratsAdherentGwt.isEmpty()) {
                        captionPanelListeProduitsAdherent.setVisible(false);
                    } else {
                        // Produit principal
                        InfosProduitAdherentModel produitPrincipal;

                        // On nettoie le panel
                        captionPanelListeProduitsAdherent.clear();

                        final VerticalPanel panelListeProduitsAdherent = new VerticalPanel();
                        panelListeProduitsAdherent.setWidth(ComposantTarificateurConstants.POURCENT_100);
                        panelListeProduitsAdherent.setSpacing(5);
                        captionPanelListeProduitsAdherent.add(panelListeProduitsAdherent);

                        for (ListeProduitsAdherentModel listeProduitsParcouru : listeProduitsContratsAdherentGwt) {
                            produitPrincipal = listeProduitsParcouru.getProduitPrincipal();
                            int i = 0;

                            // Label de la liste des produits d'un adhérent.
                            final HTML labelListeProduitsAdherent = new HTML();

                            // Bouton de génération d'une proposition pour un adhérent.
                            final DecoratedButton btnGenererProposition = new DecoratedButton(pageConstants.labelBoutonAjoutProduitAdherent());

                            final HorizontalPanel hpConteneurProduits = new HorizontalPanel();
                            hpConteneurProduits.setWidth(ComposantTarificateurConstants.POURCENT_100);
                            hpConteneurProduits.add(labelListeProduitsAdherent);
                            hpConteneurProduits.add(btnGenererProposition);
                            hpConteneurProduits.setCellHorizontalAlignment(labelListeProduitsAdherent, HasHorizontalAlignment.ALIGN_LEFT);
                            hpConteneurProduits.setCellHorizontalAlignment(btnGenererProposition, HasHorizontalAlignment.ALIGN_RIGHT);
                            hpConteneurProduits.setCellVerticalAlignment(labelListeProduitsAdherent, HasVerticalAlignment.ALIGN_MIDDLE);
                            hpConteneurProduits.setCellVerticalAlignment(btnGenererProposition, HasVerticalAlignment.ALIGN_MIDDLE);
                            hpConteneurProduits.setCellWidth(btnGenererProposition, "160px");
                            panelListeProduitsAdherent.add(hpConteneurProduits);

                            // Ecriture de la liste des produits de l'adhérent
                            final StringBuffer htmlListeProduitsAdherent = new StringBuffer();
                            if (produitPrincipal != null) {
                                // On affiche : libelleProduit ( dateAdhesion -> dateResiliation )
                                htmlListeProduitsAdherent.append("<b>");
                                htmlListeProduitsAdherent.append(produitPrincipal.getLibelle());
                                htmlListeProduitsAdherent.append("</b>");
                                htmlListeProduitsAdherent.append(PageConstants.DU + " " + produitPrincipal.getDateAdhesion() + " " + PageConstants.AU);
                                htmlListeProduitsAdherent.append(produitPrincipal.getDateResiliation() != null ? produitPrincipal.getDateResiliation() : "...");
                                final String garantieAia = produitPrincipal.getGarantieAia();
                                final String produitAia = produitPrincipal.getProduitAia();
                                btnGenererProposition.addClickHandler(new ClickHandler() {
                                    @Override
                                    public void onClick(ClickEvent event) {
                                        afficherPopupSelectionProduitsAdherent(produitAia, garantieAia);
                                    }
                                });
                                i++;
                            }
                            for (InfosProduitAdherentModel infosProduitLieParcouru : listeProduitsParcouru.getListeProduitsLies()) {
                                if (i == 1) {
                                    htmlListeProduitsAdherent.append(ComposantTarificateurConstants.SAUT_LIGNE);
                                } else {
                                    htmlListeProduitsAdherent.append(PageConstants.VIRGULE);
                                }
                                htmlListeProduitsAdherent.append(infosProduitLieParcouru.getLibelle());
                                i++;
                            }
                            labelListeProduitsAdherent.setHTML(htmlListeProduitsAdherent.toString());
                            // Affichage de la liste
                            captionPanelListeProduitsAdherent.setVisible(true);
                            // si pas de produit principal santé, on cache le bouton générerProposition
                            btnGenererProposition.setVisible(listeProduitsParcouru.getProduitPrincipal() != null);
                        }
                    }
                }
            };
            // Appel du service
            tarificateurService.getListeProduitsAdherent(idPersonne, callback);
        } else {
            // Liste des produits des adhérents non visible
            captionPanelListeProduitsAdherent.clear();
            captionPanelListeProduitsAdherent.setVisible(false);
        }
    }

    /**
     * Récupération de eventBusLocal.
     * @return the eventBusLocal
     */
    public HandlerManager getEventBusLocal() {
        return eventBusLocal;
    }

}
