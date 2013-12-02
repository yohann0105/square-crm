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
package com.square.client.gwt.client.presenter.personne;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.confirm.ConfirmPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.PopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.RafraichirCoordonneesEvent;
import com.square.client.gwt.client.exception.ConfirmationImpacterFamilleExceptionGwt;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.AdresseCreationModel;
import com.square.client.gwt.client.model.AdresseModel;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCritereCodePostalCommuneModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheDepartementModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.IdentifiantLibelleBooleanModel;
import com.square.client.gwt.client.model.IdentifiantLibelleDepartementCodeModel;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.personne.coordonnees.AdresseCreationViewImpl;
import com.square.client.gwt.client.view.personne.coordonnees.PopupCoordonneesAdresseViewImplConstants;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEventHandler;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;

/**
 * Presenter de la popup de coordonnées pour ajouter une nouvelle adresse.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupCoordonneesAdressePresenter extends Presenter {

    /** Vue associée au presenter. */
    private PopupCoordonneesAdresseView view;

    /** Service des personnes. */
    private PersonneServiceGwtAsync personneRpcService;

    /** Service des dimensions. */
    private DimensionServiceGwtAsync dimensionRpcService;

    /** Constantes. */
    private ConstantesModel constantes;

    /** Constantes du presenter. */
    private PopupCoordonneesAdressePresenterConstants presenterConstants;

    /** Idenitifiant de la personne en cours. */
    private Long idPersonne;

    /** Booléen indiquant si la personne a déjà une adresse principale. */
    private boolean hasAdressePrincipale;

    private Boolean impacterFamille;

    private DeskBar deskBar;

    private HandlerRegistration deskBarRegistration;

    /** Service de gestion d'aide. */
    private AideServiceGwtAsync aideService;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènement.
     * @param personneRpcService service rpc pour les personnes.
     * @param dimensionRpcService service rpc pour les dimensions.
     * @param view la vue associée au presenter.
     * @param constantes les constantes.
     * @param idPersonne l'identifiant de la personne.
     * @param hasAdressePrincipale booléen indiquant si la personne a déjà une adresse principale.
     * @param deskBar deskBar
     * @param aideService service d'aide.
     */
    public PopupCoordonneesAdressePresenter(HandlerManager eventBus, PersonneServiceGwtAsync personneRpcService, DimensionServiceGwtAsync dimensionRpcService,
        PopupCoordonneesAdresseView view, ConstantesModel constantes, Long idPersonne, boolean hasAdressePrincipale, DeskBar deskBar,
        AideServiceGwtAsync aideService) {
        super(eventBus);
        this.personneRpcService = personneRpcService;
        this.dimensionRpcService = dimensionRpcService;
        this.view = view;
        this.constantes = constantes;
        this.idPersonne = idPersonne;
        this.hasAdressePrincipale = hasAdressePrincipale;
        this.impacterFamille = null;
        this.presenterConstants = GWT.create(PopupCoordonneesAdressePresenterConstants.class);
        this.deskBar = deskBar;
        this.aideService = aideService;
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onShow(HasWidgets container) {
        // Construction de la liste des identifiants des composants d'aide de la vue associée à ce presenter
        final List<Long> listeIdsComposantsAides = new ArrayList<Long>();
        for (final AideComposant composant : view.getBlocAdresse().getListAideCompsant()) {
            listeIdsComposantsAides.add(composant.getId());
        }

        // Recherche des composants d'aide existant pour les rendre visible
        aideService.rechercherIdsComposantsAides(listeIdsComposantsAides, new AsyncCallback<List<Long>>() {
            @Override
            public void onSuccess(List<Long> result) {
                if (result != null) {
                    for (final AideComposant composant : view.getBlocAdresse().getListAideCompsant()) {
                        composant.setVisible(result.contains(composant.getId()));
                    }
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });

        view.afficherPopup();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                view.getBlocAdresse().getSlbType().setFocus(true);
                // on ajoute la popup à la deskBar
                deskBar.addPopup(view.getMinimizablePopup());
            }
        });
    }

    /**
     * Permet d'afficher la popup.
     * @param hasAdressePrincipale indique s'il une adresse principale est présente.
     */
    public void showPopup(boolean hasAdressePrincipale) {
        this.hasAdressePrincipale = hasAdressePrincipale;
        view.afficherPopup();
    }

    @Override
    public void onBind() {
        // Gestion des touches de la popup

        for (HasUpdateAideEventHandler handler : view.getBlocAdresse().getListeUpdateEventHandler()) {
            handler.addUpdateAideEventHandler(new UpdateAideEventHandler() {

                @Override
                public void onUpdateAide(UpdateAideEvent event) {
                    aideService.creerOuModifierAide(event.getAide(), event.getCallBack());

                }
            });

        }
        for (HasDemandeAideEventHandler handler : view.getBlocAdresse().getListeDemandeEventHandler()) {
            handler.addDemandeAideEventHandler(new DemandeAideEventHandler() {
                @Override
                public void onDemandeAide(DemandeAideEvent event) {
                    aideService.rechercherAide(event.getIdComposant(), event.getCallBack());
                }
            });
        }

        view.getAllKeyHandlersFocusPanel().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                final int keyCode = event.getNativeKeyCode();
                if (keyCode == KeyCodes.KEY_ENTER) {
                    final AdresseCreationViewImpl blocAdresse = view.getBlocAdresse();
                    final AdresseModel adresseModel = recupererNouvelleAdresse(blocAdresse);
                    ajouterAdresse(adresseModel);
                }
                else if (keyCode == KeyCodes.KEY_ESCAPE) {
                    view.cacherPopup();
                }
            }
        });
        view.getBtnEnregistrer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final AdresseCreationViewImpl blocAdresse = view.getBlocAdresse();
                final AdresseModel adresseModel = recupererNouvelleAdresse(blocAdresse);
                ajouterAdresse(adresseModel);
            }
        });

        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.cacherPopup();
            }
        });
        view.getBlocAdresse().getSlbType().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionRpcService.rechercherTypeAdresseParCriteres(criteres, event.getCallBack());
            }
        });
        view.getBlocAdresse().getSlbType().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                if (hasAdressePrincipale && constantes.getIdNatureAdressePrincipale().equals(event.getValue().getIdentifiant())) {
                    view.getBlocAdresse().construireActionAdresse();
                }
                else {
                    view.getBlocAdresse().supprimerActionAdresse();
                }
            }
        });

        view.getBlocAdresse().getSlbNatureVoie().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionRpcService.rechercherNatureVoieParCriteres(criteres, event.getCallBack());

            }
        });
        view.getBlocAdresse().getSlbCodePostal().addSuggestHandler(new SuggestListBoxSuggestEventHandler<CodePostalCommuneModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<CodePostalCommuneModel> event) {
                final DimensionCritereCodePostalCommuneModel criteres = new DimensionCritereCodePostalCommuneModel();
                criteres.setLibelleCodePostal(event.getSuggest());
                criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX_CP);
                dimensionRpcService.rechercherCodesPostauxCommunes(criteres, event.getCallBack());
            }
        });
        view.getBlocAdresse().getSlbCodePostalValue().addValueChangeHandler(new ValueChangeHandler<CodePostalCommuneModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<CodePostalCommuneModel> event) {
                if (event.getValue() != null) {
                    view.getBlocAdresse().getLValueVille().setText(event.getValue().getLibelleAcheminement());
                    recupererDepartement(view.getBlocAdresse(), event.getValue().getCommune().getIdentifiant());
                }
                else {
                    view.getBlocAdresse().getLValueVille().setText("");
                    view.getBlocAdresse().getLValueDepartement().setText("");
                }
            }
        });
        view.getBlocAdresse().getSlbPays().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleBooleanModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleBooleanModel> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                dimensionRpcService.rechercherPaysParCriteres(criteres, event.getCallBack());
            }
        });
        view.getBlocAdresse().getSlbPays().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleBooleanModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleBooleanModel> event) {
                desactiverCodePostal(view.getBlocAdresse());
            }
        });

        deskBarRegistration = deskBar.getEventBus().addHandler(EnableMinimizeWidgetEvent.TYPE, new EnableMinimizeWidgetEventHandler() {
            @Override
            public void enableMinimizeWidget(EnableMinimizeWidgetEvent event) {
                view.activerBoutonReduire(event.isEnabled());
            }
        });
    }

    /**
     * Récupère la nouvelle adresse.
     * @param blocAdresse le bloc d'adresse.
     * @return la nouvelle adresse.
     */
    private AdresseModel recupererNouvelleAdresse(AdresseCreationViewImpl blocAdresse) {
        final AdresseModel adresse = new AdresseModel();

        adresse.setIdentifiant(blocAdresse.getIdAdresse());
        adresse.setNature(blocAdresse.getSlbType().getValue());
        adresse.setDateFin(null);
        adresse.setNumVoie(blocAdresse.getTbNumeroVoie().getValue());
        adresse.setTypeVoie(blocAdresse.getSlbNatureVoie().getValue());
        adresse.setComplementNom(blocAdresse.getTbComplementNom().getValue());
        adresse.setNomVoie(blocAdresse.getTbAdresse().getValue());
        adresse.setComplementAdresse(blocAdresse.getTbComplementAdresse().getValue());
        adresse.setAutresComplements(blocAdresse.getTbAutresComplements().getValue());
        final CodePostalCommuneModel codePostalCommune = blocAdresse.getSlbCodePostalValue().getValue();
        if (codePostalCommune != null) {
            adresse.setCodePostalCommune(codePostalCommune);
        }
        if (blocAdresse.getIdDepartement() != null) {
            adresse.setDepartement(new IdentifiantLibelleGwt(blocAdresse.getIdDepartement(), blocAdresse.getLValueDepartement().getText()));
        }
        adresse.setPays(blocAdresse.getSlbPays().getValue());
        adresse.setCodePostalEtranger(blocAdresse.getTbCodePostalEtranger().getValue());

        adresse.setChoixPasserEnSecondaire(blocAdresse.getChoixPasserEnSecondaire().booleanValue());

        return adresse;
    }

    /**
     * Récupère le département de la commune.
     */
    private void recupererDepartement(final AdresseCreationView blocAdresse, Long idCommune) {
        final DimensionCritereRechercheDepartementModel criteres = new DimensionCritereRechercheDepartementModel();
        criteres.setIdCommune(idCommune);
        dimensionRpcService.rechercherDepartementParCriteres(criteres, new AsyncCallback<List<IdentifiantLibelleDepartementCodeModel>>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(List<IdentifiantLibelleDepartementCodeModel> result) {
                if (result != null) {
                    final IdentifiantLibelleDepartementCodeModel departement = result.get(0);
                    blocAdresse.setIdDepartement(departement.getId());
                    blocAdresse.getLValueDepartement().setText(departement.getLibelle());
                }
                view.onRpcServiceSuccess();
            }
        });
    }

    /**
     * Désactive le code postal.
     */
    private void desactiverCodePostal(AdresseCreationView blocAdresse) {
        boolean activer = false;
        if (blocAdresse.getSlbPays().getValue() != null && blocAdresse.getSlbPays().getValue().getIdentifiant() != null) {
            activer = blocAdresse.getSlbPays().getValue().getIdentifiant().equals(constantes.getIdPaysFrance());
        }
        if (!activer) {
            blocAdresse.nettoyerCodePostal();
            blocAdresse.getLValueVille().setText("");
            blocAdresse.getLValueDepartement().setText("");
        }
        else {
            blocAdresse.getTbCodePostalEtranger().setValue("");
            blocAdresse.getTbCommuneEtranger().setValue("");
        }
        blocAdresse.afficheBlocCoordonneesFrance(activer);
    }

    /**
     * Ajoute une nouvelle adresse.
     * @param nouvelleAdresse la nouvelle adresse.
     */
    private void ajouterAdresse(final AdresseModel nouvelleAdresse) {
        view.afficherLoadingPopup(new LoadingPopupConfiguration(presenterConstants.ajoutNouvelleAdresseEnCours()));
        personneRpcService.ajouterNouvelleAdresse(idPersonne, nouvelleAdresse, impacterFamille, new AsyncCallback<AdresseCreationModel>() {
            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(((ControleIntegriteExceptionGwt) caught).getRapport()));
                    view.masquerLoadingPopup();
                    view.getBlocAdresse().getSlbType().setFocus(true);
                }
                else if (caught instanceof ConfirmationImpacterFamilleExceptionGwt) {
                    view.masquerLoadingPopup();
                    final PopupConfiguration config = new PopupConfiguration(caught.getMessage());
                    config.setHtmlCaptionBtnOk(view.getViewConstants().libelleOui());
                    config.setHtmlCaptionBtnCancel(view.getViewConstants().libelleNon());
                    config.setCallback(new PopupCallback() {
                        @Override
                        public void onResult(boolean result) {
                            impacterFamille = result;
                            // On relance l'ajout d'adresse
                            ajouterAdresse(nouvelleAdresse);
                        }
                    });
                    ConfirmPopup.afficher(config);
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }

            @Override
            public void onSuccess(AdresseCreationModel result) {
                // On réinitialise le flag
                impacterFamille = null;
                view.cacherPopup();

                String messageNotification = presenterConstants.notificationAdresseAjoutee();
                // Si la nature de la personne a changé, on affiche un message l'indiquant
                if (result.getHasNaturePersonneChanged() != null && result.getHasNaturePersonneChanged()) {
                    messageNotification =
                        messageNotification + AppControllerConstants.SAUT_LIGNE + presenterConstants.debutNotificiationNaturePersonneModifiee() + " "
                            + result.getAncienneNaturePersonne() + " " + presenterConstants.finNotificiationNaturePersonneModifiee() + " "
                            + result.getNouvelleNaturePersonne();
                }
                final PopupInfoConfiguration config = new PopupInfoConfiguration(messageNotification, AppControllerConstants.NOTIFICATION_TIME_OUT);
                new DecoratedInfoPopup(config).show();

                // Publication d'un évènement pour mettre à jour les coordonnées de la personne
                fireEventLocalBus(new RafraichirCoordonneesEvent());
                view.onRpcServiceSuccess();
            }
        });
    }

    /**
     * Interface de la vue associée au presenter.
     * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
     */
    public interface PopupCoordonneesAdresseView extends View {

        /**
         * Affiche un message de chargement.
         * @param config la config
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Interface accesseurs constantes de la vue.
         * @return l'interface
         */
        PopupCoordonneesAdresseViewImplConstants getViewConstants();

        /** Masque un message de chargement. */
        void masquerLoadingPopup();

        /**
         * Retourne le widget.
         * @return le widget
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Renvoie le bloc adresse.
         * @return bloc adresse
         */
        AdresseCreationViewImpl getBlocAdresse();

        /**
         * Renvoie le bouton d'enregistrement.
         * @return bEnregistrer
         */
        HasClickHandlers getBtnEnregistrer();

        /**
         * Renvoie le bouton d'annulation.
         * @return bAnnuler
         */
        HasClickHandlers getBtnAnnuler();

        /** Affiche la popup. */
        void afficherPopup();

        /** Cache la popup. */
        void cacherPopup();

        /**
         * Methode appelé lorsque un service Rpc s'est déroulé correctement.
         */
        void onRpcServiceSuccess();

        /**
         * Methode appelé lorsque un servie Rpc ne s'est pas déroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Accesseur AllKeyHandlers du focusPanel.
         * @return le handler
         */
        HasAllKeyHandlers getAllKeyHandlersFocusPanel();

        /**
         * Active/Désactive le bouton réduire.
         * @param enabled flag
         */
        void activerBoutonReduire(boolean enabled);

        /**
         * Récupère la popup minimisable.
         * @return la popup minimisable
         */
        IsMinimizable getMinimizablePopup();
    }

    /**
     * Interface de la vue de création d'adresse.
     * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
     */
    public interface AdresseCreationView extends View {

        /**
         * Récupère le type de l'adresse.
         * @return le type.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbType();

        /**
         * Récupère la nature de voie de l'adresse.
         * @return la nature de voie de l'adresse.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbNatureVoie();

        /**
         * Récupère le code postal de l'adresse.
         * @return le code postal.
         */
        HasSuggestListBoxHandler<CodePostalCommuneModel> getSlbCodePostal();

        /**
         * Récupère la valeur du code postal.
         * @return la valeur du code postal.
         */
        HasValue<CodePostalCommuneModel> getSlbCodePostalValue();

        /**
         * Récupère le pays de l'adresse.
         * @return le pays.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel> getSlbPays();

        /**
         * Récupère la valeur de la ville de l'adresse.
         * @return la valeur de la ville.
         */
        HasText getLValueVille();

        /**
         * Récupère la valur du département de l'adresse.
         * @return la valeur du département.
         */
        HasText getLValueDepartement();

        /**
         * Récupère l'identifiant de l'adresse.
         * @return l'identifiant de l'adresse.
         */
        Long getIdAdresse();

        /**
         * Récupère la valeur du numéro de voie.
         * @return la valeur du numéro de voie.
         */
        HasValue<String> getTbNumeroVoie();

        /**
         * Récupère la valeur du complément de nom.
         * @return la valeur du complément de nom.
         */
        HasValue<String> getTbComplementNom();

        /**
         * Récupère la valeur de l'adresse.
         * @return la valeur de l'adresse.
         */
        HasValue<String> getTbAdresse();

        /**
         * Récupère la valeur des autres compléments.
         * @return la valeur des autres compléments.
         */
        HasValue<String> getTbAutresComplements();

        /**
         * Récupère la valeur du complément d'adresse.
         * @return la valeur du complément d'adresse.
         */
        HasValue<String> getTbComplementAdresse();

        /**
         * Récupère l'identifiant du département de l'adresse.
         * @return l'identifiant du département.
         */
        Long getIdDepartement();

        /**
         * Modifie l'identifiant du département de l'adresse.
         * @param idDepartement l'identifiant du département.
         */
        void setIdDepartement(Long idDepartement);

        /**
         * Récupère la valeur du code postal étranger de l'adresse.
         * @return la valeur du code postal étranger.
         */
        HasValue<String> getTbCodePostalEtranger();

        /**
         * Récupère le choix de passer l'adresse en secondaire ou non.
         * @return le choix.
         */
        Boolean getChoixPasserEnSecondaire();

        /** Construit le panel d'action sur l'adresse. */
        void construireActionAdresse();

        /** Supprime le panel d'action sur l'adresse. */
        void supprimerActionAdresse();

        /**
         * Affiche ou non le bloc des coordonnées françaises.
         * @param affiche true si on l'affiche, false si non.
         */
        void afficheBlocCoordonneesFrance(Boolean affiche);

        /** Nettoye la valeur du code postal. */
        void nettoyerCodePostal();

        /**
         * Récupère la valeur de la commune étrangère de l'adresse.
         * @return la valeur de la commune étrangère.
         */
        HasValue<String> getTbCommuneEtranger();

        /**
         * Récupére la listes des composants d'aides.
         * @return List<AideComposant>
         */
        List<AideComposant> getListAideCompsant();

        /**
         * Récupére la liste des composants d'aides avec demandeEventHandler.
         * @return List<HasDemandeAideEventHandler>
         */
        List<HasDemandeAideEventHandler> getListeDemandeEventHandler();

        /**
         * Récupére la liste des composants d'aides avec demandeEventHandler.
         * @return List<HasUpdateAideEventHandler>
         */
        List<HasUpdateAideEventHandler> getListeUpdateEventHandler();

    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
        deskBarRegistration.removeHandler();
    }
}
