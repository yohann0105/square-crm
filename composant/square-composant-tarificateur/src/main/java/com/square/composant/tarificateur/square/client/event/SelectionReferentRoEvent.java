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
package com.square.composant.tarificateur.square.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.square.composant.tarificateur.square.client.model.personne.AssureSocialModel;
import com.square.composant.tarificateur.square.client.presenter.adhesion.InfosAdhesionPresenter.BlocInfoSanteSimple;

/**
 * Evenement sur la sélection d'un referent RO.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class SelectionReferentRoEvent extends GwtEvent<SelectionReferentRoEventHandler> {

    /** Type de l'evenement. */
    public static final Type<SelectionReferentRoEventHandler> TYPE = new Type<SelectionReferentRoEventHandler>();

    private AssureSocialModel referent;

    private BlocInfoSanteSimple view;

    /**
     * Constructeur.
     * @param referent le referent à sélectionner
     * @param view la vue source de l'ouverture de la popup
     */
    public SelectionReferentRoEvent(AssureSocialModel referent, BlocInfoSanteSimple view) {
        super();
        this.referent = referent;
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(SelectionReferentRoEventHandler handler) {
        handler.onSelect(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type<SelectionReferentRoEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Get the referent value.
     * @return the referent
     */
    public AssureSocialModel getReferent() {
        return referent;
    }

    /**
     * Get the view value.
     * @return the view
     */
    public BlocInfoSanteSimple getView() {
        return view;
    }

}
