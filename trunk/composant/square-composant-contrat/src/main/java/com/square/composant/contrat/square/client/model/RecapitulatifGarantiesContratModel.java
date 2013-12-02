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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle récapitulatif des garanties souscrites dans un contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class RecapitulatifGarantiesContratModel implements IsSerializable {

    /**
     * Liste des bénéficiaires.
     */
    private List<GarantieBeneficiaireModel> listeBeneficiaires;

    /**
     * Liste des garanties.
     */
    private List<GarantieSimpleModel> listeGaranties;

    /**
     * Getter function.
     * @return the listeBeneficiaires
     */
    public List<GarantieBeneficiaireModel> getListeBeneficiaires() {
        if (listeBeneficiaires == null) {
            listeBeneficiaires = new ArrayList<GarantieBeneficiaireModel>();
        }
        return listeBeneficiaires;
    }

    /**
     * Getter function.
     * @return the listeGaranties
     */
    public List<GarantieSimpleModel> getListeGaranties() {
        if (listeGaranties == null) {
            listeGaranties = new ArrayList<GarantieSimpleModel>();
        }
        return listeGaranties;
    }

    /**
     * Setter function.
     * @param listeBeneficiaires the listeBeneficiaires to set
     */
    public void setListeBeneficiaires(List<GarantieBeneficiaireModel> listeBeneficiaires) {
        this.listeBeneficiaires = listeBeneficiaires;
    }

    /**
     * Setter function.
     * @param listeGaranties the listeGaranties to set
     */
    public void setListeGaranties(List<GarantieSimpleModel> listeGaranties) {
        this.listeGaranties = listeGaranties;
    }
}
