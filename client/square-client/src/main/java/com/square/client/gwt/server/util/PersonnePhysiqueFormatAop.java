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
/**
 * 
 */
package com.square.client.gwt.server.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.model.PersonnePhysiqueRelationModel;
import com.square.client.gwt.client.model.PersonneRelationModel;
import com.square.client.gwt.client.model.PersonneSimpleModel;
import com.square.client.gwt.client.model.RelationInfosModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosAdhesionModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPersonneModel;

/**
 * Conventer specifique pour toutes les classes qui touche au PP afin d'appliquer des regles de formatage.
 * @author Goumard Stephane (stephane.goumard@scub.net) - SCUB
 */
public class PersonnePhysiqueFormatAop {
    /**
     * Interception des methodes qui retournent un objet de type PersonneModel.
     * @param retVal l'objet retourné.
     */
    @SuppressWarnings("unchecked")
    public void formatPp(Object retVal) {
        final char[] delimiters = {' ', '-', '_' };
        if (retVal instanceof PersonneModel) {
            final PersonneModel personne = (PersonneModel) retVal;
            if (personne.getNom() != null && !"".equals(personne.getNom())) {
                personne.setNom(personne.getNom().toUpperCase());
            }
            if (personne.getPrenom() != null && !"".equals(personne.getPrenom())) {
                final String prenomMisEnForme = WordUtils.capitalizeFully(personne.getPrenom(), delimiters);
                personne.setPrenom(prenomMisEnForme);
            }
        } else if (retVal instanceof PersonneSimpleModel) {
            final PersonneSimpleModel personne = (PersonneSimpleModel) retVal;
            if (personne.getNom() != null && !"".equals(personne.getNom())) {
                personne.setNom(personne.getNom().toUpperCase());
            }
            if (personne.getPrenom() != null && !"".equals(personne.getPrenom())) {
                final String prenomMisEnForme = WordUtils.capitalizeFully(personne.getPrenom(), delimiters);
                personne.setPrenom(prenomMisEnForme);
            }
        } else if (retVal instanceof RemotePagingResultsGwt<?>) {
            if (((RemotePagingResultsGwt<?>) retVal).getListResults().size() > 0
                && ((RemotePagingResultsGwt<?>) retVal).getListResults().get(0) instanceof PersonneSimpleModel) {
                final RemotePagingResultsGwt<PersonneSimpleModel> result = (RemotePagingResultsGwt<PersonneSimpleModel>) retVal;
                for (PersonneSimpleModel personne : result.getListResults()) {
                    if (personne.getNom() != null && !"".equals(personne.getNom())) {
                        personne.setNom(personne.getNom().toUpperCase());
                    }
                    if (personne.getPrenom() != null && !"".equals(personne.getPrenom())) {
                        final String prenomMisEnForme = WordUtils.capitalizeFully(personne.getPrenom(), delimiters);
                        personne.setPrenom(prenomMisEnForme);
                    }
                }
            }
        } else if (retVal instanceof ArrayList<?> && ((ArrayList<?>) retVal).size() > 0 && ((ArrayList<?>) retVal).get(0) instanceof RelationInfosModel<?>) {
            final List<RelationInfosModel<? extends PersonneRelationModel>> relations = (ArrayList<RelationInfosModel<? extends PersonneRelationModel>>) retVal;
            for (RelationInfosModel<? extends PersonneRelationModel> relation : relations) {
                if (relation.getPersonne() instanceof PersonnePhysiqueRelationModel) {
                    final PersonnePhysiqueRelationModel personne = (PersonnePhysiqueRelationModel) relation.getPersonne();
                    if (personne.getNom() != null && !"".equals(personne.getNom())) {
                        personne.setNom(personne.getNom().toUpperCase());
                    }
                    if (personne.getPrenom() != null && !"".equals(personne.getPrenom())) {
                        final String prenomMisEnForme = WordUtils.capitalizeFully(personne.getPrenom(), delimiters);
                        personne.setPrenom(prenomMisEnForme);
                    }
                }
            }
        } else if (retVal instanceof InfosAdhesionModel) {
            final InfosAdhesionModel infosAdhesion = (InfosAdhesionModel) retVal;
            if (infosAdhesion.getInfosPersonnes() != null && infosAdhesion.getInfosPersonnes().size() > 0) {
                for (InfosPersonneModel infosPersonne : infosAdhesion.getInfosPersonnes()) {
                    if (infosPersonne.getNom() != null && !"".equals(infosPersonne.getNom())) {
                        infosPersonne.setNom(infosPersonne.getNom().toUpperCase());
                    }
                    if (infosPersonne.getPrenom() != null && !"".equals(infosPersonne.getPrenom())) {
                        final String prenomMisEnForme = WordUtils.capitalizeFully(infosPersonne.getPrenom(), delimiters);
                        infosPersonne.setPrenom(prenomMisEnForme);
                    }
                }
            }
        }
    }
}
