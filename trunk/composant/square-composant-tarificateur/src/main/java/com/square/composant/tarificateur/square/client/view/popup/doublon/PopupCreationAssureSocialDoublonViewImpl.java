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
package com.square.composant.tarificateur.square.client.view.popup.doublon;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.header.HeaderFlexTable;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.doublons.PersonneDoublonModel;
import com.square.composant.tarificateur.square.client.presenter.popup.doublon.PopupCreationAssureSocialDoublonPresenter.PopupCreationAssureSocialDoublonView;

/**
 * Vue de la popup indiquant la possibilité de doublon lors de la création d'une personne.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupCreationAssureSocialDoublonViewImpl extends Popup implements PopupCreationAssureSocialDoublonView {

    /** View constants. */
    private static PopupCreationAssureSocialDoublonViewImplConstants viewConstants = GWT.create(PopupCreationAssureSocialDoublonViewImplConstants.class);

    /** Contenu de la popup. */
    private VerticalPanel pContenu;

    /** Bouton pour créer une nouvelle personne. */
    private DecoratedButton btnCreer;

    /** Bouton pour rattacher à une personne sélectionnée. */
    private DecoratedButton btnRattacher;

    private DecoratedButton btnAnnuler;

    /** Pour récupérer les évènements sur le tableau dans le Presenter. */
    private HandlerManager remotePagingHandlerManager;

    /** Identifiant de la personne sélectionnée pour le rattachement. */
    private Long idPersonneSelectionnee;

    private List<PersonneDoublonModel> listeDoublons;
    private String nom;
    private String prenom;

    /**
     * Constructeur.
     * @param nom le nom de la personne.
     * @param prenom le prénom de la personne.
     * @param listeDoublons la liste des doublons.
     */
    public PopupCreationAssureSocialDoublonViewImpl(String nom, String prenom, List<PersonneDoublonModel> listeDoublons) {
        super(viewConstants.titrePopup(), false, false, true);
        this.prenom = prenom;
        this.nom = nom;
        this.listeDoublons = listeDoublons;

        pContenu = new VerticalPanel();
        pContenu.setWidth(PopupCreationAssureSocialDoublonViewImplConstants.LARGEUR_POPUP);
        pContenu.setSpacing(10);

        construire();

        this.setWidget(pContenu, 0);
        this.addStyleName(ComposantTarificateur.RESOURCES.css().popupCreationPersonneDoublon());
    }

    private void construire() {
        final VerticalPanel conteneurTexte = new VerticalPanel();
        conteneurTexte.setSpacing(3);
        conteneurTexte.setWidth(ComposantTarificateurConstants.POURCENT_100);
        final Label lPhraseEnTete = new Label(nom + " " + prenom  + " " + viewConstants.finPhraseEnTete());
        final Label lVerification = new Label(viewConstants.phraseVerifier());
        conteneurTexte.add(lPhraseEnTete);
        conteneurTexte.setCellHorizontalAlignment(lPhraseEnTete, HasAlignment.ALIGN_CENTER);
        conteneurTexte.add(lVerification);
        conteneurTexte.setCellHorizontalAlignment(lVerification, HasAlignment.ALIGN_CENTER);
        pContenu.add(conteneurTexte);
        pContenu.setCellHorizontalAlignment(conteneurTexte, HasAlignment.ALIGN_CENTER);

        /** Construit le tableau de recherche. */
        construireTableauRecherche(listeDoublons);

        /** Construit la barre de boutons. */
        construireBarreBoutons();
    }

    @Override
    public void rafraichir(String newNom, String newPrenom, List<PersonneDoublonModel> newListeDoublons) {
        this.prenom = newPrenom;
        this.nom = newNom;
        this.listeDoublons = newListeDoublons;
        pContenu.clear();
        construire();
    }

    /**
     * Construit le tableau de recherche.
     * @param listeDoublons la liste des doublons.
     */
    private void construireTableauRecherche(List<PersonneDoublonModel> newListeDoublons) {
        final List<CheckBox> listeCBSelection = new ArrayList<CheckBox>();
        final HeaderFlexTable<PersonneDoublonModel> ftDoublons = new HeaderFlexTable<PersonneDoublonModel>() {
            @Override
            public Widget[] setHeader() {
                return new Widget[]{
                    new Label(viewConstants.enTeteColonneNom()),
                    new Label(viewConstants.enTeteColonnePrenom()),
                    new Label(viewConstants.enTeteColonneDateNaissance()),
                    new Label(viewConstants.enTeteColonneAdresse()),
                    new Label(viewConstants.enTeteColonneCodePostal()),
                    new Label(viewConstants.enTeteColonneVille()),
                    new Label(viewConstants.enTeteColonneCompoFamiliale()),
                    new Label(viewConstants.enTeteSelection())
                };
            }

            @Override
            public void setRow(final PersonneDoublonModel doublon) {
                setWidget(0, new Label(doublon.getNom()));
                setWidget(1, new Label(doublon.getPrenom()));
                setWidget(2, new Label(doublon.getDateNaissance()));
                setWidget(3, new Label(doublon.getAdresse()));
                setWidget(4, new Label(doublon.getCodePostal()));
                setWidget(5, new Label(doublon.getCommune()));
                setWidget(6, new Label(doublon.getCompoFamiliale()));
                final CheckBox cb = new CheckBox();
                cb.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        if (cb.getValue()) {
                            for (CheckBox checkBox : listeCBSelection) {
                                if (!cb.equals(checkBox)) {
                                    checkBox.setEnabled(false);
                                }
                            }
                            idPersonneSelectionnee = doublon.getId();
                            btnRattacher.setEnabled(true);
                        }
                        else {
                            for (CheckBox checkBox : listeCBSelection) {
                                checkBox.setEnabled(true);
                            }
                            idPersonneSelectionnee = null;
                            btnRattacher.setEnabled(false);
                        }
                    }
                });
                listeCBSelection.add(cb);
                setWidget(7, cb);
                setCellHorizontalAlignment(7, HasAlignment.ALIGN_CENTER);
            }
        };
        ftDoublons.setWidth(ComposantTarificateurConstants.POURCENT_100);
        ftDoublons.setListeObjets(newListeDoublons);

        final ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setHeight("250px");
        scrollPanel.add(ftDoublons);
        pContenu.add(scrollPanel);
    }

    /** Construit la barre de boutons. */
    private void construireBarreBoutons() {
        btnRattacher = new DecoratedButton(viewConstants.btnRattacherPersonneExistante());
        btnRattacher.setEnabled(false);
        btnCreer = new DecoratedButton(viewConstants.btnCreerNouvellePersonne());
        btnAnnuler = new DecoratedButton(viewConstants.btnAnnuler());

        final HorizontalPanel conteneurBoutons = new HorizontalPanel();
        conteneurBoutons.setSpacing(5);
        conteneurBoutons.add(btnRattacher);
        conteneurBoutons.add(btnCreer);
        conteneurBoutons.add(btnAnnuler);

        pContenu.add(conteneurBoutons);
        pContenu.setCellHorizontalAlignment(conteneurBoutons, HasAlignment.ALIGN_CENTER);
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public HasClickHandlers getBtnCreer() {
        return btnCreer;
    }

    @Override
    public HasClickHandlers getBtnRattacher() {
        return btnRattacher;
    }

    @Override
    public HandlerManager getRemotePagingHandlerManager() {
        return remotePagingHandlerManager;
    }

    @Override
    public Long getIdPersonneSelectionnee() {
        return idPersonneSelectionnee;
    }

    @Override
    public void afficherPopup() {
        this.show();
    }

    @Override
    public void cacherPopup() {
        this.hide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasClickHandlers getBtnAnnuler() {
        return btnAnnuler;
    }
}
