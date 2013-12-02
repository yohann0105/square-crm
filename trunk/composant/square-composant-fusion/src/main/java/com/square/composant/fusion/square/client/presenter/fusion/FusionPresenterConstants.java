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
package com.square.composant.fusion.square.client.presenter.fusion;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes du presenter pour la fusion.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface FusionPresenterConstants extends Constants {

    /**
     * Message indiquant le chargement en cours des deux personnes pour la fusion.
     * @return le message.
     */
    String chargementPersonnesFusionEnCours();

    /**
     * Message indiquant la validation de la fusion en cours.
     * @return le message.
     */
    String validationFusionEnCours();

    /**
     * Message demandant la confirmation pour lancer la fusion.
     * @return le message.
     */
    String confirmationFusion();

    /**
     * Titre de la popup de confirmation pour le lancement de la fusion.
     * @return le titre.
     */
    String titrePopupConfirmationFusion();

    /**
     * Erreur du contrôle d'intégrité de la cible.
     * @return le message d'erreur
     */
    String erreurControleIntegriteCible();
}
