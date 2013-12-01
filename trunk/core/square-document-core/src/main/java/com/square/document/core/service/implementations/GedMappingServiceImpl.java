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
package com.square.document.core.service.implementations;

import com.square.document.core.service.interfaces.GedMappingService;

/**
 * .
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public class GedMappingServiceImpl implements GedMappingService {

    private String cheminRepertoire;

    private String nodeRefCategorieDocumentsAvenant;

    private String nodeRefCategorieDocumentsBulletinAdhesion;

    private String nodeRefCategorieDocumentsLettreAnnulation;

    private String sensEntrant;

    private String sensSortant;

    @Override
    public String getNodeRefCategorieDocumentsAvenant() {
        return nodeRefCategorieDocumentsAvenant;
    }

    @Override
    public String getNodeRefCategorieDocumentsBulletinAdhesion() {
        return nodeRefCategorieDocumentsBulletinAdhesion;
    }

    @Override
    public String getNodeRefCategorieDocumentsLettreAnnulation() {
        return nodeRefCategorieDocumentsLettreAnnulation;
    }

    @Override
    public String getSensEntrant() {
        return sensEntrant;
    }

    @Override
    public String getSensSortant() {
        return sensSortant;
    }

    @Override
    public String getCheminRepertoire() {
        return cheminRepertoire;
    }

    /**
     * Repertoire du chemin.
     * @param chemin le chemin
     */
    public void setCheminRepertoire(String chemin) {
        this.cheminRepertoire = chemin;
    }

    /**
     * Set the value of nodeRefCategorieDocumentsAvenant.
     * @param nodeRefCategorieDocumentsAvenant the nodeRefCategorieDocumentsAvenant to set
     */
    public void setNodeRefCategorieDocumentsAvenant(String nodeRefCategorieDocumentsAvenant) {
        this.nodeRefCategorieDocumentsAvenant = nodeRefCategorieDocumentsAvenant;
    }

    /**
     * Set the value of nodeRefCategorieDocumentsBulletinAdhesion.
     * @param nodeRefCategorieDocumentsBulletinAdhesion the nodeRefCategorieDocumentsBulletinAdhesion to set
     */
    public void setNodeRefCategorieDocumentsBulletinAdhesion(String nodeRefCategorieDocumentsBulletinAdhesion) {
        this.nodeRefCategorieDocumentsBulletinAdhesion = nodeRefCategorieDocumentsBulletinAdhesion;
    }

    /**
     * Set the value of nodeRefCategorieDocumentsLettreAnnulation.
     * @param nodeRefCategorieDocumentsLettreAnnulation the nodeRefCategorieDocumentsLettreAnnulation to set
     */
    public void setNodeRefCategorieDocumentsLettreAnnulation(String nodeRefCategorieDocumentsLettreAnnulation) {
        this.nodeRefCategorieDocumentsLettreAnnulation = nodeRefCategorieDocumentsLettreAnnulation;
    }

    /**
     * Set the value of sensEntrant.
     * @param sensEntrant the sensEntrant to set
     */
    public void setSensEntrant(String sensEntrant) {
        this.sensEntrant = sensEntrant;
    }

    /**
     * Set the value of sensSortant.
     * @param sensSortant the sensSortant to set
     */
    public void setSensSortant(String sensSortant) {
        this.sensSortant = sensSortant;
    }

}
