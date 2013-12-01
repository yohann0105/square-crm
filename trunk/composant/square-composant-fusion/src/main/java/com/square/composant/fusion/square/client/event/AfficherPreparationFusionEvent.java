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
package com.square.composant.fusion.square.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Handler sur la préparation à la fusion entre deux personnes.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AfficherPreparationFusionEvent extends GwtEvent<AfficherPreparationFusionEventHandler> {

    /** Type de l'évènement. */
    public static final Type<AfficherPreparationFusionEventHandler> TYPE = new Type<AfficherPreparationFusionEventHandler>();

    /** Identifiant de la première personne à fusionner. */
    private Long idPersonne1;

    /** Identifiant de la seconde personne à fusionner. */
    private Long idPersonne2;

    /**
     * Constructeur.
     * @param idPersonne1 le nrid de la première personne à fusionner.
     * @param idPersonne2 le nrid de la seconde personne à fusionner.
     */
    public AfficherPreparationFusionEvent(Long idPersonne1, Long idPersonne2) {
        super();
        this.idPersonne1 = idPersonne1;
        this.idPersonne2 = idPersonne2;
    }

    @Override
    protected void dispatch(AfficherPreparationFusionEventHandler handler) {
        handler.preparerFusion(this);
    }

    @Override
    public Type<AfficherPreparationFusionEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Getter.
     * @return the nridPersonne1
     */
    public Long getIdPersonne1() {
        return idPersonne1;
    }

    /**
     * Getter.
     * @return the nridPersonne2
     */
    public Long getIdPersonne2() {
        return idPersonne2;
    }

}
