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
package com.square.composant.fusion.square.client.view.fusion;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.fusion.square.client.composants.BlocChampsPersonneFusion;
import com.square.composant.fusion.square.client.composants.ResumeFusion;
import com.square.composant.fusion.square.client.model.ConstantesModel;
import com.square.composant.fusion.square.client.model.PersonneCibleFusionModel;
import com.square.composant.fusion.square.client.model.PersonneSourceFusionModel;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenter;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenterConstants;
import com.square.composant.fusion.square.client.presenter.fusion.FusionPresenter;

/**
 * Vue pour la fusion.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class FusionViewImpl extends Composite implements FusionPresenter.FusionView {

    /** Constantes de la vue. */
    private FusionViewImplConstants viewConstants;

    /** Conteneur principal. */
    private VerticalPanel conteneur;

    /** Bouton permettant d'afficher le résumer de la fusion. */
    private DecoratedButton btnSuivant;

    /** Bouton permettant de revenir en arrière (sélection des champs à fusionner). */
    private DecoratedButton btnPrecedent;

    /** Bouton pour valider la fusion. */
    private DecoratedButton btnValiderFusion;

    /** Personne source en cours. */
    private BlocChampsPersonneFusion blocPersonneSource;

    /** Personne cible en cours. */
    private BlocChampsPersonneFusion blocPersonneCible;

    /** Bouton pour inverser la source et la cible (actif). */
    private Image btnInverserEnabled;

    /** Bouton pour inverser la source et la cible (inactif). */
    private Image btnInverserDesabled;

    /** Panel contenant la comparaison des champs à fusionner entre les dux personnes. */
    private HorizontalPanel pContenuComparaisonPersonne;

    /** Résumé de la fusion. */
    private ResumeFusion resumeFusion;

    /** Constantes. */
    private ConstantesModel constantes;

    /**
     * Constructeur.
     * @param constantes les constantes.
     */
    public FusionViewImpl(ConstantesModel constantes) {
        // Création des constantes de la vue
        viewConstants = (FusionViewImplConstants) GWT.create(FusionViewImplConstants.class);
        this.constantes = constantes;

        conteneur = new VerticalPanel();
        conteneur.setSpacing(5);

        // Construction de la barre de boutons
        construireBarreBoutons();

        // Construction du contenu de la comparaison des deux personnes
        pContenuComparaisonPersonne = new HorizontalPanel();
        pContenuComparaisonPersonne.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        pContenuComparaisonPersonne.setVisible(false);
        conteneur.add(pContenuComparaisonPersonne);

        // Initialisation du bouton pour inverser le sens de la fusion
        btnInverserEnabled = new Image(ComposantFusionPresenter.RESOURCES.inverserSensFusionActif());
        btnInverserDesabled = new Image(ComposantFusionPresenter.RESOURCES.inverserSensFusionInactif());
        btnInverserDesabled.addStyleName(ComposantFusionPresenter.RESOURCES.css().btnInactif());

        // Construction du contenu du résumé de la fusion
        construireContenuResumeFusion();

        this.initWidget(conteneur);
        this.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        this.addStyleName(ComposantFusionPresenter.RESOURCES.css().contenuPageFusion());
    }

    /** Construit la barre de boutons. */
    private void construireBarreBoutons() {
        btnSuivant = new DecoratedButton(viewConstants.btnSuivant());
        btnSuivant.setVisible(false);
        btnPrecedent = new DecoratedButton(viewConstants.btnPrecedent());
        btnPrecedent.setVisible(false);
        btnValiderFusion = new DecoratedButton(viewConstants.btnValiderFusion());
        btnValiderFusion.setVisible(false);

        // Barre de boutons de la page
        final HorizontalPanel pBarreBoutons = new HorizontalPanel();
        pBarreBoutons.setWidth(ComposantFusionPresenterConstants.POURCENT_100);

        // Contenu de la barre de boutons
        final HorizontalPanel pContenuBarreBoutons = new HorizontalPanel();
        pContenuBarreBoutons.setSpacing(5);
        pContenuBarreBoutons.add(btnSuivant);
        pContenuBarreBoutons.add(btnPrecedent);
        pContenuBarreBoutons.add(btnValiderFusion);

        pBarreBoutons.add(pContenuBarreBoutons);
        conteneur.add(pBarreBoutons);
    }

    /** Construite le contenu du résumé avant la fusion. */
    private void construireContenuResumeFusion() {
        resumeFusion = new ResumeFusion(constantes);
        resumeFusion.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        resumeFusion.setVisible(false);
        conteneur.add(resumeFusion);
        conteneur.setCellHorizontalAlignment(resumeFusion, HasAlignment.ALIGN_CENTER);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void chargerPersonnes(PersonneSourceFusionModel personneSourceEncours, PersonneCibleFusionModel personneCibleEnCours) {
        pContenuComparaisonPersonne.clear();
        pContenuComparaisonPersonne.setVisible(true);
        resumeFusion.setVisible(false);
        // initialisation des bloc des champs des personnes
        blocPersonneSource = new BlocChampsPersonneFusion(true, personneSourceEncours, personneCibleEnCours, constantes);
        blocPersonneCible = new BlocChampsPersonneFusion(false, personneSourceEncours, personneCibleEnCours, constantes);

        pContenuComparaisonPersonne.add(blocPersonneSource);
        if (personneSourceEncours.getPossedeContrat() != null && !personneSourceEncours.getPossedeContrat()
                && personneCibleEnCours.getPossedeContrat() != null && !personneCibleEnCours.getPossedeContrat()
                && personneSourceEncours.getPossedeOppTransformee() != null && !personneSourceEncours.getPossedeOppTransformee()
                && personneCibleEnCours.getPossedeOppTransformee() != null && !personneCibleEnCours.getPossedeOppTransformee()) {
            pContenuComparaisonPersonne.add(btnInverserEnabled);
            pContenuComparaisonPersonne.setCellVerticalAlignment(btnInverserEnabled, HasAlignment.ALIGN_MIDDLE);
            pContenuComparaisonPersonne.setCellHorizontalAlignment(btnInverserEnabled, HasAlignment.ALIGN_CENTER);
        } else {
            pContenuComparaisonPersonne.add(btnInverserDesabled);
            pContenuComparaisonPersonne.setCellVerticalAlignment(btnInverserDesabled, HasAlignment.ALIGN_MIDDLE);
            pContenuComparaisonPersonne.setCellHorizontalAlignment(btnInverserDesabled, HasAlignment.ALIGN_CENTER);
        }
        pContenuComparaisonPersonne.add(blocPersonneCible);

        btnSuivant.setVisible(true);
        btnValiderFusion.setVisible(false);
        btnPrecedent.setVisible(false);
    }

    @Override
    public HasClickHandlers getBtnInverser() {
        return btnInverserEnabled;
    }

    @Override
    public HasClickHandlers getBtnSuivant() {
        return btnSuivant;
    }

    @Override
    public HasClickHandlers getBtnValiderFusion() {
        return btnValiderFusion;
    }

    @Override
    public HasClickHandlers getBtnPrecedent() {
        return btnPrecedent;
    }

    @Override
    public void afficherSelectionChampsFusion() {
        pContenuComparaisonPersonne.setVisible(true);
        resumeFusion.setVisible(false);
        btnSuivant.setVisible(true);
        btnPrecedent.setVisible(false);
        btnValiderFusion.setVisible(false);
    }

    @Override
    public void chargerResume(PersonneSourceFusionModel personneSourceEncours) {
        pContenuComparaisonPersonne.setVisible(false);
        resumeFusion.setVisible(true);
        btnSuivant.setVisible(false);
        btnPrecedent.setVisible(true);
        btnValiderFusion.setVisible(true);
        resumeFusion.chargerResume(personneSourceEncours);
    }

    @Override
    public void initVue() {
        pContenuComparaisonPersonne.setVisible(false);
        resumeFusion.setVisible(false);
        btnSuivant.setVisible(false);
        btnPrecedent.setVisible(false);
        btnValiderFusion.setVisible(false);
    }

    @Override
    public void nettoyer() {
        pContenuComparaisonPersonne.clear();
    }
}
