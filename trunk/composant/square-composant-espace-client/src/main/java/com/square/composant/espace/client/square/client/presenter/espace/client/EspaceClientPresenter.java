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
package com.square.composant.espace.client.square.client.presenter.espace.client;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.espace.client.square.client.bundle.ComposantEspaceClientResources;
import com.square.composant.espace.client.square.client.content.i18n.ComposantEspaceClientConstants;
import com.square.composant.espace.client.square.client.model.ConstantesModel;
import com.square.composant.espace.client.square.client.model.espace.adherent.OptionAdherentModel;
import com.square.composant.espace.client.square.client.model.espace.client.EspaceClientInternetModel;
import com.square.composant.espace.client.square.client.service.AdherentServiceGwt;
import com.square.composant.espace.client.square.client.service.AdherentServiceGwtAsync;
import com.square.composant.espace.client.square.client.service.ConstantesServiceGwt;
import com.square.composant.espace.client.square.client.service.ConstantesServiceGwtAsync;
import com.square.composant.espace.client.square.client.service.EspaceClientInternetServiceGwt;
import com.square.composant.espace.client.square.client.service.EspaceClientInternetServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Presenter pour l'espace personnel de l'adhérent.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class EspaceClientPresenter extends Presenter {

    /** Instance des ressources. */
    public static final ComposantEspaceClientResources RESOURCES = GWT.create(ComposantEspaceClientResources.class);

    private ComposantEspaceClientConstants composantEspaceClientConstants;

    private EspaceClientView view;

    private AdherentServiceGwtAsync adherentServiceASync;

    private EspaceClientInternetServiceGwtAsync espaceClientServiceASync;

    private ConstantesServiceGwtAsync constantesServiceASync;

    private ConstantesModel constantesComposant;

    private Long uidPersonne;

    private EspaceClientInternetModel infosEspaceClient;

    private EspaceClientConstants constantesEspaceAdherent;

    /**
     * Constructeur.
     * @param eventBus le bus qui transmet les évènements.
     * @param uidPersonne identifiant unique Square de l'adhérent.
     * @param view la vue instanciée à lier au presenter.
     */
    public EspaceClientPresenter(HandlerManager eventBus, final EspaceClientView view, Long uidPersonne) {
        super(eventBus);

        StyleInjector.inject(RESOURCES.css().getText());

        this.view = view;
        this.uidPersonne = uidPersonne;
        composantEspaceClientConstants = (ComposantEspaceClientConstants) GWT.create(ComposantEspaceClientConstants.class);
        adherentServiceASync = GWT.create(AdherentServiceGwt.class);
        espaceClientServiceASync = GWT.create(EspaceClientInternetServiceGwt.class);
        constantesServiceASync = GWT.create(ConstantesServiceGwt.class);
        constantesEspaceAdherent = GWT.create(EspaceClientConstants.class);

        // On récupère les constantes
        constantesServiceASync.getConstantes(new AsyncCallback<ConstantesModel>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(ConstantesModel constantesModel) {
                constantesComposant = constantesModel;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View asView() {
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBind() {
        view.getBlocOptionsView().getHandlerEnregistrerOptions().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                enregistrerOptions();
            }
        });
        final ValueChangeHandler<Boolean> valueChangeHandlerModeEnvoi = new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                rafraichirBtnEnvoiMdp();
            }
        };
        view.getBlocIdentifiantsView().getValueChangeHandlerModeEnvoiEmail().addValueChangeHandler(valueChangeHandlerModeEnvoi);
        view.getBlocIdentifiantsView().getValueChangeHandlerModeEnvoiSMS().addValueChangeHandler(valueChangeHandlerModeEnvoi);
        view.getBlocIdentifiantsView().getHandlerEnvoiMotDePasse().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                envoyerMotDePasse();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onShow(HasWidgets container) {
        chargerInfosEspaceAdherent();
        container.add(view.asWidget());
    }

    private void envoyerMotDePasse() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(composantEspaceClientConstants.envoiMotDePasseEnCours()));
        final boolean envoyerParMail = Boolean.TRUE.equals(view.getBlocIdentifiantsView().getModeEnvoiEmail().getValue());
        final boolean envoyerParSms = Boolean.TRUE.equals(view.getBlocIdentifiantsView().getModeEnvoiSMS().getValue());
        espaceClientServiceASync.envoyerMotDePassePerdu(infosEspaceClient.getLogin(), envoyerParMail, envoyerParSms, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(Void result) {
                LoadingPopup.stopAll();
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(constantesEspaceAdherent.notificationMdpEnvoye(), constantesEspaceAdherent.notificationTimeOut());
                final DecoratedInfoPopup infoPopup = new DecoratedInfoPopup(config);
                infoPopup.show();
            }
        });
    }

    private void enregistrerOptions() {
        adherentServiceASync.mettreAJourListeOptionsAdherent(uidPersonne, infosEspaceClient.getInfosOptionsAdherentModel().getListeOptions(),
            new AsyncCallback<List<OptionAdherentModel>>() {
                @Override
                public void onFailure(Throwable caught) {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }

                @Override
                public void onSuccess(List<OptionAdherentModel> result) {
                    final PopupInfoConfiguration config =
                        new PopupInfoConfiguration(constantesEspaceAdherent.notificationOptionsEnregistrees(), constantesEspaceAdherent.notificationTimeOut());
                    config.setCallback(new PopupCallback() {

                        @Override
                        public void onResult(boolean result) {
                            chargerInfosEspaceAdherent();
                        }
                    });
                    final DecoratedInfoPopup infoPopup = new DecoratedInfoPopup(config);
                    infoPopup.show();
                }
            });
    }

    /**
     * Récupère les informations de l'espace personnel de l'adhérent pour les charger dans l'interface graphique.
     */
    public void chargerInfosEspaceAdherent() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(constantesEspaceAdherent.rechercheEnCours()));
        espaceClientServiceASync.getEspaceClientInternet(uidPersonne, new AsyncCallback<EspaceClientInternetModel>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(EspaceClientInternetModel result) {
                LoadingPopup.stopAll();
                infosEspaceClient = result;
                if (infosEspaceClient.getInfosOptionsAdherentModel() != null) {
                    detecterOptionsManquantes();
                }
                rafraichirVues();
            }
        });
    }

    /**
     * Détecte les options manquantes.
     */
    private void detecterOptionsManquantes() {
        // On détecte les options existantes
        boolean alreadyHasOptionSms = false;
        boolean alreadyHasOptionMutuellement = false;
        boolean alreadyHasOptionRelevePrestation = false;
        for (OptionAdherentModel option : infosEspaceClient.getInfosOptionsAdherentModel().getListeOptions()) {
            if (constantesComposant.getIdTypeOptionEnvoiSms().equals(option.getType().getIdentifiant())) {
                alreadyHasOptionSms = true;
            } else if (constantesComposant.getIdTypeOptionEnvoiMutuellementEmail().equals(option.getType().getIdentifiant())) {
                alreadyHasOptionMutuellement = true;
            } else if (constantesComposant.getIdTypeOptionEnvoiRelevesPrestationEmail().equals(option.getType().getIdentifiant())) {
                alreadyHasOptionRelevePrestation = true;
            }
        }
        // On ajoute manuellement les options qui n'existent pas dans la liste des options à enregistrer
        if (!alreadyHasOptionSms) {
            ajouterOptionManquante(constantesComposant.getIdTypeOptionEnvoiSms());
        }
        if (!alreadyHasOptionMutuellement) {
            ajouterOptionManquante(constantesComposant.getIdTypeOptionEnvoiMutuellementEmail());
        }
        if (!alreadyHasOptionRelevePrestation) {
            ajouterOptionManquante(constantesComposant.getIdTypeOptionEnvoiRelevesPrestationEmail());
        }
    }

    /**
     * Ajoute une option manquante à la liste des options à sauvegarder.
     * @param idTypeOption type de l'option
     */
    private void ajouterOptionManquante(Long idTypeOption) {
        final OptionAdherentModel option = new OptionAdherentModel();
        option.setActive(false);
        option.setType(new IdentifiantLibelleGwt(idTypeOption));
        infosEspaceClient.getInfosOptionsAdherentModel().getListeOptions().add(option);
    }

    private void rafraichirVues() {
        view.getBlocSyntheseView().getDatePremiereVisite().setText(infosEspaceClient.getDatePremiereVisite());
        view.getBlocSyntheseView().getDateDerniereVisite().setText(infosEspaceClient.getDateDerniereVisite());
        view.getBlocSyntheseView().getNbVisites().setText(String.valueOf(infosEspaceClient.getNbVisites()));

        if (infosEspaceClient.getInfosOptionsAdherentModel() != null) {
            view.getBlocSyntheseView().ajouterDateModifiationOptions();
            view.getBlocSyntheseView().getDateModificationOptions().setText(infosEspaceClient.getInfosOptionsAdherentModel().getDateModificationOptions());
        }

        view.getBlocIdentifiantsView().getEmail().setText(infosEspaceClient.getEmail());
        view.getBlocIdentifiantsView().enableCbEmail(infosEspaceClient.getEmail() != null && !"".equals(infosEspaceClient.getEmail()));
        view.getBlocIdentifiantsView().getTelephone().setText(infosEspaceClient.getTelephone());
        view.getBlocIdentifiantsView().enableCbTelephone(infosEspaceClient.getTelephone() != null && !"".equals(infosEspaceClient.getTelephone()));
        view.getBlocIdentifiantsView().getIdentifiantConnexion().setText(infosEspaceClient.getLogin());

        rafraichirBtnEnvoiMdp();

        if (infosEspaceClient.getInfosOptionsAdherentModel() != null) {
            view.getBlocOptionsView().setBlocOptionVisible(true);
            rafraichirOptionsAdherent();
        }
    }

    private void rafraichirOptionsAdherent() {
        for (final OptionAdherentModel option : infosEspaceClient.getInfosOptionsAdherentModel().getListeOptions()) {
            if (constantesComposant.getIdTypeOptionEnvoiSms().equals(option.getType().getIdentifiant())) {
                if (option.isActive()) {
                    view.getBlocOptionsView().getLabelOptionSms().setText(composantEspaceClientConstants.labelOptionActive());
                } else {
                    view.getBlocOptionsView().getLabelOptionSms().setText(composantEspaceClientConstants.labelOptionDesactive());
                }
                view.getBlocOptionsView().getOptionSmsActive().setValue(option.isActive());
                view.getBlocOptionsView().getOptionSmsDesactive().setValue(!option.isActive());
                if (option.getDateModification() != null) {
                    view.getBlocOptionsView().getDateModificationOptionSms().setText(option.getDateModification());
                } else {
                    view.getBlocOptionsView().getDateModificationOptionSms().setText(composantEspaceClientConstants.aucuneDate());
                }

                // Mise en place des handlers pour enregistrer automatiquement les modifs dans le DTO :
                view.getBlocOptionsView().getOptionSmsActiveEventHandler().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Boolean> event) {
                        option.setActive(event.getValue());
                    }
                });
                view.getBlocOptionsView().getOptionSmsDesactiveEventHandler().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Boolean> event) {
                        option.setActive(!event.getValue());
                    }
                });

            } else if (constantesComposant.getIdTypeOptionEnvoiMutuellementEmail().equals(option.getType().getIdentifiant())) {
                if (option.isActive()) {
                    view.getBlocOptionsView().getLabelOptionMutuellement().setText(composantEspaceClientConstants.labelOptionActive());
                } else {
                    view.getBlocOptionsView().getLabelOptionMutuellement().setText(composantEspaceClientConstants.labelOptionDesactive());
                }
                view.getBlocOptionsView().getOptionMutuellementActive().setValue(option.isActive());
                view.getBlocOptionsView().getOptionMutuellementDesactive().setValue(!option.isActive());
                if (option.getDateModification() != null) {
                    view.getBlocOptionsView().getDateModificationOptionMutuellement().setText(option.getDateModification());
                } else {
                    view.getBlocOptionsView().getDateModificationOptionMutuellement().setText(composantEspaceClientConstants.aucuneDate());
                }

                // Mise en place des handlers pour enregistrer automatiquement les modifs dans le DTO :
                view.getBlocOptionsView().getOptionMutuellementActiveEventHandler().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Boolean> event) {
                        option.setActive(event.getValue());
                    }
                });
                view.getBlocOptionsView().getOptionMutuellementDesactiveEventHandler().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Boolean> event) {
                        option.setActive(!event.getValue());
                    }
                });
            } else if (constantesComposant.getIdTypeOptionEnvoiRelevesPrestationEmail().equals(option.getType().getIdentifiant())) {
                if (option.isActive()) {
                    view.getBlocOptionsView().getLabelOptionRelevesPrestations().setText(composantEspaceClientConstants.labelOptionActive());
                } else {
                    view.getBlocOptionsView().getLabelOptionRelevesPrestations().setText(composantEspaceClientConstants.labelOptionDesactive());
                }
                view.getBlocOptionsView().getOptionRelevesPrestationsActive().setValue(option.isActive());
                view.getBlocOptionsView().getOptionRelevesPrestationsDesactive().setValue(!option.isActive());
                if (option.getDateModification() != null) {
                    view.getBlocOptionsView().getDateModificationOptionRelevesPrestations().setText(option.getDateModification());
                } else {
                    view.getBlocOptionsView().getDateModificationOptionRelevesPrestations().setText(composantEspaceClientConstants.aucuneDate());
                }

                // Mise en place des handlers pour enregistrer automatiquement les modifs dans le DTO :
                view.getBlocOptionsView().getOptionRelevesPrestationsActiveEventHandler().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Boolean> event) {
                        option.setActive(event.getValue());
                    }
                });
                view.getBlocOptionsView().getOptionRelevesPrestationsDesactiveEventHandler().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Boolean> event) {
                        option.setActive(!event.getValue());
                    }
                });
            }
        }
    }

    private void rafraichirBtnEnvoiMdp() {
        final boolean personneHasEmail = infosEspaceClient.getEmail() != null && !infosEspaceClient.getEmail().isEmpty();
        final boolean personneHasTelephone = infosEspaceClient.getTelephone() != null && !infosEspaceClient.getTelephone().isEmpty();
        final boolean isModeEnvoiSelected =
            Boolean.TRUE.equals(view.getBlocIdentifiantsView().getModeEnvoiEmail().getValue())
                || Boolean.TRUE.equals(view.getBlocIdentifiantsView().getModeEnvoiSMS().getValue());
        // On active l'envoi de mot de passe que si la personne possède un email ou un téléphone et qu'un mode d'envoi est selectionné
        view.getBlocIdentifiantsView().enableEnvoiMotDePasse((personneHasEmail || personneHasTelephone) && isModeEnvoiSelected);
    }

    /**
     * Interface de la vue.
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     */
    public interface EspaceClientView extends View {
        /**
         * Récupère la vue du bloc de synthèse.
         * @return la vue.
         */
        BlocSyntheseEspaceAdherentView getBlocSyntheseView();

        /**
         * Récupère la vue du bloc d'identifiants.
         * @return la vue.
         */
        BlocIdentifiantsAdherentView getBlocIdentifiantsView();

        /**
         * Récupère la vue du bloc d'options.
         * @return la vue.
         */
        BlocOptionsAdherentView getBlocOptionsView();

        /**
         * Affiche un message d'erreur.
         * @param errorPopupConfiguration errorPopupConfiguration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);
    }

    /**
     * Interface de la vue.
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     */
    public interface BlocSyntheseEspaceAdherentView extends View {
        /**
         * Récupère la date de première visite.
         * @return le texte.
         */
        HasText getDatePremiereVisite();

        /**
         * Récupère la date de dernière visite.
         * @return le texte.
         */
        HasText getDateDerniereVisite();

        /**
         * Récupère le nombre de visites.
         * @return le texte.
         */
        HasText getNbVisites();

        /**
         * Récupère la date de modification des options.
         * @return le texte.
         */
        HasText getDateModificationOptions();

        /**
         * Ajoute la date de modifications des options.
         */
        void ajouterDateModifiationOptions();
    }

    /**
     * Interface de la vue.
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     */
    public interface BlocIdentifiantsAdherentView extends View {
        /**
         * Récupère l'email de l'adhérent.
         * @return le texte.
         */
        HasText getEmail();

        /**
         * Récupère le téléphone de l'adhérent.
         * @return le texte.
         */
        HasText getTelephone();

        /**
         * Récupère le numéro de l'adhérent.
         * @return le texte.
         */
        HasText getIdentifiantConnexion();

        /**
         * Accesseur mode envoi par email.
         * @return la valeur du mode d'envoi
         */
        HasValue<Boolean> getModeEnvoiEmail();

        /**
         * Accesseur mode envoi par SMS.
         * @return la valeur du mode d'envoi
         */
        HasValue<Boolean> getModeEnvoiSMS();

        /**
         * Récupère le handler de changement de valeur du mode d'envoi par email.
         * @return le handler
         */
        HasValueChangeHandlers<Boolean> getValueChangeHandlerModeEnvoiEmail();

        /**
         * Récupère le handler de changement de valeur du mode d'envoi par SMS.
         * @return le handler
         */
        HasValueChangeHandlers<Boolean> getValueChangeHandlerModeEnvoiSMS();

        /**
         * Récupère le handler de clic pour envoyer le mot de passe.
         * @return le handler.
         */
        HasClickHandlers getHandlerEnvoiMotDePasse();

        /**
         * Active / désactive l'envoi de mot de passe.
         * @param enable true pour activer, false pour désactiver.
         */
        void enableEnvoiMotDePasse(boolean enable);

        /**
         * Active / désactive la checkbox de l'email.
         * @param enable true pour activer, false pour désactiver.
         */
        void enableCbEmail(boolean enable);

        /**
         * Active / désactive la checkbox du téléphone.
         * @param enable true pour activer, false pour désactiver.
         */
        void enableCbTelephone(boolean enable);
    }

    /**
     * Interface de la vue.
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     */
    public interface BlocOptionsAdherentView extends View {
        /**
         * Accesseur texte.
         * @return le texte.
         */
        HasText getLabelOptionSms();

        /**
         * Accesseur texte.
         * @return le texte.
         */
        HasText getLabelOptionMutuellement();

        /**
         * Accesseur texte.
         * @return le texte.
         */
        HasText getLabelOptionRelevesPrestations();

        /**
         * Accesseur texte.
         * @return le texte.
         */
        HasText getDateModificationOptionSms();

        /**
         * Accesseur texte.
         * @return le texte.
         */
        HasText getDateModificationOptionMutuellement();

        /**
         * Accesseur texte.
         * @return le texte.
         */
        HasText getDateModificationOptionRelevesPrestations();

        /**
         * Accesseur valeur.
         * @return la valeur.
         */
        HasValue<Boolean> getOptionSmsActive();

        /**
         * Accesseur event.
         * @return le handler
         */
        HasValueChangeHandlers<Boolean> getOptionSmsActiveEventHandler();

        /**
         * Accesseur valeur.
         * @return la valeur.
         */
        HasValue<Boolean> getOptionMutuellementActive();

        /**
         * Accesseur event.
         * @return le handler
         */
        HasValueChangeHandlers<Boolean> getOptionMutuellementActiveEventHandler();

        /**
         * Accesseur valeur.
         * @return la valeur.
         */
        HasValue<Boolean> getOptionRelevesPrestationsActive();

        /**
         * Accesseur event.
         * @return le handler
         */
        HasValueChangeHandlers<Boolean> getOptionRelevesPrestationsActiveEventHandler();

        /**
         * Accesseur valeur.
         * @return la valeur.
         */
        HasValue<Boolean> getOptionSmsDesactive();

        /**
         * Accesseur event.
         * @return le handler
         */
        HasValueChangeHandlers<Boolean> getOptionSmsDesactiveEventHandler();

        /**
         * Accesseur valeur.
         * @return la valeur.
         */
        HasValue<Boolean> getOptionMutuellementDesactive();

        /**
         * Accesseur event.
         * @return le handler
         */
        HasValueChangeHandlers<Boolean> getOptionMutuellementDesactiveEventHandler();

        /**
         * Accesseur valeur.
         * @return la valeur.
         */
        HasValue<Boolean> getOptionRelevesPrestationsDesactive();

        /**
         * Accesseur event.
         * @return le handler
         */
        HasValueChangeHandlers<Boolean> getOptionRelevesPrestationsDesactiveEventHandler();

        /**
         * Accesseur handler de clic pour enregistrer les options.
         * @return le handler.
         */
        HasClickHandlers getHandlerEnregistrerOptions();

        /**
         * Visibilité du bloc d'option.
         * @param visible visible ou pas
         */
        void setBlocOptionVisible(boolean visible);

    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

}
