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
package com.square.client.gwt.server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.square.adherent.noyau.service.interfaces.AdherentService;
import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.client.gwt.client.model.BeneficiaireModel;
import com.square.client.gwt.client.model.CompteursModel;
import com.square.client.gwt.client.model.CoordonneesModel;
import com.square.client.gwt.client.model.PersonneCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneDoublonModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.model.PersonnePermissionOngletModel;
import com.square.client.gwt.client.model.PersonnePhysiqueCopieModel;
import com.square.client.gwt.client.model.PersonneSimpleModel;
import com.square.client.gwt.client.model.TelephoneModel;
import com.square.client.gwt.client.model.CompteursModel.Compteur;
import com.square.client.gwt.client.service.PersonnePhysiqueServiceGwt;
import com.square.composant.fusion.square.client.model.RechercheDoublonCritereModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPersonneSyncModel;
import com.square.composant.tarificateur.square.client.model.personne.InfoSanteCreationModel;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.BeneficiaireDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.InfosPersonneSyncDto;
import com.square.core.model.dto.OpportuniteCriteresRechercheDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonnePhysiqueCopieDto;
import com.square.core.model.dto.PersonnePhysiqueRelationDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.ActionService;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.mail.core.dto.gestionmails.CritereRechercheNombreEmailDto;
import com.square.mail.core.service.interfaces.gestionmails.EmailService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurSquareMappingService;

/**
 * Implémentation serveur des services GWT pour le service des personnes physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonnePhysiqueServiceGwtImpl implements PersonnePhysiqueServiceGwt {

    private PersonnePhysiqueService personnePhysiqueService;

    private PersonneService personneService;

    private SquareMappingService squareMappingService;

    private TarificateurSquareMappingService tarificateurSquareMappingService;

    private AdherentService adherentService;

    private ContratService contratService;

    private OpportuniteService opportuniteService;

    private MapperDozerBean mapperDozerBean;

    private EmailService emailService;

    /** Service des actions. */
    private ActionService actionService;

    private static final String ESPACE = " ";

    /**
     * {@inheritDoc}
     */
    @Override
    public RemotePagingResultsGwt<PersonneSimpleModel> rechercherPersonneFullTextParCriteres(
        RemotePagingCriteriasGwt<PersonneCriteresRechercheModel> criteres) {
        final PersonneCriteresRechercheDto criterias = mapperDozerBean.map(criteres.getCriterias(), PersonneCriteresRechercheDto.class);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, criteres.getFirstResult(), criteres.getMaxResult());
        final List<RemotePagingSort> listSort = mapperDozerBean.mapList(criteres.getListeSorts(), RemotePagingSort.class);
        criteresDto.setListeSorts(listSort);
        final RemotePagingResultsDto<PersonneSimpleDto> resultatsDto = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresDto);
        final RemotePagingResultsGwt<PersonneSimpleModel> resultats = new RemotePagingResultsGwt<PersonneSimpleModel>();
        final List<PersonneSimpleModel> listePersonnesGwt = mapperDozerBean.mapList(resultatsDto.getListResults(), PersonneSimpleModel.class);
        resultats.setListResults(listePersonnesGwt);
        resultats.setTotalResults(resultatsDto.getTotalResults());
        return resultats;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonneModel creerPersonnePhysique(PersonneModel personne, List<BeneficiaireModel> listeBeneficiaire, CoordonneesModel coordonnees) {
        PersonneDto personneDto = mapperDozerBean.map(personne, PersonneDto.class);
        final List<BeneficiaireDto> listeBeneficiaireDto = mapperDozerBean.mapList(listeBeneficiaire, BeneficiaireDto.class);
        final CoordonneesDto coordonneesDto = mapperDozerBean.map(coordonnees, CoordonneesDto.class);
        personneDto = personnePhysiqueService.creerPersonnePhysique(personneDto, listeBeneficiaireDto, coordonneesDto);
        return mapperDozerBean.map(personneDto, PersonneModel.class);
    }

    @Override
    public PersonneModel modifierPersonnePhysique(PersonneModel personne) {
        final PersonneDto personneDto = mapperDozerBean.map(personne, PersonneDto.class);
        final PersonneDto personneModifiee = personnePhysiqueService.modifierPersonnePhysique(personneDto);
        return mapperDozerBean.map(personneModifiee, PersonneModel.class);
    }

    @Override
    public PersonneSimpleModel rechercherPersonneSimpleParIdentifiant(Long id) {
        return mapperDozerBean.map(personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(id), PersonneSimpleModel.class);
    }

    @Override
    public PersonneModel rechercherPersonneParIdentifiant(Long id) {
        return mapperDozerBean.map(personnePhysiqueService.rechercherPersonneParIdentifiant(id), PersonneModel.class);
    }

    @Override
    public PersonneModel creerUneCopiePersonne(PersonnePhysiqueCopieModel infosCopie) {
        final PersonnePhysiqueCopieDto infosCopieDto = mapperDozerBean.map(infosCopie, PersonnePhysiqueCopieDto.class);
        final PersonneDto result = personnePhysiqueService.creerUneCopiePersonne(infosCopieDto);

        final PersonneModel resultModel = mapperDozerBean.map(result, PersonneModel.class);
        return resultModel;
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the personnePhysiqueService value.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public com.square.composant.tarificateur.square.client.model.personne.PersonneModel construirePersonneTarificateur(Long idPersonne) {
        final PersonneDto personne = personnePhysiqueService.rechercherPersonneParIdentifiant(idPersonne);
        final com.square.composant.tarificateur.square.client.model.personne.PersonneModel personneTarificateur = mapperPersonne(personne);

        // Mapping des relations
        final List<Long> groupements = new ArrayList<Long>();
        groupements.add(squareMappingService.getIdGroupementFamille());
        List<Long> types = new ArrayList<Long>();
        types.add(squareMappingService.getIdTypeRelationConjoint());
        final RelationCriteresRechercheDto criteresConjoint = new RelationCriteresRechercheDto();
        criteresConjoint.setGroupements(groupements);
        criteresConjoint.setTypes(types);
        criteresConjoint.setIdPersonne(idPersonne);
        criteresConjoint.setSupprime(false);
        criteresConjoint.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteriasConjoint =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresConjoint, 0, Integer.MAX_VALUE);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> resultConjoint =
            personneService.rechercherRelationsParCritreres(criteriasConjoint);
        final List<RelationInfosDto<? extends PersonneRelationDto>> listeRelations = new ArrayList<RelationInfosDto<? extends PersonneRelationDto>>();
        if (resultConjoint.getListResults() != null && resultConjoint.getListResults().size() > 0) {
            listeRelations.addAll(resultConjoint.getListResults());
        }
        final RelationCriteresRechercheDto criteresEnfant = new RelationCriteresRechercheDto();
        criteresEnfant.setGroupements(groupements);
        types = new ArrayList<Long>();
        types.add(squareMappingService.getIdTypeRelationEnfant());
        criteresEnfant.setTypes(types);
        criteresEnfant.setIdPersonneSource(idPersonne);
        criteresEnfant.setSupprime(false);
        criteresEnfant.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteriasEnfant =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresEnfant, 0, Integer.MAX_VALUE);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> resultEnfant =
            personneService.rechercherRelationsParCritreres(criteriasEnfant);
        if (resultEnfant.getListResults() != null && resultEnfant.getListResults().size() > 0) {
            listeRelations.addAll(resultEnfant.getListResults());
        }

        if (listeRelations != null && listeRelations.size() > 0) {
            final Map<Long, com.square.composant.tarificateur.square.client.model.personne.BeneficiaireModel> mapBeneficiaires =
                new HashMap<Long, com.square.composant.tarificateur.square.client.model.personne.BeneficiaireModel>();
            for (RelationInfosDto<? extends PersonneRelationDto> relation : listeRelations) {
                if (relation.getPersonne() instanceof PersonnePhysiqueRelationDto) {
                    // On récupère l'ensemble des informations du bénéficiaire
                    final PersonneDto beneficiaire = personnePhysiqueService.rechercherPersonneParIdentifiant(relation.getPersonne().getId());
                    final Long idTypeRelation = relation.getType().getIdentifiant();
                    // On mappe le bénéficiaire
                    final com.square.composant.tarificateur.square.client.model.personne.BeneficiaireModel benefTarificateur;
                    benefTarificateur =
                        mapperDozerBean.map(mapperPersonne(beneficiaire),
                            com.square.composant.tarificateur.square.client.model.personne.BeneficiaireModel.class);
                    if (squareMappingService.getIdTypeRelationConjoint().equals(idTypeRelation)) {
                        benefTarificateur.setIdLienFamilial(tarificateurSquareMappingService.getIdLienFamilialConjoint());
                    }
                    else if (squareMappingService.getIdTypeRelationEnfant().equals(idTypeRelation)) {
                        benefTarificateur.setIdLienFamilial(tarificateurSquareMappingService.getIdLienFamilialEnfant());
                    }
                    final Calendar jour = Calendar.getInstance();
                    boolean actif = relation.getDateDebut() == null || relation.getDateDebut().before(jour);
                    actif &= relation.getDateFin() == null || relation.getDateFin().after(jour);
                    actif &= !beneficiaire.isDecede();
                    actif &= !beneficiaire.isSupprime();
                    benefTarificateur.setActif(actif);
                    // il peut arriver qu'un bénéficiaire apparaisse plusieurs fois dans les relations de la personne
                    // on ne le met qu'une fois, et s'il existe en priorite celui qui correspond à une relation active
                    if (!mapBeneficiaires.containsKey(benefTarificateur.getEidPersonne()) || benefTarificateur.getActif()) {
                        mapBeneficiaires.put(benefTarificateur.getEidPersonne(), benefTarificateur);
                    }
                }
            }
            personneTarificateur.setListeBeneficiaires(new ArrayList<com.square.composant.tarificateur.square.client.model.personne.BeneficiaireModel>(
                mapBeneficiaires.values()));
        }

        return personneTarificateur;
    }

    @Override
    public PersonnePermissionOngletModel chargerPermissionOngletPersonne(Long uidPersonne) {
        final PersonnePermissionOngletModel permissions = new PersonnePermissionOngletModel();
        permissions.setHasEspaceClient(adherentService.hasPersonneEspaceAdherent(uidPersonne));
        permissions.setHasContrats(contratService.hasPersonneContrats(uidPersonne));
        permissions.setIsAfficherCotisation(contratService.hasDroitAffichageCotisation(uidPersonne));
        return permissions;
    }

    @Override
    public Boolean hasPersonneContrats(Long uidPersonne) {
        return contratService.hasPersonneContrats(uidPersonne);
    }

    /**
     * Mappe une personne pour l'adapter au tarificateur.
     * @param personne la personne à mapper.
     * @return la personne mappée.
     */
    private com.square.composant.tarificateur.square.client.model.personne.PersonneModel mapperPersonne(PersonneDto personne) {
        final com.square.composant.tarificateur.square.client.model.personne.PersonneModel personneTarificateur =
            mapperDozerBean.map(personne, com.square.composant.tarificateur.square.client.model.personne.PersonneModel.class);
        // On mappe les infos de santé à la main
        if (personne.getInfoSante() != null) {
            personneTarificateur.setInfoSante(new InfoSanteCreationModel());
            personneTarificateur.getInfoSante().setEidReferent(personne.getInfoSante().getIdReferent());
            if (personne.getInfoSante().getCaisse() != null) {
                personneTarificateur.getInfoSante().setEidCaisse(personne.getInfoSante().getCaisse().getId());
            }
            final int tailleNro = 15;
            if (!StringUtils.isBlank(personne.getInfoSante().getNro()) && personne.getInfoSante().getNro().length() == tailleNro) {
                final int tailleNss = 13;
                personneTarificateur.getInfoSante().setNumSecuriteSocial(personne.getInfoSante().getNro().substring(0, tailleNss));
                personneTarificateur.getInfoSante().setCleSecuriteSocial(personne.getInfoSante().getNro().substring(tailleNss, tailleNro));
            }
        }

        // On récupère les coordonnées de la personne.
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(personne.getIdentifiant());
        // Mapping des téléphones
        for (TelephoneDto tel : coordonnees.getTelephones()) {
            final com.square.composant.tarificateur.square.client.model.personne.TelephoneModel telTarificateur;
            final Long idNatureTel = tel.getNature().getIdentifiant();
            if (squareMappingService.getIdNatureTelephoneFixe().equals(idNatureTel)) {
                telTarificateur = mapperDozerBean.map(tel, com.square.composant.tarificateur.square.client.model.personne.TelephoneModel.class);
                personneTarificateur.setTelephoneFixe(telTarificateur);
            }
            else if (squareMappingService.getIdNatureMobilePrive().equals(idNatureTel)) {
                telTarificateur = mapperDozerBean.map(tel, com.square.composant.tarificateur.square.client.model.personne.TelephoneModel.class);
                personneTarificateur.setTelephonePortable(telTarificateur);
            }
            else if (squareMappingService.getIdNatureMobileTravail().equals(idNatureTel)) {
                telTarificateur = mapperDozerBean.map(tel, com.square.composant.tarificateur.square.client.model.personne.TelephoneModel.class);
                personneTarificateur.setTelephoneBureau(telTarificateur);
            }
        }

        // Mapping des adresses
        for (AdresseDto adresse : coordonnees.getAdresses()) {
            final com.square.composant.tarificateur.square.client.model.personne.AdresseModel adresseTarificateur;
            final Long idNatureAdresse = adresse.getNature().getIdentifiant();
            if (squareMappingService.getIdNatureAdressePrincipale().equals(idNatureAdresse)) {
                adresseTarificateur = mapperDozerBean.map(adresse, com.square.composant.tarificateur.square.client.model.personne.AdresseModel.class);
                personneTarificateur.setAdressePrincipale(adresseTarificateur);
            }
            else if (squareMappingService.getIdNatureAdresseSecondaire().equals(idNatureAdresse)) {
                adresseTarificateur = mapperDozerBean.map(adresse, com.square.composant.tarificateur.square.client.model.personne.AdresseModel.class);
                personneTarificateur.setAdresseSecondaire(adresseTarificateur);
            }
        }

        // Mapping des emails
        if (!coordonnees.getEmails().isEmpty()) {
            // On récupère le premier email seulement
            final com.square.composant.tarificateur.square.client.model.personne.EmailModel emailTarificateur;
            emailTarificateur =
                mapperDozerBean.map(coordonnees.getEmails().get(0), com.square.composant.tarificateur.square.client.model.personne.EmailModel.class);
            personneTarificateur.setEmail(emailTarificateur);
        }
        return personneTarificateur;
    }

    @Override
    public void mettreAJourInfosAdhesion(List<InfosPersonneSyncModel> listeInfos) {
        final List<InfosPersonneSyncDto> listeInfosDto = mapperDozerBean.mapList(listeInfos, InfosPersonneSyncDto.class);
        personnePhysiqueService.mettreAJourInfosAdhesion(listeInfosDto);
    }

    @Override
    public void controlerTelephone(TelephoneModel telephone) {
        final TelephoneDto telephoneDto = mapperDozerBean.map(telephone, TelephoneDto.class);
        personnePhysiqueService.controlerTelephone(telephoneDto);
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
                final PersonneSimpleDto personneSimple = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(doublon.getId());
                doublon.setCompoFamiliale(personneSimple.getSituationFamiliale());

                // Récupération de l'adresse principale
                final CoordonneesDto coordonneesDoublon = personneService.rechercherCoordonneesParIdPersonne(doublon.getId());
                final AdresseDto adressePrinc = rechercherAdressePrincipaleEnCours(coordonneesDoublon.getAdresses());
                final String adressePrincDoublon = getAdresseFusionByAdresse(adressePrinc);
                doublon.setAdresse(adressePrincDoublon);
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
                libelle.append("(NPAI)");
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
     * Setter function.
     * @param personneService the personneService to set
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Setter function.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définit la valeur de tarificateurSquareMappingService.
     * @param tarificateurSquareMappingService la nouvelle valeur de tarificateurSquareMappingService
     */
    public void setTarificateurSquareMappingService(TarificateurSquareMappingService tarificateurSquareMappingService) {
        this.tarificateurSquareMappingService = tarificateurSquareMappingService;
    }

    /**
     * Définit la valeur de adherentService.
     * @param adherentService la nouvelle valeur de adherentService
     */
    public void setAdherentService(AdherentService adherentService) {
        this.adherentService = adherentService;
    }

    /**
     * Définit la valeur de contratService.
     * @param contratService la nouvelle valeur de contratService
     */
    public void setContratService(ContratService contratService) {
        this.contratService = contratService;
    }

    @Override
    public CompteursModel getCompteursParId(Long id, CompteursModel parametres) {
        if (parametres.getCompteursDemandes() == null) {
            return parametres;
        }
        final Map<Compteur, Integer> compteursResultat = new HashMap<Compteur, Integer>();
        for (Compteur compteurDemande : parametres.getCompteursDemandes()) {
            try {
                switch (compteurDemande) {
                    case ACTIONS :
                        final ActionCritereRechercheDto criteres = new ActionCritereRechercheDto();
                        criteres.setIdPersonne(id);
                        final Integer nbActions = actionService.countActionsParCriteres(criteres);
                        compteursResultat.put(Compteur.ACTIONS, nbActions);
                    break;
                    case CONTRATS :
                        final Integer nbContrats = contratService.countContrats(id, false);
                        compteursResultat.put(Compteur.CONTRATS, nbContrats);
                    break;
                    case RELATIONS :
                        final RelationCriteresRechercheDto criteresRelations = new RelationCriteresRechercheDto();
                        criteresRelations.setPasDansGroupements(Arrays.asList(squareMappingService.getIdGroupementFamille()));
                        criteresRelations.setIdPersonne(id);
                        criteresRelations.setSupprime(false);
                        final Integer nbRelations = personneService.countRelationsParCriteres(criteresRelations);
                        compteursResultat.put(Compteur.RELATIONS, nbRelations);
                    break;
                    case FAMILLE :
                        final RelationCriteresRechercheDto criteresFamille = new RelationCriteresRechercheDto();
                        criteresFamille.setGroupements(Arrays.asList(squareMappingService.getIdGroupementFamille()));
                        criteresFamille.setIdPersonne(id);
                        criteresFamille.setSupprime(false);
                        final Integer nbFamille = personneService.countRelationsParCriteres(criteresFamille);
                        compteursResultat.put(Compteur.FAMILLE, nbFamille);
                    break;
                    case OPPORTUNITES :
                        final OpportuniteCriteresRechercheDto criteresOpp = new OpportuniteCriteresRechercheDto();
                        criteresOpp.setIdPersonnePhysique(id);
                        final Integer nbOpp = opportuniteService.countOpportuniteParCriteres(criteresOpp);
                        compteursResultat.put(Compteur.OPPORTUNITES, nbOpp);
                    break;
                    case EMAILS :
                        final PersonneSimpleDto pp = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(id);
                        final CritereRechercheNombreEmailDto criteresMail = new CritereRechercheNombreEmailDto();
                        criteresMail.setNumeroAdherent(pp.getNumeroClient());
                        final Integer nbMails = emailService.getNombreEmailsByCriteres(criteresMail);
                        compteursResultat.put(Compteur.EMAILS, nbMails);
                    break;
                    default: break;
                }
            } catch (Exception e) {
                // On ne bloque pas l'ensemble des compteurs parce qu'un d'eux n'a pas fonctionné
                e.printStackTrace();
            }
        }
        parametres.setCompteursObtenus(compteursResultat);
        return parametres;
    }

    /**
     * Définition de opportuniteService.
     * @param opportuniteService the opportuniteService to set
     */
    public void setOpportuniteService(OpportuniteService opportuniteService) {
        this.opportuniteService = opportuniteService;
    }

    /**
     * Définition de actionService.
     * @param actionService the actionService to set
     */
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    /**
     * Définition de emailService.
     * @param emailService the emailService to set
     */
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
