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

import java.util.List;

import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.dao.interfaces.AideDao;
import com.square.core.model.Aide;
import com.square.core.model.dto.AideDto;
import com.square.core.service.interfaces.AideService;
import com.square.core.util.AideKeyUtil;

/**
 * implémentation de service de gestion des aides.
 * @author mohamed - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AideServiceImplementation implements AideService {
    /**
     * Mapper dozer.
     */
    private MapperDozerBean mapperDozerBean;

    /**
     * DAO pour les aides.
     */
    private AideDao aideDao;

    /**
     * Classe utilitaire pour accéder aux messages.
     */
    private MessageSourceUtil messageSourceUtil;

    @Override
    public AideDto rechercherAide(Long id) {
        if (id == null) {
            throw new BusinessException(messageSourceUtil.get(AideKeyUtil.MESSAGE_ERROR_SEARCH_AIDE_ID_REQUIRED));
        }
        final Aide aideTrouve = aideDao.rechercherAideparId(id);
        if (aideTrouve != null) {
            final AideDto aide = mapperDozerBean.map(aideTrouve, AideDto.class);
            return aide;
        }
        return null;
    }

    @Override
    public AideDto rechercherAideParidComposant(Long idComposant) {
        if (idComposant == null) {
            throw new BusinessException(messageSourceUtil.get(AideKeyUtil.MESSAGE_ERROR_SEARCH_AIDE_IDCOMPOSANT_REQUIRED));
        }
        final Aide aideTrouve = aideDao.rechercherAideparIdComposant(idComposant);
        if (aideTrouve != null) {
            final AideDto aide = mapperDozerBean.map(aideTrouve, AideDto.class);
            return aide;
        }

        return null;
    }

    @Override
    public AideDto creerOuModifierAide(AideDto aide) {
        if (aide == null) {
            throw new BusinessException(messageSourceUtil.get(AideKeyUtil.MESSAGE_ERROR_SAVE_AIDE_DTO_REQUIRED));
        }
        if (aide.getIdComposant() == null) {
            throw new BusinessException(messageSourceUtil.get(AideKeyUtil.MESSAGE_ERROR_SEARCH_AIDE_ID_REQUIRED));
        }
        Aide aideToUpdate = aideDao.rechercherAideparIdComposant(aide.getIdComposant());
        if (aideToUpdate == null) {
            aideToUpdate = mapperDozerBean.map(aide, Aide.class);
        }
        aideToUpdate.setText(aide.getText());
        aideDao.creerAide(aideToUpdate);
        final AideDto aideDto = (AideDto) mapperDozerBean.map(aideToUpdate, AideDto.class);

        return aideDto;
    }

    @Override
    public List<Long> rechercherIdsComposantsAides(List<Long> listeIdsComposant) {
        return aideDao.rechercherIdsComposantsAides(listeIdsComposant);
    }

    /**
     * Setter.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Setter.
     * @param aideDao the aideDao to set
     */
    public void setAideDao(AideDao aideDao) {
        this.aideDao = aideDao;
    }

    /**
     * Setter.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }
}
