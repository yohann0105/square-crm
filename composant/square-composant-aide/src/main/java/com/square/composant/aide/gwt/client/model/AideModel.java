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
package com.square.composant.aide.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * implémentation de dto GWT de l'aide.
 * @author Ksouri MohamedAli - SCUB
 */
public class AideModel implements IsSerializable {
    private Long id;

    private String text;

    private Long idComposant;

    /**
     * modifie l'id de l'aide.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * récupére l'id de l'aide.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * modifie le texte.
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * récupére le texte de l'aide.
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * modifie l'id composant.
     * @param idComposant the idComposant to set
     */
    public void setIdComposant(Long idComposant) {
        this.idComposant = idComposant;
    }

    /**
     * récupére l'id de composant.
     * @return the idComposant
     */
    public Long getIdComposant() {
        return idComposant;
    }
}
