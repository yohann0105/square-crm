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
package com.square.composant.fusion.square.server.service;


import com.square.composant.fusion.square.client.model.ConstantesModel;
import com.square.composant.fusion.square.client.service.ConstantesMappingServiceGwt;
import com.square.core.service.interfaces.SquareMappingService;


/**
 * Implémentation de l'interface ConstantesMappingServiceGwt.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ConstantesMappingServiceGwtImpl implements ConstantesMappingServiceGwt {

    /** Service de mapping de square. */
    private SquareMappingService squareMappingService;

    @Override
    public ConstantesModel getConstantes() {
        final ConstantesModel constantesGwt = new ConstantesModel();
        constantesGwt.setProprietePersonneNumeroClient(squareMappingService.getProprietePersonneNumeroClient());
        constantesGwt.setProprietePersonneNom(squareMappingService.getProprietePersonneNom());
        constantesGwt.setProprietePersonnePrenom(squareMappingService.getProprietePersonnePrenom());
        constantesGwt.setProprietePersonneDateNaissance(squareMappingService.getProprietePersonneDateNaissance());
        constantesGwt.setProprietePersonneSegment(squareMappingService.getProprietePersonneSegment());
        constantesGwt.setProprietePersonneNature(squareMappingService.getProprietePersonneNature());
        constantesGwt.setProprietePersonneCodePostal(squareMappingService.getProprietePersonneCodePostal());
        constantesGwt.setProprietePersonneCommune(squareMappingService.getProprietePersonneCommune());
        constantesGwt.setProprietePersonneCommercial(squareMappingService.getProprietePersonneCommercial());
        constantesGwt.setProprietePersonneAgence(squareMappingService.getProprietePersonneAgence());
        constantesGwt.setProprietePersonneDateCreation(squareMappingService.getProprietePersonneDateCreation());
        constantesGwt.setIdNatureTelephoneFixe(squareMappingService.getIdNatureTelephoneFixe());
        return constantesGwt;
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }
}
