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
 * Evenement de creation de personne pour le bus.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class CreatePersonEvent extends GwtEvent<CreatePersonEventHandler> {

    /** Type de l'evenement. */
    public static final Type<CreatePersonEventHandler> TYPE = new Type<CreatePersonEventHandler>();

    /** Identifiant. */
    private Long id;

    /** Nom. */
    private String nom;

    /** Prenom. */
    private String prenom;

    /** Identifiant de la nature. */
    private Long idNature;

    /**
     * Constructeur.
     * @param id l'identifiant
     * @param nom le nom
     * @param prenom le prenom
     * @param idNature l'identifiant de la nature de la personne.
     */
    public CreatePersonEvent(Long id, String nom, String prenom, Long idNature) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.idNature = idNature;
    }

    @Override
    public Type<CreatePersonEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CreatePersonEventHandler handler) {
        handler.createPerson(this);
    }

    /**
     * Get the id value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the nom value.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Get the prenom value.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Récupère la valeur de idNature.
     * @return la valeur de idNature
     */
    public Long getIdNature() {
        return idNature;
    }
}
