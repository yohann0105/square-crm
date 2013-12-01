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
 * Evènement pour lancer la recherche de doublons.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ChercherDoublonEvent extends GwtEvent<ChercherDoublonEventHandler> {

    /** Type de l'évènement. */
    public static final Type<ChercherDoublonEventHandler> TYPE = new Type<ChercherDoublonEventHandler>();

    /** Nom du doublon. */
    private String nom;

    /** Prénom du doublon. */
    private String prenom;

    /** Date de naissance du doublon. */
    private String dateNaissance;

    /**
     * Constructeur.
     * @param nom le nom du doublon.
     * @param prenom le prénom du doublon.
     * @param dateNaissance la date de naissance du doublon.
     */
    public ChercherDoublonEvent(String nom, String prenom, String dateNaissance) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    @Override
    protected void dispatch(ChercherDoublonEventHandler handler) {
        handler.chercherDoublons(this);
    }

    @Override
    public Type<ChercherDoublonEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Récupère la valeur de nom.
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère la valeur de prenom.
     * @return la valeur de prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Récupère la valeur de dateNaissance.
     * @return la valeur de dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }
}
