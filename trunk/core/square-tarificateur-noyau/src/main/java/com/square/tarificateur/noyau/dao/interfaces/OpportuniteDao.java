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
package com.square.tarificateur.noyau.dao.interfaces;

import java.util.List;

import com.square.tarificateur.noyau.dto.devis.CriteresRechercheOpportuniteDto;
import com.square.tarificateur.noyau.model.opportunite.Adhesion;
import com.square.tarificateur.noyau.model.opportunite.Opportunite;

/**
 * Interface de la couche d'accès aux données pour les opportunités.
 *
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public interface OpportuniteDao {

    /**
     * Récupère l'opportunité correspondant à l'identifiant externe d'opportunité spécifié.
     * @param eidOpportunite identifiant externe d'opportunité.
     * @return l'opportunité trouvée. null sinon.
     */
    Opportunite getOpportuniteByEid(Long eidOpportunite);

    /**
     * Enregistre en base l'opportunité passée en paramètre.
     * @param opportunite l'opportunité à sauvegarder.
     */
    void saveOpportunite(Opportunite opportunite);

    /**
     * Récupère l'opportunité par son identifiant.
     * @param idOpportunite identifiant de l'opportunité.
     * @return l'opportunité trouvée. null sinon.
     */
    Opportunite getOpportuniteById(Long idOpportunite);

    /**
     * Recupere un id de devis lié a l'adhesion.
     * @param idAdhesion l'identifiant de l'adhesion
     * @param idFinaliteAccepte idFinaliteAccepte
     * @return l'adhesion
     */
    Long getIdDevisLieAdhesion(Long idAdhesion, Long idFinaliteAccepte);

    /**
     * Recupere une adhésion par son id de fichier CNS.
     * @param idFichier l'identifiant du fichier
     * @return l'adhesion
     */
    Adhesion getAdhesionByIdFichierCNS(String idFichier);

    /**
     * Recupere la liste des ids de fichiers qui n'ont pas été téléchargés.
     * @return la liste des ids
     */
    List<String> getIdsBaAdhesionNonTelecharges();

    /**
     * Récupère l'opportunité correspondant au numéro de transaction passé en paramètre.
     * @param numeroTransaction le numéro de transaction
     * @return l'opportunité trouvée, null sinon.
     */
    Opportunite getOpportuniteByNumTransaction(String numeroTransaction);

    /**
     * Recherche les opportunités correspondant aux critères.
     * @param criteres les critères de recherche.
     * @return la liste des opportunités trouvées.
     */
    List<Opportunite> rechercherOpportunitesByCritere(CriteresRechercheOpportuniteDto criteres);
}
