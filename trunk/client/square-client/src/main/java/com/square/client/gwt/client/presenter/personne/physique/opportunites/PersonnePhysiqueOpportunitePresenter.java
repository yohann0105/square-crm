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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.confirm.ConfirmPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.PopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.UpdateTabNameEvent;
import com.square.client.gwt.client.event.VoirActionLieeOpportuniteEvent;
import com.square.client.gwt.client.event.actions.RefreshActionsEvent;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.OpportuniteCriteresRechercheModel;
import com.square.client.gwt.client.model.OpportuniteModificationModel;
import com.square.client.gwt.client.model.OpportuniteSimpleModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.OpportuniteServiceGwtAsync;
import com.square.client.gwt.client.service.PersonnePhysiqueServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.personne.physique.opportunites.gestion.PersonnePhysiqueOpportunitesViewImplConstants;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.event.MiseAJourOpportuniteEvent;
import com.square.composant.tarificateur.square.client.event.MiseAJourOpportuniteEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesMajInfosAdhesionEvent;
import com.square.composant.tarificateur.square.client.event.SuccesMajInfosAdhesionEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesTransfertEvent;
import com.square.composant.tarificateur.square.client.event.SuccesTransfertEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesTransformationAiaEvent;
import com.square.composant.tarificateur.square.client.event.SuccesTransformationAiaEventHandler;
import com.square.composant.tarificateur.square.client.event.SuppressionOpportuniteEvent;
import com.square.composant.tarificateur.square.client.event.SuppressionOpportuniteEventHandler;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPersonneSyncModel;
import com.square.composant.tarificateur.square.client.model.opportunite.InfosOpportuniteModel;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.HasCloseBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.HasOpenBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEventHandler;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Présenter de la page de gestion des opportunités.
 * @author cblanchard - SCUB
 */
public class PersonnePhysiqueOpportunitePresenter extends Presenter {

    /** Le service des opportunités. */
    private OpportuniteServiceGwtAsync opportuniteRpcService;

    /** Le service des dimensions. */
    private DimensionServiceGwtAsync dimensionRpcService;

    /** Le service des personnes. */
    private PersonneServiceGwtAsync personneRpcService;

    /** Le service des personnes. */
    private PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService;

    /** La vue. */
    private PersonnePhysiqueOpportunitesView view;

    /** L'identifiant de la personne. */
    private Long idPersonne;

    /** La liste des identifiants des opportunités à ouvrir. */
    private List<Long> listeIdsOpportunitesAOuvrir;

    /** Les constantes. */
    private ConstantesModel constantesModel;

    private OpportuniteConstants constantesOpportunites;

    private DimensionRessourceModel utilisateurConnecte;

    private DeskBar deskBar;

    private AideServiceGwtAsync aideService;

    /** Liste des composants tarificateurs embarqués. */
    private List<ComposantTarificateur> composantsTarificateurs;
    private Map<Long, OpportuniteBlocView> blocsOpportunites;

    /**
     * Constructeur du présenteur de la gestion des opportunités.
     * @param eventBus le bus
     * @param opportuniteRpcService le service d'opportunité
     * @param dimensionServiceRpcService le service de dimension
     * @param personneRpcService le service de personne
     * @param personnePhysiqueRpcService le service de personne physique
     * @param viewOpportunite la vue des opportunités
     * @param idPersonne l'identifiant de la personne
     * @param constantes les constantes
     * @param listeIdsOpportunitesAOuvrir la liste des identifiants des opportunités à ouvrir
     * @param utilisateurConnecte infos sur l'utilisateur connecté à l'application.
     * @param deskBar deskBar
     * @param aideService service d'aide.
     */
    public PersonnePhysiqueOpportunitePresenter(HandlerManager eventBus, OpportuniteServiceGwtAsync opportuniteRpcService,
        DimensionServiceGwtAsync dimensionServiceRpcService, PersonneServiceGwtAsync personneRpcService,
        PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService, PersonnePhysiqueOpportunitesView viewOpportunite, Long idPersonne,
        ConstantesModel constantes, List<Long> listeIdsOpportunitesAOuvrir, DimensionRessourceModel utilisateurConnecte, DeskBar deskBar,
        AideServiceGwtAsync aideService) {
        super(eventBus);
        this.opportuniteRpcService = opportuniteRpcService;
        this.dimensionRpcService = dimensionServiceRpcService;
        this.view = viewOpportunite;
        this.idPersonne = idPersonne;
        this.constantesModel = constantes;
        this.listeIdsOpportunitesAOuvrir = listeIdsOpportunitesAOuvrir;
        this.personneRpcService = personneRpcService;
        this.personnePhysiqueRpcService = personnePhysiqueRpcService;
        this.constantesOpportunites = GWT.create(OpportuniteConstants.class);
        this.utilisateurConnecte = utilisateurConnecte;
        this.deskBar = deskBar;
        this.aideService = aideService;
        composantsTarificateurs = new ArrayList<ComposantTarificateur>();
        blocsOpportunites = new HashMap<Long, OpportuniteBlocView>();
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        initListeOpportunites(listeIdsOpportunitesAOuvrir);
    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
    }

    /**
     * Rafraichit une liste d'opportunités.
     * @param listeIdsOpportunites la liste des identifiants de opportunités à rafraichir.
     */
    public void rafraichir(List<Long> listeIdsOpportunites) {
        this.listeIdsOpportunitesAOuvrir = listeIdsOpportunites;
        initListeOpportunites(listeIdsOpportunitesAOuvrir);
    }

    private void refreshEnteteOpportunite(Long idOpportunite) {
        final OpportuniteCriteresRechercheModel criteres = new OpportuniteCriteresRechercheModel();
        criteres.setListeIds(Arrays.asList(new Long[] {idOpportunite}));
        opportuniteRpcService.rechercherOpportuniteParCriteres(criteres, new AsyncCallback<List<OpportuniteSimpleModel>>() {
            @Override
            public void onSuccess(List<OpportuniteSimpleModel> result) {
                if (result != null && !result.isEmpty()) {
                    if (blocsOpportunites.containsKey(result.get(0).getIdOpportunite())) {
                        remplirDonneesBloc(blocsOpportunites.get(result.get(0).getIdOpportunite()), result.get(0));
                    }
                }
                view.onRpcServiceSucess();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    private void initListeOpportunites(List<Long> listeIdsOpportunites) {
        listeIdsOpportunitesAOuvrir = listeIdsOpportunites;
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().textChargement()));
        view.clear();
        blocsOpportunites.clear();
        nettoyerTarificateurs();
        final OpportuniteCriteresRechercheModel criteres = new OpportuniteCriteresRechercheModel();
        criteres.setIdPersonnePhysique(idPersonne);
        opportuniteRpcService.rechercherOpportuniteParCriteres(criteres, new AsyncCallback<List<OpportuniteSimpleModel>>() {
            @Override
            public void onSuccess(List<OpportuniteSimpleModel> result) {
                fireEventLocalBus(new UpdateTabNameEvent(idPersonne, result.size(), constantesOpportunites.opportunite()));
                for (OpportuniteSimpleModel opportuniteSimpleModel : result) {
                    formerBloc(opportuniteSimpleModel);
                }
                view.onRpcServiceSucess();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));

            }
        });
    }

    private OpportuniteBlocView formerBloc(final OpportuniteSimpleModel opportuniteSimpleModel) {
        final OpportuniteBlocView bloc = view.creerBloc();
        blocsOpportunites.put(opportuniteSimpleModel.getIdOpportunite(), bloc);

        // Construction de la liste des identifiants des composants d'aide de la vue associée à ce presenter
        final List<Long> listeIdsComposantsAides = new ArrayList<Long>();
        for (final AideComposant composant : bloc.getListAideCompsant()) {
            listeIdsComposantsAides.add(composant.getId());
        }

        // Recherche des composants d'aide existant pour les rendre visible
        aideService.rechercherIdsComposantsAides(listeIdsComposantsAides, new AsyncCallback<List<Long>>() {
            @Override
            public void onSuccess(List<Long> result) {
                if (result != null) {
                    for (final AideComposant composant : bloc.getListAideCompsant()) {
                        composant.setVisible(result.contains(composant.getId()));
                    }
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });

        for (HasUpdateAideEventHandler handler : bloc.getListeUpdateEventHandler()) {
            handler.addUpdateAideEventHandler(new UpdateAideEventHandler() {
                @Override
                public void onUpdateAide(UpdateAideEvent event) {
                    aideService.creerOuModifierAide(event.getAide(), event.getCallBack());
                }
            });
        }
        for (HasDemandeAideEventHandler handler : bloc.getListeDemandeEventHandler()) {
            handler.addDemandeAideEventHandler(new DemandeAideEventHandler() {
                @Override
                public void onDemandeAide(DemandeAideEvent event) {
                    aideService.rechercherAide(event.getIdComposant(), event.getCallBack());
                }
            });
        }

        // on integre le composant tarificateur a l'ouverture de l'opp
        bloc.getBlocSyntheseOpportunite().addOpenEventHandler(new OpenBlocSyntheseEventHandler() {
            @Override
            public void onOpen(OpenBlocSyntheseEvent event) {
                // on integre le composant qu'une seule fois
                if (!bloc.getConteneurComposantTarificateur().iterator().hasNext()) {
                    construirePersonneTarificateur(opportuniteSimpleModel, bloc);
                }
            }
        });

        // Alimentation de la liste des ressources
        bloc.getSlbsRessource().addSuggestHandler(new SuggestListBoxSuggestEventHandler<DimensionRessourceModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<DimensionRessourceModel> event) {
                final DimensionCriteresRechercheRessourceModel criteres = new DimensionCriteresRechercheRessourceModel();
                final String libelleRessourceRecherchee = event.getSuggest();
                criteres.setNom(libelleRessourceRecherchee);
                criteres.setPrenom(libelleRessourceRecherchee);
                if (bloc.getSlbsAgenceValue().getValue() != null) {
                    criteres.setIdAgence(bloc.getSlbsAgenceValue().getValue().getIdentifiant());
                }
                final List<Long> listeIdsEtats = new ArrayList<Long>();
                listeIdsEtats.add(constantesModel.getIdEtatActifRessource());
                criteres.setIdEtats(listeIdsEtats);
                dimensionRpcService.rechercherRessourceParCriteres(criteres, event.getCallBack());
            }
        });
        // Alimentation de la liste des agences
        bloc.getSlbsAgence().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                criteres.setVisible(true);
                dimensionRpcService.rechercherAgenceParCriteres(criteres, event.getCallBack());
            }
        });
        bloc.getSlbsAgenceValue().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                final DimensionRessourceModel ressource = new DimensionRessourceModel();
                ressource.setNom("");
                ressource.setPrenom("");
                bloc.getSlbsRessourceValue().setValue(ressource);
            }
        });
        remplirDonneesBloc(bloc, opportuniteSimpleModel);
        if (listeIdsOpportunitesAOuvrir != null && listeIdsOpportunitesAOuvrir.contains(opportuniteSimpleModel.getIdOpportunite())) {
            bloc.ouvrirBloc();
        }
        return bloc;
    }

    private void remplirDonneesBloc(OpportuniteBlocView bloc, OpportuniteSimpleModel opportuniteSimpleModel) {
        String couleurEntete = null;
        if (opportuniteSimpleModel.getStatut().getIdentifiant().equals(constantesModel.getIdStatutOpportuniteNonRenseigne())) {
            couleurEntete = SquareResources.INSTANCE.css().blocSyntheseDepliantOpportuniteNonRenseigne();
        }
        else if (opportuniteSimpleModel.getStatut().getIdentifiant().equals(constantesModel.getIdStatutOpportuniteTransforme())) {
            couleurEntete = SquareResources.INSTANCE.css().blocSyntheseDepliantOpportuniteTransformee();
        }
        else if (opportuniteSimpleModel.getStatut().getIdentifiant().equals(constantesModel.getIdStatutOpportuniteRefuse())) {
            couleurEntete = SquareResources.INSTANCE.css().blocSyntheseDepliantOpportuniteRefusee();
        }
        else if (opportuniteSimpleModel.getStatut().getIdentifiant().equals(constantesModel.getIdStatutOpportuniteAccepte())) {
            couleurEntete = SquareResources.INSTANCE.css().blocSyntheseDepliantOpportuniteAcceptee();
        }
        else if (opportuniteSimpleModel.getStatut().getIdentifiant().equals(constantesModel.getIdStatutOpportuniteEnAttente())) {
            couleurEntete = SquareResources.INSTANCE.css().blocSyntheseDepliantOpportuniteEnAttente();
        }
        else if (opportuniteSimpleModel.getStatut().getIdentifiant().equals(constantesModel.getIdStatutOpportuniteCorbeille())) {
            couleurEntete = SquareResources.INSTANCE.css().blocSyntheseDepliantOpportuniteCorbeille();
        }
        bloc.changerCouleurBloc(couleurEntete);

        // Alimentation des labels
        bloc.getlType().setText(opportuniteSimpleModel.getType());
        bloc.getlObjet().setText(opportuniteSimpleModel.getObjet());
        bloc.getlSousObjet().setText(opportuniteSimpleModel.getSousObjet());
        bloc.getlStatut().setText(opportuniteSimpleModel.getStatut().getLibelle());
        bloc.getSlbsAgenceValue().setValue(opportuniteSimpleModel.getAgence());
        if (opportuniteSimpleModel.getRessource() != null) {
            bloc.getSlbsRessourceValue().setValue(opportuniteSimpleModel.getRessource());
        }
        bloc.getlDateCreation().setText(opportuniteSimpleModel.getDateCreation());
        bloc.getlCreateur().setText(opportuniteSimpleModel.getCreateur().getLibelle());
        bloc.modifierTitreBloc(opportuniteSimpleModel.getEidOpportunite());
    }

    /**
     * Integre le composant tarificateur avec ses evenements.
     */
    private void integrerComposantTarificateur(final OpportuniteSimpleModel opportunite, final OpportuniteBlocView bloc,
        com.square.composant.tarificateur.square.client.model.personne.PersonneModel personne) {
        // on cree le Dto que l'on va transmettre au composant
        final InfosOpportuniteModel infosOpportunite = new InfosOpportuniteModel();
        if (opportunite.getAgence() != null && opportunite.getAgence().getIdentifiantExterieur() != null) {
            infosOpportunite.setEidAgence(Long.valueOf(opportunite.getAgence().getIdentifiantExterieur()));
        }
        if (opportunite.getCreateur() != null && opportunite.getCreateur().getIdentifiantExterieur() != null) {
            infosOpportunite.setEidCreateur(Long.valueOf(opportunite.getCreateur().getIdentifiantExterieur()));
        }
        infosOpportunite.setEidOpportunite(opportunite.getIdOpportunite());
        if (opportunite.getRessource() != null && opportunite.getRessource().getIdentifiantExterieur() != null) {
            infosOpportunite.setEidResponsable(Long.valueOf(opportunite.getRessource().getIdentifiantExterieur()));
        }
        infosOpportunite.setLoginUtilisateurConnecte(utilisateurConnecte.getLogin());
        infosOpportunite.setPersonne(personne);
        // on integre le composant tarificateur
        final ComposantTarificateur composantTarificateur =
            new ComposantTarificateur(eventBus, deskBar, infosOpportunite, constantesModel.isHasRoleAdmin(), constantesModel.isHasRoleAnimateur());
        // Enregistrement dans la liste (pour nettoyage ultérieur)
        composantsTarificateurs.add(composantTarificateur);

        // ON AJOUTE LES HANDLERS SUR LES BOUTONS
        composantTarificateur.getBtnAjouterDevisClickHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ajouterDevis(composantTarificateur, infosOpportunite);
            }
        });
        composantTarificateur.getBtnVoirActionsClickHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                personneRpcService.rechercherPersonneParId(idPersonne, new AsyncCallback<PersonneBaseModel>() {
                    @Override
                    public void onSuccess(PersonneBaseModel result) {
                        if (result instanceof PersonneModel) {
                            final PersonneModel personne = (PersonneModel) result;
                            fireEventLocalBus(new VoirActionLieeOpportuniteEvent(idPersonne,
                            personne.getNom() != null ? personne.getNom() : "",
                            personne.getPrenom() != null ? personne.getPrenom() : "",
                            (personne.getNaturePersonne() != null) ? personne.getNaturePersonne().getIdentifiant() : null,
                            opportunite.getIdOpportunite(), opportunite.getEidOpportunite()));
                        }
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }
                });
            }
        });

        // abonnement sur les evenements de suppression des opportunites.
        composantTarificateur.addEventHandlerToLocalBus(SuppressionOpportuniteEvent.TYPE, new SuppressionOpportuniteEventHandler() {
            @Override
            public void supprimerOpportunite(final SuppressionOpportuniteEvent event) {
                final PopupCallback callback = new PopupCallback() {
                    public void onResult(boolean result) {
                        if (result) {
                            final Long idOpportuniteASupprimer = event.getidOpportuniteASupprimer();
                            opportuniteRpcService.supprimerOpportunite(idOpportuniteASupprimer, new AsyncCallback<Object>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                                }

                                @Override
                                public void onSuccess(Object result) {
                                    initListeOpportunites(null);
                                    final PopupInfoConfiguration config =
                                        new PopupInfoConfiguration(constantesOpportunites.notificationOpportuniteSupprimee(),
                                            AppControllerConstants.NOTIFICATION_TIME_OUT);
                                    new DecoratedInfoPopup(config).show();
                                    fireEventLocalBus(new RefreshActionsEvent());
                                }
                            });
                        }
                    }
                };
                final PopupConfiguration config = new PopupConfiguration(constantesOpportunites.confirmationSuppression());
                config.setTitle(constantesOpportunites.titrePopupConfirmationSuppression());
                config.setCallback(callback);
                ConfirmPopup.afficher(config);
            }
        });

        // ON S'ABONNE AUX EVENEMENTS REMONTES PAR LE COMPOSANT
        composantTarificateur.addEventHandlerToLocalBus(MiseAJourOpportuniteEvent.TYPE, new MiseAJourOpportuniteEventHandler() {
            @Override
            public void mettreAJour(MiseAJourOpportuniteEvent event) {
                enregistrerModifications(bloc, opportunite.getIdOpportunite());
            }
        });
        composantTarificateur.addEventHandlerToLocalBus(SuccesMajInfosAdhesionEvent.TYPE, new SuccesMajInfosAdhesionEventHandler() {
            @Override
            public void onSuccess(SuccesMajInfosAdhesionEvent event) {
                mettreAJourInfosAdhesion(event.getListePersonnes());
            }
        });
        composantTarificateur.addEventHandlerToLocalBus(SuccesTransfertEvent.TYPE, new SuccesTransfertEventHandler() {
            @Override
            public void onSuccess(SuccesTransfertEvent event) {
                initListeOpportunites(null);
            }
        });

        composantTarificateur.addEventHandlerToLocalBus(SuccesTransformationAiaEvent.TYPE, new SuccesTransformationAiaEventHandler() {
            @Override
            public void onSuccess(SuccesTransformationAiaEvent event) {
                //Rechargement des actions et du composant concerné
                fireEventLocalBus(new RefreshActionsEvent());
                composantTarificateur.chargerOpportunite(true, false);
            }
        });

        // ON AFFICHE LE COMPOSANT
        composantTarificateur.showPresenter(bloc.getConteneurComposantTarificateur());
    }

    /**
     * Met à jour les informations d'adhésion.
     */
    private void mettreAJourInfosAdhesion(final List<InfosPersonneSyncModel> listeInfos) {
        // Appel au service
        personnePhysiqueRpcService.mettreAJourInfosAdhesion(listeInfos, new AsyncCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                // TODO rafraichir la personne
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    /**
     * Enregistre les modifications d'une opportunité.
     * @param bloc les informations
     * @param idOpportunite l'identifiant de l'opportunité
     */
    private void enregistrerModifications(final OpportuniteBlocView bloc, final Long idOpportunite) {
        // Récupération des valeurs
        final OpportuniteModificationModel opportuniteModificationModel = new OpportuniteModificationModel();
        opportuniteModificationModel.setAgence(bloc.getSlbsAgenceValue().getValue());
        opportuniteModificationModel.setIdOpportunite(idOpportunite);
        opportuniteModificationModel.setRessource(bloc.getSlbsRessourceValue().getValue());

        // Appel au service
        opportuniteRpcService.modifierOpportunite(opportuniteModificationModel, new AsyncCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(constantesOpportunites.notificationOpportuniteMaj(), AppControllerConstants.NOTIFICATION_TIME_OUT);
                config.setCallback(new PopupCallback() {

                    @Override
                    public void onResult(boolean result) {
                        refreshEnteteOpportunite(idOpportunite);
                    }
                });
                new DecoratedInfoPopup(config).show();
            }

            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    // Passage de l'exception vers les composants
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    bloc.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }
        });
    }

    /**
     * Recupere la personne pour le tarificateur.
     */
    private void construirePersonneTarificateur(final OpportuniteSimpleModel opportunite, final OpportuniteBlocView bloc) {
        // Appel au service
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().textChargementOpportunite()));
        personnePhysiqueRpcService.construirePersonneTarificateur(idPersonne,
            new AsyncCallback<com.square.composant.tarificateur.square.client.model.personne.PersonneModel>() {
                @Override
                public void onSuccess(com.square.composant.tarificateur.square.client.model.personne.PersonneModel personne) {
                    integrerComposantTarificateur(opportunite, bloc, personne);
                }

                @Override
                public void onFailure(Throwable caught) {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            });
    }

    /** Ajoute un devis. */
    private void ajouterDevis(final ComposantTarificateur composantTarificateur, final InfosOpportuniteModel infosOpportunite) {
        // Test si la famille est éligible pour la création de devis
        opportuniteRpcService.isFamilleEligiblePourOpportunite(idPersonne, new AsyncCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (Boolean.TRUE.equals(result)) {
                    // Récupération de la personne au cas où la personne a été modifiée entre temps (changement de compo familiale)
                    view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().textChargementFamille()));
                    personnePhysiqueRpcService.construirePersonneTarificateur(idPersonne,
                        new AsyncCallback<com.square.composant.tarificateur.square.client.model.personne.PersonneModel>() {
                            @Override
                            public void onSuccess(com.square.composant.tarificateur.square.client.model.personne.PersonneModel personne) {
                                view.onRpcServiceSucess();
                                infosOpportunite.setPersonne(personne);
                                composantTarificateur.creerNouveauDevis(infosOpportunite);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                            }
                        });
                }
                else {
                    final ErrorPopupConfiguration errorPopupConfiguration = new ErrorPopupConfiguration(view.getViewConstants().erreurEligibiliteFamille());
                    view.onRpcServiceFailure(errorPopupConfiguration);
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    /** Interface de la vue. */
    public interface PersonnePhysiqueOpportunitesView extends View {
        /**
         * Popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Creer la vue et l'ajoute à la page.
         * @return la vue crée
         */
        OpportuniteBlocView creerBloc();

        /**
         * Masque les popup de chargement.
         */
        void onRpcServiceSucess();

        /**
         * Nettoie la fenêtre.
         */
        void clear();

        /**
         * Affiche une popup de chargement.
         * @param loadingPopupConfiguration la configuration
         */
        void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration);

        /**
         * Constantes de la vue.
         * @return les constantes de la vue
         */
        PersonnePhysiqueOpportunitesViewImplConstants getViewConstants();
    }

    /** Interface d'un bloc. */
    public interface OpportuniteBlocView extends View {
        /**
         * Renvoi la valeur de lType.
         * @return lType
         */
        HasText getlType();

        /**
         * Ouvre un bloc.
         */
        void ouvrirBloc();

        /**
         * Renvoi la valeur de lObjet.
         * @return lObjet
         */
        HasText getlObjet();

        /**
         * Renvoi la valeur de lSousObjet.
         * @return lSousObjet
         */
        HasText getlSousObjet();

        /**
         * Renvoi la valeur de lStatut.
         * @return lStatut
         */
        HasText getlStatut();

        /**
         * Renvoi la valeur de lDateCreation.
         * @return lDateCreation
         */
        HasText getlDateCreation();

        /**
         * Renvoi la valeur de lCreateur.
         * @return lCreateur
         */
        HasText getlCreateur();

        /**
         * Renvoi la valeur de slbsRessource.
         * @return slbsRessource
         */
        HasSuggestListBoxHandler<DimensionRessourceModel> getSlbsRessource();

        /**
         * Renvoi la valeur de slbsAgence.
         * @return slbsAgence
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsAgence();

        /**
         * Renvoi la valeur de slbsRessource.
         * @return slbsRessource
         */
        HasValue<DimensionRessourceModel> getSlbsRessourceValue();

        /**
         * Renvoi la valeur de slbsAgence.
         * @return slbsAgence
         */
        HasValue<IdentifiantLibelleGwt> getSlbsAgenceValue();

        /**
         * Renvoi la valeur de iconeErreurChampManager.
         * @return iconeErreurChampManager
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Modifie le titre du caption panel.
         * @param eid l'eid de l'opportunite
         */
        void modifierTitreBloc(String eid);

        /**
         * Recupere le conteneur du composant tarificateur.
         * @return le conteneur
         */
        HasWidgets getConteneurComposantTarificateur();

        /**
         * Recupere le bloc synthese.
         * @return le bloc
         */
        HasOpenBlocSyntheseEventHandlers getBlocSyntheseOpportunite();

        /**
         * Recupere le bloc synthese.
         * @return le bloc
         */
        HasCloseBlocSyntheseEventHandlers getCloseBlocSyntheseOpportuniteEventHandler();

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
         * Change la couleur.
         * @param couleurEntete couleur d'entête.
         */
        void changerCouleurBloc(String couleurEntete);

    }

    private void nettoyerTarificateurs() {
        // Nettoyage des composants tarificateurs
        for (ComposantTarificateur tarificateur : composantsTarificateurs) {
            tarificateur.detachPresenter();
        }
        composantsTarificateurs.clear();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
        nettoyerTarificateurs();
    }
}
