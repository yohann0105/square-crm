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
package com.square.composant.tarificateur.square.client.model.selecteur;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model representant une famille de produit principal pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class FamillePrincipaleSelecteurModel extends FamilleSelecteurModel implements IsSerializable {

    /**
     * Produit de la famille principale.
     */
    private ProduitSelecteurModel produitPrincipal;

    /**
     * Get the produitPrincipal value.
     * @return the produitPrincipal
     */
    public ProduitSelecteurModel getProduitPrincipal() {
        return produitPrincipal;
    }

    /**
     * Set the produitPrincipal value.
     * @param produitPrincipal the produitPrincipal to set
     */
    public void setProduitPrincipal(ProduitSelecteurModel produitPrincipal) {
        this.produitPrincipal = produitPrincipal;
    }

}
