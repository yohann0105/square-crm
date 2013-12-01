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
package com.square.composant.envoi.email.square.client.view;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.confirm.ConfirmPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.PopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.envoi.email.square.client.composant.RichTextPanel;
import com.square.composant.envoi.email.square.client.composant.RichTextToolbar;
import com.square.composant.envoi.email.square.client.constantes.ComposantEnvoiEmailConstants;
import com.square.composant.envoi.email.square.client.event.SuppressionPieceJointeEvent;
import com.square.composant.envoi.email.square.client.model.PieceJointeModel;
import com.square.composant.envoi.email.square.client.presenter.ComposantEnvoiEmailPresenter.ComposantEnvoiEmailView;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Implémentation de la vue ComposantEnvoiEmailView.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ComposantEnvoiEmailViewImpl extends Composite implements ComposantEnvoiEmailView {

    /** DecoratedTextBox pour l'adresse mail de l'expéditeur. */
    private DecoratedTextBox tbExpediteur;

    /** DecoratedTextBox pour le nom de l'expéditeur. */
    private DecoratedTextBox tbNomExpediteur;

    /** DecoratedTextBox pour l'adresse mail du destinataire. */
    private DecoratedTextBox tbDestinataire;

    /** DecoratedTextBox pour l'objet du mail. */
    private DecoratedTextBox tbObjet;

    /** SuggestListBox pour le modele d'email. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbModeleEmail;

    /** Zone de texte du message du mail. */
    private RichTextArea rtaMessage;

    /** Toolbar associée à la RichTextArea. */
    private RichTextToolbar toolbar;

    /** Bouton pour envoyer l'email. */
    private DecoratedButton btnEnvoyerEmail;

    private DecoratedButton btnReduire;

    /** Bouton pour ajouter une pièce jointe. */
    private DecoratedButton btnJoindreFichier;

    /** Bouton d'annulation. */
    private DecoratedButton btnAnnuler;

    /** Tableau des fichiers joints. */
    private FlexTable panelFichiersJoints;

    /** Label "Aucun fichier joint". */
    private Label labelAucunFichierJoint;

    /** Bus d'évènements. */
    private HandlerManager eventBus;

    /** Constantes de la vue. */
    private ComposantEnvoiEmailViewImplConstants viewConstants;

    /** Messages de la vue. */
    private ComposantEnvoiEmailViewImplMessages viewMessage;

    private FocusPanel focusPopupPanel;

    /** Icone manager. */
    private IconeErreurChampManager iconeErreurChampManager;

    private boolean minimisable;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènement.
     * @param minimisable flag pour savoir si on ajoute le bouton reduire
     */
    public ComposantEnvoiEmailViewImpl(HandlerManager eventBus, boolean minimisable) {
        this.viewConstants = GWT.create(ComposantEnvoiEmailViewImplConstants.class);
        this.viewMessage = GWT.create(ComposantEnvoiEmailViewImplMessages.class);
        this.eventBus = eventBus;
        iconeErreurChampManager = new IconeErreurChampManager();
        this.minimisable = minimisable;

        initContenuView();
    }

    /** Initialisation du contenu de la vue. */
    private void initContenuView() {
        focusPopupPanel = new FocusPanel();
        focusPopupPanel.setWidth(ComposantEnvoiEmailConstants.CENT_POURCENT);
        // Panel représentant le contenu du composant
        final VerticalPanel contenu = new VerticalPanel();
        contenu.setWidth(ComposantEnvoiEmailConstants.CENT_POURCENT);
        contenu.setSpacing(5);

        // Email
        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.titreBlocComposant());
        final VerticalPanel panelContenuEmail = new VerticalPanel();
        panelContenuEmail.setWidth(ComposantEnvoiEmailConstants.CENT_POURCENT);
        int row = 0;
        // Table contenant les informations du mail
        final FlexTable tableInfos = new FlexTable();
        final Label lExpediteur = new Label(viewConstants.expediteur());
        tableInfos.setWidget(row, 0, lExpediteur);
        tbExpediteur = new DecoratedTextBox();
        tableInfos.setWidget(row++, 1, construireBlocIcone(tbExpediteur, "EmailDto.expediteur"));
        final Label lNomExpediteur = new Label(viewConstants.nomExpediteur());
        tableInfos.setWidget(row, 0, lNomExpediteur);
        tbNomExpediteur = new DecoratedTextBox();
        tableInfos.setWidget(row++, 1, tbNomExpediteur);
        final Label lDestinataire = new Label(viewConstants.destinataire());
        tableInfos.setWidget(row, 0, lDestinataire);
        tbDestinataire = new DecoratedTextBox();
        tableInfos.setWidget(row++, 1, construireBlocIcone(tbDestinataire, "EmailDto.listeDestinataires"));
        final Label lObjet = new Label(viewConstants.objet());
        tableInfos.setWidget(row, 0, lObjet);
        tbObjet = new DecoratedTextBox();
        tableInfos.setWidget(row++, 1, construireBlocIcone(tbObjet, "EmailDto.titre"));
        // Panneau des fichiers joints
        final Label lPieceJointe = new Label(viewConstants.pieceJointe());
        tableInfos.setWidget(row, 0, lPieceJointe);
        tableInfos.getCellFormatter().setVerticalAlignment(row, 0, HasAlignment.ALIGN_TOP);
        panelFichiersJoints = new FlexTable();
        labelAucunFichierJoint = new Label(viewConstants.aucunFichierJoint());
        panelFichiersJoints.setWidget(0, 0, labelAucunFichierJoint);
        tableInfos.setWidget(row++, 1, panelFichiersJoints);
        final Label lMessage = new Label(viewConstants.message());
        tableInfos.setWidget(row++, 0, lMessage);

        // Colonne vide (espace)
        tableInfos.getColumnFormatter().setWidth(2, ComposantEnvoiEmailViewImplConstants.LARGEUR_COLONNE_VIDE);

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
        final Label lModeleEmail = new Label(viewConstants.modeleEmail());
        tableInfos.setWidget(0, 3, lModeleEmail);
        slbModeleEmail = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        tableInfos.setWidget(0, 4, slbModeleEmail);

        panelContenuEmail.add(tableInfos);

        // création de la zone de texte (RichTextArea)
        rtaMessage = new RichTextArea();
        rtaMessage.setSize(ComposantEnvoiEmailConstants.CENT_POURCENT, ComposantEnvoiEmailViewImplConstants.HAUTEUR_RICH_TEXT_AREA);
        toolbar = new RichTextToolbar(rtaMessage);
        final  RichTextPanel rtpMessage = new RichTextPanel(toolbar, rtaMessage);
        rtpMessage.setWidth(ComposantEnvoiEmailConstants.CENT_POURCENT);
        toolbar.setWidth(ComposantEnvoiEmailConstants.CENT_POURCENT);
        panelContenuEmail.add(rtpMessage);

        fieldSetPanel.add(panelContenuEmail);

        contenu.add(fieldSetPanel);

        // Ajout de la barre de boutons
        final HorizontalPanel pBoutons = construireBarreBoutons();
        contenu.add(pBoutons);
        contenu.setCellHorizontalAlignment(pBoutons, HasAlignment.ALIGN_CENTER);

        focusPopupPanel.setWidget(contenu);
        this.initWidget(focusPopupPanel);
    }

    /**
     * Construit la barre de boutons.
     * @return le panel contenant les boutons.
     */
    private HorizontalPanel construireBarreBoutons() {
        btnEnvoyerEmail = new DecoratedButton(viewConstants.btnEnvoyerEmail());
        btnJoindreFichier = new DecoratedButton(viewConstants.btnJoindreFichier());
        if (minimisable) {
            btnReduire = new DecoratedButton(viewConstants.reduire());
        }
        btnAnnuler = new DecoratedButton(viewConstants.btnAnnuler());

        final HorizontalPanel barreBoutons = new HorizontalPanel();
        barreBoutons.setSpacing(5);
        barreBoutons.add(btnEnvoyerEmail);
        barreBoutons.add(btnJoindreFichier);
        if (minimisable) {
            barreBoutons.add(btnReduire);
        }
        barreBoutons.add(btnAnnuler);

        return barreBoutons;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void afficherFichierJoint(final PieceJointeModel fichierJoint, boolean supprimable) {
        // Récupération de l'index de la ligne du panel à laquelle on ajoute le fichier
        final int ligneCourante = panelFichiersJoints.getRowCount();

        // Ajout du fichier au panel des fichiers joints
        final Label labelNomFichierJoint = new Label(fichierJoint.getNom());
        panelFichiersJoints.setWidget(ligneCourante, 0, labelNomFichierJoint);
        // Affichage du bouton supprimer si le fichier est supprimable
        if (supprimable) {
        	// Lien de suppression du fichier joint
        	final DecoratedButton btnSuppression = new DecoratedButton(viewConstants.btnSupprimer());
        	btnSuppression.addClickHandler(new ClickHandler() {
        		@Override
        		public void onClick(ClickEvent event) {
        			final PopupCallback callback = new PopupCallback() {
        				public void onResult(boolean result) {
        					if (result) {
        						eventBus.fireEvent(new SuppressionPieceJointeEvent(fichierJoint, labelNomFichierJoint));
        					}
        				}
        			};
        			final PopupConfiguration config = new PopupConfiguration(viewMessage.messageSuppressionPieceJointe(fichierJoint.getNom()));
        			config.setTitle(viewConstants.titrePopupConfirmationSuppressionPieceJointe());
        			config.setCallback(callback);
        			ConfirmPopup.afficher(config);
        		}
        	});
        	panelFichiersJoints.setWidget(ligneCourante, 1, btnSuppression);
        }

        // On cache "Aucun fichier joint"
        labelAucunFichierJoint.setVisible(false);
    }

    @Override
    public void afficherAucunePieceJointe() {
        labelAucunFichierJoint.setVisible(true);
    }

    @Override
    public void nettoyerPiecesJointes() {
        panelFichiersJoints.clear();
    }

    @Override
    public void supprimerPieceJointe(Label labelPieceJointe) {
        final int idxLigneNomFichierJoint = trouverLigneFichierJoint(labelPieceJointe);
        if (idxLigneNomFichierJoint < 0) {
            LoadingPopup.stopAll();
            final ErrorPopupConfiguration errorPopupConfiguration = new ErrorPopupConfiguration(viewConstants.erreurSuppresionPieceJointe());
            ErrorPopup.afficher(errorPopupConfiguration);
        }
        else {
            panelFichiersJoints.removeRow(idxLigneNomFichierJoint);
        }
    }

    /**
     * Recherche du Label du fichier dans le tableau des fichiers joints.
     * @param labelNomFichier le widget à rechercher
     * @return l'index de la ligne où se trouve le widget
     */
    private int trouverLigneFichierJoint(Label labelNomFichier) {
        if (labelNomFichier != null) {
            // Parcours du tableau des fichiers joints pour retrouver le widget
            for (int i = 0; i < panelFichiersJoints.getRowCount(); i++) {
                // Si le label correspond : on retourne l'index de la ligne
                if (labelNomFichier.equals(panelFichiersJoints.getWidget(i, 0))) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        panel.add(icone);
        panel.setCellVerticalAlignment(icone, HasVerticalAlignment.ALIGN_MIDDLE);
        return panel;
    }

    @Override
    public HasClickHandlers getBtnAnnuler() {
        return btnAnnuler;
    }

    @Override
    public HasClickHandlers getBtnEnvoyerEmail() {
        return btnEnvoyerEmail;
    }

    @Override
    public HasClickHandlers getBtnJoindreFichier() {
        return btnJoindreFichier;
    }

    @Override
    public HasValue<String> getTbDestinataire() {
        return tbDestinataire;
    }

    @Override
    public HasValue<String> getTbExpediteur() {
        return tbExpediteur;
    }

    @Override
    public HasValue<String> getTbNomExpediteur() {
        return tbNomExpediteur;
    }

    @Override
    public HasValue<String> getTbObjet() {
        return tbObjet;
    }

    @Override
    public String getMessage() {
        return rtaMessage.getHTML();
    }

    @Override
    public void setMessage(String message) {
        rtaMessage.setHTML(message);
    }

    @Override
    public void setFocusMessage() {
        rtaMessage.setFocus(true);
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stopAll();
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasAllKeyHandlers getAllKeyHandlersFocusPanel() {
        return focusPopupPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initFocus() {
        btnAnnuler.setFocus(true);
    }

    @Override
    public void activerBoutonReduire(boolean actif) {
        if (minimisable) {
            btnReduire.setEnabled(actif);
        }
    }

    @Override
    public HasClickHandlers getBtnReduireHandler() {
        return btnReduire;
    }

    @Override
    public HasAllKeyHandlers getAllKeyHandlersMessage() {
        return rtaMessage;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbModeleEmail() {
        return slbModeleEmail;
    }
}
