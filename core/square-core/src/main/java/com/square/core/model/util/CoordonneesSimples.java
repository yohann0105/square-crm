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
package com.square.core.model.util;

import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.TelephoneDto;

/**
 * Bean contenant l'adresse principale, le téléphone, le téléphone portable et le mail.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class CoordonneesSimples {

    /** Adresse principale. */
    private AdresseDto adressePrincipale = new AdresseDto();

    /** Téléphone. */
    private TelephoneDto telephone = new TelephoneDto();

    /** Téléphone portable. */
    private TelephoneDto telephonePortable = new TelephoneDto();

    /** Mail. */
    private EmailDto email = new EmailDto();

    /**
     * Récupère la valeur de adressePrincipale.
     * @return la valeur de adressePrincipale
     */
    public AdresseDto getAdressePrincipale() {
        return adressePrincipale;
    }

    /**
     * Définit la valeur de adressePrincipale.
     * @param adressePrincipale la nouvelle valeur de adressePrincipale
     */
    public void setAdressePrincipale(AdresseDto adressePrincipale) {
        this.adressePrincipale = adressePrincipale;
    }

    /**
     * Récupère la valeur de telephone.
     * @return la valeur de telephone
     */
    public TelephoneDto getTelephone() {
        return telephone;
    }

    /**
     * Définit la valeur de telephone.
     * @param telephone la nouvelle valeur de telephone
     */
    public void setTelephone(TelephoneDto telephone) {
        this.telephone = telephone;
    }

    /**
     * Récupère la valeur de telephonePortable.
     * @return la valeur de telephonePortable
     */
    public TelephoneDto getTelephonePortable() {
        return telephonePortable;
    }

    /**
     * Définit la valeur de telephonePortable.
     * @param telephonePortable la nouvelle valeur de telephonePortable
     */
    public void setTelephonePortable(TelephoneDto telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    /**
     * Récupère la valeur de email.
     * @return la valeur de email
     */
    public EmailDto getEmail() {
        return email;
    }

    /**
     * Définit la valeur de email.
     * @param email la nouvelle valeur de email
     */
    public void setEmail(EmailDto email) {
        this.email = email;
    }

}
