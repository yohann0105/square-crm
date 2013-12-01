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
package com.square.composant.tarificateur.square.client.view.popup.ajout.assure;

import java.util.Date;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.CaisseSimpleModel;
import com.square.composant.tarificateur.square.client.presenter.popup.ajout.assure.PopupAjoutAssurePresenter.PopupAjoutAssureView;
import com.square.composant.tarificateur.square.client.view.adhesion.InfosAdhesionViewImplConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue de la popup d'impression d'un devis.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupAjoutAssureViewImpl extends Popup implements PopupAjoutAssureView {

    /** Constantes de la vue. */
    private static PopupAjoutAssureViewImplConstants viewConstants = (PopupAjoutAssureViewImplConstants) GWT.create(PopupAjoutAssureViewImplConstants.class);

    /** Bouton de validation. */
    private DecoratedButton btnValider;

    /** Bouton d'annulation. */
    private DecoratedButton btnAnnuler;

    private FocusPanel focusPanel;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbCivilite;

    private DecoratedTextBox tbNom;

    private DecoratedTextBox tbPrenom;

    private DecoratedCalendrierDateBox cdbDateNaissance;

    private DecoratedTextBox tbNumSS;

    private DecoratedTextBox tbCleSS;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbRegime;

    private DecoratedSuggestListBoxSingle<CaisseSimpleModel> slbCaisse;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Panel pour prévenir de la présence d'un doublon. */
    private HorizontalPanel pWarningDoublon;

    /**
     * Constructeur.
     */
    public PopupAjoutAssureViewImpl() {
        super(viewConstants.titrePopup(), false, true, true);

        iconeErreurChampManager = new IconeErreurChampManager();

        btnValider = new DecoratedButton(viewConstants.btnValider());
        btnAnnuler = new DecoratedButton(viewConstants.btnAnnuler());

        final HorizontalPanel pBoutons = new HorizontalPanel();
        pBoutons.setSpacing(5);
        pBoutons.add(btnValider);
        pBoutons.add(btnAnnuler);

        final VerticalPanel contenuPrincipal = new VerticalPanel();
        contenuPrincipal.setSpacing(5);
        contenuPrincipal.setWidth(ComposantTarificateurConstants.POURCENT_100);

        final Image imgWarning = new Image(ComposantTarificateur.RESOURCES.iconeWarning());
        final Label lWarningDoublon = new Label(viewConstants.warningDoublons());
        lWarningDoublon.addStyleName(ComposantTarificateur.RESOURCES.css().labelReclamation());
        pWarningDoublon = new HorizontalPanel();
        pWarningDoublon.setVisible(false);
        pWarningDoublon.setSpacing(2);
        pWarningDoublon.add(imgWarning);
        pWarningDoublon.add(lWarningDoublon);
        pWarningDoublon.setCellVerticalAlignment(lWarningDoublon, HasVerticalAlignment.ALIGN_MIDDLE);

        focusPanel = new FocusPanel(contenuPrincipal);
        focusPanel.setWidth(ComposantTarificateurConstants.POURCENT_100);
        focusPanel.setWidget(construireFormulaire());

        final VerticalPanel conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(PopupAjoutAssureViewImplConstants.LARGEUR_POPUP);
        conteneurGlobal.add(pWarningDoublon);
        conteneurGlobal.add(focusPanel);
        conteneurGlobal.add(pBoutons);
        conteneurGlobal.setCellHorizontalAlignment(pBoutons, HasAlignment.ALIGN_CENTER);
        conteneurGlobal.setCellHorizontalAlignment(pWarningDoublon, HasAlignment.ALIGN_CENTER);

        this.setWidget(conteneurGlobal, 0);
        this.addStyleName(ComposantTarificateur.RESOURCES.css().popupAjoutAssure());
    }

    private FlexTable construireFormulaire() {
        final Label lCivilite = new Label(viewConstants.civilite());
        final Label lNom = new Label(viewConstants.nom());
        final Label lPrenom = new Label(viewConstants.prenom());
        final Label lDateNaissance = new Label(viewConstants.dateNaissance());
        final Label lNumSS = new Label(viewConstants.numSS());
        final Label lCleSS = new Label(viewConstants.cleSS());
        final Label lRegime = new Label(viewConstants.regime());
        final Label lCaisse = new Label(viewConstants.caisse());

        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> properties = new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
            @Override
            public String getSelectSuggestRenderer(IdentifiantLibelleGwt row) {
                return row == null ? "" : row.getLibelle();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(IdentifiantLibelleGwt row) {
                return new String[] {row == null ? "" : row.getLibelle()};
            }
        };
        final SuggestListBoxSingleProperties<CaisseSimpleModel> propertiesCaisse = new SuggestListBoxSingleProperties<CaisseSimpleModel>() {
            @Override
            public String getSelectSuggestRenderer(CaisseSimpleModel row) {
                return row == null ? "" : row.getCode() + "." + row.getCentre() + "." + row.getNom();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(CaisseSimpleModel row) {
                return new String[] {row == null ? "" : row.getCode() + "." + row.getCentre() + "." + row.getNom()};
            }
        };

        slbCivilite = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(properties);
        slbCaisse = new DecoratedSuggestListBoxSingle<CaisseSimpleModel>(propertiesCaisse);
        slbRegime = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(properties);
        tbNom = new DecoratedTextBox();
        tbPrenom = new DecoratedTextBox();
        tbNumSS = new DecoratedTextBox();
        tbCleSS = new DecoratedTextBox();
        cdbDateNaissance = new DecoratedCalendrierDateBox(true);

        tbNumSS.setMaxLength(InfosAdhesionViewImplConstants.LONGUEUR_NUM_SECURITE_SOCIALE);
        tbCleSS.setMaxLength(InfosAdhesionViewImplConstants.LONGUEUR_CLE_SECURITE_SOCIALE);
        tbCleSS.setWidth(InfosAdhesionViewImplConstants.LARGEUR_TB_CLE_SS);

        final FlexTable formulaire = new FlexTable();
        formulaire.setWidth(ComposantTarificateurConstants.POURCENT_100);
        formulaire.setWidget(0, 0, lCivilite);
        formulaire.setWidget(0, 1, construireBlocIcone(slbCivilite, "PersonneDto.eidCivilite"));
        formulaire.setWidget(0, 2, lDateNaissance);
        formulaire.setWidget(0, 3, construireBlocIcone(cdbDateNaissance, "PersonneDto.dateNaissance"));
        formulaire.setWidget(1, 0, lNom);
        formulaire.setWidget(1, 1, construireBlocIcone(tbNom, "PersonneDto.nom"));
        formulaire.setWidget(1, 2, lPrenom);
        formulaire.setWidget(1, 3, construireBlocIcone(tbPrenom, "PersonneDto.prenom"));
        formulaire.setWidget(2, 0, lNumSS);
        formulaire.setWidget(2, 1, tbNumSS);
        formulaire.setWidget(2, 2, lCleSS);
        formulaire.setWidget(2, 3, construireBlocIcone(tbCleSS, "PersonneDto.infoSante.numSecuriteSocial"));
        formulaire.setWidget(3, 0, lRegime);
        formulaire.setWidget(3, 1, construireBlocIcone(slbRegime, "PersonneDto.infoSante.eidRegime"));
        formulaire.setWidget(3, 2, lCaisse);
        formulaire.setWidget(3, 3, construireBlocIcone(slbCaisse, "PersonneDto.infoSante.eidCaisse"));
        return formulaire;
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        panel.add(icone);
        panel.setCellVerticalAlignment(icone, HasAlignment.ALIGN_MIDDLE);
        return panel;
    }

    @Override
    public void desactiverBoxs() {
    	slbCivilite.setEnabled(false);
    	tbNom.setEnabled(false);
    	tbPrenom.setEnabled(false);
    	cdbDateNaissance.setEnabled(false);
    	tbNumSS.setEnabled(false);
    	tbCleSS.setEnabled(false);
    	slbRegime.setEnabled(false);
    	slbCaisse.setEnabled(false);
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
        this.show();
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        if (errorPopupConfiguration != null) {
            ErrorPopup.afficher(errorPopupConfiguration);
        }
    }

    @Override
    public void initFocus() {
        slbCivilite.setFocus(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasAllKeyHandlers getAllKeyHandlersFocusPanel() {
        return focusPanel;
    }

    @Override
    public PopupAjoutAssureViewImplConstants getConstants() {
        return viewConstants;
    }

    @Override
    public HasValue<CaisseSimpleModel> getCaisse() {
        return slbCaisse;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getCivilite() {
        return slbCivilite;
    }

    @Override
    public HasValue<String> getCleSS() {
        return tbCleSS;
    }

    @Override
    public HasValue<Date> getDateNaissance() {
        return cdbDateNaissance;
    }

    @Override
    public HasValue<String> getNom() {
        return tbNom;
    }

    @Override
    public HasValue<String> getNumSS() {
        return tbNumSS;
    }

    @Override
    public HasValue<String> getPrenom() {
        return tbPrenom;
    }

    @Override
    public HasSuggestListBoxHandler<CaisseSimpleModel> getCaisseSuggestHandler() {
        return slbCaisse;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getCiviliteSuggestHandler() {
        return slbCivilite;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getRegimeSuggestHandler() {
        return slbRegime;
    }

    @Override
    public HasValueChangeHandlers<IdentifiantLibelleGwt> getRegimeValueChangeHandler() {
        return slbRegime;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getRegime() {
        return slbRegime;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public void onRpcSuccess() {
        LoadingPopup.stopAll();
    }

    @Override
    public void onRpcCall(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public void afficherWarningDoublons(boolean visible) {
        pWarningDoublon.setVisible(visible);
    }
}
