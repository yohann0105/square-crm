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

import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_ADRESSE_TROP_LONGUE;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_FORMAT_NUM_VOIE_INCORRECT;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_ADRESSE_ADRESSE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_ADRESSE_CODEPOSTAL_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_ADRESSE_PAYS_NULL;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.core.dao.interfaces.AdresseNatureDao;
import com.square.core.dao.interfaces.CodePostalCommuneDao;
import com.square.core.dao.interfaces.PaysDao;
import com.square.core.dao.interfaces.PersonneMoraleDao;
import com.square.core.dao.interfaces.PersonneMoraleNatureDao;
import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.dao.interfaces.TypeVoieDao;
import com.square.core.model.Adresse;
import com.square.core.model.CodePostalCommune;
import com.square.core.model.Pays;
import com.square.core.model.PersonneAttribution;
import com.square.core.model.PersonneMorale;
import com.square.core.model.PersonneMoraleNature;
import com.square.core.model.TypeVoie;
import com.square.core.model.Ressources.Agence;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.PersonneMoralCriteresRechercheDto;
import com.square.core.model.dto.PersonneMoraleDto;
import com.square.core.model.dto.PersonneMoraleRechercheDto;
import com.square.core.model.dto.PersonneMoraleSimpleDto;
import com.square.core.model.dto.RapportDto;
import com.square.core.model.exception.ControleIntegriteException;
import com.square.core.model.util.ResultatPaginationFullText;
import com.square.core.service.interfaces.PersonneMoraleService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.AgenceHabilitationUtil;
import com.square.core.util.PersonneMoraleKeyUtil;
import com.square.core.util.PersonnePhysiqueKeyUtil;
import com.square.core.util.poi.DocumentXls;
import com.square.core.util.validation.RapportUtil;
import com.square.core.util.validation.ValidationExpressionProp;
import com.square.core.util.validation.ValidationExpressionUtil;

/**
 * Implémentation des services des personnes morales.
 * @author cblanchard - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class PersonneMoraleServiceImplementation implements PersonneMoraleService {

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    /** Dao sur la personne morale. */
    PersonneMoraleDao personneMoraleDao;

    /** Dao pour les natures de personne morale. */
    private PersonneMoraleNatureDao personneMoraleNatureDao;

    /** Dao pour les ressources. */
    private RessourceDao ressourceDao;

    /** Dao sur les codes postaux - communes. */
    private CodePostalCommuneDao codePostalCommuneDao;

    /** Le dao pour les pays. */
    private PaysDao paysDao;

    /** Le dao de la nature de la voie. */
    private TypeVoieDao typeVoieDao;

    /** Le dao de la nature de l'adresse. */
    private AdresseNatureDao adresseNatureDao;

    /** Habilitation des agences . */
    private AgenceHabilitationUtil agenceHabilitationUtil;

    /** Classe utilitaire pour la vérification. */
    private ValidationExpressionUtil validationExpressionUtil;

    /** Le logger. */
    private static Logger logger = Logger.getLogger(PersonneMoraleServiceImplementation.class);

    /** Service de mapping. */
    private SquareMappingService squareMappingService;

    private int paginationExportRecherche;

    @Override
    public RemotePagingResultsDto<PersonneMoraleRechercheDto> rechercherPersonneMoraleParCriteres(
        RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto> criteres) {
        try {
            if (criteres == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SEARCH_DTO_NULL));
            }

            final ResultatPaginationFullText<PersonneMorale> resultatPagination = personneMoraleDao.rechercherPersonneMoraleParCriteres(criteres);
            logger.info("Demande de recherche en FULL TEXT NOMBRE DE RESULTAT " + resultatPagination.getListeResultats().size() + "/"
                + resultatPagination.getNombreTotalDeResultat());
            final List<PersonneMorale> resultPersonne = resultatPagination.getListeResultats();

            // Mapping PersonneMorale -> PersonneMoraleRecherche
            final List<PersonneMoraleRechercheDto> resultDto = new ArrayList<PersonneMoraleRechercheDto>();

            for (PersonneMorale personneMorale : resultPersonne) {
                final PersonneMoraleRechercheDto personneMoraleRecherche = mapperDozerBean.map(personneMorale, PersonneMoraleRechercheDto.class);

                personneMoraleRecherche.setNumeroEntreprise(personneMorale.getNum());
                if (personneMorale.getAdresses() != null) {
                    // Récupération de l'adresse principale du client.
                    for (Adresse adr : personneMorale.getAdresses()) {
                        // Adresse Principale.
                        if (adr.getNature() != null && adr.getNature().getId().equals(squareMappingService.getIdNatureAdressePrincipale())) {
                            // Récupération du code postal - commune
                            if (adr.getCodePostalCommune() != null) {
                                final CodePostalCommuneDto codePostalCommuneDto = mapperDozerBean.map(adr.getCodePostalCommune(), CodePostalCommuneDto.class);
                                personneMoraleRecherche.setCodePostalCommune(codePostalCommuneDto);
                            }
                        }
                    }
                }
                // TODO Traitement du doublon

                resultDto.add(personneMoraleRecherche);
            }
            final RemotePagingResultsDto<PersonneMoraleRechercheDto> result = new RemotePagingResultsDto<PersonneMoraleRechercheDto>();
            result.setListResults(resultDto);
            result.setTotalResults(resultatPagination.getNombreTotalDeResultat());
            return result;
        }
        catch (ParseException e) {
            logger.error(e);
            throw new BusinessException(messageSourceUtil.get(PersonneMoraleKeyUtil.ERREUR_CRITERES_RECHERCHE_INVALIDE), e);
        }
    }

    /**
     * Recherche d'une personne morale par son identifiant.
     * @param identifiant l'identifiant de la personne
     * @return la personne morale trouvée
     * @author mlamine - SCUB
     */
    public PersonneMoraleDto recherchePersonneMoraleParId(Long identifiant) {
        final PersonneMorale personne = personneMoraleDao.rechercherPersonneMoraleParId(identifiant);
        final PersonneMoraleDto personneDto = mapperDozerBean.map(personne, PersonneMoraleDto.class);
        return personneDto;
    }

    @Override
    public List<PersonneMoraleDto> rechercherPersonnesMoralesParRequete(String requete) {
        final List<PersonneMorale> personnes = personneMoraleDao.recherchePersonnesMoralesParRequete(requete);
        return mapperDozerBean.mapList(personnes, PersonneMoraleDto.class);
    }

    /**
     * Recherche d'une personne morale simple par son identifiant.
     * @param identifiant l'identifiant de la personne
     * @return la personne morale simple trouvée
     * @author mlamine - SCUB
     */
    public PersonneMoraleSimpleDto recherchepersonneMoraleSimpleParId(Long identifiant) {
        if (identifiant == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneMoraleKeyUtil.MESSAGE_ERREUR_PERSONNE_MORALE_NULL));
        }
        final PersonneMoraleSimpleDto personne = personneMoraleDao.rechercherPersonneMoraleSimpleParId(identifiant);
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneMoraleKeyUtil.MESSAGE_ERREUR_PERSONNE_MORALE_NULL) + "id =>" + identifiant);
        }
        return personne;
    }

    @Override
    public FichierDto exporterRecherchePersonneMoraleParCriteres(RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto> criteres) {
        final String nomFichier = "exportRecherchePersonnesMorales.xls";

        final String[] entetes = new String[] {"Numéro d'entreprise", "Raison sociale", "Nature", "Code Postal", "Ville"};
        final Integer[] entetesWidth = new Integer[] {5500, 16500, 5500, 5500, 5500};

        final DocumentXls documentXls = new DocumentXls(nomFichier, entetes, entetesWidth);

        // on recupere le resultat de la recherche par pagination
        int start = 0;
        RemotePagingResultsDto<PersonneMoraleRechercheDto> resultats;
        do {
            // on redefinit la pagination
            criteres.setFirstResult(start);
            criteres.setMaxResult(paginationExportRecherche);
            resultats = rechercherPersonneMoraleParCriteres(criteres);
            start += resultats.getListResults().size();
            // on integre les resultats au fichier
            for (PersonneMoraleRechercheDto personne : resultats.getListResults()) {
                // Récupération du code postal et de la commune
                String codePostal = null;
                String commune = null;
                if (personne.getCodePostalCommune() != null) {
                    if (personne.getCodePostalCommune().getCodePostal() != null) {
                        codePostal = personne.getCodePostalCommune().getCodePostal().getLibelle();
                    }
                    if (personne.getCodePostalCommune().getCommune() != null) {
                        commune = personne.getCodePostalCommune().getCommune().getLibelle();
                    }
                }
                final String[] infosLignes =
                    new String[] {personne.getNumeroEntreprise(), personne.getRaisonSociale(),
                        personne.getNature() != null ? personne.getNature().getLibelle() : "", codePostal != null ? codePostal : "",
                        commune != null ? commune : ""};
                documentXls.ajouterLigne(infosLignes);
            }
        }
        while (start < resultats.getTotalResults());

        return documentXls.cloturerDocument();
    }

    @Override
    public PersonneMoraleDto creerPersonneMorale(PersonneMoraleDto personneDto, AdresseDto adresseDto) {
        final RapportDto rapport = new RapportDto();

        // Controle des champs obligatoires
        if (personneDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonneMoraleKeyUtil.MESSAGE_ERREUR_PERSONNE_MORALE_NULL));
        }

        // Controle sur l'adresse
        if (adresseDto == null) {
            throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_ADRESSE_DTO_NULL));
        }

        validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(
            PersonneMoraleKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_MORALE_RAISON_SOCIALE_VIDE),
            personneDto.getRaisonSociale() == null ? null : personneDto.getRaisonSociale(), PersonneMoraleDto.class.getSimpleName() + ".raisonSociale");
        final ValidationExpressionProp[] propsPersonneMorale =
            new ValidationExpressionProp[] {
                new ValidationExpressionProp("raisonSociale", null,
                    messageSourceUtil.get(PersonneMoraleKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_MORALE_RAISON_SOCIALE_VIDE))};
        validationExpressionUtil.verifierSiVide(rapport, personneDto, propsPersonneMorale);


        // La suite des vérifications
        validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_ADRESSE_PAYS_NULL),
            adresseDto.getPays() == null ? null : adresseDto.getPays().getIdentifiant(), AdresseDto.class.getSimpleName() + ".pays");

        // Vérification du format du numéro de voie
        if (adresseDto.getNumVoie() != null && !"".equals(adresseDto.getNumVoie())) {
            validationExpressionUtil.verifierFormatNumVoie(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_FORMAT_NUM_VOIE_INCORRECT), adresseDto
                    .getNumVoie(), AdresseDto.class.getSimpleName() + ".numVoie");
        }

        // Vérification de la longueur de l'adresse
        validationExpressionUtil.verifierAdresse(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_ADRESSE_TROP_LONGUE), adresseDto, AdresseDto.class
                .getSimpleName() + ".nomVoie");

        // Controles complémentaires si france
        if (adresseDto.getPays() != null && squareMappingService.getIdPaysFrance().equals(adresseDto.getPays().getIdentifiant())) {
            final ValidationExpressionProp[] propsAddresse =
                new ValidationExpressionProp[] {new ValidationExpressionProp("nomVoie", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_ADRESSE_ADRESSE_NULL))};
            validationExpressionUtil.verifierSiVide(rapport, adresseDto, propsAddresse);
            validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_ADRESSE_CODEPOSTAL_NULL), adresseDto
                    .getCodePostalCommune() == null ? null : adresseDto.getCodePostalCommune().getIdentifiant(), AdresseDto.class.getSimpleName()
                + ".codePostal");
        }

        // Les controles sont terminés, suite du processus :
        if (rapport.getEnErreur()) {
            RapportUtil.logRapport(rapport, logger);
            throw new ControleIntegriteException(rapport);
        }

        final PersonneMorale personneACreer = new PersonneMorale();
        personneACreer.setRaisonSociale(personneDto.getRaisonSociale().toLowerCase());
        personneACreer.setDateCreation(Calendar.getInstance());
        if (personneDto.getNature() != null) {
            final PersonneMoraleNature naturePersonneMorale =
                personneMoraleNatureDao.rechercherNaturePersonneMoraleParId(personneDto.getNature().getIdentifiant());
            personneACreer.setNaturePersonneMorale(naturePersonneMorale);
        }
        if (personneDto.getCreateur() != null) {
            final Ressource ressource = ressourceDao.rechercherRessourceParId(personneDto.getCreateur().getIdentifiant());
            personneACreer.setRessource(ressource);
        }
        final PersonneAttribution attribution = new PersonneAttribution();
        CodePostalCommune codePostalCommune = null;
        if (adresseDto != null && squareMappingService.getIdPaysFrance().equals(adresseDto.getPays().getIdentifiant())) {
            if (adresseDto.getCodePostalCommune() != null && adresseDto.getCodePostalCommune().getIdentifiant() != null) {
                // On vérifie que l'adresse a un code postal - commune et que le code postal - commune existe en base de données.
                codePostalCommune = codePostalCommuneDao.rechercheCodePostalCommuneParId(adresseDto.getCodePostalCommune().getIdentifiant());
                if (codePostalCommune == null) {
                    throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_CODEPOSTAL_COMMUNE_INEXISTANT_EN_BD));
                }
            }
        }
        Agence agence = null;
        if (personneDto.getCommercial() == null && codePostalCommune != null && codePostalCommune.getCodePostal() != null
            && codePostalCommune.getCodePostal().getCodePostal() != null && !"".equals(codePostalCommune.getCodePostal().getCodePostal().trim())
            && codePostalCommune.getCodePostal().getCodePostal().trim().length() != 2) {
            // on recupere le commercial suivant le code postal
            agence = agenceHabilitationUtil.getAgenceByCodePostal(codePostalCommune.getCodePostal().getCodePostal().trim());
        } else if (personneDto.getCommercial() != null) {
            final Ressource ressource = ressourceDao.rechercherRessourceParId(personneDto.getCommercial().getIdentifiant());
            attribution.setRessource(ressource);
            agence = ressource.getAgence();
        } else {
            throw new BusinessException("Erreur aucun responsable trouvé pour la personne");
        }
        attribution.setAgence(agence);
        personneACreer.setAttribution(attribution);

        // Création de l'adresse
        Pays paysAdresse = null;
        if (adresseDto != null && adresseDto.getPays() != null) {
            paysAdresse = paysDao.rechercherPaysParId(adresseDto.getPays().getIdentifiant());
            if (paysAdresse == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PAYS_INEXISTENT_EN_BD));
            }
        }
        TypeVoie typeVoie = null;
        if (adresseDto != null && adresseDto.getTypeVoie() != null && adresseDto.getTypeVoie().getIdentifiant() != null) {
            typeVoie = typeVoieDao.rechercheTypeVoieParId(adresseDto.getTypeVoie().getIdentifiant());
            if (typeVoie == null) {
                throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_TYPEVOIE_INEXISTANT));
            }
        }
        final Adresse adresse = mapperDozerBean.map(adresseDto, Adresse.class);
        adresse.setTypeVoie(typeVoie);
        adresse.setDateDebut(Calendar.getInstance());
        adresse.setCodePostalCommune(codePostalCommune);
        adresse.setPays(paysAdresse);
        adresse.setNature(adresseNatureDao.rechercheAdresseNatureParId(squareMappingService.getIdNatureAdressePrincipale()));
        adresse.setDateCreation(Calendar.getInstance());
        personneACreer.addAdresse(adresse);

        // Création de la personne
        personneMoraleDao.creerPersonneMorale(personneACreer);

        return recherchePersonneMoraleParId(personneACreer.getId());
    }

    @Override
    public void indexerListePersonnesMorales(List<Long> listeIdsPersonnesAIndexer) {
        if (listeIdsPersonnesAIndexer != null && listeIdsPersonnesAIndexer.size() > 0) {
            for (Long idPersonne : listeIdsPersonnesAIndexer) {
                logger.debug("Indexation de Personne Morale id = " + idPersonne);
                final PersonneMorale personneMorale = personneMoraleDao.rechercherPersonneMoraleParId(idPersonne);
                if (personneMorale == null) {
                    throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PERSONNE_INEXISTANTE));
                }
                // Modification d'un champ pour forcer la réindexation
                personneMorale.setDateModification(Calendar.getInstance());
            }
        }
    }

    /**
     * Modifie la valeur de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Modifie la valeur de messageSourceUtil.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Modifie la valeur de personneMoraleDao.
     * @param personneMoraleDao the personneMoraleDao to set
     */
    public void setPersonneMoraleDao(PersonneMoraleDao personneMoraleDao) {
        this.personneMoraleDao = personneMoraleDao;
    }

    /**
     * Modifie le service de mapping.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Set the paginationExportRecherche value.
     * @param paginationExportRecherche the paginationExportRecherche to set
     */
    public void setPaginationExportRecherche(int paginationExportRecherche) {
        this.paginationExportRecherche = paginationExportRecherche;
    }

    /**
     * Définit la valeur de personneMoraleNatureDao.
     * @param personneMoraleNatureDao la nouvelle valeur de personneMoraleNatureDao
     */
    public void setPersonneMoraleNatureDao(PersonneMoraleNatureDao personneMoraleNatureDao) {
        this.personneMoraleNatureDao = personneMoraleNatureDao;
    }

    /**
     * Définit la valeur de ressourceDao.
     * @param ressourceDao la nouvelle valeur de ressourceDao
     */
    public void setRessourceDao(RessourceDao ressourceDao) {
        this.ressourceDao = ressourceDao;
    }

    /**
     * Définit la valeur de codePostalCommuneDao.
     * @param codePostalCommuneDao la nouvelle valeur de codePostalCommuneDao
     */
    public void setCodePostalCommuneDao(CodePostalCommuneDao codePostalCommuneDao) {
        this.codePostalCommuneDao = codePostalCommuneDao;
    }

    /**
     * Définit la valeur de agenceHabilitationUtil.
     * @param agenceHabilitationUtil la nouvelle valeur de agenceHabilitationUtil
     */
    public void setAgenceHabilitationUtil(AgenceHabilitationUtil agenceHabilitationUtil) {
        this.agenceHabilitationUtil = agenceHabilitationUtil;
    }

    /**
     * Définit la valeur de paysDao.
     * @param paysDao la nouvelle valeur de paysDao
     */
    public void setPaysDao(PaysDao paysDao) {
        this.paysDao = paysDao;
    }

    /**
     * Définit la valeur de typeVoieDao.
     * @param typeVoieDao la nouvelle valeur de typeVoieDao
     */
    public void setTypeVoieDao(TypeVoieDao typeVoieDao) {
        this.typeVoieDao = typeVoieDao;
    }

    /**
     * Définit la valeur de adresseNatureDao.
     * @param adresseNatureDao la nouvelle valeur de adresseNatureDao
     */
    public void setAdresseNatureDao(AdresseNatureDao adresseNatureDao) {
        this.adresseNatureDao = adresseNatureDao;
    }

    /**
     * Définit la valeur de validationExpressionUtil.
     * @param validationExpressionUtil la nouvelle valeur de validationExpressionUtil
     */
    public void setValidationExpressionUtil(ValidationExpressionUtil validationExpressionUtil) {
        this.validationExpressionUtil = validationExpressionUtil;
    }
}
