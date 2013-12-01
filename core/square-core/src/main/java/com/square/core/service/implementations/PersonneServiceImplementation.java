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

import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_DATE_DEBUT;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_INSERTION_ADRESSE_MAUVAIS_NOMVOIE;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_ADRESSE_TROP_LONGUE;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_BAD_EMAIL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_BAD_NUMTELEPHONE;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_FORMAT_NUM_VOIE_INCORRECT;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_ADRESSE_CODEPOSTAL_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_ADRESSE_PAYS_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_EMAIL_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_INDICATIFTEL_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NATURETELEPHONE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NUMTELEPHONE_NULL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.AdherentService;
import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.core.dao.interfaces.AdresseDao;
import com.square.core.dao.interfaces.AdresseNatureDao;
import com.square.core.dao.interfaces.AgenceDao;
import com.square.core.dao.interfaces.CodePostalCommuneDao;
import com.square.core.dao.interfaces.EmailDao;
import com.square.core.dao.interfaces.EmailNatureDao;
import com.square.core.dao.interfaces.NatureTelephoneDao;
import com.square.core.dao.interfaces.PaysDao;
import com.square.core.dao.interfaces.PersonneDao;
import com.square.core.dao.interfaces.PersonnePhysiqueDao;
import com.square.core.dao.interfaces.PersonnePhysiqueNatureDao;
import com.square.core.dao.interfaces.RelationDao;
import com.square.core.dao.interfaces.RelationTypeDao;
import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.dao.interfaces.TelephoneDao;
import com.square.core.dao.interfaces.TypeVoieDao;
import com.square.core.model.Adresse;
import com.square.core.model.AdresseNature;
import com.square.core.model.CodePostalCommune;
import com.square.core.model.Email;
import com.square.core.model.EmailNature;
import com.square.core.model.NatureTelephone;
import com.square.core.model.Pays;
import com.square.core.model.Personne;
import com.square.core.model.PersonneMorale;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.PersonnePhysiqueNature;
import com.square.core.model.Relation;
import com.square.core.model.RelationType;
import com.square.core.model.Telephone;
import com.square.core.model.TypeVoie;
import com.square.core.model.Ressources.Agence;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.AdresseCreationDto;
import com.square.core.model.dto.AdresseCriteresRechercheDto;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.AdresseSimpleDto;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.PersonneBaseDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneMoraleDto;
import com.square.core.model.dto.PersonneMoraleRelationDto;
import com.square.core.model.dto.PersonnePhysiqueRelationDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.RapportDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.model.dto.SousRapportDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.model.exception.ConfirmationDesactivationEserviceException;
import com.square.core.model.exception.ConfirmationImpacterFamilleException;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.model.util.CritereRechercheEmail;
import com.square.core.model.util.CritereRechercheTelephone;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.AgenceHabilitationUtil;
import com.square.core.util.FormatUtil;
import com.square.core.util.PersonneKeyUtil;
import com.square.core.util.PersonnePhysiqueKeyUtil;
import com.square.core.util.validation.RapportUtil;
import com.square.core.util.validation.ValidationExpressionProp;
import com.square.core.util.validation.ValidationExpressionUtil;
import com.square.core.util.validation.ValidationPersonneUtil;

/**
 * Imlementation du Service Autour des Personne.
 * @author Goumard Stephane (Scub) - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class PersonneServiceImplementation implements PersonneService {

    /** Le logger. */
    private static Logger logger = Logger.getLogger(PersonnePhysiqueServiceImplementation.class);

    /** Le dao sur les personnes. */
    private PersonneDao personneDao;

    private PersonnePhysiqueDao personnePhysiqueDao;

    /** Le dao pour la nature des téléphones. */
    private NatureTelephoneDao natureTelephoneDao;

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    /** Le dao de la nature de l'adresse. */
    private AdresseNatureDao adresseNatureDao;

    /** Le dao de la nature de la voie. */
    private TypeVoieDao typeVoieDao;

    /** Dao pour la relation. */
    private RelationDao relationDao;

    /** Le dao pour les pays. */
    private PaysDao paysDao;

    /** Dao pour la relation. */
    private RelationTypeDao relationTypeDao;

    /** Classe utilitaire pour la vérification. */
    private ValidationExpressionUtil validationExpressionUtil;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Service de mapping. */
    private SquareMappingService squareMappingService;

    /** Le dao pour la nature de l'email. */
    private EmailNatureDao emailNatureDao;

    /** Dao sur les téléphones. */
    private TelephoneDao telephoneDao;

    /** Dao sur les emails. */
    private EmailDao emailDao;

    /** Dao sur les adresses. */
    private AdresseDao adresseDao;

    /** Dao sur les resources. */
    private RessourceDao ressourceDao;

    /** Dao sur les agences. */
    private AgenceDao agenceDao;

    /** Dao sur codes postaux - communes. */
    private CodePostalCommuneDao codePostalCommuneDao;

    /** Dao sur la nature de la personne Physique. */
    private PersonnePhysiqueNatureDao personnePhysiqueNatureDao;

    private FormatUtil formatUtil;

    /** Habilitation des agences . */
    private AgenceHabilitationUtil agenceHabilitationUtil;

    /** Classe utilitaire pour la validation des personnes. */
    private ValidationPersonneUtil validationPersonneUtil;

    /** Service adherent. */
    private AdherentService adherentService;

    /** Service contrat. */
    private ContratService contratService;

    private AdherentMappingService adherentMappingService;

    @Override
    public RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> rechercherRelationsParCritreres(
        RemotePagingCriteriasDto<RelationCriteresRechercheDto> remotePagingCriterias) {
        if (remotePagingCriterias == null || remotePagingCriterias.getCriterias() == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_RECH_CRITERE));
        }
        final RelationCriteresRechercheDto criteres = remotePagingCriterias.getCriterias();
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(remotePagingCriterias);

        final List<RelationInfosDto<? extends PersonneRelationDto>> resultats = new ArrayList<RelationInfosDto<? extends PersonneRelationDto>>();

        for (Relation relation : listRelation) {
            // PARAMETRE DYNAMIQUE EN FONCTION DU SENS DE LA RELATION
            Personne personne = null;
            IdentifiantLibelleDto typeRelationDto = null;
            Long idPersonnePrincipal = null;
            Long idPersonneCible = null;
            if (criteres.getIdPersonne() != null) {
                idPersonnePrincipal =
                    relation.getPersonneSource().getId().equals(criteres.getIdPersonne()) ? relation.getPersonneSource().getId() : relation.getPersonneCible()
                            .getId();
                idPersonneCible =
                    !relation.getPersonneSource().getId().equals(criteres.getIdPersonne()) ? relation.getPersonneSource().getId() : relation.getPersonneCible()
                            .getId();
                personne = relation.getPersonneSource().getId().equals(criteres.getIdPersonne()) ? relation.getPersonneCible() : relation.getPersonneSource();
                typeRelationDto =
                    new IdentifiantLibelleDto(relation.getType().getId(), relation.getPersonneSource().getId().equals(criteres.getIdPersonne()) ? relation
                            .getType().getLibelle() : relation.getType().getInverse());
            }
            else {
                typeRelationDto = new IdentifiantLibelleDto(relation.getType().getId(), relation.getType().getLibelle());
                idPersonnePrincipal = criteres.getIdPersonneSource();
                personne = relation.getPersonneCible();
                idPersonneCible = personne.getId();
            }
            if (personne instanceof PersonnePhysique) {
                final RelationInfosDto<PersonnePhysiqueRelationDto> relationDto = new RelationInfosDto<PersonnePhysiqueRelationDto>();
                mapperDozerBean.map(relation, relationDto);
                final PersonnePhysiqueRelationDto personnePhysique = mapperDozerBean.map(personne, PersonnePhysiqueRelationDto.class);
                final Calendar dateNaissance = personnePhysique.getDateNaissance();
                final Calendar dateActuelle = Calendar.getInstance();

                if (dateNaissance != null) {
                    final int age = dateActuelle.get(Calendar.YEAR) - dateNaissance.get(Calendar.YEAR);
                    personnePhysique.setAge(age);
                }
                final PersonnePhysique pPhysique = (PersonnePhysique) personne;
                if (pPhysique.getInfoSante() != null) {
                    personnePhysique.setNumSS(pPhysique.getInfoSante().getNumSecuriteSocial());
                    personnePhysique.setCleNumSS(pPhysique.getInfoSante().getCleSecuriteSocial());
                    if (pPhysique.getInfoSante() != null && pPhysique.getInfoSante().getCaisse() != null) {
                        final CaisseSimpleDto caisse =
                            new CaisseSimpleDto(pPhysique.getInfoSante().getCaisse().getId(), pPhysique.getInfoSante().getCaisse().getNom(), pPhysique
                                    .getInfoSante().getCaisse().getCode());
                        caisse.setCentre(pPhysique.getInfoSante().getCaisse().getCentre());
                        personnePhysique.setCaisse(caisse);
                        if (pPhysique.getInfoSante().getCaisse().getRegime() != null) {
                            final IdentifiantLibelleDto regime =
                                new IdentifiantLibelleDto(pPhysique.getInfoSante().getCaisse().getRegime().getId(), pPhysique.getInfoSante().getCaisse()
                                        .getRegime().getLibelle());
                            personnePhysique.setRegime(regime);
                        }
                    }
                }
                personnePhysique.setNaturePersonne((IdentifiantLibelleDto) mapperDozerBean.map(pPhysique.getNature(), IdentifiantLibelleDto.class));

                relationDto.setPersonne(personnePhysique);
                relationDto.setIdPersonnePrincipale(idPersonnePrincipal);
                relationDto.setIdPersonne(idPersonneCible);
                relationDto.setType(typeRelationDto);
                relationDto.setActif(relation.isTopActif());
                if (BooleanUtils.isTrue(criteres.getRelationsSansContrat())) {
                    if (contratService.countContrats(personnePhysique.getId(), false) == 0) {
                        resultats.add(relationDto);
                    }
                }
                else {
                    resultats.add(relationDto);
                }
            }
            else if (personne instanceof PersonneMorale) {
                final RelationInfosDto<PersonneMoraleRelationDto> relationDto = new RelationInfosDto<PersonneMoraleRelationDto>();
                mapperDozerBean.map(relation, relationDto);
                relationDto.setPersonne((PersonneMoraleRelationDto) mapperDozerBean.map(personne, PersonneMoraleRelationDto.class));
                relationDto.setIdPersonnePrincipale(idPersonnePrincipal);
                relationDto.setIdPersonne(idPersonneCible);
                relationDto.setType(typeRelationDto);
                relationDto.setActif(relation.isTopActif());
                resultats.add(relationDto);
            }
        }
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> resultsDto =
            new RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>>();
        resultsDto.setListResults(resultats);
        resultsDto.setTotalResults(relationDao.countRelationsParCriteres(criteres));
        return resultsDto;
    }

    @Override
    public RelationDto creerRelation(RelationDto relation) {
        final RapportDto rapport = controlerRelation(new RapportDto(), relation, -1);
        if (rapport.getEnErreur()) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }

        if (relation.getDateDebut() == null) {
            relation.setDateDebut(Calendar.getInstance());
        }

        final Relation relationModel = formerRelation(relation, new Relation());
        relationModel.setDateCreation(Calendar.getInstance());
        relationDao.creerRelation(relationModel);
        return mapperDozerBean.map(relationModel, RelationDto.class);
    }

    @Override
    public RelationDto rechercherRelationParId(Long idRelation) {
        final Relation relation = relationDao.rechercherRelationParId(idRelation);
        if (relation != null) {
            final RelationDto relationDto = mapperDozerBean.map(relation, RelationDto.class);
            relationDto.setType(new IdentifiantLibelleDto(relation.getType().getId(), relation.getType().getLibelle()));
            relationDto.setGroupement(new IdentifiantLibelleDto(relation.getType().getRelationGroupement().getId(), relation.getType().getRelationGroupement()
                    .getLibelle()));
            return relationDto;
        }
        return null;
    }

    @Override
    public void modifierRelation(RelationDto relation) {
        Relation relationModel = relationDao.rechercherRelationParId(relation.getId());
        if (relationModel == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_U_RELATION_EXISTE_PAS));
        }
        final RapportDto rapport = controlerRelation(new RapportDto(), relation, -1);
        if (rapport.getEnErreur()) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }
        relationModel = formerRelation(relation, relationModel);
        relationModel.setDateModification(Calendar.getInstance());
    }

    @Override
    public void modifierRelations(List<RelationDto> relations) {
        // CONTROLE DES RELATIONS
        RapportDto rapport = new RapportDto();
        for (int index = 0; index < relations.size(); index++) {
            final RelationDto relation = relations.get(index);
            final Relation relationModel = relationDao.rechercherRelationParId(relation.getId());
            if (relationModel == null) {
                throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_U_RELATION_EXISTE_PAS));
            }
            rapport = controlerRelation(rapport, relation, index);
        }
        if (rapport.getEnErreur()) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }
        // MODIFICATION DES RELATIONS
        // On traite d'abord les relations non actives
        final List<RelationDto> relationsNonActives = new ArrayList<RelationDto>();
        final List<RelationDto> relationsActives = new ArrayList<RelationDto>();
        for (RelationDto relation : relations) {
            nettoyerDate(relation.getDateDebut());
            nettoyerDate(relation.getDateFin());
            if (isRelationActive(relation.getDateDebut(), relation.getDateFin())) {
                relationsActives.add(relation);
            }
            else {
                relationsNonActives.add(relation);
            }
        }

        for (RelationDto relation : relationsNonActives) {
            final Relation relationModel = formerRelation(relation, relationDao.rechercherRelationParId(relation.getId()));
            relationModel.setDateModification(Calendar.getInstance());
        }

        // On trie les relations actives pour traiter d'abord les relations enfant-parent puis les relations conjoints
        // Cela permet de traiter les cas de changement de relation enfant->conjoint et conjoint->enfant quand ils sont
        // tous les deux présents(lors d'une erreur de saisie par exemple)
        final List<RelationDto> relationsParentsActives = new ArrayList<RelationDto>();
        final List<RelationDto> relationsConjointsActives = new ArrayList<RelationDto>();
        final List<RelationDto> autresRelationsActives = new ArrayList<RelationDto>();
        for (RelationDto relation : relationsActives) {
            if (relation.getType().equals(squareMappingService.getIdTypeRelationEnfant())) {
                relationsParentsActives.add(relation);
            }
            else if (relation.getType().equals(squareMappingService.getIdTypeRelationConjoint())) {
                relationsConjointsActives.add(relation);
            }
            else {
                autresRelationsActives.add(relation);
            }
        }

        for (RelationDto relation : relationsParentsActives) {
            final Relation relationModel = formerRelation(relation, relationDao.rechercherRelationParId(relation.getId()));
            relationModel.setDateModification(Calendar.getInstance());
        }

        for (RelationDto relation : relationsConjointsActives) {
            final Relation relationModel = formerRelation(relation, relationDao.rechercherRelationParId(relation.getId()));
            relationModel.setDateModification(Calendar.getInstance());
        }

        for (RelationDto relation : autresRelationsActives) {
            final Relation relationModel = formerRelation(relation, relationDao.rechercherRelationParId(relation.getId()));
            relationModel.setDateModification(Calendar.getInstance());
        }
    }

    /**
     * Permet de controler qu'une relation est valide.
     * @param rapport .
     * @param relation .
     * @param index .
     */
    private RapportDto controlerRelation(final RapportDto rapport, RelationDto relation, int index) {
        if (relation.getIdPersonnePrincipale() == null || relation.getIdPersonne() == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_RECH_PERSONNE));
        }
        final ValidationExpressionProp[] propsRelation = new ValidationExpressionProp[] {
        // new ValidationExpressionProp("dateDebut", index, null, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_CU_DATEDEBUT)),
            new ValidationExpressionProp("type.libelle", index, null, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_CU_TYPE))};
        validationExpressionUtil.verifierSiNull(rapport, relation, propsRelation);

        return rapport;
    }

    /**
     * Former un objet relation à partir d'un objet relationDto.
     * @param relation le dto.
     * @return la relation.
     */
    private Relation formerRelation(final RelationDto relation, Relation relationModel) {
        // CREER LA RELATION
        final Personne personnePrincipal = personneDao.rechercherPersonneParId(relation.getIdPersonnePrincipale());
        if (personnePrincipal == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_RECH_PERSONNE));
        }
        final Personne personne = personneDao.rechercherPersonneParId(relation.getIdPersonne());
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_RECH_PERSONNE));
        }
        final RelationType typeRelation = relationTypeDao.rechercherTypeRelationParId(relation.getType().getIdentifiant());
        if (typeRelation == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_CU_TYPE));
        }

        // Nettoyage des dates de début et de fin de relation
        nettoyerDate(relation.getDateDebut());
        nettoyerDate(relation.getDateFin());

        final boolean isRelationActive = isRelationActive(relation.getDateDebut(), relation.getDateFin());

        // Vérifier l'unicité de la relation de type conjoint active
        if (squareMappingService.getIdTypeRelationConjoint().equals(relation.getType().getIdentifiant()) && isRelationActive) {
            final RelationCriteresRechercheDto relationCriteresRechConj = new RelationCriteresRechercheDto();
            final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
                new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(relationCriteresRechConj, 0, Integer.MAX_VALUE);
            relationCriteresRechConj.setIdPersonne(relation.getIdPersonnePrincipale());
            relationCriteresRechConj.setActif(true);
            final List<Long> listeTypes = new ArrayList<Long>();
            listeTypes.add(squareMappingService.getIdTypeRelationConjoint());
            relationCriteresRechConj.setTypes(listeTypes);
            if (relation.getId() != null) {
                final List<Long> listePasDansRelations = new ArrayList<Long>();
                listePasDansRelations.add(relation.getId());
                relationCriteresRechConj.setPasDansRelations(listePasDansRelations);
            }
            if (relationDao.rechercherRelationsParCriteres(criterias).size() > 0) {
                throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_CONJOINT_DOUBLE));
            }

            relationCriteresRechConj.setIdPersonne(relation.getIdPersonne());
            if (relationDao.rechercherRelationsParCriteres(criterias).size() > 0) {
                throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_CONJOINT_DOUBLE));
            }
        }

        if (isRelationActive) {
            final RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();
            final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
                new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
            final List<Long> listeTypes = new ArrayList<Long>();
            listeTypes.add(typeRelation.getId());
            criteres.setTypes(listeTypes);
            if (relationModel.getId() != null) {
                final List<Long> listePasDansRelations = new ArrayList<Long>();
                listePasDansRelations.add(relationModel.getId());
                criteres.setPasDansRelations(listePasDansRelations);
            }
            criteres.setActif(true);

            // REGLE DE GESTION PAS DE RELATION DE MEME TYPE SUR LES MEMES PERSONNES ON TEST DANS LES DEUX SENS
            criteres.setIdPersonneCible(relation.getIdPersonne());
            criteres.setIdPersonneSource(relation.getIdPersonnePrincipale());
            List<Relation> controles = relationDao.rechercherRelationsParCriteres(criterias);
            if (controles.size() > 0) {
                throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_CU_DEUXIEME_TYPE));
            }
            // REGLE DE GESTION PAS DE RELATION DE MEME TYPE SUR LES MEMES PERSONNES ON TEST DANS LES DEUX SENS
            criteres.setIdPersonneCible(relation.getIdPersonnePrincipale());
            criteres.setIdPersonneSource(relation.getIdPersonne());
            controles = relationDao.rechercherRelationsParCriteres(criterias);
            if (controles.size() > 0) {
                throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_CU_DEUXIEME_TYPE));
            }
        }
        mapperDozerBean.map(relation, relationModel);
        relationModel.setType(typeRelation);

        if (!(typeRelation.getLibelle().equals(relation.getType().getLibelle()))
            || (relation.getAncienType() != null && relation.getAncienType().getIdentifiant().equals(relation.getType().getIdentifiant()) && !relation
                    .getAncienType().getLibelle().equals(relation.getType().getLibelle()))) {
            relationModel.setPersonneSource(personne);
            relationModel.setPersonneCible(personnePrincipal);
        }
        else {
            relationModel.setPersonneSource(personnePrincipal);
            relationModel.setPersonneCible(personne);
        }

        // Calcul du flag actif de la relation
        relationModel.setTopActif(isRelationActive(relationModel.getDateDebut(), relationModel.getDateFin()));
        return relationModel;
    }

    @Override
    public List<RelationDto> rechercherRelationDoubleSens(RelationCriteresRechercheDto criteres) {
        if (criteres.getIdPersonneCible() == null || criteres.getIdPersonneSource() == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL));
        }
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);

        final List<Relation> relations = new ArrayList<Relation>();

        final long idPersonnePrincipale = criteres.getIdPersonneSource();
        final long idAssureSocial = criteres.getIdPersonneCible();

        // REGLE DE GESTION PAS DE RELATION DE MEME TYPE SUR LES MEMES PERSONNES ON TEST DANS LES DEUX SENS
        criteres.setIdPersonneCible(idAssureSocial);
        criteres.setIdPersonneSource(idPersonnePrincipale);
        relations.addAll(relationDao.rechercherRelationsParCriteres(criterias));

        // REGLE DE GESTION PAS DE RELATION DE MEME TYPE SUR LES MEMES PERSONNES ON TEST DANS LES DEUX SENS
        criteres.setIdPersonneCible(idPersonnePrincipale);
        criteres.setIdPersonneSource(idAssureSocial);
        relations.addAll(relationDao.rechercherRelationsParCriteres(criterias));

        final List<RelationDto> relationsDto = new ArrayList<RelationDto>();
        for (Relation relation : relations) {
            final RelationDto relationDto = mapperDozerBean.map(relation, RelationDto.class);
            relationDto.setType(new IdentifiantLibelleDto(relation.getType().getId(), relation.getType().getLibelle()));
            relationDto.setGroupement(new IdentifiantLibelleDto(relation.getType().getRelationGroupement().getId(), relation.getType().getRelationGroupement()
                    .getLibelle()));
            relationsDto.add(relationDto);
        }

        return relationsDto;
    }

    @Override
    public List<AdresseSimpleDto> rechercherAdresseSimpleParIdPersonne(Long idPersonne) {

        // Vérifications
        if (idPersonne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL));
        }
        if (personneDao.rechercherPersonneParId(idPersonne) == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }

        // Récupération des adresses
        return personneDao.rechercherAdressesSimplesParIdPersonne(idPersonne);
    }

    @Override
    public CoordonneesDto rechercherCoordonneesParIdPersonne(Long idPersonne) {

        // Vérification que l'identifiant de la persone n'est pas null
        if (idPersonne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL));
        }
        final CoordonneesDto coordonneesDto = new CoordonneesDto();
        coordonneesDto.setIdPersonne(idPersonne);

        // RECHERCHE ADRESSE
        final AdresseCriteresRechercheDto criteres = new AdresseCriteresRechercheDto();
        criteres.setIdPersonne(idPersonne);
        final List<AdresseDto> adresses = mapperDozerBean.mapList(adresseDao.rechercherAdresseParCritere(criteres), AdresseDto.class);
        coordonneesDto.setAdresses(adresses);

        // RECHERCHE EMAIL
        final CritereRechercheEmail criteresEmail = new CritereRechercheEmail();
        final Set<Long> idsPersonnes = new HashSet<Long>();
        idsPersonnes.add(idPersonne);
        criteresEmail.setIdsPersonnes(idsPersonnes);
        final List<EmailDto> emails = mapperDozerBean.mapList(emailDao.rechercherEmailParCritere(criteresEmail), EmailDto.class);
        coordonneesDto.setEmails(emails);

        // RECHERCHE TELEPHONES
        final CritereRechercheTelephone critereTel = new CritereRechercheTelephone();
        critereTel.setIdsPersonnes(idsPersonnes);
        final List<TelephoneDto> telephones = mapperDozerBean.mapList(telephoneDao.rechercherTelephoneParCritere(critereTel), TelephoneDto.class);
        coordonneesDto.setTelephones(telephones);

        return coordonneesDto;
    }

    @Override
    public CoordonneesDto creerOuMettreAJourCoordonnees(CoordonneesDto coordonnees, Boolean impacterFamille, Boolean forcerDesactivationEservices) {
        final RapportDto rapportCoordonnees = new RapportDto();

        if (coordonnees == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_DTO_NULL));
        }

        if (coordonnees.getIdPersonne() == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL));
        }

        final Long idNatureMobilePrive = squareMappingService.getIdNatureMobilePrive();
        final Long idNatureMobileTravail = squareMappingService.getIdNatureMobileTravail();

        // Controles génériques (Au moins 1 moyen de communication)
        boolean unEmailTrouve = false;
        boolean unTelephoneTrouve = false;
        if (coordonnees.getEmails() != null) {
            for (EmailDto email : coordonnees.getEmails()) {
                if (email.getAdresse() != null && !"".equals(email.getAdresse().trim())) {
                    unEmailTrouve = true;
                    break;
                }
            }
        }
        if (coordonnees.getTelephones() != null) {
            for (TelephoneDto telephone : coordonnees.getTelephones()) {
                if (telephone != null && telephone.getNumero() != null && !"".equals(telephone.getNumero().trim())) {
                    unTelephoneTrouve = true;
                    break;
                }
            }
        }

        // Récupération de la personne en base.
        final Personne personnePrincipale = personneDao.rechercherPersonneParId(coordonnees.getIdPersonne());
        if (personnePrincipale == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }
        final boolean isAdherent =
            personnePrincipale instanceof PersonnePhysique
                && ((PersonnePhysique) personnePrincipale).getNature().getId().equals(squareMappingService.getIdNaturePersonneAdherent());

        // Récupération de la nature de la personne si personne physique
        Long idNaturePersonnePrincipale = null;
        final PersonnePhysique personnePrincipalePhysique = personnePhysiqueDao.rechercherPersonneParId(personnePrincipale.getId());
        if (personnePrincipalePhysique != null && personnePrincipalePhysique.getNature() != null) {
            idNaturePersonnePrincipale = personnePrincipalePhysique.getNature().getId();
        }
        final Long idNaturePersonneVivier = squareMappingService.getIdNaturePersonneVivier();
        final Long idNaturePersonneBeneficiaireVivier = squareMappingService.getIdNaturePersonneBeneficiaireVivier();

        // validationExpressionUtil
        // .verifierSiVrai(rapport, null, PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_COORDONNEE_OBLIGATOIRE, testUnMoyenDeCommMin,
        // CoordonneesDto.class.getSimpleName() + ".telephones")
        // .verifierSiVrai(rapport, null, PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_COORDONNEE_OBLIGATOIRE, testUnMoyenDeCommMin,
        // CoordonneesDto.class.getSimpleName() + ".emails");
        // FIXME Pour le moment, on leve une exception car non pris en charge pour l'affichage de l'interface
        if (!unEmailTrouve && !unTelephoneTrouve && !idNaturePersonneVivier.equals(idNaturePersonnePrincipale)
            && !idNaturePersonneBeneficiaireVivier.equals(idNaturePersonnePrincipale)) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_COORDONNEE_OBLIGATOIRE));
        }

        final Set<Personne> personnes = new LinkedHashSet<Personne>();
        // On recherche les membres de la famille de la personne
        final Set<Personne> famille = getFamille(personnePrincipale);
        // Si le flag qui indique si l'on doit ajouter la nouvelle adresse aux bénéficiaires n'est pas spécifié
        // Si la personne a une famille, on lève une exception car le flag impacterBeneficiaires doit être renseigné
        // pour déterminer si il faut ajouter l'adresse aux membres de la famille de la personne
        if (impacterFamille == null && famille.size() > 0) {
            throw new ConfirmationImpacterFamilleException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.CONFIRMATION_IMPACTER_FAMILLE, new String[] {String
                    .valueOf(famille.size())}));
        }
        personnes.add(personnePrincipale);
        if (BooleanUtils.isTrue(impacterFamille)) {
            // On ajoute les membres de la famille à la liste des personnes concernées par la création de nouvelles coordonnées
            personnes.addAll(famille);
        }
        final Calendar maintenant = Calendar.getInstance();

        boolean desactiverEserviceTelephone = false;
        boolean desactiverEserviceEmail = false;

        // Traitement des téléphones
        int nbTelephonesMobile = 0;
        if (coordonnees.getTelephones() != null && !coordonnees.getTelephones().isEmpty()) {
            for (int i = 0; i < coordonnees.getTelephones().size(); i++) {
                final TelephoneDto telephoneDto = coordonnees.getTelephones().get(i);
                final boolean numeroRenseigne = StringUtils.isNotBlank(telephoneDto.getNumero());
                if (telephoneDto.getNature() != null
                    && (telephoneDto.getNature().getIdentifiant().equals(idNatureMobilePrive) || telephoneDto.getNature().getIdentifiant().equals(
                        idNatureMobileTravail)) && numeroRenseigne) {
                    nbTelephonesMobile++;
                }
                boolean isTelephoneModifie = false;
                if (telephoneDto.getId() != null) {
                    // On récupère le téléphone à mettre à jour à partir de la base de données
                    final Telephone telephoneAMaj = telephoneDao.rechercherTelephoneParId(telephoneDto.getId());
                    // Si on recoit un id ici, mais que le numéro n'est pas renseigné, il faut supprimer le téléphone correspondant
                    // Note : on ne supprime pas tous les téléphones car d'autres peuvent exister mais ne pas être affichés dans Square
                    if (!numeroRenseigne) {
                        // Si il faut impacter toute la famille, ou que le téléphone est lié à la personne principale uniquement
                        if (BooleanUtils.isTrue(impacterFamille) || telephoneAMaj.getPersonnes().size() == 1) {
                            // On supprime le téléphone
                            supprimerTelephone(telephoneAMaj, maintenant);
                        }
                        else {
                            // On enlève le téléphone de la liste des téléphones de la personne principale
                            personnePrincipale.removeTelephone(telephoneAMaj);
                            // on verifie si la personne est le porteur
                            if (telephoneAMaj.getPorteurUid() != null && telephoneAMaj.getPorteurUid().equals(personnePrincipale.getId())) {
                                // on supprime l'identifiant externe et le porteur du telephone restant sur la famille
                                telephoneAMaj.setIdentifiantExterieur(null);
                                telephoneAMaj.setPorteurUid(null);
                            }
                        }
                        continue;
                    }
                    else {
                        // Sinon on détermine si le téléphone a été modifié
                        isTelephoneModifie = isTelephoneModifie(telephoneAMaj, telephoneDto);
                        // Si le téléphone existe et a été modifié, et qu'il ne faut pas impacter la famille
                        if (BooleanUtils.isFalse(impacterFamille) && isTelephoneModifie && telephoneAMaj.getPersonnes().size() > 1) {
                            // on supprime le telephone de la personne
                            personnePrincipale.removeTelephone(telephoneAMaj);
                            // on supprime l'identifiant pour créer un nouveau telephone
                            telephoneDto.setId(null);
                            // on verifie si la personne est le porteur
                            if (telephoneAMaj.getPorteurUid() != null && telephoneAMaj.getPorteurUid().equals(personnePrincipale.getId())) {
                                // on supprime l'identifiant externe et le porteur du telephone restant sur la famille (pas de synchro aia)
                                telephoneAMaj.setIdentifiantExterieur(null);
                                telephoneAMaj.setPorteurUid(null);
                            }
                            else {
                                // On supprime l'identifiant externe afin de ne pas synchroniser avec aia
                                telephoneDto.setIdext(null);
                            }
                        }
                    }
                }
                if (numeroRenseigne) {
                    final RapportDto rapportTelephone = new RapportDto();
                    controlerTelephone(rapportTelephone, telephoneDto, i);
                    if (rapportTelephone.getRapports() != null && !rapportTelephone.getRapports().isEmpty()) {
                        for (String key : rapportTelephone.getRapports().keySet()) {
                            final SousRapportDto sousRapport = rapportTelephone.getRapports().get(key);
                            rapportCoordonnees.ajoutRapport(sousRapport.getAttribut(), sousRapport.getMessage(), sousRapport.getErreur());
                        }
                    }
                    if (!rapportTelephone.getEnErreur()) {
                        final Telephone telephone = creerOuMettreAJourTelephone(personnes, telephoneDto, impacterFamille);
                        if (telephone.getId() == null) {
                            // On rattache le téléphone à la personne principale
                            personnePrincipale.addTelephone(telephone);
                            if (BooleanUtils.isTrue(impacterFamille)) {
                                // On rattache aussi le téléphone aux membres de sa famille
                                for (Personne personne : famille) {
                                    // bug 8832 : on impacte que ceux qui n'ont pas deja de telephones
                                    if (personne.getTelephones().size() == 0) {
                                        personne.addTelephone(telephone);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // on desactive l'envoi par telephone
        if (nbTelephonesMobile == 0 && isAdherent) {
            // on verifie si la personne possede l'eservice
            final List<Long> optionsSouscrites = adherentService.getListeIdsTypesOptionsSouscrites(personnePrincipale.getId());
            boolean possedeEserviceTelephone = false;
            for (Long idTypeOption : optionsSouscrites) {
                if (idTypeOption.equals(adherentMappingService.getIdTypeOptionEnvoiSms())) {
                    possedeEserviceTelephone = true;
                    break;
                }
            }
            desactiverEserviceTelephone = possedeEserviceTelephone;
        }

        // Traitement des emails
        if (coordonnees.getEmails() != null && !coordonnees.getEmails().isEmpty()) {
            for (int i = 0; i < coordonnees.getEmails().size(); i++) {
                final EmailDto emailDto = coordonnees.getEmails().get(i);
                final boolean adresseEmailRenseignee = StringUtils.isNotBlank(emailDto.getAdresse());
                boolean isEmailModifie = false;

                // FIXME : La constante nature de l'email devrait elle etre fournie par le client ?
                emailDto.setNatureEmail(new IdentifiantLibelleDto(squareMappingService.getIdNatureEmailPersonnel()));

                if (emailDto.getIdentifiant() != null) {
                    // On récupère l'email à mettre à jour à partir de la base de données
                    final Email emailAMaj = emailDao.rechercherEmailParId(emailDto.getIdentifiant());
                    // Si on recoit un id ici, mais que les infos ne sont pas renseignées, il faut supprimer le téléphone correspondant
                    // Note : on ne supprime pas tous les téléphones car d'autres peuvent exister mais ne pas être affichés dans Square
                    if (!adresseEmailRenseignee) {
                        // Si il faut impacter toute la famille, ou que l'email est lié à la personne principale uniquement
                        if (BooleanUtils.isTrue(impacterFamille) || emailAMaj.getPersonnes().size() == 1) {
                            // On supprime l'email
                            supprimerEmail(emailAMaj, maintenant);
                        }
                        else {
                            // On enlève le téléphone de la liste des téléphones de la personne principale
                            personnePrincipale.removeEmail(emailAMaj);
                            // on verifie si la personne est le porteur
                            if (emailAMaj.getPorteurUid() != null && emailAMaj.getPorteurUid().equals(personnePrincipale.getId())) {
                                // on supprime l'identifiant externe et le porteur de l'email restant sur la famille
                                emailAMaj.setIdentifiantExterieur(null);
                                emailAMaj.setPorteurUid(null);
                            }
                        }
                        if (isAdherent) {
                            // on verifie si la personne possede l'eservice
                            final List<Long> optionsSouscrites = adherentService.getListeIdsTypesOptionsSouscrites(personnePrincipale.getId());
                            boolean possedeEserviceEmail = false;
                            for (Long idTypeOption : optionsSouscrites) {
                                if (idTypeOption.equals(adherentMappingService.getIdTypeOptionEnvoiMutuellementEmail())
                                    || idTypeOption.equals(adherentMappingService.getIdTypeOptionEnvoiRelevesPrestationEmail())) {
                                    possedeEserviceEmail = true;
                                    break;
                                }
                            }
                            desactiverEserviceEmail = possedeEserviceEmail;
                        }
                        continue;
                    }
                    else {
                        // Sinon on détermine si l'email a été modifié
                        isEmailModifie = isEmailModifie(emailAMaj, emailDto);
                        // Si le téléphone existe et a été modifié, et qu'il ne faut pas impacter la famille
                        if (BooleanUtils.isFalse(impacterFamille) && isEmailModifie && emailAMaj.getPersonnes().size() > 1) {
                            // on supprime l'email de la personne
                            personnePrincipale.removeEmail(emailAMaj);
                            // on supprime l'identifiant pour créer un nouvel email
                            emailDto.setIdentifiant(null);
                            // on verifie si la personne est le porteur
                            if (emailAMaj.getPorteurUid() != null && emailAMaj.getPorteurUid().equals(personnePrincipale.getId())) {
                                // on supprime l'identifiant externe et le porteur de l'email restant sur la famille (pas de synchro aia)
                                emailAMaj.setIdentifiantExterieur(null);
                                emailAMaj.setPorteurUid(null);
                            }
                            else {
                                // On supprime l'identifiant externe afin de ne pas synchroniser avec aia
                                emailDto.setIdext(null);
                            }
                        }
                    }
                }
                if (adresseEmailRenseignee) {
                    final RapportDto rapportEmail = new RapportDto();
                    controlerEmail(rapportEmail, emailDto, i);
                    if (rapportEmail.getRapports() != null && !rapportEmail.getRapports().isEmpty()) {
                        for (String key : rapportEmail.getRapports().keySet()) {
                            final SousRapportDto sousRapport = rapportEmail.getRapports().get(key);
                            rapportCoordonnees.ajoutRapport(sousRapport.getAttribut(), sousRapport.getMessage(), sousRapport.getErreur());
                        }
                    }
                    if (!rapportEmail.getEnErreur()) {
                        final Email email = creerOuMettreAJourEmail(personnes, emailDto, impacterFamille);
                        // Si c'est un nouvel email
                        if (email.getId() == null) {
                            // On rattache le nouvel email à la personne principale
                            personnePrincipale.addEMail(email);
                            if (BooleanUtils.isTrue(impacterFamille)) {
                                // On rattache aussi le nouvel email aux membres de sa famille
                                for (Personne personne : famille) {
                                    // bug 8832 : on impacte que ceux qui n'ont pas deja d'emails
                                    if (personne.getEmails().size() == 0) {
                                        personne.addEMail(email);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // on desactive les eservices suivant les besoins
        if (!BooleanUtils.isTrue(forcerDesactivationEservices)) {
            if (desactiverEserviceTelephone && desactiverEserviceEmail) {
                throw new ConfirmationDesactivationEserviceException(messageSourceUtil
                        .get(PersonneKeyUtil.MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_TELEPHONE_ET_EMAIL));
            }
            else if (desactiverEserviceTelephone) {
                throw new ConfirmationDesactivationEserviceException(messageSourceUtil
                        .get(PersonneKeyUtil.MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_TELEPHONE));
            }
            else if (desactiverEserviceEmail) {
                throw new ConfirmationDesactivationEserviceException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_EMAIL));
            }
        }
        else {
            if (desactiverEserviceTelephone) {
                adherentService.desactiverOptionsEnvoiParTelephone(personnePrincipale.getId());
            }
            if (desactiverEserviceEmail) {
                adherentService.desactiverOptionsEnvoiParEmail(personnePrincipale.getId());
            }
        }

        // Recherche de l'adresse principale en cours
        final AdresseCriteresRechercheDto criteresRechercheAdressePrincipale = new AdresseCriteresRechercheDto();
        criteresRechercheAdressePrincipale.setIdPersonne(personnePrincipale.getId());
        criteresRechercheAdressePrincipale.setIdNature(squareMappingService.getIdNatureAdressePrincipale());
        criteresRechercheAdressePrincipale.setActive(true);
        final List<Adresse> listAddressesPrin = adresseDao.rechercherAdresseParCritere(criteresRechercheAdressePrincipale);
        Adresse adressePrincipaleEnCours = new Adresse();
        boolean plusQueAdressePrincipale = false;
        boolean choixPasserEnSecondaire = false;
        if (listAddressesPrin != null && listAddressesPrin.size() > 0) {
            adressePrincipaleEnCours = listAddressesPrin.get(0);
        }
        Calendar dateFinAdressePrincipale = null;

        // Traitement des adresses
        if (coordonnees.getAdresses() != null && !coordonnees.getAdresses().isEmpty()) {
            final RapportDto rapportAdresses = new RapportDto();
            for (int i = 0; i < coordonnees.getAdresses().size(); i++) {
                final AdresseDto adresseDto = coordonnees.getAdresses().get(i);

                if (adresseDto.getNature() != null && adresseDto.getNature().getIdentifiant().equals(squareMappingService.getIdNatureAdressePrincipale())
                    && adressePrincipaleEnCours.getId() != null && !adressePrincipaleEnCours.getId().equals(adresseDto.getIdentifiant())
                    && (adresseDto.getDateFin() == null || Calendar.getInstance().before(adresseDto.getDateFin()))) {

                    dateFinAdressePrincipale = adresseDto.getDateDebut();
                    if (!choixPasserEnSecondaire) {
                        choixPasserEnSecondaire =
                            adresseDto.getChoixPasserEnSecondaire() == null ? false : adresseDto.getChoixPasserEnSecondaire().booleanValue();
                    }
                    plusQueAdressePrincipale = true;
                }

                final RapportDto rapportAdresse = new RapportDto();
                controlerAdresse(rapportAdresse, adresseDto, i);
                if (rapportAdresse.getRapports() != null && !rapportAdresse.getRapports().isEmpty()) {
                    for (String key : rapportAdresse.getRapports().keySet()) {
                        final SousRapportDto sousRapport = rapportAdresse.getRapports().get(key);
                        rapportCoordonnees.ajoutRapport(sousRapport.getAttribut(), sousRapport.getMessage(), sousRapport.getErreur());
                    }
                }
                if (!rapportAdresse.getEnErreur()) {
                    if (adresseDto.getIdentifiant() != null) {
                        final Adresse adresseAMaj = adresseDao.rechercherAdresseParId(adresseDto.getIdentifiant());
                        final boolean isAdresseModifiee = isAdresseModifiee(adresseAMaj, adresseDto);
                        // Si l'adresse existe et a été modifiée, et qu'il ne faut pas impacter la famille
                        if (BooleanUtils.isFalse(impacterFamille) && isAdresseModifiee && adresseAMaj.getPersonnes().size() > 1) {
                            // on supprime l'adresse de la personne
                            personnePrincipale.removeAdresse(adresseAMaj);
                            // On supprime l'identifiant de l'adresse afin de créer une nouvelle adresse
                            adresseDto.setIdentifiant(null);
                            // on verifie si la personne est le porteur
                            if (adresseAMaj.getPorteurUid() != null && adresseAMaj.getPorteurUid().equals(personnePrincipale.getId())) {
                                // on supprime l'identifiant externe et le porteur de l'adresse restante sur la famille (pas de synchro aia)
                                adresseAMaj.setIdentifiantExterieur(null);
                                adresseAMaj.setPorteurUid(null);
                            }
                            else {
                                // On supprime l'identifiant externe pour ne pas synchroniser avec aia
                                adresseDto.setIdext(null);
                            }
                        }
                    }
                    final Adresse adresseCree = creerOuMettreAJourAdresse(personnes, adresseDto, impacterFamille);
                    // on met l'id ext à jour apres la création (probleme de flush a cause de la contrainte d'unicité)
                    adresseCree.setIdentifiantExterieur(adresseDto.getIdext());
                }
            }
            final AdresseNature adresseNature = adresseNatureDao.rechercheAdresseNatureParId(squareMappingService.getIdNatureAdresseSecondaire());

            // si le rapport n'est pas en erreur, on met à jour l'adresse principale
            if (Boolean.FALSE.equals(rapportAdresses.getEnErreur())) {
                for (int i = 0; i < coordonnees.getAdresses().size(); i++) {
                    final AdresseDto adresseDto = coordonnees.getAdresses().get(i);
                    if (plusQueAdressePrincipale && (adressePrincipaleEnCours.getId().equals(adresseDto.getIdentifiant()))) {
                        if (choixPasserEnSecondaire) {
                            adressePrincipaleEnCours.setNature(adresseNature);
                        }
                        else {
                            dateFinAdressePrincipale.add(Calendar.DAY_OF_MONTH, -1);
                            adressePrincipaleEnCours.setDateFin(dateFinAdressePrincipale);
                        }
                        break;
                    }
                }
            }
        }

        if (Boolean.TRUE.equals(rapportCoordonnees.getEnErreur()) && !idNaturePersonneVivier.equals(idNaturePersonnePrincipale)
            && !idNaturePersonneBeneficiaireVivier.equals(idNaturePersonnePrincipale)) {
            RapportUtil.logRapport(rapportCoordonnees, logger);
            throw new ControleIntegriteException(rapportCoordonnees);
        }

        /*
         * // Un contact devra avoir au moins une adresse principal criteresRechercheAdressePrincipale = new AdresseCriteresRechercheDto();
         * criteresRechercheAdressePrincipale.setIdPersonne(personne.getId());
         * criteresRechercheAdressePrincipale.setIdNature(squareMappingService.getIdNatureAdressePrincipale()); long nbPrimaires =
         * adresseDao.rechercherIdAdressesParCriteres(criteresRechercheAdressePrincipale).size(); if (nbPrimaires == 0) { throw new
         * BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_AUCUNE_ADRESSE_PRINCIPALE)); } else if (nbPrimaires > 1) { throw new
         * BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_PRINCIPALE_DUPLIQUEE)); }
         */

        // 2.13 Une date de fin ne pourra etre mis sur une adresse principal que s'il existe une adresse secondaire.
        // Dans ce cas l'adresse principal deviendra automatiquement secondaire et l'adresse secondaire deviendra principal
        Adresse adressePrincipale = null;
        Adresse adresseSecondaire = null;
        for (Adresse adresse : personnePrincipale.getAdresses()) {
            if (adresse.getNature().getId().equals(squareMappingService.getIdNatureAdressePrincipale())) {
                adressePrincipale = adresse;
            }
            if (adresse.getNature().getId().equals(squareMappingService.getIdNatureAdresseSecondaire()) && adresse.getDateFin() == null) {
                adresseSecondaire = adresse;
            }
        }
        if (adressePrincipale != null && adressePrincipale.getDateFin() != null && adresseSecondaire != null) {
            // Changement adresse principale adresse secondaire
            adressePrincipale.setNature(adresseNatureDao.rechercheAdresseNatureParId(squareMappingService.getIdNatureAdresseSecondaire()));
            adresseSecondaire.setNature(adresseNatureDao.rechercheAdresseNatureParId(squareMappingService.getIdNatureAdressePrincipale()));
            // Enregistrement des adresses
            final AdresseDto adresseDtoPrincipal = mapperDozerBean.map(adressePrincipale, AdresseDto.class);
            final AdresseDto adresseDtoSecondaire = mapperDozerBean.map(adresseSecondaire, AdresseDto.class);
            creerOuMettreAJourAdresse(personnes, adresseDtoPrincipal, impacterFamille);
            creerOuMettreAJourAdresse(personnes, adresseDtoSecondaire, impacterFamille);
        }

        if (!idNaturePersonneVivier.equals(idNaturePersonnePrincipale) && !idNaturePersonneBeneficiaireVivier.equals(idNaturePersonnePrincipale)) {
            controlerPersonneAUneAdressePrincipale(personnePrincipale);
        }

        // si il s'agit d'une personne physique et vivier ou bénéficiaire vivier, on transforme en prospect ou bénéficiaire prospect
        boolean vivierToProspect = false;
        boolean hasNaturePersonneChanged = false;
        String ancienneNaturePersonne = "";
        String nouvelleNaturePersonne = "";
        if (personnePrincipalePhysique != null && personnePrincipalePhysique.getNature() != null) {
            ancienneNaturePersonne = personnePrincipalePhysique.getNature().getLibelle();
            Long idNaturePersonne = null;
            if (idNaturePersonneVivier.equals(idNaturePersonnePrincipale) && validationPersonneUtil.verifierContrainteProspect(personnePrincipalePhysique)) {
                idNaturePersonne = squareMappingService.getIdNaturePersonneProspect();
                vivierToProspect = true;
                hasNaturePersonneChanged = true;
            }
            else if (idNaturePersonneBeneficiaireVivier.equals(idNaturePersonnePrincipale)
                && validationPersonneUtil.verifierContrainteBeneficiaireProspect(personnePrincipalePhysique)) {
                idNaturePersonne = squareMappingService.getIdNaturePersonneBeneficiaireProspect();
                hasNaturePersonneChanged = true;
            }
            if (idNaturePersonne != null) {
                final PersonnePhysiqueNature naturePersonne = personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(idNaturePersonne);
                if (naturePersonne != null) {
                    nouvelleNaturePersonne = naturePersonne.getLibelle();
                }
                personnePrincipalePhysique.setNature(naturePersonne);
            }
        }

        // si la personne est passée de vivier à prospect et qu'elle a des bénéficiaires vivier,
        // on essaye de les passer en bénéficiaire prospect si c'est possible
        if (vivierToProspect && famille != null && famille.size() > 0) {
            for (Personne beneficiaire : famille) {
                final PersonnePhysique beneficiairePhysique = (PersonnePhysique) beneficiaire;
                if (beneficiairePhysique.getNature() != null && idNaturePersonneBeneficiaireVivier.equals(beneficiairePhysique.getNature().getId())
                    && validationPersonneUtil.verifierContrainteBeneficiaireProspect(beneficiairePhysique)) {
                    final PersonnePhysiqueNature naturePersonne =
                        personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(squareMappingService.getIdNaturePersonneBeneficiaireProspect());
                    beneficiairePhysique.setNature(naturePersonne);
                }
            }
        }

        final CoordonneesDto newCoordonnees = rechercherCoordonneesParIdPersonne(personnePrincipale.getId());
        newCoordonnees.setHasNaturePersonneChanged(hasNaturePersonneChanged);
        newCoordonnees.setAncienneNaturePersonne(ancienneNaturePersonne);
        newCoordonnees.setNouvelleNaturePersonne(nouvelleNaturePersonne);
        return newCoordonnees;
    }

    /**
     * Vérifie qu'une personne a au moins une adresse principale.
     * @param personne la personne à contrôler
     */
    private void controlerPersonneAUneAdressePrincipale(Personne personne) {
        int nbPrimaires = 0;
        for (Adresse adresse : personne.getAdresses()) {
            if (squareMappingService.getIdNatureAdressePrincipale().equals(adresse.getNature().getId()) && !adresse.isSupprime()
                && (adresse.getDateFin() == null || Calendar.getInstance().before(adresse.getDateFin()))) {
                nbPrimaires++;
            }
        }
        if (nbPrimaires == 0) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_AUCUNE_ADRESSE_PRINCIPALE));
        }
        else if (nbPrimaires > 1) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_PRINCIPALE_DUPLIQUEE));
        }
    }

    /**
     * Détermine si le téléphone a été modifié ou non.
     * @param telephoneAMaj le téléphone provenant de la base de données
     * @param telephoneDto le téléphone modifié
     * @return true si le DTO contient des infos modifiée, false sinon
     */
    private boolean isTelephoneModifie(Telephone telephoneAMaj, TelephoneDto telephoneDto) {
        if (!telephoneAMaj.getNumTelephone().equals(formatUtil.supprimerFormatNumTel(telephoneDto.getNumero()))) {
            return true;
        }
        if (telephoneDto.getNature() == null || !telephoneAMaj.getNatureTelephone().getId().equals(telephoneDto.getNature().getIdentifiant())) {
            return true;
        }
        if (telephoneDto.getPays() == null || !telephoneAMaj.getPays().getId().equals(telephoneDto.getPays().getId())) {
            return true;
        }
        return false;
    }

    /**
     * Détermine si l'email a été modifié ou non.
     * @param emailAMaj l'email provenant de la base de données
     * @param emailDto l'email modifié
     * @return true si le DTO contient des infos modifiée, false sinon
     */
    private boolean isEmailModifie(Email emailAMaj, EmailDto emailDto) {
        if (!emailAMaj.getAdresse().equals(emailDto.getAdresse())) {
            return true;
        }
        if (emailDto.getNatureEmail() == null || !emailAMaj.getNature().getId().equals(emailDto.getNatureEmail().getIdentifiant())) {
            return true;
        }
        return false;
    }

    private boolean isAdresseModifiee(Adresse adresseAMaj, AdresseDto adresseDto) {
        if (!StringUtils.equals(adresseAMaj.getNumeroVoie(), adresseDto.getNumVoie())) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : numero voie different");
            }
            return true;
        }
        if (!StringUtils.equals(adresseAMaj.getNomVoie(), adresseDto.getNomVoie())) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : nom voie different");
            }
            return true;
        }
        if (!StringUtils.equals(adresseAMaj.getResidenceBatiment(), adresseDto.getComplementAdresse())) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : complement adresse different");
            }
            return true;
        }
        if (!StringUtils.equals(adresseAMaj.getBoitePostal(), adresseDto.getAutresComplements())) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : autres complements different");
            }
            return true;
        }
        if (adresseAMaj.getTopNpai() != adresseDto.isNpai()) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : NPAI different");
            }
            return true;
        }
        if (adresseAMaj.getNature() != null && adresseDto.getNature() == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : nature differente");
            }
            return true;
        }
        else if (adresseAMaj.getNature() == null && adresseDto.getNature() != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : nature differente");
            }
            return true;
        }
        else if (adresseAMaj.getNature() != null && adresseDto.getNature() != null
            && !adresseAMaj.getNature().getId().equals(adresseDto.getNature().getIdentifiant())) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : nature differente");
            }
            return true;
        }
        if (adresseAMaj.getTypeVoie() != null && adresseDto.getTypeVoie() == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : type voie differente");
            }
            return true;
        }
        else if (adresseAMaj.getTypeVoie() == null && adresseDto.getTypeVoie() != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : type voie differente");
            }
            return true;
        }
        else if (adresseAMaj.getTypeVoie() != null && adresseDto.getTypeVoie() != null
            && !adresseAMaj.getTypeVoie().getId().equals(adresseDto.getTypeVoie().getIdentifiant())) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : type voie differente");
            }
            return true;
        }
        if (adresseAMaj.getCodePostalCommune() == null && adresseDto.getCodePostalCommune() != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : code postal commune differente");
            }
            return true;
        }
        else if (adresseAMaj.getCodePostalCommune() != null && adresseDto.getCodePostalCommune() == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : code postal commune differente");
            }
            return true;
        }
        else if (adresseAMaj.getCodePostalCommune() != null && adresseDto.getCodePostalCommune() != null) {
            if (adresseAMaj.getCodePostalCommune().getCodePostal() != null && adresseDto.getCodePostalCommune().getCodePostal() == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("isAdresseModifiee : code postal differente");
                }
                return true;
            }
            else if (adresseAMaj.getCodePostalCommune().getCodePostal() == null && adresseDto.getCodePostalCommune().getCodePostal() != null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("isAdresseModifiee : code postal differente");
                }
                return true;
            }
            else if (adresseAMaj.getCodePostalCommune().getCodePostal() != null && adresseDto.getCodePostalCommune().getCodePostal() != null
                && !adresseAMaj.getCodePostalCommune().getCodePostal().getId().equals(adresseDto.getCodePostalCommune().getCodePostal().getIdentifiant())) {
                if (logger.isDebugEnabled()) {
                    logger.debug("isAdresseModifiee : code postal differente");
                }
                return true;
            }
            if (adresseAMaj.getCodePostalCommune().getCommune() != null && adresseDto.getCodePostalCommune().getCommune() == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("isAdresseModifiee : commune differente");
                }
                return true;
            }
            else if (adresseAMaj.getCodePostalCommune().getCommune() == null && adresseDto.getCodePostalCommune().getCommune() != null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("isAdresseModifiee : commune differente");
                }
                return true;
            }
            else if (adresseAMaj.getCodePostalCommune().getCommune() != null && adresseDto.getCodePostalCommune().getCommune() != null
                && !adresseAMaj.getCodePostalCommune().getCommune().getId().equals(adresseDto.getCodePostalCommune().getCommune().getIdentifiant())) {
                if (logger.isDebugEnabled()) {
                    logger.debug("isAdresseModifiee : commune differente");
                }
                return true;
            }
        }
        if (!adresseAMaj.getPays().getId().equals(adresseDto.getPays().getIdentifiant())) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : pays different");
            }
            return true;
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (adresseAMaj.getDateDebut() == null && adresseDto.getDateDebut() != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : date debut differente");
            }
            return true;
        }
        else if (adresseAMaj.getDateDebut() != null && adresseDto.getDateDebut() == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : date debut differente");
            }
            return true;
        }
        else if (adresseAMaj.getDateDebut() != null && adresseDto.getDateDebut() != null
            && !sdf.format(adresseAMaj.getDateDebut().getTime()).equals(sdf.format(adresseDto.getDateDebut().getTime()))) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : date debut differente");
            }
            return true;
        }
        if (adresseAMaj.getDateFin() == null && adresseDto.getDateFin() != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : date fin differente");
            }
            return true;
        }
        else if (adresseAMaj.getDateFin() != null && adresseDto.getDateFin() == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : date fin differente");
            }
            return true;
        }
        else if (adresseAMaj.getDateFin() != null && adresseDto.getDateFin() != null
            && !sdf.format(adresseAMaj.getDateFin().getTime()).equals(sdf.format(adresseDto.getDateFin().getTime()))) {
            if (logger.isDebugEnabled()) {
                logger.debug("isAdresseModifiee : date fin differente");
            }
            return true;
        }
        return false;
    }

    private void controlerTelephone(final RapportDto rapport, final TelephoneDto telephoneDto, final int index) {
        final ValidationExpressionProp[] propsTelephone =
            new ValidationExpressionProp[] {
                new ValidationExpressionProp("nature", index, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_NATURETELEPHONE_NULL)),
                new ValidationExpressionProp("numero", index, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_NUMTELEPHONE_NULL))};

        // Récupération du format du téléphone en fonction de son pays
        String formatTel = "";
        boolean isPaysTelFrance = false;
        if (telephoneDto.getPays() != null) {
            final Pays paysTelephone = paysDao.rechercherPaysParId(telephoneDto.getPays().getId());
            if (paysTelephone != null) {
                formatTel = paysTelephone.getFormatTelephone();
                if (paysTelephone.getId().equals(squareMappingService.getIdPaysFrance())
                    || paysTelephone.getId().equals(squareMappingService.getIdPaysFranceMetropolitaine())) {
                    isPaysTelFrance = true;
                }
            }
        }

        // Note : Si un index significatif est passé, on change le libellé pour qu'il
        // corresponde a la norme des libellés de type liste (ie TelephoneDto.pays.O TelephoneDto.numero.1 ...)
        validationExpressionUtil.verifierSiVide(rapport, telephoneDto, propsTelephone).verifierSiNull(rapport, null,
            messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_INDICATIFTEL_NULL), telephoneDto.getPays() == null ? null : telephoneDto.getPays().getId(),
            TelephoneDto.class.getSimpleName() + ".pays" + (index > -1 ? "." + index : "")).verifierFormatTelephone(rapport, null,
            messageSourceUtil.get(MESSAGE_ERROR_BAD_NUMTELEPHONE), telephoneDto.getNumero(), formatTel,
            TelephoneDto.class.getSimpleName() + ".numero" + (index > -1 ? "." + index : ""));
        // FIXME : pour l'instant, plus de vérification de la nature de téléphone
        // if (telephoneDto.getNature() != null && telephoneDto.getNature().getIdentifiant() != null
        // && squareMappingService.getListeIdsNaturesTelephoneMobile().contains(telephoneDto.getNature().getIdentifiant())) {
        // validationExpressionUtil.verifierTelephoneMobile(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), telephoneDto
        // .getNumero(), isPaysTelFrance, formatTel, TelephoneDto.class.getSimpleName() + ".nature" + (index > -1 ? "." + index : ""));
        // } else if (telephoneDto.getNature() != null && telephoneDto.getNature().getIdentifiant() != null
        // && squareMappingService.getIdNatureTelephoneFixe().equals(telephoneDto.getNature().getIdentifiant())) {
        // validationExpressionUtil.verifierTelephoneFixe(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), telephoneDto
        // .getNumero(), isPaysTelFrance, formatTel, TelephoneDto.class.getSimpleName() + ".nature" + (index > -1 ? "." + index : ""));
        // } else {
        // validationExpressionUtil.verifierTelephoneFixe(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), telephoneDto
        // .getNumero(), false, formatTel, TelephoneDto.class.getSimpleName() + ".nature" + (index > -1 ? "." + index : ""));
        // }
    }

    private Telephone creerOuMettreAJourTelephone(Set<Personne> personnes, TelephoneDto telephoneDto, Boolean impacterFamille) {
        // Vérification des dépendances
        final NatureTelephone natureTelephone = natureTelephoneDao.rechercherNatureTelephoneParId(telephoneDto.getNature().getIdentifiant());
        if (natureTelephone == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_NATURE_TELEPHONE_INEXISTENT_EN_BD));
        }

        final Pays paysTelephone = paysDao.rechercherPaysParId(telephoneDto.getPays().getId());
        if (paysTelephone == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PAYS_INEXISTENT_EN_BD));
        }
        // On construit la liste des identifiants des persones de la famille
        final Set<Long> idsPersonnes = new LinkedHashSet<Long>();
        for (Personne personne : personnes) {
            idsPersonnes.add(personne.getId());
        }
        // On construit la liste des personnes associées au téléphone en cas de restauration
        final Set<Personne> nouvellesPersonnes = new LinkedHashSet<Personne>();
        Long idPersonneModifie = null;
        for (Personne personne : personnes) {
            if (idPersonneModifie == null) {
                idPersonneModifie = personne.getId();
            }
            nouvellesPersonnes.add(personne);
            if (BooleanUtils.isFalse(impacterFamille)) {
                break;
            }
        }
        // On supprime le formatage du numéro de téléphone pour stocker les données brutes seulement
        final String numeroTelephone = formatUtil.supprimerFormatNumTel(telephoneDto.getNumero());
        Telephone telephone;
        final Calendar maintenant = Calendar.getInstance();

        if (telephoneDto.getId() != null) {
            // si le téléphone est à supprimer
            if (BooleanUtils.isTrue(telephoneDto.getSupprime())) {
                final Telephone tel = telephoneDao.rechercherTelephoneParId(telephoneDto.getId());
                supprimerTelephone(tel, maintenant);
                return tel;
            }
            else {
                // MODE MISE A JOUR
                // On récupère le téléphone à mettre à jour
                telephone = telephoneDao.rechercherTelephoneParId(telephoneDto.getId());
                if (telephone == null) {
                    throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_TELEPHONE_INEXISTENT_EN_BD));
                }
                // On vérifie que le téléphone n'existe pas déjà dans la liste des téléphones de la personne
                final CritereRechercheTelephone critereTel = new CritereRechercheTelephone();
                critereTel.setIdsPersonnes(idsPersonnes);
                critereTel.getIdsTelExclus().add(telephoneDto.getId());
                critereTel.setNumeroTelephone(numeroTelephone);
                critereTel.setIsSupprime(null);
                final List<Telephone> listeTelephones = telephoneDao.rechercherTelephoneParCritere(critereTel);
                if (listeTelephones != null && listeTelephones.size() > 0) {
                    // Ce téléphone existe déjà, on le récupère
                    final Telephone telExistant = listeTelephones.get(0);
                    // On le restaure
                    restaurerTelephone(telExistant, nouvellesPersonnes, maintenant);
                    // On supprime l'ancien téléphone
                    supprimerTelephone(telephone, maintenant);
                    return telExistant;
                }
                else {
                    // MISE A JOUR DU TELEPHONE
                    telephone.setDateModification(maintenant);
                    telephone.setNumTelephone(numeroTelephone);
                    telephone.setPays(paysTelephone);
                    telephone.setNatureTelephone(natureTelephone);
                    return telephone;
                }
            }
        }
        else {
            // MODE CREATION
            // On vérifie que le téléphone n'existe pas déjà dans la liste des téléphones de la personne
            final CritereRechercheTelephone critereTel = new CritereRechercheTelephone();
            critereTel.setIdsPersonnes(idsPersonnes);
            critereTel.setNumeroTelephone(numeroTelephone);
            critereTel.setIsSupprime(null);
            final List<Telephone> listeTelephones = telephoneDao.rechercherTelephoneParCritere(critereTel);
            if (listeTelephones != null && listeTelephones.size() > 0) {
                // Le téléphone existe déjà, on le récupère
                telephone = listeTelephones.get(0);

                // si le téléphone est de différente nature on lui change sa nature
                if (telephoneDto.getNature() != null
                    && (telephone.getNatureTelephone() == null || !telephone.getNatureTelephone().getId().equals(telephoneDto.getNature().getIdentifiant()))) {
                    telephone.setNatureTelephone(natureTelephone);
                    return telephone;
                }

                // Mantis 8288 On vérifie que la liste des personnes ratachées au numéro ne contient
                // que la personne sur laquelle on veux changer le numéro
                final List<String> listeNumerosPersonneFamilleConcernees = new ArrayList<String>();
                for (Personne personneTel : telephone.getPersonnes()) {
                    if (!idPersonneModifie.equals(personneTel.getId())) {
                        listeNumerosPersonneFamilleConcernees.add(personneTel.getNum());
                    }
                }
                if (listeNumerosPersonneFamilleConcernees.size() > 0 && !telephone.isSupprime()) {
                    String messageErreur =
                        "Certains membres de la famille possèdent déjà le numéro de téléphone " + telephone.getNumTelephone()
                            + ", veuillez d'abord leur supprimer ou bien ne pas demander à les impacter. Les numéros des personnes concernées sont : ";
                    final int nbNum = listeNumerosPersonneFamilleConcernees.size();
                    int compteur = 0;
                    for (String num : listeNumerosPersonneFamilleConcernees) {
                        compteur++;
                        messageErreur = messageErreur + num + (compteur == nbNum ? "." : ", ");
                    }
                    throw new BusinessException(messageErreur);
                }

                // On restaure le téléphone
                restaurerTelephone(telephone, nouvellesPersonnes, maintenant);

                return telephone;
            }
            else {
                // CREATION D'UN NOUVEAU TELEPHONE
                telephone = new Telephone();
                telephone.setDateCreation(maintenant);
                telephone.setNumTelephone(numeroTelephone);
                telephone.setPays(paysTelephone);
                telephone.setNatureTelephone(natureTelephone);
                if (telephoneDto.getIdext() != null) {
                    telephone.setIdentifiantExterieur(telephoneDto.getIdext());
                }
                return telephone;
            }
        }
    }

    private void controlerEmail(final RapportDto rapport, final EmailDto emailDto, final int index) {
        final ValidationExpressionProp[] propsEmail =
            new ValidationExpressionProp[] {
                new ValidationExpressionProp("natureEmail", index, null, messageSourceUtil
                        .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NATUREEMAIL_NULL)),
                new ValidationExpressionProp("adresse", index, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_EMAIL_NULL))};

        validationExpressionUtil.verifierSiVide(rapport, emailDto, propsEmail).verifierEmail(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_BAD_EMAIL),
            emailDto.getAdresse(), EmailDto.class.getSimpleName() + ".adresse" + (index > -1 ? "." + index : ""));
    }

    private Email creerOuMettreAJourEmail(Set<Personne> personnes, EmailDto emailDto, Boolean impacterFamille) {
        // Vérification des dépendances
        final EmailNature natureEmail = emailNatureDao.rechercheNatureEmailParId(emailDto.getNatureEmail().getIdentifiant());
        if (natureEmail == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_NATURE_EMAIL_INEXISTANT));
        }

        // On construit la liste des identifiants des persones de la famille
        final Set<Long> idsPersonnes = new LinkedHashSet<Long>();
        for (Personne personne : personnes) {
            idsPersonnes.add(personne.getId());
        }
        // On construit la liste des personnes associées au téléphone en cas de restauration
        final Set<Personne> nouvellesPersonnes = new LinkedHashSet<Personne>();
        for (Personne personne : personnes) {
            nouvellesPersonnes.add(personne);
            if (BooleanUtils.isFalse(impacterFamille)) {
                break;
            }
        }
        Email email;
        final Calendar maintenant = Calendar.getInstance();

        if (emailDto.getIdentifiant() != null) {
            // MODE MISE A JOUR
            // On récupère l'email à mettre à jour
            email = emailDao.rechercherEmailParId(emailDto.getIdentifiant());
            if (email == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_EMAIL_INEXISTENT_EN_BD));
            }
            // On vérifie que l'email n'existe pas déjà dans la liste des emails de la personne
            final CritereRechercheEmail criteres = new CritereRechercheEmail();
            criteres.setIdsPersonnes(idsPersonnes);
            criteres.setEmail(emailDto.getAdresse());
            criteres.setIsSupprime(null);
            criteres.getIdsEmailsExclus().add(emailDto.getIdentifiant());
            final List<Email> listeEmails = emailDao.rechercherEmailParCritere(criteres);
            if (listeEmails != null && listeEmails.size() > 0) {
                // Cet email existe déjà, on le récupère
                final Email emailExistant = listeEmails.get(0);
                // On le restaure
                restaurerEmail(emailExistant, nouvellesPersonnes, maintenant);
                // On supprime l'ancien email
                supprimerEmail(email, maintenant);
                return emailExistant;
            }
            else {
                // MISE A JOUR EMAIL EXISTANT
                return mettreAJourEmail(email, emailDto, natureEmail, maintenant);
            }
        }
        else {
            // MODE CREATION
            // On vérifie que le téléphone n'existe pas déjà dans la liste des téléphones de la personne
            final CritereRechercheEmail criteres = new CritereRechercheEmail();
            criteres.setIdsPersonnes(idsPersonnes);
            criteres.setEmail(emailDto.getAdresse());
            criteres.setIsSupprime(null);
            final List<Email> listeEmails = emailDao.rechercherEmailParCritere(criteres);
            if (listeEmails != null && listeEmails.size() > 0) {
                // Cet email existe déjà, on le récupère
                email = listeEmails.get(0);
                // On le restaure
                restaurerEmail(email, nouvellesPersonnes, maintenant);
                return email;
            }
            else {
                // CREATION NOUVEL EMAIL
                return creerEmail(emailDto, natureEmail, maintenant);
            }
        }
    }

    /**
     * Supprime le téléphone passé en paramètre.
     * @param telephone le téléphone à supprimer
     * @param dateSuppression la date de suppression
     * @return le téléphone supprimé
     */
    private Telephone supprimerTelephone(Telephone telephone, Calendar dateSuppression) {
        if (!telephone.isSupprime()) {
            telephone.setSupprime(true);
            telephone.setDateSuppression(dateSuppression);
        }
        return telephone;
    }

    /**
     * Restaure le téléphone passé en paramètre.
     * @param telephone le téléphone à restaurer
     * @param nouvellesPersonnes les personnes associées au téléphone à restaurer
     * @param dateRestauration la date de restauration
     * @return le téléphone restauré
     */
    private Telephone restaurerTelephone(Telephone telephone, Set<Personne> nouvellesPersonnes, Calendar dateRestauration) {
        telephone.setDateModification(dateRestauration);
        telephone.setSupprime(false);
        telephone.setDateSuppression(null);
        // On enlève liaisons obolètes entre les personnes et le téléphone avant de le restaurer
        final Set<Personne> personnes = new LinkedHashSet<Personne>(telephone.getPersonnes());
        personnes.removeAll(nouvellesPersonnes);
        for (Personne personne : personnes) {
            personne.removeTelephone(telephone);
        }
        // On ajoute le téléphone pour les nouvelles personnes
        for (Personne personne : nouvellesPersonnes) {
            personne.addTelephone(telephone);
        }
        return telephone;
    }

    /**
     * Supprime l'email passé en paramètre.
     * @param email l'email à supprimer
     * @param dateSuppression la date à laquelle l'email a été supprimé
     * @return l'email supprimé
     */
    private Email supprimerEmail(Email email, Calendar dateSuppression) {
        if (!email.isSupprime()) {
            email.setSupprime(true);
            email.setDateSuppression(dateSuppression);
        }
        return email;
    }

    /**
     * Restaure l'email supprimé passé en paramètre.
     * @param email l'email à restaurer
     * @param nouvellesPersonnes les personnes associées au téléphone à restaurer
     * @param dateRestauration date à laquelle l'email a été restauré
     */
    private Email restaurerEmail(Email email, Set<Personne> nouvellesPersonnes, Calendar dateRestauration) {
        email.setDateModification(dateRestauration);
        email.setSupprime(false);
        email.setDateSuppression(null);
        // On enlève liaisons obolètes entre les personnes et le téléphone avant de le restaurer
        final Set<Personne> personnes = new LinkedHashSet<Personne>(email.getPersonnes());
        personnes.removeAll(nouvellesPersonnes);
        for (Personne personne : personnes) {
            personne.removeEmail(email);
        }
        // On ajoute le téléphone pour les nouvelles personnes
        for (Personne personne : nouvellesPersonnes) {
            personne.addEMail(email);
        }
        return email;
    }

    /**
     * Créé un nouvel email.
     * @param emailACreer DTO contenant les informations de l'email à créer
     * @param natureEmail nature de l'email
     * @param dateCreation date à laquelle l'email a été créé
     * @return le nouvel email
     */
    private Email creerEmail(EmailDto emailACreer, EmailNature natureEmail, Calendar dateCreation) {
        final Email email = new Email();
        email.setDateCreation(dateCreation);
        email.setAdresse(emailACreer.getAdresse());
        email.setNature(natureEmail);
        if (emailACreer.getIdext() != null) {
            email.setIdentifiantExterieur(emailACreer.getIdext());
        }
        return email;
    }

    /**
     * Met à jour un email.
     * @param emailAMaj l'email à mettre à jour
     * @param emailMaj les informations de l'email pour la mise à jour
     * @param natureEmail la nature de l'email pour la mise à jour
     * @param dateMaj la date de la mise à jour
     * @return l'email mis à jour
     */
    private Email mettreAJourEmail(Email emailAMaj, EmailDto emailMaj, EmailNature natureEmail, Calendar dateMaj) {
        emailAMaj.setDateModification(dateMaj);
        emailAMaj.setAdresse(emailMaj.getAdresse());
        emailAMaj.setNature(natureEmail);
        return emailAMaj;
    }

    private void controlerAdresse(final RapportDto rapport, final AdresseDto adresseDto, final int index) {

        // Si aucun pays de communiqué on remonte une erreur
        if (adresseDto.getPays() == null) {
            final ValidationExpressionProp[] propsPays =
                new ValidationExpressionProp[] {new ValidationExpressionProp("pays", index, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_ADRESSE_PAYS_NULL)),
                    new ValidationExpressionProp("nomVoie", index, null, messageSourceUtil.get(MESSAGE_ERREUR_INSERTION_ADRESSE_MAUVAIS_NOMVOIE)),
                    new ValidationExpressionProp("dateDebut", index, null, messageSourceUtil.get(MESSAGE_ERREUR_ADRESSE_DATE_DEBUT)),
                    new ValidationExpressionProp("nature", index, null, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_NATURE_ADRESSE_NULL))};
            validationExpressionUtil.verifierSiVide(rapport, adresseDto, propsPays);
        }
        // Si un pays est communiqué on applique les règles en fonction du pays
        else {
            // Si le pays est la france, on applique les contrôles de la france
            if (squareMappingService.getIdPaysFrance().equals(adresseDto.getPays().getIdentifiant())) {
                final ValidationExpressionProp[] propsFrance =
                    new ValidationExpressionProp[] {
                        new ValidationExpressionProp("nomVoie", index, null, messageSourceUtil.get(MESSAGE_ERREUR_INSERTION_ADRESSE_MAUVAIS_NOMVOIE)),
                        new ValidationExpressionProp("dateDebut", index, null, messageSourceUtil.get(MESSAGE_ERREUR_ADRESSE_DATE_DEBUT)),
                        new ValidationExpressionProp("codePostalCommune", index, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_ADRESSE_CODEPOSTAL_NULL)),
                        new ValidationExpressionProp("nature", index, null, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_NATURE_ADRESSE_NULL))};
                validationExpressionUtil.verifierSiVide(rapport, adresseDto, propsFrance);
            }
            // Si le pays n'est pas la france on applique les contrôles étrangers
            else {
                final ValidationExpressionProp[] propsEtranger =
                    new ValidationExpressionProp[] {
                        new ValidationExpressionProp("nomVoie", index, null, messageSourceUtil.get(MESSAGE_ERREUR_INSERTION_ADRESSE_MAUVAIS_NOMVOIE)),
                        new ValidationExpressionProp("dateDebut", index, null, messageSourceUtil.get(MESSAGE_ERREUR_ADRESSE_DATE_DEBUT)),
                        new ValidationExpressionProp("nature", index, null, messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_NATURE_ADRESSE_NULL))};
                validationExpressionUtil.verifierSiVide(rapport, adresseDto, propsEtranger);
            }
        }

        // Vérification du format du numéro de voie
        if (adresseDto.getNumVoie() != null && !"".equals(adresseDto.getNumVoie())) {
            validationExpressionUtil.verifierFormatNumVoie(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_FORMAT_NUM_VOIE_INCORRECT), adresseDto
                    .getNumVoie(), AdresseDto.class.getSimpleName() + ".numVoie" + (index > -1 ? "." + index : ""));
        }

        // Vérification de la longueur de l'adresse
        validationExpressionUtil.verifierAdresse(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_ADRESSE_TROP_LONGUE), adresseDto, AdresseDto.class
                .getSimpleName()
            + ".nomVoie" + (index > -1 ? "." + index : ""));
    }

    private Adresse creerOuMettreAJourAdresse(Set<Personne> personnes, AdresseDto adresseDto, Boolean impacterFamille) {
        // Vérification des dépendances
        final Pays paysAdresse = paysDao.rechercherPaysParId(adresseDto.getPays().getIdentifiant());
        if (paysAdresse == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PAYS_INEXISTENT_EN_BD));
        }

        TypeVoie typeVoie = null;
        if (adresseDto.getTypeVoie() != null && adresseDto.getTypeVoie().getIdentifiant() != null) {
            typeVoie = typeVoieDao.rechercheTypeVoieParId(adresseDto.getTypeVoie().getIdentifiant());
            if (typeVoie == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_TYPEVOIE_INEXISTANT));
            }
        }
        AdresseNature nature = null;
        if (adresseDto.getNature() != null) {
            nature = adresseNatureDao.rechercheAdresseNatureParId(adresseDto.getNature().getIdentifiant());
            if (nature == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_NATURE_ADRESSE_INEXISTANT));
            }
        }

        CodePostalCommune codePostalCommune = null;
        if (adresseDto != null && squareMappingService.getIdPaysFrance().equals(adresseDto.getPays().getIdentifiant())) {
            if (adresseDto.getCodePostalCommune() != null && adresseDto.getCodePostalCommune().getIdentifiant() != null) {
                // On vérifie que l'adresse a un code postal - commune et que le code postal - commune existe en base de données.
                codePostalCommune = codePostalCommuneDao.rechercheCodePostalCommuneParId(adresseDto.getCodePostalCommune().getIdentifiant());
                if (codePostalCommune == null) {
                    throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_CODEPOSTAL_COMMUNE_INEXISTANT_EN_BD));
                }
            }
        }

        Adresse adresse;
        if (adresseDto.getIdentifiant() != null) {
            // MAJ ADRESSE EXISTANTE
            adresse = adresseDao.rechercherAdresseParId(adresseDto.getIdentifiant());
            if (adresse == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_INEXISTENT_EN_BD));
            }
            adresse.setDateModification(Calendar.getInstance());
        }
        else {
            // CREATION NOUVELLE ADRESSE
            adresse = new Adresse();
            adresse.setDateCreation(Calendar.getInstance());
        }

        // On détecte s'il y a modification du code postal de l'adresse principale pour modifier l'agence attribuée sur la personne
        Agence agence = null;
        if (squareMappingService.getIdNatureAdressePrincipale().equals(nature.getId())) {
            if (codePostalCommune != null && !codePostalCommune.equals(adresse.getCodePostalCommune())) {
                if (codePostalCommune.getCodePostal() != null && codePostalCommune.getCodePostal().getCodePostal() != null
                    && !"".equals(codePostalCommune.getCodePostal().getCodePostal().trim())
                    && codePostalCommune.getCodePostal().getCodePostal().trim().length() != 2) {
                    // on recupere le commercial suivant le code postal
                    agence = agenceHabilitationUtil.getAgenceByCodePostal(codePostalCommune.getCodePostal().getCodePostal().trim());
                }
            }
        }

        mapperDozerBean.map(adresseDto, adresse);
        adresse.setPays(paysAdresse);
        adresse.setTypeVoie(typeVoie);
        adresse.setNature(nature);
        adresse.setCodePostalCommune(codePostalCommune);

        if (adresse.getId() == null) {
            // On sauvegarde dans la base de données la nouvelle adresse
            adresseDao.ajouterAdresse(adresse);
            // On associe la nouvelle adresse aux personnes
            for (Personne personne : personnes) {
                personne.addAdresse(adresse);
                // Si il ne faut pas impacter la famille on n'ajoute l'adresse qu'à la personne principale
                if (BooleanUtils.isFalse(impacterFamille)) {
                    break;
                }
            }
        }

        if (agence != null) {
            for (Personne personne : personnes) {
                if (personne.getAttribution() == null) {
                    throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_ATTRIBUTION_INEXISTANTE));
                }
                personne.getAttribution().setAgence(agence);
                // Si il ne faut pas impacter la famille on ne met à jour seulement l'attribution de la personne principale
                if (BooleanUtils.isFalse(impacterFamille)) {
                    break;
                }
            }
        }
        return adresse;
    }

    /**
     * Supprime l'adresse passée en paramètre.
     * @param adresse l'adresse à supprimer
     * @param dateSuppression la date de suppression
     * @return l'adresse supprimée
     */
    private Adresse supprimerAdresse(Adresse adresse, Calendar dateSuppression) {
        if (!adresse.isSupprime()) {
            adresse.setSupprime(true);
            adresse.setDateSuppression(dateSuppression);
        }
        return adresse;
    }

    @Override
    public PersonneBaseDto rechercherPersonneParId(Long id) {
        final Personne personne = personneDao.rechercherPersonneParId(id);
        if (personne instanceof PersonnePhysique) {
            return mapperDozerBean.map(personne, PersonneDto.class);
        }
        else {
            return mapperDozerBean.map(personne, PersonneMoraleDto.class);
        }
    }

    @Override
    public void supprimerPersonne(Long idPersonne) {
        logger.debug("Suppression de la personne " + idPersonne);
        if (idPersonne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_SUPPRESSION_PERSONNE_NULL));
        }
        // Récupération de la personne
        final Personne personne = personneDao.rechercherPersonneParId(idPersonne);
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_SUPPRESSION_PERSONNE_INEXISTANTE));
        }
        // Mise à jour du flag "Supprime" et de la date de suppression
        personne.setSupprime(true);
        personne.setDateSuppression(Calendar.getInstance());
    }

    @Override
    public void transfererPersonneACommercial(Long idPersonne, Long idCommercial) {
        logger.debug("Transfert de la personne " + idPersonne + " au commercial " + idCommercial);
        if (idPersonne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_PERSONNE_NULL));
        }
        if (idCommercial == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_COMMERCIAL_NULL));
        }
        // Récupération de la personne
        final Personne personne = personneDao.rechercherPersonneParId(idPersonne);
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_PERSONNE_INEXISTANTE));
        }
        // Récupération de la ressource
        final Ressource commercial = ressourceDao.rechercherRessourceParId(idCommercial);
        if (commercial == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_COMMERCIAL_INEXISTANT));
        }
        // Modification de l'attribution de la personne
        personne.getAttribution().setRessource(commercial);
        // Modification de l'agence si nécessaire
        if (personne.getAttribution().getAgence() == null && commercial.getAgence() != null) {
            personne.getAttribution().setAgence(commercial.getAgence());
        }
        personne.setDateModification(Calendar.getInstance());
    }

    @Override
    public void transfererPersonneAAgence(Long idPersonne, Long idAgence) {
        logger.debug("Transfert de la personne " + idPersonne + " à l'agence " + idAgence);
        if (idPersonne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_PERSONNE_NULL));
        }
        if (idAgence == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_AGENCE_NULL));
        }
        // Récupération de la personne
        final Personne personne = personneDao.rechercherPersonneParId(idPersonne);
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_PERSONNE_INEXISTANTE));
        }
        // Récupération de l'agence
        final Agence agence = agenceDao.rechercheAgenceParId(idAgence);
        if (agence == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_AGENCE_INEXISTANTE));
        }
        // Modification de l'agence attribuée à la personne
        personne.getAttribution().setAgence(agence);
        personne.setDateModification(Calendar.getInstance());
    }

    @Override
    public void transfererRelations(Long idPersonneSource, Long idPersonneCible) {
        logger.debug("Transfert des relations de la personne source " + idPersonneSource + " vers la personne cible " + idPersonneCible);
        if (idPersonneSource == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_RELATIONS_PERSONNE_SOURCE_NULL));
        }
        if (idPersonneCible == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_RELATIONS_PERSONNE_CIBLE_NULL));
        }

        // Récupération de la personne cible
        final Personne personneCible = personneDao.rechercherPersonneParId(idPersonneCible);
        if (personneCible == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_TRANSFERT_RELATIONS_PERSONNE_CIBLE_INEXISTANTE));
        }

        // Recherche des relations dont la source est la personne source
        final RelationCriteresRechercheDto critereRelationSource = new RelationCriteresRechercheDto();
        critereRelationSource.setIdPersonneSource(idPersonneSource);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteriasRelationSource =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(critereRelationSource, 0, Integer.MAX_VALUE);
        final List<Relation> listeRelationsSource = relationDao.rechercherRelationsParCriteres(criteriasRelationSource);
        // Modification de la source de la relation par la personne cible
        if (listeRelationsSource != null && !listeRelationsSource.isEmpty()) {
            for (Relation relationSource : listeRelationsSource) {
                // Vérification que la futur relation n'existe pas déjà
                final RelationCriteresRechercheDto critereRelationExistante = new RelationCriteresRechercheDto();
                critereRelationExistante.setIdPersonneSource(personneCible.getId());
                critereRelationExistante.setIdPersonneCible(relationSource.getPersonneCible().getId());
                final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteriasRelationExistante =
                    new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(critereRelationExistante, 0, Integer.MAX_VALUE);
                final List<Relation> listeRelationsExistante = relationDao.rechercherRelationsParCriteres(criteriasRelationExistante);
                // Si la futur relation existe déjà, alors on la supprime logiquement, sinon, on la modifie
                if (listeRelationsExistante != null && listeRelationsExistante.size() > 0) {
                    relationSource.setSupprime(true);
                    relationSource.setDateFin(Calendar.getInstance());
                }
                else {
                    relationSource.setPersonneSource(personneCible);
                    relationSource.setDateMaj(Calendar.getInstance());
                    relationSource.setDateModification(Calendar.getInstance());
                }
            }
        }

        // Recherche des relations dont la cible est la personne source
        final RelationCriteresRechercheDto critereRelationCible = new RelationCriteresRechercheDto();
        critereRelationCible.setIdPersonneCible(idPersonneSource);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteriasRelationsCible =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(critereRelationCible, 0, Integer.MAX_VALUE);
        final List<Relation> listeRelationsCible = relationDao.rechercherRelationsParCriteres(criteriasRelationsCible);
        // Modification de la cible de la relation par la personne cible
        if (listeRelationsCible != null && !listeRelationsCible.isEmpty()) {
            for (Relation relationCible : listeRelationsCible) {
                // Vérification que la futur relation n'existe pas déjà
                final RelationCriteresRechercheDto critereRelationExistante = new RelationCriteresRechercheDto();
                critereRelationExistante.setIdPersonneSource(relationCible.getPersonneSource().getId());
                critereRelationExistante.setIdPersonneCible(personneCible.getId());
                final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteriasRelationExistante =
                    new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(critereRelationExistante, 0, Integer.MAX_VALUE);
                final List<Relation> listeRelationsExistante = relationDao.rechercherRelationsParCriteres(criteriasRelationExistante);
                // Si la futur relation existe déjà, alors on la supprime logiquement, sinon, on la modifie
                if (listeRelationsExistante != null && listeRelationsExistante.size() > 0) {
                    relationCible.setSupprime(true);
                    relationCible.setDateFin(Calendar.getInstance());
                }
                else {
                    relationCible.setPersonneCible(personneCible);
                    relationCible.setDateMaj(Calendar.getInstance());
                    relationCible.setDateModification(Calendar.getInstance());
                }
            }
        }
    }

    @Override
    public int countRelationsParCriteres(RelationCriteresRechercheDto criteres) {
        return relationDao.countRelationsParCriteres(criteres);
    }

    @Override
    public void mettreAJourCoordonneesExtId(CoordonneesDto coordonnees) {
        if (coordonnees.getTelephones() != null && !coordonnees.getTelephones().isEmpty()) {
            for (TelephoneDto telephoneDto : coordonnees.getTelephones()) {
                final Telephone telephone = telephoneDao.rechercherTelephoneParId(telephoneDto.getId());
                telephone.setIdentifiantExterieur(telephoneDto.getIdext());
                telephone.setPorteurUid(telephoneDto.getPorteurUid());
            }
        }
        if (coordonnees.getEmails() != null && !coordonnees.getEmails().isEmpty()) {
            for (EmailDto emailDto : coordonnees.getEmails()) {
                final Email email = emailDao.rechercherEmailParId(emailDto.getIdentifiant());
                email.setIdentifiantExterieur(emailDto.getIdext());
                email.setPorteurUid(emailDto.getPorteurUid());
            }
        }
        if (coordonnees.getAdresses() != null && !coordonnees.getAdresses().isEmpty()) {
            for (AdresseDto adresseDto : coordonnees.getAdresses()) {
                final Adresse adresse = adresseDao.rechercherAdresseParId(adresseDto.getIdentifiant());
                adresse.setIdentifiantExterieur(adresseDto.getIdext());
                adresse.setPorteurUid(adresseDto.getPorteurUid());
            }
        }
    }

    @Override
    public AdresseCreationDto ajouterNouvelleAdresse(Long idPersonne, AdresseDto adresseDto, Boolean impacterFamille) {
        final RapportDto rapport = new RapportDto();
        if (adresseDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_DTO_NULL));
        }
        // Récupération de la personne en base.
        final Personne personnePrincipale = personneDao.rechercherPersonneParId(idPersonne);
        if (personnePrincipale == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }
        // Recherche de l'adresse principale actuelle
        final AdresseCriteresRechercheDto criteresRechercheAdressePrincipale = new AdresseCriteresRechercheDto();
        criteresRechercheAdressePrincipale.setIdPersonne(personnePrincipale.getId());
        criteresRechercheAdressePrincipale.setIdNature(squareMappingService.getIdNatureAdressePrincipale());
        criteresRechercheAdressePrincipale.setActive(true);
        final List<Adresse> listAdressesPrincipales = adresseDao.rechercherAdresseParCritere(criteresRechercheAdressePrincipale);
        Adresse adressePrincipaleEnCours = new Adresse();
        boolean plusQueAdressePrincipale = false;
        boolean choixPasserEnSecondaire = false;
        if (listAdressesPrincipales != null && listAdressesPrincipales.size() > 0) {
            adressePrincipaleEnCours = listAdressesPrincipales.get(0);
        }
        final Long idNatureAdressePrincipale = squareMappingService.getIdNatureAdressePrincipale();
        final Long idNatureAdresseSecondaire = squareMappingService.getIdNatureAdresseSecondaire();
        final AdresseNature natureAdressePrincipale = adresseNatureDao.rechercheAdresseNatureParId(idNatureAdressePrincipale);
        final AdresseNature natureAdresseSecondaire = adresseNatureDao.rechercheAdresseNatureParId(idNatureAdresseSecondaire);
        // Si l'adresse à ajouter est une adresse principale et est différente de l'adresse principale actuelle
        if (adresseDto.getNature() != null && adresseDto.getNature().getIdentifiant().equals(idNatureAdressePrincipale)
            && adressePrincipaleEnCours.getId() != null && !adressePrincipaleEnCours.getId().equals(adresseDto.getIdentifiant())) {
            // Est ce que l'adresse à ajouter doit remplacer l'adresse principale actuelle?
            choixPasserEnSecondaire = BooleanUtils.isTrue(adresseDto.getChoixPasserEnSecondaire());
            plusQueAdressePrincipale = true;
        }
        // Si aucune date de début n'est renseignée pour l'adresse
        if (adresseDto.getDateDebut() == null) {
            // On initialise la date de début de l'adresse à la date courante
            adresseDto.setDateDebut(Calendar.getInstance());
        }
        // Vérification des champs obligatoires de l'adresse à ajouter
        controlerAdresse(rapport, adresseDto, 0);
        if (Boolean.TRUE.equals(rapport.getEnErreur())) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }
        // On recherche les membres de la famille de la personne
        final Set<Personne> famille = getFamille(personnePrincipale);
        // Si le flag qui indique si l'on doit ajouter la nouvelle adresse aux bénéficiaires n'est pas spécifié
        // Si la personne a une famille, on lève une exception car le flag impacterBeneficiaires doit être renseigné
        // pour déterminer si il faut ajouter l'adresse aux membres de la famille de la personne
        if (impacterFamille == null && famille.size() > 0) {
            throw new ConfirmationImpacterFamilleException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.CONFIRMATION_IMPACTER_FAMILLE, new String[] {String
                    .valueOf(famille.size())}));
        }
        final AdresseCreationDto adresseCree = new AdresseCreationDto();
        final Set<Personne> personnes = new LinkedHashSet<Personne>();
        personnes.add(personnePrincipale);
        if (BooleanUtils.isTrue(impacterFamille)) {
            // On ajoute la famille
            personnes.addAll(famille);
        }
        adresseCree.setIdAdressesModifiees(new ArrayList<Long>());
        final Adresse adresse = creerOuMettreAJourAdresse(personnes, adresseDto, impacterFamille);
        if (adresseDto.getIdentifiant() == null) {
            adresseCree.setIdAdresseCree(adresse.getId());
        }
        // si le rapport n'est pas en erreur, on met à jour l'adresse principale
        if (plusQueAdressePrincipale) {
            if (choixPasserEnSecondaire) {
                // On passe l'adresse principale actuelle en adresse secondaire
                adressePrincipaleEnCours.setNature(natureAdresseSecondaire);
            }
            else {
                // On remplace l'adresse principale actuelle par l'adresse à ajouter
                // On met donc fin à l'adresse principale actuelle en spécifiant la date de fin à J-1 de la date de début de la nouvelle adresse
                final Calendar dateFinAdressePrincipale = adresseDto.getDateDebut();
                dateFinAdressePrincipale.add(Calendar.DAY_OF_MONTH, -1);
                adressePrincipaleEnCours.setDateFin(dateFinAdressePrincipale);
                // Si la date de fin de l'action principal est inferieur à la date de debut
                if (adressePrincipaleEnCours.getDateFin().compareTo(adressePrincipaleEnCours.getDateDebut()) < 0
                    && dateFinAdressePrincipale.get(Calendar.DAY_OF_MONTH) < adressePrincipaleEnCours.getDateDebut().get(Calendar.DAY_OF_MONTH)) {
                    adressePrincipaleEnCours.getDateFin().add(Calendar.DAY_OF_MONTH, +1);
                }
            }
            adresseCree.getIdAdressesModifiees().add(adressePrincipaleEnCours.getId());
        }

        /*
         * // Un contact devra avoir au moins une adresse principal criteresRechercheAdressePrincipale = new AdresseCriteresRechercheDto();
         * criteresRechercheAdressePrincipale.setIdPersonne(personne.getId());
         * criteresRechercheAdressePrincipale.setIdNature(squareMappingService.getIdNatureAdressePrincipale()); long nbPrimaires =
         * adresseDao.rechercherIdAdressesParCriteres(criteresRechercheAdressePrincipale).size(); if (nbPrimaires == 0) { throw new
         * BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_AUCUNE_ADRESSE_PRINCIPALE)); } else if (nbPrimaires > 1) { throw new
         * BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_PRINCIPALE_DUPLIQUEE)); }
         */

        // 2.13 Une date de fin ne pourra etre mise sur une adresse principale que s'il existe une adresse secondaire.
        // Dans ce cas l'adresse principal deviendra automatiquement secondaire et l'adresse secondaire deviendra principale
        Adresse adressePrincipale = null;
        Adresse adresseSecondaire = null;
        for (Adresse adresseParcourue : personnePrincipale.getAdresses()) {
            if (adresseParcourue.getNature().getId().equals(idNatureAdressePrincipale)) {
                adressePrincipale = adresseParcourue;
            }
            if (adresseParcourue.getNature().getId().equals(idNatureAdresseSecondaire) && adresseParcourue.getDateFin() == null) {
                adresseSecondaire = adresseParcourue;
            }
        }
        if (adressePrincipale != null && adressePrincipale.getDateFin() != null && adresseSecondaire != null) {
            // Changement adresse principale adresse secondaire
            adressePrincipale.setNature(natureAdresseSecondaire);
            adresseSecondaire.setNature(natureAdressePrincipale);
            // Enregistrement des adresses
            final AdresseDto adresseDtoPrincipal = mapperDozerBean.map(adressePrincipale, AdresseDto.class);
            final AdresseDto adresseDtoSecondaire = mapperDozerBean.map(adresseSecondaire, AdresseDto.class);
            creerOuMettreAJourAdresse(personnes, adresseDtoPrincipal, impacterFamille);
            creerOuMettreAJourAdresse(personnes, adresseDtoSecondaire, impacterFamille);
            if (!adresseCree.getIdAdressesModifiees().contains(adressePrincipale.getId())) {
                adresseCree.getIdAdressesModifiees().add(adressePrincipale.getId());
            }
            if (!adresseCree.getIdAdressesModifiees().contains(adresseSecondaire.getId())) {
                adresseCree.getIdAdressesModifiees().add(adresseSecondaire.getId());
            }

        }

        controlerPersonneAUneAdressePrincipale(personnePrincipale);

        // si il s'agit d'une personne physique et vivier ou bénéficiaire vivier, on transforme en prospect ou bénéficiaire prospect
        final PersonnePhysique personnePhysique = personnePhysiqueDao.rechercherPersonneParId(idPersonne);
        boolean vivierToProspect = false;
        boolean hasNaturePersonneChanged = false;
        Long idNaturePersonnePhysique = null;
        final Long idNaturePersonneVivier = squareMappingService.getIdNaturePersonneVivier();
        final Long idNaturePersonneBeneficiaireVivier = squareMappingService.getIdNaturePersonneBeneficiaireVivier();
        String ancienneNaturePersonne = "";
        String nouvelleNaturePersonne = "";
        if (personnePhysique != null && personnePhysique.getNature() != null) {
            if (idNaturePersonneVivier.equals(personnePhysique.getNature().getId()) && validationPersonneUtil.verifierContrainteProspect(personnePhysique)) {
                idNaturePersonnePhysique = squareMappingService.getIdNaturePersonneProspect();
                vivierToProspect = true;
                hasNaturePersonneChanged = true;
                ancienneNaturePersonne = personnePhysique.getNature().getLibelle();
            }
            else if (idNaturePersonneBeneficiaireVivier.equals(personnePhysique.getNature())
                && validationPersonneUtil.verifierContrainteBeneficiaireProspect(personnePhysique)) {
                idNaturePersonnePhysique = squareMappingService.getIdNaturePersonneBeneficiaireProspect();
                hasNaturePersonneChanged = true;
                ancienneNaturePersonne = personnePhysique.getNature().getLibelle();
            }
            if (idNaturePersonnePhysique != null) {
                final PersonnePhysiqueNature naturePersonne = personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(idNaturePersonnePhysique);
                if (naturePersonne != null) {
                    nouvelleNaturePersonne = naturePersonne.getLibelle();
                }
                personnePhysique.setNature(naturePersonne);
            }
        }

        // si la personne est passée de vivier à prospect et qu'elle a des bénéficiaires vivier,
        // on essaye de les passer en bénéficiaire prospect si c'est possible
        if (vivierToProspect && famille != null && famille.size() > 0) {
            for (Personne beneficiaire : famille) {
                final PersonnePhysique beneficiairePhysique = (PersonnePhysique) beneficiaire;
                if (beneficiairePhysique.getNature() != null && idNaturePersonneBeneficiaireVivier.equals(beneficiairePhysique.getNature().getId())
                    && validationPersonneUtil.verifierContrainteBeneficiaireProspect(beneficiairePhysique)) {
                    final PersonnePhysiqueNature naturePersonne =
                        personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(squareMappingService.getIdNaturePersonneBeneficiaireProspect());
                    beneficiairePhysique.setNature(naturePersonne);
                }
            }
        }

        adresseCree.setHasNaturePersonneChanged(hasNaturePersonneChanged);
        adresseCree.setAncienneNaturePersonne(ancienneNaturePersonne);
        adresseCree.setNouvelleNaturePersonne(nouvelleNaturePersonne);
        return adresseCree;
    }

    @Override
    public TelephoneDto creerOuMettreAJourTelephone(Long idPersonne, TelephoneDto telephoneDto, Boolean impacterFamille, Boolean forcerDesactivationEservices) {
        final RapportDto rapport = new RapportDto();

        if (idPersonne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL));
        }

        // Récupération de la personne en base.
        final Personne personnePrincipale = personneDao.rechercherPersonneParId(idPersonne);
        if (personnePrincipale == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }

        final Set<Personne> personnes = new LinkedHashSet<Personne>();
        // On recherche les membres de la famille de la personne
        final Set<Personne> famille = getFamille(personnePrincipale);
        // Si le flag qui indique si l'on doit ajouter la nouvelle adresse aux bénéficiaires n'est pas spécifié
        // Si la personne a une famille, on lève une exception car le flag impacterBeneficiaires doit être renseigné
        // pour déterminer si il faut ajouter l'adresse aux membres de la famille de la personne
        if (impacterFamille == null && famille.size() > 0) {
            throw new ConfirmationImpacterFamilleException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.CONFIRMATION_IMPACTER_FAMILLE, new String[] {String
                    .valueOf(famille.size())}));
        }
        personnes.add(personnePrincipale);
        if (BooleanUtils.isTrue(impacterFamille)) {
            // On ajoute les membres de la famille à la liste des personnes concernées par la création de nouvelles coordonnées
            personnes.addAll(famille);
        }
        final Calendar maintenant = Calendar.getInstance();

        // Traitement du téléphone
        final boolean numeroRenseigne = StringUtils.isNotBlank(telephoneDto.getNumero());
        boolean isTelephoneModifie = false;
        if (telephoneDto.getId() != null) {
            // On récupère le téléphone à mettre à jour à partir de la base de données
            final Telephone telephoneAMaj = telephoneDao.rechercherTelephoneParId(telephoneDto.getId());
            // Si on recoit un id ici, mais que le numéro n'est pas renseigné, il faut supprimer le téléphone correspondant
            // Note : on ne supprime pas tous les téléphones car d'autres peuvent exister mais ne pas être affichés dans Square
            if (!numeroRenseigne) {
                // Si il faut impacter toute la famille, ou que le téléphone est lié à la personne principale uniquement
                if (BooleanUtils.isTrue(impacterFamille) || telephoneAMaj.getPersonnes().size() == 1) {
                    // On supprime le téléphone
                    supprimerTelephone(telephoneAMaj, maintenant);
                }
                else {
                    // On enlève le téléphone de la liste des téléphones de la personne principale
                    personnePrincipale.removeTelephone(telephoneAMaj);
                    // on verifie si la personne est le porteur
                    if (telephoneAMaj.getPorteurUid() != null && telephoneAMaj.getPorteurUid().equals(personnePrincipale.getId())) {
                        // on supprime l'identifiant externe et le porteur du telephone restant sur la famille
                        telephoneAMaj.setIdentifiantExterieur(null);
                        telephoneAMaj.setPorteurUid(null);
                    }
                }
                if (personnePrincipale instanceof PersonnePhysique
                    && ((PersonnePhysique) personnePrincipale).getNature().getId().equals(squareMappingService.getIdNaturePersonneAdherent())) {
                    // on verifie si la personne possede l'eservice
                    final List<Long> optionsSouscrites = adherentService.getListeIdsTypesOptionsSouscrites(personnePrincipale.getId());
                    boolean possedeEserviceTelephone = false;
                    for (Long idTypeOption : optionsSouscrites) {
                        if (idTypeOption.equals(adherentMappingService.getIdTypeOptionEnvoiSms())) {
                            possedeEserviceTelephone = true;
                            break;
                        }
                    }

                    if (possedeEserviceTelephone && !BooleanUtils.isTrue(forcerDesactivationEservices)) {
                        throw new ConfirmationDesactivationEserviceException(messageSourceUtil
                                .get(PersonneKeyUtil.MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_TELEPHONE));
                    }
                    else if (possedeEserviceTelephone) {
                        adherentService.desactiverOptionsEnvoiParTelephone(personnePrincipale.getId());
                    }
                }
            }
            else {
                // Sinon on détermine si le téléphone a été modifié
                isTelephoneModifie = isTelephoneModifie(telephoneAMaj, telephoneDto);
                // Si le téléphone existe et a été modifié, et qu'il ne faut pas impacter la famille
                if (BooleanUtils.isFalse(impacterFamille) && isTelephoneModifie && telephoneAMaj.getPersonnes().size() > 1) {
                    // on supprime le telephone de la personne
                    personnePrincipale.removeTelephone(telephoneAMaj);
                    // on supprime l'identifiant pour créer un nouveau telephone
                    telephoneDto.setId(null);
                    // on verifie si la personne est le porteur
                    if (telephoneAMaj.getPorteurUid() != null && telephoneAMaj.getPorteurUid().equals(personnePrincipale.getId())) {
                        // on supprime l'identifiant externe et le porteur du telephone restant sur la famille (pas de synchro aia)
                        telephoneAMaj.setIdentifiantExterieur(null);
                        telephoneAMaj.setPorteurUid(null);
                    }
                    else {
                        // On supprime l'identifiant externe afin de ne pas synchroniser avec aia
                        telephoneDto.setIdext(null);
                    }
                }
            }
        }
        if (numeroRenseigne) {
            controlerTelephone(rapport, telephoneDto, 0);
            if (!rapport.getEnErreur()) {
                final Telephone telephone = creerOuMettreAJourTelephone(personnes, telephoneDto, impacterFamille);
                if (telephone.getId() == null) {
                    // On rattache le téléphone à la personne principale
                    personnePrincipale.addTelephone(telephone);
                    if (BooleanUtils.isTrue(impacterFamille)) {
                        // On rattache aussi le téléphone aux membres de sa famille
                        for (Personne personne : famille) {
                            personne.addTelephone(telephone);
                        }
                    }
                }
            }
        }

        if (Boolean.TRUE.equals(rapport.getEnErreur())) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }

        // si il s'agit d'une personne physique et vivier ou bénéficiaire vivier, on transforme en prospect ou bénéficiaire prospect
        final PersonnePhysique personnePhysique = personnePhysiqueDao.rechercherPersonneParId(personnePrincipale.getId());
        boolean vivierToProspect = false;
        Long idNaturePersonnePhysique = null;
        final Long idNaturePersonneVivier = squareMappingService.getIdNaturePersonneVivier();
        final Long idNaturePersonneBeneficiaireVivier = squareMappingService.getIdNaturePersonneBeneficiaireVivier();
        if (personnePhysique != null && personnePhysique.getNature() != null) {
            if (idNaturePersonneVivier.equals(personnePhysique.getNature().getId()) && validationPersonneUtil.verifierContrainteProspect(personnePhysique)) {
                idNaturePersonnePhysique = squareMappingService.getIdNaturePersonneProspect();
                vivierToProspect = true;
            }
            else if (idNaturePersonneBeneficiaireVivier.equals(personnePhysique.getNature())
                && validationPersonneUtil.verifierContrainteBeneficiaireProspect(personnePhysique)) {
                idNaturePersonnePhysique = squareMappingService.getIdNaturePersonneBeneficiaireProspect();
            }
            if (idNaturePersonnePhysique != null) {
                final PersonnePhysiqueNature naturePersonne = personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(idNaturePersonnePhysique);
                personnePhysique.setNature(naturePersonne);
            }
        }

        // si la personne est passée de vivier à prospect et qu'elle a des bénéficiaires vivier,
        // on essaye de les passer en bénéficiaire prospect si c'est possible
        if (vivierToProspect && famille != null && famille.size() > 0) {
            for (Personne beneficiaire : famille) {
                final PersonnePhysique beneficiairePhysique = (PersonnePhysique) beneficiaire;
                if (beneficiairePhysique.getNature() != null && idNaturePersonneBeneficiaireVivier.equals(beneficiairePhysique.getNature().getId())
                    && validationPersonneUtil.verifierContrainteBeneficiaireProspect(beneficiairePhysique)) {
                    final PersonnePhysiqueNature naturePersonne =
                        personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(squareMappingService.getIdNaturePersonneBeneficiaireProspect());
                    beneficiairePhysique.setNature(naturePersonne);
                }
            }
        }

        // Récupération du téléphone créé ou modifié pour le retourner
        final CritereRechercheTelephone critereTel = new CritereRechercheTelephone();
        final Set<Long> idsPersonnes = new HashSet<Long>();
        idsPersonnes.add(idPersonne);
        critereTel.setIdsPersonnes(idsPersonnes);
        final List<TelephoneDto> telephones = mapperDozerBean.mapList(telephoneDao.rechercherTelephoneParCritere(critereTel), TelephoneDto.class);
        for (TelephoneDto telephoneTrouve : telephones) {
            if (telephoneTrouve.getNumero().equals(telephoneDto.getNumero())) {
                return telephoneTrouve;
            }
        }

        // Si téléphone non trouvé, on retourne un téléphone vide
        final TelephoneDto telephoneVide = new TelephoneDto();
        return telephoneVide;
    }

    @Override
    public EmailDto creerOuMettreAJourEmail(Long idPersonne, EmailDto emailDto, Boolean impacterFamille, Boolean forcerDesactivationEservices) {
        final RapportDto rapport = new RapportDto();

        if (idPersonne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL));
        }

        // Récupération de la personne en base.
        final Personne personnePrincipale = personneDao.rechercherPersonneParId(idPersonne);
        if (personnePrincipale == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }

        final Set<Personne> personnes = new LinkedHashSet<Personne>();
        // On recherche les membres de la famille de la personne
        final Set<Personne> famille = getFamille(personnePrincipale);
        // Si le flag qui indique si l'on doit ajouter la nouvelle adresse aux bénéficiaires n'est pas spécifié
        // Si la personne a une famille, on lève une exception car le flag impacterBeneficiaires doit être renseigné
        // pour déterminer si il faut ajouter l'adresse aux membres de la famille de la personne
        if (impacterFamille == null && famille.size() > 0) {
            throw new ConfirmationImpacterFamilleException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.CONFIRMATION_IMPACTER_FAMILLE, new String[] {String
                    .valueOf(famille.size())}));
        }
        personnes.add(personnePrincipale);
        if (BooleanUtils.isTrue(impacterFamille)) {
            // On ajoute les membres de la famille à la liste des personnes concernées par la création de nouvelles coordonnées
            personnes.addAll(famille);
        }
        final Calendar maintenant = Calendar.getInstance();

        // Traitement de l'email
        final boolean adresseEmailRenseignee = StringUtils.isNotBlank(emailDto.getAdresse());
        boolean isEmailModifie = false;

        if (emailDto.getIdentifiant() != null) {
            // On récupère l'email à mettre à jour à partir de la base de données
            final Email emailAMaj = emailDao.rechercherEmailParId(emailDto.getIdentifiant());
            // Si on recoit un id ici, mais que les infos ne sont pas renseignées, il faut supprimer le téléphone correspondant
            // Note : on ne supprime pas tous les téléphones car d'autres peuvent exister mais ne pas être affichés dans Square
            if (!adresseEmailRenseignee) {
                // Si il faut impacter toute la famille, ou que l'email est lié à la personne principale uniquement
                if (BooleanUtils.isTrue(impacterFamille) || emailAMaj.getPersonnes().size() == 1) {
                    // On supprime l'email
                    supprimerEmail(emailAMaj, maintenant);
                }
                else {
                    // On enlève le téléphone de la liste des téléphones de la personne principale
                    personnePrincipale.removeEmail(emailAMaj);
                    // on verifie si la personne est le porteur
                    if (emailAMaj.getPorteurUid() != null && emailAMaj.getPorteurUid().equals(personnePrincipale.getId())) {
                        // on supprime l'identifiant externe et le porteur de l'email restant sur la famille
                        emailAMaj.setIdentifiantExterieur(null);
                        emailAMaj.setPorteurUid(null);
                    }
                }
                if (personnePrincipale instanceof PersonnePhysique
                    && ((PersonnePhysique) personnePrincipale).getNature().getId().equals(squareMappingService.getIdNaturePersonneAdherent())) {
                    // on verifie si la personne possede l'eservice
                    final List<Long> optionsSouscrites = adherentService.getListeIdsTypesOptionsSouscrites(personnePrincipale.getId());
                    boolean possedeEserviceTelephone = false;
                    for (Long idTypeOption : optionsSouscrites) {
                        if (idTypeOption.equals(adherentMappingService.getIdTypeOptionEnvoiMutuellementEmail())
                            || idTypeOption.equals(adherentMappingService.getIdTypeOptionEnvoiRelevesPrestationEmail())) {
                            possedeEserviceTelephone = true;
                            break;
                        }
                    }

                    if (possedeEserviceTelephone && !BooleanUtils.isTrue(forcerDesactivationEservices)) {
                        throw new ConfirmationDesactivationEserviceException(messageSourceUtil
                                .get(PersonneKeyUtil.MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_EMAIL));
                    }
                    else if (possedeEserviceTelephone) {
                        adherentService.desactiverOptionsEnvoiParEmail(personnePrincipale.getId());
                    }
                }
            }
            else {
                // Sinon on détermine si l'email a été modifié
                isEmailModifie = isEmailModifie(emailAMaj, emailDto);
                // Si le téléphone existe et a été modifié, et qu'il ne faut pas impacter la famille
                if (BooleanUtils.isFalse(impacterFamille) && isEmailModifie && emailAMaj.getPersonnes().size() > 1) {
                    // on supprime l'email de la personne
                    personnePrincipale.removeEmail(emailAMaj);
                    // on supprime l'identifiant pour créer un nouvel email
                    emailDto.setIdentifiant(null);
                    // on verifie si la personne est le porteur
                    if (emailAMaj.getPorteurUid() != null && emailAMaj.getPorteurUid().equals(personnePrincipale.getId())) {
                        // on supprime l'identifiant externe et le porteur de l'email restant sur la famille (pas de synchro aia)
                        emailAMaj.setIdentifiantExterieur(null);
                        emailAMaj.setPorteurUid(null);
                    }
                    else {
                        // On supprime l'identifiant externe afin de ne pas synchroniser avec aia
                        emailDto.setIdext(null);
                    }
                }
            }
        }
        if (adresseEmailRenseignee) {
            controlerEmail(rapport, emailDto, 0);
            if (!rapport.getEnErreur()) {
                final Email email = creerOuMettreAJourEmail(personnes, emailDto, impacterFamille);
                // Si c'est un nouvel email
                if (email.getId() == null) {
                    // On rattache le nouvel email à la personne principale
                    personnePrincipale.addEMail(email);
                    if (BooleanUtils.isTrue(impacterFamille)) {
                        // On rattache aussi le nouvel email aux membres de sa famille
                        for (Personne personne : famille) {
                            personne.addEMail(email);
                        }
                    }
                }
            }
        }

        if (Boolean.TRUE.equals(rapport.getEnErreur())) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }

        // si il s'agit d'une personne physique et vivier ou bénéficiaire vivier, on transforme en prospect ou bénéficiaire prospect
        final PersonnePhysique personnePhysique = personnePhysiqueDao.rechercherPersonneParId(personnePrincipale.getId());
        boolean vivierToProspect = false;
        Long idNaturePersonnePhysique = null;
        final Long idNaturePersonneVivier = squareMappingService.getIdNaturePersonneVivier();
        final Long idNaturePersonneBeneficiaireVivier = squareMappingService.getIdNaturePersonneBeneficiaireVivier();
        if (personnePhysique != null && personnePhysique.getNature() != null) {
            if (idNaturePersonneVivier.equals(personnePhysique.getNature().getId()) && validationPersonneUtil.verifierContrainteProspect(personnePhysique)) {
                idNaturePersonnePhysique = squareMappingService.getIdNaturePersonneProspect();
                vivierToProspect = true;
            }
            else if (idNaturePersonneBeneficiaireVivier.equals(personnePhysique.getNature())
                && validationPersonneUtil.verifierContrainteBeneficiaireProspect(personnePhysique)) {
                idNaturePersonnePhysique = squareMappingService.getIdNaturePersonneBeneficiaireProspect();
            }
            if (idNaturePersonnePhysique != null) {
                final PersonnePhysiqueNature naturePersonne = personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(idNaturePersonnePhysique);
                personnePhysique.setNature(naturePersonne);
            }
        }

        // si la personne est passée de vivier à prospect et qu'elle a des bénéficiaires vivier,
        // on essaye de les passer en bénéficiaire prospect si c'est possible
        if (vivierToProspect && famille != null && famille.size() > 0) {
            for (Personne beneficiaire : famille) {
                final PersonnePhysique beneficiairePhysique = (PersonnePhysique) beneficiaire;
                if (beneficiairePhysique.getNature() != null && idNaturePersonneBeneficiaireVivier.equals(beneficiairePhysique.getNature().getId())
                    && validationPersonneUtil.verifierContrainteBeneficiaireProspect(beneficiairePhysique)) {
                    final PersonnePhysiqueNature naturePersonne =
                        personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(squareMappingService.getIdNaturePersonneBeneficiaireProspect());
                    beneficiairePhysique.setNature(naturePersonne);
                }
            }
        }

        // Récupération de l'email créé ou modifié pour le retourner
        final CritereRechercheEmail critereEmail = new CritereRechercheEmail();
        final Set<Long> idsPersonnes = new HashSet<Long>();
        idsPersonnes.add(idPersonne);
        critereEmail.setIdsPersonnes(idsPersonnes);
        final List<EmailDto> emails = mapperDozerBean.mapList(emailDao.rechercherEmailParCritere(critereEmail), EmailDto.class);
        for (EmailDto emailTrouve : emails) {
            if (emailTrouve.getAdresse().equals(emailDto.getAdresse())) {
                return emailTrouve;
            }
        }

        // Si email non trouvé, on retourne un email vide
        final EmailDto emailVide = new EmailDto();
        return emailVide;
    }

    /**
     * Test si une relation est active ou pas à la date du jour.
     * @param dateDebut date de début de la relation
     * @param dateFin date de fin de la relation
     * @return true si la relation est active, false sinon
     */
    private boolean isRelationActive(Calendar dateDebut, Calendar dateFin) {
        final Calendar dateDuJour = Calendar.getInstance();
        final int jour = dateDuJour.get(Calendar.DATE);
        final int mois = dateDuJour.get(Calendar.MONTH);
        final int annee = dateDuJour.get(Calendar.YEAR);
        dateDuJour.clear();
        dateDuJour.set(annee, mois, jour);
        return (dateDebut == null || dateDebut.before(dateDuJour) || dateDebut.equals(dateDuJour))
            && (dateFin == null || dateFin.after(dateDuJour));
    }

    /**
     * Récupère que le jour du Calendar.
     * @param date la date
     */
    private void nettoyerDate(Calendar date) {
        if (date != null) {
            final int jour = date.get(Calendar.DATE);
            final int mois = date.get(Calendar.MONTH);
            final int annee = date.get(Calendar.YEAR);
            date.clear();
            date.set(annee, mois, jour);
        }
    }

    /**
     * Récupère la famille de la personne passée en paramètre.
     * @param personnePrincipale la personne principale dont on doit récupérer la famille
     * @return la famille de la personne principale
     */
    private Set<Personne> getFamille(Personne personnePrincipale) {
        final Set<Personne> famille = new LinkedHashSet<Personne>();
        final RelationCriteresRechercheDto criteriasRel = new RelationCriteresRechercheDto();
        criteriasRel.setIdPersonne(personnePrincipale.getId());
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        criteriasRel.setGroupements(groupements);
        criteriasRel.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteresRel =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteriasRel, 0, Integer.MAX_VALUE);
        final List<Relation> listRelation = relationDao.rechercherRelationsParCriteres(criteresRel);
        for (Relation relation : listRelation) {
            // On ajoute la personne liée à la personne principale
            if (relation.getPersonneSource().getId().equals(personnePrincipale.getId())) {
                famille.add(relation.getPersonneCible());
            }
            else {
                famille.add(relation.getPersonneSource());
            }
        }
        return famille;
    }

    @Override
    public List<PersonneDto> rechercherPersonnesParAdresse(Long adresseId) {
        final List<Personne> personnes = personneDao.rechercherPersonnesParAdresse(adresseId);
        return mapperDozerBean.mapList(personnes, PersonneDto.class);
    }

    @Override
    public List<PersonneDto> rechercherPersonnesParEmail(Long emailId) {
        final List<Personne> personnes = personneDao.rechercherPersonnesParEmail(emailId);
        return mapperDozerBean.mapList(personnes, PersonneDto.class);
    }

    @Override
    public List<PersonneDto> rechercherPersonnesParTelephone(Long telephoneId) {
        final List<Personne> personnes = personneDao.rechercherPersonnesParTelephone(telephoneId);
        return mapperDozerBean.mapList(personnes, PersonneDto.class);
    }

    @Override
    public int desactiverRelations(List<Long> idsRelationsADesactiver) {
        return relationDao.desactiverRelations(idsRelationsADesactiver);
    }

    @Override
    public List<Long> rechercherIdsRelationsADesactiver(Calendar date, int pagination) {
        if (date == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneKeyUtil.MESSAGE_ERREUR_REL_RECH_CRITERE));
        }
        return relationDao.rechercherIdsRelationsADesactiver(date, pagination);
    }

    /**
     * Modifie personneDao.
     * @param personneDao la nouvelle valeur de personneDao
     */
    public void setPersonneDao(PersonneDao personneDao) {
        this.personneDao = personneDao;
    }

    /**
     * Modifie personnePhysiqueDao.
     * @param personnePhysiqueDao la nouvelle valeur de personnePhysiqueDao
     */
    public void setPersonnePhysiqueDao(PersonnePhysiqueDao personnePhysiqueDao) {
        this.personnePhysiqueDao = personnePhysiqueDao;
    }

    /**
     * Fixe la valeur.
     * @param relationTypeDao the relationTypeDao to set
     */
    public void setRelationTypeDao(RelationTypeDao relationTypeDao) {
        this.relationTypeDao = relationTypeDao;
    }

    /**
     * Fixe la valeur.
     * @param validationExpressionUtil the validationExpressionUtil to set
     */
    public void setValidationExpressionUtil(ValidationExpressionUtil validationExpressionUtil) {
        this.validationExpressionUtil = validationExpressionUtil;
    }

    /**
     * Modifie la valeur de relationDao.
     * @param relationDao the relationDao to set
     */
    public void setRelationDao(RelationDao relationDao) {
        this.relationDao = relationDao;
    }

    /**
     * Modifie messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Fixe la valeur.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Modifie squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Modifie telephoneDao.
     * @param telephoneDao la nouvelle valeur de telephoneDao
     */
    public void setTelephoneDao(TelephoneDao telephoneDao) {
        this.telephoneDao = telephoneDao;
    }

    /**
     * Modifie emailDao.
     * @param emailDao la nouvelle valeur de emailDao
     */
    public void setEmailDao(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    /**
     * Modifie adresseDao.
     * @param adresseDao la nouvelle valeur de adresseDao
     */
    public void setAdresseDao(AdresseDao adresseDao) {
        this.adresseDao = adresseDao;
    }

    /**
     * Modifie natureTelephoneDao.
     * @param natureTelephoneDao la nouvelle valeur de natureTelephoneDao
     */
    public void setNatureTelephoneDao(NatureTelephoneDao natureTelephoneDao) {
        this.natureTelephoneDao = natureTelephoneDao;
    }

    /**
     * Modifie adresseNatureDao.
     * @param adresseNatureDao la nouvelle valeur de adresseNatureDao
     */
    public void setAdresseNatureDao(AdresseNatureDao adresseNatureDao) {
        this.adresseNatureDao = adresseNatureDao;
    }

    /**
     * Modifie typeVoieDao.
     * @param typeVoieDao la nouvelle valeur de typeVoieDao
     */
    public void setTypeVoieDao(TypeVoieDao typeVoieDao) {
        this.typeVoieDao = typeVoieDao;
    }

    /**
     * Modifie paysDao.
     * @param paysDao la nouvelle valeur de paysDao
     */
    public void setPaysDao(PaysDao paysDao) {
        this.paysDao = paysDao;
    }

    /**
     * Modifie emailNatureDao.
     * @param emailNatureDao la nouvelle valeur de emailNatureDao
     */
    public void setEmailNatureDao(EmailNatureDao emailNatureDao) {
        this.emailNatureDao = emailNatureDao;
    }

    /**
     * Définit la valeur de ressourceDao.
     * @param ressourceDao la nouvelle valeur de ressourceDao
     */
    public void setRessourceDao(RessourceDao ressourceDao) {
        this.ressourceDao = ressourceDao;
    }

    /**
     * Définit la valeur de agenceDao.
     * @param agenceDao la nouvelle valeur de agenceDao
     */
    public void setAgenceDao(AgenceDao agenceDao) {
        this.agenceDao = agenceDao;
    }

    /**
     * Setter function.
     * @param formatUtil the formatUtil to set
     */
    public void setFormatUtil(FormatUtil formatUtil) {
        this.formatUtil = formatUtil;
    }

    /**
     * Définit la valeur de codePostalCommuneDao.
     * @param codePostalCommuneDao the codePostalCommuneDao to set
     */
    public void setCodePostalCommuneDao(CodePostalCommuneDao codePostalCommuneDao) {
        this.codePostalCommuneDao = codePostalCommuneDao;
    }

    /**
     * Setter.
     * @param agenceHabilitationUtil the agenceHabilitationUtil to set
     */
    public void setAgenceHabilitationUtil(AgenceHabilitationUtil agenceHabilitationUtil) {
        this.agenceHabilitationUtil = agenceHabilitationUtil;
    }

    /**
     * Setter.
     * @param adherentService the adherentService to set
     */
    public void setAdherentService(AdherentService adherentService) {
        this.adherentService = adherentService;
    }

    /**
     * Setter.
     * @param contratService the contratService to set
     */
    public void setContratService(ContratService contratService) {
        this.contratService = contratService;
    }

    /**
     * Définit la valeur de validationPersonneUtil.
     * @param validationPersonneUtil la nouvelle valeur de validationPersonneUtil
     */
    public void setValidationPersonneUtil(ValidationPersonneUtil validationPersonneUtil) {
        this.validationPersonneUtil = validationPersonneUtil;
    }

    /**
     * Définit la valeur de personnePhysiqueNatureDao.
     * @param personnePhysiqueNatureDao la nouvelle valeur de personnePhysiqueNatureDao
     */
    public void setPersonnePhysiqueNatureDao(PersonnePhysiqueNatureDao personnePhysiqueNatureDao) {
        this.personnePhysiqueNatureDao = personnePhysiqueNatureDao;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

}
