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

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evènement pour la lancer la recherche des actions du pot commun dans Square.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class LancerRechercheActionEvent extends GwtEvent<LancerRechercheActionEventHandler> {

    /** Type de l'evenement. */
    public static final Type<LancerRechercheActionEventHandler> TYPE = new Type<LancerRechercheActionEventHandler>();

    /**
     * Flag qui indique si la recherche peut afficher les actions du pot commun ou non.
     */
    private boolean afficherActionsPotCommun;

    /**
     * Constructeur.
     * @param afficherActionsPotCommun indique si la recherche peut afficher les actions du pot commun ou non
     */
    public LancerRechercheActionEvent(boolean afficherActionsPotCommun) {
        super();
        this.afficherActionsPotCommun = afficherActionsPotCommun;
    }

    @Override
    protected void dispatch(LancerRechercheActionEventHandler handler) {
        handler.lancerRechercheActionsPotCommun(this);
    }

    @Override
    public Type<LancerRechercheActionEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Accesseur pour l'attribut afficherActionsPotCommun.
     * @return l'attribut afficherActionsPotCommun
     */
    public boolean isAfficherActionsPotCommun() {
        return afficherActionsPotCommun;
    }

}
