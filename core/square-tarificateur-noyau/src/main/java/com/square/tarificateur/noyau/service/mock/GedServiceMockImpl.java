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
package com.square.tarificateur.noyau.service.mock;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.document.core.dto.CriteresRechercheDocumentDto;
import com.square.document.core.dto.DocumentDto;
import com.square.document.core.dto.RetourAjoutDocumentDto;
import com.square.document.core.service.interfaces.GedService;
import com.square.tarificateur.noyau.util.MessageKeyUtil;

/**
 * Mock GED pour filtrer les branchements.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class GedServiceMockImpl implements GedService {

    private Logger logger = RootLogger.getLogger(GedServiceMockImpl.class);

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    private GedService gedServiceReal;

    @Override
    public RetourAjoutDocumentDto ajouterDocument(DocumentDto document, String utilisateur) {
        throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_APPLICATION_GED_INDISPONIBLE));
    }

    @Override
    public List<DocumentDto> getListeDocumentsByCriteres(CriteresRechercheDocumentDto criteres, String utilisateur) {
        return gedServiceReal.getListeDocumentsByCriteres(criteres, utilisateur);
    }

    @Override
    public void transfererDocumentPersonne(String numAdherentSource, String numAdherentDestination, String utilisateur) {
        logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_GED_TRANSFERER_DOCUMENT_PERSONNE,
        		 new String[] {numAdherentSource, numAdherentDestination}));
    }

    /**
     * Set the value of gedServiceReal.
     * @param gedServiceReal the gedServiceReal to set
     */
    public void setGedServiceReal(GedService gedServiceReal) {
        this.gedServiceReal = gedServiceReal;
    }

	@Override
	public DocumentDto getDocumentByCriteres(
			CriteresRechercheDocumentDto criteres, String utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

}
