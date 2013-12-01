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
package com.square.composant.cotisations.client.view.bloc.entete;

import java.util.Map;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue BlocEnteteCotisation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface BlocEnteteCotisationViewImplConstants extends Constants {

    /**
     * Le label.
     * @return le label
     */
    String periode();

    /**
     * Le label.
     * @return le label
     */
    String modePaiement();

    /**
     * Le label.
     * @return le label
     */
    String montant();

    /**
     * Le label.
     * @return le label
     */
    String montantRegle();

    /**
     * Le label.
     * @return le label
     */
    String situation();

    /**
     * Le label.
     * @return le label
     */
    String periodeCouverture();

    /**
     * Le label.
     * @return le label
     */
    String banque();

    /**
     * Le label.
     * @return le label
     */
    String compte();

    /**
     * Le label.
     * @return le label
     */
    String numeroCheque();

    /**
     * Le label.
     * @return le label
     */
    String paiement();

    /**
     * Le label.
     * @return le label
     */
    String date();

    /**
     * Le label.
     * @return le label
     */
    String situationPrime();

    /**
     * Le label.
     * @return le label
     */
    String situationPrimeRejetee();

    /**
     * Le label.
     * @return le label
     */
    String motifRejet();

    /**
     * Le label.
     * @return le label
     */
    String dateRejet();

    /**
     * Le label.
     * @return le label
     */
    String enteteContrat();

    /**
     * Le label.
     * @return le label
     */
    String enteteGarantie();

    /**
     * Le label.
     * @return le label
     */
    String enteteBeneficiaire();

    /**
     * Le label.
     * @return le label
     */
    String enteteMontant();

    /**
     * Le label.
     * @return le label
     */
    String enteteTypePrime();

    /**
     * Le label.
     * @return le label
     */
    String enteteTypeEcheance();

    /**
     * La table de mapping des écheances.
     * @return la table
     */
    Map<String, String> echeanceMap();
}
