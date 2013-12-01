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
package com.square.composant.prestations.square.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.square.adherent.noyau.dto.DimensionAdherentCriteresRechercheDto;
import com.square.adherent.noyau.dto.IdentifiantEidLibelleDto;
import com.square.adherent.noyau.dto.adherent.BeneficiairePrestationDto;
import com.square.adherent.noyau.dto.prestation.EntetePrestationResultatRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationCriteresRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationResultatRechercheDto;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.PrestationService;
import com.square.composant.prestations.square.client.model.EntetePrestationModel;
import com.square.composant.prestations.square.client.model.IdentifiantEidLibelleGwt;
import com.square.composant.prestations.square.client.model.LigneDecompteModel;
import com.square.composant.prestations.square.client.model.PrestationCriteresRechercheModel;
import com.square.composant.prestations.square.client.service.PrestationServiceGwt;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;

/**
 * Implémentation du service synchrone GWT des prestations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class PrestationServiceGwtImpl implements PrestationServiceGwt {

    /** Service des prestations. */
    private PrestationService prestationService;

    /** Service des personnes physiques. */
    private PersonnePhysiqueService personnePhysiqueService;

    private MapperDozerBean mapperDozerBean;

    private AdherentMappingService adherentMappingService;

    @Override
    public List<IdentifiantLibelleGwt> recupererBenefPrestations(Long uidAssure, String prenom) {
        final List<IdentifiantLibelleGwt> listePersonnes = new ArrayList<IdentifiantLibelleGwt>();
        // On ajoute la personne principale puis ses bénéficiaires
        final PersonneSimpleDto personnePrincipale = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(uidAssure);
        listePersonnes.add(new IdentifiantLibelleGwt(personnePrincipale.getId(), personnePrincipale.getNom().toUpperCase() + " "
            + StringUtils.capitalize(personnePrincipale.getPrenom())));
        final List<BeneficiairePrestationDto> resultatsDto = prestationService.recupererBeneficiairesPrestations(uidAssure, prenom);
        for (BeneficiairePrestationDto beneficiaire : resultatsDto) {
            listePersonnes.add(new IdentifiantLibelleGwt(beneficiaire.getIdentifiant(), beneficiaire.getNom().toUpperCase() + " "
                + StringUtils.capitalize(beneficiaire.getPrenom())));
        }
        return listePersonnes;
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherOriginesDecompteParCriteres(String libelle) {
        final DimensionAdherentCriteresRechercheDto criteres = new DimensionAdherentCriteresRechercheDto();
        criteres.setLibelle(libelle);
        final List<IdentifiantLibelleDto> liste = prestationService.rechercherOriginesDecompteParCriteres(criteres);
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantEidLibelleGwt> rechercherActesDecompteParCriteres(String libelle) {
        final DimensionAdherentCriteresRechercheDto criteres = new DimensionAdherentCriteresRechercheDto();
        criteres.setLibelle(libelle);
        final List<IdentifiantEidLibelleDto> liste = prestationService.rechercherActesDecompteParCriteres(criteres);
        return mapperDozerBean.mapList(liste, IdentifiantEidLibelleGwt.class);
    }

    @Override
    public RemotePagingResultsGwt<EntetePrestationModel> rechercherEntetesPrestationParCritreres(
        RemotePagingCriteriasGwt<PrestationCriteresRechercheModel> criteres) {
        final PrestationCriteresRechercheDto criterias = mapperDozerBean.map(criteres.getCriterias(), PrestationCriteresRechercheDto.class);
        final RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<PrestationCriteresRechercheDto>(criterias, criteres.getFirstResult(), criteres.getMaxResult());
        final List<RemotePagingSort> listSort = new ArrayList<RemotePagingSort>();
//        listSort.add(new RemotePagingSort(adherentMappingService.getOrderDecompteByDateReglement(), RemotePagingSort.REMOTE_PAGING_SORT_DESC));
        listSort.add(new RemotePagingSort(adherentMappingService.getOrderDecompteByNumero(), RemotePagingSort.REMOTE_PAGING_SORT_DESC));
        criteresDto.setListeSorts(listSort);
        final RemotePagingResultsDto<EntetePrestationResultatRechercheDto> resultatsDto =
            prestationService.rechercherEntetesPrestationParCritreres(criteresDto);
        final RemotePagingResultsGwt<EntetePrestationModel> resultats = new RemotePagingResultsGwt<EntetePrestationModel>();
        final List<EntetePrestationModel> listeprestationGwt = mapperDozerBean.mapList(resultatsDto.getListResults(), EntetePrestationModel.class);
        resultats.setListResults(listeprestationGwt);
        resultats.setTotalResults(resultatsDto.getTotalResults());
        return resultats;
    }

    @Override
    public List<LigneDecompteModel> recupererLignesPrestationsByNumeroDecompte(Long idAdherent, String numeroDecompte, String colonneTri, int triAsc) {
        final PrestationCriteresRechercheDto criterias = new PrestationCriteresRechercheDto();
        criterias.setNumeroDecompteExact(numeroDecompte);
        criterias.setIdAssure(idAdherent);
        final RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PrestationCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);
        final List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        listeSorts.add(new RemotePagingSort(colonneTri, triAsc));
        // si on trie par date, on ajoute un tri sur la colonne
        if (colonneTri.equals(adherentMappingService.getOrderDecompteByDateSoin())) {
            listeSorts.add(new RemotePagingSort(adherentMappingService.getOrderDecompteByNumeroCouplage(), RemotePagingSort.REMOTE_PAGING_SORT_ASC));
        }
        criteres.setListeSorts(listeSorts);
        final RemotePagingResultsDto<PrestationResultatRechercheDto> results = prestationService.rechercherPrestationParCritreres(criteres);
        final List<LigneDecompteModel> lignesDecomptes = new ArrayList<LigneDecompteModel>();
        for (PrestationResultatRechercheDto ligne : results.getListResults()) {
            final LigneDecompteModel ligneModel = mapperDozerBean.map(ligne, LigneDecompteModel.class);
            ligneModel.setBeneficiaire(ligne.getNomBeneficiaire().toUpperCase() + " " + StringUtils.capitalize(ligne.getPrenomBeneficiaire()));
            ligneModel.setTauxRemboursementCompl(Integer.valueOf((int) Math.round(ligne.getRemboursementCompl() / ligne.getMontant() * 100)));
            ligneModel.setTauxRemboursementSecu(Integer.valueOf((int) Math.round(ligne.getRemboursementSecu() / ligne.getMontant() * 100)));
            lignesDecomptes.add(ligneModel);
        }
        return lignesDecomptes;
    }

    /**
     * Setter function.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Setter.
     * @param prestationService the prestationService to set
     */
    public void setPrestationService(PrestationService prestationService) {
        this.prestationService = prestationService;
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

}
