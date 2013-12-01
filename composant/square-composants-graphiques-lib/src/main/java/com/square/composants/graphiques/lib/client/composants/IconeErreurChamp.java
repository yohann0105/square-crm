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
package com.square.composants.graphiques.lib.client.composants;

import org.scub.foundation.framework.gwt.module.client.util.composants.disclosurePanel.CustomDisclosurePanel;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composants.graphiques.lib.client.bundle.SquareLibErreurChampResource;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.composants.model.SousRapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionHandler;

/**
 * Icone d'affichage d'une erreur pour un champ.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@SuppressWarnings("unchecked")
public class IconeErreurChamp extends Composite implements ChangeHandler, ValueChangeHandler, SelectionHandler, ControleIntegriteExceptionHandler {

    private String nomChamp;

    private Image imgIcone;

    /**
     * Constructeur privé.
     * @param nomChamp le nom du champ concerné par cette icone
     * @param composant composant.
     */
    public IconeErreurChamp(String nomChamp, HasHandlers composant) {
        this.nomChamp = nomChamp;

        imgIcone = new Image(SquareLibErreurChampResource.INSTANCE.erreurChamp());
        hide();

        final SimplePanel conteneur = new SimplePanel();
        conteneur.setPixelSize(imgIcone.getWidth(), imgIcone.getHeight());
        conteneur.add(imgIcone);

        // on ajoute un handler au changement de valeur du composant pour masquer l'icone
        if (composant instanceof HasChangeHandlers) {
            ((HasChangeHandlers) composant).addChangeHandler(this);
        }
        if (composant instanceof HasValueChangeHandlers) {
            ((HasValueChangeHandlers) composant).addValueChangeHandler(this);
        }
        if (composant instanceof HasSelectionHandlers) {
            ((HasSelectionHandlers) composant).addSelectionHandler(this);
        }

        this.initWidget(conteneur);
    }

    @Override
    public void onErreur(ControleIntegriteExceptionEvent event) {
        final RapportModel rapport = event.getRapport();
        final SousRapportModel sousRapport = rapport.recupererElement(nomChamp);
        if (sousRapport != null && sousRapport.getErreur().booleanValue()) {
            imgIcone.setTitle(sousRapport.getMessage());
            imgIcone.setVisible(true);
            // Si le champ en erreur appartient à un bloc dépliable fermé on ouvre le bloc
            Widget parent = this.getParent();
            while (parent != null) {
            	if (parent instanceof CustomDisclosurePanel && !((CustomDisclosurePanel) parent).isOpen()) {
            		 ((CustomDisclosurePanel) parent).setOpen(true);
            	}
            	parent = parent.getParent();
            }
        } else {
            hide();
        }
    }

    @Override
    public void onChange(ChangeEvent event) {
        hide();
    }

    @Override
    public void onValueChange(ValueChangeEvent event) {
        hide();
    }

    @Override
    public void onSelection(SelectionEvent event) {
        hide();
    }

    /** Masque l'icone. */
    public void hide() {
        imgIcone.setVisible(false);
        imgIcone.setTitle("");
    }

    /**
     * Indique si l'icône est en erreur.
     * @return si l'icône est en erreur
     */
    public boolean isErreur() {
    	return imgIcone.isVisible();
    }
}
