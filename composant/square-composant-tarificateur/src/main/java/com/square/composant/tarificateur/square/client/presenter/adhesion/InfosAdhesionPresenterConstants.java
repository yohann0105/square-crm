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
package com.square.composant.tarificateur.square.client.presenter.adhesion;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes du presenter pour les infos d'adhésion.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface InfosAdhesionPresenterConstants extends Constants {

    /** Id pour reconnaitre l'item d'ajout d'assure social. */
    Long ID_AJOUT_ASSURE_SOCIAL = -1L;

    /**
     * Chemin du champ de la date de signature.
     * @return le chemin du champ de la date de signature
     */
    String champDateSignature();

    /**
     * L'erreur de la date de signature invalide.
     * @return l'erreur
     */
    String erreurDateSignatureInvalide();

    /**
     * Le texte.
     * @return le texte
     */
    String conjoint();

    /**
     * Le texte.
     * @return le texte
     */
    String enfant();
}
