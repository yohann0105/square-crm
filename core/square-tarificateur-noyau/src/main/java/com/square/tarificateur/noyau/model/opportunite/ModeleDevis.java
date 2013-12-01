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
package com.square.tarificateur.noyau.model.opportunite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle graphique d'un devis.
 * @author Goumard Stephane (stephane.goumard@scub.net) - SCUB
 */
@Entity
@Table(name = "DIM_MODELE_DEVIS")
public class ModeleDevis extends BaseModel {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -6785505421052825812L;

    /** Identifiant BDD du modèle Adhérent. */
    public static final String MODELE_DEVIS_ADHERENT = "107373";

    /** Identifiant BDD du modèle Prospect. */
    public static final String MODELE_DEVIS_PROSPECT = "107372";

    /** Identifiant BDD du modèle CNP. */
    public static final String MODELE_DEVIS_CNP = "107374";

    /** Libellé du modele. */
    @Column(name = "LIBELLE")
    private String libelle;

    /** Chemin de la vignette. */
    @Column(name = "NOM_REEL")
    private String nomReel;

    /** Ordre. */
    @Column(name = "ORDRE")
    private Integer ordre;

    /**
     * Get the libelle value.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the libelle value.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Get the nomReel value.
     * @return the nomReel
     */
    public String getNomReel() {
        return nomReel;
    }

    /**
     * Set the nomReel value.
     * @param nomReel the nomReel to set
     */
    public void setNomReel(String nomReel) {
        this.nomReel = nomReel;
    }

    /**
     * Get the ordre value.
     * @return the ordre
     */
    public Integer getOrdre() {
        return ordre;
    }

    /**
     * Set the ordre value.
     * @param ordre the ordre to set
     */
    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ModeleDevis)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
