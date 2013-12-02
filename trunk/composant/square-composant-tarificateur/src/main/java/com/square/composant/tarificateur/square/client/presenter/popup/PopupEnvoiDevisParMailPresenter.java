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
package com.square.composant.tarificateur.square.client.presenter.popup;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.exception.TechnicalExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
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
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.envoi.email.square.client.event.AnnulationEnvoiEmailEvent;
import com.square.composant.envoi.email.square.client.event.AnnulationEnvoiEmailEventHandler;
import com.square.composant.envoi.email.square.client.event.EmailEnvoyeEvent;
import com.square.composant.envoi.email.square.client.event.EmailEnvoyeEventHandler;
import com.square.composant.envoi.email.square.client.model.PieceJointeModel;
import com.square.composant.envoi.email.square.client.presenter.ComposantEnvoiEmailPresenter;
import com.square.composant.envoi.email.square.client.presenter.ComposantEnvoiEmailPresenter.ComposantEnvoiEmailView;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.CritereModeleDevisModel;
import com.square.composant.tarificateur.square.client.model.InfosExpediteurEnvoiEmailModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.EditionDocumentModel;
import com.square.composant.tarificateur.square.client.service.DimensionServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurEditiqueServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwtAsync;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;
import com.square.composants.graphiques.lib.client.popups.minimizable.PopupMinimizable;

/**
 * Presenter de la popup d'envoi par email d'un devis.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupEnvoiDevisParMailPresenter extends Presenter {

    private PopupChoixModelesView view;

    private ConstantesModel constantesApp;

    private TarificateurServiceGwtAsync tarificateurRpcService;

    private TarificateurEditiqueServiceGwtAsync tarificateurEditiqueRpcService;

    private DimensionServiceGwtAsync dimensionRpcService;

    private DevisModel devis;

    private List<IdentifiantLibelleGwt> listeModelesDevis;

    private List<IdentifiantLibelleGwt> listeBeneficiaires;

    private Long referenceOpportunite;

    private List<Long> listeIdsLignesSelectionnees;

    private PopupEnvoiDevisParMailPresenterConstants constantsPresenter;

    /** Popup qui permet d'envoyer un email. */
    private Popup popupEnvoiEmail;

    /** Presenter du composant d'envoi d'email. */
    private ComposantEnvoiEmailPresenter composantEnvoiEmailPresenter;

    /** Adresse email de la personne en cours. */
    private String emailPersonneEnCours;

    /** Eid du responsable de l'opportunité. */
    private Long eidResponsable;

    /** Modèle en cours de l'édition de document. */
    private EditionDocumentModel editionDocumentModel;

    private DeskBar deskBar;

    private InfosExpediteurEnvoiEmailModel infos;

    private List<PieceJointeModel> listePiecesJointes;

    private ComposantTarificateurConstants composantTarificateurConstants;
    /**
     * Constructeur du présenter.
     * @param eventBus le bus.
     * @param vue la vue.
     * @param constantesApp les constantes de l'application.
     * @param tarificateurRpcService service rpc du tarificateur.
     * @param tarificateurEditiqueRpcService service rpc du tarificateur éditique.
     * @param dimensionRpcService service rpc des dimensions.
     * @param devis l'identifiant du devis sélectionné.
     * @param referenceOpportunite la référence de l'opportunité.
     * @param listeIdsLignesSelectionnees la liste des identifiants des lignes de devis sélectionnées.
     * @param emailPersonneEnCours l'adresse email de la personne en cours.
     * @param eidResponsable eid du responsable de l'opportunité
     * @param deskBar deskBar
     */
    public PopupEnvoiDevisParMailPresenter(HandlerManager eventBus, PopupChoixModelesView vue, ConstantesModel constantesApp,
        TarificateurServiceGwtAsync tarificateurRpcService, TarificateurEditiqueServiceGwtAsync tarificateurEditiqueRpcService,
        DimensionServiceGwtAsync dimensionRpcService, DevisModel devis, Long referenceOpportunite, List<Long> listeIdsLignesSelectionnees,
        String emailPersonneEnCours, Long eidResponsable, DeskBar deskBar) {
        super(eventBus);
        this.view = vue;
        this.constantesApp = constantesApp;
        this.tarificateurRpcService = tarificateurRpcService;
        this.tarificateurEditiqueRpcService = tarificateurEditiqueRpcService;
        this.dimensionRpcService = dimensionRpcService;
        this.devis = devis;
        this.referenceOpportunite = referenceOpportunite;
        this.listeIdsLignesSelectionnees = listeIdsLignesSelectionnees;
        this.emailPersonneEnCours = emailPersonneEnCours;
        this.eidResponsable = eidResponsable;
        this.deskBar = deskBar;
        this.composantTarificateurConstants = (ComposantTarificateurConstants) GWT.create(ComposantTarificateurConstants.class);
        editionDocumentModel = null;

        constantsPresenter = (PopupEnvoiDevisParMailPresenterConstants) GWT.create(PopupEnvoiDevisParMailPresenterConstants.class);

        construirePopupEnvoiEmail();

        // Récupération des modèles de devis pour l'envoi par email
        getModelesDevisPourEnvoiMail();

        // Récupération des éventuels bénéficiaires
        getBeneficiairesFromProspectByLigneDevis();
    }

    /** Construction de la popup qui permet d'envoyer un email. */
    private void construirePopupEnvoiEmail() {
        popupEnvoiEmail = new Popup(constantsPresenter.titrePopupEnvoiEmail(), false, false, true);
        popupEnvoiEmail.addStyleName(ComposantTarificateur.RESOURCES.css().popupEnvoiEmail());
        final VerticalPanel pContenuComposantEnvoiEmail = new VerticalPanel();
        pContenuComposantEnvoiEmail.setWidth("800px");
        composantEnvoiEmailPresenter = addChildPresenter(new ComposantEnvoiEmailPresenter(eventBus, deskBar));
        composantEnvoiEmailPresenter.showPresenter(pContenuComposantEnvoiEmail);
        popupEnvoiEmail.setWidget(pContenuComposantEnvoiEmail);
    }

    /** Récupère les modèles de devis pour l'envoi par email. */
    private void getModelesDevisPourEnvoiMail() {
        final CritereModeleDevisModel critere = new CritereModeleDevisModel();
        critere.setIdDevis(devis.getId());
        final List<Long> listeIdModeles = new ArrayList<Long>();
        listeIdModeles.add(constantesApp.getIdModeleDevisBulletinAdhesion());
        listeIdModeles.add(constantesApp.getIdModeleDevisFicheTransfert());
        critere.setListeIdsModeles(listeIdModeles);
        final AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback = new AsyncCallback<List<IdentifiantLibelleGwt>>() {
            @Override
            public void onSuccess(List<IdentifiantLibelleGwt> result) {
                listeModelesDevis = result;
                view.chargerModelesDevis(listeModelesDevis);

                DeferredCommand.addCommand(new Command() {
                    @Override
                    public void execute() {
                        view.initFocus();
                    }
                });
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        tarificateurRpcService.getListeModelesDevisByCritere(critere, asyncCallback);
    }

    /**
     * Affiche si nécessaire la sélection des bénéficiaires du prospect d'un devis pour des lignes de devis sélectionnées.
     */
    private void getBeneficiairesFromProspectByLigneDevis() {
        final AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback = new AsyncCallback<List<IdentifiantLibelleGwt>>() {
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            public void onSuccess(List<IdentifiantLibelleGwt> result) {
                listeBeneficiaires = result;
                view.chargerBeneficiaires(listeBeneficiaires);
            }
        };
        tarificateurRpcService.getBeneficiairesFromProspectByDevis(devis, listeIdsLignesSelectionnees, asyncCallback);
    }

    /**
     * Crée les pièces jointes correspondant aux devis.
     */
    private void creerPiecesJointes() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(constantsPresenter.creationPiecesJointesEnCours()));

        // Récupération des identifiants des modèles sélectionnés
        final List<Long> listeIdsModelesSelectionnes = view.getIdsModelesSelectionnes();

        // Récupération des bénéficiaires
        final List<Long> listeIdsBeneficiairesSelectionnes = view.getIdsBeneficiairesSelectionnes();

        editionDocumentModel = new EditionDocumentModel();
        editionDocumentModel.setIdentifiantDevis(devis.getId());
        editionDocumentModel.setIdsLigneDevisSelection(listeIdsLignesSelectionnees);
        editionDocumentModel.setIdsModelesDevisSelectionnes(listeIdsModelesSelectionnes);
        editionDocumentModel.setReferenceOpportunite(referenceOpportunite.toString());
        editionDocumentModel.setIdsBeneficiairesSelectionnes(listeIdsBeneficiairesSelectionnes);

        final AsyncCallback<List<PieceJointeModel>> asyncCallback = new AsyncCallback<List<PieceJointeModel>>() {
            @Override
            public void onSuccess(List<PieceJointeModel> listePJ) {
                LoadingPopup.stopAll();
                listePiecesJointes = listePJ;
                view.hidePopup();
                // Initialisation du contenu de l'email
                if (eidResponsable != null) {
                    recupererInfosExpediteur();
                } else {
                    infos = new InfosExpediteurEnvoiEmailModel();
                    initContenuEmail();
                }
            }

            @Override
            public void onFailure(Throwable caught) {
            	if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_SAVE_ATTACHED_PIECE)) {
            		view.onRpcServiceFailure(new ErrorPopupConfiguration(new TechnicalExceptionGwt(
            				composantTarificateurConstants.errorSaveAttachedPiece())));
            	} else if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_GENERATION_FICHIER)) {
            		view.onRpcServiceFailure(new ErrorPopupConfiguration(new TechnicalExceptionGwt(
            				composantTarificateurConstants.errorGenerationFichier())));
            	} else {
                	view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            	}
            }
        };
        tarificateurEditiqueRpcService.getPiecesJointes(editionDocumentModel, asyncCallback);
    }

    /**
     * Initialisation du contenu de l'email.
     * @param listePiecesJointes la liste des pièces jointes.
     */
    private void recupererInfosExpediteur() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(constantsPresenter.initialisationEmailEnCours()));
        final AsyncCallback<InfosExpediteurEnvoiEmailModel> asyncCallback = new AsyncCallback<InfosExpediteurEnvoiEmailModel>() {

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(InfosExpediteurEnvoiEmailModel result) {
                LoadingPopup.stopAll();
                infos = result;
                initContenuEmail();
            }
        };
        dimensionRpcService.getInfosExpediteurEmail(eidResponsable, asyncCallback);
    }

    private void initContenuEmail() {
        // Liste des destinataires du mail.
        final List<String> listeDestinataires = new ArrayList<String>();
        listeDestinataires.add(emailPersonneEnCours);

        composantEnvoiEmailPresenter.initialiserComposant(infos.getAdresseEmailExpediteur(), infos.getExpediteur(), listeDestinataires, listePiecesJointes);
        popupEnvoiEmail.show();
        composantEnvoiEmailPresenter.initFocus();
    }

    /** Notifie l'envoie de documents par mail. */
    private void notifierEnvoiDocumentsPdfParMail() {
        // Notification à l'aide du modèle d'édition de documents qui a été généré pour récupérer les pièces jointes
        if (editionDocumentModel != null) {
            final AsyncCallback<Void> asyncCallback = new AsyncCallback<Void>() {

                @Override
                public void onFailure(Throwable caught) {
                	if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_NOTIFICATION_LOGIN_ABSENT)) {
                		view.onRpcServiceFailure(new ErrorPopupConfiguration(
                				new TechnicalExceptionGwt(composantTarificateurConstants.errorNotificationLoginAbsent())));
                	} else if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_NOTIFICATION_USER_ABSENT)) {
                		view.onRpcServiceFailure(new ErrorPopupConfiguration(
                				new TechnicalExceptionGwt(composantTarificateurConstants.errorNotificationUserAbsent())));
                	} else {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                	}
                }

                @Override
                public void onSuccess(Void result) {
                    editionDocumentModel = null;
                }
            };
            tarificateurEditiqueRpcService.notifierEnvoiDocumentsPdfParMail(editionDocumentModel, asyncCallback);
        }
    }

    @Override
    public View asView() {
        return null;
    }

    @Override
    public void onBind() {
        // Gestion des touches de la popup
        view.getAllKeyHandlersFocusPanel().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    // Création des pièces jointes
                    creerPiecesJointes();
                } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    view.hidePopup();
                }
            }
        });
        view.getBtnValider().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Création des pièces jointes
                creerPiecesJointes();
            }
        });
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
                editionDocumentModel = null;
            }
        });
        composantEnvoiEmailPresenter.addEventHandlerToLocalBus(AnnulationEnvoiEmailEvent.TYPE, new AnnulationEnvoiEmailEventHandler() {
            @Override
            public void annulerEnvoiEmail(AnnulationEnvoiEmailEvent event) {
                popupEnvoiEmail.hide();
                editionDocumentModel = null;
            }
        });
        composantEnvoiEmailPresenter.addEventHandlerToLocalBus(EmailEnvoyeEvent.TYPE, new EmailEnvoyeEventHandler() {
            @Override
            public void informerEmailEnvoye(EmailEnvoyeEvent event) {
                popupEnvoiEmail.hide();
                // Notification de l'envoi des documents par mail
                notifierEnvoiDocumentsPdfParMail();
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        view.showPopup();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                // on en fait une popup minimisable
                deskBar.addPopup(new PopupMinimizable(popupEnvoiEmail, constantsPresenter.titrePopupEnvoiEmail(),
                    ((ComposantEnvoiEmailView) composantEnvoiEmailPresenter.asView()).getBtnReduireHandler()));
            }
        });
    }

    /**
     * .
     * @param devis .
     * @param referenceOpportunite .
     * @param listeIdsLignesSelectionnees .
     * @param emailPersonneEnCours .
     */
    public void afficherPourEnvoi(DevisModel devis, Long referenceOpportunite, List<Long> listeIdsLignesSelectionnees, String emailPersonneEnCours) {
        if (devis.getId() != this.devis.getId()) {
            this.devis = devis;
            getBeneficiairesFromProspectByLigneDevis();
        } else {
            view.chargerBeneficiaires(listeBeneficiaires);
        }

        this.referenceOpportunite = referenceOpportunite;
        this.listeIdsLignesSelectionnees = listeIdsLignesSelectionnees;
        this.emailPersonneEnCours = emailPersonneEnCours;

        view.chargerModelesDevis(listeModelesDevis);
        view.showPopup();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
