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
package com.square.adherent.noyau.model.dimension;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Modèle du moyen de paiement d'un relevé de prestation.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DIM_RELEVE_PRESTATION_MOYEN_PAIEMENT")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "RELEVE_MOYEN_PAIEMENT_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "libelle", column = @Column(name = "RELEVE_MOYEN_PAIEMENT_LIB", nullable = false)),
    @AttributeOverride(name = "ordre", column = @Column(name = "RELEVE_MOYEN_PAIEMENT_ORDRE", nullable = false)),
    @AttributeOverride(name = "visible", column = @Column(name = "RELEVE_MOYEN_PAIEMENT_VISIBLE", nullable = false))
})
public class RelevePrestationMoyenPaiement extends ModelDim {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 1L;

    /** Autorise l'envoi de mail. */
    @Column(name = "RELEVE_MOYEN_PAIEMENT_AUTORISE_ENVOIE_MAIL")
    private Boolean autorisationEnvoiMail;

    /** UID de la personne associée. */
    @Column(name = "RELEVE_MOYEN_PAIEMENT_EID")
    private String identifiantExterieur;

    /**
     * Récupère la valeur de autorisationEnvoiMail.
     * @return la valeur de autorisationEnvoiMail
     */
    public Boolean getAutorisationEnvoiMail() {
        return autorisationEnvoiMail;
    }

    /**
     * Définit la valeur de autorisationEnvoiMail.
     * @param autorisationEnvoiMail la nouvelle valeur de autorisationEnvoiMail
     */
    public void setAutorisationEnvoiMail(Boolean autorisationEnvoiMail) {
        this.autorisationEnvoiMail = autorisationEnvoiMail;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RelevePrestationMoyenPaiement)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Récupération de identifiantExterieur.
     * @return the identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Définition de identifiantExterieur.
     * @param identifiantExterieur the identifiantExterieur to set
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }
}
