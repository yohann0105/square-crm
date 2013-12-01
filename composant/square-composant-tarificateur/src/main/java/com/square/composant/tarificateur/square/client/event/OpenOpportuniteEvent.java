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

/**
 * Evènement permettant d'ouvrir une opportunité.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class OpenOpportuniteEvent extends GwtEvent<OpenOpportuniteEventHandler> {

    /** Type de l'evenement. */
    public static final Type<OpenOpportuniteEventHandler> TYPE = new Type<OpenOpportuniteEventHandler>();

    /**
     * Identifiant externe (Square) de l'opportunité
     */
    private Long eidOpportunite;

    /**
     * Identifiant externe (Square) de la personne principale concernée par l'opportunité
     */
    private Long eidPersonnePrincipale;
    
    /**
     * Constructeur.
     * @param eidOpportunite identifiant square de l'opportunité
     * @param eidPersonnePrincipale identifiant square de la personne principale associée à l'opportunité
     */
    public OpenOpportuniteEvent(Long eidOpportunite, Long eidPersonnePrincipale) {
        super();
        this.eidOpportunite = eidOpportunite;
        this.eidPersonnePrincipale = eidPersonnePrincipale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(OpenOpportuniteEventHandler handler) {
        handler.openOpportunite(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type<OpenOpportuniteEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Getter function.
     * @return the eidOpportunite
     */
    public Long getEidOpportunite() {
        return eidOpportunite;
    }

    /**
     * Getter function.
     * @return the eidPersonnePrincipale
     */
    public Long getEidPersonnePrincipale() {
        return eidPersonnePrincipale;
    }

}
