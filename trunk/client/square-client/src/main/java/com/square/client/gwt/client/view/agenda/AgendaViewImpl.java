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
package com.square.client.gwt.client.view.agenda;

import java.util.Date;

import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.AppointmentStyle;
import com.bradrydzewski.gwt.calendar.client.CalendarSettings;
import com.bradrydzewski.gwt.calendar.client.CalendarViews;
import com.bradrydzewski.gwt.calendar.client.event.HasDateRequestHandlers;
import com.bradrydzewski.gwt.calendar.client.event.HasUpdateHandlers;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasOpenHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.bundle.theme.smatis.SmatisResources;
import com.square.client.gwt.client.composant.bloc.ContenuOnglet;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.presenter.agenda.AgendaPresenter.AgendaView;
import com.square.client.gwt.client.util.agenda.Calendar;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;

/**
 * Implémentation de la vue de l'agenda.
 * @author cblanchard - SCUB
 */
public class AgendaViewImpl extends Composite implements AgendaView {

    /** View constants. */
    private static AgendaViewImplConstants viewConstants = (AgendaViewImplConstants) GWT.create(AgendaViewImplConstants.class);

    private static AgendaViewImplDebugIdConstants viewDebugIdConstants = (AgendaViewImplDebugIdConstants) GWT.create(AgendaViewImplDebugIdConstants.class);

    /** Conteneur principal. */
    private VerticalPanel conteneur;

    /** Agenda. */
    private Calendar calendar;

    /** Bouton pour basculer sur la vue par semaines. */
    private DecoratedButton btVueSemaine;

    /** Bouton pour basculer la vue par mois. */
    private DecoratedButton btVueMois;

    /** Bouton pour avancer. */
    private PushButton btAvancer;

    /** Bouton pour reculer. */
    private PushButton btReculer;

    /** Bouton pour revenir à la date d'aujourd'hui. */
    private DecoratedButton btRemiseAZero;

    /** Label pour la période. */
    private Label lPeriode;

    private HorizontalPanel hpListeAgendas;

    private DecoratedSuggestListBoxSingle<DimensionRessourceModel> slbAgendas;

    /**
     * Constructeur.
     */
    public AgendaViewImpl() {
        conteneur = new VerticalPanel();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.setHeight(AppControllerConstants.POURCENT_100);
        calendar = new Calendar();
        calendar.ensureDebugId(viewDebugIdConstants.debugIdCalendar());
        setStyle();

        construireListeAgendas();

        construireBlocBouton();

        calendar.setWidth(AppControllerConstants.POURCENT_100);
        calendar.setHeight(AppControllerConstants.POURCENT_100);
        conteneur.setSpacing(10);

        conteneur.add(calendar);
        conteneur.addStyleName(SquareResources.INSTANCE.css().agenda());
        initWidget(new ContenuOnglet(conteneur));
    }

    private void construireListeAgendas() {
        hpListeAgendas = new HorizontalPanel();
        hpListeAgendas.setVisible(false);
        slbAgendas = new DecoratedSuggestListBoxSingle<DimensionRessourceModel>(new SuggestListBoxSingleProperties<DimensionRessourceModel>() {
            @Override
            public String getSelectSuggestRenderer(DimensionRessourceModel row) {
                return row == null ? "" : row.getNom() + " " + row.getPrenom();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(DimensionRessourceModel row) {
                return new String[] {row == null ? "" : row.getNom() + " " + row.getPrenom()};
            }
        });
        final Label lAgendas = new Label(viewConstants.libelleListeAgendas());
        hpListeAgendas.add(lAgendas);
        hpListeAgendas.add(slbAgendas);
        hpListeAgendas.setCellVerticalAlignment(lAgendas, HasAlignment.ALIGN_MIDDLE);
        hpListeAgendas.setCellVerticalAlignment(slbAgendas, HasAlignment.ALIGN_MIDDLE);
        hpListeAgendas.setSpacing(5);
    }

    private void construireBlocBouton() {
        final HorizontalPanel hpBoutons = new HorizontalPanel();

        btVueMois = new DecoratedButton(viewConstants.libelleMois());
        btVueMois.ensureDebugId(viewDebugIdConstants.debugIdBtVueMois());
        btVueSemaine = new DecoratedButton(viewConstants.libelleSemaine());
        btVueSemaine.ensureDebugId(viewDebugIdConstants.debugIdBtVueSemaine());
        btRemiseAZero = new DecoratedButton(viewConstants.libelleJour());
        btRemiseAZero.ensureDebugId(viewDebugIdConstants.debugIdBtRemiseAZero());
        btAvancer = new PushButton(new Image(SmatisResources.INSTANCE.iconeOngletScrollDroite()));
        btAvancer.ensureDebugId(viewDebugIdConstants.debugIdBtAvancer());
        btReculer = new PushButton(new Image(SmatisResources.INSTANCE.iconeOngletScrollGauche()));
        btReculer.ensureDebugId(viewDebugIdConstants.debugIdBtReculer());
        btReculer.setStylePrimaryName("agendaPushButton");
        btAvancer.setStylePrimaryName("agendaPushButton");

        final HorizontalPanel hpVues = new HorizontalPanel();
        hpVues.add(btVueSemaine);
        hpVues.add(btVueMois);
        hpVues.setSpacing(5);
        hpBoutons.add(hpVues);

        final HorizontalPanel hpValeur = new HorizontalPanel();
        lPeriode = new Label();
        hpValeur.add(lPeriode);
        hpValeur.setSpacing(5);
        hpBoutons.add(hpValeur);

        final HorizontalPanel hpDeplacement = new HorizontalPanel();
        hpDeplacement.add(btReculer);
        hpDeplacement.add(btRemiseAZero);
        hpDeplacement.add(btAvancer);
        hpDeplacement.setSpacing(5);
        hpDeplacement.setCellVerticalAlignment(btAvancer, HasAlignment.ALIGN_MIDDLE);
        hpDeplacement.setCellVerticalAlignment(btReculer, HasAlignment.ALIGN_MIDDLE);
        hpBoutons.add(hpDeplacement);

        hpBoutons.setCellHorizontalAlignment(hpDeplacement, HasAlignment.ALIGN_RIGHT);
        hpBoutons.setCellHorizontalAlignment(hpVues, HasAlignment.ALIGN_LEFT);
        hpBoutons.setCellHorizontalAlignment(hpValeur, HasAlignment.ALIGN_CENTER);
        hpBoutons.setCellVerticalAlignment(hpValeur, HasAlignment.ALIGN_MIDDLE);

        hpBoutons.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.add(hpBoutons);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void selectionnerSemaine(String dateDebut) {
        final Date dateDebutSemaine = DateUtil.getDate(dateDebut);
        calendar.setDate(dateDebutSemaine);
        calendar.setView(CalendarViews.DAY, 6);
        setStyle();
    }

    @Override
    public void ajouterAction(Date dateDebut, Date dateFin, String titre, String idAction, String description, Boolean isAdherent, Boolean isStatutTerminer) {
        calendar.suspendLayout();
        final Appointment appt = new Appointment();
        appt.setStart(dateDebut);
        appt.setEnd(dateFin);
        appt.setTitle(titre);
        if (idAction != null) {
            appt.setId(idAction);
            if (isAdherent != null && isAdherent.equals(Boolean.TRUE)) {
                appt.setStyle(AppointmentStyle.LIGHT_GREEN);
            } else {
                appt.setStyle(AppointmentStyle.GREEN);
            }
            if (isStatutTerminer != null && isStatutTerminer.equals(Boolean.TRUE)) {
                appt.setStyle(AppointmentStyle.RED);
            }
        } else {
            appt.setStyle(AppointmentStyle.GREY);
        }
        if (description != null) {
            appt.setDescription(description);
        }
        calendar.addAppointment(appt);
        calendar.resumeLayout();
    }

    @Override
    public void setStyle() {
        calendar.setStyleName(SquareResources.INSTANCE.css().calendar());
    }

    @Override
    public void modeVueMois() {
        calendar.setView(CalendarViews.MONTH);
    }

    @Override
    public void configurationAgenda(int heureDebutTravail, int heureFinTravail, int heureCourante, boolean activationDragAndDrop) {
        final CalendarSettings calendarSettings = new CalendarSettings();
        calendarSettings.setIntervalsPerHour(4);
        calendarSettings.setEnableDragDrop(activationDragAndDrop);
        calendarSettings.setWorkingHourStart(heureDebutTravail);
        calendarSettings.setWorkingHourEnd(heureFinTravail);
        calendarSettings.setScrollToHour(heureCourante);
        calendarSettings.setPixelsPerInterval(20);
        calendar.setSettings(calendarSettings);
    }

    @Override
    public void onRpcFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public HasClickHandlers getBtVueMois() {
        return btVueMois;
    }

    @Override
    public HasClickHandlers getBtVueSemaine() {
        return btVueSemaine;
    }

    @Override
    public HasOpenHandlers<Appointment> getCalendarAppointment() {
        return calendar;
    }

    @Override
    public HasUpdateHandlers<Appointment> getCalendarUpdate() {
        return calendar;
    }

    @Override
    public HasDateRequestHandlers<Date> getCalendarDateRequest() {
        return calendar;
    }

    @Override
    public void nettoyerAgenda() {
        calendar.clearAppointments();
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public AgendaViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public HasClickHandlers getBtAvancer() {
        return btAvancer;
    }

    @Override
    public HasClickHandlers getBtReculer() {
        return btReculer;
    }

    @Override
    public HasClickHandlers getBtRemiseAZero() {
        return btRemiseAZero;
    }

    @Override
    public HasText getlPeriode() {
        return lPeriode;
    }

    @Override
    public void selectionnerMois(String date) {
        calendar.setDate(DateUtil.getDate(date));
    }

    @Override
    public void modeVueJour(Date target) {
        calendar.setView(CalendarViews.DAY, 1);
        calendar.setDate(target);
        setStyle();
    }

    @Override
    public void afficherNavigation(boolean visible) {
        lPeriode.setVisible(visible);
        btAvancer.setVisible(visible);
        btReculer.setVisible(visible);
        btRemiseAZero.setVisible(visible);
    }

    @Override
    public void afficherListeAgenda(boolean visible) {
        if (visible && !hpListeAgendas.isAttached()) {
            conteneur.insert(hpListeAgendas, 0);
        } else if (hpListeAgendas.isAttached()) {
            conteneur.remove(hpListeAgendas);
        }
    }

    @Override
    public HasSuggestListBoxHandler<DimensionRessourceModel> getSlbAgendasSuggestHandler() {
        return slbAgendas;
    }

    @Override
    public HasValue<DimensionRessourceModel> getSlbAgendasValueHandler() {
        return slbAgendas;
    }

}
