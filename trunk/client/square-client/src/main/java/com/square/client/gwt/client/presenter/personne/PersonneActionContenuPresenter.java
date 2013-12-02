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
package com.square.client.gwt.client.presenter.personne;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.ListBoxUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.richtextarea.RichTextToolbar;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.model.ActionModel;
import com.square.client.gwt.client.model.ActionNatureResultatCriteresRechercheModel;
import com.square.client.gwt.client.model.ActionNotificationInfosModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheResultatActionModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.EnregistrementAction;
import com.square.client.gwt.client.model.HistoriqueCommentaireModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.service.ActionServiceGwtAsync;
import com.square.client.gwt.client.service.ConstantesServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.personne.action.PersonneActionContenuViewImplConstants;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composant.ged.square.client.presenter.ListeCompacteDocumentsPresenter;
import com.square.composant.ged.square.client.service.DocumentsServiceGwt;
import com.square.composant.ged.square.client.service.DocumentsServiceGwtAsync;
import com.square.composant.ged.square.client.view.listecompacte.ListeCompacteDocumentsViewImpl;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Presenteur pour le contenu des actions.
 * @author cblanchard - SCUB
 */
public class PersonneActionContenuPresenter extends Presenter {

    private static final String ESPACE = " ";

    /** L'identifiant de l'action. */
    private Long idAction;

    /** La vue. */
    private PersonneActionContenuView view;

    /** Le service des actions. */
    private ActionServiceGwtAsync actionServiceRpc;

    /** Le service de constante. */
    private ConstantesServiceGwtAsync constantesServiceRpc;

    /** Constants du presenter */
    private PersonneActionContenuPresenterConstants presenterConstants;

    /** Le service de dimension. */
    private DimensionServiceGwtAsync dimensionServiceRpc;

    /** Les constantes. */
    ConstantesModel constantes;

    /** Etat de l'action. */
    private EnregistrementAction enregistrementAction;

    /** Identifiant de la personne. */
    private Long idPersonne;

    /** Numéro de client. */
    private String numClient;

    /** Le service des personnes. */
    private PersonneServiceGwtAsync personneRpcService;

    /** Service de gestion d'aide . */
    private AideServiceGwtAsync aideService;

    private boolean attributionAgenceReadOnly;

    private boolean attributionRessourceReadOnly;

    private ActionModel actionEnCours;

    /** La ressource connectee. */
    private DimensionRessourceModel ressourceConnectee;

    /** Liste des documents presenter. **/
    private ListeCompacteDocumentsPresenter listeCompacteDocumentsPresenter;

    /**
     * Constructeur.
     * @param eventBus le bus
     * @param vueContenu la vue du contenu
     * @param idAction l'identifiant de l'action
     * @param actionServiceRpc le service des actions
     * @param constantesServiceRpc le service des constantes
     * @param dimensionServiceRpc le service de dimension
     * @param constantes les contantes
     * @param enregistrementAction états de l'action
     * @param idPersonne identifiant de la personne
     * @param numClient numClient de la personne
     * @param personneServiceRPC le service des personne physique
     * @param aideService le service
     * @param ressourceConnectee la ressource connectée
     */
    public PersonneActionContenuPresenter(HandlerManager eventBus, PersonneActionContenuView vueContenu, Long idAction, ActionServiceGwtAsync actionServiceRpc,
        ConstantesServiceGwtAsync constantesServiceRpc, DimensionServiceGwtAsync dimensionServiceRpc, ConstantesModel constantes,
        EnregistrementAction enregistrementAction, Long idPersonne, String numClient, PersonneServiceGwtAsync personneServiceRPC,
        AideServiceGwtAsync aideService, DimensionRessourceModel ressourceConnectee) {
        super(eventBus);
        this.idAction = idAction;
        this.view = vueContenu;
        this.actionServiceRpc = actionServiceRpc;
        this.constantesServiceRpc = constantesServiceRpc;
        this.presenterConstants = GWT.create(PersonneActionContenuPresenterConstants.class);
        this.dimensionServiceRpc = dimensionServiceRpc;
        this.constantes = constantes;
        this.enregistrementAction = enregistrementAction;
        this.idPersonne = idPersonne;
        this.personneRpcService = personneServiceRPC;
        this.numClient = numClient;
        this.aideService = aideService;
        this.ressourceConnectee = ressourceConnectee;
    }

    @Override
    public View asView() {
        return view;
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

        // Alimentation des statuts
        view.getSlbStatut().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setVisible(true);
                dimensionServiceRpc.rechercherStatutActions(criteres, event.getCallBack());
            }
        });
        view.getSlbNatureContact().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setVisible(true);
                dimensionServiceRpc.rechercherNatureActions(criteres, event.getCallBack());
            }
        });
        view.getSlbNatureContactValue().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                // La liste des résultats de la nature n'est affichée que pour la nature "Téléphone sortant" ou "Visite sortante"
                if (event.getValue() != null && constantes.getIdNatureActionTelephoneSortant().equals(event.getValue().getIdentifiant())) {
                    final PersonneActionContenuViewImplConstants constants =
                        (PersonneActionContenuViewImplConstants) GWT.create(PersonneActionContenuViewImplConstants.class);
                    view.getLEtat().setText(constants.libelleEtatAppel());
                    view.afficherListeNaturesResultat(true);
                    view.getSlbNatureResultatValue().setValue(null);
                } else if (event.getValue() != null && constantes.getIdNatureActionVisiteSortante().equals(event.getValue().getIdentifiant())) {
                    final PersonneActionContenuViewImplConstants constants =
                        (PersonneActionContenuViewImplConstants) GWT.create(PersonneActionContenuViewImplConstants.class);
                    view.getLEtat().setText(constants.libelleEtatVisite());
                    view.afficherListeNaturesResultat(true);
                    view.getSlbNatureResultatValue().setValue(null);
                } else {
                    view.afficherListeNaturesResultat(false);
                }
            }
        });
        // Alimentation des résultats de natures
        if (view.getSlbNatureResultat() != null) {
            view.getSlbNatureResultat().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
                @Override
                public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                    final ActionNatureResultatCriteresRechercheModel criteres = new ActionNatureResultatCriteresRechercheModel();
                    criteres.setVisible(true);
                    if (view.getSlbNatureContactValue().getValue() != null) {
                        criteres.setIdActionNature(view.getSlbNatureContactValue().getValue().getIdentifiant());
                    }
                    dimensionServiceRpc.rechercherNatureResultatActions(criteres, event.getCallBack());
                }
            });
        }
        // Alimentation des agences
        view.getSuggestAttributionAgence().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {

            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                final String libelleAgence = event.getSuggest();
                if (libelleAgence != null && !"".equals(libelleAgence.trim())) {
                    criteres.setLibelle(libelleAgence);
                }
                criteres.setVisible(true);
                dimensionServiceRpc.rechercherAgenceParCriteres(criteres, event.getCallBack());
            }
        });
        // Alimentation des résultats
        view.getSlbsResultatAction().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                if (actionEnCours != null) {
                    final DimensionCritereRechercheResultatActionModel criteresResultat = new DimensionCritereRechercheResultatActionModel();
                    final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                    criteres.setVisible(true);
                    if (actionEnCours.getIdOpportunite() != null) {
                        criteresResultat.setRecuperationOpportunite(false);
                    }
                    dimensionServiceRpc.rechercherResultatActions(criteresResultat, event.getCallBack());
                }
            }
        });
        // Evenement sur le clique de l'opportunite
        view.getlOpportuniteHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (actionEnCours != null) {
                    personneRpcService.rechercherPersonneParId(idPersonne, new AsyncCallback<PersonneBaseModel>() {
                        @Override
                        public void onSuccess(PersonneBaseModel resultPersonne) {
                            if (resultPersonne instanceof PersonneModel) {
                                final PersonneModel personne = (PersonneModel) resultPersonne;
                                fireEventGlobalBus(new OpenPersonEvent(idPersonne, personne.getNumClient(), personne.getNom() != null ? personne.getNom() : "",
                                    personne.getPrenom() != null ? personne.getPrenom() : "", personne.getNaturePersonne() != null ? personne
                                            .getNaturePersonne().getIdentifiant() : null, null, actionEnCours.getIdOpportunite()));
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                        }
                    });
                }
            }
        });
        // Alimentation des ressources si la ressource n'est pas renseignée
        view.getSuggestRessourceAgence().addSuggestHandler(new SuggestListBoxSuggestEventHandler<DimensionRessourceModel>() {

            @Override
            public void suggest(SuggestListBoxSuggestEvent<DimensionRessourceModel> event) {
                if (actionEnCours != null) {
                    final DimensionCriteresRechercheRessourceModel criteres = new DimensionCriteresRechercheRessourceModel();
                    final String libelleRessourceRecherchee = event.getSuggest();
                    Long idAgenceAttribuee = null;
                    // Si l'agence à laquelle l'action est attribuée est en lecture seule
                    if (attributionAgenceReadOnly) {
                        // On récupère l'identifiant de l'agence
                        if (actionEnCours.getAgence() != null && actionEnCours.getAgence().getIdentifiant() != null) {
                            idAgenceAttribuee = actionEnCours.getAgence().getIdentifiant();
                        }
                    } else {
                        // Sinon on récupère l'identifiant de l'agence selectionnée
                        if (view.getSlbAttributionAgence() != null && view.getSlbAttributionAgence().getValue() != null) {
                            idAgenceAttribuee = view.getSlbAttributionAgence().getValue().getIdentifiant();
                        }
                    }
                    if (idAgenceAttribuee != null) {
                        criteres.setIdAgence(idAgenceAttribuee);
                    }
                    criteres.setPrenom(libelleRessourceRecherchee);
                    criteres.setNom(libelleRessourceRecherchee);
                    // Recherche des ressources actives
                    final List<Long> listeIdsEtats = new ArrayList<Long>();
                    listeIdsEtats.add(constantes.getIdEtatActifRessource());
                    criteres.setIdEtats(listeIdsEtats);
                    if (libelleRessourceRecherchee == null || "".equals(libelleRessourceRecherchee.trim())) {
                        criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    }
                    dimensionServiceRpc.rechercherRessourceParCriteres(criteres, event.getCallBack());
                }
            }
        });

        // Si l'attribution d'agence n'est pas en lecture seule
        view.getSuggestRessourceChangeHandler().addValueChangeHandler(new ValueChangeHandler<DimensionRessourceModel>() {

            @Override
            public void onValueChange(ValueChangeEvent<DimensionRessourceModel> event) {
                if (!attributionAgenceReadOnly) {
                    // On récupère l'agence de la ressource qui vient d'être selectionnée
                    final IdentifiantLibelleGwt agenceRessource = event.getValue().getAgence();
                    // On sélectionne l'agence dans la liste déroulante des agences
                    view.getSlbAttributionAgence().setValue(agenceRessource);
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

        // On initialise tous les composants du contenu
        initialiserContenu();

        container.add(view.asWidget());
    }

    /** Méthode privée qui alimente les composants. */
    public void initialiserContenu() {
        initListesNotification();

        // On initialise la GED
        if (listeCompacteDocumentsPresenter == null) {
            // FIXME SG Optimisation : on dois revoir l'instanciation du service RPC lors de la refonte prevus.
            final DocumentsServiceGwtAsync documentService = GWT.create(DocumentsServiceGwt.class);
            listeCompacteDocumentsPresenter =
                addChildPresenter(new ListeCompacteDocumentsPresenter(eventBus, idAction, numClient, new ListeCompacteDocumentsViewImpl(), documentService));
            listeCompacteDocumentsPresenter.showPresenter(view.getConteneurGed());
        } else {
            listeCompacteDocumentsPresenter.chargerDocuments();
        }

        // Récupération des informations générales sur l'action
        actionServiceRpc.rechercherActionParIdentifiant(idAction, new AsyncCallback<ActionModel>() {

            @Override
            public void onSuccess(final ActionModel action) {
                actionEnCours = action;
                remplirVue();

                // Récupération de l'historique des commentaires.
                actionServiceRpc.rechercherNotesActions(idAction, new AsyncCallback<List<HistoriqueCommentaireModel>>() {
                    @Override
                    public void onSuccess(List<HistoriqueCommentaireModel> result) {
                        if (result != null) {
                            String text = "";
                            // Comentaires des actions liées
                            for (HistoriqueCommentaireModel commentaire : result) {
                                text += "> ";
                                if (commentaire.getObjet() != null) {
                                    text += commentaire.getObjet();
                                    text += ESPACE;
                                }
                                if (commentaire.getSousObjet() != null) {
                                    text += commentaire.getSousObjet();
                                    text += ESPACE;
                                }
                                if (commentaire.getAttribution() != null) {
                                    text += presenterConstants.par() + " " + commentaire.getAttribution();
                                    text += ESPACE;
                                }
                                if (commentaire.getDateAction() != null) {
                                    text += presenterConstants.le() + " " + commentaire.getDateAction();
                                }
                                if (commentaire.getDescriptif() != null) {
                                    text += " : ";
                                    text += commentaire.getDescriptif();
                                }
                                text += "<br/>";
                            }
                            // Commentaires de l'action courante
                            if (action.getCommentaires() != null && !action.getCommentaires().isEmpty()) {
                                text += "<br/>" + presenterConstants.commentaireAction() + " <br/>";
                                for (HistoriqueCommentaireModel commentaire : action.getCommentaires()) {
                                    if (commentaire.getDescriptif() != null) {
                                        if (commentaire.getRessource() != null) {
                                            text += commentaire.getRessource().getNom() + ESPACE;
                                            text += commentaire.getRessource().getPrenom() + " : ";
                                        }
                                        // else {
                                        // text += "Non attribué : ";
                                        // }
                                        text += commentaire.getDescriptif();
                                        text += "<br/>";
                                    }
                                }
                            }
                            view.getTaHistorique().setHTML(text);
                        }
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }
                });
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    /**
     * Remplissage de la vue avec les données de l'action en cours.
     */
    private void remplirVue() {

        final boolean actionReadOnly = constantes.getIdStatutTerminer().equals(actionEnCours.getStatut().getIdentifiant());

        // Créateur
        if (actionEnCours.getCreateur() != null) {
            view.getlCreateur().setText(actionEnCours.getCreateur().getNom() + " " + actionEnCours.getCreateur().getPrenom());
        }
        // Agence
        if (actionEnCours.getAgence() != null) {
            view.getlAgence().setText(actionEnCours.getAgence().getLibelle());
        }
        // Ressource
        if (actionEnCours.getRessource() != null && actionEnCours.getRessource().getIdentifiant() != null) {
            view.getLRessourceAgence().setText(actionEnCours.getRessource().getNom() + " " + actionEnCours.getRessource().getPrenom());
        }
        // Calcul du flag de lecture seule pour l'attribution de l'action à une agence
        attributionAgenceReadOnly =
            actionReadOnly || (actionEnCours.getAgence() != null && !constantes.getIdAgenceFrance().equals(actionEnCours.getAgence().getIdentifiant()));

        view.afficherListeAgence(!attributionAgenceReadOnly);

        // Initialisation de la valeur
        view.getSlbAttributionAgence().setValue(actionEnCours.getAgence());
        view.getSlbRessourceAgence().setValue(actionEnCours.getRessource());

        // Calcul du flag de lecture seule pour l'attribution de l'action à une ressource
        final boolean attributionRessourceReadOnly =
            actionReadOnly || attributionAgenceReadOnly && actionEnCours.getRessource() != null && actionEnCours.getRessource().getIdentifiant() != null;
        view.afficherListeRessourceAgence(!attributionRessourceReadOnly);

        // Date de création
        if (actionEnCours.getDateCreation() != null) {
            view.getlDateCreation().setText(actionEnCours.getDateCreation());
        }
        // Campagne
        if (actionEnCours.getCampagne() != null) {
            view.getlCampagne().setText(actionEnCours.getCampagne().getLibelle());
        }
        // Rappel
        if (actionEnCours.getRappel() != null && actionEnCours.getIdNotificationList() != null) {
            view.getCbRappel().setValue(actionEnCours.getRappel());
            ListBoxUtil.setItemSelected(view.getLbNotification(), String.valueOf(actionEnCours.getIdNotificationList()));
        }
        // Ajout à l'agenda
        if (actionEnCours.getVisibleAgenda() != null) {
            view.getCbAjoutAgenda().setValue(actionEnCours.getVisibleAgenda());
            view.activerAjoutAgenda(actionEnCours.getDateAction() != null && !"".equals(actionEnCours.getDateAction())
                && actionEnCours.getHeureAction() != null && !"".equals(actionEnCours.getHeureAction()) && actionEnCours.getDuree() != null
                && actionEnCours.getDuree().getIdentifiant() != null);
        }
        // Rappel mail
        if (actionEnCours.getMePrevenirParMail() != null) {
            view.getCbRappelMail().setValue(actionEnCours.getMePrevenirParMail());
        }
        // Opportunité
        if (actionEnCours.getIdOpportunite() != null && actionEnCours.getEidOpportunite() != null) {
            view.getlOpportunite().setText(actionEnCours.getEidOpportunite());
        }
        // Statut
        view.getSlbStatut().setValue(actionEnCours.getStatut());
        // Résultat
        if (actionEnCours.getResultat() != null) {
            view.getSlbsResultatAction().setValue(actionEnCours.getResultat());
            enregistrementAction.setIdResultat(actionEnCours.getResultat().getIdentifiant());
        }
        // Nature
        if (actionEnCours.getNatureAction() != null) {
            view.getSlbNatureContactValue().setValue(actionEnCours.getNatureAction());
        }
        // Résultat de nature
        if (actionEnCours.getNatureResultat() != null) {
            view.getSlbNatureResultatValue().setValue(actionEnCours.getNatureResultat());
        }
        // La liste des résultats de la nature n'est affichée que pour les natures "Téléphone sortant" et "Visite sortante"
        view.afficherListeNaturesResultat(actionEnCours.getNatureAction() != null
            && (constantes.getIdNatureActionTelephoneSortant().equals(actionEnCours.getNatureAction().getIdentifiant()) || constantes
                    .getIdNatureActionVisiteSortante().equals(actionEnCours.getNatureAction().getIdentifiant())));

        if (actionEnCours.getStatut().getIdentifiant().equals(constantes.getIdStatutTerminer())
            || actionEnCours.getStatut().getIdentifiant().equals(constantes.getIdStatutAnnuler())) {
            view.disable();
        }
    }

    /**
     * Permet d'intialiser la liste de notification.
     */
    private void initListesNotification() {
        constantesServiceRpc.getListeActionNotifications(new AsyncCallback<List<ActionNotificationInfosModel>>() {
            @Override
            public void onSuccess(List<ActionNotificationInfosModel> result) {
                final List<IdentifiantLibelleGwt> resultGwt = new ArrayList<IdentifiantLibelleGwt>();
                for (ActionNotificationInfosModel notification : result) {
                    final IdentifiantLibelleGwt notificationGwt = new IdentifiantLibelleGwt();
                    notificationGwt.setIdentifiant(notification.getId());
                    notificationGwt.setLibelle(notification.getLibelle());
                    resultGwt.add(notificationGwt);
                }
                ListBoxUtil.remplirListe(view.getLbNotification(), resultGwt, true);
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    /** Interface de la vue sur le contenu. */
    public interface PersonneActionContenuView extends View {
        /**
         * Renvoi la valeur de constants.
         * @return constants
         */
        PersonneActionContenuViewImplConstants getConstants();

        /** Désactive le contenu. */
        void disable();

        /**
         * Affiche une popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Renvoi la valeur de lCreateur.
         * @return lCreateur
         */
        HasText getlCreateur();

        /**
         * Renvoi la valeur de lAgence.
         * @return lAgence
         */
        HasText getlAgence();

        /**
         * Renvoi la valeur de lDateCreation.
         * @return lDateCreation
         */
        HasText getlDateCreation();

        /**
         * Renvoi la valeur de lCampagne.
         * @return lCampagne
         */
        HasText getlCampagne();

        /**
         * Renvoi la valeur de cbRappel.
         * @return cbRappel
         */
        CheckBox getCbRappel();

        /**
         * Renvoi la valeur de cbRappelMail.
         * @return cbRappelMail
         */
        CheckBox getCbRappelMail();

        /**
         * Renvoi la valeur de lbNotification.
         * @return lbNotification
         */
        ListBox getLbNotification();

        /**
         * Renvoi la valeur de rttToolbar.
         * @return rttToolbar
         */
        RichTextToolbar getRttToolbar();

        /**
         * Renvoi la valeur de rtaDescriptif.
         * @return rtaDescriptif
         */
        RichTextArea getRtaDescriptif();

        /**
         * Renvoi la valeur de taHistorique.
         * @return taHistorique
         */
        HasHTML getTaHistorique();

        /**
         * Renvoi la valeur de slbsResultatAction.
         * @return slbsResultatAction
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbsResultatAction();

        /**
         * Renvoi la valeur de lOpportunite.
         * @return lOpportunite
         */
        HasText getlOpportunite();

        /**
         * Renvoi le handler du clique sur le lien opportunité.
         * @return lOpportunite
         */
        HasClickHandlers getlOpportuniteHandler();

        /**
         * Renvoi la valeur de slbsStatut.
         * @return slbsStatut
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbStatut();

        /**
         * Renvoi la valeur de iconeErreurChampManager.
         * @return iconeErreurChampManager
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Récupère la valeur de slbNatureResultat.
         * @return la valeur de slbNatureResultat
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureResultat();

        /**
         * Récupère la valeur de slbNatureResultat.
         * @return la valeur de slbNatureResultat
         */
        HasValue<IdentifiantLibelleGwt> getSlbNatureResultatValue();

        /**
         * Accesseur handler nature contact.
         * @return handler.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureContact();

        /**
         * Accesseur valeur nature contact.
         * @return valeur nature contact
         */
        HasValue<IdentifiantLibelleGwt> getSlbNatureContactValue();

        /**
         * Affiche la liste des résultats de nature.
         * @param affiche flag indiquant si la liste doit être affichée (true) ou pas (false)
         */
        void afficherListeNaturesResultat(boolean affiche);

        /**
         * Récupération du conteneur pour les fonctionalités de GED.
         * @return le conteneur Ged
         */
        HasWidgets getConteneurGed();

        /**
         * Retourne le handler pour la suggestion d'agences.
         * @return handler de suggestion.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestAttributionAgence();

        /**
         * Retourne le handler pour la suggestion de ressources.
         * @return handler de suggestion.
         */
        HasSuggestListBoxHandler<DimensionRessourceModel> getSuggestRessourceAgence();

        /**
         * Retourne l'identifiant de la ressource selectionnée.
         * @return l'identifiant de la ressource selectionnée.
         */
        DimensionRessourceModel getRessourceAgence();

        /**
         * Renvoi la valeur de lRessourceAgence.
         * @return lRessourceAgence
         */
        HasText getLRessourceAgence();

        /**
         * Renvoi la valeur de lEtat.
         * @return lEtat
         */
        HasText getLEtat();

        /**
         * Affiche la liste des ressource de l'agence (si true) ou le libellé de la ressource (si false).
         * @param afficherListe le flag indiquant si la liste doit être affichée
         */
        void afficherListeRessourceAgence(boolean afficherListe);

        /**
         * Accesseur à l'identifiant de l'agence à laquelle l'action est attribuée.
         * @return identifiant de l'agence à laquelle l'action est attribuée
         */
        IdentifiantLibelleGwt getAttributionAgence();

        /**
         * Accesseur valeur de la liste déroulante de suggestion d'agence à laquelle l'action est attribuée.
         * @return la valeur de la liste déroulante
         */
        HasValue<IdentifiantLibelleGwt> getSlbAttributionAgence();

        /**
         * Accesseur valeur de la liste déroulante de suggestion d'agence à laquelle l'action est attribuée.
         * @return la valeur de la liste déroulante
         */
        HasValue<DimensionRessourceModel> getSlbRessourceAgence();

        /**
         * Affiche la liste des agences (si true) ou le libellé d'agence (si false).
         * @param afficherListe le flag indiquant si la liste doit être affichée
         */
        void afficherListeAgence(boolean afficherListe);

        /**
         * Handler changement de valeur de la liste déroulante de la ressource à laquelle l'action est attribuée.
         * @return le handler
         */
        HasValueChangeHandlers<DimensionRessourceModel> getSuggestRessourceChangeHandler();

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
         * Récupère la valeur pour l'ajout ou non à l'agenda.
         * @return la valeur pour l'ajout ou non à l'agenda.
         */
        HasValue<Boolean> getCbAjoutAgenda();

        /**
         * Active / Désactive l'ajout à l'agenda.
         * @param enabled true pour activer, false pour désactiver.
         */
        void activerAjoutAgenda(boolean enabled);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
