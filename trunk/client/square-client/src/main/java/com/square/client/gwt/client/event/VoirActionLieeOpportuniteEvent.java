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
 * Evenement de creation de campagne pour le bus.
 * @author cblanchard - SCUB
 */
public class VoirActionLieeOpportuniteEvent extends GwtEvent<VoirActionLieeOpportuniteEventHandler> {

    /** Type de l'evenement. */
    public static final Type<VoirActionLieeOpportuniteEventHandler> TYPE = new Type<VoirActionLieeOpportuniteEventHandler>();

    /** Identifiant. */
    private Long idOpportunite;

    /** Identifiant de la personne. */
    private Long idPersonne;

    /** Nom de la personne. */
    private String nom;

    /** Prénom de la personne. */
    private String prenom;

    /** Identifiant de la nature de la personne. */
    private Long idNaturePersonne;

    /** Eid de l'opportunité. */
    private String eidOpportunite;

    /**
     * Constructeur.
     * @param idOpportunite l'identifiant de la campagne
     * @param idPersonne identifiant de la personne
     * @param nom nom de la personne
     * @param prenom prénom de la personne
     * @param idNaturePersonne identifiant de la nature de la personne
     * @param eidOpportunite eid de l'opportunité
     */
    public VoirActionLieeOpportuniteEvent(Long idPersonne, String nom, String prenom, Long idNaturePersonne, Long idOpportunite, String eidOpportunite) {
        super();
        this.idOpportunite = idOpportunite;
        this.idPersonne = idPersonne;
        this.prenom = prenom;
        this.nom = nom;
        this.idNaturePersonne = idNaturePersonne;
        this.eidOpportunite = eidOpportunite;
    }

    @Override
    protected void dispatch(VoirActionLieeOpportuniteEventHandler handler) {
        handler.visualiserActions(this);
    }

    @Override
    public Type<VoirActionLieeOpportuniteEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Renvoi la valeur de id.
     * @return id
     */
    public Long getIdOpportunite() {
        return idOpportunite;
    }

    /**
     * Renvoi la valeur de idPersonne.
     * @return idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Renvoi la valeur de nom.
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoi la valeur de prenom.
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Renvoi la valeur de eidOpportunite.
     * @return eidOpportunite
     */
    public String getEidOpportunite() {
        return eidOpportunite;
    }

    /**
     * Récupère la valeur de idNaturePersonne.
     * @return la valeur de idNaturePersonne
     */
    public Long getIdNaturePersonne() {
        return idNaturePersonne;
    }
}
