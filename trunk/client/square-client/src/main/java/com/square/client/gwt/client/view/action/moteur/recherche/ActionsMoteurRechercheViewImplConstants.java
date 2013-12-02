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
package com.square.client.gwt.client.view.action.moteur.recherche;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue ActionsMoteurRecherche.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface ActionsMoteurRechercheViewImplConstants extends Constants {

    /** Nombre d'elements par page. */
    int NB_ELEMENT_PAR_PAGE = 20;

    /** Hauteur bloc. */
    String HAUTEUR_BLOC = "150px";

    /** Hauteur scroll panel list box multiple. */
    String HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE = "300px";

    /** Espace. */
    String SPACE = " ";

    /**
     * Label.
     * @return le label
     */
    String recherche();

    /**
     * Label.
     * @return le label
     */
    String planification();

    /**
     * Label.
     * @return le label
     */
    String information();

    /**
     * Label.
     * @return le label
     */
    String identificaton();

    /**
     * Label.
     * @return le label
     */
    String rechercher();

    /**
     * Label.
     * @return le label
     */
    String type();

    /**
     * Label.
     * @return le label
     */
    String objet();

    /**
     * Label.
     * @return le label
     */
    String sousObjet();

    /**
     * Label.
     * @return le label
     */
    String statut();

    /**
     * Label.
     * @return le label
     */
    String nature();

    /**
     * Label.
     * @return le label
     */
    String natureResultat();

    /**
     * Label.
     * @return le label
     */
    String resultat();

    /**
     * Label.
     * @return le label
     */
    String priorite();

    /**
     * Label.
     * @return le label
     */
    String reclamation();

    /**
     * Label.
     * @return le label
     */
    String libelleCampagne();

    /**
     * Label.
     * @return le label
     */
    String dateDebut();

    /**
     * Label.
     * @return le label
     */
    String dateFin();

    /**
     * Label.
     * @return le label
     */
    String headerDateAction();

    /**
     * Label.
     * @return le label
     */
    String headerDateCreation();

    /**
     * Label.
     * @return le label
     */
    String headerType();

    /**
     * Label.
     * @return le label
     */
    String headerObjet();

    /**
     * Label.
     * @return le label
     */
    String headerSousObjet();

    /**
     * Label.
     * @return le label
     */
    String headerPriorite();

    /**
     * Label.
     * @return le label
     */
    String headerStatut();

    /**
     * Label.
     * @return le label
     */
    String headerAgence();

    /**
     * Label.
     * @return le label
     */
    String headerCommerciale();

    /**
     * Label.
     * @return le label
     */
    String headerDateTerminee();

    /**
     * Titre de la popup de selection.
     * @return le titre
     */
    String titrePopUpSelection();

    /**
     * Label.
     * @return le label.
     */
    String createur();

    /**
     * Label.
     * @return le label.
     */
    String attribueA();

    /**
     * Label.
     * @return le label.
     */
    String agence();

    /**
     * Label.
     * @return le label.
     */
    String libeleCreation();

    /**
     * Label.
     * @return le label
     */
    String effacer();

    /**
     * Label.
     * @return le label
     */
    String rechercheEt();

    /**
     * Label.
     * @return le label
     */
    String headerHeureAction();

    /**
     * Libelle.
     * @return le label
     */
    String libelleBoutonTransfererActions();

    /**
     * Libelle.
     * @return le label
     */
    String libelleMenuContextuel();

    /**
     * Libelle.
     * @return le label
     */
    String erreurFiltrageStatutAFaire();

    /**
     * Libelle.
     * @return le label
     */
    String erreurFiltrageAucunResultat();

}
