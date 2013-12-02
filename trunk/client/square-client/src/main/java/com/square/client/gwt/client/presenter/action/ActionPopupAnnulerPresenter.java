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

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.event.actions.AjoutActionEvent;
import com.square.client.gwt.client.view.action.annulation.ActionPopupAnnulerViewImpl;

/**
 * Presenter de la popup d'annulation.
 * @author cblanchard - SCUB
 */
public class ActionPopupAnnulerPresenter extends Presenter {

    /** la vue. */
    private ActionPopupAnnulerView view;

    /** Id Action. */
    private Long idAction;

    /** Id Personne. */
    private Long idPersonne;

    private Long filtreIdOpportunite;

    private String filtreEidOpportunite;

    /**
     * Constructeur.
     * @param eventBus le bus
     * @param actionPopupAnnulerViewImpl la vue
     * @param idAction l'identifiant de l'action
     * @param idPersonne l'identifiant de la personne
     */
    public ActionPopupAnnulerPresenter(HandlerManager eventBus, ActionPopupAnnulerViewImpl actionPopupAnnulerViewImpl, Long idAction, Long idPersonne,
        Long filtreIdOpportunite, String filtreEidOpportunite) {
        super(eventBus);
        this.view = actionPopupAnnulerViewImpl;
        this.idAction = idAction;
        this.idPersonne = idPersonne;
        this.filtreIdOpportunite = filtreIdOpportunite;
        this.filtreEidOpportunite = filtreEidOpportunite;
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        view.getBtnNon().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.masquerPopup();
            }
        });
        view.getBtnOui().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.masquerPopup();
                fireEventGlobalBus(new AjoutActionEvent(idPersonne, idAction, filtreIdOpportunite, filtreEidOpportunite, false));
            }
        });
    }

    /**
     * Affichage de la popup pour de nouveaux paramètres.
     * @param idAction id
     * @param idPersonne id
     * @param filtreIdOpportunite id
     * @param filtreEidOpportunite id
     */
    public void afficher(Long idAction, Long idPersonne,
        final Long filtreIdOpportunite, String filtreEidOpportunite) {
        this.idAction = idAction;
        this.idPersonne = idPersonne;
        this.filtreIdOpportunite = filtreIdOpportunite;
        this.filtreEidOpportunite = filtreEidOpportunite;
        view.showPopup();
    }

    @Override
    public void onShow(HasWidgets container) {
        view.showPopup();
    }

    /** Interface de la vue. */
    public interface ActionPopupAnnulerView extends View {

        /** Affiche la popup. */
        void showPopup();

        /** Masque la popup. */
        void masquerPopup();

        /**
         * Renvoi la valeur de btnOui.
         * @return btnOui
         */
        HasClickHandlers getBtnNon();

        /**
         * Renvoi la valeur de btnNon.
         * @return btnNon
         */
        HasClickHandlers getBtnOui();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
