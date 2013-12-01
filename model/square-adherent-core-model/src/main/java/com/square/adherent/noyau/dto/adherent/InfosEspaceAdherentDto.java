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
package com.square.adherent.noyau.dto.adherent;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * DTO encapsulant les informations de l'espace personne d'un adhérent.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class InfosEspaceAdherentDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -2474366815496129447L;

    /**
     * Indique si le compte de l'espace personnel est activé ou non.
     */
    private boolean connexionActive;

    /**
     * Date de première visite de l'adhérent sur le site Web.
     */
    private Calendar datePremiereVisite;

    /**
     * Date de dernière visite de l'adhérent sur le site Web.
     */
    private Calendar dateDerniereVisite;

    /**
     * Nombre de connexion de l'adhérent à son espace personnel sur le site Web.
     */
    private int nbVisites;

    /**
     * Date de modification des informations de l'adhérent.
     */
    private Calendar dateModificationOptions;

    /**
     * Numéro de l'adhérent.
     */
    private String numeroAdherent;

    /**
     * Adresse email de l'adhérent.
     */
    private String email;

    /**
     * Options de l'adhérent.
     */
    private List<OptionAdherentDto> listeOptions;

    /**
     * Getter function.
     * @return the connexionActive
     */
    public boolean isConnexionActive() {
        return connexionActive;
    }

    /**
     * Getter function.
     * @return the datePremiereVisite
     */
    public Calendar getDatePremiereVisite() {
        return datePremiereVisite;
    }

    /**
     * Getter function.
     * @return the dateDerniereVisite
     */
    public Calendar getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    /**
     * Getter function.
     * @return the nbVisites
     */
    public int getNbVisites() {
        return nbVisites;
    }

    /**
     * Getter function.
     * @return the dateModificationOptions
     */
    public Calendar getDateModificationOptions() {
        return dateModificationOptions;
    }

    /**
     * Getter function.
     * @return the numeroAdherent
     */
    public String getNumeroAdherent() {
        return numeroAdherent;
    }

    /**
     * Getter function.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter function.
     * @return the listeOptions
     */
    public List<OptionAdherentDto> getListeOptions() {
        return listeOptions;
    }

    /**
     * Setter function.
     * @param connexionActive the connexionActive to set
     */
    public void setConnexionActive(boolean connexionActive) {
        this.connexionActive = connexionActive;
    }

    /**
     * Setter function.
     * @param datePremiereVisite the datePremiereVisite to set
     */
    public void setDatePremiereVisite(Calendar datePremiereVisite) {
        this.datePremiereVisite = datePremiereVisite;
    }

    /**
     * Setter function.
     * @param dateDerniereVisite the dateDerniereVisite to set
     */
    public void setDateDerniereVisite(Calendar dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    /**
     * Setter function.
     * @param nbVisites the nbVisites to set
     */
    public void setNbVisites(int nbVisites) {
        this.nbVisites = nbVisites;
    }

    /**
     * Setter function.
     * @param dateModificationOptions the dateModificationOptions to set
     */
    public void setDateModificationOptions(Calendar dateModificationOptions) {
        this.dateModificationOptions = dateModificationOptions;
    }

    /**
     * Setter function.
     * @param numeroAdherent the numeroAdherent to set
     */
    public void setNumeroAdherent(String numeroAdherent) {
        this.numeroAdherent = numeroAdherent;
    }

    /**
     * Setter function.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter function.
     * @param listeOptions the listeOptions to set
     */
    public void setListeOptions(List<OptionAdherentDto> listeOptions) {
        this.listeOptions = listeOptions;
    }

}
