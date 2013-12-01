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
package com.square.composant.tarificateur.square.client.model.opportunite.devis;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.ligne.LigneDevisModel;
import com.square.composant.tarificateur.square.client.model.personne.PersonneModel;

/**
 * Model Gwt modélisant un devis.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class DevisModel implements IsSerializable {

    /** Identifiant du devis. */
    private Long id;

    /** EID de l'agence. */
    private Long eidAgence;

    /** EID du responsable. */
    private Long eidResponsable;

    /** EID du créateur. */
    private Long eidCreateur;

    /** Personne principale du devis. */
    private PersonneModel personnePrincipale;

    /** Date de création. */
    private String dateCreation;

    /** Date de clôture. */
    private String dateCloture;

    /** Identifiant de la finalité du devis. */
    private IdentifiantLibelleGwt finalite;

    /** Motif. */
    private IdentifiantLibelleGwt motif;

    /** Flag indiquant que la famille a été modifiée. */
    private boolean familleModifiee;

    /** Flag indiquant que le devis est en lecture seule. */
    private boolean lectureSeule;

    /** Liste des lignes de devis. */
    private List<LigneDevisModel> listeLigneDevis = new ArrayList<LigneDevisModel>();

    /** Le type du devis. */
    private String type;

    /**
     * Getter function.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter function.
     * @return the eidAgence
     */
    public Long getEidAgence() {
        return eidAgence;
    }

    /**
     * Getter function.
     * @return the eidResponsable
     */
    public Long getEidResponsable() {
        return eidResponsable;
    }

    /**
     * Getter function.
     * @return the eidCreateur
     */
    public Long getEidCreateur() {
        return eidCreateur;
    }

    /**
     * Getter function.
     * @return the personnePrincipale
     */
    public PersonneModel getPersonnePrincipale() {
        return personnePrincipale;
    }

    /**
     * Getter function.
     * @return the dateCreation
     */
    public String getDateCreation() {
        return dateCreation;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Getter function.
     * @return the dateCloture
     */
    public String getDateCloture() {
        return dateCloture;
    }

    /**
     * Getter function.
     * @return the finalite
     */
    public IdentifiantLibelleGwt getFinalite() {
        return finalite;
    }

    /**
     * Getter function.
     * @return the motif
     */
    public IdentifiantLibelleGwt getMotif() {
        return motif;
    }

    /**
     * Setter function.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter function.
     * @param eidAgence the eidAgence to set
     */
    public void setEidAgence(Long eidAgence) {
        this.eidAgence = eidAgence;
    }

    /**
     * Setter function.
     * @param eidResponsable the eidResponsable to set
     */
    public void setEidResponsable(Long eidResponsable) {
        this.eidResponsable = eidResponsable;
    }

    /**
     * Setter function.
     * @param eidCreateur the eidCreateur to set
     */
    public void setEidCreateur(Long eidCreateur) {
        this.eidCreateur = eidCreateur;
    }

    /**
     * Setter function.
     * @param personnePrincipale the personnePrincipale to set
     */
    public void setPersonnePrincipale(PersonneModel personnePrincipale) {
        this.personnePrincipale = personnePrincipale;
    }

    /**
     * Setter function.
     * @param dateCloture the dateCloture to set
     */
    public void setDateCloture(String dateCloture) {
        this.dateCloture = dateCloture;
    }

    /**
     * Setter function.
     * @param finalite the finalite to set
     */
    public void setFinalite(IdentifiantLibelleGwt finalite) {
        this.finalite = finalite;
    }

    /**
     * Setter function.
     * @param motif the motif to set
     */
    public void setMotif(IdentifiantLibelleGwt motif) {
        this.motif = motif;
    }

    /**
     * Récupère la valeur de lectureSeule.
     * @return the lectureSeule
     */
    public boolean isLectureSeule() {
        return lectureSeule;
    }

    /**
     * Définit la valeur de lectureSeule.
     * @param lectureSeule the lectureSeule to set
     */
    public void setLectureSeule(boolean lectureSeule) {
        this.lectureSeule = lectureSeule;
    }

    /**
     * Get the listeLigneDevis value.
     * @return the listeLigneDevis
     */
    public List<LigneDevisModel> getListeLigneDevis() {
        if (listeLigneDevis == null) {
            listeLigneDevis = new ArrayList<LigneDevisModel>();
        }
        return listeLigneDevis;
    }

    /**
     * Set the listeLigneDevis value.
     * @param listeLigneDevis the listeLigneDevis to set
     */
    public void setListeLigneDevis(List<LigneDevisModel> listeLigneDevis) {
        this.listeLigneDevis = listeLigneDevis;
    }

    /**
     * Récupère la valeur de familleModifiee.
     * @return la valeur de familleModifiee
     */
    public boolean isFamilleModifiee() {
        return familleModifiee;
    }

    /**
     * Définit la valeur de familleModifiee.
     * @param familleModifiee la nouvelle valeur de familleModifiee
     */
    public void setFamilleModifiee(boolean familleModifiee) {
        this.familleModifiee = familleModifiee;
    }

    /**
     * Get the type value.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type value.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
