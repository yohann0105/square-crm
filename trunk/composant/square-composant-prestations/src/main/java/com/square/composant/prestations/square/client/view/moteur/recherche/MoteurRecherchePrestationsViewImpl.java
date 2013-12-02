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
package com.square.composant.prestations.square.client.view.moteur.recherche;

import java.util.Date;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxComposite.SuggestListBoxCompositeProperties;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.prestations.square.client.ComposantPrestations;
import com.square.composant.prestations.square.client.content.i18n.ComposantPrestationsConstants;
import com.square.composant.prestations.square.client.model.IdentifiantEidLibelleGwt;
import com.square.composant.prestations.square.client.presenter.moteur.recherche.MoteurRecherchePrestationsPresenter.MoteurRecherchePrestationsView;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxComposite;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;

/**
 * Vue du moteur de recherche de prestation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class MoteurRecherchePrestationsViewImpl extends Composite implements MoteurRecherchePrestationsView {

    /** View constants. */
    private static MoteurRecherchePrestationsViewImplConstants viewConstants =
        (MoteurRecherchePrestationsViewImplConstants) GWT.create(MoteurRecherchePrestationsViewImplConstants.class);

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbBeneficaires;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbOrigines;

    private DecoratedSuggestListBoxComposite<IdentifiantEidLibelleGwt> slbActes;

    private DecoratedCalendrierDateBox cdbDateDebutSoins;

    private DecoratedCalendrierDateBox cdbDateFinSoins;

    private DecoratedButton btnRechercher;

    private DecoratedButton btnInitRecherche;

    private FocusPanel focusPanel;

    private VerticalPanel conteneurEntetes;

    private HorizontalPanel conteneurPagination;

    /**
     * Constructeur.
     */
    public MoteurRecherchePrestationsViewImpl() {
        final Widget conteneurBarreBoutons = construireBarreBoutons();
        conteneurPagination = new HorizontalPanel();
        conteneurPagination.setSpacing(3);
        conteneurEntetes = new VerticalPanel();
        conteneurEntetes.setWidth(ComposantPrestationsConstants.POURCENT_100);
        conteneurEntetes.setSpacing(5);

        final VerticalPanel conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setSpacing(10);
        conteneurGlobal.setWidth(ComposantPrestationsConstants.POURCENT_100);
        conteneurGlobal.add(construireBlocRecherche());
        conteneurGlobal.add(conteneurBarreBoutons);
        conteneurGlobal.add(conteneurPagination);
        conteneurGlobal.add(conteneurEntetes);
        conteneurGlobal.setCellHorizontalAlignment(conteneurBarreBoutons, HasAlignment.ALIGN_CENTER);
        conteneurGlobal.setCellHorizontalAlignment(conteneurPagination, HasAlignment.ALIGN_RIGHT);

        this.focusPanel = new FocusPanel();
        focusPanel.setWidth(ComposantPrestationsConstants.POURCENT_100);
        focusPanel.add(conteneurGlobal);

        this.initWidget(focusPanel);
        this.setWidth(ComposantPrestationsConstants.POURCENT_100);
        this.addStyleName(ComposantPrestations.RESOURCES.css().moteurRecherche());
    }

    private Widget construireBlocRecherche() {
        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties =
            new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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
        final SuggestListBoxCompositeProperties<IdentifiantEidLibelleGwt> slbIdentifiantLibelleCompositeProperties =
            new SuggestListBoxCompositeProperties<IdentifiantEidLibelleGwt>() {
                @Override
                public String getSelectSuggestRenderer(IdentifiantEidLibelleGwt row) {
                    return row == null ? "" : row.getLibelle() + " - " + row.getIdentifiantExterieur();
                }

                @Override
                public String[] getResultColumnsRenderer() {
                    return new String[] {};
                }

                @Override
                public String[] getResultRowsRenderer(IdentifiantEidLibelleGwt row) {
                    return new String[] {row == null ? "" : row.getLibelle() + " - " + row.getIdentifiantExterieur()};
                }

                @Override
                public Integer getLeftPosition() {
                    return LEFT_POSITION_CENTER;
                }

                @Override
                public String getSuggestListBoxMultiplePopupTitle() {
                    return viewConstants.selectionDesActes();
                }

                @Override
                public Integer getTopPosition() {
                    return TOP_POSITION_CENTER;
                }
            };

        slbBeneficaires = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbOrigines = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbActes = new DecoratedSuggestListBoxComposite<IdentifiantEidLibelleGwt>(slbIdentifiantLibelleCompositeProperties);
        slbActes.setScrollPanelSuggestMultipleHeight(MoteurRecherchePrestationsViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        cdbDateDebutSoins = new DecoratedCalendrierDateBox();
        cdbDateFinSoins = new DecoratedCalendrierDateBox();
        final Label lBeneficaires = creerLibelle(viewConstants.beneficaires());
        final Label lDateDebutSoins = creerLibelle(viewConstants.dateDebutSoins());
        final Label lDateFinSoins = creerLibelle(viewConstants.dateFinSoins());
        final Label lOrigine = creerLibelle(viewConstants.origine());
        final Label lActes = creerLibelle(viewConstants.actes());

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.recherche());
        final FlexTable fpRecherche = new FlexTable();
        fpRecherche.setWidth(ComposantPrestationsConstants.POURCENT_100);
        fpRecherche.setCellSpacing(5);
        fpRecherche.setWidget(0, 0, lBeneficaires);
        fpRecherche.setWidget(0, 1, slbBeneficaires);
        fpRecherche.setWidget(0, 2, lDateDebutSoins);
        fpRecherche.setWidget(0, 3, cdbDateDebutSoins);
        fpRecherche.setWidget(0, 4, lDateFinSoins);
        fpRecherche.setWidget(0, 5, cdbDateFinSoins);
        fpRecherche.setWidget(1, 0, lOrigine);
        fpRecherche.setWidget(1, 1, slbOrigines);
        fpRecherche.setWidget(1, 2, lActes);
        fpRecherche.setWidget(1, 3, slbActes);

        fieldSetPanel.add(fpRecherche);
        return fieldSetPanel;
    }

    private Widget construireBarreBoutons() {
        final HorizontalPanel pBoutons = new HorizontalPanel();
        pBoutons.setSpacing(5);
        btnRechercher = new DecoratedButton(viewConstants.rechercher());
        btnInitRecherche = new DecoratedButton(viewConstants.initRecherche());
        pBoutons.add(btnRechercher);
        pBoutons.add(btnInitRecherche);
        return pBoutons;
    }

    private Label creerLibelle(final String libelle) {
        final Label label = new Label(libelle, false);
        label.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
        label.addStyleName(ComposantPrestations.RESOURCES.css().libelleMoteurRecherche());
        return label;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(config);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public HasClickHandlers getBtnRechercher() {
        return btnRechercher;
    }

    @Override
    public HasClickHandlers getBtnInitRecherche() {
        return btnInitRecherche;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbBeneficaires() {
        return slbBeneficaires;
    }

    @Override
    public void effacerRecherche() {
        slbBeneficaires.clean();
        slbOrigines.clean();
        slbActes.clean();
        cdbDateDebutSoins.clean();
        cdbDateFinSoins.clean();
    }

    @Override
    public HasKeyPressHandlers getGestionToucheEntreHandler() {
        return focusPanel;
    }

    @Override
    public HasValue<Date> getCdbDateDebutSoins() {
        return cdbDateDebutSoins;
    }

    @Override
    public HasValue<Date> getCdbDateFinSoins() {
        return cdbDateFinSoins;
    }

    @Override
    public HasValue<List<IdentifiantEidLibelleGwt>> getSlbActes() {
        return slbActes;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbOrigines() {
        return slbOrigines;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbBeneficairesHandler() {
        return slbBeneficaires;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbOriginesHandler() {
        return slbOrigines;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantEidLibelleGwt> getSlbActesHandler() {
        return slbActes;
    }

    @Override
    public MoteurRecherchePrestationsViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public HasWidgets ajouterConteneurEntete() {
        final SimplePanel conteneurEntete = new SimplePanel();
        conteneurEntete.setWidth(ComposantPrestationsConstants.POURCENT_100);
        conteneurEntetes.add(conteneurEntete);
        return conteneurEntete;
    }

    @Override
    public void viderConteneurEntetes() {
        conteneurEntetes.clear();
    }

    @Override
    public HasClickHandlers ajouterLienPage(int numPage, boolean selected) {
        final Label labelPage = new Label(String.valueOf(numPage));
        if (selected) {
            labelPage.addStyleName(ComposantPrestations.RESOURCES.css().lienPaginationSelected());
        } else {
            labelPage.addStyleName(ComposantPrestations.RESOURCES.css().lienPagination());
        }
        conteneurPagination.add(labelPage);
        return labelPage;
    }

    @Override
    public void viderPagination() {
        conteneurPagination.clear();
    }

}
