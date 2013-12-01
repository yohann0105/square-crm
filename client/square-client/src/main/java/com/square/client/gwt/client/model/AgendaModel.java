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
package com.square.client.gwt.client.model;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model contenant les informations à afficher sur l'agenda.
 * @author cblanchard - SCUB
 */
public class AgendaModel implements IsSerializable {

    /** Date de debut de semaine. */
    private String dateDebut;

    /** Date de fin de semaine. */
    private String dateFin;

    /** Mois début. */
    private String moisDebut;

    /** Mois fin. */
    private String moisFin;

    /** Annee début. */
    private String anneeDebut;

    /** Annee fin. */
    private String anneeFin;

    /** Jour debut. */
    private String jourDebut;

    /** Jour fin. */
    private String jourFin;

    /** Date de référence. */
    private Date dateReference;

    /** Liste des rendez vous de l'agenda. */
    private List<RendezVousModel> rendezVous;

    /**
     * Renvoi la valeur de dateDebut.
     * @return dateDebut
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Modifie dateDebut.
     * @param dateDebut la nouvelle valeur de dateDebut
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Renvoi la valeur de dateFin.
     * @return dateFin
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Modifie dateFin.
     * @param dateFin la nouvelle valeur de dateFin
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Renvoi la valeur de jourDebut.
     * @return jourDebut
     */
    public String getJourDebut() {
        return jourDebut;
    }

    /**
     * Modifie jourDebut.
     * @param jourDebut la nouvelle valeur de jourDebut
     */
    public void setJourDebut(String jourDebut) {
        this.jourDebut = jourDebut;
    }

    /**
     * Renvoi la valeur de jourFin.
     * @return jourFin
     */
    public String getJourFin() {
        return jourFin;
    }

    /**
     * Modifie jourFin.
     * @param jourFin la nouvelle valeur de jourFin
     */
    public void setJourFin(String jourFin) {
        this.jourFin = jourFin;
    }

    /**
     * Renvoi la valeur de dateReference.
     * @return dateReference
     */
    public Date getDateReference() {
        return dateReference;
    }

    /**
     * Modifie dateReference.
     * @param dateReference la nouvelle valeur de dateReference
     */
    public void setDateReference(Date dateReference) {
        this.dateReference = dateReference;
    }

    /**
     * Get the value of rendezVous.
     * @return the rendezVous
     */
    public List<RendezVousModel> getRendezVous() {
        return rendezVous;
    }

    /**
     * Set the value of rendezVous.
     * @param rendezVous the rendezVous to set
     */
    public void setRendezVous(List<RendezVousModel> rendezVous) {
        this.rendezVous = rendezVous;
    }

    /**
     * Get the value of moisDebut.
     * @return the moisDebut
     */
    public String getMoisDebut() {
        return moisDebut;
    }

    /**
     * Set the value of moisDebut.
     * @param moisDebut the moisDebut to set
     */
    public void setMoisDebut(String moisDebut) {
        this.moisDebut = moisDebut;
    }

    /**
     * Get the value of moisFin.
     * @return the moisFin
     */
    public String getMoisFin() {
        return moisFin;
    }

    /**
     * Set the value of moisFin.
     * @param moisFin the moisFin to set
     */
    public void setMoisFin(String moisFin) {
        this.moisFin = moisFin;
    }

    /**
     * Get the value of anneeDebut.
     * @return the anneeDebut
     */
    public String getAnneeDebut() {
        return anneeDebut;
    }

    /**
     * Set the value of anneeDebut.
     * @param anneeDebut the anneeDebut to set
     */
    public void setAnneeDebut(String anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    /**
     * Get the value of anneeFin.
     * @return the anneeFin
     */
    public String getAnneeFin() {
        return anneeFin;
    }

    /**
     * Set the value of anneeFin.
     * @param anneeFin the anneeFin to set
     */
    public void setAnneeFin(String anneeFin) {
        this.anneeFin = anneeFin;
    }
}
