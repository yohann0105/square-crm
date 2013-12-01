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
package com.square.client.gwt.client.view.campagne.creation;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constantes de la vue CampagneCreation.
 * @author cblanchard - SCUB
 */
public interface CampagneCreationViewImplConstants extends Constants {

    /**
     * Label.
     * @return le label
     */
    String popupTitle();

    /**
     * Le label du bouton pour créer la campagne.
     * @return le label du bouton
     */
    String creationCampagne();

    /**
     * Label.
     * @return le label
     */
    String reduire();

    /**
     * Label du bouton annuler.
     * @return le label du bouton annuler
     */
    String annuler();

    /**
     * Label du caption panel.
     * @return le label du caption panel
     */
    String infoBloc();

    /**
     * Label libelle.
     * @return le label du libelle
     */
    String libelle();

    /**
     * Label du type.
     * @return le label du type
     */
    String type();

    /**
     * Label de la date de début.
     * @return la date de debut
     */
    String dateDebut();

    /**
     * Label de la date de fin.
     * @return le label de la date de fin
     */
    String dateFin();

}
