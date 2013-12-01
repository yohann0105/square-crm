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
package com.square.composant.tarificateur.square.client.model;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle pour les critères de recherche des modèles de devis.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class CritereModeleDevisModel implements IsSerializable {

    /** L'identifiant du devis. */
    private Long idDevis;

    /** La liste des identifiants des modèles de devis rechercher. */
    private List<Long> listeIdsModeles;

    /**
     * Récupère la valeur de listeIdsModeles.
     * @return la valeur de listeIdsModeles
     */
    public List<Long> getListeIdsModeles() {
        return listeIdsModeles;
    }

    /**
     * Définit la valeur de listeIdsModeles.
     * @param listeIdsModeles la nouvelle valeur de listeIdsModeles
     */
    public void setListeIdsModeles(List<Long> listeIdsModeles) {
        this.listeIdsModeles = listeIdsModeles;
    }

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
}
