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

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.square.composant.tarificateur.square.client.model.CaisseSimpleModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresLienFamilialRechercheModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheCaisseModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheRegimeModel;
import com.square.composant.tarificateur.square.client.model.InfosExpediteurEnvoiEmailModel;
import com.square.composant.tarificateur.square.client.service.DimensionServiceGwt;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.DimensionCriteresRechercheCaisseDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.DimensionCriteresRechercheRegimeDto;
import com.square.core.model.dto.RessourceDto;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.service.interfaces.RessourceService;
import com.square.tarificateur.noyau.dto.personne.DimensionCriteresLienFamilialRechercheDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;

/**
 * Implémentation serveur des services GWT pour le service des dimensions.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class DimensionServiceGwtImpl implements DimensionServiceGwt {

    private DimensionService dimensionService;

    private MapperDozerBean mapperDozerBean;

    private RessourceService ressourceService;

    /** Service tarificateur. */
    private TarificateurPersonneService tarificateurPersonneService;

    @Override
    public List<CaisseSimpleModel> rechercherCaisseParCriteres(DimensionCriteresRechercheCaisseModel criteres) {
        final DimensionCriteresRechercheCaisseDto criteresCaisse = mapperDozerBean.map(criteres, DimensionCriteresRechercheCaisseDto.class);
        final List<CaisseSimpleDto> liste = dimensionService.rechercherCaisseParCriteres(criteresCaisse);
        return mapperDozerBean.mapList(liste, CaisseSimpleModel.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherRegimeParCriteres(DimensionCriteresRechercheRegimeModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherRegimeParCriteres((DimensionCriteresRechercheRegimeDto) mapperDozerBean.map(criteres,
                DimensionCriteresRechercheRegimeDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public InfosExpediteurEnvoiEmailModel getInfosExpediteurEmail(Long eidResponsable) {
        final InfosExpediteurEnvoiEmailModel infosEnvoiEmail = new InfosExpediteurEnvoiEmailModel();
        final RessourceDto responsable = ressourceService.rechercherRessourceParEid(eidResponsable.toString());
        if (responsable != null) {
            infosEnvoiEmail.setExpediteur(responsable.getNom() + " " + responsable.getPrenom());
            infosEnvoiEmail.setAdresseEmailExpediteur(responsable.getEmail());
        }
        return infosEnvoiEmail;
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherCiviliteParCriteres(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherCiviliteParCriteres((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherLiensFamiliauxParCriteres(DimensionCriteresLienFamilialRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
        	tarificateurPersonneService.rechercherLiensFamiliauxParCriteres(
        			(DimensionCriteresLienFamilialRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresLienFamilialRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the dimensionSquareService value.
     * @param dimensionService the dimensionSquareService to set
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * Set the ressourceService value.
     * @param ressourceService the ressourceService to set
     */
    public void setRessourceService(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

	/**
	 * Définit le tarificateurPersonneService.
	 * @param tarificateurPersonneService le nouveau tarificateurPersonneService
	 */
	public void setTarificateurPersonneService(TarificateurPersonneService tarificateurPersonneService) {
		this.tarificateurPersonneService = tarificateurPersonneService;
	}
}
