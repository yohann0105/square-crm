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

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.event.opportunites.ForcerCreationOpportuniteEvent;
import com.square.client.gwt.client.view.personne.physique.opportunites.popupCreationForcee.PopupCreationForceeOpportuniteViewImpl;

/**
 * Présenter de la popup de création forcée.
 * @author cblanchard - SCUB
 */
public class PopupCreationForceeOpportunitePresenter extends Presenter {

    /** La vue. */
    private PopupCreationForceeOpportuniteView view;

    /** Identifiant de la personne. */
    private Long idPersonne;

    /**
     * Constructeur.
     * @param eventBus le bus
     * @param view la vue
     * @param idPersonne la personne
     */
    public PopupCreationForceeOpportunitePresenter(HandlerManager eventBus, PopupCreationForceeOpportuniteViewImpl view, Long idPersonne) {
        super(eventBus);
        this.view = view;
        this.idPersonne = idPersonne;
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        view.getBtnRefuser().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
            }
        });
        view.getBtnValider().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEventLocalBus(new ForcerCreationOpportuniteEvent(idPersonne));
                view.hidePopup();
            }
        });

    }

    @Override
    public void onShow(HasWidgets container) {
        view.showPopup();
    }

    /** Interface de la vue de la popup de création forcée. */
    public interface PopupCreationForceeOpportuniteView extends View {

        /** Affiche la popup. */
        void showPopup();

        /**
         * Méthode appelée lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param errorPopupConfiguration infos sur l'erreur.
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /** Masque toutes les popup. */
        void hidePopup();

        /**
         * Renvoi la valeur de btnValider.
         * @return btnValider
         */
        HasClickHandlers getBtnValider();

        /**
         * Renvoi la valeur de btnRefuser.
         * @return btnRefuser
         */
        HasClickHandlers getBtnRefuser();

    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
