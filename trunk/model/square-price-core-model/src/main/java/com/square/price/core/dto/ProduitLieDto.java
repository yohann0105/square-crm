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
/**
 * Classe ProduitLieDto.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class ProduitLieDto implements Serializable
{
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 4476079623821898244L;

    /**
     * Affichage devis.
     */
    private Boolean affichageDevis;

    /**
     * Critere obligatoire du produit.
     */
    private Boolean optionnel;

    /**
     * Critere actif du produit.
     */
    private Boolean actif = true;

    /**
     * ProduitLie.
     */
    private ProduitDto produitLie;

    /**
     * Get the affichageDevis value.
     * @return the affichageDevis
     */
    public Boolean getAffichageDevis() {
        return affichageDevis;
    }

    /**
     * Set the affichageDevis value.
     * @param affichageDevis the affichageDevis to set
     */
    public void setAffichageDevis(Boolean affichageDevis) {
        this.affichageDevis = affichageDevis;
    }

    /**
     * Get the produitLie value.
     * @return the produitLie
     */
    public ProduitDto getProduitLie() {
        return produitLie;
    }

    /**
     * Set the produitLie value.
     * @param produitLie the produitLie to set
     */
    public void setProduitLie(ProduitDto produitLie) {
        this.produitLie = produitLie;
    }

    /**
     * Get the optionnel value.
     * @return the optionnel
     */
    public Boolean getOptionnel() {
        return optionnel;
    }

    /**
     * Set the optionnel value.
     * @param optionnel the optionnel to set
     */
    public void setOptionnel(Boolean optionnel) {
        this.optionnel = optionnel;
    }

    /**
     * Get the actif value.
     * @return the actif
     */
    public Boolean getActif() {
        return actif;
    }

    /**
     * Set the actif value.
     * @param actif the actif to set
     */
    public void setActif(Boolean actif) {
        this.actif = actif;
    }
}
