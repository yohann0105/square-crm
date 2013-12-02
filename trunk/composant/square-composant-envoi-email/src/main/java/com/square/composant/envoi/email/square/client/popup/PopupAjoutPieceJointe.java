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
package com.square.composant.envoi.email.square.client.popup;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.envoi.email.square.client.composant.FichierUpload;
import com.square.composant.envoi.email.square.client.constantes.ComposantEnvoiEmailConstants;
import com.square.composant.envoi.email.square.client.presenter.ComposantEnvoiEmailPresenter;

/**
 * Popup permettant d'ajouter un fichier joint au mail.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupAjoutPieceJointe extends Popup {

    /** Constantes de la popup. */
    private static PopupAjoutPieceJointeConstants popupConstants = GWT.create(PopupAjoutPieceJointeConstants.class);

    /** Panneau upload. */
    private HorizontalPanel panneauUpload;

    /** Composant d'upload. */
    private FichierUpload fichierUpload;

    /** Bouton d'ajout. */
    private DecoratedButton btnAjouter;

    /** Bouton d'annulation. */
    private DecoratedButton btnAnnuler;

    /** Bus d'évènements. */
    private HandlerManager eventBus;

    private FocusPanel focusPopupPanel;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènements.
     */
    public PopupAjoutPieceJointe(HandlerManager eventBus) {
        super(popupConstants.titrePopup(), false, false, true);
        this.eventBus = eventBus;
        initPopup();
    }

    /** Initialisation de la popup. */
    private void initPopup() {
        focusPopupPanel = new FocusPanel();
        focusPopupPanel.setWidth(ComposantEnvoiEmailConstants.CENT_POURCENT);
        focusPopupPanel.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    lancerUpload();
                } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide();
                }
            }
        });

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth(ComposantEnvoiEmailConstants.CENT_POURCENT);

        // Composant d'upload
        panneauUpload = new HorizontalPanel();
        fichierUpload = new FichierUpload(eventBus);
        panneauUpload.add(fichierUpload);
        conteneur.add(panneauUpload);
        conteneur.setCellHorizontalAlignment(focusPopupPanel, HasHorizontalAlignment.ALIGN_CENTER);

        // Panneau des boutons
        final HorizontalPanel panneauBoutons = new HorizontalPanel();
        panneauBoutons.setSpacing(5);
        // Bouton d'ajout
        btnAjouter = new DecoratedButton(popupConstants.btnAjouter());
        btnAjouter.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                lancerUpload();
            }
        });
        panneauBoutons.add(btnAjouter);
        // Bouton d'annulation
        btnAnnuler = new DecoratedButton(popupConstants.btnAnnuler());
        btnAnnuler.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();
            }
        });
        panneauBoutons.add(btnAnnuler);
        conteneur.add(panneauBoutons);
        conteneur.setCellHorizontalAlignment(panneauBoutons, HasHorizontalAlignment.ALIGN_CENTER);

        focusPopupPanel.setWidget(conteneur);
        this.setWidget(focusPopupPanel);
        this.addStyleName(ComposantEnvoiEmailPresenter.RESSOURCES.css().popupAjoutPieceJointe());
    }

    /** Affiche la popup d'ajout de fichier joint. */
    public void afficherPopupAjoutFichierJoint() {
        // Réinitialisation du composant d'upload
        panneauUpload.remove(fichierUpload);
        fichierUpload = new FichierUpload(eventBus);
        panneauUpload.add(fichierUpload);
        this.show();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                // On initialise le focus
                btnAnnuler.setFocus(true);
            }
        });
    }

    /** Lance l'upload de la pièce jointe. */
    private void lancerUpload() {
        // Vérification qu'un fichier est indiqué
        if (fichierUpload.isFichierSelectionne()) {
            // Soumission du formulaire
            fichierUpload.submit();
        } else {
            final ErrorPopupConfiguration errorPopupConfiguration = new ErrorPopupConfiguration(popupConstants.selectionnerFichier());
            ErrorPopup.afficher(errorPopupConfiguration);
        }
    }
}
