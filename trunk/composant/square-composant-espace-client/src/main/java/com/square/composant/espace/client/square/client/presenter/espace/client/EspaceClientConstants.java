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
package com.square.composant.espace.client.square.client.presenter.espace.client;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de l'espace client.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public interface EspaceClientConstants extends Constants {

    /**
     * La durée en secondes durant laquelle les notifications sont affichées.
     * @return durée en secondes du timeout.
     */
    float notificationTimeOut();

    /**
     * Message de notification du succès de l'envoi de mot de passe à l'adhérent.
     * @return le texte associé.
     */
    String notificationMdpEnvoye();

    /**
     * Message de notification du succès de l'enregistrement des options de l'adhérent.
     * @return le texte associé.
     */
    String notificationOptionsEnregistrees();

    /**
     * Libellé.
     * @return le libellé
     */
    String rechercheEnCours();
}
