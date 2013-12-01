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
package com.square.client.gwt.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.client.gwt.client.model.PaysSimpleModel;

/**
 * Evènement pour la suggestion de pays.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class SuggestPaysEvent extends GwtEvent<SuggestPaysEventHandler> {

    /** Type de l'évènement. */
    public static final Type<SuggestPaysEventHandler> TYPE = new Type<SuggestPaysEventHandler> ();

    /** L'objet en cours. **/
    private AsyncCallback<List<PaysSimpleModel>> callBack;

    /** Valeur saisie dans le text Box. **/
    private String suggest;

    /**
     * Constructeur par defaut.
     * @param callBack .
     * @param suggest la valeur de la suggestion.
     */
    public SuggestPaysEvent(AsyncCallback<List<PaysSimpleModel>> callBack, String suggest) {
        super();
        this.callBack = callBack;
        this.suggest = suggest;
    }

    @Override
    protected void dispatch(SuggestPaysEventHandler handler) {
        handler.suggest(this);
    }

    @Override
    public Type<SuggestPaysEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Recuperer la callback du composant.
     * @return the callBack
     */
    public AsyncCallback<List<PaysSimpleModel>> getCallBack() {
        return callBack;
    }

    /**
     * Recuperer la valeur du suggest.
     * @return the suggest
     */
    public String getSuggest() {
        return suggest;
    }
}
