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
package com.square.adherent.noyau.dto.adherent;

import java.io.Serializable;

/**
 * DTO regroupant l'identifiant d'une personne et son numéro de client.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class InfosIdentifiantPersonneDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 8745012070514336783L;

    /** L'identifiant de la personne. */
    private Long idPersonne;

    /** Le numéro de client de la personne. */
    private String numClient;

    /**
     * Récupère la valeur de idPersonne.
     * @return la valeur de idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Définit la valeur de idPersonne.
     * @param idPersonne la nouvelle valeur de idPersonne
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Récupère la valeur de numClient.
     * @return la valeur de numClient
     */
    public String getNumClient() {
        return numClient;
    }

    /**
     * Définit la valeur de numClient.
     * @param numClient la nouvelle valeur de numClient
     */
    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }
}
