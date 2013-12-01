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
package com.square.core.service.implementations;

import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.dao.interfaces.AgenceDao;
import com.square.core.dao.interfaces.RegionCommercialeDao;
import com.square.core.model.RegionCommerciale;
import com.square.core.model.Ressources.Agence;
import com.square.core.model.dto.AgenceDto;
import com.square.core.model.dto.IdentifiantEidLibelleDto;
import com.square.core.service.interfaces.AgenceService;
import com.square.core.util.AgenceKeyUtil;

/**
 * Implémentation de l'interface AgenceService.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AgenceServiceImplementation implements AgenceService {

    /** Dao pour les agences. */
    private AgenceDao agenceDao;

    /** Dao pour les régions commerciales. */
    private RegionCommercialeDao regionCommercialeDao;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    @Override
    public AgenceDto creerAgence(AgenceDto agenceDto) {
        // Vérification des champs obligatoires
        verifierChampsObligatoires(agenceDto);
        final RegionCommerciale region = regionCommercialeDao.rechercherRegionCommercialeParId(agenceDto.getRegion().getIdentifiant());
        if (region == null) {
            throw new BusinessException(messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_REG_COM_INEXISTANTE));
        }

        // Création de l'agence
        final Agence agence = mapperDozerBean.map(agenceDto, Agence.class);
        agence.setDateCreation(Calendar.getInstance());
        agence.setSupprime(false);
        agence.setRegion(region);
        agenceDao.creerAgence(agence);
        return mapperDozerBean.map(agence, AgenceDto.class);
    }

    @Override
    public AgenceDto modifierAgence(AgenceDto agenceDto) {
        // Récupération de l'agence
        final Agence agence = agenceDao.rechercheAgenceParId(agenceDto.getId());
        if (agence == null) {
            throw new BusinessException(messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_INEXISTANTE));
        }
        if (agence.isSupprime()) {
            throw new BusinessException(messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_UPDATE_AGENCE_SUPPRIMEE));
        }

        // Vérification des champs obligatoires
        verifierChampsObligatoires(agenceDto);
        final RegionCommerciale region = regionCommercialeDao.rechercherRegionCommercialeParId(agenceDto.getRegion().getIdentifiant());
        if (region == null) {
            throw new BusinessException(messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_REG_COM_INEXISTANTE));
        }

        // Mise à jour de l'agence
        mapperDozerBean.map(agenceDto, agence);
        agence.setDateModification(Calendar.getInstance());
        agence.setRegion(region);
        return mapperDozerBean.map(agence, AgenceDto.class);
    }

    @Override
    public AgenceDto rechercherAgenceParEid(String eidAgence) {
        final Agence agence = agenceDao.rechercheAgenceParEid(eidAgence);
        return mapperDozerBean.map(agence, AgenceDto.class);
    }

    @Override
    public void supprimerAgence(String eidAgence) {
        final Agence agence = agenceDao.rechercheAgenceParEid(eidAgence);
        if (agence == null) {
            throw new BusinessException(messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_INEXISTANTE));
        }
        agence.setSupprime(true);
        agence.setDateSuppression(Calendar.getInstance());
    }

    @Override
    public IdentifiantEidLibelleDto rechercherRegionCommercialeParEid(String eidRegion) {
        final RegionCommerciale region = regionCommercialeDao.rechercherRegionCommercialeParEid(eidRegion);
        return mapperDozerBean.map(region, IdentifiantEidLibelleDto.class);
    }

    /**
     * Vérifie les champs obligatoires d'une agence.
     * @param agenceDto les informations de l'agence à vérifier.
     */
    private void verifierChampsObligatoires(AgenceDto agenceDto) {
        // Libellé de l'agence obligatoire
        if (agenceDto.getLibelle() == null || "".equals(agenceDto.getLibelle())) {
            throw new BusinessException(messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_LIBELLE_OBLIGATOIRE));
        }
        // Région commerciale obligatoire
        if (agenceDto.getRegion() == null || agenceDto.getRegion().getIdentifiant() == null
                || "".equals(agenceDto.getRegion().getIdentifiant())) {
            throw new BusinessException(messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_REG_COM_OBLIGATOIRE));
        }
    }

    @Override
    public List<AgenceDto> rechercherAgencesParIds(List<Long> listeIds) {
        if (listeIds == null || listeIds.size() == 0) {
            return null;
        }
        final List<Agence> listeAgences = agenceDao.rechercheAgencesParIds(listeIds);
        return mapperDozerBean.mapList(listeAgences, AgenceDto.class);
    }

    /**
     * Définit la valeur de agenceDao.
     * @param agenceDao la nouvelle valeur de agenceDao
     */
    public void setAgenceDao(AgenceDao agenceDao) {
        this.agenceDao = agenceDao;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Définit la valeur de regionCommercialeDao.
     * @param regionCommercialeDao la nouvelle valeur de regionCommercialeDao
     */
    public void setRegionCommercialeDao(RegionCommercialeDao regionCommercialeDao) {
        this.regionCommercialeDao = regionCommercialeDao;
    }

}
