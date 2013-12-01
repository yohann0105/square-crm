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
package com.square.composant.contrat.square.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.contrat.square.client.bundle.ComposantContratResources;
import com.square.composant.contrat.square.client.model.GarantieModel;
import com.square.composant.contrat.square.client.presenter.ContratContenuPresenter.ContratGarantieView;

/**
 * Implémentation de la vue de la garantie d'un contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratGarantieViewImpl extends Composite implements ContratGarantieView {

    private NumberFormat numberFormat;

    /** View constants. */
    private ContratGarantieViewImplConstants viewConstants;

    /** Conteneur global. */
    private FlexTable conteneurGlobal;

    /**
     * Constructeur.
     * @param resources les ressources
     * @param garantie la garantie
     * @param style le style
     */
    public ContratGarantieViewImpl(ComposantContratResources resources, GarantieModel garantie, String style) {
        this.viewConstants = (ContratGarantieViewImplConstants) GWT.create(ContratGarantieViewImplConstants.class);
        numberFormat = NumberFormat.getFormat("#,##0.00 " + viewConstants.symboleMonnaie());
        construireBlocContenu(garantie);
        final CaptionPanel captionPanel = new CaptionPanel(garantie.getLibelle());
        captionPanel.setStylePrimaryName(resources.css().panelGarantieContrat());
        captionPanel.add(conteneurGlobal);
        initWidget(captionPanel);
        if (style != null) {
            captionPanel.addStyleName(style);
        }
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    /** Construit le contenu. */
    private void construireBlocContenu(GarantieModel garantie) {
        conteneurGlobal = new FlexTable();
        conteneurGlobal.setWidth(ContratsViewImplConstants.POURCENT_100);
        conteneurGlobal.setCellPadding(2);

        // Code tarif
        if (garantie.getCodeTarif() != null) {
            final Label lCodeTarif = new Label(viewConstants.libelleCodeTarif(), false);
            final Label labelCodeTarif = new Label(garantie.getCodeTarif(), false);
            conteneurGlobal.setWidget(0, 0, lCodeTarif);
            conteneurGlobal.setWidget(0, 1, labelCodeTarif);
        }

        // Code génération
        if (garantie.getCodeGeneration() != null) {
            final Label lCodeGeneration = new Label(viewConstants.libelleCodeGeneration(), false);
            final Label labelCodeGeneration = new Label(garantie.getCodeGeneration(), false);
            conteneurGlobal.setWidget(0, 2, lCodeGeneration);
            conteneurGlobal.setWidget(0, 3, labelCodeGeneration);
        }

        // Garantie Gestion
        if (garantie.getLibelleGarantieGestion() != null) {
            final Label lGarantieGestion = new Label(viewConstants.libelleGarantieGestion(), false);
            final Label labelGarantieGestion = new Label(garantie.getLibelleGarantieGestion(), false);
            conteneurGlobal.setWidget(0, 4, lGarantieGestion);
            conteneurGlobal.setWidget(0, 5, labelGarantieGestion);
        }

        // Produit Gestion
        if (garantie.getLibelleProduitGestion() != null) {
            final Label lProduitGestion = new Label(viewConstants.libelleProduitGestion(), false);
            final Label labelProduitGestion = new Label(garantie.getLibelleProduitGestion(), false);
            conteneurGlobal.setWidget(0, 6, lProduitGestion);
            conteneurGlobal.setWidget(0, 7, labelProduitGestion);
        }

        // Loi Madelin
        final Label lLoiMadelin = new Label(viewConstants.libelleLoiMadelin(), false);
        final CheckBox cbLoiMadelin = new CheckBox();
        cbLoiMadelin.setEnabled(false);
        cbLoiMadelin.setValue(garantie.getLoiMadelin());
        conteneurGlobal.setWidget(1, 0, lLoiMadelin);
        conteneurGlobal.setWidget(1, 1, cbLoiMadelin);

        // Montant souscrit
        if (garantie.getMontantSouscrit() != null) {
            final Label lMontantSouscrit = new Label(viewConstants.libelleMontantSouscrit(), false);
            final Label labelMontantSouscrit = new Label(numberFormat.format(garantie.getMontantSouscrit()), false);
            conteneurGlobal.setWidget(1, 2, lMontantSouscrit);
            conteneurGlobal.setWidget(1, 3, labelMontantSouscrit);
        }

        conteneurGlobal.getColumnFormatter().setWidth(0, "14%");
        conteneurGlobal.getColumnFormatter().setWidth(1, "8%");
        conteneurGlobal.getColumnFormatter().setWidth(2, "12%");
        conteneurGlobal.getColumnFormatter().setWidth(3, "5%");
        conteneurGlobal.getColumnFormatter().setWidth(4, "12%");
        conteneurGlobal.getColumnFormatter().setWidth(5, "24%");
        conteneurGlobal.getColumnFormatter().setWidth(6, "12%");
        conteneurGlobal.getColumnFormatter().setWidth(7, "13%");
    }

}
