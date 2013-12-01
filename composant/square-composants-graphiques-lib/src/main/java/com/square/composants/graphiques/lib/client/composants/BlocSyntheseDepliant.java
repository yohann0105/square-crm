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
package com.square.composants.graphiques.lib.client.composants;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.ExplorableComposite;
import org.scub.foundation.framework.gwt.module.client.util.composants.disclosurePanel.CustomDisclosurePanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composants.graphiques.lib.client.ComposantsGraphiquesConstants;
import com.square.composants.graphiques.lib.client.bundle.SquareLibResources;
import com.square.composants.graphiques.lib.client.event.CloseBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.CloseBlocSyntheseEventHandler;
import com.square.composants.graphiques.lib.client.event.HasAllBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.HasCloseBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.HasOpenBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEventHandler;

/**
 * Bloc permettant d'avoir un bloc synthese dépliant.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class BlocSyntheseDepliant extends ExplorableComposite implements OpenHandler<CustomDisclosurePanel>, CloseHandler<CustomDisclosurePanel>,
        HasOpenBlocSyntheseEventHandlers, HasCloseBlocSyntheseEventHandlers, HasAllBlocSyntheseEventHandlers {

    private CustomDisclosurePanel disclosurePanel;

    private Image icone;

    private HandlerManager handlerManager = new HandlerManager(disclosurePanel);

    private FocusPanel conteneurEnteteFocus;

    private int nbChampParLigne;

    private List<ChampSynthese> listeChamps;

    private Grid resumePanel;

    private int nbLigne;

    /**
     * Constructeur.
     * @param listeChamps Liste des champs de synthèse
     * @param contenu contenu du bloc déplié
     */
    public BlocSyntheseDepliant(List<ChampSynthese> listeChamps, Widget contenu) {
        this(listeChamps, null, contenu, listeChamps.size());
    }

    /**
     * Constructeur.
     * @param listeChamps Liste des champs de synthèse
     * @param contenu contenu du bloc déplié
     * @param nbChampParLigne permet de contrôler le nombre de ligne de l'entête.
     */
    public BlocSyntheseDepliant(List<ChampSynthese> listeChamps, Widget contenu, int nbChampParLigne) {
        this(listeChamps, null, contenu, nbChampParLigne);
    }

    /**
     * Constructeur.
     * @param listeChamps Liste des champs de synthèse
     * @param contenu contenu du bloc déplié
     * @param header .
     */
    public BlocSyntheseDepliant(List<ChampSynthese> listeChamps, Widget header, Widget contenu) {
        this(listeChamps, header, contenu, listeChamps.size());
    }

    /**
     * Constructeur.
     * @param listeChamps .
     * @param header .
     * @param contenu .
     * @param nbChampParLigne .
     */
    public BlocSyntheseDepliant(List<ChampSynthese> listeChamps, Widget header, Widget contenu, final int nbChampParLigne) {
        this.listeChamps = listeChamps;
    	this.nbChampParLigne = nbChampParLigne;
    	disclosurePanel = new CustomDisclosurePanel();
        disclosurePanel.setAnimationEnabled(true);
        disclosurePanel.addOpenHandler(this);
        disclosurePanel.addCloseHandler(this);
        disclosurePanel.addStyleName(SquareLibResources.INSTANCE.css().blocSyntheseDepliant());
        disclosurePanel.setWidth(ComposantsGraphiquesConstants.POURCENT_100);

        final SimplePanel conteneurContenu = new SimplePanel();
        conteneurContenu.add(contenu);

        icone = new Image(SquareLibResources.INSTANCE.expand());
        icone.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                disclosurePanel.setOpen(!disclosurePanel.isOpen());
            }
        });

        nbLigne = (int) Math.ceil((double) listeChamps.size() / nbChampParLigne);
        resumePanel = new Grid(nbLigne, nbChampParLigne);
        resumePanel.setWidth(ComposantsGraphiquesConstants.POURCENT_100);
        int index = 0;
        for (int row = 0; row < resumePanel.getRowCount(); row++) {
            for (int column = 0; column < resumePanel.getColumnCount() && (index < listeChamps.size()); column++) {
                final ChampSynthese champ = listeChamps.get(index++);
                resumePanel.setWidget(row, column, champ);
                addOpenEventHandler(champ);
                addCloseEventHandler(champ);
            }
        }

        final HorizontalPanel conteneurEntete = new HorizontalPanel();
        conteneurEntete.setSpacing(5);
        conteneurEntete.setWidth(ComposantsGraphiquesConstants.POURCENT_100);
        conteneurEntete.add(resumePanel);
        if (header != null) {
            conteneurEntete.add(header);
            conteneurEntete.setCellHorizontalAlignment(header, HasAlignment.ALIGN_RIGHT);
        }
        conteneurEntete.add(icone);
        conteneurEntete.setCellHorizontalAlignment(icone, HasAlignment.ALIGN_RIGHT);
        conteneurEntete.setCellVerticalAlignment(resumePanel, HasAlignment.ALIGN_MIDDLE);
        conteneurEntete.setCellVerticalAlignment(icone, HasAlignment.ALIGN_MIDDLE);

        conteneurEnteteFocus = new FocusPanel();
        conteneurEnteteFocus.setWidth(ComposantsGraphiquesConstants.POURCENT_100);
        conteneurEnteteFocus.add(conteneurEntete);

        disclosurePanel.setHeader(conteneurEnteteFocus);
        disclosurePanel.setContent(conteneurContenu);

        initWidget(disclosurePanel);
    }

    @Override
    public void onOpen(OpenEvent<CustomDisclosurePanel> event) {
        icone.setResource(SquareLibResources.INSTANCE.collapse());
        handlerManager.fireEvent(new OpenBlocSyntheseEvent());
    }

    @Override
    public void onClose(CloseEvent<CustomDisclosurePanel> event) {
        icone.setResource(SquareLibResources.INSTANCE.expand());
        handlerManager.fireEvent(new CloseBlocSyntheseEvent());
    }

    /**
     * Demande l'ouverture / la fermeture du composant.
     * @param isOpen (ouverture / fermeture)
     */
    public void setOpen(boolean isOpen) {
        disclosurePanel.setOpen(isOpen);
    }

    /**
     * Retourne le statut ouvert / fermer du composant.
     * @return le flag
     */
    public boolean isOpen() {
    	return disclosurePanel.isOpen();
    }

    /**
     * Reduit le bloc à une seule ligne.
     */
    public void reduireBlocEnUneLigne() {
    	resumePanel.resize(1, resumePanel.getColumnCount());
    }

    /**
     * Restaure le bloc à sa taille originale.
     */
    public void restaurerBloc() {
    	resumePanel.resize(nbLigne, resumePanel.getColumnCount());
        int index = nbChampParLigne;
        for (int row = 1; row < nbLigne; row++) {
            for (int column = 0; column < resumePanel.getColumnCount() && (index < listeChamps.size()); column++) {
                final ChampSynthese champ = listeChamps.get(index++);
                resumePanel.setWidget(row, column, champ);
                addOpenEventHandler(champ);
                addCloseEventHandler(champ);
            }
        }
    }

    /**
     * Définit le style de l'entete du bloc synthese.
     * @param style le style
     */
    public void addStyleNameHeader(String style) {
        disclosurePanel.getHeader().addStyleName(style);
    }

    /**
     * Définit le style de l'entete du bloc synthese.
     * @param style le style
     */
    public void removeStyleNameHeader(String style) {
        disclosurePanel.getHeader().removeStyleName(style);
    }

    /**
     * Définit le style de l'entete du bloc synthese.
     * @param style le style
     */
    public void setStyleNameHeader(String style) {
        disclosurePanel.getHeader().setStylePrimaryName(style);
    }

    @Override
    public HandlerRegistration addOpenEventHandler(OpenBlocSyntheseEventHandler handler) {
        return handlerManager.addHandler(OpenBlocSyntheseEvent.TYPE, handler);
    }

    @Override
    public HandlerRegistration addCloseEventHandler(CloseBlocSyntheseEventHandler handler) {
        return handlerManager.addHandler(CloseBlocSyntheseEvent.TYPE, handler);
    }

    /**
     * Retourne le clickhandler manager sur l'entete du bloc synthese.
     * @return le handler manager
     */
    public HasAllMouseHandlers getEnteteAllMouseHandlers() {
        return conteneurEnteteFocus;
    }

}
