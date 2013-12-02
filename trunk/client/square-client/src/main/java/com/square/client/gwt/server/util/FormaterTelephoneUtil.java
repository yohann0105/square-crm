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
package com.square.client.gwt.server.util;

/**
 * Classe utilitaire pour déterminer le format graphique d'un numéro de téléphone en fonction de son pays.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public final class FormaterTelephoneUtil {

    /** Constructeur privé. */
    private FormaterTelephoneUtil() {
    }

    /**
     * Formate un numéro de téléphone en fonction de son pays.
     * @param idPays l'identifiant du pays.
     * @return le format.
     */
    public static String formterNumTelephone(Long idPays) {
        if (idPays != null) {
            if (idPays.equals(1L)) {
                return "NNN NNN NNN";
            }
            else if (idPays.equals(2L)) {
                return "NN NNN NNNN";
            }
            else if (idPays.equals(3L)) {
                return "NN NNNNN";
            }
            else if (idPays.equals(4L)) {
                return "NN/NNNNNN";
            }
            else if (idPays.equals(5L)) {
                return "NN NNNN NNNN";
            }
            else if (idPays.equals(6L)) {
                return "NNN NNN";
            }
            else if (idPays.equals(17L)) {
                return "NNN NNN NNN";
            }
            else if (idPays.equals(24L)) {
                return "N NNN NN NN";
            }
            else if (idPays.equals(43L)) {
                return "NN NNNNNNNN";
            }
            else if (idPays.equals(53L)) {
                return "NN NNN NNN";
            }
            else if (idPays.equals(63L)) {
                return "NNN.NN.NN.NN";
            }
            else if (idPays.equals(66L)) {
                return "NNN NNN-NNN";
            }
            else if (idPays.equals(69L)) {
                return "NNN NNN NNNN";
            }
            else if (idPays.equals(70L)) {
                return "NN NN NN NN NN";
            }
            else if (idPays.equals(71L)) {
                return "NN NN NN NN NN";
            }
            else if (idPays.equals(87L)) {
                return "NN NN NN NN NN";
            }
            else if (idPays.equals(91L)) {
                return "NN NNN NNN";
            }
            else if (idPays.equals(111L)) {
                return "N NNN NNNN";
            }
            else if (idPays.equals(129L)) {
                return "NNN NNN NNN";
            }
            else if (idPays.equals(140L)) {
                return "NN NN NN NN NN";
            }
            else if (idPays.equals(146L)) {
                return "NN NN NN NN";
            }
            else if (idPays.equals(179L)) {
                return "NNN NNN NNN";
            }
            else if (idPays.equals(184L)) {
                return "NNN NNN NNN";
            }
            else if (idPays.equals(185L)) {
                return "NN NN NN NN NN";
            }
            else if (idPays.equals(187L)) {
                return "NNNNN NNN NNN NNN";
            }
            else if (idPays.equals(213L)) {
                return "NNN NNN NN NN";
            }
            else if (idPays.equals(226L)) {
                return "NNN NN NN";
            }
            else if (idPays.equals(230L)) {
                return "NN NNN NNN";
            }
            else if (idPays.equals(232L)) {
                return "NNN NNN NNN";
            }
            else {
                return "NNNNNNNNNN";
            }
        }
        return null;
    }
}
