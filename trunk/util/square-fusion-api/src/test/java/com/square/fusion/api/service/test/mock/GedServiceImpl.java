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
package com.square.fusion.api.service.test.mock;

import java.util.List;

import com.square.document.core.dto.CriteresRechercheDocumentDto;
import com.square.document.core.dto.DocumentDto;
import com.square.document.core.dto.RetourAjoutDocumentDto;
import com.square.document.core.service.interfaces.GedService;

/**
 * Mock du service de la GED.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class GedServiceImpl implements GedService {

    @Override
    public RetourAjoutDocumentDto ajouterDocument(DocumentDto document, String utilisateur) {
        return null;
    }

    @Override
    public List<DocumentDto> getListeDocumentsByCriteres(CriteresRechercheDocumentDto criteres, String utilisateur) {
        return null;
    }

    @Override
    public void transfererDocumentPersonne(String numAdherentSource, String numAdherentDestination, String utilisateur) {
    }

	@Override
	public DocumentDto getDocumentByCriteres(
			CriteresRechercheDocumentDto criteres, String utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

}
