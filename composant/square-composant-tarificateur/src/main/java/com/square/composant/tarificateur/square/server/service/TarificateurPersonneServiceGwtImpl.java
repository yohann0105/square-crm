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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.CaisseSimpleModel;
import com.square.composant.tarificateur.square.client.model.adhesion.RelationAssureSocialModel;
import com.square.composant.tarificateur.square.client.model.doublons.PersonneDoublonModel;
import com.square.composant.tarificateur.square.client.model.doublons.RechercheDoublonCritereModel;
import com.square.composant.tarificateur.square.client.model.personne.AssureSocialModel;
import com.square.composant.tarificateur.square.client.model.personne.InfoSanteModel;
import com.square.composant.tarificateur.square.client.service.TarificateurPersonneServiceGwt;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.CaisseDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.NumeroRoDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationDto;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.tarificateur.noyau.dto.personne.DimensionCriteresLienFamilialRechercheDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurSquareMappingService;

/**
 * Implémentation serveur des services GWT pour les services du tarficateur.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class TarificateurPersonneServiceGwtImpl implements TarificateurPersonneServiceGwt {

    /** Service tarificateur. */
    private TarificateurPersonneService tarificateurPersonneService;

    private MapperDozerBean mapperDozerBean;

    private DimensionService dimensionService;

    private PersonnePhysiqueService personnePhysiqueService;

    private PersonneService personneService;

    private SquareMappingService squareMappingService;

    /** Service de mapping. */
    private TarificateurSquareMappingService tarificateurSquareMappingService;

    private static final String ESPACE = " ";

    @Override
    public AssureSocialModel createAssureSocial(AssureSocialModel assureSocial, Long idDevis, boolean fromOuvertureOpp) {
        final PersonneDto assureDto =
            tarificateurPersonneService.createAssureSocial((PersonneDto) mapperDozerBean.map(assureSocial, PersonneDto.class), idDevis, fromOuvertureOpp);
        return mapperDozerBean.map(assureDto, AssureSocialModel.class);
    }

    @Override
    public RelationAssureSocialModel rechercherRelationAssureSocial(Long idPersonne, Long idAssureSocial, Long eidPersonne, Long eidAssureSocial) {
        // on recherche le lien existant dans le tarificateur
        final IdentifiantLibelleDto relationTarificateurDto = tarificateurPersonneService.rechercherRelationAssureSocial(idPersonne);

        IdentifiantLibelleDto relationSquareDto = null;
        // On va aussi chercher la relation dans square
        final RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();
        criteres.setActif(true);
        criteres.setIdPersonneSource(eidPersonne);
        criteres.setIdPersonneCible(eidAssureSocial);
        final List<Long> types = new ArrayList<Long>();
        types.add(squareMappingService.getIdTypeRelationEnfant());
        types.add(squareMappingService.getIdTypeRelationConjoint());
        criteres.setTypes(types);
        final List<RelationDto> relationsDto = personneService.rechercherRelationDoubleSens(criteres);
        if (!relationsDto.isEmpty()) {
            // On mappe avec les constantes de liens familiaux du tarificateur
            relationSquareDto = relationsDto.get(0).getType();
            final DimensionCriteresLienFamilialRechercheDto criteresTarificateur = new DimensionCriteresLienFamilialRechercheDto();
            final List<Long> listeIds = new ArrayList<Long>();
            if (relationSquareDto.getIdentifiant().equals(squareMappingService.getIdTypeRelationConjoint())) {
                listeIds.add(tarificateurSquareMappingService.getIdLienFamilialConjoint());
            }
            if (relationSquareDto.getIdentifiant().equals(squareMappingService.getIdTypeRelationEnfant())) {
                listeIds.add(tarificateurSquareMappingService.getIdLienFamilialEnfant());
            }
            criteresTarificateur.setListeIds(listeIds);
            final List<IdentifiantLibelleDto> liensFamiliaux = tarificateurPersonneService.rechercherLiensFamiliauxParCriteres(criteresTarificateur);
            if (!liensFamiliaux.isEmpty()) {
                relationSquareDto = liensFamiliaux.get(0);
            }
        }

        final IdentifiantLibelleGwt relationTarificateurModel = mapperDozerBean.map(relationTarificateurDto, IdentifiantLibelleGwt.class);
        IdentifiantLibelleGwt relationSquareModel = null;
        if (relationSquareDto != null) {
            relationSquareModel = mapperDozerBean.map(relationSquareDto, IdentifiantLibelleGwt.class);
        }
        final RelationAssureSocialModel relationModel = new RelationAssureSocialModel();
        relationModel.setLienFamilialTarificateur(relationTarificateurModel);
        relationModel.setLienFamilialSquare(relationSquareModel);
        return relationModel;
    }

    @Override
    public List<AssureSocialModel> getListeAssuresSociaux(Long idDevis) {
        final List<PersonneDto> listeDto = tarificateurPersonneService.getAssuresSociauxByIdDevis(idDevis);
        final List<AssureSocialModel> liste = mapperDozerBean.mapList(listeDto, AssureSocialModel.class);
        for (AssureSocialModel assureSocialModel : liste) {
            // on complete le regime et caisse
            for (PersonneDto assureSocialDto : listeDto) {
                if (assureSocialDto.getId().equals(assureSocialModel.getId()) && assureSocialDto.getInfoSante() != null) {
                    final CaisseDto caisse = dimensionService.rechercherCaisseParId(assureSocialDto.getInfoSante().getEidCaisse());
                    if (caisse != null) {
                        assureSocialModel.getInfoSante().setCaisse((CaisseSimpleModel) mapperDozerBean.map(caisse, CaisseSimpleModel.class));
                        assureSocialModel.getInfoSante()
                                .setRegime((IdentifiantLibelleGwt) mapperDozerBean.map(caisse.getRegime(), IdentifiantLibelleGwt.class));
                    }
                    break;
                }
            }
        }
        return liste;
    }

    @Override
    public List<PersonneDoublonModel> rechercherDoublonsPersonneParCriteres(RechercheDoublonCritereModel criteres) {
        List<PersonneDoublonModel> listeDoublons = new ArrayList<PersonneDoublonModel>();
        // On vérifie que les critères nécessaires à la recherche de doublons sont renseignés.
        if (!StringUtils.isBlank(criteres.getNom()) && !StringUtils.isBlank(criteres.getPrenom()) && !StringUtils.isBlank(criteres.getDateNaissance())) {
            final PersonneCriteresRechercheDto criterias = mapperDozerBean.map(criteres, PersonneCriteresRechercheDto.class);
            criterias.setRechercheStricte(true);
            final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresDto =
                new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);
            final RemotePagingResultsDto<PersonneSimpleDto> result = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresDto);
            listeDoublons = mapperDozerBean.mapList(result.getListResults(), PersonneDoublonModel.class);

            for (PersonneDoublonModel doublon : listeDoublons) {
                // Récupération de la situation familiale (composition familiale)
                final com.square.core.model.dto.PersonneDto personneSimple = personnePhysiqueService.rechercherPersonneParIdentifiant(doublon.getId());
                doublon.setCompoFamiliale(personneSimple.getSitFam().getLibelle());

                // Récupération de l'adresse principale
                final CoordonneesDto coordonneesDoublon = personneService.rechercherCoordonneesParIdPersonne(doublon.getId());
                final AdresseDto adressePrinc = rechercherAdressePrincipaleEnCours(coordonneesDoublon.getAdresses());
                if (adressePrinc != null) {
                    final String adressePrincDoublon = getAdresseFusionByAdresse(adressePrinc);
                    doublon.setAdresse(adressePrincDoublon);
                    if (adressePrinc.getCodePostalCommune() != null) {
                        if (adressePrinc.getCodePostalCommune().getCodePostal() != null) {
                            doublon.setCodePostal(adressePrinc.getCodePostalCommune().getCodePostal().getLibelle());
                        }
                        if (adressePrinc.getCodePostalCommune().getCommune() != null) {
                            doublon.setCommune(adressePrinc.getCodePostalCommune().getCommune().getLibelle());
                        }
                    }
                    else if (adressePrinc.getCodePostalEtranger() != null) {
                        doublon.setCodePostal(adressePrinc.getCodePostalEtranger());
                    }
                    else if (adressePrinc.getCommuneEtranger() != null) {
                        doublon.setCommune(adressePrinc.getCommuneEtranger());
                    }
                }

                if (personneSimple.getInfoSante() != null) {
                    final InfoSanteModel infoSante = new InfoSanteModel();
                    final NumeroRoDto numeroRo = squareMappingService.convertirNroVersNss(personneSimple.getInfoSante().getNro());
                    if (numeroRo != null) {
                        infoSante.setNumSecuriteSocial(numeroRo.getNumeroSS());
                        infoSante.setCleSecuriteSocial(numeroRo.getCleSS());
                    }
                    infoSante.setCaisse((CaisseSimpleModel) mapperDozerBean.map(personneSimple.getInfoSante().getCaisse(), CaisseSimpleModel.class));
                    infoSante.setRegime((IdentifiantLibelleGwt) mapperDozerBean.map(personneSimple.getInfoSante().getCaisseRegime(),
                        IdentifiantLibelleGwt.class));
                    doublon.setInfoSante(infoSante);
                }
            }
        }

        return listeDoublons;
    }

    /**
     * Recherche l'adresse principale en cours parmi une liste d'adresses.
     * @param listeAdresses la liste d'adresses
     * @return l'adresse, null si pas trouvée
     */
    private AdresseDto rechercherAdressePrincipaleEnCours(List<AdresseDto> listeAdresses) {
        final Long idNatureAdressePrincipale = squareMappingService.getIdNatureAdressePrincipale();
        final Calendar aujourdhui = Calendar.getInstance();
        // Parcours de la liste d'adresses
        if (listeAdresses != null) {
            for (AdresseDto adresse : listeAdresses) {
                // Si c'est une adresse principale
                if (adresse.getNature() != null && idNatureAdressePrincipale.equals(adresse.getNature().getIdentifiant())) {
                    // Test si l'adresse est active
                    if (adresse.getDateDebut().before(aujourdhui) && (adresse.getDateFin() == null || adresse.getDateFin().after(aujourdhui))) {
                        return adresse;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Construit l'adresse principale.
     * @param adresseDto l'objet AdresseDto.
     * @return l'adresse principale.
     */
    private String getAdresseFusionByAdresse(AdresseDto adresseDto) {
    	if (adresseDto == null) {
            return null;
        }
        else {
            // Construction du libellé de l'adresse
            final StringBuffer libelle = new StringBuffer("");
            if (adresseDto.isNpai()) {
                libelle.append(ComposantTarificateurConstants.MESSAGE_NPAI);
            }
            if (adresseDto.getComplementNom() != null && !"".equals(adresseDto.getComplementNom().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getComplementNom().trim());
            }
            if (adresseDto.getNumVoie() != null && !"".equals(adresseDto.getNumVoie().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getNumVoie().trim());
            }
            if (adresseDto.getTypeVoie() != null && adresseDto.getTypeVoie().getLibelle() != null && !"".equals(adresseDto.getTypeVoie().getLibelle().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getTypeVoie().getLibelle().trim());
            }
            if (adresseDto.getNomVoie() != null && !"".equals(adresseDto.getNomVoie().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getNomVoie().trim());
            }
            if (adresseDto.getComplementAdresse() != null && !"".equals(adresseDto.getComplementAdresse().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getComplementAdresse().trim());
            }
            if (adresseDto.getAutresComplements() != null && !"".equals(adresseDto.getAutresComplements().trim())) {
                if (libelle.length() > 0) {
                    libelle.append(ESPACE);
                }
                libelle.append(adresseDto.getAutresComplements().trim());
            }
            return libelle.toString();
        }
    }

    /**
     * Set the tarificateurPersonneService value.
     * @param tarificateurPersonneService the tarificateurPersonneService to set
     */
    public void setTarificateurPersonneService(TarificateurPersonneService tarificateurPersonneService) {
        this.tarificateurPersonneService = tarificateurPersonneService;
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the dimensionService value.
     * @param dimensionService the dimensionService to set
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * Set the personnePhysiqueService value.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
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
     * Définit le tarificateurSquareMappingService.
     * @param tarificateurSquareMappingService le nouveau tarificateurSquareMappingService
     */
    public void setTarificateurSquareMappingService(TarificateurSquareMappingService tarificateurSquareMappingService) {
        this.tarificateurSquareMappingService = tarificateurSquareMappingService;
    }
}
