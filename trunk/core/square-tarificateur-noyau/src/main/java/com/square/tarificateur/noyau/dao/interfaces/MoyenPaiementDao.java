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

import com.square.tarificateur.noyau.model.paiement.MoyenPaiement;

/**
 * Interface d'accès aux données liées aux moyens de paiement.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface MoyenPaiementDao {

    /**
     * Récupère le moyen de paiement demandé.
     * @param idMoyenPaiement l'identifiant du moyen de paiement
     * @return le moyen de paiement
     */
    MoyenPaiement getMoyenPaiement(Long idMoyenPaiement);

    /**
     * Récupère la liste des moyens de paiement.
     * @return les moyens de paiement.
     */
    List<MoyenPaiement> getListeMoyensPaiement();

    /**
     * Récupère la liste des moyens de paiement par criteres.
     * @param criteres les criteres
     * @return les moyens de paiement.
     */
    List<MoyenPaiement> rechercherMoyensPaiementParCriteres(IdentifiantLibelleDto criteres);
}
