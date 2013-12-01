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
package com.square.composant.tarificateur.square.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model pour les infos des produits adherent.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ListeProduitsAdherentModel implements IsSerializable {

    /** Identifiant. */
    private InfosProduitAdherentModel produitPrincipal;

    private List<InfosProduitAdherentModel> listeProduitsLies;

    /**
     * Get the produitPrincipal value.
     * @return the produitPrincipal
     */
    public InfosProduitAdherentModel getProduitPrincipal() {
        return produitPrincipal;
    }

    /**
     * Set the produitPrincipal value.
     * @param produitPrincipal the produitPrincipal to set
     */
    public void setProduitPrincipal(InfosProduitAdherentModel produitPrincipal) {
        this.produitPrincipal = produitPrincipal;
    }

    /**
     * Get the listeProduitsLies value.
     * @return the listeProduitsLies
     */
    public List<InfosProduitAdherentModel> getListeProduitsLies() {
        if (listeProduitsLies == null) {
            listeProduitsLies = new ArrayList<InfosProduitAdherentModel>();
        }
        return listeProduitsLies;
    }

    /**
     * Set the listeProduitsLies value.
     * @param listeProduitsLies the listeProduitsLies to set
     */
    public void setListeProduitsLies(List<InfosProduitAdherentModel> listeProduitsLies) {
        this.listeProduitsLies = listeProduitsLies;
    }

}
