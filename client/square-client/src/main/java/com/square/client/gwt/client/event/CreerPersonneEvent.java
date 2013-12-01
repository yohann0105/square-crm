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
import com.square.client.gwt.client.model.PersonneBaseModel;

/**
 * Evènement pour créer une personne.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class CreerPersonneEvent extends GwtEvent<CreerPersonneEventHandler> {

    /** Type de l'évènement. */
    public static final Type<CreerPersonneEventHandler> TYPE = new Type<CreerPersonneEventHandler>();

    /** La personne à créer. */
    private PersonneBaseModel personne;

    /** Indique si la personne est un bénéficiaire ou non. */
    private boolean isBeneficiaire;

    /**
     * Constructeur.
     * @param personne la personne à fusioner.
     * @param isBeneficiaire indique si la personne est un bénéficiaire ou non.
     */
    public CreerPersonneEvent(PersonneBaseModel personne, boolean isBeneficiaire) {
        this.personne = personne;
        this.isBeneficiaire = isBeneficiaire;
    }

    @Override
    protected void dispatch(CreerPersonneEventHandler handler) {
        handler.creerPersonne(this);
    }

    @Override
    public Type<CreerPersonneEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Récupère la valeur de personne.
     * @return la valeur de personne
     */
    public PersonneBaseModel getPersonne() {
        return personne;
    }

    /**
     * Récupère la valeur de isBeneficiaire.
     * @return la valeur de isBeneficiaire
     */
    public boolean isBeneficiaire() {
        return isBeneficiaire;
    }
}
