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
package com.square.client.gwt.client.presenter.personne.physique;

import java.util.ArrayList;
import java.util.Date;
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
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingSortGwt;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
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
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.ChercherDoublonEvent;
import com.square.client.gwt.client.event.ChercherDoublonEventHandler;
import com.square.client.gwt.client.event.FusionnerPersonnesEvent;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCritereCodePostalCommuneModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneSimpleModel;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonnePhysiqueServiceGwtAsync;
import com.square.client.gwt.client.service.UtilServiceGwtAsync;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Presenter pour la page moteur de recherche de personne physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonnePhysiqueMoteurRecherchePresenter extends Presenter {

    /** Service asynchrone. */
    private PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService;

    /** Service asynchrone des dimensions. */
    private DimensionServiceGwtAsync dimensionServiceGwtAsync;

    /** Vue associé au presenter. */
    private PersonnePhysiqueMoteurRechercheView view;

    private UtilServiceGwtAsync utilServiceGwtAsync;

    private RemotePagingCriteriasGwt<PersonneCriteresRechercheModel> criteresRechercheEnCours;

    private ConstantesModel constantes;

    private DeskBar deskBar;

    private AideServiceGwtAsync aideService;

    /**
     * Constructeur par defaut.
     * @param eventBus le bus des évenements
     * @param personnePhysiqueRpcService service rpc pour les personnes physique
     * @param dimensionServiceGwtAsync service rpc pour les dimensions
     * @param utilServiceGwtAsync service rpc pour l'utilitaire
     * @param view la vue associé au presenter
     * @param appControllerConstants les constantes d'application
     * @param constantes les constantes
     * @param deskBar deskBar
     * @param aideService service d'aide.
     */
    public PersonnePhysiqueMoteurRecherchePresenter(HandlerManager eventBus, PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService,
        DimensionServiceGwtAsync dimensionServiceGwtAsync, UtilServiceGwtAsync utilServiceGwtAsync, PersonnePhysiqueMoteurRechercheView view,
        AppControllerConstants appControllerConstants, ConstantesModel constantes, DeskBar deskBar, AideServiceGwtAsync aideService) {
        super(eventBus);
        this.personnePhysiqueRpcService = personnePhysiqueRpcService;
        this.dimensionServiceGwtAsync = dimensionServiceGwtAsync;
        this.utilServiceGwtAsync = utilServiceGwtAsync;
        this.constantes = constantes;
        this.view = view;
        this.deskBar = deskBar;
        this.aideService = aideService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View asView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBind() {

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
                rechercherPersonnesPhysique();
            }
        });
        view.getGestionToucheEntreHandler().addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    rechercherPersonnesPhysique();
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
            new SetDataProviderEventHandler<PersonneCriteresRechercheModel, PersonneSimpleModel>() {
                @Override
                public void setDataProvider(SetDataProviderEvent<PersonneCriteresRechercheModel, PersonneSimpleModel> event) {
                    // on recupère les criteres
                    final PersonneCriteresRechercheModel criterias = new PersonneCriteresRechercheModel();
                    criterias.setNumeroClient(view.getTbNumeroClient().getValue().trim());
                    criterias.setNom(view.getTbNom().getValue().trim());
                    criterias.setNomJeuneFille(view.getTbNomJF().getValue().trim());
                    criterias.setPrenom(view.getTbPrenom().getValue().trim());
                    criterias.setNro(view.getTbNumeroRO().getValue().trim());
                    final Date date = view.getCdbDateNaissance().getValue();
                    if (date != null) {
                        criterias.setDateNaissance(DateUtil.getString(date));
                    }
                    criterias.setTelephone(view.getTbTelephone().getValue().trim());
                    criterias.setEmail(view.getTbEmail().getValue().trim());
                    criterias.setNumVoie(view.getTbNumeroVoie().getValue().trim());
                    criterias.setAdresse(view.getTbAdresse().getValue().trim());
                    final List<Long> idsAgences = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelleAgence : view.getAgences().getValue()) {
                        if (idLibelleAgence != null) {
                            idsAgences.add(idLibelleAgence.getIdentifiant());
                        }
                    }
                    criterias.setIdAgences(idsAgences);

                    final List<Long> listeNatures = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbNatureValue().getValue()) {
                        if (idLibelle != null) {
                            listeNatures.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeNatures(listeNatures);
                    final List<Long> listeNaturesVoie = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbNatureVoieValue().getValue()) {
                        if (idLibelle != null) {
                            listeNaturesVoie.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeNaturesVoie(listeNaturesVoie);
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
                    event.getParams().setCriterias(criterias);

                    // Si on triait selon le nom, on triera aussi avec le prénom
                    RemotePagingSortGwt rpsPrenom = null;
                    for (RemotePagingSortGwt rps : event.getParams().getListeSorts()) {
                        if (rps.getSortField().equals(constantes.getProprietePersonneNom())) {
                            rpsPrenom = new RemotePagingSortGwt();
                            rpsPrenom.setSortField(constantes.getProprietePersonnePrenom());
                            rpsPrenom.setSortAsc(rps.getSortAsc());
                        }
                    }
                    if (rpsPrenom != null) {
                        event.getParams().getListeSorts().add(rpsPrenom);
                    }

                    // on affiche le lien pour l'export excel
                    if (criteresRechercheEnCours == null) {
                        view.afficherExportExcel();
                    }
                    // on stocke les parametres pour l'export excel
                    criteresRechercheEnCours = event.getParams();

                    // appel du service
                    personnePhysiqueRpcService.rechercherPersonneFullTextParCriteres(event.getParams(), event.getCallback());
                }
            });
        view.getRemotePagingHandlerManager().addHandler(SetCellClickedEvent.TYPE, new SetCellClickedEventHandler<PersonneSimpleModel>() {
            @Override
            public void setCellClicked(SetCellClickedEvent<PersonneSimpleModel> event) {
                // on envoie un evenement de demande d'ouverture dans le bus
                final OpenPersonEvent openPersonEvent =
                    new OpenPersonEvent(event.getModele().getId(), event.getModele().getNumeroClient(), event.getModele().getNom() != null ? event.getModele()
                            .getNom() : "", event.getModele().getPrenom() != null ? event.getModele().getPrenom() : "",
                        (event.getModele().getNature() != null) ? event.getModele().getNature().getIdentifiant() : null);
                fireEventGlobalBus(openPersonEvent);
            }
        });
        view.getRemotePagingHandlerManager().addHandler(ChercherDoublonEvent.TYPE, new ChercherDoublonEventHandler() {
            @Override
            public void chercherDoublons(ChercherDoublonEvent event) {
                // Affichage d'une popup pour la fusion
                fireEventGlobalBus(new FusionnerPersonnesEvent(event.getNom(), event.getPrenom(), event.getDateNaissance(), null));
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

        view.getSlbNature().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherNaturePersonnePhysiqueParCriteres(criteres, event.getCallBack());
            }
        });

        view.getSlbNatureVoie().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherNatureVoieParCriteres(criteres, event.getCallBack());
            }
        });

        view.getSuggestionAgence().addSuggestHandler(
            new org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {

                @Override
                public void suggest(
                    org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                    final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                    final String libelleAgenceRecherchee = event.getSuggest();
                    criteres.setLibelle(libelleAgenceRecherchee);
                    criteres.setVisible(true);
                    if (libelleAgenceRecherchee == null || "".equals(libelleAgenceRecherchee.trim())) {
                        criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    }
                    dimensionServiceGwtAsync.rechercherAgenceParCriteres(criteres, event.getCallBack());
                }
            });
        final ClickHandler exportExcel = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                utilServiceGwtAsync.mapperCriteresRecherche(criteresRechercheEnCours, new AsyncCallback<Map<String, String>>() {
                    @Override
                    public void onSuccess(Map<String, String> result) {
                        // on ajoute le type du service à appeler et le type de l'objet
                        result.put(constantes.getParamService(), constantes.getValueServiceRecherchePersonnePhysique());
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
        view.getTbNumeroClientFocus().setFocus(true);
    }

    /**
     * Recherche une personne à partir des criteres.
     */
    public void rechercherPersonnesPhysique() {
        view.rechercher();
    }

    /**
     * Lance la recherche pour un appel de la téléphonie.
     * @param numAdherent le numéro de l'adhérent.
     * @param nom le nom de la personne de l'appel.
     * @param numeroTelephone le numéro de téléphone de la personne de l'appel.
     */
    public void lancerRechercheAppelEntrant(String numAdherent, String nom, String numeroTelephone) {
        // Remplit le numéro de client dans le moteur de recherche
        if (numAdherent != null && !"".equals(numAdherent)) {
            view.getTbNumeroClient().setValue(numAdherent);
        }
        // Remplit le nom de la personne dans le moteur de recherche
        if (nom != null && !"".equals(nom)) {
            view.getTbNom().setValue(nom);
        }
        // Remplit le numéro de téléphone dans le moteur de recherche
        if (numeroTelephone != null && !"".equals(numeroTelephone)) {
            view.getTbTelephone().setValue(numeroTelephone);
        }

        // Lance la recherche
        rechercherPersonnesPhysique();
    }

    /**
     * Interface pour la vue PersonnePhysiqueMoteurRechercheView.
     */
    public interface PersonnePhysiqueMoteurRechercheView extends View {
        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbNumeroClient();

        /**
         * Va permettre de mettre le focus sur le premier champ du moteur de recherche.
         * @return le focusable du champ.
         */
        Focusable getTbNumeroClientFocus();

        /**
         * Retourne les agences selectionnées.
         * @return les agences selectionnées.
         */
        HasValue<List<IdentifiantLibelleGwt>> getAgences();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbNom();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbNomJF();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbPrenom();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbNumeroRO();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedCalendrierDateBox getCdbDateNaissance();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbTelephone();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbEmail();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbNumeroVoie();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbAdresse();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<CodePostalCommuneModel> getSlbVille();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<List<CodePostalCommuneModel>> getSlbVilleValue();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnRechercher();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnEffacerRecherche();

        /**
         * Affiche un message de chargement.
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
         * Lance la recherche.
         */
        void rechercher();

        // TODO à remettre éventuellement pour le bug 7339
        // /**
        // * Lance la recherche sur la page en cours.
        // */
        // void rechercherPageEnCours();

        /**
         * Retourne le gestionnaire d'evenement du tableau.
         * @return le gestionnaire d'evenement.
         */
        HandlerManager getRemotePagingHandlerManager();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNature();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbNatureValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureVoie();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbNatureVoieValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbCodePostal();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbCodePostalValue();

        /**
         * Handler pour la suggestion de recherche d'agences.
         * @return le handler.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestionAgence();

        /**
         * Initialiser le moteur de recherche.
         */
        void effacerRecherche();

        /**
         * Gerer la touche entré.
         * @return le handler sur la gestion de la touche entré.
         */
        HasKeyPressHandlers getGestionToucheEntreHandler();

        /**
         * Affiche le bloc d'export excel.
         */
        void afficherExportExcel();

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
