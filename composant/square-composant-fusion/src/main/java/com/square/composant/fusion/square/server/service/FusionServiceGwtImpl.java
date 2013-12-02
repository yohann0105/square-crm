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

import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.context.SecurityContextHolder;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.square.composant.fusion.square.client.exception.ControleIntegriteFusionExceptionGwt;
import com.square.composant.fusion.square.client.model.ParametresFusionModel;
import com.square.composant.fusion.square.client.model.RechercheDoublonCritereModel;
import com.square.composant.fusion.square.client.model.RechercheDoublonResultatModel;
import com.square.composant.fusion.square.client.service.FusionServiceGwt;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.fusion.api.dto.ParametresFusionDto;
import com.square.fusion.api.service.interfaces.FusionPersonneService;


/**
 * Implémentation de l'interface FusionServiceGwt.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class FusionServiceGwtImpl implements FusionServiceGwt {

    /** Service pour les personnes physiques. */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Service pour la fusion. */
    private FusionPersonneService fusionPersonneService;

    /** Mapper. */
    private MapperDozerBean mapperDozerBean;

    @Override
    public RemotePagingResultsGwt<RechercheDoublonResultatModel> rechercherDoublonsPourSelection(
        RemotePagingCriteriasGwt<RechercheDoublonCritereModel> criteres) {
        final PersonneCriteresRechercheDto criteriasDto = mapperDozerBean.map(criteres.getCriterias(), PersonneCriteresRechercheDto.class);
        criteriasDto.setRechercheStricte(true);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteriasDto, criteres.getFirstResult(), criteres.getMaxResult());
        final List<RemotePagingSort> listeSorts = mapperDozerBean.mapList(criteres.getListeSorts(), RemotePagingSort.class);
        criteresDto.setListeSorts(listeSorts);
        final RemotePagingResultsDto<PersonneSimpleDto> resultDto = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresDto);
        final RemotePagingResultsGwt<RechercheDoublonResultatModel> result = new RemotePagingResultsGwt<RechercheDoublonResultatModel>();
        final List<RechercheDoublonResultatModel> listeDoublons = mapperDozerBean.mapList(resultDto.getListResults(), RechercheDoublonResultatModel.class);
        result.setListResults(listeDoublons);
        result.setTotalResults(resultDto.getTotalResults());
        return result;
    }

    @Override
    public ParametresFusionModel preparerFusion(Long idPersonne1, Long idPersonne2) {
        final ParametresFusionDto fusionDto = fusionPersonneService.preparerFusion(idPersonne1, idPersonne2);
        final ParametresFusionModel model = mapperDozerBean.map(fusionDto, ParametresFusionModel.class);
        return model;
    }

    @Override
    public void validerFusion(ParametresFusionModel parametresFusion) {
        final ParametresFusionDto parametresFusionDto = mapperDozerBean.map(parametresFusion, ParametresFusionDto.class);
        try {
            fusionPersonneService.validerFusion(parametresFusionDto, getLoginUtilisateurCourant());
        } catch (ControleIntegriteException e) {
            throw mapperControleIntegriteException(e);
        }
    }

    /**
     * Affiche le message d'erreur du contrôle d'intégrité.
     * @param caught l'exception du contrôle d'intégrité
     */
    private ControleIntegriteFusionExceptionGwt mapperControleIntegriteException(ControleIntegriteException caught) {
        final List<String> listeErreurs = new ArrayList<String>();
        if (caught != null) {
            if (caught.getRapport() != null && caught.getRapport().getRapports() != null) {
                for (String cle : caught.getRapport().getRapports().keySet()) {
                    if (caught.getRapport().getRapports().get(cle).getErreur()) {
                        listeErreurs.add(caught.getRapport().getRapports().get(cle).getMessage());
                    }
                }
            }
        }
        return new ControleIntegriteFusionExceptionGwt(listeErreurs);
    }

    /***
     * Récupération du login (Acegi & Spring security supportés.
     * @return le login
     */
    private String getLoginUtilisateurCourant() {
        return SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
            ? SecurityContextHolder.getContext().getAuthentication().getName()
                 : org.springframework.security.context.SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * Setter.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de personnePhysiqueService.
     * @param personnePhysiqueService la nouvelle valeur de personnePhysiqueService
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Définit la valeur de fusionPersonneService.
     * @param fusionPersonneService la nouvelle valeur de fusionPersonneService
     */
    public void setFusionPersonneService(FusionPersonneService fusionPersonneService) {
        this.fusionPersonneService = fusionPersonneService;
    }

}
