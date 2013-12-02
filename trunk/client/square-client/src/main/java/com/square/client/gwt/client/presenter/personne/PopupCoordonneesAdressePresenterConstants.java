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
package com.square.client.gwt.client.presenter.personne;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes du presenter PopupCoordonneesAdressePresenter.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface PopupCoordonneesAdressePresenterConstants extends Constants {

    /**
     * Message indiquant l'ajout en cours d'une nouvelle adresse.
     * @return le message.
     */
    String ajoutNouvelleAdresseEnCours();

    /**
     * Message indiquant que le l'adresse a bien été ajoutée.
     * @return le message.
     */
    String notificationAdresseAjoutee();

    /**
     * Chemin du champ de la date de début.
     * @return le chemin
     */
    String champDateDebut();

    /**
     * Message d'erreur sur la date de début.
     * @return le message
     */
    String erreurDateDebutInvalide();

    /**
     * Chemin du champ de la date de fin.
     * @return le chemin
     */
    String champDateFin();

    /**
     * Message d'erreur sur la date de fin.
     * @return le message
     */
    String erreurDateFinInvalide();

    /**
     * Debut du message indiquant que la nature de la personne a changé.
     * @return le message.
     */
    String debutNotificiationNaturePersonneModifiee();

    /**
     * Fin du message indiquant que la nature de la personne a changé.
     * @return le message.
     */
    String finNotificiationNaturePersonneModifiee();
}
