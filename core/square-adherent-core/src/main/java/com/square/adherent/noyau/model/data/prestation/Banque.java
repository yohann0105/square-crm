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
package com.square.adherent.noyau.model.data.prestation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité persistante modélisant une Banque.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
@Entity
@Table(name = "DATA_BANQUE")
public class Banque implements Serializable {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -8338283645592232587L;

    /** Identifiant. */
    @Id
    @Column(name = "BANQUE_UID")
    private Long id;

    /**
     * Domiciliation de la banque.
     */
    @Column(name = "BANQUE_DOMICILIATION")
    private String domiciliation;

    /**
     * numero de compte.
     */
    @Column(name = "BANQUE_COMPTE")
    private String compte;

    /** Composant du RIB : code de la banque. */
    @Column(name = "BANQUE_CODE")
    private String codeBanque;

    /** Composant du RIB : code du guichet. */
    @Column(name = "BANQUE_GUICHET")
    private String codeGuichet;

    /** Composant du RIB : clé. */
    @Column(name = "BANQUE_CLE_RIB")
    private String cle;

    /** UID du propriétaire du compte. */
    @Column(name = "BANQUE_PERSONNE_UID")
    private Long uidProprietaireCompte;

    /**
     * Recuperer la valeur.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Fixer la valeur.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Recuperer la valeur.
     * @return the domiciliation
     */
    public String getDomiciliation() {
        return domiciliation;
    }

    /**
     * Fixer la valeur.
     * @param domiciliation the domiciliation to set
     */
    public void setDomiciliation(String domiciliation) {
        this.domiciliation = domiciliation;
    }

    /**
     * Recuperer la valeur.
     * @return the compte
     */
    public String getCompte() {
        return compte;
    }

    /**
     * Fixer la valeur.
     * @param compte the compte to set
     */
    public void setCompte(String compte) {
        this.compte = compte;
    }

    /**
     * Récupère la valeur de codeBanque.
     * @return la valeur de codeBanque
     */
    public String getCodeBanque() {
        return codeBanque;
    }

    /**
     * Définit la valeur de codeBanque.
     * @param codeBanque la nouvelle valeur de codeBanque
     */
    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    /**
     * Récupère la valeur de codeGuichet.
     * @return la valeur de codeGuichet
     */
    public String getCodeGuichet() {
        return codeGuichet;
    }

    /**
     * Définit la valeur de codeGuichet.
     * @param codeGuichet la nouvelle valeur de codeGuichet
     */
    public void setCodeGuichet(String codeGuichet) {
        this.codeGuichet = codeGuichet;
    }

    /**
     * Récupère la valeur de cle.
     * @return la valeur de cle
     */
    public String getCle() {
        return cle;
    }

    /**
     * Définit la valeur de cle.
     * @param cle la nouvelle valeur de cle
     */
    public void setCle(String cle) {
        this.cle = cle;
    }

    /**
     * Récupère la valeur de uidProprietaireCompte.
     * @return la valeur de uidProprietaireCompte
     */
    public Long getUidProprietaireCompte() {
        return uidProprietaireCompte;
    }

    /**
     * Définit la valeur de uidProprietaireCompte.
     * @param uidProprietaireCompte la nouvelle valeur de uidProprietaireCompte
     */
    public void setUidProprietaireCompte(Long uidProprietaireCompte) {
        this.uidProprietaireCompte = uidProprietaireCompte;
    }
}
