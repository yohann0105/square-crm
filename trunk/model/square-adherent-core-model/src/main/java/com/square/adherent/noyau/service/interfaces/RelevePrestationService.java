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
package com.square.adherent.noyau.service.interfaces;

import java.util.List;

import com.square.adherent.noyau.dto.fichier.FichierDto;
import com.square.adherent.noyau.dto.prestation.CritereSelectionRelevePrestationDto;
import com.square.adherent.noyau.dto.prestation.ParamRelevePrestationMailDto;
import com.square.adherent.noyau.dto.prestation.RelevePrestationDto;

/**
 * Interface des services liés aux relevés de prestation.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface RelevePrestationService {

    /**
     * Renvoie la liste des relevés de prestations selon certains critères.
     * @param critereSelectionRelevePrestationDto : critères de filtrage de la recherche.
     * @return la liste des relevés de prestation.
     */
    List<RelevePrestationDto> getListeReleveParCriteres(CritereSelectionRelevePrestationDto critereSelectionRelevePrestationDto);

    /**
     * Renvoie la liste des relevés de prestations selon certains critères.
     * @param critereSelectionRelevePrestationDto : critères de filtrage de la recherche.
     * @param triDesc : ordre de tri des résultalts: true = DESC; false = ASC.
     * @return la liste des relevés de prestation.
     */
    List<RelevePrestationDto> getListeReleveParCriteres(CritereSelectionRelevePrestationDto critereSelectionRelevePrestationDto, Boolean triDesc);

    /**
     * Convertit un relevé de prestations en tableau de bits.
     * @param idRelevePrestation : identifiant du relevé de prestation à convertir.
     * @param duplicata : true si on veut apposer la mention duplicata sur le relevé.
     * @return fichierDto obtenu après conversion.
     */
    FichierDto getRelevePrestationByteArray(Long idRelevePrestation, boolean duplicata);

    /**
     * Convertit un relevé de prestations en tableau de bits.
     * @param idRelevePrestation : identifiant du relevé de prestation à convertir.
     * @param idPersonne : identifiant de la personne demandeuse.
     * @param duplicata : true si on veut apposer la mention duplicata sur le relevé.
     * @return fichierDto obtenu après conversion.
     */
    FichierDto getRelevePrestationByteArray(Long idRelevePrestation, Long idPersonne, boolean duplicata);

    /**
     * Envoi un relevé de prestations par mail.
     * @param paramRelevePrestationMailDto paramètres pour l'envoi du mail.
     */
    void envoyerParMailRelevesPrestations(ParamRelevePrestationMailDto paramRelevePrestationMailDto);

    /**
     * Compte le nombre de candidats à l'envoi de relevé de prestations par mail.
     * @return le nombre de candidats.
     */
    Integer getNombreCandidatsEnvoiRelevesPrestationEmail();

    /**
     * Retourne les candidats à l'envoi de relevé de prestations par mail.
     * @param firstResult : premier résultats à récupérer.
     * @param maxResult : limite maximum de résultats à récupérer.
     * @param listeIdsExclus : liste des ids de personnes à exclure (en erreurs)
     * @return les identifiants des candidats.
     */
    List<Long> getCandidatsEnvoiRelevesPrestationEmail(int firstResult, int maxResult, List<Long> listeIdsExclus);

    /**
     * Ajoute un relevé de prestations à la liste des relevés de l'adhérent.
     * @param nomFichier : nom du relevé à ajouter.
     */
    void ajouterRelevePrestation(String nomFichier);

}
