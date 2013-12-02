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
package com.square.composant.tarificateur.square.client.presenter.popup;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;

/**
 * Interface de la vue.
 */
public interface PopupChoixModelesView extends View {
    /**
     * Affiche la popup d'erreur.
     * @param errorPopupConfiguration la configuration
     */
    void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

    /**
     * Récupère le handler du bouton de validation.
     * @return le handler.
     */
    HasClickHandlers getBtnValider();

    /**
     * Récupère le handler du bouton d'annulation.
     * @return le handler.
     */
    HasClickHandlers getBtnAnnuler();

    /**
     * Affiche la popup.
     */
    void showPopup();

    /**
     * Cache la popup.
     */
    void hidePopup();

    /**
     * Récupère la liste des identifiants de modèles de devis sélectionnés.
     * @return la liste des identifiants.
     */
    List<Long> getIdsModelesSelectionnes();

    /**
     * Charge la liste des modèles de devis dans la vue.
     * @param listeModelesDevis la liste des modèles de devis.
     */
    void chargerModelesDevis(List<IdentifiantLibelleGwt> listeModelesDevis);

    /**
     * Charge la liste bénéficiaires dans la vue.
     * @param listeBeneficiaires la liste des bénéficiaires.
     */
    void chargerBeneficiaires(List<IdentifiantLibelleGwt> listeBeneficiaires);

    /**
     * Récupère la liste des identifiants des bénéficiaires sélectionnés.
     * @return la liste des identifiants.
     */
    List<Long> getIdsBeneficiairesSelectionnes();

    /**
     * Accesseur AllKeyHandlers du focus panel.
     * @return le handler
     */
    HasAllKeyHandlers getAllKeyHandlersFocusPanel();

    /**
     * Initialise le focus.
     */
    void initFocus();

    /**
     * Nettoie la vue (déselectionne les composant).
     */
    void nettoyer();
}
