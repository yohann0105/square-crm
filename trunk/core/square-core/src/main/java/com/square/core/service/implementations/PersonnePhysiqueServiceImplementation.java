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

import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_CAISSE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_CIVILITE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_INVALIDE;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NOM_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NUMERO_SECURITE_SOCIALE_INVALIDE;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NUMERO_SECURITE_SOCIALE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_REGIME_NULL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.adherent.noyau.service.interfaces.EspaceClientInternetService;
import com.square.core.dao.interfaces.AdresseDao;
import com.square.core.dao.interfaces.AdresseNatureDao;
import com.square.core.dao.interfaces.AgenceDao;
import com.square.core.dao.interfaces.CaisseDao;
import com.square.core.dao.interfaces.CodePostalCommuneDao;
import com.square.core.dao.interfaces.CspDao;
import com.square.core.dao.interfaces.EmailDao;
import com.square.core.dao.interfaces.EmailNatureDao;
import com.square.core.dao.interfaces.NatureTelephoneDao;
import com.square.core.dao.interfaces.PaysDao;
import com.square.core.dao.interfaces.PersonneCiviliteDao;
import com.square.core.dao.interfaces.PersonneDao;
import com.square.core.dao.interfaces.PersonnePhysiqueDao;
import com.square.core.dao.interfaces.PersonnePhysiqueNatureDao;
import com.square.core.dao.interfaces.PersonneProfessionDao;
import com.square.core.dao.interfaces.PersonneReseauDao;
import com.square.core.dao.interfaces.PersonneStatutDao;
import com.square.core.dao.interfaces.RelationDao;
import com.square.core.dao.interfaces.RelationTypeDao;
import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.dao.interfaces.SegmentDao;
import com.square.core.dao.interfaces.SituationFamilialeDao;
import com.square.core.dao.interfaces.TelephoneDao;
import com.square.core.dao.interfaces.TypeVoieDao;
import com.square.core.model.Adresse;
import com.square.core.model.Caisse;
import com.square.core.model.CodePostalCommune;
import com.square.core.model.Email;
import com.square.core.model.InfoSante;
import com.square.core.model.NatureTelephone;
import com.square.core.model.Pays;
import com.square.core.model.Personne;
import com.square.core.model.PersonneAttribution;
import com.square.core.model.PersonneCSP;
import com.square.core.model.PersonneCivilite;
import com.square.core.model.PersonneMorale;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.PersonnePhysiqueNature;
import com.square.core.model.PersonneProfession;
import com.square.core.model.PersonneReseau;
import com.square.core.model.PersonneSegment;
import com.square.core.model.PersonneSituationFamiliale;
import com.square.core.model.PersonneStatut;
import com.square.core.model.Relation;
import com.square.core.model.RelationType;
import com.square.core.model.Telephone;
import com.square.core.model.TypeVoie;
import com.square.core.model.Ressources.Agence;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.AdresseCriteresRechercheDto;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.BeneficiaireDto;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.InfoSanteDto;
import com.square.core.model.dto.InfosPersonneSyncDto;
import com.square.core.model.dto.NumeroRoDto;
import com.square.core.model.dto.PaysSimpleDto;
import com.square.core.model.dto.PersonneCreationAssureSocialDto;
import com.square.core.model.dto.PersonneCreationVivierDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonnePhysiqueCopieDto;
import com.square.core.model.dto.PersonnePhysiqueIdCriteresRechercheDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RapportDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.model.plugin.NumeroClientSquarePlugin;
import com.square.core.model.util.CoordonneesSimples;
import com.square.core.model.util.ResultatPaginationFullText;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.AgenceHabilitationUtil;
import com.square.core.util.FormatUtil;
import com.square.core.util.PersonnePhysiqueKeyUtil;
import com.square.core.util.RessourceHabilitationUtil;
import com.square.core.util.poi.DocumentXls;
import com.square.core.util.validation.RapportUtil;
import com.square.core.util.validation.ValidationExpressionProp;
import com.square.core.util.validation.ValidationExpressionUtil;
import com.square.core.util.validation.ValidationPersonneUtil;

/**
 * Implémentation des services des personnes physiques.
 * @author cblanchard - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class PersonnePhysiqueServiceImplementation implements PersonnePhysiqueService {

    /** Le logger. */
    private static Logger logger = Logger.getLogger(PersonnePhysiqueServiceImplementation.class);

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Classe utilitaire pour la vérification. */
    private ValidationExpressionUtil validationExpressionUtil;

    /** Le DAO personne physique. */
    private PersonnePhysiqueDao personnePhysiqueDao;

    /** Dao pour la relation. */
    private RelationDao relationDao;

    /** Le dao des civilités. */
    private PersonneCiviliteDao personneCiviliteDao;

    /** Le dao des professions. */
    private PersonneProfessionDao personneProfessionDao;

    /** Le dao de la nature de la voie. */
    private TypeVoieDao typeVoieDao;

    /** Le dao pour la nature des téléphones. */
    private NatureTelephoneDao natureTelephoneDao;

    /** Le dao pour les pays. */
    private PaysDao paysDao;

    /** Le dao pour la nature de l'email. */
    private EmailNatureDao emailNatureDao;

    /** Le dao de la nature de l'adresse. */
    private AdresseNatureDao adresseNatureDao;

    /** Le dao des caisses. */
    private CaisseDao caisseDao;

    /** Le dao des csp. */
    private CspDao cspDao;

    /** Le dao des segments. */
    private SegmentDao segmentDao;

    /** Le dao sur la situation familiale. */
    private SituationFamilialeDao situationFamilialeDao;

    /** Service de mapping. */
    private SquareMappingService squareMappingService;

    /** Le dao de statut de personne. */
    private PersonneStatutDao personneStatutDao;

    /** Le dao de réseau de personne. */
    private PersonneReseauDao personneReseauDao;

    /** Dao sur les types de relation. */
    private RelationTypeDao relationTypeDao;

    /** Service sur les personnes. */
    private PersonneService personneService;

    /** Service sur les personnes. */
    private OpportuniteService opportuniteService;

    /** Service sur les personnes. */
    private ActionService actionService;

    /** Dao sur la nature de la personne Physique. */
    private PersonnePhysiqueNatureDao personnePhysiqueNatureDao;

    /** Dao sur les téléphones. */
    private TelephoneDao telephoneDao;

    /** Dao sur les emails. */
    private EmailDao emailDao;

    /** Dao sur les adresses. */
    private AdresseDao adresseDao;

    /** Doa sur les ressources . **/
    private RessourceDao ressourceDao;

    /** Dao sur les agences . */
    private AgenceDao agenceDao;

    /** Dao sur les personnes. */
    private PersonneDao personneDao;

    /** Dao sur les codes postaux - communes. */
    private CodePostalCommuneDao codePostalCommuneDao;

    /** Habilitation des ressources . */
    private RessourceHabilitationUtil ressourceHabilitationUtil;

    /** Habilitation des agences . */
    private AgenceHabilitationUtil agenceHabilitationUtil;

    private FormatUtil formatUtil;

    /** Classe utilitaire pour la validation des personnes. */
    private ValidationPersonneUtil validationPersonneUtil;

    private int paginationExportRecherche;

    /** Plugin numerotation unique des clients. */
    private NumeroClientSquarePlugin numeroClientSquarePlugin;

    /** Service de l'espace client. */
    private EspaceClientInternetService espaceClientInternetService;

    private static final String CHAMP_PRENOM = "prenom";
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_DATE_NAISSANCE = "dateNaissance";

    @Override
    public PersonneDto creerPersonnePhysique(PersonneDto personneDto, List<BeneficiaireDto> listeBeneficiaire, AdresseDto adresse, EmailDto email,
        TelephoneDto telephone) {
        final CoordonneesDto coordonnees = new CoordonneesDto();
        coordonnees.getAdresses().add(adresse);
        coordonnees.getEmails().add(email);
        coordonnees.getTelephones().add(telephone);

        return this.creerPersonnePhysique(personneDto, listeBeneficiaire, coordonnees);
    }

    @Override
    public List<PersonneDto> rechercherPersonneParRequete(String requete) {
        final List<PersonnePhysique> personnes = personnePhysiqueDao.recherchePersonneParRequete(requete);
        return mapperDozerBean.mapList(personnes, PersonneDto.class);
    }

    @Override
    public void controlerTelephone(TelephoneDto telephoneDto) {
        final RapportDto rapport = new RapportDto();
        if (telephoneDto.getNumero() != null && !telephoneDto.getNumero().trim().isEmpty()) {
            final int nbreCharsPourComplete = 12 - telephoneDto.getNumero().trim().length();
            final StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < nbreCharsPourComplete; i++) {
                stringBuilder.append("0");
            }
            telephoneDto.setNumero(telephoneDto.getNumero() + stringBuilder.toString());
        }
        final Pays paysTel = paysDao.rechercherPaysParId(telephoneDto.getPays().getId());
        if (paysTel == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PAYS_INEXISTENT_EN_BD));
        }
        boolean isPaysTelFrance = false;
        if (paysTel.getId().equals(squareMappingService.getIdPaysFrance()) || paysTel.getId().equals(squareMappingService.getIdPaysFranceMetropolitaine())) {
            isPaysTelFrance = true;
        }
        // FIXME : pour l'instant, plus de vérification de la nature de téléphone
        // if (telephoneDto.getNature() != null && squareMappingService.getListeIdsNaturesTelephoneMobile().contains(telephoneDto.getNature().getIdentifiant()))
        // {
        // validationExpressionUtil.verifierTelephoneMobile(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), telephoneDto
        // .getNumero(), isPaysTelFrance, paysTel.getFormatTelephone(), TelephoneDto.class.getSimpleName() + ".nature");
        // }
        // else if (telephoneDto.getNature() != null && telephoneDto.getNature().getIdentifiant().equals(squareMappingService.getIdNatureTelephoneFixe())) {
        // validationExpressionUtil.verifierTelephoneFixe(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), telephoneDto
        // .getNumero(), isPaysTelFrance, paysTel.getFormatTelephone(), TelephoneDto.class.getSimpleName() + ".nature");
        // }
        // else {
        // validationExpressionUtil.verifierTelephoneFixe(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE), telephoneDto
        // .getNumero(), false, paysTel.getFormatTelephone(), TelephoneDto.class.getSimpleName() + ".nature");
        // }
        if (Boolean.TRUE.equals(rapport.getEnErreur())) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }
    }

    /**
     * Implémentation du service de création d'une personne.
     * @param personneDto la personne à créer ou à mettre à jour.
     * @param listeBeneficiaire la liste des beneficiaires.
     * @param coordonnees coordonnées de la personne.
     * @return PersonneDto la personne créée.
     * @author mlamine - SCUB
     */
    public PersonneDto creerPersonnePhysique(PersonneDto personneDto, List<BeneficiaireDto> listeBeneficiaire, CoordonneesDto coordonnees) {
        final RapportDto rapport = new RapportDto();
        Adresse adressePrincipale = null;
        Email emailPersonnel = null;
        Telephone telephoneFixe = null;
        Telephone telephonePortable = null;

        // Contrôle des champs obligatoires
        if (personneDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_DTO_NULL));
        }
        // Récupération de la personne en base si l'identifiant est renseigné (doublon)
        boolean assureSelectionneParmiDoublon = false;
        PersonnePhysique personnePrincipale = null;
        if (personneDto.getIdentifiant() != null) {
            personnePrincipale = personnePhysiqueDao.rechercherPersonneParId(personneDto.getIdentifiant());
            adressePrincipale = getAdressePersonne(personnePrincipale, squareMappingService.getIdNatureAdressePrincipale());
            emailPersonnel = getEmailPersonne(personnePrincipale, squareMappingService.getIdNatureEmailPersonnel());
            telephoneFixe = getTelephonePersonne(personnePrincipale, squareMappingService.getIdNatureTelephoneFixe());
            telephonePortable = getTelephonePersonne(personnePrincipale, squareMappingService.getIdNatureMobilePrive());
            if (personnePrincipale != null) {
                assureSelectionneParmiDoublon = true;
            }
            else {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_ASSURE_SELECTIONNE_INEXISTANTE));
            }
        }

        EmailDto emailDto = null;
        AdresseDto adresseDto = null;
        TelephoneDto telephoneFixeDto = null;
        TelephoneDto telephonePortableDto = null;
        if (coordonnees.getTelephones() != null && coordonnees.getTelephones().size() >= 1 && coordonnees.getTelephones().get(0) != null
            && StringUtils.isNotBlank(coordonnees.getTelephones().get(0).getNumero())) {
            telephoneFixeDto = coordonnees.getTelephones().get(0);
        }
        if (coordonnees.getTelephones() != null && coordonnees.getTelephones().size() >= 2 && coordonnees.getTelephones().get(1) != null
            && StringUtils.isNotBlank(coordonnees.getTelephones().get(1).getNumero())) {
            telephonePortableDto = coordonnees.getTelephones().get(1);
        }
        if (coordonnees.getEmails() != null && !coordonnees.getEmails().isEmpty() && coordonnees.getEmails().get(0) != null
            && StringUtils.isNotBlank(coordonnees.getEmails().get(0).getAdresse())) {
            emailDto = coordonnees.getEmails().get(0);
        }
        if (coordonnees.getAdresses() != null) {
            for (AdresseDto adresse : coordonnees.getAdresses()) {
                if (adresse != null) {
                    adresseDto = adresse;
                    break;
                }
            }
        }

        // Pas de contrôle si l'assuré est sélectionné parmi un doublon
        if (!assureSelectionneParmiDoublon) {
            // Controle sur les adresses
            if (adresseDto == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_DTO_NULL));
            }
            validationPersonneUtil.verifierContrainteCreationProspect(personneDto, emailDto, adresseDto, telephoneFixeDto, telephonePortableDto, rapport);
        }
        if (listeBeneficiaire != null) {
            validationPersonneUtil.verifierContrainteCreationBeneficiaires(listeBeneficiaire, rapport);
        }

        // Les controles sont terminés, suite du processus :
        if (rapport.getEnErreur()) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }
        // On mappe les coordonnées en objet du modèle
        if (adresseDto != null) {
            adressePrincipale = mapperAdressePourCreation(adresseDto);
        }
        if (emailDto != null) {
            emailPersonnel = mapperEmailPourCreation(emailDto);
        }
        if (telephoneFixeDto != null) {
            telephoneFixe = mapperTelephonePourCreation(telephoneFixeDto);
        }
        if (telephonePortableDto != null) {
            telephonePortable = mapperTelephonePourCreation(telephonePortableDto);
        }

        // On créé la personne principale si l'assuré n'a pas été sélectionné parmi les doublons
        if (!assureSelectionneParmiDoublon) {
            personnePrincipale = mapperPersonnePourCreation(personneDto, adressePrincipale, emailPersonnel, telephoneFixe, telephonePortable);
            // Création de la personne
            personnePhysiqueDao.creerPersonnePhysique(personnePrincipale);
        }
        // On créé les bénéficiaires un par un
        if (listeBeneficiaire != null) {
            Long idConjoint = null;
            for (int i = 0; i < listeBeneficiaire.size(); i++) {
                final BeneficiaireDto beneficiaireDto = listeBeneficiaire.get(i);
                PersonnePhysique beneficiaire = null;
                // Récupération du bénéficiaire si sélectionné parmi un doublon
                boolean beneficiaireSelectionneParmiDoublon = false;
                if (beneficiaireDto.getIdentifiant() != null) {
                    beneficiaire = personnePhysiqueDao.rechercherPersonneParId(beneficiaireDto.getIdentifiant());
                    if (beneficiaire != null) {
                        beneficiaireSelectionneParmiDoublon = true;
                    }
                    else {
                        throw new BusinessException(
                            messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_BENEFICIAIRE_SELECTIONNE_INEXISTANTE));
                    }
                }
                // Recopie des informations de l'adhérent principal & copie des infos du bénéficiaire s'il n'est pas sélectionné parmi les doublons
                if (!beneficiaireSelectionneParmiDoublon) {
                    beneficiaire =
                        mapperPersonnePourCreation(mapperBeneficiaireEnPersonneDto(beneficiaireDto, personnePrincipale), adressePrincipale, emailPersonnel,
                            telephoneFixe, telephonePortable);
                    // Création du bénéficiaire
                    personnePhysiqueDao.creerPersonnePhysique(beneficiaire);
                }
                // On créé la relation entre la personne principale et le bénéficiaire qui vient d'être créé
                final RelationDto relation = new RelationDto();
                relation.setDateDebut(Calendar.getInstance());
                relation.setIdPersonnePrincipale(personnePrincipale.getId());
                relation.setIdPersonne(beneficiaire.getId());
                final RelationType type = relationTypeDao.rechercherTypeRelationParId(beneficiaireDto.getTypeRelation().getIdentifiant());
                relation.setType(new IdentifiantLibelleDto(type.getId(), type.getLibelle()));
                personneService.creerRelation(relation);
                if (logger.isDebugEnabled()) {
                    logger.debug(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_INFO_RELATION_CREEE));
                }

                // Si le bénéficiaire est le conjoint, on récupère son id.
                if (type.getId().equals(squareMappingService.getIdTypeRelationConjoint())) {
                    idConjoint = beneficiaire.getId();
                    logger.fatal("*************** idConjoint enregistré: " + idConjoint);
                }
                // Si c'est un enfant on crée la relation avec le conjoint si celà a été choisi
                else if (type.getId().equals(squareMappingService.getIdTypeRelationEnfant()) && idConjoint != null
                    && BooleanUtils.isTrue(beneficiaireDto.getRattacherAuxParents())) {
                    logger.fatal("*************** creation relation enfant conjoint");
                    final RelationDto relationEnfantConjoint = new RelationDto();
                    relationEnfantConjoint.setDateDebut(Calendar.getInstance());
                    relationEnfantConjoint.setIdPersonnePrincipale(idConjoint);
                    relationEnfantConjoint.setIdPersonne(beneficiaire.getId());
                    relationEnfantConjoint.setType(new IdentifiantLibelleDto(type.getId(), type.getLibelle()));
                    personneService.creerRelation(relationEnfantConjoint);
                    if (logger.isDebugEnabled()) {
                        logger.debug(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_INFO_RELATION_CREEE));
                    }
                }
                logger.fatal("*************** idConjoint : " + idConjoint);
                logger.fatal("*************** beneficiaireDto.getRattacherAuxParents() : " + beneficiaireDto.getRattacherAuxParents());
            }
        }
        return rechercherPersonneParIdentifiant(personnePrincipale.getId());
    }

    /**
     * Récupère l'email de la personne.
     * @param personne la personne dont on souhaite récupérer l'email
     * @param idNatureEmail la nature de l'email recherché
     * @return l'email trouvé
     */
    private Email getEmailPersonne(Personne personne, Long idNatureEmail) {
        Email emailRecherche = null;
        for (Email email : personne.getEmails()) {
            if (idNatureEmail.equals(email.getNature().getId()) && !email.isSupprime()) {
                emailRecherche = email;
                break;
            }
        }
        return emailRecherche;
    }

    /**
     * Récupère l'adresse d'une personne.
     * @param personne la personne
     * @param idNatureAdresse la nature de l'adresse recherchée
     * @return l'adresse trouvée
     */
    private Adresse getAdressePersonne(Personne personne, Long idNatureAdresse) {
        Adresse adresseRecherchee = null;
        for (Adresse adresse : personne.getAdresses()) {
            if (idNatureAdresse.equals(adresse.getNature().getId()) && !adresse.isSupprime() && adresse.getDateFin() == null) {
                adresseRecherchee = adresse;
                break;
            }
        }
        return adresseRecherchee;
    }

    /**
     * Récupère le téléphone d'une personne.
     * @param personne la personne
     * @param idNatureTelephone la nature du téléphone recherché
     * @return le téléphone trouvé
     */
    private Telephone getTelephonePersonne(Personne personne, Long idNatureTelephone) {
        Telephone telephoneRecherche = null;
        for (Telephone telephone : personne.getTelephones()) {
            if (!telephone.isSupprime() && idNatureTelephone.equals(telephone.getNatureTelephone().getId())) {
                telephoneRecherche = telephone;
                break;
            }
        }
        return telephoneRecherche;
    }

    /**
     * Mappe le DTO passé en paramètre en objet du modèle.
     * @param adresseDto l'adresse sous forme de DTO
     * @return l'adresse sous forme d'objet du modèle
     */
    private Adresse mapperAdressePourCreation(AdresseDto adresseDto) {
        if (adresseDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_DTO_NULL));
        }
        // On vérifie / récupère les objets du modèle de dimension requis
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
        CodePostalCommune codePostalCommune = null;
        if (squareMappingService.getIdPaysFrance().equals(adresseDto.getPays().getIdentifiant())) {
            if (adresseDto.getCodePostalCommune() != null && adresseDto.getCodePostalCommune().getIdentifiant() != null) {
                // On vérifie que l'adresse a un code postal - commune et que le code postal - commune existe en base de données.
                codePostalCommune = codePostalCommuneDao.rechercheCodePostalCommuneParId(adresseDto.getCodePostalCommune().getIdentifiant());
                if (codePostalCommune == null) {
                    throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_CODEPOSTAL_COMMUNE_INEXISTANT_EN_BD));
                }
            }
        }
        // Mapping dozer de l'adresse
        final Adresse adresse = mapperDozerBean.map(adresseDto, Adresse.class);
        // Mapping manuel supplémentaire de certains attributs
        adresse.setId(null);
        adresse.setIdentifiantExterieur(null);
        adresse.setDateCreation(Calendar.getInstance());
        adresse.setDateDebut(Calendar.getInstance());
        adresse.setTypeVoie(typeVoie);
        adresse.setCodePostalCommune(codePostalCommune);
        adresse.setPays(paysAdresse);
        adresse.setNature(adresseNatureDao.rechercheAdresseNatureParId(squareMappingService.getIdNatureAdressePrincipale()));
        return adresse;
    }

    /**
     * Mappe le DTO passé en paramètre en objet du modèle.
     * @param adresseDto l'adresse sous forme de DTO
     * @return l'adresse sous forme d'objet du modèle
     */
    private Email mapperEmailPourCreation(EmailDto emailDto) {
        if (emailDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_EMAIL_DTO_NULL));
        }
        final Email email = new Email();
        email.setId(null);
        email.setIdentifiantExterieur(null);
        email.setDateCreation(Calendar.getInstance());
        email.setNature(emailNatureDao.rechercheNatureEmailParId(squareMappingService.getIdNatureEmailPersonnel()));
        email.setAdresse(emailDto.getAdresse());
        return email;
    }

    /**
     * Mappe le DTO passé en paramètre en objet du modèle.
     * @param telephoneDto le téléphone sous forme de DTO
     * @return l'adresse sous forme d'objet du modèle
     */
    private Telephone mapperTelephonePourCreation(TelephoneDto telephoneDto) {
        if (telephoneDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_TELEPHONE_DTO_NULL));
        }
        NatureTelephone natureTelephone = null;
        if (telephoneDto != null && telephoneDto.getNature() != null) { // POSSIBLE SI EMAIL RENSEIGNE VOIR CONTROLE INTEGRITE
            natureTelephone = natureTelephoneDao.rechercherNatureTelephoneParId(telephoneDto.getNature().getIdentifiant());
            if (natureTelephone == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_NATURE_TELEPHONE_INEXISTENT_EN_BD));
            }
        }
        Pays paysTelephone = null;
        if (telephoneDto != null && telephoneDto.getPays() != null) { // POSSIBLE SI PAYS RENSEIGNE VOIR CONTROLE INTEGRITE
            paysTelephone = paysDao.rechercherPaysParId(telephoneDto.getPays().getId());
            if (paysTelephone == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PAYS_INEXISTENT_EN_BD));
            }
        }
        final Telephone telephone = mapperDozerBean.map(telephoneDto, Telephone.class);
        telephone.setId(null);
        telephone.setIdentifiantExterieur(null);
        telephone.setDateCreation(Calendar.getInstance());
        telephone.setNumTelephone(formatUtil.supprimerFormatNumTel(telephoneDto.getNumero()));
        telephone.setNatureTelephone(natureTelephone);
        telephone.setPays(paysTelephone);
        return telephone;
    }

    /**
     * Mappe un DTO bénéficiaire en DTO personne en recopiant certains attributs de la personne principale à laquelle le bénéficiaire est rattaché.
     * @param beneficiaireDto le DTO contenant les infos du bénéficiaire
     * @param personnePrincipale l'objet du modèle de la personne principale à laquelle le bénéficiaire est rattaché
     * @return un DTO contenant les infos du bénéficiaire
     */
    private PersonneDto mapperBeneficiaireEnPersonneDto(BeneficiaireDto beneficiaireDto, PersonnePhysique personnePrincipale) {
        PersonneDto personneDto = null;
        if (beneficiaireDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_BENEFICIAIRE_DTO_NULL));
        }
        // Mapping dozer automatique
        personneDto = mapperDozerBean.map(beneficiaireDto, PersonneDto.class);
        // On force certains attributs
        // On force la nature de la personne à "bénéficiaire prospect"
        personneDto.setNaturePersonne(new IdentifiantLibelleDto(squareMappingService.getIdNaturePersonneBeneficiaireProspect()));
        // Recopie manuelle de certains attributs de la personne principale vers le bénéficiaire
        // On recopie les infos santé de la personne principale
        if (personnePrincipale.getInfoSante() != null && personnePrincipale.getInfoSante().getCaisse() != null) {
            final InfoSanteDto infoSanteDto = new InfoSanteDto();
            infoSanteDto.setCaisse(new CaisseSimpleDto(personnePrincipale.getInfoSante().getCaisse().getId()));
            personneDto.setInfoSante(infoSanteDto);
        }
        // On recopie la ressource attribuée à la personne principale
        if (personnePrincipale.getAttribution().getRessource() != null) {
            personneDto.setCommercial(new DimensionRessourceDto(personnePrincipale.getRessource().getId()));
        }
        // On recopie le créateur de la personne principale
        final DimensionRessourceDto createur = new DimensionRessourceDto();
        createur.setIdentifiantExterieur(personnePrincipale.getRessource().getIdentifiantExterieur());
        personneDto.setCreateur(createur);
        return personneDto;
    }

    /**
     * Mappe le DTO passé en paramètre en objet du modèle.
     * @param personneDto le DTO contenant les informations de la personne
     * @param adressePrincipale l'adresse principale de la personne
     * @param emailPersonnel l'email personnel de la personne
     * @param telephoneFixe le téléphone fixe de la personne
     * @param telephonePortable le téléphone portable de la personne
     * @return la personne sous forme d'un objet du modèle
     */
    private PersonnePhysique mapperPersonnePourCreation(PersonneDto personneDto, Adresse adressePrincipale, Email emailPersonnel, Telephone telephoneFixe,
        Telephone telephonePortable) {
        PersonnePhysique personneToCreate = null;
        if (personneDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_DTO_NULL));
        }
        // Controle des différentes références
        PersonnePhysiqueNature naturePersonne = null;
        if (personneDto.getNaturePersonne() != null && personneDto.getNaturePersonne().getIdentifiant() != null) {
            naturePersonne = personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(personneDto.getNaturePersonne().getIdentifiant());
            if (naturePersonne == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_NATURE_PERSONNE_INEXISTANTE));
            }
        }
        PersonneProfession profession = null;
        if (personneDto.getProfession() != null && personneDto.getProfession().getIdentifiant() != null) {
            profession = personneProfessionDao.rechercherProfessionParId(personneDto.getProfession().getIdentifiant());
            if (profession == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PROFESSION_INEXISTENT_EN_BD));
            }
        }
        Caisse caisse = null;
        if (personneDto.getInfoSante() != null && personneDto.getInfoSante().getCaisse() != null && personneDto.getInfoSante().getCaisse().getId() != null) {
            caisse = caisseDao.rechercheCaisseParId(personneDto.getInfoSante().getCaisse().getId());
            if (caisse == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_CAISSEREGIME_INEXISTENT_EN_BD));
            }
        }
        if (personneDto.getCommercial() != null) {
            final Ressource ressource = ressourceDao.rechercherRessourceParId(personneDto.getCommercial().getIdentifiant());
            if (ressource == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_COMMERCIAL_INEXISTANTE));
            }

            final Agence agence = ressource.getAgence();
            if (agence == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_AGENCE_INEXISTANTE));
            }
        }
        PersonneCivilite civilite = null;
        if (personneDto.getCivilite() != null && personneDto.getCivilite().getIdentifiant() != null) {
            civilite = personneCiviliteDao.rechercherCiviliteParId(personneDto.getCivilite().getIdentifiant());
            if (civilite == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_CIVILITE_INEXISTENT_EN_BD));
            }
        }
        final Long idCsp;
        if (personneDto.getCsp() != null && personneDto.getCsp().getIdentifiant() != null) {
            idCsp = personneDto.getCsp().getIdentifiant();
        }
        else {
            // Si la Catégorie Socio-Professionnelle n'est pas spécifiée, on renseigne la CSP par défaut
            idCsp = squareMappingService.getIdCSPPersonneParDefaut();
        }
        final PersonneCSP csp = cspDao.recherchePersonneCSPParId(idCsp);
        if (csp == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_CSP_INEXISTENT_EN_BD));
        }
        // Initialisation de la personne à créer.
        // Informations simples.
        personneToCreate = mapperDozerBean.map(personneDto, PersonnePhysique.class);
        // FIXME : Il ne doit pas y avoir de extid renseigné a ce stade sinon les bénéficiaires le recoivent aussi
        // et cela provoque une erreur de contrainte unique. Trouver une meilleure solution.
        personneToCreate.setIdentifiantExterieur(null);
        if (personneDto.getInfoSante() != null) {
            personneToCreate.setInfoSante(new InfoSante());
            if (StringUtils.isNotBlank(personneDto.getInfoSante().getNro())) {
                final NumeroRoDto numerRO = squareMappingService.convertirNroVersNss(personneDto.getInfoSante().getNro());
                if (numerRO != null) {
                    personneToCreate.getInfoSante().setNumSecuriteSocial(numerRO.getNumeroSS());
                    personneToCreate.getInfoSante().setCleSecuriteSocial(numerRO.getCleSS());
                }
            }
            if (caisse != null) {
                personneToCreate.getInfoSante().setCaisse(caisse);
            }
            if (personneDto.getInfoSante().getIdReferent() == null) {
                // il est son propre referent
                personneToCreate.getInfoSante().setReferent(personneToCreate);
            }
            else {
                personneToCreate.getInfoSante().setReferent(personnePhysiqueDao.rechercherPersonneParId(personneDto.getInfoSante().getIdReferent()));
            }
        }
        if (profession != null) {
            personneToCreate.setProfession(profession);
        }
        else {
            personneToCreate.setProfession(personneProfessionDao.rechercherProfessionParId(squareMappingService.getIdProfessionPersonneParDefaut()));
        }
        personneToCreate.setCivilite(civilite);
        final Calendar today = Calendar.getInstance();
        personneToCreate.setDateCreation(today);
        personneToCreate.setDateModification(today);
        // Si la nature de la personne n'est pas renseignée, on met celle par défaut
        if (naturePersonne != null) {
            personneToCreate.setNature(naturePersonne);
        }
        else {
            personneToCreate.setNature(personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(squareMappingService.getIdNaturePersonneParDefaut()));
        }
        personneToCreate.setSegment(segmentDao.recherchePersonneSegmentParId(squareMappingService.getIdSegmentPersonneParDefaut()));
        personneToCreate.setSituationFamiliale(situationFamilialeDao.rechercherSituationFamiliale(squareMappingService
                .getIdSituationFamilialePersonneParDefaut()));
        personneToCreate.setReseau(personneReseauDao.rechercheReseauParId(squareMappingService.getIdReseauVentePersonneParDefaut()));
        personneToCreate.setCsp(csp);

        // La ressource connectée.
        if (personneDto.getCreateur() != null && personneDto.getCreateur().getIdentifiantExterieur() != null
                && !"".equals(personneDto.getCreateur().getIdentifiantExterieur())) {
                personneToCreate.setRessource(ressourceDao.rechercherRessourceParEid(personneDto.getCreateur().getIdentifiantExterieur()));
        }
        else {
            personneToCreate.setRessource(ressourceHabilitationUtil.getUtilisateurConnecte());
         }

        // les attributions de la personne
        final PersonneAttribution attribution = new PersonneAttribution();
        final CodePostalCommune codePostalCommune = adressePrincipale != null ? adressePrincipale.getCodePostalCommune() : null;
        Agence agence = null;
        if (personneDto.getCommercial() == null && codePostalCommune != null && codePostalCommune.getCodePostal() != null
            && codePostalCommune.getCodePostal().getCodePostal() != null && !"".equals(codePostalCommune.getCodePostal().getCodePostal().trim())
            && codePostalCommune.getCodePostal().getCodePostal().trim().length() != 2) {
            // on détermine l'agence responsable de la personne à partir de son code postal
            agence = agenceHabilitationUtil.getAgenceByCodePostal(codePostalCommune.getCodePostal().getCodePostal().trim());
        }
        else if (personneDto.getCommercial() != null) {
            final Ressource ressource = ressourceDao.rechercherRessourceParId(personneDto.getCommercial().getIdentifiant());
            attribution.setRessource(ressource);
            agence = ressource.getAgence();
        }
        else {
            throw new BusinessException("Erreur aucun responsable trouvé pour la personne");
        }
        if (agence == null) {
            throw new BusinessException("Erreur, aucune agence trouvée pour la personne");
        }
        attribution.setAgence(agence);
        personneToCreate.setAttribution(attribution);
        // On attribue un numéro unique à la personne
        personneToCreate.setNum(numeroClientSquarePlugin.getNumeroClient());
        // On lie les coordonnées à la personne
        if (adressePrincipale != null) {
            personneToCreate.addAdresse(adressePrincipale);
        }
        if (emailPersonnel != null) {
            personneToCreate.addEMail(emailPersonnel);
        }
        if (telephoneFixe != null) {
            personneToCreate.addTelephone(telephoneFixe);
        }
        if (telephonePortable != null) {
            personneToCreate.addTelephone(telephonePortable);
        }
        return personneToCreate;
    }

    /**
     * Implémentation du service de copie d'une personne.
     * @param infosCopie la personne à copier
     * @return PersonneDto la personne copiée
     * @author mlamine - SCUB
     */
    public PersonneDto creerUneCopiePersonne(PersonnePhysiqueCopieDto infosCopie) {
        // On récupère la personne source
        final Personne personneSource = personneDao.rechercherPersonneParId(infosCopie.getIdPersonneSource());
        if (personneSource == null) {
            logger.error("La personne source de la copie n'existe pas : " + infosCopie.getIdPersonneSource());
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }
        final PersonneDto copiePersonneDto = new PersonneDto();
        // Mapping des informations de la personne
        mapperDozerBean.map(infosCopie, copiePersonneDto);
        copiePersonneDto.setCivilite(infosCopie.getCivilite() == null ? null : new IdentifiantLibelleDto(infosCopie.getCivilite()));
        copiePersonneDto.setNaturePersonne(infosCopie.getIdNaturePersonne() == null ? null : new IdentifiantLibelleDto(infosCopie.getIdNaturePersonne()));

        // On récupère les informations de la personne source
        Adresse adressePrincipale = getAdressePersonne(personneSource, squareMappingService.getIdNatureAdressePrincipale());
        if (adressePrincipale == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_AUCUNE_ADRESSE_PRINCIPALE));
        }
        Telephone telephoneFixe = null;
        Telephone telephonePortable = null;
        Email emailPersonnel = null;
        if (personneSource instanceof PersonnePhysique) {
            final RapportDto rapport = new RapportDto();
            final ValidationExpressionProp[] propsPersonne =
                new ValidationExpressionProp[] {
                    new ValidationExpressionProp("civilite", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_CIVILITE_NULL)),
                    new ValidationExpressionProp(CHAMP_PRENOM, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_NULL)),
                    new ValidationExpressionProp(CHAMP_NOM, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_NOM_NULL)),
                    new ValidationExpressionProp(CHAMP_DATE_NAISSANCE, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_NULL))};
            validationExpressionUtil.verifierSiVide(rapport, infosCopie, propsPersonne);

            // Vérification de nom et prenom
            validationPersonneUtil.verifierNomPrenomPersonne(rapport, infosCopie.getNom(), infosCopie.getPrenom(), infosCopie.getClass());

            // Vérification des infos santé
            if (infosCopie.getInfosSante() != null) {
                // Numéro RO.
                final NumeroRoDto nroDto = squareMappingService.convertirNroVersNss(infosCopie.getInfosSante().getNro());
                if (nroDto != null) {
                    validationExpressionUtil.verifierNumeroSecuriteSociale(rapport, null, messageSourceUtil
                            .get(MESSAGE_ERROR_SAVE_PERSONNE_NUMERO_SECURITE_SOCIALE_INVALIDE), infosCopie.getClass().getSimpleName() + ".infoSante.nro",
                        nroDto.getNumeroSS(), nroDto.getCleSS());
                }
                else {
                    rapport.ajoutRapport(infosCopie.getClass().getSimpleName() + ".infoSante.nro", messageSourceUtil
                            .get(MESSAGE_ERROR_SAVE_PERSONNE_NUMERO_SECURITE_SOCIALE_NULL), true);
                }

                // Régime
                final IdentifiantLibelleDto regime = infosCopie.getInfosSante() != null ? infosCopie.getInfosSante().getCaisseRegime() : null;
                validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_REGIME_NULL), regime, infosCopie
                        .getClass().getSimpleName()
                    + ".infoSante.regime");

                // Caisse
                final CaisseSimpleDto caisse = infosCopie.getInfosSante() != null ? infosCopie.getInfosSante().getCaisse() : null;
                validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_CAISSE_NULL), caisse, infosCopie
                        .getClass().getSimpleName()
                    + ".infoSante.caisse");
            }

            // Les controles sont terminés, suite du processus :
            if (rapport.getEnErreur()) {
                RapportUtil.logRapport(rapport, logger);
                throw new ControleIntegriteException(rapport);
            }
            // On récupère les coordonnées personnelles de la personne source
            emailPersonnel = getEmailPersonne(personneSource, squareMappingService.getIdNatureEmailPersonnel());
            telephoneFixe = getTelephonePersonne(personneSource, squareMappingService.getIdNatureTelephoneFixe());
            telephonePortable = getTelephonePersonne(personneSource, squareMappingService.getIdNatureMobilePrive());
            // Si les coordonnées de la personne source doivent être dupliquées
            if (infosCopie.isDupliquerCoordonnees()) {
                // On duplique les coordonnées de la personne source
                if (adressePrincipale != null) {
                    adressePrincipale = mapperAdressePourCreation((AdresseDto) mapperDozerBean.map(adressePrincipale, AdresseDto.class));
                }
                if (emailPersonnel != null) {
                    emailPersonnel = mapperEmailPourCreation((EmailDto) mapperDozerBean.map(emailPersonnel, EmailDto.class));
                }
                if (telephoneFixe != null) {
                    telephoneFixe = mapperTelephonePourCreation((TelephoneDto) mapperDozerBean.map(telephoneFixe, TelephoneDto.class));
                }
                if (telephonePortable != null) {
                    telephonePortable = mapperTelephonePourCreation((TelephoneDto) mapperDozerBean.map(telephonePortable, TelephoneDto.class));
                }
            }
            // On récupère les informations supplémentaires spécifiques à une personne physique
            final PersonneDto personneDto = rechercherPersonneParIdentifiant(infosCopie.getIdPersonneSource());
            copiePersonneDto.setReseauVente(personneDto.getReseauVente());
            copiePersonneDto.setSegment(personneDto.getSegment());
            if (infosCopie == null) {
                if (personneDto.getInfoSante() != null) {
                    if (copiePersonneDto.getInfoSante() == null) {
                        copiePersonneDto.setInfoSante(new InfoSanteDto());
                    }
                    copiePersonneDto.getInfoSante().setCaisse(personneDto.getInfoSante().getCaisse());
                }
            }
            else {
                copiePersonneDto.setInfoSante(infosCopie.getInfosSante());
            }
            copiePersonneDto.setCsp(personneDto.getCsp());
            copiePersonneDto.setCommercial(personneDto.getCommercial());
        }
        else if (personneSource instanceof PersonneMorale) {
            // Si la personne source personne morale : récupération du téléphone passé en paramètre
            if (infosCopie.getTelephone() != null) {
                telephoneFixe = mapperTelephonePourCreation(infosCopie.getTelephone());
            }
        }
        final PersonnePhysique copiePersonne =
            mapperPersonnePourCreation(copiePersonneDto, adressePrincipale, emailPersonnel, telephoneFixe, telephonePortable);
        if (!infosCopie.isDupliquerCoordonnees() && personneSource instanceof PersonnePhysique) {
            // On associe les adresses / telephones / emails de la personne source si c'est une personne physique à la personne copiée
            for (Adresse adresse : personneSource.getAdresses()) {
                if (!adresse.isSupprime() && adresse.getDateFin() == null) {
                    copiePersonne.addAdresse(adresse);
                }
            }
            for (Telephone telephone : personneSource.getTelephones()) {
                if (!telephone.isSupprime()) {
                    copiePersonne.addTelephone(telephone);
                }
            }
            for (Email email : personneSource.getEmails()) {
                if (!email.isSupprime()) {
                    copiePersonne.addEMail(email);
                }
            }
        }
        // On sauvegarde la copie de la personne dans la base de données
        personnePhysiqueDao.creerPersonnePhysique(copiePersonne);
        return rechercherPersonneParIdentifiant(copiePersonne.getId());
    }

    /**
     * Implémentation du service de modification d'une personne.
     * @param personneDto la personne à créer ou à mettre à jour.
     * @return PersonneDto la personne créée.
     * @author mlamine - SCUB
     */
    public PersonneDto modifierPersonnePhysique(PersonneDto personneDto) {

        // On vérifie que la personne à mettre à jour n'est pas nulle
        if (personneDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_DTO_NULL));
        }

        // Contrôle des données
        final RapportDto rapport = validationPersonneUtil.controlerModiciationPersonnePhysique(personneDto);


        final Long idNaturePersonneVivier = squareMappingService.getIdNaturePersonneVivier();
        final Long idNaturePersonneProspect = squareMappingService.getIdNaturePersonneProspect();
        final Long idNaturePersonneBeneficiaireVivier = squareMappingService.getIdNaturePersonneBeneficiaireVivier();
        final Long idNaturePersonneBeneficiaireProspect = squareMappingService.getIdNaturePersonneBeneficiaireProspect();

        if (rapport.getEnErreur() && !personneDto.getNaturePersonne().getIdentifiant().equals(idNaturePersonneVivier)
                && !personneDto.getNaturePersonne().getIdentifiant().equals(idNaturePersonneBeneficiaireVivier)) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }

        // si on est sur un vivier ou bénéficiaire vivier, on change automatiquement en prospect ou bénéficiaire prospect si les conditions sont respectés
        boolean hasNaturePersonneChanged = false;
        String ancienneNaturePersonne = "";
        String nouvelleNaturePersonne = "";
        if (personneDto.getNaturePersonne().getIdentifiant().equals(idNaturePersonneVivier)
                && validationPersonneUtil.verifierContrainteCreationProspect(personneDto)) {
            personneDto.getNaturePersonne().setIdentifiant(idNaturePersonneProspect);
            hasNaturePersonneChanged = true;
            final PersonnePhysiqueNature ancienneNature = personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(idNaturePersonneVivier);
            final PersonnePhysiqueNature nouvelleNature = personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(idNaturePersonneProspect);
            if (ancienneNature != null) {
                ancienneNaturePersonne = ancienneNature.getLibelle();
            }
            if (nouvelleNature != null) {
                nouvelleNaturePersonne = nouvelleNature.getLibelle();
            }
        }
        else if (personneDto.getNaturePersonne().getIdentifiant().equals(idNaturePersonneBeneficiaireVivier)
                && validationPersonneUtil.verifierContrainteCreationProspect(personneDto)) {
            personneDto.getNaturePersonne().setIdentifiant(idNaturePersonneBeneficiaireProspect);
            hasNaturePersonneChanged = true;
            final PersonnePhysiqueNature ancienneNature = personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(idNaturePersonneBeneficiaireVivier);
            final PersonnePhysiqueNature nouvelleNature = personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(idNaturePersonneBeneficiaireProspect);
            if (ancienneNature != null) {
                ancienneNaturePersonne = ancienneNature.getLibelle();
            }
            if (nouvelleNature != null) {
                nouvelleNaturePersonne = nouvelleNature.getLibelle();
            }
        }

        // Vérification des dépendances obligatoires
        PersonneCivilite civilite = null;
        if (personneDto.getCivilite() != null && personneDto.getCivilite().getIdentifiant() != null) {
            civilite = personneCiviliteDao.rechercherCiviliteParId(personneDto.getCivilite().getIdentifiant());
        }
        PersonneSegment segment = null;
        if (personneDto.getSegment() != null && personneDto.getSegment().getIdentifiant() != null) {
            segment = segmentDao.recherchePersonneSegmentParId(personneDto.getSegment().getIdentifiant());
        }
        PersonneSituationFamiliale situationFamiliale = null;
        if (personneDto.getSitFam() != null && personneDto.getSitFam().getIdentifiant() != null) {
            situationFamiliale = situationFamilialeDao.rechercherSituationFamiliale(personneDto.getSitFam().getIdentifiant());
        }
        PersonneReseau personneReseau = null;
        if (personneDto.getReseauVente() != null && personneDto.getReseauVente().getIdentifiant() != null) {
            personneReseau = personneReseauDao.rechercheReseauParId(personneDto.getReseauVente().getIdentifiant());
        }
        PersonneCSP personneCSP = null;
        if (personneDto.getCsp() != null && personneDto.getCsp().getIdentifiant() != null) {
            personneCSP = cspDao.recherchePersonneCSPParId(personneDto.getCsp().getIdentifiant());
        }
        final PersonnePhysiqueNature naturePersonne =
            personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(personneDto.getNaturePersonne().getIdentifiant());

        PersonneProfession profession = null;
        if (personneDto.getProfession() != null) {
            profession = personneProfessionDao.rechercherProfessionParId(personneDto.getProfession().getIdentifiant());
            if (profession == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PROFESSION_INEXISTENT_EN_BD));
            }
        }

        Agence agence = null;
        if (personneDto.getAgence() != null) {
            agence = agenceDao.rechercheAgenceParId(personneDto.getAgence().getIdentifiant());
        }

        Ressource ressource = null;
        if (personneDto.getCommercial() != null && personneDto.getCommercial().getIdentifiant() != null) {
            ressource = ressourceDao.rechercherRessourceParId(personneDto.getCommercial().getIdentifiant());
            if (ressource == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_COMMERCIAL_INEXISTANTE));
            }
            if (agence == null) {
                agence = ressource.getAgence();
            }
        }
        // Les autres dépendances
        Caisse caisse = null;
        if (personneDto.getInfoSante() != null && personneDto.getInfoSante().getCaisse() != null && personneDto.getInfoSante().getCaisse().getId() != null) {
            caisse = caisseDao.rechercheCaisseParId(personneDto.getInfoSante().getCaisse().getId());
            if (caisse == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_CAISSE_INEXISTENT_EN_BD));
            }
        }

        PersonneStatut statut = null;
        if (personneDto.getStatut() != null && personneDto.getStatut().getIdentifiant() != null) {
            statut = personneStatutDao.recherchePersonneStatutParId(personneDto.getStatut().getIdentifiant());
            if (statut == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_STATUT_INEXISTENT_EN_BD));
            }
        }

        final PersonnePhysique personneAmodifier = personnePhysiqueDao.rechercherPersonneParId(personneDto.getIdentifiant());

        // Vérificiation de la modification de la nature de la personne
        if (!personneAmodifier.getNature().getId().equals(personneDto.getNaturePersonne().getIdentifiant())) {
            final String roleBatch = squareMappingService.getRoleSquareBatch();

            // Si la personne passe de vivier à prospect alors la nature des bénéficiaires du chef de famille passe a bénéficiaire prospect si c'est possible
            if (personneAmodifier.getNature().getId().equals(idNaturePersonneVivier)
                && personneDto.getNaturePersonne().getIdentifiant().equals(idNaturePersonneProspect)) {
                // Récupération des bénéficiaires
                final RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();
                final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
                    new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
                criteres.setIdPersonne(personneDto.getIdentifiant());
                final List<Long> listeGroupements = new ArrayList<Long>();
                listeGroupements.add(squareMappingService.getIdGroupementFamille());
                criteres.setGroupements(listeGroupements);
                final List<Relation> listeRelations = relationDao.rechercherRelationsParCriteres(criterias);

                // Modification de la nature des bénéficiaires
                if (listeRelations != null && listeRelations.size() > 0) {
                    final PersonnePhysiqueNature natureBeneficiaireProspect =
                        personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(squareMappingService.getIdNaturePersonneBeneficiaireProspect());
                    for (Relation relation : listeRelations) {
                        PersonnePhysique beneficiaire;
                        if (personneAmodifier.getId().equals(relation.getPersonneSource().getId())) {
                            beneficiaire = (PersonnePhysique) relation.getPersonneCible();
                        }
                        else {
                            beneficiaire = (PersonnePhysique) relation.getPersonneSource();
                        }
                        if (beneficiaire.getNature() != null && idNaturePersonneBeneficiaireVivier.equals(beneficiaire.getNature().getId())) {
                            if (beneficiaire.getCivilite() != null && beneficiaire.getCivilite().getId() != null
                                    && beneficiaire.getNom() != null && !StringUtils.isBlank(beneficiaire.getNom())
                                    && beneficiaire.getPrenom() != null && !StringUtils.isBlank(beneficiaire.getPrenom())
                                    && beneficiaire.getDateNaissance() != null) {
                                beneficiaire.setNature(natureBeneficiaireProspect);
                            }
                        }
                    }
                }
            }
            else if (personneAmodifier.getNature().getId().equals(idNaturePersonneBeneficiaireVivier)
                    && personneDto.getNaturePersonne().getIdentifiant().equals(idNaturePersonneBeneficiaireProspect)) {
                // possibiliter de passer automatiquement de bénéficiaire vivier à bénéficiaire prospect
            }
            // Le passage adhérent -> prospect ou prospect -> adhérent n'est possible que par un batch
            else if (((personneAmodifier.getNature().getId().equals(squareMappingService.getIdNaturePersonneAdherent())
                    && personneDto.getNaturePersonne().getIdentifiant().equals(idNaturePersonneProspect))
                    || (personneAmodifier.getNature().getId().equals(idNaturePersonneProspect)
                            && personneDto.getNaturePersonne().getIdentifiant().equals(squareMappingService.getIdNaturePersonneAdherent())))
                && (roleBatch == null || !hasRole(roleBatch))) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_MODIFICATION_NATURE_PERSONNE_IMPOSSIBLE));
            }
            else if (!personneDto.getNaturePersonne().getIdentifiant().equals(squareMappingService.getIdNaturePersonneDecede())) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_MODIFICATION_NATURE_PERSONNE_IMPOSSIBLE));
            }
            else if (!personneAmodifier.getNature().getId().equals(squareMappingService.getIdNaturePersonneDecede())) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_MODIFICATION_NATURE_PERSONNE_IMPOSSIBLE));
            }
        }

        // Tout semble ok, on peut commencer les modifications
        mapperDozerBean.map(personneDto, personneAmodifier);

        personneAmodifier.setCivilite(civilite);
        personneAmodifier.setProfession(profession);
        personneAmodifier.setNature(naturePersonne);
        personneAmodifier.setSegment(segment);
        personneAmodifier.setSituationFamiliale(situationFamiliale);
        personneAmodifier.setReseau(personneReseau);
        personneAmodifier.setStatut(statut);
        if (personneCSP != null) {
            personneAmodifier.setCsp(personneCSP);
        }

        // on mappe les infos de santé à la main
        if (personneDto.getInfoSante() != null && personneDto.getInfoSante().getIdReferent() != null
            && !personneDto.getInfoSante().getIdReferent().equals(personneDto.getIdentifiant())) {
            // Récupération du referent
            final PersonnePhysique referent = personnePhysiqueDao.rechercherPersonneParId(personneDto.getInfoSante().getIdReferent());
            if (referent == null) {
                logger.error("La personne " + personneDto.getInfoSante().getIdReferent() + " n'existe pas");
                throw new TechnicalException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
            }
            // on recupere les infos santé du referent
            personneAmodifier.setInfoSante(referent.getInfoSante());
        }
        else {
            // si il n'a pas encore d'info de santé, ou si il n'est pas le referent de ces infos de sante
            if (personneAmodifier.getInfoSante() == null
                || (personneAmodifier.getInfoSante().getReferent() != null && !personneAmodifier.getInfoSante().getReferent().getId().equals(
                    personneDto.getIdentifiant()))) {
                personneAmodifier.setInfoSante(new InfoSante());
            }
            personneAmodifier.getInfoSante().setCaisse(caisse);
            if (personneDto.getInfoSante() != null && personneDto.getInfoSante().getNro() != null) {
	            final NumeroRoDto numeroRO = squareMappingService.convertirNroVersNss(personneDto.getInfoSante().getNro());
	            if (numeroRO != null) {
	                personneAmodifier.getInfoSante().setNumSecuriteSocial(numeroRO.getNumeroSS());
	                personneAmodifier.getInfoSante().setCleSecuriteSocial(numeroRO.getCleSS());
	            }
            }
            personneAmodifier.getInfoSante().setReferent(personneAmodifier);
        }

        // Les attributions de la personne
        if (personneAmodifier.getAttribution() == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_ATTRIBUTION_INEXISTANTE));
        }
        personneAmodifier.getAttribution().setAgence(agence);
        personneAmodifier.getAttribution().setRessource(ressource);
        personneAmodifier.setDateModification(Calendar.getInstance());

        final PersonneDto personneModifiee = rechercherPersonneParIdentifiant(personneAmodifier.getId());
        // ajout des informations pour spécifier si la nature de la personne principale a changé
        // (vivier -> prospect ou bénéficiaire vivier -> bénéficiaire prospect)
        personneModifiee.setHasNaturePersonneChanged(hasNaturePersonneChanged);
        personneModifiee.setAncienneNaturePersonne(ancienneNaturePersonne);
        personneModifiee.setNouvelleNaturePersonne(nouvelleNaturePersonne);
        return personneModifiee;
    }

    /**
     * Recherche full text de message.
     * @param criteres le texte à rechercher.
     * @return la liste des personnes.
     */
    public RemotePagingResultsDto<PersonneSimpleDto> rechercherPersonneFullTextParCriteres(RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres) {
        try {
            if (criteres == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SEARCH_DTO_NULL));
            }

            final ResultatPaginationFullText<PersonnePhysique> resultatPagination = personnePhysiqueDao.rechercheFullTextPersonne(criteres);
            logger.info("Demande de recherche en FULL TEXT NOMBRE DE RESULTAT " + resultatPagination.getListeResultats().size() + "/"
                + resultatPagination.getNombreTotalDeResultat());
            final List<PersonnePhysique> resultPersonne = resultatPagination.getListeResultats();

            // Mapping PersonnePhysique -> PersonneSimpleDto
            final List<PersonneSimpleDto> resultDto = new ArrayList<PersonneSimpleDto>();

            for (PersonnePhysique personnePhysique : resultPersonne) {
                final PersonneSimpleDto personneSimple = mapperDozerBean.map(personnePhysique, PersonneSimpleDto.class);

                // NRO :
                personneSimple
                        .setNro(personnePhysique.getInfoSante() != null && personnePhysique.getInfoSante().getNumSecuriteSocial() != null ? personnePhysique
                                .getInfoSante().getNumSecuriteSocial()
                            + personnePhysique.getInfoSante().getCleSecuriteSocial() : null);

                if (personnePhysique.getAdresses() != null) {
                    // Récupération de l'adresse principale du client.
                    for (Adresse adr : personnePhysique.getAdresses()) {
                        // Adresse Principale.
                        if (adr.getNature() != null && adr.getNature().getId().equals(squareMappingService.getIdNatureAdressePrincipale())) {
                            // Récupération du code postal - commune
                            if (adr.getCodePostalCommune() != null) {
                                final CodePostalCommuneDto codePostalCommuneDto = mapperDozerBean.map(adr.getCodePostalCommune(), CodePostalCommuneDto.class);
                                personneSimple.setCodePostalCommune(codePostalCommuneDto);
                            }
                        }
                    }
                }
                // Traitement du flag doublon (recherche des personnes ayant le même nom, prénom, date de naissance en dehors de la personne en cours)
                if ((personnePhysique.getNom() != null && !"".equals(personnePhysique.getNom()))
                        || (personnePhysique.getPrenom() != null && !"".equals(personnePhysique.getPrenom()))
                        || personnePhysique.getDateNaissance() != null) {
                    personneSimple.setDoublon(isPersonnePossedeDoublon(personnePhysique));
                }
                else {
                    personneSimple.setDoublon(false);
                }

                resultDto.add(personneSimple);
            }
            final RemotePagingResultsDto<PersonneSimpleDto> result = new RemotePagingResultsDto<PersonneSimpleDto>();
            result.setListResults(resultDto);
            result.setTotalResults(resultatPagination.getNombreTotalDeResultat());
            return result;
        }
        catch (ParseException e) {
            logger.error(e);
            throw new BusinessException(e);
        }
    }

    /**
     * Recherche une personne par son identifiant et renvoi toutes les informations de cette personne.
     * @param id l'identifiant de la personne à retrouver
     * @return la personne simple dto contenant toutes les informations de la personne
     */
    public PersonneDto rechercherPersonneParIdentifiant(Long id) {
        final PersonnePhysique personne = personnePhysiqueDao.rechercherPersonneParId(id);
        if (personne == null) {
            logger.error(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE) + " : " + id);
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }
        final PersonneDto personneDto = mapperDozerBean.map(personne, PersonneDto.class);
        if (personne.getInfoSante() != null) {
            final String nro =
                squareMappingService.convertirNssVersNro(personne.getInfoSante().getNumSecuriteSocial(), personne.getInfoSante().getCleSecuriteSocial());
            if (personneDto.getInfoSante() == null) {
                personneDto.setInfoSante(new InfoSanteDto());
            }
            personneDto.getInfoSante().setNro(nro);
        }
        if (personne.getDateNaissance() != null) {
            personneDto.setAge(calculerAge(personne.getDateNaissance()));
        }
        return personneDto;
    }

    /**
     * Recherche d'une personneSimple par son identifiant.
     * @param id l'identifiant de la personne à retrouver.
     * @return la personne simple dto contenant les informations de la personne.
     */
    public PersonneSimpleDto rechercherPersonneSimpleParIdentifiant(Long id) {
        if (id == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_NULL));
        }

        final PersonnePhysique personneBase = personnePhysiqueDao.rechercherPersonneParId(id);
        if (personneBase == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }
        final PersonneSimpleDto personne = mapperDozerBean.map(personneBase, PersonneSimpleDto.class);

        final AdresseCriteresRechercheDto criteresAdresses = new AdresseCriteresRechercheDto();
        criteresAdresses.setIdPersonne(id);
        criteresAdresses.setIdNature(squareMappingService.getIdNatureAdressePrincipale());
        final List<Adresse> adresses = adresseDao.rechercherAdresseParCritere(criteresAdresses);
        if (adresses != null && adresses.size() > 0) {
            // Récupération du code postal et de la commune
            if (adresses.get(0).getCodePostalCommune() != null) {
                final CodePostalCommuneDto codePostalCommuneDto = mapperDozerBean.map(adresses.get(0).getCodePostalCommune(), CodePostalCommuneDto.class);
                personne.setCodePostalCommune(codePostalCommuneDto);
            }
        }
        // Mise à jour de la situation familiale
        // Recherche des relations actives de la personne.
        final RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
        criteres.setIdPersonne(id);
        final List<Long> listeTypes = new ArrayList<Long>();
        listeTypes.add(squareMappingService.getIdTypeRelationConjoint());
        listeTypes.add(squareMappingService.getIdTypeRelationEnfant());
        criteres.setTypes(listeTypes);
        criteres.setActif(true);
        final List<Relation> relationsPersonne = relationDao.rechercherRelationsParCriteres(criterias);

        // Traitement des relations pour trouver la situation famililale
        String situationFamiliale = "";
        int nombreEnfant = 0;
        for (Relation relation : relationsPersonne) {
            if (relation.getType().getId().equals(squareMappingService.getIdTypeRelationConjoint())) {
                situationFamiliale = messageSourceUtil.get(PersonnePhysiqueKeyUtil.LIBELLE_EN_COUPLE);
            }
            if (relation.getType().getId().equals(squareMappingService.getIdTypeRelationEnfant()) && relation.getPersonneSource().getId().equals(id)) {
                nombreEnfant++;
            }
        }
        if ("".equals(situationFamiliale)) {
            situationFamiliale = messageSourceUtil.get(PersonnePhysiqueKeyUtil.LIBELLE_CELIBATAIRE);
        }
        if (nombreEnfant > 1) {
            situationFamiliale += ", " + nombreEnfant + " " + messageSourceUtil.get(PersonnePhysiqueKeyUtil.LIBELLE_ENFANTS);
        }
        else {
            situationFamiliale += ", " + nombreEnfant + " " + messageSourceUtil.get(PersonnePhysiqueKeyUtil.LIBELLE_ENFANT);
        }
        personne.setSituationFamiliale(situationFamiliale);

        if (personne.getDateNaissance() != null) {
            // Récupération de l'age de la personne
            personne.setAge(calculerAge(personne.getDateNaissance()) + " " + messageSourceUtil.get(PersonnePhysiqueKeyUtil.LIBELLE_AGE));
        }
        return personne;
    }

    /**
     * Calcule l'âge d'une personne à partir de sa date de naissance.
     * @param datedeNaissance la date de naissance de la personne.
     * @return l'age de la personne
     */
    private int calculerAge(Calendar datedeNaissance) {
        final Calendar dateNaissanceClonee = (Calendar) datedeNaissance.clone();
        final Calendar dateActuelle = Calendar.getInstance();
        int age = dateActuelle.get(Calendar.YEAR) - dateNaissanceClonee.get(Calendar.YEAR);

        dateNaissanceClonee.add(Calendar.YEAR, age);
        if (dateActuelle.before(dateNaissanceClonee)) {
            age--;
        }
        return age;
    }

    @Override
    public void mettreAJourInfosAdhesion(List<InfosPersonneSyncDto> listeInfos) {
        // on recupere chacune des personnes
        for (InfosPersonneSyncDto infosPersonneSyncDto : listeInfos) {
            if (infosPersonneSyncDto.getIdPersonne().equals(infosPersonneSyncDto.getIdReferent())) {
                final PersonnePhysique personne = personnePhysiqueDao.rechercherPersonneParId(infosPersonneSyncDto.getIdPersonne());
                // on recree les infoSante si il n'en possède pas ou que le referent à changé
                if (personne.getInfoSante() == null || (!personne.getInfoSante().getReferent().getId().equals(infosPersonneSyncDto.getIdReferent()))) {
                    personne.setInfoSante(new InfoSante());
                }
                personne.getInfoSante().setNumSecuriteSocial(infosPersonneSyncDto.getNumeroSecuriteSociale());
                personne.getInfoSante().setCleSecuriteSocial(infosPersonneSyncDto.getCleSecuriteSociale());
                if (infosPersonneSyncDto.getEidCaisse() != null) {
                    personne.getInfoSante().setCaisse(caisseDao.rechercheCaisseParId(infosPersonneSyncDto.getEidCaisse()));
                }
                else {
                    personne.getInfoSante().setCaisse(null);
                }
                personne.getInfoSante().setReferent(personne);
                personne.setDateModification(Calendar.getInstance());
            }
        }
        // on parcours ensuite ceux qui ont des referents
        for (InfosPersonneSyncDto infosPersonneSyncDto : listeInfos) {
            if (!infosPersonneSyncDto.getIdPersonne().equals(infosPersonneSyncDto.getIdReferent())) {
                final PersonnePhysique personne = personnePhysiqueDao.rechercherPersonneParId(infosPersonneSyncDto.getIdPersonne());
                // il a un referent
                personne.setInfoSante(personnePhysiqueDao.rechercherPersonneParId(infosPersonneSyncDto.getIdReferent()).getInfoSante());
                personne.setDateModification(Calendar.getInstance());
            }
        }
    }

    /**
     * Teste si une personne possède des doublons (recherche sur nom, prénom, date de naissance).
     * @param personne la personne
     * @return true si la personne possède des doublons, false sinon
     */
    private boolean isPersonnePossedeDoublon(PersonnePhysique personne) {
        // Création du critère de recherche
        final PersonneCriteresRechercheDto criteresPersonne = new PersonneCriteresRechercheDto();
        criteresPersonne.setNom(personne.getNom());
        criteresPersonne.setPrenom(personne.getPrenom());
        criteresPersonne.setDateNaissance(personne.getDateNaissance());
        criteresPersonne.setRechercheStricte(true);
        final List<Long> listeIdsPersonnesIgnorees = new ArrayList<Long>();
        listeIdsPersonnesIgnorees.add(personne.getId());
        criteresPersonne.setIdPersonnesAIgnorer(listeIdsPersonnesIgnorees);
        // On recherche s'il y a au moins une personne qui correspond aux critères
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteresPersonne, 0, 1);
        try {
            final ResultatPaginationFullText<PersonnePhysique> resultatsDoublons = personnePhysiqueDao.rechercheFullTextPersonne(criteres);
            return resultatsDoublons.getNombreTotalDeResultat() > 0;
        }
        catch (ParseException e) {
            logger.error(e);
            throw new BusinessException(e);
        }
    }

    /**
     * Récupère les coordonnées simples d'une personne (adresse principale, téléphone, portable, mail).
     * @param idPersonne l'identifiant de la personne
     * @return l'objet contenant les coordonnées
     */
    private CoordonneesSimples rechercherCoordonneesSimplePersonne(Long idPersonne) {
        final CoordonneesSimples coordonneesSimples = new CoordonneesSimples();
        // Récupération de l'ensemble des coordonnées de la personne
        final CoordonneesDto coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(idPersonne);
        // Récupération de l'adresse principale
        coordonneesSimples.setAdressePrincipale(rechercherAdressePrincipaleEnCours(coordonneesDto.getAdresses()));
        // Récupération des téléphones
        coordonneesSimples.setTelephone(rechercherTelephonePrincipal(coordonneesDto.getTelephones()));
        coordonneesSimples.setTelephonePortable(rechercherTelephoneMobile(coordonneesDto.getTelephones()));
        // Récupération du mail
        coordonneesSimples.setEmail(rechercherEmail(coordonneesDto.getEmails()));
        return coordonneesSimples;
    }

    /**
     * Recherche l'adresse principale en cours parmi une liste d'adresses.
     * @param listeAdresses la liste d'adresses
     * @param idNatureAdressePrincipale l'identifiant de la nature d'une adresse principale
     * @return l'adresse, null si pas trouvée
     */
    private AdresseDto rechercherAdressePrincipaleEnCours(List<AdresseDto> listeAdresses) {
        final Long idNatureAdressePrincipale = squareMappingService.getIdNatureAdressePrincipale();
        final Calendar aujourdhui = Calendar.getInstance();
        // Parcours de la liste d'adresses
        if (listeAdresses != null) {
            for (AdresseDto adresse : listeAdresses) {
                // Si c'est une adresse principale
                if (adresse.getNature() != null && idNatureAdressePrincipale.equals(adresse.getNature().getIdentifiant())) {
                    // Test si l'adresse est active
                    if (adresse.getDateDebut().before(aujourdhui) && (adresse.getDateFin() == null || adresse.getDateFin().after(aujourdhui))) {
                        return adresse;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Recherche le téléphone principal parmi une liste de téléphones.
     * @param listeTelephones la liste de téléphones
     * @return le téléphone principal, null si pas trouvé
     */
    private TelephoneDto rechercherTelephonePrincipal(List<TelephoneDto> listeTelephones) {
        final List<Long> listeIdsNaturesTelephoneMobile = squareMappingService.getListeIdsNaturesTelephoneMobile();
        if (listeTelephones != null) {
            for (TelephoneDto telephone : listeTelephones) {
                if (telephone.getNature() != null) {
                    if (!listeIdsNaturesTelephoneMobile.contains(telephone.getNature().getIdentifiant())) {
                        return telephone;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Recherche le téléphone mobile parmi une liste de téléphones.
     * @param listeTelephones la liste de téléphones
     * @return le téléphone mobile, null si pas trouvé
     */
    private TelephoneDto rechercherTelephoneMobile(List<TelephoneDto> listeTelephones) {
        final List<Long> listeIdsNaturesTelephoneMobile = squareMappingService.getListeIdsNaturesTelephoneMobile();
        if (listeTelephones != null) {
            for (TelephoneDto telephone : listeTelephones) {
                if (telephone.getNature() != null) {
                    if (listeIdsNaturesTelephoneMobile.contains(telephone.getNature().getIdentifiant())) {
                        return telephone;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Recherche le mail parmi une liste de mails.
     * @param listeEmails la liste de mails
     * @return le téléphone mobile, null si pas trouvé
     */
    private EmailDto rechercherEmail(List<EmailDto> listeEmails) {
        // Récupère le premier de la liste
        if (listeEmails != null && !listeEmails.isEmpty()) {
            return listeEmails.get(0);
        }
        return null;
    }

    /**
     * Détermine si l'utilisateur connecté a un certains role.
     * @param role le role à tester.
     * @return true si l'utilisateur a ce role, false si non.
     */
    private boolean hasRole(String role) {
        // Récupération des rôles de l'utilisateur connecté
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        final GrantedAuthority[] authorities = authentication.getAuthorities();

        for (int i = 0; i < authorities.length; i++) {
            final String authority = authorities[i].getAuthority();
            if (role.equals(authority)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public PersonneDto creerPersonnePhysiqueVivier(PersonneCreationVivierDto vivierDto) {
        if (vivierDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_DTO_NULL));
        }
        if (vivierDto.getCivilite() == null || vivierDto.getCivilite().getIdentifiant() == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_CIVILITE_NULL));
        }
        if (StringUtils.isBlank(vivierDto.getNom())) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NOM_NULL));
        }
        if (StringUtils.isBlank(vivierDto.getPrenom())) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_NULL));
        }
        if (StringUtils.isBlank(vivierDto.getEmail())) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_EMAIL_NULL));
        }

        final Long idNaturePersonneVivier = squareMappingService.getIdNaturePersonneVivier();

        // on cree la personne
        final PersonneDto personneDto = mapperDozerBean.map(vivierDto, PersonneDto.class);
        personneDto.setNaturePersonne(new IdentifiantLibelleDto(idNaturePersonneVivier));

        // email
        EmailDto emailDto = null;
        if (vivierDto.getEmail() != null) {
            emailDto = new EmailDto();
            emailDto.setAdresse(vivierDto.getEmail());
            emailDto.setNatureEmail(new IdentifiantLibelleDto(squareMappingService.getIdNatureEmailPersonnel()));
        }
        final Email emailPersonnel = mapperEmailPourCreation(emailDto);
        // telephone
        TelephoneDto telephoneDto = null;
        Telephone telephone1 = null;
        if (vivierDto.getTelephone() != null) {
            telephoneDto = new TelephoneDto();
            telephoneDto.setNumero(vivierDto.getTelephone());
            final PaysSimpleDto pays = new PaysSimpleDto();
            pays.setId(squareMappingService.getIdPaysFrance());
            telephoneDto.setPays(pays);
            Long idNatureTelephone = null;
            if (vivierDto.getTelephone() != null) {
                if (vivierDto.getTelephone().trim().startsWith("06") && vivierDto.getTelephone().trim().startsWith("07")) {
                    // Mapping du téléphone portable
                    idNatureTelephone = squareMappingService.getIdNatureMobilePrive();
                }
                else {
                    // Téléphone fixe
                    idNatureTelephone = squareMappingService.getIdNatureTelephoneFixe();
                }
            }
            telephoneDto.setNature(new IdentifiantLibelleDto(idNatureTelephone));
            telephone1 = mapperTelephonePourCreation(telephoneDto);
        }
        // adresse
        AdresseDto adresseDto = null;
        Adresse adressePrincipale = null;
        if (vivierDto.getCodePostalCommune() != null && vivierDto.getCodePostalCommune().getIdentifiant() != null) {
            adresseDto = new AdresseDto();
            adresseDto.setNomVoie(squareMappingService.getLibelleNonRenseigne());
            adresseDto.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdressePrincipale()));
            adresseDto.setCodePostalCommune(vivierDto.getCodePostalCommune());
            adresseDto.setPays(new IdentifiantLibelleBooleanDto(squareMappingService.getIdPaysFrance()));
            adressePrincipale = mapperAdressePourCreation(adresseDto);
        }

        if (vivierDto.getIdCommercial() != null) {
            final DimensionRessourceDto commercial = new DimensionRessourceDto();
            commercial.setIdentifiant(vivierDto.getIdCommercial());
            personneDto.setCommercial(commercial);
            personneDto.setCreateur(commercial);
        }

        final PersonnePhysique personneACreer = mapperPersonnePourCreation(personneDto, adressePrincipale, emailPersonnel, telephone1, null);
        // Création de la personne
        personnePhysiqueDao.creerPersonnePhysique(personneACreer);

        return rechercherPersonneParIdentifiant(personneACreer.getId());
    }

    @Override
    public PersonneDto creerPersonnePhysiqueAssureSocial(PersonneCreationAssureSocialDto assureSocialDto) {
        if (assureSocialDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_DTO_NULL));
        }

        final RapportDto rapport = new RapportDto();
        final ValidationExpressionProp[] propsPersonne =
            new ValidationExpressionProp[] {new ValidationExpressionProp("civilite", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_CIVILITE_NULL)),
                new ValidationExpressionProp(CHAMP_NOM, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_NOM_NULL)),
                new ValidationExpressionProp(CHAMP_PRENOM, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_NULL)),
                new ValidationExpressionProp(CHAMP_DATE_NAISSANCE, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_NULL))};
        validationExpressionUtil.verifierSiVide(rapport, assureSocialDto, propsPersonne);

        // Vérification de nom et prenom
        validationPersonneUtil.verifierNomPrenomPersonne(rapport, assureSocialDto.getNom(), assureSocialDto.getPrenom(), assureSocialDto.getClass());

        // Si une date de naissance est spécifiée
        if (assureSocialDto.getDateNaissance() != null) {
            // On vérifie que la date de naissance indiquée est valide
            if (!validationExpressionUtil.isDateDeNaissanceValide(assureSocialDto.getDateNaissance())) {
                rapport.ajoutRapport("PersonneDto.dateNaissance", messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_INVALIDE), true);
            }
        }

        // caisse obligatoire
        final CaisseSimpleDto caisse = assureSocialDto.getInfoSante() != null ? assureSocialDto.getInfoSante().getCaisse() : null;
        validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_CAISSE_NULL), caisse, assureSocialDto
                .getClass().getSimpleName()
            + ".infoSante.caisse");

        // Numéro RO.
        final NumeroRoDto nroDto =
            squareMappingService.convertirNroVersNss(assureSocialDto.getInfoSante() != null ? assureSocialDto.getInfoSante().getNro() : "");
        validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_NUMERO_SECURITE_SOCIALE_INVALIDE), nroDto,
            assureSocialDto.getClass().getSimpleName() + ".infoSante.nro");
        if (nroDto != null) {
            validationExpressionUtil.verifierNumeroSecuriteSociale(rapport, null, messageSourceUtil
                    .get(MESSAGE_ERROR_SAVE_PERSONNE_NUMERO_SECURITE_SOCIALE_INVALIDE), assureSocialDto.getClass().getSimpleName() + ".infoSante.nro", nroDto
                    .getNumeroSS(), nroDto.getCleSS());
        }

        if (rapport.getEnErreur()) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }

        final Long idNaturePersonneAssureSocial = squareMappingService.getIdNaturePersonneAssureSocial();

        // on cree la personne
        final PersonneDto personneDto = mapperDozerBean.map(assureSocialDto, PersonneDto.class);
        personneDto.setNaturePersonne(new IdentifiantLibelleDto(idNaturePersonneAssureSocial));

        final DimensionRessourceDto commercial = new DimensionRessourceDto();
        commercial.setIdentifiant(ressourceHabilitationUtil.getUtilisateurConnecte().getId());
        commercial.setIdentifiantExterieur(ressourceHabilitationUtil.getUtilisateurConnecte().getIdentifiantExterieur());
        personneDto.setCommercial(commercial);
        personneDto.setCreateur(commercial);

        final PersonnePhysique personneACreer = mapperPersonnePourCreation(personneDto, null, null, null, null);

        // Création de la personne
        personnePhysiqueDao.creerPersonnePhysique(personneACreer);

        return rechercherPersonneParIdentifiant(personneACreer.getId());
    }

    @Override
    public PersonneDto rechercherPersonneParIdentifiantExterieur(String id) {
        final PersonnePhysique personne = personnePhysiqueDao.rechercherPersonneParIdExt(id);
        if (personne == null) {
            return null;
        }
        final PersonneDto personneDto = mapperDozerBean.map(personne, PersonneDto.class);
        if (personne.getInfoSante() != null) {
            final String nro =
                squareMappingService.convertirNssVersNro(personne.getInfoSante().getNumSecuriteSocial(), personne.getInfoSante().getCleSecuriteSocial());
            if (personneDto.getInfoSante() == null) {
                personneDto.setInfoSante(new InfoSanteDto());
            }
            personneDto.getInfoSante().setNro(nro);
        }
        personneDto.setAge(calculerAge(personne.getDateNaissance()));
        return personneDto;
    }

    @Override
    public FichierDto exporterRecherchePersonneFullTextParCriteres(RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres) {
        final String nomFichier = "exportRecherchePersonnesPhysiques.xls";
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        final String[] entetes =
            new String[] {"Numéro Client", CHAMP_NOM, CHAMP_PRENOM, "Date Naissance", "Nature", "Segment", "Agence", "Code Postal", "Ville", "Doublon"};
        final Integer[] entetesWidth = new Integer[] {5500, 5500, 5500, 5500, 5500, 5500, 5500, 5500, 5500, 5500};

        final DocumentXls documentXls = new DocumentXls(nomFichier, entetes, entetesWidth);

        // on recupere le resultat de la recherche par pagination
        int start = 0;
        RemotePagingResultsDto<PersonneSimpleDto> resultats;
        do {
            // on redefinit la pagination
            criteres.setFirstResult(start);
            criteres.setMaxResult(paginationExportRecherche);
            resultats = rechercherPersonneFullTextParCriteres(criteres);
            start += resultats.getListResults().size();
            // on integre les resultats au fichier
            for (PersonneSimpleDto personne : resultats.getListResults()) {
                // Récupération du code postal et de la commune
                String codePostal = null;
                String commune = null;
                if (personne.getCodePostalCommune() != null) {
                    if (personne.getCodePostalCommune().getCodePostal() != null) {
                        codePostal = personne.getCodePostalCommune().getCodePostal().getLibelle();
                    }
                    if (personne.getCodePostalCommune().getCommune() != null) {
                        commune = personne.getCodePostalCommune().getCommune().getLibelle();
                    }
                }
                final String[] infosLignes =
                    new String[] {personne.getNumeroClient(), personne.getNom(), personne.getPrenom(),
                        personne.getDateNaissance() != null ? sdf.format(personne.getDateNaissance().getTime()) : "",
                        personne.getNature() != null ? personne.getNature().getLibelle() : "",
                        personne.getSegment() != null ? personne.getSegment().getLibelle() : "",
                        personne.getAgence() != null ? personne.getAgence().getLibelle() : "", codePostal != null ? codePostal : "",
                        commune != null ? commune : "", personne.isDoublon() ? "Oui" : "Non"};
                documentXls.ajouterLigne(infosLignes);
            }
        }
        while (start < resultats.getTotalResults());

        return documentXls.cloturerDocument();
    }

    @Override
    public void transformerVivierEnProspect(Long uidPersonne) {
        final PersonneDto personneDto = rechercherPersonneParIdentifiant(uidPersonne);
        final Long idNaturePersonneVivier = squareMappingService.getIdNaturePersonneVivier();
        // si on est sur un vivier, on change en prospect si les conditions sont respectées
        if (personneDto.getNaturePersonne().getIdentifiant().equals(idNaturePersonneVivier)
                && validationPersonneUtil.verifierContrainteCreationProspect(personneDto)) {
            final Long idNaturePersonneProspect = squareMappingService.getIdNaturePersonneProspect();
            final PersonnePhysiqueNature naturePersonne = personnePhysiqueNatureDao.rechercherPersonnePhysiqueParId(idNaturePersonneProspect);
            final PersonnePhysique personnePrincipale = personnePhysiqueDao.rechercherPersonneParId(personneDto.getIdentifiant());
            personnePrincipale.setNature(naturePersonne);
        }
    }

    @Override
    public PersonneDto creerPersonnePhysiqueGestionVivier(PersonneDto personneDto, List<BeneficiaireDto> listeBeneficiaire, AdresseDto adresse,
        EmailDto email, TelephoneDto telephone) {
        // On détermine s'il s'agit d'un vivier ou d'un prospect
        final RapportDto rapport = new RapportDto();
        TelephoneDto telephoneFixeDto = null;
        TelephoneDto telephonePortableDto = null;
        if (telephone != null && telephone.getNature() != null) {
            if (squareMappingService.getIdNatureTelephoneFixe().equals(telephone.getNature().getIdentifiant())) {
                telephoneFixeDto = telephone;
            }
            else if (squareMappingService.getIdNatureMobilePrive().equals(telephone.getNature().getIdentifiant())) {
                telephonePortableDto = telephone;
            }
        }
        validationPersonneUtil.verifierContrainteCreationProspect(personneDto, email, adresse, telephoneFixeDto, telephonePortableDto, rapport);
        final IdentifiantLibelleDto naturePersonne = new IdentifiantLibelleDto();
        if (rapport.getEnErreur()) {
            // Vivier
            naturePersonne.setIdentifiant(squareMappingService.getIdNaturePersonneVivier());
        }
        else {
            // Prospect
            naturePersonne.setIdentifiant(squareMappingService.getIdNaturePersonneProspect());
        }
        personneDto.setNaturePersonne(naturePersonne);

        // On mappe les coordonnées en objet du modèle
        Adresse adressePrincipale = null;
        Email emailPersonnel = null;
        Telephone telephoneFixe = null;
        Telephone telephonePortable = null;
        if (adresse != null) {
            adressePrincipale = mapperAdressePourCreation(adresse);
        }
        if (email != null) {
            emailPersonnel = mapperEmailPourCreation(email);
        }
        if (telephoneFixeDto != null) {
            telephoneFixe = mapperTelephonePourCreation(telephoneFixeDto);
        }
        if (telephonePortableDto != null) {
            telephonePortable = mapperTelephonePourCreation(telephonePortableDto);
        }
        final PersonnePhysique personnePrincipale = mapperPersonnePourCreation(personneDto, adressePrincipale, emailPersonnel, telephoneFixe,
            telephonePortable);
        personnePhysiqueDao.creerPersonnePhysique(personnePrincipale);

        // création des bénéficiaires
        if (listeBeneficiaire != null && listeBeneficiaire.size() > 0) {
            Long idConjoint = null;
            for (BeneficiaireDto beneficiaireDto : listeBeneficiaire) {
                // si le benef ne remplit pas au moins un des champs, on en tient pas compte
                if (!validationPersonneUtil.verifierContrainteBeneficiaireVivier(beneficiaireDto)) {
                    continue;
                }
                final RapportDto rapportBeneficiaire = new RapportDto();
                final List<BeneficiaireDto> listeBenef = new ArrayList<BeneficiaireDto>();
                listeBenef.add(beneficiaireDto);
                validationPersonneUtil.verifierContrainteCreationBeneficiaires(listeBenef, rapportBeneficiaire);
                final IdentifiantLibelleDto natureBeneficiaire = new IdentifiantLibelleDto();
                if (rapport.getEnErreur() || rapportBeneficiaire.getEnErreur()) {
                    // Bénéficiaire Vivier
                    natureBeneficiaire.setIdentifiant(squareMappingService.getIdNaturePersonneBeneficiaireVivier());
                }
                else {
                    // Bénéficiaire Prospect
                    natureBeneficiaire.setIdentifiant(squareMappingService.getIdNaturePersonneBeneficiaireProspect());
                }
                final PersonneDto beneficiairePersonneDto = mapperBeneficiaireEnPersonneDto(beneficiaireDto, personnePrincipale);
                beneficiairePersonneDto.setNaturePersonne(natureBeneficiaire);
                final PersonnePhysique beneficiaire = mapperPersonnePourCreation(beneficiairePersonneDto, adressePrincipale, emailPersonnel,
                            telephoneFixe, telephonePortable);
                // Création du bénéficiaire
                personnePhysiqueDao.creerPersonnePhysique(beneficiaire);
                // On créé la relation entre la personne principale et le bénéficiaire qui vient d'être créé
                final RelationDto relation = new RelationDto();
                relation.setDateDebut(Calendar.getInstance());
                relation.setIdPersonnePrincipale(personnePrincipale.getId());
                relation.setIdPersonne(beneficiaire.getId());
                final RelationType type = relationTypeDao.rechercherTypeRelationParId(beneficiaireDto.getTypeRelation().getIdentifiant());
                relation.setType(new IdentifiantLibelleDto(type.getId(), type.getLibelle()));
                personneService.creerRelation(relation);

                // Si le bénéficiaire est le conjoint, on récupère son id.
                if (type.getId().equals(squareMappingService.getIdTypeRelationConjoint())) {
                    idConjoint = beneficiaire.getId();
                }
                // Si c'est un enfant on crée la relation avec le conjoint si celà a été choisi
                else if (type.getId().equals(squareMappingService.getIdTypeRelationEnfant()) && idConjoint != null
                    && BooleanUtils.isTrue(beneficiaireDto.getRattacherAuxParents())) {
                    final RelationDto relationEnfantConjoint = new RelationDto();
                    relationEnfantConjoint.setDateDebut(Calendar.getInstance());
                    relationEnfantConjoint.setIdPersonnePrincipale(idConjoint);
                    relationEnfantConjoint.setIdPersonne(beneficiaire.getId());
                    relationEnfantConjoint.setType(new IdentifiantLibelleDto(type.getId(), type.getLibelle()));
                    personneService.creerRelation(relationEnfantConjoint);
                    if (logger.isDebugEnabled()) {
                        logger.debug(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_INFO_RELATION_CREEE));
                    }
                }
            }
        }

        return rechercherPersonneParIdentifiant(personnePrincipale.getId());
    }

    @Override
    public boolean hasMembreFamilleNaturePersonne(Long idPersonne, List<Long> listeIdsNaturesPersonne) {
        final PersonnePhysique personnePrincipale = personnePhysiqueDao.rechercherPersonneParId(idPersonne);
        if (personnePrincipale == null) {
            logger.error("La personne " + idPersonne + " n'existe pas");
            throw new TechnicalException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
        }

        // Vérification pour la personne principale
        if (personnePrincipale.getNature() != null && listeIdsNaturesPersonne.contains(personnePrincipale.getNature().getId())) {
            return true;
        }

        // Vérification pour les personnes de la famille
        final RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
        criteres.setIdPersonne(idPersonne);
        final List<Long> listeGroupements = new ArrayList<Long>();
        listeGroupements.add(squareMappingService.getIdGroupementFamille());
        criteres.setGroupements(listeGroupements);
        final List<Relation> listeRelations = relationDao.rechercherRelationsParCriteres(criterias);
        if (listeRelations != null && listeRelations.size() > 0) {
            for (Relation relation : listeRelations) {
                PersonnePhysique personneFamille = null;
                if (idPersonne.equals(relation.getPersonneSource().getId())) {
                    personneFamille = (PersonnePhysique) relation.getPersonneCible();
                }
                else {
                    personneFamille = (PersonnePhysique) relation.getPersonneSource();
                }
                if (personneFamille.getNature() != null && listeIdsNaturesPersonne.contains(personneFamille.getNature().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void indexerListePersonnesPhysiques(List<Long> listeIdsPersonnesAIndexer) {
        if (listeIdsPersonnesAIndexer != null && listeIdsPersonnesAIndexer.size() > 0) {
            for (Long idPersonne : listeIdsPersonnesAIndexer) {
                logger.debug("Indexation de Personne Physique id = " + idPersonne);
                final PersonnePhysique personnePhysique = personnePhysiqueDao.rechercherPersonneParId(idPersonne);
                if (personnePhysique == null) {
                    throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
                }
                // Modification d'un champ pour forcer la réindexation
                personnePhysique.setDateModification(Calendar.getInstance());
            }
        }
    }

    @Override
    public List<Long> rechercherIdPersonneParCriteres(PersonnePhysiqueIdCriteresRechercheDto criteres) {
        return personnePhysiqueDao.rechercherIdPersonneParCriteres(criteres);
    }

    /**
     * Modifie la valeur de messageSourceUtil.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Modifie la valeur de personnePhysiqueDao.
     * @param personnePhysiqueDao the personnePhysiqueDao to set
     */
    public void setPersonnePhysiqueDao(PersonnePhysiqueDao personnePhysiqueDao) {
        this.personnePhysiqueDao = personnePhysiqueDao;
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Modifie validation.
     * @param validation la nouvelle valeur de validation
     */
    public void setValidation(ValidationExpressionUtil validation) {
        this.validationExpressionUtil = validation;
    }

    /**
     * Modifie la valeur de personneCiviliteDao.
     * @param personneCiviliteDao the personneCiviliteDao to set
     */
    public void setPersonneCiviliteDao(PersonneCiviliteDao personneCiviliteDao) {
        this.personneCiviliteDao = personneCiviliteDao;
    }

    /**
     * Modifie la valeur de personneProfessionDao.
     * @param personneProfessionDao the personneProfessionDao to set
     */
    public void setPersonneProfessionDao(PersonneProfessionDao personneProfessionDao) {
        this.personneProfessionDao = personneProfessionDao;
    }

    /**
     * Modifie la valeur de relationDao.
     * @param relationDao the relationDao to set
     */
    public void setRelationDao(RelationDao relationDao) {
        this.relationDao = relationDao;
    }

    /**
     * Modifie typeVoieDao.
     * @param typeVoieDao la nouvelle valeur de typeVoieDao
     */
    public void setTypeVoieDao(TypeVoieDao typeVoieDao) {
        this.typeVoieDao = typeVoieDao;
    }

    /**
     * Modifie natureTelephoneDao.
     * @param natureTelephoneDao la nouvelle valeur de natureTelephoneDao
     */
    public void setNatureTelephoneDao(NatureTelephoneDao natureTelephoneDao) {
        this.natureTelephoneDao = natureTelephoneDao;
    }

    /**
     * Modifie paysDao.
     * @param paysDao la nouvelle valeur de paysDao
     */
    public void setPaysDao(PaysDao paysDao) {
        this.paysDao = paysDao;
    }

    /**
     * Modifie la valeur de emailNatureDao.
     * @param emailNatureDao the emailNatureDao to set
     */
    public void setEmailNatureDao(EmailNatureDao emailNatureDao) {
        this.emailNatureDao = emailNatureDao;
    }

    /**
     * Modifie la valeur de adresseNatureDao.
     * @param adresseNatureDao the adresseNatureDao to set
     */
    public void setAdresseNatureDao(AdresseNatureDao adresseNatureDao) {
        this.adresseNatureDao = adresseNatureDao;
    }

    /**
     * Modifie validationExpressionUtil.
     * @param validationExpressionUtil la nouvelle valeur de validationExpressionUtil
     */
    public void setValidationExpressionUtil(ValidationExpressionUtil validationExpressionUtil) {
        this.validationExpressionUtil = validationExpressionUtil;
    }

    /**
     * Modifie caisseDao.
     * @param caisseDao la nouvelle valeur de caisseDao
     */
    public void setCaisseDao(CaisseDao caisseDao) {
        this.caisseDao = caisseDao;
    }

    /**
     * Modifie cspDao.
     * @param cspDao la nouvelle valeur de cspDao
     */
    public void setCspDao(CspDao cspDao) {
        this.cspDao = cspDao;
    }

    /**
     * Modifie segmentDao.
     * @param segmentDao la nouvelle valeur de segmentDao
     */
    public void setSegmentDao(SegmentDao segmentDao) {
        this.segmentDao = segmentDao;
    }

    /**
     * Modifie situationFamilialeDao.
     * @param situationFamilialeDao la nouvelle valeur de situationFamilialeDao
     */
    public void setSituationFamilialeDao(SituationFamilialeDao situationFamilialeDao) {
        this.situationFamilialeDao = situationFamilialeDao;
    }

    /**
     * Modifie squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Setter sur le dao .
     * @param personneStatutDao the personneStatutDao to set
     */
    public void setPersonneStatutDao(PersonneStatutDao personneStatutDao) {
        this.personneStatutDao = personneStatutDao;
    }

    /**
     * Setter sur le dao .
     * @param personneReseauDao the personneReseauDao to set
     */
    public void setPersonneReseauDao(PersonneReseauDao personneReseauDao) {
        this.personneReseauDao = personneReseauDao;
    }

    /**
     * Setter sur le dao .
     * @param relationTypeDao the relationTypeDao to set
     */
    public void setRelationTypeDao(RelationTypeDao relationTypeDao) {
        this.relationTypeDao = relationTypeDao;
    }

    /**
     * Modifie la valeur de personnePhysiqueNatureDao.
     * @param personnePhysiqueNatureDao the personnePhysiqueNatureDao to set
     */
    public void setPersonnePhysiqueNatureDao(PersonnePhysiqueNatureDao personnePhysiqueNatureDao) {
        this.personnePhysiqueNatureDao = personnePhysiqueNatureDao;
    }

    /**
     * Modifie le dao du téléphone.
     * @param telephoneDao the telephoneDao to set
     */
    public void setTelephoneDao(TelephoneDao telephoneDao) {
        this.telephoneDao = telephoneDao;
    }

    /**
     * Modife le dao de l'email.
     * @param emailDao the emailDao to set
     */
    public void setEmailDao(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    /**
     * Modifie le dao de l'adresse.
     * @param adresseDao the adresseDao to set
     */
    public void setAdresseDao(AdresseDao adresseDao) {
        this.adresseDao = adresseDao;
    }

    /**
     * Modifie opportuniteService.
     * @param opportuniteService la nouvelle valeur de opportuniteService
     */
    public void setOpportuniteService(OpportuniteService opportuniteService) {
        this.opportuniteService = opportuniteService;
    }

    /**
     * Modifie actionService.
     * @param actionService la nouvelle valeur de actionService
     */
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    /**
     * Modifie personneService.
     * @param personneService la nouvelle valeur de personneService
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
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
     * Modifie la valeur de ressourceHabilitationUtil.
     * @param ressourceHabilitationUtil the ressourceHabilitationUtil to set
     */
    public void setRessourceHabilitationUtil(RessourceHabilitationUtil ressourceHabilitationUtil) {
        this.ressourceHabilitationUtil = ressourceHabilitationUtil;
    }

    /**
     * Setter function.
     * @param formatUtil the formatUtil to set
     */
    public void setFormatUtil(FormatUtil formatUtil) {
        this.formatUtil = formatUtil;
    }

    /**
     * Set the paginationExportRecherche value.
     * @param paginationExportRecherche the paginationExportRecherche to set
     */
    public void setPaginationExportRecherche(int paginationExportRecherche) {
        this.paginationExportRecherche = paginationExportRecherche;
    }

    /**
     * Définit la valeur de personneDao.
     * @param personneDao la nouvelle valeur de personneDao
     */
    public void setPersonneDao(PersonneDao personneDao) {
        this.personneDao = personneDao;
    }

    /**
     * Fixer la valeur.
     * @param numeroClientSquarePlugin the numeroClientSquarePlugin to set
     */
    public void setNumeroClientSquarePlugin(NumeroClientSquarePlugin numeroClientSquarePlugin) {
        this.numeroClientSquarePlugin = numeroClientSquarePlugin;
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
     * Définit la valeur de validationPersonneUtil.
     * @param validationPersonneUtil la nouvelle valeur de validationPersonneUtil
     */
    public void setValidationPersonneUtil(ValidationPersonneUtil validationPersonneUtil) {
        this.validationPersonneUtil = validationPersonneUtil;
    }

	/**
	 * Définit la valeur de espaceClientInternetService;
	 * @param espaceClientInternetService the espaceClientInternetService to set
	 */
	public void setEspaceClientInternetService(
			EspaceClientInternetService espaceClientInternetService) {
		this.espaceClientInternetService = espaceClientInternetService;
	}
}
