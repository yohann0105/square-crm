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
package com.square.client.gwt.client.view.personne.relations;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.ItemValueModel;
import com.square.client.gwt.client.presenter.personne.PersonneRelationsPresenter.PersonneRelationsBlocView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue pour les blocs de relations.
 * @author cblanchard - SCUB
 */
public class PersonneRelationsBlocViewImpl extends Composite implements PersonneRelationsBlocView {

    /** Contenu du bloc dépliant. */
    private FlexTable contenu;

    /** Constantes de la vue. **/
    private PersonneRelationsBlocViewImplConstants viewConstants;

    /** Constantes de la vue. **/
    private PersonneRelationsBlocViewImplDebugIdConstants viewDebugIdConstants;

    /** Gestion des icones d'erreur. **/
    private IconeErreurChampManager iconeErreurChampManager;

    /** Image Synthése. **/
    private Image imageSynthese;

    /** SuggesListbox des types de relations. */
    private DecoratedSuggestListBoxSingle<ItemValueModel> suggestListBoxTypeRelation;

    private AideComposant aidesuggestListBoxTypeRelation;

    /** Label Personne cible. **/
    private Label lPersonneCible;

    /** Calendrier date box date de fin. */
    private DecoratedCalendrierDateBox dateFin;

    private AideComposant aidedateFin;

    private int index;

    /** Conteneur de l'image des contrats. */
    private VerticalPanel conteneurImageContrat;

    private VerticalPanel conteneurImageActivation;

    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    private boolean isAdmin;

    private CaptionPanel fieldSetPanel;

    /**
     * Constructeur.
     * @param typeRelation le type de relation à afficher
     * @param index index de la relation
     */
    public PersonneRelationsBlocViewImpl(String typeRelation, int index, boolean isAdmin) {
        this.viewConstants = (PersonneRelationsBlocViewImplConstants) GWT.create(PersonneRelationsBlocViewImplConstants.class);
        this.viewDebugIdConstants = (PersonneRelationsBlocViewImplDebugIdConstants) GWT.create(PersonneRelationsBlocViewImplDebugIdConstants.class);
        this.iconeErreurChampManager = new IconeErreurChampManager();
        this.index = index;
        this.isAdmin = isAdmin;

        construireContenu();

        fieldSetPanel = new CaptionPanel(typeRelation);
        fieldSetPanel.add(contenu);
        initWidget(fieldSetPanel);
    }

    private void construireContenu() {
        contenu = new FlexTable();
        contenu.setWidth(AppControllerConstants.POURCENT_100);

        final Label lRelation = new Label(viewConstants.libelleRelation(), false);
        final Label lDateFin = new Label(viewConstants.libelleDateFin(), false);

        final SuggestListBoxSingleProperties<ItemValueModel> properties = new SuggestListBoxSingleProperties<ItemValueModel>() {
            @Override
            public String getSelectSuggestRenderer(ItemValueModel row) {
                return row == null ? "" : row.getValue();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(ItemValueModel row) {
                return new String[] {row == null ? "" : row.getValue()};
            }
        };
        suggestListBoxTypeRelation = new DecoratedSuggestListBoxSingle<ItemValueModel>(properties);
        suggestListBoxTypeRelation.ensureDebugId(viewDebugIdConstants.debugSuggestListBoxTypeRelation());
        aidesuggestListBoxTypeRelation = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_BLOC_TYPE_RELATION, isAdmin);
        ajouterAideComposant(aidesuggestListBoxTypeRelation);
        final HorizontalPanel hpSlbTypeRelation =
            construireBlocIcone(suggestListBoxTypeRelation, "RelationDto.type.libelle." + index, aidesuggestListBoxTypeRelation);
        lPersonneCible = new Label();
        dateFin = new DecoratedCalendrierDateBox(true);
        dateFin.ensureDebugId(viewDebugIdConstants.debugIdDateFin());
        aidedateFin = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_BLOC_DATE_FIN, isAdmin);
        ajouterAideComposant(aidedateFin);

        imageSynthese = new Image(SquareResources.INSTANCE.iconeOngletPersonnePhysique());
        imageSynthese.setStylePrimaryName(SquareResources.INSTANCE.css().lienFichePersonne());
        imageSynthese.setTitle(viewConstants.titreImageLienPersonne());
        conteneurImageContrat = new VerticalPanel();
        conteneurImageActivation = new VerticalPanel();

        contenu.setWidget(0, 0, lRelation);
        contenu.setWidget(0, 1, hpSlbTypeRelation);
        contenu.setWidget(0, 2, lPersonneCible);
        contenu.setWidget(0, 3, lDateFin);
        contenu.setWidget(0, 4, construireBlocIcone(dateFin, "RelationDto.dateFin." + index, aidedateFin));
        contenu.setWidget(0, 5, imageSynthese);
        contenu.setWidget(0, 6, conteneurImageContrat);
        contenu.setWidget(0, 7, conteneurImageActivation);

        contenu.getRowFormatter().setVerticalAlign(0, HasVerticalAlignment.ALIGN_MIDDLE);
        contenu.getCellFormatter().setHorizontalAlignment(0, 5, HasHorizontalAlignment.ALIGN_CENTER);
        contenu.getCellFormatter().setHorizontalAlignment(0, 6, HasHorizontalAlignment.ALIGN_CENTER);

        contenu.getCellFormatter().setWidth(0, 0, "8%");
        contenu.getCellFormatter().setWidth(0, 1, "27%");
        contenu.getCellFormatter().setWidth(0, 2, "30%");
        contenu.getCellFormatter().setWidth(0, 3, "5%");
        contenu.getCellFormatter().setWidth(0, 4, "18%");
        contenu.getCellFormatter().setWidth(0, 5, "4%");
        contenu.getCellFormatter().setWidth(0, 6, "4%");
        contenu.getCellFormatter().setWidth(0, 7, "4%");
    }

    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);
    }

    /**
     * Construit un bloc avec un label et un champ avec un champ d'aide pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp, AideComposant aideComposant) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        final HorizontalPanel panelIcone = new HorizontalPanel();
        panelIcone.add(icone);
        panelIcone.add(aideComposant);
        panel.add(panelIcone);
        panel.setCellVerticalAlignment(panelIcone, HasVerticalAlignment.ALIGN_MIDDLE);
        return panel;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasClickHandlers getImageSynthese() {
        return imageSynthese;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public HasSuggestListBoxHandler<ItemValueModel> getSuggestListBoxTypeRelationHandler() {
        return suggestListBoxTypeRelation;
    }

    @Override
    public HasValue<ItemValueModel> getSuggestListBoxTypeRelationValue() {
        return suggestListBoxTypeRelation;
    }

    @Override
    public Label getlPersonneCible() {
        return lPersonneCible;
    }

    @Override
    public DecoratedCalendrierDateBox getDateFin() {
        return dateFin;
    }

    @Override
    public VerticalPanel getConteneurImageContrat() {
        return conteneurImageContrat;
    }

    @Override
    public List<AideComposant> getListAideCompsant() {
        return listComposantAide;
    }

    @Override
    public List<HasDemandeAideEventHandler> getListeDemandeEventHandler() {
        return listDemandeAideEventHandler;
    }

    @Override
    public List<HasUpdateAideEventHandler> getListeUpdateEventHandler() {
        return listUpdateAideEventHandler;
    }

    @Override
    public void afficherStyleRelationTerminee() {
        fieldSetPanel.addStyleName(SquareResources.INSTANCE.css().blocRelationTerminee());
    }

    @Override
    public void afficherStyleRelationEnCours() {
    }
}
