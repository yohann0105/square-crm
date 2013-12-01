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
package com.square.composant.aide.gwt.client;

import org.scub.foundation.framework.gwt.module.client.util.composants.richtextarea.RichTextPanel;
import org.scub.foundation.framework.gwt.module.client.util.composants.richtextarea.RichTextToolbar;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.aide.gwt.client.content.i18n.AideComposantMessages;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.model.AideModel;

/**
 * Classe de composant d'aide en Ligne.
 * @author mohamedAli - SCUB
 */
public class AideComposant extends Composite implements HasDemandeAideEventHandler, HasUpdateAideEventHandler {

    private Long id;

    private AideImage aideIcone;

    private AideImage aideIconeAdmin;

    private boolean isAdmin;

    private int clientWidth;

    private HTML aideText;

    private AideComposantMessages aideConstants;

    private Popup updatePopup;

    private RichTextArea aideTextArea;

    private SimplePanel panelIcone;

    private Popup panelAide;

    private static final int POPUP_POSITION = 550;

    /**
     * constructeur.
     * @param id id de composant.
     * @param isAdmin Admin/user
     */
    public AideComposant(final Long id, final boolean isAdmin) {

        // CONSTANTE COMPOSANT
        this.isAdmin = isAdmin;
        this.id = id;
        this.aideConstants = GWT.create(AideComposantMessages.class);
        this.clientWidth = Window.getClientWidth();

        // GESTION POPUP MESSAGE
        aideIcone = new AideImage(AideComposantRessources.INSTANCE.iconeAide());
        final LoadingPopup loading = LoadingPopup.getInstance();
        loading.setWidth("500");
        loading.setHTML(aideConstants.chargementEnCours());
        this.panelAide = new Popup(aideConstants.popupTitle(), true, false, false);
        this.aideText = new HTML();
        this.aideText.setWidth("500px");
        panelAide.setWidget(aideText);

        aideIcone.addContextMenuHandler(new ContextMenuHandler() {

            @Override
            public void onContextMenu(ContextMenuEvent event) {

                if (isAdmin) {
                    // MASQUER POPUP AFFICHAGE
                    event.stopPropagation();
                    panelAide.hide();
                    // CHARGEMENT ET AFFICHAGE POPUP EDITION
                    fireAideEventEdition(aideIcone.getAbsoluteLeft(), aideIcone.getAbsoluteTop(), loading);
                    controleAffichagePopup(aideIcone.getAbsoluteLeft(), aideIcone.getAbsoluteTop(), loading);
                }

            }
        });

        // GESTION POPUP MODE LECTURE
        updatePopup = createUpdatePanel();
        aideIcone.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // MASQUER POPUP AFFICHAGE
                controleAffichagePopup(aideIcone.getAbsoluteLeft(), aideIcone.getAbsoluteTop(), loading);
                fireAideEvent(aideIcone.getAbsoluteLeft(), aideIcone.getAbsoluteTop(), loading);
            }
        });

        // PANEL PRINCIPAL INITIALISATION
        aideIconeAdmin = new AideImage(AideComposantRessources.INSTANCE.iconeSansAide());

        aideIconeAdmin.addContextMenuHandler(new ContextMenuHandler() {

            @Override
            public void onContextMenu(ContextMenuEvent event) {
                event.stopPropagation();
                event.getNativeEvent().stopPropagation();
                if (isAdmin) {
                    // MASQUER POPUP AFFICHAGE
                    panelAide.hide();
                    // CHARGEMENT ET AFFICHAGE POPUP EDITION
                    fireAideEventEdition(aideIconeAdmin.getAbsoluteLeft(), aideIconeAdmin.getAbsoluteTop(), loading);
                    controleAffichagePopup(aideIconeAdmin.getAbsoluteLeft(), aideIconeAdmin.getAbsoluteTop(), loading);
                }
            }
        });

        panelIcone = new SimplePanel();
        panelIcone.add(aideIconeAdmin);
        this.setVisible(false);
        initWidget(panelIcone);

    }

    private void fireAideEventEdition(final int left, final int top, final LoadingPopup loading) {
        final DemandeAideEvent aideEvent = new DemandeAideEvent(new AsyncCallback<AideModel>() {

            @Override
            public void onFailure(Throwable caught) {

                aideTextArea.setHTML(aideConstants.erreurRecuperationMessage() + id + "");
                loading.hide();
                controleAffichagePopup(left, top, updatePopup);
            }

            @Override
            public void onSuccess(AideModel result) {
                aideTextArea.setHTML(result != null && result.getText() != null
                		? result.getText() : aideConstants.erreurAucuneAideAssocieComposant() + id + "");
                loading.hide();
                controleAffichagePopup(left, top, updatePopup);
            }
        }, id);
        fireEvent(aideEvent);
    }

    private void controleAffichagePopup(int left, int top, Popup popup) {
        int leftReal = left;
        if ((left + POPUP_POSITION) > this.clientWidth) {
            leftReal = left - POPUP_POSITION;
        }
        popup.showOnPosition(leftReal + 10, top + 10);
    }

    private void fireAideEvent(final int left, final int top, final LoadingPopup loading) {
        final DemandeAideEvent aideEvent = new DemandeAideEvent(new AsyncCallback<AideModel>() {

            @Override
            public void onFailure(Throwable caught) {
                aideText.setHTML(aideConstants.erreurRecuperationMessage() + id + "");
                loading.hide();
                controleAffichagePopup(left, top, panelAide);
            }

            @Override
            public void onSuccess(AideModel result) {
                aideText.setHTML(result != null && result.getText() != null ? result.getText() : aideConstants.erreurAucuneAideAssocieComposant() + id + "");
                loading.hide();
                controleAffichagePopup(left, top, panelAide);
            }
        }, id);
        fireEvent(aideEvent);
    }

    /**
     * création de popup de modification d'aide.
     * @return une popup de style edition.
     */
    public Popup createUpdatePanel() {
        final Popup popup = new Popup(aideConstants.popupTitle(), false, true, true);
        popup.setWidth("500");
        final VerticalPanel panel = new VerticalPanel();
        aideTextArea = new RichTextArea();

        final RichTextToolbar aideToolbar =
            new RichTextToolbar(aideTextArea, RichTextToolbar.BOLD, RichTextToolbar.ITALIC, RichTextToolbar.UNDERLINE, RichTextToolbar.OL, RichTextToolbar.UL);

        final RichTextPanel updatePopupEditor = new RichTextPanel(aideToolbar, aideTextArea);
        panel.add(updatePopupEditor);

        final Button buttonEnregistrer = new Button(aideConstants.buttonEnregistrerLabel());
        buttonEnregistrer.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                final AsyncCallback<AideModel> asyncCallback = new AsyncCallback<AideModel>() {
                    @Override
                    public void onFailure(final Throwable caught) {
                        ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
                    }

                    @Override
                    public void onSuccess(AideModel result) {
                        if (!"".equals(aideTextArea.getText())) {
                            setVisible(true);
                        }
                        updatePopup.hide();
                    }
                };
                final AideModel aide = new AideModel();
                aide.setIdComposant(id);
                aide.setText(aideTextArea.getHTML());
                fireEvent(new UpdateAideEvent(asyncCallback, aide));
            }
        });

        final Button buttonAnnuler = new Button(aideConstants.buttonQuitterLabel());
        buttonAnnuler.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                updatePopup.hide();
            }
        });
        final HorizontalPanel panelButtons = new HorizontalPanel();
        panelButtons.add(buttonEnregistrer);
        panelButtons.add(buttonAnnuler);
        panel.add(panelButtons);
        popup.setWidget(panel);

        return popup;
    }

    @Override
    public void setVisible(boolean visible) {
        if (!visible) {
            if (isAdmin) {
                panelIcone.clear();
                panelIcone.add(aideIconeAdmin);
            }
            else {
                panelIcone.clear();
            }
        }
        else {
            panelIcone.clear();
            panelIcone.add(aideIcone);
        }
    }

    @Override
    public HandlerRegistration addDemandeAideEventHandler(DemandeAideEventHandler handler) {
        return addHandler(handler, DemandeAideEvent.TYPE);
    }

    @Override
    public HandlerRegistration addUpdateAideEventHandler(UpdateAideEventHandler handler) {
        return addHandler(handler, UpdateAideEvent.TYPE);
    }

    /**
     * Fixer la valeur.
     * @param clientWidth the clientWidth to set
     */
    public void setClientWidth(int clientWidth) {
        this.clientWidth = clientWidth;
    }

    /**
     * récupére l'id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * modifie l'id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
