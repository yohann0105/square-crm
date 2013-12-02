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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.square.client.gwt.client.exception.ConfirmationCreationOpportuniteExceptionGwt;
import com.square.client.gwt.client.model.ActionCreationModel;
import com.square.client.gwt.client.model.ActionCritereRechercheModel;
import com.square.client.gwt.client.model.ActionModel;
import com.square.client.gwt.client.model.ActionModificationModel;
import com.square.client.gwt.client.model.ActionRechercheModel;
import com.square.client.gwt.client.model.ActionResultatModel;
import com.square.client.gwt.client.model.ActionSyntheseModel;
import com.square.client.gwt.client.model.CritereActionSyntheseModel;
import com.square.client.gwt.client.model.HistoriqueCommentaireModel;
import com.square.client.gwt.client.model.StackModel;
import com.square.client.gwt.client.service.ActionServiceGwt;
import com.square.core.model.dto.ActionCreationDto;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.ActionDto;
import com.square.core.model.dto.ActionModificationDto;
import com.square.core.model.dto.ActionRechercheDto;
import com.square.core.model.dto.ActionResultatDto;
import com.square.core.model.dto.ActionSyntheseDto;
import com.square.core.model.dto.CritereActionSyntheseDto;
import com.square.core.model.dto.HistoriqueCommentaireDto;
import com.square.core.model.exception.ConfirmationCreationOpportuniteException;
import com.square.core.service.interfaces.ActionService;

/**
 * Implémentation du serveur des services GWT pour les services des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionServiceGwtImpl implements ActionServiceGwt {

    private SimpleDateFormat sdfHeuresMinutes = new SimpleDateFormat("HH:mm");

    private ActionService actionService;

    private MapperDozerBean mapperDozerBean;

    @Override
    public RemotePagingResultsGwt<ActionRechercheModel> rechercherActionParCritere(RemotePagingCriteriasGwt<ActionCritereRechercheModel> criteres) {
        final ActionCritereRechercheDto criterias = mapperDozerBean.map(criteres.getCriterias(), ActionCritereRechercheDto.class);

        final RemotePagingCriteriasDto<ActionCritereRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criterias, criteres.getFirstResult(), criteres.getMaxResult());
        final List<RemotePagingSort> listSort = mapperDozerBean.mapList(criteres.getListeSorts(), RemotePagingSort.class);
        criteresDto.setListeSorts(listSort);
        final RemotePagingResultsDto<ActionRechercheDto> resultatDto = actionService.rechercherActionParCritere(criteresDto);
        final RemotePagingResultsGwt<ActionRechercheModel> resultatGwt = new RemotePagingResultsGwt<ActionRechercheModel>();
        final List<ActionRechercheModel> listActionGwt = new ArrayList<ActionRechercheModel>();
        if (resultatDto != null && resultatDto.getListResults() != null && !resultatDto.getListResults().isEmpty()) {
            for (ActionRechercheDto actionRechercheDto : resultatDto.getListResults()) {
                listActionGwt.add(convertActionRechercheToModelGwt(actionRechercheDto));
            }
        }
        resultatGwt.setListResults(listActionGwt);
        resultatGwt.setTotalResults(resultatDto.getTotalResults());
        return resultatGwt;
    }

    @Override
    public void transfererActionsParCritere(ActionCritereRechercheModel criteres, Long idAgenceCible, Long idRessourceCible) {
        final ActionCritereRechercheDto criteresDto = mapperDozerBean.map(criteres, ActionCritereRechercheDto.class);
        actionService.transfererActionsParCritere(criteresDto, idAgenceCible, idRessourceCible);
    }

    @Override
    public ActionCreationModel creerAction(ActionCreationModel action) {
        final ActionCreationDto actionDto = mapperDozerBean.map(action, ActionCreationDto.class);
        // Création de la date d'action.
        if (action.getDateAction() != null) {
            actionDto.setDateAction(convertDateHeureToCalendar(action.getDateAction(), action.getHeuresAction()));
        }

        final ActionCreationDto resultDto = actionService.creerAction(actionDto);
        return mapperDozerBean.map(resultDto, ActionCreationModel.class);
    }

    @Override
    public ActionResultatModel modifierAction(ActionModificationModel actionModificationModel) {
        final ActionModificationDto actionModificationDto = mapperDozerBean.map(actionModificationModel, ActionModificationDto.class);
        if (actionModificationModel.getDateAction() != null) {
            actionModificationDto.setDateAction(convertDateHeureToCalendar(actionModificationModel.getDateAction(), actionModificationModel.getHeureAction()));
        }
        if (actionModificationModel.getDuree() != null) {
            actionModificationDto.setIdDuree(actionModificationModel.getDuree().getIdentifiant());
        }
        ActionResultatDto resultatDto = null;
        try {
            resultatDto = actionService.modifierAction(actionModificationDto);
        }
        catch (ConfirmationCreationOpportuniteException confirmationException) {
            throw new ConfirmationCreationOpportuniteExceptionGwt(confirmationException.getMessage());
        }
        return mapperDozerBean.map(resultatDto, ActionResultatModel.class);
    }

    @Override
    public ActionModel rechercherActionParIdentifiant(Long idAction) {
        final ActionDto actionDto = actionService.rechercherActionParIdentifiant(idAction);
        return convertActionToModelGwt(actionDto);
    }

    @Override
    public List<HistoriqueCommentaireModel> rechercherNotesActions(Long idAction) {
        final List<HistoriqueCommentaireDto> historiqueDto = actionService.rechercherNotesActions(idAction);
        final List<HistoriqueCommentaireModel> historiqueModel = mapperDozerBean.mapList(historiqueDto, HistoriqueCommentaireModel.class);
        return historiqueModel;
    }

    @Override
    public List<StackModel<ActionSyntheseModel>> recupererActionsSynthese(CritereActionSyntheseModel critere) {
        final CritereActionSyntheseDto critereDto = mapperDozerBean.map(critere, CritereActionSyntheseDto.class);
        final List<Stack<ActionSyntheseDto>> resultatDto = actionService.recupererActionsSynthese(critereDto);
        final List<StackModel<ActionSyntheseModel>> resultatModel = new ArrayList<StackModel<ActionSyntheseModel>>();
        for (Stack<ActionSyntheseDto> pile : resultatDto) {
            final StackModel<ActionSyntheseModel> pileModel = new StackModel<ActionSyntheseModel>();
            for (ActionSyntheseDto actionDto : pile) {
                final ActionSyntheseModel actionSyntheseModel = mapperDozerBean.map(actionDto, ActionSyntheseModel.class);
                // Création de l'heure de l'action
                String heure = "";
                heure = sdfHeuresMinutes.format(actionDto.getDateAction().getTime());
                actionSyntheseModel.setHeureAction(heure);
                // Création de l'heure de l'action terminée
                String heureTerminee = null;
                if (actionDto.getDateActionTerminee() != null) {
                    heureTerminee = sdfHeuresMinutes.format(actionDto.getDateActionTerminee().getTime());
                }
                actionSyntheseModel.setHeureActionTerminee(heureTerminee);
                pileModel.push(actionSyntheseModel);
            }
            resultatModel.add(pileModel);
        }
        return resultatModel;
    }

    /**
     * Convertit Le DTO Action en objet du modèle pour GWT.
     * @param action l'actions à convertir.
     * @return l'action convertie pour GWT.
     */
    private ActionModel convertActionToModelGwt(ActionDto action) {
        final ActionModel actionModel = mapperDozerBean.map(action, ActionModel.class);
        // On récupère l'heure de l'action pour la stocker dans un champ supplémentaire
        actionModel.setHeureAction(sdfHeuresMinutes.format(action.getDateAction().getTime()));
        return actionModel;
    }

    private Calendar convertDateHeureToCalendar(String date, String heure) {
        final Calendar dateAction = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dateAction.setTime(sdf.parse(date));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        if (heure != null && !("".equals(heure))) {
            final String[] result = heure.split(":");
            dateAction.set(Calendar.HOUR_OF_DAY, Integer.valueOf(result[0]));
            dateAction.set(Calendar.MINUTE, Integer.valueOf(result[1]));
        }
        return dateAction;
    }

    /**
     * Convertit Le DTO ActionRechercheDto en objet du modèle pour GWT.
     * @param actionRechercheDto l'action à convertir.
     * @return l'action convertie pour GWT.
     */
    private ActionRechercheModel convertActionRechercheToModelGwt(ActionRechercheDto actionRechercheDto) {
        final ActionRechercheModel actionRechercheModel = mapperDozerBean.map(actionRechercheDto, ActionRechercheModel.class);
        // On récupère l'heure de l'action pour la stocker dans un champ supplémentaire
        actionRechercheModel.setHeureAction(sdfHeuresMinutes.format(actionRechercheDto.getDateAction().getTime()));
        return actionRechercheModel;
    }

    /**
     * Modifie la valeur de actionService.
     * @param actionService the actionService to set
     */
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }
}
