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
package com.square.composant.contrat.square.client.view;

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
import com.square.composant.contrat.square.client.bundle.ComposantContratResources;
import com.square.composant.contrat.square.client.model.ConstantesModel;
import com.square.composant.contrat.square.client.presenter.ContratContenuPresenter.ContratContenuView;
import com.square.composant.contrat.square.client.presenter.ContratsPresenter.ContratInfosView;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;
import com.square.composants.graphiques.lib.client.event.HasOpenBlocSyntheseEventHandlers;

/**
 * Implémentation de la vue des infos d'un contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratInfosViewImpl extends Composite implements ContratInfosView {

    /** Ressources. */
    private ComposantContratResources resources;

    /** Constantes. */
    private ContratInfosViewImplConstants viewConstants;

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

    /** Les constantes. */
    private ConstantesModel constantes;

    /** Label de la date de signature. */
    private Label labelDateSignature;

    /**
     * Constructeur avec paramètre.
     * @param resources les ressources
     * @param constantes les constantes
     * @param afficherDateResiliation flag d'affichage de la date résiliation
     */
    public ContratInfosViewImpl(ComposantContratResources resources, ConstantesModel constantes, boolean afficherDateResiliation) {
        this.viewConstants = (ContratInfosViewImplConstants) GWT.create(ContratInfosViewImplConstants.class);
        this.resources = resources;
        this.constantes = constantes;

        contenu = new VerticalPanel();
        contenu.setWidth(ContratsViewImplConstants.POURCENT_100);

        // Initialisation des champs de l'entête
        labelStatut = new Label();
        labelDateSignature = new Label();
        labelDateEffet = new Label();
        labelDateResiliation = new Label();

        // Création des champs de synthèse
        final List<ChampSynthese> listeChamps = new ArrayList<ChampSynthese>();
        listeChamps.add(new ChampSynthese(labelStatut, viewConstants.libelleStatut(), true));
        listeChamps.add(new ChampSynthese(labelDateSignature, viewConstants.libelleDateSignature(), true));
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
    public void setNumeroContrat(String numeroContrat) {
        captionPanelContrat.setCaptionText(viewConstants.libelleContrat() + " " + numeroContrat);
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
    public HasWidgets getConteneur() {
        return contenu;
    }

    @Override
    public HasOpenBlocSyntheseEventHandlers getBlocSyntheseContrat() {
        return blocSyntheseDepliant;
    }

    @Override
    public ContratContenuView creerVueContenu() {
        return new ContratContenuViewImpl(resources, constantes);
    }

    @Override
    public HasText getLabelDateSignature() {
        return labelDateSignature;
    }

}
