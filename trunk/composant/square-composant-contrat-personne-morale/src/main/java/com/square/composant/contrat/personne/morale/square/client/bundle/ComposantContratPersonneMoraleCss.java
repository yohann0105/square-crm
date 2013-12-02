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
package com.square.composant.contrat.personne.morale.square.client.bundle;

import com.google.gwt.resources.client.CssResource;

/**
 * Interface du CSS utilise dans le composé des contrats des personnes morales.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 *
 */
public interface ComposantContratPersonneMoraleCss extends CssResource {

    /**
     * Style CSS.
     * @return le style
     */
    String composantContrat();

    /**
     * Style CSS.
     * @return le style
     */
    String blocSyntheseDepliantContratEnCours();

    /**
     * Style CSS.
     * @return le style
     */
    String blocSyntheseDepliantContratResilie();

    /**
     * Style CSS.
     * @return le style
     */
    String statutContratEnCours();

    /**
     * Style CSS.
     * @return le style
     */
    String statutContratResilie();

    /**
     * Style CSS.
     * @return le style
     */
    String panelGarantieContrat();

    /**
     * Style CSS.
     * @return le style
     */
    String imagePdfCliquable();

    /**
     * Style CSS.
     * @return le style
     */
    String celluleRecapitulatifGarantie();

    /**
     * Style CSS.
     * @return le style
     */
    String celluleRecapitulatifGarantieNonEnCours();

    /**
     * Style CSS.
     * @return le style
     */
    String couleurFondGarantieEnCours();

    /**
     * Style CSS.
     * @return le style
     */
    String couleurFondGarantieResiliee();

    /**
     * Style CSS.
     * @return le style
     */
    String couleurFondGarantieFutur();

    /**
     * Retourne le style css.
     * @return le style
     */
    String tableau();

    /**
     * Retourne le style css.
     * @return le style
     */
    String ligneEnteteColonne();

    /**
     * Retourne le style css.
     * @return le style
     */
    String celluleEnteteLigne();
}
