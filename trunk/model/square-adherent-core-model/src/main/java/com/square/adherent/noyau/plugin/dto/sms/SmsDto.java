package com.square.adherent.noyau.plugin.dto.sms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Dto d'un sms de base.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public class SmsDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -9032064676843860324L;

    /** Identifiant. */
    private Long identifiant;

    /** Date de l'envoi. */
    private Calendar dateEnvoi;

    /** Numero de telephone. */
    private String numeroTelephone;

    /** Code pays. */
    private String codePays;

    /** Message. */
    private String message;

    /** Statut. */
    private Long idStatut;

    /** Statut. */
    private String libelleStatut;

    /** Date de réception. */
    private Calendar dateReception;

    /** Liste des notifications rattchées au sms. */
    private List < NotificationSmsDto > listeNotificationSms;


    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Get the dateEnvoi value.
     * @return the dateEnvoi
     */
    public Calendar getDateEnvoi() {
        return dateEnvoi;
    }

    /**
     * Set the dateEnvoi value.
     * @param dateEnvoi the dateEnvoi to set
     */
    public void setDateEnvoi(Calendar dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    /**
     * Get the numeroTelephone value.
     * @return the numeroTelephone
     */
    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    /**
     * Set the numeroTelephone value.
     * @param numeroTelephone the numeroTelephone to set
     */
    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    /**
     * Get the message value.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message value.
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the idStatut value.
     * @return the idStatut
     */
    public Long getIdStatut() {
        return idStatut;
    }

    /**
     * Set the idStatut value.
     * @param idStatut the idStatut to set
     */
    public void setIdStatut(Long idStatut) {
        this.idStatut = idStatut;
    }

    /**
     * Get the libelleStatut value.
     * @return the libelleStatut
     */
    public String getLibelleStatut() {
        return libelleStatut;
    }

    /**
     * Set the libelleStatut value.
     * @param libelleStatut the libelleStatut to set
     */
    public void setLibelleStatut(String libelleStatut) {
        this.libelleStatut = libelleStatut;
    }

    /**
     * Get the codePays value.
     * @return the codePays
     */
    public String getCodePays() {
        return codePays;
    }

    /**
     * Set the codePays value.
     * @param codePays the codePays to set
     */
    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    /**
     * Get the dateReception value.
     * @return the dateReception
     */
    public Calendar getDateReception() {
        return dateReception;
    }

    /**
     * Set the dateReception value.
     * @param dateReception the dateReception to set
     */
    public void setDateReception(Calendar dateReception) {
        this.dateReception = dateReception;
    }

    /**
     * Get the listeNotificationSms value.
     * @return the listeNotificationSms
     */
    public List < NotificationSmsDto > getListeNotificationSms() {
        if (listeNotificationSms == null) {
            return new ArrayList < NotificationSmsDto > ();
        }
        else {
            return listeNotificationSms;
        }
    }

    /**
     * Set the listeNotificationSms value.
     * @param listeNotificationSms the listeNotificationSms to set
     */
    public void setListeNotificationSms(List < NotificationSmsDto > listeNotificationSms) {
        this.listeNotificationSms = listeNotificationSms;
    }
}
