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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.espace.client.square.client.content.i18n.ComposantEspaceClientConstants;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter.BlocSyntheseEspaceAdherentView;

/**
 * Implémentation de la vue contenant la synthèse de l'espace personnel d'un adhérent.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class BlocSyntheseEspaceAdherentViewImpl extends Composite implements BlocSyntheseEspaceAdherentView {

    private BlocSyntheseEspaceAdherentViewImplConstants viewConstants;

    private Label lDatePremiereVisite;

    private Label lDateDerniereVisite;

    private Label lNbVisites;

    private Label lDateModificationOptions;

    private FlexTable tableSynthese;

    /**
     * Constructeur.
     */
    public BlocSyntheseEspaceAdherentViewImpl() {
        super();
        viewConstants = (BlocSyntheseEspaceAdherentViewImplConstants) GWT.create(BlocSyntheseEspaceAdherentViewImplConstants.class);
        initWidget(construireBlocSyntheseEspaceAdherent());
    }

    private Widget construireBlocSyntheseEspaceAdherent() {
        final Label lDatePremiereVisiteTitle = new Label(viewConstants.labelDatePremiereVisite(), false);
        final Label lDateDerniereVisiteTitle = new Label(viewConstants.labelDateDerniereVisite(), false);
        final Label lNbVisitesTitle = new Label(viewConstants.labelNbVisites(), false);
        lDatePremiereVisite = new Label();
        lDateDerniereVisite = new Label();
        lNbVisites = new Label();
        lDateModificationOptions = new Label();

        tableSynthese = new FlexTable();
        tableSynthese.setWidth(ComposantEspaceClientConstants.POURCENT_100);
        tableSynthese.setWidget(0, 0, lDatePremiereVisiteTitle);
        tableSynthese.setWidget(0, 1, lDatePremiereVisite);
        tableSynthese.setWidget(0, 2, lDateDerniereVisiteTitle);
        tableSynthese.setWidget(0, 3, lDateDerniereVisite);
        tableSynthese.setWidget(0, 4, lNbVisitesTitle);
        tableSynthese.setWidget(0, 5, lNbVisites);
        tableSynthese.setCellSpacing(3);
        tableSynthese.getColumnFormatter().setWidth(0, BlocSyntheseEspaceAdherentViewImplConstants.LARGEUR_COL_LIBELLE_LARGE);
        tableSynthese.getColumnFormatter().setWidth(1, BlocSyntheseEspaceAdherentViewImplConstants.LARGEUR_COL_CHAMP);
        tableSynthese.getColumnFormatter().setWidth(2, BlocSyntheseEspaceAdherentViewImplConstants.LARGEUR_COL_LIBELLE);
        tableSynthese.getColumnFormatter().setWidth(3, BlocSyntheseEspaceAdherentViewImplConstants.LARGEUR_COL_CHAMP);
        tableSynthese.getColumnFormatter().setWidth(4, BlocSyntheseEspaceAdherentViewImplConstants.LARGEUR_COL_LIBELLE);
        tableSynthese.getColumnFormatter().setWidth(5, BlocSyntheseEspaceAdherentViewImplConstants.LARGEUR_COL_CHAMP_PETIT);

        final CaptionPanel cpSynthese = new CaptionPanel(viewConstants.captionSynthese());
        cpSynthese.add(tableSynthese);
        return cpSynthese;
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
    public HasText getDateDerniereVisite() {
        return lDateDerniereVisite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getDateModificationOptions() {
        return lDateModificationOptions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getDatePremiereVisite() {
        return lDatePremiereVisite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getNbVisites() {
        return lNbVisites;
    }

    @Override
    public void ajouterDateModifiationOptions() {
        final Label lDateModificationOptionsTitle = new Label(viewConstants.labelDateModificationOptions(), false);
        tableSynthese.setWidget(1, 0, lDateModificationOptionsTitle);
        tableSynthese.setWidget(1, 1, lDateModificationOptions);
    }

}
