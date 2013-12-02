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
package com.square.client.gwt.client.model.actions;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.client.gwt.client.presenter.personne.PersonneActionsPresenter.PersonneInfosActionView;

/**
 * Item de l'arborescence des actions pour la mise en place du zoom.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ActionZoomModel implements IsSerializable {

    private Long idAction;

    private PersonneInfosActionView viewAction;

    private Long idActionMere;

    private Integer indexBloc;

    /**
     * Constructeur.
     * @deprecated utiliser le constructeur parametré pour mettre le handler
     */
    @Deprecated
    public ActionZoomModel() {
        super();
    }

    /**
     * Constructeur.
     * @param idAction l'id de l'action
     * @param idActionMere l'id de l'action mere
     * @param viewAction la reference de la vue de l'action
     * @param indexBloc l'index du bloc
     */
    public ActionZoomModel(final Long idAction, final Long idActionMere, final PersonneInfosActionView viewAction, Integer indexBloc) {
        super();
        this.idAction = idAction;
        this.idActionMere = idActionMere;
        this.viewAction = viewAction;
        this.indexBloc = indexBloc;
    }

    /**
     * Get the idAction value.
     * @return the idAction
     */
    public Long getIdAction() {
        return idAction;
    }

    /**
     * Set the idAction value.
     * @param idAction the idAction to set
     */
    public void setIdAction(Long idAction) {
        this.idAction = idAction;
    }

    /**
     * Get the idActionMere value.
     * @return the idActionMere
     */
    public Long getIdActionMere() {
        return idActionMere;
    }

    /**
     * Set the idActionMere value.
     * @param idActionMere the idActionMere to set
     */
    public void setIdActionMere(Long idActionMere) {
        this.idActionMere = idActionMere;
    }

    /**
     * Get the viewAction value.
     * @return the viewAction
     */
    public PersonneInfosActionView getViewAction() {
        return viewAction;
    }

    /**
     * Set the viewAction value.
     * @param viewAction the viewAction to set
     */
    public void setViewAction(PersonneInfosActionView viewAction) {
        this.viewAction = viewAction;
    }

    /**
     * Get the indexBloc value.
     * @return the indexBloc
     */
    public Integer getIndexBloc() {
        return indexBloc;
    }

    /**
     * Set the indexBloc value.
     * @param indexBloc the indexBloc to set
     */
    public void setIndexBloc(Integer indexBloc) {
        this.indexBloc = indexBloc;
    }
}
