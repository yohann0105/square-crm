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
package com.square.client.gwt.client.service;

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.client.gwt.client.model.CotisationCriteresRechercheModel;
import com.square.client.gwt.client.model.CotisationRechercheModel;

/**
 * Interface synchrone des services GWT pour le service des cotisations.
 * @author Farh ZARROUK (farh.zarrouk@gmail.com) - SCUB
 */

@RemoteServiceRelativePath(value = "handler/cotisationService")
public interface CotisationServiceGwt extends RemoteService {

    CotisationRechercheModel rechercherCotisationParCriteres(CotisationCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

}
