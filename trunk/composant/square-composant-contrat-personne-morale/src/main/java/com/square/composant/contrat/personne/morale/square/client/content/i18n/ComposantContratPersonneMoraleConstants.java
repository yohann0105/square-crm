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
package com.square.composant.contrat.personne.morale.square.client.content.i18n;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes du composant des contrats d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface ComposantContratPersonneMoraleConstants extends Constants {

    /** Cent pourcent. */
    String POURCENT_100 = "100%";

    /** Largeur. */
    String LARGEUR_SCROLL_PANEL_CONTRAT = "860px";

    /** URL de la servlet de récupération de la grille de presta au format PDF. */
    String URL_SERVLET_GRILLE_PRESTA_PDF = "servlet/getGrillePrestaPDFContratPersonneMorale";

    /** Paramètre de l'identifiant du produit pour la récupération de grille de prestation. */
    String PARAM_ID_PRODUIT = "idProduit";
}
