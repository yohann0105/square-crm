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

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evènement envoyé pour rafraichir une relation.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class RafraichirRelationEvent extends GwtEvent<RafraichirRelationEventHandler> {

    /** Type de l'evenement. */
    public static final Type<RafraichirRelationEventHandler> TYPE = new Type<RafraichirRelationEventHandler>();

    /** identifiant limitation pour les groupement. */
    private List<Long> filtreGroupements;

    /** identifiant limitation pour les groupement. */
    private List<Long> filtrePasDansGroupements;

    /** Identifiant du mode d'edition voulus apres rafraichissement. */
    private int modeEdition;

    /**
     * Constructeur.
     * @param filtreGroupements filtre groupement
     * @param filtrePasDansGroupements filtre pas dans groupement
     * @param modeEdition mode d'édition.
     */
    public RafraichirRelationEvent(List<Long> filtreGroupements, List<Long> filtrePasDansGroupements, int modeEdition) {
        this.filtreGroupements = filtreGroupements;
        this.filtrePasDansGroupements = filtrePasDansGroupements;
        this.modeEdition = modeEdition;

    }

    @Override
    protected void dispatch(RafraichirRelationEventHandler handler) {
        handler.rafraichirRelation(this);

    }

    @Override
    public Type<RafraichirRelationEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Retourne la valeur de filtreGroupements.
     * @return the filtreGroupements
     */
    public List<Long> getFiltreGroupements() {
        return filtreGroupements;
    }

    /**
     * Retourne la valeur de filtrePasDansGroupements.
     * @return the filtrePasDansGroupements
     */
    public List<Long> getFiltrePasDansGroupements() {
        return filtrePasDansGroupements;
    }

    /**
     * Recuperer la valeur.
     * @return the modeEdition
     */
    public int getModeEdition() {
        return modeEdition;
    }
}
