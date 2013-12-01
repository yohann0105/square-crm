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
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.dao.interfaces.ActionDao;
import com.square.core.dao.interfaces.ActionNatureDao;
import com.square.core.dao.interfaces.ActionObjetDao;
import com.square.core.dao.interfaces.ActionResultatDao;
import com.square.core.dao.interfaces.ActionSousObjetDao;
import com.square.core.dao.interfaces.ActionStatutDao;
import com.square.core.dao.interfaces.ActionTypeDao;
import com.square.core.dao.interfaces.AdresseDao;
import com.square.core.dao.interfaces.AgenceDao;
import com.square.core.dao.interfaces.CampagneDao;
import com.square.core.dao.interfaces.OpportuniteDao;
import com.square.core.dao.interfaces.OpportuniteStatutDao;
import com.square.core.dao.interfaces.PersonnePhysiqueDao;
import com.square.core.dao.interfaces.RelationDao;
import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.model.Action;
import com.square.core.model.ActionNature;
import com.square.core.model.ActionObjet;
import com.square.core.model.ActionSousObjet;
import com.square.core.model.ActionStatut;
import com.square.core.model.ActionType;
import com.square.core.model.Adresse;
import com.square.core.model.Campagne;
import com.square.core.model.Opportunite;
import com.square.core.model.OpportuniteAttribution;
import com.square.core.model.OpportuniteStatut;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.Relation;
import com.square.core.model.Ressources.Agence;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.ActionCreationDto;
import com.square.core.model.dto.AdresseCriteresRechercheDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.IdentifiantEidLibelleDto;
import com.square.core.model.dto.OpportuniteCriteresRechercheDto;
import com.square.core.model.dto.OpportuniteDto;
import com.square.core.model.dto.OpportuniteMaJStatutDto;
import com.square.core.model.dto.OpportuniteModificationDto;
import com.square.core.model.dto.OpportuniteSimpleDto;
import com.square.core.model.dto.RapportDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.exception.ConfirmationCreationOpportuniteException;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.OpportuniteKeyUtil;
import com.square.core.util.RessourceHabilitationUtil;
import com.square.core.util.validation.RapportUtil;
import com.square.core.util.validation.ValidationExpressionProp;
import com.square.core.util.validation.ValidationExpressionUtil;

/**
 * Implémentation des services opportunités.
 * @author cblanchard - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class OpportuniteServiceImplementation implements OpportuniteService {

    private static final String ESPACE = " ";

    /** Le logger. */
    private static Logger logger = Logger.getLogger(OpportuniteServiceImplementation.class);

    /**
     * Mapper Dozer.
     */
    private MapperDozerBean mapperDozerBean;

    /**
     * Classe utilitaire pour accéder aux messages.
     */
    private MessageSourceUtil messageSourceUtil;

    /** Classe utilitaire pour la vérification. */
    private ValidationExpressionUtil validationExpressionUtil;

    /** Le dao sur les natures d'action. */
    private ActionNatureDao actionNatureDao;

    /** Le dao sur les campagnes. */
    private CampagneDao campagneDao;

    /** Le dao sur les agences. */
    private AgenceDao agenceDao;

    /** Le dao sur les ressources. */
    private RessourceDao ressourceDao;

    /** Le dao sur les personnes physiques. */
    private PersonnePhysiqueDao personnePhysiqueDao;

    /** Le dao sur les types des actions. */
    private ActionTypeDao actionTypeDao;

    /** Le dao sur les objets. */
    private ActionObjetDao actionObjetDao;

    /** Le dao du sous Objet. */
    private ActionSousObjetDao actionSousObjetDao;

    /** Le dao des opportunités. */
    private OpportuniteDao opportuniteDao;

    /** Le service de mapping. */
    private SquareMappingService squareMappingService;

    /** Le dao des actions. */
    private ActionDao actionDao;

    /** Le dao sur les résultats du dao. */
    private ActionResultatDao actionResultatDao;

    /** Le dao sur les statuts d'action. */
    private ActionStatutDao actionStatutDao;

    /** le service des actions. */
    private ActionService actionService;

    /** le dao sur les statuts d'opportunités. */
    private OpportuniteStatutDao opportuniteStatutDao;

    /** DAO Adresse. */
    private AdresseDao adresseDao;

    /** DAO Relation. */
    private RelationDao relationDao;

    /** Habilitation des ressources . */
    private RessourceHabilitationUtil ressourceHabilitationUtil;

    @Override
    public OpportuniteDto creerOpportunite(OpportuniteDto opportuniteDto) {

        // Vérification des champs obligatoires
        final RapportDto rapportDto = new RapportDto();
        verificationNull(opportuniteDto, rapportDto);

        if (rapportDto.getEnErreur()) {
            RapportUtil.logRapport(rapportDto, logger);
            throw new ControleIntegriteException(rapportDto);
        }

        if (opportuniteDto.getIdPersonnePhysique() == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_PERSONNE_NULL));
        }

        // Vérification de l'existance de la personne
        final PersonnePhysique personne = personnePhysiqueDao.rechercherPersonneParId(opportuniteDto.getIdPersonnePhysique());
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_PERSONNE_INEXISTANTE));
        }

    	// mantis 8360 : Si la personne a un référent sans CSP ou caisse, on refuse la création d'une opportunité
    	if (personne.getInfoSante() != null && personne.getInfoSante().getReferent() != null && !personne.getInfoSante().getReferent().equals(personne)) {
    		if (personne.getInfoSante().getReferent().getCsp() == null) {
    			final String[] errorText = new String[] {personne.getInfoSante().getReferent().getNum()};
    			throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_CREATION_PERSONNE_REFERENT_CSP, errorText));
    		}
    	}

        // Vérification que la personne n'est pas décédée
        if (personne.isDeces() || squareMappingService.getIdNaturePersonneDecede().equals(personne.getNature().getId())) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_PERSONNE_DECEDEE));
        }

        // Vérification de l'égibilité de la famille
        if (!isFamilleEligiblePourOpportunite(opportuniteDto.getIdPersonnePhysique())) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_FAMILLE_NON_ELIGIBLE));
        }

        // Vérification de l'existance de l'action source et récupération de l'action
        Action action = null;
        if (opportuniteDto.getIdActionSource() != null) {
            action = actionDao.rechercherActionParId(opportuniteDto.getIdActionSource());
            if (action == null) {
                throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_ACTION_SOURCE_INEXISTANTE));
            }
            if (action.getActionAffectation().getOpportunite() != null) {
                throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_DEJA_ACTIVE));
            }
        }

        // Récupération des données
        ActionNature nature = null;
        Campagne campagne = null;
        Ressource createur = null;
        Agence agence = null;
        Ressource ressource = null;
        ActionType type = null;
        ActionObjet actionObjet = null;
        ActionSousObjet actionSousObjet = null;

        // Récupération du créateur
        if (opportuniteDto.getIdCreateur() != null) {
            createur = ressourceDao.rechercherRessourceParId(opportuniteDto.getIdCreateur());
            if (createur == null) {
                throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_CREATEUR_INEXISTANT));
            }
        }

        // Si l'action n'est pas renseigné, nouvelle opportunité
        if (action == null) {
            // Récupération de la nature
            nature = actionNatureDao.rechercherNatureActionParId(opportuniteDto.getIdNature());
            if (nature == null) {
                throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_NATURE_INEXISTANT));
            }
            // Récupération de la campagne
            if (opportuniteDto.getIdCampagne() != null) {
                campagne = campagneDao.rechercherCampagneParId(opportuniteDto.getIdCampagne());
                if (campagne == null) {
                    throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_CAMPAGNE_INEXISTANTE));
                }
            }
            // Récupération de l'agence
            if (opportuniteDto.getIdAgence() != null) {
                agence = agenceDao.rechercheAgenceParId(opportuniteDto.getIdAgence());
                if (agence == null) {
                    throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_AGENCE_INEXISTANTE,
                        new String[] {opportuniteDto.getIdAgence().toString()}));
                }
            }
            // Récupération de la ressource
            if (opportuniteDto.getIdRessource() != null) {
                ressource = ressourceDao.rechercherRessourceParId(opportuniteDto.getIdRessource());
                if (ressource == null) {
                    throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_RESSOURCE_INEXISTANTE));
                }
                if (agence == null) {
                    agence = ressource.getAgence();
                }
            }
            // Récupération du type
            type = actionTypeDao.rechercherTypeActionParId(opportuniteDto.getIdType());
            if (type == null) {
                throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_TYPE_INEXISTANT));
            }
            // Récupération de l'objet
            actionObjet = actionObjetDao.rechercherObjetActionParId(opportuniteDto.getIdObjet());
            if (actionObjet == null) {
                throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_OBJET_INEXISTANT));
            }
            // Récupération du sous objet
            if (opportuniteDto.getIdSousObjet() != null) {
                actionSousObjet = actionSousObjetDao.rechercherSousObjetActionParId(opportuniteDto.getIdSousObjet());
                if (actionSousObjet == null) {
                    throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_SOUS_OBJET_INEXISTANT));
                }
            }
        } else {
            // Si l'action est renseigné on reprend les informations de l'action

            // Récupération de la nature de l'action source
            nature = action.getNature();
            // Récupération de la campagne si l'action est renseigné
            campagne = action.getCampagne();
            // Récupération de l'agence si l'action est renseigné
            agence = action.getActionAttribution().getAgence();
            // Récupération de la ressource si l'action est renseigné
            if (action.getActionAttribution().getRessource() != null) {
                ressource = action.getActionAttribution().getRessource();
            }
            // Récupération du type
            type = action.getType();
            // Récupération de l'objet
            actionObjet = action.getObjet();
            // Récupération du sous objet
            actionSousObjet = action.getSousObjet();
        }

        // Vérification que la personne ne possède pas plus d'une opportunité active
        if (opportuniteDto.getCreationForcee() == null || !opportuniteDto.getCreationForcee()) {
            final OpportuniteCriteresRechercheDto criteres = new OpportuniteCriteresRechercheDto();
            criteres.setIdPersonnePhysique(personne.getId());
            final List<Opportunite> listOpportunite = opportuniteDao.rechercherOpportunitesParCriteres(criteres);
            final Long idStatutOpportuniteNonRenseigne = squareMappingService.getIdStatutOpportuniteNonRenseigne();
            if (listOpportunite != null) {
                for (Opportunite opportunite : listOpportunite) {
                    if (opportunite.getStatut().getId().equals(idStatutOpportuniteNonRenseigne)) {
                        throw new ConfirmationCreationOpportuniteException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_CONFIRMATION_CREATION_OPPORTUNITE));
                    }
                }
            }
        }

        // Création de l'opportunité
        final Opportunite opportunite = new Opportunite();

        // TODO Génération de l'eid. (à integrer numero devis avec tarificateur)
        final Date dateDuJour = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateDuJour);
        final String annee = String.valueOf(calendar.get(Calendar.YEAR));
        String mois = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        if (mois.length() == 1) {
            mois = "0" + mois;
        }

        final Long sequence = opportuniteDao.rechercherSequence();
        final StringBuilder caracteresManquants = new StringBuilder();

        final int nbreCarateresAajouter = 7 - sequence.toString().length();
        for (int i = 0; i < nbreCarateresAajouter; i++) {
            caracteresManquants.append("0");
        }

        final String identifiantExterieur = annee + mois + caracteresManquants.toString() + sequence.toString();
        // opportunite.setIdentifiantExterieur(String.valueOf(aleatoire.nextInt()));
        opportunite.setIdentifiantExterieur(identifiantExterieur);

        opportunite.setDateCreation(Calendar.getInstance());
        opportunite.setPersonnePhysique(personne);
        // Le créateur est la personne connectée s'il n'est pas renseigné
        if (createur == null) {
            opportunite.setRessource(ressourceHabilitationUtil.getUtilisateurConnecte());
        } else {
            opportunite.setRessource(createur);
        }
        final OpportuniteAttribution opportuniteAttribution = new OpportuniteAttribution();
        opportuniteAttribution.setAgence(agence);
        opportuniteAttribution.setRessource(ressource);
        opportuniteAttribution.setDateCreation(Calendar.getInstance());
        opportunite.setOpportuniteAttribution(opportuniteAttribution);
        final OpportuniteStatut opportuniteStatut =
            opportuniteStatutDao.rechercherOpportuniteStatutParId(squareMappingService.getIdStatutOpportuniteNonRenseigne());
        if (opportuniteStatut == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPPORTUNITE_STATUT_INEXISTANT));
        }
        opportunite.setStatut(opportuniteStatut);
        opportunite.setSupprime(false);
        opportuniteDao.creerOpportunite(opportunite);

        // Création de l'action d'origine si aucune action source de renseignée
        ActionCreationDto actionSourceEnregistree = null;
        if (action == null) {
            final Calendar dateAction = Calendar.getInstance();
            Calendar dateDecalage = null;
            final Long idPriorite = null;
            ActionCreationDto actionSource = null;
            if (BooleanUtils.isTrue(opportuniteDto.getIsFromSiteWeb())) {
                dateDecalage = Calendar.getInstance();
                dateDecalage.add(Calendar.MINUTE, squareMappingService.getDecalageDateAffichageAction());
                ressource = ressourceDao.rechercherRessourceParId(opportuniteDto.getIdCreateur());
                actionSource = creerActionCreation(dateAction, dateDecalage, personne.getId(), nature.getId(), type.getId(), idPriorite,
                	actionObjet.getId(), actionSousObjet, campagne, ressource, ressource.getAgence(), opportuniteDto.getIdCreateur());
            }
            else {
                actionSource = creerActionCreation(dateAction, dateDecalage, personne.getId(), nature.getId(), type.getId(), idPriorite,
                    	actionObjet.getId(), actionSousObjet, campagne, ressource, agence, opportuniteDto.getIdCreateur());
            }
            actionSource.setIdOpportunite(opportunite.getId());
            actionSourceEnregistree = actionService.creerAction(actionSource);

            // On passe l'action créer en relance.
            final Action actionSourceModification = actionDao.rechercherActionParId(actionSourceEnregistree.getIdentifiant());
            actionSourceModification.setStatut(actionStatutDao.rechercherStatutParId(squareMappingService.getIdStatutTerminer()));
            actionSourceModification.setResultat(actionResultatDao.rechercherActionResultatParId(squareMappingService.getIdResultatOpportunite()));
            actionSourceModification.setMailNotification(false);
            actionSourceModification.setDateTerminee(Calendar.getInstance());

        } else {
            // Modification de l'action si elle est renseignée
            action.getActionAffectation().setOpportunite(opportunite);
            action.setDateModification(Calendar.getInstance());
            action.setResultat(actionResultatDao.rechercherActionResultatParId(squareMappingService.getIdResultatOpportunite()));
            action.setStatut(actionStatutDao.rechercherStatutActionParId(squareMappingService.getIdStatutTerminer()));
            action.setDateTerminee(Calendar.getInstance());
        }
        // Attention : Ne pas modifier la condition
        if (opportuniteDto.getPasDeRelance() == null || Boolean.FALSE.equals(opportuniteDto.getPasDeRelance())) {
            // Création d'une action liée de relance
            final Calendar dateActionRelance = Calendar.getInstance();
            Calendar dateDecalage = null;
            Long idPriorite = null;
            // L'action de relance prend la nature téléphone sortant par défaut
            final Long idNature = squareMappingService.getIdNatureActionTelephoneSortant();
            if (BooleanUtils.isTrue(opportuniteDto.getIsFromSiteWeb())) {
                dateActionRelance.add(Calendar.MINUTE, squareMappingService.getDecalageDateActionRelance());
                dateDecalage = Calendar.getInstance();
                dateDecalage.add(Calendar.MINUTE, squareMappingService.getDecalageDateAffichageAction());
                // Les actions de relance du Site Web sont automatiquement créées en priorité "haute"
                idPriorite = squareMappingService.getIdActionPrioriteUrgente();
            }
            if (BooleanUtils.isTrue(opportuniteDto.getIsFromSiteWeb())) {
            	if (opportuniteDto.getIdRessource() != null) {
	                ressource = ressourceDao.rechercherRessourceParId(opportuniteDto.getIdRessource());
	                if (ressource == null) {
	                    throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_RESSOURCE_INEXISTANTE));
	                }
	                agence = ressource.getAgence();
            	}
            	else {
            		// on remet la ressource à null pour éviter que l'action soit attribuée à WEB Site
            		ressource = null;
            	}
            }
            final ActionCreationDto actionRelance =
                creerActionCreation(dateActionRelance, dateDecalage, personne.getId(), idNature, squareMappingService.getIdTypeActionRelance(),
                    idPriorite, squareMappingService.getIdObjetActionClient(), actionSousObjet, campagne, ressource, agence, opportuniteDto.getIdCreateur());
            actionRelance.setIdOpportunite(opportunite.getId());
            if (action != null) {
                actionRelance.setIdActionSource(action.getId());
            } else {
                actionRelance.setIdActionSource(actionSourceEnregistree.getIdentifiant());
            }
            final ActionCreationDto actionRelanceEnregistree = actionService.creerAction(actionRelance);
            final Action actionRelanceBase = actionDao.rechercherActionParId(actionRelanceEnregistree.getIdentifiant());
            actionRelanceBase.setStatut(actionStatutDao.rechercherStatutActionParId(squareMappingService.getIdStatutActionParDefaut()));
        }

        final OpportuniteDto opportuniteResultatDto = opportuniteDto;
        opportuniteResultatDto.setIdOpportunite(opportunite.getId());
        opportuniteResultatDto.setEidOpportunite(opportunite.getIdentifiantExterieur());
        opportuniteResultatDto.setIdCreateur(opportunite.getRessource().getId());
        opportuniteResultatDto.setIdActionSource(action == null ? actionSourceEnregistree.getIdentifiant() : action.getId());
        return opportuniteResultatDto;
    }

    /**
     * Méthode privée vérifiant que les champs obligatoires ne sont pas null.
     * @param opportuniteDto l'objet à vérifier
     * @param rapportDto le rapport d'erreur
     */
    private void verificationNull(OpportuniteDto opportuniteDto, RapportDto rapportDto) {
        if (opportuniteDto == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_DTO_CREATION_NULL));
        }

        if (opportuniteDto.getIdActionSource() == null) {
            final ValidationExpressionProp[] propsOpportunite =
                new ValidationExpressionProp[] {
                    new ValidationExpressionProp("idNature", null, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_NATURE_NULL)),
                    new ValidationExpressionProp("idType", null, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_TYPE_NULL)),
                    new ValidationExpressionProp("idObjet", null, messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_OBJET_NULL))};

            validationExpressionUtil.verifierSiVide(rapportDto, opportuniteDto, propsOpportunite);
            if (opportuniteDto.getIdAgence() == null && opportuniteDto.getIdRessource() == null) {
                rapportDto.ajoutRapport("OpportuniteDto.idAgence", messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_AGENGE_RESSOURCE_NULL), true);
                rapportDto.ajoutRapport("OpportuniteDto.idRessource", messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_AGENGE_RESSOURCE_NULL), true);
            }
        }
    }

    /**
     * Méthode privée permettant de créer un ActionCreationDto à partir des paramètres.
     * @param dateAction la date de l'action
     * @param dateAffichageAction la date d'affichage de l'action
     * @param idPersonne la personne
     * @param idNature la nature de l'action
     * @param idType le type de l'action
     * @param idPriorite la priorité de l'action
     * @param idActionObjet l'objet de l'action
     * @param actionSousObjet le sous objet de l'action
     * @param campagne la campagne liée à l'action
     * @param ressource la ressource de l'action
     * @param agence l'agence de l'action
     */
    private ActionCreationDto creerActionCreation(Calendar dateAction, Calendar dateAffichageAction, Long idPersonne, Long idNature, Long idType,
        Long idPriorite, Long idActionObjet, ActionSousObjet actionSousObjet, Campagne campagne, Ressource ressource, Agence agence, Long idCreateur) {
        final ActionCreationDto actionSource = new ActionCreationDto();
        actionSource.setDateAction(dateAction);
        actionSource.setDateAffichageAction(dateAffichageAction);
        actionSource.setIdPersonne(idPersonne);
        actionSource.setIdNatureAction(idNature);
        actionSource.setIdType(idType);
        actionSource.setIdPriorite(idPriorite);
        actionSource.setIdObjet(idActionObjet);
        actionSource.setIdCreateur(idCreateur);
        if (actionSousObjet != null) {
            actionSource.setIdSousObjet(actionSousObjet.getId());
        }
        if (campagne != null) {
            actionSource.setIdCampagne(campagne.getId());
        }
        if (ressource != null) {
            actionSource.setIdCommercial(ressource.getId());
        }
        if (agence != null) {
            actionSource.setIdAgence(agence.getId());
        }
        if (ressource != null) {
            actionSource.setIdCommercial(ressource.getId());
        }
        return actionSource;
    }

    @Override
    public List<OpportuniteSimpleDto> rechercherOpportuniteParCriteres(OpportuniteCriteresRechercheDto criteres) {
        // Vérification que le dto de critères n'est pas null
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_DTO_CRITERE_RECHERCHE_NULL));
        }
        // // Vérification que la personne n'est pas null
        // if (criteres.getIdPersonnePhysique() == null) {
        // throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_PERSONNE_NULL));
        // }
        // // Vérification que la personne existe
        // if (personnePhysiqueDao.rechercherPersonneParId(criteres.getIdPersonnePhysique()) == null) {
        // throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_PERSONNE_INEXISTANTE));
        // }

        // Récupération des opportunités de la personne
        final List<Opportunite> listOpportunite = opportuniteDao.rechercherOpportunitesParCriteres(criteres);

        final List<OpportuniteSimpleDto> listOpportuniteDto = new ArrayList<OpportuniteSimpleDto>();
        // Sur chacune des opportunités construction de l'opportuniteSimpleDto
        for (Opportunite opportunite : listOpportunite) {
            final OpportuniteSimpleDto opportuniteSimpleDto = mapperDozerBean.map(opportunite, OpportuniteSimpleDto.class);
            if (opportunite.getRessource() != null) {
                final IdentifiantEidLibelleDto createur = new IdentifiantEidLibelleDto();
                createur.setIdentifiant(opportunite.getRessource().getId());
                createur.setLibelle(opportunite.getRessource().getNom() + ESPACE + opportunite.getRessource().getPrenom());
                createur.setIdentifiantExterieur(opportunite.getRessource().getIdentifiantExterieur());
                opportuniteSimpleDto.setCreateur(createur);
            }
            if (opportunite.getOpportuniteAttribution().getRessource() != null) {
                final DimensionRessourceDto ressource = new DimensionRessourceDto();
                ressource.setIdentifiant(opportunite.getOpportuniteAttribution().getRessource().getId());
                ressource.setNom(opportunite.getOpportuniteAttribution().getRessource().getNom());
                ressource.setPrenom(opportunite.getOpportuniteAttribution().getRessource().getPrenom());
                ressource.setIdentifiantExterieur(opportunite.getOpportuniteAttribution().getRessource().getIdentifiantExterieur());
                opportuniteSimpleDto.setRessource(ressource);
            }
            if (opportunite.getOpportuniteAttribution().getAgence() != null) {
                final IdentifiantEidLibelleDto agence = new IdentifiantEidLibelleDto();
                agence.setIdentifiant(opportunite.getOpportuniteAttribution().getAgence().getId());
                agence.setLibelle(opportunite.getOpportuniteAttribution().getAgence().getLibelle());
                agence.setIdentifiantExterieur(opportunite.getOpportuniteAttribution().getAgence().getIdentifiantExterieur());
                opportuniteSimpleDto.setAgence(agence);
            }
            // Recherche de l'action source liée à l'opportunité
            final List<Action> listeActionsSources = actionDao.rechercherActionsSourcesParOpportunite(opportunite.getId());
            if (listeActionsSources != null && !listeActionsSources.isEmpty()) {
                mapperDozerBean.map(listeActionsSources.get(0), opportuniteSimpleDto);
            } else {
                // Si pas d'action source, recherche de la première action liée à l'opportunité
                final List<Action> listAction = actionDao.rechercherActionsParOpportunite(opportunite.getId());
                if (listAction != null && listAction.size() != 0) {
                    mapperDozerBean.map(listAction.get(0), opportuniteSimpleDto);
                }
            }
            listOpportuniteDto.add(opportuniteSimpleDto);
        }

        return listOpportuniteDto;
    }

    @Override
    public void modifierOpportunite(OpportuniteModificationDto opportuniteModificationDto) {
        // Vérification que les champs obligatoires ne sont pas null
        verificationNullModification(opportuniteModificationDto);

        Ressource ressource = null;
        Agence agence = null;

        final DimensionRessourceDto ressourceDto = opportuniteModificationDto.getRessource();
        // Si la ressource est spécifiée
        if (ressourceDto != null && ressourceDto.getIdentifiant() != null) {
            // On récupère la ressource dans la base de données
            logger.debug("Récupération de la ressource id " + ressourceDto.getIdentifiant() + " dans la base de données...");
            ressource = ressourceDao.rechercherRessourceParId(opportuniteModificationDto.getRessource().getIdentifiant());
            if (ressource == null) {
                throw new TechnicalException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_RESSOURCE_INEXISTANTE,
                    new String[] {String.valueOf(ressourceDto.getIdentifiant())}));
            }
        }

        // Si l'agence est spécifiée
        final IdentifiantLibelleDto agenceDto = opportuniteModificationDto.getAgence();
        if (agenceDto != null && agenceDto.getIdentifiant() != null) {
            // On récupère l'agence dans la base de données
            agence = agenceDao.rechercheAgenceParId(opportuniteModificationDto.getAgence().getIdentifiant());
            if (agence == null) {
                throw new TechnicalException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_AGENCE_INEXISTANTE,
                    new String[] {String.valueOf(agenceDto.getIdentifiant())}));
            }
        } else if (ressource != null) {
            // Sinon on attribue l'agence de la ressource si une ressource a été spécifiée
            agence = ressource.getAgence();
        }

        // Récupération de l'opportunité
        final Opportunite opportunite = opportuniteDao.rechercherOpportuniteParId(opportuniteModificationDto.getIdOpportunite());
        // Modification de l'opportunité
        opportunite.getOpportuniteAttribution().setRessource(ressource);
        opportunite.getOpportuniteAttribution().setAgence(agence);
    }

    @Override
    public void mettreAJourStatutOpportunite(OpportuniteMaJStatutDto opportuniteMaJStatut) {
        // Vérification que les champs ne sont pas null
        if (opportuniteMaJStatut == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_MODIFICATION_DTO_NULL));
        }
        if (opportuniteMaJStatut.getIdOpportunite() == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_ID_OPP_NULL));
        }
        if (opportuniteMaJStatut.getStatut() == null || opportuniteMaJStatut.getStatut().getIdentifiant() == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_STATUT_NULL));
        }

        // Vérification de l'existance du statut
        final OpportuniteStatut opportuniteStatut =
            opportuniteStatutDao.rechercherOpportuniteStatutParId(opportuniteMaJStatut.getStatut().getIdentifiant());
        if (opportuniteStatut == null) {
            logger.error("Le statut de l'opportunité n'existe pas : " + opportuniteMaJStatut.getStatut().getIdentifiant());
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPPORTUNITE_STATUT_INEXISTANT));
        }
        final Long idStatutApres = opportuniteMaJStatut.getStatut().getIdentifiant();

        // Récupération de l'opportunité
        final Opportunite opportunite = opportuniteDao.rechercherOpportuniteParId(opportuniteMaJStatut.getIdOpportunite());
        final Long idStatutAvant = opportunite.getStatut().getId();
        // Modification de l'opportunité
        opportunite.setStatut(opportuniteStatut);

        // Fermeture des actions de types relance liées à l'opportunité si l'opportunité passe à "Transformée"
        final Long idStatutTransforme = squareMappingService.getIdStatutOpportuniteTransforme();
        if (!idStatutTransforme.equals(idStatutAvant) && idStatutTransforme.equals(idStatutApres)) {
            final List<Action> listeActionsOpportunite = actionDao.rechercherActionsParOpportunite(opportuniteMaJStatut.getIdOpportunite());
            final Long idTypeActionRelance = squareMappingService.getIdTypeActionRelance();
            final Long idStatutActionAFaire = squareMappingService.getIdStatutAFaire();
            final ActionStatut statutTermine = actionStatutDao.rechercherStatutActionParId(squareMappingService.getIdStatutTerminer());
            final Calendar maintenant = Calendar.getInstance();
            for (Action action : listeActionsOpportunite) {
                if (action.getType() != null && idTypeActionRelance.equals(action.getType().getId())
                        && (action.getStatut() == null || action.getStatut().getId() == null || idStatutActionAFaire.equals(action.getStatut().getId()))) {
                    action.setStatut(statutTermine);
                    action.setDateModification(maintenant);
                    action.setDateTerminee(maintenant);
                }
            }
        }
    }

    /**
     * Méthode privée vérifiant que les champs obligatoires ne sont pas null.
     * @param opportuniteModificationDto l'objet à vérifier
     */
    private void verificationNullModification(OpportuniteModificationDto opportuniteModificationDto) {
        final RapportDto rapport = new RapportDto();
        // Vérification que les champs ne sont pas null
        if (opportuniteModificationDto == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_MODIFICATION_DTO_NULL));
        }
        if (opportuniteModificationDto.getIdOpportunite() == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_ID_OPP_NULL));
        }
        if (opportuniteModificationDto.getAgence() == null && opportuniteModificationDto.getRessource() == null) {
            rapport.ajoutRapport("opportuniteModificationDto.ressource", messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_RESSOURCE_NULL), true);
            rapport.ajoutRapport("opportuniteModificationDto.agence", messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_AGENGE_RESSOURCE_NULL), true);
            throw new ControleIntegriteException(rapport);
        }
        // Vérification de l'existance de l'opportunité
        if (opportuniteDao.rechercherOpportuniteParId(opportuniteModificationDto.getIdOpportunite()) == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_ID_OPP_INEXISTANT));
        }
    }

    @Override
    public boolean hasPersonneOpportunite(Long idPersonne) {
        final OpportuniteCriteresRechercheDto criteres = new OpportuniteCriteresRechercheDto();
        criteres.setIdPersonnePhysique(idPersonne);
        final List<Opportunite> listeOpportunites = opportuniteDao.rechercherOpportunitesParCriteres(criteres);
        return listeOpportunites != null && listeOpportunites.size() > 0;
    }

    @Override
    public void transfererOpportunites(Long idPersonneSource, Long idPersonneCible) {
        logger.debug("Transfert des opportunités de la personne " + idPersonneSource + " vers la personne " + idPersonneCible);
        if (idPersonneSource == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_TRANSFERT_PERSONNE_SOURCE_NULL));
        }
        if (idPersonneCible == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_TRANSFERT_PERSONNE_CIBLE_NULL));
        }
        // Récupération de la personne cible
        final PersonnePhysique personneCible = personnePhysiqueDao.rechercherPersonneParId(idPersonneCible);
        if (personneCible == null) {
            throw new BusinessException(messageSourceUtil.get(OpportuniteKeyUtil.MESSAGE_ERREUR_OPP_TRANSFERT_PERSONNE_CIBLE_INEXISTANTE));
        }
        // Récupération de la liste des opportunités de la personne source
        final OpportuniteCriteresRechercheDto criteres = new OpportuniteCriteresRechercheDto();
        criteres.setIdPersonnePhysique(idPersonneSource);
        final List<Opportunite> listeOpportunites = opportuniteDao.rechercherOpportunitesParCriteres(criteres);
        // Transfert des opportunités de la source vers la cible
        if (listeOpportunites != null && !listeOpportunites.isEmpty()) {
            for (Opportunite opportunite : listeOpportunites) {
                opportunite.setPersonnePhysique(personneCible);
                opportunite.setDateModification(Calendar.getInstance());
            }
        }
    }

    @Override
    public boolean isFamilleEligiblePourOpportunite(Long idPersonnePrincipale) {
        logger.debug("Test de l'éligibilité de la création d'une opportunité pour la famille de la personne " + idPersonnePrincipale);

        boolean hasAdressePersonnePrincipale = false;
        boolean hasAdresseConjoint = false;

        // Recherche d'une adresse principale pour la personne principale
        final AdresseCriteresRechercheDto criteresAdresse = new AdresseCriteresRechercheDto();
        criteresAdresse.setIdPersonne(idPersonnePrincipale);
        final List<Adresse> listeAdressesPersonnePrincipale = adresseDao.rechercherAdresseParCritere(criteresAdresse);
        hasAdressePersonnePrincipale = possedeAdressePrincipale(listeAdressesPersonnePrincipale);

        // Recherche d'une adresse principale sur le conjoint si pas trouvée sur la personne principale
        if (!hasAdressePersonnePrincipale) {
            // Récupération des relations conjoint de la personne de la personne principale
            final RelationCriteresRechercheDto criteresRelations = new RelationCriteresRechercheDto();
            final List<Long> typesRelation = new ArrayList<Long>();
            typesRelation.add(squareMappingService.getIdTypeRelationConjoint());
            criteresRelations.setTypes(typesRelation);
            criteresRelations.setIdPersonneSource(idPersonnePrincipale);
            criteresRelations.setActif(Boolean.TRUE);
            final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteriasRelations =
                new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelations, 0, Integer.MAX_VALUE);
            final List<Relation> listeRelations = relationDao.rechercherRelationsParCriteres(criteriasRelations);
            for (Relation relation : listeRelations) {
                criteresAdresse.setIdPersonne(relation.getPersonneCible().getId());
                final List<Adresse> listeAdressesConjoint = adresseDao.rechercherAdresseParCritere(criteresAdresse);
                hasAdresseConjoint = possedeAdressePrincipale(listeAdressesConjoint);
                if (hasAdresseConjoint) {
                    break;
                }
            }
        }
        final boolean familleEligible = hasAdressePersonnePrincipale || hasAdresseConjoint;
        logger.debug("Famille de la personne " + idPersonnePrincipale + " éligible : " + familleEligible + "; hasAdressePersonnePrincipale = "
            + hasAdressePersonnePrincipale + ", hasAdresseConjoint = " + hasAdresseConjoint);
        return familleEligible;
    }

    @Override
    public void supprimerOpportunite(Long idOpportunite) {
        final List<Action> listeActionsOpportunite = actionDao.rechercherActionsParOpportunite(idOpportunite);
        for (Action action : listeActionsOpportunite) {
        	actionDao.supprimerLogiquement(action);
        }
    	opportuniteDao.supprimerOpportunite(idOpportunite);
    }

    @Override
    public boolean hasPersonneOpportuniteByStatuts(Long idPersonne, List<Long> listeIdsStatuts) {
        return opportuniteDao.hasPersonneOpportuniteByStatuts(idPersonne, listeIdsStatuts);
    }

    /**
     * Teste si une liste d'adresses a une adresse principale.
     * @param listeAdresses la liste d'adresses
     * @return true si la liste possède une adresse principale, false sinon
     */
    private boolean possedeAdressePrincipale(List<Adresse> listeAdresses) {
        final Long idNatureAdressePrincipale = squareMappingService.getIdNatureAdressePrincipale();
        for (Adresse adresse : listeAdresses) {
            final Long idNatureAdresse = adresse.getNature().getId();
            if (idNatureAdressePrincipale.equals(idNatureAdresse) && adresse.getCodePostalCommune() != null && adresse.getCodePostalCommune().getId() != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Modifie la valeur de messageSourceUtil.
     * @param messageSourceUtil the messageSourceUtil to set
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
     * Modifie actionNatureDao.
     * @param actionNatureDao la nouvelle valeur de actionNatureDao
     */
    public void setActionNatureDao(ActionNatureDao actionNatureDao) {
        this.actionNatureDao = actionNatureDao;
    }

    /**
     * Modifie campagneDao.
     * @param campagneDao la nouvelle valeur de campagneDao
     */
    public void setCampagneDao(CampagneDao campagneDao) {
        this.campagneDao = campagneDao;
    }

    /**
     * Modifie agenceDao.
     * @param agenceDao la nouvelle valeur de agenceDao
     */
    public void setAgenceDao(AgenceDao agenceDao) {
        this.agenceDao = agenceDao;
    }

    /**
     * Modifie ressourceDao.
     * @param ressourceDao la nouvelle valeur de ressourceDao
     */
    public void setRessourceDao(RessourceDao ressourceDao) {
        this.ressourceDao = ressourceDao;
    }

    /**
     * Modifie personnePhysiqueDao.
     * @param personnePhysiqueDao la nouvelle valeur de personnePhysiqueDao
     */
    public void setPersonnePhysiqueDao(PersonnePhysiqueDao personnePhysiqueDao) {
        this.personnePhysiqueDao = personnePhysiqueDao;
    }

    /**
     * Modifie actionTypeDao.
     * @param actionTypeDao la nouvelle valeur de actionTypeDao
     */
    public void setActionTypeDao(ActionTypeDao actionTypeDao) {
        this.actionTypeDao = actionTypeDao;
    }

    /**
     * Modifie actionObjetDao.
     * @param actionObjetDao la nouvelle valeur de actionObjetDao
     */
    public void setActionObjetDao(ActionObjetDao actionObjetDao) {
        this.actionObjetDao = actionObjetDao;
    }

    /**
     * Modifie actionSousObjetDao.
     * @param actionSousObjetDao la nouvelle valeur de actionSousObjetDao
     */
    public void setActionSousObjetDao(ActionSousObjetDao actionSousObjetDao) {
        this.actionSousObjetDao = actionSousObjetDao;
    }

    /**
     * Modifie opportuniteDao.
     * @param opportuniteDao la nouvelle valeur de opportuniteDao
     */
    public void setOpportuniteDao(OpportuniteDao opportuniteDao) {
        this.opportuniteDao = opportuniteDao;
    }

    /**
     * Modifie actionDao.
     * @param actionDao la nouvelle valeur de actionDao
     */
    public void setActionDao(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    /**
     * Modifie squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Modifie actionService.
     * @param actionService la nouvelle valeur de actionService
     */
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    /**
     * Modifie opportuniteStatutDao.
     * @param opportuniteStatutDao la nouvelle valeur de opportuniteStatutDao
     */
    public void setOpportuniteStatutDao(OpportuniteStatutDao opportuniteStatutDao) {
        this.opportuniteStatutDao = opportuniteStatutDao;
    }

    /**
     * Modifie actionResultatDao.
     * @param actionResultatDao la nouvelle valeur de actionResultatDao
     */
    public void setActionResultatDao(ActionResultatDao actionResultatDao) {
        this.actionResultatDao = actionResultatDao;
    }

    /**
     * Modifie actionStatutDao.
     * @param actionStatutDao la nouvelle valeur de actionStatutDao
     */
    public void setActionStatutDao(ActionStatutDao actionStatutDao) {
        this.actionStatutDao = actionStatutDao;
    }

    /**
     * Modifie mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Modifie ressourceHabilitationUtil.
     * @param ressourceHabilitationUtil la nouvelle valeur de ressourceHabilitationUtil
     */
    public void setRessourceHabilitationUtil(RessourceHabilitationUtil ressourceHabilitationUtil) {
        this.ressourceHabilitationUtil = ressourceHabilitationUtil;
    }

    /**
     * Définit la valeur de adresseDao.
     * @param adresseDao la nouvelle valeur de adresseDao
     */
    public void setAdresseDao(AdresseDao adresseDao) {
        this.adresseDao = adresseDao;
    }

    /**
     * Définit la valeur de relationDao.
     * @param relationDao la nouvelle valeur de relationDao
     */
    public void setRelationDao(RelationDao relationDao) {
        this.relationDao = relationDao;
    }

    @Override
    public int countOpportuniteParCriteres(OpportuniteCriteresRechercheDto criteres) {
        return opportuniteDao.countOpportunitesParCriteres(criteres);
    }
}
