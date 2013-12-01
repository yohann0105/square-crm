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
package com.square.composant.tarificateur.square.client.model.adhesion;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO modélisant les Informations supplémentaires à spécifier pour permettre une adhésion.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class InfosAdhesionModel implements IsSerializable {

    /** Identifiant du devis. */
    private Long idDevis;

    /** Informations supplémentaires pour chaque personne concernée par l'adhésion. */
    private List<InfosPersonneModel> infosPersonnes;

    /** Informations supplémentaires concernant le paiement. */
    private InfosPaiementModel infosPaiement;

    /** Informations du RIB. */
    private InfosRibModel infosRib;

    /** Indique si l'adhérent souhaite la télétransmission. */
    private Boolean teletransmission;

    /** Date de clic sur le lien du BA. */
    private String dateClicBA;

    /** Date de telechargement du BA. */
    private String dateTelechargementBA;

    /**
     * Récupère la valeur de infosPersonnes.
     * @return la valeur de infosPersonnes
     */
    public List<InfosPersonneModel> getInfosPersonnes() {
        if (infosPersonnes == null) {
            infosPersonnes = new ArrayList<InfosPersonneModel>();
        }
        return infosPersonnes;
    }

    /**
     * Définit la valeur de infosPersonnes.
     * @param infosPersonnes la nouvelle valeur de infosPersonnes
     */
    public void setInfosPersonnes(List<InfosPersonneModel> infosPersonnes) {
        this.infosPersonnes = infosPersonnes;
    }

    /**
     * Récupère la valeur de infosPaiement.
     * @return la valeur de infosPaiement
     */
    public InfosPaiementModel getInfosPaiement() {
        return infosPaiement;
    }

    /**
     * Définit la valeur de infosPaiement.
     * @param infosPaiement la nouvelle valeur de infosPaiement
     */
    public void setInfosPaiement(InfosPaiementModel infosPaiement) {
        this.infosPaiement = infosPaiement;
    }

    /**
     * Récupère la valeur de idDevis.
     * @return la valeur de idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }

    /**
     * Définit la valeur de idDevis.
     * @param idDevis la nouvelle valeur de idDevis
     */
    public void setIdDevis(Long idDevis) {
        this.idDevis = idDevis;
    }

    /**
     * Get the infosRib value.
     * @return the infosRib
     */
    public InfosRibModel getInfosRib() {
        return infosRib;
    }

    /**
     * Set the infosRib value.
     * @param infosRib the infosRib to set
     */
    public void setInfosRib(InfosRibModel infosRib) {
        this.infosRib = infosRib;
    }

    /**
     * Get the dateClicBA value.
     * @return the dateClicBA
     */
    public String getDateClicBA() {
        return dateClicBA;
    }

    /**
     * Set the dateClicBA value.
     * @param dateClicBA the dateClicBA to set
     */
    public void setDateClicBA(String dateClicBA) {
        this.dateClicBA = dateClicBA;
    }

    /**
     * Get the dateTelechargementBA value.
     * @return the dateTelechargementBA
     */
    public String getDateTelechargementBA() {
        return dateTelechargementBA;
    }

    /**
     * Set the dateTelechargementBA value.
     * @param dateTelechargementBA the dateTelechargementBA to set
     */
    public void setDateTelechargementBA(String dateTelechargementBA) {
        this.dateTelechargementBA = dateTelechargementBA;
    }

    /**
     * Accesseur pour l'attribut teletransmission.
     * @return l'attribut teletransmission
     */
    public Boolean getTeletransmission() {
        return teletransmission;
    }

    /**
     * Définit la valeur de teletransmission.
     * @param teletransmission la nouvelle valeur de teletransmission
     */
    public void setTeletransmission(Boolean teletransmission) {
        this.teletransmission = teletransmission;
    }

}
