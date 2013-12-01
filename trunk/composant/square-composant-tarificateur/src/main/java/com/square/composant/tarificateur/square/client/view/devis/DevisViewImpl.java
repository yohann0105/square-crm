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
package com.square.composant.tarificateur.square.client.view.devis;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.presenter.devis.DevisPresenter.DevisView;
import com.square.composant.tarificateur.square.client.presenter.ligne.devis.LigneDevisPresenter.LigneDevisView;
import com.square.composant.tarificateur.square.client.view.ligne.devis.LigneDevisViewImpl;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Vue des devis.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class DevisViewImpl extends Composite implements DevisView {

    /** View constants. */
    private static DevisViewImplConstants viewConstants;

    /** Bouton pour enregistrer un devis. */
    private DecoratedButton btnEnregistrerDevis;

    /** Bouton pour enregistrer un devis. */
    private DecoratedButton btnAjouterProduit;

    /** Bouton pour envoyer un devis par mail. */
    private DecoratedButton btnEnvoyerDevisMail;

    /** Bouton pour imprimer un devis. */
    private DecoratedButton btnImprimerDevis;

    /** Bouton pour transformer en devis aia. */
    private DecoratedButton btnTransformerDevisAia;

    /** Bouton pour transfert vers une nouvelle opportunité. */
    private DecoratedButton btnTransfererNouveauDevis;

    /** Bouton pour afficher la popup d'informations d'adhésion. */
    private DecoratedButton btnInformationsAdhesion;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> lbMotifDevis;

    private VerticalPanel blocEntete;

    private Label lFinaliteDevis;

    private HorizontalPanel blocFinaliteDevis;

    private VerticalPanel conteneurLignesDevis;

    private Image iconeWarningFamilleDifferente;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /**
     * Constructeur.
     */
    public DevisViewImpl() {
        viewConstants = GWT.create(DevisViewImplConstants.class);
        iconeErreurChampManager = new IconeErreurChampManager();

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.addStyleName(ComposantTarificateur.RESOURCES.css().ficheDevis());
        conteneur.setWidth(ComposantTarificateurConstants.POURCENT_100);
        conteneur.setSpacing(5);

        creerBlocEntete();

        conteneurLignesDevis = new VerticalPanel();
        conteneurLignesDevis.setWidth(ComposantTarificateurConstants.POURCENT_100);
        conteneurLignesDevis.setSpacing(5);

        conteneur.add(blocEntete);
        conteneur.add(conteneurLignesDevis);

        this.initWidget(conteneur);
        this.setWidth(ComposantTarificateurConstants.POURCENT_100);
    }

    private void creerBlocEntete() {
        btnEnregistrerDevis = new DecoratedButton(viewConstants.libelleBoutonEnregistrer());
        btnAjouterProduit = new DecoratedButton(viewConstants.libelleBoutonAjouterProduit());
        btnAjouterProduit.setTitle(viewConstants.titleBoutonAjouterProduit());
        btnEnvoyerDevisMail = new DecoratedButton(viewConstants.libelleBoutonEnvoyerMail());
        btnImprimerDevis = new DecoratedButton(viewConstants.libelleBoutonImprimer());
        btnTransformerDevisAia = new DecoratedButton(viewConstants.libelleBoutonTransformerDevisAia());
        btnTransformerDevisAia.setTitle(viewConstants.titleBoutonTransformerDevisAia());
        btnTransfererNouveauDevis = new DecoratedButton(viewConstants.libelleBoutonTransfertNouveauDevis());
        btnTransfererNouveauDevis.setTitle(viewConstants.titleBoutonTransfertNouveauDevis());
        btnInformationsAdhesion = new DecoratedButton(viewConstants.libelleBoutonInformationsAdhesion());
        btnInformationsAdhesion.setTitle(viewConstants.titleBoutonInformationsAdhesion());
        btnTransformerDevisAia.setEnabled(false);
        btnTransfererNouveauDevis.setEnabled(false);

        final HorizontalPanel buttonBar = new HorizontalPanel();
        buttonBar.setSpacing(5);
        buttonBar.add(btnEnregistrerDevis);
        buttonBar.add(btnAjouterProduit);
        buttonBar.add(btnImprimerDevis);
        buttonBar.add(btnEnvoyerDevisMail);
        buttonBar.add(btnTransformerDevisAia);
        buttonBar.add(btnTransfererNouveauDevis);
        buttonBar.add(btnInformationsAdhesion);

        lbMotifDevis = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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
        final HorizontalPanel pMotifDevis = creerBlocLibelleValeur(viewConstants.labelMotifDevis(), construireBlocIcone(lbMotifDevis, "idMotifDevis"));
        pMotifDevis.setSpacing(5);

        final Label libelleFinaliteDevis = new Label(viewConstants.libelleFinaliteDevis());
        libelleFinaliteDevis.setWordWrap(false);
        lFinaliteDevis = new Label();
        lFinaliteDevis.setWordWrap(false);
        blocFinaliteDevis = new HorizontalPanel();
        blocFinaliteDevis.setSpacing(2);
        blocFinaliteDevis.add(libelleFinaliteDevis);
        blocFinaliteDevis.add(lFinaliteDevis);

        iconeWarningFamilleDifferente = new Image(ComposantTarificateur.RESOURCES.iconeWarning());

        final HorizontalPanel conteneurLigne2 = new HorizontalPanel();
        conteneurLigne2.setSpacing(5);
        conteneurLigne2.add(pMotifDevis);
        conteneurLigne2.add(iconeWarningFamilleDifferente);
        conteneurLigne2.add(blocFinaliteDevis);
        conteneurLigne2.setCellVerticalAlignment(pMotifDevis, HasVerticalAlignment.ALIGN_MIDDLE);
        conteneurLigne2.setCellVerticalAlignment(iconeWarningFamilleDifferente, HasVerticalAlignment.ALIGN_MIDDLE);
        conteneurLigne2.setCellVerticalAlignment(blocFinaliteDevis, HasVerticalAlignment.ALIGN_MIDDLE);

        // Menu Bar
        blocEntete = new VerticalPanel();
        blocEntete.addStyleName(ComposantTarificateur.RESOURCES.css().blocEnteteDevis());
        blocEntete.setWidth(ComposantTarificateurConstants.POURCENT_100);
        blocEntete.add(buttonBar);
        blocEntete.add(conteneurLigne2);
        blocEntete.setCellHorizontalAlignment(conteneurLigne2, HasHorizontalAlignment.ALIGN_RIGHT);
    }

    private HorizontalPanel creerBlocLibelleValeur(String libelle, Widget wValeur) {
        final Label lLibelle = new Label(libelle);
        lLibelle.setWordWrap(false);
        lLibelle.addStyleName(ComposantTarificateur.RESOURCES.css().important());
        if (wValeur instanceof HasText) {
            wValeur.addStyleName(ComposantTarificateur.RESOURCES.css().important());
        }
        final HorizontalPanel conteneurBloc = new HorizontalPanel();
        conteneurBloc.add(lLibelle);
        conteneurBloc.add(wValeur);
        conteneurBloc.setCellVerticalAlignment(lLibelle, HasVerticalAlignment.ALIGN_MIDDLE);
        conteneurBloc.setCellVerticalAlignment(wValeur, HasVerticalAlignment.ALIGN_MIDDLE);
        return conteneurBloc;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        if (errorPopupConfiguration != null) {
            ErrorPopup.afficher(errorPopupConfiguration);
        }
    }

    @Override
    public HasClickHandlers getBtnAjouterProduit() {
        return btnAjouterProduit;
    }

    @Override
    public HasClickHandlers getBtnEnregistrerDevis() {
        return btnEnregistrerDevis;
    }

    @Override
    public HasClickHandlers getBtnEnvoyerDevisMail() {
        return btnEnvoyerDevisMail;
    }

    @Override
    public HasClickHandlers getBtnImprimerDevis() {
        return btnImprimerDevis;
    }

    @Override
    public HasClickHandlers getBtnTransfererNouveauDevis() {
        return btnTransfererNouveauDevis;
    }

    @Override
    public HasClickHandlers getBtnTransformerDevisAia() {
        return btnTransformerDevisAia;
    }

    @Override
    public HasClickHandlers getBtnInformationsAdhesion() {
        return btnInformationsAdhesion;
    }

    @Override
    public void activerBtnAjouterProduit(boolean actif) {
        btnAjouterProduit.setEnabled(actif);
    }

    @Override
    public void activerBtnTransformerDevisAia(boolean actif) {
        btnTransformerDevisAia.setEnabled(actif);
    }

    @Override
    public HasText getLibelleFinaliteDevis() {
        return lFinaliteDevis;
    }

    @Override
    public void setStylePrimaryNameFinaliteDevis(String styleName) {
        blocFinaliteDevis.setStylePrimaryName(styleName);
    }

    @Override
    public HasWidgets getConteneurLigneDevis() {
        final SimplePanel conteneurUneLigneDevis = new SimplePanel();
        conteneurUneLigneDevis.setWidth(ComposantTarificateurConstants.POURCENT_100);
        conteneurLignesDevis.add(conteneurUneLigneDevis);
        return conteneurUneLigneDevis;
    }

    @Override
    public void activerBtnTransfererNouveauDevis(boolean actif) {
        btnTransfererNouveauDevis.setEnabled(actif);
    }

    @Override
    public void activerLbMotifDevis(boolean actif) {
        lbMotifDevis.setEnabled(actif);
    }

    @Override
    public LigneDevisView creerLigneDevisView(ConstantesModel constantesApp) {
        return new LigneDevisViewImpl(constantesApp);
    }

    @Override
    public void afficherPopupLoadingEnregistrementDevis() {
        final LoadingPopupConfiguration config = new LoadingPopupConfiguration(viewConstants.enregistrementDevis());
        LoadingPopup.afficher(config);
    }

    @Override
    public void stopperPopupChargement() {
        LoadingPopup.stop();
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getMotifDevis() {
        return lbMotifDevis;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getMotifDevisSuggestHandler() {
        return lbMotifDevis;
    }

    @Override
    public void afficherPopupLoadingTransfertDevis() {
        final LoadingPopupConfiguration config = new LoadingPopupConfiguration(viewConstants.transfertDevis());
        LoadingPopup.afficher(config);
    }

    @Override
    public void afficherConfirmationTransfert() {
        final PopupInfoConfiguration config = new PopupInfoConfiguration(viewConstants.messagePopupSuccesTransfertDevis(),
            ComposantTarificateurConstants.NOTIFICATION_TIME_OUT);
        new DecoratedInfoPopup(config).show();
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        panel.add(icone);
        panel.setCellVerticalAlignment(icone, HasAlignment.ALIGN_MIDDLE);
        return panel;
    }

    @Override
    public void afficherIconeAlerteFamilleDifferente(boolean visible) {
        iconeWarningFamilleDifferente.setVisible(visible);
    }

    @Override
    public void activerBtnImprimerDevis(boolean actif) {
        btnImprimerDevis.setEnabled(actif);
    }

    @Override
    public void activerBtnEnvoiEmailDevis(boolean actif) {
        btnEnvoyerDevisMail.setEnabled(actif);
    }

    @Override
    public void activerBtnEnregistrerDevis(boolean actif) {
        btnEnregistrerDevis.setEnabled(actif);
    }

    @Override
    public Image getIconeWarningFamilleDifferente() {
        return iconeWarningFamilleDifferente;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public void nettoyer() {
        conteneurLignesDevis.clear();
    }
}
