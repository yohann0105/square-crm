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
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.square.client.gwt.client.model.ActionNatureResultatCriteresRechercheModel;
import com.square.client.gwt.client.model.CaisseSimpleModel;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.DimensionCritereCodePostalCommuneModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheDepartementModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheObjetModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheRessourceFonctionModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheResultatActionModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheSousObjetModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheTypeRelationModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheCaisseModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRegimeModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.IdentifiantLibelleBooleanModel;
import com.square.client.gwt.client.model.IdentifiantLibelleDepartementCodeModel;
import com.square.client.gwt.client.model.IdentifiantLibelleTypeRelationModel;
import com.square.client.gwt.client.model.ItemValueModel;
import com.square.client.gwt.client.model.PaysSimpleModel;
import com.square.client.gwt.client.service.DimensionServiceGwt;
import com.square.client.gwt.server.util.FormaterTelephoneUtil;
import com.square.core.model.dto.ActionNatureResultatCriteresRechercheDto;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CommuneCriteresRechercherDto;
import com.square.core.model.dto.DimensionCritereRechercheCodePostalCommuneDto;
import com.square.core.model.dto.DimensionCritereRechercheDepartementDto;
import com.square.core.model.dto.DimensionCritereRechercheObjetDto;
import com.square.core.model.dto.DimensionCritereRechercheRessourceFonctionDto;
import com.square.core.model.dto.DimensionCritereRechercheResultatActionDto;
import com.square.core.model.dto.DimensionCritereRechercheSousObjetDto;
import com.square.core.model.dto.DimensionCritereRechercheTypeRelationDto;
import com.square.core.model.dto.DimensionCriteresRechercheCaisseDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.DimensionCriteresRechercheRegimeDto;
import com.square.core.model.dto.DimensionCriteresRechercheRessourceDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.IdentifiantLibelleDepartementCodeDto;
import com.square.core.model.dto.IdentifiantLibelleTypeRelationDto;
import com.square.core.model.dto.PaysSimpleDto;
import com.square.core.service.interfaces.DimensionService;

/**
 * Implémentation serveur des services GWT pour le service des dimensions.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class DimensionServiceGwtImpl implements DimensionServiceGwt {

    private DimensionService dimensionService;

    private MapperDozerBean mapperDozerBean;

    @Override
    public List<IdentifiantLibelleGwt> rechercherNaturePersonnePhysiqueParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherNaturePersonnePhysiqueParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres,
                DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherNaturePersonneMoraleParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherNaturePersonneMoraleParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres,
                DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherCodePostauxParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherCodePostauxParCriteres((DimensionCriteresRechercheDto) mapperDozerBean
                    .map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherCommuneParCriteres(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto dimensionCriteres = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final CommuneCriteresRechercherDto criteresDto = new CommuneCriteresRechercherDto();
        criteresDto.setDimensionCriteres(dimensionCriteres);
        final List<IdentifiantLibelleDto> liste = dimensionService.rechercherCommuneParCriteres(criteresDto);
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherNatureVoieParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService
                    .rechercherNatureVoieParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherCiviliteParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherCiviliteParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherNatureTelephoneParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherNatureTelephoneParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres,
                DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleBooleanModel> rechercherPaysParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleBooleanDto> liste =
            dimensionService.rechercherPaysBooleanParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleBooleanModel.class);
    }

    @Override
    public List<PaysSimpleModel> rechercherSimplePaysParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<PaysSimpleDto> liste =
            dimensionService
                    .rechercherSimplePaysParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        final List<PaysSimpleModel> listePays = mapperDozerBean.mapList(liste, PaysSimpleModel.class);
        // Transformation de l'expression régulière du format des numéros de téléphone en formateur
        for (PaysSimpleModel pays : listePays) {
            pays.setFormatTelephone(FormaterTelephoneUtil.formterNumTelephone(pays.getId()));
        }
        return listePays;
    }

    @Override
    public List<IdentifiantLibelleBooleanModel> rechercherProfessionParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleBooleanDto> liste =
            dimensionService
                    .rechercherProfessionBooleanParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleBooleanModel.class);
    }

    @Override
    public List<CodePostalCommuneModel> rechercherCodesPostauxCommunes(DimensionCritereCodePostalCommuneModel criteres) {
    	final DimensionCritereRechercheCodePostalCommuneDto criteresDto = mapperDozerBean.map(criteres, DimensionCritereRechercheCodePostalCommuneDto.class);
        final List<CodePostalCommuneDto> liste = dimensionService.rechercherCodesPostauxCommunesParCriteres(criteresDto);
        return mapperDozerBean.mapList(liste, CodePostalCommuneModel.class);
    }

    @Override
    public List<CaisseSimpleModel> rechercherCaisseParCriteres(DimensionCriteresRechercheCaisseModel criteres) {
        final List<CaisseSimpleDto> liste =
            dimensionService.rechercherCaisseParCriteres((DimensionCriteresRechercheCaisseDto) mapperDozerBean.map(criteres,
                DimensionCriteresRechercheCaisseDto.class));
        return mapperDozerBean.mapList(liste, CaisseSimpleModel.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherRegimeParCriteres(DimensionCriteresRechercheRegimeModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherRegimeParCriteres((DimensionCriteresRechercheRegimeDto) mapperDozerBean.map(criteres,
                DimensionCriteresRechercheRegimeDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherTypeAdresseParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherTypeAdresseParCriteres((DimensionCriteresRechercheDto) mapperDozerBean
                    .map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherSegmentParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherSegmentParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherSitFamParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherSitFamParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherCSPParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherCSPParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherReseauVenteParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherReseauVenteParCriteres((DimensionCriteresRechercheDto) mapperDozerBean
                    .map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleDepartementCodeModel> rechercherDepartementParCriteres(DimensionCritereRechercheDepartementModel criteres) {
        final List<IdentifiantLibelleDepartementCodeDto> liste =
            dimensionService.rechercherDepartementParCriteres((DimensionCritereRechercheDepartementDto) mapperDozerBean.map(criteres,
                DimensionCritereRechercheDepartementDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleDepartementCodeModel.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherFormesJuridiques(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherFormesJuridiques((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleTypeRelationModel> rechercherTypesRelations(DimensionCritereRechercheTypeRelationModel criteres) {
        final DimensionCritereRechercheTypeRelationDto criteresDto = mapperDozerBean.map(criteres, DimensionCritereRechercheTypeRelationDto.class);
        final List<IdentifiantLibelleTypeRelationDto> resultDto = dimensionService.rechercherTypesRelations(criteresDto);
        return mapperDozerBean.mapList(resultDto, IdentifiantLibelleTypeRelationModel.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherPrioriteActions(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto critereDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultDto = dimensionService.rechercherPrioriteActionsParCriteres(critereDto);
        return mapperDozerBean.mapList(resultDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherTypesActions(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto critereDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultDto = dimensionService.rechercherTypesActionsParCriteres(critereDto);
        return mapperDozerBean.mapList(resultDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherNatureActions(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto critereDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultDto = dimensionService.rechercherNatureActionsParCriteres(critereDto);
        return mapperDozerBean.mapList(resultDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherNatureResultatActions(ActionNatureResultatCriteresRechercheModel criteres) {
        final ActionNatureResultatCriteresRechercheDto critereDto = mapperDozerBean.map(criteres, ActionNatureResultatCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultDto = dimensionService.rechercherNatureResultatActionsParCriteres(critereDto);
        return mapperDozerBean.mapList(resultDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherObjetActions(DimensionCritereRechercheObjetModel criteres) {
        final DimensionCritereRechercheObjetDto critereDto = mapperDozerBean.map(criteres, DimensionCritereRechercheObjetDto.class);
        final List<IdentifiantLibelleDto> resultDto = dimensionService.rechercherObetsActionsParCriteres(critereDto);
        return mapperDozerBean.mapList(resultDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherResultatActions(DimensionCritereRechercheResultatActionModel criteres) {
        final DimensionCritereRechercheResultatActionDto criteresDto = new DimensionCritereRechercheResultatActionDto();
        final DimensionCriteresRechercheDto criteresGeneraliste = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        criteresDto.setDimensionCriteres(criteresGeneraliste);
        criteresDto.setRecuperationOpportunite(criteres.getRecuperationOpportunite());
        final List<IdentifiantLibelleDto> resultDto = dimensionService.rechercherResultatActionsParCriteres(criteresDto);
        return mapperDozerBean.mapList(resultDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherSousObjetActions(DimensionCritereRechercheSousObjetModel criteres) {
        final DimensionCritereRechercheSousObjetDto critereDto = mapperDozerBean.map(criteres, DimensionCritereRechercheSousObjetDto.class);
        final List<IdentifiantLibelleDto> resultDto = dimensionService.rechercherSousObetsActionsParCriteres(critereDto);
        return mapperDozerBean.mapList(resultDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherStatutActions(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto critereDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultDto = dimensionService.rechercherStatutActionsParCriteres(critereDto);
        return mapperDozerBean.mapList(resultDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherStatutsCampagnes(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto criteresDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultatDto = dimensionService.rechercherStatutsCampagnes(criteresDto);
        return mapperDozerBean.mapList(resultatDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherTypesCampagnes(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto criteresDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultatDto = dimensionService.rechercherTypesCampagnes(criteresDto);
        return mapperDozerBean.mapList(resultatDto, IdentifiantLibelleGwt.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleGwt> rechercherAgenceParCriteres(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto criteresDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultatDto = dimensionService.rechercherAgenceParCriteres(criteresDto);
        return mapperDozerBean.mapList(resultatDto, IdentifiantLibelleGwt.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleGwt> rechercherRessourceFonctionParCriteres(DimensionCritereRechercheRessourceFonctionModel criteres) {
        final DimensionCritereRechercheRessourceFonctionDto criteresDto = mapperDozerBean.map(criteres, DimensionCritereRechercheRessourceFonctionDto.class);
        final List<IdentifiantLibelleDto> resultatDto = dimensionService.rechercherRessourceFonctionParCriteres(criteresDto);
        return mapperDozerBean.mapList(resultatDto, IdentifiantLibelleGwt.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleGwt> rechercherRessourceServiceParCriteres(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto criteresDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultatDto = dimensionService.rechercherRessourceServiceParCriteres(criteresDto);
        return mapperDozerBean.mapList(resultatDto, IdentifiantLibelleGwt.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleGwt> rechercherRessourceEtatParCriteres(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto criteresDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultatDto = dimensionService.rechercherRessourceEtatParCriteres(criteresDto);
        return mapperDozerBean.mapList(resultatDto, IdentifiantLibelleGwt.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DimensionRessourceModel> rechercherRessourceParCriteres(DimensionCriteresRechercheRessourceModel criteres) {
        final DimensionCriteresRechercheRessourceDto criteresDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheRessourceDto.class);
        final List<DimensionRessourceDto> resultatDto = dimensionService.rechercherRessourceParCriteres(criteresDto);
        return mapperDozerBean.mapList(resultatDto, DimensionRessourceModel.class);
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the dimensionService value.
     * @param dimensionService the dimensionService to set
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherListeNombreEnfants(int nombreEnfant, String libNoEnfants, String libEnfant, String libEnfants,
        String suggest) {
        final List<IdentifiantLibelleGwt> liste = new ArrayList<IdentifiantLibelleGwt>();
        for (int nbEnfant = 0; nbEnfant <= nombreEnfant; nbEnfant++) {
            final IdentifiantLibelleGwt item = new IdentifiantLibelleGwt();
            item.setIdentifiant(Integer.valueOf(nbEnfant).longValue());
            item.setLibelle(nbEnfant == 0 ? libNoEnfants : nbEnfant == 1 ? nbEnfant + " " + libEnfant : nbEnfant + " " + libEnfants);
            liste.add(item);
        }
        List<IdentifiantLibelleGwt> listeSuggest = new ArrayList<IdentifiantLibelleGwt>();
        if (suggest == null) {
            listeSuggest = liste;
        }
        else {
            for (IdentifiantLibelleGwt identifiant : liste) {
                if (suggest != null && identifiant.getLibelle().toLowerCase().startsWith(suggest)) {
                    listeSuggest.add(identifiant);
                }
            }
        }

        return listeSuggest;
    }

    @Override
    public List<ItemValueModel> chargerListeRelation(DimensionCritereRechercheTypeRelationModel criteres) {
        final DimensionCritereRechercheTypeRelationDto criteresDto = mapperDozerBean.map(criteres, DimensionCritereRechercheTypeRelationDto.class);
        final List<IdentifiantLibelleTypeRelationDto> resultDto = dimensionService.rechercherTypesRelations(criteresDto);

        final List<ItemValueModel> liste = new ArrayList<ItemValueModel>();
        for (IdentifiantLibelleTypeRelationDto type : resultDto) {
            if (criteres.getLibelle() == null) {
                final ItemValueModel itemLibelle = new ItemValueModel();
                itemLibelle.setItem(type.getIdentifiant() + "#" + type.getLibelle());
                itemLibelle.setValue(type.getLibelle());
                liste.add(itemLibelle);
                if (criteres.getInverse()) {
                    final ItemValueModel itemInverse = new ItemValueModel();
                    itemInverse.setItem(type.getIdentifiant() + "#" + type.getLibelleInverse());
                    itemInverse.setValue(type.getLibelleInverse());
                    liste.add(itemInverse);
                }
            }
            else {
                if (type.getLibelle().toLowerCase().startsWith(criteres.getLibelle())) {
                    final ItemValueModel itemLibelle = new ItemValueModel();
                    itemLibelle.setItem(type.getIdentifiant() + "#" + type.getLibelle());
                    itemLibelle.setValue(type.getLibelle());
                    liste.add(itemLibelle);
                }
                if (criteres.getInverse() && type.getLibelleInverse().toLowerCase().startsWith(criteres.getLibelle())) {
                    final ItemValueModel itemInverse = new ItemValueModel();
                    itemInverse.setItem(type.getIdentifiant() + "#" + type.getLibelleInverse());
                    itemInverse.setValue(type.getLibelleInverse());
                    liste.add(itemInverse);
                }
            }
        }
        return liste;
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherListeConjoint(String pasConjoint, String unConjoint, String suggest) {
        final List<IdentifiantLibelleGwt> liste = new ArrayList<IdentifiantLibelleGwt>();
        liste.add(new IdentifiantLibelleGwt(0l, pasConjoint));
        liste.add(new IdentifiantLibelleGwt(1l, unConjoint));
        List<IdentifiantLibelleGwt> listeResultat = new ArrayList<IdentifiantLibelleGwt>();

        if (suggest == null) {
            listeResultat = liste;
        }
        else {
            for (IdentifiantLibelleGwt libelle : liste) {
                if (libelle.getLibelle().toLowerCase().startsWith(suggest)) {
                    listeResultat.add(libelle);
                }
            }
        }

        return listeResultat;
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherListeTypesRelations(List<IdentifiantLibelleGwt> liste, String suggest) {

        List<IdentifiantLibelleGwt> listeResultat = new ArrayList<IdentifiantLibelleGwt>();
        if (suggest == null) {
            listeResultat = liste;
        }
        else {
            for (IdentifiantLibelleGwt type : liste) {
                if (type.getLibelle().toLowerCase().startsWith(suggest)) {
                    listeResultat.add(type);
                }
            }
        }
        return listeResultat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdentifiantLibelleGwt rechercherAgenceParIdRessource(Long idRessource) {
        final IdentifiantLibelleDto agence = dimensionService.rechercherAgenceParIdRessource(idRessource);
        return mapperDozerBean.map(agence, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherDureeActionParCriteres(DimensionCriteresRechercheModel criteres) {
        final DimensionCriteresRechercheDto critereDto = mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class);
        final List<IdentifiantLibelleDto> resultDto = dimensionService.rechercherDureeActionParCriteres(critereDto);
        return mapperDozerBean.mapList(resultDto, IdentifiantLibelleGwt.class);
    }
}
