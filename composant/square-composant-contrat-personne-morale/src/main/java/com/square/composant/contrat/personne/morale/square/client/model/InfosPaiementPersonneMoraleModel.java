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
 * Modèle regroupant les informations de paiement des cotisations d'un contrat d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class InfosPaiementPersonneMoraleModel extends InfosPaiementModel implements IsSerializable {

    /** Type d'échéance. */
    private String typeEcheance;

    /** Composant du RIB : code de la banque. */
    private String codeBanque;

    /** Composant du RIB : code du guichet. */
    private String codeGuichet;

    /** Composant du RIB : code du compte. */
    private String codeCompte;

    /** Composant du RIB : clé. */
    private String cle;

    /**
     * Récupère la valeur de typeEcheance.
     * @return la valeur de typeEcheance
     */
    public String getTypeEcheance() {
        return typeEcheance;
    }

    /**
     * Définit la valeur de typeEcheance.
     * @param typeEcheance la nouvelle valeur de typeEcheance
     */
    public void setTypeEcheance(String typeEcheance) {
        this.typeEcheance = typeEcheance;
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
     * Récupère la valeur de codeCompte.
     * @return la valeur de codeCompte
     */
    public String getCodeCompte() {
        return codeCompte;
    }

    /**
     * Définit la valeur de codeCompte.
     * @param codeCompte la nouvelle valeur de codeCompte
     */
    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
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
}
