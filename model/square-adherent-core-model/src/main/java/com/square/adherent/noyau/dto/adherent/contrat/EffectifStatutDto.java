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
 * Dto permettant d'associer un effectif à un statut.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class EffectifStatutDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 3240269767092376599L;

    /** L'identifiant du statut. */
    private Long idStatut;

    /** Effectif. */
    private Integer effectif;

    /**
     * Récupère la valeur de idStatut.
     * @return la valeur de idStatut
     */
    public Long getIdStatut() {
        return idStatut;
    }

    /**
     * Définit la valeur de idStatut.
     * @param idStatut la nouvelle valeur de idStatut
     */
    public void setIdStatut(Long idStatut) {
        this.idStatut = idStatut;
    }

    /**
     * Récupère la valeur de effectif.
     * @return la valeur de effectif
     */
    public Integer getEffectif() {
        return effectif;
    }

    /**
     * Définit la valeur de effectif.
     * @param effectif la nouvelle valeur de effectif
     */
    public void setEffectif(Integer effectif) {
        this.effectif = effectif;
    }
}
