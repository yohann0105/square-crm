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
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.espace.client.square.client.content.i18n.ComposantEspaceClientConstants;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter.BlocIdentifiantsAdherentView;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;

/**
 * Implémentation de la vue de gestion des identifiants de l'espace personnel d'une personne.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class BlocIdentifiantsAdherentViewImpl extends Composite implements BlocIdentifiantsAdherentView {

    private BlocIdentifiantsAdherentViewImplConstants viewConstants;

    private Label lEmailAdherent;

    private Label lTelephoneAdherent;

    private Label lNumeroAdherent;

    private CheckBox cbModeEnvoiEmail;

    private CheckBox cbModeEnvoiSMS;

    private DecoratedButton btnEnvoiMdp;

    /**
     * Constructeur.
     */
    public BlocIdentifiantsAdherentViewImpl() {
        super();
        viewConstants = (BlocIdentifiantsAdherentViewImplConstants) GWT.create(BlocIdentifiantsAdherentViewImplConstants.class);
        initWidget(construireBlocIdentifiants());
    }

    private Widget construireBlocIdentifiants() {
        lEmailAdherent = new Label();
        lTelephoneAdherent = new Label();
        lNumeroAdherent = new Label();
        cbModeEnvoiEmail = new CheckBox(viewConstants.captionModeEnvoiEmail());
        cbModeEnvoiSMS = new CheckBox(viewConstants.captionModeEnvoiSMS());
        btnEnvoiMdp = new DecoratedButton(viewConstants.captionEnvoyerMotDePasse());
        final VerticalPanel conteneurModesEnvoi = new VerticalPanel();
        conteneurModesEnvoi.setSpacing(5);
        conteneurModesEnvoi.add(cbModeEnvoiEmail);
        conteneurModesEnvoi.add(cbModeEnvoiSMS);
        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth(ComposantEspaceClientConstants.POURCENT_100);
        conteneur.setSpacing(10);
        conteneur.add(conteneurModesEnvoi);
        conteneur.add(btnEnvoiMdp);
        conteneur.setCellHorizontalAlignment(conteneurModesEnvoi, HasAlignment.ALIGN_CENTER);
        conteneur.setCellHorizontalAlignment(btnEnvoiMdp, HasAlignment.ALIGN_CENTER);

        final List<ChampSynthese> listeChamps = new ArrayList<ChampSynthese>();
        listeChamps.add(new ChampSynthese(lEmailAdherent, viewConstants.lEmailPersonne(), true));
        listeChamps.add(new ChampSynthese(lTelephoneAdherent, viewConstants.lTelephonePersonne(), true));
        listeChamps.add(new ChampSynthese(lNumeroAdherent, viewConstants.lIdentifiantConnexion(), true));
        final BlocSyntheseDepliant blocIdentifiants = new BlocSyntheseDepliant(listeChamps, conteneur);
        blocIdentifiants.setStylePrimaryName(EspaceClientPresenter.RESOURCES.css().blocSyntheseDepliant());

        final CaptionPanel cpIdentifiants = new CaptionPanel(viewConstants.captionBlocIdentifiants());
        cpIdentifiants.add(blocIdentifiants);
        return cpIdentifiants;
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
    public HasText getEmail() {
        return lEmailAdherent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getTelephone() {
        return lTelephoneAdherent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getIdentifiantConnexion() {
        return lNumeroAdherent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<Boolean> getModeEnvoiEmail() {
        return cbModeEnvoiEmail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<Boolean> getModeEnvoiSMS() {
        return cbModeEnvoiSMS;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public HasValueChangeHandlers<Boolean> getValueChangeHandlerModeEnvoiEmail() {
        return cbModeEnvoiEmail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValueChangeHandlers<Boolean> getValueChangeHandlerModeEnvoiSMS() {
        return cbModeEnvoiSMS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasClickHandlers getHandlerEnvoiMotDePasse() {
        return btnEnvoiMdp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableEnvoiMotDePasse(boolean enable) {
        btnEnvoiMdp.setEnabled(enable);
    }

    @Override
    public void enableCbEmail(boolean enable) {
        cbModeEnvoiEmail.setEnabled(enable);
    }

    @Override
    public void enableCbTelephone(boolean enable) {
        cbModeEnvoiSMS.setEnabled(enable);
    }

}
