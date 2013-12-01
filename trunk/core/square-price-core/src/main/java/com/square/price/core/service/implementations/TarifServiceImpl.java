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
package com.square.price.core.service.implementations;

import com.square.price.core.dto.CritereDto;
import com.square.price.core.dto.TarifCriteresDto;
import com.square.price.core.dto.TarifReponsesDto;
import com.square.price.core.service.interfaces.TarifService;

/**
 * .
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public class TarifServiceImpl implements TarifService {

	private static final Float MONTANT = 30f;

	@Override
	public CritereDto getCritereParIdentifiant(Integer identifiantCritere,
			Integer idApplication) {
		final CritereDto critere = new CritereDto();
		critere.setIdentifiant(1);
		critere.setLibelle("Critère 1");
		return critere;
	}

	@Override
	public TarifReponsesDto getTarifParCriteres(TarifCriteresDto criteres) {
		final TarifReponsesDto tarif = new TarifReponsesDto();
		tarif.setMontant(MONTANT);
		return tarif;
	}
}
