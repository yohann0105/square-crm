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

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.square.client.gwt.client.model.CotisationCriteresRechercheModel;
import com.square.client.gwt.client.model.CotisationRechercheModel;
import com.square.client.gwt.client.service.CotisationServiceGwt;
import com.square.core.model.dto.CotisationCriteresRechercheDto;
import com.square.core.model.dto.CotisationRechercheDto;
import com.square.core.service.interfaces.CotisationService;

/**
 * Implémentation du serveur des services GWT pour les services des cotisations.
 * @author Farh ZARROUK (farh.zarrouk@gmail.com) - SCUB
 */
public class CotisationServiceGwtImpl implements CotisationServiceGwt {

    /** Le service. */
    private CotisationService cotisationService;

    /** Le mapper. */
    private MapperDozerBean mapperDozerBean;

    @Override
    public CotisationRechercheModel rechercherCotisationParCriteres(CotisationCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt {
        final CotisationCriteresRechercheDto criteresDto = mapperDozerBean.map(criteres, CotisationCriteresRechercheDto.class);
        final CotisationRechercheDto resultatDto = cotisationService.rechercherCotisationParCriteres(criteresDto);
        return mapperDozerBean.map(resultatDto, CotisationRechercheModel.class);
    }

}
