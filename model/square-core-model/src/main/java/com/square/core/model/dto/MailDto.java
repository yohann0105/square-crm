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
package com.square.core.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.scub.foundation.framework.base.exception.TechnicalException;

/**
 * DTO représentant les informations d'un email.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class MailDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 3228500070363626208L;

    /** Adresse électronique de l'expéditeur. */
    private String expediteur;

    /** Nom de l'expéditeur. */
    private String nomExpediteur;

    /** Liste des adresses électroniques des destinataires. */
    private List<String> listeDestinataires;

    /** Liste des destinataires en copie cachée. */
    private List<String> listeDestinatairesBcc;

    /** Titre du mail. */
    private String titre;

    /** Contenu du mail. */
    private String texte;

    /** Liste des pièces jointes associées au mail. */
    private List<PieceJointeFileDto> listePiecesJointes;

    /** Booléen pour savoir le mail est au format HTML. */
    private Boolean isHtml = false;

    /**
     * Constructeur.
     */
    public MailDto() {
        super();
        this.listeDestinataires = new ArrayList<String>();
        this.listeDestinatairesBcc = new ArrayList<String>();
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
    public MailDto(String expediteur, List<String> destinataires, String titre, String texte, List<PieceJointeFileDto> piecesJointes, Boolean isHtml) {
        super();
        this.listeDestinatairesBcc = new ArrayList<String>();
        if (expediteur == null) {
            throw new TechnicalException("L'expéditeur du mail doit être spécifié.");
        }
        if (!checkAdresseMail(expediteur)) {
            throw new TechnicalException("Le format de l'adresse électronique de l'expéditeur est incorrect : " + expediteur);
        }
        this.expediteur = expediteur;
        this.titre = titre;
        this.texte = texte;
        this.listePiecesJointes = piecesJointes;
        this.isHtml = isHtml;
        // Ajout des destinataires en vérifiant la syntaxe de leur adresse électronique
        this.listeDestinataires = new ArrayList<String>();
        if (destinataires == null || destinataires.size() == 0) {
            throw new TechnicalException("Le mail à envoyer doit contenir au moins un destinataire.");
        }
        for (String destinataire : destinataires) {
            ajouterDestinataire(destinataire);
        }
    }

    /**
     * Ajoute un destinataire pour le mail.
     * @param destinataire : l'adresse électronique du destinataire à ajouter.
     */
    public void ajouterDestinataire(String destinataire) {
        if (destinataire == null) {
            throw new TechnicalException("L'adresse du destinataire est vide");
        } else if (!checkAdresseMail(destinataire)) {
            throw new TechnicalException("Le format de l'adresse électronique du destinataire est incorrect : " + destinataire);
        } else {
            this.listeDestinataires.add(destinataire);
        }
    }

    /**
     * Get the expediteur value.
     * @return the expediteur
     */
    public String getExpediteur() {
        return expediteur;
    }

    /**
     * Set the expediteur value.
     * @param expediteur the expediteur to set
     */
    public void setExpediteur(String expediteur) {
        if (expediteur == null) {
            throw new TechnicalException("L'expéditeur du mail doit être spécifié.");
        }
        if (!checkAdresseMail(expediteur)) {
            throw new TechnicalException("Le format de l'adresse électronique de l'expéditeur est incorrect : " + expediteur);
        }
        this.expediteur = expediteur;
    }

    /**
     * Get the titre value.
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Set the titre value.
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Get the texte value.
     * @return the texte
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Set the texte value.
     * @param texte the texte to set
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /**
     * Get the listePiecesJointes value.
     * @return the listePiecesJointes
     */
    public List<PieceJointeFileDto> getListePiecesJointes() {
        if (listePiecesJointes == null) {
            listePiecesJointes = new ArrayList<PieceJointeFileDto>();
        }
        return listePiecesJointes;
    }

    /**
     * Set the listePiecesJointes value.
     * @param listePiecesJointes the listePiecesJointes to set
     */
    public void setListePiecesJointes(List<PieceJointeFileDto> listePiecesJointes) {
        this.listePiecesJointes = listePiecesJointes;
    }

    /**
     * Get the isHtml value.
     * @return the isHtml
     */
    public Boolean getIsHtml() {
        return isHtml;
    }

    /**
     * Set the isHtml value.
     * @param isHtml the isHtml to set
     */
    public void setIsHtml(Boolean isHtml) {
        this.isHtml = isHtml;
    }

    /**
     * Get the nomExpediteur value.
     * @return the nomExpediteur
     */
    public String getNomExpediteur() {
        return nomExpediteur;
    }

    /**
     * Set the nomExpediteur value.
     * @param nomExpediteur the nomExpediteur to set
     */
    public void setNomExpediteur(String nomExpediteur) {
        this.nomExpediteur = nomExpediteur;
    }

    /**
     * Récupération de la liste des destinataires en copie cachée.
     * @return la liste
     */
    public List<String> getListeDestinatairesBcc() {
        if (listeDestinatairesBcc == null) {
            listeDestinatairesBcc = new ArrayList<String>();
        }
        return listeDestinatairesBcc;
    }

    /**
     * Définition de la liste des destinataires en copie cachée.
     * @param listeDestinatairesBcc la liste
     */
    public void setListeDestinatairesBcc(List<String> listeDestinatairesBcc) {
        this.listeDestinatairesBcc = listeDestinatairesBcc;
    }

    /**
     * Ajoute un destinataire en copie cachée pour le mail.
     * @param destinataire : l'adresse électronique du destinataire en copie cachée à ajouter.
     */
    public void ajouterDestinataireBcc(String destinataire) {
        if (destinataire == null) {
            throw new TechnicalException("L'adresse du destinataire est vide");
        } else if (!checkAdresseMail(destinataire)) {
            throw new TechnicalException("Le format de l'adresse électronique du destinataire est incorrect : " + destinataire);
        } else {
            this.listeDestinatairesBcc.add(destinataire);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append("from : " + expediteur + " / ");
        result.append("to : " + listeDestinataires + " / ");
        result.append("bcc : " + listeDestinatairesBcc);
        return result.toString();
    }

    /**
     * Méthode de validation d'un mail.
     * @param mail : mail à vérifier.
     * @return true si le mail est valide, false sinon.
     */
    private boolean checkAdresseMail(final String mail) {
        return Pattern.matches("\\b[A-Za-z0-9\\._\\-~#]+@[A-Za-z0-9][A-Za-z0-9\\._\\-~#]*\\.[A-Za-z]{2,4}\\b", mail);
    }

    /**
     * Récupère la valeur de listeDestinataires.
     * @return la valeur de listeDestinataires
     */
    public List<String> getListeDestinataires() {
        if (listeDestinataires == null) {
            listeDestinataires = new ArrayList<String>();
        }
        return listeDestinataires;
    }

    /**
     * Définit la valeur de listeDestinataires.
     * @param listeDestinataires la nouvelle valeur de listeDestinataires
     */
    public void setListeDestinataires(List<String> listeDestinataires) {
        this.listeDestinataires = listeDestinataires;
    }
}
