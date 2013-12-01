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
 * Interface des constants de debugId de la vue PersonnePhysiqueCreation.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface PersonnePhysiqueCreationViewImplDebugIdConstants extends Constants {

    /**
     * DebugId pour le bouton de création.
     * @return le debugId.
     */
    String debugIdBtnCreer();

    /**
     * DebugId pour le bouton de reduction.
     * @return le debugId.
     */
    String debugIdBtnReduire();

    /**
     * DebugId pour le bouton d'annulation.
     * @return le debugId.
     */
    String debugIdBtnAnnuler();

    /**
     * DebugId pour la SuggestBox des civilités.
     * @return le debugId.
     */
    String debugIdSlbCivilite();

    /**
     * DebugId pour la TextBox du nom.
     * @return le debugId.
     */
    String debugIdTbNom();

    /**
     * DebugId pour la textBox du prénom.
     * @return le debugId.
     */
    String debugIdTbPrenom();

    /**
     * DebugId pour le TextBox de la date de naissance.
     * @return le debugId.
     */
    String debugIdCdbDateNaissance();

    /**
     * DebugId pour la TextBox de l'email.
     * @return le debugId.
     */
    String debugIdTbEmail();

    /**
     * DebugId pour la TextBox du téléphone.
     * @return le debugId.
     */
    String debugIdTbTelephone();

    /**
     * DebugId pour la TextBox du téléphone portable.
     * @return le debugId.
     */
    String debugIdTbTelephonePortable();

    /**
     * DebugId pour la SuggestBox de la nature du téléphone.
     * @return le debugId.
     */
    String debugIdSlbNatureTelephone();

    /**
     * DebugId pour la SuggestBox de la nature du téléphone portable.
     * @return le debugId.
     */
    String debugIdSlbNatureTelephonePortable();

    /**
     * DebugId pour la SuggestBox de la profession.
     * @return le debugId.
     */
    String debugIdSlbProfession();

    /**
     * DebugId pour l'image du drapeau du paus du téléphone.
     * @return le debugId.
     */
    String debugIdImgFlagPaysTelephone();

    /**
     * DebugId pour la ListBox du conjoint.
     * @return le debugId.
     */
    String debugIdLbConjoint();

    /**
     * DebugId pour la vue du conjoint.
     * @return le debugId.
     */
    String debugIdConjointView();

    /**
     * DebugId pour la ListBox du nombre d'enfants.
     * @return le debugId.
     */
    String debugIdLbNbEnfants();

    /**
     * DebugId pour le bloc de enfants.
     * @return le debugId.
     */
    String debugIdBlocEnfants();

    /**
     * DebugId pour la TextBox du numéro de voie.
     * @return le debugId.
     */
    String debugIdTbNumeroVoie();

    /**
     * DebugId pour la SuggestBox de la nature de voie.
     * @return le debugId.
     */
    String debugIdSlbNatureVoie();

    /**
     * DebugId pour la TextBox du complément du nom.
     * @return le debugId.
     */
    String debugIdTbComplementNom();

    /**
     * DebugId pour la TextBox de l'adresse.
     * @return le debugId.
     */
    String debugIdTbAdresse();

    /**
     * DebugId pour la TextBox du complément d'adresse.
     * @return le debugId.
     */
    String debugIdTbComplementAdresse();

    /**
     * DebugId pour la TextBox des autres compléments.
     * @return le debugId.
     */
    String debugIdTbAutresComplements();

    /**
     * DebugId pour la SuggestBox du code postal.
     * @return le debugId.
     */
    String debugIdSlbCodePostal();

    /**
     * DebugId pour la SuggestBox du pays.
     * @return le debugId.
     */
    String debugIdSlbPays();

    /**
     * DebugId pour le label de la ville.
     * @return le debugId.
     */
    String debugIdLValueVille();

    /**
     * DebugId pour la TextBox du code postal étranger.
     * @return le debugId.
     */
    String debugIdTbCodePostalEtranger();

    /**
     * DebugId pour la TextBox de la commune étrangère.
     * @return le debugId.
     */
    String debugIdTbCommuneEtranger();

    /**
     * DebugId pour la CheckBox de demande de rattachement aux deux parents.
     * @return le debugId.
     */
    String debugIdCbRattacherParents();

    /**
     * DebugId pour l'image du drapeau du téléphone portable.
     * @return le debugId.
     */
    String debugIdImgFlagPaysTelephonePortable();

    /**
     * DebugId pour le label prévenant de la présence de doublon.
     * @return le debugId.
     */
    String debugIdLWarningDoublon();
}
