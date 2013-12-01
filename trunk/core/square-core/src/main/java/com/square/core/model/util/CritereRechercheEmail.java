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
package com.square.core.model.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Bean pour les critères de recherche d'emails.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class CritereRechercheEmail {

    /** Liste des identifiants des personnes associées au téléphone. */
    private Set<Long> idsPersonnes;

    /** Identifiant de la nature de l'email. */
    private Long idNature;

    /** Email. */
    private String email;

    /** Indique si on recherche les emails supprimés ou non. */
    private Boolean isSupprime = false;

    /** Identifiants des emails à exclure de la recherche. */
    private Set<Long> idsEmailsExclus;

    /**
     * Getter function.
     * @return the idsPersonnes
     */
    public Set<Long> getIdsPersonnes() {
        return idsPersonnes;
    }

    /**
     * Setter function.
     * @param idsPersonnes the idsPersonnes to set
     */
    public void setIdsPersonnes(Set<Long> idsPersonnes) {
        this.idsPersonnes = idsPersonnes;
    }

    /**
     * Récupère la valeur de idNature.
     * @return la valeur de idNature
     */
    public Long getIdNature() {
        return idNature;
    }

    /**
     * Définit la valeur de idNature.
     * @param idNature la nouvelle valeur de idNature
     */
    public void setIdNature(Long idNature) {
        this.idNature = idNature;
    }

    /**
     * Récupère la valeur de email.
     * @return la valeur de email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit la valeur de email.
     * @param email la nouvelle valeur de email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Récupère la valeur de isSupprime.
     * @return la valeur de isSupprime
     */
    public Boolean getIsSupprime() {
        return isSupprime;
    }

    /**
     * Définit la valeur de isSupprime.
     * @param isSupprime la nouvelle valeur de isSupprime
     */
    public void setIsSupprime(Boolean isSupprime) {
        this.isSupprime = isSupprime;
    }

    /**
     * Getter function.
     * @return the idsEmailsExclus
     */
    public Set<Long> getIdsEmailsExclus() {
        if (this.idsEmailsExclus == null) {
            this.idsEmailsExclus = new HashSet<Long>();
        }
        return idsEmailsExclus;
    }

    /**
     * Setter function.
     * @param idsEmailsExclus the idsEmailsExclus to set
     */
    public void setIdsEmailsExclus(Set<Long> idsEmailsExclus) {
        this.idsEmailsExclus = idsEmailsExclus;
    }
}
