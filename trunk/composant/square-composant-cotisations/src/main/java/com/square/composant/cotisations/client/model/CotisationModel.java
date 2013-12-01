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
package com.square.composant.cotisations.client.model;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model qui représente une ligne de cotisation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CotisationModel implements IsSerializable {

    /** Date de début. */
    private String dateDebut;

    /** Date de fin. */
    private String dateFin;

    /** Montant. */
    private Float montant;

    /** Montant réglé. */
    private Float montantRegle;

    /** Jour de paiement. */
    private String jourPaiement;

    /** Mode de paiement. */
    private IdentifiantLibelleGwt modePaiement;

    /** Situation. */
    private CodeAiaLibelleModel situation;

    /** Détail d'une cotisation. */
    private List<DetailCotisationModel> listeDetailsCotisation;

    /** Détail d'une cotisation. */
    private List<DetailEncaissementModel> listeDetailsEncaissement;

    /**
     * Get the dateDebut value.
     * @return the dateDebut
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Set the dateDebut value.
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Get the dateFin value.
     * @return the dateFin
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Set the dateFin value.
     * @param dateFin the dateFin to set
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Get the montant value.
     * @return the montant
     */
    public Float getMontant() {
        return montant;
    }

    /**
     * Set the montant value.
     * @param montant the montant to set
     */
    public void setMontant(Float montant) {
        this.montant = montant;
    }

    /**
     * Get the montantRegle value.
     * @return the montantRegle
     */
    public Float getMontantRegle() {
        return montantRegle;
    }

    /**
     * Set the montantRegle value.
     * @param montantRegle the montantRegle to set
     */
    public void setMontantRegle(Float montantRegle) {
        this.montantRegle = montantRegle;
    }

    /**
     * Get the jourPaiement value.
     * @return the jourPaiement
     */
    public String getJourPaiement() {
        return jourPaiement;
    }

    /**
     * Set the jourPaiement value.
     * @param jourPaiement the jourPaiement to set
     */
    public void setJourPaiement(String jourPaiement) {
        this.jourPaiement = jourPaiement;
    }

    /**
     * Get the modePaiement value.
     * @return the modePaiement
     */
    public IdentifiantLibelleGwt getModePaiement() {
        return modePaiement;
    }

    /**
     * Set the modePaiement value.
     * @param modePaiement the modePaiement to set
     */
    public void setModePaiement(IdentifiantLibelleGwt modePaiement) {
        this.modePaiement = modePaiement;
    }

    /**
     * Get the situation value.
     * @return the situation
     */
    public CodeAiaLibelleModel getSituation() {
        return situation;
    }

    /**
     * Set the situation value.
     * @param situation the situation to set
     */
    public void setSituation(CodeAiaLibelleModel situation) {
        this.situation = situation;
    }

    /**
     * Get the listeDetailsCotisation value.
     * @return the listeDetailsCotisation
     */
    public List<DetailCotisationModel> getListeDetailsCotisation() {
        return listeDetailsCotisation;
    }

    /**
     * Set the listeDetailsCotisation value.
     * @param listeDetailsCotisation the listeDetailsCotisation to set
     */
    public void setListeDetailsCotisation(List<DetailCotisationModel> listeDetailsCotisation) {
        this.listeDetailsCotisation = listeDetailsCotisation;
    }

    /**
     * Get the listeDetailsEncaissement value.
     * @return the listeDetailsEncaissement
     */
    public List<DetailEncaissementModel> getListeDetailsEncaissement() {
        return listeDetailsEncaissement;
    }

    /**
     * Set the listeDetailsEncaissement value.
     * @param listeDetailsEncaissement the listeDetailsEncaissement to set
     */
    public void setListeDetailsEncaissement(List<DetailEncaissementModel> listeDetailsEncaissement) {
        this.listeDetailsEncaissement = listeDetailsEncaissement;
    }

}
