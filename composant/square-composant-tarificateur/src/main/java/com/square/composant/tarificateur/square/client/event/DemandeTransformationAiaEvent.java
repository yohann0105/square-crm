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
 * Evenement sur une demande d'envoi de la transformation aia.
 */
public class DemandeTransformationAiaEvent extends GwtEvent<DemandeTransformationAiaEventHandler> {

    private final Long idDevis;
    private final boolean creerActionSuivi;
    private final boolean rechargerInfosAdhesion;

    /** Type de l'evenement. */
    public static final Type<DemandeTransformationAiaEventHandler> TYPE = new Type<DemandeTransformationAiaEventHandler>();

    /**
     * Constructeur.
     */
    public DemandeTransformationAiaEvent(Long idDevis, boolean creerActionSuivi, boolean rechargerInfosAdhesion) {
        this.idDevis = idDevis;
        this.creerActionSuivi = creerActionSuivi;
        this.rechargerInfosAdhesion = rechargerInfosAdhesion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(DemandeTransformationAiaEventHandler handler) {
        handler.demanderTransformation(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type<DemandeTransformationAiaEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Récupération de idDevis.
     * @return the idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }

    /**
     * Récupération de creerActionSuivi.
     * @return the creerActionSuivi
     */
    public boolean isCreerActionSuivi() {
        return creerActionSuivi;
    }

    /**
     * Récupération de rechargerInfosAdhesion.
     * @return the rechargerInfosAdhesion
     */
    public boolean isRechargerInfosAdhesion() {
        return rechargerInfosAdhesion;
    }
}
