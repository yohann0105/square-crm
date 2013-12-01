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
package com.square.adherent.noyau.dto.produit;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Dto sur les informations produits.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class InfosProduitDto implements Serializable {

    /** /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -4770502457903916970L;

    /** Role. */
    private Long idRole;

    /** Assuré. */
    private Long idPersonne;
    private String nomPersonne;
    private String prenomPersonne;
    private Calendar dateNaissancePersonne;

    /** Contrat produit. */
    private String libelleCommercial;
    private String produitAia;

    /** Contrat Garantie. */
    private String garantieAia;
    private Long idFamilleGarantie;

    /** Date d'adhésion au produit. */
    private Calendar dateEffet;

    /** Date de résiliation du produit. */
    private Calendar dateResiliation;

    /** Règlement. */
    private String frequenceReglement;
    private String modeReglement;

    /** Ordre d'affichage. */
    private int ordre;

    /** Booléen indiquant si le produit est issu d'un contrat collectif. */
    private boolean isFromContratCollectif;

    /**
     * Get the produitAia value.
     * @return the produitAia
     */
    public String getProduitAia() {
        return produitAia;
    }

    /**
     * Set the produitAia value.
     * @param produitAia the produitAia to set
     */
    public void setProduitAia(String produitAia) {
        this.produitAia = produitAia;
    }

    /**
     * Get the garantieAia value.
     * @return the garantieAia
     */
    public String getGarantieAia() {
        return garantieAia;
    }

    /**
     * Set the garantieAia value.
     * @param garantieAia the garantieAia to set
     */
    public void setGarantieAia(String garantieAia) {
        this.garantieAia = garantieAia;
    }

    /**
     * Get the dateEffet value.
     * @return the dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Set the dateEffet value.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Get the dateResiliation value.
     * @return the dateResiliation
     */
    public Calendar getDateResiliation() {
        return dateResiliation;
    }

    /**
     * Set the dateResiliation value.
     * @param dateResiliation the dateResiliation to set
     */
    public void setDateResiliation(Calendar dateResiliation) {
        this.dateResiliation = dateResiliation;
    }

    /**
     * Get the frequenceReglement value.
     * @return the frequenceReglement
     */
    public String getFrequenceReglement() {
        return frequenceReglement;
    }

    /**
     * Set the frequenceReglement value.
     * @param frequenceReglement the frequenceReglement to set
     */
    public void setFrequenceReglement(String frequenceReglement) {
        this.frequenceReglement = frequenceReglement;
    }

    /**
     * Get the modeReglement value.
     * @return the modeReglement
     */
    public String getModeReglement() {
        return modeReglement;
    }

    /**
     * Set the modeReglement value.
     * @param modeReglement the modeReglement to set
     */
    public void setModeReglement(String modeReglement) {
        this.modeReglement = modeReglement;
    }

    /**
     * Get the ordre value.
     * @return the ordre
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Set the ordre value.
     * @param ordre the ordre to set
     */
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    /**
     * Get the libelleCommercial value.
     * @return the libelleCommercial
     */
    public String getLibelleCommercial() {
        return libelleCommercial;
    }

    /**
     * Set the libelleCommercial value.
     * @param libelleCommercial the libelleCommercial to set
     */
    public void setLibelleCommercial(String libelleCommercial) {
        this.libelleCommercial = libelleCommercial;
    }

    /**
     * Get the isFromContratCollectif value.
     * @return the isFromContratCollectif
     */
    public boolean getIsFromContratCollectif() {
        return isFromContratCollectif;
    }

    /**
     * Set the isFromContratCollectif value.
     * @param isFromContratCollectif the isFromContratCollectif to set
     */
    public void setIsFromContratCollectif(boolean isFromContratCollectif) {
        this.isFromContratCollectif = isFromContratCollectif;
    }

    /**
     * Récupère la valeur de idPersonne.
     * @return la valeur de idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Définit la valeur de idPersonne.
     * @param idPersonne la nouvelle valeur de idPersonne
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Récupère la valeur de idRole.
     * @return la valeur de idRole
     */
    public Long getIdRole() {
        return idRole;
    }

    /**
     * Définit la valeur de idRole.
     * @param idRole la nouvelle valeur de idRole
     */
    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    /**
     * Récupère la valeur de idFamilleGarantie.
     * @return la valeur de idFamilleGarantie
     */
    public Long getIdFamilleGarantie() {
        return idFamilleGarantie;
    }

    /**
     * Définit la valeur de idFamilleGarantie.
     * @param idFamilleGarantie la nouvelle valeur de idFamilleGarantie
     */
    public void setIdFamilleGarantie(Long idFamilleGarantie) {
        this.idFamilleGarantie = idFamilleGarantie;
    }

    /**
     * Get the value of nomPersonne.
     * @return the nomPersonne
     */
    public String getNomPersonne() {
        return nomPersonne;
    }

    /**
     * Set the value of nomPersonne.
     * @param nomPersonne the nomPersonne to set
     */
    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    /**
     * Get the value of prenomPersonne.
     * @return the prenomPersonne
     */
    public String getPrenomPersonne() {
        return prenomPersonne;
    }

    /**
     * Set the value of prenomPersonne.
     * @param prenomPersonne the prenomPersonne to set
     */
    public void setPrenomPersonne(String prenomPersonne) {
        this.prenomPersonne = prenomPersonne;
    }

    /**
     * Get the value of dateNaissancePersonne.
     * @return the dateNaissancePersonne
     */
    public Calendar getDateNaissancePersonne() {
        return dateNaissancePersonne;
    }

    /**
     * Set the value of dateNaissancePersonne.
     * @param dateNaissancePersonne the dateNaissancePersonne to set
     */
    public void setDateNaissancePersonne(Calendar dateNaissancePersonne) {
        this.dateNaissancePersonne = dateNaissancePersonne;
    }

}
