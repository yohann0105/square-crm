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
 * Critére pour la recherche des proudits lies d'un produit.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class ProduitLieCriteresDto implements Serializable {

    private static final long serialVersionUID = -5061230114141991590L;

    private Integer identifiantProduit;

    private Integer identifiantProduitLie;

    private Boolean optionnel;

    private Integer identifiantApplication;

    private Boolean possedeTarif;

    private Boolean seulementActif = true;

    private Boolean ouvertALaVentePourCourtier;

    /**
     * Identifiant du courtier si récupération des produits liés d'un courtier<br>
     * TODO Lors migration Square : A remplir par le client. Si non rempli, recherche hors courtier
     */
    private Long idCourtier;

    private String idPayeurType;

    private String idTarifType;

    /**
     * Get the identifiantApplication value.
     * @return the identifiantApplication
     */
    public Integer getIdentifiantApplication() {
        return identifiantApplication;
    }

    /**
     * Set the identifiantApplication value.
     * @param identifiantApplication the identifiantApplication to set
     */
    public void setIdentifiantApplication(Integer identifiantApplication) {
        this.identifiantApplication = identifiantApplication;
    }

    /**
     * Get the identifiantProduit value.
     * @return the identifiantProduit
     */
    public Integer getIdentifiantProduit() {
        return identifiantProduit;
    }

    /**
     * Set the identifiantProduit value.
     * @param identifiantProduit the identifiantProduit to set
     */
    public void setIdentifiantProduit(Integer identifiantProduit) {
        this.identifiantProduit = identifiantProduit;
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
     * Get the possedeTarif value.
     * @return the possedeTarif
     */
    public Boolean getPossedeTarif() {
        return possedeTarif;
    }

    /**
     * Set the possedeTarif value.
     * @param possedeTarif the possedeTarif to set
     */
    public void setPossedeTarif(Boolean possedeTarif) {
        this.possedeTarif = possedeTarif;
    }

    /**
     * Get the identifiantProduitLie value.
     * @return the identifiantProduitLie
     */
    public Integer getIdentifiantProduitLie() {
        return identifiantProduitLie;
    }

    /**
     * Set the identifiantProduitLie value.
     * @param identifiantProduitLie the identifiantProduitLie to set
     */
    public void setIdentifiantProduitLie(Integer identifiantProduitLie) {
        this.identifiantProduitLie = identifiantProduitLie;
    }

    /**
     * Get the seulementActif value.
     * @return the seulementActif
     */
    public Boolean getSeulementActif() {
        return seulementActif;
    }

    /**
     * Set the seulementActif value.
     * @param seulementActif the seulementActif to set
     */
    public void setSeulementActif(Boolean seulementActif) {
        this.seulementActif = seulementActif;
    }

    /**
     * Récupère la valeur de ouvertALaVentePourCourtier.
     * @return la valeur de ouvertALaVentePourCourtier
     */
    public Boolean getOuvertALaVentePourCourtier() {
        return ouvertALaVentePourCourtier;
    }

    /**
     * Définit la valeur de ouvertALaVentePourCourtier.
     * @param ouvertALaVentePourCourtier la nouvelle valeur de ouvertALaVentePourCourtier
     */
    public void setOuvertALaVentePourCourtier(Boolean ouvertALaVentePourCourtier) {
        this.ouvertALaVentePourCourtier = ouvertALaVentePourCourtier;
    }

    /**
     * Récupère la valeur de idCourtier.
     * @return the idCourtier
     */
    public Long getIdCourtier() {
        return idCourtier;
    }

    /**
     * Définit la valeur de idCourtier.
     * @param idCourtier the idCourtier to set
     */
    public void setIdCourtier(Long idCourtier) {
        this.idCourtier = idCourtier;
    }

    /**
     * Get the value of idPayeurType.
     * @return the idPayeurType
     */
    public String getIdPayeurType() {
        return idPayeurType;
    }

    /**
     * Set the value of idPayeurType.
     * @param idPayeurType the idPayeurType to set
     */
    public void setIdPayeurType(String idPayeurType) {
        this.idPayeurType = idPayeurType;
    }

    /**
     * Get the value of idTarifType.
     * @return the idTarifType
     */
    public String getIdTarifType() {
        return idTarifType;
    }

    /**
     * Set the value of idTarifType.
     * @param idTarifType the idTarifType to set
     */
    public void setIdTarifType(String idTarifType) {
        this.idTarifType = idTarifType;
    }
}
