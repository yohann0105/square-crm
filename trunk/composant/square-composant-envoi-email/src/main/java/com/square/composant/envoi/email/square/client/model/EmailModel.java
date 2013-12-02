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
package com.square.composant.envoi.email.square.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle pour un email.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class EmailModel implements IsSerializable {

    /** Adresse électronique de l'expéditeur. */
    private String expediteur;

    /** Nom de l'expéditeur. */
    private String nomExpediteur;

    /** Liste des adresses électroniques des destinataires. */
    private List<String> listeDestinataires;

    /** Titre du mail. */
    private String titre;

    /** Texte du mail. */
    private String texte;

    /** Liste des pièces jointes associées au mail. */
    private List<PieceJointeModel> listePiecesJointes;

    /** Booléen pour savoir le mail est au format HTML. */
    private Boolean isHtml;

    /**
     * Constructeur par défaut.
     */
    public EmailModel() {
    }

    /**
     * Constructeur.
     * @param expediteur : adresse électronique de l'expéditeur du mail.
     * @param destinataires : liste des adresses électroniques des destinataires du mail.
     * @param titre : titre du mail.
     * @param texte : texte du mail.
     * @param piecesJointes : liste des pièces jointes du mail.
     * @param isHtml : true si le mail est au format HTML, false si non.
     */
    public EmailModel(String expediteur, List<String> destinataires, String titre, String texte, List<PieceJointeModel> piecesJointes, Boolean isHtml) {
        this.expediteur = expediteur;
        this.titre = titre;
        this.texte = texte;
        this.listePiecesJointes = piecesJointes;
        this.isHtml = isHtml;
        // Ajout des destinataires en vérifiant la syntaxe de leur adresse électronique
        this.listeDestinataires = new ArrayList<String>();
        for (String destinataire : destinataires) {
            if (destinataire != null && !"".equals(destinataire.trim())) {
                this.listeDestinataires.add(destinataire);
            }
        }
    }

    /**
     * Récupère la valeur de expediteur.
     * @return la valeur de expediteur
     */
    public String getExpediteur() {
        return expediteur;
    }

    /**
     * Définit la valeur de expediteur.
     * @param expediteur la nouvelle valeur de expediteur
     */
    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    /**
     * Récupère la valeur de nomExpediteur.
     * @return la valeur de nomExpediteur
     */
    public String getNomExpediteur() {
        return nomExpediteur;
    }

    /**
     * Définit la valeur de nomExpediteur.
     * @param nomExpediteur la nouvelle valeur de nomExpediteur
     */
    public void setNomExpediteur(String nomExpediteur) {
        this.nomExpediteur = nomExpediteur;
    }

    /**
     * Récupère la valeur de listeDestinataires.
     * @return la valeur de listeDestinataires
     */
    public List<String> getListeDestinataires() {
        return listeDestinataires;
    }

    /**
     * Récupère la valeur de titre.
     * @return la valeur de titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Définit la valeur de titre.
     * @param titre la nouvelle valeur de titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Récupère la valeur de texte.
     * @return la valeur de texte
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Définit la valeur de texte.
     * @param texte la nouvelle valeur de texte
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /**
     * Récupère la valeur de listePiecesJointes.
     * @return la valeur de listePiecesJointes
     */
    public List<PieceJointeModel> getListePiecesJointes() {
        return listePiecesJointes;
    }

    /**
     * Définit la valeur de listePiecesJointes.
     * @param listePiecesJointes la nouvelle valeur de listePiecesJointes
     */
    public void setListePiecesJointes(List<PieceJointeModel> listePiecesJointes) {
        this.listePiecesJointes = listePiecesJointes;
    }

    /**
     * Récupère la valeur de isHtml.
     * @return la valeur de isHtml
     */
    public Boolean getIsHtml() {
        return isHtml;
    }

    /**
     * Définit la valeur de isHtml.
     * @param isHtml la nouvelle valeur de isHtml
     */
    public void setIsHtml(Boolean isHtml) {
        this.isHtml = isHtml;
    }
}
