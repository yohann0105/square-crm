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
package com.square.adherent.noyau.dao.interfaces.prestation;

import java.util.List;

import com.square.adherent.noyau.dto.prestation.CritereSelectionRelevePrestationDto;
import com.square.adherent.noyau.model.data.prestation.RelevePrestation;
import com.square.adherent.noyau.model.dimension.RelevePrestationMoyenPaiement;

/**
 * Interface d'accès aux données liées aux relevés de prestation.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface RelevePrestationDao {

    /**
     * Retourne les relevés de prestations filtrés par critères.
     * @param critereSelectionRelevePrestationDto : critères de filtrage de la recherche.
     * @param triDesc : true si on veut trier la liste par date décroissante.
     * @return la liste des relevés de prestations.
     */
    List<RelevePrestation> getListeReleveParCriteres(CritereSelectionRelevePrestationDto critereSelectionRelevePrestationDto, Boolean triDesc);

    /**
     * Retourne le nombre de candidats à l'envoi de mail.
     * @param nbJoursDifferesEnvoiMail nb de jours differes
     * @param idNatureConnexionEspaceAdherent l'id de la nature
     * @param idOptionRelevePresta l'id de l'option
     * @return total des candidats.
     */
    Integer getNombreCandidatsEnvoiRelevesPrestationEmail(int nbJoursDifferesEnvoiMail, Long idNatureConnexionEspaceAdherent, Long idOptionRelevePresta);

    /**
     * Retourne la liste des candidats à l'envoi de mail.
     * @param firstResult : premier résultats à récupérer.
     * @param maxResult : limite maximum des résultas à récupérer.
     * @param listeIdsExclus : liste des ids de personnes à exclure (en erreurs)
     * @param nbJoursDifferesEnvoiMail nb de jours differes
     * @param idNatureConnexionEspaceAdherent l'id de la nature
     * @param idOptionRelevePresta l'id de l'option
     * @return la liste des identifiants des candidats.
     */
    List<Long> getCandidatsEnvoiRelevesPrestationEmail(int firstResult, int maxResult, List<Long> listeIdsExclus, int nbJoursDifferesEnvoiMail,
        Long idNatureConnexionEspaceAdherent, Long idOptionRelevePresta);
    /**
     * Teste si le relevé existe déjà dans la base de données.
     * @param nomFichier : nom du fichier à testé.
     * @return true si le fichier existe déjà.
     */
    boolean existe(String nomFichier);

    /**
     * Créer un relevé de prestations.
     * @param relevePrestation : le relevé de prestation à créer.
     */
    void createRelevePresta(RelevePrestation relevePrestation);

    /**
     * Supprime un relevé de prestations.
     * @param relevePrestation : le relevé à supprimer.
     */
    void deleteRelevePresta(RelevePrestation relevePrestation);

    /**
     * Retourne le moyen de paiement correspondant au libellé.
     * @param eid : eid du moyen de paiement à récupérer.
     * @return le moyen de paiement.
     */
    RelevePrestationMoyenPaiement getMoyenPaiementByEid(String eid);

}
