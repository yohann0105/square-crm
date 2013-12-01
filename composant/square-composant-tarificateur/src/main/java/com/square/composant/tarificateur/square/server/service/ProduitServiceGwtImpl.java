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
package com.square.composant.tarificateur.square.server.service;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.composant.tarificateur.square.client.model.GammesProduitsCriteresModel;
import com.square.composant.tarificateur.square.client.model.IntegerLibelleModel;
import com.square.composant.tarificateur.square.client.model.ProduitCriteresModel;
import com.square.composant.tarificateur.square.client.service.ProduitServiceGwt;
import com.square.price.core.dto.GammeProduitCriteresDto;
import com.square.price.core.dto.GammeProduitDto;
import com.square.price.core.dto.ProduitCriteresDto;
import com.square.price.core.dto.ProduitDto;
import com.square.price.core.dto.ReseauGammeDto;
import com.square.price.core.dto.VetusteGammeDto;
import com.square.price.core.service.interfaces.ProduitService;
import com.square.price.core.service.interfaces.TarificateurMappingService;

/**
 * Implémentation serveur des services GWT pour les produits.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class ProduitServiceGwtImpl implements ProduitServiceGwt {

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Service produit. */
    private ProduitService produitService;

    /** Service TarificateurMapping. */
    private TarificateurMappingService tarificateurMappingService;

    @Override
    public List<IntegerLibelleModel> getListeCategoriesGamme(String suggest) {
        // Appel du service
        return mapperDozerBean.mapList(produitService.getListeCategorieGammeParLibelle(suggest), IntegerLibelleModel.class);
    }

    @Override
    public List<IntegerLibelleModel> getListeGammesProduits(GammesProduitsCriteresModel gammesProduitsCriteres) {
        // Transformation des critères
        final GammeProduitCriteresDto gammeProduitCriteresDto = mapperDozerBean.map(gammesProduitsCriteres, GammeProduitCriteresDto.class);
        gammeProduitCriteresDto.getDomaine().setIdentifiantGestion(tarificateurMappingService.getIdentifiantGestionIndividuelle());
        // Tri sur ordre d'affichage
        gammeProduitCriteresDto.setColonneTri(tarificateurMappingService.getProprieteGammeProduitOrdreAffichage());
        final List<GammeProduitDto> listeNoyau = produitService.getListeGammesProduits(gammeProduitCriteresDto);

        // Transformation de la liste
        return mapperDozerBean.mapList(listeNoyau, IntegerLibelleModel.class);
    }

    @Override
    public List<IntegerLibelleModel> getListeProduits(ProduitCriteresModel produitCriteresGWT) {
            // Transformation des critères
            final ProduitCriteresDto produitCriteresDto = mapperDozerBean.map(produitCriteresGWT, ProduitCriteresDto.class);

            // Récupération des produits vendus seuls
            produitCriteresDto.setVenduSeul(Boolean.TRUE);

            // Appel du service
            final List<ProduitDto> listeProduits = produitService.getListeProduits(produitCriteresDto);

            return mapperDozerBean.mapList(listeProduits, IntegerLibelleModel.class);
    }

    @Override
    public List<IntegerLibelleModel> getListeReseauxGamme(String suggest) {
        // Appel du service
        final List<ReseauGammeDto> listeNoyau = produitService.getListeReseauGammeParLibelle(suggest);

        // On ne récupère que "Smatis France" et "Smatis Innovation"
        final List<ReseauGammeDto> listeFiltree = new ArrayList<ReseauGammeDto>();
        final Integer idReseauFrance = tarificateurMappingService.getIdentifiantReseauFrance();
        for (ReseauGammeDto reseau : listeNoyau) {
            if (idReseauFrance.equals(reseau.getIdentifiant())) {
                listeFiltree.add(reseau);
            }
        }
        return mapperDozerBean.mapList(listeFiltree, IntegerLibelleModel.class);
    }

    @Override
    public List<IntegerLibelleModel> getListeVetustesGamme(String suggest) {
        // Appel du service
        final List<VetusteGammeDto> listeNoyau = produitService.getListeVetusteGammeParLibelle(suggest);
        return mapperDozerBean.mapList(listeNoyau, IntegerLibelleModel.class);
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the produitService value.
     * @param produitService the produitService to set
     */
    public void setProduitService(ProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * Set the tarificateurMappingService value.
     * @param tarificateurMappingService the tarificateurMappingService to set
     */
    public void setTarificateurMappingService(TarificateurMappingService tarificateurMappingService) {
        this.tarificateurMappingService = tarificateurMappingService;
    }
}
