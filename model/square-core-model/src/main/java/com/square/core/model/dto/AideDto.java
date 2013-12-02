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
package com.square.core.model.dto;

import java.io.Serializable;

/**
 * dto de l'aide en ligne.
 */
public class AideDto implements Serializable {
    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 6946472967513404005L;

    /** Identifiant de l'aide. */
    private Long id;

    /** Identifiant du composant relatif à l'aide. */
    private Long idComposant;

    /** Texte de l'aide. */
    private String text;

    /**
     * Modifie l'id de l'aide.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoie l'id de l'aide.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie le texte de l'aide.
     * @param aideText the text to set
     */
    public void setText(String aideText) {
        this.text = aideText;
    }

    /**
     * Renvoie le texte de l'aide.
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Modifie d'identifiant du composant associé à l'aide.
     * @param idComposant the idComposant to set
     */
    public void setIdComposant(Long idComposant) {
        this.idComposant = idComposant;
    }

    /**
     * Récupère l'identifiant du composant associé à l'aide.
     * @return the idComposant
     */
    public Long getIdComposant() {
        return idComposant;
    }

}
