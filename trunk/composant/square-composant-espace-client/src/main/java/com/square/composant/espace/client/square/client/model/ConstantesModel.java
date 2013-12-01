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
package com.square.composant.espace.client.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model permettant de centraliser les constantes nécessaires à l'application.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ConstantesModel implements IsSerializable {

    /** Identifiant du statut "En cours" d'un contrat. */
    private Long idStatutContratEnCours;

    /** Identifiant du statut "Résilié" d'un contrat. */
    private Long idStatutContratResilie;

    /** Identifiant du segment "Collectif". */
    private Long idSegmentCollectif;

    /** Identifiant du type d'option. */
    private Long idTypeOptionEnvoiRelevesPrestationEmail;

    /** Identifiant du type d'option. */
    private Long idTypeOptionEnvoiMutuellementEmail;

    /** Identifiant du type d'option. */
    private Long idTypeOptionEnvoiSms;

    /**
     * Récupère la valeur de idStatutContratEnCours.
     * @return la valeur de idStatutContratEnCours
     */
    public Long getIdStatutContratEnCours() {
        return idStatutContratEnCours;
    }

    /**
     * Définit la valeur de idStatutContratEnCours.
     * @param idStatutContratEnCours la nouvelle valeur de idStatutContratEnCours
     */
    public void setIdStatutContratEnCours(Long idStatutContratEnCours) {
        this.idStatutContratEnCours = idStatutContratEnCours;
    }

    /**
     * Récupère la valeur de idStatutContratResilie.
     * @return la valeur de idStatutContratResilie
     */
    public Long getIdStatutContratResilie() {
        return idStatutContratResilie;
    }

    /**
     * Définit la valeur de idStatutContratResilie.
     * @param idStatutContratResilie la nouvelle valeur de idStatutContratResilie
     */
    public void setIdStatutContratResilie(Long idStatutContratResilie) {
        this.idStatutContratResilie = idStatutContratResilie;
    }

    /**
     * Récupère la valeur de idSegmentCollectif.
     * @return la valeur de idSegmentCollectif
     */
    public Long getIdSegmentCollectif() {
        return idSegmentCollectif;
    }

    /**
     * Définit la valeur de idSegmentCollectif.
     * @param idSegmentCollectif la nouvelle valeur de idSegmentCollectif
     */
    public void setIdSegmentCollectif(Long idSegmentCollectif) {
        this.idSegmentCollectif = idSegmentCollectif;
    }

    /**
     * Getter function.
     * @return the idTypeOptionEnvoiRelevesPrestationEmail
     */
    public Long getIdTypeOptionEnvoiRelevesPrestationEmail() {
        return idTypeOptionEnvoiRelevesPrestationEmail;
    }

    /**
     * Getter function.
     * @return the idTypeOptionEnvoiMutuellementEmail
     */
    public Long getIdTypeOptionEnvoiMutuellementEmail() {
        return idTypeOptionEnvoiMutuellementEmail;
    }

    /**
     * Getter function.
     * @return the idTypeOptionEnvoiSms
     */
    public Long getIdTypeOptionEnvoiSms() {
        return idTypeOptionEnvoiSms;
    }

    /**
     * Setter function.
     * @param idTypeOptionEnvoiRelevesPrestationEmail the idTypeOptionEnvoiRelevesPrestationEmail to set
     */
    public void setIdTypeOptionEnvoiRelevesPrestationEmail(Long idTypeOptionEnvoiRelevesPrestationEmail) {
        this.idTypeOptionEnvoiRelevesPrestationEmail = idTypeOptionEnvoiRelevesPrestationEmail;
    }

    /**
     * Setter function.
     * @param idTypeOptionEnvoiMutuellementEmail the idTypeOptionEnvoiMutuellementEmail to set
     */
    public void setIdTypeOptionEnvoiMutuellementEmail(Long idTypeOptionEnvoiMutuellementEmail) {
        this.idTypeOptionEnvoiMutuellementEmail = idTypeOptionEnvoiMutuellementEmail;
    }

    /**
     * Setter function.
     * @param idTypeOptionEnvoiSms the idTypeOptionEnvoiSms to set
     */
    public void setIdTypeOptionEnvoiSms(Long idTypeOptionEnvoiSms) {
        this.idTypeOptionEnvoiSms = idTypeOptionEnvoiSms;
    }
}
