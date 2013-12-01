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
package com.square.client.gwt.client.presenter.personne.physique;


import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.PopupStateChangeEvent;
import org.scub.foundation.framework.gwt.module.client.util.popup.PopupStateChangeEventHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.event.CreerPersonneEvent;
import com.square.client.gwt.client.event.CreerPersonneRelationEvent;
import com.square.client.gwt.client.event.SimpleValueChangeEvent;
import com.square.client.gwt.client.model.BeneficiaireModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneDoublonModel;
import com.square.client.gwt.client.model.PersonneModel;

/**
 * Presenter de la popup indiquant la possibilité de doublon lors de la création d'une personne.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupCreationPersonneDoublonPresenter extends Presenter {

    /** Vue associée au presenter. */
    private PopupCreationPersonneDoublonView view;

    /** La personne en cours. */
    private PersonneBaseModel personneEnCours;

    /** Indique si la personne en cours est un bénéficiaire. */
    private boolean isBeneficiaire;

    /** Indique si la création de la personne provient des relations ou non. */
    private boolean isFromRelation;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènement.
     * @param view la vue associée au presenter.
     * @param personne la personne à créer.
     * @param isBeneficiaire indique si la personne est un bénéficiaire.
     * @param isFromRelation indique si la création de la personne provient des relations ou non.
     */
    public PopupCreationPersonneDoublonPresenter(HandlerManager eventBus, PopupCreationPersonneDoublonView view, PersonneBaseModel personne,
        boolean isBeneficiaire, boolean isFromRelation) {
        super(eventBus);
        this.view = view;
        this.personneEnCours = personne;
        this.isBeneficiaire = isBeneficiaire;
        this.isFromRelation = isFromRelation;
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        view.getBtnCreer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (isFromRelation) {
                   fireEventLocalBus(new CreerPersonneRelationEvent(null));
                }
                else {
                    if (personneEnCours instanceof PersonneModel) {
                        final PersonneModel personne = (PersonneModel) personneEnCours;
                        fireEventLocalBus(new CreerPersonneEvent(personne, isBeneficiaire));
                    }
                    else if (personneEnCours instanceof BeneficiaireModel) {
                        final BeneficiaireModel personne = (BeneficiaireModel) personneEnCours;
                        fireEventLocalBus(new CreerPersonneEvent(personne, isBeneficiaire));
                    }
                }
                view.cacherPopup();
                // On indique qu'une décision a été prise
                fireEventLocalBus(new SimpleValueChangeEvent<Void>(null));
            }
        });
        view.getBtnRattacher().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final Long idPersonneRattachement = view.getIdPersonneSelectionnee();

                if (isFromRelation) {
                    fireEventLocalBus(new CreerPersonneRelationEvent(idPersonneRattachement));
                }
                else {
                    if (personneEnCours instanceof PersonneModel) {
                        final PersonneModel personne = (PersonneModel) personneEnCours;
                        personne.setIdentifiant(idPersonneRattachement);
                        fireEventLocalBus(new CreerPersonneEvent(personne, isBeneficiaire));
                    }
                    else if (personneEnCours instanceof BeneficiaireModel) {
                        final BeneficiaireModel personne = (BeneficiaireModel) personneEnCours;
                        personne.setIdentifiant(idPersonneRattachement);
                        fireEventLocalBus(new CreerPersonneEvent(personne, isBeneficiaire));
                    }
                }
                view.cacherPopup();
                // On indique qu'une décision a été prise
                fireEventLocalBus(new SimpleValueChangeEvent<Void>(null));
            }
        });
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.cacherPopup();
                // On indique qu'une décision a été prise
                fireEventLocalBus(new SimpleValueChangeEvent<Void>(null));
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        view.afficherPopup();
    }

    /**
     * Afficher la popup.
     * @param nom .
     * @param prenom .
     * @param isContactPrincipal .
     * @param listeDoublons .
     */
    public void showPopup(String nom, String prenom, Boolean isContactPrincipal, List<PersonneDoublonModel> listeDoublons) {
        view.setData(nom, prenom, isContactPrincipal, listeDoublons);
        view.afficherPopup();
    }
    /**
     * Interface de la vue du presenter.
     * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
     */
    public interface PopupCreationPersonneDoublonView extends View {

        /**
         * Accesseur sur le click handler du bouton de création.
         * @return le handler.
         */
        HasClickHandlers getBtnCreer();

        /**
         * Accesseur sur le click handler du bouton de rattachement.
         * @return le handler.
         */
        HasClickHandlers getBtnRattacher();

        /**
         * Accesseur sur le click handler du bouton de rattachement.
         * @return le handler.
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Retourne le gestionnaire d'évènement du tableau.
         * @return le gestionnaire d'évènement.
         */
        HandlerManager getRemotePagingHandlerManager();

        /**
         * Récupère l'identifiant de la personne sélectionnée pour le rattachement.
         * @return l'identifiant de la personne sélectionnée.
         */
        Long getIdPersonneSelectionnee();

        /** Affiche la popup. */
        void afficherPopup();

        /** Ferme la popup. */
        void cacherPopup();

        /**
         * Initialiser les donnees de la vue.
         * @param nom .
         * @param prenom .
         * @param isContactPrincipal .
         * @param listeDoublons .
         */
        void setData(String nom, String prenom, Boolean isContactPrincipal,
            List < PersonneDoublonModel > listeDoublons);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
