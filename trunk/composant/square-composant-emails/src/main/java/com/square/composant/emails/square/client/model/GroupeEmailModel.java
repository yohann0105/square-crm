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

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO GWT représentant un groupe d'email.
 * @author nnadeau - SCUB
 */
public class GroupeEmailModel implements IsSerializable {

    /** Identifiant. */
    private Long id;

    /** GroupMessageId. */
    private String groupeMessageId;

    /** Liste des emails. */
    private List<EmailModel> listeEmails;

    /**
     * Get the id value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id value.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the groupeMessageId value.
     * @return the groupeMessageId
     */
    public String getGroupeMessageId() {
        return groupeMessageId;
    }

    /**
     * Set the groupeMessageId value.
     * @param groupeMessageId the groupeMessageId to set
     */
    public void setGroupeMessageId(String groupeMessageId) {
        this.groupeMessageId = groupeMessageId;
    }

    /**
     * Get the listeEmails value.
     * @return the listeEmails
     */
    public List<EmailModel> getListeEmails() {
        return listeEmails;
    }

    /**
     * Set the listeEmails value.
     * @param listeEmails the listeEmails to set
     */
    public void setListeEmails(List<EmailModel> listeEmails) {
        this.listeEmails = listeEmails;
    }
}
