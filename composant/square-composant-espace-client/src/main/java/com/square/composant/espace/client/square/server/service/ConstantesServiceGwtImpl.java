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
package com.square.composant.espace.client.square.server.service;

import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.composant.espace.client.square.client.model.ConstantesModel;
import com.square.composant.espace.client.square.client.service.ConstantesServiceGwt;

/**
 * Implémentation serveur des services GWT pour les constantes.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class ConstantesServiceGwtImpl implements ConstantesServiceGwt {

    /** Service de mapping des adhérents. */
    private AdherentMappingService adherentMappingService;

    @Override
    public ConstantesModel getConstantes() {
        final ConstantesModel constantes = new ConstantesModel();
        constantes.setIdStatutContratEnCours(adherentMappingService.getIdStatutContratEnCours());
        constantes.setIdStatutContratResilie(adherentMappingService.getIdStatutContratResilie());
        constantes.setIdSegmentCollectif(adherentMappingService.getIdSegmentCollectif());
        constantes.setIdTypeOptionEnvoiMutuellementEmail(adherentMappingService.getIdTypeOptionEnvoiMutuellementEmail());
        constantes.setIdTypeOptionEnvoiRelevesPrestationEmail(adherentMappingService.getIdTypeOptionEnvoiRelevesPrestationEmail());
        constantes.setIdTypeOptionEnvoiSms(adherentMappingService.getIdTypeOptionEnvoiSms());
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
