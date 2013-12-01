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
package com.square.tarificateur.noyau.bean.editique;

import org.scub.foundation.framework.base.dto.FichierDto;

/**
 * Bean englobant le DTO d'un fichier et son modèle.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class FichierModeleBean {

    /** Le DTO du fichier. */
    private FichierDto fichierDto;

    /** Le modèle du fichier. */
    private Long idModeleFichier;

    /**
     * Constructeur avec paramètres.
     * @param fichierDto le fichier
     * @param idModeleFichier le modèle du fichier
     */
    public FichierModeleBean(FichierDto fichierDto, Long idModeleFichier) {
        this.fichierDto = fichierDto;
        this.idModeleFichier = idModeleFichier;
    }

    /**
     * Récupère la valeur fichierDto.
     * @return the fichierDto
     */
    public FichierDto getFichierDto() {
        return fichierDto;
    }

    /**
     * Définit la valeur de fichierDto.
     * @param fichierDto the fichierDto to set
     */
    public void setFichierDto(FichierDto fichierDto) {
        this.fichierDto = fichierDto;
    }

    /**
     * Récupère la valeur idModeleFichier.
     * @return the idModeleFichier
     */
    public Long getIdModeleFichier() {
        return idModeleFichier;
    }

    /**
     * Définit la valeur de idModeleFichier.
     * @param idModeleFichier the idModeleFichier to set
     */
    public void setIdModeleFichier(Long idModeleFichier) {
        this.idModeleFichier = idModeleFichier;
    }

}
