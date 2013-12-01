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
package com.square.adherent.noyau.service.test.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.square.price.core.dto.CategorieGammeDto;
import com.square.price.core.dto.ContrainteVenteDto;
import com.square.price.core.dto.CritereCriteresDto;
import com.square.price.core.dto.CritereDto;
import com.square.price.core.dto.CriteresImageGammeDto;
import com.square.price.core.dto.CriteresImageProduitDto;
import com.square.price.core.dto.GammeProduitCriteresDto;
import com.square.price.core.dto.GammeProduitDto;
import com.square.price.core.dto.IdentifiantStringLibelleDto;
import com.square.price.core.dto.ListeProduitsAdherentDto;
import com.square.price.core.dto.ModePaiementDto;
import com.square.price.core.dto.ProduitCriteresDto;
import com.square.price.core.dto.ProduitDto;
import com.square.price.core.dto.ProduitLieCriteresDto;
import com.square.price.core.dto.ProduitLieDto;
import com.square.price.core.dto.ReseauGammeDto;
import com.square.price.core.dto.VetusteGammeDto;
import com.square.price.core.dto.ZonageCriteresDto;
import com.square.price.core.dto.ZonageDto;
import com.square.price.core.service.interfaces.ProduitService;

/**
 * Mock pour le service ProduitService.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ProduitServiceImpl implements ProduitService {

    @Override
    public ContrainteVenteDto getContrainteVenteParProduit(Integer identifiantProduit) {
        return null;
    }

    @Override
    public byte[] getImageGamme(CriteresImageGammeDto criteres) {
        return null;
    }

    @Override
    public byte[] getImageProduit(CriteresImageProduitDto criteres) {
        return null;
    }

    @Override
    public List<String> getLibsMappingCritere(Integer identifiantCritere, String valeurCritere, Integer identifiantProduit, Calendar dateEffet) {
        return null;
    }

    @Override
    public List<CritereDto> getListeCriteresParProduit(CritereCriteresDto criteres) {
        return null;
    }

    @Override
    public List<GammeProduitDto> getListeGammesProduits(GammeProduitCriteresDto critere) {
        return null;
    }

    @Override
    public List<ProduitDto> getListeProduits(ProduitCriteresDto critere) {
        final List<ProduitDto> listeProduits = new ArrayList<ProduitDto>();
        final Integer idProduitExcellence = 1860220;
        final Integer idGammeExcellence = 16;
        final Integer idProduitAHC = 1669188;
        final Integer idProduitSante = 1862074;
        final Integer idProduitDC65 = 1861998;
        final Integer idProduitDCIndiv = 100051;
        if ("SMC SANTE".equals(critere.getProduitAia()) && "SMATIS EXCELLENCE C_CFTA41 O1".equals(critere.getGarantieAia())) {
            final ProduitDto produit = new ProduitDto();
            produit.setIdentifiant(idProduitExcellence);
            produit.setLibelleCommercial("SMATIS EXCELLENCE C");
            produit.setFormulePresta(new IdentifiantStringLibelleDto("presta", "Presta"));
            final Integer ordreAffichage = 21;
            produit.setOrdreAffichage(ordreAffichage);
            final GammeProduitDto gammeProduit = new GammeProduitDto();
            gammeProduit.setIdentifiant(idGammeExcellence);
            gammeProduit.setLibelle("EXCELLENCE");
            gammeProduit.setIdCategorie(1);
            produit.setGamme(gammeProduit);
            listeProduits.add(produit);
        }
        else if ("SMC SANTE".equals(critere.getProduitAia()) && "ALLOCATION HOSPITALIERE COMPLEMENTAIRE".equals(critere.getGarantieAia())) {
            final ProduitDto produit = new ProduitDto();
            produit.setIdentifiant(idProduitAHC);
            produit.setLibelleCommercial("ALLOCATION HOSPITALIERE COMPLEMENTAIRE");
            produit.setFormulePresta(null);
            final Integer ordreAffichage = 2;
            produit.setOrdreAffichage(ordreAffichage);
            final GammeProduitDto gammeProduit = new GammeProduitDto();
            gammeProduit.setIdentifiant(10);
            gammeProduit.setLibelle("COLLECTIF PORTEFEUILLE PREV");
            gammeProduit.setIdCategorie(2);
            produit.setGamme(gammeProduit);
            listeProduits.add(produit);
        }
        else if ("SMI ABSOLUE".equals(critere.getProduitAia()) && "SANTE 150I_CFTA67".equals(critere.getGarantieAia())) {
            final ProduitDto produit = new ProduitDto();
            produit.setIdentifiant(idProduitSante);
            produit.setLibelleCommercial("SANTE 150I");
            produit.setFormulePresta(null);
            final Integer ordreAffichage = 75;
            produit.setOrdreAffichage(ordreAffichage);
            final GammeProduitDto gammeProduit = new GammeProduitDto();
            gammeProduit.setIdentifiant(1);
            gammeProduit.setLibelle("ABSOLUE");
            gammeProduit.setIdCategorie(1);
            produit.setGamme(gammeProduit);
            listeProduits.add(produit);
        }
        else if ("SMI ACTIVE".equals(critere.getProduitAia()) && "DECES 65".equals(critere.getGarantieAia())) {
            final ProduitDto produit = new ProduitDto();
            produit.setIdentifiant(idProduitDC65);
            produit.setLibelleCommercial("DECES 65");
            produit.setFormulePresta(null);
            final Integer ordreAffichage = 99;
            produit.setOrdreAffichage(ordreAffichage);
            final GammeProduitDto gammeProduit = new GammeProduitDto();
            gammeProduit.setIdentifiant(12);
            gammeProduit.setLibelle("ACTIVE");
            gammeProduit.setIdCategorie(1);
            produit.setGamme(gammeProduit);
            listeProduits.add(produit);
        }
        else if ("SMI PREV".equals(critere.getProduitAia()) && "DECES TEMPORAIRE DC69".equals(critere.getGarantieAia())) {
            final ProduitDto produit = new ProduitDto();
            produit.setIdentifiant(idProduitDCIndiv);
            produit.setLibelleCommercial("DECES INDIVIDUEL");
            produit.setFormulePresta(null);
            final Integer ordreAffichage = 399;
            produit.setOrdreAffichage(ordreAffichage);
            final GammeProduitDto gammeProduit = new GammeProduitDto();
            final Integer idGamme = 25;
            gammeProduit.setIdentifiant(idGamme);
            gammeProduit.setLibelle("PORTEFEUILLE PREV");
            gammeProduit.setIdCategorie(2);
            produit.setGamme(gammeProduit);
            listeProduits.add(produit);
        }
        return listeProduits;
    }

    @Override
    public List<ProduitLieDto> getListeProduitsLies(ProduitLieCriteresDto criteres) {
        return null;
    }

    @Override
    public ModePaiementDto getModePaiementParProduit(Integer identifiantProduit) {
        return null;
    }

    @Override
    public Boolean isItGammePrevoyance(Integer identifiantGammeProduit) {
        final Integer idGammeProduitPrevoyance = 10;
        if (idGammeProduitPrevoyance.equals(identifiantGammeProduit)) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean isProduitPossedeCritere(Integer identifiantProduit, Integer identifiantCritere) {
        final Integer idCritereMontantSouscrit = 12;
        final Integer idProduitAHC = 1669188;
        if (idProduitAHC.equals(identifiantProduit) && idCritereMontantSouscrit.equals(identifiantCritere)) {
            return true;
        }
        return false;
    }

    @Override
    public CategorieGammeDto getCategorieGamme(Integer id) {
        return null;
    }

    @Override
    public List<CategorieGammeDto> getListeCategorieGammeParLibelle(String libelle) {
        return null;
    }

    @Override
    public List<ReseauGammeDto> getListeReseauGammeParLibelle(String libelle) {
        return null;
    }

    @Override
    public List<VetusteGammeDto> getListeVetusteGammeParLibelle(String libelle) {
        return null;
    }

    @Override
    public ReseauGammeDto getReseauGamme(Integer id) {
        return null;
    }

    @Override
    public VetusteGammeDto getVetusteGamme(Integer id) {
        return null;
    }

    @Override
    public List<CritereDto> getListeCriteres() {
        return null;
    }

    @Override
    public List<ZonageDto> getListeZonages(ZonageCriteresDto critere) {
        return null;
    }

    @Override
    public ListeProduitsAdherentDto getListeProduitsAdherent(Long uidAdherent, String produitAia, String garantieAia, Integer idApplication) {
        return null;
    }

    @Override
    public boolean isProduitOuvertVente(Integer idProduit) {
        return false;
    }

}
