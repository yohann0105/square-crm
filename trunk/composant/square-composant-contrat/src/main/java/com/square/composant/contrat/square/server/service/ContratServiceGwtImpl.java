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
package com.square.composant.contrat.square.server.service;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.adherent.noyau.dto.adherent.contrat.AjustementDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieSimpleDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosContratsDto;
import com.square.adherent.noyau.dto.adherent.contrat.ListeContratsDto;
import com.square.adherent.noyau.dto.adherent.contrat.RecapitulatifGarantiesContratDto;
import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.composant.contrat.square.client.model.AjustementTarifModel;
import com.square.composant.contrat.square.client.model.ContratModel;
import com.square.composant.contrat.square.client.model.ContratSimpleModel;
import com.square.composant.contrat.square.client.model.GarantieBeneficiaireModel;
import com.square.composant.contrat.square.client.model.GarantieModel;
import com.square.composant.contrat.square.client.model.GarantieSimpleModel;
import com.square.composant.contrat.square.client.model.InfosContratsBeneficiaireModel;
import com.square.composant.contrat.square.client.model.InfosContratsModel;
import com.square.composant.contrat.square.client.model.InfosGarantieBeneficiaireModel;
import com.square.composant.contrat.square.client.model.ListeContratsModel;
import com.square.composant.contrat.square.client.model.RatioPrestationCotisationModel;
import com.square.composant.contrat.square.client.model.RecapitulatifGarantiesContratModel;
import com.square.composant.contrat.square.client.service.ContratServiceGwt;

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
    public ListeContratsModel getListeContrats(Long uidPersonne) {
        // Appel du service
        final ListeContratsDto listeContratsDto = contratService.getListeContrats(uidPersonne);
        // Mapping
        return mapperListeContrats(listeContratsDto);
    }

    @Override
    public ContratModel getContrat(Long uidContrat) {
        // Appel du service
        final ContratDto contratDto = contratService.getContrat(uidContrat);
        // Mapping
        return mapperContrat(contratDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InfosContratsBeneficiaireModel hasContratAssureBeneficiaire(Long idAssure, Long idBeneficiaire) {
        return mapperDozerBean.map(contratService.hasContratAssureBeneficiaire(idAssure, idBeneficiaire), InfosContratsBeneficiaireModel.class);
    }

    /**
     * Mappe le DTO ListeContratsDto en modèle ListeContratsModel.
     * @param listeContratsDto le DTO ListeContratsDto
     * @return le modèle ListeContratsModel
     */
    private ListeContratsModel mapperListeContrats(ListeContratsDto listeContratsDto) {
        final ListeContratsModel listeContratsModel = new ListeContratsModel();
        // Mapping des infos de contrats
        final InfosContratsDto infosContratsDto = listeContratsDto.getInfosContrats();
        if (infosContratsDto != null) {
            final InfosContratsModel infosContratsModel = mapperDozerBean.map(infosContratsDto, InfosContratsModel.class);
            // Mapping du récapitulatif des garanties
            infosContratsModel.setListeGarantiesContrat(mapperRecapitulatifGaranties(infosContratsDto.getListeGarantiesContrat()));
            // Mapping des ratios prestations/cotisations
            final List<RatioPrestationCotisationModel> listeRatiosPrestationCotisation =
                mapperDozerBean.mapList(infosContratsDto.getListeRatiosPrestationCotisation(), RatioPrestationCotisationModel.class);
            infosContratsModel.setListeRatiosPrestationCotisation(listeRatiosPrestationCotisation);
            listeContratsModel.setInfosContrats(infosContratsModel);
        }
        // Mapping des contrats souscrits
        if (listeContratsDto.getListeContrats() != null) {
            final List<ContratSimpleModel> listeContratsSimples = mapperDozerBean.mapList(listeContratsDto.getListeContrats(), ContratSimpleModel.class);
            listeContratsModel.setListeContrats(listeContratsSimples);
        }
        return listeContratsModel;
    }

    /**
     * Mappe le DTO RecapitulatifGarantiesContratDto en modèle RecapitulatifGarantiesContratModel.
     * @param listeContratsDto le DTO RecapitulatifGarantiesContratDto
     * @return le modèle RecapitulatifGarantiesContratModel
     */
    private RecapitulatifGarantiesContratModel mapperRecapitulatifGaranties(RecapitulatifGarantiesContratDto recapitulatifGarantiesDto) {
        if (recapitulatifGarantiesDto != null) {
            final RecapitulatifGarantiesContratModel recapitulatifGarantiesContratModel = new RecapitulatifGarantiesContratModel();
            // Mapping des bénéficiaires
            if (recapitulatifGarantiesDto.getListeBeneficiaires() != null) {
                final List<GarantieBeneficiaireModel> listeBeneficiairesModel =
                    mapperDozerBean.mapList(recapitulatifGarantiesDto.getListeBeneficiaires(), GarantieBeneficiaireModel.class);
                recapitulatifGarantiesContratModel.setListeBeneficiaires(listeBeneficiairesModel);
            }
            // Mapping des garanties
            if (recapitulatifGarantiesDto.getListeGaranties() != null) {
                final List<GarantieSimpleModel> listeGarantiesSimplesModel = new ArrayList<GarantieSimpleModel>();
                for (GarantieSimpleDto garantieSimpleDto : recapitulatifGarantiesDto.getListeGaranties()) {
                    final GarantieSimpleModel garantieSimpleModel = mapperGarantieSimple(garantieSimpleDto);
                    if (garantieSimpleModel != null) {
                        listeGarantiesSimplesModel.add(garantieSimpleModel);
                    }
                }
                recapitulatifGarantiesContratModel.setListeGaranties(listeGarantiesSimplesModel);
            }
            return recapitulatifGarantiesContratModel;
        }
        else {
            return null;
        }
    }

    /**
     * Mappe le DTO GarantieSimpleDto en modèle GarantieSimpleModel.
     * @param listeContratsDto le DTO GarantieSimpleDto
     * @return le modèle GarantieSimpleModel
     */
    private GarantieSimpleModel mapperGarantieSimple(GarantieSimpleDto garantieSimpleDto) {
        if (garantieSimpleDto != null) {
            final GarantieSimpleModel garantieSimpleModel = mapperDozerBean.map(garantieSimpleDto, GarantieSimpleModel.class);

            //la garantie possède une grille de prestation ou non
            if (garantieSimpleDto.getIdFormulePresta() != null) {
                garantieSimpleModel.setPossedeGrillePresta(true);
            }

            // Mapping de la liste des infos de garantie des bénéficiaires
            if (garantieSimpleDto.getListeInfosGarantiesBeneficiaires() != null) {
                final List<InfosGarantieBeneficiaireModel> listeInfosGarantieBeneficiaire =
                    mapperDozerBean.mapList(garantieSimpleDto.getListeInfosGarantiesBeneficiaires(), InfosGarantieBeneficiaireModel.class);
                garantieSimpleModel.setListeInfosGarantiesBeneficiaires(listeInfosGarantieBeneficiaire);
            }
            return garantieSimpleModel;
        }
        else {
            return null;
        }
    }

    /**
     * Mappe le DTO ContratDto en modèle ContratModel.
     * @param listeContratsDto le DTO ContratDto
     * @return le modèle ContratModel
     */
    private ContratModel mapperContrat(ContratDto contratDto) {
        final ContratModel contratModel = mapperDozerBean.map(contratDto, ContratModel.class);
        // Mapping du récapitulatif des garanties
        contratModel.setRecapitulatifGarantiesContrat(mapperRecapitulatifGaranties(contratDto.getRecapitulatifGarantiesContrat()));
        // Mapping des ajustements
        if (contratDto.getListeAjustements() != null) {
            final List<AjustementTarifModel> listeAjustements = new ArrayList<AjustementTarifModel>();
            for (AjustementDto ajustementDto : contratDto.getListeAjustements()) {
                final AjustementTarifModel ajustementTarifModel = mapperDozerBean.map(ajustementDto, AjustementTarifModel.class);
                listeAjustements.add(ajustementTarifModel);
            }
            contratModel.setListeAjustements(listeAjustements);
        }
        // Mapping des garanties
        if (contratDto.getListeGaranties() != null) {
            final List<GarantieModel> listeGaranties = new ArrayList<GarantieModel>();
            for (GarantieDto garantieDto : contratDto.getListeGaranties()) {
                final GarantieModel garantieModel = mapperDozerBean.map(garantieDto, GarantieModel.class);
                if (garantieModel != null) {
                    listeGaranties.add(garantieModel);
                }
            }
            contratModel.setListeGaranties(listeGaranties);
        }
        return contratModel;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de contratService.
     * @param contratService la nouvelle valeur de contratService
     */
    public void setContratService(ContratService contratService) {
        this.contratService = contratService;
    }

}
