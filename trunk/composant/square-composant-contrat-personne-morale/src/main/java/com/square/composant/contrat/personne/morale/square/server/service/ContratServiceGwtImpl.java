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
package com.square.composant.contrat.personne.morale.square.server.service;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.adherent.noyau.dto.adherent.contrat.ContratPersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosContratsPersonneMoraleDto;
import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.composant.contrat.personne.morale.square.client.model.ContratPersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.model.InfosContratsPersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.service.ContratServiceGwt;


/**
 * Implémentation de l'interface ContratServiceGwt.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ContratServiceGwtImpl implements ContratServiceGwt {

    /** Service Contrat. */
    private ContratService contratService;

    /** MapperDozerBean. */
    private MapperDozerBean mapperDozerBean;

    @Override
    public ContratPersonneMoraleModel getContratPersonneMorale(Long uidContrat) {
        final ContratPersonneMoraleDto contratDto = contratService.getContratPersonneMorale(uidContrat);
        return mapperDozerBean.map(contratDto, ContratPersonneMoraleModel.class);
    }

    @Override
    public InfosContratsPersonneMoraleModel getInfosContratPersonneMorale(Long uidPersonneMorale) {
        final InfosContratsPersonneMoraleDto infosContratDto = contratService.getInfosContratPersonneMorale(uidPersonneMorale);
        return mapperDozerBean.map(infosContratDto, InfosContratsPersonneMoraleModel.class);
    }

    /**
     * Définit la valeur de contratService.
     * @param contratService la nouvelle valeur de contratService
     */
    public void setContratService(ContratService contratService) {
        this.contratService = contratService;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

}
