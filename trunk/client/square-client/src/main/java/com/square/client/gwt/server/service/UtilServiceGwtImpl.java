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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.client.gwt.client.model.ActionCritereRechercheModel;
import com.square.client.gwt.client.model.CampagneCriteresRechercheModel;
import com.square.client.gwt.client.model.IdentifiantLibelleOuiNonModel;
import com.square.client.gwt.client.model.PersonneCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneMoralCriteresRechercheModel;
import com.square.client.gwt.client.model.RessourceCriteresRechercheModel;
import com.square.client.gwt.client.service.UtilServiceGwt;
import com.square.client.gwt.server.util.StringConverter;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.CampagneCriteresRechercheDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneMoralCriteresRechercheDto;
import com.square.core.model.dto.RessourceCriteresRechercheDto;

/**
 * Implémentation du service synchrone GWT utilitaire.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class UtilServiceGwtImpl implements UtilServiceGwt {

    private static final String MESSAGE_ERREUR = "Une erreur est survenue lors du mapping des criteres";

    private MapperDozerBean mapperDozerBean;

    /** Map de correspondance entre les objets GWT et les objets noyau. */
    private static final Map<String, String> CORRESPONDANCE_TYPE = new HashMap<String, String>();

    static {
        CORRESPONDANCE_TYPE.put(PersonneCriteresRechercheModel.class.getName(), PersonneCriteresRechercheDto.class.getName());
        CORRESPONDANCE_TYPE.put(CampagneCriteresRechercheModel.class.getName(), CampagneCriteresRechercheDto.class.getName());
        CORRESPONDANCE_TYPE.put(ActionCritereRechercheModel.class.getName(), ActionCritereRechercheDto.class.getName());
        CORRESPONDANCE_TYPE.put(RessourceCriteresRechercheModel.class.getName(), RessourceCriteresRechercheDto.class.getName());
        CORRESPONDANCE_TYPE.put(PersonneMoralCriteresRechercheModel.class.getName(), PersonneMoralCriteresRechercheDto.class.getName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> mapperCriteresRecherche(RemotePagingCriteriasGwt<? extends IsSerializable> criteres) {
        // on construit l'objet noyau suivant la correspondance
        try {
            final Class cls = Class.forName(CORRESPONDANCE_TYPE.get(criteres.getCriterias().getClass().getName()));
            final Constructor ct = cls.getConstructor();
            final Object criteriasDto = ct.newInstance();

            // on convertit les criteres en criteres noyau
            mapperDozerBean.map(criteres.getCriterias(), criteriasDto);
            final RemotePagingCriteriasDto criteresDto = new RemotePagingCriteriasDto(criteriasDto, criteres.getFirstResult(), criteres.getMaxResult());
            final List<RemotePagingSort> listSort = mapperDozerBean.mapList(criteres.getListeSorts(), RemotePagingSort.class);
            criteresDto.setListeSorts(listSort);

            resetConverter();

            final Map<String, String> map = BeanUtils.describe(criteresDto.getCriterias());
            map.putAll(BeanUtils.describe(criteresDto));

            // on nettoie la map
            final Set<String> keyset = new HashSet<String>(map.keySet());
            for (String key : keyset) {
                if (StringUtils.isBlank(map.get(key)) || key.equals("class") || key.equals("criterias")) {
                    map.remove(key);
                }
            }
            return map;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new TechnicalException(MESSAGE_ERREUR);
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new TechnicalException(MESSAGE_ERREUR);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new TechnicalException(MESSAGE_ERREUR);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new TechnicalException(MESSAGE_ERREUR);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new TechnicalException(MESSAGE_ERREUR);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new TechnicalException(MESSAGE_ERREUR);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new TechnicalException(MESSAGE_ERREUR);
        }
    }

    /**
     * Enregistrement des converter.
     */
    private void resetConverter() {
        ConvertUtils.deregister();
        ConvertUtils.register(new StringConverter(), String.class);
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

	@Override
	public List<IdentifiantLibelleOuiNonModel> getListeOuiNon()
			throws GwtRunTimeExceptionGwt {
		return IdentifiantLibelleOuiNonModel.getListSuggestions();
	}
}
