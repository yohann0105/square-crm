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
package com.square.composant.emails.square.client.model;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO GWT représentant un email.
 * @author nnadeau - SCUB
 */
public class EmailModel implements IsSerializable {

    /** Identifiant. */
    private Long id;

    /** Message ID. */
    private String messageId;

    /** GroupMessageId. */
    private String groupeMessageId;

    /** Type de l'email : true si l'email contient de l'HTML. */
    private Boolean contentTypeHTML;

    /** Sujet. */
    private String sujet;

    /** Corps. */
    private String corps;

    /** Date d'envoi. */
    private String dateEnvoi;

    /** Expéditeur. */
    private PersonneEmailModel expediteur;

    /** Liste des destinataires. */
    private List<PersonneEmailModel> listeDestinataires;

    /** Liste des destinataires en copie. */
    private List<PersonneEmailModel> listeDestinatairesBcc;

    /** Liste des pièces jointes. */
    private List<PieceJointeEmailModel> listePiecesJointes;

    /** Informations du prospect associé. */
    private InformationsProspectModel infosProspect;

    /** Utilisateur en charge. */
    private IdentifiantLibelleGwt utilisateurCharge;

    /** Service associé. */
    private IdentifiantLibelleGwt service;

    /** Etat du mail. */
    private IdentifiantLibelleGwt etat;

    /**
     * Get the id value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id value.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the messageId value.
     * @return the messageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Set the messageId value.
     * @param messageId the messageId to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Get the contentTypeHTML value.
     * @return the contentTypeHTML
     */
    public Boolean getContentTypeHTML() {
        return contentTypeHTML;
    }

    /**
     * Set the contentTypeHTML value.
     * @param contentTypeHTML the contentTypeHTML to set
     */
    public void setContentTypeHTML(Boolean contentTypeHTML) {
        this.contentTypeHTML = contentTypeHTML;
    }

    /**
     * Get the sujet value.
     * @return the sujet
     */
    public String getSujet() {
        return sujet;
    }

    /**
     * Set the sujet value.
     * @param sujet the sujet to set
     */
    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    /**
     * Get the corps value.
     * @return the corps
     */
    public String getCorps() {
        return corps;
    }

    /**
     * Set the corps value.
     * @param corps the corps to set
     */
    public void setCorps(String corps) {
        this.corps = corps;
    }

    /**
     * Get the dateEnvoi value.
     * @return the dateEnvoi
     */
    public String getDateEnvoi() {
        return dateEnvoi;
    }

    /**
     * Set the dateEnvoi value.
     * @param dateEnvoi the dateEnvoi to set
     */
    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    /**
     * Get the expediteur value.
     * @return the expediteur
     */
    public PersonneEmailModel getExpediteur() {
        return expediteur;
    }

    /**
     * Set the expediteur value.
     * @param expediteur the expediteur to set
     */
    public void setExpediteur(PersonneEmailModel expediteur) {
        this.expediteur = expediteur;
    }

    /**
     * Get the listeDestinataires value.
     * @return the listeDestinataires
     */
    public List<PersonneEmailModel> getListeDestinataires() {
        if (listeDestinataires == null) {
            listeDestinataires = new ArrayList<PersonneEmailModel>();
        }
        return listeDestinataires;
    }

    /**
     * Set the listeDestinataires value.
     * @param listeDestinataires the listeDestinataires to set
     */
    public void setListeDestinataires(List<PersonneEmailModel> listeDestinataires) {
        this.listeDestinataires = listeDestinataires;
    }

    /**
     * Get the listePiecesJointes value.
     * @return the listePiecesJointes
     */
    public List<PieceJointeEmailModel> getListePiecesJointes() {
        if (listePiecesJointes == null) {
            listePiecesJointes = new ArrayList<PieceJointeEmailModel>();
        }
        return listePiecesJointes;
    }

    /**
     * Set the listePiecesJointes value.
     * @param listePiecesJointes the listePiecesJointes to set
     */
    public void setListePiecesJointes(List<PieceJointeEmailModel> listePiecesJointes) {
        this.listePiecesJointes = listePiecesJointes;
    }

    /**
     * Get the infosProspect value.
     * @return the infosProspect
     */
    public InformationsProspectModel getInfosProspect() {
        return infosProspect;
    }

    /**
     * Set the infosProspect value.
     * @param infosProspect the infosProspect to set
     */
    public void setInfosProspect(InformationsProspectModel infosProspect) {
        this.infosProspect = infosProspect;
    }

    /**
     * Get the utilisateurCharge value.
     * @return the utilisateurCharge
     */
    public IdentifiantLibelleGwt getUtilisateurCharge() {
        return utilisateurCharge;
    }

    /**
     * Set the utilisateurCharge value.
     * @param utilisateurCharge the utilisateurCharge to set
     */
    public void setUtilisateurCharge(IdentifiantLibelleGwt utilisateurCharge) {
        this.utilisateurCharge = utilisateurCharge;
    }

    /**
     * Get the service value.
     * @return the service
     */
    public IdentifiantLibelleGwt getService() {
        return service;
    }

    /**
     * Set the service value.
     * @param service the service to set
     */
    public void setService(IdentifiantLibelleGwt service) {
        this.service = service;
    }

    /**
     * Get the etat value.
     * @return the etat
     */
    public IdentifiantLibelleGwt getEtat() {
        return etat;
    }

    /**
     * Set the etat value.
     * @param etat the etat to set
     */
    public void setEtat(IdentifiantLibelleGwt etat) {
        this.etat = etat;
    }

    /**
     * Get the groupeMessageId value.
     * @return the groupeMessageId
     */
    public String getGroupeMessageId() {
        return groupeMessageId;
    }

    /**
     * Set the groupeMessageId value.
     * @param groupeMessageId the groupeMessageId to set
     */
    public void setGroupeMessageId(String groupeMessageId) {
        this.groupeMessageId = groupeMessageId;
    }

    /**
     * Get the listeDestinatairesBcc value.
     * @return the listeDestinatairesBcc
     */
    public List<PersonneEmailModel> getListeDestinatairesBcc() {
        if (listeDestinatairesBcc == null) {
            listeDestinatairesBcc = new ArrayList<PersonneEmailModel>();
        }
        return listeDestinatairesBcc;
    }

    /**
     * Set the listeDestinatairesBcc value.
     * @param listeDestinatairesBcc the listeDestinatairesBcc to set
     */
    public void setListeDestinatairesBcc(List<PersonneEmailModel> listeDestinatairesBcc) {
        this.listeDestinatairesBcc = listeDestinatairesBcc;
    }
}
