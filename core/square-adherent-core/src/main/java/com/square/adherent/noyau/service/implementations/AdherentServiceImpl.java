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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.adherent.noyau.dao.interfaces.EspaceClientInternetDao;
import com.square.adherent.noyau.dao.interfaces.MagazineDao;
import com.square.adherent.noyau.dao.interfaces.OptionDao;
import com.square.adherent.noyau.dao.interfaces.OptionTypeDao;
import com.square.adherent.noyau.dao.interfaces.contrat.GarantieBaremeDao;
import com.square.adherent.noyau.dao.interfaces.contrat.GarantieDao;
import com.square.adherent.noyau.dao.interfaces.personne.PersonneDao;
import com.square.adherent.noyau.dao.interfaces.prestation.ReserveBancoDao;
import com.square.adherent.noyau.dto.adherent.BeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.InfosCompteAdherentDto;
import com.square.adherent.noyau.dto.adherent.InfosEspaceAdherentDto;
import com.square.adherent.noyau.dto.adherent.InfosIdentifiantPersonneDto;
import com.square.adherent.noyau.dto.adherent.InfosOptionsAdherentDto;
import com.square.adherent.noyau.dto.adherent.OptionAdherentDto;
import com.square.adherent.noyau.dto.adherent.contrat.CoupleBaremeDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBaremeCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBaremeDto;
import com.square.adherent.noyau.dto.adherent.prestations.DemandePriseEnChargeDto;
import com.square.adherent.noyau.dto.espace.client.EspaceClientInternetDto;
import com.square.adherent.noyau.dto.prestation.CriteresPersonnesNotificationSmsDto;
import com.square.adherent.noyau.dto.produit.ReserveProduitBancoDto;
import com.square.adherent.noyau.model.data.contrat.GarantieBareme;
import com.square.adherent.noyau.model.data.espace.client.EspaceClientInternet;
import com.square.adherent.noyau.model.data.espace.client.Option;
import com.square.adherent.noyau.model.dimension.OptionType;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.AdherentService;
import com.square.adherent.noyau.service.interfaces.EspaceClientInternetService;
import com.square.adherent.noyau.util.MessageKeyUtil;
import com.square.adherent.noyau.util.PersonneUtil;
import com.square.core.model.dto.ActionCreationDto;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.HistoriqueCommentaireDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonnePhysiqueCopieDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.mail.core.dto.emails.EmailAvecModeleDto;
import com.square.mail.core.dto.emails.InfosModeleEmailDto;
import com.square.mail.core.service.interfaces.email.EnvoiEmailMappingService;
import com.square.mail.core.service.interfaces.email.MailService;

/**
 * Implémentation des services spécifiques aux adhérents Smatis.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class AdherentServiceImpl implements AdherentService {

    private final Logger logger = Logger.getLogger(this.getClass());

    private MailService mailService;

    private EnvoiEmailMappingService envoiEmailMappingService;

    private PersonneService personneService;

    private PersonnePhysiqueService personnePhysiqueService;

    private ActionService actionService;

    private SquareMappingService squareMappingService;

    private EspaceClientInternetService espaceClientInternetService;

    private AdherentMappingService adherentMappingService;

    private MessageSourceUtil messageSourceUtil;

    private PersonneUtil personneUtil;

    private MapperDozerBean mapperDozerBean;

    private EspaceClientInternetDao espaceClientInternetDao;

    private OptionDao optionDao;

    private MagazineDao magazineDao;

    private OptionTypeDao optionTypeDao;

    private GarantieDao garantieDao;

    private PersonneDao personneDao;

    private ReserveBancoDao reserveBancoDao;

    private GarantieBaremeDao garantieBaremeDao;

    private SimpleDateFormat sdf;

    private SimpleDateFormat sdfMois;

    /**
     * {@inheritDoc}
     */
    @Override
    public InfosEspaceAdherentDto getInfosEspaceAdherent(Long uidPersonne) {
        if (uidPersonne == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_INFOS_ESPACE_ADHERENT_UID_PERSONNE_NULL));
        }
        // On récupère les infos de connexion de l'adhérent
        final EspaceClientInternet connexionAdherent = espaceClientInternetDao.getEspaceClientInternetClient(uidPersonne);
        if (connexionAdherent == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_INFOS_ESPACE_ADHERENT_UID_PERSONNE_NULL, new String[] {uidPersonne
                    .toString()}));
        }
        final List<Option> optionsAdherent = optionDao.getOptionsAdherent(uidPersonne);
        // On récupère les coordonnées de l'adhérent à partir de Square.
        final CoordonneesDto coordonneesAdherent = personneService.rechercherCoordonneesParIdPersonne(uidPersonne);
        // On vérifie que l'adhérent possède des coordonnées et un email.
        if (coordonneesAdherent == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_COORDONNEES_PERSONNE_NULL, new String[] {uidPersonne
                    .toString()}));
        }

        // Mapping automatique des infos
        final InfosEspaceAdherentDto infosEspaceAdherentDto = mapperDozerBean.map(connexionAdherent, InfosEspaceAdherentDto.class);
        if (!coordonneesAdherent.getEmails().isEmpty()) {
            final EmailDto emailAdherent = coordonneesAdherent.getEmails().get(0);
            // Mapping manuel de l'adresse email de l'adhérent.
            infosEspaceAdherentDto.setEmail(emailAdherent.getAdresse());
        }
        // On détermine la date de dernière modification des options
        Calendar dateDerniereModificationOptions = null;
        for (Option option : optionsAdherent) {
            final Calendar dateModificationOption = option.getDateModification();
            if (dateDerniereModificationOptions == null || dateModificationOption.after(dateDerniereModificationOptions)) {
                dateDerniereModificationOptions = dateModificationOption;
            }
        }
        infosEspaceAdherentDto.setDateModificationOptions(dateDerniereModificationOptions);
        final List<OptionAdherentDto> listeOptions = mapperDozerBean.mapList(optionsAdherent, OptionAdherentDto.class);
        infosEspaceAdherentDto.setListeOptions(listeOptions);
        return infosEspaceAdherentDto;
    }

    @Override
    public InfosOptionsAdherentDto getInfosOptionsAdherent(Long uidPersonne) {
        if (uidPersonne == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_INFOS_ESPACE_ADHERENT_UID_PERSONNE_NULL));
        }
        final List<Option> optionsAdherent = optionDao.getOptionsAdherent(uidPersonne);
        // On récupère les coordonnées de l'adhérent à partir de Square.
        final CoordonneesDto coordonneesAdherent = personneService.rechercherCoordonneesParIdPersonne(uidPersonne);
        // On vérifie que l'adhérent possède des coordonnées et un email.
        if (coordonneesAdherent == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_ENVOYER_MOT_DE_PASSE_COORDONNEES_PERSONNE_NULL, new String[] {uidPersonne
                    .toString()}));
        }

        // Mapping automatique des infos
        final InfosOptionsAdherentDto infosOptionsAdherentDto = new InfosOptionsAdherentDto();
        // On détermine la date de dernière modification des options
        Calendar dateDerniereModificationOptions = null;
        for (Option option : optionsAdherent) {
            final Calendar dateModificationOption = option.getDateModification();
            if (dateDerniereModificationOptions == null || dateModificationOption.after(dateDerniereModificationOptions)) {
                dateDerniereModificationOptions = dateModificationOption;
            }
        }
        infosOptionsAdherentDto.setDateModificationOptions(dateDerniereModificationOptions);
        final List<OptionAdherentDto> listeOptions = mapperDozerBean.mapList(optionsAdherent, OptionAdherentDto.class);
        infosOptionsAdherentDto.setListeOptions(listeOptions);
        return infosOptionsAdherentDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OptionAdherentDto> mettreAJourListeOptionsAdherent(Long uidPersonne, List<OptionAdherentDto> listeOptions) {
        if (uidPersonne == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_MAJ_OPTIONS_ADHERENT_UID_PERSONNE_NULL));
        }
        final CoordonneesDto coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(uidPersonne);
        // On parcours les options pour les ajouter / mettre à jour.
        for (OptionAdherentDto option : listeOptions) {
            creerOuMajOptionAdherent(uidPersonne, option, coordonneesDto);
        }
        // On retourne les options de l'adhérent mises à jour
        final List<Option> optionsAdherent = optionDao.getOptionsAdherent(uidPersonne);
        return mapperDozerBean.mapList(optionsAdherent, OptionAdherentDto.class);
    }

    /**
     * Créé / met à jour une option d'adhérent.
     * @param uidPersonne l'identifiant de l'adhérent.
     * @param optionDto l'option à créer / mettre à jour.
     */
    private void creerOuMajOptionAdherent(Long uidPersonne, OptionAdherentDto optionDto, final CoordonneesDto coordonnees) {
        final Long idOptionEnvoiMutuellement = adherentMappingService.getIdTypeOptionEnvoiMutuellementEmail();
        final Long idOptionEnvoiRelevesPresta = adherentMappingService.getIdTypeOptionEnvoiRelevesPrestationEmail();
        final Long idOptionEnvoiSms = adherentMappingService.getIdTypeOptionEnvoiSms();
        final Long idNatureMobilePrive = squareMappingService.getIdNatureMobilePrive();
        final Long idNatureMobileTravail = squareMappingService.getIdNatureMobileTravail();

        Option option = null;
        if (optionDto.getId() != null) {
            option = optionDao.getOptionById(optionDto.getId());
        } else if (optionDto.getType() != null && optionDto.getType().getIdentifiant() != null) {
            option = optionDao.getOptionByPersonneAndType(uidPersonne, optionDto.getType().getIdentifiant());
        }
        final Calendar maintenant = Calendar.getInstance();
        if (option == null) {
            // Si l'option est activée
            if (optionDto.isActive()) {
                // On la sauvegarde dans la base de données.
                option = new Option();
                final OptionType typeOption = optionTypeDao.getOptionTypeById(optionDto.getType().getIdentifiant());
                if (typeOption == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_TYPE_OPTION_INVALIDE,
                    		new String[] {String.valueOf(optionDto.getType().getIdentifiant())}));
                    throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_MAJ_OPTIONS_ADHERENT_UID_PERSONNE_NULL));
                }
                // si l'option a activer est de de type 'envoie par email'
                if (optionDto.getType() != null
                    && (idOptionEnvoiRelevesPresta.equals(optionDto.getType().getIdentifiant()) || idOptionEnvoiMutuellement.equals(optionDto.getType()
                            .getIdentifiant()))) {
                    // si l'adhérent n'a pas d'email
                    if (coordonnees.getEmails() == null || coordonnees.getEmails().isEmpty()) {
                        throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_MAJ_OPTIONS_ADHERENT_UID_PERSONNE_EMAIL_REQUIS));
                    }
                }
                // si l'option a activer est de de type 'envoie par sms'
                if (optionDto.getType() != null && (idOptionEnvoiSms.equals(optionDto.getType().getIdentifiant()))) {
                    boolean existeTelephoneMobile = false;
                    if (coordonnees.getTelephones() != null) {
                        for (TelephoneDto telephone : coordonnees.getTelephones()) {
                            if (idNatureMobilePrive.equals(telephone.getNature().getIdentifiant())
                                || idNatureMobileTravail.equals(telephone.getNature().getIdentifiant())) {
                                existeTelephoneMobile = true;
                                break;
                            }
                        }
                    }
                    // si l'adhérent n'a pas de telephone
                    if (!existeTelephoneMobile) {
                        throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_MAJ_OPTIONS_ADHERENT_UID_PERSONNE_TELEPHONE_MOBILE_REQUIS));
                    }
                }
                option.setType(typeOption);
                option.setUidPersonne(uidPersonne);
                option.setDateCreation(maintenant);
                option.setDateDebut(maintenant);
                option.setDateModification(maintenant);
                option.setActive(true);
                // On créer l'option
                optionDao.saveOption(option);
            }
        } else {
            // On met à jour l'option
            // Activation de l'option
            if (optionDto.isActive() && !option.isActive()) {
                // si l'option a activer est de de type 'envoie par email'
                if (optionDto.getType() != null
                    && (idOptionEnvoiRelevesPresta.equals(optionDto.getType().getIdentifiant()) || idOptionEnvoiMutuellement.equals(optionDto.getType()
                            .getIdentifiant()))) {
                    // si l'adhérent n'a pas d'email
                    if (coordonnees.getEmails() == null || coordonnees.getEmails().isEmpty()) {
                        throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_MAJ_OPTIONS_ADHERENT_UID_PERSONNE_EMAIL_REQUIS));
                    }
                }
                // si l'option a activer est de de type 'envoie par sms'
                if (optionDto.getType() != null && (idOptionEnvoiSms.equals(optionDto.getType().getIdentifiant()))) {
                    boolean existeTelephoneMobile = false;
                    if (coordonnees.getTelephones() != null) {
                        for (TelephoneDto telephone : coordonnees.getTelephones()) {
                            if (idNatureMobilePrive.equals(telephone.getNature().getIdentifiant())
                                || idNatureMobileTravail.equals(telephone.getNature().getIdentifiant())) {
                                existeTelephoneMobile = true;
                                break;
                            }
                        }
                    }
                    // si l'adhérent n'a pas de telephone
                    if (!existeTelephoneMobile) {
                        throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_MAJ_OPTIONS_ADHERENT_UID_PERSONNE_TELEPHONE_MOBILE_REQUIS));
                    }
                }
                option.setActive(true);
                option.setDateDebut(maintenant);
                option.setDateModification(maintenant);
                option.setDateFin(null);
            }
            // Désactivation de l'option
            else if (!optionDto.isActive() && option.isActive()) {
                option.setActive(false);
                option.setDateModification(maintenant);
                option.setDateFin(maintenant);
            }
        }

        // Mise à jour de la date de dématérialisation des documents seulement pour les relevés de prestation
        if (optionDto.getType() != null && idOptionEnvoiRelevesPresta.equals(optionDto.getType().getIdentifiant())) {
            // Récupération des infos de connexion de la personne
            final Long idNatureEspaceAdherent = adherentMappingService.getIdNatureConnexionEspaceClient();
            final EspaceClientInternet espaceClientInternet =
                espaceClientInternetDao.getEspaceClientInternetByPersonneAndNature(uidPersonne, idNatureEspaceAdherent);
            espaceClientInternet.setDateDerniereDematerialisation(maintenant);
            espaceClientInternet.setDateModification(maintenant);
        }
    }

    @Override
    public List<Long> getListeBeneficiairesPersonne(Long idPersonne) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_BENEFICIAIRE_PERSONNE) + idPersonne);
        return personneDao.getListeBeneficiairesPersonne(idPersonne);
    }

    @Override
    public Long getIdAdherentPrincipal(Long idPersonne) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_ADHERENT_PRINCIPAL_PERSONNE) + idPersonne);
        return personneDao.getAdherentPrincipalPersonne(idPersonne);
    }

    @Override
    public List<ReserveProduitBancoDto> getReserveProduitBancoByUser(Long idAdherent) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_INFO_RESERVE_BANCO_ADHERENT) + idAdherent);
        return reserveBancoDao.getListeReservesBancoByAdherent(idAdherent);
    }

    @Override
    public void mettreAJourMotDePasse(Long idPersonne, String motDePasse) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MAJ_MDP_PERSONNE) + idPersonne);

        // Récupération de la connexion de la personne correspondante.
        final Long idNature = adherentMappingService.getIdNatureConnexionEspaceClient();
        final EspaceClientInternet espaceClientInternet = espaceClientInternetDao.getEspaceClientInternetByPersonneAndNature(idPersonne, idNature);

        if (espaceClientInternet == null) {
            throw new BusinessException(MessageKeyUtil.ERROR_COMPTE_INEXITANT);
        }
        // Modification du mot de passe
        espaceClientInternet.setMotDePasse(motDePasse);
        espaceClientInternet.setDateModification(Calendar.getInstance());
    }

    @Override
    public boolean hasPersonneEspaceAdherent(Long uidPersonne) {
        return espaceClientInternetDao.getEspaceClientInternetClient(uidPersonne) != null;
    }

    @Override
    public boolean isPersonneAssocieeAContrat(Long uidPersonne) {
        // Une personne est associée à un contrat si elle est assurée ou bénéficiaire d'une garantie
        return garantieDao.isPersonneAssocieeAContrat(uidPersonne);
    }

    @Override
    public boolean isPersonnePossedeOption(Long idPersonne, Long idTypeOption) {
        return optionDao.isPersonnePossedeOption(idPersonne, idTypeOption);
    }

    @Override
    public List<Long> getListeIdsTypesOptionsSouscrites(Long uidPersonne) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_OPTIONS_SOUSCRITES_PERSONNE) + uidPersonne);
        final List<Option> listeOptions = optionDao.getListeOptionsSouscritesByPersonne(uidPersonne);
        // Récupération des identifiant des options souscrites
        final List<Long> listeIdTypesOptionsSouscrites = new ArrayList<Long>();
        for (Option option : listeOptions) {
            if (option.getType() != null && option.getType().getId() != null) {
                listeIdTypesOptionsSouscrites.add(option.getType().getId());
            }
        }
        return listeIdTypesOptionsSouscrites;
    }

    @Override
    public void mettreAJourInfosCompteAdherent(InfosCompteAdherentDto infosCompteAdherent) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MODIF_INFO_COMPTE_PERSONNE) + infosCompteAdherent.getIdAdherent());
        // On met à jour l'espace client de l'adhérent
        espaceClientInternetService.majEspaceClient((EspaceClientInternetDto) mapperDozerBean.map(infosCompteAdherent, EspaceClientInternetDto.class));
        final CoordonneesDto coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(infosCompteAdherent.getIdAdherent());
        // On met à jour les options de l'adhérent
        if (infosCompteAdherent.getListeOptions() != null) {
            for (OptionAdherentDto option : infosCompteAdherent.getListeOptions()) {
                // Création ou mise à jour de l'option
                creerOuMajOptionAdherent(infosCompteAdherent.getIdAdherent(), option, coordonneesDto);
            }
        }
    }

    @Override
    public void desactiverOptionsEnvoiParEmail(Long uidPersonne) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_DESACTIVATION_OPTION_ENVOIE_EMAIL_PERSONNE) + uidPersonne);
        final CoordonneesDto coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(uidPersonne);
        final OptionAdherentDto optionEnvoiMutuellement = new OptionAdherentDto();
        optionEnvoiMutuellement.setType(new IdentifiantLibelleDto(adherentMappingService.getIdTypeOptionEnvoiMutuellementEmail()));
        optionEnvoiMutuellement.setActive(false);
        creerOuMajOptionAdherent(uidPersonne, optionEnvoiMutuellement, coordonneesDto);
        final OptionAdherentDto optionEnvoiRelevesPrestation = new OptionAdherentDto();
        optionEnvoiRelevesPrestation.setType(new IdentifiantLibelleDto(adherentMappingService.getIdTypeOptionEnvoiRelevesPrestationEmail()));
        optionEnvoiRelevesPrestation.setActive(false);
        creerOuMajOptionAdherent(uidPersonne, optionEnvoiRelevesPrestation, coordonneesDto);
    }

    @Override
    public void desactiverOptionsEnvoiParTelephone(Long uidPersonne) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_DESACTIVATION_OPTION_ENVOIE_TEL_PERSONNE) + uidPersonne);
        final CoordonneesDto coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(uidPersonne);
        final OptionAdherentDto optionEnvoiSms = new OptionAdherentDto();
        optionEnvoiSms.setType(new IdentifiantLibelleDto(adherentMappingService.getIdTypeOptionEnvoiSms()));
        optionEnvoiSms.setActive(false);
        creerOuMajOptionAdherent(uidPersonne, optionEnvoiSms, coordonneesDto);
    }

    @Override
    public List<Long> getListeAdherentsByIdOptionSouscrite(Long idOption) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_LISTE_ADHERENT_SOUSCRIT_OPTION) + idOption);
        return optionDao.getListeAdherentsByIdOptionSouscrite(idOption);
    }

    @Override
    public List<Long> getListeAdherentsByIdMagazineEnvoye(Long idMagazine) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_LISTE_ADHERENT_RECU_MAGAZINE) + idMagazine);
        return magazineDao.getListeAdherentsByIdMagazineEnvoye(idMagazine);
    }

    /**
     * {@inheritDoc}
     */
    public List<Long> getListePersonnesNotificationSmsByCriteres(CriteresPersonnesNotificationSmsDto criteres) {
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_PERSONNE_A_NOTIFIER_PAR_SMS_PAR_CRITERE));
        return personneDao.getListePersonnesNotificationSmsByCriteres(criteres);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void demanderPriseEnCharge(DemandePriseEnChargeDto demandePriseEnChargeDto) {
        // Vérification des paramètres...
        // L'identifiant de l'adhérent à l'origine de la demande est obligatoire
        if (demandePriseEnChargeDto.getIdAdherent() == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_ID_ADHERENT));
        }
        // On récupère l'adhérent à l'origine de la demande de prise en charge
        PersonneSimpleDto adherent = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(demandePriseEnChargeDto.getIdAdherent());
        if (adherent == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_ADHERENT_INTROUVABLE,
            		new String[] {String.valueOf(demandePriseEnChargeDto.getIdAdherent())}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_PRISE_EN_CHARGE_ADHERENT_INTROUVABLE));
        }
        // L'identifiant de la personne à prendre en charge est obligatoire
        if (demandePriseEnChargeDto.getIdPersonneAPrendreEnCharge() == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_ID_PERSONNE_A_PRENDRE_EN_CHARGE));
        }
        PersonneSimpleDto personneAPrendreEnCharge;
        // On récupère la personne à prendre en charge si l'identifiant de la personne à prendre en charge est différent de l'identifiant de l'adhérent
        if (!demandePriseEnChargeDto.getIdAdherent().equals(demandePriseEnChargeDto.getIdPersonneAPrendreEnCharge())) {
            personneAPrendreEnCharge = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(demandePriseEnChargeDto.getIdPersonneAPrendreEnCharge());
            if (personneAPrendreEnCharge == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_PRENDRE_CHARGE_INTROUVABLE,
                		new String[] {String.valueOf(demandePriseEnChargeDto.getIdPersonneAPrendreEnCharge())}));
                throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_PRISE_EN_CHARGE_PERSONNE_A_PRENDRE_EN_CHARGE_INTROUVABLE));
            }
        } else {
            personneAPrendreEnCharge = adherent;
        }
        // Le nom de l'établissement est obligatoire
        if (StringUtils.isBlank(demandePriseEnChargeDto.getNomEtablissement())) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_NOM_ETABLISSEMENT));
        }
        // L'adresse de l'établissement est obligatoire
        if (StringUtils.isBlank(demandePriseEnChargeDto.getAdresseEtablissement())) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_ADRESSE_ETABLISSEMENT));
        }
        // La ville de l'établissement est obligatoire
        if (StringUtils.isBlank(demandePriseEnChargeDto.getVilleEtablissement())) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_VILLE_ETABLISSEMENT));
        }
        // La date d'entrée dans l'établissement est obligatoire
        if (demandePriseEnChargeDto.getDateEntree() == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_DATE_ENTREE));
        }
        // La nature des soins est obligatoire
        if (StringUtils.isBlank(demandePriseEnChargeDto.getNatureSoins())) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_NATURE_SOINS));
        }

        // Préparation de la demande de prise en charge...
        // On formate les champs textuels de l'adhérent
        adherent = personneUtil.formatterPersonneSimple(adherent);
        personneAPrendreEnCharge = personneUtil.formatterPersonneSimple(personneAPrendreEnCharge);
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_DEMANDE_PRISE_CHARGE_ADHERENT,
        		new String[] {String.valueOf(adherent.getId()), String.valueOf(adherent.getNumeroClient())}));

        // On créer une action dans Square
        final ActionCreationDto action = new ActionCreationDto();
        final List<HistoriqueCommentaireDto> commentaires = new ArrayList<HistoriqueCommentaireDto>();
        final HistoriqueCommentaireDto commentaire = new HistoriqueCommentaireDto();
        sdf = new SimpleDateFormat(messageSourceUtil.get(MessageKeyUtil.FORMAT_DATE_DD_MM_YYYY));
        final StringBuffer descriptif = new StringBuffer(messageSourceUtil.get(MessageKeyUtil.MESSAGE_DESCRIPTIF_ADHERENT,
        		new String[] {personneAPrendreEnCharge.getCivilite() != null ? personneAPrendreEnCharge.getCivilite().getLibelle() + " " : " ",
        					    personneAPrendreEnCharge.getNom(),
        						personneAPrendreEnCharge.getPrenom(),
        						personneAPrendreEnCharge.getNumeroClient(),
        						demandePriseEnChargeDto.getNomEtablissement(),
        						demandePriseEnChargeDto.getNumeroFaxEtablissement(),
        						demandePriseEnChargeDto.getNumeroVoieEtablissement(),
        						demandePriseEnChargeDto.getNatureVoieEtablissement(),
        						demandePriseEnChargeDto.getAdresseEtablissement(),
        						demandePriseEnChargeDto.getComplementAdresseEtablissement(),
        						demandePriseEnChargeDto.getAutreComplementEtablissement(),
        						demandePriseEnChargeDto.getCodePostalEtablissement(),
        						demandePriseEnChargeDto.getVilleEtablissement(),
        						sdf.format(demandePriseEnChargeDto.getDateEntree().getTime()),
        						demandePriseEnChargeDto.getNatureSoins(),
        						demandePriseEnChargeDto.getCodeDiscipline()}));

        commentaire.setDescriptif(descriptif.toString());
        final Calendar now = Calendar.getInstance();
        action.setIdPersonne(adherent.getId());
        action.setIdStatut(squareMappingService.getIdStatutActionAFaire());
        action.setIdPriorite(squareMappingService.getIdActionPrioriteParDefaut());
        action.setDateAction(now);
        action.setDateAffichageAction(now);
        action.setIdAgence(adherent.getAgence().getIdentifiant());
        // Si l'adhérent a un commercial associé
        if (adherent.getCommercial() != null && adherent.getCommercial().getIdentifiant() != null) {
            action.setIdCommercial(adherent.getCommercial().getIdentifiant());
        }
        action.setIdNatureAction(squareMappingService.getIdNatureActionInternet());
        action.setIdType(squareMappingService.getIdTypeActionPrestationsSinistres());
        action.setIdObjet(squareMappingService.getIdObjetActionDemandeDevisOuPEC());
        action.setIdCreateur(demandePriseEnChargeDto.getIdCreateur());
        action.setReclamation(false);
        commentaires.add(commentaire);
        action.setCommentaires(commentaires);
        actionService.creerAction(action);

        // On récupère les coordonnées de l'adhérent
        final CoordonneesDto coordonneesAdherent = personneService.rechercherCoordonneesParIdPersonne(adherent.getId());
        // On envoie un email de notification à l'adhérent si il a un email personnel
        final EmailDto emailAdherent = personneUtil.getEmailsByNature(coordonneesAdherent.getEmails(), squareMappingService.getIdNatureEmailPersonnel()).get(0);
        if (emailAdherent != null && StringUtils.isNotBlank(emailAdherent.getAdresse())) {
            //TODO changement de dto !
        	final com.square.mail.core.dto.emails.MailDto mailToAdherentDto = new com.square.mail.core.dto.emails.MailDto();

            final InfosModeleEmailDto infosModele = new InfosModeleEmailDto();
            infosModele.setIdModeleEmail(envoiEmailMappingService.getIdModeleDemandePriseEnCharge());
            if (adherent.getCivilite() != null) {
                infosModele.setCiviliteDestinataire(adherent.getCivilite().getLibelle());
            }
            infosModele.setEmailDestinataire(emailAdherent.getAdresse());
            infosModele.setNomDestinataire(adherent.getNom());

            final EmailAvecModeleDto emailAvecModeleDto = new EmailAvecModeleDto();
            emailAvecModeleDto.setInfosModele(infosModele);
            emailAvecModeleDto.setEmail(mailToAdherentDto);
            try {
                mailService.envoyerMailDepuisModele(emailAvecModeleDto);
                logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_NOTIFICATION_DEMANDE_PECHARGE_ENVOYER_ADRESSE,
                		new String[] {emailAdherent.getAdresse()}));
            } catch (Exception e) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_ENVOI_MAIL_NOTIFICATION_DEMANDE_PECHARGE), e);
            }
        } else {
            logger.warn(messageSourceUtil.get(MessageKeyUtil.LOGGER_WARN_AUCUN_MAIL_NOTIFICATION_DEMANDE_PECHARGE_ENVOYER_ADHERENT_NO_ADRESSE));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BeneficiaireDto ajouterBeneficiaire(Long idAdherent, BeneficiaireDto beneficiaireDto) {
        // Vérification paramètres
        if (idAdherent == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_ID_ADHERENT));
        }
        // On récupère l'adhérent
        final PersonneSimpleDto adherent = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(idAdherent);
        if (adherent == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AJOUTER_BENEFICIAIRE_ADHERENT_INTROUVABLE));
        }
        if (beneficiaireDto == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_BENEFICIAIRE_DTO));
        }
        if (beneficiaireDto.getCivilite() == null || beneficiaireDto.getCivilite().getIdentifiant() == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_CIVILITE_BENEFICIAIRE));
        }
        if (StringUtils.isBlank(beneficiaireDto.getNom())) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_NOM_BENEFICIAIRE));
        }
        if (StringUtils.isBlank(beneficiaireDto.getPrenom())) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_PRENOM_BENEFICIAIRE));
        }
        if (beneficiaireDto.getDateNaissance() == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_DATE_NAISSANCE_BENEFICIAIRE));
        }
        if (beneficiaireDto.getTypeRelation() == null || beneficiaireDto.getTypeRelation().getIdentifiant() == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_ID_TYPE_RELATION));
        }
        if (beneficiaireDto.getIdCreateur() == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_ID_CREATEUR));
        }
        final Long idTypeRelationConjoint = squareMappingService.getIdTypeRelationConjoint();
        // Si le bénéficiaire à ajouter est de type conjoint
        if (idTypeRelationConjoint.equals(beneficiaireDto.getTypeRelation().getIdentifiant())) {
            // On vérifie au préalable que l'adhérent n'a pas déjà un conjoint
            final RelationCriteresRechercheDto criterias = new RelationCriteresRechercheDto();
            criterias.setIdPersonne(adherent.getId());
            criterias.setActif(true);
            final List<Long> types = new ArrayList<Long>();
            types.add(idTypeRelationConjoint);
            criterias.setTypes(types);
            final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteres =
                new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);
            final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> result = personneService.rechercherRelationsParCritreres(criteres);
            if (!result.getListResults().isEmpty()) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_RELATION_CONJOINT_EXISTANTE));
            }
        }

        // On créé le bénéficiaire dans Square
        final Calendar maintenant = Calendar.getInstance();
        final PersonnePhysiqueCopieDto infosCopie = mapperDozerBean.map(beneficiaireDto, PersonnePhysiqueCopieDto.class);
        infosCopie.setIdPersonneSource(adherent.getId());
        final PersonneDto beneficiaireCree = personnePhysiqueService.creerUneCopiePersonne(infosCopie);
        // On créé la relation entre l'adhérent et le bénéficiaire
        final RelationDto relation = new RelationDto();
        relation.setIdPersonnePrincipale(adherent.getId());
        relation.setIdPersonne(beneficiaireCree.getIdentifiant());
        relation.setType(beneficiaireDto.getTypeRelation());
        relation.setDateDebut(maintenant);
        personneService.creerRelation(relation);
        // On créé l'action
        final ActionCreationDto action = new ActionCreationDto();
        final Calendar now = Calendar.getInstance();
        action.setIdPersonne(adherent.getId());
        action.setIdStatut(squareMappingService.getIdStatutActionAFaire());
        action.setIdPriorite(squareMappingService.getIdActionPrioriteParDefaut());
        action.setDateAction(now);
        action.setDateAffichageAction(now);
        action.setIdAgence(squareMappingService.getIdAgencePoleFidelisation());
        // Mantis 8458 : on affecte plus de commercial
        // if (adherent.getCommercial() != null && adherent.getCommercial().getIdentifiant() != null) {
        // action.setIdCommercial(adherent.getCommercial().getIdentifiant());
        // }
        action.setIdNatureAction(squareMappingService.getIdNatureActionInternet());
        action.setIdType(squareMappingService.getIdTypeActionOpportunite());
        action.setIdObjet(squareMappingService.getIdObjetActionChgtCompoFamiliale());
        action.setIdCreateur(beneficiaireDto.getIdCreateur());
        action.setReclamation(false);
        final StringBuffer descriptif = new StringBuffer(messageSourceUtil.get(MessageKeyUtil.MESSAGE_DESCRIPTIF_BENEFICIAIRE,
        		new String[] {beneficiaireDto.getTypeRelation() != null ? beneficiaireDto.getTypeRelation().getLibelle() : "",
        					beneficiaireDto.getCivilite() != null ? beneficiaireDto.getCivilite().getLibelle() : " ",
        					beneficiaireDto.getNom(),
        					beneficiaireDto.getPrenom(),
        					beneficiaireDto.getDateNaissance() != null ? sdf.format(beneficiaireDto.getDateNaissance().getTime()) : ""}));

        final List<HistoriqueCommentaireDto> commentaires = new ArrayList<HistoriqueCommentaireDto>();
        final HistoriqueCommentaireDto commentaire = new HistoriqueCommentaireDto();
        commentaire.setDescriptif(descriptif.toString());
        commentaires.add(commentaire);
        action.setCommentaires(commentaires);
        actionService.creerAction(action);
        // On retourne le bénéficiaire créé
        return mapperDozerBean.map(beneficiaireCree, BeneficiaireDto.class);
    }

    @Override
    public List<GarantieBaremeDto> getListeGarantiesBaremesAdherent(GarantieBaremeCriteresDto criteres) {
    	sdfMois = new SimpleDateFormat(messageSourceUtil.get(MessageKeyUtil.FORMAT_DATE_MM));
        // Vérification paramètres
        if (criteres == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_RECUPERATION_GARANTIES_BAREMES_CRITERES_NULL));
        }
        final List<GarantieBareme> listeGarantiesBaremes = garantieBaremeDao.getListeGarantiesBaremesByCriteres(criteres);
        final List<GarantieBaremeDto> listeGarantiesBaremesDto = new ArrayList<GarantieBaremeDto>();
        for (GarantieBareme garantieBareme : listeGarantiesBaremes) {
        	final GarantieBaremeDto garantieBaremeDto = mapperDozerBean.map(garantieBareme, GarantieBaremeDto.class);
        	garantieBaremeDto.setDateAdhesion(garantieBareme.getGarantie().getDateAdhesion());
        	garantieBaremeDto.setDateResiliation(garantieBareme.getGarantie().getDateResiliation());
            if (garantieBareme.getGarantie().getDateAdhesion() != null) {
            	garantieBaremeDto.setCodeMois(sdfMois.format(garantieBareme.getGarantie().getDateAdhesion().getTime()));
            }
            // on recupere l'age de la personne
            final Long uidPersonne =
                garantieBareme.getGarantie().getUidBeneficiaire() != null ? garantieBareme.getGarantie().getUidBeneficiaire() : garantieBareme
                        .getGarantie().getUidAssure();
            final PersonneDto personne = personnePhysiqueService.rechercherPersonneParIdentifiant(uidPersonne);
            garantieBaremeDto.setCodeAgeMin(getAge(personne.getDateNaissance()));
            garantieBaremeDto.setCodeAgeMinMillesime(getAgeMillesime(personne.getDateNaissance()));
            listeGarantiesBaremesDto.add(garantieBaremeDto);
        }
        return listeGarantiesBaremesDto;
    }

    @Override
    public List<CoupleBaremeDto> getListeCouplesBaremesAdherent(GarantieBaremeCriteresDto criteres) {
        // Vérification paramètres
        if (criteres == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_RECUPERATION_GARANTIES_BAREMES_CRITERES_NULL));
        }
        final List<CoupleBaremeDto> listeCouplesBaremes = garantieBaremeDao.getListeCouplesBaremesByCriteres(criteres);
        return listeCouplesBaremes;
    }

    private Integer getAge(Calendar dateNaissance) {
        final Integer ageMillesime = getAgeMillesime(dateNaissance);
        final Integer age = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) > dateNaissance.get(Calendar.DAY_OF_YEAR) ? ageMillesime : ageMillesime - 1;
        return age;
    }

    private Integer getAgeMillesime(Calendar dateNaissance) {
        return Calendar.getInstance().get(Calendar.YEAR) - dateNaissance.get(Calendar.YEAR);
    }

    @Override
    public InfosIdentifiantPersonneDto getInfosAdherentPrincipalByNumAdherent(String numAdherent) {
        // Récupération de l'identifiant de la personne
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        criterias.setNumeroClient(numAdherent);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, 1);
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
        if (result == null || result.getListResults() == null || result.getListResults().size() != 1) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_NUM_CLIENT_INEXISTANTE, new String[] {numAdherent}));
        }
        final Long idPersonne = result.getListResults().get(0).getId();
        if (idPersonne != null) {
            final Long idPersonnePrincipale = personneDao.getAdherentPrincipalPersonne(idPersonne);
            if (idPersonnePrincipale != null) {
                // Récupération du numéro d'adhérent correspondant à la personne principale
                final PersonneDto personnePrincipale = (PersonneDto) personneService.rechercherPersonneParId(idPersonnePrincipale);
                if (personnePrincipale == null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANT, new String[] {idPersonnePrincipale.toString()}));
                }
                // Création du DTO contenant les identifiants
                final InfosIdentifiantPersonneDto infosIdentifiantPersonneDto = new InfosIdentifiantPersonneDto();
                infosIdentifiantPersonneDto.setIdPersonne(personnePrincipale.getIdentifiant());
                infosIdentifiantPersonneDto.setNumClient(personnePrincipale.getNumClient());
                return infosIdentifiantPersonneDto;
            }
        }
        return null;
    }

    @Override
    public List<com.square.adherent.noyau.dto.adherent.PersonneDto> getListeAssurePrincipalParPagination(int debutPagination, int taillePagination) {
        // Création de l'objet de retour
        final List<com.square.adherent.noyau.dto.adherent.PersonneDto> listeAssures = new ArrayList<com.square.adherent.noyau.dto.adherent.PersonneDto>();

        // Recherche des personnes
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        final List<Long> listeNatures = new ArrayList<Long>();
        listeNatures.add(squareMappingService.getIdNaturePersonneAdherent());
        criterias.setListeNatures(listeNatures);
        final List<Long> listeReseaux = new ArrayList<Long>();
        listeReseaux.add(squareMappingService.getIdReseauIndividuel());
        criterias.setListeReseaux(listeReseaux);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, debutPagination, taillePagination);
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);

        // Récupération des informations supplémentaires nécessaires
        final Long idNatureEmailPersonnel = squareMappingService.getIdNatureEmailPersonnel();
        final Long idTelephoneFixe = squareMappingService.getIdNatureTelephoneFixe();
        if (result != null && result.getListResults() != null) {
            for (PersonneSimpleDto personne : result.getListResults()) {
                final com.square.adherent.noyau.dto.adherent.PersonneDto assure =
                    mapperDozerBean.map(personne, com.square.adherent.noyau.dto.adherent.PersonneDto.class);
                // Récupération des coordonnées
                final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(assure.getId());
                if (coordonnees != null) {
                    if (coordonnees.getEmails() != null && coordonnees.getEmails().size() > 0) {
                        // Email
                        for (EmailDto email : coordonnees.getEmails()) {
                            if (email.getNatureEmail() != null && idNatureEmailPersonnel.equals(email.getNatureEmail().getIdentifiant())) {
                                assure.setAdresseEmail(email.getAdresse());
                                break;
                            }
                        }

                        // Téléphone
                        for (TelephoneDto telephone : coordonnees.getTelephones()) {
                            if (telephone.getNature() != null && idTelephoneFixe.equals(telephone.getNature().getIdentifiant())) {
                                assure.setNumeroTelephoneFixe(telephone.getNumero());
                                break;
                            }
                        }

                        // Adresse
                        for (AdresseDto adresse : coordonnees.getAdresses()) {
                            if (adresse.getNature() != null && idTelephoneFixe.equals(adresse.getNature().getIdentifiant())) {
                                final com.square.adherent.noyau.dto.adherent.AdresseDto adressePrincipale =
                                    mapperDozerBean.map(adresse, com.square.adherent.noyau.dto.adherent.AdresseDto.class);
                                assure.setAdressePrincipale(adressePrincipale);
                                break;
                            }
                        }
                    }
                }
                listeAssures.add(assure);
            }
        }
        return listeAssures;
    }

    @Override
    public List<com.square.adherent.noyau.dto.adherent.PersonneDto> getListeBeneficaireParPagination(int debutPagination, int taillePagination) {
        // Création de l'objet de retour
        final List<com.square.adherent.noyau.dto.adherent.PersonneDto> listeBeneficiaires = new ArrayList<com.square.adherent.noyau.dto.adherent.PersonneDto>();

        // Recherche des personnes
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        final List<Long> listeNatures = new ArrayList<Long>();
        listeNatures.add(squareMappingService.getIdNaturePersonneBeneficiaireAdherent());
        criterias.setListeNatures(listeNatures);
        final List<Long> listeReseaux = new ArrayList<Long>();
        listeReseaux.add(squareMappingService.getIdReseauIndividuel());
        criterias.setListeReseaux(listeReseaux);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, debutPagination, taillePagination);
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);

        // Récupération des informations supplémentaires nécessaires
        final Long idNatureEmailPersonnel = squareMappingService.getIdNatureEmailPersonnel();
        final Long idTelephoneFixe = squareMappingService.getIdNatureTelephoneFixe();
        if (result != null && result.getListResults() != null) {
            for (PersonneSimpleDto personne : result.getListResults()) {
                final com.square.adherent.noyau.dto.adherent.PersonneDto beneficiaire =
                    mapperDozerBean.map(personne, com.square.adherent.noyau.dto.adherent.PersonneDto.class);
                // Récupération des coordonnées
                final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(personne.getId());
                if (coordonnees != null) {
                    if (coordonnees.getEmails() != null && coordonnees.getEmails().size() > 0) {
                        // Email
                        for (EmailDto email : coordonnees.getEmails()) {
                            if (email.getNatureEmail() != null && idNatureEmailPersonnel.equals(email.getNatureEmail().getIdentifiant())) {
                                beneficiaire.setAdresseEmail(email.getAdresse());
                                break;
                            }
                        }

                        // Téléphone
                        for (TelephoneDto telephone : coordonnees.getTelephones()) {
                            if (telephone.getNature() != null && idTelephoneFixe.equals(telephone.getNature().getIdentifiant())) {
                                beneficiaire.setNumeroTelephoneFixe(telephone.getNumero());
                                break;
                            }
                        }

                        // Adresse
                        for (AdresseDto adresse : coordonnees.getAdresses()) {
                            if (adresse.getNature() != null && idTelephoneFixe.equals(adresse.getNature().getIdentifiant())) {
                                final com.square.adherent.noyau.dto.adherent.AdresseDto adressePrincipale =
                                    mapperDozerBean.map(adresse, com.square.adherent.noyau.dto.adherent.AdresseDto.class);
                                beneficiaire.setAdressePrincipale(adressePrincipale);
                                break;
                            }
                        }
                    }
                }
                listeBeneficiaires.add(beneficiaire);
            }
        }
        return listeBeneficiaires;
    }

    @Override
    public List<BeneficiaireDto> getListeInfosBeneficiairesPersonne(Long idPersonne) {
        // Création de l'objet de retour
        final List<BeneficiaireDto> listeBeneficiaires = new ArrayList<BeneficiaireDto>();
        // Récupération des identifiants de bénéficiaires
        final List<Long> listeIdsBeneficiaires = personneDao.getListeBeneficiairesPersonne(idPersonne);
        if (listeIdsBeneficiaires != null && listeIdsBeneficiaires.size() > 0) {
            for (Long idBeneficiaire : listeIdsBeneficiaires) {
                final PersonneDto personne = personnePhysiqueService.rechercherPersonneParIdentifiant(idBeneficiaire);
                final BeneficiaireDto beneficiaire = mapperDozerBean.map(personne, BeneficiaireDto.class);
                listeBeneficiaires.add(beneficiaire);
            }
        }
        return listeBeneficiaires;
    }

    @Override
    public com.square.adherent.noyau.dto.adherent.PersonneDto getPersonneById(Long idPersonne) {
        final PersonneDto personneSquare = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne);

        if (personneSquare != null) {
            final com.square.adherent.noyau.dto.adherent.PersonneDto personneEcm =
                mapperDozerBean.map(personneSquare, com.square.adherent.noyau.dto.adherent.PersonneDto.class);
            // Récupération des informations supplémentaires nécessaires
            final Long idNatureEmailPersonnel = squareMappingService.getIdNatureEmailPersonnel();
            final Long idTelephoneFixe = squareMappingService.getIdNatureTelephoneFixe();
            // Récupération des coordonnées
            final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(personneSquare.getIdentifiant());
            if (coordonnees != null) {
                if (coordonnees.getEmails() != null && coordonnees.getEmails().size() > 0) {
                    // Email
                    for (EmailDto email : coordonnees.getEmails()) {
                        if (email.getNatureEmail() != null && idNatureEmailPersonnel.equals(email.getNatureEmail().getIdentifiant())) {
                            personneEcm.setAdresseEmail(email.getAdresse());
                            break;
                        }
                    }

                    // Téléphone
                    for (TelephoneDto telephone : coordonnees.getTelephones()) {
                        if (telephone.getNature() != null && idTelephoneFixe.equals(telephone.getNature().getIdentifiant())) {
                            personneEcm.setNumeroTelephoneFixe(telephone.getNumero());
                            break;
                        }
                    }

                    // Adresse
                    for (AdresseDto adresse : coordonnees.getAdresses()) {
                        if (adresse.getNature() != null && idTelephoneFixe.equals(adresse.getNature().getIdentifiant())) {
                            final com.square.adherent.noyau.dto.adherent.AdresseDto adressePrincipale =
                                mapperDozerBean.map(adresse, com.square.adherent.noyau.dto.adherent.AdresseDto.class);
                            personneEcm.setAdressePrincipale(adressePrincipale);
                            break;
                        }
                    }
                }
            }
            return personneEcm;
        }
        return null;
    }

    @Override
    public com.square.adherent.noyau.dto.adherent.PersonneDto getPersonneByNumClient(String numClient) {
        // Recherche de la personne
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        criterias.setNumeroClient(numClient);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres = new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, 1);
        final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);

        if (result != null && result.getListResults() != null) {
            final PersonneSimpleDto personneSquare = result.getListResults().get(0);
            // Récupération des informations supplémentaires nécessaires
            final Long idNatureEmailPersonnel = squareMappingService.getIdNatureEmailPersonnel();
            final Long idTelephoneFixe = squareMappingService.getIdNatureTelephoneFixe();
            final com.square.adherent.noyau.dto.adherent.PersonneDto personneEcm =
                mapperDozerBean.map(personneSquare, com.square.adherent.noyau.dto.adherent.PersonneDto.class);
            // Récupération des coordonnées
            final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(personneSquare.getId());
            if (coordonnees != null) {
                if (coordonnees.getEmails() != null && coordonnees.getEmails().size() > 0) {
                    // Email
                    for (EmailDto email : coordonnees.getEmails()) {
                        if (email.getNatureEmail() != null && idNatureEmailPersonnel.equals(email.getNatureEmail().getIdentifiant())) {
                            personneEcm.setAdresseEmail(email.getAdresse());
                            break;
                        }
                    }

                    // Téléphone
                    for (TelephoneDto telephone : coordonnees.getTelephones()) {
                        if (telephone.getNature() != null && idTelephoneFixe.equals(telephone.getNature().getIdentifiant())) {
                            personneEcm.setNumeroTelephoneFixe(telephone.getNumero());
                            break;
                        }
                    }

                    // Adresse
                    for (AdresseDto adresse : coordonnees.getAdresses()) {
                        if (adresse.getNature() != null && idTelephoneFixe.equals(adresse.getNature().getIdentifiant())) {
                            final com.square.adherent.noyau.dto.adherent.AdresseDto adressePrincipale =
                                mapperDozerBean.map(adresse, com.square.adherent.noyau.dto.adherent.AdresseDto.class);
                            personneEcm.setAdressePrincipale(adressePrincipale);
                            break;
                        }
                    }
                }
            }
            return personneEcm;
        }
        return null;
    }

    /**
     * Setter function.
     * @param personneService the personneService to set
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Setter function.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Setter function.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Setter function.
     * @param optionDao the optionDao to set
     */
    public void setOptionDao(OptionDao optionDao) {
        this.optionDao = optionDao;
    }

    /**
     * Setter function.
     * @param optionTypeDao the optionTypeDao to set
     */
    public void setOptionTypeDao(OptionTypeDao optionTypeDao) {
        this.optionTypeDao = optionTypeDao;
    }

    /**
     * Définit la valeur de garantieDao.
     * @param garantieDao la nouvelle valeur de garantieDao
     */
    public void setGarantieDao(GarantieDao garantieDao) {
        this.garantieDao = garantieDao;
    }

    /**
     * Définit la valeur de personneDao.
     * @param personneDao la nouvelle valeur de personneDao
     */
    public void setPersonneDao(PersonneDao personneDao) {
        this.personneDao = personneDao;
    }

    /**
     * Définit la valeur de reserveBancoDao.
     * @param reserveBancoDao la nouvelle valeur de reserveBancoDao
     */
    public void setReserveBancoDao(ReserveBancoDao reserveBancoDao) {
        this.reserveBancoDao = reserveBancoDao;
    }

    /**
     * Set the magazineDao value.
     * @param magazineDao the magazineDao to set
     */
    public void setMagazineDao(MagazineDao magazineDao) {
        this.magazineDao = magazineDao;
    }

    /**
     * Setter function.
     * @param espaceClientInternetService the espaceClientInternetService to set
     */
    public void setEspaceClientInternetService(EspaceClientInternetService espaceClientInternetService) {
        this.espaceClientInternetService = espaceClientInternetService;
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
     * @param actionService the actionService to set
     */
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    /**
     * Setter function.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
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
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Set the value of garantieBaremeDao.
     * @param garantieBaremeDao the garantieBaremeDao to set
     */
    public void setGarantieBaremeDao(GarantieBaremeDao garantieBaremeDao) {
        this.garantieBaremeDao = garantieBaremeDao;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

}
