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
package com.square.client.gwt.client.view.personne.physique.creation;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.header.HeaderFlexTable;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.PopupStateChangeEvent;
import org.scub.foundation.framework.gwt.module.client.util.popup.PopupStateChangeEventHandler;

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
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.PersonneDoublonModel;
import com.square.client.gwt.client.presenter.personne.physique.PopupCreationPersonneDoublonPresenter;

/**
 * Vue de la popup indiquant la possibilité de doublon lors de la création d'une personne.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupCreationPersonneDoublonViewImpl extends Popup implements PopupCreationPersonneDoublonPresenter.PopupCreationPersonneDoublonView {

    /** View constants. */
    private static PopupCreationPersonneDoublonViewImplConstants viewConstants = GWT.create(PopupCreationPersonneDoublonViewImplConstants.class);

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

    /** conteneurTexte. **/
    private VerticalPanel conteneurTexte;

    /** Scroll Panel. **/
    private ScrollPanel scrollPanel;

    /**
     * Constructeur.
     * @param nom le nom de la personne.
     * @param prenom le prénom de la personne.
     * @param isContactPrincipal indique si la personne est un contact principal ou non.
     * @param listeDoublons la liste des doublons.
     */
    public PopupCreationPersonneDoublonViewImpl(String nom, String prenom, boolean isContactPrincipal, List<PersonneDoublonModel> listeDoublons) {
        super(viewConstants.titrePopup(), false, false, true);

        pContenu = new VerticalPanel();
        pContenu.setWidth(PopupCreationPersonneDoublonViewImplConstants.LARGEUR_POPUP);
        pContenu.setSpacing(10);

        scrollPanel = new ScrollPanel();
        scrollPanel.setHeight("250px");
        pContenu.add(scrollPanel);

        this.conteneurTexte = new VerticalPanel();
        pContenu.add(conteneurTexte);

        formerBlocTexte(nom, prenom, isContactPrincipal);

        /** Construit le tableau de recherche. */
        construireTableauRecherche(listeDoublons);

        /** Construit la barre de boutons. */
        construireBarreBoutons();

        this.setWidget(pContenu, 0);
        this.addStyleName(SquareResources.INSTANCE.css().popupCreationPersonneDoublon());
    }
    /**
     * Former le Bloc texte.
     * @param nom
     * @param prenom
     * @param typePersonne
     */
    private void formerBlocTexte(final String nom, final String prenom, final Boolean isContactPrincipal) {
        String typePersonne = "";
        if (isContactPrincipal) {
            typePersonne = viewConstants.statutContactPrincipal();
        } else {
            typePersonne = viewConstants.statutBeneficiaire();
        }

        conteneurTexte.setSpacing(3);
        conteneurTexte.setWidth(AppControllerConstants.POURCENT_100);
        final Label lPhraseEnTete = new Label(nom + " " + prenom + " " + typePersonne + " " + viewConstants.finPhraseEnTete());
        final Label lVerification = new Label(viewConstants.phraseVerifier());
        conteneurTexte.add(lPhraseEnTete);
        conteneurTexte.setCellHorizontalAlignment(lPhraseEnTete, HasAlignment.ALIGN_CENTER);
        conteneurTexte.add(lVerification);
        conteneurTexte.setCellHorizontalAlignment(lVerification, HasAlignment.ALIGN_CENTER);
    }

    /**
     * Construit le tableau de recherche.
     * @param listeDoublons la liste des doublons.
     */
    private void construireTableauRecherche(List<PersonneDoublonModel> listeDoublons) {
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
//        ftDoublons.addStyleName(SquareResources.INSTANCE.css().popupTableDoublons());
//        ftDoublons.getCellFormatter().addStyleName(0, 0, SquareResources.INSTANCE.css().titreColonneTableDoublon());
//        styleLigne = SquareResources.INSTANCE.css().celluleSombreTableDoublon();
//        styleLigne = SquareResources.INSTANCE.css().celluleClaireTableDoublon();
        ftDoublons.setWidth(AppControllerConstants.POURCENT_100);
        ftDoublons.setListeObjets(listeDoublons);

        scrollPanel.add(ftDoublons);
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
    /**
     * Permet d'initliaser les données de la vue.
     * @param nom .
     * @param prenom .
     * @param listeDoublons .
     */
    @Override
    public void setData(String nom, String prenom, Boolean isContactPrincipal,
        List < PersonneDoublonModel > listeDoublons) {
        clean();
        formerBlocTexte(nom, prenom, isContactPrincipal);
        construireTableauRecherche(listeDoublons);
    }
    //TODO Cette methode dois être herité du socle.
    private void clean() {
        conteneurTexte.clear();
        scrollPanel.clear();
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
