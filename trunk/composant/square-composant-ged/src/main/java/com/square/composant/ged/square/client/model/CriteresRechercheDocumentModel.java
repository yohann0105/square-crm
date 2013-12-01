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
package com.square.composant.ged.square.client.model;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Critères de recherche de documents.
 * @author jgoncalves - SCUB
 */
public class CriteresRechercheDocumentModel implements IsSerializable {

    /** Liste d'identifiants. */
    private List<String> ids;

    /** numéro de client. */
    private String numeroClient;

    /**
     * Récupération de ids.
     * @return the ids
     */
    public List<String> getIds() {
        return ids;
    }

    /**
     * Définition de ids.
     * @param ids the ids to set
     */
    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    /**
     * Récupération de numeroClient.
     * @return the numeroClient
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * Définition de numeroClient.
     * @param numeroClient the numeroClient to set
     */
    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }

}
