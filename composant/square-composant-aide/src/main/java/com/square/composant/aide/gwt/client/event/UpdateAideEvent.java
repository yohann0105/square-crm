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
package com.square.composant.aide.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.composant.aide.gwt.client.model.AideModel;

/**
 * Evenement de type DemandeAideEvent.
 * @author Ksouri Mohamed Ali - SCUB
 */
public class UpdateAideEvent extends GwtEvent<UpdateAideEventHandler> {

    /** Type de l'evenement. */
    public static final Type<UpdateAideEventHandler> TYPE = new Type<UpdateAideEventHandler>();

    /** L'objet en cours. **/
    private AsyncCallback<AideModel> callBack;

    /** Valeur saisie dans le text Box. **/
    private AideModel aide;

    /**
     * Constructeur par defaut.
     * @param callBack .
     * @param aide le modéle de l'aide.
     */
    public UpdateAideEvent(AsyncCallback<AideModel> callBack, AideModel aide) {
        super();
        this.callBack = callBack;
        this.aide = aide;
    }

    @Override
    public Type<UpdateAideEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UpdateAideEventHandler handler) {
        handler.onUpdateAide(this);
    }

    /**
     * Recuperer la callback du composant.
     * @return the callBack
     */
    public AsyncCallback<AideModel> getCallBack() {
        return callBack;
    }

    /**
     * récupére le modéle de l'aide.
     * @return the aide
     */
    public AideModel getAide() {
        return aide;
    }

}
