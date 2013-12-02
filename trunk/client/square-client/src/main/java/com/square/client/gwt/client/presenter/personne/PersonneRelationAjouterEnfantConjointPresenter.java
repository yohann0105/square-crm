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

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.view.personne.relations.PersonneRelationAjouterEnfantConjointViewImpl;

/**
 * Presenter pour la popup d'ajout de demande d'ajout d'un enfant au conjoint.
 * @author scub - SCUB
 */
public class PersonneRelationAjouterEnfantConjointPresenter extends Presenter {

    /** la vue. */
    private PersonneRelationAjouterEnfantConjointView view;

    /** l'id du conjoint. */
    private Long idConjoint;

    /**
     * Constructeur.
     * @param eventBus le bus
     * @param viewImpl la vue
     * @param idDuConjoint l'id du conjoint
     */
    PersonneRelationAjouterEnfantConjointPresenter(HandlerManager eventBus, PersonneRelationAjouterEnfantConjointViewImpl viewImpl, Long idDuConjoint) {
        super(eventBus);
        this.view = viewImpl;
        this.idConjoint = idDuConjoint;
    }

    /**
     * Getter.
     * @return the idConjoint
     */
    public Long getIdConjoint() {
        return idConjoint;
    }

    /**
     * Setter.
     * @param idConjoint the idConjoint to set
     */
    public void setIdConjoint(Long idConjoint) {
        this.idConjoint = idConjoint;
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
            }
        });
    }
    /**
     * Renvoi la valeur de btnOui.
     * @return btnOui
     */
    public HasClickHandlers getBtnNon() {
        return view.getBtnNon();
    }

    /**
     * Renvoi la valeur de btnNon.
     * @return btnNon
     */
    public HasClickHandlers getBtnOui() {
        return view.getBtnOui();
    }

    @Override
    public void onShow(HasWidgets container) {
        view.showPopup();
    }


    /** Interface de la vue. */
    public interface PersonneRelationAjouterEnfantConjointView extends View {

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
