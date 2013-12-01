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
package com.square.composant.tarificateur.square.client.view.adhesion;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de la vue associée.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public interface InfosAdhesionViewImplConstants extends Constants {

    /** Largeur de champ pour les clés de sécurité sociale. */
    String LARGEUR_TB_CLE_SS = "30px";

    /** Caractère espace. */
    String ESPACE = " ";

    /** Longueur d'un numéro de sécurité sociale. */
    int LONGUEUR_NUM_SECURITE_SOCIALE = 13;

    /** Longueur d'une clé de sécurité sociale. */
    int LONGUEUR_CLE_SECURITE_SOCIALE = 2;

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_0 = "20%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_1 = "34%";

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_2 = "15%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_3 = "31%";

    /** Largeur. */
    String LARGEUR_POPUP = "800px";

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String infosAdhesion();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lNumSs();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lCleSs();

    /**
     * Libelle.
     * @return le texte.
     */
    String relation();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lRegime();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lCaisse();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lTns();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lLoiMadelin();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lcouvertNow();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lcouvert6DerniersMois();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lAnnuler();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lEnregistrer();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String captionPanelInfosPaiement();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelDateSignature();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelMontantPaiement();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelModePaiement();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelNumeroTransaction();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelPeriodicitePaiement();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelJourPaiement();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelConjoint();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelEnfant();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelSaisieInfosSecuriteSociale();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelAjoutAssureSocial();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lRib();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lDateClicBA();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lDateTelechargementBA();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lTitreDateClicBA();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String lTitreDateTelechargementBA();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String captionPanelInfosSignatureNumerique();

    /**
     * Libellé de l'option télétransmission.
     * @return le texte associé
     */
    String lTeletransmission();

    /**
     * Libellé du panel options.
     * @return le texte associé
     */
    String captionPanelOptions();

    /**
     * Libellé pour le titre de l'option télétransmission.
     * @return le texte associé
     */
    String lTitreTeletransmission();

    /**
     * Label.
     * @return le label
     */
    String reduire();

    /**
     * Label.
     * @return le label
     */
    String recuperationAssuresSociaux();

}
