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
package com.square.composant.contrat.personne.morale.square.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant des informations d'une garantie d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class GarantiePersonneMoraleModel implements IsSerializable {

    /** Identifiant de la garantie. */
    private Long id;

    /** Libellé de la garantie. */
    private String libelle;

    /** Identifiant du produit associé. */
    private Integer idProduit;

    /** Listes des lignes d'information de la garantie. */
    private List<InfosGarantiePersonneMoraleModel> listeInfosGarantie;

    /** Indique si la garantie possède une grille de prestation. */
    private Boolean possedeGrillePresta;

    /**
     * Récupère la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la valeur de libelle.
     * @return la valeur de libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit la valeur de libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
     * Récupère la valeur de listeInfosGarantie.
     * @return la valeur de listeInfosGarantie
     */
    public List<InfosGarantiePersonneMoraleModel> getListeInfosGarantie() {
        if (listeInfosGarantie == null) {
            listeInfosGarantie = new ArrayList<InfosGarantiePersonneMoraleModel>();
        }
        return listeInfosGarantie;
    }

    /**
     * Définit la valeur de listeInfosGarantie.
     * @param listeInfosGarantie la nouvelle valeur de listeInfosGarantie
     */
    public void setListeInfosGarantie(List<InfosGarantiePersonneMoraleModel> listeInfosGarantie) {
        this.listeInfosGarantie = listeInfosGarantie;
    }

    /**
     * Récupère la valeur de possedeGrillePresta.
     * @return la valeur de possedeGrillePresta
     */
    public Boolean getPossedeGrillePresta() {
        return possedeGrillePresta;
    }

    /**
     * Définit la valeur de possedeGrillePresta.
     * @param possedeGrillePresta la nouvelle valeur de possedeGrillePresta
     */
    public void setPossedeGrillePresta(Boolean possedeGrillePresta) {
        this.possedeGrillePresta = possedeGrillePresta;
    }
}
