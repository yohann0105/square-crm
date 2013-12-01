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
package com.square.client.gwt.client.composant;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.list.ListItemWidget;
import com.square.client.gwt.client.composant.list.UnorderedListWidget;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.composants.model.SousRapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionHandler;

/**
 * Panel permettant d'afficher les erreurs d'intégrité.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
@SuppressWarnings("unchecked")
public class PanelErreursIntegrite extends Composite implements ControleIntegriteExceptionHandler, ChangeHandler, ValueChangeHandler, SelectionHandler {

    private VerticalPanel conteneur;

    /**
     * Constructeur.
     */
    public PanelErreursIntegrite() {
        conteneur = new VerticalPanel();
        this.initWidget(conteneur);
        this.addStyleName(SquareResources.INSTANCE.css().panelErreursIntegrite());
        // On cache le panel par défaut
        this.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onErreur(ControleIntegriteExceptionEvent event) {
        conteneur.clear();
        final RapportModel rapport = event.getRapport();
        final String msgErreurGeneral = rapport.getMessageGenerale();
        if (Boolean.TRUE.equals(rapport.getInfo())) {
            // Le rapport contient des erreurs
            if (msgErreurGeneral != null && !"".equals(msgErreurGeneral.trim())) {
                // On affiche le message d'erreur général
                conteneur.add(new Label(msgErreurGeneral));
            }
            if (!rapport.getRapports().isEmpty()) {
                // Initialisation liste non ordonnée
                final UnorderedListWidget ul = new UnorderedListWidget();
                for (SousRapportModel ssRapport : rapport.getRapports().values()) {
                    if (Boolean.TRUE.equals(ssRapport.getErreur())) {
                        // On ajoute chaque message d'erreur dans la liste
                        final ListItemWidget li = new ListItemWidget(ssRapport.getMessage());
                        ul.add(li);
                    }
                }
                conteneur.add(ul);
            }
            // On affiche le widget
            this.setVisible(true);
        } else {
            // Aucune erreur, on cache widget
            this.setVisible(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onChange(ChangeEvent event) {
        this.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onValueChange(ValueChangeEvent event) {
        this.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSelection(SelectionEvent event) {
        this.setVisible(false);
    }

}
