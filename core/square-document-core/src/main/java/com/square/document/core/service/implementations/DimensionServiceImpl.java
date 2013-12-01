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
package com.square.document.core.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.document.core.dao.interfaces.TagDao;
import com.square.document.core.dto.TypeDocumentDto;
import com.square.document.core.model.Tag;
import com.square.document.core.service.interfaces.DimensionService;

/**
 * .
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
@Transactional(propagation = Propagation.REQUIRED)
public class DimensionServiceImpl implements DimensionService {

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Dao pour les Tags. */
    private TagDao tagDao;

    /** Service de dimension. */
    @SuppressWarnings("unused")
    private DimensionService dimensionService;

    /** Classe utilitaire pour accéder aux messages. */
    @SuppressWarnings("unused")
    private MessageSourceUtil messageSourceUtil;

    /**
     * Setter pour mapperdozer.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Setter du dao pour les tags.
     * @param tagDao the tagDao to set
     */
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    /**
     * Setter pour l'interface du service de dimension.
     * @param dimensionService the dimensionService to set
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * Setter pour les messages.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    @Override
    public List<TypeDocumentDto> getListeTypesDocuments(String utilisateur) {
        final List<Tag> tagList = tagDao.getListeTag();
        final List<TypeDocumentDto> listeType = new ArrayList<TypeDocumentDto>();
        for (Tag tag : tagList) {
            if (tag.getListeTags().size() > 0) {
                final TypeDocumentDto type = mapperDozerBean.map(tag, TypeDocumentDto.class);
                final List<TypeDocumentDto> listeSousTypes = mapperDozerBean.mapList(tag.getListeTags(), TypeDocumentDto.class);
                type.setListeTypesDocuments(listeSousTypes);
                listeType.add(type);
            }
        }
        return listeType;
    }
}
