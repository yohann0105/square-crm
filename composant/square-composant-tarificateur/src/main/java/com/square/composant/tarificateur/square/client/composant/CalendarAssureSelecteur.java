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
package com.square.composant.tarificateur.square.client.composant;

import java.util.Date;

import org.scub.foundation.framework.gwt.module.client.util.DataCheckerUtil;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.evenement.Evenement;
import org.scub.foundation.framework.gwt.module.client.util.evenement.ObservableGenerique;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HTML;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.selecteur.AssureSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.ProduitSelecteurModel;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;

/**
 * Un Calendar qui encapsule un assuré et change une de ses valeurs en fonction de ses évènements.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CalendarAssureSelecteur extends Composite implements ChangeHandler, HasChangeHandlers, HasAllKeyHandlers, ValueChangeHandler<Date>, Focusable {

    private AssureSelecteurModel objSelecteur;

    private DecoratedCalendrierDateBox calendar;

    private HTML labelAge;

    private ProduitSelecteurModel produit;

    private boolean isProspect;

    private ComposantTarificateurConstants constants = GWT.create(ComposantTarificateurConstants.class);;

    private ObservableGenerique obsGenerique = new ObservableGenerique();

    /**
     * Constructeur.
     * @param objSelecteur objet du selecteur à mettre a jour
     * @param labelAge label age à mettre à jour
     * @param produit produit courant
     * @param isProspect flag pour savoir si l'assure est un prospect
     * @param idCritereAgeMin identifiant du critere d'age min
     */
    @SuppressWarnings("deprecation")
	public CalendarAssureSelecteur(final AssureSelecteurModel objSelecteur, HTML labelAge, ProduitSelecteurModel produit, boolean isProspect,
        Integer idCritereAgeMin) {
        this.objSelecteur = objSelecteur;
        this.calendar = new DecoratedCalendrierDateBox();
        this.labelAge = labelAge;
        this.produit = produit;
        this.isProspect = isProspect;

        // sélection de la valeur si elle existe
        if (objSelecteur != null && objSelecteur.getDateEffetTarification() != null) {
            setDate(objSelecteur.getDateEffetTarification());
        }
        // sinon on met a jour la valeur
        else {
            setDateValeur(DateUtil.getString(calendar.getDate()));
        }

        this.calendar.addChangeHandler(this);
        this.calendar.addValueChangeHandler(this);

        initWidget(calendar);
    }

    /**
     * {@inheritDoc}
     */
    public void setEnabled(boolean enabled) {
        calendar.setEnabled(enabled);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isVisible() {
        return calendar.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    public void setVisible(boolean visible) {
        calendar.setVisible(visible);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("deprecation")
	public String getDate() {
        return DateUtil.getString(calendar.getDate());
    }

    /**
     * Définit la valeur.
     */
    private void setDateValeur(String date) {
        objSelecteur.setDateEffetTarification(date);

        if (date != null && DataCheckerUtil.isDate(date)) {
            // Notification du changement d'age du prospect
            final Evenement evenement = new Evenement(ComposantTarificateurConstants.EVENT_DEMANDE_CALCULER_AGE, constants.demandeCalculAge(), null, this);
            obsGenerique.informerObservateur(evenement);
        } else if (date == null) {
            // notification de modification de l'age pour verification des contraintes de vente
            final Evenement evenement = new Evenement(ComposantTarificateurConstants.EVENT_DEMANDE_VERIF_CONTRAINTES_VENTE,
                		constants.demandeVerificationContraintesVente(), null, this);
            obsGenerique.informerObservateur(evenement);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("deprecation")
	public void setDate(String date) {
        calendar.setDate(DateUtil.getDate(date));
        setDateValeur(date);
    }

    /**
     * Recupere la valeur de obsGenerique.
     * @return la valeur de obsGenerique
     */
    public ObservableGenerique getObsGenerique() {
        return obsGenerique;
    }

    /**
     * Retourne la valeur de objSelecteur.
     * @return la valeur de objSelecteur
     */
    public AssureSelecteurModel getObjSelecteur() {
        return objSelecteur;
    }

    /**
     * Retourne la valeur de labelAge.
     * @return la valeur de labelAge
     */
    public HTML getLabelAge() {
        return labelAge;
    }

    /**
     * Retourne la valeur de produit.
     * @return la valeur de produit
     */
    public ProduitSelecteurModel getProduit() {
        return produit;
    }

    /**
     * Retourne la valeur de isProspect.
     * @return la valeur de isProspect
     */
    public boolean isProspect() {
        return isProspect;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void onChange(ChangeEvent event) {
        setDateValeur(calendar.getDate() != null ? DateUtil.getString(calendar.getDate()) : null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onValueChange(ValueChangeEvent<Date> event) {
        setDateValeur(DateUtil.getString(event.getValue()));
    }

    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return calendar.addChangeHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return calendar.addKeyUpHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return calendar.addKeyDownHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return calendar.addKeyPressHandler(handler);
    }

    @Override
    public int getTabIndex() {
        return 0;
    }

    @Override
    public void setAccessKey(char key) {
    }

    @Override
    public void setFocus(boolean focused) {
        calendar.setFocus(focused);
    }

    @Override
    public void setTabIndex(int index) {
    }

}
