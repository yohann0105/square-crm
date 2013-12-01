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
package com.square.tarificateur.noyau.util.devis;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.adherent.noyau.service.interfaces.AdherentService;
import com.square.core.model.dto.OpportuniteMaJStatutDto;
import com.square.core.model.dto.OpportuniteSimpleDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.price.core.dto.ContrainteVenteDto;
import com.square.price.core.dto.CritereDto;
import com.square.price.core.dto.ListeProduitsAdherentDto;
import com.square.price.core.dto.ModePaiementDto;
import com.square.price.core.dto.ProduitAdherentDto;
import com.square.price.core.dto.ProduitCriteresDto;
import com.square.price.core.dto.ProduitDto;
import com.square.price.core.dto.ProduitLieCriteresDto;
import com.square.price.core.dto.ProduitLieDto;
import com.square.price.core.dto.TarifCritereDto;
import com.square.price.core.dto.TarifCriteresDto;
import com.square.price.core.dto.TarifReponsesDto;
import com.square.price.core.dto.regles.CritereRegleDto;
import com.square.price.core.dto.regles.ListeObjetsParDiscriminantDto;
import com.square.price.core.dto.regles.ReglesRechercheDto;
import com.square.price.core.dto.regles.ReglesReponseDto;
import com.square.price.core.dto.regles.ResultatRegleDto;
import com.square.price.core.service.interfaces.ProduitService;
import com.square.price.core.service.interfaces.RegleMappingService;
import com.square.price.core.service.interfaces.RegleService;
import com.square.price.core.service.interfaces.TarifService;
import com.square.price.core.service.interfaces.TarificateurMappingService;
import com.square.tarificateur.noyau.bean.LigneDevisPourRegle;
import com.square.tarificateur.noyau.bean.PropositionLigneDevis;
import com.square.tarificateur.noyau.bean.opportunite.InfosOpportuniteSquareBean;
import com.square.tarificateur.noyau.bean.personne.InfosPersonneSquareBean;
import com.square.tarificateur.noyau.dao.interfaces.BeneficiaireDao;
import com.square.tarificateur.noyau.dao.interfaces.DevisDao;
import com.square.tarificateur.noyau.dao.interfaces.FinaliteDao;
import com.square.tarificateur.noyau.dao.interfaces.LigneDevisDao;
import com.square.tarificateur.noyau.dao.interfaces.MotifDevisDao;
import com.square.tarificateur.noyau.dao.interfaces.PersonneDao;
import com.square.tarificateur.noyau.dao.interfaces.SourceLigneDevisDao;
import com.square.tarificateur.noyau.dto.devis.ClotureDevisQueryDto;
import com.square.tarificateur.noyau.dto.devis.ClotureDevisQueryLigneDto;
import com.square.tarificateur.noyau.dto.devis.LigneDevisDto;
import com.square.tarificateur.noyau.dto.devis.ValeurCritereLigneDevisDto;
import com.square.tarificateur.noyau.dto.personne.BeneficiaireDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.model.opportunite.Devis;
import com.square.tarificateur.noyau.model.opportunite.Finalite;
import com.square.tarificateur.noyau.model.opportunite.LigneDevis;
import com.square.tarificateur.noyau.model.opportunite.LigneDevisLiee;
import com.square.tarificateur.noyau.model.opportunite.MotifDevis;
import com.square.tarificateur.noyau.model.opportunite.Opportunite;
import com.square.tarificateur.noyau.model.opportunite.RegleValeur;
import com.square.tarificateur.noyau.model.opportunite.ValeurCritereLigneDevis;
import com.square.tarificateur.noyau.model.personne.Beneficiaire;
import com.square.tarificateur.noyau.model.personne.Personne;
import com.square.tarificateur.noyau.service.interfaces.TarificateurSquareMappingService;
import com.square.tarificateur.noyau.util.DateUtil;
import com.square.tarificateur.noyau.util.MessageKeyUtil;
import com.square.tarificateur.noyau.util.opportunite.OpportuniteUtil;
import com.square.tarificateur.noyau.util.personne.PersonneUtil;

/**
 * Classe utilitaire pour les devis.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class DevisUtil {

    private static final int CENT = 100;

    private static final String ESPACE = " ";

    /** Logger. */
    private final Logger logger = Logger.getLogger(this.getClass());

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    /** DAO Finalité. */
    private FinaliteDao finaliteDao;

    /** Dao Personne. */
    private PersonneDao personneDao;

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

    /** Service tarif. */
    private TarifService tarifService;

    /** Service regleMapping. */
    private RegleMappingService regleMappingService;

    /** Service regle. */
    private RegleService regleService;

    /** Service personne. */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Source des lignes de devis. */
    private SourceLigneDevisDao sourceLigneDevisDao;

    private OpportuniteService opportuniteServiceSquare;

    /** DAO Motif de devis. */
    private MotifDevisDao motifDevisDao;

    /** PersonneUtil. */
    private PersonneUtil personneUtil;

    /** OpportuniteUtil. */
    private OpportuniteUtil opportuniteUtil;

    /** Ordre du lien familial Adherent. */
    private static final Integer ORDRE_LIEN_FAMILIAL_ADHERENT = 1;

    /** Ordre du lien familial Conjoint. */
    private static final Integer ORDRE_LIEN_FAMILIAL_CONJOINT = 2;

    /** Ordre du lien familial Enfant. */
    private static final Integer ORDRE_LIEN_FAMILIAL_ENFANT = 3;

    /** Service adhérent. */
    private AdherentService adherentService;

    /**
     * Setter.
     * @param adherentService .
     */
    public void setAdherentService(AdherentService adherentService) {
        this.adherentService = adherentService;
    }

    /**
     * Constructeur.
     */
    public DevisUtil() {
    }

    /**
     * Ajoute une ligne de devis à partir d'une proposition.
     * @param proposition la proposition
     * @return la ligne de devis
     */
    public LigneDevisDto addLigneDevis(PropositionLigneDevis proposition) {
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_DEMANDE_AJOUT_LIGNE_DEVIS,
       		 new String[] {String.valueOf(proposition.getIdentifiantDevis())}));
        final Devis devis = devisDao.getDevis(proposition.getIdentifiantDevis());
        if (devis == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_AJOUT_IMPOSSIBLE_DEVIS_INEXISTANT,
              		 new String[] {String.valueOf(proposition.getIdentifiantDevis())}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEVIS_INEXISTANT));
        }
        // Récupération de constantes
        final Integer idCritereDureePret = tarificateurMappingService.getIdentifiantCritereDureePret();
        final Finalite finaliteEnCours = finaliteDao.getFinaliteById(tarificateurSquareMappingService.getIdFinaliteEnCours());
        final Integer idApplicationGestcom = tarificateurMappingService.getIdentifiantApplicationGestcom();
        final List<Integer> listeProduitsAExclureRegles = tarificateurMappingService.getListeProduitsAExclureRegles();
        final Long idSourceLigneDevisNonRenseignee = tarificateurSquareMappingService.getIdSourceLigneDevisNonRenseignee();

        // Stockage des lignes de devis pour le calcul des règles
        final Map<Long, LigneDevis> mapLignesDevisPourCalculRegles = new HashMap<Long, LigneDevis>();

        final LigneDevis ligneDevisPrincipale =
            formerLigneDevis(proposition.getLigneDevis(), null, null, idCritereDureePret,
                finaliteEnCours, idApplicationGestcom, proposition.getLigneDevis().getSourceLigneDevis() != null ? proposition.getLigneDevis()
                        .getSourceLigneDevis().getIdentifiant() : idSourceLigneDevisNonRenseignee);

        // Récupération du montant TTC de la ligne principal
        final Float montantLignePrincipaleSansProduitsLies = ligneDevisPrincipale.getMontantTtc();
        devis.getListeLigneDevis().add(ligneDevisPrincipale);
        ligneDevisPrincipale.setDevis(devis);
        ligneDevisDao.saveLigneDevis(ligneDevisPrincipale);
        // Ajout de la ligne de devis dans la map
        mapLignesDevisPourCalculRegles.put(ligneDevisPrincipale.getId(), ligneDevisPrincipale);

        // on recupere la liste des produits liés obligatoires du produit principal
        final ProduitLieCriteresDto criteresProduitLie = new ProduitLieCriteresDto();
        criteresProduitLie.setIdentifiantProduit(proposition.getLigneDevis().getIdentifiantProduit());
        criteresProduitLie.setIdentifiantApplication(idApplicationGestcom);
        criteresProduitLie.setOptionnel(Boolean.FALSE);
        final List<ProduitLieDto> listeProduitsLiesObligatoires = produitService.getListeProduitsLies(criteresProduitLie);

        // creation d'une liste des identifiants
        final List<Integer> listeIdsProduitsLiesObligatoires = new ArrayList<Integer>();
        for (ProduitLieDto produitLieDto : listeProduitsLiesObligatoires) {
            listeIdsProduitsLiesObligatoires.add(produitLieDto.getProduitLie().getIdentifiant());
        }

        if (proposition.getLigneDevis().getListeLignesDevisLiees() != null) {
            for (LigneDevisDto ligneDevisLieeDto : proposition.getLigneDevis().getListeLignesDevisLiees()) {
                final LigneDevis ligneLiee =
                    formerLigneDevis(ligneDevisLieeDto, ligneDevisPrincipale, listeIdsProduitsLiesObligatoires, idCritereDureePret,
                        finaliteEnCours, idApplicationGestcom, ligneDevisLieeDto
                                .getSourceLigneDevis() != null ? ligneDevisLieeDto.getSourceLigneDevis().getIdentifiant() : idSourceLigneDevisNonRenseignee);
                // AJOUT DE LA LIGNE PRINCIPAL LIEE
                devis.getListeLigneDevis().add(ligneLiee);
                ligneLiee.setDevis(devis);
                if (ligneDevisPrincipale.getDateEffet().after(ligneLiee.getDateEffet())) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DATE_EFFET_PRINCIPAL_INF_DATE_EFFET_LIEE));
                }
                ligneDevisDao.saveLigneDevis(ligneLiee);
                // Ajout de la ligne de devis dans la map
                mapLignesDevisPourCalculRegles.put(ligneLiee.getId(), ligneLiee);

                // AJOUT LIGNE DEVIS LIEE A LA LIGNE DE DEVIS
                final LigneDevisLiee ligneDevisLiee = new LigneDevisLiee();
                ligneDevisLiee.setLigneDevisSource(ligneDevisPrincipale);
                ligneDevisLiee.setLigneDevisLiee(ligneLiee);
                ligneDevisPrincipale.getListeLignesDevisLiees().add(ligneDevisLiee);
            }
        }

        // Création du cache des infos de personnes et opportunité
        final InfosPersonneSquareBean infosPersonnes = new InfosPersonneSquareBean();
        final InfosOpportuniteSquareBean infosOpportunites = new InfosOpportuniteSquareBean();

        // MAPPING DU RETOUR
        LigneDevisDto ligne = mapperLigneDevis(ligneDevisPrincipale, infosPersonnes, infosOpportunites);
        List<LigneDevisDto> listeLignesLiees = new ArrayList<LigneDevisDto>();
        for (LigneDevisLiee ligneDevisLiee : ligneDevisPrincipale.getListeLignesDevisLiees()) {
            final LigneDevisDto ligneDevisLieeDto = mapperDozerBean.map(ligneDevisLiee, LigneDevisDto.class);
            listeLignesLiees.add(ligneDevisLieeDto);
        }

        ligne.setListeLignesDevisLiees(listeLignesLiees);
        // Ajout de la ligne de devis principale à la proposition pour passage au calcul des règles
        proposition.setLigneDevis(ligne);

        // Appel de la méthode de calcul des règles
        final ReglesReponseDto reglesReponseDto = calculerReglesPourLigneDevis(proposition, devis, listeProduitsAExclureRegles);

        // Parcours de la liste des réponses
        if (reglesReponseDto != null && reglesReponseDto.getListeResultatRegles() != null) {
            for (ListeObjetsParDiscriminantDto<ResultatRegleDto> listeResultatRegles : reglesReponseDto.getListeResultatRegles()) {
                // Recherche de la ligne de devis dans la map à partir du discriminant
                final LigneDevis ligneDevis = mapLignesDevisPourCalculRegles.get(Long.valueOf(listeResultatRegles.getDiscriminant()));
                if (ligneDevis != null && listeResultatRegles.getListeObjets() != null) {
                    // On stocke la régle max qui impacte le tarif
                    ResultatRegleDto regleMax = null;
                    // On ajoute chacune des règles à la ligne de devis
                    for (ResultatRegleDto resultatRegleDto : listeResultatRegles.getListeObjets()) {
                        // Création de l'objet RegleValeur
                        final RegleValeur regleValeur = new RegleValeur();
                        regleValeur.setIdRegle(resultatRegleDto.getIdRegle());
                        regleValeur.setValeurRegle(resultatRegleDto.getValeurRegle());
                        regleValeur.setIdValeurRegle(resultatRegleDto.getIdValeurRegle());
                        // Ajout de la règle à la liste de règles de la ligne de devis
                        ligneDevis.getListeReglesValeurs().add(regleValeur);

                        // on verifie si c'est la regle max
                        if (resultatRegleDto.getImpacteTarif().booleanValue()
                            && (regleMax == null || Long.valueOf(resultatRegleDto.getValeurRegle()) > Long.valueOf(regleMax.getValeurRegle()))) {
                            regleMax = resultatRegleDto;
                        }
                    }
                    if (regleMax != null) {
                        // Bug 1122 : si c'est la ligne principale : on fait la remise sur le montant de la ligne sans les produits
                        if (ligneDevis.getId().equals(ligneDevisPrincipale.getId())) {
                            calculerMontantRemiseLigneDevis(ligneDevis, regleMax, montantLignePrincipaleSansProduitsLies);
                        } else {
                            // Met a jour le montantTtcApresRemise
                            calculerMontantRemiseLigneDevis(ligneDevis, regleMax, null);
                        }
                    }
                }
            }
        }

        // Recalcul de la finalite du devis
        recalculerFinaliteDevisEtOpportunite(devis);

        // MAPPING DU RETOUR
        ligne = mapperLigneDevis(ligneDevisPrincipale, infosPersonnes, infosOpportunites);
        listeLignesLiees = new ArrayList<LigneDevisDto>();
        for (LigneDevisLiee ligneDevisLiee : ligneDevisPrincipale.getListeLignesDevisLiees()) {
            listeLignesLiees.add(mapperLigneDevis(ligneDevisLiee.getLigneDevisLiee(), listeProduitsLiesObligatoires, infosPersonnes, infosOpportunites));
        }

        ligne.setListeLignesDevisLiees(listeLignesLiees);
        return ligne;
    }

    /**
     * Recupère une ligne de devis par son identifiant.
     * @param idLigneDevis l'id de la ligne de devis
     * @return la ligne de devis
     */
    public LigneDevisDto getLigneDevisParIdentifiant(Long idLigneDevis) {
        final LigneDevis ligneDevis = ligneDevisDao.getLigneDevis(idLigneDevis);

        if (ligneDevis != null) {
            final LigneDevisDto ligneDevisDto = mapperLigneDevisPrincipale(ligneDevis, new InfosPersonneSquareBean(), new InfosOpportuniteSquareBean());
            return ligneDevisDto;
        } else {
            return null;
        }
    }

    /**
     * Recalcule la finalité d'un devis et de l'opportunité en fonction de la finalité de ses lignes de devis. <br>
     * Calcul effectué : <br>
     * Si au moins une ligne acceptée : accepté <br>
     * Si au moins une ligne en cours : en cours <br>
     * Si toutes les lignes en corbeille : corbeille <br>
     * Si toutes les lignes refusées : refusé
     * @param devis le devis
     */
    public void recalculerFinaliteDevisEtOpportunite(Devis devis) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECALCUL_FINALITE_DEVIS,
         		 new String[] {String.valueOf(devis.getId())}));

        final Long idFinaliteNonRenseignee = tarificateurSquareMappingService.getIdFinaliteNonRenseignee();
        final Long idFinaliteAcceptee = tarificateurSquareMappingService.getIdFinaliteAcceptee();
        final Long idFinaliteRefusee = tarificateurSquareMappingService.getIdFinaliteRefusee();
        final Long idFinaliteCorbeille = tarificateurSquareMappingService.getIdFinaliteCorbeille();
        final Long idFinaliteEnCours = tarificateurSquareMappingService.getIdFinaliteEnCours();
        final Long idFinaliteTransformee = tarificateurSquareMappingService.getIdFinaliteTransformee();

        Long idFinaliteDevis = idFinaliteNonRenseignee;

        // Récupération du nombre total de lignes
        // final int nbTotalLignes = devis.getListeLigneDevis().size();

        // Nombre de lignes par finalité
        int nbLignesAcceptees = 0;
        int nbLignesEnCours = 0;
        int nbLignesCorbeille = 0;
        int nbLignesRefusees = 0;

        final List<Long> finalites = ligneDevisDao.getIdsFinaliteLigneDevis(devis.getId());

        // Parcours des lignes
        for (Long finalite : finalites) {
            if (idFinaliteAcceptee.equals(finalite)) {
                nbLignesAcceptees++;
            } else if (idFinaliteNonRenseignee.equals(finalite)) {
                nbLignesEnCours++;
            } else if (idFinaliteEnCours.equals(finalite)) {
                nbLignesEnCours++;
            } else if (idFinaliteCorbeille.equals(finalite)) {
                nbLignesCorbeille++;
            } else if (idFinaliteRefusee.equals(finalite)) {
                nbLignesRefusees++;
            }
        }
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_INFO_DEVIS,
        		 new String[] {String.valueOf(devis.getId()),
        					   String.valueOf(nbLignesAcceptees),
        					   String.valueOf(nbLignesEnCours),
        					   String.valueOf(nbLignesCorbeille),
        					   String.valueOf(nbLignesRefusees)}));

        // Si au moins une ligne acceptée --> accepté
        if (nbLignesAcceptees > 0) {
            // Si le devis a une date de cloture --> transformée
            if (devis.getDateCloture() != null) {
                idFinaliteDevis = idFinaliteTransformee;
            } else {
                idFinaliteDevis = idFinaliteAcceptee;
            }
        }
        // Si au moins une ligne en cours --> en cours
        else if (nbLignesEnCours > 0) {
            idFinaliteDevis = idFinaliteEnCours;
        }
        // Si toutes les lignes en corbeille --> corbeille
        else if (nbLignesAcceptees == 0 && nbLignesEnCours == 0 && nbLignesRefusees == 0 && nbLignesCorbeille != 0) {
            idFinaliteDevis = idFinaliteCorbeille;
        }
        // Si au moins une ligne refusée et aucune acceptée ni encours --> refusé
        else if (nbLignesRefusees > 0 && nbLignesAcceptees == 0 && nbLignesEnCours == 0) {
            idFinaliteDevis = idFinaliteRefusee;
        }
        // Sinon en cours : ne doit pas arriver
        else {
            idFinaliteDevis = idFinaliteNonRenseignee;
        }
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_INFO_DEVIS_FINALITE,
       		 new String[] {String.valueOf(devis.getId()), String.valueOf(idFinaliteDevis)}));

        // Mise à jour de la finalité du devis
        devis.setFinalite(finaliteDao.getFinaliteById(idFinaliteDevis));
        if (idFinaliteDevis.equals(idFinaliteNonRenseignee) || idFinaliteDevis.equals(idFinaliteEnCours) || idFinaliteDevis.equals(idFinaliteAcceptee)) {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_DEVIS_OUVERTURE,
              		 new String[] {String.valueOf(devis.getId())}));
            devis.setDateCloture(null);
        } else if (idFinaliteDevis.equals(idFinaliteRefusee) || idFinaliteDevis.equals(idFinaliteCorbeille) || idFinaliteDevis.equals(idFinaliteTransformee)) {
            if (devis.getDateCloture() != null) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_MODIFICATION_DATE_CLOTURE_DEVIS_EXISTANTE_IMPOSSIBLE));
            }
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_DEVIS_CLOTURE,
             		 new String[] {String.valueOf(devis.getId())}));
            devis.setDateCloture(Calendar.getInstance());
        }

        // Récupération des devis de l'opportunité
        final List<Devis> listeDevisOpportunite = devisDao.getListeDevisByOpportunite(devis.getOpportunite().getId());
        // bug 6612 : on enleve la lecture sur les autres devis non accepté
        // // On met les devis en lecture seule si le devis en cours est accepté, sinon on enleve la lecture seule
        // final boolean activerLectureSeule = idFinaliteAcceptee.equals(idFinaliteDevis);
        // for (Devis devisOpportunite : listeDevisOpportunite) {
        // // Si ce n'est pas le devis que l'on recalcule
        // if (!devisOpportunite.getId().equals(devis.getId())) {
        // devisOpportunite.setLectureSeule(activerLectureSeule);
        // }
        // }

        // Recalcul de la finalité de l'opportunité : mêmes règles que pour les devis
        Long idFinaliteOpportunite = idFinaliteNonRenseignee;
        // Récupération du nombre total de devis
        final int nbTotalDevis = listeDevisOpportunite.size();
        // Nombre de devis par finalité
        int nbDevisTransformes = 0;
        int nbDevisAcceptes = 0;
        int nbDevisEnCours = 0;
        int nbDevisCorbeille = 0;
        int nbDevisRefuses = 0;

        // Parcours des devis
        for (Devis devisOpportunite : listeDevisOpportunite) {
            // Si c'est le devis que l'on recalcule
            if (devisOpportunite.getId().equals(devis.getId())) {
                if (idFinaliteTransformee.equals(idFinaliteDevis)) {
                    nbDevisTransformes++;
                } else if (idFinaliteAcceptee.equals(idFinaliteDevis)) {
                    nbDevisAcceptes++;
                } else if (idFinaliteNonRenseignee.equals(idFinaliteDevis)) {
                    nbDevisEnCours++;
                } else if (idFinaliteEnCours.equals(idFinaliteDevis)) {
                    nbDevisEnCours++;
                } else if (idFinaliteCorbeille.equals(idFinaliteDevis)) {
                    nbDevisCorbeille++;
                } else if (idFinaliteRefusee.equals(idFinaliteDevis)) {
                    nbDevisRefuses++;
                }
            } else if (devisOpportunite.getFinalite() != null) {
                if (idFinaliteTransformee.equals(devisOpportunite.getFinalite().getId())) {
                    nbDevisTransformes++;
                } else if (idFinaliteAcceptee.equals(devisOpportunite.getFinalite().getId())) {
                    nbDevisAcceptes++;
                } else if (idFinaliteNonRenseignee.equals(devisOpportunite.getFinalite().getId())) {
                    nbDevisEnCours++;
                } else if (idFinaliteEnCours.equals(devisOpportunite.getFinalite().getId())) {
                    nbDevisEnCours++;
                } else if (idFinaliteCorbeille.equals(devisOpportunite.getFinalite().getId())) {
                    nbDevisCorbeille++;
                } else if (idFinaliteRefusee.equals(devisOpportunite.getFinalite().getId())) {
                    nbDevisRefuses++;
                }
            } else {
                nbDevisEnCours++;
                break;
            }
        }
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_INFO_OPPORTUNITE_DEVIS,
        		 new String[] {String.valueOf(devis.getOpportunite().getId()),
        					   String.valueOf(nbDevisTransformes),
        					   String.valueOf(nbDevisAcceptes),
        					   String.valueOf(nbDevisEnCours),
        					   String.valueOf(nbDevisCorbeille),
        					   String.valueOf(nbDevisRefuses)}));

        // Il ne doit y avoir qu'un seul devis accepté par opportunité
        if (nbDevisAcceptes > 1) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_OPPORTUNITE_POSSEDE_DEJA_DEVIS_ACCEPTE));
        }

        // Si au moins un devis transformé --> opportunité transformée
        if (nbDevisTransformes > 0) {
            idFinaliteOpportunite = idFinaliteTransformee;
        }
        // Si au moins un devis en cours --> opportunité en cours
        else if (nbDevisEnCours > 0) {
            idFinaliteOpportunite = idFinaliteEnCours;
        }
        // Si au moins un devis accepté --> opportunité acceptée
        else if (nbDevisAcceptes > 0) {
            idFinaliteOpportunite = idFinaliteAcceptee;
        }
        // Si toutes les lignes en corbeille --> corbeille
        else if (nbDevisCorbeille == nbTotalDevis) {
            idFinaliteOpportunite = idFinaliteCorbeille;
        }
        // Si au moins une ligne refusée et aucune acceptée ni encours --> refusé
        else if (nbDevisRefuses > 0 && nbDevisAcceptes == 0 && nbDevisEnCours == 0) {
            idFinaliteOpportunite = idFinaliteRefusee;
        }
        // Sinon en cours : ne doit pas arriver
        else {
            idFinaliteOpportunite = idFinaliteNonRenseignee;
        }
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_INFO_OPPORTUNITE_FINALITE,
       		 new String[] {String.valueOf(devis.getOpportunite().getId()), String.valueOf(idFinaliteOpportunite)}));
        // Si la finalité de l'opportunité change, on modifie la finalité et éventuellement la date de cloture
        if (devis.getOpportunite().getFinalite() != null && !idFinaliteOpportunite.equals(devis.getOpportunite().getFinalite().getId())) {
            final Finalite finalite = finaliteDao.getFinaliteById(idFinaliteOpportunite);
            // Mise à jour de la finalité de l'opportunité
            final Opportunite opportunite = devis.getOpportunite();
            opportunite.setFinalite(finalite);
            if (idFinaliteOpportunite.equals(idFinaliteRefusee) || idFinaliteOpportunite.equals(idFinaliteCorbeille)
                || idFinaliteOpportunite.equals(idFinaliteTransformee)) {
                if (opportunite.getDateCloture() != null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_MODIFICATION_DATE_CLOTURE_OPP_EXISTANTE_IMPOSSIBLE));
                }
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CLOTURE_OPPORTUNITE,
                  		 new String[] {String.valueOf(opportunite.getId())}));
                opportunite.setDateCloture(Calendar.getInstance());
            } else {
                if (opportunite.getDateCloture() != null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_MODIFICATION_DATE_CLOTURE_OPP_EXISTANTE_IMPOSSIBLE));
                }
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_OUVERTURE_OPPORTUNITE,
                 		 new String[] {String.valueOf(devis.getId())}));
                opportunite.setDateCloture(null);
            }

            // on met à jour l'opportunité square
            final OpportuniteMaJStatutDto opportuniteMaJStatutDto = new OpportuniteMaJStatutDto();
            opportuniteMaJStatutDto.setIdOpportunite(opportunite.getEidOpportunite());
            opportuniteMaJStatutDto.setStatut(new IdentifiantLibelleDto(finalite.getEidFinalite()));
            opportuniteServiceSquare.mettreAJourStatutOpportunite(opportuniteMaJStatutDto);
        }
    }

    /**
     * Recalcule une ligne de devis.
     * @param proposition la proposition
     * @return la ligne de devis
     */
    public LigneDevisDto recalculerLigneDevis(PropositionLigneDevis proposition) {
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_DEMANDE_MODIF_LIGNE_DEVIS));

        // Clonage de la proposition
        final PropositionLigneDevis propositionClonee = mapperDozerBean.map(proposition, PropositionLigneDevis.class);

        final Long idLigneDevis = proposition.getLigneDevis().getIdentifiant();
        // on recupere avant la date de creation de la ligne principale
        final LigneDevis ligneDevisOld = ligneDevisDao.getLigneDevis(idLigneDevis);
        final Calendar dateCreationLigne = ligneDevisOld.getDateCreation();
        propositionClonee.getLigneDevis().setDateCreation(dateCreationLigne);
        // Récupération des données de la ligne de devis supprimée
        recupererInfosLignePourRecalcul(ligneDevisOld, propositionClonee.getLigneDevis());
        deleteLigneDevis(idLigneDevis, true);
        return addLigneDevis(propositionClonee);
    }

    /**
     * Forme la ligne de devis en base.
     * @param ligneDevisDto informations sur la ligne à former
     * @param ligneDevisPrincipale ligne principale si la ligne a former est une ligne liée (accepte null)
     * @param listeIdsProduitsLiesObligatoires liste des identifiants des produits obligatoires de la ligne principale (accepte null)
     * @return la ligne de devis
     */
    @SuppressWarnings("unchecked")
    private LigneDevis formerLigneDevis(LigneDevisDto ligneDevisDto, LigneDevis ligneDevisPrincipale, List<Integer> listeIdsProduitsLiesObligatoires,
        Integer idCritereDureePret,
        Finalite finaliteNonRenseignee, Integer idApplicationGestcom, Long idSourceLigneDevis) {
        // FORMER LA LIGNE
        final LigneDevis ligneDevis = new LigneDevis();
        // on recupere le gestionnaire
        if (ligneDevisDto.getGestionnaire() != null) {
            ligneDevis.setEidAuteur(ligneDevisDto.getGestionnaire().getIdentifiant());
        }
        ligneDevis.setEidProduit(ligneDevisDto.getIdentifiantProduit());
        ligneDevis.setDateEffet(ligneDevisDto.getDateEffet());

        // MISE A JOUR DE LA DATE DE CREATION
        final Calendar dateDuJour = Calendar.getInstance();
        // pas de date => date du jour
        if (ligneDevisPrincipale == null && ligneDevisDto.getDateCreation() == null) {
            ligneDevis.setDateCreation(dateDuJour);
        }
        // date de l'ancienne ligne récupérée via la proposition
        else if (ligneDevisPrincipale == null && ligneDevisDto.getDateCreation() != null) {
            ligneDevis.setDateCreation(ligneDevisDto.getDateCreation());
        }
        // date deja initialisé dans la ligne principale
        else if (ligneDevisPrincipale != null) {
            ligneDevis.setDateCreation(ligneDevisPrincipale.getDateCreation());
        }

        if (ligneDevisDto.getFinalite() == null || ligneDevisDto.getFinalite().getIdentifiant() == null) {
            ligneDevis.setFinalite(finaliteNonRenseignee);
        } else {
            ligneDevis.setFinalite(finaliteDao.getFinaliteById(ligneDevisDto.getFinalite().getIdentifiant()));
        }

        if (ligneDevisDto.getIdentifiantBeneficiaire() != null) {
            final Personne beneficiaire = personneDao.getPersonne(ligneDevisDto.getIdentifiantBeneficiaire());
            ligneDevis.setBeneficiaire(beneficiaire);
        }

        // RECUPERE LE TARIF DE LA LIGNE
        TarifReponsesDto tarifReponse = null;
        Float tarifLigne = Float.valueOf(0);

        final TarifCriteresDto criteres = new TarifCriteresDto();
        criteres.setIdentifiantApplication(idApplicationGestcom);
        criteres.setDateEffet(ligneDevisDto.getDateEffet());
        criteres.setIdentifiantProduit(ligneDevisDto.getIdentifiantProduit());

        // CONTROLE DES CRITERES OBLIGATOIRE
        criteres.setControleCriteres(Boolean.TRUE);

        // PASSAGE DES VALEURS CRITERE EN TARIF CRITERE
        List<TarifCritereDto> listeCritere = new ArrayList<TarifCritereDto>();
        listeCritere = mapperDozerBean.mapList(ligneDevisDto.getListeValeurCritereLigneDevis(), TarifCritereDto.class);
        criteres.setControleCriteres(true);
        criteres.setListeCriteres(listeCritere);

        String memoireLienFamille = "";
        if (ligneDevisDto.isTarifACalculer()) {
            tarifReponse = tarifService.getTarifParCriteres(criteres);
            if (ligneDevisDto.isTarifACalculer()) {
                tarifLigne = tarifReponse.getMontant();
            }
        } else {
            for (TarifCritereDto tarifCritereDto : listeCritere) {
                if (tarifCritereDto.getIdentifiant().equals(tarificateurMappingService.getIdentifiantCritereLienFamille())) {
                    memoireLienFamille = tarifCritereDto.getValeur();
                    tarifCritereDto.setValeur("id");
                }
            }
        }

        Float montantTtc;

        // MAPPING DES CRITERES
        for (ValeurCritereLigneDevisDto valeurCritereDto : ligneDevisDto.getListeValeurCritereLigneDevis()) {

            final ValeurCritereLigneDevis valeurCritere = new ValeurCritereLigneDevis();
            valeurCritere.setValeur(valeurCritereDto.getValeur());
            valeurCritere.setEidCritere(valeurCritereDto.getIdentifiantCritere());

            try {
                valeurCritere.setAffichageValeur(valeurCritere.getValeur());
                final CritereDto critere = tarifService.getCritereParIdentifiant(valeurCritereDto.getIdentifiantCritere(), idApplicationGestcom);
                Object libelle = null;
                if (critere.getChampBaremeLibMappingHibernate() != null && tarifReponse != null) {
                    // RECUPERE LE LIBELLE DU CRITERE
                    final Class cls = tarifReponse.getClass();
                    final Field field = cls.getDeclaredField(critere.getChampBaremeLibMappingHibernate());
                    field.setAccessible(true);
                    libelle = field.get(tarifReponse);
                    logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_AFFICHAGE_CRITERE_LIB_MAPPING,
                     		 new String[] {String.valueOf(critere.getChampBaremeLibMappingHibernate()), (String) libelle}));
                }
                if (!ligneDevisDto.isTarifACalculer()) {
                    if (valeurCritereDto.getIdentifiantCritere().equals(tarificateurMappingService.getIdentifiantCritereLienFamille())) {
                        libelle =
                            produitService.getLibsMappingCritere(valeurCritereDto.getIdentifiantCritere(), memoireLienFamille,
                                ligneDevisDto.getIdentifiantProduit(), ligneDevisDto.getDateEffet()).get(0);
                    } else if (valeurCritereDto.getIdentifiantCritere().equals(tarificateurMappingService.getIdentifiantCritereCompositionFamille())
                        || valeurCritereDto.getIdentifiantCritere().equals(tarificateurMappingService.getIdentifiantCritereMois())
                        || valeurCritereDto.getIdentifiantCritere().equals(tarificateurMappingService.getIdentifiantCritereAgeMin())
                        || valeurCritereDto.getIdentifiantCritere().equals(tarificateurMappingService.getIdentifiantCritereAgeMax())) {
                        final List<String> listeLibelle =
                            produitService.getLibsMappingCritere(valeurCritereDto.getIdentifiantCritere(), valeurCritereDto.getValeur(), ligneDevisDto
                                    .getIdentifiantProduit(), ligneDevisDto.getDateEffet());
                        if (listeLibelle.size() > 0) {
                            libelle = listeLibelle.get(0);
                        } else {
                            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_NON_BLOQUANTE_NO_LIBELLE_CRITERE_MAPPING,
                        		 new String[] {String.valueOf(valeurCritereDto.getIdentifiantCritere()),
                        					   valeurCritereDto.getValeur(),
                        					   String.valueOf(ligneDevisDto.getIdentifiantProduit()),
                        					   ligneDevisDto.getDateEffet() == null ? ""
                        					   : new SimpleDateFormat(messageSourceUtil.get(MessageKeyUtil.DATE_FORMAT_DDMMYYYY_SEPARATOR)).
                        					   format(ligneDevisDto.getDateEffet().getTime())}));
                        }
                    }
                }
                if (libelle != null) {
                    valeurCritere.setAffichageValeur((String) libelle);
                }
            } catch (IllegalAccessException e) {
                logger.warn(e);
                valeurCritere.setAffichageValeur(valeurCritere.getValeur());
            } catch (SecurityException e) {
                logger.warn(e);
                valeurCritere.setAffichageValeur(valeurCritere.getValeur());
            } catch (NoSuchFieldException e) {
                logger.warn(e);
                valeurCritere.setAffichageValeur(valeurCritere.getValeur());
            }
            ligneDevis.getListeValeurCritereLigneDevis().add(valeurCritere);
        }

        montantTtc = tarifLigne;

        // On arrondit le montant à deux chiffres après la virgule (arrondie au centième le plus près)
        ligneDevis.setMontantTtc(arrondirMontant(montantTtc));
        // on met a jour le montant apres remise en partant du principe qu'il n'y a pas de remise (si il y a remise, il sera recalculé ultérieurement)
        ligneDevis.setMontantRemise(0f);

        // si la ligne est une ligne liée et que le produit de cette ligne est un produit obligatoire de la ligne principale
        if (ligneDevisPrincipale != null && listeIdsProduitsLiesObligatoires != null
            && listeIdsProduitsLiesObligatoires.contains(ligneDevisDto.getIdentifiantProduit())) {
            // on ajoute le tarif de la ligne a celle de la ligne principale
            ligneDevisPrincipale.setMontantTtc(arrondirMontant(ligneDevisPrincipale.getMontantTtc() + tarifLigne));
            // on met a jour le montant apres remise en partant du principe qu'il n'y a pas de remise (si il y a remise, il sera recalculé ultérieurement)
            ligneDevisPrincipale.setMontantRemise(0f);
        }

        if (idSourceLigneDevis != null) {
            ligneDevis.setSourceLigneDevis(sourceLigneDevisDao.getSourceLigneDevisParId(idSourceLigneDevis));
        } else {
            ligneDevis.setSourceLigneDevis(sourceLigneDevisDao.getSourceLigneDevisParId(tarificateurSquareMappingService.getIdFinaliteCorbeille()));
        }
        if (ligneDevisDto.getSelectionnePourAdhesion() != null) {
            ligneDevis.setSelectionnePourAdhesion(ligneDevisDto.getSelectionnePourAdhesion());
        }
        if (ligneDevisDto.getSelectionnePourImpression() != null) {
            ligneDevis.setSelectionnePourImpression(ligneDevisDto.getSelectionnePourImpression());
        }
        return ligneDevis;
    }

    /**
     * Mappe une ligne de devis en DTO.
     * @param ligneDevis la ligne de devis
     * @param infosPersonnes le cache des infos de personnes
     * @param infosOpportunites le cache des infos d'opportunités
     * @return le DTO
     */
    private LigneDevisDto mapperLigneDevis(LigneDevis ligneDevis, InfosPersonneSquareBean infosPersonnes, InfosOpportuniteSquareBean infosOpportunites) {
        return mapperLigneDevis(ligneDevis, null, infosPersonnes, infosOpportunites);
    }

    /**
     * Mappe une ligne de devis en DTO.
     * @param ligneDevis la ligne de devis
     * @param une liste de produits liés obligatoires pour renseigner le DTO
     * @param infosPersonnes le cache des infos de personnes
     * @param infosOpportunites le cache des infos d'opportunités
     * @return le DTO
     */
    private LigneDevisDto mapperLigneDevis(LigneDevis ligneDevis, List<ProduitLieDto> listeProduitsLiesObligatoires, InfosPersonneSquareBean infosPersonnes,
        InfosOpportuniteSquareBean infosOpportunites) {
        final LigneDevisDto ligneDevisDto = mapperDozerBean.map(ligneDevis, LigneDevisDto.class);
        if (ligneDevis.getEidAuteur() != null) {
            ligneDevisDto.setGestionnaire(new IdentifiantLibelleDto(ligneDevis.getEidAuteur()));
        } else {
            // On renseigne avec le responsable de l'opportunité
            final OpportuniteSimpleDto opportuniteSquare =
                opportuniteUtil.getOpportuniteSimple(ligneDevis.getDevis().getOpportunite().getEidOpportunite(), infosOpportunites);
            if (opportuniteSquare.getRessource() != null && opportuniteSquare.getRessource().getIdentifiantExterieur() != null) {
                ligneDevisDto.setGestionnaire(new IdentifiantLibelleDto(Long.valueOf(opportuniteSquare.getRessource().getIdentifiantExterieur())));
            }
        }

        // Récupération du lien familial des bénéficiaires de la personne principale (clé = idBeneficiaire, valeur = idLienFamilial)
        final Map<Long, Long> mapBeneficiairesLienFamilial = new HashMap<Long, Long>();
        if (ligneDevis.getDevis() != null && ligneDevis.getDevis().getPersonnePrincipale() != null
            && ligneDevis.getDevis().getPersonnePrincipale().getListeBeneficiaires() != null) {
            for (Beneficiaire beneficiaire : ligneDevis.getDevis().getPersonnePrincipale().getListeBeneficiaires()) {
                if (beneficiaire.getPersonneCible() != null && !mapBeneficiairesLienFamilial.containsKey(beneficiaire.getPersonneCible().getId())) {
                    if (beneficiaire.getLienFamilial() != null && beneficiaire.getLienFamilial().getId() != null) {
                        mapBeneficiairesLienFamilial.put(beneficiaire.getPersonneCible().getId(), beneficiaire.getLienFamilial().getId());
                    }
                }
            }
        }

        // Recuperation des infos du produit
        if (!ligneDevisDto.getIdentifiantProduit().equals("")) {
            final Integer idProduit = ligneDevisDto.getIdentifiantProduit();

            // on recupere le produit concerné
            final ProduitCriteresDto criteres = new ProduitCriteresDto();
            criteres.setIdentifiantProduit(idProduit);
            final List<ProduitDto> listeProduits = produitService.getListeProduits(criteres);

            if (listeProduits.size() == 1) {
                ligneDevisDto.setLibelleProduit(listeProduits.get(0).getLibelle());
                final boolean possedeGrillePresta =
                    listeProduits.get(0).getFormulePresta() != null && listeProduits.get(0).getFormulePresta().getIdentifiant() != null
                        && !"".equals(listeProduits.get(0).getFormulePresta().getIdentifiant());
                ligneDevisDto.setProduitAGrillePresta(possedeGrillePresta);
                ligneDevisDto.setOrdreAffichage(listeProduits.get(0).getOrdreAffichage());
                ligneDevisDto.setOrdreFamille(listeProduits.get(0).getOrdreFamille());
                ligneDevisDto.setIdentifiantFamille(listeProduits.get(0).getIdentifiantFamille());
                ligneDevisDto.setOrdreGamme(listeProduits.get(0).getGamme().getOrdreAffichage());
                final ModePaiementDto modePaiementDto = produitService.getModePaiementParProduit(idProduit);
                ligneDevisDto.setIdentifiantModePaiement(modePaiementDto.getIdentifiant());
                ligneDevisDto.setLibelleModePaiement(modePaiementDto.getLibelle());
                ligneDevisDto.setIdentifiantProspect(ligneDevis.getDevis().getPersonnePrincipale().getId());

                // on recupere l'age, le prénom et le nom du prospect ou du benef
                if (ligneDevisDto.getIdentifiantBeneficiaire() != null) {
                    ligneDevisDto.setOrdreAgeProspect(getAgeCalculeBeneficiaire(ligneDevisDto.getDateEffet(), ligneDevisDto.getIdentifiantBeneficiaire(),
                        idProduit, ligneDevis.getDevis().getPersonnePrincipale().getListeBeneficiaires()));
                    final Long idLienFamilial = mapBeneficiairesLienFamilial.get(ligneDevisDto.getIdentifiantBeneficiaire());
                    ligneDevisDto.setIdLienFamilial(idLienFamilial);
                    if (tarificateurSquareMappingService.getIdLienFamilialConjoint().equals(idLienFamilial)) {
                        ligneDevisDto.setOrdreLienFamilial(ORDRE_LIEN_FAMILIAL_CONJOINT);
                    } else if (tarificateurSquareMappingService.getIdLienFamilialEnfant().equals(idLienFamilial)) {
                        ligneDevisDto.setOrdreLienFamilial(ORDRE_LIEN_FAMILIAL_ENFANT);
                    }
                    String prenomNomBeneficiaire = "";
                    final Personne beneficiaire = personneDao.getPersonne(ligneDevisDto.getIdentifiantBeneficiaire());
                    if (beneficiaire != null) {
                        final PersonneSimpleDto beneficiaireSquare = personneUtil.getPersonneSimple(beneficiaire.getEidPersonne(), infosPersonnes);
                        prenomNomBeneficiaire = beneficiaireSquare.getNom().toUpperCase() + ESPACE + beneficiaireSquare.getPrenom();
                    }
                    ligneDevisDto.setPrenomNomBeneficiaire(prenomNomBeneficiaire);
                    ligneDevisDto.setEidBeneficiaire(beneficiaire.getEidPersonne());
                } else {
                    final Personne prospectDto = personneDao.getPersonne(ligneDevis.getDevis().getPersonnePrincipale().getId());
                    ligneDevisDto.setOrdreAgeProspect(getAgeCalculePersonne(ligneDevisDto.getDateEffet(), prospectDto.getDateNaissance(), idProduit));
                    ligneDevisDto.setIdLienFamilial(tarificateurSquareMappingService.getIdLienFamilialAssurePrincipal());
                    ligneDevisDto.setOrdreLienFamilial(ORDRE_LIEN_FAMILIAL_ADHERENT);
                    String prenomNomProspect = "";
                    if (prospectDto != null) {
                        final PersonneSimpleDto prospectSquare = personneUtil.getPersonneSimple(prospectDto.getEidPersonne(), infosPersonnes);
                        prenomNomProspect = prospectSquare.getNom().toUpperCase() + ESPACE + prospectSquare.getPrenom();
                    }
                    ligneDevisDto.setPrenomNomBeneficiaire(prenomNomProspect);
                }

                // On détermine si le produit en cours est un produit lié, et s'il est obligatoire
                ligneDevisDto.setProduitOptionnel(Boolean.TRUE);
                if (listeProduitsLiesObligatoires != null) {
                    for (ProduitLieDto produitLieDto : listeProduitsLiesObligatoires) {
                        if (produitLieDto.getProduitLie() != null && produitLieDto.getProduitLie().getIdentifiant() != null) {
                            if (produitLieDto.getProduitLie().getIdentifiant().equals(listeProduits.get(0).getIdentifiant())) {
                                ligneDevisDto.setProduitOptionnel(Boolean.FALSE);
                            }
                        }
                    }
                }
            } else {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_RECUPERATION_PRODUIT,
               		 new String[] {String.valueOf(idProduit), String.valueOf(listeProduits.size())}));
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_RECUPERATION_PRODUIT));
            }
        }
        return ligneDevisDto;
    }

    /**
     * Calcule les règles d'une proposition de ligne de devis.
     * @param ligneDevisPrincipale la ligne de devis principale
     * @return les règles à appliquer pour la tarification
     */
    private ReglesReponseDto calculerReglesPourLigneDevis(PropositionLigneDevis propositionLigneDevis, Devis devis, List<Integer> listeProduitsAExclureRegles) {

        // Récupération du prospect à partir du devis
        final Personne prospect = devis.getPersonnePrincipale();

        // Construction de la ligne devis principale
        final LigneDevisDto ligneDevisPrincipale = propositionLigneDevis.getLigneDevis();
        final LigneDevisPourRegle ligneDevisPrincipaleRegle = mapperDozerBean.map(ligneDevisPrincipale, LigneDevisPourRegle.class);
        // Calcul de l'age du bénéficiaire de la ligne principale
        ligneDevisPrincipaleRegle.setAgeBeneficiaire(getAgeCalculePersonne(ligneDevisPrincipale.getDateEffet(), prospect.getDateNaissance(),
            ligneDevisPrincipale.getIdentifiantProduit()));
        // Affectation du role (toujours assuré)
        ligneDevisPrincipaleRegle.setRoleBeneficiaire(regleMappingService.getConstanteRoleAssure());
        // Affectation de l'index du prospect (toujours 1)
        ligneDevisPrincipaleRegle.setIndexRoleBeneficiaire(1);
        // Code génération
        for (ValeurCritereLigneDevisDto valeurCritere : ligneDevisPrincipale.getListeValeurCritereLigneDevis()) {
            if (tarificateurMappingService.getIdentifiantCritereGeneration().equals(valeurCritere.getIdentifiantCritere())) {
                ligneDevisPrincipaleRegle.setCodeGeneration(valeurCritere.getValeur());
                break;
            }
        }

        // Construction de la liste des lignes de devis liées
        final List<LigneDevisPourRegle> listeLignesDevisLieesRegle = new ArrayList<LigneDevisPourRegle>();
        for (LigneDevisDto ligneDevisLiee : propositionLigneDevis.getLigneDevis().getListeLignesDevisLiees()) {
                final LigneDevisPourRegle ligneDevisLieeRegle = mapperDozerBean.map(ligneDevisLiee, LigneDevisPourRegle.class);
                // On renseigne l'age du bénéficiaire (ou du prospect si ce n'est pas un bénéficiaire)
                if (ligneDevisLiee.getIdentifiantBeneficiaire() != null) {
                    // Calcul de l'age du bénéficiaire pour le produit et à la date demandés
                    ligneDevisLieeRegle.setAgeBeneficiaire(getAgeCalculeBeneficiaire(ligneDevisLiee.getDateEffet(),
                        ligneDevisLiee.getIdentifiantBeneficiaire(), ligneDevisLiee.getIdentifiantProduit(), devis.getPersonnePrincipale()
                                .getListeBeneficiaires()));
                } else {
                    // Calcul de l'age du prospect
                    ligneDevisLieeRegle.setAgeBeneficiaire(getAgeCalculePersonne(ligneDevisLiee.getDateEffet(), prospect.getDateNaissance(), ligneDevisLiee
                            .getIdentifiantProduit()));
                }
                // Code génération
                for (ValeurCritereLigneDevisDto valeurCritere : ligneDevisLiee.getListeValeurCritereLigneDevis()) {
                    if (tarificateurMappingService.getIdentifiantCritereGeneration().equals(valeurCritere.getIdentifiantCritere())) {
                        ligneDevisLieeRegle.setCodeGeneration(valeurCritere.getValeur());
                        break;
                    }
                }
                // Ajout de la ligne de devis à la liste
                listeLignesDevisLieesRegle.add(ligneDevisLieeRegle);
        }
        // Tri des lignes par age
        ordonnerListeLignesDevisParAge(listeLignesDevisLieesRegle);

        // Récupération du rôle et de l'index du bénéficiaire pour les lignes liées
        affectationRolePourListeLignesDevisLiees(listeLignesDevisLieesRegle);

        // Affectation de la liste des lignes à la ligne de devis principale
        ligneDevisPrincipaleRegle.setListeLignesLiees(listeLignesDevisLieesRegle);

        // Création de la recherche de règle
        final ReglesRechercheDto reglesRechercheDto = creerRechercheRegles(ligneDevisPrincipaleRegle, listeProduitsAExclureRegles);

        return regleService.getListeReglesByProduit(reglesRechercheDto);
    }

    /**
     * Calcule le montant de la remise pour une ligne de devis.
     * @param ligneDevis la ligne de devis concernée
     * @param montantLignePrincipaleSansProduitsLies le montant TTC de la ligne principale sans l'ajout des montants des produits liés obligatoire<br>
     *            Peut être null. Si non null, calcul de la remise sur ce montant
     */
    private void calculerMontantRemiseLigneDevis(LigneDevis ligneDevis, ResultatRegleDto resultatRegleDto, Float montantLignePrincipaleSansProduitsLies) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CALCUL_REMISE_LIGNE_DEVIS,
          		 new String[] {String.valueOf(ligneDevis.getId()),
        		               String.valueOf(resultatRegleDto.getImpacteTarif()),
        		               String.valueOf(resultatRegleDto.getValeurRegle()),
        		               String.valueOf(ligneDevis.getMontantTtc()),
        		               String.valueOf(montantLignePrincipaleSansProduitsLies)}));
        // on verifie si la regle impacte le tarif
        if (resultatRegleDto.getImpacteTarif() != null && resultatRegleDto.getImpacteTarif().booleanValue()) {
            final Float montantLigneAvantRemise =
                montantLignePrincipaleSansProduitsLies != null ? montantLignePrincipaleSansProduitsLies : ligneDevis.getMontantTtc();
            final Float montantRemise = montantLigneAvantRemise * (Float.valueOf(resultatRegleDto.getValeurRegle())) / CENT;
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CALCUL_REMISE_LIGNE_DEVIS_MONTANT_REMISE,
             		 new String[] {String.valueOf(montantRemise)}));
            // on met a jour le montantRemise
            ligneDevis.setMontantRemise(arrondirMontant(montantRemise));
        }
    }

    /**
     * Map d'une ligne principale vers un DTO.
     * @param ligneDevisPrincipale la ligne de devis principale
     * @param infosPersonnes le cache des infos de personnes
     * @param infosOpportunites le cache des infos d'opportunités
     * @return le DTO
     */
    public LigneDevisDto mapperLigneDevisPrincipale(LigneDevis ligneDevisPrincipale, InfosPersonneSquareBean infosPersonnes,
        InfosOpportuniteSquareBean infosOpportunites) {

        // Récupération des libellés de critères :
        final List<CritereDto> listeCriteres = produitService.getListeCriteres();
        final Map<Integer, String> mapIdsLibellesCriteres = new HashMap<Integer, String>();
        for (final CritereDto critere : listeCriteres) {
            mapIdsLibellesCriteres.put(critere.getIdentifiant(), critere.getLibelle());
        }

        // on recupere les produits liés au produit de la ligne principale
        final ProduitLieCriteresDto criteresProduitLie = new ProduitLieCriteresDto();
        criteresProduitLie.setIdentifiantProduit(ligneDevisPrincipale.getEidProduit());
        criteresProduitLie.setIdentifiantApplication(tarificateurMappingService.getIdentifiantApplicationGestcom());
        final List<ProduitLieDto> listeProduitsLies = produitService.getListeProduitsLies(criteresProduitLie);
        // creation d'une map (idProduit/optionnel)
        final Map<Integer, Boolean> mapProduitLies = new HashMap<Integer, Boolean>();
        for (ProduitLieDto produitLieDto : listeProduitsLies) {
            mapProduitLies.put(produitLieDto.getProduitLie().getIdentifiant(), produitLieDto.getOptionnel());
        }
        final LigneDevisDto ligneDevisPrincipaleDto = mapperLigneDevis(ligneDevisPrincipale, infosPersonnes, infosOpportunites);
        // Récupération de listeValeurCritere en type List
        final List<ValeurCritereLigneDevis> listeValeursCritere =
            new ArrayList<ValeurCritereLigneDevis>(ligneDevisPrincipale.getListeValeurCritereLigneDevis());
        // Tri de la liste
        Collections.sort(listeValeursCritere);
        final List<ValeurCritereLigneDevisDto> listeValeursCritereDto = mapperDozerBean.mapList(listeValeursCritere, ValeurCritereLigneDevisDto.class);
        // On complete avec les libellés :
        for (ValeurCritereLigneDevisDto valeurCritereDto : listeValeursCritereDto) {
            valeurCritereDto.setLibelleCritere(mapIdsLibellesCriteres.get(valeurCritereDto.getIdentifiantCritere()));
        }
        ligneDevisPrincipaleDto.setListeValeurCritereLigneDevis(listeValeursCritereDto);
        // Gestion des lignes liées
        final List<LigneDevisDto> listeLignesDevisLieesDto = new ArrayList<LigneDevisDto>();
        // Parcours des lignes liées
        for (LigneDevisLiee ligneDevisLiee : ligneDevisPrincipale.getListeLignesDevisLiees()) {
            final LigneDevisDto ligneDevisLieeDto = mapperLigneDevis(ligneDevisLiee.getLigneDevisLiee(), infosPersonnes, infosOpportunites);
            // Récupération de listeValeurCritere en type List
            final List<ValeurCritereLigneDevis> listeValeursCritereLigneLiee =
                new ArrayList<ValeurCritereLigneDevis>(ligneDevisLiee.getLigneDevisLiee().getListeValeurCritereLigneDevis());
            // Tri de la liste
            Collections.sort(listeValeursCritereLigneLiee);
            final List<ValeurCritereLigneDevisDto> listeValeursCritereDtoLigneLiee =
                mapperDozerBean.mapList(listeValeursCritereLigneLiee, ValeurCritereLigneDevisDto.class);
            // On complete avec les libellés :
            for (ValeurCritereLigneDevisDto valeurCritereDto : listeValeursCritereDtoLigneLiee) {
                valeurCritereDto.setLibelleCritere(mapIdsLibellesCriteres.get(valeurCritereDto.getIdentifiantCritere()));
            }
            ligneDevisLieeDto.setListeValeurCritereLigneDevis(listeValeursCritereDtoLigneLiee);
            // on recupere si le produit de la ligne liée est obligatoire ou non
            final Boolean isOptionnel = mapProduitLies.get(ligneDevisLieeDto.getIdentifiantProduit());
            ligneDevisLieeDto.setProduitOptionnel(isOptionnel != null ? isOptionnel : true);

            // Ajout de la ligne de devis liée à la liste
            listeLignesDevisLieesDto.add(ligneDevisLieeDto);
        }
        // Affectation de la liste des lignes de devis liées à la ligne de devis
        ligneDevisPrincipaleDto.setListeLignesDevisLiees(listeLignesDevisLieesDto);

        // on tri les lignes liées
        Collections.sort(ligneDevisPrincipaleDto.getListeLignesDevisLiees());

        return ligneDevisPrincipaleDto;
    }

    /**
     * Récupère les infos d'une ligne avant son recalcul (suppression).
     * @param ancienneLigne l'ancienne ligne (de type modèle)
     * @param nouvelleLigne la nouvelle ligne (de type DTO)
     */
    private void recupererInfosLignePourRecalcul(LigneDevis ancienneLigne, LigneDevisDto nouvelleLigne) {
        // Bug 0003770 - le créateur doit rester le même lors d'une modification
        if (ancienneLigne.getEidAuteur() != null) {
            nouvelleLigne.setGestionnaire(new IdentifiantLibelleDto(ancienneLigne.getEidAuteur()));
            for (LigneDevisDto ligneLiee : nouvelleLigne.getListeLignesDevisLiees()) {
                ligneLiee.setGestionnaire(new IdentifiantLibelleDto(ancienneLigne.getEidAuteur()));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deleteLigneDevis(Long idLigneDevis) {
        deleteLigneDevis(idLigneDevis, false);
    }

    /**
     * Supprime la ligne de devis demandée.
     * @param idLigneDevis l'identifiant de la ligne de devis à supprimer
     * @param pourRecalcul flag indiquant que c'est une suppression pour un recalcul (ne vérifie pas que c'est une ligne d'adhésion)
     */
    private void deleteLigneDevis(Long idLigneDevis, boolean pourRecalcul) {
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_DEMANDE_SUPPRESION_LIGNE_DEVIS,
        		 new String[] {String.valueOf(idLigneDevis)}));
        final Long idFinaliteNonRenseignee = tarificateurSquareMappingService.getIdFinaliteNonRenseignee();
        final Long idFinaliteEnCours = tarificateurSquareMappingService.getIdFinaliteEnCours();
        final Long idFinaliteRefusee = tarificateurSquareMappingService.getIdFinaliteRefusee();
        final Long idFinaliteAcceptee = tarificateurSquareMappingService.getIdFinaliteAcceptee();

        final LigneDevis ligneDevis = ligneDevisDao.getLigneDevis(idLigneDevis);
        final Devis devis = ligneDevis.getDevis();
        if (ligneDevis == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_ID_LIGNE_DEVIS_INEXISTANT,
           		 new String[] {String.valueOf(idLigneDevis)}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_SUPPRESSION_LIGNE_IMPOSSIBLE_LIGNE_INEXISTANTE));
        }
        if (!ligneDevis.getFinalite().getId().equals(idFinaliteNonRenseignee) && !ligneDevis.getFinalite().getId().equals(idFinaliteEnCours)) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_SUPPRESSION_LIGNE_DEVIS_AVEC_FINALITE_RENSEIGNEE_IMPOSSIBLE));
        }

        final boolean isLigneAdhesion = ligneDevis.getFinalite().getId().equals(idFinaliteAcceptee);
        // On ne peut pas supprimer une ligne liée à une adhésion sauf si c'est pour un recalcul
        if (!pourRecalcul && isLigneAdhesion) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_SUPPRESSION_PROPOSITION_LIEE_ADHESION_INTERNET_IMPOSSIBLE));
        }
        // CONTROLE AUCUNE LIGNE AVEC FINALITE NON RENSEIGNE
        for (LigneDevisLiee ligneDevisLiee : ligneDevis.getListeLignesDevisLiees()) {
            final LigneDevis ligneLiee = ligneDevisLiee.getLigneDevisLiee();
            if (!ligneLiee.getFinalite().getId().equals(idFinaliteNonRenseignee) && !ligneLiee.getFinalite().getId().equals(idFinaliteEnCours)
                && !ligneLiee.getFinalite().getId().equals(idFinaliteRefusee)) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_SUPPRESSION_LIGNE_DEVIS_AVEC_FINALITE_RENSEIGNEE_IMPOSSIBLE));
            }
            if (!pourRecalcul && isLigneAdhesion) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_SUPPRESSION_PROPOSITION_LIEE_ADHESION_INTERNET_IMPOSSIBLE));
            }
            // REMOVE FROM ASSOCIATION
            devis.getListeLigneDevis().remove(ligneLiee);
            ligneLiee.setDevis(null);
        }
        // REMOVE FROM ASSOCIATION
        devis.getListeLigneDevis().remove(ligneDevis);
        ligneDevis.setDevis(null);
        ligneDevisDao.deleteLigneDevis(ligneDevis);
    }

    /**
     * {@inheritDoc}
     */
    public void cloturerDevisQuery(ClotureDevisQueryDto query) {
        // RECUPERE LE DEVIS
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_DEMANDE_CLOTURE_DEVIS, new String[] {String.valueOf(query.getIdDevis())}));
        final Devis devis = devisDao.getDevis(query.getIdDevis());

        // Motif de devis (pour synchro)
        if (query.getIdMotifDevis() != null) {
            final MotifDevis motifDevis = motifDevisDao.getMotifDevisById(query.getIdMotifDevis());
            if (motifDevis != null) {
                devis.setMotif(motifDevis);
            }
        }

        // SI LE DEVIS NE CONTIENT AUCUNE LIGNE ON NE FAIT RIEN (NI CLOTURE, NI OUVERTURE)
        if (devis.getListeLigneDevis().size() != 0) {

            // MAJ DES LIGNES DE LA REQUETE
            for (ClotureDevisQueryLigneDto queryLigne : query.getLignesQuery()) {
                final LigneDevis ligne = ligneDevisDao.getLigneDevis(queryLigne.getIdentifaintLigneDevis());
                if (ligne == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_CLOTURE_IMPOSSIBLE_LIGNE_INEXISTANTE,
                    		new String[] {String.valueOf(queryLigne.getIdentifaintLigneDevis())}));
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_CLOTURE_IMPOSSIBLE_LIGNE_INEXISTANTE,
                    		new String[] {String.valueOf(queryLigne.getIdentifaintLigneDevis())}));
                }
                final Finalite finalite = finaliteDao.getFinaliteById(queryLigne.getIdentifiantFinaliteLigneDevis());
                if (finalite != null) {
                    ligne.setFinalite(finalite);
                    logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_FINALITE_ID_LIBELLE,
                    		new String[] {String.valueOf(queryLigne.getIdentifaintLigneDevis()), finalite.getLibelle()}));
                }
                // Sélection pour adhésion
                if (queryLigne.getSelectionPourAdhesion() != null) {
                    ligne.setSelectionnePourAdhesion(queryLigne.getSelectionPourAdhesion());
                }
            }
            // Recalcul de la finalité du devis et de la relance
            recalculerFinaliteDevisEtOpportunite(devis);
        }
    }

    /**
     * Crée la liste des critères pour la recherche de tarif de la garantie emprunteur. Le critère "Durée du prêt en mois" est divisé par 12 pour avoir le
     * nombre d'années
     * @param listeValeursCritere la liste des valeurs de critères de la ligne de devis
     * @param idCritereDureePret l'identifiant du critère "Durée du prêt"
     * @return la liste des critères pour la recherche de tarif de la garantie emprunteur
     */
    private List<TarifCritereDto> getListeTarifCriterePourGarantieEmprunteur(List<ValeurCritereLigneDevisDto> listeValeursCritere, Integer idCritereDureePret) {
        final List<TarifCritereDto> listeTarifCritere = new ArrayList<TarifCritereDto>();
        // Parcours de la liste des ValeursCriteres
        for (ValeurCritereLigneDevisDto valeurCritere : listeValeursCritere) {
            // Mapping
            final TarifCritereDto tarifCritereDto = mapperDozerBean.map(valeurCritere, TarifCritereDto.class);
            // Si c'est le critère "Durée du prêt", division par 12
            if (valeurCritere.getIdentifiantCritere().equals(idCritereDureePret)) {
                if (valeurCritere.getValeur() != null) {
                    try {
                        final String dureePretEnAnnee = new Integer(Math.round(Float.parseFloat(valeurCritere.getValeur()) / 12)).toString();
                        tarifCritereDto.setValeur(dureePretEnAnnee);
                    } catch (NumberFormatException e) {
                        logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_FORMATION_LIGNE_DEVIS_DUREEPRET_INCORRECTE,
                        		new String[] {String.valueOf(valeurCritere.getValeur())}));
                        throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DUREE_PRET_INCORRECTE));
                    }
                }
            }
            listeTarifCritere.add(tarifCritereDto);
        }
        return listeTarifCritere;
    }

    /**
     * Contrôle la durée du prêt (entre 12 et 361).
     * @param dureePretString la durée du prêt
     */
    private void controlerDureePretGarantieEmprunteur(String dureePretString) {
        try {
            // La durée doit être comprise entre 12 et 361 sinon exception
            final Integer dureePretMin = 12;
            final Integer dureePretMax = 361;
            final Integer dureePret = Integer.valueOf(dureePretString);
            if (dureePret < dureePretMin || dureePret > dureePretMax) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DUREE_PRET_INCORRECTE));
            }
        } catch (NumberFormatException e) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_FORMATION_LIGNE_DEVIS_DUREEPRET_INCORRECTE,
            		new String[] {dureePretString}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DUREE_PRET_INCORRECTE));
        }
    }

    /**
     * Ajoute ou surcharge le critère "Taux" de la ligne de devis.
     * @param taux le taux
     * @param ligneDevis la ligne de devis
     */
    private void ajouterValeurCritereTaux(Float taux, LigneDevis ligneDevis) {
        // Récupération de l'identifiant du critère Taux
        final Integer idCritereTaux = tarificateurMappingService.getIdentifiantCritereTaux();
        boolean contientCritereTaux = false;
        // Parcours de la liste des valeurs de criteres de la ligne de devis
        for (ValeurCritereLigneDevis valeurCritere : ligneDevis.getListeValeurCritereLigneDevis()) {
            // Si Critère Taux : surcharge de la valeur
            if (valeurCritere.getEidCritere().equals(idCritereTaux)) {
                valeurCritere.setValeur(taux.toString());
                valeurCritere.setAffichageValeur(taux.toString());
                contientCritereTaux = true;
                return;
            }
        }
        // Si le critère Taux n'est pas trouvé : ajout du critère
        if (!contientCritereTaux) {
            final ValeurCritereLigneDevis valeurCritere = new ValeurCritereLigneDevis();
            valeurCritere.setValeur(taux.toString());
            valeurCritere.setAffichageValeur(taux.toString());
            valeurCritere.setEidCritere(idCritereTaux);
            ligneDevis.getListeValeurCritereLigneDevis().add(valeurCritere);
        }
    }

    /**
     * Calcule le montant de la ligne de devis pour la Garantie Emprunteur.
     * @param ligneDevis la ligne de devis
     * @return le montant de la ligne de devis
     */
    private Float calculerMontantLigneDevisPourGarantieEmprunteur(LigneDevis ligneDevis) {
        // Récupération des identifiants de critères
        final Integer idCritereCapital = tarificateurMappingService.getIdentifiantCritereCapital();
        final Integer idCritereTauxCouverture = tarificateurMappingService.getIdentifiantCritereTauxCouverture();
        final Integer idCritereTaux = tarificateurMappingService.getIdentifiantCritereTaux();

        // Initialisation
        Float capital = new Float(0);
        Float tauxCouverture = new Float(0);
        Float taux = new Float(0);

        try {
            // Parcours des critères de la ligne de devis
            for (ValeurCritereLigneDevis valeurCritere : ligneDevis.getListeValeurCritereLigneDevis()) {
                // Capital
                if (valeurCritere.getEidCritere().equals(idCritereCapital)) {
                    capital = Float.valueOf(valeurCritere.getValeur());
                }
                // Taux de couverture
                else if (valeurCritere.getEidCritere().equals(idCritereTauxCouverture)) {
                    tauxCouverture = Float.valueOf(valeurCritere.getValeur());
                }
                // Taux
                else if (valeurCritere.getEidCritere().equals(idCritereTaux)) {
                    taux = Float.valueOf(valeurCritere.getValeur());
                }
            }
        } catch (NumberFormatException e) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_CALCUL_MONTANT_LIGNE_DEVIS,
            		new String[] {e.getMessage()}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CALCUL_MONTANT_LIGNE_DEVIS_IMPOSSIBLE));
        }

        // Calcul du montant
        final Float cent = new Float(100);
        final Float milleDeuxCents = new Float(1200);
        final Float montant = (((capital * tauxCouverture) / cent) * taux) / milleDeuxCents;

        return montant;
    }

    /**
     * Calcule le montant de la ligne de devis pour les produits Caution ou Crédit Relais.
     * @param ligneDevis la ligne de devis
     * @return le montant de la ligne de devis
     */
    private Float calculerMontantLigneDevisPourCautionOuCreditRelais(LigneDevis ligneDevis) {
        // Récupération des identifiants de critères
        final Integer idCritereTaux = tarificateurMappingService.getIdentifiantCritereTaux();
        final Integer idCritereMontant = tarificateurMappingService.getIdentifiantCritereMontant();

        // Initialisation
        Float taux = new Float(0);
        Float montantCritere = new Float(0);

        try {
            // Parcours des critères de la ligne de devis
            for (ValeurCritereLigneDevis valeurCritere : ligneDevis.getListeValeurCritereLigneDevis()) {
                // Taux
                if (valeurCritere.getEidCritere().equals(idCritereTaux)) {
                    taux = Float.valueOf(valeurCritere.getValeur());
                }
                // Montant
                else if (valeurCritere.getEidCritere().equals(idCritereMontant)) {
                    montantCritere = Float.valueOf(valeurCritere.getValeur());
                }
            }
        } catch (NumberFormatException e) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_CALCUL_MONTANT_LIGNE_DEVIS,
            		new String[] {e.getMessage()}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CALCUL_MONTANT_LIGNE_DEVIS_IMPOSSIBLE));
        }

        // Calcul du montant
        final Float cent = new Float(100);
        final Float montant = (taux * montantCritere) / cent;

        return montant;
    }

    /**
     * Arrondit le montant à la deuxième décimale la plus proche (ex : 15.246 => 15.25).
     * @param montant le montant à arrondir
     * @return le montant arrondi
     */
    private Float arrondirMontant(Float montant) {
        final Float cent = new Float(100);
        return Math.round(montant * cent) / cent;
    }

    /**
     * {@inheritDoc}
     */
    public Integer getAgeCalculeBeneficiaire(Calendar dateCalcul, Long idBeneficiaire, Integer idProduit, Set<Beneficiaire> listeBeneficiaires) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_DEMANDE_CALCUL_INFO_BENEFICIAIRE,
        		new String[] {String.valueOf(idBeneficiaire)}));

        if (idBeneficiaire == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AUCUN_IDENTIFIANT_BENEFICIAIRE_INDIQUE));
        }

        for (Beneficiaire beneficiaire : listeBeneficiaires) {
            if (beneficiaire.getPersonneCible().getId().equals(idBeneficiaire)) {
                return getAgeCalculePersonne(dateCalcul, beneficiaire.getPersonneCible().getDateNaissance(), idProduit);
            }
        }
        throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_BENEFICIAIRE_INEXISTANT));
    }

    /**
     * Calcul de l'age millesimé ou calendaire d'une personne en fonction de la date du calcul et du produit.
     * @param dateCalcul la date du calcul
     * @param dateNaissancePersonne la date de naissance de la personne
     * @param idProduit l'identifiant du produit
     * @return l'age
     */
    private Integer getAgeCalculePersonne(Calendar dateCalcul, Calendar dateNaissancePersonne, Integer idProduit) {
        // Calcul de l'age Millesimé
        final Calendar dateDeNaissance = (Calendar) dateNaissancePersonne.clone();
        int age = dateCalcul.get(Calendar.YEAR) - dateDeNaissance.get(Calendar.YEAR);

        // ON RECUPERE LES CONTRAINTES DE VENTES DU PRODUIT SI LE PRODUIT EST INDIQUE
        if (idProduit != null) {
            final ContrainteVenteDto contrainte = produitService.getContrainteVenteParProduit(idProduit);
            dateDeNaissance.add(Calendar.YEAR, age);
            if ((contrainte == null || contrainte.getAgeMillesime() == null || !contrainte.getAgeMillesime()) && dateCalcul.before(dateDeNaissance)) {
                age--;
            }
        }
        return age;
    }

    /**
     * Ordonne la liste des ligne de devis par age décroissant du bénéficiaire.
     * @param listeLignesDevis la liste des lignes de devis
     */
    private void ordonnerListeLignesDevisParAge(List<LigneDevisPourRegle> listeLignesDevis) {
        // Création d'un Comparator
        final Comparator<LigneDevisPourRegle> comparator = new Comparator<LigneDevisPourRegle>() {

            public int compare(LigneDevisPourRegle o1, LigneDevisPourRegle o2) {
                return o2.getAgeBeneficiaire().compareTo(o1.getAgeBeneficiaire());
            }
        };
        // On ordonne la liste à l'aide du comparator
        Collections.sort(listeLignesDevis, comparator);

        if (logger.isDebugEnabled()) {
            for (LigneDevisPourRegle ligneDevis : listeLignesDevis) {
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_INFO_LIGNEDEVIS_BENEFICIAIRE_AGE,
                		new String[] {String.valueOf(ligneDevis.getIdentifiant()), String.valueOf(ligneDevis.getIdentifiantBeneficiaire()),
                		String.valueOf(ligneDevis.getAgeBeneficiaire())}));
            }
        }
    }

    /**
     * Affecte pour chaque ligne de devis les rôles et les index des bénéficiaires pour chaque rôle.
     * @param listeLignesDevis
     */
    private void affectationRolePourListeLignesDevisLiees(List<LigneDevisPourRegle> listeLignesDevis) {
        final Long idLienConjoint = tarificateurSquareMappingService.getIdLienFamilialConjoint();
        final Long idLienEnfant = tarificateurSquareMappingService.getIdLienFamilialEnfant();
        final String roleAssure = regleMappingService.getConstanteRoleAssure();
        final String roleConjoint = regleMappingService.getConstanteRoleConjoint();
        final String roleEnfant = regleMappingService.getConstanteRoleEnfant();
        final Map<Long, String> mapRoleParBeneficiaire = new HashMap<Long, String>();
        final List<Long> listeConjoints = new ArrayList<Long>();
        final List<Long> listeEnfants = new ArrayList<Long>();

        // Parcours des lignes de devis liées
        for (LigneDevisPourRegle ligneDevisLiee : listeLignesDevis) {
            // Si c'est le prospect => role assuré et index = 1
            if (ligneDevisLiee.getIdentifiantBeneficiaire() == null) {
                ligneDevisLiee.setRoleBeneficiaire(roleAssure);
                ligneDevisLiee.setIndexRoleBeneficiaire(1);
            } else {
                // Affectation du role
                // On regarde si le role du bénéficiaire est déjà présent dans la map
                final String roleBeneficiaire = mapRoleParBeneficiaire.get(ligneDevisLiee.getIdentifiantBeneficiaire());
                if (roleBeneficiaire != null) {
                    // On affecte le rôle trouvé
                    ligneDevisLiee.setRoleBeneficiaire(roleBeneficiaire);
                } else {
                    // Sinon on récupère le rôle à partir de la base
                    final Beneficiaire beneficiaire = beneficiaireDao.getBeneficiaireByCible(ligneDevisLiee.getIdentifiantBeneficiaire());
                    if (beneficiaire.getLienFamilial() != null) {
                        if (beneficiaire.getLienFamilial().getId().equals(idLienConjoint)) {
                            // Si conjoint
                            ligneDevisLiee.setRoleBeneficiaire(roleConjoint);
                            // On ajoute ce bénéficiaire avec son rôle à la map
                            mapRoleParBeneficiaire.put(ligneDevisLiee.getIdentifiantBeneficiaire(), roleConjoint);
                        } else if (beneficiaire.getLienFamilial().getId().equals(idLienEnfant)) {
                            // Si enfant
                            ligneDevisLiee.setRoleBeneficiaire(roleEnfant);
                            // On ajoute ce bénéficiaire avec son rôle à la map
                            mapRoleParBeneficiaire.put(ligneDevisLiee.getIdentifiantBeneficiaire(), roleEnfant);
                        } else {
                            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_BENEFICIAIRE_ABSCENCE_LIEN_FAMILIAL,
                            		new String[] {String.valueOf(ligneDevisLiee.getIdentifiantBeneficiaire())}));
                            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_BENEFICIAIRE_SANS_LIEN_FAMILIAL));
                        }
                    } else {
                        logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_BENEFICIAIRE_ABSCENCE_LIEN_FAMILIAL,
                        		new String[] {String.valueOf(ligneDevisLiee.getIdentifiantBeneficiaire())}));
                        throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_BENEFICIAIRE_SANS_LIEN_FAMILIAL));
                    }
                }

                // Affectation de l'index du bénéficiaire en fonction du role
                if (ligneDevisLiee.getRoleBeneficiaire().equals(roleConjoint)) {
                    // Si la liste des conjoints contient déjà le bénéficiaire : on récupère l'index
                    final int indexBeneficiaire = listeConjoints.indexOf(ligneDevisLiee.getIdentifiantBeneficiaire());
                    if (indexBeneficiaire != -1) {
                        ligneDevisLiee.setIndexRoleBeneficiaire(indexBeneficiaire + 1);
                    } else {
                        // On l'ajoute à la liste
                        listeConjoints.add(ligneDevisLiee.getIdentifiantBeneficiaire());
                        ligneDevisLiee.setIndexRoleBeneficiaire(listeConjoints.size());
                    }
                } else if (ligneDevisLiee.getRoleBeneficiaire().equals(roleEnfant)) {
                    // Si la liste des enfants contient déjà le bénéficiaire : on récupère l'index
                    final int indexBeneficiaire = listeEnfants.indexOf(ligneDevisLiee.getIdentifiantBeneficiaire());
                    if (indexBeneficiaire != -1) {
                        ligneDevisLiee.setIndexRoleBeneficiaire(indexBeneficiaire + 1);
                    } else {
                        // On l'ajoute à la liste
                        listeEnfants.add(ligneDevisLiee.getIdentifiantBeneficiaire());
                        ligneDevisLiee.setIndexRoleBeneficiaire(listeEnfants.size());
                    }
                }
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_INFO_LIGNE_DEVIS_BENEFICIAIRE_ROLE_AGE_INDEX,
                		new String[] {String.valueOf(ligneDevisLiee.getIdentifiant()),
                					  String.valueOf(ligneDevisLiee.getIdentifiantBeneficiaire()),
                					  ligneDevisLiee.getRoleBeneficiaire(),
                					  String.valueOf(ligneDevisLiee.getAgeBeneficiaire()),
                					  String.valueOf(ligneDevisLiee.getIndexRoleBeneficiaire())}));
            }
        }
    }

    /**
     * Créer la recherche de règles à partir de la ligne de devis principale.
     * @param ligneDevisPrincipale la ligne de devis principale
     */
    private ReglesRechercheDto creerRechercheRegles(LigneDevisPourRegle ligneDevisPrincipale, List<Integer> listeProduitsAExclureRegles) {
        // Création du DTO de recherche de règles
        final ReglesRechercheDto reglesRechercheDto = new ReglesRechercheDto();
        // Code du produit
        reglesRechercheDto.setCodeProduit(ligneDevisPrincipale.getIdentifiantProduit());
        // Date d'effet
        reglesRechercheDto.setDateEffet(ligneDevisPrincipale.getDateEffet());

        // Création de la liste des critères de recherche de règles
        final List<ListeObjetsParDiscriminantDto<CritereRegleDto>> listeReglesRechercheCritere =
            new ArrayList<ListeObjetsParDiscriminantDto<CritereRegleDto>>();

        // Récupération de constantes
        final Integer idCritereAge = regleMappingService.getIdCritereAge();
        final Integer idCritereProduit = regleMappingService.getIdCritereProduit();
        final Integer idCritereRole = regleMappingService.getIdCritereRole();
        final Integer idCritereIndexRole = regleMappingService.getIdCritereIndexRole();
        final Integer idCritereGeneration = regleMappingService.getIdCritereGeneration();

        // Création des critères pour la ligne de devis principale
        listeReglesRechercheCritere.add(creerListeCriteresReglesPourLigneDevis(ligneDevisPrincipale, idCritereAge, idCritereProduit, idCritereRole,
            idCritereIndexRole, idCritereGeneration));

        // Création des critères pour les lignes de devis liées
        for (LigneDevisPourRegle ligneDevisLiee : ligneDevisPrincipale.getListeLignesLiees()) {
            // si il ne s'agit pas d'un produit bonus
            if (!listeProduitsAExclureRegles.contains(ligneDevisLiee.getIdentifiantProduit())) {
                listeReglesRechercheCritere.add(creerListeCriteresReglesPourLigneDevis(ligneDevisLiee, idCritereAge, idCritereProduit, idCritereRole,
                    idCritereIndexRole, idCritereGeneration));
            }
        }

        // Ajout de la liste des critères à la recherche
        reglesRechercheDto.setListeReglesRechercheCritere(listeReglesRechercheCritere);

        return reglesRechercheDto;
    }

    /**
     * Crée la liste des critères de recherche de règles pour une ligne de devis.
     * @param ligneDevisRegle la ligne de devis
     * @return la liste des critères de recherche de règles
     */
    private ListeObjetsParDiscriminantDto<CritereRegleDto> creerListeCriteresReglesPourLigneDevis(LigneDevisPourRegle ligneDevisRegle, Integer idCritereAge,
        Integer idCritereProduit, Integer idCritereRole, Integer idCritereIndexRole, Integer idCritereGeneration) {
        // Création des critères de recherche (discriminant = identifiant de la ligne de devis)
        final ListeObjetsParDiscriminantDto<CritereRegleDto> listeCriteres =
            new ListeObjetsParDiscriminantDto<CritereRegleDto>(ligneDevisRegle.getIdentifiant().toString());
        // Critere age
        final Integer age = ligneDevisRegle.getAgeBeneficiaire();
        final CritereRegleDto critereAge = new CritereRegleDto(idCritereAge, age.toString());
        listeCriteres.ajouterObjetAListe(critereAge);
        // Critere Produit
        final Integer idProduit = ligneDevisRegle.getIdentifiantProduit();
        final CritereRegleDto critereProduit = new CritereRegleDto(idCritereProduit, idProduit.toString());
        listeCriteres.ajouterObjetAListe(critereProduit);
        // Critere Role
        final String role = ligneDevisRegle.getRoleBeneficiaire();
        final CritereRegleDto critereRole = new CritereRegleDto(idCritereRole, role);
        listeCriteres.ajouterObjetAListe(critereRole);
        // Critere Index role
        final Integer indexRole = ligneDevisRegle.getIndexRoleBeneficiaire();
        final CritereRegleDto critereIndexRole = new CritereRegleDto(idCritereIndexRole, indexRole.toString());
        listeCriteres.ajouterObjetAListe(critereIndexRole);
        // Critere Index code génération
        final String codeGeneration = ligneDevisRegle.getCodeGeneration();
        final CritereRegleDto critereCodeGeneration = new CritereRegleDto(idCritereGeneration, codeGeneration);
        listeCriteres.ajouterObjetAListe(critereCodeGeneration);

        return listeCriteres;
    }

    /**
     * {@inheritDoc}
     */
    public PropositionLigneDevis genererPropositionDevisPourAdherent(Long idDevis, String produitAia, String garantieAia,
        final PersonneDto nouvellePersonnePrincipale, InfosPersonneSquareBean infosPersonnes) {
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_GENERATION_PROP_DEVIS_ADHERENT,
        		new String[] {String.valueOf(idDevis)}));

        PersonneDto personne = null;
        // Récupération du prospect dans le devis si existant
        if (idDevis != null) {
            // Récupération du devis
            final Devis devis = devisDao.getDevis(idDevis);
            personne = personneUtil.mapperPersonneEnPersonneDto(devis.getPersonnePrincipale(), infosPersonnes);
        }
        // sinon on recupere la nouvelle personne
        else {
            personne = nouvellePersonnePrincipale;
        }

        final PersonneSimpleDto personneSquare = personneUtil.getPersonneSimple(personne.getEidPersonne(), infosPersonnes);

        // Si la personne est bénéficiaire, on récupère lesproduits de l'adhérent principal :
        final Long idAdherentPrincipal = adherentService.getIdAdherentPrincipal(personneSquare.getId());
        PersonneSimpleDto adherentPrincipalSquare = null;
        if (idAdherentPrincipal != null) {
            adherentPrincipalSquare = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(idAdherentPrincipal);
        }

        // Récupération de la liste des produits de l'adhérent
        final Integer idApplicationGestcom = tarificateurMappingService.getIdentifiantApplicationGestcom();

        final ListeProduitsAdherentDto listeProduitsAdherentDto =
            produitService.getListeProduitsAdherent(adherentPrincipalSquare != null ? adherentPrincipalSquare.getId() : personneSquare.getId(), produitAia,
                garantieAia, idApplicationGestcom);

        // Formation de la proposition
        return formerPropositionAdherent(idDevis, personne, listeProduitsAdherentDto, personneSquare.getId());
    }

    /**
     * Forme une proposition de ligne de devis pour un adhérent.
     * @param personne personne
     * @param listeProduitsAdherentDto la liste des produits de l'adhérent
     * @param idAdherent l'identifiant de l'adhérent.
     * @return la proposition
     */
    private PropositionLigneDevis formerPropositionAdherent(Long idDevis, PersonneDto personne, ListeProduitsAdherentDto listeProduitsAdherentDto,
        Long idAdherent) {
        // Récupération de constante
        final Long idModeTarificationBeneficiaire = tarificateurMappingService.getConstanteIdModeTarificationBeneficiaire();
        final String codeRoleAssurePrincipal = tarificateurMappingService.getConstanteLienFamilleAssurePrincipal();

        final Integer constanteNbAnneesBonus1 = tarificateurSquareMappingService.getNbAnneesBonus1();
        final Integer constanteNbAnneesBonus2 = tarificateurSquareMappingService.getNbAnneesBonus2();
        final Integer idFamilleBonus1 = tarificateurMappingService.getIdentifiantFamilleBonus1();
        final Integer idFamilleBonus2 = tarificateurMappingService.getIdentifiantFamilleBonus2();

        // Génération de la liste des ids bénéficiaires connu de la personne courante (pour exclure de la génération les benef inconnus)
        final List<Long> idsBenefs = new ArrayList<Long>();
        for (BeneficiaireDto benef : personne.getListeBeneficiaires()) {
            idsBenefs.add(benef.getEidPersonne());
        }

        // Création de la proposition
        final PropositionLigneDevis proposition = new PropositionLigneDevis();

        // Identifiant du devis
        proposition.setIdentifiantDevis(idDevis);

        // Recherche du produit principal
        final ProduitAdherentDto produitPrincipal = listeProduitsAdherentDto.getProduitPrincipal();
        if (produitPrincipal == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_PROPOSITION_ADHERENT_IMPOSSIBLE_AUCUN_PRODUIT_PRINCIPAL));
        }
        // Génération de la ligne de devis principale
        final LigneDevisDto ligneDevisPrincipale = mapperProduitAdherentEnLigneDevisDto(produitPrincipal,
        		personne, idFamilleBonus1, idFamilleBonus2, constanteNbAnneesBonus1, constanteNbAnneesBonus2);

        // Génération des lignes liées
        final List<LigneDevisDto> listeLignesDevisLiees = new ArrayList<LigneDevisDto>();
        for (ProduitAdherentDto produit : listeProduitsAdherentDto.getListeProduitsLies()) {
            // On ne prend pas en compte le produit principal
            if (!produit.equals(produitPrincipal)) {
                // On n'ajoute pas les produits des bénéficiaires dont le code de tarification n'est pas bénéficiaire
                // sur une generation de proposition à partir d'un benef, on ne recupere pas les lignes liées
                // qui étaient sur ce benef ou sur un benef qui ne serait pas connu de l'adhérent en cours
                if ((produit.getRole() != null && codeRoleAssurePrincipal.equals(produit.getRole().getIdentifiantExterieur()))
                    || (produit.getModeTarification() != null && (idModeTarificationBeneficiaire.equals(produit.getModeTarification().getIdentifiant()))
                        && !idAdherent.equals(produit.getUidBeneficiaire()) && idsBenefs.contains(produit.getUidBeneficiaire()))) {
                    // Génération de la ligne liée
                    final LigneDevisDto ligneDevisLiee =
                        mapperProduitAdherentEnLigneDevisDto(produit, personne, idFamilleBonus1, idFamilleBonus2, constanteNbAnneesBonus1,
                            constanteNbAnneesBonus2);
                    listeLignesDevisLiees.add(ligneDevisLiee);
                }
            }
        }
        // Ajout des lignes liées à la ligne de devis principal
        ligneDevisPrincipale.setListeLignesDevisLiees(listeLignesDevisLiees);

        // Ajout de la ligne de devis principale à la proposition
        proposition.setLigneDevis(ligneDevisPrincipale);

        return proposition;
    }

    /**
     * Mappe un objet ProduitAdherentDto en LigneDevisDto.
     * @param produit le produit de l'adhérent
     * @param personne la personne
     * @return la ligne de devis
     */
    private LigneDevisDto mapperProduitAdherentEnLigneDevisDto(ProduitAdherentDto produit, PersonneDto personne, Integer idFamilleBonus1,
        Integer idFamilleBonus2, Integer constanteNbAnneesBonus1, Integer constanteNbAnneesBonus2) {

        // Création de la ligne de devis
        final LigneDevisDto ligneDevisDto = new LigneDevisDto();

        // Identifiant du produit
        ligneDevisDto.setIdentifiantProduit(produit.getIdentifiant());
        final ProduitCriteresDto criteres = new ProduitCriteresDto();
        criteres.setIdentifiantProduit(produit.getIdentifiant());
        final List<ProduitDto> listeProduits = produitService.getListeProduits(criteres);
        if (listeProduits.size() != 1) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_RECUPERATION_LIBELLE_PRODUIT));
        }
        ligneDevisDto.setLibelleProduit(listeProduits.get(0).getLibelleCommercial());
        // Généré par contrat
        ligneDevisDto.setGenererParContrat(Boolean.TRUE);

        // on initialise la date d'effet au 1er du mois suivant
        final Calendar dateEffet = DateUtil.getDatePremierMoisSuivant();
        // si il s'agit d'un produit bonus, on modifie la date d'effet
        if (idFamilleBonus1.equals(listeProduits.get(0).getIdentifiantFamille())) {
            dateEffet.add(Calendar.YEAR, constanteNbAnneesBonus1);
        }
        if (idFamilleBonus2.equals(listeProduits.get(0).getIdentifiantFamille())) {
            dateEffet.add(Calendar.YEAR, constanteNbAnneesBonus2);
        }
        ligneDevisDto.setDateEffet(dateEffet);

        // Récupération du bénéficiaire si c'est un bénéficiaire qui est associé au produit
        PersonneDto beneficiaire = null;
        final String codeRoleAssurePrincipal = tarificateurMappingService.getConstanteLienFamilleAssurePrincipal();
        if (produit.getRole() != null && produit.getRole().getIdentifiantExterieur() != null
            && !produit.getRole().getIdentifiantExterieur().equals(codeRoleAssurePrincipal)) {
            beneficiaire = getBeneficiaireProspect(produit.getUidBeneficiaire(), produit.getRole().getIdentifiantExterieur(), personne);
            ligneDevisDto.setIdentifiantBeneficiaire(beneficiaire.getId());
            ligneDevisDto.setEidBeneficiaire(beneficiaire.getEidPersonne());
        }

        // Récupération de la liste des valeurs de critères
        ligneDevisDto.setListeValeurCritereLigneDevis(getListeValeursCriteresLigneDevis(produit, personne, beneficiaire, dateEffet));

        return ligneDevisDto;
    }

    /**
     * Récupère la liste des valeurs de critères à partir de celle du produit adhérent.
     * @param produit le produit de l'adhérent
     * @param prospect le prospect associé au produit
     * @param beneficiaireProspect le bénéficiaire associé au produit (null si c'est le prospect qui est associé au produit)
     * @param dateEffet la date d'effet pour le calcul de l'age
     * @return la liste des valeurs de critères
     */
    private List<ValeurCritereLigneDevisDto> getListeValeursCriteresLigneDevis(ProduitAdherentDto produit, PersonneDto prospect,
        PersonneDto beneficiaireProspect, Calendar dateEffet) {
        // Récupération de constantes
        final Integer idCritereAgeMin = tarificateurMappingService.getIdentifiantCritereAgeMin();
        final Integer idCritereAgeMax = tarificateurMappingService.getIdentifiantCritereAgeMax();

        // Parcours de la liste des tarifs
        final List<ValeurCritereLigneDevisDto> listeValeursCriteres = new ArrayList<ValeurCritereLigneDevisDto>();
        for (TarifCritereDto tarifCritere : produit.getListeCriteres()) {
            final ValeurCritereLigneDevisDto valeurCritere = new ValeurCritereLigneDevisDto();
            valeurCritere.setIdentifiantCritere(tarifCritere.getIdentifiant());
            // Cas particulier pour les critères Age Min et Age Max
            if (tarifCritere.getIdentifiant().equals(idCritereAgeMin) || tarifCritere.getIdentifiant().equals(idCritereAgeMax)) {
                // Récupération de l'age calculé de la personne associée au produit
                // Si c'est un bénéficiaire
                if (beneficiaireProspect != null) {
                    valeurCritere.setValeur(getAgeCalculePersonne(dateEffet, beneficiaireProspect.getDateNaissance(), produit.getIdentifiant()).toString());
                } else if (prospect != null) {
                    // Sinon c'est le prospect
                    valeurCritere.setValeur(getAgeCalculePersonne(dateEffet, prospect.getDateNaissance(), produit.getIdentifiant()).toString());
                }
            } else {
                valeurCritere.setValeur(tarifCritere.getValeur());
            }
            listeValeursCriteres.add(valeurCritere);
        }
        return listeValeursCriteres;
    }

    /**
     * Récupère le bénéficiaire gestcom à partir de son codeBeneficiaire. <br>
     * Crée le bénéficiaire s'il n'existe pas dans la gestcom.
     * @param codeBeneficiaire le code du bénéficiaire
     * @param roleBeneficiaire le role du bénéficiaire (utilisé si le bénéficiaire n'existe pas dans la gestcom)
     * @param personne la personne principale du devis
     * @return l'identifiant du bénéficiaire
     */
    private PersonneDto getBeneficiaireProspect(Long uidBeneficiaire, String roleBeneficiaire, PersonneDto personne) {
        // Récupération du bénéficiaire
        final PersonneSimpleDto benef = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(uidBeneficiaire);

        // Exception si le bénéficiaire n'a pas été trouvé dans square
        if (benef != null) {
            // On recherche la copie du bénéficiaire GRC dans le devis courant :
            for (BeneficiaireDto beneficiaire : personne.getListeBeneficiaires()) {
                if (beneficiaire.getEidPersonne().equals(benef.getId())) {
                    return mapperDozerBean.map(beneficiaire, PersonneDto.class);
                }
            }
        }

        logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_BENEFICIAIRE_INEXISTANT,
        		new String[] {String.valueOf(uidBeneficiaire)}));
        throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_PROPOSITION_ADHERENT_IMPOSSIBLE_BENEFICIAIRE_INCONNU));
    }

    /**
     * Récupère le type d'un devis (créée pour les performances).
     * @param idDevis à analyser.
     * @return le type du devis
     */
    public String getTypeDevis(Long idDevis) {
        if (idDevis != null) {
            return tarificateurSquareMappingService.getTypeSantePrevoyance();
        } else {
            // Aucune ligne : type Neutre
            return tarificateurSquareMappingService.getTypeNeutre();
        }
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the messageSourceUtil value.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Set the finaliteDao value.
     * @param finaliteDao the finaliteDao to set
     */
    public void setFinaliteDao(FinaliteDao finaliteDao) {
        this.finaliteDao = finaliteDao;
    }

    /**
     * Set the personneDao value.
     * @param personneDao the personneDao to set
     */
    public void setPersonneDao(PersonneDao personneDao) {
        this.personneDao = personneDao;
    }

    /**
     * Set the beneficiaireDao value.
     * @param beneficiaireDao the beneficiaireDao to set
     */
    public void setBeneficiaireDao(BeneficiaireDao beneficiaireDao) {
        this.beneficiaireDao = beneficiaireDao;
    }

    /**
     * Set the devisDao value.
     * @param devisDao the devisDao to set
     */
    public void setDevisDao(DevisDao devisDao) {
        this.devisDao = devisDao;
    }

    /**
     * Set the ligneDevisDao value.
     * @param ligneDevisDao the ligneDevisDao to set
     */
    public void setLigneDevisDao(LigneDevisDao ligneDevisDao) {
        this.ligneDevisDao = ligneDevisDao;
    }

    /**
     * Set the tarificateurSquareMappingService value.
     * @param tarificateurSquareMappingService the tarificateurSquareMappingService to set
     */
    public void setTarificateurSquareMappingService(TarificateurSquareMappingService tarificateurSquareMappingService) {
        this.tarificateurSquareMappingService = tarificateurSquareMappingService;
    }

    /**
     * Set the produitService value.
     * @param produitService the produitService to set
     */
    public void setProduitService(ProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * Set the tarificateurMappingService value.
     * @param tarificateurMappingService the tarificateurMappingService to set
     */
    public void setTarificateurMappingService(TarificateurMappingService tarificateurMappingService) {
        this.tarificateurMappingService = tarificateurMappingService;
    }

    /**
     * Set the tarifService value.
     * @param tarifService the tarifService to set
     */
    public void setTarifService(TarifService tarifService) {
        this.tarifService = tarifService;
    }

    /**
     * Set the regleMappingService value.
     * @param regleMappingService the regleMappingService to set
     */
    public void setRegleMappingService(RegleMappingService regleMappingService) {
        this.regleMappingService = regleMappingService;
    }

    /**
     * Set the regleService value.
     * @param regleService the regleService to set
     */
    public void setRegleService(RegleService regleService) {
        this.regleService = regleService;
    }

    /**
     * Fixer la valeur.
     * @param sourceLigneDevisDao the sourceLigneDevisDao to set
     */
    public void setSourceLigneDevisDao(SourceLigneDevisDao sourceLigneDevisDao) {
        this.sourceLigneDevisDao = sourceLigneDevisDao;
    }

    /**
     * Définition de personnePhysiqueService.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Set the opportuniteServiceSquare value.
     * @param opportuniteServiceSquare the opportuniteServiceSquare to set
     */
    public void setOpportuniteServiceSquare(OpportuniteService opportuniteServiceSquare) {
        this.opportuniteServiceSquare = opportuniteServiceSquare;
    }

    /**
     * Définit la valeur de motifDevisDao.
     * @param motifDevisDao la nouvelle valeur de motifDevisDao
     */
    public void setMotifDevisDao(MotifDevisDao motifDevisDao) {
        this.motifDevisDao = motifDevisDao;
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
}
