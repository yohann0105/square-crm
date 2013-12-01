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
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.adherent.noyau.dto.adherent.contrat.ContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratSimpleDto;
import com.square.adherent.noyau.dto.adherent.contrat.ListeContratsDto;
import com.square.adherent.noyau.dto.produit.CriteresInfosProduitsDto;
import com.square.adherent.noyau.dto.produit.InfosProduitDto;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.AdherentService;
import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.ActionDto;
import com.square.core.model.dto.ActionRechercheDto;
import com.square.core.model.dto.CaisseDto;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.DimensionCritereRechercheTypeRelationDto;
import com.square.core.model.dto.DimensionCriteresRechercheCaisseDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.DimensionCriteresRechercheRegimeDto;
import com.square.core.model.dto.IdentifiantLibelleDepartementCodeDto;
import com.square.core.model.dto.IdentifiantLibelleTypeRelationDto;
import com.square.core.model.dto.OpportuniteSimpleDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.price.core.dto.ProduitCriteresDto;
import com.square.price.core.dto.ProduitDto;
import com.square.price.core.service.interfaces.ProduitService;
import com.square.price.core.service.interfaces.TarificateurMappingService;
import com.square.print.core.dto.ModeleDevisDto;
import com.square.print.core.service.interfaces.EditiqueMappingService;
import com.square.tarificateur.noyau.bean.CriteresRechercheLignesDevis;
import com.square.tarificateur.noyau.bean.PropositionLigneDevis;
import com.square.tarificateur.noyau.bean.opportunite.InfosOpportuniteSquareBean;
import com.square.tarificateur.noyau.bean.personne.InfosPersonneSquareBean;
import com.square.tarificateur.noyau.dao.interfaces.BeneficiaireDao;
import com.square.tarificateur.noyau.dao.interfaces.DevisDao;
import com.square.tarificateur.noyau.dao.interfaces.FinaliteDao;
import com.square.tarificateur.noyau.dao.interfaces.JourPaiementDao;
import com.square.tarificateur.noyau.dao.interfaces.LienFamilialDao;
import com.square.tarificateur.noyau.dao.interfaces.LigneDevisDao;
import com.square.tarificateur.noyau.dao.interfaces.MotifDevisDao;
import com.square.tarificateur.noyau.dao.interfaces.MoyenPaiementDao;
import com.square.tarificateur.noyau.dao.interfaces.OpportuniteDao;
import com.square.tarificateur.noyau.dao.interfaces.PeriodicitePaiementDao;
import com.square.tarificateur.noyau.dao.interfaces.PersonneDao;
import com.square.tarificateur.noyau.dao.interfaces.RelationAssureSocialDao;
import com.square.tarificateur.noyau.dao.interfaces.SourceLigneDevisDao;
import com.square.tarificateur.noyau.dto.InfosAdhesionDto;
import com.square.tarificateur.noyau.dto.InfosBaDto;
import com.square.tarificateur.noyau.dto.InfosOpportuniteDto;
import com.square.tarificateur.noyau.dto.InfosPaiementDto;
import com.square.tarificateur.noyau.dto.InfosPersonneDto;
import com.square.tarificateur.noyau.dto.InfosRibDto;
import com.square.tarificateur.noyau.dto.RapportDto;
import com.square.tarificateur.noyau.dto.devis.ClotureDevisQueryDto;
import com.square.tarificateur.noyau.dto.devis.CritereModeleDevisDto;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheDevisDto;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheLigneDevisDto;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheOpportuniteDto;
import com.square.tarificateur.noyau.dto.devis.DevisDto;
import com.square.tarificateur.noyau.dto.devis.DevisModificationDto;
import com.square.tarificateur.noyau.dto.devis.EnregistrementFinaliteDevisDto;
import com.square.tarificateur.noyau.dto.devis.EnregistrementFinaliteLigneDevisDto;
import com.square.tarificateur.noyau.dto.devis.LigneDevisDto;
import com.square.tarificateur.noyau.dto.devis.MotifDevisDto;
import com.square.tarificateur.noyau.dto.devis.OpportuniteDto;
import com.square.tarificateur.noyau.dto.devis.OpportuniteModificationDto;
import com.square.tarificateur.noyau.dto.devis.TransfertDevisDto;
import com.square.tarificateur.noyau.dto.personne.AdresseTarificateurDto;
import com.square.tarificateur.noyau.dto.personne.BeneficiaireDto;
import com.square.tarificateur.noyau.dto.personne.BeneficiaireTarificateurDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.dto.personne.PersonneTarificateurDto;
import com.square.tarificateur.noyau.dto.produit.adherent.InfosProduitAdherentDto;
import com.square.tarificateur.noyau.dto.produit.adherent.ListeProduitsAdherentDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.AssureSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.FamilleLieeSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.ProduitSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.SelecteurProduitDto;
import com.square.tarificateur.noyau.exception.ControleIntegriteException;
import com.square.tarificateur.noyau.model.opportunite.Adhesion;
import com.square.tarificateur.noyau.model.opportunite.Devis;
import com.square.tarificateur.noyau.model.opportunite.Finalite;
import com.square.tarificateur.noyau.model.opportunite.InfosRib;
import com.square.tarificateur.noyau.model.opportunite.LigneDevis;
import com.square.tarificateur.noyau.model.opportunite.LigneDevisLiee;
import com.square.tarificateur.noyau.model.opportunite.MotifDevis;
import com.square.tarificateur.noyau.model.opportunite.Opportunite;
import com.square.tarificateur.noyau.model.opportunite.SourceLigneDevis;
import com.square.tarificateur.noyau.model.paiement.JourPaiement;
import com.square.tarificateur.noyau.model.paiement.MoyenPaiement;
import com.square.tarificateur.noyau.model.paiement.PeriodicitePaiement;
import com.square.tarificateur.noyau.model.personne.Beneficiaire;
import com.square.tarificateur.noyau.model.personne.InfoSante;
import com.square.tarificateur.noyau.model.personne.LienFamilial;
import com.square.tarificateur.noyau.model.personne.Personne;
import com.square.tarificateur.noyau.model.personne.RelationAssureSocial;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurSquareMappingService;
import com.square.tarificateur.noyau.util.MessageKeyUtil;
import com.square.tarificateur.noyau.util.comparaison.famille.ComparaisonFamilleUtil;
import com.square.tarificateur.noyau.util.devis.DevisUtil;
import com.square.tarificateur.noyau.util.opportunite.OpportuniteUtil;
import com.square.tarificateur.noyau.util.personne.PersonneUtil;
import com.square.tarificateur.noyau.util.selecteur.produit.BuilderSelecteurProduit;
import com.square.tarificateur.noyau.util.validation.RapportUtil;
import com.square.tarificateur.noyau.util.validation.ValidationInfosAdhesionUtil;

/**
 * Implémentation de TarificateurService.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class TarificateurServiceImpl implements TarificateurService {

    /** Logger. */
    private final Logger logger = Logger.getLogger(this.getClass());

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    /**Constante.*/
    private static final int TREIZE = 13;

    /**Constante.*/
    private static final int QUINZE = 15;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Classe utilitaire pour la vérification des infos d'adhésion. */
    private ValidationInfosAdhesionUtil validationInfosAdhesionUtil;

    /** DAO pour les opportunités. */
    private OpportuniteDao opportuniteDao;

    /** DAO Finalité. */
    private FinaliteDao finaliteDao;

    /** DAO Devis. */
    private DevisDao devisDao;

    /** DAO LigneDevis. */
    private LigneDevisDao ligneDevisDao;

    /** DAO Motif de devis. */
    private MotifDevisDao motifDevisDao;

    /** DAO Moyen de paiement. */
    private MoyenPaiementDao moyenPaiementDao;

    /** DAO Periodicité de paiement. */
    private PeriodicitePaiementDao periodicitePaiementDao;

    /** DAO Jour de paiement. */
    private JourPaiementDao jourPaiementDao;

    /** Service de mapping. */
    private TarificateurSquareMappingService tarificateurSquareMappingService;

    /** Service de mapping. */
    private SquareMappingService squareMappingService;

    /** Service contrat. */
    private ContratService contratService;

    /** Service Produit. */
    private ProduitService produitService;

    /** Service de mapping du tarificateur. */
    private TarificateurMappingService tarificateurMappingService;

    /** Dao Personne. */
    private PersonneDao personneDao;

    /** Service de mapping des adhérents. */
    private AdherentMappingService adherentMappingService;

    /** Dao RelationAssureSocialD. */
    private RelationAssureSocialDao relationAssureSocialDao;

    /** Dao Beneficiaire. */
    private BeneficiaireDao beneficiaireDao;

    /** Dao LienFamilial. */
    private LienFamilialDao lienFamilialDao;

    /** Service Dimension. */
    private DimensionService dimensionService;

    /** Service de mapping de l'editique. */
    private EditiqueMappingService editiqueMappingService;

    /** Service Opportunité Square. */
    private OpportuniteService opportuniteServiceSquare;

    /** Service Action. */
    private ActionService actionService;

    /** Service PersonnePhysique. */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Service Personne. */
    private PersonneService personneService;

    /** Liste de Custom Converter pour BeanUtils. */
    private Map<Class<? extends Object>, Converter> customConverter;

    /** Devis Util. */
    private DevisUtil devisUtil;

    /** PersonneUtil. */
    private PersonneUtil personneUtil;

    /** OpportuniteUtil. */
    private OpportuniteUtil opportuniteUtil;

    /** TarificateurPersonneService. */
    private TarificateurPersonneService tarificateurPersonneService;

    /** DAO des sources de ligne de devis. */
    private SourceLigneDevisDao sourceLigneDevisDao;

    /** Service adhérent. */
    private AdherentService adherentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public LigneDevisDto addLigneDevisParSelecteurProduit(SelecteurProduitDto selecteurProduit) {
        final BuilderSelecteurProduit builderSelecteurProduit =
            new BuilderSelecteurProduit(mapperDozerBean, produitService, tarificateurMappingService, tarificateurSquareMappingService, contratService,
                dimensionService, personneDao, messageSourceUtil, adherentMappingService, personnePhysiqueService);
        // on recupere la proposition à partir du selecteur
        final PropositionLigneDevis proposition = builderSelecteurProduit.getPropositionParSelecteurProduit(selecteurProduit);
        // on ajoute la ligne
        final LigneDevisDto ligneDevis = devisUtil.addLigneDevis(proposition);
        // on retourne la ligne de devis (enrichi de l'identifiant externe éventuellement communiqué dans la prosposition)
        ligneDevis.setIdentifiantExterne(selecteurProduit.getIdentifiantExterne());
        return ligneDevis;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LigneDevisDto addLigneDevisParSelecteurProduit(SelecteurProduitDto selecteurProduit, InfosOpportuniteDto infosOpportunite) {
        // on cree un devis si il n'existe pas
        if (selecteurProduit.getIdentifiantDevis() == null && infosOpportunite != null) {
            final DevisDto devisDto = createDevis(infosOpportunite);
            // on met l'id du devis
            selecteurProduit.setIdentifiantDevis(devisDto.getId());

            // on cree une map pour retrouver les ids des benefs facilement
            final Map<String, Long> mapIdsBenefs = new HashMap<String, Long>();
            for (BeneficiaireDto beneficiaire : devisDto.getPersonnePrincipale().getListeBeneficiaires()) {
                mapIdsBenefs.put(getBeneficiaireConcatene(beneficiaire.getNom(), beneficiaire.getPrenom(), beneficiaire.getDateNaissance()), beneficiaire
                        .getId());
            }

            // on remplit les id des personnes à partir de nom/prenom/dateNaissance
            final Long idPersonnePrincipale = devisDto.getPersonnePrincipale().getId();
            selecteurProduit.getFamillePrincipale().getProduitPrincipal().getAssurePrincipal().setIdentifiant(idPersonnePrincipale);
            if (selecteurProduit.getFamillePrincipale().getProduitPrincipal().getListeBeneficiaires() != null) {
                for (AssureSelecteurDto benef : selecteurProduit.getFamillePrincipale().getProduitPrincipal().getListeBeneficiaires()) {
                    benef.setIdentifiant(mapIdsBenefs.get(getBeneficiaireConcatene(benef.getNom(), benef.getPrenom(), benef.getDateNaissance())));
                }
            }
            for (FamilleLieeSelecteurDto familleLiee : selecteurProduit.getListeFamillesLiees()) {
                for (ProduitSelecteurDto produitLie : familleLiee.getListeProduitsLies()) {
                    produitLie.getAssurePrincipal().setIdentifiant(idPersonnePrincipale);
                    if (produitLie.getListeBeneficiaires() != null) {
                        for (AssureSelecteurDto benef : produitLie.getListeBeneficiaires()) {
                            benef.setIdentifiant(mapIdsBenefs.get(getBeneficiaireConcatene(benef.getNom(), benef.getPrenom(), benef.getDateNaissance())));
                        }
                    }
                }
            }
        }
        return addLigneDevisParSelecteurProduit(selecteurProduit);
    }

    @Override
    public void majFamilleDevis(final Long idDevis, PersonneTarificateurDto personne) {
        // RECUPERATION DU DEVIS
        final Devis devis = devisDao.getDevis(idDevis);
        if (devis == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MAJ_FAMILLE_DEVIS_IMPOSSIBLE_DEVIS_INEXISTANT,
            		 new String[] {String.valueOf(idDevis)}));
        }
        // MAJ DE LA PERSONNE PRINCIPAL
        mapperDozerBean.map(personne, devis.getPersonnePrincipale());

        // BENEIFICIARES QUI N'EXISTE PAS ENCORE
        for (BeneficiaireTarificateurDto beneficiaireParcouru : personne.getListeBeneficiaires()) {
            // RECHERCHE DANS LES BENEFICIAIRES EXISTANTS
            boolean findBenef = false;
            for (Beneficiaire benef : devis.getPersonnePrincipale().getListeBeneficiaires()) {
                if (benef.getPersonneCible().getEidPersonne().equals(beneficiaireParcouru.getEidPersonne())) {

                    mapperDozerBean.map(beneficiaireParcouru, benef.getPersonneCible());
                    findBenef = true;
                    break;
                }
            }
            if (!findBenef) {
                final Beneficiaire beneficiaire = new Beneficiaire();
                beneficiaire.setPersonneSource(devis.getPersonnePrincipale());
                beneficiaire.setPersonneCible((Personne) mapperDozerBean.map(beneficiaireParcouru, Personne.class));
                final LienFamilial lienFamilial = lienFamilialDao.getLienFamilial(beneficiaireParcouru.getIdLienFamilial());
                if (lienFamilial == null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CREATE_DEVIS_LIEN_FAMILIAL_NULL, new String[] {beneficiaireParcouru
                            .getIdLienFamilial().toString()}));
                }
                beneficiaire.setLienFamilial(lienFamilial);
                devis.getPersonnePrincipale().getListeBeneficiaires().add(beneficiaire);
            }
        }
    }

    @Override
    public LigneDevisDto addLigneDevis(Long idDevis, LigneDevisDto ligneDevis) {
        // on recupere la proposition à partir du selecteur
        final PropositionLigneDevis proposition = new PropositionLigneDevis();
        proposition.setIdentifiantDevis(idDevis);
        proposition.setLigneDevis(ligneDevis);
        // on ajoute la ligne
        return devisUtil.addLigneDevis(proposition);
    }

    private String getBeneficiaireConcatene(String nom, String prenom, Calendar dateNaissance) {
        final SimpleDateFormat sdfBenef;
    	sdfBenef = new SimpleDateFormat(messageSourceUtil.get(MessageKeyUtil.DATE_FORMAT_DDMMYYYY));
        return nom + prenom + sdfBenef.format(dateNaissance.getTime());
    }

    @Override
    public void cloturerDevisQuery(ClotureDevisQueryDto query) {
        devisUtil.cloturerDevisQuery(query);
    }

    @Override
    public LigneDevisDto getLigneDevisParIdentifiant(Long idLigneDevis) {
        return devisUtil.getLigneDevisParIdentifiant(idLigneDevis);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enregistrerFinaliteDevis(EnregistrementFinaliteDevisDto enregistrementFinaliteDevisDto) {
        // Récupération du devis
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CLOTURE_DEVIS,
       		 new String[] {String.valueOf(enregistrementFinaliteDevisDto.getIdDevis())}));

        final RapportDto rapportErreur = new RapportDto();
        if (enregistrementFinaliteDevisDto.getIdMotifDevis() == null) {
            rapportErreur.ajoutRapport("idMotifDevis", messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_CLOTURE_IMPOSSIBLE_MOTIF_DEVIS_NON_RENSEIGNE), true);
        }

        if (BooleanUtils.isTrue(rapportErreur.getEnErreur())) {
            RapportUtil.logRapport(rapportErreur, logger);
            throw new ControleIntegriteException(rapportErreur);
        }

        final Devis devis = devisDao.getDevis(enregistrementFinaliteDevisDto.getIdDevis());
        if (devis == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_CLOTURE_IMPOSSIBLE_DEVIS_INEXISTANT,
              		 new String[] {String.valueOf(enregistrementFinaliteDevisDto.getIdDevis())}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEMANDE_CLOTURE_IMPOSSIBLE_DEVIS_INEXISTANT));
        }

        // Récupération des lignes à modifier
        final Map<Long, Long> mapEnregistrementLignes = new HashMap<Long, Long>();
        for (EnregistrementFinaliteLigneDevisDto enregistrementLigne : enregistrementFinaliteDevisDto.getListeEnregistrementsFinaliteLignes()) {
            mapEnregistrementLignes.put(enregistrementLigne.getIdentifiantLigneDevis(), enregistrementLigne.getIdentifiantFinaliteLigneDevis());
        }

        final Long idMotifDevis = enregistrementFinaliteDevisDto.getIdMotifDevis();
        if (idMotifDevis != null) {
            final MotifDevis motifDevis = motifDevisDao.getMotifDevisById(idMotifDevis);
            if (motifDevis == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_MOTIF_DEVIS_INEXISTANT,
                 		 new String[] {String.valueOf(idMotifDevis)}));
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MOTIF_DEVIS_INEXISTANT));
            }
            devis.setMotif(motifDevis);
        }

        // Si le devis ne contient aucune ligne, on ne fait rien
        if (devis.getListeLigneDevis().size() != 0) {
            // Mise à jour des lignes demandées
            for (LigneDevis ligne : devis.getListeLigneDevis()) {
                // Si la ligne doit être modifiée
                final Long idFinaliteLigne = mapEnregistrementLignes.get(ligne.getId());
                if (idFinaliteLigne != null) {

                    final Finalite finalite = finaliteDao.getFinaliteById(idFinaliteLigne);
                    if (finalite != null) {
                        ligne.setFinalite(finalite);
                        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_FINALITE_LIGNE,
                        		 new String[] {String.valueOf(idFinaliteLigne), finalite.getLibelle()}));
                    }
                }
            }
            // Recalcul de la finalité du devis et de l'opportunité
            devisUtil.recalculerFinaliteDevisEtOpportunite(devis);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InfosAdhesionDto getInfosAdhesion(Long idDevis) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_INFO_ADHESION_DEVIS,
       		 new String[] {String.valueOf(idDevis)}));
        // Récupération du devis
        final Devis devis = devisDao.getDevis(idDevis);
        if (devis == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_DEVIS_INEXISTANT,
              		 new String[] {String.valueOf(idDevis)}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEVIS_INEXISTANT));
        }
        // Récupération des personnes
        final Personne personnePrincipale = devis.getPersonnePrincipale();
        if (personnePrincipale == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_DEVIS_PERSONNE_PRINCIPALE_INEXISTANTE,
             		 new String[] {String.valueOf(idDevis)}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AUCUNE_PERSONNE_PRINCIPALE_POUR_DEVIS));
        }
        // Récupération de l'adhésion à partir de l'opportunité
        final Opportunite opportunite = devis.getOpportunite();
        if (opportunite == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_DEVIS_OPPORTUNITE_INEXISTANTE,
            		 new String[] {String.valueOf(idDevis)}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AUCUNE_OPPORTUNITE_POUR_DEVIS));
        }
        final Adhesion adhesion = opportunite.getAdhesion();
        if (adhesion == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_OPPORTUNITE_ADHESION_INEXISTANTE,
           		 new String[] {String.valueOf(idDevis)}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AUCUNE_ADHESION_POUR_OPPORTUNITE));
        }
        // Mapping des données
        final InfosAdhesionDto infosAdhesion = new InfosAdhesionDto();
        infosAdhesion.setIdDevis(idDevis);
        // Mapping des données des personnes
        final List<InfosPersonneDto> listeInfosPersonnes = new ArrayList<InfosPersonneDto>();
        final InfosPersonneDto infosPersonnePrincipale = mapperDozerBean.map(personnePrincipale, InfosPersonneDto.class);
        final PersonneSimpleDto personnePrincipaleSquare = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(personnePrincipale.getEidPersonne());
        infosPersonnePrincipale.setNom(personnePrincipaleSquare.getNom());
        infosPersonnePrincipale.setPrenom(personnePrincipaleSquare.getPrenom());
        infosPersonnePrincipale.setNumClient(personnePrincipaleSquare.getNumeroClient());
        getInfosAdhesionRelation(infosPersonnePrincipale);
        listeInfosPersonnes.add(infosPersonnePrincipale);
        // Mapping des bénéficiaires
        if (personnePrincipale.getListeBeneficiaires() != null && !personnePrincipale.getListeBeneficiaires().isEmpty()) {
            for (Beneficiaire beneficiaire : personnePrincipale.getListeBeneficiaires()) {
                // Seulement si ils sont présent sur une des lignes du devis
                Boolean estDansLeDevis = false;
                final List<LigneDevis> lignesPrincipales = ligneDevisDao.getLigneDevisPrincipalParIdDevis(idDevis);
                for (LigneDevis lp : lignesPrincipales) {
                    final List<LigneDevis> lignesLies = ligneDevisDao.getLignesLieesLignePrincipale(lp.getId());
                    for (LigneDevis ll : lignesLies) {
                        if (ll.getBeneficiaire() != null && ll.getBeneficiaire().getId() == beneficiaire.getPersonneCible().getId()) {
                            estDansLeDevis = true;
                            break;
                        }
                    }
                    if (estDansLeDevis) {
                        break;
                    }
                }
                if (estDansLeDevis) {
                    final InfosPersonneDto infosBeneficiaire = mapperDozerBean.map(beneficiaire.getPersonneCible(), InfosPersonneDto.class);
                    final IdentifiantLibelleDto lienFamilial = mapperDozerBean.map(beneficiaire.getLienFamilial(), IdentifiantLibelleDto.class);
                    infosBeneficiaire.setLienFamilial(lienFamilial);
                    final PersonneSimpleDto beneficiaireSquare =
                        personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(beneficiaire.getPersonneCible().getEidPersonne());
                    infosBeneficiaire.setNom(beneficiaireSquare.getNom());
                    infosBeneficiaire.setPrenom(beneficiaireSquare.getPrenom());
                    infosBeneficiaire.setNumClient(beneficiaireSquare.getNumeroClient());
                    getInfosAdhesionRelation(infosBeneficiaire);
                    listeInfosPersonnes.add(infosBeneficiaire);
                }
            }
        }
        Collections.sort(listeInfosPersonnes, new Comparator<InfosPersonneDto>() {
            @Override
            public int compare(InfosPersonneDto o1, InfosPersonneDto o2) {
                if (o1.getLienFamilial() == null || o1.getLienFamilial().getIdentifiant() == null) {
                    return -1;
                }
                if (o2.getLienFamilial() == null || o2.getLienFamilial().getIdentifiant() == null) {
                    return -1;
                }
                if (o1.getLienFamilial().getIdentifiant().compareTo(o2.getLienFamilial().getIdentifiant()) != 0) {
                    return o1.getLienFamilial().getIdentifiant().compareTo(o2.getLienFamilial().getIdentifiant());
                }
                return 0;
            }
        });
        infosAdhesion.setInfosPersonnes(listeInfosPersonnes);


        // Mapping des infos sur l'adhesion
        infosAdhesion.setDateClicBA(adhesion.getDateClicBA());
        infosAdhesion.setDateTelechargementBA(adhesion.getDateTelechargementBA());
        InfosPaiementDto infosPaiement = new InfosPaiementDto();
        InfosRibDto infosRib = null;
        infosPaiement = mapperDozerBean.map(adhesion, InfosPaiementDto.class);
        if (adhesion.getInfosRib() != null) {
        	infosRib = mapperDozerBean.map(adhesion.getInfosRib(), InfosRibDto.class);
        }

        final Long idContratSante = adherentMappingService.getIdContratSante();
        final int jourPaiementDefault = adherentMappingService.getJourPaiementPrelevementCinq();
        final Long idFrequencePaiementDefault = adherentMappingService.getIdFrequencePrelevementMensuelle();
        final Long idMoyenPaiementPrelevement = adherentMappingService.getIdMoyenPaiementPrelevement();
        final Long idMoyenPaiementVirement = adherentMappingService.getIdMoyenPaiementVirement();

        ContratDto contratRecent = null;
        if (adhesion.getMoyenPaiement() == null
        		&& adhesion.getPeriodicitePaiement() == null
        		&& adhesion.getJourPaiement() == null) {
        	final ListeContratsDto contrats = contratService.getListeContrats(personnePrincipaleSquare.getId());
        	// Si la personne a au moins un contrat, on recopie les informations du plus récent
            if (contrats != null && contrats.getListeContrats() != null && contrats.getListeContrats().size() > 0) {
                for (ContratSimpleDto contratSimple : contrats.getListeContrats()) {
                	// si le contrat est un contrat de santé
                	if (idContratSante.equals(contratSimple.getNature().getIdentifiant())) {
                        final ContratDto contrat = contratService.getContrat(contratSimple.getIdentifiant());
		                if (contratRecent == null || contratRecent.getDateSignature().before(contrat.getDateSignature())) {
		                	contratRecent = contrat;
		                }
                	}
                }

                // Si on a trouvé un contrat avec des infos de paiement valide, on remplit les infos de paiement de l'adhésion
                if (contratRecent != null) {
                	if (contratRecent.getInfosPaiementCotisation() != null
                			&& contratRecent.getInfosPaiementCotisation().getMoyenPaiement() != null
		                    && contratRecent.getInfosPaiementCotisation().getFrequencePaiement() != null
		                    && contratRecent.getInfosPaiementCotisation().getJourPaiement() != null) {
                		// On convertit les infos de paiement côté contrat en infos de paiement côté tarificateur
	                    final IdentifiantLibelleDto moyenPaiementCritere =
	                        new IdentifiantLibelleDto(null, contratRecent.getInfosPaiementCotisation().getMoyenPaiement().getLibelle());
	                    final List<MoyenPaiement> listMoyenPaiement = moyenPaiementDao.rechercherMoyensPaiementParCriteres(moyenPaiementCritere);
	                    if (listMoyenPaiement != null && listMoyenPaiement.size() != 0) {
	                    	infosPaiement.setIdMoyenPaiement(listMoyenPaiement.get(0).getId());
	                    }
	                    final IdentifiantLibelleDto periodicitePaiementCritere =
	                        new IdentifiantLibelleDto(null, contratRecent.getInfosPaiementCotisation().getFrequencePaiement().getLibelle());
	                    final List<PeriodicitePaiement> listPeriodicitePaiement =
	                        periodicitePaiementDao.rechercherPeriodicitesPaiementParCriteres(periodicitePaiementCritere);
	                    if (listPeriodicitePaiement != null && listPeriodicitePaiement.size() != 0) {
	                        infosPaiement.setIdPeriodicitePaiement(listPeriodicitePaiement.get(0).getId());
	                    }
	                    final IdentifiantLibelleDto jourPaiementCritere =
	                        new IdentifiantLibelleDto(null, contratRecent.getInfosPaiementCotisation().getJourPaiement().toString());
	                    final List<JourPaiement> listJourPaiement = jourPaiementDao.rechercherJoursPaiementParCriteres(jourPaiementCritere);
	                    if (listJourPaiement != null && listJourPaiement.size() != 0) {
	                        infosPaiement.setIdJourPaiement(listJourPaiement.get(0).getId());
	                    }
	                    // récupération des informations du RIB
	                    if (adhesion.getInfosRib() == null && contratRecent.getInfoBanqueCotisation() != null) {
	                    	infosRib = mapperDozerBean.map(contratRecent.getInfoBanqueCotisation(), InfosRibDto.class);
	                    }
                	} else {
                		if (contratRecent.getInfosPaiementPrestation() != null
                				&& idMoyenPaiementVirement.equals(contratRecent.getInfosPaiementPrestation()
                						.getMoyenPaiement().getIdentifiant())) {
                			// On convertit les infos de paiement côté contrat en infos de paiement côté tarificateur
                			infosPaiement.setIdMoyenPaiement(idMoyenPaiementPrelevement);
    	                    // si il n'existe pas de frequence de paiement prestation on en crée une par défaut
    	                    Long idFrequencePaiementPrestation = idFrequencePaiementDefault;
    	                    if (contratRecent.getInfosPaiementPrestation().getFrequencePaiement() != null) {
    	                    	idFrequencePaiementPrestation = contratRecent.getInfosPaiementPrestation().getFrequencePaiement().getIdentifiant();
    	                    }
    	                    infosPaiement.setIdPeriodicitePaiement(idFrequencePaiementPrestation);
    	                    // si il n'existe pas de jour de paiement prestation on en crée un par défaut
    	                    String jourPaiementPrestation = "" + jourPaiementDefault;
    	                    if (contratRecent.getInfosPaiementPrestation().getJourPaiement() != null) {
    	                    	jourPaiementPrestation = "" + contratRecent.getInfosPaiementPrestation().getJourPaiement();
    	                    }
    	                    final IdentifiantLibelleDto jourPaiementCritere =
    	                        new IdentifiantLibelleDto(null, jourPaiementPrestation);
    	                    final List<JourPaiement> listJourPaiement = jourPaiementDao.rechercherJoursPaiementParCriteres(jourPaiementCritere);
    	                    if (listJourPaiement != null && listJourPaiement.size() != 0) {
    	                        infosPaiement.setIdJourPaiement(listJourPaiement.get(0).getId());
    	                    }
    	                    // récupération des informations du RIB
    	                    if (adhesion.getInfosRib() == null && contratRecent.getInfoBanquePrestation() != null) {
    	                    	infosRib = mapperDozerBean.map(contratRecent.getInfoBanquePrestation(), InfosRibDto.class);
    	                    }
                		}
                	}
                }
            }
        }

        infosAdhesion.setInfosPaiement(infosPaiement);
        infosAdhesion.setInfosRib(infosRib);
        infosAdhesion.setTeletransmission(adhesion.getTeletransmission());
        infosAdhesion.setAncienneComplemetaireAdherent(adhesion.getAncienneComplemetaireAdherent());
        infosAdhesion.setAncienneComplemetaireConjoint(adhesion.getAncienneComplemetaireConjoint());
        infosAdhesion.setDateFinContratAdherent(adhesion.getDateFinContratAdherent());
        infosAdhesion.setDateFinContratConjoint(adhesion.getDateFinContratConjoint());
        return infosAdhesion;
    }

    /**
     * Recherche de la relation entre une personne et son assuré social.
     * @param infosPersonneDto la personne.
     */
    private void getInfosAdhesionRelation(InfosPersonneDto infosPersonneDto) {
        final IdentifiantLibelleDto relation = tarificateurPersonneService.rechercherRelationAssureSocial(infosPersonneDto.getId());
        infosPersonneDto.setTypeRelationAssureSocial(relation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInfosAdhesion(InfosAdhesionDto infosAdhesion) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MAJ_INFO_ADHESION_DEVIS,
          		 new String[] {String.valueOf(infosAdhesion.getIdDevis())}));
        // Récupération du devis
        final Devis devis = devisDao.getDevis(infosAdhesion.getIdDevis());
        if (devis == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_DEVIS_INEXISTANT,
             		 new String[] {String.valueOf(infosAdhesion.getIdDevis())}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEVIS_INEXISTANT));
        }
        // Récupération de l'adhésion à partir de l'opportunité
        final Opportunite opportunite = devis.getOpportunite();
        if (opportunite == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_DEVIS_OPPORTUNITE_INEXISTANTE,
             		 new String[] {String.valueOf(infosAdhesion.getIdDevis())}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AUCUNE_OPPORTUNITE_POUR_DEVIS));
        }
        final Adhesion adhesion = opportunite.getAdhesion();
        if (adhesion == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_OPPORTUNITE_ADHESION_INEXISTANTE,
             		 new String[] {String.valueOf(opportunite.getId())}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AUCUNE_ADHESION_POUR_OPPORTUNITE));
        }

        final RapportDto rapportErreurs = validationInfosAdhesionUtil.validerInformationsAdhesionPourMiseAJour(infosAdhesion);
        if (Boolean.TRUE.equals(rapportErreurs.getEnErreur())) {
            RapportUtil.logRapport(rapportErreurs, logger);
            throw new ControleIntegriteException(rapportErreurs);
        }

        // Mapping des infos de personnes
        for (InfosPersonneDto infoPersonne : infosAdhesion.getInfosPersonnes()) {
            // Récupération de la personne
            final Personne personne = personneDao.getPersonne(infoPersonne.getId());
            if (personne == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
                		 new String[] {String.valueOf(infoPersonne.getId())}));
                throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANTE));
            }
            // Mapping des infos de la personne
            mapperDozerBean.map(infoPersonne, personne);

            // on supprime l'ancienne relation avec un assuré social, on la recréera ensuite
            if (personne.getRelationAssureSocial() != null) {
                relationAssureSocialDao.deleteRelationAssureSocial(personne.getRelationAssureSocial().getId());
                personne.setRelationAssureSocial(null);
            }

            // on mappe les infos de santé à la main
            if (infoPersonne.getInfoSante() != null && infoPersonne.getInfoSante().getIdReferent() != null
                && !infoPersonne.getInfoSante().getIdReferent().equals(infoPersonne.getId())) {
                // Récupération du referent
                final Personne referent = personneDao.getPersonne(infoPersonne.getInfoSante().getIdReferent());
                if (referent == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
                   		 new String[] {String.valueOf(infoPersonne.getInfoSante().getIdReferent())}));
                    throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANTE));
                }
                // on recupere les infos santé du referent
                personne.setInfoSante(referent.getInfoSante());

                // on crée la nouvelle relation
                if (infoPersonne.getTypeRelationAssureSocial() != null) {
                    lierPersonneAAssureSocial(referent, personne, infoPersonne.getTypeRelationAssureSocial());
                    final RelationAssureSocial relationAssureSocial = new RelationAssureSocial();
                    relationAssureSocial.setPersonne(personne);
                    relationAssureSocial.setAssureSocial(referent);
                    relationAssureSocial.setLienFamilial(lienFamilialDao.getLienFamilial(infoPersonne.getTypeRelationAssureSocial().getIdentifiant()));
                    personne.setRelationAssureSocial(relationAssureSocial);
                }
            }
            else {
                // si il n'a pas encore d'info de santé, ou si il n'est pas le referent de ces infos de sante
                if (personne.getInfoSante() == null || personne.getInfoSante().getReferent() == null
                    || !personne.getInfoSante().getReferent().getId().equals(infoPersonne.getId())) {
                    personne.setInfoSante(new InfoSante());
                }
                // on mappe normalement
                mapperDozerBean.map(infoPersonne.getInfoSante(), personne.getInfoSante());
                personne.getInfoSante().setReferent(personne);
                // on teste si la teletransission a été mise a jour a la main
                if (adhesion.getTeletransmission() != null && adhesion.getTeletransmission().equals(infosAdhesion.getTeletransmission())) {
                    // si ce n'est pas le cas on prépare la valeur de la donnée
                    if (personne.getInfoSante() != null && personne.getInfoSante().getEidCaisse() != null) {
                        final CaisseDto caisse = dimensionService.rechercherCaisseParId(personne.getInfoSante().getEidCaisse());
                        infosAdhesion.setTeletransmission(Boolean.valueOf(StringUtils.equals(squareMappingService.getInfosTeletransmissionCaisseOui(), caisse
                                .getTeletrans())));
                    }
                }
            }
        }

        // Mapping des infos de paiement dans l'adhésion
        if (infosAdhesion.getInfosPaiement() != null) {
            // Mapping automatique
            mapperDozerBean.map(infosAdhesion.getInfosPaiement(), adhesion);

            // Mapping Manuel
            final Long idMoyenPaiementPremierReglement = infosAdhesion.getInfosPaiement().getIdMoyenPaiementPremierReglement();
            final Long idMoyenPaiement = infosAdhesion.getInfosPaiement().getIdMoyenPaiement();
            final Long idPeriodicitePaiement = infosAdhesion.getInfosPaiement().getIdPeriodicitePaiement();
            final Long idJourPaiement = infosAdhesion.getInfosPaiement().getIdJourPaiement();
            if (idMoyenPaiementPremierReglement != null) {
                final MoyenPaiement moyenPaiementPremierReglement = moyenPaiementDao.getMoyenPaiement(idMoyenPaiementPremierReglement);
                if (moyenPaiementPremierReglement == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_MOYEN_PAIEMENT_PREMIER_REGLEMENT_INEXISTANT,
                      		 new String[] {String.valueOf(idMoyenPaiementPremierReglement)}));
                    throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_MOYEN_PAIEMENT_INEXISTANT));
                }
                adhesion.setMoyenPaiementPremierReglement(moyenPaiementPremierReglement);
            }
            else {
                adhesion.setMoyenPaiementPremierReglement(null);
            }
            if (idMoyenPaiement != null) {
                final MoyenPaiement moyenPaiement = moyenPaiementDao.getMoyenPaiement(idMoyenPaiement);
                if (moyenPaiement == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_MOYEN_PAIEMENT_INEXISTANT,
                     		 new String[] {String.valueOf(idMoyenPaiement)}));
                    throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_MOYEN_PAIEMENT_INEXISTANT));
                }
                adhesion.setMoyenPaiement(moyenPaiement);
            }
            else {
                adhesion.setMoyenPaiement(null);
            }

            if (idPeriodicitePaiement != null) {
                final PeriodicitePaiement periodicitePaiement = periodicitePaiementDao.getPeriodicitePaiement(idPeriodicitePaiement);
                if (periodicitePaiement == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERIODICITE_PAIEMENT_INEXISTANTE,
                    		 new String[] {String.valueOf(idPeriodicitePaiement)}));
                    throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERIODICITE_PAIEMENT_INEXISTANT));
                }
                adhesion.setPeriodicitePaiement(periodicitePaiement);
            }
            else {
                adhesion.setPeriodicitePaiement(null);
            }

            if (idJourPaiement != null) {
                final JourPaiement jourPaiement = jourPaiementDao.getJourPaiement(idJourPaiement);
                if (tarificateurSquareMappingService.getIdMoyenPaiementPrelevement().equals(idMoyenPaiement) && jourPaiement == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_JOUR_PAIEMENT_INEXISTANT,
                   		 new String[] {String.valueOf(idJourPaiement)}));
                    throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_JOUR_PAIEMENT_INEXISTANT));
                }
                adhesion.setJourPaiement(jourPaiement);
            }
            else {
                adhesion.setJourPaiement(null);
            }
        }

        adhesion.setTeletransmission(infosAdhesion.getTeletransmission());
        // Mapping des infos de signature numérique
        adhesion.setInfosRib((InfosRib) mapperDozerBean.map(infosAdhesion.getInfosRib(), InfosRib.class));
        adhesion.setIdFichierCNS(infosAdhesion.getIdFichierCNS());
        // Mapping des anciennes complémentaires
        if (infosAdhesion.getAncienneComplemetaireAdherent() != null && !"".equals(infosAdhesion.getAncienneComplemetaireAdherent())) {
            adhesion.setAncienneComplemetaireAdherent(infosAdhesion.getAncienneComplemetaireAdherent());
        }
        if (infosAdhesion.getDateFinContratAdherent() != null) {
            adhesion.setDateFinContratAdherent(infosAdhesion.getDateFinContratAdherent());
        }
        if (infosAdhesion.getAncienneComplemetaireConjoint() != null && !"".equals(infosAdhesion.getAncienneComplemetaireConjoint())) {
            adhesion.setAncienneComplemetaireConjoint(infosAdhesion.getAncienneComplemetaireConjoint());
        }
        if (infosAdhesion.getDateFinContratConjoint() != null) {
            adhesion.setDateFinContratConjoint(infosAdhesion.getDateFinContratConjoint());
        }
    }

    /**
     * Crée dans square la relation de type assuré social entre son bénéficiaire et l'assuré.
     * @param assureSocial l'assuré social
     * @param personne la personne sur le devis
     * @param lienFamilial le lien familial entre les deux personnes
     */
    private void lierPersonneAAssureSocial(Personne assureSocial, Personne personne, IdentifiantLibelleDto lienFamilial) {
        // récupération du type de relation 'assuré social'
        final DimensionCritereRechercheTypeRelationDto criteresTypeAssure = new DimensionCritereRechercheTypeRelationDto();
        final Long idTypeRelationAssureSocial = squareMappingService.getIdTypeRelationAssureSociale();
        criteresTypeAssure.setId(idTypeRelationAssureSocial);
        final List<IdentifiantLibelleTypeRelationDto> listeTypeRelations = dimensionService.rechercherTypesRelations(criteresTypeAssure);
        if (listeTypeRelations.size() != 1) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_RECUP_TYPE_RELATION));
        }
        final IdentifiantLibelleDto typeRelationAssureSocial =
            new IdentifiantLibelleDto(listeTypeRelations.get(0).getIdentifiant(), listeTypeRelations.get(0).getLibelle());

        // on cherche si il existe déjà une relation "assuré social" entre les deux
        final RelationCriteresRechercheDto criteresRelation = new RelationCriteresRechercheDto();
        criteresRelation.setIdPersonneSource(personne.getEidPersonne());
        criteresRelation.setIdPersonneCible(assureSocial.getEidPersonne());
        final List<Long> listeTypes = new ArrayList<Long>();
        listeTypes.add(idTypeRelationAssureSocial);
        criteresRelation.setTypes(listeTypes);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelation, 0, Integer.MAX_VALUE);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> relations = personneService.rechercherRelationsParCritreres(criterias);
        if (relations.getListResults().isEmpty()) {
            // on crée la relation assuré sociale
            final RelationDto relationAssureSocial = new RelationDto();
            relationAssureSocial.setDateDebut(Calendar.getInstance());
            relationAssureSocial.setIdPersonnePrincipale(personne.getEidPersonne());
            relationAssureSocial.setIdPersonne(assureSocial.getEidPersonne());
            relationAssureSocial.setType(typeRelationAssureSocial);
            personneService.creerRelation(relationAssureSocial);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleDto> getListeCaisses(Long idPersonne, Long idRegime) {
        // Vérification des paramètres
        if (idPersonne == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_LISTE_CAISSES_ID_PERSONNE_NULL));
        }
        // On récupère la personne dont on souhaite récupérer les caisses pour son département
        final Personne personne = personneDao.getPersonne(idPersonne);
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_LISTE_CAISSES_PERSONNE_NULL, new String[] {idPersonne.toString()}));
        }
        if (personne.getAdressePrincipale() == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_LISTE_CAISSES_PERSONNE_ADRESSE_PRINCIPALE_NULL, new String[] {idPersonne
                    .toString()}));
        }
        // On récupère l'identifiant externe du code postal - commune de l'adresse principale de la personne
        final Long eidCodePostalCommune = personne.getAdressePrincipale().getEidCodePostalCommune();
        // Récupération du code postal - commune à partir de Square
        final CodePostalCommuneDto codePostalCommune = dimensionService.rechercherCodePostalCommuneParId(eidCodePostalCommune);
        final Long eidCommune = codePostalCommune.getCommune().getIdentifiant();
        // On récupère la commune correspondant à cet identifiant dans Square
        final IdentifiantLibelleDepartementCodeDto idLibelleDepartement = dimensionService.rechercherDepartementParIdCommune(eidCommune);
        final DimensionCriteresRechercheCaisseDto criteres = new DimensionCriteresRechercheCaisseDto();
        criteres.setIdDepartement(idLibelleDepartement.getId());
        criteres.setIdRegime(idRegime);
        final DimensionCriteresRechercheDto dimensionCriteres = new DimensionCriteresRechercheDto();
        dimensionCriteres.setVisible(true);
        criteres.setDimensionCriteres(dimensionCriteres);
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECHERCHE_CAISSE_DEPARTEMENT,
          		 new String[] {String.valueOf(idLibelleDepartement.getId()), String.valueOf(idRegime)}));
        List<CaisseSimpleDto> listeCaisses = dimensionService.rechercherCaisseParCriteres(criteres);
        if (listeCaisses == null || listeCaisses.isEmpty()) {
            // Pas de caisse spécifique, on étend la recherche à toutes les caisses pour le régime passé en param
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECHERCHE_DEPARTEMENT_INEXISTANT,
             		 new String[] {String.valueOf(idLibelleDepartement.getId())}));
            criteres.setIdDepartement(null);
            listeCaisses = dimensionService.rechercherCaisseParCriteres(criteres);
        }
        return mapperCaisses(listeCaisses);
    }

    /**
     * Mappe la liste de caisses passée en paramètre en liste d'IdentifiantLibelleDto.
     * @param listeCaisses la liste de caisse à mapper.
     * @return liste de caisse mappée en liste d'IdentifiantLibelleDto.
     */
    private List<IdentifiantLibelleDto> mapperCaisses(List<CaisseSimpleDto> listeCaisses) {
        final List<IdentifiantLibelleDto> resultat = new ArrayList<IdentifiantLibelleDto>();
        for (CaisseSimpleDto caisse : listeCaisses) {
            resultat.add(new IdentifiantLibelleDto(caisse.getId(), caisse.getCode() + " - " + caisse.getNom()));
        }
        return resultat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleDto> getListeJoursPaiement() {
        return mapperDozerBean.mapList(jourPaiementDao.getListeJoursPaiement(), IdentifiantLibelleDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleDto> getListeMoyensPaiement() {
        return mapperDozerBean.mapList(moyenPaiementDao.getListeMoyensPaiement(), IdentifiantLibelleDto.class);
    }

    @Override
    public List<MotifDevisDto> getListeMotifsDevis() {
        return mapperDozerBean.mapList(motifDevisDao.getListeMotifsDevis(), MotifDevisDto.class);
    }

    @Override
    public List<MotifDevisDto> getMotifsDevisByCriteres(String libelle) {
        return mapperDozerBean.mapList(motifDevisDao.getMotifsDevisByCriteres(libelle), MotifDevisDto.class);
    }

    @Override
    public MotifDevisDto getMotifDevis(Long id) {
        return mapperDozerBean.map(motifDevisDao.getMotifDevisById(id), MotifDevisDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleDto> getListePeriodicitesPaiement() {
        return mapperDozerBean.mapList(periodicitePaiementDao.getListePeriodicitesPaiement(), IdentifiantLibelleDto.class);
    }

    @Override
    public List<ListeProduitsAdherentDto> getListeProduitsAdherent(Long idPersonne) {
    	final SimpleDateFormat sdf = new SimpleDateFormat(messageSourceUtil.get(MessageKeyUtil.DATE_FORMAT_DDMMYYYY_SEPARATOR));
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_LISTE_PRODUIT_ADHERENT,
        		 new String[] {String.valueOf(idPersonne)}));
        final List<ListeProduitsAdherentDto> listeProduitsContratsAdherentGwt = new ArrayList<ListeProduitsAdherentDto>();

        if (idPersonne != null) {
            // Si la personne est bénéficiaire, on récupère les produits de son adhérent principal
            final Long idAdherentPrincipal = adherentService.getIdAdherentPrincipal(idPersonne);
            final Long idPersonnePourListeProduits = idAdherentPrincipal == null ? idPersonne : idAdherentPrincipal;

            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_LISTE_PRODUIT_ADHERENT_PRINCIPAL,
           		 new String[] {String.valueOf(idPersonnePourListeProduits)}));

            // Récupération de la liste des produits des dofférents contrats de l'adhérent à partir du DWH
            final CriteresInfosProduitsDto criteres = new CriteresInfosProduitsDto();
            criteres.setIdPersonne(idPersonnePourListeProduits);
            final List<InfosProduitDto> listeProduitsAdherent = contratService.getInfosProduits(criteres);
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_NOMBRE_CONTRAT_TROUVER,
              		 new String[] {String.valueOf(listeProduitsAdherent.size())}));

            // On parcours l'ensemble des produits pour les contrats de l'adhérent
            final ListeProduitsAdherentDto listeProduitsContrat = new ListeProduitsAdherentDto();
            boolean isProduitAjoute = false;
            for (InfosProduitDto produitAdherentDto : listeProduitsAdherent) {
                if (!produitAdherentDto.getIsFromContratCollectif()) {
                    logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MESSAGE_CONTRAT_INDIVIDUEL));
                    // Récupération du libellé du produit
                    final ProduitCriteresDto produitCriteresDto = new ProduitCriteresDto();
                    produitCriteresDto.setProduitAia(produitAdherentDto.getProduitAia());
                    produitCriteresDto.setGarantieAia(produitAdherentDto.getGarantieAia());
                    logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_PRODUIT_GARANTIE_AIA,
                     		 new String[] {produitAdherentDto.getProduitAia(), produitAdherentDto.getGarantieAia()}));
                    final List<ProduitDto> listeProduits = produitService.getListeProduits(produitCriteresDto);
                    // On ne doit récupérer qu'un élément dans la liste
                    if (listeProduits.size() != 1) {
                        logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_RECUP_PRODUIT_ADHERENT,
                        		 new String[] {produitAdherentDto.getProduitAia(), produitAdherentDto.getGarantieAia(),
                        					   String.valueOf(listeProduits.size())}));
                        throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_LISTE_PRODUITS_ADHERENT));
                    }
                    final ProduitDto produitDto = listeProduits.get(0);
                    logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_PRODUIT_RECUPERER,
                    		 new String[] {String.valueOf(produitDto.getIdentifiant())}));
                    // Création du InfosProduitAdherentGwt
                    final InfosProduitAdherentDto infosProduitAdherentDto = new InfosProduitAdherentDto();
                    infosProduitAdherentDto.setIdentifiant(Long.valueOf(produitDto.getIdentifiant()));
                    infosProduitAdherentDto.setLibelle(produitDto.getLibelleCommercial());
                    infosProduitAdherentDto.setProduitAia(produitDto.getProduitAia());
                    infosProduitAdherentDto.setGarantieAia(produitDto.getGarantieAia());
                    infosProduitAdherentDto.setDateAdhesion(sdf.format(produitAdherentDto.getDateEffet().getTime()));
                    final Calendar dateResiliation = produitAdherentDto.getDateResiliation();
                    if (dateResiliation != null) {
                        infosProduitAdherentDto.setDateResiliation(sdf.format(produitAdherentDto.getDateResiliation().getTime()));
                    }

                    // Si le produit est un produit santé, alors il s'agit du produit principal
                    if (produitAdherentDto.getIdFamilleGarantie().equals(adherentMappingService.getIdFamilleGarantieSante())) {
                        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_AJOUT_PRODUIT_PRINCIPAL,
                       		 new String[] {infosProduitAdherentDto.getLibelle()}));
                        listeProduitsContrat.setProduitPrincipal(infosProduitAdherentDto);
                    }
                    // Sinon c'est un produit lié
                    else {
                        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_AJOUT_PRODUIT_LIER,
                          		 new String[] {infosProduitAdherentDto.getLibelle()}));
                        listeProduitsContrat.getListeProduitsLies().add(infosProduitAdherentDto);
                    }
                    isProduitAjoute = true;
                }
                else {
                    logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CONTRAT_COLL_NPE_CHARGE));
                }
            }
            if (isProduitAjoute) {
                // On ajoute la liste des produits pour le contrat parcouru
                listeProduitsContratsAdherentGwt.add(listeProduitsContrat);
            }
        }
        else {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_INFORMATION_ADHERENT_INEXISTANTE,
             		 new String[] {String.valueOf(idPersonne)}));
        }
        return listeProduitsContratsAdherentGwt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleDto> getListeRegimes() {
        // On récupère tous les régimes visibles à partir de Square.
        final DimensionCriteresRechercheRegimeDto criteres = new DimensionCriteresRechercheRegimeDto();
        final DimensionCriteresRechercheDto dimensionCriteres = new DimensionCriteresRechercheDto();
        dimensionCriteres.setVisible(true);
        dimensionCriteres.setMaxResults(Integer.MAX_VALUE);
        criteres.setDimensionCriteres(dimensionCriteres);
        criteres.setVisibleApplicatif(true);
        return dimensionService.rechercherRegimeParCriteres(criteres);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpportuniteDto getOrCreateOpportunite(InfosOpportuniteDto infosOpportunite) {
        final long debut = System.currentTimeMillis();

        // Vérification des paramètres
        if (infosOpportunite == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_CREATE_OPPORTUNITE_INFOS_OPPORTUNITE_NULL));
        }
        final Long eidOpportunite = infosOpportunite.getEidOpportunite();
        if (eidOpportunite == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_CREATE_OPPORTUNITE_EID_OPPORTUNITE_NULL));
        }

        // On recherche une opportunité ayant l'eid d'opportunité renseigné
        Opportunite opportunite = opportuniteDao.getOpportuniteByEid(eidOpportunite);

        final PersonneTarificateurDto personneCiblee = infosOpportunite.getPersonne();
        if (personneCiblee == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_CREATE_OPPORTUNITE_PERSONNE_NULL));
        }

        // Création du cache des infos des personnes Square
        final InfosPersonneSquareBean infosPersonneSquare = new InfosPersonneSquareBean();

        // Si l'opportunité n'existe pas
        if (opportunite == null) {
            // Vérification paramètres spécifiques à la création
            final Long eidCreateur = infosOpportunite.getEidCreateur();
            if (eidCreateur == null) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_GET_CREATE_OPPORTUNITE_EID_CREATEUR_NULL));
            }

            final Calendar now = Calendar.getInstance();
            final Finalite finaliteParDefaut = finaliteDao.getFinaliteById(tarificateurSquareMappingService.getIdFinaliteNonRenseignee());
            // On créé une opportunité à partir des informations passées en paramètre
            opportunite = mapperDozerBean.map(infosOpportunite, Opportunite.class);
            // On initialise l'opportunité
            opportunite.setDateCreation(now);
            opportunite.setFinalite(finaliteParDefaut);
            opportunite.setAdhesion(new Adhesion());
            // On indique la nature de la personne à la création de l'opportunité
            opportunite.setEidNaturePersonne(personneCiblee.getEidNature());
            opportuniteDao.saveOpportunite(opportunite);

            // On créé un nouveau devis lié à l'opportunité si demandé
            if (Boolean.TRUE.equals(infosOpportunite.getCreerPremierDevis())) {
                final Devis devis = createDevis(opportunite, personneCiblee, infosOpportunite.getOrigineSiteWeb(), infosOpportunite.getEidRelationParrain());
                if (infosOpportunite.getOrigineSiteWeb()) {
                    Long idMotifDevis = null;
                    if (infosOpportunite.getEidRelationParrain() != null) {
                        idMotifDevis = tarificateurSquareMappingService.getIdMotifDevisParrainage();
                    }
                    else {
                        idMotifDevis = tarificateurSquareMappingService.getIdMotifDevisStandard();
                    }
                    final MotifDevis motifDevis = motifDevisDao.getMotifDevisById(idMotifDevis);
                    if (motifDevis == null) {
                        logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_MOTIF_DEVIS_INEXISTANT,
                        		 new String[] {String.valueOf(idMotifDevis)}));
                        throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MOTIF_DEVIS_INEXISTANT));
                    }
                    devis.setMotif(motifDevis);
                    devisDao.saveDevis(devis);
                }
            }
        }

        final OpportuniteDto opportuniteDto = mapperDozerBean.map(opportunite, OpportuniteDto.class);

        // Récupération des infos de l'opportunité Square
        final InfosOpportuniteSquareBean infosOpportuniteSquare = new InfosOpportuniteSquareBean();
        // On complète les infos non figées de l'opportunité
        completerOpportuniteDtoAvecOpportuniteSquare(opportuniteDto, infosOpportuniteSquare);

        // Comparaison de la famille passée en paramètre et la famille de chaque devis
        final List<Devis> listeDevisOpportunite = devisDao.getListeDevisByOpportunite(opportunite.getId());
        final List<DevisDto> listeDevisDto = new ArrayList<DevisDto>();

        // Création du comparateur de familles
        final ComparaisonFamilleUtil comparaisonFamilleUtil = new ComparaisonFamilleUtil(tarificateurSquareMappingService, messageSourceUtil, customConverter);
        for (Devis devisOpportunite : listeDevisOpportunite) {

            final boolean famillesIdentiques = comparaisonFamilleUtil.isFamillesIdentiques(personneCiblee, devisOpportunite.getPersonnePrincipale());
            // Mise à jour du flag de la famille modifiée
            devisOpportunite.setFamilleModifiee(!famillesIdentiques);

            // Mapping du devis complet
            final DevisDto devisDto = mapperDozerBean.map(devisOpportunite, DevisDto.class);
            devisDto.setPersonnePrincipale(personneUtil.mapperPersonneEnPersonneDto(devisOpportunite.getPersonnePrincipale(), infosPersonneSquare));
            // On complète les infos non figées du devis
            devisDto.setEidOpportunite(eidOpportunite);
            completerDevisDtoAvecOpportuniteSquare(devisDto, opportunite.getEidOpportunite(), infosOpportuniteSquare);
            // recupere le type du devis
            devisDto.setType(devisUtil.getTypeDevis(devisDto.getId()));
            // on mappe les lignes
            final List<LigneDevis> listeLignesPrincipales = ligneDevisDao.getLigneDevisPrincipalParIdDevis(devisDto.getId());
            for (LigneDevis ligneDevisPrincipale : listeLignesPrincipales) {
                final LigneDevisDto ligneDevisPrincipaleDto =
                    devisUtil.mapperLigneDevisPrincipale(ligneDevisPrincipale, infosPersonneSquare, infosOpportuniteSquare);
                devisDto.getListeLigneDevis().add(ligneDevisPrincipaleDto);
            }
            // On tri les lignes de devis
            Collections.sort(devisDto.getListeLigneDevis());
            listeDevisDto.add(devisDto);
        }
        opportuniteDto.setListeDevis(listeDevisDto);

        // On récupère le libellé de la nature de la personne ciblée par l'opportunité
        if (opportunite.getEidNaturePersonne() != null) {
            final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
            criteres.setId(opportunite.getEidNaturePersonne());
            final List<IdentifiantLibelleDto> listeNatures = dimensionService.rechercherNaturePersonnePhysiqueParCriteres(criteres);
            if (listeNatures != null && listeNatures.size() == 1) {
                opportuniteDto.setNaturePersonnePrincipale(listeNatures.get(0).getLibelle());
            }
            else {
                logger.error(messageSourceUtil.get(MessageKeyUtil.ERREUR_RECUPERATION_NATURE_PERSONNE_SQUARE, new String[] {opportunite.getEidNaturePersonne()
                        .toString()}));
            }
        }
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_PERF_ETUDE_GET_CREATE_OPPORTUNITE,
       		 new String[] {String.valueOf(System.currentTimeMillis() - debut)}));
        return opportuniteDto;
    }

    @Override
    public List<DevisDto> getListeDevisByCriteres(CriteresRechercheDevisDto criteres) {

        long debut = System.currentTimeMillis();
        long fin = System.currentTimeMillis();

        final List<Devis> deviss = devisDao.getListeDevisByCriteres(criteres);
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECHERCHE_DEVIS_PAR_CRITERE_NB_RESULTAT,
          		 new String[] {String.valueOf(deviss.size())}));
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECHERCHE_DEVIS_PAR_CRITERE_IDDEVIS,
         		 new String[] {String.valueOf(criteres.getIdDevis())}));
        fin = System.currentTimeMillis();
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_PERF_ETUDE_RECUP_DEVIS_DANS_DAO,
          		 new String[] {String.valueOf(fin - debut)}));
        debut = System.currentTimeMillis();

        // Création du cache des infos de personnes et opportunité
        final InfosPersonneSquareBean infosPersonnesSquare = new InfosPersonneSquareBean();
        final InfosOpportuniteSquareBean infosOpportuniteSquare = new InfosOpportuniteSquareBean();

        final List<DevisDto> listeDevisDto = new ArrayList<DevisDto>();
        for (Devis devisOpportunite : deviss) {
            // Mapping du devis complet
            final DevisDto devisDto = mapperDozerBean.map(devisOpportunite, DevisDto.class);
            devisDto.setPersonnePrincipale(personneUtil.mapperPersonneEnPersonneDto(devisOpportunite.getPersonnePrincipale(), infosPersonnesSquare));
            // recupere le type du devis
            devisDto.setType(devisUtil.getTypeDevis(devisDto.getId()));
            // On complète les infos non figées
            devisDto.setEidOpportunite(devisOpportunite.getOpportunite().getEidOpportunite());
            boolean aAjouter = true;
            try {
                completerDevisDtoAvecOpportuniteSquare(devisDto, devisOpportunite.getOpportunite().getEidOpportunite(), infosOpportuniteSquare);
            }
            catch (BusinessException e) {
                if (StringUtils.equals(e.getMessage(), messageSourceUtil.get(MessageKeyUtil.ERREUR_RECUPERATION_OPPORTUNITE_SQUARE))
                    && criteres.getIdDevis() == null) {
                    aAjouter = false;
                }
                else {
                    throw e;
                }
            }

            if (aAjouter) {
                if (criteres.isRecupererContenuDevis()) {
                    fin = System.currentTimeMillis();
                    logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_PERF_ETUDE_RECUP_TYPE_DEVIS,
                     		 new String[] {String.valueOf(fin - debut)}));
                    debut = System.currentTimeMillis();

                    // on mappe les lignes
                    final List<LigneDevis> listeLignesPrincipales = ligneDevisDao.getLigneDevisPrincipalParIdDevis(devisDto.getId());

                    fin = System.currentTimeMillis();
                    logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_PERF_ETUDE_RECUP_LIGNE_DEVIS_PRINCIPAL,
                    		 new String[] {String.valueOf(fin - debut)}));
                    debut = System.currentTimeMillis();

                    for (LigneDevis ligneDevis : listeLignesPrincipales) {
                        final LigneDevisDto ligneDevisPrincipaleDto =
                            devisUtil.mapperLigneDevisPrincipale(ligneDevis, infosPersonnesSquare, infosOpportuniteSquare);
                        fin = System.currentTimeMillis();
                        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_PERF_ETUDE_MAPPING_LIGNE_DEVIS,
                       		 new String[] {String.valueOf(fin - debut)}));
                        debut = System.currentTimeMillis();
                        devisDto.getListeLigneDevis().add(ligneDevisPrincipaleDto);
                    }
                    // On tri les lignes de devis
                    Collections.sort(devisDto.getListeLigneDevis());
                }
                listeDevisDto.add(devisDto);
            }
        }
        return listeDevisDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevisDto createDevis(InfosOpportuniteDto infosOpportunite) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_DEMANDE_CREATION_DEVIS_POUR_OPPORTUNITE,
          		 new String[] {String.valueOf(infosOpportunite.getEidOpportunite())}));
        // Vérification des paramètres
        if (infosOpportunite == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CREATE_DEVIS_INFOS_OPPORTUNITE_NULL));
        }
        final Long eidOpportunite = infosOpportunite.getEidOpportunite();
        if (eidOpportunite == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CREATE_DEVIS_EID_OPPORTUNITE_NULL));
        }
        // On recherche une opportunité ayant l'eid d'opportunité renseigné
        final Opportunite opportunite = opportuniteDao.getOpportuniteByEid(eidOpportunite);
        // Si l'opportunité n'existe pas
        if (opportunite == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CREATE_DEVIS_OPPORTUNITE_NULL, new String[] {eidOpportunite.toString()}));
        }
        // Vérification que la personne est bien renseignée
        final PersonneTarificateurDto personneCiblee = infosOpportunite.getPersonne();
        if (personneCiblee == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CREATE_DEVIS_PERSONNE_NULL));
        }
        final Devis devis = createDevis(opportunite, personneCiblee, infosOpportunite.getOrigineSiteWeb(), infosOpportunite.getEidRelationParrain());
        // Mapping
        final DevisDto devisDto = mapperDozerBean.map(devis, DevisDto.class);
        devisDto.setPersonnePrincipale(personneUtil.mapperPersonneEnPersonneDto(devis.getPersonnePrincipale(), new InfosPersonneSquareBean()));
        // On complète les infos non figées
        completerDevisDtoAvecOpportuniteSquare(devisDto, devis.getOpportunite().getEidOpportunite(), new InfosOpportuniteSquareBean());
        return devisDto;
    }

    /**
     * Créé un devis pour la personne passée en paramètre rattaché à l'opportunité spécifiée.
     * @param opportunite l'opportunité auquel le devis créé sera rattaché
     * @param personne la personne ciblée par le devis.
     * @return le devis créé.
     */
    private Devis createDevis(Opportunite opportunite, PersonneTarificateurDto personneCiblee, Boolean origineSiteWeb, Long eidRelationParrain) {

        // Vérification que la famille est éligible
        if (!opportuniteServiceSquare.isFamilleEligiblePourOpportunite(personneCiblee.getEidPersonne())) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_FAMILLE_NON_ELIGIBLE,
             		 new String[] {String.valueOf(personneCiblee.getId())}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_CREATION_DEVIS_FAMILLE_NON_ELIGIBLE));
        }

        // Remplissage des adresses vides
        remplirAdressesVidesFamilleDevis(personneCiblee);

        // Vérification des champs obligatoires de la personne
        verifierChampsPersonne(personneCiblee);

        final Personne personne = mapperDozerBean.map(personneCiblee, Personne.class);

        // On initialise le devis
        final Calendar now = Calendar.getInstance();
        final Finalite finaliteParDefaut = finaliteDao.getFinaliteById(tarificateurSquareMappingService.getIdFinaliteNonRenseignee());
        final Devis devis = new Devis();
        devis.setDateCreation(now);
        devis.setEidCreateur(opportunite.getEidCreateur());
        devis.setOpportunite(opportunite);
        devis.setFinalite(finaliteParDefaut);
        devis.setPersonnePrincipale(personne);
        devis.setOrigineSiteWeb(origineSiteWeb);
        devis.setEidRelationParrain(eidRelationParrain);
        devis.setListeAssuresSociaux(new HashSet<Personne>());
        // On sauvegarde le devis pour avoir un id
        devisDao.saveDevis(devis);

        final Map<Long, Personne> mapPersonnesDevisParEid = new HashMap<Long, Personne>();
        mapPersonnesDevisParEid.put(personne.getEidPersonne(), personne);

        // on mappe les infos de santé à la main
        if (personneCiblee.getInfoSante() != null) {
            personne.setInfoSante(new InfoSante());
            personne.getInfoSante().setNumSecuriteSocial(personneCiblee.getInfoSante().getNumSecuriteSocial());
            personne.getInfoSante().setCleSecuriteSocial(personneCiblee.getInfoSante().getCleSecuriteSocial());
            personne.getInfoSante().setEidCaisse(personneCiblee.getInfoSante().getEidCaisse());
            // la personne principale est par défaut son propre referent, on redirigera vers un assuré social plus bas si nécessaire
            personne.getInfoSante().setReferent(personne);
        }

        // Mapping manuel des bénéficiaires
        for (BeneficiaireTarificateurDto beneficiaireParcouru : personneCiblee.getListeBeneficiaires()) {
            if (!BooleanUtils.isFalse(beneficiaireParcouru.getActif())) {
                final Beneficiaire beneficiaire = new Beneficiaire();
                beneficiaire.setPersonneSource(personne);
                beneficiaire.setPersonneCible((Personne) mapperDozerBean.map(beneficiaireParcouru, Personne.class));
                // on mappe les infos de santé à la main si le benef est son propre referent
                if (beneficiaireParcouru.getInfoSante() != null && beneficiaireParcouru.getInfoSante().getEidReferent() != null
                    && beneficiaireParcouru.getInfoSante().getEidReferent().equals(beneficiaireParcouru.getEidPersonne())) {
                    beneficiaire.getPersonneCible().setInfoSante(new InfoSante());
                    beneficiaire.getPersonneCible().getInfoSante().setNumSecuriteSocial(beneficiaireParcouru.getInfoSante().getNumSecuriteSocial());
                    beneficiaire.getPersonneCible().getInfoSante().setCleSecuriteSocial(beneficiaireParcouru.getInfoSante().getCleSecuriteSocial());
                    beneficiaire.getPersonneCible().getInfoSante().setEidCaisse(beneficiaireParcouru.getInfoSante().getEidCaisse());
                    // le bénéficiaire est par défaut son propre referent, on redirigera vers un assuré social plus bas si nécessaire
                    beneficiaire.getPersonneCible().getInfoSante().setReferent(beneficiaire.getPersonneCible());
                }
                final LienFamilial lienFamilial = lienFamilialDao.getLienFamilial(beneficiaireParcouru.getIdLienFamilial());
                if (lienFamilial == null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CREATE_DEVIS_LIEN_FAMILIAL_NULL, new String[] {beneficiaireParcouru
                            .getIdLienFamilial().toString()}));
                }
                beneficiaire.setLienFamilial(lienFamilial);
                personne.getListeBeneficiaires().add(beneficiaire);
                mapPersonnesDevisParEid.put(beneficiaire.getPersonneCible().getEidPersonne(), beneficiaire.getPersonneCible());
            }
        }

        // On ajoute les assurés sociaux des bénéficiaires au devis s'ils en ont un
        for (BeneficiaireTarificateurDto beneficiaireParcouru : personneCiblee.getListeBeneficiaires()) {
            if (!BooleanUtils.isFalse(beneficiaireParcouru.getActif())) {
                if (beneficiaireParcouru.getInfoSante() != null && beneficiaireParcouru.getInfoSante().getEidReferent() != null
                    && !beneficiaireParcouru.getInfoSante().getEidReferent().equals(beneficiaireParcouru.getEidPersonne())) {
                    Personne assureSocial = mapPersonnesDevisParEid.get(beneficiaireParcouru.getInfoSante().getEidReferent());
                    if (assureSocial == null) {
                        final com.square.core.model.dto.PersonneDto personneDto =
                            personnePhysiqueService.rechercherPersonneParIdentifiant(beneficiaireParcouru.getInfoSante().getEidReferent());
                        PersonneDto assureSocialDto = mapperDozerBean.map(personneDto, PersonneDto.class);
                        assureSocialDto.getInfoSante().setNumSecuriteSocial(personneDto.getInfoSante().getNro().substring(0, TREIZE));
                        assureSocialDto.getInfoSante().setCleSecuriteSocial(personneDto.getInfoSante().getNro().substring(TREIZE, QUINZE));
                        assureSocialDto = tarificateurPersonneService.createAssureSocial(assureSocialDto, devis.getId(), true);
                        assureSocial = personneDao.getPersonne(assureSocialDto.getId());
                        mapPersonnesDevisParEid.put(assureSocial.getEidPersonne(), assureSocial);
                    }
                    final Personne beneficiaire = mapPersonnesDevisParEid.get(beneficiaireParcouru.getEidPersonne());
                    beneficiaire.setInfoSante(assureSocial.getInfoSante());
                }
            }
        }

        // On ajoute l'assuré social de la personne principale au devis s'il en a un
        if (personneCiblee.getInfoSante() != null && personneCiblee.getInfoSante().getEidReferent() != null
            && !personneCiblee.getInfoSante().getEidReferent().equals(personneCiblee.getEidPersonne())) {
            Personne assureSocial = mapPersonnesDevisParEid.get(personneCiblee.getInfoSante().getEidReferent());
            if (assureSocial == null) {
                // On vérifie que les 2 personnes ont une relation active
                final RelationCriteresRechercheDto criteresRelation = new RelationCriteresRechercheDto();
                criteresRelation.setIdPersonneSource(personneCiblee.getEidPersonne());
                criteresRelation.setIdPersonneCible(personneCiblee.getInfoSante().getEidReferent());
                final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
                    new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresRelation, 0, Integer.MAX_VALUE);
                final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> relations =
                    personneService.rechercherRelationsParCritreres(criterias);
                if (!relations.getListResults().isEmpty()) {
                    final com.square.core.model.dto.PersonneDto personneDto =
                        personnePhysiqueService.rechercherPersonneParIdentifiant(personneCiblee.getInfoSante().getEidReferent());
                    PersonneDto assureSocialDto = mapperDozerBean.map(personneDto, PersonneDto.class);
                    assureSocialDto.getInfoSante().setNumSecuriteSocial(personneDto.getInfoSante().getNro().substring(0, TREIZE));
                    assureSocialDto.getInfoSante().setCleSecuriteSocial(personneDto.getInfoSante().getNro().substring(TREIZE, QUINZE));
                    assureSocialDto = tarificateurPersonneService.createAssureSocial(assureSocialDto, devis.getId(), true);
                    assureSocial = personneDao.getPersonne(assureSocialDto.getId());
                    mapPersonnesDevisParEid.put(assureSocial.getEidPersonne(), assureSocial);
                }
            }
            if (assureSocial != null) {
                personne.setInfoSante(assureSocial.getInfoSante());
            }
        }

        // on sauvegarde le devis a nouveau pour persister en cascade les elements references
        devisDao.saveDevis(devis);
        return devis;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelecteurProduitDto getSelecteurParProduit(Integer idProduit, Long idDevis, final PersonneTarificateurDto nouvellePersonnePrincipale) {
        // Création du cache des infos de personnes
        final InfosPersonneSquareBean infosPersonnesSquare = new InfosPersonneSquareBean();
        // Mapping de la personneTarificateurDto en PersonneDto
        final PersonneDto personneDto = personneUtil.mapperPersonneTarificateurEnPersonneDto(nouvellePersonnePrincipale, infosPersonnesSquare);
        return getSelecteurParProduit(idProduit, idDevis, personneDto, false, infosPersonnesSquare);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelecteurProduitDto getSelecteurProduitParLigneDevis(Long idLigneDevis, Long idDevis) {
        final BuilderSelecteurProduit builderSelecteurProduit =
            new BuilderSelecteurProduit(mapperDozerBean, produitService, tarificateurMappingService, tarificateurSquareMappingService, contratService,
                dimensionService, personneDao, messageSourceUtil, adherentMappingService, personnePhysiqueService);

        // on recupere la ligne de devis
        final LigneDevisDto ligneDevis = devisUtil.getLigneDevisParIdentifiant(idLigneDevis);
        // on recupere un selecteur produit
        final boolean isSelecteurProduitParProduitsAdherent = false;

        final SelecteurProduitDto selecteurProduitDto =
            getSelecteurParProduit(ligneDevis.getIdentifiantProduit(), idDevis, null, isSelecteurProduitParProduitsAdherent, new InfosPersonneSquareBean());

        // on remplit les valeurs du selecteur avec les données de la ligne devis
        return builderSelecteurProduit.getSelecteurProduitParLigneDevis(selecteurProduitDto, ligneDevis, isSelecteurProduitParProduitsAdherent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SelecteurProduitDto getSelecteurProduitParProduitsAdherent(Long idDevis, String produitAia, String garantieAia,
        final PersonneTarificateurDto nouvellePersonnePrincipale) {
        final BuilderSelecteurProduit builderSelecteurProduit =
            new BuilderSelecteurProduit(mapperDozerBean, produitService, tarificateurMappingService, tarificateurSquareMappingService, contratService,
                dimensionService, personneDao, messageSourceUtil, adherentMappingService, personnePhysiqueService);

        // Création du cache des infos de personnes
        final InfosPersonneSquareBean infosPersonnesSquare = new InfosPersonneSquareBean();

        // Mapping de la personneTarificateurDto en PersonneDto
        final PersonneDto personneDto = personneUtil.mapperPersonneTarificateurEnPersonneDto(nouvellePersonnePrincipale, infosPersonnesSquare);

        final PropositionLigneDevis propositionAdherent =
            devisUtil.genererPropositionDevisPourAdherent(idDevis, produitAia, garantieAia, personneDto, infosPersonnesSquare);

        // on recupere l'id du produit principal
        final Integer idProduitPrincipal = propositionAdherent.getLigneDevis().getIdentifiantProduit();
        // on recupere un selecteur produit
        final boolean isSelecteurProduitParProduitsAdherent = true;
        SelecteurProduitDto selecteurProduitDto =
            getSelecteurParProduit(idProduitPrincipal, idDevis, personneDto, isSelecteurProduitParProduitsAdherent, infosPersonnesSquare);

        // on remplit les valeurs du selecteur avec les données des infos de l'adherent
        selecteurProduitDto = builderSelecteurProduit.getSelecteurProduitParLigneDevis(selecteurProduitDto, propositionAdherent.getLigneDevis(),
        		isSelecteurProduitParProduitsAdherent);

        return selecteurProduitDto;
    }

    /**
     * Recupere un selecteur produit pour un produit.
     */
    private SelecteurProduitDto getSelecteurParProduit(Integer idProduit, Long idDevis, final PersonneDto nouvellePersonnePrincipale,
        boolean isSelecteurProduitParProduitsAdherent, InfosPersonneSquareBean infosPersonnes) {
        final BuilderSelecteurProduit builderSelecteurProduit =
            new BuilderSelecteurProduit(mapperDozerBean, produitService, tarificateurMappingService, tarificateurSquareMappingService, contratService,
                dimensionService, personneDao, messageSourceUtil, adherentMappingService, personnePhysiqueService);

        PersonneDto personnePourSelecteur = null;

        // si l'idDevis est null (nouveau devis), on prend la nouvellePersonnePrincipale
        if (idDevis == null) {
            personnePourSelecteur = nouvellePersonnePrincipale;
        }
        else {
            // sinon on recupere la personne du devis
            final Devis devis = devisDao.getDevis(idDevis);
            if (devis == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_DEVIS_INEXISTANT,
                		 new String[] {String.valueOf(idDevis)}));
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEVIS_INEXISTANT));
            }
            personnePourSelecteur = personneUtil.mapperPersonneEnPersonneDto(devis.getPersonnePrincipale(), infosPersonnes);
        }
        return builderSelecteurProduit.getSelecteurParProduit(idProduit, idDevis, personnePourSelecteur, isSelecteurProduitParProduitsAdherent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LigneDevisDto updateLigneDevisParSelecteurProduit(SelecteurProduitDto selecteurProduit) {
        final BuilderSelecteurProduit builderSelecteurProduit =
            new BuilderSelecteurProduit(mapperDozerBean, produitService, tarificateurMappingService, tarificateurSquareMappingService, contratService,
                dimensionService, personneDao, messageSourceUtil, adherentMappingService, personnePhysiqueService);
        // on recupere la proposition à partir du selecteur
        final PropositionLigneDevis proposition = builderSelecteurProduit.getPropositionParSelecteurProduit(selecteurProduit);
        // on ajoute la ligne
        final LigneDevisDto ligneDevis = devisUtil.recalculerLigneDevis(proposition);
        // on retourne la ligne de devis
        return ligneDevis;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevisDto transfererDevis(TransfertDevisDto transfertDevisDto) {
        // Récupération de l'opportunité d'origine & création de la nouvelle
        final LigneDevis ld = ligneDevisDao.getLigneDevis(transfertDevisDto.getListeIdsLignesDevisATransferer().get(0));

        // Recherche actions (source & dernière relance)
        final ActionCritereRechercheDto criteres = new ActionCritereRechercheDto();
        criteres.setIdOpportunite(ld.getDevis().getOpportunite().getEidOpportunite());
        final RemotePagingResultsDto<ActionRechercheDto> listeActions =
            actionService.rechercherActionParCritere(new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteres, 0, Integer.MAX_VALUE));
        if (listeActions.getTotalResults() > 0) {
            // Récupération de l'action source & création d'une nouvelle opp
            final ActionDto actionSource = actionService.rechercherActionParIdentifiant(listeActions.getListResults().get(0).getId());
            final com.square.core.model.dto.OpportuniteDto nouvelleOpportunite = new com.square.core.model.dto.OpportuniteDto();
            nouvelleOpportunite.setIdAgence(actionSource.getAgence().getIdentifiant());
            nouvelleOpportunite.setIdCampagne(actionSource.getCampagne() != null ? actionSource.getCampagne().getIdentifiant() : null);
            nouvelleOpportunite.setIdCreateur(transfertDevisDto.getEidRessource());
            nouvelleOpportunite.setIdType(actionSource.getType().getIdentifiant());
            nouvelleOpportunite.setIdNature(actionSource.getNatureAction().getIdentifiant());
            nouvelleOpportunite.setIdObjet(actionSource.getObjet().getIdentifiant());
            nouvelleOpportunite.setIdPersonnePhysique(actionSource.getIdPersonne());
            nouvelleOpportunite.setCreationForcee(Boolean.TRUE);
            nouvelleOpportunite.setIdRessource(actionSource.getRessource().getIdentifiant());
            nouvelleOpportunite.setIdSousObjet(actionSource.getSousObjet() != null ? actionSource.getSousObjet().getIdentifiant() : null);
            final com.square.core.model.dto.OpportuniteDto opportuniteCree = opportuniteServiceSquare.creerOpportunite(nouvelleOpportunite);

            // On modifie l'action de relance pour être a J+7
            final ActionCritereRechercheDto criteresNouvelleOpp = new ActionCritereRechercheDto();
            criteresNouvelleOpp.setIdOpportunite(opportuniteCree.getIdOpportunite());
            final RemotePagingResultsDto<ActionRechercheDto> listeActionsNouvelleOpp =
                actionService.rechercherActionParCritere(new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteresNouvelleOpp, 0, Integer.MAX_VALUE));
            if (listeActionsNouvelleOpp.getTotalResults() > 1) {
                final ActionDto actionRelance =
                    actionService.rechercherActionParIdentifiant(listeActionsNouvelleOpp.getListResults().get(listeActionsNouvelleOpp.getTotalResults() - 1)
                            .getId());
                actionRelance.getDateAction().add(Calendar.DAY_OF_MONTH, 7);
                // Copie des notes + quelques infos de l'ancienne relance :
                final ActionDto actionRelanceOrigine =
                    actionService.rechercherActionParIdentifiant(listeActions.getListResults().get(listeActions.getTotalResults() - 1).getId());
                actionRelance.setCommentaires(actionRelanceOrigine.getCommentaires());
                actionRelance.setNatureAction(actionRelanceOrigine.getNatureAction());
                actionRelance.setPriorite(actionRelanceOrigine.getPriorite());
                actionRelance.setCampagne(actionRelanceOrigine.getCampagne());
                actionService.modifierAction(actionRelance);
            }

            // Création des infos du tarificateur
            final LigneDevis ldClone = mapperDozerBean.map(ld, LigneDevis.class);

            // Copie de l'opp
            ldClone.getDevis().getOpportunite().setDateCreation(Calendar.getInstance());
            ldClone.getDevis().getOpportunite().setEidCreateur(transfertDevisDto.getEidRessource());
            ldClone.getDevis().getOpportunite().setEidOpportunite(opportuniteCree.getIdOpportunite());
            opportuniteDao.saveOpportunite(ldClone.getDevis().getOpportunite());
            ldClone.getDevis().setDateCreation(Calendar.getInstance());

            devisDao.saveDevis(ldClone.getDevis());

            // Sauvegarde des lignes de devis liées :
            for (LigneDevisLiee ldl : ldClone.getListeLignesDevisLiees()) {
                ligneDevisDao.saveLigneDevis(ldl.getLigneDevisLiee());
            }
            ligneDevisDao.saveLigneDevis(ldClone);
            final DevisDto devisDto = mapperDozerBean.map(ldClone.getDevis(), DevisDto.class);
            // On complète les infos non figées
            completerDevisDtoAvecOpportuniteSquare(devisDto, opportuniteCree.getIdOpportunite(), new InfosOpportuniteSquareBean());
            return devisDto;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleDto> getListeModelesDevisByCriteres(CritereModeleDevisDto criteres) {
        // Récupération des modèles de devis présents dans l'éditique
        final List<ModeleDevisDto> listeModelesDevis = editiqueMappingService.getListeModelesDevis();

        // Filtrage des modèles de devis
        final List<IdentifiantLibelleDto> listeModeles = new ArrayList<IdentifiantLibelleDto>();
        if (criteres != null && criteres.getListeIdsModeles() != null && criteres.getListeIdsModeles().size() > 0) {
            for (Long idModele : criteres.getListeIdsModeles()) {
                for (ModeleDevisDto modeleEditique : listeModelesDevis) {
                    if (idModele.equals(modeleEditique.getIdentifiant())) {
                            listeModeles.add((IdentifiantLibelleDto) mapperDozerBean.map(modeleEditique, IdentifiantLibelleDto.class));
                            break;
                    }
                }
            }
        }
        else {
            if (listeModelesDevis != null && listeModelesDevis.size() > 0) {
                for (ModeleDevisDto modeleEditique : listeModelesDevis) {
                    listeModeles.add((IdentifiantLibelleDto) mapperDozerBean.map(modeleEditique, IdentifiantLibelleDto.class));
                }
            }
        }
        return listeModeles;
    }

    @Override
    public String getValeurCritereCodeCompoFamille(Long idLigneDevis) {
        final LigneDevis ligneDevis = ligneDevisDao.getLigneDevis(idLigneDevis);
        final List<Beneficiaire> beneficiaires = new ArrayList<Beneficiaire>();
        for (LigneDevisLiee ligneLiees : ligneDevis.getListeLignesDevisLiees()) {
            final LigneDevis ligneDevisLiee = ligneLiees.getLigneDevisLiee();
            if (ligneDevisLiee.getBeneficiaire() != null) {
                // On regarde que le bénéficiaire n'a pas déjà été ajouté
                boolean beneficiaireAjoute = false;
                for (Beneficiaire beneficiaire : beneficiaires) {
                    if (beneficiaire.getPersonneCible().getId().equals(ligneDevisLiee.getBeneficiaire().getId())) {
                        beneficiaireAjoute = true;
                        break;
                    }
                }
                if (!beneficiaireAjoute) {
                    beneficiaires.add(beneficiaireDao.getBeneficiaireByCible(ligneDevisLiee.getBeneficiaire().getId()));
                }
            }
        }

        String codeGs = "I";
        int nbsEnfant = 0;
        for (Beneficiaire benef : beneficiaires) {
            if (benef.getLienFamilial().getId().equals(tarificateurSquareMappingService.getIdLienFamilialConjoint())) {
                codeGs = "C";
            }
            else if (benef.getLienFamilial().getId().equals(tarificateurSquareMappingService.getIdLienFamilialEnfant())) {
                nbsEnfant++;
            }
        }
        if (nbsEnfant >= 5) {
            codeGs += "X";
        }
        else if (nbsEnfant != 0) {
            codeGs += String.valueOf(nbsEnfant);
        }
        return tarificateurSquareMappingService.getCodeRoleParCompoFam(codeGs);
    }

    @Override
    public String getValeurCritereCodeCompoFamille(List<BeneficiaireDto> beneficiaires) {
        String codeGs = "I";
        int nbsEnfant = 0;
        for (BeneficiaireDto benef : beneficiaires) {
            if (benef.getIdLienFamilial().equals(tarificateurSquareMappingService.getIdLienFamilialConjoint())) {
                codeGs = "C";
            }
            else if (benef.getIdLienFamilial().equals(tarificateurSquareMappingService.getIdLienFamilialEnfant())) {
                nbsEnfant++;
            }
        }
        if (nbsEnfant >= 5) {
            codeGs += "X";
        }
        else if (nbsEnfant != 0) {
            codeGs += String.valueOf(nbsEnfant);
        }
        return tarificateurSquareMappingService.getCodeRoleParCompoFam(codeGs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getListeIdsProduitsParIdDevis(Long idDevis) {
        final List<Integer> listeIdsProduitsParIdDevis = new ArrayList<Integer>();
        // Récupération de la liste des produits de la gamme CNP
        final Devis devis = devisDao.getDevis(idDevis);
        for (LigneDevis ligne : devis.getListeLigneDevis()) {
            listeIdsProduitsParIdDevis.add(ligne.getEidProduit());
        }
        return listeIdsProduitsParIdDevis;
    }

    /**
     * {@inheritDoc}
     */
    public LigneDevisDto recalculerLigneDevis(final Long idDevis, final LigneDevisDto ligneDevis) {
        final PropositionLigneDevis prop = new PropositionLigneDevis();
        prop.setIdentifiantDevis(idDevis);
        prop.setLigneDevis(ligneDevis);
        return devisUtil.recalculerLigneDevis(prop);
    }

    @Override
    public void modifierOpportunite(OpportuniteModificationDto opportuniteModificationDto) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MODIFICATION_OPPORTUNITE,
       		 new String[] {String.valueOf(opportuniteModificationDto.getIdOpportunite()), String.valueOf(opportuniteModificationDto.getEidOpportunite())}));
        if (opportuniteModificationDto.getIdOpportunite() == null && opportuniteModificationDto.getEidOpportunite() == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERREUR_ID_OPPORTUNITE_NON_RENSEIGNE));
        }
        // Récupération de l'opportunité
        Opportunite opportunite = null;
        if (opportuniteModificationDto.getIdOpportunite() != null) {
            opportunite = opportuniteDao.getOpportuniteById(opportuniteModificationDto.getIdOpportunite());
        }
        else if (opportuniteModificationDto.getEidOpportunite() != null) {
            opportunite = opportuniteDao.getOpportuniteByEid(opportuniteModificationDto.getEidOpportunite());
        }
        if (opportunite == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_OPPORTUNITE_INEXISTANTE,
            		 new String[] {String.valueOf(opportuniteModificationDto.getIdOpportunite())}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERREUR_OPPORTUNITE_INEXISTANTE));
        }
        // Récupération de la finalité si renseigné
        if (opportuniteModificationDto.getFinalite() != null && opportuniteModificationDto.getFinalite().getIdentifiant() != null) {
            final Finalite finalite = finaliteDao.getFinaliteById(opportuniteModificationDto.getFinalite().getIdentifiant());
            if (finalite == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_OPPORTUNITE_FINALITE_INEXISTANTE,
               		 new String[] {String.valueOf(opportuniteModificationDto.getFinalite().getIdentifiant())}));
                throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERREUR_FINALITE_INEXISTANTE));
            }
            opportunite.setFinalite(finalite);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getIdsBaAdhesionNonTelecharges() {
        return opportuniteDao.getIdsBaAdhesionNonTelecharges();
    }

    /**
     * {@inheritDoc}
     */
    public Long mettreAJourInfosFichier(InfosBaDto infos) {
        final Adhesion adhesion = opportuniteDao.getAdhesionByIdFichierCNS(infos.getIdFichier());
        if (adhesion == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_OPPORTUNITE_FINALITE_INEXISTANTE_IDFICHIER,
              		 new String[] {String.valueOf(infos.getIdFichier())}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERREUR_ADHESION_INEXISTANTE));
        }
        adhesion.setDateClicBA(infos.getDateClicMail());
        adhesion.setDateTelechargementBA(infos.getDateTelechargement());
        return opportuniteDao.getIdDevisLieAdhesion(adhesion.getId(), tarificateurSquareMappingService.getIdFinaliteAcceptee());
    }

    /**
     * Remplit les adresses vides des membres de la famille avec l'adresse de la personne principale ou du conjoint.
     * @param personnePrincipale la personne principale
     */
    private void remplirAdressesVidesFamilleDevis(PersonneTarificateurDto personnePrincipale) {

        AdresseTarificateurDto adressePersonnePrincipale = null;
        AdresseTarificateurDto adresseConjoint = null;

        // Recherche des membres de la famille n'ayant pas d'adresse principale
        final List<PersonneTarificateurDto> listePersonnesAdressesVides = new ArrayList<PersonneTarificateurDto>();

        // Personne principale
        if (personnePrincipale.getAdressePrincipale() != null && personnePrincipale.getAdressePrincipale().getEidCodePostalCommune() != null) {
            adressePersonnePrincipale = personnePrincipale.getAdressePrincipale();
        }
        else {
            listePersonnesAdressesVides.add(personnePrincipale);
        }
        // Bénéficiaires
        final Long idLienFamilialConjoint = tarificateurSquareMappingService.getIdLienFamilialConjoint();
        if (personnePrincipale.getListeBeneficiaires() != null) {
            for (BeneficiaireTarificateurDto beneficiaire : personnePrincipale.getListeBeneficiaires()) {
                if (beneficiaire.getAdressePrincipale() != null && beneficiaire.getAdressePrincipale().getEidCodePostalCommune() != null) {
                    // Si c'est l'adresse principale du conjoint, on la stocke
                    if (idLienFamilialConjoint.equals(beneficiaire.getIdLienFamilial()) && adresseConjoint == null) {
                        adresseConjoint = beneficiaire.getAdressePrincipale();
                    }
                }
                else {
                    listePersonnesAdressesVides.add(beneficiaire);
                }
            }
        }
        // Remplissage des adresses vides
        if (!listePersonnesAdressesVides.isEmpty()) {
            // Exception si aucune adresse touvée
            if (adressePersonnePrincipale == null && adresseConjoint == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_ADRESSE_PRINCIPALE_INEXISTANTE));
                throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERREUR_CREATION_DEVIS_ADRESSE_PRINCIPALE_NON_RENSEIGNEE));
            }
            // Remplissage des adresses vides
            for (PersonneTarificateurDto personneSansAdresse : listePersonnesAdressesVides) {
                personneSansAdresse.setAdressePrincipale(adressePersonnePrincipale != null ? adressePersonnePrincipale : adresseConjoint);
            }
        }
    }

    @Override
    public OpportuniteDto getOpportuniteById(Long idOpportunite) {
        final Opportunite opportunite = opportuniteDao.getOpportuniteById(idOpportunite);
        if (opportunite != null) {
            // Création du cache des infos de personnes
            final InfosPersonneSquareBean infosPersonnesSquare = new InfosPersonneSquareBean();

            final OpportuniteDto opportuniteDto = mapperDozerBean.map(opportunite, OpportuniteDto.class);

            // Récupération de l'opportunité Square
            final InfosOpportuniteSquareBean infosOpportuniteSquare = new InfosOpportuniteSquareBean();
            completerOpportuniteDtoAvecOpportuniteSquare(opportuniteDto, infosOpportuniteSquare);

            // Comparaison de la famille passée en paramètre et la famille de chaque devis
            final List<Devis> listeDevisOpportunite = devisDao.getListeDevisByOpportunite(opportunite.getId());
            final List<DevisDto> listeDevisDto = new ArrayList<DevisDto>();
            for (Devis devisOpportunite : listeDevisOpportunite) {
                // Mapping du devis complet
                final DevisDto devisDto = mapperDozerBean.map(devisOpportunite, DevisDto.class);
                devisDto.setPersonnePrincipale(personneUtil.mapperPersonneEnPersonneDto(devisOpportunite.getPersonnePrincipale(), infosPersonnesSquare));
                // recupere le type du devis
                devisDto.setType(devisUtil.getTypeDevis(devisDto.getId()));
                // On complète les infos non figées
                completerDevisDtoAvecOpportuniteSquare(devisDto, opportunite.getEidOpportunite(), infosOpportuniteSquare);
                // on mappe les lignes
                final List<LigneDevis> listeLignesPrincipales = ligneDevisDao.getLigneDevisPrincipalParIdDevis(devisDto.getId());
                for (LigneDevis ligneDevisPrincipale : listeLignesPrincipales) {
                    final LigneDevisDto ligneDevisPrincipaleDto =
                        devisUtil.mapperLigneDevisPrincipale(ligneDevisPrincipale, infosPersonnesSquare, infosOpportuniteSquare);
                    devisDto.getListeLigneDevis().add(ligneDevisPrincipaleDto);
                }
                // On tri les lignes de devis
                Collections.sort(devisDto.getListeLigneDevis());
                listeDevisDto.add(devisDto);
            }
            opportuniteDto.setListeDevis(listeDevisDto);
            return opportuniteDto;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public OpportuniteDto getOpportuniteByNumTransaction(String numeroTransaction) {
        if (StringUtils.isBlank(numeroTransaction)) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_PARAM_NUMERO_TRANSACTION_VIDE));
        }
        final Opportunite opportunite = opportuniteDao.getOpportuniteByNumTransaction(numeroTransaction);
        if (opportunite == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_GET_OPP_BY_NUM_TRANSACTION_AUCUNE_OPPORTUNITE_TROUVEE));
        }
        final OpportuniteDto opportuniteDto = mapperDozerBean.map(opportunite, OpportuniteDto.class);
        // Récupération de l'opportunité Square
        final InfosOpportuniteSquareBean infosOpportuniteSquare = new InfosOpportuniteSquareBean();
        completerOpportuniteDtoAvecOpportuniteSquare(opportuniteDto, infosOpportuniteSquare);
        return opportuniteDto;
    }

    @Override
    public List<LigneDevisDto> getListeLignesDevisParCriteres(CriteresRechercheLigneDevisDto criteres) {
        final List<LigneDevis> listeLignesPrincipales =
            ligneDevisDao.getLigneDevisPrincipalParCriteres((CriteresRechercheLignesDevis) mapperDozerBean.map(criteres, CriteresRechercheLignesDevis.class));
        // Création du cache des infos de personnes et opportunités
        final InfosPersonneSquareBean infosPersonnesSquare = new InfosPersonneSquareBean();
        final InfosOpportuniteSquareBean infosOpportuniteSquare = new InfosOpportuniteSquareBean();
        final List<LigneDevisDto> resultat = new ArrayList<LigneDevisDto>();
        for (LigneDevis ligneDevis : listeLignesPrincipales) {
            resultat.add(devisUtil.mapperLigneDevisPrincipale(ligneDevis, infosPersonnesSquare, infosOpportuniteSquare));
        }
        return resultat;
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherMoyensPaiementParCriteres(IdentifiantLibelleDto criteres) {
        final List<MoyenPaiement> liste = moyenPaiementDao.rechercherMoyensPaiementParCriteres(criteres);
        return mapperDozerBean.mapList(liste, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherJoursPaiementParCriteres(IdentifiantLibelleDto criteres) {
        final List<JourPaiement> liste = jourPaiementDao.rechercherJoursPaiementParCriteres(criteres);
        return mapperDozerBean.mapList(liste, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherPeriodicitesPaiementParCriteres(IdentifiantLibelleDto criteres) {
        final List<PeriodicitePaiement> liste = periodicitePaiementDao.rechercherPeriodicitesPaiementParCriteres(criteres);
        return mapperDozerBean.mapList(liste, IdentifiantLibelleDto.class);
    }

    @Override
    public IdentifiantLibelleDto rechercherJourPaiementParId(Long id) {
        final JourPaiement jourPaiement = jourPaiementDao.getJourPaiement(id);
        return mapperDozerBean.map(jourPaiement, IdentifiantLibelleDto.class);
    }

    @Override
    public IdentifiantLibelleDto rechercherMoyenPaiementParId(Long id) {
        final MoyenPaiement moyenPaiement = moyenPaiementDao.getMoyenPaiement(id);
        return mapperDozerBean.map(moyenPaiement, IdentifiantLibelleDto.class);
    }

    @Override
    public IdentifiantLibelleDto rechercherPeriodicitePaiementParId(Long id) {
        final PeriodicitePaiement periodicitePaiement = periodicitePaiementDao.getPeriodicitePaiement(id);
        return mapperDozerBean.map(periodicitePaiement, IdentifiantLibelleDto.class);
    }

    @Override
    public void modifierDevis(DevisModificationDto devisModificationDto) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_MODIFICATION_DEVIS,
       		 new String[] {String.valueOf(devisModificationDto.getIdDevis())}));
        final Devis devis = devisDao.getDevis(devisModificationDto.getIdDevis());
        if (devis == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_MODIFICATION_DEVIS_IMPOSSIBLE_DEVIS_INEXISTANT,
            		 new String[] {String.valueOf(devisModificationDto.getIdDevis())}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MODIFICATION_DEVIS_IMPOSSIBLE_DEVIS_INEXISTANT));
        }

        // Motif
        final Long idMotifDevis = devisModificationDto.getIdMotifDevis();
        if (idMotifDevis != null) {
            final MotifDevis motifDevis = motifDevisDao.getMotifDevisById(idMotifDevis);
            if (motifDevis == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_MOTIF_DEVIS_INEXISTANT,
                  		 new String[] {String.valueOf(idMotifDevis)}));
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MOTIF_DEVIS_INEXISTANT));
            }
            devis.setMotif(motifDevis);
        }
    }

    /**
     * Vérification des champs obligatoires pour une personne.
     * @param personneDto le DTO de la personne
     */
    private void verifierChampsPersonne(PersonneTarificateurDto personneDto) {
        // EID
        if (personneDto.getEidPersonne() == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_EID_NULL));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_PERSONNE_EID_OBLIGATOIRE,
            		new String[] {String.valueOf(personneDto.getId())}));
        }
        // Date de naissance
        if (personneDto.getDateNaissance() == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_DATE_NAISSANCE_NULL,
            		 new String[] {String.valueOf(personneDto.getEidPersonne())}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_PERSONNE_DATE_NAISSANCE_OBLIGATOIRE, new String[] {personneDto
                    .getEidPersonne().toString()}));
        }
        // Vérification des adresses
        if (personneDto.getAdressePrincipale() != null) {
            verifierChampsAdresse(personneDto.getAdressePrincipale());
        }
        // Vérification des bénéficiaires
        if (personneDto.getListeBeneficiaires() != null && !personneDto.getListeBeneficiaires().isEmpty()) {
            for (PersonneTarificateurDto beneficiaire : personneDto.getListeBeneficiaires()) {
                verifierChampsPersonne(beneficiaire);
            }
        }
    }

    /**
     * Vérification des champs obligatoires pour une adresse.
     * @param adresseDto le DTO de l'adresse
     */
    private void verifierChampsAdresse(AdresseTarificateurDto adresseDto) {
        // Commune
        if (adresseDto.getEidCodePostalCommune() == null) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_CODE_POSTAL_COMMUNE_NULL,
           		 new String[] {String.valueOf(adresseDto.getEidAdresse())}));
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_ADRESSE_CODE_POSTAL_OBLIGATOIRE));
        }
    }

    /**
     * Complète le DTO de l'opportunité avec les infos non figées de Square.
     * @param opportunite le DTO de l'opportunité
     * @param infosOpportuniteSquare les infos de l'opportunité Square
     */
    private void completerOpportuniteDtoAvecOpportuniteSquare(OpportuniteDto opportunite, InfosOpportuniteSquareBean infosOpportuniteSquare) {
        // Récupération de l'opportunité Square
        final OpportuniteSimpleDto opportuniteSquare = opportuniteUtil.getOpportuniteSimple(opportunite.getEidOpportunite(), infosOpportuniteSquare);
        // Récupération de l'agence
        if (opportuniteSquare.getAgence() != null && opportuniteSquare.getAgence().getIdentifiantExterieur() != null) {
            opportunite.setEidAgence(Long.valueOf(opportuniteSquare.getAgence().getIdentifiantExterieur()));
        }
        // Récupération de la ressource
        if (opportuniteSquare.getRessource() != null && opportuniteSquare.getRessource().getIdentifiantExterieur() != null) {
            opportunite.setEidResponsable(Long.valueOf(opportuniteSquare.getRessource().getIdentifiantExterieur()));
        }
        // Récupération de l'identifiant Square de la personne physique
        opportunite.setEidPersonnePrincipale(opportuniteSquare.getIdPersonnePhysique());
    }

    /**
     * Complète le DTO du devis avec les infos non figées de l'opportunité Square.
     * @param devis le DTO du devis
     * @param eidOpportunite l'identifiant Square de l'opportunité
     * @param infosOpportuniteSquare les infos de l'opportunité Square
     */
    private void completerDevisDtoAvecOpportuniteSquare(DevisDto devis, Long eidOpportunite, InfosOpportuniteSquareBean infosOpportuniteSquare) {
        // Récupération de l'opportunité Square
        final OpportuniteSimpleDto opportuniteSquare = opportuniteUtil.getOpportuniteSimple(eidOpportunite, infosOpportuniteSquare);
        // Récupération de l'agence
        if (opportuniteSquare.getAgence() != null && opportuniteSquare.getAgence().getIdentifiantExterieur() != null) {
            devis.setEidAgence(Long.valueOf(opportuniteSquare.getAgence().getIdentifiantExterieur()));
        }
        // Récupération de la ressource
        if (opportuniteSquare.getRessource() != null && opportuniteSquare.getRessource().getIdentifiantExterieur() != null) {
            devis.setEidResponsable(Long.valueOf(opportuniteSquare.getRessource().getIdentifiantExterieur()));
        }
    }

    @Override
    public BeneficiaireDto getBeneficiaireByCible(Long idBeneficiaire) {
        final Beneficiaire beneficiaire = beneficiaireDao.getBeneficiaireByCible(idBeneficiaire);
        return mapperDozerBean.map(beneficiaire, BeneficiaireDto.class);
    }

    @Override
    public List<Long> rechercherIdsOpportunitesByCritere(CriteresRechercheOpportuniteDto criteres) {
        final List<Long> listeIdsOpportunitesSquare = new ArrayList<Long>();

        // Récupération des opportunités du tarificateur square correspondant aux critères
        final List<Opportunite> listeOpportunites = opportuniteDao.rechercherOpportunitesByCritere(criteres);

        // Filtre sur les opportunités santé si critère spécifié
        if (listeOpportunites != null && listeOpportunites.size() > 0 && criteres.getIsOppSante() != null && criteres.getIsOppSante()) {
            final Long idFinaliteAcceptee = tarificateurSquareMappingService.getIdFinaliteAcceptee();
            final Long idFinaliteTransformee = tarificateurSquareMappingService.getIdFinaliteTransformee();
            final Integer idCategorieGammeSante = tarificateurMappingService.getIdentifiantCategorieSante();
            for (Opportunite opportunite : listeOpportunites) {
                final List<Devis> listeDevis = devisDao.getListeDevisByOpportunite(opportunite.getId());
                if (listeDevis != null && listeDevis.size() > 0) {
                    boucleDevis: for (Devis devis : listeDevis) {
                        if (idFinaliteTransformee.equals(devis.getFinalite().getId()) && devis.getListeLigneDevis() != null
                            && devis.getListeLigneDevis().size() > 0) {
                            for (LigneDevis ligneDevis : devis.getListeLigneDevis()) {
                                if (ligneDevis.getFinalite() != null && idFinaliteAcceptee.equals(ligneDevis.getFinalite().getId())) {
                                    // on regarde si le produit associé à la ligne de devis est de type santé
                                    final Integer idProduit = ligneDevis.getEidProduit();
                                    final ProduitCriteresDto critereProduit = new ProduitCriteresDto();
                                    critereProduit.setIdentifiantProduit(idProduit);
                                    final List<ProduitDto> listeProduits = produitService.getListeProduits(critereProduit);
                                    if (listeProduits == null || listeProduits.size() != 1) {
                                        throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IMPOSSIBLE_RECUPERER_PRODUIT,
                                            new String[] {String.valueOf(listeProduits.get(0).getIdentifiant())}));
                                    }
                                    if (idCategorieGammeSante.equals(listeProduits.get(0).getGamme().getIdCategorie())) {
                                        listeIdsOpportunitesSquare.add(Long.valueOf(opportunite.getEidOpportunite()));
                                        break boucleDevis;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            if (listeOpportunites != null && listeOpportunites.size() > 0) {
                for (Opportunite opportunite : listeOpportunites) {
                    listeIdsOpportunitesSquare.add(Long.valueOf(opportunite.getEidOpportunite()));
                }
            }
        }

        return listeIdsOpportunitesSquare;
    }

    @Override
    public void deleteLigneDevisfromDevis(Long idDevis) {
        final Devis devis = devisDao.getDevis(idDevis);
        if (devis != null) {
            if (devis.getListeLigneDevis() != null && devis.getListeLigneDevis().size() > 0) {
                for (LigneDevis ligneDevis : devis.getListeLigneDevis()) {
                    ligneDevisDao.deleteLigneDevisFromDevis(ligneDevis);
                }
            }
        }
    }

    @Override
    public void deleteLigneDevis(Long idLigneDevis) {
        final LigneDevis ligneDevis = ligneDevisDao.getLigneDevis(idLigneDevis);
        if (ligneDevis != null) {
            ligneDevisDao.deleteLigneDevisFromDevis(ligneDevis);
        }
    }

    @Override
    public boolean comparerFamille(PersonneTarificateurDto personneAComparer, Long idPersonneReference) {
        // Récupération de la personne de référence
        final Personne personneReference = personneDao.getPersonne(idPersonneReference);
        if (personneReference == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANTE));
        }

        // Création du comparateur de familles
        final ComparaisonFamilleUtil comparaisonFamilleUtil = new ComparaisonFamilleUtil(tarificateurSquareMappingService, messageSourceUtil, customConverter);
        // Comparaison
        return comparaisonFamilleUtil.isFamillesIdentiques(personneAComparer, personneReference);
    }

    @Override
    public void modifierSourceLigneDevis(Long idLigne, Long idSource) {
        final LigneDevis ligneDevis = ligneDevisDao.getLigneDevis(idLigne);
        if (ligneDevis == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_AUCUNE_LIGNE_DEVIS_CORRESPONDANT));
        }
        final SourceLigneDevis source = sourceLigneDevisDao.getSourceLigneDevisParId(idSource);
        if (source == null) {
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERREUR_SOURCE_LIGNE_DEVIS_INEXISTANTE));
        }
        ligneDevis.setSourceLigneDevis(source);
        for (LigneDevisLiee ligneDevisLiee : ligneDevis.getListeLignesDevisLiees()) {
            ligneDevisLiee.getLigneDevisLiee().setSourceLigneDevis(source);
        }
    }

    @Override
    public void modifierSelectionPourImpression(Long idDevis, boolean isSelectionne) {
        final Devis devis = devisDao.getDevis(idDevis);
        if (devis == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEVIS_INEXISTANT));
        }
        for (LigneDevis ligneDevis : devis.getListeLigneDevis()) {
            ligneDevis.setSelectionnePourImpression(isSelectionne);
            for (LigneDevisLiee ligneDevisLiee : ligneDevis.getListeLignesDevisLiees()) {
                ligneDevisLiee.getLigneDevisLiee().setSelectionnePourImpression(isSelectionne);
            }
        }
    }

    @Override
    public Boolean personneIsBeneficiaireSurOppTransforme(Long eidPersonne) {
        final Long idStatutTransforme = tarificateurSquareMappingService.getIdFinaliteTransformee();
        final Long idStatutAccepte = tarificateurSquareMappingService.getIdFinaliteAcceptee();
        final List<Personne> personnes = personneDao.getListePersonnesByEid(eidPersonne);
        for (Personne personne : personnes) {
            final CriteresRechercheLignesDevis criteres = new CriteresRechercheLignesDevis();
            criteres.setIdBeneficiaire(personne.getId());
            criteres.setIdFinalite(idStatutAccepte);
            final List<LigneDevis> lignesDevis = ligneDevisDao.getLigneDevisPrincipalParCriteres(criteres);
            for (LigneDevis ligne : lignesDevis) {
                if (ligne.getDevis().getFinalite().getId().equals(idStatutTransforme)) {
                    return true;
                }
            }
        }
        return false;
    }


	@Override
	public List<IdentifiantLibelleDto> getBeneficiairesFromProspectByDevis(DevisDto devis, List<Long> listeIdsLignesSelectionnees) {
       if (devis == null) {
    	   throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DEVIS_INEXISTANT));
       }
       if (devis.getId() == null) {
    	   throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_IDENTIFIANT_DEVIS_INCONNU));
       }
       final List<IdentifiantLibelleDto> listeBeneficiaires = new ArrayList<IdentifiantLibelleDto>();
        // Détermine si on a un produit avec un code de tarification 'adherent'
       boolean isCodeTarificationAdherent = false;
       for (LigneDevisDto ligneDevis : devis.getListeLigneDevis()) {
    	   if (listeIdsLignesSelectionnees.contains(ligneDevis.getIdentifiant())) {
                final Integer idProduit = ligneDevis.getIdentifiantProduit();
                final ProduitCriteresDto criteres = new ProduitCriteresDto();
                criteres.setIdentifiantProduit(idProduit);
                final List<ProduitDto> listeProduits = produitService.getListeProduits(criteres);
                if (listeProduits == null || listeProduits.size() != 1) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_PRODUIT_LIGNE_DEVIS_INEXISTANT));
                }
                final ProduitDto produitDto = listeProduits.get(0);
                if (produitDto.getModeTarification() != null
                        && tarificateurMappingService.getConstanteIdModeTarificationAdherent().equals(produitDto.getModeTarification()
                        		.getIdentifiant())) {
                    isCodeTarificationAdherent = true;
                    break;
                }
            }
        }

        if (isCodeTarificationAdherent) {
            // Récupération des bénéficiaires du prospect
            final List<BeneficiaireDto> listeBenefs = devis.getPersonnePrincipale().getListeBeneficiaires();
            if (listeBenefs != null && listeBenefs.size() > 0) {
                for (BeneficiaireDto benefProspect : listeBenefs) {
                    final String libelle = benefProspect.getNom().toUpperCase() + " " + benefProspect.getPrenom();
                    final IdentifiantLibelleDto benefGwt = new IdentifiantLibelleDto(benefProspect.getId(), libelle);
                    listeBeneficiaires.add(benefGwt);
                }
            }
        }
		return listeBeneficiaires;
	}

    /**
     * Définit la valeur de moyenPaiementDao.
     * @param moyenPaiementDao la nouvelle valeur de moyenPaiementDao
     */
    public void setMoyenPaiementDao(MoyenPaiementDao moyenPaiementDao) {
        this.moyenPaiementDao = moyenPaiementDao;
    }

    /**
     * Définit la valeur de periodicitePaiementDao.
     * @param periodicitePaiementDao la nouvelle valeur de periodicitePaiementDao
     */
    public void setPeriodicitePaiementDao(PeriodicitePaiementDao periodicitePaiementDao) {
        this.periodicitePaiementDao = periodicitePaiementDao;
    }

    /**
     * Définit la valeur de jourPaiementDao.
     * @param jourPaiementDao la nouvelle valeur de jourPaiementDao
     */
    public void setJourPaiementDao(JourPaiementDao jourPaiementDao) {
        this.jourPaiementDao = jourPaiementDao;
    }

    /**
     * Set the personneDao value.
     * @param personneDao the personneDao to set
     */
    public void setPersonneDao(PersonneDao personneDao) {
        this.personneDao = personneDao;
    }

    /**
     * Set the lienFamilialDao value.
     * @param lienFamilialDao the lienFamilialDao to set
     */
    public void setLienFamilialDao(LienFamilialDao lienFamilialDao) {
        this.lienFamilialDao = lienFamilialDao;
    }

    /**
     * Set the dimensionService value.
     * @param dimensionService the dimensionService to set
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
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
     * @param opportuniteDao the opportuniteDao to set
     */
    public void setOpportuniteDao(OpportuniteDao opportuniteDao) {
        this.opportuniteDao = opportuniteDao;
    }

    /**
     * Setter function.
     * @param finaliteDao the finaliteDao to set
     */
    public void setFinaliteDao(FinaliteDao finaliteDao) {
        this.finaliteDao = finaliteDao;
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
     * @param beneficiaireDao the beneficiaireDao to set
     */
    public void setBeneficiaireDao(BeneficiaireDao beneficiaireDao) {
        this.beneficiaireDao = beneficiaireDao;
    }

    /**
     * Définit la valeur de customConverter.
     * @param customConverter la nouvelle valeur de customConverter
     */
    public void setCustomConverter(Map<Class<? extends Object>, Converter> customConverter) {
        this.customConverter = customConverter;
    }

    /**
     * Set the motifDevisDao value.
     * @param motifDevisDao the motifDevisDao to set
     */
    public void setMotifDevisDao(MotifDevisDao motifDevisDao) {
        this.motifDevisDao = motifDevisDao;
    }

    /**
     * Définit la valeur de editiqueMappingService.
     * @param editiqueMappingService la nouvelle valeur de editiqueMappingService
     */
    public void setEditiqueMappingService(EditiqueMappingService editiqueMappingService) {
        this.editiqueMappingService = editiqueMappingService;
    }

    /**
     * Set the devisUtil value.
     * @param devisUtil the devisUtil to set
     */
    public void setDevisUtil(DevisUtil devisUtil) {
        this.devisUtil = devisUtil;
    }

    /**
     * Définit la valeur de opportuniteServiceSquare.
     * @param opportuniteServiceSquare la nouvelle valeur de opportuniteServiceSquare
     */
    public void setOpportuniteServiceSquare(OpportuniteService opportuniteServiceSquare) {
        this.opportuniteServiceSquare = opportuniteServiceSquare;
    }

    /**
     * Définition de validationInfosAdhesionUtil.
     * @param validationInfosAdhesionUtil the validationInfosAdhesionUtil to set
     */
    public void setValidationInfosAdhesionUtil(ValidationInfosAdhesionUtil validationInfosAdhesionUtil) {
        this.validationInfosAdhesionUtil = validationInfosAdhesionUtil;
    }

    /**
     * Définition de contratService.
     * @param contratService the contratService to set
     */
    public void setContratService(ContratService contratService) {
        this.contratService = contratService;
    }

    /**
     * Définition de actionService.
     * @param actionService the actionService to set
     */
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    /**
     * Définit la valeur de personneUtil.
     * @param personneUtil la nouvelle valeur de personneUtil
     */
    public void setPersonneUtil(PersonneUtil personneUtil) {
        this.personneUtil = personneUtil;
    }

    /**
     * Définit la valeur de personnePhysiqueService.
     * @param personnePhysiqueService la nouvelle valeur de personnePhysiqueService
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Définit la valeur de personneService.
     * @param personneService la nouvelle valeur de personneService
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Définit la valeur de opportuniteUtil.
     * @param opportuniteUtil la nouvelle valeur de opportuniteUtil
     */
    public void setOpportuniteUtil(OpportuniteUtil opportuniteUtil) {
        this.opportuniteUtil = opportuniteUtil;
    }

    /**
     * Définit le tarificateurPersonneService.
     * @param tarificateurPersonneService le nouveau tarificateurPersonneService
     */
    public void setTarificateurPersonneService(TarificateurPersonneService tarificateurPersonneService) {
        this.tarificateurPersonneService = tarificateurPersonneService;
    }

    /**
     * Définit le squareMappingService.
     * @param squareMappingService le nouveau squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définit le relationAssureSocialDao.
     * @param relationAssureSocialDao le nouveau relationAssureSocialDao
     */
    public void setRelationAssureSocialDao(RelationAssureSocialDao relationAssureSocialDao) {
        this.relationAssureSocialDao = relationAssureSocialDao;
    }

    /**
     * Définit le sourceLigneDevisDao.
     * @param sourceLigneDevisDao le nouveau sourceLigneDevisDao
     */
    public void setSourceLigneDevisDao(SourceLigneDevisDao sourceLigneDevisDao) {
        this.sourceLigneDevisDao = sourceLigneDevisDao;
    }

    /**
     * Set the value of adherentService.
     * @param adherentService the adherentService to set
     */
    public void setAdherentService(AdherentService adherentService) {
        this.adherentService = adherentService;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }
}
