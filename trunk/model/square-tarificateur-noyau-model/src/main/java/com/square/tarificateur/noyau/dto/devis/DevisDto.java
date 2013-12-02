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
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.tarificateur.noyau.dto.personne.PersonneDto;

/**
 * DTO modélisant un devis.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class DevisDto implements Serializable {

    /** SerialVersionUID. */
    private static final long serialVersionUID = -5879816764669079129L;

    /** Identifiant du devis. */
    private Long id;

    /** EID de l'opportunité. */
    private Long eidOpportunite;

    /** EID de l'agence. */
    private Long eidAgence;

    /** EID du responsable. */
    private Long eidResponsable;

    /** EID du créateur. */
    private Long eidCreateur;

    /** Personne principale du devis. */
    private PersonneDto personnePrincipale;

    /** Date de création. */
    private Calendar dateCreation;

    /** Date de clôture. */
    private Calendar dateCloture;


    /** Identifiant de la finalité du devis. */
    private IdentifiantLibelleDto finalite;

    /** Motif. */
    private IdentifiantLibelleDto motif;

    /** Flag indiquant que la famille a été modifiée. */
    private boolean familleModifiee;

    /** Flag indiquant que le devis est en lecture seule. */
    private boolean lectureSeule;

    /** Liste des lignes de devis. */
    private List<LigneDevisDto> listeLigneDevis = new ArrayList<LigneDevisDto>();

    /** Type du devis (CNP ou SANTE/PREVOYANCE). */
    private String type;

    /** EId de la relation dans le cadre d'un parrainage. */
    private Long eidRelationParrain;

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
    public PersonneDto getPersonnePrincipale() {
        return personnePrincipale;
    }

    /**
     * Getter function.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Getter function.
     * @return the dateCloture
     */
    public Calendar getDateCloture() {
        return dateCloture;
    }

    /**
     * Getter function.
     * @return the finalite
     */
    public IdentifiantLibelleDto getFinalite() {
        return finalite;
    }

    /**
     * Getter function.
     * @return the motif
     */
    public IdentifiantLibelleDto getMotif() {
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
    public void setPersonnePrincipale(PersonneDto personnePrincipale) {
        this.personnePrincipale = personnePrincipale;
    }

    /**
     * Setter function.
     * @param dateCloture the dateCloture to set
     */
    public void setDateCloture(Calendar dateCloture) {
        this.dateCloture = dateCloture;
    }

    /**
     * Setter function.
     * @param finalite the finalite to set
     */
    public void setFinalite(IdentifiantLibelleDto finalite) {
        this.finalite = finalite;
    }

    /**
     * Setter function.
     * @param motif the motif to set
     */
    public void setMotif(IdentifiantLibelleDto motif) {
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
    public List<LigneDevisDto> getListeLigneDevis() {
        if (listeLigneDevis == null) {
            listeLigneDevis = new ArrayList<LigneDevisDto>();
        }
        return listeLigneDevis;
    }

    /**
     * Set the listeLigneDevis value.
     * @param listeLigneDevis the listeLigneDevis to set
     */
    public void setListeLigneDevis(List<LigneDevisDto> listeLigneDevis) {
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

    /**
     * Get the eidRelationParrain value.
     * @return the eidRelationParrain
     */
    public Long getEidRelationParrain() {
        return eidRelationParrain;
    }

    /**
     * Set the eidRelationParrain value.
     * @param eidRelationParrain the eidRelationParrain to set
     */
    public void setEidRelationParrain(Long eidRelationParrain) {
        this.eidRelationParrain = eidRelationParrain;
    }

    /**
     * Récupère la valeur de eidOpportunite.
     * @return la valeur de eidOpportunite
     */
    public Long getEidOpportunite() {
        return eidOpportunite;
    }

    /**
     * Définit la valeur de eidOpportunite.
     * @param eidOpportunite la nouvelle valeur de eidOpportunite
     */
    public void setEidOpportunite(Long eidOpportunite) {
        this.eidOpportunite = eidOpportunite;
    }
}