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
package com.square.core.service.implementations;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.dao.interfaces.CampagneDao;
import com.square.core.dao.interfaces.CampagneStatutDao;
import com.square.core.dao.interfaces.CampagneTypeDao;
import com.square.core.model.Campagne;
import com.square.core.model.CampagneStatut;
import com.square.core.model.CampagneType;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.CampagneCriteresRechercheDto;
import com.square.core.model.dto.CampagneCriteresRechercheLibelle;
import com.square.core.model.dto.CampagneDto;
import com.square.core.model.dto.CampagneRechercheDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.RapportDto;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.service.interfaces.CampagneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.CampagneKeyUtil;
import com.square.core.util.RessourceHabilitationUtil;
import com.square.core.util.poi.DocumentXls;
import com.square.core.util.sequence.CampagneSequenceUtil;
import com.square.core.util.validation.RapportUtil;
import com.square.core.util.validation.ValidationExpressionProp;
import com.square.core.util.validation.ValidationExpressionUtil;

/**
 * Implementation des services sur les campagnes.
 * @author cblanchard - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class CampagneServiceImplementation implements CampagneService {

    /** Le dao sur les campagnes. */
    private CampagneDao campagneDao;

    /** Le dao sur les statuts. */
    private CampagneStatutDao campagneStatutDao;

    /** Le dao sur les types. */
    private CampagneTypeDao campagneTypeDao;

    /** le mapper. */
    private MapperDozerBean mapperDozerBean;

    /** Gestion des messages. */
    private MessageSourceUtil messageSourceUtil;

    /** Classe utilitaire pour la vérification. */
    private ValidationExpressionUtil validationExpressionUtil;

    /** Le service de mapping. */
    private SquareMappingService squareMappingService;

    /** Habilitation des ressources. */
    private RessourceHabilitationUtil ressourceHabilitationUtil;

    /** Classe utilitaire pour la séquence des campagnes. */
    private CampagneSequenceUtil campagneSequenceUtil;

    private int paginationExportRecherche;

    /** Le logger. */
    private static Logger logger = Logger.getLogger(CampagneServiceImplementation.class);

    @SuppressWarnings("unchecked")
    @Override
    public RemotePagingResultsDto<CampagneRechercheDto> rechercherCampagnesParCriteres(RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteres) {

        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERROR_CAMPAGNE_SEARCH_DTO_NULL));
        }

        if (criteres.getCriterias() == null) {
            throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_DTO_RECHERCHE_NULL));
        }

        final List<Campagne> result = campagneDao.rechercherCampagnesParCriteres(criteres);
        final List<CampagneRechercheDto> resultDto = mapperDozerBean.mapList(result, CampagneRechercheDto.class);

        final RemotePagingResultsDto resultPaging = new RemotePagingResultsDto<CampagneRechercheDto>();
        resultPaging.setListResults(resultDto);
        resultPaging.setTotalResults(campagneDao.getTotalResultat(criteres));
        return resultPaging;
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherCampagnesParLibelle(CampagneCriteresRechercheLibelle criteres) {
        final List<Campagne> resultat = campagneDao.rechercheCampagnesParLibelle(criteres);
        final List<IdentifiantLibelleDto> resultDto = mapperDozerBean.mapList(resultat, IdentifiantLibelleDto.class);
        return resultDto;
    }

    @Override
    public CampagneDto creerCampagne(CampagneDto campagneDto) {
        final RapportDto rapport = new RapportDto();

        verificationCampagne(campagneDto, rapport);

        if (rapport.getEnErreur()) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }
        else {

            // Date du jour
            final Calendar dateDuJour = Calendar.getInstance();
            final int anneeJour = dateDuJour.get(Calendar.YEAR);
            final int moisJour = dateDuJour.get(Calendar.MONTH);
            final int jourJour = dateDuJour.get(Calendar.DAY_OF_MONTH);
            dateDuJour.clear();
            dateDuJour.set(anneeJour, moisJour, jourJour);

            // Date de début inférieur à la date du jour
            if (campagneDto.getDateDebut().compareTo(dateDuJour) < 0) {
                throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_DATE_DEBUT_INFERIEURE_DATE_JOUR));
            }
            // Date de fin inférieur à la date du jour
            if (campagneDto.getDateFin().compareTo(dateDuJour) < 0) {
                throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_DATE_FIN_INFERIEURE_DATE_JOUR));
            }

            verificationExitance(campagneDto);

            final Campagne campagne = mapperDozerBean.map(campagneDto, Campagne.class);
            // Génération du code
            if (campagneDto.getCode() == null || "".equals(campagneDto.getCode()))
            {
                campagne.setCode(String.valueOf(campagneSequenceUtil.rechercherSequence()));
            }
            campagne.setLibelle(campagneDto.getLibelle());
            campagne.setDateCreation(Calendar.getInstance());
            campagne.setStatut(campagneStatutDao.rechercherCampagneStatutParId(squareMappingService.getIdStatutCampagneActive()));
            campagne.setType(campagneTypeDao.rechercherCampagneTypeParId(campagneDto.getType().getIdentifiant()));

            // Créateur de la campagne: ressource connectée.
            campagne.setRessource(ressourceHabilitationUtil.getUtilisateurConnecte());

            campagneDao.creerCampagne(campagne);
            mapperDozerBean.map(campagne, campagneDto);
            return campagneDto;
        }
    }

    @Override
    public CampagneDto modifierCampagne(CampagneDto campagneDto) {
        final RapportDto rapport = new RapportDto();
        // Vérification que le dto n'est pas null.
        if (campagneDto == null) {
            throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERROR_CAMPAGNE_SEARCH_DTO_NULL));
        }
        // Vérification que l'identifiant n'est pas null
        if (campagneDto.getId() == null) {
            throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_ID_NULL));
        }

        verificationCampagne(campagneDto, rapport);

        if (campagneDto.getStatut() == null) {
            rapport.ajoutRapport("CampagneDto.statut", messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_STATUT_CAMPAGNE_NULL), true);
        }

        if (rapport.getEnErreur()) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }
        else {

            verificationExitance(campagneDto);

            // Vérification que le statut existe en base
            if (campagneDto.getStatut() != null) {
                final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto(campagneDto.getStatut().getIdentifiant());
                final List<CampagneStatut> resultat = campagneStatutDao.rechercherCampagneStatutParCriteres(criteres);
                if (resultat.size() == 0) {
                    throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_STATUT_CAMPAGNE_INEXISTANT));
                }
            }

            // Recherche de la campagne en base.
            final Campagne campagneBase = campagneDao.rechercherCampagneParId(campagneDto.getId());
            if (campagneBase == null) {
                throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_ID_INEXISTANT));
            }

            mapperDozerBean.map(campagneDto, campagneBase);
            campagneBase.setDateModification(Calendar.getInstance());
            final CampagneStatut statut = campagneStatutDao.rechercherCampagneStatutParId(campagneDto.getStatut().getIdentifiant());
            campagneBase.setStatut(statut);

            return rechercherCampagnesParId(campagneBase.getId());
        }

    }

    /**
     * Méthode privée réalisant les vérification pour la création et la mise à jour de campagne.
     * @param campagneDto le dto à vérifier
     * @param le rapport d'erreur à remplir
     * @return true si la campagne est correcte false sinon
     */
    private void verificationCampagne(CampagneDto campagneDto, RapportDto rapport) {

        if (campagneDto == null) {
            throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERROR_CAMPAGNE_SEARCH_DTO_NULL));
        }

        final ValidationExpressionProp[] propsCampagne =
            new ValidationExpressionProp[] {
                new ValidationExpressionProp("libelle", null, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_LIBELLE_CAMPAGNE_NULL)),
                new ValidationExpressionProp("type", null, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_TYPE_CAMPAGNE_NULL)),
                new ValidationExpressionProp("dateDebut", null, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_DATE_DEBUT_CAMPAGNE_NULL)),
                new ValidationExpressionProp("dateFin", null, messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_DATE_FIN_CAMPAGNE_NULL))};

        validationExpressionUtil.verifierSiVide(rapport, campagneDto, propsCampagne);

    }

    /**
     * Méthode privée réalisant les vérification d'existance en base.
     * @param campagneDto la campagne à vérifier
     */
    private void verificationExitance(CampagneDto campagneDto) {
        // Vérification que le type existe en base
        if (campagneDto.getType() != null) {
            final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto(campagneDto.getType().getIdentifiant());
            final List<CampagneType> resultat = campagneTypeDao.rechercherCampagneTypeParCriteres(criteres);
            if (resultat.size() == 0) {
                throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_TYPE_CAMPAGNE_INEXISTANT));
            }
        }

        // Vérification que le nouveau libelle n'est pas déjà présent en base
        final CampagneCriteresRechercheLibelle criteres = new CampagneCriteresRechercheLibelle();
        criteres.setLibelle(campagneDto.getLibelle());
        final List<IdentifiantLibelleDto> resultat = rechercherCampagnesParLibelle(criteres);
        for (IdentifiantLibelleDto campagneLibelle : resultat) {
            if ((!(campagneLibelle.getIdentifiant().equals(campagneDto.getId()))) && (campagneLibelle.getLibelle().equals(campagneDto.getLibelle()))) {
                throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_LIBELLE_CAMPAGNE_EXITANT));
            }
        }

        // Vérification que la date de fin n'est pas inférieur à la date de début
        if (campagneDto.getDateFin().compareTo(campagneDto.getDateDebut()) < 0) {
            throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_DATE_FIN_INFERIEURE_DATE_DEBUT));
        }
    }

    @Override
    public CampagneDto rechercherCampagnesParId(Long id) {
        CampagneDto campagneDto;
        if (id == null) {
            throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_ID_NULL));
        }

        final Campagne campagne = campagneDao.rechercherCampagneParId(id);

        if (campagne == null) {
            throw new BusinessException(messageSourceUtil.get(CampagneKeyUtil.MESSAGE_ERREUR_CAMPAGNE_ID_INEXISTANT));
        } else {
            campagneDto = mapperCampagne(campagne);
        }
        return campagneDto;
    }

    /**
     * Mappe une campagne du modèle de données vers un DTO.
     * @param campagne campagne du modèle de données
     * @return campagne sous forme de DTO
     */
    private CampagneDto mapperCampagne(Campagne campagne) {
        final CampagneDto campagneDto;
        campagneDto = mapperDozerBean.map(campagne, CampagneDto.class);
        final Ressource createur = campagne.getRessource();
        campagneDto.setCreateur(new IdentifiantLibelleDto(createur.getId(), createur.getNom() + " " + createur.getPrenom()));
        return campagneDto;
    }

    @Override
    public FichierDto exporterRechercheCampagnesParCriteres(RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteres) {
        final String nomFichier = "exportRechercheCampagnes.xls";
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        final String[] entetes =
            new String[] {"Code", "Libellé", "Statut", "Date de début", "Créateur"};
        final Integer[] entetesWidth = new Integer[] {5500, 16500, 5500, 5500, 5500};

        final DocumentXls documentXls = new DocumentXls(nomFichier, entetes, entetesWidth);

        // on recupere le resultat de la recherche par pagination
        int start = 0;
        RemotePagingResultsDto<CampagneRechercheDto> resultats;
        do {
            // on redefinit la pagination
            criteres.setFirstResult(start);
            criteres.setMaxResult(paginationExportRecherche);
            resultats = rechercherCampagnesParCriteres(criteres);
            start += resultats.getListResults().size();
            // on integre les resultats au fichier
            for (CampagneRechercheDto campagne : resultats.getListResults()) {
                final String[] infosLignes =
                    new String[] {
                        campagne.getCode(),
                        campagne.getLibelle(),
                        campagne.getStatut() != null ? campagne.getStatut().getLibelle() : "",
                        campagne.getDateDebut() != null ? sdf.format(campagne.getDateDebut().getTime()) : "",
                        campagne.getRessource() != null ? campagne.getRessource().getNom() + " " + campagne.getRessource().getPrenom() : ""
                    };
                documentXls.ajouterLigne(infosLignes);
            }
        } while (start < resultats.getTotalResults());

        return documentXls.cloturerDocument();
    }

    /**
     * Modifie campagneDao.
     * @param campagneDao la nouvelle valeur de campagneDao
     */
    public void setCampagneDao(CampagneDao campagneDao) {
        this.campagneDao = campagneDao;
    }

    /**
     * Modifie mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Modifie messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Modifie validationExpressionUtil.
     * @param validationExpressionUtil la nouvelle valeur de validationExpressionUtil
     */
    public void setValidationExpressionUtil(ValidationExpressionUtil validationExpressionUtil) {
        this.validationExpressionUtil = validationExpressionUtil;
    }

    /**
     * Modifie campagneStatutDao.
     * @param campagneStatutDao la nouvelle valeur de campagneStatutDao
     */
    public void setCampagneStatutDao(CampagneStatutDao campagneStatutDao) {
        this.campagneStatutDao = campagneStatutDao;
    }

    /**
     * Modifie campagneTypeDao.
     * @param campagneTypeDao la nouvelle valeur de campagneTypeDao
     */
    public void setCampagneTypeDao(CampagneTypeDao campagneTypeDao) {
        this.campagneTypeDao = campagneTypeDao;
    }

    /**
     * Modifie squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Modifie la valeur de ressourceHabilitationUtil.
     * @param ressourceHabilitationUtil the ressourceHabilitationUtil to set
     */
    public void setRessourceHabilitationUtil(RessourceHabilitationUtil ressourceHabilitationUtil) {
        this.ressourceHabilitationUtil = ressourceHabilitationUtil;
    }

    /**
     * Set the paginationExportRecherche value.
     * @param paginationExportRecherche the paginationExportRecherche to set
     */
    public void setPaginationExportRecherche(int paginationExportRecherche) {
        this.paginationExportRecherche = paginationExportRecherche;
    }

    /**
     * Définit la valeur de campagneSequenceUtil.
     * @param campagneSequenceUtil la nouvelle valeur de campagneSequenceUtil
     */
    public void setCampagneSequenceUtil(CampagneSequenceUtil campagneSequenceUtil) {
        this.campagneSequenceUtil = campagneSequenceUtil;
    }

}
