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
package com.square.adherent.noyau.dao.interfaces.contrat;

import java.util.List;

import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheGarantieDto;
import com.square.adherent.noyau.dto.adherent.prestations.CoordonneesBancairesRemboursementDto;
import com.square.adherent.noyau.dto.produit.CriteresInfosProduitsDto;
import com.square.adherent.noyau.dto.produit.InfosProduitDto;
import com.square.adherent.noyau.model.data.contrat.Garantie;

/**
 * Interface d'accès aux données liées aux garanties.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface GarantieDao {

    /**
     * Récupère les informations sur un produit à partir de certains critères.
     * @param criteres : critères de la recherche de produits.
     * @return les informations sur les produits.
     */
    List<InfosProduitDto> getInfosProduits(CriteresInfosProduitsDto criteres);

    /**
     * Teste si une personne est assure ou beneficiaire d'une garantie.
     * @param idPersonne l'identifiant de la personne
     * @return true si la personne est assure d'une garantie, false sinon
     */
    boolean isPersonneAssocieeAContrat(Long idPersonne);

    /**
     * Recherche les garanties correspondant à des critères.
     * @param criteres les critères de recherche.
     * @return la liste des garanties du contrat.
     */
    List<Garantie> getListeGarantiesByCriteres(CritereRechercheGarantieDto criteres);

    /**
     * Détermine s'il existe des garanties en cours entre un assuré et un bénéficiaire.
     * @param idAssure l'identifiant de l'assuré.
     * @param idBeneficiaire l'identifiant du bénéficiaire.
     * @return true s'il existe des garanties en cours entre l'assuré et le bénéficiaire, false si non.
     */
    boolean hasGarantieAssureBeneficiaire(Long idAssure, Long idBeneficiaire);

    /**
     * Récuppère les garanties entre un assuré et un bénéficiaire.
     * @param idAssure l'identifiant de l'assuré.
     * @param idBeneficiaire l'identifiant du bénéficiaire.
     * @return les garanties trouvées.
     */
    List<Garantie> getListeGarantiesBeneficiaire(Long idAssure, Long idBeneficiaire);

    /**
     * Récupère les garanties d'un contrat.
     * @param eidContrat l'identifiant extérieur du contrat.
     * @return la liste des garanties.
     */
    List<Garantie> getListeGarantiesContratPersonneMorale(String eidContrat);

    /**
     * Récupère les coordonnées bancaires utilisées pour rembourser les prestations d'un adhérent et de ses bénéficiaires.
     * @param uidAdherent l'identifiant de l'adhérent
     * @return les coordonnées bancaires utilisées pour rembourser les prestations.
     */
    List<CoordonneesBancairesRemboursementDto> getCoordonneesBancairesRemboursement(Long uidAdherent);
}
