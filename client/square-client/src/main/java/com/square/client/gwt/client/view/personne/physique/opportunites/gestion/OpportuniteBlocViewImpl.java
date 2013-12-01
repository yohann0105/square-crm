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
package com.square.client.gwt.client.view.personne.physique.opportunites.gestion;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.presenter.personne.physique.opportunites.PersonnePhysiqueOpportunitePresenter.OpportuniteBlocView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.event.HasCloseBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.HasOpenBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Bloc représentant une opportunité.
 * @author cblanchard - SCUB
 */
public class OpportuniteBlocViewImpl extends Composite implements OpportuniteBlocView {

    /** View constants. */
    private static OpportuniteBlocViewImplConstants viewConstants = (OpportuniteBlocViewImplConstants) GWT.create(OpportuniteBlocViewImplConstants.class);

    /** View constants. */
    private static OpportuniteBlocViewImplDebugIdConstants viewDebugIdConstants =
        (OpportuniteBlocViewImplDebugIdConstants) GWT.create(OpportuniteBlocViewImplDebugIdConstants.class);

    /** Conteneur principal. */
    private VerticalPanel conteneur;

    /** Le label du type. */
    private Label lType;

    /** Le label de l'objet. */
    private Label lObjet;

    /** Le label du sousObjet. */
    private Label lSousObjet;

    /** Le label du statut. */
    private Label lStatut;

    /** Le label de la date de création. */
    private Label lDateCreation;

    /** Le label du créateur. */
    private Label lCreateur;

    /** La suggestListe des ressources. */
    private DecoratedSuggestListBoxSingle<DimensionRessourceModel> slbsRessource;

    private AideComposant aideslbsRessource;

    /** La suggestListe des agences. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbsAgence;

    private AideComposant aideslbsAgence;

    private BlocSyntheseDepliant blocSyntheseDepliant;

    /** Caption panel d'un bloc. */
    private CaptionPanel captionPanel;

    /** Le contenu. */
    private VerticalPanel contenu = new VerticalPanel();

    /**
     * Gestion des icones d'erreur.
     */
    private IconeErreurChampManager iconeErreurChampManager;

    /**
     * mode de connexion.
     */
    private boolean isAdmin;

    /**
     * liste de composants d'aide de la vue.
     */
    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    /**
     * liste des events handlers des composants d'aides.
     */

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    /**
     * Constructeur.
     * @param isAdmin qualifie l'utilisateur connécté
     */
    public OpportuniteBlocViewImpl(boolean isAdmin) {
        iconeErreurChampManager = new IconeErreurChampManager();
        this.isAdmin = isAdmin;
        conteneur = new VerticalPanel();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.setSpacing(10);
        contenu = new VerticalPanel();
        contenu.setWidth(AppControllerConstants.POURCENT_100);
        construireBloc();
        initWidget(conteneur);
    }

    private void construireBloc() {
        captionPanel = new CaptionPanel();
        lType = new Label();
        lObjet = new Label();
        lSousObjet = new Label();
        lStatut = new Label();
        lDateCreation = new Label();
        lCreateur = new Label();
        captionPanel.setCaptionText(viewConstants.titreOpportunite());
        final SuggestListBoxSingleProperties<DimensionRessourceModel> properties = new SuggestListBoxSingleProperties<DimensionRessourceModel>() {
            @Override
            public String getSelectSuggestRenderer(DimensionRessourceModel row) {
                return row == null ? "" : row.getNom() + " " + row.getPrenom();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(DimensionRessourceModel row) {
                return new String[] {row == null ? "" : row.getNom() + " " + row.getPrenom()};
            }
        };

        slbsRessource = new DecoratedSuggestListBoxSingle<DimensionRessourceModel>(properties);
        slbsRessource.ensureDebugId(viewDebugIdConstants.debugIdSlbsRessource());
        aideslbsRessource = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_OPPORTUNITES_GESTION_RESSOURCE, isAdmin);
        ajouterAideComposant(aideslbsRessource);

        final HorizontalPanel panelslbsRessource = new HorizontalPanel();
        panelslbsRessource.add(slbsRessource);
        panelslbsRessource.add(aideslbsRessource);
        panelslbsRessource.setSpacing(5);

        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> propertiesAgence = new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
            @Override
            public String getSelectSuggestRenderer(IdentifiantLibelleGwt row) {
                return row == null ? "" : row.getLibelle();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(IdentifiantLibelleGwt row) {
                return new String[] {row == null ? "" : row.getLibelle()};
            }
        };
        slbsAgence = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesAgence);
        slbsAgence.ensureDebugId(viewDebugIdConstants.debugIdSlbsAgence());
        aideslbsAgence = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_OPPORTUNITES_GESTION_AGENCE, isAdmin);
        ajouterAideComposant(aideslbsAgence);
        final HorizontalPanel panelslbsAgence = new HorizontalPanel();
        panelslbsAgence.add(slbsAgence);
        panelslbsAgence.add(aideslbsAgence);
        panelslbsAgence.setSpacing(5);

        final List<ChampSynthese> listeChamps = new ArrayList<ChampSynthese>();
        listeChamps.add(new ChampSynthese(lType, viewConstants.type(), true));
        listeChamps.add(new ChampSynthese(lObjet, viewConstants.objet(), true));
        listeChamps.add(new ChampSynthese(lSousObjet, viewConstants.sousObjet(), true));
        listeChamps.add(new ChampSynthese(panelslbsAgence, viewConstants.agence(), iconeErreurChampManager.createInstance("opportuniteModificationDto.agence",
            slbsAgence), true));
        listeChamps.add(new ChampSynthese(lStatut, viewConstants.statut(), true));
        listeChamps.add(new ChampSynthese(lDateCreation, viewConstants.dateCreation(), true));
        listeChamps.add(new ChampSynthese(lCreateur, viewConstants.createur(), true));
        listeChamps.add(new ChampSynthese(panelslbsRessource, viewConstants.ressource(), iconeErreurChampManager.createInstance(
            "opportuniteModificationDto.ressource", slbsRessource), true));
        blocSyntheseDepliant = new BlocSyntheseDepliant(listeChamps, contenu, 4);
        blocSyntheseDepliant.ensureDebugId(viewDebugIdConstants.debugIdSBlocSyntheseDepliant());
        captionPanel.add(blocSyntheseDepliant);
        conteneur.add(captionPanel);
    }

    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    @Override
    public void modifierTitreBloc(String eid) {
        captionPanel.setCaptionText(viewConstants.titreOpportunite() + " " + eid);
    }

    /** Ouvrir le bloc. */
    @Override
    public void ouvrirBloc() {
        blocSyntheseDepliant.setOpen(true);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasText getlType() {
        return lType;
    }

    @Override
    public HasText getlObjet() {
        return lObjet;
    }

    @Override
    public HasText getlSousObjet() {
        return lSousObjet;
    }

    @Override
    public HasText getlStatut() {
        return lStatut;
    }

    @Override
    public HasText getlDateCreation() {
        return lDateCreation;
    }

    @Override
    public HasText getlCreateur() {
        return lCreateur;
    }

    @Override
    public HasSuggestListBoxHandler<DimensionRessourceModel> getSlbsRessource() {
        return slbsRessource;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsAgence() {
        return slbsAgence;
    }

    @Override
    public HasValue<DimensionRessourceModel> getSlbsRessourceValue() {
        return slbsRessource;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbsAgenceValue() {
        return slbsAgence;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    /**
     * Ajoute la couleur au bloc en fonction du statut.
     * @param couleurEntete la couleur de l'entête
     */
    public void changerCouleurBloc(String couleurEntete) {
        if (couleurEntete != null) {
            blocSyntheseDepliant.setStyleNameHeader(couleurEntete);
        }
    }

    @Override
    public HasWidgets getConteneurComposantTarificateur() {
        return contenu;
    }

    @Override
    public HasOpenBlocSyntheseEventHandlers getBlocSyntheseOpportunite() {
        return blocSyntheseDepliant;
    }

    @Override
    public HasCloseBlocSyntheseEventHandlers getCloseBlocSyntheseOpportuniteEventHandler() {
        return blocSyntheseDepliant;
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

}
