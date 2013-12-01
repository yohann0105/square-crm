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
package com.square.client.gwt.client.event.code.postal.commune;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.client.gwt.client.model.IdentifiantLibelleCodePostalCommuneModel;

/**
 * Evenement pour la récupération des suggestions pour la suggestbox des codes postaux.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class SetRequestSuggestionsCodePostalCommuneEvent extends GwtEvent<SetRequestSuggestionsCodePostalCommuneEventHandler> {

    /** Type de l'evenement. */
    public static final Type<SetRequestSuggestionsCodePostalCommuneEventHandler> TYPE = new Type<SetRequestSuggestionsCodePostalCommuneEventHandler>();

    /** Libelle de recherche. */
    private String libelle;

    /** Le callback. */
    private AsyncCallback<List<IdentifiantLibelleCodePostalCommuneModel>> asyncCallback;

    /**
     * Constructeur par defaut.
     * @param modele l'objet associé à la ligne.
     */
    public SetRequestSuggestionsCodePostalCommuneEvent(String libelle, AsyncCallback<List<IdentifiantLibelleCodePostalCommuneModel>> asyncCallback) {
        super();
        this.libelle = libelle;
        this.asyncCallback = asyncCallback;
    }

    @Override
    public Type<SetRequestSuggestionsCodePostalCommuneEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SetRequestSuggestionsCodePostalCommuneEventHandler handler) {
        handler.setRequestSuggestions(this);
    }

    /**
     * Get the libelle value.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Get the asyncCallback value.
     * @return the asyncCallback
     */
    public AsyncCallback<List<IdentifiantLibelleCodePostalCommuneModel>> getAsyncCallback() {
        return asyncCallback;
    }

}
