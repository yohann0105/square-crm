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
package com.square.composant.prestations.square.server.service;

import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.composant.prestations.square.client.model.ConstantesPrestationModel;
import com.square.composant.prestations.square.client.service.ConstantesPrestationServiceGwt;

/**
 * Implémentation de l'interface ConstantesPrestationServiceGwt.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ConstantesPrestationServiceGwtImpl implements ConstantesPrestationServiceGwt {

    /** Service de mapping de l'ecm. */
    private AdherentMappingService adherentMappingService;

    @Override
    public ConstantesPrestationModel chargerConstantesPrestations() {
        final ConstantesPrestationModel constantes = new ConstantesPrestationModel();
        constantes.setOrderDecompteByDateSoin(adherentMappingService.getOrderDecompteByDateSoin());
        constantes.setOrderDecompteByBeneficiaire(adherentMappingService.getOrderDecompteByBeneficiaire());
        constantes.setOrderDecompteByActe(adherentMappingService.getOrderDecompteByActe());
        constantes.setOrderDecompteByDepense(adherentMappingService.getOrderDecompteByDepense());
        constantes.setOrderDecompteByBase(adherentMappingService.getOrderDecompteByBase());
        constantes.setOrderDecompteByTaux(adherentMappingService.getOrderDecompteByTaux());
        constantes.setOrderDecompteByRbtRO(adherentMappingService.getOrderDecompteByRbtRO());
        constantes.setOrderDecompteByRbtSmatis(adherentMappingService.getOrderDecompteByRbtSmatis());
        constantes.setOrderDecompteByRbtProf(adherentMappingService.getOrderDecompteByRbtProf());
        constantes.setOrderDecompteByRaC(adherentMappingService.getOrderDecompteByRaC());
        constantes.setIdOrigineDecompteIndu(adherentMappingService.getIdOrigineDecompteIndu());
        constantes.setIdStatutPaiementDecompteNonPaye(adherentMappingService.getIdStatutPaiementDecompteNonPaye());
        constantes.setIdStatutPaiementDecomptePaye(adherentMappingService.getIdStatutPaiementDecomptePaye());
        constantes.setIdNatureReglementTiersSante(adherentMappingService.getIdNatureReglementTiersSante());
        return constantes;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

}
