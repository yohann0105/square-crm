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
package com.square.client.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle regroupant les informations nécessaires avant la création d'une opportunité.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class InfosCreationOpportuniteModel implements IsSerializable {

    /** Booléen indiquant que la personne a déjà une opportunité. */
    private boolean hasOpportunite;

    /** Booléen indiquant si la personne est décédée. */
    private boolean isPersonneDecedee;

    /** Booléen indiquant que l'un des membres de la famille de la personne est de nature vivier (ou bénéficiaire vivier). */
    private boolean hasFamilleVivier;

    /**
     * Récupère la valeur de hasOpportunite.
     * @return la valeur de hasOpportunite
     */
    public boolean hasOpportunite() {
        return hasOpportunite;
    }

    /**
     * Définit la valeur de hasOpportunite.
     * @param hasOpportunite la nouvelle valeur de hasOpportunite
     */
    public void setHasOpportunite(boolean hasOpportunite) {
        this.hasOpportunite = hasOpportunite;
    }

    /**
     * Récupère la valeur de isPersonneDecedee.
     * @return la valeur de isPersonneDecedee
     */
    public boolean isPersonneDecedee() {
        return isPersonneDecedee;
    }

    /**
     * Définit la valeur de isPersonneDecedee.
     * @param personneDecedee la nouvelle valeur de isPersonneDecedee
     */
    public void setPersonneDecedee(boolean personneDecedee) {
        this.isPersonneDecedee = personneDecedee;
    }

    /**
     * Récupère la valeur de hasFamilleVivier.
     * @return la valeur de hasFamilleVivier
     */
    public boolean hasFamilleVivier() {
        return hasFamilleVivier;
    }

    /**
     * Définit la valeur de hasFamilleVivier.
     * @param hasFamilleVivier la nouvelle valeur de hasFamilleVivier
     */
    public void setHasFamilleVivier(boolean hasFamilleVivier) {
        this.hasFamilleVivier = hasFamilleVivier;
    }
}
