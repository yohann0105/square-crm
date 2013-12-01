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
package com.square.tarificateur.noyau.service.implementations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.model.dto.CaisseDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.OpportuniteSimpleDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RelationDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.mail.core.dto.emails.EmailAvecModeleDto;
import com.square.mail.core.dto.emails.InfosModeleEmailDto;
import com.square.mail.core.dto.emails.MailDto;
import com.square.mail.core.dto.emails.PieceJointeFileDto;
import com.square.mail.core.service.interfaces.email.EnvoiEmailMappingService;
import com.square.mail.core.service.interfaces.email.MailService;
import com.square.price.core.dto.CritereCriteresDto;
import com.square.price.core.dto.CritereDto;
import com.square.price.core.dto.CriteresImageGammeDto;
import com.square.price.core.dto.CriteresImageProduitDto;
import com.square.price.core.dto.GammeProduitCriteresDto;
import com.square.price.core.dto.GammeProduitDto;
import com.square.price.core.dto.ModePaiementDto;
import com.square.price.core.dto.ProduitCriteresDto;
import com.square.price.core.dto.ProduitDto;
import com.square.price.core.dto.ProduitLieCriteresDto;
import com.square.price.core.dto.ProduitLieDto;
import com.square.price.core.service.interfaces.ProduitService;
import com.square.price.core.service.interfaces.RegleMappingService;
import com.square.price.core.service.interfaces.TarificateurMappingService;
import com.square.print.core.dto.AdresseDto;
import com.square.print.core.dto.AgenceEditiqueDto;
import com.square.print.core.dto.ModeleDevisDto;
import com.square.print.core.dto.ProspectDevisDto;
import com.square.print.core.dto.adhesion.BeneficiaireBADto;
import com.square.print.core.dto.adhesion.BeneficiaireMontantBADto;
import com.square.print.core.dto.adhesion.BulletinAdhesionDto;
import com.square.print.core.dto.adhesion.CritereProduitBADto;
import com.square.print.core.dto.adhesion.DocumentsBulletinsAdhesionDto;
import com.square.print.core.dto.adhesion.InfosAdhesionInternetDto;
import com.square.print.core.dto.adhesion.InfosReglementDto;
import com.square.print.core.dto.adhesion.InfosRibDto;
import com.square.print.core.dto.adhesion.LettreAnnulationDto;
import com.square.print.core.dto.adhesion.PropositionBADto;
import com.square.print.core.dto.adhesion.PropositionFamilleProduitsBADto;
import com.square.print.core.dto.adhesion.PropositionProduitBADto;
import com.square.print.core.dto.adhesion.ProspectBADto;
import com.square.print.core.service.interfaces.EditiqueMappingService;
import com.square.print.core.service.interfaces.EditiqueService;
import com.square.synchro.app.noyau.dto.DefaultMessageSynchroAppDto;
import com.square.synchro.app.noyau.dto.SquareGedImpMailDevisSynchroDto;
import com.square.synchro.app.noyau.services.interfaces.SynchroAppJmsSender;
import com.square.tarificateur.noyau.bean.editique.FichierModeleBean;
import com.square.tarificateur.noyau.bean.opportunite.InfosOpportuniteSquareBean;
import com.square.tarificateur.noyau.bean.personne.InfosPersonneSquareBean;
import com.square.tarificateur.noyau.dao.interfaces.BeneficiaireDao;
import com.square.tarificateur.noyau.dao.interfaces.DevisDao;
import com.square.tarificateur.noyau.dao.interfaces.LigneDevisDao;
import com.square.tarificateur.noyau.dto.editique.EditionDocumentDto;
import com.square.tarificateur.noyau.dto.editique.FichierModeleDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.model.opportunite.Adhesion;
import com.square.tarificateur.noyau.model.opportunite.Devis;
import com.square.tarificateur.noyau.model.opportunite.LigneDevis;
import com.square.tarificateur.noyau.model.opportunite.LigneDevisLiee;
import com.square.tarificateur.noyau.model.opportunite.RegleValeur;
import com.square.tarificateur.noyau.model.opportunite.ValeurCritereLigneDevis;
import com.square.tarificateur.noyau.model.personne.Beneficiaire;
import com.square.tarificateur.noyau.model.personne.Personne;
import com.square.tarificateur.noyau.service.interfaces.TarificateurEditiqueService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurSquareMappingService;
import com.square.tarificateur.noyau.util.MessageKeyUtil;
import com.square.tarificateur.noyau.util.devis.DevisUtil;
import com.square.tarificateur.noyau.util.opportunite.OpportuniteUtil;
import com.square.tarificateur.noyau.util.personne.PersonneUtil;
import com.square.user.core.dto.AgenceReelleDto;
import com.square.user.core.service.interfaces.AgenceService;
import com.square.user.core.service.interfaces.UtilisateurMappingService;

/**
 * Implémentation de TarificateurEditiqueService.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class TarificateurEditiqueServiceImpl implements TarificateurEditiqueService {

    /** Logger. */
    private Logger logger = Logger.getLogger(this.getClass());

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    /** Dao Beneficiaire. */
    private BeneficiaireDao beneficiaireDao;

    /** DAO Devis. */
    private DevisDao devisDao;

    /** DAO LigneDevis. */
    private LigneDevisDao ligneDevisDao;

    /** Service de mapping. */
    private TarificateurSquareMappingService tarificateurSquareMappingService;

    /** Service Produit. */
    private ProduitService produitService;

    /** Service de mapping du tarificateur. */
    private TarificateurMappingService tarificateurMappingService;

    /** Service regleMapping. */
    private RegleMappingService regleMappingService;

    private EditiqueMappingService editiqueMappingService;

    private EditiqueService editiqueService;

    private UtilisateurMappingService utilisateurMappingService;

    private AgenceService agenceService;

    private DimensionService dimensionService;

    private MailService mailService;

    private EnvoiEmailMappingService envoiEmailMappingService;

    @SuppressWarnings("unused")
	private DevisUtil devisUtil;

    private SquareMappingService squareMappingService;

    private PersonneService personneService;

    private PersonneUtil personneUtil;

    private OpportuniteUtil opportuniteUtil;

    private TarificateurPersonneService tarificateurPersonneService;

    /** Service pour poster dans la file JMS. */
    private SynchroAppJmsSender synchroAppJmsSender;

    /**
     * {@inheritDoc}
     */
    @Override
    public void envoyerParMailDocumentPdf(EditionDocumentDto editionDocumentDto) {
        // Création du cache des infos de personnes Square
        final InfosPersonneSquareBean infosPersonnesSquare = new InfosPersonneSquareBean();
        // Création du cache des infos d'opportunités Square
        final InfosOpportuniteSquareBean infosOpportunitesSquare = new InfosOpportuniteSquareBean();

        // Récupération du devis
        final Devis devis = devisDao.getDevis(editionDocumentDto.getIdentifiantDevis());

        // On génère les documents pdf à envoyer par email.
        final List<FichierModeleBean> fichiersModelesGeneres = genererDocumentsPdf(editionDocumentDto, infosPersonnesSquare, infosOpportunitesSquare, devis);

        final Personne personneCiblee = devis.getPersonnePrincipale();

        // Récupération des infos de la personne ciblée
        final PersonneSimpleDto personneSquare = personneUtil.getPersonneSimple(personneCiblee.getEidPersonne(), infosPersonnesSquare);
        // Récupération des coordonnées de la personne
        final CoordonneesDto coordonneesPersonneCiblee = personneUtil.getCoordonnees(personneCiblee.getEidPersonne(), infosPersonnesSquare);

        // Recherche du mail personnel
        final Long idNatureEmailPersonnel = squareMappingService.getIdNatureEmailPersonnel();
        final EmailDto emailPersonneCiblee = personneUtil.rechercherEmailParNature(coordonneesPersonneCiblee.getEmails(), idNatureEmailPersonnel);
        if (emailPersonneCiblee == null || StringUtils.isBlank(emailPersonneCiblee.getAdresse())) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_AUCUN_EMAIL_PERSONNE_CIBLEE));
        }

        final MailDto mailToProspectDto = new MailDto();
        final List<PieceJointeFileDto> listePiecesJointes =
            mapperDozerBean.mapList(getListeFichiersByListeFichiersModeles(fichiersModelesGeneres), PieceJointeFileDto.class);
        mailToProspectDto.setListePiecesJointes(listePiecesJointes);
        final InfosModeleEmailDto infosModele = new InfosModeleEmailDto();
        if (editionDocumentDto.getIdModeleEmail() != null) {
            infosModele.setIdModeleEmail(editionDocumentDto.getIdModeleEmail());
        }
        else {
            infosModele.setIdModeleEmail(envoiEmailMappingService.getIdModeleEnvoiDocumentsPieceJointe());
        }
        infosModele.setCiviliteDestinataire(personneSquare.getCivilite().getLibelle());
        infosModele.setNomDestinataire(personneSquare.getNom().toUpperCase());
        infosModele.setEmailDestinataire(emailPersonneCiblee.getAdresse());
        final EmailAvecModeleDto emailAvecModeleDto = new EmailAvecModeleDto();
        emailAvecModeleDto.setEmail(mailToProspectDto);
        emailAvecModeleDto.setInfosModele(infosModele);

        mailService.envoyerMailDepuisModele(emailAvecModeleDto);

        // Envoie du document vers la file d'attente
        final SquareGedImpMailDevisSynchroDto message = mapperDozerBean.map(editionDocumentDto, SquareGedImpMailDevisSynchroDto.class);
        message.setImpression(false);
        message.setMsgSynchroOrigineHeader(DefaultMessageSynchroAppDto.MSOH_SQUARE_GED_IMPMAIL_DEVIS);
        synchroAppJmsSender.envoyerMessageSynchro(message);
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_ENVOIE_DEVIS_NOUVELLE_ACTION_POSTER_FILE,
        		 new String[] {String.valueOf(editionDocumentDto.getIdentifiantDevis())}));
    }

    @Override
    public List<FichierDto> imprimerDocumentPdf(EditionDocumentDto editionDocumentDto) {
        // Création du cache des infos de personnes Square
        final InfosPersonneSquareBean infosPersonnesSquare = new InfosPersonneSquareBean();
        // Création du cache des infos d'opportunités Square
        final InfosOpportuniteSquareBean infosOpportunitesSquare = new InfosOpportuniteSquareBean();

        // Récupération du devis
        final Devis devis = devisDao.getDevis(editionDocumentDto.getIdentifiantDevis());
        // Génération des documents
        final List<FichierModeleBean> fichiersModelesGeneres = genererDocumentsPdf(editionDocumentDto, infosPersonnesSquare, infosOpportunitesSquare, devis);

        // Envoie du document vers la file d'attente
        final SquareGedImpMailDevisSynchroDto message = mapperDozerBean.map(editionDocumentDto, SquareGedImpMailDevisSynchroDto.class);
        message.setImpression(true);
        message.setMsgSynchroOrigineHeader(DefaultMessageSynchroAppDto.MSOH_SQUARE_GED_IMPMAIL_DEVIS);
        synchroAppJmsSender.envoyerMessageSynchro(message);
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_ENVOIE_DEVIS_NOUVELLE_ACTION_POSTER_FILE,
       		 new String[] {String.valueOf(editionDocumentDto.getIdentifiantDevis())}));
        return getListeFichiersByListeFichiersModeles(fichiersModelesGeneres);
    }

    @Override
    public List<FichierDto> genererDocumentsPdf(EditionDocumentDto editionDocumentDto) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MESSAGE_GENERATION_DOCUMENTS_PDF));
        // Création du cache des infos de personnes Square
        final InfosPersonneSquareBean infosPersonnesSquare = new InfosPersonneSquareBean();
        // Création du cache des infos d'opportunités Square
        final InfosOpportuniteSquareBean infosOpportunitesSquare = new InfosOpportuniteSquareBean();
        // Récupération du devis
        final Devis devis = devisDao.getDevis(editionDocumentDto.getIdentifiantDevis());
        // On génère les documents pdf.
        final List<FichierModeleBean> fichiersModelesGeneres = genererDocumentsPdf(editionDocumentDto, infosPersonnesSquare, infosOpportunitesSquare, devis);
        return getListeFichiersByListeFichiersModeles(fichiersModelesGeneres);
    }

    @Override
    public List<FichierModeleDto> genererDocumentsModelePdf(EditionDocumentDto editionDocumentDto) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MESSAGE_GENERATION_DOCUMENTS_PDF));
        // Création du cache des infos de personnes Square
        final InfosPersonneSquareBean infosPersonnesSquare = new InfosPersonneSquareBean();
        // Création du cache des infos d'opportunités Square
        final InfosOpportuniteSquareBean infosOpportunitesSquare = new InfosOpportuniteSquareBean();
        // Récupération du devis
        final Devis devis = devisDao.getDevis(editionDocumentDto.getIdentifiantDevis());
        // On génère les documents pdf.
        final List<FichierModeleBean> fichiersModelesGeneres = genererDocumentsPdf(editionDocumentDto, infosPersonnesSquare, infosOpportunitesSquare, devis);
        return mapperDozerBean.mapList(fichiersModelesGeneres, FichierModeleDto.class);
    }

    @Override
    public void notifierEnvoiDocumentsPdfParMail(EditionDocumentDto editionDocumentDto) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_NOTIFICATION_ENVOI_DOCUMENT_MAIL));

        // Envoie du document vers la file d'attente
        final SquareGedImpMailDevisSynchroDto message = mapperDozerBean.map(editionDocumentDto, SquareGedImpMailDevisSynchroDto.class);
        message.setImpression(false);
        message.setMsgSynchroOrigineHeader(DefaultMessageSynchroAppDto.MSOH_SQUARE_GED_IMPMAIL_DEVIS);
        synchroAppJmsSender.envoyerMessageSynchro(message);
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_ENVOIE_DEVIS_NOUVELLE_ACTION_POSTER_FILE));
    }

    /**
     * Génère la liste des documents PDF.
     * @param editionDocumentDto le DTO contenant les infos d'édition
     * @param infosPersonneSquare le cache des infos de personnes Square
     * @param infosOpportuniteSquare le cache des infos d'opportunités Square
     * @param devis le devis
     * @return la liste des fichiers générés
     */
    private List<FichierModeleBean> genererDocumentsPdf(EditionDocumentDto editionDocumentDto, InfosPersonneSquareBean infosPersonneSquare,
        InfosOpportuniteSquareBean infosOpportuniteSquare, Devis devis) {
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_DEMANDE_DEVIS_PDF,
        		 new String[] {String.valueOf(editionDocumentDto.getIdentifiantDevis())}));
        // Lignes sélectionnées à imprimer > 0
        if (editionDocumentDto.getIdsLigneDevisSelection() == null || editionDocumentDto.getIdsLigneDevisSelection().size() == 0) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_LIGNE_DEVIS_SELECTIONNEE_OBLIGATOIRE));
        }
        // On vérifie qu'au moins un modèle de devis a été spécifié
        final List<Long> idsModelesDevisSelectionnes = editionDocumentDto.getIdsModelesDevisSelectionnes();
        if (idsModelesDevisSelectionnes == null || idsModelesDevisSelectionnes.isEmpty()) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MODELE_DEVIS_SELECTIONNE_OBLIGATOIRE));
        }

        // Récupération de l'opportunité Square associé au devis
        final OpportuniteSimpleDto opportuniteSquare = opportuniteUtil.getOpportuniteSimple(devis.getOpportunite().getEidOpportunite(), infosOpportuniteSquare);

        // Récupération de l'agence de la personne du devis
        final com.square.core.model.dto.PersonneDto personneSquare =
            personneUtil.getPersonnePhysique(devis.getPersonnePrincipale().getEidPersonne(), infosPersonneSquare);
        final Long eidAgence = Long.valueOf(personneSquare.getAgence().getIdentifiantExterieur());
        if (eidAgence == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_ABSCENCE_AGENCE_PERSONNE_OPPORTUNITE,
            		 new String[] {String.valueOf(devis.getOpportunite().getEidOpportunite())}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_GENERATION_DOCUMENT_AGENCE_OBLIGATOIRE));
        }

        // Récupération des constantes
        final Long idModeleDevisBulletinAdhesion = editiqueMappingService.getConstanteIdModeleDevisBulletinAdhesion();
        final Long idModeleDevisFicheTransfert = editiqueMappingService.getConstanteIdModeleDevisFicheTransfert();
        final Long idModeleLetteAnnulation = editiqueMappingService.getConstanteIdModeleLettreAnnulation();
        final Long idModeleLettreRadiation = editiqueMappingService.getConstanteIdModeleLettreRadiation();
        final Long idModeleLettreRadiationLoiChatel = editiqueMappingService.getConstanteIdModeleLettreRadiationLoiChatel();
        final List<FichierModeleBean> fichiersGeneres = new ArrayList<FichierModeleBean>();
        for (Long idModeleDevis : idsModelesDevisSelectionnes) {
            if (idModeleDevis == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_MODELE_DEVIS_NON_RENSEIGNER));
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MODELE_DEVIS_INCOMPATIBLE));
            }
            else if (!(idModeleDevis.equals(idModeleDevisBulletinAdhesion) || idModeleDevis.equals(idModeleDevisFicheTransfert)
                    || idModeleDevis.equals(idModeleLetteAnnulation) || idModeleDevis.equals(idModeleLettreRadiation) || idModeleDevis
                        .equals(idModeleLettreRadiationLoiChatel))) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_MODELE_DEVIS_IS_NOT_BULLETIN_ADHESION_OU_FICHE_TRANSFERT));
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MODELE_DEVIS_INCOMPATIBLE));
            }

            if (idModeleDevis.equals(idModeleLetteAnnulation)) {
                // On génère la lettre d'annulation
                final LettreAnnulationDto lettreAnnulation = new LettreAnnulationDto();

                // Récupération des informations de l'agence responsable de l'opportunité.
                final AgenceReelleDto agenceResponsable = agenceService.getAgenceReelleById(eidAgence);
                // Création de l'agence
                final AgenceEditiqueDto agence = new AgenceEditiqueDto();
                agence.setDenomination(agenceResponsable.getDenomination());
                agence.setNumeroTelephone(utilisateurMappingService.getNumeroTelephoneUnique());
                agence.setAdresse((AdresseDto) mapperDozerBean.map(agenceResponsable, AdresseDto.class));
                lettreAnnulation.setAgence(agence);

                final Adhesion adhesion = devis.getOpportunite().getAdhesion();
                adhesion.setDateCourrier(Calendar.getInstance());

                // Récupération de la référence
                final String reference = opportuniteSquare.getEidOpportunite();
                lettreAnnulation.setNumeroDossier(reference);
                lettreAnnulation.setDateCourrier(adhesion.getDateCourrier());
                lettreAnnulation.setDateCreationAdhesion(adhesion.getDateSignature());

                // Création du prospect
                final Personne personneCiblee = devis.getPersonnePrincipale();
                // Récupération du Prospect Square
                final PersonneSimpleDto personneCibleeSquare = personneUtil.getPersonneSimple(personneCiblee.getEidPersonne(), infosPersonneSquare);
                final ProspectDevisDto prospect = new ProspectDevisDto();
                if (personneCibleeSquare.getCivilite() != null && !StringUtils.isBlank(personneCibleeSquare.getCivilite().getLibelle())) {
                    prospect.setGenre(personneCibleeSquare.getCivilite().getLibelle());
                }
                prospect.setNom(personneCibleeSquare.getNom().toUpperCase());
                final char[] delimiters = {' ', '-', '_'};
                final String prenomMisEnForme = WordUtils.capitalizeFully(personneCibleeSquare.getPrenom(), delimiters);
                prospect.setPrenom(prenomMisEnForme);
                // Recherche de l'adresse principale
                final CoordonneesDto coordonnees = personneUtil.getCoordonnees(personneCiblee.getEidPersonne(), infosPersonneSquare);
                final com.square.core.model.dto.AdresseDto adressePrincipale = personneUtil.rechercherAdressePrincipaleEnCours(coordonnees.getAdresses());
                prospect.setAdresse(mapperAdresse(adressePrincipale));
                lettreAnnulation.setProspect(prospect);
                final FichierDto fichierGenere = editiqueService.getLettreAnnulation(lettreAnnulation);
                fichierGenere.setNom(getNomFichierPdf(messageSourceUtil.get(MessageKeyUtil.NOM_FICHIER_LETTRE_ANNULATION)));
                fichiersGeneres.add(new FichierModeleBean(fichierGenere, idModeleDevis));
            }
            else if (idModeleDevis.equals(idModeleLettreRadiation) || idModeleDevis.equals(idModeleLettreRadiationLoiChatel)) {
                final FichierDto fichierGenere = editiqueService.getFichier(editiqueMappingService.getMapFichiersStatiques().get(idModeleDevis.toString()));
                if (idModeleDevis.equals(idModeleLettreRadiation)) {
                    fichierGenere.setNom(getNomFichierPdf(messageSourceUtil.get(MessageKeyUtil.NOM_FICHIER_LETTRE_RADIATION)));
                }
                else if (idModeleDevis.equals(idModeleLettreRadiationLoiChatel)) {
                    fichierGenere.setNom(getNomFichierPdf(messageSourceUtil.get(MessageKeyUtil.NOM_FICHIER_LETTRE_RADIATION_LOI_CHATEL)));
                }
                fichiersGeneres.add(new FichierModeleBean(fichierGenere, idModeleDevis));
            }
            else {
                // Type de devis Santé/Prévoyance
                // Récupération de la référence
                final String reference = opportuniteSquare.getEidOpportunite();
                // Création du DTO permettant la génération du bulletin d'adhésion
                final DocumentsBulletinsAdhesionDto documentsBulletinsAdhesionDto =
                    getDocumentsBaDtoPourDevis(devis, editionDocumentDto, reference, idModeleDevis, eidAgence, infosPersonneSquare);
                final FichierDto fichierGenere = editiqueService.genererPdfDocumentsBulletinsAdhesion(documentsBulletinsAdhesionDto);
                // Nom du fichier en fonction du modèle
                if (idModeleDevisBulletinAdhesion.equals(idModeleDevis)) {
                    fichierGenere.setNom(getNomFichierPdf(messageSourceUtil.get(MessageKeyUtil.NOM_FICHIER_BULLETIN_ADHESION)));
                }
                else if (idModeleDevisFicheTransfert.equals(idModeleDevis)) {
                    fichierGenere.setNom(getNomFichierPdf(messageSourceUtil.get(MessageKeyUtil.NOM_FICHIER_AVENANT)));
                }
                fichiersGeneres.add(new FichierModeleBean(fichierGenere, idModeleDevis));

                // On met à jour la date de dernière édition du BA si elle est renseignée
                if (editionDocumentDto.getDateEditionBa() != null) {
                    devis.getOpportunite().getAdhesion().setDateEditionBA(editionDocumentDto.getDateEditionBa());
                }
            }
        }
        return fichiersGeneres;
    }

    /**
     * Mappe une personne dans un DTO adapté au noyau éditique.
     * @param personne la personne à mapper.
     * @param infosPersonneSquare le cache des infos de personnes Square
     * @return La personne mappée dans un DTO adapté au noyau éditique.
     */
    private ProspectBADto mapperProspect(Personne personne, Long eidRelationParrain, InfosPersonneSquareBean infosPersonneSquare) {
        // Mapping de la personne
        final ProspectBADto prospect = mapperDozerBean.map(personne, ProspectBADto.class);
        // Récupération de la personne
        final PersonneSimpleDto personneSimpleSquare = personneUtil.getPersonneSimple(personne.getEidPersonne(), infosPersonneSquare);
        mapperDozerBean.map(personneSimpleSquare, prospect);

        prospect.setNom(prospect.getNom().toUpperCase());
        final char[] delimiters = {' ', '-', '_'};
        final String prenomMisEnForme = WordUtils.capitalizeFully(prospect.getPrenom().trim(), delimiters);
        prospect.setPrenom(prenomMisEnForme);

        // Récupération des coordonnées de la personne
        final CoordonneesDto coordonnees = personneUtil.getCoordonnees(personne.getEidPersonne(), infosPersonneSquare);

        // Récupération de l'adresse principale
        final com.square.core.model.dto.AdresseDto adressePrincipale = personneUtil.rechercherAdressePrincipaleEnCours(coordonnees.getAdresses());
        prospect.setAdresse(mapperAdresse(adressePrincipale));

        // Récupération du téléphone parmi fixe, portable et bureau
        final Long idNatureTelephoneFixe = squareMappingService.getIdNatureTelephoneFixe();
        final TelephoneDto telephoneFixe = personneUtil.rechercherTelephoneParNature(coordonnees.getTelephones(), idNatureTelephoneFixe);
        if (telephoneFixe != null && !StringUtils.isBlank(telephoneFixe.getNumero())) {
            prospect.setNumeroTelephone(telephoneFixe.getNumero());
        }
        else {
            final Long idNatureMobilePrive = squareMappingService.getIdNatureMobilePrive();
            final TelephoneDto telephonePortable = personneUtil.rechercherTelephoneParNature(coordonnees.getTelephones(), idNatureMobilePrive);
            if (telephonePortable != null && !StringUtils.isBlank(telephonePortable.getNumero())) {
                prospect.setNumeroTelephone(telephonePortable.getNumero());
            }
            else {
                final Long idNatureMobileTravail = squareMappingService.getIdNatureMobileTravail();
                final TelephoneDto telephoneBureau = personneUtil.rechercherTelephoneParNature(coordonnees.getTelephones(), idNatureMobileTravail);
                if (telephoneBureau != null && !StringUtils.isBlank(telephoneBureau.getNumero())) {
                    prospect.setNumeroTelephone(telephoneBureau.getNumero());
                }
            }
        }

        // Récupération du mail personnel
        final Long idNatureEmailPersonnel = squareMappingService.getIdNatureEmailPersonnel();
        final EmailDto email = personneUtil.rechercherEmailParNature(coordonnees.getEmails(), idNatureEmailPersonnel);
        if (email != null && !StringUtils.isBlank(email.getAdresse())) {
            prospect.setEmail(email.getAdresse());
        }

        // Récupération de la caisse
        if (personne.getInfoSante() != null && personne.getInfoSante().getEidCaisse() != null) {
            final CaisseDto caisse = dimensionService.rechercherCaisseParId(personne.getInfoSante().getEidCaisse());
            if (caisse == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_CAISSE_NULL,
                		 new String[] {String.valueOf(personne.getInfoSante().getEidCaisse())}));
            }
            else {
                prospect.setLibelleCaisse(caisse.getCode());
                prospect.setLibelleRegime(caisse.getRegime().getLibelle());
            }
        }

        // Récupération du numéro d'adherent du parrain
        if (eidRelationParrain != null) {
            final RelationDto relation = personneService.rechercherRelationParId(eidRelationParrain);
            // on verifie si le parrain vient du segment individuel
            final com.square.core.model.dto.PersonneDto parrain = personneUtil.getPersonnePhysique(relation.getIdPersonnePrincipale(), infosPersonneSquare);
            prospect.setNumeroAdherentParrain(parrain.getNumClient());
        }

        return prospect;
    }

    /**
     * Mappe une personne dans un DTO adapté pour le noyau éditique.
     * @param beneficiaire la personne bénéficiaire.
     * @param infosPersonneSquare le cache des infos de personnes Square
     * @return le DTO contenant les informations de la personne.
     */
    private BeneficiaireBADto mapperBeneficiaire(Personne beneficiaire, InfosPersonneSquareBean infosPersonneSquare) {
        final BeneficiaireBADto beneficiaireBA = mapperDozerBean.map(beneficiaire, BeneficiaireBADto.class);
        // Récupération de la personne
        final PersonneSimpleDto personneSquare = personneUtil.getPersonneSimple(beneficiaire.getEidPersonne(), infosPersonneSquare);
        mapperDozerBean.map(personneSquare, beneficiaireBA);
        beneficiaireBA.setNom(beneficiaireBA.getNom().toUpperCase());
        final char[] delimiters = {' ', '-', '_'};
        final String prenomMisEnForme = WordUtils.capitalizeFully(beneficiaireBA.getPrenom().trim(), delimiters);
        beneficiaireBA.setPrenom(prenomMisEnForme);
        if (beneficiaire.getInfoSante() != null && beneficiaire.getInfoSante().getEidCaisse() != null) {
            final CaisseDto caisse = dimensionService.rechercherCaisseParId(beneficiaire.getInfoSante().getEidCaisse());
            if (caisse == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_CAISSE_NULL,
               		 new String[] {String.valueOf(beneficiaire.getInfoSante().getEidCaisse())}));
            }
            else {
                beneficiaireBA.setLibelleCaisse(caisse.getCode());
                beneficiaireBA.setLibelleRegime(caisse.getRegime().getLibelle());
            }
        }
        return beneficiaireBA;
    }

    /**
     * Mappe une adresse dans un DTO adapté au noyau éditique.
     * @param l'adresse principale Square de la personne
     * @return L'adresse mappée dans un DTO adapté au noyau d'éditique.
     */
    private AdresseDto mapperAdresse(com.square.core.model.dto.AdresseDto adresseSquare) {
        final AdresseDto adresseDto = new AdresseDto();
        if (adresseSquare != null) {
            // Mapping
            mapperDozerBean.map(adresseSquare, adresseDto);

            // Code postal et Commune
            if (adresseSquare.getCodePostalCommune().getIdentifiant() != null) {
                // Récupération du code postal - commune à partir de Square
                final CodePostalCommuneDto codePostalCommune =
                    dimensionService.rechercherCodePostalCommuneParId(adresseSquare.getCodePostalCommune().getIdentifiant());
                if (codePostalCommune != null) {
                    // Code postal
                    if (codePostalCommune.getCodePostal() != null && !StringUtils.isBlank(codePostalCommune.getCodePostal().getLibelle())) {
                        adresseDto.setCodePostal(codePostalCommune.getCodePostal().getLibelle());
                    }
                    else {
                        adresseDto.setCodePostal(adresseSquare.getCodePostalEtranger());
                    }
                    // Commune (correspond au libellé d'acheminement)
                    if (!StringUtils.isBlank(codePostalCommune.getLibelleAcheminement())) {
                        adresseDto.setVille(codePostalCommune.getLibelleAcheminement());
                    }
                    else {
                        adresseDto.setVille(adresseSquare.getCommuneEtranger());
                    }
                }
            }
        }
        return adresseDto;
    }

    /**
     * Récupère les lignes de devis principales sélectionnées à partir du devis.
     * @param devis le devis
     * @param listeIdsLignesSelectionnnees la liste des identifiants des lignes de devis sélectionnées
     * @return la liste des lignes de devis sélectionnées
     */
    @SuppressWarnings("unused")
	private List<LigneDevis> getListeLignesDevisPrincipalesSelectionnees(Devis devis, List<Long> listeIdsLignesSelectionnnees) {
        final List<LigneDevis> listeLignesDevis = new ArrayList<LigneDevis>();
        // Parcours des lignes de devis du devis
        for (LigneDevis ligneDevis : devis.getListeLigneDevis()) {
            if (!ligneDevisDao.estUneLigneLiee(ligneDevis.getId()) && listeIdsLignesSelectionnnees.contains(ligneDevis.getId())) {
                // Si la liste des identifiants contient l'identifiant de la ligne de devis en cours, on ajoute cette ligne
                if (listeIdsLignesSelectionnnees.contains(ligneDevis.getId())) {
                    listeLignesDevis.add(ligneDevis);
                    // on met a jour la selection pour impression de la ligne
                    ligneDevis.setSelectionnePourImpression(true);
                }
                else {
                    // on met a jour la selection pour impression de la ligne
                    ligneDevis.setSelectionnePourImpression(false);
                    // Parcours des lignes de devis liées pour mettre l'impression à false
                    for (LigneDevisLiee ligneLiee : ligneDevis.getListeLignesDevisLiees()) {
                        // on met a jour la selection pour impression de la ligne
                        ligneLiee.getLigneDevisLiee().setSelectionnePourImpression(false);
                    }
                }
            }
        }
        return listeLignesDevis;
    }

    /**
     * Crée le prospect d'un devis pdf à partir du devis.
     * @param infosPersonneSquare le cache des infos de personnes Square
     * @param devis le devis
     * @return le prospect
     */
    @SuppressWarnings("unused")
	private ProspectDevisDto creerProspectDevisPdf(Devis devis, InfosPersonneSquareBean infosPersonneSquare) {
        // Récupération du prospect
        final Personne prospect = devis.getPersonnePrincipale();
        // Récupération de la personne Square
        final PersonneSimpleDto personneSquare = personneUtil.getPersonneSimple(prospect.getEidPersonne(), infosPersonneSquare);
        // Récupération de l'adresse principale de la personne square
        final CoordonneesDto coordonnees = personneUtil.getCoordonnees(prospect.getEidPersonne(), infosPersonneSquare);
        final com.square.core.model.dto.AdresseDto adressePrincipale = personneUtil.rechercherAdressePrincipaleEnCours(coordonnees.getAdresses());
        // Mapping de l'adresse
        final AdresseDto adresseDto = mapperAdresse(adressePrincipale);
        // Création du prospect
        String civilite = "";
        if (personneSquare.getCivilite() != null && !StringUtils.isBlank(personneSquare.getCivilite().getLibelle())) {
            civilite = personneSquare.getCivilite().getLibelle();
        }
        final char[] delimiters = {' ', '-', '_'};
        final String prenomMisEnForme = WordUtils.capitalizeFully(personneSquare.getPrenom().trim(), delimiters);
        return new ProspectDevisDto(prospect.getId(), civilite, personneSquare.getNom().toUpperCase(), prenomMisEnForme, adresseDto);
    }
    /**
     * Récupère le DTO permettant la génération d'un bulletin d'adhésion à partir d'un devis.
     * @param devis le devis
     * @param editionDocumentDto les infos pour l'édition
     * @param reference la référence de l'opportunité ou du devis.
     * @param idModeleDevis l'identifiant du modèle de devis
     * @param eidAgence l'identifiant extérieur de l'agence responsable
     * @param infosPersonneSquare le cache des infos de personnes Square
     * @return le DTO contenant les infos nécessaires à la génération d'un bulletin d'adhésion
     */
    public DocumentsBulletinsAdhesionDto getDocumentsBaDtoPourDevis(Devis devis, EditionDocumentDto editionDocumentDto, String reference, Long idModeleDevis,
        Long eidAgence, InfosPersonneSquareBean infosPersonneSquare) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_GENERATION_DOCUMENT_BA_DEVIS,
       		 new String[] {String.valueOf(devis.getId())}));
        final DocumentsBulletinsAdhesionDto documentsBulletinsAdhesionDto = new DocumentsBulletinsAdhesionDto();
        // Récupération du prospect
        final ProspectBADto prospectBADto = mapperProspect(devis.getPersonnePrincipale(), devis.getEidRelationParrain(), infosPersonneSquare);
        if (idModeleDevis != null && !idModeleDevis.equals(editiqueMappingService.getConstanteIdModeleDevisFicheTransfert())) {
            prospectBADto.setNumeroAdherent(null);
        }
        documentsBulletinsAdhesionDto.setProspect(prospectBADto);
        // Récupération de l'agence
        if (eidAgence != null) {
            documentsBulletinsAdhesionDto.setAgence(getAgenceBa(eidAgence));
        }
        // Création de la liste des bulletins d'adhésion
        documentsBulletinsAdhesionDto.setListeBulletinsAdhesion(creerListeBulletinsAdhesionPourDevis(devis, prospectBADto, editionDocumentDto, reference,
            idModeleDevis, infosPersonneSquare));
        // Motif du devis
        documentsBulletinsAdhesionDto.setMotifDevis(devis.getMotif() != null ? (IdentifiantLibelleDto) mapperDozerBean.map(devis.getMotif(),
            IdentifiantLibelleDto.class) : null);
        // Modèle de devis
        documentsBulletinsAdhesionDto.setIdModeleDevis(idModeleDevis);
        // On detecte si il s'agit d'une signature numérique ou pas
        documentsBulletinsAdhesionDto.setFromSignatureNumerique(editionDocumentDto.getFromSignatureNumerique());
        // On complète le DTO (flag possedeProduitSante)
        completerDocumentsBulletinsAdhesion(documentsBulletinsAdhesionDto);
        // On complète les infos de règlement
        completerInfosReglement(documentsBulletinsAdhesionDto, devis, editionDocumentDto);
        return documentsBulletinsAdhesionDto;
    }

    /**
     * Créer l'agence pour le bulletin d'adhésion.
     * @param idAgence l'identifiant de l'agence
     * @return le DTO contenant les informations du responsable
     */
    private AgenceEditiqueDto getAgenceBa(Long idAgence) {
        // Récupération de l'agence à partir du service utilisateur
        final AgenceReelleDto agenceDto = agenceService.getAgenceReelleById(idAgence);
        if (agenceDto != null) {
            // Création de l'adresse de l'agence
            final AdresseDto adresseDto = mapperDozerBean.map(agenceDto, AdresseDto.class);
            // Création de l'agence
            return new AgenceEditiqueDto(agenceDto.getId(), agenceDto.getDenomination(), adresseDto, agenceDto.getMail());
        }
        else {
            return null;
        }
    }

    /**
     * Complète le DTO DocumentsBulletinsAdhesionDto (flag pour indiquer qu'il y a un produit santé).
     * @param documentsBulletinsAdhesionDto le DTO à compléter
     */
    private void completerDocumentsBulletinsAdhesion(DocumentsBulletinsAdhesionDto documentsBulletinsAdhesionDto) {
        // Flag indiquant qu'il y a un produit santé
        boolean possedeProduitSante = false;
        // Parcours des bulletins d'adhésion
        for (BulletinAdhesionDto bulletinAdhesion : documentsBulletinsAdhesionDto.getListeBulletinsAdhesion()) {
            // On regarde s'il y a une famille Santé
            if (bulletinAdhesion.getProposition().getPropositionFamilleSante() != null) {
                possedeProduitSante = true;
                break;
            }
        }
        documentsBulletinsAdhesionDto.setPossedeProduitSante(possedeProduitSante);
        // On génère la lettre d'accompagnement
        documentsBulletinsAdhesionDto.setGenererCourrierAccompagnement(Boolean.TRUE);
        // On génère les grilles de prestations
        documentsBulletinsAdhesionDto.setGenererGrillesPrestations(Boolean.TRUE);
    }

    /**
     * Complète les informations de règlement.
     * @param documentsDto le DTO contenant les documents
     * @param devis le devis à partir duquel on récupère les informations de règlement
     * @param editionDocumentDto infos pour l'edition
     */
    private void completerInfosReglement(DocumentsBulletinsAdhesionDto documentsDto, Devis devis, EditionDocumentDto editionDocumentDto) {
        if (devis != null) {
            final Adhesion adhesion = devis.getOpportunite().getAdhesion();
            // Récupération de la périodicité
            String periodicite = null;
            if (adhesion.getPeriodicitePaiement() != null && adhesion.getPeriodicitePaiement().getId() != null) {
                final Long idPeriodicite = adhesion.getPeriodicitePaiement().getId();
                if (idPeriodicite.equals(tarificateurSquareMappingService.getIdPeriodicitePaiementMensuelle())) {
                    periodicite = InfosReglementDto.PERIODICITE_MENSUELLE;
                }
                else if (idPeriodicite.equals(tarificateurSquareMappingService.getIdPeriodicitePaiementTrimestrielle())) {
                    periodicite = InfosReglementDto.PERIODICITE_TRIMESTRIELLE;
                }
                else if (idPeriodicite.equals(tarificateurSquareMappingService.getIdPeriodicitePaiementSemestrielle())) {
                    periodicite = InfosReglementDto.PERIODICITE_SEMESTRIELLE;
                }
                else if (idPeriodicite.equals(tarificateurSquareMappingService.getIdPeriodicitePaiementAnnuelle())) {
                    periodicite = InfosReglementDto.PERIODICITE_ANNUELLE;
                }
            }
            // Récupération du moyen de paiement (Prélèvement ou chèque)
            String moyenPaiement = null;
            if (adhesion.getMoyenPaiement() != null && adhesion.getMoyenPaiement().getId() != null) {
                final Long idMoyenPaiement = adhesion.getMoyenPaiement().getId();
                if (idMoyenPaiement.equals(tarificateurSquareMappingService.getIdMoyenPaiementPrelevement())) {
                    moyenPaiement = InfosReglementDto.MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE;
                }
                else if (idMoyenPaiement.equals(tarificateurSquareMappingService.getIdMoyenPaiementCheque())) {
                    moyenPaiement = InfosReglementDto.MODE_REGLEMENT_CHEQUE;
                }
            }
            // Récupération du moyen de paiement avec le jour si prélèvement
            if (moyenPaiement != null && moyenPaiement.equals(InfosReglementDto.MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE)) {
                // Récupération du jour de paiement
                if (adhesion.getJourPaiement() != null && adhesion.getJourPaiement().getId() != null) {
                    final Long idJourPaiement = adhesion.getJourPaiement().getId();
                    if (idJourPaiement.equals(tarificateurSquareMappingService.getIdJourPaiement5DuMois())) {
                        moyenPaiement = InfosReglementDto.MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_5_DU_MOIS;
                    }
                    else if (idJourPaiement.equals(tarificateurSquareMappingService.getIdJourPaiement10DuMois())) {
                        moyenPaiement = InfosReglementDto.MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_10_DU_MOIS;
                    }
                    else if (idJourPaiement.equals(tarificateurSquareMappingService.getIdJourPaiement15DuMois())) {
                        moyenPaiement = InfosReglementDto.MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_15_DU_MOIS;
                    }
                }
            }

            InfosRibDto infosRibDto = null;
            // si on a passé des infos de RIB et qu'on en a pas sur l'adhésion
            if (editionDocumentDto.getInfosRib() != null && adhesion.getInfosRib() == null) {
                infosRibDto = mapperDozerBean.map(editionDocumentDto.getInfosRib(), InfosRibDto.class);
            }
            else if (adhesion.getInfosRib() != null) {
                infosRibDto = mapperDozerBean.map(adhesion.getInfosRib(), InfosRibDto.class);
            }
            // si on a passé des infos de paiement et qu'on en a pas sur l'adhésion
            if (editionDocumentDto.getJourPaiement() != null && editionDocumentDto.getJourPaiement().getIdentifiant() != null && moyenPaiement == null) {
                final Long idJourPaiement = editionDocumentDto.getJourPaiement().getIdentifiant();
                if (idJourPaiement.equals(tarificateurSquareMappingService.getIdJourPaiement5DuMois())) {
                    moyenPaiement = InfosReglementDto.MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_5_DU_MOIS;
                }
                else if (idJourPaiement.equals(tarificateurSquareMappingService.getIdJourPaiement10DuMois())) {
                    moyenPaiement = InfosReglementDto.MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_10_DU_MOIS;
                }
                else if (idJourPaiement.equals(tarificateurSquareMappingService.getIdJourPaiement15DuMois())) {
                    moyenPaiement = InfosReglementDto.MODE_REGLEMENT_PRELEVEMENT_AUTOMATIQUE_15_DU_MOIS;
                }
            }
            for (BulletinAdhesionDto bulletinAdhesionDto : documentsDto.getListeBulletinsAdhesion()) {
                if (periodicite != null || moyenPaiement != null) {
                    bulletinAdhesionDto.setInfosReglement(new InfosReglementDto(periodicite, moyenPaiement));
                }
                bulletinAdhesionDto.setInfosRib(infosRibDto);
            }
        }
    }

    /**
     * Créer la liste des bulletins d'adhésion pour un devis.
     * @param devis le devis
     * @param prospect le prospect
     * @param editionDocumentDto infos pour l'edition
     * @param reference la référence
     * @param idModeleDevis l'identifiant du modèle de devis
     * @param infosPersonneSquare le cache des infos de personnes Square
     * @return la liste des bulletins d'adhésion
     */
    private List<BulletinAdhesionDto> creerListeBulletinsAdhesionPourDevis(Devis devis, ProspectBADto prospect, EditionDocumentDto editionDocumentDto,
        String reference, Long idModeleDevis, InfosPersonneSquareBean infosPersonneSquare) {
        final List<Long> listeIdsLignesSelectionnees = editionDocumentDto.getIdsLigneDevisSelection();
        final List<Long> listeIdsBenefsSelectionnes = editionDocumentDto.getIdsBeneficiairesSelectionnes();

        // Création de la liste des bulletins d'adhésion
        final List<BulletinAdhesionDto> listeBulletinsAdhesion = new ArrayList<BulletinAdhesionDto>();

        // Récupération des lignes principales sélectionnées
        final List<LigneDevis> listeLignesSelectionnees = getListeLignesSelectionnees(devis, listeIdsLignesSelectionnees);

        // Création d'un bulletin d'adhésion par lignes principales sélectionnées
        for (LigneDevis ligneDevisPrincipale : listeLignesSelectionnees) {
            final BulletinAdhesionDto bulletinAdhesion = new BulletinAdhesionDto();
            // Numéro de dossier = référence
            bulletinAdhesion.setNumeroDossier(reference);
            // Titre du document = libellé du modèle de devis en lettres capitales
            if (idModeleDevis != null) {
                final ModeleDevisDto modeleDevis = editiqueMappingService.getModelDevisById(idModeleDevis);
                if (modeleDevis != null) {
                    bulletinAdhesion.setTitreDocumentBA(modeleDevis.getLibelle().toUpperCase());
                }
            }
            // Création de la liste des bénéficiaires
            bulletinAdhesion.setListeBeneficiaires(creerListeBeneficiaires(ligneDevisPrincipale, listeIdsLignesSelectionnees, listeIdsBenefsSelectionnes, devis
                    .getPersonnePrincipale(), infosPersonneSquare));
            // Création de la proposition
            formerPropositionBaPourLigneDevis(bulletinAdhesion, ligneDevisPrincipale, prospect, listeIdsLignesSelectionnees, false);
            // Date de signature
            if (editionDocumentDto.getDateSignature() != null) {
                bulletinAdhesion.setDateSignature(editionDocumentDto.getDateSignature());
            }
            else {
                bulletinAdhesion.setDateSignature(devis.getOpportunite().getAdhesion().getDateSignature() != null ? devis.getOpportunite().getAdhesion()
                        .getDateSignature() : Calendar.getInstance());
            }
            // Flag pour savoir si l'adhesion a été crée et donc le document signé
            if (editionDocumentDto.getDocumentSigneNumeriquement() != null) {
                bulletinAdhesion.setDocumentSigneNumeriquement(editionDocumentDto.getDocumentSigneNumeriquement());
            }
            // Numéro d'adhérent du parrain
            bulletinAdhesion.setNumeroAdherentParrain(prospect.getNumeroAdherentParrain());
            // Récupération des infos de l'adhésion Internet
            bulletinAdhesion.setInfosAdhesionInternet(getInfosAdhesionInternet(devis.getOpportunite().getAdhesion(), editionDocumentDto));

            // Délai de stage
            bulletinAdhesion.setPossedeDelaiAttente(calculerDelaiDeStage(devis.getPersonnePrincipale()));

            // On complète le bulletin d'adhésion (infos décès, montants, bonus Eco, capital garanti)
            completerBulletinAdhesion(bulletinAdhesion);
            listeBulletinsAdhesion.add(bulletinAdhesion);
        }
        return listeBulletinsAdhesion;
    }

    /**
     * Détermine le délai de stage.
     * @param personne la personne concernée par le délai de stage
     * @return true si le délai de stage est appliqué, false sinon
     */
    private boolean calculerDelaiDeStage(Personne personne) {
        final Calendar dateNaissance = (Calendar) personne.getDateNaissance().clone();
        final boolean personneCouverteActuellement = personne.getActuellementCouvert();
        final boolean personneCouverteSixDerniersMois = personne.getCouvertSixDerniersMois();
        // CALCULE DE L'AGE CALENDAIRE DE L'ASSURE
        final Calendar toDay = Calendar.getInstance();
        int age = toDay.get(Calendar.YEAR) - dateNaissance.get(Calendar.YEAR);
        dateNaissance.add(Calendar.YEAR, age);
        if (toDay.before(dateNaissance)) {
            age--;
        }

        boolean delaiDeStage;
        if (age >= tarificateurSquareMappingService.getConstanteAdhesionAgeMiniDelaiStage().intValue()) {
            // Si la personne a 65 ans ou plus, le délais de stage ne s'applique pas que si couvertActuellement et pas couvert 6 derniers mois
            delaiDeStage = !(personneCouverteActuellement && !personneCouverteSixDerniersMois);
        }
        else {
            // Si la personne a moins de 65 ans, le délais de stage ne s'applique que si couvertActuellement et couvert 6 derniers mois,
            // ou si pas couvertActuellement et couvert 6 derniers mois
            delaiDeStage = (personneCouverteActuellement && personneCouverteSixDerniersMois)
                || (!personneCouverteActuellement && personneCouverteSixDerniersMois);
        }
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_DELAIS_STAGE,
       		 new String[] {String.valueOf(age),
        		personneCouverteActuellement ? messageSourceUtil.get(MessageKeyUtil.MESSAGE_OUI) : messageSourceUtil.get(MessageKeyUtil.MESSAGE_NON),
       			personneCouverteSixDerniersMois ? messageSourceUtil.get(MessageKeyUtil.MESSAGE_OUI) : messageSourceUtil.get(MessageKeyUtil.MESSAGE_NON),
       		    delaiDeStage ? messageSourceUtil.get(MessageKeyUtil.MESSAGE_OUI) : messageSourceUtil.get(MessageKeyUtil.MESSAGE_NON)}));
        return delaiDeStage;
    }

    /**
     * Récupère les infos d'une adhésion internet.
     * @param adhesion l'adhésion
     * @return les infos de l'adhésion
     */
    private InfosAdhesionInternetDto getInfosAdhesionInternet(Adhesion adhesion, EditionDocumentDto editionDocumentDto) {
        // Calcul du type de paiement
        final Long idMoyenPaiementCarte = tarificateurSquareMappingService.getIdMoyenPaiementCarteBancaire();
        final Long idMoyenPaiementCheque = tarificateurSquareMappingService.getIdMoyenPaiementCheque();
        String typePaiement = null;
        if (adhesion.getMoyenPaiementPremierReglement() != null && adhesion.getMoyenPaiementPremierReglement().getId().equals(idMoyenPaiementCarte)) {
            typePaiement = InfosAdhesionInternetDto.TYPE_PAIEMENT_CARTE_BANCAIRE;
        }
        else if (adhesion.getMoyenPaiementPremierReglement() != null && adhesion.getMoyenPaiementPremierReglement().getId().equals(idMoyenPaiementCheque)) {
            typePaiement = InfosAdhesionInternetDto.TYPE_PAIEMENT_CHEQUE;
        }
        boolean teletransmission = false;
        if (editionDocumentDto.getTeletransmission() != null) {
            teletransmission = editionDocumentDto.getTeletransmission().booleanValue();
        }
        else if (adhesion.getTeletransmission() != null) {
            teletransmission = adhesion.getTeletransmission().booleanValue();
        }
        // Création des infos
        return new InfosAdhesionInternetDto(adhesion.getMontantPaiement(), typePaiement, teletransmission);
    }

    /**
     * Récupère les lignes de devis principales sélectionnées pour l'impression pour un devis.
     * @param devis le devis
     * @param listeIdsLignesSelectionnnees la liste des identifiants des lignes de devis sélectionnées pour l'impression
     * @return la liste des lignes de devis
     */
    private List<LigneDevis> getListeLignesSelectionnees(Devis devis, List<Long> listeIdsLignesSelectionnnees) {
        final List<LigneDevis> listeLignesDevis = new ArrayList<LigneDevis>();
        // Parcours des lignes de devis du devis
        for (LigneDevis ligneDevis : devis.getListeLigneDevis()) {
            if (!ligneDevisDao.estUneLigneLiee(ligneDevis.getId()) && listeIdsLignesSelectionnnees.contains(ligneDevis.getId())) {
                // Si la liste des identifiants contient l'identifiant de la ligne de devis en cours, on ajoute cette ligne
                if (listeIdsLignesSelectionnnees.contains(ligneDevis.getId())) {
                    listeLignesDevis.add(ligneDevis);
                    // on met a jour la selection pour impression de la ligne
                    ligneDevis.setSelectionnePourImpression(true);
                }
                else {
                    // on met a jour la selection pour impression de la ligne
                    ligneDevis.setSelectionnePourImpression(false);
                    // Parcours des lignes de devis liées pour mettre l'impression à false
                    for (LigneDevisLiee ligneLiee : ligneDevis.getListeLignesDevisLiees()) {
                        // on met a jour la selection pour impression de la ligne
                        ligneLiee.getLigneDevisLiee().setSelectionnePourImpression(false);
                    }
                }
            }
        }
        return listeLignesDevis;
    }

    /**
     * Crée la liste des bénéficiaire à partir d'une ligne de devis principal.
     * @param ligneDevisPrincipale la ligne de devis principale
     * @param listeIdsLignesSelectionnees la liste des identifiants de lignes sélectionnées
     * @param listeIdsLignesSelectionnees la liste des identifiants des bénéficiaires sélectionnés.
     * @param prospect le prospect
     * @param infosPersonneSquare le cache des infos de personnes Square
     * @return la liste des bénéficiaires
     */
    private List<BeneficiaireBADto> creerListeBeneficiaires(LigneDevis ligneDevisPrincipale, List<Long> listeIdsLignesSelectionnees,
        List<Long> listeIdsBenefsSelectionnes, Personne prospect, InfosPersonneSquareBean infosPersonneSquare) {
        // On stocke le lien familial de chaque bénéficiaire avec le prospect dans une map
        final Map<Long, Long> mapLienFamilialBeneficiaires = new HashMap<Long, Long>();
        for (Beneficiaire beneficiaireParcouru : prospect.getListeBeneficiaires()) {
            mapLienFamilialBeneficiaires.put(beneficiaireParcouru.getPersonneCible().getId(), beneficiaireParcouru.getLienFamilial().getId());
        }

        final List<BeneficiaireBADto> listeBeneficiaires = new ArrayList<BeneficiaireBADto>();

        // Map des valeurs de règle de stage par bénéficiaire
        final Map<Long, List<RegleValeur>> mapValeursReglesStageBeneficiaires = new HashMap<Long, List<RegleValeur>>();
        final List<RegleValeur> listeValeursReglesStageProspect = new ArrayList<RegleValeur>();

        // Récupération de constantes
        final Integer idRegleStage = regleMappingService.getIdRegleStage();
        final Integer idValeurRegleStageSmatis = regleMappingService.getIdValeurRegleStageSmatis();
        final Long idLienConjoint = tarificateurSquareMappingService.getIdLienFamilialConjoint();

        // Test si le produit de la ligne principale a un code de tarification "adherent"
        final Integer idProduit = ligneDevisPrincipale.getEidProduit();
        final ProduitCriteresDto criteres = new ProduitCriteresDto();
        criteres.setIdentifiantProduit(idProduit);
        final List<ProduitDto> listeProduits = produitService.getListeProduits(criteres);
        if (listeProduits == null || listeProduits.size() != 1) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.PRODUIT_LIGNE_DEVIS_INTROUVABLE));
        }
        final ProduitDto produitDto = listeProduits.get(0);
        if (produitDto.getModeTarification() != null
                && tarificateurMappingService.getConstanteIdModeTarificationAdherent().equals(produitDto.getModeTarification().getIdentifiant())) {
            if (listeIdsBenefsSelectionnes != null && listeIdsBenefsSelectionnes.size() > 0) {
                for (Long idBeneficiaire : listeIdsBenefsSelectionnes) {
                    final Beneficiaire beneficiaireProspect = beneficiaireDao.getBeneficiaireByCible(idBeneficiaire);
                    if (beneficiaireProspect == null) {
                        throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_BENEFICIAIRE_INEXISTANT));
                    }
                    // Création du bénéficiaireBA
                    final BeneficiaireBADto beneficiaireDevisDto = mapperBeneficiaire(beneficiaireProspect.getPersonneCible(), infosPersonneSquare);
                    beneficiaireDevisDto.setIdBeneficiaire(beneficiaireProspect.getPersonneCible().getId());
                    // Test si c'est le conjoint
                    if (beneficiaireProspect.getLienFamilial() != null && beneficiaireProspect.getLienFamilial().getId() != null
                        && beneficiaireProspect.getLienFamilial().getId().equals(idLienConjoint)) {
                        beneficiaireDevisDto.setIsConjoint(Boolean.TRUE);
                    }
                    // Ajout du bénéficiaire à la liste des bénéficiaires du devis
                    listeBeneficiaires.add(beneficiaireDevisDto);
                }
            }
        }
        else {
            // Ajout des bénéficiaires du prospect à partir des lignes liées de la ligne principale
            final List<Long> listeIdsBeneficiairesDejaPresents = new ArrayList<Long>();
            // Parcours des lignes liées
            for (LigneDevisLiee ligneLiee : ligneDevisPrincipale.getListeLignesDevisLiees()) {
                final LigneDevis ligneDevisLiee = ligneLiee.getLigneDevisLiee();
                // On ne prend en compte que les bénéficiaires des lignes sélectionnées
                if (listeIdsLignesSelectionnees.contains(ligneDevisLiee.getId())) {
                    // Si c'est une ligne sur un bénéficiaire : on l'ajoute à la liste s'il n'est pas déjà présent
                    final Personne beneficiaire = ligneDevisLiee.getBeneficiaire();
                    if (beneficiaire != null) {
                        if (!listeIdsBeneficiairesDejaPresents.contains(beneficiaire.getId())) {
                            // Ajout de l'id du bénéficiaire à la liste des bénéficiaires déjà présents
                            listeIdsBeneficiairesDejaPresents.add(beneficiaire.getId());
                            // Création du bénéficiaireBA
                            final BeneficiaireBADto beneficiaireDevisDto = mapperBeneficiaire(beneficiaire, infosPersonneSquare);
                            beneficiaireDevisDto.setIdBeneficiaire(beneficiaire.getId());
                            final Long idLienFamilial = mapLienFamilialBeneficiaires.get(beneficiaire.getId());
                            // Détection du conjoint
                            if (idLienFamilial != null && idLienFamilial.equals(idLienConjoint)) {
                                beneficiaireDevisDto.setIsConjoint(Boolean.TRUE);
                            }
                            // Ajout du bénéficiaire à la liste des bénéficiaires du devis
                            listeBeneficiaires.add(beneficiaireDevisDto);
                        }
                        // Récupération de la valeur de la règle "Stage"
                        List<RegleValeur> listeValeurReglesStageBeneficiaire = mapValeursReglesStageBeneficiaires.get(beneficiaire.getId());
                        if (listeValeurReglesStageBeneficiaire == null) {
                            listeValeurReglesStageBeneficiaire = new ArrayList<RegleValeur>();
                            mapValeursReglesStageBeneficiaires.put(beneficiaire.getId(), listeValeurReglesStageBeneficiaire);
                        }
                        final RegleValeur regleValeur = getValeurRegleStageByLigneDevis(ligneDevisLiee, idRegleStage);
                        if (regleValeur != null) {
                            listeValeurReglesStageBeneficiaire.add(regleValeur);
                        }
                    }
                    else {
                        // Sinon c'est un prospect
                        // Récupération de la valeur de la règle "Stage"
                        final RegleValeur regleValeurStage = getValeurRegleStageByLigneDevis(ligneDevisLiee, idRegleStage);
                        if (regleValeurStage != null) {
                            listeValeursReglesStageProspect.add(regleValeurStage);
                        }
                    }
                }
            }
            // Ajout des délais de stage des bénéficiaires
            for (BeneficiaireBADto beneficiaireBADto : listeBeneficiaires) {
                final List<RegleValeur> listeValeursReglesStageBeneficiaire = mapValeursReglesStageBeneficiaires.get(beneficiaireBADto.getIdBeneficiaire());
                beneficiaireBADto.setDelaiAttente(getDelaiStageByReglesValeurs(listeValeursReglesStageBeneficiaire, idValeurRegleStageSmatis));
            }
        }

        // On ordonne les bénéficiaires par date de naissance croissante
        // test si les date de naissance sont non nulles
        for (BeneficiaireBADto beneficiaireBADto : listeBeneficiaires) {
            if (beneficiaireBADto.getDateDeNaissance() == null) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_BENEFICIAIRE_DATE_NAISSANCE_NULLE));
            }
        }
        // Création d'un Comparator
        final Comparator<BeneficiaireBADto> comparator = new Comparator<BeneficiaireBADto>() {
            public int compare(BeneficiaireBADto o1, BeneficiaireBADto o2) {
                return ((BeneficiaireBADto) o1).getDateDeNaissance().compareTo(((BeneficiaireBADto) o2).getDateDeNaissance());
            }
        };
        Collections.sort(listeBeneficiaires, comparator);

        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_TAILLE_LISTE_BENEFICIAIRE,
       		 new String[] {String.valueOf(listeBeneficiaires.size())}));

        // Ajout du prospect en tant que bénéficiaire (en début de liste)
        final BeneficiaireBADto prospectBeneficiaireBA = mapperBeneficiaire(prospect, infosPersonneSquare);
        prospectBeneficiaireBA.setIdProspect(prospect.getId());
        // Ajout de la valeur de règle Stage de la ligne principale
        final RegleValeur regleValeurStage = getValeurRegleStageByLigneDevis(ligneDevisPrincipale, idRegleStage);
        if (regleValeurStage != null) {
            listeValeursReglesStageProspect.add(regleValeurStage);
        }
        // Ajout du délai de stage au prospect
        prospectBeneficiaireBA.setDelaiAttente(getDelaiStageByReglesValeurs(listeValeursReglesStageProspect, idValeurRegleStageSmatis));
        listeBeneficiaires.add(0, prospectBeneficiaireBA);

        return listeBeneficiaires;
    }

    /** Former la proposition BA pour la ligne de devis principale.
     * @param ligneDevisPrincipale la ligne de devis principale
     * @param prospect le prospect
     * @param listeIdsLignesSelectionnees la liste des identifiants
     * @param bulletinAdhesion le ba
     * @param pourAdhesion pourAdhesion
     */
    private void formerPropositionBaPourLigneDevis(BulletinAdhesionDto bulletinAdhesion, LigneDevis ligneDevisPrincipale, ProspectBADto prospect,
        List<Long> listeIdsLignesSelectionnees, boolean pourAdhesion) {

        final Long idFinaliteAcceptee = tarificateurSquareMappingService.getIdFinaliteAcceptee();
        // Gamme du produit Santé
        String libelleGammeProduitSante = "";
        byte[] logoGammeProduitSante = null;
        // Gamme du produit Prévoyance
        byte[] logoGammeProduitPrevoyance = null;
        // Libellé du produit santé principal
        String libelleProduitSantePrincipal = "";
        // Date d'effet du bonus 1
        Calendar dateEffetBonus1 = null;

        // Récupération des constantes des modes de paiement
        final Integer idModePaiementMensuel = tarificateurMappingService.getIdentifiantModePaiementMensuel();
        final Integer idModePaiementUnique = tarificateurMappingService.getIdentifiantModePaiementUnique();

        // Récupération des constantes des produits Bonus
        final List<Integer> listeProduitsBonus1 = tarificateurMappingService.getListeProduitsBonus1();
        final List<Integer> listeProduitsBonus2 = tarificateurMappingService.getListeProduitsBonus2();
        final List<Integer> listeProduitsBonus = new ArrayList<Integer>(listeProduitsBonus1);
        listeProduitsBonus.addAll(listeProduitsBonus2);

        // Liste des différentes familles de produits
        final List<Integer> listeIdsProduitsSante = new ArrayList<Integer>();
        final List<Integer> listeIdsProduitsPrevoyance = new ArrayList<Integer>();

        // Map des produits avec la liste des bénéficiaires
        final Map<Integer, List<BeneficiaireMontantBADto>> mapProduitBeneficiaires = new HashMap<Integer, List<BeneficiaireMontantBADto>>();

        // Gestion de la ligne de devis principale
        final Integer idProduitPrincipal = ligneDevisPrincipale.getEidProduit();
        ProduitCriteresDto criteresProduit = new ProduitCriteresDto();
        criteresProduit.setIdentifiantProduit(idProduitPrincipal);
        List<ProduitDto> listeProduits = produitService.getListeProduits(criteresProduit);
        if (listeProduits.size() != 1) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.GENERATION_PDF_IMPOSSIBLE));
        }
        final ProduitDto produitPrincipal = listeProduits.get(0);
        final Integer idGammeLignePrincipale = produitPrincipal.getGamme().getIdentifiant();
        // Ajout du produit de la ligne principale à la famille santé ou prévoyance selon le type
        if (produitService.isItGammePrevoyance(idGammeLignePrincipale)) {
            listeIdsProduitsPrevoyance.add(idProduitPrincipal);
        }
        else {
            listeIdsProduitsSante.add(idProduitPrincipal);
            // Récupération de l'image de la gamme de la santé
            final GammeProduitCriteresDto criteresGammes = new GammeProduitCriteresDto();
            criteresGammes.setIdentifiantGammeProduit(idGammeLignePrincipale);
            final List<GammeProduitDto> listeGammes = produitService.getListeGammesProduits(criteresGammes);
            if (listeGammes.size() != 1) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.GENERATION_PDF_IMPOSSIBLE));
            }
            libelleGammeProduitSante = listeGammes.get(0).getLibelle();
            // Récupération du logo de la gamme
            final CriteresImageGammeDto criteresImageGamme = new CriteresImageGammeDto(idGammeLignePrincipale, CriteresImageGammeDto.TYPE_IMAGE_ROND);
            logoGammeProduitSante = produitService.getImageGamme(criteresImageGamme);
            // Récupération du libellé du produit santé principal
            final ProduitCriteresDto critereProduit = new ProduitCriteresDto();
            critereProduit.setIdentifiantProduit(idProduitPrincipal);
            libelleProduitSantePrincipal = produitPrincipal.getLibelleCommercial();
        }

        // Création du bénéficiaireMontant du produit
        final BeneficiaireMontantBADto prospectBeneficiaireMontant = creerBeneficiaireMontant(ligneDevisPrincipale, prospect, null);
        // Ajout du bénéficiaire à la map des produits
        if (mapProduitBeneficiaires.get(idProduitPrincipal) == null) {
            mapProduitBeneficiaires.put(idProduitPrincipal, new ArrayList<BeneficiaireMontantBADto>());
        }
        mapProduitBeneficiaires.get(idProduitPrincipal).add(prospectBeneficiaireMontant);

        // Gestion des lignes liées
        // on recupere les produits liés au produit de la ligne principale
        final ProduitLieCriteresDto criteresProduitLie = new ProduitLieCriteresDto();
        criteresProduitLie.setIdentifiantProduit(ligneDevisPrincipale.getEidProduit());
        criteresProduitLie.setIdentifiantApplication(tarificateurMappingService.getIdentifiantApplicationGestcom());
        // null -> pour forcer la recherche sur les produits actifs et non actifs
        criteresProduitLie.setSeulementActif(null);
        final List<ProduitLieDto> listeProduitsLies = produitService.getListeProduitsLies(criteresProduitLie);
        // creation d'une map (idProduit / produit lié optionnel ou pas)
        final Map<Integer, ProduitLieDto> mapProduitLies = new HashMap<Integer, ProduitLieDto>();
        for (ProduitLieDto produitLieDto : listeProduitsLies) {
            mapProduitLies.put(produitLieDto.getProduitLie().getIdentifiant(), produitLieDto);
        }
        // Parcours des lignes liées
        for (LigneDevisLiee ligneLiee : ligneDevisPrincipale.getListeLignesDevisLiees()) {
            final LigneDevis ligneDevisLiee = ligneLiee.getLigneDevisLiee();
            // on recupere si le produit de la ligne liée doit être affiché
            Boolean aAfficher = true;
            Boolean isOptionnel = true;
            if (mapProduitLies.containsKey(ligneDevisLiee.getEidProduit())) {
                // Bug 0004583 : Si le produit était un ancien produit obligatoire, il ne faut pas l'afficher, mais inclure son tarif sur le produit principal
                final ProduitLieDto produitLie = mapProduitLies.get(ligneDevisLiee.getEidProduit());
                aAfficher = produitLie.getAffichageDevis() == null || Boolean.TRUE.equals(produitLie.getAffichageDevis());
                isOptionnel = produitLie.getAffichageDevis() == null || Boolean.TRUE.equals(produitLie.getOptionnel());
                if (!aAfficher && isOptionnel && listeIdsLignesSelectionnees.contains(ligneDevisLiee.getId())) {
                    prospectBeneficiaireMontant.setMontantSansRemise(prospectBeneficiaireMontant.getMontantSansRemise()
                        + ligneLiee.getLigneDevisLiee().getMontantTtc());
                    prospectBeneficiaireMontant.setMontantRemise(prospectBeneficiaireMontant.getMontantRemise()
                        + ligneLiee.getLigneDevisLiee().getMontantRemise());
                } else if (aAfficher && !isOptionnel && listeIdsLignesSelectionnees.contains(ligneDevisLiee.getId())) {
                	// On va afficher les produits liés obligatoires, il faut enlever le montant du produit principal
                        prospectBeneficiaireMontant.setMontantSansRemise(prospectBeneficiaireMontant.getMontantSansRemise() - ligneLiee.getLigneDevisLiee().getMontantTtc());
                }
            }
            // On ne prend en compte que les lignes sélectionnées
            // On ne prend pas en compte les lignes de devis liés obligatoires
            if (((!pourAdhesion && listeIdsLignesSelectionnees != null && listeIdsLignesSelectionnees.contains(ligneDevisLiee.getId())) || (pourAdhesion
                && ligneDevisLiee.getFinalite().getId() != null && ligneDevisLiee.getFinalite().getId().equals(idFinaliteAcceptee)))
                && aAfficher) {
                criteresProduit = new ProduitCriteresDto();
                criteresProduit.setIdentifiantProduit(ligneDevisLiee.getEidProduit());
                listeProduits = produitService.getListeProduits(criteresProduit);
                if (listeProduits.size() != 1) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.GENERATION_PDF_IMPOSSIBLE));
                }
                final ProduitDto produitLigneLiee = listeProduits.get(0);
                // On ne prend pas en compte les produits bonus
                final Integer idGammeProduitLigneLiee = produitLigneLiee.getGamme().getIdentifiant();
                final Integer idProduitLigneLiee = produitLigneLiee.getIdentifiant();
                if (!listeProduitsBonus.contains(idProduitLigneLiee)) {
                    // Si gamme prévoyance, ajout du produit à la famille prévoyance s'il n'est pas déjà présent
                    if (produitService.isItGammePrevoyance(idGammeProduitLigneLiee)) {
                        if (!listeIdsProduitsPrevoyance.contains(idProduitLigneLiee)) {
                            listeIdsProduitsPrevoyance.add(idProduitLigneLiee);
                        }
                    }
                    else {
                        // Famille Santé
                        if (!listeIdsProduitsSante.contains(idProduitLigneLiee)) {
                            listeIdsProduitsSante.add(idProduitLigneLiee);
                        }
                    }

                    // Création du bénéficiaireMontant du produit
                    final BeneficiaireMontantBADto beneficiaireMontant = creerBeneficiaireMontant(ligneDevisLiee, prospect, mapProduitLies);
                    // Ajout du bénéficiaire à la map des produits
                    if (mapProduitBeneficiaires.get(idProduitLigneLiee) == null) {
                        mapProduitBeneficiaires.put(idProduitLigneLiee, new ArrayList<BeneficiaireMontantBADto>());
                    }
                    mapProduitBeneficiaires.get(idProduitLigneLiee).add(beneficiaireMontant);

                }
                else {
                    // C'est un produit Bonus
                    // Si c'est un produit Bonus 1 sur le prospect, on récupère la date d'effet du bonus
                    if (listeProduitsBonus1.contains(idProduitLigneLiee) && ligneDevisLiee.getBeneficiaire() == null) {
                        dateEffetBonus1 = ligneDevisLiee.getDateEffet();
                    }
                }

                // Impression de la ligne seulement si impression devis
                if (!pourAdhesion) {
                    ligneDevisLiee.setSelectionnePourImpression(true);
                }
            }
            else {
                // Impression de la ligne seulement si impression devis
                if (!pourAdhesion) {
                    ligneDevisLiee.setSelectionnePourImpression(false);
                }
            }
        }

        // SANTE
        // Création de la liste des produits santé
        final List<PropositionProduitBADto> listePropositionsProduitsSante = new ArrayList<PropositionProduitBADto>();
        // Parcours de la liste des identifiants des produits santé
        for (Integer idProduitSante : listeIdsProduitsSante) {
            // Récupération du produit
            final ProduitCriteresDto critereProduit = new ProduitCriteresDto();
            critereProduit.setIdentifiantProduit(idProduitSante);
            // Appel du service
            final List<ProduitDto> listeProduitsSante = produitService.getListeProduits(critereProduit);
            if (listeProduitsSante.size() != 1) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.GENERATION_PDF_IMPOSSIBLE));
            }
            // Récupération du mode de paiement du produit
            String frequenceCotisationProduit = "";
            final ModePaiementDto modePaiementProduit = produitService.getModePaiementParProduit(idProduitSante);
            if (modePaiementProduit.getIdentifiant().equals(idModePaiementUnique)) {
                frequenceCotisationProduit = PropositionProduitBADto.FREQUENCE_COTISATION_UNIQUE;
            }
            else if (modePaiementProduit.getIdentifiant().equals(idModePaiementMensuel)) {
                frequenceCotisationProduit = PropositionProduitBADto.FREQUENCE_COTISATION_MENSUELLE;
            }
            // Récupération du logo du produit
            final CriteresImageProduitDto criteresImageProduit = new CriteresImageProduitDto(idProduitSante, CriteresImageProduitDto.TYPE_IMAGE_ALLONGE);
            final byte[] logoProduit = produitService.getImageProduit(criteresImageProduit);
            // Création du DTO PropositionProduit
            // Le montant total du produit est renseigné plus tard
            final PropositionProduitBADto propositionProduit =
                new PropositionProduitBADto(idProduitSante, listeProduitsSante.get(0).getLibelleCommercial(), logoProduit, frequenceCotisationProduit,
                    mapProduitBeneficiaires.get(idProduitSante));
            listePropositionsProduitsSante.add(propositionProduit);
        }
        // Création de la proposition de la famille santé si la liste des produits n'est pas vide
        PropositionFamilleProduitsBADto propositionFamilleSante = null;
        if (listePropositionsProduitsSante != null && !listePropositionsProduitsSante.isEmpty()) {
            propositionFamilleSante =
                new PropositionFamilleProduitsBADto("SANTE", listePropositionsProduitsSante, libelleGammeProduitSante, logoGammeProduitSante,
                    libelleProduitSantePrincipal);
        }

        // PREVOYANCE
        // Création de la liste des produits prévoyance
        final List<PropositionProduitBADto> listePropositionsProduitsPrevoyance = new ArrayList<PropositionProduitBADto>();
        // Parcours de la liste des identifiants des produits prévoyance
        for (Integer idProduitPrevoyance : listeIdsProduitsPrevoyance) {
            // Récupération du produit
            final ProduitCriteresDto critereProduit = new ProduitCriteresDto();
            critereProduit.setIdentifiantProduit(idProduitPrevoyance);
            // Appel du service
            final List<ProduitDto> listeProduitsPrevoyance = produitService.getListeProduits(critereProduit);
            if (listeProduitsPrevoyance.size() != 1) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.GENERATION_PDF_IMPOSSIBLE));
            }
            // Récupération du mode de paiement du produit
            String frequenceCotisationProduit = "";
            final ModePaiementDto modePaiementProduit = produitService.getModePaiementParProduit(idProduitPrevoyance);
            if (modePaiementProduit.getIdentifiant().equals(idModePaiementUnique)) {
                frequenceCotisationProduit = PropositionProduitBADto.FREQUENCE_COTISATION_UNIQUE;
            }
            else if (modePaiementProduit.getIdentifiant().equals(idModePaiementMensuel)) {
                frequenceCotisationProduit = PropositionProduitBADto.FREQUENCE_COTISATION_MENSUELLE;
            }
            // Création du DTO PropositionProduit
            // Le montant total du produit est renseigné plus tard
            final PropositionProduitBADto propositionProduit =
                new PropositionProduitBADto(idProduitPrevoyance, listeProduitsPrevoyance.get(0).getLibelleCommercial(), null, frequenceCotisationProduit,
                    mapProduitBeneficiaires.get(idProduitPrevoyance));
            listePropositionsProduitsPrevoyance.add(propositionProduit);
            // Récupération du logo de la gamme Prévoyance
            if (logoGammeProduitPrevoyance == null) {
                final CriteresImageGammeDto criteresImageGamme =
                    new CriteresImageGammeDto(listeProduitsPrevoyance.get(0).getGamme().getIdentifiant(), CriteresImageGammeDto.TYPE_IMAGE_ROND);
                logoGammeProduitPrevoyance = produitService.getImageGamme(criteresImageGamme);
            }
        }
        // Bug 1105 : ordre des produits
        final Comparator<PropositionProduitBADto> comparateurProduits = new Comparator<PropositionProduitBADto>() {
            public int compare(PropositionProduitBADto o1, PropositionProduitBADto o2) {
                return o1.getLibelleProduit().compareTo(o2.getLibelleProduit());
            }
        };
        Collections.sort(listePropositionsProduitsPrevoyance, comparateurProduits);

        // Création de la proposition de la famille prévoyance si la liste des produits n'est pas vide
        PropositionFamilleProduitsBADto propositionFamillePrevoyance = null;
        if (listePropositionsProduitsPrevoyance != null && !listePropositionsProduitsPrevoyance.isEmpty()) {
            propositionFamillePrevoyance =
                new PropositionFamilleProduitsBADto("PREVOYANCE", listePropositionsProduitsPrevoyance, null, logoGammeProduitPrevoyance, null);
        }

        // Map l'id du bénéficiaire
        final Map<Long, List<Integer>> nbProduitsParBenef = new HashMap<Long, List<Integer>>();

        // On chaque bénéficiaire au nombre de produits santé dont il bénéficie
        if (propositionFamilleSante != null && propositionFamilleSante.getListePropositionsProduit() != null) {
            for (PropositionProduitBADto produitSante : propositionFamilleSante.getListePropositionsProduit()) {
                // on recupere le produit
                final ProduitLieDto produitLie = mapProduitLies.get(produitSante.getIdProduit());
                if (produitLie != null && !produitLie.getOptionnel()) {
                    continue;
                }
                for (BeneficiaireMontantBADto beneficiaire : produitSante.getListeBeneficiaires()) {
                    // On récupère l'id de la personne associée au produit et on lui ajoute
                    final Long idBenef = beneficiaire.getIdProspect() == null ? beneficiaire.getIdBeneficiaire() : beneficiaire.getIdProspect();

                    if (nbProduitsParBenef.containsKey(idBenef)) {
                        nbProduitsParBenef.get(idBenef).set(0, nbProduitsParBenef.get(idBenef).get(0) + 1);
                    }
                    // Si le bénéficiaire n'était pas encore listé, on le rajoute
                    else {
                        final List<Integer> nbProduits = new ArrayList<Integer>(2);
                        nbProduits.add(1);
                        nbProduits.add(0);
                        nbProduitsParBenef.put(idBenef, nbProduits);
                    }
                }
            }
        }

        // On fait de même avec le nombre de produits prévoyance
        if (propositionFamillePrevoyance != null && propositionFamillePrevoyance.getListePropositionsProduit() != null) {
            for (PropositionProduitBADto produitPrevoyance : propositionFamillePrevoyance.getListePropositionsProduit()) {
                for (BeneficiaireMontantBADto beneficiaire : produitPrevoyance.getListeBeneficiaires()) {
                    // On récupère l'id de la personne associée au produit et on lui ajoute
                    final Long idBenef = beneficiaire.getIdProspect() == null ? beneficiaire.getIdBeneficiaire() : beneficiaire.getIdProspect();

                    if (nbProduitsParBenef.containsKey(idBenef)) {
                        nbProduitsParBenef.get(idBenef).set(1, nbProduitsParBenef.get(idBenef).get(1) + 1);
                    }
                    // Si le bénéficiaire n'était pas encore listé, on le rajoute
                    else {
                        final List<Integer> nbProduits = new ArrayList<Integer>(2);
                        nbProduits.add(0);
                        nbProduits.add(1);
                        nbProduitsParBenef.put(idBenef, nbProduits);
                    }
                }
            }
        }

//        final Iterator<Long> it = nbProduitsParBenef.keySet().iterator();
//        String error = null;
//
//        while (it.hasNext()) {
//            final Long key = it.next();
//            final int nbProdSante = nbProduitsParBenef.get(key).get(0);
//            final int nbProdPrev = nbProduitsParBenef.get(key).get(1);
//            if (nbProdSante > 1 || nbProdSante + nbProdPrev > 3) {
//                final PersonneDto personneTarificateur = tarificateurPersonneService.getPersonneById(key);
//                final String benefError =
//                    messageSourceUtil.get(MessageKeyUtil.GENERATION_BA_PDF_IMPOSSIBLE_NB_PRODUITS_LIMITE_DEPASSE_BENEFICIAIRE, new String[] {
//                        personneTarificateur.getNom().toUpperCase(), personneTarificateur.getPrenom(), Long.toString(nbProdSante), Long.toString(nbProdPrev)});
//                if (error == null) {
//                    error = messageSourceUtil.get(MessageKeyUtil.GENERATION_BA_PDF_IMPOSSIBLE_NB_PRODUITS_LIMITE_DEPASSEE);
//                }
//                error = error + "<br />" + benefError;
//            }
//        }
//        if (error != null) {
//            throw new BusinessException(error);
//        }

        /*
         * // On autorise (0 santé + 3 prévoyances) ou (1 santé + 2 prévoyance) maximum final int nbProduitsSante = propositionFamilleSante == null ? 0 :
         * propositionFamilleSante.getListePropositionsProduit().size(); final int nbProduitsPrevoyance = propositionFamillePrevoyance == null ? 0 :
         * propositionFamillePrevoyance.getListePropositionsProduit().size(); if (nbProduitsSante > 1 || nbProduitsSante + nbProduitsPrevoyance > 3) { throw new
         * BusinessException(messageSourceUtil.get(MessageKeyUtil.GENERATION_BA_PDF_IMPOSSIBLE_NB_PRODUITS_LIMITE_DEPASSEE, new String[] {
         * String.valueOf(nbProduitsSante), String.valueOf(nbProduitsPrevoyance)})); }
         */

        // Création de la proposition
        final PropositionBADto propositionBADto = new PropositionBADto(propositionFamilleSante, propositionFamillePrevoyance);
        // Mise à jour du bulletin d'adhésion
        bulletinAdhesion.setProposition(propositionBADto);
        // Date d'effet du bonus
        bulletinAdhesion.setDateEffetBonus1(dateEffetBonus1);
    }

    /**
     * Complète le bulletin d'adhésion (infos Décès, montants totaux, bonus Eco, capital garanti).
     * @param bulletin le bulletin à compléter
     */
    private void completerBulletinAdhesion(BulletinAdhesionDto bulletin) {
        completerBulletinPourMontantsTotauxEtBonusEco(bulletin);
    }

    /**
     * Complète le bulletin d'adhésion pour renseigner les montants totaux des produits, familles et bulletin.
     * @param bulletin le bulletin à compléter
     */
    private void completerBulletinPourMontantsTotauxEtBonusEco(BulletinAdhesionDto bulletin) {
        final PropositionBADto propositionBA = bulletin.getProposition();
        final PropositionFamilleProduitsBADto propositionFamilleSante = propositionBA.getPropositionFamilleSante();
        final PropositionFamilleProduitsBADto propositionFamillePrevoyance = propositionBA.getPropositionFamillePrevoyance();

        // Libellés pour les montants totaux
        String libelleSanteMensuel = null;
        String libelleSanteUnique = null;
        String libellePrevoyanceMensuel = null;
        String libellePrevoyanceUnique = null;

        // Montants du bulletin
        boolean possedeMontantMensuelBulletin = false;
        boolean possedeMontantUniqueBulletin = false;
        float montantTotalMensuelBulletinSansRemise = 0f;
        float montantTotalMensuelRemiseBulletin = 0f;
        float montantTotalUniqueBulletinSansRemise = 0f;
        float montantTotalUniqueRemiseBulletin = 0f;

        // Code génération
        final Integer idCritereGeneration = tarificateurMappingService.getIdentifiantCritereGeneration();
        String codeGeneration = null;

        // Pourcentage du bonus éco
        int pourcentageBonusEco = 0;

        // Total de chaque produit Santé
        if (propositionFamilleSante != null) {
            boolean possedeMontantMensuelFamille = false;
            boolean possedeMontantUniqueFamille = false;
            float montantTotalMensuelFamilleSansRemise = 0f;
            float montantTotalMensuelRemiseFamille = 0f;
            float montantTotalUniqueFamilleSansRemise = 0f;
            float montantTotalUniqueRemiseFamille = 0f;
            if (propositionFamilleSante.getListePropositionsProduit() != null) {
                int indexProduit = 0;
                for (PropositionProduitBADto produit : propositionFamilleSante.getListePropositionsProduit()) {
                    float montantTotalProduitSansRemise = 0f;
                    float montantTotalRemiseProduit = 0f;
                    // Parcours de chaque bénéficiaire du produit pour ajouter son montant et déterminer le bonus éco
                    if (produit.getListeBeneficiaires() != null) {
                        for (BeneficiaireMontantBADto beneficiaireMontant : produit.getListeBeneficiaires()) {
                            // Montants (on ne prend pas en compte les montants offerts)
                            if (beneficiaireMontant.getMontantSansRemise() != null
                                && (beneficiaireMontant.getOffert() == null || !beneficiaireMontant.getOffert())) {
                                montantTotalProduitSansRemise += beneficiaireMontant.getMontantSansRemise();
                                if (produit.getFrequenceCotisation().equals(PropositionProduitBADto.FREQUENCE_COTISATION_MENSUELLE)) {
                                    libelleSanteMensuel = propositionFamilleSante.getLibelleGamme();
                                    possedeMontantMensuelFamille = true;
                                    possedeMontantMensuelBulletin = true;
                                    montantTotalMensuelFamilleSansRemise += beneficiaireMontant.getMontantSansRemise();
                                    montantTotalMensuelBulletinSansRemise += beneficiaireMontant.getMontantSansRemise();
                                }
                                else if (produit.getFrequenceCotisation().equals(PropositionProduitBADto.FREQUENCE_COTISATION_UNIQUE)) {
                                    libelleSanteUnique = propositionFamilleSante.getLibelleGamme();
                                    possedeMontantUniqueFamille = true;
                                    possedeMontantUniqueBulletin = true;
                                    montantTotalUniqueFamilleSansRemise += beneficiaireMontant.getMontantSansRemise();
                                    montantTotalUniqueBulletinSansRemise += beneficiaireMontant.getMontantSansRemise();
                                }
                            }
                            if (beneficiaireMontant.getMontantRemise() != null && (beneficiaireMontant.getOffert() == null
                            		|| !beneficiaireMontant.getOffert())) {
                                montantTotalRemiseProduit += beneficiaireMontant.getMontantRemise();
                                if (produit.getFrequenceCotisation().equals(PropositionProduitBADto.FREQUENCE_COTISATION_MENSUELLE)) {
                                    montantTotalMensuelRemiseFamille += beneficiaireMontant.getMontantRemise();
                                    montantTotalMensuelRemiseBulletin += beneficiaireMontant.getMontantRemise();
                                }
                                else if (produit.getFrequenceCotisation().equals(PropositionProduitBADto.FREQUENCE_COTISATION_UNIQUE)) {
                                    montantTotalUniqueRemiseFamille += beneficiaireMontant.getMontantRemise();
                                    montantTotalUniqueRemiseBulletin += beneficiaireMontant.getMontantRemise();
                                }
                            }
                            // Recherche du code génération
                            if (codeGeneration == null) {
                                codeGeneration = getCodeGeneration(beneficiaireMontant, idCritereGeneration);
                            }
                            // Bonus Eco
                            if (beneficiaireMontant.getPourcentageBonusEco() != null
                                && beneficiaireMontant.getPourcentageBonusEco().intValue() > pourcentageBonusEco) {
                                pourcentageBonusEco = beneficiaireMontant.getPourcentageBonusEco().intValue();
                            }
                        }
                    }
                    produit.setMontantTotalProduitSansRemise(montantTotalProduitSansRemise);
                    produit.setMontantTotalRemiseProduit(montantTotalRemiseProduit);
                    indexProduit++;
                }
            }
            // Ajout des montants totaux pour la famille Santé
            if (possedeMontantMensuelFamille) {
                propositionFamilleSante.setMontantTotalMensuelFamilleSansRemise(montantTotalMensuelFamilleSansRemise);
                propositionFamilleSante.setMontantTotalMensuelRemiseFamille(montantTotalMensuelRemiseFamille);
            }
            if (possedeMontantUniqueFamille) {
                propositionFamilleSante.setMontantTotalUniqueFamilleSansRemise(montantTotalUniqueFamilleSansRemise);
                propositionFamilleSante.setMontantTotalUniqueRemiseFamille(montantTotalUniqueRemiseFamille);
            }
        }

        // Total de chaque produit Prévoyance
        if (propositionFamillePrevoyance != null) {
            boolean possedeMontantMensuelFamille = false;
            boolean possedeMontantUniqueFamille = false;
            float montantTotalMensuelFamilleSansRemise = 0f;
            float montantTotalMensuelRemiseFamille = 0f;
            float montantTotalUniqueFamilleSansRemise = 0f;
            float montantTotalUniqueRemiseFamille = 0f;
            if (propositionFamillePrevoyance.getListePropositionsProduit() != null) {
                int indexProduit = 0;
                for (PropositionProduitBADto produit : propositionFamillePrevoyance.getListePropositionsProduit()) {
                    float montantTotalProduitSansRemise = 0f;
                    float montantTotalRemiseProduit = 0f;
                    // Parcours de chaque bénéficiaire du produit pour ajouter son montant
                    if (produit.getListeBeneficiaires() != null) {
                        for (BeneficiaireMontantBADto beneficiaireMontant : produit.getListeBeneficiaires()) {
                            if (beneficiaireMontant.getMontantSansRemise() != null
                                && (beneficiaireMontant.getOffert() == null || !beneficiaireMontant.getOffert())) {
                                montantTotalProduitSansRemise += beneficiaireMontant.getMontantSansRemise();
                                if (produit.getFrequenceCotisation().equals(PropositionProduitBADto.FREQUENCE_COTISATION_MENSUELLE)) {
                                    libellePrevoyanceMensuel = "PREVOYANCE";
                                    possedeMontantMensuelFamille = true;
                                    possedeMontantMensuelBulletin = true;
                                    montantTotalMensuelFamilleSansRemise += beneficiaireMontant.getMontantSansRemise();
                                    montantTotalMensuelBulletinSansRemise += beneficiaireMontant.getMontantSansRemise();
                                }
                                else if (produit.getFrequenceCotisation().equals(PropositionProduitBADto.FREQUENCE_COTISATION_UNIQUE)) {
                                    libellePrevoyanceUnique = "PREVOYANCE";
                                    possedeMontantUniqueFamille = true;
                                    possedeMontantUniqueBulletin = true;
                                    montantTotalUniqueFamilleSansRemise += beneficiaireMontant.getMontantSansRemise();
                                    montantTotalUniqueBulletinSansRemise += beneficiaireMontant.getMontantSansRemise();
                                }
                            }
                            if (beneficiaireMontant.getMontantRemise() != null && (beneficiaireMontant.getOffert() == null
                            		|| !beneficiaireMontant.getOffert())) {
                                montantTotalRemiseProduit += beneficiaireMontant.getMontantRemise();
                                if (produit.getFrequenceCotisation().equals(PropositionProduitBADto.FREQUENCE_COTISATION_MENSUELLE)) {
                                    montantTotalMensuelRemiseFamille += beneficiaireMontant.getMontantRemise();
                                    montantTotalMensuelRemiseBulletin += beneficiaireMontant.getMontantRemise();
                                }
                                else if (produit.getFrequenceCotisation().equals(PropositionProduitBADto.FREQUENCE_COTISATION_UNIQUE)) {
                                    montantTotalUniqueRemiseFamille += beneficiaireMontant.getMontantRemise();
                                    montantTotalUniqueRemiseBulletin += beneficiaireMontant.getMontantRemise();
                                }
                            }
                            // Recherche du code génération
                            if (codeGeneration == null) {
                                codeGeneration = getCodeGeneration(beneficiaireMontant, idCritereGeneration);
                            }
                            // Bonus Eco
                            if (beneficiaireMontant.getPourcentageBonusEco() != null
                                && beneficiaireMontant.getPourcentageBonusEco().intValue() > pourcentageBonusEco) {
                                pourcentageBonusEco = beneficiaireMontant.getPourcentageBonusEco().intValue();
                            }
                        }
                    }
                    produit.setMontantTotalProduitSansRemise(montantTotalProduitSansRemise);
                    produit.setMontantTotalRemiseProduit(montantTotalRemiseProduit);
                    indexProduit++;
                }
            }
            // Ajout des montants totaux pour la famille Prévoyance
            if (possedeMontantMensuelFamille) {
                propositionFamillePrevoyance.setMontantTotalMensuelFamilleSansRemise(montantTotalMensuelFamilleSansRemise);
                propositionFamillePrevoyance.setMontantTotalMensuelRemiseFamille(montantTotalMensuelRemiseFamille);
            }
            if (possedeMontantUniqueFamille) {
                propositionFamillePrevoyance.setMontantTotalUniqueFamilleSansRemise(montantTotalUniqueFamilleSansRemise);
                propositionFamillePrevoyance.setMontantTotalUniqueRemiseFamille(montantTotalUniqueRemiseFamille);
            }
        }

        // Ajout des montants totaux pour le bulletin
        if (possedeMontantMensuelBulletin) {
            bulletin.setMontantTotalMensuelSansRemise(montantTotalMensuelBulletinSansRemise);
            bulletin.setMontantTotalMensuelRemise(montantTotalMensuelRemiseBulletin);
        }
        if (possedeMontantUniqueBulletin) {
            bulletin.setMontantTotalUniqueSansRemise(montantTotalUniqueBulletinSansRemise);
            bulletin.setMontantTotalUniqueRemise(montantTotalUniqueRemiseBulletin);
        }

        // On renseigne le libellé mensuel
        if (libelleSanteMensuel != null && libellePrevoyanceMensuel != null) {
            bulletin.setLibelleMontantTotalMensuel(libelleSanteMensuel + " + " + libellePrevoyanceMensuel);
        }
        else if (libelleSanteMensuel != null) {
            bulletin.setLibelleMontantTotalMensuel(libelleSanteMensuel);
        }
        else if (libellePrevoyanceMensuel != null) {
            bulletin.setLibelleMontantTotalMensuel(libellePrevoyanceMensuel);
        }

        // On renseigne le libellé unique
        if (libelleSanteUnique != null && libellePrevoyanceUnique != null) {
            bulletin.setLibelleMontantTotalUnique(libelleSanteUnique + " + " + libellePrevoyanceUnique);
        }
        else if (libelleSanteUnique != null) {
            bulletin.setLibelleMontantTotalUnique(libelleSanteUnique);
        }
        else if (libellePrevoyanceUnique != null) {
            bulletin.setLibelleMontantTotalUnique(libellePrevoyanceUnique);
        }

        // Code génération
        bulletin.setCodeGeneration(codeGeneration);

        // On renseigne le libellé du bonus Eco si supérieur à 0
        if (pourcentageBonusEco > 0) {
            bulletin.setLibelleBonusEco(messageSourceUtil.get(MessageKeyUtil.MESSAGE_REMISE) + pourcentageBonusEco + "%");
        }
    }

    /**
     * Récupère la valeur de règle de stage d'une ligne de devis.
     * @param ligneDevis la ligne de devis
     * @param idRegleStage l'identifiant de la règle de stage
     * @return la valeur de règle si trouvée, null sinon
     */
    private RegleValeur getValeurRegleStageByLigneDevis(LigneDevis ligneDevis, Integer idRegleStage) {
        // Parcours des valeurs de règle de la ligne de devis
        for (RegleValeur regleValeur : ligneDevis.getListeReglesValeurs()) {
            // Si c'est une règle de stage, on la retourne
            if (regleValeur.getIdRegle().equals(idRegleStage)) {
                return regleValeur;
            }
        }
        return null;
    }

    /**
     * Retourne le délai de stage à partir d'une liste de règles valeurs.
     * @param listeReglesValeurs la liste des règles valeurs
     * @param idValeurRegleStageSmatis l'identifiant de la valeur de règle "Stage Smatis"
     * @return le délai de stage
     */
    private String getDelaiStageByReglesValeurs(List<RegleValeur> listeReglesValeurs, Integer idValeurRegleStageSmatis) {
        if (listeReglesValeurs != null) {
            // Parcours des règles valeurs
            for (RegleValeur regleValeur : listeReglesValeurs) {
                // Si c'est la valeur de règle "Stage Smatis", on retourne la valeur
                if (regleValeur.getIdValeurRegle().equals(idValeurRegleStageSmatis)) {
                    return regleValeur.getValeurRegle();
                }
            }
        }
        // Si aucun délai de stage n'a été trouvé : on renvoie "Garanties immédiates"
        return messageSourceUtil.get(MessageKeyUtil.MESSAGE_ABSCENCE_DELAIS_STAGE);
    }

    /**
     * Récupère le code génération à partir d'un bénéficiaireMontant.
     * @param beneficiaireMontant le bénéficiaireMontant
     * @param idCritereGeneration l'identifiant du critère "Génération"
     * @return la valeur du critère "Génération"
     */
    private String getCodeGeneration(BeneficiaireMontantBADto beneficiaireMontant, Integer idCritereGeneration) {
        // On recherche le code génération sur le prospect
        if (beneficiaireMontant.getIdProspect() != null) {
            for (CritereProduitBADto critere : beneficiaireMontant.getListeCriteres()) {
                if (critere.getIdCritere().equals(idCritereGeneration)) {
                    return critere.getAffichageValeur();
                }
            }
        }
        return null;
    }

    /**
     * Créer le bénéficiaire et le montant d'une ligne de devis.
     * @param ligneDevis la ligne de devis associée au bénéficiaire
     * @param prospect le prospect (pour définir le prospect comme bénéficiaire si le bénéficiaire est null)
     * @return le beneficiaireMontant
     */
    private BeneficiaireMontantBADto creerBeneficiaireMontant(LigneDevis ligneDevis, ProspectBADto prospect, Map<Integer, ProduitLieDto> mapProduitLies) {
    	boolean afficherObligatoire = false;
    	if (mapProduitLies != null && mapProduitLies.containsKey(ligneDevis.getEidProduit())) {
    		afficherObligatoire = !mapProduitLies.get(ligneDevis.getEidProduit()).getOptionnel() 
    			&& mapProduitLies.get(ligneDevis.getEidProduit()).getAffichageDevis();
    	}
        // Calcul du montant offert
        float montantOffert = 0;
        if (ligneDevis.getMontantTtc() != null) {
            montantOffert += ligneDevis.getMontantTtc();
        }
        if (ligneDevis.getMontantRemise() != null) {
            montantOffert -= ligneDevis.getMontantRemise();
        }

        // Création des critères
        final Set<ValeurCritereLigneDevis> listeValeursCriteres = ligneDevis.getListeValeurCritereLigneDevis();
        final List<CritereProduitBADto> listeCriteresProduits = new ArrayList<CritereProduitBADto>();
        // RECHERCHE DU CRITERE AGE_MIN ET MONTANT_MIN
        final Integer idCritereAgeMin = tarificateurMappingService.getIdentifiantCritereAgeMin();
        final Integer idCritereAgeMax = tarificateurMappingService.getIdentifiantCritereAgeMax();
        final Integer idCritereMontantMin = tarificateurMappingService.getIdentifiantCritereMontant();
        final Integer idCritereMontantMax = tarificateurMappingService.getIdentifiantCritereMontantDeux();
        final Integer idCritereGarantieSouscrite = tarificateurMappingService.getIdentifiantCritereGarantieSouscrite();
        boolean critereMontantMinPresent = false;
        for (ValeurCritereLigneDevis valeurCritere : listeValeursCriteres) {
            if (valeurCritere.getEidCritere().equals(idCritereMontantMin)) {
                critereMontantMinPresent = true;
            }
        }
        // Parcours de la liste des valeurs critères pour créer les critères de produits du devis
        for (ValeurCritereLigneDevis valeurCritere : listeValeursCriteres) {
            if (!(valeurCritere.getEidCritere().equals(idCritereAgeMin) || valeurCritere.getEidCritere().equals(idCritereAgeMax))
                && !(valeurCritere.getEidCritere().equals(idCritereMontantMax) && critereMontantMinPresent)) {
                // Récupération du libellé du critère par le service
                final CritereCriteresDto critereCriteresDto = new CritereCriteresDto();
                critereCriteresDto.setIdentifiantProduit(ligneDevis.getEidProduit());
                critereCriteresDto.setIdentifiantCritere(valeurCritere.getEidCritere());
                critereCriteresDto.setIdentifiantApplication(tarificateurMappingService.getIdentifiantApplicationGestcom());
                critereCriteresDto.setChargerValeursPossibles(false);
                final List<CritereDto> listeCriteres = produitService.getListeCriteresParProduit(critereCriteresDto);
                if (listeCriteres.size() != 1) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_GENERATION_PDF_IMPOSSIBLE,
                    		 new String[] {String.valueOf(valeurCritere.getEidCritere()), String.valueOf(listeCriteres.size())}));
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.GENERATION_PDF_IMPOSSIBLE));
                }
                final Integer idCritere = listeCriteres.get(0).getIdentifiant();
                // Bug 1116 : on n'affiche pas le critère garantie soucrite
                final Boolean afficherCritere = !idCritere.equals(idCritereGarantieSouscrite);
                // Création du critère
                final CritereProduitBADto critereProduit =
                    new CritereProduitBADto(idCritere, listeCriteres.get(0).getLibelle(), valeurCritere.getValeur(), valeurCritere.getAffichageValeur(),
                        afficherCritere);
                // Ajout du critère à la liste
                listeCriteresProduits.add(critereProduit);
            }
        }
        // On ordonne les critères
        final Comparator<CritereProduitBADto> comparateurCriteres = new Comparator<CritereProduitBADto>() {
            public int compare(CritereProduitBADto o1, CritereProduitBADto o2) {
                return o1.getIdCritere().compareTo(o2.getIdCritere());
            }
        };
        Collections.sort(listeCriteresProduits, comparateurCriteres);

        // Recherche d'un bonus éco
        int pourcentageBonusEco = 0;
        final Integer idRegleReductionSelonProduit = regleMappingService.getIdRegleReductionSelonProduit();
        // On teste si la ligne de devis possède la règle "Réduction selon le produit"
        // Parcours des Régles
        for (RegleValeur regleValeur : ligneDevis.getListeReglesValeurs()) {
            if (regleValeur.getIdRegle().equals(idRegleReductionSelonProduit)) {
                // Récupération du pourcentage de la remise la plus forte
                if (Integer.parseInt(regleValeur.getValeurRegle()) > pourcentageBonusEco) {
                    pourcentageBonusEco = Integer.parseInt(regleValeur.getValeurRegle());
                }
            }
        }

        // L'identifiant du bénéficiaire est celui du prospect si le bénéficiaire est null
        final Long idProspect;
        final Long idBeneficiaire;
        // Prospect
        if (ligneDevis.getBeneficiaire() == null) {
            idProspect = ligneDevis.getDevis().getPersonnePrincipale().getId();
            idBeneficiaire = null;
        }
        else {
            // Bénéficiaire
            idProspect = null;
            idBeneficiaire = ligneDevis.getBeneficiaire().getId();
        }
        // La date d'effet est celle de la ligne de devis
        final Calendar dateEffet = ligneDevis.getDateEffet();
        // Création du BeneficiaireMontant
        return new BeneficiaireMontantBADto(idProspect, idBeneficiaire, ligneDevis.getMontantTtc(), ligneDevis.getMontantRemise(), dateEffet,
            listeCriteresProduits, pourcentageBonusEco != 0 ? pourcentageBonusEco : null, montantOffert == 0f && ligneDevis.getMontantTtc() > 0f);
    }

    /**
     * Récupère le montant du capital garanti à partir du produit.
     * @param produit le produit
     * @return la valeur du montant du capital
     */
    @SuppressWarnings("unused")
	private Float getMontantCapitalGarantiParProduit(PropositionProduitBADto produit) {
        final Integer idCriterePrimeSouscrite = tarificateurMappingService.getIdentifiantCriterePrimeSouscrite();

        // On récupère le 1er bénéficiaireMontant du produit (tous les bénéficiaires ont le même montant de garantie)
        if (produit.getListeBeneficiaires() != null) {
            // Parcours des valeurs de critères
            for (CritereProduitBADto critere : produit.getListeBeneficiaires().get(0).getListeCriteres()) {
                // Recherche du critère Prime souscrite
                if (critere.getIdCritere().equals(idCriterePrimeSouscrite)) {
                    try {
                        final Float valeur = Float.valueOf(critere.getValeur());
                        return valeur;
                    }
                    catch (NumberFormatException e) {
                        logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_MONTANT_CAPITAL_GARANTI_BAD_FORMAT));
                        return null;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Génère le nom d'un fichier PDF avec un timestamp et l'extension.
     * @param nomFichier le nom du fichier
     * @return le nom de fichier complet
     */
    private String getNomFichierPdf(String nomFichier) {
        final StringBuffer nom = new StringBuffer(nomFichier);
        nom.append("_").append(getTimeStamp()).append(messageSourceUtil.get(MessageKeyUtil.EXTENSION_FICHIER_PDF));
        return nom.toString();
    }

    /**
     * Génère un timestamp.
     * @return le timestamp
     */
    private String getTimeStamp() {
        final Calendar maintenant = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat(messageSourceUtil.get(MessageKeyUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
        return sdf.format(maintenant.getTime());
    }

    /**
     * Construit une liste de FichierDto à partir d'une liste de FichierModele.
     * @param listeFichiersModeles la liste des FichierModele
     * @return la liste des FichierDto
     */
    private List<FichierDto> getListeFichiersByListeFichiersModeles(List<FichierModeleBean> listeFichiersModeles) {
        final List<FichierDto> listeFichiers = new ArrayList<FichierDto>();
        if (listeFichiersModeles != null) {
            for (FichierModeleBean fichierModele : listeFichiersModeles) {
                if (fichierModele.getFichierDto() != null) {
                    listeFichiers.add(fichierModele.getFichierDto());
                }
            }
        }
        return listeFichiers;
    }

    /**
     * Setter function.
     * @param logger the logger to set
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
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
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Setter function.
     * @param beneficiaireDao the beneficiaireDao to set
     */
    public void setBeneficiaireDao(BeneficiaireDao beneficiaireDao) {
        this.beneficiaireDao = beneficiaireDao;
    }

    /**
     * Setter function.
     * @param devisDao the devisDao to set
     */
    public void setDevisDao(DevisDao devisDao) {
        this.devisDao = devisDao;
    }

    /**
     * Setter function.
     * @param ligneDevisDao the ligneDevisDao to set
     */
    public void setLigneDevisDao(LigneDevisDao ligneDevisDao) {
        this.ligneDevisDao = ligneDevisDao;
    }

    /**
     * Setter function.
     * @param tarificateurSquareMappingService the tarificateurSquareMappingService to set
     */
    public void setTarificateurSquareMappingService(TarificateurSquareMappingService tarificateurSquareMappingService) {
        this.tarificateurSquareMappingService = tarificateurSquareMappingService;
    }

    /**
     * Setter function.
     * @param produitService the produitService to set
     */
    public void setProduitService(ProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * Setter function.
     * @param tarificateurMappingService the tarificateurMappingService to set
     */
    public void setTarificateurMappingService(TarificateurMappingService tarificateurMappingService) {
        this.tarificateurMappingService = tarificateurMappingService;
    }

    /**
     * Setter function.
     * @param regleMappingService the regleMappingService to set
     */
    public void setRegleMappingService(RegleMappingService regleMappingService) {
        this.regleMappingService = regleMappingService;
    }

    /**
     * Setter function.
     * @param editiqueMappingService the editiqueMappingService to set
     */
    public void setEditiqueMappingService(EditiqueMappingService editiqueMappingService) {
        this.editiqueMappingService = editiqueMappingService;
    }

    /**
     * Setter function.
     * @param editiqueService the editiqueService to set
     */
    public void setEditiqueService(EditiqueService editiqueService) {
        this.editiqueService = editiqueService;
    }

    /**
     * Setter function.
     * @param utilisateurMappingService the utilisateurMappingService to set
     */
    public void setUtilisateurMappingService(UtilisateurMappingService utilisateurMappingService) {
        this.utilisateurMappingService = utilisateurMappingService;
    }

    /**
     * Setter function.
     * @param agenceService the agenceService to set
     */
    public void setAgenceService(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    /**
     * Setter function.
     * @param dimensionService the dimensionService to set
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * Set the devisUtil value.
     * @param devisUtil the devisUtil to set
     */
    public void setDevisUtil(DevisUtil devisUtil) {
        this.devisUtil = devisUtil;
    }

    /**
     * Définit la valeur de mailService.
     * @param mailService la nouvelle valeur de mailService
     */
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * Définit la valeur de envoiEmailMappingService.
     * @param envoiEmailMappingService la nouvelle valeur de envoiEmailMappingService
     */
    public void setEnvoiEmailMappingService(EnvoiEmailMappingService envoiEmailMappingService) {
        this.envoiEmailMappingService = envoiEmailMappingService;
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définit la valeur de personneUtil.
     * @param personneUtil la nouvelle valeur de personneUtil
     */
    public void setPersonneUtil(PersonneUtil personneUtil) {
        this.personneUtil = personneUtil;
    }

    /**
     * Définit la valeur de opportuniteUtil.
     * @param opportuniteUtil la nouvelle valeur de opportuniteUtil
     */
    public void setOpportuniteUtil(OpportuniteUtil opportuniteUtil) {
        this.opportuniteUtil = opportuniteUtil;
    }

    /**
     * Définit la valeur de personneService.
     * @param personneService la nouvelle valeur de personneService
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Définit la valeur de tarificateurPersonneService.
     * @param tarificateurPersonneService the tarificateurPersonneService to set
     */
    public void setTarificateurPersonneService(TarificateurPersonneService tarificateurPersonneService) {
        this.tarificateurPersonneService = tarificateurPersonneService;
    }

    /**
     * Définit la valeur de synchroAppJmsSender.
     * @param synchroAppJmsSender the synchroAppJmsSender to set
     */
    public void setSynchroAppJmsSender(SynchroAppJmsSender synchroAppJmsSender) {
        this.synchroAppJmsSender = synchroAppJmsSender;
    }
}
