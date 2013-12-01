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
package com.square.composant.fusion.square.client.exception;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

/**
 * Exception du contrôle d'intégrité d'une personne pour la fusion.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ControleIntegriteFusionExceptionGwt extends GwtRunTimeExceptionGwt {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -5271438329757097225L;

    /** Liste des erreurs. */
    private List<String> listeErreurs;

    /**
     * Constructeur par défaut.
     */
    public ControleIntegriteFusionExceptionGwt() {
        super();
    }

    /**
     * Constructeur avec liste des erreurs.
     * @param listeErreurs la liste des erreurs
     */
    public ControleIntegriteFusionExceptionGwt(List<String> listeErreurs) {
        this.listeErreurs = listeErreurs;
    }

    /**
     * Récupère la valeur de listeErreurs.
     * @return la valeur de listeErreurs
     */
    public List<String> getListeErreurs() {
        return listeErreurs;
    }

    /**
     * Définit la valeur de listeErreurs.
     * @param listeErreurs la nouvelle valeur de listeErreurs
     */
    public void setListeErreurs(List<String> listeErreurs) {
        this.listeErreurs = listeErreurs;
    }
}