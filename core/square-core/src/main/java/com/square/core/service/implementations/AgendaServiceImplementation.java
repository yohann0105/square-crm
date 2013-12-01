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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.dao.interfaces.ActionDao;
import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.model.Action;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.AgendasDisponiblesCriteresDto;
import com.square.core.model.dto.DimensionCriteresRechercheRessourceDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.PersonneBaseDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneMoraleDto;
import com.square.core.model.dto.RendezVousCriteresRechercheDto;
import com.square.core.model.dto.RendezVousDto;
import com.square.core.model.dto.RendezVousTiersCriteresDto;
import com.square.core.model.dto.RendezVousTiersDto;
import com.square.core.model.plugin.AgendaTiersSquarePlugin;
import com.square.core.model.util.CritereRechercheAction;
import com.square.core.service.interfaces.AgendaService;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.AgendaKeyUtil;

/**
 * Implémentation du service de l'agenda.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AgendaServiceImplementation implements AgendaService {

    private static final String ESPACE = " ";

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Le dao pour les actions. */
    private ActionDao actionDao;

    /** Le service des personnes. */
    private PersonneService personneService;

    private RessourceDao ressourceDao;

    private DimensionService dimensionService;

    private SquareMappingService squareMappingService;

    private AgendaTiersSquarePlugin agendaTiersSquarePlugin;

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public List<RendezVousDto> rechercherRendezVousParCriteres(RendezVousCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_CRITERES_NULL));
        }
        if (criteres.getIdRessource() == null) {
            throw new BusinessException(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_RESSOURCE_NULL));
        }
        if (criteres.getDateMinDateDebut() == null) {
            throw new BusinessException(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_DATE_MIN_NULL));
        }
        if (criteres.getDateMaxDateDebut() == null) {
            throw new BusinessException(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_DATE_MAX_NULL));
        }
        if (criteres.getDateMinDateDebut().after(criteres.getDateMaxDateDebut())) {
            throw new BusinessException(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_DATE_MIN_SUP_DATE_MAX));
        }

        final Ressource ressource = ressourceDao.rechercherRessourceParId(criteres.getIdRessource());
        if (ressource == null) {
            throw new BusinessException(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_RESSOURCE_INEXISTANTE));
        }
        final CritereRechercheAction criteresActions = mapperDozerBean.map(criteres, CritereRechercheAction.class);
        criteresActions.setVisibleAgenda(true);
        final List<Action> actions = actionDao.rechercherActionParCriteresEtRessource(criteresActions, ressource);

        final List<RendezVousDto> listeResult = new ArrayList<RendezVousDto>();

        for (Action action : actions) {
            final RendezVousDto rendezVous = mapperDozerBean.map(action, RendezVousDto.class);
            if (action.getActionAffectation().getPersonne() != null) {
                final PersonneBaseDto personne = personneService.rechercherPersonneParId(action.getActionAffectation().getPersonne().getId());
                if (personne instanceof PersonneDto) {
                    final PersonneDto personnePhysique = (PersonneDto) personne;
                    rendezVous.setNomPersonne(personnePhysique.getNom().toUpperCase());
                    rendezVous.setPrenomPersonne(StringUtils.capitalize(personnePhysique.getPrenom()));
                    rendezVous.setTitre(personnePhysique.getNom().toUpperCase() + ESPACE + StringUtils.capitalize(personnePhysique.getPrenom()));
                    rendezVous.setNaturePersonne(personnePhysique.getNaturePersonne());
                } else if (personne instanceof PersonneMoraleDto) {
                    final PersonneMoraleDto personneMorale = (PersonneMoraleDto) personne;
                    rendezVous.setRaisonSociale(personneMorale.getRaisonSociale().toUpperCase());
                    rendezVous.setTitre(personneMorale.getNumEntreprise() + ESPACE + personneMorale.getRaisonSociale().toUpperCase());
                    rendezVous.setNaturePersonne(personneMorale.getNature());
                }
            }
            listeResult.add(rendezVous);
        }

        try {
            // on recupere les rendez vous tiers
            final RendezVousTiersCriteresDto criteresTiers = new RendezVousTiersCriteresDto();
            criteresTiers.setIdUtilisateurConnecte(Long.parseLong(ressource.getIdentifiantExterieur()));
            criteresTiers.setDateMin(criteres.getDateMinDateDebut());
            criteresTiers.setDateMax(criteres.getDateMaxDateDebut());
            final List<RendezVousTiersDto> listeRendezVousTiers = agendaTiersSquarePlugin.getListeRendezVous(criteresTiers);
            for (RendezVousTiersDto rendezVousTiersDto : listeRendezVousTiers) {
                if (rendezVousTiersDto.getDate() != null) {
                    listeResult.add((RendezVousDto) mapperDozerBean.map(rendezVousTiersDto, RendezVousDto.class));
                }
            }
        } catch (BusinessException e) {
            logger.error(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_RECUPERATION_AGENDA_TIERS), e);
        }

        Collections.sort(listeResult, new Comparator<RendezVousDto>() {
            @Override
            public int compare(RendezVousDto r0, RendezVousDto r1) {
                return r0.getDate().compareTo(r1.getDate());
            }
        });
        return listeResult;
    }

    @Override
    public List<DimensionRessourceDto> rechercherAgendasDisponibles(AgendasDisponiblesCriteresDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_CRITERES_NULL));
        }
        final DimensionCriteresRechercheRessourceDto criteresRessources = new DimensionCriteresRechercheRessourceDto();
        criteresRessources.setNomPrenom(criteres.getLibelle());
        criteresRessources.setIsSupprime(false);
        final List<Long> idsEtats = new ArrayList<Long>();
        idsEtats.add(squareMappingService.getIdEtatActifRessource());
        criteresRessources.setIdEtats(idsEtats);
        final List<Long> idsFonctions = new ArrayList<Long>();
        if (criteres.getIdAnimateur() != null) {
            // on recupere la ressource animateur
            final Ressource animateur = ressourceDao.rechercherRessourceParId(criteres.getIdAnimateur());
            if (animateur.getFonction() == null || !animateur.getFonction().getId().equals(squareMappingService.getIdFonctionAnimateur())) {
                throw new BusinessException(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_PAS_ANIMATEUR));
            }
            if (animateur.getAgence() == null) {
                logger.error(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_ANIMATEUR_SANS_AGENCE) + " : animateur = " + animateur.getId());
                throw new BusinessException(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_ANIMATEUR_SANS_AGENCE));
            }
            if (animateur.getAgence().getRegion() == null) {
                logger.error(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_AGENCE_SANS_REGION) + " : agence = " + animateur.getAgence().getId());
                throw new BusinessException(messageSourceUtil.get(AgendaKeyUtil.MESSAGE_ERREUR_AGENDA_AGENCE_SANS_REGION));
            }
            idsFonctions.add(squareMappingService.getIdFonctionCC());
            // on filtre par region
            final List<Long> idsRegions = new ArrayList<Long>();
            idsRegions.add(animateur.getAgence().getRegion().getId());
            criteresRessources.setIdRegions(idsRegions);
        } else {
            idsFonctions.add(squareMappingService.getIdFonctionCC());
            idsFonctions.add(squareMappingService.getIdFonctionAnimateur());
        }
        criteresRessources.setIdFonctions(idsFonctions);
        final List<DimensionRessourceDto> listeAgendas = new ArrayList<DimensionRessourceDto>();
        listeAgendas.add(dimensionService.getRessourceConnectee());
        listeAgendas.addAll(dimensionService.rechercherRessourceParCriteres(criteresRessources));
        return listeAgendas;
    }

    /**
     * Set the value of messageSourceUtil.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Set the value of mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the value of actionDao.
     * @param actionDao the actionDao to set
     */
    public void setActionDao(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    /**
     * Set the value of personneService.
     * @param personneService the personneService to set
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Set the value of agendaTiersSquarePlugin.
     * @param agendaTiersSquarePlugin the agendaTiersSquarePlugin to set
     */
    public void setAgendaTiersSquarePlugin(AgendaTiersSquarePlugin agendaTiersSquarePlugin) {
        this.agendaTiersSquarePlugin = agendaTiersSquarePlugin;
    }

    /**
     * Set the value of ressourceDao.
     * @param ressourceDao the ressourceDao to set
     */
    public void setRessourceDao(RessourceDao ressourceDao) {
        this.ressourceDao = ressourceDao;
    }

    /**
     * Set the value of dimensionService.
     * @param dimensionService the dimensionService to set
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * Set the value of squareMappingService.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

}
