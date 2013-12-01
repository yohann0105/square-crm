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
import com.square.composant.tarificateur.square.client.model.adhesion.InfosGlobalesAdhesionModel;
import com.square.composant.tarificateur.square.client.model.transfo.aia.DemandeTransformationAiaModel;
import com.square.composant.tarificateur.square.client.model.transfo.aia.ResultatTransformationAiaModel;

/**
 * Interface des services liés aux services du tarificateur square pour la transfo aia.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/tarificateurTransformationAiaService")
public interface TarificateurTransformationAiaServiceGwt extends RemoteService {
    /**
     * Transforme un devis en devis AIA.
     * @param demandeTransformationAia demandeTransformationAia
     * @return le resultat de la transformation.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    ResultatTransformationAiaModel transformerDevisAia(DemandeTransformationAiaModel demandeTransformationAia) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère les informations d'adhésion d'un devis.
     * @param idDevis identifiant du devis.
     * @return informations d'adhésion pour le devis.
     * @throws GwtRunTimeExceptionGwt exception levée par GWT.
     */
    InfosGlobalesAdhesionModel getInfosAdhesion(Long idDevis) throws GwtRunTimeExceptionGwt;
}
