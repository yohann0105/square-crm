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
package com.square.client.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.square.client.gwt.client.model.PaysSimpleModel;

/**
 * Evènement pour modifier le pays d'un téléphone.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ModifierPaysTelephoneEvent extends GwtEvent<ModifierPaysTelephoneEventHandler> {

    /** Url de l'image du pays sélectionné. */
    private String urlImagePays;

    /** Le pays sélectionné. */
    private PaysSimpleModel paysTelephone;

    /** Title de l'image du pays sélectionné. */
    private String titleImagePays;

    /** Indique s'il s'agit du téléphone principal ou non. */
    private Boolean isTelephonePrincipal;

    /** Type de l'evenement. */
    public static final Type<ModifierPaysTelephoneEventHandler> TYPE = new Type<ModifierPaysTelephoneEventHandler>();

    /**
     * Constructeur.
     * @param url l'url de l'image du pays sélectionné.
     * @param title le title du pays.
     * @param pays le pays du téléphone.
     * @param isTelephonePrincipal indique s'il s'agit du téléphone princpal ou non.
     */
    public ModifierPaysTelephoneEvent(String url, String title, PaysSimpleModel pays, Boolean isTelephonePrincipal) {
        this.urlImagePays = url;
        this.titleImagePays = title;
        this.paysTelephone = pays;
        this.isTelephonePrincipal = isTelephonePrincipal;
    }

    @Override
    protected void dispatch(ModifierPaysTelephoneEventHandler handler) {
        handler.modifierPaysTelephone(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ModifierPaysTelephoneEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Récupère la valeur de isTelephonePrincipal.
     * @return la valeur de isTelephonePrincipal
     */
    public Boolean getIsTelephonePrincipal() {
        return isTelephonePrincipal;
    }

    /**
     * Récupère la valeur de urlImagePays.
     * @return la valeur de urlImagePays
     */
    public String getUrlImagePays() {
        return urlImagePays;
    }

    /**
     * Récupère la valeur de titleImagePays.
     * @return la valeur de titleImagePays
     */
    public String getTitleImagePays() {
        return titleImagePays;
    }

    /**
     * Récupère la valeur de paysTelephone.
     * @return la valeur de paysTelephone
     */
    public PaysSimpleModel getPaysTelephone() {
        return paysTelephone;
    }

}
