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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.flux.opportunite.client.model.QuotaModel;
import com.square.composant.flux.opportunite.client.presenter.PopupModifQuotasPresenter;
import com.square.composant.flux.opportunite.client.presenter.PopupModifQuotasPresenter.PopupModifQuotasView;
import com.square.composants.graphiques.lib.client.composants.DecoratedTextBoxFormat;

/**
 * Vue de la popup de modification des quotas.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupModifQuotasViewImpl extends Popup implements PopupModifQuotasView {

    /** Constantes de la vue. */
    private static PopupModifQuotasViewImplConstants viewConstants = GWT.create(PopupModifQuotasViewImplConstants.class);

    /** Tableau des quotas. */
    private FlexTable ftQuotas;

    /** Maps des quotas présents. */
    private Map<Long, DecoratedTextBoxFormat> mapQuotas;

    /** Bouton pour enregistrer. */
    private DecoratedButton btnEnregistrer;

    /** Bouton pour annuler. */
    private DecoratedButton btnAnnuler;

    /**
     * Constructeur.
     */
    public PopupModifQuotasViewImpl() {
        super(viewConstants.titrePopup(), false, false, true);

        // Construction du contenu de la popup
        construireContenuPopup();
    }

    /** Construit le contenu de la popup. */
    private void construireContenuPopup() {
        final VerticalPanel contenuPopup = new VerticalPanel();
        contenuPopup.setSpacing(5);

        // Ajout du tableau de saisie des quotas
        final CaptionPanel pNbOpportunites = new CaptionPanel(viewConstants.titrePanelNbOpportunites());
        ftQuotas = new FlexTable();
        pNbOpportunites.add(ftQuotas);
        contenuPopup.add(pNbOpportunites);

        // Ajout de la barre de boutons
        final HorizontalPanel pBarreBoutons = construireBarreBoutons();
        contenuPopup.add(pBarreBoutons);
        contenuPopup.setCellHorizontalAlignment(pBarreBoutons, HasAlignment.ALIGN_CENTER);

        this.setWidget(contenuPopup);
        this.addStyleName(PopupModifQuotasPresenter.RESOURCES.css().popupModifQuotas());
    }

    private HorizontalPanel construireBarreBoutons() {
        final HorizontalPanel pBarreBoutons = new HorizontalPanel();
        pBarreBoutons.setSpacing(5);
        btnEnregistrer = new DecoratedButton(viewConstants.btnEnregistrer());
        btnAnnuler = new DecoratedButton(viewConstants.btnAnnuler());
        pBarreBoutons.add(btnEnregistrer);
        pBarreBoutons.add(btnAnnuler);
        return pBarreBoutons;
    }

    /**
     * Initialise le contenu de la popup.
     * @param listeJours les jours des quotas.
     */
    private void initJours(List<IdentifiantLibelleGwt> listeJours) {
        // Nettoyage du contenu du tableau
        while (ftQuotas.getRowCount() > 0) {
            ftQuotas.removeRow(0);
        }
        mapQuotas = new HashMap<Long, DecoratedTextBoxFormat>();

        // Remplissage du tableau
        int colonne = 0;
        for (IdentifiantLibelleGwt jour : listeJours) {
            final Label lJour = new Label(jour.getLibelle());
            ftQuotas.setWidget(0, colonne, lJour);
            ftQuotas.getCellFormatter().setHorizontalAlignment(0, colonne, HasAlignment.ALIGN_CENTER);

            final DecoratedTextBoxFormat dtbQuota = new DecoratedTextBoxFormat("NNNNNN");
            ftQuotas.setWidget(1, colonne, dtbQuota);
            mapQuotas.put(jour.getIdentifiant(), dtbQuota);

            colonne++;
        }
    }

    @Override
    public void hidePopup() {
        this.hide();
    }

    @Override
    public void showPopup(List<IdentifiantLibelleGwt> listeJours) {
        initJours(listeJours);
        this.show();
    }

    @Override
    public Widget asWidget() {
        return this;
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
    public HasClickHandlers getBtnAnnuler() {
        return btnAnnuler;
    }

    @Override
    public HasClickHandlers getBtnEnregistrer() {
        return btnEnregistrer;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public List<QuotaModel> getListeQuotas() {
        final List<QuotaModel> listeQuotas = new ArrayList<QuotaModel>();
        for (Entry<Long, DecoratedTextBoxFormat> quota : mapQuotas.entrySet()) {
            final QuotaModel quotaModel = new QuotaModel();
            final IdentifiantLibelleGwt jourQuota = new IdentifiantLibelleGwt(quota.getKey());
            quotaModel.setJourQuota(jourQuota);
            if (quota.getValue() != null && quota.getValue().getValue() != null && !"".equals(quota.getValue().getValue())) {
                quotaModel.setValue(Integer.valueOf(quota.getValue().getValue()));
            }
            else {
                quotaModel.setValue(0);
            }
            listeQuotas.add(quotaModel);
        }
        return listeQuotas;
    }
}
