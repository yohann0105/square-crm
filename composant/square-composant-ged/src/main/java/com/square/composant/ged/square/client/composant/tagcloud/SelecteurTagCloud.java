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
package com.square.composant.ged.square.client.composant.tagcloud;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.ged.square.client.model.TypeDocumentModel;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Composant permettant de gérer la sélection de types de documents sous forme de nuage de tags.
 * @author jgoncalves - SCUB
 */
public class SelecteurTagCloud extends Composite implements HasValueChangeHandlers<List<String>>, HasValue<List<String>> {

    /**
     * Identifiants de debug de la vue {@link SelecteurTagCloud}.
     */
    public static final SelecteurTagCloudDebugConstants DEBUG_CONSTANTS = GWT.create(SelecteurTagCloudDebugConstants.class);

    private static SelecteurTagCloudConstants constants = GWT.create(SelecteurTagCloudConstants.class);

    /** Constantes.*/
    private static final int TAILLE_POLICE_MIN = 8;
    private static final int TAILLE_POLICE_MAX = 15;
	private static final int FREQUENCE_MIN = 3000000;

    private List<TypeDocumentModel> listeTypesDocumentsDisponibles;
    private List<TypeDocumentModel> listeTypesDocumentsSelectionnes;
    private List<String> listeIdsTypesDocumentsSelectionnes;
    private VerticalPanel panelCbCategories;
    private FlowPanel tagCloud;
    private List<Anchor> listeTagsVisibles;
    private VerticalPanel selectedLinks;
    private DecoratedTextBox tbRecherche;
    private List<RadioButton> listeRb;

    private boolean lectureSeule;

    /** Constructeur par défaut. */
    public SelecteurTagCloud() {
        this(null);
    }

    /**
     * Constructeur paramétré.
     * @param iconeErreurChampManager un gestionnaire pour les icones d'erreur.
     */
    public SelecteurTagCloud(IconeErreurChampManager iconeErreurChampManager) {
        listeTypesDocumentsSelectionnes = new ArrayList<TypeDocumentModel>();
        listeTagsVisibles = new ArrayList<Anchor>();
        listeIdsTypesDocumentsSelectionnes = new ArrayList<String>();
        listeRb = new ArrayList<RadioButton>();

        final HorizontalPanel panelPrincipal = new HorizontalPanel();
        panelPrincipal.setWidth("100%");

        final VerticalPanel colonneGauche = new VerticalPanel();
        colonneGauche.addStyleName("colonneGaucheSelectionTypes");
        final VerticalPanel colonneDroite = new VerticalPanel();
        colonneDroite.addStyleName("colonneDroiteSelectionTypes");

        final CaptionPanel categoriesPanel = new CaptionPanel(constants.captionCategories());
        categoriesPanel.addStyleName("captionCategories");
        panelCbCategories = new VerticalPanel();
        categoriesPanel.add(panelCbCategories);

        final CaptionPanel typologiesPanel = new CaptionPanel(constants.captionTypologies());
        typologiesPanel.ensureDebugId(DEBUG_CONSTANTS.debugIdCaptionPanelTypologies());
        typologiesPanel.addStyleName("captionTypologies");
        selectedLinks = new VerticalPanel();
        final ScrollPanel scroll = new ScrollPanel();
        scroll.addStyleName("scrollTypologies");
        scroll.add(selectedLinks);
        typologiesPanel.add(scroll);

        colonneGauche.add(categoriesPanel);
        colonneGauche.add(typologiesPanel);

        tbRecherche = new DecoratedTextBox();
        tbRecherche.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                rechercher(tbRecherche.getValue());
            }
        });
        final HorizontalPanel panelTbRecherche = new HorizontalPanel();
        panelTbRecherche.add(tbRecherche);
        if (iconeErreurChampManager != null) {
            final IconeErreurChamp icone = iconeErreurChampManager.createInstance(constants.erreurChampVide(), this);
            panelTbRecherche.add(icone);
            panelTbRecherche.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
        }

        final ScrollPanel tagScroll = new ScrollPanel();
        tagCloud = new FlowPanel();
        tagScroll.add(tagCloud);
        tagCloud.addStyleName("tagCloud");
        tagScroll.addStyleName("tagScroll");
        colonneDroite.add(panelTbRecherche);
        colonneDroite.add(tagScroll);

        panelPrincipal.add(colonneGauche);
        panelPrincipal.add(colonneDroite);

        initWidget(panelPrincipal);
    }

    /**
     * Remplissage des catégories.
     */
    private void initCategories() {
        for (final TypeDocumentModel typeDoc : listeTypesDocumentsDisponibles) {
            final RadioButton rb = new RadioButton(constants.rbCategories(), typeDoc.getDescription() != null
                && !"".equals(typeDoc.getDescription().trim()) ? typeDoc
                .getDescription() : typeDoc.getNom());
            rb.setFormValue(typeDoc.getCode());
            rb.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    tbRecherche.setValue("");
                    remplirNuageTags(typeDoc);
                }
            });
            panelCbCategories.add(rb);
            listeRb.add(rb);
        }
    }

    /**
     * Remplissage du nuage de tags.
     * @param typeDocParent le typeDoc parent pour le remplissage.
     */
    private void remplirNuageTags(final TypeDocumentModel typeDocParent) {
        tagCloud.clear();
        listeTagsVisibles.clear();
        int frequenceMin = FREQUENCE_MIN;
        int frequenceMax = 0;

        // On parcours la liste une première fois pour déterminer la fréquence min & max
        for (TypeDocumentModel typeDoc : typeDocParent.getListeTypesDocuments()) {
            if (frequenceMin > typeDoc.getNombreDocs() + 1) {
                frequenceMin = typeDoc.getNombreDocs() + 1;
            }
            if (frequenceMax < typeDoc.getNombreDocs() + 1) {
                frequenceMax = typeDoc.getNombreDocs() + 1;
            }
        }
        for (final TypeDocumentModel typeDoc : typeDocParent.getListeTypesDocuments()) {
            final String text = typeDoc.getDescription() != null && !"".equals(typeDoc.getDescription().trim()) ? typeDoc
                .getDescription() : typeDoc.getNom();
            // Le tag doit être insécable
            final Anchor tagLink = new Anchor(text.replaceAll(" ", "&nbsp;") + " ", true);
            tagLink.setStylePrimaryName("cloudTag");
            final Style linkStyle = tagLink.getElement().getStyle();

            final double weight = (Math.log(typeDoc.getNombreDocs() + 1) - Math.log(frequenceMin)) / (Math.log(frequenceMax) - Math.log(frequenceMin));
            final int fontSize = TAILLE_POLICE_MIN + (int) Math.round((TAILLE_POLICE_MAX - TAILLE_POLICE_MIN) * weight);

            linkStyle.setProperty("fontSize", Integer.toString(fontSize) + "pt");
            tagLink.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    selectionnerElement(typeDoc, typeDocParent);
                }
            });
            tagCloud.add(tagLink);
            listeTagsVisibles.add(tagLink);
        }
    }

    /**
     * Ajout d'un élément à la liste des éléments sélectionnés.
     */
    private void selectionnerElement(final TypeDocumentModel typeDocument, final TypeDocumentModel typeDocumentParent) {

        if (!listeTypesDocumentsSelectionnes.contains(typeDocument)) {
            listeTypesDocumentsSelectionnes.add(typeDocument);
            final String labelParent = typeDocumentParent.getDescription() != null
                && !"".equals(typeDocumentParent.getDescription().trim()) ? typeDocumentParent
                .getDescription() : typeDocumentParent.getNom();
            final String label = typeDocument.getDescription() != null && !"".equals(typeDocument.getDescription().trim()) ? typeDocument
                .getDescription() : typeDocument.getNom();
            final Anchor tagLink = new Anchor(labelParent + " / " + label);
            tagLink.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (!lectureSeule) {
                        selectedLinks.remove(tagLink);
                        listeTypesDocumentsSelectionnes.remove(typeDocument);
                        listeIdsTypesDocumentsSelectionnes.remove(typeDocument.getNom());
                        fireValueChange();
                    }
                }
            });
            selectedLinks.add(tagLink);
            listeIdsTypesDocumentsSelectionnes.add(typeDocument.getNom());
            fireValueChange();
        }
    }

    private void fireValueChange() {
        ValueChangeEvent.fire(this, listeIdsTypesDocumentsSelectionnes);
    }

    /**
     * Définition de listeTypesDocumentsDisponibles.
     * @param listeTypesDocumentsDisponibles the listeTypesDocumentsDisponibles to set
     */
    public void setListeTypesDocumentsDisponibles(List<TypeDocumentModel> listeTypesDocumentsDisponibles) {
        this.listeTypesDocumentsDisponibles = listeTypesDocumentsDisponibles;
        initCategories();
    }

    /**
     * Recherche parmis les tags visibles.
     * @param recherche la recherche
     */
    private void rechercher(String recherche) {
        for (Anchor anchor : listeTagsVisibles) {
            //anchor.getElement().getStyle().setVisibility(anchor.getText().toLowerCase()
              //  .startsWith(recherche.toLowerCase()) ? Visibility.VISIBLE : Visibility.HIDDEN);
            anchor.setVisible(anchor.getText().toLowerCase().startsWith(recherche.toLowerCase()));
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<List<String>> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public List<String> getValue() {
        return listeIdsTypesDocumentsSelectionnes;
    }

    @Override
    public void setValue(List<String> value) {
        //listeIdsTypesDocumentsSelectionnes = value;
        // Il faut recontruire la liste des types sélectionnés
        for (TypeDocumentModel typeDocument : listeTypesDocumentsDisponibles) {
            trouverTypesSelectionnes(typeDocument, value);
        }
    }

    /**
     * Parcours les types disponibles pour trouver ceux qui ont été sélectionnés.
     * @param typeDocument .
     * @param listeIdsSelectionnes .
     */
    private void trouverTypesSelectionnes(TypeDocumentModel typeDocument, List<String> listeIdsSelectionnes) {
        if (typeDocument.getListeTypesDocuments() != null && !typeDocument.getListeTypesDocuments().isEmpty()) {
            for (final TypeDocumentModel typeDocEnfant : typeDocument.getListeTypesDocuments()) {
                if (listeIdsSelectionnes.contains(typeDocEnfant.getCode())) {
                    selectionnerElement(typeDocEnfant, typeDocument);
                }
                if (typeDocEnfant.getListeTypesDocuments() != null && !typeDocEnfant.getListeTypesDocuments().isEmpty()) {
                    trouverTypesSelectionnes(typeDocEnfant, listeIdsSelectionnes);
                }
            }
        }
    }

    @Override
    public void setValue(List<String> value, boolean fireEvents) {
        setValue(listeIdsTypesDocumentsSelectionnes);
        if (fireEvents) {
            ValueChangeEvent.fire(this, listeIdsTypesDocumentsSelectionnes);
        }
    }

    /**
     * Passe le composant en lecture seule.
     * @param newLectureSeule vrai ou faux
     */
    public void lectureSeule(boolean newLectureSeule) {
        tbRecherche.setEnabled(!newLectureSeule);
        for (RadioButton rb : listeRb) {
            rb.setEnabled(!newLectureSeule);
        }
        this.lectureSeule = newLectureSeule;
    }

    /**
     * Récupération de constants.
     * @return the constants
     */
    public static SelecteurTagCloudConstants getConstants() {
        return constants;
    }

}
