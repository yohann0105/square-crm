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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model Gwt pour enregistrer la finalité d'un devis et de ses lignes de devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class EnregistrementFinaliteDevisModel implements IsSerializable {

    /** Identifiant devis. */
    private Long idDevis;

    /** Identifiant du motif de devis. */
    private Long idMotifDevis;

    /** Liste des DTO pour enregistrer la finalité des lignes de devis. */
    private List<EnregistrementFinaliteLigneDevisModel> listeEnregistrementsFinaliteLignes;

    /**
     * Constructeur.
     */
    public EnregistrementFinaliteDevisModel() {
        super();
    }

    /**
     * Constructeur.
     * @param idDevis l'identifiant du devis.
     * @param idMotifDevis l'identifiant du motif de devis.
     */
    public EnregistrementFinaliteDevisModel(final Long idDevis, final Long idMotifDevis) {
        super();
        this.idDevis = idDevis;
        this.idMotifDevis = idMotifDevis;
    }

    /**
     * Constructeur.
     * @param idDevis l'identifiant du devis.
     * @param idMotifDevis l'identifiant du motif de devis.
     * @param listeEnregistrementsFinaliteLignes la liste des enregistrements des finalités des lignes.
     */
    public EnregistrementFinaliteDevisModel(Long idDevis, final Long idMotifDevis,
        List<EnregistrementFinaliteLigneDevisModel> listeEnregistrementsFinaliteLignes) {
        super();
        this.idDevis = idDevis;
        this.idMotifDevis = idMotifDevis;
        this.listeEnregistrementsFinaliteLignes = listeEnregistrementsFinaliteLignes;
    }

    /**
     * Get the idDevis value.
     * @return the idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }

    /**
     * Set the idDevis value.
     * @param idDevis the idDevis to set
     */
    public void setIdDevis(Long idDevis) {
        this.idDevis = idDevis;
    }

    /**
     * Récupère la valeur de listeEnregistrementsFinaliteLignes.
     * @return the listeEnregistrementsFinaliteLignes
     */
    public List<EnregistrementFinaliteLigneDevisModel> getListeEnregistrementsFinaliteLignes() {
        if (listeEnregistrementsFinaliteLignes == null) {
            listeEnregistrementsFinaliteLignes = new ArrayList<EnregistrementFinaliteLigneDevisModel>();
        }
        return listeEnregistrementsFinaliteLignes;
    }

    /**
     * Définit la valeur de listeEnregistrementsFinaliteLignes.
     * @param listeEnregistrementsFinaliteLignes the listeEnregistrementsFinaliteLignes to set
     */
    public void setListeEnregistrementsFinaliteLignes(List<EnregistrementFinaliteLigneDevisModel> listeEnregistrementsFinaliteLignes) {
        this.listeEnregistrementsFinaliteLignes = listeEnregistrementsFinaliteLignes;
    }

    /**
     * Get the idMotifDevis value.
     * @return the idMotifDevis
     */
    public Long getIdMotifDevis() {
        return idMotifDevis;
    }

    /**
     * Set the idMotifDevis value.
     * @param idMotifDevis the idMotifDevis to set
     */
    public void setIdMotifDevis(Long idMotifDevis) {
        this.idMotifDevis = idMotifDevis;
    }
}
