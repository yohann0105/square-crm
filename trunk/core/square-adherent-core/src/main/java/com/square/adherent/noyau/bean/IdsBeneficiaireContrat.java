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
package com.square.adherent.noyau.bean;

/**
 * Classe permettant d'associer un identifiant de bénéficiaire à un identifiant de contrat.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class IdsBeneficiaireContrat {

    /** Identifiant du bénéficiaire. */
    private Long idBenef;

    /** Identifiant du contrat. */
    private Long idContrat;

    /**
     * Récupère la valeur de idBenef.
     * @return la valeur de idBenef
     */
    public Long getIdBenef() {
        return idBenef;
    }

    /**
     * Définit la valeur de idBenef.
     * @param idBenef la nouvelle valeur de idBenef
     */
    public void setIdBenef(Long idBenef) {
        this.idBenef = idBenef;
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
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof IdsBeneficiaireContrat)) {
            return false;
        }
        else {
            final IdsBeneficiaireContrat obj = (IdsBeneficiaireContrat) other;
            return this.idBenef.equals(obj.getIdBenef()) && this.idContrat.equals(obj.getIdContrat());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
