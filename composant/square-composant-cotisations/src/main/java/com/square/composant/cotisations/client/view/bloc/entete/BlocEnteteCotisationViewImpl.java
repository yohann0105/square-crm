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
package com.square.composant.cotisations.client.view.bloc.entete;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.grid.header.HeaderFlexTable;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.cotisations.client.ComposantCotisations;
import com.square.composant.cotisations.client.content.i18n.ComposantCotisationsConstants;
import com.square.composant.cotisations.client.model.DetailCotisationModel;
import com.square.composant.cotisations.client.model.DetailEncaissementModel;
import com.square.composant.cotisations.client.presenter.bloc.entete.BlocEnteteCotisationPresenter.BlocEnteteCotisationView;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;

/**
 * Vue de l'entete de cotisation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class BlocEnteteCotisationViewImpl extends Composite implements BlocEnteteCotisationView {

    /** View constants. */
    private static BlocEnteteCotisationViewImplConstants viewConstants =
        (BlocEnteteCotisationViewImplConstants) GWT.create(BlocEnteteCotisationViewImplConstants.class);

    /** View messages. */
    private static BlocEnteteCotisationViewImplMessages viewMessages =
        (BlocEnteteCotisationViewImplMessages) GWT.create(BlocEnteteCotisationViewImplMessages.class);

    private BlocSyntheseDepliant blocSyntheseDepliant;

    private VerticalPanel conteneurGlobal;

    private VerticalPanel contenu;

    private VerticalPanel contenuDetailsEncaissement;

    private HeaderFlexTable<DetailCotisationModel> ftDetailsCotisation;

    private String currentContratLigne;

    /**
     * Constructeur.
     */
    public BlocEnteteCotisationViewImpl() {
        conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(ComposantCotisationsConstants.POURCENT_100);

        construireContenu();

        this.initWidget(conteneurGlobal);
        this.setWidth(ComposantCotisationsConstants.POURCENT_100);
        this.addStyleName(ComposantCotisations.RESOURCES.css().enteteCotisation());
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    private void construireContenu() {
        contenuDetailsEncaissement = new VerticalPanel();
        contenuDetailsEncaissement.setWidth(ComposantCotisationsConstants.POURCENT_100);

        ftDetailsCotisation = new HeaderFlexTable<DetailCotisationModel>() {
            @Override
            public Widget[] setHeader() {
                return new Label[] {new Label(viewConstants.enteteContrat()), new Label(viewConstants.enteteGarantie()),
                    new Label(viewConstants.enteteBeneficiaire()), new Label(viewConstants.enteteMontant()), new Label(viewConstants.enteteTypePrime()),
                    new Label(viewConstants.enteteTypeEcheance())};
            }

            @Override
            public void setRow(DetailCotisationModel detail) {
                setWidget(0, new Label(detail.getContrat() != null && !detail.getContrat().equals(currentContratLigne) ? detail.getContrat() : ""));
                String libelle = detail.getGarantie() != null ? detail.getGarantie() : "";
                if (detail.getEidGarantie() != null && !"".equals(detail.getEidGarantie())) {
                    libelle += detail.getGarantie() != null ? "<br />" : "";
                    if (detail.getLibelle() != null && !"".equals(detail.getLibelle())) {
                        libelle += detail.getLibelle();
                    }
                }
                else {
                    libelle += " (" + detail.getEidGarantie() + ")";
                }

                setWidget(1, new HTML(libelle));
                setWidget(2, new Label(detail.getBeneficiaire() != null ? detail.getBeneficiaire().getNom() + ComposantCotisationsConstants.ESPACE
                    + detail.getBeneficiaire().getPrenom() : ""));
                setWidget(3, new Label(ComposantCotisationsConstants.NUMBERFORMAT.format(detail.getMontant())));
                setWidget(4, new Label(detail.getTypePrime()));
                setWidget(5, new Label(viewConstants.echeanceMap().get(detail.getTypeEcheance())));
                currentContratLigne = detail.getContrat();
            }
        };
        ftDetailsCotisation.setWidth(ComposantCotisationsConstants.POURCENT_100);

        contenu = new VerticalPanel();
        contenu.addStyleName(ComposantCotisations.RESOURCES.css().contenuEntete());
        contenu.setWidth(ComposantCotisationsConstants.POURCENT_100);
        contenu.setSpacing(5);
        contenu.add(contenuDetailsEncaissement);
        contenu.add(ftDetailsCotisation);
    }

    @Override
    public void chargerEntete(List<ChampSynthese> listeChamps) {
        blocSyntheseDepliant = new BlocSyntheseDepliant(listeChamps, contenu);
        blocSyntheseDepliant.setWidth(ComposantCotisationsConstants.POURCENT_100);
        conteneurGlobal.add(blocSyntheseDepliant);
    }

    @Override
    public BlocEnteteCotisationViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        ErrorPopup.afficher(config);
    }

    @Override
    public BlocEnteteCotisationViewImplMessages getViewMessages() {
        return viewMessages;
    }

    @Override
    public void chargerDetailsCotisation(List<DetailCotisationModel> lignes) {
        ftDetailsCotisation.setListeObjets(lignes);
    }

    @Override
    public void chargerDetailsEncaissement(List<DetailEncaissementModel> lignes) {
        for (DetailEncaissementModel detailEncaissement : lignes) {
            final HorizontalPanel ligne1 = new HorizontalPanel();
            ligne1.addStyleName(ComposantCotisations.RESOURCES.css().ligneEncaissement());
            ligne1.setSpacing(2);
            if (detailEncaissement.getMoyenPaiement() != null) {
                ligne1.add(new Label(viewConstants.paiement() + ComposantCotisationsConstants.ESPACE + detailEncaissement.getMoyenPaiement().getLibelle(),
                    false));
            }
            if (detailEncaissement.getDate() != null && !"".equals(detailEncaissement.getDate())) {
                ligne1.add(new Label(viewConstants.date() + ComposantCotisationsConstants.ESPACE + detailEncaissement.getDate(), false));
            }
            if (detailEncaissement.getMontant() != null && !"".equals(detailEncaissement.getMontant())) {
                ligne1.add(new Label(viewConstants.montant() + ComposantCotisationsConstants.ESPACE
                    + ComposantCotisationsConstants.NUMBERFORMAT.format(detailEncaissement.getMontant()), false));
            }
            if (ligne1.getWidgetCount() > 0) {
                contenuDetailsEncaissement.add(ligne1);
                contenuDetailsEncaissement.setCellHorizontalAlignment(ligne1, HasHorizontalAlignment.ALIGN_LEFT);
            }

            final HorizontalPanel ligne2 = new HorizontalPanel();
            ligne2.addStyleName(ComposantCotisations.RESOURCES.css().ligneEncaissement());
            ligne2.setSpacing(2);
            if (detailEncaissement.getNumeroCheque() != null && !"".equals(detailEncaissement.getNumeroCheque())) {
                ligne2.add(new Label(viewConstants.numeroCheque() + ComposantCotisationsConstants.ESPACE + detailEncaissement.getNumeroCheque(), false));
            }
            if (detailEncaissement.getCompte() != null && !"".equals(detailEncaissement.getCompte())) {
                ligne2.add(new Label(viewConstants.compte() + ComposantCotisationsConstants.ESPACE + detailEncaissement.getCompte(), false));
            }
            if (detailEncaissement.getBanque() != null && !"".equals(detailEncaissement.getBanque())) {
                ligne2.add(new Label(viewConstants.banque() + ComposantCotisationsConstants.ESPACE + detailEncaissement.getBanque(), false));
            }
            if (ligne2.getWidgetCount() > 0) {
                contenuDetailsEncaissement.add(ligne2);
                contenuDetailsEncaissement.setCellHorizontalAlignment(ligne2, HasHorizontalAlignment.ALIGN_RIGHT);
            }

            // affichage des rejets
            if (detailEncaissement.getMotifRejet() != null && !"".equals(detailEncaissement.getMotifRejet())
            		&& detailEncaissement.getDateRejet() != null && !"".equals(detailEncaissement.getDateRejet())) {
//            if (detailEncaissement.getMotifRejet() != null && !"".equals(detailEncaissement.getMotifRejet())) {
                final HorizontalPanel ligne3 = new HorizontalPanel();
                ligne3.addStyleName(ComposantCotisations.RESOURCES.css().ligneEncaissement());
                //ligne3.addStyleName(ComposantCotisations.RESOURCES.css().ligneDeuxEncaissement());
                ligne3.setSpacing(2);

                ligne3.add(new Label(viewConstants.situationPrime() + ComposantCotisationsConstants.ESPACE + viewConstants.situationPrimeRejetee(),
                        false));

                if (detailEncaissement.getMotifRejet() != null && !"".equals(detailEncaissement.getMotifRejet())) {
                	ligne3.add(new Label(viewConstants.motifRejet() + ComposantCotisationsConstants.ESPACE + detailEncaissement.getMotifRejet(), false));
                }
                if (detailEncaissement.getDateRejet() != null && !"".equals(detailEncaissement.getDateRejet())) {
                    ligne3.add(new Label(viewConstants.dateRejet() + ComposantCotisationsConstants.ESPACE + detailEncaissement.getDateRejet(), false));
                }
                if (ligne3.getWidgetCount() > 0) {
                    contenuDetailsEncaissement.add(ligne3);
                    contenuDetailsEncaissement.setCellHorizontalAlignment(ligne3, HasHorizontalAlignment.ALIGN_LEFT);
                }
            }
        }
    }
}
