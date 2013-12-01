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
package com.square.fusion.api.util;

/**
 * Classe utilitaire regroupant les messages de l'application.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public final class MessageKeyUtil {

    /** Constructeur. */
    private MessageKeyUtil() { }

    /** Constantes message d'erreur. */
    public static final String ERREUR_FUSION_PERSONNE_INEXISTANTE = "erreur.fusion.personne.inexistante";

    /** Constantes message d'erreur. */
    public static final String ERREUR_FUSION_IMPOSSIBLE_PERSONNES_RATTACHEES_CONTRATS = "erreur.fusion.impossible.personnes.rattachees.contrats";

    /** Constantes message d'erreur. */
    public static final String ERREUR_FUSION_PERSONNE_SOURCE_INEXISTANTE = "erreur.fusion.personne.source.inexistante";

    /** Constantes message d'erreur. */
    public static final String ERREUR_FUSION_PERSONNE_CIBLE_INEXISTANTE = "erreur.fusion.personne.cible.inexistante";

    /** Constantes message d'erreur. */
    public static final String ERREUR_FUSION_IMPOSSIBLE_PERSONNES_OPPS_TRANSFORMEES = "erreur.fusion.impossible.personnes.opps.transformees";

    /** Constantes message d'erreur. */
    public static final String ERREUR_FUSION_IMPOSSIBLE_PERSONNES_BENEFICIAIRES_SUR_OPPS_TRANSFORMEES =
    	"erreur.fusion.impossible.personnes.beneficiaires.sur.opps.transformees";

    /** Constantes message d'erreur. */
    public static final String ERREUR_FUSION_IMPOSSIBLE_PERSONNES_RATTACHEES_CONTRATS_OPPS_TRANSFORMEES =
        "erreur.fusion.impossible.personnes.rattachees.contrats.opps.transformees";

    /** Constantes message d'erreur. */
    public static final String ERREUR_FUSION_IMPOSSIBLE_PERSONNES_RATTACHEES_CONTRATS_BENEFICIAIRES_SUR_OPPS_TRANSFORMEES =
        "erreur.fusion.impossible.personnes.rattachees.contrats.beneficiaires.sur.opps.transformees";

    /** Constantes message d'erreur. */
    public static final String ERREUR_FUSION_IMPOSSIBLE_PERSONNES_OPP_TRANSFORMEE_BENEFICIAIRES_SUR_OPPS_TRANSFORMEES =
        "erreur.fusion.impossible.personnes.opp.transformee.beneficiaires.sur.opps.transformees";

    /** Message. */
	public static final String LOGGER_ERROR_PERSONNE_INEXISTANTE = "logger.error.personne.inexistante";

    /** Message. */
	public static final String LOGGER_INFOS_PERSONNE_CIBLE_SOURCE = "logger.infos.personne.cible.source";

    /** Message. */
	public static final String LOGGER_DEBUG_VALIDATION_FUSION = "logger.debug.validation.fusion";

    /** Message. */
	public static final String LOGGER_INFOS_TRANSFERT_DOCUMENTS = "logger.infos.transfert.documents";

    /** Message. */
	public static final String MESSAGE_NPAI = "message.npai";

    /** Message. */
	public static final String LOGGER_DEBUG_CREATION_ADRESSE_PRINCIPALE_CIBLE = "logger.debug.creation.adresse.principale.cible";

    /** Message. */
	public static final String LOGGER_DEBUG_CREATION_MAIL_CIBLE_NATURE = "logger.debug.creation.mail.cible.nature";

}
