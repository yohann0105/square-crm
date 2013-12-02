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
package com.square.client.gwt.server.service;

import java.util.List;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;
import org.springframework.security.context.SecurityContextHolder;

import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.RessourceCriteresRechercheModel;
import com.square.client.gwt.client.model.RessourceModel;
import com.square.client.gwt.client.service.RessourceServiceGwt;
import com.square.core.model.dto.RessourceCriteresRechercheDto;
import com.square.core.model.dto.RessourceRechercheDto;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.service.interfaces.RessourceService;

/**
 * Implémentation du service synchrone GWT des ressources.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class RessourceServiceGwtImpl implements RessourceServiceGwt {

    private DimensionService dimensionService;

    private MapperDozerBean mapperDozerBean;

    private RessourceService ressourceService;

    /**
     * {@inheritDoc}
     */
    @Override
    public DimensionRessourceModel getRessourceConnectee() {
        final DimensionRessourceModel ressource = mapperDozerBean.map(dimensionService.getRessourceConnectee(), DimensionRessourceModel.class);
        ressource.setLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return ressource;
    }

    @Override
    public RessourceModel rechercherRessourceParId(Long id) {
        return mapperDozerBean.map(ressourceService.rechercherRessourceParIdentifiant(id), RessourceModel.class);
    }

    @Override
    public RemotePagingResultsGwt<RessourceModel> rechercherRessourceFullTextParCriteres(RemotePagingCriteriasGwt<RessourceCriteresRechercheModel> criteres) {
        final RessourceCriteresRechercheDto criterias = mapperDozerBean.map(criteres.getCriterias(), RessourceCriteresRechercheDto.class);
        final RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<RessourceCriteresRechercheDto>(criterias, criteres.getFirstResult(), criteres.getMaxResult());
        final List<RemotePagingSort> listSort = mapperDozerBean.mapList(criteres.getListeSorts(), RemotePagingSort.class);
        criteresDto.setListeSorts(listSort);
        final RemotePagingResultsDto<RessourceRechercheDto> resultatsDto = ressourceService.rechercherRessourceFullTextParCriteres(criteresDto);
        final RemotePagingResultsGwt<RessourceModel> resultats = new RemotePagingResultsGwt<RessourceModel>();
        final List<RessourceModel> listeRessourcesGwt = mapperDozerBean.mapList(resultatsDto.getListResults(), RessourceModel.class);
        resultats.setListResults(listeRessourcesGwt);
        resultats.setTotalResults(resultatsDto.getTotalResults());
        return resultats;
    }

    @Override
    public List<Long> rechercherIdsRessourcesFullTextParCriteres(RessourceCriteresRechercheModel criteres) {
        final RessourceCriteresRechercheDto criteresDto = mapperDozerBean.map(criteres, RessourceCriteresRechercheDto.class);
        return ressourceService.rechercherIdsRessourcesFullTextParCriteres(criteresDto);
    }

    /**
     * Setter function.
     * @param dimensionService the dimensionService to set
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * Setter function.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Setter function.
     * @param ressourceService the ressourceService to set
     */
    public void setRessourceService(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

}
