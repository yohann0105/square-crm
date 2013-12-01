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
package com.square.composant.tarificateur.square.client.view.adhesion;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;

import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.CaisseSimpleModel;
import com.square.composant.tarificateur.square.client.model.IdentifiantEidLibelleModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPersonneModel;
import com.square.composant.tarificateur.square.client.presenter.adhesion.InfosAdhesionPresenter.BlocInfoSanteComplete;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue qui regroupe les informations de santé simple.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class BlocInfoSanteCompleteViewImpl extends Composite implements BlocInfoSanteComplete {

    /** Constantes associées à la vue. */
    private InfosAdhesionViewImplConstants viewConstants;

    private VerticalPanel container;

    private CaptionPanel panel;

    private DecoratedTextBox tbCleSs;

    private DecoratedTextBox tbNumSs;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbRelation;

    private Label lRelation;

    /** Liste déroulante qui contient les références de Sécurité Sociale des referents. */
    private DecoratedSuggestListBoxSingle<IdentifiantEidLibelleModel> lbSsReferents;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> lbRegime;

    private DecoratedSuggestListBoxSingle<CaisseSimpleModel> lbCaisse;

    private CheckBox cbTns;

    private CheckBox cbLoiMadelin;

    private CheckBox cbCouvertNow;

    private CheckBox cbCouvert6DerniersMois;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /**
     * Constructeur.
     * @param viewConstants constantes de la vue parente.
     * @param suffix suffix
     * @param iconeErreurChampManager iconeErreurChampManager
     */
    public BlocInfoSanteCompleteViewImpl(InfosAdhesionViewImplConstants viewConstants, String suffix, IconeErreurChampManager iconeErreurChampManager) {
        super();
        this.iconeErreurChampManager = iconeErreurChampManager;
        this.viewConstants = viewConstants;
        this.container = new VerticalPanel();
        container.setWidth(ComposantTarificateurConstants.POURCENT_100);

        construirePanel(suffix);
        super.initWidget(container);
    }

    /**
     * Ajoute un panel contenant les informations d'un enfant.
     * @param enfant
     */
    private void construirePanel(String suffix) {
        final Label lNumSs = new Label(viewConstants.lNumSs());
        lRelation = new Label(viewConstants.relation());
        final Label lRegime = new Label(viewConstants.lRegime());
        final Label lCaisse = new Label(viewConstants.lCaisse());
        final Label lTns = new Label(viewConstants.lTns(), false);
        final Label lLoiMadelin = new Label(viewConstants.lLoiMadelin(), false);
        final Label lcouvertNow = new Label(viewConstants.lcouvertNow(), false);
        final Label lcouvert6DerniersMois = new Label(viewConstants.lcouvert6DerniersMois(), false);

        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties =
            new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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
        };

        final SuggestListBoxSingleProperties<IdentifiantEidLibelleModel> slbIdentifiantEidLibelleProperties =
            new SuggestListBoxSingleProperties<IdentifiantEidLibelleModel>() {
            @Override
            public String getSelectSuggestRenderer(IdentifiantEidLibelleModel row) {
                return row == null ? "" : row.getLibelle();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(IdentifiantEidLibelleModel row) {
                return new String[] {row == null ? "" : row.getLibelle()};
            }
        };

        final SuggestListBoxSingleProperties<CaisseSimpleModel> slbCaisseProperties = new SuggestListBoxSingleProperties<CaisseSimpleModel>() {
            @Override
            public String getSelectSuggestRenderer(CaisseSimpleModel row) {
                return row == null ? "" : row.getCode() + "." + row.getCentre() + "." + row.getNom();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(CaisseSimpleModel row) {
                return new String[] {row == null ? "" : row.getCode() + "." + row.getCentre() + "." + row.getNom()};
            }
        };
        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> propertiesRelation = new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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
        };

        lbSsReferents = new DecoratedSuggestListBoxSingle<IdentifiantEidLibelleModel>(slbIdentifiantEidLibelleProperties);
        tbNumSs = new DecoratedTextBox();
        tbNumSs.setMaxLength(InfosAdhesionViewImplConstants.LONGUEUR_NUM_SECURITE_SOCIALE);
        tbCleSs = new DecoratedTextBox();
        tbCleSs.setMaxLength(InfosAdhesionViewImplConstants.LONGUEUR_CLE_SECURITE_SOCIALE);
        tbCleSs.setWidth(InfosAdhesionViewImplConstants.LARGEUR_TB_CLE_SS);
        slbRelation = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesRelation);
        setRelationVisible(false);
        lbRegime = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        lbCaisse = new DecoratedSuggestListBoxSingle<CaisseSimpleModel>(slbCaisseProperties);
        cbTns = new CheckBox();
        cbLoiMadelin = new CheckBox();
        cbCouvertNow = new CheckBox();
        cbCouvert6DerniersMois = new CheckBox();

        final HorizontalPanel hpSecuSociale = new HorizontalPanel();
        hpSecuSociale.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        hpSecuSociale.add(lbSsReferents);
        hpSecuSociale.add(new HTML("&nbsp;"));
        hpSecuSociale.add(tbNumSs);
        hpSecuSociale.add(new HTML("&nbsp;"));
        hpSecuSociale.add(construireBlocIcone(tbCleSs, "PersonneDto.numSecuriteSocial" + suffix));

        final FlexTable flexTable = new FlexTable();
        flexTable.setWidth(ComposantTarificateurConstants.POURCENT_100);
        flexTable.setCellSpacing(3);
        flexTable.setWidget(0, 0, lNumSs);
        flexTable.setWidget(0, 1, hpSecuSociale);
        flexTable.setWidget(1, 0, lRegime);
        flexTable.setWidget(1, 1, construireBlocIcone(lbRegime, "PersonneDto.eidRegime" + suffix));
        flexTable.setWidget(1, 2, lCaisse);
        flexTable.setWidget(1, 3, construireBlocIcone(lbCaisse, "PersonneDto.eidCaisse" + suffix));
        flexTable.setWidget(2, 0, lTns);
        flexTable.setWidget(2, 1, cbTns);
        flexTable.setWidget(2, 2, lLoiMadelin);
        flexTable.setWidget(2, 3, cbLoiMadelin);
        flexTable.setWidget(3, 0, lcouvertNow);
        flexTable.setWidget(3, 1, cbCouvertNow);
        flexTable.setWidget(3, 2, lcouvert6DerniersMois);
        flexTable.setWidget(3, 3, cbCouvert6DerniersMois);
        flexTable.setWidget(4, 0, lRelation);
        flexTable.setWidget(4, 1, construireBlocIcone(slbRelation, "RelationDto.type" + suffix));
        flexTable.getFlexCellFormatter().setColSpan(0, 1, 3);
        flexTable.getColumnFormatter().setWidth(0, InfosAdhesionViewImplConstants.LARGEUR_COL_LIBELLE_0);
        flexTable.getColumnFormatter().setWidth(1, InfosAdhesionViewImplConstants.LARGEUR_COL_CHAMP_1);
        flexTable.getColumnFormatter().setWidth(2, InfosAdhesionViewImplConstants.LARGEUR_COL_LIBELLE_2);
        flexTable.getColumnFormatter().setWidth(3, InfosAdhesionViewImplConstants.LARGEUR_COL_CHAMP_3);

        panel = new CaptionPanel();
        panel.add(flexTable);
        container.add(panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void chargerInfos(InfosPersonneModel infos, IdentifiantEidLibelleModel referenceSS, String captionText) {
        if (infos != null) {
            panel.setCaptionText(captionText);
            if (infos.getInfoSante() != null) {
                tbNumSs.setValue(infos.getInfoSante().getNumSecuriteSocial());
                tbCleSs.setValue(infos.getInfoSante().getCleSecuriteSocial());
                if (infos.getInfoSante().getRegime() != null) {
                    lbRegime.setValue(infos.getInfoSante().getRegime());
                }
                if (infos.getInfoSante().getCaisse() != null) {
                    lbCaisse.setValue(infos.getInfoSante().getCaisse());
                }
                // on bloque si referent autre
                activerSaisieInfos(infos.getId().equals(infos.getInfoSante().getIdReferent()));
            }
            cbTns.setValue(infos.isTravailleurNonSalarie());
            cbLoiMadelin.setValue(infos.isLoiMadelin());
            cbCouvertNow.setValue(infos.isActuellementCouvert());
            cbCouvert6DerniersMois.setValue(infos.isCouvertSixDerniersMois());
            if (infos.getTypeRelationAssureSocial() != null) {
            	final IdentifiantLibelleGwt item = new IdentifiantLibelleGwt();
                item.setIdentifiant(infos.getTypeRelationAssureSocial().getIdentifiant());
                item.setLibelle(infos.getTypeRelationAssureSocial().getLibelle());
            	slbRelation.setValue(item);
            }
        }
        lbSsReferents.setValue(referenceSS);
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panelIcone = new HorizontalPanel();
        panelIcone.add(composant);
        panelIcone.add(icone);
        panelIcone.setCellVerticalAlignment(icone, HasAlignment.ALIGN_MIDDLE);
        return panelIcone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<String> getNumeroSecuriteSociale() {
        return tbNumSs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<String> getCleSecuriteSociale() {
        return tbCleSs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<CaisseSimpleModel> getCaisse() {
        return lbCaisse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<IdentifiantEidLibelleModel> getReferent() {
        return lbSsReferents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<IdentifiantLibelleGwt> getRelation() {
        return slbRelation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<IdentifiantLibelleGwt> getRegime() {
        return lbRegime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasKeyPressHandlers getCleSsKeyPressHandler() {
        return tbCleSs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasKeyPressHandlers getNumSsKeyPressHandler() {
        return tbNumSs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activerSaisieInfos(boolean activerSaisie) {
        tbNumSs.setEnabled(activerSaisie);
        tbCleSs.setEnabled(activerSaisie);
        lbCaisse.setEnabled(activerSaisie);
        lbRegime.setEnabled(activerSaisie);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelationVisible(boolean visible) {
    	slbRelation.setVisible(visible);
    	lRelation.setVisible(visible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public HasValueChangeHandlers<String> getCleSsValueChangeHandler() {
        return tbCleSs;
    }

    @Override
    public HasValueChangeHandlers<String> getNumSsValueChangeHandler() {
        return tbNumSs;
    }

    @Override
    public HasValueChangeHandlers<IdentifiantEidLibelleModel> getReferentValueChangeHandler() {
        return lbSsReferents;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantEidLibelleModel> getReferentSuggestHandler() {
        return lbSsReferents;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getRelationSuggestHandler() {
        return slbRelation;
    }

    @Override
    public HasSuggestListBoxHandler<CaisseSimpleModel> getCaisseSuggestHandler() {
        return lbCaisse;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getRegimeSuggestHandler() {
        return lbRegime;
    }

    @Override
    public HasValueChangeHandlers<IdentifiantLibelleGwt> getRegimeValueChangeHandler() {
        return lbRegime;
    }

    @Override
    public void setInfosAdhesionEditables(boolean isEditable) {
        lbSsReferents.setEnabled(isEditable);
        tbNumSs.setEnabled(isEditable);
        tbCleSs.setEnabled(isEditable);
        lbRegime.setEnabled(isEditable);
        lbCaisse.setEnabled(isEditable);
        cbTns.setEnabled(isEditable);
        cbLoiMadelin.setEnabled(isEditable);
        cbCouvertNow.setEnabled(isEditable);
        cbCouvert6DerniersMois.setEnabled(isEditable);
    }

    @Override
    public HasValueChangeHandlers<CaisseSimpleModel> getCaisseValueChangeHandler() {
        return lbCaisse;
    }

    @Override
    public HasValue<Boolean> getCouvert6DerniersMois() {
        return cbCouvert6DerniersMois;
    }

    @Override
    public HasValue<Boolean> getCouvertActuellement() {
        return cbCouvertNow;
    }

    @Override
    public HasValue<Boolean> getLoiMadelin() {
        return cbLoiMadelin;
    }

    @Override
    public HasValue<Boolean> getTns() {
        return cbTns;
    }

    @Override
    public void initFocus() {
        tbNumSs.setFocus(true);
    }

    @Override
    public boolean isChampRegimeActif() {
        return lbRegime.isEnabled();
    }

}
