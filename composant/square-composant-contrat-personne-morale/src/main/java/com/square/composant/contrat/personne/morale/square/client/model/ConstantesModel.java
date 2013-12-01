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
package com.square.composant.contrat.personne.morale.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle permettant de centraliser les constantes nécessaires à l'application.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ConstantesModel implements IsSerializable {

    /** Identifiant du statut "En cours" d'un contrat. */
    private Long idStatutContratEnCours;

    /** Identifiant du statut "Résilié" d'un contrat. */
    private Long idStatutContratResilie;

    /** Identifiant du type de gestion "Souscripteur". */
    private Long idTypeGestionSouscripteur;

    /** Identifiant du type de gestion "Multipart". */
    private Long idTypeGestionMultipart;

    /** Identifiant du statut de la garantie "Encours". */
    private Long idStatutGarantieEnCours;

    /** Identifiant du statut de la garantie "Résiliée". */
    private Long idStatutGarantieResiliee;

    /** Identifiant du statut de la garantie "Futur". */
    private Long idStatutGarantieFutur;

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
     * Récupère la valeur de idTypeGestionSouscripteur.
     * @return la valeur de idTypeGestionSouscripteur
     */
    public Long getIdTypeGestionSouscripteur() {
        return idTypeGestionSouscripteur;
    }

    /**
     * Définit la valeur de idTypeGestionSouscripteur.
     * @param idTypeGestionSouscripteur la nouvelle valeur de idTypeGestionSouscripteur
     */
    public void setIdTypeGestionSouscripteur(Long idTypeGestionSouscripteur) {
        this.idTypeGestionSouscripteur = idTypeGestionSouscripteur;
    }

    /**
     * Récupère la valeur de idTypeGestionMultipart.
     * @return la valeur de idTypeGestionMultipart
     */
    public Long getIdTypeGestionMultipart() {
        return idTypeGestionMultipart;
    }

    /**
     * Définit la valeur de idTypeGestionMultipart.
     * @param idTypeGestionMultipart la nouvelle valeur de idTypeGestionMultipart
     */
    public void setIdTypeGestionMultipart(Long idTypeGestionMultipart) {
        this.idTypeGestionMultipart = idTypeGestionMultipart;
    }

    /**
     * Récupère la valeur de idStatutGarantieEnCours.
     * @return la valeur de idStatutGarantieEnCours
     */
    public Long getIdStatutGarantieEnCours() {
        return idStatutGarantieEnCours;
    }

    /**
     * Définit la valeur de idStatutGarantieEnCours.
     * @param idStatutGarantieEnCours la nouvelle valeur de idStatutGarantieEnCours
     */
    public void setIdStatutGarantieEnCours(Long idStatutGarantieEnCours) {
        this.idStatutGarantieEnCours = idStatutGarantieEnCours;
    }

    /**
     * Récupère la valeur de idStatutGarantieResiliee.
     * @return la valeur de idStatutGarantieResiliee
     */
    public Long getIdStatutGarantieResiliee() {
        return idStatutGarantieResiliee;
    }

    /**
     * Définit la valeur de idStatutGarantieResiliee.
     * @param idStatutGarantieResiliee la nouvelle valeur de idStatutGarantieResiliee
     */
    public void setIdStatutGarantieResiliee(Long idStatutGarantieResiliee) {
        this.idStatutGarantieResiliee = idStatutGarantieResiliee;
    }

    /**
     * Récupère la valeur de idStatutGarantieFutur.
     * @return la valeur de idStatutGarantieFutur
     */
    public Long getIdStatutGarantieFutur() {
        return idStatutGarantieFutur;
    }

    /**
     * Définit la valeur de idStatutGarantieFutur.
     * @param idStatutGarantieFutur la nouvelle valeur de idStatutGarantieFutur
     */
    public void setIdStatutGarantieFutur(Long idStatutGarantieFutur) {
        this.idStatutGarantieFutur = idStatutGarantieFutur;
    }
}
