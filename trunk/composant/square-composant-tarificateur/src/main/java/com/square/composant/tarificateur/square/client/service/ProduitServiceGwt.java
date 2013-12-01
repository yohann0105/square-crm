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

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.tarificateur.square.client.model.GammesProduitsCriteresModel;
import com.square.composant.tarificateur.square.client.model.IntegerLibelleModel;
import com.square.composant.tarificateur.square.client.model.ProduitCriteresModel;

/**
 * Interface de services pour les produits.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/produitService")
public interface ProduitServiceGwt extends RemoteService {

    /**
     * Récupère la liste des réseaux de gammes.
     * @param suggest la suggestion
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     * @return la liste des réseaux de gammes
     */
    List<IntegerLibelleModel> getListeReseauxGamme(String suggest) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère la liste des vétustés de gammes.
     * @param suggest la suggestion
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     * @return la liste des vétustés de gammes
     */
    List<IntegerLibelleModel> getListeVetustesGamme(String suggest) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère la liste des catégories de gammes.
     * @param suggest la suggestion
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     * @return la liste des catégories de gammes
     */
    List<IntegerLibelleModel> getListeCategoriesGamme(String suggest) throws GwtRunTimeExceptionGwt;

    /**
     * Retourne la liste des gammes de produits.
     * @param gammesProduitsCriteresGWT les critères de recherche
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     * @return une liste de gammes de produit
     */
    List<IntegerLibelleModel> getListeGammesProduits(GammesProduitsCriteresModel gammesProduitsCriteresGWT) throws GwtRunTimeExceptionGwt;

    /**
     * Retourne une liste de produits en fonction de critères de recherche.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     * @param produitCriteresGWT les critères de recherche
     * @return une liste de produits
     */
    List<IntegerLibelleModel> getListeProduits(ProduitCriteresModel produitCriteresGWT) throws GwtRunTimeExceptionGwt;
}
