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
package com.square.client.gwt.client.view.personne.coordonnees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.exception.TechnicalExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasListGridCellClickHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.ListGrid;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.bundle.theme.smatis.SmatisResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.SuggestPaysEvent;
import com.square.client.gwt.client.event.SuggestPaysEventHandler;
import com.square.client.gwt.client.model.PaysSimpleModel;
import com.square.client.gwt.client.presenter.personne.PopupSelectionPaysPresenter.PopupSelectionPaysView;

/**
 * Popup de sélection d'un pays.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupSelectionPaysViewImpl extends Popup implements PopupSelectionPaysView {

    /** Constantes de la popup. */
    private static PopupSelectionPaysViewImplConstants popupConstants =
        (PopupSelectionPaysViewImplConstants) GWT.create(PopupSelectionPaysViewImplConstants.class);

    /** Text Box de saisie. **/
    private DecoratedTextBox tbSaisie;

    /** Tableau de résultat. **/
    private ListGrid result;

    /** Bouton annuler. **/
    private DecoratedButton btnAnnuler;

    /** Propriété des pays. **/
    private ListBoxPaysProperties properties;

    /** Gestions du tableau. */
    private Map<Integer, PaysSimpleModel> mappSuggestPays;

    /** Pays sélectionné. */
    private PaysSimpleModel paysSelectionne;

    /**
     * Constructeur.
     * @param properties les propriétés de la popup.
     */
    public PopupSelectionPaysViewImpl(ListBoxPaysProperties properties) {
        super(popupConstants.titrePopup(), false, true, true);
        this.properties = properties;
        this.mappSuggestPays = new HashMap<Integer, PaysSimpleModel>();

        // Construction du contenu de la popup
        final VerticalPanel contenuPopup = new VerticalPanel();
        contenuPopup.setSpacing(5);

        // Construction de la zone de recherche
        final HorizontalPanel pRecherche = new HorizontalPanel();
        pRecherche.setSpacing(5);
        final Label lRechercher = new Label(popupConstants.rechercher());
        tbSaisie = new DecoratedTextBox();
        pRecherche.add(lRechercher);
        pRecherche.setCellVerticalAlignment(lRechercher, HasAlignment.ALIGN_MIDDLE);
        pRecherche.add(tbSaisie);
        contenuPopup.add(pRecherche);

        // Construction du tableau des résultats
        result = new ListGrid();
        contenuPopup.add(result);

        // Ajout du bouton d'annulation
        btnAnnuler = new DecoratedButton(popupConstants.btnAnnuler());
        contenuPopup.add(btnAnnuler);
        contenuPopup.setCellHorizontalAlignment(btnAnnuler, HasAlignment.ALIGN_CENTER);

        this.setWidget(contenuPopup);
        this.addStyleName(SquareResources.INSTANCE.css().popupSelectionPays());
    }

    @Override
    public void show() {
        super.show();
        paysSelectionne = null;
        tbSaisie.setValue("");
        result.clear();
        mappSuggestPays.clear();
    }

    /**
     * Interface de la définition des propriétés des pays.
     */
    public interface ListBoxPaysProperties extends SuggestListBoxProperties<PaysSimpleModel> {
    }

    /**
     * Retourne l'identifiant du pays sélectionné.
     * @return l'identifiant du pays sélectionné.
     */
    public Long getIdPaysSelectionne() {
        if (paysSelectionne != null) {
            return paysSelectionne.getId();
        } else {
            return null;
        }
    }

    @Override
    public void fireSuggestEvent() {
        final AsyncCallback<List<PaysSimpleModel>> asyncCallback = new AsyncCallback<List<PaysSimpleModel>>() {
            @Override
            public void onSuccess(List<PaysSimpleModel> results) {
                chargerPays(results);
            }

            @Override
            public void onFailure(Throwable caught) {
                throw new TechnicalExceptionGwt(caught);
            }
        };
        final SuggestPaysEvent event = new SuggestPaysEvent(asyncCallback, tbSaisie.getValue());
        fireEvent(event);
    }

    /**
     * Charge la liste des pays.
     * @param listePays la liste des pays à charger.
     */
    private void chargerPays(List<PaysSimpleModel> listePays) {
        result.clear();
        mappSuggestPays.clear();
        for (int column = 0; column < properties.getResultColumnsRenderer().length; column++) {
            result.setWidget(0, column, new Label(properties.getResultColumnsRenderer()[column]));
        }
        for (int index = 0, row = 1; index < listePays.size(); row++, index++) {
            mappSuggestPays.put(row, listePays.get(index));
            final String[] columns = properties.getResultRowsRenderer(listePays.get(index));
            for (int column = 0; column < columns.length; column++) {
                if (column != 3) {
                    final SimplePanel panel = new SimplePanel();
                    panel.setWidth(AppControllerConstants.POURCENT_100);
                    panel.setHeight("24px");
                    final VerticalPanel pContenuCell = new VerticalPanel();
                    pContenuCell.setHeight("100%");
                    final HTML libelle = new HTML(columns[column]);
                    pContenuCell.add(libelle);
                    pContenuCell.setCellVerticalAlignment(libelle, HasAlignment.ALIGN_MIDDLE);
                    panel.add(pContenuCell);
                    this.result.setWidget(row, column, panel);
                } else {
                    final SimplePanel panel = new SimplePanel();
                    final Image flag = new Image();
                    flag.setUrl(PopupSelectionPaysViewImplConstants.DEBUT_URL_IMAGE_PAYS + columns[column]
                        + PopupSelectionPaysViewImplConstants.FIN_URL_IMAGE_PAYS);
                    flag.addStyleName(SmatisResources.INSTANCE.css().imageDrapeauRecherche());
                    panel.add(flag);
                    this.result.setWidget(row, column, panel);
                    this.result.getCellFormatter().setHorizontalAlignment(row, column, HasAlignment.ALIGN_CENTER);
                }
            }
        }
    }

    @Override
    public HandlerRegistration addSuggestHandler(SuggestPaysEventHandler handler) {
        return addHandler(handler, SuggestPaysEvent.TYPE);
    }

    @Override
    public HasListGridCellClickHandler getResult() {
        return result;
    }

    @Override
    public HasClickHandlers getBtnAnnuler() {
        return btnAnnuler;
    }

    @Override
    public PaysSimpleModel getPaysSelectionne(int row) {
        paysSelectionne = mappSuggestPays.get(row);
        return paysSelectionne;
    }

    @Override
    public void hidePopup() {
        hide();
    }

    @Override
    public void showPopup() {
        show();
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public HasKeyUpHandlers getTbSaisie() {
        return tbSaisie;
    }
}
