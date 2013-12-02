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

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant les informations principales d'une garantie.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class GarantieSimpleModel implements IsSerializable {

    /** Identifiant du produit. */
    private Integer idProduit;

    /**
     * Libellé de la garantie.
     */
    private String libelle;

    /**
     * Identifiant de la gamme.
     */
    private Long idGamme;

    /**
     * Indique si la garantie possède une grille de prestation associée ou non.
     */
    private boolean possedeGrillePresta;

    /**
     * Informations des bénéficiaire des la garantie.
     */
    private List<InfosGarantieBeneficiaireModel> listeInfosGarantiesBeneficiaires;

    /** Segment. */
    private IdentifiantLibelleGwt segment;

    /**
     * Getter function.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Getter function.
     * @return the idGamme
     */
    public Long getIdGamme() {
        return idGamme;
    }

    /**
     * Getter function.
     * @return the possedeGrillePresta
     */
    public boolean isPossedeGrillePresta() {
        return possedeGrillePresta;
    }

    /**
     * Getter function.
     * @return the listeInfosGarantiesBeneficiaires
     */
    public List<InfosGarantieBeneficiaireModel> getListeInfosGarantiesBeneficiaires() {
        if (listeInfosGarantiesBeneficiaires == null) {
            listeInfosGarantiesBeneficiaires = new ArrayList<InfosGarantieBeneficiaireModel>();
        }
        return listeInfosGarantiesBeneficiaires;
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
     * @param idGamme the idGamme to set
     */
    public void setIdGamme(Long idGamme) {
        this.idGamme = idGamme;
    }

    /**
     * Setter function.
     * @param possedeGrillePresta the possedeGrillePresta to set
     */
    public void setPossedeGrillePresta(boolean possedeGrillePresta) {
        this.possedeGrillePresta = possedeGrillePresta;
    }

    /**
     * Setter function.
     * @param listeInfosGarantiesBeneficiaires the listeInfosGarantiesBeneficiaires to set
     */
    public void setListeInfosGarantiesBeneficiaires(List<InfosGarantieBeneficiaireModel> listeInfosGarantiesBeneficiaires) {
        this.listeInfosGarantiesBeneficiaires = listeInfosGarantiesBeneficiaires;
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
     * Récupère la valeur de segment.
     * @return la valeur de segment
     */
    public IdentifiantLibelleGwt getSegment() {
        return segment;
    }

    /**
     * Définit la valeur de segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(IdentifiantLibelleGwt segment) {
        this.segment = segment;
    }

}
