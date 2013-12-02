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
package com.square.adherent.noyau.dto.adherent.connexion;

import java.io.Serializable;

/**
 * DTO contenant les identifiants de connexion.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class IdentifiantsConnexionDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6418892397929746707L;

    /** Login. */
    private String login;

    /** Mot de passe. */
    private String motDePasse;

    /** Indique si le mot de passe est encrypté. */
    private boolean isMotDePasseEncrypte;

    /** Flag indiquant que c'est pour une demande de mot de passe. */
    private Boolean mettreAJourInfosConnexion = Boolean.TRUE;

    /** Constructeur. */
    public IdentifiantsConnexionDto() { }

    /**
     * Constructeur avec paramètres.
     * @param login le login
     * @param motDePasse le mot de passe
     */
    public IdentifiantsConnexionDto(String login, String motDePasse) {
        this.login = login;
        this.motDePasse = motDePasse;
    }

    /**
     * Retourne la valeur de login.
     * @return la valeur de login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Définit la valeur de login.
     * @param login la nouvelle valeur de login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retourne la valeur de motDePasse.
     * @return la valeur de motDePasse
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Définit la valeur de motDePasse.
     * @param motDePasse la nouvelle valeur de motDePasse
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Récupère la valeur de mettreAJourInfosConnexion.
     * @return la valeur de mettreAJourInfosConnexion
     */
    public Boolean getMettreAJourInfosConnexion() {
        return mettreAJourInfosConnexion;
    }

    /**
     * Définit la valeur de mettreAJourInfosConnexion.
     * @param mettreAJourInfosConnexion la nouvelle valeur de mettreAJourInfosConnexion
     */
    public void setMettreAJourInfosConnexion(Boolean mettreAJourInfosConnexion) {
        this.mettreAJourInfosConnexion = mettreAJourInfosConnexion;
    }

    /**
     * Getter function.
     * @return the isMotDePasseEncrypte
     */
    public boolean isMotDePasseEncrypte() {
        return isMotDePasseEncrypte;
    }

    /**
     * Setter function.
     * @param newIsMotDePasseEncrypte the newIsMotDePasseEncrypte to set
     */
    public void setMotDePasseEncrypte(boolean newIsMotDePasseEncrypte) {
        this.isMotDePasseEncrypte = newIsMotDePasseEncrypte;
    }

}
