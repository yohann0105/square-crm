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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.contrat.square.client.model.AjustementTarifModel;
import com.square.composant.contrat.square.client.presenter.ContratContenuPresenter.ContratGarantieAjustementView;

/**
 * Implémentation de la vue d'un ajustement.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratGarantieAjustementViewImpl implements ContratGarantieAjustementView {

    private NumberFormat numberFormat;

    /** Label de la référence. */
    private Label labelReference;

    /** Label du libellé. */
    private Label labelLibelle;

    /** Label du tarif. */
    private Label labelTarif;

    /** Label de la date de début. */
    private Label labelDateDebut;

    /** Label de la date de fin. */
    private Label labelDateFin;

    /**
     * Constructeur.
     * @param ajustement ajustement
     */
    public ContratGarantieAjustementViewImpl(AjustementTarifModel ajustement) {
    	ContratGarantieViewImplConstants constants = GWT.create(ContratGarantieViewImplConstants.class);
    	numberFormat = NumberFormat.getFormat("#,##0.00 " + constants.symboleMonnaie());
        labelReference = new Label(ajustement.getReference());
        labelLibelle = new Label(ajustement.getNature());
        labelTarif = new Label(numberFormat.format(ajustement.getMontant()));
        labelDateDebut = new Label(ajustement.getDateDebut());
        labelDateFin = new Label(ajustement.getDateFin());
    }

    @Override
    public Label getLabelDateDebut() {
        return labelDateDebut;
    }

    @Override
    public Label getLabelDateFin() {
        return labelDateFin;
    }

    @Override
    public Label getLabelLibelle() {
        return labelLibelle;
    }

    @Override
    public Label getLabelReference() {
        return labelReference;
    }

    @Override
    public Label getLabelTarif() {
        return labelTarif;
    }

    @Override
    public Widget asWidget() {
        return null;
    }

}
