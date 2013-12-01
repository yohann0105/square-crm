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

import com.square.tarificateur.noyau.dto.devis.CriteresRechercheDevisDto;
import com.square.tarificateur.noyau.model.opportunite.Devis;

/**
 * Interface d'accès aux données liées aux devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface DevisDao {

    /**
     * Récupère le devis demandé.
     * @param idDevis l'identifiant du devis
     * @return le devis
     */
    Devis getDevis(Long idDevis);

    /**
     * Sauvegarde le devis passé en paramètre dans la base de données.
     * @param devis le devis à créer dans la base de données.
     */
    void saveDevis(Devis devis);

    /**
     * Récupère la liste des devis appartenant à une opportunité.
     * @param idOpportunite l'identifiant de l'opportunité
     * @return la liste des devis
     */
    List<Devis> getListeDevisByOpportunite(Long idOpportunite);

    /**
     * Recherche de devis par criteres.
     * @param criteres la liste des criteres.
     * @return la liste des devis.
     */
    List < Devis > getListeDevisByCriteres(CriteresRechercheDevisDto criteres);

    /**
     * Supprime un devis.
     * @param idDevis l'id du devis
     */
    void deleteDevis(Long idDevis);

    /**
     * Permet de Recuperer l'ensemble des identifiants d'un Produit.
     * @param idDevis identifiant du devis.
     * @return la liste des identifiant externe du produit.
     */
    List < Integer > getEidsProduitDevis(final Long idDevis);
}
