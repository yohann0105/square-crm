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
package com.square.composant.tarificateur.square.client.view.ligne.devis;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue LigneDevisViewImpl.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface LigneDevisViewImplConstants extends Constants {

    /**
     * Libelle.
     * @return le libelle
     */
    String titreCheckboxTransfertSelection();

    /**
     * Libelle.
     * @return le libelle
     */
    String labelDateCreation();

    /**
     * Libelle.
     * @return le libelle
     */
    String labelCreateur();

    /**
     * Libelle.
     * @return le libellconste
     */
    String labelProvenance();

    /**
     * Libelle.
     * @return le libelle
     */
    String libelleBoutonVisualiser();

    /**
     * Libelle.
     * @return le libelle
     */
    String titreBoutonVisualiser();

    /**
     * Libelle.
     * @return le libelle
     */
    String libelleBoutonModifier();

    /**
     * Libelle.
     * @return le libelle
     */
    String titreBoutonModifier();

    /**
     * Le texte.
     * @return le texte
     */
    String titreColonneProduits();

    /**
     * Le texte.
     * @return le texte
     */
    String titreColonneNomPrenom();

    /**
     * Le texte.
     * @return le texte
     */
    String titreColonneCriteres();

    /**
     * Le texte.
     * @return le texte
     */
    String titreColonneDateEffet();

    /**
     * Le texte.
     * @return le texte
     */
    String titreColonneTarifs();

    /**
     * Le texte.
     * @return le texte
     */
    String labelColonneAdhesion();

    /**
     * Le texte.
     * @return le texte
     */
    String labelColonneRefus();

    /**
     * Le texte.
     * @return le texte
     */
    String labelColonneEnCours();

    /**
     * Le texte.
     * @return le texte
     */
    String labelColonneCorbeille();

    /**
     * Le texte.
     * @return le texte
     */
    String titleColonneAdhesion();

    /**
     * Le texte.const
     * @return le texte
     */
    String titleColonneRefus();

    /**
     * Le texte.
     * @return le texte
     */
    String titleColonneCorbeille();

    /**
     * Le texte.
     * @return le texte
     */
    String titleColonneEnCours();

    /**
     * Le texte.
     * @return le texte
     */
    String titreColonneSelectionImpression();

    /**
     * Le texte.
     * @return le texte
     */
    String libelleOffert();

    /**
     * Le texte.
     * @return le texte
     */
    String libelleBeneficiaire();

    /**
     * Le texte.
     * @return le texte
     */
    String titreRadioAccepte();

    /**
     * Le texte.
     * @return le texte
     */
    String titreRadioRefuse();

    /**
     * Le texte.
     * @return le texte
     */
    String titreRadioEnCours();

    /**
     * Le texte.
     * @return le texte
     */
    String titreRadioCorbeille();

    /**
     * Le texte.
     * @return le texte
     */
    String titreCheckboxSelection();

    /**
     * Le texte.
     * @return le texte
     */
    String symboleMonnaie();

    /**
     * Le texte.
     * @return le texte
     */
    String libelleTotal();

    /**
     * Le texte.
     * @return le texte
     */
    String libelleTTC();

    /**
     * Le texte.
     * @return le texte
     */
    String libelleTotalRemise();

    /**
     * Le texte.
     * @return le texte
     */
    String libelleTTCApresRemise();

    /**
     * Le texte.
     * @return le texte
     */
    String libelleModePaiementMensuel();

    /**
     * Retourne le message d'erreur pour la selection de plus de deux lignes principales.
     * @return le message
     */
    String erreurSelectionLignesMax();

    /**
     * Le texte.
     * @return le texte
     */
    String produitNonVendu();

    /**
     * Retourne le message d'erreur quand un devis possède des produits non vendu.
     * @return le message
     */
    String possedeProduitsNonVendu();
    
    /**.
     * retourn le nombre maximum decaractères affichées pour le nom et prénom
     * @return la valeur
     */
    int nbMaxCaracteresNomPrenom();
}
