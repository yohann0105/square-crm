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
/**
 * 
 */
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Query pour la cloture d'un devis.
 * @author Goumard stephane (stephane.goumard@scub.net) - SCUB
 */
public class ClotureDevisQueryDto implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3993344570943759833L;

    /**
     * Identifiant devis.
     */
    private Long idDevis;

    /** Identifiant du motif de devis (sert pour la synchro tarificateur Gestcom ==> Square). */
    private Long idMotifDevis;

    /**
     * Liste des query pour les lignes de devis.
     */
    private List<ClotureDevisQueryLigneDto> lignesQuery;

    /**
     * Constructeur.
     * @param idDevis .
     */
    public ClotureDevisQueryDto(final Long idDevis) {
        super();
        this.idDevis = idDevis;
    }

    /**
     * Constructeur.
     * @param idDevis .
     * @param lignesQuery .
     */
    public ClotureDevisQueryDto(Long idDevis, List<ClotureDevisQueryLigneDto> lignesQuery) {
        super();
        this.idDevis = idDevis;
        this.lignesQuery = lignesQuery;
    }

    /**
     * Get the idDevis value.
     * @return the idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }

    /**
     * Set the idDevis value.
     * @param idDevis the idDevis to set
     */
    public void setIdDevis(Long idDevis) {
        this.idDevis = idDevis;
    }

    /**
     * Get the lignesQuery value.
     * @return the lignesQuery
     */
    public List<ClotureDevisQueryLigneDto> getLignesQuery() {
        if (lignesQuery == null) {
            lignesQuery = new ArrayList<ClotureDevisQueryLigneDto>();
        }
        return lignesQuery;
    }

    /**
     * Set the lignesQuery value.
     * @param lignesQuery the lignesQuery to set
     */
    public void setLignesQuery(List<ClotureDevisQueryLigneDto> lignesQuery) {
        this.lignesQuery = lignesQuery;
    }

    /**
     * Récupère la valeur de idMotifDevis.
     * @return la valeur de idMotifDevis
     */
    public Long getIdMotifDevis() {
        return idMotifDevis;
    }

    /**
     * Définit la valeur de idMotifDevis.
     * @param idMotifDevis la nouvelle valeur de idMotifDevis
     */
    public void setIdMotifDevis(Long idMotifDevis) {
        this.idMotifDevis = idMotifDevis;
    }
}
