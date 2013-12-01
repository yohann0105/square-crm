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
package com.square.composant.fusion.square.client.view.selection.doublon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gwtwidgets.client.ui.pagination.Column;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEvent;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingSortGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.remote.paging.RemotePagingTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.fusion.square.client.model.ConstantesModel;
import com.square.composant.fusion.square.client.model.RechercheDoublonCritereModel;
import com.square.composant.fusion.square.client.model.RechercheDoublonResultatModel;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenter;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenterConstants;
import com.square.composant.fusion.square.client.presenter.selection.doublon.SelectionDoublonPresenter;

/**
 * Vue de la sélectionde doublons pour la fusion.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class SelectionDoublonViewImpl extends Composite implements SelectionDoublonPresenter.SelectionDoublonView {

    /** Constantes de la vue. */
    private SelectionDoublonViewImplConstants viewConstants;

    /** Conteneur principal. */
    private VerticalPanel conteneur;

    /** Tableau présentant les résultats de la recherche. */
    private RemotePagingTable<RechercheDoublonResultatModel, RechercheDoublonCritereModel> remotePagingTableDoublons;

    /** Pour recuperer les evenements sur le tableau dans le Presenter. */
    private HandlerManager remotePagingHandlerManager;

    /** Les constantes. */
    private ConstantesModel constantes;

    /** Bouton pour valider la sélection des 2 doublons. */
    private DecoratedButton btnValider;

    /** Bouton pour valider la sélection. */
    private DecoratedButton btnViderSelection;

    /** La liste des CheckBoxs sélectionnées. */
    private Map<Long, CheckBox> mapCBSelectionnees;

    private Long idPersonneSelectionnee;

    /**
     * Constructeur.
     * @param constantes les constantes.
     */
    public SelectionDoublonViewImpl(ConstantesModel constantes) {
        // Création des constantes de la vue
        viewConstants = (SelectionDoublonViewImplConstants) GWT.create(SelectionDoublonViewImplConstants.class);
        this.constantes = constantes;

        mapCBSelectionnees = new HashMap<Long, CheckBox>();

        conteneur = new VerticalPanel();
        conteneur.setSpacing(10);
        conteneur.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        conteneur.addStyleName(ComposantFusionPresenter.RESOURCES.css().selectionDoublons());

        // Construit la barre de boutons
        construireBarreBoutons();

        // Construit le tableau affichant les doublons
        construireTableauSelectionDoublon();

        this.initWidget(conteneur);
        this.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        this.addStyleName(ComposantFusionPresenter.RESOURCES.css().contenuPageFusion());
    }

    /** Cnostruit la barre de boutons. */
    private void construireBarreBoutons() {
        btnValider = new DecoratedButton(viewConstants.btnValider());
        btnValider.setEnabled(false);
        btnViderSelection = new DecoratedButton(viewConstants.btnViderSelection());
        btnViderSelection.setEnabled(false);

        // Barre de boutons de la page
        final HorizontalPanel pBarreBoutons = new HorizontalPanel();
        pBarreBoutons.setWidth(ComposantFusionPresenterConstants.POURCENT_100);

        // Contenu de la barre de boutons
        final HorizontalPanel pContenuBarreBoutons = new HorizontalPanel();
        pContenuBarreBoutons.setSpacing(5);
        pContenuBarreBoutons.add(btnValider);
        pContenuBarreBoutons.add(btnViderSelection);

        pBarreBoutons.add(pContenuBarreBoutons);
        conteneur.add(pBarreBoutons);
    }

    /** Construit le tableau de sélection de doublons. */
    private void construireTableauSelectionDoublon() {
        remotePagingTableDoublons =
            new RemotePagingTable<RechercheDoublonResultatModel, RechercheDoublonCritereModel>(
                    SelectionDoublonViewImplConstants.NB_ELEMENT_PAR_PAGE, true) {

                @Override
                public void setDataProvider(RemotePagingCriteriasGwt<RechercheDoublonCritereModel> params,
                    AsyncCallback<RemotePagingResultsGwt<RechercheDoublonResultatModel>> callback) {
                    remotePagingHandlerManager.fireEvent(new SetDataProviderEvent<RechercheDoublonCritereModel, RechercheDoublonResultatModel>(
                            params, callback));
                }

                @Override
                public int setDefaultSortAsc() {
                    return RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                }

                @Override
                public String setDefaultSortField() {
                    return constantes.getProprietePersonneNom();
                }

                @Override
                public Column[] setHeader() {
                    return new Column[] {new Column(viewConstants.headerNumClient(), constantes.getProprietePersonneNumeroClient()),
                        new Column(viewConstants.headerDateCreation(), constantes.getProprietePersonneDateCreation()),
                        new Column(viewConstants.headerNom(), constantes.getProprietePersonneNom()),
                        new Column(viewConstants.headerPrenom(), constantes.getProprietePersonnePrenom()),
                        new Column(viewConstants.headerDateNaissance(), constantes.getProprietePersonneDateNaissance()),
                        new Column(viewConstants.headerNature(), constantes.getProprietePersonneNature()),
                        new Column(viewConstants.headerCodePostal(), constantes.getProprietePersonneCodePostal()),
                        new Column(viewConstants.headerCommune(), constantes.getProprietePersonneCommune()),
                        new Column(viewConstants.headerSelectionne())};
                }

                @Override
                public void setRow(int row, final RechercheDoublonResultatModel doublon) {
                    setWidget(row, 0, new Label(doublon.getNumeroClient()));
                    setWidget(row, 1, new Label(doublon.getDateCreation()));
                    setWidget(row, 2, new Label(doublon.getNom()));
                    setWidget(row, 3, new Label(doublon.getPrenom()));
                    setWidget(row, 4, new Label(doublon.getDateNaissance()));
                    setWidget(row, 5, new Label(doublon.getNature()));
                    setWidget(row, 6, new Label(doublon.getCodePostal()));
                    setWidget(row, 7, new Label(doublon.getCommune()));
                    final CheckBox cb = new CheckBox();
                    cb.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                            if (cb.getValue()) {
                                mapCBSelectionnees.put(doublon.getId(), cb);
                            }
                            else {
                                mapCBSelectionnees.remove(doublon.getId());
                            }

                            // Mise à jour de l'autorisation sur les CheckBoxs
                            majCBTableau();

                            // Mise à jour de l'état des boutons
                            mAJEtatsBtnsSelection();
                        }
                    });
                    if (doublon.getId().equals(idPersonneSelectionnee)) {
                        // On pré-selectionne la ligne correspondant à l'identifiant de la personne spéficiée
                        cb.setValue(true);
                        mapCBSelectionnees.put(doublon.getId(), cb);
                    }
                    setWidget(row, 8, cb);

                    getFlexCellFormatter().setHorizontalAlignment(row, 8, HasAlignment.ALIGN_CENTER);
                }

                @Override
                public void setCellClicked(RechercheDoublonResultatModel doublon) {
                }
            };
        remotePagingHandlerManager = new HandlerManager(remotePagingTableDoublons);
        remotePagingTableDoublons.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        conteneur.add(remotePagingTableDoublons);
    }

    /**
     * Met à jour les CheckBoxs du tableau paginé.
     */
    private void majCBTableau() {
        for (int i = 1; i < remotePagingTableDoublons.getFtResultats().getRowCount(); i++) {
            final CheckBox cb = (CheckBox) remotePagingTableDoublons.getFtResultats().getWidget(i, 8);
            if (mapCBSelectionnees.size() < 2 || mapCBSelectionnees.containsValue(cb)) {
                cb.setEnabled(true);
            }
            else {
                cb.setEnabled(false);
            }
        }
    }

    /** Met à jour l'état des boutons relatis à la sélection. */
    private void mAJEtatsBtnsSelection() {
        if (mapCBSelectionnees != null && mapCBSelectionnees.size() < 2) {
            btnValider.setEnabled(false);
        }
        else {
            btnValider.setEnabled(true);
        }
        if (mapCBSelectionnees != null && mapCBSelectionnees.size() > 0) {
            btnViderSelection.setEnabled(true);
        }
        else {
            btnViderSelection.setEnabled(false);
        }
    }

    @Override
    public DecoratedButton getBtnValider() {
        return btnValider;
    }

    @Override
    public DecoratedButton getBtnViderSelection() {
        return btnViderSelection;
    }

    @Override
    public HandlerManager getRemotePagingHandlerManager() {
        return remotePagingHandlerManager;
    }

    @Override
    public RemotePagingTable<RechercheDoublonResultatModel, RechercheDoublonCritereModel> getRemotePagingTableDoublons() {
        return remotePagingTableDoublons;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public List<Long> getListeIdsPersonnesSelectionnees() {
        final List<Long> listeIdsSelectionnes = new ArrayList<Long>();
        for (Long nrid : mapCBSelectionnees.keySet()) {
            listeIdsSelectionnes.add(nrid);
        }
        return listeIdsSelectionnes;
    }

    @Override
    public void viderSelection() {
        mapCBSelectionnees.clear();
        for (int i = 1; i < remotePagingTableDoublons.getFtResultats().getRowCount(); i++) {
            if (remotePagingTableDoublons.getFtResultats().getCellCount(i) > 8) {
                final CheckBox cb = (CheckBox) remotePagingTableDoublons.getFtResultats().getWidget(i, 8);
                cb.setEnabled(true);
                cb.setValue(false);
            }
        }
        btnValider.setEnabled(false);
        btnViderSelection.setEnabled(false);
    }

    /**
     * Définition de idPersonneSelectionnee.
     * @param idPersonneSelectionnee the idPersonneSelectionnee to set
     */
    public void setIdPersonneSelectionnee(Long idPersonneSelectionnee) {
        this.idPersonneSelectionnee = idPersonneSelectionnee;
    }

    @Override
    public void nettoyer() {
        remotePagingTableDoublons.getFtResultats().clear();
    }

}
