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
package com.square.tarificateur.noyau.util.validation;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.tarificateur.noyau.dto.InfosAdhesionDto;
import com.square.tarificateur.noyau.dto.InfosPersonneDto;
import com.square.tarificateur.noyau.dto.InfosRibDto;
import com.square.tarificateur.noyau.dto.RapportDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurSquareMappingService;
import com.square.tarificateur.noyau.util.MessageKeyUtil;

/**
 * Valide les informations d'adhésions attachées a un devis.
 * @author jgoncalves - SCUB
 */
public class ValidationInfosAdhesionUtil {
    private static final String CHAMP_NUM_SECURITE_SOCIAL = ".numSecuriteSocial";

    private static final String ESPACE = " ";

    /** Logger. */
    private final Logger logger = Logger.getLogger(this.getClass());

    /** Service Personne. */
    private TarificateurPersonneService tarificateurPersonneService;

    /** Classe utilitaire pour la vérification. */
    private ValidationExpressionUtil validationExpressionUtil;

    /** Classe utilitaire pour accéder aux messages. */
    private static MessageSourceUtil messageSourceUtil;

    /** Service de mapping. */
    private TarificateurSquareMappingService tarificateurSquareMappingService;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /**Constantes de calcul.*/
    private static final int CALCUL_CLE_BANQUE_BASE = 97;
    private static final int CALCUL_CLE_FACTEUR_CODE_BANQUE = 89;
    private static final int CALCUL_CLE_FACTEUR_CODE_GUICHET = 15;
    private static final int CALCUL_CLE_FACTEUR_CODE_COMPTE_UN = 76;
    private static final int CALCUL_CLE_FACTEUR_CODE_COMPTE_DEUX = 3;
    private static final int FORMAT_SUBSTRING_CODE_COMPTE_DEUX = 11;

    /**
     * Validation des informations d'adhésion pour la transformation AIA.
     * @param infosAdhesion les infos
     * @return le rapport
     */
    public RapportDto validerInformationsAdhesionPourTransfoAia(InfosAdhesionDto infosAdhesion) {
        // Rapport d'erreurs
        final RapportDto rapportErreurs = new RapportDto();

        // Récupération des infos de paiement
        final Long idMoyenPaiement = infosAdhesion.getInfosPaiement().getIdMoyenPaiement();
        final Long idPeriodicitePaiement = infosAdhesion.getInfosPaiement().getIdPeriodicitePaiement();
        final Long idJourPaiement = infosAdhesion.getInfosPaiement().getIdJourPaiement();
        final Calendar dateSignature = infosAdhesion.getInfosPaiement().getDateSignature();

        validationExpressionUtil.verifierSiNull(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_ID_MOYEN_PAIEMENT_OBLIGATOIRE),
            idMoyenPaiement, "idMoyenPaiement");
        validationExpressionUtil.verifierSiNull(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_ID_PERIODICITE_PAIEMENT_OBLIGATOIRE),
            idPeriodicitePaiement, "idPeriodicitePaiement");
        // Le jour de paiement doit être renseigné si Mode de paiement = Prélèvement
        if (tarificateurSquareMappingService.getIdMoyenPaiementPrelevement().equals(idMoyenPaiement)) {
            validationExpressionUtil.verifierSiNull(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_ID_JOUR_PAIEMENT_OBLIGATOIRE),
                idJourPaiement, "idJourPaiement");
        }
        // La date de signature doit être renseignée
        validationExpressionUtil.verifierSiNull(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_DATE_SIGNATURE_OBLIGATOIRE), dateSignature,
            "dateSignature");
        // La date de signature doit être antérieure à la date courante
        validerDateSignature(rapportErreurs, dateSignature);
        // Le RIB doit etre valide
        if (infosAdhesion.getInfosRib() != null) {
            validerRib(rapportErreurs, infosAdhesion.getInfosRib());
        }

        // Recherche de la personne principale et d'un conjoint éventuel
        final Long idLienConjoint = tarificateurSquareMappingService.getIdLienFamilialConjoint();
        final Long idLienEnfant = tarificateurSquareMappingService.getIdLienFamilialEnfant();
        PersonneDto personnePrincipale = null;
        PersonneDto personneConjoint = null;
        for (InfosPersonneDto infoPersonne : infosAdhesion.getInfosPersonnes()) {
            if (infoPersonne.getLienFamilial() == null) {
                // Récupération de la personne principale
                personnePrincipale = tarificateurPersonneService.getPersonneById(infoPersonne.getId());
                if (personnePrincipale == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
                    		new String[] {String.valueOf(infoPersonne.getId())}));
                    throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANTE));
                }
            }
            else if (infoPersonne.getLienFamilial().getIdentifiant().equals(idLienConjoint)) {
                // Récupération du conjoint
                personneConjoint = tarificateurPersonneService.getPersonneById(infoPersonne.getId());
                if (personnePrincipale == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
                    		new String[] {String.valueOf(infoPersonne.getId())}));
                    throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANTE));
                }
            }
        }

        // Mapping des infos de personnes
        for (InfosPersonneDto infoPersonne : infosAdhesion.getInfosPersonnes()) {
            PersonneDto personne = null;
            // Validation du numéro de sécurité social
            String lienFamilial = "";
            if (infoPersonne.getLienFamilial() != null) {
                if (infoPersonne.getLienFamilial().getIdentifiant().equals(idLienConjoint)) {
                    lienFamilial = "-conjoint";
                    // Conjoint déjà récupéré
                    personne = personneConjoint;
                }
                else if (infoPersonne.getLienFamilial().getIdentifiant().equals(idLienEnfant)) {
                    lienFamilial = "-enfant" + infoPersonne.getId();
                    // Récupération de l'enfant
                    personne = tarificateurPersonneService.getPersonneById(infoPersonne.getId());
                }
            }
            else {
                // Personne principale déjà récupérée
                personne = personnePrincipale;
            }
            if (personne == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
                		new String[] {String.valueOf(infoPersonne.getId())}));
                throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANTE));
            }

            // Si le referent est différent de la personne parcouru, on ne verifie pas (deja verifiée sur la personne referente)
            if (infoPersonne.getInfoSante() == null || infoPersonne.getInfoSante().getIdReferent() == null
                || infoPersonne.getInfoSante().getIdReferent().equals(infoPersonne.getId())) {
                final String numeroSecu = infoPersonne.getInfoSante() != null ? infoPersonne.getInfoSante().getNumSecuriteSocial() : null;
                final String cleSecu = infoPersonne.getInfoSante() != null ? infoPersonne.getInfoSante().getCleSecuriteSocial() : null;
                validationExpressionUtil.verifierNumeroSecuriteSociale(rapportErreurs, null, messageSourceUtil.get(
                    MessageKeyUtil.ERROR_VALIDATION_NUMERO_SECU_PERSONNE, new String[] {personne.getPrenom() + ESPACE + personne.getNom()}), personne
                        .getClass().getSimpleName()
                    + CHAMP_NUM_SECURITE_SOCIAL + lienFamilial, numeroSecu, cleSecu, personne.getDateNaissance(), personne.getEidCivilite());
            }
            // par contre on vérifie que le lien familial avec le referent est bien present
            else if (infoPersonne.getInfoSante() != null && infoPersonne.getInfoSante().getIdReferent() != null
                && !infoPersonne.getInfoSante().getIdReferent().equals(infoPersonne.getId()) && infoPersonne.getTypeRelationAssureSocial() == null) {
                rapportErreurs.ajoutRapport("RelationDto.type" + lienFamilial, messageSourceUtil
                        .get(MessageKeyUtil.ERREUR_ASSURE_SOCIAL_RELATION_TYPE_OBLIGATOIRE), true);
            }
            // Vérification que le régime et la caisse sont renseignés
            final Long eidCaisse = infoPersonne.getInfoSante() != null ? infoPersonne.getInfoSante().getEidCaisse() : null;
            validationExpressionUtil.verifierSiNull(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_EID_CAISSE_OBLIGATOIRE), eidCaisse,
                personne.getClass().getSimpleName() + ".eidCaisse" + lienFamilial);

            // Mapping des infos de la personne
            mapperDozerBean.map(infoPersonne, personne);
        }
        return rapportErreurs;
    }

    /**
     * Validation des informations de santé d'un assure social.
     * @param assureSocialDto les infos
     * @param fromOuvertureOpp indique si l'appel provient de l'ouverture d'une opportunité
     * @return le rapport
     */
    public RapportDto validerAssureSocial(PersonneDto assureSocialDto, boolean fromOuvertureOpp) {
        // Rapport d'erreurs
        final RapportDto rapportErreurs = new RapportDto();

        validationExpressionUtil.verifierSiVide(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_ASSURE_SOCIAL_NOM_OBLIGATOIRE),
            assureSocialDto.getNom(), assureSocialDto.getClass().getSimpleName() + ".nom");
        validationExpressionUtil.verifierSiVide(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_ASSURE_SOCIAL_PRENOM_OBLIGATOIRE),
            assureSocialDto.getPrenom(), assureSocialDto.getClass().getSimpleName() + ".prenom");
        validationExpressionUtil.verifierSiNull(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_ASSURE_SOCIAL_DATE_NAISSANCE_OBLIGATOIRE),
            assureSocialDto.getDateNaissance(), assureSocialDto.getClass().getSimpleName() + ".dateNaissance");
        validationExpressionUtil.verifierSiNull(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_ASSURE_SOCIAL_CIVILITE_OBLIGATOIRE),
            assureSocialDto.getEidCivilite(), assureSocialDto.getClass().getSimpleName() + ".eidCivilite");

        validationExpressionUtil.verifierNumeroSecuriteSociale(rapportErreurs, null, messageSourceUtil
                .get(MessageKeyUtil.ERREUR_ASSURE_SOCIAL_NUMERO_RO_OBLIGATOIRE), assureSocialDto.getClass().getSimpleName() + ".infoSante"
            + CHAMP_NUM_SECURITE_SOCIAL, assureSocialDto.getInfoSante() != null ? assureSocialDto.getInfoSante().getNumSecuriteSocial() : null, assureSocialDto
                .getInfoSante() != null ? assureSocialDto.getInfoSante().getCleSecuriteSocial() : null, assureSocialDto.getDateNaissance(), assureSocialDto
                .getEidCivilite());

        if (!fromOuvertureOpp) {
            validationExpressionUtil.verifierSiNull(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_ASSURE_SOCIAL_REGIME_OBLIGATOIRE),
                assureSocialDto.getInfoSante() != null ? assureSocialDto.getInfoSante().getEidRegime() : null, assureSocialDto.getClass().getSimpleName()
                    + ".infoSante.eidRegime");
            validationExpressionUtil.verifierSiNull(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_ASSURE_SOCIAL_CAISSE_OBLIGATOIRE),
                assureSocialDto.getInfoSante() != null ? assureSocialDto.getInfoSante().getEidCaisse() : null, assureSocialDto.getClass().getSimpleName()
                    + ".infoSante.eidCaisse");
        }

        return rapportErreurs;
    }

    /**
     * Validation des informations d'adhésion pour mise à jour (on vérifie que la validité des champs sans les rendre obligatoires).
     * @param infosAdhesion les infos
     * @return le rapport
     */
    public RapportDto validerInformationsAdhesionPourMiseAJour(InfosAdhesionDto infosAdhesion) {
        // Rapport d'erreurs
        final RapportDto rapportErreurs = new RapportDto();

        // La date de signature doit être antérieure à la date courante si elle est renseignée
        if (infosAdhesion.getInfosPaiement() != null && infosAdhesion.getInfosPaiement().getDateSignature() != null) {
            validerDateSignature(rapportErreurs, infosAdhesion.getInfosPaiement().getDateSignature());
        }
        // Le RIB doit etre valide s'il est renseigné
        if (infosAdhesion.getInfosRib() != null) {
            validerRib(rapportErreurs, infosAdhesion.getInfosRib());
        }

        // Recherche de la personne principale et d'un conjoint éventuel
        final Long idLienConjoint = tarificateurSquareMappingService.getIdLienFamilialConjoint();
        final Long idLienEnfant = tarificateurSquareMappingService.getIdLienFamilialEnfant();
        PersonneDto personnePrincipale = null;
        PersonneDto personneConjoint = null;
        for (InfosPersonneDto infoPersonne : infosAdhesion.getInfosPersonnes()) {
            if (infoPersonne.getLienFamilial() == null) {
                // Récupération de la personne principale
                personnePrincipale = tarificateurPersonneService.getPersonneById(infoPersonne.getId());
                if (personnePrincipale == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
                    		new String[] {String.valueOf(infoPersonne.getId())}));
                    throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANTE));
                }
            }
            else if (infoPersonne.getLienFamilial().getIdentifiant().equals(idLienConjoint)) {
                // Récupération du conjoint
                personneConjoint = tarificateurPersonneService.getPersonneById(infoPersonne.getId());
                if (personneConjoint == null) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
                    		new String[] {String.valueOf(infoPersonne.getId())}));
                    throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANTE));
                }
            }
        }

        // Verification des infos de personnes
        for (InfosPersonneDto infoPersonne : infosAdhesion.getInfosPersonnes()) {
            PersonneDto personne = null;
            // Validation du numéro de sécurité sociale s'il est renseigné (numéro ou clé)
            String lienFamilial = "";
            if (infoPersonne.getLienFamilial() != null) {
                if (infoPersonne.getLienFamilial().getIdentifiant().equals(idLienConjoint)) {
                    lienFamilial = "-conjoint";
                    // Conjoint déjà récupéré
                    personne = personneConjoint;
                }
                else if (infoPersonne.getLienFamilial().getIdentifiant().equals(idLienEnfant)) {
                    lienFamilial = "-enfant" + infoPersonne.getId();
                    // Récupération de l'enfant
                    personne = tarificateurPersonneService.getPersonneById(infoPersonne.getId());
                }
            }
            else {
                // Personne principale déjà récupérée
                personne = personnePrincipale;
            }
            if (personne == null) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_PERSONNE_INEXISTANTE,
                		new String[] {String.valueOf(infoPersonne.getId())}));
                throw new TechnicalException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANTE));
            }
            if (infoPersonne.getInfoSante() != null) {
                if ((infoPersonne.getInfoSante().getNumSecuriteSocial() != null && !"".equals(infoPersonne.getInfoSante().getNumSecuriteSocial()))
                    || (infoPersonne.getInfoSante().getCleSecuriteSocial() != null && !"".equals(infoPersonne.getInfoSante().getCleSecuriteSocial()))) {
                    // Si le referent est différent de la personne parcouru, on ne verifie pas (deja verifiée sur la personne referente)
                    if (infoPersonne.getInfoSante().getIdReferent() == null || infoPersonne.getInfoSante().getIdReferent().equals(infoPersonne.getId())) {
                        validationExpressionUtil.verifierNumeroSecuriteSociale(rapportErreurs, null, messageSourceUtil.get(
                            MessageKeyUtil.ERROR_VALIDATION_NUMERO_SECU_PERSONNE, new String[] {personne.getPrenom() + ESPACE + personne.getNom()}), personne
                                .getClass().getSimpleName()
                            + CHAMP_NUM_SECURITE_SOCIAL + lienFamilial, infoPersonne.getInfoSante().getNumSecuriteSocial(), infoPersonne.getInfoSante()
                                .getCleSecuriteSocial(), personne.getDateNaissance(), personne.getEidCivilite());
                    }
                }
                // si le regime est renseigné, la caisse doit l'etre aussi
                if (infoPersonne.getInfoSante().getEidRegime() != null) {
                    validationExpressionUtil.verifierSiNull(rapportErreurs, null, messageSourceUtil.get(MessageKeyUtil.ERREUR_EID_CAISSE_OBLIGATOIRE),
                        infoPersonne.getInfoSante().getEidCaisse(), personne.getClass().getSimpleName() + ".eidCaisse" + lienFamilial);
                }
            }

            // Mapping des infos de la personne
            mapperDozerBean.map(infoPersonne, personne);
        }
        return rapportErreurs;
    }

    private void validerRib(RapportDto rapportErreurs, InfosRibDto infosRib) {
        try {
            final int longueurCodeCompte = 11;
            if (infosRib.getCodeCompte() != null && infosRib.getCodeCompte().length() != longueurCodeCompte) {
                rapportErreurs.ajoutRapport("infosRib", messageSourceUtil.get(MessageKeyUtil.ERREUR_RIB_INVALIDE), true);
                return;
            }
            final String codeBanqueFormate = formaterElementRib(infosRib.getCodeBanque());
            final String codeGuichetFormate = formaterElementRib(infosRib.getCodeGuichet());
            final String codeCompteFormate = formaterElementRib(infosRib.getCodeCompte());
            final String cleFormate = formaterElementRib(infosRib.getCle());
            final int codeBanque = StringUtils.isNotBlank(codeBanqueFormate) ? Integer.valueOf(codeBanqueFormate).intValue() : 0;
            final int codeGuichet = StringUtils.isNotBlank(codeGuichetFormate) ? Integer.valueOf(codeGuichetFormate).intValue() : 0;
            final int cle = StringUtils.isNotBlank(cleFormate) ? Integer.valueOf(cleFormate).intValue() : 0;
            final int codeCompte1 = StringUtils.isNotBlank(codeCompteFormate) ? Integer.valueOf(codeCompteFormate.substring(0, 6)).intValue() : 0;
            final int codeCompte2 = StringUtils.isNotBlank(codeCompteFormate) ? Integer.valueOf(codeCompteFormate.substring(6,
            		FORMAT_SUBSTRING_CODE_COMPTE_DEUX)).intValue() : 0;
            final int calculCle = CALCUL_CLE_BANQUE_BASE - ((CALCUL_CLE_FACTEUR_CODE_BANQUE * codeBanque
            		+ CALCUL_CLE_FACTEUR_CODE_GUICHET * codeGuichet + CALCUL_CLE_FACTEUR_CODE_COMPTE_UN * codeCompte1
            		+ CALCUL_CLE_FACTEUR_CODE_COMPTE_DEUX * codeCompte2) % CALCUL_CLE_BANQUE_BASE);
            if (calculCle != cle) {
                rapportErreurs.ajoutRapport("infosRib", messageSourceUtil.get(MessageKeyUtil.ERREUR_RIB_INVALIDE), true);
            }
        }
        catch (NumberFormatException e) {
            rapportErreurs.ajoutRapport("infosRib", messageSourceUtil.get(MessageKeyUtil.ERREUR_RIB_INVALIDE), true);
        }
    }

    /**
     * Substitue les lettres d'un élément de RIB par des chiffres.
     * @param elementRib l'élément de RIB
     * @return l'élément de RIB formaté
     */
    private String formaterElementRib(String elementRib) {
        if (elementRib != null) {
            return elementRib.replaceAll("[aAjJ]", "1").replaceAll("[bBkKsS]", "2").replaceAll("[cClLtT]", "3").replaceAll("[dDmMuU]", "4").replaceAll(
                "[eEnNvV]", "5").replaceAll("[fFoOwW]", "6").replaceAll("[gGpPxX]", "7").replaceAll("[hHqQyY]", "8").replaceAll("[iIrRzZ]", "9");
        }
        else {
            return "";
        }
    }

    private void validerDateSignature(RapportDto rapportErreurs, Calendar dateSignature) {
        // La date de signature doit être antérieure à la date courante
        final Calendar dateNow = Calendar.getInstance();
        if (dateSignature != null && dateSignature.after(dateNow)) {
            rapportErreurs.ajoutRapport("dateSignature", messageSourceUtil.get(MessageKeyUtil.ERREUR_DATE_SIGNATURE_POSTERIEURE), true);
        }
    }

    /**
     * Définition de validationExpressionUtil.
     * @param validationExpressionUtil the validationExpressionUtil to set
     */
    public void setValidationExpressionUtil(ValidationExpressionUtil validationExpressionUtil) {
        this.validationExpressionUtil = validationExpressionUtil;
    }

    /**
     * Définition de messageSourceUtil.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        ValidationInfosAdhesionUtil.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Définition de tarificateurSquareMappingService.
     * @param tarificateurSquareMappingService the tarificateurSquareMappingService to set
     */
    public void setTarificateurSquareMappingService(TarificateurSquareMappingService tarificateurSquareMappingService) {
        this.tarificateurSquareMappingService = tarificateurSquareMappingService;
    }

    /**
     * Définition de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the tarificateurPersonneService value.
     * @param tarificateurPersonneService the tarificateurPersonneService to set
     */
    public void setTarificateurPersonneService(TarificateurPersonneService tarificateurPersonneService) {
        this.tarificateurPersonneService = tarificateurPersonneService;
    }
}
