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
 * Handler sur la fin de la fusion de deux personnes.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class FinFusionPersonnesEvent extends GwtEvent<FinFusionPersonnesEventHandler> {

    /** Type de l'évènement. */
    public static final Type<FinFusionPersonnesEventHandler> TYPE = new Type<FinFusionPersonnesEventHandler>();

    /** Identifiant de la personne source fusionnée. */
    private Long idPersonneSource;

    /** Identifiant de la personne cible fusionnée. */
    private Long idPersonneCible;

    /**
     * Constructeur.
     * @param idPersonneSource l'identifiant de la personne source
     * @param idPersonneCible l'identifiant de la personne cible
     */
    public FinFusionPersonnesEvent(Long idPersonneSource, Long idPersonneCible) {
        super();
        this.idPersonneSource = idPersonneSource;
        this.idPersonneCible = idPersonneCible;
    }

    @Override
    protected void dispatch(FinFusionPersonnesEventHandler handler) {
        handler.onFinFusionPersonnes(this);
    }

    @Override
    public Type<FinFusionPersonnesEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Récupère la valeur de idPersonneSource.
     * @return la valeur de idPersonneSource
     */
    public Long getIdPersonneSource() {
        return idPersonneSource;
    }

	/**
	 * Récupère la valeur idPersonneCible.
	 * @return the idPersonneCible
	 */
	public Long getIdPersonneCible() {
		return idPersonneCible;
	}

	/**
	 * Définit la valeur de idPersonneCible.
	 * @param idPersonneCible the idPersonneCible to set
	 */
	public void setIdPersonneCible(Long idPersonneCible) {
		this.idPersonneCible = idPersonneCible;
	}

}
