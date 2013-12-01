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
package com.square.tarificateur.noyau.dto.personne;

import java.io.Serializable;

/**
 * DTO modélisant un bénéficiaire du tarificateur.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class BeneficiaireTarificateurDto extends PersonneTarificateurDto implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -2685936048605202935L;

    /** Lien familial. */
    private Long idLienFamilial;

    /** Faux si la relation a pris fin. */
    private Boolean actif;

    /**
     * Getter function.
     * @return the idLienFamilial
     */
    public Long getIdLienFamilial() {
        return idLienFamilial;
    }

    /**
     * Setter function.
     * @param idLienFamilial the idLienFamilial to set
     */
    public void setIdLienFamilial(Long idLienFamilial) {
        this.idLienFamilial = idLienFamilial;
    }

    /**
     * Getter function.
	 * @return the actif
	 */
	public Boolean getActif() {
		return actif;
	}

	/**
     * Setter function.
	 * @param actif the actif to set
	 */
	public void setActif(Boolean actif) {
		this.actif = actif;
	}
}
