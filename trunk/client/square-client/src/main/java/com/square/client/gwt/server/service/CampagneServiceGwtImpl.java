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

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.exception.AccessDeniedExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;
import org.springframework.security.AccessDeniedException;

import com.square.client.gwt.client.model.CampagneCriteresRechercheLibelleModel;
import com.square.client.gwt.client.model.CampagneCriteresRechercheModel;
import com.square.client.gwt.client.model.CampagneModel;
import com.square.client.gwt.client.model.CampagneRechercheModel;
import com.square.client.gwt.client.service.CampagneServiceGwt;
import com.square.core.model.dto.CampagneCriteresRechercheDto;
import com.square.core.model.dto.CampagneCriteresRechercheLibelle;
import com.square.core.model.dto.CampagneDto;
import com.square.core.model.dto.CampagneRechercheDto;
import com.square.core.service.interfaces.CampagneService;

/**
 * Implémentation serveur des services GWT pour les services des campagnes.
 * @author cblanchard - SCUB
 */
public class CampagneServiceGwtImpl implements CampagneServiceGwt {

    /** Le service. */
    private CampagneService campagneService;

    /** Le mapper. */
    private MapperDozerBean mapperDozerBean;

    @Override
    public CampagneModel creerCampagne(CampagneModel campagneModel) {
        try {
			final CampagneDto campagneDto = mapperDozerBean.map(campagneModel, CampagneDto.class);
			final CampagneDto resultDto = campagneService.creerCampagne(campagneDto);
			return mapperDozerBean.map(resultDto, CampagneModel.class);
		} catch (AccessDeniedException e) {
			throw new AccessDeniedExceptionGwt();
		}
    }

    @Override
    public CampagneModel modifierCampagne(CampagneModel campagneModel) {
        try {
			final CampagneDto campagneDto = mapperDozerBean.map(campagneModel, CampagneDto.class);
			final CampagneDto resultDto = campagneService.modifierCampagne(campagneDto);
			return mapperDozerBean.map(resultDto, CampagneModel.class);
		} catch (AccessDeniedException e) {
			throw new AccessDeniedExceptionGwt();
		}
    }

    @Override
    public RemotePagingResultsGwt<CampagneRechercheModel> rechercherCampagnesParCriteres(RemotePagingCriteriasGwt<CampagneCriteresRechercheModel> criteres) {
        final CampagneCriteresRechercheDto criterias = mapperDozerBean.map(criteres.getCriterias(), CampagneCriteresRechercheDto.class);

        final RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<CampagneCriteresRechercheDto>(criterias, criteres.getFirstResult(), criteres.getMaxResult());
        final List<RemotePagingSort> listSort = mapperDozerBean.mapList(criteres.getListeSorts(), RemotePagingSort.class);
        criteresDto.setListeSorts(listSort);
        final RemotePagingResultsDto<CampagneRechercheDto> resultatDto = campagneService.rechercherCampagnesParCriteres(criteresDto);
        final RemotePagingResultsGwt<CampagneRechercheModel> resultatGwt = new RemotePagingResultsGwt<CampagneRechercheModel>();

        final List<CampagneRechercheModel> listCampagneGwt = mapperDozerBean.mapList(resultatDto.getListResults(), CampagneRechercheModel.class);
        resultatGwt.setListResults(listCampagneGwt);
        resultatGwt.setTotalResults(resultatDto.getTotalResults());

        return resultatGwt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleGwt> rechercherIdLibelleCampagnesParCriteres(CampagneCriteresRechercheModel criteres) {
        final CampagneCriteresRechercheDto criterias = mapperDozerBean.map(criteres, CampagneCriteresRechercheDto.class);

        final RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<CampagneCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);

        final RemotePagingResultsDto<CampagneRechercheDto> resultatDto = campagneService.rechercherCampagnesParCriteres(criteresDto);

        final List<IdentifiantLibelleGwt> listCampagneGwt = new ArrayList<IdentifiantLibelleGwt>();
        for (CampagneRechercheDto campagneRechercheDto : resultatDto.getListResults()) {
            final IdentifiantLibelleGwt campagneModel = new IdentifiantLibelleGwt();
            final IdentifiantLibelleGwt ressource = new IdentifiantLibelleGwt();
            if (campagneRechercheDto.getRessource() != null) {
                ressource.setIdentifiant(campagneRechercheDto.getRessource().getIdentifiant());
                ressource.setLibelle(campagneRechercheDto.getRessource().getNom() + " " + campagneRechercheDto.getRessource().getPrenom());
            }
            listCampagneGwt.add(campagneModel);
        }
        return listCampagneGwt;
    }

    @Override
    public CampagneModel rechercherCampagnesParId(Long id) {
        return mapperDozerBean.map(campagneService.rechercherCampagnesParId(id), CampagneModel.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherCampagnesParLibelle(CampagneCriteresRechercheLibelleModel criteres) {
        final CampagneCriteresRechercheLibelle criteresDto = mapperDozerBean.map(criteres, CampagneCriteresRechercheLibelle.class);
        final List<IdentifiantLibelleDto> resultatsDto = campagneService.rechercherCampagnesParLibelle(criteresDto);
        return mapperDozerBean.mapList(resultatsDto, IdentifiantLibelleGwt.class);
    }

    /**
     * Modifie campagneService.
     * @param campagneService la nouvelle valeur de campagneService
     */
    public void setCampagneService(CampagneService campagneService) {
        this.campagneService = campagneService;
    }

    /**
     * Modifie mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

}
