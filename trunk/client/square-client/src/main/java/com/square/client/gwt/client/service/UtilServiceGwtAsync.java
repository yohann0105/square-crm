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

import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.client.gwt.client.model.IdentifiantLibelleOuiNonModel;

/**
 * Interface asynchrone des services GWT utilitaires.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface UtilServiceGwtAsync {

    /**
     * Transforme des criteres de recherche en map libelle/valeur.
     * @param criteres criteres à convertir
     * @param asyncCallback le callback
     */
    void mapperCriteresRecherche(RemotePagingCriteriasGwt<? extends IsSerializable> criteres, AsyncCallback<Map<String, String>> asyncCallback);

    /**
     * Renvoie la liste des propositions pour les combos Oui/Non.
     * @param asyncCallback le callback
     */
    void getListeOuiNon(AsyncCallback<List<IdentifiantLibelleOuiNonModel>> asyncCallback);
}
