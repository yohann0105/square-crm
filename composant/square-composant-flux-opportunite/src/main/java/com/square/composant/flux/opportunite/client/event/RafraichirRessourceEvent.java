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
package com.square.composant.flux.opportunite.client.event;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evènement pour rafraichir les infos des ressources.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class RafraichirRessourceEvent extends GwtEvent<RafraichirRessourceEventHandler> {

    /** Type de l'evenement. */
    public static final Type<RafraichirRessourceEventHandler> TYPE = new Type<RafraichirRessourceEventHandler>();

    /** Liste des identifiants des ressources à rafraichir. */
    private List<Long> listeIdsRessources;

    /**
     * Constructeur.
     * @param listeIdsRessources la liste des identifiants des ressources à rafraichir.
     */
    public RafraichirRessourceEvent(List<Long> listeIdsRessources) {
        super();
        this.listeIdsRessources = listeIdsRessources;
    }

    @Override
    protected void dispatch(RafraichirRessourceEventHandler handler) {
        handler.rafraichirRessource(this);
    }

    @Override
    public Type<RafraichirRessourceEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Récupère la valeur de listeIdsRessources.
     * @return la valeur de listeIdsRessources
     */
    public List<Long> getListeIdsRessources() {
        if (listeIdsRessources == null) {
            listeIdsRessources = new ArrayList<Long>();
        }
        return listeIdsRessources;
    }
}
