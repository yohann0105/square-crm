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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.dao.interfaces.ActionAffectationDao;
import com.square.core.dao.interfaces.ActionDao;
import com.square.core.dao.interfaces.ActionDureeDao;
import com.square.core.dao.interfaces.ActionNatureDao;
import com.square.core.dao.interfaces.ActionNatureResultatDao;
import com.square.core.dao.interfaces.ActionObjetDao;
import com.square.core.dao.interfaces.ActionPrioriteDao;
import com.square.core.dao.interfaces.ActionResultatDao;
import com.square.core.dao.interfaces.ActionSousObjetDao;
import com.square.core.dao.interfaces.ActionStatutDao;
import com.square.core.dao.interfaces.ActionTypeDao;
import com.square.core.dao.interfaces.AgenceDao;
import com.square.core.dao.interfaces.CampagneDao;
import com.square.core.dao.interfaces.OpportuniteDao;
import com.square.core.dao.interfaces.PersonneDao;
import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.model.Action;
import com.square.core.model.ActionAffectation;
import com.square.core.model.ActionAttribution;
import com.square.core.model.ActionDuree;
import com.square.core.model.ActionNature;
import com.square.core.model.ActionObjet;
import com.square.core.model.ActionPriorite;
import com.square.core.model.ActionSousObjet;
import com.square.core.model.ActionType;
import com.square.core.model.Campagne;
import com.square.core.model.Commentaire;
import com.square.core.model.Document;
import com.square.core.model.Opportunite;
import com.square.core.model.Personne;
import com.square.core.model.PersonneMorale;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.Ressources.Agence;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.ActionCreationDto;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.ActionDto;
import com.square.core.model.dto.ActionModificationDto;
import com.square.core.model.dto.ActionNotificationInfosDto;
import com.square.core.model.dto.ActionRechercheDto;
import com.square.core.model.dto.ActionResultatDto;
import com.square.core.model.dto.ActionSyntheseDto;
import com.square.core.model.dto.CritereActionSyntheseDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.DocumentDto;
import com.square.core.model.dto.HistoriqueCommentaireDto;
import com.square.core.model.dto.MailDto;
import com.square.core.model.dto.OpportuniteDto;
import com.square.core.model.dto.RapportDto;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.model.plugin.EmailSquarePlugin;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.ActionKeyUtil;
import com.square.core.util.RessourceHabilitationUtil;
import com.square.core.util.poi.DocumentXls;
import com.square.core.util.validation.RapportUtil;
import com.square.core.util.validation.ValidationExpressionProp;
import com.square.core.util.validation.ValidationExpressionUtil;

/**
 * Implémentation du service des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class ActionServiceImplementation implements ActionService {

    private static final String ESPACE = " ";

    /** Le logger. */
    private static Logger logger = Logger.getLogger(ActionServiceImplementation.class);

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Le dao pour les actions. */
    private ActionDao actionDao;

    /** Classe utilitaire pour la vérification. */
    private ValidationExpressionUtil validationExpressionUtil;

    /** Le dao sur les personnes. */
    private PersonneDao personneDao;

    /** Le dao sur les natures d'actions. */
    private ActionNatureDao actionNatureDao;

    /** Le dao sur les types d'actions. */
    private ActionTypeDao actionTypeDao;

    /** Le dao sur les objets d'action. */
    private ActionObjetDao actionObjetDao;

    /** Le dao pour les sous objets. */
    private ActionSousObjetDao actionSousObjetDao;

    /** Le dao pour les priorités. */
    private ActionPrioriteDao actionPrioriteDao;

    /** DAO pour les durées des actions. */
    private ActionDureeDao actionDureeDao;

    /** Le dao pour les campagnes. */
    private CampagneDao campagneDao;

    /** Le service de mapping. */
    private SquareMappingService squareMappingService;

    /** Le dao sur les affectations. */
    private ActionAffectationDao actionAffectationDao;

    /** Le dao sur les ressources. */
    private RessourceDao ressourceDao;

    /** Le dao sur les agences. */
    private AgenceDao agenceDao;

    /** Le dao sur les statuts. */
    private ActionStatutDao actionStatutDao;

    /** Le dao sur les résultats. */
    private ActionResultatDao actionResultatDao;

    /** Le dao sur les opportunités. */
    private OpportuniteDao opportuniteDao;

    /** Le service sur les opportunités. */
    private OpportuniteService opportuniteService;

    /** Habilitation des ressources . */
    private RessourceHabilitationUtil ressourceHabilitationUtil;

    /** Dao pour les natures de résultat. */
    private ActionNatureResultatDao actionNatureResultatDao;

    private int paginationExportRecherche;

    /** PLugin des emails de square. */
    private EmailSquarePlugin emailSquarePlugin;

    /** Expéditeur de l'email envoyé. */
    private String expediteurNoReply;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public RemotePagingResultsDto<ActionRechercheDto> rechercherActionParCritere(RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_RECHERCHE_ACTION_DTO_NULL));
        }
        // Les actions trouvées sous forme de dto.
        final List<ActionRechercheDto> listeActions = actionDao.rechercheActionParCriteres(criteres);
        final RemotePagingResultsDto<ActionRechercheDto> result = new RemotePagingResultsDto<ActionRechercheDto>();
        result.setListResults(listeActions);
        result.setTotalResults(actionDao.nombreTotalAction(criteres));
        logger.info("Demande de recherche NOMBRE DE RESULTAT " + result.getListResults().size() + "/" + result.getTotalResults());
        return result;
    }

    @Override
    public ActionCreationDto creerAction(ActionCreationDto actionCreationDto) {
        // Vérification que les informations ne sont pas null
        final RapportDto rapport = new RapportDto();
        verificationAction(actionCreationDto, rapport);

        if (rapport.getEnErreur()) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }
        // Vérification de l'existance en base des données liées

        // Vérification que l'idpersonne existe
        final Personne personne = personneDao.rechercherPersonneParId(actionCreationDto.getIdPersonne());
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDPERSONNE_INEXISTANT));
        }

        // Vérification que la nature de l'action existe
        final ActionNature actionNature = actionNatureDao.rechercherNatureActionParId(actionCreationDto.getIdNatureAction());
        if (actionNature == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_ACTION_INEXISTANT));
        }
        // Vérification que le type existe
        final ActionType actionType = actionTypeDao.rechercherTypeActionParId(actionCreationDto.getIdType());
        if (actionType == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TYPE_ACTION_INEXISTANT));
        }
        // Vérification que l'objet existe
        final ActionObjet actionObjet = actionObjetDao.rechercherObjetActionParId(actionCreationDto.getIdObjet());
        if (actionObjet == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_OBJET_INEXISTANT));
        }
        // Vérification que le sous objet existe
        ActionSousObjet actionSousObjet = null;
        if (actionCreationDto.getIdSousObjet() != null) {
            actionSousObjet = actionSousObjetDao.rechercherSousObjetActionParId(actionCreationDto.getIdSousObjet());
            if (actionSousObjet == null) {
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_SOUS_OBJET_INEXISTANT));
            }
        }
        // Vérification que la priorité existe
        ActionPriorite actionPriorite = null;
        if (actionCreationDto.getIdPriorite() != null) {
            actionPriorite = actionPrioriteDao.rechercherPrioriteActionParId(actionCreationDto.getIdPriorite());
            if (actionPriorite == null) {
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_PRIORITE_INEXISTANTE));
            }
        }
        // Vérification que la campagne existe
        Campagne actionCampagne = null;
        if (actionCreationDto.getIdCampagne() != null) {
            actionCampagne = campagneDao.rechercherCampagneParId(actionCreationDto.getIdCampagne());
            if (actionCampagne == null) {
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_CAMPAGNE_INEXISTANTE));
            }
        }
        // Vérification que l'agence existe
        Agence agence = null;
        if (actionCreationDto.getIdAgence() != null) {
            agence = agenceDao.rechercheAgenceParId(actionCreationDto.getIdAgence());
            if (agence == null) {
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_AGENCE_INEXISTANTE));
            }
        }
        // Vérification que la ressource existe
        Ressource ressource = null;
        if (actionCreationDto.getIdCommercial() != null) {
            ressource = ressourceDao.rechercherRessourceParId(actionCreationDto.getIdCommercial());
            if (ressource == null) {
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_COMMERCIAL_INEXISTANTE));
            }
        }

        // Vérification de la notification
        if (actionCreationDto.getRappel() != null && actionCreationDto.getRappel()) {
            if (actionCreationDto.getIdNotificationList() != null) {
                final ActionNotificationInfosDto notification = squareMappingService.getActionNotificationParId(actionCreationDto.getIdNotificationList());
                if (notification == null) {
                    throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_RAPPEL_SANS_TEMPS));
                }
            } else {
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_RAPPEL_SANS_TEMPS));
            }
        }

        // Vérification que la durée existe
        ActionDuree dureeAction = null;
        if (actionCreationDto.getIdDuree() != null) {
            dureeAction = actionDureeDao.rechercherDureeActionParId(actionCreationDto.getIdDuree());
            if (dureeAction == null) {
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DUREE_INEXISTANTE));
            }
        }

        // Création d'une nouvelle action
        final Action action = new Action();

        // ActionDto -> Action
        mapperAction(actionCreationDto, action, actionNature, actionType, actionObjet, actionSousObjet, actionPriorite, actionCampagne, dureeAction);

        final Opportunite opportunite = opportuniteDao.rechercherOpportuniteParId(actionCreationDto.getIdOpportunite());

        Action actionSource = null;
        if (actionCreationDto.getIdActionSource() != null) {
            actionSource = actionDao.rechercherActionParId(actionCreationDto.getIdActionSource());
            if (actionSource == null) {
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDSOURCE_INEXISTANTE));
            }
        } else {
            // Si l'action source n'est pas renseignée mais que l'opportunité est renseignée, l'action source est la dernière action liée à l'opportunité
            if (opportunite != null) {
                // Recherche des actions liées à l'opportunité
                final CritereActionSyntheseDto critereActionSyntheseDto = new CritereActionSyntheseDto();
                critereActionSyntheseDto.setIdOpportunite(opportunite.getId());
                critereActionSyntheseDto.setIdPersonne(personne.getId());
                final List<Stack<ActionSyntheseDto>> listeActionsOpportunite = recupererActionsSynthese(critereActionSyntheseDto);
                if (listeActionsOpportunite != null && !listeActionsOpportunite.isEmpty()) {
                    if (listeActionsOpportunite.get(0).peek() != null) {
                        actionSource = actionDao.rechercherActionParId(listeActionsOpportunite.get(0).peek().getId());
                    }
                }
            }
        }
        if (actionSource != null) {
            // On reprend la campagne
            action.setCampagne(actionSource.getCampagne());
            if (actionNature != null) {
                action.setNature(actionNature);
            } else {
                action.setNature(actionSource.getNature());
            }
            action.setActionSource(actionSource);
        }

        // Créer l'affectation vers une personne.
        if (actionCreationDto.getIdActionSource() == null) {
            final ActionAffectation actionAffectation = new ActionAffectation();
            // Vérification que l'idpersonne existe
            actionAffectation.setPersonne(personne);
            actionAffectation.setOpportunite(opportunite);
            actionAffectation.setDateCreation(Calendar.getInstance());
            actionAffectation.setSupprime(false);
            actionAffectationDao.creerActionAffectation(actionAffectation);
            action.setActionAffectation(actionAffectation);
        } else {
            // Création d'un action liée : Identifiant de la personne correspondant à l'action source
            final ActionAffectation actionAffectation = new ActionAffectation();
            actionAffectation.setPersonne(actionSource.getActionAffectation().getPersonne());
            actionAffectation.setOpportunite(opportunite);
            actionAffectation.setDateCreation(Calendar.getInstance());
            actionAffectation.setSupprime(false);
            // Si l'action source possède une opportunité on la récupère
            if (actionSource.getActionAffectation() != null && actionSource.getActionAffectation().getOpportunite() != null) {
                actionAffectation.setOpportunite(actionSource.getActionAffectation().getOpportunite());
            }
            actionAffectationDao.creerActionAffectation(actionAffectation);
            action.setActionAffectation(actionAffectation);
        }

        // Le créateur de l'action : si pas spécifié, l'utilisateur connecté
        action.setRessource(actionCreationDto.getIdCreateur() != null ? ressourceDao.rechercherRessourceParId(actionCreationDto.getIdCreateur())
            : ressourceHabilitationUtil.getUtilisateurConnecte());

        // ATTRIBUTIONS DE L'ACTION
        final ActionAttribution actionAttribution = new ActionAttribution();

        // Récupération du commercial
        if (actionCreationDto.getIdCommercial() != null) {
            actionAttribution.setRessource(ressource);
        }
        actionAttribution.setAgence(agence);

        action.setActionAttribution(actionAttribution);
        action.setDate(actionCreationDto.getDateAction());
        action.setVisibleAgenda(actionCreationDto.getVisibleAgenda() != null && actionCreationDto.getVisibleAgenda().booleanValue());

        final Long idStatutAction =
            actionCreationDto.getIdStatut() != null ? actionCreationDto.getIdStatut() : squareMappingService.getIdStatutActionParDefaut();
        action.setStatut(actionStatutDao.rechercherStatutActionParId(idStatutAction));
        if (squareMappingService.getIdStatutActionTermine().equals(idStatutAction)) {
            action.setDateTerminee(Calendar.getInstance());
        }

        // Constuction du commentaire
        if (actionCreationDto.getCommentaires() != null) {
            final List<Commentaire> commentaires = mapperDozerBean.mapList(actionCreationDto.getCommentaires(), Commentaire.class);
            action.setCommentaires(commentaires);
        }

        // Affectation des documents
        if (actionCreationDto.getDocuments() != null) {
            final List<Document> documents = mapperDozerBean.mapList(actionCreationDto.getDocuments(), Document.class);
            action.setDocuments(documents);
        }

        // Création de l'action
        actionDao.creerAction(action);

        // Action -> ActionDto
        mapperDozerBean.map(action, actionCreationDto);
        return actionCreationDto;
    }

    /**
     * Méthode privée qui vérifie si les champs obligatoires sont renseignés.
     * @param actionDto les données sources
     * @param rapport le rapport à remplir au fur et à mesure
     */
    private void verificationAction(ActionCreationDto actionDto, RapportDto rapport) {
        if (actionDto == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DTO_NULL));
        }

        final ValidationExpressionProp[] propsAction =
            new ValidationExpressionProp[] {
                new ValidationExpressionProp("dateAction", null, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DATE_ACTION_NULL)),
                new ValidationExpressionProp("idPersonne", null, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDPERSONNE_NULL)),
                new ValidationExpressionProp("idNatureAction", null, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_ACTION_NULL)),
                new ValidationExpressionProp("idType", null, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TYPE_ACTION_NULL)),
                new ValidationExpressionProp("idObjet", null, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_OBJET_NULL)),
                new ValidationExpressionProp("idAgence", null, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_AGENCE_NULL))};

        validationExpressionUtil.verifierSiVide(rapport, actionDto, propsAction);

        if (actionDto.getDateAction() != null) {
            // On vérifie que la date d'action est postérieure à la date courante
            final Calendar dateCourante = DateUtils.truncate(Calendar.getInstance(), Calendar.DAY_OF_MONTH);
            final Calendar dateAction = DateUtils.truncate((Calendar) actionDto.getDateAction().clone(), Calendar.DAY_OF_MONTH);
            if (dateAction.before(dateCourante)) {
                rapport.ajoutRapport(actionDto.getClass().getSimpleName() + ".dateAction", messageSourceUtil
                        .get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DATE_ACTION_INVALIDE), true);
            }
        }

        // On ne peut rendre visible une action dans l'agenda que si elle a une date de début, une durée
        if (actionDto.getVisibleAgenda() != null && actionDto.getVisibleAgenda().booleanValue()
            && (actionDto.getDateAction() == null || actionDto.getIdDuree() == null)) {
            rapport.ajoutRapport(actionDto.getClass().getSimpleName() + ".isVisibleAgenda", messageSourceUtil
                    .get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IMPOSSIBLE_VISIBLE_AGENDA), true);
        }
    }

    /**
     * Méthode privée qui créer un objet action à partir d'un objet actionDto.
     * @param actionDto l'action dto Source
     * @param action l'action resultante
     */
    private void mapperAction(ActionCreationDto actionCreationDto, Action action, ActionNature natureAction, ActionType typeAction, ActionObjet objetAction,
        ActionSousObjet sousObjetAction, ActionPriorite prioriteAction, Campagne campagne, ActionDuree dureeAction) {
        action.setDate(actionCreationDto.getDateAction());
        // Nature Action
        if (natureAction != null) {
            action.setNature(natureAction);
        }
        // Type
        if (typeAction != null) {
            action.setType(typeAction);
        }
        // Objet
        if (objetAction != null) {
            action.setObjet(objetAction);
        }
        // Sous objet
        if (sousObjetAction != null) {
            action.setSousObjet(sousObjetAction);
        }
        // Priorité
        if (prioriteAction != null) {
            action.setPriorite(prioriteAction);
        } else {
            action.setPriorite(actionPrioriteDao.rechercherPrioriteActionParId(squareMappingService.getIdActionPrioriteParDefaut()));
        }
        // Campagne
        if (campagne != null) {
            action.setCampagne(campagne);
        }
        // Durée
        if (dureeAction != null) {
            action.setDuree(dureeAction);
        }
        // Enregistrement de la date de notification
        if (actionCreationDto.getRappel() != null && actionCreationDto.getRappel()) {
            if (actionCreationDto.getIdNotificationList() != null) {
                final ActionNotificationInfosDto notification = squareMappingService.getActionNotificationParId(actionCreationDto.getIdNotificationList());
                final Calendar soustraction = Calendar.getInstance();
                soustraction.clear();
                final Long dateTime = -notification.getNotification().getTimeInMillis() + actionCreationDto.getDateAction().getTimeInMillis();
                soustraction.setTimeInMillis(dateTime);
                action.setDateNotification(soustraction);
            }
        } else {
            action.setDateNotification(null);
        }
        // Réclamation
        if (actionCreationDto.getReclamation() != null) {
            action.setReclamation(actionCreationDto.getReclamation());
        } else {
            action.setReclamation(false);
        }
        // Mail notification
        if (actionCreationDto.getMePrevenirParMail() != null) {
            action.setMailNotification(actionCreationDto.getMePrevenirParMail());
        } else {
            action.setMailNotification(false);
        }
        // Date d'affichage
        if (actionCreationDto.getDateAffichageAction() != null) {
            action.setDateAffichage(actionCreationDto.getDateAffichageAction());
        }
        // Date de création
        action.setDateCreation(Calendar.getInstance());
    }

    @Override
    public ActionResultatDto modifierAction(ActionModificationDto actionModificationDto) {
        // Dto null
        if (actionModificationDto == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DTO_NULL));
        }

        // Identifiant de l'action null
        if (actionModificationDto.getIdAction() == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_NULL));
        }

        // Récupération de l'action correspondant
        final Action actionAModifier = actionDao.rechercherActionParId(actionModificationDto.getIdAction());
        if (actionAModifier == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_INEXISTANTE));
        }
        if (logger.isDebugEnabled()) {
            logger.debug("bug 0008864 : modifierAction(ActionModificationDto)");
            logger.debug("recuperation de l'action : " + actionModificationDto.getIdAction());
            logger.debug("dateCreation = " + (actionAModifier.getDateCreation() != null ? SDF.format(actionAModifier.getDateCreation().getTime()) : "null"));
        }

        Agence agence = null;
        if (actionModificationDto.getAgence() != null && actionModificationDto.getAgence().getIdentifiant() != null) {
            agence = agenceDao.rechercheAgenceParId(actionModificationDto.getAgence().getIdentifiant());
            if (agence == null) {
                logger.error("L'agence id = " + actionModificationDto.getAgence().getIdentifiant() + "n'existe pas.");
                throw new TechnicalException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_AGENCE_INEXISTANTE));
            }
        }

        // Récupération de la ressource correspondant au commercial (on permet désormais le changement de ressource même si existant)
        Ressource commercial = null;
        if (actionAModifier.getActionAttribution() != null && actionModificationDto.getRessource() != null
            && actionModificationDto.getRessource().getIdentifiant() != null) {
            commercial = ressourceDao.rechercherRessourceParId(actionModificationDto.getRessource().getIdentifiant());
            if (commercial == null) {
                logger.error("La ressource " + actionModificationDto.getRessource().getIdentifiant() + " n'existe pas");
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_COMMERCIAL_INEXISTANTE));
            }
        }

        // Récupération de la durée
        ActionDuree dureeAction = null;
        if (actionModificationDto.getIdDuree() != null) {
            dureeAction = actionDureeDao.rechercherDureeActionParId(actionModificationDto.getIdDuree());
            if (dureeAction == null) {
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DUREE_INEXISTANTE));
            }
        }

        final RapportDto rapport = new RapportDto();

        // Vérification de la nature
        if (actionModificationDto.getNatureAction() == null || actionModificationDto.getNatureAction().getIdentifiant() == null) {
            rapport.ajoutRapport(ActionModificationDto.class.getSimpleName() + ".nature", messageSourceUtil
                    .get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_ACTION_NULL), true);
        }

        // Pour une action de nature "téléphone sortant" passant au statut "terminé"
        if (actionModificationDto.getNatureAction() != null
            && squareMappingService.getIdNatureActionTelephoneSortant().equals(actionModificationDto.getNatureAction().getIdentifiant())
            && actionModificationDto.getStatut() != null
            && squareMappingService.getIdStatutActionTermine().equals(actionModificationDto.getStatut().getIdentifiant())) {
            // On vérifie que le résultat de la nature est renseigné
            if (actionModificationDto.getNatureResultat() == null || actionModificationDto.getNatureResultat().getIdentifiant() == null) {
                rapport.ajoutRapport(ActionModificationDto.class.getSimpleName() + ".natureResultat", messageSourceUtil
                        .get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_RESULTAT_ACTION_NULL), true);
            }
        }

        // vérification que la nature de resultat est selectionnée pour la nature d'action visite sortante si on demande la vérification
        if (!BooleanUtils.isFalse(actionModificationDto.getVerifierResultatNatureAction()) && actionModificationDto.getNatureAction() != null
            && squareMappingService.getIdNatureActionVisiteSortante().equals(actionModificationDto.getNatureAction().getIdentifiant())
            && actionModificationDto.getNatureResultat() == null
            && squareMappingService.getIdStatutActionTermine().equals(actionModificationDto.getStatut().getIdentifiant())) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_RESULTAT_VISITE_SORTANTE));
        }

        // Contrôle du statut
        final ValidationExpressionProp[] propsAction =
            new ValidationExpressionProp[] {
                new ValidationExpressionProp("statut", null, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_STATUT_NULL)),
                new ValidationExpressionProp("dateAction", null, messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DATE_ACTION_NULL))};

        validationExpressionUtil.verifierSiVide(rapport, actionModificationDto, propsAction);

        if (actionModificationDto.getDateAction() != null) {
            // On copie la date de l'action enregistrée en base
            final Calendar ancienneDateAction = DateUtils.truncate((Calendar) actionAModifier.getDate().clone(), Calendar.HOUR);
            // On copie la nouvelle date d'action
            final Calendar nouvelleDateAction = DateUtils.truncate((Calendar) actionModificationDto.getDateAction().clone(), Calendar.HOUR);
            // Si la date de l'action est modifiée
            if (!ancienneDateAction.equals(nouvelleDateAction)) {
                // On vérifie que la date d'action est postérieure à la date courante
                if (DateUtils.truncate(nouvelleDateAction, Calendar.DAY_OF_MONTH).before(DateUtils.truncate(Calendar.getInstance(), Calendar.DAY_OF_MONTH))) {
                    rapport.ajoutRapport(actionModificationDto.getClass().getSimpleName() + ".dateAction", messageSourceUtil
                            .get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DATE_ACTION_INVALIDE), true);
                }
            }
        }

        // Une action ne peut être terminée sans être attribuée
        if (actionModificationDto.getStatut() != null
            && squareMappingService.getIdStatutActionTermine().equals(actionModificationDto.getStatut().getIdentifiant())
            && (actionModificationDto.getRessource() == null || actionModificationDto.getRessource().getIdentifiant() == null)) {
            rapport.ajoutRapport(ActionModificationDto.class.getSimpleName() + ".statut", messageSourceUtil
                    .get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TERMINEE_SANS_ATTRIBUTION), true);
        }

        // On ne peut rendre visible une action dans l'agenda que si elle a une date de début, une durée
        if (actionModificationDto.getVisibleAgenda() != null && actionModificationDto.getVisibleAgenda().booleanValue()
            && (actionModificationDto.getDateAction() == null || actionModificationDto.getIdDuree() == null)) {
            rapport.ajoutRapport(actionModificationDto.getClass().getSimpleName() + ".isVisibleAgenda", messageSourceUtil
                    .get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IMPOSSIBLE_VISIBLE_AGENDA), true);
        }

        // Présence d'erreurs empêchant la modification de l'action
        if (rapport.getEnErreur()) {
            // On retourne le rapport d'erreur
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }

        // Vérification de l'existance des nouveaux champs
        verificationExistanceModification(actionModificationDto, actionAModifier);

        // Vérification de la règle de gestion de l'opportunité en cours
        if (Boolean.TRUE.equals(actionModificationDto.getVerifierRegleGestionOpportuniteEnCours())) {
            verifierRegleGestionOpportuniteOuverte(actionModificationDto, actionAModifier);
        }

        // Modification de l'action
        if (actionModificationDto.getNatureAction() != null && actionModificationDto.getNatureAction().getIdentifiant() != null) {
            final ActionNature nature = actionNatureDao.rechercherNatureActionParId(actionModificationDto.getNatureAction().getIdentifiant());
            if (nature == null) {
                throw new TechnicalException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_ACTION_INEXISTANT));
            }
            actionAModifier.setNature(nature);
        }
        // modification du type d'action
        final IdentifiantLibelleDto typeAction = actionModificationDto.getTypeAction();
        if (actionModificationDto.getTypeAction() != null && typeAction.getIdentifiant() != null) {
            actionAModifier.setType(actionTypeDao.rechercherTypeActionParId(typeAction.getIdentifiant()));
        }

        // modificatio de l'objet d'action
        final IdentifiantLibelleDto objetAction = actionModificationDto.getObjetAction();
        if (actionModificationDto.getObjetAction() != null && objetAction.getIdentifiant() != null) {
            actionAModifier.setObjet(actionObjetDao.rechercherObjetActionParId(objetAction.getIdentifiant()));
        }
        // affectation au pot commun
        if (actionModificationDto.getAffectationPotCommun() != null && actionModificationDto.getAffectationPotCommun()) {
            actionAModifier.getActionAttribution().setAgence(agenceDao.rechercheAgenceParId(squareMappingService.getIdentifiantAgenceFrance()));
            actionAModifier.getActionAttribution().setRessource(null);
        }
        if (actionModificationDto.getRappelMail() != null) {
            actionAModifier.setMailNotification(actionModificationDto.getRappelMail());
        }
        // action téléphone sortant ou visite sortante
        if (actionModificationDto.getNatureAction() != null
            && (squareMappingService.getIdNatureActionTelephoneSortant().equals(actionModificationDto.getNatureAction().getIdentifiant()) || squareMappingService
                    .getIdNatureActionVisiteSortante().equals(actionModificationDto.getNatureAction().getIdentifiant()))) {
            if (actionModificationDto.getNatureResultat() != null && actionModificationDto.getNatureResultat().getIdentifiant() != null) {
                actionAModifier.setNatureResultat(actionNatureResultatDao.rechercherNatureResultatActionById(actionModificationDto.getNatureResultat()
                        .getIdentifiant()));
            } else {
                actionAModifier.setNatureResultat(null);
            }
        }

        if (agence != null) {
            // si on affecte une action de relance (qui n'avait pas d'agence) à une agence, on affecte aussi l'opp à l'agence
            if (actionAModifier.getActionAttribution().getAgence() == null
                && actionAModifier.getType().getId().equals(squareMappingService.getIdTypeActionRelance())
                && actionAModifier.getActionAffectation().getOpportunite() != null) {
                actionAModifier.getActionAffectation().getOpportunite().getOpportuniteAttribution().setAgence(agence);
            }
            actionAModifier.getActionAttribution().setAgence(agence);
        }

        if (commercial != null) {
            // si on affecte une action de relance (qui n'avait pas de ressource) à une ressource, on affecte aussi l'opp à la ressource
            if (actionAModifier.getActionAttribution().getRessource() == null
                && actionAModifier.getType().getId().equals(squareMappingService.getIdTypeActionRelance())
                && actionAModifier.getActionAffectation().getOpportunite() != null) {
                actionAModifier.getActionAffectation().getOpportunite().getOpportuniteAttribution().setRessource(commercial);
            }
            actionAModifier.getActionAttribution().setRessource(commercial);
        }

        final Long idStatutTermine = squareMappingService.getIdStatutTerminer();

        // Résultat
        // Création d'une opportunité si le résultat passe à opportunité
        OpportuniteDto opportuniteCree = null;
        if (actionAModifier.getResultat() == null) {
            if (actionModificationDto.getResultat() != null
                && actionModificationDto.getResultat().getIdentifiant().equals(squareMappingService.getIdResultatOpportunite())) {
                actionAModifier.setStatut(actionStatutDao.rechercherStatutActionParId(idStatutTermine));
                opportuniteCree = creationOpportunite(actionAModifier);
            } else if (actionModificationDto.getResultat() != null
                && actionModificationDto.getResultat().getIdentifiant().equals(squareMappingService.getIdResultatRelance())) {
                actionAModifier.setStatut(actionStatutDao.rechercherStatutActionParId(idStatutTermine));
            } else {
                actionAModifier.setStatut(actionStatutDao.rechercherStatutActionParId(actionModificationDto.getStatut().getIdentifiant()));
            }
            if (actionModificationDto.getResultat() != null) {
                actionAModifier.setResultat(actionResultatDao.rechercherActionResultatParId(actionModificationDto.getResultat().getIdentifiant()));
            }
        } else {
            if (actionModificationDto.getResultat() != null
                && actionModificationDto.getResultat().getIdentifiant().equals(squareMappingService.getIdResultatOpportunite())) {
                if (!actionAModifier.getResultat().getId().equals(squareMappingService.getIdResultatOpportunite())) {
                    actionAModifier.setStatut(actionStatutDao.rechercherStatutActionParId(idStatutTermine));
                    opportuniteCree = creationOpportunite(actionAModifier);
                }
            } else if (actionModificationDto.getResultat() != null
                && actionModificationDto.getResultat().getIdentifiant().equals(squareMappingService.getIdResultatRelance())) {
                actionAModifier.setStatut(actionStatutDao.rechercherStatutActionParId(idStatutTermine));
            } else {
                actionAModifier.setStatut(actionStatutDao.rechercherStatutActionParId(actionModificationDto.getStatut().getIdentifiant()));
            }
            if (actionModificationDto.getResultat() != null) {
                actionAModifier.setResultat(actionResultatDao.rechercherActionResultatParId(actionModificationDto.getResultat().getIdentifiant()));
            }
        }

        // Mise à jour de la date de l'action
        if (idStatutTermine.equals(actionModificationDto.getStatut().getIdentifiant())) {
            // Si l'action passe au statut "terminé", on met à jour la date terminée de l'action en prenant la date courante
            actionAModifier.setDateTerminee(Calendar.getInstance());
        } else {
            actionAModifier.setDate(actionModificationDto.getDateAction());
        }

        // Priorité
        if (actionModificationDto.getPriorite() != null && actionModificationDto.getPriorite().getIdentifiant() != null) {
            // Récupération de la priorité
            final ActionPriorite priorite = actionPrioriteDao.rechercherPrioriteActionParId(actionModificationDto.getPriorite().getIdentifiant());
            if (priorite != null) {
                actionAModifier.setPriorite(priorite);
            }
        }

        // Durée de l'action
        if (dureeAction != null) {
            actionAModifier.setDuree(dureeAction);
        }

        // Action visible ou non dans l'agenda
        actionAModifier.setVisibleAgenda(actionModificationDto.getVisibleAgenda() != null && actionModificationDto.getVisibleAgenda().booleanValue());

        // Construction de la date de notification
        if (actionModificationDto.getRappel() != null && actionModificationDto.getRappel()) {
            if (actionModificationDto.getIdNotification() != null) {
                final ActionNotificationInfosDto notification = squareMappingService.getActionNotificationParId(actionModificationDto.getIdNotification());
                final Calendar soustraction = Calendar.getInstance();
                soustraction.clear();
                final Long dateTime = -notification.getNotification().getTimeInMillis() + actionAModifier.getDate().getTimeInMillis();
                soustraction.setTimeInMillis(dateTime);
                actionAModifier.setDateNotification(soustraction);
            } else {
                actionAModifier.setDateNotification(null);
            }
        } else {
            actionAModifier.setDateNotification(null);
        }
        // Constuction du commentaire
        if (actionModificationDto.getCommentaire() != null) {
            final Commentaire commentaire = mapperDozerBean.map(actionModificationDto.getCommentaire(), Commentaire.class);
            if (actionModificationDto.getCommentaire().getRessource() != null) {
                final Ressource ressource = ressourceDao.rechercherRessourceParId(actionModificationDto.getCommentaire().getRessource().getId());
                commentaire.setRessource(ressource);
            }
            if (actionAModifier.getCommentaires() == null) {
                final List<Commentaire> list = new ArrayList<Commentaire>();
                list.add(commentaire);
                actionAModifier.setCommentaires(list);
            } else {
                actionAModifier.getCommentaires().add(commentaire);
            }
        }

        // Envoi d'un email si demandé
        if (actionAModifier.isMailNotification() && squareMappingService.getIdStatutActionTermine().equals(actionAModifier.getStatut().getId())) {
            if (actionAModifier.getRessource() != null) {
                final Ressource createur = actionAModifier.getRessource();
                if (createur.getEmail() == null || "".equals(createur.getEmail())) {
                    throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_EMAIL_CREATEUR_ACTION_VIDE));
                }
                final List<String> listeDestinataires = new ArrayList<String>();
                listeDestinataires.add(createur.getEmail());
                final String titre = messageSourceUtil.get(ActionKeyUtil.TITRE_EMAIL_FIN_ACTION);
                String personneAction = "";
                String type = "";
                String objet = "";
                String sousObjet = "";
                String nature = "";
                String campagne = "";
                if (actionAModifier.getActionAffectation() != null && actionAModifier.getActionAffectation().getPersonne() != null) {
                    final Personne personne = actionAModifier.getActionAffectation().getPersonne();
                    if (personne instanceof PersonnePhysique) {
                        final PersonnePhysique personnePhysique = (PersonnePhysique) actionAModifier.getActionAffectation().getPersonne();
                        personneAction = personnePhysique.getNom() + ESPACE + personnePhysique.getPrenom();
                    } else if (personne instanceof PersonneMorale) {
                        final PersonneMorale personneMorale = (PersonneMorale) actionAModifier.getActionAffectation().getPersonne();
                        personneAction = personneMorale.getRaisonSociale();
                    }
                }
                if (actionAModifier.getType() != null) {
                    type = actionAModifier.getType().getLibelle();
                }
                if (actionAModifier.getObjet() != null) {
                    objet = actionAModifier.getObjet().getLibelle();
                }
                if (actionAModifier.getSousObjet() != null) {
                    sousObjet = actionAModifier.getSousObjet().getLibelle();
                }
                if (actionAModifier.getNature() != null) {
                    nature = actionAModifier.getNature().getLibelle();
                }
                if (actionAModifier.getCampagne() != null) {
                    campagne = actionAModifier.getCampagne().getLibelle();
                }
                final String message =
                    messageSourceUtil.get(ActionKeyUtil.MESSAGE_EMAIL_FIN_ACTION, new String[] {personneAction, type, objet, sousObjet, nature, campagne});
                final MailDto mailDto = new MailDto(expediteurNoReply, listeDestinataires, titre, message, null, false);
                emailSquarePlugin.envoyerEmail(mailDto);
            }
        }
        // Renseignement de la date de modification
        actionAModifier.setDateModification(Calendar.getInstance());

        final ActionResultatDto resultat = new ActionResultatDto();
        if (opportuniteCree != null) {
            resultat.setIdOpportunite(opportuniteCree.getIdOpportunite());
        }
        return resultat;
    }

    @Override
    public ActionDto modifierAction(ActionDto actionDto) {
        if (actionDto == null || actionDto.getIdentifiant() == null) {
            return null;
        }
        final Action actionAModifier = actionDao.rechercherActionParId(actionDto.getIdentifiant());

        // La date de création ne devrait pas pouvoir être modifié, on la sauvegarde pour la remettre après le mapping
        final Calendar dateCreation = actionAModifier.getDateCreation();
        mapperDozerBean.map(actionDto, actionAModifier);
        actionAModifier.setDateCreation(dateCreation);

        if (actionDto.getStatut() != null) {
            actionAModifier.setStatut(actionStatutDao.rechercherStatutActionParId(actionDto.getStatut().getIdentifiant()));
            if (actionDto.getStatut().getIdentifiant().equals(squareMappingService.getIdStatutActionTermine())) {
                actionAModifier.setDateTerminee(Calendar.getInstance());
            }
        }
        if (actionDto.getResultat() != null) {
            actionAModifier.setResultat(actionResultatDao.rechercherActionResultatParId(actionDto.getResultat().getIdentifiant()));
        }
        if (actionDto.getNatureAction() != null) {
            actionAModifier.setNature(actionNatureDao.rechercherNatureActionParId(actionDto.getNatureAction().getIdentifiant()));
        }
        if (actionDto.getType() != null) {
            actionAModifier.setType(actionTypeDao.rechercherTypeActionParId(actionDto.getType().getIdentifiant()));
        }

        if (actionDto.getObjet() != null) {
            actionAModifier.setObjet(actionObjetDao.rechercherObjetActionParId(actionDto.getObjet().getIdentifiant()));
        }
        if (actionDto.getSousObjet() != null) {
            actionAModifier.setSousObjet(actionSousObjetDao.rechercherSousObjetActionParId(actionDto.getSousObjet().getIdentifiant()));
        }
        if (actionDto.getPriorite() != null) {
            actionAModifier.setPriorite(actionPrioriteDao.rechercherPrioriteActionParId(actionDto.getPriorite().getIdentifiant()));
        }
        if (actionDto.getCampagne() != null) {
            actionAModifier.setCampagne(campagneDao.rechercherCampagneParId(actionDto.getCampagne().getIdentifiant()));
        }
        if (actionDto.getNatureResultat() != null && actionDto.getNatureResultat().getIdentifiant() != null) {
            actionAModifier.setNatureResultat(actionNatureResultatDao.rechercherNatureResultatActionById(actionDto.getNatureResultat().getIdentifiant()));
        }
        if (actionDto.getAgence() != null) {
            final ActionAttribution actionAttribution =
                actionAModifier.getActionAttribution() == null ? new ActionAttribution() : actionAModifier.getActionAttribution();
            final Agence agence = agenceDao.rechercheAgenceParId(actionDto.getAgence().getIdentifiant());
            Ressource ressource = null;
            if (actionDto.getRessource() != null) {
                ressource = ressourceDao.rechercherRessourceParId(actionDto.getRessource().getIdentifiant());
            }
            actionAttribution.setRessource(ressource);
            actionAttribution.setAgence(agence);
            actionAModifier.setActionAttribution(actionAttribution);
        }
        if (actionDto.getIdOpportunite() != null || actionDto.getIdPersonne() != null) {
            final Personne personne = actionDto.getIdPersonne() == null ? null : personneDao.rechercherPersonneParId(actionDto.getIdPersonne());
            final Opportunite opportunite =
                actionDto.getIdOpportunite() == null ? null : opportuniteDao.rechercherOpportuniteParId(actionDto.getIdOpportunite());
            final ActionAffectation actionAffectation =
                actionAModifier.getActionAffectation() == null ? new ActionAffectation() : actionAModifier.getActionAffectation();
            actionAffectation.setPersonne(personne);
            actionAffectation.setOpportunite(opportunite);
            actionAffectation.setDateCreation(Calendar.getInstance());
            actionAffectation.setSupprime(false);
            actionAffectationDao.creerActionAffectation(actionAffectation);
            actionAModifier.setActionAffectation(actionAffectation);
        }
        if (actionDto.getIdActionSource() != null) {
            final Action actionSource = actionDao.rechercherActionParId(actionDto.getIdActionSource());
            actionAModifier.setActionSource(actionSource);
        }
        if (actionDto.getDuree() != null && actionDto.getDuree().getIdentifiant() != null) {
            actionAModifier.setDuree(actionDureeDao.rechercherDureeActionParId(actionDto.getDuree().getIdentifiant()));
        }
        // Renseignement de la date de modification
        if (actionAModifier != null) {
            actionAModifier.setDateModification(Calendar.getInstance());
        }
        return rechercherActionParIdentifiant(actionDto.getIdentifiant());
    }

    /**
     * Méthode privée pour la création d'opportunité.
     * @param actionAModifier les données de l'action pour créer l'opportunite
     */
    private OpportuniteDto creationOpportunite(Action actionAModifier) {
        final OpportuniteDto opportuniteDto = new OpportuniteDto();
        opportuniteDto.setIdActionSource(actionAModifier.getId());
        if (actionAModifier.getActionAffectation() != null && actionAModifier.getActionAffectation().getPersonne() != null) {
            opportuniteDto.setIdPersonnePhysique(actionAModifier.getActionAffectation().getPersonne().getId());
        }
        if (actionAModifier.getNature() != null) {
            opportuniteDto.setIdNature(actionAModifier.getNature().getId());
        }
        if (actionAModifier.getCampagne() != null) {
            opportuniteDto.setIdCampagne(actionAModifier.getCampagne().getId());
        }
        if (actionAModifier.getActionAttribution() != null && actionAModifier.getActionAttribution().getAgence() != null) {
            opportuniteDto.setIdAgence(actionAModifier.getActionAttribution().getAgence().getId());
        }
        if (actionAModifier.getActionAttribution() != null && actionAModifier.getActionAttribution().getRessource() != null) {
            opportuniteDto.setIdRessource(actionAModifier.getActionAttribution().getRessource().getId());
        }
        if (actionAModifier.getType() != null) {
            opportuniteDto.setIdType(actionAModifier.getType().getId());
        }
        if (actionAModifier.getObjet() != null) {
            opportuniteDto.setIdObjet(actionAModifier.getObjet().getId());
        }
        if (actionAModifier.getSousObjet() != null) {
            opportuniteDto.setIdSousObjet(actionAModifier.getSousObjet().getId());
        }
        final OpportuniteDto opportuniteCree = opportuniteService.creerOpportunite(opportuniteDto);
        return opportuniteCree;
    }

    /**
     * Méthode privée pour vérifier l'existance des champs lors de la modification.
     * @param actionModificationDto les nouvelles données de l'action
     * @param actionAModifier l'action à modifier
     */
    private void verificationExistanceModification(ActionModificationDto actionModificationDto, Action actionAModifier) {

        // Statut inexistant
        if (actionStatutDao.rechercherStatutParId(actionModificationDto.getStatut().getIdentifiant()) == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_STATUT_INEXISTANT));
        }

        final IdentifiantLibelleDto natureAction = actionModificationDto.getNatureAction();
        final IdentifiantLibelleDto statutAction = actionModificationDto.getStatut();
        final IdentifiantLibelleDto natureResultat = actionModificationDto.getNatureResultat();
        final IdentifiantLibelleDto typeAction = actionModificationDto.getTypeAction();
        final IdentifiantLibelleDto objetAction = actionModificationDto.getObjetAction();
        // Si l'action passe au statut "terminé" et a pour nature "téléphone sortant"
        if (natureAction != null && squareMappingService.getIdNatureActionTelephoneSortant().equals(natureAction.getIdentifiant()) && statutAction != null
            && squareMappingService.getIdStatutActionTermine().equals(statutAction.getIdentifiant())) {
            // On vérifie que la nature du résultat est renseignée et qu'elle existe bien dans la base de données
            if (natureResultat == null
                || (natureResultat != null && actionNatureResultatDao.rechercherNatureResultatActionById(natureResultat.getIdentifiant()) == null)) {
                throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_NATURE_RESULTAT_INEXISTANT));
            }
        }

        // Résultat inexistant
        if (actionModificationDto.getResultat() != null
            && actionResultatDao.rechercherActionResultatParId(actionModificationDto.getResultat().getIdentifiant()) == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_RESULTAT_INEXISTANT));
        }

        // Identifiant de notification inexistant
        if (actionModificationDto.getIdNotification() != null
            && squareMappingService.getActionNotificationParId(actionModificationDto.getIdNotification()) == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDNOTIFICATION_INEXISTANT));
        }

        // type d'action innexistant
        if (actionModificationDto.getTypeAction() != null && typeAction.getIdentifiant() != null
            && actionTypeDao.rechercherTypeActionParId(typeAction.getIdentifiant()) == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TYPE_ACTION_INEXISTANT));
        }

        // objet d'action innexistant
        if (actionModificationDto.getObjetAction() != null && objetAction.getIdentifiant() != null
            && actionObjetDao.rechercherObjetActionParId(objetAction.getIdentifiant()) == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_OBJET_INEXISTANT));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void supprimerAction(Long idAction) {
        logger.info("Suppression de l'action (id = " + idAction + ") ...");
        if (idAction == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_SUPPRIMER_ACTION_PARAM_ID_ACTION_OBLIGATOIRE));
        }
        // On récupère l'action à supprimer
        final Action actionASupprimer = actionDao.rechercherActionParId(idAction);
        // On vérifie que l'action à supprimer existe bien
        if (actionASupprimer == null) {
            throw new TechnicalException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_SUPPRIMER_ACTION_ACTION_INEXISTANTE, new String[] {idAction
                    .toString()}));
        }
        // On supprime l'action
        supprimerAction(actionASupprimer, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void supprimerActionsPersonne(Long idPersonne) {
        if (idPersonne == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDPERSONNE_INEXISTANT));
        }
        // On récupère les actions à supprimer
        final CritereActionSyntheseDto critereActionSyntheseDto = new CritereActionSyntheseDto();
        critereActionSyntheseDto.setIdPersonne(idPersonne);
        final List<Action> actions = actionDao.rechercherActionsSources(critereActionSyntheseDto);
        for (Action actionASupprimer : actions) {
            // On supprime l'action
            supprimerAction(actionASupprimer, true);
        }
    }

    /**
     * Supprime une action.
     * @param action l'action à supprimer
     * @param logiquement si c'est une suppression logique ou physique
     */
    private void supprimerAction(Action actionASupprimer, boolean suppressionLogique) {
        // On recherche des actions liées à l'action à supprimer
        final List<Action> actionsLiees = actionDao.rechercherActionsLiees(actionASupprimer.getId(), null, false);
        // Si l'action possède des actions liées
        if (actionsLiees != null && !actionsLiees.isEmpty()) {
            // On supprime ses actions liées une à une
            for (Action actionLiee : actionsLiees) {
                supprimerAction(actionLiee, suppressionLogique);
            }
        }
        // On supprime l'action
        if (suppressionLogique) {
            actionDao.supprimerLogiquement(actionASupprimer);
        } else {
            actionDao.supprimerAction(actionASupprimer);
            logger.info("action (id = " + actionASupprimer.getId() + ") supprimée avec succès");
        }
    }

    @Override
    public ActionDto rechercherActionParIdentifiant(Long idAction) {
        if (idAction == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_NULL));
        }

        final Action action = actionDao.rechercherActionParId(idAction);

        if (action == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_INEXISTANT));
        }
        // Récupération des informations
        final ActionDto resultat = mapperDozerBean.map(action, ActionDto.class);
        // Créateur
        if (action.getRessource() != null) {
            resultat.setCreateur((DimensionRessourceDto) mapperDozerBean.map(action.getRessource(), DimensionRessourceDto.class));
        }
        // Notification
        if (action.getDateNotification() != null) {
            resultat.setRappel(true);
            final Long idNotification = squareMappingService.getIdNotification(action.getDate(), action.getDateNotification());
            resultat.setIdNotificationList(idNotification);
        } else {
            resultat.setRappel(false);
            resultat.setIdNotificationList(null);
        }
        // La ressource
        if (action.getActionAttribution().getRessource() != null) {
            resultat.setRessource((DimensionRessourceDto) mapperDozerBean.map(action.getActionAttribution().getRessource(), DimensionRessourceDto.class));
        }
        if (action.getActionAttribution().getAgence() != null) {
            resultat.setAgence(new IdentifiantLibelleDto(action.getActionAttribution().getAgence().getId(), action.getActionAttribution().getAgence()
                    .getLibelle()));
        }
        // Commentaire
        if (action.getCommentaires() != null && !action.getCommentaires().isEmpty()) {
            final List<HistoriqueCommentaireDto> commentaires = mapperDozerBean.mapList(action.getCommentaires(), HistoriqueCommentaireDto.class);
            if (commentaires != null && commentaires.size() > 0) {
                for (HistoriqueCommentaireDto historiqueCommentaireDto : commentaires) {
                    historiqueCommentaireDto.setDateAction(action.getDate());
                    if (action.getObjet() != null) {
                        historiqueCommentaireDto.setObjet(action.getObjet().getLibelle());
                    }
                    if (action.getSousObjet() != null) {
                        historiqueCommentaireDto.setSousObjet(action.getSousObjet().getLibelle());
                    }
                    if (action.getActionAttribution() != null && action.getActionAttribution().getRessource() != null) {
                        final String attribution = genererLibelleRessource(action.getActionAttribution().getRessource());
                        historiqueCommentaireDto.setAttribution(attribution);
                    }
                }
            }

            resultat.setCommentaires(commentaires);
        }
        return resultat;
    }

    @Override
    public ActionDto rechercherActionParIdentifiantExterieur(String idExterieur) {
        if (idExterieur == null) {
            return null;
        }
        final Action action = actionDao.rechercherActionParIdExterieur(Long.parseLong(idExterieur));
        if (action != null) {
            return rechercherActionParIdentifiant(action.getId());
        } else {
            return null;
        }
    }

    @Override
    public List<HistoriqueCommentaireDto> rechercherNotesActions(Long idAction) {
        if (idAction == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_NULL));
        }
        final Action action = actionDao.rechercherActionParId(idAction);
        if (action == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_INEXISTANT));
        }
        // Historique des messages
        final List<HistoriqueCommentaireDto> historique = new ArrayList<HistoriqueCommentaireDto>();
        if (action.getActionSource() != null) {
            rechercherHistoriqueCommentaire(historique, action.getActionSource().getId());
        }

        Collections.sort(historique);
        return historique;
    }

    /**
     * Méthode privée pour rechercher l'historique des commentaires des action.
     * @param historique l'historique
     * @param idAction l'identifiant de l'action
     */
    private void rechercherHistoriqueCommentaire(List<HistoriqueCommentaireDto> historique, Long idAction) {

        // Recherche de l'action
        final Action action = actionDao.rechercherActionParId(idAction);

        historique.add(constructionCommentaire(action));

        if (action.getActionSource() != null) {
            rechercherHistoriqueCommentaire(historique, action.getActionSource().getId());
        }
    }

    @Override
    public List<Stack<ActionSyntheseDto>> recupererActionsSynthese(CritereActionSyntheseDto critere) {
        // Vérifications
        if (critere == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_DTO_NULL));
        }
        if (critere.getIdPersonne() == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDPERSONNE_NULL));
        }
        if (personneDao.rechercherPersonneParId(critere.getIdPersonne()) == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDPERSONNE_INEXISTANT));
        }
        // Vérification sur l'opportunite
        if (critere.getIdOpportunite() != null && opportuniteDao.rechercherOpportuniteParId(critere.getIdOpportunite()) == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_OPPORTUNITE_INEXISTANTE));
        }

        // Récupération des actions sources
        final List<Action> actionsSources = actionDao.rechercherActionsSources(critere);

        // Liste résultat
        final List<Stack<ActionSyntheseDto>> listeActions = new ArrayList<Stack<ActionSyntheseDto>>();

        // Parcours des actions sources pour recherche les actions liées
        for (Action actionSource : actionsSources) {
            // Création d'une nouvelle pile
            final Stack<ActionSyntheseDto> stack = new Stack<ActionSyntheseDto>();
            // Ajout de l'action source dans la pile
            final ActionSyntheseDto actionSyntheseDto = mapperDozerBean.map(actionSource, ActionSyntheseDto.class);
            actionSyntheseDto.setNiveau(0);
            actionSyntheseDto.setAttribueA(genererLibelleAttribueA(actionSource.getActionAttribution()));
            stack.push(actionSyntheseDto);
            // Recherche des actions liées à l'action source
            rechercherActionsLiees(stack, actionSource.getId(), actionSyntheseDto.getNiveau(), critere.getIdOpportunite(), critere.getFiltrerDateCreation());
            // Enregistrement de la pile
            listeActions.add(stack);
        }

        return listeActions;
    }

    /**
     * Méthode privée récursive qui recherche les actions liées.
     * @param idActionSource l'identifiant de l'action source
     * @param niveau le niveau
     * @param filtrerDateEffet bolléen pour indiquer si on filtre ou non sur la date d'effet
     */
    private void rechercherActionsLiees(Stack<ActionSyntheseDto> pileEnCours, Long idActionSource, int niveau, Long idOpportunite, Boolean filtrerDateEffet) {
        final Long idTypeActionRelance = squareMappingService.getIdTypeActionRelance();
        final List<Action> actionsLiees = actionDao.rechercherActionsLiees(idActionSource, idOpportunite, filtrerDateEffet);
        for (Action actionLiee : actionsLiees) {
            // Ajout de l'action source dans la pile
            final ActionSyntheseDto actionSyntheseDto = mapperDozerBean.map(actionLiee, ActionSyntheseDto.class);
            // On incrémente le niveau des actions de type Relance
            int niveauSuivant = 0;
            if (actionLiee.getType() != null && idTypeActionRelance.equals(actionLiee.getType().getId())) {
                niveauSuivant = niveau + 1;
            }
            actionSyntheseDto.setNiveau(niveauSuivant);
            actionSyntheseDto.setAttribueA(genererLibelleAttribueA(actionLiee.getActionAttribution()));
            // Récupération si la date d'action est éditable
            if (actionEditable(actionLiee)) {
                actionSyntheseDto.setDateActionEditable(true);
            }
            pileEnCours.push(actionSyntheseDto);
            rechercherActionsLiees(pileEnCours, actionLiee.getId(), niveauSuivant, idOpportunite, filtrerDateEffet);
        }
    }

    /**
     * Génère un libellé pour la resource passée en paramètre.
     * @param resource la resource pour laquelle on souhaite générer un libellé
     * @return le libellé généré
     */
    private String genererLibelleRessource(Ressource resource) {
        if (resource != null) {
            return resource.getNom() + ESPACE + resource.getPrenom();
        } else {
            return "";
        }
    }

    /**
     * Génère le libellé attribué à à partir des infos d'attribution de l'action.
     * @param actionAttribution attribution de l'action
     * @return libellé généré
     */
    private String genererLibelleAttribueA(ActionAttribution actionAttribution) {
        final StringBuffer libelleAttribueA = new StringBuffer();
        final String attributionRessource = genererLibelleRessource(actionAttribution.getRessource());
        if (!StringUtils.isBlank(attributionRessource)) {
            libelleAttribueA.append(attributionRessource);
            libelleAttribueA.append(", ");
        }
        final String attributionAgence = actionAttribution.getAgence().getLibelle();
        libelleAttribueA.append(attributionAgence);
        return libelleAttribueA.toString();
    }

    /**
     * Méthode privée permettant de savoir si une action est la dernière action de relance d'une opportunité et que la date d'action est égale à la date de
     * création.
     * @param action l'action à vérifier
     * @return true si l'action correspond false sinon
     */
    private boolean actionEditable(Action action) {
        // Si l'action possède une action source, que son résultat est null et que son statut n'est pas terminé
        // Alors c'est une action de relance
        Boolean editable = false;
        if (action.getActionSource() != null && action.getResultat() == null && !action.getStatut().equals(squareMappingService.getIdStatutTerminer())) {
            // L'action doit comporter une opportunite
            if (action.getActionAffectation() != null && action.getActionAffectation().getOpportunite() != null) {
                // Vérification au niveau des dates
                final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                final String dateAction = sdf.format(action.getDate().getTime());
                final String dateCreation = sdf.format(action.getDateCreation().getTime());

                if (dateAction.equals(dateCreation)) {
                    editable = true;
                }
            }
        }
        return editable;
    }

    /**
     * Méthode privée permettant de constuire un commentaire à partir d'une action.
     * @param action l'action
     * @return l'historique commentaire correspondant
     */
    private HistoriqueCommentaireDto constructionCommentaire(Action action) {
        final HistoriqueCommentaireDto historiqueCommentaireDto = new HistoriqueCommentaireDto();
        historiqueCommentaireDto.setDateAction(action.getDate());
        if (action.getCommentaires() != null && !action.getCommentaires().isEmpty()) {
            String text = "";
            for (Commentaire commentaire : action.getCommentaires()) {
                text += "<br/>";
                if (commentaire.getDescriptif() != null) {
                    if (commentaire.getRessource() != null) {
                        text += commentaire.getRessource().getNom() + " ";
                        text += commentaire.getRessource().getPrenom() + " : ";
                    }
                    // else {
                    // text += "Non attribué : ";
                    // }
                    text += commentaire.getDescriptif();
                }
            }
            historiqueCommentaireDto.setDescriptif(text);
        }
        if (action.getObjet() != null) {
            historiqueCommentaireDto.setObjet(action.getObjet().getLibelle());
        }
        if (action.getSousObjet() != null) {
            historiqueCommentaireDto.setSousObjet(action.getSousObjet().getLibelle());
        }
        if (action.getActionAttribution() != null && action.getActionAttribution().getRessource() != null) {
            final String attribution = genererLibelleRessource(action.getActionAttribution().getRessource());
            historiqueCommentaireDto.setAttribution(attribution);
        }
        return historiqueCommentaireDto;
    }

    @Override
    public void transfererActionsPersonne(Long idPersonneSource, Long idPersonneCible) {
        logger.debug("Transfert des actions de la personne source " + idPersonneSource + " vers la personne cible " + idPersonneCible);
        if (idPersonneSource == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TRANSFERT_PERSONNE_SOURCE_NULL));
        }
        if (idPersonneCible == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TRANSFERT_PERSONNE_CIBLE_NULL));
        }
        // Récupération de la personne cible
        final Personne personneCible = personneDao.rechercherPersonneParId(idPersonneCible);
        if (personneCible == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_TRANSFERT_PERSONNE_CIBLE_INEXISTANTE));
        }
        // Récupération des affectations d'actions de la personne source
        final List<ActionAffectation> listeActionsAffectation = actionAffectationDao.rechercherActionAffectationPersonne(idPersonneSource);
        // Transfert des actions (affectations)
        for (ActionAffectation actionAffectation : listeActionsAffectation) {
            actionAffectation.setPersonne(personneCible);
            actionAffectation.setDateModification(Calendar.getInstance());
        }
    }

    @Override
    public void attacherDocuments(Long idAction, List<DocumentDto> documents) {
        if (idAction == null) {
            throw new TechnicalException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_IDACTION_NULL));
        }
        final Action action = actionDao.rechercherActionParId(idAction);
        if (action == null) {
            throw new TechnicalException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_INEXISTANTE));
        }

        if (action.getDocuments() == null) {
            action.setDocuments(new ArrayList<Document>());
        }
        action.getDocuments().clear();
        final List<Document> nouvelleListe = mapperDozerBean.mapList(documents, Document.class);
        action.getDocuments().addAll(nouvelleListe);
    }

    @Override
    public FichierDto exporterRechercheActionsParCriteres(RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres) {
        final String nomFichier = "exportRechercheActions.xls";
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        final String[] entetes =
            new String[] {"Priorité", "A faire le", "Créée le", "Type", "Objet", "Sous-objet", "Statut", "Num. Client", "Agence / Pôle", "Commerciale",
                "Créateur", "Terminée le"};
        final Integer[] entetesWidth = new Integer[] {5500, 5500, 5500, 5500, 11000, 11000, 5500, 5500, 5500, 5500, 5500, 5500};

        final DocumentXls documentXls = new DocumentXls(nomFichier, entetes, entetesWidth);

        // on recupere le resultat de la recherche par pagination
        int start = 0;
        RemotePagingResultsDto<ActionRechercheDto> resultats;
        do {
            // on redefinit la pagination
            criteres.setFirstResult(start);
            criteres.setMaxResult(paginationExportRecherche);
            resultats = rechercherActionParCritere(criteres);
            start += resultats.getListResults().size();
            // on integre les resultats au fichier
            for (ActionRechercheDto action : resultats.getListResults()) {
                final String[] infosLignes =
                    new String[] {action.getPriorite(), action.getDateAction() != null ? sdf.format(action.getDateAction().getTime()) : "",
                        action.getDateCreation() != null ? sdf.format(action.getDateCreation().getTime()) : "", action.getType(), action.getObjet(),
                        action.getSousObjet(), action.getStatut(), action.getNumeroClient(), action.getAgence() != null ? action.getAgence().getLibelle() : "",
                        action.getCommercial() != null ? action.getCommercial().getNom() + " " + action.getCommercial().getPrenom() : "",
                        action.getCreateur() != null ? action.getCreateur().getNom() + " " + action.getCreateur().getPrenom() : "",
                        action.getDateTerminee() != null ? sdf.format(action.getDateTerminee().getTime()) : ""};
                documentXls.ajouterLigne(infosLignes);
            }
        } while (start < resultats.getTotalResults());

        return documentXls.cloturerDocument();
    }

    /**
     * Vérifie la règle de gestion de l'opportunité en cours pour la modification d'une action. <br/>
     * On ne peut pas passer une action de type relance à "Terminé" si l'opportunité associée est en cours.
     * @param actionModificationDto les nouvelles données de l'action
     * @param actionAModifier l'action à modifier
     */
    private void verifierRegleGestionOpportuniteOuverte(ActionModificationDto actionModificationDto, Action actionAModifier) {
        // Exception si on passe le statut d'une action de type "Relance" à "Terminé" si l'opportunité associée est en cours
        if (actionModificationDto.getStatut() != null && squareMappingService.getIdStatutTerminer().equals(actionModificationDto.getStatut().getIdentifiant())
            && actionAModifier.getType() != null && squareMappingService.getIdTypeActionRelance().equals(actionAModifier.getType().getId())) {
            if (actionAModifier.getActionAffectation() != null && actionAModifier.getActionAffectation().getOpportunite() != null
                && actionAModifier.getActionAffectation().getOpportunite().getStatut() != null) {
                final Long idStatutOpportunite = actionAModifier.getActionAffectation().getOpportunite().getStatut().getId();
                if (squareMappingService.getIdStatutOpportuniteEnAttente().equals(idStatutOpportunite)
                    || squareMappingService.getIdStatutOpportuniteNonRenseigne().equals(idStatutOpportunite)) {
                    throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_RELANCE_TERMINEE_OPPORTUNITE_EN_COURS));
                }
            }
        }
    }

    @Override
    public void transfererActionsParCritere(ActionCritereRechercheDto criteres, Long idAgenceCible, Long idRessourceCible) {
        if (criteres == null) {
            throw new TechnicalException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_RECHERCHE_ACTION_DTO_NULL));
        }
        final RapportDto rapport = new RapportDto();
        if (idAgenceCible == null) {
            rapport.ajoutRapport(ActionRechercheDto.class.getSimpleName() + ".agence.id", messageSourceUtil
                    .get(ActionKeyUtil.MESSAGE_ERREUR_TRANSFERT_ACTION_AGENCE_NULL), true);
        }
        if (rapport.getEnErreur()) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }

        final Agence agence = agenceDao.rechercheAgenceParId(idAgenceCible);
        Ressource ressource = null;
        if (idRessourceCible != null) {
            ressource = ressourceDao.rechercherRessourceParId(idRessourceCible);
        }

        final RemotePagingCriteriasDto<ActionCritereRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteres, 0, Integer.MAX_VALUE);

        // on recupere la liste des actions via les criteres de recherche
        final List<ActionRechercheDto> results = actionDao.rechercheActionParCriteres(criteresDto);
        logger.debug("Nombre total d'actions à transférer : " + results.size());

        // on transfere les actions vers la ressource cible
        for (ActionRechercheDto actionRechercheDto : results) {
            final Action actionAModifier = actionDao.rechercherActionParId(actionRechercheDto.getId());
            actionAModifier.getActionAttribution().setAgence(agence);
            actionAModifier.getActionAttribution().setRessource(ressource);
        }
    }

    @Override
    public void notifier(Long idAction) {
        final Action action = actionDao.rechercherActionParId(idAction);
        action.setNotifier(true);
    }

    /**
     * Modifie validationExpressionUtil.
     * @param validationExpressionUtil la nouvelle valeur de validationExpressionUtil
     */
    public void setValidationExpressionUtil(ValidationExpressionUtil validationExpressionUtil) {
        this.validationExpressionUtil = validationExpressionUtil;
    }

    /**
     * Modifie personneDao.
     * @param personneDao la nouvelle valeur de personneDao
     */
    public void setPersonneDao(PersonneDao personneDao) {
        this.personneDao = personneDao;
    }

    /**
     * Modifie actionNatureDao.
     * @param actionNatureDao la nouvelle valeur de actionNatureDao
     */
    public void setActionNatureDao(ActionNatureDao actionNatureDao) {
        this.actionNatureDao = actionNatureDao;
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
     * Modifie actionPrioriteDao.
     * @param actionPrioriteDao la nouvelle valeur de actionPrioriteDao
     */
    public void setActionPrioriteDao(ActionPrioriteDao actionPrioriteDao) {
        this.actionPrioriteDao = actionPrioriteDao;
    }

    /**
     * Modifie campagneDao.
     * @param campagneDao la nouvelle valeur de campagneDao
     */
    public void setCampagneDao(CampagneDao campagneDao) {
        this.campagneDao = campagneDao;
    }

    /**
     * Modifie squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Modifie actionAffectationDao.
     * @param actionAffectationDao la nouvelle valeur de actionAffectationDao
     */
    public void setActionAffectationDao(ActionAffectationDao actionAffectationDao) {
        this.actionAffectationDao = actionAffectationDao;
    }

    /**
     * Modifie la valeur de ressourceDao.
     * @param ressourceDao the ressourceDao to set
     */
    public void setRessourceDao(RessourceDao ressourceDao) {
        this.ressourceDao = ressourceDao;
    }

    /**
     * Modifie la valeur de agenceDao.
     * @param agenceDao the agenceDao to set
     */
    public void setAgenceDao(AgenceDao agenceDao) {
        this.agenceDao = agenceDao;
    }

    /**
     * Modifie actionStatutDao.
     * @param actionStatutDao la nouvelle valeur de actionStatutDao
     */
    public void setActionStatutDao(ActionStatutDao actionStatutDao) {
        this.actionStatutDao = actionStatutDao;
    }

    /**
     * Modifie actionResultatDao.
     * @param actionResultatDao la nouvelle valeur de actionResultatDao
     */
    public void setActionResultatDao(ActionResultatDao actionResultatDao) {
        this.actionResultatDao = actionResultatDao;
    }

    /**
     * Modifie la valeur de ressourceHabilitationUtil.
     * @param ressourceHabilitationUtil the ressourceHabilitationUtil to set
     */
    public void setRessourceHabilitationUtil(RessourceHabilitationUtil ressourceHabilitationUtil) {
        this.ressourceHabilitationUtil = ressourceHabilitationUtil;
    }

    /**
     * Modifie la valeur de messageSourceUtil.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Modifie la valeur de actionDao.
     * @param actionDao the actionDao to set
     */
    public void setActionDao(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    /**
     * Modifie opportuniteDao.
     * @param opportuniteDao la nouvelle valeur de opportuniteDao
     */
    public void setOpportuniteDao(OpportuniteDao opportuniteDao) {
        this.opportuniteDao = opportuniteDao;
    }

    /**
     * Modifie opportuniteService.
     * @param opportuniteService la nouvelle valeur de opportuniteService
     */
    public void setOpportuniteService(OpportuniteService opportuniteService) {
        this.opportuniteService = opportuniteService;
    }

    /**
     * Définition de actionNatureResultatDao.
     * @param actionNatureResultatDao the actionNatureResultatDao to set
     */
    public void setActionNatureResultatDao(ActionNatureResultatDao actionNatureResultatDao) {
        this.actionNatureResultatDao = actionNatureResultatDao;
    }

    /**
     * Set the paginationExportRecherche value.
     * @param paginationExportRecherche the paginationExportRecherche to set
     */
    public void setPaginationExportRecherche(int paginationExportRecherche) {
        this.paginationExportRecherche = paginationExportRecherche;
    }

    /**
     * Définit la valeur de emailSquarePlugin.
     * @param emailSquarePlugin la nouvelle valeur de emailSquarePlugin
     */
    public void setEmailSquarePlugin(EmailSquarePlugin emailSquarePlugin) {
        this.emailSquarePlugin = emailSquarePlugin;
    }

    /**
     * Définit la valeur de expediteurNoReply.
     * @param expediteurNoReply la nouvelle valeur de expediteurNoReply
     */
    public void setExpediteurNoReply(String expediteurNoReply) {
        this.expediteurNoReply = expediteurNoReply;
    }

    @Override
    public int countActionsParCriteres(ActionCritereRechercheDto criteres) {
        return actionDao.nombreTotalAction(new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteres, 0, 1));
    }

    /**
     * Définit la valeur de actionDureeDao.
     * @param actionDureeDao la nouvelle valeur de actionDureeDao
     */
    public void setActionDureeDao(ActionDureeDao actionDureeDao) {
        this.actionDureeDao = actionDureeDao;
    }
}
