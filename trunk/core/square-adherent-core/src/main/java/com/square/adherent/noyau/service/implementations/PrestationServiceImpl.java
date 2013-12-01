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
package com.square.adherent.noyau.service.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.adherent.noyau.dao.interfaces.contrat.GarantieDao;
import com.square.adherent.noyau.dao.interfaces.prestation.DecompteActeDao;
import com.square.adherent.noyau.dao.interfaces.prestation.DecompteDao;
import com.square.adherent.noyau.dao.interfaces.prestation.DecompteOrigineDao;
import com.square.adherent.noyau.dto.DimensionAdherentCriteresRechercheDto;
import com.square.adherent.noyau.dto.IdentifiantEidLibelleDto;
import com.square.adherent.noyau.dto.adherent.BeneficiairePrestationDto;
import com.square.adherent.noyau.dto.adherent.prestations.CoordonneesBancairesRemboursementDto;
import com.square.adherent.noyau.dto.prestation.EntetePrestationResultatRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationCriteresRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationResultatRechercheDto;
import com.square.adherent.noyau.model.dimension.prestation.DecompteActe;
import com.square.adherent.noyau.model.dimension.prestation.DecompteOrigine;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.PrestationService;
import com.square.adherent.noyau.util.MessageKeyUtil;
import com.square.core.model.dto.PersonnePhysiqueRelationDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Service de gestion des prestations.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class PrestationServiceImpl implements PrestationService {

    /** Logger. */
    private final Logger logger = Logger.getLogger(this.getClass());

    /** Dao sur les decomptes. */
    private DecompteDao decompteDao;

    /** Dao sur les origines de decomptes. */
    private DecompteOrigineDao decompteOrigineDao;

    /** Dao sur les actes de decomptes. */
    private DecompteActeDao decompteActeDao;

    /** Dao sur les garanties. */
    private GarantieDao garantieDao;

    /** Dozer mapper. */
    private MapperDozerBean mapperDozerBean;

    /** PersonnePhysiqueService. */
    private PersonnePhysiqueService personnePhysiqueService;

    /** PersonnePhysiqueService. */
    private PersonneService personneService;

    /** Service de mapping de square. */
    private SquareMappingService squareMappingService;

    /** Service des constantes des adhérents. */
    private AdherentMappingService adherentMappingService;

    private MessageSourceUtil messageSourceUtil;

    @Override
    public RemotePagingResultsDto<PrestationResultatRechercheDto> rechercherPrestationParCritreres(
        final RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres) {
        final RemotePagingResultsDto<PrestationResultatRechercheDto> result = new RemotePagingResultsDto<PrestationResultatRechercheDto>();
        result.setTotalResults(decompteDao.getNombreTotalPrestationParCritreres(criteres));

        final List<PrestationResultatRechercheDto> listes = decompteDao.rechercherPrestationParCritreres(criteres);

        // Map des bénéficiaires
        final Map<Long, PersonneSimpleDto> mapBeneficiaires = new HashMap<Long, PersonneSimpleDto>();

        // UTILISATION SQUARE POUR TRADUIRE BENEFICIAIRE
        for (PrestationResultatRechercheDto presta : listes) {
            PersonneSimpleDto personne = mapBeneficiaires.get(presta.getUidLigPatient());
            if (personne == null) {
                personne = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(presta.getUidLigPatient());
                mapBeneficiaires.put(presta.getUidLigPatient(), personne);
            }
            presta.setNomBeneficiaire(personne.getNom());
            presta.setPrenomBeneficiaire(personne.getPrenom());
            final Double resteACharge = Math.abs(presta.getMontant() - (presta.getRemboursementSecu() + presta.getRemboursementCompl()));
            presta.setResteACharge(resteACharge);

            // Remplacement du symbole Euro dans le libellé de l'acte
            if (presta.getActe() != null && presta.getActe().getLibelle() != null) {
                presta.getActe().setLibelle(presta.getActe().getLibelle().replace('\u0080', '€'));
                presta.setLibelleActe(presta.getActe().getLibelle());
            }

            // Origine
            if (presta.getOrigine() != null && presta.getOrigine().getLibelle() != null) {
                presta.setLibelleOrigine(presta.getOrigine().getLibelle());
            }
        }
        // Tri sur les bénéficiaires
        if (criteres.getListeSorts() != null && criteres.getListeSorts().size() > 0
                && adherentMappingService.getOrderDecompteByBeneficiaire().equals(criteres.getListeSorts().get(0).getSortField())) {
            final Comparator<PrestationResultatRechercheDto> comparator = new Comparator<PrestationResultatRechercheDto>() {
                @Override
                public int compare(PrestationResultatRechercheDto o1, PrestationResultatRechercheDto o2) {
                    if (criteres.getListeSorts().get(0).getSortAsc() == RemotePagingSort.REMOTE_PAGING_SORT_ASC) {
                        if (o1.getNomBeneficiaire().equals(o2.getNomBeneficiaire())) {
                            return o1.getPrenomBeneficiaire().compareTo(o2.getPrenomBeneficiaire());
                        }
                        else {
                            return o1.getNomBeneficiaire().compareTo(o2.getNomBeneficiaire());
                        }
                    }
                    else {
                        if (o1.getNomBeneficiaire().equals(o2.getNomBeneficiaire())) {
                            return o2.getPrenomBeneficiaire().compareTo(o1.getPrenomBeneficiaire());
                        }
                        else {
                            return o2.getNomBeneficiaire().compareTo(o1.getNomBeneficiaire());
                        }
                    }
                }
            };
            Collections.sort(listes, comparator);
        }
        result.setListResults(listes);
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_NOMBRE_PRESTATION_RECUPERER,
        		new String[] {String.valueOf(result.getListResults().size()), String.valueOf(result.getTotalResults())}));
        return result;
    }

    @Override
    public RemotePagingResultsDto<EntetePrestationResultatRechercheDto> rechercherEntetesPrestationParCritreres(
        RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres) {
        final RemotePagingResultsDto<EntetePrestationResultatRechercheDto> result = new RemotePagingResultsDto<EntetePrestationResultatRechercheDto>();
        result.setTotalResults(decompteDao.getNombreTotalEntetesPrestationParCritreres(criteres));

        final List<EntetePrestationResultatRechercheDto> listes = decompteDao.rechercherEntetesPrestationParCritreres(criteres);
        result.setListResults(listes);
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_NOMBRE_ENTETE_PRESTATION_RECUPERER,
        		new String[] {String.valueOf(result.getListResults().size()), String.valueOf(result.getTotalResults())}));
        return result;
    }

    @Override
    public List<BeneficiairePrestationDto> recupererBeneficiairesPrestations(Long uidAssure, String prenom) {
        // RECUPERE LES RELATIONS FAMILLE DE L'ASSURE
        final RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();
        criteres.setIdPersonneSource(uidAssure);
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteres.setGroupements(groupements);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias = new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(
                criteres, 0, Integer.MAX_VALUE);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> relations = personneService.rechercherRelationsParCritreres(criterias);
        final List<BeneficiairePrestationDto> benefs = new ArrayList<BeneficiairePrestationDto>();
        final Long idTypeConjoint = squareMappingService.getIdTypeRelationConjoint();
        final Long idTypeEnfant = squareMappingService.getIdTypeRelationEnfant();
        for (RelationInfosDto<? extends PersonneRelationDto> relation : relations.getListResults()) {
            // SEULE LES CONJOINTS ET ENFANT NOUS INTERESSENT
            if (relation.getType().getIdentifiant().equals(idTypeConjoint) || relation.getType().getIdentifiant().equals(idTypeEnfant)) {
                final PersonnePhysiqueRelationDto personne = (PersonnePhysiqueRelationDto) relation.getPersonne();
                if (prenom == null || prenom.equals("") || personne.getPrenom().startsWith(prenom)) {
                    benefs.add(new BeneficiairePrestationDto(personne.getId(), personne.getNom(), personne.getPrenom()));
                }
            }
        }
        return benefs;
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherOriginesDecompteParCriteres(DimensionAdherentCriteresRechercheDto criteres) {
        final List<DecompteOrigine> liste = decompteOrigineDao.rechercherOriginesDecompteParCriteres(criteres);
        return mapperDozerBean.mapList(liste, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantEidLibelleDto> rechercherActesDecompteParCriteres(DimensionAdherentCriteresRechercheDto criteres) {
        final List<DecompteActe> liste = decompteActeDao.rechercherActesDecompteParCriteres(criteres);
        return mapperDozerBean.mapList(liste, IdentifiantEidLibelleDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CoordonneesBancairesRemboursementDto> getCoordonneesBancairesRemboursements(Long uidAdherent) {
        if (uidAdherent == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_COORDONNEES_BANCAIRES_REMBOURSEMENT_PREREQUIS_UID_ADHERENT));
        }
        return garantieDao.getCoordonneesBancairesRemboursement(uidAdherent);
    }

    /**
     * Fixer la valeur.
     * @param decompteDao the decompteDao to set
     */
    public void setDecompteDao(DecompteDao decompteDao) {
        this.decompteDao = decompteDao;
    }

    /**
     * Fixer la valeur.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Fixer la valeur.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Fixer la valeur.
     * @param personneService the personneService to set
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Fixer la valeur.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Set the decompteOrigineDao value.
     * @param decompteOrigineDao the decompteOrigineDao to set
     */
    public void setDecompteOrigineDao(DecompteOrigineDao decompteOrigineDao) {
        this.decompteOrigineDao = decompteOrigineDao;
    }

    /**
     * Set the decompteActeDao value.
     * @param decompteActeDao the decompteActeDao to set
     */
    public void setDecompteActeDao(DecompteActeDao decompteActeDao) {
        this.decompteActeDao = decompteActeDao;
    }

    /**
     * Setter function.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Setter function.
     * @param garantieDao the garantieDao to set
     */
    public void setGarantieDao(GarantieDao garantieDao) {
        this.garantieDao = garantieDao;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

}
