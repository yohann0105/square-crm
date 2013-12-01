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
package com.square.composant.tarificateur.square.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModel;

/**
 * Evènement pour l'affichage de la popup d'envoi de devis par mail.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AffichagePopupEnvoiDevisParMailEvent extends GwtEvent<AffichagePopupEnvoiDevisParMailEventHandler> {

    /** Type de l'évènement. */
    public static final Type<AffichagePopupEnvoiDevisParMailEventHandler> TYPE = new Type<AffichagePopupEnvoiDevisParMailEventHandler>();

    /** Devis à envoyer par email. */
    private DevisModel devis;

    /** Référence de l'opportunité (eid de l'opportunité). */
    private Long referenceOpportunite;

    /** Liste des identifiants de lignes sélectionnées pour l'envoi par email du devis. */
    private List<Long> listeIdsLignesSelectionnees;

    /**
     * Constructeur.
     * @param devis le devis.
     * @param referenceOpportunite la référence de l'opportunité.
     * @param listeIdsLignesSelectionnees identifiants des lignes sélectionnées.
     */
    public AffichagePopupEnvoiDevisParMailEvent(DevisModel devis, Long referenceOpportunite, List<Long> listeIdsLignesSelectionnees) {
        super();
        this.devis = devis;
        this.referenceOpportunite = referenceOpportunite;
        this.listeIdsLignesSelectionnees = listeIdsLignesSelectionnees;
    }

    @Override
    protected void dispatch(AffichagePopupEnvoiDevisParMailEventHandler handler) {
        handler.afficherPopup(this);
    }

    @Override
    public Type<AffichagePopupEnvoiDevisParMailEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Récupère la valeur de listeIdsLignesSelectionnees.
     * @return la valeur de listeIdsLignesSelectionnees
     */
    public List<Long> getListeIdsLignesSelectionnees() {
        return listeIdsLignesSelectionnees;
    }

    /**
     * Récupère la valeur de referenceOpportunite.
     * @return la valeur de referenceOpportunite
     */
    public Long getReferenceOpportunite() {
        return referenceOpportunite;
    }

    /**
     * Récupère la valeur de devis.
     * @return la valeur de devis
     */
    public DevisModel getDevis() {
        return devis;
    }

}
