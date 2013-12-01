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
package com.square.core.util.validation;

import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_AJOUT_BENEFICIAIRE_TYPERELATION_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_SAVE_PERSONNE_AGENCE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_SAVE_PERSONNE_CSP_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_SAVE_PERSONNE_RESEAU_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_SITUATIONFAMILIALE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_ADRESSE_TROP_LONGUE;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_BAD_EMAIL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_FORMAT_NUM_VOIE_INCORRECT;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_ADRESSE_ADRESSE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_ADRESSE_CODEPOSTAL_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_ADRESSE_PAYS_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_BENEFICIAIRE_CIVILITE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_BENEFICIAIRE_DATENAISSANCE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_BENEFICIAIRE_NOM_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_BENEFICIAIRE_PRENOM_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_CAISSE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_CIVILITE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_INVALIDE;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NATURETELEPHONE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NATURE_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NOM_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NUMERO_SECURITE_SOCIALE_INVALIDE;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_NULL;
import static com.square.core.util.PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_SEGMENT_NULL;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.core.dao.interfaces.PaysDao;
import com.square.core.dao.interfaces.PersonneCiviliteDao;
import com.square.core.dao.interfaces.RelationTypeDao;
import com.square.core.model.Adresse;
import com.square.core.model.Email;
import com.square.core.model.Pays;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.RelationType;
import com.square.core.model.Telephone;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.BeneficiaireDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.NumeroRoDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.RapportDto;
import com.square.core.model.dto.SousRapportDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.PersonnePhysiqueKeyUtil;

/**
 * Classe utilitaire pour la validation des contraintes des personnes.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public final class ValidationPersonneUtil {

    /** Service sur les personnes. */
    private PersonneService personneService;

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    /** Classe utilitaire pour la vérification. */
    private ValidationExpressionUtil validationExpressionUtil;

    /** Dao pour les pays. */
    private PaysDao paysDao;

    /** Dao pour les relations. */
    private RelationTypeDao relationTypeDao;

    /** Dao pour les civilités des personnes. */
    private PersonneCiviliteDao personneCiviliteDao;

    /** Service de mapping. */
    private SquareMappingService squareMappingService;

    /** Nombre de caractères maximum pour le libellé d'une adresse (numéro voie + nature de voie + nom voie). */
    private static final int NB_CARACTERE_ADRESSE = 38;

    /** Constructeur privé. */
    private ValidationPersonneUtil() {
    }

    /**
     * Vérifie si les contraintes de création d'un prospect sont respectées.
     * @param personneDto les informations de la personne à créer.
     * @return true si les contraintes sont vérifiées, false si non.
     * */
    public boolean verifierContrainteCreationProspect(PersonneDto personneDto) {
        final RapportDto rapport = new RapportDto();
        final CoordonneesDto coordonnees = personneService.rechercherCoordonneesParIdPersonne(personneDto.getIdentifiant());
        EmailDto emailDto = null;
        AdresseDto adresseDto = null;
        TelephoneDto telephoneFixeDto = null;
        TelephoneDto telephonePortableDto = null;
        if (coordonnees.getTelephones() != null && coordonnees.getTelephones().size() >= 1
            && StringUtils.isNotBlank(coordonnees.getTelephones().get(0).getNumero())) {
            telephoneFixeDto = coordonnees.getTelephones().get(0);
        }
        if (coordonnees.getTelephones() != null && coordonnees.getTelephones().size() >= 2
            && StringUtils.isNotBlank(coordonnees.getTelephones().get(1).getNumero())) {
            telephonePortableDto = coordonnees.getTelephones().get(1);
        }
        if (coordonnees.getEmails() != null && !coordonnees.getEmails().isEmpty() && coordonnees.getEmails().get(0) != null
            && StringUtils.isNotBlank(coordonnees.getEmails().get(0).getAdresse())) {
            emailDto = coordonnees.getEmails().get(0);
        }
        if (coordonnees.getAdresses() != null) {
            for (AdresseDto adresse : coordonnees.getAdresses()) {
                if (adresse != null) {
                    adresseDto = adresse;
                    break;
                }
            }
        }
        return verifierContrainteCreationProspect(personneDto, emailDto, adresseDto, telephoneFixeDto, telephonePortableDto, rapport);
    }

    /**
     * Vérifie si les contraintes de création d'un prospect sont respectées.
     * @param personneDto les informations de la personne.
     * @param emailDto l'email de la personne.
     * @param adresseDto l'adresse de la personne.
     * @param telephoneFixeDto le téléphone fixe de la personne.
     * @param telephonePortableDto le téléphone portable de la personne.
     * @param rapport le rapport d'erreur.
     * @return true si les contraintes sont vérifiées, false si non.
     */
    public boolean verifierContrainteCreationProspect(PersonneDto personneDto, EmailDto emailDto, AdresseDto adresseDto, TelephoneDto telephoneFixeDto,
        TelephoneDto telephonePortableDto, RapportDto rapport) {
        // Controles génériques (Au moins 1 moyen de communication)
        if ((emailDto == null || StringUtils.isBlank(emailDto.getAdresse())) && telephoneFixeDto == null && telephonePortableDto == null) {
            rapport.ajoutRapport("EmailDto.adresse", messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_COORDONNEE_OBLIGATOIRE), true);
            rapport.ajoutRapport("TelephoneDto.numero.0", messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_COORDONNEE_OBLIGATOIRE), true);
            rapport.ajoutRapport("TelephoneDto.numero.1", messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_COORDONNEE_OBLIGATOIRE), true);
        }
        // Si l'un des trois moyens de communication est donné vérification des valeurs
        else {
            // Traitement des téléphones
            if (telephoneFixeDto != null || telephonePortableDto != null) {
                if (telephoneFixeDto != null) {
                    validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_NATURETELEPHONE_NULL),
                        telephoneFixeDto.getNature(), TelephoneDto.class.getSimpleName() + ".nature.0");
                    final Pays paysTelephone = paysDao.rechercherPaysParId(telephoneFixeDto.getPays().getId());
                    if (paysTelephone == null) {
                        throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PAYS_INEXISTENT_EN_BD));
                    }
                }
                if (telephonePortableDto != null) {
                    validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_NATURETELEPHONE_NULL),
                        telephonePortableDto.getNature(), TelephoneDto.class.getSimpleName() + ".nature.1");
                    final Pays paysTelephone = paysDao.rechercherPaysParId(telephonePortableDto.getPays().getId());
                    if (paysTelephone == null) {
                        throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_PAYS_INEXISTENT_EN_BD));
                    }
                }
            }
            // Récupération de l'email
            if (emailDto != null) {
                validationExpressionUtil.verifierEmail(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_BAD_EMAIL), emailDto.getAdresse(), EmailDto.class
                        .getSimpleName()
                    + ".adresse");
            }
        }
        final ValidationExpressionProp[] propsPersonne =
            new ValidationExpressionProp[] {new ValidationExpressionProp("civilite", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_CIVILITE_NULL)),
                new ValidationExpressionProp("prenom", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_NULL)),
                new ValidationExpressionProp("nom", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_NOM_NULL)),
                new ValidationExpressionProp("dateNaissance", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_NULL))};

        validationExpressionUtil.verifierSiVide(rapport, personneDto, propsPersonne);
        // Vérification de nom et prenom
        verifierNomPrenomPersonne(rapport, personneDto.getNom(), personneDto.getPrenom(), personneDto.getClass());
        // Si une date de naissance est spécifiée
        if (personneDto.getDateNaissance() != null) {
            // On vérifie que la date de naissance indiquée est valide
            if (!validationExpressionUtil.isDateDeNaissanceValide(personneDto.getDateNaissance())) {
                rapport.ajoutRapport("PersonneDto.dateNaissance", messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_INVALIDE), true);
            }
        }
        validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_ADRESSE_ADRESSE_NULL), adresseDto, AdresseDto.class
                .getSimpleName());
        if (adresseDto != null) {
            final ValidationExpressionProp[] propsAddresse =
                new ValidationExpressionProp[] {new ValidationExpressionProp("nomVoie", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_ADRESSE_ADRESSE_NULL))};
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
                    .getSimpleName()
                + ".nomVoie");
            // Controles complémentaires si france
            if (adresseDto.getPays() != null && squareMappingService.getIdPaysFrance().equals(adresseDto.getPays().getIdentifiant())) {
                validationExpressionUtil.verifierSiVide(rapport, adresseDto, propsAddresse);
                validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_ADRESSE_CODEPOSTAL_NULL), adresseDto
                        .getCodePostalCommune() == null ? null : adresseDto.getCodePostalCommune().getIdentifiant(), AdresseDto.class.getSimpleName()
                    + ".codePostal");
            }
        }

        return !rapport.getEnErreur();
    }

    /**
     * Vérifie le nom et le prénom d'une personne.
     * @param rapport le rapport d'erreur.
     * @param nom le nom de la personne.
     * @param prenom le prénom de la personne.
     * @param classObjet la classe de l'objet.
     */
    public void verifierNomPrenomPersonne(RapportDto rapport, String nom, String prenom, Class classObjet) {
        final String regexp = "[a-zA-ZéèêàâôîïûçÉÈÊÀÂÔÎÏÛÇ&&[^\\d\\s\\-]]([a-zA-ZéèêàâôîïûçÉÈÊÀÂÔÎÏÛÇ\\s\\-]*[^\\d\\s\\-])*";
        final Pattern pattern = Pattern.compile(regexp);

        // Nom
        Matcher matcher = pattern.matcher(nom != null ? nom : "");
        if (!matcher.matches()) {
            rapport.ajoutRapport(classObjet.getSimpleName() + ".nom",
                messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_NOM_BAD_FORMAT), true);
        }

        // Prenom
        matcher = pattern.matcher(prenom != null ? prenom : "");
        if (!matcher.matches()) {
            rapport.ajoutRapport(classObjet.getSimpleName() + ".prenom", messageSourceUtil
                    .get(PersonnePhysiqueKeyUtil.MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_BAD_FORMAT), true);
        }
    }

    /**
     * Contrôle les données d'une personne physique pour sa modification.
     * @param personneDto les informations de la personne.
     * @return le rapport d'erreur.
     */
    public RapportDto controlerModiciationPersonnePhysique(PersonneDto personneDto) {
        final RapportDto rapport = new RapportDto();

        // Controle des champs obligatoires
        final ValidationExpressionProp[] props =
            new ValidationExpressionProp[] {new ValidationExpressionProp("civilite", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_CIVILITE_NULL)),
                new ValidationExpressionProp("prenom", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_NULL)),
                new ValidationExpressionProp("nom", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_NOM_NULL)),
                new ValidationExpressionProp("dateNaissance", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_NULL)),
                new ValidationExpressionProp("naturePersonne", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_NATURE_NULL)),
                new ValidationExpressionProp("segment", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_SEGMENT_NULL)),
                new ValidationExpressionProp("sitFam", null, messageSourceUtil.get(MESSAGE_ERREUR_SITUATIONFAMILIALE_NULL)),
                new ValidationExpressionProp("reseauVente", null, messageSourceUtil.get(MESSAGE_ERREUR_SAVE_PERSONNE_RESEAU_NULL)),
                new ValidationExpressionProp("csp", null, messageSourceUtil.get(MESSAGE_ERREUR_SAVE_PERSONNE_CSP_NULL)),
                new ValidationExpressionProp("agence", null, messageSourceUtil.get(MESSAGE_ERREUR_SAVE_PERSONNE_AGENCE_NULL))};
        validationExpressionUtil.verifierSiVide(rapport, personneDto, props);

        // Vérification de nom et prenom
        verifierNomPrenomPersonne(rapport, personneDto.getNom(), personneDto.getPrenom(), personneDto.getClass());

        // Si une date de naissance est spécifiée
        if (personneDto.getDateNaissance() != null) {
            // On vérifie que la date de naissance indiquée est valide
            if (!validationExpressionUtil.isDateDeNaissanceValide(personneDto.getDateNaissance())) {
                rapport.ajoutRapport("PersonneDto.dateNaissance", messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_INVALIDE), true);
            }
        }

        // si régime rempli, caisse obligatoire
        if (personneDto.getInfoSante() != null && personneDto.getInfoSante().getCaisseRegime() != null
            && personneDto.getInfoSante().getCaisseRegime().getIdentifiant() != null) {
            validationExpressionUtil.verifierSiNull(rapport, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_PERSONNE_CAISSE_NULL), personneDto.getInfoSante()
                    .getCaisse(), PersonneDto.class.getSimpleName() + ".infoSante.caisse");
        }

        // Numéro RO.
        // TODO : Voir si possible de faire plus propre
        NumeroRoDto nroDto = null;
        if (personneDto.getInfoSante() != null) {
            nroDto = squareMappingService.convertirNroVersNss(personneDto.getInfoSante().getNro());
            if (nroDto != null) {
                validationExpressionUtil.verifierNumeroSecuriteSociale(rapport, null, messageSourceUtil
                        .get(MESSAGE_ERROR_SAVE_PERSONNE_NUMERO_SECURITE_SOCIALE_INVALIDE), personneDto.getClass().getSimpleName() + ".nro", nroDto
                        .getNumeroSS(), nroDto.getCleSS());
            }
        }

        return rapport;
    }

    /**
     * Vérifie si les contraintes de création de bénéficiaires sont respectées.
     * @param listeBeneficiaire la liste des bénéficiaires à vérifier.
     * @param rapport le rapport d'erreur.
     */
    public void verifierContrainteCreationBeneficiaires(List<BeneficiaireDto> listeBeneficiaire, RapportDto rapport) {
        for (int i = 0; i < listeBeneficiaire.size(); i++) {
            final BeneficiaireDto beneficiaireDto = listeBeneficiaire.get(i);
            // Récupération du type de relation
            if (beneficiaireDto.getTypeRelation() != null) {
                final RelationType relationType = relationTypeDao.rechercherTypeRelationParId(beneficiaireDto.getTypeRelation().getIdentifiant());
                if (relationType == null) {
                    throw new BusinessException(messageSourceUtil.get(PersonnePhysiqueKeyUtil.MESSAGE_ERREUR_TYPERELATION_INEXISTENT_EN_BD));
                }
            }
            if (beneficiaireDto.getIdentifiant() == null) {
                // Vérification des champs obligatoires
                final int idx = beneficiaireDto.getIndex();
                final ValidationExpressionProp[] propsBeneficiaire =
                    new ValidationExpressionProp[] {
                        new ValidationExpressionProp("civilite", idx, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_CIVILITE_NULL)),
                        new ValidationExpressionProp("prenom", idx, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_PRENOM_NULL)),
                        new ValidationExpressionProp("nom", idx, null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_NOM_NULL)),
                        new ValidationExpressionProp("dateNaissance", idx, null,
                            messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_DATENAISSANCE_NULL)),
                        new ValidationExpressionProp("typeRelation", idx, null, messageSourceUtil.get(MESSAGE_ERREUR_AJOUT_BENEFICIAIRE_TYPERELATION_NULL))};
                validationExpressionUtil.verifierSiVide(rapport, beneficiaireDto, propsBeneficiaire);
            }
        }
    }

    /**
     * Vérifie les contraintes pour un prospect.
     * @param personne les informations de la personne à vérifier.
     * @return true si les contraintes sont vérifiées, false si non.
     */
    public boolean verifierContrainteProspect(PersonnePhysique personne) {
        // Vérification de la civilité
        if (personne.getCivilite() == null) {
            return false;
        }

        final String regexp = "[a-zA-ZéèêàâôîïûçÉÈÊÀÂÔÎÏÛÇ&&[^\\d\\s\\-]]([a-zA-ZéèêàâôîïûçÉÈÊÀÂÔÎÏÛÇ\\s\\-]*[^\\d\\s\\-])*";
        final Pattern pattern = Pattern.compile(regexp);

        // Vérification du nom
        final Matcher matcherNom = pattern.matcher(personne.getNom());
        if (personne.getNom() == null || StringUtils.isBlank(personne.getNom())) {
            return false;
        }
        else if (!matcherNom.matches()) {
            return false;
        }

        // Vérification du prénom
        final Matcher matcherPrenom = pattern.matcher(personne.getPrenom());
        if (personne.getPrenom() == null || StringUtils.isBlank(personne.getPrenom())) {
            return false;
        }
        else if (!matcherPrenom.matches()) {
            return false;
        }

        // Vérification de la date de naissance
        if (personne.getDateNaissance() == null) {
            return false;
        }
        else {
            // On vérifie que la date de naissance indiquée est valide
            if (!validationExpressionUtil.isDateDeNaissanceValide(personne.getDateNaissance())) {
                return false;
            }
        }

        // Au moins 1 moyen de communication
        boolean hasEmail = false;
        boolean hasTelephone = false;
        boolean hasAdresse = false;
        if (personne.getEmails() != null) {
            for (Email email : personne.getEmails()) {
                if (!email.isSupprime()) {
                    hasEmail = true;
                    break;
                }
            }
        }
        if (!hasEmail && personne.getTelephones() != null) {
            for (Telephone telephone : personne.getTelephones()) {
                if (!telephone.isSupprime()) {
                    hasTelephone = true;
                    break;
                }
            }
        }
        if (!hasEmail && !hasTelephone && personne.getAdresses() != null) {
            final Calendar today = Calendar.getInstance();
            for (Adresse adresse : personne.getAdresses()) {
                if (!adresse.isSupprime() && (adresse.getDateFin() == null || adresse.getDateFin().after(today))) {
                    hasAdresse = true;
                    break;
                }
            }
        }
        if (!hasEmail && !hasTelephone && !hasAdresse) {
            return false;
        }

        // Vérification des téléphones
        if (personne.getTelephones() != null) {
            for (Telephone telephone : personne.getTelephones()) {
                if (!telephone.isSupprime()) {
                    if (telephone.getNatureTelephone() == null || telephone.getPays() == null || telephone.getNumTelephone() == null
                            || StringUtils.isBlank(telephone.getNumTelephone())) {
                        return false;
                    }
                }
            }
        }

        // Vérification des emails
        if (personne.getEmails() != null) {
            for (Email email : personne.getEmails()) {
                if (!email.isSupprime()) {
                    if (email.getAdresse() == null || StringUtils.isBlank(email.getAdresse())
                            || !email.getAdresse().toUpperCase().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$")) {
                        return false;
                    }
                }
            }
        }

        // Vérification des adresses
        if (personne.getAdresses() == null || personne.getAdresses().size() == 0) {
            return false;
        }
        else {
            final Calendar today = Calendar.getInstance();
            for (Adresse adresse : personne.getAdresses()) {
                if (!adresse.isSupprime() && (adresse.getDateFin() == null || adresse.getDateFin().after(today))) {
                    if (adresse.getNomVoie() == null || StringUtils.isBlank(adresse.getNomVoie())) {
                        return false;
                    }
                    if (adresse.getNumeroVoie() != null && !StringUtils.isBlank(adresse.getNumeroVoie())
                            && !adresse.getNumeroVoie().toUpperCase().matches("^[\\d]{1,4}[A-Z]{0,1}$")) {
                        return false;
                    }
                    // Vérification de la longueur de l'adresse
                    String libelleAdresse = adresse.getNumeroVoie();
                    if (adresse.getTypeVoie() != null) {
                        if (libelleAdresse != null && !StringUtils.isBlank(libelleAdresse)) {
                            libelleAdresse = libelleAdresse + " " + adresse.getTypeVoie().getLibelle();
                        }
                        else {
                            libelleAdresse = adresse.getTypeVoie().getLibelle();
                        }
                    }
                    if (libelleAdresse != null && !StringUtils.isBlank(libelleAdresse)) {
                        libelleAdresse = libelleAdresse + " " + adresse.getNomVoie();
                    }
                    else {
                        libelleAdresse = adresse.getNomVoie();
                    }
                    if (libelleAdresse.length() > NB_CARACTERE_ADRESSE) {
                        return false;
                    }

                    if (adresse.getPays() == null) {
                        return false;
                    }
                    else if (squareMappingService.getIdPaysFrance().equals(adresse.getPays().getId())
                            && adresse.getCodePostalCommune() == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Vérifie les contraintes pour un bénéficiaire prospect.
     * @param personne les infos de la personne à vérifier.
     * @return true si les contraintes sont vérifiées, false si non.
     */
    public boolean verifierContrainteBeneficiaireProspect(PersonnePhysique personne) {
        final RapportDto rapport = new RapportDto();

        // Vérification des champs obligatoires
        final ValidationExpressionProp[] propsBeneficiaire =
            new ValidationExpressionProp[] {
                new ValidationExpressionProp("civilite", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_CIVILITE_NULL)),
                new ValidationExpressionProp("prenom", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_PRENOM_NULL)),
                new ValidationExpressionProp("nom", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_NOM_NULL)),
                new ValidationExpressionProp("dateNaissance", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_DATENAISSANCE_NULL))};
        validationExpressionUtil.verifierSiVide(rapport, personne, propsBeneficiaire);

        return !rapport.getEnErreur();
    }

    /**
     * Vérifie les contraintes pour un vivier.
     * @param beneficiaire les infos de la personne à vérifier.
     * @return true si une des contraintes est vérifiée, false sinon.
     */
    public boolean verifierContrainteBeneficiaireVivier(BeneficiaireDto beneficiaire) {
        final RapportDto rapport = new RapportDto();

        // Vérification des champs obligatoires
        final ValidationExpressionProp[] propsBeneficiaire =
            new ValidationExpressionProp[] {
                new ValidationExpressionProp("civilite", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_CIVILITE_NULL)),
                new ValidationExpressionProp("prenom", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_PRENOM_NULL)),
                new ValidationExpressionProp("nom", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_NOM_NULL)),
                new ValidationExpressionProp("dateNaissance", null, messageSourceUtil.get(MESSAGE_ERROR_SAVE_BENEFICIAIRE_DATENAISSANCE_NULL))};
        validationExpressionUtil.verifierSiVide(rapport, beneficiaire, propsBeneficiaire);

        boolean verifieAucuneContrainte = true;
        for (SousRapportDto sousRapport : rapport.getRapports().values()) {
            // si un sous rapport n'est pas en erreur, ça signifie qu'on respecte au moins une contrainte
            verifieAucuneContrainte &= sousRapport.getErreur();
        }

        return !verifieAucuneContrainte;
    }

    /**
     * Définit la valeur de personneService.
     * @param personneService la nouvelle valeur de personneService
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Définit la valeur de messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Définit la valeur de validationExpressionUtil.
     * @param validationExpressionUtil la nouvelle valeur de validationExpressionUtil
     */
    public void setValidationExpressionUtil(ValidationExpressionUtil validationExpressionUtil) {
        this.validationExpressionUtil = validationExpressionUtil;
    }

    /**
     * Définit la valeur de paysDao.
     * @param paysDao la nouvelle valeur de paysDao
     */
    public void setPaysDao(PaysDao paysDao) {
        this.paysDao = paysDao;
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définit la valeur de relationTypeDao.
     * @param relationTypeDao la nouvelle valeur de relationTypeDao
     */
    public void setRelationTypeDao(RelationTypeDao relationTypeDao) {
        this.relationTypeDao = relationTypeDao;
    }

    /**
     * Définit la valeur de personneCiviliteDao.
     * @param personneCiviliteDao la nouvelle valeur de personneCiviliteDao
     */
    public void setPersonneCiviliteDao(PersonneCiviliteDao personneCiviliteDao) {
        this.personneCiviliteDao = personneCiviliteDao;
    }
}
