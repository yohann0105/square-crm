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
package com.square.client.gwt.client.view.personne.relations;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.OrgChart;
import com.google.gwt.visualization.client.visualizations.OrgChart.Options;
import com.square.client.gwt.client.presenter.personne.PersonneRelationsVisualizationPresenter.PersonneRelationsVisualizationView;

/**
 * Implementation de la vue representation graphique des relations.
 * @author Goumard Stephane (scub) - SCUB
 */
public class PersonneRelationsVisualizationViewImpl extends Composite implements PersonneRelationsVisualizationView {

    private Options options;

    /**
     * Chart.
     */
    private OrgChart orgChart;

    /**
     * Table de données.
     */
    private DataTable datas;

    /**
     * Panel conteneur.
     */
    private SimplePanel panel;

    /**
     * Consants de la vue.
     */
    private PersonneRelationsVisualizationViewImplConstants constants;

    // private Hashtable<Integer, String> listePersonne = new Hashtable<Integer, String>();

    /**
     * Constructeur.
     */
    public PersonneRelationsVisualizationViewImpl() {
        panel = new SimplePanel();
        datas = DataTable.create();
        options = Options.create();
        orgChart = new OrgChart(datas, options);
        constants = GWT.create(PersonneRelationsVisualizationViewImplConstants.class);
        initWidget(panel);
    }

    @Override
    public void afficherGraphique(final String[][] relationsDatas) {
        options.setAllowHtml(true);
        // datas = DataTable.create();
        datas.addColumn(ColumnType.STRING, "idPersonneSource");
        datas.addColumn(ColumnType.STRING, "idPersonneCible");
        datas.addColumn(ColumnType.STRING, "isPersonneSource");
        for (int row = 0; row < relationsDatas.length; row++) {
            datas.addRows(1);
            for (int column = 0; column < 3; column++) {
                datas.setValue(row, column, relationsDatas[row][column]);
                // listePersonne.put(row, relationsDatas[row][column]);
                if (column == 0) {
                    //Mise en forme du nom de la personne en cours
                    if (relationsDatas[row][2].equals("Personne source")) {
                        datas.setFormattedValue(row, column, "<div style='color:#8B4513; font-style:italic; font-weight:bold; text-decoration: blink;'>"
                        	+ relationsDatas[row][3] + "</div>");
                    } else if(relationsDatas[row][2].equals("Parent")) {
                    	datas.setFormattedValue(row, column, "<div style='color:red; font-style:italic'>" + relationsDatas[row][4] + "</div>"
    	                        + "<div style='color:#330066'>" + relationsDatas[row][3] + "</div>");
                    } else if(relationsDatas[row][2].equals("Conjoint")) {
                    	datas.setFormattedValue(row, column, "<div style='color:red; font-style:italic'>" + relationsDatas[row][4] + "</div>"
                    			+ "<div style='color:#FF00CC'>" + relationsDatas[row][3] + "</div>");
                    } else if(relationsDatas[row][2].equals("Enfant")) {
                    	datas.setFormattedValue(row, column, "<div style='color:red; font-style:italic'>" + relationsDatas[row][4] + "</div>"
                    			+ "<div style='color:#669900'>" + relationsDatas[row][3] + "</div>");
                    } else {
                    	datas.setFormattedValue(row, column, "<div style='color:#b4bfc1; background-color:#dfdfdf;'><div style='font-style:italic;'>"
                    			+ relationsDatas[row][4] + "<br />" + relationsDatas[row][3] + "</div>");
                    }
                }
            }
        }
        panel.clear();
        panel.add(orgChart);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void afficherLoadingPopup() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(constants.chargementGraphique()));
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stopAll();
    }

    @Override
    public OrgChart getChart() {
        return orgChart;
    }

    @Override
    public DataTable getData() {
        return datas;
    }
}
