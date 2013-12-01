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
package com.square.adherent.noyau.dto.produit;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Dto pour les critères d'informations des produits.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class CriteresInfosProduitsDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 4839719615368824680L;

    private Long id;

    private Calendar dateDeFinDeGarantie;

    private boolean filtreNullSurDateDeFin = true;

    private boolean horsProduitsBonus = false;

    private Calendar dateDebutDeGarantie;

    private Long idContrat;

    private Long idPersonne;

    private Long idAssure;

    private Long idFamille;

    private String numeroClient;

    /**
     * Get the id value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id value.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the dateDeFinDeGarantie value.
     * @return the dateDeFinDeGarantie
     */
    public Calendar getDateDeFinDeGarantie() {
        return dateDeFinDeGarantie;
    }

    /**
     * Set the dateDeFinDeGarantie value.
     * @param dateDeFinDeGarantie the dateDeFinDeGarantie to set
     */
    public void setDateDeFinDeGarantie(Calendar dateDeFinDeGarantie) {
        this.dateDeFinDeGarantie = dateDeFinDeGarantie;
    }

    /**
     * Get the filtreNullSurDateDeFin value.
     * @return the filtreNullSurDateDeFin
     */
    public boolean isFiltreNullSurDateDeFin() {
        return filtreNullSurDateDeFin;
    }

    /**
     * Set the filtreNullSurDateDeFin value.
     * @param filtreNullSurDateDeFin the filtreNullSurDateDeFin to set
     */
    public void setFiltreNullSurDateDeFin(boolean filtreNullSurDateDeFin) {
        this.filtreNullSurDateDeFin = filtreNullSurDateDeFin;
    }

    /**
     * Get the dateDebutDeGarantie value.
     * @return the dateDebutDeGarantie
     */
    public Calendar getDateDebutDeGarantie() {
        return dateDebutDeGarantie;
    }

    /**
     * Set the dateDebutDeGarantie value.
     * @param dateDebutDeGarantie the dateDebutDeGarantie to set
     */
    public void setDateDebutDeGarantie(Calendar dateDebutDeGarantie) {
        this.dateDebutDeGarantie = dateDebutDeGarantie;
    }

    /**
     * Get the horsProduitsBonus value.
     * @return the horsProduitsBonus
     */
    public boolean isHorsProduitsBonus() {
        return horsProduitsBonus;
    }

    /**
     * Set the horsProduitsBonus value.
     * @param horsProduitsBonus the horsProduitsBonus to set
     */
    public void setHorsProduitsBonus(boolean horsProduitsBonus) {
        this.horsProduitsBonus = horsProduitsBonus;
    }

    /**
     * Récupère la valeur de idContrat.
     * @return la valeur de idContrat
     */
    public Long getIdContrat() {
        return idContrat;
    }

    /**
     * Définit la valeur de idContrat.
     * @param idContrat la nouvelle valeur de idContrat
     */
    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
    }

    /**
     * Récupère la valeur de idPersonne.
     * @return la valeur de idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Définit la valeur de idPersonne.
     * @param idPersonne la nouvelle valeur de idPersonne
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Récupère la valeur de idAssure.
     * @return la valeur de idAssure
     */
    public Long getIdAssure() {
        return idAssure;
    }

    /**
     * Définit la valeur de idAssure.
     * @param idAssure la nouvelle valeur de idAssure
     */
    public void setIdAssure(Long idAssure) {
        this.idAssure = idAssure;
    }

    /**
     * Get the value of idFamille.
     * @return the idFamille
     */
    public Long getIdFamille() {
        return idFamille;
    }

    /**
     * Set the value of idFamille.
     * @param idFamille the idFamille to set
     */
    public void setIdFamille(Long idFamille) {
        this.idFamille = idFamille;
    }

    /**
     * Get the value of numeroClient.
     * @return the numeroClient
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * Set the value of numeroClient.
     * @param numeroClient the numeroClient to set
     */
    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }
}
