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

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.square.composant.contrat.personne.morale.square.client.bundle.ComposantContratPersonneMoraleResources;
import com.square.composant.contrat.personne.morale.square.client.event.ContratsPersonneMoraleChargesEvent;
import com.square.composant.contrat.personne.morale.square.client.model.ConstantesModel;
import com.square.composant.contrat.personne.morale.square.client.model.ContratSimplePersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.model.InfosContratsPersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.model.PopulationModel;
import com.square.composant.contrat.personne.morale.square.client.model.SyntheseContratPersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.presenter.ContratPersonneMoraleContenuPresenter.ContratPersonneMoraleContenuView;
import com.square.composant.contrat.personne.morale.square.client.service.ConstantesServiceGwt;
import com.square.composant.contrat.personne.morale.square.client.service.ConstantesServiceGwtAsync;
import com.square.composant.contrat.personne.morale.square.client.service.ContratServiceGwt;
import com.square.composant.contrat.personne.morale.square.client.service.ContratServiceGwtAsync;
import com.square.composants.graphiques.lib.client.event.HasOpenBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEventHandler;

/**
 * Presenter des contrats d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ContratsPersonneMoralePresenter extends Presenter {

    /** Instance des ressources. */
    public static final ComposantContratPersonneMoraleResources RESSOURCES = GWT.create(ComposantContratPersonneMoraleResources.class);

    /** Vue du presenter. */
    private ContratsPersonneMoraleView view;

    /** L'identifiant de la personne morale. */
    private Long idPersonneMorale;

    /** Constantes de l'application. */
    private ConstantesModel constantesApp;

    /** Service sur les contrats. */
    private ContratServiceGwtAsync contratServiceRpc;

    /** Service sur les constantes. */
    private ConstantesServiceGwtAsync constantesServiceRpc;

    /** Constantes du presenter. */
    private ContratsPersonneMoralePresenterConstants presenterConstants;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènements
     * @param view la vue
     * @param idPersonneMorale l'identifiant de la personne morale
     */
    public ContratsPersonneMoralePresenter(HandlerManager eventBus, ContratsPersonneMoraleView view, Long idPersonneMorale) {
        super(eventBus);
        this.view = view;
        this.presenterConstants = GWT.create(ContratsPersonneMoralePresenterConstants.class);
        this.contratServiceRpc = GWT.create(ContratServiceGwt.class);
        this.constantesServiceRpc = GWT.create(ConstantesServiceGwt.class);
        this.idPersonneMorale = idPersonneMorale;
        StyleInjector.inject(ContratsPersonneMoralePresenter.RESSOURCES.css().getText());
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
        // Chargement des constantes
        chargerConstantes();
        container.add(view.asWidget());
    }

    /** Charge les constantes de l'application. */
    private void chargerConstantes() {
        // Création du callback
        final AsyncCallback<ConstantesModel> asyncCallback = new AsyncCallback<ConstantesModel>() {
            @Override
            public void onSuccess(ConstantesModel result) {
                view.onRpcServiceSuccess();
                constantesApp = result;
                // Chargement des infos des contrats
                chargerInfosContrat();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        constantesServiceRpc.getConstantes(asyncCallback);
    }

    /** Charge les infos des contrats. */
    private void chargerInfosContrat() {
        // Chargement des contrats de la personne
        view.afficherLoadingPopup(new LoadingPopupConfiguration(presenterConstants.chargementListeContrats()));
        // Création du callback
        final AsyncCallback<InfosContratsPersonneMoraleModel> asyncCallback = new AsyncCallback<InfosContratsPersonneMoraleModel>() {
            @Override
            public void onSuccess(final InfosContratsPersonneMoraleModel result) {
                view.onRpcServiceSuccess();
                // Tentative de connexion a internet pour déterminer si la visualisation est disponible.
                final RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "http://www.google.fr");
                rb.setHeader("Access-Control-Allow-Origin", "http://www.google.fr");
                final int timeout = 15000;
                rb.setTimeoutMillis(timeout);
                rb.setCallback(new RequestCallback() {
                    @Override
                    public void onResponseReceived(Request request, Response response) {
                        GWT.log("response : " + response);
                        if (response != null) {
                            final Runnable onLoadCallback = new Runnable() {
                                public void run() {
                                        // Construction du camembert représentant les populations
                                        construireDonneesPopulation(result.getSyntheseContrat().getPopulation());
                                }
                            };
                            VisualizationUtils.loadVisualizationApi(onLoadCallback, "corechart");
                        }
                    }

                    @Override
                    public void onError(Request request, Throwable exception) { }
                });
                try {
                    rb.send();
                } catch (RequestException e) { GWT.log("", e); }

                // Chargement des infos
                initInfosContrats(result);

                final int nbContratsCharges = result.getListeContrats().size();
                fireEventLocalBus(new ContratsPersonneMoraleChargesEvent(nbContratsCharges));
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        contratServiceRpc.getInfosContratPersonneMorale(idPersonneMorale, asyncCallback);
    }

    /**
     * Initialise les informations des contrats d'une personne morale.
     * @param infosContrat les informations des contrats de la personne morale.
     */
    private void initInfosContrats(InfosContratsPersonneMoraleModel infosContrat) {
        final SyntheseContratPersonneMoraleModel syntheseContrats = infosContrat.getSyntheseContrat();
        if (syntheseContrats != null) {
            // Affichage des infos
            view.getLabelStatut().setText(syntheseContrats.getStatut().getLibelle());
            // Modification de la couleur du statut
            if (syntheseContrats.getStatut() != null && syntheseContrats.getStatut().getIdentifiant() != null) {
                String couleurStatut = null;
                if (constantesApp.getIdStatutContratEnCours().equals(syntheseContrats.getStatut().getIdentifiant())) {
                    couleurStatut = ContratsPersonneMoralePresenter.RESSOURCES.css().statutContratEnCours();
                }
                else if (constantesApp.getIdStatutContratResilie().equals(syntheseContrats.getStatut().getIdentifiant())) {
                    couleurStatut = ContratsPersonneMoralePresenter.RESSOURCES.css().statutContratResilie();
                }
                view.modifierCouleurStatut(couleurStatut);
            }
            view.getLabelPremiereMutualisation().setText(syntheseContrats.getDatePremiereMutualisation());
            view.setLabelFidelite(syntheseContrats.getNbAnneesFidelite(), syntheseContrats.getNbMoisFidelite());
            view.getLabelGestionContrat().setText(syntheseContrats.getGestionDuContrat());
            view.getLabelGestionnaire().setText(syntheseContrats.getGestionnaire());
            if (syntheseContrats.getPopulation() != null && syntheseContrats.getPopulation().size() > 0) {
                // Construction du tableau des populations
                view.initTableauPopulation();
                for (PopulationModel population : syntheseContrats.getPopulation()) {
                    view.ajouterLignePopulation(population.getLibelle(), population.getEffectif());
                }
            }
            else {
                view.masquerCamembertPopulation();
            }

            // Affichage des infos de dernière radiation
            if (syntheseContrats.getDateDerniereRadiation() != null) {
                view.getLabelDateDerniereRadiation().setText(syntheseContrats.getDateDerniereRadiation());
                view.getLabelMotifDerniereRadiation().setText(syntheseContrats.getMotifDerniereRadiation());
                view.afficherDerniereRadiation(true);
            }
            else {
                view.afficherDerniereRadiation(false);
            }
        }

        // Chargement de la liste des contrats simples
        chargerListeContratsSimples(infosContrat.getListeContrats());
    }

    /**
     * Charge la liste des contrats simples.
     * @param listeContratsSimples la liste des contrats simples
     */
    private void chargerListeContratsSimples(List<ContratSimplePersonneMoraleModel> listeContratsSimples) {
        if (listeContratsSimples != null) {
            // Parcours des contrats pour ajout
            for (final ContratSimplePersonneMoraleModel contrat : listeContratsSimples) {
                final ContratPersonneMoraleInfosView contratInfosView = view.ajouterContrat(constantesApp, contrat.getDateResiliation() != null);
                // Mise à jour du numéro de contrat
                if (contrat.getNumeroContrat() != null) {
                    contratInfosView.setNumeroContrat(contrat.getNumeroContrat());
                }
                // Remplissage des valeurs
                if (contrat.getStatut() != null && contrat.getStatut().getLibelle() != null) {
                    contratInfosView.getLabelStatut().setText(contrat.getStatut().getLibelle());
                }
                if (contrat.getDateCreation() != null && !"".equals(contrat.getDateCreation())) {
                    contratInfosView.getLabelDateCreation().setText(contrat.getDateCreation());
                }
                if (contrat.getDateEffet() != null && !"".equals(contrat.getDateEffet())) {
                    contratInfosView.getLabelDateEffet().setText(contrat.getDateEffet());
                }
                if (contrat.getDateResiliation() != null && !"".equals(contrat.getDateResiliation())) {
                    contratInfosView.getLabelDateResiliation().setText(contrat.getDateResiliation());
                }
                // Modification de la couleur de l'entête
                if (contrat.getStatut() != null && contrat.getStatut().getIdentifiant() != null) {
                    String couleurEntete = null;
                    if (constantesApp.getIdStatutContratEnCours().equals(contrat.getStatut().getIdentifiant())) {
                        couleurEntete = ContratsPersonneMoralePresenter.RESSOURCES.css().blocSyntheseDepliantContratEnCours();
                    }
                    else if (constantesApp.getIdStatutContratResilie().equals(contrat.getStatut().getIdentifiant())) {
                        couleurEntete = ContratsPersonneMoralePresenter.RESSOURCES.css().blocSyntheseDepliantContratResilie();
                    }
                    contratInfosView.modifierCouleurEntete(couleurEntete);
                }
                // Ajout d'un écouteur sur l'ouverture du bloc dépliant
                contratInfosView.getBlocSyntheseContrat().addOpenEventHandler(new OpenBlocSyntheseEventHandler() {
                    @Override
                    public void onOpen(OpenBlocSyntheseEvent event) {
                        // Création de la vue du contenu du contrat
                        final ContratPersonneMoraleContenuView contratContenuView = contratInfosView.creerVueContenu();
                        // Création du presenter
                        new ContratPersonneMoraleContenuPresenter(eventBus, contratContenuView, contratServiceRpc, constantesApp, contrat.getIdentifiant())
                                .showPresenter(contratInfosView.getConteneur());
                    }
                });
            }
        }
    }

    /**
     * Construit les données de la population.
     * @param listePopulations la liste des populations.
     */
    private void construireDonneesPopulation(List<PopulationModel> listePopulations) {
        if (listePopulations != null && listePopulations.size() > 0) {
            // Récupération de l'effectif total
            Integer effectifTotal = 0;
            for (PopulationModel population : listePopulations) {
                effectifTotal += population.getEffectif();
            }

            // Construction des données
            final DataTable donnees = DataTable.create();
            donnees.addColumn(AbstractDataTable.ColumnType.STRING, presenterConstants.libellePopulation());
            donnees.addColumn(AbstractDataTable.ColumnType.NUMBER, presenterConstants.libelleEffectif());
            donnees.addRows(listePopulations.size());
            final Integer cent = 100;
            for (int i = 0; i < listePopulations.size(); i++) {
                final Integer pourcentageEffectif = listePopulations.get(i).getEffectif() * cent / effectifTotal;
                donnees.setValue(i, 0, listePopulations.get(i).getLibelle());
                donnees.setValue(i, 1, pourcentageEffectif);
                donnees.setFormattedValue(i, 1, "");
            }
            view.afficherCamembertPopulation(donnees);
        }
        else {
            view.masquerCamembertPopulation();
        }
    }

    /**
     * Vue du presenter des contrats.
     */
    public interface ContratsPersonneMoraleView extends View {

        /** Fermer la popup de chargement. */
        void onRpcServiceSuccess();

        /**
         * Popup d'erreur.
         * @param errorPopupConfiguration la configuration.
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Affiche une popup de chargement.
         * @param loadingPopupConfiguration la configuration.
         */
        void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration);

        /**
         * Récupère la valeur du label du statut.
         * @return la valeur du label du statut.
         */
        HasText getLabelStatut();

        /**
         * Modifie la couleur du label Statut en fonction du statut du contrat.
         * @param couleurStatut le style CSS à appliquer.
         */
        void modifierCouleurStatut(String couleurStatut);

        /**
         * Récupère la valeur du label de la date de la première mutualisation.
         * @return la valeur du label de la date de la première mutualisation.
         */
        HasText getLabelPremiereMutualisation();

        /**
         * Définit le label de la fidélité.
         * @param nbAnnees le nombre d'années.
         * @param nbMois le nombre de mois.
         */
        void setLabelFidelite(Integer nbAnnees, Integer nbMois);

        /**
         * Récupère la valeur du label de la gestion du contrat.
         * @return la valeur du label de la gestion du contrat.
         */
        HasText getLabelGestionContrat();

        /**
         * Récupère la valeur du label du gestionnaire.
         * @return la valeur du label du gestionnaire.
         */
        HasText getLabelGestionnaire();

        /**
         * Récupère la valeur du label de la dernière date de radiation.
         * @return la valeur ddu label de la dernière date de radiation.
         */
        HasText getLabelDateDerniereRadiation();

        /**
         * Récupère la valeur du label du motif de la dernière radiation radiation.
         * @return la valeur du label du motif de la dernière radiation radiation.
         */
        HasText getLabelMotifDerniereRadiation();

        /**
         * Affiche les infos liées à la dernière radiation.
         * @param visible le flag de visibilité
         */
        void afficherDerniereRadiation(boolean visible);

        /**
         * Crée et ajoute un contrat à la liste des contrats.
         * @param constantesApp constantes de l'application
         * @param afficherDateResiliation flag indiquant si la date de résiliation est affichée.
         * @return la vue créée du contrat.
         */
        ContratPersonneMoraleInfosView ajouterContrat(ConstantesModel constantesApp, boolean afficherDateResiliation);

        /** Initialise le tableau présentant les populations et leurs effectifs. */
        void initTableauPopulation();

        /**
         * Ajoute une ligne dans la table des populations.
         * @param libellePopulation le libellé de la population.
         * @param effectif l'effectif de la population.
         */
        void ajouterLignePopulation(String libellePopulation, Integer effectif);

        /**
         * Affiche le camembert de la population.
         * @param donneesPopulation les données pour remplir le cambert.
         */
        void afficherCamembertPopulation(DataTable donneesPopulation);

        /** Masque le camembert de la population. */
        void masquerCamembertPopulation();
    }

    /**
     * Vue pour les infos d'un contrat d'une personne morale.
     */
    public interface ContratPersonneMoraleInfosView extends View {

        /**
         * Définit le numéro du contrat.
         * @param numeroContrat le numéro du contrat
         */
        void setNumeroContrat(String numeroContrat);

        /**
         * Modifie la couleur de l'entête en fonction du statut du contrat.
         * @param couleurEntete le style CSS à appliquer
         */
        void modifierCouleurEntete(String couleurEntete);

        /**
         * Réupère le label du statut.
         * @return le label
         */
        HasText getLabelStatut();

        /**
         * Réupère le label de la date de création.
         * @return le label
         */
        HasText getLabelDateCreation();

        /**
         * Réupère le label de la date d'effet.
         * @return le label
         */
        HasText getLabelDateEffet();

        /**
         * Réupère le label de la date de résiliation.
         * @return le label
         */
        HasText getLabelDateResiliation();

        /**
         * Recupere le conteneur des infos du contrat.
         * @return le conteneur
         */
        HasWidgets getConteneur();

        /**
         * Recupere le bloc synthese.
         * @return le bloc
         */
        HasOpenBlocSyntheseEventHandlers getBlocSyntheseContrat();

        /**
         * Créer la vue du contenu d'un contrat.
         * @return l'interface de la vue créee
         */
        ContratPersonneMoraleContenuView creerVueContenu();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
