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
package com.square.adherent.noyau.dto.adherent.contrat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO récapitulatif des garanties souscrites dans un contrat.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class RecapitulatifGarantiesContratDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -2121990836140724177L;

    /**
     * Liste des bénéficiaires.
     */
    private List<GarantieBeneficiaireDto> listeBeneficiaires;

    /**
     * Liste des garanties.
     */
    private List<GarantieSimpleDto> listeGaranties;

    /**
     * Getter function.
     * @return the listeBeneficiaires
     */
    public List<GarantieBeneficiaireDto> getListeBeneficiaires() {
        if (listeBeneficiaires == null) {
            listeBeneficiaires = new ArrayList<GarantieBeneficiaireDto>();
        }
        return listeBeneficiaires;
    }

    /**
     * Getter function.
     * @return the listeGaranties
     */
    public List<GarantieSimpleDto> getListeGaranties() {
        if (listeGaranties == null) {
            listeGaranties = new ArrayList<GarantieSimpleDto>();
        }
        return listeGaranties;
    }

    /**
     * Setter function.
     * @param listeBeneficiaires the listeBeneficiaires to set
     */
    public void setListeBeneficiaires(List<GarantieBeneficiaireDto> listeBeneficiaires) {
        this.listeBeneficiaires = listeBeneficiaires;
    }

    /**
     * Setter function.
     * @param listeGaranties the listeGaranties to set
     */
    public void setListeGaranties(List<GarantieSimpleDto> listeGaranties) {
        this.listeGaranties = listeGaranties;
    }
}
