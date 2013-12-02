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
package com.square.composant.contrat.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO qui regroupe les flags vis à vis des contrats d'un assuré rattaché à un bénéficiaire en particulier.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class InfosContratsBeneficiaireModel implements IsSerializable {

    /**
     * Indique si le bénéficiaire est rattaché à au moins un contrat de l'assuré.
     */
    private boolean hasContrats;

    /**
     * Indique si le bénéficiaire est rattaché à au moins un contrat actif de l'assuré.
     */
    private boolean hasContratsActifs;

    /**
     * Getter function.
     * @return the hasContrats
     */
    public boolean isHasContrats() {
        return hasContrats;
    }

    /**
     * Getter function.
     * @return the hasContratsActifs
     */
    public boolean isHasContratsActifs() {
        return hasContratsActifs;
    }

    /**
     * Setter function.
     * @param hasContrats the hasContrats to set
     */
    public void setHasContrats(boolean hasContrats) {
        this.hasContrats = hasContrats;
    }

    /**
     * Setter function.
     * @param hasContratsActifs the hasContratsActifs to set
     */
    public void setHasContratsActifs(boolean hasContratsActifs) {
        this.hasContratsActifs = hasContratsActifs;
    }
}
