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
package com.square.tarificateur.noyau.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * DTO modélisant les Informations supplémentaires à spécifier pour permettre une adhésion.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class InfosAdhesionDto implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 240917037877560759L;

    /** Identifiant du devis. */
    private Long idDevis;

    /** Informations supplémentaires pour chaque personne concernée par l'adhésion. */
    private List<InfosPersonneDto> infosPersonnes;

    /** Informations supplémentaires concernant le paiement. */
    private InfosPaiementDto infosPaiement;

    /** Identifiant du BA déposé sur le serveur CNS. */
    private String idFichierCNS;

    /** Informations du RIB. */
    private InfosRibDto infosRib;

    /** Indique si l'adhérent souhaite la télétransmission. */
    private Boolean teletransmission = false;

    /** Date de clic sur le lien du BA. */
    private Calendar dateClicBA;

    /** Date de telechargement du BA. */
    private Calendar dateTelechargementBA;

    /** Libellé de l'ancienne complémentaire de l'adhérent. */
    private String ancienneComplemetaireAdherent;

    /** Date de fin du contrat de l'adhérent. */
    private Calendar dateFinContratAdherent;

    /** Libellé de l'ancienne complémentaire du conjoint. */
    private String ancienneComplemetaireConjoint;

    /** Date de fin du contrat du conjoint. */
    private Calendar dateFinContratConjoint;

    /**
     * Récupère la valeur de infosPersonnes.
     * @return la valeur de infosPersonnes
     */
    public List<InfosPersonneDto> getInfosPersonnes() {
        if (infosPersonnes == null) {
            infosPersonnes = new ArrayList<InfosPersonneDto>();
        }
        return infosPersonnes;
    }

    /**
     * Définit la valeur de infosPersonnes.
     * @param infosPersonnes la nouvelle valeur de infosPersonnes
     */
    public void setInfosPersonnes(List<InfosPersonneDto> infosPersonnes) {
        this.infosPersonnes = infosPersonnes;
    }

    /**
     * Récupère la valeur de infosPaiement.
     * @return la valeur de infosPaiement
     */
    public InfosPaiementDto getInfosPaiement() {
        return infosPaiement;
    }

    /**
     * Définit la valeur de infosPaiement.
     * @param infosPaiement la nouvelle valeur de infosPaiement
     */
    public void setInfosPaiement(InfosPaiementDto infosPaiement) {
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
     * Get the idFichierCNS value.
     * @return the idFichierCNS
     */
    public String getIdFichierCNS() {
        return idFichierCNS;
    }

    /**
     * Set the idFichierCNS value.
     * @param idFichierCNS the idFichierCNS to set
     */
    public void setIdFichierCNS(String idFichierCNS) {
        this.idFichierCNS = idFichierCNS;
    }

    /**
     * Get the infosRib value.
     * @return the infosRib
     */
    public InfosRibDto getInfosRib() {
        return infosRib;
    }

    /**
     * Set the infosRib value.
     * @param infosRib the infosRib to set
     */
    public void setInfosRib(InfosRibDto infosRib) {
        this.infosRib = infosRib;
    }

    /**
     * Get the teletransmission value.
     * @return the teletransmission
     */
    public Boolean getTeletransmission() {
        return teletransmission;
    }

    /**
     * Set the teletransmission value.
     * @param teletransmission the teletransmission to set
     */
    public void setTeletransmission(Boolean teletransmission) {
        this.teletransmission = teletransmission;
    }

    /**
     * Get the dateClicBA value.
     * @return the dateClicBA
     */
    public Calendar getDateClicBA() {
        return dateClicBA;
    }

    /**
     * Set the dateClicBA value.
     * @param dateClicBA the dateClicBA to set
     */
    public void setDateClicBA(Calendar dateClicBA) {
        this.dateClicBA = dateClicBA;
    }

    /**
     * Get the dateTelechargementBA value.
     * @return the dateTelechargementBA
     */
    public Calendar getDateTelechargementBA() {
        return dateTelechargementBA;
    }

    /**
     * Set the dateTelechargementBA value.
     * @param dateTelechargementBA the dateTelechargementBA to set
     */
    public void setDateTelechargementBA(Calendar dateTelechargementBA) {
        this.dateTelechargementBA = dateTelechargementBA;
    }

    /**
     * Récupère la valeur de ancienneComplemetaireAdherent.
     * @return la valeur de ancienneComplemetaireAdherent
     */
    public String getAncienneComplemetaireAdherent() {
        return ancienneComplemetaireAdherent;
    }

    /**
     * Définit la valeur de ancienneComplemetaireAdherent.
     * @param ancienneComplemetaireAdherent la nouvelle valeur de ancienneComplemetaireAdherent
     */
    public void setAncienneComplemetaireAdherent(String ancienneComplemetaireAdherent) {
        this.ancienneComplemetaireAdherent = ancienneComplemetaireAdherent;
    }

    /**
     * Récupère la valeur de dateFinContratAdherent.
     * @return la valeur de dateFinContratAdherent
     */
    public Calendar getDateFinContratAdherent() {
        return dateFinContratAdherent;
    }

    /**
     * Définit la valeur de dateFinContratAdherent.
     * @param dateFinContratAdherent la nouvelle valeur de dateFinContratAdherent
     */
    public void setDateFinContratAdherent(Calendar dateFinContratAdherent) {
        this.dateFinContratAdherent = dateFinContratAdherent;
    }

    /**
     * Récupère la valeur de ancienneComplemetaireConjoint.
     * @return la valeur de ancienneComplemetaireConjoint
     */
    public String getAncienneComplemetaireConjoint() {
        return ancienneComplemetaireConjoint;
    }

    /**
     * Définit la valeur de ancienneComplemetaireConjoint.
     * @param ancienneComplemetaireConjoint la nouvelle valeur de ancienneComplemetaireConjoint
     */
    public void setAncienneComplemetaireConjoint(String ancienneComplemetaireConjoint) {
        this.ancienneComplemetaireConjoint = ancienneComplemetaireConjoint;
    }

    /**
     * Récupère la valeur de dateFinContratConjoint.
     * @return la valeur de dateFinContratConjoint
     */
    public Calendar getDateFinContratConjoint() {
        return dateFinContratConjoint;
    }

    /**
     * Définit la valeur de dateFinContratConjoint.
     * @param dateFinContratConjoint la nouvelle valeur de dateFinContratConjoint
     */
    public void setDateFinContratConjoint(Calendar dateFinContratConjoint) {
        this.dateFinContratConjoint = dateFinContratConjoint;
    }

}
