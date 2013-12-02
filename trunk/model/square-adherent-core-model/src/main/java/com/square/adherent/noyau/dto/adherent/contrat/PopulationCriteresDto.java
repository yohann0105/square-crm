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

/**
 * DTO des critères de recherche de populations d'un contrat d'une entreprise.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 *
 */
public class PopulationCriteresDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -1225534681633549715L;

    /** Contrat. */
    private String contrat;

    /**
     * Retourne la valeur de contrat.
     * @return la valeur de contrat
     */
    public String getContrat() {
        return contrat;
    }

    /**
     * Définit la valeur de contrat.
     * @param contrat la nouvelle valeur de contrat
     */
    public void setContrat(String contrat) {
        this.contrat = contrat;
    }
}
