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
package com.square.synchro.app.noyau.jms.mdp;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.square.core.model.dto.AgenceDto;
import com.square.core.model.dto.IdentifiantEidLibelleDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RessourceDto;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.RessourceService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.mail.core.service.interfaces.gestionmails.EmailMappingService;
import com.square.mail.core.service.interfaces.gestionmails.EmailService;
import com.square.synchro.app.noyau.dao.interfaces.SynchroAppTableCrspDao;
import com.square.synchro.app.noyau.dto.DefaultMessageSynchroAppDto;
import com.square.synchro.app.noyau.util.MessageKeyUtil;
import com.square.user.core.dto.AgenceReelleDto;
import com.square.user.core.service.interfaces.AgenceService;
import com.square.user.core.service.interfaces.UtilisateurMappingService;
import com.square.user.core.service.interfaces.UtilisateurService;

/**
 * Mdp pour de l'application Square.
 * @author Goumard Stephane (stephane.goumard@scub.net) - SCUB
 */
public class SynchroAppSquareMdp implements MessageListener {
    /**
     * Dao pour l'enretistrement de correspondance.
     */
    @SuppressWarnings("unused")
	private SynchroAppTableCrspDao synchroAppTableCrspDao;

    /** File DMQ. */
    private JmsTemplate jmsSmatisDmqSender;

    /**
     * Service PP de Square.
     */
    private PersonnePhysiqueService personnePhysiqueService;

    /**
     * Service de Mapping Square.
     */
    @SuppressWarnings("unused")
    private SquareMappingService squareMappingService;

    /** Service des actions square. */
    @SuppressWarnings("unused")
    private ActionService actionServiceSquare;

    /** Service des ressources. **/
    private RessourceService ressourceService;

    /** Service des mails. */
    @SuppressWarnings("unused")
    private EmailService emailService;

    /** Service de mapping des mails. */
    @SuppressWarnings("unused")
    private EmailMappingService emailMappingService;

    /** Service des utilisateurs. */
    @SuppressWarnings("unused")
    private UtilisateurService utilisateurService;

    /** Service ded mapping des utilisateurs. */
    @SuppressWarnings("unused")
    private UtilisateurMappingService utilisateurMappingService;

    /** Service des agences. */
    private AgenceService agenceService;

    /** Service des dimansions. */
    @SuppressWarnings("unused")
    private DimensionService dimensionService;

    /** Services des agences de square. */
    private com.square.core.service.interfaces.AgenceService agenceSquareService;

    /** MapperDozer. */
    private MapperDozerBean mapperDozerBean;

    /** Adresse du lien de consultation d'un mail. */
    private String lienConsultationMail;

    /** Gestion des messages. */
    private MessageSourceUtil messageSourceUtil;

    /**
     * Logger.
     */
    private Logger logger = RootLogger.getLogger(SynchroAppSquareMdp.class);

    @Override
    public void onMessage(final Message message) {
        try {
            logger.debug(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MESSAGE_TRAITEMENT_ENCOUR,
              		 new String[] {String.valueOf(message.getJMSMessageID())})));
            if (!message.getJMSRedelivered()) {
                final DefaultMessageSynchroAppDto messageSynchro = (DefaultMessageSynchroAppDto) (((ObjectMessage) message).getObject());
                logger.debug(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECEPTION_MESSAGE,
                 		 new String[] {messageSynchro.getMsgSynchroOrigineHeader(), String.valueOf(messageSynchro.getMsgSynchroIdObjet())})));
                if (messageSynchro.getMsgSynchroOrigineHeader().equals(DefaultMessageSynchroAppDto.MSOH_GESTIONMAILS_SEND_MAIL)) {
                    processSendMailFromGestionMails(messageSynchro.getMsgSynchroIdObjet(), messageSynchro.getMsgSynchroOrigineHeader());
                }
                else if (messageSynchro.getMsgSynchroOrigineHeader().equals(DefaultMessageSynchroAppDto.MSOH_HABILITATIONS_ACTIVER_UTILISATEUR)) {
                    processActiverRessource(messageSynchro.getMsgSynchroIdObjet());
                }
                else if (messageSynchro.getMsgSynchroOrigineHeader().equals(DefaultMessageSynchroAppDto.MSOH_HABILITATIONS_UPDATE_UTILISATEUR)) {
                    processUpdateRessource(messageSynchro.getMsgSynchroIdObjet());
                }
                else if (messageSynchro.getMsgSynchroOrigineHeader().equals(DefaultMessageSynchroAppDto.MSOH_HABILITATIONS_CREATEORUPDATE_AGENCE)) {
                    processCreateOrUpdateAgence(messageSynchro.getMsgSynchroIdObjet());
                }
                else if (messageSynchro.getMsgSynchroOrigineHeader().equals(DefaultMessageSynchroAppDto.MSOH_HABILITATIONS_SUPPRIMER_AGENCE)) {
                    processDelelteAgence(messageSynchro.getMsgSynchroIdObjet());
                }
            }
            else {
                // Envoi vers la DMQ :
                final MessageCreator messageCreator = new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        return message;
                    }
                };
                logger.debug(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_ENVOIE_DMQ,
                		 new String[] {String.valueOf(message.getJMSMessageID())})));
                jmsSmatisDmqSender.send(messageCreator);
            }
        }
        catch (JMSException e) {
            logger.fatal(e, e);
            throw new TechnicalException(e);
        }
    }

    /**
     * Process gérant l'envoie de mails à partir de la gestion des mails. <br/>
     * Crée une action d'envoi de mail dans Square
     * @param identifiantMail l'identifiant du mail
     * @param msgOrigineHeader le header du message d'origine
     */
    private void processSendMailFromGestionMails(String identifiantMail, String msgOrigineHeader) {
//        try {
//            // Récupération du mail dans la gestion des mails
//            final com.smatis.gestion.mail.noyau.dto.EmailDto emailDto = emailService.getEmailById(Long.valueOf(identifiantMail));
//            if (emailDto == null) {
//                throw new BusinessException("Synchronisation " + msgOrigineHeader + " impossible : le mail " + identifiantMail + " n'existe pas");
//            }
//            // Création de l'action que si le mail n'a jamais été traité
//            // Recherche de la correspondance
//            final String idCrsp =
//                synchroAppTableCrspDao.rechercherCrsp(identifiantMail, DefaultMessageSynchroAppDto.getAppFromMsgOrigineHeader(msgOrigineHeader),
//                    DefaultMessageSynchroAppDto.getAppFromMsgOrigineHeader(DefaultMessageSynchroAppDto.MSOH_SQUARE_SEND_MAIL), DefaultMessageSynchroAppDto
//                            .getTypeObjetFromMsgOrigineHeader(msgOrigineHeader));
//            if (idCrsp == null) {
//                final ActionCreationDto actionDto = new ActionCreationDto();
//                // Récupération de la personne à partir du numéro d'adhérent
//                PersonneSimpleDto personne = null;
//                String numAdherent = null;
//                if (emailDto.getInfosProspect() != null) {
//                    numAdherent = emailDto.getInfosProspect().getNumeroAdherent();
//                    personne = recupererPersonneSquareParNumeroClient(numAdherent);
//                }
//                if (personne == null) {
//                    logger.error("Synchronisation email " + identifiantMail + " impossible : personne non trouvée, num adhérent =  " + numAdherent);
//                    throw new BusinessException("Synchronisation " + msgOrigineHeader + " impossible pour le mail " + identifiantMail
//                        + " : la personne n'existe pas");
//                }
//                // Récupération de la nature de l'action (email sortant si expéditeur Smatis, sinon email entrant)
//                Long idNatureAction = squareMappingService.getIdNatureActionEmailEntrant();
//                if (emailDto.getExpediteur() != null && emailDto.getExpediteur().getAdresseEmail() != null
//                    && emailMappingService.getExpediteurEmailSmatis().equals(emailDto.getExpediteur().getAdresseEmail())) {
//                    idNatureAction = squareMappingService.getIdNatureActionEmailSortant();
//                }
//                // Récupération de l'action source à partir du mail parent
//                Long idActionSource = null;
//                if (emailDto.getIdParent() != null && !emailDto.getIdParent().equals(emailDto.getId())) {
//                    final String idCrspActionSource =
//                        synchroAppTableCrspDao.rechercherCrsp(String.valueOf(emailDto.getIdParent()), DefaultMessageSynchroAppDto
//                                .getAppFromMsgOrigineHeader(msgOrigineHeader), DefaultMessageSynchroAppDto
//                                .getAppFromMsgOrigineHeader(DefaultMessageSynchroAppDto.MSOH_SQUARE_SEND_MAIL), DefaultMessageSynchroAppDto
//                                .getTypeObjetFromMsgOrigineHeader(msgOrigineHeader));
//                    if (idCrspActionSource != null) {
//                        idActionSource = Long.valueOf(idCrspActionSource);
//                    }
//                    else {
//                        logger.warn("Corrrespondance non trouvée pour le mail parent " + emailDto.getIdParent() + " du mail " + identifiantMail);
//                    }
//                }
//                // Récupération du créateur, de l'agence, du commercial responsable du mail
//                // Si l'utilisateur à charge du mail est renseigné, on le récupère
//                Long idCreateur = null;
//                Long idAgence = null;
//                Long idCommercial = null;
//                if (emailDto.getUtilisateurCharge() != null) {
//                    final RessourceDto commercial = recupererRessourceSquareFromHabilitations(emailDto.getUtilisateurCharge().getIdentifiant());
//                    idCreateur = commercial.getId();
//                    idCommercial = commercial.getId();
//                    idAgence = commercial.getAgence().getIdentifiant();
//                }
//                else {
//                    // Sinon on récupère le créateur, responsable et agence de la personne physique
//                    if (personne.getCreateur() != null) {
//                        idCreateur = personne.getCreateur().getIdentifiant();
//                    }
//                    if (personne.getCommercial() != null) {
//                        idCommercial = personne.getCommercial().getIdentifiant();
//                    }
//                    if (personne.getAgence() != null) {
//                        idAgence = personne.getAgence().getIdentifiant();
//                    }
//                }
//                logger.debug("Action email : créateur = " + idCreateur + ", commercial = " + idCommercial + ", agence = " + idAgence);
//                actionDto.setIdCreateur(idCreateur);
//                actionDto.setIdCommercial(idCommercial);
//                actionDto.setIdAgence(idAgence);
//                actionDto.setDateAction(emailDto.getDateEnvoi());
//                actionDto.setIdStatut(squareMappingService.getIdStatutActionTermine());
//                actionDto.setIdPersonne(personne.getId());
//                actionDto.setIdNatureAction(idNatureAction);
//                // Type = Relance
//                actionDto.setIdType(squareMappingService.getIdTypeActionRelance());
//                // Objet = Client
//                actionDto.setIdObjet(squareMappingService.getIdObjetActionClient());
//                actionDto.setIdActionSource(idActionSource);
//                // Commentaire = sujet du mail + lien de consultation
//                final StringBuffer commentaire = new StringBuffer();
//                commentaire.append("<a href='").append(lienConsultationMail).
//    	append(emailDto.getId()).append(">").append(emailDto.getSujet()).append("</a> : ");
//                commentaire.append(lienConsultationMail).append(emailDto.getId());
//                final HistoriqueCommentaireDto historiqueCommentaireDto = new HistoriqueCommentaireDto();
//                historiqueCommentaireDto.setDescriptif(commentaire.toString());
//                final List<HistoriqueCommentaireDto> commentaires = new ArrayList<HistoriqueCommentaireDto>();
//                commentaires.add(historiqueCommentaireDto);
//                actionDto.setCommentaires(commentaires);
//
//                // Création de l'action
//                final SynchroAppTableCrsp crsp = new SynchroAppTableCrsp();
//                crsp.setIdentifiantApp(DefaultMessageSynchroAppDto.getAppFromMsgOrigineHeader(DefaultMessageSynchroAppDto.MSOH_SQUARE_SEND_MAIL));
//                crsp.setIdentifiantAppCrsp(DefaultMessageSynchroAppDto.getAppFromMsgOrigineHeader(msgOrigineHeader));
//                crsp.setIdentifiantObjet(actionServiceSquare.creerAction(actionDto).getIdentifiant().toString());
//                crsp.setIdentifiantObjetCrsp(identifiantMail);
//                crsp.setTypeObjet(DefaultMessageSynchroAppDto.getTypeObjetFromMsgOrigineHeader(msgOrigineHeader));
//                synchroAppTableCrspDao.save(crsp);
//            }
//        }
//        catch (Exception e) {
//            if (e instanceof ControleIntegriteException) {
//                for (SousRapportDto rapport : ((ControleIntegriteException) e).getRapport().getRapports().values()) {
//                    if (rapport.getErreur().booleanValue()) {
//                        logger.error(rapport.getAttribut() + "=>" + rapport.getMessage());
//                    }
//                }
//            }
//            logger.error("ID MAIL = " + identifiantMail);
//            throw new TechnicalException(e);
//        }
    }

    /**
     * Récupère une personne Square par son numéro de client.
     * @param numeroClient le numéro de client
     * @return la personne
     */
    @SuppressWarnings("unused")
	private PersonneSimpleDto recupererPersonneSquareParNumeroClient(String numeroClient) {
        if (numeroClient != null && !"".equals(numeroClient.trim())) {
            // Récupération des informations dans Square à partir du numéro de client
            final PersonneCriteresRechercheDto criteres = new PersonneCriteresRechercheDto();
            criteres.setNumeroClient(numeroClient);
            criteres.setRechercheStricte(true);
            final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> remotePagingCriteria =
                new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);
            // Appel du service de recherche
            final RemotePagingResultsDto<PersonneSimpleDto> resultats = personnePhysiqueService.rechercherPersonneFullTextParCriteres(remotePagingCriteria);
            // On récupère les infos du premier de la liste
            if (resultats != null && !resultats.getListResults().isEmpty()) {
                return resultats.getListResults().get(0);
            }
        }
        // On retourne null si pas trouvé
        return null;
    }

    /**
     * Permet de recuperer un utilisateur square en fonction d'un utilisateur Habilitations.
     * @param idUtilisateur l'identifiant de l'utilisateur Habilitations
     * @return la ressource
     */
    @SuppressWarnings("unused")
	private RessourceDto recupererRessourceSquareFromHabilitations(Long idUtilisateur) {
        logger.debug(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_RECHERCHE_CORRESPONDANCE_USER_HABILITATIONS,
       		 new String[] {String.valueOf(String.valueOf(idUtilisateur))})));
        if (idUtilisateur != null) {
            return ressourceService.rechercherRessourceParEid(String.valueOf(idUtilisateur));
        }
        return null;
    }

    /**
     * Active/Désactive une ressource.
     * @param idRessource l'identifiant de la ressource.
     */
    private void processActiverRessource(String eidRessource) {
//        final Long idRessource = Long.valueOf(eidRessource);
//        final UtilisateurDto utilisateurDto = utilisateurService.getUtilisateurById(idRessource);
//        if (utilisateurDto == null) {
//            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.UTILISATEUR_INEXISTANT));
//        }
//        // on détecte si l'utilisateur a un rôle de Square
//        if (utilisateurService.hasRoleByNom(utilisateurDto.getId(), utilisateurMappingService.getPrefixeRoleSquare())) {
//            // Recherche de la ressource dans Square
//            final RessourceDto ressource = ressourceService.isRessourceExiste(eidRessource);
//            if (ressource != null) {
//                final boolean isActif =
//                    utilisateurDto.getActif() != null && utilisateurDto.getValide() != null && utilisateurDto.getActif() && utilisateurDto.getValide();
//                ressourceService.activerRessource(ressource.getId(), isActif);
//            }
//            else {
//                if (utilisateurDto.getActif() != null && utilisateurDto.getValide() != null && utilisateurDto.getActif() && utilisateurDto.getValide()
//                        && (utilisateurDto.getDateFinValidite() == null || utilisateurDto.getDateFinValidite().after(Calendar.getInstance()))
//                        && utilisateurService.hasRoleByNom(utilisateurDto.getId(), utilisateurMappingService.getPrefixeRoleSquare())) {
//                    // Si la ressource n'existe pas dans square, on la crée
//                    final RessourceDto ressourceDto = mapperUtilisateur(utilisateurDto);
//                    ressourceDto.setIdExt(utilisateurDto.getId().toString());
//                    final RessourceDto ressourceCreee = ressourceService.creerRessource(ressourceDto);
//
//                }
//            }
//        }
//        else {
//            // Si suppression du rôle Square, désactivation dans Square
//            final RessourceDto ressourceSquare = ressourceService.isRessourceExiste(eidRessource);
//            if (ressourceSquare != null && ressourceSquare.getEtat() != null
//                    && squareMappingService.getIdEtatActifRessource().equals(ressourceSquare.getEtat().getIdentifiant())) {
//                ressourceService.activerRessource(ressourceSquare.getId(), false);
//            }
//        }
    }

    /**
     * Met à jour une ressource.
     * @param idRessource l'identifiant de la ressource.
     */
    private void processUpdateRessource(String idRessource) {
//        final Long eidRessource = Long.valueOf(idRessource);
//        final UtilisateurDto utilisateurDto = utilisateurService.getUtilisateurById(eidRessource);
//        // on détecte si l'utilisateur a un rôle de Square
//        if (utilisateurService.hasRoleByNom(utilisateurDto.getId(), utilisateurMappingService.getPrefixeRoleSquare())) {
//            final RessourceDto ressourceDto = mapperUtilisateur(utilisateurDto);
//            if (logger.isDebugEnabled()) {
//	            if (ressourceDto.getAgence() != null && ressourceDto.getAgence().getIdentifiant() != null) {
//	            	logger.info("Agence ID après mapping ==> " + ressourceDto.getAgence().getIdentifiant());
//	            	logger.info("Agence LIBELLE après mapping ==> " + ressourceDto.getAgence().getLibelle());
//	            } else {
//	            	logger.info("Agence NULL après mapping ==> " + (ressourceDto.getAgence() == null));
//	            }
//            }
//            ressourceService.updateRessource(ressourceDto);
//            // modification des actions associées à la ressource
//            final ActionCritereRechercheDto critere = new ActionCritereRechercheDto();
//            final List<Long> idCommerciaux = new ArrayList<Long>();
//            idCommerciaux.add(eidRessource);
//            critere.setIdCommerciaux(idCommerciaux);
//            actionServiceSquare.transfererActionsParCritere(critere, ressourceDto.getAgence().getIdentifiant(), eidRessource);
//        }
//        else {
//            // Si suppression du rôle Square, désactivation dans Square
//            final RessourceDto ressourceSquare = ressourceService.isRessourceExiste(idRessource);
//            if (ressourceSquare != null && ressourceSquare.getEtat() != null
//                    && squareMappingService.getIdEtatActifRessource().equals(ressourceSquare.getEtat().getIdentifiant())) {
//                ressourceService.activerRessource(ressourceSquare.getId(), false);
//            }
//        }
    }
//
//    /**
//     * Mappe un utilisateur des habilitations en une ressource de Square.
//     * @param utilisateurDto l'utilisateur à mapper.
//     * @return la ressource.
//     */
//    private RessourceDto mapperUtilisateur(UtilisateurDto utilisateurDto) {
//        final RessourceDto ressourceDto = mapperDozerBean.map(utilisateurDto, RessourceDto.class);
//        Long idEtat;
//        if (utilisateurDto.getActif() != null && utilisateurDto.getValide() != null && utilisateurDto.getActif() && utilisateurDto.getValide()
//                && (utilisateurDto.getDateFinValidite() == null || utilisateurDto.getDateFinValidite().after(Calendar.getInstance()))
//                && utilisateurService.hasRoleByNom(utilisateurDto.getId(), utilisateurMappingService.getPrefixeRoleSquare())) {
//            idEtat = squareMappingService.getIdEtatActifRessource();
//        }
//        else {
//            idEtat = squareMappingService.getIdEtatInactifRessource();
//        }
//        final DimensionCriteresRechercheDto critereEtat = new DimensionCriteresRechercheDto();
//        critereEtat.setId(idEtat);
//        final List<IdentifiantLibelleDto> listeEtats = dimensionService.rechercherRessourceEtatParCriteres(critereEtat);
//        if (listeEtats != null && listeEtats.size() == 1) {
//            ressourceDto.setEtat(listeEtats.get(0));
//        }
//        if (utilisateurDto.getAgence() != null && utilisateurDto.getAgence().getIdentifiant() != null) {
//            if (logger.isDebugEnabled()) {
//	        	logger.info("Agence ID ==> " + ressourceDto.getAgence().getIdentifiant());
//	        	logger.info("Agence LIBELLE ==> " + ressourceDto.getAgence().getLibelle());
//            }
//            final IdentifiantEidLibelleDto agenceDto = dimensionService.rechercherAgenceParEid(utilisateurDto.getAgence().getIdentifiant().toString());
//            ressourceDto.setAgence(agenceDto);
//        } else {
//            if (logger.isDebugEnabled()) {
//            	logger.info("Agence NULL ==> " + (ressourceDto.getAgence() == null));
//            }
//        }
//        if (utilisateurDto.getService() != null && utilisateurDto.getService().getIdentifiant() != null) {
//            final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
//            criteres.setIdentifiantExterieur(utilisateurDto.getService().getIdentifiant().toString());
//            final List<IdentifiantLibelleDto> listeServiceDto = dimensionService.rechercherRessourceServiceParCriteres(criteres);
//            if (listeServiceDto != null && listeServiceDto.size() == 1) {
//                ressourceDto.setService(listeServiceDto.get(0));
//            }
//        }
//        if (utilisateurDto.getFonction() != null && utilisateurDto.getFonction().getIdentifiant() != null) {
//            final DimensionCritereRechercheRessourceFonctionDto criteres = new DimensionCritereRechercheRessourceFonctionDto();
//            criteres.setIdentifiantExterieur(utilisateurDto.getFonction().getIdentifiant().toString());
//            final List<IdentifiantLibelleDto> listeFonctions = dimensionService.rechercherRessourceFonctionParCriteres(criteres);
//            if (listeFonctions != null && listeFonctions.size() == 1) {
//                ressourceDto.setFonction(listeFonctions.get(0));
//            }
//        }
//        return ressourceDto;
//    }

    /**
     * Crée ou met à jour une agence.
     * @param idAgence l'identifiant de l'agence.
     */
    private void processCreateOrUpdateAgence(String idAgence) {
        final AgenceReelleDto agenceReelleDto = agenceService.getAgenceReelleById(Long.valueOf(idAgence));
        final AgenceDto agenceDto = agenceSquareService.rechercherAgenceParEid(idAgence);
        IdentifiantEidLibelleDto regionDto = null;
        if (agenceReelleDto.getReseau() != null) {
            regionDto = agenceSquareService.rechercherRegionCommercialeParEid(agenceReelleDto.getReseau().getIdentifiant().toString());
        }
        if (agenceDto == null) {
            // Création
            final AgenceDto agenceACreer = mapperDozerBean.map(agenceReelleDto, AgenceDto.class);
            agenceACreer.setIdExt(agenceReelleDto.getId().toString());
            agenceACreer.setRegion(regionDto);
            agenceSquareService.creerAgence(agenceACreer);
        }
        else {
            // Modification
            mapperDozerBean.map(agenceReelleDto, agenceDto);
            agenceDto.setRegion(regionDto);
            agenceSquareService.modifierAgence(agenceDto);
        }
    }

    /**
     * Supprime une agence.
     * @param idAgence l'identifiant de l'agence.
     */
    private void processDelelteAgence(String idAgence) {
        // Suppression logique
        agenceSquareService.supprimerAgence(idAgence);
    }

    /**
     * Fixer la valeur.
     * @param synchroAppTableCrspDao the synchroAppTableCrspDao to set
     */
    public void setSynchroAppTableCrspDao(SynchroAppTableCrspDao synchroAppTableCrspDao) {
        this.synchroAppTableCrspDao = synchroAppTableCrspDao;
    }

    /**
     * Fixer la valeur.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Fixer la valeur.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définition de actionServiceSquare.
     * @param actionServiceSquare the actionServiceSquare to set
     */
    public void setActionServiceSquare(ActionService actionServiceSquare) {
        this.actionServiceSquare = actionServiceSquare;
    }

    /**
     * Définition de jmsSmatisDmqSender.
     * @param jmsSmatisDmqSender the jmsSmatisDmqSender to set
     */
    public void setJmsSmatisDmqSender(JmsTemplate jmsSmatisDmqSender) {
        this.jmsSmatisDmqSender = jmsSmatisDmqSender;
    }

    /**
     * Set the param value.
     * @param ressourceService the ressourceService to set
     */
    public void setRessourceService(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    /**
     * Définit la valeur de emailService.
     * @param emailService la nouvelle valeur de emailService
     */
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Définit la valeur de emailMappingService.
     * @param emailMappingService la nouvelle valeur de emailMappingService
     */
    public void setEmailMappingService(EmailMappingService emailMappingService) {
        this.emailMappingService = emailMappingService;
    }

    /**
     * Définit la valeur de lienConsultationMail.
     * @param lienConsultationMail la nouvelle valeur de lienConsultationMail
     */
    public void setLienConsultationMail(String lienConsultationMail) {
        this.lienConsultationMail = lienConsultationMail;
    }

    /**
     * Récupère la valeur de lienConsultationMail.
     * @return la valeur de lienConsultationMail
     */
    public String getLienConsultationMail() {
        return lienConsultationMail;
    }

    /**
     * Définit la valeur de utilisateurService.
     * @param utilisateurService la nouvelle valeur de utilisateurService
     */
    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de dimensionService.
     * @param dimensionService la nouvelle valeur de dimensionService
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * Définit la valeur de agenceService.
     * @param agenceService la nouvelle valeur de agenceService
     */
    public void setAgenceService(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    /**
     * Définit la valeur de messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Définit la valeur de utilisateurMappingService.
     * @param utilisateurMappingService la nouvelle valeur de utilisateurMappingService
     */
    public void setUtilisateurMappingService(UtilisateurMappingService utilisateurMappingService) {
        this.utilisateurMappingService = utilisateurMappingService;
    }

    /**
     * Définit la valeur de agenceSquareService.
     * @param agenceSquareService la nouvelle valeur de agenceSquareService
     */
    public void setAgenceSquareService(com.square.core.service.interfaces.AgenceService agenceSquareService) {
        this.agenceSquareService = agenceSquareService;
    }
}
