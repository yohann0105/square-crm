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

import com.google.gwt.event.shared.GwtEvent;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;

/**
 * Evenement pour l'affichage de la popup d'adhésion.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class AffichagePopupAdhesionEvent extends GwtEvent<AffichagePopupAdhesionEventHandler> {

    /** Type de l'evenement. */
    public static final Type<AffichagePopupAdhesionEventHandler> TYPE = new Type<AffichagePopupAdhesionEventHandler>();

    /** Identifiant du devis selectionné. */
    private Long idDevis;

    /** Identifiant du idDepartement de la personne. */
    private Long idDepartement;

    /** Indique si il faut lancer la transformation du devis vers Aia. */
    private boolean transformerDevisAia;

    /** Indique si les informations d'adhésion sont éditables ou non. */
    private boolean isInfoAdhesionEditable;

    /** Rapport d'erreurs pour les infos d'adhésion. */
    private RapportModel rapportErreurs;

    /**
     * Constructeur.
     * @param idDevis Identifiant du devis.
     * @param idDepartement idDepartement.
     * @param transformerDevisAia Indique si il faut lancer la transformation du devis vers Aia.
     * @param isInfoAdhesionEditable indique si les informations d'adhésion sont éditables ou non.
     */
    public AffichagePopupAdhesionEvent(Long idDevis, Long idDepartement, boolean transformerDevisAia, boolean isInfoAdhesionEditable) {
        super();
        this.idDevis = idDevis;
        this.idDepartement = idDepartement;
        this.transformerDevisAia = transformerDevisAia;
        this.isInfoAdhesionEditable = isInfoAdhesionEditable;
    }

    /**
     * Constructeur.
     * @param idDevis Identifiant du devis.
     * @param idCodePostalCommune idCodePostalCommune.
     * @param transformerDevisAia Indique si il faut lancer la transformation du devis vers Aia.
     * @param isInfoAdhesionEditable indique si les informations d'adhésion sont éditables ou non.
     * @param rapportErreurs le rapport d'erreurs pour les infos d'adhésion
     */
    public AffichagePopupAdhesionEvent(Long idDevis, Long idCodePostalCommune, boolean transformerDevisAia, boolean isInfoAdhesionEditable,
        RapportModel rapportErreurs) {
        this(idDevis, idCodePostalCommune, transformerDevisAia, isInfoAdhesionEditable);
        this.rapportErreurs = rapportErreurs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(AffichagePopupAdhesionEventHandler handler) {
        handler.afficherPopup(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type<AffichagePopupAdhesionEventHandler> getAssociatedType() {
        return TYPE;
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
     * @return the transformerDevisAia
     */
    public boolean isTransformerDevisAia() {
        return transformerDevisAia;
    }

    /**
     * Récupère la valeur de isInfoAdhesionEditable.
     * @return la valeur de isInfoAdhesionEditable
     */
    public boolean isInfoAdhesionEditable() {
        return isInfoAdhesionEditable;
    }

    /**
     * Récupère la valeur de rapportErreurs.
     * @return la valeur de rapportErreurs
     */
    public RapportModel getRapportErreurs() {
        return rapportErreurs;
    }

    /**
     * Définit la valeur de rapportErreurs.
     * @param rapportErreurs la nouvelle valeur de rapportErreurs
     */
    public void setRapportErreurs(RapportModel rapportErreurs) {
        this.rapportErreurs = rapportErreurs;
    }

    /**
     * Get the idDepartement value.
     * @return the idDepartement
     */
    public Long getIdDepartement() {
        return idDepartement;
    }

}
