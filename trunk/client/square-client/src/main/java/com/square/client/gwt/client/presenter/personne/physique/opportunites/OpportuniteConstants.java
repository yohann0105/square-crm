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
package com.square.client.gwt.client.presenter.personne.physique.opportunites;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes associées aux opportunités.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public interface OpportuniteConstants extends Constants {

    /**
     * Notification affichée lorsqu'une opportunité est créée.
     * @return le texte associé.
     */
    String notificationOpportuniteCreee();

    /**
     * Notification affichée lorsqu'une opportunité est mise à jour.
     * @return le texte associé.
     */
    String notificationOpportuniteMaj();

    /**
     * Texte de confirmation de suppression d'une opportunite.
     * @return le texte associe.
     */
    String confirmationSuppression();

    /**
     * Notification affichee lorsqu'une opportunite a ete supprimee.
     * @return le texte assscoie.
     */
    String notificationOpportuniteSupprimee();

    /**
     * titre de la popup de demande de confirmation de suppression.
     * @return le titre de la popup.
     */
    String titrePopupConfirmationSuppression();

    /**
     * Champ de texte opportunité.
     * @return le texte
     * @return
     */
    String opportunite();

}
