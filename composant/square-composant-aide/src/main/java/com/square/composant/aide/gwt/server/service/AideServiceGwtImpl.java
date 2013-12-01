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
package com.square.composant.aide.gwt.server.service;

import java.util.List;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.composant.aide.gwt.client.model.AideModel;
import com.square.composant.aide.gwt.client.service.AideServiceGwt;
import com.square.core.model.dto.AideDto;
import com.square.core.service.interfaces.AideService;

/**
 * Implementation du service d'aide.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */

public class AideServiceGwtImpl implements AideServiceGwt {
    private AideService aideService;

    private MapperDozerBean mapperDozerBean;

    @Override
    public AideModel rechercherAide(Long id) {
        final AideDto aideDto = aideService.rechercherAideParidComposant(id);
        final AideModel aideModel = mapperDozerBean.map(aideDto, AideModel.class);
        return aideModel;
    }

    @Override
    public AideModel creerOuModifierAide(AideModel aide) {
        AideDto aideDto = mapperDozerBean.map(aide, AideDto.class);
        aideDto = aideService.creerOuModifierAide(aideDto);
        final AideModel aideModel = mapperDozerBean.map(aideDto, AideModel.class);
        return aideModel;
    }

    @Override
    public List<Long> rechercherIdsComposantsAides(List<Long> listeIdsComposant) {
        return aideService.rechercherIdsComposantsAides(listeIdsComposant);
    }

    /**
     * Setter sur le service aia.
     * @param aideService the aideService to set
     */
    public void setAideService(AideService aideService) {
        this.aideService = aideService;
    }

    /**
     * Setter sur la mapper dozer bean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }
}
