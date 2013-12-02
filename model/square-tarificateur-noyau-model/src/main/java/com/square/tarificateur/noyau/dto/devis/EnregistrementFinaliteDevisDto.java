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
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO pour enregistrer la finalité d'un devis et de ses lignes de devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class EnregistrementFinaliteDevisDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 6398296805889942144L;

    /** Identifiant devis. */
    private Long idDevis;

    /** Identifiant du motif de devis. */
    private Long idMotifDevis;

    /** Liste des DTO pour enregistrer la finalité des lignes de devis. */
    private List<EnregistrementFinaliteLigneDevisDto> listeEnregistrementsFinaliteLignes;

    /**
     * Constructeur.
     */
    public EnregistrementFinaliteDevisDto() {
        super();
    }

    /**
     * Constructeur.
     * @param idDevis l'identifiant du devis.
     */
    public EnregistrementFinaliteDevisDto(final Long idDevis) {
        super();
        this.idDevis = idDevis;
    }

    /**
     * Constructeur.
     * @param idDevis l'identifiant du devis.
     * @param listeEnregistrementsFinaliteLignes la liste des enregistrements des finalités des lignes.
     */
    public EnregistrementFinaliteDevisDto(Long idDevis, List<EnregistrementFinaliteLigneDevisDto> listeEnregistrementsFinaliteLignes) {
        super();
        this.idDevis = idDevis;
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
    public List<EnregistrementFinaliteLigneDevisDto> getListeEnregistrementsFinaliteLignes() {
        if (listeEnregistrementsFinaliteLignes == null) {
            listeEnregistrementsFinaliteLignes = new ArrayList<EnregistrementFinaliteLigneDevisDto>();
        }
        return listeEnregistrementsFinaliteLignes;
    }

    /**
     * Définit la valeur de listeEnregistrementsFinaliteLignes.
     * @param listeEnregistrementsFinaliteLignes the listeEnregistrementsFinaliteLignes to set
     */
    public void setListeEnregistrementsFinaliteLignes(List<EnregistrementFinaliteLigneDevisDto> listeEnregistrementsFinaliteLignes) {
        this.listeEnregistrementsFinaliteLignes = listeEnregistrementsFinaliteLignes;
    }

    /**
     * Getter function.
     * @return the idMotifDevis
     */
    public Long getIdMotifDevis() {
        return idMotifDevis;
    }

    /**
     * Setter function.
     * @param idMotifDevis the idMotifDevis to set
     */
    public void setIdMotifDevis(Long idMotifDevis) {
        this.idMotifDevis = idMotifDevis;
    }
}
