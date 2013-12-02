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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.client.gwt.client.model.CompteursModel;
import com.square.client.gwt.client.model.PersonneMoralCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneMoraleModel;
import com.square.client.gwt.client.model.PersonneMoraleRechercheModel;
import com.square.client.gwt.client.model.PersonneMoraleSimpleModel;
import com.square.client.gwt.client.model.CompteursModel.Compteur;
import com.square.client.gwt.client.service.PersonneMoraleServiceGwt;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.PersonneMoralCriteresRechercheDto;
import com.square.core.model.dto.PersonneMoraleDto;
import com.square.core.model.dto.PersonneMoraleRechercheDto;
import com.square.core.model.dto.PersonneMoraleSimpleDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.PersonneMoraleService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Implémentation serveur des services GWT pour le service des personnes morales.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneMoraleServiceGwtImpl implements PersonneMoraleServiceGwt {

    /** Service des personnes morales. **/
    private PersonneMoraleService personneMoraleService;

    /** Service des actions. */
    private ActionService actionService;

    /** Service contrat. */
    private ContratService contratService;

    /** Service personne. */
    private PersonneService personneService;

    /** Service mapping. */
    private SquareMappingService squareMappingService;

    /** Dozzer. **/
    MapperDozerBean mapperDozerBean;

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonneMoraleModel recherchePersonneMoraleParId(Long identifiant) {
        final PersonneMoraleDto personneMoralDto = personneMoraleService.recherchePersonneMoraleParId(identifiant);
        return mapperDozerBean.map(personneMoralDto, PersonneMoraleModel.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonneMoraleSimpleModel recherchepersonneMoraleSimpleParId(Long identifiant) {
        final PersonneMoraleSimpleDto personneMoraleSimpleDto = personneMoraleService.recherchepersonneMoraleSimpleParId(identifiant);
        return mapperDozerBean.map(personneMoraleSimpleDto, PersonneMoraleSimpleModel.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RemotePagingResultsGwt<PersonneMoraleRechercheModel> rechercherPersonneMoraleParCriteres(
        RemotePagingCriteriasGwt<PersonneMoralCriteresRechercheModel> criteres) {
        final PersonneMoralCriteresRechercheDto criterias = mapperDozerBean.map(criteres.getCriterias(), PersonneMoralCriteresRechercheDto.class);
        final RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto>(criterias, criteres.getFirstResult(), criteres.getMaxResult());
        final List<RemotePagingSort> listSort = mapperDozerBean.mapList(criteres.getListeSorts(), RemotePagingSort.class);
        criteresDto.setListeSorts(listSort);
        final RemotePagingResultsDto<PersonneMoraleRechercheDto> resultatsDto = personneMoraleService.rechercherPersonneMoraleParCriteres(criteresDto);
        final RemotePagingResultsGwt<PersonneMoraleRechercheModel> resultats = new RemotePagingResultsGwt<PersonneMoraleRechercheModel>();
        final List<PersonneMoraleRechercheModel> listePersonnesMoraleGwt =
            mapperDozerBean.mapList(resultatsDto.getListResults(), PersonneMoraleRechercheModel.class);
        resultats.setListResults(listePersonnesMoraleGwt);
        resultats.setTotalResults(resultatsDto.getTotalResults());
        return resultats;
    }

    /**
     * Modifie la valeur de personneMoraleService.
     * @param personneMoraleService the personneMoraleService to set
     */
    public void setPersonneMoraleService(PersonneMoraleService personneMoraleService) {
        this.personneMoraleService = personneMoraleService;
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    @Override
    public CompteursModel getCompteursParId(Long id, CompteursModel parametres) {
        if (parametres.getCompteursDemandes() == null) {
            return parametres;
        }
        final Map<Compteur, Integer> compteursResultat = new HashMap<Compteur, Integer>();
        for (Compteur compteurDemande : parametres.getCompteursDemandes()) {
            try {
                switch (compteurDemande) {
                    case ACTIONS :
                        final ActionCritereRechercheDto criteres = new ActionCritereRechercheDto();
                        criteres.setIdPersonne(id);
                        final Integer nbActions = actionService.countActionsParCriteres(criteres);
                        compteursResultat.put(Compteur.ACTIONS, nbActions);
                    break;
                    case CONTRATS :
                        final Integer nbContrats = contratService.countContrats(id, true);
                        compteursResultat.put(Compteur.CONTRATS, nbContrats);
                    break;
                    case RELATIONS :
                        final RelationCriteresRechercheDto criteresRelations = new RelationCriteresRechercheDto();
                        criteresRelations.setIdPersonne(id);
                        criteresRelations.setPasDansGroupements(
                            Arrays.asList(squareMappingService.getIdGroupementFamille(), squareMappingService.getIdGroupementProfessionnel()));
                        final Integer nbRelations = personneService.countRelationsParCriteres(criteresRelations);
                        compteursResultat.put(Compteur.RELATIONS, nbRelations);
                    break;
                    default: break;
                }
            } catch (Exception e) {
                // On ne bloque pas l'ensemble des compteurs parce qu'un d'eux n'a pas fonctionné
                e.printStackTrace();
            }
        }
        parametres.setCompteursObtenus(compteursResultat);
        return parametres;
    }

    /**
     * Définition de actionService.
     * @param actionService the actionService to set
     */
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    /**
     * Définition de contratService.
     * @param contratService the contratService to set
     */
    public void setContratService(ContratService contratService) {
        this.contratService = contratService;
    }

    /**
     * Définition de personneService.
     * @param personneService the personneService to set
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Définition de squareMappingService.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

}
