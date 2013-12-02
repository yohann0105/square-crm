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
package com.square.tarificateur.noyau.util;


/**
 * Classe utilitaire pour la validation d'expression.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public final class ValidationUtil {

    /** Constructeur privé. */
    private ValidationUtil() { }

    /**
     * Teste si le numéro de sécurité sociale est valide.
     * @param numero le numéro de sécurité sociale
     * @param cle la clé
     * @return true si valide, false sinon
     */
    public static boolean verifierNumeroSecuriteSociale(final String numero, final String cle) {

        if (numero == null || "".equals(numero) || cle == null || "".equals(cle)) {
            return false;
        }

        boolean erreur = false;
        String numSecu = numero.trim();
        final String cleSecu = cle.trim();
        final int tailleNumSecu = 13;
        final int tailleCle = 2;

        // Le numéro doit contenir 13 caractères et la clé 2
        if (numSecu.length() != tailleNumSecu || cleSecu.length() != tailleCle) {
            erreur = true;
        } else {
            // Cas particulier de la Corse
            // Si le numéro contient un A ou un B, on le remplace par un zéro
            final boolean contientA = numSecu.contains("A");
            final boolean contientB = numSecu.contains("B");

            // On remplace le A ou le B par un zéro
            if (contientA || contientB) {
                if (contientA) {
                    numSecu = numSecu.replace("A", "0");
                } else if (contientB) {
                    numSecu = numSecu.replace("B", "0");
                }
            }

            try {
                // On tente de transformer le numéro de sécu en nombre
                long numSecuLong = Long.parseLong(numSecu);
                final long cleLong = Long.parseLong(cleSecu);

                // Cas particulier de la Corse
                // Si le numéro de sécu contient un A, on soustrait 1000000 du numéro de sécu (nombre)
                if (contientA) {
                    final long nbMagigueA = 1000000;
                    numSecuLong = numSecuLong - nbMagigueA;
                } else if (contientB) {
                    // S'il contient un B, on soustrait 2000000 du numéro de sécu (nombre)
                    final long nbMagigueB = 2000000;
                    numSecuLong = numSecuLong - nbMagigueB;
                }

                // Cle = 97 - (num_secu % 97)
                final long modulo = 97;
                erreur = cleLong != (modulo - (numSecuLong % modulo));
            } catch (NumberFormatException e) {
                erreur = true;
            }
        }
        return !erreur;
    }
}
