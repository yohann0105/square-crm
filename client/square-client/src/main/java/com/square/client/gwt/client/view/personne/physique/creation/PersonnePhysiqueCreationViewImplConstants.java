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
package com.square.client.gwt.client.view.personne.physique.creation;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue PersonnePhysiqueCreation.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface PersonnePhysiqueCreationViewImplConstants extends Constants {

    /** Hauteur du scrollpanel. */
    String HAUTEUR_SCOLLPANEL = "720px";

    /** Format du téléphone par défaut. */
    String FORMAT_TELEPHONE_DEFAUT = "NN NN NN NN NN";

    /** Longueur max pour la TextBox des emails. */
    int MAX_LENGTH_TB_EMAIL = 80;

    /**
     * Label.
     * @return le label
     */
    String popupTitle();

    /**
     * Label.
     * @return le label
     */
    String civilite();

    /**
     * Label.
     * @return le label
     */
    String nom();

    /**
     * Label.
     * @return le label
     */
    String telephone();

    /**
     * Label.
     * @return le label
     */
    String email();

    /**
     * Label.
     * @return le label
     */
    String numeroVoie();

    /**
     * Label.
     * @return le label
     */
    String adresse();

    /**
     * Label.
     * @return le label
     */
    String dateNaissance();

    /**
     * Label.
     * @return le label
     */
    String codePostal();

    /**
     * Label.
     * @return le label
     */
    String natureVoie();

    /**
     * Label.
     * @return le label
     */
    String ville();
    
    /**
     * Label.
     * @return le label
     */
    String rattacherAuxParents();

    /**
     * Label.
     * @return le label
     */
    String creer();

    /**
     * Label.
     * @return le label
     */
    String reduire();

    /**
     * Label.
     * @return le label
     */
    String annuler();

    /**
     * Label.
     * @return le label
     */
    String profession();

    /**
     * Label.
     * @return le label
     */
    String titreChefDeFamille();

    /**
     * Label.
     * @return le label
     */
    String hasConjoint();

    /**
     * Label.
     * @return le label
     */
    String titreConjoint();

    /**
     * Label.
     * @return le label
     */
    String complementNom();

    /**
     * Label.
     * @return le label
     */
    String complementAdresse();

    /**
     * Label.
     * @return le label
     */
    String autresComplements();

    /**
     * Label.
     * @return le label
     */
    String pays();

    /**
     * Label.
     * @return le label
     */
    String titreAdresse();

    /**
     * Label.
     * @return le label
     */
    String titreEnfants();

    /**
     * Label.
     * @return le label
     */
    String creationPersonne();

    /**
     * Titre de la colonne des pays.
     * @return le titre.
     */
    String titreColonnePays();

    /**
     * Titre de la colonne de l'indicatif téléphonique.
     * @return le titre.
     */
    String titreColonneIndicatifTelephonique();

    /**
     * Titre de la colonne du format téléphonique.
     * @return le titre.
     */
    String titreColonneFormatTelephonique();

    /**
     * Message indiquant la présence de doublons.
     * @return le message.
     */
    String warningDoublons();

}
