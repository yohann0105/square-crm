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
package com.square.client.gwt.client.presenter.personne.morale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.remote.paging.RemotePagingTable;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.servlet.ParamServlet;
import org.scub.foundation.framework.gwt.module.client.util.servlet.ServletUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.OpenPersonneMoraleEvent;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCritereCodePostalCommuneModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheDepartementModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.IdentifiantLibelleDepartementCodeModel;
import com.square.client.gwt.client.model.PersonneMoralCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneMoraleRechercheModel;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneMoraleServiceGwtAsync;
import com.square.client.gwt.client.service.UtilServiceGwtAsync;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;

/**
 * Presenter pour la page moteur de recherche de personne morale.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneMoraleMoteurRecherchePresenter extends Presenter {

    /** Services asynchrones. **/
    private PersonneMoraleServiceGwtAsync personneMoraleServiceGwtAsync;

    private DimensionServiceGwtAsync dimensionServiceGwtAsync;

    /** Vue associée au presenter. **/
    private PersonneMoraleMoteurRechercheView view;

    private UtilServiceGwtAsync utilServiceGwtAsync;

    private RemotePagingCriteriasGwt<PersonneMoralCriteresRechercheModel> criteresRechercheEnCours;

    private ConstantesModel constantes;

    /** Service de gestion d'aide . */
    private AideServiceGwtAsync aideService;

    /**
     * Constructeur de personne morale .
     * @param eventBus le bus des évenements.
     * @param personneMoraleServiceGwtAsync service rpc des personnes morales.
     * @param dimensionServiceGwtAsync service rpc pour les dimensions.
     * @param view la vue assoicées au presenter.
     * @param appControllerConstants les constantes d'application.
     * @param utilServiceGwtAsync service rpc pour l'utilitaire
     * @param constantes les constantes
     * @param aideService service d'aide.
     */
    public PersonneMoraleMoteurRecherchePresenter(HandlerManager eventBus, PersonneMoraleServiceGwtAsync personneMoraleServiceGwtAsync,
        DimensionServiceGwtAsync dimensionServiceGwtAsync, UtilServiceGwtAsync utilServiceGwtAsync, PersonneMoraleMoteurRechercheView view,
        AppControllerConstants appControllerConstants, ConstantesModel constantes, AideServiceGwtAsync aideService) {
        super(eventBus);
        this.personneMoraleServiceGwtAsync = personneMoraleServiceGwtAsync;
        this.dimensionServiceGwtAsync = dimensionServiceGwtAsync;
        this.view = view;
        this.utilServiceGwtAsync = utilServiceGwtAsync;
        this.constantes = constantes;
        this.aideService = aideService;
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        // les évenements relatifs à l'aide en ligne pour les composants d'aide.
        for (HasUpdateAideEventHandler handler : view.getListeUpdateEventHandler()) {
            handler.addUpdateAideEventHandler(new UpdateAideEventHandler() {
                @Override
                public void onUpdateAide(UpdateAideEvent event) {
                    aideService.creerOuModifierAide(event.getAide(), event.getCallBack());
                }
            });
        }

        for (HasDemandeAideEventHandler handler : view.getListeDemandeEventHandler()) {
            handler.addDemandeAideEventHandler(new DemandeAideEventHandler() {
                @Override
                public void onDemandeAide(DemandeAideEvent event) {
                    aideService.rechercherAide(event.getIdComposant(), event.getCallBack());
                }
            });
        }

        view.getBtnRechercher().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                rechercherPersonneMorale();
            }
        });
        view.getGestionToucheEntreHandler().addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    rechercherPersonneMorale();
                }
            }
        });
        view.getBtnEffacerRecherche().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.effacerRecherche();
            }
        });
        view.getRemotePagingHandlerManager().addHandler(SetDataProviderEvent.TYPE,
            new SetDataProviderEventHandler<PersonneMoralCriteresRechercheModel, PersonneMoraleRechercheModel>() {
                public void setDataProvider(SetDataProviderEvent<PersonneMoralCriteresRechercheModel, PersonneMoraleRechercheModel> event) {
                    // On récupère les critères.
                    final PersonneMoralCriteresRechercheModel criterias = new PersonneMoralCriteresRechercheModel();
                    criterias.setRaisonSociale(view.getTbRaisonSocialeValue().getValue());
                    criterias.setNumeroEntreprise(view.getTbNumEntrepriseValue().getValue());
                    final List<Long> listeFormesJuridiques = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbFormeJuridiqueValue().getValue()) {
                        if (idLibelle != null) {
                            listeFormesJuridiques.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeFormesJuridiques(listeFormesJuridiques);
                    final List<Long> listeCodesPostaux = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbCodePostalValue().getValue()) {
                        if (idLibelle != null) {
                            listeCodesPostaux.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeCodesPostaux(listeCodesPostaux);
                    final List<Long> listeVilles = new ArrayList<Long>();
                    for (CodePostalCommuneModel idLibelle : view.getSlbVilleValue().getValue()) {
                        if (idLibelle != null) {
                            listeVilles.add(idLibelle.getCommune().getIdentifiant());
                        }
                    }
                    criterias.setListeVilles(listeVilles);
                    final List<Long> listeDepartements = new ArrayList<Long>();
                    for (IdentifiantLibelleDepartementCodeModel idLibelle : view.getSlbDepartementValue().getValue()) {
                        if (idLibelle != null) {
                            listeDepartements.add(idLibelle.getId());
                        }
                    }
                    criterias.setListeDepartements(listeDepartements);
                    final List<Long> listeAgences = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbAgenceValue().getValue()) {
                        if (idLibelle != null) {
                            listeAgences.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeAgences(listeAgences);
                    final List<Long> listeCommerciaux = new ArrayList<Long>();
                    for (DimensionRessourceModel idLibelle : view.getSlbCommercialValue().getValue()) {
                        if (idLibelle != null) {
                            listeCommerciaux.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeCommerciaux(listeCommerciaux);
                    final List<Long> listeNatures = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbNatureValue().getValue()) {
                        if (idLibelle != null) {
                            listeNatures.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeNatures(listeNatures);
                    event.getParams().setCriterias(criterias);

                    // on affiche le lien pour l'export excel
                    if (criteresRechercheEnCours == null) {
                        view.afficherExportExcel();
                    }
                    // on stocke les parametres pour l'export excel
                    criteresRechercheEnCours = event.getParams();

                    // Appel du service
                    personneMoraleServiceGwtAsync.rechercherPersonneMoraleParCriteres(event.getParams(), event.getCallback());

                }
            });
        view.getRemotePagingHandlerManager().addHandler(SetCellClickedEvent.TYPE, new SetCellClickedEventHandler<PersonneMoraleRechercheModel>() {
            @Override
            public void setCellClicked(SetCellClickedEvent<PersonneMoraleRechercheModel> event) {
                fireEventGlobalBus(new OpenPersonneMoraleEvent(event.getModele().getId(), (event.getModele().getNature() != null) ? event.getModele()
                        .getNature().getIdentifiant() : null, event.getModele().getRaisonSociale()));
            }
        });
        view.getSlbVille().addSuggestHandler(new SuggestListBoxSuggestEventHandler<CodePostalCommuneModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<CodePostalCommuneModel> event) {
                DimensionCritereCodePostalCommuneModel criteres = null;
                final String libelleRecherche = event.getSuggest();
                if (libelleRecherche == null || "".equals(libelleRecherche.trim())) {
                    criteres = new DimensionCritereCodePostalCommuneModel();
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    dimensionServiceGwtAsync.rechercherCodesPostauxCommunes(criteres, event.getCallBack());
                }
                else {
                    criteres = new DimensionCritereCodePostalCommuneModel();
                    criteres.setLibelleAcheminement(libelleRecherche);
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX_CP);
                    dimensionServiceGwtAsync.rechercherCodesPostauxCommunes(criteres, event.getCallBack());
                }
            }
        });

        view.setNbMinCaracteresRechercheCodePostal(3);
        view.getSlbCodePostal().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                DimensionCriteresRechercheModel criteres = null;
                final String libelleRecherche = event.getSuggest();
                if (libelleRecherche == null || "".equals(libelleRecherche.trim())) {
                    criteres = new DimensionCriteresRechercheModel();
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    dimensionServiceGwtAsync.rechercherCodePostauxParCriteres(criteres, event.getCallBack());
                }
                else {
                    criteres = new DimensionCriteresRechercheModel();
                    criteres.setLibelle(libelleRecherche);
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX_CP);
                    dimensionServiceGwtAsync.rechercherCodePostauxParCriteres(criteres, event.getCallBack());
                }
            }
        });

        // On récupère les valeurs des tables de dimension
        view.getSlbFormeJuridique().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherFormesJuridiques(criteres, event.getCallBack());
            }
        });

        view.getSlbDepartement().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleDepartementCodeModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleDepartementCodeModel> event) {
                final DimensionCritereRechercheDepartementModel criteres = new DimensionCritereRechercheDepartementModel();
                final DimensionCriteresRechercheModel crit = new DimensionCriteresRechercheModel();
                crit.setLibelle(event.getSuggest());
                criteres.setDimensionCriteres(crit);
                dimensionServiceGwtAsync.rechercherDepartementParCriteres(criteres, event.getCallBack());
            }
        });

        view.getSlbAgence().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherAgenceParCriteres(criteres, event.getCallBack());
            }
        });

        view.getSlbCommercial().addSuggestHandler(new SuggestListBoxSuggestEventHandler<DimensionRessourceModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<DimensionRessourceModel> event) {
                final DimensionCriteresRechercheRessourceModel criteres = new DimensionCriteresRechercheRessourceModel();
                final String libelleRessourceRecherchee = event.getSuggest();
                criteres.setNom(libelleRessourceRecherchee);
                criteres.setPrenom(libelleRessourceRecherchee);
                if (libelleRessourceRecherchee != null && !"".equals(libelleRessourceRecherchee)) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherRessourceParCriteres(criteres, event.getCallBack());
            }
        });

        view.getSlbNature().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherNaturePersonneMoraleParCriteres(criteres, event.getCallBack());
            }
        });

        final ClickHandler exportExcel = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                utilServiceGwtAsync.mapperCriteresRecherche(criteresRechercheEnCours, new AsyncCallback<Map<String, String>>() {
                    @Override
                    public void onSuccess(Map<String, String> result) {
                        // on ajoute le type du service à appeler et le type de l'objet
                        result.put(constantes.getParamService(), constantes.getValueServiceRecherchePersonneMorale());
                        // on transforme la map en liste de params
                        final List<ParamServlet> listeParams = new ArrayList<ParamServlet>();
                        for (String key : result.keySet()) {
                            listeParams.add(new ParamServlet(key, result.get(key)));
                        }
                        // on cree l'url complete
                        final String urlServleComplete = ServletUtil.formatUrl(constantes.getUrlServletExporterRecherche(), listeParams);
                        // on appelle la servlet
                        Window.open(urlServleComplete, "_blank", "resizable=yes,menubar=no,location=no");
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }
                });
            }
        };
        view.getImageExportExcel().addClickHandler(exportExcel);
        view.getLabelExportExcel().addClickHandler(exportExcel);
    }

    @Override
    public void onShow(HasWidgets container) {
        // Construction de la liste des identifiants des composants d'aide de la vue associée à ce presenter
        final List<Long> listeIdsComposantsAides = new ArrayList<Long>();
        for (final AideComposant composant : view.getListAideCompsant()) {
            listeIdsComposantsAides.add(composant.getId());
        }

        // Recherche des composants d'aide existant pour les rendre visible
        aideService.rechercherIdsComposantsAides(listeIdsComposantsAides, new AsyncCallback<List<Long>>() {
            @Override
            public void onSuccess(List<Long> result) {
                if (result != null) {
                    for (final AideComposant composant : view.getListAideCompsant()) {
                        composant.setVisible(result.contains(composant.getId()));
                    }
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });

        container.add(view.asWidget());
    }

    /**
     * Rechercher une société à partir des critères.
     */
    private void rechercherPersonneMorale() {
        view.getRemotePagingTable().rechercher();
    }

    /**
     * Interface pour la vue PersonneMoraleMoteurRechercherView.
     */
    public interface PersonneMoraleMoteurRechercheView extends View {
        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasValue<String> getTbRaisonSocialeValue();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasKeyPressHandlers getTbRaisonSociale();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasValue<String> getTbNumEntrepriseValue();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasKeyPressHandlers getTbNumEntreprise();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasValue<List<IdentifiantLibelleDepartementCodeModel>> getSlbDepartementValue();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleDepartementCodeModel> getSlbDepartement();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbFormeJuridiqueValue();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbNatureValue();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbFormeJuridique();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNature();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasValue<List<CodePostalCommuneModel>> getSlbVilleValue();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasSuggestListBoxHandler<CodePostalCommuneModel> getSlbVille();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbCodePostalValue();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbCodePostal();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbAgenceValue();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbAgence();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasValue<List<DimensionRessourceModel>> getSlbCommercialValue();

        /**
         * Retourne le widget.
         * @return le widget.
         */
        HasSuggestListBoxHandler<DimensionRessourceModel> getSlbCommercial();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnRechercher();

        /**
         * Afficher un message de chargement.
         * @param config la config
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Affiche un message d'avertissement.
         * @param config la config
         */
        void afficherInfoPopup(PopupInfoConfiguration config);

        /**
         * Methode appelé lorsque un service Rpc s'est deroulé correctement.
         */
        void onRpcServiceSuccess();

        /**
         * Methode appelé lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Retourne le tableau d'affichage des sociétés.
         * @return le tableau.
         */
        RemotePagingTable<PersonneMoraleRechercheModel, PersonneMoralCriteresRechercheModel> getRemotePagingTable();

        /**
         * Retourne le gestionnaire d'evenement du tableau.
         * @return le gestionnaire d'evenement.
         */
        HandlerManager getRemotePagingHandlerManager();

        /**
         * Initialiser le moteur de recherche.
         */
        void effacerRecherche();

        /**
         * Efface le moteur de recherche.
         * @return le handler.
         */
        HasClickHandlers getBtnEffacerRecherche();

        /**
         * Gerer la touche entré.
         * @return le handler sur la gestion de la touche entré.
         */
        HasKeyPressHandlers getGestionToucheEntreHandler();

        /**
         * Handler sur le clic de l'export excel.
         * @return handler
         */
        HasClickHandlers getImageExportExcel();

        /**
         * Handler sur le clic de l'export excel.
         * @return handler
         */
        HasClickHandlers getLabelExportExcel();

        /**
         * Affiche le bloc d'export excel.
         */
        void afficherExportExcel();

        /**
         * Définit le nombre minimal de caractères pour la recherche de code postal.
         * @param nbMinCaracteres nombre minimum de caractères
         */
        void setNbMinCaracteresRechercheCodePostal(int nbMinCaracteres);

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
    }
}
