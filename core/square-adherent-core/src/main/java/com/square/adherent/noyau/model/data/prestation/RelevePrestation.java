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
package com.square.adherent.noyau.model.data.prestation;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.scub.foundation.framework.core.model.BaseModel;

import com.square.adherent.noyau.model.dimension.RelevePrestationMoyenPaiement;

/**
 * Modèle d'un relevé de prestation.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_RELEVE_PRESTATION")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "RELEVE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "RELEVE_VERSION", unique = false))
})
public class RelevePrestation extends BaseModel {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 1749320416836838362L;

    /** UID de la personne associée. */
    @Column(name = "RELEVE_PERSONNE_UID")
    private Long uidPersonne;

    /** Nom du fichier associé. */
    @Column(name = "RELEVE_NOM_FICHIER")
    private String nomFichier;

    /** Nom du fichier commercial associé. */
    @Column(name = "RELEVE_NOM_FICHIER_COMMERCIAL")
    private String nomFichierCommercial;

    /** Date d'impression. */
    @Column(name = "RELEVE_DATE_IMPRESSION")
    private Calendar dateImpression;

    /** Flag indiquant si envoyé par email. */
    @Column(name = "RELEVE_ENVOI_MAIL")
    private Boolean envoyeMail;

    /** Date d'envoi de l'email. */
    @Column(name = "RELEVE_DATE_ENVOIE_MAIL")
    private Calendar dateEnvoiMail;

    /** Moyen de paiement. */
    @ManyToOne
    @JoinColumn(name = "RELEVE_MOYEN_PAIEMENT_UID")
    private RelevePrestationMoyenPaiement moyenPaiement;

    /**
     * Récupère la valeur de uidPersonne.
     * @return la valeur de uidPersonne
     */
    public Long getUidPersonne() {
        return uidPersonne;
    }

    /**
     * Définit la valeur de uidPersonne.
     * @param uidPersonne la nouvelle valeur de uidPersonne
     */
    public void setUidPersonne(Long uidPersonne) {
        this.uidPersonne = uidPersonne;
    }

    /**
     * Récupère la valeur de nomFichier.
     * @return la valeur de nomFichier
     */
    public String getNomFichier() {
        return nomFichier;
    }

    /**
     * Définit la valeur de nomFichier.
     * @param nomFichier la nouvelle valeur de nomFichier
     */
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    /**
     * Récupère la valeur de nomFichierCommercial.
     * @return la valeur de nomFichierCommercial
     */
    public String getNomFichierCommercial() {
        return nomFichierCommercial;
    }

    /**
     * Définit la valeur de nomFichierCommercial.
     * @param nomFichierCommercial la nouvelle valeur de nomFichierCommercial
     */
    public void setNomFichierCommercial(String nomFichierCommercial) {
        this.nomFichierCommercial = nomFichierCommercial;
    }

    /**
     * Récupère la valeur de dateImpression.
     * @return la valeur de dateImpression
     */
    public Calendar getDateImpression() {
        return dateImpression;
    }

    /**
     * Définit la valeur de dateImpression.
     * @param dateImpression la nouvelle valeur de dateImpression
     */
    public void setDateImpression(Calendar dateImpression) {
        this.dateImpression = dateImpression;
    }

    /**
     * Récupère la valeur de envoyeMail.
     * @return la valeur de envoyeMail
     */
    public Boolean getEnvoyeMail() {
        return envoyeMail;
    }

    /**
     * Définit la valeur de envoyeMail.
     * @param envoyeMail la nouvelle valeur de envoyeMail
     */
    public void setEnvoyeMail(Boolean envoyeMail) {
        this.envoyeMail = envoyeMail;
    }

    /**
     * Récupère la valeur de dateEnvoiMail.
     * @return la valeur de dateEnvoiMail
     */
    public Calendar getDateEnvoiMail() {
        return dateEnvoiMail;
    }

    /**
     * Définit la valeur de dateEnvoiMail.
     * @param dateEnvoiMail la nouvelle valeur de dateEnvoiMail
     */
    public void setDateEnvoiMail(Calendar dateEnvoiMail) {
        this.dateEnvoiMail = dateEnvoiMail;
    }

    /**
     * Récupère la valeur de moyenPaiement.
     * @return la valeur de moyenPaiement
     */
    public RelevePrestationMoyenPaiement getMoyenPaiement() {
        return moyenPaiement;
    }

    /**
     * Définit la valeur de moyenPaiement.
     * @param moyenPaiement la nouvelle valeur de moyenPaiement
     */
    public void setMoyenPaiement(RelevePrestationMoyenPaiement moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof RelevePrestation) {
            return super.equalsUtil(other);
        } else {
            return false;
        }
    }
}
