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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
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
import com.google.gwt.visualization.client.visualizations.OrgChart;
import com.square.composant.contrat.square.client.bundle.ComposantContratResources;
import com.square.composant.contrat.square.client.event.ContratsChargesEvent;
import com.square.composant.contrat.square.client.model.ConstantesModel;
import com.square.composant.contrat.square.client.model.ContratSimpleModel;
import com.square.composant.contrat.square.client.model.GarantieBeneficiaireModel;
import com.square.composant.contrat.square.client.model.GarantieSimpleModel;
import com.square.composant.contrat.square.client.model.InfosContratsModel;
import com.square.composant.contrat.square.client.model.InfosGarantieBeneficiaireModel;
import com.square.composant.contrat.square.client.model.ListeContratsModel;
import com.square.composant.contrat.square.client.model.RatioPrestationCotisationModel;
import com.square.composant.contrat.square.client.model.RecapitulatifGarantiesContratModel;
import com.square.composant.contrat.square.client.model.ReserveBancoModel;
import com.square.composant.contrat.square.client.presenter.ContratContenuPresenter.ContratContenuView;
import com.square.composant.contrat.square.client.service.ConstantesServiceGwt;
import com.square.composant.contrat.square.client.service.ConstantesServiceGwtAsync;
import com.square.composant.contrat.square.client.service.ContratServiceGwt;
import com.square.composant.contrat.square.client.service.ContratServiceGwtAsync;
import com.square.composant.contrat.square.client.util.FormatServletUtil;
import com.square.composant.contrat.square.client.util.FormatServletUtil.ParamServlet;
import com.square.composant.contrat.square.client.view.ContratsViewImplConstants;
import com.square.composants.graphiques.lib.client.event.HasOpenBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEventHandler;

/**
 * Presenter des contrats d'une personne.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratsPresenter extends Presenter {

    /** Instance des ressources. */
    public static final ComposantContratResources RESOURCES = GWT.create(ComposantContratResources.class);

    /** Constantes pour le présenter. */
    private ContratsPresenterConstants presenterConstants;

    /** Vue du presenter. */
    private ContratsView view;

    /** Constantes de l'application. */
    private ConstantesModel constantesApp;

    /** L'identifiant de la personne. */
    private Long idPersonne;

    /** Service contrat. */
    private ContratServiceGwtAsync contratServiceRpc;

    /** Service Constantes. */
    private ConstantesServiceGwtAsync constantesServiceRpc;

    private InfosContratsModel currentInfosContrats = null;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènements
     * @param view la vue
     * @param idPersonne l'identifiant de la personne
     */
    public ContratsPresenter(HandlerManager eventBus, ContratsView view, Long idPersonne) {
        super(eventBus);
        this.presenterConstants = (ContratsPresenterConstants) GWT.create(ContratsPresenterConstants.class);
        this.view = view;
        this.contratServiceRpc = GWT.create(ContratServiceGwt.class);
        this.constantesServiceRpc = GWT.create(ConstantesServiceGwt.class);
        this.idPersonne = idPersonne;
        StyleInjector.inject(ContratsPresenter.RESOURCES.css().getText());
        // Chargement des constantes
        chargerConstantes();
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
        container.clear();
        if (currentInfosContrats != null) {
            chargerGraphiques();
        }
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
                chargerListeContrats();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        constantesServiceRpc.getConstantes(asyncCallback);
    }

    /** Charge les infos des contrats. */
    private void chargerListeContrats() {
        // Chargement des contrats de la personne
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().chargementListeContrats()));
        // Création du callback
        final AsyncCallback<ListeContratsModel> asyncCallback = new AsyncCallback<ListeContratsModel>() {
            @Override
            public void onSuccess(ListeContratsModel result) {
                view.onRpcServiceSuccess();
                // Chargement des infos
                chargerListeContrats(result);

                final int nbContratsCharges = result.getListeContrats().size();
                fireEventLocalBus(new ContratsChargesEvent(nbContratsCharges));
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        contratServiceRpc.getListeContrats(idPersonne, asyncCallback);
    }

    /**
     * Charge les infos et la liste des contrats.
     * @param listeContrats le modèle contenant les infos
     */
    private void chargerListeContrats(ListeContratsModel listeContrats) {
        // Remplissage des infos
        final InfosContratsModel infosContrats = listeContrats.getInfosContrats();
        if (infosContrats != null) {
            // On peut commencer à charger l'API de visualisation et les graphiques
            chargerApiVisualisation(infosContrats);
            // Affichage des infos
            view.getLabelStatut().setText(infosContrats.getStatut().getLibelle());
            // Modification de la couleur du statut
            if (infosContrats.getStatut() != null && infosContrats.getStatut().getIdentifiant() != null) {
                String couleurStatut = null;
                if (constantesApp.getIdStatutContratEnCours().equals(infosContrats.getStatut().getIdentifiant())) {
                    couleurStatut = ContratsPresenter.RESOURCES.css().statutContratEnCours();
                } else if (constantesApp.getIdStatutContratResilie().equals(infosContrats.getStatut().getIdentifiant())) {
                    couleurStatut = ContratsPresenter.RESOURCES.css().statutContratResilie();
                }
                view.modifierCouleurStatut(couleurStatut);
            }
            view.getLabelPremiereMutualisation().setText(infosContrats.getDatePremiereMutualisation());
            view.setLabelFidelite(infosContrats.getNbAnneesFidelite(), infosContrats.getNbMoisFidelite());
            view.setLabelTeletransmission(infosContrats.getTeletransmission());
            view.getLabelGestionContrat().setText(infosContrats.getGestionDuContrat());
            view.getLabelGestionnaire().setText(infosContrats.getGestionnaire());
            // Affichage des infos spécifiques au collectif
            if (infosContrats.getSegment() != null
                && (constantesApp.getIdSegmentCollectif().equals(infosContrats.getSegment().getIdentifiant()) || constantesApp
                        .getIdSegmentCollectifIndividualise().equals(infosContrats.getSegment().getIdentifiant()))) {
                view.getLabelPopulation().setText(infosContrats.getPopulation());
                view.afficherInfosCollectif(true);
            } else {
                view.afficherInfosCollectif(false);
            }
            // Affichage des infos de dernière radiation
            if (infosContrats.getDateDerniereRadiation() != null) {
                view.getLabelDateDerniereRadiation().setText(infosContrats.getDateDerniereRadiation());
                view.getLabelMotifDerniereRadiation().setText(infosContrats.getMotifDerniereRadiation());
                view.afficherDerniereRadiation(true);
            } else {
                view.afficherDerniereRadiation(false);
            }
            // Affichage du récapitulatif des garanties
            chargerRecapitulatifGaranties(infosContrats.getListeGarantiesContrat());
        }
        // Chargement de la liste des contrats simples
        chargerListeContratsSimples(listeContrats.getListeContrats());
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
                    int indexMapBene = 0;
                    for (int idxBeneficiaire = 0; idxBeneficiaire < recapitulatifGaranties.getListeBeneficiaires().size(); idxBeneficiaire++) {
                        final GarantieBeneficiaireModel beneficiaire = recapitulatifGaranties.getListeBeneficiaires().get(idxBeneficiaire);
                        if (mapIndexBeneficiaires.get(beneficiaire.getIdBenef()) == null) {
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
                            mapIndexBeneficiaires.put(beneficiaire.getIdBenef(), Integer.valueOf(indexMapBene++));
                        }
                    }
                    view.remplirBeneficiaireRoleRecapGaranties(listeBeneficiaires, listeRoles);

                    // Ajout des garanties
                    if (recapitulatifGaranties.getListeGaranties() != null) {
                        for (final GarantieSimpleModel garantie : recapitulatifGaranties.getListeGaranties()) {
                            String libelleGarantie = "";
                            if (garantie.getLibelle() != null) {
                                libelleGarantie = garantie.getLibelle();
                            }
                            // Récupération des valeurs par bénéficiaires
                            final InfosGarantieBeneficiaireModel[] tabValeurs = new InfosGarantieBeneficiaireModel[listeBeneficiaires.size()];
                            if (garantie.getListeInfosGarantiesBeneficiaires() != null) {
                                for (InfosGarantieBeneficiaireModel valeurGarantieBeneficiaire : garantie.getListeInfosGarantiesBeneficiaires()) {
                                    tabValeurs[mapIndexBeneficiaires.get(valeurGarantieBeneficiaire.getIdBeneficiaire())] = valeurGarantieBeneficiaire;
                                }
                            }
                            final List<InfosGarantieBeneficiaireModel> listeValeurs = new ArrayList<InfosGarantieBeneficiaireModel>();
                            for (InfosGarantieBeneficiaireModel valeur : tabValeurs) {
                                if (valeur != null) {
                                    listeValeurs.add(valeur);
                                } else {
                                    final InfosGarantieBeneficiaireModel infosVide = new InfosGarantieBeneficiaireModel();
                                    listeValeurs.add(infosVide);
                                }
                            }
                            final Boolean isSegmentCollectif = constantesApp.getIdSegmentCollectif().equals(garantie.getSegment().getIdentifiant());
                            final HasClickHandlers elementCliquable =
                                view.ajouterGarantieEtValeursRecapGaranties(libelleGarantie, garantie.isPossedeGrillePresta(), listeValeurs, constantesApp);
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
    }

    /**
     * Charge la liste des contrats simples.
     * @param listeContratsSimples la liste des contrats simples
     */
    private void chargerListeContratsSimples(List<ContratSimpleModel> listeContratsSimples) {
        if (listeContratsSimples != null) {
            // Parcours des contrats pour ajout
            for (final ContratSimpleModel contrat : listeContratsSimples) {
                final ContratInfosView contratInfosView = view.ajouterContrat(constantesApp, contrat.getDateResiliation() != null);
                // Mise à jour du numéro de contrat
                if (contrat.getNumeroContrat() != null) {
                    contratInfosView.setNumeroContrat(contrat.getNumeroContrat());
                }
                // Remplissage des valeurs
                if (contrat.getStatut() != null && contrat.getStatut().getLibelle() != null) {
                    contratInfosView.getLabelStatut().setText(contrat.getStatut().getLibelle());
                }
                if (contrat.getDateSignature() != null && !"".equals(contrat.getDateSignature())) {
                    contratInfosView.getLabelDateSignature().setText(contrat.getDateSignature());
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
                        couleurEntete = ContratsPresenter.RESOURCES.css().blocSyntheseDepliantContratEnCours();
                    } else if (constantesApp.getIdStatutContratResilie().equals(contrat.getStatut().getIdentifiant())) {
                        couleurEntete = ContratsPresenter.RESOURCES.css().blocSyntheseDepliantContratResilie();
                    }
                    // TODO Voir si besoin d'autres couleurs
                    contratInfosView.modifierCouleurEntete(couleurEntete);
                }

                // Création du presenter
                final ContratContenuPresenter contenuPresenter =
                    addChildPresenter(new ContratContenuPresenter(eventBus, contratInfosView.creerVueContenu(), contratServiceRpc, constantesApp, contrat
                            .getIdentifiant()));
                contenuPresenter.showPresenter(contratInfosView.getConteneur());

                // Ajout d'un écouteur sur l'ouverture du bloc dépliant
                contratInfosView.getBlocSyntheseContrat().addOpenEventHandler(new OpenBlocSyntheseEventHandler() {
                    @Override
                    public void onOpen(OpenBlocSyntheseEvent event) {
                        contenuPresenter.chargerContrat();
                    }
                });
            }
        }
    }

    /**
     * Charge l'API graphique puis les graphiques si la connexion à Internet existe.
     * @param infosContrats les infos de contrat
     */
    private void chargerApiVisualisation(final InfosContratsModel infosContrats) {
        currentInfosContrats = infosContrats;
        // Tentative de connexion a internet pour déterminer si la visualisation est disponible.
        final RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "http://www.google.fr");
        rb.setHeader("Access-Control-Allow-Origin", "http://www.google.fr");
        final int timeout = 15000;
        rb.setTimeoutMillis(timeout);
        rb.setCallback(new RequestCallback() {
            @Override
            public void onResponseReceived(Request request, Response response) {
                if (response != null) {
                    final Runnable onLoadCallback = new Runnable() {
                        public void run() {
                            // Chargement des graphiques
                            chargerGraphiques();
                        }
                    };
                    VisualizationUtils.loadVisualizationApi(onLoadCallback, OrgChart.PACKAGE);
                }
            }

            @Override
            public void onError(Request request, Throwable exception) {
            }
        });
        try {
            rb.send();
        } catch (RequestException e) {
            GWT.log("", e);
        }
    }

    /**
     * Charge les graphiques à partir des infos de contrat.
     * @param infosContrats les infos de contrat
     */
    private void chargerGraphiques() {
        // Chargement de la statistique P/C
        if (currentInfosContrats.getListeRatiosPrestationCotisation() != null && currentInfosContrats.getListeRatiosPrestationCotisation() != null) {
            final DataTable donneesStatsPC = construireDonneesStatistiquePC(currentInfosContrats.getListeRatiosPrestationCotisation());
            if (donneesStatsPC != null) {
                view.afficherStatistiquePrestationCotisation(donneesStatsPC);
            } else {
                view.masquerStatistiquePrestationCotisation();
            }
        } else {
            view.masquerStatistiquePrestationCotisation();
        }
        // Chargement des jauges banco
        if (currentInfosContrats.getListeReservesBanco() != null && !currentInfosContrats.getListeReservesBanco().isEmpty()) {
            view.initJaugesBanco();
            boolean masquerJaugesBanco = true;
            for (ReserveBancoModel reserveBanco : currentInfosContrats.getListeReservesBanco()) {
                final DataTable dataTable = construireDonneesJaugeBanco(reserveBanco);
                if (dataTable != null) {
                    masquerJaugesBanco = false;
                    view.afficherJaugeBanco(reserveBanco.getDateDebutReserve(), reserveBanco.getDateFinReserve(), dataTable);
                }
            }
            if (masquerJaugesBanco) {
                view.masquerJaugesBanco();
            }
        } else {
            view.masquerJaugesBanco();
        }
    }

    /**
     * Construit les données de la jauge Banco.
     * @param reserveBanco l'objet contenant les infos de la réserve banco
     * @return les données
     */
    private DataTable construireDonneesJaugeBanco(ReserveBancoModel reserveBanco) {
        // Calcul des pourcentages
        if (reserveBanco.getReserveAnnuelle() != 0) {
            final double cent = 100d;
            final double pourcentageReserveConsomme = reserveBanco.getReserveConsommee() * cent / reserveBanco.getReserveAnnuelle();
            final double pourcentageReserveRestante = cent - pourcentageReserveConsomme;
            // Création des données
            final DataTable donnees = DataTable.create();
            donnees.addColumn(AbstractDataTable.ColumnType.STRING, view.getViewConstants().libelleLegendeJaugeBancoDescription());
            donnees.addColumn(AbstractDataTable.ColumnType.NUMBER, view.getViewConstants().libelleLegendeJaugeBancoPourcentage());
            donnees.removeRows(0, donnees.getNumberOfRows());
            donnees.addRows(2);
            donnees.setValue(0, 0, view.getViewConstants().libelleLegendeJaugeBancoConsomme());
            donnees.setValue(0, 1, pourcentageReserveConsomme);
            donnees.setFormattedValue(0, 1, "");
            donnees.setValue(1, 0, view.getViewConstants().libelleLegendeJaugeBancoRestant());
            donnees.setValue(1, 1, pourcentageReserveRestante);
            donnees.setFormattedValue(1, 1, "");
            return donnees;
        } else {
            return null;
        }
    }

    /**
     * Construit les données de statistique Prestations/Cotisations.
     * @param reserveBanco l'objet contenant les infos de la réserve banco
     * @return les données
     */
    private DataTable construireDonneesStatistiquePC(List<RatioPrestationCotisationModel> listeRatiosPrestationCotisation) {
        if (listeRatiosPrestationCotisation != null && listeRatiosPrestationCotisation.size() > 0) {
            // Récupération de la liste ordonnées des années et la liste des identifiants de personnes
            final Set<Integer> setAnnees = new TreeSet<Integer>();
            final Set<Long> setIdsPersonnes = new HashSet<Long>();
            final Map<Long, String> mapLibellesPersonnes = new HashMap<Long, String>();
            for (RatioPrestationCotisationModel ratio : listeRatiosPrestationCotisation) {
                setAnnees.add(Integer.valueOf(ratio.getAnnee()));
                setIdsPersonnes.add(ratio.getPersonne().getIdentifiant());
                mapLibellesPersonnes.put(ratio.getPersonne().getIdentifiant(), ratio.getPersonne().getLibelle());
            }
            // Création de liste à partir des sets
            final Iterator<Integer> iteratorAnnees = setAnnees.iterator();
            final Iterator<Long> iteratorIdsPersonne = setIdsPersonnes.iterator();
            final List<Integer> listeAnnees = new ArrayList<Integer>();
            final List<Long> listeIdsPersonnes = new ArrayList<Long>();
            while (iteratorAnnees.hasNext()) {
                listeAnnees.add(iteratorAnnees.next());
            }
            while (iteratorIdsPersonne.hasNext()) {
                listeIdsPersonnes.add(iteratorIdsPersonne.next());
            }
            // Création du tableau des ratios
            final double[][] ratios = new double[listeAnnees.size()][listeIdsPersonnes.size()];
            for (RatioPrestationCotisationModel ratio : listeRatiosPrestationCotisation) {
                ratios[listeAnnees.indexOf(Integer.valueOf(ratio.getAnnee()))][listeIdsPersonnes.indexOf(ratio.getPersonne().getIdentifiant())] =
                    ratio.getRatioPrestationCotisation();
            }

            // Création des données
            final DataTable donnees = DataTable.create();
            donnees.addColumn(AbstractDataTable.ColumnType.STRING, view.getViewConstants().libelleLegendeStatPCAnnee());
            // Une colonne pour chaque personne
            for (Long identifiantPersonne : listeIdsPersonnes) {
                donnees.addColumn(AbstractDataTable.ColumnType.NUMBER, mapLibellesPersonnes.get(identifiantPersonne));
            }
            donnees.removeRows(0, donnees.getNumberOfRows());
            donnees.addRows(listeAnnees.size());
            // Remplissage des données par année
            for (int idxAnnee = 0; idxAnnee < listeAnnees.size(); idxAnnee++) {
                donnees.setValue(idxAnnee, 0, listeAnnees.get(idxAnnee).toString());
                // Remplissage des données par personne
                for (int idxPersonne = 0; idxPersonne < listeIdsPersonnes.size(); idxPersonne++) {
                    donnees.setValue(idxAnnee, idxPersonne + 1, ratios[idxAnnee][idxPersonne]);
                }
            }
            return donnees;
        } else {
            return null;
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
     * Vue pour les contrats (synthèse + liste des contrats).
     */
    public interface ContratsView extends View {

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
        ContratsViewImplConstants getViewConstants();

        /**
         * Récupère la valeur de labelStatut.
         * @return la valeur de labelStatut
         */
        HasText getLabelStatut();

        /**
         * Récupère la valeur de labelPremiereMutualisation.
         * @return la valeur de labelPremiereMutualisation
         */
        HasText getLabelPremiereMutualisation();

        /**
         * Récupère la valeur de labelDateDerniereRadiation.
         * @return la valeur de labelDateDerniereRadiation
         */
        HasText getLabelDateDerniereRadiation();

        /**
         * Récupère la valeur de labelMotifDerniereRadiation.
         * @return la valeur de labelMotifDerniereRadiation
         */
        HasText getLabelMotifDerniereRadiation();

        /**
         * Récupère la valeur de labelPopulation.
         * @return la valeur de labelPopulation
         */
        HasText getLabelPopulation();

        /**
         * Récupère la valeur de labelGestionContrat.
         * @return la valeur de labelGestionContrat
         */
        HasText getLabelGestionContrat();

        /**
         * Récupère la valeur de labelGestionnaire.
         * @return la valeur de labelGestionnaire
         */
        HasText getLabelGestionnaire();

        /**
         * Définit le label Fidélité.
         * @param nbAnnees le nombre d'années
         * @param nbMois le nombre de mois
         */
        void setLabelFidelite(Integer nbAnnees, Integer nbMois);

        /**
         * Définit le label Télétransmission.
         * @param teletransmission définit la valeur du label
         */
        void setLabelTeletransmission(Boolean teletransmission);

        /**
         * Affiche les infos liées au collectif.
         * @param visible le flag de visibilité
         */
        void afficherInfosCollectif(boolean visible);

        /**
         * Affiche les infos liées à la dernière radiation.
         * @param visible le flag de visibilité
         */
        void afficherDerniereRadiation(boolean visible);

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
         * @param constantes les constantes
         * @return l'élément cliquable permettant de télécharger la grille, null ne possède pas de grille de presta
         */
        HasClickHandlers ajouterGarantieEtValeursRecapGaranties(String libelleGarantie, boolean possedeGrillePresta,
            List<InfosGarantieBeneficiaireModel> listeValeurs, ConstantesModel constantes);

        /**
         * Crée et ajoute un contrat à la liste des contrats.
         * @param constantes les constantes
         * @param afficherDateResiliation flag indiquant si la date de résiliation
         * @return la vue du contrat créée
         */
        ContratInfosView ajouterContrat(ConstantesModel constantes, boolean afficherDateResiliation);

        /**
         * Modifie la couleur du label Statut en fonction du statut du contrat.
         * @param couleurStatut le style CSS à appliquer
         */
        void modifierCouleurStatut(String couleurStatut);

        /**
         * Affiche la jauge banco.
         * @param dateDebut dateDebut
         * @param dateFin dateFin
         * @param donnees donnees
         */
        void afficherJaugeBanco(String dateDebut, String dateFin, DataTable donnees);

        /**
         * Masque la jauge banco.
         */
        void masquerJaugesBanco();

        /**
         * Init la jauge banco.
         */
        void initJaugesBanco();

        /**
         * Affiche la statistique P/C.
         * @param donneesStatsPC donnees
         */
        void afficherStatistiquePrestationCotisation(DataTable donneesStatsPC);

        /**
         * Masque la statistique P/C.
         */
        void masquerStatistiquePrestationCotisation();

        /**
         * Appel la servlet de grilles de prestations.
         * @param url l'url
         */
        void voirGrillePrestations(String url);
    }

    /**
     * Vue pour les infos d'un contrat.
     */
    public interface ContratInfosView extends View {

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
         * Réupère le label de la date de signature.
         * @return le label
         */
        HasText getLabelDateSignature();

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
        ContratContenuView creerVueContenu();
    }

    @Override
    public void onDetach() {
    }

}
