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
package com.square.client.gwt.server.service.aop;

import org.scub.foundation.framework.base.exception.CoreRunTimeException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.client.gwt.client.exception.ConfirmationCreationOpportuniteExceptionGwt;
import com.square.client.gwt.client.exception.ConfirmationDesactivationEserviceExceptionGwt;
import com.square.client.gwt.client.exception.ConfirmationImpacterFamilleExceptionGwt;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.core.model.exception.ConfirmationCreationOpportuniteException;
import com.square.core.model.exception.ConfirmationDesactivationEserviceException;
import com.square.core.model.exception.ConfirmationImpacterFamilleException;
import com.square.core.model.exception.ControleIntegriteException;

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
        if (ex instanceof ControleIntegriteException) {
            throw new ControleIntegriteExceptionGwt((RapportModel) mapperDozerBean.map(((ControleIntegriteException) ex).getRapport(), RapportModel.class));
        } else if (ex instanceof com.square.tarificateur.noyau.exception.ControleIntegriteException) {
            throw new com.square.composant.tarificateur.square.client.exception.ControleIntegriteExceptionGwt((RapportModel) mapperDozerBean.map(
                ((com.square.tarificateur.noyau.exception.ControleIntegriteException) ex).getRapport(), RapportModel.class));
        } else if (ex instanceof ConfirmationCreationOpportuniteException) {
            throw new ConfirmationCreationOpportuniteExceptionGwt(ex.getMessage());
        } else if (ex instanceof ConfirmationImpacterFamilleException) {
            throw new ConfirmationImpacterFamilleExceptionGwt(ex.getMessage());
        } else if (ex instanceof ConfirmationDesactivationEserviceException) {
            throw new ConfirmationDesactivationEserviceExceptionGwt(ex.getMessage());
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
