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
package com.square.composant.contrat.square.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.square.composant.contrat.square.client.bundle.ComposantContratResources;
import com.square.composant.contrat.square.client.model.AjustementTarifModel;
import com.square.composant.contrat.square.client.model.ConstantesModel;
import com.square.composant.contrat.square.client.model.ContratModel;
import com.square.composant.contrat.square.client.model.GarantieBeneficiaireModel;
import com.square.composant.contrat.square.client.model.GarantieModel;
import com.square.composant.contrat.square.client.model.GarantieSimpleModel;
import com.square.composant.contrat.square.client.model.InfosGarantieBeneficiaireModel;
import com.square.composant.contrat.square.client.model.RecapitulatifGarantiesContratModel;
import com.square.composant.contrat.square.client.service.ContratServiceGwtAsync;
import com.square.composant.contrat.square.client.util.FormatServletUtil;
import com.square.composant.contrat.square.client.util.FormatServletUtil.ParamServlet;
import com.square.composant.contrat.square.client.view.ContratContenuViewImplConstants;
import com.square.composant.contrat.square.client.view.ContratsViewImplConstants;

/**
 * Presenter du contenu d'un contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratContenuPresenter extends Presenter {

	/** Constantes du presenter. */
	private ContratContenuPresenterConstants presenterConstants;

    /** Vue du presenter. */
    private ContratContenuView view;

    /** Service contrat. */
    private ContratServiceGwtAsync contratServiceRpc;

    /** Constantes de l'application. */
    private ConstantesModel constantesApp;

    /** Identifiant du contrat. */
    private Long idContrat;

    /** Ressources. */
    private ComposantContratResources resources;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènements
     * @param view la vue
     * @param contratServiceGwtAsync le service des contrats
     * @param constantesApp les constantes de l'application
     * @param idContrat l'identifiant du contrat
     */
    public ContratContenuPresenter(HandlerManager eventBus, ContratContenuView view, ContratServiceGwtAsync contratServiceGwtAsync,
        ConstantesModel constantesApp, Long idContrat) {
        super(eventBus);
        this.presenterConstants = (ContratContenuPresenterConstants) GWT.create(ContratContenuPresenterConstants.class);
        this.view = view;
        this.contratServiceRpc = contratServiceGwtAsync;
        this.constantesApp = constantesApp;
        this.idContrat = idContrat;
        this.resources = (ComposantContratResources) GWT.create(ComposantContratResources.class);
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
        container.add(view.asWidget());
    }

    /** Charge le contrat. */
    public void chargerContrat() {
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().chargementContrat()));
        // Création du callback
        final AsyncCallback<ContratModel> asyncCallback = new AsyncCallback<ContratModel>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(ContratModel result) {
                view.onRpcServiceSuccess();
                // Chargement du contrat
                chargerContrat(result);
            }
        };
        contratServiceRpc.getContrat(idContrat, asyncCallback);
    }

    /**
     * Charge le contrat dans la vue.
     * @param contrat le contrat
     */
    private void chargerContrat(ContratModel contrat) {
        if (contrat != null) {
            view.nettoyer();
            // Affichage du récapitulatif des garanties
            chargerRecapitulatifGaranties(contrat.getRecapitulatifGarantiesContrat());

            // Affichage de l'apporteur
            final String libelleApporteur = contrat.getApporteur();
            if (libelleApporteur != null && !"".equals(libelleApporteur.trim())) {
                view.getLabelApporteur().setText(libelleApporteur);
            }
            // Affichage du gestionnaire
            final String libelleGestionnaire = contrat.getGestionnaire();
            if (libelleGestionnaire != null && !"".equals(libelleGestionnaire.trim())) {
                view.getLabelGestionnaire().setText(libelleGestionnaire);
            }

            // Affichage des infos de paiement de prestation
            final boolean segmentCollectif =
                contrat.getSegment() != null
                    && contrat.getSegment().getIdentifiant() != null
                    && (constantesApp.getIdSegmentCollectif().equals(contrat.getSegment().getIdentifiant()) || constantesApp
                            .getIdSegmentCollectifIndividualise().equals(contrat.getSegment().getIdentifiant())) && contrat.getTypePayeur() != null
                    && contrat.getTypePayeur().getIdentifiant() != null
                    && constantesApp.getIdTypePayeurSouscripteur().equals(contrat.getTypePayeur().getIdentifiant());
            boolean afficherJourPaiementCotis = true;
            if (contrat.getInfosPaiementCotisation() != null && contrat.getInfosPaiementCotisation().getMoyenPaiement() != null
                    && contrat.getInfosPaiementCotisation().getMoyenPaiement().getIdentifiant() != null
                    && constantesApp.getListeIdsMoyenPaiementCotisSansJour().contains(
                        contrat.getInfosPaiementCotisation().getMoyenPaiement().getIdentifiant())) {
                afficherJourPaiementCotis = false;
            }
            view.chargerBlocInfosPaiement(contrat, segmentCollectif, afficherJourPaiementCotis);

            // Chargement de la liste des ajustements
            if (contrat.getListeAjustements() != null && contrat.getListeAjustements().size() > 0) {
                view.chargerGarantieAjustement(contrat.getListeAjustements());
            }
            // Ajout des garanties
            chargerListeGaranties(contrat.getListeGaranties());
        }
    }

    /**
     * Charge le récapitulatif des garanties.
     * @param recapitulatifGaranties l'objet contenant les infos du récapitulatif
     */
    private void chargerRecapitulatifGaranties(RecapitulatifGarantiesContratModel recapitulatifGaranties) {
        if (recapitulatifGaranties != null && recapitulatifGaranties.getListeBeneficiaires() != null
            && recapitulatifGaranties.getListeBeneficiaires().size() > 0 && recapitulatifGaranties.getListeGaranties() != null
            && recapitulatifGaranties.getListeGaranties().size() > 0) {
            final Map<Long, Integer> mapIndexBeneficiaires = new HashMap<Long, Integer>();
            // Ajout des bénéficiaires et rôles
            if (recapitulatifGaranties != null) {
                if (recapitulatifGaranties.getListeBeneficiaires() != null) {
                    final List<String> listeBeneficiaires = new ArrayList<String>();
                    final List<String> listeRoles = new ArrayList<String>();
                    for (int idxBeneficiaire = 0; idxBeneficiaire < recapitulatifGaranties.getListeBeneficiaires().size(); idxBeneficiaire++) {
                        final GarantieBeneficiaireModel beneficiaire = recapitulatifGaranties.getListeBeneficiaires().get(idxBeneficiaire);
                        // Construction du libellé de la personne
                        final StringBuffer nomPrenom = new StringBuffer("");
                        if (beneficiaire.getNom() != null && !"".equals(beneficiaire.getNom().trim())) {
                        	String nomLimite = beneficiaire.getNom();
                        	if (nomLimite.length() > presenterConstants.nbMaxCaracteresNom()) {
                        		nomLimite = nomLimite.substring(0, presenterConstants.nbMaxCaracteresNom()) + "...";
                        	}
                            nomPrenom.append(nomLimite);
                        }
                        if (beneficiaire.getPrenom() != null && !"".equals(beneficiaire.getPrenom().trim())) {
                            if (nomPrenom.length() > 0) {
                                nomPrenom.append("<br/>");
                            }
                            String prenomLimite = beneficiaire.getPrenom();
                            if (prenomLimite.length() > presenterConstants.nbMaxCaracteresPrenom()) {
                            	prenomLimite = prenomLimite.substring(0, presenterConstants.nbMaxCaracteresPrenom()) + "...";
                            }
                            nomPrenom.append(prenomLimite);
                        }
                        listeBeneficiaires.add(nomPrenom.toString());
                        // Rôle
                        if (beneficiaire.getRole() != null && beneficiaire.getRole().getLibelle() != null) {
                            listeRoles.add(beneficiaire.getRole().getLibelle());
                        }
                        mapIndexBeneficiaires.put(beneficiaire.getIdBenef(), Integer.valueOf(idxBeneficiaire));
                    }
                    view.remplirBeneficiaireRoleRecapGaranties(listeBeneficiaires, listeRoles);
                }

                // Ajout des garanties
                if (recapitulatifGaranties.getListeGaranties() != null) {
                    for (final GarantieSimpleModel garantie : recapitulatifGaranties.getListeGaranties()) {
                        String libelleGarantie = "";
                        if (garantie.getLibelle() != null) {
                            libelleGarantie = garantie.getLibelle();
                        }
                        // Récupération des valeurs par bénéficiaires
                        final InfosGarantieBeneficiaireModel[] tabValeurs =
                            new InfosGarantieBeneficiaireModel[recapitulatifGaranties.getListeBeneficiaires().size()];
                        if (garantie.getListeInfosGarantiesBeneficiaires() != null) {
                            for (InfosGarantieBeneficiaireModel valeurGarantieBeneficiaire : garantie.getListeInfosGarantiesBeneficiaires()) {
                                tabValeurs[mapIndexBeneficiaires.get(valeurGarantieBeneficiaire.getIdBeneficiaire())] = valeurGarantieBeneficiaire;
                            }
                        }
                        final List<InfosGarantieBeneficiaireModel> listeValeurs = new ArrayList<InfosGarantieBeneficiaireModel>();
                        for (InfosGarantieBeneficiaireModel valeur : tabValeurs) {
                            if (valeur != null) {
                                listeValeurs.add(valeur);
                            }
                            else {
                                final InfosGarantieBeneficiaireModel infoVide = new InfosGarantieBeneficiaireModel();
                                listeValeurs.add(infoVide);
                            }
                        }
                        final Boolean isSegmentCollectif = constantesApp.getIdSegmentCollectif().equals(garantie.getSegment().getIdentifiant());
                        final HasClickHandlers elementCliquable =
                            view.ajouterGarantieEtValeursRecapGaranties(libelleGarantie, garantie.isPossedeGrillePresta(), listeValeurs);
                        // Affichage de la grille de prestation sur le clic si la garantie possède une grille de presta
                        if (garantie.isPossedeGrillePresta() && elementCliquable != null) {
                            elementCliquable.addClickHandler(new ClickHandler() {
                                @Override
                                public void onClick(ClickEvent event) {
                                    voirGrillePrestations(garantie.getIdProduit(), isSegmentCollectif);
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    /**
     * Charge la liste des garanties du contrat.
     * @param listeGaranties la liste des garanties
     */
    private void chargerListeGaranties(List<GarantieModel> listeGaranties) {
        if (listeGaranties != null) {
            view.construireBlocGarantie();

            final String couleurFondGarantieEnCours = resources.css().couleurFondGarantieEnCours();
            final String couleurFondGarantieResiliee = resources.css().couleurFondGarantieResiliee();
            final String couleurFondGarantieFutur = resources.css().couleurFondGarantieFutur();
            for (GarantieModel garantie : listeGaranties) {
                String style = null;
                if (garantie.getStatut() != null && garantie.getStatut().getIdentifiant() != null) {
                    if (constantesApp.getIdStatutGarantieEnCours().equals(garantie.getStatut().getIdentifiant())) {
                        style = couleurFondGarantieEnCours;
                    }
                    else if (constantesApp.getIdStatutGarantieResiliee().equals(garantie.getStatut().getIdentifiant())) {
                        style = couleurFondGarantieResiliee;
                    }
                    else if (constantesApp.getIdStatutGarantieFutur().equals(garantie.getStatut().getIdentifiant())) {
                        style = couleurFondGarantieFutur;
                    }
                }
                view.ajouterGarantie(garantie, style);
            }
        }
    }

    /**
     * Affiche la grille de prestations en PDF.
     * @param identifiantProduit l'identifiant du produit.
     * @param isCollectif indique si le segment est collectif ou non.
     */
    private void voirGrillePrestations(Integer idProduit, Boolean isCollectif) {
        // Définition des paramètres de la servlet
        final ParamServlet[] params =
            new ParamServlet[] {FormatServletUtil.getIntanceParamServlet(ContratsViewImplConstants.PARAM_ID_PRODUIT, idProduit.toString()),
                FormatServletUtil.getIntanceParamServlet(ContratsViewImplConstants.PARAM_IS_COLLECTIF, isCollectif.toString())};

        final String url = FormatServletUtil.formatUrl(ContratsViewImplConstants.URL_SERVLET_GRILLE_PRESTA_PDF, params);
        view.voirGrillePrestations(url);
    }

    /**
     * Vue pour le contenu principal d'un contrat.
     */
    public interface ContratContenuView extends View {

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
         * Renvoi la valeur de viewConstants.
         * @return viewConstants
         */
        ContratContenuViewImplConstants getViewConstants();

        /**
         * Remplit la liste des bénéficiaires et des rôles pour le récapitulatif des garanties.
         * @param listeBeneficiaires la liste des bénéficiaires
         * @param listeRoles la liste des rôles
         */
        void remplirBeneficiaireRoleRecapGaranties(List<String> listeBeneficiaires, List<String> listeRoles);

        /**
         * Remplit le libellé et les valeurs des garanties.
         * @param libelleGarantie le libellé de la garantie
         * @param possedeGrillePresta flag indiquant que la garantie possède une grille de prestation pour permettre de la télécharger
         * @param listeValeurs la liste des valeurs de la garantie pour chaque bénéficiaire dans l'ordre des bénéficiaires
         * @return l'élément cliquable permettant de télécharger la grille, null ne possède pas de grille de presta
         */
        HasClickHandlers ajouterGarantieEtValeursRecapGaranties(String libelleGarantie, boolean possedeGrillePresta,
            List<InfosGarantieBeneficiaireModel> listeValeurs);

        /**
         * Récupère le label de l'apporteur du contrat.
         * @return le label
         */
        HasText getLabelApporteur();

        /**
         * Récupère le label de du gestionnaire du contrat.
         * @return le label
         */
        HasText getLabelGestionnaire();

        /**
         * Construit le bloc des garanties.
         */
        void construireBlocGarantie();

        /**
         * Ajoute une garantie au contenu du contrat.
         * @param garantie la garantie
         * @param style le style
         */
        void ajouterGarantie(GarantieModel garantie, String style);

        /**
         * Appel la servlet de grilles de prestations.
         * @param url l'url
         */
        void voirGrillePrestations(String url);

        /**
         * Charge les ajustements.
         * @param listesAjustements listesAjustements
         */
        void chargerGarantieAjustement(final List<AjustementTarifModel> listesAjustements);

        /**
         * Charge les infos de paiement.
         * @param contrat le contrat
         * @param segmentCollectif segment collectif
         * @param afficherJourPaiementCotis afficherJourPaiementCotis
         */
        void chargerBlocInfosPaiement(final ContratModel contrat, boolean segmentCollectif, boolean afficherJourPaiementCotis);

        /**
         * Nettoie tout.
         */
        void nettoyer();
    }

    /**
     * Vue pour une garantie.
     */
    public interface ContratGarantieView extends View {
    }

    /**
     * Vue pour le contenu d'un ajustement.
     */
    public interface ContratGarantieAjustementView extends View {

        /**
         * Récupère le label Référence.
         * @return le label.
         */
        HasText getLabelReference();

        /**
         * Récupère le label Libellé.
         * @return le label.
         */
        HasText getLabelLibelle();

        /**
         * Récupère le label Tarif.
         * @return le label.
         */
        HasText getLabelTarif();

        /**
         * Récupère le label Date de début.
         * @return le label.
         */
        HasText getLabelDateDebut();

        /**
         * Récupère le label Date de fin.
         * @return le label.
         */
        HasText getLabelDateFin();
    }

    @Override
    public void onDetach() {
    }

}
