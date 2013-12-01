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
package com.square.composant.ged.square.client.view.listedetaillee;

import org.gwtwidgets.client.util.SimpleDateFormat;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.ged.square.client.composant.popup.ErrorPopup;
import com.square.composant.ged.square.client.composant.popup.LoadingPopup;
import com.square.composant.ged.square.client.composant.popup.LoadingPopupConfiguration;
import com.square.composant.ged.square.client.presenter.ListeDetailleeDocumentsPresenter.ListeDetailleeDocumentsView;

/**
 * Implémentation de la vue.
 * @author jgoncalves - SCUB
 */
public class ListeDetailleeDocumentsViewImpl extends Composite implements ListeDetailleeDocumentsView {

    private static final String LARGEUR_NOM = "42%";

    private static final String LARGEUR_DATE = "13%";

    private static final String LARGEUR_SENS = "7%";

    private static final String LARGEUR_TAGS = "38%";

    private static final String POURCENT_100 = "100%";

    private ListeDetailleeDocumentsViewImplConstants viewConstants;

    private VerticalPanel conteneurLignes;

    /** Constructeur par défaut. */
    public ListeDetailleeDocumentsViewImpl() {
        viewConstants = GWT.create(ListeDetailleeDocumentsViewImplConstants.class);
        final FlexTable headerPanel = new FlexTable();
        headerPanel.setWidth(POURCENT_100);
        headerPanel.setStylePrimaryName("tableau");
        headerPanel.getRowFormatter().setStylePrimaryName(0, "ligneEnteteColonne");
        headerPanel.setWidget(0, 0, new Label(viewConstants.titreColonneNomDocument()));
        headerPanel.setWidget(0, 1, new Label(viewConstants.titreColonneDateReception()));
        headerPanel.setWidget(0, 2, new Label(viewConstants.titreColonneSens()));
        headerPanel.setWidget(0, 3, new Label(viewConstants.titreColonneTag()));
        headerPanel.getColumnFormatter().setWidth(0, LARGEUR_NOM);
        headerPanel.getColumnFormatter().setWidth(1, LARGEUR_DATE);
        headerPanel.getColumnFormatter().setWidth(2, LARGEUR_SENS);
        headerPanel.getColumnFormatter().setWidth(3, LARGEUR_TAGS);
        headerPanel.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
        headerPanel.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);

        conteneurLignes = new VerticalPanel();
        conteneurLignes.setWidth(POURCENT_100);
        conteneurLignes.setStylePrimaryName("conteneurLignes");

        final VerticalPanel listeDetaillee = new VerticalPanel();
        listeDetaillee.setWidth(POURCENT_100);
        listeDetaillee.setStylePrimaryName("listeDetaillee");
        listeDetaillee.add(headerPanel);
        listeDetaillee.add(conteneurLignes);

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth(POURCENT_100);
        conteneur.setSpacing(10);
        conteneur.add(listeDetaillee);

        initWidget(conteneur);
    }

    @Override
    public ListeDetailleeDocumentsViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public void onRpcServiceFailure(Throwable exception) {
        LoadingPopup.stopAll();
        ErrorPopup.getInstance().afficher(viewConstants.messageErreurGeneriqueGed());
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void ajouterBlocListeDocuments(BlocListeDocumentsDto bloc) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        final Label titreBloc = new Label(bloc.getTitre());
        titreBloc.setStylePrimaryName("titreBloc");

        final FlexTable blocLignes = new FlexTable();
        blocLignes.setStylePrimaryName("tableau");
        blocLignes.setWidth(POURCENT_100);
        blocLignes.getColumnFormatter().setWidth(0, LARGEUR_NOM);
        blocLignes.getColumnFormatter().setWidth(1, LARGEUR_DATE);
        blocLignes.getColumnFormatter().setWidth(2, LARGEUR_SENS);
        blocLignes.getColumnFormatter().setWidth(3, LARGEUR_TAGS);
        int i = 0;
        for (LigneDocumentDto ligne : bloc.getListeLignes()) {
            final FlowPanel panelTags = new FlowPanel();
            if (ligne.getTags() != null && ligne.getTags().size() > 0) {
                for (String tag : ligne.getTags()) {
                    final Label lTag = new Label(tag, false);
                    lTag.setStylePrimaryName("tag");
                    panelTags.add(lTag);
                }
            }
            final Anchor nomFichier = new Anchor(ligne.getNom(), ligne.getUrl(), "_blank");
            nomFichier.setStylePrimaryName("nomFichier");
            blocLignes.setWidget(i, 0, nomFichier);
            if (ligne.getDateReception() != null) {
                blocLignes.setWidget(i, 1, new Label(sdf.format(ligne.getDateReception())));
            }
            else
            {
                blocLignes.setWidget(i, 1, new Label(""));
            }
            blocLignes.setWidget(i, 2, new Label(ligne.getSens()));
            blocLignes.setWidget(i, 3, panelTags);
            blocLignes.getCellFormatter().setHorizontalAlignment(i, 1, HasHorizontalAlignment.ALIGN_CENTER);
            blocLignes.getCellFormatter().setHorizontalAlignment(i, 2, HasHorizontalAlignment.ALIGN_CENTER);
            i++;
        }

        final VerticalPanel blocPanel = new VerticalPanel();
        blocPanel.setWidth(POURCENT_100);
        blocPanel.setSpacing(5);
        blocPanel.add(titreBloc);
        blocPanel.add(blocLignes);

        conteneurLignes.add(blocPanel);
    }

    @Override
    public void afficherAucunDocument() {
        final Label aucunDocument = new Label("Aucun document");
        aucunDocument.setStylePrimaryName("aucunDocument");
        conteneurLignes.add(aucunDocument);
        conteneurLignes.setCellHorizontalAlignment(aucunDocument, HasHorizontalAlignment.ALIGN_CENTER);
    }

    @Override
    public void nettoyer() {
        conteneurLignes.clear();
    }

}
