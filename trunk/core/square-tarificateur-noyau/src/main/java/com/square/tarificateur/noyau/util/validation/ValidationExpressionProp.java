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
package com.square.tarificateur.noyau.util.validation;

/**
 * Classe utilitaire pour la gestion des contrôles.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public final class ValidationExpressionProp {
    /** Le message a afficher si ok. */
    private String message;

    /** Le message a afficher si erreur. */
    private String messageErreur;

    /** Le nom de la propriété a analyser. */
    private String nomPropriete;

    /** L'index a afficher (si l'élément fait partie d'une liste. */
    private int index = -1;

    /**
     * Constructeur.
     * @param nomPropriete le nom de la propriété a analyser
     * @param message le message si ok
     * @param messageErreur le message si erreur
     */
    public ValidationExpressionProp(String nomPropriete, String message, String messageErreur) {
        this.nomPropriete = nomPropriete;
        this.message = message;
        this.messageErreur = messageErreur;
    }

    /**
     * Constructeur.
     * @param nomPropriete le nom de la propriété a analyser
     * @param index le numéro d'index pour l'affichage si l'élément fait partie d'une listes
     * @param message le message si ok
     * @param messageErreur le message si erreur
     */
    public ValidationExpressionProp(String nomPropriete, int index, String message, String messageErreur) {
        this.nomPropriete = nomPropriete;
        this.message = message;
        this.messageErreur = messageErreur;
        this.index = index;
    }

    /**
     * Retourne le message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Modifie le message.
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retourne le message d'erreur.
     * @return the messageErreur
     */
    public String getMessageErreur() {
        return messageErreur;
    }

    /**
     * Modifie le message d'erreur.
     * @param messageErreur the messageErreur to set
     */
    public void setMessageErreur(String messageErreur) {
        this.messageErreur = messageErreur;
    }

    /**
     * Retourne le nom de la propriété.
     * @return the nomPropriete
     */
    public String getNomPropriete() {
        return nomPropriete;
    }

    /**
     * Modifie le nom de la propriété.
     * @param nomPropriete the nomPropriete to set
     */
    public void setNomPropriete(String nomPropriete) {
        this.nomPropriete = nomPropriete;
    }

    /**
     * Retourne l'index.
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Modifie l'index.
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
