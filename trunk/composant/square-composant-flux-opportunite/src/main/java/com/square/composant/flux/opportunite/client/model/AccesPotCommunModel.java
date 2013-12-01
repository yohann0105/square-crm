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
package com.square.composant.flux.opportunite.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle pour les informations d'accès au pot commun.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AccesPotCommunModel implements IsSerializable {

    /** Booléen indiquant si la ressource a accès au pot commun. */
    private boolean hasAccesPotCommun = false;

    /** Booléen indiquant si toutes les actions rattachées au quota sont terminées. */
    private boolean isAllActionsQuotaTerminees = false;

    /**
     * Getter.
     * @return the hasAccesPotCommun
     */
    public boolean isHasAccesPotCommun() {
        return hasAccesPotCommun;
    }

    /**
     * Setter.
     * @param hasAccesPotCommun the hasAccesPotCommun to set
     */
    public void setHasAccesPotCommun(boolean hasAccesPotCommun) {
        this.hasAccesPotCommun = hasAccesPotCommun;
    }

    /**
     * Getter.
     * @return the isAllActionsQuotaTerminees
     */
    public boolean isAllActionsQuotaTerminees() {
        return isAllActionsQuotaTerminees;
    }

    /**
     * Setter.
     * @param allActionsQuotaTerminees the isAllActionsQuotaTerminees to set
     */
    public void setAllActionsQuotaTerminees(boolean allActionsQuotaTerminees) {
        this.isAllActionsQuotaTerminees = allActionsQuotaTerminees;
    }
}
