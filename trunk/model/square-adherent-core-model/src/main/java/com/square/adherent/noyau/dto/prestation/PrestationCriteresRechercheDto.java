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
package com.square.adherent.noyau.dto.prestation;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Objet contenant les critères de recherche sur les prestations.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class PrestationCriteresRechercheDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -600317715897289444L;

    /** Numéro d'identifiation du bénéficiare. */
    private Long idBeneficiaire;

    /** Numero de l'assure. */
    private Long idAssure;

    /** N Décomptes de type "commence par". */
    private String numero;

    /** Numéro de décompte exact. */
    private String numeroDecompteExact;

    /** Date de debut des soins. */
    private Calendar dateDebutSoins;

    /** Date de fin des soins. */
    private Calendar dateFinSoins;

    /** Identifiant de l'origine. */
    private Long idOrigine;

    /** Identifiant des actes. */
    private List<Long> idActes;

    /** Identifiant des actes à exclure. */
    private List<Long> idActesAExclure;

    /**
     * Recuperer la valeur.
     * @return the idBeneficiaire
     */
    public Long getIdBeneficiaire() {
        return idBeneficiaire;
    }

    /**
     * Fixer la valeur.
     * @param idBeneficiaire the idBeneficiaire to set
     */
    public void setIdBeneficiaire(Long idBeneficiaire) {
        this.idBeneficiaire = idBeneficiaire;
    }

    /**
     * Recuperer la valeur.
     * @return the idAssure
     */
    public Long getIdAssure() {
        return idAssure;
    }

    /**
     * Fixer la valeur.
     * @param idAssure the idAssure to set
     */
    public void setIdAssure(Long idAssure) {
        this.idAssure = idAssure;
    }

    /**
     * Recuperer la valeur.
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Fixer la valeur.
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Récupère la valeur de numeroDecompteExact.
     * @return la valeur de numeroDecompteExact
     */
    public String getNumeroDecompteExact() {
        return numeroDecompteExact;
    }

    /**
     * Définit la valeur de numeroDecompteExact.
     * @param numeroDecompteExact la nouvelle valeur de numeroDecompteExact
     */
    public void setNumeroDecompteExact(String numeroDecompteExact) {
        this.numeroDecompteExact = numeroDecompteExact;
    }

    /**
     * Get the dateDebutSoins value.
     * @return the dateDebutSoins
     */
    public Calendar getDateDebutSoins() {
        return dateDebutSoins;
    }

    /**
     * Set the dateDebutSoins value.
     * @param dateDebutSoins the dateDebutSoins to set
     */
    public void setDateDebutSoins(Calendar dateDebutSoins) {
        this.dateDebutSoins = dateDebutSoins;
    }

    /**
     * Get the dateFinSoins value.
     * @return the dateFinSoins
     */
    public Calendar getDateFinSoins() {
        return dateFinSoins;
    }

    /**
     * Set the dateFinSoins value.
     * @param dateFinSoins the dateFinSoins to set
     */
    public void setDateFinSoins(Calendar dateFinSoins) {
        this.dateFinSoins = dateFinSoins;
    }

    /**
     * Get the idOrigine value.
     * @return the idOrigine
     */
    public Long getIdOrigine() {
        return idOrigine;
    }

    /**
     * Set the idOrigine value.
     * @param idOrigine the idOrigine to set
     */
    public void setIdOrigine(Long idOrigine) {
        this.idOrigine = idOrigine;
    }

    /**
     * Get the idActes value.
     * @return the idActes
     */
    public List<Long> getIdActes() {
        return idActes;
    }

    /**
     * Set the idActes value.
     * @param idActes the idActes to set
     */
    public void setIdActes(List<Long> idActes) {
        this.idActes = idActes;
    }

    /**
     * Get the value of idActesAExclure.
     * @return the idActesAExclure
     */
    public List<Long> getIdActesAExclure() {
        return idActesAExclure;
    }

    /**
     * Set the value of idActesAExclure.
     * @param idActesAExclure the idActesAExclure to set
     */
    public void setIdActesAExclure(List<Long> idActesAExclure) {
        this.idActesAExclure = idActesAExclure;
    }
}
