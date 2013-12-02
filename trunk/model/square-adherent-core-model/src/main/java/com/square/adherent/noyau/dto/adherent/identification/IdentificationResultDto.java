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
package com.square.adherent.noyau.dto.adherent.identification;

import java.io.Serializable;


/**
 * DTO englobant les informations de connexion.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class IdentificationResultDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6300956536485791129L;

    /** Identifiant de l'adhérent. */
    private Long idAdherent;

    /** Booléen indiquant si c'est une première connexion. */
    private Boolean isPremiereConnexion;

    /** Booléen indiquant si l'adhérent est issu du courtage. */
    private Boolean isAdherentCourtage;

    /**
     * Get the isAdherentCourtage value.
     * @return the isAdherentCourtage
     */
    public Boolean getIsAdherentCourtage() {
        return isAdherentCourtage;
    }

    /**
     * Set the isAdherentCourtage value.
     * @param isAdherentCourtage the isAdherentCourtage to set
     */
    public void setIsAdherentCourtage(Boolean isAdherentCourtage) {
        this.isAdherentCourtage = isAdherentCourtage;
    }

    /**
     * Retourne la valeur de isPremiereConnexion.
     * @return la valeur de isPremiereConnexion
     */
    public Boolean getIsPremiereConnexion() {
        return isPremiereConnexion;
    }

    /**
     * Définit la valeur de isPremiereConnexion.
     * @param isPremiereConnexion la nouvelle valeur de isPremiereConnexion
     */
    public void setIsPremiereConnexion(Boolean isPremiereConnexion) {
        this.isPremiereConnexion = isPremiereConnexion;
    }

    /**
     * Récupère la valeur de idAdherent.
     * @return la valeur de idAdherent
     */
    public Long getIdAdherent() {
        return idAdherent;
    }

    /**
     * Définit la valeur de idAdherent.
     * @param idAdherent la nouvelle valeur de idAdherent
     */
    public void setIdAdherent(Long idAdherent) {
        this.idAdherent = idAdherent;
    }

}
