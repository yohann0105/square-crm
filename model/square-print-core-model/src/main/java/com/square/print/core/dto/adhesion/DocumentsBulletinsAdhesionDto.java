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
package com.square.print.core.dto.adhesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.print.core.dto.AgenceEditiqueDto;

/**
 * Modélise un ou plusieurs bulletin d'adhesion / fiche de transfert et les informations nécéssaires à la génération au format pdf.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net)
 */
public class DocumentsBulletinsAdhesionDto implements Serializable {

    private static final long serialVersionUID = -1023919974382990151L;

    /** Prospect associé à l'adhésion. */
    private ProspectBADto prospect;

    /** Agence responsable de l'adhésion. */
    private AgenceEditiqueDto agence;

    /** Liste des bulletins/fiches. */
    private List<BulletinAdhesionDto> listeBulletinsAdhesion;

    /** Détermine si on génère un courrier d'accompagnement. */
    private Boolean genererCourrierAccompagnement;

    /** Flag pour savoir si il s'agit de signature numérique ou pas. */
    private Boolean fromSignatureNumerique = Boolean.FALSE;

    /** Détermine si on génère les grilles de prestations. */
    private Boolean genererGrillesPrestations;

    /** Indique s'il y a au moins un produit santé parmi les propositions. */
    private Boolean possedeProduitSante;

    /** Motif. */
    private IdentifiantLibelleDto motifDevis;

    /** Identifiant du modèle de devis. */
    private Long idModeleDevis;

    /**
     * Flag indiquant que c'est une impression pour un courtier. TODO à renseigner lors de la migration de Square
     */
    private Boolean pourCourtier = Boolean.FALSE;

    /** Constructeur. */
    public DocumentsBulletinsAdhesionDto() {
    }

    /**
     * Constructeur avec paramètres.
     * @param prospect le prospect
     * @param agence l'agence
     * @param listeBulletinsAdhesion la liste des bulletins d'adhésion
     * @param genererCourrierAccompagnement flag indiquant si le courrier d'accompagnement doit être généré
     * @param genererGrillesPrestations flag indiquant si les grilles de prestations doivent être générées
     * @param possedeProduitSante flag indiquant s'il y a un produit santé parmi les produits
     * @param motifDevis le motif de devis
     * @param idModeleDevis identifiant du modèle de devis
     */
    public DocumentsBulletinsAdhesionDto(ProspectBADto prospect, AgenceEditiqueDto agence, List<BulletinAdhesionDto> listeBulletinsAdhesion,
        Boolean genererCourrierAccompagnement, Boolean genererGrillesPrestations, Boolean possedeProduitSante, IdentifiantLibelleDto motifDevis,
        Long idModeleDevis) {
        this.prospect = prospect;
        this.agence = agence;
        this.listeBulletinsAdhesion = listeBulletinsAdhesion;
        this.genererCourrierAccompagnement = genererCourrierAccompagnement;
        this.genererGrillesPrestations = genererGrillesPrestations;
        this.possedeProduitSante = possedeProduitSante;
        this.motifDevis = motifDevis;
        this.idModeleDevis = idModeleDevis;
    }

    /**
     * Get the prospect value.
     * @return the prospect
     */
    public ProspectBADto getProspect() {
        return prospect;
    }

    /**
     * Set the prospect value.
     * @param prospect the prospect to set
     */
    public void setProspect(ProspectBADto prospect) {
        this.prospect = prospect;
    }

    /**
     * Get the listeBulletinsAdhesion value.
     * @return the listeBulletinsAdhesion
     */
    public List<BulletinAdhesionDto> getListeBulletinsAdhesion() {
        return listeBulletinsAdhesion;
    }

    /**
     * Set the listeBulletinsAdhesion value.
     * @param listeBulletinsAdhesion the listeBulletinsAdhesion to set
     */
    public void setListeBulletinsAdhesion(List<BulletinAdhesionDto> listeBulletinsAdhesion) {
        this.listeBulletinsAdhesion = listeBulletinsAdhesion;
    }

    /**
     * Ajoute un bulletin d'adhésion au document.
     * @param bulletinAdhesion le bulletin d'adhésion à ajouter.
     */
    public void addBulletinAdhesion(BulletinAdhesionDto bulletinAdhesion) {
        if (listeBulletinsAdhesion == null) {
            this.listeBulletinsAdhesion = new ArrayList<BulletinAdhesionDto>();
        }
        this.listeBulletinsAdhesion.add(bulletinAdhesion);
    }

    /**
     * Get the genererCourrierAccompagnement value.
     * @return the genererCourrierAccompagnement
     */
    public Boolean getGenererCourrierAccompagnement() {
        return genererCourrierAccompagnement;
    }

    /**
     * Set the genererCourrierAccompagnement value.
     * @param genererCourrierAccompagnement the genererCourrierAccompagnement to set
     */
    public void setGenererCourrierAccompagnement(Boolean genererCourrierAccompagnement) {
        this.genererCourrierAccompagnement = genererCourrierAccompagnement;
    }

    /**
     * Get the genererGrillesPrestations value.
     * @return the genererGrillesPrestations
     */
    public Boolean getGenererGrillesPrestations() {
        return genererGrillesPrestations;
    }

    /**
     * Set the genererGrillesPrestations value.
     * @param genererGrillesPrestations the genererGrillesPrestations to set
     */
    public void setGenererGrillesPrestations(Boolean genererGrillesPrestations) {
        this.genererGrillesPrestations = genererGrillesPrestations;
    }

    /**
     * Récupère la valeur de possedeProduitSante.
     * @return la valeur de possedeProduitSante
     */
    public Boolean getPossedeProduitSante() {
        return possedeProduitSante;
    }

    /**
     * Définit la valeur de possedeProduitSante.
     * @param possedeProduitSante la nouvelle valeur de possedeProduitSante
     */
    public void setPossedeProduitSante(Boolean possedeProduitSante) {
        this.possedeProduitSante = possedeProduitSante;
    }

    /**
     * Récupère la valeur de idModeleDevis.
     * @return la valeur de idModeleDevis
     */
    public Long getIdModeleDevis() {
        return idModeleDevis;
    }

    /**
     * Définit la valeur de idModeleDevis.
     * @param idModeleDevis la nouvelle valeur de idModeleDevis
     */
    public void setIdModeleDevis(Long idModeleDevis) {
        this.idModeleDevis = idModeleDevis;
    }

    /**
     * Récupère la valeur de pourCourtier.
     * @return the pourCourtier
     */
    public Boolean getPourCourtier() {
        return pourCourtier;
    }

    /**
     * Définit la valeur de pourCourtier.
     * @param pourCourtier the pourCourtier to set
     */
    public void setPourCourtier(Boolean pourCourtier) {
        this.pourCourtier = pourCourtier;
    }

    /**
     * Get the fromSignatureNumerique value.
     * @return the fromSignatureNumerique
     */
    public Boolean getFromSignatureNumerique() {
        return fromSignatureNumerique;
    }

    /**
     * Set the fromSignatureNumerique value.
     * @param fromSignatureNumerique the fromSignatureNumerique to set
     */
    public void setFromSignatureNumerique(Boolean fromSignatureNumerique) {
        this.fromSignatureNumerique = fromSignatureNumerique;
    }

    /**
     * Récupère la valeur de agence.
     * @return la valeur de agence
     */
    public AgenceEditiqueDto getAgence() {
        return agence;
    }

    /**
     * Définit la valeur de agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(AgenceEditiqueDto agence) {
        this.agence = agence;
    }

    /**
     * Get the motifDevis value.
     * @return the motifDevis
     */
    public IdentifiantLibelleDto getMotifDevis() {
        return motifDevis;
    }

    /**
     * Set the motifDevis value.
     * @param motifDevis the motifDevis to set
     */
    public void setMotifDevis(IdentifiantLibelleDto motifDevis) {
        this.motifDevis = motifDevis;
    }
}
