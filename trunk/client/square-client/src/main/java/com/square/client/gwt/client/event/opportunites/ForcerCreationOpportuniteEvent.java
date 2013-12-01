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
package com.square.client.gwt.client.event.opportunites;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evènement pour forcer la création d'une opportunité.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ForcerCreationOpportuniteEvent extends GwtEvent<ForcerCreationOpportuniteEventHandler> {

    /** Type de l'evenement. */
    public static final Type<ForcerCreationOpportuniteEventHandler> TYPE = new Type<ForcerCreationOpportuniteEventHandler>();

    /** Identifiant de la personne. */
    private Long idPersonne;

    /**
     * Constructeur.
     * @param idPersonne l'identifiant de la personne.
     */
    public ForcerCreationOpportuniteEvent(Long idPersonne) {
        super();
        this.idPersonne = idPersonne;
    }

    @Override
    protected void dispatch(ForcerCreationOpportuniteEventHandler handler) {
        handler.forcerCreationOpportunite(this);
    }

    @Override
    public Type<ForcerCreationOpportuniteEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Récupère la valeur de idPersonne.
     * @return la valeur de idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }
}
