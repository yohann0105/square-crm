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
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO encapsulant les informations principales d'une garantie.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class GarantieSimpleDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 7940146614188985302L;

    /** Identifiant de la garantie. */
    private Long id;

    /** Identifiant du contrat à laquelle la garantie est associée. */
    private Long idContrat;

    /** Identifiant du produit. */
    private Integer idProduit;

    /** Libellé de la garantie. */
    private String libelle;

    /** Identifiant de la gamme. */
    //TODO cette notion n'existe pas dans
    private Long idGamme;

    private String idFormulePresta;

    private String libelleFormulePresta;

    /** Informations des bénéficiaires de la garantie. */
    private List<InfosGarantieBeneficiaireDto> listeInfosGarantiesBeneficiaires;

    /** Segment. */
    private IdentifiantLibelleDto segment;

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
     * @return the listeInfosGarantiesBeneficiaires
     */
    public List<InfosGarantieBeneficiaireDto> getListeInfosGarantiesBeneficiaires() {
        if (listeInfosGarantiesBeneficiaires == null) {
            listeInfosGarantiesBeneficiaires = new ArrayList<InfosGarantieBeneficiaireDto>();
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
     * @param listeInfosGarantiesBeneficiaires the listeInfosGarantiesBeneficiaires to set
     */
    public void setListeInfosGarantiesBeneficiaires(List<InfosGarantieBeneficiaireDto> listeInfosGarantiesBeneficiaires) {
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
     * Récupère la valeur de segment.
     * @return la valeur de segment
     */
    public IdentifiantLibelleDto getSegment() {
        return segment;
    }

    /**
     * Définit la valeur de segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(IdentifiantLibelleDto segment) {
        this.segment = segment;
    }

    /**
     * Getter function.
     * @return the idContrat
     */
    public Long getIdContrat() {
        return idContrat;
    }

    /**
     * Setter function.
     * @param idContrat the idContrat to set
     */
    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
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
