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
 * Demande de fusion.
 */
public class FusionnerPersonnesEvent extends GwtEvent<FusionnerPersonnesEventHandler> {

    /** Type de l'evenement. */
    public static final Type<FusionnerPersonnesEventHandler> TYPE = new Type<FusionnerPersonnesEventHandler>();
    private String nom;
    private String prenom;
    private String dateNaissance;
    private Long idPersonneSelectionnee;

    /**
     * Constructeur.
     * @param nom .
     * @param prenom .
     * @param dateNaissance .
     * @param idPersonneSelectionnee .
     */
    public FusionnerPersonnesEvent(String nom, String prenom, String dateNaissance, Long idPersonneSelectionnee) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.idPersonneSelectionnee = idPersonneSelectionnee;
    }

    @Override
    protected void dispatch(FusionnerPersonnesEventHandler handler) {
        handler.demanderFusion(this);
    }

    @Override
    public Type<FusionnerPersonnesEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Récupération de nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupération de prenom.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Récupération de dateNaissance.
     * @return the dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Récupération de idPersonneSelectionnee.
     * @return the idPersonneSelectionnee
     */
    public Long getIdPersonneSelectionnee() {
        return idPersonneSelectionnee;
    }

}
