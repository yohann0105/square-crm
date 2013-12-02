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
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.richtextarea.RichTextPanel;
import org.scub.foundation.framework.gwt.module.client.util.composants.richtextarea.RichTextToolbar;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.presenter.personne.PersonneActionContenuPresenter.PersonneActionContenuView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue du contenu d'une action.
 * @author cblanchard - SCUB
 */
public class PersonneActionContenuViewImpl extends Composite implements PersonneActionContenuView {

    /** Le conteur global. */
    private FlexTable flexTable;

    /** Les constantes. */
    private PersonneActionContenuViewImplConstants constants;

    /** Les constantes de debug. */
    private PersonneActionContenuViewImplDebugIdConstants viewDebudIdConstants;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Label du créateur. */
    private Label lCreateur;

    /** SuggestListBox qui permet de selectionner l'agence à laquelle l'action est attribuée. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbAttributionAgence;

    /** Label de l'agence à laquelle l'action est attribuée. */
    private Label lAgence;

    /** SuggestListBox qui permet de selectionner la ressource de l'agence selectionnée à laquelle l'action est attribuée. */
    private DecoratedSuggestListBoxSingle<DimensionRessourceModel> slbAttributionRessourceAgence;

    /** Label de la ressource à laquelle l'action est attribuée. */
    private Label lRessourceAgence;

    /** Label de la date de création. */
    private Label lDateCreation;

    /** Label de la campagne. */
    private Label lCampagne;

    /** CheckBox de rappel. */
    private CheckBox cbRappel;

    private AideComposant aidecbRappel;

    /** CheckBox de rappel mail. */
    private CheckBox cbRappelMail;

    private AideComposant aidecbRappelMail;

    /** CheckBob pour l'ajout à l'agenda. */
    private CheckBox cbAjoutAgenda;

    /** Composant d'aide pour la CheckBox d'ajout à l'agenda. */
    private AideComposant aideCbAjoutAgenda;

    /** Liste de notification. */
    private ListBox lbNotification;

    /** Toolbar de la zone de texte. */
    private RichTextToolbar rttToolbar;

    /** Zone de texte pour l'action. */
    private RichTextArea rtaDescriptif;

    /** Zone de texte pour l'action. */
    private RichTextPanel rtpCommmentaire;

    /** Zone de texte pour l'historique. */
    private HTML htmlHistorique;

    /** Label du résultat de l'action. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbsResultatAction;

    private AideComposant aideslbsResultatAction;

    /** Label du statut. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbStatut;

    private AideComposant aideslbStatut;

    /** SuggestListBox de résultat de la nature. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbNatureResultat;

    private AideComposant aideslbNatureResultat;

    /** SuggestListBox de résultat de la nature. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbNatureContact;

    private AideComposant aideslbNatureContact;

    /** Panel de l'état de l'appel ou de la visite. */
    private HorizontalPanel panelEtat;

    private Label lEtat;

    /** Label de l'opportunité. */
    private Hyperlink lOpportunite;

    /** Conteneur GED. */
    private CaptionPanel conteneurGed;

    private AideComposant aidelOpportunite;

    private AideComposant aidehtmlHistorique;

    private AideComposant aidertpCommentaire;

    /** mode de connexion admin/normal . */
    private boolean isAdmin;

    /**
     * liste des composants d'aide.
     */
    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    /**
     * listes des events des composants d'aides .
     */
    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    /** Constructeur. */
    public PersonneActionContenuViewImpl(boolean isAdmin) {
        constants = (PersonneActionContenuViewImplConstants) GWT.create(PersonneActionContenuViewImplConstants.class);
        this.isAdmin = isAdmin;
        viewDebudIdConstants = (PersonneActionContenuViewImplDebugIdConstants) GWT.create(PersonneActionContenuViewImplDebugIdConstants.class);

        iconeErreurChampManager = new IconeErreurChampManager();

        flexTable = new FlexTable();
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setCellSpacing(5);

        // Construction du bandeau Affectation / Campagne
        constructionAffectationCampagne();
        // Construction du bandeau de notification
        constructionNotification();

        // Construction du bandeau GED
        conteneurGed = new CaptionPanel(constants.titreGed());
        flexTable.setWidget(2, 0, conteneurGed);
        flexTable.getFlexCellFormatter().setColSpan(2, 0, 2);

        // Construction du bloc action
        constructionAction();

        initWidget(flexTable);
    }

    private void constructionAffectationCampagne() {
        // Partie affectation
        final CaptionPanel captionAffectation = new CaptionPanel(constants.titreCaptionAffectation());
        captionAffectation.setHeight("120px");
        final FlexTable flexTableAffectation = new FlexTable();
        lCreateur = new Label();
        lDateCreation = new Label();
        lAgence = new Label();
        slbAttributionAgence = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {

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

        });
        slbAttributionAgence.ensureDebugId(viewDebudIdConstants.debugIdSlbAttributionAgence());
        lRessourceAgence = new Label();
        slbAttributionRessourceAgence =
            new DecoratedSuggestListBoxSingle<DimensionRessourceModel>(new SuggestListBoxSingleProperties<DimensionRessourceModel>() {
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
                    return new String[] {row == null ? "" : row.getNom(), row.getPrenom()};
                }

            });
        slbAttributionRessourceAgence.ensureDebugId(viewDebudIdConstants.debugIdSlbAttributionRessourceAgence());
        final HorizontalPanel panelAttributionAgence = new HorizontalPanel();
        panelAttributionAgence.add(lAgence);
        panelAttributionAgence.add(slbAttributionAgence);
        final HorizontalPanel panelRessourceAgence = new HorizontalPanel();
        panelRessourceAgence.add(lRessourceAgence);
        panelRessourceAgence.add(slbAttributionRessourceAgence);
        flexTableAffectation.setWidget(0, 0, new Label(constants.libelleCreateur()));
        flexTableAffectation.setWidget(0, 1, lCreateur);
        flexTableAffectation.setWidget(1, 0, new Label(constants.libelleAgence()));
        flexTableAffectation.setWidget(1, 1, panelAttributionAgence);
        flexTableAffectation.setWidget(2, 0, new Label(constants.libelleAttributionRessourceAgence()));
        flexTableAffectation.setWidget(2, 1, panelRessourceAgence);
        flexTableAffectation.setWidget(3, 0, new Label(constants.libelleDateCreation()));
        flexTableAffectation.setWidget(3, 1, lDateCreation);
        flexTableAffectation.setCellSpacing(5);
        captionAffectation.add(flexTableAffectation);

        // Partie campagne
        final CaptionPanel captionCampagne = new CaptionPanel(constants.titreCaptionCampagne());
        captionCampagne.setHeight("120px");
        final FlexTable flexTableCampagne = new FlexTable();
        lCampagne = new Label();
        flexTableCampagne.setWidget(0, 0, new Label(constants.libelleCampagne()));
        flexTableCampagne.setWidget(0, 1, lCampagne);
        flexTableCampagne.setCellSpacing(5);
        captionCampagne.add(flexTableCampagne);

        // Ajout des partie au panel
        flexTable.setWidget(0, 0, captionAffectation);
        flexTable.setWidget(0, 1, captionCampagne);
        flexTable.getCellFormatter().setWidth(0, 0, "50%");
        flexTable.getCellFormatter().setWidth(0, 1, "50%");
    }

    private void constructionNotification() {
        final CaptionPanel captionNotification = new CaptionPanel(constants.titreNotification());
        final VerticalPanel verticalPanelNotificationContenu = new VerticalPanel();

        // Horizontal panel partie notification rappel
        final HorizontalPanel horizontalPanelRappel = new HorizontalPanel();
        cbRappel = new CheckBox();
        cbRappel.ensureDebugId(viewDebudIdConstants.debugIdCbRappel());
        aidecbRappel = new AideComposant(AideComposantConstants.AIDE_PERSONNE_ACTION_RAPPEL, isAdmin);
        ajouterAideComposant(aidecbRappel);
        lbNotification = new ListBox();
        lbNotification.ensureDebugId(viewDebudIdConstants.debugIdLbNotification());
        horizontalPanelRappel.add(aidecbRappel);
        horizontalPanelRappel.add(cbRappel);
        final Label libelleRappel = new Label(constants.libelleRappel());
        horizontalPanelRappel.add(libelleRappel);
        horizontalPanelRappel.setCellVerticalAlignment(libelleRappel, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelRappel.setCellVerticalAlignment(aidecbRappel, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelRappel.add(lbNotification);
        final Label libelleRappelAvant = new Label(constants.libelleRappelAvant());
        horizontalPanelRappel.add(libelleRappelAvant);
        horizontalPanelRappel.setCellVerticalAlignment(libelleRappelAvant, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelRappel.setSpacing(2);

        // Horizontal panel partie notification Mail
        final HorizontalPanel horizontalPanelMail = new HorizontalPanel();
        cbRappelMail = new CheckBox();
        aidecbRappelMail = new AideComposant(AideComposantConstants.AIDE_PERSONNE_ACTION_RAPPEL_MAIL, isAdmin);
        ajouterAideComposant(aidecbRappelMail);
        horizontalPanelMail.add(aidecbRappelMail);
        horizontalPanelMail.add(cbRappelMail);
        cbRappelMail.ensureDebugId(viewDebudIdConstants.debugIdCbRappelMail());
        final Label libelleRappelMail = new Label(constants.rappelMail());
        horizontalPanelMail.add(libelleRappelMail);
        horizontalPanelMail.setCellVerticalAlignment(libelleRappelMail, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelMail.setCellVerticalAlignment(aidecbRappelMail, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelMail.setSpacing(2);

        // Horizontal panel partie notification ajout agenda
        final HorizontalPanel horizontalPanelAjoutAgenda = new HorizontalPanel();
        cbAjoutAgenda = new CheckBox();
        aideCbAjoutAgenda = new AideComposant(106010L, isAdmin);
        ajouterAideComposant(aideCbAjoutAgenda);
        horizontalPanelAjoutAgenda.add(aideCbAjoutAgenda);
        horizontalPanelAjoutAgenda.add(cbAjoutAgenda);
        cbAjoutAgenda.ensureDebugId(viewDebudIdConstants.debugIdCbAjoutAgenda());
        final Label libelleAjoutAgenda = new Label(constants.ajoutAgenda());
        horizontalPanelAjoutAgenda.add(libelleAjoutAgenda);
        horizontalPanelAjoutAgenda.setCellVerticalAlignment(libelleAjoutAgenda, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelAjoutAgenda.setCellVerticalAlignment(aideCbAjoutAgenda, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelAjoutAgenda.setSpacing(2);

        verticalPanelNotificationContenu.add(horizontalPanelRappel);
        verticalPanelNotificationContenu.add(horizontalPanelMail);
        verticalPanelNotificationContenu.add(horizontalPanelAjoutAgenda);
        captionNotification.add(verticalPanelNotificationContenu);
        flexTable.setWidget(1, 0, captionNotification);
        flexTable.getFlexCellFormatter().setColSpan(1, 0, 2);
    }

    private void constructionAction() {
        final CaptionPanel captionAction = new CaptionPanel(constants.titreAction());

        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> properties = new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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

        // Construction partie nature, résultat
        final FlexTable ftNatureResultat = new FlexTable();
        ftNatureResultat.setWidth(AppControllerConstants.POURCENT_100);
        ftNatureResultat.getColumnFormatter().setWidth(0, "15%");
        ftNatureResultat.getColumnFormatter().setWidth(1, "35%");
        ftNatureResultat.getColumnFormatter().setWidth(2, "15%");
        ftNatureResultat.getColumnFormatter().setWidth(3, "35%");
        ftNatureResultat.setCellSpacing(5);

        // Nature
        slbNatureContact = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(properties);
        slbNatureContact.ensureDebugId(viewDebudIdConstants.debugIdSlbsNatureContactAction());
        aideslbNatureContact = new AideComposant(AideComposantConstants.AIDE_PERSONNE_ACTION_NATURE_CONTACT, isAdmin);
        ajouterAideComposant(aideslbNatureContact);
        slbNatureResultat = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(properties);
        slbNatureResultat.ensureDebugId(viewDebudIdConstants.debugIdSlbsNatureResultatAction());
        aideslbNatureResultat = new AideComposant(AideComposantConstants.AIDE_PERSONNE_ACTION_NATURE_RESULTAT, isAdmin);
        ajouterAideComposant(aideslbNatureResultat);
        // aideslbNatureContact = new AideComposant(AideComposantConstants.AIDE_PERSONNE_ACTION_NATURE_CONTACT, isAdmin);
        // ajouterAideComposant(aideslbNatureContact);
        final HorizontalPanel hpNatureContact = new HorizontalPanel();
        hpNatureContact.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        hpNatureContact.add(slbNatureContact);
        hpNatureContact.add(iconeErreurChampManager.createInstance("ActionModificationDto.nature", slbNatureContact));
        hpNatureContact.add(aideslbNatureContact);
        hpNatureContact.setSpacing(2);
        lEtat = new Label("");
        panelEtat = new HorizontalPanel();
        panelEtat.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        panelEtat.add(slbNatureResultat);
        panelEtat.add(iconeErreurChampManager.createInstance("ActionModificationDto.natureResultat", slbNatureResultat));
        panelEtat.add(aideslbNatureResultat);
        panelEtat.setSpacing(2);

        // Opportunité
        final Label ltitreOpportunite = new Label(constants.libelleOpportunite());
        lOpportunite = new Hyperlink();
        lOpportunite.setStyleName("lienSquare");
        aidelOpportunite = new AideComposant(AideComposantConstants.AIDE_PERSONNE_ACTION_OPPORTUNITE, isAdmin);
        ajouterAideComposant(aidelOpportunite);
        final HorizontalPanel panelLink = new HorizontalPanel();
        panelLink.add(lOpportunite);
        panelLink.add(aidelOpportunite);
        panelLink.setCellVerticalAlignment(aidelOpportunite, HasVerticalAlignment.ALIGN_MIDDLE);
        panelLink.setCellVerticalAlignment(lOpportunite, HasVerticalAlignment.ALIGN_MIDDLE);
        panelLink.setSpacing(10);
        // Statut de l'action
        final HorizontalPanel panelStatut = new HorizontalPanel();
        panelStatut.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        panelStatut.setSpacing(2);
        final Label libelleStatut = new Label(constants.libelleStatut());
        slbStatut = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(properties);
        slbStatut.ensureDebugId(viewDebudIdConstants.debugIdSlbStatut());
        aideslbStatut = new AideComposant(AideComposantConstants.AIDE_PERSONNE_ACTION_STATUT, isAdmin);
        ajouterAideComposant(aideslbStatut);
        panelStatut.add(slbStatut);
        panelStatut.add(iconeErreurChampManager.createInstance("ActionModificationDto.statut", slbStatut));
        panelStatut.add(aideslbStatut);

        // Résultat de l'action
        slbsResultatAction = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(properties);
        slbsResultatAction.ensureDebugId(viewDebudIdConstants.debugIdSlbsResultatAction());
        aideslbsResultatAction = new AideComposant(AideComposantConstants.AIDE_PERSONNE_ACTION_RESULTAT_ACTION, isAdmin);
        ajouterAideComposant(aideslbsResultatAction);
        final HorizontalPanel panelResultatAction = new HorizontalPanel();
        panelResultatAction.add(slbsResultatAction);
        panelResultatAction.add(aideslbsResultatAction);
        panelResultatAction.setSpacing(10);

        final Label libelleResultat = new Label(constants.libelleResultat());

        // Construction zone de text pour l'historique des actions précédentes
        htmlHistorique = new HTML();
        htmlHistorique.ensureDebugId(viewDebudIdConstants.debugIdTaHistorique());
        htmlHistorique.setWidth(AppControllerConstants.POURCENT_100);
        aidehtmlHistorique = new AideComposant(AideComposantConstants.AIDE_PERSONNE_ACTION_HISTORIQUE, isAdmin);
        ajouterAideComposant(aidehtmlHistorique);

        final ScrollPanel conteneurHistorique = new ScrollPanel();
        conteneurHistorique.setStylePrimaryName(SquareResources.INSTANCE.css().historiqueAction());
        conteneurHistorique.setWidth(AppControllerConstants.POURCENT_100);
        conteneurHistorique.setHeight("100px");
        conteneurHistorique.add(htmlHistorique);

        // Zone de texte
        rtaDescriptif = new RichTextArea();
        rtaDescriptif.ensureDebugId(viewDebudIdConstants.debugIdRtaDescriptif());
        rttToolbar = new RichTextToolbar(rtaDescriptif, RichTextToolbar.BOLD, RichTextToolbar.ITALIC, RichTextToolbar.UNDERLINE);
        rttToolbar.ensureDebugId(viewDebudIdConstants.debugIdRttToolbar());
        rtpCommmentaire = new RichTextPanel(rttToolbar, rtaDescriptif);
        rtpCommmentaire.ensureDebugId(viewDebudIdConstants.debugIdRtpCommmentaire());
        rttToolbar.setWidth(AppControllerConstants.POURCENT_100);
        rtpCommmentaire.setWidth(AppControllerConstants.POURCENT_100);
        aidertpCommentaire = new AideComposant(AideComposantConstants.AIDE_PERSONNE_ACTION_COMMENTAIRE, isAdmin);
        ajouterAideComposant(aidertpCommentaire);

        ftNatureResultat.setWidget(0, 0, new Label(constants.libelleNatureAction()));
        ftNatureResultat.setWidget(0, 1, hpNatureContact);
        ftNatureResultat.setWidget(0, 2, lEtat);
        ftNatureResultat.setWidget(0, 3, panelEtat);
        ftNatureResultat.setWidget(1, 0, ltitreOpportunite);
        ftNatureResultat.setWidget(1, 1, panelLink);
        ftNatureResultat.setWidget(2, 0, libelleStatut);
        ftNatureResultat.setWidget(2, 1, panelStatut);
        ftNatureResultat.setWidget(3, 0, libelleResultat);
        ftNatureResultat.setWidget(3, 1, panelResultatAction);
        ftNatureResultat.setWidget(4, 0, new Label(constants.notes()));
        ftNatureResultat.getFlexCellFormatter().setColSpan(4, 0, 4);
        ftNatureResultat.setWidget(5, 0, aidehtmlHistorique);
        ftNatureResultat.setWidget(6, 0, conteneurHistorique);
        ftNatureResultat.getFlexCellFormatter().setColSpan(6, 0, 4);
        ftNatureResultat.setWidget(7, 0, aidertpCommentaire);
        ftNatureResultat.setWidget(8, 0, rtpCommmentaire);
        ftNatureResultat.getFlexCellFormatter().setColSpan(8, 0, 4);

        captionAction.add(ftNatureResultat);
        flexTable.setWidget(3, 0, captionAction);
        flexTable.getFlexCellFormatter().setColSpan(3, 0, 2);
    }

    /**
     * remplie les listes de composants d'aides.
     * @param aideComposant le composant à ajouter
     */
    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    @Override
    public void disable() {
        disableElement(flexTable.getElement());
        rtpCommmentaire.setEnabled(false);
    }

    /**
     * Permet de desactiver l'ensemble des champs.
     * @param element .
     */
    private void disableElement(Element element) {
        for (int index = 0; index < DOM.getChildCount(element); index++) {
            final Element nested = DOM.getChild(element, index);
            disableElement(nested);
            DOM.setElementPropertyBoolean(nested, "disabled", true);
        }
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public PersonneActionContenuViewImplConstants getConstants() {
        return constants;
    }

    @Override
    public HasText getlCreateur() {
        return lCreateur;
    }

    @Override
    public HasText getlAgence() {
        return lAgence;
    }

    @Override
    public HasText getlDateCreation() {
        return lDateCreation;
    }

    @Override
    public HasText getlCampagne() {
        return lCampagne;
    }

    @Override
    public CheckBox getCbRappel() {
        return cbRappel;
    }

    @Override
    public CheckBox getCbRappelMail() {
        return cbRappelMail;
    }

    @Override
    public ListBox getLbNotification() {
        return lbNotification;
    }

    @Override
    public RichTextToolbar getRttToolbar() {
        return rttToolbar;
    }

    @Override
    public RichTextArea getRtaDescriptif() {
        return rtaDescriptif;
    }

    @Override
    public HasHTML getTaHistorique() {
        return htmlHistorique;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbsResultatAction() {
        return slbsResultatAction;
    }

    @Override
    public HasText getlOpportunite() {
        return lOpportunite;
    }

    @Override
    public HasClickHandlers getlOpportuniteHandler() {
        return lOpportunite;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbStatut() {
        return slbStatut;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureResultat() {
        return slbNatureResultat;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbNatureResultatValue() {
        return slbNatureResultat;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureContact() {
        return slbNatureContact;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbNatureContactValue() {
        return slbNatureContact;
    }

    @Override
    public void afficherListeNaturesResultat(boolean affiche) {
        panelEtat.setVisible(affiche);
        lEtat.setVisible(affiche);
    }

    @Override
    public HasWidgets getConteneurGed() {
        return conteneurGed;
    }

    @Override
    public IdentifiantLibelleGwt getAttributionAgence() {
        return slbAttributionAgence.getValue();
    }

    @Override
    public DimensionRessourceModel getRessourceAgence() {
        return slbAttributionRessourceAgence.getValue();
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestAttributionAgence() {
        return slbAttributionAgence;
    }

    @Override
    public HasSuggestListBoxHandler<DimensionRessourceModel> getSuggestRessourceAgence() {
        return slbAttributionRessourceAgence;
    }

    @Override
    public HasValueChangeHandlers<DimensionRessourceModel> getSuggestRessourceChangeHandler() {
        return slbAttributionRessourceAgence;
    }

    @Override
    public HasText getLRessourceAgence() {
        return lRessourceAgence;
    }

    @Override
    public HasText getLEtat() {
        return lEtat;
    }

    @Override
    public void afficherListeAgence(boolean afficherListe) {
        slbAttributionAgence.setVisible(afficherListe);
        lAgence.setVisible(!afficherListe);
    }

    @Override
    public void afficherListeRessourceAgence(boolean afficherListe) {
        slbAttributionRessourceAgence.setVisible(afficherListe);
        lRessourceAgence.setVisible(!afficherListe);
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbAttributionAgence() {
        return slbAttributionAgence;
    }

    @Override
    public HasValue<DimensionRessourceModel> getSlbRessourceAgence() {
        return slbAttributionRessourceAgence;
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
    public HasValue<Boolean> getCbAjoutAgenda() {
        return cbAjoutAgenda;
    }

    @Override
    public void activerAjoutAgenda(boolean enabled) {
        cbAjoutAgenda.setEnabled(enabled);
    }
}
