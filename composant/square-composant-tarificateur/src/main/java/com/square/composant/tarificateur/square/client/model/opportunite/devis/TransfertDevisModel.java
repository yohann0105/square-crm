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
package com.square.composant.tarificateur.square.client.model.opportunite.devis;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model permettant le transfert de lignes de devis d'un devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class TransfertDevisModel implements IsSerializable {

    /** Identifiant du devis source. */
    private Long idDevis;

    /** Liste des identifiants des lignes de devis à transférer. */
    private List<Long> listeIdsLignesDevisATransferer;

    /**
     * Récupère la valeur de idDevis.
     * @return la valeur de idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }

    /**
     * Définit la valeur de idDevis.
     * @param idDevis la nouvelle valeur de idDevis
     */
    public void setIdDevis(Long idDevis) {
        this.idDevis = idDevis;
    }

    /**
     * Récupère la valeur de listeIdsLignesDevisATransferer.
     * @return la valeur de listeIdsLignesDevisATransferer
     */
    public List<Long> getListeIdsLignesDevisATransferer() {
        return listeIdsLignesDevisATransferer;
    }

    /**
     * Définit la valeur de listeIdsLignesDevisATransferer.
     * @param listeIdsLignesDevisATransferer la nouvelle valeur de listeIdsLignesDevisATransferer
     */
    public void setListeIdsLignesDevisATransferer(List<Long> listeIdsLignesDevisATransferer) {
        this.listeIdsLignesDevisATransferer = listeIdsLignesDevisATransferer;
    }

}
