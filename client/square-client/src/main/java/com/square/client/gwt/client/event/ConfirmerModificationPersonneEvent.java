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

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evènement envoyé pour valider / annuler la modification d'une personne / puis fusionner les doublons.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class ConfirmerModificationPersonneEvent extends GwtEvent<ConfirmerModificationPersonneEventHandler> {

    /** Type de l'évènement. */
    public static final Type<ConfirmerModificationPersonneEventHandler> TYPE = new Type<ConfirmerModificationPersonneEventHandler>();

    /**
     * Enumération des actions possibles confirmées par l'utilisateur.
     */
    public static enum Action {
        /**
         * Enregistrer les modifications de la personne.
         */
        ENREGISTRER,
        /**
         * Enregistrer les modifications de la personne puis lancer la procédure de fusion des doublons.
         */
        ENREGISTRER_PUIS_FUSIONNER,
        /**
         * Annuler les modifications de la personne.
         */
        ANNULER;
    }

    /**
     * Identifiant de la personne concernée par l'évènement.
     */
    private Long idPersonne;

    /**
     * L'action confirmée concernant la modification de la personne.
     */
    private Action action;

    /**
     * Constructeur.
     * @param idPersonne identifiant de la personne concernée par la modification
     * @param action l'action confirmée concernant cette modification
     */
    public ConfirmerModificationPersonneEvent(Long idPersonne, Action action) {
        super();
        this.idPersonne = idPersonne;
        this.action = action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(ConfirmerModificationPersonneEventHandler handler) {
        handler.modifierPersonne(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type<ConfirmerModificationPersonneEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Getter function.
     * @return the idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Getter function.
     * @return the action
     */
    public Action getAction() {
        return action;
    }

}
