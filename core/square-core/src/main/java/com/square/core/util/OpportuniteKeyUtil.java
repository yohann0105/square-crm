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
 * Messages d'erreur pour les opportunités.
 * @author cblanchard - SCUB
 */
public class OpportuniteKeyUtil {

	/**Message d'erreur lorsque le csp de la personne référent est null. */
	public static final String MESSAGE_ERREUR_OPP_CREATION_PERSONNE_REFERENT_CSP = "message_error_creer_opportunite_personne_referent_csp";

	/**Message d'erreur lorsque la caisse de la personne référent est null. */
	public static final String MESSAGE_ERREUR_OPP_CREATION_PERSONNE_REFERENT_CAISSE = "message_error_creer_opportunite_personne_referent_caisse";

    /** Message d'erreur lorsque le dto pour la création est null. */
    public static final String MESSAGE_ERREUR_OPP_DTO_CREATION_NULL = "message_erreur_opp_dto_creation_null";

    /** Message d'erreur lorsque la nature n'est pas définie. */
    public static final String MESSAGE_ERREUR_OPP_NATURE_NULL = "message_erreur_opp_nature_null";

    /** Message d'erreur lorsque la nature est inexistante. */
    public static final String MESSAGE_ERREUR_OPP_NATURE_INEXISTANT = "message_erreur_opp_nature_inexistant";

    /** Message d'erreur lorsque la campagne n'existe pas. */
    public static final String MESSAGE_ERREUR_OPP_CAMPAGNE_INEXISTANTE = "message_erreur_opp_campagne_inexistante";

    /** Message d'erreur lorsque l'agence n'existe pas. */
    public static final String MESSAGE_ERREUR_OPP_AGENCE_INEXISTANTE = "message_erreur_opp_agence_inexistante";

    /** Message d'erreur lorsque la ressource n'existe pas. */
    public static final String MESSAGE_ERREUR_OPP_RESSOURCE_INEXISTANTE = "message_erreur_opp_ressource_inexistante";

    /** Message d'erreur lorsque la personne. */
    public static final String MESSAGE_ERREUR_OPP_PERSONNE_NULL = "message_erreur_opp_personne_null";

    /** Message d'erreur lorsque la personne n'existe pas. */
    public static final String MESSAGE_ERREUR_OPP_PERSONNE_INEXISTANTE = "message_erreur_opp_personne_inexistante";

    /** Message d'erreur lorsque le type est null. */
    public static final String MESSAGE_ERREUR_OPP_TYPE_NULL = "message_erreur_opp_type_null";

    /** Message d'erreur lorsque le type n'existe pas. */
    public static final String MESSAGE_ERREUR_OPP_TYPE_INEXISTANT = "message_erreur_opp_type_inexistant";

    /** Message d'erreur lorsque l'objet est null. */
    public static final String MESSAGE_ERREUR_OPP_OBJET_NULL = "message_erreur_opp_objet_null";

    /** Message d'erreur lorsque l'objet n'existe pas. */
    public static final String MESSAGE_ERREUR_OPP_OBJET_INEXISTANT = "message_erreur_opp_objet_inexistant";

    /** Message d'erreur lorsque le sous objet n'existe pas. */
    public static final String MESSAGE_ERREUR_OPP_SOUS_OBJET_INEXISTANT = "message_erreur_opp_sous_objet_inexistant";

    /** Message d'erreur lorsque l'action source n'existe pas. */
    public static final String MESSAGE_ERREUR_OPP_ACTION_SOURCE_INEXISTANTE = "message_erreur_opp_action_source_inexistante";

    /** Message pour la confirmation de création. */
    public static final String MESSAGE_CONFIRMATION_CREATION_OPPORTUNITE = "message_confirmation_creation_opportunite";

    /** Message d'erreur lorsque l'action source possède déjà une opportunité active. */
    public static final String MESSAGE_ERREUR_OPP_DEJA_ACTIVE = "message_erreur_opp_deja_active";

    /** Message d'erreur lorsque l'agence et la ressource sont null. */
    public static final String MESSAGE_ERREUR_OPP_AGENGE_RESSOURCE_NULL = "message_erreur_opp_agenge_ressource_null";

    /** Message d'erreur si le statut de l'opportunité n'existe pas. */
    public static final String MESSAGE_ERREUR_OPPORTUNITE_STATUT_INEXISTANT = "message_erreur_opportunite_statut_inexistant";

    /** Message d'erreur si le dto de critère de recherche est null. */
    public static final String MESSAGE_ERREUR_OPP_DTO_CRITERE_RECHERCHE_NULL = "message_erreur_opp_dto_critere_recherche_null";

    /** Message d'erreur lorsque le dto de modification est null. */
    public static final String MESSAGE_ERREUR_OPP_MODIFICATION_DTO_NULL = "message_erreur_opp_modification_dto_null";

    /** Message d'erreur lorsque l'identifiant de l'opportunité pour la modification est null. */
    public static final String MESSAGE_ERREUR_OPP_ID_OPP_NULL = "message_erreur_opp_id_opp_null";

    /** Message d'erreur lorsque l'opportunité n'existe pas. */
    public static final String MESSAGE_ERREUR_OPP_ID_OPP_INEXISTANT = "message_erreur_opp_id_opp_inexistant";

    /** Message d'erreur lorsque la statut est null. */
    public static final String MESSAGE_ERREUR_OPP_STATUT_NULL = "message_erreur_opp_statut_null";

    /** Message d'erreur pour une ressource null. */
    public static final String MESSAGE_ERREUR_OPP_RESSOURCE_NULL = "message_erreur_opp_ressource_null";

    /** Message d'erreur pour une source null pour un transfert d'opportunité. */
    public static final String MESSAGE_ERREUR_OPP_TRANSFERT_PERSONNE_SOURCE_NULL = "message_erreur_opp_transfert_personne_source_null";

    /** Message d'erreur pour une source null pour un transfert d'opportunité. */
    public static final String MESSAGE_ERREUR_OPP_TRANSFERT_PERSONNE_CIBLE_NULL = "message_erreur_opp_transfert_personne_cible_null";

    /** Message d'erreur pour une cible inexistante pour un transfert d'opportunité. */
    public static final String MESSAGE_ERREUR_OPP_TRANSFERT_PERSONNE_CIBLE_INEXISTANTE = "message_erreur_opp_transfert_personne_cible_inexistante";

    /** Message d'erreur lorsque le créateur de l'opportunité n'existe pas. */
    public static final String MESSAGE_ERREUR_OPP_CREATEUR_INEXISTANT = "message_erreur_opp_createur_inexistant";

    /** Message d'erreur lorsque la famille n'est pas éligible pour la création d'opportunité. */
    public static final String MESSAGE_ERREUR_OPP_FAMILLE_NON_ELIGIBLE = "message_erreur_opp_famille_non_eligible";

    /** Message d'erreur lorsque l'on tente de créer une opportunité sur une personne décédée. */
    public static final String MESSAGE_ERREUR_OPP_PERSONNE_DECEDEE = "message_erreur_opp_personne_decedee";

}
