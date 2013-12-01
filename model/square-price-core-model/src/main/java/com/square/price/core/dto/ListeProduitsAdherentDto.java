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
package com.square.price.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto d'une liste de produits d'adhérent.
 * @author Nicolas Peltier (nicolas.peltier@scub.net)
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public class ListeProduitsAdherentDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -1094149143020685999L;

    /** Uid de l'adhérent. */
    private Long uidAdherent;

    /** Produit principal. */
    private ProduitAdherentDto produitPrincipal;

    /** Liste des produits liés. */
    private List < ProduitAdherentDto > listeProduitsLies;

    /**
     * Get the produitPrincipal value.
     * @return the produitPrincipal
     */
    public ProduitAdherentDto getProduitPrincipal() {
        return produitPrincipal;
    }

    /**
     * Set the produitPrincipal value.
     * @param produitPrincipal the produitPrincipal to set
     */
    public void setProduitPrincipal(ProduitAdherentDto produitPrincipal) {
        this.produitPrincipal = produitPrincipal;
    }

    /**
     * Get the listeProduitsLies value.
     * @return the listeProduitsLies
     */
    public List < ProduitAdherentDto > getListeProduitsLies() {
        if (listeProduitsLies == null) {
            listeProduitsLies = new ArrayList < ProduitAdherentDto > ();
        }
        return listeProduitsLies;
    }

    /**
     * Set the listeProduitsLies value.
     * @param listeProduitsLies the listeProduitsLies to set
     */
    public void setListeProduitsLies(List < ProduitAdherentDto > listeProduitsLies) {
        this.listeProduitsLies = listeProduitsLies;
    }

    /**
     * Get the value of uidAdherent.
     * @return the uidAdherent
     */
    public Long getUidAdherent() {
        return uidAdherent;
    }

    /**
     * Set the value of uidAdherent.
     * @param uidAdherent the uidAdherent to set
     */
    public void setUidAdherent(Long uidAdherent) {
        this.uidAdherent = uidAdherent;
    }

}
