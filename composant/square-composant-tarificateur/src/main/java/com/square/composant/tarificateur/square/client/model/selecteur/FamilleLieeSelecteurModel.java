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

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.tarificateur.square.client.composant.SelecteurProduitEventMapper;

/**
 * Model representant une famille liée de produits pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class FamilleLieeSelecteurModel extends FamilleSelecteurModel implements IsSerializable, SelecteurProduitEventMapper<Boolean> {

    /**
     * Liste des produits de la famille liée.
     */
    private List<ProduitSelecteurModel> listeProduitsLies;

    /**
     * {@inheritDoc}
     */
    public Boolean getValeurMapper() {
        return getIsSelection();
    }

    /**
     * {@inheritDoc}
     */
    public void setValeurMapper(Boolean valeurMapper) {
        setIsSelection(valeurMapper);
    }

    /**
     * Get the listeProduitsLies value.
     * @return the listeProduitsLies
     */
    public List<ProduitSelecteurModel> getListeProduitsLies() {
        return listeProduitsLies;
    }

    /**
     * Set the listeProduitsLies value.
     * @param listeProduitsLies the listeProduitsLies to set
     */
    public void setListeProduitsLies(List<ProduitSelecteurModel> listeProduitsLies) {
        this.listeProduitsLies = listeProduitsLies;
    }

}
