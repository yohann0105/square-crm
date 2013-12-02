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
package com.square.client.gwt.server.util;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.square.client.gwt.client.model.PersonneMoraleModel;
import com.square.client.gwt.client.model.PersonneMoraleRechercheModel;
import com.square.client.gwt.client.model.PersonneMoraleRelationModel;
import com.square.client.gwt.client.model.PersonneMoraleSimpleModel;
import com.square.client.gwt.client.model.PersonneRelationModel;
import com.square.client.gwt.client.model.RelationInfosModel;

/**
 * Convertisseur spécifique pour toutes les classes qui touchent aux personnes morales afin d'appliquer des règles de formatage.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PersonneMoraleFormatAop {
    /**
     * Interception des methodes qui retournent un objet de type PersonneMoraleModel.
     * @param retVal l'objet retourné.
     */
    @SuppressWarnings("unchecked")
    public void formatPm(Object retVal) {
        if (retVal instanceof PersonneMoraleModel) {
            final PersonneMoraleModel personne = (PersonneMoraleModel) retVal;
            personne.setRaisonSociale(personne.getRaisonSociale().toUpperCase());
        } else if (retVal instanceof PersonneMoraleSimpleModel) {
            final PersonneMoraleSimpleModel personne = (PersonneMoraleSimpleModel) retVal;
            personne.setRaisonSociale(personne.getRaisonSociale().toUpperCase());
        } else if (retVal instanceof RemotePagingResultsGwt<?>) {
            if (((RemotePagingResultsGwt<?>) retVal).getListResults().size() > 0
                && ((RemotePagingResultsGwt<?>) retVal).getListResults().get(0) instanceof PersonneMoraleRechercheModel) {
                final RemotePagingResultsGwt<PersonneMoraleRechercheModel> result = (RemotePagingResultsGwt<PersonneMoraleRechercheModel>) retVal;
                for (PersonneMoraleRechercheModel personne : result.getListResults()) {
                    personne.setRaisonSociale(personne.getRaisonSociale().toUpperCase());
                }
            }
        } else if (retVal instanceof ArrayList<?> && ((ArrayList<?>) retVal).size() > 0 && ((ArrayList<?>) retVal).get(0) instanceof RelationInfosModel<?>) {
            final List<RelationInfosModel<? extends PersonneRelationModel>> relations = (ArrayList<RelationInfosModel<? extends PersonneRelationModel>>) retVal;
            for (RelationInfosModel<? extends PersonneRelationModel> relation : relations) {
                if (relation.getPersonne() instanceof PersonneMoraleRelationModel) {
                    final PersonneMoraleRelationModel personne = (PersonneMoraleRelationModel) relation.getPersonne();
                    personne.setRaisonSociale(personne.getRaisonSociale().toUpperCase());
                }
            }
        }
    }
}
