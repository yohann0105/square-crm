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
package com.square.composant.contrat.personne.morale.square.client.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.contrat.personne.morale.square.client.bundle.ComposantContratPersonneMoraleResources;
import com.square.composant.contrat.personne.morale.square.client.content.i18n.ComposantContratPersonneMoraleConstants;
import com.square.composant.contrat.personne.morale.square.client.model.ConstantesModel;
import com.square.composant.contrat.personne.morale.square.client.model.GarantiePersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.model.InfosGarantiePersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.presenter.ContratPersonneMoraleContenuPresenter.ContratGarantiePersonneMoraleView;

/**
 * Implémentation de la vue d'une garantie d'un contrat d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ContratGarantiePersonneMoraleViewImpl extends Composite implements ContratGarantiePersonneMoraleView {

    private NumberFormat numberFormat;

    /** View constants. */
    private ContratGarantiePMViewImplConstants viewConstants;

    /** Constantes de l'application. */
    private ConstantesModel constantesApp;

    /** Ressources. */
    private ComposantContratPersonneMoraleResources ressources;

    /** Conteneur global. */
    private VerticalPanel conteneurGlobal;

    /** Conteneur de l'icone pdf. */
    private VerticalPanel conteneurIconePdf;

    /**
     * Constructeur.
     * @param constantesApp constantes de l'application
     * @param ressources les ressources.
     * @param garantie la garantie.
     */
    public ContratGarantiePersonneMoraleViewImpl(ConstantesModel constantesApp, ComposantContratPersonneMoraleResources ressources,
        GarantiePersonneMoraleModel garantie) {
        this.viewConstants = (ContratGarantiePMViewImplConstants) GWT.create(ContratGarantiePMViewImplConstants.class);
        numberFormat = NumberFormat.getFormat("#,##0.00 " + viewConstants.symboleMonnaie());
        this.constantesApp = constantesApp;
        this.ressources = ressources;
        construireBlocContenu(garantie);
        final CaptionPanel captionPanel = new CaptionPanel(garantie.getLibelle());
        captionPanel.setStylePrimaryName(this.ressources.css().panelGarantieContrat());
        captionPanel.add(conteneurGlobal);
        initWidget(captionPanel);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    /** Construit le contenu. */
    private void construireBlocContenu(GarantiePersonneMoraleModel garantie) {
        conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);
        conteneurGlobal.setSpacing(5);

        conteneurIconePdf = new VerticalPanel();
        conteneurGlobal.add(conteneurIconePdf);

        construireTableauGarantie(garantie);
    }

    /** Construit le tableau de la garantie. */
    private void construireTableauGarantie(GarantiePersonneMoraleModel garantie) {
        if (garantie.getListeInfosGarantie() != null && garantie.getListeInfosGarantie().size() > 0) {
            // On regarde s'il y a des montants souscrits pour afficher la colonne ou non.
            boolean hasMontantSouscrit = false;
            for (InfosGarantiePersonneMoraleModel infosGarantie : garantie.getListeInfosGarantie()) {
                if (infosGarantie.getMontantSouscrit() != null && infosGarantie.getMontantSouscrit() > 0.0d) {
                    hasMontantSouscrit = true;
                    break;
                }
            }

            // Construction du tableau
            final FlexTable tableInfosGarantie = new FlexTable();
            tableInfosGarantie.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);
            tableInfosGarantie.setCellPadding(6);
            tableInfosGarantie.setStylePrimaryName(ressources.css().tableau());
            tableInfosGarantie.getRowFormatter().setStyleName(0, ressources.css().ligneEnteteColonne());
            tableInfosGarantie.setWidget(0, 0, new Label(viewConstants.labelCodeTarif()));
            tableInfosGarantie.setWidget(0, 1, new Label(viewConstants.labelGarantieGestion()));
            tableInfosGarantie.setWidget(0, 2, new Label(viewConstants.labelPopulation()));
            if (hasMontantSouscrit) {
                tableInfosGarantie.setWidget(0, 3, new Label(viewConstants.labelCapital()));
            }
            // Remplissage des lignes
            int ligne = 1;
            for (InfosGarantiePersonneMoraleModel infosGarantie : garantie.getListeInfosGarantie()) {
                tableInfosGarantie.setWidget(ligne, 0, new Label(infosGarantie.getCodeTarif()));
                tableInfosGarantie.setWidget(ligne, 1, new Label(infosGarantie.getLibelleGarantieGestion()));
                tableInfosGarantie.setWidget(ligne, 2, new Label(infosGarantie.getLibellePopulation()));
                if (hasMontantSouscrit && infosGarantie.getMontantSouscrit() != null) {
                    tableInfosGarantie.setWidget(ligne, 3, new Label(numberFormat.format(infosGarantie.getMontantSouscrit())));
                }
                final Long idStatut = infosGarantie.getStatut().getIdentifiant();
                if (constantesApp.getIdStatutGarantieEnCours().equals(idStatut)) {
                    tableInfosGarantie.getRowFormatter().addStyleName(ligne, this.ressources.css().couleurFondGarantieEnCours());
                } else if (constantesApp.getIdStatutGarantieResiliee().equals(idStatut)) {
                    tableInfosGarantie.getRowFormatter().addStyleName(ligne, this.ressources.css().couleurFondGarantieResiliee());
                }
                ligne++;
            }

            if (hasMontantSouscrit) {
                tableInfosGarantie.getColumnFormatter().setWidth(0, "15%");
                tableInfosGarantie.getColumnFormatter().setWidth(1, "45%");
                tableInfosGarantie.getColumnFormatter().setWidth(2, "25%");
                tableInfosGarantie.getColumnFormatter().setWidth(3, "15%");
            }
            else {
                tableInfosGarantie.getColumnFormatter().setWidth(0, "20%");
                tableInfosGarantie.getColumnFormatter().setWidth(1, "50%");
                tableInfosGarantie.getColumnFormatter().setWidth(2, "30%");
            }
            conteneurGlobal.add(tableInfosGarantie);
        }
    }

    /**
     * Affiche l'icone PDF.
     * @param iconePdf l'icone à afficher.
     */
    public void afficherIconePdf(Image iconePdf) {
        conteneurIconePdf.clear();
        conteneurIconePdf.add(iconePdf);
    }

}
