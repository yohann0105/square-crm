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
package com.square.composant.contrat.square.client.model;

import java.util.ArrayList;
import java.util.List;

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

    /** Identifiant du segment "Collectif Individualisé". */
    private Long idSegmentCollectifIndividualise;

    /** Identifiant du type payeur "Souscripteur". */
    private Long idTypePayeurSouscripteur;

    /** Identifiant du statut "En cours" d'une garantie. */
    private Long idStatutGarantieEnCours;

    /** Identifiant du statut "Résiliée" d'une garantie. */
    private Long idStatutGarantieResiliee;

    /** Identifiant du statut "Futur" d'une garantie. */
    private Long idStatutGarantieFutur;

    /** Liste des identifiants des moyens de paiement pour lesquels les jours de paiment ne sont pas affichés. */
    private List<Long> listeIdsMoyenPaiementCotisSansJour;

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
     * Récupère la valeur de idTypePayeurSouscripteur.
     * @return la valeur de idTypePayeurSouscripteur
     */
    public Long getIdTypePayeurSouscripteur() {
        return idTypePayeurSouscripteur;
    }

    /**
     * Définit la valeur de idTypePayeurSouscripteur.
     * @param idTypePayeurSouscripteur la nouvelle valeur de idTypePayeurSouscripteur
     */
    public void setIdTypePayeurSouscripteur(Long idTypePayeurSouscripteur) {
        this.idTypePayeurSouscripteur = idTypePayeurSouscripteur;
    }

    /**
     * Récupère la valeur de idSegmentCollectifIndividualise.
     * @return la valeur de idSegmentCollectifIndividualise
     */
    public Long getIdSegmentCollectifIndividualise() {
        return idSegmentCollectifIndividualise;
    }

    /**
     * Définit la valeur de idSegmentCollectifIndividualise.
     * @param idSegmentCollectifIndividualise la nouvelle valeur de idSegmentCollectifIndividualise
     */
    public void setIdSegmentCollectifIndividualise(Long idSegmentCollectifIndividualise) {
        this.idSegmentCollectifIndividualise = idSegmentCollectifIndividualise;
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

    /**
     * Récupère la valeur de listeIdsMoyenPaiementCotisSansJour.
     * @return la valeur de listeIdsMoyenPaiementCotisSansJour
     */
    public List<Long> getListeIdsMoyenPaiementCotisSansJour() {
        if (listeIdsMoyenPaiementCotisSansJour == null) {
            listeIdsMoyenPaiementCotisSansJour = new ArrayList<Long>();
        }
        return listeIdsMoyenPaiementCotisSansJour;
    }

    /**
     * Définit la valeur de listeIdsMoyenPaiementCotisSansJour.
     * @param listeIdsMoyenPaiementCotisSansJour la nouvelle valeur de listeIdsMoyenPaiementCotisSansJour
     */
    public void setListeIdsMoyenPaiementCotisSansJour(List<Long> listeIdsMoyenPaiementCotisSansJour) {
        this.listeIdsMoyenPaiementCotisSansJour = listeIdsMoyenPaiementCotisSansJour;
    }

}
