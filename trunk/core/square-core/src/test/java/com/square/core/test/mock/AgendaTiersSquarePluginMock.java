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
package com.square.core.test.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.square.core.model.dto.RendezVousTiersCriteresDto;
import com.square.core.model.dto.RendezVousTiersDto;
import com.square.core.model.plugin.AgendaTiersSquarePlugin;

/**
 * Mock pour le plugin des agenda tiers de square.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class AgendaTiersSquarePluginMock implements AgendaTiersSquarePlugin {

    private static final int YEAR_2011 = 2011;

	private static final int ONZE = 11;

	private static final int QUATORZE = 15;

	private static final int VINGT_HUIT = 28;

	private static final Integer SOIXANTE = 60;

	private static final Integer CENT_QUATREVINGT = 180;

	@Override
    public List<RendezVousTiersDto> getListeRendezVous(RendezVousTiersCriteresDto criteres) {
        final List<RendezVousTiersDto> listeRendezVousTiers = new ArrayList<RendezVousTiersDto>();
        final RendezVousTiersDto rendezVous1 = new RendezVousTiersDto();
        rendezVous1.setDate(getCalendar(YEAR_2011, 7, VINGT_HUIT, 0, 10));
        rendezVous1.setNbMinutesDuree(SOIXANTE);
        rendezVous1.setTitre("Rendez vous 1");
        listeRendezVousTiers.add(rendezVous1);
        final RendezVousTiersDto rendezVous2 = new RendezVousTiersDto();
        rendezVous2.setDate(getCalendar(YEAR_2011, 7, VINGT_HUIT, 0, ONZE));
        rendezVous2.setNbMinutesDuree(SOIXANTE);
        rendezVous2.setTitre("Rendez vous 2");
        listeRendezVousTiers.add(rendezVous2);
        final RendezVousTiersDto rendezVous3 = new RendezVousTiersDto();
        rendezVous3.setDate(getCalendar(YEAR_2011, 7, VINGT_HUIT, 0, QUATORZE));
        rendezVous3.setNbMinutesDuree(CENT_QUATREVINGT);
        rendezVous3.setTitre("Rendez vous 3");
        listeRendezVousTiers.add(rendezVous3);
        return listeRendezVousTiers;
    }

    private Calendar getCalendar(int year, int month, int date, int minute, int hour) {
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, date);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.HOUR, hour);
        return calendar;
    }

}
