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
package com.square.composant.tarificateur.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle regroupant les informations de l'expéditeur pour l'envoi d'un email.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class InfosExpediteurEnvoiEmailModel implements IsSerializable {

    /** L'expéditeur de l'email (nom et prénom). */
    private String expediteur;

    /** L'adresse email de l'expéditeur. */
    private String adresseEmailExpediteur;

    /**
     * Récupère la valeur de expediteur.
     * @return la valeur de expediteur
     */
    public String getExpediteur() {
        return expediteur;
    }

    /**
     * Définit la valeur de expediteur.
     * @param expediteur la nouvelle valeur de expediteur
     */
    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    /**
     * Récupère la valeur de adresseEmailExpediteur.
     * @return la valeur de adresseEmailExpediteur
     */
    public String getAdresseEmailExpediteur() {
        return adresseEmailExpediteur;
    }

    /**
     * Définit la valeur de adresseEmailExpediteur.
     * @param adresseEmailExpediteur la nouvelle valeur de adresseEmailExpediteur
     */
    public void setAdresseEmailExpediteur(String adresseEmailExpediteur) {
        this.adresseEmailExpediteur = adresseEmailExpediteur;
    }
}
