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
package com.square.client.gwt.client.event.actions;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evenement pour la popup annuler action.
 * @author cblanchard - SCUB
 */
public class AjoutPopupActionAnnulerEvent extends GwtEvent<AjoutPopupActionAnnulerEventHandler> {

    /** Type de l'evenement. */
    public static final Type<AjoutPopupActionAnnulerEventHandler> TYPE = new Type<AjoutPopupActionAnnulerEventHandler>();

    /** Identifiant de l'action. */
    private Long idAction;

    /** Identifiant de la personne. */
    private Long idPersonne;

    /** Identifiant de l'opportunité utilisée pour filtrer les actions. */
    private Long filtreidOpportunite;

    /** Identifiant externe de l'opportunité utilisée pour filtrer les actions. */
    private String filtreEidOpportunite;

    /**
     * Constructeur.
     * @param idAction l'identifiant de l'action.
     * @param idPersonne l'identifiant de la personne.
     * @param filtreidOpportunite Identifiant de l'opportunité utilisée pour filtrer les actions
     * @param filtreEidOpportunite Identifiant externe de l'opportunité utilisée pour filtrer les actions
     */
    public AjoutPopupActionAnnulerEvent(Long idAction, Long idPersonne, Long filtreidOpportunite, String filtreEidOpportunite) {
        super();
        this.idAction = idAction;
        this.idPersonne = idPersonne;
        this.filtreidOpportunite = filtreidOpportunite;
        this.filtreEidOpportunite = filtreEidOpportunite;
    }

    @Override
    protected void dispatch(AjoutPopupActionAnnulerEventHandler handler) {
        handler.ajouterAction(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<AjoutPopupActionAnnulerEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Renvoi la valeur de idAction.
     * @return idAction
     */
    public Long getIdAction() {
        return idAction;
    }

    /**
     * Renvoi l'identifiant de la personne.
     * @return idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Getter function.
     * @return the filtreidOpportunite
     */
    public Long getFiltreidOpportunite() {
        return filtreidOpportunite;
    }

    /**
     * Getter function.
     * @return the filtreEidOpportunite
     */
    public String getFiltreEidOpportunite() {
        return filtreEidOpportunite;
    }

}
