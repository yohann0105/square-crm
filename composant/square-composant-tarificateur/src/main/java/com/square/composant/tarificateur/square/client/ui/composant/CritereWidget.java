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
package com.square.composant.tarificateur.square.client.ui.composant;

import org.scub.foundation.framework.gwt.module.client.util.evenement.Evenement;
import org.scub.foundation.framework.gwt.module.client.util.evenement.Observateur;

import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.composant.CalendarAssureSelecteur;
import com.square.composant.tarificateur.square.client.composant.CritereWidgetAvailable;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;

/**
 * Objet permettant de lier un critère à un widget.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class CritereWidget implements Observateur {

    /** L'identifiant du critère. */
    private Integer idCritere;

    /** Le widget associé. */
    private CritereWidgetAvailable widget;

    private Integer idCritereGeneration;

    private Integer idCritereMois;

    /**
     * Constructeur.
     * @param idCritereGeneration idCritereGeneration
     * @param idCritereMois idCritereMois
     */
    public CritereWidget(Integer idCritereGeneration, Integer idCritereMois) {
        this.idCritereGeneration = idCritereGeneration;
        this.idCritereMois = idCritereMois;
    }

    /**
     * Retourne la valeur de idCritere.
     * @return idCritere
     */
    public Integer getIdCritere() {
        return idCritere;
    }

    /**
     * Définit la valeur de idCritere.
     * @param idCritere la nouvelle valeur de idCritere
     */
    public void setIdCritere(Integer idCritere) {
        this.idCritere = idCritere;
    }

    /**
     * Retourne la valeur de wiget.
     * @return wiget
     */
    public Widget getWidget() {
        return widget.getCritereWidget();
    }

    /**
     * Afficher ou pas le widget du critere.
     * @param visible true pour afficher false sinon.
     */
    public void setVisible(boolean visible) {
        widget.setVisible(visible);
    }

    /**
     * Enabled ou pas le widget du critere.
     * @param enabled true pour modifiable false sinon.
     */
    public void setEnabled(boolean enabled) {
        widget.setEnabled(enabled);
    }

    /**
     * Récupère la valeur du widget.
     * @return la valeur du widget
     */
    public String getValeurCritereWidget() {
        return widget.getValeurCritereWidget();
    }

    /**
     * Affecte la valeur du widget.
     * @param valeur la valeur
     */
    public void setValeurCritereWidget(String valeur) {
        widget.setValeurCritereWidget(valeur);
    }

    /**
     * {@inheritDoc}
     */
    public void nouvelEvenement(Evenement evenement) {
        if (evenement.getNom().equals(ComposantTarificateurConstants.EVENT_DEMANDE_CALCULER_AGE)) {
            if (evenement.getSender() instanceof CalendarAssureSelecteur) {
                final CalendarAssureSelecteur calendar = (CalendarAssureSelecteur) evenement.getSender();
                // on met à jour le mois
                if (idCritere.equals(idCritereMois)) {
                    setValeurCritereWidget(calendar.getDate().split("/")[1]);
                }
                // on met à jour la génération
                else if (idCritere.equals(idCritereGeneration)) {
                    setValeurCritereWidget(calendar.getDate().split("/")[2]);
                }
            }
        }
    }

    /**
     * Set the widget value.
     * @param widget the widget to set
     */
    public void setWidget(CritereWidgetAvailable widget) {
        this.widget = widget;
    }
}
