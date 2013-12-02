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
 * Critères de recherche pour les contrats.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class CritereRechercheContratDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -4324391553043093138L;

    /** L'identifiant de l'assuré du contrat. */
    private Long idAssure;

    /** Le numero de contrat. */
    private String numeroContrat;

    /** Booléen indiquant si on ne veut récupérer que les contrats en cours. */
    private Boolean hasContratEnCours;

    /** Identifiant souscripteur. */
    private Long idSouscripteur;

    /** idAssure doit etre null. */
    private boolean idAssureNull;

    /** Eid du contrat. */
    private String contratEid;

    /**
     * Récupère la valeur de idAssure.
     * @return la valeur de idAssure
     */
    public Long getIdAssure() {
        return idAssure;
    }

    /**
     * Définit la valeur de idAssure.
     * @param idAssure la nouvelle valeur de idAssure
     */
    public void setIdAssure(Long idAssure) {
        this.idAssure = idAssure;
    }

    /**
     * Récupère la valeur de hasContratEnCours.
     * @return la valeur de hasContratEnCours
     */
    public Boolean getHasContratEnCours() {
        return hasContratEnCours;
    }

    /**
     * Définit la valeur de hasContratEnCours.
     * @param hasContratEnCours la nouvelle valeur de hasContratEnCours
     */
    public void setHasContratEnCours(Boolean hasContratEnCours) {
        this.hasContratEnCours = hasContratEnCours;
    }

    /**
     * Get the numeroContrat value.
     * @return the numeroContrat
     */
    public String getNumeroContrat() {
        return numeroContrat;
    }

    /**
     * Set the numeroContrat value.
     * @param numeroContrat the numeroContrat to set
     */
    public void setNumeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
    }

    /**
     * Récupération de idSouscripteur.
     * @return the idSouscripteur
     */
    public Long getIdSouscripteur() {
        return idSouscripteur;
    }

    /**
     * Définition de idSouscripteur.
     * @param idSouscripteur the idSouscripteur to set
     */
    public void setIdSouscripteur(Long idSouscripteur) {
        this.idSouscripteur = idSouscripteur;
    }

    /**
     * Récupération de idAssureNull.
     * @return the idAssureNull
     */
    public boolean isIdAssureNull() {
        return idAssureNull;
    }

    /**
     * Définition de idAssureNull.
     * @param idAssureNull the idAssureNull to set
     */
    public void setIdAssureNull(boolean idAssureNull) {
        this.idAssureNull = idAssureNull;
    }

    /**
     * Get the contratEid value.
     * @return the contratEid
     */
    public String getContratEid() {
        return contratEid;
    }

    /**
     * Set the contratEid value.
     * @param contratEid the contratEid to set
     */
    public void setContratEid(String contratEid) {
        this.contratEid = contratEid;
    }
}
