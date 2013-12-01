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
package com.square.core.dao.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.ActionDao;
import com.square.core.model.Action;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.ActionRechercheDto;
import com.square.core.model.dto.CritereActionSyntheseDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.util.CritereRechercheAction;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Implémentation du dao des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionDaoImplementation extends HibernateDaoBaseImplementation implements ActionDao {

    private SquareMappingService squareMappingService;

    private static final String CHAMP_DATE = "date";

    private static final int HOUR_MAX = 23;

    private static final int MINUTE_MAX = 59;

    private static final int SECONDE_MAX = 59;

    @SuppressWarnings("unchecked")
    @Override
    public List<ActionRechercheDto> rechercheActionParCriteres(RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres) {
        final StringBuffer requete = new StringBuffer("select distinct act.id , affectationPersonne.id , affectationPersonne.num,");
        requete.append(" (select personne.nom from PersonnePhysique personne where personne.id = affectationPersonne.id),");
        requete.append(" (select personne.prenom from PersonnePhysique personne where personne.id = affectationPersonne.id),");
        requete.append(" (select personneMorale.raisonSociale from PersonneMorale personneMorale where personneMorale.id = affectationPersonne.id),");
        requete.append(" affectationOpportunite.id, act.date, act.dateCreation, act.dateTerminee, act.type.libelle, act.objet.libelle,");
        requete.append(" ssObjet.libelle, act.priorite.libelle, statutAction.libelle, attributionAgence.id, attributionAgence.libelle,");
        requete.append(" attributionRessource.id, attributionRessource.nom, attributionRessource.prenom,");
        requete.append(" createur.id, createur.nom, createur.prenom,");
        // Ajout colonnes ordre dans la clause select pour permettre le tri dans la clause order by
        requete.append(" act.type.ordre, act.objet.ordre, ssObjet.ordre, act.priorite.ordre, statutAction.ordre");
        // Ajout du tri par l'identifiant unique pour les actions afin d'assurer le tri
        criteres.getListeSorts().add(new RemotePagingSort(squareMappingService.getOrderByActionId(), RemotePagingSort.REMOTE_PAGING_SORT_ASC));
        final Query query = creerQuery(requete.toString(), criteres, true);

        final List<Object[]> resultat = query.list();
        final List<ActionRechercheDto> list = new ArrayList<ActionRechercheDto>();
        for (Object[] row : resultat) {
            final ActionRechercheDto action = new ActionRechercheDto();
            int i = 0;
            action.setId((Long) row[i++]);
            action.setIdpersonne((Long) row[i++]);
            action.setNumeroClient((String) row[i++]);
            action.setNomPersonne((String) row[i++]);
            action.setPrenomPersonne((String) row[i++]);
            action.setRaisonSociale((String) row[i++]);
            action.setIdOpportunite((Long) row[i++]);
            action.setDateAction((Calendar) row[i++]);
            action.setDateCreation((Calendar) row[i++]);
            action.setDateTerminee((Calendar) row[i++]);
            action.setType((String) row[i++]);
            action.setObjet((String) row[i++]);
            final Object objSsObjet = row[i++];
            if (objSsObjet != null) {
                action.setSousObjet((String) objSsObjet);
            }
            action.setPriorite((String) row[i++]);
            action.setStatut((String) row[i++]);
            action.setAgence(new IdentifiantLibelleDto((Long) row[i++], (String) row[i++]));
            final Object objIdCommercial = row[i++];
            final Object objNomCommercial = row[i++];
            final Object objPrenomCommercial = row[i++];
            if (objIdCommercial != null) {
                final DimensionRessourceDto commercial = new DimensionRessourceDto();
                commercial.setIdentifiant((Long) objIdCommercial);
                if (objNomCommercial != null) {
                    commercial.setNom((String) objNomCommercial);
                }
                if (objPrenomCommercial != null) {
                    commercial.setPrenom((String) objPrenomCommercial);
                }
                action.setCommercial(commercial);
            }
            final DimensionRessourceDto createur = new DimensionRessourceDto();
            createur.setIdentifiant((Long) row[i++]);
            createur.setNom((String) row[i++]);
            createur.setPrenom((String) row[i++]);
            action.setCreateur(createur);
            list.add(action);
        }
        return list;
    }

    @Override
    public int nombreTotalAction(RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres) {
        final String select = "select count(distinct act.id)";
        final Query query = creerQuery(select, criteres, false);
        return Integer.valueOf(query.uniqueResult().toString());
    }

    private Query creerQuery(String select, RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres, boolean addOrderBy) {
        final StringBuffer requete = new StringBuffer(select);
        requete.append(" from Action act");
        requete.append(" left join act.sousObjet ssObjet");
        requete.append(" inner join act.statut statutAction");
        requete.append(" inner join act.actionAttribution attribution");
        requete.append(" inner join act.ressource createur");
        requete.append(" left join attribution.ressource attributionRessource");
        requete.append(" left join attribution.agence attributionAgence");
        requete.append(" inner join act.actionAffectation affectation");
        requete.append(" left join affectation.personne affectationPersonne");
        requete.append(" left join affectation.opportunite affectationOpportunite");
        requete.append(" left join act.documents document");
        requete.append(" where 1=1");

        final ActionCritereRechercheDto actionCritereDto = criteres.getCriterias();

        final Map<String, Object> parameters = new HashMap<String, Object>();
        if (actionCritereDto != null) {
            final List<Long> idsAgence = actionCritereDto.getIdAgences();
            final List<Long> idsCommerciaux = actionCritereDto.getIdCommerciaux();
            final boolean hasCritereCommercial = idsCommerciaux != null && !idsCommerciaux.isEmpty();
            final boolean hasCritereAgence = idsAgence != null && !idsAgence.isEmpty();

            if (actionCritereDto.getIdsActions() != null && !actionCritereDto.getIdsActions().isEmpty()) {
                requete.append(" and act.id in (:idsActions)");
                parameters.put("idsActions", actionCritereDto.getIdsActions());
            }
            if (!Boolean.TRUE.equals(actionCritereDto.getDesactiverFiltreAffichage())) {
                requete.append(" and (act.dateAffichage is null or act.dateAffichage <= NOW()) ");
            }
            // Critère sur le type de l'action.
            if (actionCritereDto.getIdType() != null) {
                requete.append(" and act.type.id = :idType");
                parameters.put("idType", actionCritereDto.getIdType());
            }
            // Critère sur la nature de l'action.
            if (actionCritereDto.getListeNatureActions() != null && !actionCritereDto.getListeNatureActions().isEmpty()) {
                requete.append(" and act.nature.id in (:idsNatureAction)");
                parameters.put("idsNatureAction", actionCritereDto.getListeNatureActions());
            }
            // Critère sur la nature du résultat.
            if (actionCritereDto.getListeNatureResultats() != null && !actionCritereDto.getListeNatureResultats().isEmpty()) {
                requete.append(" and act.natureResultat.id in (:idsNatureResultats)");
                parameters.put("idsNatureResultats", actionCritereDto.getListeNatureResultats());
            }
            // Critère sur la priorité de l'action.
            if (actionCritereDto.getListePriorites() != null && !actionCritereDto.getListePriorites().isEmpty()) {
                requete.append(" and act.priorite.id in (:idsPriorites)");
                parameters.put("idsPriorites", actionCritereDto.getListePriorites());
            }
            // Critère sur le résultat de l'action.
            if (actionCritereDto.getListeResultats() != null && !actionCritereDto.getListeResultats().isEmpty()) {
                requete.append(" and act.resultat.id in (:idsResultats)");
                parameters.put("idsResultats", actionCritereDto.getListeResultats());
            }
            // Critère sur le statut de l'action.
            if (actionCritereDto.getListeStatuts() != null && !actionCritereDto.getListeStatuts().isEmpty()) {
                requete.append(" and statutAction.id in (:idsStatuts)");
                parameters.put("idsStatuts", actionCritereDto.getListeStatuts());
            }
            // Critère sur l'objet de l'action.
            if (actionCritereDto.getIdObjet() != null) {
                requete.append(" and act.objet.id = :idObjet");
                parameters.put("idObjet", actionCritereDto.getIdObjet());
            }
            // Critère sur le sous objet de l'action.
            if (actionCritereDto.getIdSousObjet() != null) {
                requete.append(" and ssObjet.id = :idSsObjet");
                parameters.put("idSsObjet", actionCritereDto.getIdSousObjet());
            }
            // Critère sur la campagne.
            if (actionCritereDto.getListeCampagnes() != null && !actionCritereDto.getListeCampagnes().isEmpty()) {
                requete.append(" and act.campagne.id in (:idsCampagnes)");
                parameters.put("idsCampagnes", actionCritereDto.getListeCampagnes());
            }
            // Critère sur le créateur de l'action.
            final List<Long> idsCreateur = actionCritereDto.getIdCreateurs();
            if (idsCreateur != null && !idsCreateur.isEmpty()) {
                requete.append(" and act.ressource.id in (:idsCreateur)");
                parameters.put("idsCreateur", idsCreateur);
            }
            // Critère sur la ressource ou sur l'agence responsable de l'action.
            if (hasCritereCommercial && hasCritereAgence) {
                // Si recherche de type ET ou non renseigné : recherche de type ET
                if (actionCritereDto.getRechercheEtEntreAgencesEtCommerciaux() == null
                    || Boolean.TRUE.equals(actionCritereDto.getRechercheEtEntreAgencesEtCommerciaux())) {
                    requete.append(" and attributionRessource.id in (:idsCommerciaux) and attributionAgence.id in (:idsAgence)");
                }
                else {
                    // Sinon recherche de type OU
                    requete.append(" and (attributionRessource.id in (:idsCommerciaux) or attributionAgence.id in (:idsAgence))");
                }
                parameters.put("idsCommerciaux", idsCommerciaux);
                parameters.put("idsAgence", idsAgence);
            }
            else {
                if (hasCritereCommercial) {
                    requete.append(" and attributionRessource.id in (:idsCommerciaux)");
                    parameters.put("idsCommerciaux", idsCommerciaux);
                }
                if (hasCritereAgence) {
                    requete.append("");
                    // Si recherche de type ET : recherche des actions de l'agence sans affectation à un commercial
                    if (actionCritereDto.getRechercheEtEntreAgencesEtCommerciaux() == null
                        || Boolean.TRUE.equals(actionCritereDto.getRechercheEtEntreAgencesEtCommerciaux())) {
                        requete.append(" and attributionAgence.id in (:idsAgence) and attributionRessource is null");
                    }
                    else {
                        // Sinon recherche de type OU : recherche de l'ensemble des actions de l'agence (avec ou sans affectation à un commercial)
                        requete.append(" and attributionAgence.id in (:idsAgence)");
                    }
                    parameters.put("idsAgence", idsAgence);
                }
            }
            // Identifiants des agences à laquelle l'action est attribuée à exclure de la recherche
            final Set<Long> idsAgencesExclues = actionCritereDto.getIdsAgencesExclues();
            if (idsAgencesExclues != null && !idsAgencesExclues.isEmpty()) {
                requete.append(" and attributionAgence.id not in (:idsAgencesExclues)");
                parameters.put("idsAgencesExclues", idsAgencesExclues);
            }
            // Critère sur la date début de l'action.
            if (actionCritereDto.getDateDebutAction() != null) {
                requete.append(" and act.date >= :dateDebutAction");
                parameters.put("dateDebutAction", actionCritereDto.getDateDebutAction());
            }
            if (actionCritereDto.getDateNotification() != null) {
                requete.append(" and act.dateNotification <= :dateNotification");
                parameters.put("dateNotification", actionCritereDto.getDateNotification());
                requete.append(" and act.notifier = :notifier");
                parameters.put("notifier", false);
                requete.append(" and attributionRessource.id is not null");
            }
            // Critère sur la date fin de l'action.
            if (actionCritereDto.getDateFinAction() != null) {
                requete.append(" and act.date <= :dateFinAction");
                final Calendar dateFin = actionCritereDto.getDateFinAction();
                dateFin.set(Calendar.HOUR_OF_DAY, HOUR_MAX);
                dateFin.set(Calendar.MINUTE, MINUTE_MAX);
                dateFin.set(Calendar.SECOND, SECONDE_MAX);
                parameters.put("dateFinAction", actionCritereDto.getDateFinAction());
            }
            // Critère sur la reclamation de l'action.
            if (actionCritereDto.getReclamation() != null) {
                requete.append(" and act.reclamation = :reclamation");
                parameters.put("reclamation", actionCritereDto.getReclamation());
            }
            if (actionCritereDto.getIdOpportunite() != null) {
                requete.append(" and affectation.opportunite.id = :idOpportunite");
                parameters.put("idOpportunite", actionCritereDto.getIdOpportunite());
            }
            if (actionCritereDto.getIdPersonne() != null) {
                requete.append(" and affectationPersonne.id = :idPersonne");
                parameters.put("idPersonne", actionCritereDto.getIdPersonne());
            }
            if (actionCritereDto.getIdDocuments() != null && !actionCritereDto.getIdDocuments().isEmpty()) {
                requete.append(" and document.identifiantExterieur in (:idDocuments)");
                parameters.put("idDocuments", actionCritereDto.getIdDocuments());
            }
        }

        // on ne remonte que les actions non supprimees
        requete.append(" and act.supprime =:suppr");
        parameters.put("suppr", false);

        Query query;
        if (addOrderBy) {
            query = createQuery(requete.toString(), criteres);
        }
        else {
            query = createQuery(requete.toString());
        }
        query.setProperties(parameters);
        return query;
    }

    @Override
    public void creerAction(Action action) {
        save(action);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Action> rechercherActionsSources(CritereActionSyntheseDto critereActionSyntheseDto) {
        final Criteria criteria = createCriteria(Action.class);

        // Critere de la personne
        criteria.createAlias("actionAffectation", "actionAffectationAlias");
        criteria.add(Restrictions.eq("actionAffectationAlias.personne.id", critereActionSyntheseDto.getIdPersonne()));
        // Critere action source : l'action source doit etre null ou rattachée à une autre personne
        // (pb des actions sur personnes morales dont la source est une action sur personne physique)
        criteria.createAlias("actionSource", "actionSourceAlias", CriteriaSpecification.LEFT_JOIN);
        criteria.createAlias("actionSourceAlias.actionAffectation", "actionSourceAffectationAlias", CriteriaSpecification.LEFT_JOIN);
        final Junction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.isNull("actionSource"));
        disjunction.add(Restrictions.not(Restrictions.eqProperty("actionSourceAffectationAlias.personne.id", "actionAffectationAlias.personne.id")));
        criteria.add(disjunction);
        // Critere sur les opportunités
        if (critereActionSyntheseDto.getIdOpportunite() != null) {
            criteria.add(Restrictions.eq("actionAffectationAlias.opportunite.id", critereActionSyntheseDto.getIdOpportunite()));
        }
        // Critère sur la date d'effet
        if (critereActionSyntheseDto.getFiltrerDateCreation() != null && critereActionSyntheseDto.getFiltrerDateCreation()) {
            criteria.add(Restrictions.le("dateCreation", Calendar.getInstance()));
        }
        criteria.add(Restrictions.eq("supprime", false));
        // Ordre
        criteria.addOrder(Order.desc(CHAMP_DATE));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Action> rechercherActionsLiees(Long idActionSource, Long idOpportunite, Boolean filtrerDateCreation) {
        final Criteria criteria = createCriteria(Action.class);
        criteria.add(Restrictions.eq("actionSource.id", idActionSource));
        if (idOpportunite != null) {
            criteria.createAlias("actionAffectation", "actionAffectationAlias");
            criteria.add(Restrictions.eq("actionAffectationAlias.opportunite.id", idOpportunite));
        }
        if (filtrerDateCreation != null && filtrerDateCreation) {
            criteria.add(Restrictions.le("dateCreation", Calendar.getInstance()));
        }
        criteria.add(Restrictions.eq("supprime", false));
        criteria.addOrder(Order.asc(CHAMP_DATE));
        return criteria.list();
    }

    @Override
    public Action rechercherActionParId(Long idAction) {
        return load(idAction, Action.class);
    }

    @Override
    public Action rechercherActionParIdExterieur(Long idExt) {
        return (Action) createCriteria(Action.class).add(Restrictions.eq("identifiantExterieur", idExt.toString())).add(Restrictions.eq("supprime", false))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Action> rechercherActionsParOpportunite(Long idOpportunite) {
        final Criteria criteria = creerCriteriaActionsParOpportunite(idOpportunite);
        criteria.addOrder(Order.asc(CHAMP_DATE));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Action> rechercherActionsSourcesParOpportunite(Long idOpportunite) {
        final Criteria criteria = creerCriteriaActionsParOpportunite(idOpportunite);
        // Critere action source
        criteria.add(Restrictions.isNull("actionSource"));
        criteria.addOrder(Order.asc(CHAMP_DATE));
        return criteria.list();
    }

    /**
     * Créer le criteria pour la recherche d'action par opportunité.
     * @param idOpportunite l'identifiant de l'opportunité
     * @return le criteria
     */
    private Criteria creerCriteriaActionsParOpportunite(Long idOpportunite) {
        final Criteria criteria = createCriteria(Action.class);
        criteria.createAlias("actionAffectation", "actionAffectationAlias");
        criteria.add(Restrictions.eq("actionAffectationAlias.opportunite.id", idOpportunite));
        criteria.add(Restrictions.eq("supprime", false));
        return criteria;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Action> rechercherActionParCriteresEtRessource(CritereRechercheAction criteres, Ressource ressource) {
        final Map<String, Object> params = new HashMap<String, Object>();
        final StringBuffer hqlQuery = new StringBuffer("select act");
        hqlQuery.append(" from Action act");
        hqlQuery.append(" where 1 = 1");
        if (criteres.getDateMinDateDebut() != null && criteres.getDateMaxDateDebut() != null) {
            final String dateMin = "dateMinDateDebut";
            final String dateMax = "dateMaxDateDebut";
            hqlQuery.append(" and act.date between :" + dateMin + " and :" + dateMax);
            params.put(dateMin, criteres.getDateMinDateDebut());
            params.put(dateMax, criteres.getDateMaxDateDebut());
        }
        if (ressource != null) {
            final String param = "idRessource";
            hqlQuery.append(" and act.actionAttribution.ressource.id = :" + param);
            params.put(param, ressource.getId());
        }
        if (criteres.getIdsStatut() != null && !criteres.getIdsStatut().isEmpty()) {
            final String param = "idsStatuts";
            hqlQuery.append(" and act.statut.id in (:" + param + ")");
            params.put(param, criteres.getIdsStatut());
        }
        if (criteres.isVisibleAgenda()) {
            final String param = "visibleAgenda";
            hqlQuery.append(" and act.visibleAgenda = :" + param);
            params.put(param, criteres.isVisibleAgenda());
        }

        // on ne remonte que les actions non supprimees
        hqlQuery.append(" and act.supprime = :suppr");
        params.put("suppr", false);

        hqlQuery.append(" and hour(act.date) > 0");
        final Query query = createQuery(hqlQuery.toString());
        if (!params.isEmpty()) {
            query.setProperties(params);
        }
        return query.list();
    }

    /**
     * Setter function.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void supprimerAction(Action action) {
        delete(action);
    }

    @Override
    public void supprimerLogiquement(Action action) {
        action.setDateSuppression(Calendar.getInstance());
        action.setSupprime(true);
        update(action);
    }

}
