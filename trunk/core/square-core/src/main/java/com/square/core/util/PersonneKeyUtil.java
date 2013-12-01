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
 * Fichier qui regrouppe les clefs des messages pour les personnes.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneKeyUtil {
    /**
     * Constructeur par defaut.
     */
    private PersonneKeyUtil() {
    }

    /**
     * Message d'erreur pour la création de la relation si la civilité de la personne cible est nulle.
     */
    public static final String MESSAGE_ERROR_SAVE_RELATION_CIVILITE_NULL = "message_error_save_relation_civilite_null";

    /**
     * Message d'erreur pour la création de la relation si le prénom de la personne cible est nulle.
     */
    public static final String MESSAGE_ERROR_SAVE_RELATION_PRENOM_NULL = "message_error_save_relation_prenom_null";

    /**
     * Message d'erreur pour la création de la relation si le nom de la personne cible est nulle.
     */
    public static final String MESSAGE_ERROR_SAVE_RELATION_NOM_NULL = "message_error_save_relation_nom_null";

    /**
     * Message d'erreur pour la création de la relation si la date de naissance de la personne cible est nulle.
     */
    public static final String MESSAGE_ERROR_SAVE_RELATION_DATENAISSANCE_NULL = "message_error_save_relation_datenaissance_null";

    /**
     * Message d'erreur pour la création de la relation si la date de début est nulle.
     */
    public static final String MESSAGE_ERROR_SAVE_RELATION_DATEDEBUT_NULL = "message_error_save_relation_datedebut_null";

    /**
     * Message d'erreur pour la création de la relation si le type de relation est nulle.
     */
    public static final String MESSAGE_ERROR_SAVE_RELATION_TYPERELATION_NULL = "message_error_save_relation_typerelation_null";

    /**
     * Message d'erreur si la personne source n'existe pas en base de données.
     */
    public static final String MESSAGE_ERROR_PERSONNE_SOURCE_INEXISTENT_EN_BD = "message_error_personne_source_inexistent_en_bd";

    /**
     * Message d'erreur si la personne source n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_REL_RECH_PERSONNE = "message_erreur_rel_rech_personne";

    /**
     * Message d'erreur si la personne source n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_REL_RECH_CRITERE = "message_erreur_rel_rech_critere";

    /**
     * Message d'erreur si la personne source n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_REL_CU_DATEDEBUT = "message_erreur_rel_cu_datedebut";

    /**
     * Message d'erreur si la personne source n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_REL_CU_TYPE = "message_erreur_rel_cu_type";

    /**
     * Message d'erreur si la personne source n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_REL_CU_DEUXIEME_TYPE = "message_erreur_rel_cu_deuxieme_type";

    /**
     * Message d'erreur si la personne source n'existe pas en base de données.
     */
    public static final String MESSAGE_ERREUR_REL_U_RELATION_EXISTE_PAS = "message_erreur_rel_u_relation_existe_pas";

    /**
     * Message d'erreur si une relation du même type sur ces deux personnes existe déjà.
     */
    public static final String MESSAGE_ERREUR_CONJOINT_DOUBLE = "message_erreur_conjoint_double";

    /**
     * Message si la nature de l'adresse n'est pas fournit.
     */
    public static final String MESSAGE_ERREUR_NATURE_ADRESSE_NULL = "message_erreur_nature_adresse_null";

    /**
     * Message d'erreur si la personne à supprimer n'est pas définie.
     */
    public static final String MESSAGE_ERREUR_SUPPRESSION_PERSONNE_NULL = "message_erreur_suppression_personne_null";

    /**
     * Message d'erreur si la personne à supprimer n'existe pas.
     */
    public static final String MESSAGE_ERREUR_SUPPRESSION_PERSONNE_INEXISTANTE = "message_erreur_suppression_personne_inexistante";

    /**
     * Message d'erreur si la personne n'est pas définie pour le transfert du commercial.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_PERSONNE_NULL = "message_erreur_transfert_commercial_personne_personne_null";

    /**
     * Message d'erreur si le commercial n'est pas défini pour le transfert du commercial.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_COMMERCIAL_NULL = "message_erreur_transfert_commercial_personne_commercial_null";

    /**
     * Message d'erreur si la personne n'existe pas pour le transfert du commercial.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_PERSONNE_INEXISTANTE =
        "message_erreur_transfert_commercial_personne_personne_inexistante";

    /**
     * Message d'erreur si la ressource n'existe pas pour le transfert du commercial.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_COMMERCIAL_PERSONNE_COMMERCIAL_INEXISTANT =
        "message_erreur_transfert_commercial_personne_commercial_inexistant";

    /**
     * Message d'erreur si la personne n'est pas définie pour le transfert de l'agence.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_PERSONNE_NULL = "message_erreur_transfert_agence_personne_personne_null";

    /**
     * Message d'erreur si l'agence n'existe pas pour le transfert de l'agence.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_AGENCE_NULL = "message_erreur_transfert_agence_personne_agence_null";

    /**
     * Message d'erreur si la personne n'existe pas pour le transfert de l'agence.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_PERSONNE_INEXISTANTE = "message_erreur_transfert_agence_personne_personne_inexistante";

    /**
     * Message d'erreur si l'agence n'existe pas pour le transfert de l'agence.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_AGENCE_PERSONNE_AGENCE_INEXISTANTE = "message_erreur_transfert_agence_personne_agence_inexistante";

    /**
     * Message d'erreur si la personne source n'est pas définie pour le transfert de relations.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_RELATIONS_PERSONNE_SOURCE_NULL = "message_erreur_transfert_relations_personne_source_null";

    /**
     * Message d'erreur si la personne cible n'est pas définie pour le transfert de relations.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_RELATIONS_PERSONNE_CIBLE_NULL = "message_erreur_transfert_relations_personne_cible_null";

    /**
     * Message d'erreur si la personne cible n'existe pas pour le transfert de relations.
     */
    public static final String MESSAGE_ERREUR_TRANSFERT_RELATIONS_PERSONNE_CIBLE_INEXISTANTE = "message_erreur_transfert_relations_personne_cible_inexistante";

    /**
     * Message de confirmation.
     */
    public static final String MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_TELEPHONE_ET_EMAIL = "message_confirmation_desactivation_eservice_telephone_et_email";

    /**
     * Message de confirmation.
     */
    public static final String MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_TELEPHONE = "message_confirmation_desactivation_eservice_telephone";

    /**
     * Message de confirmation.
     */
    public static final String MESSAGE_CONFIRMATION_DESACTIVATION_ESERVICE_EMAIL = "message_confirmation_desactivation_eservice_email";

}
