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
package com.square.tarificateur.noyau.bean;

import java.util.Calendar;
import java.util.List;

/**
 * Ligne de devis pour la tarification à partir de règle.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class LigneDevisPourRegle {

    /** Identifiant de la ligne de devis. */
    private Long identifiant;

    /** Identifiant beneficiaire. */
    private Long identifiantBeneficiaire;

    /** Age du bénéficiaire. */
    private Integer ageBeneficiaire;

    /** Role du bénéficiaire. */
    private String roleBeneficiaire;

    /** Index du bénéficiaire par rapport au role. */
    private Integer indexRoleBeneficiaire;

    /** Date effet de la ligne. */
    private Calendar dateEffet;

    /** Le produit associé. */
    private Integer identifiantProduit;

    /** Le code génération. */
    private String codeGeneration;

    /** Liste des lignes de devis liées. */
    private List<LigneDevisPourRegle> listeLignesLiees;

    /**
     * Définit la valeur de ageBeneficiaire.
     * @return la valeur de ageBeneficiaire
     */
    public Integer getAgeBeneficiaire() {
        return ageBeneficiaire;
    }

    /**
     * Définit la valeur de ageBeneficiaire.
     * @param ageBeneficiaire la nouvelle valeur de ageBeneficiaire
     */
    public void setAgeBeneficiaire(Integer ageBeneficiaire) {
        this.ageBeneficiaire = ageBeneficiaire;
    }

    /**
     * Définit la valeur de identifiantBeneficiaire.
     * @return la valeur de identifiantBeneficiaire
     */
    public Long getIdentifiantBeneficiaire() {
        return identifiantBeneficiaire;
    }

    /**
     * Définit la valeur de identifiantBeneficiaire.
     * @param identifiantBeneficiaire la nouvelle valeur de identifiantBeneficiaire
     */
    public void setIdentifiantBeneficiaire(Long identifiantBeneficiaire) {
        this.identifiantBeneficiaire = identifiantBeneficiaire;
    }

    /**
     * Définit la valeur de dateEffet.
     * @return la valeur de dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Définit la valeur de dateEffet.
     * @param dateEffet la nouvelle valeur de dateEffet
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Définit la valeur de identifiantProduit.
     * @return la valeur de identifiantProduit
     */
    public Integer getIdentifiantProduit() {
        return identifiantProduit;
    }

    /**
     * Définit la valeur de identifiantProduit.
     * @param identifiantProduit la nouvelle valeur de identifiantProduit
     */
    public void setIdentifiantProduit(Integer identifiantProduit) {
        this.identifiantProduit = identifiantProduit;
    }

    /**
     * Définit la valeur de identifiant.
     * @return la valeur de identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit la valeur de identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Définit la valeur de roleBeneficiaire.
     * @return la valeur de roleBeneficiaire
     */
    public String getRoleBeneficiaire() {
        return roleBeneficiaire;
    }

    /**
     * Définit la valeur de roleBeneficiaire.
     * @param roleBeneficiaire la nouvelle valeur de roleBeneficiaire
     */
    public void setRoleBeneficiaire(String roleBeneficiaire) {
        this.roleBeneficiaire = roleBeneficiaire;
    }

    /**
     * Définit la valeur de indexRoleBeneficiaire.
     * @return la valeur de indexRoleBeneficiaire
     */
    public Integer getIndexRoleBeneficiaire() {
        return indexRoleBeneficiaire;
    }

    /**
     * Définit la valeur de indexRoleBeneficiaire.
     * @param indexRoleBeneficiaire la nouvelle valeur de indexRoleBeneficiaire
     */
    public void setIndexRoleBeneficiaire(Integer indexRoleBeneficiaire) {
        this.indexRoleBeneficiaire = indexRoleBeneficiaire;
    }

    /**
     * Définit la valeur de listeLignesLiees.
     * @return la valeur de listeLignesLiees
     */
    public List<LigneDevisPourRegle> getListeLignesLiees() {
        return listeLignesLiees;
    }

    /**
     * Définit la valeur de listeLignesLiees.
     * @param listeLignesLiees la nouvelle valeur de listeLignesLiees
     */
    public void setListeLignesLiees(List<LigneDevisPourRegle> listeLignesLiees) {
        this.listeLignesLiees = listeLignesLiees;
    }

    /**
     * Récupération de codeGeneration.
     * @return the codeGeneration
     */
    public String getCodeGeneration() {
        return codeGeneration;
    }

    /**
     * Définition de codeGeneration.
     * @param codeGeneration the codeGeneration to set
     */
    public void setCodeGeneration(String codeGeneration) {
        this.codeGeneration = codeGeneration;
    }

}
