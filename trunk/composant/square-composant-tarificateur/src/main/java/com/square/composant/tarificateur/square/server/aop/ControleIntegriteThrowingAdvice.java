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
package com.square.composant.tarificateur.square.server.aop;

import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.CoreRunTimeException;
import org.scub.foundation.framework.base.exception.PermissionException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.gwt.module.client.exception.BusinessExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.exception.PermissionExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.exception.TechnicalExceptionGwt;

import com.square.composant.tarificateur.square.client.exception.ControleIntegriteExceptionGwt;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.tarificateur.noyau.exception.ControleIntegriteException;

/**
 * Advisor pour intercepter les exception du noyau et les transformer en exception GWT.
 * @author Goumard stephane (stephane.goumard@scub.net) - SCUB
 */
public class ControleIntegriteThrowingAdvice {
    /**
     * Mapper dozer.
     */
    private MapperDozerBean mapperDozerBean;

    /**
     * Methode qui intercepte l'exception si elle du type CoreRunTimeException.
     * @param ex l'exception levée.
     */
    public void afterThrowing(CoreRunTimeException ex) {
        if (ex instanceof BusinessException) {
            throw new BusinessExceptionGwt(ex.getMessage());
        } else if (ex instanceof PermissionException) {
            throw new PermissionExceptionGwt(ex.getMessage());
        } else if (ex instanceof ControleIntegriteException) {
            throw new ControleIntegriteExceptionGwt((RapportModel) mapperDozerBean.map(((ControleIntegriteException) ex).getRapport(), RapportModel.class));
        } else {
            throw new TechnicalExceptionGwt(ex.getMessage());
        }
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }
}
