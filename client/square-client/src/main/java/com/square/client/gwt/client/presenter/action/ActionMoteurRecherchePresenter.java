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
package com.square.client.gwt.client.presenter.action;

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
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
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
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.controller.AppControllerViewImpl.ContainerTabPanel;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.event.OpenPersonneMoraleEvent;
import com.square.client.gwt.client.event.RafraichirRechercheActionEvent;
import com.square.client.gwt.client.event.RafraichirRechercheActionEventHandler;
import com.square.client.gwt.client.model.ActionCritereRechercheModel;
import com.square.client.gwt.client.model.ActionNatureResultatCriteresRechercheModel;
import com.square.client.gwt.client.model.ActionRechercheModel;
import com.square.client.gwt.client.model.CampagneCriteresRechercheLibelleModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheObjetModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheResultatActionModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheSousObjetModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.model.PersonneMoraleModel;
import com.square.client.gwt.client.service.ActionServiceGwtAsync;
import com.square.client.gwt.client.service.CampagneServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.service.UtilServiceGwtAsync;
import com.square.client.gwt.client.view.action.moteur.recherche.ActionsMoteurRechercheViewImplConstants;
import com.square.client.gwt.client.view.action.transfert.ActionTransfertViewImpl;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxComposite;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;

/**
 * Presenter pour les moteur de recherche des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionMoteurRecherchePresenter extends Presenter {

    /** Services asynchrones. **/
    /** Le service des actions. */
    ActionServiceGwtAsync actionServiceGwtAsync;

    /** Le service de dimensions. */
    DimensionServiceGwtAsync dimensionServiceGwtAsync;

    /** Le service de campagnes. */
    CampagneServiceGwtAsync campagneServiceGwtAsync;

    /** Le service des personnes. */
    PersonneServiceGwtAsync personneServiceGwtAsync;

    /** Vue associée au presenter. **/
    ActionMoteurRechercheView view;

    /** La ressource connectee. */
    private DimensionRessourceModel ressourceConnectee;

    /** Constantes du model. */
    private ConstantesModel constantesModel;

    private UtilServiceGwtAsync utilServiceGwtAsync;

    private RemotePagingCriteriasGwt<ActionCritereRechercheModel> criteresRechercheEnCours;

    /** service de gestion des aides. */
    private AideServiceGwtAsync aideService;

    private ActionTransfertPresenter actionTransfertPresenter;

    /**
     * Constructeur de moteur recherche action.
     * @param eventBus le bues des evénements
     * @param actionServiceGwtAsync service rpc des actions
     * @param dimensionServiceGwtAsync service rpc pour les dimensions
     * @param campagneServiceGwtAsync service rcp des campagnes
     * @param personneServiceGwtAsync service rcp des personnes
     * @param view la vue assoicées au presenter
     * @param utilServiceGwtAsync service rpc pour l'utilitaire
     * @param appControllerConstants les constantes d'application
     * @param ressourceConnectee la ressource connectée
     * @param constantesModel les constantes du model
     * @param uidRessource identifiant de la ressource.
     * @param aideService aideService
     */
    public ActionMoteurRecherchePresenter(HandlerManager eventBus, ActionServiceGwtAsync actionServiceGwtAsync,
        DimensionServiceGwtAsync dimensionServiceGwtAsync, CampagneServiceGwtAsync campagneServiceGwtAsync, PersonneServiceGwtAsync personneServiceGwtAsync,
        UtilServiceGwtAsync utilServiceGwtAsync, ActionMoteurRechercheView view, AppControllerConstants appControllerConstants,
        DimensionRessourceModel ressourceConnectee, ConstantesModel constantesModel, Long uidRessource, AideServiceGwtAsync aideService) {
        super(eventBus);
        this.actionServiceGwtAsync = actionServiceGwtAsync;
        this.dimensionServiceGwtAsync = dimensionServiceGwtAsync;
        this.campagneServiceGwtAsync = campagneServiceGwtAsync;
        this.personneServiceGwtAsync = personneServiceGwtAsync;
        this.view = view;
        this.ressourceConnectee = ressourceConnectee;
        this.constantesModel = constantesModel;
        this.utilServiceGwtAsync = utilServiceGwtAsync;
        this.aideService = aideService;
    }

    @Override
    public View asView() {
        return this.view;
    }

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
                rechercherAction();
            }
        });
        view.focusPanelKeyPressHandler().addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    rechercherAction();
                }
            }
        });
        view.getBtnEffaceRecherche().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.effacerRecherche();

                // on initialise les valeurs le statut "A faire"
                initValeursListBox(false, false, true);
            }
        });
        view.getRemotePagingHandlerManager().addHandler(SetDataProviderEvent.TYPE,
            new SetDataProviderEventHandler<ActionCritereRechercheModel, ActionRechercheModel>() {
                @Override
                public void setDataProvider(SetDataProviderEvent<ActionCritereRechercheModel, ActionRechercheModel> event) {
                    // On récupère les critères.
                    final ActionCritereRechercheModel criterias = new ActionCritereRechercheModel();
                    // Type
                    criterias.setIdType(view.getLbTypeValue().getValue() == null ? null : view.getLbTypeValue().getValue().getIdentifiant());
                    // Nature
                    final List<Long> listeNature = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getLbNatureValue().getValue()) {
                        if (idLibelle != null) {
                            listeNature.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeNatureActions(listeNature);
                    // Nature résultat
                    final List<Long> listeResultas = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getLbNatureResultatValue().getValue()) {
                        if (idLibelle != null) {
                            listeResultas.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeResultats(listeResultas);
                    // Statut
                    final List<Long> listeStatuts = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getLbStatutValue().getValue()) {
                        if (idLibelle != null) {
                            listeStatuts.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeStatuts(listeStatuts);
                    // Résultat
                    final List<Long> listeResultats = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getLbResultatValue().getValue()) {
                        if (idLibelle != null) {
                            listeResultats.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeResultats(listeResultats);
                    // Priorité
                    final List<Long> listePriorites = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getLbPrioriteValue().getValue()) {
                        if (idLibelle != null) {
                            listePriorites.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListePriorites(listePriorites);
                    // Objet
                    criterias.setIdObjet(view.getLbObjetValue().getValue() == null ? null : view.getLbObjetValue().getValue().getIdentifiant());
                    // Sous objet
                    criterias.setIdSousObjet(view.getLbSousObjetValue().getValue() == null ? null : view.getLbSousObjetValue().getValue().getIdentifiant());
                    // Campagne
                    final List<Long> listeCampagnes = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getLbLibCampagneValue().getValue()) {
                        if (idLibelle != null) {
                            listeCampagnes.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeCampagnes(listeCampagnes);
                    // Date de début
                    final Date dateDebut = view.getCdbDateDebut().getValue();
                    if (dateDebut != null) {
                        criterias.setDateDebutAction(DateUtil.getString(dateDebut));
                    }
                    // Date de fin
                    final Date datefin = view.getCdbDateFin().getValue();
                    if (datefin != null) {
                        criterias.setDateFinAction(DateUtil.getString(datefin));
                    }
                    // Réclamation (si coché : recherche des actions avec réclamation, si non coché : recherche de l'ensemble des actions)
                    if (Boolean.TRUE.equals(view.getCbreclamation().getValue())) {
                        criterias.setReclamation(view.getCbreclamation().getValue());
                    }

                    final List<Long> idsAgences = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbAgenceValue().getValue()) {
                        if (idLibelle != null) {
                            idsAgences.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setIdAgences(idsAgences);

                    final List<Long> idsCommerciaux = new ArrayList<Long>();
                    for (DimensionRessourceModel idLibelle : view.getSlbCommercialValue().getValue()) {
                        if (idLibelle != null) {
                            idsCommerciaux.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setIdCommerciaux(idsCommerciaux);
                    criterias.setRechercheEtEntreAgencesEtCommerciaux(view.getCbRechercheEtEntreAgencesEtCommerciaux().getValue());

                    final List<Long> idsCreateurs = new ArrayList<Long>();
                    for (DimensionRessourceModel idLibelle : view.getSlbCreateurValue().getValue()) {
                        if (idLibelle != null) {
                            idsCreateurs.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setIdCreateurs(idsCreateurs);

                    event.getParams().setCriterias(criterias);

                    // on affiche le lien pour l'export excel
                    if (criteresRechercheEnCours == null) {
                        view.afficherExportExcel();
                    }
                    // on stocke les parametres pour l'export excel
                    criteresRechercheEnCours = event.getParams();

                    // Appel du service
                    actionServiceGwtAsync.rechercherActionParCritere(event.getParams(), event.getCallback());
                }
            });
        view.getRemotePagingHandlerManager().addHandler(SetCellClickedEvent.TYPE, new SetCellClickedEventHandler<ActionRechercheModel>() {
            @Override
            public void setCellClicked(final SetCellClickedEvent<ActionRechercheModel> event) {
                if (event.getModele().getIdpersonne() != null) {
                    personneServiceGwtAsync.rechercherPersonneParId(event.getModele().getIdpersonne(), new AsyncCallback<PersonneBaseModel>() {

                        @Override
                        public void onSuccess(PersonneBaseModel result) {
                            if (result instanceof PersonneModel) {
                                final PersonneModel personne = (PersonneModel) result;
                                fireEventGlobalBus(new OpenPersonEvent(personne.getIdentifiant(), personne.getNumClient(), personne.getNom() != null ? personne
                                        .getNom() : "", personne.getPrenom() != null ? personne.getPrenom() : "",
                                    personne.getNaturePersonne() != null ? personne.getNaturePersonne().getIdentifiant() : null, event.getModele().getId(),
                                    null));
                            }
                            else {
                                final PersonneMoraleModel personneMorale = (PersonneMoraleModel) result;
                                fireEventGlobalBus(new OpenPersonneMoraleEvent(event.getModele().getIdpersonne(),
                                    (personneMorale.getNature() != null) ? personneMorale.getNature().getIdentifiant() : null, personneMorale
                                            .getRaisonSociale(), event.getModele().getId()));
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            view.onRpcServiceFailure(new ErrorPopupConfiguration(caught.getMessage()));
                        }
                    });
                }

            }
        });
        // On récupère les valeurs des tables de dimension
        view.getLbType().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionServiceGwtAsync.rechercherTypesActions(criteres, event.getCallBack());
            }
        });
        view.getLbTypeValue().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                view.nettoyerObjet();
                view.nettoyerSousObjet();
            }
        });
        view.getLbObjetValue().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                view.nettoyerSousObjet();
            }
        });
        view.getLbResultat().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCritereRechercheResultatActionModel criteresResultat = new DimensionCritereRechercheResultatActionModel();
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                criteresResultat.setDimensionCriteres(criteres);
                dimensionServiceGwtAsync.rechercherResultatActions(criteresResultat, event.getCallBack());
            }
        });
        view.getLbNature().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionServiceGwtAsync.rechercherNatureActions(criteres, event.getCallBack());
            }
        });
        view.getLbNatureResultat().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final ActionNatureResultatCriteresRechercheModel criteres = new ActionNatureResultatCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionServiceGwtAsync.rechercherNatureResultatActions(criteres, event.getCallBack());
            }
        });
        view.getLbStatut().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionServiceGwtAsync.rechercherStatutActions(criteres, event.getCallBack());
            }
        });
        view.getLbPriorite().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionServiceGwtAsync.rechercherPrioriteActions(criteres, event.getCallBack());
            }
        });
        view.getLbObjet().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCritereRechercheObjetModel critereObjet = new DimensionCritereRechercheObjetModel();
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                if (view.getLbTypeValue().getValue() != null) {
                    critereObjet.setIdType(view.getLbTypeValue().getValue().getIdentifiant());
                }
                criteres.setLibelle(event.getSuggest());
                critereObjet.setDimensionCriteres(criteres);
                dimensionServiceGwtAsync.rechercherObjetActions(critereObjet, event.getCallBack());
            }
        });
        view.getLbSousObjet().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCritereRechercheSousObjetModel critereSousObjet = new DimensionCritereRechercheSousObjetModel();
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                if (view.getLbObjetValue().getValue() != null) {
                    critereSousObjet.setIdObjet(view.getLbObjetValue().getValue().getIdentifiant());
                }
                criteres.setLibelle(event.getSuggest());
                critereSousObjet.setDimensionCriteres(criteres);
                dimensionServiceGwtAsync.rechercherSousObjetActions(critereSousObjet, event.getCallBack());
            }
        });
        view.getLbLibCampagne().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final CampagneCriteresRechercheLibelleModel critereCampagne = new CampagneCriteresRechercheLibelleModel();
                critereCampagne.setLibelle(event.getSuggest());
                campagneServiceGwtAsync.rechercherCampagnesParLibelle(critereCampagne, event.getCallBack());
            }
        });
        view.getSlbAgence().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionServiceGwtAsync.rechercherAgenceParCriteres(criteres, event.getCallBack());
            }
        });
        // Handler de changement de la selection
        view.getDecoratedSblCommercial().addValueChangeHandler(new ValueChangeHandler<List<DimensionRessourceModel>>() {
            @Override
            public void onValueChange(ValueChangeEvent<List<DimensionRessourceModel>> event) {
                // si il n'y a qu'un commercial de sélectionné
                if (event.getValue() != null && event.getValue().size() == 1) {
                    view.changeStyleRessourceBarre(event.getValue().get(0).getEtat().getIdentifiant());
                    // On renseigne l'agence si elle n'est pas renseignée
                    if (view.getSlbAgenceValue().getValue() == null || view.getSlbAgenceValue().getValue().isEmpty()) {
                        final Long idRessource = (event.getValue() == null) ? null : event.getValue().get(0).getIdentifiant();
                        dimensionServiceGwtAsync.rechercherAgenceParIdRessource(idRessource, new AsyncCallback<IdentifiantLibelleGwt>() {
                            @Override
                            public void onSuccess(IdentifiantLibelleGwt result) {
                                final List<IdentifiantLibelleGwt> listValue = new ArrayList<IdentifiantLibelleGwt>();
                                listValue.add(result);
                                view.getSlbAgenceValue().setValue(listValue);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                            }
                        });
                    }
                }
            }
        });
        view.getSlbCreateur().addValueChangeHandler(new ValueChangeHandler<List<DimensionRessourceModel>>() {
            @Override
            public void onValueChange(ValueChangeEvent<List<DimensionRessourceModel>> event) {
                // si il n'y a qu'une personne de sélectionnée
                if (event.getValue() != null && event.getValue().size() == 1) {
                    view.changeStyleCreateurBarre(event.getValue().get(0).getEtat().getIdentifiant());
                }
            }
        });
        final SuggestListBoxSuggestEventHandler<DimensionRessourceModel> handlerRessource = new SuggestListBoxSuggestEventHandler<DimensionRessourceModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<DimensionRessourceModel> event) {
                final DimensionCriteresRechercheRessourceModel criteres = new DimensionCriteresRechercheRessourceModel();
                final String libelleRessourceRecherchee = event.getSuggest();
                criteres.setNom(libelleRessourceRecherchee);
                criteres.setPrenom(libelleRessourceRecherchee);
                criteres.setIsSupprime(false);
                if (libelleRessourceRecherchee == null || "".equals(libelleRessourceRecherchee.trim())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                final List<Long> idsAgences = new ArrayList<Long>();
                for (IdentifiantLibelleGwt idLibelle : view.getSlbAgenceValue().getValue()) {
                    if (idLibelle != null) {
                        idsAgences.add(idLibelle.getIdentifiant());
                    }
                }
                criteres.setIdAgences(idsAgences);
                dimensionServiceGwtAsync.rechercherRessourceParCriteres(criteres, event.getCallBack());
            }
        };
        view.getSlbCommercial().addSuggestHandler(handlerRessource);
        final SuggestListBoxSuggestEventHandler<DimensionRessourceModel> handlerCreateur = new SuggestListBoxSuggestEventHandler<DimensionRessourceModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<DimensionRessourceModel> event) {
                final DimensionCriteresRechercheRessourceModel criteres = new DimensionCriteresRechercheRessourceModel();
                final String libelleRessourceRecherchee = event.getSuggest();
                criteres.setNom(libelleRessourceRecherchee);
                criteres.setPrenom(libelleRessourceRecherchee);
                criteres.setIsSupprime(false);
                if (libelleRessourceRecherchee == null || "".equals(libelleRessourceRecherchee.trim())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherRessourceParCriteres(criteres, event.getCallBack());
            }
        };
        view.getSlbCreateur().addSuggestHandler(handlerCreateur);

        // ajout Handler sur la touche Entrée
        final KeyPressHandler keyEnterHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getCharCode() == KeyCodes.KEY_ENTER) {
                    rechercherAction();
                }
            }
        };

        view.getCdbDateDebut().addKeyPressHandler(keyEnterHandler);
        view.getCdbDateFin().addKeyPressHandler(keyEnterHandler);
        view.getCbreclamation().addKeyPressHandler(keyEnterHandler);
        view.getCbRechercheEtEntreAgencesEtCommerciauxKeyPress().addKeyPressHandler(keyEnterHandler);
        view.getCbRechercheEtEntreAgencesEtCommerciaux().setValue(Boolean.TRUE);

        final ClickHandler exportExcel = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                utilServiceGwtAsync.mapperCriteresRecherche(criteresRechercheEnCours, new AsyncCallback<Map<String, String>>() {
                    @Override
                    public void onSuccess(Map<String, String> result) {
                        // on ajoute le type du service à appeler et le type de l'objet
                        result.put(constantesModel.getParamService(), constantesModel.getValueServiceRechercheAction());
                        // on transforme la map en liste de params
                        final List<ParamServlet> listeParams = new ArrayList<ParamServlet>();
                        for (String key : result.keySet()) {
                            listeParams.add(new ParamServlet(key, result.get(key)));
                        }
                        // on cree l'url complete
                        final String urlServleComplete = ServletUtil.formatUrl(constantesModel.getUrlServletExporterRecherche(), listeParams);
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

        // menu contextuel
        view.getTransfererActionsHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // on n'affiche que si filtre Statut = A faire
                final List<IdentifiantLibelleGwt> listeStatuts = view.getSlbStatutValue().getValue();
                if (listeStatuts.size() != 1 || (listeStatuts.size() == 1
                        && !listeStatuts.get(0).getIdentifiant().equals(constantesModel.getIdStatutAFaire()))) {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(view.getViewConstants().erreurFiltrageStatutAFaire()));
                }
                else if (criteresRechercheEnCours == null || view.getRemotePagingTable().getCurrentList() == null
                    || view.getRemotePagingTable().getCurrentList().size() == 0) {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(view.getViewConstants().erreurFiltrageAucunResultat()));
                }
                else {
                    if (actionTransfertPresenter == null) {
                        actionTransfertPresenter =
                            addChildPresenter(new ActionTransfertPresenter(eventBus, new ActionTransfertViewImpl(constantesModel.isHasRoleAdmin()),
                                actionServiceGwtAsync, dimensionServiceGwtAsync, criteresRechercheEnCours.getCriterias(), ressourceConnectee, aideService));
                        actionTransfertPresenter.showPresenter(null);
                        actionTransfertPresenter.addEventHandlerToLocalBus(RafraichirRechercheActionEvent.TYPE, new RafraichirRechercheActionEventHandler() {
                            @Override
                            public void rafraichirRechercheAction(RafraichirRechercheActionEvent event) {
                                rechercherAction();
                            }
                        });
                    }
                    else {
                        actionTransfertPresenter.afficher(criteresRechercheEnCours.getCriterias());
                    }
                }
            }
        });
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

        // on ajoute le menu contextuel si il a le bon role
        if (constantesModel.isHasRoleAnimateur()) {
            ((ContainerTabPanel) container).addContextMenu(view.asContextMenuWidget());
        }

        // on initialise les valeurs avec l'utilisateur connecté, son agent et le statut "A faire"
        initValeursListBox(true, true, true);
    }

    private void initValeursListBox(boolean selectAgence, boolean selectRessource, boolean selectStatut) {
        if (selectRessource) {
            // Utilisateur connecté
            final List<DimensionRessourceModel> ressources = new ArrayList<DimensionRessourceModel>();
            ressources.add(ressourceConnectee);
            view.getSlbCommercialValue().setValue(ressources);
        }
        if (selectAgence) {
            // Agence de l'utilisateur connecté
            final List<IdentifiantLibelleGwt> agences = new ArrayList<IdentifiantLibelleGwt>();
            agences.add(ressourceConnectee.getAgence());
            view.getSlbAgenceValue().setValue(agences);
        }
        if (selectStatut) {
            // Statut "A faire"
            final List<IdentifiantLibelleGwt> statuts = new ArrayList<IdentifiantLibelleGwt>();
            statuts.add(constantesModel.getIdLibelleStatutAFaire());
            view.getSlbStatutValue().setValue(statuts);
        }
    }

    /**
     * Recherche des actions à partir des critères.
     */
    void rechercherAction() {
    	lancerRechercheActions();
    }

    /** Lance la recherche des actions. */
    private void lancerRechercheActions() {
        view.getRemotePagingTable().rechercher();
    }

    /**
     * Interface pour la vue du moteur de recherche des actions.
     */
    public interface ActionMoteurRechercheView extends View {

        /**
         * Key press sur focus panel.
         * @return le widget
         */
        HasKeyPressHandlers focusPanelKeyPressHandler();

        /**
         * Le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbType();

        /**
         * Le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleGwt> getLbTypeValue();

        /**
         * Le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbObjet();

        /**
         * Le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleGwt> getLbObjetValue();

        /**
         * Le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbSousObjet();

        /**
         * Le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleGwt> getLbSousObjetValue();

        /**
         * Le widget.
         * @return le widget
         */
        DecoratedCalendrierDateBox getCdbDateDebut();

        /**
         * Le widget.
         * @return le widget
         */
        DecoratedCalendrierDateBox getCdbDateFin();

        /**
         * Renvoi la valeur de statutValue.
         * @return statutValue.
         */
        HasValue<List<IdentifiantLibelleGwt>> getLbStatutValue();

        /**
         * Renvoi la valeur de slbStatut.
         * @return le slbStatut
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbStatut();

        /**
         * Le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbNature();

        /**
         * Le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getLbNatureValue();

        /**
         * Le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbResultat();

        /**
         * Le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getLbResultatValue();

        /**
         * Le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbLibCampagne();

        /**
         * Le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getLbLibCampagneValue();

        /**
         * Le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbPriorite();

        /**
         * Le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getLbPrioriteValue();

        /**
         * Le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbNatureResultat();

        /**
         * Le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getLbNatureResultatValue();

        /**
         * Le widget.
         * @return le widget
         */
        CheckBox getCbreclamation();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnEffaceRecherche();

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
         * Retourne le tableau d'affichage des actions.
         * @return le tableau.
         */
        RemotePagingTable<ActionRechercheModel, ActionCritereRechercheModel> getRemotePagingTable();

        /**
         * Retourne le gestionnaire d'evenement du tableau.
         * @return le gestionnaire d'evenement.
         */
        HandlerManager getRemotePagingHandlerManager();

        /**
         * Retournes les constantes.
         * @return les constantes.
         */
        ActionsMoteurRechercheViewImplConstants getViewConstants();

        /**
         * Récupère le critère de recherche du créateur.
         * @return critère de recherche du créateur.
         */
        DecoratedSuggestListBoxComposite<DimensionRessourceModel> getSlbCreateur();

        /**
         * Récupère la valeur de la ressource attribué à selectionné.
         * @return la valeur de la ressource attribué à selectionné.
         */
        HasValue<List<DimensionRessourceModel>> getSlbCommercialValue();

        /**
         * Récupère le critère de recherche de la ressource attribué à.
         * @return critère de recherche de la ressource attribué à.
         */
        HasSuggestListBoxHandler<DimensionRessourceModel> getSlbCommercial();

        /**
         * Récupère le critère de recherche du créateur.
         * @return critère de recherche du créateur.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbAgence();

        /**
         * Récupère la valeur de l'agence selectionnée.
         * @return la valeur de l'agence selectionnée.
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbAgenceValue();

        /**
         * Récupère la valeur du créateur selectionné.
         * @return la valeur du créateur selectionné.
         */
        HasValue<List<DimensionRessourceModel>> getSlbCreateurValue();

        /**
         * Handler pour la suggestion de recherche de commerciaux.
         * @return le handler.
         */
        DecoratedSuggestListBoxComposite<DimensionRessourceModel> getDecoratedSblCommercial();

        /**
         * Initialiser le moteur de recherche.
         */
        void effacerRecherche();

        /**
         * Nettoyer objet.
         */
        void nettoyerObjet();

        /**
         * Nettoyer Sous objet.
         */
        void nettoyerSousObjet();

        /**
         * Récupère la valeur du statut selectionné.
         * @return la valeur du statut selectionné.
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbStatutValue();

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
         * Handler sur la checkBox de la recherche de type Et entre agences et commerciaux.
         * @return handler
         */
        HasValue<Boolean> getCbRechercheEtEntreAgencesEtCommerciaux();

        /**
         * Handler sur la checkBox de la recherche de type Et entre agences et commerciaux pour les évènements KeyPress.
         * @return handler
         */
        HasKeyPressHandlers getCbRechercheEtEntreAgencesEtCommerciauxKeyPress();

        /**
         * Recuperer le menu context.
         * @return .
         */
        Widget asContextMenuWidget();

        /**
         * Boutton pour transferer des actions.
         * @return le composant
         */
        HasClickHandlers getTransfererActionsHandler();

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

        /**
         * Permet d'ajouter ou d'enlever le style barré du texte pour les créateurs.
         * @param type le type (actif ou non)
         */
        void changeStyleCreateurBarre(Long type);

        /**
         * Permet d'ajouter ou d'enlever le style barré du texte pour les ressources.
         * @param type le type (actif ou non)
         */
        void changeStyleRessourceBarre(Long type);
    }

    /*
     * (non-Javadoc)
     * @see org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter#onDetach()
     */
    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

}
