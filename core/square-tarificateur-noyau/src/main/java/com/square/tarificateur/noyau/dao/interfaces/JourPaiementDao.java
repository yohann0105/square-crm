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

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.tarificateur.noyau.model.paiement.JourPaiement;

/**
 * Interface d'accès aux données liées aux jours de paiement.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface JourPaiementDao {

    /**
     * Récupère le jour de paiement demandé.
     * @param idJourPaiement l'identifiant du jour de paiement
     * @return le jour de paiement
     */
    JourPaiement getJourPaiement(Long idJourPaiement);

    /**
     * Récupère les jours du mois de paiement.
     * @return l'ensemble des jours du mois de paiement.
     */
    List<JourPaiement> getListeJoursPaiement();

    /**
     * Récupère la liste des jours de paiement par criteres.
     * @param criteres les criteres
     * @return les jours de paiement.
     */
    List<JourPaiement> rechercherJoursPaiementParCriteres(IdentifiantLibelleDto criteres);
}
