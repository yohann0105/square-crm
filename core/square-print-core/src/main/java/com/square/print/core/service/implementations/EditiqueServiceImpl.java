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
package com.square.print.core.service.implementations;

import java.io.IOException;
import java.util.List;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.commons.io.IOUtils;
import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.exception.TechnicalException;

import com.square.print.core.dto.ModeleDevisDto;
import com.square.print.core.dto.adhesion.DocumentsBulletinsAdhesionDto;
import com.square.print.core.dto.adhesion.LettreAnnulationDto;
import com.square.print.core.service.interfaces.EditiqueMappingService;
import com.square.print.core.service.interfaces.EditiqueService;

/**
 * .
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public class EditiqueServiceImpl implements EditiqueService {

    /** Service EditiqueMapping. */
    private EditiqueMappingService editiqueMappingService;

	@Override
	public byte[] fusionnerFichiersPdfs(List<FichierDto> listeFichiers) {
		try {
			return IOUtils.toByteArray(this.getClass().getResourceAsStream("/matrice.pdf"));
		} catch (IOException e) {
			throw new TechnicalException(e.getMessage(), e);
		}
	}

	@Override
	public FichierDto genererPdfDocumentsBulletinsAdhesion(
			DocumentsBulletinsAdhesionDto documentsBulletinsAdhesionDto) {
		try {
			final byte[] contenuFichier = IOUtils.toByteArray(this.getClass().getResourceAsStream("/matrice.pdf"));
			final FichierDto fichier = new FichierDto("", Magic.getMagicMatch(contenuFichier).getMimeType(), contenuFichier);
			return fichier;
		} catch (IOException e) {
			throw new TechnicalException(e.getMessage(), e);
		} catch (MagicParseException e) {
			throw new TechnicalException(e.getMessage(), e);
		} catch (MagicMatchNotFoundException e) {
			throw new TechnicalException(e.getMessage(), e);
		} catch (MagicException e) {
			throw new TechnicalException(e.getMessage(), e);
		}
	}

	@Override
	public FichierDto getFichier(String nomFichier) {
		try {
			final byte[] contenuFichier = IOUtils.toByteArray(this.getClass().getResourceAsStream("/" + nomFichier));
			final FichierDto fichier = new FichierDto(nomFichier, Magic.getMagicMatch(contenuFichier).getMimeType(), contenuFichier);
			return fichier;
		} catch (IOException e) {
			throw new TechnicalException(e.getMessage(), e);
		} catch (MagicParseException e) {
			throw new TechnicalException(e.getMessage(), e);
		} catch (MagicMatchNotFoundException e) {
			throw new TechnicalException(e.getMessage(), e);
		} catch (MagicException e) {
			throw new TechnicalException(e.getMessage(), e);
		}
	}

	@Override
	public FichierDto getLettreAnnulation(LettreAnnulationDto lettreAnnulation) {
		try {
			final byte[] contenuFichier = IOUtils.toByteArray(this.getClass().getResourceAsStream("/"
					+ editiqueMappingService.getMapFichiersStatiques().get(editiqueMappingService.getConstanteIdModeleLettreAnnulation())));
			final ModeleDevisDto modele = editiqueMappingService.getModelDevisById(editiqueMappingService.getConstanteIdModeleLettreAnnulation());
			final FichierDto fichier = new FichierDto(modele.getLibelle(),
					Magic.getMagicMatch(contenuFichier).getMimeType(), contenuFichier);
			return fichier;
		} catch (IOException e) {
			throw new TechnicalException(e.getMessage(), e);
		} catch (MagicParseException e) {
			throw new TechnicalException(e.getMessage(), e);
		} catch (MagicMatchNotFoundException e) {
			throw new TechnicalException(e.getMessage(), e);
		} catch (MagicException e) {
			throw new TechnicalException(e.getMessage(), e);
		}
	}

    /**
     * Set EditiqueMappingService.
     * @param editiqueMappingService le service editiqueMappingService
     */
	public void setEditiqueMappingService(EditiqueMappingService editiqueMappingService) {
		this.editiqueMappingService = editiqueMappingService;
	}

}
