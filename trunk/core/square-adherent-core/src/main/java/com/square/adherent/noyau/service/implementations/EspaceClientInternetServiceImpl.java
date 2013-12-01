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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.adherent.noyau.dao.interfaces.EspaceClientInternetDao;
import com.square.adherent.noyau.dao.interfaces.EspaceClientInternetNatureDao;
import com.square.adherent.noyau.dto.adherent.connexion.IdentifiantsConnexionDto;
import com.square.adherent.noyau.dto.adherent.connexion.InformationConnexionSimpleDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratSimpleDto;
import com.square.adherent.noyau.dto.espace.client.EspaceClientInternetDto;
import com.square.adherent.noyau.model.data.espace.client.EspaceClientInternet;
import com.square.adherent.noyau.plugin.SmsPlugin;
import com.square.adherent.noyau.plugin.dto.sms.CreationSmsDto;
import com.square.adherent.noyau.plugin.dto.sms.SmsDto;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.adherent.noyau.service.interfaces.EspaceClientInternetService;
import com.square.adherent.noyau.util.MessageKeyUtil;
import com.square.adherent.noyau.util.PersonneUtil;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.mail.core.dto.emails.EmailAvecModeleDto;
import com.square.mail.core.dto.emails.InfosModeleEmailDto;
import com.square.mail.core.service.interfaces.email.EnvoiEmailMappingService;
import com.square.mail.core.service.interfaces.email.MailService;

/**
 * Implémentation de EspaceClientInternetService.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class EspaceClientInternetServiceImpl implements EspaceClientInternetService {

    /** DAO EspaceClientInternet. */
    private EspaceClientInternetDao espaceClientInternetDao;

    private EspaceClientInternetNatureDao espaceClientInternetNatureDao;

    /** Service Mapping adhérents. */
    private AdherentMappingService adherentMappingService;

    /** Service de mapping Square. */
    private SquareMappingService squareMappingService;

    /** Service Personne Square. */
    private PersonneService personneService;

    private PersonnePhysiqueService personnePhysiqueService;

    private MailService mailService;

    private EnvoiEmailMappingService envoiEmailMappingService;

    private SmsPlugin smsPlugin;

    private ContratService contratService;

    /** MessageSourceUtil. */
    private MessageSourceUtil messageSourceUtil;

    /** MapperDozerBean. */
    private MapperDozerBean mapperDozerBean;

    private StandardPBEStringEncryptor passwordEncryptor;

    private PersonneUtil personneUtil;

    private SimpleDateFormat sdf;

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public EspaceClientInternetDto identificationEspaceClient(IdentifiantsConnexionDto identifiants) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_DEMANDE_AUTHENTIFICATION_LOGIN, new String[] {String.valueOf(identifiants.getLogin())}));

        // Le login et le mot de passe doivent être renseignés
        if (StringUtils.isBlank(identifiants.getLogin()) || StringUtils.isBlank(identifiants.getMotDePasse())) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IDENTIFICATION_LOGIN_MDP_INCORRECTS));
        }

        identifiants.setLogin(identifiants.getLogin().trim());

        // Récupération de la connexion "Espace client" en fonction des identifiants
        final Long idNatureConnexionEspaceClient = adherentMappingService.getIdNatureConnexionEspaceClient();
        List<EspaceClientInternet> listeConnexions =
            espaceClientInternetDao.getListeEspaceClientInternetsByIdentifiantsAndNature(identifiants, idNatureConnexionEspaceClient, true);

        // Si on ne trouve pas de connexion et le numéro de client commence par 0, on réessaye sans tenir compte du 0.
        if (listeConnexions.size() == 0 && identifiants.getLogin().trim().startsWith("0")) {
            final String numeroClientSansZero = identifiants.getLogin().trim().substring(1);
            identifiants.setLogin(numeroClientSansZero);
            listeConnexions = espaceClientInternetDao.getListeEspaceClientInternetsByIdentifiantsAndNature(identifiants, idNatureConnexionEspaceClient, true);
        }

        // Si il n'y a pas qu'une seule connexion, on lève une erreur
        if (listeConnexions.size() != 1) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IDENTIFICATION_LOGIN_MDP_INCORRECTS));
        }

        // Récupération de la connexion et de la personne
        final EspaceClientInternet espaceClient = listeConnexions.get(0);
        // On vérifie que le mot de passe est correct
        // Si le mot de passe n'est pas encrypté
        if (identifiants.isMotDePasseEncrypte()) {
            try {
                // On encrypte le mot de passe avant de lancer la recherche
                final String mdpDecrypte = passwordEncryptor.decrypt(identifiants.getMotDePasse());
                identifiants.setMotDePasse(mdpDecrypte);
            } catch (EncryptionOperationNotPossibleException e) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_AUTHENTIFICATION,
                		new String[] {String.valueOf(identifiants.getLogin()), String.valueOf(identifiants.getMotDePasse())}));
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IDENTIFICATION_LOGIN_MDP_INCORRECTS));
            }
        }

        if (!StringUtils.equals(espaceClient.getMotDePasse(), identifiants.getMotDePasse())) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IDENTIFICATION_LOGIN_MDP_INCORRECTS));
        }

        // On récupère la personne à partir de Square
        final PersonneSimpleDto personne = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(espaceClient.getUidPersonne());
        // Si la personne est de nature adhérent
        if (squareMappingService.getIdNaturePersonneAdherent().equals(personne.getNature().getIdentifiant())) {
            // Un adhérent pourra toujours s'authentifier dans les deux ans suivant la date de résiliation de son contrat.
            // On recherche un contrat valide pour l'adhérent lui permettant de s'authentifier
            final ContratSimpleDto contrat = contratService.getContratSantePersonne(personne.getId());
            if (contrat == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_AUTHENTIFICATION_AUCUN_CONTRAT,
                		new String[] {String.valueOf(identifiants.getLogin())}));
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IDENTIFICATION_LOGIN_MDP_INCORRECTS));
            }
        }
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_PERSONNE_AUTHENTIFIE,
        		new String[] {String.valueOf(espaceClient.getUidPersonne()), espaceClient.getLogin()}));

        // Mise à jour des informations de visite si l'authentification s'est déroulée correctement
        if (Boolean.TRUE.equals(identifiants.getMettreAJourInfosConnexion())) {
            final Calendar dateDuJour = Calendar.getInstance();
            sdf = new SimpleDateFormat(messageSourceUtil.get(MessageKeyUtil.FORMAT_DATE_DD_MM_YYYY_HH_MM_SS));
            // Si la date de première visite est nulle, on renseigne la date du jour
            if (espaceClient.getDatePremiereVisite() == null) {
                espaceClient.setDatePremiereVisite(dateDuJour);
            }
            espaceClient.setDateDerniereVisite(dateDuJour);
            espaceClient.setDateModification(dateDuJour);
            final Integer nbVisites = espaceClient.getNbVisites();
            espaceClient.setNbVisites(nbVisites == null ? 1 : nbVisites + 1);
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MAJ_STATS_ESPACE_CLIENT,
            		new String[] {sdf.format(espaceClient.getDatePremiereVisite().getTime()),
            					  sdf.format(espaceClient.getDateDerniereVisite().getTime()),
            					  String.valueOf(espaceClient.getNbVisites())}));
        }
        // Mapping de l'objet de retour
        return mapperDozerBean.map(espaceClient, EspaceClientInternetDto.class);
    }

    @Override
    public InformationConnexionSimpleDto getInfoConnexionSimpleByNumClient(String numClient) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_INFO_CONNECTION_SIMPLE_CLIENT,
        		new String[] {String.valueOf(numClient)}));

        // Le login doit être renseigné
        if (numClient == null || "".equals(numClient.trim())) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IDENTIFICATION_LOGIN_INCORRECT));
        }

        final IdentifiantsConnexionDto identifiants = new IdentifiantsConnexionDto();
        identifiants.setLogin(numClient.trim());

        // Récupération de la connexion
        final Long idNatureConnexionEspaceClient = adherentMappingService.getIdNatureConnexionEspaceClient();
        List<EspaceClientInternet> listeConnexions =
            espaceClientInternetDao.getListeEspaceClientInternetsByIdentifiantsAndNature(identifiants, idNatureConnexionEspaceClient, null);

        // Si on ne trouve pas de connexion et le numéro de client commence par 0, on réessaye sans tenir compte du 0.
        if (listeConnexions.size() == 0 && identifiants.getLogin().trim().startsWith("0")) {
            final String numeroClientSansZero = identifiants.getLogin().trim().substring(1);
            identifiants.setLogin(numeroClientSansZero);
            listeConnexions = espaceClientInternetDao.getListeEspaceClientInternetsByIdentifiantsAndNature(identifiants, idNatureConnexionEspaceClient, true);
        }

        // Il doit y avoir une connexion pour le client demandé
        if (listeConnexions.size() != 1) {
            throw new BusinessException(messageSourceUtil
                    .get(MessageKeyUtil.ERROR_MESSAGE_ESPACE_CLIENT_INTERNET_ADHERENT_INEXISTANT, new String[] {numClient}));
        }

        final InformationConnexionSimpleDto infosConnexion = new InformationConnexionSimpleDto();
        // Récupération de l'email
        final Long idNatureEmail = squareMappingService.getIdNatureEmailPersonnel();
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(listeConnexions.get(0).getUidPersonne());
        if (coordonnees != null && coordonnees.getEmails() != null) {
            for (EmailDto email : coordonnees.getEmails()) {
                if (email.getNatureEmail() != null && idNatureEmail.equals(email.getNatureEmail().getIdentifiant())) {
                    infosConnexion.setEmail(email.getAdresse());
                    break;
                }
            }
        }
        infosConnexion.setMotDePasse(listeConnexions.get(0).getMotDePasse());
        return infosConnexion;
    }

    @Override
    public EspaceClientInternetDto getEspaceClientInternet(Long uidPersonne) {
        final Long idNatureConnexionEspaceClient = adherentMappingService.getIdNatureConnexionEspaceClient();
        return mapperDozerBean.map(espaceClientInternetDao.getEspaceClientInternetByPersonneAndNature(uidPersonne, idNatureConnexionEspaceClient),
            EspaceClientInternetDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EspaceClientInternetDto creerEspaceClient(EspaceClientInternetDto espaceClientDto) {
        // Vérification des paramètres
        if (espaceClientDto == null || espaceClientDto.getUidPersonne() == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CREER_ESPACE_CLIENT_PARAM_ID_PERSONNE_REQUIS));
        }
        // On vérifie que cette personne ne possède pas déjà d'espace client
        final EspaceClientInternet connexionExistante = espaceClientInternetDao.getEspaceClientInternetClient(espaceClientDto.getUidPersonne());
        if (connexionExistante != null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CREER_ESPACE_CLIENT_ESPACE_CLIENT_DEJA_EXISTANT,
                new String[] {espaceClientDto.getUidPersonne().toString()}));
        }
        // Si la personne est 'bénéficiaire prospect', on ne lui crée pas d'espace client.
        final PersonneDto personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(espaceClientDto.getUidPersonne());
        if (personneDto != null && personneDto.getNaturePersonne() != null
            && personneDto.getNaturePersonne().getIdentifiant().equals(squareMappingService.getIdNaturePersonneBeneficiaireProspect())) {
            return null;
        }
        if (personneDto != null && personneDto.getNaturePersonne() != null
            && personneDto.getNaturePersonne().getIdentifiant().equals(squareMappingService.getIdNaturePersonneVivier())) {
            return null;
        }

        // On recherche la personne dans Square / on vérifie que cette personne existe bien dans Square
        final PersonneSimpleDto personne = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(espaceClientDto.getUidPersonne());

        final EspaceClientInternet nouvelEspaceClient = new EspaceClientInternet();
        // On mappe manuellement l'espace client à partir du DTO, sinon on utilise une valeur par défaut
        nouvelEspaceClient.setUidPersonne(espaceClientDto.getUidPersonne());
        if (StringUtils.isNotBlank(espaceClientDto.getEid())) {
            nouvelEspaceClient.setIdentifiantExterieur(espaceClientDto.getEid());
        }
        if (StringUtils.isNotBlank(espaceClientDto.getLogin())) {
            nouvelEspaceClient.setLogin(espaceClientDto.getLogin());
        } else {
            // Par défaut le login de la personne correspond à son numéro client
            nouvelEspaceClient.setLogin(personne.getNumeroClient());
        }
        if (StringUtils.isNotBlank(espaceClientDto.getMotDePasse())) {
            nouvelEspaceClient.setMotDePasse(espaceClientDto.getMotDePasse());
        } else {
            // On génère le mot de passe
            nouvelEspaceClient.setMotDePasse(genererMotDePasse());
        }
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_INFO_CLIENT_CONNECTER,
        		new String[] {String.valueOf(nouvelEspaceClient.getUidPersonne()),
        					  nouvelEspaceClient.getLogin(),
        					  nouvelEspaceClient.getMotDePasse()}));
        if (espaceClientDto.getDateCreation() != null) {
            nouvelEspaceClient.setDateCreation(espaceClientDto.getDateCreation());
        } else {
            final Calendar now = Calendar.getInstance();
            nouvelEspaceClient.setDateCreation(now);
        }
        if (espaceClientDto.getDateModification() != null) {
            nouvelEspaceClient.setDateModification(espaceClientDto.getDateModification());
        }
        if (espaceClientDto.getDateDesactivation() != null) {
            nouvelEspaceClient.setDateDesactivation(espaceClientDto.getDateDesactivation());
        }
        if (espaceClientDto.getDateReactivation() != null) {
            nouvelEspaceClient.setDateReactivation(espaceClientDto.getDateReactivation());
        }
        if (espaceClientDto.getDateDerniereDematerialisation() != null) {
            nouvelEspaceClient.setDateDerniereDematerialisation(espaceClientDto.getDateDerniereDematerialisation());
        }
        if (espaceClientDto.getDatePremiereVisite() != null) {
            nouvelEspaceClient.setDatePremiereVisite(espaceClientDto.getDatePremiereVisite());
        }
        if (espaceClientDto.getDateDerniereVisite() != null) {
            nouvelEspaceClient.setDateDerniereVisite(espaceClientDto.getDateDerniereVisite());
        }
        if (espaceClientDto.getActive() != null) {
            nouvelEspaceClient.setActive(espaceClientDto.getActive());
        } else {
            nouvelEspaceClient.setActive(true);
        }
        if (espaceClientDto.getNbVisites() != null) {
            nouvelEspaceClient.setNbVisites(espaceClientDto.getNbVisites());
        } else {
            // On initialise le nombre de visites à 0
            nouvelEspaceClient.setNbVisites(0);
        }
        if (espaceClientDto.getNature() != null && espaceClientDto.getNature().getIdentifiant() != null) {
            nouvelEspaceClient.setNature(espaceClientInternetNatureDao.getConnexioNatureById(espaceClientDto.getNature().getIdentifiant()));
        } else {
            nouvelEspaceClient.setNature(espaceClientInternetNatureDao.getConnexioNatureById(adherentMappingService.getIdNatureConnexionEspaceClient()));
        }
        if (espaceClientDto.getPremiereVisite() != null) {
            nouvelEspaceClient.setPremiereVisite(espaceClientDto.getPremiereVisite());
        } else {
            // On initialise le flag de première visite
            nouvelEspaceClient.setPremiereVisite(true);
        }

        espaceClientInternetDao.saveEspaceClientInternet(nouvelEspaceClient);

        // On envoie un email à la personne pour indiquer qu'elle a accès à son espace client
        // On récupère la première adresse email personnelle pour la personne
        EmailDto emailPersonnel = null;
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(personne.getId());
        for (EmailDto email : coordonnees.getEmails()) {
            if (squareMappingService.getIdNatureEmailPersonnel().equals(email.getNatureEmail().getIdentifiant())) {
                emailPersonnel = email;
                break;
            }
        }
        // 0008526 & 8331 - La création de l'espace adhérent doit être systématique, donc l'email sera envoyé seulement si un email existe..
        if (emailPersonnel != null && !StringUtils.isEmpty(emailPersonnel.getAdresse())) {
            final EmailAvecModeleDto emailAvecModeleDto = new EmailAvecModeleDto();
            final InfosModeleEmailDto infosModele = new InfosModeleEmailDto();
            infosModele.setIdModeleEmail(envoiEmailMappingService.getIdModeleConfirmationCreationEspaceClient());
            infosModele.setEmailDestinataire(emailPersonnel.getAdresse());
            infosModele.setCiviliteDestinataire(personne.getCivilite().getLibelle());
            infosModele.setNomDestinataire(StringUtils.capitalize(personne.getNom()));
            final Map<String, Serializable> mapInfos = new HashMap<String, Serializable>();
            mapInfos.put("login", nouvelEspaceClient.getLogin());
            mapInfos.put("encryptedPassword", passwordEncryptor.encrypt(nouvelEspaceClient.getMotDePasse()));
            infosModele.setMapInfos(mapInfos);
            emailAvecModeleDto.setInfosModele(infosModele);
            mailService.envoyerMailDepuisModele(emailAvecModeleDto);
        }

        return mapperDozerBean.map(nouvelEspaceClient, EspaceClientInternetDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EspaceClientInternetDto majEspaceClient(EspaceClientInternetDto infosConnexionMaj) {
        final Long idNatureEspaceAdherent = adherentMappingService.getIdNatureConnexionEspaceClient();
        final EspaceClientInternet espaceClientInternet =
            espaceClientInternetDao.getEspaceClientInternetByPersonneAndNature(infosConnexionMaj.getUidPersonne(), idNatureEspaceAdherent);
        if (espaceClientInternet == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_MAJ_ESPACE_CLIENT_ESPACE_CLIENT_INTROUVABLE,
                new String[] {infosConnexionMaj.getUidPersonne().toString()}));
        }
        if (StringUtils.isNotBlank(infosConnexionMaj.getLogin())) {
            // Si le login n'est pas disponible
            if (!verifierLoginDisponible(infosConnexionMaj.getLogin(), infosConnexionMaj.getUidPersonne())) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MAJ_ESPACE_CLIENT_LOGIN_DEJA_UTILISE, new String[] {infosConnexionMaj
                        .getLogin()}));
            }
            // On met à jour l'identifiant de connexion
            espaceClientInternet.setLogin(infosConnexionMaj.getLogin());
        }
        if (StringUtils.isNotBlank(infosConnexionMaj.getMotDePasse())) {
            // On met à jour le mot de passe de connexion
            espaceClientInternet.setMotDePasse(infosConnexionMaj.getMotDePasse());
        }
        // on met à jour les flags
        if (infosConnexionMaj.getPremiereVisite() != null) {
            espaceClientInternet.setPremiereVisite(infosConnexionMaj.getPremiereVisite());
        }
        if (infosConnexionMaj.getAfficheNouveauService() != null) {
            espaceClientInternet.setAfficheNouveauService(infosConnexionMaj.getAfficheNouveauService());
        }
        // On actualise la date de modification
        espaceClientInternet.setDateModification(Calendar.getInstance());
        return mapperDozerBean.map(espaceClientInternet, EspaceClientInternetDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verifierLoginDisponible(String login, Long idPersonne) {
        // On recherche une connexion existante avec ce login pour déterminer si le login est disponible
        final IdentifiantsConnexionDto identifiants = new IdentifiantsConnexionDto(login, null);
        // bug 8259 : on verifie si le login est un nombre de 7 chiffres différent de l'id client
        final PersonneDto personne = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne);
        if (login != null && login.matches("[0-9]{7}") && !login.equals(personne.getNumClient())) {
            return false;
        } else if (login != null && login.matches("[0-9]{7}") && login.equals(personne.getNumClient())) {
            return true;
        }
        final List<EspaceClientInternet> espaceClientInternets =
            espaceClientInternetDao.getListeEspaceClientInternetsByIdentifiantsAndNature(identifiants, adherentMappingService
                    .getIdNatureConnexionEspaceClient(), null);
        return espaceClientInternets != null && espaceClientInternets.size() == 0;
    }

    @Override
    public void fusionnerEspaceClient(Long idPersonneSource, Long idPersonneCible) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_FUSION_ESPACE_CLIENT_PERSONNE_SOURCE,
        			new String[] {String.valueOf(idPersonneSource), String.valueOf(idPersonneCible)}));
        // on recupere les deux personnes
        final PersonneSimpleDto personneSource = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(idPersonneSource);
        final PersonneSimpleDto personneCible = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(idPersonneCible);

        if (personneSource == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_FUSION_PERSONNE_SOURCE_INEXISTANTE));
        }
        if (personneCible == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_FUSION_PERSONNE_CIBLE_INEXISTANTE));
        }

        final EspaceClientInternet espaceClientInternetSource = espaceClientInternetDao.getEspaceClientInternetClient(idPersonneSource);
        final EspaceClientInternet espaceClientInternetCible = espaceClientInternetDao.getEspaceClientInternetClient(idPersonneCible);

        final Long idNaturePersonneProspect = squareMappingService.getIdNaturePersonneProspect();
        final Long idNaturePersonneAdherent = squareMappingService.getIdNaturePersonneAdherent();

        final boolean personneSourceProspect = personneSource.getNature().getIdentifiant().equals(idNaturePersonneProspect);
        final boolean personneCibleProspect = personneCible.getNature().getIdentifiant().equals(idNaturePersonneProspect);
        final boolean personneSourceAdherent = personneSource.getNature().getIdentifiant().equals(idNaturePersonneAdherent);
        final boolean personneCibleAdherent = personneCible.getNature().getIdentifiant().equals(idNaturePersonneAdherent);

        if (espaceClientInternetSource != null && espaceClientInternetCible == null) {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_ABSCENCE_CONNEXION_CIBLE));
            espaceClientInternetSource.setUidPersonne(idPersonneCible);
        } else if (espaceClientInternetSource == null && espaceClientInternetCible != null) {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_ABSCENCE_CONNEXION_SOURCE));
            espaceClientInternetCible.setUidPersonne(idPersonneCible);
        } else if (espaceClientInternetSource != null && espaceClientInternetCible != null) {
            // entre deux prospects
            if (personneSourceProspect && personneCibleProspect) {
                // on garde la connexion source que si elle est active alors que la connexion cible ne l'est pas
                if (espaceClientInternetSource.isActive() && !espaceClientInternetCible.isActive()) {
                    logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_DEUX_PROSPECT_ESPACE_CLIENT_CONSERVE_SOURCE));
                    fusionnerEspaceClientInternet(espaceClientInternetSource, idPersonneCible, espaceClientInternetCible);
                } else {
                    logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_DEUX_PROSPECT_ESPACE_CLIENT_CONSERVE_CIBLE));
                    fusionnerEspaceClientInternet(espaceClientInternetCible, idPersonneCible, espaceClientInternetSource);
                }
            }
            // entre un prospect et un adherent
            else if (personneSourceProspect && personneCibleAdherent) {
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CONNECTION_SOURCE_PROSPECT_CIBLE_ADHERENT_CONSERVE_CIBLE));
                fusionnerEspaceClientInternet(espaceClientInternetCible, idPersonneCible, espaceClientInternetSource);
            } else if (personneSourceAdherent && personneCibleProspect) {
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CONNECTION_SOURCE_ADHERENT_CIBLE_PROSPECT_CONSERVE_SOURCE));
                fusionnerEspaceClientInternet(espaceClientInternetSource, idPersonneCible, espaceClientInternetCible);
            }
        }
    }

    private void fusionnerEspaceClientInternet(EspaceClientInternet espaceClientInternetAAffecter, Long uidPersonne,
        EspaceClientInternet espaceClientInternetASupprimer) {
        // on supprime l'autre connexion
        espaceClientInternetDao.deleteEspaceClientInternet(espaceClientInternetASupprimer);
        // on affecte les connexions à la personne cible
        espaceClientInternetAAffecter.setUidPersonne(uidPersonne);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void envoyerMotDePassePerdu(String login, boolean envoyerParMail, boolean envoyerParSms) {
        // On vérifie les paramètres
        if (StringUtils.isBlank(login)) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_UID_PERSONNE_NULL));
        }
        if (!envoyerParMail && !envoyerParSms) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_AUCUN_MODE_ENVOI_SPECIFIE));
        }
        // On recherche la connexion correspondant au login spécifié
        final IdentifiantsConnexionDto identifiants = new IdentifiantsConnexionDto(login, null);
        List<EspaceClientInternet> espaceClientInternets =
            espaceClientInternetDao.getListeEspaceClientInternetsByIdentifiantsAndNature(identifiants, adherentMappingService
                    .getIdNatureConnexionEspaceClient(), null);
        // Si on ne trouve pas d'espace client et le numéro de client commence par 0, on réessaye sans tenir compte du 0.
        if (espaceClientInternets.isEmpty() && identifiants.getLogin().trim().startsWith("0")) {
            final String numeroClientSansZero = identifiants.getLogin().trim().substring(1);
            identifiants.setLogin(numeroClientSansZero);
            espaceClientInternets =
                espaceClientInternetDao.getListeEspaceClientInternetsByIdentifiantsAndNature(identifiants, adherentMappingService
                        .getIdNatureConnexionEspaceClient(), null);
        }
        if (espaceClientInternets.isEmpty()) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_ESPACE_CLIENT_INTERNET_NULL, new String[] {login}));
        }
        final EspaceClientInternet espaceClientInternet = espaceClientInternets.get(0);
        // Si la connexion est désactivée
        if (!espaceClientInternet.isActive()) {
            // On renvoie une erreur au lieu d'envoyer le mot de passe
            throw new BusinessException(messageSourceUtil
                    .get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_ESPACE_CLIENT_INTERNET_DESACTIVEE, new String[] {login}));
        }
        // On récupère les informations de la personne à partir de Square
        final PersonneSimpleDto personne = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(espaceClientInternet.getUidPersonne());
        // Si aucune personne n'est trouvée
        if (personne == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_PERSONNE_NULL, new String[] {espaceClientInternet
                    .getUidPersonne().toString()}));
        }
        // On récupère les coordonnées de la personne à partir de Square.
        final CoordonneesDto coordonneesPersonne = personneService.rechercherCoordonneesParIdPersonne(espaceClientInternet.getUidPersonne());
        if (coordonneesPersonne == null
            || (coordonneesPersonne != null && coordonneesPersonne.getEmails().isEmpty() && coordonneesPersonne.getTelephones().isEmpty())) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_COORDONNEES_PERSONNE_NULL,
                new String[] {espaceClientInternet.getUidPersonne().toString()}));
        }

        if (envoyerParMail) {
            // On vérifie que la personne possède des coordonnées et un email.
            if (coordonneesPersonne.getEmails().isEmpty()) {
                throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_AUCUN_EMAIL, new String[] {espaceClientInternet
                        .getUidPersonne().toString()}));
            }
            final EmailDto emailPersonne = coordonneesPersonne.getEmails().get(0);
            final Map<String, Serializable> mapInfos = new HashMap<String, Serializable>();
            mapInfos.put("numAdherent", espaceClientInternet.getLogin());
            mapInfos.put("motDePasse", espaceClientInternet.getMotDePasse().trim());
            final InfosModeleEmailDto infosModele = new InfosModeleEmailDto();
            infosModele.setIdModeleEmail(envoiEmailMappingService.getIdModeleMotDePasseOublie());
            infosModele.setEmailDestinataire(emailPersonne.getAdresse());
            infosModele.setCiviliteDestinataire(personne.getCivilite().getLibelle());
            infosModele.setNomDestinataire(personne.getNom().toUpperCase());
            infosModele.setMapInfos(mapInfos);
            final EmailAvecModeleDto emailAvecModeleDto = new EmailAvecModeleDto();
            emailAvecModeleDto.setInfosModele(infosModele);

            // Envoi d'un email à l'adhérent pour lui rappeller ses identifiants.
            try {
                mailService.envoyerMailDepuisModele(emailAvecModeleDto);
                logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_IDENTIFIANT_CONNECTION_ESPACE_CLIENT_PERSONNE_ENVOYE_MAIL,
            			new String[] {String.valueOf(personne.getId()), personne.getNumeroClient(), emailPersonne.getAdresse() }));
            } catch (Exception e) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_ECHEC_ENVOIE_MAIL_MDP_ADHERENT,
                		new String[] {espaceClientInternet.getLogin() }));
                throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_ECHEC_ENVOI_EMAIL));
            }
        }

        if (envoyerParSms) {
            if (coordonneesPersonne.getTelephones().isEmpty()) {
                throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_AUCUN_TELEPHONE,
                    new String[] {espaceClientInternet.getUidPersonne().toString()}));
            }
            // On recherche un numéro de téléphone mobile personnel
            List<TelephoneDto> telephones =
                personneUtil.getTelephonesByNature(coordonneesPersonne.getTelephones(), squareMappingService.getIdNatureMobilePrive());
            // Si aucun numéro de téléphone mobile personnel n'est trouvé
            if (telephones.isEmpty()) {
                // On recherche un numéro de téléphone mobile professionnel
                telephones = personneUtil.getTelephonesByNature(coordonneesPersonne.getTelephones(), squareMappingService.getIdNatureMobileTravail());
            }
            // Si aucun numéro de téléphone mobile professionnel n'est trouvé
            if (telephones.isEmpty()) {
                // On recherche un numéro de téléphone fixe personnel
                telephones = personneUtil.getTelephonesByNature(coordonneesPersonne.getTelephones(), squareMappingService.getIdNatureTelephoneFixe());
            }
            // Si aucun numéro de téléphone mobile professionnel n'est trouvé
            if (telephones.isEmpty()) {
                // On recherche un numéro de téléphone fixe personnel
                telephones = personneUtil.getTelephonesByNature(coordonneesPersonne.getTelephones(), squareMappingService.getIdNatureTelephoneFixeTravail());
            }
            if (telephones.isEmpty()) {
                throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_AUCUN_TELEPHONE,
                    new String[] {espaceClientInternet.getUidPersonne().toString()}));
            }
            final TelephoneDto telephone = telephones.get(0);
            // création du SMS
            final StringBuffer message = new StringBuffer(messageSourceUtil.get(MessageKeyUtil.MESSAGE_SMS_ADHERENT,
            		new String[] {espaceClientInternet.getLogin(), espaceClientInternet.getMotDePasse()}));
            final CreationSmsDto creationSmsDto = new CreationSmsDto();
            creationSmsDto.setNumeroTelephone(telephone.getNumero());
            creationSmsDto.setCodePays(telephone.getPays().getCodeISO());
            creationSmsDto.setMessage(message.toString());
            final SmsDto smsBaseDto = smsPlugin.creerSms(creationSmsDto);

            // Envoi du SMS
            smsPlugin.envoyerSms(smsBaseDto.getIdentifiant());
            logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_IDENTIFIANT_CONNECTION_ESPACE_CLIENT_PERSONNE_ENVOYE_SMS,
            		new String[] {String.valueOf(personne.getId()), String.valueOf(personne.getNumeroClient()), telephone.getNumero()}));
        }
    }

    /**
     * Génère un mot de passe de manière aléatoire.
     * @return le mot de passe généré.
     */
    private String genererMotDePasse() {
        final String caracteres = "abcdefghijklmnopqrstuvwxyz0123456789";
        String mdp = "";
        final Random random = new Random();
        for (int i = 0; i < 6; i++) {
            final int position = random.nextInt(caracteres.length());
            mdp = mdp + caracteres.charAt(position);
        }
        return mdp;
    }

    /**
     * {@inheritDoc}
     */
    public void desactiverEspaceClient(Long uidPersonne) {
        final EspaceClientInternet espaceClient = espaceClientInternetDao.getEspaceClientInternetClient(uidPersonne);
        if (espaceClient != null) {
            espaceClient.setActive(false);
            final Calendar now = Calendar.getInstance();
            espaceClient.setDateDesactivation(now);
            espaceClient.setDateModification(now);
        }
    }

    /**
     * Définit la valeur de messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définit la valeur de personneService.
     * @param personneService la nouvelle valeur de personneService
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Setter function.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Setter function.
     * @param espaceClientInternetNatureDao the espaceClientInternetNatureDao to set
     */
    public void setEspaceClientInternetNatureDao(EspaceClientInternetNatureDao espaceClientInternetNatureDao) {
        this.espaceClientInternetNatureDao = espaceClientInternetNatureDao;
    }

    /**
     * Setter function.
     * @param mailService the mailService to set
     */
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * Setter function.
     * @param envoiEmailMappingService the envoiEmailMappingService to set
     */
    public void setEnvoiEmailMappingService(EnvoiEmailMappingService envoiEmailMappingService) {
        this.envoiEmailMappingService = envoiEmailMappingService;
    }

    /**
     * Setter function.
     * @param smsPlugin the smsPlugin to set
     */
    public void setSmsPlugin(SmsPlugin smsPlugin) {
        this.smsPlugin = smsPlugin;
    }

    /**
     * Setter function.
     * @param personneUtil the personneUtil to set
     */
    public void setPersonneUtil(PersonneUtil personneUtil) {
        this.personneUtil = personneUtil;
    }

    /**
     * Setter function.
     * @param passwordEncryptor the passwordEncryptor to set
     */
    public void setPasswordEncryptor(StandardPBEStringEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    /**
     * Setter function.
     * @param espaceClientInternetDao the espaceClientInternetDao to set
     */
    public void setEspaceClientInternetDao(EspaceClientInternetDao espaceClientInternetDao) {
        this.espaceClientInternetDao = espaceClientInternetDao;
    }

    /**
     * Setter function.
     * @param contratService the contratService to set
     */
    public void setContratService(ContratService contratService) {
        this.contratService = contratService;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

}
