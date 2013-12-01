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
package com.square.tarificateur.noyau.util.selecteur.produit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.adherent.noyau.dto.produit.CriteresInfosProduitsDto;
import com.square.adherent.noyau.dto.produit.InfosProduitDto;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.core.model.dto.IdentifiantLibelleDepartementCodeDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.price.core.dto.ContrainteVenteDto;
import com.square.price.core.dto.CritereCriteresDto;
import com.square.price.core.dto.CritereDto;
import com.square.price.core.dto.ProduitCriteresDto;
import com.square.price.core.dto.ProduitDto;
import com.square.price.core.dto.ProduitLieCriteresDto;
import com.square.price.core.dto.ProduitLieDto;
import com.square.price.core.dto.ZonageCriteresDto;
import com.square.price.core.dto.ZonageDto;
import com.square.price.core.service.interfaces.ProduitService;
import com.square.price.core.service.interfaces.TarificateurMappingService;
import com.square.tarificateur.noyau.bean.PropositionLigneDevis;
import com.square.tarificateur.noyau.dao.interfaces.PersonneDao;
import com.square.tarificateur.noyau.dto.RapportDto;
import com.square.tarificateur.noyau.dto.devis.LigneDevisDto;
import com.square.tarificateur.noyau.dto.devis.ValeurCritereLigneDevisDto;
import com.square.tarificateur.noyau.dto.personne.BeneficiaireDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.AssureSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.ContrainteVenteSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.CritereSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.FamilleLieeSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.FamillePrincipaleSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.FamilleSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.ProduitSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.SelecteurProduitDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.ValeurCritereAssureSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.ValeurCritereSelecteurDto;
import com.square.tarificateur.noyau.exception.ControleIntegriteException;
import com.square.tarificateur.noyau.service.interfaces.TarificateurSquareMappingService;
import com.square.tarificateur.noyau.util.DateUtil;
import com.square.tarificateur.noyau.util.MessageKeyUtil;
import com.square.tarificateur.noyau.util.validation.RapportUtil;

/**
 * Classe utilitaire pour construire le selecteur produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class BuilderSelecteurProduit {

    private static final Integer CONSTANTE_IDENTIFIANT_SIMULACRE_PRODUIT_PRINCIPAL = 964138725;

    private static final Integer CONSTANTE_IDENTIFIANT_CRITERE_SIMULACRE_TYPE_PRINCIPAL = 829576314;

    private static final String CONSTANTE_LIBELLE_CRITERE_SIMULACRE_TYPE_PRINCIPAL = "Type de garantie";

    /** Mapping dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Service produit tarificateur. */
    private ProduitService produitService;

    /** Service Contrat. */
    private ContratService contratService;

    /** Service TarificateurSquareMapping. */
    private TarificateurSquareMappingService tarificateurSquareMappingService;

    /** Service de mapping des adhérents. */
    private AdherentMappingService adherentMappingService;

    /** Service Dimension. */
    private DimensionService dimensionService;

    /** Service PersonnePhysique. */
    private PersonnePhysiqueService personnePhysiqueService;

    private MessageSourceUtil messageSourceUtil;

    private Integer idApplicationGestcom;

    private Long idModeTarificationAdherent;

    private Integer idFamilleNo;

    private Integer idFamilleBonus1;

    private Integer idFamilleBonus2;

    private Integer idCritereAgeMax;

    private Integer idCritereMontantMax;

    private Integer idCritereAgeMin;

    private Integer idCritereMontantMin;

    private Integer idCritereLienFamille;

    private Integer idCritereCompositionFamille;

    private Integer idCritereZoneTarifaire;

    private Integer idCritereGeneration;

    private Integer idCritereMois;

    private Integer idCritereGarantieSouscrite;

    private Integer constanteNbAnneesBonus1;

    private Integer constanteNbAnneesBonus2;

    private Long idLienFamilialEnfant;

    private Long idLienFamilialAssurePrincipal;

    private Long idLienFamilialConjoint;

    private String codeCompoAssurePrincipal;

    private String codeCompoConjoint;

    private String codeCompoEnfant;

    private String typeHtmlSelect;

    private Integer constanteIdCritereSimulacreTypeGo;

    private Integer constanteIdModeTarificationFamille;

    private String constanteLienFamilleConjoint;

    private String constanteLienFamilleEnfant;

    private SimpleDateFormat formateurMois = new SimpleDateFormat("MM");

    private SimpleDateFormat sdfDateConcat = new SimpleDateFormat("ddMMyyyy");

    private Logger logger = Logger.getLogger(this.getClass());

    private final int valeurCritereGeneration = initValeurCritereGeneration();

    private int initValeurCritereGeneration() {
        final Date date = new Date();
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Constructeur.
     * @param mapperDozerBean mapperDozerBean
     * @param produitService produitService
     * @param tarificateurMappingService tarificateurMappingService
     * @param tarificateurSquareMappingService tarificateurSquareMappingService
     * @param contratService contratService
     * @param dimensionService dimensionService
     * @param personneDao personneDao
     * @param messageSourceUtil messageSourceUtil
     * @param adherentMappingService adherentMappingService
     * @param personnePhysiqueService personnePhysiqueService
     */
    public BuilderSelecteurProduit(MapperDozerBean mapperDozerBean, ProduitService produitService, TarificateurMappingService tarificateurMappingService,
        TarificateurSquareMappingService tarificateurSquareMappingService, ContratService contratService, DimensionService dimensionService,
        PersonneDao personneDao, MessageSourceUtil messageSourceUtil, AdherentMappingService adherentMappingService,
        PersonnePhysiqueService personnePhysiqueService) {
        this.messageSourceUtil = messageSourceUtil;
        this.mapperDozerBean = mapperDozerBean;
        this.produitService = produitService;
        this.contratService = contratService;
        this.tarificateurSquareMappingService = tarificateurSquareMappingService;
        this.adherentMappingService = adherentMappingService;
        this.dimensionService = dimensionService;
        this.personnePhysiqueService = personnePhysiqueService;

        this.idApplicationGestcom = tarificateurMappingService.getIdentifiantApplicationGestcom();
        this.idModeTarificationAdherent = tarificateurMappingService.getConstanteIdModeTarificationAdherent();
        this.idFamilleNo = tarificateurMappingService.getIdentifiantFamilleNo();

        this.idCritereAgeMax = tarificateurMappingService.getIdentifiantCritereAgeMax();
        this.idCritereMontantMax = tarificateurMappingService.getIdentifiantCritereMontantDeux();
        this.idCritereAgeMin = tarificateurMappingService.getIdentifiantCritereAgeMin();
        this.idCritereMontantMin = tarificateurMappingService.getIdentifiantCritereMontant();
        this.idCritereLienFamille = tarificateurMappingService.getIdentifiantCritereLienFamille();
        this.idCritereCompositionFamille = tarificateurMappingService.getIdentifiantCritereCompositionFamille();
        this.idCritereZoneTarifaire = tarificateurMappingService.getIdentifiantCritereZoneTarifaire();
        this.idCritereGeneration = tarificateurMappingService.getIdentifiantCritereGeneration();
        this.idCritereMois = tarificateurMappingService.getIdentifiantCritereMois();
        this.idCritereGarantieSouscrite = tarificateurMappingService.getIdentifiantCritereGarantieSouscrite();
        this.typeHtmlSelect = tarificateurMappingService.getConstanteTypeHTMLSelect();

        this.constanteNbAnneesBonus1 = tarificateurSquareMappingService.getNbAnneesBonus1();
        this.constanteNbAnneesBonus2 = tarificateurSquareMappingService.getNbAnneesBonus2();
        this.idFamilleBonus1 = tarificateurMappingService.getIdentifiantFamilleBonus1();
        this.idFamilleBonus2 = tarificateurMappingService.getIdentifiantFamilleBonus2();

        this.idLienFamilialAssurePrincipal = tarificateurSquareMappingService.getIdLienFamilialAssurePrincipal();
        this.idLienFamilialConjoint = tarificateurSquareMappingService.getIdLienFamilialConjoint();
        this.idLienFamilialEnfant = tarificateurSquareMappingService.getIdLienFamilialEnfant();
        this.codeCompoAssurePrincipal = tarificateurSquareMappingService.getCodeCompoParCompoFam(idLienFamilialAssurePrincipal);
        this.codeCompoConjoint = tarificateurSquareMappingService.getCodeCompoParCompoFam(idLienFamilialConjoint);
        this.codeCompoEnfant = tarificateurSquareMappingService.getCodeCompoParCompoFam(idLienFamilialEnfant);

        this.constanteIdModeTarificationFamille = tarificateurMappingService.getConstanteIdModeTarificationFamille();
        this.constanteLienFamilleConjoint = tarificateurMappingService.getConstanteLienFamilleConjoint();
        this.constanteLienFamilleEnfant = tarificateurMappingService.getConstanteLienFamilleEnfant();
    }

    /**
     * Contruit un selecteur suivant un produit d'un devis.
     * @param idProduit id du produit à convertir
     * @param idDevis id du devis
     * @param personnePrincipale la personne principale
     * @param isSelecteurProduitParProduitsAdherent permet d'afficher aussi les produits liés non actifs
     * @return le selecteur
     */
    public SelecteurProduitDto getSelecteurParProduit(Integer idProduit, Long idDevis, PersonneDto personnePrincipale,
        boolean isSelecteurProduitParProduitsAdherent) {
        // on cree le selecteur
        final SelecteurProduitDto selecteurProduit = new SelecteurProduitDto();
        selecteurProduit.setIdentifiantDevis(idDevis);

        // on stocke les liens familiaux des benefs dans une map pour simplifier le traitement
        final Map<String, Long> mapLiensFamiliauxBenefs = new HashMap<String, Long>();
        for (BeneficiaireDto beneficiaire : personnePrincipale.getListeBeneficiaires()) {
            // on stocke dans la map suivant {nom}{prenom}{datenaissance} car id peut etre null si nouvelle famille
            final String cle = beneficiaire.getNom() + beneficiaire.getPrenom() + sdfDateConcat.format(beneficiaire.getDateNaissance().getTime());
            mapLiensFamiliauxBenefs.put(cle, beneficiaire.getIdLienFamilial());
        }

        // on recupere le produit principal
        final ProduitCriteresDto criteresProduit = new ProduitCriteresDto();
        criteresProduit.setIdentifiantProduit(idProduit);
        final List<ProduitDto> listeProduits = produitService.getListeProduits(criteresProduit);
        if (listeProduits == null || listeProduits.size() != 1) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IMPOSSIBLE_RECUPERER_PRODUIT));
        }
        final ProduitDto produitPrincipal = listeProduits.get(0);

        // on ajoute la famille principale
        final FamillePrincipaleSelecteurDto famillePrincipale = new FamillePrincipaleSelecteurDto();
        createFamille(famillePrincipale, produitPrincipal, true);
        // on ajoute le produit principal
        ajouterProduit(produitPrincipal, true, false, false, famillePrincipale, personnePrincipale, mapLiensFamiliauxBenefs,
            isSelecteurProduitParProduitsAdherent);
        // on met à jour le champ optionnel et selection de la famille
        famillePrincipale.setOptionnel(Boolean.FALSE);
        famillePrincipale.setIsSelection(Boolean.TRUE);
        selecteurProduit.setFamillePrincipale(famillePrincipale);

        // on recupere les produits liés
        final ProduitLieCriteresDto criteresProduitsLies = new ProduitLieCriteresDto();
        criteresProduitsLies.setIdentifiantApplication(idApplicationGestcom);
        criteresProduitsLies.setIdentifiantProduit(produitPrincipal.getIdentifiant());
        criteresProduitsLies.setSeulementActif(!isSelecteurProduitParProduitsAdherent);
        final List<ProduitLieDto> listeProduitLies = produitService.getListeProduitsLies(criteresProduitsLies);

        // on initialise la liste des familles liées
        selecteurProduit.setListeFamillesLiees(new ArrayList<FamilleLieeSelecteurDto>());

        // on parcours la liste des produits liés
        for (ProduitLieDto produitLie : listeProduitLies) {
            final ProduitDto produit = produitLie.getProduitLie();
            // on verifie si la famille existe deja ou pas
            final FamilleSelecteurDto famille = getFamilleDansSelecteur(produit, selecteurProduit);

            // on ajoute le produit
            ajouterProduit(produit, false, produitLie.getOptionnel(), produitLie.getActif(), famille, personnePrincipale, mapLiensFamiliauxBenefs,
                isSelecteurProduitParProduitsAdherent);

            // si famille liée
            if (famille instanceof FamilleLieeSelecteurDto) {
                // on met à jour le champ optionnel et selection de la famille
                famille.setOptionnel(famille.getOptionnel().booleanValue() && produitLie.getOptionnel() != null && produitLie.getOptionnel().booleanValue());
                famille.setIsSelection(!famille.getOptionnel().booleanValue());
                // on ordonne la liste des produits de la famille si famille liée
                Collections.sort(((FamilleLieeSelecteurDto) famille).getListeProduitsLies());
            }
        }

        if (selecteurProduit.getListeFamillesLiees() != null) {
            // on ordonne la liste des familles liées
            Collections.sort(selecteurProduit.getListeFamillesLiees());
        }

        // on verifie si il y a les familles bonus1 et bonus2 dans le selecteur
        FamilleLieeSelecteurDto familleBonus1 = null;
        FamilleLieeSelecteurDto familleBonus2 = null;
        for (FamilleLieeSelecteurDto familleLiee : selecteurProduit.getListeFamillesLiees()) {
            if (familleLiee.getIdentifiant().equals(idFamilleBonus1)) {
                familleBonus1 = familleLiee;
            } else if (familleLiee.getIdentifiant().equals(idFamilleBonus2)) {
                familleBonus2 = familleLiee;
            }
        }
        // on charge les infos des produits bonus dans le selecteur si famille bonus
        if (familleBonus1 != null || familleBonus2 != null) {
            recupererInfosProduitsBonus(familleBonus1, familleBonus2, personnePrincipale);
        }

        return selecteurProduit;
    }

    /**
     * Crée la famille à partir des infos fournies.
     */
    private void createFamille(FamilleSelecteurDto famille, ProduitDto produit, boolean isProduitPrincipal) {
        // si no famille, on cree une famille specifique au niveau du libellé
        if (produit.getIdentifiantFamille().equals(idFamilleNo)) {
            famille.setLibelle(produit.getLibelleCommercial());
        } else {
            famille.setLibelle(produit.getLibelleFamille());
        }
        famille.setIdentifiant(produit.getIdentifiantFamille());
        famille.setLibelleGamme(produit.getGamme().getLibelle());
        famille.setOrdreGamme(produit.getGamme().getOrdreAffichage());
        famille.setOrdreFamille(produit.getOrdreAffichageFamille());
        famille.setOptionnel(Boolean.TRUE);

        // si il s'agit de la famille du produit principal, on selectionne par défaut
        if (isProduitPrincipal) {
            famille.setIsSelection(Boolean.TRUE);
        }
    }

    /**
     * Recupere une famille liée dans le selecteur si elle existe.
     */
    private FamilleSelecteurDto getFamilleDansSelecteur(ProduitDto produit, SelecteurProduitDto selecteurProduitDto) {
        // si la famille recherchée n'est pas la "no famille"
        if (!produit.getIdentifiantFamille().equals(idFamilleNo)) {
            // on verifie la famille principale
            if (selecteurProduitDto.getFamillePrincipale().getIdentifiant().equals(produit.getIdentifiantFamille())) {
                return selecteurProduitDto.getFamillePrincipale();
            }

            if (selecteurProduitDto.getListeFamillesLiees() != null) {
                // on parcours les familles liées du selecteur
                for (FamilleLieeSelecteurDto familleLiee : selecteurProduitDto.getListeFamillesLiees()) {
                    if (familleLiee.getIdentifiant().equals(produit.getIdentifiantFamille())) {
                        return familleLiee;
                    }
                }
            }
        }
        // sinon on cree la famille
        final FamilleLieeSelecteurDto famille = new FamilleLieeSelecteurDto();
        createFamille(famille, produit, false);
        // on initialise la liste des produits de la famille liée
        famille.setListeProduitsLies(new ArrayList<ProduitSelecteurDto>());
        selecteurProduitDto.getListeFamillesLiees().add(famille);
        return famille;
    }

    /**
     * Crée un produit complet pour le selecteur.
     */
    private void ajouterProduit(ProduitDto produit, boolean produitPrincipal, boolean produitLieOptionnel, boolean produitLieActif,
        FamilleSelecteurDto famille, PersonneDto personnePrincipale, Map<String, Long> mapLiensFamiliauxBenefs, boolean isSelecteurProduitParProduitsAdherent) {
        boolean isExistingProduit = false;
        ProduitSelecteurDto produitSelecteur = null;

        // si on essaye d'ajouter un produit lié dans la famille principale
        if (famille instanceof FamillePrincipaleSelecteurDto && !produitPrincipal && ((FamillePrincipaleSelecteurDto) famille).getProduitPrincipal() != null) {
            // on transforme le produit principale en critere simulacre
            transformerProduitPrincipalEnSimulacre((FamillePrincipaleSelecteurDto) famille);
            // on recupere le critere simulacre du produit principal
            for (CritereSelecteurDto critere : ((FamillePrincipaleSelecteurDto) famille).getProduitPrincipal().getListeCriteres()) {
                if (critere.getIdentifiant().equals(CONSTANTE_IDENTIFIANT_CRITERE_SIMULACRE_TYPE_PRINCIPAL)) {
                    // on ajoute le produit en tant que valeur de critere
                    final ValeurCritereSelecteurDto valeurCritere = new ValeurCritereSelecteurDto(produit.getIdentifiant().toString(), produit.getLibelle());
                    critere.getListeValeursCriteres().add(valeurCritere);
                    // on ordonne la liste
                    Collections.sort(critere.getListeValeursCriteres());
                }
            }
            return;
        }

        // on détermine si le produit est ouvert à la vente
        final boolean isProduitOuverVente = produitService.isProduitOuvertVente(produit.getIdentifiant());

        // si le produitSelecteur != produit GO ou produitSelecteur == produit GO et qu'il n'existe pas encore
        if (produitSelecteur == null) {
            produitSelecteur = mapperDozerBean.map(produit, ProduitSelecteurDto.class);

            // on ajoute l'assure au produit
            final AssureSelecteurDto assurePrincipal = (AssureSelecteurDto) mapperDozerBean.map(personnePrincipale, AssureSelecteurDto.class);
            // Récupération du nom et du prénom de l'assuré
            final PersonneSimpleDto assurePrincipalSquare = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(personnePrincipale.getEidPersonne());
            assurePrincipal.setNom(assurePrincipalSquare.getNom());
            assurePrincipal.setPrenom(assurePrincipalSquare.getPrenom());
            assurePrincipal.setListeValeursCriteres(new ArrayList<ValeurCritereAssureSelecteurDto>());
            // si il s'agit du produit principal, on selectionne l'assure par défaut
            if (produitPrincipal || !produitLieOptionnel) {
                assurePrincipal.setIsSelection(Boolean.TRUE);
            }

            // on selectionne le produit si il s'agit d'un produit lié obligatoire
            produitSelecteur.setIsSelection(!produitLieOptionnel);

            // on initialise la date d'effet au 1er du mois suivant
            final Calendar dateEffet = DateUtil.getDatePremierMoisSuivant();
            // si il s'agit d'un produit bonus, on modifie la date d'effet
            if (idFamilleBonus1.equals(produit.getIdentifiantFamille())) {
                dateEffet.add(Calendar.YEAR, constanteNbAnneesBonus1);
            }
            if (idFamilleBonus2.equals(produit.getIdentifiantFamille())) {
                dateEffet.add(Calendar.YEAR, constanteNbAnneesBonus2);
            }
            assurePrincipal.setDateEffetTarification(dateEffet);
            produitSelecteur.setAssurePrincipal(assurePrincipal);

            // on ajoute les beneficiaires si code tarification du produit != adherent
            if (!idModeTarificationAdherent.equals(produit.getModeTarification().getIdentifiant())) {
                // Bugs 1646 et 1716 : tri Conjoint - Enfants
                final List<AssureSelecteurDto> listeBeneficiaires = new ArrayList<AssureSelecteurDto>();
                final List<AssureSelecteurDto> listeEnfants = new ArrayList<AssureSelecteurDto>();
                for (BeneficiaireDto beneficiaire : personnePrincipale.getListeBeneficiaires()) {
                    // Mapping
                    final AssureSelecteurDto beneficiaireDto = mapperDozerBean.map(beneficiaire, AssureSelecteurDto.class);
                    // Récupération du nom et du prénom de l'assuré
                    final PersonneSimpleDto beneficiaireSquare = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(beneficiaire.getEidPersonne());
                    beneficiaireDto.setNom(beneficiaireSquare.getNom());
                    beneficiaireDto.setPrenom(beneficiaireSquare.getPrenom());
                    // Si c'est un enfant : ajout à la liste des enfants
                    if (beneficiaire.getIdLienFamilial().equals(idLienFamilialEnfant)) {
                        listeEnfants.add(beneficiaireDto);
                    } else {
                        // Conjoint : ajout à la liste des bénéficiaires
                        listeBeneficiaires.add(beneficiaireDto);
                    }
                }
                // Ajout des enfants à la liste des bénéficiaires
                listeBeneficiaires.addAll(listeEnfants);

                produitSelecteur.setListeBeneficiaires(listeBeneficiaires);
                // on parcours la liste des beneficiaires
                for (AssureSelecteurDto beneficiaire : produitSelecteur.getListeBeneficiaires()) {
                    // on en profite pour initialiser la liste des criteres des beneficiaires
                    beneficiaire.setListeValeursCriteres(new ArrayList<ValeurCritereAssureSelecteurDto>());
                    // si il s'agit du produit principal, on selectionne le beneficiaire par défaut
                    if (produitPrincipal || !produitLieOptionnel) {
                        beneficiaire.setIsSelection(Boolean.TRUE);
                    }
                    // on initialise la date d'effet au 1er du mois suivant
                    final Calendar dateEffetBeneficiaire = DateUtil.getDatePremierMoisSuivant();
                    // si il s'agit d'un produit bonus, on modifie la date d'effet
                    if (idFamilleBonus1.equals(produit.getIdentifiantFamille())) {
                        dateEffetBeneficiaire.add(Calendar.YEAR, constanteNbAnneesBonus1);
                    }
                    if (idFamilleBonus2.equals(produit.getIdentifiantFamille())) {
                        dateEffetBeneficiaire.add(Calendar.YEAR, constanteNbAnneesBonus2);
                    }
                    beneficiaire.setDateEffetTarification(dateEffetBeneficiaire);
                }
            }

            // on recupere les contraintes du produit
            final ContrainteVenteDto contrainteVente = produitService.getContrainteVenteParProduit(produit.getIdentifiant());
            if (contrainteVente != null) {
                produitSelecteur.setContraintesVente((ContrainteVenteSelecteurDto) mapperDozerBean.map(contrainteVente, ContrainteVenteSelecteurDto.class));
            }

            // si il s'agit du produit principal, on selectionne par défaut
            if (produitPrincipal) {
                produitSelecteur.setIsSelection(Boolean.TRUE);
            }
            // on marque si il s'agit d'un produit actif ou aps
            produitSelecteur.setIsActif(produitLieActif);

            // on marque si le produit est ouvert à la vente ou non.
            produitSelecteur.setIsProduitOuvertVente(isProduitOuverVente);
        }

        // on recupere / met a jour les criteres du produit
        getCriteresProduitSelecteur(produitSelecteur, produit, personnePrincipale, mapLiensFamiliauxBenefs, isProduitOuverVente,
            isSelecteurProduitParProduitsAdherent);

        // si le produit n'existe pas deja
        if (!isExistingProduit) {
            // on ajoute le produit a la famille
            if (famille instanceof FamillePrincipaleSelecteurDto) {
                ((FamillePrincipaleSelecteurDto) famille).setProduitPrincipal(produitSelecteur);
            } else if (famille instanceof FamilleLieeSelecteurDto) {
                ((FamilleLieeSelecteurDto) famille).getListeProduitsLies().add(produitSelecteur);
            }
        }

    }

    /**
     * Transforme le produit principal en produit simulacre.
     */
    private void transformerProduitPrincipalEnSimulacre(FamillePrincipaleSelecteurDto famillePrincipale) {
        final ProduitSelecteurDto produitSelecteur = famillePrincipale.getProduitPrincipal();
        final Integer idProduitOrigine = produitSelecteur.getIdentifiant();
        // on ne fait la transformation que si le produit principal n'est pas deja un produit simulacre
        if (!idProduitOrigine.equals(CONSTANTE_IDENTIFIANT_SIMULACRE_PRODUIT_PRINCIPAL)) {
            final String libelleProduitOrigine = produitSelecteur.getLibelle();
            final Integer idCritereSimulacre = CONSTANTE_IDENTIFIANT_CRITERE_SIMULACRE_TYPE_PRINCIPAL;

            // on redefinit le produit principal
            produitSelecteur.setIdentifiant(CONSTANTE_IDENTIFIANT_SIMULACRE_PRODUIT_PRINCIPAL);
            produitSelecteur.setLibelle(famillePrincipale.getLibelle());

            // on cree un critere simulacre sur le type de garantie
            final CritereSelecteurDto critereSimulacre = new CritereSelecteurDto();
            critereSimulacre.setIdentifiant(idCritereSimulacre);
            critereSimulacre.setLibelle(CONSTANTE_LIBELLE_CRITERE_SIMULACRE_TYPE_PRINCIPAL);
            critereSimulacre.setTypeHtml(typeHtmlSelect);
            critereSimulacre.setVisible(Boolean.TRUE);
            critereSimulacre.setModifiable(!constanteIdModeTarificationFamille.equals(produitSelecteur.getModeTarification().getIdentifiant().intValue()));
            critereSimulacre.setModifiablePourAdherentPrincipal(Boolean.FALSE);
            critereSimulacre.setObligatoire(Boolean.TRUE);
            critereSimulacre.setAfficheCodeOuLib(Boolean.FALSE);
            critereSimulacre.setListeValeursCriteres(new ArrayList<ValeurCritereSelecteurDto>());
            // on ajoute le produit en tant que valeur de critere
            final ValeurCritereSelecteurDto valeurCritere = new ValeurCritereSelecteurDto(idProduitOrigine.toString(), libelleProduitOrigine);
            critereSimulacre.getListeValeursCriteres().add(valeurCritere);
            // on ajoute le critere a la liste des criteres
            produitSelecteur.getListeCriteres().add(critereSimulacre);

            // valeur de critere vide par défaut
            final String valeurCritereAssure = idProduitOrigine.toString();
            // on parcours les assurés (principal et beneficiaires) pour ajouter le critere
            produitSelecteur.getAssurePrincipal().getListeValeursCriteres().add(new ValeurCritereAssureSelecteurDto(idCritereSimulacre, valeurCritereAssure));
            if (produitSelecteur.getListeBeneficiaires() != null) {
                for (AssureSelecteurDto beneficiaire : produitSelecteur.getListeBeneficiaires()) {
                    beneficiaire.getListeValeursCriteres().add(new ValeurCritereAssureSelecteurDto(idCritereSimulacre, valeurCritereAssure));
                }
            }
        }
    }

    /**
     * Recupere les criteres du produit.
     */
    private void getCriteresProduitSelecteur(ProduitSelecteurDto produitSelecteur, final ProduitDto produitDto, final PersonneDto personnePrincipale,
        Map<String, Long> mapLiensFamiliauxBenefs, boolean isProduitOuverVente, boolean isSelecteurProduitParProduitsAdherent) {
        // on ne recupere les criteres que si ils n'ont pas deja été récupérés
        // (dans le cas ou on a plusieurs produits GO, le 1er aura deja récupéré les criteres qui sont les memes pour tous les produits GO)
        if (produitSelecteur.getListeCriteres() == null) {
            // on recupere les criteres du produit
            final CritereCriteresDto criteresCriteres = new CritereCriteresDto();
            criteresCriteres.setIdentifiantProduit(produitDto.getIdentifiant());
            criteresCriteres.setIdentifiantApplication(idApplicationGestcom);
            criteresCriteres.setChargerValeursPossibles(Boolean.TRUE);
            final List<CritereDto> listeCriteresProduit = produitService.getListeCriteresParProduit(criteresCriteres);
            produitSelecteur.setListeCriteres(new ArrayList<CritereSelecteurDto>());
            if (listeCriteresProduit != null) {
                // on parcours les criteres
                for (CritereDto critere : listeCriteresProduit) {
                    // si critere différent de ageMax et montant
                    if (!critere.getIdentifiant().equals(idCritereAgeMax) && !critere.getIdentifiant().equals(idCritereMontantMax)) {
                        // on ajoute le critere au produit (avec ses valeur criteres)
                        final CritereSelecteurDto critereSelecteur = (CritereSelecteurDto) mapperDozerBean.map(critere, CritereSelecteurDto.class);

                        if (critere.getIdentifiant().equals(idCritereGeneration) && !isSelecteurProduitParProduitsAdherent) {
                            final List<ValeurCritereSelecteurDto> listeValeursCriteres = new ArrayList<ValeurCritereSelecteurDto>();
                            for (ValeurCritereSelecteurDto valCrit : critereSelecteur.getListeValeursCriteres()) {
                                String annee = valCrit.getCodeCritere();
                                int anneei;
                                if (annee.length() > 4) {
                                    annee = annee.substring(0, 4);
                                    anneei = Integer.parseInt(annee) - 1;
                                } else {
                                    anneei = Integer.parseInt(annee);
                                }

                                if (isProduitOuverVente) {
                                    if (valeurCritereGeneration <= anneei) {
                                        listeValeursCriteres.add(valCrit);
                                    }
                                } else {
                                    if (valeurCritereGeneration > anneei) {
                                        listeValeursCriteres.add(valCrit);
                                    }
                                }
                            }
                            critereSelecteur.setListeValeursCriteres(listeValeursCriteres);
                        }

                        produitSelecteur.getListeCriteres().add(critereSelecteur);

                        // on parcours les assurés (principal et beneficiaires) pour ajouter le critere
                        produitSelecteur.getAssurePrincipal().getListeValeursCriteres().add(
                            initValeurCritereAssure(critere, produitSelecteur.getAssurePrincipal(), true, produitDto.getIdentifiant(), personnePrincipale,
                                mapLiensFamiliauxBenefs));
                        // criteres generations et mois non ajoutés sur les benefs
                        if (!critere.getIdentifiant().equals(idCritereGeneration) && !critere.getIdentifiant().equals(idCritereMois)) {
                            if (produitSelecteur.getListeBeneficiaires() != null) {
                                for (AssureSelecteurDto beneficiaire : produitSelecteur.getListeBeneficiaires()) {
                                    beneficiaire.getListeValeursCriteres().add(
                                        initValeurCritereAssure(critere, beneficiaire, false, produitDto.getIdentifiant(), personnePrincipale,
                                            mapLiensFamiliauxBenefs));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Initialise les valeurs de criteres pour un assuré.
     */
    private ValeurCritereAssureSelecteurDto initValeurCritereAssure(CritereDto critere, AssureSelecteurDto assure, boolean isAssurePrincipal,
        Integer idProduit, final PersonneDto personnePrincipale, Map<String, Long> mapLiensFamiliauxBenefs) {
        final Integer idCritere = critere.getIdentifiant();
        final ValeurCritereAssureSelecteurDto valeurCritereAssure = new ValeurCritereAssureSelecteurDto(idCritere);
        // si critere lien famille
        if (idCritere.equals(idCritereLienFamille)) {
            String lienFam = "";
            if (isAssurePrincipal) {
                lienFam = codeCompoAssurePrincipal;
            } else {
                lienFam = getLienFamilleCalculeBeneficiaire(assure, mapLiensFamiliauxBenefs);
            }
            valeurCritereAssure.setValeur(lienFam);
        }
        // si critere composition famille
        else if (idCritere.equals(idCritereAgeMin)) {
            final Integer ageCalcule = getAgeCalculePersonne(assure.getDateEffetTarification(), assure.getDateNaissance(), idProduit);
            valeurCritereAssure.setValeur(ageCalcule.toString());
        }
        // si critere composition famille
        else if (idCritere.equals(idCritereCompositionFamille)) {
            final String compoFam = getCompoFamilleCalculeeProspect(personnePrincipale);
            valeurCritereAssure.setValeur(compoFam);
        }
        // si critere zone tarifaire
        else if (idCritere.equals(idCritereZoneTarifaire)) {
            // on cree les criteres de recherche
            final ZonageCriteresDto criteres = new ZonageCriteresDto();
            // on recupere le departement du prospect en cours
            if (personnePrincipale != null && personnePrincipale.getAdressePrincipale() != null) {
                final IdentifiantLibelleDepartementCodeDto departement =
                    dimensionService.rechercherDepartementParIdCommune(personnePrincipale.getAdressePrincipale().getEidCommune());
                criteres.setCodeDepartement(departement.getCodeDepartement());
            }
            criteres.setDateEffet(assure.getDateEffetTarification());
            criteres.setIdentifiantProduit(idProduit);

            // Appel du service
            final List<ZonageDto> listeZonages = produitService.getListeZonages(criteres);
            if (listeZonages.size() == 1) {
                valeurCritereAssure.setValeur(listeZonages.get(0).getCodeZone().toString());
            }
        }
        // si critere generation
        else if (idCritere.equals(idCritereGeneration)) {
            // on détermine si le produit est ouvert à la vente
            if (produitService.isProduitOuvertVente(idProduit)) {
                valeurCritereAssure.setValeur(String.valueOf(valeurCritereGeneration));
            } else {
                valeurCritereAssure.setValeur(String.valueOf(assure.getDateEffetTarification().get(Calendar.YEAR)));
            }
        }
        // si critere generation
        else if (idCritere.equals(idCritereMois)) {
            valeurCritereAssure.setValeur(formateurMois.format(assure.getDateEffetTarification().getTime()));
        } else if (idCritere.equals(idCritereGarantieSouscrite)) {
            valeurCritereAssure.setValeur(critere.getForcageCodeCritere());
        }
        return valeurCritereAssure;
    }

    /**
     * Construit une PropositionLigneDevis à partir d'un objet SelecteurProduitDto pour sauvegarder la ligne de devis.
     * @param selecteurProduit l'objet selecteur
     * @return la ligne de devis.
     */
    public PropositionLigneDevis getPropositionParSelecteurProduit(SelecteurProduitDto selecteurProduit) {
        final RapportDto rapportErreurs = new RapportDto();
        final PropositionLigneDevis proposition = new PropositionLigneDevis();
        final List<LigneDevisDto> listeLignesDevisLiees = new ArrayList<LigneDevisDto>();

        // on met a jour l'id du devis
        proposition.setIdentifiantDevis(selecteurProduit.getIdentifiantDevis());

        // on recupere le produit principal
        final ProduitSelecteurDto produitPrincipal = selecteurProduit.getFamillePrincipale().getProduitPrincipal();

        // On remplit la compostion familiale dans le cas d'un produit evo5 banco ou evo5+ banco
        Integer idProduitPrincipal = produitPrincipal.getIdentifiant();
        String valeurCodeCompo = null;
        for (ValeurCritereAssureSelecteurDto valeur : produitPrincipal.getAssurePrincipal().getListeValeursCriteres()) {
            if (valeur.getIdentifiantCritere().equals(CONSTANTE_IDENTIFIANT_CRITERE_SIMULACRE_TYPE_PRINCIPAL)) {
                idProduitPrincipal = Integer.parseInt(valeur.getValeur());
                break;
            }
        }

        if (produitPrincipal.getModeTarification() == null) {
            // RECUPERATION CODE TARIFICATION SINON DIPSONIBLE
            final ProduitCriteresDto criteres = new ProduitCriteresDto();
            criteres.setIdentifiantProduit(idProduitPrincipal);
            final List<ProduitDto> listeProduits = produitService.getListeProduits(criteres);
            if (listeProduits == null || listeProduits.size() != 1) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IMPOSSIBLE_RECUPERER_PRODUIT, new String[] {String
                        .valueOf(idProduitPrincipal)}));
            }
            final ProduitDto produit = listeProduits.get(0);
            final IdentifiantLibelleDto modeTarification = mapperDozerBean.map(produit.getModeTarification(), IdentifiantLibelleDto.class);
            produitPrincipal.setModeTarification(modeTarification);
        }

        if (produitPrincipal.getIsSelection() && produitPrincipal.getModeTarification() != null
            && constanteIdModeTarificationFamille.equals(produitPrincipal.getModeTarification().getIdentifiant().intValue())) {
            String codeGs = "I";
            int nbsEnfant = 0;
            if (produitPrincipal.getListeBeneficiaires() != null && produitPrincipal.getListeBeneficiaires().size() > 0) {
                for (AssureSelecteurDto beneficiaire : produitPrincipal.getListeBeneficiaires()) {
                    if (beneficiaire.getIsSelection() != null && beneficiaire.getIsSelection().booleanValue()) {
                        if (beneficiaire.getListeValeursCriteres() != null && beneficiaire.getListeValeursCriteres().size() > 0) {
                            for (ValeurCritereAssureSelecteurDto valeurCritere : beneficiaire.getListeValeursCriteres()) {
                                if (idCritereLienFamille.equals(valeurCritere.getIdentifiantCritere())) {
                                    if (constanteLienFamilleConjoint.equals(valeurCritere.getValeur())) {
                                        codeGs = "C";
                                    } else if (constanteLienFamilleEnfant.equals(valeurCritere.getValeur())) {
                                        nbsEnfant++;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (nbsEnfant >= 5) {
                codeGs += "X";
            } else if (nbsEnfant != 0) {
                codeGs += String.valueOf(nbsEnfant);
            }
            valeurCodeCompo = tarificateurSquareMappingService.getCodeRoleParCompoFam(codeGs);
            for (ValeurCritereAssureSelecteurDto valeur : produitPrincipal.getAssurePrincipal().getListeValeursCriteres()) {
                if (idCritereCompositionFamille.equals(valeur.getIdentifiantCritere())) {
                    valeur.setValeur(valeurCodeCompo);
                    break;
                }
            }
        }

        String prefixProp = "famillePrincipale.produitPrincipal.";
        validerAssure(rapportErreurs, produitPrincipal.getAssurePrincipal(), prefixProp);
        // ON CREE LA LIGNE PRINCIPALE (assure principal du produit principal)
        proposition.setLigneDevis(creerLigneDevisPourAssure(produitPrincipal.getAssurePrincipal(), produitPrincipal, true, selecteurProduit.getEidAuteur()));
        proposition.getLigneDevis().setIdentifiant(selecteurProduit.getIdentifiantLigneDevisPrincipale());

        // ON PARCOURS LES BENEFICIAIRES DU PRODUIT PRINCIPAL POUR CREER LES LIGNES LIEES
        if (produitPrincipal.getListeBeneficiaires() != null) {
            for (AssureSelecteurDto beneficiaire : produitPrincipal.getListeBeneficiaires()) {
                // ON CREE UNE LIGNE LIEE SI LE BENEFICIAIRE DU PRODUIT PRINCIPAL EST SELECTIONNE
                if (beneficiaire.getIsSelection() != null && beneficiaire.getIsSelection().booleanValue()) {
                    if (valeurCodeCompo != null) {
                        for (ValeurCritereAssureSelecteurDto valeur : beneficiaire.getListeValeursCriteres()) {
                            if (idCritereCompositionFamille.equals(valeur.getIdentifiantCritere())) {
                                valeur.setValeur(valeurCodeCompo);
                                break;
                            }
                        }
                    }
                    prefixProp = "famillePrincipale.produitPrincipal.";
                    validerAssure(rapportErreurs, beneficiaire, prefixProp);
                    // on ajoute la ligne de devis liée a la liste
                    listeLignesDevisLiees.add(creerLigneDevisPourAssure(beneficiaire, produitPrincipal, false, selecteurProduit.getEidAuteur()));
                }
            }
        }

        // on parcours les familles liées
        for (FamilleLieeSelecteurDto familleLiee : selecteurProduit.getListeFamillesLiees()) {
            // on verifie si la famille est selectionnée
            if (familleLiee.getIsSelection() != null && familleLiee.getIsSelection().booleanValue()) {
                // on parcours les produits de la famille liée
                for (ProduitSelecteurDto produitLie : familleLiee.getListeProduitsLies()) {
                    // on verifie si le produit est selectionné
                    if (produitLie.getIsSelection() != null && produitLie.getIsSelection().booleanValue()) {
                        final AssureSelecteurDto assurePrincipal = produitLie.getAssurePrincipal();
                        // ON CREE UNE LIGNE LIEE SI L'ASSURE PRINCIPAL DU PRODUIT LIE EST SELECTIONNE
                        if (assurePrincipal.getIsSelection() != null && assurePrincipal.getIsSelection().booleanValue()) {
                            prefixProp = "familleLiee-" + familleLiee.getIdentifiant() + ".produitLie-" + produitLie.getIdentifiant() + ".";
                            validerAssure(rapportErreurs, assurePrincipal, prefixProp);
                            // on ajoute la ligne de devis liée a la liste
                            listeLignesDevisLiees.add(creerLigneDevisPourAssure(assurePrincipal, produitLie, true, selecteurProduit.getEidAuteur()));
                        }

                        if (produitLie.getListeBeneficiaires() != null) {
                            // ON PARCOURS LES BENEFICIAIRES DU PRODUIT LIE POUR CREER LES LIGNES LIEES
                            for (AssureSelecteurDto beneficiaire : produitLie.getListeBeneficiaires()) {
                                // ON CREE UNE LIGNE LIEE SI LE BENEFICIAIRE DU PRODUIT LIE EST SELECTIONNE
                                if (beneficiaire.getIsSelection() != null && beneficiaire.getIsSelection().booleanValue()) {
                                    prefixProp = "familleLiee-" + familleLiee.getIdentifiant() + ".produitLie-" + produitLie.getIdentifiant() + ".";
                                    validerAssure(rapportErreurs, beneficiaire, prefixProp);
                                    // on ajoute la ligne de devis liée a la liste
                                    listeLignesDevisLiees.add(creerLigneDevisPourAssure(beneficiaire, produitLie, false, selecteurProduit.getEidAuteur()));
                                }
                            }
                        }
                    }
                }
            }
        }
        // on ajoute les lignes de devis liées a la proposition
        proposition.getLigneDevis().setListeLignesDevisLiees(listeLignesDevisLiees);

        if (Boolean.TRUE.equals(rapportErreurs.getEnErreur())) {
            RapportUtil.logRapport(rapportErreurs, logger);
            throw new ControleIntegriteException(rapportErreurs);
        }

        return proposition;
    }

    private void validerAssure(final RapportDto rapport, AssureSelecteurDto assure, String prefixProp) {
        final String prefixPropAssure = new StringBuffer(prefixProp).append("assure-").append(assure.getIdentifiant()).append(".").toString();
        for (ValeurCritereAssureSelecteurDto valeur : assure.getListeValeursCriteres()) {
            if (valeur.getValeur() == null) {
                final StringBuffer prop = new StringBuffer(prefixPropAssure).append("idCritere-").append(valeur.getIdentifiantCritere());
                rapport.ajoutRapport(prop.toString(), messageSourceUtil.get(MessageKeyUtil.MESSAGE_RAPPORT_VALEUR_SELECTIONNER), true);
            }
        }
    }

    private LigneDevisDto creerLigneDevisPourAssure(AssureSelecteurDto assure, ProduitSelecteurDto produit, boolean assurePrincipal, Long eidAuteur) {
        // on construit la ligne de devis liée
        final LigneDevisDto ligneDevis = new LigneDevisDto();
        ligneDevis.setIdentifiantProduit(produit.getIdentifiant());
        ligneDevis.setDateEffet(assure.getDateEffetTarification());
        if (!assurePrincipal) {
            ligneDevis.setIdentifiantBeneficiaire(assure.getIdentifiant());
        }
        if (eidAuteur != null) {
            ligneDevis.setGestionnaire(new IdentifiantLibelleDto(eidAuteur));
        }
        // on recupere les valeurs de critere de l'assure
        final List<ValeurCritereLigneDevisDto> listeValeurCritereLigneDevisLiee = new ArrayList<ValeurCritereLigneDevisDto>();

        Integer idProduitRecupereeDansValeur = null;
        for (ValeurCritereAssureSelecteurDto valeur : assure.getListeValeursCriteres()) {
            // si critere n'est pas le simulacre type garantie produit GO ou simulacre produit principal
            if (!valeur.getIdentifiantCritere().equals(constanteIdCritereSimulacreTypeGo)
                && !valeur.getIdentifiantCritere().equals(CONSTANTE_IDENTIFIANT_CRITERE_SIMULACRE_TYPE_PRINCIPAL)) {
                final ValeurCritereLigneDevisDto valeurLigne = (ValeurCritereLigneDevisDto) mapperDozerBean.map(valeur, ValeurCritereLigneDevisDto.class);
                listeValeurCritereLigneDevisLiee.add(valeurLigne);
                // si critere age max, on le duplique en critere age min
                if (valeur.getIdentifiantCritere().equals(idCritereAgeMin)) {
                    final ValeurCritereLigneDevisDto valeurLigneAgeMax = new ValeurCritereLigneDevisDto();
                    valeurLigneAgeMax.setIdentifiantCritere(idCritereAgeMax);
                    valeurLigneAgeMax.setValeur(valeurLigne.getValeur());
                    listeValeurCritereLigneDevisLiee.add(valeurLigneAgeMax);
                }
                // si critere montant max, on le duplique en critere montant min
                else if (valeur.getIdentifiantCritere().equals(idCritereMontantMin)) {
                    final ValeurCritereLigneDevisDto valeurLigneMontantMax = new ValeurCritereLigneDevisDto();
                    valeurLigneMontantMax.setIdentifiantCritere(idCritereMontantMax);
                    valeurLigneMontantMax.setValeur(valeurLigne.getValeur());
                    listeValeurCritereLigneDevisLiee.add(valeurLigneMontantMax);
                }
            }
            // si critere est le simulacre du type garantie produit GO ou produit Perso
            else {
                // on ne recupere pas cette valeur critere, mais on redefinit l'id du produit de la ligne
                idProduitRecupereeDansValeur = Integer.parseInt(valeur.getValeur());
            }
        }
        // pour les benefs on rajoute les criteres Mois et Generation de l'assure principal
        if (!assurePrincipal) {
            // on recupere les criteres Mois et Generation
            for (ValeurCritereAssureSelecteurDto valeurCritereAssure : produit.getAssurePrincipal().getListeValeursCriteres()) {
                if (valeurCritereAssure.getIdentifiantCritere().equals(idCritereGeneration)) {
                    final ValeurCritereLigneDevisDto valeurLigneGeneration = new ValeurCritereLigneDevisDto();
                    valeurLigneGeneration.setIdentifiantCritere(idCritereGeneration);
                    valeurLigneGeneration.setValeur(valeurCritereAssure.getValeur());
                    listeValeurCritereLigneDevisLiee.add(valeurLigneGeneration);
                } else if (valeurCritereAssure.getIdentifiantCritere().equals(idCritereMois)) {
                    final ValeurCritereLigneDevisDto valeurLigneGeneration = new ValeurCritereLigneDevisDto();
                    valeurLigneGeneration.setIdentifiantCritere(idCritereMois);
                    valeurLigneGeneration.setValeur(valeurCritereAssure.getValeur());
                    listeValeurCritereLigneDevisLiee.add(valeurLigneGeneration);
                }
            }
        }
        ligneDevis.setListeValeurCritereLigneDevis(listeValeurCritereLigneDevisLiee);

        // si on rentre dans le cas du produit simulacre, on recupere l'id du produit dans la valeur du critere
        if (idProduitRecupereeDansValeur != null) {
            ligneDevis.setIdentifiantProduit(idProduitRecupereeDansValeur);
        } else {
            ligneDevis.setIdentifiantProduit(produit.getIdentifiant());
        }

        // Si on est dans le cas d'un produit dont le code compo est famille, on vérifie si on doit calculer ou non le tarif
        // Sinon le tarif sera à calculer dans tous les cas.
        final ProduitCriteresDto critere = new ProduitCriteresDto();
        critere.setIdentifiantProduit(ligneDevis.getIdentifiantProduit());
        final ProduitDto produitBase = produitService.getListeProduits(critere).get(0);
        if (produitBase.getModeTarification() != null && constanteIdModeTarificationFamille.equals(produitBase.getModeTarification().getIdentifiant())) {
            ligneDevis.setTarifACalculer(assurePrincipal);
        } else {
            ligneDevis.setTarifACalculer(true);
        }

        // on retourne la ligne de devis liée
        return ligneDevis;
    }

    /**
     * Remplit un SelecteurProduitDto à partir des informations du ligne de devis.
     * @param selecteurProduit l'objet selecteur
     * @param ligneDevis la ligne de devis
     * @param isSelecteurProduitParProduitsAdherent boolean pour savoir si on affiche les produits lies non actifs ou pas
     * @return le selecteurProduit.
     */
    public SelecteurProduitDto getSelecteurProduitParLigneDevis(SelecteurProduitDto selecteurProduit, LigneDevisDto ligneDevis,
        boolean isSelecteurProduitParProduitsAdherent) {
        // on charge l'id de la ligne
        selecteurProduit.setIdentifiantLigneDevisPrincipale(ligneDevis.getIdentifiant());

        if (selecteurProduit.getFamillePrincipale() != null && selecteurProduit.getFamillePrincipale().getProduitPrincipal() != null
            && selecteurProduit.getFamillePrincipale().getProduitPrincipal().getListeBeneficiaires() != null) {
            // par défaut on décoche la pré-selection des bénéficiaires sur le produit principal
            for (AssureSelecteurDto beneficiaireProduitPrincipal : selecteurProduit.getFamillePrincipale().getProduitPrincipal().getListeBeneficiaires()) {
                beneficiaireProduitPrincipal.setIsSelection(false);
            }
        }

        // on déselectionne et déverouille les produits liés obligatoires du produit principal si on est dans le cas où l'on affiche les produits
        // liés non actifs. cela permet de ne pas cocher obligatoire les nouveaux produits exo et assistance si ce sont les anciens qui sont liés au devis
        if (isSelecteurProduitParProduitsAdherent && selecteurProduit.getListeFamillesLiees() != null) {
            // on parcours les familles liées
            for (FamilleLieeSelecteurDto familleLiee : selecteurProduit.getListeFamillesLiees()) {
                if (familleLiee.getIsSelection() != null && familleLiee.getIsSelection().booleanValue()) {
                    familleLiee.setIsSelection(false);
                    familleLiee.setOptionnel(true);
                    // on parcours les produits liés
                    for (ProduitSelecteurDto produitLie : familleLiee.getListeProduitsLies()) {
                        produitLie.setIsSelection(false);
                    }
                }
            }
        }

        // on met a jour les infos du produit principal pour l'assure principal
        searchInfosSelecteurFromLigneDevis(selecteurProduit, ligneDevis.getIdentifiantProduit(), null, ligneDevis, isSelecteurProduitParProduitsAdherent);

        return selecteurProduit;
    }

    /**
     * Recherche la famille, le produit et l'assure correspondant à la ligne devis.
     */
    private void searchInfosSelecteurFromLigneDevis(SelecteurProduitDto selecteurProduit, Integer idProduit, String libelleProduit, LigneDevisDto ligneDevis,
        boolean isSelecteurProduitParProduitsAdherent) {

        // on verifie si il s'agit du produit principal
        final FamillePrincipaleSelecteurDto famillePrincipale = selecteurProduit.getFamillePrincipale();
        final ProduitSelecteurDto produitPrincipal = famillePrincipale.getProduitPrincipal();

        // si il s'agit du produit principal
        if (idProduit != null && produitPrincipal.getIdentifiant().equals(idProduit)) {
            // on recupere l'assure concerné
            final AssureSelecteurDto assure = searchAssureConcerne(produitPrincipal, ligneDevis);
            updateInfosSelecteurFromLignesDevis(famillePrincipale, produitPrincipal, assure, ligneDevis, isSelecteurProduitParProduitsAdherent);
            return;
        }
        // sinon si il s'agit d'un produit lié
        else {
            // on parcours d'abord les familles liées
            for (FamilleLieeSelecteurDto familleLiee : selecteurProduit.getListeFamillesLiees()) {
                // on parcours les produits liés de la famille
                for (ProduitSelecteurDto produitLie : familleLiee.getListeProduitsLies()) {
                    // si c'est le produit recherché
                    if ((idProduit != null && produitLie.getIdentifiant().equals(idProduit))
                        || (libelleProduit != null && produitLie.getLibelle().equals(libelleProduit))) {
                        // on recupere l'assure concerné
                        final AssureSelecteurDto assure = searchAssureConcerne(produitLie, ligneDevis);
                        updateInfosSelecteurFromLignesDevis(familleLiee, produitLie, assure, ligneDevis, isSelecteurProduitParProduitsAdherent);
                        return;
                    }
                }
            }
            // si le produit concerné n'a toujours pas été trouvé : on verifie si le produit principal est un produit simulacre
            if (produitPrincipal.getIdentifiant().equals(CONSTANTE_IDENTIFIANT_SIMULACRE_PRODUIT_PRINCIPAL)) {
                // on recupere le produit pour verifier qu'il s'agit bien de la même famille que le produit principal simulacre
                // cela evite que des produits qui sont dans les lignes liées du devis mais qui n'existe pas dans le selecteur produit
                // soit assimilé comme des produits simulacres
                final ProduitCriteresDto criteresProduit = new ProduitCriteresDto();
                criteresProduit.setIdentifiantProduit(idProduit);
                final List<ProduitDto> listeProduits = produitService.getListeProduits(criteresProduit);
                if (listeProduits == null || listeProduits.size() != 1) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IMPOSSIBLE_RECUPERER_PRODUIT));
                }
                final ProduitDto produitRecupere = listeProduits.get(0);
                // on verifie si il s'agit de la même famille
                if (produitRecupere.getIdentifiantFamille().equals(famillePrincipale.getIdentifiant())) {
                    // on recupere l'assure concerné
                    final AssureSelecteurDto assure = searchAssureConcerne(produitPrincipal, ligneDevis);
                    updateInfosSelecteurFromLignesDevis(famillePrincipale, produitPrincipal, assure, ligneDevis, isSelecteurProduitParProduitsAdherent);
                    return;
                }
            }
        }
    }

    /**
     * Recherche l'assuré concerné dans le produit.
     */
    private AssureSelecteurDto searchAssureConcerne(ProduitSelecteurDto produit, LigneDevisDto ligneDevis) {
        // si c'est pour l'assure principal
        if (ligneDevis.getEidBeneficiaire() == null) {
            return produit.getAssurePrincipal();
        }
        // si c'est pour un beneficiaire
        else if (ligneDevis.getEidBeneficiaire() != null) {
            // on recupere le beneficiaire
            for (AssureSelecteurDto beneficiaire : produit.getListeBeneficiaires()) {
                // si c'est le beneficiaire recherché
                if (beneficiaire.getEidPersonne().equals(ligneDevis.getEidBeneficiaire())) {
                    return beneficiaire;
                }
            }
        }
        return null;
    }

    /**
     * Met à jour les informations de la famille, du produit et de l'assure.
     */
    private void updateInfosSelecteurFromLignesDevis(FamilleSelecteurDto famille, ProduitSelecteurDto produitSelecteur, AssureSelecteurDto assure,
        LigneDevisDto ligneDevis, boolean isSelecteurProduitParProduitsAdherent) {
        // on ne modifie pas les dates d'effet si on vient d'une génération de proposition pour adherent
        // car elles ont deja été initialisées correctement lors de la création du selecteur pour le produit
        if (!isSelecteurProduitParProduitsAdherent) {
            assure.setDateEffetTarification(ligneDevis.getDateEffet());
        }
        // on selectionne la famille, le produit et l'assure d'un produit bonus si la date d'effet est antérieur au 1er du mois suivant
        if ((famille.getIdentifiant().equals(idFamilleBonus1) || famille.getIdentifiant().equals(idFamilleBonus2))
            && assure.getDateEffetTarification().before(DateUtil.getDatePremierMoisSuivant())) {
            famille.setIsSelection(Boolean.TRUE);
            produitSelecteur.setIsSelection(Boolean.TRUE);
            assure.setIsSelection(Boolean.TRUE);
        }
        // ainsi que les produits non bonus
        else if (!famille.getIdentifiant().equals(idFamilleBonus1) && !famille.getIdentifiant().equals(idFamilleBonus2)) {
            famille.setIsSelection(Boolean.TRUE);
            produitSelecteur.setIsSelection(Boolean.TRUE);
            assure.setIsSelection(Boolean.TRUE);
        }
        // on met à jour les valeurs de criteres suivant celles de la ligne devis
        for (ValeurCritereAssureSelecteurDto valeur : assure.getListeValeursCriteres()) {
            // si il s'agit d'un produit GO ou Perso, on met comme valeur l'id du produit
            if (valeur.getIdentifiantCritere().equals(constanteIdCritereSimulacreTypeGo)
                || valeur.getIdentifiantCritere().equals(CONSTANTE_IDENTIFIANT_CRITERE_SIMULACRE_TYPE_PRINCIPAL)) {
                // on ne tient pas compte des produits Bonus
                valeur.setValeur(ligneDevis.getIdentifiantProduit().toString());
            }
            // pour les autres produits, on recupere dans la ligne devis
            else {
                for (ValeurCritereLigneDevisDto valeurLigne : ligneDevis.getListeValeurCritereLigneDevis()) {
                    if (valeur.getIdentifiantCritere().equals(valeurLigne.getIdentifiantCritere())) {
                        // ON VERIFIE QUE LA VALEUR DE LA LIGNE DEVIS EST BIEN UNE VALEUR POSSIBLE POUR CE CRITERE
                        // on recupere la liste des criteres pour le produit
                        final List<CritereSelecteurDto> listeCriteresProduit = produitSelecteur.getListeCriteres();
                        if (listeCriteresProduit == null) {
                            return;
                        }
                        for (CritereSelecteurDto critereProduit : listeCriteresProduit) {
                            // on recupere la liste des valeurs possibles du critere pour le produit
                            if (critereProduit.getIdentifiant().equals(valeur.getIdentifiantCritere())) {
                                final List<ValeurCritereSelecteurDto> listeValeursPossiblesCriteresProduit = critereProduit.getListeValeursCriteres();
                                // si des valeurs possibles existent
                                if (listeValeursPossiblesCriteresProduit != null && listeValeursPossiblesCriteresProduit.size() > 0) {
                                    // on verifie si la valeur fait parti de la liste des valeurs possibles
                                    for (ValeurCritereSelecteurDto valeurCritereProduit : listeValeursPossiblesCriteresProduit) {
                                        // Bug 4054 : CritereDao de la base produit formatte les longs en supprimant les 0 pour l'affichage..
                                        // ce qui a pour effet de rendre le equals inopérant ici... Mise en place du même traitement
                                        if (valeurLigne.getValeur() != null) {
                                            valeurLigne.setValeur(valeurLigne.getValeur().replaceAll("\\.0$", ""));
                                        }
                                        if (valeurCritereProduit.getCodeCritere().equals(valeurLigne.getValeur())) {
                                            // on met à jour la valeur de la ligne
                                            valeur.setValeur(valeurLigne.getValeur());
                                            // sinon on ne touche à rien et on laisse la valeur d'origine
                                        }
                                    }
                                }
                                // si aucune valeurs possibles existent : on laisse la valeur
                                else {
                                    // on met à jour la valeur de la ligne
                                    valeur.setValeur(valeurLigne.getValeur());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Recupere les informations sur les produits bonus de l'adherent.
     */
    private void recupererInfosProduitsBonus(FamilleLieeSelecteurDto familleBonus1, FamilleLieeSelecteurDto familleBonus2,
    final PersonneDto personnePrincipale) {

        // Récupération des informations produits pour un assuré.
        final CriteresInfosProduitsDto criteres = new CriteresInfosProduitsDto();
        criteres.setIdPersonne(personnePrincipale.getEidPersonne());
        criteres.setHorsProduitsBonus(false);
        final List<InfosProduitDto> listInfosProduits = contratService.getInfosProduits(criteres);

        // si l'adherent à des produits bonus
        if (listInfosProduits != null && listInfosProduits.size() > 0) {

            for (InfosProduitDto infosProduitsDto : listInfosProduits) {
                if (!adherentMappingService.getIdFamilleGarantieBonus().equals(infosProduitsDto.getIdFamilleGarantie())) {
                    continue;
                }
                // Récupération du produit correspondant aux critères.
                final ProduitCriteresDto produitCriteresDto = new ProduitCriteresDto();
                produitCriteresDto.setGarantieAia(infosProduitsDto.getGarantieAia());
                produitCriteresDto.setProduitAia(infosProduitsDto.getProduitAia());
                final List<ProduitDto> listeProduits = produitService.getListeProduits(produitCriteresDto);
                if (listeProduits == null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_AUCUN_PRODUIT_CORRESPONDANT,
                    		new String[] {String.valueOf(infosProduitsDto.getGarantieAia()), String.valueOf(infosProduitsDto.getProduitAia())}));
                }
                if (listeProduits.size() == 0 || listeProduits.size() > 1) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_IMPOSSIBLE_RECUPERER_PRODUIT_BONUS,
                    		new String[] {String.valueOf(infosProduitsDto.getGarantieAia()),
                    					  String.valueOf(infosProduitsDto.getProduitAia()), String.valueOf(listeProduits.size())}));
                }
                final ProduitDto produit = listeProduits.get(0);
                String nom = null;
                String prenom = null;
                if (infosProduitsDto.getIdPersonne().equals(personnePrincipale.getEidPersonne())) {
                    nom = personnePrincipale.getNom();
                    prenom = personnePrincipale.getPrenom();
                } else {
                    for (BeneficiaireDto benef : personnePrincipale.getListeBeneficiaires()) {
                        if (benef.getEidPersonne().equals(infosProduitsDto.getIdPersonne())) {
                            nom = benef.getNom();
                            prenom = benef.getPrenom();
                            break;
                        }
                    }
                }
                if (nom == null && prenom == null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_ABSCENCE_BENEFICIAIRE_PRODUIT_CONTRAT));
                }
                // si le produit est un bonus1
                if (produit.getIdentifiantFamille().equals(idFamilleBonus1)) {
                    // on met à jour la date de bonus
                    mettreAJourDateEffetBonus(familleBonus1, nom, prenom, infosProduitsDto.getDateEffet());
                }
                // si il s'agit d'un bonus2
                else if (produit.getIdentifiantFamille().equals(idFamilleBonus2)) {
                    // on met à jour la date de bonus
                    mettreAJourDateEffetBonus(familleBonus2, nom, prenom, infosProduitsDto.getDateEffet());
                }
            }
        }
    }

    /**
     * Met à jour la date d'effet d'un produit bonus pour un bénéficiaire à partir de la famille.
     */
    private void mettreAJourDateEffetBonus(FamilleLieeSelecteurDto famille, String nom, String prenom, Calendar dateEffet) {
        // la famille bonus ne doit contenir qu'un seul produit
        final ProduitSelecteurDto produitBonus = famille.getListeProduitsLies().get(0);
        if (produitBonus.getAssurePrincipal().getNom().equals(nom) && produitBonus.getAssurePrincipal().getPrenom().equals(prenom)) {
            // on met à jour la date d'effet
            produitBonus.getAssurePrincipal().setDateEffetTarification(dateEffet);
            // on bloque la modification de la date d'effet en mode automatique
            produitBonus.getAssurePrincipal().setAllowChangeWithAutoDateEffet(false);
            // on selectionne que si date d'effet est inférieure à date du jour
            if (dateEffet.before(DateUtil.getDatePremierMoisSuivant())) {
                famille.setIsSelection(true);
                produitBonus.setIsSelection(true);
                produitBonus.getAssurePrincipal().setIsSelection(true);
            }
        }
        if (produitBonus.getListeBeneficiaires() != null) {
            // on recupere le beneficiaire concerné via le nom et prenom
            for (AssureSelecteurDto beneficiaire : produitBonus.getListeBeneficiaires()) {
                if (beneficiaire.getNom().equals(nom) && beneficiaire.getPrenom().equals(prenom)) {
                    // on met à jour la date d'effet
                    beneficiaire.setDateEffetTarification(dateEffet);
                    // on bloque la modification de la date d'effet en mode automatique
                    beneficiaire.setAllowChangeWithAutoDateEffet(false);
                    // on selectionne que si date d'effet est inférieure à date du jour
                    if (dateEffet.before(DateUtil.getDatePremierMoisSuivant())) {
                        famille.setIsSelection(true);
                        produitBonus.setIsSelection(true);
                        beneficiaire.setIsSelection(true);
                    }
                }
            }
        }
    }

    /**
     * Recupere le lien famille d'un beneficiaire.
     */
    private String getLienFamilleCalculeBeneficiaire(AssureSelecteurDto benef, Map<String, Long> mapLiensFamiliauxBenefs) {
        // on recupere dans la map suivant {nom}{prenom}{datenaissance} car id peut etre null si nouvelle famille
        final String cle = benef.getNom() + benef.getPrenom() + sdfDateConcat.format(benef.getDateNaissance().getTime());
        final Long idLienFamBenef = mapLiensFamiliauxBenefs.get(cle);
        if (idLienFamBenef.equals(idLienFamilialConjoint)) {
            return codeCompoConjoint;
        } else if (idLienFamBenef.equals(idLienFamilialEnfant)) {
            return codeCompoEnfant;
        }
        return null;
    }

    /**
     * Calcul de l'age millesimé ou calendaire d'une personne en fonction de la date du calcul et du produit.
     * @param dateCalcul la date du calcul
     * @param dateNaissancePersonne la date de naissance de la personne
     * @param idProduit l'identifiant du produit
     * @return l'age
     */
    private Integer getAgeCalculePersonne(Calendar dateCalcul, Calendar dateNaissancePersonne, Integer idProduit) {
        // TODO ne pas calculer à chaque fois : le faire en début de traitement et le stocker
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

    /** Calcul de la composition familiale de la personne principale. */
    private String getCompoFamilleCalculeeProspect(PersonneDto personnePrincipale) {
        // TODO ne pas calculer à chaque fois : le faire en début de traitement et le stocker
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_CALCUL_COMPOSITION_FAMILIALE_PROSPECT,
        		new String[] {String.valueOf(personnePrincipale.getId()), personnePrincipale.getNom(), personnePrincipale.getPrenom()}));
        // TODO sortir sous forme de constante de mapping (existante dans tarificateur produit ?)
        String codeFam = "I";
        int nbsEnfant = 0;
        for (BeneficiaireDto benef : personnePrincipale.getListeBeneficiaires()) {
            if (benef.getIdLienFamilial().equals(idLienFamilialConjoint)) {
                codeFam = "C";
            } else if (benef.getIdLienFamilial().equals(idLienFamilialEnfant)) {
                nbsEnfant++;
            }
        }
        if (nbsEnfant >= 5) {
            codeFam += "X";
        } else if (nbsEnfant != 0) {
            codeFam += String.valueOf(nbsEnfant);
        }
        return tarificateurSquareMappingService.getCodeRoleParCompoFam(codeFam);
    }

}
