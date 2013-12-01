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
package com.square.composant.contrat.personne.morale.square.client.view;

import java.util.ArrayList;
import java.util.List;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.contrat.personne.morale.square.client.bundle.ComposantContratPersonneMoraleResources;
import com.square.composant.contrat.personne.morale.square.client.content.i18n.ComposantContratPersonneMoraleConstants;
import com.square.composant.contrat.personne.morale.square.client.model.ConstantesModel;
import com.square.composant.contrat.personne.morale.square.client.presenter.ContratPersonneMoraleContenuPresenter.ContratPersonneMoraleContenuView;
import com.square.composant.contrat.personne.morale.square.client.presenter.ContratsPersonneMoralePresenter.ContratPersonneMoraleInfosView;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;
import com.square.composants.graphiques.lib.client.event.HasOpenBlocSyntheseEventHandlers;

/**
 * Implémentation de la vue ContratPersonneMoraleInfosView.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ContratPersonneMoraleInfosViewImpl extends Composite implements ContratPersonneMoraleInfosView {

    /** Ressources. */
    private ComposantContratPersonneMoraleResources ressources;

    /** Constantes. */
    private ContratPersonneMoraleInfosViewImplConstants viewConstants;

    /** Constantes de l'application. */
    private ConstantesModel constantesApp;

    /** Caption panel englobant le contrat. */
    private CaptionPanel captionPanelContrat;

    /** BlocSyntheseDepliant du contrat. */
    private BlocSyntheseDepliant blocSyntheseDepliant;

    /** Label du statut. */
    private Label labelStatut;

    /** Label de la date d'effet. */
    private Label labelDateEffet;

    /** Label de la date de résiliation. */
    private Label labelDateResiliation;

    /** Le conteneur du contenu du bloc. */
    private VerticalPanel contenu;

    /** Label de la date de création. */
    private Label labelDateCreation;

    /**
     * Constructeur.
     * @param constantesApp constantes de l'application
     * @param ressources les ressources.
     * @param afficherDateResiliation flag d'affichage de la date résiliation.
     */
    public ContratPersonneMoraleInfosViewImpl(ConstantesModel constantesApp, ComposantContratPersonneMoraleResources ressources,
        boolean afficherDateResiliation) {
        this.viewConstants = (ContratPersonneMoraleInfosViewImplConstants) GWT.create(ContratPersonneMoraleInfosViewImplConstants.class);
        this.constantesApp = constantesApp;
        this.ressources = ressources;

        contenu = new VerticalPanel();
        contenu.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);

        // Initialisation des champs de l'entête
        labelStatut = new Label();
        labelDateCreation = new Label();
        labelDateEffet = new Label();
        labelDateResiliation = new Label();

        // Création des champs de synthèse
        final List<ChampSynthese> listeChamps = new ArrayList<ChampSynthese>();
        listeChamps.add(new ChampSynthese(labelStatut, viewConstants.libelleStatut(), true));
        listeChamps.add(new ChampSynthese(labelDateCreation, viewConstants.libelleDateCreation(), true));
        listeChamps.add(new ChampSynthese(labelDateEffet, viewConstants.libelleDateEffet(), true));
        if (afficherDateResiliation) {
            listeChamps.add(new ChampSynthese(labelDateResiliation, viewConstants.libelleDateResiliation(), true));
        }
        final int nbChampsVisibles = afficherDateResiliation ? 4 : 3;
        blocSyntheseDepliant = new BlocSyntheseDepliant(listeChamps, contenu, nbChampsVisibles);

        captionPanelContrat = new CaptionPanel(viewConstants.libelleContrat());
        captionPanelContrat.add(blocSyntheseDepliant);
        initWidget(captionPanelContrat);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public ContratPersonneMoraleContenuView creerVueContenu() {
        return new ContratPersonneMoraleContenuViewImpl(constantesApp, ressources);
    }

    @Override
    public HasOpenBlocSyntheseEventHandlers getBlocSyntheseContrat() {
        return blocSyntheseDepliant;
    }

    @Override
    public HasWidgets getConteneur() {
        return contenu;
    }

    @Override
    public HasText getLabelDateEffet() {
        return labelDateEffet;
    }

    @Override
    public HasText getLabelDateResiliation() {
        return labelDateResiliation;
    }

    @Override
    public HasText getLabelDateCreation() {
        return labelDateCreation;
    }

    @Override
    public HasText getLabelStatut() {
        return labelStatut;
    }

    @Override
    public void modifierCouleurEntete(String couleurEntete) {
        if (couleurEntete != null) {
            blocSyntheseDepliant.setStyleNameHeader(couleurEntete);
        }
    }

    @Override
    public void setNumeroContrat(String numeroContrat) {
        captionPanelContrat.setCaptionText(viewConstants.libelleContrat() + " " + numeroContrat);
    }

}
