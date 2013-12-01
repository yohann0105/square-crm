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

import com.square.tarificateur.noyau.bean.CriteresBeneficiaire;
import com.square.tarificateur.noyau.model.personne.Beneficiaire;

/**
 * Interface d'accès aux données liées aux bénéficiaires.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface BeneficiaireDao {

    /**
     * Récupère le bénéficiaire demandé par sa cible.
     * @param idBeneficiaire l'identifiant du bénéficiaire cible
     * @return le bénéficiaire
     */
    Beneficiaire getBeneficiaireByCible(Long idBeneficiaire);

    /**
     * Crée un bénéficiaire.
     * @param beneficiaire le bénéficiaire
     */
    void createBeneficiaire(Beneficiaire beneficiaire);

    /**
     * Recupere des bénéficiaires par des criteres.
     * @param criteres les criteres
     * @return la liste de bénéficiaires
     */
    List<Beneficiaire> getBeneficiairesParCriteres(CriteresBeneficiaire criteres);

    /**
     * Supprime un bénéficiaire.
     * @param idBeneficiaire l'id du bénéficiaire
     */
    void deleteBeneficiaire(Long idBeneficiaire);
}
