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
package com.square.composant.tarificateur.square.client;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.event.MiseAJourOpportuniteEvent;
import com.square.composant.tarificateur.square.client.event.MiseAJourOpportuniteEventHandler;
import com.square.composant.tarificateur.square.client.event.RechargementOpportuniteEvent;
import com.square.composant.tarificateur.square.client.event.SuccesImpressionEvent;
import com.square.composant.tarificateur.square.client.event.SuccesImpressionEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesMajInfosAdhesionEvent;
import com.square.composant.tarificateur.square.client.event.SuccesMajInfosAdhesionEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesTransfertEvent;
import com.square.composant.tarificateur.square.client.event.SuccesTransfertEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesTransformationAiaEvent;
import com.square.composant.tarificateur.square.client.event.SuccesTransformationAiaEventHandler;
import com.square.composant.tarificateur.square.client.model.opportunite.InfosOpportuniteModel;
import com.square.composant.tarificateur.square.client.model.personne.AdresseModel;
import com.square.composant.tarificateur.square.client.model.personne.BeneficiaireModel;
import com.square.composant.tarificateur.square.client.model.personne.EmailModel;
import com.square.composant.tarificateur.square.client.model.personne.InfoSanteCreationModel;
import com.square.composant.tarificateur.square.client.model.personne.PersonneModel;
import com.square.composant.tarificateur.square.client.model.personne.TelephoneModel;
import com.square.composants.graphiques.lib.client.bundle.SquareLibResources;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Point d'entrée de l'application.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public final class ComposantTarificateurModule implements EntryPoint {
    /**
     * {@inheritDoc}
     */
    public void onModuleLoad() {

        final ComposantTarificateurConstants constants = GWT.create(ComposantTarificateurConstants.class);

        firefox3compatibility();

        StyleInjector.inject(SquareLibResources.INSTANCE.css().getText());

        final PersonneModel personne = getPersonneTest();
        final Long eidOpportunite = 112624460L;// 125350001L;
        final Long eidCreateur = 4L;
        final Long eidAgence = 4L;
        final Long eidResponsable = 58L;
        final InfosOpportuniteModel infosOpportunite = new InfosOpportuniteModel();
        infosOpportunite.setEidOpportunite(eidOpportunite);
        infosOpportunite.setEidCreateur(eidCreateur);
        infosOpportunite.setEidAgence(eidAgence);
        infosOpportunite.setEidResponsable(eidResponsable);
        infosOpportunite.setLoginUtilisateurConnecte("ymurg");
        infosOpportunite.setPersonne(personne);

        final HandlerManager eventBus = new HandlerManager(null);
        eventBus.addHandler(MiseAJourOpportuniteEvent.TYPE, new MiseAJourOpportuniteEventHandler() {
            @Override
            public void mettreAJour(MiseAJourOpportuniteEvent event) {
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(constants.miseAJourHoportunite(), ComposantTarificateurConstants.NOTIFICATION_TIME_OUT);
                new DecoratedInfoPopup(config).show();
            }
        });
        eventBus.addHandler(SuccesImpressionEvent.TYPE, new SuccesImpressionEventHandler() {
            @Override
            public void onSuccess(SuccesImpressionEvent event) {
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(constants.succesImpression(), ComposantTarificateurConstants.NOTIFICATION_TIME_OUT);
                config.setCallback(new PopupCallback() {

                    @Override
                    public void onResult(boolean result) {
                        // poste un evenement pour recharger l'opp
                        eventBus.fireEvent(new RechargementOpportuniteEvent());
                    }
                });
                new DecoratedInfoPopup(config).show();
            }
        });
        eventBus.addHandler(SuccesMajInfosAdhesionEvent.TYPE, new SuccesMajInfosAdhesionEventHandler() {
            @Override
            public void onSuccess(SuccesMajInfosAdhesionEvent event) {
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(constants.succesMiseJourInfoAdhesion(), ComposantTarificateurConstants.NOTIFICATION_TIME_OUT);
                new DecoratedInfoPopup(config).show();
            }
        });
        eventBus.addHandler(SuccesTransformationAiaEvent.TYPE, new SuccesTransformationAiaEventHandler() {
            @Override
            public void onSuccess(SuccesTransformationAiaEvent event) {
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(constants.succesTransformationAia(), ComposantTarificateurConstants.NOTIFICATION_TIME_OUT);
                new DecoratedInfoPopup(config).show();
            }
        });
        eventBus.addHandler(SuccesTransfertEvent.TYPE, new SuccesTransfertEventHandler() {
            @Override
            public void onSuccess(SuccesTransfertEvent event) {
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(constants.succesTransfert(), ComposantTarificateurConstants.NOTIFICATION_TIME_OUT);
                new DecoratedInfoPopup(config).show();
            }
        });

        final DeskBar deskBar = new DeskBar(0, eventBus);

        final ComposantTarificateur composantTarificateur = new ComposantTarificateur(eventBus, deskBar, infosOpportunite, true, true);
        composantTarificateur.getBtnAjouterDevisClickHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                composantTarificateur.creerNouveauDevis(infosOpportunite);
            }
        });
        composantTarificateur.getBtnVoirActionsClickHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(constants.affichageActions(), ComposantTarificateurConstants.NOTIFICATION_TIME_OUT);
                new DecoratedInfoPopup(config).show();
            }
        });

        final VerticalPanel conteneurTarificateur = new VerticalPanel();
        conteneurTarificateur.setWidth("944px");
        composantTarificateur.showPresenter(conteneurTarificateur);
        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth("100%");
        conteneur.add(conteneurTarificateur);
        conteneur.add(deskBar);
        RootPanel.get().add(conteneur);
    }

    /**
     * Retourne une personne complète permettant d'effectuer des tests avec.
     * @return une personne de test.
     */
    private PersonneModel getPersonneTest() {
        final Long eidPersonnePrincipale = 49528564L; // 4L;
        final Long eidConjoint = 5L;
        final Long eidEnfant1 = 6L;
        final Long eidEnfant2 = 7L;

        final PersonneModel personne = new PersonneModel();
        personne.setActuellementCouvert(true);
        final AdresseModel adressePrincipale = new AdresseModel();
        // adressePrincipale.setBoitePostal("16000");
        adressePrincipale.setEidAdresse(1L);
        adressePrincipale.setEidCodePostalCommune(1L);
        adressePrincipale.setEidPays(1L);
        adressePrincipale.setEidTypeVoie(1L);
        adressePrincipale.setNomVoie("de Limoges");
        adressePrincipale.setNumeroVoie("147");
        personne.setAdressePrincipale(adressePrincipale);
        final InfoSanteCreationModel infoSante = new InfoSanteCreationModel();
        infoSante.setEidCaisse(1L);
        infoSante.setNumSecuriteSocial("1840716341053");
        infoSante.setCleSecuriteSocial("72");
        infoSante.setEidReferent(eidPersonnePrincipale);
        personne.setInfoSante(infoSante);
        personne.setCouvertSixDerniersMois(true);
        final String dateNaissance = "08/02/1981";
        personne.setDateNaissance(dateNaissance);
        personne.setEidCategorieSocioProf(1L);
        personne.setEidCivilite(2L);
        personne.setEidPersonne(eidPersonnePrincipale);
        personne.setEidSituationFamiliale(1L);
        personne.setEidStatut(1L);
        final EmailModel email = new EmailModel();
        email.setAdresse("blanche.neige@gmail.com");
        personne.setEmail(email);
        personne.setLoiMadelin(true);
        personne.setNom("Neige");
        personne.setNomJeuneFille("Snow");
        personne.setPrenom("Blanche");
        final TelephoneModel telephoneBureau = new TelephoneModel();
        telephoneBureau.setNumTelephone("0545010203");
        personne.setTelephoneBureau(telephoneBureau);
        final TelephoneModel telephoneFixe = new TelephoneModel();
        telephoneFixe.setNumTelephone("0545040506");
        personne.setTelephoneFixe(telephoneFixe);
        final TelephoneModel telephonePortable = new TelephoneModel();
        telephonePortable.setNumTelephone("0601020304");
        personne.setTelephonePortable(telephonePortable);
        personne.setTravailleurNonSalarie(true);
        final List<BeneficiaireModel> listeBeneficiaires = new ArrayList<BeneficiaireModel>();
        // Ajout de bénéficiaires
        final BeneficiaireModel beneficiaire1 = new BeneficiaireModel();
        beneficiaire1.setNom("Timide");
        beneficiaire1.setPrenom("Jean Luc");
        beneficiaire1.setEidPersonne(eidConjoint);
        beneficiaire1.setEidCivilite(1L);
        final String dateNaissanceConjoint = "08/02/1983";
        beneficiaire1.setDateNaissance(dateNaissanceConjoint);
        beneficiaire1.setIdLienFamilial(2L);
        beneficiaire1.setAdressePrincipale(adressePrincipale);
        final InfoSanteCreationModel infoSanteBenef1 = new InfoSanteCreationModel();
        infoSanteBenef1.setEidCaisse(1L);
        infoSanteBenef1.setNumSecuriteSocial("2900816341053");
        infoSanteBenef1.setCleSecuriteSocial("35");
        infoSanteBenef1.setEidReferent(eidConjoint);
        beneficiaire1.setInfoSante(infoSanteBenef1);
        listeBeneficiaires.add(beneficiaire1);
        final BeneficiaireModel beneficiaire2 = new BeneficiaireModel();
        beneficiaire2.setNom("Timide");
        beneficiaire2.setPrenom("Albert");
        beneficiaire2.setEidPersonne(eidEnfant1);
        beneficiaire2.setEidCivilite(1L);
        final String dateNaissanceBenef1 = "08/02/2006";
        beneficiaire2.setDateNaissance(dateNaissanceBenef1);
        beneficiaire2.setIdLienFamilial(3L);
        beneficiaire2.setAdressePrincipale(adressePrincipale);
        final InfoSanteCreationModel infoSanteBenef2 = new InfoSanteCreationModel();
        infoSanteBenef2.setEidCaisse(1L);
        infoSanteBenef2.setNumSecuriteSocial("1840716341053");
        infoSanteBenef2.setCleSecuriteSocial("72");
        infoSanteBenef2.setEidReferent(eidPersonnePrincipale);
        beneficiaire2.setInfoSante(infoSanteBenef2);
        listeBeneficiaires.add(beneficiaire2);
        final BeneficiaireModel beneficiaire3 = new BeneficiaireModel();
        beneficiaire3.setNom("Grincheux");
        beneficiaire3.setPrenom("Bernard");
        beneficiaire3.setEidPersonne(eidEnfant2);
        beneficiaire3.setEidCivilite(1L);
        final String dateNaissanceBenef2 = "08/02/2008";
        beneficiaire3.setDateNaissance(dateNaissanceBenef2);
        beneficiaire3.setIdLienFamilial(3L);
        beneficiaire3.setAdressePrincipale(adressePrincipale);
        final InfoSanteCreationModel infoSanteBenef3 = new InfoSanteCreationModel();
        infoSanteBenef3.setEidCaisse(1L);
        infoSanteBenef3.setNumSecuriteSocial("2101016341053");
        infoSanteBenef3.setCleSecuriteSocial("10");
        infoSanteBenef3.setEidReferent(eidEnfant2);
        beneficiaire3.setInfoSante(infoSanteBenef3);
        listeBeneficiaires.add(beneficiaire3);
        personne.setListeBeneficiaires(listeBeneficiaires);
        return personne;
    }

    private static native void firefox3compatibility() /*-{
	   if (!$doc.getBoxObjectFor) { $doc.getBoxObjectFor = function (element) {
	   var box = element.getBoundingClientRect();
	   return { "x" : box.left, "y" : box.top, "width" : box.width, "height" : box.height, "screenX": box.left, "screenY":box.top }; } }
	}-*/;
}
