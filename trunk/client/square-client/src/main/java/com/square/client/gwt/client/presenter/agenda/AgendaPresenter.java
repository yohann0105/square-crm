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
package com.square.client.gwt.client.presenter.agenda;

import java.util.Date;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.event.DateRequestEvent;
import com.bradrydzewski.gwt.calendar.client.event.DateRequestHandler;
import com.bradrydzewski.gwt.calendar.client.event.HasDateRequestHandlers;
import com.bradrydzewski.gwt.calendar.client.event.HasUpdateHandlers;
import com.bradrydzewski.gwt.calendar.client.event.UpdateEvent;
import com.bradrydzewski.gwt.calendar.client.event.UpdateHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasOpenHandlers;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.event.OpenPersonneMoraleEvent;
import com.square.client.gwt.client.model.AgendaModel;
import com.square.client.gwt.client.model.AgendasDisponiblesCriteresModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.model.PersonneMoraleModel;
import com.square.client.gwt.client.model.RendezVousModel;
import com.square.client.gwt.client.service.AgendaServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.agenda.AgendaViewImplConstants;

/**
 * Presenter de la page de l'agenda.
 * @author cblanchard - SCUB
 */
public class AgendaPresenter extends Presenter {

    /** La service de l'agenda. */
    private AgendaServiceGwtAsync agendaRpcService;

    /** La vue. */
    private AgendaView agendaView;

    /** Le service des personnes. */
    private PersonneServiceGwtAsync personneRpcService;

    private boolean vueParSemaine = true;

    private Date dateReference;

    private ConstantesModel constantes;

    private DimensionRessourceModel ressourceConnectee;

    private DimensionRessourceModel personneSelectionnee;

    /**
     * Constructeur.
     * @param eventBus le bus
     * @param agendaRpcService le service de l'agenda
     * @param personneRpcService le service des personnes
     * @param agendaView la vue
     * @param constantes constantes
     * @param ressourceConnectee ressourceConnectee
     */
    public AgendaPresenter(HandlerManager eventBus, AgendaServiceGwtAsync agendaRpcService, PersonneServiceGwtAsync personneRpcService, AgendaView agendaView,
        ConstantesModel constantes, DimensionRessourceModel ressourceConnectee) {
        super(eventBus);
        this.agendaRpcService = agendaRpcService;
        this.agendaView = agendaView;
        this.personneRpcService = personneRpcService;
        this.constantes = constantes;
        this.ressourceConnectee = ressourceConnectee;
    }

    @Override
    public View asView() {
        return this.agendaView;
    }

    @Override
    public void onBind() {
        // Demande d'affichage de la vue par mois
        agendaView.getBtVueMois().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                agendaView.modeVueMois();
                vueParSemaine = false;
                alimenterAgenda(1, 0);
                agendaView.afficherNavigation(true);
            }
        });
        // Demande d'affichage de la vue par semaines
        agendaView.getBtVueSemaine().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                alimenterAgenda(0, 0);
                vueParSemaine = true;
                agendaView.afficherNavigation(true);
            }
        });
        // Demande d'ouverture d'une action
        agendaView.getCalendarAppointment().addOpenHandler(new OpenHandler<Appointment>() {
            @Override
            public void onOpen(OpenEvent<Appointment> event) {
                final Appointment appt = event.getTarget();
                final String id = appt.getId();

                if (id != null && id.indexOf("#") > 0) {
                    final String[] ids = id.split("#");
                    final Long idAction = Long.valueOf(ids[0]);
                    final Long idPersonne = Long.valueOf(ids[1]);
                    personneRpcService.rechercherPersonneParId(idPersonne, new AsyncCallback<PersonneBaseModel>() {
                        @Override
                        public void onSuccess(PersonneBaseModel result) {
                            if (result instanceof PersonneModel) {
                                final PersonneModel personneModel = (PersonneModel) result;
                                fireEventGlobalBus(new OpenPersonEvent(personneModel.getIdentifiant(), personneModel.getNumClient(),
                                    personneModel.getNom() != null ? personneModel.getNom() : "", personneModel.getPrenom() != null ? personneModel.getPrenom()
                                        : "", (personneModel.getNaturePersonne() != null) ? personneModel.getNaturePersonne().getIdentifiant() : null,
                                    idAction, null));
                            } else if (result instanceof PersonneMoraleModel) {
                                final PersonneMoraleModel personneMorale = (PersonneMoraleModel) result;
                                fireEventGlobalBus(new OpenPersonneMoraleEvent(idPersonne, (personneMorale.getNature() != null) ? personneMorale.getNature()
                                        .getIdentifiant() : null, personneMorale.getRaisonSociale(), idAction));
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            agendaView.onRpcFailure(new ErrorPopupConfiguration(caught));
                        }
                    });
                }
            }
        });
        // Demande d'ouverture de semaine à partir du mois
        agendaView.getCalendarDateRequest().addDateRequestHandler(new DateRequestHandler<Date>() {
            @Override
            public void onDateRequested(DateRequestEvent<Date> event) {
                agendaView.afficherLoadingPopup(new LoadingPopupConfiguration(agendaView.getViewConstants().libelleChargement()));
                agendaView.modeVueJour(event.getTarget());
                vueParSemaine = true;
                dateReference = event.getTarget();
                agendaView.masquerLoadingPopup();
                agendaView.afficherNavigation(false);
            }
        });
        // Mise à jour dans l'agenda
        agendaView.getCalendarUpdate().addUpdateHandler(new UpdateHandler<Appointment>() {
            @Override
            public void onUpdate(UpdateEvent<Appointment> event) {
                event.setCancelled(true);
            }
        });
        agendaView.getBtAvancer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (vueParSemaine) {
                    alimenterAgenda(0, 1);
                } else {
                    alimenterAgenda(1, 1);
                }
            }
        });
        agendaView.getBtReculer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (vueParSemaine) {
                    alimenterAgenda(0, -1);
                } else {
                    alimenterAgenda(1, -1);
                }
            }
        });
        agendaView.getBtRemiseAZero().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (vueParSemaine) {
                    alimenterAgenda(0, 0);
                } else {
                    alimenterAgenda(1, 0);
                }
            }
        });
        agendaView.getSlbAgendasSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<DimensionRessourceModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<DimensionRessourceModel> event) {
                final AgendasDisponiblesCriteresModel criteres = new AgendasDisponiblesCriteresModel();
                criteres.setLibelle(event.getSuggest());
                // si utilisateurConnecte a le role animateur mais pas le role admin, on filtre sur l'animateur
                if (!constantes.isHasRoleAdmin() && constantes.isHasRoleAnimateur()) {
                    criteres.setIdAnimateur(ressourceConnectee.getIdentifiant());
                }
                agendaRpcService.rechercherAgendasDisponibles(criteres, event.getCallBack());
            }
        });
        agendaView.getSlbAgendasValueHandler().addValueChangeHandler(new ValueChangeHandler<DimensionRessourceModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<DimensionRessourceModel> event) {
                personneSelectionnee = event.getValue();
                // permet de ne pas faire de refresh quand on charge l'utilisateur connecté par défaut
                if (personneSelectionnee != null && dateReference != null) {
                    refresh();
                }
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        container.add(agendaView.asWidget());

        agendaView.afficherListeAgenda(constantes.isHasRoleAdmin() || constantes.isHasRoleAnimateur());
        if (constantes.isHasRoleAdmin() || constantes.isHasRoleAnimateur()) {
            // on charge l'utilisateur connecté par défaut
            agendaView.getSlbAgendasValueHandler().setValue(ressourceConnectee);
        }
        personneSelectionnee = ressourceConnectee;

        dateReference = new Date();
        // on initialise sur la semaine en cours le temps du chargement par soucis esthétique
        final Date dateLundi = new Date();
        dateLundi.setDate(dateReference.getDate() - dateReference.getDay() + 1);
        configurationAgenda();
        agendaView.selectionnerSemaine(DateUtil.getString(dateLundi));
        agendaView.setStyle();

        refresh();
        vueParSemaine = true;
    }

    private void alimenterAgenda(final int unite, int valeur) {
        if (personneSelectionnee != null) {
            agendaView.afficherLoadingPopup(new LoadingPopupConfiguration(agendaView.getViewConstants().libelleChargement()));
            agendaRpcService.rechercherActionsAgenda(personneSelectionnee.getIdentifiant(), unite, valeur, dateReference, new AsyncCallback<AgendaModel>() {
                @Override
                public void onFailure(Throwable caught) {
                    agendaView.onRpcFailure(new ErrorPopupConfiguration(caught));
                }

                @Override
                public void onSuccess(AgendaModel result) {
                    agendaView.nettoyerAgenda();

                    dateReference = result.getDateReference();
                    String libellePeriode = "";
                    if (unite == 0) {
                        if (result.getAnneeDebut().equals(result.getAnneeFin())) {
                            libellePeriode =
                                result.getJourDebut() + " " + result.getMoisDebut() + " - " + result.getJourFin() + " " + result.getMoisFin() + " "
                                    + result.getAnneeFin();
                        } else if (result.getMoisDebut().equals(result.getMoisFin())) {
                            libellePeriode = result.getJourDebut() + " - " + result.getJourFin() + " " + result.getMoisFin() + " " + result.getAnneeFin();
                        } else {
                            libellePeriode =
                                result.getJourDebut() + " " + result.getMoisDebut() + " " + result.getAnneeDebut() + " - " + result.getJourFin() + " "
                                    + result.getMoisFin() + " " + result.getAnneeFin();
                        }
                        agendaView.selectionnerSemaine(result.getDateDebut());
                    } else if (unite == 1) {
                        libellePeriode = result.getMoisDebut() + " " + result.getAnneeDebut();
                        agendaView.selectionnerMois(result.getDateDebut());
                    }
                    agendaView.getlPeriode().setText(libellePeriode);

                    for (RendezVousModel rendezVous : result.getRendezVous()) {
                        // Construction d'une action
                        final Date dateFin = (Date) rendezVous.getDate().clone();
                        if (rendezVous.getNbMinutesDuree() != null) {
                            dateFin.setMinutes(dateFin.getMinutes() + rendezVous.getNbMinutesDuree());
                        } else {
                            // si pas de durée, durée par défaut est de 1 heure
                            dateFin.setHours(dateFin.getHours() + 1);
                        }

                        String description = null;
                        String id = null;
                        Boolean isAdherent = null;
                        Boolean isStatutTerminer = null;

                        if (rendezVous.getIdAction() != null) {
                            description = rendezVous.getNature() + ", ";
                            description += rendezVous.getType() + ", ";
                            description += rendezVous.getObjet();
                            if (rendezVous.getSousObjet() != null) {
                                description += ", " + rendezVous.getSousObjet();
                            }
                            id = rendezVous.getIdAction().toString() + (rendezVous.getIdPersonne() != null ? "#" + rendezVous.getIdPersonne().toString() : "");
                            isAdherent =
                                rendezVous.getNaturePersonne() != null ? rendezVous.getNaturePersonne().getIdentifiant().equals(
                                    constantes.getIdNaturePersonneAdherent())
                                    || rendezVous.getNaturePersonne().getIdentifiant().equals(constantes.getIdNaturePersonneMoraleAdherent()) : null;
                            isStatutTerminer = rendezVous.getIdStatut() != null ? rendezVous.getIdStatut().equals(constantes.getIdStatutTerminer()) : null;
                        }
                        agendaView.ajouterAction(rendezVous.getDate(), dateFin, rendezVous.getTitre(), id, description, isAdherent, isStatutTerminer);
                    }
                    if (vueParSemaine) {
                        agendaView.setStyle();
                    }

                    agendaView.masquerLoadingPopup();
                }
            });
        }
    }

    private void configurationAgenda() {
        agendaView.configurationAgenda(8, 19, new Date().getHours(), false);
    }

    /** Rafraichit l'agenda. */
    public void refresh() {
        // on charge l'agenda
        alimenterAgenda(0, 0);
    }

    /**
     * Interface de la vue.
     */
    public interface AgendaView extends View {

        /**
         * Permet de selectionner une semaine à partir d'une date.
         * @param dateDebut la date
         */
        void selectionnerSemaine(String dateDebut);

        /**
         * Passe en vue jour sur la date target.
         * @param target la date
         */
        void modeVueJour(Date target);

        /**
         * Selectionne le mois.
         * @param string la date
         */
        void selectionnerMois(String string);

        /**
         * La popup d'erreur.
         * @param errorPopupConfiguration la config.
         */
        void onRpcFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Configure l'agenda.
         * @param heureDebutTravail l'heure de début de travail
         * @param heureFinTravail l'heure de fin de travail
         * @param heureCourante l'heure courante
         * @param activationDragAndDrop active ou non le drag and drop
         */
        void configurationAgenda(int heureDebutTravail, int heureFinTravail, int heureCourante, boolean activationDragAndDrop);

        /**
         * Nettoie l'agenda.
         */
        void nettoyerAgenda();

        /**
         * Passe la vue en mode mois.
         */
        void modeVueMois();

        /**
         * Ajoute une action.
         * @param dateDebut la date de debut
         * @param dateFin la date de fin
         * @param titre le titre
         * @param idAction l'identifiant de l'action
         * @param description la description
         * @param isAdherent flag adherent
         */
        void ajouterAction(Date dateDebut, Date dateFin, String titre, String idAction, String description, Boolean isAdherent, Boolean isStatutTerminer);

        /**
         * Clique sur le changement de vue en mois.
         * @return le bouton vue mois
         */
        HasClickHandlers getBtVueMois();

        /**
         * Clique sur le changement de vue par semaine.
         * @return le bouton vu par semaine
         */
        HasClickHandlers getBtVueSemaine();

        /**
         * Gestion de la popup pour le chargement.
         * @param loadingPopupConfiguration la configuration
         */
        void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration);

        /**
         * Masque la popup de chargement.
         */
        void masquerLoadingPopup();

        /**
         * Renvoi la valeur de viewConstants.
         * @return viewConstants
         */
        AgendaViewImplConstants getViewConstants();

        /**
         * Gestion de l'évènement de l'ouverture d'une action.
         * @return le double clique sur une action
         */
        HasOpenHandlers<Appointment> getCalendarAppointment();

        /**
         * Renvoi la valeur de btAvancer.
         * @return btAvancer
         */
        HasClickHandlers getBtAvancer();

        /**
         * Renvoi la valeur de btReculer.
         * @return btReculer
         */
        HasClickHandlers getBtReculer();

        /**
         * Renvoi la valeur de btRemiseAZero.
         * @return btRemiseAZero
         */
        HasClickHandlers getBtRemiseAZero();

        /**
         * Renvoi la valeur de lPeriode.
         * @return lJours
         */
        HasText getlPeriode();

        /**
         * Gestion de la mise à jour d'une action.
         * @return la modification d'une action
         */
        HasUpdateHandlers<Appointment> getCalendarUpdate();

        /**
         * Clique pour la demande d'affichage d'un jour.
         * @return clique pour l'affichage d'un jour
         */
        HasDateRequestHandlers<Date> getCalendarDateRequest();

        /**
         * Affiche la navigation.
         * @param visible flag visible
         */
        void afficherNavigation(boolean visible);

        /**
         * Definit le style.
         */
        void setStyle();

        /**
         * Affiche la liste des agendas.
         * @param visible flag visible
         */
        void afficherListeAgenda(boolean visible);

        /**
         * Handler sur les valeurs.
         * @return handler
         */
        HasSuggestListBoxHandler<DimensionRessourceModel> getSlbAgendasSuggestHandler();

        /**
         * Handler sur les valeurs.
         * @return handler
         */
        HasValue<DimensionRessourceModel> getSlbAgendasValueHandler();

    }

    @Override
    public void onDetach() {
    }
}
