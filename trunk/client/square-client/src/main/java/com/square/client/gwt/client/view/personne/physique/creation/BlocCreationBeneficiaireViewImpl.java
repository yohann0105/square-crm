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

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.bundle.theme.smatis.SmatisResources;
import com.square.client.gwt.client.presenter.personne.physique.PersonnePhysiqueCreationPresenter;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.DecoratedTextBoxFormat;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue du bloc d'un bénéficiaire.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class BlocCreationBeneficiaireViewImpl extends Composite implements PersonnePhysiqueCreationPresenter.BlocCreationBeneficiaireView {

    /** View constants. */
    private PersonnePhysiqueCreationViewImplConstants viewConstants;

    /** View constants debugId. */
    private static PersonnePhysiqueCreationViewImplDebugIdConstants viewDebugIdConstants =
        (PersonnePhysiqueCreationViewImplDebugIdConstants) GWT.create(PersonnePhysiqueCreationViewImplDebugIdConstants.class);

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbCivilite;

    private SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbCiviliteProperties;

    private DecoratedTextBoxFormat tbNom;

    private DecoratedTextBoxFormat tbPrenom;

    private DecoratedCalendrierDateBox cdbDateNaissance;

    private CheckBox cbRattacherParents;

    private FlexTable flexTable;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Panel pour prévenir de la présence d'un doublon. */
    private HorizontalPanel pWarningDoublon;

    private int index;

    /**
     * Constructeur.
     * @param iconeErreurChampManager manager des icones des champs d'erreur.
     * @param viewConstants les constantes d'application.
     * @param index index du bloc.
     */
    public BlocCreationBeneficiaireViewImpl(IconeErreurChampManager iconeErreurChampManager, PersonnePhysiqueCreationViewImplConstants viewConstants,
        int index) {
        this.index = index;
        this.viewConstants = viewConstants;
        this.iconeErreurChampManager = iconeErreurChampManager;
        flexTable = new FlexTable();
        flexTable.setCellSpacing(5);
        construireBlocBeneficiaire();
        initWidget(flexTable);
    }

    /**
     * Construit le bloc du beneficiaire.
     */
    private void construireBlocBeneficiaire() {
        final Label lCivilite = new Label(viewConstants.civilite());
        final Label lNom = new Label(viewConstants.nom());
        final Label lDateNaissance = new Label(viewConstants.dateNaissance());
        lCivilite.setWordWrap(false);
        lNom.setWordWrap(false);
        lDateNaissance.setWordWrap(false);

        slbCiviliteProperties = new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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
        slbCivilite = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbCiviliteProperties);
        slbCivilite.ensureDebugId(viewDebugIdConstants.debugIdSlbCivilite());
        tbNom = new DecoratedTextBoxFormat("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        tbNom.ensureDebugId(viewDebugIdConstants.debugIdTbNom());
        tbPrenom = new DecoratedTextBoxFormat("AAAAAAAAAAAAAAAAAAAAAAAAA");
        tbPrenom.ensureDebugId(viewDebugIdConstants.debugIdTbPrenom());
        cdbDateNaissance = new DecoratedCalendrierDateBox(true);
        cdbDateNaissance.ensureDebugId(viewDebugIdConstants.debugIdCdbDateNaissance());
        cbRattacherParents = new CheckBox(viewConstants.rattacherAuxParents());
        cbRattacherParents.ensureDebugId(viewDebugIdConstants.debugIdCbRattacherParents());
        cbRattacherParents.setVisible(false);
        cbRattacherParents.setValue(false);

        int row = 0;
        final int spacing = 3;

        final HorizontalPanel ligneCivilite = new HorizontalPanel();
        ligneCivilite.setSpacing(spacing);
        ligneCivilite.add(construireBlocIcone(slbCivilite, "BeneficiaireDto.civilite." + index));
        flexTable.setWidget(row, 0, lCivilite);
        flexTable.setWidget(row++, 1, ligneCivilite);

        final HorizontalPanel ligneNom = new HorizontalPanel();
        ligneNom.setSpacing(spacing);
        ligneNom.add(construireBlocIcone(tbNom, "BeneficiaireDto.nom." + index));
        ligneNom.add(construireBlocIcone(tbPrenom, "BeneficiaireDto.prenom." + index));
        flexTable.setWidget(row, 0, lNom);
        flexTable.setWidget(row++, 1, ligneNom);

        final HorizontalPanel ligneDateNaissance = new HorizontalPanel();
        ligneDateNaissance.setSpacing(spacing);
        ligneDateNaissance.add(construireBlocIcone(cdbDateNaissance, "BeneficiaireDto.dateNaissance." + index));
        flexTable.setWidget(row, 0, lDateNaissance);
        flexTable.setWidget(row++, 1, ligneDateNaissance);

        flexTable.setWidget(row++, 1, cbRattacherParents);

        pWarningDoublon = new HorizontalPanel();
        pWarningDoublon.setVisible(false);
        pWarningDoublon.setSpacing(2);
        final Image imgWarning = new Image(SmatisResources.INSTANCE.imgWarning());
        pWarningDoublon.add(imgWarning);
        final Label lWarningDoublon = new Label(viewConstants.warningDoublons());
        lWarningDoublon.ensureDebugId(viewDebugIdConstants.debugIdLWarningDoublon());
        lWarningDoublon.addStyleName(SquareResources.INSTANCE.css().labelReclamation());
        pWarningDoublon.add(lWarningDoublon);
        flexTable.setWidget(row, 0, pWarningDoublon);
        flexTable.getFlexCellFormatter().setColSpan(row++, 0, 2);
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
    public Widget asWidget() {
        return this;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateNaissance() {
        return cdbDateNaissance;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbCivilite() {
        return slbCivilite;
    }

    @Override
    public HasValue<String> getTbNom() {
        return tbNom;
    }

    @Override
    public HasValue<String> getTbPrenom() {
        return tbPrenom;
    }

    @Override
    public void afficherWarningDoublons() {
        pWarningDoublon.setVisible(true);
    }

    @Override
    public void masquerWarningDoublons() {
        pWarningDoublon.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CheckBox getCbRattacherParents() {
        return cbRattacherParents;
    }
}
