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
package com.square.composant.espace.client.square.client.view.espaceadherent;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes associées au bloc de synthèse de l'espace adhérent.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public interface BlocSyntheseEspaceAdherentViewImplConstants extends Constants {

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_LARGE = "26%";

    /** Largeur. */
    String LARGEUR_COL_LIBELLE = "18%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_PETIT = "7%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP = "15%";

    /**
     * Libellé.
     * @return le libellé.
     */
    String captionSynthese();

    /**
     * Libellé.
     * @return le libellé.
     */
    String labelDatePremiereVisite();

    /**
     * Libellé.
     * @return le libellé.
     */
    String labelDateDerniereVisite();

    /**
     * Libellé.
     * @return le libellé.
     */
    String labelNbVisites();

    /**
     * Libellé.
     * @return le libellé.
     */
    String labelDateModificationOptions();
}
