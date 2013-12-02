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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * DTO représentant les critères de recherche d'opportunités.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class CriteresRechercheOpportuniteDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 6298072828649333836L;

    /** Identifiant de la finalité des opportunités. */
    private Long idFinalite;

    /** Début de l'intervalle pour la date de cloture de l'opportunité. */
    private Calendar debutDateCloture;

    /** Fin de l'intervalle pour la date de cloture de l'opportunité. */
    private Calendar finDateCloture;

    /** Booléen pour filtrer sur les opportunités de type santé. */
    private Boolean isOppSante;

    /** Liste des eids des opportunités. */
    private List<Long> listeEids;

    /** Liste des eids des natures de personnes des opportunités à exclure. */
    private List<Long> listeEidsNaturePersonneExclure;

    /**
     * Getter.
     * @return the idFinalite
     */
    public Long getIdFinalite() {
        return idFinalite;
    }

    /**
     * Setter.
     * @param idFinalite the idFinalite to set
     */
    public void setIdFinalite(Long idFinalite) {
        this.idFinalite = idFinalite;
    }

    /**
     * Getter.
     * @return the debutDateCloture
     */
    public Calendar getDebutDateCloture() {
        return debutDateCloture;
    }

    /**
     * Setter.
     * @param debutDateCloture the debutDateCloture to set
     */
    public void setDebutDateCloture(Calendar debutDateCloture) {
        this.debutDateCloture = debutDateCloture;
    }

    /**
     * Getter.
     * @return the finDateCloture
     */
    public Calendar getFinDateCloture() {
        return finDateCloture;
    }

    /**
     * Setter.
     * @param finDateCloture the finDateCloture to set
     */
    public void setFinDateCloture(Calendar finDateCloture) {
        this.finDateCloture = finDateCloture;
    }

    /**
     * Récupère la valeur de isOppSante.
     * @return la valeur de isOppSante
     */
    public Boolean getIsOppSante() {
        return isOppSante;
    }

    /**
     * Définit la valeur de isOppSante.
     * @param isOppSante la nouvelle valeur de isOppSante
     */
    public void setIsOppSante(Boolean isOppSante) {
        this.isOppSante = isOppSante;
    }

    /**
     * Récupère la valeur de listeEids.
     * @return la valeur de listeEids
     */
    public List<Long> getListeEids() {
        if (listeEids == null) {
            listeEids = new ArrayList<Long>();
        }
        return listeEids;
    }

    /**
     * Définit la valeur de listeEids.
     * @param listeEids la nouvelle valeur de listeEids
     */
    public void setListeEids(List<Long> listeEids) {
        this.listeEids = listeEids;
    }

    /**
     * Récupère la valeur de listeEidsNaturePersonneExclure.
     * @return la valeur de listeEidsNaturePersonneExclure
     */
    public List<Long> getListeEidsNaturePersonneExclure() {
        if (listeEidsNaturePersonneExclure == null) {
            listeEidsNaturePersonneExclure = new ArrayList<Long>();
        }
        return listeEidsNaturePersonneExclure;
    }

    /**
     * Définit la valeur de listeEidsNaturePersonneExclure.
     * @param listeEidsNaturePersonneExclure la nouvelle valeur de listeEidsNaturePersonneExclure
     */
    public void setListeEidsNaturePersonneExclure(List<Long> listeEidsNaturePersonneExclure) {
        this.listeEidsNaturePersonneExclure = listeEidsNaturePersonneExclure;
    }

}
