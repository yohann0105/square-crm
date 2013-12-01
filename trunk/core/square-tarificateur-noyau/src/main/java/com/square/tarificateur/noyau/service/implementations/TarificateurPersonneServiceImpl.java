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
package com.square.tarificateur.noyau.service.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.InfoSanteDto;
import com.square.core.model.dto.PersonneCreationAssureSocialDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.tarificateur.noyau.bean.personne.InfosPersonneSquareBean;
import com.square.tarificateur.noyau.dao.interfaces.DevisDao;
import com.square.tarificateur.noyau.dao.interfaces.LienFamilialDao;
import com.square.tarificateur.noyau.dao.interfaces.PersonneDao;
import com.square.tarificateur.noyau.dto.RapportDto;
import com.square.tarificateur.noyau.dto.personne.DimensionCriteresLienFamilialRechercheDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.dto.personne.PersonneTarificateurDto;
import com.square.tarificateur.noyau.exception.ControleIntegriteException;
import com.square.tarificateur.noyau.model.opportunite.Devis;
import com.square.tarificateur.noyau.model.personne.InfoSante;
import com.square.tarificateur.noyau.model.personne.LienFamilial;
import com.square.tarificateur.noyau.model.personne.Personne;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;
import com.square.tarificateur.noyau.util.MessageKeyUtil;
import com.square.tarificateur.noyau.util.personne.PersonneUtil;
import com.square.tarificateur.noyau.util.validation.RapportUtil;
import com.square.tarificateur.noyau.util.validation.ValidationInfosAdhesionUtil;

/**
 * Implémentation de TarificateurPersonneService.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class TarificateurPersonneServiceImpl implements TarificateurPersonneService {
	
    /** DAO liens familiaux. */
    private LienFamilialDao lienFamilialDao;

    /** DAO Personne. */
    private PersonneDao personneDao;

    /** DAO Devis. */
    private DevisDao devisDao;

    /** MessageSourceUtil. */
    private MessageSourceUtil messageSourceUtil;

    /** MapperDozerBean. */
    private MapperDozerBean mapperDozerBean;

    /** PersonneUtil. */
    private PersonneUtil personneUtil;

    /** Logger. */
    private Logger logger = Logger.getLogger(TarificateurPersonneServiceImpl.class);

    /** Classe utilitaire pour la vérification des infos d'adhésion. */
    private ValidationInfosAdhesionUtil validationInfosAdhesionUtil;

    /**Service des personnePhysiques.*/
    private PersonnePhysiqueService personnePhysiqueService;

    /**Service de mappign Square.*/
    private SquareMappingService squareMappingService;

    @Override
    public void transfererEidPersonne(Long eidPersonneSource, Long eidPersonneCible) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_TRANSFERT_EID_PERSONNE_SOURCE_TO_CIBLE,
        		 new String[] {String.valueOf(eidPersonneSource), String.valueOf(eidPersonneCible)}));
        if (eidPersonneSource == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_EID_TRANSFERT_PERSONNE_SOURCE_NULL));
        }
        if (eidPersonneCible == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_EID_TRANSFERT_PERSONNE_CIBLE_NULL));
        }
        // Récupération de la liste des personnes ayant l'EID source
        final List<Personne> listePersonnes = personneDao.getListePersonnesByEid(eidPersonneSource);
        if (listePersonnes != null) {
            // Transfert des EID des personnes
            for (Personne personne : listePersonnes) {
                personne.setEidPersonne(eidPersonneCible);
            }
        }
    }

    @Override
    public PersonneDto createOrUpdatePersonne(PersonneTarificateurDto personneDto) {
        if (personneDto == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_PERSONNE_VIDE));
        }
        Personne personne = null;
        if (personneDto.getId() == null) {
            personne = new Personne();
        }
        else {
            personne = personneDao.getPersonne(personneDto.getId());
            if (personne == null) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_PERSONNE_INEXISTANTE));
            }
        }

        // Mapping
        mapperDozerBean.map(personneDto, personne);
        personne.getInfoSante().setReferent(personne);

        // TODO Voir pour la sauvegarde des bénéficiaires
        if (personneDto.getId() == null) {
            personneDao.savePersonne(personne);
        }
        return personneUtil.mapperPersonneEnPersonneDto(personne, new InfosPersonneSquareBean());
    }

    @Override
    public PersonneDto getPersonneById(Long idPersonne) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_PERSONNE,
       		 new String[] {String.valueOf(idPersonne)}));
        final Personne personne = personneDao.getPersonne(idPersonne);
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_PERSONNE_INEXISTANTE));
        }
        return personneUtil.mapperPersonneEnPersonneDto(personne, new InfosPersonneSquareBean());
    }

    @Override
    public PersonneDto getPersonneByEid(Long eidPersonne) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_PERSONNE_BY_EID,
          		 new String[] {String.valueOf(eidPersonne)}));
        final Personne personne = personneDao.getPersonneByEid(eidPersonne);
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_PERSONNE_INEXISTANTE));
        }
        return personneUtil.mapperPersonneEnPersonneDto(personne, new InfosPersonneSquareBean());
    }

    @Override
    public PersonneDto createAssureSocial(PersonneDto assureSocialDto, Long idDevis, boolean fromOuvertureOpp) {
        final RapportDto rapportErreurs = validationInfosAdhesionUtil.validerAssureSocial(assureSocialDto, fromOuvertureOpp);

        com.square.core.model.dto.PersonneDto personneSquare = null;
        if (assureSocialDto.getEidPersonne() != null) {
            personneSquare = personnePhysiqueService.rechercherPersonneParIdentifiant(assureSocialDto.getEidPersonne());
            // O vérifie que la csp existe pour la personne dans Square (bug 8609)
            if (personneSquare.getCsp() == null || personneSquare.getCsp().getIdentifiant() == null) {
                rapportErreurs.ajoutRapport("PersonneDto.csp", messageSourceUtil.get(MessageKeyUtil.ERREUR_SAVE_PERSONNE_SQUARE_CSP_NULL), true);
            }
        }

        if (Boolean.TRUE.equals(rapportErreurs.getEnErreur())) {
            RapportUtil.logRapport(rapportErreurs, logger);
            throw new ControleIntegriteException(rapportErreurs);
        }

        final Devis devis = devisDao.getDevis(idDevis);
        if (assureSocialDto.getEidPersonne() != null) {
            for (Personne assureSocial : devis.getListeAssuresSociaux()) {
                if (assureSocial.getEidPersonne().equals(assureSocialDto.getEidPersonne())) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_ASSURE_SOCIAL_EXISTANT));
                }
            }
        }

        final Personne assureSocial = mapperDozerBean.map(assureSocialDto, Personne.class);
        // si il existe un referent, on recupere le numero Unique du referent, sinon on en cree un nouveau
        InfoSante infoSante = null;
        if (assureSocialDto.getInfoSante() != null && assureSocialDto.getInfoSante().getIdReferent() != null) {
            infoSante = personneDao.getPersonne(assureSocialDto.getInfoSante().getIdReferent()).getInfoSante();
        }
        else {
            infoSante = mapperDozerBean.map(assureSocialDto.getInfoSante(), InfoSante.class);
            infoSante.setReferent(assureSocial);
        }
        assureSocial.setInfoSante(infoSante);

        personneDao.savePersonne(assureSocial);

        // on cree la personne dans Square si elle n'existe pas déjà
        if (assureSocialDto.getEidPersonne() == null) {
            final PersonneCreationAssureSocialDto assureSocialSquare = mapperAssureSocial(assureSocialDto);
            final com.square.core.model.dto.PersonneDto personneSquareCree = personnePhysiqueService.creerPersonnePhysiqueAssureSocial(assureSocialSquare);
            // on renseigne l'eid
            assureSocial.setEidPersonne(personneSquareCree.getIdentifiant());
        }
        else {
            // sinon on met à jour les infos santé
            final InfoSanteDto infoSanteSquare = new InfoSanteDto();
            if (StringUtils.isNotBlank(infoSante.getNumSecuriteSocial()) && StringUtils.isNotBlank(infoSante.getCleSecuriteSocial())) {
                infoSanteSquare.setNro(squareMappingService.convertirNssVersNro(infoSante.getNumSecuriteSocial(), infoSante.getCleSecuriteSocial()));
            }
            infoSanteSquare.setCaisse(new CaisseSimpleDto(infoSante.getEidCaisse()));
            infoSanteSquare.setIdReferent(personneSquare.getIdentifiant());
            personneSquare.setInfoSante(infoSanteSquare);

            personnePhysiqueService.modifierPersonnePhysique(personneSquare);
        }

        // on rattache la personne au devis
        if (devis.getListeAssuresSociaux() == null) {
            devis.setListeAssuresSociaux(new HashSet<Personne>());
        }
        devis.getListeAssuresSociaux().add(assureSocial);

        return getPersonneById(assureSocial.getId());
    }

    private PersonneCreationAssureSocialDto mapperAssureSocial(PersonneDto assureSocialDto) {
        final PersonneCreationAssureSocialDto assureSocialSquare = mapperDozerBean.map(assureSocialDto, PersonneCreationAssureSocialDto.class);
        if (assureSocialDto.getInfoSante() != null) {
            final InfoSanteDto infoSante = new InfoSanteDto();
            infoSante.setCaisse(new CaisseSimpleDto(assureSocialDto.getInfoSante().getEidCaisse()));
            infoSante.setCaisseRegime(new IdentifiantLibelleDto(assureSocialDto.getInfoSante().getEidRegime()));
            infoSante.setIdReferent(assureSocialDto.getInfoSante().getEidReferent());
            infoSante.setNro(assureSocialDto.getInfoSante().getNumSecuriteSocial() + assureSocialDto.getInfoSante().getCleSecuriteSocial());
            assureSocialSquare.setInfoSante(infoSante);
        }
        return assureSocialSquare;
    }

    @Override
    public List<PersonneDto> getAssuresSociauxByIdDevis(Long idDevis) {
        final Devis devis = devisDao.getDevis(idDevis);
        final List<PersonneDto> listeAssuresSociaux = new ArrayList<PersonneDto>();
        if (devis != null && devis.getListeAssuresSociaux() != null) {
            for (Personne personne : devis.getListeAssuresSociaux()) {
                listeAssuresSociaux.add(getPersonneById(personne.getId()));
            }
        }
        return listeAssuresSociaux;
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherLiensFamiliauxParCriteres(DimensionCriteresLienFamilialRechercheDto criteres) {
        final List<LienFamilial> result = lienFamilialDao.rechercherLiensFamiliauxParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public IdentifiantLibelleDto rechercherRelationAssureSocial(Long idPersonne) {
        final Personne personne = personneDao.getPersonne(idPersonne);
        if (personne.getRelationAssureSocial() != null) {
            return mapperDozerBean.map(personne.getRelationAssureSocial().getLienFamilial(), IdentifiantLibelleDto.class);
        }
        return null;
    }

    /**
     * Définit la valeur de messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Définit la valeur de personneDao.
     * @param personneDao la nouvelle valeur de personneDao
     */
    public void setPersonneDao(PersonneDao personneDao) {
        this.personneDao = personneDao;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de personneUtil.
     * @param personneUtil la nouvelle valeur de personneUtil
     */
    public void setPersonneUtil(PersonneUtil personneUtil) {
        this.personneUtil = personneUtil;
    }

    /**
     * Set the validationInfosAdhesionUtil value.
     * @param validationInfosAdhesionUtil the validationInfosAdhesionUtil to set
     */
    public void setValidationInfosAdhesionUtil(ValidationInfosAdhesionUtil validationInfosAdhesionUtil) {
        this.validationInfosAdhesionUtil = validationInfosAdhesionUtil;
    }

    /**
     * Set the devisDao value.
     * @param devisDao the devisDao to set
     */
    public void setDevisDao(DevisDao devisDao) {
        this.devisDao = devisDao;
    }

    /**
     * Set the personnePhysiqueService value.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Set the squareMappingService value.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définit le lienFamilialDao.
     * @param lienFamilialDao le nouveau lienFamilialDao
     */
    public void setLienFamilialDao(LienFamilialDao lienFamilialDao) {
        this.lienFamilialDao = lienFamilialDao;
    }  
}
