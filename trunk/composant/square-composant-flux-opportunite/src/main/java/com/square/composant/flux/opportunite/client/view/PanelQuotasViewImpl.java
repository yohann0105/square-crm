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
package com.square.composant.flux.opportunite.client.view;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.flux.opportunite.client.content.i18n.ComposantFluxOpportuniteConstants;
import com.square.composant.flux.opportunite.client.model.QuotaModel;
import com.square.composant.flux.opportunite.client.presenter.PanelQuotasPresenter;
import com.square.composant.flux.opportunite.client.presenter.PanelQuotasPresenter.PanelQuotasView;

/**
 * Vue du panel des quotas.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PanelQuotasViewImpl extends Composite implements PanelQuotasView {

    /** Constantes de la vue. */
    private PanelQuotasViewImplConstants viewConstants;

    /** Panel contenant les informations des quotas. */
    private CaptionPanel captionPanelInformation;

    /** FlexTable contenant les quotats pour les différents jours. */
    private FlexTable ftQuotasJours;

    /**
     * Constructeur.
     */
    public PanelQuotasViewImpl() {
        super();
        viewConstants = (PanelQuotasViewImplConstants) GWT.create(PanelQuotasViewImplConstants.class);

        this.initWidget(construirePanelQuotas());
        this.addStyleName(PanelQuotasPresenter.RESOURCES.css().panelQuotas());
    }

    /**
     * Construit le contenu du panel des quotas.
     * @return le widget.
     */
    private Widget construirePanelQuotas() {
        final VerticalPanel contenu = new VerticalPanel();
        contenu.setWidth(ComposantFluxOpportuniteConstants.POURCENT_100);
        contenu.setSpacing(5);
        captionPanelInformation = new CaptionPanel(viewConstants.titrePanelQuotas());
        captionPanelInformation.setVisible(false);
        captionPanelInformation.add(contenu);

        final Label lNbOpportunitesJours = new Label(viewConstants.labelNbOpportunitesJours());
        contenu.add(lNbOpportunitesJours);

        ftQuotasJours = new FlexTable();
        ftQuotasJours.setCellSpacing(10);
        contenu.add(ftQuotasJours);
        contenu.setCellHorizontalAlignment(ftQuotasJours, HasAlignment.ALIGN_CENTER);

        return captionPanelInformation;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void chargerQuotas(List<QuotaModel> listeQuotas) {
        // Nettoyage de la FlexTable
        while (ftQuotasJours.getRowCount() > 0) {
            ftQuotasJours.removeRow(0);
        }

        if (listeQuotas != null && listeQuotas .size() > 0) {
            int colonne = 0;
            for (QuotaModel quota : listeQuotas) {
                if (quota.getJourQuota() != null) {
                    final Label lJour = new Label(quota.getJourQuota().getLibelle());
                    ftQuotasJours.setWidget(0, colonne, lJour);
                    ftQuotasJours.getCellFormatter().setHorizontalAlignment(0, colonne, HasAlignment.ALIGN_CENTER);
                }
                final DecoratedTextBox tbQuota = new DecoratedTextBox();
                String valQuota = "0";
                if (quota.getValue() != null) {
                    valQuota = quota.getValue().toString();
                }
                tbQuota.setValue(valQuota);
                tbQuota.setEnabled(false);
                ftQuotasJours.setWidget(1, colonne, tbQuota);
                colonne++;
            }
            captionPanelInformation.setVisible(true);
        }
    }

    @Override
    public void cacherPanelQuotas() {
        captionPanelInformation.setVisible(false);
    }

}
