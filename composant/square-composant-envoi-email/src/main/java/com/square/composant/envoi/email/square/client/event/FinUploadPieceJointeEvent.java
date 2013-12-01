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
package com.square.composant.envoi.email.square.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evènement pour signaler la fin de l'upload d'une pièce jointe.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class FinUploadPieceJointeEvent extends GwtEvent<FinUploadPieceJointeEventHandler> {

    /** Les propriétés de la pièce jointe. */
    private String proprietesPieceJointe;

    /** Type de l'évènement. */
    public static final Type<FinUploadPieceJointeEventHandler> TYPE = new Type<FinUploadPieceJointeEventHandler>();

    /**
     * Constructeur.
     * @param proprietesPieceJointe les propriétés de la pièce jointe.
     */
    public FinUploadPieceJointeEvent(String proprietesPieceJointe) {
        super();
        this.proprietesPieceJointe = proprietesPieceJointe;
    }

    @Override
    protected void dispatch(FinUploadPieceJointeEventHandler handler) {
        handler.informerFinUploadPieceJointe(this);
    }

    @Override
    public Type<FinUploadPieceJointeEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Récupère la valeur de proprietesPieceJointe.
     * @return la valeur de proprietesPieceJointe
     */
    public String getProprietesPieceJointe() {
        return proprietesPieceJointe;
    }

}
