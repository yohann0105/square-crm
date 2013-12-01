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
package com.square.composant.tarificateur.square.server.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.gwt.module.client.exception.BusinessExceptionGwt;

import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.age.AgeModel;
import com.square.composant.tarificateur.square.client.model.age.DateCalculAgeModel;
import com.square.composant.tarificateur.square.client.model.opportunite.InfosOpportuniteModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.ligne.LigneDevisModel;
import com.square.composant.tarificateur.square.client.model.personne.PersonneModel;
import com.square.composant.tarificateur.square.client.model.selecteur.AssureSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.CritereSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.FamilleLieeSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.ProduitSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.SelecteurProduitModel;
import com.square.composant.tarificateur.square.client.model.selecteur.ValeurCritereAssureSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.ValeurCritereSelecteurModel;
import com.square.composant.tarificateur.square.client.service.SelecteurProduitServiceGwt;
import com.square.composant.tarificateur.square.server.service.dto.DateCalculAgeDto;
import com.square.composant.tarificateur.square.server.service.util.CalculAgeUtil;
import com.square.tarificateur.noyau.dto.InfosOpportuniteDto;
import com.square.tarificateur.noyau.dto.devis.LigneDevisDto;
import com.square.tarificateur.noyau.dto.personne.PersonneTarificateurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.AssureSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.CritereSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.FamilleLieeSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.ProduitSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.SelecteurProduitDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.ValeurCritereAssureSelecteurDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.ValeurCritereSelecteurDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurService;
import com.square.user.core.dto.UtilisateurDto;
import com.square.user.core.service.interfaces.UtilisateurService;

/**
 * Implémentation serveur des services GWT pour les devis.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class SelecteurProduitServiceGwtImpl implements SelecteurProduitServiceGwt {

    private TarificateurService tarificateurService;

    private MapperDozerBean mapperDozerBean;

    private UtilisateurService utilisateurService;

    /** Expression régulière d'une date. */
    private static final String REGEXP_DATE = "[0-9]{2}/[0-9]{2}/[0-9]{4}";

    @Override
    public LigneDevisModel addLigneDevisParSelecteurProduit(SelecteurProduitModel selecteurProduit) {
        final SelecteurProduitDto selecteurProduitDto = mapperSelecteurProduitModel(selecteurProduit);
        selecteurProduitDto.setEidAuteur(getIdUtilisateurConnecte(selecteurProduit.getLoginUtilisateurConnecte()));
        final LigneDevisDto ligneDevisDto = tarificateurService.addLigneDevisParSelecteurProduit(selecteurProduitDto);
        return mapperDozerBean.map(ligneDevisDto, LigneDevisModel.class);
    }

    @Override
    public LigneDevisModel addLigneDevisParSelecteurProduit(SelecteurProduitModel selecteurProduit, InfosOpportuniteModel infosOpportunite) {
        final SelecteurProduitDto selecteurProduitDto = mapperSelecteurProduitModel(selecteurProduit);
        selecteurProduitDto.setEidAuteur(getIdUtilisateurConnecte(selecteurProduit.getLoginUtilisateurConnecte()));
        final InfosOpportuniteDto infosOpportuniteDto = mapperDozerBean.map(infosOpportunite, InfosOpportuniteDto.class);
        final LigneDevisDto ligneDevisDto = tarificateurService.addLigneDevisParSelecteurProduit(selecteurProduitDto, infosOpportuniteDto);
        return mapperDozerBean.map(ligneDevisDto, LigneDevisModel.class);
    }

    @Override
    public SelecteurProduitModel getSelecteurParProduit(Integer idProduit, Long idDevis, final PersonneModel nouvellePersonnePrincipale) {
        final SelecteurProduitDto selecteurProduitDto =
            tarificateurService.getSelecteurParProduit(idProduit, idDevis,
                (PersonneTarificateurDto) mapperDozerBean.map(nouvellePersonnePrincipale, PersonneTarificateurDto.class));
        return mapperSelecteurProduitDto(selecteurProduitDto);
    }

    @Override
    public SelecteurProduitModel getSelecteurProduitParLigneDevis(Long idLigneDevis, Long idDevis) {
        final SelecteurProduitDto selecteurProduitDto = tarificateurService.getSelecteurProduitParLigneDevis(idLigneDevis, idDevis);
        return mapperSelecteurProduitDto(selecteurProduitDto);
    }

    @Override
    public SelecteurProduitModel getSelecteurProduitParProduitsAdherent(Long idDevis, String produitAia, String garantieAia,
        final PersonneModel nouvellePersonnePrincipale) {
        final PersonneTarificateurDto nouvellePersonneDto = mapperDozerBean.map(nouvellePersonnePrincipale, PersonneTarificateurDto.class);
        final SelecteurProduitDto selecteurProduitDto =
            tarificateurService.getSelecteurProduitParProduitsAdherent(idDevis, produitAia, garantieAia, nouvellePersonneDto);
        return mapperSelecteurProduitDto(selecteurProduitDto);
    }

    @Override
    public LigneDevisModel updateLigneDevisParSelecteurProduit(SelecteurProduitModel selecteurProduit) {
        final SelecteurProduitDto selecteurProduitDto = mapperSelecteurProduitModel(selecteurProduit);
        selecteurProduitDto.setEidAuteur(getIdUtilisateurConnecte(selecteurProduit.getLoginUtilisateurConnecte()));
        final LigneDevisDto ligneDevisDto = tarificateurService.updateLigneDevisParSelecteurProduit(selecteurProduitDto);
        return mapperDozerBean.map(ligneDevisDto, LigneDevisModel.class);
    }

    /**
     * Mappe un selecteur produit Model en Dto.
     */
    private SelecteurProduitDto mapperSelecteurProduitModel(SelecteurProduitModel selecteurProduitModel) {
        // on mappe le selecteur produit
        final SelecteurProduitDto selecteurProduitDto = mapperDozerBean.map(selecteurProduitModel, SelecteurProduitDto.class);

        final ProduitSelecteurDto produitPrincipalDto = selecteurProduitDto.getFamillePrincipale().getProduitPrincipal();
        final ProduitSelecteurModel produitPrincipalModel = selecteurProduitModel.getFamillePrincipale().getProduitPrincipal();
        final List<FamilleLieeSelecteurModel> listeFamillesLieesModel = selecteurProduitModel.getListeFamillesLiees();

        // on mappe le produit principal
        mapperProduitModel(produitPrincipalModel, produitPrincipalDto);

        // on mappe la liste des familles liees
        if (listeFamillesLieesModel != null) {
            selecteurProduitDto.setListeFamillesLiees(new ArrayList<FamilleLieeSelecteurDto>());

            // on recupere les familles liées du selecteur
            for (FamilleLieeSelecteurModel familleLieeSelecteurModel : listeFamillesLieesModel) {
                final FamilleLieeSelecteurDto familleLieeSelecteurDto = mapperDozerBean.map(familleLieeSelecteurModel, FamilleLieeSelecteurDto.class);
                familleLieeSelecteurDto.setListeProduitsLies(new ArrayList<ProduitSelecteurDto>());
                selecteurProduitDto.getListeFamillesLiees().add(familleLieeSelecteurDto);

                // on recupere produits liées de la famille
                final List<ProduitSelecteurModel> listeProduitsLiesModel = familleLieeSelecteurModel.getListeProduitsLies();
                if (listeProduitsLiesModel != null) {
                    for (ProduitSelecteurModel produitSelecteurModel : listeProduitsLiesModel) {
                        // on mappe le produit et on l'ajoute à la liste
                        final ProduitSelecteurDto produitSelecteurDto = mapperDozerBean.map(produitSelecteurModel, ProduitSelecteurDto.class);
                        mapperProduitModel(produitSelecteurModel, produitSelecteurDto);
                        familleLieeSelecteurDto.getListeProduitsLies().add(produitSelecteurDto);
                    }
                }
            }
        }

        return selecteurProduitDto;
    }

    /**
     * Mappe un selecteur produit Dto en Model.
     */
    private SelecteurProduitModel mapperSelecteurProduitDto(SelecteurProduitDto selecteurProduitDto) {
        // on mappe le selecteur produit
        final SelecteurProduitModel selecteurProduitModel = mapperDozerBean.map(selecteurProduitDto, SelecteurProduitModel.class);

        final ProduitSelecteurModel produitPrincipalModel = selecteurProduitModel.getFamillePrincipale().getProduitPrincipal();
        final ProduitSelecteurDto produitPrincipalDto = selecteurProduitDto.getFamillePrincipale().getProduitPrincipal();
        final List<FamilleLieeSelecteurDto> listeFamillesLieesDto = selecteurProduitDto.getListeFamillesLiees();

        // on mappe le produit principal
        mapperProduitDto(produitPrincipalDto, produitPrincipalModel);

        // on mappe la liste des familles liees
        if (listeFamillesLieesDto != null) {
            selecteurProduitModel.setListeFamillesLiees(new ArrayList<FamilleLieeSelecteurModel>());

            // on recupere les familles liées du selecteur
            for (FamilleLieeSelecteurDto familleLieeSelecteurDto : listeFamillesLieesDto) {
                final FamilleLieeSelecteurModel familleLieeSelecteurModel = mapperDozerBean.map(familleLieeSelecteurDto, FamilleLieeSelecteurModel.class);
                familleLieeSelecteurModel.setListeProduitsLies(new ArrayList<ProduitSelecteurModel>());
                selecteurProduitModel.getListeFamillesLiees().add(familleLieeSelecteurModel);

                // on recupere produits liées de la famille
                final List<ProduitSelecteurDto> listeProduitsLiesDto = familleLieeSelecteurDto.getListeProduitsLies();
                if (listeProduitsLiesDto != null) {
                    for (ProduitSelecteurDto produitSelecteurDto : listeProduitsLiesDto) {
                        // on mappe le produit et on l'ajoute à la liste
                        final ProduitSelecteurModel produitSelecteurModel = mapperDozerBean.map(produitSelecteurDto, ProduitSelecteurModel.class);
                        mapperProduitDto(produitSelecteurDto, produitSelecteurModel);
                        familleLieeSelecteurModel.getListeProduitsLies().add(produitSelecteurModel);
                    }
                }
            }
        }

        // Ajout de la map des âges
        selecteurProduitModel.setMapAges(creerMapAges(selecteurProduitDto));

        return selecteurProduitModel;
    }

    /**
     * Mappe en produitSelecteurModel en produitSelecteurDto.
     */
    private void mapperProduitDto(ProduitSelecteurDto produitSelecteurDto, ProduitSelecteurModel produitSelecteurModel) {
        final List<CritereSelecteurDto> listeCriteresDto = produitSelecteurDto.getListeCriteres();
        final AssureSelecteurModel assureSelecteurGwt = produitSelecteurModel.getAssurePrincipal();
        final AssureSelecteurDto assureSelecteurDto = produitSelecteurDto.getAssurePrincipal();
        final List<AssureSelecteurDto> listeBeneficiairesDto = produitSelecteurDto.getListeBeneficiaires();

        // on mappe les criteres du produit principal
        if (listeCriteresDto != null) {
            produitSelecteurModel.setListeCriteres(new ArrayList<CritereSelecteurModel>());
            // on recupere les criteres du produit principal
            for (CritereSelecteurDto critereSelecteurDto : listeCriteresDto) {
                final CritereSelecteurModel critereSelecteurModel = mapperDozerBean.map(critereSelecteurDto, CritereSelecteurModel.class);
                produitSelecteurModel.getListeCriteres().add(critereSelecteurModel);
                if (critereSelecteurDto.getListeValeursCriteres() != null) {
                    // on recupere sa liste de valeurs criteres
                    final List<ValeurCritereSelecteurModel> listeValeursCriteres =
                        mapperDozerBean.mapList(critereSelecteurDto.getListeValeursCriteres(), ValeurCritereSelecteurModel.class);
                    critereSelecteurModel.setListeValeursCriteres(listeValeursCriteres);
                }
            }
        }

        if (assureSelecteurDto.getListeValeursCriteres() != null) {
            // on mappe la liste de valeurs de l'assure principal du produit principal
            final List<ValeurCritereAssureSelecteurModel> listeValeurs =
                mapperDozerBean.mapList(assureSelecteurDto.getListeValeursCriteres(), ValeurCritereAssureSelecteurModel.class);
            assureSelecteurGwt.setListeValeursCriteres(listeValeurs);
        }

        // on mappe la liste des beneficiaires du produit principal
        if (listeBeneficiairesDto != null) {
            produitSelecteurModel.setListeBeneficiaires(new ArrayList<AssureSelecteurModel>());
            // on recupere les beneficiaires du produit principal
            for (AssureSelecteurDto beneficiaireDto : listeBeneficiairesDto) {
                final AssureSelecteurModel beneficiaireModel = mapperDozerBean.map(beneficiaireDto, AssureSelecteurModel.class);
                produitSelecteurModel.getListeBeneficiaires().add(beneficiaireModel);
                if (beneficiaireDto.getListeValeursCriteres() != null) {
                    // on recupere sa liste de valeurs criteres
                    final List<ValeurCritereAssureSelecteurModel> listeValeurs =
                        mapperDozerBean.mapList(beneficiaireDto.getListeValeursCriteres(), ValeurCritereAssureSelecteurModel.class);
                    beneficiaireModel.setListeValeursCriteres(listeValeurs);
                }
            }
        }

        // Mapping du champ permettant de savoir si le produit est ouvert à la vente
        produitSelecteurModel.setIsProduitOuvertVente(produitSelecteurDto.getIsProduitOuvertVente());
    }

    /**
     * Mappe en produitSelecteurModel en produitSelecteurDto.
     */
    private void mapperProduitModel(ProduitSelecteurModel produitSelecteurModel, ProduitSelecteurDto produitSelecteurDto) {
        final List<CritereSelecteurModel> listeCriteresModel = produitSelecteurModel.getListeCriteres();
        final AssureSelecteurDto assureSelecteurDto = produitSelecteurDto.getAssurePrincipal();
        final AssureSelecteurModel assureSelecteurModel = produitSelecteurModel.getAssurePrincipal();
        final List<AssureSelecteurModel> listeBeneficiairesModel = produitSelecteurModel.getListeBeneficiaires();

        // on mappe les criteres du produit principal
        if (listeCriteresModel != null) {
            produitSelecteurDto.setListeCriteres(new ArrayList<CritereSelecteurDto>());
            // on recupere les criteres du produit principal
            for (CritereSelecteurModel critereSelecteurModel : listeCriteresModel) {
                final CritereSelecteurDto critereSelecteurDto = mapperDozerBean.map(critereSelecteurModel, CritereSelecteurDto.class);
                produitSelecteurDto.getListeCriteres().add(critereSelecteurDto);
                if (critereSelecteurModel.getListeValeursCriteres() != null) {
                    // on recupere sa liste de valeurs criteres
                    final List<ValeurCritereSelecteurDto> listeValeurs =
                        mapperDozerBean.mapList(critereSelecteurModel.getListeValeursCriteres(), ValeurCritereSelecteurDto.class);
                    critereSelecteurDto.setListeValeursCriteres(listeValeurs);
                }
            }
        }

        if (assureSelecteurModel.getListeValeursCriteres() != null) {
            // on mappe la liste de valeurs de l'assure principal du produit principal
            final List<ValeurCritereAssureSelecteurDto> listeValeurs =
                mapperDozerBean.mapList(assureSelecteurModel.getListeValeursCriteres(), ValeurCritereAssureSelecteurDto.class);
            assureSelecteurDto.setListeValeursCriteres(listeValeurs);
        }

        // on mappe la liste des beneficiaires du produit principal
        if (listeBeneficiairesModel != null) {
            produitSelecteurDto.setListeBeneficiaires(new ArrayList<AssureSelecteurDto>());
            // on recupere les beneficiaires du produit principal
            for (AssureSelecteurModel beneficiaireModel : listeBeneficiairesModel) {
                final AssureSelecteurDto beneficiaireDto = mapperDozerBean.map(beneficiaireModel, AssureSelecteurDto.class);
                produitSelecteurDto.getListeBeneficiaires().add(beneficiaireDto);
                if (beneficiaireModel.getListeValeursCriteres() != null) {
                    // on recupere sa liste de valeurs criteres
                    final List<ValeurCritereAssureSelecteurDto> listeValeurs =
                        mapperDozerBean.mapList(beneficiaireModel.getListeValeursCriteres(), ValeurCritereAssureSelecteurDto.class);
                    beneficiaireDto.setListeValeursCriteres(listeValeurs);
                }
            }
        }
    }

    /**
     * Crée la map des âges.
     * @param selecteurProduitGWT le sélecteur produit.
     */
    private Map<String, AgeModel> creerMapAges(SelecteurProduitDto selecteurProduitDto) {
        // Récupération de l'ensemble des dates possibles
        final Set<DateCalculAgeDto> listeDatesACalculer = new HashSet<DateCalculAgeDto>();

        // Produit principal
        final ProduitSelecteurDto produitPrincipal = selecteurProduitDto.getFamillePrincipale().getProduitPrincipal();
        listeDatesACalculer.add(getDateCalculAgeGwtByAssureSelecteur(produitPrincipal.getAssurePrincipal()));
        if (produitPrincipal.getListeBeneficiaires() != null && !produitPrincipal.getListeBeneficiaires().isEmpty()) {
            for (AssureSelecteurDto beneficiaires : produitPrincipal.getListeBeneficiaires()) {
                listeDatesACalculer.add(getDateCalculAgeGwtByAssureSelecteur(beneficiaires));
            }
        }

        // Familles liées
        if (selecteurProduitDto.getListeFamillesLiees() != null && !selecteurProduitDto.getListeFamillesLiees().isEmpty()) {
            for (FamilleLieeSelecteurDto familleLiee : selecteurProduitDto.getListeFamillesLiees()) {
                // Parcours des produits de la famille
                if (familleLiee.getListeProduitsLies() != null && !familleLiee.getListeProduitsLies().isEmpty()) {
                    for (ProduitSelecteurDto produitSelecteur : familleLiee.getListeProduitsLies()) {
                        // Récupération des dates
                        listeDatesACalculer.add(getDateCalculAgeGwtByAssureSelecteur(produitSelecteur.getAssurePrincipal()));
                        if (produitSelecteur.getListeBeneficiaires() != null && !produitSelecteur.getListeBeneficiaires().isEmpty()) {
                            for (AssureSelecteurDto beneficiaires : produitSelecteur.getListeBeneficiaires()) {
                                listeDatesACalculer.add(getDateCalculAgeGwtByAssureSelecteur(beneficiaires));
                            }
                        }
                    }
                }
            }
        }

        // Parcours des dates pour récupérer les âges
        final Map<String, AgeModel> mapAges = new HashMap<String, AgeModel>();
        final SimpleDateFormat sdf = new SimpleDateFormat(ComposantTarificateurConstants.DATE_FORMAT);
        for (DateCalculAgeDto dateCalculAgeDto : listeDatesACalculer) {
            // Transformation des dates
            final String dateCalculString = sdf.format(dateCalculAgeDto.getDateCalcul().getTime());
            final String dateNaissanceString = sdf.format(dateCalculAgeDto.getDateNaissance().getTime());
            final String cle = dateCalculString + dateNaissanceString;
            mapAges.put(cle, CalculAgeUtil.getAgesCalculesPersonne(dateCalculAgeDto.getDateCalcul(), dateCalculAgeDto.getDateNaissance()));
        }
        return mapAges;
    }

    /**
     * Récupère le DTO dateCalcul à partir d'un AssureSelecteur.
     * @param assureSelecteurGWT le DTO AssureSelecteur
     * @return le DTO DateCalculAge
     */
    private DateCalculAgeDto getDateCalculAgeGwtByAssureSelecteur(AssureSelecteurDto assureSelecteurDto) {
        final DateCalculAgeDto dateCalculAgeDto = new DateCalculAgeDto();
        dateCalculAgeDto.setDateCalcul(assureSelecteurDto.getDateEffetTarification());
        dateCalculAgeDto.setDateNaissance(assureSelecteurDto.getDateNaissance());
        return dateCalculAgeDto;
    }

    /**
     * {@inheritDoc}
     */
    public AgeModel getAgesCalculesPersonne(DateCalculAgeModel dateCalculAge) {
    	final SimpleDateFormat sdf = new SimpleDateFormat(ComposantTarificateurConstants.DATE_FORMAT);
        if (dateCalculAge.getDateCalcul() != null && !dateCalculAge.getDateCalcul().equals("") && dateCalculAge.getDateNaissance() != null
            && !dateCalculAge.getDateNaissance().equals("")) {
            if (dateCalculAge.getDateCalcul().trim().matches(REGEXP_DATE) && dateCalculAge.getDateNaissance().trim().matches(REGEXP_DATE)) {
                try {
                    // Transformation des dates
                    final Calendar dateCalcul = Calendar.getInstance();
                    dateCalcul.setTime(sdf.parse(dateCalculAge.getDateCalcul()));
                    final Calendar dateNaissance = Calendar.getInstance();
                    dateNaissance.setTime(sdf.parse(dateCalculAge.getDateNaissance()));
                    // Appel de la méthode utilitaire
                    return CalculAgeUtil.getAgesCalculesPersonne(dateCalcul, dateNaissance);
                } catch (ParseException e) {
                    throw new TechnicalException(ComposantTarificateurConstants.ERROR_DATE_PARSING);
                }
            } else {
                throw new BusinessExceptionGwt(ComposantTarificateurConstants.ERROR_DATE_INVALID);
            }
        } else {
            throw new BusinessExceptionGwt(ComposantTarificateurConstants.ERROR_DATE_ABSENT);
        }
    }

    /**
     * Récupère l'identifiant de l'utilisateur connecté par son login.
     * @param login login de l'utilisateur connecté
     * @return l'identifiant de l'utilisateur connecté
     */
    private Long getIdUtilisateurConnecte(String login) {
        final UtilisateurDto utilisateur = utilisateurService.getUtilisateurByLogin(login);
        if (utilisateur == null) {
            throw new BusinessExceptionGwt(ComposantTarificateurConstants.ERROR_USER_ABSENT);
        }
        return utilisateur.getId();
    }

    /**
     * Set the tarificateurService value.
     * @param tarificateurService the tarificateurService to set
     */
    public void setTarificateurService(TarificateurService tarificateurService) {
        this.tarificateurService = tarificateurService;
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de utilisateurService.
     * @param utilisateurService la nouvelle valeur de utilisateurService
     */
    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
}
