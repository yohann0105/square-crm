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
package com.square.composant.contrat.personne.morale.square.client.presenter;

import java.util.Iterator;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.square.composant.contrat.personne.morale.square.client.content.i18n.ComposantContratPersonneMoraleConstants;
import com.square.composant.contrat.personne.morale.square.client.model.ConstantesModel;
import com.square.composant.contrat.personne.morale.square.client.model.ContratPersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.model.GarantiePersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.model.RecapitulatifPopulationModel;
import com.square.composant.contrat.personne.morale.square.client.service.ContratServiceGwtAsync;

/**
 * Presenter du contenu d'un contrat.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ContratPersonneMoraleContenuPresenter extends Presenter {

    /** Vue du presenter. */
    private ContratPersonneMoraleContenuView view;

    /** Service contrat. */
    private ContratServiceGwtAsync contratServiceRpc;

    /** Constantes de l'application. */
    private ConstantesModel constantesApp;

    /** Identifiant du contrat. */
    private Long idContrat;

    /** Constantes du presenter. */
    private ContratPMContenuPresenterConstants constantesPresenter;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènements
     * @param view la vue
     * @param contratServiceGwtAsync le service des contrats
     * @param constantesApp les constantes de l'application
     * @param idContrat l'identifiant du contrat
     */
    public ContratPersonneMoraleContenuPresenter(HandlerManager eventBus, ContratPersonneMoraleContenuView view, ContratServiceGwtAsync contratServiceGwtAsync,
        ConstantesModel constantesApp, Long idContrat) {
        super(eventBus);
        this.view = view;
        this.contratServiceRpc = contratServiceGwtAsync;
        this.constantesApp = constantesApp;
        this.idContrat = idContrat;
        this.constantesPresenter = (ContratPMContenuPresenterConstants) GWT.create(ContratPMContenuPresenterConstants.class);
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {

    }

    @Override
    public void onShow(HasWidgets container) {
        // Chargement du contrat
        chargerContrat();
        container.add(view.asWidget());
    }

    /** Charge le contrat. */
    private void chargerContrat() {
        view.afficherLoadingPopup(new LoadingPopupConfiguration(constantesPresenter.chargementContrat()));
        // Création du callback
        final AsyncCallback<ContratPersonneMoraleModel> asyncCallback = new AsyncCallback<ContratPersonneMoraleModel>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(ContratPersonneMoraleModel result) {
                view.onRpcServiceSuccess();
                // Rempli le contrat
                remplirContrat(result);
                // Si certains produits n'ont pas été trouvés, on les affiche
                final List<String> listeProduitsNonTrouves = result.getListeProduitsNonTrouves();
                if (!listeProduitsNonTrouves.isEmpty()) {
                    final StringBuffer message = new StringBuffer(constantesPresenter.msgErreurProduitsNonTrouves() + "<br/>");
                    for (final Iterator<String> it = listeProduitsNonTrouves.iterator(); it.hasNext(); ) {
                        final String libelleProduitNonTrouve = it.next();
                        message.append(libelleProduitNonTrouve);
                        if (it.hasNext()) {
                            message.append("<br/>");
                        }
                    }
                    final ErrorPopupConfiguration config = new ErrorPopupConfiguration(message.toString());
                    view.afficherErrorPopup(config);
                }
            }
        };
        contratServiceRpc.getContratPersonneMorale(idContrat, asyncCallback);
    }

    /**
     * Remplit le contrat dans la vue.
     * @param contrat le contrat.
     */
    private void remplirContrat(ContratPersonneMoraleModel contrat) {
        if (contrat != null) {
            view.chargerRecapitulatifPopulation(contrat.getRecapitulatifPopulation());
            if (contrat.getSegment() != null) {
                view.getLibelleSegment().setText(contrat.getSegment().getLibelle());
            }
            if (contrat.getTypeGestion() != null) {
                view.getLibelleTypeGestion().setText(contrat.getTypeGestion().getLibelle());
            }
            view.getLibelleTypeSouscription().setText(contrat.getTypeSouscription());
            view.getLibelleProduitGestion().setText(contrat.getProduitGestion());
            view.getLibelleApporteur().setText(contrat.getApporteur());
            if (contrat.getNbAdherents() != null) {
                view.getLibelleNbAdherents().setText(contrat.getNbAdherents().toString());
            }
            else {
                view.getLibelleNbAdherents().setText("0");
            }
            if (contrat.getNbBeneficiaires() != null) {
                view.getLibelleNbBeneficiaires().setText(contrat.getNbBeneficiaires().toString());
            }
            else {
                view.getLibelleNbBeneficiaires().setText("0");
            }
            if (contrat.getTypeGestion() != null && (constantesApp.getIdTypeGestionSouscripteur().equals(contrat.getTypeGestion().getIdentifiant())
                    || constantesApp.getIdTypeGestionMultipart().equals(contrat.getTypeGestion().getIdentifiant()))) {
                if (contrat.getInfosPaiement() != null) {
                    if (contrat.getInfosPaiement().getMoyenPaiementCotis() != null) {
                        view.getLibelleModePaiement().setText(contrat.getInfosPaiement().getMoyenPaiementCotis().getLibelle());
                    }
                    if (contrat.getInfosPaiement().getFrequencePaiementCotis() != null) {
                        view.getLibellePeriodePaiement().setText(contrat.getInfosPaiement().getFrequencePaiementCotis().getLibelle());
                    }
                    if (contrat.getInfosPaiement().getJourPaiement() != null) {
                        view.getLibelleJourPaiement().setText(contrat.getInfosPaiement().getJourPaiement().toString());
                    }
                    view.getLibelleTypeEcheance().setText(contrat.getInfosPaiement().getTypeEcheance());
                    view.getLibelleCodeBanque().setText(contrat.getInfosPaiement().getCodeBanque());
                    view.getLibelleCodeGuichet().setText(contrat.getInfosPaiement().getCodeGuichet());
                    view.getLibelleCodeCompte().setText(contrat.getInfosPaiement().getCodeCompte());
                    view.getLibelleCleRIB().setText(contrat.getInfosPaiement().getCle());
                    view.setPanelPaiementCotisationVisible(true);
                }
            }

            if (contrat.getListeGaranties() != null && contrat.getListeGaranties().size() > 0) {
                view.construireBlocGaranties();
                for (final GarantiePersonneMoraleModel garantie : contrat.getListeGaranties()) {
                    final HasClickHandlers elementCliquable = view.ajouterGarantie(garantie);
                    // Affichage de la grille de prestation sur le clic si la garantie possède une grille de presta
                    if (garantie.getPossedeGrillePresta() != null && garantie.getPossedeGrillePresta() && elementCliquable != null) {
                        elementCliquable.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent event) {
                                voirGrillePrestations(garantie.getIdProduit());
                            }
                        });
                    }
                }
            }
        }
    }

    /**
     * Affiche la grille de prestations en PDF.
     * @param identifiantProduit l'identifiant du produit
     */
    private void voirGrillePrestations(Integer idProduit) {
        final String appelServlet =
            ComposantContratPersonneMoraleConstants.URL_SERVLET_GRILLE_PRESTA_PDF + "?"
            + ComposantContratPersonneMoraleConstants.PARAM_ID_PRODUIT + "=" + idProduit;
        view.voirGrillePrestations(appelServlet);
    }

    /**
     * Vue pour le contenu principal d'un contrat.
     */
    public interface ContratPersonneMoraleContenuView extends View {

        /**
         * Charge le récapitulatif des populations.
         * @param recapitulatifPopulation le récapitulatif à charger.
         */
        void chargerRecapitulatifPopulation(RecapitulatifPopulationModel recapitulatifPopulation);

        /**
         * Accesseur du libellé du segment.
         * @return le libellé.
         */
        HasText getLibelleSegment();
        /**
         * Accesseur du libellé du type de gestion.
         * @return le libellé.
         */
        HasText getLibelleTypeGestion();

        /**
         * Accesseur du libellé du type de souscription.
         * @return le libellé.
         */
        HasText getLibelleTypeSouscription();

        /**
         * Accesseur du libellé du produit de gestion.
         * @return le libellé.
         */
        HasText getLibelleProduitGestion();

        /**
         * Accesseur du libellé de l'apporteur.
         * @return le libellé.
         */
        HasText getLibelleApporteur();

        /**
         * Accesseur du libellé du nombre d'adhérents.
         * @return le libellé.
         */
        HasText getLibelleNbAdherents();

        /**
         * Accesseur du libellé du nombre de bénéficiaires.
         * @return le libellé.
         */
        HasText getLibelleNbBeneficiaires();

        /**
         * Accesseur du libellé du mode de paiement.
         * @return le libellé.
         */
        HasText getLibelleModePaiement();

        /**
         * Accesseur du libellé de la période de paiement.
         * @return le libellé.
         */
        HasText getLibellePeriodePaiement();

        /**
         * Accesseur du libellé du jour de paiement.
         * @return le libellé.
         */
        HasText getLibelleJourPaiement();

        /**
         * Accesseur du libellé du type d'échéance.
         * @return le libellé.
         */
        HasText getLibelleTypeEcheance();

        /**
         * Accesseur du libellé du code de la banque.
         * @return le libellé.
         */
        HasText getLibelleCodeBanque();

        /**
         * Accesseur du libellé du code guichet.
         * @return le libellé.
         */
        HasText getLibelleCodeGuichet();

        /**
         * Accesseur du libellé du code du compte.
         * @return le libellé.
         */
        HasText getLibelleCodeCompte();

        /**
         * Accesseur du libellé de la clé du RIB.
         * @return le libellé.
         */
        HasText getLibelleCleRIB();

        /**
         * Rend visible / invisible le panel des informations de paiement de cotisation.
         * @param isVisible true si visible, false si invisible.
         */
        void setPanelPaiementCotisationVisible(boolean isVisible);

        /** Construit le bloc des garanties. */
        void construireBlocGaranties();

        /**
         * Ajoute une garantie dans le panel des garanties.
         * @param garantie la garantie à charger.
         * @return l'élément cliquable permettant de télécharger la grille, null ne possède pas de grille de presta.
         */
        HasClickHandlers ajouterGarantie(GarantiePersonneMoraleModel garantie);

        /** Fermer la popup de chargement. */
        void onRpcServiceSuccess();

        /**
         * Popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Affiche une popup de chargement.
         * @param loadingPopupConfiguration la configuration
         */
        void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration);

        /**
         * Affiche une popup d'erreur.
         * @param config la configuration de la popup d'erreur.
         */
        void afficherErrorPopup(ErrorPopupConfiguration config);

        /**
         * Appel la servlet de grilles de prestations.
         * @param url l'url.
         */
        void voirGrillePrestations(String url);

    }

    /**
     * Vue pour une garantie.
     */
    public interface ContratGarantiePersonneMoraleView extends View {

        /**
         * Affiche l'icone du pdf.
         * @param iconePdf l'icone à afficher.
         */
        void afficherIconePdf(Image iconePdf);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
