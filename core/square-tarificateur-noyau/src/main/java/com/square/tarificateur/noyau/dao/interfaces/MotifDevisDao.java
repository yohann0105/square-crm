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

import com.square.tarificateur.noyau.model.opportunite.MotifDevis;

/**
 * Interface d'accès aux données pour les motifs de devis.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface MotifDevisDao {

    /**
     * Récupère la liste des motifs de devis.
     * @return les motifs de devis
     */
    List<MotifDevis> getListeMotifsDevis();

    /**
     * Récupère les motifs de devis correspondant au libelle.
     * @param libelle le libelle
     * @return les différents motifs de devis.
     */
    List<MotifDevis> getMotifsDevisByCriteres(String libelle);

    /**
     * Récupère un motif de devis par son identifiant.
     * @param idMotifDevis l'identifiant du motif à récupérer.
     * @return le motif de devis correspondant.
     */
    MotifDevis getMotifDevisById(Long idMotifDevis);

}