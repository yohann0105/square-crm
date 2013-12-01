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
import java.util.List;

/**
 * Représente les infos de mise à jour d'un compte adhérent.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class InfosCompteAdherentDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 5807000742889591053L;

    /** Identifiant de l'adhérent. */
    private Long idAdherent;

    /** Mot de passe. */
    private String motDePasse;

    /** Première visite. */
    private Boolean isPremiereVisite;

    /** Affichage la page de nouveau service. */
    private Boolean afficheNouveauService;

    /** Liste des options du compte à mettre à jour. */
    private List<OptionAdherentDto> listeOptions;

    /**
     * Récupère la valeur de idAdherent.
     * @return la valeur de idAdherent
     */
    public Long getIdAdherent() {
        return idAdherent;
    }

    /**
     * Définit la valeur de idAdherent.
     * @param idAdherent la nouvelle valeur de idAdherent
     */
    public void setIdAdherent(Long idAdherent) {
        this.idAdherent = idAdherent;
    }

    /**
     * Récupère la valeur de motDePasse.
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
     * Récupère la valeur de isPremiereVisite.
     * @return la valeur de isPremiereVisite
     */
    public Boolean getIsPremiereVisite() {
        return isPremiereVisite;
    }

    /**
     * Définit la valeur de isPremiereVisite.
     * @param isPremiereVisite la nouvelle valeur de isPremiereVisite
     */
    public void setIsPremiereVisite(Boolean isPremiereVisite) {
        this.isPremiereVisite = isPremiereVisite;
    }

    /**
     * Récupère la valeur de afficheNouveauService.
     * @return la valeur de afficheNouveauService
     */
    public Boolean getAfficheNouveauService() {
        return afficheNouveauService;
    }

    /**
     * Définit la valeur de afficheNouveauService.
     * @param afficheNouveauService la nouvelle valeur de afficheNouveauService
     */
    public void setAfficheNouveauService(Boolean afficheNouveauService) {
        this.afficheNouveauService = afficheNouveauService;
    }

    /**
     * Récupère la valeur de listeOptions.
     * @return la valeur de listeOptions
     */
    public List<OptionAdherentDto> getListeOptions() {
        return listeOptions;
    }

    /**
     * Définit la valeur de listeOptions.
     * @param listeOptions la nouvelle valeur de listeOptions
     */
    public void setListeOptions(List<OptionAdherentDto> listeOptions) {
        this.listeOptions = listeOptions;
    }

}
