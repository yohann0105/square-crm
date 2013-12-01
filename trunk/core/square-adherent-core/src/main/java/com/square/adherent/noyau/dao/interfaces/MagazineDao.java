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
package com.square.adherent.noyau.dao.interfaces;

import java.util.List;

import com.square.adherent.noyau.model.data.magazine.AdherentMagazine;
import com.square.adherent.noyau.model.data.magazine.Magazine;

/**
 * Interface d'accès aux données liées aux magazines.
 * @author nnadeau - SCUB
 */
public interface MagazineDao {

    /**
     * Recupere un magazine.
     * @param idMagazine l'identifiant du magazine
     * @return le magazine
     */
    Magazine getMagazine(Long idMagazine);

    /**
     * Sauvegarde un magazine.
     * @param magazine le magazine
     */
    void saveMagazine(Magazine magazine);

    /**
     * Sauvegarde un adherentMagazine.
     * @param adherentMagazine le adherentMagazine
     */
    void saveAdherentMagazine(AdherentMagazine adherentMagazine);

    /**
     * Récupère la liste des identifiant des adhérents ayant déjà reçu le magazine spécifié.
     * @param idMagazine l'identifiant du magazine.
     * @return la liste des adhérentsOnum.
     */
    List<Long> getListeAdherentsByIdMagazineEnvoye(Long idMagazine);
}
