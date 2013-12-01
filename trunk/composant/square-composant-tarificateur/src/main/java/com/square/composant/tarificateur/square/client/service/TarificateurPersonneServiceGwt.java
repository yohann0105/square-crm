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
import com.square.composant.tarificateur.square.client.model.adhesion.RelationAssureSocialModel;
import com.square.composant.tarificateur.square.client.model.doublons.PersonneDoublonModel;
import com.square.composant.tarificateur.square.client.model.doublons.RechercheDoublonCritereModel;
import com.square.composant.tarificateur.square.client.model.personne.AssureSocialModel;

/**
 * Interface des services liés aux services du tarificateur square.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/tarificateurPersonneService")
public interface TarificateurPersonneServiceGwt extends RemoteService {

    /**
     * Crée un assuré social.
     * @param assureSocial l'assure social à sauvegarder
     * @param idDevis id du devis porteur de l'assure
     * @param fromOuvertureOpp indique si l'appel provient de l'ouverture d'une opportunité
     * @return l'assure social sauvegardé
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    AssureSocialModel createAssureSocial(AssureSocialModel assureSocial, Long idDevis, boolean fromOuvertureOpp) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les relations familiales entre une personne et son assuré social.
     * On cherche dans le tarificateur et dans square.
     * @param idPersonne l'identifiant de la personne
     * @param idAssureSocial l'identifiant de l'assuré social
     * @param eidPersonne l'identifiant externe de la personne
     * @param eidAssureSocial l'identifiant externe de l'assuré social
     * @return la relation familiale
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    RelationAssureSocialModel rechercherRelationAssureSocial(Long idPersonne, Long idAssureSocial, Long eidPersonne, Long eidAssureSocial) throws GwtRunTimeExceptionGwt;

    /**
     * Recupere la liste des assures sociaux d'un devis.
     * @param idDevis l'id du devis
     * @return la liste des assures sociaux
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    List<AssureSocialModel> getListeAssuresSociaux(Long idDevis) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche de personne en doublons.
     * @param criteres les critères de recherche.
     * @return la liste des doublons.
     */
    List<PersonneDoublonModel> rechercherDoublonsPersonneParCriteres(RechercheDoublonCritereModel criteres) throws GwtRunTimeExceptionGwt;

}
