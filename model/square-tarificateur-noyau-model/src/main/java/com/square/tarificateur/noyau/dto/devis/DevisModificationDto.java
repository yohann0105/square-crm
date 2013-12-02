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
 * Classe permettant de modifier un devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class DevisModificationDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -4916851418251315202L;

    /** Identifiant du devis. */
    private Long idDevis;

    /** Identifiant du motif de devis. */
    private Long idMotifDevis;

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

    /**
     * Récupère la valeur de idDevis.
     * @return la valeur de idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }

    /**
     * Définit la valeur de idDevis.
     * @param idDevis la nouvelle valeur de idDevis
     */
    public void setIdDevis(Long idDevis) {
        this.idDevis = idDevis;
    }
}
