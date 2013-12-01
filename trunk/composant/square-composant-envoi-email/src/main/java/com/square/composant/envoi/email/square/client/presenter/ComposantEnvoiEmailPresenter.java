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
package com.square.composant.envoi.email.square.client.presenter;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.exception.TechnicalExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.square.composant.envoi.email.square.client.bundle.ComposantEnvoiEmailRessources;
import com.square.composant.envoi.email.square.client.constantes.ComposantEnvoiEmailConstants;
import com.square.composant.envoi.email.square.client.content.i18n.ComposantEnvoiEmailMessages;
import com.square.composant.envoi.email.square.client.event.AnnulationEnvoiEmailEvent;
import com.square.composant.envoi.email.square.client.event.EmailEnvoyeEvent;
import com.square.composant.envoi.email.square.client.event.FinUploadPieceJointeEvent;
import com.square.composant.envoi.email.square.client.event.FinUploadPieceJointeEventHandler;
import com.square.composant.envoi.email.square.client.event.SuppressionPieceJointeEvent;
import com.square.composant.envoi.email.square.client.event.SuppressionPieceJointeEventHandler;
import com.square.composant.envoi.email.square.client.exception.ControleIntegriteExceptionGwt;
import com.square.composant.envoi.email.square.client.model.DimensionCriteresRechercheModel;
import com.square.composant.envoi.email.square.client.model.EmailModel;
import com.square.composant.envoi.email.square.client.model.ModeleEmailModel;
import com.square.composant.envoi.email.square.client.model.PieceJointeModel;
import com.square.composant.envoi.email.square.client.popup.PopupAjoutPieceJointe;
import com.square.composant.envoi.email.square.client.service.ComposantEnvoiMailServiceGWT;
import com.square.composant.envoi.email.square.client.service.ComposantEnvoiMailServiceGWTAsync;
import com.square.composant.envoi.email.square.client.view.ComposantEnvoiEmailViewImpl;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEventHandler;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Presenter du composant d'envoi d'email.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ComposantEnvoiEmailPresenter extends Presenter {

    /** Ressources. */
    public static final ComposantEnvoiEmailRessources RESSOURCES = GWT.create(ComposantEnvoiEmailRessources.class);

    /** Vue associée au presenter. */
    private ComposantEnvoiEmailView view;

    /** Liste des pièces jointes. */
    private List<PieceJointeModel> listePiecesJointes;

    /** Popup permettant d'ajouter une pièce jointe au mail. */
    private PopupAjoutPieceJointe popupAjoutPieceJoint;

    private ComposantEnvoiEmailMessages composantMessages;

    /** Service d'envoi d'email. */
    private ComposantEnvoiMailServiceGWTAsync emailComposantEnvoiService;

    /** Flag indiquant si la touche Ctrl est enfoncée. */
    private boolean toucheCtrlEnfoncee = false;

    private final int toucheCtrl = 17;

    private final int toucheV = 86;

    private DeskBar deskBar;

    private HandlerRegistration deskBarRegistration;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènements.
     * @param deskBar deskBar
     */
    public ComposantEnvoiEmailPresenter(HandlerManager eventBus, DeskBar deskBar) {
        super(eventBus);
        StyleInjector.inject(RESSOURCES.css().getText());
        this.composantMessages = GWT.create(ComposantEnvoiEmailMessages.class);
        this.emailComposantEnvoiService = GWT.create(ComposantEnvoiMailServiceGWT.class);
        this.view = new ComposantEnvoiEmailViewImpl(localEventBus, true);
        listePiecesJointes = new ArrayList<PieceJointeModel>();
        popupAjoutPieceJoint = new PopupAjoutPieceJointe(localEventBus);
        this.deskBar = deskBar;
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        view.getSlbModeleEmail().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(ComposantEnvoiEmailConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                emailComposantEnvoiService.rechercherListeModelesEmails(criteres, event.getCallBack());
            }
        });
        view.getSlbModeleEmail().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setId(event.getValue().getIdentifiant());
                final AsyncCallback<List<ModeleEmailModel>> asyncCallback = new AsyncCallback<List<ModeleEmailModel>>() {
                    public void onFailure(Throwable caught) {
                    	view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }

                    public void onSuccess(List<ModeleEmailModel> result) {
                    	final ModeleEmailModel modeleEmail = result.get(0);
                    	view.getTbObjet().setValue(modeleEmail.getObjet(), true);
                    	view.setMessage(modeleEmail.getCorps());
                    	view.setFocusMessage();
                    }
                };
                emailComposantEnvoiService.rechercherModelesEmails(criteres, asyncCallback);
            }
        });
        // Gestion des touches de la popup
        view.getAllKeyHandlersFocusPanel().addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    envoyerEmail();
                }
                else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    fireEventLocalBus(new AnnulationEnvoiEmailEvent());
                }
            }
        });
        addEventHandlerToLocalBus(FinUploadPieceJointeEvent.TYPE, new FinUploadPieceJointeEventHandler() {
            @Override
            public void informerFinUploadPieceJointe(FinUploadPieceJointeEvent event) {
                ajouterFichierJoint(event.getProprietesPieceJointe());
                popupAjoutPieceJoint.hide();
            }
        });
        addEventHandlerToLocalBus(SuppressionPieceJointeEvent.TYPE, new SuppressionPieceJointeEventHandler() {
            @Override
            public void supprimerPieceJointe(SuppressionPieceJointeEvent event) {
                supprimerPieceJointeRepertoire(event);
            }
        });
        view.getBtnEnvoyerEmail().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                envoyerEmail();
            }
        });
        view.getBtnJoindreFichier().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                popupAjoutPieceJoint.afficherPopupAjoutFichierJoint();
            }
        });
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                viderPiecesJointes();
                fireEventLocalBus(new AnnulationEnvoiEmailEvent());
            }
        });
        // On reformate le texte sur un Ctrl+V provenant de word ou openoffice (mantis 6757).
        view.getAllKeyHandlersMessage().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == toucheCtrl && !toucheCtrlEnfoncee) {
                    toucheCtrlEnfoncee = true;
                }
            }
        });
        view.getAllKeyHandlersMessage().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == toucheCtrl && toucheCtrlEnfoncee) {
                    toucheCtrlEnfoncee = false;
                }
                else if (toucheCtrlEnfoncee && event.getNativeKeyCode() == toucheV) {
                    // on reformate le texte afin d'éviter les sauts de lignes provenant d'un copier-coller.
                    view.setMessage(view.getMessage().replaceAll("<p[^>]*>", "").replaceAll("</p>", "<br/>").trim());
                }
            }
        });
        deskBarRegistration = deskBar.getEventBus().addHandler(EnableMinimizeWidgetEvent.TYPE, new EnableMinimizeWidgetEventHandler() {
            @Override
            public void enableMinimizeWidget(EnableMinimizeWidgetEvent event) {
                view.activerBoutonReduire(event.isEnabled());
            }
        });
    }

    /** Envoie l'email. */
    private void envoyerEmail() {
        final LoadingPopupConfiguration loadingPopupConfiguration = new LoadingPopupConfiguration(composantMessages.messageEnvoiMailEnCours());
        LoadingPopup.afficher(loadingPopupConfiguration);

        // on reformate le texte afin d'éviter les sauts de lignes provenant d'un copier-coller.
        view.setMessage(view.getMessage().replaceAll("<p[^>]*>", "").replaceAll("</p>", "<br/>").trim());

        final String[] tabDestinataires = view.getTbDestinataire().getValue().split(", ");
        final List<String> listeDestinataires = new ArrayList<String>();
        if (tabDestinataires != null && tabDestinataires.length > 0) {
            for (int i = 0; i < tabDestinataires.length; i++) {
                if (tabDestinataires[i] != null && !"".equals(tabDestinataires[i].trim())) {
                    listeDestinataires.add(tabDestinataires[i]);
                }
            }
        }
        final EmailModel email =
            new EmailModel(view.getTbExpediteur().getValue(), listeDestinataires, view.getTbObjet().getValue(), view.getMessage(), listePiecesJointes, true);
        final String nomExpediteur = view.getTbNomExpediteur().getValue();
        if (nomExpediteur != null && !"".equals(nomExpediteur)) {
            email.setNomExpediteur(nomExpediteur);
        }

        // Envoi du mail
        final AsyncCallback<Void> asyncCallback = new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    view.masquerLoadingPopup();
                }
                else if (caught.getMessage().equals(ComposantEnvoiEmailConstants.ERREUR_ABSCENCE_PIECE_JOINTE)) {
                		view.onRpcServiceFailure(new ErrorPopupConfiguration(new TechnicalExceptionGwt(composantMessages.erreurAbscencePieceJointe())));
                	} else if (caught.getMessage().equals(ComposantEnvoiEmailConstants.ERREUR_RECUPERATION_PIECE_JOINTE)) {
                		view.onRpcServiceFailure(new ErrorPopupConfiguration(new TechnicalExceptionGwt(
                				composantMessages.erreurRecuperationPieceJointe())));
                	} else {
                		view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }

            public void onSuccess(Void result) {
                LoadingPopup.stopAll();
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(composantMessages.emailEnvoye(), ComposantEnvoiEmailConstants.NOTIFICATION_TIME_OUT);
                config.setCallback(new PopupCallback() {
                    @Override
                    public void onResult(boolean result) {
                        fireEventLocalBus(new EmailEnvoyeEvent());
                    }
                });
                new DecoratedInfoPopup(config).show();
            }
        };
        emailComposantEnvoiService.envoyerEmail(email, asyncCallback);
    }

    /** Suppression des pièces jointes restantes dans le répertoire de stockage. */
    private void viderPiecesJointes() {
        if (listePiecesJointes != null && listePiecesJointes.size() > 0) {
            final List<String> listePiecesJointesASupprimer = new ArrayList<String>();
            for (PieceJointeModel pieceJointe : listePiecesJointes) {
                listePiecesJointesASupprimer.add(pieceJointe.getPath());
            }
            final AsyncCallback<Void> asyncCallback = new AsyncCallback<Void>() {
                @Override
                public void onFailure(Throwable caught) {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }

                @Override
                public void onSuccess(Void result) {
                }
            };
            emailComposantEnvoiService.supprimerListePiecesJointesRepertoire(listePiecesJointesASupprimer, asyncCallback);
        }
    }

    /**
     * Supprime une pièce jointe du répertoire de stockage des données.
     * @param event l'évènement déclenchant la suppresion de la pièce jointe.
     */
    private void supprimerPieceJointeRepertoire(final SuppressionPieceJointeEvent event) {
        final AsyncCallback<Void> asyncCallback = new AsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                // Suppression du fichier de la liste
                listePiecesJointes.remove(event.getPieceJointe());
                // On affiche "Aucun fichier joint" si la liste est vide
                if (listePiecesJointes.size() == 0) {
                    view.afficherAucunePieceJointe();
                }
                view.supprimerPieceJointe(event.getLabelPieceJointe());
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        final List<String> listePiecesJointesASupprimer = new ArrayList<String>();
        listePiecesJointesASupprimer.add(event.getPieceJointe().getPath());
        emailComposantEnvoiService.supprimerListePiecesJointesRepertoire(listePiecesJointesASupprimer, asyncCallback);
    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
        this.initFocus();
    }

    /**
     * Ajoute le fichier joint en décodant les propriétés du fichier et en ajoutant le fichier au mail.
     * @param proprietesFichier les propriétés du fichier encodé.
     */
    public void ajouterFichierJoint(String proprietesFichier) {
        // Parsing des propriétés
        final PieceJointeModel fichierJoint = parseProprietesFichierJoint(proprietesFichier);

        // Ajout du fichier joint à la liste
        listePiecesJointes.add(fichierJoint);

        // Affichage de la pièce jointe dans la vue
        view.afficherFichierJoint(fichierJoint, true);
    }

    /**
     * Parsing des propriétés d'un fichier joint.
     * @param proprietesFichier la chaine des propriétés du fichier à parser.
     * @return le modèle contenant les propriétés du fichier joint.
     */
    private PieceJointeModel parseProprietesFichierJoint(String proprietesFichier) {
        final PieceJointeModel pieceJointe = new PieceJointeModel();
        // Séparation des différentes propriétés
        final String[] tabParamsValeurs = proprietesFichier.split(ComposantEnvoiEmailConstants.ET);
        // Parcours du tableau pour séparation du paramètre et de sa valeur
        for (int i = 0; i < tabParamsValeurs.length; i++) {
            final String[] paramValeur = tabParamsValeurs[i].split(ComposantEnvoiEmailConstants.EGAL);
            final String param = paramValeur[0];
            final String valeur = paramValeur[1];
            // Remplissage du DTO en fonction du paramètre
            if (param.equals(ComposantEnvoiEmailConstants.PARAM_NOM_FICHIER)) {
                pieceJointe.setNom(valeur);
            }
            else if (param.equals(ComposantEnvoiEmailConstants.PARAM_TYPE_MIME)) {
                pieceJointe.setTypeMime(valeur);
            }
            else if (param.equals(ComposantEnvoiEmailConstants.PARAM_PATH_FICHIER_TEMP)) {
                pieceJointe.setPath(valeur);
            }
        }
        return pieceJointe;
    }

    /**
     * Initialise le composant.
     * @param adresseEmailExpediteur l'adresse email de l'expéditeur.
     * @param expediteur le libellé de l'expéditeur.
     * @param listeDestinataires la liste des destinataires.
     * @param listePiecesJointesModel la liste des pièces jointes.
     */
    public void initialiserComposant(String adresseEmailExpediteur, String expediteur, List<String> listeDestinataires,
        List<PieceJointeModel> listePiecesJointesModel) {
        // Expéditeur
        view.getTbExpediteur().setValue(adresseEmailExpediteur);
        view.getTbNomExpediteur().setValue(expediteur);

        // Destinataires
        view.getTbDestinataire().setValue("");
        view.getTbObjet().setValue("");
        view.setMessage("");
        view.getSlbModeleEmail().clean();

        String destinataires = "";
        if (listeDestinataires.size() > 0) {
            if (listeDestinataires.get(0) != null && !"".equals(listeDestinataires.get(0))) {
                destinataires = listeDestinataires.get(0);
            }

            for (int i = 1; i < listeDestinataires.size(); i++) {
                if (listeDestinataires.get(i) != null && !"".equals(listeDestinataires.get(i))) {
                    destinataires = destinataires + ", " + listeDestinataires.get(i);
                }
            }
            view.getTbDestinataire().setValue(destinataires);
        }

        view.nettoyerPiecesJointes();
        // Pièces jointes initiales non supprimables
        for (PieceJointeModel pieceJointe : listePiecesJointesModel) {
            view.afficherFichierJoint(pieceJointe, false);
        }
        listePiecesJointes.clear();
        listePiecesJointes = listePiecesJointesModel;
    }

    /**
     * Initialise le focus de la vue gérée par ce presenter.
     */
    public void initFocus() {
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                // On initialise le focus
                view.initFocus();
            }
        });
    }

    /**
     * Vue du composant d'envoi d'email.
     * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
     */
    public interface ComposantEnvoiEmailView extends View {

        /**
         * Retourne le ClickHandler du bouton d'envoi de l'email.
         * @return le ClickHandler.
         */
        HasClickHandlers getBtnEnvoyerEmail();

        /**
         * Initialise le focus de la vue.
         */
        void initFocus();

        /**
         * Retourne le ClickHandler du bouton pour joindre un fichier à l'email.
         * @return le ClickHandler.
         */
        HasClickHandlers getBtnJoindreFichier();

        /**
         * Retourne le ClickHandler du bouton d'annulation.
         * @return le ClickHandler.
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Affiche la pièce jointe.
         * @param fichierJoint le fichier de la pièce jointe.
         * @param supprimable flag indiquant que le fichier est supprimable
         */
        void afficherFichierJoint(PieceJointeModel fichierJoint, boolean supprimable);

        /** Affiche aucune pièce jointe. */
        void afficherAucunePieceJointe();

        /**
         * Supprime une pièce jointe dans le panel listant les différentes pièces jointes.
         * @param labelPieceJointe le label de la pièce jointe à supprimer.
         */
        void supprimerPieceJointe(Label labelPieceJointe);

        /**
         * Récupère la valeur de l'email de l'expéditeur.
         * @return la valeur.
         */
        HasValue<String> getTbExpediteur();

        /**
         * Récupère la valeur du destinatiaire de l'email.
         * @return la valeur.
         */
        HasValue<String> getTbDestinataire();

        /**
         * Récupère la valeur de l'objet de l'email.
         * @return la valeur.
         */
        HasValue<String> getTbObjet();

        /**
         * Récupère la valeur du nom de l'expéditeur.
         * @return la valeur.
         */
        HasValue<String> getTbNomExpediteur();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbModeleEmail();

        /**
         * Récupère le message de l'email.
         * @return le message.
         */
        String getMessage();

        /**
         * Accesseur AllKeyHandlers du focus panel.
         * @return le handler
         */
        HasAllKeyHandlers getAllKeyHandlersFocusPanel();

        /**
         * Renvoi la valeur de iconeErreurChampManager.
         * @return iconeErreurChampManager
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /** Masque la popup de chargement. */
        void masquerLoadingPopup();

        /**
         * Affiche la popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Recupere le bouton reduire.
         * @return le bouton
         */
        HasClickHandlers getBtnReduireHandler();

        /**
         * Désactive le bouton réduire.
         * @param actif flag actif
         */
        void activerBoutonReduire(boolean actif);

        /**
         * Ecrit le message.
         * @param message le message
         */
        void setMessage(String message);

        /**
         * Donne le focus au message.
         */
        void setFocusMessage();

        /**
         * Recupere le message.
         * @return le message
         */
        HasAllKeyHandlers getAllKeyHandlersMessage();

        /**
         * Nettoie les pieces jointes.
         */
        void nettoyerPiecesJointes();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
        deskBarRegistration.removeHandler();
    }
}
