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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.evenement.FormulaireChangeHandlerUtil;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasBeforeSelectionHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.controller.AppControllerViewImpl.ContainerTabPanel;
import com.square.client.gwt.client.event.AppellerPersonneEvent;
import com.square.client.gwt.client.event.AppellerPersonneEventHandler;
import com.square.client.gwt.client.event.ConfirmerModificationPersonneEvent;
import com.square.client.gwt.client.event.ConfirmerModificationPersonneEventHandler;
import com.square.client.gwt.client.event.FusionnerPersonnesEvent;
import com.square.client.gwt.client.event.OpenOpportuniteEvent;
import com.square.client.gwt.client.event.OpenOpportuniteEventHandler;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.event.RafraichirPersonneEvent;
import com.square.client.gwt.client.event.RafraichirPersonneEventHandler;
import com.square.client.gwt.client.event.UpdateTabEvent;
import com.square.client.gwt.client.event.UpdateTabNameEvent;
import com.square.client.gwt.client.event.UpdateTabNameEventHandler;
import com.square.client.gwt.client.event.VoirActionLieeOpportuniteEvent;
import com.square.client.gwt.client.event.VoirActionLieeOpportuniteEventHandler;
import com.square.client.gwt.client.event.actions.AjoutActionEvent;
import com.square.client.gwt.client.event.actions.RefreshActionsEvent;
import com.square.client.gwt.client.event.actions.RefreshActionsEventHandler;
import com.square.client.gwt.client.event.opportunites.AjoutOpportuniteEvent;
import com.square.client.gwt.client.event.opportunites.AjoutOpportuniteEventHandler;
import com.square.client.gwt.client.event.opportunites.ForcerCreationOpportuniteEvent;
import com.square.client.gwt.client.event.opportunites.ForcerCreationOpportuniteEventHandler;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.CaisseSimpleModel;
import com.square.client.gwt.client.model.CompteursModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheCaisseModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRegimeModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.IdentifiantLibelleBooleanModel;
import com.square.client.gwt.client.model.InfoSanteModel;
import com.square.client.gwt.client.model.InfosCreationOpportuniteModel;
import com.square.client.gwt.client.model.OngletModel;
import com.square.client.gwt.client.model.PersonneCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.model.PersonnePermissionOngletModel;
import com.square.client.gwt.client.model.PersonneSimpleModel;
import com.square.client.gwt.client.model.CompteursModel.Compteur;
import com.square.client.gwt.client.presenter.personne.PersonneActionsPresenter;
import com.square.client.gwt.client.presenter.personne.PersonneCoordonneesPresenter;
import com.square.client.gwt.client.presenter.personne.PersonneRelationsModePresenter;
import com.square.client.gwt.client.presenter.personne.PersonneCoordonneesPresenter.PersonneCoordonneesView;
import com.square.client.gwt.client.presenter.personne.physique.opportunites.OpportuniteCreationPresenter;
import com.square.client.gwt.client.presenter.personne.physique.opportunites.PersonnePhysiqueOpportunitePresenter;
import com.square.client.gwt.client.presenter.personne.physique.opportunites.PopupCreationForceeOpportunitePresenter;
import com.square.client.gwt.client.service.ActionServiceGwtAsync;
import com.square.client.gwt.client.service.CampagneServiceGwtAsync;
import com.square.client.gwt.client.service.ConstantesServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.OpportuniteServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneMoraleServiceGwtAsync;
import com.square.client.gwt.client.service.PersonnePhysiqueServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.service.UtilServiceGwtAsync;
import com.square.client.gwt.client.view.personne.action.PersonneActionsViewImpl;
import com.square.client.gwt.client.view.personne.coordonnees.PersonneCoordonneesViewImpl;
import com.square.client.gwt.client.view.personne.physique.creation.PopupModificationPersonneDoublonViewImpl;
import com.square.client.gwt.client.view.personne.physique.gestion.GestionPersonnePhysiqueViewImplConstants;
import com.square.client.gwt.client.view.personne.physique.opportunites.creation.OpportuniteCreationViewImpl;
import com.square.client.gwt.client.view.personne.physique.opportunites.gestion.PersonnePhysiqueOpportunitesViewImpl;
import com.square.client.gwt.client.view.personne.physique.opportunites.popupCreationForcee.PopupCreationForceeOpportuniteViewImpl;
import com.square.client.gwt.client.view.personne.relations.PersonneRelationsModeViewImpl;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composant.contrat.square.client.event.ContratsChargesEvent;
import com.square.composant.contrat.square.client.event.ContratsChargesEventHandler;
import com.square.composant.contrat.square.client.presenter.ContratsPresenter;
import com.square.composant.contrat.square.client.view.ContratsViewImpl;
import com.square.composant.cotisations.client.ComposantCotisations;
import com.square.composant.cotisations.client.ComposantCotisationsViewImpl;
import com.square.composant.emails.square.client.ComposantEmailsController;
import com.square.composant.emails.square.client.ComposantEmailsViewImpl;
import com.square.composant.emails.square.client.event.ListeEmailsChargeesEvent;
import com.square.composant.emails.square.client.event.ListeEmailsChargeesEventHandler;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter;
import com.square.composant.espace.client.square.client.view.espace.client.EspaceClientViewImpl;
import com.square.composant.ged.square.client.presenter.ListeDetailleeDocumentsPresenter;
import com.square.composant.ged.square.client.service.DocumentsServiceGwtAsync;
import com.square.composant.ged.square.client.view.listedetaillee.ListeDetailleeDocumentsViewImpl;
import com.square.composant.prestations.square.client.ComposantPrestations;
import com.square.composant.prestations.square.client.ComposantPrestationsViewImpl;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEventHandler;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Presenter sur la gestion des personnes Physique.
 * @author sgoumard - SCUB
 */
public class GestionPersonnePhysiquePresenter extends Presenter {

    /** Service asynchrone. */
    private PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService;

    private DimensionServiceGwtAsync dimensionServiceRpcService;

    private AppControllerConstants appControllerConstants;

    private PersonneServiceGwtAsync personneRpcService;

    private PersonneMoraleServiceGwtAsync personneMoraleRpcService;

    private ActionServiceGwtAsync actionServiceRpc;

    private ConstantesServiceGwtAsync constantesRpcService;

    private OpportuniteServiceGwtAsync opportuniteRpcService;

    private CampagneServiceGwtAsync campagneRpcService;

//    private AiaServiceGwtAsync aiaServiceRpcService;

    private AideServiceGwtAsync aideService;

    private UtilServiceGwtAsync utilServiceGwtAsync;

    private DocumentsServiceGwtAsync documentsService;

    private ConstantesModel constantes;

    /** Identifiant de la personne. */
    private Long idPersonne;

    /** Numéro de la personne. */
    private String numPersonne;

    /** Identifiant de la nature de la personne. */
    private Long idNaturePersonne;

    /** Personne courante. */
    private PersonneModel currentPersonne;

    private boolean initPersonne;

    private Boolean estAdherent;

    /** Demande affichage initiale action. */
    private Long idActionOuvrir;
    private Long idFiltreIdOpportunite;
    private String idFiltreEidOpportunite;

    /** La liste des identifiants des opportunités à ouvrir. */
    private List<Long> listeIdsOpportunitesAOuvrir;

    /** Identifiant d'opportunité pour afficher les actions liées à cette opportunité. */
    private Long idFiltreOpportunite;

    /** Identifiant externe des opportunités. */
    private String eidOpportunite;

    /**
     * Les Vues.
     */
    private GestionPersonnePhysiqueView view;

    private DimensionRessourceModel utilisateurConnecte;

    private boolean isConnecteTelephonie;

    private boolean hasTelephonie;

    private GestionPersonnePhysiquePresenterConstants presenterConstants;

    private String nomOngletSelectionne;

    private DeskBar deskBar;

    //LISTE DES PRESENTER FILS
    private PersonneCoordonneesPresenter personneCoordonneesPresenter;
    private PersonneRelationsModePresenter personneRelationsModePresenterFamiliale;
    private PersonneRelationsModePresenter personneRelationsModePresenter;
    private PersonnePhysiqueOpportunitePresenter personnePhysiqueOpportunitePresenter;
    private ComposantCotisations composantCotisations;
    private EspaceClientPresenter espaceClientPresenter;
    private ContratsPresenter contratsPresenter;
    private ComposantPrestations composantPrestations;
    private ListeDetailleeDocumentsPresenter listeDetailleeDocumentsPresenter;
    private PopupModificationPersonneDoublonPresenter popupModificationPersonneDoublonPresenter;
    private ComposantEmailsController composantEmailsController;
    private PersonneActionsPresenter personneActionsPresenter;
    private OpportuniteCreationPresenter opportuniteCreationPresenter;
    private PopupCreationForceeOpportunitePresenter popupCreationForceeOpportunitePresenter;
//    private OuvrirDansAiaPresenter ouvrirDansAiaPresenter;
    private Map<Compteur, String> mapCompteursOnglets;

    /**
     * Constructeur.
     * @param eventBus le bus d'evenement.
     * @param view la vue associé au presenter.
     * @param dimensionServiceRpcService le service sur les dimensions
     * @param personnePhysiqueRpcService le service sur les personnes physiques
     * @param personneRpcService le service des personnes
     * @param personneMoraleRpcService service autour des personnes morales.
     * @param actionRpcService le services des actions
     * @param opportuniteRpcService le service des opportunités
     * @param idPersonne l'identifiant de la personne.
     * @param appControllerConstants les constantes de l'application
     * @param constantes les constantes.
     * @param constantesRpcService le service de constantes
     * @param numPersonne numéro de la personne.
     * @param idNaturePersonne identifiant de la nature de la personne.
     * @param idAction l'identifiant de l'action
     * @param idOpportunite l'identifiant de l'opportunité à ouvrir
     * @param filtreIdOpportunite identifiant l'opportunité permettant de filtrer la visualisation des actions
     * @param eidOpportunite l'eid de l'opportunité permettant de filtrer la visualisation des actions
     * @param utilisateurConnecte utilisateurConnecte
     * @param hasTelephonie booléen pour savoir si l'utilisateur courant a la téléphonie.
     * @param isConnecteTelephonie booléen pour savoir si l'utilisateur courant est connecté à la téléphonie.
     * @param deskBar deskBar
     * @param aideService service d'aide.
     * @param utilServiceGwtAsync service util.
     * @param documentsService service pour les doucments.
     * @param campagneRpcService service pour les campagnes.
     * @param aiaServiceRpcService service AIA.
     */
    public GestionPersonnePhysiquePresenter(DimensionServiceGwtAsync dimensionServiceRpcService, PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService,
        PersonneServiceGwtAsync personneRpcService, PersonneMoraleServiceGwtAsync personneMoraleRpcService, ActionServiceGwtAsync actionRpcService,
        OpportuniteServiceGwtAsync opportuniteRpcService, ConstantesServiceGwtAsync constantesRpcService, HandlerManager eventBus,
        GestionPersonnePhysiqueView view, final Long idPersonne, String numPersonne, Long idNaturePersonne, AppControllerConstants appControllerConstants,
        ConstantesModel constantes, Long idAction, Long idOpportunite, Long filtreIdOpportunite, String eidOpportunite,
        DimensionRessourceModel utilisateurConnecte, boolean hasTelephonie, boolean isConnecteTelephonie, DeskBar deskBar, AideServiceGwtAsync aideService,
        UtilServiceGwtAsync utilServiceGwtAsync, DocumentsServiceGwtAsync documentsService, CampagneServiceGwtAsync campagneRpcService) {
        super(eventBus);
        this.view = view;
        this.personnePhysiqueRpcService = personnePhysiqueRpcService;
        this.campagneRpcService = campagneRpcService;
        this.personneRpcService = personneRpcService;
        this.personneMoraleRpcService = personneMoraleRpcService;
        this.dimensionServiceRpcService = dimensionServiceRpcService;
        this.idPersonne = idPersonne;
        this.numPersonne = numPersonne;
        this.idNaturePersonne = idNaturePersonne;
        this.appControllerConstants = appControllerConstants;
        this.constantes = constantes;
        this.actionServiceRpc = actionRpcService;
        this.opportuniteRpcService = opportuniteRpcService;
        this.constantesRpcService = constantesRpcService;
//        this.aiaServiceRpcService = aiaServiceRpcService;
        this.idActionOuvrir = idAction;
        this.idFiltreOpportunite = filtreIdOpportunite;
        this.eidOpportunite = eidOpportunite;
        this.utilisateurConnecte = utilisateurConnecte;
        this.hasTelephonie = hasTelephonie;
        this.isConnecteTelephonie = isConnecteTelephonie;
        this.deskBar = deskBar;
        this.documentsService = documentsService;
        listeIdsOpportunitesAOuvrir = new ArrayList<Long>();
        this.aideService = aideService;
        if (idOpportunite != null) {
            listeIdsOpportunitesAOuvrir.add(idOpportunite);
        }
        this.presenterConstants = GWT.create(GestionPersonnePhysiquePresenterConstants.class);
        this.utilServiceGwtAsync = utilServiceGwtAsync;
//        ouvrirDansAiaPresenter = addChildPresenter(new OuvrirDansAiaPresenter(eventBus, idPersonne, aiaServiceRpcService));
//        ouvrirDansAiaPresenter.showPresenter(view.getNewContextMenuSlot());

    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind()
    {
        //GESTION DES SOUS ONGLETS
        view.getTabPanelSelectionHandlers().addSelectionHandler(new SelectionHandler<Integer>()
        {
            @Override
            public void onSelection(SelectionEvent<Integer> event)
            {
                nomOngletSelectionne = view.getContainerTabInfos(event.getSelectedItem());
                if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabCoordonnees()) && personneCoordonneesPresenter == null)
                {
                    final PersonneCoordonneesView viewCoordonnees = new PersonneCoordonneesViewImpl(constantes, false, hasTelephonie, isConnecteTelephonie);
                    personneCoordonneesPresenter = addChildPresenter(new PersonneCoordonneesPresenter(eventBus, personneRpcService, dimensionServiceRpcService,
                        viewCoordonnees, appControllerConstants, constantes, idPersonne, deskBar, aideService));
                    personneCoordonneesPresenter.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabCoordonnees()));
                    personneCoordonneesPresenter.addEventHandlerToLocalBus(AppellerPersonneEvent.TYPE, new AppellerPersonneEventHandler() {
                        @Override
                        public void appellerPersonne(AppellerPersonneEvent event) {
                            // Propagation
                            fireEventLocalBus(event);
                        }
                    });
                    personneCoordonneesPresenter.addEventHandlerToLocalBus(RafraichirPersonneEvent.TYPE, new RafraichirPersonneEventHandler() {
                        @Override
                        public void rafraichirPersonne(RafraichirPersonneEvent event) {
                            initPersonne();
                        }
                    });
                }
                else if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabRelationFamiliales()) && personneRelationsModePresenterFamiliale == null)
                {
                    final List<Long> filtreGroupements = new ArrayList<Long>();
                    filtreGroupements.add(constantes.getIdGroupementFamiliale());
                    personneRelationsModePresenterFamiliale = addChildPresenter(new PersonneRelationsModePresenter(eventBus,
                        new PersonneRelationsModeViewImpl(), personneRpcService, personnePhysiqueRpcService, personneMoraleRpcService,
                        dimensionServiceRpcService, constantes, filtreGroupements, idPersonne, view.getTbGnom().getValue(), idNaturePersonne,
                        null, 0L, AppControllerConstants.MODE_RELATION_EDITION, aideService, deskBar));
                    personneRelationsModePresenterFamiliale.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabRelationFamiliales()));
                    personneRelationsModePresenterFamiliale.addEventHandlerToLocalBus(UpdateTabNameEvent.TYPE, new UpdateTabNameEventHandler() {
                        @Override
                        public void updateTabName(UpdateTabNameEvent event) {
                            view.updateTabName(event.getNbItems(), event.getTabName());
                        }
                    });
                } else if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabRelations()) && personneRelationsModePresenter == null) {
                    final List<Long> filtrePasDansGroupements = new ArrayList<Long>();
                    filtrePasDansGroupements.add(constantes.getIdGroupementFamiliale());
                    personneRelationsModePresenter = addChildPresenter(
                        new PersonneRelationsModePresenter(eventBus, new PersonneRelationsModeViewImpl(), personneRpcService,
                        personnePhysiqueRpcService, personneMoraleRpcService, dimensionServiceRpcService, constantes, null,
                        idPersonne, view.getTbGnom().getValue().trim(), idNaturePersonne, filtrePasDansGroupements, 0L,
                        AppControllerConstants.MODE_RELATION_EDITION, aideService, deskBar));
                    personneRelationsModePresenter.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabRelations()));
                    personneRelationsModePresenter.addEventHandlerToLocalBus(UpdateTabNameEvent.TYPE, new UpdateTabNameEventHandler() {
                        @Override
                        public void updateTabName(UpdateTabNameEvent event) {
                            view.updateTabName(event.getNbItems(), event.getTabName());
                        }
                    });
                } else if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabOpportunite()) && personnePhysiqueOpportunitePresenter == null) {
                    personnePhysiqueOpportunitePresenter =
                        addChildPresenter(new PersonnePhysiqueOpportunitePresenter(eventBus, opportuniteRpcService, dimensionServiceRpcService,
                            personneRpcService, personnePhysiqueRpcService, new PersonnePhysiqueOpportunitesViewImpl(
                                constantes.isHasRoleAdmin()), idPersonne, constantes,
                            listeIdsOpportunitesAOuvrir, utilisateurConnecte, deskBar, aideService));

                    personnePhysiqueOpportunitePresenter.addEventHandlerToLocalBus(UpdateTabNameEvent.TYPE, new UpdateTabNameEventHandler() {
                        @Override
                        public void updateTabName(UpdateTabNameEvent event) {
                            final Long id = event.getId();
                            if (id != null && id.equals(idPersonne)) {
                                view.updateTabName(event.getNbItems(), event.getTabName());
                            }
                        }
                    });
                    personnePhysiqueOpportunitePresenter.addEventHandlerToLocalBus(VoirActionLieeOpportuniteEvent.TYPE,
                        new VoirActionLieeOpportuniteEventHandler() {
                        @Override
                        public void visualiserActions(final VoirActionLieeOpportuniteEvent event) {
                            idFiltreIdOpportunite = event.getIdOpportunite();
                            idActionOuvrir = null;
                            idFiltreEidOpportunite = event.getEidOpportunite();
                            if (personneActionsPresenter != null) {
                                personneActionsPresenter.initListeActions(idActionOuvrir, idFiltreIdOpportunite, idFiltreEidOpportunite);
                            }
                            view.selectTab(view.getViewConstants().libelleTabActions());
                        }
                    });
                    personnePhysiqueOpportunitePresenter.addEventHandlerToLocalBus(RefreshActionsEvent.TYPE, new RefreshActionsEventHandler() {
                        @Override
                        public void refresh() {
                            rafraichirActions();
                        }
                    });
                    personnePhysiqueOpportunitePresenter.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabOpportunite()));
                    // cotisations uniquement pour segment individuel
                } else if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabCotisations()) && composantCotisations == null) {
                    composantCotisations = addChildPresenter(new ComposantCotisations(eventBus, new ComposantCotisationsViewImpl(), idPersonne));
                    composantCotisations.showPresenter(view.getContainerTab(view.getViewConstants()
                            .libelleTabCotisations()));
                }  else if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabEspaceClient())) {
                    if (espaceClientPresenter == null) {
                        espaceClientPresenter = addChildPresenter(new EspaceClientPresenter(eventBus, new EspaceClientViewImpl(), idPersonne));
                        espaceClientPresenter.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabEspaceClient()));
                    } else {
                        espaceClientPresenter.chargerInfosEspaceAdherent();
                    }
                } else if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabContrats()) && contratsPresenter == null) {
                    contratsPresenter = addChildPresenter(new ContratsPresenter(eventBus, new ContratsViewImpl(), idPersonne));
                    contratsPresenter.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabContrats()));
                    contratsPresenter.addEventHandlerToLocalBus(ContratsChargesEvent.TYPE, new ContratsChargesEventHandler() {
                        @Override
                        public void updateContratsCharges(ContratsChargesEvent event) {
                            if (event.getNbContratsCharges() > 0) {
                                view.updateTabName(event.getNbContratsCharges(), view.getViewConstants().libelleTabContrats());
                            }
                        }
                    });
                } else if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabPrestation()) && composantPrestations == null)
                {
                    composantPrestations = addChildPresenter(new ComposantPrestations(eventBus, new ComposantPrestationsViewImpl(), idPersonne));
                    composantPrestations.showPresenter(view.getContainerTab(view.getViewConstants()
                            .libelleTabPrestation()));
                } else if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabEmails()) && composantEmailsController == null) {
                    composantEmailsController = addChildPresenter(new ComposantEmailsController(eventBus, new ComposantEmailsViewImpl(), numPersonne));
                    composantEmailsController.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabEmails()));
                    composantEmailsController.addEventHandlerToLocalBus(ListeEmailsChargeesEvent.TYPE, new ListeEmailsChargeesEventHandler() {
                        @Override
                        public void updateInfosEmails(ListeEmailsChargeesEvent event) {
                            if (numPersonne.equals(event.getNumeroPersonne()) && event.getNbEmails() > 0) {
                                view.updateTabName(event.getNbEmails(), view.getViewConstants().libelleTabEmails());
                            }
                        }
                    });
                } else if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabDocuments()))
                {
                    if (listeDetailleeDocumentsPresenter == null) {
                        listeDetailleeDocumentsPresenter = addChildPresenter(
                            new ListeDetailleeDocumentsPresenter(eventBus, numPersonne, new ListeDetailleeDocumentsViewImpl(), documentsService));
                        listeDetailleeDocumentsPresenter.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabDocuments()));
                    } else {
                        listeDetailleeDocumentsPresenter.rafraichir();
                    }
                }
                else if (nomOngletSelectionne.equals(view.getViewConstants().libelleTabActions()))
                {
                    if (personneActionsPresenter == null)
                    {
                        personneActionsPresenter = addChildPresenter(new PersonneActionsPresenter(eventBus, actionServiceRpc, dimensionServiceRpcService,
                            constantesRpcService, personneRpcService,
                            new PersonneActionsViewImpl(constantes), idPersonne, constantes, idActionOuvrir, idFiltreIdOpportunite, eidOpportunite, numPersonne,
                            utilisateurConnecte, aideService));
                        personneActionsPresenter.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabActions()));
                        personneActionsPresenter.addEventHandlerToLocalBus(UpdateTabNameEvent.TYPE, new UpdateTabNameEventHandler() {
                            @Override
                            public void updateTabName(UpdateTabNameEvent event) {
                                    view.updateTabName(event.getNbItems(), event.getTabName());
                            }
                        });
                        // Gestion des opportunités
                        personneActionsPresenter.addEventHandlerToLocalBus(AjoutOpportuniteEvent.TYPE, new AjoutOpportuniteEventHandler() {
                            @Override
                            public void ajouterOpportunite(final AjoutOpportuniteEvent event) {
                                creerOpportunite(false);
                            }
                        });
                        personneActionsPresenter.addEventHandlerToLocalBus(OpenOpportuniteEvent.TYPE, new OpenOpportuniteEventHandler() {
                            @Override
                            public void open(OpenOpportuniteEvent openOpportuniteEvent) {
                                listeIdsOpportunitesAOuvrir  = Arrays.asList(new Long[]{openOpportuniteEvent.getId()});
                                if (openOpportuniteEvent.isRafraichir()) {
                                    if (personnePhysiqueOpportunitePresenter != null) {
                                        personnePhysiqueOpportunitePresenter.rafraichir(listeIdsOpportunitesAOuvrir);
                                    }
                                    view.selectTab(view.getViewConstants().libelleTabOpportunite());
                                }
                            }
                        });
                    }
                       // On ne rafraichit pas pour le moment
//                    else
//                    {
//                        personneActionsPresenter.initListeActions(idActionOuvrir, idFiltreIdOpportunite, idFiltreEidOpportunite);
//                    }
                }
            }
        });


        view.getBtnOpportuniteContextAddOpportunite().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                creerOpportunite(false);
            }
        });
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
        view.getBtGenregistrer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (isDatesValides()) {
                    if (isRechercheDoublonPossible()) {
                        rechercherDoublonsPersonne();
                    }
                    else {
                        // On essaye d'enregistrer la personne pour lever les exceptions d'intégrité
                        enregistrerPersonne(false);
                    }
                }
            }
        });
        view.getLbGannuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                initPersonne();
            }
        });
        view.getLibGcivilite().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherCiviliteParCriteres(criteres, event.getCallBack());
            }
        });
        view.getLibGnature().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherNaturePersonnePhysiqueParCriteres(criteres, event.getCallBack());
            }
        });
        view.getLibGregime().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheRegimeModel criteres = new DimensionCriteresRechercheRegimeModel();
                final DimensionCriteresRechercheModel dimensionCriteres = new DimensionCriteresRechercheModel();
                dimensionCriteres.setLibelle(event.getSuggest());
                dimensionCriteres.setVisible(true);
                if (!"".equals(event.getSuggest())) {
                    dimensionCriteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                criteres.setDimensionCriteres(dimensionCriteres);
                dimensionServiceRpcService.rechercherRegimeParCriteres(criteres, event.getCallBack());
            }
        });
        // SI LA VALEUR DU REGIME CHANGE ON VIDE LES CAISSES
        view.getLibGregime().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                view.getLibGcaisse().clean();

            }
        });
        view.getLibGcaisse().addSuggestHandler(new SuggestListBoxSuggestEventHandler<CaisseSimpleModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<CaisseSimpleModel> event) {
                final DimensionCriteresRechercheCaisseModel criteres = new DimensionCriteresRechercheCaisseModel();
                // ON FILTRE SUR LE REGIME SI IL EST MENTIONNE
                criteres.setIdRegime(view.getLibGregime().getValue() != null ? view.getLibGregime().getValue().getIdentifiant() : null);
                final DimensionCriteresRechercheModel critereDim = new DimensionCriteresRechercheModel();
                critereDim.setLibelle(event.getSuggest());
                critereDim.setVisible(true);
                critereDim.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                criteres.setDimensionCriteres(critereDim);
                dimensionServiceRpcService.rechercherCaisseParCriteres(criteres, event.getCallBack());
            }
        });
        view.getLibGprofession().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleBooleanModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleBooleanModel> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherProfessionParCriteres(criteres, event.getCallBack());
            }
        });
//        view.getLibGprofession().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleBooleanModel>() {
//          @Override
//          public void onValueChange(ValueChangeEvent<IdentifiantLibelleBooleanModel> event) {
//              metAJourPanelAutres(view.getLibGprofession().getValue().getBool());
//          }
//      });
        view.getLibGsegment().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherSegmentParCriteres(criteres, event.getCallBack());
            }
        });
        view.getLibGreseauVente().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherReseauVenteParCriteres(criteres, event.getCallBack());
            }
        });
        view.getLibGcivilite().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                view.afficherNomJeuneFille(!view.getLibGcivilite().getValue().getIdentifiant().equals(constantes.getIdCiviliteMonsieur()));
            }
        });
        view.getLibGcsp().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherCSPParCriteres(criteres, event.getCallBack());
            }
        });
        view.getLibGsituationMarie().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherSitFamParCriteres(criteres, event.getCallBack());
            }
        });
        view.getBlRpersonne().addOpenEventHandler(new OpenBlocSyntheseEventHandler() {
            @Override
            public void onOpen(OpenBlocSyntheseEvent event) {
                if (!initPersonne) {
                    initPersonne();
                }
            }
        });

        view.getSuggestAgence().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final String libelleAgenceRecherchee = event.getSuggest();
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(libelleAgenceRecherchee);
                if (libelleAgenceRecherchee == null || "".equals(libelleAgenceRecherchee.trim())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherAgenceParCriteres(criteres, event.getCallBack());
            }
        });
        view.getBtActionsContextAddActions().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEventGlobalBus(new AjoutActionEvent(idPersonne));
            }
        });
        view.getCbGdateNaissance().addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                view.getLbAge().setText(calculerAge(view.getCbGdateNaissance().getValue()) + " " + presenterConstants.libelleAge());
            }
        });

        FormulaireChangeHandlerUtil.registerFormulaireChangeHandler(view.asWidget(), (DecoratedButton) view.getBtGenregistrer());


       /*
        // FIXME JUAN PERFS 150911 - Cet évènement n'est plus levé par la popup, que fait on?
        view.getTabPanelBeforeSelectionHandlers().addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>()
        {
            @Override
            public void onBeforeSelection(BeforeSelectionEvent<Integer> event)
            {
                nomOngletSelectionne = view.getContainerTabInfos(event.getItem());
                eventBus.addHandler(RafraichirRelationEvent.TYPE, new RafraichirRelationEventHandler()
                {
                    @Override
                    public void rafraichirRelation(RafraichirRelationEvent event)
                    {
                        if (event.getFiltreGroupements() != null)
                        {
                            for (Long idGroupement : event.getFiltreGroupements())
                            {
                                if (idGroupement == constantes.getIdGroupementFamiliale())
                                {
                                    viewRelationsFamiliales = new PersonneRelationsModeViewImpl();
                                    final List<Long> filtreGroupements = new ArrayList<Long>();
                                    filtreGroupements.add(constantes.getIdGroupementFamiliale());
                                    personneRelationsModePresenterFamiliale = new PersonneRelationsModePresenter(eventBus,
                                        viewRelationsFamiliales, personneRpcService, personnePhysiqueRpcService, personneMoraleRpcService,
                                        dimensionServiceRpcService, constantes, filtreGroupements, idPersonne, view.getTbGnom().getValue(),
                                        null, 0L, event.getModeEdition(), aideService, deskBar);
                                    personneRelationsModePresenterFamiliale.showPresenter(view.getContainerTab(
                                        view.getViewConstants().libelleTabRelationFamiliales()));
                                }
                            }
                        }
                        if (event.getFiltrePasDansGroupements() != null)
                        {
                            for (Long idGroupement : event.getFiltrePasDansGroupements())
                            {
                                if (idGroupement == constantes.getIdGroupementFamiliale())
                                {
                                    viewRelations = new PersonneRelationsModeViewImpl();
                                    final List<Long> filtrePasDansGroupements = new ArrayList<Long>();
                                    filtrePasDansGroupements.add(constantes.getIdGroupementFamiliale());
                                    personneRelationsModePresenterRelation = new PersonneRelationsModePresenter(eventBus, viewRelations, personneRpcService,
                                        personnePhysiqueRpcService, personneMoraleRpcService, dimensionServiceRpcService, constantes, null,
                                        idPersonne, view.getTbGnom().getValue().trim(), filtrePasDansGroupements, 0L, event.getModeEdition(), aideService,
                                        deskBar);
                                    personneRelationsModePresenterRelation.showPresenter(view.getContainerTab(
                                        view.getViewConstants().libelleTabRelations()));
                                }
                            }
                        }
                    }
                });
            }
        });


       */
    }

    /** Création d'une opportunité. */
    private void creerOpportunite(boolean forcerCreation) {
        if (forcerCreation) {
            if (opportuniteCreationPresenter == null) {
                opportuniteCreationPresenter = addChildPresenter(new OpportuniteCreationPresenter(eventBus,
                    new OpportuniteCreationViewImpl(constantes.isHasRoleAdmin()),
                    idPersonne, utilisateurConnecte, true, opportuniteRpcService, dimensionServiceRpcService,
                campagneRpcService, personneRpcService, constantes, deskBar, aideService));
                opportuniteCreationPresenter.showPresenter(null);
            } else {
                opportuniteCreationPresenter.afficher();
            }
        } else {
            final AsyncCallback<InfosCreationOpportuniteModel> asyncCallback = new AsyncCallback<InfosCreationOpportuniteModel>() {
                @Override
                public void onSuccess(InfosCreationOpportuniteModel result) {
                    if (result.isPersonneDecedee()) {
                        ErrorPopup.afficher(new ErrorPopupConfiguration(appControllerConstants.erreurCreationOpportunitePersonneDecedee()));
                    }
                    else if (result.hasFamilleVivier()) {
                        ErrorPopup.afficher(new ErrorPopupConfiguration(appControllerConstants.erreurCreationOpportuniteFamilleVivier()));
                    }
                    else {
                        if (result.hasOpportunite()) {
                            if (popupCreationForceeOpportunitePresenter == null) {
                                popupCreationForceeOpportunitePresenter = addChildPresenter(new PopupCreationForceeOpportunitePresenter(eventBus,
                                    new PopupCreationForceeOpportuniteViewImpl(), idPersonne));
                                popupCreationForceeOpportunitePresenter.showPresenter(null);
                                popupCreationForceeOpportunitePresenter.addEventHandlerToLocalBus(ForcerCreationOpportuniteEvent.TYPE,
                                    new ForcerCreationOpportuniteEventHandler() {
                                    @Override
                                    public void forcerCreationOpportunite(ForcerCreationOpportuniteEvent event) {
                                        creerOpportunite(true);
                                    }
                                });
                            } else {
                                popupCreationForceeOpportunitePresenter.onShow(null);
                            }
                        } else {
                            creerOpportunite(true);
                        }
                    }
                }

                @Override
                public void onFailure(Throwable caught) {
                    ErrorPopup.afficher(new ErrorPopupConfiguration(appControllerConstants.messageErreurCodeSplitting()));
                }
            };
            opportuniteRpcService.getInfosCreationOpportunite(idPersonne, asyncCallback);
        }
    }

    /**
     * Recherche les doublons de personnes déjà existantes avec le même nom / prénom / date de naissance que la personne qui est modifiée.
     */
    private void rechercherDoublonsPersonne() {
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().rechercheDoublonsEnCours()));
        final RemotePagingCriteriasGwt<PersonneCriteresRechercheModel> criteres = new RemotePagingCriteriasGwt<PersonneCriteresRechercheModel>();
        final PersonneCriteresRechercheModel criterias = new PersonneCriteresRechercheModel();
        criterias.setNom(view.getTbGnom().getValue().trim());
        criterias.setPrenom(view.getTbGprenom().getValue().trim());
        final Date dateDeNaissance = view.getCbGdateNaissance().getValue();
        criterias.setDateNaissance(dateDeNaissance != null ? DateUtil.getString(dateDeNaissance) : null);
        criterias.setRechercheStricte(true);
        final List<Long> idPersonnesAIgnorer = new ArrayList<Long>();
        idPersonnesAIgnorer.add(idPersonne);
        criterias.setIdPersonnesAIgnorer(idPersonnesAIgnorer);
        criteres.setCriterias(criterias);
        criteres.setFirstResult(0);
        criteres.setMaxResult(Integer.MAX_VALUE);
        personnePhysiqueRpcService.rechercherPersonneFullTextParCriteres(criteres, new AsyncCallback<RemotePagingResultsGwt<PersonneSimpleModel>>() {

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(RemotePagingResultsGwt<PersonneSimpleModel> result) {
                view.onRpcServiceSuccess();
                if (result.getListResults().isEmpty()) {
                    // Aucun doublon n'a été trouvé, on enregistre la personne
                    enregistrerPersonne(false);
                }
                else {
                    // On affiche une popup pour notifier qu'en modifiant le nom/prénom ou la date de naissance de la personne
                    // l'utilisateur est sur le point de créer un doublon
                    if (popupModificationPersonneDoublonPresenter == null) {
                        final PopupModificationPersonneDoublonViewImpl popupView = new PopupModificationPersonneDoublonViewImpl();
                        popupModificationPersonneDoublonPresenter = addChildPresenter(
                            new PopupModificationPersonneDoublonPresenter(eventBus, popupView, idPersonne));
                        popupModificationPersonneDoublonPresenter.showPresenter(null);
                        popupModificationPersonneDoublonPresenter.addEventHandlerToLocalBus(ConfirmerModificationPersonneEvent.TYPE, new ConfirmerModificationPersonneEventHandler() {
                            @Override
                            public void modifierPersonne(ConfirmerModificationPersonneEvent event) {
                                final Long idPersonneCiblee = event.getIdPersonne();
                                if (idPersonneCiblee != null && idPersonne.equals(idPersonneCiblee)) {
                                    switch (event.getAction()) {
                                    case ENREGISTRER:
                                        enregistrerPersonne(false);
                                        break;
                                    case ENREGISTRER_PUIS_FUSIONNER:
                                        enregistrerPersonne(true);
                                        break;
                                    case ANNULER:
                                        initPersonne();
                                        break;
                                    default:
                                        break;
                                    }
                                }
                            }
                        });
                    } else {
                            popupModificationPersonneDoublonPresenter.afficher(idPersonne);
                    }
                }
            }
        });
    }

    private void enregistrerPersonne(final boolean afficherPopupFusion) {
        final PersonneModel personne = new PersonneModel();
        personne.setIdentifiant(idPersonne);
        // soit on recupere l'info sante existant pour en modifier les valeurs tout en gardant le referent
        if (currentPersonne.getInfoSante() != null) {
            personne.setInfoSante(currentPersonne.getInfoSante());
            personne.getInfoSante().setNro(currentPersonne.getInfoSante().getNro());
        }
        else {
            personne.setInfoSante(new InfoSanteModel());
            personne.getInfoSante().setIdReferent(idPersonne);
        }
        if (view.getLibGcaisse().getValue() != null
                && !GestionPersonnePhysiqueViewImplConstants.IDENTIFIANT_NC.equals(view.getLibGcaisse().getValue().getId())) {
            personne.getInfoSante().setCaisse(new CaisseSimpleModel(view.getLibGcaisse().getValue().getId()));
        }
        else {
            personne.getInfoSante().setCaisse(null);
        }
        if (view.getLibGregime().getValue() != null
                && !GestionPersonnePhysiqueViewImplConstants.IDENTIFIANT_NC.equals(view.getLibGregime().getValue().getIdentifiant())) {
            personne.getInfoSante().setCaisseRegime(view.getLibGregime().getValue());
        }
        else {
            personne.getInfoSante().setCaisseRegime(null);
        }
        if (view.getLibGcivilite().getValue() != null) {
            personne.setCivilite(view.getLibGcivilite().getValue());
        }
        personne.setNom(view.getTbGnom().getValue().trim().toString());
        personne.setPrenom(view.getTbGprenom().getValue().trim().toString());
        if (view.getCbGdateNaissance().getValue() != null) {
            personne.setDateNaissance(DateUtil.getString(view.getCbGdateNaissance().getValue()));
        }
        personne.setNomJeuneFille(view.getTbGnomJeuneFille().getValue().toString());
        if (view.getLibGsegment().getValue() != null) {
            personne.setSegment(view.getLibGsegment().getValue());
        }
        if (view.getLibGprofession().getValue() != null) {
            personne.setProfession(view.getLibGprofession().getValue());
        }
        if (view.getLibGreseauVente().getValue() != null) {
            personne.setReseauVente(view.getLibGreseauVente().getValue());
        }
        if (view.getLibGcsp() != null) {
            personne.setCsp(view.getLibGcsp().getValue());
        }
        if (view.getLibGsituationMarie() != null) {
            personne.setSitFam(view.getLibGsituationMarie().getValue());
        }
        if (view.getLibGsituationEnfantsValue().getValue() != null && view.getLibGsituationEnfantsValue().getValue().getIdentifiant() != null) {
            personne.setNbEnfants(Integer.valueOf(view.getLibGsituationEnfantsValue().getValue().getIdentifiant().toString()));
        }
        if (view.getLibGnature() != null) {
            personne.setNaturePersonne(view.getLibGnature().getValue());
        }
        personne.setAgence(view.getAgence().getValue());
        personne.setDateCreation(view.getDateCreation().getText());

        final Boolean isVivier =
            currentPersonne != null ? currentPersonne.getNaturePersonne().getIdentifiant().equals(constantes.getIdNaturePersonneVivier()) : null;

        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().modificationPersonne()));
        personnePhysiqueRpcService.modifierPersonnePhysique(personne, new AsyncCallback<PersonneModel>() {
            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(((ControleIntegriteExceptionGwt) caught).getRapport()));
                    view.masquerLoadingPopup();
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }

            @Override
            public void onSuccess(final PersonneModel personne) {
                // si la personne est passé de vivier à prospect, on recharge la personne complete
                if (isVivier != null && isVivier && personne.getNaturePersonne().getIdentifiant().equals(constantes.getIdNaturePersonneProspect())) {
                    fireEventGlobalBus(new OpenPersonEvent(idPersonne, personne.getNumClient(),
                    personne.getNom() != null ? personne.getNom() : "", personne.getPrenom() != null ? personne.getPrenom() : "",
                    (personne.getNaturePersonne() != null) ? personne.getNaturePersonne().getIdentifiant() : null));
                }

                final IdentifiantLibelleGwt aucun = new IdentifiantLibelleGwt(view.getViewConstants().IDENTIFIANT_NC);

                view.getLbRnumeroClient().setText(personne.getNumClient());
                view.getLibGnature().setValue(personne.getNaturePersonne() != null ? personne.getNaturePersonne() : aucun);
                view.getLibGcivilite().setValue(personne.getCivilite() != null ? personne.getCivilite() : aucun);
                view.getTbGnom().setValue(personne.getNom(), true);
                view.getTbGprenom().setValue(personne.getPrenom(), true);
                view.getLbAge().setText("(" + personne.getAge() + presenterConstants.ans() + ")");
                Date date = null;
                if (personne.getDateNaissance() != null && !"".equals(personne.getDateNaissance())) {
                    date = DateUtil.getDate(personne.getDateNaissance());
                }
                view.getCbGdateNaissance().setValue(date);

                // gestion du nombre de caractères du nom prénom
                final String nom = personne.getNom();
                String nomLimite = nom;
                if (nom.length() > appControllerConstants.nbMaxCaracteresNom()) { // limite si superieur a la constante
                    nomLimite = nomLimite.substring(0, appControllerConstants.nbMaxCaracteresNom()) + "...";
                }
                final String prenom = personne.getPrenom();
                String prenomLimite = prenom;
                if (prenom.length() > appControllerConstants.nbMaxCaracteresPrenom()) { // limite si superieur a la constante
                    prenomLimite = prenomLimite.substring(0, appControllerConstants.nbMaxCaracteresPrenom()) + "...";
                }

                final OngletModel onglet = new OngletModel(personne.getIdentifiant().toString(), nomLimite + "<br/>" + prenomLimite);

                fireEventLocalBus(new UpdateTabEvent(onglet));

                // On affiche la liste des opportunités si l'onglet en cours est l'onglet des opportunités
                if (nomOngletSelectionne != null && nomOngletSelectionne.equals(view.getViewConstants().libelleTabOpportunite())) {
                    personnePhysiqueOpportunitePresenter.onShow(view.getContainerTab(view.getViewConstants().libelleTabOpportunite()));
                }
                view.onRpcServiceSuccess();
                String messageNotification = presenterConstants.notificationPersonneMaj();
                if (personne.getHasNaturePersonneChanged() != null && personne.getHasNaturePersonneChanged()) {
                    messageNotification =
                        messageNotification + AppControllerConstants.SAUT_LIGNE + presenterConstants.debutNotificiationNaturePersonneModifiee() + " "
                            + personne.getAncienneNaturePersonne() + " " + presenterConstants.finNotificiationNaturePersonneModifiee() + " "
                            + personne.getNouvelleNaturePersonne();
                }
                final PopupInfoConfiguration popupConfiguration = new PopupInfoConfiguration(messageNotification, AppControllerConstants.NOTIFICATION_TIME_OUT);
                popupConfiguration.setCallback(new PopupCallback() {
                    @Override
                    public void onResult(boolean result) {
                        if (afficherPopupFusion) {
                            fireEventGlobalBus(new FusionnerPersonnesEvent(personne.getNom() != null ? personne.getNom() : "",
                                personne.getPrenom() != null ? personne.getPrenom() : "", personne.getDateNaissance(), personne.getIdentifiant()));
                        }
                    }
                });
                new DecoratedInfoPopup(popupConfiguration).show();
                initPersonne();
            }
        });
        view.disableBoutonEnregistrer();
    }

    /**
     * Permet d'inifialiser la synthese de la personne.
     */
    private void initPersonneSimple() {
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().recuperationPersonne()));

        personnePhysiqueRpcService.rechercherPersonneSimpleParIdentifiant(idPersonne, new AsyncCallback<PersonneSimpleModel>() {
            @Override
            public void onSuccess(PersonneSimpleModel result) {
                final IdentifiantLibelleGwt aucun = new IdentifiantLibelleGwt(GestionPersonnePhysiqueViewImplConstants.IDENTIFIANT_NC);
                if (result.getNature() != null && result.getNature().getIdentifiant() != null) {
                    estAdherent = result.getNature().getIdentifiant().equals(constantes.getIdNaturePersonneAdherent());
                }
                else {
                    estAdherent = false;
                }
                view.getLbRnumeroClient().setText(result.getNumeroClient());
                view.getLibGnature().setValue(result.getNature() != null ? result.getNature() : aucun);
                view.getLibGcivilite().setValue(result.getCivilite() != null ? result.getCivilite() : aucun);
                view.getTbGnom().setValue(result.getNom() != null ? result.getNom() : "", true);
                view.getTbGprenom().setValue(result.getPrenom() != null ? result.getPrenom() : "", true);
                view.getLbAge().setText("(" + result.getAge() + ")");
                if (result.getDateNaissance() != null) {
                    view.getCbGdateNaissance().setValue(DateUtil.getDate(result.getDateNaissance()), true);
                }
                view.afficherChampSyntheseDateNaissance(result.getDateNaissance() != null);
                view.getLbSituationFamiliale().setText(result.getSituationFamiliale());
                view.onRpcServiceSuccess();

                // AFFICHAGE MENU CONTEXTUELLE
                // limite le nombre de caractères
                final String nom = result.getNom() != null ? result.getNom() : "";
                String nomLimite = nom;
                if (nom != null && nom.length() > presenterConstants.maxCaracteresNom()) { // limite si superieur a la constante
                    nomLimite = nomLimite.substring(0, presenterConstants.maxCaracteresNom()) + "...";
                }

                // limite le nombre de caractères
                final String prenom = result.getPrenom() != null ? result.getPrenom() : "";
                String prenomLimite = prenom;
                if (prenom != null && prenom.length() > presenterConstants.maxCaracteresPrenom()) { // limite si superieur a la constante
                    prenomLimite = prenomLimite.substring(0, presenterConstants.maxCaracteresPrenom()) + "...";
                }
                view.setLbContextMenuTitle(nom + " " + prenom);

                view.getLbContextMenu().setText(view.getViewConstants().actionsSur() + " " + nomLimite + " " + prenomLimite);
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    /**
     * Permet d'afficher le bouton supprimer si l'utilisateur a le role admin et que la personne n'a pas de contrats.
     */

    /**
     * Permet d'initialiser la fiche personne à partir du click sur l'evenement.
     * @param personne
     */
    private void initPersonne() {
        final Boolean isVivier =
            currentPersonne != null ? currentPersonne.getNaturePersonne().getIdentifiant().equals(constantes.getIdNaturePersonneVivier()) : null;

        initListesEnfants();
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().recuperationPersonne()));
        personnePhysiqueRpcService.rechercherPersonneParIdentifiant(idPersonne, new AsyncCallback<PersonneModel>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(final PersonneModel personne) {
                // si la personne est passé de vivier à prospect, on recharge la personne complete
                if (isVivier != null && isVivier && personne.getNaturePersonne() != null && personne.getNaturePersonne().getIdentifiant() != null
                        && personne.getNaturePersonne().getIdentifiant().equals(constantes.getIdNaturePersonneProspect())) {
                    fireEventGlobalBus(new OpenPersonEvent(idPersonne, personne.getNumClient(),
                        personne.getNom() != null ? personne.getNom() : "", personne.getPrenom() != null ? personne.getPrenom() : "",
                        (personne.getNaturePersonne() != null) ? personne.getNaturePersonne().getIdentifiant() : null));
                }

                currentPersonne = personne;
                if (personne.getNaturePersonne() != null && personne.getNaturePersonne().getIdentifiant() != null) {
                    estAdherent = personne.getNaturePersonne().getIdentifiant().equals(constantes.getIdNaturePersonneAdherent());
                }
                else {
                    estAdherent = false;
                }
                // MAPPING PERSONNE
                final String emptyString = "";
                final String nonCommunique = "NC";
                view.getTbGnom().setValue(personne.getNom() == null ? emptyString : personne.getNom(), true);
                view.getTbGprenom().setValue(personne.getPrenom() == null ? emptyString : personne.getPrenom());
                if (personne.getDateNaissance() != null) {
                    view.getCbGdateNaissance().setValue(DateUtil.getDate(personne.getDateNaissance()));
                }
                view.getTbGnomJeuneFille().setValue(personne.getNomJeuneFille() == null ? emptyString : personne.getNomJeuneFille());
                view.afficherNomJeuneFille(personne.getCivilite() != null
                    && !personne.getCivilite().getIdentifiant().equals(constantes.getIdCiviliteMonsieur()));
                // Si la personne est décédée
                view.getLbGDecede().setText(personne.isDecede() ? view.getViewConstants().lbGdecede() + " " + personne.getDateDeces() : "");

                view.getDateCreation().setText(personne.getDateCreation());

                final IdentifiantLibelleGwt aucun = new IdentifiantLibelleGwt(GestionPersonnePhysiqueViewImplConstants.IDENTIFIANT_NC);
                final IdentifiantLibelleBooleanModel aucunBoolean = new IdentifiantLibelleBooleanModel(GestionPersonnePhysiqueViewImplConstants.IDENTIFIANT_NC,
                    null, Boolean.FALSE);
                final CaisseSimpleModel aucuneCaisse = new CaisseSimpleModel(GestionPersonnePhysiqueViewImplConstants.IDENTIFIANT_NC);
                view.getLibGcivilite().setValue(personne.getCivilite() != null ? personne.getCivilite() : aucun);
                view.getLibGsegment().setValue(personne.getSegment() != null ? personne.getSegment() : aucun);
                view.getLibGregime().setValue(
                    personne.getInfoSante() != null && personne.getInfoSante().getCaisseRegime() != null ? personne.getInfoSante().getCaisseRegime() : aucun);
                view.getLibGcaisse().setValue(
                    personne.getInfoSante() != null && personne.getInfoSante().getCaisse() != null ? personne.getInfoSante().getCaisse() : aucuneCaisse);
                // si la personne a un referent pour les infos de santé, on grise le regime et caisse
                if (personne.getInfoSante() != null && personne.getInfoSante().getIdReferent() != null
                    && !personne.getInfoSante().getIdReferent().equals(personne.getIdentifiant())) {
                    view.activerInfoSante(false);
                }
                else {
                    view.activerInfoSante(true);
                }

                view.getLibGprofession().setValue(personne.getProfession() != null ? personne.getProfession() : aucunBoolean);
                view.getLibGreseauVente().setValue(personne.getReseauVente() != null ? personne.getReseauVente() : aucun);
                view.getLibGcsp().setValue(personne.getCsp() != null ? personne.getCsp() : aucun);
                view.getLibGsituationMarie().setValue(personne.getSitFam() != null ? personne.getSitFam() : aucun);
                view.getLibGnature().setValue(personne.getNaturePersonne() != null ? personne.getNaturePersonne() : aucun);
                dimensionServiceRpcService.rechercherListeNombreEnfants(AppControllerConstants.NB_ENFANTS_MAX, appControllerConstants.libNoEnfants(),
                    appControllerConstants.libEnfant(), appControllerConstants.libEnfants(), null, new AsyncCallback<List<IdentifiantLibelleGwt>>() {
                        @Override
                        public void onSuccess(List<IdentifiantLibelleGwt> resultNombreEnfant) {
                            for (IdentifiantLibelleGwt nombre : resultNombreEnfant) {
                                if (nombre.getIdentifiant().equals(Long.valueOf(Integer.valueOf(personne.getNbEnfants()).toString()))) {
                                    view.getLibGsituationEnfantsValue().setValue(nombre);
                                    view.disableBoutonEnregistrer();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                        }
                    });
                view.getLbGstatut().setText(
                    personne.getStatut() != null && personne.getStatut().getLibelle() != null ? personne.getStatut().getLibelle() : nonCommunique);
                view.getLbGnumeroRo().setText(
                    personne.getInfoSante() != null && personne.getInfoSante().getNro() != null ? personne.getInfoSante().getNro() : nonCommunique);
                view.getCreateur().setText(personne.getCreateur().getNom() + " " + personne.getCreateur().getPrenom());
                view.getDateCreation().setText(personne.getDateCreation());
                final IdentifiantLibelleGwt agence = personne.getAgence();
                if (agence != null) {
                    view.getAgence().setValue(agence);
                }
                initPersonne = true;

                view.onRpcServiceSuccess();
                view.disableBoutonEnregistrer();
            }
        });
    }

    /**
     * Permet d'intialiser les listes du moteur de recherche.
     */
    private void initListesEnfants() {
        view.getLibGsituationEnfants().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                dimensionServiceRpcService.rechercherListeNombreEnfants(AppControllerConstants.NB_ENFANTS_MAX, appControllerConstants.libNoEnfants(),
                    appControllerConstants.libEnfant(), appControllerConstants.libEnfants(), event.getSuggest(), event.getCallBack());
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        initPersonneSimple();
        rafraichirCompteurs(new CompteursModel(Compteur.values()));

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

        if (idActionOuvrir == null && (listeIdsOpportunitesAOuvrir == null || listeIdsOpportunitesAOuvrir.isEmpty()) && idFiltreOpportunite == null)
        {
            if (estAdherent == null) {
                personnePhysiqueRpcService.rechercherPersonneParIdentifiant(idPersonne, new AsyncCallback<PersonneModel>() {
                    @Override
                    public void onSuccess(PersonneModel result) {
                        currentPersonne = result;
                        if (currentPersonne.getNaturePersonne() != null && currentPersonne.getNaturePersonne().getIdentifiant() != null) {
                            estAdherent = currentPersonne.getNaturePersonne().getIdentifiant().equals(constantes.getIdNaturePersonneAdherent());
                        }
                        else {
                            estAdherent = false;
                        }
                        view.selectTab(view.getViewConstants().libelleTabCoordonnees());
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }
                });
            }
            else
            {
                view.selectTab(view.getViewConstants().libelleTabCoordonnees());
            }
        }
        else if (idActionOuvrir != null || idFiltreOpportunite != null)
        {
            view.selectTab(view.getViewConstants().libelleTabActions());
        }
        else if (listeIdsOpportunitesAOuvrir != null && !listeIdsOpportunitesAOuvrir.isEmpty())
        {
            view.selectTab(view.getViewConstants().libelleTabOpportunite());
        }
        container.add(view.asWidget());
            ((ContainerTabPanel) container).addContextMenu(view.asContextMenuWidget());
    }

    /**
     * Rafraichissement des actions.
     */
    private void rafraichirActions() {
        if (personneActionsPresenter != null) {
            personneActionsPresenter.initListeActions(null, null, null);
        }
    }

    /**
     * Teste la validité des dates.
     * @return true si les dates sont valides, false sinon
     */
    private boolean isDatesValides() {
        boolean datesValides = true;
        final RapportModel rapport = new RapportModel();
        if (!view.getCbGdateNaissance().isDateValide()) {
            rapport.ajoutRapport(presenterConstants.champDateNaissancePersonne(), presenterConstants.erreurDateNaissancePersonneInvalide(), true);
            datesValides = false;
        }
        view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
        if (!datesValides) {
            view.masquerLoadingPopup();
        }
        return datesValides;
    }

    /**
     * Détermine si la recherche de doublons est possible par rapport aux informations saisies sur la fiche de la personne.
     * @return true si la recherche est possible, false sinon
     */
    private boolean isRechercheDoublonPossible() {
        final String chaineVide = "";
        final String nom = view.getTbGnom().getValue().trim();
        final String prenom = view.getTbGprenom().getValue().trim();
        final Date dateDeNaissance = view.getCbGdateNaissance().getValue();
        return nom != null && !nom.trim().equals(chaineVide) && prenom != null && !prenom.trim().equals(chaineVide) && dateDeNaissance != null;
    }

    /**
     * Calcule l'âge d'une personne à partir de sa date de naissance.
     * @param dateNaissance la date de naissance de la personne.
     * @return l'age de la personne
     */
    private int calculerAge(Date dateNaissance) {
        final Date dateActuelle = new Date();
        int age = dateActuelle.getYear() - dateNaissance.getYear();

        dateNaissance.setYear(dateNaissance.getYear() + age);
        if (dateActuelle.before(dateNaissance)) {
            age--;
        }
        return age;
    }

    /**
     * Interface de la vue.
     */
    public interface GestionPersonnePhysiqueView extends View {
        /**
         * Getter sur composant.
         * @return le composant.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGnature();

        /**
         * Sélectionne l'onglet ayant le nom passé en paramètre.
         * @param tabName le nom de l'onglet à sélectionner.
         */
        void selectTab(String tabName);

        /**
         * Lodifie le nom de l'onglet.
         * @param nbItems nbItems
         * @param tabName tabName
         */
        void updateTabName(int nbItems, String tabName);

        /**
         * Getter sur composant.
         * @return le composant.
         */
        DecoratedSuggestListBoxSingle<CaisseSimpleModel> getLibGcaisse();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel> getLibGprofession();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGregime();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGsegment();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGreseauVente();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGcsp();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGsituationMarie();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLibGsituationEnfants();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGcivilite();

        /**
         * Gestions du click sur la synthese.
         * @return le composant
         */
        BlocSyntheseDepliant getBlRpersonne();

        /**
         * Numéro Client.
         * @return le numéro
         */
        HasText getLbRnumeroClient();

        /**
         * Nom de la personne.
         * @return le composant
         */
        HasValue<String> getTbGnom();

        /**
         * Prenom de la personne.
         * @return le composant
         */
        HasValue<String> getTbGprenom();

        /**
         * Date de naissance.
         * @return le composant
         */
        DecoratedCalendrierDateBox getCbGdateNaissance();

        /**
         * Nom de jeune fille.
         * @return the tbGnomJeuneFille
         */
        HasValue<String> getTbGnomJeuneFille();

        /**
         * Le statut.
         * @return the lbGstatut
         */
        HasText getLbGstatut();

        /**
         * le nro.
         * @return the lbGnumeroRo
         */
        HasText getLbGnumeroRo();

        /**
         * Decede.
         * @return the lbGDecede
         */
        HasText getLbGDecede();

        /**
         * Button enregistrer.
         * @return le composant
         */
        HasClickHandlers getBtGenregistrer();

        /**
         * Bouton annuler.
         * @return le composant
         */
        HasClickHandlers getLbGannuler();

        /**
         * Recuperer le gestionnaire des icones d'erreurs.
         * @return iconeErreurChampManager
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Affiche un message de chargement.
         * @param config la config
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Retourne les constantes.
         * @return les constantes.
         */
        GestionPersonnePhysiqueViewImplConstants getViewConstants();

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
         * Masque un message de chargement.
         */
        void masquerLoadingPopup();

        /**
         * Recupere le container associé à l'onglet et le remonte selectionne le container.
         * @param nomOnglet le nom de l'onglet
         * @return le container.
         */
        HasWidgets getContainerTab(String nomOnglet);

        /**
         * Recuperer le nom d'un onglet par son index.
         * @param index .
         * @return .
         */
        String getContainerTabInfos(Integer index);

        /**
         * Recuperer le tab panel pour gerer ces evenements.
         * @return la gestion de l'onglet.
         */
        HasBeforeSelectionHandlers<Integer> getTabPanelBeforeSelectionHandlers();

        /**
         * Récupère le handler de selection des onglets de la vue.
         * @return le handler.
         */
        HasSelectionHandlers<Integer> getTabPanelSelectionHandlers();

        /**
         * Renvoi la valeur de lbSituationFamiliale.
         * @return lbSituationFamiliale
         */
        Label getLbSituationFamiliale();

        /**
         * Renvoi la valeur de lbAge.
         * @return lbAge
         */
        Label getLbAge();

        /**
         * Récupère l'agence sélectionnée.
         * @return l'agence selectionnée.
         */
        HasValue<IdentifiantLibelleGwt> getAgence();

        /**
         * Handler de suggestion pour la recherche d'agences.
         * @return le handler.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestAgence();

        /**
         * Handler pour les changements de valeur de l'agence selectionnée.
         * @return le handler.
         */
        HasValueChangeHandlers<IdentifiantLibelleGwt> getChangeAgence();

        /**
         * Spécifie le créateur.
         * @return le créateur.
         */
        HasText getCreateur();

        /**
         * Spécifie la date de création.
         * @return la date de création.
         */
        HasText getDateCreation();

        /**
         * Mettre visible/invisible le champ nom de jeune fille.
         * @param visible visible
         */
        void afficherNomJeuneFille(boolean visible);

        /**
         * Bouton ajouter une action à la personne.
         * @return le gestionnaire on click.
         */
        HasClickHandlers getBtActionsContextAddActions();

        /**
         * Bouton ajouter une opportunité à la personne.
         * @return le gestionnaire on click
         */
        HasClickHandlers getBtnOpportuniteContextAddOpportunite();

        /**
         * Recuperer le menu context.
         * @return .
         */
        Widget asContextMenuWidget();

        /**
         * Handler.
         * @return handler
         */
        HasWidgets getNewContextMenuSlot();

        /**
         * Recuperer la label du menu Context.
         * @return le label.
         */
        HasText getLbContextMenu();

        /**
         * Recuperer l'index de l'onglet selectionné.
         * @return l'index
         */
        int getTabPanelSelectedIndex();

        /**
         * Sélectionne un onglet par son index.
         * @param index index
         */
        void setTabPanelSelectedIndex(int index);

        /**
         * Retourne la valeur du nombre d'enfants.
         * @return LibGsituationEnfants
         */
        HasValue<IdentifiantLibelleGwt> getLibGsituationEnfantsValue();

        /**
         * Affiche le champ de synthese date de naissance.
         * @param visible flag visible
         */
        void afficherChampSyntheseDateNaissance(boolean visible);

        /**
         * Active ou désactive les infos santé.
         * @param actif le flag
         */
        void activerInfoSante(boolean actif);

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
         * Permet de modifier le title pour affiche le nom complet dans le menu contextuel.
         * @param title le title à afficher
         */
        void setLbContextMenuTitle(String title);

        /**
         * Methode permettant de griser le bouton enregistrer apres enregistrement.
         */
        void disableBoutonEnregistrer();

        /**
         * Met à jour le tabPanel.
         * @param permissionsOnglets permissions
         */
        void majTabPanel(PersonnePermissionOngletModel permissionsOnglets);

        /**
         * Méthode permettant de diable/enable le panel Autres.
         * @param bool l'indicateur d'affichage
         */
        void afficherPanelAutres(boolean bool);

    }

    /**
     * Rafraichir un ou plusieurs compteurs.
     * @param params compteur.
     */
    public void rafraichirCompteurs(CompteursModel params) {
        if (mapCompteursOnglets == null) {
            // Remplissage de la map Compteur / onglet :
            mapCompteursOnglets = new HashMap<Compteur, String>();
            mapCompteursOnglets.put(Compteur.ACTIONS, view.getViewConstants().libelleTabActions());
            mapCompteursOnglets.put(Compteur.CONTRATS, view.getViewConstants().libelleTabContrats());
            mapCompteursOnglets.put(Compteur.COORDONNEES, view.getViewConstants().libelleTabCoordonnees());
            mapCompteursOnglets.put(Compteur.COTISATIONS, view.getViewConstants().libelleTabCotisations());
            mapCompteursOnglets.put(Compteur.DOCUMENTS, view.getViewConstants().libelleTabDocuments());
            mapCompteursOnglets.put(Compteur.EMAILS, view.getViewConstants().libelleTabEmails());
            mapCompteursOnglets.put(Compteur.FAMILLE, view.getViewConstants().libelleTabRelationFamiliales());
            mapCompteursOnglets.put(Compteur.OPPORTUNITES, view.getViewConstants().libelleTabOpportunite());
            mapCompteursOnglets.put(Compteur.PRESTATIONS, view.getViewConstants().libelleTabPrestation());
            mapCompteursOnglets.put(Compteur.RELATIONS, view.getViewConstants().libelleTabRelations());
        }

        personnePhysiqueRpcService.getCompteursParId(idPersonne, params, new AsyncCallback<CompteursModel>() {

            @Override
            public void onSuccess(CompteursModel result) {
                for (Entry<Compteur, Integer> entry : result.getCompteursObtenus().entrySet()) {
                    if (mapCompteursOnglets.containsKey(entry.getKey()) && entry.getValue() != null) {
                        view.updateTabName(entry.getValue(), mapCompteursOnglets.get(entry.getKey()));
                    }
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                GWT.log("error : " + caught);
                // rien
            }
        });
    }

    @Override
    public void onDetach() {
        GWT.log("On Detach GestionPersonnePhysique idPersonne =>" + idPersonne);
    }
}
