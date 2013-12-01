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
package com.square.core.util;

/**
 * Gestion des messages.
 * @author cblanchard - SCUB
 */
public final class PersonnePhysiqueKeyUtil {

    private PersonnePhysiqueKeyUtil() {
    };

    /**
     * Message d'erreur pour la recherche si on donne un dto null au service.
     */
    public static final String MESSAGE_ERROR_SEARCH_DTO_NULL = "message_error_search_dto_null";

    /**
     * Message d'erreur pour la création d'une personne sans civilité.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_CIVILITE_NULL = "message_error_save_personne_civilite_null";

    /**
     * Message d'erreur pour la création d'une personne sans prénom.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_BAD_FORMAT = "message_error_save_personne_prenom_bad_format";

    /**
     * Message d'erreur pour la création d'une personne sans nom.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_NOM_BAD_FORMAT = "message_error_save_personne_nom_bad_format";

    /**
     * Message d'erreur pour la création d'une personne sans prénom.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_PRENOM_NULL = "message_error_save_personne_prenom_null";

    /**
     * Message d'erreur pour la création d'une personne sans nom.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_NOM_NULL = "message_error_save_personne_nom_null";

    /**
     * Message d'erreur pour la création d'une personne sans date de naissance.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_NULL = "message_error_save_personne_datenaissance_null";

    /**
     * Message d'erreur pour la création d'une personne avec une date de naissance erronée.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_DATENAISSANCE_INVALIDE = "message_error_save_personne_datenaissance_invalide";

    /**
     * Message d'erreur pour la création d'une personne avec une caisse null.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_CAISSE_NULL = "message_error_save_personne_caisse_null";

    /**
     * Message d'erreur pour la création d'une personne avec un régime null.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_REGIME_NULL = "message_error_save_personne_regime_null";

    /**
     * Message d'erreur pour la création d'une personne sans email.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_EMAIL_NULL = "message_error_save_personne_email_null";

    /**
     * Message d'erreur pour la création d'une personne sans numéro de téléphone.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_NATURETELEPHONE_NULL = "message_error_save_personne_naturetelephone_null";

    /**
     * Message d'erreur pour la création d'une personne nature de téléphone non conforme.
     */
    public static final String MESSAGE_ERROR_MAUVAISE_NATURE_TELEPHONE = "message_error_mauvaise_nature_telephone";

    /**
     * Nature d'email vide.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_NATUREEMAIL_NULL = "message_error_save_personne_natureemail_null";

    /**
     * Message d'erreur si il y a un indicatif de téléphone null.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_INDICATIFTEL_NULL = "message_error_save_personne_indicatiftel_null";

    /**
     * Message d'erreur pour la création d'une personne sans numéro de téléphone.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_NUMTELEPHONE_NULL = "message_error_save_personne_numtelephone_null";

    /**
     * Message d'erreur pour la création d'une personne sans profession.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_PROFESSION_NULL = "message_error_save_personne_profession_null";

    /**
     * Message d'erreur pour la création d'un conjoint sans civilité.
     */
    public static final String MESSAGE_ERROR_SAVE_BENEFICIAIRE_CIVILITE_NULL = "message_error_save_beneficiaire_civilite_null";

    /**
     * Message d'erreur pour la création d'un beneficiaire sans prénom.
     */
    public static final String MESSAGE_ERROR_SAVE_BENEFICIAIRE_PRENOM_NULL = "message_error_save_beneficiaire_prenom_null";

    /**
     * Message d'erreur pour la création d'un beneficiaire sans prénom.
     */
    public static final String MESSAGE_ERROR_SAVE_BENEFICIAIRE_NOM_NULL = "message_error_save_beneficiaire_nom_null";

    /**
     * Message d'erreur pour la création d'un beneficiaire sans date de naissance.
     */
    public static final String MESSAGE_ERROR_SAVE_BENEFICIAIRE_DATENAISSANCE_NULL = "message_error_save_beneficiaire_datenaissance_null";

    /**
     * Message d'erreur pour la création d'un beneficiaire sans type de relation.
     */
    public static final String MESSAGE_ERREUR_AJOUT_BENEFICIAIRE_TYPERELATION_NULL = "message_erreur_ajout_beneficiaire_typerelation_null";

    /**
     * Message d'erreur pour la création d'une adresse sans identifiant externe.
     */
    public static final String MESSAGE_ERROR_SAVE_ADRESSE_IDEXT_NULL = "message_error_save_adresse_idext_null";

    /**
     * Message d'erreur pour la création d'une adresse sans numéro de voie.
     */
    public static final String MESSAGE_ERROR_SAVE_ADRESSE_NUMVOIE_NULL = "message_error_save_adresse_numvoie_null";

    /**
     * Message d'erreur pour la création d'une adresse sans nature de voie.
     */
    public static final String MESSAGE_ERROR_SAVE_ADRESSE_NATUREVOIE_NULL = "message_error_save_adresse_naturevoie_null";

    /**
     * Message d'erreur pour la création d'une adresse sans adresse.
     */
    public static final String MESSAGE_ERROR_SAVE_ADRESSE_ADRESSE_NULL = "message_error_save_adresse_adresse_null";

    /**
     * Message d'erreur pour la création d'une adresse sans codePostal.
     */
    public static final String MESSAGE_ERROR_SAVE_ADRESSE_CODEPOSTAL_NULL = "message_error_save_adresse_codepostal_null";

    /**
     * Message d'erreur pour la création d'une adresse sans ville.
     */
    public static final String MESSAGE_ERROR_SAVE_ADRESSE_VILLE_NULL = "message_error_save_adresse_ville_null";

    /**
     * Message d'erreur pour la création d'une adresse sans pays.
     */
    public static final String MESSAGE_ERROR_SAVE_ADRESSE_PAYS_NULL = "message_error_save_adresse_pays_null";

    /**
     * Message d'erreur pour la création d'une adresse sans drapeau pays.
     */
    public static final String MESSAGE_ERROR_SAVE_ADRESSE_DRAPPAYS_NULL = "message_error_save_adresse_drappays_null";

    /**
     * Message d'erreur si le département est null.
     */
    public static final String MESSAGE_ERROR_SAVE_ADRESSE_DEPARTEMENT_NULL = "message_error_save_adresse_departement_null";

    /**
     * Message d'erreur format numéro de téléphone incorrect.
     */
    public static final String MESSAGE_ERROR_BAD_NUMTELEPHONE = "message_error_bad_numtelephone";

    /**
     * Message d'erreur format email incorrect.
     */
    public static final String MESSAGE_ERROR_BAD_EMAIL = "message_error_bad_email";

    /**
     * Message pour personne créée.
     */
    public static final String MESSAGE_INFO_SAVE_PERSONNE_CREATED = "message_info_save_personne_created";

    /**
     * Message pour la impossibilité de convertir une personne en personneSimpleDto.
     */
    public static final String MESSAGE_ERROR_CREATE_PERSONNE_SIMPLE_DTO = "message_error_create_personne_simple_dto";

    /**
     * Message d'erreur si la source du mapping n'est pas bon.
     */
    public static final String MESSAGE_ERREUR_MAUVAISE_SOURCE_MAPPING = "message_erreur_mauvaise_source_mapping";

    /**
     * Message d'erreur si la source n'a pas d'identifiant.
     */
    public static final String MESSAGE_ERREUR_MAUVAIS_IDENTIFIANT_SOURCE = "message_erreur_mauvais_identifiant_source";

    /**
     * Message d'erreur si la source n'a pas de date de naissnce.
     */
    public static final String MESSAGE_ERREUR_MAUVAISE_DATE_NAISSANCE = "message_erreur_mauvaise_date_naissance";

    /**
     * Message d'erreur si la civilité n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_CIVILITE_INEXISTENT_EN_BD = "message_erreur_civilite_inexistent_en_bd";

    /**
     * Message d'erreur si la nature du téléphone n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_NATURE_TELEPHONE_INEXISTENT_EN_BD = "message_erreur_nature_telephone_inexistent_en_bd";

    /**
     * Message d'erreur si la profession est inexistant en base de données.
     */
    public static final String MESSAGE_ERREUR_PROFESSION_INEXISTENT_EN_BD = "message_erreur_profession_inexistent_en_bd";

    /**
     * Message d'erreur si le code postal n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_CODEPOSTAL_INEXISTENT_EN_BD = "message_erreur_codepostal_inexistent_en_bd";

    /**
     * Message d'erreur si la ville n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_VILLE_INEXISTENT_EN_BD = "message_erreur_ville_inexistent_en_bd";

    /**
     * Message d'erreur si le n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_PAYS_INEXISTENT_EN_BD = "message_erreur_pays_inexistent_en_bd";

    /**
     * Message d'information si une personne est créée correctement.
     */
    public static final String MESSAGE_INFO_PERSONNE_PHYSIQUE_CREEE = "message_info_personne_physique_creee";

    /**
     * Message d'erreur si PersonneDto est null.
     */
    public static final String MESSAGE_ERREUR_PERSONNE_DTO_NULL = "message_erreur_personne_dto_null";

    /**
     * Message d'erreur si BeneficiaireDto est null.
     */
    public static final String MESSAGE_ERREUR_BENEFICIAIRE_DTO_NULL = "message_erreur_beneficiaire_dto_null";

    /**
     * Message d'erreur si PersonneDto est nulL.
     */
    public static final String MESSAGE_ERREUR_ADRESSE_DTO_NULL = "message_erreur_adresse_dto_null";

    /**
     * Message d'erreur si telephoneDto est nulL.
     */
    public static final String MESSAGE_ERREUR_TELEPHONE_DTO_NULL = "message_erreur_telephone_dto_null";

    /**
     * Message d'erreur si le numéro de client est null.
     */
    public static final String MESSAGE_ERREUR_SAVE_PERSONNE_NUMCLIENT_NULL = "message_erreur_save_personne_numclient_null";

    /**
     * Message d'information si une relation est créée correctement.
     */
    public static final String MESSAGE_INFO_RELATION_CREEE = "message_info_relation_creee";

    /**
     * Message d'information si une personne est modifiée correctement.
     */
    public static final String MESSAGE_INFO_PERSONNE_PHYSIQUE_MODIFIER = "message_info_personne_physique_modifier";

    /**
     * Message d'erreur si une caisse n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_CAISSE_INEXISTENT_EN_BD = "message_erreur_caisse_inexistent_en_bd";

    /**
     * Message d'erreur si la nature de la personne est nulle.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_NATURE_NULL = "message_error_save_personne_nature_null";

    /**
     * Message d'erreur si la nature n'existe pas en base de données.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_NATURE_INEXISTENT_EN_BD = "message_error_save_personne_nature_inexistent_en_bd";

    /**
     * Message d'erreur si un regime n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_CAISSEREGIME_INEXISTENT_EN_BD = "message_erreur_caisseregime_inexistent_en_bd";

    /**
     * Message d'erreur si un statut n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_STATUT_INEXISTENT_EN_BD = "message_erreur_statut_inexistent_en_bd";

    /**
     * Message d'erreur si un segment n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_SEGMENT_INEXISTENT_EN_BD = "message_erreur_segment_inexistent_en_bd";

    /**
     * Message d'erreur si une catégorie socio professionnelle n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_CSP_INEXISTENT_EN_BD = "message_erreur_csp_inexistent_en_bd";

    /**
     * Message d'erreur si un réseau n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_RESEAU_INEXISTENT_EN_BD = "message_erreur_reseau_inexistent_en_bd";

    /**
     * Message d'erreur si la situation familiale est nulle.
     */
    public static final String MESSAGE_ERREUR_SITUATIONFAMILIALE_NULL = "message_erreur_situationfamiliale_null";

    /**
     * Message d'erreur si une situation familiale n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_SITUATIONFAMILIALE_INEXISTENT_EN_BD = "message_erreur_situationfamiliale_inexistent_en_bd";

    /**
     * Message d'erreur si un réseau est null.
     */
    public static final String MESSAGE_ERREUR_SAVE_PERSONNE_RESEAU_NULL = "message_erreur_save_personne_reseau_null";

    /**
     * Message d'erreur si un csp est null.
     */
    public static final String MESSAGE_ERREUR_SAVE_PERSONNE_CSP_NULL = "message_erreur_save_personne_csp_null";

    /**
     * Message d'erreur si une ressource n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_RESSOURCE_INEXISTENT_EN_BD = "message_erreur_ressource_inexistent_en_bd";

    /**
     * Message d'erreur si la personne recherché n'existe pas en base.
     */
    public static final String MESSAGE_ERREUR_PERSONNE_INEXISTANTE = "message_erreur_personne_inexistante";

    /**
     * Message d'erreur lorsque la personne n'a plus d'adresse principale.
     */
    public static final String MESSAGE_ERREUR_ADRESSE_INEXISTENT_EN_BD = "message_erreur_adresse_inexistante_en_bd";

    /**
     * Message d'erreur lorsque la personne n'a plus d'adresse principale.
     */
    public static final String MESSAGE_ERREUR_AUCUNE_ADRESSE_PRINCIPALE = "message_erreur_aucune_adresse_principale";

    /**
     * Message d'erreur lorsque la personne possède plusieurs adresses principales.
     */
    public static final String MESSAGE_ERREUR_ADRESSE_PRINCIPALE_DUPLIQUEE = "message_erreur_adresse_principale_dupliquee";

    /**
     * Message d'erreur si le téléphone et l'email des coordonnée n'est pas fournit.
     */
    public static final String MESSAGE_ERREUR_COORDONNEE_OBLIGATOIRE = "message_erreur_coordonnees_obligatoire";

    /**
     * Messsage d'erreur si le nom de la voie n'est pas définie lors d'un enregistrement/mise à jour de l'adresse.
     */
    public static final String MESSAGE_ERREUR_INSERTION_ADRESSE_MAUVAIS_NOMVOIE = "message_erreur_insertion_adresse_mauvais_nomvoie";

    /**
     * Message d'erreur si la date de début de l'adresse n'est pas définie.
     */
    public static final String MESSAGE_ERREUR_ADRESSE_DATE_DEBUT = "message_erreur_adresse_date_debut";

    /**
     * Message d'erreur lorsque la nature de l'adresse n'est pas définie.
     */
    public static final String MESSAGE_ERREUR_ADRESSE_NATURE = "message_erreur_adresse_nature";

    /**
     * Message d'erreur lorsque la nature du téléphone n'est pas définie.
     */
    public static final String MESSAGE_ERREUR_TELEPHONE_NATURE = "message_erreur_telephone_nature";

    /**
     * Message d'erreur lorsque le numéro de téléphone n'est pas défini.
     */
    public static final String MESSAGE_ERREUR_TELEPHONE_NUMERO = "message_erreur_telephone_numero";

    /**
     * Message d'erreur lorsque le pays du téléphone n'est pas défini.
     */
    public static final String MESSAGE_ERREUR_TELEPHONE_PAYS = "message_erreur_telephone_pays";

    /**
     * Message d'erreur si la commune demandé n'existe pas.
     */
    public static final String MESSAGE_ERREUR_COMMUNE_INEXISTANTE = "message_erreur_commune_inexistante";

    /**
     * Message d'erreur si le téléphone demandé n'existe pas.
     */
    public static final String MESSAGE_ERREUR_TELEPHONE_INEXISTENT_EN_BD = "message_erreur_telephone_inexistant";

    /**
     * Message d'erreur si le téléphone demandé n'existe pas.
     */
    public static final String MESSAGE_ERREUR_EMAIL_INEXISTENT_EN_BD = "message_erreur_email_inexistant";

    /**
     * Message d'erreur si la nature de l'adresse n'existe pas.
     */
    public static final String MESSAGE_ERREUR_NATURE_ADRESSE_INEXISTANT = "message_erreur_nature_adresse_inexistant";

    /**
     * Message d'erreur si le département de l'adresse n'existe pas.
     */
    public static final String MESSAGE_ERREUR_DEPARTEMENT_INEXITANT = "message_erreur_departement_inexitant";

    /**
     * Message d'erreur si le type de voie de l'adresse n'existe pas.
     */
    public static final String MESSAGE_ERREUR_TYPEVOIE_INEXISTANT = "message_erreur_typevoie_inexistant";

    /**
     * Message d'erreur si l'identifiant de la personne dans les coordonnées n'est pas fournit.
     */
    public static final String MESSAGE_ERREUR_PERSONNE_NULL = "message_erreur_personne_null";

    /**
     * Message d'erreur si le type de relaton n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_TYPERELATION_INEXISTENT_EN_BD = "message_erreur_typerelation_inexistent_en_bd";

    /**
     * Message d'erreur si la nature de l'email n'existe pas.
     */
    public static final String MESSAGE_ERREUR_NATURE_EMAIL_INEXISTANT = "message_erreur_nature_email_inexistant";

    /**
     * Message d'erreur si le dto de critère de recherche est null.
     */
    public static final String MESSAGE_ERREUR_DTO_RECHERCHE_NULL = "message_erreur_dto_recherche_null";

    /**
     *Message d'erreur si le dto email est null.
     */
    public static final String MESSAGE_ERREUR_EMAIL_DTO_NULL = "message_erreur_email_dto_null";

    /**
     * Message d'erreur si l'adresse principale est désactivé sans adresse secondaire.
     */
    public static final String MESSAGE_ERREUR_ADRESSE_SECONDAIRE_NULL_ADRESSE_PRINCIPALE_DATE_FIN =
        "message_erreur_adresse_secondaire_null_adresse_principale_date_fin";

    /**
     * Message d'erreur si la personne recherché n'existe pas en base.
     */
    public static final String MESSAGE_ERREUR_ADRESSE_DOUBLON = "message_erreur_adresse_doublon";

    /**
     * Message d'erreur si l'adresse de l'email est mal formé.
     */
    public static final String MESSAGE_ERREUR_EMAIL_ADRESSE = "message_erreur_email_adresse";

    /**
     * Message d'erreur si le nom de jeune fille est null.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_NOMJEUNEFILLE_NULL = "message_error_save_personne_nomjeunefille_null";

    /**
     * Message d'erreur si le segment de la personne est null.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_SEGMENT_NULL = "message_error_save_personne_segment_null";

    /**
     * Numéro de sécurité sociale invalide.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_NUMERO_SECURITE_SOCIALE_INVALIDE = "message_error_save_personne_numero_securite_sociale_invalide";

    /**
     * Numéro de sécurité sociale null.
     */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_NUMERO_SECURITE_SOCIALE_NULL = "message_error_save_personne_numero_securite_sociale_vide";

    /**
     * Message d'erreur lorsqu'un critères n'est pas bon.
     */
    public static final String MESSAGE_ERREUR_MAUVAIS_CRITERE = "message_erreur_mauvais_critere";

    /**
     * Message d'erreur s'il n y a aucun email.
     */
    public static final String MESSAGE_ERREUR_AUCUN_EMAIL = "message_erreur_aucun_email";

    /**
     * Message d'erreur s'il n'y a aucun téléphone dans la liste.
     */
    public static final String MESSAGE_ERREUR_AUCUN_TELEPHONE = "message_erreur_aucun_telephone";

    /**
     * Libelle en couple.
     */
    public static final String LIBELLE_EN_COUPLE = "libelle_en_couple";

    /**
     * Libelle célibataire.
     */
    public static final String LIBELLE_CELIBATAIRE = "libelle_celibataire";

    /**
     * Libelle enfant.
     */
    public static final String LIBELLE_ENFANT = "libelle_enfant";

    /**
     * Message d'erreur si la nature du téléphone n'est pas bonne.
     */
    public static final String MESSAGE_ERREUR_MAUVAISE_NATURE_TELEPHONE = "message_erreur_mauvaise_nature_telephone";

    /**
     * Libelle enfants.
     */
    public static final String LIBELLE_ENFANTS = "libelle_enfants";

    /**
     * Libelle enfants.
     */
    public static final String LIBELLE_AGE = "libelle_age";

    /**
     * Message d'erreur si l'agence n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_AGENCE_INEXISTENT_EN_BD = "message_erreur_agence_inexistent_en_bd";

    /**
     * Message d'erreur si le commercial n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_PERSONNE_COMMERCIAL_INEXISTANTE = "message_erreur_personne_commercial_inexistante";

    /**
     * Message d'erreur si l'agence n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_PERSONNE_AGENCE_INEXISTANTE = "message_erreur_personne_agence_inexistante";

    /**
     * Message d'erreur si l'agence n'est pas renseignée.
     */
    public static final String MESSAGE_ERREUR_SAVE_PERSONNE_AGENCE_NULL = "message_erreur_save_personne_agence_null";

    /**
     * Message d'erreur si l'attribution n'est pas renseignée.
     */
    public static final String MESSAGE_ERREUR_PERSONNE_ATTRIBUTION_INEXISTANTE = "message_erreur_personne_attribution_inexistante";

    /**
     * Message d'erreur lorsque l'assuré (doublon) sélectionné n'existe pas.
     */
    public static final String MESSAGE_ERREUR_PERSONNE_ASSURE_SELECTIONNE_INEXISTANTE = "message_erreur_personne_assure_selectionne_inexistante";

    /**
     * Message d'erreur lorsqu'un bénéficiaire (doublon) sélectionné n'existe pas.
     */
    public static final String MESSAGE_ERREUR_PERSONNE_BENEFICIAIRE_SELECTIONNE_INEXISTANTE = "message_erreur_personne_beneficiaire_selectionne_inexistante";

    /** Message d'erreur lorsque l'utilisateur essaye de modifier la nature d'une personne et que ça n'est pas possible. */
    public static final String MESSAGE_ERREUR_MODIFICATION_NATURE_PERSONNE_IMPOSSIBLE = "message_erreur_modification_nature_personne_impossible";

    /** Message d'erreur pour une nature de personne inexistante. */
    public static final String MESSAGE_ERREUR_NATURE_PERSONNE_INEXISTANTE = "message_erreur_nature_personne_inexistante";

    /** Message d'erreur pour une adresse trop longue. */
    public static final String MESSAGE_ERROR_ADRESSE_TROP_LONGUE = "message_erreur_adresse_trop_longue";

    /** Message d'erreur lorsqu'un code postal - commune n'existe pas. */
    public static final String MESSAGE_ERREUR_CODEPOSTAL_COMMUNE_INEXISTANT_EN_BD = "message_erreur_codepostal_commune_inexistant_en_bd";

    /** Message d'erreur pour un numéro de voie dont le format est incorrect. */
    public static final String MESSAGE_ERROR_FORMAT_NUM_VOIE_INCORRECT = "message_erreur_format_num_voie_incorrect";

    /** Message d'erreur si la liste de personnes à supprimer est vide. */
    public static final String MESSAGE_ERREUR_LISTE_PERSONNES_SUPPRIMER_VIDE = "message_erreur_liste_personnes_supprimer_vide";

    /** Message de confirmation pour indiquer si il faut impacter la famille. */
    public static final String CONFIRMATION_IMPACTER_FAMILLE = "confirmation_impacter_famille";
}
