package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO représentant un email.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class EmailDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 721055478418051291L;

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
    private Calendar dateEnvoi;

    /** Expéditeur. */
    private PersonneEmailDto expediteur;

    /** Liste des destinataires. */
    private List < PersonneEmailDto > listeDestinataires;

    /** Liste des destinataires en copie cachée. */
    private List < PersonneEmailDto > listeDestinatairesBcc;

    /** Liste des pièces jointes. */
    private List < PieceJointeEmailDto > listePiecesJointes;

    /** Informations du prospect associé. */
    private InformationsProspectDto infosProspect;

    /** Utilisateur en charge. */
    private IdentifiantLibelleDto utilisateurCharge;

    /** Service associé. */
    private IdentifiantLibelleDto service;

    /** Etat du mail. */
    private IdentifiantLibelleDto etat;

    /** Header References (stocke la séquence des messageId précédents). */
    private String headerReferences;

    /** Identifiant du parent. */
    private Long idParent;

    /**
     * Retourne la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne la valeur de messageId.
     * @return la valeur de messageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Définit la valeur de messageId.
     * @param messageId la nouvelle valeur de messageId
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
     * Retourne la valeur de sujet.
     * @return la valeur de sujet
     */
    public String getSujet() {
        return sujet;
    }

    /**
     * Définit la valeur de sujet.
     * @param sujet la nouvelle valeur de sujet
     */
    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    /**
     * Retourne la valeur de corps.
     * @return la valeur de corps
     */
    public String getCorps() {
        return corps;
    }

    /**
     * Définit la valeur de corps.
     * @param corps la nouvelle valeur de corps
     */
    public void setCorps(String corps) {
        this.corps = corps;
    }

    /**
     * Retourne la valeur de dateEnvoi.
     * @return la valeur de dateEnvoi
     */
    public Calendar getDateEnvoi() {
        return dateEnvoi;
    }

    /**
     * Définit la valeur de dateEnvoi.
     * @param dateEnvoi la nouvelle valeur de dateEnvoi
     */
    public void setDateEnvoi(Calendar dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    /**
     * Retourne la valeur de expediteur.
     * @return la valeur de expediteur
     */
    public PersonneEmailDto getExpediteur() {
        return expediteur;
    }

    /**
     * Définit la valeur de expediteur.
     * @param expediteur la nouvelle valeur de expediteur
     */
    public void setExpediteur(PersonneEmailDto expediteur) {
        this.expediteur = expediteur;
    }

    /**
     * Retourne la valeur de listeDestinataires.
     * @return la valeur de listeDestinataires
     */
    public List < PersonneEmailDto > getListeDestinataires() {
        if (listeDestinataires == null) {
            listeDestinataires = new ArrayList < PersonneEmailDto > ();
        }
        return listeDestinataires;
    }

    /**
     * Définit la valeur de listeDestinataires.
     * @param listeDestinataires la nouvelle valeur de listeDestinataires
     */
    public void setListeDestinataires(List < PersonneEmailDto > listeDestinataires) {
        this.listeDestinataires = listeDestinataires;
    }

    /**
     * Retourne la valeur de listePiecesJointes.
     * @return la valeur de listePiecesJointes
     */
    public List < PieceJointeEmailDto > getListePiecesJointes() {
        if (listePiecesJointes == null) {
            listePiecesJointes = new ArrayList < PieceJointeEmailDto > ();
        }
        return listePiecesJointes;
    }

    /**
     * Définit la valeur de listePiecesJointes.
     * @param listePiecesJointes la nouvelle valeur de listePiecesJointes
     */
    public void setListePiecesJointes(List < PieceJointeEmailDto > listePiecesJointes) {
        this.listePiecesJointes = listePiecesJointes;
    }

    /**
     * Retourne la valeur de infosProspect.
     * @return la valeur de infosProspect
     */
    public InformationsProspectDto getInfosProspect() {
        return infosProspect;
    }

    /**
     * Définit la valeur de infosProspect.
     * @param infosProspect la nouvelle valeur de infosProspect
     */
    public void setInfosProspect(InformationsProspectDto infosProspect) {
        this.infosProspect = infosProspect;
    }

    /**
     * Retourne la valeur de utilisateurCharge.
     * @return la valeur de utilisateurCharge
     */
    public IdentifiantLibelleDto getUtilisateurCharge() {
        return utilisateurCharge;
    }

    /**
     * Définit la valeur de utilisateurCharge.
     * @param utilisateurCharge la nouvelle valeur de utilisateurCharge
     */
    public void setUtilisateurCharge(IdentifiantLibelleDto utilisateurCharge) {
        this.utilisateurCharge = utilisateurCharge;
    }

    /**
     * Retourne la valeur de service.
     * @return la valeur de service
     */
    public IdentifiantLibelleDto getService() {
        return service;
    }

    /**
     * Définit la valeur de service.
     * @param service la nouvelle valeur de service
     */
    public void setService(IdentifiantLibelleDto service) {
        this.service = service;
    }

    /**
     * Retourne la valeur de etat.
     * @return la valeur de etat
     */
    public IdentifiantLibelleDto getEtat() {
        return etat;
    }

    /**
     * Définit la valeur de etat.
     * @param etat la nouvelle valeur de etat
     */
    public void setEtat(IdentifiantLibelleDto etat) {
        this.etat = etat;
    }

    /**
     * Retourne la valeur de groupeMessageId.
     * @return la valeur de groupeMessageId
     */
    public String getGroupeMessageId() {
        return groupeMessageId;
    }

    /**
     * Définit la valeur de groupeMessageId.
     * @param groupeMessageId la nouvelle valeur de groupeMessageId
     */
    public void setGroupeMessageId(String groupeMessageId) {
        this.groupeMessageId = groupeMessageId;
    }

    /**
     * Retourne la valeur de headerReferences.
     * @return la valeur de headerReferences
     */
    public String getHeaderReferences() {
        return headerReferences;
    }

    /**
     * Définit la valeur de headerReferences.
     * @param headerReferences la nouvelle valeur de headerReferences
     */
    public void setHeaderReferences(String headerReferences) {
        this.headerReferences = headerReferences;
    }

    /**
     * Get the idParent value.
     * @return the idParent
     */
    public Long getIdParent() {
        return idParent;
    }

    /**
     * Set the idParent value.
     * @param idParent the idParent to set
     */
    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }

    /**
     * Get the listeDestinatairesBcc value.
     * @return the listeDestinatairesBcc
     */
    public List < PersonneEmailDto > getListeDestinatairesBcc() {
        return listeDestinatairesBcc;
    }

    /**
     * Set the listeDestinatairesBcc value.
     * @param listeDestinatairesBcc the listeDestinatairesBcc to set
     */
    public void setListeDestinatairesBcc(List < PersonneEmailDto > listeDestinatairesBcc) {
        this.listeDestinatairesBcc = listeDestinatairesBcc;
    }

}
