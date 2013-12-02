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
package com.square.client.gwt.client.presenter.personne.physique.opportunites;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.event.opportunites.AjoutOpportuniteEvent;
import com.square.client.gwt.client.exception.ConfirmationCreationOpportuniteExceptionGwt;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.CampagneCriteresRechercheLibelleModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheObjetModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheSousObjetModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.OpportuniteModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.service.CampagneServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.OpportuniteServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.personne.physique.opportunites.creation.OpportuniteCreationViewImpl;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEventHandler;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;

/**
 * Presenter de la vue de création d'opportunité.
 * @author cblanchard - SCUB
 */
public class OpportuniteCreationPresenter extends Presenter {

    /** La vue. */
    private OpportuniteCreationView view;

    /** L'identifiant de la personne. */
    private Long idPersonne;

    /** Le service des opportunités. */
    private OpportuniteServiceGwtAsync opportuniteRpcService;

    /** Le service des dimensions. */
    private DimensionServiceGwtAsync dimensionRpcService;

    /** Le service des campagnes. */
    private CampagneServiceGwtAsync campagneRpcService;

    /** Le service des personnes. */
    private PersonneServiceGwtAsync personneRpcService;

    /** La ressource connectee. */
    private DimensionRessourceModel ressourceConnectee;

    /** Booléen pour savoir s'il faut forcer ou non la création de l'opportunité. */
    private Boolean forcerCreation;

    /** Constantes de l'application. */
    private ConstantesModel constantes;

    private OpportuniteConstants constantesOpportunites;

    private DeskBar deskBar;

    /** service de gestion d'aide . */
    private AideServiceGwtAsync aideService;

    private HandlerRegistration deskBarRegistration;

    /**
     * Constructeur.
     * @param eventBus le bus
     * @param opportuniteCreationViewImpl la vue
     * @param idPersonne l'identifiant de la personne
     * @param ressourceConnectee ressource connecté
     * @param forcerCreation booléen pour savoir s'il faut forcer ou non la création de l'opportunité
     * @param opportuniteRpcService le service des opportunités
     * @param dimensionServiceRpcService le service de dimension
     * @param campagneRpcService le service des campagnes
     * @param personneRpcService le service des personnes
     * @param constantes les constantes de l'application
     * @param deskBar deskBar
     * @param aideService service d'aide.
     */
    public OpportuniteCreationPresenter(HandlerManager eventBus, OpportuniteCreationViewImpl opportuniteCreationViewImpl, Long idPersonne,
        DimensionRessourceModel ressourceConnectee, Boolean forcerCreation, OpportuniteServiceGwtAsync opportuniteRpcService,
        DimensionServiceGwtAsync dimensionServiceRpcService, CampagneServiceGwtAsync campagneRpcService, PersonneServiceGwtAsync personneRpcService,
        ConstantesModel constantes, DeskBar deskBar, AideServiceGwtAsync aideService) {
        super(eventBus);
        this.view = opportuniteCreationViewImpl;
        this.idPersonne = idPersonne;
        this.opportuniteRpcService = opportuniteRpcService;
        this.dimensionRpcService = dimensionServiceRpcService;
        this.campagneRpcService = campagneRpcService;
        this.personneRpcService = personneRpcService;
        this.ressourceConnectee = ressourceConnectee;
        this.forcerCreation = forcerCreation;
        this.constantes = constantes;
        this.constantesOpportunites = GWT.create(OpportuniteConstants.class);
        this.deskBar = deskBar;
        this.aideService = aideService;
    }

    @Override
    public View asView() {
        return view;
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
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
            }
        });
        // Alimentation de la nature
        view.getSlbsNature().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                criteres.setVisible(true);
                dimensionRpcService.rechercherNatureActions(criteres, event.getCallBack());

            }
        });
        // Alimentation de la campagne
        view.getSlbsCampagne().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final CampagneCriteresRechercheLibelleModel criteres = new CampagneCriteresRechercheLibelleModel();
                criteres.setLibelle(event.getSuggest());
                campagneRpcService.rechercherCampagnesParLibelle(criteres, event.getCallBack());
            }
        });
        // Alimentation des agenges
        view.getSlbsAgence().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                criteres.setVisible(true);
                dimensionRpcService.rechercherAgenceParCriteres(criteres, event.getCallBack());
            }
        });
        // Alimentation des ressources
        view.getSlbsRessource().addSuggestHandler(new SuggestListBoxSuggestEventHandler<DimensionRessourceModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<DimensionRessourceModel> event) {
                final DimensionCriteresRechercheRessourceModel criteres = new DimensionCriteresRechercheRessourceModel();
                final String libelleRessourceRecherchee = event.getSuggest();
                if (view.getSlbsAgenceValue() != null && view.getSlbsAgenceValue().getValue() != null) {
                    criteres.setIdAgence(view.getSlbsAgenceValue().getValue().getIdentifiant());
                }
                criteres.setNom(libelleRessourceRecherchee);
                criteres.setPrenom(libelleRessourceRecherchee);
                // Recherche des ressources actives
                final List<Long> listeIdsEtats = new ArrayList<Long>();
                listeIdsEtats.add(constantes.getIdEtatActifRessource());
                criteres.setIdEtats(listeIdsEtats);
                dimensionRpcService.rechercherRessourceParCriteres(criteres, event.getCallBack());
            }
        });

        // Changement de ressource
        view.getSlbsCommercial().addValueChangeHandler(new ValueChangeHandler<DimensionRessourceModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<DimensionRessourceModel> event) {
                final Long idRessource = event.getValue().getIdentifiant();
                dimensionRpcService.rechercherAgenceParIdRessource(idRessource, new AsyncCallback<IdentifiantLibelleGwt>() {
                    @Override
                    public void onSuccess(IdentifiantLibelleGwt result) {
                        view.getSlbsAgenceValue().setValue(result);
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }
                });
                //
            }
        });

        // Alimentation des types
        view.getSlbsType().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                criteres.setVisible(true);
                dimensionRpcService.rechercherTypesActions(criteres, event.getCallBack());
            }
        });
        // Alimentation des objets
        view.getSlbsObjet().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCritereRechercheObjetModel criteres = new DimensionCritereRechercheObjetModel();
                final DimensionCriteresRechercheModel criteresGeneraux = new DimensionCriteresRechercheModel();
                criteresGeneraux.setLibelle(event.getSuggest());
                criteresGeneraux.setVisible(true);
                criteres.setDimensionCriteres(criteresGeneraux);
                if (view.getSlbsTypeValue() != null && view.getSlbsTypeValue().getValue() != null) {
                    criteres.setIdType(view.getSlbsTypeValue().getValue().getIdentifiant());
                }
                dimensionRpcService.rechercherObjetActions(criteres, event.getCallBack());
            }
        });
        // Alimentation des sousObjets
        view.getSlbsSousObjet().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCritereRechercheSousObjetModel criteres = new DimensionCritereRechercheSousObjetModel();
                final DimensionCriteresRechercheModel criteresGeneraux = new DimensionCriteresRechercheModel();
                criteresGeneraux.setLibelle(event.getSuggest());
                criteresGeneraux.setVisible(true);
                criteres.setDimensionCriteres(criteresGeneraux);
                if (view.getSlbsObjetValue() != null && view.getSlbsObjetValue().getValue() != null) {
                    criteres.setIdObjet(view.getSlbsObjetValue().getValue().getIdentifiant());
                }
                dimensionRpcService.rechercherSousObjetActions(criteres, event.getCallBack());
            }
        });
        // Alimentation de la personne
        personneRpcService.rechercherPersonneParId(idPersonne, new AsyncCallback<PersonneBaseModel>() {
            @Override
            public void onSuccess(PersonneBaseModel result) {
                if (result instanceof PersonneModel) {
                    final PersonneModel personne = (PersonneModel) result;
                    view.getlPersonne().setText(personne.getNom() + " " + personne.getPrenom());
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));

            }
        });

        // Evenement sur le bouton enregistrer
        view.getBtnEnregistrer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                enregistrementOpportunite();
                view.criserBoutonEnregister();
            }
        });

        // Evenément sur la touche entrée.
        view.getAllKeyHandlersFocusPanel().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                final int keyCode = event.getNativeKeyCode();
                if (keyCode == KeyCodes.KEY_ENTER) {
                    enregistrementOpportunite();
                } else if (keyCode == KeyCodes.KEY_ESCAPE) {
                    view.hidePopup();
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

    private void enregistrementOpportunite() {
        // Récupération des valeurs
        final OpportuniteModel opportuniteModel = new OpportuniteModel();
        opportuniteModel.setIdPersonnePhysique(idPersonne);
        if (view.getSlbsNatureValue() != null && view.getSlbsNatureValue().getValue() != null) {
            opportuniteModel.setIdNature(view.getSlbsNatureValue().getValue().getIdentifiant());
        }
        if (view.getSlbsCampagneValue() != null && view.getSlbsCampagneValue().getValue() != null) {
            opportuniteModel.setIdCampagne(view.getSlbsCampagneValue().getValue().getIdentifiant());
        }
        if (view.getSlbsAgenceValue() != null && view.getSlbsAgenceValue().getValue() != null) {
            opportuniteModel.setIdAgence(view.getSlbsAgenceValue().getValue().getIdentifiant());
        }
        if (view.getSlbsRessourceValue() != null && view.getSlbsRessourceValue().getValue() != null) {
            opportuniteModel.setIdRessource(view.getSlbsRessourceValue().getValue().getIdentifiant());
        }
        if (view.getSlbsTypeValue() != null && view.getSlbsTypeValue().getValue() != null) {
            opportuniteModel.setIdType(view.getSlbsTypeValue().getValue().getIdentifiant());
        }
        if (view.getSlbsObjetValue() != null && view.getSlbsObjetValue().getValue() != null) {
            opportuniteModel.setIdObjet(view.getSlbsObjetValue().getValue().getIdentifiant());
        }
        if (view.getSlbsSousObjetValue() != null && view.getSlbsSousObjetValue().getValue() != null) {
            opportuniteModel.setIdSousObjet(view.getSlbsSousObjetValue().getValue().getIdentifiant());
        }
        opportuniteModel.setCreationForcee(forcerCreation);
        // Appel au service
        opportuniteRpcService.creerOpportunite(opportuniteModel, new AsyncCallback<OpportuniteModel>() {
            @Override
            public void onSuccess(final OpportuniteModel opportunite) {
                // Récupération des informations de la personne
                personneRpcService.rechercherPersonneParId(idPersonne, new AsyncCallback<PersonneBaseModel>() {

                    @Override
                    public void onSuccess(final PersonneBaseModel resultPersonne) {

                        view.hidePopup();
                        final PopupInfoConfiguration config = new PopupInfoConfiguration(constantesOpportunites.notificationOpportuniteCreee(),
                            AppControllerConstants.NOTIFICATION_TIME_OUT);
                        config.setCallback(new PopupCallback() {

                            @Override
                            public void onResult(boolean result) {
                                if (resultPersonne instanceof PersonneModel) {
                                    final PersonneModel personnePhysique = (PersonneModel) resultPersonne;
                                    fireEventGlobalBus(new OpenPersonEvent(idPersonne, personnePhysique.getNumClient(),
                                    personnePhysique.getNom() != null ? personnePhysique.getNom() : "",
                                    personnePhysique.getPrenom() != null ? personnePhysique.getPrenom() : "",
                                    (personnePhysique.getNaturePersonne() != null) ? personnePhysique.getNaturePersonne().getIdentifiant() : null,
                                    null, opportunite.getIdOpportunite()));
                                }
                            }
                        });
                        new DecoratedInfoPopup(config).show();
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }
                });
            }

            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    // Passage de l'exception vers les composants
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    view.masquerLoadingPopup();
                    view.activerBoutonEnregister();
                }
                // Récupération de l'exception de création forcée.
                else if (caught instanceof ConfirmationCreationOpportuniteExceptionGwt) {
                    view.hidePopup();
                    fireEventLocalBus(new AjoutOpportuniteEvent(idPersonne));
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    view.activerBoutonEnregister();
                }

            }
        });
    }

    /**
     * affichage & réinitialisation des champs.
     */
    public void afficher() {
     // On pré-remplit l'agence et la ressource à partir des informations de la ressource connectée.
        view.getSlbsRessourceValue().setValue(ressourceConnectee);
        view.getSlbsAgenceValue().setValue(ressourceConnectee.getAgence());
        // On pré-remplit le type avec "Opportunité
        view.getSlbsTypeValue().setValue(constantes.getIdLibelleTypeOpportunite());
        view.getSlbsCampagneValue().setValue(new IdentifiantLibelleGwt());
        view.getSlbsNatureValue().setValue(new IdentifiantLibelleGwt());
        view.getSlbsObjetValue().setValue(new IdentifiantLibelleGwt());
        view.getSlbsSousObjetValue().setValue(new IdentifiantLibelleGwt());

        // On affiche la popup.
        view.showPopup();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                view.setFocus();
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

        afficher();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                // on ajoute la popup à la deskBar
                deskBar.addPopup(view.getMinimizablePopup());
            }
        });
    }

    /**
     * Interface de la vue de création.
     */
    public interface OpportuniteCreationView extends View {
        /** Affiche la popup de création. */
        void showPopup();

        /**
         * Initialise le focus pour cette vue.
         */
        void setFocus();

        /** Masque la popup de chargement. */
        void masquerLoadingPopup();

        /**
         * Renvoi le gestionnaire d'erreur.
         * @return le gestionnaire d'erreur
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Affiche une popup d'erreur.
         * @param errorPopupConfiguration la configuration de la popup
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /** Masque la popup. */
        void hidePopup();

        /**
         * Renvoi la valeur de lPersonne.
         * @return lPersonne
         */
        HasText getlPersonne();

        /**
         * Renvoi la valeur de slbsNature.
         * @return slbsNature
         */
        HasValue<IdentifiantLibelleGwt> getSlbsNatureValue();

        /**
         * Renvoi la valeur de slbsCampagne.
         * @return slbsCampagne
         */
        HasValue<IdentifiantLibelleGwt> getSlbsCampagneValue();

        /**
         * Renvoi la valeur de slbsAgence.
         * @return slbsAgence
         */
        HasValue<IdentifiantLibelleGwt> getSlbsAgenceValue();

        /**
         * Renvoi la valeur de slbsRessource.
         * @return slbsRessource
         */
        HasValue<DimensionRessourceModel> getSlbsRessourceValue();

        /**
         * Renvoi la valeur de slbsType.
         * @return slbsType
         */
        HasValue<IdentifiantLibelleGwt> getSlbsTypeValue();

        /**
         * Renvoi la valeur de slbsObjet.
         * @return slbsObjet
         */
        HasValue<IdentifiantLibelleGwt> getSlbsObjetValue();

        /**
         * Renvoi la valeur de slbsSousObjet.
         * @return slbsSousObjet
         */
        HasValue<IdentifiantLibelleGwt> getSlbsSousObjetValue();

        /**
         * Renvoi la valeur de slbsNature.
         * @return slbsNature
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsNature();

        /**
         * Renvoi la valeur de slbsCampagne.
         * @return slbsCampagne
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsCampagne();

        /**
         * Renvoi la valeur de slbsAgence.
         * @return slbsAgence
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsAgence();

        /**
         * Renvoi la valeur de slbsRessource.
         * @return slbsRessource
         */
        HasSuggestListBoxHandler<DimensionRessourceModel> getSlbsRessource();

        /**
         * Renvoi la valeur de slbsType.
         * @return slbsType
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsType();

        /**
         * Renvoi la valeur de slbsObjet.
         * @return slbsObjet
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsObjet();

        /**
         * Renvoi la valeur de slbsSousObjet.
         * @return slbsSousObjet
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsSousObjet();

        /**
         * Renvoi la valeur de btnAnnuler.
         * @return btnAnnuler
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Renvoi la valeur de btnEnregistrer.
         * @return btnEnregistrer
         */
        HasClickHandlers getBtnEnregistrer();

        /**
         * Renvoie le handler de slbsCommercial.
         * @return slbsCommercial
         */
        DecoratedSuggestListBoxSingle<DimensionRessourceModel> getSlbsCommercial();

        /**
         * Permet de griser le bouton d'enregitrement d'une opportunité.
         */
        void criserBoutonEnregister();

        /**
         * Permet d'activer le bouton d'enregitrement d'une opportunité.
         */
        void activerBoutonEnregister();

        /**
         * Accesseur AllKeyHandlers focus panel.
         * @return le handler
         */
        HasAllKeyHandlers getAllKeyHandlersFocusPanel();

        /**
         * Active/Désactive le bouton réduire.
         * @param enabled flag
         */
        void activerBoutonReduire(boolean enabled);

        /**
         * Récupère la popup minimisable.
         * @return la popup minimisable
         */
        IsMinimizable getMinimizablePopup();

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
        deskBarRegistration.removeHandler();
    }

}
