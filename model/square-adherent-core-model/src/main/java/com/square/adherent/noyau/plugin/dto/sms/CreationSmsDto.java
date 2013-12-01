package com.square.adherent.noyau.plugin.dto.sms;

import java.io.Serializable;

/**
 * Dto de création de sms.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public class CreationSmsDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 2234882055567899176L;

    /** Numéro de téléphone de l'adhérent. */
    private String numeroTelephone;

    /** Code pays de l'adhérent. */
    private String codePays;

    /** Message du sms. */
    private String message;

    /** Statut du sms (permet de forcer un statut particulier à la création, "a envoyer" si null). */
    private Long idStatut;

    /** Flag qui permet d'outrepasser la limite d'envoi de SMS par jour. Valeur par défaut : FALSE */
    private Boolean outrepasserLimite = Boolean.FALSE;

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
     * Getter function.
     * @return the outrepasserLimite
     */
    public Boolean getOutrepasserLimite() {
        return outrepasserLimite;
    }

    /**
     * Définit si l'on souhaite outrepasser la limite de SMS.
     * @param outrepasserLimite la valeur du flag. Valeur par défaut : FALSE
     */
    public void setOutrepasserLimite(Boolean outrepasserLimite) {
        this.outrepasserLimite = outrepasserLimite;
    }

}
