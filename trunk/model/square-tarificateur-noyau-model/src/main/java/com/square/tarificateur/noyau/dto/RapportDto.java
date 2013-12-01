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
package com.square.tarificateur.noyau.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Un objet contenant le rapport d'erreur.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class RapportDto implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1048552854314717776L;

    /**
     * Etat du rapport.
     */
    private Boolean enErreur = false;

    /**
     * Message generale du rapport.
     */
    private String messageGenerale;

    /**
     * La map conténant l'attribut et les éléments du rapport.
     */
    private Map<String, SousRapportDto> rapports = new HashMap<String, SousRapportDto>();

    /**
     * Constructeur rapport.
     * @param enErreur il y a une erreur dans le message.
     * @param messageGenerale message generale du rapport.
     */
    public RapportDto(Boolean enErreur, String messageGenerale) {
        super();
        this.enErreur = enErreur;
        this.messageGenerale = messageGenerale;
    }

    /**
     * Constructeur rapport.
     */
    public RapportDto() {
        super();
        this.messageGenerale = "";
    }

    /**
     * Retourne la valeur de enErreur.
     * @return the enErreur
     */
    public Boolean getEnErreur() {
        return enErreur;
    }

    /**
     * Retourne la valeur de rapports.
     * @return the rapports
     */
    public Map<String, SousRapportDto> getRapports() {
        return rapports;
    }

    /**
     * La méthode d'ajout d'un élement au rapport.
     * @param attribut la propriété à vérifier
     * @param message le message
     * @param erreur l'état du message
     */
    public void ajoutRapport(String attribut, String message, Boolean erreur) {
        final SousRapportDto sousRapport = new SousRapportDto(attribut, message, erreur);
        if (erreur) {
            enErreur = true;
            if (messageGenerale != null && !"".equals(messageGenerale)) {
                messageGenerale += "<br/>" + message;
            }
            else {
                messageGenerale = message;
            }
        }
        // Ajout de l'élément dans la map seulement s'il n'y a pas déjà un message d'erreur
        if (!rapports.containsKey(attribut) || (!rapports.get(attribut).getErreur() && erreur)) {
            rapports.put(attribut, sousRapport);
        }
    }

    /**
     * Méthode qui récupère l'élément du rapport en fonction de sa clé.
     * @param attribut l'attribut.
     * @return ElementRapportDto l'élément du rapport.
     */
    public SousRapportDto recupererElement(String attribut) {
        return rapports.get(attribut);
    }

    /**
     * Retourne le message.
     * @return the message
     */
    public String getMessageGenerale() {
        return messageGenerale;
    }
}
