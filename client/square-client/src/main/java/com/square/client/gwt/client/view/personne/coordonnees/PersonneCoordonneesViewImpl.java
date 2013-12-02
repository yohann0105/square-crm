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
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.bundle.theme.smatis.SmatisResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.PaysSimpleModel;
import com.square.client.gwt.client.presenter.personne.PersonneCoordonneesPresenter;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.DecoratedTextBoxFormat;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue du moteur de recherche de personne physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonneCoordonneesViewImpl extends ExplorableComposite implements PersonneCoordonneesPresenter.PersonneCoordonneesView {

    /** View constants. */
    private static PersonneCoordonneesViewImplConstants viewConstants =
        (PersonneCoordonneesViewImplConstants) GWT.create(PersonneCoordonneesViewImplConstants.class);

    /** View debugId constants. */
    private static PersonneCoordonneesViewImplDebugIdConstants viewDebugIdConstants =
        (PersonneCoordonneesViewImplDebugIdConstants) GWT.create(PersonneCoordonneesViewImplDebugIdConstants.class);

    private VerticalPanel conteneurBoutons;

    private VerticalPanel conteneurAdresses;

    private DecoratedTextBoxFormat tbTelephone;

    private AideComposant aidetbTelephone;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbNatureTelephone;

    private AideComposant aideslbNatureTelephone;

    private DecoratedTextBoxFormat tbTelephone2;

    private AideComposant aidetbTelephone2;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbNatureTelephone2;

    private AideComposant aideslbNatureTelephone2;

    private DecoratedTextBox tbEmail;

    private AideComposant aidetbEmail;

    private Image imgFlagPaysTelephone;

    private Image imgFlagPaysTelephone2;

    private DecoratedButton btnEnregistrer;

    private DecoratedButton btnAjouter;

    private CaptionPanel fieldSetPanelAdresse;

    private CaptionPanel fieldSetPanelInformations;

    private List<BlocCoordonneesAdresseViewImpl> listeAdressesView;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Gestion affichage du composant. */
    private boolean disabled;

    /** Image du téléphone actif pour appeller la personne. */
    private Image imgTelephoneActif;

    /** Image du téléphone 2 actif pour appeller la personne. */
    private Image imgTelephone2Actif;

    /** Image du téléphone inactif pour appeller la personne. */
    private Image imgTelephoneInactif;

    /** Image du téléphone 2 inactif pour appeller la personne. */
    private Image imgTelephone2Inactif;

    /** Booléen permettant de savoir si l'utilisateur courant est connecté à la téléphonie. */
    private boolean isConnecteTelephonie;

    /** Booléen permettant de savoir si l'utilisateur courant a la téléphonie. */
    private boolean hasTelephonie;

    /** Pays associé au téléphone. */
    private PaysSimpleModel paysTelephone;

    /** Pays associé au téléphone2. */
    private PaysSimpleModel paysTelephone2;

    /** Identifiant du code pays France. */
    private Long idCodePaysFrance;

    /** Identifiant du code pays France métropolitaine. */
    private Long idCodePaysFranceMetropolitaine;

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
     * @param constantes les constantes.
     * @param disabled permet de ne pas rendre editable la vue.
     * @param hasTelephonie booléen permettant de savoir si l'utilisateur courant a la téléphonie.
     * @param isConnecteTelephonie booléen pour savoir si l'utilisateur courant est connecté à la téléphonie.
     */
    public PersonneCoordonneesViewImpl(ConstantesModel constantes, boolean disabled, boolean hasTelephonie, boolean isConnecteTelephonie) {
        this.disabled = disabled;
        this.isConnecteTelephonie = isConnecteTelephonie;
        this.hasTelephonie = hasTelephonie;
        this.idCodePaysFrance = constantes.getIdPaysFrance();
        this.idCodePaysFranceMetropolitaine = constantes.getIdPaysFranceMetropolitaine();
        iconeErreurChampManager = new IconeErreurChampManager();
        this.isAdmin = constantes.isHasRoleAdmin();
        conteneurBoutons = new VerticalPanel();
        conteneurBoutons.setWidth(AppControllerConstants.POURCENT_100);

        conteneurAdresses = new VerticalPanel();
        conteneurAdresses.ensureDebugId(viewDebugIdConstants.debugIdConteneurAdresses());
        conteneurAdresses.setSpacing(10);
        conteneurAdresses.setWidth(AppControllerConstants.POURCENT_100);

        fieldSetPanelInformations = new CaptionPanel(viewConstants.titreInformations());
        fieldSetPanelAdresse = new CaptionPanel(viewConstants.titreAdresse());
        fieldSetPanelAdresse.add(conteneurAdresses);

        construireBlocBoutons();
        construireBlocInformations();
        construireBlocsAdresse();

        final VerticalPanel conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(AppControllerConstants.POURCENT_100);
        conteneurGlobal.setSpacing(10);
        conteneurGlobal.add(conteneurBoutons);
        conteneurGlobal.add(fieldSetPanelInformations);
        conteneurGlobal.add(fieldSetPanelAdresse);

        this.initWidget(conteneurGlobal);

        this.setWidth(AppControllerConstants.POURCENT_100);
        this.addStyleName(SquareResources.INSTANCE.css().personneCoordonnees());

        if (this.disabled) {
            disableElement(this.getElement());
        }
    }

    /**
     * Permet de desactiver l'ensemble des champs.
     * @param element .
     */
    private void disableElement(Element element) {
        for (int index = 0; index < DOM.getChildCount(element); index++) {
            final Element nested = DOM.getChild(element, index);
            disableElement(nested);
            DOM.setElementPropertyBoolean(nested, "disabled", true);
        }
    }

    private void construireBlocBoutons() {
        btnEnregistrer = new DecoratedButton(viewConstants.enregistrer());
        btnEnregistrer.ensureDebugId(viewDebugIdConstants.debugIdBtnEnregistrer());
        btnAjouter = new DecoratedButton(viewConstants.ajouter());
        btnAjouter.ensureDebugId(viewDebugIdConstants.debugIdBtnAjouter());

        final HorizontalPanel blocBoutons = new HorizontalPanel();
        blocBoutons.setStylePrimaryName(SquareResources.INSTANCE.css().blocBoutons());
        blocBoutons.add(btnEnregistrer);
        blocBoutons.add(btnAjouter);

        conteneurBoutons.add(blocBoutons);
        conteneurBoutons.setCellHorizontalAlignment(blocBoutons, HasAlignment.ALIGN_RIGHT);
    }

    private void construireBlocInformations() {
        final Label lTel = new Label(viewConstants.telephone());
        final Label lTel2 = new Label(viewConstants.telephone());
        final Label lEmail = new Label(viewConstants.email());
        lTel.setWordWrap(false);
        lTel2.setWordWrap(false);
        lEmail.setWordWrap(false);

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

        tbTelephone = new DecoratedTextBoxFormat(PersonneCoordonneesViewImplConstants.FORMAT_TELEPHONE_DEFAUT);
        tbTelephone.addStyleName(SquareResources.INSTANCE.css().champTelephone());
        tbTelephone.ensureDebugId(viewDebugIdConstants.debugIdTbTelephone());
        aidetbTelephone = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_TELEPHONE, isAdmin);
        ajouterAideComposant(aidetbTelephone);

        slbNatureTelephone = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbNatureTelephone.ensureDebugId(viewDebugIdConstants.debugIdSlbNatureTelephone());
        slbNatureTelephone.addStyleName(SmatisResources.INSTANCE.css().listeNatureTelephone());
        aideslbNatureTelephone = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_NATURE_TELEPHONE, isAdmin);
        ajouterAideComposant(aideslbNatureTelephone);

        tbTelephone2 = new DecoratedTextBoxFormat(PersonneCoordonneesViewImplConstants.FORMAT_TELEPHONE_DEFAUT);
        tbTelephone2.addStyleName(SquareResources.INSTANCE.css().champTelephone());
        tbTelephone2.ensureDebugId(viewDebugIdConstants.debugIdTbTelephone2());
        aidetbTelephone2 = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_TELEPHONE_DEUX, isAdmin);
        ajouterAideComposant(aidetbTelephone2);

        slbNatureTelephone2 = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbNatureTelephone2.ensureDebugId(viewDebugIdConstants.debugIdSlbNatureTelephone2());
        slbNatureTelephone2.addStyleName(SmatisResources.INSTANCE.css().listeNatureTelephone());
        aideslbNatureTelephone2 = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_NATURE_TELEPHONE_DEUX, isAdmin);
        ajouterAideComposant(aidetbTelephone2);

        tbEmail = new DecoratedTextBox();
        tbEmail.setMaxLength(PersonneCoordonneesViewImplConstants.MAX_LENGTH_TB_EMAIL);
        tbEmail.ensureDebugId(viewDebugIdConstants.debugIdTbEmail());
        tbEmail.setWidth("300px");
        aidetbEmail = new AideComposant(AideComposantConstants.AIDE_PERSONNE_COORDONNEES_EMAIL, isAdmin);
        ajouterAideComposant(aidetbEmail);

        imgFlagPaysTelephone = new Image(SquareResources.INSTANCE.flagFr());
        imgFlagPaysTelephone.ensureDebugId(viewDebugIdConstants.debugIdImgFlagPaysTelephone());
        imgFlagPaysTelephone.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        imgFlagPaysTelephone2 = new Image(SquareResources.INSTANCE.flagFr());
        imgFlagPaysTelephone2.ensureDebugId(viewDebugIdConstants.debugIdImgFlagPaysTelephone2());
        imgFlagPaysTelephone2.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());

        final HorizontalPanel blocTel = new HorizontalPanel();
        blocTel.setSpacing(2);
        blocTel.add(lTel);
        slbNatureTelephone.addStyleName(SquareResources.INSTANCE.css().textBoxNature());
        blocTel.add(construireBlocIcone(tbTelephone, "TelephoneDto.numero.0", aidetbTelephone));
        blocTel.add(construireBlocIcone(slbNatureTelephone, "TelephoneDto.nature.0", aideslbNatureTelephone));
        blocTel.add(imgFlagPaysTelephone);
        imgTelephoneActif = new Image(SmatisResources.INSTANCE.iconePhoneActif());
        imgTelephoneActif.addStyleName(SmatisResources.INSTANCE.css().imgTelephoneActif());
        imgTelephoneActif.setVisible(false);
        imgTelephoneInactif = new Image(SmatisResources.INSTANCE.iconePhoneInactif());
        imgTelephoneInactif.setVisible(false);
        blocTel.add(imgTelephoneActif);
        blocTel.add(imgTelephoneInactif);
        blocTel.setCellVerticalAlignment(lTel, HasAlignment.ALIGN_MIDDLE);
        final HorizontalPanel blocTel2 = new HorizontalPanel();
        blocTel2.setSpacing(2);
        blocTel2.add(lTel2);
        slbNatureTelephone2.addStyleName(SquareResources.INSTANCE.css().textBoxNature());
        blocTel2.add(construireBlocIcone(tbTelephone2, "TelephoneDto.numero.1", aidetbTelephone2));
        blocTel2.add(construireBlocIcone(slbNatureTelephone2, "TelephoneDto.nature.1", aideslbNatureTelephone2));
        blocTel2.add(imgFlagPaysTelephone2);
        imgTelephone2Actif = new Image(SmatisResources.INSTANCE.iconePhoneActif());
        imgTelephone2Actif.addStyleName(SmatisResources.INSTANCE.css().imgTelephoneActif());
        imgTelephone2Actif.setVisible(false);
        imgTelephone2Inactif = new Image(SmatisResources.INSTANCE.iconePhoneInactif());
        imgTelephone2Inactif.setVisible(false);
        blocTel2.add(imgTelephone2Actif);
        blocTel2.add(imgTelephone2Inactif);
        blocTel2.setCellVerticalAlignment(lTel2, HasAlignment.ALIGN_MIDDLE);

        final FlexTable ftTelEmail = new FlexTable();
        ftTelEmail.setWidth(AppControllerConstants.POURCENT_100);
        ftTelEmail.setWidget(0, 0, lTel);
        ftTelEmail.setWidget(0, 1, blocTel);
        ftTelEmail.setWidget(0, 2, lTel2);
        ftTelEmail.setWidget(0, 3, blocTel2);
        ftTelEmail.setWidget(1, 0, lEmail);
        ftTelEmail.setWidget(1, 1, construireBlocIcone(tbEmail, "EmailDto.adresse.0", aidetbEmail));

        ftTelEmail.getColumnFormatter().setWidth(0, "4%");
        ftTelEmail.getColumnFormatter().setWidth(1, "46%");
        ftTelEmail.getColumnFormatter().setWidth(2, "4%");
        ftTelEmail.getColumnFormatter().setWidth(3, "46%");

        fieldSetPanelInformations.add(ftTelEmail);
    }

    private void construireBlocsAdresse() {
        listeAdressesView = new ArrayList<BlocCoordonneesAdresseViewImpl>();
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp, AideComposant aideComposant) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        final HorizontalPanel panelIcone = new HorizontalPanel();
        // panelIcone.setSpacing(5);
        panelIcone.add(icone);
        panelIcone.add(aideComposant);
        panel.add(panelIcone);
        // panelIcone.setCellVerticalAlignment(aideComposant, HasVerticalAlignment.ALIGN_MIDDLE);
        panel.setCellVerticalAlignment(panelIcone, HasVerticalAlignment.ALIGN_MIDDLE);
        return panel;
    }

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
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbNatureTelephone() {
        return slbNatureTelephone;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbNatureTelephone2() {
        return slbNatureTelephone2;
    }

    @Override
    public DecoratedTextBox getTbEmail() {
        return tbEmail;
    }

    @Override
    public DecoratedTextBoxFormat getTbTelephone() {
        return tbTelephone;
    }

    @Override
    public DecoratedTextBoxFormat getTbTelephone2() {
        return tbTelephone2;
    }

    @Override
    public List<BlocCoordonneesAdresseViewImpl> getListeAdressesView() {
        return listeAdressesView;
    }

    @Override
    public HasClickHandlers getBtnAjouter() {
        return btnAjouter;
    }

    @Override
    public HasClickHandlers getBtnEnregistrer() {
        return btnEnregistrer;
    }

    @Override
    public BlocCoordonneesAdresseViewImpl ajouterBlocAdresse(boolean addOnTop, int index, boolean isOpen) {
        final BlocCoordonneesAdresseViewImpl bloc = new BlocCoordonneesAdresseViewImpl(iconeErreurChampManager, viewConstants, index, isOpen, isAdmin);
        listeAdressesView.add(bloc);
        if (addOnTop) {
            // on ajoute le bloc tout en haut
            conteneurAdresses.insert(bloc, 0);
        }
        else {
            conteneurAdresses.add(bloc);
        }

        if (this.disabled) {
            disableElement(bloc.getElement());
        }
        return bloc;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(config);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public void supprimerBlocsAdresses() {
        listeAdressesView.clear();
        conteneurAdresses.clear();
    }

    @Override
    public PersonneCoordonneesViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public Long getIdPaysTelephone() {
        return paysTelephone.getId();
    }

    @Override
    public Long getIdPaysTelephone2() {
        return paysTelephone2.getId();
    }

    @Override
    public void setPaysTelephone(PaysSimpleModel pays) {
        paysTelephone = pays;
        imgFlagPaysTelephone.setUrl(PopupSelectionPaysViewImplConstants.DEBUT_URL_IMAGE_PAYS + paysTelephone.getId()
            + PopupSelectionPaysViewImplConstants.FIN_URL_IMAGE_PAYS);
        imgFlagPaysTelephone.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        final String title = viewConstants.titlePays() + PersonneCoordonneesViewImplConstants.ESPACE + pays.getLibelle()
        + PersonneCoordonneesViewImplConstants.SEPARATEUR_TITLE_FLAG + viewConstants.titleIndicatifTel()
        + PersonneCoordonneesViewImplConstants.ESPACE + pays.getIndicatifTelephone() + PersonneCoordonneesViewImplConstants.SEPARATEUR_TITLE_FLAG
        + viewConstants.titleFormatNumTel() + PersonneCoordonneesViewImplConstants.ESPACE + pays.getFormatTelephone();
        imgFlagPaysTelephone.setTitle(title);
    }

    @Override
    public void setPaysTelephone2(PaysSimpleModel pays) {
        paysTelephone2 = pays;
        imgFlagPaysTelephone2.setUrl(PopupSelectionPaysViewImplConstants.DEBUT_URL_IMAGE_PAYS + paysTelephone2.getId()
            + PopupSelectionPaysViewImplConstants.FIN_URL_IMAGE_PAYS);
        imgFlagPaysTelephone2.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        final String title = viewConstants.titlePays() + PersonneCoordonneesViewImplConstants.ESPACE + pays.getLibelle()
        + PersonneCoordonneesViewImplConstants.SEPARATEUR_TITLE_FLAG + viewConstants.titleIndicatifTel()
        + PersonneCoordonneesViewImplConstants.ESPACE + pays.getIndicatifTelephone() + PersonneCoordonneesViewImplConstants.SEPARATEUR_TITLE_FLAG
        + viewConstants.titleFormatNumTel() + PersonneCoordonneesViewImplConstants.ESPACE + pays.getFormatTelephone();
        imgFlagPaysTelephone2.setTitle(title);
    }

    @Override
    public void afficherTelephonie(Boolean affiche) {
        if (affiche != null && affiche) {
            afficherImageBoutonsTelephonie();
            // Ajout d'un handler sur la TextBox du numéro de téléphone pour savoir si le téléphone doit être activé ou non
            tbTelephone.addKeyUpHandler(new KeyUpHandler() {
                @Override
                public void onKeyUp(KeyUpEvent event) {
                    if (hasTelephonie) {
                        if (tbTelephone.getValue() != null && !"".equals(tbTelephone.getValue())
                            && tbTelephone.getValue().length() == paysTelephone.getFormatTelephone().length() && isConnecteTelephonie) {
                            imgTelephoneActif.setVisible(true);
                            imgTelephoneInactif.setVisible(false);
                        }
                        else {
                            imgTelephoneActif.setVisible(false);
                            imgTelephoneInactif.setVisible(true);
                        }
                    }
                }
            });
            // Ajout d'un handler sur la TextBox du numéro de téléphone 2 pour savoir si le téléphone doit être activé ou non
            tbTelephone2.addKeyUpHandler(new KeyUpHandler() {
                @Override
                public void onKeyUp(KeyUpEvent event) {
                    if (hasTelephonie) {
                        if (tbTelephone2.getValue() != null && !"".equals(tbTelephone2.getValue())
                            && tbTelephone2.getValue().length() == paysTelephone2.getFormatTelephone().length() && isConnecteTelephonie) {
                            imgTelephone2Actif.setVisible(true);
                            imgTelephone2Inactif.setVisible(false);
                        }
                        else {
                            imgTelephone2Actif.setVisible(false);
                            imgTelephone2Inactif.setVisible(true);
                        }
                    }
                }
            });
        }
    }

    /**
     * Affiche les images correcpondantes pour la téléphonie.
     */
    private void afficherImageBoutonsTelephonie() {
        if (hasTelephonie) {
            if (isConnecteTelephonie) {
                if (tbTelephone.getValue() != null && !"".equals(tbTelephone.getValue()) && paysTelephone != null && paysTelephone.getFormatTelephone() != null
                    && tbTelephone.getValue().length() == paysTelephone.getFormatTelephone().length()) {
                    imgTelephoneActif.setVisible(true);
                    imgTelephoneInactif.setVisible(false);
                }
                else {
                    imgTelephoneActif.setVisible(false);
                    imgTelephoneInactif.setVisible(true);
                }
                if (tbTelephone2.getValue() != null && !"".equals(tbTelephone2.getValue()) && paysTelephone2 != null
                    && paysTelephone2.getFormatTelephone() != null && tbTelephone2.getValue().length() == paysTelephone2.getFormatTelephone().length()) {
                    imgTelephone2Actif.setVisible(true);
                    imgTelephone2Inactif.setVisible(false);
                }
                else {
                    imgTelephone2Actif.setVisible(false);
                    imgTelephone2Inactif.setVisible(true);
                }
            }
            else {
                imgTelephoneActif.setVisible(false);
                imgTelephoneInactif.setVisible(true);
                imgTelephone2Actif.setVisible(false);
                imgTelephone2Inactif.setVisible(true);
            }
        }
    }

    @Override
    public HasClickHandlers getImageTelephoneActif() {
        return imgTelephoneActif;
    }

    @Override
    public HasClickHandlers getImageTelephone2Actif() {
        return imgTelephone2Actif;
    }

    @Override
    public void activerBoutonsTelephonie() {
        isConnecteTelephonie = true;
        afficherImageBoutonsTelephonie();
    }

    @Override
    public void desactiverBoutonsTelephonie() {
        isConnecteTelephonie = false;
        imgTelephoneActif.setVisible(false);
        imgTelephoneInactif.setVisible(true);
        imgTelephone2Actif.setVisible(false);
        imgTelephone2Inactif.setVisible(true);
    }

    @Override
    public HasClickHandlers getImgFlagPaysTelephone() {
        return imgFlagPaysTelephone;
    }

    @Override
    public HasClickHandlers getImgFlagPaysTelephone2() {
        return imgFlagPaysTelephone2;
    }

    @Override
    public void chargerImagePaysTelephone(String url, String title) {
        imgFlagPaysTelephone.setUrl(url);
        imgFlagPaysTelephone.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        imgFlagPaysTelephone.setTitle(title);
    }

    @Override
    public void chargerImagePaysTelephone2(String url, String title) {
        imgFlagPaysTelephone2.setUrl(url);
        imgFlagPaysTelephone2.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        imgFlagPaysTelephone2.setTitle(title);
    }

    @Override
    public void initTbTelephone(PaysSimpleModel pays, String value) {
        paysTelephone = pays;
        tbTelephone.setFormat(paysTelephone.getFormatTelephone());
        tbTelephone.setValue(value);
        afficherImageBoutonsTelephonie();
    }

    @Override
    public void initTbTelephone2(PaysSimpleModel pays, String value) {
        paysTelephone2 = pays;
        tbTelephone2.setFormat(paysTelephone2.getFormatTelephone());
        tbTelephone2.setValue(value);
        afficherImageBoutonsTelephonie();
    }

    @Override
    public String getNumeroTelephone() {
        String numTel = "";
        if (!idCodePaysFrance.equals(paysTelephone.getId()) && !idCodePaysFranceMetropolitaine.equals(paysTelephone.getId())) {
            numTel = "00" + String.valueOf(paysTelephone.getIndicatifTelephone());
        }
        numTel = numTel + tbTelephone.getValue();
        return numTel;
    }

    @Override
    public String getNumeroTelephone2() {
        String numTel = "";
        if (!idCodePaysFrance.equals(paysTelephone2.getId()) && !idCodePaysFranceMetropolitaine.equals(paysTelephone2.getId())) {
            numTel = "00" + String.valueOf(paysTelephone2.getIndicatifTelephone());
        }
        numTel = numTel + tbTelephone2.getValue();
        return numTel;
    }

    @Override
    public void nettoyerTelephone2(PaysSimpleModel paysDefautTel) {
        paysTelephone2 = paysDefautTel;
        imgFlagPaysTelephone2.setUrl(PopupSelectionPaysViewImplConstants.DEBUT_URL_IMAGE_PAYS + paysTelephone2.getId()
            + PopupSelectionPaysViewImplConstants.FIN_URL_IMAGE_PAYS);
        imgFlagPaysTelephone2.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        final String title = viewConstants.titlePays() + PersonneCoordonneesViewImplConstants.ESPACE + paysTelephone2.getLibelle()
        + PersonneCoordonneesViewImplConstants.SEPARATEUR_TITLE_FLAG + viewConstants.titleIndicatifTel()
        + PersonneCoordonneesViewImplConstants.ESPACE + paysTelephone2.getIndicatifTelephone() + PersonneCoordonneesViewImplConstants.SEPARATEUR_TITLE_FLAG
        + viewConstants.titleFormatNumTel() + PersonneCoordonneesViewImplConstants.ESPACE + paysTelephone2.getFormatTelephone();
        imgFlagPaysTelephone2.setTitle(title);
        tbTelephone2.setFormat(paysTelephone2.getFormatTelephone());
        tbTelephone2.setValue("");
        slbNatureTelephone2.setValue(null);
        afficherImageBoutonsTelephonie();
    }

    @Override
    public void nettoyerTelephone(PaysSimpleModel paysDefautTel) {
        paysTelephone = paysDefautTel;
        imgFlagPaysTelephone.setUrl(PopupSelectionPaysViewImplConstants.DEBUT_URL_IMAGE_PAYS + paysTelephone.getId()
            + PopupSelectionPaysViewImplConstants.FIN_URL_IMAGE_PAYS);
        imgFlagPaysTelephone.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        tbTelephone.setFormat(paysTelephone.getFormatTelephone());
        tbTelephone.setValue("");
        slbNatureTelephone.setValue(null);
        afficherImageBoutonsTelephonie();
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

    @Override
    public void enableBtnAjoutAdresse(boolean enabled) {
        btnAjouter.setEnabled(enabled);
    }

    @Override
    public void disableBoutonEnregistrer() {
    	btnEnregistrer.setEnabled(false);
    }
}
