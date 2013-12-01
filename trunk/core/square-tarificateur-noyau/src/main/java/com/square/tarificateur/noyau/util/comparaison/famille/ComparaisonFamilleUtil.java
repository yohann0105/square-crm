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
package com.square.tarificateur.noyau.util.comparaison.famille;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.tarificateur.noyau.dto.personne.BeneficiaireTarificateurDto;
import com.square.tarificateur.noyau.dto.personne.PersonneTarificateurDto;
import com.square.tarificateur.noyau.model.personne.Beneficiaire;
import com.square.tarificateur.noyau.model.personne.Personne;
import com.square.tarificateur.noyau.service.interfaces.TarificateurSquareMappingService;
import com.square.tarificateur.noyau.util.MessageKeyUtil;

/**
 * Classe utilitaire permettant la comparaison de famille.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ComparaisonFamilleUtil {

    /** MessageSourceUtil. */
    private MessageSourceUtil messageSourceUtil;

    /** Liste de Custom Converter pour BeanUtils. */
    private Map<Class<? extends Object>, Converter> customConverter;

    /** Liste des propriétés de personne à comparer. */
    private List<String> listeProprietesPersonneAComparer;

    /** Liste des propriétés d'info santé à comparer. */
    private List<String> listeProprietesInfoSanteAComparer;

    /** Liste des propriétés d'adresses à comparer. */
    private List<String> listeProprietesAdresseAComparer;

    /** Logger. */
    private Logger logger = Logger.getLogger(ComparaisonFamilleUtil.class);

    /**
     * Constructeur.
     * @param tarificateurSquareMappingService le service de mapping
     * @param messageSourceUtil le message source
     * @param customConverter les custom converters
     */
    public ComparaisonFamilleUtil(TarificateurSquareMappingService tarificateurSquareMappingService, MessageSourceUtil messageSourceUtil,
        Map<Class<? extends Object>, Converter> customConverter) {
        this.messageSourceUtil = messageSourceUtil;
        this.customConverter = customConverter;
        this.listeProprietesPersonneAComparer = tarificateurSquareMappingService.getListeProprietesPersonneAComparer();
        this.listeProprietesAdresseAComparer = tarificateurSquareMappingService.getListeProprietesAdresseAComparer();
        this.listeProprietesInfoSanteAComparer = tarificateurSquareMappingService.getListeProprietesInfoSanteAComparer();
    }

    /**
     * Compare les champs des 2 familles passées en paramètres.
     * @param personnePrincipaleDto le DTO de la personne principale
     * @param personnePrincipale le modèle de la personne principale
     * @return true si les familles sont identiques, false sinon
     */
    public boolean isFamillesIdentiques(PersonneTarificateurDto personnePrincipaleDto, Personne personnePrincipale) {
        // Réinitialisation des converters pour BeanUtils
        resetConverter();

        // Comparaison de la personne principale
        if (!isPersonnesIdentiques(personnePrincipaleDto, personnePrincipale)) {
            return false;
        }

        // Récupération des bénéficiaires (clé : eidPersonne, valeur : bénéficiaire)
        final Map<Long, BeneficiaireTarificateurDto> mapBeneficiairesDto = new HashMap<Long, BeneficiaireTarificateurDto>();
        final Map<Long, Beneficiaire> mapBeneficiaires = new HashMap<Long, Beneficiaire>();
        final List<Long> listeEidBeneficiairesDto = new ArrayList<Long>();
        final List<Long> listeEidBeneficiaires = new ArrayList<Long>();
        // Parcours de la liste des bénéficiaires pour remplissage des maps
        if (personnePrincipaleDto.getListeBeneficiaires() != null && !personnePrincipaleDto.getListeBeneficiaires().isEmpty()) {
            for (BeneficiaireTarificateurDto beneficiaireDto : personnePrincipaleDto.getListeBeneficiaires()) {
                if (beneficiaireDto.getEidPersonne() == null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_EID_PERSONNE_BENEFICIAIRE_NON_RENSEIGNE));
                }
                mapBeneficiairesDto.put(beneficiaireDto.getEidPersonne(), beneficiaireDto);
                listeEidBeneficiairesDto.add(beneficiaireDto.getEidPersonne());
            }
        }
        if (personnePrincipale.getListeBeneficiaires() != null && !personnePrincipale.getListeBeneficiaires().isEmpty()) {
            for (Beneficiaire beneficiaire : personnePrincipale.getListeBeneficiaires()) {
                if (beneficiaire.getPersonneCible().getEidPersonne() == null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_EID_PERSONNE_BENEFICIAIRE_NON_RENSEIGNE));
                }
                mapBeneficiaires.put(beneficiaire.getPersonneCible().getEidPersonne(), beneficiaire);
                listeEidBeneficiaires.add(beneficiaire.getPersonneCible().getEidPersonne());
            }
        }

        // Comparaison du nombre et des eid des bénéficiaires
        if (!(listeEidBeneficiairesDto.containsAll(listeEidBeneficiaires) && listeEidBeneficiaires.containsAll(listeEidBeneficiairesDto))) {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_LISTE_BENEFICIAIRE_DIFFERE_DTO_MODEL,
            		 new String[] {listeEidBeneficiairesDto.toString(), listeEidBeneficiaires.toString()}));
            return false;
        }

        // Comparaison des champs de chacun des bénéficiaires
        for (Long eidBeneficiaire : listeEidBeneficiairesDto) {
            final BeneficiaireTarificateurDto beneficiaireDto = mapBeneficiairesDto.get(eidBeneficiaire);
            final Beneficiaire beneficiaire = mapBeneficiaires.get(eidBeneficiaire);
            // Comparaison des liens familiaux
            if (!beneficiaire.getLienFamilial().getId().equals(beneficiaireDto.getIdLienFamilial())) {
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_LIEN_FAMILIAL_INCOHERENT));
                return false;
            }
            // Comparaison des champs des bénéficiaires
            if (!isObjetsIdentiques(beneficiaireDto, beneficiaire.getPersonneCible(), listeProprietesPersonneAComparer)) {
                return false;
            }
        }

        // Tout est identique
        return true;
    }

    /**
     * Compare les champs des 2 personnes passées en paramètres.
     * @param personneDto le DTO de la personne
     * @param personne le modèle de la personne
     * @return true si les personnes sont identiques, false sinon
     */
    private boolean isPersonnesIdentiques(PersonneTarificateurDto personneDto, Personne personne) {
        // Comparaison des champs de la personne
        if (!isObjetsIdentiques(personneDto, personne, listeProprietesPersonneAComparer)) {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CHAMPS_DIFFERENTS_PERSONNE,
            		 new String[] {String.valueOf(personne.getId())}));
            return false;
        }
        // Comparaison des champs des infos santé
        if (!isObjetsIdentiques(personneDto.getInfoSante(), personne.getInfoSante(), listeProprietesInfoSanteAComparer)) {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_INFO_SANTE_DIFFERENTES_PERSONNE,
           		 new String[] {String.valueOf(personne.getId())}));
            return false;
        }
        // Comparaison de l'adresse principale de la personne
        if (!isObjetsIdentiques(personneDto.getAdressePrincipale(), personne.getAdressePrincipale(), listeProprietesAdresseAComparer)) {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_ADRESSE_PRINCIPALE_DIFFERENTES_PERSONNE,
              		 new String[] {String.valueOf(personne.getId())}));
            return false;
        }
        // Tout est identique
        return true;
    }

    /**
     * Compare les champs des 2 objets passés en paramètres.
     * @param objetDto le DTO de l'objet
     * @param objetModel le modèle de l'objet
     * @return true si les objets sont identiques, false sinon
     */
    @SuppressWarnings("unchecked")
    private boolean isObjetsIdentiques(Object objetDto, Object objetModel, List<String> listeProprietesAComparer) {
        // Si les 2 objets sont vides ==> identiques
        if (objetDto == null && objetModel == null) {
            return true;
        }
        // Si un objet est vide et l'autre pas ==> différents
        else if ((objetDto == null && objetModel != null) || (objetDto != null && objetModel == null)) {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_OBJET_DIFFERENTS));
            return false;
        }

        // Récupération des propriétés des objets
        Map<String, String> mapProprietesObjetDto = null;
        Map<String, String> mapProprietesObjetModele = null;
        try {
            mapProprietesObjetDto = BeanUtils.describe(objetDto);
            mapProprietesObjetModele = BeanUtils.describe(objetModel);
        } catch (IllegalAccessException e) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_WHILE_OBJECT_PROPERTY_RECUPERATION,
             		 new String[] {e.getMessage()}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_COMPARAISON_FAMILLES));
        } catch (InvocationTargetException e) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_WHILE_OBJECT_PROPERTY_RECUPERATION,
            		 new String[] {e.getMessage()}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_COMPARAISON_FAMILLES));
        } catch (NoSuchMethodException e) {
            logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_WHILE_OBJECT_PROPERTY_RECUPERATION,
            		 new String[] {e.getMessage()}));
            throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_COMPARAISON_FAMILLES));
        }

        // Parcours des propriétés à comparer
        for (String nomPropriete : listeProprietesAComparer) {
            // Vérification que la propriété est présente dans le DTO
            if (!mapProprietesObjetDto.containsKey(nomPropriete)) {
                logger.warn(messageSourceUtil.get(MessageKeyUtil.LOGGER_WARN_PROPERTY_MISSING_DTO,
               		 new String[] {nomPropriete}));
            }
            // Vérification que la propriété est présente dans le MODEL
            if (!mapProprietesObjetModele.containsKey(nomPropriete)) {
                logger.warn(messageSourceUtil.get(MessageKeyUtil.LOGGER_WARN_PROPERTY_MISSING_MODEL,
                  		 new String[] {nomPropriete}));
            }
            // Récupération de la valeur du DTO et du modèle
            final String valeurProprieteObjetDto = mapProprietesObjetDto.get(nomPropriete);
            final String valeurProprieteObjetModele = mapProprietesObjetModele.get(nomPropriete);
            // Comparaison des valeurs
            if (valeurProprieteObjetDto != null && valeurProprieteObjetModele != null
            		&& !valeurProprieteObjetDto.equalsIgnoreCase(valeurProprieteObjetModele)) {
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_PROPERTY_DIFFERE_DTO_MODEL,
                 		 new String[] {nomPropriete, valeurProprieteObjetDto, valeurProprieteObjetModele}));
                return false;
            } else if ((valeurProprieteObjetDto != null && !"".equals(valeurProprieteObjetDto) && valeurProprieteObjetModele == null)
                || (valeurProprieteObjetDto == null && valeurProprieteObjetModele != null && !"".equals(valeurProprieteObjetModele))) {
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_PROPERTY_DIFFERE_DTO_MODEL_NULL,
                		 new String[] {nomPropriete, valeurProprieteObjetDto, valeurProprieteObjetModele}));
                return false;
            }
        }
        return true;
    }

    /** Enregistrement des converters. */
    private void resetConverter() {
        ConvertUtils.deregister();
        if (customConverter != null) {
            for (Class<? extends Object> clazz : customConverter.keySet()) {
                ConvertUtils.register(customConverter.get(clazz), clazz);
            }
        }
    }
}
