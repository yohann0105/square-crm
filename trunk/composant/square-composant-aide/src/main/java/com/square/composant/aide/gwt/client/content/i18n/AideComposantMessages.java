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
package com.square.composant.aide.gwt.client.content.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * interface de constantes.
 * @author mohamed - SCUB
 *
 */
public interface AideComposantMessages extends Messages {
    /**
     * message LoadingPopup.
     * @return message.
     */
    String chargementEnCours();
    /**
     * message LoadingPopup pour enregistrement.
     * @return message.
     */
    String enregistrementEnCours();
    /**
     * libellé de bouton d'enregistrement de modification.
     * @return message.
     */
    String buttonEnregistrerLabel();
    /**
     * libellé de bouton quitter en modification.
     * @return message.
     */
    String buttonQuitterLabel();
    /**
     * titre de la popup d'affichage.
     * @return message.
     */
    String popupTitle();
    /**
     * titre de la popup de modification.
     * @return message.
     */
    String popupModificationTitle();
    /**
     * Message d'errreur.
     * @return message
     */
	String erreurRecuperationMessage();
	/**
	 * Message d'erreur.
	 * @return message
	 */
	String erreurAucuneAideAssocieComposant();

}
