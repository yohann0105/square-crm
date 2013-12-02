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
package com.square.composant.emails.square.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evènement envoyé lorsque les emails ont été chargés.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class ListeEmailsChargeesEvent extends GwtEvent<ListeEmailsChargeesEventHandler> {

    /** Type de l'evenement. */
    public static final Type<ListeEmailsChargeesEventHandler> TYPE = new Type<ListeEmailsChargeesEventHandler>();

    /** Le numéro de la personne concernée. */
    private String numeroPersonne;

    /** Le nombre total d'emails. */
    private int nbEmails;

    /**
     * Constructeur.
     * @param numeroPersonne le numéro de la personne concernée
     * @param nbEmails le nombre total d'emails chargés.
     */
    public ListeEmailsChargeesEvent(String numeroPersonne, int nbEmails) {
        super();
        this.numeroPersonne = numeroPersonne;
        this.nbEmails = nbEmails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(ListeEmailsChargeesEventHandler handler) {
        handler.updateInfosEmails(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type<ListeEmailsChargeesEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Getter function.
     * @return the nbEmails
     */
    public int getNbEmails() {
        return nbEmails;
    }

    /**
     * Getter function.
     * @return the numeroPersonne
     */
    public String getNumeroPersonne() {
        return numeroPersonne;
    }

}
