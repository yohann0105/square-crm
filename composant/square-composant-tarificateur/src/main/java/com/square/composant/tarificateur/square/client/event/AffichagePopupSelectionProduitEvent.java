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

/**
 * Evenement pour l'affichage de la popup de choix de produit.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class AffichagePopupSelectionProduitEvent extends GwtEvent<AffichagePopupSelectionProduitEventHandler> {

    /** Type de l'evenement. */
    public static final Type<AffichagePopupSelectionProduitEventHandler> TYPE = new Type<AffichagePopupSelectionProduitEventHandler>();

    /** L'id du devis dans lequel ajouter le produit. Si null, nouveau devis. */
    private Long idDevis;

    /** L'id du produit. */
    private Integer idProduit;

    /** Le type du devis. */
    private String typeDevis;

    /** L'id de la ligne de devis a charger dans le selecteur. Si null, nouvelle ligne. */
    private Long idLigneDevis;

    /**
     * Constructeur.
     * @param idProduit idProduit
     * @param idDevis idDevis
     * @param typeDevis typeDevis
     * @param idLigneDevis idLigneDevis
     */
    public AffichagePopupSelectionProduitEvent(Integer idProduit, Long idDevis, String typeDevis, Long idLigneDevis) {
        super();
        this.idProduit = idProduit;
        this.idDevis = idDevis;
        this.typeDevis = typeDevis;
        this.idLigneDevis = idLigneDevis;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(AffichagePopupSelectionProduitEventHandler handler) {
        handler.afficherPopup(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type<AffichagePopupSelectionProduitEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Get the idDevis value.
     * @return the idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }

    /**
     * Get the typeDevis value.
     * @return the typeDevis
     */
    public String getTypeDevis() {
        return typeDevis;
    }

    /**
     * Get the idProduit value.
     * @return the idProduit
     */
    public Integer getIdProduit() {
        return idProduit;
    }

    /**
     * Get the idLigneDevis value.
     * @return the idLigneDevis
     */
    public Long getIdLigneDevis() {
        return idLigneDevis;
    }
}
