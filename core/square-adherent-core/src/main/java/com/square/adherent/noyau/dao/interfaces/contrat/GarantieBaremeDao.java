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
package com.square.adherent.noyau.dao.interfaces.contrat;

import java.util.List;

import com.square.adherent.noyau.dto.adherent.contrat.CoupleBaremeDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBaremeCriteresDto;
import com.square.adherent.noyau.model.data.contrat.GarantieBareme;

/**
 * Interface d'accès aux données liées aux garanties baremes.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface GarantieBaremeDao {

    /**
     * Recherche les garanties correspondant à des critères.
     * @param criteres les critères de recherche.
     * @return la liste des garanties du contrat.
     */
    List<GarantieBareme> getListeGarantiesBaremesByCriteres(GarantieBaremeCriteresDto criteres);

    /**
     * Recherche les couples de baremes correspondant à des critères.
     * @param criteres les critères de recherche.
     * @return la liste des couples de baremes.
     */
    List<CoupleBaremeDto> getListeCouplesBaremesByCriteres(GarantieBaremeCriteresDto criteres);
}
