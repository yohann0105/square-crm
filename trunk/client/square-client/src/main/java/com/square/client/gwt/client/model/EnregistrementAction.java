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
package com.square.client.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EnregistrementAction implements IsSerializable {

    /** L'identifiant de l'action. */
    private Long idAction;

    /** L'identifiant du statut. */
    private Long idStatut;

    /** L'identifiant du résultat. */
    private Long idResultat;

    /**
     * Renvoi la valeur de idAction.
     * @return idAction
     */
    public Long getIdAction() {
        return idAction;
    }

    /**
     * Modifie idAction.
     * @param idAction la nouvelle valeur de idAction
     */
    public void setIdAction(Long idAction) {
        this.idAction = idAction;
    }

    /**
     * Renvoi la valeur de idStatut.
     * @return idStatut
     */
    public Long getIdStatut() {
        return idStatut;
    }

    /**
     * Modifie idStatut.
     * @param idStatut la nouvelle valeur de idStatut
     */
    public void setIdStatut(Long idStatut) {
        this.idStatut = idStatut;
    }

    /**
     * Renvoi la valeur de idResultat.
     * @return idResultat
     */
    public Long getIdResultat() {
        return idResultat;
    }

    /**
     * Modifie idResultat.
     * @param idResultat la nouvelle valeur de idResultat
     */
    public void setIdResultat(Long idResultat) {
        this.idResultat = idResultat;
    }

}
