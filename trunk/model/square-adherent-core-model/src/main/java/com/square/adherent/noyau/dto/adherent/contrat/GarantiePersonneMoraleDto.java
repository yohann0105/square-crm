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
package com.square.adherent.noyau.dto.adherent.contrat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO encapsulant des informations d'une garantie d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class GarantiePersonneMoraleDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -300707459478069474L;

    /** Identifiant de la garantie. */
    private Long id;

    /** Libellé de la garantie. */
    private String libelle;

    /** Identifiant du produit associé. */
    private Integer idProduit;

    /** Listes des lignes d'information de la garantie. */
    private List<InfosGarantiePersonneMoraleDto> listeInfosGarantie;

    private String idFormulePresta;

    private String libelleFormulePresta;

    /** Statut de la garantie. */
    private IdentifiantLibelleDto statut;

    /** Identifiant de la nature du produit de la garantie. */
    private Integer idNatureProduit;

    /** Date de signature de la garantie. */
    private Calendar dateSignature;

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
    public List<InfosGarantiePersonneMoraleDto> getListeInfosGarantie() {
        if (listeInfosGarantie == null) {
            listeInfosGarantie = new ArrayList<InfosGarantiePersonneMoraleDto>();
        }
        return listeInfosGarantie;
    }

    /**
     * Définit la valeur de listeInfosGarantie.
     * @param listeInfosGarantie la nouvelle valeur de listeInfosGarantie
     */
    public void setListeInfosGarantie(List<InfosGarantiePersonneMoraleDto> listeInfosGarantie) {
        this.listeInfosGarantie = listeInfosGarantie;
    }

    /**
     * Récupère la valeur de statut.
     * @return la valeur de statut
     */
    public IdentifiantLibelleDto getStatut() {
        return statut;
    }

    /**
     * Définit la valeur de statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleDto statut) {
        this.statut = statut;
    }

    /**
     * Récupère la valeur de dateSignature.
     * @return la valeur de dateSignature
     */
    public Calendar getDateSignature() {
        return dateSignature;
    }

    /**
     * Définit la valeur de dateSignature.
     * @param dateSignature la nouvelle valeur de dateSignature
     */
    public void setDateSignature(Calendar dateSignature) {
        this.dateSignature = dateSignature;
    }

    /**
     * Récupère la valeur de idNatureProduit.
     * @return la valeur de idNatureProduit
     */
    public Integer getIdNatureProduit() {
        return idNatureProduit;
    }

    /**
     * Définit la valeur de idNatureProduit.
     * @param idNatureProduit la nouvelle valeur de idNatureProduit
     */
    public void setIdNatureProduit(Integer idNatureProduit) {
        this.idNatureProduit = idNatureProduit;
    }

    /**
     * Get the value of idFormulePresta.
     * @return the idFormulePresta
     */
    public String getIdFormulePresta() {
        return idFormulePresta;
    }

    /**
     * Set the value of idFormulePresta.
     * @param idFormulePresta the idFormulePresta to set
     */
    public void setIdFormulePresta(String idFormulePresta) {
        this.idFormulePresta = idFormulePresta;
    }

    /**
     * Get the value of libelleFormulePresta.
     * @return the libelleFormulePresta
     */
    public String getLibelleFormulePresta() {
        return libelleFormulePresta;
    }

    /**
     * Set the value of libelleFormulePresta.
     * @param libelleFormulePresta the libelleFormulePresta to set
     */
    public void setLibelleFormulePresta(String libelleFormulePresta) {
        this.libelleFormulePresta = libelleFormulePresta;
    }

}
