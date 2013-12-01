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
package com.square.core.service.implementations;

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.dao.interfaces.ActionDureeDao;
import com.square.core.dao.interfaces.ActionNatureDao;
import com.square.core.dao.interfaces.ActionNatureResultatDao;
import com.square.core.dao.interfaces.ActionObjetDao;
import com.square.core.dao.interfaces.ActionPrioriteDao;
import com.square.core.dao.interfaces.ActionResultatDao;
import com.square.core.dao.interfaces.ActionSousObjetDao;
import com.square.core.dao.interfaces.ActionStatutDao;
import com.square.core.dao.interfaces.ActionTypeDao;
import com.square.core.dao.interfaces.AdresseNatureDao;
import com.square.core.dao.interfaces.AgenceDao;
import com.square.core.dao.interfaces.CaisseDao;
import com.square.core.dao.interfaces.CaisseRegimeDao;
import com.square.core.dao.interfaces.CampagneStatutDao;
import com.square.core.dao.interfaces.CampagneTypeDao;
import com.square.core.dao.interfaces.CodePostalCommuneDao;
import com.square.core.dao.interfaces.CodePostalDao;
import com.square.core.dao.interfaces.CommuneDao;
import com.square.core.dao.interfaces.CspDao;
import com.square.core.dao.interfaces.DepartementDao;
import com.square.core.dao.interfaces.FormeJuridiqueDao;
import com.square.core.dao.interfaces.ModeleEmailDao;
import com.square.core.dao.interfaces.NatureTelephoneDao;
import com.square.core.dao.interfaces.PaysDao;
import com.square.core.dao.interfaces.PersonneCiviliteDao;
import com.square.core.dao.interfaces.PersonneMoraleNatureDao;
import com.square.core.dao.interfaces.PersonnePhysiqueNatureDao;
import com.square.core.dao.interfaces.PersonneProfessionDao;
import com.square.core.dao.interfaces.PersonneReseauDao;
import com.square.core.dao.interfaces.PersonneStatutDao;
import com.square.core.dao.interfaces.RelationTypeDao;
import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.dao.interfaces.RessourceEtatDao;
import com.square.core.dao.interfaces.RessourceFonctionDao;
import com.square.core.dao.interfaces.RessourceServiceDao;
import com.square.core.dao.interfaces.SegmentDao;
import com.square.core.dao.interfaces.SituationFamilialeDao;
import com.square.core.dao.interfaces.TypeVoieDao;
import com.square.core.model.ActionDuree;
import com.square.core.model.ActionNature;
import com.square.core.model.ActionNatureResultat;
import com.square.core.model.ActionObjet;
import com.square.core.model.ActionPriorite;
import com.square.core.model.ActionResultat;
import com.square.core.model.ActionSousObjet;
import com.square.core.model.ActionStatut;
import com.square.core.model.ActionType;
import com.square.core.model.AdresseNature;
import com.square.core.model.Caisse;
import com.square.core.model.CaisseRegime;
import com.square.core.model.CampagneStatut;
import com.square.core.model.CampagneType;
import com.square.core.model.CodePostal;
import com.square.core.model.Commune;
import com.square.core.model.Departement;
import com.square.core.model.FormeJuridique;
import com.square.core.model.NatureTelephone;
import com.square.core.model.Pays;
import com.square.core.model.PersonneCSP;
import com.square.core.model.PersonneCivilite;
import com.square.core.model.PersonneMoraleNature;
import com.square.core.model.PersonnePhysiqueNature;
import com.square.core.model.PersonneProfession;
import com.square.core.model.PersonneReseau;
import com.square.core.model.PersonneSegment;
import com.square.core.model.PersonneSituationFamiliale;
import com.square.core.model.PersonneStatut;
import com.square.core.model.RelationType;
import com.square.core.model.TypeVoie;
import com.square.core.model.Ressources.Agence;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.Ressources.RessourceEtat;
import com.square.core.model.Ressources.RessourceFonction;
import com.square.core.model.Ressources.RessourceService;
import com.square.core.model.dto.ActionNatureResultatCriteresRechercheDto;
import com.square.core.model.dto.CaisseDto;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CommuneCriteresRechercherDto;
import com.square.core.model.dto.DimensionCritereRechercheCodePostalCommuneDto;
import com.square.core.model.dto.DimensionCritereRechercheDepartementDto;
import com.square.core.model.dto.DimensionCritereRechercheObjetDto;
import com.square.core.model.dto.DimensionCritereRechercheRessourceFonctionDto;
import com.square.core.model.dto.DimensionCritereRechercheResultatActionDto;
import com.square.core.model.dto.DimensionCritereRechercheSousObjetDto;
import com.square.core.model.dto.DimensionCritereRechercheTypeRelationDto;
import com.square.core.model.dto.DimensionCriteresRechercheCaisseDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.DimensionCriteresRechercheRegimeDto;
import com.square.core.model.dto.DimensionCriteresRechercheRessourceDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.IdentifiantEidLibelleDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.IdentifiantLibelleCodePostalCommuneDto;
import com.square.core.model.dto.IdentifiantLibelleDepartementCodeDto;
import com.square.core.model.dto.IdentifiantLibelleTypeRelationDto;
import com.square.core.model.dto.ModeleEmailDto;
import com.square.core.model.dto.PaysSimpleDto;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.util.ActionKeyUtil;
import com.square.core.util.DimensionKeyUtil;
import com.square.core.util.PersonnePhysiqueKeyUtil;
import com.square.core.util.RessourceHabilitationUtil;

/**
 * Implémentation des services des tables de dimensions.
 * @author cblanchard - SCUB
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class DimensionServiceImplementation implements DimensionService {

    /**
     * Mapper Dozer.
     */
    private MapperDozerBean mapperDozerBean;

    /**
     * Gestion des messages.
     */
    private MessageSourceUtil messageSourceUtil;

    /**
     * Le dao des civilités.
     */
    private PersonneCiviliteDao personneCiviliteDao;

    /**
     * Le dao des professions.
     */
    private PersonneProfessionDao personneProfessionDao;

    /**
     * Le dao de la nature des personnes physique.
     */
    private PersonnePhysiqueNatureDao personnePhysiqueNatureDao;

    /**
     * Le dao de la nature des personnes physique.
     */
    private PersonneMoraleNatureDao personneMoraleNatureDao;

    /**
     * Le dao de la nature de la voie.
     */
    private TypeVoieDao typeVoieDao;

    /**
     * Le dao du code postal.
     */
    private CodePostalDao codePostalDao;

    /**
     * Le dao des villes.
     */
    private CommuneDao communeDao;

    /**
     * Le dao du code postal - commune.
     */
    private CodePostalCommuneDao codePostalCommuneDao;

    /**
     * le dao du département.
     */
    private DepartementDao departementDao;

    /**
     * Le dao pour la nature des téléphones.
     */
    private NatureTelephoneDao natureTelephoneDao;

    /**
     * Le dao pour les pays.
     */
    private PaysDao paysDao;

    /**
     * Le dao de la nature de l'adresse.
     */
    private AdresseNatureDao adresseNatureDao;

    /**
     * Le dao des caisses.
     */
    private CaisseDao caisseDao;

    /**
     * Le dao des régimes.
     */
    private CaisseRegimeDao caisseRegimeDao;

    /**
     * Le dao des csp.
     */
    private CspDao cspDao;

    /**
     * Le dao des segments.
     */
    private SegmentDao segmentDao;

    /**
     * Le dao sur la situation familiale.
     */
    private SituationFamilialeDao situationFamilialeDao;

    /**
     * Le dao sur le réseau.
     */
    private PersonneReseauDao personneReseauDao;

    /**
     * Le dao sur le statut de personne.
     */
    private PersonneStatutDao personneStatutDao;

    /**
     * Le dao des formes juridiques.
     */
    private FormeJuridiqueDao formeJuridiqueDao;

    /**
     * Le dao des types de relation.
     */
    private RelationTypeDao relationTypeDao;

    /**
     * Le dao des status.
     */
    private CampagneStatutDao campagneStatutDao;

    /**
     * Le dao des types.
     */
    private CampagneTypeDao campagneTypeDao;

    /**
     * Le dao des natures des actions.
     */
    private ActionNatureDao actionNatureDao;

    /**
     * Le dao des types des actions.
     */
    private ActionTypeDao actionTypeDao;

    /**
     * Le dao des natures des résultats de l'action.
     */
    private ActionNatureResultatDao actionNatureResultatDao;

    /**
     * Le dao des priorités des actions.
     */
    private ActionPrioriteDao actionPrioriteDao;

    /**
     * Le dao des statuts des actions.
     */
    private ActionStatutDao actionStatutDao;

    /**
     * Le dao des résultats des actions.
     */
    private ActionResultatDao actionResultatDao;

    /**
     * Le dao pour les objets des actions.
     */
    private ActionObjetDao actionObjetDao;

    /**
     * Le dao pour les sous objets des actions.
     */
    private ActionSousObjetDao actionSousObjetDao;

    /**
     * Le dao sur les ressources.
     */
    private RessourceDao ressourceDao;

    /**
     * Le dao sur les agences.
     */
    private AgenceDao agenceDao;

    /**
     * Le dao sur la dimension Fonction de la ressource.
     */
    private RessourceFonctionDao ressourceFonctionDao;

    /**
     * Le dao sur la dimension Service de la ressource.
     */
    private RessourceServiceDao ressourceServiceDao;

    /**
     * Le dao sur la dimension Etat de la ressource.
     */
    private RessourceEtatDao ressourceEtatDao;

    /**
     * Le dao sur les modeles d'email.
     */
    private ModeleEmailDao modeleEmailDao;

    /**
     * Classe utilitaire pour faire le pont entre les habilitations et les ressources Square.
     */
    private RessourceHabilitationUtil ressourceHabilitationUtil;

    /**
     * DAO pour les durées des actions.
     */
    private ActionDureeDao actionDureeDao;

    /**
     * Recherche des natures de personnes physiques par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherNaturePersonnePhysiqueParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<PersonnePhysiqueNature> result = personnePhysiqueNatureDao.rechercherPersonnePhysiqueNatureParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des natures de voies par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherNatureVoieParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<TypeVoie> result = typeVoieDao.rechercherTypeVoieParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des code Postaux par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherCodePostauxParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<CodePostal> result = codePostalDao.rechercherCodePostalParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des villes par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherCommuneParCriteres(CommuneCriteresRechercherDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<Commune> result = communeDao.rechercherCommuneParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des civilités par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherCiviliteParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<PersonneCivilite> result = personneCiviliteDao.rechercherCiviliteParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche de la nature des telephones par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherNatureTelephoneParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<NatureTelephone> result = natureTelephoneDao.rechercherNatureTelephoneParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des professions par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherProfessionParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<PersonneProfession> result = personneProfessionDao.rechercherProfessionParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des professions par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleBooleanDto> rechercherProfessionBooleanParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<PersonneProfession> result = personneProfessionDao.rechercherProfessionParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleBooleanDto.class);
    }

    /**
     * Recherche des pays par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherPaysParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<Pays> result = paysDao.rechercherPaysParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des pays par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleBooleanDto> rechercherPaysBooleanParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<Pays> result = paysDao.rechercherPaysParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleBooleanDto.class);
    }

    /**
     * Recherche des caisses par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<CaisseSimpleDto> rechercherCaisseParCriteres(DimensionCriteresRechercheCaisseDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<Caisse> result = caisseDao.rechercherCaisseParCriteres(criteres);
        return mapperDozerBean.mapList(result, CaisseSimpleDto.class);
    }

    /**
     * Recherche des régime par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherRegimeParCriteres(DimensionCriteresRechercheRegimeDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<CaisseRegime> result = caisseRegimeDao.rechercherRegimeParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des CSP par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherCSPParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<PersonneCSP> result = cspDao.rechercherCSPParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des Segments par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherSegmentParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<PersonneSegment> result = segmentDao.rechercherSegmentsParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des situations familialles par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherSitFamParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<PersonneSituationFamiliale> result = situationFamilialeDao.rechercherSituationFamilialeParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des Types d'adresses par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherTypeAdresseParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<AdresseNature> result = adresseNatureDao.rechercherAdresseNatureParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des départements par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDepartementCodeDto> rechercherDepartementParCriteres(DimensionCritereRechercheDepartementDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<Departement> result = departementDao.rechercherDepartementParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDepartementCodeDto.class);
    }

    /**
     * Recherche des réseaux de vente par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherReseauVenteParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<PersonneReseau> result = personneReseauDao.rechercherReseauParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des pays par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<PaysSimpleDto> rechercherSimplePaysParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<Pays> result = paysDao.rechercherPaysParCriteres(criteres);
        return mapperDozerBean.mapList(result, PaysSimpleDto.class);
    }

    /**
     * Recherche des statuts des personnes par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    public List<IdentifiantLibelleDto> rechercherPersonneStatutParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<PersonneStatut> result = personneStatutDao.rechercherStatutParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des codesPostaux - Commune par critères.
     * @param criteres les critères de recherche
     * @return la liste des codesPostaux - Commune
     */
    public List<IdentifiantLibelleCodePostalCommuneDto> rechercherCodesPostauxCommunes(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        return communeDao.rechercherCodesPostauxCommunes(criteres);
    }

    /**
     * Recherche des communes - codePostaux par critères.
     * @param criteres les critères de recherche
     * @return la liste des communes - code postaux
     */
    public List<IdentifiantLibelleCodePostalCommuneDto> rechercherCommunesCodesPostaux(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        return communeDao.rechercherCommunesCodesPostaux(criteres);
    }

    /**
     * Recherche des formes juridiques.
     * @param criteres les critères de recherche
     * @return la liste des formes juridiques
     */
    public List<IdentifiantLibelleDto> rechercherFormesJuridiques(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<FormeJuridique> result = formeJuridiqueDao.rechercherFormeJuridiqueParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * Recherche des types de relations.
     * @param criteres les critères de recherche
     * @return la liste des types de relations
     */
    public List<IdentifiantLibelleTypeRelationDto> rechercherTypesRelations(DimensionCritereRechercheTypeRelationDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<RelationType> result = relationTypeDao.rechercherRelationTypeParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleTypeRelationDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherNaturePersonneMoraleParCriteres(DimensionCriteresRechercheDto criteres) {
        final List<PersonneMoraleNature> result = personneMoraleNatureDao.rechercherNaturePersonneMoraleParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherNatureActionsParCriteres(DimensionCriteresRechercheDto criteres) {
        final List<ActionNature> result = actionNatureDao.rechercherNatureActionParCritere(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherTypesActionsParCriteres(DimensionCriteresRechercheDto criteres) {
        final List<ActionType> result = actionTypeDao.rechecherTypeActionParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherNatureResultatActionsParCriteres(ActionNatureResultatCriteresRechercheDto criteres) {
        final List<ActionNatureResultat> result = actionNatureResultatDao.rechercherActionNatureResultatParCritere(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherObetsActionsParCriteres(DimensionCritereRechercheObjetDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<ActionObjet> result = actionObjetDao.rechercherObjetActionParCritere(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherResultatActionsParCriteres(DimensionCritereRechercheResultatActionDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<ActionResultat> result = actionResultatDao.rechercherResultatActionParCritere(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherPrioriteActionsParCriteres(DimensionCriteresRechercheDto criteres) {
        final List<ActionPriorite> result = actionPrioriteDao.rechercherPrioriteActionParCritere(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherSousObetsActionsParCriteres(DimensionCritereRechercheSousObjetDto criteres) {
        final List<ActionSousObjet> result = actionSousObjetDao.rechercherSousObjetActionParCritere(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherStatutActionsParCriteres(DimensionCriteresRechercheDto criteres) {
        final List<ActionStatut> result = actionStatutDao.rechercherStatutActionParCritere(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherStatutsCampagnes(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<CampagneStatut> result = campagneStatutDao.rechercherCampagneStatutParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherTypesCampagnes(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        final List<CampagneType> result = campagneTypeDao.rechercherCampagneTypeParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherAgenceParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        // Les agences trouvées sous forme de dto.
        final List<Agence> result = agenceDao.rechercherAgenceParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdentifiantEidLibelleDto rechercherAgenceParEid(String eid) {
        if (eid == null) {
            throw new TechnicalException(messageSourceUtil.get(DimensionKeyUtil.MSG_ERREUR_PARAM_EID_NULL));
        }
        final Agence agence = agenceDao.rechercheAgenceParEid(eid);
        return mapperDozerBean.map(agence, IdentifiantEidLibelleDto.class);
    }

    @Override
    public IdentifiantLibelleDto rechercherAgenceParIdRessource(Long idRessource) {
        if (idRessource == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_RESSOURCE_INEXISTENT_EN_BD));
        }
        // l'agence recherchée
        final Ressource ressource = ressourceDao.rechercherRessourceParId(idRessource);
        if (ressource == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_RESSOURCE_INEXISTANTE));
        }
        if (ressource.getAgence() == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_AGENCE_INEXISTANTE));
        }

        final Long idAgence = ressource.getAgence().getId();

        final Agence agence = agenceDao.rechercheAgenceParId(idAgence);
        if (agence == null) {
            throw new BusinessException(messageSourceUtil.get(ActionKeyUtil.MESSAGE_ERREUR_ACTION_AGENCE_INEXISTANTE));
        }

        return mapperDozerBean.map(agence, IdentifiantLibelleDto.class);
    }

    @Override
    public List<DimensionRessourceDto> rechercherRessourceParCriteres(DimensionCriteresRechercheRessourceDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }

        // Les ressources trouvées sous forme de dto.
        final List<Ressource> result = ressourceDao.rechercherRessourceParCriteres(criteres);
        return mapperDozerBean.mapList(result, DimensionRessourceDto.class);
    }

    @Override
    public IdentifiantLibelleDepartementCodeDto rechercherDepartementParIdCommune(Long idCommune) {
        return mapperDozerBean.map(departementDao.rechercherDepartementParIdCommune(idCommune), IdentifiantLibelleDepartementCodeDto.class);
    }

    @Override
    public IdentifiantEidLibelleDto rechercherCSPParId(Long idCSP) {
        return mapperDozerBean.map(cspDao.recherchePersonneCSPParId(idCSP), IdentifiantEidLibelleDto.class);
    }

    @Override
    public CaisseDto rechercherCaisseParId(Long idCaisse) {
        return mapperDozerBean.map(caisseDao.rechercheCaisseParId(idCaisse), CaisseDto.class);
    }

    @Override
    public IdentifiantEidLibelleDto rechercherCiviliteParId(Long idCivilite) {
        return mapperDozerBean.map(personneCiviliteDao.rechercherCiviliteParId(idCivilite), IdentifiantEidLibelleDto.class);
    }

    @Override
    public IdentifiantEidLibelleDto rechercherRegimeParId(Long idRegime) {
        return mapperDozerBean.map(caisseRegimeDao.rechercheRegimeParId(idRegime), IdentifiantEidLibelleDto.class);
    }

    @Override
    public IdentifiantEidLibelleDto rechercherSitFamParId(Long idSitFam) {
        return mapperDozerBean.map(situationFamilialeDao.rechercherSituationFamiliale(idSitFam), IdentifiantEidLibelleDto.class);
    }

    @Override
    public IdentifiantEidLibelleDto rechercherTypeVoieParId(Long idTypeVoie) {
        return mapperDozerBean.map(typeVoieDao.rechercheTypeVoieParId(idTypeVoie), IdentifiantEidLibelleDto.class);
    }

    @Override
    public IdentifiantEidLibelleDto rechercherStatutParId(Long idStatut) {
        return mapperDozerBean.map(personneStatutDao.recherchePersonneStatutParId(idStatut), IdentifiantEidLibelleDto.class);
    }

    @Override
    public IdentifiantEidLibelleDto rechercherPaysParId(Long idPays) {
        return mapperDozerBean.map(paysDao.rechercherPaysParId(idPays), IdentifiantEidLibelleDto.class);
    }

    @Override
    public IdentifiantEidLibelleDto rechercherCodePostalParId(Long idCodePostal) {
        return mapperDozerBean.map(codePostalDao.rechercheCodePostalParId(idCodePostal), IdentifiantEidLibelleDto.class);
    }

    @Override
    public IdentifiantEidLibelleDto rechercherCommuneParId(Long idCommune) {
        return mapperDozerBean.map(communeDao.rechercheCommuneParId(idCommune), IdentifiantEidLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherRessourceFonctionParCriteres(DimensionCritereRechercheRessourceFonctionDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        // Les fonctions trouvées sous forme de dto.
        final List<RessourceFonction> result = ressourceFonctionDao.rechercherRessourceFonctionParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherRessourceServiceParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        // Les services trouvés sous forme de dto.
        final List<RessourceService> result = ressourceServiceDao.rechercherRessourceServiceParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherRessourceEtatParCriteres(DimensionCriteresRechercheDto criteres) {
        if (criteres == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_DTO_RECHERCHE_NULL));
        }
        // Les etats trouvés sous forme de dto.
        final List<RessourceEtat> result = ressourceEtatDao.rechercherRessourceEtatParCriteres(criteres);
        return mapperDozerBean.mapList(result, IdentifiantLibelleDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DimensionRessourceDto getRessourceConnectee() {
        return mapperDozerBean.map(ressourceHabilitationUtil.getUtilisateurConnecte(), DimensionRessourceDto.class);
    }

    @Override
    public CodePostalCommuneDto rechercherCodePostalCommuneParId(Long idCodePostalCommune) {
        return mapperDozerBean.map(codePostalCommuneDao.rechercheCodePostalCommuneParId(idCodePostalCommune), CodePostalCommuneDto.class);
    }

    @Override
    public List<CodePostalCommuneDto> rechercherCodesPostauxCommunesParCriteres(DimensionCritereRechercheCodePostalCommuneDto criteres) {
        return mapperDozerBean.mapList(codePostalCommuneDao.rechercherCodesPostauxCommunesParCriteres(criteres), CodePostalCommuneDto.class);
    }

    @Override
    public boolean hasRessourceConnecteeRole(Long idRole) {
        return ressourceHabilitationUtil.hasRessourceConnecteeRole(idRole);
    }

    @Override
    public boolean hasRessourceRole(Long idRessource, Long idRole) {
        return ressourceHabilitationUtil.hasRessourceRole(idRessource, idRole);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherListeModelesEmails(DimensionCriteresRechercheDto criteres) {
    	return modeleEmailDao.rechercherListeModelesEmails(criteres);
    }

    @Override
    public List<ModeleEmailDto> rechercherModelesEmails(DimensionCriteresRechercheDto criteres) {
    	 return mapperDozerBean.mapList(modeleEmailDao.rechercherModelesEmailParCriteres(criteres), ModeleEmailDto.class);
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherDureeActionParCriteres(DimensionCriteresRechercheDto criteres) {
        final List<ActionDuree> listeDurees = actionDureeDao.rechercherDureeActionParCriteres(criteres);
        return mapperDozerBean.mapList(listeDurees, IdentifiantLibelleDto.class);
    }

    /**
     * Modifie mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Modifie personneCiviliteDao.
     * @param personneCiviliteDao la nouvelle valeur de personneCiviliteDao
     */
    public void setPersonneCiviliteDao(PersonneCiviliteDao personneCiviliteDao) {
        this.personneCiviliteDao = personneCiviliteDao;
    }

    /**
     * Modifie personneProfessionDao.
     * @param personneProfessionDao la nouvelle valeur de personneProfessionDao
     */
    public void setPersonneProfessionDao(PersonneProfessionDao personneProfessionDao) {
        this.personneProfessionDao = personneProfessionDao;
    }

    /**
     * Modifie personnePhysiqueNatureDao.
     * @param personnePhysiqueNatureDao la nouvelle valeur de personnePhysiqueNatureDao
     */
    public void setPersonnePhysiqueNatureDao(PersonnePhysiqueNatureDao personnePhysiqueNatureDao) {
        this.personnePhysiqueNatureDao = personnePhysiqueNatureDao;
    }

    /**
     * Modifie typeVoieDao.
     * @param typeVoieDao la nouvelle valeur de typeVoieDao
     */
    public void setTypeVoieDao(TypeVoieDao typeVoieDao) {
        this.typeVoieDao = typeVoieDao;
    }

    /**
     * Modifie codePostalDao.
     * @param codePostalDao la nouvelle valeur de codePostalDao
     */
    public void setCodePostalDao(CodePostalDao codePostalDao) {
        this.codePostalDao = codePostalDao;
    }

    /**
     * Modifie communeDao.
     * @param communeDao la nouvelle valeur de communeDao
     */
    public void setCommuneDao(CommuneDao communeDao) {
        this.communeDao = communeDao;
    }

    /**
     * Modifie departementDao.
     * @param departementDao la nouvelle valeur de departementDao
     */
    public void setDepartementDao(DepartementDao departementDao) {
        this.departementDao = departementDao;
    }

    /**
     * Modifie natureTelephoneDao.
     * @param natureTelephoneDao la nouvelle valeur de natureTelephoneDao
     */
    public void setNatureTelephoneDao(NatureTelephoneDao natureTelephoneDao) {
        this.natureTelephoneDao = natureTelephoneDao;
    }

    /**
     * Modifie paysDao.
     * @param paysDao la nouvelle valeur de paysDao
     */
    public void setPaysDao(PaysDao paysDao) {
        this.paysDao = paysDao;
    }

    /**
     * Modifie adresseNatureDao.
     * @param adresseNatureDao la nouvelle valeur de adresseNatureDao
     */
    public void setAdresseNatureDao(AdresseNatureDao adresseNatureDao) {
        this.adresseNatureDao = adresseNatureDao;
    }

    /**
     * Modifie caisseDao.
     * @param caisseDao la nouvelle valeur de caisseDao
     */
    public void setCaisseDao(CaisseDao caisseDao) {
        this.caisseDao = caisseDao;
    }

    /**
     * Modifie caisseRegimeDao.
     * @param caisseRegimeDao la nouvelle valeur de caisseRegimeDao
     */
    public void setCaisseRegimeDao(CaisseRegimeDao caisseRegimeDao) {
        this.caisseRegimeDao = caisseRegimeDao;
    }

    /**
     * Modifie cspDao.
     * @param cspDao la nouvelle valeur de cspDao
     */
    public void setCspDao(CspDao cspDao) {
        this.cspDao = cspDao;
    }

    /**
     * Modifie segmentDao.
     * @param segmentDao la nouvelle valeur de segmentDao
     */
    public void setSegmentDao(SegmentDao segmentDao) {
        this.segmentDao = segmentDao;
    }

    /**
     * Modifie situationFamilialeDao.
     * @param situationFamilialeDao la nouvelle valeur de situationFamilialeDao
     */
    public void setSituationFamilialeDao(SituationFamilialeDao situationFamilialeDao) {
        this.situationFamilialeDao = situationFamilialeDao;
    }

    /**
     * Modifie la valeur de personneReseauDao.
     * @param personneReseauDao the personneReseauDao to set
     */
    public void setPersonneReseauDao(PersonneReseauDao personneReseauDao) {
        this.personneReseauDao = personneReseauDao;
    }

    /**
     * Modifie la valeur de personneStatutDao.
     * @param personneStatutDao the personneStatutDao to set
     */
    public void setPersonneStatutDao(PersonneStatutDao personneStatutDao) {
        this.personneStatutDao = personneStatutDao;
    }

    /**
     * Modifie messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Modifie formeJuridiqueDao.
     * @param formeJuridiqueDao la nouvelle valeur de formeJuridiqueDao
     */
    public void setFormeJuridiqueDao(FormeJuridiqueDao formeJuridiqueDao) {
        this.formeJuridiqueDao = formeJuridiqueDao;
    }

    /**
     * Modifie relationTypeDao.
     * @param relationTypeDao la nouvelle valeur de relationTypeDao
     */
    public void setRelationTypeDao(RelationTypeDao relationTypeDao) {
        this.relationTypeDao = relationTypeDao;
    }

    /**
     * Modifie la nature de la personne morale.
     * @param personneMoraleNatureDao the personneMoraleNatureDao to set
     */
    public void setPersonneMoraleNatureDao(PersonneMoraleNatureDao personneMoraleNatureDao) {
        this.personneMoraleNatureDao = personneMoraleNatureDao;
    }

    /**
     * Modifie la valeur de actionNatureDao.
     * @param actionNatureDao the actionNatureDao to set
     */
    public void setActionNatureDao(ActionNatureDao actionNatureDao) {
        this.actionNatureDao = actionNatureDao;
    }

    /**
     * Modifie la valeur de actionTypeDao.
     * @param actionTypeDao the actionTypeDao to set
     */
    public void setActionTypeDao(ActionTypeDao actionTypeDao) {
        this.actionTypeDao = actionTypeDao;
    }

    /**
     * Modifie la valeur de actionNatureResultatDao.
     * @param actionNatureResultatDao the actionNatureResultatDao to set
     */
    public void setActionNatureResultatDao(ActionNatureResultatDao actionNatureResultatDao) {
        this.actionNatureResultatDao = actionNatureResultatDao;
    }

    /**
     * Modifie campagneStatutDao.
     * @param campagneStatutDao la nouvelle valeur de campagneStatutDao
     */
    public void setCampagneStatutDao(CampagneStatutDao campagneStatutDao) {
        this.campagneStatutDao = campagneStatutDao;
    }

    /**
     * Modifie campagneTypeDao.
     * @param campagneTypeDao la nouvelle valeur de campagneTypeDao
     */
    public void setCampagneTypeDao(CampagneTypeDao campagneTypeDao) {
        this.campagneTypeDao = campagneTypeDao;
    }

    /**
     * Modifie la valeur de actionPrioriteDao.
     * @param actionPrioriteDao the actionPrioriteDao to set
     */
    public void setActionPrioriteDao(ActionPrioriteDao actionPrioriteDao) {
        this.actionPrioriteDao = actionPrioriteDao;
    }

    /**
     * Modifie la valeur de actionStatutDao.
     * @param actionStatutDao the actionStatutDao to set
     */
    public void setActionStatutDao(ActionStatutDao actionStatutDao) {
        this.actionStatutDao = actionStatutDao;
    }

    /**
     * Modifie la valeur de actionResultatDao.
     * @param actionResultatDao the actionResultatDao to set
     */
    public void setActionResultatDao(ActionResultatDao actionResultatDao) {
        this.actionResultatDao = actionResultatDao;
    }

    /**
     * Modifie la valeur de actionObjetDao.
     * @param actionObjetDao the actionObjetDao to set
     */
    public void setActionObjetDao(ActionObjetDao actionObjetDao) {
        this.actionObjetDao = actionObjetDao;
    }

    /**
     * Modifie la valeur de actionSousObjetDao.
     * @param actionSousObjetDao the actionSousObjetDao to set
     */
    public void setActionSousObjetDao(ActionSousObjetDao actionSousObjetDao) {
        this.actionSousObjetDao = actionSousObjetDao;
    }

    /**
     * Modifie la valeur de ressourceDao.
     * @param ressourceDao the ressourceDao to set
     */
    public void setRessourceDao(RessourceDao ressourceDao) {
        this.ressourceDao = ressourceDao;
    }

    /**
     * Modifie la valeur de agenceDao.
     * @param agenceDao the agenceDao to set
     */
    public void setAgenceDao(AgenceDao agenceDao) {
        this.agenceDao = agenceDao;
    }

    /**
     * Modifie la valeur de ressourceFonctionDao.
     * @param ressourceFonctionDao the ressourceFonctionDao to set
     */
    public void setRessourceFonctionDao(RessourceFonctionDao ressourceFonctionDao) {
        this.ressourceFonctionDao = ressourceFonctionDao;
    }

    /**
     * Modifie la valeur de ressourceServiceDao.
     * @param ressourceServiceDao the ressourceServiceDao to set
     */
    public void setRessourceServiceDao(RessourceServiceDao ressourceServiceDao) {
        this.ressourceServiceDao = ressourceServiceDao;
    }

    /**
     * Modifie la valeur de ressourceEtatDao.
     * @param ressourceEtatDao the ressourceEtatDao to set
     */
    public void setRessourceEtatDao(RessourceEtatDao ressourceEtatDao) {
        this.ressourceEtatDao = ressourceEtatDao;
    }

    /**
     * Setter function.
     * @param ressourceHabilitationUtil the ressourceHabilitationUtil to set
     */
    public void setRessourceHabilitationUtil(RessourceHabilitationUtil ressourceHabilitationUtil) {
        this.ressourceHabilitationUtil = ressourceHabilitationUtil;
    }

    /**
     * Définit la valeur de codePostalCommuneDao.
     * @param codePostalCommuneDao the codePostalCommuneDao to set
     */
    public void setCodePostalCommuneDao(CodePostalCommuneDao codePostalCommuneDao) {
        this.codePostalCommuneDao = codePostalCommuneDao;
    }

    /**
     * Définit la valeur de modeleEmailDao.
     * @param modeleEmailDao the modeleEmailDao to set
     */
    public void setModeleEmailDao(ModeleEmailDao modeleEmailDao) {
        this.modeleEmailDao = modeleEmailDao;
    }

    /**
     * Set the value of actionDureeDao.
     * @param actionDureeDao the actionDureeDao to set
     */
    public void setActionDureeDao(ActionDureeDao actionDureeDao) {
        this.actionDureeDao = actionDureeDao;
    }

}
