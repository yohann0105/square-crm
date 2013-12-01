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
package com.square.composants.graphiques.lib.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;

/**
 * Evenement associé à l'exception de ControleIntegrite.
 * @author sgoumard . - SCUB
 */
public class ControleIntegriteExceptionEvent extends GwtEvent<ControleIntegriteExceptionHandler> {

    /** Type de l'evenement. */
    public static final Type<ControleIntegriteExceptionHandler> TYPE = new Type<ControleIntegriteExceptionHandler>();

    /** le rapport. */
    private RapportModel rapport;

    /** Indique si l'on force la popup d'erreur à apparaitre. */
    private Boolean forcerPopupErreur;

    /**
     * Constructeur.
     * @param rapport .
     */
    public ControleIntegriteExceptionEvent(RapportModel rapport) {
        super();
        this.rapport = rapport;
    }

    /**
     * Constructeur.
     * @param rapport .
     * @param forcerPopupErreur indique si l'on force la popup d'erreur à apparaitre.
     */
    public ControleIntegriteExceptionEvent(RapportModel rapport, Boolean forcerPopupErreur) {
        super();
        this.rapport = rapport;
        this.forcerPopupErreur = forcerPopupErreur;
    }

    @Override
    protected void dispatch(ControleIntegriteExceptionHandler handler) {
        handler.onErreur(this);
    }

    @Override
    public Type<ControleIntegriteExceptionHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Le Rapport.
     * @return the rapport
     */
    public RapportModel getRapport() {
        return rapport;
    }

    /**
     * Récupère la valeur de forcerPopupErreur.
     * @return le booléen
     */
    public Boolean isForcerPopupErreur() {
        return forcerPopupErreur;
    }
}
