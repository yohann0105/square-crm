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

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.client.gwt.client.model.InfosCreationOpportuniteModel;
import com.square.client.gwt.client.model.OpportuniteCriteresRechercheModel;
import com.square.client.gwt.client.model.OpportuniteModel;
import com.square.client.gwt.client.model.OpportuniteModificationModel;
import com.square.client.gwt.client.model.OpportuniteSimpleModel;
import com.square.client.gwt.client.service.OpportuniteServiceGwt;
import com.square.core.model.dto.OpportuniteCriteresRechercheDto;
import com.square.core.model.dto.OpportuniteDto;
import com.square.core.model.dto.OpportuniteModificationDto;
import com.square.core.model.dto.OpportuniteSimpleDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Implémentation serveur des services GWT pour le service des opportunités.
 * @author cblanchard - SCUB
 */
public class OpportuniteServiceGwtImpl implements OpportuniteServiceGwt {

    /** Service des opportunités. */
    private OpportuniteService opportuniteService;

    /** Service des personnes physiques. */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Service des contantes de Square. */
    private SquareMappingService squareMappingService;

    /** Service de mapping. */
    private MapperDozerBean mapperDozerBean;

    @Override
    public OpportuniteModel creerOpportunite(OpportuniteModel opportuniteModel) {
        final OpportuniteDto opportuniteDto = mapperDozerBean.map(opportuniteModel, OpportuniteDto.class);
        OpportuniteDto opportuniteDtoResultat = new OpportuniteDto();
        opportuniteDtoResultat = opportuniteService.creerOpportunite(opportuniteDto);
        final OpportuniteModel opportuniteModelRes = mapperDozerBean.map(opportuniteDtoResultat, OpportuniteModel.class);
        return opportuniteModelRes;
    }

    @Override
    public void modifierOpportunite(OpportuniteModificationModel opportuniteModificationModel) {
        final OpportuniteModificationDto opportuniteModificationDto = mapperDozerBean.map(opportuniteModificationModel, OpportuniteModificationDto.class);
        opportuniteService.modifierOpportunite(opportuniteModificationDto);
    }

    @Override
    public List<OpportuniteSimpleModel> rechercherOpportuniteParCriteres(OpportuniteCriteresRechercheModel criteres) {
        final OpportuniteCriteresRechercheDto criteresDto = mapperDozerBean.map(criteres, OpportuniteCriteresRechercheDto.class);
        final List<OpportuniteSimpleDto> resultat = opportuniteService.rechercherOpportuniteParCriteres(criteresDto);
        return mapperDozerBean.mapList(resultat, OpportuniteSimpleModel.class);
    }

    @Override
    public InfosCreationOpportuniteModel getInfosCreationOpportunite(Long idPersonne) {
        final InfosCreationOpportuniteModel infos = new InfosCreationOpportuniteModel();
        infos.setHasOpportunite(opportuniteService.hasPersonneOpportunite(idPersonne));
        final PersonneDto personne = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne);
        infos.setPersonneDecedee(personne.isDecede() || squareMappingService.getIdNaturePersonneDecede().equals(personne.getNaturePersonne().getIdentifiant()));
        final List<Long> listeIdsNaturesPersonne = new ArrayList<Long>();
        listeIdsNaturesPersonne.add(squareMappingService.getIdNaturePersonneVivier());
        listeIdsNaturesPersonne.add(squareMappingService.getIdNaturePersonneBeneficiaireVivier());
        infos.setHasFamilleVivier(personnePhysiqueService.hasMembreFamilleNaturePersonne(idPersonne, listeIdsNaturesPersonne));
        return infos;
    }

    @Override
    public Boolean isFamilleEligiblePourOpportunite(Long idPersonnePrincipale) {
        return opportuniteService.isFamilleEligiblePourOpportunite(idPersonnePrincipale);
    }

    @Override
    public void supprimerOpportunite(Long idOpportunite) {
        opportuniteService.supprimerOpportunite(idOpportunite);
    }

    /**
     * Modifie opportuniteService.
     * @param opportuniteService la nouvelle valeur de opportuniteService
     */
    public void setOpportuniteService(OpportuniteService opportuniteService) {
        this.opportuniteService = opportuniteService;
    }

    /**
     * Modifie mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
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
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }
}
