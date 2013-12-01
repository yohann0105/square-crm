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
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModificationModel;

/**
 * Evènement pour l'affichage de la popup d'impression de devis.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AffichagePopupImpressionDevisEvent extends GwtEvent<AffichagePopupImpressionDevisEventHandler> {

    /** Type de l'évènement. */
    public static final Type<AffichagePopupImpressionDevisEventHandler> TYPE = new Type<AffichagePopupImpressionDevisEventHandler>();

    /** Devis à imprimer. */
    private DevisModel devis;

    private DevisModificationModel devisModificationModel;

    /** Référence de l'opportunité (eid de l'opportunité). */
    private Long referenceOpportunite;

    /** Liste des identifiants de lignes sélectionnées pour l'impression du devis. */
    private List<Long> listeIdsLignesSelectionnees;

    /**
     * Constructeur.
     * @param devis le devis.
     * @param devisModificationModel informations à enregistrer avant l'impression
     * @param referenceOpportunite la référence de l'opportunité.
     * @param listeIdsLignesSelectionnees identifiants des lignes sélectionnées.
     */
    public AffichagePopupImpressionDevisEvent(DevisModel devis, DevisModificationModel devisModificationModel, Long referenceOpportunite, List<Long> listeIdsLignesSelectionnees) {
        super();
        this.devis = devis;
        this.devisModificationModel = devisModificationModel;
        this.referenceOpportunite = referenceOpportunite;
        this.listeIdsLignesSelectionnees = listeIdsLignesSelectionnees;
    }

    @Override
    protected void dispatch(AffichagePopupImpressionDevisEventHandler handler) {
        handler.afficherPopup(this);
    }

    @Override
    public Type<AffichagePopupImpressionDevisEventHandler> getAssociatedType() {
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

    /**
     * Définit la valeur de devis.
     * @param devis la nouvelle valeur de devis
     */
    public void setDevis(DevisModel devis) {
        this.devis = devis;
    }

    /**
     * Récupère la valeur de devisModificationModel.
     * @return la valeur de devisModificationModel
     */
    public DevisModificationModel getDevisModificationModel() {
        return devisModificationModel;
    }

}
