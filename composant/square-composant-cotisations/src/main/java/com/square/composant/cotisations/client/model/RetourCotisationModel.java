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
package com.square.composant.cotisations.client.model;

import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model qui représente le retour de cotisation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class RetourCotisationModel implements IsSerializable {

    private static final long serialVersionUID = -6999015300187638833L;

    /** Cotisations. */
    private RemotePagingResultsGwt<CotisationModel> resultatsCotisation;

    /** Solde. */
    private Float solde;

    /**
     * Get the resultatsCotisation value.
     * @return the resultatsCotisation
     */
    public RemotePagingResultsGwt<CotisationModel> getResultatsCotisation() {
        return resultatsCotisation;
    }

    /**
     * Set the resultatsCotisation value.
     * @param resultatsCotisation the resultatsCotisation to set
     */
    public void setResultatsCotisation(RemotePagingResultsGwt<CotisationModel> resultatsCotisation) {
        this.resultatsCotisation = resultatsCotisation;
    }

    /**
     * Get the solde value.
     * @return the solde
     */
    public Float getSolde() {
        return solde;
    }

    /**
     * Set the solde value.
     * @param solde the solde to set
     */
    public void setSolde(Float solde) {
        this.solde = solde;
    }

}
