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
package com.square.composant.ged.square.client.view.listedetaillee;

import java.util.Date;
import java.util.List;

/**
 * DTO pour le transport d'informations d'une ligne entre la vue et le controller.
 * @author jgoncalves - SCUB
 */
public class LigneDocumentDto implements Comparable<LigneDocumentDto> {
    private String nom;
    private String url;
    private Date dateReception;
    private String sens;
    private List<String> tags;

    /**
     * Constructeur.
     * @param nom le nom
     * @param url l'url
     * @param dateReception la date de réception
     * @param sens le sens
     */
    public LigneDocumentDto(String nom, String url, Date dateReception, String sens) {
        this.nom = nom;
        this.url = url;
        this.dateReception = dateReception;
        this.sens = sens;
    }

    /**
     * Récupération de nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }
    /**
     * Définition de nom.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * Récupération de dateReception.
     * @return the dateReception
     */
    public Date getDateReception() {
        return dateReception;
    }
    /**
     * Définition de dateReception.
     * @param dateReception the dateReception to set
     */
    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
    }
    /**
     * Récupération de sens.
     * @return the sens
     */
    public String getSens() {
        return sens;
    }
    /**
     * Définition de sens.
     * @param sens the sens to set
     */
    public void setSens(String sens) {
        this.sens = sens;
    }
    /**
     * Récupération de tags.
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }
    /**
     * Définition de tags.
     * @param tags the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    /**
     * Récupération de url.
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * Définition de url.
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(LigneDocumentDto o) {
        if (o == null || dateReception == null) {
            return 1;
        }
        return -(dateReception.compareTo(o.getDateReception()));

    }
}
