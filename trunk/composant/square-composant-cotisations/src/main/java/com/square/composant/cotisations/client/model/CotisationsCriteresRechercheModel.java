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

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model qui représente les criteres de recherche de cotisations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CotisationsCriteresRechercheModel implements IsSerializable {

    private Long uidPersonne;

    private String dateDebut;

    private String dateFin;

    private String contrat;

    private Long idModePaiement;

    private Float montantMin;

    private Float montantMax;

    private CodeAiaLibelleModel situation;

    private boolean simulation = false;

    /**
     * Consctructeur.
     */
    public CotisationsCriteresRechercheModel() {
        super();
    }

    /**
     * Get the contrat value.
     * @return the contrat
     */
    public String getContrat() {
        return contrat;
    }

    /**
     * Set the contrat value.
     * @param contrat the contrat to set
     */
    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    /**
     * Get the idModePaiement value.
     * @return the idModePaiement
     */
    public Long getIdModePaiement() {
        return idModePaiement;
    }

    /**
     * Set the idModePaiement value.
     * @param idModePaiement the idModePaiement to set
     */
    public void setIdModePaiement(Long idModePaiement) {
        this.idModePaiement = idModePaiement;
    }

    /**
     * Get the montantMin value.
     * @return the montantMin
     */
    public Float getMontantMin() {
        return montantMin;
    }

    /**
     * Set the montantMin value.
     * @param montantMin the montantMin to set
     */
    public void setMontantMin(Float montantMin) {
        this.montantMin = montantMin;
    }

    /**
     * Get the montantMax value.
     * @return the montantMax
     */
    public Float getMontantMax() {
        return montantMax;
    }

    /**
     * Set the montantMax value.
     * @param montantMax the montantMax to set
     */
    public void setMontantMax(Float montantMax) {
        this.montantMax = montantMax;
    }

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
     * Get the uidPersonne value.
     * @return the uidPersonne
     */
    public Long getUidPersonne() {
        return uidPersonne;
    }

    /**
     * Set the uidPersonne value.
     * @param uidPersonne the uidPersonne to set
     */
    public void setUidPersonne(Long uidPersonne) {
        this.uidPersonne = uidPersonne;
    }

    /**
     * Get the simulation value.
     * @return the simulation
     */
    public boolean isSimulation() {
        return simulation;
    }

    /**
     * Set the simulation value.
     * @param simulation the simulation to set
     */
    public void setSimulation(boolean simulation) {
        this.simulation = simulation;
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
}
