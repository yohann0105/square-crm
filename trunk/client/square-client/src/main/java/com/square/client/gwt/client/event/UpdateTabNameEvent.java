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

/**
 * Handler sur la demande de rafraichissement.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class UpdateTabNameEvent extends GwtEvent<UpdateTabNameEventHandler> {
    /** Type de l'evenement. */
    public static final Type<UpdateTabNameEventHandler> TYPE = new Type<UpdateTabNameEventHandler>();

    /**
     * Identifiant de l'entité à laquelle l'onglet à mettre à jour est attaché.
     */
    private Long id;

    /**
     * Le nombre d'items que l'onglet contient.
     */
    private int nbItems;

    /**
     * Le nom de l'onglet.
     */
    private String tabName;

    /**
     * Constructeur.
     * @param id identifiant de l'entité à laquelle l'onglet à mettre à jour est attaché.
     * @param tabName Nom de l'onglet.
     * @param nbItems nombre d'objets.
     */
    public UpdateTabNameEvent(Long id, int nbItems, String tabName) {
        super();
        this.id = id;
        this.tabName = tabName;
        this.nbItems = nbItems;
    }

    @Override
    public Type<UpdateTabNameEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UpdateTabNameEventHandler handler) {
        handler.updateTabName(this);
    }

    /**
     * Getter function.
     * @return the nbItems
     */
    public int getNbItems() {
        return nbItems;
    }

    /**
     * Getter function.
     * @return the tabName
     */
    public String getTabName() {
        return tabName;
    }

    /**
     * Setter function.
     * @param nbItems the nbItems to set
     */
    public void setNbItems(int nbItems) {
        this.nbItems = nbItems;
    }

    /**
     * Getter function.
     * @return the id
     */
    public Long getId() {
        return id;
    }

}
