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
package com.square.composant.tarificateur.square.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPaiementModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPersonneSyncModel;

/**
 * Evenement sur le succes d'envoi de la mise a jour des informations d'adhésion.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class SuccesMajInfosAdhesionEvent extends GwtEvent<SuccesMajInfosAdhesionEventHandler> {

    /** Type de l'evenement. */
    public static final Type<SuccesMajInfosAdhesionEventHandler> TYPE = new Type<SuccesMajInfosAdhesionEventHandler>();

    private List<InfosPersonneSyncModel> listePersonnes;

    private InfosPaiementModel infosPaiement;

    /** Indique si il faut lancer la transformation du devis vers Aia. */
    private boolean transformerDevisAia;

    /** Indique si il faut lancer créer une action de suivi après la transformation. */
    private boolean creerActionSuivi;

    /** L'id du devis de l'évènement. */
    private Long idDevis;

    /**
     * Constructeur.
     * @param listePersonnes Liste des personnes mises à jour.
     * @param infosPaiement les informations de paiement mises à jour.
     * @param transformerDevisAia Indique si il faut lancer la transformation du devis vers Aia.
     * @param idDevis l'id du devis.
     */
    public SuccesMajInfosAdhesionEvent(List<InfosPersonneSyncModel> listePersonnes, InfosPaiementModel infosPaiement,
    		boolean transformerDevisAia, Long idDevis) {
        super();
        this.listePersonnes = listePersonnes;
        this.infosPaiement = infosPaiement;
        this.transformerDevisAia = transformerDevisAia;
        this.idDevis = idDevis;
    }

    /**
     * Constructeur.
     * @param listePersonnes Liste des personnes mises à jour.
     * @param infosPaiement les informations de paiement mises à jour.
     * @param transformerDevisAia Indique si il faut lancer la transformation du devis vers Aia.
     * @param creerActionSuivi Indique si il faut lancer créer une action de suivi après la transformation.
     * @param idDevis l'id du devis.
     */
    public SuccesMajInfosAdhesionEvent(List<InfosPersonneSyncModel> listePersonnes, InfosPaiementModel infosPaiement,
    		boolean transformerDevisAia, Long idDevis, boolean creerActionSuivi) {
        this(listePersonnes, infosPaiement, transformerDevisAia, idDevis);
        this.creerActionSuivi = creerActionSuivi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(SuccesMajInfosAdhesionEventHandler handler) {
        handler.onSuccess(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type<SuccesMajInfosAdhesionEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Getter function.
     * @return the listePersonnes
     */
    public List<InfosPersonneSyncModel> getListePersonnes() {
        return listePersonnes;
    }

    /**
     * Getter function.
     * @return the transformerDevisAia
     */
    public boolean isTransformerDevisAia() {
        return transformerDevisAia;
    }

    /**
     * Getter function.
     * @return the idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }

    /**
     * Getter function.
     * @return the infosPaiement
     */
    public InfosPaiementModel getInfosPaiement() {
        return infosPaiement;
    }

	/**
	 * Renvoie la valeur de creerActionSuivi.
	 * @return creerActionSuivi
	 */
	public boolean isCreerActionSuivi() {
		return creerActionSuivi;
	}

}
