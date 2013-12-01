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
package com.square.client.gwt.client.event.actions;

import com.google.gwt.event.shared.GwtEvent;
import com.square.client.gwt.client.model.ActionModificationModel;

/**
 * Evenement pour l'ajout d'une action.
 * @author cblanchard - SCUB
 */
public class AjoutActionEvent extends GwtEvent<AjoutActionEventHandler> {

    /** Type de l'evenement. */
    public static final Type<AjoutActionEventHandler> TYPE = new Type<AjoutActionEventHandler>();

    /** Id de la personne. */
    private Long idPersonne;

    /** Identifiant de l'action source à laquelle sera liée la nouvelle action. */
    private Long idActionSource;

    /** Identifiant de l'opportunité utilisée pour filtrer les actions. */
    private Long filtreidOpportunite;

    /** Identifiant externe de l'opportunité utilisée pour filtrer les actions. */
    private String filtreEidOpportunite;

    /** L'action source à enregistrer avant l'enregistrement de la relance. */
    private ActionModificationModel actionSourceAEnregistrer;

    /** Indique si l'on affiche dans la popup la possibilité de fermer l'action source. */
    private Boolean demanderFermerSource;

    /**
     * Constructeur.
     * @param idPersonne l'identifiant de la personne.
     */
    public AjoutActionEvent(Long idPersonne) {
        super();
        this.idPersonne = idPersonne;
    }

    /**
     * Constructeur.
     * @param idPersonne identifiant de la personne.
     * @param idActionSource identifiant de l'action source liée.
     * @param filtreidOpportunite Identifiant de l'opportunité utilisée pour filtrer les actions
     * @param filtreEidOpportunite Identifiant externe de l'opportunité utilisée pour filtrer les actions
     * @param demanderFermerSource Indique si l'on affiche dans la popup la possibilité de fermer l'action source
     */
    public AjoutActionEvent(Long idPersonne, Long idActionSource, Long filtreidOpportunite, String filtreEidOpportunite, Boolean demanderFermerSource) {
        this.idPersonne = idPersonne;
        this.idActionSource = idActionSource;
        this.filtreidOpportunite = filtreidOpportunite;
        this.filtreEidOpportunite = filtreEidOpportunite;
        this.demanderFermerSource = demanderFermerSource;
    }

    /**
     * Constructeur pour passer l'action source de la relance.
     * @param idPersonne identifiant de la personne.
     * @param idActionSource identifiant de l'action source liée.
     * @param filtreidOpportunite Identifiant de l'opportunité utilisée pour filtrer les actions
     * @param filtreEidOpportunite Identifiant externe de l'opportunité utilisée pour filtrer les actions
     * @param actionSourcePourRelance actionSourcePourRelance
     * @param demanderFermerSource Indique si l'on affiche dans la popup la possibilité de fermer l'action source
     */
    public AjoutActionEvent(Long idPersonne, Long idActionSource, Long filtreidOpportunite, String filtreEidOpportunite,
        ActionModificationModel actionSourceAEnregistrer, Boolean demanderFermerSource) {
        this(idPersonne, idActionSource, filtreidOpportunite, filtreEidOpportunite, demanderFermerSource);
        this.actionSourceAEnregistrer = actionSourceAEnregistrer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(AjoutActionEventHandler handler) {
        handler.ajouterAction(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type<AjoutActionEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Renvoi la valeur de idPersonne.
     * @return idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Récupération de idActionSource.
     * @return the idActionSource
     */
    public Long getIdActionSource() {
        return idActionSource;
    }

    /**
     * Récupération de filtreidOpportunite.
     * @return the filtreidOpportunite
     */
    public Long getFiltreidOpportunite() {
        return filtreidOpportunite;
    }

    /**
     * Récupération de filtreEidOpportunite.
     * @return the filtreEidOpportunite
     */
    public String getFiltreEidOpportunite() {
        return filtreEidOpportunite;
    }

    /**
     * Récupération de actionSourcePourRelance.
     * @return the actionSourcePourRelance
     */
    public ActionModificationModel getActionSourceAEnregistrer() {
        return actionSourceAEnregistrer;
    }

    /**
     * Récupération de demanderFermerSource.
     * @return the demanderFermerSource
     */
    public Boolean getDemanderFermerSource() {
        return demanderFermerSource;
    }

}
