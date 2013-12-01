package com.square.adherent.noyau.plugin.dto.sms;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Dto d'une notifcation.
 * @author nnadeau
 */
public class NotificationSmsDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -3912526135457251413L;

    /** Code de la notification. */
    private String codeNotification;

    /** Libellé de la notification. */
    private String libelleNotification;

    /** Code de la raison associée à la notification. */
    private String codeRaison;

    /** Libellé de la raison associée à la notification. */
    private String libelleRaison;

    /** Date de la notification. */
    private Calendar dateNotification;

    /**
     * Get the codeNotification value.
     * @return the codeNotification
     */
    public String getCodeNotification() {
        return codeNotification;
    }

    /**
     * Set the codeNotification value.
     * @param codeNotification the codeNotification to set
     */
    public void setCodeNotification(String codeNotification) {
        this.codeNotification = codeNotification;
    }

    /**
     * Get the codeRaison value.
     * @return the codeRaison
     */
    public String getCodeRaison() {
        return codeRaison;
    }

    /**
     * Set the codeRaison value.
     * @param codeRaison the codeRaison to set
     */
    public void setCodeRaison(String codeRaison) {
        this.codeRaison = codeRaison;
    }

    /**
     * Get the dateNotification value.
     * @return the dateNotification
     */
    public Calendar getDateNotification() {
        return dateNotification;
    }

    /**
     * Set the dateNotification value.
     * @param dateNotification the dateNotification to set
     */
    public void setDateNotification(Calendar dateNotification) {
        this.dateNotification = dateNotification;
    }

    /**
     * Get the libelleNotification value.
     * @return the libelleNotification
     */
    public String getLibelleNotification() {
        return libelleNotification;
    }

    /**
     * Set the libelleNotification value.
     * @param libelleNotification the libelleNotification to set
     */
    public void setLibelleNotification(String libelleNotification) {
        this.libelleNotification = libelleNotification;
    }

    /**
     * Get the libelleRaison value.
     * @return the libelleRaison
     */
    public String getLibelleRaison() {
        return libelleRaison;
    }

    /**
     * Set the libelleRaison value.
     * @param libelleRaison the libelleRaison to set
     */
    public void setLibelleRaison(String libelleRaison) {
        this.libelleRaison = libelleRaison;
    }
}
