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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.square.core.model.dto.ActionCreationDto;
import com.square.core.model.dto.ActionDto;
import com.square.core.model.dto.ActionSyntheseDto;
import com.square.core.model.dto.CritereActionSyntheseDto;
import com.square.core.model.dto.HistoriqueCommentaireDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RessourceDto;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.RessourceService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.document.core.service.interfaces.GedMappingService;
import com.square.document.core.service.interfaces.GedService;
import com.square.print.core.service.interfaces.EditiqueMappingService;
import com.square.synchro.app.noyau.bean.InfosPersonneSquareBean;
import com.square.synchro.app.noyau.dto.DefaultMessageSynchroAppDto;
import com.square.synchro.app.noyau.dto.SquareGedFusionDocumentSynchroDto;
import com.square.synchro.app.noyau.dto.SquareGedImpMailDevisSynchroDto;
import com.square.synchro.app.noyau.util.MessageKeyUtil;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheDevisDto;
import com.square.tarificateur.noyau.dto.devis.DevisDto;
import com.square.tarificateur.noyau.dto.editique.EditionDocumentDto;
import com.square.tarificateur.noyau.dto.editique.FichierModeleDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurEditiqueService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurService;

/**
 * Mdp de la GED.
 * @author Clément Lavaud - SCUB
 */
public class SynchroAppSquareGedMdp implements MessageListener {

    /** File DMQ. */
    private JmsTemplate jmsSmatisDmqSender;

    /** Adresse du lien de consultation d'un mail. */
    private String lienConsultationMail;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Service du tarificateur editique. */
    private TarificateurEditiqueService tarificateurEditiqueService;

    /** Service du tarificateur. */
    private TarificateurService tarificateurService;

    /** Service des ressources. */
    private RessourceService ressourceService;

    /** Service des ressources. */
    private ActionService actionServiceSquare;
//
//    /** Service des utilisateurs. */
//    private UtilisateurService utilisateurService;

    /** Service des personnes physiques. */
    private PersonnePhysiqueService personnePhysiqueServiceSquare;

    /** SquareMappingService. */
    private SquareMappingService squareMappingService;

    /** EditiqueMappingService. */
    @SuppressWarnings("unused")
	private EditiqueMappingService editiqueMappingService;

    /** GedMappingService. */
    @SuppressWarnings("unused")
	private GedMappingService gedMappingService;

    /** Gestion des messages. */
    private MessageSourceUtil messageSourceUtil;

    /** Service GED. */
    private GedService gedService;

    /** Logger. */
    private Logger logger = RootLogger.getLogger(SynchroAppSquareGedMdp.class);

    @Override
    public void onMessage(final Message message) {
        try {
            logger.debug(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MESSAGE_TRAITEMENT_ENCOUR,
             		 new String[] {String.valueOf(message.getJMSMessageID())})));
            if (!message.getJMSRedelivered()) {
                final DefaultMessageSynchroAppDto messageSynchro = (DefaultMessageSynchroAppDto) (((ObjectMessage) message).getObject());
                logger.debug(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECEPTION_MESSAGE,
                		 new String[] {messageSynchro.getMsgSynchroOrigineHeader(), String.valueOf(messageSynchro.getMsgSynchroIdObjet())})));
                if (messageSynchro.getMsgSynchroOrigineHeader().equals(DefaultMessageSynchroAppDto.MSOH_SQUARE_GED_IMPMAIL_DEVIS)) {
                    processImpMailDevis((SquareGedImpMailDevisSynchroDto) (((ObjectMessage) message).getObject()));
                }
                else if (messageSynchro.getMsgSynchroOrigineHeader().equals(DefaultMessageSynchroAppDto.MSOH_SQUARE_GED_FUSION_DOCUMENT)) {
                    processFusionDocuments((SquareGedFusionDocumentSynchroDto) (((ObjectMessage) message).getObject()));
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
     * Transfert des documents d'une personne à une autre.
     * @param fusionDocumentsDto les infos de la fusion de documents
     */
    private void processFusionDocuments(SquareGedFusionDocumentSynchroDto fusionDocumentsDto) {
        // Transfert des documents
        gedService.transfererDocumentPersonne(fusionDocumentsDto.getNumClientPersonneSource(), fusionDocumentsDto.getNumClientPersonneCible(),
            fusionDocumentsDto.getLoginUtilisateur());
    }

    /**
     * Crée une action et un document associé à cet action.
     * @param impMailDto infos sur le devis et l'édition.
     */
    private void processImpMailDevis(SquareGedImpMailDevisSynchroDto impMailDto) {
        final EditionDocumentDto editionDocumentDto = mapperDozerBean.map(impMailDto, EditionDocumentDto.class);

        // Création du cache des infos de personnes Square
        final InfosPersonneSquareBean infosPersonnesSquare = new InfosPersonneSquareBean();

        final RessourceDto ressourceCreateur = getRessourceByEid(editionDocumentDto.getEidCreateur());
        // Récupération du devis
        final CriteresRechercheDevisDto criteres = new CriteresRechercheDevisDto();
        criteres.setIdDevis(impMailDto.getIdentifiantDevis());
        criteres.setOrigineSiteWeb(null);
        final List<DevisDto> listeDevis = tarificateurService.getListeDevisByCriteres(criteres);
        if (listeDevis == null || listeDevis.size() != 1) {
            logger.error(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_NOMBRE_DEVIS_RECUPERER_INCORRECT,
           		 new String[] {String.valueOf(impMailDto.getIdentifiantDevis()), String.valueOf(listeDevis.size())})));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_NOMBRE_DEVIS_DIFFERENT_DE_UN));
        }
        final DevisDto devis = listeDevis.get(0);
        // Génération des documents
        final List<FichierModeleDto> fichiersModelesGeneres = tarificateurEditiqueService.genererDocumentsModelePdf(editionDocumentDto);
        // Création de l'action
        final ActionCreationDto actionCreee = creerActionPourEditionOuEnvoiMail(devis, ressourceCreateur, impMailDto.getImpression());
        // Rattachement des documents à l'action
        if (editionDocumentDto.getEidCreateur() != null) {
            // Récupération des infos de la personne ciblée
            final PersonneSimpleDto personneSquare = getPersonneSimple(devis.getPersonnePrincipale().getEidPersonne(), infosPersonnesSquare);
            associerDocumentsAAction(fichiersModelesGeneres, actionCreee.getIdentifiant(), personneSquare.getNumeroClient(), ressourceCreateur);
        }
    }

    /**
     * Attache une liste de fichiers à une action.
     * @param listeFichiers la liste des fichiers
     * @param idAction l'identifiant de l'action
     */
    private void associerDocumentsAAction(List<FichierModeleDto> listeFichiers, Long idAction, String numeroClient, RessourceDto ressource) {
//        logger.debug("Association de fichiers à l'action " + idAction);
//        if (listeFichiers != null) {
//            // Récupération de l'utilisateur
//            final UtilisateurDto utilisateur = utilisateurService.getUtilisateurById(Long.valueOf(ressource.getIdExt()));
//            if (utilisateur == null) {
//                logger.error("Erreur lors de l'attachement de documents à une action : utilisateur inexistant : " + ressource.getIdExt());
//                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_ATTACHEMENT_DOCUMENT_UTILISATEUR_INEXISTANT, new String[] {ressource
//                        .getIdExt()}));
//            }
//            try {
//                // Flag indiquant une erreur d'association
//                boolean erreurAssociationDocument = false;
//                // Récupération de constantes
//                final String nodeRefCategorieBulletinAdhesion = gedMappingService.getNodeRefCategorieDocumentsSmatisBulletinAdhesion();
//                final String nodeRefCategorieAvenant = gedMappingService.getNodeRefCategorieDocumentsSmatisAvenant();
//                final String nodeRefCategorieLettreAnnulation = gedMappingService.getNodeRefCategorieDocumentsSmatisLettreAnnulation();
//                final Long idModeleDevisFicheTransfert = editiqueMappingService.getConstanteIdModeleDevisFicheTransfert();
//                final Long idModeleLettreAnnulation = editiqueMappingService.getConstanteIdModeleLettreAnnulation();
//                final List<com.square.core.model.dto.DocumentDto> listeDocumentsSquare = new ArrayList<com.square.core.model.dto.DocumentDto>();
//                for (FichierModeleDto fichierModele : listeFichiers) {
//                    if (fichierModele.getFichierDto() != null) {
//                        final FichierDto fichierDto = fichierModele.getFichierDto();
//                        // Création du document pour la GED
//                        final DocumentDto document = new DocumentDto();
//                        document.setDateNumerisation(Calendar.getInstance());
//                        document.setNom(fichierDto.getNom());
//                        document.setContenu(fichierDto.getData());
//                        document.setNumeroClient(numeroClient);
//                        document.setSens(gedMappingService.getSensSortant());
//                        // Définition des catégories
//                        final List<CodeLibelleDto> listeTypes = new ArrayList<CodeLibelleDto>();
//                        final CodeLibelleDto type = new CodeLibelleDto();
//                        if (idModeleLettreAnnulation.equals(fichierModele.getIdModeleFichier())) {
//                            // Document Smatis / lettre annulation vpc
//                            type.setCode(nodeRefCategorieLettreAnnulation);
//                        }
//                        else if (idModeleDevisFicheTransfert.equals(fichierModele.getIdModeleFichier())) {
//                            // Document Smatis / Avenant
//                            type.setCode(nodeRefCategorieAvenant);
//                        }
//                        else {
//                            // Document Smatis / Bulletin d'adhésion par défaut
//                            type.setCode(nodeRefCategorieBulletinAdhesion);
//                        }
//                        listeTypes.add(type);
//                        document.setTypes(listeTypes);
//                        // Ajout du document dans la GED
//                        final RetourAjoutDocumentDto retourAjoutDocumentDto = gedService.ajouterDocument(document, utilisateur.getLogin());
//                        // Si l'identifiant du document n'est pas renseigné : notification
//                        if (StringUtils.isBlank(retourAjoutDocumentDto.getIdentifiantDocument())) {
//                            logger.error("L'identifiant du document ajouté n'est pas renseigné");
//                            erreurAssociationDocument = true;
//                        }
//                        // Création du document square
//                        final com.square.core.model.dto.DocumentDto documentSquare = new com.square.core.model.dto.DocumentDto();
//                        documentSquare.setIdentifiantExterieur(retourAjoutDocumentDto.getIdentifiantDocument());
//                        listeDocumentsSquare.add(documentSquare);
//                    }
//                }
//                // Rattachement des documents à l'action
//                if (!listeDocumentsSquare.isEmpty()) {
//                    actionServiceSquare.attacherDocuments(idAction, listeDocumentsSquare);
//                }
//                // Notification s'il y a eu une erreur
//                if (erreurAssociationDocument) {
//                    notifierErreurAssociationDocument(idAction);
//                }
//            }
//            catch (Exception e) {
//                // S'il y a une exception, on ne bloque pas l'édition des documents
//                logger.error("Erreur lors du rattachement des documents à l'action : ", e);
//                // On ajoute un commentaire à l'action créée pour indiquer que les documents n'ont pas été joints
//                notifierErreurAssociationDocument(idAction);
//            }
//        }
    }

    /**
     * Crée l'action pour l'édition ou l'envoi de mail.
     * @param editionDocumentDto le DTO contenant les infos d'édition
     * @param devis le devis
     * @param pourImpression flag indiquant que c'est une demande d'impression (sinon envoi par mail)
     * @return l'action créée
     */
    private ActionCreationDto creerActionPourEditionOuEnvoiMail(DevisDto devis, RessourceDto ressourceCreateur, boolean pourImpression) {
        if (pourImpression) {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CREATION_ACTION_EDITION_DOC));
        }
        else {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CREATION_ACTION_ENVOIE_DOC));
        }

        // L'agence responsable est celle du créateur
        Long eidAgenceSquare = null;
        if (ressourceCreateur.getAgence() != null && ressourceCreateur.getAgence().getIdentifiant() != null) {
            eidAgenceSquare = ressourceCreateur.getAgence().getIdentifiant();
        }

        // Récupération de l'action source de la dernière action liée à l'opportunité
        Long idActionSource = null;
        final CritereActionSyntheseDto criteresActions = new CritereActionSyntheseDto();
        criteresActions.setIdPersonne(devis.getPersonnePrincipale().getEidPersonne());
        criteresActions.setIdOpportunite(devis.getEidOpportunite());
        final List<Stack<ActionSyntheseDto>> listeActionsOpportunite = actionServiceSquare.recupererActionsSynthese(criteresActions);
        if (listeActionsOpportunite != null && !listeActionsOpportunite.isEmpty() && listeActionsOpportunite.get(0) != null
            && listeActionsOpportunite.get(0).peek() != null) {
            idActionSource = listeActionsOpportunite.get(0).peek().getId();
        }
        else {
            logger.warn(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_WARN_AUCUNE_ACTION_OPPORTUNITE,
              		 new String[] {String.valueOf(devis.getEidOpportunite())})));
        }

        // Création de l'action
        final ActionCreationDto action = new ActionCreationDto();
        action.setDateAction(Calendar.getInstance());
        action.setIdPersonne(devis.getPersonnePrincipale().getEidPersonne());
        action.setIdOpportunite(devis.getEidOpportunite());
        action.setIdActionSource(idActionSource);
        // L'action est affectée à l'utilisateur connecté (créateur, responsable et agence)
        action.setIdCommercial(ressourceCreateur.getId());
        action.setIdAgence(eidAgenceSquare);
        action.setIdCreateur(ressourceCreateur.getId());
        action.setIdPriorite(squareMappingService.getIdActionPrioriteParDefaut());
        action.setIdType(squareMappingService.getIdTypeActionOpportunite());
        action.setIdObjet(squareMappingService.getIdObjetActionEditique());
        // Topologie identique quelque soit le modèle
        if (pourImpression) {
            action.setIdNatureAction(squareMappingService.getIdNatureActionCourrierSortant());
            action.setIdSousObjet(squareMappingService.getIdSousObjetActionEditionBa());
        }
        else {
            action.setIdNatureAction(squareMappingService.getIdNatureActionEmailSortant());
            action.setIdSousObjet(squareMappingService.getIdSousObjetActionEnvoiBaParMail());
        }
        action.setIdStatut(squareMappingService.getIdStatutActionTermine());
        // Création de l'action
        return actionServiceSquare.creerAction(action);
    }

    /**
     * Notifie une erreur d'ajout de document dans la GED. <br/>
     * Ajoute un commentaire à l'action associée
     * @param idAction identifiant Square de l'action
     */
    @SuppressWarnings("unused")
	private void notifierErreurAssociationDocument(Long idAction) {
        logger.debug(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_ERREUR_AJOUT_DOCUMENT_ACTION,
         		 new String[] {String.valueOf(idAction)})));
        // Récupération de l'action
        final ActionDto actionDto = actionServiceSquare.rechercherActionParIdentifiant(idAction);
        if (actionDto == null) {
            logger.error(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_ACTION_INEXISTANTE,
           		 new String[] {String.valueOf(idAction)})));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_NOTIFICATION_ERREUR_GED_ACTION_INEXISTANTE));
        }
        // Ajout d'un commentaire à l'action
        final HistoriqueCommentaireDto historiqueCommentaireDto = new HistoriqueCommentaireDto();
        historiqueCommentaireDto.setDescriptif(messageSourceUtil.get(MessageKeyUtil.COMMENTAIRE_ACTION_ERREUR_ASSOCIATION_DOCUMENTS_GED));
        if (actionDto.getCommentaires() == null) {
            actionDto.setCommentaires(new ArrayList<HistoriqueCommentaireDto>());
        }
        actionDto.getCommentaires().add(historiqueCommentaireDto);
        actionServiceSquare.modifierAction(actionDto);
    }

    /**
     * Récupère la ressource dans Square à partir de l'identifiant externe.
     * @param eidRessource identifiant externe de la ressource
     * @return la ressource Square
     */
    private RessourceDto getRessourceByEid(Long eidCreateur) {
        // Récupération de la ressource du créateur
        if (eidCreateur == null) {
            logger.error(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_IDENTIFIANT_RESSOURCE_NULL)));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_EID_CREATEUR_ACTION_EDITION_NON_RENSEIGNE));
        }
        final RessourceDto ressourceCreateur = ressourceService.rechercherRessourceParEid(eidCreateur.toString());
        if (ressourceCreateur == null) {
            logger.error(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_RESSOURCE_CREATEUR_NULL,
              		 new String[] {String.valueOf(eidCreateur)})));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_RESSOURCE_CREATEUR_ACTION_EDITION_INEXISTANTE));
        }
        return ressourceCreateur;
    }

    /**
     * Récupère une personne Square par son identifiant en recherchant dans le cache ou en la récupérant par le service.
     * @param eidPersonne l'identifiant Square de la personne
     * @param infosPersonnes le cache des infos de personnes
     * @return le DTO de la personne Square
     */
    public PersonneSimpleDto getPersonneSimple(Long eidPersonne, InfosPersonneSquareBean infosPersonnes) {
        // Recherche dans le cache
        PersonneSimpleDto personneSimpleDto = infosPersonnes.getMapPersonnesSimples().get(eidPersonne);
        if (personneSimpleDto == null) {
            // Recherche par le service
            personneSimpleDto = personnePhysiqueServiceSquare.rechercherPersonneSimpleParIdentifiant(eidPersonne);
            // Ajout dans le cache
            infosPersonnes.getMapPersonnesSimples().put(eidPersonne, personneSimpleDto);
        }
        return personneSimpleDto;
    }

    /**
     * Définition de jmsSmatisDmqSender.
     * @param jmsSmatisDmqSender the jmsSmatisDmqSender to set
     */
    public void setJmsSmatisDmqSender(JmsTemplate jmsSmatisDmqSender) {
        this.jmsSmatisDmqSender = jmsSmatisDmqSender;
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
     * Setter function.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Définit la valeur de tarificateurEditiqueService.
     * @param tarificateurEditiqueService la nouvelle valeur de tarificateurEditiqueService
     */
    public void setTarificateurEditiqueService(TarificateurEditiqueService tarificateurEditiqueService) {
        this.tarificateurEditiqueService = tarificateurEditiqueService;
    }

    /**
     * Définit la valeur de tarificateurService.
     * @param tarificateurService la nouvelle valeur de tarificateurService
     */
    public void setTarificateurService(TarificateurService tarificateurService) {
        this.tarificateurService = tarificateurService;
    }

    /**
     * Définit la valeur de ressourceService.
     * @param ressourceService la nouvelle valeur de ressourceService
     */
    public void setRessourceService(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

//    /**
//     * Définit la valeur de utilisateurService.
//     * @param utilisateurService la nouvelle valeur de utilisateurService
//     */
//    public void setUtilisateurService(UtilisateurService utilisateurService) {
//        this.utilisateurService = utilisateurService;
//    }

    /**
     * Définit la valeur de gedMappingService.
     * @param gedMappingService la nouvelle valeur de gedMappingService
     */
    public void setGedMappingService(GedMappingService gedMappingService) {
        this.gedMappingService = gedMappingService;
    }

//    /**
//     * Définit la valeur de editiqueMappingService.
//     * @param editiqueMappingService la nouvelle valeur de editiqueMappingService
//     */
//    public void setEditiqueMappingService(EditiqueMappingService editiqueMappingService) {
//        this.editiqueMappingService = editiqueMappingService;
//    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Set the gedService value.
     * @param gedService the gedService to set
     */
    public void setGedService(GedService gedService) {
        this.gedService = gedService;
    }

    /**
     * Set the value of actionServiceSquare.
     * @param actionServiceSquare the actionServiceSquare to set
     */
    public void setActionServiceSquare(ActionService actionServiceSquare) {
        this.actionServiceSquare = actionServiceSquare;
    }

    /**
     * Set the value of personnePhysiqueServiceSquare.
     * @param personnePhysiqueServiceSquare the personnePhysiqueServiceSquare to set
     */
    public void setPersonnePhysiqueServiceSquare(PersonnePhysiqueService personnePhysiqueServiceSquare) {
        this.personnePhysiqueServiceSquare = personnePhysiqueServiceSquare;
    }
}
