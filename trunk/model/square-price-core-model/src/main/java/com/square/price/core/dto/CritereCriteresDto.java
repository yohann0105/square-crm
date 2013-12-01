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
import java.util.Calendar;
import java.util.List;


/**
 * Dto pour la recherche de critere.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class CritereCriteresDto implements Serializable {

    private static final long serialVersionUID = -3609733649599201190L;

    private Integer identifiantProduit;

    private Boolean obligatoire;

    private Boolean chargerValeursPossibles;

    private Calendar dateEffet;

    private Integer identifiantCritere;

    private Integer identifiantApplication;

    private String contrat;

    private List<String> listeIdsBareme;

    private Integer idProduitPrincipal;

    private boolean filtreCourtier = true;

    /**
     * Identifiant du courtier si récupération de critères par courtier<br>
     * TODO Lors migration Square : A remplir par le client. Si non rempli, recherche hors courtier
     */
    private Long idCourtier;

    /**
     * Get the chargerValeursPossibles value.
     * @return the chargerValeursPossibles
     */
    public Boolean getChargerValeursPossibles() {
        return chargerValeursPossibles;
    }

    /**
     * Set the chargerValeursPossibles value.
     * @param chargerValeursPossibles the chargerValeursPossibles to set
     */
    public void setChargerValeursPossibles(Boolean chargerValeursPossibles) {
        this.chargerValeursPossibles = chargerValeursPossibles;
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
     * Get the obligatoire value.
     * @return the obligatoire
     */
    public Boolean getObligatoire() {
        return obligatoire;
    }

    /**
     * Set the obligatoire value.
     * @param obligatoire the obligatoire to set
     */
    public void setObligatoire(Boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    /**
     * Get the dateEffet value.
     * @return the dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Set the dateEffet value.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Get the identifiantCritere value.
     * @return the identifiantCritere
     */
    public Integer getIdentifiantCritere() {
        return identifiantCritere;
    }

    /**
     * Set the identifiantCritere value.
     * @param identifiantCritere the identifiantCritere to set
     */
    public void setIdentifiantCritere(Integer identifiantCritere) {
        this.identifiantCritere = identifiantCritere;
    }

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
     * Get the contrat value.
     * @return the contrat
     */
    public String getContrat() {
        return contrat;
    }

    /**
     * Set the contrat value.
     * @param contrat the contrat to set
     */
    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    /**
     * Récupère la valeur de idProduitPrincipal.
     * @return la valeur de idProduitPrincipal
     */
    public Integer getIdProduitPrincipal() {
        return idProduitPrincipal;
    }

    /**
     * Définit la valeur de idProduitPrincipal.
     * @param idProduitPrincipal la nouvelle valeur de idProduitPrincipal
     */
    public void setIdProduitPrincipal(Integer idProduitPrincipal) {
        this.idProduitPrincipal = idProduitPrincipal;
    }

    /**
     * Get the filtreCourtier value.
     * @return the filtreCourtier
     */
    public boolean isFiltreCourtier() {
        return filtreCourtier;
    }

    /**
     * Set the filtreCourtier value.
     * @param filtreCourtier the filtreCourtier to set
     */
    public void setFiltreCourtier(boolean filtreCourtier) {
        this.filtreCourtier = filtreCourtier;
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
     * Get the value of listeIdsBareme.
     * @return the listeIdsBareme
     */
    public List<String> getListeIdsBareme() {
        return listeIdsBareme;
    }

    /**
     * Set the value of listeIdsBareme.
     * @param listeIdsBareme the listeIdsBareme to set
     */
    public void setListeIdsBareme(List<String> listeIdsBareme) {
        this.listeIdsBareme = listeIdsBareme;
    }
}
