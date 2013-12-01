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
package com.square.composants.graphiques.lib.client.composants.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Un objet contenant le rapport d'erreur.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class RapportModel implements IsSerializable {

    /** Etat du rapport. */
    private Boolean info = false;

    /** La map conténant l'attribut et les éléments du rapport. */
    private Map<String, SousRapportModel> rapports = new HashMap<String, SousRapportModel>();

    /**
     * Message generale du rapport.
     */
    private String messageGenerale;

    /**
     * Retourne la valeur de info.
     * @return the info
     */
    public Boolean getInfo() {
        return info;
    }

    /**
     * Modifie la valeur de info.
     * @param info the info to set
     */
    public void setInfo(Boolean info) {
        this.info = info;
    }

    /**
     * Retourne la valeur de rapports.
     * @return the rapports
     */
    public Map<String, SousRapportModel> getRapports() {
        return rapports;
    }

    /**
     * Modifie la valeur de rapports.
     * @param rapports the rapports to set
     */
    public void setRapports(Map<String, SousRapportModel> rapports) {
        this.rapports = rapports;
    }

    /**
     * Méthode qui récupère l'élément du rapport en fonction de sa clé.
     * @param attribut l'attribut.
     * @return le sous rapport
     */
    public SousRapportModel recupererElement(String attribut) {
        return rapports.get(attribut);
    }

    /**Get the messageGenerale.
     * @return the messageGenerale
     */
    public String getMessageGenerale() {
        return messageGenerale;
    }

    /**Set the messageGenerale.
     * @param messageGenerale the messageGenerale to set
     */
    public void setMessageGenerale(String messageGenerale) {
        this.messageGenerale = messageGenerale;
    }

    /**
     * La méthode d'ajout d'un élement au rapport.
     * @param attribut la propriété à vérifier
     * @param message le message
     * @param erreur l'état du message
     */
    public void ajoutRapport(String attribut,  String message, Boolean erreur) {
        final SousRapportModel sousRapport = new SousRapportModel(attribut, message, erreur);
        if (erreur) {
            info = true;
        }
        //Ajout de l'élément dans la map seulement s'il n'y a pas déjà un message d'erreur
        if (!rapports.containsKey(attribut) || (!rapports.get(attribut).getErreur() &&  erreur)) {
            rapports.put(attribut, sousRapport);
        }
    }
}
