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
package com.square.composant.aide.gwt.client.service;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.composant.aide.gwt.client.model.AideModel;

/**
 * Service asynchrone des aides.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface AideServiceGwtAsync {

    /**
     * Recherche une aide par son identifiant.
     * @param id id de l'aide.
     * @param callBack callback.
     * @throws GwtRunTimeExceptionGwt GwtRunTimeExceptionGwt.
     */
    void rechercherAide(Long id, AsyncCallback<AideModel> callBack) throws GwtRunTimeExceptionGwt;

    /**
     * Crée ou modifie une aide.
     * @param aide l'aide à créer ou modifier.
     * @param callBack callback
     * @throws GwtRunTimeExceptionGwt GwtRunTimeExceptionGwt
     */
    void creerOuModifierAide(AideModel aide, AsyncCallback<AideModel> callBack) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des aides par leurs identifiants.
     * @param listeIdsComposant la liste des identifiants des composants des aides à rechercher.
     * @param callBack callback.
     * @throws GwtRunTimeExceptionGwt GwtRunTimeExceptionGwt.
     */
    void rechercherIdsComposantsAides(List<Long> listeIdsComposant, AsyncCallback<List<Long>> callBack) throws GwtRunTimeExceptionGwt;
}
