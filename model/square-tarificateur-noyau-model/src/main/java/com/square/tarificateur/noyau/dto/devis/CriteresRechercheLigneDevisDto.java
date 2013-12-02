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
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;

/**
 * Critères de recherche de lignes de devis.
 * @author jgoncalves - SCUB
 */
public class CriteresRechercheLigneDevisDto implements Serializable {
    private static final long serialVersionUID = 8560291768205283425L;
    private Long idDevis;
    private Long idSource;
    private Boolean selectionnePourAdhesion;
    private Long idFinalite;
    private Long idBeneficiaire;

    /**
     * Récupération de idDevis.
     * @return the idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }
    /**
     * Définition de idDevis.
     * @param idDevis the idDevis to set
     */
    public void setIdDevis(Long idDevis) {
        this.idDevis = idDevis;
    }
    /**
     * Récupération de idSource.
     * @return the idSource
     */
    public Long getIdSource() {
        return idSource;
    }
    /**
     * Définition de idSource.
     * @param idSource the idSource to set
     */
    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }
    /**
     * Récupération de selectionnePourAdhesion.
     * @return the selectionnePourAdhesion
     */
    public Boolean getSelectionnePourAdhesion() {
        return selectionnePourAdhesion;
    }
    /**
     * Définition de selectionnePourAdhesion.
     * @param selectionnePourAdhesion the selectionnePourAdhesion to set
     */
    public void setSelectionnePourAdhesion(Boolean selectionnePourAdhesion) {
        this.selectionnePourAdhesion = selectionnePourAdhesion;
    }
    /**
     * Récupère la valeur de idFinalite.
     * @return la valeur de idFinalite
     */
    public Long getIdFinalite() {
        return idFinalite;
    }
    /**
     * Définit la valeur de idFinalite.
     * @param idFinalite la nouvelle valeur de idFinalite
     */
    public void setIdFinalite(Long idFinalite) {
        this.idFinalite = idFinalite;
    }

    /**
     * Récupération de l'id de beneficiaire.
     * @return the idBeneficiaire
     */
    public Long getIdBeneficiaire() {
        return idBeneficiaire;
    }

    /**
     * Définition de l'id de beneficiaire.
     * @param idBeneficiaire the idBeneficiaire to set
     */
    public void setIdBeneficiaire(Long idBeneficiaire) {
        this.idBeneficiaire = idBeneficiaire;
    }
}
