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

import com.square.tarificateur.noyau.bean.CriteresRechercheLignesDevis;
import com.square.tarificateur.noyau.model.opportunite.LigneDevis;

/**
 * Interface d'accès aux données liées aux lignes de devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface LigneDevisDao {

    /**
     * Récupère la ligne de devis demandée.
     * @param idLigneDevis l'identifiant de la ligne de devis
     * @return la ligne de devis
     */
    LigneDevis getLigneDevis(Long idLigneDevis);

    /**
     * Sauvegarde une ligne de devis.
     * @param ligneDevis la ligne de devis
     */
    void saveLigneDevis(LigneDevis ligneDevis);

    /**
     * Supprime une ligne de devis.
     * @param ligneDevis la ligne de devis
     */
    void deleteLigneDevis(LigneDevis ligneDevis);

    /**
     * Recupere la liste des lignes de devis principal pour un devis.
     * @param idDevis l'identifiant du devis.
     * @return la liste des lignes de devis.
     */
    List<LigneDevis> getLigneDevisPrincipalParIdDevis(final Long idDevis);

    /**
     * Recupere la liste des lignes principales en fonction de critères.
     * @param criteres les critères
     * @return la liste des lignes de devis.
     */
    List<LigneDevis> getLigneDevisPrincipalParCriteres(final CriteresRechercheLignesDevis criteres);

    /**
     * Détermine si la ligne correspondant à l'identifiant passé en paramètre est une ligne liée ou non.
     * @param idLigneDevis identifiant de la ligne de devis.
     * @return true si la ligne est liée.
     */
    boolean estUneLigneLiee(Long idLigneDevis);

    /**
     * Récupère la liste des lignes liées à une ligne principale.
     * @param idLignePrincipale l'identifiant de la ligne principale.
     * @return la liste des lignes liées.
     */
    List<LigneDevis> getLignesLieesLignePrincipale(Long idLignePrincipale);

    /**
     * Recuperatin des identifiant de ligne de devis principal.
     * @param idDevis identifant devis.
     * @return la liste des identifiants.
     */
    List<Long> getIdLigneDevisPrincipalParIdDevis(Long idDevis);

    /**
     * Recuepration de toutes les finalites des lignes de devis d'un devis.
     * @param idDevis identifiant du devis.
     * @return la liste des finalites.
     */
    List<Long> getIdsFinaliteLigneDevis(Long idDevis);

    /**
     * Supprime une ligne de devis.
     * @param ligneDevis la ligne de devis à supprimer.
     */
    void deleteLigneDevisFromDevis(LigneDevis ligneDevis);
}
