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
package com.square.composant.contrat.square.client.view;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constantes de la vue des infos d'un contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface ContratInfosViewImplConstants extends Constants {

    /**
     * Libellé Statut.
     * @return le libellé
     */
    String libelleStatut();

    /**
     * Libellé Date d'effet.
     * @return le libellé
     */
    String libelleDateEffet();

    /**
     * Libellé Date de résiliation.
     * @return le libellé
     */
    String libelleDateResiliation();

    /**
     * Libelle Contrat.
     * @return le libellé
     */
    String libelleContrat();

    /**
     * Libellé Date de signature.
     * @return le libellé
     */
    String libelleDateSignature();
}
