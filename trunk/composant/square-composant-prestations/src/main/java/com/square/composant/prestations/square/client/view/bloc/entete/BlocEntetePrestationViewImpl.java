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
package com.square.composant.prestations.square.client.view.bloc.entete;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingSortGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.header.HeaderFlexTable;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.prestations.square.client.ComposantPrestations;
import com.square.composant.prestations.square.client.content.i18n.ComposantPrestationsConstants;
import com.square.composant.prestations.square.client.model.ConstantesPrestationModel;
import com.square.composant.prestations.square.client.model.LigneDecompteModel;
import com.square.composant.prestations.square.client.presenter.bloc.entete.BlocEntetePrestationPresenter.BlocEntetePrestationView;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;
import com.square.composants.graphiques.lib.client.event.HasAllBlocSyntheseEventHandlers;

/**
 * Vue de l'entete de prestation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class BlocEntetePrestationViewImpl extends Composite implements BlocEntetePrestationView {

    /** View constants. */
    private static BlocEntetePrestationViewImplConstants viewConstants =
        (BlocEntetePrestationViewImplConstants) GWT.create(BlocEntetePrestationViewImplConstants.class);

    private BlocSyntheseDepliant blocSyntheseDepliant;

    private VerticalPanel conteneurGlobal;

    private FlexTable contenu;

    private Label lbDateDebutSoins;

    private Label lbDateFinSoins;

    private Label lbDestinataireReglement;

    private Label lbCompte;

    private Label lbNumeroCheque;

    private NumberFormat numberFormat = NumberFormat.getFormat("#,##0.00");

    private Label lDateSoin;
    private Label lBeneficiaire;
    private Label lActe;
    private Label lDepenseEngagee;;
    private Label lBase;
    private Label lTaux;
    private Label lRbtRo;
    private Label lRbtSmatisAdherent;
    private Label lRbtSmatisProf;
    private Label lRAC;

    private ConstantesPrestationModel constantes;

    /** Booléen indiquant un tri sur la colonne "Rbt Prof". */
    /** Booléen utilisé pour différencier les colonnes "Rbt Smatis" et "Rbt Prof" qui trient sur la même colonne en base
     * et pour pouvoir mettre en place par le suite l'image du tri sur la bonne colonne. */
    private boolean isTriColonneRbtProf = false;

    /**
     * Constructeur.
     * @param constantes les constantes.
     */
    public BlocEntetePrestationViewImpl(ConstantesPrestationModel constantes) {
        this.constantes = constantes;
        conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(ComposantPrestationsConstants.POURCENT_100);

        construireContenu();
        initColonnesLignes();

        this.initWidget(conteneurGlobal);
        this.setWidth(ComposantPrestationsConstants.POURCENT_100);
        this.addStyleName(ComposantPrestations.RESOURCES.css().entetePrestation());
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    private void construireContenu() {
        final Label lDateSoins = new Label(viewConstants.dateSoins());
        final Label lDestinataireReglement = new Label(viewConstants.destinataireReglement());
        final HTML separateurDate = new HTML("&nbsp;-&nbsp;");
        lbDateDebutSoins = new Label();
        lbDateFinSoins = new Label();
        lbDestinataireReglement = new Label();
        final HorizontalPanel hpDate = new HorizontalPanel();
        hpDate.add(lbDateDebutSoins);
        hpDate.add(separateurDate);
        hpDate.add(lbDateFinSoins);

        contenu = new FlexTable();
        contenu.addStyleName(ComposantPrestations.RESOURCES.css().contenuEntete());
        contenu.setWidth(ComposantPrestationsConstants.POURCENT_100);
        contenu.setCellSpacing(5);
        contenu.setWidget(0, 0, lDateSoins);
        contenu.setWidget(0, 1, hpDate);
        contenu.setWidget(0, 2, lDestinataireReglement);
        contenu.setWidget(0, 3, lbDestinataireReglement);
        contenu.getColumnFormatter().setWidth(0, "12%");
        contenu.getColumnFormatter().setWidth(1, "20%");
        contenu.getColumnFormatter().setWidth(2, "20%");
        contenu.getColumnFormatter().setWidth(3, "58%");
    }

    /** Initialise les colonnes des lignes. */
    private void initColonnesLignes() {
        lDateSoin = new Label(viewConstants.dateSoin());
        lDateSoin.addStyleName(ComposantPrestations.RESOURCES.css().titreLignesDecomptes());
        lBeneficiaire = new Label(viewConstants.beneficiaire());
        lBeneficiaire.addStyleName(ComposantPrestations.RESOURCES.css().titreLignesDecomptes());
        lActe = new Label(viewConstants.acte());
        lActe.addStyleName(ComposantPrestations.RESOURCES.css().titreLignesDecomptes());
        lDepenseEngagee = new Label(viewConstants.depenseEngagee());
        lDepenseEngagee.setTitle(viewConstants.titleDepenseEngagee());
        lDepenseEngagee.addStyleName(ComposantPrestations.RESOURCES.css().titreLignesDecomptes());
        lBase = new Label(viewConstants.base());
        lBase.addStyleName(ComposantPrestations.RESOURCES.css().titreLignesDecomptes());
        lTaux = new Label(viewConstants.taux());
        lTaux.addStyleName(ComposantPrestations.RESOURCES.css().titreLignesDecomptes());
        lRbtRo = new Label(viewConstants.remboursementRO());
        lRbtRo.setTitle(viewConstants.titleRemboursementRO());
        lRbtRo.addStyleName(ComposantPrestations.RESOURCES.css().titreLignesDecomptes());
        lRbtSmatisAdherent = new Label(viewConstants.remboursementSmatisAdherent());
        lRbtSmatisAdherent.setTitle(viewConstants.titleRemboursementSmatisAdherent());
        lRbtSmatisAdherent.addStyleName(ComposantPrestations.RESOURCES.css().titreLignesDecomptes());
        lRbtSmatisProf = new Label(viewConstants.remboursementSmatisProf());
        lRbtSmatisProf.setTitle(viewConstants.titleRemboursementSmatisProf());
        lRbtSmatisProf.addStyleName(ComposantPrestations.RESOURCES.css().titreLignesDecomptes());
        lRAC = new Label(viewConstants.resteACharge());
        lRAC.setTitle(viewConstants.titleResteACharge());
        lRAC.addStyleName(ComposantPrestations.RESOURCES.css().titreLignesDecomptes());
    }

    @Override
    public void chargerEntete(List<ChampSynthese> listeChamps) {
        blocSyntheseDepliant = new BlocSyntheseDepliant(listeChamps, contenu);
        blocSyntheseDepliant.setWidth(ComposantPrestationsConstants.POURCENT_100);
        conteneurGlobal.add(blocSyntheseDepliant);
    }

    @Override
    public HasAllBlocSyntheseEventHandlers getBlocSyntheseHandler() {
        return blocSyntheseDepliant;
    }

    @Override
    public BlocEntetePrestationViewImplConstants getViewConstants() {
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
    public HasText getCompte() {
        return lbCompte;
    }

    @Override
    public HasText getDateDebutSoins() {
        return lbDateDebutSoins;
    }

    @Override
    public HasText getDateFinSoins() {
        return lbDateFinSoins;
    }

    @Override
    public HasText getDestinataireReglement() {
        return lbDestinataireReglement;
    }

    @Override
    public HasText getNumeroCheque() {
        return lbNumeroCheque;
    }

    @Override
    public void afficherCompte() {
        final Label label = new Label(viewConstants.compte());
        lbCompte = new Label();
        contenu.setWidget(1, 2, label);
        contenu.setWidget(1, 3, lbCompte);
    }

    @Override
    public void afficherNumeroCheque() {
        final Label label = new Label(viewConstants.cheque());
        lbNumeroCheque = new Label();
        contenu.setWidget(1, 2, label);
        contenu.setWidget(1, 3, lbNumeroCheque);
    }

    @Override
    public void chargerLignes(List<LigneDecompteModel> lignes) {
        final HeaderFlexTable<LigneDecompteModel> ftLignes = new HeaderFlexTable<LigneDecompteModel>() {
            @Override
            public Widget[] setHeader() {
                lDateSoin.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                return new Label[] {lDateSoin, lBeneficiaire, lActe, lDepenseEngagee, lBase, lTaux, lRbtRo, lRbtSmatisAdherent, lRbtSmatisProf, lRAC};
            }

            @Override
            public void setRow(LigneDecompteModel ligne) {
                setWidget(0, new Label(ligne.getDateDebutSoins()));
                setWidget(1, new Label(ligne.getBeneficiaire()));
                final StringBuffer libelleActe = new StringBuffer(ligne.getActe().getLibelle());
                if (ligne.getProfessionnelSante() != null && !ligne.getProfessionnelSante().isEmpty()) {
                    libelleActe.append("<br />");
                    libelleActe.append(ligne.getProfessionnelSante());
                }
                setWidget(2, new HTML(libelleActe.toString()));
                setWidget(3, new Label(numberFormat.format(ligne.getMontant())));
                setWidget(4, new Label(ligne.getBaseRemboursementRO() != null ? numberFormat.format(ligne.getBaseRemboursementRO()) : ""));
                setWidget(5, new Label(ligne.getTauxRemboursementRO() != null ? numberFormat.format(ligne.getTauxRemboursementRO() * 100) + "%" : ""));
                final StringBuffer rbtSecu = new StringBuffer(numberFormat.format(ligne.getRemboursementSecu()));
                if (ligne.getRemboursementSecu() > 0) {
                    rbtSecu.append(" (" + ligne.getTauxRemboursementSecu() + "%)");
                }
                final StringBuffer rbtSmatisAdherent;
                final StringBuffer rbtSmatisProf;
                if (constantes.getIdNatureReglementTiersSante().equals(ligne.getIdNatureReglement())) {
                    rbtSmatisProf = new StringBuffer(numberFormat.format(ligne.getRemboursementCompl()));
                    if (ligne.getRemboursementCompl() > 0) {
                        rbtSmatisProf.append(" (" + ligne.getTauxRemboursementCompl() + "%)");
                    }
                    rbtSmatisAdherent = new StringBuffer(numberFormat.format(0));
                } else {
                    rbtSmatisProf = new StringBuffer(numberFormat.format(0));
                    rbtSmatisAdherent = new StringBuffer(numberFormat.format(ligne.getRemboursementCompl()));
                    if (ligne.getRemboursementCompl() > 0) {
                        rbtSmatisAdherent.append(" (" + ligne.getTauxRemboursementCompl() + "%)");
                    }
                }
                setWidget(6, new Label(rbtSecu.toString()));
                setWidget(7, new Label(rbtSmatisAdherent.toString()));
                setWidget(8, new Label(rbtSmatisProf.toString()));
                setWidget(9, new Label(numberFormat.format(ligne.getResteACharge())));
                setCellHorizontalAlignment(0, HasHorizontalAlignment.ALIGN_CENTER);
                setCellHorizontalAlignment(3, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(4, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(5, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(6, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(7, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(8, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(9, HasHorizontalAlignment.ALIGN_RIGHT);
            }
        };
        ftLignes.setWidth(ComposantPrestationsConstants.POURCENT_100);
        ftLignes.setListeObjets(lignes);

        final int row = contenu.getRowCount();
        contenu.setWidget(row, 0, ftLignes);
        contenu.getFlexCellFormatter().setColSpan(row, 0, 4);
    }

    @Override
    public HasClickHandlers getLDateSoin() {
        return lDateSoin;
    }

    @Override
    public HasClickHandlers getLBeneficiaire() {
        return lBeneficiaire;
    }

    @Override
    public HasClickHandlers getLActe() {
        return lActe;
    }

    @Override
    public HasClickHandlers getLDepenseEngagee() {
        return lDepenseEngagee;
    }

    @Override
    public HasClickHandlers getLBase() {
        return lBase;
    }

    @Override
    public HasClickHandlers getLTaux() {
        return lTaux;
    }

    @Override
    public HasClickHandlers getLRbtRo() {
        return lRbtRo;
    }

    @Override
    public HasClickHandlers getLRbtSmatisAdherent() {
        return lRbtSmatisAdherent;
    }

    @Override
    public HasClickHandlers getLRbtSmatisProf() {
        return lRbtSmatisProf;
    }

    @Override
    public HasClickHandlers getLRAC() {
        return lRAC;
    }

    @Override
    public void actualiserLignes(final List<LigneDecompteModel> listeLignes, final String colonneTri, final int triDefaut) {
        final HeaderFlexTable<LigneDecompteModel> ftLignes = new HeaderFlexTable<LigneDecompteModel>() {
            @Override
            public Widget[] setHeader() {
                supprimerAncienTri();
                if (constantes.getOrderDecompteByDateSoin().equals(colonneTri)) {
                    if (RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC == triDefaut) {
                        lDateSoin.addStyleName(ComposantPrestations.RESOURCES.css().triAsc());
                    }
                    else {
                        lDateSoin.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                    }
                }
                else if (constantes.getOrderDecompteByBeneficiaire().equals(colonneTri)) {
                    if (RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC == triDefaut) {
                        lBeneficiaire.addStyleName(ComposantPrestations.RESOURCES.css().triAsc());
                    }
                    else {
                        lBeneficiaire.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                    }
                }
                else if (constantes.getOrderDecompteByActe().equals(colonneTri)) {
                    if (RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC == triDefaut) {
                        lActe.addStyleName(ComposantPrestations.RESOURCES.css().triAsc());
                    }
                    else {
                        lActe.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                    }
                }
                else if (constantes.getOrderDecompteByDepense().equals(colonneTri)) {
                    if (RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC == triDefaut) {
                        lDepenseEngagee.addStyleName(ComposantPrestations.RESOURCES.css().triAsc());
                    }
                    else {
                        lDepenseEngagee.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                    }
                }
                else if (constantes.getOrderDecompteByBase().equals(colonneTri)) {
                    if (RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC == triDefaut) {
                        lBase.addStyleName(ComposantPrestations.RESOURCES.css().triAsc());
                    }
                    else {
                        lBase.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                    }
                }
                else if (constantes.getOrderDecompteByTaux().equals(colonneTri)) {
                    if (RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC == triDefaut) {
                        lTaux.addStyleName(ComposantPrestations.RESOURCES.css().triAsc());
                    }
                    else {
                        lTaux.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                    }
                }
                else if (constantes.getOrderDecompteByRbtRO().equals(colonneTri)) {
                    if (RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC == triDefaut) {
                        lRbtRo.addStyleName(ComposantPrestations.RESOURCES.css().triAsc());
                    }
                    else {
                        lRbtRo.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                    }
                }
                else if (constantes.getOrderDecompteByRbtSmatis().equals(colonneTri) && !isTriColonneRbtProf) {
                    if (RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC == triDefaut) {
                        lRbtSmatisAdherent.addStyleName(ComposantPrestations.RESOURCES.css().triAsc());
                    }
                    else {
                        lRbtSmatisAdherent.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                    }
                }
                else if (constantes.getOrderDecompteByRbtProf().equals(colonneTri) && isTriColonneRbtProf) {
                    if (RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC == triDefaut) {
                        lRbtSmatisProf.addStyleName(ComposantPrestations.RESOURCES.css().triAsc());
                    }
                    else {
                        lRbtSmatisProf.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                    }
                }
                else if (constantes.getOrderDecompteByRaC().equals(colonneTri)) {
                    if (RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC == triDefaut) {
                        lRAC.addStyleName(ComposantPrestations.RESOURCES.css().triAsc());
                    }
                    else {
                        lRAC.addStyleName(ComposantPrestations.RESOURCES.css().triDesc());
                    }
                }
                return new Label[] {lDateSoin, lBeneficiaire, lActe, lDepenseEngagee, lBase, lTaux, lRbtRo, lRbtSmatisAdherent, lRbtSmatisProf, lRAC};
            }

            @Override
            public void setRow(LigneDecompteModel ligne) {
                setWidget(0, new Label(ligne.getDateDebutSoins()));
                setWidget(1, new Label(ligne.getBeneficiaire()));
                final StringBuffer libelleActe = new StringBuffer(ligne.getActe().getLibelle());
                if (ligne.getProfessionnelSante() != null && !ligne.getProfessionnelSante().isEmpty()) {
                    libelleActe.append("<br />");
                    libelleActe.append(ligne.getProfessionnelSante());
                }
                setWidget(2, new HTML(libelleActe.toString()));
                setWidget(3, new Label(numberFormat.format(ligne.getMontant())));
                setWidget(4, new Label(ligne.getBaseRemboursementRO() != null ? numberFormat.format(ligne.getBaseRemboursementRO()) : ""));
                setWidget(5, new Label(ligne.getTauxRemboursementRO() != null ? numberFormat.format(ligne.getTauxRemboursementRO() * 100) + "%" : ""));
                final StringBuffer rbtSecu = new StringBuffer(numberFormat.format(ligne.getRemboursementSecu()));
                if (ligne.getRemboursementSecu() > 0) {
                    rbtSecu.append(" (" + ligne.getTauxRemboursementSecu() + "%)");
                }
                final StringBuffer rbtSmatisAdherent;
                final StringBuffer rbtSmatisProf;
                if (ligne.getProfessionnelSante() != null && !ligne.getProfessionnelSante().isEmpty()) {
                    rbtSmatisProf = new StringBuffer(numberFormat.format(ligne.getRemboursementCompl()));
                    if (ligne.getRemboursementCompl() > 0) {
                        rbtSmatisProf.append(" (" + ligne.getTauxRemboursementCompl() + "%)");
                    }
                    rbtSmatisAdherent = new StringBuffer(numberFormat.format(0));
                } else {
                    rbtSmatisProf = new StringBuffer(numberFormat.format(0));
                    rbtSmatisAdherent = new StringBuffer(numberFormat.format(ligne.getRemboursementCompl()));
                    if (ligne.getRemboursementCompl() > 0) {
                        rbtSmatisAdherent.append(" (" + ligne.getTauxRemboursementCompl() + "%)");
                    }
                }
                setWidget(6, new Label(rbtSecu.toString()));
                setWidget(7, new Label(rbtSmatisAdherent.toString()));
                setWidget(8, new Label(rbtSmatisProf.toString()));
                setWidget(9, new Label(numberFormat.format(ligne.getResteACharge())));
                setCellHorizontalAlignment(0, HasHorizontalAlignment.ALIGN_CENTER);
                setCellHorizontalAlignment(3, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(4, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(5, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(6, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(7, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(8, HasHorizontalAlignment.ALIGN_RIGHT);
                setCellHorizontalAlignment(9, HasHorizontalAlignment.ALIGN_RIGHT);
            }
        };
        ftLignes.setWidth(ComposantPrestationsConstants.POURCENT_100);
        ftLignes.setListeObjets(listeLignes);

        final int row = contenu.getRowCount() - 1;
        contenu.removeRow(row);
        contenu.setWidget(row, 0, ftLignes);
        contenu.getFlexCellFormatter().setColSpan(row, 0, 4);
    }

    /** Supprime l'image de l'ancien tri. */
    void supprimerAncienTri() {
        lDateSoin.removeStyleName(ComposantPrestations.RESOURCES.css().triAsc());
        lDateSoin.removeStyleName(ComposantPrestations.RESOURCES.css().triDesc());
        lBeneficiaire.removeStyleName(ComposantPrestations.RESOURCES.css().triAsc());
        lBeneficiaire.removeStyleName(ComposantPrestations.RESOURCES.css().triDesc());
        lActe.removeStyleName(ComposantPrestations.RESOURCES.css().triAsc());
        lActe.removeStyleName(ComposantPrestations.RESOURCES.css().triDesc());
        lDepenseEngagee.removeStyleName(ComposantPrestations.RESOURCES.css().triAsc());
        lDepenseEngagee.removeStyleName(ComposantPrestations.RESOURCES.css().triDesc());
        lBase.removeStyleName(ComposantPrestations.RESOURCES.css().triAsc());
        lBase.removeStyleName(ComposantPrestations.RESOURCES.css().triDesc());
        lTaux.removeStyleName(ComposantPrestations.RESOURCES.css().triAsc());
        lTaux.removeStyleName(ComposantPrestations.RESOURCES.css().triDesc());
        lRbtRo.removeStyleName(ComposantPrestations.RESOURCES.css().triAsc());
        lRbtRo.removeStyleName(ComposantPrestations.RESOURCES.css().triDesc());
        lRbtSmatisAdherent.removeStyleName(ComposantPrestations.RESOURCES.css().triAsc());
        lRbtSmatisAdherent.removeStyleName(ComposantPrestations.RESOURCES.css().triDesc());
        lRbtSmatisProf.removeStyleName(ComposantPrestations.RESOURCES.css().triAsc());
        lRbtSmatisProf.removeStyleName(ComposantPrestations.RESOURCES.css().triDesc());
        lRAC.removeStyleName(ComposantPrestations.RESOURCES.css().triAsc());
        lRAC.removeStyleName(ComposantPrestations.RESOURCES.css().triDesc());
    }

    @Override
    public void setIsTriColonneRbtProf(boolean value) {
        isTriColonneRbtProf = value;
    }
}
