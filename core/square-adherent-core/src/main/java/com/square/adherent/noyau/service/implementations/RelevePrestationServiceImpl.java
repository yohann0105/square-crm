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

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.CoreRunTimeException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.square.adherent.noyau.dao.interfaces.OptionDao;
import com.square.adherent.noyau.dao.interfaces.prestation.RelevePrestationDao;
import com.square.adherent.noyau.dto.fichier.FichierDto;
import com.square.adherent.noyau.dto.prestation.CritereSelectionRelevePrestationDto;
import com.square.adherent.noyau.dto.prestation.ParamRelevePrestationMailDto;
import com.square.adherent.noyau.dto.prestation.RelevePrestationDto;
import com.square.adherent.noyau.model.data.espace.client.Option;
import com.square.adherent.noyau.model.data.prestation.RelevePrestation;
import com.square.adherent.noyau.model.dimension.RelevePrestationMoyenPaiement;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.RelevePrestationService;
import com.square.adherent.noyau.util.MessageKeyUtil;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.mail.core.dto.emails.EmailAvecModeleDto;
import com.square.mail.core.dto.emails.InfosModeleEmailDto;
import com.square.mail.core.dto.emails.MailDto;
import com.square.mail.core.dto.emails.PieceJointeFileDto;
import com.square.mail.core.service.interfaces.email.EnvoiEmailMappingService;
import com.square.mail.core.service.interfaces.email.MailService;

/**
 * Implémentation de RelevePrestationService.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class RelevePrestationServiceImpl implements RelevePrestationService {

    /** DAO Releve de prestation. */
    private RelevePrestationDao relevePrestationDao;

    private OptionDao optionDao;

    private PersonneService personneService;

    private SquareMappingService squareMappingService;

    private PersonnePhysiqueService personnePhysiqueService;

    private String emailSurveillance;

    private AdherentMappingService adherentMappingService;

    private MailService mailService;

    /** MapperDozerBean. */
    private MapperDozerBean mapperDozerBean;

    private EnvoiEmailMappingService envoiEmailMappingService;

    /** Logger. */
    private Logger logger = Logger.getLogger(RelevePrestationServiceImpl.class);

    /** serveur emc. */
    private String serveurEmcRepReleve;

    /** Niveau de transparence sur le pdf. */
    private static final float NIVEAU_TRANSPARENCE = 0.25f;

    /** MessageSourceUtil. */
    private MessageSourceUtil messageSourceUtil;

    /** Nom du fichier temporaire du duplicata. */
    private static final String FICHIER_DUPLICATA_TEMP = "duplicata.pdf";

    /** Nombre maximum de relevés pour une personne. */
    private static final int NB_RELEVE_MAX = 3;

    @Override
    public List<RelevePrestationDto> getListeReleveParCriteres(CritereSelectionRelevePrestationDto critereSelectionRelevePrestationDto) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_RELEVE_PRESTATION_CRITERES));
        final List<RelevePrestation> listeReleves = relevePrestationDao.getListeReleveParCriteres(critereSelectionRelevePrestationDto, false);
        return mapperDozerBean.mapList(listeReleves, RelevePrestationDto.class);
    }

    @Override
    public List<RelevePrestationDto> getListeReleveParCriteres(CritereSelectionRelevePrestationDto critereSelectionRelevePrestationDto, Boolean triDesc) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_RELEVE_PRESTATION_CRITERES));
        final List<RelevePrestation> listeReleves = relevePrestationDao.getListeReleveParCriteres(critereSelectionRelevePrestationDto, triDesc);
        return mapperDozerBean.mapList(listeReleves, RelevePrestationDto.class);
    }

    /** {@inheritDoc} */
    public void envoyerParMailRelevesPrestations(ParamRelevePrestationMailDto paramRelevePrestationMailDto) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_ENVOIE_RELEVES_PRESTATIONS_EMAIL,
        		new String[] {String.valueOf(paramRelevePrestationMailDto.getIdPersonne())}));

        final PersonneDto adherent = personnePhysiqueService.rechercherPersonneParIdentifiant(paramRelevePrestationMailDto.getIdPersonne());
        if (adherent == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_ADHERENT_NON_TROUVE,
            		new String[] {String.valueOf(paramRelevePrestationMailDto.getIdPersonne())}));
        }

        final Long idTypeOption = adherentMappingService.getIdTypeOptionEnvoiRelevesPrestationEmail();
        if (!optionDao.isPersonnePossedeOption(adherent.getIdentifiant(), idTypeOption)) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_OPTION_ENVOIE_MAIL_DESACTIVER));
        }

        final CritereSelectionRelevePrestationDto critereSelectionRelevePrestationDto = new CritereSelectionRelevePrestationDto();
        critereSelectionRelevePrestationDto.setIdPersonne(adherent.getIdentifiant());
        critereSelectionRelevePrestationDto.setAutorisationEnvoiMail(true);

        // Définition de la date minimale d'impression en fonction de la date de souscription à l'option

        final Option option = optionDao.getOptionByPersonneAndType(adherent.getIdentifiant(), idTypeOption);
        final Calendar dateMinImpression = option.getDateDebut();
        critereSelectionRelevePrestationDto.setDateMinImpression(dateMinImpression);

        // Si l'id du relevé n'a pas été renseigné (donc = null), il ne sera pas en paramètre de la recherche.
        critereSelectionRelevePrestationDto.setRelevePrestationId(paramRelevePrestationMailDto.getRelevePrestationId());

        // Si le paramètre ForceEnvoyeMail est actif, on envoie les relevés même s'ils ont déjà été envoyés.
        if (!paramRelevePrestationMailDto.isForceEnvoyeMail()) {
            critereSelectionRelevePrestationDto.setEnvoyeMail(false);
            final Calendar today = Calendar.getInstance();
            final int jour = today.get(Calendar.DAY_OF_MONTH) - 3;
            today.set(Calendar.DAY_OF_MONTH, jour);
            critereSelectionRelevePrestationDto.setDateMaxImpression(today);
        }

        final List<RelevePrestation> releves = relevePrestationDao.getListeReleveParCriteres(critereSelectionRelevePrestationDto, null);
        if (releves == null || releves.size() == 0) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_ABSCENCE_RELEVE_A_ENVOYER));
        }

        // création du mail
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(adherent.getIdentifiant());
        String emailAdherent = null;
        for (EmailDto email : coordonnees.getEmails()) {
            if (squareMappingService.getIdNatureEmailPersonnel().equals(email.getNatureEmail().getIdentifiant())) {
                emailAdherent = email.getAdresse();
                break;
            }
        }
        if (emailAdherent == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_ABSCENCE_ADRESSE_EMAIL_ADHERENT,
            		new String[] {String.valueOf(paramRelevePrestationMailDto.getIdPersonne())}));
        }
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_EMAIL_ADHERENT,
        		new String[] {emailAdherent}));

        // Ajout pièces jointes
        final List<PieceJointeFileDto> listePiecesJointes = new ArrayList<PieceJointeFileDto>();
        for (int i = 0; i < releves.size(); i++) {
            final int index = i;
            final Calendar dateDuJour = Calendar.getInstance();
            try {
                final FichierDto releveAJoindre = getRelevePrestationByteArray(releves.get(index).getId(), false);
                if (releveAJoindre != null) {
                    final PieceJointeFileDto pieceJointe =
                        new PieceJointeFileDto(releveAJoindre.getNomFichier(), releveAJoindre.getTypeMime(), releveAJoindre.getContenu());
                    listePiecesJointes.add(pieceJointe);
                    releves.get(index).setDateEnvoiMail(dateDuJour);
                    releves.get(index).setEnvoyeMail(true);
                    logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MAJ_TEMOIN_ENVOI_RELEVE_PRESTATION,
                    		new String[] {releves.get(i).getNomFichier()}));
                }
            }
            catch (CoreRunTimeException e) {
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_RECUPERATION_RELEVE_PRESTATION,
                		new String[] {serveurEmcRepReleve, File.separator, releves.get(index).getNomFichier()}));
                throw new TechnicalException(e.getMessage());
            }
        }

        final MailDto mailToAdherentDto = new MailDto();
        mailToAdherentDto.setListePiecesJointes(listePiecesJointes);
        mailToAdherentDto.ajouterDestinataireBcc(emailSurveillance);

        final InfosModeleEmailDto infosModele = new InfosModeleEmailDto();
        infosModele.setIdModeleEmail(envoiEmailMappingService.getIdModeleEnvoiRelevePrestations());
        infosModele.setEmailDestinataire(emailAdherent);
        infosModele.setCiviliteDestinataire(adherent.getCivilite().getLibelle());
        infosModele.setNomDestinataire(adherent.getNom().toUpperCase());

        final EmailAvecModeleDto emailAvecModeleDto = new EmailAvecModeleDto();
        emailAvecModeleDto.setEmail(mailToAdherentDto);
        emailAvecModeleDto.setInfosModele(infosModele);

        // On envoie le relevé de prestation par email en pièce jointe à l'adhérent
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_ENVOIE_RELEVE_PRESTATION_MAIL,
        		new String[] {String.valueOf(listePiecesJointes.size())}));
        mailService.envoyerMailDepuisModele(emailAvecModeleDto);
    }

    @Override
    public FichierDto getRelevePrestationByteArray(Long idRelevePrestation, boolean duplicata) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CONVERSION_RELEVE_PRESTATION,
        		new String[] {String.valueOf(idRelevePrestation)}));
        return getRelevePrestationByteArray(idRelevePrestation, null, duplicata);
    }

    @Override
    public FichierDto getRelevePrestationByteArray(Long idRelevePrestation, Long idPersonne, boolean duplicata) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CONVERSION_RELEVE_PRESTATION,
        		new String[] {String.valueOf(idRelevePrestation)}));
        final CritereSelectionRelevePrestationDto critereSelectionRelevePrestationDto = new CritereSelectionRelevePrestationDto();
        critereSelectionRelevePrestationDto.setRelevePrestationId(idRelevePrestation);
        if (idPersonne != null) {
            critereSelectionRelevePrestationDto.setIdPersonne(idPersonne);
        }
        final List<RelevePrestation> lstReleves = relevePrestationDao.getListeReleveParCriteres(critereSelectionRelevePrestationDto, null);
        if (lstReleves.size() == 1) {
            final RelevePrestation releve = lstReleves.get(0);
            final String error = messageSourceUtil.get(MessageKeyUtil.ERROR_RECUPERATION_FICHIER);
            FichierDto fichier;
            final String cheminFichier = serveurEmcRepReleve + File.separator + releve.getNomFichier();
            try {
                fichier = new FichierDto();
                fichier.setNomFichier(releve.getNomFichierCommercial());
                if (duplicata) {
                    // On appose la mention "DUPLICATA" sur toutes les pages du relevé.
                    try {
                        final PdfReader reader = new PdfReader(cheminFichier);
                        final int nombrePages = reader.getNumberOfPages();
                        final BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLDOBLIQUE, BaseFont.WINANSI, BaseFont.EMBEDDED);
                        final PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(FICHIER_DUPLICATA_TEMP));
                        final int taillePolice = 56;
                        final int positionX = ((int) PageSize.A4.getWidth()) / 2;
                        final int positionY = ((int) PageSize.A4.getHeight()) / 2;
                        final int rotation = 30;
                        for (int i = 1; i <= nombrePages; i++) {
                            final PdfContentByte over = stamp.getOverContent(i);
                            over.beginText();
                            over.setColorFill(Color.GRAY);
                            final PdfGState gs1 = new PdfGState();
                            gs1.setFillOpacity(NIVEAU_TRANSPARENCE);
                            over.setGState(gs1);
                            over.setFontAndSize(bf, taillePolice);
                            over.showTextAligned(PdfContentByte.ALIGN_CENTER, "DUPLICATA", positionX, positionY, rotation);
                            over.endText();
                        }
                        stamp.close();
                        reader.close();
                        fichier.setContenu(IOUtils.toByteArray(new FileInputStream(FICHIER_DUPLICATA_TEMP)));
                        final File file = new File(FICHIER_DUPLICATA_TEMP);
                        file.delete();
                    }
                    catch (DocumentException e) {
                        throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_IMPOSSIBLE_AJOUTER_MENTION_DUPLICATA,
                        		new String[] {releve.getNomFichier()}));
                    }
                }
                else {
                    fichier.setContenu(IOUtils.toByteArray(new FileInputStream(cheminFichier)));
                }
                fichier.setTypeMime(Magic.getMagicMatch(fichier.getContenu()).getMimeType());
            }
            catch (FileNotFoundException e) {
                logger.error(error + releve.getNomFichier(), e);
                throw new TechnicalException(error + cheminFichier);
            }
            catch (IOException e) {
                logger.error(error + releve.getNomFichier(), e);
                throw new TechnicalException(error + cheminFichier);
            }
            catch (MagicParseException e) {
                logger.error(error + releve.getNomFichier(), e);
                throw new TechnicalException(error + cheminFichier);
            }
            catch (MagicMatchNotFoundException e) {
                logger.error(error + releve.getNomFichier(), e);
                throw new TechnicalException(error + cheminFichier);
            }
            catch (MagicException e) {
                logger.error(error + releve.getNomFichier(), e);
                throw new TechnicalException(error + cheminFichier);
            }
            return fichier;
        }
        else {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_ABSCENCE_RELEVE_PRESTATION_PERSONNE));
        }
    }

    @Override
    public Integer getNombreCandidatsEnvoiRelevesPrestationEmail() {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_COMPTE_NOMBRE_CANDIDAT_ENVOIE_MAIL));
        return relevePrestationDao.getNombreCandidatsEnvoiRelevesPrestationEmail(adherentMappingService.getNombreJoursDifferesEnvoiMail(),
            adherentMappingService.getIdNatureConnexionEspaceClient(), adherentMappingService.getIdTypeOptionEnvoiRelevesPrestationEmail());
    }

    @Override
    public List<Long> getCandidatsEnvoiRelevesPrestationEmail(int firstResult, int maxResult, List<Long> listeIdsExclus) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUPERATION_CANDIDAT_ENVOIE_RELEVE_PRESTATION_MAIL));
        return relevePrestationDao.getCandidatsEnvoiRelevesPrestationEmail(firstResult, maxResult, listeIdsExclus, adherentMappingService
                .getNombreJoursDifferesEnvoiMail(), adherentMappingService.getIdNatureConnexionEspaceClient(), adherentMappingService
                .getIdTypeOptionEnvoiRelevesPrestationEmail());
    }

    @Override
    public void ajouterRelevePrestation(String nomFichier) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_AJOUT_RELEVE_PRESTATION,
        		new String[] {nomFichier}));

        final SimpleDateFormat sdf = new SimpleDateFormat(messageSourceUtil.get(MessageKeyUtil.FORMAT_DATE_YYYYMMDD));
        final String nomFichierDossier = sdf.format(Calendar.getInstance().getTime()) + File.separator + nomFichier;
        // nomFichier = N° adhérent + "_" + Date au format AAAAMMJJ + "_" + Mutuelle + "_" + Mode de paiement.pdf
        if (!relevePrestationDao.existe(nomFichierDossier)) {
            final String[] datas = nomFichier.toLowerCase().split("_");
            if (datas.length == 4) {
                final String numeroAdherent = datas[0];
                // Récupération de l'identifiant de la personne à partir du numéro d'adhérent.
                final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
                criterias.setNumeroClient(numeroAdherent);
                final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
                    new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);
                final RemotePagingResultsDto<PersonneSimpleDto> resultats = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteres);
                if (resultats == null || resultats.getTotalResults() != 1) {
                    if (resultats == null) {
                        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_IMPOSSIBLE_AJOUTER_RELEVE_PRESTATION_RESULTAT_NULL));
                    }
                    else {
                        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_IMPOSSIBLE_AJOUTER_RELEVE_PRESTATION_NB_RESULTAT,
                        		new String[] {String.valueOf(resultats.getTotalResults()), String.valueOf(resultats.getListResults().size())}));
                    }
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_IMPOSSIBLE_AJOUTER_RELEVE_PRESTATION_ABSCENCE_PERSONNE,
                    		new String[] {String.valueOf(numeroAdherent)}));
                }
                final PersonneSimpleDto personne = resultats.getListResults().get(0);
                final RelevePrestation relevePresta = new RelevePrestation();
                relevePresta.setUidPersonne(personne.getId());
                relevePresta.setNomFichier(nomFichierDossier);
                relevePresta.setNomFichierCommercial(messageSourceUtil.get(MessageKeyUtil.MESSAGE_NOM_FICHIER) + datas[1] + ".pdf");
                final char[] annee = new char[4];
                datas[1].getChars(0, 4, annee, 0);
                final int anneeInt = Integer.parseInt(String.copyValueOf(annee));
                final char[] mois = new char[2];
                datas[1].getChars(4, 6, mois, 0);
                final int moisInt = Integer.parseInt(String.copyValueOf(mois));
                final char[] jour = new char[2];
                datas[1].getChars(6, 8, jour, 0);
                final int jourInt = Integer.parseInt(String.copyValueOf(jour));
                final Calendar dateImpression = Calendar.getInstance();
                dateImpression.clear();
                dateImpression.set(anneeInt, moisInt - 1, jourInt);
                relevePresta.setDateImpression(dateImpression);
                relevePresta.setEnvoyeMail(false);
                final String paiementEid = datas[3].split(".pdf")[0];
                final RelevePrestationMoyenPaiement paiement = relevePrestationDao.getMoyenPaiementByEid(paiementEid);
                if (paiement == null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MOYEN_PAIEMENT_INCONNU,
                    		new String[] {String.valueOf(paiementEid)}));
                }
                else {
                    relevePresta.setMoyenPaiement(paiement);
                }

                // On récupère la liste des relevés d'un adhérent.
                final CritereSelectionRelevePrestationDto critere = new CritereSelectionRelevePrestationDto();
                critere.setIdPersonne(personne.getId());
                final List<RelevePrestation> releves = relevePrestationDao.getListeReleveParCriteres(critere, true);
                if (releves.size() >= NB_RELEVE_MAX) {
                    // On supprime le relevé le plus ancien.
                    relevePrestationDao.deleteRelevePresta(releves.get(NB_RELEVE_MAX - 1));
                }
                relevePrestationDao.createRelevePresta(relevePresta);
            }
        }
        else {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CREATION_FICHIER_EXISTE_BASE,
            		new String[] {nomFichier}));
        }
    }

    /**
     * Définit la valeur de relevePrestationDao.
     * @param relevePrestationDao la nouvelle valeur de relevePrestationDao
     */
    public void setRelevePrestationDao(RelevePrestationDao relevePrestationDao) {
        this.relevePrestationDao = relevePrestationDao;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de serveurEmcRepReleve.
     * @param serveurEmcRepReleve la nouvelle valeur de serveurEmcRepReleve
     */
    public void setServeurEmcRepReleve(String serveurEmcRepReleve) {
        this.serveurEmcRepReleve = serveurEmcRepReleve;
    }

    /**
     * Définition de optionDao.
     * @param optionDao the optionDao to set
     */
    public void setOptionDao(OptionDao optionDao) {
        this.optionDao = optionDao;
    }

    /**
     * Définition de personnePhysiqueService.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Set the emailSurveillance value.
     * @param emailSurveillance the emailSurveillance to set
     */
    public void setEmailSurveillance(String emailSurveillance) {
        this.emailSurveillance = emailSurveillance;
    }

    /**
     * Set the personneService value.
     * @param personneService the personneService to set
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Set the squareMappingService value.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Set the mailService value.
     * @param mailService the mailService to set
     */
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * Set the envoiEmailMappingService value.
     * @param envoiEmailMappingService the envoiEmailMappingService to set
     */
    public void setEnvoiEmailMappingService(EnvoiEmailMappingService envoiEmailMappingService) {
        this.envoiEmailMappingService = envoiEmailMappingService;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

	/**Set messageSourceUtil.
	 * @param messageSourceUtil the messageSourceUtil to set
	 */
	public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
		this.messageSourceUtil = messageSourceUtil;
	}

}
