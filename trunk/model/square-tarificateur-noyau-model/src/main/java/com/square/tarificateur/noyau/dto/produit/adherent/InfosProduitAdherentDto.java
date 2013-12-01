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
package com.square.tarificateur.noyau.dto.produit.adherent;

import java.io.Serializable;

/**
 * DTO pour les infos d'un produit adherent.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfosProduitAdherentDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6132496476696064936L;

    /** Identifiant. */
    private Long identifiant;

    /** Libellé. */
    private String libelle;

    /** Contrat produit. */
    private String produitAia;

    /** Contrat Garantie. */
    private String garantieAia;

    /** Date de début du contrat associé au produit. */
    private String dateAdhesion;

    /** Date de fin du contrat associé au produit. */
    private String dateResiliation;

    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Get the libelle value.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the libelle value.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Get the produitAia value.
     * @return the produitAia
     */
    public String getProduitAia() {
        return produitAia;
    }

    /**
     * Set the produitAia value.
     * @param produitAia the produitAia to set
     */
    public void setProduitAia(String produitAia) {
        this.produitAia = produitAia;
    }

    /**
     * Get the garantieAia value.
     * @return the garantieAia
     */
    public String getGarantieAia() {
        return garantieAia;
    }

    /**
     * Set the garantieAia value.
     * @param garantieAia the garantieAia to set
     */
    public void setGarantieAia(String garantieAia) {
        this.garantieAia = garantieAia;
    }

    /**
     * Get the dateAdhesion value.
     * @return the dateAdhesion
     */
    public String getDateAdhesion() {
        return dateAdhesion;
    }

    /**
     * Get the dateResiliation value.
     * @return the dateResiliation
     */
    public String getDateResiliation() {
        return dateResiliation;
    }

    /**
     * Set the dateAdhesion value.
     * @param dateAdhesion the dateAdhesion to set
     */
    public void setDateAdhesion(String dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    /**
     * Set the dateResiliation value.
     * @param dateResiliation the dateResiliation to set
     */
    public void setDateResiliation(String dateResiliation) {
        this.dateResiliation = dateResiliation;
    }

}
