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
package com.square.print.core.service.interfaces;

import java.util.List;

import org.scub.foundation.framework.base.dto.FichierDto;

import com.square.print.core.dto.adhesion.DocumentsBulletinsAdhesionDto;
import com.square.print.core.dto.adhesion.LettreAnnulationDto;


/**
 * Interface des services d'édition de documents (devis, ...).
 * 
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public interface EditiqueService {

	/**
	 * Récupère un fichier suivant son id.
	 * 
	 * @param nomFichier id du fichiers
	 * @return le fichier
	 */
	FichierDto getFichier(String nomFichier);

	/**
	 * Génère une lettre d'annulation sous forme de document pdf à partir d'un DTO contenant l'ensemble des données nécessaires.
	 * 
	 * @param lettreAnnulation le DTO contenant les informations nécessaires pour la génération
	 * @return le DTO ayant le contenu du fichier généré
	 */
	FichierDto getLettreAnnulation(LettreAnnulationDto lettreAnnulation);

	/**
	 * Génère un fichier pdf à partir des informations spécifiées dans le DTO en paramètre.
	 * 
	 * @param documentsBulletinsAdhesionDto DTO contenant le(s) bulletin(s) d'adhésion
	 * @return le DTO ayant le contenu du fichier généré
	 */
	FichierDto genererPdfDocumentsBulletinsAdhesion(DocumentsBulletinsAdhesionDto documentsBulletinsAdhesionDto);

	/**
	 * Fusionne le contenu d'une liste de fichiers.
	 * 
	 * @param listeFichiers la liste des fichiers à fusionner.
	 * @return le contenu de la fusion.
	 */
	byte[] fusionnerFichiersPdfs(List<FichierDto> listeFichiers);
}
