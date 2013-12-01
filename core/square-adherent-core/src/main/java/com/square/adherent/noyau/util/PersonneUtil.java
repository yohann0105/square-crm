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
package com.square.adherent.noyau.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;

import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.TelephoneDto;

/**
 * Classe utilitaire pour les personnes.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class PersonneUtil {

    /** Caractères qui délimitent les mots. */
    private static final char[] DELIMITERS = {' ', '-', '_' };

    /**
     * Filtre les téléphones correspondant à la nature passée en paramètre.
     * @param telephones la liste complète de téléphones
     * @param idNatureTelephone la nature de téléphone recherchée
     * @return la liste des téléphones filtrés correspondant à la nature passée en paramètre
     */
    public List<TelephoneDto> getTelephonesByNature(List<TelephoneDto> telephones, Long idNatureTelephone) {
        final List<TelephoneDto> telephonesFiltres = new ArrayList<TelephoneDto>();
        if (telephones != null && !telephones.isEmpty() && idNatureTelephone != null) {
            for (TelephoneDto telephone : telephones) {
                if (idNatureTelephone.equals(telephone.getNature().getIdentifiant())) {
                    telephonesFiltres.add(telephone);
                }
            }
        }
        return telephonesFiltres;
    }

    /**
     * Filtre les emails correspondant à la nature passée en paramètre.
     * @param emails la liste d'emails à filtrer
     * @param idNatureEmail la nature de l'email recherché
     * @return la liste des emails filtrés correspondant à la nature passée en paramètre
     */
    public List<EmailDto> getEmailsByNature(List<EmailDto> emails, Long idNatureEmail) {
        final List<EmailDto> emailsFiltres = new ArrayList<EmailDto>();
        if (emails != null && !emails.isEmpty() && idNatureEmail != null) {
            for (EmailDto email : emails) {
                if (idNatureEmail.equals(email.getNatureEmail().getIdentifiant())) {
                    emailsFiltres.add(email);
                }
            }
        }
        return emailsFiltres;
    }

    /**
     * Formatte les champs textuels du DTO passé en paramètre.
     * @param personne la personne à formatter
     * @return la personne formattée
     */
    public PersonneSimpleDto formatterPersonneSimple(PersonneSimpleDto personne) {
        personne.setNom(personne.getNom().toUpperCase());
        personne.setPrenom(WordUtils.capitalizeFully(personne.getPrenom().trim(), DELIMITERS));
        return personne;
    }
}
