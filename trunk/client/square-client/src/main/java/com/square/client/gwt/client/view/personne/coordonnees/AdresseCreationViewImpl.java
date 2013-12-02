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

import static com.square.client.gwt.client.view.personne.coordonnees.AdresseCreationViewImplConstants.LARGEUR_COL_CHAMP_1;
import static com.square.client.gwt.client.view.personne.coordonnees.AdresseCreationViewImplConstants.LARGEUR_COL_CHAMP_3;
import static com.square.client.gwt.client.view.personne.coordonnees.AdresseCreationViewImplConstants.LARGEUR_COL_LIBELLE_0;
import static com.square.client.gwt.client.view.personne.coordonnees.AdresseCreationViewImplConstants.LARGEUR_COL_LIBELLE_2;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.IdentifiantLibelleBooleanModel;
import com.square.client.gwt.client.presenter.personne.PopupCoordonneesAdressePresenter.AdresseCreationView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue d'un bloc adresse.
 * @author Farh ZARROUK (farh.zarrouk@gmail.com) - SCUB
 */
public class AdresseCreationViewImpl extends Composite implements AdresseCreationView {

    private AideComposant aidecdbDateFin;

    private Label lValueDepartement;

    private Label lValueVille;

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

    private AideComposant aideslbType;

    private DecoratedSuggestListBoxSingle<CodePostalCommuneModel> slbCodePostal;

    private AideComposant aideslbCodePostal;

    private VerticalPanel conteneur;

    private Long idDepartement;

    private Long idAdresse;

    private DecoratedTextBox tbCodePostalEtranger;

    private AideComposant aidetbCodePostalEtranger;

    private DecoratedTextBox tbCommuneEtranger;

    private AideComposant aidetbCommuneEtranger;

    private RadioButton rbPasserEnSecondaire;

    private AideComposant aiderbPasserEnSecondaire;

    private RadioButton rbRenseignerDateFin;

    private AideComposant aiderbRenseignerDateFin;

    private VerticalPanel conteneurType;

    private HorizontalPanel blocIconeCodePostal;

    private HorizontalPanel blocIconeCodePostalEtranger;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Constantes de la vue. */
    private AdresseCreationViewImplConstants adresseCreationConstantes;

    /** View debugId constants. */
    private static PersonneCoordonneesViewImplDebugIdConstants viewDebugIdConstants =
        (PersonneCoordonneesViewImplDebugIdConstants) GWT.create(PersonneCoordonneesViewImplDebugIdConstants.class);

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

    private HorizontalPanel panelslbType;

    /**
     * Constructeur.
     * @param iconeErreurChampManager le gestionnaire d'erreur
     * @param index l'index
     */
    public AdresseCreationViewImpl(IconeErreurChampManager iconeErreurChampManager, int index, boolean isAdmin) {
        this.iconeErreurChampManager = iconeErreurChampManager;
        this.adresseCreationConstantes = GWT.create(AdresseCreationViewImplConstants.class);
        this.isAdmin = isAdmin;
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

        conteneur = new VerticalPanel();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.setSpacing(10);
        AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        conteneur.add(aideView);
        conteneur.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);
        conteneur.add(construireBlocType(index, slbIdentifiantLibelleProperties));
        conteneur.add(construireBlocAdresse(index, slbIdentifiantLibelleProperties));
        initWidget(conteneur);
    }

    private Widget construireBlocType(int index, SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties) {
        final Label lbType = new Label(adresseCreationConstantes.type(), false);
        slbType = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbType.ensureDebugId(viewDebugIdConstants.debugIdSlbType());
        aideslbType = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_TYPE, isAdmin);
        ajouterAideComposant(aideslbType);
        // panelslbType = new HorizontalPanel();
        // panelslbType.setSpacing(4);
        // panelslbType.add(slbType);
        // panelslbType.add(aideslbType);

        final FlexTable flexTable = new FlexTable();
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setCellSpacing(3);
        flexTable.setWidget(0, 0, lbType);
        flexTable.setWidget(0, 1, construireBlocIcone(slbType, "AdresseDto.nature." + index, aideslbType));
        flexTable.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE_0);

        conteneurType = new VerticalPanel();
        conteneurType.setWidth(AppControllerConstants.POURCENT_100);
        conteneurType.add(flexTable);
        final CaptionPanel captionPanel = new CaptionPanel(adresseCreationConstantes.typeAdresse());
        captionPanel.add(conteneurType);
        return captionPanel;
    }

    private Widget construireBlocAdresse(int index, SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties) {
        final SuggestListBoxSingleProperties<CodePostalCommuneModel> properties = new SuggestListBoxSingleProperties<CodePostalCommuneModel>() {
            @Override
            public String getSelectSuggestRenderer(CodePostalCommuneModel row) {
                return row != null ? row.getCodePostal().getLibelle() : "";
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

        tbNumeroVoie = new DecoratedTextBox();
        tbNumeroVoie.setMaxLength(5);
        tbNumeroVoie.ensureDebugId(viewDebugIdConstants.debugIdTbNumeroVoie());
        aidetbNumeroVoie = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_NUMERO_VOIE, isAdmin);
        ajouterAideComposant(aidetbNumeroVoie);

        slbNatureVoie = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbNatureVoie.ensureDebugId(viewDebugIdConstants.debugIdSlbNatureVoie());
        aideslbNatureVoie = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_NATURE_VOIE, isAdmin);
        ajouterAideComposant(aideslbNatureVoie);

        tbComplementNom = new DecoratedTextBox();
        tbComplementNom.setMaxLength(AdresseCreationViewImplConstants.MAX_LENGTH_38);
        tbComplementNom.ensureDebugId(viewDebugIdConstants.debugIdTbComplementNom());
        aidetbComplementNom = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_COMPLEMENT_NOM, isAdmin);
        ajouterAideComposant(aidetbComplementNom);

        tbAdresse = new DecoratedTextBox();
        tbAdresse.setMaxLength(AdresseCreationViewImplConstants.MAX_LENGTH_38);
        tbAdresse.ensureDebugId(viewDebugIdConstants.debugIdTbAdresse());
        aidetbAdresse = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_ADRESSE, isAdmin);
        ajouterAideComposant(aidetbAdresse);

        tbComplementAdresse = new DecoratedTextBox();
        tbComplementAdresse.setMaxLength(AdresseCreationViewImplConstants.MAX_LENGTH_38);
        tbComplementAdresse.ensureDebugId(viewDebugIdConstants.debugIdTbComplementAdresse());
        aidetbComplementAdresse = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_COMPLEMENT_ADRESSE, isAdmin);
        ajouterAideComposant(aidetbComplementAdresse);

        tbAutresComplements = new DecoratedTextBox();
        tbAutresComplements.setMaxLength(AdresseCreationViewImplConstants.MAX_LENGTH_38);
        tbAutresComplements.ensureDebugId(viewDebugIdConstants.debugIdTbAutresComplements());
        aidetbAutresComplements = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_AUTRES_COMPLEMENTS, isAdmin);
        ajouterAideComposant(aidetbAutresComplements);

        slbCodePostal = new DecoratedSuggestListBoxSingle<CodePostalCommuneModel>(properties);
        slbCodePostal.setMaxLenght(AdresseCreationViewImplConstants.MAX_LENGTH_5);
        slbCodePostal.ensureDebugId(viewDebugIdConstants.debugIdSlbCodePostal());
        aideslbCodePostal = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_CODE_POSTAL, isAdmin);
        ajouterAideComposant(aideslbCodePostal);

        slbPays = new DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel>(slbIdentifiantLibelleBooleanProperties);
        slbPays.ensureDebugId(viewDebugIdConstants.debugIdSlbPays());
        aideslbPays =new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_PAYS, isAdmin);
        ajouterAideComposant(aideslbPays);

        lValueVille = new Label();
        lValueDepartement = new Label();
        tbCommuneEtranger = new DecoratedTextBox();
        tbCommuneEtranger.setMaxLength(AdresseCreationViewImplConstants.MAX_LENGTH_38);
        tbCommuneEtranger.ensureDebugId(viewDebugIdConstants.debugIdTbCommuneEtranger());
        aidetbCommuneEtranger = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_COMMUNE_ETRANGER, isAdmin);
        ajouterAideComposant(aidetbCommuneEtranger);

        tbCodePostalEtranger = new DecoratedTextBox();
        tbCodePostalEtranger.setMaxLength(10);
        tbCodePostalEtranger.ensureDebugId(viewDebugIdConstants.debugIdTbCodePostalEtranger());
        aidetbCodePostalEtranger = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_CREATION_CODE_POSTAL_ETRANGER, isAdmin);
        ajouterAideComposant(aidetbCodePostalEtranger);

        final Label numeroVoie = new Label(adresseCreationConstantes.numeroVoie(), false);
        final Label natureVoie = new Label(adresseCreationConstantes.natureVoie(), false);
        final Label complementNom = new Label(adresseCreationConstantes.complementNom(), false);
        final Label adresse = new Label(adresseCreationConstantes.adresse(), false);
        final Label complementAdresse = new Label(adresseCreationConstantes.complementAdresse(), false);
        final Label autresComplements = new Label(adresseCreationConstantes.autresComplements(), false);
        final Label lCodePostal = new Label(adresseCreationConstantes.codePostal(), false);
        final Label lVille = new Label(adresseCreationConstantes.ville(), false);
        final Label lDepartement = new Label(adresseCreationConstantes.departement(), false);
        final Label lPays = new Label(adresseCreationConstantes.pays(), false);

        blocIconeCodePostal = construireBlocIcone(slbCodePostal, "AdresseDto.codePostal." + index, aideslbCodePostal);
        blocIconeCodePostalEtranger = construireBlocIcone(tbCodePostalEtranger, "AdresseDto.codePostalEtranger." + index, aidetbCodePostalEtranger);
        final VerticalPanel conteneurCodePostal = new VerticalPanel();
        conteneurCodePostal.add(blocIconeCodePostal);
        conteneurCodePostal.add(blocIconeCodePostalEtranger);
        final VerticalPanel conteneurVille = new VerticalPanel();
        conteneurVille.add(lValueVille);
        conteneurVille.add(tbCommuneEtranger);

        final FlexTable flexTable = new FlexTable();
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setCellSpacing(3);
        flexTable.setWidget(0, 0, complementNom);
        flexTable.setWidget(0, 1, construireBlocIcone(tbComplementNom, "AdresseDto.complementNom." + index, aidetbComplementNom));
        flexTable.setWidget(1, 0, numeroVoie);
        flexTable.setWidget(1, 1, construireBlocIcone(tbNumeroVoie, "AdresseDto.numVoie." + index, aidetbNumeroVoie));
        flexTable.setWidget(1, 2, natureVoie);
        flexTable.setWidget(1, 3, construireBlocIcone(slbNatureVoie, "AdresseDto.typeVoie." + index, aideslbNatureVoie));
        flexTable.setWidget(2, 0, adresse);
        flexTable.setWidget(2, 1, construireBlocIcone(tbAdresse, "AdresseDto.nomVoie." + index, aidetbAdresse));
        flexTable.setWidget(3, 0, complementAdresse);
        flexTable.setWidget(3, 1, construireBlocIcone(tbComplementAdresse, "AdresseDto.complementAdresse." + index, aidetbComplementAdresse));
        flexTable.setWidget(4, 0, autresComplements);
        flexTable.setWidget(4, 1, construireBlocIcone(tbAutresComplements, "AdresseDto.autresComplements." + index, aidetbAutresComplements));
        flexTable.setWidget(5, 0, lPays);
        flexTable.setWidget(5, 1, construireBlocIcone(slbPays, "AdresseDto.pays." + index, aideslbPays));
        flexTable.setWidget(6, 0, lCodePostal);
        flexTable.setWidget(6, 1, conteneurCodePostal);
        flexTable.setWidget(6, 2, lVille);
        flexTable.setWidget(6, 3, conteneurVille);
        // afficher/masquer le département suivant le cas
        flexTable.setWidget(7, 0, lDepartement);
        flexTable.setWidget(7, 1, lValueDepartement);
        flexTable.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE_0);
        flexTable.getColumnFormatter().setWidth(1, LARGEUR_COL_CHAMP_1);
        flexTable.getColumnFormatter().setWidth(2, LARGEUR_COL_LIBELLE_2);
        flexTable.getColumnFormatter().setWidth(3, LARGEUR_COL_CHAMP_3);

        final CaptionPanel captionPanel = new CaptionPanel(adresseCreationConstantes.adresse());
        captionPanel.add(flexTable);
        return captionPanel;
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
        panel.setSpacing(2);
        composant.setWidth("96%");
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
    public void construireActionAdresse() {
        final Label lAction = new Label(adresseCreationConstantes.lAction(), false);
        rbPasserEnSecondaire = new RadioButton("actionAdresse", adresseCreationConstantes.passerSecondaire());
        rbPasserEnSecondaire.setValue(true);
        rbRenseignerDateFin = new RadioButton("actionAdresse", adresseCreationConstantes.renseignerDateFin());

        final VerticalPanel actionPanel = new VerticalPanel();
        actionPanel.setSpacing(5);
        actionPanel.add(lAction);
        actionPanel.add(rbPasserEnSecondaire);
        actionPanel.add(rbRenseignerDateFin);
        conteneurType.add(actionPanel);
    }

    @Override
    public void supprimerActionAdresse() {
        if (conteneurType.getWidgetCount() > 1) {
            conteneurType.remove(1);
        }
        rbPasserEnSecondaire = null;
        rbRenseignerDateFin = null;
    }

    @Override
    public HasText getLValueDepartement() {
        return lValueDepartement;
    }

    @Override
    public HasText getLValueVille() {
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
    public HasValue<String> getTbAdresse() {
        return tbAdresse;
    }

    @Override
    public HasValue<String> getTbAutresComplements() {
        return tbAutresComplements;
    }

    @Override
    public HasValue<String> getTbComplementAdresse() {
        return tbComplementAdresse;
    }

    @Override
    public HasValue<String> getTbComplementNom() {
        return tbComplementNom;
    }

    @Override
    public HasValue<String> getTbNumeroVoie() {
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
    public HasValue<String> getTbCodePostalEtranger() {
        return tbCodePostalEtranger;
    }

    @Override
    public void afficheBlocCoordonneesFrance(Boolean affiche) {
        blocIconeCodePostal.setVisible(affiche);
        blocIconeCodePostalEtranger.setVisible(!affiche);
        lValueVille.setVisible(affiche);
        tbCommuneEtranger.setVisible(!affiche);
    }

    @Override
    public Boolean getChoixPasserEnSecondaire() {
        return rbPasserEnSecondaire != null ? rbPasserEnSecondaire.getValue() : false;
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
    public void setIdDepartement(Long idDepartement) {
        this.idDepartement = idDepartement;
    }

    @Override
    public HasValue<String> getTbCommuneEtranger() {
        return tbCommuneEtranger;
    }

    @Override
    public void nettoyerCodePostal() {
    	slbCodePostal.clean();
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
