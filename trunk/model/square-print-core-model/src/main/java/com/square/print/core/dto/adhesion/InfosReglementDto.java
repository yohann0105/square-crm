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
package com.square.print.core.dto.adhesion;

import java.io.Serializable;

/**
 * DTO représentant les informations de règlement.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class InfosReglementDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 3968943770111131715L;

    /** Périodicité Mensuelle. */
    public static final String PERIODICITE_MENSUELLE = "PERIODICITE_MENSUELLE";

    /** Périodicité Trimestrielle. */
    public static final String PERIODICITE_TRIMESTRIELLE = "PERIODICITE_TRIMESTRIELLE";

    /** Périodicité Semestrielle. */
    public static final String PERIODICITE_SEMESTRIELLE = "PERIODICITE_SEMESTRIELLE";

    /** Périodicité Annuelle. */
    public static final String PERIODICITE_ANNUELLE = "PERIODICITE_ANNUELLE";

    /** Mode de règlement "Prélèvement automatique". */
    public static final String MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE = "MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE";

    /** Mode de règlement "Prélèvement automatique 5 du mois". */
    public static final String MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_5_DU_MOIS = "MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_5_DU_MOIS";

    /** Mode de règlement "Prélèvement automatique 10 du mois". */
    public static final String MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_10_DU_MOIS = "MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_10_DU_MOIS";

    /** Mode de règlement "Prélèvement automatique 15 du mois". */
    public static final String MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_15_DU_MOIS = "MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_15_DU_MOIS";

    /** Mode de règlement "Chèque". */
    public static final String MODE_REGLEMENT_CHEQUE = "MODE_REGLEMENT_CHEQUE";

    /** Périodicité. */
    private String periodicite;

    /** Mode de règlement. */
    private String modeReglement;

    /**
     * Constructeur.
     */
    public InfosReglementDto() {
    }

    /**
     * Constructeur avec paramètres.
     * @param periodicite la périodicité
     * @param modeReglement le mode de règlement
     */
    public InfosReglementDto(String periodicite, String modeReglement) {
        this.periodicite = periodicite;
        this.modeReglement = modeReglement;
    }

    /**
     * Récupère la valeur de periodicite.
     * @return la valeur de periodicite
     */
    public String getPeriodicite() {
        return periodicite;
    }

    /**
     * Définit la valeur de periodicite.
     * @param periodicite la nouvelle valeur de periodicite
     */
    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    /**
     * Récupère la valeur de modeReglement.
     * @return la valeur de modeReglement
     */
    public String getModeReglement() {
        return modeReglement;
    }

    /**
     * Définit la valeur de modeReglement.
     * @param modeReglement la nouvelle valeur de modeReglement
     */
    public void setModeReglement(String modeReglement) {
        this.modeReglement = modeReglement;
    }

}
