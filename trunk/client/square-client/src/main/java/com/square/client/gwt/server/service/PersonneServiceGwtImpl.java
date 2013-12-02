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
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;

import com.square.client.gwt.client.model.AdresseCreationModel;
import com.square.client.gwt.client.model.AdresseModel;
import com.square.client.gwt.client.model.CoordonneesModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.model.PersonneMoraleModel;
import com.square.client.gwt.client.model.PersonneMoraleRelationModel;
import com.square.client.gwt.client.model.PersonnePhysiqueRelationModel;
import com.square.client.gwt.client.model.PersonneRelationModel;
import com.square.client.gwt.client.model.RelationCriteresRechercheModel;
import com.square.client.gwt.client.model.RelationInfosModel;
import com.square.client.gwt.client.model.RelationModel;
import com.square.client.gwt.client.model.TelephoneModel;
import com.square.client.gwt.client.service.PersonneServiceGwt;
import com.square.client.gwt.server.util.FormaterTelephoneUtil;
import com.square.core.model.dto.AdresseCreationDto;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.PersonneBaseDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneMoraleDto;
import com.square.core.model.dto.PersonneMoraleRelationDto;
import com.square.core.model.dto.PersonnePhysiqueRelationDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Implémentation serveur des services GWT pour le service des personnes.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonneServiceGwtImpl implements PersonneServiceGwt {

    private PersonneService personneService;

    private PersonnePhysiqueService personnePhysiqueService;

    private SquareMappingService squareMappingService;

    private MapperDozerBean mapperDozerBean;

    @Override
    public CoordonneesModel creerOuMettreAJourCoordonnees(CoordonneesModel coordonnees, Boolean impacterFamille, Boolean forcerDesactivationEservices) {
        final CoordonneesDto coordonneesDto = mapperDozerBean.map(coordonnees, CoordonneesDto.class);
        final CoordonneesModel coordonneesCreees =
            mapperDozerBean.map(personneService.creerOuMettreAJourCoordonnees(coordonneesDto, impacterFamille, forcerDesactivationEservices),
                CoordonneesModel.class);
        // Transformation de l'expression régulière du format des numéros de téléphone en formateur
        if (coordonneesCreees.getTelephones() != null && coordonneesCreees.getTelephones().size() > 0) {
            for (TelephoneModel telephone : coordonneesCreees.getTelephones()) {
                if (telephone.getPays() != null && telephone.getPays().getFormatTelephone() != null) {
                    telephone.getPays().setFormatTelephone(FormaterTelephoneUtil.formterNumTelephone(telephone.getPays().getId()));
                }
            }
        }
        return coordonneesCreees;
    }

    @Override
    public CoordonneesModel rechercherCoordonneesParIdPersonne(Long idPersonne) {
        final CoordonneesModel coordonneesModel = mapperDozerBean.map(personneService.rechercherCoordonneesParIdPersonne(idPersonne), CoordonneesModel.class);
        // Transformation de l'expression régulière du format des numéros de téléphone en formateur
        if (coordonneesModel.getTelephones() != null && coordonneesModel.getTelephones().size() > 0) {
            for (TelephoneModel telephone : coordonneesModel.getTelephones()) {
                if (telephone.getPays() != null && telephone.getPays().getFormatTelephone() != null) {
                    telephone.getPays().setFormatTelephone(FormaterTelephoneUtil.formterNumTelephone(telephone.getPays().getId()));
                }
            }
        }
        return coordonneesModel;
    }

    @Override
    public RelationModel creerRelation(RelationModel relation) {
        final RelationDto relationDto = mapperDozerBean.map(relation, RelationDto.class);
        personneService.creerRelation(relationDto);
        return null;
    }

    @Override
    public void modifierRelations(List<RelationModel> relations) {
        final List<RelationDto> relationsDto = mapperDozerBean.mapList(relations, RelationDto.class);
        personneService.modifierRelations(relationsDto);
    }

    @Override
    public void activerRelation(Long idRelation, boolean active) {
        final RelationDto relation = personneService.rechercherRelationParId(idRelation);
        relation.setDateFin(active ? null : Calendar.getInstance());
        personneService.modifierRelation(relation);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RelationInfosModel<? extends PersonneRelationModel>> rechercherRelationsParCritreres(RelationCriteresRechercheModel criteres) {
        final RelationCriteresRechercheDto criteresDto = mapperDozerBean.map(criteres, RelationCriteresRechercheDto.class);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteresDto, 0, Integer.MAX_VALUE);
        criterias.setListeSorts(new ArrayList<RemotePagingSort>());
        // tri : d'abord relations en cours puis terminée
        final RemotePagingSort sortActif = new RemotePagingSort(squareMappingService.getOrderByRelationActif(), RemotePagingSort.REMOTE_PAGING_SORT_DESC);
        final RemotePagingSort sortType = new RemotePagingSort(squareMappingService.getOrderByRelationTypeOrdre(), RemotePagingSort.REMOTE_PAGING_SORT_ASC);
        final RemotePagingSort sortDateFin = new RemotePagingSort(squareMappingService.getOrderByRelationDateFin(), RemotePagingSort.REMOTE_PAGING_SORT_ASC);
        criterias.getListeSorts().add(sortActif);
        criterias.getListeSorts().add(sortType);
        // criterias.getListeSorts().add(sortDateFin);
        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> result = personneService.rechercherRelationsParCritreres(criterias);

        final List<RelationInfosModel<? extends PersonneRelationModel>> resultModel = new ArrayList<RelationInfosModel<? extends PersonneRelationModel>>();
        if (result.getListResults() != null && result.getListResults().size() > 0) {
            for (RelationInfosDto<? extends PersonneRelationDto> relation : result.getListResults()) {
                final RelationInfosModel relationInfosModel = mapperDozerBean.map(relation, RelationInfosModel.class);
                if (relation.getPersonne() instanceof PersonnePhysiqueRelationDto) {
                    final PersonnePhysiqueRelationModel personnePhysiqueRelationModel =
                        mapperDozerBean.map(relation.getPersonne(), PersonnePhysiqueRelationModel.class);
                    relationInfosModel.setPersonne(personnePhysiqueRelationModel);
                } else if (relation.getPersonne() instanceof PersonneMoraleRelationDto) {
                    final PersonneMoraleRelationModel personneMoraleRelationModel =
                        mapperDozerBean.map(relation.getPersonne(), PersonneMoraleRelationModel.class);
                    relationInfosModel.setPersonne(personneMoraleRelationModel);
                }
                resultModel.add(relationInfosModel);
            }
        }
        return resultModel;
    }

    @Override
    public String[][] relationOrgChartDataStore(final Long idPersonne, final List<Long> filtreGroupement, final List<Long> filtrePasDansGroupement) {
        final PersonneBaseDto personne = personneService.rechercherPersonneParId(idPersonne);
        String nom = "";
        Integer age = 0;
        Boolean relationPersonnePhysique = Boolean.TRUE;
        if (personne instanceof PersonneMoraleDto) {
            final PersonneMoraleDto personneMorale = (PersonneMoraleDto) personne;
            nom += personneMorale.getRaisonSociale().toUpperCase();
            relationPersonnePhysique = Boolean.FALSE;
        } else {
            final PersonneDto personnePhysique = (PersonneDto) personne;
            final char[] delimiters = {' ', '-', '_'};
            final String prenomMisEnForme = WordUtils.capitalizeFully(personnePhysique.getPrenom(), delimiters);
            nom += personnePhysique.getNom().toUpperCase() + " " + prenomMisEnForme;
            final PersonneSimpleDto personneSimpleDto = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(personnePhysique.getIdentifiant());
            final String[] ageString = personneSimpleDto.getAge().split(" ");
            if (ageString.length > 0) {
                age = Integer.parseInt(ageString[0]);
            }
            relationPersonnePhysique = Boolean.TRUE;
        }

        final List<String[]> datas = new ArrayList<String[]>();
        final String[] data = new String[] {idPersonne.toString(), idPersonne.toString(), "Personne source", nom, "Source", age.toString()};
        datas.add(data);

        ajouterRelations(datas, idPersonne, true, filtreGroupement, filtrePasDansGroupement);
        final String[][] datasRelation = new String[datas.size()][6];
        for (int index = 0; index < datas.size(); index++) {
            datasRelation[index] = datas.get(index);
        }
        // On effectue le tri s'il s'agit de relation entre personne physique.
        // if (relationPersonnePhysique) {
        // trieDatasRelationSelonAge(datasRelation);
        // }

        if (!relationPersonnePhysique) {
            for (int rowi = 0; rowi < datasRelation.length; rowi++) {
                datasRelation[rowi][1] = datasRelation[0][0];
            }
        }
        return datasRelation;
    }

    /**
     * Trie la matrice des relations selon l'âge.
     * @param datasRelation
     */
    private void trieDatasRelationSelonAge(String[][] datasRelation) {
        String[] tmp = new String[5];
        for (int rowi = 0; rowi < datasRelation.length; rowi++) {
            for (int rowj = 0; rowj < datasRelation.length; rowj++) {
                if (Integer.parseInt(datasRelation[rowi][5]) > Integer.parseInt(datasRelation[rowj][5])) {
                    tmp = datasRelation[rowi];
                    datasRelation[rowi] = datasRelation[rowj];
                    datasRelation[rowj] = tmp;
                }
            }
        }
    }

    /**
     * Ajouter les relations.
     * @param datasRelations les données en cours.
     * @param idPersonnePrincipal identifiant de la personne cible.
     * @param nomPersonne le nom de la personne.
     */
    private void ajouterRelations(List<String[]> datasRelations, final Long idPersonnePrincipal, boolean twoWay, List<Long> filtreGroupements,
        List<Long> filtrePasDansGroupements) {

        // On va commencer par déterminer si la personne est une personne physique ou morale
        final PersonneBaseModel personnePrincipal = rechercherPersonneParId(idPersonnePrincipal);
        if (personnePrincipal != null) {
            // Si c'est une personne morale, on garde l'ancien traitement
            if (personnePrincipal instanceof PersonneMoraleModel) {
                Integer age = 0;
                final RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();
                if (!twoWay) {
                    criteres.setIdPersonneSource(idPersonnePrincipal);
                } else {
                    criteres.setIdPersonne(idPersonnePrincipal);
                }
                criteres.setGroupements(filtreGroupements);
                criteres.setPasDansGroupements(filtrePasDansGroupements);
                final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
                    new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);

                final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> result =
                    personneService.rechercherRelationsParCritreres(criterias);

                if (result.getListResults() != null && result.getListResults().size() > 0) {
                    for (RelationInfosDto<? extends PersonneRelationDto> personne : result.getListResults()) {
                        String nom = "";
                        if (personne.getPersonne() instanceof PersonneMoraleRelationDto) {
                            final PersonneMoraleRelationDto personneMorale = (PersonneMoraleRelationDto) personne.getPersonne();
                            nom += personneMorale.getRaisonSociale().toUpperCase();
                        } else {
                            final PersonnePhysiqueRelationDto personnePhysique = (PersonnePhysiqueRelationDto) personne.getPersonne();
                            final char[] delimiters = {' ', '-', '_'};
                            final String prenomMisEnForme = WordUtils.capitalizeFully(personnePhysique.getPrenom(), delimiters);
                            nom += personnePhysique.getNom().toUpperCase() + " " + prenomMisEnForme;
                            final PersonneSimpleDto personneSimpleDto =
                                personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(personnePhysique.getId());
                            final String[] ageString = personneSimpleDto.getAge().split(" ");
                            if (ageString.length > 0) {
                                age = Integer.parseInt(ageString[0]);
                            }
                        }
                        final String etatRelation = (personne.getDateFin() != null) ? "Relation Terminée" : "Relation en cours";
                        final String[] data =
                            new String[] {personne.getPersonne().getId().toString(), idPersonnePrincipal.toString(), etatRelation, nom,
                                personne.getType().getLibelle(), age.toString()};
                        datasRelations.add(data);
                    }
                }
            }
            // Si c'est une personne physique, on va réorganiser les éléments de façon généalogique
            else if (personnePrincipal instanceof PersonneModel) {
                Integer age = 0;
                final RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();

                final List<RelationInfosDto<? extends PersonneRelationDto>> listeParents = new ArrayList<RelationInfosDto<? extends PersonneRelationDto>>();
                final List<RelationInfosDto<? extends PersonneRelationDto>> listeConjoints = new ArrayList<RelationInfosDto<? extends PersonneRelationDto>>();
                final List<RelationInfosDto<? extends PersonneRelationDto>> listeEnfants = new ArrayList<RelationInfosDto<? extends PersonneRelationDto>>();

                // dans tous les cas, on récupère les relations dont la personne est source
                criteres.setIdPersonneSource(idPersonnePrincipal);
                criteres.setGroupements(filtreGroupements);
                criteres.setPasDansGroupements(filtrePasDansGroupements);
                final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteriasSource =
                    new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);

                final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> resultSource =
                    personneService.rechercherRelationsParCritreres(criteriasSource);

                if (resultSource.getListResults() != null && resultSource.getListResults().size() > 0) {
                    for (RelationInfosDto<? extends PersonneRelationDto> personne : resultSource.getListResults()) {
                        if (personne.getType().getIdentifiant().equals(squareMappingService.getIdTypeRelationConjoint())) {
                            listeConjoints.add(personne);
                        } else {
                            listeEnfants.add(personne);
                        }
                    }
                }
                // Si on les veux, on récupère à part les relations dont la personne est la cible
                // c'est à dire les relations a pour parent et est le conjoint de
                if (twoWay) {
                    criteres.setIdPersonneSource(null);
                    criteres.setIdPersonne(idPersonnePrincipal);
                    final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteriasCible =
                        new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);

                    final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> resultCible =
                        personneService.rechercherRelationsParCritreres(criteriasCible);

                    if (resultCible.getListResults() != null && resultCible.getListResults().size() > 0) {
                        for (RelationInfosDto<? extends PersonneRelationDto> personne : resultCible.getListResults()) {

                            // On s'assure que la relation n'existe pas déja
                            boolean existeDeja = false;
                            for (RelationInfosDto<? extends PersonneRelationDto> relationEnfant : listeEnfants) {
                                if (relationEnfant.getId().equals(personne.getId())) {
                                    existeDeja = true;
                                }
                            }
                            for (RelationInfosDto<? extends PersonneRelationDto> relationConjoint : listeConjoints) {
                                if (relationConjoint.getId().equals(personne.getId())) {
                                    existeDeja = true;
                                }
                            }
                            if (!existeDeja) {
                                if (personne.getType().getIdentifiant().equals(squareMappingService.getIdTypeRelationEnfant())) {
                                    listeParents.add(personne);
                                } else {
                                    listeConjoints.add(personne);
                                }
                            }
                        }
                    }
                }
                // On fabrique une liste de relations triées
                final List<RelationInfosDto<? extends PersonneRelationDto>> listePersonnes = new ArrayList<RelationInfosDto<? extends PersonneRelationDto>>();
                listePersonnes.addAll(listeParents);
                listePersonnes.addAll(listeConjoints);
                listePersonnes.addAll(listeEnfants);

                // On retire la personne de la liste
                final String[] dataPersonne = datasRelations.get(0);
                datasRelations.clear();

                // On renmplie la liste avec les personnes déja triées
                for (RelationInfosDto<? extends PersonneRelationDto> personne : listePersonnes) {
                    String nom = "";
                    String etatRelation;
                    if (listeParents.contains(personne)) {
                        etatRelation = "Parent";
                    } else if (listeConjoints.contains(personne)) {
                        etatRelation = "Conjoint";
                    } else {
                        etatRelation = "Enfant";
                    }
                    final PersonnePhysiqueRelationDto personnePhysique = (PersonnePhysiqueRelationDto) personne.getPersonne();
                    final char[] delimiters = {' ', '-', '_'};
                    final String prenomMisEnForme = WordUtils.capitalizeFully(personnePhysique.getPrenom(), delimiters);
                    nom += personnePhysique.getNom().toUpperCase() + " " + prenomMisEnForme;
                    final PersonneSimpleDto personneSimpleDto = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(personnePhysique.getId());
                    final String[] ageString = personneSimpleDto.getAge().split(" ");
                    if (ageString.length > 0) {
                        age = Integer.parseInt(ageString[0]);
                    }
                    if (personne.getDateFin() != null) {
                        etatRelation = "Relation Terminée";
                    }
                    final String[] data =
                        new String[] {personne.getPersonne().getId().toString(), idPersonnePrincipal.toString(), etatRelation, nom,
                            personne.getType().getLibelle(), age.toString()};
                    datasRelations.add(data);
                }

                // On réinsère la personne dans la liste après ses parents
                datasRelations.add(listeParents.size(), dataPersonne);

                // On fait pointer chaque parent sur lui même
                if (!listeParents.isEmpty()) {
                    for (int i = 0; i < listeParents.size(); i++) {
                        datasRelations.get(i)[1] = datasRelations.get(i)[0];
                    }
                    // On fait pointer la personne sur le dernier parent de la liste
                    datasRelations.get(listeParents.size())[1] = datasRelations.get(listeParents.size() - 1)[0];
                }
                // Si pas de parents, la personne pointe sur elle même
                else {
                    datasRelations.get(0)[1] = datasRelations.get(0)[0];
                }
                // Si on a des conjoints, on les fait pointer sur la bonne personne
                if (!listeConjoints.isEmpty()) {
                    // On fait pointer les conjoints sur le dernier parent
                    for (int i = 0; i < listeConjoints.size(); i++) {
                        if (!listeParents.isEmpty()) {
                            datasRelations.get(listeParents.size() + i + 1)[1] = datasRelations.get(listeParents.size() - 1)[0];
                        }
                        // Si pas de parents, on fait pointer sur lui-même
                        else {
                            datasRelations.get(i + 1)[1] = datasRelations.get(i + 1)[0];
                        }
                    }
                }
            } // fin bloc personne physique
        } // fin bloc personne existe
    }

    @Override
    public PersonneBaseModel rechercherPersonneParId(Long id) {
        final PersonneBaseDto personne = personneService.rechercherPersonneParId(id);
        if (personne instanceof PersonneDto) {
            return mapperDozerBean.map(personne, PersonneModel.class);
        } else {
            return mapperDozerBean.map(personne, PersonneMoraleModel.class);
        }
    }

    @Override
    public Boolean hasConjoint(Long idPersonne) {
        final RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();
        criteres.setIdPersonne(idPersonne);
        final List<Long> listeTypes = new ArrayList<Long>();
        listeTypes.add(squareMappingService.getIdTypeRelationConjoint());
        criteres.setTypes(listeTypes);
        criteres.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);

        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> result = personneService.rechercherRelationsParCritreres(criterias);
        return result != null && result.getListResults() != null && result.getListResults().size() > 0;
    }

    @Override
    public Long getIdConjoint(Long idPersonne) {
        final RelationCriteresRechercheDto criteres = new RelationCriteresRechercheDto();
        criteres.setIdPersonne(idPersonne);
        final List<Long> listeTypes = new ArrayList<Long>();
        listeTypes.add(squareMappingService.getIdTypeRelationConjoint());
        criteres.setTypes(listeTypes);
        criteres.setActif(true);
        final RemotePagingCriteriasDto<RelationCriteresRechercheDto> criterias =
            new RemotePagingCriteriasDto<RelationCriteresRechercheDto>(criteres, 0, Integer.MAX_VALUE);

        final RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> result = personneService.rechercherRelationsParCritreres(criterias);

        if (result == null || result.getListResults() == null || result.getListResults().size() == 0 || result.getListResults().get(0).getIdPersonne() == null) {
            return null;
        } else {
            return result.getListResults().get(0).getIdPersonne();
        }
    }

    @Override
    public String getLibelleAPourEnfant() {
        return squareMappingService.getLibelleAPourEnfant();
    }

    @Override
    public AdresseCreationModel ajouterNouvelleAdresse(Long idPersonne, AdresseModel nouvelleAdresse, Boolean impacterFamille) {
        final AdresseDto newAdresseDto = mapperDozerBean.map(nouvelleAdresse, AdresseDto.class);
        final AdresseCreationDto adresseCreee = personneService.ajouterNouvelleAdresse(idPersonne, newAdresseDto, impacterFamille);
        return mapperDozerBean.map(adresseCreee, AdresseCreationModel.class);
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the personneService value.
     * @param personneService the personneService to set
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Set the personnePhysiqueService value.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }
}
