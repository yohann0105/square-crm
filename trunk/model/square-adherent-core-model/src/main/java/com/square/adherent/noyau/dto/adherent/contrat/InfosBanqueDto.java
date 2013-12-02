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
 * Dto regroupant les informations de banque.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfosBanqueDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 6622195445567993509L;

    /** Domiciliation de la banque. */
    private String domiciliation;

    /** Composant du RIB : code de la banque. */
    private String codeBanque;

    /** Composant du RIB : code du guichet. */
    private String codeGuichet;

    /** Composant du RIB : code du compte. */
    private String codeCompte;

    /** Composant du RIB : clé. */
    private String cle;

    /**
     * Récupère la valeur de codeBanque.
     * @return la valeur de codeBanque
     */
    public String getCodeBanque() {
        return codeBanque;
    }

    /**
     * Définit la valeur de codeBanque.
     * @param codeBanque la nouvelle valeur de codeBanque
     */
    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    /**
     * Récupère la valeur de codeGuichet.
     * @return la valeur de codeGuichet
     */
    public String getCodeGuichet() {
        return codeGuichet;
    }

    /**
     * Définit la valeur de codeGuichet.
     * @param codeGuichet la nouvelle valeur de codeGuichet
     */
    public void setCodeGuichet(String codeGuichet) {
        this.codeGuichet = codeGuichet;
    }

    /**
     * Récupère la valeur de codeCompte.
     * @return la valeur de codeCompte
     */
    public String getCodeCompte() {
        return codeCompte;
    }

    /**
     * Définit la valeur de codeCompte.
     * @param codeCompte la nouvelle valeur de codeCompte
     */
    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
    }

    /**
     * Récupère la valeur de cle.
     * @return la valeur de cle
     */
    public String getCle() {
        return cle;
    }

    /**
     * Définit la valeur de cle.
     * @param cle la nouvelle valeur de cle
     */
    public void setCle(String cle) {
        this.cle = cle;
    }

    /**
     * Get the domiciliation value.
     * @return the domiciliation
     */
    public String getDomiciliation() {
        return domiciliation;
    }

    /**
     * Set the domiciliation value.
     * @param domiciliation the domiciliation to set
     */
    public void setDomiciliation(String domiciliation) {
        this.domiciliation = domiciliation;
    }
}
