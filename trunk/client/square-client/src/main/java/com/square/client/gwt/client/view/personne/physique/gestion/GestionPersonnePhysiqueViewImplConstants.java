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
/**
 *
 */
package com.square.client.gwt.client.view.personne.physique.gestion;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes pour la vue Gestion des personnes.
 * @author Goumard Stephane (Scub) - SCUB
 */
public interface GestionPersonnePhysiqueViewImplConstants extends Constants {

    /** Ligne du nom de jeune fille. */
    int LIGNE_NOM_JEUNE_FILLE = 1;

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_0 = "13%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_1 = "37%";

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_2 = "8%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_3 = "42%";

    /** Identifiant NC. */
    Long IDENTIFIANT_NC = -1L;

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbRnumeroClient();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbRnature();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbRpersonne();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbGnomJeuneFille();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbGdateNaissance();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbGnom();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbGprenom();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbGstatut();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbGnumeroRo();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbGcaisse();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGregime();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGprofession();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGsegment();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGreseauVente();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGcsp();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGsituationDeclare();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGsituationEnfants();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGdecede();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGcivilite();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGinformationSup();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGdossier();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGautres();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGassuranceVie();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGassuranceVieCotisationAnnuelle();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGassuranceVieCotisationUnique();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGdomiciliationTiers();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGcotisationsTiers();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGprestationsTiers();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGboitePostale();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGinformation();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGenregistrer();

    /**
     * lbelle de resumé.
     * @return le lbelle.
     */
    String lbGannuler();

    /**
     * Libelle des coordonnees.
     * @return le libelle des coordonnee.
     */
    String libelleTabCoordonnees();

    /**
     * Libelle des relations familiales.
     * @return le libelle des relations familiales
     */
    String libelleTabRelationFamiliales();

    /**
     * Libelle des relations familiales.
     * @return le libelle des relations familiales
     */
    String libelleTabRelations();

    /**
     * Libelle action.
     * @return le libelle action
     */
    String libelleTabActions();

    /**
     * Libelle opportunite.
     * @return le libelle opportunite
     */
    String libelleTabOpportunite();

    /**
     * Libelle opportunite.
     * @return le libelle opportunite
     */
    String libelleTabDocuments();

    /**
     * Message de recuperation de personne.
     * @return le message
     */
    String recuperationPersonne();

    /**
     * Message d'enregistrement de personne.
     * @return le message
     */
    String modificationPersonne();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleAgence();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleCommercial();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleCreation();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbGdateCreation();

    /**
     * Libelle de resumé.
     * @return le libelle.
     */
    String lbGcreation();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleCreePar();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleLe();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String actionsSur();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String nouvelleAction();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String nouvelleOpportunite();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String lbParOuv();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String lbParFer();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleTabContrats();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleTabCotisations();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleTabEspaceClient();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleTabEmails();

    /**
     * Accesseur libellé.
     * @return le libelle de prestation.
     */
    String libelleTabPrestation();

    /**
     * Message indiquant qu'une recherche de doublons est en cours.
     * @return le message
     */
    String rechercheDoublonsEnCours();

    /**.
     * Nombre maximum de caractères à afficher pour le prénom
     * @return la valeur
     */
    int maxCaracteresPrenom();

    /**.
     * Nombre maximum de caractères à afficher pour le nom
     * @return la valeur
     */
    int maxCaracteresNom();
}
