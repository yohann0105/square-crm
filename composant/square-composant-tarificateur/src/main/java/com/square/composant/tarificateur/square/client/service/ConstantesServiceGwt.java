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
package com.square.composant.tarificateur.square.client.service;

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;

/**
 * Interface synchrone des services GWT pour la récupération des constantes.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/constantesTarificateurService")
public interface ConstantesServiceGwt extends RemoteService {

    /**
     * Récuperes les constantes.
     * @return les constantes
     * @throws GwtRunTimeExceptionGwt
     */
    ConstantesModel getConstantes() throws GwtRunTimeExceptionGwt;

}
