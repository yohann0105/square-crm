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
package com.square.composant.flux.opportunite.server.service;

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.square.composant.flux.opportunite.client.model.AccesPotCommunModel;
import com.square.composant.flux.opportunite.client.model.QuotaModel;
import com.square.composant.flux.opportunite.client.service.FluxOpportuniteServiceGwt;
import com.square.flux.opportunite.core.service.dto.AccesPotCommunDto;
import com.square.flux.opportunite.core.service.dto.QuotaDto;
import com.square.flux.opportunite.core.service.interfaces.FluxOpportuniteService;

/**
 * Implementation du service sur les examples.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class FluxOpportuniteServiceGwtImpl implements FluxOpportuniteServiceGwt {

    /** Service pour le flux d'opportunite. */
    private FluxOpportuniteService fluxOpportuniteService;

    /** MapperDozerBean. */
    private MapperDozerBean mapperDozerBean;

    @Override
    public List<QuotaModel> getListeQuotasRessource(Long idRessource) {
        final List<QuotaDto> listeQuotasSto = fluxOpportuniteService.getListeQuotasRessource(idRessource);
        return mapperDozerBean.mapList(listeQuotasSto, QuotaModel.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> getListeJours() {
        final List<IdentifiantLibelleDto> listeJoursDto = fluxOpportuniteService.getListeJours();
        return mapperDozerBean.mapList(listeJoursDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public void modifierQuotas(List<Long> listeIdRessources, List<QuotaModel> listeQuotas) {
        final List<QuotaDto> listeQuotasDto = mapperDozerBean.mapList(listeQuotas, QuotaDto.class);
        fluxOpportuniteService.modifierQuotasListeRessources(listeQuotasDto, listeIdRessources);
    }

    @Override
    public AccesPotCommunModel hasAccesPotCommun(Long idRessource) {
        final AccesPotCommunDto accesPotCommunDto = fluxOpportuniteService.hasAccesPotCommun(idRessource);
        return mapperDozerBean.map(accesPotCommunDto, AccesPotCommunModel.class);
    }

    /**
     * Définit la valeur de fluxOpportuniteService.
     * @param fluxOpportuniteService la nouvelle valeur de fluxOpportuniteService
     */
    public void setFluxOpportuniteService(FluxOpportuniteService fluxOpportuniteService) {
        this.fluxOpportuniteService = fluxOpportuniteService;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }
}