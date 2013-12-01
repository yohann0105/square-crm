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
 * Classe utilitaire regroupant les messages pour les agences.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AgenceKeyUtil {

    /** Constructeur par defaut. */
    private AgenceKeyUtil() {
    }

    /** Message d'erreur lorsqu'une agence n'existe pas. */
    public static final String MESSAGE_ERREUR_AGENCE_INEXISTANTE = "message_erreur_agence_inexistante";

    /** Message d'erreur pour indiquer que le libellé d'une agence est obligatoire. */
    public static final String MESSAGE_ERREUR_AGENCE_LIBELLE_OBLIGATOIRE = "message_erreur_agence_libelle_obligatoire";

    /** Message d'erreur pour indiquer que la région commerciale d'une agence est obligatoire. */
    public static final String MESSAGE_ERREUR_AGENCE_REG_COM_OBLIGATOIRE = "message_erreur_agence_reg_com_obligatoire";

    /** Message d'erreur pour indiquer que la région commerciale d'une agence est inexistante. */
    public static final String MESSAGE_ERREUR_AGENCE_REG_COM_INEXISTANTE = "message_erreur_agence_reg_com_inexistante";

    /** Message d'erreur pour indique qu'on veut modifier une agence supprimée (logiquement). */
    public static final String MESSAGE_ERREUR_UPDATE_AGENCE_SUPPRIMEE = "message_erreur_update_agence_supprimee";
}
