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
package com.square.composant.emails.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO GWT représentant les critères de recherche pour les utilisateurs.
 * @author nnadeau - SCUB
 */
public class UtilisateurRechercheQueryModel implements IsSerializable {

    /** Critère sur le nom, le prénom ou l'email de l'utilisateur. */
    private String critereEmailNomPrenom;

    /** Pagination. */
    private PagingModel pagination;

    /** Tri sur le nom. */
    private OrderByModel orderBy;

    /**
     * Get the critereEmailNomPrenom value.
     * @return the critereEmailNomPrenom
     */
    public String getCritereEmailNomPrenom() {
        return critereEmailNomPrenom;
    }

    /**
     * Set the critereEmailNomPrenom value.
     * @param critereEmailNomPrenom the critereEmailNomPrenom to set
     */
    public void setCritereEmailNomPrenom(String critereEmailNomPrenom) {
        this.critereEmailNomPrenom = critereEmailNomPrenom;
    }

    /**
     * Get the pagination value.
     * @return the pagination
     */
    public PagingModel getPagination() {
        return pagination;
    }

    /**
     * Set the pagination value.
     * @param pagination the pagination to set
     */
    public void setPagination(PagingModel pagination) {
        this.pagination = pagination;
    }

    /**
     * Get the orderBy value.
     * @return the orderBy
     */
    public OrderByModel getOrderBy() {
        return orderBy;
    }

    /**
     * Set the orderBy value.
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(OrderByModel orderBy) {
        this.orderBy = orderBy;
    }
}
