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
package com.square.adherent.noyau.dto.banque;

import java.io.Serializable;

/**
 * DTO pour les critères de recherche de banques.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class CritereRechercheBanqueDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -2730730225527951687L;

    /** Code de la banque. */
    private String codeBanque;

    /** Code du guichet. */
    private String codeGuichet;

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
}
