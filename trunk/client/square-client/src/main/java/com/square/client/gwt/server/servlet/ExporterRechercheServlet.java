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
package com.square.client.gwt.server.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.client.gwt.server.util.StringConverter;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.CampagneCriteresRechercheDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneMoralCriteresRechercheDto;
import com.square.core.model.dto.RessourceCriteresRechercheDto;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.CampagneService;
import com.square.core.service.interfaces.PersonneMoraleService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.RessourceService;

/**
 * Servlet pour exporter une recherche au format excel.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ExporterRechercheServlet extends HttpServlet {

    /** Url de la servlet d'export de recherche. */
    public static final String URL_SERVLET_EXPORTER_RECHERCHE = "servlet/exporterRecherche";

    /** Parametre pour identifier le service. */
    public static final String PARAM_SERVICE = "service";

    /** Parametre pour identifier le service. */
    public static final String VALUE_SERVICE_RECHERCHE_PERSONNE_PHYSIQUE = "exporterRecherchePersonnePhysique";

    /** Parametre pour identifier le service. */
    public static final String VALUE_SERVICE_RECHERCHE_CAMPAGNE = "exporterRechercheCampagne";

    /** Parametre pour identifier le service. */
    public static final String VALUE_SERVICE_RECHERCHE_ACTION = "exporterRechercheAction";

    /** Parametre pour identifier le service. */
    public static final String VALUE_SERVICE_RECHERCHE_RESSOURCE = "exporterRechercheRessource";

    /** Parametre pour identifier le service. */
    public static final String VALUE_SERVICE_RECHERCHE_PERSONNE_MORALE = "exporterRecherchePersonneMorale";

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 5304131663050937035L;

    private PersonnePhysiqueService personnePhysiqueService;

    private CampagneService campagneService;

    private ActionService actionService;

    private RessourceService ressourceService;

    private PersonneMoraleService personneMoraleService;

    @Override
    public void init() throws ServletException {
        super.init();
        final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        personnePhysiqueService = (PersonnePhysiqueService) context.getBean("personnePhysiqueService");
        campagneService = (CampagneService) context.getBean("campagneService");
        actionService = (ActionService) context.getBean("actionService");
        ressourceService = (RessourceService) context.getBean("ressourceService");
        personneMoraleService = (PersonneMoraleService) context.getBean("personneMoraleService");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(PARAM_SERVICE) == null) {
            throw new TechnicalException("Une erreur est survenue : des paramètres de servlet sont manquants");
        }

        FichierDto fichierDto = null;
        Object criterias = null;

        // on cree l'objet criterias
        if (req.getParameter(PARAM_SERVICE).equals(VALUE_SERVICE_RECHERCHE_PERSONNE_PHYSIQUE)) {
            criterias = new PersonneCriteresRechercheDto();
        } else if (req.getParameter(PARAM_SERVICE).equals(VALUE_SERVICE_RECHERCHE_CAMPAGNE)) {
            criterias = new CampagneCriteresRechercheDto();
        } else if (req.getParameter(PARAM_SERVICE).equals(VALUE_SERVICE_RECHERCHE_ACTION)) {
            criterias = new ActionCritereRechercheDto();
        } else if (req.getParameter(PARAM_SERVICE).equals(VALUE_SERVICE_RECHERCHE_RESSOURCE)) {
            criterias = new RessourceCriteresRechercheDto();
        } else if (req.getParameter(PARAM_SERVICE).equals(VALUE_SERVICE_RECHERCHE_PERSONNE_MORALE)) {
            criterias = new PersonneMoralCriteresRechercheDto();
        }
        // on remplit les criteres suivant la map
        final Map<String, String> mapParams = req.getParameterMap();
        final RemotePagingCriteriasDto criteres = recupererCriteres(mapParams, criterias);

        // on appelle le bon service et on recupere le fichier à partir des criteres
        if (req.getParameter(PARAM_SERVICE).equals(VALUE_SERVICE_RECHERCHE_PERSONNE_PHYSIQUE)) {
            fichierDto = personnePhysiqueService.exporterRecherchePersonneFullTextParCriteres(criteres);
        } else if (req.getParameter(PARAM_SERVICE).equals(VALUE_SERVICE_RECHERCHE_CAMPAGNE)) {
            fichierDto = campagneService.exporterRechercheCampagnesParCriteres(criteres);
        } else if (req.getParameter(PARAM_SERVICE).equals(VALUE_SERVICE_RECHERCHE_ACTION)) {
            fichierDto = actionService.exporterRechercheActionsParCriteres(criteres);
        } else if (req.getParameter(PARAM_SERVICE).equals(VALUE_SERVICE_RECHERCHE_RESSOURCE)) {
            fichierDto = ressourceService.exporterRechercheRessourceFullTextParCriteres(criteres);
        } else if (req.getParameter(PARAM_SERVICE).equals(VALUE_SERVICE_RECHERCHE_PERSONNE_MORALE)) {
            fichierDto = personneMoraleService.exporterRecherchePersonneMoraleParCriteres(criteres);
        }

        // Définition du type de la réponse
        resp.setContentType(fichierDto.getTypeMime());
        // Nom du fichier
        resp.setHeader("Content-disposition", "attachment; filename=" + fichierDto.getNom());
        // Copie du contenu du fichier dans le flux de sortie
        final OutputStream out = resp.getOutputStream();
        out.write(fichierDto.getData());
        out.close();
    }

    @SuppressWarnings("unchecked")
    private RemotePagingCriteriasDto<? extends IsSerializable> recupererCriteres(Map<String, String> map, Object criteres) {
        try {
            resetConverter();
            BeanUtils.populate(criteres, map);
            final RemotePagingCriteriasDto<? extends IsSerializable> criterias = new RemotePagingCriteriasDto(criteres, 0, Integer.MAX_VALUE);
            BeanUtils.populate(criterias, map);
            return criterias;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new TechnicalException("Une erreur est survenue lors de la récupération des criteres");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new TechnicalException("Une erreur est survenue lors de la récupération des criteres");
        }
    }

    /**
     * Enregistrement des converter.
     */
    private void resetConverter() {
        ConvertUtils.deregister();
        ConvertUtils.register(new StringConverter(), String.class);
    }
}
