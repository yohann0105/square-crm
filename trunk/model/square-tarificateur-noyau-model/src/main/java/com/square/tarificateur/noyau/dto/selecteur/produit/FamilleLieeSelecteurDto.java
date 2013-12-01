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
package com.square.tarificateur.noyau.dto.selecteur.produit;

import java.io.Serializable;
import java.util.List;

/**
 * Dto representant une famille liée de produits pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class FamilleLieeSelecteurDto extends FamilleSelecteurDto implements Serializable, Comparable<FamilleLieeSelecteurDto> {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -3134325873207197228L;

    /** Liste des produits de la famille liée. */
    private List<ProduitSelecteurDto> listeProduitsLies;

    /**
     * Get the listeProduitsLies value.
     * @return the listeProduitsLies
     */
    public List<ProduitSelecteurDto> getListeProduitsLies() {
        return listeProduitsLies;
    }

    /**
     * Set the listeProduitsLies value.
     * @param listeProduitsLies the listeProduitsLies to set
     */
    public void setListeProduitsLies(List<ProduitSelecteurDto> listeProduitsLies) {
        this.listeProduitsLies = listeProduitsLies;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(FamilleLieeSelecteurDto obj) {
        if (this.getOrdreGamme() == null && obj.getOrdreGamme() == null) {
            return 0;
        } else if (this.getOrdreGamme() != null && obj.getOrdreGamme() == null) {
            return 1;
        } else if (this.getOrdreGamme() == null && obj.getOrdreGamme() != null) { return -1; }

        final int compareToGamme = this.getOrdreGamme().compareTo(obj.getOrdreGamme());
        if (compareToGamme == 0) {
            if (this.getOrdreFamille() == null && obj.getOrdreFamille() == null) {
                return 0;
            } else if (this.getOrdreFamille() != null && obj.getOrdreFamille() == null) {
                return 1;
            } else if (this.getOrdreFamille() == null && obj.getOrdreFamille() != null) { return -1; }
            return this.getOrdreFamille().compareTo(obj.getOrdreFamille());
        }
        return compareToGamme;
    }

}
