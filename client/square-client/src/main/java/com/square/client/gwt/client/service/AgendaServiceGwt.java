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

import java.util.Date;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.client.gwt.client.model.AgendaModel;
import com.square.client.gwt.client.model.AgendasDisponiblesCriteresModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;

/**
 * Interface synchrone des services GWT pour le service de l'agenda.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/agendaService")
public interface AgendaServiceGwt extends RemoteService {

    /**
     * Recherche les actions pour l'agenda en fonctions des critères.
     * @param idRessource idRessource
     * @param unite unite
     * @param valeur valeur
     * @param dateReference dateReference
     * @return la liste des actions correspondant
     */
    AgendaModel rechercherActionsAgenda(Long idRessource, int unite, int valeur, Date dateReference) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les agendas disponibles pour l'utilisateur connecté.
     * @param criteres criteres de recherche
     * @return la liste des agendas
     * @throws GwtRunTimeExceptionGwt
     */
    List<DimensionRessourceModel> rechercherAgendasDisponibles(AgendasDisponiblesCriteresModel criteres) throws GwtRunTimeExceptionGwt;
}
