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
package com.square.client.gwt.client.view.personne.coordonnees;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.ExplorableComposite;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.IdentifiantLibelleBooleanModel;
import com.square.client.gwt.client.presenter.personne.PersonneCoordonneesPresenter.BlocCoordonneesAdresseView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue d'un bloc adresse.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class BlocCoordonneesAdresseViewImpl extends ExplorableComposite implements BlocCoordonneesAdresseView {

    /** View constants. */
    private PersonneCoordonneesViewImplConstants viewConstants;

    /** View debugId constants. */
    private static PersonneCoordonneesViewImplDebugIdConstants viewDebugIdConstants =
        (PersonneCoordonneesViewImplDebugIdConstants) GWT.create(PersonneCoordonneesViewImplDebugIdConstants.class);

    private DecoratedCalendrierDateBox cdbDateDebut;

    private AideComposant aidecdbDateDebut;

    private HorizontalPanel panelcdbDateDebut;

    private DecoratedCalendrierDateBox cdbDateFin;

    private HorizontalPanel panelcdbDateFin;

    private AideComposant aidecdbDateFin;

    private Label lValueDepartement;

    private HorizontalPanel panellValueDepartement;

    private Label lValueVille;

    private HorizontalPanel panellValueVille;

    private CheckBox cbNPAI;

    private HorizontalPanel panelcbNPAI;

    private AideComposant aidecbNPAI;

    private DecoratedTextBox tbAdresse;

    private AideComposant aidetbAdresse;

    private DecoratedTextBox tbNumeroVoie;

    private AideComposant aidetbNumeroVoie;

    private DecoratedTextBox tbComplementNom;

    private AideComposant aidetbComplementNom;

    private DecoratedTextBox tbComplementAdresse;

    private AideComposant aidetbComplementAdresse;

    private DecoratedTextBox tbAutresComplements;

    private AideComposant aidetbAutresComplements;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbNatureVoie;

    private AideComposant aideslbNatureVoie;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel> slbPays;

    private AideComposant aideslbPays;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbType;

    private HorizontalPanel panelslbType;

    private AideComposant aideslbType;

    private DecoratedSuggestListBoxSingle<CodePostalCommuneModel> slbCodePostal;

    private AideComposant aideslbCodePostal;

    private BlocSyntheseDepliant blocSyntheseDepliant;

    private VerticalPanel conteneur;

    private VerticalPanel contenu;

    private Long idDepartement;

    private Long idAdresse;

    private DecoratedTextBox tbCodePostalEtranger;

    private AideComposant aidetbCodePostalEtranger;

    private DecoratedTextBox tbCommuneEtranger;

    private AideComposant aidetbCommuneEtranger;

    private FlexTable ftBlocFrance;

    private FlexTable ftBlocEtranger;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;
    /**
     * liste de composants d'aide de la vue.
     */
    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();
    /**
     * liste des events des handlers des composants d'aides.
     */
    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();
    /**
     * mode de connexion.
     */
    private boolean isAdmin;

    /**
     * Constructeur.
     * @param viewConstants les constantes d'application
     * @param iconeErreurChampManager le gestionnaire d'erreur
     * @param index l'index
     * @param isOpen demande d'ouverture ou fermeture du bloc depliant.
     */
    public BlocCoordonneesAdresseViewImpl(IconeErreurChampManager iconeErreurChampManager, PersonneCoordonneesViewImplConstants viewConstants, int index,
        boolean isOpen, boolean isAdmin) {
        this.viewConstants = viewConstants;
        this.isAdmin = isAdmin;
        this.iconeErreurChampManager = iconeErreurChampManager;
        conteneur = new VerticalPanel();
        construireContenu(index);

        final Label lbType = new Label(viewConstants.type());
        final Label lbNPAI = new Label(viewConstants.NPAI());
        final Label lbDateDebut = new Label(viewConstants.dateDebut());
        final Label lbDateFin = new Label(viewConstants.dateFin());

        final List<ChampSynthese> listeChampsSynthese = new ArrayList<ChampSynthese>();
        listeChampsSynthese.add(new ChampSynthese(panelslbType, lbType, iconeErreurChampManager.createInstance("AdresseDto.nature." + index, slbType), false));
        listeChampsSynthese.add(new ChampSynthese(panelcbNPAI, lbNPAI, iconeErreurChampManager.createInstance("AdresseDto.npai." + index, cbNPAI), false));
        listeChampsSynthese.add(new ChampSynthese(panelcdbDateDebut, lbDateDebut, iconeErreurChampManager.createInstance("AdresseDto.dateDebut." + index,
            cdbDateDebut), false));
        listeChampsSynthese.add(new ChampSynthese(panelcdbDateFin, lbDateFin,
            iconeErreurChampManager.createInstance("AdresseDto.dateFin." + index, cdbDateFin), false));
        blocSyntheseDepliant = new BlocSyntheseDepliant(listeChampsSynthese, contenu);
        blocSyntheseDepliant.ensureDebugId(viewDebugIdConstants.debugIdBlocSyntheseDepliant());
        blocSyntheseDepliant.setOpen(isOpen);
        blocSyntheseDepliant.setStyleNameHeader(SquareResources.INSTANCE.css().enteteAdresse());
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.add(blocSyntheseDepliant);
        initWidget(conteneur);
    }

    /**
     * Construit le bloc de l'adresse.
     */
    private void construireContenu(int index) {
        final Label numeroVoie = new Label(viewConstants.numeroVoie(), false);
        final Label natureVoie = new Label(viewConstants.natureVoie(), false);
        final Label complementNom = new Label(viewConstants.complementNom(), false);
        final Label adresse = new Label(viewConstants.adresse(), false);
        final Label complementAdresse = new Label(viewConstants.complementAdresse(), false);
        final Label autresComplements = new Label(viewConstants.autresComplements(), false);
        final Label lCodePostal = new Label(viewConstants.codePostal(), false);
        final Label lVille = new Label(viewConstants.ville(), false);
        final Label lDepartement = new Label(viewConstants.departement(), false);
        final Label lPays = new Label(viewConstants.pays(), false);

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
            
            final SuggestListBoxSingleProperties<IdentifiantLibelleBooleanModel> slbIdentifiantLibelleBooleanProperties =
                new SuggestListBoxSingleProperties<IdentifiantLibelleBooleanModel>() {
                    @Override
                    public String getSelectSuggestRenderer(IdentifiantLibelleBooleanModel row) {
                        return row == null ? "" : row.getLibelle();
                    }
                    @Override
                    public String[] getResultColumnsRenderer() {
                        return new String[] {};
                    }

                    @Override
                    public String[] getResultRowsRenderer(IdentifiantLibelleBooleanModel row) {
                        return new String[] {row == null ? "" : row.getLibelle()};
                    }
                };

        slbType = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbType.ensureDebugId(viewDebugIdConstants.debugIdSlbType());
        aideslbType = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_TYPE, isAdmin);
        ajouterAideComposant(aideslbType);
        panelslbType = new HorizontalPanel();
        panelslbType.setSpacing(4);
        panelslbType.add(slbType);
        panelslbType.add(aideslbType);

        cbNPAI = new CheckBox(viewConstants.NPAICheckbox());
        cbNPAI.ensureDebugId(viewDebugIdConstants.debugIdCbNPAI());
        aidecbNPAI = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_NPAI, isAdmin);
        ajouterAideComposant(aidecbNPAI);
        panelcbNPAI = new HorizontalPanel();
        panelcbNPAI.setSpacing(4);
        panelcbNPAI.add(cbNPAI);
        panelcbNPAI.add(aidecbNPAI);

        cdbDateDebut = new DecoratedCalendrierDateBox(true);
        cdbDateDebut.ensureDebugId(viewDebugIdConstants.debugIdCdbDateDebut());
        cdbDateDebut.setEnabled(false);
        aidecdbDateDebut = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_DATE_DEBUT, isAdmin);
        ajouterAideComposant(aidecdbDateDebut);
        panelcdbDateDebut = new HorizontalPanel();
        panelcdbDateDebut.setSpacing(4);
        panelcdbDateDebut.add(cdbDateDebut);
        panelcdbDateDebut.add(aidecdbDateDebut);

        cdbDateFin = new DecoratedCalendrierDateBox(true);
        cdbDateFin.ensureDebugId(viewDebugIdConstants.debugIdCdbDateFin());
        cdbDateFin.setEnabled(false);
        aidecdbDateFin = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_DATE_FIN, isAdmin);
        ajouterAideComposant(aidecdbDateFin);
        panelcdbDateFin = new HorizontalPanel();
        panelcdbDateFin.setSpacing(4);
        panelcdbDateFin.add(cdbDateFin);
        panelcdbDateFin.add(aidecdbDateFin);

        tbNumeroVoie = new DecoratedTextBox();
        tbNumeroVoie.setMaxLength(5);
        aidetbNumeroVoie = new AideComposant(AideComposantConstants.AIDE_ADRESSE_NUMERO, isAdmin);
        ajouterAideComposant(aidetbNumeroVoie);
        tbNumeroVoie.ensureDebugId(viewDebugIdConstants.debugIdTbNumeroVoie());

        slbNatureVoie = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbNatureVoie.ensureDebugId(viewDebugIdConstants.debugIdSlbNatureVoie());
        aideslbNatureVoie = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_NATURE_VOIE, isAdmin);
        ajouterAideComposant(aideslbNatureVoie);

        tbComplementNom = new DecoratedTextBox();
        tbComplementNom.setMaxLength(38);
        tbComplementNom.ensureDebugId(viewDebugIdConstants.debugIdTbComplementNom());
        aidetbComplementNom = new AideComposant(AideComposantConstants.AIDE_ADRESSE_COMPLEMENT_NOM, isAdmin);
        ajouterAideComposant(aidetbComplementNom);

        tbAdresse = new DecoratedTextBox();
        tbAdresse.setMaxLength(38);
        tbAdresse.ensureDebugId(viewDebugIdConstants.debugIdTbAdresse());
        aidetbAdresse = new AideComposant(AideComposantConstants.AIDE_ADRESSE_LIBELLE_VOIE, isAdmin);
        ajouterAideComposant(aidetbAdresse);

        tbComplementAdresse = new DecoratedTextBox();
        tbComplementAdresse.setMaxLength(38);
        tbComplementAdresse.ensureDebugId(viewDebugIdConstants.debugIdTbComplementAdresse());
        aidetbComplementAdresse = new AideComposant(AideComposantConstants.AIDE_ADRESSE_COMPLEMENT_ADRESSE, isAdmin);
        ajouterAideComposant(aidetbComplementAdresse);

        tbAutresComplements = new DecoratedTextBox();
        tbAutresComplements.setMaxLength(38);
        tbAutresComplements.ensureDebugId(viewDebugIdConstants.debugIdTbAutresComplements());
        aidetbAutresComplements = new AideComposant(AideComposantConstants.AIDE_ADRESSE_COMPLEMENT_AUTRES, isAdmin);
        ajouterAideComposant(aidetbAutresComplements);

        final SuggestListBoxSingleProperties<CodePostalCommuneModel> properties =
            new SuggestListBoxSingleProperties<CodePostalCommuneModel>() {
                @Override
                public String getSelectSuggestRenderer(CodePostalCommuneModel row) {
                    return row == null ? "" : row.getCodePostal().getLibelle();
                }
                @Override
                public String[] getResultColumnsRenderer() {
                    return new String[] {};
                }
                @Override
                public String[] getResultRowsRenderer(CodePostalCommuneModel row) {
                	if (row == null) {
                        return new String[] {"", ""};
                    } else {
                    	// Affichage du libellé de la commune si différent du libellé d'acheminement
                    	String libelleCommune = row.getLibelleAcheminement();
                    	if (!row.getLibelleAcheminement().equals(row.getCommune().getLibelle())) {
                    		libelleCommune += " (" + row.getCommune().getLibelle() + ")";
                    	}
                        return new String[] {row.getCodePostal().getLibelle(), libelleCommune};
                    }
                }
            };
        slbCodePostal = new DecoratedSuggestListBoxSingle<CodePostalCommuneModel>(properties);
        slbCodePostal.setMaxLenght(5);
        slbCodePostal.ensureDebugId(viewDebugIdConstants.debugIdSlbCodePostal());
        aideslbCodePostal = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CODE_POSTAL, isAdmin);
        ajouterAideComposant(aideslbCodePostal);

        slbPays = new DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel>(slbIdentifiantLibelleBooleanProperties);
        slbPays.ensureDebugId(viewDebugIdConstants.debugIdSlbPays());
        aideslbPays = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_PAYS, isAdmin);
        ajouterAideComposant(aideslbPays);

        lValueVille = new Label();
        lValueDepartement = new Label();
        tbCommuneEtranger = new DecoratedTextBox();
        tbCommuneEtranger.setMaxLength(38);
        tbCommuneEtranger.ensureDebugId(viewDebugIdConstants.debugIdTbCommuneEtranger());
        aidetbCommuneEtranger = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_COMMUNE_ETRANGER, isAdmin);
        ajouterAideComposant(aidetbCommuneEtranger);

        tbCodePostalEtranger = new DecoratedTextBox();
        tbCodePostalEtranger.setMaxLength(10);
        tbCodePostalEtranger.ensureDebugId(viewDebugIdConstants.debugIdTbCodePostalEtranger());
        aidetbCodePostalEtranger = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CODE_POSTAL_ETRANGER, isAdmin);
        ajouterAideComposant(aidetbCodePostalEtranger);

        contenu = new VerticalPanel();
        contenu.setSpacing(5);
        contenu.setWidth(AppControllerConstants.POURCENT_100);

        final FlexTable adresseDeplie = new FlexTable();
        adresseDeplie.setCellSpacing(3);
        adresseDeplie.setWidth(AppControllerConstants.POURCENT_100);
        adresseDeplie.setWidget(0, 0, complementNom);
        adresseDeplie.setWidget(0, 1, construireBlocIcone(tbComplementNom, "AdresseDto.complementNom." + index, aidetbComplementNom));
        adresseDeplie.setWidget(0, 2, numeroVoie);
        adresseDeplie.setWidget(0, 3, construireBlocIcone(tbNumeroVoie, "AdresseDto.numVoie." + index, aidetbNumeroVoie));
        adresseDeplie.setWidget(1, 0, natureVoie);
        adresseDeplie.setWidget(1, 1, construireBlocIcone(slbNatureVoie, "AdresseDto.typeVoie." + index, aideslbNatureVoie));
        adresseDeplie.setWidget(2, 0, adresse);
        adresseDeplie.setWidget(2, 1, construireBlocIcone(tbAdresse, "AdresseDto.nomVoie." + index, aidetbAdresse));
        adresseDeplie.setWidget(2, 2, complementAdresse);
        adresseDeplie.setWidget(2, 3, construireBlocIcone(tbComplementAdresse, "AdresseDto.complementAdresse." + index, aidetbComplementAdresse));
        adresseDeplie.setWidget(3, 0, autresComplements);
        adresseDeplie.setWidget(3, 1, construireBlocIcone(tbAutresComplements, "AdresseDto.autresComplements." + index, aidetbAutresComplements));
        adresseDeplie.setWidget(3, 2, lPays);
        adresseDeplie.setWidget(3, 3, construireBlocIcone(slbPays, "AdresseDto.pays." + index, aideslbPays));
        adresseDeplie.getColumnFormatter().setWidth(0, PersonneCoordonneesViewImplConstants.LARGEUR_COL_LIBELLE);
        adresseDeplie.getColumnFormatter().setWidth(1, PersonneCoordonneesViewImplConstants.LARGEUR_COL_CHAMP);
        adresseDeplie.getColumnFormatter().setWidth(2, PersonneCoordonneesViewImplConstants.LARGEUR_COL_LIBELLE);
        adresseDeplie.getColumnFormatter().setWidth(3, PersonneCoordonneesViewImplConstants.LARGEUR_COL_CHAMP);

        ftBlocFrance = new FlexTable();
        ftBlocFrance.setCellSpacing(3);
        ftBlocFrance.setWidth(AppControllerConstants.POURCENT_100);
        ftBlocFrance.setWidget(0, 0, lCodePostal);
        ftBlocFrance.setWidget(0, 1, construireBlocIcone(slbCodePostal, "AdresseDto.codePostalCommune." + index, aideslbCodePostal));
        ftBlocFrance.setWidget(0, 2, lVille);
        ftBlocFrance.setWidget(0, 3, lValueVille);
        ftBlocFrance.setWidget(1, 0, lDepartement);
        ftBlocFrance.setWidget(1, 1, lValueDepartement);
        ftBlocFrance.getColumnFormatter().setWidth(0, PersonneCoordonneesViewImplConstants.LARGEUR_COL_LIBELLE);
        ftBlocFrance.getColumnFormatter().setWidth(1, PersonneCoordonneesViewImplConstants.LARGEUR_COL_CHAMP);
        ftBlocFrance.getColumnFormatter().setWidth(2, PersonneCoordonneesViewImplConstants.LARGEUR_COL_LIBELLE);
        ftBlocFrance.getColumnFormatter().setWidth(3, PersonneCoordonneesViewImplConstants.LARGEUR_COL_CHAMP);

        ftBlocEtranger = new FlexTable();
        ftBlocEtranger.setCellSpacing(3);
        ftBlocEtranger.setWidth(AppControllerConstants.POURCENT_100);
        ftBlocEtranger.setWidget(0, 0, new Label(viewConstants.codePostal()));
        ftBlocEtranger.setWidget(0, 1, construireBlocIcone(tbCodePostalEtranger, "AdresseDto.codePostalEtranger." + index, aidetbCodePostalEtranger));
        ftBlocEtranger.setWidget(0, 2, new Label(viewConstants.ville()));
        ftBlocEtranger.setWidget(0, 3, construireBlocIcone(tbCommuneEtranger, "AdresseDto.communeEtranger." + index, aidetbCommuneEtranger));
        ftBlocEtranger.getColumnFormatter().setWidth(0, PersonneCoordonneesViewImplConstants.LARGEUR_COL_LIBELLE);
        ftBlocEtranger.getColumnFormatter().setWidth(1, PersonneCoordonneesViewImplConstants.LARGEUR_COL_CHAMP);
        ftBlocEtranger.getColumnFormatter().setWidth(2, PersonneCoordonneesViewImplConstants.LARGEUR_COL_LIBELLE);
        ftBlocEtranger.getColumnFormatter().setWidth(3, PersonneCoordonneesViewImplConstants.LARGEUR_COL_CHAMP);

        contenu.add(adresseDeplie);
        contenu.add(ftBlocFrance);
        contenu.add(ftBlocEtranger);
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        panel.add(icone);
        panel.setCellVerticalAlignment(icone, HasAlignment.ALIGN_MIDDLE);
        return panel;
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp, AideComposant aideComposant) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        HorizontalPanel panelIcone = new HorizontalPanel();
        // panelIcone.setSpacing(5);
        panelIcone.add(icone);
        panelIcone.add(aideComposant);
        panel.add(panelIcone);
        // panelIcone.setCellVerticalAlignment(aideComposant, HasVerticalAlignment.ALIGN_MIDDLE);
        panel.setCellVerticalAlignment(panelIcone, HasVerticalAlignment.ALIGN_MIDDLE);
        return panel;
    }

    /** remplissage des listes des aides et des events associés. */
    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public CheckBox getCbNPAI() {
        return cbNPAI;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateDebut() {
        return cdbDateDebut;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateFin() {
        return cdbDateFin;
    }

    @Override
    public Label getLValueDepartement() {
        return lValueDepartement;
    }

    @Override
    public Label getLValueVille() {
        return lValueVille;
    }

    @Override
    public HasSuggestListBoxHandler<CodePostalCommuneModel> getSlbCodePostal() {
        return slbCodePostal;
    }

    @Override
    public HasValue<CodePostalCommuneModel> getSlbCodePostalValue() {
        return slbCodePostal;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbNatureVoie() {
        return slbNatureVoie;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel> getSlbPays() {
        return slbPays;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbType() {
        return slbType;
    }

    @Override
    public DecoratedTextBox getTbAdresse() {
        return tbAdresse;
    }

    @Override
    public DecoratedTextBox getTbAutresComplements() {
        return tbAutresComplements;
    }

    @Override
    public DecoratedTextBox getTbComplementAdresse() {
        return tbComplementAdresse;
    }

    @Override
    public DecoratedTextBox getTbComplementNom() {
        return tbComplementNom;
    }

    @Override
    public DecoratedTextBox getTbNumeroVoie() {
        return tbNumeroVoie;
    }

    @Override
    public Long getIdAdresse() {
        return idAdresse;
    }

    @Override
    public Long getIdDepartement() {
        return idDepartement;
    }

    @Override
    public void setIdAdresse(Long id) {
        idAdresse = id;
    }

    @Override
    public void setIdDepartement(Long id) {
        idDepartement = id;
    }

    @Override
    public HasValue<String> getTbCodePostalEtranger() {
        return tbCodePostalEtranger;
    }

    @Override
    public HasValue<String> getTbCommuneEtranger() {
        return tbCommuneEtranger;
    }

    @Override
    public void afficheBlocCoordonneesFrance(Boolean affiche) {
        contenu.remove(affiche ? ftBlocEtranger : ftBlocFrance);
        contenu.add(affiche ? ftBlocFrance : ftBlocEtranger);
    }

    @Override
    public void nettoyerCodePostal() {
        slbCodePostal.clean();
    }

    @Override
    public Boolean getChoixPasserEnSecondaire() {
        return null;
    }

    @Override
    public List<AideComposant> getListAideCompsant() {

        return listComposantAide;
    }

    @Override
    public List<HasDemandeAideEventHandler> getListeDemandeEventHandler() {
        return listDemandeAideEventHandler;
    }

    @Override
    public List<HasUpdateAideEventHandler> getListeUpdateEventHandler() {
        return listUpdateAideEventHandler;
    }

}
