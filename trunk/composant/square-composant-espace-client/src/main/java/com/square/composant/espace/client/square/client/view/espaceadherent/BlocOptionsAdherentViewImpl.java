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
package com.square.composant.espace.client.square.client.view.espaceadherent;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.espace.client.square.client.content.i18n.ComposantEspaceClientConstants;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter.BlocOptionsAdherentView;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;

/**
 * Implémentation de la vue de gestion des options d'un adhérent.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class BlocOptionsAdherentViewImpl extends Composite implements BlocOptionsAdherentView {

    private BlocOptionsAdherentViewImplConstants viewConstants;

    private Label lOptionSms;

    private Label lOptionMutuellement;

    private Label lOptionRelevesPrestation;

    private DecoratedButton btnEnregistrerOptions;

    private RadioButton rbtnOptionSmsActive;

    private RadioButton rbtnOptionMutuellementActive;

    private RadioButton rbtnOptionRelevesPrestationsActive;

    private RadioButton rbtnOptionSmsDesactive;

    private RadioButton rbtnOptionMutuellementDesactive;

    private RadioButton rbtnOptionRelevesPrestationsDesactive;

    private Label lDateModifOptionSms;

    private Label lDateModifOptionMensuellement;

    private Label lDateModifOptionRelevesPrestations;

    /**
     * Constructeur.
     */
    public BlocOptionsAdherentViewImpl() {
        super();
        viewConstants = (BlocOptionsAdherentViewImplConstants) GWT.create(BlocOptionsAdherentViewImplConstants.class);
        initWidget(construireBlocOptions());
    }

    /**
     * Construit le Widget encapsulé dans la vue.
     * @return le widget encapsulé.
     */
    private Widget construireBlocOptions() {
        lOptionSms = new Label(viewConstants.labelOptionDesactive());
        lOptionMutuellement = new Label(viewConstants.labelOptionDesactive());
        lOptionRelevesPrestation = new Label(viewConstants.labelOptionDesactive());
        rbtnOptionSmsActive = new RadioButton(viewConstants.labelOptionSms());
        rbtnOptionSmsDesactive = new RadioButton(viewConstants.labelOptionSms());
        rbtnOptionSmsDesactive.setValue(true);
        rbtnOptionMutuellementActive = new RadioButton(viewConstants.labelOptionMutuellement());
        rbtnOptionMutuellementDesactive = new RadioButton(viewConstants.labelOptionMutuellement());
        rbtnOptionMutuellementDesactive.setValue(true);
        rbtnOptionRelevesPrestationsActive = new RadioButton(viewConstants.labelOptionRelevesPrestations());
        rbtnOptionRelevesPrestationsDesactive = new RadioButton(viewConstants.labelOptionRelevesPrestations());
        rbtnOptionRelevesPrestationsDesactive.setValue(true);
        lDateModifOptionSms = new Label(viewConstants.aucuneDate());
        lDateModifOptionMensuellement = new Label(viewConstants.aucuneDate());
        lDateModifOptionRelevesPrestations = new Label(viewConstants.aucuneDate());
        btnEnregistrerOptions = new DecoratedButton(viewConstants.captionEnregistrerOptions());

        final FlexTable ftEditionOptions = new FlexTable();
        ftEditionOptions.setStylePrimaryName(EspaceClientPresenter.RESOURCES.css().tableauOptions());
        ftEditionOptions.setCellPadding(6);
        ftEditionOptions.setWidget(0, 1, new Label(viewConstants.labelOptionActive()));
        ftEditionOptions.setWidget(0, 2, new Label(viewConstants.labelOptionDesactive()));
        ftEditionOptions.setWidget(0, 3, new Label(viewConstants.labelDateModification()));
        ftEditionOptions.setWidget(1, 0, new Label(viewConstants.labelOptionSms()));
        ftEditionOptions.setWidget(1, 1, rbtnOptionSmsActive);
        ftEditionOptions.setWidget(1, 2, rbtnOptionSmsDesactive);
        ftEditionOptions.setWidget(1, 3, lDateModifOptionSms);
        ftEditionOptions.setWidget(2, 0, new Label(viewConstants.labelOptionMutuellement()));
        ftEditionOptions.setWidget(2, 1, rbtnOptionMutuellementActive);
        ftEditionOptions.setWidget(2, 2, rbtnOptionMutuellementDesactive);
        ftEditionOptions.setWidget(2, 3, lDateModifOptionMensuellement);
        ftEditionOptions.setWidget(3, 0, new Label(viewConstants.labelOptionRelevesPrestations()));
        ftEditionOptions.setWidget(3, 1, rbtnOptionRelevesPrestationsActive);
        ftEditionOptions.setWidget(3, 2, rbtnOptionRelevesPrestationsDesactive);
        ftEditionOptions.setWidget(3, 3, lDateModifOptionRelevesPrestations);
        ftEditionOptions.getCellFormatter().setStylePrimaryName(0, 0, EspaceClientPresenter.RESOURCES.css().celluleSansBordure());
        ftEditionOptions.getCellFormatter().setStylePrimaryName(0, 1, EspaceClientPresenter.RESOURCES.css().celluleEnteteColonne());
        ftEditionOptions.getCellFormatter().setStylePrimaryName(0, 2, EspaceClientPresenter.RESOURCES.css().celluleEnteteColonne());
        ftEditionOptions.getCellFormatter().setStylePrimaryName(0, 3, EspaceClientPresenter.RESOURCES.css().celluleEnteteColonne());
        ftEditionOptions.getCellFormatter().setStylePrimaryName(1, 0, EspaceClientPresenter.RESOURCES.css().celluleEnteteLigne());
        ftEditionOptions.getCellFormatter().setStylePrimaryName(2, 0, EspaceClientPresenter.RESOURCES.css().celluleEnteteLigne());
        ftEditionOptions.getCellFormatter().setStylePrimaryName(3, 0, EspaceClientPresenter.RESOURCES.css().celluleEnteteLigne());

        final int nbRows = ftEditionOptions.getRowCount();
        for (int i = 0; i < nbRows; i++) {
            ftEditionOptions.getCellFormatter().setHorizontalAlignment(i, 1, HasHorizontalAlignment.ALIGN_CENTER);
            ftEditionOptions.getCellFormatter().setHorizontalAlignment(i, 2, HasHorizontalAlignment.ALIGN_CENTER);
            ftEditionOptions.getCellFormatter().setHorizontalAlignment(i, 3, HasHorizontalAlignment.ALIGN_CENTER);
        }
        ftEditionOptions.getColumnFormatter().setWidth(0, BlocOptionsAdherentViewImplConstants.LARGEUR_COL);
        ftEditionOptions.getColumnFormatter().setWidth(1, BlocOptionsAdherentViewImplConstants.LARGEUR_COL);
        ftEditionOptions.getColumnFormatter().setWidth(2, BlocOptionsAdherentViewImplConstants.LARGEUR_COL);
        ftEditionOptions.getColumnFormatter().setWidth(3, BlocOptionsAdherentViewImplConstants.LARGEUR_COL);

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setSpacing(10);
        conteneur.setWidth(ComposantEspaceClientConstants.POURCENT_100);
        conteneur.add(ftEditionOptions);
        conteneur.add(btnEnregistrerOptions);
        conteneur.setCellHorizontalAlignment(ftEditionOptions, HasAlignment.ALIGN_CENTER);
        conteneur.setCellHorizontalAlignment(btnEnregistrerOptions, HasAlignment.ALIGN_CENTER);

        final List<ChampSynthese> listeChamps = new ArrayList<ChampSynthese>();
        listeChamps.add(new ChampSynthese(lOptionSms, viewConstants.labelOptionSms(), false));
        listeChamps.add(new ChampSynthese(lOptionMutuellement, viewConstants.labelOptionMutuellement(), false));
        listeChamps.add(new ChampSynthese(lOptionRelevesPrestation, viewConstants.labelOptionRelevesPrestations(), false));
        final BlocSyntheseDepliant blocOptions = new BlocSyntheseDepliant(listeChamps, conteneur);
        blocOptions.setStylePrimaryName(EspaceClientPresenter.RESOURCES.css().blocSyntheseDepliant());

        final CaptionPanel cpOptions = new CaptionPanel(viewConstants.captionBlocOptions());
        cpOptions.add(blocOptions);
        return cpOptions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasClickHandlers getHandlerEnregistrerOptions() {
        return btnEnregistrerOptions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<Boolean> getOptionMutuellementActive() {
        return rbtnOptionMutuellementActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<Boolean> getOptionMutuellementDesactive() {
        return rbtnOptionMutuellementDesactive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<Boolean> getOptionRelevesPrestationsActive() {
        return rbtnOptionRelevesPrestationsActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<Boolean> getOptionRelevesPrestationsDesactive() {
        return rbtnOptionRelevesPrestationsDesactive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<Boolean> getOptionSmsActive() {
        return rbtnOptionSmsActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<Boolean> getOptionSmsDesactive() {
        return rbtnOptionSmsDesactive;
    }

    @Override
    public HasValueChangeHandlers<Boolean> getOptionMutuellementActiveEventHandler() {
        return rbtnOptionMutuellementActive;
    }

    @Override
    public HasValueChangeHandlers<Boolean> getOptionMutuellementDesactiveEventHandler() {
        return rbtnOptionMutuellementDesactive;
    }

    @Override
    public HasValueChangeHandlers<Boolean> getOptionRelevesPrestationsActiveEventHandler() {
        return rbtnOptionRelevesPrestationsActive;
    }

    @Override
    public HasValueChangeHandlers<Boolean> getOptionRelevesPrestationsDesactiveEventHandler() {
        return rbtnOptionRelevesPrestationsDesactive;
    }

    @Override
    public HasValueChangeHandlers<Boolean> getOptionSmsActiveEventHandler() {
        return rbtnOptionSmsActive;
    }

    @Override
    public HasValueChangeHandlers<Boolean> getOptionSmsDesactiveEventHandler() {
        return rbtnOptionSmsDesactive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getDateModificationOptionMutuellement() {
        return lDateModifOptionMensuellement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getDateModificationOptionRelevesPrestations() {
        return lDateModifOptionRelevesPrestations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getDateModificationOptionSms() {
        return lDateModifOptionSms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getLabelOptionMutuellement() {
        return lOptionMutuellement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getLabelOptionRelevesPrestations() {
        return lOptionRelevesPrestation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getLabelOptionSms() {
        return lOptionSms;
    }

    @Override
    public void setBlocOptionVisible(boolean visible) {
        setVisible(visible);
    }
}
