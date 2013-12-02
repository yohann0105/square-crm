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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.client.gwt.client.model.AgendaModel;
import com.square.client.gwt.client.model.AgendasDisponiblesCriteresModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.RendezVousModel;
import com.square.client.gwt.client.service.AgendaServiceGwt;
import com.square.core.model.dto.AgendasDisponiblesCriteresDto;
import com.square.core.model.dto.RendezVousCriteresRechercheDto;
import com.square.core.model.dto.RendezVousDto;
import com.square.core.service.interfaces.AgendaService;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Implémentation du serveur des services GWT pour les services de l'agenda.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class AgendaServiceGwtImpl implements AgendaServiceGwt {

    private AgendaService agendaService;

    private SquareMappingService squareMappingService;

    private MapperDozerBean mapperDozerBean;

    @Override
    public AgendaModel rechercherActionsAgenda(Long idRessource, int unite, int valeur, Date dateReference) {
        final Calendar dateReferenceCalendar = Calendar.getInstance();
        dateReferenceCalendar.setTime(dateReference);
        if (unite == 0 && valeur != 0) {
            dateReferenceCalendar.add(Calendar.WEEK_OF_YEAR, valeur);
        }
        else if (unite == 1 && valeur != 0) {
            dateReferenceCalendar.add(Calendar.MONTH, valeur);
        } else if (valeur == 0) {
            dateReferenceCalendar.setTime(Calendar.getInstance().getTime());
        }
        // AG : faire un set 0 et pas un clear sur l'heure car sinon ce n'est pas pris en compte
        dateReferenceCalendar.set(Calendar.HOUR_OF_DAY, 0);
        dateReferenceCalendar.clear(Calendar.MINUTE);
        dateReferenceCalendar.clear(Calendar.SECOND);
        dateReferenceCalendar.clear(Calendar.MILLISECOND);

        // Date de début
        final Calendar dateDebut = Calendar.getInstance();
        dateDebut.setTime(dateReferenceCalendar.getTime());
        // Demande sur une semaine
        if (unite == 0) {
            dateDebut.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        // Si décalage d'un mois
        else if (unite == 1) {
            dateDebut.set(Calendar.DAY_OF_MONTH, dateDebut.getActualMinimum(Calendar.DAY_OF_MONTH));
        }

        // Date de fin
        final Calendar dateFin = Calendar.getInstance();
        dateFin.setTime(dateReferenceCalendar.getTime());
        if (unite == 0) {
            dateFin.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        }
        else if (unite == 1) {
            dateFin.set(Calendar.DAY_OF_MONTH, dateFin.getActualMaximum(Calendar.DAY_OF_MONTH));
        }

        // Appel au service de récupération
        final RendezVousCriteresRechercheDto criteresDto = new RendezVousCriteresRechercheDto();
        criteresDto.setDateMinDateDebut(dateDebut);
        criteresDto.setDateMaxDateDebut(dateFin);
        criteresDto.getIdsStatut().add(squareMappingService.getIdStatutAFaire());
        criteresDto.getIdsStatut().add(squareMappingService.getIdStatutTerminer());
        criteresDto.setIdRessource(idRessource);
        final List<RendezVousDto> listeDto = agendaService.rechercherRendezVousParCriteres(criteresDto);

        // Création de l'agenda model
        final List<RendezVousModel> listeModel = mapperDozerBean.mapList(listeDto, RendezVousModel.class);

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final SimpleDateFormat sdfMois = new SimpleDateFormat("MMMM", Locale.FRENCH);

        final AgendaModel agenda = new AgendaModel();
        agenda.setRendezVous(listeModel);
        agenda.setDateDebut(sdf.format(dateDebut.getTime()));
        agenda.setDateFin(sdf.format(dateFin.getTime()));
        agenda.setMoisDebut(sdfMois.format(dateDebut.getTime()));
        agenda.setMoisFin(sdfMois.format(dateFin.getTime()));
        agenda.setAnneeDebut(String.valueOf(dateDebut.get(Calendar.YEAR)));
        agenda.setAnneeFin(String.valueOf(dateFin.get(Calendar.YEAR)));
        agenda.setJourDebut(String.valueOf(dateDebut.get(Calendar.DAY_OF_MONTH)));
        agenda.setJourFin(String.valueOf(dateFin.get(Calendar.DAY_OF_MONTH)));
        agenda.setDateReference(dateReferenceCalendar.getTime());

        return agenda;
    }

    @Override
    public List<DimensionRessourceModel> rechercherAgendasDisponibles(AgendasDisponiblesCriteresModel criteres) {
        final AgendasDisponiblesCriteresDto criteresDto = (AgendasDisponiblesCriteresDto) mapperDozerBean.map(criteres, AgendasDisponiblesCriteresDto.class);
        return mapperDozerBean.mapList(agendaService.rechercherAgendasDisponibles(criteresDto), DimensionRessourceModel.class);
    }

    /**
     * Setter function.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the value of agendaService.
     * @param agendaService the agendaService to set
     */
    public void setAgendaService(AgendaService agendaService) {
        this.agendaService = agendaService;
    }
}
