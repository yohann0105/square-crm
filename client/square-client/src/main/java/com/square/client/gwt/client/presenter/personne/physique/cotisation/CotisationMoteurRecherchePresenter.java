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
package com.square.client.gwt.client.presenter.personne.physique.cotisation;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.service.CotisationServiceGwtAsync;

/**
 * Presenter pour le moteur de recherche des cotisations.
 * @author Farh ZARROUK (farh.zarrouk@gmail.com) - SCUB
 */
public class CotisationMoteurRecherchePresenter extends Presenter {

    /** Service Asynchrone de cotisation. */
    private CotisationServiceGwtAsync cotisationServiceGwtAsync;

    /** Vue associé au presenter. */
    private CotisationMoteurRechercheView view;

    public CotisationMoteurRecherchePresenter(HandlerManager eventBus, CotisationServiceGwtAsync cotisationServiceGwtAsync, CotisationMoteurRechercheView view) {
        super(eventBus);
        this.cotisationServiceGwtAsync = cotisationServiceGwtAsync;
        this.view = view;
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
    }

    @Override
    public void onShow(HasWidgets container) {
    }

    /**
     * Interface de la vue CotisationMoteurRechercheView.
     */
    public interface CotisationMoteurRechercheView extends View {

    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
