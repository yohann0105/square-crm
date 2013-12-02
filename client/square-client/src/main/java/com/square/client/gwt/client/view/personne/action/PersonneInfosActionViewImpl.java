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
package com.square.client.gwt.client.view.personne.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.presenter.personne.PersonneActionContenuPresenter.PersonneActionContenuView;
import com.square.client.gwt.client.presenter.personne.PersonneActionsPresenter.PersonneInfosActionView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.DecoratedTextBoxFormat;
import com.square.composants.graphiques.lib.client.event.HasCloseBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.HasOpenBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue pour le détail d'une action.
 * @author cblanchard - SCUB
 */
public class PersonneInfosActionViewImpl extends Composite implements PersonneInfosActionView {

    /** View constants. */
    private static PersonneInfosActionViewImplDebugIdConstants viewDebugIdConstants =
        (PersonneInfosActionViewImplDebugIdConstants) GWT.create(PersonneInfosActionViewImplDebugIdConstants.class);

    /** BlocSyntheseDepliant de l'action. */
    private BlocSyntheseDepliant blocSyntheseDepliant;

    /** Bouton ajouter une action liée. */
    private DecoratedButton btnAjoutActionLiee;

    /** Bouton enregistrer. */
    private DecoratedButton btnEnregistrer;

    /** Label du type. */
    private Label lType;

    /** Label de l'objet. */
    private Label lObjet;

    /** Label du sous-objet. */
    private Label lSousObjet;

    /** Label du niveau. */
    private Label lNiveau;

    private Label libelleDate;

    /** Label de la date. */
    private DecoratedCalendrierDateBox cdbDate;

    private AideComposant aidecdbDate;

    /** Label de l'heure de la date. */
    private DecoratedTextBoxFormat tbfHeureDate;

    private AideComposant aidetbfHeureDate;

    /** Label de la date d'action terminée. */
    private Label lDateTerminee;

    /** Label de l'heure d'action terminée. */
    private Label lHeureTerminee;

    /** Label de la ressource. */
    private Label lRessource;

    /** Label de la réclamation. */
    private Label lReclamation;

    /** La suggestListe des priorités. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbPriorite;

    private AideComposant aideslbPriorite;

    /** Les constantes. */
    private PersonneInfosActionViewImplConstants constants;

    /** Le conteneur du contenu du bloc. */
    private VerticalPanel contenu;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    private HTML libelleNiveau;

    /** Label pour la nature du contact. */
    private Label lNatureContact;

    /** Label pour le libellé de la campagne. */
    private Label lCampagne;

    /**
     * mode de connexion.
     */
    private boolean isAdmin;

    /** Indique si action terminée ou annulée. */
    private boolean isAnnuleOuTermine;

    /**
     * liste de composants d'aide de la vue.
     */
    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    /**
     * liste des events des handlers des composants d'aides.
     */
    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    /** La suggestListe des durées. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbDuree;

    /** Composant d'aide pour la durée. */
    private AideComposant aideSlbDuree;

    /**
     * Constructeur.
     * @param isAdmin si l'utilsiateur courant est admin
     */
    public PersonneInfosActionViewImpl(boolean isAdmin) {
        iconeErreurChampManager = new IconeErreurChampManager();
        this.isAdmin = isAdmin;
        constants = (PersonneInfosActionViewImplConstants) GWT.create(PersonneInfosActionViewImplConstants.class);

        // Propriétés por la suggestListBox
        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties =
            new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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

        // Initialisation des champs de l'entête.
        final HTML libelleType = new HTML(constants.libelleType(), false);
        libelleType.addStyleName(SquareResources.INSTANCE.css().headerEnteteAction());
        lType = new Label("", false);
        lType.addStyleName(SquareResources.INSTANCE.css().headerEnteteAction());
        final HTML libelleObjet = new HTML(constants.libelleObjet(), false);
        libelleObjet.addStyleName(SquareResources.INSTANCE.css().headerEnteteAction());
        lObjet = new Label("", false);
        lObjet.addStyleName(SquareResources.INSTANCE.css().headerEnteteAction());
        final HTML libelleSousObjet = new HTML(constants.libelleSousObjet(), false);
        libelleSousObjet.addStyleName(SquareResources.INSTANCE.css().headerEnteteAction());
        lSousObjet = new Label("", false);
        lSousObjet.addStyleName(SquareResources.INSTANCE.css().headerEnteteAction());
        libelleNiveau = new HTML(" ", false);
        lNiveau = new Label("", false);
        libelleDate = new Label(constants.libelleDate(), false);
        cdbDate = new DecoratedCalendrierDateBox(true);
        aidecdbDate = new AideComposant(AideComposantConstants.AIDE_PERSONNE_INFOS_ACTION_DATE, isAdmin);
        ajouterAideComposant(aidecdbDate);

        HorizontalPanel panelcdbDate = new HorizontalPanel();
        panelcdbDate.add(cdbDate);
        panelcdbDate.add(aidecdbDate);
        panelcdbDate.setSpacing(5);

        final HTML libelleRessource = new HTML(constants.libelleRessource(), false);
        lRessource = new Label("", false);
        final HTML libellePriorite = new HTML(constants.libellePriorite(), false);
        slbPriorite = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbPriorite.ensureDebugId(viewDebugIdConstants.debugIdSlbPriorite());
        aideslbPriorite = new AideComposant(AideComposantConstants.AIDE_PERSONNE_INFOS_ACTION_PRIORITE, isAdmin);
        ajouterAideComposant(aideslbPriorite);
        HorizontalPanel panelslbPriorite = new HorizontalPanel();
        panelslbPriorite.add(slbPriorite);
        panelslbPriorite.add(aideslbPriorite);
        panelslbPriorite.setSpacing(5);
        lReclamation = new Label("", false);
        lReclamation.addStyleName(SquareResources.INSTANCE.css().labelReclamation());
        tbfHeureDate = new DecoratedTextBoxFormat("NN:NN");
        tbfHeureDate.addStyleName(SquareResources.INSTANCE.css().heureDate());
        aidetbfHeureDate = new AideComposant(AideComposantConstants.AIDE_PERSONNE_INFOS_ACTION_HEURE_DATE, isAdmin);
        ajouterAideComposant(aidetbfHeureDate);
        final HorizontalPanel paneltbfHeureDate = new HorizontalPanel();
        paneltbfHeureDate.add(tbfHeureDate);
        paneltbfHeureDate.add(aidetbfHeureDate);
        paneltbfHeureDate.setSpacing(2);
        slbDuree = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbDuree.ensureDebugId(viewDebugIdConstants.debugIdSlbDuree());
        slbDuree.setWidth(PersonneInfosActionViewImplConstants.LARGEUR_SLB_DUREE);
        aideSlbDuree = new AideComposant(107004L, isAdmin);
        final HorizontalPanel panelslbDuree = new HorizontalPanel();
        panelslbDuree.add(slbDuree);
        panelslbDuree.add(aideSlbDuree);
        panelslbDuree.setSpacing(2);

        panelcdbDate.setWidth("120%");
        paneltbfHeureDate.setWidth("130%");
        panelslbDuree.setWidth("120%");
        lRessource.setWidth("110%");
        panelslbPriorite.setWidth("110%");

        final HTML libelleNatureContact = new HTML(constants.libelleNatureContact(), false);
        lNatureContact = new Label("", false);
        final HTML libelleCampagne = new HTML(constants.libelleCampagne(), false);
        lCampagne = new Label("", false);
        final HTML libelleDateTerminee = new HTML(constants.libelleFaitLe(), false);
        lDateTerminee = new Label("", false);
        lHeureTerminee = new Label("", false);

        final List<ChampSynthese> listeChamps = new ArrayList<ChampSynthese>();
        listeChamps.add(new ChampSynthese(lType, libelleType, true));
        listeChamps.add(new ChampSynthese(lObjet, libelleObjet, true));
        listeChamps.add(new ChampSynthese(lSousObjet, libelleSousObjet, true));
        listeChamps.add(new ChampSynthese(lReclamation, "", true));
        final ChampSynthese champSyntheseNiveau = new ChampSynthese(lNiveau, libelleNiveau, true);
        listeChamps.add(champSyntheseNiveau);

        // si l'action est terminée, on ne garde que les éléments de la première ligne
        listeChamps.add(new ChampSynthese(panelcdbDate, libelleDate,
            iconeErreurChampManager.createInstance("ActionModificationDto.dateAction", cdbDate), true));
        listeChamps.add(new ChampSynthese(paneltbfHeureDate, constants.libelleA(), constants.libelleDe(),
            iconeErreurChampManager.createInstance("ActionModificationDto.dateAction", tbfHeureDate), true));
        listeChamps.add(new ChampSynthese(panelslbDuree, constants.libelleDuree(), iconeErreurChampManager.createInstance("ActionModificationDto.idDuree",
            slbDuree), true, true));
        listeChamps.add(new ChampSynthese(lRessource, libelleRessource, true));
        listeChamps.add(new ChampSynthese(panelslbPriorite, libellePriorite, iconeErreurChampManager.createInstance("ActionModificationDto.priorite",
            slbPriorite), true));
        listeChamps.add(new ChampSynthese(lDateTerminee, libelleDateTerminee, false));
        listeChamps.add(new ChampSynthese(lHeureTerminee, "", false));
        listeChamps.add(new ChampSynthese(lNatureContact, libelleNatureContact, false));
        listeChamps.add(new ChampSynthese(lCampagne, libelleCampagne, false));

        final VerticalPanel panelContenuBloc = new VerticalPanel();
        panelContenuBloc.setStylePrimaryName(SquareResources.INSTANCE.css().personneInfosAction());
        panelContenuBloc.setSpacing(5);
        contenu = new VerticalPanel();
        contenu.setWidth(AppControllerConstants.POURCENT_100);
        panelContenuBloc.add(contenu);

        final HorizontalPanel horizontalPanelBouton = new HorizontalPanel();
        btnAjoutActionLiee = new DecoratedButton(constants.boutonAjouterActionLiee());
        btnEnregistrer = new DecoratedButton(constants.boutonEnregistrer());
        horizontalPanelBouton.add(btnAjoutActionLiee);
        horizontalPanelBouton.add(btnEnregistrer);
        horizontalPanelBouton.setSpacing(5);

        panelContenuBloc.add(horizontalPanelBouton);
        panelContenuBloc.setCellHorizontalAlignment(horizontalPanelBouton, HasAlignment.ALIGN_RIGHT);
        panelContenuBloc.setWidth(AppControllerConstants.POURCENT_100);
        blocSyntheseDepliant = new BlocSyntheseDepliant(listeChamps, panelContenuBloc, 5);
        initWidget(blocSyntheseDepliant);
    }

    /** remplissage des listes des aides et des events associés. */
    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    @Override
    public void desactiverInfos() {
        btnAjoutActionLiee.setEnabled(false);
    }

    /**
     * Désactive les champs de la date d'action.
     */
    @Override
    public void desactiverDate() {
        cdbDate.setEnabled(false);
        tbfHeureDate.setEnabled(false);
        slbDuree.setEnabled(false);
    }

    @Override
    public void modifierStyleStatutTerminer() {
        blocSyntheseDepliant.setStyleNameHeader(SquareResources.INSTANCE.css().blocSyntheseDepliantActionTerminerHeader());
        blocSyntheseDepliant.setStyleName(SquareResources.INSTANCE.css().blocSyntheseDepliantActionTerminer());
        libelleDate.setText(constants.libelleAFaire());
    }

    @Override
    public void modifierStyleStatutAnnuler() {
        blocSyntheseDepliant.setStyleNameHeader(SquareResources.INSTANCE.css().blocSyntheseDepliantActionAnnulerHeader());
        blocSyntheseDepliant.setStyleName(SquareResources.INSTANCE.css().blocSyntheseDepliantActionAnnuler());
        libelleDate.setText(constants.libelleDate());
    }

    @Override
    public void modifierStyleStatutEnCours() {
        blocSyntheseDepliant.setStyleNameHeader(SquareResources.INSTANCE.css().blocSyntheseDepliantActionEnCoursHeader());
        blocSyntheseDepliant.setStyleName(SquareResources.INSTANCE.css().blocSyntheseDepliantActionEnCours());
        libelleDate.setText(constants.libelleAFaire());
    }

    @Override
    public void reduireBlocEnUneLigne() {
        blocSyntheseDepliant.reduireBlocEnUneLigne();
    }

    @Override
    public void restaurerBloc() {
        blocSyntheseDepliant.restaurerBloc();
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public Label getlType() {
        return lType;
    }

    @Override
    public Label getlObjet() {
        return lObjet;
    }

    @Override
    public Label getlSousObjet() {
        return lSousObjet;
    }

    @Override
    public Label getlNiveau() {
        return lNiveau;
    }

    @Override
    public DecoratedCalendrierDateBox getcdbDate() {
        return cdbDate;
    }

    @Override
    public Label getlRessource() {
        return lRessource;
    }

    @Override
    public Label getlReclamation() {
        return lReclamation;
    }

    @Override
    public PersonneInfosActionViewImplConstants getConstants() {
        return constants;
    }

    @Override
    public HasOpenBlocSyntheseEventHandlers getBlocSyntheseDepliantOpenHandler() {
        return blocSyntheseDepliant;
    }

    @Override
    public HasCloseBlocSyntheseEventHandlers getBlocSyntheseDepliantCloseHandler() {
        return blocSyntheseDepliant;
    }

    @Override
    public VerticalPanel getContenu() {
        return contenu;
    }

    @Override
    public HasValue<String> getlHeureDate() {
        return tbfHeureDate;
    }

    @Override
    public DecoratedButton getBtnAjoutActionLiee() {
        return btnAjoutActionLiee;
    }

    @Override
    public DecoratedButton getBtnEnregistrer() {
        return btnEnregistrer;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public PersonneActionContenuView creerVueContenu() {
        final PersonneActionContenuViewImpl viewContenu = new PersonneActionContenuViewImpl(this.isAdmin);
        return viewContenu;
    }

    @Override
    public void activerBtnEnregistrer(boolean isEnable) {
        btnEnregistrer.setEnabled(isEnable);
    }

    @Override
    public HasHTML getLibelleNiveau() {
        return libelleNiveau;
    }

    @Override
    public PersonneInfosActionViewImplConstants getViewConstants() {
        return constants;
    }

    @Override
    public HasAllMouseHandlers getEnteteAllMouseHandler() {
        return blocSyntheseDepliant.getEnteteAllMouseHandlers();
    }

    @Override
    public void openBlocSyntheseDepliant(boolean open) {
        blocSyntheseDepliant.setOpen(true);
    }

    @Override
    public void appliquerStylePastel(boolean set) {
        if (set) {
            blocSyntheseDepliant.addStyleName(SquareResources.INSTANCE.css().blocSyntheseDepliantActionPastel());
        } else {
            blocSyntheseDepliant.removeStyleName(SquareResources.INSTANCE.css().blocSyntheseDepliantActionPastel());
        }
    }

    @Override
    public HasText getLNatureContact() {
        return lNatureContact;
    }

    @Override
    public HasText getLCampagne() {
        return lCampagne;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbPriorite() {
        return slbPriorite;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbPrioriteValue() {
        return slbPriorite;
    }

    @Override
    public void activerPriorite(boolean isEnable) {
        slbPriorite.setEnabled(isEnable);
    }

    @Override
    public HasText getLDateTerminee() {
        return lDateTerminee;
    }

    @Override
    public HasText getLHeureTerminee() {
        return lHeureTerminee;
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

    /**
     * Renvoie la valeur de isAnnuleOuTermine.
     * @return isAnnuleOuTermine
     */
    public boolean isAnnuleOuTermine() {
        return isAnnuleOuTermine;
    }

    /**
     * Modifie isAnnuleOuTermine.
     * @param isAnnuleOuTermine la nouvelle valeur de isAnnuleOuTermine
     */
    public void setAnnuleOuTermine(boolean isAnnuleOuTermine) {
        this.isAnnuleOuTermine = isAnnuleOuTermine;
    }

    @Override
    public boolean isOpen() {
        return blocSyntheseDepliant.isOpen();
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbDuree() {
        return slbDuree;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbDureeValue() {
        return slbDuree;
    }

    @Override
    public HasValueChangeHandlers<Date> getValueChangeHandlerDateAction() {
        return cdbDate;
    }

    @Override
    public HasValueChangeHandlers<String> getValueChangeHandlerHeureAction() {
        return tbfHeureDate;
    }

    @Override
    public HasValueChangeHandlers<IdentifiantLibelleGwt> getValueChangeHandlerDuree() {
        return slbDuree;
    }

    @Override
    public HasKeyUpHandlers getKeyUpHandlerDateAction() {
        return cdbDate;
    }
}
