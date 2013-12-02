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
package com.square.synchro.app.noyau.dto;

import java.io.Serializable;

/**
 * DTO représentant les informations d'un RIB.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfosRibDto implements Serializable {

	/** Identificateur de sérialisation. */
	private static final long serialVersionUID = -9061450464742570977L;

    private String codeBanque;

    private String codeGuichet;

    private String codeCompte;

    private String cle;

    /**
     * Get the codeBanque value.
     * @return the codeBanque
     */
    public String getCodeBanque() {
        return codeBanque;
    }

    /**
     * Set the codeBanque value.
     * @param codeBanque the codeBanque to set
     */
    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    /**
     * Get the codeGuichet value.
     * @return the codeGuichet
     */
    public String getCodeGuichet() {
        return codeGuichet;
    }

    /**
     * Set the codeGuichet value.
     * @param codeGuichet the codeGuichet to set
     */
    public void setCodeGuichet(String codeGuichet) {
        this.codeGuichet = codeGuichet;
    }

    /**
     * Get the codeCompte value.
     * @return the codeCompte
     */
    public String getCodeCompte() {
        return codeCompte;
    }

    /**
     * Set the codeCompte value.
     * @param codeCompte the codeCompte to set
     */
    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
    }

    /**
     * Get the cle value.
     * @return the cle
     */
    public String getCle() {
        return cle;
    }

    /**
     * Set the cle value.
     * @param cle the cle to set
     */
    public void setCle(String cle) {
        this.cle = cle;
    }

}
