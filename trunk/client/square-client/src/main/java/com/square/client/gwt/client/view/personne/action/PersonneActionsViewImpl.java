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

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.ActionModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.HistoriqueCommentaireModel;
import com.square.client.gwt.client.presenter.personne.PersonneActionsPresenter.PersonneActionsView;
import com.square.client.gwt.client.presenter.personne.PersonneActionsPresenter.PersonneBlocActionView;
import com.square.client.gwt.client.presenter.personne.PersonneActionsPresenter.PersonneInfosActionView;

/**
 * Vue pour les actions d'une personne.
 * @author cblanchard - SCUB
 */
public class PersonneActionsViewImpl extends Composite implements PersonneActionsView {

    private static final String ESPACE = " ";

    /** View constants. */
    private static PersonneActionsViewImplConstants viewConstants = (PersonneActionsViewImplConstants) GWT.create(PersonneActionsViewImplConstants.class);

    /** View constants. */
    private static PersonneActionsViewImplDebugIdConstants viewDebugIdConstants =
        (PersonneActionsViewImplDebugIdConstants) GWT.create(PersonneActionsViewImplDebugIdConstants.class);

    /** Panel global. */
    private VerticalPanel conteneur;

    /** Conteneur des actions. */
    private VerticalPanel conteneurBlocAction;

    /** Infobulle action. */
    private Popup infoBulleAction;

    private DecoratedButton btnAfficherActions;

    private HorizontalPanel conteneurEntete;

    private HTML titreEntete;

    private ConstantesModel constantes;

    /**
     * Constructeur.
     * @param isAdmin flag isAdmin
     * @param constantes constantes
     */
    public PersonneActionsViewImpl(ConstantesModel constantes) {
        conteneur = new VerticalPanel();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);

        conteneurEntete = new HorizontalPanel();
        conteneurEntete.setSpacing(10);
        conteneurEntete.setWidth(AppControllerConstants.POURCENT_100);
        titreEntete = new HTML();
        titreEntete.setStylePrimaryName(SquareResources.INSTANCE.css().titreEnteteBlocsActions());
        btnAfficherActions = new DecoratedButton(viewConstants.libelleBoutonAfficherActions());
        btnAfficherActions.ensureDebugId(viewDebugIdConstants.debugIdBtnAfficherActions());
        conteneurEntete.add(titreEntete);
        conteneurEntete.add(btnAfficherActions);
        conteneurEntete.setCellVerticalAlignment(titreEntete, HasVerticalAlignment.ALIGN_MIDDLE);
        conteneurEntete.setCellHorizontalAlignment(btnAfficherActions, HasHorizontalAlignment.ALIGN_RIGHT);

        conteneurBlocAction = new VerticalPanel();
        conteneurBlocAction.setSpacing(10);
        conteneurBlocAction.setWidth(AppControllerConstants.POURCENT_100);

        conteneur.add(conteneurBlocAction);
        sinkEvents(Event.ONMOUSEMOVE);

        infoBulleAction = new Popup(viewConstants.titleInfoBulle(), true, false, false);
        this.constantes = constantes;
        this.initWidget(conteneur);
        this.setWidth(AppControllerConstants.POURCENT_100);
    }

    @Override
    public void afficherFiltre(String eidOpportunite) {
        titreEntete.setHTML(viewConstants.libelleCaptionPanelActionOpp() + eidOpportunite);
        conteneur.insert(conteneurEntete, 0);
    }

    @Override
    public void masquerFiltre() {
        conteneur.remove(conteneurEntete);
    }

    @Override
    public void effacerListeActions() {
        conteneurBlocAction.clear();
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public PersonneBlocActionView ajouterBlocAction() {
        final PersonneBlocActionViewImpl bloc = new PersonneBlocActionViewImpl(constantes.isHasRoleAdmin());
        conteneurBlocAction.add(bloc);
        return bloc;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public PersonneActionsViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public HasClickHandlers getBtnAfficherActions() {
        return btnAfficherActions;
    }

    @Override
    public void afficherInfoBulleAction(ActionModel action, PersonneInfosActionView personneInfosActionView) {
        final VerticalPanel contenuInfoBulle = new VerticalPanel();
        contenuInfoBulle.setWidth("400px");
        contenuInfoBulle.setSpacing(5);

        final Label libelleDate = new Label();
        if (action.getStatut().getIdentifiant().equals(constantes.getIdStatutTerminer())) {
            libelleDate.setText(viewConstants.aFaireLe() + ESPACE + action.getDateAction() + ", " + viewConstants.faitLe() + ESPACE + action.getDateTerminee());
        } else if (action.getStatut().getIdentifiant().equals(constantes.getIdStatutAnnuler())) {
            libelleDate.setText(viewConstants.le() + ESPACE + action.getDateAction());
        }
        String lValueCreateur = "";
        if (action.getRessource() != null) {
            final IdentifiantLibelleGwt agence = action.getCreateur().getAgence() != null ? action.getCreateur().getAgence() : null;
            lValueCreateur = action.getCreateur().getNom() + ESPACE + action.getCreateur().getPrenom() + (agence != null ? ", " + agence.getLibelle() : "");
        }
        final Label lCreateur = new Label(viewConstants.creePar() + ESPACE + lValueCreateur);

        String lValueRessource = "";
        if (action.getRessource() != null) {
            final IdentifiantLibelleGwt agence = action.getRessource().getAgence() != null ? action.getRessource().getAgence() : action.getAgence();
            lValueRessource = action.getRessource().getNom() + ESPACE + action.getRessource().getPrenom() + (agence != null ? ", " + agence.getLibelle() : "");
        } else if (action.getAgence() != null) {
            lValueRessource = action.getAgence().getLibelle();
        }
        final Label lRessource = new Label(viewConstants.par() + ESPACE + lValueRessource);

        final Label lCommentaires = new Label(viewConstants.commentaires() + ESPACE);
        final VerticalPanel conteneurCommentaires = new VerticalPanel();
        conteneurCommentaires.add(lCommentaires);
        if (action.getCommentaires() != null) {
            for (HistoriqueCommentaireModel ligneHisto : action.getCommentaires()) {
                conteneurCommentaires.add(new HTML(ligneHisto.getDescriptif()));
            }
        }

        contenuInfoBulle.add(libelleDate);
        contenuInfoBulle.add(lCreateur);
        contenuInfoBulle.add(lRessource);
        contenuInfoBulle.add(conteneurCommentaires);

        infoBulleAction.setWidget(contenuInfoBulle, 0);
        infoBulleAction.showRelativeTo(personneInfosActionView.asWidget(), false, personneInfosActionView.asWidget().getOffsetWidth(),
            - (contenuInfoBulle.getOffsetHeight() / 2));
    }

    @Override
    public void masquerInfoBulleAction() {
        infoBulleAction.hide();
    }
}
