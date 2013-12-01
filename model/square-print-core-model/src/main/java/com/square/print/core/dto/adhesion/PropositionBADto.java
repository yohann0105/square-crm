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
package com.square.print.core.dto.adhesion;

import java.io.Serializable;

/**
 * DTO représentant une proposition de devis pour un produit santé et d'autres familles de produits.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class PropositionBADto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6618034183036247579L;

    /** Proposition pour la famille de produits santé. */
    private PropositionFamilleProduitsBADto propositionFamilleSante;

    /** Proposition pour la famille de produits Prévoyance. */
    private PropositionFamilleProduitsBADto propositionFamillePrevoyance;


    /** Constructeur. */
    public PropositionBADto() { }

    /**
     * Constructeur.
     * @param propositionFamilleSante la proposition pour la famille de produits santé
     * @param propositionFamillePrevoyance la proposition pour la famille de produits Prévoyance
     */
    public PropositionBADto(PropositionFamilleProduitsBADto propositionFamilleSante, PropositionFamilleProduitsBADto propositionFamillePrevoyance) {
        this.propositionFamilleSante = propositionFamilleSante;
        this.propositionFamillePrevoyance = propositionFamillePrevoyance;
    }

    /**
     * Définit la valeur de propositionFamilleSante.
     * @return la valeur de propositionFamilleSante
     */
    public PropositionFamilleProduitsBADto getPropositionFamilleSante() {
        return propositionFamilleSante;
    }

    /**
     * Définit la valeur de propositionFamilleSante.
     * @param propositionFamilleSante la nouvelle valeur de propositionFamilleSante
     */
    public void setPropositionFamilleSante(PropositionFamilleProduitsBADto propositionFamilleSante) {
        this.propositionFamilleSante = propositionFamilleSante;
    }

    /**
     * Retourne la valeur de propositionFamillePrevoyance.
     * @return la valeur de propositionFamillePrevoyance
     */
    public PropositionFamilleProduitsBADto getPropositionFamillePrevoyance() {
        return propositionFamillePrevoyance;
    }

    /**
     * Définit la valeur de propositionFamillePrevoyance.
     * @param propositionFamillePrevoyance la nouvelle valeur de propositionFamillePrevoyance
     */
    public void setPropositionFamillePrevoyance(PropositionFamilleProduitsBADto propositionFamillePrevoyance) {
        this.propositionFamillePrevoyance = propositionFamillePrevoyance;
    }

}
