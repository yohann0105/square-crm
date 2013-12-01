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
 * Classe utilitaire à utiliser pour formater.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public final class FormatUtil {

    /**
     * Supprime les différents formatages appliqués au numéro de téléphone.
     * @param numeroTelelephoneFormate le numéro de téléphone formaté.
     * @return le numéro de téléphone sans format.
     */
    public String supprimerFormatNumTel(String numeroTelelephoneFormate) {
        // On supprime les caractères superflus
        String numeroTelephone = numeroTelelephoneFormate.replaceAll(" ", "");
        numeroTelephone = numeroTelephone.replaceAll("\\.", "");
        numeroTelephone = numeroTelephone.replaceAll("-", "");
        numeroTelephone = numeroTelephone.replaceAll("/", "");
        return numeroTelephone;
    }

}
