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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.dao.interfaces.AgenceDao;
import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.dao.interfaces.RessourceEtatDao;
import com.square.core.dao.interfaces.RessourceFonctionDao;
import com.square.core.dao.interfaces.RessourceServiceDao;
import com.square.core.model.Ressources.Agence;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.Ressources.RessourceEtat;
import com.square.core.model.Ressources.RessourceFonction;
import com.square.core.model.Ressources.RessourceService;
import com.square.core.model.dto.RessourceCriteresRechercheDto;
import com.square.core.model.dto.RessourceDto;
import com.square.core.model.dto.RessourceRechercheDto;
import com.square.core.model.util.ResultatPaginationFullText;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.PersonnePhysiqueKeyUtil;
import com.square.core.util.RessourceKeyUtil;
import com.square.core.util.poi.DocumentXls;

/**
 * Implémentation des services des ressources.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class RessourceServiceImplementation implements com.square.core.service.interfaces.RessourceService {

    /** Le logger. */
    private static Logger logger = Logger.getLogger(RessourceServiceImplementation.class);

    /** Le dao sur les ressources. */
    private RessourceDao ressourceDao;

    /** Dao pour les états des ressources. */
    private RessourceEtatDao ressourceEtatDao;

    /** Dao pour les agences. */
    private AgenceDao agenceDao;

    /** Dao pour les services. */
    private RessourceServiceDao ressourceServiceDao;

    /** Dao pour les fonctions. */
    private RessourceFonctionDao ressourceFonctionDao;

    /** Service de mapping. */
    private SquareMappingService squareMappingService;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Gestion des messages. */
    private MessageSourceUtil messageSourceUtil;

    private int paginationExportRecherche;

    @Override
    public RemotePagingResultsDto<RessourceRechercheDto> rechercherRessourceFullTextParCriteres(
        RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteres) {
        try {
            if (criteres == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SEARCH_DTO_NULL));
            }

            final ResultatPaginationFullText<Ressource> resultatPagination = ressourceDao.rechercherFullTextRessource(criteres);
            logger.info("Demande de recherche en FULL TEXT NOMBRE DE RESULTAT " + resultatPagination.getListeResultats().size() + "/"
                + resultatPagination.getNombreTotalDeResultat());
            final List<Ressource> resultRessource = resultatPagination.getListeResultats();

            // Mapping Ressource -> RessourceRechercheDto
            final List<RessourceRechercheDto> resultDto = new ArrayList<RessourceRechercheDto>();

            for (Ressource ressource : resultRessource) {
                final RessourceRechercheDto dimensionRessource = mapperDozerBean.map(ressource, RessourceRechercheDto.class);
                resultDto.add(dimensionRessource);
            }
            final RemotePagingResultsDto<RessourceRechercheDto> result = new RemotePagingResultsDto<RessourceRechercheDto>();
            result.setListResults(resultDto);
            result.setTotalResults(resultatPagination.getNombreTotalDeResultat());
            return result;
        } catch (ParseException e) {
            logger.error(e);
            throw new BusinessException(e);
        }
    }

    @Override
    public RessourceDto rechercherRessourceParIdentifiant(Long id) {
        final Ressource ressource = ressourceDao.rechercherRessourceParId(id);
        if (ressource == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }
        return mapperDozerBean.map(ressource, RessourceDto.class);
    }

    @Override
    public RessourceDto rechercherRessourceParEid(String eid) {
        if (eid == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }
        final Ressource ressource = ressourceDao.rechercherRessourceParEid(eid);
        if (ressource == null) {
            logger.error("Aucune ressource trouvée correspondant à l'eid : " + eid);
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }
        return mapperDozerBean.map(ressource, RessourceDto.class);
    }

    @Override
    public FichierDto exporterRechercheRessourceFullTextParCriteres(RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteres) {
        final String nomFichier = "exportRechercheRessources.xls";

        final String[] entetes =
            new String[] {"Nom", "Prénom", "Fonction", "Agence", "Service", "Etat"};
        final Integer[] entetesWidth = new Integer[] {5500, 5500, 11000, 5500, 5500, 5500};

        final DocumentXls documentXls = new DocumentXls(nomFichier, entetes, entetesWidth);

        // on recupere le resultat de la recherche par pagination
        int start = 0;
        RemotePagingResultsDto<RessourceRechercheDto> resultats;
        do {
            // on redefinit la pagination
            criteres.setFirstResult(start);
            criteres.setMaxResult(paginationExportRecherche);
            resultats = rechercherRessourceFullTextParCriteres(criteres);
            start += resultats.getListResults().size();
            // on integre les resultats au fichier
            for (RessourceRechercheDto ressource : resultats.getListResults()) {
                final String[] infosLignes =
                    new String[] {
                        ressource.getNom(),
                        ressource.getPrenom(),
                        ressource.getFonction() != null ? ressource.getFonction().getLibelle() : "",
                        ressource.getAgence() != null ? ressource.getAgence().getLibelle() : "",
                        ressource.getService() != null ? ressource.getService().getLibelle() : "",
                        ressource.getEtat() != null ? ressource.getEtat().getLibelle() : ""
                    };
                documentXls.ajouterLigne(infosLignes);
            }
        } while (start < resultats.getTotalResults());

        return documentXls.cloturerDocument();
    }

    @Override
    public RessourceDto creerRessource(RessourceDto ressourceDto) {
        // Vérification des champs obligatoires
        controlerRessource(ressourceDto);

        // Mapping de la ressource
        final Ressource ressourceACreer = new Ressource();
        mapperRessource(ressourceDto, ressourceACreer);
        ressourceACreer.setDateCreation(Calendar.getInstance());
        ressourceACreer.setSupprime(false);

        // Création de la ressource
        ressourceDao.creerRessource(ressourceACreer);
        return mapperDozerBean.map(ressourceACreer, RessourceDto.class);
    }

    @Override
    public RessourceDto updateRessource(RessourceDto ressourceDto) {
        // Récupération de la ressource, par son id ou eid suivant ce dont on dispose
        final Ressource ressource;
        if (ressourceDto.getId() != null) {
        	ressource = ressourceDao.rechercherRessourceParId(ressourceDto.getId());
        } else {
        	ressource = ressourceDao.rechercherRessourceParEid(ressourceDto.getIdExt());
        }
        if (ressource == null) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_INEXISTANTE));
        }
        ressourceDto.setId(ressource.getId());

        if (logger.isDebugEnabled()) {
	        if (ressourceDto.getAgence() != null && ressourceDto.getAgence().getIdentifiant() != null) {
	        	logger.debug("Agence ID ==> " + ressourceDto.getAgence().getIdentifiant());
	        	logger.debug("Agence LIBELLE ==> " + ressourceDto.getAgence().getLibelle());
	        } else {
	        	logger.debug("Agence NULL ==> " + (ressourceDto.getAgence() == null));
	        }
        }
        // Vérification des champs obligatoires
        controlerRessource(ressourceDto);

        // Mise à jour de la ressource
        mapperRessource(ressourceDto, ressource);
        ressource.setDateModification(Calendar.getInstance());
        return mapperDozerBean.map(ressource, RessourceDto.class);
    }

    /**
     * Controle d'une ressource.
     * @param ressourceDto la ressource à controler.
     */
    private void controlerRessource(RessourceDto ressourceDto) {
        if (ressourceDto.getNom() == null || "".equals(ressourceDto.getNom())) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_NOM_OBLIGATOIRE));
        }
        if (ressourceDto.getPrenom() == null || "".equals(ressourceDto.getPrenom())) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_PRENOM_OBLIGATOIRE));
        }
        if (ressourceDto.getEtat() == null || ressourceDto.getEtat().getIdentifiant() == null) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_ETAT_OBLIGATOIRE));
        }
        if (ressourceDto.getAgence() == null || ressourceDto.getAgence().getIdentifiant() == null) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_AGENCE_OBLIGATOIRE));
        }
        if (ressourceDto.getService() == null || ressourceDto.getService().getIdentifiant() == null) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_SERVICE_OBLIGATOIRE));
        }
        if (ressourceDto.getFonction() == null || ressourceDto.getFonction().getIdentifiant() == null) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_FONCTION_OBLIGATOIRE));
        }
    }

    /**
     * Mappe une ressource.
     * @param ressourceDto la source de la ressource à mapper.
     * @param ressource la destination de la ressource mappée.
     */
    private void mapperRessource(RessourceDto ressourceDto, Ressource ressource) {
        // Récupération des objets référencés
        final RessourceEtat etat = ressourceEtatDao.rechercherEtatRessParId(ressourceDto.getEtat().getIdentifiant());
        if (etat == null) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_ETAT_INEXISTANT));
        }
        final Agence agence = agenceDao.rechercheAgenceParId(ressourceDto.getAgence().getIdentifiant());
        if (agence == null) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_AGENCE_INEXISTANTE));
        }
        final RessourceService service = ressourceServiceDao.rechercherServiceRessParId(ressourceDto.getService().getIdentifiant());
        if (service == null) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_SERVICE_INEXISTANT));
        }
        final RessourceFonction fonction = ressourceFonctionDao.rechercherFonctionRessParId(ressourceDto.getFonction().getIdentifiant());
        if (fonction == null) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_FONCTION_INEXISTANTE));
        }

        // Création de la ressource
        mapperDozerBean.map(ressourceDto, ressource);
        ressource.setEtat(etat);
        ressource.setAgence(agence);
        ressource.setService(service);
        ressource.setFonction(fonction);
    }

    @Override
    public void activerRessource(Long idRessource, boolean isActif) {
        // Récupération de la ressource
        final Ressource ressource = ressourceDao.rechercherRessourceParId(idRessource);
        if (ressource == null) {
            throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_INEXISTANTE));
        }
        else {
            // Modification de la ressource
            final Calendar today = Calendar.getInstance();
            ressource.setDateModification(today);
            Long idEtat = null;
            if (isActif) {
                idEtat = squareMappingService.getIdEtatActifRessource();
            }
            else {
                idEtat = squareMappingService.getIdEtatInactifRessource();
            }
            final RessourceEtat etat = ressourceEtatDao.rechercherEtatRessParId(idEtat);
            if (etat == null) {
                throw new BusinessException(messageSourceUtil.get(RessourceKeyUtil.MESSAGE_ERREUR_RESSOURCE_ETAT_INEXISTANT));
            }
            ressource.setEtat(etat);
        }
    }

    @Override
    public RessourceDto isRessourceExiste(String eidRessource) {
        final Ressource ressource = ressourceDao.rechercherRessourceParEid(eidRessource);
        if (ressource != null) {
            return mapperDozerBean.map(ressource, RessourceDto.class);
        }
        return null;
    }

    @Override
    public List<Long> rechercherIdsRessourcesFullTextParCriteres(RessourceCriteresRechercheDto criteres) {
        try {
            if (criteres == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SEARCH_DTO_NULL));
            }

            final List<Long> listeIdsRessources = ressourceDao.rechercherFullTextIdsRessources(criteres);
        return listeIdsRessources;
        } catch (ParseException e) {
            logger.error(e);
            throw new BusinessException(e);
        }
    }

    /**
     * Modifie la valeur de ressourceDao.
     * @param ressourceDao the ressourceDao to set
     */
    public void setRessourceDao(RessourceDao ressourceDao) {
        this.ressourceDao = ressourceDao;
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
     * Set the paginationExportRecherche value.
     * @param paginationExportRecherche the paginationExportRecherche to set
     */
    public void setPaginationExportRecherche(int paginationExportRecherche) {
        this.paginationExportRecherche = paginationExportRecherche;
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définit la valeur de ressourceEtatDao.
     * @param ressourceEtatDao la nouvelle valeur de ressourceEtatDao
     */
    public void setRessourceEtatDao(RessourceEtatDao ressourceEtatDao) {
        this.ressourceEtatDao = ressourceEtatDao;
    }

    /**
     * Définit la valeur de agenceDao.
     * @param agenceDao la nouvelle valeur de agenceDao
     */
    public void setAgenceDao(AgenceDao agenceDao) {
        this.agenceDao = agenceDao;
    }

    /**
     * Définit la valeur de ressourceServiceDao.
     * @param ressourceServiceDao la nouvelle valeur de ressourceServiceDao
     */
    public void setRessourceServiceDao(RessourceServiceDao ressourceServiceDao) {
        this.ressourceServiceDao = ressourceServiceDao;
    }

    /**
     * Définit la valeur de ressourceFonctionDao.
     * @param ressourceFonctionDao la nouvelle valeur de ressourceFonctionDao
     */
    public void setRessourceFonctionDao(RessourceFonctionDao ressourceFonctionDao) {
        this.ressourceFonctionDao = ressourceFonctionDao;
    }

}
