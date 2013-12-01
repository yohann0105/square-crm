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
package com.square.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

/**
 * Entité persistante modélisant les personnes morales.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@Entity
@Table(name = "DATA_PERSONNE_MORALE")
@PrimaryKeyJoinColumn(name = "PERSONNE_UID")
@ForeignKey(name = "FK_DATA_PERSONNE_MORALE_DATA_PERSONNE_PERSONNE_UID")
@Indexed
public class PersonneMorale extends Personne {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 4960837433270827785L;

    /**
     * Enseigne commercial.
     */
    @Column(name = "PERSONNE_ENSEIGNE_COMMERCIAL")
    @Field(index = Index.TOKENIZED)
    private String enseigneCommercial;

    /**
     * Code ape.
     */
    @Column(name = "PERSONNE_CODE_APE")
    private String codeApe;

    /**
     * Numéro siret.
     */
    @Column(name = "PERSONNE_NUM_SIRET")
    @Field(index = Index.TOKENIZED)
    private String numSiret;

    /**
     * Raison sociale.
     */
    @Column(name = "PERSONNE_RAISON_SOCIALE")
    @Fields({@Field(index = Index.TOKENIZED), @Field(name = "triRaisonSociale", index = Index.UN_TOKENIZED, store = Store.YES) })
    private String raisonSociale;

    /**
     * Capital social.
     */
    @Column(name = "PERSONNE_CAPITAL_SOC")
    private Float capitalSocial;

    /**
     * Chiffre d'affaires.
     */
    @Column(name = "PERSONNE_CHIFFRE_AFFAIRE")
    private Float chiffreAffaires;

    /**
     * Activité de la société.
     */
    @Column(name = "PERSONNE_ACTIVITE")
    private String activite;

    /**
     * Efffectif de la société.
     */
    @Column(name = "PERSONNE_EFFECTIF_TOTAL")
    private Integer effectif;

    /**
     * Nature de la personne morale.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_NATURE_UID")
    @ForeignKey(name = "FK_DATA_PERSONNE_MORALE_DIM_PERSONNE_MORALE_NATURE_PERSONNE_NATURE_UID")
    @IndexedEmbedded
    private PersonneMoraleNature naturePersonneMorale;

    /**
     * Forme juridique de la personne.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_FORME_JURIDIQUE_UID")
    @IndexedEmbedded
    @ForeignKey(name = "FK_DATA_PERSONNE_MORALE_DIM_PERSONNE_FORME_JURIDIQUE_PERSONNE_FORME_JURIDIQUE_UID")
    private FormeJuridique formeJuridique;

    /**
     * Retourne la valeur de enseigneCommercial.
     * @return the enseigneCommercial
     */
    public String getEnseigneCommercial() {
        return enseigneCommercial;
    }

    /**
     * Modifie la valeur de enseigneCommercial.
     * @param enseigneCommercial the enseigneCommercial to set
     */
    public void setEnseigneCommercial(String enseigneCommercial) {
        this.enseigneCommercial = enseigneCommercial;
    }

    /**
     * Retourne la valeur de codeApe.
     * @return the codeApe
     */
    public String getCodeApe() {
        return codeApe;
    }

    /**
     * Modifie la valeur de codeApe.
     * @param codeApe the codeApe to set
     */
    public void setCodeApe(String codeApe) {
        this.codeApe = codeApe;
    }

    /**
     * Retourne la valeur de numSiret.
     * @return the numSiret
     */
    public String getNumSiret() {
        return numSiret;
    }

    /**
     * Modifie la valeur de numSiret.
     * @param numSiret the numSiret to set
     */
    public void setNumSiret(String numSiret) {
        this.numSiret = numSiret;
    }

    /**
     * Retourne la valeur de raisonSociale.
     * @return the raisonSociale
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * Modifie la valeur de raisonSociale.
     * @param raisonSociale the raisonSociale to set
     */
    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    /**
     * Retourne la valeur de activite.
     * @return the activite
     */
    public String getActivite() {
        return activite;
    }

    /**
     * Modifie la valeur de activite.
     * @param activite the activite to set
     */
    public void setActivite(String activite) {
        this.activite = activite;
    }

    /**
     * Retourne la valeur de effectif.
     * @return the effectif
     */
    public Integer getEffectif() {
        return effectif;
    }

    /**
     * Modifie la valeur de effectif.
     * @param effectif the effectif to set
     */
    public void setEffectif(Integer effectif) {
        this.effectif = effectif;
    }

    /**
     * Renvoi la valeur de naturePersonneMorale.
     * @return naturePersonneMorale
     */
    public PersonneMoraleNature getNaturePersonneMorale() {
        return naturePersonneMorale;
    }

    /**
     * Modifie naturePersonneMorale.
     * @param naturePersonneMorale la nouvelle valeur de naturePersonneMorale
     */
    public void setNaturePersonneMorale(PersonneMoraleNature naturePersonneMorale) {
        this.naturePersonneMorale = naturePersonneMorale;
    }

    /**
     * Renvoi la valeur de formeJuridique.
     * @return formeJuridique
     */
    public FormeJuridique getFormeJuridique() {
        return formeJuridique;
    }

    /**
     * Modifie formeJuridique.
     * @param formeJuridique la nouvelle valeur de formeJuridique
     */
    public void setFormeJuridique(FormeJuridique formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    /**
     * Renvoi la valeur de capitalSocial.
     * @return capitalSocial
     */
    public Float getCapitalSocial() {
        return capitalSocial;
    }

    /**
     * Modifie capitalSocial.
     * @param capitalSocial la nouvelle valeur de capitalSocial
     */
    public void setCapitalSocial(Float capitalSocial) {
        this.capitalSocial = capitalSocial;
    }

    /**
     * Renvoi la valeur de chiffreAffaires.
     * @return chiffreAffaires
     */
    public Float getChiffreAffaires() {
        return chiffreAffaires;
    }

    /**
     * Modifie chiffreAffaires.
     * @param chiffreAffaires la nouvelle valeur de chiffreAffaires
     */
    public void setChiffreAffaires(Float chiffreAffaires) {
        this.chiffreAffaires = chiffreAffaires;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PersonneMorale)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
