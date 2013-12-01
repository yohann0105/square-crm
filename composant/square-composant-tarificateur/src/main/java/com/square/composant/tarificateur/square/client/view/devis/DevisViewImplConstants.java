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
package com.square.composant.tarificateur.square.client.view.devis;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue DevisViewImpl.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface DevisViewImplConstants extends Constants {

    /**
     * Libellé du bouton Enregistrer.
     * @return le libelle
     */
    String libelleBoutonEnregistrer();

    /**
     * Libellé du bouton Ajouter Produit.
     * @return le libelle
     */
    String libelleBoutonAjouterProduit();

    /**
     * Titre du bouton Ajouter Produit.
     * @return le libelle
     */
    String titleBoutonAjouterProduit();

    /**
     * Libellé du bouton pour envoyer un devis par mail.
     * @return le libellé.
     */
    String libelleBoutonEnvoyerMail();

    /**
     * Libellé du bouton pour imprimer un devis.
     * @return le libellé.
     */
    String libelleBoutonImprimer();

    /**
     * Libelle du bouton de transformation en devis AIA.
     * @return le message
     */
    String libelleBoutonTransformerDevisAia();

    /**
     * Titre du bouton de transformation en devis AIA.
     * @return le message
     */
    String titleBoutonTransformerDevisAia();

    /**
     * Libellé du bouton de transfert vers une nouvelle opportunité.
     * @return le message
     */
    String libelleBoutonTransfertNouveauDevis();

    /**
     * Titre du bouton de transfert vers une nouvelle opportunité.
     * @return le libelle
     */
    String titleBoutonTransfertNouveauDevis();

    /**
     * Libelle du bouton d'infos adhésion.
     * @return le libelle
     */
    String libelleBoutonInformationsAdhesion();

    /**
     * Titre du bouton d'infos adhésion.
     * @return le libelle
     */
    String titleBoutonInformationsAdhesion();

    /**
     * Libelle de la finalite de devis.
     * @return le libelle
     */
    String libelleFinaliteDevis();

    /**
     * Libelle.
     * @return le libelle
     */
    String labelMotifDevis();

    /**
     * Message d'enregistrement de devis.
     * @return le message
     */
    String enregistrementDevis();

    /**
     * Message de transfert de devis.
     * @return le message
     */
    String transfertDevis();

    /**
     * Message de la popup de succès du transfert de propositions.
     * @return le message
     */
    String messagePopupSuccesTransfertDevis();
}