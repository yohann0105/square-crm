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
 * Gestion des messages d'erreur pour les actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionKeyUtil {

    /**
     * Constructeur par defaut.
     */
    private ActionKeyUtil() {
    }

    /**
     * Message d'erreur si le dto de critère de recherche est nul.
     */
    public static final String MESSAGE_ERREUR_RECHERCHE_ACTION_DTO_NULL = "message_erreur_recherche_action_dto_null";

    /** Message d'erreur lorsque la date de l'action est nulle. */
    public static final String MESSAGE_ERREUR_ACTION_DATE_ACTION_NULL = "message_erreur_action_date_action_null";

    /** Message d'erreur lorsque l'identifiant de la personne est nul. */
    public static final String MESSAGE_ERREUR_ACTION_IDPERSONNE_NULL = "message_erreur_action_idpersonne_null";

    /** Message d'erreur lorsque l'identifiant de la personne ne référence aucune personne. */
    public static final String MESSAGE_ERREUR_ACTION_IDPERSONNE_INEXISTANT = "message_erreur_action_idpersonne_inexistant";

    /** Message d'erreur lorsque la nature de l'action est null. */
    public static final String MESSAGE_ERREUR_ACTION_NATURE_ACTION_NULL = "message_erreur_action_nature_action_null";

    /** Message d'erreur lorsque la nature de l'action est inexistante. */
    public static final String MESSAGE_ERREUR_ACTION_NATURE_ACTION_INEXISTANT = "message_erreur_action_nature_action_inexistant";

    /** Message d'erreur lorsque le type de l'action est null. */
    public static final String MESSAGE_ERREUR_ACTION_TYPE_ACTION_NULL = "message_erreur_action_type_action_null";

    /** Message d'erreur lorsque le type de l'action est inexistant. */
    public static final String MESSAGE_ERREUR_ACTION_TYPE_ACTION_INEXISTANT = "message_erreur_action_type_action_inexistant";

    /** Message d'erreur lorsque l'objet est null. */
    public static final String MESSAGE_ERREUR_ACTION_OBJET_NULL = "message_erreur_action_objet_null";

    /** Message d'erreur lorsque l'objet n'existe pas. */
    public static final String MESSAGE_ERREUR_ACTION_OBJET_INEXISTANT = "message_erreur_action_objet_inexistant";

    /** Message d'erreur lorsque le sous objet n'existe pas. */
    public static final String MESSAGE_ERREUR_ACTION_SOUS_OBJET_INEXISTANT = "message_erreur_action_sous_objet_inexistant";

    /** Message d'erreur lorsque la priorité n'existe pas. */
    public static final String MESSAGE_ERREUR_ACTION_PRIORITE_INEXISTANTE = "message_erreur_action_priorite_inexistante";

    /** Message d'erreur lorsque la campagne de l'action n'existe pas. */
    public static final String MESSAGE_ERREUR_ACTION_CAMPAGNE_INEXISTANTE = "message_erreur_action_campagne_inexistante";

    /** Message d'erreur si le dto ActionDto est null. */
    public static final String MESSAGE_ERREUR_ACTION_DTO_NULL = "message_erreur_action_dto_null";

    /** Message d'erreur si on a demandé un rappel sans fournir de temps. */
    public static final String MESSAGE_ERREUR_ACTION_RAPPEL_SANS_TEMPS = "message_erreur_action_rappel_sans_temps";

    /** Message d'erreur si l'identifiant de l'action est null. */
    public static final String MESSAGE_ERREUR_ACTION_IDACTION_NULL = "message_erreur_action_idaction_null";

    /** Message d'erreur si l'agence de l'action est nulle. **/
    public static final String MESSAGE_ERREUR_TRANSFERT_ACTION_AGENCE_NULL = "message_erreur_transfert_action_agence_null";

    /** Message d'erreur si l'agence de l'action est nulle. **/
    public static final String MESSAGE_ERREUR_ACTION_AGENCE_NULL = "message_erreur_action_agence_null";

    /** Message d'erreur si l'identifiant de l'action est inexistant. */
    public static final String MESSAGE_ERREUR_ACTION_IDACTION_INEXISTANT = "message_erreur_action_idaction_inexistant";

    /** Message d'erreur si le statut est null. */
    public static final String MESSAGE_ERREUR_ACTION_STATUT_NULL = "message_erreur_action_statut_null";

    /** Message d'erreur si le statut n'existe pas. */
    public static final String MESSAGE_ERREUR_ACTION_STATUT_INEXISTANT = "message_erreur_action_statut_inexistant";

    /** Message d'erreur si le résultat est inexistant. */
    public static final String MESSAGE_ERREUR_ACTION_RESULTAT_INEXISTANT = "message_erreur_action_resultat_inexistant";

    /** Message d'erreur si l'identifiant de notification n'existe pas. */
    public static final String MESSAGE_ERREUR_ACTION_IDNOTIFICATION_INEXISTANT = "message_erreur_action_idnotification_inexistant";

    /** Message d'erreur si le commentaire est incomplet. */
    public static final String MESSAGE_ERREUR_ACTION_COMMENTAIRE_INCOMPLET = "message_erreur_action_commentaire_incomplet";

    /** Message d'erreur si l'agence est inexistante en base de données. */
    public static final String MESSAGE_ERREUR_ACTION_AGENCE_INEXISTANTE = "message_erreur_action_agence_inexistante";

    /** Message d'erreur si le commercial n'existe pas en base de données. */
    public static final String MESSAGE_ERREUR_ACTION_COMMERCIAL_INEXISTANTE = "message_erreur_action_commercial_inexistante";

    /** Message d'erreur si l'identifiant de l'action source n'existe pas. */
    public static final String MESSAGE_ERREUR_ACTION_IDSOURCE_INEXISTANTE = "message_erreur_action_idsource_inexistante";

    /** Message d'erreur si l'action source spécifiée n'existe pas. */
    public static final String MESSAGE_ERREUR_ACTION_SOURCE_INEXISTANTE = "message_erreur_action_source_inexistante";

    /** Message d'erreur si l'opportunité n'existe pas. */
    public static final String MESSAGE_ERREUR_ACTION_OPPORTUNITE_INEXISTANTE = "message_erreur_action_opportunite_inexistante";

    /** Message d'erreur si l'id ressource est nul. */
    public static final String MESSAGE_ERREUR_RESSOURCE_NULL = "message_erreur_ressource_null";

    /** Message d'erreur si la date min de recherche d'action est nulle. */
    public static final String MESSAGE_ERREUR_ACTION_DATE_MIN_NULL = "message_erreur_action_date_min_null";

    /** Message d'erreur si la date max de recherche d'action est nulle. */
    public static final String MESSAGE_ERREUR_ACTION_DATE_MAX_NULL = "message_erreur_action_date_max_null";

    /** Message d'erreur si la date min est supérieure à la date max. */
    public static final String MESSAGE_ERREUR_ACTION_DATE_MIN_SUP_DATE_MIN = "message_erreur_action_date_min_sup_date_min";

    /** Message d'erreur si l'utilisateur demandé n'existe pas. */
    public static final String MESSAGE_ERREUR_ACTION_RESSOURCE_INEXISTANTE = "message_erreur_action_ressource_inexistante";

    /** Message d'erreur pour une source null pour un transfert d'opportunité. */
    public static final String MESSAGE_ERREUR_ACTION_TRANSFERT_PERSONNE_SOURCE_NULL = "message_erreur_action_transfert_personne_source_null";

    /** Message d'erreur pour une source null pour un transfert d'opportunité. */
    public static final String MESSAGE_ERREUR_ACTION_TRANSFERT_PERSONNE_CIBLE_NULL = "message_erreur_action_transfert_personne_cible_null";

    /** Message d'erreur pour une cible inexistante pour un transfert d'opportunité. */
    public static final String MESSAGE_ERREUR_ACTION_TRANSFERT_PERSONNE_CIBLE_INEXISTANTE = "message_erreur_action_transfert_personne_cible_inexistante";

    /** Message d'erreur lorsque le résultat de la nature de l'action n'est pas renseigné. */
    public static final String MESSAGE_ERREUR_ACTION_NATURE_RESULTAT_ACTION_NULL = "message_erreur_action_nature_resultat_action_null";

    /** Message d'erreur lorsque le résultat de la nature de l'action n'est pas renseigné. */
    public static final String MESSAGE_ERREUR_ACTION_NATURE_RESULTAT_INEXISTANT = "message_erreur_action_nature_resultat_inexistant";

    /** Message d'erreur lorsqu'une action est inexistante. */
    public static final String MESSAGE_ERREUR_ACTION_INEXISTANTE = "message_erreur_action_inexistante";

    /** Message d'erreur lorsqu'un identifiant extérieur de document est null. */
    public static final String MESSAGE_ERREUR_ACTION_DOCUMENT_EID_NULL = "message_erreur_action_document_eid_null";

    /** Message d'erreur si on passe une action de relance à "Terminé" si l'opportunité associée est en cours. */
    public static final String MESSAGE_ERREUR_ACTION_RELANCE_TERMINEE_OPPORTUNITE_EN_COURS = "message_erreur_action_relance_terminee_opportunite_en_cours";

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_ACTION_DATE_ACTION_INVALIDE = "message_erreur_action_date_action_invalide";

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_ACTION_TERMINEE_SANS_ATTRIBUTION = "message_erreur_action_terminee_sans_attribution";

    /** Titre de l'email de fin d'action. */
    public static final String TITRE_EMAIL_FIN_ACTION = "titre_email_fin_action";

    /** Message de l'email de fin d'action. */
    public static final String MESSAGE_EMAIL_FIN_ACTION = "message_email_fin_action";

    /** Message d'erreur si le créateur d'une action ne possède pas d'email. */
    public static final String MESSAGE_ERREUR_EMAIL_CREATEUR_ACTION_VIDE = "message_email_createur_action_vide";

    /** Message d'erreur paramètre idAction obligatoire pour l'appel au service supprimerAction. */
    public static final String MESSAGE_ERREUR_SUPPRIMER_ACTION_PARAM_ID_ACTION_OBLIGATOIRE =
        "message_erreur_supprimer_action_param_id_action_obligatoire";

    /** Message d'erreur l'action à supprimer n'existe pas lors de l'appel au service supprimerAction. */
    public static final String MESSAGE_ERREUR_SUPPRIMER_ACTION_ACTION_INEXISTANTE =
        "message_erreur_supprimer_action_action_inexistante";

    /** Message d'erreur si la durée d'action est inexistante. */
    public static final String MESSAGE_ERREUR_ACTION_DUREE_INEXISTANTE = "message_erreur_action_duree_inexistante";

    /** Message d'erreur indiquant qu'il n'est pas possible de rendre l'action visible sur l'agenda. */
    public static final String MESSAGE_ERREUR_ACTION_IMPOSSIBLE_VISIBLE_AGENDA = "message_erreur_action_impossible_visible_agenda";

    /** Message d'erreur lorsque la nature de resultat n'est pas renseigné sur une nature d'actionvisite sortante. */
    public static final String MESSAGE_ERREUR_ACTION_NATURE_RESULTAT_VISITE_SORTANTE =  "message_erreur_action_nature_resultat_visite_sortante";
}
