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
package com.square.client.gwt.client.view.personne.relations;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constantes de la vue relations familiales.
 * @author cblanchard - SCUB
 */
public interface PersonneRelationsViewImplConstants extends Constants {

    /** Identifiant NC. */
    Long IDENTIFIANT_NC = -1L;

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_0 = "17%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_1 = "39%";

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_2 = "8%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_3 = "36%";

    /** Longueur d'un numéro de sécurité sociale. */
    int LONGUEUR_NUM_SECURITE_SOCIALE = 13;

    /** Longueur d'une clé de sécurité sociale. */
    int LONGUEUR_CLE_SECURITE_SOCIALE = 2;

    /** Largeur de champ pour les clés de sécurité sociale. */
    String LARGEUR_TB_CLE_SS = "30px";

    /**
     * Renvoi le libelle civilité.
     * @return libelle civilité
     */
    String civilite();

    /**
     * Renvoi le libelle du nom.
     * @return libelle nom
     */
    String nom();

    /**
     * Renvoi le libelle prénom.
     * @return libelle prénom
     */
    String prenom();

    /**
     * Renvoi le libelle date de naissance.
     * @return libelle date de naissance
     */
    String dateNaissance();

    /**
     * Renvoi le libelle relation.
     * @return le libelle relation
     */
    String typeRelation();

    /**
     * Renvoi le libelle de la date de début.
     * @return libelle date de début.
     */
    String dateDebut();

    /**
     * Renvoi le libelle enregistrer.
     * @return libelle enregistrer
     */
    String enregistrer();

    /**
     * Renvoi le libelle du lien annuler.
     * @return le libelle annuler
     */
    String annuler();

    /**
     * Renvoi le libelle ou.
     * @return le libelle ou
     */
    String libelleOu();

    /**
     * Renvoi le libelle Ajouter une relation.
     * @return le libelle
     */
    String ajouterRelation();

    /**
     * Label .
     * @return le label.
     */
    String raisonSociale();

    /**
     * Label .
     * @return le label.
     */
    String numEntreprise();

    /**
     * Label.
     * @return le label
     */
    String rechercher();

    /**
     * Label.
     * @return le label
     */
    String headerNumeroEntreprise();

    /**
     * Label.
     * @return le label
     */
    String headerRaisonSociale();

    /**
     * Label.
     * @return le label
     */
    String headerNature();

    /**
     * Champ numéro entreprise.
     * @return le nom du champ
     */
    String fieldNumeroEntreprise();

    /**
     * Champ raison sociale.
     * @return le nom du champ
     */
    String fieldRaisonSociale();

    /**
     * Champ nature.
     * @return le nom du champ
     */
    String fieldNature();

    /**
     * Constante Caption Relation.
     * @return .
     */
    String lbCaptionReseau();

    /**
     * Constante .
     * @return .
     */
    String lbCaptionTypePersonne();

    /**
     * Constante .
     * @return .
     */
    String lbCaptionPersonneMorale();

    /**
     * Constante .
     * @return .
     */
    String lbCaptionPersonnePhysique();

    /**
     * Constante .
     * @return .
     */
    String lbtypePersonne();

    /**
     * Constante .
     * @return .
     */
    String typePersonnePP();

    /**
     * Constante .
     * @return .
     */
    String typePersonnePM();

    /**
     * Constante .
     * @return .
     */
    String textPopupCreationFamille();

    /**
     * Constante .
     * @return .
     */
    String textPopupCreationGenerique();

    /**
     * Renvoi le libelle de la popup de modification.
     * @return Renvoi le libelle de la popup de modification.
     */
    String messageErreurModification();

    /**
     * Label .
     * @return le label.
     */
    String telephone();

    /**
     * Titre du message du rapport d'erreurs concernant la personne source de la relation.
     * @return le texte associé.
     */
    String titreErreursPersonneSource();

    /**
     * Label.
     * @return le label
     */
    String reduire();

    /**
     * Titre du panel des infos santé.
     * @return le titre.
     */
    String lbCaptionInfosSante();

    /**
     * Label pour le numéro de sécurité sociale.
     * @return le label.
     */
    String lNumSs();

    /**
     * Label pour le régime.
     * @return le label.
     */
    String lRegime();

    /**
     * Label pour la caisse.
     * @return le label.
     */
    String lCaisse();

    /**
     * Accesseurs constante.
     * @return la constante.
     */
    String labelSaisieInfosSecuriteSociale();

    /**
     * chargement realation en cour.
     * @return le texte
     * @return
     */
    String chergementRelationEnCours();
}
