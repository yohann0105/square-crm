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

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.square.composant.tarificateur.square.client.model.CaisseSimpleModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosAdhesionModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosGlobalesAdhesionModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPersonneModel;
import com.square.composant.tarificateur.square.client.model.personne.AssureSocialModel;
import com.square.composant.tarificateur.square.client.model.transfo.aia.DemandeTransformationAiaModel;
import com.square.composant.tarificateur.square.client.model.transfo.aia.ResultatTransformationAiaModel;
import com.square.composant.tarificateur.square.client.service.TarificateurTransformationAiaServiceGwt;
import com.square.core.model.dto.CaisseDto;
import com.square.core.service.interfaces.DimensionService;
import com.square.tarificateur.noyau.dto.InfosAdhesionDto;
import com.square.tarificateur.noyau.dto.InfosPersonneDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.dto.transfo.aia.DemandeTransformationAiaDto;
import com.square.tarificateur.noyau.dto.transfo.aia.ResultatTransformationAiaDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurTransformationAiaService;

/**
 * Implémentation serveur des services GWT pour les services du tarficateur pour la transfo aia.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class TarificateurTransformationAiaServiceGwtImpl implements TarificateurTransformationAiaServiceGwt {

    private TarificateurTransformationAiaService tarificateurTransformationAiaService;

    private MapperDozerBean mapperDozerBean;

    private DimensionService dimensionService;

    private TarificateurService tarificateurService;

    private TarificateurPersonneService tarificateurPersonneService;

    @Override
    public ResultatTransformationAiaModel transformerDevisAia(DemandeTransformationAiaModel demandeTransformationAia) {
        final DemandeTransformationAiaDto demande = mapperDozerBean.map(demandeTransformationAia, DemandeTransformationAiaDto.class);
        final ResultatTransformationAiaDto resultat = tarificateurTransformationAiaService.transformerDevisDansAia(demande);
        return mapperDozerBean.map(resultat, ResultatTransformationAiaModel.class);
    }

    @Override
    public InfosGlobalesAdhesionModel getInfosAdhesion(Long idDevis) {
        final InfosAdhesionDto infosAdhesion = tarificateurTransformationAiaService.getInfosAdhesion(idDevis);
        final InfosAdhesionModel infosAdhesionModel = mapperDozerBean.map(infosAdhesion, InfosAdhesionModel.class);
        for (InfosPersonneDto infosPersonneDto : infosAdhesion.getInfosPersonnes()) {
            // on complete le regime et caisse
            for (InfosPersonneModel infosPersonnes : infosAdhesionModel.getInfosPersonnes()) {
                if (infosPersonneDto.getId().equals(infosPersonnes.getId()) && infosPersonneDto.getInfoSante() != null
                    && infosPersonneDto.getInfoSante().getEidCaisse() != null) {
                    final CaisseDto caisse = dimensionService.rechercherCaisseParId(infosPersonneDto.getInfoSante().getEidCaisse());
                    infosPersonnes.getInfoSante().setCaisse((CaisseSimpleModel) mapperDozerBean.map(caisse, CaisseSimpleModel.class));
                    infosPersonnes.getInfoSante().setRegime((IdentifiantLibelleGwt) mapperDozerBean.map(caisse.getRegime(), IdentifiantLibelleGwt.class));
                    break;
                }
            }
        }
        infosAdhesionModel.getInfosPaiement().setJourPaiement(
            (IdentifiantLibelleGwt) mapperDozerBean.map(tarificateurService.rechercherJourPaiementParId(infosAdhesion.getInfosPaiement().getIdJourPaiement()),
                IdentifiantLibelleGwt.class));
        infosAdhesionModel.getInfosPaiement().setPeriodicitePaiement(
            (IdentifiantLibelleGwt) mapperDozerBean.map(tarificateurService.rechercherPeriodicitePaiementParId(infosAdhesion.getInfosPaiement()
                    .getIdPeriodicitePaiement()), IdentifiantLibelleGwt.class));
        infosAdhesionModel.getInfosPaiement().setMoyenPaiement(
            (IdentifiantLibelleGwt) mapperDozerBean.map(
                tarificateurService.rechercherMoyenPaiementParId(infosAdhesion.getInfosPaiement().getIdMoyenPaiement()), IdentifiantLibelleGwt.class));

        final InfosGlobalesAdhesionModel infosGlobales = new InfosGlobalesAdhesionModel();
        infosGlobales.setInfosAdhesionModel(infosAdhesionModel);
        infosGlobales.setListeAssuresSociaux(getListeAssuresSociaux(idDevis));
        return infosGlobales;
    }

    private List<AssureSocialModel> getListeAssuresSociaux(Long idDevis) {
        final List<PersonneDto> listeDto = tarificateurPersonneService.getAssuresSociauxByIdDevis(idDevis);
        final List<AssureSocialModel> liste = mapperDozerBean.mapList(listeDto, AssureSocialModel.class);
        for (AssureSocialModel assureSocialModel : liste) {
            // on complete le regime et caisse
            for (PersonneDto assureSocialDto : listeDto) {
                if (assureSocialDto.getId().equals(assureSocialModel.getId()) && assureSocialDto.getInfoSante() != null) {
                    final CaisseDto caisse = dimensionService.rechercherCaisseParId(assureSocialDto.getInfoSante().getEidCaisse());
                    if (caisse != null) {
                        assureSocialModel.getInfoSante().setCaisse((CaisseSimpleModel) mapperDozerBean.map(caisse, CaisseSimpleModel.class));
                        assureSocialModel.getInfoSante()
                                .setRegime((IdentifiantLibelleGwt) mapperDozerBean.map(caisse.getRegime(), IdentifiantLibelleGwt.class));
                    }
                    break;
                }
            }
        }
        return liste;
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the tarificateurTransformationAiaService value.
     * @param tarificateurTransformationAiaService the tarificateurTransformationAiaService to set
     */
    public void setTarificateurTransformationAiaService(TarificateurTransformationAiaService tarificateurTransformationAiaService) {
        this.tarificateurTransformationAiaService = tarificateurTransformationAiaService;
    }

    /**
     * Set the dimensionService value.
     * @param dimensionService the dimensionService to set
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * Set the tarificateurService value.
     * @param tarificateurService the tarificateurService to set
     */
    public void setTarificateurService(TarificateurService tarificateurService) {
        this.tarificateurService = tarificateurService;
    }

    /**
     * Set the tarificateurPersonneService value.
     * @param tarificateurPersonneService the tarificateurPersonneService to set
     */
    public void setTarificateurPersonneService(TarificateurPersonneService tarificateurPersonneService) {
        this.tarificateurPersonneService = tarificateurPersonneService;
    }

}
