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
package com.square.composant.tarificateur.square.client.view.popup;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.presenter.popup.PopupChoixModelesView;

/**
 * Vue de la popup d'impression d'un devis.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupChoixModelesViewImpl extends Popup implements PopupChoixModelesView {

    /** Type de la popup. */
    public enum TypePopup {
        IMPRESSION, EMAIL
    }

    /** Constantes de la vue. */
    private static PopupChoixModelesViewImplConstants viewConstants =
        (PopupChoixModelesViewImplConstants) GWT.create(PopupChoixModelesViewImplConstants.class);

    /** Panel pour les modèles. */
    private VerticalPanel panelModeles;

    /** Panel du contenu des bénéficiaires. */
    private VerticalPanel pContenuBeneficiaires;

    /** Bouton de validation. */
    private DecoratedButton btnValider;

    /** Bouton d'annulation. */
    private DecoratedButton btnAnnuler;

    /** Liste des identifiants des modèles sélectionnés. */
    private List<Long> listeIdsModelesSelectionnes;

    /** Liste des identifiants des bénéficiaires sélectionnés. */
    private List<Long> listeIdsBenefsSelectionnes;

    private CaptionPanel captionPanelBenefs;

    private FocusPanel focusPanel;

    /**
     * Constructeur.
     * @param typePopup le type de la popup
     */
    public PopupChoixModelesViewImpl(TypePopup typePopup) {
        super(typePopup == TypePopup.IMPRESSION ? viewConstants.titrePopupImpression() : viewConstants.titrePopupEmail(), false, false, true);
        listeIdsModelesSelectionnes = new ArrayList<Long>();
        listeIdsBenefsSelectionnes = new ArrayList<Long>();

        panelModeles = new VerticalPanel();
        panelModeles.setSpacing(3);
        pContenuBeneficiaires = new VerticalPanel();
        pContenuBeneficiaires.setSpacing(3);
        btnValider = new DecoratedButton(viewConstants.btnValider());
        btnAnnuler = new DecoratedButton(viewConstants.btnAnnuler());

        final HorizontalPanel pBoutons = new HorizontalPanel();
        pBoutons.setSpacing(5);
        pBoutons.add(btnValider);
        pBoutons.add(btnAnnuler);

        final CaptionPanel captionPanelModeles = new CaptionPanel(viewConstants.selectionModeles());
        captionPanelModeles.add(panelModeles);

        captionPanelBenefs = new CaptionPanel(viewConstants.selectionBeneficiaires());
        captionPanelBenefs.add(pContenuBeneficiaires);

        final VerticalPanel contenuPrincipal = new VerticalPanel();
        contenuPrincipal.setSpacing(5);
        contenuPrincipal.add(captionPanelModeles);
        contenuPrincipal.add(captionPanelBenefs);
        contenuPrincipal.setWidth(ComposantTarificateurConstants.POURCENT_100);

        focusPanel = new FocusPanel(contenuPrincipal);
        focusPanel.setWidth(ComposantTarificateurConstants.POURCENT_100);

        final VerticalPanel conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(PopupChoixModelesViewImplConstants.LARGEUR_POPUP);
        conteneurGlobal.add(focusPanel);
        conteneurGlobal.add(pBoutons);
        conteneurGlobal.setCellHorizontalAlignment(pBoutons, HasAlignment.ALIGN_CENTER);

        this.setWidget(conteneurGlobal, 0);
        this.addStyleName(ComposantTarificateur.RESOURCES.css().popupChoixModele());
    }

    @Override
    public HasClickHandlers getBtnAnnuler() {
        return btnAnnuler;
    }

    @Override
    public HasClickHandlers getBtnValider() {
        return btnValider;
    }

    @Override
    public void hidePopup() {
        this.hide();
    }

    @Override
    public void showPopup() {
        btnValider.setEnabled(false);
        this.show();
    }


    @Override
    public void chargerModelesDevis(List<IdentifiantLibelleGwt> listeModelesDevis) {
        listeIdsModelesSelectionnes = new ArrayList<Long>();
        panelModeles.clear();
        if (listeModelesDevis != null && listeModelesDevis.size() > 0) {
            for (final IdentifiantLibelleGwt modele : listeModelesDevis) {
                final CheckBox cb = new CheckBox(modele.getLibelle());
                cb.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        if (cb.getValue()) {
                            listeIdsModelesSelectionnes.add(modele.getIdentifiant());
                            btnValider.setEnabled(true);
                        }
                        else {
                            listeIdsModelesSelectionnes.remove(modele.getIdentifiant());
                            if (listeIdsModelesSelectionnes.size() == 0) {
                                btnValider.setEnabled(false);
                            }
                        }
                    }
                });
                panelModeles.add(cb);
            }
        }
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public List<Long> getIdsModelesSelectionnes() {
        return listeIdsModelesSelectionnes;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public void chargerBeneficiaires(List<IdentifiantLibelleGwt> listeBeneficiaires) {
        pContenuBeneficiaires.clear();
        if (listeBeneficiaires == null || listeBeneficiaires.size() == 0) {
            captionPanelBenefs.setVisible(false);
        }
        else {
            for (final IdentifiantLibelleGwt beneficiaire : listeBeneficiaires) {
                final CheckBox cb = new CheckBox(beneficiaire.getLibelle());
                cb.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        if (cb.getValue()) {
                            listeIdsBenefsSelectionnes.add(beneficiaire.getIdentifiant());
                        }
                        else {
                            listeIdsBenefsSelectionnes.remove(beneficiaire.getIdentifiant());
                        }
                    }
                });
                pContenuBeneficiaires.add(cb);
            }
            captionPanelBenefs.setVisible(true);
        }

    }

    @Override
    public List<Long> getIdsBeneficiairesSelectionnes() {
        return listeIdsBenefsSelectionnes;
    }

    @Override
    public void initFocus() {
        if (panelModeles.getWidgetCount() > 0) {
            ((CheckBox) panelModeles.getWidget(0)).setFocus(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasAllKeyHandlers getAllKeyHandlersFocusPanel() {
        return focusPanel;
    }

    @Override
    public void nettoyer() {
    	listeIdsModelesSelectionnes.clear();
        for (int i = 0; i < panelModeles.getWidgetCount(); i++) {
            if (panelModeles.getWidget(i) instanceof CheckBox) {
                ((CheckBox) panelModeles.getWidget(i)).setValue(false);
            }
        }
        for (int i = 0; i < pContenuBeneficiaires.getWidgetCount(); i++) {
            if (pContenuBeneficiaires.getWidget(i) instanceof CheckBox) {
                ((CheckBox) pContenuBeneficiaires.getWidget(i)).setValue(false);
            }
        }
    }
}
