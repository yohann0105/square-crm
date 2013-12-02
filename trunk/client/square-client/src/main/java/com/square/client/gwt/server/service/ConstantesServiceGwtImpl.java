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
package com.square.client.gwt.server.service;

import java.util.List;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.client.gwt.client.model.ActionNotificationInfosModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.PaysSimpleModel;
import com.square.client.gwt.client.service.ConstantesServiceGwt;
import com.square.client.gwt.server.servlet.ExporterRechercheServlet;
import com.square.client.gwt.server.util.FormaterTelephoneUtil;
import com.square.core.model.dto.ActionNotificationInfosDto;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Implémentation serveur des services GWT pour les constantes.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class ConstantesServiceGwtImpl implements ConstantesServiceGwt {

    private SquareMappingService squareMappingService;

    /** Le mapper. */
    private MapperDozerBean mapperDozerBean;

    @Override
    public ConstantesModel getConstantes() {
        final ConstantesModel constantes = mapperDozerBean.map(squareMappingService.getConstantes(), ConstantesModel.class);

        final PaysSimpleModel paysFranceModel = mapperDozerBean.map(constantes.getPaysFrance(), PaysSimpleModel.class);
        paysFranceModel.setFormatTelephone(FormaterTelephoneUtil.formterNumTelephone(paysFranceModel.getId()));
        constantes.setPaysFrance(paysFranceModel);

        // constantes de la servlet d'export
        constantes.setUrlServletExporterRecherche(ExporterRechercheServlet.URL_SERVLET_EXPORTER_RECHERCHE);
        constantes.setParamService(ExporterRechercheServlet.PARAM_SERVICE);
        constantes.setValueServiceRecherchePersonnePhysique(ExporterRechercheServlet.VALUE_SERVICE_RECHERCHE_PERSONNE_PHYSIQUE);
        constantes.setValueServiceRechercheCampagne(ExporterRechercheServlet.VALUE_SERVICE_RECHERCHE_CAMPAGNE);
        constantes.setValueServiceRechercheAction(ExporterRechercheServlet.VALUE_SERVICE_RECHERCHE_ACTION);
        constantes.setValueServiceRechercheRessource(ExporterRechercheServlet.VALUE_SERVICE_RECHERCHE_RESSOURCE);
        constantes.setValueServiceRecherchePersonneMorale(ExporterRechercheServlet.VALUE_SERVICE_RECHERCHE_PERSONNE_MORALE);

        return constantes;
    }

    /**
     * Recuperer la liste des notifications.
     * @return la liste des notifications
     */
    public List<ActionNotificationInfosModel> getListeActionNotifications() {
        final List<ActionNotificationInfosDto> resultDto = squareMappingService.getListeActionNotifications();
        return mapperDozerBean.mapList(resultDto, ActionNotificationInfosModel.class);
    }

    /**
     * Recuperer la liste des notifications.
     * @param id l'identifiant de la nortification
     * @return la notification
     */
    public ActionNotificationInfosModel getActionNotificationParId(Long id) {
        final ActionNotificationInfosDto resultDto = squareMappingService.getActionNotificationParId(id);
        return mapperDozerBean.map(resultDto, ActionNotificationInfosModel.class);
    }

    /**
     * Set the squareMappingService value.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Modifie mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    @Override
    public Long getIdNotification(String dateAction, String dateNotification) {
        // TODO A implementer
        return null;
    }
}
