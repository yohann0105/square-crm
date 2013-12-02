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

import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.exception.BusinessExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.CritereModeleDevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModificationModel;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwtAsync;
import com.square.composant.tarificateur.square.client.util.FormatServletUtil;
import com.square.composant.tarificateur.square.client.util.FormatServletUtil.ParamServlet;

/**
 * Presenter de la popup d'impression de devis.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupImpressionDevisPresenter extends Presenter {

    private PopupChoixModelesView view;

    private ConstantesModel constantesApp;

    private TarificateurServiceGwtAsync tarificateurRpcService;

    private DevisModel devis;

    private DevisModificationModel devisModificationModel;

    private Long referenceOpportunite;

    private PopupEnvoiDevisParMailPresenterConstants constants;

    private List<Long> listeIdsLignesSelectionnees;

    private ComposantTarificateurConstants composantTarificateurConstants;

    /**
     * Constructeur du présenter.
     * @param eventBus le bus.
     * @param vue la vue.
     * @param constantesApp les constantes de l'application.
     * @param tarificateurRpcService service rpc du tarificateur.
     * @param devis l'identifiant du devis sélectionné.
     * @param devisModificationModel informations à enregistrer avant l'impression
     * @param referenceOpportunite la référence de l'opportunité.
     * @param listeIdsLignesSelectionnees la liste des identifiants des lignes de devis sélectionnées.
     */
    public PopupImpressionDevisPresenter(HandlerManager eventBus, PopupChoixModelesView vue, ConstantesModel constantesApp,
        TarificateurServiceGwtAsync tarificateurRpcService, DevisModel devis, DevisModificationModel devisModificationModel, Long referenceOpportunite,
        List<Long> listeIdsLignesSelectionnees) {
        super(eventBus);
        this.view = vue;
        this.constantesApp = constantesApp;
        this.tarificateurRpcService = tarificateurRpcService;
        this.devis = devis;
        this.devisModificationModel = devisModificationModel;
        this.referenceOpportunite = referenceOpportunite;
        this.listeIdsLignesSelectionnees = listeIdsLignesSelectionnees;
        this.constants = GWT.create(PopupEnvoiDevisParMailPresenterConstants.class);
        this.composantTarificateurConstants = GWT.create(ComposantTarificateurConstants.class);
        // Récupération des modèles de devis pour l'envoi par email
        getModelesDevisPourEnvoiMail();

        // Récupération des éventuels bénéficiaires
        getBeneficiairesFromProspectByLigneDevis();
    }

    /** Récupère les modèles de devis pour l'envoi par email. */
    private void getModelesDevisPourEnvoiMail() {
        final CritereModeleDevisModel critere = new CritereModeleDevisModel();
        critere.setIdDevis(devis.getId());
        final List<Long> listeIdModeles = new ArrayList<Long>();
        listeIdModeles.add(constantesApp.getIdModeleDevisBulletinAdhesion());
        listeIdModeles.add(constantesApp.getIdModeleDevisFicheTransfert());
        listeIdModeles.add(constantesApp.getIdModeleLettreAnnulation());
        listeIdModeles.add(constantesApp.getIdModeleLettreRadiation());
        listeIdModeles.add(constantesApp.getIdModeleLettreRadiationLoiChatel());
        critere.setListeIdsModeles(listeIdModeles);
        final AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback = new AsyncCallback<List<IdentifiantLibelleGwt>>() {
            @Override
            public void onSuccess(List<IdentifiantLibelleGwt> result) {
                view.chargerModelesDevis(result);

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
                view.chargerBeneficiaires(result);
            }
        };
        tarificateurRpcService.getBeneficiairesFromProspectByDevis(devis, listeIdsLignesSelectionnees, asyncCallback);
    }

    /**
     * Enregistre le devis avant l'impression.
     */
    private void enregistrerDevis() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(constants.enregistrementDevisImpression()));
        // Appel du service
        tarificateurRpcService.modifierDevis(devisModificationModel, new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            public void onSuccess(Void object) {
                // On lance l'impression une fois le devis enregistré
                imprimerDevis();
            }
        });
    }

    /**
     * Lance l'impression des modèles de devis.
     */
    private void imprimerDevis() {
        // Récupération des choix des documents
        final List<Long> listeIdsModelesSelectionnes = view.getIdsModelesSelectionnes();

        // Récupération des bénéficiaires
        final List<Long> listeIdsBeneficiairesSelectionnes = view.getIdsBeneficiairesSelectionnes();

        // Construction du paramètre des identifiants des lignes de devis sélectionnées
        String idsLignesDevis = "";
        if (listeIdsLignesSelectionnees != null && listeIdsLignesSelectionnees.size() > 0) {
            idsLignesDevis = listeIdsLignesSelectionnees.get(0).toString();
            for (int i = 1; i < listeIdsLignesSelectionnees.size(); i++) {
                idsLignesDevis = idsLignesDevis + ComposantTarificateurConstants.SEPARATOR_IDENTIFIANTS + listeIdsLignesSelectionnees.get(i).toString();
            }
        }

        // Construction du paramètre des identifiants des modèles de devis sélectionnés
        String idsModelesDevis = "";
        if (listeIdsModelesSelectionnes != null && listeIdsModelesSelectionnes.size() > 0) {
            idsModelesDevis = listeIdsModelesSelectionnes.get(0).toString();
            for (int i = 1; i < listeIdsModelesSelectionnes.size(); i++) {
                idsModelesDevis = idsModelesDevis + ComposantTarificateurConstants.SEPARATOR_IDENTIFIANTS + listeIdsModelesSelectionnes.get(i).toString();
            }
        }

        // Construction du paramètre des identifiants des bénéficiaires sélectionnés
        String idsBeneficiaires = "";
        if (listeIdsBeneficiairesSelectionnes != null && listeIdsBeneficiairesSelectionnes.size() > 0) {
            idsBeneficiaires = listeIdsBeneficiairesSelectionnes.get(0).toString();
            for (int i = 1; i < listeIdsBeneficiairesSelectionnes.size(); i++) {
                idsBeneficiaires =
                    idsBeneficiaires + ComposantTarificateurConstants.SEPARATOR_IDENTIFIANTS + listeIdsBeneficiairesSelectionnes.get(i).toString();
            }
        }

        // On prépare les fichiers à imprimer
        tarificateurRpcService.construireFichiersDevisAImprimer(devis.getId(), idsLignesDevis, idsModelesDevis, idsBeneficiaires, referenceOpportunite
                .toString(), new AsyncCallback<Long>() {
            public void onSuccess(Long idImpression) {
                // Définition des paramètres de la servlet
                final ParamServlet[] params =
                    new ParamServlet[] {FormatServletUtil.getIntanceParamServlet(ComposantTarificateurConstants.PARAM_ID_IMPRESSION, Long
                            .toString(idImpression))};

                final String url = FormatServletUtil.formatUrl(ComposantTarificateurConstants.URL_IMPRIMER_DEVIS, params);
                Window.open(url, "_blank", "resizable=yes,menubar=no,location=no");

                // Event pas utilisé
                // fireEventLocalBus(new SuccesImpressionEvent());
                LoadingPopup.stop();
                view.hidePopup();
            }

            public void onFailure(Throwable caught) {
            	if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_LIGNE_DEVIS_PRINT_ABSENT)) {
            		view.onRpcServiceFailure(new ErrorPopupConfiguration(new BusinessExceptionGwt(composantTarificateurConstants.errorLigneDevisAbsent())));
            	} else if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_MODELE_DEVIS_ABSENT)) {
            		view.onRpcServiceFailure(new ErrorPopupConfiguration(new BusinessExceptionGwt(
            				composantTarificateurConstants.errorModeleDevisAbsent())));
            	} else if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_USER_ABSENT)) {
            		view.onRpcServiceFailure(new ErrorPopupConfiguration(new BusinessExceptionGwt(composantTarificateurConstants.errorUserAbsent())));
            	} else {
            		view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            	}
            }
        });
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
                    // On enregistre le devis puis on lance l'impression
                    enregistrerDevis();
                } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    view.hidePopup();
                }
            }
        });
        view.getBtnValider().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // On enregistre le devis puis on lance l'impression
                enregistrerDevis();
            }
        });
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
            }
        });
    }
    /**
     * Demande d'impression du devis.
     * @param newDevis newDevis
     * @param newDevisModificationModel newDevisModificationModel
     * @param newReferenceOpportunite newReferenceOpportunite
     * @param newListeIdsLignesSelectionnees newListeIdsLignesSelectionnees
     */
    public void demanderImpression(DevisModel newDevis, DevisModificationModel newDevisModificationModel, Long newReferenceOpportunite,
        List<Long> newListeIdsLignesSelectionnees) {
        this.devis = newDevis;
        this.devisModificationModel = newDevisModificationModel;
        this.referenceOpportunite = newReferenceOpportunite;
        this.listeIdsLignesSelectionnees = newListeIdsLignesSelectionnees;
        view.nettoyer();
        view.initFocus();
        view.showPopup();
    }

    @Override
    public void onShow(HasWidgets container) {
        view.showPopup();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
