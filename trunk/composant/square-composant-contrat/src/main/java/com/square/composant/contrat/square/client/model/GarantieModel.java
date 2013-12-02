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
package com.square.composant.contrat.square.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant les informations d'une garantie.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class GarantieModel implements IsSerializable {

    /**
     * Identifiant du produit.
     */
    private Integer idProduit;

    /**
     * Libellé de la garantie.
     */
    private String libelle;

    /**
     * Code du tarif.
     */
    private String codeTarif;

    /**
     * Code de la génération.
     */
    private String codeGeneration;

    /**
     * Libellé de gestion de la garantie.
     */
    private String libelleGarantieGestion;

    /**
     * Libellé de gestion du produit.
     */
    private String libelleProduitGestion;

    /** Le statut de la garantie. */
    private IdentifiantLibelleGwt statut;

    /** Montant souscrit. */
    private Double montantSouscrit;

    /** Loi Madelin. */
    private Boolean loiMadelin = false;


    /**
     * Getter function.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Getter function.
     * @return the codeTarif
     */
    public String getCodeTarif() {
        return codeTarif;
    }

    /**
     * Getter function.
     * @return the codeGeneration
     */
    public String getCodeGeneration() {
        return codeGeneration;
    }

    /**
     * Getter function.
     * @return the libelleGarantieGestion
     */
    public String getLibelleGarantieGestion() {
        return libelleGarantieGestion;
    }

    /**
     * Getter function.
     * @return the libelleProduitGestion
     */
    public String getLibelleProduitGestion() {
        return libelleProduitGestion;
    }

    /**
     * Setter function.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Setter function.
     * @param codeTarif the codeTarif to set
     */
    public void setCodeTarif(String codeTarif) {
        this.codeTarif = codeTarif;
    }

    /**
     * Setter function.
     * @param codeGeneration the codeGeneration to set
     */
    public void setCodeGeneration(String codeGeneration) {
        this.codeGeneration = codeGeneration;
    }

    /**
     * Setter function.
     * @param libelleGarantieGestion the libelleGarantieGestion to set
     */
    public void setLibelleGarantieGestion(String libelleGarantieGestion) {
        this.libelleGarantieGestion = libelleGarantieGestion;
    }

    /**
     * Setter function.
     * @param libelleProduitGestion the libelleProduitGestion to set
     */
    public void setLibelleProduitGestion(String libelleProduitGestion) {
        this.libelleProduitGestion = libelleProduitGestion;
    }

    /**
     * Récupère la valeur de idProduit.
     * @return la valeur de idProduit
     */
    public Integer getIdProduit() {
        return idProduit;
    }

    /**
     * Définit la valeur de idProduit.
     * @param idProduit la nouvelle valeur de idProduit
     */
    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    /**
     * Récupère la valeur de statut.
     * @return la valeur de statut
     */
    public IdentifiantLibelleGwt getStatut() {
        return statut;
    }

    /**
     * Définit la valeur de statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleGwt statut) {
        this.statut = statut;
    }

    /**
     * Récupère la valeur de montantSouscrit.
     * @return la valeur de montantSouscrit
     */
    public Double getMontantSouscrit() {
        return montantSouscrit;
    }

    /**
     * Définit la valeur de montantSouscrit.
     * @param montantSouscrit la nouvelle valeur de montantSouscrit
     */
    public void setMontantSouscrit(Double montantSouscrit) {
        this.montantSouscrit = montantSouscrit;
    }

    /**
     * Récupère la valeur de loiMadelin.
     * @return la valeur de loiMadelin
     */
    public Boolean getLoiMadelin() {
        return loiMadelin;
    }

    /**
     * Définit la valeur de loiMadelin.
     * @param loiMadelin la nouvelle valeur de loiMadelin
     */
    public void setLoiMadelin(Boolean loiMadelin) {
        this.loiMadelin = loiMadelin;
    }
}
